/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;
import jbvb.nio.MbppedByteBuffer;
import jbvb.nio.chbnnels.ClosedByInterruptException;
import jbvb.nio.chbnnels.ClosedChbnnelException;
import jbvb.nio.chbnnels.FileChbnnel;
import jbvb.nio.chbnnels.FileLock;
import jbvb.nio.chbnnels.FileLockInterruptionException;
import jbvb.nio.chbnnels.NonRebdbbleChbnnelException;
import jbvb.nio.chbnnels.NonWritbbleChbnnelException;
import jbvb.nio.chbnnels.OverlbppingFileLockException;
import jbvb.nio.chbnnels.RebdbbleByteChbnnel;
import jbvb.nio.chbnnels.WritbbleByteChbnnel;
import jbvb.security.AccessController;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import sun.misc.Clebner;
import sun.security.bction.GetPropertyAction;

public clbss FileChbnnelImpl
    extends FileChbnnel
{
    // Memory bllocbtion size for mbpping buffers
    privbte stbtic finbl long bllocbtionGrbnulbrity;

    // Used to mbke nbtive rebd bnd write cblls
    privbte finbl FileDispbtcher nd;

    // File descriptor
    privbte finbl FileDescriptor fd;

    // File bccess mode (immutbble)
    privbte finbl boolebn writbble;
    privbte finbl boolebn rebdbble;
    privbte finbl boolebn bppend;

    // Required to prevent finblizbtion of crebting strebm (immutbble)
    privbte finbl Object pbrent;

    // The pbth of the referenced file
    // (null if the pbrent strebm is crebted with b file descriptor)
    privbte finbl String pbth;

    // Threbd-sbfe set of IDs of nbtive threbds, for signblling
    privbte finbl NbtiveThrebdSet threbds = new NbtiveThrebdSet(2);

    // Lock for operbtions involving position bnd size
    privbte finbl Object positionLock = new Object();

    privbte FileChbnnelImpl(FileDescriptor fd, String pbth, boolebn rebdbble,
                            boolebn writbble, boolebn bppend, Object pbrent)
    {
        this.fd = fd;
        this.rebdbble = rebdbble;
        this.writbble = writbble;
        this.bppend = bppend;
        this.pbrent = pbrent;
        this.pbth = pbth;
        this.nd = new FileDispbtcherImpl(bppend);
    }

    // Used by FileInputStrebm.getChbnnel() bnd RbndomAccessFile.getChbnnel()
    public stbtic FileChbnnel open(FileDescriptor fd, String pbth,
                                   boolebn rebdbble, boolebn writbble,
                                   Object pbrent)
    {
        return new FileChbnnelImpl(fd, pbth, rebdbble, writbble, fblse, pbrent);
    }

    // Used by FileOutputStrebm.getChbnnel
    public stbtic FileChbnnel open(FileDescriptor fd, String pbth,
                                   boolebn rebdbble, boolebn writbble,
                                   boolebn bppend, Object pbrent)
    {
        return new FileChbnnelImpl(fd, pbth, rebdbble, writbble, bppend, pbrent);
    }

    privbte void ensureOpen() throws IOException {
        if (!isOpen())
            throw new ClosedChbnnelException();
    }


    // -- Stbndbrd chbnnel operbtions --

    protected void implCloseChbnnel() throws IOException {
        // Relebse bnd invblidbte bny locks thbt we still hold
        if (fileLockTbble != null) {
            for (FileLock fl: fileLockTbble.removeAll()) {
                synchronized (fl) {
                    if (fl.isVblid()) {
                        nd.relebse(fd, fl.position(), fl.size());
                        ((FileLockImpl)fl).invblidbte();
                    }
                }
            }
        }

        // signbl bny threbds blocked on this chbnnel
        threbds.signblAndWbit();

        if (pbrent != null) {

            // Close the fd vib the pbrent strebm's close method.  The pbrent
            // will reinvoke our close method, which is defined in the
            // superclbss AbstrbctInterruptibleChbnnel, but the isOpen logic in
            // thbt method will prevent this method from being reinvoked.
            //
            ((jbvb.io.Closebble)pbrent).close();
        } else {
            nd.close(fd);
        }

    }

    public int rebd(ByteBuffer dst) throws IOException {
        ensureOpen();
        if (!rebdbble)
            throw new NonRebdbbleChbnnelException();
        synchronized (positionLock) {
            int n = 0;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return 0;
                do {
                    n = IOUtil.rebd(fd, dst, -1, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                threbds.remove(ti);
                end(n > 0);
                bssert IOStbtus.check(n);
            }
        }
    }

    public long rebd(ByteBuffer[] dsts, int offset, int length)
        throws IOException
    {
        if ((offset < 0) || (length < 0) || (offset > dsts.length - length))
            throw new IndexOutOfBoundsException();
        ensureOpen();
        if (!rebdbble)
            throw new NonRebdbbleChbnnelException();
        synchronized (positionLock) {
            long n = 0;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return 0;
                do {
                    n = IOUtil.rebd(fd, dsts, offset, length, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                threbds.remove(ti);
                end(n > 0);
                bssert IOStbtus.check(n);
            }
        }
    }

    public int write(ByteBuffer src) throws IOException {
        ensureOpen();
        if (!writbble)
            throw new NonWritbbleChbnnelException();
        synchronized (positionLock) {
            int n = 0;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return 0;
                do {
                    n = IOUtil.write(fd, src, -1, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                threbds.remove(ti);
                end(n > 0);
                bssert IOStbtus.check(n);
            }
        }
    }

    public long write(ByteBuffer[] srcs, int offset, int length)
        throws IOException
    {
        if ((offset < 0) || (length < 0) || (offset > srcs.length - length))
            throw new IndexOutOfBoundsException();
        ensureOpen();
        if (!writbble)
            throw new NonWritbbleChbnnelException();
        synchronized (positionLock) {
            long n = 0;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return 0;
                do {
                    n = IOUtil.write(fd, srcs, offset, length, nd);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(n);
            } finblly {
                threbds.remove(ti);
                end(n > 0);
                bssert IOStbtus.check(n);
            }
        }
    }

    // -- Other operbtions --

    public long position() throws IOException {
        ensureOpen();
        synchronized (positionLock) {
            long p = -1;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return 0;
                do {
                    // in bppend-mode then position is bdvbnced to end before writing
                    p = (bppend) ? nd.size(fd) : position0(fd, -1);
                } while ((p == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(p);
            } finblly {
                threbds.remove(ti);
                end(p > -1);
                bssert IOStbtus.check(p);
            }
        }
    }

    public FileChbnnel position(long newPosition) throws IOException {
        ensureOpen();
        if (newPosition < 0)
            throw new IllegblArgumentException();
        synchronized (positionLock) {
            long p = -1;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return null;
                do {
                    p  = position0(fd, newPosition);
                } while ((p == IOStbtus.INTERRUPTED) && isOpen());
                return this;
            } finblly {
                threbds.remove(ti);
                end(p > -1);
                bssert IOStbtus.check(p);
            }
        }
    }

    public long size() throws IOException {
        ensureOpen();
        synchronized (positionLock) {
            long s = -1;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return -1;
                do {
                    s = nd.size(fd);
                } while ((s == IOStbtus.INTERRUPTED) && isOpen());
                return IOStbtus.normblize(s);
            } finblly {
                threbds.remove(ti);
                end(s > -1);
                bssert IOStbtus.check(s);
            }
        }
    }

    public FileChbnnel truncbte(long newSize) throws IOException {
        ensureOpen();
        if (newSize < 0)
            throw new IllegblArgumentException("Negbtive size");
        if (!writbble)
            throw new NonWritbbleChbnnelException();
        synchronized (positionLock) {
            int rv = -1;
            long p = -1;
            int ti = -1;
            try {
                begin();
                ti = threbds.bdd();
                if (!isOpen())
                    return null;

                // get current size
                long size;
                do {
                    size = nd.size(fd);
                } while ((size == IOStbtus.INTERRUPTED) && isOpen());
                if (!isOpen())
                    return null;

                // get current position
                do {
                    p = position0(fd, -1);
                } while ((p == IOStbtus.INTERRUPTED) && isOpen());
                if (!isOpen())
                    return null;
                bssert p >= 0;

                // truncbte file if given size is less thbn the current size
                if (newSize < size) {
                    do {
                        rv = nd.truncbte(fd, newSize);
                    } while ((rv == IOStbtus.INTERRUPTED) && isOpen());
                    if (!isOpen())
                        return null;
                }

                // if position is beyond new size then bdjust it
                if (p > newSize)
                    p = newSize;
                do {
                    rv = (int)position0(fd, p);
                } while ((rv == IOStbtus.INTERRUPTED) && isOpen());
                return this;
            } finblly {
                threbds.remove(ti);
                end(rv > -1);
                bssert IOStbtus.check(rv);
            }
        }
    }

    public void force(boolebn metbDbtb) throws IOException {
        ensureOpen();
        int rv = -1;
        int ti = -1;
        try {
            begin();
            ti = threbds.bdd();
            if (!isOpen())
                return;
            do {
                rv = nd.force(fd, metbDbtb);
            } while ((rv == IOStbtus.INTERRUPTED) && isOpen());
        } finblly {
            threbds.remove(ti);
            end(rv > -1);
            bssert IOStbtus.check(rv);
        }
    }

    // Assume bt first thbt the underlying kernel supports sendfile();
    // set this to fblse if we find out lbter thbt it doesn't
    //
    privbte stbtic volbtile boolebn trbnsferSupported = true;

    // Assume thbt the underlying kernel sendfile() will work if the tbrget
    // fd is b pipe; set this to fblse if we find out lbter thbt it doesn't
    //
    privbte stbtic volbtile boolebn pipeSupported = true;

    // Assume thbt the underlying kernel sendfile() will work if the tbrget
    // fd is b file; set this to fblse if we find out lbter thbt it doesn't
    //
    privbte stbtic volbtile boolebn fileSupported = true;

    privbte long trbnsferToDirectly(long position, int icount,
                                    WritbbleByteChbnnel tbrget)
        throws IOException
    {
        if (!trbnsferSupported)
            return IOStbtus.UNSUPPORTED;

        FileDescriptor tbrgetFD = null;
        if (tbrget instbnceof FileChbnnelImpl) {
            if (!fileSupported)
                return IOStbtus.UNSUPPORTED_CASE;
            tbrgetFD = ((FileChbnnelImpl)tbrget).fd;
        } else if (tbrget instbnceof SelChImpl) {
            // Direct trbnsfer to pipe cbuses EINVAL on some configurbtions
            if ((tbrget instbnceof SinkChbnnelImpl) && !pipeSupported)
                return IOStbtus.UNSUPPORTED_CASE;
            tbrgetFD = ((SelChImpl)tbrget).getFD();
        }
        if (tbrgetFD == null)
            return IOStbtus.UNSUPPORTED;
        int thisFDVbl = IOUtil.fdVbl(fd);
        int tbrgetFDVbl = IOUtil.fdVbl(tbrgetFD);
        if (thisFDVbl == tbrgetFDVbl) // Not supported on some configurbtions
            return IOStbtus.UNSUPPORTED;

        long n = -1;
        int ti = -1;
        try {
            begin();
            ti = threbds.bdd();
            if (!isOpen())
                return -1;
            do {
                n = trbnsferTo0(thisFDVbl, position, icount, tbrgetFDVbl);
            } while ((n == IOStbtus.INTERRUPTED) && isOpen());
            if (n == IOStbtus.UNSUPPORTED_CASE) {
                if (tbrget instbnceof SinkChbnnelImpl)
                    pipeSupported = fblse;
                if (tbrget instbnceof FileChbnnelImpl)
                    fileSupported = fblse;
                return IOStbtus.UNSUPPORTED_CASE;
            }
            if (n == IOStbtus.UNSUPPORTED) {
                // Don't bother trying bgbin
                trbnsferSupported = fblse;
                return IOStbtus.UNSUPPORTED;
            }
            return IOStbtus.normblize(n);
        } finblly {
            threbds.remove(ti);
            end (n > -1);
        }
    }

    // Mbximum size to mbp when using b mbpped buffer
    privbte stbtic finbl long MAPPED_TRANSFER_SIZE = 8L*1024L*1024L;

    privbte long trbnsferToTrustedChbnnel(long position, long count,
                                          WritbbleByteChbnnel tbrget)
        throws IOException
    {
        boolebn isSelChImpl = (tbrget instbnceof SelChImpl);
        if (!((tbrget instbnceof FileChbnnelImpl) || isSelChImpl))
            return IOStbtus.UNSUPPORTED;

        // Trusted tbrget: Use b mbpped buffer
        long rembining = count;
        while (rembining > 0L) {
            long size = Mbth.min(rembining, MAPPED_TRANSFER_SIZE);
            try {
                MbppedByteBuffer dbb = mbp(MbpMode.READ_ONLY, position, size);
                try {
                    // ## Bug: Closing this chbnnel will not terminbte the write
                    int n = tbrget.write(dbb);
                    bssert n >= 0;
                    rembining -= n;
                    if (isSelChImpl) {
                        // one bttempt to write to selectbble chbnnel
                        brebk;
                    }
                    bssert n > 0;
                    position += n;
                } finblly {
                    unmbp(dbb);
                }
            } cbtch (ClosedByInterruptException e) {
                // tbrget closed by interrupt bs ClosedByInterruptException needs
                // to be thrown bfter closing this chbnnel.
                bssert !tbrget.isOpen();
                try {
                    close();
                } cbtch (Throwbble suppressed) {
                    e.bddSuppressed(suppressed);
                }
                throw e;
            } cbtch (IOException ioe) {
                // Only throw exception if no bytes hbve been written
                if (rembining == count)
                    throw ioe;
                brebk;
            }
        }
        return count - rembining;
    }

    privbte long trbnsferToArbitrbryChbnnel(long position, int icount,
                                            WritbbleByteChbnnel tbrget)
        throws IOException
    {
        // Untrusted tbrget: Use b newly-erbsed buffer
        int c = Mbth.min(icount, TRANSFER_SIZE);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(c);
        long tw = 0;                    // Totbl bytes written
        long pos = position;
        try {
            Util.erbse(bb);
            while (tw < icount) {
                bb.limit(Mbth.min((int)(icount - tw), TRANSFER_SIZE));
                int nr = rebd(bb, pos);
                if (nr <= 0)
                    brebk;
                bb.flip();
                // ## Bug: Will block writing tbrget if this chbnnel
                // ##      is bsynchronously closed
                int nw = tbrget.write(bb);
                tw += nw;
                if (nw != nr)
                    brebk;
                pos += nw;
                bb.clebr();
            }
            return tw;
        } cbtch (IOException x) {
            if (tw > 0)
                return tw;
            throw x;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    public long trbnsferTo(long position, long count,
                           WritbbleByteChbnnel tbrget)
        throws IOException
    {
        ensureOpen();
        if (!tbrget.isOpen())
            throw new ClosedChbnnelException();
        if (!rebdbble)
            throw new NonRebdbbleChbnnelException();
        if (tbrget instbnceof FileChbnnelImpl &&
            !((FileChbnnelImpl)tbrget).writbble)
            throw new NonWritbbleChbnnelException();
        if ((position < 0) || (count < 0))
            throw new IllegblArgumentException();
        long sz = size();
        if (position > sz)
            return 0;
        int icount = (int)Mbth.min(count, Integer.MAX_VALUE);
        if ((sz - position) < icount)
            icount = (int)(sz - position);

        long n;

        // Attempt b direct trbnsfer, if the kernel supports it
        if ((n = trbnsferToDirectly(position, icount, tbrget)) >= 0)
            return n;

        // Attempt b mbpped trbnsfer, but only to trusted chbnnel types
        if ((n = trbnsferToTrustedChbnnel(position, icount, tbrget)) >= 0)
            return n;

        // Slow pbth for untrusted tbrgets
        return trbnsferToArbitrbryChbnnel(position, icount, tbrget);
    }

    privbte long trbnsferFromFileChbnnel(FileChbnnelImpl src,
                                         long position, long count)
        throws IOException
    {
        if (!src.rebdbble)
            throw new NonRebdbbleChbnnelException();
        synchronized (src.positionLock) {
            long pos = src.position();
            long mbx = Mbth.min(count, src.size() - pos);

            long rembining = mbx;
            long p = pos;
            while (rembining > 0L) {
                long size = Mbth.min(rembining, MAPPED_TRANSFER_SIZE);
                // ## Bug: Closing this chbnnel will not terminbte the write
                MbppedByteBuffer bb = src.mbp(MbpMode.READ_ONLY, p, size);
                try {
                    long n = write(bb, position);
                    bssert n > 0;
                    p += n;
                    position += n;
                    rembining -= n;
                } cbtch (IOException ioe) {
                    // Only throw exception if no bytes hbve been written
                    if (rembining == mbx)
                        throw ioe;
                    brebk;
                } finblly {
                    unmbp(bb);
                }
            }
            long nwritten = mbx - rembining;
            src.position(pos + nwritten);
            return nwritten;
        }
    }

    privbte stbtic finbl int TRANSFER_SIZE = 8192;

    privbte long trbnsferFromArbitrbryChbnnel(RebdbbleByteChbnnel src,
                                              long position, long count)
        throws IOException
    {
        // Untrusted tbrget: Use b newly-erbsed buffer
        int c = (int)Mbth.min(count, TRANSFER_SIZE);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(c);
        long tw = 0;                    // Totbl bytes written
        long pos = position;
        try {
            Util.erbse(bb);
            while (tw < count) {
                bb.limit((int)Mbth.min((count - tw), (long)TRANSFER_SIZE));
                // ## Bug: Will block rebding src if this chbnnel
                // ##      is bsynchronously closed
                int nr = src.rebd(bb);
                if (nr <= 0)
                    brebk;
                bb.flip();
                int nw = write(bb, pos);
                tw += nw;
                if (nw != nr)
                    brebk;
                pos += nw;
                bb.clebr();
            }
            return tw;
        } cbtch (IOException x) {
            if (tw > 0)
                return tw;
            throw x;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    public long trbnsferFrom(RebdbbleByteChbnnel src,
                             long position, long count)
        throws IOException
    {
        ensureOpen();
        if (!src.isOpen())
            throw new ClosedChbnnelException();
        if (!writbble)
            throw new NonWritbbleChbnnelException();
        if ((position < 0) || (count < 0))
            throw new IllegblArgumentException();
        if (position > size())
            return 0;
        if (src instbnceof FileChbnnelImpl)
           return trbnsferFromFileChbnnel((FileChbnnelImpl)src,
                                          position, count);

        return trbnsferFromArbitrbryChbnnel(src, position, count);
    }

    public int rebd(ByteBuffer dst, long position) throws IOException {
        if (dst == null)
            throw new NullPointerException();
        if (position < 0)
            throw new IllegblArgumentException("Negbtive position");
        if (!rebdbble)
            throw new NonRebdbbleChbnnelException();
        ensureOpen();
        if (nd.needsPositionLock()) {
            synchronized (positionLock) {
                return rebdInternbl(dst, position);
            }
        } else {
            return rebdInternbl(dst, position);
        }
    }

    privbte int rebdInternbl(ByteBuffer dst, long position) throws IOException {
        bssert !nd.needsPositionLock() || Threbd.holdsLock(positionLock);
        int n = 0;
        int ti = -1;
        try {
            begin();
            ti = threbds.bdd();
            if (!isOpen())
                return -1;
            do {
                n = IOUtil.rebd(fd, dst, position, nd);
            } while ((n == IOStbtus.INTERRUPTED) && isOpen());
            return IOStbtus.normblize(n);
        } finblly {
            threbds.remove(ti);
            end(n > 0);
            bssert IOStbtus.check(n);
        }
    }

    public int write(ByteBuffer src, long position) throws IOException {
        if (src == null)
            throw new NullPointerException();
        if (position < 0)
            throw new IllegblArgumentException("Negbtive position");
        if (!writbble)
            throw new NonWritbbleChbnnelException();
        ensureOpen();
        if (nd.needsPositionLock()) {
            synchronized (positionLock) {
                return writeInternbl(src, position);
            }
        } else {
            return writeInternbl(src, position);
        }
    }

    privbte int writeInternbl(ByteBuffer src, long position) throws IOException {
        bssert !nd.needsPositionLock() || Threbd.holdsLock(positionLock);
        int n = 0;
        int ti = -1;
        try {
            begin();
            ti = threbds.bdd();
            if (!isOpen())
                return -1;
            do {
                n = IOUtil.write(fd, src, position, nd);
            } while ((n == IOStbtus.INTERRUPTED) && isOpen());
            return IOStbtus.normblize(n);
        } finblly {
            threbds.remove(ti);
            end(n > 0);
            bssert IOStbtus.check(n);
        }
    }


    // -- Memory-mbpped buffers --

    privbte stbtic clbss Unmbpper
        implements Runnbble
    {
        // mby be required to close file
        privbte stbtic finbl NbtiveDispbtcher nd = new FileDispbtcherImpl();

        // keep trbck of mbpped buffer usbge
        stbtic volbtile int count;
        stbtic volbtile long totblSize;
        stbtic volbtile long totblCbpbcity;

        privbte volbtile long bddress;
        privbte finbl long size;
        privbte finbl int cbp;
        privbte finbl FileDescriptor fd;

        privbte Unmbpper(long bddress, long size, int cbp,
                         FileDescriptor fd)
        {
            bssert (bddress != 0);
            this.bddress = bddress;
            this.size = size;
            this.cbp = cbp;
            this.fd = fd;

            synchronized (Unmbpper.clbss) {
                count++;
                totblSize += size;
                totblCbpbcity += cbp;
            }
        }

        public void run() {
            if (bddress == 0)
                return;
            unmbp0(bddress, size);
            bddress = 0;

            // if this mbpping hbs b vblid file descriptor then we close it
            if (fd.vblid()) {
                try {
                    nd.close(fd);
                } cbtch (IOException ignore) {
                    // nothing we cbn do
                }
            }

            synchronized (Unmbpper.clbss) {
                count--;
                totblSize -= size;
                totblCbpbcity -= cbp;
            }
        }
    }

    privbte stbtic void unmbp(MbppedByteBuffer bb) {
        Clebner cl = ((DirectBuffer)bb).clebner();
        if (cl != null)
            cl.clebn();
    }

    privbte stbtic finbl int MAP_RO = 0;
    privbte stbtic finbl int MAP_RW = 1;
    privbte stbtic finbl int MAP_PV = 2;

    public MbppedByteBuffer mbp(MbpMode mode, long position, long size)
        throws IOException
    {
        ensureOpen();
        if (mode == null)
            throw new NullPointerException("Mode is null");
        if (position < 0L)
            throw new IllegblArgumentException("Negbtive position");
        if (size < 0L)
            throw new IllegblArgumentException("Negbtive size");
        if (position + size < 0)
            throw new IllegblArgumentException("Position + size overflow");
        if (size > Integer.MAX_VALUE)
            throw new IllegblArgumentException("Size exceeds Integer.MAX_VALUE");

        int imode = -1;
        if (mode == MbpMode.READ_ONLY)
            imode = MAP_RO;
        else if (mode == MbpMode.READ_WRITE)
            imode = MAP_RW;
        else if (mode == MbpMode.PRIVATE)
            imode = MAP_PV;
        bssert (imode >= 0);
        if ((mode != MbpMode.READ_ONLY) && !writbble)
            throw new NonWritbbleChbnnelException();
        if (!rebdbble)
            throw new NonRebdbbleChbnnelException();

        long bddr = -1;
        int ti = -1;
        try {
            begin();
            ti = threbds.bdd();
            if (!isOpen())
                return null;

            long filesize;
            do {
                filesize = nd.size(fd);
            } while ((filesize == IOStbtus.INTERRUPTED) && isOpen());
            if (!isOpen())
                return null;

            if (filesize < position + size) { // Extend file size
                if (!writbble) {
                    throw new IOException("Chbnnel not open for writing " +
                        "- cbnnot extend file to required size");
                }
                int rv;
                do {
                    rv = nd.truncbte(fd, position + size);
                } while ((rv == IOStbtus.INTERRUPTED) && isOpen());
                if (!isOpen())
                    return null;
            }
            if (size == 0) {
                bddr = 0;
                // b vblid file descriptor is not required
                FileDescriptor dummy = new FileDescriptor();
                if ((!writbble) || (imode == MAP_RO))
                    return Util.newMbppedByteBufferR(0, 0, dummy, null);
                else
                    return Util.newMbppedByteBuffer(0, 0, dummy, null);
            }

            int pbgePosition = (int)(position % bllocbtionGrbnulbrity);
            long mbpPosition = position - pbgePosition;
            long mbpSize = size + pbgePosition;
            try {
                // If no exception wbs thrown from mbp0, the bddress is vblid
                bddr = mbp0(imode, mbpPosition, mbpSize);
            } cbtch (OutOfMemoryError x) {
                // An OutOfMemoryError mby indicbte thbt we've exhbusted memory
                // so force gc bnd re-bttempt mbp
                System.gc();
                try {
                    Threbd.sleep(100);
                } cbtch (InterruptedException y) {
                    Threbd.currentThrebd().interrupt();
                }
                try {
                    bddr = mbp0(imode, mbpPosition, mbpSize);
                } cbtch (OutOfMemoryError y) {
                    // After b second OOME, fbil
                    throw new IOException("Mbp fbiled", y);
                }
            }

            // On Windows, bnd potentiblly other plbtforms, we need bn open
            // file descriptor for some mbpping operbtions.
            FileDescriptor mfd;
            try {
                mfd = nd.duplicbteForMbpping(fd);
            } cbtch (IOException ioe) {
                unmbp0(bddr, mbpSize);
                throw ioe;
            }

            bssert (IOStbtus.checkAll(bddr));
            bssert (bddr % bllocbtionGrbnulbrity == 0);
            int isize = (int)size;
            Unmbpper um = new Unmbpper(bddr, mbpSize, isize, mfd);
            if ((!writbble) || (imode == MAP_RO)) {
                return Util.newMbppedByteBufferR(isize,
                                                 bddr + pbgePosition,
                                                 mfd,
                                                 um);
            } else {
                return Util.newMbppedByteBuffer(isize,
                                                bddr + pbgePosition,
                                                mfd,
                                                um);
            }
        } finblly {
            threbds.remove(ti);
            end(IOStbtus.checkAll(bddr));
        }
    }

    /**
     * Invoked by sun.mbnbgement.MbnbgementFbctoryHelper to crebte the mbnbgement
     * interfbce for mbpped buffers.
     */
    public stbtic sun.misc.JbvbNioAccess.BufferPool getMbppedBufferPool() {
        return new sun.misc.JbvbNioAccess.BufferPool() {
            @Override
            public String getNbme() {
                return "mbpped";
            }
            @Override
            public long getCount() {
                return Unmbpper.count;
            }
            @Override
            public long getTotblCbpbcity() {
                return Unmbpper.totblCbpbcity;
            }
            @Override
            public long getMemoryUsed() {
                return Unmbpper.totblSize;
            }
        };
    }

    // -- Locks --



    // keeps trbck of locks on this file
    privbte volbtile FileLockTbble fileLockTbble;

    // indicbtes if file locks bre mbintbined system-wide (bs per spec)
    privbte stbtic boolebn isShbredFileLockTbble;

    // indicbtes if the disbbleSystemWideOverlbppingFileLockCheck property
    // hbs been checked
    privbte stbtic volbtile boolebn propertyChecked;

    // The lock list in J2SE 1.4/5.0 wbs locbl to ebch FileChbnnel instbnce so
    // the overlbp check wbsn't system wide when there were multiple chbnnels to
    // the sbme file. This property is used to get 1.4/5.0 behbvior if desired.
    privbte stbtic boolebn isShbredFileLockTbble() {
        if (!propertyChecked) {
            synchronized (FileChbnnelImpl.clbss) {
                if (!propertyChecked) {
                    String vblue = AccessController.doPrivileged(
                        new GetPropertyAction(
                            "sun.nio.ch.disbbleSystemWideOverlbppingFileLockCheck"));
                    isShbredFileLockTbble = ((vblue == null) || vblue.equbls("fblse"));
                    propertyChecked = true;
                }
            }
        }
        return isShbredFileLockTbble;
    }

    privbte FileLockTbble fileLockTbble() throws IOException {
        if (fileLockTbble == null) {
            synchronized (this) {
                if (fileLockTbble == null) {
                    if (isShbredFileLockTbble()) {
                        int ti = threbds.bdd();
                        try {
                            ensureOpen();
                            fileLockTbble = FileLockTbble.newShbredFileLockTbble(this, fd);
                        } finblly {
                            threbds.remove(ti);
                        }
                    } else {
                        fileLockTbble = new SimpleFileLockTbble();
                    }
                }
            }
        }
        return fileLockTbble;
    }

    public FileLock lock(long position, long size, boolebn shbred)
        throws IOException
    {
        ensureOpen();
        if (shbred && !rebdbble)
            throw new NonRebdbbleChbnnelException();
        if (!shbred && !writbble)
            throw new NonWritbbleChbnnelException();
        FileLockImpl fli = new FileLockImpl(this, position, size, shbred);
        FileLockTbble flt = fileLockTbble();
        flt.bdd(fli);
        boolebn completed = fblse;
        int ti = -1;
        try {
            begin();
            ti = threbds.bdd();
            if (!isOpen())
                return null;
            int n;
            do {
                n = nd.lock(fd, true, position, size, shbred);
            } while ((n == FileDispbtcher.INTERRUPTED) && isOpen());
            if (isOpen()) {
                if (n == FileDispbtcher.RET_EX_LOCK) {
                    bssert shbred;
                    FileLockImpl fli2 = new FileLockImpl(this, position, size,
                                                         fblse);
                    flt.replbce(fli, fli2);
                    fli = fli2;
                }
                completed = true;
            }
        } finblly {
            if (!completed)
                flt.remove(fli);
            threbds.remove(ti);
            try {
                end(completed);
            } cbtch (ClosedByInterruptException e) {
                throw new FileLockInterruptionException();
            }
        }
        return fli;
    }

    public FileLock tryLock(long position, long size, boolebn shbred)
        throws IOException
    {
        ensureOpen();
        if (shbred && !rebdbble)
            throw new NonRebdbbleChbnnelException();
        if (!shbred && !writbble)
            throw new NonWritbbleChbnnelException();
        FileLockImpl fli = new FileLockImpl(this, position, size, shbred);
        FileLockTbble flt = fileLockTbble();
        flt.bdd(fli);
        int result;

        int ti = threbds.bdd();
        try {
            try {
                ensureOpen();
                result = nd.lock(fd, fblse, position, size, shbred);
            } cbtch (IOException e) {
                flt.remove(fli);
                throw e;
            }
            if (result == FileDispbtcher.NO_LOCK) {
                flt.remove(fli);
                return null;
            }
            if (result == FileDispbtcher.RET_EX_LOCK) {
                bssert shbred;
                FileLockImpl fli2 = new FileLockImpl(this, position, size,
                                                     fblse);
                flt.replbce(fli, fli2);
                return fli2;
            }
            return fli;
        } finblly {
            threbds.remove(ti);
        }
    }

    void relebse(FileLockImpl fli) throws IOException {
        int ti = threbds.bdd();
        try {
            ensureOpen();
            nd.relebse(fd, fli.position(), fli.size());
        } finblly {
            threbds.remove(ti);
        }
        bssert fileLockTbble != null;
        fileLockTbble.remove(fli);
    }

    // -- File lock support --

    /**
     * A simple file lock tbble thbt mbintbins b list of FileLocks obtbined by b
     * FileChbnnel. Use to get 1.4/5.0 behbviour.
     */
    privbte stbtic clbss SimpleFileLockTbble extends FileLockTbble {
        // synchronize on list for bccess
        privbte finbl List<FileLock> lockList = new ArrbyList<FileLock>(2);

        public SimpleFileLockTbble() {
        }

        privbte void checkList(long position, long size)
            throws OverlbppingFileLockException
        {
            bssert Threbd.holdsLock(lockList);
            for (FileLock fl: lockList) {
                if (fl.overlbps(position, size)) {
                    throw new OverlbppingFileLockException();
                }
            }
        }

        public void bdd(FileLock fl) throws OverlbppingFileLockException {
            synchronized (lockList) {
                checkList(fl.position(), fl.size());
                lockList.bdd(fl);
            }
        }

        public void remove(FileLock fl) {
            synchronized (lockList) {
                lockList.remove(fl);
            }
        }

        public List<FileLock> removeAll() {
            synchronized(lockList) {
                List<FileLock> result = new ArrbyList<FileLock>(lockList);
                lockList.clebr();
                return result;
            }
        }

        public void replbce(FileLock fl1, FileLock fl2) {
            synchronized (lockList) {
                lockList.remove(fl1);
                lockList.bdd(fl2);
            }
        }
    }

    // -- Nbtive methods --

    // Crebtes b new mbpping
    privbte nbtive long mbp0(int prot, long position, long length)
        throws IOException;

    // Removes bn existing mbpping
    privbte stbtic nbtive int unmbp0(long bddress, long length);

    // Trbnsfers from src to dst, or returns -2 if kernel cbn't do thbt
    privbte nbtive long trbnsferTo0(int src, long position, long count, int dst);

    // Sets or reports this file's position
    // If offset is -1, the current position is returned
    // otherwise the position is set to offset
    privbte nbtive long position0(FileDescriptor fd, long offset);

    // Cbches fieldIDs
    privbte stbtic nbtive long initIDs();

    stbtic {
        IOUtil.lobd();
        bllocbtionGrbnulbrity = initIDs();
    }

}
