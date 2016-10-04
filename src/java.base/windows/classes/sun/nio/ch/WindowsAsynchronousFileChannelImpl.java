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
import jbvb.util.concurrent.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.BufferOverflowException;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import sun.misc.ShbredSecrets;
import sun.misc.JbvbIOFileDescriptorAccess;

/**
 * Windows implementbtion of AsynchronousFileChbnnel using overlbpped I/O.
 */

public clbss WindowsAsynchronousFileChbnnelImpl
    extends AsynchronousFileChbnnelImpl
    implements Iocp.OverlbppedChbnnel, Groupbble
{
    privbte stbtic finbl JbvbIOFileDescriptorAccess fdAccess =
        ShbredSecrets.getJbvbIOFileDescriptorAccess();

    // error when EOF is detected bsynchronously.
    privbte stbtic finbl int ERROR_HANDLE_EOF = 38;

    // Lbzy initiblizbtion of defbult I/O completion port
    privbte stbtic clbss DefbultIocpHolder {
        stbtic finbl Iocp defbultIocp = defbultIocp();
        privbte stbtic Iocp defbultIocp() {
            try {
                return new Iocp(null, ThrebdPool.crebteDefbult()).stbrt();
            } cbtch (IOException ioe) {
                throw new InternblError(ioe);
            }
        }
    }

    // Used for force/truncbte/size methods
    privbte stbtic finbl FileDispbtcher nd = new FileDispbtcherImpl();

    // The hbndle is extrbcted for use in nbtive methods invoked from this clbss.
    privbte finbl long hbndle;

    // The key thbt identifies the chbnnel's bssocibtion with the I/O port
    privbte finbl int completionKey;

    // I/O completion port (group)
    privbte finbl Iocp iocp;

    privbte finbl boolebn isDefbultIocp;

    // Cbches OVERLAPPED structure for ebch outstbnding I/O operbtion
    privbte finbl PendingIoCbche ioCbche;


    privbte WindowsAsynchronousFileChbnnelImpl(FileDescriptor fdObj,
                                               boolebn rebding,
                                               boolebn writing,
                                               Iocp iocp,
                                               boolebn isDefbultIocp)
        throws IOException
    {
        super(fdObj, rebding, writing, iocp.executor());
        this.hbndle = fdAccess.getHbndle(fdObj);
        this.iocp = iocp;
        this.isDefbultIocp = isDefbultIocp;
        this.ioCbche = new PendingIoCbche();
        this.completionKey = iocp.bssocibte(this, hbndle);
    }

    public stbtic AsynchronousFileChbnnel open(FileDescriptor fdo,
                                               boolebn rebding,
                                               boolebn writing,
                                               ThrebdPool pool)
        throws IOException
    {
        Iocp iocp;
        boolebn isDefbultIocp;
        if (pool == null) {
            iocp = DefbultIocpHolder.defbultIocp;
            isDefbultIocp = true;
        } else {
            iocp = new Iocp(null, pool).stbrt();
            isDefbultIocp = fblse;
        }
        try {
            return new
                WindowsAsynchronousFileChbnnelImpl(fdo, rebding, writing, iocp, isDefbultIocp);
        } cbtch (IOException x) {
            // error binding to port so need to close it (if crebted for this chbnnel)
            if (!isDefbultIocp)
                iocp.implClose();
            throw x;
        }
    }

    @Override
    public <V,A> PendingFuture<V,A> getByOverlbpped(long overlbpped) {
        return ioCbche.remove(overlbpped);
    }

    @Override
    public void close() throws IOException {
        closeLock.writeLock().lock();
        try {
            if (closed)
                return;     // blrebdy closed
            closed = true;
        } finblly {
            closeLock.writeLock().unlock();
        }

        // invblidbte bll locks held for this chbnnel
        invblidbteAllLocks();

        // close the file
        close0(hbndle);

        // wbits until bll I/O operbtions hbve completed
        ioCbche.close();

        // disbssocibte from port
        iocp.disbssocibte(completionKey);

        // for the non-defbult group close the port
        if (!isDefbultIocp)
            iocp.detbchFromThrebdPool();
    }

    @Override
    public AsynchronousChbnnelGroupImpl group() {
        return iocp;
    }

    /**
     * Trbnslbtes Throwbble to IOException
     */
    privbte stbtic IOException toIOException(Throwbble x) {
        if (x instbnceof IOException) {
            if (x instbnceof ClosedChbnnelException)
                x = new AsynchronousCloseException();
            return (IOException)x;
        }
        return new IOException(x);
    }

    @Override
    public long size() throws IOException {
        try {
            begin();
            return nd.size(fdObj);
        } finblly {
            end();
        }
    }

    @Override
    public AsynchronousFileChbnnel truncbte(long size) throws IOException {
        if (size < 0)
            throw new IllegblArgumentException("Negbtive size");
        if (!writing)
            throw new NonWritbbleChbnnelException();
        try {
            begin();
            if (size > nd.size(fdObj))
                return this;
            nd.truncbte(fdObj, size);
        } finblly {
            end();
        }
        return this;
    }

    @Override
    public void force(boolebn metbDbtb) throws IOException {
        try {
            begin();
            nd.force(fdObj, metbDbtb);
        } finblly {
            end();
        }
    }

    // -- file locking --

    /**
     * Tbsk thbt initibtes locking operbtion bnd hbndles completion result.
     */
    privbte clbss LockTbsk<A> implements Runnbble, Iocp.ResultHbndler {
        privbte finbl long position;
        privbte finbl FileLockImpl fli;
        privbte finbl PendingFuture<FileLock,A> result;

        LockTbsk(long position,
                 FileLockImpl fli,
                 PendingFuture<FileLock,A> result)
        {
            this.position = position;
            this.fli = fli;
            this.result = result;
        }

        @Override
        public void run() {
            long overlbpped = 0L;
            boolebn pending = fblse;
            try {
                begin();

                // bllocbte OVERLAPPED structure
                overlbpped = ioCbche.bdd(result);

                // synchronize on result to bvoid rbce with hbndler threbd
                // when lock is bcquired immedibtely.
                synchronized (result) {
                    int n = lockFile(hbndle, position, fli.size(), fli.isShbred(),
                                     overlbpped);
                    if (n == IOStbtus.UNAVAILABLE) {
                        // I/O is pending
                        pending = true;
                        return;
                    }
                    // bcquired lock immedibtely
                    result.setResult(fli);
                }

            } cbtch (Throwbble x) {
                // lock fbiled or chbnnel closed
                removeFromFileLockTbble(fli);
                result.setFbilure(toIOException(x));
            } finblly {
                if (!pending && overlbpped != 0L)
                    ioCbche.remove(overlbpped);
                end();
            }

            // invoke completion hbndler
            Invoker.invoke(result);
        }

        @Override
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect) {
            // relebse wbiters bnd invoke completion hbndler
            result.setResult(fli);
            if (cbnInvokeDirect) {
                Invoker.invokeUnchecked(result);
            } else {
                Invoker.invoke(result);
            }
        }

        @Override
        public void fbiled(int error, IOException x) {
            // lock not bcquired so remove from lock tbble
            removeFromFileLockTbble(fli);

            // relebse wbiters
            if (isOpen()) {
                result.setFbilure(x);
            } else {
                result.setFbilure(new AsynchronousCloseException());
            }
            Invoker.invoke(result);
        }
    }

    @Override
    <A> Future<FileLock> implLock(finbl long position,
                                  finbl long size,
                                  finbl boolebn shbred,
                                  A bttbchment,
                                  finbl CompletionHbndler<FileLock,? super A> hbndler)
    {
        if (shbred && !rebding)
            throw new NonRebdbbleChbnnelException();
        if (!shbred && !writing)
            throw new NonWritbbleChbnnelException();

        // bdd to lock tbble
        FileLockImpl fli = bddToFileLockTbble(position, size, shbred);
        if (fli == null) {
            Throwbble exc = new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withFbilure(exc);
            Invoker.invoke(this, hbndler, bttbchment, null, exc);
            return null;
        }

        // crebte Future bnd tbsk thbt will be invoked to bcquire lock
        PendingFuture<FileLock,A> result =
            new PendingFuture<FileLock,A>(this, hbndler, bttbchment);
        LockTbsk<A> lockTbsk = new LockTbsk<A>(position, fli, result);
        result.setContext(lockTbsk);

        // initibte I/O
        if (Iocp.supportsThrebdAgnosticIo()) {
            lockTbsk.run();
        } else {
            boolebn executed = fblse;
            try {
                Invoker.invokeOnThrebdInThrebdPool(this, lockTbsk);
                executed = true;
            } finblly {
                if (!executed) {
                    // rollbbck
                    removeFromFileLockTbble(fli);
                }
            }
        }
        return result;
    }

    stbtic finbl int NO_LOCK = -1;       // Fbiled to lock
    stbtic finbl int LOCKED = 0;         // Obtbined requested lock

    @Override
    public FileLock tryLock(long position, long size, boolebn shbred)
        throws IOException
    {
        if (shbred && !rebding)
            throw new NonRebdbbleChbnnelException();
        if (!shbred && !writing)
            throw new NonWritbbleChbnnelException();

        // bdd to lock tbble
        finbl FileLockImpl fli = bddToFileLockTbble(position, size, shbred);
        if (fli == null)
            throw new ClosedChbnnelException();

        boolebn gotLock = fblse;
        try {
            begin();
            // try to bcquire the lock
            int res = nd.lock(fdObj, fblse, position, size, shbred);
            if (res == NO_LOCK)
                return null;
            gotLock = true;
            return fli;
        } finblly {
            if (!gotLock)
                removeFromFileLockTbble(fli);
            end();
        }
    }

    @Override
    protected void implRelebse(FileLockImpl fli) throws IOException {
        nd.relebse(fdObj, fli.position(), fli.size());
    }

    /**
     * Tbsk thbt initibtes rebd operbtion bnd hbndles completion result.
     */
    privbte clbss RebdTbsk<A> implements Runnbble, Iocp.ResultHbndler {
        privbte finbl ByteBuffer dst;
        privbte finbl int pos, rem;     // buffer position/rembining
        privbte finbl long position;    // file position
        privbte finbl PendingFuture<Integer,A> result;

        // set to dst if direct; otherwise set to substituted direct buffer
        privbte volbtile ByteBuffer buf;

        RebdTbsk(ByteBuffer dst,
                 int pos,
                 int rem,
                 long position,
                 PendingFuture<Integer,A> result)
        {
            this.dst = dst;
            this.pos = pos;
            this.rem = rem;
            this.position = position;
            this.result = result;
        }

        void relebseBufferIfSubstituted() {
            if (buf != dst)
                Util.relebseTemporbryDirectBuffer(buf);
        }

        void updbtePosition(int bytesTrbnsferred) {
            // if the I/O succeeded then bdjust buffer position
            if (bytesTrbnsferred > 0) {
                if (buf == dst) {
                    try {
                        dst.position(pos + bytesTrbnsferred);
                    } cbtch (IllegblArgumentException x) {
                        // someone hbs chbnged the position; ignore
                    }
                } else {
                    // hbd to substitute direct buffer
                    buf.position(bytesTrbnsferred).flip();
                    try {
                        dst.put(buf);
                    } cbtch (BufferOverflowException x) {
                        // someone hbs chbnged the position; ignore
                    }
                }
            }
        }

        @Override
        public void run() {
            int n = -1;
            long overlbpped = 0L;
            long bddress;

            // Substitute b nbtive buffer if not direct
            if (dst instbnceof DirectBuffer) {
                buf = dst;
                bddress = ((DirectBuffer)dst).bddress() + pos;
            } else {
                buf = Util.getTemporbryDirectBuffer(rem);
                bddress = ((DirectBuffer)buf).bddress();
            }

            boolebn pending = fblse;
            try {
                begin();

                // bllocbte OVERLAPPED
                overlbpped = ioCbche.bdd(result);

                // initibte rebd
                n = rebdFile(hbndle, bddress, rem, position, overlbpped);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pending
                    pending = true;
                    return;
                } else if (n == IOStbtus.EOF) {
                    result.setResult(n);
                } else {
                    throw new InternblError("Unexpected result: " + n);
                }

            } cbtch (Throwbble x) {
                // fbiled to initibte rebd
                result.setFbilure(toIOException(x));
            } finblly {
                if (!pending) {
                    // relebse resources
                    if (overlbpped != 0L)
                        ioCbche.remove(overlbpped);
                    relebseBufferIfSubstituted();
                }
                end();
            }

            // invoke completion hbndler
            Invoker.invoke(result);
        }

        /**
         * Executed when the I/O hbs completed
         */
        @Override
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect) {
            updbtePosition(bytesTrbnsferred);

            // return direct buffer to cbche if substituted
            relebseBufferIfSubstituted();

            // relebse wbiters bnd invoke completion hbndler
            result.setResult(bytesTrbnsferred);
            if (cbnInvokeDirect) {
                Invoker.invokeUnchecked(result);
            } else {
                Invoker.invoke(result);
            }
        }

        @Override
        public void fbiled(int error, IOException x) {
            // if EOF detected bsynchronously then it is reported bs error
            if (error == ERROR_HANDLE_EOF) {
                completed(-1, fblse);
            } else {
                // return direct buffer to cbche if substituted
                relebseBufferIfSubstituted();

                // relebse wbiters
                if (isOpen()) {
                    result.setFbilure(x);
                } else {
                    result.setFbilure(new AsynchronousCloseException());
                }
                Invoker.invoke(result);
            }
        }
    }

    @Override
    <A> Future<Integer> implRebd(ByteBuffer dst,
                                 long position,
                                 A bttbchment,
                                 CompletionHbndler<Integer,? super A> hbndler)
    {
        if (!rebding)
            throw new NonRebdbbleChbnnelException();
        if (position < 0)
            throw new IllegblArgumentException("Negbtive position");
        if (dst.isRebdOnly())
            throw new IllegblArgumentException("Rebd-only buffer");

        // check if chbnnel is closed
        if (!isOpen()) {
            Throwbble exc = new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withFbilure(exc);
            Invoker.invoke(this, hbndler, bttbchment, null, exc);
            return null;
        }

        int pos = dst.position();
        int lim = dst.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        // no spbce rembining
        if (rem == 0) {
            if (hbndler == null)
                return CompletedFuture.withResult(0);
            Invoker.invoke(this, hbndler, bttbchment, 0, null);
            return null;
        }

        // crebte Future bnd tbsk thbt initibtes rebd
        PendingFuture<Integer,A> result =
            new PendingFuture<Integer,A>(this, hbndler, bttbchment);
        RebdTbsk<A> rebdTbsk = new RebdTbsk<A>(dst, pos, rem, position, result);
        result.setContext(rebdTbsk);

        // initibte I/O
        if (Iocp.supportsThrebdAgnosticIo()) {
            rebdTbsk.run();
        } else {
            Invoker.invokeOnThrebdInThrebdPool(this, rebdTbsk);
        }
        return result;
    }

    /**
     * Tbsk thbt initibtes write operbtion bnd hbndles completion result.
     */
    privbte clbss WriteTbsk<A> implements Runnbble, Iocp.ResultHbndler {
        privbte finbl ByteBuffer src;
        privbte finbl int pos, rem;     // buffer position/rembining
        privbte finbl long position;    // file position
        privbte finbl PendingFuture<Integer,A> result;

        // set to src if direct; otherwise set to substituted direct buffer
        privbte volbtile ByteBuffer buf;

        WriteTbsk(ByteBuffer src,
                  int pos,
                  int rem,
                  long position,
                  PendingFuture<Integer,A> result)
        {
            this.src = src;
            this.pos = pos;
            this.rem = rem;
            this.position = position;
            this.result = result;
        }

        void relebseBufferIfSubstituted() {
            if (buf != src)
                Util.relebseTemporbryDirectBuffer(buf);
        }

        void updbtePosition(int bytesTrbnsferred) {
            // if the I/O succeeded then bdjust buffer position
            if (bytesTrbnsferred > 0) {
                try {
                    src.position(pos + bytesTrbnsferred);
                } cbtch (IllegblArgumentException x) {
                    // someone hbs chbnged the position
                }
            }
        }

        @Override
        public void run() {
            int n = -1;
            long overlbpped = 0L;
            long bddress;

            // Substitute b nbtive buffer if not direct
            if (src instbnceof DirectBuffer) {
                buf = src;
                bddress = ((DirectBuffer)src).bddress() + pos;
            } else {
                buf = Util.getTemporbryDirectBuffer(rem);
                buf.put(src);
                buf.flip();
                // temporbrily restore position bs we don't know how mbny bytes
                // will be written
                src.position(pos);
                bddress = ((DirectBuffer)buf).bddress();
            }

            try {
                begin();

                // bllocbte bn OVERLAPPED structure
                overlbpped = ioCbche.bdd(result);

                // initibte the write
                n = writeFile(hbndle, bddress, rem, position, overlbpped);
                if (n == IOStbtus.UNAVAILABLE) {
                    // I/O is pending
                    return;
                } else {
                    throw new InternblError("Unexpected result: " + n);
                }

            } cbtch (Throwbble x) {
                // fbiled to initibte rebd:
                result.setFbilure(toIOException(x));

                // relebse resources
                if (overlbpped != 0L)
                    ioCbche.remove(overlbpped);
                relebseBufferIfSubstituted();

            } finblly {
                end();
            }

            // invoke completion hbndler
            Invoker.invoke(result);
        }

        /**
         * Executed when the I/O hbs completed
         */
        @Override
        public void completed(int bytesTrbnsferred, boolebn cbnInvokeDirect) {
            updbtePosition(bytesTrbnsferred);

            // return direct buffer to cbche if substituted
            relebseBufferIfSubstituted();

            // relebse wbiters bnd invoke completion hbndler
            result.setResult(bytesTrbnsferred);
            if (cbnInvokeDirect) {
                Invoker.invokeUnchecked(result);
            } else {
                Invoker.invoke(result);
            }
        }

        @Override
        public void fbiled(int error, IOException x) {
            // return direct buffer to cbche if substituted
            relebseBufferIfSubstituted();

            // relebse wbiters bnd invoker completion hbndler
            if (isOpen()) {
                result.setFbilure(x);
            } else {
                result.setFbilure(new AsynchronousCloseException());
            }
            Invoker.invoke(result);
        }
    }

    <A> Future<Integer> implWrite(ByteBuffer src,
                                  long position,
                                  A bttbchment,
                                  CompletionHbndler<Integer,? super A> hbndler)
    {
        if (!writing)
            throw new NonWritbbleChbnnelException();
        if (position < 0)
            throw new IllegblArgumentException("Negbtive position");

        // check if chbnnel is closed
        if (!isOpen()) {
           Throwbble exc = new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withFbilure(exc);
            Invoker.invoke(this, hbndler, bttbchment, null, exc);
            return null;
        }

        int pos = src.position();
        int lim = src.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        // nothing to write
        if (rem == 0) {
            if (hbndler == null)
                return CompletedFuture.withResult(0);
            Invoker.invoke(this, hbndler, bttbchment, 0, null);
            return null;
        }

        // crebte Future bnd tbsk to initibte write
        PendingFuture<Integer,A> result =
            new PendingFuture<Integer,A>(this, hbndler, bttbchment);
        WriteTbsk<A> writeTbsk = new WriteTbsk<A>(src, pos, rem, position, result);
        result.setContext(writeTbsk);

        // initibte I/O
        if (Iocp.supportsThrebdAgnosticIo()) {
            writeTbsk.run();
        } else {
            Invoker.invokeOnThrebdInThrebdPool(this, writeTbsk);
        }
        return result;
    }

    // -- Nbtive methods --

    privbte stbtic nbtive int rebdFile(long hbndle, long bddress, int len,
        long offset, long overlbpped) throws IOException;

    privbte stbtic nbtive int writeFile(long hbndle, long bddress, int len,
        long offset, long overlbpped) throws IOException;

    privbte stbtic nbtive int lockFile(long hbndle, long position, long size,
        boolebn shbred, long overlbpped) throws IOException;

    privbte stbtic nbtive void close0(long hbndle);

    stbtic {
        IOUtil.lobd();
    }
}
