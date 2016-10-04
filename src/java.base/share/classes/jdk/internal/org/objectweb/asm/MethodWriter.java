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
 * A {@link MethodVisitor} thbt generbtes methods in bytecode form. Ebch visit
 * method of this clbss bppends the bytecode corresponding to the visited
 * instruction to b byte vector, in the order these methods bre cblled.
 *
 * @buthor Eric Bruneton
 * @buthor Eugene Kuleshov
 */
clbss MethodWriter extends MethodVisitor {

    /**
     * Pseudo bccess flbg used to denote constructors.
     */
    stbtic finbl int ACC_CONSTRUCTOR = 0x80000;

    /**
     * Frbme hbs exbctly the sbme locbls bs the previous stbck mbp frbme bnd
     * number of stbck items is zero.
     */
    stbtic finbl int SAME_FRAME = 0; // to 63 (0-3f)

    /**
     * Frbme hbs exbctly the sbme locbls bs the previous stbck mbp frbme bnd
     * number of stbck items is 1
     */
    stbtic finbl int SAME_LOCALS_1_STACK_ITEM_FRAME = 64; // to 127 (40-7f)

    /**
     * Reserved for future use
     */
    stbtic finbl int RESERVED = 128;

    /**
     * Frbme hbs exbctly the sbme locbls bs the previous stbck mbp frbme bnd
     * number of stbck items is 1. Offset is bigger then 63;
     */
    stbtic finbl int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247; // f7

    /**
     * Frbme where current locbls bre the sbme bs the locbls in the previous
     * frbme, except thbt the k lbst locbls bre bbsent. The vblue of k is given
     * by the formulb 251-frbme_type.
     */
    stbtic finbl int CHOP_FRAME = 248; // to 250 (f8-fA)

    /**
     * Frbme hbs exbctly the sbme locbls bs the previous stbck mbp frbme bnd
     * number of stbck items is zero. Offset is bigger then 63;
     */
    stbtic finbl int SAME_FRAME_EXTENDED = 251; // fb

    /**
     * Frbme where current locbls bre the sbme bs the locbls in the previous
     * frbme, except thbt k bdditionbl locbls bre defined. The vblue of k is
     * given by the formulb frbme_type-251.
     */
    stbtic finbl int APPEND_FRAME = 252; // to 254 // fc-fe

    /**
     * Full frbme
     */
    stbtic finbl int FULL_FRAME = 255; // ff

    /**
     * Indicbtes thbt the stbck mbp frbmes must be recomputed from scrbtch. In
     * this cbse the mbximum stbck size bnd number of locbl vbribbles is blso
     * recomputed from scrbtch.
     *
     * @see #compute
     */
    privbte stbtic finbl int FRAMES = 0;

    /**
     * Indicbtes thbt the mbximum stbck size bnd number of locbl vbribbles must
     * be butombticblly computed.
     *
     * @see #compute
     */
    privbte stbtic finbl int MAXS = 1;

    /**
     * Indicbtes thbt nothing must be butombticblly computed.
     *
     * @see #compute
     */
    privbte stbtic finbl int NOTHING = 2;

    /**
     * The clbss writer to which this method must be bdded.
     */
    finbl ClbssWriter cw;

    /**
     * Access flbgs of this method.
     */
    privbte int bccess;

    /**
     * The index of the constbnt pool item thbt contbins the nbme of this
     * method.
     */
    privbte finbl int nbme;

    /**
     * The index of the constbnt pool item thbt contbins the descriptor of this
     * method.
     */
    privbte finbl int desc;

    /**
     * The descriptor of this method.
     */
    privbte finbl String descriptor;

    /**
     * The signbture of this method.
     */
    String signbture;

    /**
     * If not zero, indicbtes thbt the code of this method must be copied from
     * the ClbssRebder bssocibted to this writer in <code>cw.cr</code>. More
     * precisely, this field gives the index of the first byte to copied from
     * <code>cw.cr.b</code>.
     */
    int clbssRebderOffset;

    /**
     * If not zero, indicbtes thbt the code of this method must be copied from
     * the ClbssRebder bssocibted to this writer in <code>cw.cr</code>. More
     * precisely, this field gives the number of bytes to copied from
     * <code>cw.cr.b</code>.
     */
    int clbssRebderLength;

    /**
     * Number of exceptions thbt cbn be thrown by this method.
     */
    int exceptionCount;

    /**
     * The exceptions thbt cbn be thrown by this method. More precisely, this
     * brrby contbins the indexes of the constbnt pool items thbt contbin the
     * internbl nbmes of these exception clbsses.
     */
    int[] exceptions;

    /**
     * The bnnotbtion defbult bttribute of this method. Mby be <tt>null</tt>.
     */
    privbte ByteVector bnnd;

    /**
     * The runtime visible bnnotbtions of this method. Mby be <tt>null</tt>.
     */
    privbte AnnotbtionWriter bnns;

    /**
     * The runtime invisible bnnotbtions of this method. Mby be <tt>null</tt>.
     */
    privbte AnnotbtionWriter ibnns;

    /**
     * The runtime visible type bnnotbtions of this method. Mby be <tt>null</tt>
     * .
     */
    privbte AnnotbtionWriter tbnns;

    /**
     * The runtime invisible type bnnotbtions of this method. Mby be
     * <tt>null</tt>.
     */
    privbte AnnotbtionWriter itbnns;

    /**
     * The runtime visible pbrbmeter bnnotbtions of this method. Mby be
     * <tt>null</tt>.
     */
    privbte AnnotbtionWriter[] pbnns;

    /**
     * The runtime invisible pbrbmeter bnnotbtions of this method. Mby be
     * <tt>null</tt>.
     */
    privbte AnnotbtionWriter[] ipbnns;

    /**
     * The number of synthetic pbrbmeters of this method.
     */
    privbte int synthetics;

    /**
     * The non stbndbrd bttributes of the method.
     */
    privbte Attribute bttrs;

    /**
     * The bytecode of this method.
     */
    privbte ByteVector code = new ByteVector();

    /**
     * Mbximum stbck size of this method.
     */
    privbte int mbxStbck;

    /**
     * Mbximum number of locbl vbribbles for this method.
     */
    privbte int mbxLocbls;

    /**
     * Number of locbl vbribbles in the current stbck mbp frbme.
     */
    privbte int currentLocbls;

    /**
     * Number of stbck mbp frbmes in the StbckMbpTbble bttribute.
     */
    privbte int frbmeCount;

    /**
     * The StbckMbpTbble bttribute.
     */
    privbte ByteVector stbckMbp;

    /**
     * The offset of the lbst frbme thbt wbs written in the StbckMbpTbble
     * bttribute.
     */
    privbte int previousFrbmeOffset;

    /**
     * The lbst frbme thbt wbs written in the StbckMbpTbble bttribute.
     *
     * @see #frbme
     */
    privbte int[] previousFrbme;

    /**
     * The current stbck mbp frbme. The first element contbins the offset of the
     * instruction to which the frbme corresponds, the second element is the
     * number of locbls bnd the third one is the number of stbck elements. The
     * locbl vbribbles stbrt bt index 3 bnd bre followed by the operbnd stbck
     * vblues. In summbry frbme[0] = offset, frbme[1] = nLocbl, frbme[2] =
     * nStbck, frbme[3] = nLocbl. All types bre encoded bs integers, with the
     * sbme formbt bs the one used in {@link Lbbel}, but limited to BASE types.
     */
    privbte int[] frbme;

    /**
     * Number of elements in the exception hbndler list.
     */
    privbte int hbndlerCount;

    /**
     * The first element in the exception hbndler list.
     */
    privbte Hbndler firstHbndler;

    /**
     * The lbst element in the exception hbndler list.
     */
    privbte Hbndler lbstHbndler;

    /**
     * Number of entries in the MethodPbrbmeters bttribute.
     */
    privbte int methodPbrbmetersCount;

    /**
     * The MethodPbrbmeters bttribute.
     */
    privbte ByteVector methodPbrbmeters;

    /**
     * Number of entries in the LocblVbribbleTbble bttribute.
     */
    privbte int locblVbrCount;

    /**
     * The LocblVbribbleTbble bttribute.
     */
    privbte ByteVector locblVbr;

    /**
     * Number of entries in the LocblVbribbleTypeTbble bttribute.
     */
    privbte int locblVbrTypeCount;

    /**
     * The LocblVbribbleTypeTbble bttribute.
     */
    privbte ByteVector locblVbrType;

    /**
     * Number of entries in the LineNumberTbble bttribute.
     */
    privbte int lineNumberCount;

    /**
     * The LineNumberTbble bttribute.
     */
    privbte ByteVector lineNumber;

    /**
     * The stbrt offset of the lbst visited instruction.
     */
    privbte int lbstCodeOffset;

    /**
     * The runtime visible type bnnotbtions of the code. Mby be <tt>null</tt>.
     */
    privbte AnnotbtionWriter ctbnns;

    /**
     * The runtime invisible type bnnotbtions of the code. Mby be <tt>null</tt>.
     */
    privbte AnnotbtionWriter ictbnns;

    /**
     * The non stbndbrd bttributes of the method's code.
     */
    privbte Attribute cbttrs;

    /**
     * Indicbtes if some jump instructions bre too smbll bnd need to be resized.
     */
    privbte boolebn resize;

    /**
     * The number of subroutines in this method.
     */
    privbte int subroutines;

    // ------------------------------------------------------------------------

    /*
     * Fields for the control flow grbph bnblysis blgorithm (used to compute the
     * mbximum stbck size). A control flow grbph contbins one node per "bbsic
     * block", bnd one edge per "jump" from one bbsic block to bnother. Ebch
     * node (i.e., ebch bbsic block) is represented by the Lbbel object thbt
     * corresponds to the first instruction of this bbsic block. Ebch node blso
     * stores the list of its successors in the grbph, bs b linked list of Edge
     * objects.
     */

    /**
     * Indicbtes whbt must be butombticblly computed.
     *
     * @see #FRAMES
     * @see #MAXS
     * @see #NOTHING
     */
    privbte finbl int compute;

    /**
     * A list of lbbels. This list is the list of bbsic blocks in the method,
     * i.e. b list of Lbbel objects linked to ebch other by their
     * {@link Lbbel#successor} field, in the order they bre visited by
     * {@link MethodVisitor#visitLbbel}, bnd stbrting with the first bbsic
     * block.
     */
    privbte Lbbel lbbels;

    /**
     * The previous bbsic block.
     */
    privbte Lbbel previousBlock;

    /**
     * The current bbsic block.
     */
    privbte Lbbel currentBlock;

    /**
     * The (relbtive) stbck size bfter the lbst visited instruction. This size
     * is relbtive to the beginning of the current bbsic block, i.e., the true
     * stbck size bfter the lbst visited instruction is equbl to the
     * {@link Lbbel#inputStbckTop beginStbckSize} of the current bbsic block
     * plus <tt>stbckSize</tt>.
     */
    privbte int stbckSize;

    /**
     * The (relbtive) mbximum stbck size bfter the lbst visited instruction.
     * This size is relbtive to the beginning of the current bbsic block, i.e.,
     * the true mbximum stbck size bfter the lbst visited instruction is equbl
     * to the {@link Lbbel#inputStbckTop beginStbckSize} of the current bbsic
     * block plus <tt>stbckSize</tt>.
     */
    privbte int mbxStbckSize;

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * Constructs b new {@link MethodWriter}.
     *
     * @pbrbm cw
     *            the clbss writer in which the method must be bdded.
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type}).
     * @pbrbm signbture
     *            the method's signbture. Mby be <tt>null</tt>.
     * @pbrbm exceptions
     *            the internbl nbmes of the method's exceptions. Mby be
     *            <tt>null</tt>.
     * @pbrbm computeMbxs
     *            <tt>true</tt> if the mbximum stbck size bnd number of locbl
     *            vbribbles must be butombticblly computed.
     * @pbrbm computeFrbmes
     *            <tt>true</tt> if the stbck mbp tbbles must be recomputed from
     *            scrbtch.
     */
    MethodWriter(finbl ClbssWriter cw, finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture,
            finbl String[] exceptions, finbl boolebn computeMbxs,
            finbl boolebn computeFrbmes) {
        super(Opcodes.ASM5);
        if (cw.firstMethod == null) {
            cw.firstMethod = this;
        } else {
            cw.lbstMethod.mv = this;
        }
        cw.lbstMethod = this;
        this.cw = cw;
        this.bccess = bccess;
        if ("<init>".equbls(nbme)) {
            this.bccess |= ACC_CONSTRUCTOR;
        }
        this.nbme = cw.newUTF8(nbme);
        this.desc = cw.newUTF8(desc);
        this.descriptor = desc;
        if (ClbssRebder.SIGNATURES) {
            this.signbture = signbture;
        }
        if (exceptions != null && exceptions.length > 0) {
            exceptionCount = exceptions.length;
            this.exceptions = new int[exceptionCount];
            for (int i = 0; i < exceptionCount; ++i) {
                this.exceptions[i] = cw.newClbss(exceptions[i]);
            }
        }
        this.compute = computeFrbmes ? FRAMES : (computeMbxs ? MAXS : NOTHING);
        if (computeMbxs || computeFrbmes) {
            // updbtes mbxLocbls
            int size = Type.getArgumentsAndReturnSizes(descriptor) >> 2;
            if ((bccess & Opcodes.ACC_STATIC) != 0) {
                --size;
            }
            mbxLocbls = size;
            currentLocbls = size;
            // crebtes bnd visits the lbbel for the first bbsic block
            lbbels = new Lbbel();
            lbbels.stbtus |= Lbbel.PUSHED;
            visitLbbel(lbbels);
        }
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the MethodVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

    @Override
    public void visitPbrbmeter(String nbme, int bccess) {
        if (methodPbrbmeters == null) {
            methodPbrbmeters = new ByteVector();
        }
        ++methodPbrbmetersCount;
        methodPbrbmeters.putShort((nbme == null) ? 0 : cw.newUTF8(nbme))
                .putShort(bccess);
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtionDefbult() {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        bnnd = new ByteVector();
        return new AnnotbtionWriter(cw, fblse, bnnd, null, 0);
    }

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
    public AnnotbtionVisitor visitPbrbmeterAnnotbtion(finbl int pbrbmeter,
            finbl String desc, finbl boolebn visible) {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        ByteVector bv = new ByteVector();
        if ("Ljbvb/lbng/Synthetic;".equbls(desc)) {
            // workbround for b bug in jbvbc with synthetic pbrbmeters
            // see ClbssRebder.rebdPbrbmeterAnnotbtions
            synthetics = Mbth.mbx(synthetics, pbrbmeter + 1);
            return new AnnotbtionWriter(cw, fblse, bv, null, 0);
        }
        // write type, bnd reserve spbce for vblues count
        bv.putShort(cw.newUTF8(desc)).putShort(0);
        AnnotbtionWriter bw = new AnnotbtionWriter(cw, true, bv, bv, 2);
        if (visible) {
            if (pbnns == null) {
                pbnns = new AnnotbtionWriter[Type.getArgumentTypes(descriptor).length];
            }
            bw.next = pbnns[pbrbmeter];
            pbnns[pbrbmeter] = bw;
        } else {
            if (ipbnns == null) {
                ipbnns = new AnnotbtionWriter[Type.getArgumentTypes(descriptor).length];
            }
            bw.next = ipbnns[pbrbmeter];
            ipbnns[pbrbmeter] = bw;
        }
        return bw;
    }

    @Override
    public void visitAttribute(finbl Attribute bttr) {
        if (bttr.isCodeAttribute()) {
            bttr.next = cbttrs;
            cbttrs = bttr;
        } else {
            bttr.next = bttrs;
            bttrs = bttr;
        }
    }

    @Override
    public void visitCode() {
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        if (!ClbssRebder.FRAMES || compute == FRAMES) {
            return;
        }

        if (type == Opcodes.F_NEW) {
            if (previousFrbme == null) {
                visitImplicitFirstFrbme();
            }
            currentLocbls = nLocbl;
            int frbmeIndex = stbrtFrbme(code.length, nLocbl, nStbck);
            for (int i = 0; i < nLocbl; ++i) {
                if (locbl[i] instbnceof String) {
                    frbme[frbmeIndex++] = Frbme.OBJECT
                            | cw.bddType((String) locbl[i]);
                } else if (locbl[i] instbnceof Integer) {
                    frbme[frbmeIndex++] = ((Integer) locbl[i]).intVblue();
                } else {
                    frbme[frbmeIndex++] = Frbme.UNINITIALIZED
                            | cw.bddUninitiblizedType("",
                                    ((Lbbel) locbl[i]).position);
                }
            }
            for (int i = 0; i < nStbck; ++i) {
                if (stbck[i] instbnceof String) {
                    frbme[frbmeIndex++] = Frbme.OBJECT
                            | cw.bddType((String) stbck[i]);
                } else if (stbck[i] instbnceof Integer) {
                    frbme[frbmeIndex++] = ((Integer) stbck[i]).intVblue();
                } else {
                    frbme[frbmeIndex++] = Frbme.UNINITIALIZED
                            | cw.bddUninitiblizedType("",
                                    ((Lbbel) stbck[i]).position);
                }
            }
            endFrbme();
        } else {
            int deltb;
            if (stbckMbp == null) {
                stbckMbp = new ByteVector();
                deltb = code.length;
            } else {
                deltb = code.length - previousFrbmeOffset - 1;
                if (deltb < 0) {
                    if (type == Opcodes.F_SAME) {
                        return;
                    } else {
                        throw new IllegblStbteException();
                    }
                }
            }

            switch (type) {
            cbse Opcodes.F_FULL:
                currentLocbls = nLocbl;
                stbckMbp.putByte(FULL_FRAME).putShort(deltb).putShort(nLocbl);
                for (int i = 0; i < nLocbl; ++i) {
                    writeFrbmeType(locbl[i]);
                }
                stbckMbp.putShort(nStbck);
                for (int i = 0; i < nStbck; ++i) {
                    writeFrbmeType(stbck[i]);
                }
                brebk;
            cbse Opcodes.F_APPEND:
                currentLocbls += nLocbl;
                stbckMbp.putByte(SAME_FRAME_EXTENDED + nLocbl).putShort(deltb);
                for (int i = 0; i < nLocbl; ++i) {
                    writeFrbmeType(locbl[i]);
                }
                brebk;
            cbse Opcodes.F_CHOP:
                currentLocbls -= nLocbl;
                stbckMbp.putByte(SAME_FRAME_EXTENDED - nLocbl).putShort(deltb);
                brebk;
            cbse Opcodes.F_SAME:
                if (deltb < 64) {
                    stbckMbp.putByte(deltb);
                } else {
                    stbckMbp.putByte(SAME_FRAME_EXTENDED).putShort(deltb);
                }
                brebk;
            cbse Opcodes.F_SAME1:
                if (deltb < 64) {
                    stbckMbp.putByte(SAME_LOCALS_1_STACK_ITEM_FRAME + deltb);
                } else {
                    stbckMbp.putByte(SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED)
                            .putShort(deltb);
                }
                writeFrbmeType(stbck[0]);
                brebk;
            }

            previousFrbmeOffset = code.length;
            ++frbmeCount;
        }

        mbxStbck = Mbth.mbx(mbxStbck, nStbck);
        mbxLocbls = Mbth.mbx(mbxLocbls, currentLocbls);
    }

    @Override
    public void visitInsn(finbl int opcode) {
        lbstCodeOffset = code.length;
        // bdds the instruction to the bytecode of the method
        code.putByte(opcode);
        // updbte currentBlock
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(opcode, 0, null, null);
            } else {
                // updbtes current bnd mbx stbck sizes
                int size = stbckSize + Frbme.SIZE[opcode];
                if (size > mbxStbckSize) {
                    mbxStbckSize = size;
                }
                stbckSize = size;
            }
            // if opcode == ATHROW or xRETURN, ends current block (no successor)
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                    || opcode == Opcodes.ATHROW) {
                noSuccessor();
            }
        }
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        lbstCodeOffset = code.length;
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(opcode, operbnd, null, null);
            } else if (opcode != Opcodes.NEWARRAY) {
                // updbtes current bnd mbx stbck sizes only for NEWARRAY
                // (stbck size vbribtion = 0 for BIPUSH or SIPUSH)
                int size = stbckSize + 1;
                if (size > mbxStbckSize) {
                    mbxStbckSize = size;
                }
                stbckSize = size;
            }
        }
        // bdds the instruction to the bytecode of the method
        if (opcode == Opcodes.SIPUSH) {
            code.put12(opcode, operbnd);
        } else { // BIPUSH or NEWARRAY
            code.put11(opcode, operbnd);
        }
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        lbstCodeOffset = code.length;
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(opcode, vbr, null, null);
            } else {
                // updbtes current bnd mbx stbck sizes
                if (opcode == Opcodes.RET) {
                    // no stbck chbnge, but end of current block (no successor)
                    currentBlock.stbtus |= Lbbel.RET;
                    // sbve 'stbckSize' here for future use
                    // (see {@link #findSubroutineSuccessors})
                    currentBlock.inputStbckTop = stbckSize;
                    noSuccessor();
                } else { // xLOAD or xSTORE
                    int size = stbckSize + Frbme.SIZE[opcode];
                    if (size > mbxStbckSize) {
                        mbxStbckSize = size;
                    }
                    stbckSize = size;
                }
            }
        }
        if (compute != NOTHING) {
            // updbtes mbx locbls
            int n;
            if (opcode == Opcodes.LLOAD || opcode == Opcodes.DLOAD
                    || opcode == Opcodes.LSTORE || opcode == Opcodes.DSTORE) {
                n = vbr + 2;
            } else {
                n = vbr + 1;
            }
            if (n > mbxLocbls) {
                mbxLocbls = n;
            }
        }
        // bdds the instruction to the bytecode of the method
        if (vbr < 4 && opcode != Opcodes.RET) {
            int opt;
            if (opcode < Opcodes.ISTORE) {
                /* ILOAD_0 */
                opt = 26 + ((opcode - Opcodes.ILOAD) << 2) + vbr;
            } else {
                /* ISTORE_0 */
                opt = 59 + ((opcode - Opcodes.ISTORE) << 2) + vbr;
            }
            code.putByte(opt);
        } else if (vbr >= 256) {
            code.putByte(196 /* WIDE */).put12(opcode, vbr);
        } else {
            code.put11(opcode, vbr);
        }
        if (opcode >= Opcodes.ISTORE && compute == FRAMES && hbndlerCount > 0) {
            visitLbbel(new Lbbel());
        }
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        lbstCodeOffset = code.length;
        Item i = cw.newClbssItem(type);
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(opcode, code.length, cw, i);
            } else if (opcode == Opcodes.NEW) {
                // updbtes current bnd mbx stbck sizes only if opcode == NEW
                // (no stbck chbnge for ANEWARRAY, CHECKCAST, INSTANCEOF)
                int size = stbckSize + 1;
                if (size > mbxStbckSize) {
                    mbxStbckSize = size;
                }
                stbckSize = size;
            }
        }
        // bdds the instruction to the bytecode of the method
        code.put12(opcode, i.index);
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        lbstCodeOffset = code.length;
        Item i = cw.newFieldItem(owner, nbme, desc);
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(opcode, 0, cw, i);
            } else {
                int size;
                // computes the stbck size vbribtion
                chbr c = desc.chbrAt(0);
                switch (opcode) {
                cbse Opcodes.GETSTATIC:
                    size = stbckSize + (c == 'D' || c == 'J' ? 2 : 1);
                    brebk;
                cbse Opcodes.PUTSTATIC:
                    size = stbckSize + (c == 'D' || c == 'J' ? -2 : -1);
                    brebk;
                cbse Opcodes.GETFIELD:
                    size = stbckSize + (c == 'D' || c == 'J' ? 1 : 0);
                    brebk;
                // cbse Constbnts.PUTFIELD:
                defbult:
                    size = stbckSize + (c == 'D' || c == 'J' ? -3 : -2);
                    brebk;
                }
                // updbtes current bnd mbx stbck sizes
                if (size > mbxStbckSize) {
                    mbxStbckSize = size;
                }
                stbckSize = size;
            }
        }
        // bdds the instruction to the bytecode of the method
        code.put12(opcode, i.index);
    }

    @Override
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        lbstCodeOffset = code.length;
        Item i = cw.newMethodItem(owner, nbme, desc, itf);
        int brgSize = i.intVbl;
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(opcode, 0, cw, i);
            } else {
                /*
                 * computes the stbck size vbribtion. In order not to recompute
                 * severbl times this vbribtion for the sbme Item, we use the
                 * intVbl field of this item to store this vbribtion, once it
                 * hbs been computed. More precisely this intVbl field stores
                 * the sizes of the brguments bnd of the return vblue
                 * corresponding to desc.
                 */
                if (brgSize == 0) {
                    // the bbove sizes hbve not been computed yet,
                    // so we compute them...
                    brgSize = Type.getArgumentsAndReturnSizes(desc);
                    // ... bnd we sbve them in order
                    // not to recompute them in the future
                    i.intVbl = brgSize;
                }
                int size;
                if (opcode == Opcodes.INVOKESTATIC) {
                    size = stbckSize - (brgSize >> 2) + (brgSize & 0x03) + 1;
                } else {
                    size = stbckSize - (brgSize >> 2) + (brgSize & 0x03);
                }
                // updbtes current bnd mbx stbck sizes
                if (size > mbxStbckSize) {
                    mbxStbckSize = size;
                }
                stbckSize = size;
            }
        }
        // bdds the instruction to the bytecode of the method
        if (opcode == Opcodes.INVOKEINTERFACE) {
            if (brgSize == 0) {
                brgSize = Type.getArgumentsAndReturnSizes(desc);
                i.intVbl = brgSize;
            }
            code.put12(Opcodes.INVOKEINTERFACE, i.index).put11(brgSize >> 2, 0);
        } else {
            code.put12(opcode, i.index);
        }
    }

    @Override
    public void visitInvokeDynbmicInsn(finbl String nbme, finbl String desc,
            finbl Hbndle bsm, finbl Object... bsmArgs) {
        lbstCodeOffset = code.length;
        Item i = cw.newInvokeDynbmicItem(nbme, desc, bsm, bsmArgs);
        int brgSize = i.intVbl;
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(Opcodes.INVOKEDYNAMIC, 0, cw, i);
            } else {
                /*
                 * computes the stbck size vbribtion. In order not to recompute
                 * severbl times this vbribtion for the sbme Item, we use the
                 * intVbl field of this item to store this vbribtion, once it
                 * hbs been computed. More precisely this intVbl field stores
                 * the sizes of the brguments bnd of the return vblue
                 * corresponding to desc.
                 */
                if (brgSize == 0) {
                    // the bbove sizes hbve not been computed yet,
                    // so we compute them...
                    brgSize = Type.getArgumentsAndReturnSizes(desc);
                    // ... bnd we sbve them in order
                    // not to recompute them in the future
                    i.intVbl = brgSize;
                }
                int size = stbckSize - (brgSize >> 2) + (brgSize & 0x03) + 1;

                // updbtes current bnd mbx stbck sizes
                if (size > mbxStbckSize) {
                    mbxStbckSize = size;
                }
                stbckSize = size;
            }
        }
        // bdds the instruction to the bytecode of the method
        code.put12(Opcodes.INVOKEDYNAMIC, i.index);
        code.putShort(0);
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        lbstCodeOffset = code.length;
        Lbbel nextInsn = null;
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(opcode, 0, null, null);
                // 'lbbel' is the tbrget of b jump instruction
                lbbel.getFirst().stbtus |= Lbbel.TARGET;
                // bdds 'lbbel' bs b successor of this bbsic block
                bddSuccessor(Edge.NORMAL, lbbel);
                if (opcode != Opcodes.GOTO) {
                    // crebtes b Lbbel for the next bbsic block
                    nextInsn = new Lbbel();
                }
            } else {
                if (opcode == Opcodes.JSR) {
                    if ((lbbel.stbtus & Lbbel.SUBROUTINE) == 0) {
                        lbbel.stbtus |= Lbbel.SUBROUTINE;
                        ++subroutines;
                    }
                    currentBlock.stbtus |= Lbbel.JSR;
                    bddSuccessor(stbckSize + 1, lbbel);
                    // crebtes b Lbbel for the next bbsic block
                    nextInsn = new Lbbel();
                    /*
                     * note thbt, by construction in this method, b JSR block
                     * hbs bt lebst two successors in the control flow grbph:
                     * the first one lebds the next instruction bfter the JSR,
                     * while the second one lebds to the JSR tbrget.
                     */
                } else {
                    // updbtes current stbck size (mbx stbck size unchbnged
                    // becbuse stbck size vbribtion blwbys negbtive in this
                    // cbse)
                    stbckSize += Frbme.SIZE[opcode];
                    bddSuccessor(stbckSize, lbbel);
                }
            }
        }
        // bdds the instruction to the bytecode of the method
        if ((lbbel.stbtus & Lbbel.RESOLVED) != 0
                && lbbel.position - code.length < Short.MIN_VALUE) {
            /*
             * cbse of b bbckwbrd jump with bn offset < -32768. In this cbse we
             * butombticblly replbce GOTO with GOTO_W, JSR with JSR_W bnd IFxxx
             * <l> with IFNOTxxx <l'> GOTO_W <l>, where IFNOTxxx is the
             * "opposite" opcode of IFxxx (i.e., IFNE for IFEQ) bnd where <l'>
             * designbtes the instruction just bfter the GOTO_W.
             */
            if (opcode == Opcodes.GOTO) {
                code.putByte(200); // GOTO_W
            } else if (opcode == Opcodes.JSR) {
                code.putByte(201); // JSR_W
            } else {
                // if the IF instruction is trbnsformed into IFNOT GOTO_W the
                // next instruction becomes the tbrget of the IFNOT instruction
                if (nextInsn != null) {
                    nextInsn.stbtus |= Lbbel.TARGET;
                }
                code.putByte(opcode <= 166 ? ((opcode + 1) ^ 1) - 1
                        : opcode ^ 1);
                code.putShort(8); // jump offset
                code.putByte(200); // GOTO_W
            }
            lbbel.put(this, code, code.length - 1, true);
        } else {
            /*
             * cbse of b bbckwbrd jump with bn offset >= -32768, or of b forwbrd
             * jump with, of course, bn unknown offset. In these cbses we store
             * the offset in 2 bytes (which will be increbsed in
             * resizeInstructions, if needed).
             */
            code.putByte(opcode);
            lbbel.put(this, code, code.length - 1, fblse);
        }
        if (currentBlock != null) {
            if (nextInsn != null) {
                // if the jump instruction is not b GOTO, the next instruction
                // is blso b successor of this instruction. Cblling visitLbbel
                // bdds the lbbel of this next instruction bs b successor of the
                // current block, bnd stbrts b new bbsic block
                visitLbbel(nextInsn);
            }
            if (opcode == Opcodes.GOTO) {
                noSuccessor();
            }
        }
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        // resolves previous forwbrd references to lbbel, if bny
        resize |= lbbel.resolve(this, code.length, code.dbtb);
        // updbtes currentBlock
        if ((lbbel.stbtus & Lbbel.DEBUG) != 0) {
            return;
        }
        if (compute == FRAMES) {
            if (currentBlock != null) {
                if (lbbel.position == currentBlock.position) {
                    // successive lbbels, do not stbrt b new bbsic block
                    currentBlock.stbtus |= (lbbel.stbtus & Lbbel.TARGET);
                    lbbel.frbme = currentBlock.frbme;
                    return;
                }
                // ends current block (with one new successor)
                bddSuccessor(Edge.NORMAL, lbbel);
            }
            // begins b new current block
            currentBlock = lbbel;
            if (lbbel.frbme == null) {
                lbbel.frbme = new Frbme();
                lbbel.frbme.owner = lbbel;
            }
            // updbtes the bbsic block list
            if (previousBlock != null) {
                if (lbbel.position == previousBlock.position) {
                    previousBlock.stbtus |= (lbbel.stbtus & Lbbel.TARGET);
                    lbbel.frbme = previousBlock.frbme;
                    currentBlock = previousBlock;
                    return;
                }
                previousBlock.successor = lbbel;
            }
            previousBlock = lbbel;
        } else if (compute == MAXS) {
            if (currentBlock != null) {
                // ends current block (with one new successor)
                currentBlock.outputStbckMbx = mbxStbckSize;
                bddSuccessor(stbckSize, lbbel);
            }
            // begins b new current block
            currentBlock = lbbel;
            // resets the relbtive current bnd mbx stbck sizes
            stbckSize = 0;
            mbxStbckSize = 0;
            // updbtes the bbsic block list
            if (previousBlock != null) {
                previousBlock.successor = lbbel;
            }
            previousBlock = lbbel;
        }
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        lbstCodeOffset = code.length;
        Item i = cw.newConstItem(cst);
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(Opcodes.LDC, 0, cw, i);
            } else {
                int size;
                // computes the stbck size vbribtion
                if (i.type == ClbssWriter.LONG || i.type == ClbssWriter.DOUBLE) {
                    size = stbckSize + 2;
                } else {
                    size = stbckSize + 1;
                }
                // updbtes current bnd mbx stbck sizes
                if (size > mbxStbckSize) {
                    mbxStbckSize = size;
                }
                stbckSize = size;
            }
        }
        // bdds the instruction to the bytecode of the method
        int index = i.index;
        if (i.type == ClbssWriter.LONG || i.type == ClbssWriter.DOUBLE) {
            code.put12(20 /* LDC2_W */, index);
        } else if (index >= 256) {
            code.put12(19 /* LDC_W */, index);
        } else {
            code.put11(Opcodes.LDC, index);
        }
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        lbstCodeOffset = code.length;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(Opcodes.IINC, vbr, null, null);
            }
        }
        if (compute != NOTHING) {
            // updbtes mbx locbls
            int n = vbr + 1;
            if (n > mbxLocbls) {
                mbxLocbls = n;
            }
        }
        // bdds the instruction to the bytecode of the method
        if ((vbr > 255) || (increment > 127) || (increment < -128)) {
            code.putByte(196 /* WIDE */).put12(Opcodes.IINC, vbr)
                    .putShort(increment);
        } else {
            code.putByte(Opcodes.IINC).put11(vbr, increment);
        }
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        lbstCodeOffset = code.length;
        // bdds the instruction to the bytecode of the method
        int source = code.length;
        code.putByte(Opcodes.TABLESWITCH);
        code.putByteArrby(null, 0, (4 - code.length % 4) % 4);
        dflt.put(this, code, source, true);
        code.putInt(min).putInt(mbx);
        for (int i = 0; i < lbbels.length; ++i) {
            lbbels[i].put(this, code, source, true);
        }
        // updbtes currentBlock
        visitSwitchInsn(dflt, lbbels);
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        lbstCodeOffset = code.length;
        // bdds the instruction to the bytecode of the method
        int source = code.length;
        code.putByte(Opcodes.LOOKUPSWITCH);
        code.putByteArrby(null, 0, (4 - code.length % 4) % 4);
        dflt.put(this, code, source, true);
        code.putInt(lbbels.length);
        for (int i = 0; i < lbbels.length; ++i) {
            code.putInt(keys[i]);
            lbbels[i].put(this, code, source, true);
        }
        // updbtes currentBlock
        visitSwitchInsn(dflt, lbbels);
    }

    privbte void visitSwitchInsn(finbl Lbbel dflt, finbl Lbbel[] lbbels) {
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(Opcodes.LOOKUPSWITCH, 0, null, null);
                // bdds current block successors
                bddSuccessor(Edge.NORMAL, dflt);
                dflt.getFirst().stbtus |= Lbbel.TARGET;
                for (int i = 0; i < lbbels.length; ++i) {
                    bddSuccessor(Edge.NORMAL, lbbels[i]);
                    lbbels[i].getFirst().stbtus |= Lbbel.TARGET;
                }
            } else {
                // updbtes current stbck size (mbx stbck size unchbnged)
                --stbckSize;
                // bdds current block successors
                bddSuccessor(stbckSize, dflt);
                for (int i = 0; i < lbbels.length; ++i) {
                    bddSuccessor(stbckSize, lbbels[i]);
                }
            }
            // ends current block
            noSuccessor();
        }
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        lbstCodeOffset = code.length;
        Item i = cw.newClbssItem(desc);
        // Lbbel currentBlock = this.currentBlock;
        if (currentBlock != null) {
            if (compute == FRAMES) {
                currentBlock.frbme.execute(Opcodes.MULTIANEWARRAY, dims, cw, i);
            } else {
                // updbtes current stbck size (mbx stbck size unchbnged becbuse
                // stbck size vbribtion blwbys negbtive or null)
                stbckSize += 1 - dims;
            }
        }
        // bdds the instruction to the bytecode of the method
        code.put12(Opcodes.MULTIANEWARRAY, i.index).putByte(dims);
    }

    @Override
    public AnnotbtionVisitor visitInsnAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        ByteVector bv = new ByteVector();
        // write tbrget_type bnd tbrget_info
        typeRef = (typeRef & 0xFF0000FF) | (lbstCodeOffset << 8);
        AnnotbtionWriter.putTbrget(typeRef, typePbth, bv);
        // write type, bnd reserve spbce for vblues count
        bv.putShort(cw.newUTF8(desc)).putShort(0);
        AnnotbtionWriter bw = new AnnotbtionWriter(cw, true, bv, bv,
                bv.length - 2);
        if (visible) {
            bw.next = ctbnns;
            ctbnns = bw;
        } else {
            bw.next = ictbnns;
            ictbnns = bw;
        }
        return bw;
    }

    @Override
    public void visitTryCbtchBlock(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Lbbel hbndler, finbl String type) {
        ++hbndlerCount;
        Hbndler h = new Hbndler();
        h.stbrt = stbrt;
        h.end = end;
        h.hbndler = hbndler;
        h.desc = type;
        h.type = type != null ? cw.newClbss(type) : 0;
        if (lbstHbndler == null) {
            firstHbndler = h;
        } else {
            lbstHbndler.next = h;
        }
        lbstHbndler = h;
    }

    @Override
    public AnnotbtionVisitor visitTryCbtchAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
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
            bw.next = ctbnns;
            ctbnns = bw;
        } else {
            bw.next = ictbnns;
            ictbnns = bw;
        }
        return bw;
    }

    @Override
    public void visitLocblVbribble(finbl String nbme, finbl String desc,
            finbl String signbture, finbl Lbbel stbrt, finbl Lbbel end,
            finbl int index) {
        if (signbture != null) {
            if (locblVbrType == null) {
                locblVbrType = new ByteVector();
            }
            ++locblVbrTypeCount;
            locblVbrType.putShort(stbrt.position)
                    .putShort(end.position - stbrt.position)
                    .putShort(cw.newUTF8(nbme)).putShort(cw.newUTF8(signbture))
                    .putShort(index);
        }
        if (locblVbr == null) {
            locblVbr = new ByteVector();
        }
        ++locblVbrCount;
        locblVbr.putShort(stbrt.position)
                .putShort(end.position - stbrt.position)
                .putShort(cw.newUTF8(nbme)).putShort(cw.newUTF8(desc))
                .putShort(index);
        if (compute != NOTHING) {
            // updbtes mbx locbls
            chbr c = desc.chbrAt(0);
            int n = index + (c == 'J' || c == 'D' ? 2 : 1);
            if (n > mbxLocbls) {
                mbxLocbls = n;
            }
        }
    }

    @Override
    public AnnotbtionVisitor visitLocblVbribbleAnnotbtion(int typeRef,
            TypePbth typePbth, Lbbel[] stbrt, Lbbel[] end, int[] index,
            String desc, boolebn visible) {
        if (!ClbssRebder.ANNOTATIONS) {
            return null;
        }
        ByteVector bv = new ByteVector();
        // write tbrget_type bnd tbrget_info
        bv.putByte(typeRef >>> 24).putShort(stbrt.length);
        for (int i = 0; i < stbrt.length; ++i) {
            bv.putShort(stbrt[i].position)
                    .putShort(end[i].position - stbrt[i].position)
                    .putShort(index[i]);
        }
        if (typePbth == null) {
            bv.putByte(0);
        } else {
            int length = typePbth.b[typePbth.offset] * 2 + 1;
            bv.putByteArrby(typePbth.b, typePbth.offset, length);
        }
        // write type, bnd reserve spbce for vblues count
        bv.putShort(cw.newUTF8(desc)).putShort(0);
        AnnotbtionWriter bw = new AnnotbtionWriter(cw, true, bv, bv,
                bv.length - 2);
        if (visible) {
            bw.next = ctbnns;
            ctbnns = bw;
        } else {
            bw.next = ictbnns;
            ictbnns = bw;
        }
        return bw;
    }

    @Override
    public void visitLineNumber(finbl int line, finbl Lbbel stbrt) {
        if (lineNumber == null) {
            lineNumber = new ByteVector();
        }
        ++lineNumberCount;
        lineNumber.putShort(stbrt.position);
        lineNumber.putShort(line);
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        if (resize) {
            // replbces the temporbry jump opcodes introduced by Lbbel.resolve.
            if (ClbssRebder.RESIZE) {
                resizeInstructions();
            } else {
                throw new RuntimeException("Method code too lbrge!");
            }
        }
        if (ClbssRebder.FRAMES && compute == FRAMES) {
            // completes the control flow grbph with exception hbndler blocks
            Hbndler hbndler = firstHbndler;
            while (hbndler != null) {
                Lbbel l = hbndler.stbrt.getFirst();
                Lbbel h = hbndler.hbndler.getFirst();
                Lbbel e = hbndler.end.getFirst();
                // computes the kind of the edges to 'h'
                String t = hbndler.desc == null ? "jbvb/lbng/Throwbble"
                        : hbndler.desc;
                int kind = Frbme.OBJECT | cw.bddType(t);
                // h is bn exception hbndler
                h.stbtus |= Lbbel.TARGET;
                // bdds 'h' bs b successor of lbbels between 'stbrt' bnd 'end'
                while (l != e) {
                    // crebtes bn edge to 'h'
                    Edge b = new Edge();
                    b.info = kind;
                    b.successor = h;
                    // bdds it to the successors of 'l'
                    b.next = l.successors;
                    l.successors = b;
                    // goes to the next lbbel
                    l = l.successor;
                }
                hbndler = hbndler.next;
            }

            // crebtes bnd visits the first (implicit) frbme
            Frbme f = lbbels.frbme;
            Type[] brgs = Type.getArgumentTypes(descriptor);
            f.initInputFrbme(cw, bccess, brgs, this.mbxLocbls);
            visitFrbme(f);

            /*
             * fix point blgorithm: mbrk the first bbsic block bs 'chbnged'
             * (i.e. put it in the 'chbnged' list) bnd, while there bre chbnged
             * bbsic blocks, choose one, mbrk it bs unchbnged, bnd updbte its
             * successors (which cbn be chbnged in the process).
             */
            int mbx = 0;
            Lbbel chbnged = lbbels;
            while (chbnged != null) {
                // removes b bbsic block from the list of chbnged bbsic blocks
                Lbbel l = chbnged;
                chbnged = chbnged.next;
                l.next = null;
                f = l.frbme;
                // b rebchbble jump tbrget must be stored in the stbck mbp
                if ((l.stbtus & Lbbel.TARGET) != 0) {
                    l.stbtus |= Lbbel.STORE;
                }
                // bll visited lbbels bre rebchbble, by definition
                l.stbtus |= Lbbel.REACHABLE;
                // updbtes the (bbsolute) mbximum stbck size
                int blockMbx = f.inputStbck.length + l.outputStbckMbx;
                if (blockMbx > mbx) {
                    mbx = blockMbx;
                }
                // updbtes the successors of the current bbsic block
                Edge e = l.successors;
                while (e != null) {
                    Lbbel n = e.successor.getFirst();
                    boolebn chbnge = f.merge(cw, n.frbme, e.info);
                    if (chbnge && n.next == null) {
                        // if n hbs chbnged bnd is not blrebdy in the 'chbnged'
                        // list, bdds it to this list
                        n.next = chbnged;
                        chbnged = n;
                    }
                    e = e.next;
                }
            }

            // visits bll the frbmes thbt must be stored in the stbck mbp
            Lbbel l = lbbels;
            while (l != null) {
                f = l.frbme;
                if ((l.stbtus & Lbbel.STORE) != 0) {
                    visitFrbme(f);
                }
                if ((l.stbtus & Lbbel.REACHABLE) == 0) {
                    // finds stbrt bnd end of debd bbsic block
                    Lbbel k = l.successor;
                    int stbrt = l.position;
                    int end = (k == null ? code.length : k.position) - 1;
                    // if non empty bbsic block
                    if (end >= stbrt) {
                        mbx = Mbth.mbx(mbx, 1);
                        // replbces instructions with NOP ... NOP ATHROW
                        for (int i = stbrt; i < end; ++i) {
                            code.dbtb[i] = Opcodes.NOP;
                        }
                        code.dbtb[end] = (byte) Opcodes.ATHROW;
                        // emits b frbme for this unrebchbble block
                        int frbmeIndex = stbrtFrbme(stbrt, 0, 1);
                        frbme[frbmeIndex] = Frbme.OBJECT
                                | cw.bddType("jbvb/lbng/Throwbble");
                        endFrbme();
                        // removes the stbrt-end rbnge from the exception
                        // hbndlers
                        firstHbndler = Hbndler.remove(firstHbndler, l, k);
                    }
                }
                l = l.successor;
            }

            hbndler = firstHbndler;
            hbndlerCount = 0;
            while (hbndler != null) {
                hbndlerCount += 1;
                hbndler = hbndler.next;
            }

            this.mbxStbck = mbx;
        } else if (compute == MAXS) {
            // completes the control flow grbph with exception hbndler blocks
            Hbndler hbndler = firstHbndler;
            while (hbndler != null) {
                Lbbel l = hbndler.stbrt;
                Lbbel h = hbndler.hbndler;
                Lbbel e = hbndler.end;
                // bdds 'h' bs b successor of lbbels between 'stbrt' bnd 'end'
                while (l != e) {
                    // crebtes bn edge to 'h'
                    Edge b = new Edge();
                    b.info = Edge.EXCEPTION;
                    b.successor = h;
                    // bdds it to the successors of 'l'
                    if ((l.stbtus & Lbbel.JSR) == 0) {
                        b.next = l.successors;
                        l.successors = b;
                    } else {
                        // if l is b JSR block, bdds b bfter the first two edges
                        // to preserve the hypothesis bbout JSR block successors
                        // order (see {@link #visitJumpInsn})
                        b.next = l.successors.next.next;
                        l.successors.next.next = b;
                    }
                    // goes to the next lbbel
                    l = l.successor;
                }
                hbndler = hbndler.next;
            }

            if (subroutines > 0) {
                // completes the control flow grbph with the RET successors
                /*
                 * first step: finds the subroutines. This step determines, for
                 * ebch bbsic block, to which subroutine(s) it belongs.
                 */
                // finds the bbsic blocks thbt belong to the "mbin" subroutine
                int id = 0;
                lbbels.visitSubroutine(null, 1, subroutines);
                // finds the bbsic blocks thbt belong to the rebl subroutines
                Lbbel l = lbbels;
                while (l != null) {
                    if ((l.stbtus & Lbbel.JSR) != 0) {
                        // the subroutine is defined by l's TARGET, not by l
                        Lbbel subroutine = l.successors.next.successor;
                        // if this subroutine hbs not been visited yet...
                        if ((subroutine.stbtus & Lbbel.VISITED) == 0) {
                            // ...bssigns it b new id bnd finds its bbsic blocks
                            id += 1;
                            subroutine.visitSubroutine(null, (id / 32L) << 32
                                    | (1L << (id % 32)), subroutines);
                        }
                    }
                    l = l.successor;
                }
                // second step: finds the successors of RET blocks
                l = lbbels;
                while (l != null) {
                    if ((l.stbtus & Lbbel.JSR) != 0) {
                        Lbbel L = lbbels;
                        while (L != null) {
                            L.stbtus &= ~Lbbel.VISITED2;
                            L = L.successor;
                        }
                        // the subroutine is defined by l's TARGET, not by l
                        Lbbel subroutine = l.successors.next.successor;
                        subroutine.visitSubroutine(l, 0, subroutines);
                    }
                    l = l.successor;
                }
            }

            /*
             * control flow bnblysis blgorithm: while the block stbck is not
             * empty, pop b block from this stbck, updbte the mbx stbck size,
             * compute the true (non relbtive) begin stbck size of the
             * successors of this block, bnd push these successors onto the
             * stbck (unless they hbve blrebdy been pushed onto the stbck).
             * Note: by hypothesis, the {@link Lbbel#inputStbckTop} of the
             * blocks in the block stbck bre the true (non relbtive) beginning
             * stbck sizes of these blocks.
             */
            int mbx = 0;
            Lbbel stbck = lbbels;
            while (stbck != null) {
                // pops b block from the stbck
                Lbbel l = stbck;
                stbck = stbck.next;
                // computes the true (non relbtive) mbx stbck size of this block
                int stbrt = l.inputStbckTop;
                int blockMbx = stbrt + l.outputStbckMbx;
                // updbtes the globbl mbx stbck size
                if (blockMbx > mbx) {
                    mbx = blockMbx;
                }
                // bnblyzes the successors of the block
                Edge b = l.successors;
                if ((l.stbtus & Lbbel.JSR) != 0) {
                    // ignores the first edge of JSR blocks (virtubl successor)
                    b = b.next;
                }
                while (b != null) {
                    l = b.successor;
                    // if this successor hbs not blrebdy been pushed...
                    if ((l.stbtus & Lbbel.PUSHED) == 0) {
                        // computes its true beginning stbck size...
                        l.inputStbckTop = b.info == Edge.EXCEPTION ? 1 : stbrt
                                + b.info;
                        // ...bnd pushes it onto the stbck
                        l.stbtus |= Lbbel.PUSHED;
                        l.next = stbck;
                        stbck = l;
                    }
                    b = b.next;
                }
            }
            this.mbxStbck = Mbth.mbx(mbxStbck, mbx);
        } else {
            this.mbxStbck = mbxStbck;
            this.mbxLocbls = mbxLocbls;
        }
    }

    @Override
    public void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Utility methods: control flow bnblysis blgorithm
    // ------------------------------------------------------------------------

    /**
     * Adds b successor to the {@link #currentBlock currentBlock} block.
     *
     * @pbrbm info
     *            informbtion bbout the control flow edge to be bdded.
     * @pbrbm successor
     *            the successor block to be bdded to the current block.
     */
    privbte void bddSuccessor(finbl int info, finbl Lbbel successor) {
        // crebtes bnd initiblizes bn Edge object...
        Edge b = new Edge();
        b.info = info;
        b.successor = successor;
        // ...bnd bdds it to the successor list of the currentBlock block
        b.next = currentBlock.successors;
        currentBlock.successors = b;
    }

    /**
     * Ends the current bbsic block. This method must be used in the cbse where
     * the current bbsic block does not hbve bny successor.
     */
    privbte void noSuccessor() {
        if (compute == FRAMES) {
            Lbbel l = new Lbbel();
            l.frbme = new Frbme();
            l.frbme.owner = l;
            l.resolve(this, code.length, code.dbtb);
            previousBlock.successor = l;
            previousBlock = l;
        } else {
            currentBlock.outputStbckMbx = mbxStbckSize;
        }
        currentBlock = null;
    }

    // ------------------------------------------------------------------------
    // Utility methods: stbck mbp frbmes
    // ------------------------------------------------------------------------

    /**
     * Visits b frbme thbt hbs been computed from scrbtch.
     *
     * @pbrbm f
     *            the frbme thbt must be visited.
     */
    privbte void visitFrbme(finbl Frbme f) {
        int i, t;
        int nTop = 0;
        int nLocbl = 0;
        int nStbck = 0;
        int[] locbls = f.inputLocbls;
        int[] stbcks = f.inputStbck;
        // computes the number of locbls (ignores TOP types thbt bre just bfter
        // b LONG or b DOUBLE, bnd bll trbiling TOP types)
        for (i = 0; i < locbls.length; ++i) {
            t = locbls[i];
            if (t == Frbme.TOP) {
                ++nTop;
            } else {
                nLocbl += nTop + 1;
                nTop = 0;
            }
            if (t == Frbme.LONG || t == Frbme.DOUBLE) {
                ++i;
            }
        }
        // computes the stbck size (ignores TOP types thbt bre just bfter
        // b LONG or b DOUBLE)
        for (i = 0; i < stbcks.length; ++i) {
            t = stbcks[i];
            ++nStbck;
            if (t == Frbme.LONG || t == Frbme.DOUBLE) {
                ++i;
            }
        }
        // visits the frbme bnd its content
        int frbmeIndex = stbrtFrbme(f.owner.position, nLocbl, nStbck);
        for (i = 0; nLocbl > 0; ++i, --nLocbl) {
            t = locbls[i];
            frbme[frbmeIndex++] = t;
            if (t == Frbme.LONG || t == Frbme.DOUBLE) {
                ++i;
            }
        }
        for (i = 0; i < stbcks.length; ++i) {
            t = stbcks[i];
            frbme[frbmeIndex++] = t;
            if (t == Frbme.LONG || t == Frbme.DOUBLE) {
                ++i;
            }
        }
        endFrbme();
    }

    /**
     * Visit the implicit first frbme of this method.
     */
    privbte void visitImplicitFirstFrbme() {
        // There cbn be bt most descriptor.length() + 1 locbls
        int frbmeIndex = stbrtFrbme(0, descriptor.length() + 1, 0);
        if ((bccess & Opcodes.ACC_STATIC) == 0) {
            if ((bccess & ACC_CONSTRUCTOR) == 0) {
                frbme[frbmeIndex++] = Frbme.OBJECT | cw.bddType(cw.thisNbme);
            } else {
                frbme[frbmeIndex++] = 6; // Opcodes.UNINITIALIZED_THIS;
            }
        }
        int i = 1;
        loop: while (true) {
            int j = i;
            switch (descriptor.chbrAt(i++)) {
            cbse 'Z':
            cbse 'C':
            cbse 'B':
            cbse 'S':
            cbse 'I':
                frbme[frbmeIndex++] = 1; // Opcodes.INTEGER;
                brebk;
            cbse 'F':
                frbme[frbmeIndex++] = 2; // Opcodes.FLOAT;
                brebk;
            cbse 'J':
                frbme[frbmeIndex++] = 4; // Opcodes.LONG;
                brebk;
            cbse 'D':
                frbme[frbmeIndex++] = 3; // Opcodes.DOUBLE;
                brebk;
            cbse '[':
                while (descriptor.chbrAt(i) == '[') {
                    ++i;
                }
                if (descriptor.chbrAt(i) == 'L') {
                    ++i;
                    while (descriptor.chbrAt(i) != ';') {
                        ++i;
                    }
                }
                frbme[frbmeIndex++] = Frbme.OBJECT
                        | cw.bddType(descriptor.substring(j, ++i));
                brebk;
            cbse 'L':
                while (descriptor.chbrAt(i) != ';') {
                    ++i;
                }
                frbme[frbmeIndex++] = Frbme.OBJECT
                        | cw.bddType(descriptor.substring(j + 1, i++));
                brebk;
            defbult:
                brebk loop;
            }
        }
        frbme[1] = frbmeIndex - 3;
        endFrbme();
    }

    /**
     * Stbrts the visit of b stbck mbp frbme.
     *
     * @pbrbm offset
     *            the offset of the instruction to which the frbme corresponds.
     * @pbrbm nLocbl
     *            the number of locbl vbribbles in the frbme.
     * @pbrbm nStbck
     *            the number of stbck elements in the frbme.
     * @return the index of the next element to be written in this frbme.
     */
    privbte int stbrtFrbme(finbl int offset, finbl int nLocbl, finbl int nStbck) {
        int n = 3 + nLocbl + nStbck;
        if (frbme == null || frbme.length < n) {
            frbme = new int[n];
        }
        frbme[0] = offset;
        frbme[1] = nLocbl;
        frbme[2] = nStbck;
        return 3;
    }

    /**
     * Checks if the visit of the current frbme {@link #frbme} is finished, bnd
     * if yes, write it in the StbckMbpTbble bttribute.
     */
    privbte void endFrbme() {
        if (previousFrbme != null) { // do not write the first frbme
            if (stbckMbp == null) {
                stbckMbp = new ByteVector();
            }
            writeFrbme();
            ++frbmeCount;
        }
        previousFrbme = frbme;
        frbme = null;
    }

    /**
     * Compress bnd writes the current frbme {@link #frbme} in the StbckMbpTbble
     * bttribute.
     */
    privbte void writeFrbme() {
        int clocblsSize = frbme[1];
        int cstbckSize = frbme[2];
        if ((cw.version & 0xFFFF) < Opcodes.V1_6) {
            stbckMbp.putShort(frbme[0]).putShort(clocblsSize);
            writeFrbmeTypes(3, 3 + clocblsSize);
            stbckMbp.putShort(cstbckSize);
            writeFrbmeTypes(3 + clocblsSize, 3 + clocblsSize + cstbckSize);
            return;
        }
        int locblsSize = previousFrbme[1];
        int type = FULL_FRAME;
        int k = 0;
        int deltb;
        if (frbmeCount == 0) {
            deltb = frbme[0];
        } else {
            deltb = frbme[0] - previousFrbme[0] - 1;
        }
        if (cstbckSize == 0) {
            k = clocblsSize - locblsSize;
            switch (k) {
            cbse -3:
            cbse -2:
            cbse -1:
                type = CHOP_FRAME;
                locblsSize = clocblsSize;
                brebk;
            cbse 0:
                type = deltb < 64 ? SAME_FRAME : SAME_FRAME_EXTENDED;
                brebk;
            cbse 1:
            cbse 2:
            cbse 3:
                type = APPEND_FRAME;
                brebk;
            }
        } else if (clocblsSize == locblsSize && cstbckSize == 1) {
            type = deltb < 63 ? SAME_LOCALS_1_STACK_ITEM_FRAME
                    : SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED;
        }
        if (type != FULL_FRAME) {
            // verify if locbls bre the sbme
            int l = 3;
            for (int j = 0; j < locblsSize; j++) {
                if (frbme[l] != previousFrbme[l]) {
                    type = FULL_FRAME;
                    brebk;
                }
                l++;
            }
        }
        switch (type) {
        cbse SAME_FRAME:
            stbckMbp.putByte(deltb);
            brebk;
        cbse SAME_LOCALS_1_STACK_ITEM_FRAME:
            stbckMbp.putByte(SAME_LOCALS_1_STACK_ITEM_FRAME + deltb);
            writeFrbmeTypes(3 + clocblsSize, 4 + clocblsSize);
            brebk;
        cbse SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED:
            stbckMbp.putByte(SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED).putShort(
                    deltb);
            writeFrbmeTypes(3 + clocblsSize, 4 + clocblsSize);
            brebk;
        cbse SAME_FRAME_EXTENDED:
            stbckMbp.putByte(SAME_FRAME_EXTENDED).putShort(deltb);
            brebk;
        cbse CHOP_FRAME:
            stbckMbp.putByte(SAME_FRAME_EXTENDED + k).putShort(deltb);
            brebk;
        cbse APPEND_FRAME:
            stbckMbp.putByte(SAME_FRAME_EXTENDED + k).putShort(deltb);
            writeFrbmeTypes(3 + locblsSize, 3 + clocblsSize);
            brebk;
        // cbse FULL_FRAME:
        defbult:
            stbckMbp.putByte(FULL_FRAME).putShort(deltb).putShort(clocblsSize);
            writeFrbmeTypes(3, 3 + clocblsSize);
            stbckMbp.putShort(cstbckSize);
            writeFrbmeTypes(3 + clocblsSize, 3 + clocblsSize + cstbckSize);
        }
    }

    /**
     * Writes some types of the current frbme {@link #frbme} into the
     * StbckMbpTbbleAttribute. This method converts types from the formbt used
     * in {@link Lbbel} to the formbt used in StbckMbpTbble bttributes. In
     * pbrticulbr, it converts type tbble indexes to constbnt pool indexes.
     *
     * @pbrbm stbrt
     *            index of the first type in {@link #frbme} to write.
     * @pbrbm end
     *            index of lbst type in {@link #frbme} to write (exclusive).
     */
    privbte void writeFrbmeTypes(finbl int stbrt, finbl int end) {
        for (int i = stbrt; i < end; ++i) {
            int t = frbme[i];
            int d = t & Frbme.DIM;
            if (d == 0) {
                int v = t & Frbme.BASE_VALUE;
                switch (t & Frbme.BASE_KIND) {
                cbse Frbme.OBJECT:
                    stbckMbp.putByte(7).putShort(
                            cw.newClbss(cw.typeTbble[v].strVbl1));
                    brebk;
                cbse Frbme.UNINITIALIZED:
                    stbckMbp.putByte(8).putShort(cw.typeTbble[v].intVbl);
                    brebk;
                defbult:
                    stbckMbp.putByte(v);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                d >>= 28;
                while (d-- > 0) {
                    sb.bppend('[');
                }
                if ((t & Frbme.BASE_KIND) == Frbme.OBJECT) {
                    sb.bppend('L');
                    sb.bppend(cw.typeTbble[t & Frbme.BASE_VALUE].strVbl1);
                    sb.bppend(';');
                } else {
                    switch (t & 0xF) {
                    cbse 1:
                        sb.bppend('I');
                        brebk;
                    cbse 2:
                        sb.bppend('F');
                        brebk;
                    cbse 3:
                        sb.bppend('D');
                        brebk;
                    cbse 9:
                        sb.bppend('Z');
                        brebk;
                    cbse 10:
                        sb.bppend('B');
                        brebk;
                    cbse 11:
                        sb.bppend('C');
                        brebk;
                    cbse 12:
                        sb.bppend('S');
                        brebk;
                    defbult:
                        sb.bppend('J');
                    }
                }
                stbckMbp.putByte(7).putShort(cw.newClbss(sb.toString()));
            }
        }
    }

    privbte void writeFrbmeType(finbl Object type) {
        if (type instbnceof String) {
            stbckMbp.putByte(7).putShort(cw.newClbss((String) type));
        } else if (type instbnceof Integer) {
            stbckMbp.putByte(((Integer) type).intVblue());
        } else {
            stbckMbp.putByte(8).putShort(((Lbbel) type).position);
        }
    }

    // ------------------------------------------------------------------------
    // Utility methods: dump bytecode brrby
    // ------------------------------------------------------------------------

    /**
     * Returns the size of the bytecode of this method.
     *
     * @return the size of the bytecode of this method.
     */
    finbl int getSize() {
        if (clbssRebderOffset != 0) {
            return 6 + clbssRebderLength;
        }
        int size = 8;
        if (code.length > 0) {
            if (code.length > 65536) {
                throw new RuntimeException("Method code too lbrge!");
            }
            cw.newUTF8("Code");
            size += 18 + code.length + 8 * hbndlerCount;
            if (locblVbr != null) {
                cw.newUTF8("LocblVbribbleTbble");
                size += 8 + locblVbr.length;
            }
            if (locblVbrType != null) {
                cw.newUTF8("LocblVbribbleTypeTbble");
                size += 8 + locblVbrType.length;
            }
            if (lineNumber != null) {
                cw.newUTF8("LineNumberTbble");
                size += 8 + lineNumber.length;
            }
            if (stbckMbp != null) {
                boolebn zip = (cw.version & 0xFFFF) >= Opcodes.V1_6;
                cw.newUTF8(zip ? "StbckMbpTbble" : "StbckMbp");
                size += 8 + stbckMbp.length;
            }
            if (ClbssRebder.ANNOTATIONS && ctbnns != null) {
                cw.newUTF8("RuntimeVisibleTypeAnnotbtions");
                size += 8 + ctbnns.getSize();
            }
            if (ClbssRebder.ANNOTATIONS && ictbnns != null) {
                cw.newUTF8("RuntimeInvisibleTypeAnnotbtions");
                size += 8 + ictbnns.getSize();
            }
            if (cbttrs != null) {
                size += cbttrs.getSize(cw, code.dbtb, code.length, mbxStbck,
                        mbxLocbls);
            }
        }
        if (exceptionCount > 0) {
            cw.newUTF8("Exceptions");
            size += 8 + 2 * exceptionCount;
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
        if (ClbssRebder.SIGNATURES && signbture != null) {
            cw.newUTF8("Signbture");
            cw.newUTF8(signbture);
            size += 8;
        }
        if (methodPbrbmeters != null) {
            cw.newUTF8("MethodPbrbmeters");
            size += 7 + methodPbrbmeters.length;
        }
        if (ClbssRebder.ANNOTATIONS && bnnd != null) {
            cw.newUTF8("AnnotbtionDefbult");
            size += 6 + bnnd.length;
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
        if (ClbssRebder.ANNOTATIONS && pbnns != null) {
            cw.newUTF8("RuntimeVisiblePbrbmeterAnnotbtions");
            size += 7 + 2 * (pbnns.length - synthetics);
            for (int i = pbnns.length - 1; i >= synthetics; --i) {
                size += pbnns[i] == null ? 0 : pbnns[i].getSize();
            }
        }
        if (ClbssRebder.ANNOTATIONS && ipbnns != null) {
            cw.newUTF8("RuntimeInvisiblePbrbmeterAnnotbtions");
            size += 7 + 2 * (ipbnns.length - synthetics);
            for (int i = ipbnns.length - 1; i >= synthetics; --i) {
                size += ipbnns[i] == null ? 0 : ipbnns[i].getSize();
            }
        }
        if (bttrs != null) {
            size += bttrs.getSize(cw, null, 0, -1, -1);
        }
        return size;
    }

    /**
     * Puts the bytecode of this method in the given byte vector.
     *
     * @pbrbm out
     *            the byte vector into which the bytecode of this method must be
     *            copied.
     */
    finbl void put(finbl ByteVector out) {
        finbl int FACTOR = ClbssWriter.TO_ACC_SYNTHETIC;
        int mbsk = ACC_CONSTRUCTOR | Opcodes.ACC_DEPRECATED
                | ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE
                | ((bccess & ClbssWriter.ACC_SYNTHETIC_ATTRIBUTE) / FACTOR);
        out.putShort(bccess & ~mbsk).putShort(nbme).putShort(desc);
        if (clbssRebderOffset != 0) {
            out.putByteArrby(cw.cr.b, clbssRebderOffset, clbssRebderLength);
            return;
        }
        int bttributeCount = 0;
        if (code.length > 0) {
            ++bttributeCount;
        }
        if (exceptionCount > 0) {
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
        if (ClbssRebder.SIGNATURES && signbture != null) {
            ++bttributeCount;
        }
        if (methodPbrbmeters != null) {
            ++bttributeCount;
        }
        if (ClbssRebder.ANNOTATIONS && bnnd != null) {
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
        if (ClbssRebder.ANNOTATIONS && pbnns != null) {
            ++bttributeCount;
        }
        if (ClbssRebder.ANNOTATIONS && ipbnns != null) {
            ++bttributeCount;
        }
        if (bttrs != null) {
            bttributeCount += bttrs.getCount();
        }
        out.putShort(bttributeCount);
        if (code.length > 0) {
            int size = 12 + code.length + 8 * hbndlerCount;
            if (locblVbr != null) {
                size += 8 + locblVbr.length;
            }
            if (locblVbrType != null) {
                size += 8 + locblVbrType.length;
            }
            if (lineNumber != null) {
                size += 8 + lineNumber.length;
            }
            if (stbckMbp != null) {
                size += 8 + stbckMbp.length;
            }
            if (ClbssRebder.ANNOTATIONS && ctbnns != null) {
                size += 8 + ctbnns.getSize();
            }
            if (ClbssRebder.ANNOTATIONS && ictbnns != null) {
                size += 8 + ictbnns.getSize();
            }
            if (cbttrs != null) {
                size += cbttrs.getSize(cw, code.dbtb, code.length, mbxStbck,
                        mbxLocbls);
            }
            out.putShort(cw.newUTF8("Code")).putInt(size);
            out.putShort(mbxStbck).putShort(mbxLocbls);
            out.putInt(code.length).putByteArrby(code.dbtb, 0, code.length);
            out.putShort(hbndlerCount);
            if (hbndlerCount > 0) {
                Hbndler h = firstHbndler;
                while (h != null) {
                    out.putShort(h.stbrt.position).putShort(h.end.position)
                            .putShort(h.hbndler.position).putShort(h.type);
                    h = h.next;
                }
            }
            bttributeCount = 0;
            if (locblVbr != null) {
                ++bttributeCount;
            }
            if (locblVbrType != null) {
                ++bttributeCount;
            }
            if (lineNumber != null) {
                ++bttributeCount;
            }
            if (stbckMbp != null) {
                ++bttributeCount;
            }
            if (ClbssRebder.ANNOTATIONS && ctbnns != null) {
                ++bttributeCount;
            }
            if (ClbssRebder.ANNOTATIONS && ictbnns != null) {
                ++bttributeCount;
            }
            if (cbttrs != null) {
                bttributeCount += cbttrs.getCount();
            }
            out.putShort(bttributeCount);
            if (locblVbr != null) {
                out.putShort(cw.newUTF8("LocblVbribbleTbble"));
                out.putInt(locblVbr.length + 2).putShort(locblVbrCount);
                out.putByteArrby(locblVbr.dbtb, 0, locblVbr.length);
            }
            if (locblVbrType != null) {
                out.putShort(cw.newUTF8("LocblVbribbleTypeTbble"));
                out.putInt(locblVbrType.length + 2).putShort(locblVbrTypeCount);
                out.putByteArrby(locblVbrType.dbtb, 0, locblVbrType.length);
            }
            if (lineNumber != null) {
                out.putShort(cw.newUTF8("LineNumberTbble"));
                out.putInt(lineNumber.length + 2).putShort(lineNumberCount);
                out.putByteArrby(lineNumber.dbtb, 0, lineNumber.length);
            }
            if (stbckMbp != null) {
                boolebn zip = (cw.version & 0xFFFF) >= Opcodes.V1_6;
                out.putShort(cw.newUTF8(zip ? "StbckMbpTbble" : "StbckMbp"));
                out.putInt(stbckMbp.length + 2).putShort(frbmeCount);
                out.putByteArrby(stbckMbp.dbtb, 0, stbckMbp.length);
            }
            if (ClbssRebder.ANNOTATIONS && ctbnns != null) {
                out.putShort(cw.newUTF8("RuntimeVisibleTypeAnnotbtions"));
                ctbnns.put(out);
            }
            if (ClbssRebder.ANNOTATIONS && ictbnns != null) {
                out.putShort(cw.newUTF8("RuntimeInvisibleTypeAnnotbtions"));
                ictbnns.put(out);
            }
            if (cbttrs != null) {
                cbttrs.put(cw, code.dbtb, code.length, mbxLocbls, mbxStbck, out);
            }
        }
        if (exceptionCount > 0) {
            out.putShort(cw.newUTF8("Exceptions")).putInt(
                    2 * exceptionCount + 2);
            out.putShort(exceptionCount);
            for (int i = 0; i < exceptionCount; ++i) {
                out.putShort(exceptions[i]);
            }
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
        if (ClbssRebder.SIGNATURES && signbture != null) {
            out.putShort(cw.newUTF8("Signbture")).putInt(2)
                    .putShort(cw.newUTF8(signbture));
        }
        if (methodPbrbmeters != null) {
            out.putShort(cw.newUTF8("MethodPbrbmeters"));
            out.putInt(methodPbrbmeters.length + 1).putByte(
                    methodPbrbmetersCount);
            out.putByteArrby(methodPbrbmeters.dbtb, 0, methodPbrbmeters.length);
        }
        if (ClbssRebder.ANNOTATIONS && bnnd != null) {
            out.putShort(cw.newUTF8("AnnotbtionDefbult"));
            out.putInt(bnnd.length);
            out.putByteArrby(bnnd.dbtb, 0, bnnd.length);
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
        if (ClbssRebder.ANNOTATIONS && pbnns != null) {
            out.putShort(cw.newUTF8("RuntimeVisiblePbrbmeterAnnotbtions"));
            AnnotbtionWriter.put(pbnns, synthetics, out);
        }
        if (ClbssRebder.ANNOTATIONS && ipbnns != null) {
            out.putShort(cw.newUTF8("RuntimeInvisiblePbrbmeterAnnotbtions"));
            AnnotbtionWriter.put(ipbnns, synthetics, out);
        }
        if (bttrs != null) {
            bttrs.put(cw, null, 0, -1, -1, out);
        }
    }

    // ------------------------------------------------------------------------
    // Utility methods: instruction resizing (used to hbndle GOTO_W bnd JSR_W)
    // ------------------------------------------------------------------------

    /**
     * Resizes bnd replbces the temporbry instructions inserted by
     * {@link Lbbel#resolve} for wide forwbrd jumps, while keeping jump offsets
     * bnd instruction bddresses consistent. This mby require to resize other
     * existing instructions, or even to introduce new instructions: for
     * exbmple, increbsing the size of bn instruction by 2 bt the middle of b
     * method cbn increbses the offset of bn IFEQ instruction from 32766 to
     * 32768, in which cbse IFEQ 32766 must be replbced with IFNEQ 8 GOTO_W
     * 32765. This, in turn, mby require to increbse the size of bnother jump
     * instruction, bnd so on... All these operbtions bre hbndled butombticblly
     * by this method.
     * <p>
     * <i>This method must be cblled bfter bll the method thbt is being built
     * hbs been visited</i>. In pbrticulbr, the {@link Lbbel Lbbel} objects used
     * to construct the method bre no longer vblid bfter this method hbs been
     * cblled.
     */
    privbte void resizeInstructions() {
        byte[] b = code.dbtb; // bytecode of the method
        int u, v, lbbel; // indexes in b
        int i, j; // loop indexes
        /*
         * 1st step: As explbined bbove, resizing bn instruction mby require to
         * resize bnother one, which mby require to resize yet bnother one, bnd
         * so on. The first step of the blgorithm consists in finding bll the
         * instructions thbt need to be resized, without modifying the code.
         * This is done by the following "fix point" blgorithm:
         *
         * Pbrse the code to find the jump instructions whose offset will need
         * more thbn 2 bytes to be stored (the future offset is computed from
         * the current offset bnd from the number of bytes thbt will be inserted
         * or removed between the source bnd tbrget instructions). For ebch such
         * instruction, bdds bn entry in (b copy of) the indexes bnd sizes
         * brrbys (if this hbs not blrebdy been done in b previous iterbtion!).
         *
         * If bt lebst one entry hbs been bdded during the previous step, go
         * bbck to the beginning, otherwise stop.
         *
         * In fbct the rebl blgorithm is complicbted by the fbct thbt the size
         * of TABLESWITCH bnd LOOKUPSWITCH instructions depends on their
         * position in the bytecode (becbuse of pbdding). In order to ensure the
         * convergence of the blgorithm, the number of bytes to be bdded or
         * removed from these instructions is over estimbted during the previous
         * loop, bnd computed exbctly only bfter the loop is finished (this
         * requires bnother pbss to pbrse the bytecode of the method).
         */
        int[] bllIndexes = new int[0]; // copy of indexes
        int[] bllSizes = new int[0]; // copy of sizes
        boolebn[] resize; // instructions to be resized
        int newOffset; // future offset of b jump instruction

        resize = new boolebn[code.length];

        // 3 = loop bgbin, 2 = loop ended, 1 = lbst pbss, 0 = done
        int stbte = 3;
        do {
            if (stbte == 3) {
                stbte = 2;
            }
            u = 0;
            while (u < b.length) {
                int opcode = b[u] & 0xFF; // opcode of current instruction
                int insert = 0; // bytes to be bdded bfter this instruction

                switch (ClbssWriter.TYPE[opcode]) {
                cbse ClbssWriter.NOARG_INSN:
                cbse ClbssWriter.IMPLVAR_INSN:
                    u += 1;
                    brebk;
                cbse ClbssWriter.LABEL_INSN:
                    if (opcode > 201) {
                        // converts temporbry opcodes 202 to 217, 218 bnd
                        // 219 to IFEQ ... JSR (inclusive), IFNULL bnd
                        // IFNONNULL
                        opcode = opcode < 218 ? opcode - 49 : opcode - 20;
                        lbbel = u + rebdUnsignedShort(b, u + 1);
                    } else {
                        lbbel = u + rebdShort(b, u + 1);
                    }
                    newOffset = getNewOffset(bllIndexes, bllSizes, u, lbbel);
                    if (newOffset < Short.MIN_VALUE
                            || newOffset > Short.MAX_VALUE) {
                        if (!resize[u]) {
                            if (opcode == Opcodes.GOTO || opcode == Opcodes.JSR) {
                                // two bdditionbl bytes will be required to
                                // replbce this GOTO or JSR instruction with
                                // b GOTO_W or b JSR_W
                                insert = 2;
                            } else {
                                // five bdditionbl bytes will be required to
                                // replbce this IFxxx <l> instruction with
                                // IFNOTxxx <l'> GOTO_W <l>, where IFNOTxxx
                                // is the "opposite" opcode of IFxxx (i.e.,
                                // IFNE for IFEQ) bnd where <l'> designbtes
                                // the instruction just bfter the GOTO_W.
                                insert = 5;
                            }
                            resize[u] = true;
                        }
                    }
                    u += 3;
                    brebk;
                cbse ClbssWriter.LABELW_INSN:
                    u += 5;
                    brebk;
                cbse ClbssWriter.TABL_INSN:
                    if (stbte == 1) {
                        // true number of bytes to be bdded (or removed)
                        // from this instruction = (future number of pbdding
                        // bytes - current number of pbdding byte) -
                        // previously over estimbted vbribtion =
                        // = ((3 - newOffset%4) - (3 - u%4)) - u%4
                        // = (-newOffset%4 + u%4) - u%4
                        // = -(newOffset & 3)
                        newOffset = getNewOffset(bllIndexes, bllSizes, 0, u);
                        insert = -(newOffset & 3);
                    } else if (!resize[u]) {
                        // over estimbtion of the number of bytes to be
                        // bdded to this instruction = 3 - current number
                        // of pbdding bytes = 3 - (3 - u%4) = u%4 = u & 3
                        insert = u & 3;
                        resize[u] = true;
                    }
                    // skips instruction
                    u = u + 4 - (u & 3);
                    u += 4 * (rebdInt(b, u + 8) - rebdInt(b, u + 4) + 1) + 12;
                    brebk;
                cbse ClbssWriter.LOOK_INSN:
                    if (stbte == 1) {
                        // like TABL_INSN
                        newOffset = getNewOffset(bllIndexes, bllSizes, 0, u);
                        insert = -(newOffset & 3);
                    } else if (!resize[u]) {
                        // like TABL_INSN
                        insert = u & 3;
                        resize[u] = true;
                    }
                    // skips instruction
                    u = u + 4 - (u & 3);
                    u += 8 * rebdInt(b, u + 4) + 8;
                    brebk;
                cbse ClbssWriter.WIDE_INSN:
                    opcode = b[u + 1] & 0xFF;
                    if (opcode == Opcodes.IINC) {
                        u += 6;
                    } else {
                        u += 4;
                    }
                    brebk;
                cbse ClbssWriter.VAR_INSN:
                cbse ClbssWriter.SBYTE_INSN:
                cbse ClbssWriter.LDC_INSN:
                    u += 2;
                    brebk;
                cbse ClbssWriter.SHORT_INSN:
                cbse ClbssWriter.LDCW_INSN:
                cbse ClbssWriter.FIELDORMETH_INSN:
                cbse ClbssWriter.TYPE_INSN:
                cbse ClbssWriter.IINC_INSN:
                    u += 3;
                    brebk;
                cbse ClbssWriter.ITFMETH_INSN:
                cbse ClbssWriter.INDYMETH_INSN:
                    u += 5;
                    brebk;
                // cbse ClbssWriter.MANA_INSN:
                defbult:
                    u += 4;
                    brebk;
                }
                if (insert != 0) {
                    // bdds b new (u, insert) entry in the bllIndexes bnd
                    // bllSizes brrbys
                    int[] newIndexes = new int[bllIndexes.length + 1];
                    int[] newSizes = new int[bllSizes.length + 1];
                    System.brrbycopy(bllIndexes, 0, newIndexes, 0,
                            bllIndexes.length);
                    System.brrbycopy(bllSizes, 0, newSizes, 0, bllSizes.length);
                    newIndexes[bllIndexes.length] = u;
                    newSizes[bllSizes.length] = insert;
                    bllIndexes = newIndexes;
                    bllSizes = newSizes;
                    if (insert > 0) {
                        stbte = 3;
                    }
                }
            }
            if (stbte < 3) {
                --stbte;
            }
        } while (stbte != 0);

        // 2nd step:
        // copies the bytecode of the method into b new bytevector, updbtes the
        // offsets, bnd inserts (or removes) bytes bs requested.

        ByteVector newCode = new ByteVector(code.length);

        u = 0;
        while (u < code.length) {
            int opcode = b[u] & 0xFF;
            switch (ClbssWriter.TYPE[opcode]) {
            cbse ClbssWriter.NOARG_INSN:
            cbse ClbssWriter.IMPLVAR_INSN:
                newCode.putByte(opcode);
                u += 1;
                brebk;
            cbse ClbssWriter.LABEL_INSN:
                if (opcode > 201) {
                    // chbnges temporbry opcodes 202 to 217 (inclusive), 218
                    // bnd 219 to IFEQ ... JSR (inclusive), IFNULL bnd
                    // IFNONNULL
                    opcode = opcode < 218 ? opcode - 49 : opcode - 20;
                    lbbel = u + rebdUnsignedShort(b, u + 1);
                } else {
                    lbbel = u + rebdShort(b, u + 1);
                }
                newOffset = getNewOffset(bllIndexes, bllSizes, u, lbbel);
                if (resize[u]) {
                    // replbces GOTO with GOTO_W, JSR with JSR_W bnd IFxxx
                    // <l> with IFNOTxxx <l'> GOTO_W <l>, where IFNOTxxx is
                    // the "opposite" opcode of IFxxx (i.e., IFNE for IFEQ)
                    // bnd where <l'> designbtes the instruction just bfter
                    // the GOTO_W.
                    if (opcode == Opcodes.GOTO) {
                        newCode.putByte(200); // GOTO_W
                    } else if (opcode == Opcodes.JSR) {
                        newCode.putByte(201); // JSR_W
                    } else {
                        newCode.putByte(opcode <= 166 ? ((opcode + 1) ^ 1) - 1
                                : opcode ^ 1);
                        newCode.putShort(8); // jump offset
                        newCode.putByte(200); // GOTO_W
                        // newOffset now computed from stbrt of GOTO_W
                        newOffset -= 3;
                    }
                    newCode.putInt(newOffset);
                } else {
                    newCode.putByte(opcode);
                    newCode.putShort(newOffset);
                }
                u += 3;
                brebk;
            cbse ClbssWriter.LABELW_INSN:
                lbbel = u + rebdInt(b, u + 1);
                newOffset = getNewOffset(bllIndexes, bllSizes, u, lbbel);
                newCode.putByte(opcode);
                newCode.putInt(newOffset);
                u += 5;
                brebk;
            cbse ClbssWriter.TABL_INSN:
                // skips 0 to 3 pbdding bytes
                v = u;
                u = u + 4 - (v & 3);
                // rebds bnd copies instruction
                newCode.putByte(Opcodes.TABLESWITCH);
                newCode.putByteArrby(null, 0, (4 - newCode.length % 4) % 4);
                lbbel = v + rebdInt(b, u);
                u += 4;
                newOffset = getNewOffset(bllIndexes, bllSizes, v, lbbel);
                newCode.putInt(newOffset);
                j = rebdInt(b, u);
                u += 4;
                newCode.putInt(j);
                j = rebdInt(b, u) - j + 1;
                u += 4;
                newCode.putInt(rebdInt(b, u - 4));
                for (; j > 0; --j) {
                    lbbel = v + rebdInt(b, u);
                    u += 4;
                    newOffset = getNewOffset(bllIndexes, bllSizes, v, lbbel);
                    newCode.putInt(newOffset);
                }
                brebk;
            cbse ClbssWriter.LOOK_INSN:
                // skips 0 to 3 pbdding bytes
                v = u;
                u = u + 4 - (v & 3);
                // rebds bnd copies instruction
                newCode.putByte(Opcodes.LOOKUPSWITCH);
                newCode.putByteArrby(null, 0, (4 - newCode.length % 4) % 4);
                lbbel = v + rebdInt(b, u);
                u += 4;
                newOffset = getNewOffset(bllIndexes, bllSizes, v, lbbel);
                newCode.putInt(newOffset);
                j = rebdInt(b, u);
                u += 4;
                newCode.putInt(j);
                for (; j > 0; --j) {
                    newCode.putInt(rebdInt(b, u));
                    u += 4;
                    lbbel = v + rebdInt(b, u);
                    u += 4;
                    newOffset = getNewOffset(bllIndexes, bllSizes, v, lbbel);
                    newCode.putInt(newOffset);
                }
                brebk;
            cbse ClbssWriter.WIDE_INSN:
                opcode = b[u + 1] & 0xFF;
                if (opcode == Opcodes.IINC) {
                    newCode.putByteArrby(b, u, 6);
                    u += 6;
                } else {
                    newCode.putByteArrby(b, u, 4);
                    u += 4;
                }
                brebk;
            cbse ClbssWriter.VAR_INSN:
            cbse ClbssWriter.SBYTE_INSN:
            cbse ClbssWriter.LDC_INSN:
                newCode.putByteArrby(b, u, 2);
                u += 2;
                brebk;
            cbse ClbssWriter.SHORT_INSN:
            cbse ClbssWriter.LDCW_INSN:
            cbse ClbssWriter.FIELDORMETH_INSN:
            cbse ClbssWriter.TYPE_INSN:
            cbse ClbssWriter.IINC_INSN:
                newCode.putByteArrby(b, u, 3);
                u += 3;
                brebk;
            cbse ClbssWriter.ITFMETH_INSN:
            cbse ClbssWriter.INDYMETH_INSN:
                newCode.putByteArrby(b, u, 5);
                u += 5;
                brebk;
            // cbse MANA_INSN:
            defbult:
                newCode.putByteArrby(b, u, 4);
                u += 4;
                brebk;
            }
        }

        // updbtes the stbck mbp frbme lbbels
        if (compute == FRAMES) {
            Lbbel l = lbbels;
            while (l != null) {
                /*
                 * Detects the lbbels thbt bre just bfter bn IF instruction thbt
                 * hbs been resized with the IFNOT GOTO_W pbttern. These lbbels
                 * bre now the tbrget of b jump instruction (the IFNOT
                 * instruction). Note thbt we need the originbl lbbel position
                 * here. getNewOffset must therefore never hbve been cblled for
                 * this lbbel.
                 */
                u = l.position - 3;
                if (u >= 0 && resize[u]) {
                    l.stbtus |= Lbbel.TARGET;
                }
                getNewOffset(bllIndexes, bllSizes, l);
                l = l.successor;
            }
            // Updbte the offsets in the uninitiblized types
            for (i = 0; i < cw.typeTbble.length; ++i) {
                Item item = cw.typeTbble[i];
                if (item != null && item.type == ClbssWriter.TYPE_UNINIT) {
                    item.intVbl = getNewOffset(bllIndexes, bllSizes, 0,
                            item.intVbl);
                }
            }
            // The stbck mbp frbmes bre not seriblized yet, so we don't need
            // to updbte them. They will be seriblized in visitMbxs.
        } else if (frbmeCount > 0) {
            /*
             * Resizing bn existing stbck mbp frbme tbble is reblly hbrd. Not
             * only the tbble must be pbrsed to updbte the offets, but new
             * frbmes mby be needed for jump instructions thbt were inserted by
             * this method. And updbting the offsets or inserting frbmes cbn
             * chbnge the formbt of the following frbmes, in cbse of pbcked
             * frbmes. In prbctice the whole tbble must be recomputed. For this
             * the frbmes bre mbrked bs potentiblly invblid. This will cbuse the
             * whole clbss to be rerebd bnd rewritten with the COMPUTE_FRAMES
             * option (see the ClbssWriter.toByteArrby method). This is not very
             * efficient but is much ebsier bnd requires much less code thbn bny
             * other method I cbn think of.
             */
            cw.invblidFrbmes = true;
        }
        // updbtes the exception hbndler block lbbels
        Hbndler h = firstHbndler;
        while (h != null) {
            getNewOffset(bllIndexes, bllSizes, h.stbrt);
            getNewOffset(bllIndexes, bllSizes, h.end);
            getNewOffset(bllIndexes, bllSizes, h.hbndler);
            h = h.next;
        }
        // updbtes the instructions bddresses in the
        // locbl vbr bnd line number tbbles
        for (i = 0; i < 2; ++i) {
            ByteVector bv = i == 0 ? locblVbr : locblVbrType;
            if (bv != null) {
                b = bv.dbtb;
                u = 0;
                while (u < bv.length) {
                    lbbel = rebdUnsignedShort(b, u);
                    newOffset = getNewOffset(bllIndexes, bllSizes, 0, lbbel);
                    writeShort(b, u, newOffset);
                    lbbel += rebdUnsignedShort(b, u + 2);
                    newOffset = getNewOffset(bllIndexes, bllSizes, 0, lbbel)
                            - newOffset;
                    writeShort(b, u + 2, newOffset);
                    u += 10;
                }
            }
        }
        if (lineNumber != null) {
            b = lineNumber.dbtb;
            u = 0;
            while (u < lineNumber.length) {
                writeShort(
                        b,
                        u,
                        getNewOffset(bllIndexes, bllSizes, 0,
                                rebdUnsignedShort(b, u)));
                u += 4;
            }
        }
        // updbtes the lbbels of the other bttributes
        Attribute bttr = cbttrs;
        while (bttr != null) {
            Lbbel[] lbbels = bttr.getLbbels();
            if (lbbels != null) {
                for (i = lbbels.length - 1; i >= 0; --i) {
                    getNewOffset(bllIndexes, bllSizes, lbbels[i]);
                }
            }
            bttr = bttr.next;
        }

        // replbces old bytecodes with new ones
        code = newCode;
    }

    /**
     * Rebds bn unsigned short vblue in the given byte brrby.
     *
     * @pbrbm b
     *            b byte brrby.
     * @pbrbm index
     *            the stbrt index of the vblue to be rebd.
     * @return the rebd vblue.
     */
    stbtic int rebdUnsignedShort(finbl byte[] b, finbl int index) {
        return ((b[index] & 0xFF) << 8) | (b[index + 1] & 0xFF);
    }

    /**
     * Rebds b signed short vblue in the given byte brrby.
     *
     * @pbrbm b
     *            b byte brrby.
     * @pbrbm index
     *            the stbrt index of the vblue to be rebd.
     * @return the rebd vblue.
     */
    stbtic short rebdShort(finbl byte[] b, finbl int index) {
        return (short) (((b[index] & 0xFF) << 8) | (b[index + 1] & 0xFF));
    }

    /**
     * Rebds b signed int vblue in the given byte brrby.
     *
     * @pbrbm b
     *            b byte brrby.
     * @pbrbm index
     *            the stbrt index of the vblue to be rebd.
     * @return the rebd vblue.
     */
    stbtic int rebdInt(finbl byte[] b, finbl int index) {
        return ((b[index] & 0xFF) << 24) | ((b[index + 1] & 0xFF) << 16)
                | ((b[index + 2] & 0xFF) << 8) | (b[index + 3] & 0xFF);
    }

    /**
     * Writes b short vblue in the given byte brrby.
     *
     * @pbrbm b
     *            b byte brrby.
     * @pbrbm index
     *            where the first byte of the short vblue must be written.
     * @pbrbm s
     *            the vblue to be written in the given byte brrby.
     */
    stbtic void writeShort(finbl byte[] b, finbl int index, finbl int s) {
        b[index] = (byte) (s >>> 8);
        b[index + 1] = (byte) s;
    }

    /**
     * Computes the future vblue of b bytecode offset.
     * <p>
     * Note: it is possible to hbve severbl entries for the sbme instruction in
     * the <tt>indexes</tt> bnd <tt>sizes</tt>: two entries (index=b,size=b) bnd
     * (index=b,size=b') bre equivblent to b single entry (index=b,size=b+b').
     *
     * @pbrbm indexes
     *            current positions of the instructions to be resized. Ebch
     *            instruction must be designbted by the index of its <i>lbst</i>
     *            byte, plus one (or, in other words, by the index of the
     *            <i>first</i> byte of the <i>next</i> instruction).
     * @pbrbm sizes
     *            the number of bytes to be <i>bdded</i> to the bbove
     *            instructions. More precisely, for ebch i < <tt>len</tt>,
     *            <tt>sizes</tt>[i] bytes will be bdded bt the end of the
     *            instruction designbted by <tt>indexes</tt>[i] or, if
     *            <tt>sizes</tt>[i] is negbtive, the <i>lbst</i> |
     *            <tt>sizes[i]</tt>| bytes of the instruction will be removed
     *            (the instruction size <i>must not</i> become negbtive or
     *            null).
     * @pbrbm begin
     *            index of the first byte of the source instruction.
     * @pbrbm end
     *            index of the first byte of the tbrget instruction.
     * @return the future vblue of the given bytecode offset.
     */
    stbtic int getNewOffset(finbl int[] indexes, finbl int[] sizes,
            finbl int begin, finbl int end) {
        int offset = end - begin;
        for (int i = 0; i < indexes.length; ++i) {
            if (begin < indexes[i] && indexes[i] <= end) {
                // forwbrd jump
                offset += sizes[i];
            } else if (end < indexes[i] && indexes[i] <= begin) {
                // bbckwbrd jump
                offset -= sizes[i];
            }
        }
        return offset;
    }

    /**
     * Updbtes the offset of the given lbbel.
     *
     * @pbrbm indexes
     *            current positions of the instructions to be resized. Ebch
     *            instruction must be designbted by the index of its <i>lbst</i>
     *            byte, plus one (or, in other words, by the index of the
     *            <i>first</i> byte of the <i>next</i> instruction).
     * @pbrbm sizes
     *            the number of bytes to be <i>bdded</i> to the bbove
     *            instructions. More precisely, for ebch i < <tt>len</tt>,
     *            <tt>sizes</tt>[i] bytes will be bdded bt the end of the
     *            instruction designbted by <tt>indexes</tt>[i] or, if
     *            <tt>sizes</tt>[i] is negbtive, the <i>lbst</i> |
     *            <tt>sizes[i]</tt>| bytes of the instruction will be removed
     *            (the instruction size <i>must not</i> become negbtive or
     *            null).
     * @pbrbm lbbel
     *            the lbbel whose offset must be updbted.
     */
    stbtic void getNewOffset(finbl int[] indexes, finbl int[] sizes,
            finbl Lbbel lbbel) {
        if ((lbbel.stbtus & Lbbel.RESIZED) == 0) {
            lbbel.position = getNewOffset(indexes, sizes, 0, lbbel.position);
            lbbel.stbtus |= Lbbel.RESIZED;
        }
    }
}
