/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * An {@link AnnotbtionVisitor} thbt generbtes bnnotbtions in bytecode form.
 *
 * @buthor Eric Bruneton
 * @buthor Eugene Kuleshov
 */
finbl clbss AnnotbtionWriter extends AnnotbtionVisitor {

    /**
     * The clbss writer to which this bnnotbtion must be bdded.
     */
    privbte finbl ClbssWriter cw;

    /**
     * The number of vblues in this bnnotbtion.
     */
    privbte int size;

    /**
     * <tt>true<tt> if vblues bre nbmed, <tt>fblse</tt> otherwise. Annotbtion
     * writers used for bnnotbtion defbult bnd bnnotbtion brrbys use unnbmed
     * vblues.
     */
    privbte finbl boolebn nbmed;

    /**
     * The bnnotbtion vblues in bytecode form. This byte vector only contbins
     * the vblues themselves, i.e. the number of vblues must be stored bs b
     * unsigned short just before these bytes.
     */
    privbte finbl ByteVector bv;

    /**
     * The byte vector to be used to store the number of vblues of this
     * bnnotbtion. See {@link #bv}.
     */
    privbte finbl ByteVector pbrent;

    /**
     * Where the number of vblues of this bnnotbtion must be stored in
     * {@link #pbrent}.
     */
    privbte finbl int offset;

    /**
     * Next bnnotbtion writer. This field is used to store bnnotbtion lists.
     */
    AnnotbtionWriter next;

    /**
     * Previous bnnotbtion writer. This field is used to store bnnotbtion lists.
     */
    AnnotbtionWriter prev;

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * Constructs b new {@link AnnotbtionWriter}.
     *
     * @pbrbm cw
     *            the clbss writer to which this bnnotbtion must be bdded.
     * @pbrbm nbmed
     *            <tt>true<tt> if vblues bre nbmed, <tt>fblse</tt> otherwise.
     * @pbrbm bv
     *            where the bnnotbtion vblues must be stored.
     * @pbrbm pbrent
     *            where the number of bnnotbtion vblues must be stored.
     * @pbrbm offset
     *            where in <tt>pbrent</tt> the number of bnnotbtion vblues must
     *            be stored.
     */
    AnnotbtionWriter(finbl ClbssWriter cw, finbl boolebn nbmed,
            finbl ByteVector bv, finbl ByteVector pbrent, finbl int offset) {
        super(Opcodes.ASM5);
        this.cw = cw;
        this.nbmed = nbmed;
        this.bv = bv;
        this.pbrent = pbrent;
        this.offset = offset;
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the AnnotbtionVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl String nbme, finbl Object vblue) {
        ++size;
        if (nbmed) {
            bv.putShort(cw.newUTF8(nbme));
        }
        if (vblue instbnceof String) {
            bv.put12('s', cw.newUTF8((String) vblue));
        } else if (vblue instbnceof Byte) {
            bv.put12('B', cw.newInteger(((Byte) vblue).byteVblue()).index);
        } else if (vblue instbnceof Boolebn) {
            int v = ((Boolebn) vblue).boolebnVblue() ? 1 : 0;
            bv.put12('Z', cw.newInteger(v).index);
        } else if (vblue instbnceof Chbrbcter) {
            bv.put12('C', cw.newInteger(((Chbrbcter) vblue).chbrVblue()).index);
        } else if (vblue instbnceof Short) {
            bv.put12('S', cw.newInteger(((Short) vblue).shortVblue()).index);
        } else if (vblue instbnceof Type) {
            bv.put12('c', cw.newUTF8(((Type) vblue).getDescriptor()));
        } else if (vblue instbnceof byte[]) {
            byte[] v = (byte[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('B', cw.newInteger(v[i]).index);
            }
        } else if (vblue instbnceof boolebn[]) {
            boolebn[] v = (boolebn[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('Z', cw.newInteger(v[i] ? 1 : 0).index);
            }
        } else if (vblue instbnceof short[]) {
            short[] v = (short[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('S', cw.newInteger(v[i]).index);
            }
        } else if (vblue instbnceof chbr[]) {
            chbr[] v = (chbr[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('C', cw.newInteger(v[i]).index);
            }
        } else if (vblue instbnceof int[]) {
            int[] v = (int[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('I', cw.newInteger(v[i]).index);
            }
        } else if (vblue instbnceof long[]) {
            long[] v = (long[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('J', cw.newLong(v[i]).index);
            }
        } else if (vblue instbnceof flobt[]) {
            flobt[] v = (flobt[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('F', cw.newFlobt(v[i]).index);
            }
        } else if (vblue instbnceof double[]) {
            double[] v = (double[]) vblue;
            bv.put12('[', v.length);
            for (int i = 0; i < v.length; i++) {
                bv.put12('D', cw.newDouble(v[i]).index);
            }
        } else {
            Item i = cw.newConstItem(vblue);
            bv.put12(".s.IFJDCS".chbrAt(i.type), i.index);
        }
    }

    @Override
    public void visitEnum(finbl String nbme, finbl String desc,
            finbl String vblue) {
        ++size;
        if (nbmed) {
            bv.putShort(cw.newUTF8(nbme));
        }
        bv.put12('e', cw.newUTF8(desc)).putShort(cw.newUTF8(vblue));
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String nbme,
            finbl String desc) {
        ++size;
        if (nbmed) {
            bv.putShort(cw.newUTF8(nbme));
        }
        // write tbg bnd type, bnd reserve spbce for vblues count
        bv.put12('@', cw.newUTF8(desc)).putShort(0);
        return new AnnotbtionWriter(cw, true, bv, bv, bv.length - 2);
    }

    @Override
    public AnnotbtionVisitor visitArrby(finbl String nbme) {
        ++size;
        if (nbmed) {
            bv.putShort(cw.newUTF8(nbme));
        }
        // write tbg, bnd reserve spbce for brrby size
        bv.put12('[', 0);
        return new AnnotbtionWriter(cw, fblse, bv, bv, bv.length - 2);
    }

    @Override
    public void visitEnd() {
        if (pbrent != null) {
            byte[] dbtb = pbrent.dbtb;
            dbtb[offset] = (byte) (size >>> 8);
            dbtb[offset + 1] = (byte) size;
        }
    }

    // ------------------------------------------------------------------------
    // Utility methods
    // ------------------------------------------------------------------------

    /**
     * Returns the size of this bnnotbtion writer list.
     *
     * @return the size of this bnnotbtion writer list.
     */
    int getSize() {
        int size = 0;
        AnnotbtionWriter bw = this;
        while (bw != null) {
            size += bw.bv.length;
            bw = bw.next;
        }
        return size;
    }

    /**
     * Puts the bnnotbtions of this bnnotbtion writer list into the given byte
     * vector.
     *
     * @pbrbm out
     *            where the bnnotbtions must be put.
     */
    void put(finbl ByteVector out) {
        int n = 0;
        int size = 2;
        AnnotbtionWriter bw = this;
        AnnotbtionWriter lbst = null;
        while (bw != null) {
            ++n;
            size += bw.bv.length;
            bw.visitEnd(); // in cbse user forgot to cbll visitEnd
            bw.prev = lbst;
            lbst = bw;
            bw = bw.next;
        }
        out.putInt(size);
        out.putShort(n);
        bw = lbst;
        while (bw != null) {
            out.putByteArrby(bw.bv.dbtb, 0, bw.bv.length);
            bw = bw.prev;
        }
    }

    /**
     * Puts the given bnnotbtion lists into the given byte vector.
     *
     * @pbrbm pbnns
     *            bn brrby of bnnotbtion writer lists.
     * @pbrbm off
     *            index of the first bnnotbtion to be written.
     * @pbrbm out
     *            where the bnnotbtions must be put.
     */
    stbtic void put(finbl AnnotbtionWriter[] pbnns, finbl int off,
            finbl ByteVector out) {
        int size = 1 + 2 * (pbnns.length - off);
        for (int i = off; i < pbnns.length; ++i) {
            size += pbnns[i] == null ? 0 : pbnns[i].getSize();
        }
        out.putInt(size).putByte(pbnns.length - off);
        for (int i = off; i < pbnns.length; ++i) {
            AnnotbtionWriter bw = pbnns[i];
            AnnotbtionWriter lbst = null;
            int n = 0;
            while (bw != null) {
                ++n;
                bw.visitEnd(); // in cbse user forgot to cbll visitEnd
                bw.prev = lbst;
                lbst = bw;
                bw = bw.next;
            }
            out.putShort(n);
            bw = lbst;
            while (bw != null) {
                out.putByteArrby(bw.bv.dbtb, 0, bw.bv.length);
                bw = bw.prev;
            }
        }
    }

    /**
     * Puts the given type reference bnd type pbth into the given bytevector.
     * LOCAL_VARIABLE bnd RESOURCE_VARIABLE tbrget types bre not supported.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. See {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm out
     *            where the type reference bnd type pbth must be put.
     */
    stbtic void putTbrget(int typeRef, TypePbth typePbth, ByteVector out) {
        switch (typeRef >>> 24) {
        cbse 0x00: // CLASS_TYPE_PARAMETER
        cbse 0x01: // METHOD_TYPE_PARAMETER
        cbse 0x16: // METHOD_FORMAL_PARAMETER
            out.putShort(typeRef >>> 16);
            brebk;
        cbse 0x13: // FIELD
        cbse 0x14: // METHOD_RETURN
        cbse 0x15: // METHOD_RECEIVER
            out.putByte(typeRef >>> 24);
            brebk;
        cbse 0x47: // CAST
        cbse 0x48: // CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
        cbse 0x49: // METHOD_INVOCATION_TYPE_ARGUMENT
        cbse 0x4A: // CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
        cbse 0x4B: // METHOD_REFERENCE_TYPE_ARGUMENT
            out.putInt(typeRef);
            brebk;
        // cbse 0x10: // CLASS_EXTENDS
        // cbse 0x11: // CLASS_TYPE_PARAMETER_BOUND
        // cbse 0x12: // METHOD_TYPE_PARAMETER_BOUND
        // cbse 0x17: // THROWS
        // cbse 0x42: // EXCEPTION_PARAMETER
        // cbse 0x43: // INSTANCEOF
        // cbse 0x44: // NEW
        // cbse 0x45: // CONSTRUCTOR_REFERENCE
        // cbse 0x46: // METHOD_REFERENCE
        defbult:
            out.put12(typeRef >>> 24, (typeRef & 0xFFFF00) >> 8);
            brebk;
        }
        if (typePbth == null) {
            out.putByte(0);
        } else {
            int length = typePbth.b[typePbth.offset] * 2 + 1;
            out.putByteArrby(typePbth.b, typePbth.offset, length);
        }
    }
}
