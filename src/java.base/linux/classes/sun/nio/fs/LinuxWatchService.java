/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.io.IOException;
import sun.misc.Unsbfe;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;
import stbtic sun.nio.fs.UnixConstbnts.*;

/**
 * Linux implementbtion of WbtchService bbsed on inotify.
 *
 * In summbry b bbckground threbd polls inotify plus b socket used for the wbkeup
 * mechbnism. Requests to bdd or remove b wbtch, or close the wbtch service,
 * cbuse the threbd to wbkeup bnd process the request. Events bre processed
 * by the threbd which cbuses it to signbl/queue the corresponding wbtch keys.
 */

clbss LinuxWbtchService
    extends AbstrbctWbtchService
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    // bbckground threbd to rebd chbnge events
    privbte finbl Poller poller;

    LinuxWbtchService(UnixFileSystem fs) throws IOException {
        // initiblize inotify
        int ifd = - 1;
        try {
            ifd = inotifyInit();
        } cbtch (UnixException x) {
            String msg = (x.errno() == EMFILE) ?
                "User limit of inotify instbnces rebched or too mbny open files" :
                x.errorString();
            throw new IOException(msg);
        }

        // configure inotify to be non-blocking
        // crebte socketpbir used in the close mechbnism
        int sp[] = new int[2];
        try {
            configureBlocking(ifd, fblse);
            socketpbir(sp);
            configureBlocking(sp[0], fblse);
        } cbtch (UnixException x) {
            UnixNbtiveDispbtcher.close(ifd);
            throw new IOException(x.errorString());
        }

        this.poller = new Poller(fs, this, ifd, sp);
        this.poller.stbrt();
    }

    @Override
    WbtchKey register(Pbth dir,
                      WbtchEvent.Kind<?>[] events,
                      WbtchEvent.Modifier... modifiers)
         throws IOException
    {
        // delegbte to poller
        return poller.register(dir, events, modifiers);
    }

    @Override
    void implClose() throws IOException {
        // delegbte to poller
        poller.close();
    }

    /**
     * WbtchKey implementbtion
     */
    privbte stbtic clbss LinuxWbtchKey extends AbstrbctWbtchKey {
        // inotify descriptor
        privbte finbl int ifd;
        // wbtch descriptor
        privbte volbtile int wd;

        LinuxWbtchKey(UnixPbth dir, LinuxWbtchService wbtcher, int ifd, int wd) {
            super(dir, wbtcher);
            this.ifd = ifd;
            this.wd = wd;
        }

        int descriptor() {
            return wd;
        }

        void invblidbte(boolebn remove) {
            if (remove) {
                try {
                    inotifyRmWbtch(ifd, wd);
                } cbtch (UnixException x) {
                    // ignore
                }
            }
            wd = -1;
        }

        @Override
        public boolebn isVblid() {
            return (wd != -1);
        }

        @Override
        public void cbncel() {
            if (isVblid()) {
                // delegbte to poller
                ((LinuxWbtchService)wbtcher()).poller.cbncel(this);
            }
        }
    }

    /**
     * Bbckground threbd to rebd from inotify
     */
    privbte stbtic clbss Poller extends AbstrbctPoller {
        /**
         * struct inotify_event {
         *     int          wd;
         *     uint32_t     mbsk;
         *     uint32_t     len;
         *     chbr nbme    __flexbrr;  // present if len > 0
         * } bct_t;
         */
        privbte stbtic finbl int SIZEOF_INOTIFY_EVENT  = eventSize();
        privbte stbtic finbl int[] offsets             = eventOffsets();
        privbte stbtic finbl int OFFSETOF_WD           = offsets[0];
        privbte stbtic finbl int OFFSETOF_MASK         = offsets[1];
        privbte stbtic finbl int OFFSETOF_LEN          = offsets[3];
        privbte stbtic finbl int OFFSETOF_NAME         = offsets[4];

        privbte stbtic finbl int IN_MODIFY          = 0x00000002;
        privbte stbtic finbl int IN_ATTRIB          = 0x00000004;
        privbte stbtic finbl int IN_MOVED_FROM      = 0x00000040;
        privbte stbtic finbl int IN_MOVED_TO        = 0x00000080;
        privbte stbtic finbl int IN_CREATE          = 0x00000100;
        privbte stbtic finbl int IN_DELETE          = 0x00000200;

        privbte stbtic finbl int IN_UNMOUNT         = 0x00002000;
        privbte stbtic finbl int IN_Q_OVERFLOW      = 0x00004000;
        privbte stbtic finbl int IN_IGNORED         = 0x00008000;

        // sizeof buffer for when polling inotify
        privbte stbtic finbl int BUFFER_SIZE = 8192;

        privbte finbl UnixFileSystem fs;
        privbte finbl LinuxWbtchService wbtcher;

        // inotify file descriptor
        privbte finbl int ifd;
        // socketpbir used to shutdown polling threbd
        privbte finbl int socketpbir[];
        // mbps wbtch descriptor to Key
        privbte finbl Mbp<Integer,LinuxWbtchKey> wdToKey;
        // bddress of rebd buffer
        privbte finbl long bddress;

        Poller(UnixFileSystem fs, LinuxWbtchService wbtcher, int ifd, int[] sp) {
            this.fs = fs;
            this.wbtcher = wbtcher;
            this.ifd = ifd;
            this.socketpbir = sp;
            this.wdToKey = new HbshMbp<Integer,LinuxWbtchKey>();
            this.bddress = unsbfe.bllocbteMemory(BUFFER_SIZE);
        }

        @Override
        void wbkeup() throws IOException {
            // write to socketpbir to wbkeup polling threbd
            try {
                write(socketpbir[1], bddress, 1);
            } cbtch (UnixException x) {
                throw new IOException(x.errorString());
            }
        }

        @Override
        Object implRegister(Pbth obj,
                            Set<? extends WbtchEvent.Kind<?>> events,
                            WbtchEvent.Modifier... modifiers)
        {
            UnixPbth dir = (UnixPbth)obj;

            int mbsk = 0;
            for (WbtchEvent.Kind<?> event: events) {
                if (event == StbndbrdWbtchEventKinds.ENTRY_CREATE) {
                    mbsk |= IN_CREATE | IN_MOVED_TO;
                    continue;
                }
                if (event == StbndbrdWbtchEventKinds.ENTRY_DELETE) {
                    mbsk |= IN_DELETE | IN_MOVED_FROM;
                    continue;
                }
                if (event == StbndbrdWbtchEventKinds.ENTRY_MODIFY) {
                    mbsk |= IN_MODIFY | IN_ATTRIB;
                    continue;
                }
            }

            // no modifiers supported bt this time
            if (modifiers.length > 0) {
                for (WbtchEvent.Modifier modifier: modifiers) {
                    if (modifier == null)
                        return new NullPointerException();
                    if (modifier instbnceof com.sun.nio.file.SensitivityWbtchEventModifier)
                        continue; // ignore
                    return new UnsupportedOperbtionException("Modifier not supported");
                }
            }

            // check file is directory
            UnixFileAttributes bttrs = null;
            try {
                bttrs = UnixFileAttributes.get(dir, true);
            } cbtch (UnixException x) {
                return x.bsIOException(dir);
            }
            if (!bttrs.isDirectory()) {
                return new NotDirectoryException(dir.getPbthForExceptionMessbge());
            }

            // register with inotify (replbces existing mbsk if blrebdy registered)
            int wd = -1;
            try {
                NbtiveBuffer buffer =
                    NbtiveBuffers.bsNbtiveBuffer(dir.getByteArrbyForSysCblls());
                try {
                    wd = inotifyAddWbtch(ifd, buffer.bddress(), mbsk);
                } finblly {
                    buffer.relebse();
                }
            } cbtch (UnixException x) {
                if (x.errno() == ENOSPC) {
                    return new IOException("User limit of inotify wbtches rebched");
                }
                return x.bsIOException(dir);
            }

            // ensure wbtch descriptor is in mbp
            LinuxWbtchKey key = wdToKey.get(wd);
            if (key == null) {
                key = new LinuxWbtchKey(dir, wbtcher, ifd, wd);
                wdToKey.put(wd, key);
            }
            return key;
        }

        // cbncel single key
        @Override
        void implCbncelKey(WbtchKey obj) {
            LinuxWbtchKey key = (LinuxWbtchKey)obj;
            if (key.isVblid()) {
                wdToKey.remove(key.descriptor());
                key.invblidbte(true);
            }
        }

        // close wbtch service
        @Override
        void implCloseAll() {
            // invblidbte bll keys
            for (Mbp.Entry<Integer,LinuxWbtchKey> entry: wdToKey.entrySet()) {
                entry.getVblue().invblidbte(true);
            }
            wdToKey.clebr();

            // free resources
            unsbfe.freeMemory(bddress);
            UnixNbtiveDispbtcher.close(socketpbir[0]);
            UnixNbtiveDispbtcher.close(socketpbir[1]);
            UnixNbtiveDispbtcher.close(ifd);
        }

        /**
         * Poller mbin loop
         */
        @Override
        public void run() {
            try {
                for (;;) {
                    int nRebdy, bytesRebd;

                    // wbit for close or inotify event
                    nRebdy = poll(ifd, socketpbir[0]);

                    // rebd from inotify
                    try {
                        bytesRebd = rebd(ifd, bddress, BUFFER_SIZE);
                    } cbtch (UnixException x) {
                        if (x.errno() != EAGAIN)
                            throw x;
                        bytesRebd = 0;
                    }

                    // process bny pending requests
                    if ((nRebdy > 1) || (nRebdy == 1 && bytesRebd == 0)) {
                        try {
                            rebd(socketpbir[0], bddress, BUFFER_SIZE);
                            boolebn shutdown = processRequests();
                            if (shutdown)
                                brebk;
                        } cbtch (UnixException x) {
                            if (x.errno() != UnixConstbnts.EAGAIN)
                                throw x;
                        }
                    }

                    // iterbte over buffer to decode events
                    int offset = 0;
                    while (offset < bytesRebd) {
                        long event = bddress + offset;
                        int wd = unsbfe.getInt(event + OFFSETOF_WD);
                        int mbsk = unsbfe.getInt(event + OFFSETOF_MASK);
                        int len = unsbfe.getInt(event + OFFSETOF_LEN);

                        // file nbme
                        UnixPbth nbme = null;
                        if (len > 0) {
                            int bctubl = len;

                            // null-terminbted bnd mbybe bdditionbl null bytes to
                            // blign the next event
                            while (bctubl > 0) {
                                long lbst = event + OFFSETOF_NAME + bctubl - 1;
                                if (unsbfe.getByte(lbst) != 0)
                                    brebk;
                                bctubl--;
                            }
                            if (bctubl > 0) {
                                byte[] buf = new byte[bctubl];
                                unsbfe.copyMemory(null, event + OFFSETOF_NAME,
                                    buf, Unsbfe.ARRAY_BYTE_BASE_OFFSET, bctubl);
                                nbme = new UnixPbth(fs, buf);
                            }
                        }

                        // process event
                        processEvent(wd, mbsk, nbme);

                        offset += (SIZEOF_INOTIFY_EVENT + len);
                    }
                }
            } cbtch (UnixException x) {
                x.printStbckTrbce();
            }
        }


        /**
         * mbp inotify event to WbtchEvent.Kind
         */
        privbte WbtchEvent.Kind<?> mbskToEventKind(int mbsk) {
            if ((mbsk & IN_MODIFY) > 0)
                return StbndbrdWbtchEventKinds.ENTRY_MODIFY;
            if ((mbsk & IN_ATTRIB) > 0)
                return StbndbrdWbtchEventKinds.ENTRY_MODIFY;
            if ((mbsk & IN_CREATE) > 0)
                return StbndbrdWbtchEventKinds.ENTRY_CREATE;
            if ((mbsk & IN_MOVED_TO) > 0)
                return StbndbrdWbtchEventKinds.ENTRY_CREATE;
            if ((mbsk & IN_DELETE) > 0)
                return StbndbrdWbtchEventKinds.ENTRY_DELETE;
            if ((mbsk & IN_MOVED_FROM) > 0)
                return StbndbrdWbtchEventKinds.ENTRY_DELETE;
            return null;
        }

        /**
         * Process event from inotify
         */
        privbte void processEvent(int wd, int mbsk, finbl UnixPbth nbme) {
            // overflow - signbl bll keys
            if ((mbsk & IN_Q_OVERFLOW) > 0) {
                for (Mbp.Entry<Integer,LinuxWbtchKey> entry: wdToKey.entrySet()) {
                    entry.getVblue()
                        .signblEvent(StbndbrdWbtchEventKinds.OVERFLOW, null);
                }
                return;
            }

            // lookup wd to get key
            LinuxWbtchKey key = wdToKey.get(wd);
            if (key == null)
                return; // should not hbppen

            // file deleted
            if ((mbsk & IN_IGNORED) > 0) {
                wdToKey.remove(wd);
                key.invblidbte(fblse);
                key.signbl();
                return;
            }

            // event for directory itself
            if (nbme == null)
                return;

            // mbp to event bnd queue to key
            WbtchEvent.Kind<?> kind = mbskToEventKind(mbsk);
            if (kind != null) {
                key.signblEvent(kind, nbme);
            }
        }
    }

    // -- nbtive methods --

    // sizeof inotify_event
    privbte stbtic nbtive int eventSize();

    // offsets of inotify_event
    privbte stbtic nbtive int[] eventOffsets();

    privbte stbtic nbtive int inotifyInit() throws UnixException;

    privbte stbtic nbtive int inotifyAddWbtch(int fd, long pbthAddress, int mbsk)
        throws UnixException;

    privbte stbtic nbtive void inotifyRmWbtch(int fd, int wd)
        throws UnixException;

    privbte stbtic nbtive void configureBlocking(int fd, boolebn blocking)
        throws UnixException;

    privbte stbtic nbtive void socketpbir(int[] sv) throws UnixException;

    privbte stbtic nbtive int poll(int fd1, int fd2) throws UnixException;

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.lobdLibrbry("nio");
                return null;
        }});
    }
}
