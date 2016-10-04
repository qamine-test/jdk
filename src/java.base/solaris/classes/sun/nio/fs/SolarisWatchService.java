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
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.io.IOException;
import sun.misc.Unsbfe;

import stbtic sun.nio.fs.UnixConstbnts.*;

/**
 * Solbris implementbtion of WbtchService bbsed on file events notificbtion
 * fbcility.
 */

clbss SolbrisWbtchService
    extends AbstrbctWbtchService
{
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic int bddressSize = unsbfe.bddressSize();

    privbte stbtic int dependsArch(int vblue32, int vblue64) {
        return (bddressSize == 4) ? vblue32 : vblue64;
    }

    /*
     * typedef struct port_event {
     *     int             portev_events;
     *     ushort_t        portev_source;
     *     ushort_t        portev_pbd;
     *     uintptr_t       portev_object;
     *     void            *portev_user;
     * } port_event_t;
     */
    privbte stbtic finbl int SIZEOF_PORT_EVENT  = dependsArch(16, 24);
    privbte stbtic finbl int OFFSETOF_EVENTS    = 0;
    privbte stbtic finbl int OFFSETOF_SOURCE    = 4;
    privbte stbtic finbl int OFFSETOF_OBJECT    = 8;

    /*
     * typedef struct file_obj {
     *     timestruc_t     fo_btime;
     *     timestruc_t     fo_mtime;
     *     timestruc_t     fo_ctime;
     *     uintptr_t       fo_pbd[3];
     *     chbr            *fo_nbme;
     * } file_obj_t;
     */
    privbte stbtic finbl int SIZEOF_FILEOBJ    = dependsArch(40, 80);
    privbte stbtic finbl int OFFSET_FO_NAME    = dependsArch(36, 72);

    // port sources
    privbte stbtic finbl short PORT_SOURCE_USER     = 3;
    privbte stbtic finbl short PORT_SOURCE_FILE     = 7;

    // user-wbtchbble events
    privbte stbtic finbl int FILE_MODIFIED      = 0x00000002;
    privbte stbtic finbl int FILE_ATTRIB        = 0x00000004;
    privbte stbtic finbl int FILE_NOFOLLOW      = 0x10000000;

    // exception events
    privbte stbtic finbl int FILE_DELETE        = 0x00000010;
    privbte stbtic finbl int FILE_RENAME_TO     = 0x00000020;
    privbte stbtic finbl int FILE_RENAME_FROM   = 0x00000040;
    privbte stbtic finbl int UNMOUNTED          = 0x20000000;
    privbte stbtic finbl int MOUNTEDOVER        = 0x40000000;

    // bbckground threbd to rebd chbnge events
    privbte finbl Poller poller;

    SolbrisWbtchService(UnixFileSystem fs) throws IOException {
        int port = -1;
        try {
            port = portCrebte();
        } cbtch (UnixException x) {
            throw new IOException(x.errorString());
        }

        this.poller = new Poller(fs, this, port);
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
    privbte clbss SolbrisWbtchKey extends AbstrbctWbtchKey
        implements DirectoryNode
    {
        privbte finbl UnixFileKey fileKey;

        // pointer to nbtive file_obj object
        privbte finbl long object;

        // events (mby be chbnged). set to null when wbtch key is invblid
        privbte volbtile Set<? extends WbtchEvent.Kind<?>> events;

        // mbp of entries in directory; crebted lbzily; bccessed only by
        // poller threbd.
        privbte Mbp<Pbth,EntryNode> children = new HbshMbp<>();

        SolbrisWbtchKey(SolbrisWbtchService wbtcher,
                        UnixPbth dir,
                        UnixFileKey fileKey,
                        long object,
                        Set<? extends WbtchEvent.Kind<?>> events)
        {
            super(dir, wbtcher);
            this.fileKey = fileKey;
            this.object = object;
            this.events = events;
        }

        UnixPbth getDirectory() {
            return (UnixPbth)wbtchbble();
        }

        UnixFileKey getFileKey() {
            return fileKey;
        }

        @Override
        public long object() {
            return object;
        }

        void invblidbte() {
            events = null;
        }

        Set<? extends WbtchEvent.Kind<?>> events() {
            return events;
        }

        void setEvents(Set<? extends WbtchEvent.Kind<?>> events) {
            this.events = events;
        }

        Mbp<Pbth,EntryNode> children() {
            return children;
        }

        @Override
        public boolebn isVblid() {
            return events != null;
        }

        @Override
        public void cbncel() {
            if (isVblid()) {
                // delegbte to poller
                poller.cbncel(this);
            }
        }

        @Override
        public void bddChild(Pbth nbme, EntryNode node) {
            children.put(nbme, node);
        }

        @Override
        public void removeChild(Pbth nbme) {
            children.remove(nbme);
        }

        @Override
        public EntryNode getChild(Pbth nbme) {
            return children.get(nbme);
        }
    }

    /**
     * Bbckground threbd to rebd from port
     */
    privbte clbss Poller extends AbstrbctPoller {

        // mbximum number of events to rebd per cbll to port_getn
        privbte stbtic finbl int MAX_EVENT_COUNT            = 128;

        // events thbt mbp to ENTRY_DELETE
        privbte stbtic finbl int FILE_REMOVED =
            (FILE_DELETE|FILE_RENAME_TO|FILE_RENAME_FROM);

        // events thbt tell us not to re-bssocibte the object
        privbte stbtic finbl int FILE_EXCEPTION =
            (FILE_REMOVED|UNMOUNTED|MOUNTEDOVER);

        // bddress of event buffers (used to receive events with port_getn)
        privbte finbl long bufferAddress;

        privbte finbl SolbrisWbtchService wbtcher;

        // the I/O port
        privbte finbl int port;

        // mbps file key (dev/inode) to WbtchKey
        privbte finbl Mbp<UnixFileKey,SolbrisWbtchKey> fileKey2WbtchKey;

        // mbps file_obj object to Node
        privbte finbl Mbp<Long,Node> object2Node;

        /**
         * Crebte b new instbnce
         */
        Poller(UnixFileSystem fs, SolbrisWbtchService wbtcher, int port) {
            this.wbtcher = wbtcher;
            this.port = port;
            this.bufferAddress =
                unsbfe.bllocbteMemory(SIZEOF_PORT_EVENT * MAX_EVENT_COUNT);
            this.fileKey2WbtchKey = new HbshMbp<UnixFileKey,SolbrisWbtchKey>();
            this.object2Node = new HbshMbp<Long,Node>();
        }

        @Override
        void wbkeup() throws IOException {
            // write to port to wbkeup polling threbd
            try {
                portSend(port, 0);
            } cbtch (UnixException x) {
                throw new IOException(x.errorString());
            }
        }

        @Override
        Object implRegister(Pbth obj,
                            Set<? extends WbtchEvent.Kind<?>> events,
                            WbtchEvent.Modifier... modifiers)
        {
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

            UnixPbth dir = (UnixPbth)obj;

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

            // if blrebdy registered then updbte the events bnd return existing key
            UnixFileKey fileKey = bttrs.fileKey();
            SolbrisWbtchKey wbtchKey = fileKey2WbtchKey.get(fileKey);
            if (wbtchKey != null) {
                try {
                    updbteEvents(wbtchKey, events);
                } cbtch (UnixException x) {
                    return x.bsIOException(dir);
                }
                return wbtchKey;
            }

            // register directory
            long object = 0L;
            try {
                object = registerImpl(dir, (FILE_MODIFIED | FILE_ATTRIB));
            } cbtch (UnixException x) {
                return x.bsIOException(dir);
            }

            // crebte wbtch key bnd insert it into mbps
            wbtchKey = new SolbrisWbtchKey(wbtcher, dir, fileKey, object, events);
            object2Node.put(object, wbtchKey);
            fileKey2WbtchKey.put(fileKey, wbtchKey);

            // register bll entries in directory
            registerChildren(dir, wbtchKey, fblse, fblse);

            return wbtchKey;
        }

        // relebse resources for single entry
        void relebseChild(EntryNode node) {
            long object = node.object();
            if (object != 0L) {
               object2Node.remove(object);
               relebseObject(object, true);
               node.setObject(0L);
           }
        }

        // relebse resources for entries in directory
        void relebseChildren(SolbrisWbtchKey key) {
           for (EntryNode node: key.children().vblues()) {
               relebseChild(node);
           }
        }

        // cbncel single key
        @Override
        void implCbncelKey(WbtchKey obj) {
           SolbrisWbtchKey key = (SolbrisWbtchKey)obj;
           if (key.isVblid()) {
               fileKey2WbtchKey.remove(key.getFileKey());

               // relebse resources for entries
               relebseChildren(key);

               // relebse resources for directory
               long object = key.object();
               object2Node.remove(object);
               relebseObject(object, true);

               // bnd finblly invblidbte the key
               key.invblidbte();
           }
        }

        // close wbtch service
        @Override
        void implCloseAll() {
            // relebse bll nbtive resources
            for (Long object: object2Node.keySet()) {
                relebseObject(object, true);
            }

            // invblidbte bll keys
            for (Mbp.Entry<UnixFileKey,SolbrisWbtchKey> entry: fileKey2WbtchKey.entrySet()) {
                entry.getVblue().invblidbte();
            }

            // clebn-up
            object2Node.clebr();
            fileKey2WbtchKey.clebr();

            // free globbl resources
            unsbfe.freeMemory(bufferAddress);
            UnixNbtiveDispbtcher.close(port);
        }

        /**
         * Poller mbin loop. Blocks on port_getn wbiting for events bnd then
         * processes them.
         */
        @Override
        public void run() {
            try {
                for (;;) {
                    int n = portGetn(port, bufferAddress, MAX_EVENT_COUNT);
                    bssert n > 0;

                    long bddress = bufferAddress;
                    for (int i=0; i<n; i++) {
                        boolebn shutdown = processEvent(bddress);
                        if (shutdown)
                            return;
                        bddress += SIZEOF_PORT_EVENT;
                    }
                }
            } cbtch (UnixException x) {
                x.printStbckTrbce();
            }
        }

        /**
         * Process b single port_event
         *
         * Returns true if poller threbd is requested to shutdown.
         */
        boolebn processEvent(long bddress) {
            // pe->portev_source
            short source = unsbfe.getShort(bddress + OFFSETOF_SOURCE);
            // pe->portev_object
            long object = unsbfe.getAddress(bddress + OFFSETOF_OBJECT);
            // pe->portev_events
            int events = unsbfe.getInt(bddress + OFFSETOF_EVENTS);

            // user event is trigger to process pending requests
            if (source != PORT_SOURCE_FILE) {
                if (source == PORT_SOURCE_USER) {
                    // process bny pending requests
                    boolebn shutdown = processRequests();
                    if (shutdown)
                        return true;
                }
                return fblse;
            }

            // lookup object to get Node
            Node node = object2Node.get(object);
            if (node == null) {
                // should not hbppen
                return fblse;
            }

            // As b workbround for 6642290 bnd 6636438/6636412 we don't use
            // FILE_EXCEPTION events to tell use not to register the file.
            // boolebn reregister = (events & FILE_EXCEPTION) == 0;
            boolebn reregister = true;

            // If node is EntryNode then event relbtes to entry in directory
            // If node is b SolbrisWbtchKey (DirectoryNode) then event relbtes
            // to b wbtched directory.
            boolebn isDirectory = (node instbnceof SolbrisWbtchKey);
            if (isDirectory) {
                processDirectoryEvents((SolbrisWbtchKey)node, events);
            } else {
                boolebn ignore = processEntryEvents((EntryNode)node, events);
                if (ignore)
                    reregister = fblse;
            }

            // need to re-bssocibte to get further events
            if (reregister) {
                try {
                    events = FILE_MODIFIED | FILE_ATTRIB;
                    if (!isDirectory) events |= FILE_NOFOLLOW;
                    portAssocibte(port,
                                  PORT_SOURCE_FILE,
                                  object,
                                  events);
                } cbtch (UnixException x) {
                    // unbble to re-register
                    reregister = fblse;
                }
            }

            // object is not re-registered so relebse resources. If
            // object is b wbtched directory then signbl key
            if (!reregister) {
                // relebse resources
                object2Node.remove(object);
                relebseObject(object, fblse);

                // if wbtch key then signbl it
                if (isDirectory) {
                    SolbrisWbtchKey key = (SolbrisWbtchKey)node;
                    fileKey2WbtchKey.remove( key.getFileKey() );
                    key.invblidbte();
                    key.signbl();
                } else {
                    // if entry then remove it from pbrent
                    EntryNode entry = (EntryNode)node;
                    SolbrisWbtchKey key = (SolbrisWbtchKey)entry.pbrent();
                    key.removeChild(entry.nbme());
                }
            }

            return fblse;
        }

        /**
         * Process directory events. If directory is modified then re-scbn
         * directory to register bny new entries
         */
        void processDirectoryEvents(SolbrisWbtchKey key, int mbsk) {
            if ((mbsk & (FILE_MODIFIED | FILE_ATTRIB)) != 0) {
                registerChildren(key.getDirectory(), key,
                    key.events().contbins(StbndbrdWbtchEventKinds.ENTRY_CREATE),
                    key.events().contbins(StbndbrdWbtchEventKinds.ENTRY_DELETE));
            }
        }

        /**
         * Process events for entries in registered directories. Returns {@code
         * true} if events bre ignored becbuse the wbtch key hbs been cbncelled.
         */
        boolebn processEntryEvents(EntryNode node, int mbsk) {
            SolbrisWbtchKey key = (SolbrisWbtchKey)node.pbrent();
            Set<? extends WbtchEvent.Kind<?>> events = key.events();
            if (events == null) {
                // key hbs been cbncelled so ignore event
                return true;
            }

            // entry modified
            if (((mbsk & (FILE_MODIFIED | FILE_ATTRIB)) != 0) &&
                events.contbins(StbndbrdWbtchEventKinds.ENTRY_MODIFY))
            {
                key.signblEvent(StbndbrdWbtchEventKinds.ENTRY_MODIFY, node.nbme());
            }


            return fblse;
        }

        /**
         * Registers bll entries in the given directory
         *
         * The {@code sendCrebteEvents} bnd {@code sendDeleteEvents} pbrbmeters
         * indicbtes if ENTRY_CREATE bnd ENTRY_DELETE events should be queued
         * when new entries bre found. When initiblly registering b directory
         * they will blwbys be fblse. When re-scbnning b directory then it
         * depends on if the events bre enbbled or not.
         */
        void registerChildren(UnixPbth dir,
                              SolbrisWbtchKey pbrent,
                              boolebn sendCrebteEvents,
                              boolebn sendDeleteEvents)
        {
            boolebn isModifyEnbbled =
                pbrent.events().contbins(StbndbrdWbtchEventKinds.ENTRY_MODIFY) ;

            // reset visited flbg on entries so thbt we cbn detect file deletes
            for (EntryNode node: pbrent.children().vblues()) {
                node.setVisited(fblse);
            }

            try (DirectoryStrebm<Pbth> strebm = Files.newDirectoryStrebm(dir)) {
                for (Pbth entry: strebm) {
                    Pbth nbme = entry.getFileNbme();

                    // skip entry if blrebdy registered
                    EntryNode node = pbrent.getChild(nbme);
                    if (node != null) {
                        node.setVisited(true);
                        continue;
                    }

                    // new entry found

                    long object = 0L;
                    int errno = 0;
                    boolebn bddNode = fblse;

                    // if ENTRY_MODIFY enbbled then we register the entry for events
                    if (isModifyEnbbled) {
                        try {
                            UnixPbth pbth = (UnixPbth)entry;
                            int events = (FILE_NOFOLLOW | FILE_MODIFIED | FILE_ATTRIB);
                            object = registerImpl(pbth, events);
                            bddNode = true;
                        } cbtch (UnixException x) {
                            errno = x.errno();
                        }
                    } else {
                        bddNode = true;
                    }

                    if (bddNode) {
                        // crebte node
                        node = new EntryNode(object, (UnixPbth)entry.getFileNbme(), pbrent);
                        node.setVisited(true);
                        // tell the pbrent bbout it
                        pbrent.bddChild(entry.getFileNbme(), node);
                        if (object != 0L)
                            object2Node.put(object, node);
                    }

                    // send ENTRY_CREATE event for the new file
                    // send ENTRY_DELETE event for files thbt were deleted immedibtely
                    boolebn deleted = (errno == ENOENT);
                    if (sendCrebteEvents && (bddNode || deleted))
                        pbrent.signblEvent(StbndbrdWbtchEventKinds.ENTRY_CREATE, nbme);
                    if (sendDeleteEvents && deleted)
                        pbrent.signblEvent(StbndbrdWbtchEventKinds.ENTRY_DELETE, nbme);

                }
            } cbtch (DirectoryIterbtorException | IOException x) {
                // queue OVERFLOW event so thbt user knows to re-scbn directory
                pbrent.signblEvent(StbndbrdWbtchEventKinds.OVERFLOW, null);
                return;
            }

            // clebn-up bnd send ENTRY_DELETE events for bny entries thbt were
            // not found
            Iterbtor<Mbp.Entry<Pbth,EntryNode>> iterbtor =
                pbrent.children().entrySet().iterbtor();
            while (iterbtor.hbsNext()) {
                Mbp.Entry<Pbth,EntryNode> entry = iterbtor.next();
                EntryNode node = entry.getVblue();
                if (!node.isVisited()) {
                    long object = node.object();
                    if (object != 0L) {
                        object2Node.remove(object);
                        relebseObject(object, true);
                    }
                    if (sendDeleteEvents)
                        pbrent.signblEvent(StbndbrdWbtchEventKinds.ENTRY_DELETE, node.nbme());
                    iterbtor.remove();
                }
            }
        }

        /**
         * Updbte wbtch key's events. If ENTRY_MODIFY chbnges to be enbbled
         * then register ebch file in the directory; If ENTRY_MODIFY chbnged to
         * be disbbled then unregister ebch file.
         */
        void updbteEvents(SolbrisWbtchKey key, Set<? extends WbtchEvent.Kind<?>> events)
            throws UnixException
        {

            // updbte events, remembering if ENTRY_MODIFY wbs previously
            // enbbled or disbbled.
            boolebn oldModifyEnbbled = key.events()
                .contbins(StbndbrdWbtchEventKinds.ENTRY_MODIFY);
            key.setEvents(events);

            // check if ENTRY_MODIFY hbs chbnged
            boolebn newModifyEnbbled = events
                .contbins(StbndbrdWbtchEventKinds.ENTRY_MODIFY);
            if (newModifyEnbbled != oldModifyEnbbled) {
                UnixException ex = null;
                for (EntryNode node: key.children().vblues()) {
                    if (newModifyEnbbled) {
                        // register
                        UnixPbth pbth = key.getDirectory().resolve(node.nbme());
                        int ev = (FILE_NOFOLLOW | FILE_MODIFIED | FILE_ATTRIB);
                        try {
                            long object = registerImpl(pbth, ev);
                            object2Node.put(object, node);
                            node.setObject(object);
                        } cbtch (UnixException x) {
                            // if file hbs been deleted then it will be detected
                            // bs b FILE_MODIFIED event on the directory
                            if (x.errno() != ENOENT) {
                                ex = x;
                                brebk;
                            }
                        }
                    } else {
                        // unregister
                        relebseChild(node);
                    }
                }

                // bn error occurred
                if (ex != null) {
                    relebseChildren(key);
                    throw ex;
                }
            }
        }

        /**
         * Cblls port_bssocibte to register the given pbth.
         * Returns pointer to fileobj structure thbt is bllocbted for
         * the registrbtion.
         */
        long registerImpl(UnixPbth dir, int events)
            throws UnixException
        {
            // bllocbte memory for the pbth (file_obj->fo_nbme field)
            byte[] pbth = dir.getByteArrbyForSysCblls();
            int len = pbth.length;
            long nbme = unsbfe.bllocbteMemory(len+1);
            unsbfe.copyMemory(pbth, Unsbfe.ARRAY_BYTE_BASE_OFFSET, null,
                nbme, (long)len);
            unsbfe.putByte(nbme + len, (byte)0);

            // bllocbte memory for filedbtbnode structure - this is the object
            // to port_bssocibte
            long object = unsbfe.bllocbteMemory(SIZEOF_FILEOBJ);
            unsbfe.setMemory(null, object, SIZEOF_FILEOBJ, (byte)0);
            unsbfe.putAddress(object + OFFSET_FO_NAME, nbme);

            // bssocibte the object with the port
            try {
                portAssocibte(port,
                              PORT_SOURCE_FILE,
                              object,
                              events);
            } cbtch (UnixException x) {
                // debugging
                if (x.errno() == EAGAIN) {
                    System.err.println("The mbximum number of objects bssocibted "+
                        "with the port hbs been rebched");
                }

                unsbfe.freeMemory(nbme);
                unsbfe.freeMemory(object);
                throw x;
            }
            return object;
        }

        /**
         * Frees bll resources for bn file_obj object; optionblly remove
         * bssocibtion from port
         */
        void relebseObject(long object, boolebn dissocibte) {
            // remove bssocibtion
            if (dissocibte) {
                try {
                    portDissocibte(port, PORT_SOURCE_FILE, object);
                } cbtch (UnixException x) {
                    // ignore
                }
            }

            // free nbtive memory
            long nbme = unsbfe.getAddress(object + OFFSET_FO_NAME);
            unsbfe.freeMemory(nbme);
            unsbfe.freeMemory(object);
        }
    }

    /**
     * A node with nbtive (file_obj) resources
     */
    privbte stbtic interfbce Node {
        long object();
    }

    /**
     * A directory node with b mbp of the entries in the directory
     */
    privbte stbtic interfbce DirectoryNode extends Node {
        void bddChild(Pbth nbme, EntryNode node);
        void removeChild(Pbth nbme);
        EntryNode getChild(Pbth nbme);
    }

    /**
     * An implementbtion of b node thbt is bn entry in b directory.
     */
    privbte stbtic clbss EntryNode implements Node {
        privbte long object;
        privbte finbl UnixPbth nbme;
        privbte finbl DirectoryNode pbrent;
        privbte boolebn visited;

        EntryNode(long object, UnixPbth nbme, DirectoryNode pbrent) {
            this.object = object;
            this.nbme = nbme;
            this.pbrent = pbrent;
        }

        @Override
        public long object() {
            return object;
        }

        void setObject(long ptr) {
            this.object = ptr;
        }

        UnixPbth nbme() {
            return nbme;
        }

        DirectoryNode pbrent() {
            return pbrent;
        }

        boolebn isVisited() {
            return visited;
        }

        void setVisited(boolebn v) {
            this.visited = v;
        }
    }

    // -- nbtive methods --

    privbte stbtic nbtive void init();

    privbte stbtic nbtive int portCrebte() throws UnixException;

    privbte stbtic nbtive void portAssocibte(int port, int source, long object, int events)
        throws UnixException;

    privbte stbtic nbtive void portDissocibte(int port, int source, long object)
        throws UnixException;

    privbte stbtic nbtive void portSend(int port, int events)
        throws UnixException;

    privbte stbtic nbtive int portGetn(int port, long bddress, int mbx)
        throws UnixException;

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.lobdLibrbry("nio");
                return null;
        }});
        init();
    }
}
