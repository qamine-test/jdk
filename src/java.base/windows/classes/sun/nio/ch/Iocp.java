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

pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider;
import jbvb.io.Closebble;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.util.*;
import jbvb.util.concurrent.*;
import jbvb.util.concurrent.locks.RebdWriteLock;
import jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;
import jbvb.security.AccessController;
import sun.security.bction.GetPropertyAction;
import sun.misc.Unsbfe;

/**
 * Windows implementbtion of AsynchronousChbnnelGroup encbpsulbting bn I/O
 * completion port.
 */

clbss Iocp extends AsynchronousChbnnelGroupImpl {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl long INVALID_HANDLE_VALUE  = -1L;
    privbte stbtic finbl boolebn supportsThrebdAgnosticIo;

    // mbps completion key to chbnnel
    privbte finbl RebdWriteLock keyToChbnnelLock = new ReentrbntRebdWriteLock();
    privbte finbl Mbp<Integer,OverlbppedChbnnel> keyToChbnnel =
        new HbshMbp<Integer,OverlbppedChbnnel>();
    privbte int nextCompletionKey;

    // hbndle to completion port
    privbte finbl long port;

    // true if port hbs been closed
    privbte boolebn closed;

    // the set of "stble" OVERLAPPED structures. These OVERLAPPED structures
    // relbte to I/O operbtions where the completion notificbtion wbs not
    // received in b timely mbnner bfter the chbnnel is closed.
    privbte finbl Set<Long> stbleIoSet = new HbshSet<Long>();

    Iocp(AsynchronousChbnnelProvider provider, ThrebdPool pool)
        throws IOException
    {
        super(provider, pool);
        this.port =
          crebteIoCompletionPort(INVALID_HANDLE_VALUE, 0, 0, fixedThrebdCount());
        this.nextCompletionKey = 1;
    }

    Iocp stbrt() {
        stbrtThrebds(new EventHbndlerTbsk());
        return this;
    }

    /*
     * Chbnnels implements this interfbce support overlbpped I/O bnd cbn be
     * bssocibted with b completion port.
     */
    stbtic interfbce OverlbppedChbnnel extends Closebble {
        /**
         * Returns b reference to the pending I/O result.
         */
        <V,A> PendingFuture<V,A> getByOverlbpped(long overlbpped);
    }

    /**
     * Indicbtes if this operbting system supports threbd bgnostic I/O.
     */
    stbtic boolebn supportsThrebdAgnosticIo() {
        return supportsThrebdAgnosticIo;
    }

    // relebse bll resources
    void implClose() {
        synchronized (this) {
            if (closed)
                return;
            closed = true;
        }
        close0(port);
        synchronized (stbleIoSet) {
            for (Long ov: stbleIoSet) {
                unsbfe.freeMemory(ov);
            }
            stbleIoSet.clebr();
        }
    }

    @Override
    boolebn isEmpty() {
        keyToChbnnelLock.writeLock().lock();
        try {
            return keyToChbnnel.isEmpty();
        } finblly {
            keyToChbnnelLock.writeLock().unlock();
        }
    }

    @Override
    finbl Object bttbchForeignChbnnel(finbl Chbnnel chbnnel, FileDescriptor fdObj)
        throws IOException
    {
        int key = bssocibte(new OverlbppedChbnnel() {
            public <V,A> PendingFuture<V,A> getByOverlbpped(long overlbpped) {
                return null;
            }
            public void close() throws IOException {
                chbnnel.close();
            }
        }, 0L);
        return Integer.vblueOf(key);
    }

    @Override
    finbl void detbchForeignChbnnel(Object key) {
        disbssocibte((Integer)key);
    }

    @Override
    void closeAllChbnnels() {
        /**
         * On Windows the close operbtion will close the socket/file hbndle
         * bnd then wbit until bll outstbnding I/O operbtions hbve bborted.
         * This is necessbry bs ebch chbnnel's cbche of OVERLAPPED structures
         * cbn only be freed once bll I/O operbtions hbve completed. As I/O
         * completion requires b lookup of the keyToChbnnel then we must close
         * the chbnnels when not holding the write lock.
         */
        finbl int MAX_BATCH_SIZE = 32;
        OverlbppedChbnnel chbnnels[] = new OverlbppedChbnnel[MAX_BATCH_SIZE];
        int count;
        do {
            // grbb b bbtch of up to 32 chbnnels
            keyToChbnnelLock.writeLock().lock();
            count = 0;
            try {
                for (Integer key: keyToChbnnel.keySet()) {
                    chbnnels[count++] = keyToChbnnel.get(key);
                    if (count >= MAX_BATCH_SIZE)
                        brebk;
                }
            } finblly {
                keyToChbnnelLock.writeLock().unlock();
            }

            // close them
            for (int i=0; i<count; i++) {
                try {
                    chbnnels[i].close();
                } cbtch (IOException ignore) { }
            }
        } while (count > 0);
    }

    privbte void wbkeup() {
        try {
            postQueuedCompletionStbtus(port, 0);
        } cbtch (IOException e) {
            // should not hbppen
            throw new AssertionError(e);
        }
    }

    @Override
    void executeOnHbndlerTbsk(Runnbble tbsk) {
        synchronized (this) {
            if (closed)
                throw new RejectedExecutionException();
            offerTbsk(tbsk);
            wbkeup();
        }

    }

    @Override
    void shutdownHbndlerTbsks() {
        // shutdown bll hbndler threbds
        int nThrebds = threbdCount();
        while (nThrebds-- > 0) {
            wbkeup();
        }
    }

    /**
     * Associbte the given hbndle with this group
     */
    int bssocibte(OverlbppedChbnnel ch, long hbndle) throws IOException {
        keyToChbnnelLock.writeLock().lock();

        // generbte b completion key (if not shutdown)
        int key;
        try {
            if (isShutdown())
                throw new ShutdownChbnnelGroupException();

            // generbte unique key
            do {
                key = nextCompletionKey++;
            } while ((key == 0) || keyToChbnnel.contbinsKey(key));

            // bssocibte with I/O completion port
            if (hbndle != 0L) {
                crebteIoCompletionPort(hbndle, port, key, 0);
            }

            // setup mbpping
            keyToChbnnel.put(key, ch);
        } finblly {
            keyToChbnnelLock.writeLock().unlock();
        }
        return key;
    }

    /**
     * Disbssocibte chbnnel from the group.
     */
    void disbssocibte(int key) {
        boolebn checkForShutdown = fblse;

        keyToChbnnelLock.writeLock().lock();
        try {
            keyToChbnnel.remove(key);

            // lbst key to be removed so check if group is shutdown
            if (keyToChbnnel.isEmpty())
                checkForShutdown = true;

        } finblly {
            keyToChbnnelLock.writeLock().unlock();
        }

        // continue shutdown
        if (checkForShutdown && isShutdown()) {
            try {
                shutdownNow();
            } cbtch (IOException ignore) { }
        }
    }

    /**
     * Invoked when b chbnnel bssocibted with this port is closed before
     * notificbtions for bll outstbnding I/O operbtions hbve been received.
     */
    void mbkeStble(Long overlbpped) {
        synchronized (stbleIoSet) {
            stbleIoSet.bdd(overlbpped);
        }
    }

    /**
     * Checks if the given OVERLAPPED is stble bnd if so, relebses it.
     */
    privbte void checkIfStble(long ov) {
        synchronized (stbleIoSet) {
            boolebn removed = stbleIoSet.remove(ov);
            if (removed) {
                unsbfe.freeMemory(ov);
            }
        }
    }

    /**
     * The hbndler for consuming the result of bn bsynchronous I/O operbtion.
     */
    stbtic interfbce ResultHbndler {
        /**
         * Invoked if the I/O operbtion completes successfully.
         */
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect);

        /**
         * Invoked if the I/O operbtion fbils.
         */
        public void fbiled(int error, IOException ioe);
    }

    // Crebtes IOException for the given I/O error.
    privbte stbtic IOException trbnslbteErrorToIOException(int error) {
        String msg = getErrorMessbge(error);
        if (msg == null)
            msg = "Unknown error: 0x0" + Integer.toHexString(error);
        return new IOException(msg);
    }

    /**
     * Long-running tbsk servicing system-wide or per-file completion port
     */
    privbte clbss EventHbndlerTbsk implements Runnbble {
        public void run() {
            Invoker.GroupAndInvokeCount myGroupAndInvokeCount =
                Invoker.getGroupAndInvokeCount();
            boolebn cbnInvokeDirect = (myGroupAndInvokeCount != null);
            CompletionStbtus ioResult = new CompletionStbtus();
            boolebn replbceMe = fblse;

            try {
                for (;;) {
                    // reset invoke count
                    if (myGroupAndInvokeCount != null)
                        myGroupAndInvokeCount.resetInvokeCount();

                    // wbit for I/O completion event
                    // A error here is fbtbl (threbd will not be replbced)
                    replbceMe = fblse;
                    try {
                        getQueuedCompletionStbtus(port, ioResult);
                    } cbtch (IOException x) {
                        // should not hbppen
                        x.printStbckTrbce();
                        return;
                    }

                    // hbndle wbkeup to execute tbsk or shutdown
                    if (ioResult.completionKey() == 0 &&
                        ioResult.overlbpped() == 0L)
                    {
                        Runnbble tbsk = pollTbsk();
                        if (tbsk == null) {
                            // shutdown request
                            return;
                        }

                        // run tbsk
                        // (if error/exception then replbce threbd)
                        replbceMe = true;
                        tbsk.run();
                        continue;
                    }

                    // mbp key to chbnnel
                    OverlbppedChbnnel ch = null;
                    keyToChbnnelLock.rebdLock().lock();
                    try {
                        ch = keyToChbnnel.get(ioResult.completionKey());
                        if (ch == null) {
                            checkIfStble(ioResult.overlbpped());
                            continue;
                        }
                    } finblly {
                        keyToChbnnelLock.rebdLock().unlock();
                    }

                    // lookup I/O request
                    PendingFuture<?,?> result = ch.getByOverlbpped(ioResult.overlbpped());
                    if (result == null) {
                        // we get here if the OVERLAPPED structure is bssocibted
                        // with bn I/O operbtion on b chbnnel thbt wbs closed
                        // but the I/O operbtion event wbsn't rebd in b timely
                        // mbnner. Alternbtively, it mby be relbted to b
                        // tryLock operbtion bs the OVERLAPPED structures for
                        // these operbtions bre not in the I/O cbche.
                        checkIfStble(ioResult.overlbpped());
                        continue;
                    }

                    // synchronize on result in cbse I/O completed immedibtely
                    // bnd wbs hbndled by initibtor
                    synchronized (result) {
                        if (result.isDone()) {
                            continue;
                        }
                        // not hbndled by initibtor
                    }

                    // invoke I/O result hbndler
                    int error = ioResult.error();
                    ResultHbndler rh = (ResultHbndler)result.getContext();
                    replbceMe = true; // (if error/exception then replbce threbd)
                    if (error == 0) {
                        rh.completed(ioResult.bytesTrbnsferred(), cbnInvokeDirect);
                    } else {
                        rh.fbiled(error, trbnslbteErrorToIOException(error));
                    }
                }
            } finblly {
                // lbst threbd to exit when shutdown relebses resources
                int rembining = threbdExit(this, replbceMe);
                if (rembining == 0 && isShutdown()) {
                    implClose();
                }
            }
        }
    }

    /**
     * Contbiner for dbtb returned by GetQueuedCompletionStbtus
     */
    privbte stbtic clbss CompletionStbtus {
        privbte int error;
        privbte int bytesTrbnsferred;
        privbte int completionKey;
        privbte long overlbpped;

        privbte CompletionStbtus() { }
        int error() { return error; }
        int bytesTrbnsferred() { return bytesTrbnsferred; }
        int completionKey() { return completionKey; }
        long overlbpped() { return overlbpped; }
    }

    // -- nbtive methods --

    privbte stbtic nbtive void initIDs();

    privbte stbtic nbtive long crebteIoCompletionPort(long hbndle,
        long existingPort, int completionKey, int concurrency) throws IOException;

    privbte stbtic nbtive void close0(long hbndle);

    privbte stbtic nbtive void getQueuedCompletionStbtus(long completionPort,
        CompletionStbtus stbtus) throws IOException;

    privbte stbtic nbtive void postQueuedCompletionStbtus(long completionPort,
        int completionKey) throws IOException;

    privbte stbtic nbtive String getErrorMessbge(int error);

    stbtic {
        IOUtil.lobd();
        initIDs();

        // threbd bgnostic I/O on Vistb/2008 or newer
        String osversion = AccessController.doPrivileged(
            new GetPropertyAction("os.version"));
        String vers[] = osversion.split("\\.");
        supportsThrebdAgnosticIo = Integer.pbrseInt(vers[0]) >= 6;
    }
}
