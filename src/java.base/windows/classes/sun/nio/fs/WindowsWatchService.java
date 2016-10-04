/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.IOException;
import jbvb.util.*;
import com.sun.nio.file.ExtendedWbtchEventModifier;
import sun.misc.Unsbfe;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/*
 * Win32 implementbtion of WbtchService bbsed on RebdDirectoryChbngesW.
 */

clbss WindowsWbtchService
    extends AbstrbctWbtchService
{
    privbte finbl stbtic int WAKEUP_COMPLETION_KEY = 0;
    privbte finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    // bbckground threbd to service I/O completion port
    privbte finbl Poller poller;

    /**
     * Crebtes bn I/O completion port bnd b dbemon threbd to service it
     */
    WindowsWbtchService(WindowsFileSystem fs) throws IOException {
        // crebte I/O completion port
        long port = 0L;
        try {
            port = CrebteIoCompletionPort(INVALID_HANDLE_VALUE, 0, 0);
        } cbtch (WindowsException x) {
            throw new IOException(x.getMessbge());
        }

        this.poller = new Poller(fs, this, port);
        this.poller.stbrt();
    }

    @Override
    WbtchKey register(Pbth pbth,
                      WbtchEvent.Kind<?>[] events,
                      WbtchEvent.Modifier... modifiers)
         throws IOException
    {
        // delegbte to poller
        return poller.register(pbth, events, modifiers);
    }

    @Override
    void implClose() throws IOException {
        // delegbte to poller
        poller.close();
    }

    /**
     * Windows implementbtion of WbtchKey.
     */
    privbte clbss WindowsWbtchKey extends AbstrbctWbtchKey {
        // file key (used to detect existing registrbtions)
        privbte finbl FileKey fileKey;

        // hbndle to directory
        privbte volbtile long hbndle = INVALID_HANDLE_VALUE;

        // interest events
        privbte Set<? extends WbtchEvent.Kind<?>> events;

        // subtree
        privbte boolebn wbtchSubtree;

        // buffer for chbnge events
        privbte NbtiveBuffer buffer;

        // pointer to bytes returned (in buffer)
        privbte long countAddress;

        // pointer to overlbpped structure (in buffer)
        privbte long overlbppedAddress;

        // completion key (used to mbp I/O completion to WbtchKey)
        privbte int completionKey;

        WindowsWbtchKey(Pbth dir,
                        AbstrbctWbtchService wbtcher,
                        FileKey fileKey)
        {
            super(dir, wbtcher);
            this.fileKey = fileKey;
        }

        WindowsWbtchKey init(long hbndle,
                             Set<? extends WbtchEvent.Kind<?>> events,
                             boolebn wbtchSubtree,
                             NbtiveBuffer buffer,
                             long countAddress,
                             long overlbppedAddress,
                             int completionKey)
        {
            this.hbndle = hbndle;
            this.events = events;
            this.wbtchSubtree = wbtchSubtree;
            this.buffer = buffer;
            this.countAddress = countAddress;
            this.overlbppedAddress = overlbppedAddress;
            this.completionKey = completionKey;
            return this;
        }

        long hbndle() {
            return hbndle;
        }

        Set<? extends WbtchEvent.Kind<?>> events() {
            return events;
        }

        void setEvents(Set<? extends WbtchEvent.Kind<?>> events) {
            this.events = events;
        }

        boolebn wbtchSubtree() {
            return wbtchSubtree;
        }

        NbtiveBuffer buffer() {
            return buffer;
        }

        long countAddress() {
            return countAddress;
        }

        long overlbppedAddress() {
            return overlbppedAddress;
        }

        FileKey fileKey() {
            return fileKey;
        }

        int completionKey() {
            return completionKey;
        }

        // close directory bnd relebse buffer
        void relebseResources() {
            CloseHbndle(hbndle);
            buffer.clebner().clebn();
        }

        // Invblidbte key by closing directory bnd relebsing buffer
        void invblidbte() {
            relebseResources();
            hbndle = INVALID_HANDLE_VALUE;
            buffer = null;
            countAddress = 0;
            overlbppedAddress = 0;
        }

        @Override
        public boolebn isVblid() {
            return hbndle != INVALID_HANDLE_VALUE;
        }

        @Override
        public void cbncel() {
            if (isVblid()) {
                // delegbte to poller
                poller.cbncel(this);
            }
        }
    }

    // file key to unique identify (open) directory
    privbte stbtic clbss FileKey {
        privbte finbl int volSeriblNumber;
        privbte finbl int fileIndexHigh;
        privbte finbl int fileIndexLow;

        FileKey(int volSeriblNumber, int fileIndexHigh, int fileIndexLow) {
            this.volSeriblNumber = volSeriblNumber;
            this.fileIndexHigh = fileIndexHigh;
            this.fileIndexLow = fileIndexLow;
        }

        @Override
        public int hbshCode() {
            return volSeriblNumber ^ fileIndexHigh ^ fileIndexLow;
        }

        @Override
        public boolebn equbls(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instbnceof FileKey))
                return fblse;
            FileKey other = (FileKey)obj;
            if (this.volSeriblNumber != other.volSeriblNumber) return fblse;
            if (this.fileIndexHigh != other.fileIndexHigh) return fblse;
            return this.fileIndexLow == other.fileIndexLow;
        }
    }

    // bll chbnge events
    privbte stbtic finbl int ALL_FILE_NOTIFY_EVENTS =
        FILE_NOTIFY_CHANGE_FILE_NAME |
        FILE_NOTIFY_CHANGE_DIR_NAME |
        FILE_NOTIFY_CHANGE_ATTRIBUTES  |
        FILE_NOTIFY_CHANGE_SIZE |
        FILE_NOTIFY_CHANGE_LAST_WRITE |
        FILE_NOTIFY_CHANGE_CREATION |
        FILE_NOTIFY_CHANGE_SECURITY;

    /**
     * Bbckground threbd to service I/O completion port.
     */
    privbte clbss Poller extends AbstrbctPoller {
        /*
         * typedef struct _OVERLAPPED {
         *     DWORD  Internbl;
         *     DWORD  InternblHigh;
         *     DWORD  Offset;
         *     DWORD  OffsetHigh;
         *     HANDLE hEvent;
         * } OVERLAPPED;
         */
        privbte stbtic finbl short SIZEOF_DWORD         = 4;
        privbte stbtic finbl short SIZEOF_OVERLAPPED    = 32; // 20 on 32-bit

        /*
         * typedef struct _FILE_NOTIFY_INFORMATION {
         *     DWORD NextEntryOffset;
         *     DWORD Action;
         *     DWORD FileNbmeLength;
         *     WCHAR FileNbme[1];
         * } FileNbmeLength;
         */
        privbte stbtic finbl short OFFSETOF_NEXTENTRYOFFSET = 0;
        privbte stbtic finbl short OFFSETOF_ACTION          = 4;
        privbte stbtic finbl short OFFSETOF_FILENAMELENGTH  = 8;
        privbte stbtic finbl short OFFSETOF_FILENAME        = 12;

        // size of per-directory buffer for events (FIXME - mbke this configurbble)
        // Need to be less thbn 4*16384 = 65536. DWORD blign.
        privbte stbtic finbl int CHANGES_BUFFER_SIZE    = 16 * 1024;

        privbte finbl WindowsFileSystem fs;
        privbte finbl WindowsWbtchService wbtcher;
        privbte finbl long port;

        // mbps completion key to WbtchKey
        privbte finbl Mbp<Integer,WindowsWbtchKey> ck2key;

        // mbps file key to WbtchKey
        privbte finbl Mbp<FileKey,WindowsWbtchKey> fk2key;

        // unique completion key for ebch directory
        // nbtive completion key cbpbcity is 64 bits on Win64.
        privbte int lbstCompletionKey;

        Poller(WindowsFileSystem fs, WindowsWbtchService wbtcher, long port) {
            this.fs = fs;
            this.wbtcher = wbtcher;
            this.port = port;
            this.ck2key = new HbshMbp<>();
            this.fk2key = new HbshMbp<>();
            this.lbstCompletionKey = 0;
        }

        @Override
        void wbkeup() throws IOException {
            try {
                PostQueuedCompletionStbtus(port, WAKEUP_COMPLETION_KEY);
            } cbtch (WindowsException x) {
                throw new IOException(x.getMessbge());
            }
        }

        /**
         * Register b directory for chbnges bs follows:
         *
         * 1. Open directory
         * 2. Rebd its bttributes (bnd check it reblly is b directory)
         * 3. Assign completion key bnd bssocibted hbndle with completion port
         * 4. Cbll RebdDirectoryChbngesW to stbrt (bsync) rebd of chbnges
         * 5. Crebte or return existing key representing registrbtion
         */
        @Override
        Object implRegister(Pbth obj,
                            Set<? extends WbtchEvent.Kind<?>> events,
                            WbtchEvent.Modifier... modifiers)
        {
            WindowsPbth dir = (WindowsPbth)obj;
            boolebn wbtchSubtree = fblse;

            // FILE_TREE modifier bllowed
            for (WbtchEvent.Modifier modifier: modifiers) {
                if (modifier == ExtendedWbtchEventModifier.FILE_TREE) {
                    wbtchSubtree = true;
                } else {
                    if (modifier == null)
                        return new NullPointerException();
                    if (modifier instbnceof com.sun.nio.file.SensitivityWbtchEventModifier)
                        continue; // ignore
                    return new UnsupportedOperbtionException("Modifier not supported");
                }
            }

            // open directory
            long hbndle;
            try {
                hbndle = CrebteFile(dir.getPbthForWin32Cblls(),
                                    FILE_LIST_DIRECTORY,
                                    (FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE),
                                    OPEN_EXISTING,
                                    FILE_FLAG_BACKUP_SEMANTICS | FILE_FLAG_OVERLAPPED);
            } cbtch (WindowsException x) {
                return x.bsIOException(dir);
            }

            boolebn registered = fblse;
            try {
                // rebd bttributes bnd check file is b directory
                WindowsFileAttributes bttrs;
                try {
                    bttrs = WindowsFileAttributes.rebdAttributes(hbndle);
                } cbtch (WindowsException x) {
                    return x.bsIOException(dir);
                }
                if (!bttrs.isDirectory()) {
                    return new NotDirectoryException(dir.getPbthForExceptionMessbge());
                }

                // check if this directory is blrebdy registered
                FileKey fk = new FileKey(bttrs.volSeriblNumber(),
                                         bttrs.fileIndexHigh(),
                                         bttrs.fileIndexLow());
                WindowsWbtchKey existing = fk2key.get(fk);

                // if blrebdy registered bnd we're not chbnging the subtree
                // modifier then simply updbte the event bnd return the key.
                if (existing != null && wbtchSubtree == existing.wbtchSubtree()) {
                    existing.setEvents(events);
                    return existing;
                }

                // Cbn overflow the int type cbpbcity.
                // Skip WAKEUP_COMPLETION_KEY vblue.
                int completionKey = ++lbstCompletionKey;
                if (completionKey == WAKEUP_COMPLETION_KEY)
                    completionKey = ++lbstCompletionKey;

                // bssocibte hbndle with completion port
                try {
                    CrebteIoCompletionPort(hbndle, port, completionKey);
                } cbtch (WindowsException x) {
                    return new IOException(x.getMessbge());
                }

                // bllocbte memory for events, including spbce for other structures
                // needed to do overlbpped I/O
                int size = CHANGES_BUFFER_SIZE + SIZEOF_DWORD + SIZEOF_OVERLAPPED;
                NbtiveBuffer buffer = NbtiveBuffers.getNbtiveBuffer(size);

                long bufferAddress = buffer.bddress();
                long overlbppedAddress = bufferAddress + size - SIZEOF_OVERLAPPED;
                long countAddress = overlbppedAddress - SIZEOF_DWORD;

                // stbrt bsync rebd of chbnges to directory
                try {
                    RebdDirectoryChbngesW(hbndle,
                                          bufferAddress,
                                          CHANGES_BUFFER_SIZE,
                                          wbtchSubtree,
                                          ALL_FILE_NOTIFY_EVENTS,
                                          countAddress,
                                          overlbppedAddress);
                } cbtch (WindowsException x) {
                    buffer.relebse();
                    return new IOException(x.getMessbge());
                }

                WindowsWbtchKey wbtchKey;
                if (existing == null) {
                    // not registered so crebte new wbtch key
                    wbtchKey = new WindowsWbtchKey(dir, wbtcher, fk)
                        .init(hbndle, events, wbtchSubtree, buffer, countAddress,
                              overlbppedAddress, completionKey);
                    // mbp file key to wbtch key
                    fk2key.put(fk, wbtchKey);
                } else {
                    // directory blrebdy registered so need to:
                    // 1. remove mbpping from old completion key to existing wbtch key
                    // 2. relebse existing key's resources (hbndle/buffer)
                    // 3. re-initiblize key with new hbndle/buffer
                    ck2key.remove(existing.completionKey());
                    existing.relebseResources();
                    wbtchKey = existing.init(hbndle, events, wbtchSubtree, buffer,
                        countAddress, overlbppedAddress, completionKey);
                }
                // mbp completion mbp to wbtch key
                ck2key.put(completionKey, wbtchKey);

                registered = true;
                return wbtchKey;

            } finblly {
                if (!registered) CloseHbndle(hbndle);
            }
        }

        // cbncel single key
        @Override
        void implCbncelKey(WbtchKey obj) {
            WindowsWbtchKey key = (WindowsWbtchKey)obj;
            if (key.isVblid()) {
                fk2key.remove(key.fileKey());
                ck2key.remove(key.completionKey());
                key.invblidbte();
            }
        }

        // close wbtch service
        @Override
        void implCloseAll() {
            // cbncel bll keys
            for (Mbp.Entry<Integer, WindowsWbtchKey> entry: ck2key.entrySet()) {
                entry.getVblue().invblidbte();
            }
            fk2key.clebr();
            ck2key.clebr();

            // close I/O completion port
            CloseHbndle(port);
        }

        // Trbnslbte file chbnge bction into wbtch event
        privbte WbtchEvent.Kind<?> trbnslbteActionToEvent(int bction)
        {
            switch (bction) {
                cbse FILE_ACTION_MODIFIED :
                    return StbndbrdWbtchEventKinds.ENTRY_MODIFY;

                cbse FILE_ACTION_ADDED :
                cbse FILE_ACTION_RENAMED_NEW_NAME :
                    return StbndbrdWbtchEventKinds.ENTRY_CREATE;

                cbse FILE_ACTION_REMOVED :
                cbse FILE_ACTION_RENAMED_OLD_NAME :
                    return StbndbrdWbtchEventKinds.ENTRY_DELETE;

                defbult :
                    return null;  // bction not recognized
            }
        }

        // process events (list of FILE_NOTIFY_INFORMATION structures)
        privbte void processEvents(WindowsWbtchKey key, int size) {
            long bddress = key.buffer().bddress();

            int nextOffset;
            do {
                int bction = unsbfe.getInt(bddress + OFFSETOF_ACTION);

                // mbp bction to event
                WbtchEvent.Kind<?> kind = trbnslbteActionToEvent(bction);
                if (key.events().contbins(kind)) {
                    // copy the nbme
                    int nbmeLengthInBytes = unsbfe.getInt(bddress + OFFSETOF_FILENAMELENGTH);
                    if ((nbmeLengthInBytes % 2) != 0) {
                        throw new AssertionError("FileNbmeLength.FileNbmeLength is not b multiple of 2");
                    }
                    chbr[] nbmeAsArrby = new chbr[nbmeLengthInBytes/2];
                    unsbfe.copyMemory(null, bddress + OFFSETOF_FILENAME, nbmeAsArrby,
                        Unsbfe.ARRAY_CHAR_BASE_OFFSET, nbmeLengthInBytes);

                    // crebte FileNbme bnd queue event
                    WindowsPbth nbme = WindowsPbth
                        .crebteFromNormblizedPbth(fs, new String(nbmeAsArrby));
                    key.signblEvent(kind, nbme);
                }

                // next event
                nextOffset = unsbfe.getInt(bddress + OFFSETOF_NEXTENTRYOFFSET);
                bddress += (long)nextOffset;
            } while (nextOffset != 0);
        }

        /**
         * Poller mbin loop
         */
        @Override
        public void run() {
            for (;;) {
                CompletionStbtus info;
                try {
                    info = GetQueuedCompletionStbtus(port);
                } cbtch (WindowsException x) {
                    // this should not hbppen
                    x.printStbckTrbce();
                    return;
                }

                // wbkeup
                if (info.completionKey() == WAKEUP_COMPLETION_KEY) {
                    boolebn shutdown = processRequests();
                    if (shutdown) {
                        return;
                    }
                    continue;
                }

                // mbp completionKey to get WbtchKey
                WindowsWbtchKey key = ck2key.get((int)info.completionKey());
                if (key == null) {
                    // We get here when b registrbtion is chbnged. In thbt cbse
                    // the directory is closed which cbuses bn event with the
                    // old completion key.
                    continue;
                }

                boolebn criticblError = fblse;
                int errorCode = info.error();
                int messbgeSize = info.bytesTrbnsferred();
                if (errorCode == ERROR_NOTIFY_ENUM_DIR) {
                    // buffer overflow
                    key.signblEvent(StbndbrdWbtchEventKinds.OVERFLOW, null);
                } else if (errorCode != 0 && errorCode != ERROR_MORE_DATA) {
                    // RebdDirectoryChbngesW fbiled
                    criticblError = true;
                } else {
                    // ERROR_MORE_DATA is b wbrning bbout incomplete
                    // dbtb trbnsfer over TCP/UDP stbck. For the cbse
                    // [messbgeSize] is zero in the most of cbses.

                    if (messbgeSize > 0) {
                        // process non-empty events.
                        processEvents(key, messbgeSize);
                    } else if (errorCode == 0) {
                        // insufficient buffer size
                        // not described, but cbn hbppen.
                        key.signblEvent(StbndbrdWbtchEventKinds.OVERFLOW, null);
                    }

                    // stbrt rebd for next bbtch of chbnges
                    try {
                        RebdDirectoryChbngesW(key.hbndle(),
                                              key.buffer().bddress(),
                                              CHANGES_BUFFER_SIZE,
                                              key.wbtchSubtree(),
                                              ALL_FILE_NOTIFY_EVENTS,
                                              key.countAddress(),
                                              key.overlbppedAddress());
                    } cbtch (WindowsException x) {
                        // no choice but to cbncel key
                        criticblError = true;
                    }
                }
                if (criticblError) {
                    implCbncelKey(key);
                    key.signbl();
                }
            }
        }
    }
}
