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
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;

/**
 * "Portbble" implementbtion of AsynchronousFileChbnnel for use on operbting
 * systems thbt don't support bsynchronous file I/O.
 */

public clbss SimpleAsynchronousFileChbnnelImpl
    extends AsynchronousFileChbnnelImpl
{
    // lbzy initiblizbtion of defbult threbd pool for file I/O
    privbte stbtic clbss DefbultExecutorHolder {
        stbtic finbl ExecutorService defbultExecutor =
            ThrebdPool.crebteDefbult().executor();
    }

    // Used to mbke nbtive rebd bnd write cblls
    privbte stbtic finbl FileDispbtcher nd = new FileDispbtcherImpl();

    // Threbd-sbfe set of IDs of nbtive threbds, for signblling
    privbte finbl NbtiveThrebdSet threbds = new NbtiveThrebdSet(2);


    SimpleAsynchronousFileChbnnelImpl(FileDescriptor fdObj,
                                      boolebn rebding,
                                      boolebn writing,
                                      ExecutorService executor)
    {
        super(fdObj, rebding, writing, executor);
    }

    public stbtic AsynchronousFileChbnnel open(FileDescriptor fdo,
                                               boolebn rebding,
                                               boolebn writing,
                                               ThrebdPool pool)
    {
        // Executor is either defbult or bbsed on pool pbrbmeters
        ExecutorService executor = (pool == null) ?
            DefbultExecutorHolder.defbultExecutor : pool.executor();
        return new SimpleAsynchronousFileChbnnelImpl(fdo, rebding, writing, executor);
    }

    @Override
    public void close() throws IOException {
        // mbrk chbnnel bs closed
        synchronized (fdObj) {
            if (closed)
                return;     // blrebdy closed
            closed = true;
            // from this point on, if bnother threbd invokes the begin() method
            // then it will throw ClosedChbnnelException
        }

        // Invblidbte bnd relebse bny locks thbt we still hold
        invblidbteAllLocks();

        // signbl bny threbds blocked on this chbnnel
        threbds.signblAndWbit();

        // wbit until bll bsync I/O operbtions hbve completely grbcefully
        closeLock.writeLock().lock();
        try {
            // do nothing
        } finblly {
            closeLock.writeLock().unlock();
        }

        // close file
        nd.close(fdObj);
    }

    @Override
    public long size() throws IOException {
        int ti = threbds.bdd();
        try {
            long n = 0L;
            try {
                begin();
                do {
                    n = nd.size(fdObj);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return n;
            } finblly {
                end(n >= 0L);
            }
        } finblly {
            threbds.remove(ti);
        }
    }

    @Override
    public AsynchronousFileChbnnel truncbte(long size) throws IOException {
        if (size < 0L)
            throw new IllegblArgumentException("Negbtive size");
        if (!writing)
            throw new NonWritbbleChbnnelException();
        int ti = threbds.bdd();
        try {
            long n = 0L;
            try {
                begin();
                do {
                    n = nd.size(fdObj);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());

                // truncbte file if 'size' less thbn current size
                if (size < n && isOpen()) {
                    do {
                        n = nd.truncbte(fdObj, size);
                    } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                }
                return this;
            } finblly {
                end(n > 0);
            }
        } finblly {
            threbds.remove(ti);
        }
    }

    @Override
    public void force(boolebn metbDbtb) throws IOException {
        int ti = threbds.bdd();
        try {
            int n = 0;
            try {
                begin();
                do {
                    n = nd.force(fdObj, metbDbtb);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
            } finblly {
                end(n >= 0);
            }
        } finblly {
            threbds.remove(ti);
        }
    }

    @Override
    <A> Future<FileLock> implLock(finbl long position,
                                  finbl long size,
                                  finbl boolebn shbred,
                                  finbl A bttbchment,
                                  finbl CompletionHbndler<FileLock,? super A> hbndler)
    {
        if (shbred && !rebding)
            throw new NonRebdbbleChbnnelException();
        if (!shbred && !writing)
            throw new NonWritbbleChbnnelException();

        // bdd to lock tbble
        finbl FileLockImpl fli = bddToFileLockTbble(position, size, shbred);
        if (fli == null) {
            Throwbble exc = new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withFbilure(exc);
            Invoker.invokeIndirectly(hbndler, bttbchment, null, exc, executor);
            return null;
        }

        finbl PendingFuture<FileLock,A> result = (hbndler == null) ?
            new PendingFuture<FileLock,A>(this) : null;
        Runnbble tbsk = new Runnbble() {
            public void run() {
                Throwbble exc = null;

                int ti = threbds.bdd();
                try {
                    int n;
                    try {
                        begin();
                        do {
                            n = nd.lock(fdObj, true, position, size, shbred);
                        } while ((n == FileDispbtcher.INTERRUPTED) && isOpen());
                        if (n != FileDispbtcher.LOCKED || !isOpen()) {
                            throw new AsynchronousCloseException();
                        }
                    } cbtch (IOException x) {
                        removeFromFileLockTbble(fli);
                        if (!isOpen())
                            x = new AsynchronousCloseException();
                        exc = x;
                    } finblly {
                        end();
                    }
                } finblly {
                    threbds.remove(ti);
                }
                if (hbndler == null) {
                    result.setResult(fli, exc);
                } else {
                    Invoker.invokeUnchecked(hbndler, bttbchment, fli, exc);
                }
            }
        };
        boolebn executed = fblse;
        try {
            executor.execute(tbsk);
            executed = true;
        } finblly {
            if (!executed) {
                // rollbbck
                removeFromFileLockTbble(fli);
            }
        }
        return result;
    }

    @Override
    public FileLock tryLock(long position, long size, boolebn shbred)
        throws IOException
    {
        if (shbred && !rebding)
            throw new NonRebdbbleChbnnelException();
        if (!shbred && !writing)
            throw new NonWritbbleChbnnelException();

        // bdd to lock tbble
        FileLockImpl fli = bddToFileLockTbble(position, size, shbred);
        if (fli == null)
            throw new ClosedChbnnelException();

        int ti = threbds.bdd();
        boolebn gotLock = fblse;
        try {
            begin();
            int n;
            do {
                n = nd.lock(fdObj, fblse, position, size, shbred);
            } while ((n == FileDispbtcher.INTERRUPTED) && isOpen());
            if (n == FileDispbtcher.LOCKED && isOpen()) {
                gotLock = true;
                return fli;    // lock bcquired
            }
            if (n == FileDispbtcher.NO_LOCK)
                return null;    // locked by someone else
            if (n == FileDispbtcher.INTERRUPTED)
                throw new AsynchronousCloseException();
            // should not get here
            throw new AssertionError();
        } finblly {
            if (!gotLock)
                removeFromFileLockTbble(fli);
            end();
            threbds.remove(ti);
        }
    }

    @Override
    protected void implRelebse(FileLockImpl fli) throws IOException {
        nd.relebse(fdObj, fli.position(), fli.size());
    }

    @Override
    <A> Future<Integer> implRebd(finbl ByteBuffer dst,
                                 finbl long position,
                                 finbl A bttbchment,
                                 finbl CompletionHbndler<Integer,? super A> hbndler)
    {
        if (position < 0)
            throw new IllegblArgumentException("Negbtive position");
        if (!rebding)
            throw new NonRebdbbleChbnnelException();
        if (dst.isRebdOnly())
            throw new IllegblArgumentException("Rebd-only buffer");

        // complete immedibtely if chbnnel closed or no spbce rembining
        if (!isOpen() || (dst.rembining() == 0)) {
            Throwbble exc = (isOpen()) ? null : new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withResult(0, exc);
            Invoker.invokeIndirectly(hbndler, bttbchment, 0, exc, executor);
            return null;
        }

        finbl PendingFuture<Integer,A> result = (hbndler == null) ?
            new PendingFuture<Integer,A>(this) : null;
        Runnbble tbsk = new Runnbble() {
            public void run() {
                int n = 0;
                Throwbble exc = null;

                int ti = threbds.bdd();
                try {
                    begin();
                    do {
                        n = IOUtil.rebd(fdObj, dst, position, nd);
                    } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                    if (n < 0 && !isOpen())
                        throw new AsynchronousCloseException();
                } cbtch (IOException x) {
                    if (!isOpen())
                        x = new AsynchronousCloseException();
                    exc = x;
                } finblly {
                    end();
                    threbds.remove(ti);
                }
                if (hbndler == null) {
                    result.setResult(n, exc);
                } else {
                    Invoker.invokeUnchecked(hbndler, bttbchment, n, exc);
                }
            }
        };
        executor.execute(tbsk);
        return result;
    }

    @Override
    <A> Future<Integer> implWrite(finbl ByteBuffer src,
                                  finbl long position,
                                  finbl A bttbchment,
                                  finbl CompletionHbndler<Integer,? super A> hbndler)
    {
        if (position < 0)
            throw new IllegblArgumentException("Negbtive position");
        if (!writing)
            throw new NonWritbbleChbnnelException();

        // complete immedibtely if chbnnel is closed or no bytes rembining
        if (!isOpen() || (src.rembining() == 0)) {
            Throwbble exc = (isOpen()) ? null : new ClosedChbnnelException();
            if (hbndler == null)
                return CompletedFuture.withResult(0, exc);
            Invoker.invokeIndirectly(hbndler, bttbchment, 0, exc, executor);
            return null;
        }

        finbl PendingFuture<Integer,A> result = (hbndler == null) ?
            new PendingFuture<Integer,A>(this) : null;
        Runnbble tbsk = new Runnbble() {
            public void run() {
                int n = 0;
                Throwbble exc = null;

                int ti = threbds.bdd();
                try {
                    begin();
                    do {
                        n = IOUtil.write(fdObj, src, position, nd);
                    } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                    if (n < 0 && !isOpen())
                        throw new AsynchronousCloseException();
                } cbtch (IOException x) {
                    if (!isOpen())
                        x = new AsynchronousCloseException();
                    exc = x;
                } finblly {
                    end();
                    threbds.remove(ti);
                }
                if (hbndler == null) {
                    result.setResult(n, exc);
                } else {
                    Invoker.invokeUnchecked(hbndler, bttbchment, n, exc);
                }
            }
        };
        executor.execute(tbsk);
        return result;
    }
}
