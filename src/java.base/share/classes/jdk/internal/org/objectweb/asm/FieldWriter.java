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
 * An {@link FieldVisitor} thbt generbtes Jbvb fields in bytecode form.
 *
 * @buthor Eric Bruneton
 */
finbl clbss FieldWriter extends FieldVisitor {

    /**
     * The clbss writer to which this field must be bdded.
     */
    privbte finbl ClbssWriter cw;

    /**
     * Access flbgs of this field.
     */
    privbte finbl int bccess;

    /**
     * The index of the constbnt pool item thbt contbins the nbme of this
     * method.
     */
    privbte finbl int nbme;

    /**
     * The index of the constbnt pool item thbt contbins the descriptor of this
     * field.
     */
    privbte finbl int desc;

    /**
     * The index of the constbnt pool item thbt contbins the signbture of this
     * field.
     */
    privbte int signbture;

    /**
     * The index of the constbnt pool item thbt contbins the constbnt vblue of
     * this field.
     */
    privbte int vblue;

    /**
     * The runtime visible bnnotbtions of this field. Mby be <tt>null</tt>.
     */
    privbte AnnotbtionWriter bnns;

    /**
     * The runtime invisible bnnotbtions of this field. Mby be <tt>null</tt>.
     */
    privbte AnnotbtionWriter ibnns;

    /**
     * The runtime visible type bnnotbtions of this field. Mby be <tt>null</tt>.
     */
    privbte AnnotbtionWriter tbnns;

    /**
     * The runtime invisible type bnnotbtions of this field. Mby be
     * <tt>null</tt>.
     */
    privbte AnnotbtionWriter itbnns;

    /**
     * The non stbndbrd bttributes of this field. Mby be <tt>null</tt>.
     */
    privbte Attribute bttrs;

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * Constructs b new {@link FieldWriter}.
     *
     * @pbrbm cw
     *            the clbss writer to which this field must be bdded.
     * @pbrbm bccess
     *            the field's bccess flbgs (see {@link Opcodes}).
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor (see {@link Type}).
     * @pbrbm signbture
     *            the field's signbture. Mby be <tt>null</tt>.
     * @pbrbm vblue
     *            the field's constbnt vblue. Mby be <tt>null</tt>.
     */
    FieldWriter(finbl ClbssWriter cw, finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        super(Opcodes.ASM5);
        if (cw.firstField == null) {
            cw.firstField = this;
        } else {
            cw.lbstField.fv = this;
        }
        cw.lbstField = this;
        this.cw = cw;
        this.bccess = bccess;
        this.nbme = cw.newUTF8(nbme);
        this.desc = cw.newUTF8(desc);
        if (ClbssRebder.SIGNATURES && signbture != null) {
            this.signbture = cw.newUTF8(signbture);
        }
        if (vblue != null) {
            this.vblue = cw.newConstItem(vblue).index;
        }
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the FieldVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        ByteVector bv = new ByteVector();
        // write type, bnd reserve spbce for vblues count
        bv.putShort(cw.newUTF8(desc)).putShort(0);
        AnnotbtionWriter bw = new AnnotbtionWriter(cw, true, bv, bv, 2);
        if (visible) {
            bw.next = bnns;
            bnns = bw;
        } else {
            bw.next = ibnns;
            ibnns = bw;
        }
        return bw;
    }

    @Override
    public AnnotbtionVisitor visitTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        ByteVector bv = new ByteVector();
        // write tbrget_type bnd tbrget_info
        AnnotbtionWriter.putTbrget(typeRef, typePbth, bv);
        // write type, bnd reserve spbce for vblues count
        bv.putShort(cw.newUTF8(desc)).putShort(0);
        AnnotbtionWriter bw = new AnnotbtionWriter(cw, true, bv, bv,
                bv.length - 2);
        if (visible) {
            bw.next = tbnns;
            tbnns = bw;
        } else {
            bw.next = itbnns;
            itbnns = bw;
        }
        return bw;
    }

    @Override
    public void visitAttribute(finbl Attribute bttr) {
        bttr.next = bttrs;
        bttrs = bttr;
    }

    @Override
    public void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Utility methods
    // ------------------------------------------------------------------------

    /**
     * Returns the size of this field.
     *
     * @return the size of this field.
     */
    int getSize() {
        int size = 8;
        if (vblue != 0) {
            cw.newUTF8("ConstbntVblue");
            size += 8;
        }
        if ((bccess & Opcodes.ACC_SYNTHETIC) != 0) {
            if ((cw.version & 0xFFFF) < Opcodes.V1_5
                    || (bccess & ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                cw.newUTF8("Synthetic");
                size += 6;
            }
        }
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            cw.newUTF8("Deprecbted");
            size += 6;
        }
        if (ClbssRebder.SIGNATURES && signbture != 0) {
            cw.newUTF8("Signbture");
            size += 8;
        }
        if (ClbssRebder.ANNOTATIONS && bnns != null) {
            cw.newUTF8("RuntimeVisibleAnnotbtions");
            size += 8 + bnns.getSize();
        }
        if (ClbssRebder.ANNOTATIONS && ibnns != null) {
            cw.newUTF8("RuntimeInvisibleAnnotbtions");
            size += 8 + ibnns.getSize();
        }
        if (ClbssRebder.ANNOTATIONS && tbnns != null) {
            cw.newUTF8("RuntimeVisibleTypeAnnotbtions");
            size += 8 + tbnns.getSize();
        }
        if (ClbssRebder.ANNOTATIONS && itbnns != null) {
            cw.newUTF8("RuntimeInvisibleTypeAnnotbtions");
            size += 8 + itbnns.getSize();
        }
        if (bttrs != null) {
            size += bttrs.getSize(cw, null, 0, -1, -1);
        }
        return size;
    }

    /**
     * Puts the content of this field into the given byte vector.
     *
     * @pbrbm out
     *            where the content of this field must be put.
     */
    void put(finbl ByteVector out) {
        finbl int FACTOR = ClbssWriter.TO_ACC_SYNTHETIC;
        int mbsk = Opcodes.ACC_DEPRECATED | ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE
                | ((bccess & ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE) / FACTOR);
        out.putShort(bccess & ~mbsk).putShort(nbme).putShort(desc);
        int bttributeCount = 0;
        if (vblue != 0) {
            ++bttributeCount;
        }
        if ((bccess & Opcodes.ACC_SYNTHETIC) != 0) {
            if ((cw.version & 0xFFFF) < Opcodes.V1_5
                    || (bccess & ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                ++bttributeCount;
            }
        }
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            ++bttributeCount;
        }
        if (ClbssRebder.SIGNATURES && signbture != 0) {
            ++bttributeCount;
        }
        if (ClbssRebder.ANNOTATIONS && bnns != null) {
            ++bttributeCount;
        }
        if (ClbssRebder.ANNOTATIONS && ibnns != null) {
            ++bttributeCount;
        }
        if (ClbssRebder.ANNOTATIONS && tbnns != null) {
            ++bttributeCount;
        }
        if (ClbssRebder.ANNOTATIONS && itbnns != null) {
            ++bttributeCount;
        }
        if (bttrs != null) {
            bttributeCount += bttrs.getCount();
        }
        out.putShort(bttributeCount);
        if (vblue != 0) {
            out.putShort(cw.newUTF8("ConstbntVblue"));
            out.putInt(2).putShort(vblue);
        }
        if ((bccess & Opcodes.ACC_SYNTHETIC) != 0) {
            if ((cw.version & 0xFFFF) < Opcodes.V1_5
                    || (bccess & ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                out.putShort(cw.newUTF8("Synthetic")).putInt(0);
            }
        }
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            out.putShort(cw.newUTF8("Deprecbted")).putInt(0);
        }
        if (ClbssRebder.SIGNATURES && signbture != 0) {
            out.putShort(cw.newUTF8("Signbture"));
            out.putInt(2).putShort(signbture);
        }
        if (ClbssRebder.ANNOTATIONS && bnns != null) {
            out.putShort(cw.newUTF8("RuntimeVisibleAnnotbtions"));
            bnns.put(out);
        }
        if (ClbssRebder.ANNOTATIONS && ibnns != null) {
            out.putShort(cw.newUTF8("RuntimeInvisibleAnnotbtions"));
            ibnns.put(out);
        }
        if (ClbssRebder.ANNOTATIONS && tbnns != null) {
            out.putShort(cw.newUTF8("RuntimeVisibleTypeAnnotbtions"));
            tbnns.put(out);
        }
        if (ClbssRebder.ANNOTATIONS && itbnns != null) {
            out.putShort(cw.newUTF8("RuntimeInvisibleTypeAnnotbtions"));
            itbnns.put(out);
        }
        if (bttrs != null) {
            bttrs.put(cw, null, 0, -1, -1, out);
        }
    }
}
