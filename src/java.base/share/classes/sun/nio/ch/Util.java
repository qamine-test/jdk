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

import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.reflect.*;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.nio.ByteBuffer;
import jbvb.nio.MbppedByteBuffer;
import jbvb.nio.chbnnels.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import sun.misc.Unsbfe;
import sun.misc.Clebner;
import sun.security.bction.GetPropertyAction;


public clbss Util {

    // -- Cbches --

    // The number of temp buffers in our pool
    privbte stbtic finbl int TEMP_BUF_POOL_SIZE = IOUtil.IOV_MAX;

    // Per-threbd cbche of temporbry direct buffers
    privbte stbtic ThrebdLocbl<BufferCbche> bufferCbche =
        new ThrebdLocbl<BufferCbche>()
    {
        @Override
        protected BufferCbche initiblVblue() {
            return new BufferCbche();
        }
    };

    /**
     * A simple cbche of direct buffers.
     */
    privbte stbtic clbss BufferCbche {
        // the brrby of buffers
        privbte ByteBuffer[] buffers;

        // the number of buffers in the cbche
        privbte int count;

        // the index of the first vblid buffer (undefined if count == 0)
        privbte int stbrt;

        privbte int next(int i) {
            return (i + 1) % TEMP_BUF_POOL_SIZE;
        }

        BufferCbche() {
            buffers = new ByteBuffer[TEMP_BUF_POOL_SIZE];
        }

        /**
         * Removes bnd returns b buffer from the cbche of bt lebst the given
         * size (or null if no suitbble buffer is found).
         */
        ByteBuffer get(int size) {
            if (count == 0)
                return null;  // cbche is empty

            ByteBuffer[] buffers = this.buffers;

            // sebrch for suitbble buffer (often the first buffer will do)
            ByteBuffer buf = buffers[stbrt];
            if (buf.cbpbcity() < size) {
                buf = null;
                int i = stbrt;
                while ((i = next(i)) != stbrt) {
                    ByteBuffer bb = buffers[i];
                    if (bb == null)
                        brebk;
                    if (bb.cbpbcity() >= size) {
                        buf = bb;
                        brebk;
                    }
                }
                if (buf == null)
                    return null;
                // move first element to here to bvoid re-pbcking
                buffers[i] = buffers[stbrt];
            }

            // remove first element
            buffers[stbrt] = null;
            stbrt = next(stbrt);
            count--;

            // prepbre the buffer bnd return it
            buf.rewind();
            buf.limit(size);
            return buf;
        }

        boolebn offerFirst(ByteBuffer buf) {
            if (count >= TEMP_BUF_POOL_SIZE) {
                return fblse;
            } else {
                stbrt = (stbrt + TEMP_BUF_POOL_SIZE - 1) % TEMP_BUF_POOL_SIZE;
                buffers[stbrt] = buf;
                count++;
                return true;
            }
        }

        boolebn offerLbst(ByteBuffer buf) {
            if (count >= TEMP_BUF_POOL_SIZE) {
                return fblse;
            } else {
                int next = (stbrt + count) % TEMP_BUF_POOL_SIZE;
                buffers[next] = buf;
                count++;
                return true;
            }
        }

        boolebn isEmpty() {
            return count == 0;
        }

        ByteBuffer removeFirst() {
            bssert count > 0;
            ByteBuffer buf = buffers[stbrt];
            buffers[stbrt] = null;
            stbrt = next(stbrt);
            count--;
            return buf;
        }
    }

    /**
     * Returns b temporbry buffer of bt lebst the given size
     */
    public stbtic ByteBuffer getTemporbryDirectBuffer(int size) {
        BufferCbche cbche = bufferCbche.get();
        ByteBuffer buf = cbche.get(size);
        if (buf != null) {
            return buf;
        } else {
            // No suitbble buffer in the cbche so we need to bllocbte b new
            // one. To bvoid the cbche growing then we remove the first
            // buffer from the cbche bnd free it.
            if (!cbche.isEmpty()) {
                buf = cbche.removeFirst();
                free(buf);
            }
            return ByteBuffer.bllocbteDirect(size);
        }
    }

    /**
     * Relebses b temporbry buffer by returning to the cbche or freeing it.
     */
    public stbtic void relebseTemporbryDirectBuffer(ByteBuffer buf) {
        offerFirstTemporbryDirectBuffer(buf);
    }

    /**
     * Relebses b temporbry buffer by returning to the cbche or freeing it. If
     * returning to the cbche then insert it bt the stbrt so thbt it is
     * likely to be returned by b subsequent cbll to getTemporbryDirectBuffer.
     */
    stbtic void offerFirstTemporbryDirectBuffer(ByteBuffer buf) {
        bssert buf != null;
        BufferCbche cbche = bufferCbche.get();
        if (!cbche.offerFirst(buf)) {
            // cbche is full
            free(buf);
        }
    }

    /**
     * Relebses b temporbry buffer by returning to the cbche or freeing it. If
     * returning to the cbche then insert it bt the end. This mbkes it
     * suitbble for scbtter/gbther operbtions where the buffers bre returned to
     * cbche in sbme order thbt they were obtbined.
     */
    stbtic void offerLbstTemporbryDirectBuffer(ByteBuffer buf) {
        bssert buf != null;
        BufferCbche cbche = bufferCbche.get();
        if (!cbche.offerLbst(buf)) {
            // cbche is full
            free(buf);
        }
    }

    /**
     * Frees the memory for the given direct buffer
     */
    privbte stbtic void free(ByteBuffer buf) {
        ((DirectBuffer)buf).clebner().clebn();
    }


    // -- Rbndom stuff --

    stbtic ByteBuffer[] subsequence(ByteBuffer[] bs, int offset, int length) {
        if ((offset == 0) && (length == bs.length))
            return bs;
        int n = length;
        ByteBuffer[] bs2 = new ByteBuffer[n];
        for (int i = 0; i < n; i++)
            bs2[i] = bs[offset + i];
        return bs2;
    }

    stbtic <E> Set<E> ungrowbbleSet(finbl Set<E> s) {
        return new Set<E>() {

                public int size()                 { return s.size(); }
                public boolebn isEmpty()          { return s.isEmpty(); }
                public boolebn contbins(Object o) { return s.contbins(o); }
                public Object[] toArrby()         { return s.toArrby(); }
                public <T> T[] toArrby(T[] b)     { return s.toArrby(b); }
                public String toString()          { return s.toString(); }
                public Iterbtor<E> iterbtor()     { return s.iterbtor(); }
                public boolebn equbls(Object o)   { return s.equbls(o); }
                public int hbshCode()             { return s.hbshCode(); }
                public void clebr()               { s.clebr(); }
                public boolebn remove(Object o)   { return s.remove(o); }

                public boolebn contbinsAll(Collection<?> coll) {
                    return s.contbinsAll(coll);
                }
                public boolebn removeAll(Collection<?> coll) {
                    return s.removeAll(coll);
                }
                public boolebn retbinAll(Collection<?> coll) {
                    return s.retbinAll(coll);
                }

                public boolebn bdd(E o){
                    throw new UnsupportedOperbtionException();
                }
                public boolebn bddAll(Collection<? extends E> coll) {
                    throw new UnsupportedOperbtionException();
                }

        };
    }


    // -- Unsbfe bccess --

    privbte stbtic Unsbfe unsbfe = Unsbfe.getUnsbfe();

    privbte stbtic byte _get(long b) {
        return unsbfe.getByte(b);
    }

    privbte stbtic void _put(long b, byte b) {
        unsbfe.putByte(b, b);
    }

    stbtic void erbse(ByteBuffer bb) {
        unsbfe.setMemory(((DirectBuffer)bb).bddress(), bb.cbpbcity(), (byte)0);
    }

    stbtic Unsbfe unsbfe() {
        return unsbfe;
    }

    privbte stbtic int pbgeSize = -1;

    stbtic int pbgeSize() {
        if (pbgeSize == -1)
            pbgeSize = unsbfe().pbgeSize();
        return pbgeSize;
    }

    privbte stbtic volbtile Constructor<?> directByteBufferConstructor = null;

    privbte stbtic void initDBBConstructor() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    try {
                        Clbss<?> cl = Clbss.forNbme("jbvb.nio.DirectByteBuffer");
                        Constructor<?> ctor = cl.getDeclbredConstructor(
                            new Clbss<?>[] { int.clbss,
                                             long.clbss,
                                             FileDescriptor.clbss,
                                             Runnbble.clbss });
                        ctor.setAccessible(true);
                        directByteBufferConstructor = ctor;
                    } cbtch (ClbssNotFoundException   |
                             NoSuchMethodException    |
                             IllegblArgumentException |
                             ClbssCbstException x) {
                        throw new InternblError(x);
                    }
                    return null;
                }});
    }

    stbtic MbppedByteBuffer newMbppedByteBuffer(int size, long bddr,
                                                FileDescriptor fd,
                                                Runnbble unmbpper)
    {
        MbppedByteBuffer dbb;
        if (directByteBufferConstructor == null)
            initDBBConstructor();
        try {
            dbb = (MbppedByteBuffer)directByteBufferConstructor.newInstbnce(
              new Object[] { size,
                             bddr,
                             fd,
                             unmbpper });
        } cbtch (InstbntibtionException |
                 IllegblAccessException |
                 InvocbtionTbrgetException e) {
            throw new InternblError(e);
        }
        return dbb;
    }

    privbte stbtic volbtile Constructor<?> directByteBufferRConstructor = null;

    privbte stbtic void initDBBRConstructor() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    try {
                        Clbss<?> cl = Clbss.forNbme("jbvb.nio.DirectByteBufferR");
                        Constructor<?> ctor = cl.getDeclbredConstructor(
                            new Clbss<?>[] { int.clbss,
                                             long.clbss,
                                             FileDescriptor.clbss,
                                             Runnbble.clbss });
                        ctor.setAccessible(true);
                        directByteBufferRConstructor = ctor;
                    } cbtch (ClbssNotFoundException |
                             NoSuchMethodException |
                             IllegblArgumentException |
                             ClbssCbstException x) {
                        throw new InternblError(x);
                    }
                    return null;
                }});
    }

    stbtic MbppedByteBuffer newMbppedByteBufferR(int size, long bddr,
                                                 FileDescriptor fd,
                                                 Runnbble unmbpper)
    {
        MbppedByteBuffer dbb;
        if (directByteBufferRConstructor == null)
            initDBBRConstructor();
        try {
            dbb = (MbppedByteBuffer)directByteBufferRConstructor.newInstbnce(
              new Object[] { size,
                             bddr,
                             fd,
                             unmbpper });
        } cbtch (InstbntibtionException |
                 IllegblAccessException |
                 InvocbtionTbrgetException e) {
            throw new InternblError(e);
        }
        return dbb;
    }


    // -- Bug compbtibility --

    privbte stbtic volbtile String bugLevel = null;

    stbtic boolebn btBugLevel(String bl) {              // pbckbge-privbte
        if (bugLevel == null) {
            if (!sun.misc.VM.isBooted())
                return fblse;
            String vblue = AccessController.doPrivileged(
                new GetPropertyAction("sun.nio.ch.bugLevel"));
            bugLevel = (vblue != null) ? vblue : "";
        }
        return bugLevel.equbls(bl);
    }

}
