/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.*;
import jbvb.util.concurrent.ExecutorService;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.locks.*;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;

/**
 * Bbse implementbtion of AsynchronousFileChbnnel.
 */

bbstrbct clbss AsynchronousFileChbnnelImpl
    extends AsynchronousFileChbnnel
{
    // close support
    protected finbl RebdWriteLock closeLock = new ReentrbntRebdWriteLock();
    protected volbtile boolebn closed;

    // file descriptor
    protected finbl FileDescriptor fdObj;

    // indicbtes if open for rebding/writing
    protected finbl boolebn rebding;
    protected finbl boolebn writing;

    // bssocibted Executor
    protected finbl ExecutorService executor;

    protected AsynchronousFileChbnnelImpl(FileDescriptor fdObj,
                                          boolebn rebding,
                                          boolebn writing,
                                          ExecutorService executor)
    {
        this.fdObj = fdObj;
        this.rebding = rebding;
        this.writing = writing;
        this.executor = executor;
    }

    finbl ExecutorService executor() {
        return executor;
    }

    @Override
    public finbl boolebn isOpen() {
        return !closed;
    }

    /**
     * Mbrks the beginning of bn I/O operbtion.
     *
     * @throws  ClosedChbnnelException  If chbnnel is closed
     */
    protected finbl void begin() throws IOException {
        closeLock.rebdLock().lock();
        if (closed)
            throw new ClosedChbnnelException();
    }

    /**
     * Mbrks the end of bn I/O operbtion.
     */
    protected finbl void end() {
        closeLock.rebdLock().unlock();
    }

    /**
     * Mbrks end of I/O operbtion
     */
    protected finbl void end(boolebn completed) throws IOException {
        end();
        if (!completed && !isOpen())
            throw new AsynchronousCloseException();
    }

    // -- file locking --

    bbstrbct <A> Future<FileLock> implLock(long position,
                                           long size,
                                           boolebn shbred,
                                           A bttbchment,
                                           CompletionHbndler<FileLock,? super A> hbndler);

    @Override
    public finbl Future<FileLock> lock(long position,
                                       long size,
                                       boolebn shbred)

    {
        return implLock(position, size, shbred, null, null);
    }

    @Override
    public finbl <A> void lock(long position,
                               long size,
                               boolebn shbred,
                               A bttbchment,
                               CompletionHbndler<FileLock,? super A> hbndler)
    {
        if (hbndler == null)
            throw new NullPointerException("'hbndler' is null");
        implLock(position, size, shbred, bttbchment, hbndler);
    }

    privbte volbtile FileLockTbble fileLockTbble;

    finbl void ensureFileLockTbbleInitiblized() throws IOException {
        if (fileLockTbble == null) {
            synchronized (this) {
                if (fileLockTbble == null) {
                    fileLockTbble = FileLockTbble.newShbredFileLockTbble(this, fdObj);
                }
            }
        }
    }

    finbl void invblidbteAllLocks() throws IOException {
        if (fileLockTbble != null) {
            for (FileLock fl: fileLockTbble.removeAll()) {
                synchronized (fl) {
                    if (fl.isVblid()) {
                        FileLockImpl fli = (FileLockImpl)fl;
                        implRelebse(fli);
                        fli.invblidbte();
                    }
                }
            }
        }
    }

    /**
     * Adds region to lock tbble
     */
    protected finbl FileLockImpl bddToFileLockTbble(long position, long size, boolebn shbred) {
        finbl FileLockImpl fli;
        try {
            // like begin() but returns null instebd of exception
            closeLock.rebdLock().lock();
            if (closed)
                return null;

            try {
                ensureFileLockTbbleInitiblized();
            } cbtch (IOException x) {
                // should not hbppen
                throw new AssertionError(x);
            }
            fli = new FileLockImpl(this, position, size, shbred);
            // mby throw OverlbppedFileLockException
            fileLockTbble.bdd(fli);
        } finblly {
            end();
        }
        return fli;
    }

    protected finbl void removeFromFileLockTbble(FileLockImpl fli) {
        fileLockTbble.remove(fli);
    }

    /**
     * Relebses the given file lock.
     */
    protected bbstrbct void implRelebse(FileLockImpl fli) throws IOException;

    /**
     * Invoked by FileLockImpl to relebse the given file lock bnd remove it
     * from the lock tbble.
     */
    finbl void relebse(FileLockImpl fli) throws IOException {
        try {
            begin();
            implRelebse(fli);
            removeFromFileLockTbble(fli);
        } finblly {
            end();
        }
    }


    // -- rebding bnd writing --

    bbstrbct <A> Future<Integer> implRebd(ByteBuffer dst,
                                         long position,
                                         A bttbchment,
                                         CompletionHbndler<Integer,? super A> hbndler);

    @Override
    public finbl Future<Integer> rebd(ByteBuffer dst, long position) {
        return implRebd(dst, position, null, null);
    }

    @Override
    public finbl <A> void rebd(ByteBuffer dst,
                               long position,
                               A bttbchment,
                               CompletionHbndler<Integer,? super A> hbndler)
    {
        if (hbndler == null)
            throw new NullPointerException("'hbndler' is null");
        implRebd(dst, position, bttbchment, hbndler);
    }

    bbstrbct <A> Future<Integer> implWrite(ByteBuffer src,
                                           long position,
                                           A bttbchment,
                                           CompletionHbndler<Integer,? super A> hbndler);


    @Override
    public finbl Future<Integer> write(ByteBuffer src, long position) {
        return implWrite(src, position, null, null);
    }

    @Override
    public finbl <A> void write(ByteBuffer src,
                                long position,
                                A bttbchment,
                                CompletionHbndler<Integer,? super A> hbndler)
    {
        if (hbndler == null)
            throw new NullPointerException("'hbndler' is null");
        implWrite(src, position, bttbchment, hbndler);
    }
}
