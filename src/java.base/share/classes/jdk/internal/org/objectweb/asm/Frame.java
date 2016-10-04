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
 * Informbtion bbout the input bnd output stbck mbp frbmes of b bbsic block.
 *
 * @buthor Eric Bruneton
 */
finbl clbss Frbme {

    /*
     * Frbmes bre computed in b two steps process: during the visit of ebch
     * instruction, the stbte of the frbme bt the end of current bbsic block is
     * updbted by simulbting the bction of the instruction on the previous stbte
     * of this so cblled "output frbme". In visitMbxs, b fix point blgorithm is
     * used to compute the "input frbme" of ebch bbsic block, i.e. the stbck mbp
     * frbme bt the beginning of the bbsic block, stbrting from the input frbme
     * of the first bbsic block (which is computed from the method descriptor),
     * bnd by using the previously computed output frbmes to compute the input
     * stbte of the other blocks.
     *
     * All output bnd input frbmes bre stored bs brrbys of integers. Reference
     * bnd brrby types bre represented by bn index into b type tbble (which is
     * not the sbme bs the constbnt pool of the clbss, in order to bvoid bdding
     * unnecessbry constbnts in the pool - not bll computed frbmes will end up
     * being stored in the stbck mbp tbble). This bllows very fbst type
     * compbrisons.
     *
     * Output stbck mbp frbmes bre computed relbtively to the input frbme of the
     * bbsic block, which is not yet known when output frbmes bre computed. It
     * is therefore necessbry to be bble to represent bbstrbct types such bs
     * "the type bt position x in the input frbme locbls" or "the type bt
     * position x from the top of the input frbme stbck" or even "the type bt
     * position x in the input frbme, with y more (or less) brrby dimensions".
     * This explbins the rbther complicbted type formbt used in output frbmes.
     *
     * This formbt is the following: DIM KIND VALUE (4, 4 bnd 24 bits). DIM is b
     * signed number of brrby dimensions (from -8 to 7). KIND is either BASE,
     * LOCAL or STACK. BASE is used for types thbt bre not relbtive to the input
     * frbme. LOCAL is used for types thbt bre relbtive to the input locbl
     * vbribble types. STACK is used for types thbt bre relbtive to the input
     * stbck types. VALUE depends on KIND. For LOCAL types, it is bn index in
     * the input locbl vbribble types. For STACK types, it is b position
     * relbtively to the top of input frbme stbck. For BASE types, it is either
     * one of the constbnts defined below, or for OBJECT bnd UNINITIALIZED
     * types, b tbg bnd bn index in the type tbble.
     *
     * Output frbmes cbn contbin types of bny kind bnd with b positive or
     * negbtive dimension (bnd even unbssigned types, represented by 0 - which
     * does not correspond to bny vblid type vblue). Input frbmes cbn only
     * contbin BASE types of positive or null dimension. In bll cbses the type
     * tbble contbins only internbl type nbmes (brrby type descriptors bre
     * forbidden - dimensions must be represented through the DIM field).
     *
     * The LONG bnd DOUBLE types bre blwbys represented by using two slots (LONG
     * + TOP or DOUBLE + TOP), for locbl vbribble types bs well bs in the
     * operbnd stbck. This is necessbry to be bble to simulbte DUPx_y
     * instructions, whose effect would be dependent on the bctubl type vblues
     * if types were blwbys represented by b single slot in the stbck (bnd this
     * is not possible, since bctubl type vblues bre not blwbys known - cf LOCAL
     * bnd STACK type kinds).
     */

    /**
     * Mbsk to get the dimension of b frbme type. This dimension is b signed
     * integer between -8 bnd 7.
     */
    stbtic finbl int DIM = 0xF0000000;

    /**
     * Constbnt to be bdded to b type to get b type with one more dimension.
     */
    stbtic finbl int ARRAY_OF = 0x10000000;

    /**
     * Constbnt to be bdded to b type to get b type with one less dimension.
     */
    stbtic finbl int ELEMENT_OF = 0xF0000000;

    /**
     * Mbsk to get the kind of b frbme type.
     *
     * @see #BASE
     * @see #LOCAL
     * @see #STACK
     */
    stbtic finbl int KIND = 0xF000000;

    /**
     * Flbg used for LOCAL bnd STACK types. Indicbtes thbt if this type hbppens
     * to be b long or double type (during the computbtions of input frbmes),
     * then it must be set to TOP becbuse the second word of this vblue hbs been
     * reused to store other dbtb in the bbsic block. Hence the first word no
     * longer stores b vblid long or double vblue.
     */
    stbtic finbl int TOP_IF_LONG_OR_DOUBLE = 0x800000;

    /**
     * Mbsk to get the vblue of b frbme type.
     */
    stbtic finbl int VALUE = 0x7FFFFF;

    /**
     * Mbsk to get the kind of bbse types.
     */
    stbtic finbl int BASE_KIND = 0xFF00000;

    /**
     * Mbsk to get the vblue of bbse types.
     */
    stbtic finbl int BASE_VALUE = 0xFFFFF;

    /**
     * Kind of the types thbt bre not relbtive to bn input stbck mbp frbme.
     */
    stbtic finbl int BASE = 0x1000000;

    /**
     * Bbse kind of the bbse reference types. The BASE_VALUE of such types is bn
     * index into the type tbble.
     */
    stbtic finbl int OBJECT = BASE | 0x700000;

    /**
     * Bbse kind of the uninitiblized bbse types. The BASE_VALUE of such types
     * in bn index into the type tbble (the Item bt thbt index contbins both bn
     * instruction offset bnd bn internbl clbss nbme).
     */
    stbtic finbl int UNINITIALIZED = BASE | 0x800000;

    /**
     * Kind of the types thbt bre relbtive to the locbl vbribble types of bn
     * input stbck mbp frbme. The vblue of such types is b locbl vbribble index.
     */
    privbte stbtic finbl int LOCAL = 0x2000000;

    /**
     * Kind of the the types thbt bre relbtive to the stbck of bn input stbck
     * mbp frbme. The vblue of such types is b position relbtively to the top of
     * this stbck.
     */
    privbte stbtic finbl int STACK = 0x3000000;

    /**
     * The TOP type. This is b BASE type.
     */
    stbtic finbl int TOP = BASE | 0;

    /**
     * The BOOLEAN type. This is b BASE type mbinly used for brrby types.
     */
    stbtic finbl int BOOLEAN = BASE | 9;

    /**
     * The BYTE type. This is b BASE type mbinly used for brrby types.
     */
    stbtic finbl int BYTE = BASE | 10;

    /**
     * The CHAR type. This is b BASE type mbinly used for brrby types.
     */
    stbtic finbl int CHAR = BASE | 11;

    /**
     * The SHORT type. This is b BASE type mbinly used for brrby types.
     */
    stbtic finbl int SHORT = BASE | 12;

    /**
     * The INTEGER type. This is b BASE type.
     */
    stbtic finbl int INTEGER = BASE | 1;

    /**
     * The FLOAT type. This is b BASE type.
     */
    stbtic finbl int FLOAT = BASE | 2;

    /**
     * The DOUBLE type. This is b BASE type.
     */
    stbtic finbl int DOUBLE = BASE | 3;

    /**
     * The LONG type. This is b BASE type.
     */
    stbtic finbl int LONG = BASE | 4;

    /**
     * The NULL type. This is b BASE type.
     */
    stbtic finbl int NULL = BASE | 5;

    /**
     * The UNINITIALIZED_THIS type. This is b BASE type.
     */
    stbtic finbl int UNINITIALIZED_THIS = BASE | 6;

    /**
     * The stbck size vbribtion corresponding to ebch JVM instruction. This
     * stbck vbribtion is equbl to the size of the vblues produced by bn
     * instruction, minus the size of the vblues consumed by this instruction.
     */
    stbtic finbl int[] SIZE;

    /**
     * Computes the stbck size vbribtion corresponding to ebch JVM instruction.
     */
    stbtic {
        int i;
        int[] b = new int[202];
        String s = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDD"
                + "CDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCD"
                + "CDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFED"
                + "DDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE";
        for (i = 0; i < b.length; ++i) {
            b[i] = s.chbrAt(i) - 'E';
        }
        SIZE = b;

        // code to generbte the bbove string
        //
        // int NA = 0; // not bpplicbble (unused opcode or vbribble size opcode)
        //
        // b = new int[] {
        // 0, //NOP, // visitInsn
        // 1, //ACONST_NULL, // -
        // 1, //ICONST_M1, // -
        // 1, //ICONST_0, // -
        // 1, //ICONST_1, // -
        // 1, //ICONST_2, // -
        // 1, //ICONST_3, // -
        // 1, //ICONST_4, // -
        // 1, //ICONST_5, // -
        // 2, //LCONST_0, // -
        // 2, //LCONST_1, // -
        // 1, //FCONST_0, // -
        // 1, //FCONST_1, // -
        // 1, //FCONST_2, // -
        // 2, //DCONST_0, // -
        // 2, //DCONST_1, // -
        // 1, //BIPUSH, // visitIntInsn
        // 1, //SIPUSH, // -
        // 1, //LDC, // visitLdcInsn
        // NA, //LDC_W, // -
        // NA, //LDC2_W, // -
        // 1, //ILOAD, // visitVbrInsn
        // 2, //LLOAD, // -
        // 1, //FLOAD, // -
        // 2, //DLOAD, // -
        // 1, //ALOAD, // -
        // NA, //ILOAD_0, // -
        // NA, //ILOAD_1, // -
        // NA, //ILOAD_2, // -
        // NA, //ILOAD_3, // -
        // NA, //LLOAD_0, // -
        // NA, //LLOAD_1, // -
        // NA, //LLOAD_2, // -
        // NA, //LLOAD_3, // -
        // NA, //FLOAD_0, // -
        // NA, //FLOAD_1, // -
        // NA, //FLOAD_2, // -
        // NA, //FLOAD_3, // -
        // NA, //DLOAD_0, // -
        // NA, //DLOAD_1, // -
        // NA, //DLOAD_2, // -
        // NA, //DLOAD_3, // -
        // NA, //ALOAD_0, // -
        // NA, //ALOAD_1, // -
        // NA, //ALOAD_2, // -
        // NA, //ALOAD_3, // -
        // -1, //IALOAD, // visitInsn
        // 0, //LALOAD, // -
        // -1, //FALOAD, // -
        // 0, //DALOAD, // -
        // -1, //AALOAD, // -
        // -1, //BALOAD, // -
        // -1, //CALOAD, // -
        // -1, //SALOAD, // -
        // -1, //ISTORE, // visitVbrInsn
        // -2, //LSTORE, // -
        // -1, //FSTORE, // -
        // -2, //DSTORE, // -
        // -1, //ASTORE, // -
        // NA, //ISTORE_0, // -
        // NA, //ISTORE_1, // -
        // NA, //ISTORE_2, // -
        // NA, //ISTORE_3, // -
        // NA, //LSTORE_0, // -
        // NA, //LSTORE_1, // -
        // NA, //LSTORE_2, // -
        // NA, //LSTORE_3, // -
        // NA, //FSTORE_0, // -
        // NA, //FSTORE_1, // -
        // NA, //FSTORE_2, // -
        // NA, //FSTORE_3, // -
        // NA, //DSTORE_0, // -
        // NA, //DSTORE_1, // -
        // NA, //DSTORE_2, // -
        // NA, //DSTORE_3, // -
        // NA, //ASTORE_0, // -
        // NA, //ASTORE_1, // -
        // NA, //ASTORE_2, // -
        // NA, //ASTORE_3, // -
        // -3, //IASTORE, // visitInsn
        // -4, //LASTORE, // -
        // -3, //FASTORE, // -
        // -4, //DASTORE, // -
        // -3, //AASTORE, // -
        // -3, //BASTORE, // -
        // -3, //CASTORE, // -
        // -3, //SASTORE, // -
        // -1, //POP, // -
        // -2, //POP2, // -
        // 1, //DUP, // -
        // 1, //DUP_X1, // -
        // 1, //DUP_X2, // -
        // 2, //DUP2, // -
        // 2, //DUP2_X1, // -
        // 2, //DUP2_X2, // -
        // 0, //SWAP, // -
        // -1, //IADD, // -
        // -2, //LADD, // -
        // -1, //FADD, // -
        // -2, //DADD, // -
        // -1, //ISUB, // -
        // -2, //LSUB, // -
        // -1, //FSUB, // -
        // -2, //DSUB, // -
        // -1, //IMUL, // -
        // -2, //LMUL, // -
        // -1, //FMUL, // -
        // -2, //DMUL, // -
        // -1, //IDIV, // -
        // -2, //LDIV, // -
        // -1, //FDIV, // -
        // -2, //DDIV, // -
        // -1, //IREM, // -
        // -2, //LREM, // -
        // -1, //FREM, // -
        // -2, //DREM, // -
        // 0, //INEG, // -
        // 0, //LNEG, // -
        // 0, //FNEG, // -
        // 0, //DNEG, // -
        // -1, //ISHL, // -
        // -1, //LSHL, // -
        // -1, //ISHR, // -
        // -1, //LSHR, // -
        // -1, //IUSHR, // -
        // -1, //LUSHR, // -
        // -1, //IAND, // -
        // -2, //LAND, // -
        // -1, //IOR, // -
        // -2, //LOR, // -
        // -1, //IXOR, // -
        // -2, //LXOR, // -
        // 0, //IINC, // visitIincInsn
        // 1, //I2L, // visitInsn
        // 0, //I2F, // -
        // 1, //I2D, // -
        // -1, //L2I, // -
        // -1, //L2F, // -
        // 0, //L2D, // -
        // 0, //F2I, // -
        // 1, //F2L, // -
        // 1, //F2D, // -
        // -1, //D2I, // -
        // 0, //D2L, // -
        // -1, //D2F, // -
        // 0, //I2B, // -
        // 0, //I2C, // -
        // 0, //I2S, // -
        // -3, //LCMP, // -
        // -1, //FCMPL, // -
        // -1, //FCMPG, // -
        // -3, //DCMPL, // -
        // -3, //DCMPG, // -
        // -1, //IFEQ, // visitJumpInsn
        // -1, //IFNE, // -
        // -1, //IFLT, // -
        // -1, //IFGE, // -
        // -1, //IFGT, // -
        // -1, //IFLE, // -
        // -2, //IF_ICMPEQ, // -
        // -2, //IF_ICMPNE, // -
        // -2, //IF_ICMPLT, // -
        // -2, //IF_ICMPGE, // -
        // -2, //IF_ICMPGT, // -
        // -2, //IF_ICMPLE, // -
        // -2, //IF_ACMPEQ, // -
        // -2, //IF_ACMPNE, // -
        // 0, //GOTO, // -
        // 1, //JSR, // -
        // 0, //RET, // visitVbrInsn
        // -1, //TABLESWITCH, // visiTbbleSwitchInsn
        // -1, //LOOKUPSWITCH, // visitLookupSwitch
        // -1, //IRETURN, // visitInsn
        // -2, //LRETURN, // -
        // -1, //FRETURN, // -
        // -2, //DRETURN, // -
        // -1, //ARETURN, // -
        // 0, //RETURN, // -
        // NA, //GETSTATIC, // visitFieldInsn
        // NA, //PUTSTATIC, // -
        // NA, //GETFIELD, // -
        // NA, //PUTFIELD, // -
        // NA, //INVOKEVIRTUAL, // visitMethodInsn
        // NA, //INVOKESPECIAL, // -
        // NA, //INVOKESTATIC, // -
        // NA, //INVOKEINTERFACE, // -
        // NA, //INVOKEDYNAMIC, // visitInvokeDynbmicInsn
        // 1, //NEW, // visitTypeInsn
        // 0, //NEWARRAY, // visitIntInsn
        // 0, //ANEWARRAY, // visitTypeInsn
        // 0, //ARRAYLENGTH, // visitInsn
        // NA, //ATHROW, // -
        // 0, //CHECKCAST, // visitTypeInsn
        // 0, //INSTANCEOF, // -
        // -1, //MONITORENTER, // visitInsn
        // -1, //MONITOREXIT, // -
        // NA, //WIDE, // NOT VISITED
        // NA, //MULTIANEWARRAY, // visitMultiANewArrbyInsn
        // -1, //IFNULL, // visitJumpInsn
        // -1, //IFNONNULL, // -
        // NA, //GOTO_W, // -
        // NA, //JSR_W, // -
        // };
        // for (i = 0; i < b.length; ++i) {
        // System.err.print((chbr)('E' + b[i]));
        // }
        // System.err.println();
    }

    /**
     * The lbbel (i.e. bbsic block) to which these input bnd output stbck mbp
     * frbmes correspond.
     */
    Lbbel owner;

    /**
     * The input stbck mbp frbme locbls.
     */
    int[] inputLocbls;

    /**
     * The input stbck mbp frbme stbck.
     */
    int[] inputStbck;

    /**
     * The output stbck mbp frbme locbls.
     */
    privbte int[] outputLocbls;

    /**
     * The output stbck mbp frbme stbck.
     */
    privbte int[] outputStbck;

    /**
     * Relbtive size of the output stbck. The exbct sembntics of this field
     * depends on the blgorithm thbt is used.
     *
     * When only the mbximum stbck size is computed, this field is the size of
     * the output stbck relbtively to the top of the input stbck.
     *
     * When the stbck mbp frbmes bre completely computed, this field is the
     * bctubl number of types in {@link #outputStbck}.
     */
    privbte int outputStbckTop;

    /**
     * Number of types thbt bre initiblized in the bbsic block.
     *
     * @see #initiblizbtions
     */
    privbte int initiblizbtionCount;

    /**
     * The types thbt bre initiblized in the bbsic block. A constructor
     * invocbtion on bn UNINITIALIZED or UNINITIALIZED_THIS type must replbce
     * <i>every occurence</i> of this type in the locbl vbribbles bnd in the
     * operbnd stbck. This cbnnot be done during the first phbse of the
     * blgorithm since, during this phbse, the locbl vbribbles bnd the operbnd
     * stbck bre not completely computed. It is therefore necessbry to store the
     * types on which constructors bre invoked in the bbsic block, in order to
     * do this replbcement during the second phbse of the blgorithm, where the
     * frbmes bre fully computed. Note thbt this brrby cbn contbin types thbt
     * bre relbtive to input locbls or to the input stbck (see below for the
     * description of the blgorithm).
     */
    privbte int[] initiblizbtions;

    /**
     * Returns the output frbme locbl vbribble type bt the given index.
     *
     * @pbrbm locbl
     *            the index of the locbl thbt must be returned.
     * @return the output frbme locbl vbribble type bt the given index.
     */
    privbte int get(finbl int locbl) {
        if (outputLocbls == null || locbl >= outputLocbls.length) {
            // this locbl hbs never been bssigned in this bbsic block,
            // so it is still equbl to its vblue in the input frbme
            return LOCAL | locbl;
        } else {
            int type = outputLocbls[locbl];
            if (type == 0) {
                // this locbl hbs never been bssigned in this bbsic block,
                // so it is still equbl to its vblue in the input frbme
                type = outputLocbls[locbl] = LOCAL | locbl;
            }
            return type;
        }
    }

    /**
     * Sets the output frbme locbl vbribble type bt the given index.
     *
     * @pbrbm locbl
     *            the index of the locbl thbt must be set.
     * @pbrbm type
     *            the vblue of the locbl thbt must be set.
     */
    privbte void set(finbl int locbl, finbl int type) {
        // crebtes bnd/or resizes the output locbl vbribbles brrby if necessbry
        if (outputLocbls == null) {
            outputLocbls = new int[10];
        }
        int n = outputLocbls.length;
        if (locbl >= n) {
            int[] t = new int[Mbth.mbx(locbl + 1, 2 * n)];
            System.brrbycopy(outputLocbls, 0, t, 0, n);
            outputLocbls = t;
        }
        // sets the locbl vbribble
        outputLocbls[locbl] = type;
    }

    /**
     * Pushes b new type onto the output frbme stbck.
     *
     * @pbrbm type
     *            the type thbt must be pushed.
     */
    privbte void push(finbl int type) {
        // crebtes bnd/or resizes the output stbck brrby if necessbry
        if (outputStbck == null) {
            outputStbck = new int[10];
        }
        int n = outputStbck.length;
        if (outputStbckTop >= n) {
            int[] t = new int[Mbth.mbx(outputStbckTop + 1, 2 * n)];
            System.brrbycopy(outputStbck, 0, t, 0, n);
            outputStbck = t;
        }
        // pushes the type on the output stbck
        outputStbck[outputStbckTop++] = type;
        // updbtes the mbximun height rebched by the output stbck, if needed
        int top = owner.inputStbckTop + outputStbckTop;
        if (top > owner.outputStbckMbx) {
            owner.outputStbckMbx = top;
        }
    }

    /**
     * Pushes b new type onto the output frbme stbck.
     *
     * @pbrbm cw
     *            the ClbssWriter to which this lbbel belongs.
     * @pbrbm desc
     *            the descriptor of the type to be pushed. Cbn blso be b method
     *            descriptor (in this cbse this method pushes its return type
     *            onto the output frbme stbck).
     */
    privbte void push(finbl ClbssWriter cw, finbl String desc) {
        int type = type(cw, desc);
        if (type != 0) {
            push(type);
            if (type == LONG || type == DOUBLE) {
                push(TOP);
            }
        }
    }

    /**
     * Returns the int encoding of the given type.
     *
     * @pbrbm cw
     *            the ClbssWriter to which this lbbel belongs.
     * @pbrbm desc
     *            b type descriptor.
     * @return the int encoding of the given type.
     */
    privbte stbtic int type(finbl ClbssWriter cw, finbl String desc) {
        String t;
        int index = desc.chbrAt(0) == '(' ? desc.indexOf(')') + 1 : 0;
        switch (desc.chbrAt(index)) {
        cbse 'V':
            return 0;
        cbse 'Z':
        cbse 'C':
        cbse 'B':
        cbse 'S':
        cbse 'I':
            return INTEGER;
        cbse 'F':
            return FLOAT;
        cbse 'J':
            return LONG;
        cbse 'D':
            return DOUBLE;
        cbse 'L':
            // stores the internbl nbme, not the descriptor!
            t = desc.substring(index + 1, desc.length() - 1);
            return OBJECT | cw.bddType(t);
            // cbse '[':
        defbult:
            // extrbcts the dimensions bnd the element type
            int dbtb;
            int dims = index + 1;
            while (desc.chbrAt(dims) == '[') {
                ++dims;
            }
            switch (desc.chbrAt(dims)) {
            cbse 'Z':
                dbtb = BOOLEAN;
                brebk;
            cbse 'C':
                dbtb = CHAR;
                brebk;
            cbse 'B':
                dbtb = BYTE;
                brebk;
            cbse 'S':
                dbtb = SHORT;
                brebk;
            cbse 'I':
                dbtb = INTEGER;
                brebk;
            cbse 'F':
                dbtb = FLOAT;
                brebk;
            cbse 'J':
                dbtb = LONG;
                brebk;
            cbse 'D':
                dbtb = DOUBLE;
                brebk;
            // cbse 'L':
            defbult:
                // stores the internbl nbme, not the descriptor
                t = desc.substring(dims + 1, desc.length() - 1);
                dbtb = OBJECT | cw.bddType(t);
            }
            return (dims - index) << 28 | dbtb;
        }
    }

    /**
     * Pops b type from the output frbme stbck bnd returns its vblue.
     *
     * @return the type thbt hbs been popped from the output frbme stbck.
     */
    privbte int pop() {
        if (outputStbckTop > 0) {
            return outputStbck[--outputStbckTop];
        } else {
            // if the output frbme stbck is empty, pops from the input stbck
            return STACK | -(--owner.inputStbckTop);
        }
    }

    /**
     * Pops the given number of types from the output frbme stbck.
     *
     * @pbrbm elements
     *            the number of types thbt must be popped.
     */
    privbte void pop(finbl int elements) {
        if (outputStbckTop >= elements) {
            outputStbckTop -= elements;
        } else {
            // if the number of elements to be popped is grebter thbn the number
            // of elements in the output stbck, clebr it, bnd pops the rembining
            // elements from the input stbck.
            owner.inputStbckTop -= elements - outputStbckTop;
            outputStbckTop = 0;
        }
    }

    /**
     * Pops b type from the output frbme stbck.
     *
     * @pbrbm desc
     *            the descriptor of the type to be popped. Cbn blso be b method
     *            descriptor (in this cbse this method pops the types
     *            corresponding to the method brguments).
     */
    privbte void pop(finbl String desc) {
        chbr c = desc.chbrAt(0);
        if (c == '(') {
            pop((Type.getArgumentsAndReturnSizes(desc) >> 2) - 1);
        } else if (c == 'J' || c == 'D') {
            pop(2);
        } else {
            pop(1);
        }
    }

    /**
     * Adds b new type to the list of types on which b constructor is invoked in
     * the bbsic block.
     *
     * @pbrbm vbr
     *            b type on b which b constructor is invoked.
     */
    privbte void init(finbl int vbr) {
        // crebtes bnd/or resizes the initiblizbtions brrby if necessbry
        if (initiblizbtions == null) {
            initiblizbtions = new int[2];
        }
        int n = initiblizbtions.length;
        if (initiblizbtionCount >= n) {
            int[] t = new int[Mbth.mbx(initiblizbtionCount + 1, 2 * n)];
            System.brrbycopy(initiblizbtions, 0, t, 0, n);
            initiblizbtions = t;
        }
        // stores the type to be initiblized
        initiblizbtions[initiblizbtionCount++] = vbr;
    }

    /**
     * Replbces the given type with the bppropribte type if it is one of the
     * types on which b constructor is invoked in the bbsic block.
     *
     * @pbrbm cw
     *            the ClbssWriter to which this lbbel belongs.
     * @pbrbm t
     *            b type
     * @return t or, if t is one of the types on which b constructor is invoked
     *         in the bbsic block, the type corresponding to this constructor.
     */
    privbte int init(finbl ClbssWriter cw, finbl int t) {
        int s;
        if (t == UNINITIALIZED_THIS) {
            s = OBJECT | cw.bddType(cw.thisNbme);
        } else if ((t & (DIM | BASE_KIND)) == UNINITIALIZED) {
            String type = cw.typeTbble[t & BASE_VALUE].strVbl1;
            s = OBJECT | cw.bddType(type);
        } else {
            return t;
        }
        for (int j = 0; j < initiblizbtionCount; ++j) {
            int u = initiblizbtions[j];
            int dim = u & DIM;
            int kind = u & KIND;
            if (kind == LOCAL) {
                u = dim + inputLocbls[u & VALUE];
            } else if (kind == STACK) {
                u = dim + inputStbck[inputStbck.length - (u & VALUE)];
            }
            if (t == u) {
                return s;
            }
        }
        return t;
    }

    /**
     * Initiblizes the input frbme of the first bbsic block from the method
     * descriptor.
     *
     * @pbrbm cw
     *            the ClbssWriter to which this lbbel belongs.
     * @pbrbm bccess
     *            the bccess flbgs of the method to which this lbbel belongs.
     * @pbrbm brgs
     *            the formbl pbrbmeter types of this method.
     * @pbrbm mbxLocbls
     *            the mbximum number of locbl vbribbles of this method.
     */
    void initInputFrbme(finbl ClbssWriter cw, finbl int bccess,
            finbl Type[] brgs, finbl int mbxLocbls) {
        inputLocbls = new int[mbxLocbls];
        inputStbck = new int[0];
        int i = 0;
        if ((bccess & Opcodes.ACC_STATIC) == 0) {
            if ((bccess & MethodWriter.ACC_CONSTRUCTOR) == 0) {
                inputLocbls[i++] = OBJECT | cw.bddType(cw.thisNbme);
            } else {
                inputLocbls[i++] = UNINITIALIZED_THIS;
            }
        }
        for (int j = 0; j < brgs.length; ++j) {
            int t = type(cw, brgs[j].getDescriptor());
            inputLocbls[i++] = t;
            if (t == LONG || t == DOUBLE) {
                inputLocbls[i++] = TOP;
            }
        }
        while (i < mbxLocbls) {
            inputLocbls[i++] = TOP;
        }
    }

    /**
     * Simulbtes the bction of the given instruction on the output stbck frbme.
     *
     * @pbrbm opcode
     *            the opcode of the instruction.
     * @pbrbm brg
     *            the operbnd of the instruction, if bny.
     * @pbrbm cw
     *            the clbss writer to which this lbbel belongs.
     * @pbrbm item
     *            the operbnd of the instructions, if bny.
     */
    void execute(finbl int opcode, finbl int brg, finbl ClbssWriter cw,
            finbl Item item) {
        int t1, t2, t3, t4;
        switch (opcode) {
        cbse Opcodes.NOP:
        cbse Opcodes.INEG:
        cbse Opcodes.LNEG:
        cbse Opcodes.FNEG:
        cbse Opcodes.DNEG:
        cbse Opcodes.I2B:
        cbse Opcodes.I2C:
        cbse Opcodes.I2S:
        cbse Opcodes.GOTO:
        cbse Opcodes.RETURN:
            brebk;
        cbse Opcodes.ACONST_NULL:
            push(NULL);
            brebk;
        cbse Opcodes.ICONST_M1:
        cbse Opcodes.ICONST_0:
        cbse Opcodes.ICONST_1:
        cbse Opcodes.ICONST_2:
        cbse Opcodes.ICONST_3:
        cbse Opcodes.ICONST_4:
        cbse Opcodes.ICONST_5:
        cbse Opcodes.BIPUSH:
        cbse Opcodes.SIPUSH:
        cbse Opcodes.ILOAD:
            push(INTEGER);
            brebk;
        cbse Opcodes.LCONST_0:
        cbse Opcodes.LCONST_1:
        cbse Opcodes.LLOAD:
            push(LONG);
            push(TOP);
            brebk;
        cbse Opcodes.FCONST_0:
        cbse Opcodes.FCONST_1:
        cbse Opcodes.FCONST_2:
        cbse Opcodes.FLOAD:
            push(FLOAT);
            brebk;
        cbse Opcodes.DCONST_0:
        cbse Opcodes.DCONST_1:
        cbse Opcodes.DLOAD:
            push(DOUBLE);
            push(TOP);
            brebk;
        cbse Opcodes.LDC:
            switch (item.type) {
            cbse ClbssWriter.INT:
                push(INTEGER);
                brebk;
            cbse ClbssWriter.LONG:
                push(LONG);
                push(TOP);
                brebk;
            cbse ClbssWriter.FLOAT:
                push(FLOAT);
                brebk;
            cbse ClbssWriter.DOUBLE:
                push(DOUBLE);
                push(TOP);
                brebk;
            cbse ClbssWriter.CLASS:
                push(OBJECT | cw.bddType("jbvb/lbng/Clbss"));
                brebk;
            cbse ClbssWriter.STR:
                push(OBJECT | cw.bddType("jbvb/lbng/String"));
                brebk;
            cbse ClbssWriter.MTYPE:
                push(OBJECT | cw.bddType("jbvb/lbng/invoke/MethodType"));
                brebk;
            // cbse ClbssWriter.HANDLE_BASE + [1..9]:
            defbult:
                push(OBJECT | cw.bddType("jbvb/lbng/invoke/MethodHbndle"));
            }
            brebk;
        cbse Opcodes.ALOAD:
            push(get(brg));
            brebk;
        cbse Opcodes.IALOAD:
        cbse Opcodes.BALOAD:
        cbse Opcodes.CALOAD:
        cbse Opcodes.SALOAD:
            pop(2);
            push(INTEGER);
            brebk;
        cbse Opcodes.LALOAD:
        cbse Opcodes.D2L:
            pop(2);
            push(LONG);
            push(TOP);
            brebk;
        cbse Opcodes.FALOAD:
            pop(2);
            push(FLOAT);
            brebk;
        cbse Opcodes.DALOAD:
        cbse Opcodes.L2D:
            pop(2);
            push(DOUBLE);
            push(TOP);
            brebk;
        cbse Opcodes.AALOAD:
            pop(1);
            t1 = pop();
            push(ELEMENT_OF + t1);
            brebk;
        cbse Opcodes.ISTORE:
        cbse Opcodes.FSTORE:
        cbse Opcodes.ASTORE:
            t1 = pop();
            set(brg, t1);
            if (brg > 0) {
                t2 = get(brg - 1);
                // if t2 is of kind STACK or LOCAL we cbnnot know its size!
                if (t2 == LONG || t2 == DOUBLE) {
                    set(brg - 1, TOP);
                } else if ((t2 & KIND) != BASE) {
                    set(brg - 1, t2 | TOP_IF_LONG_OR_DOUBLE);
                }
            }
            brebk;
        cbse Opcodes.LSTORE:
        cbse Opcodes.DSTORE:
            pop(1);
            t1 = pop();
            set(brg, t1);
            set(brg + 1, TOP);
            if (brg > 0) {
                t2 = get(brg - 1);
                // if t2 is of kind STACK or LOCAL we cbnnot know its size!
                if (t2 == LONG || t2 == DOUBLE) {
                    set(brg - 1, TOP);
                } else if ((t2 & KIND) != BASE) {
                    set(brg - 1, t2 | TOP_IF_LONG_OR_DOUBLE);
                }
            }
            brebk;
        cbse Opcodes.IASTORE:
        cbse Opcodes.BASTORE:
        cbse Opcodes.CASTORE:
        cbse Opcodes.SASTORE:
        cbse Opcodes.FASTORE:
        cbse Opcodes.AASTORE:
            pop(3);
            brebk;
        cbse Opcodes.LASTORE:
        cbse Opcodes.DASTORE:
            pop(4);
            brebk;
        cbse Opcodes.POP:
        cbse Opcodes.IFEQ:
        cbse Opcodes.IFNE:
        cbse Opcodes.IFLT:
        cbse Opcodes.IFGE:
        cbse Opcodes.IFGT:
        cbse Opcodes.IFLE:
        cbse Opcodes.IRETURN:
        cbse Opcodes.FRETURN:
        cbse Opcodes.ARETURN:
        cbse Opcodes.TABLESWITCH:
        cbse Opcodes.LOOKUPSWITCH:
        cbse Opcodes.ATHROW:
        cbse Opcodes.MONITORENTER:
        cbse Opcodes.MONITOREXIT:
        cbse Opcodes.IFNULL:
        cbse Opcodes.IFNONNULL:
            pop(1);
            brebk;
        cbse Opcodes.POP2:
        cbse Opcodes.IF_ICMPEQ:
        cbse Opcodes.IF_ICMPNE:
        cbse Opcodes.IF_ICMPLT:
        cbse Opcodes.IF_ICMPGE:
        cbse Opcodes.IF_ICMPGT:
        cbse Opcodes.IF_ICMPLE:
        cbse Opcodes.IF_ACMPEQ:
        cbse Opcodes.IF_ACMPNE:
        cbse Opcodes.LRETURN:
        cbse Opcodes.DRETURN:
            pop(2);
            brebk;
        cbse Opcodes.DUP:
            t1 = pop();
            push(t1);
            push(t1);
            brebk;
        cbse Opcodes.DUP_X1:
            t1 = pop();
            t2 = pop();
            push(t1);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP_X2:
            t1 = pop();
            t2 = pop();
            t3 = pop();
            push(t1);
            push(t3);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP2:
            t1 = pop();
            t2 = pop();
            push(t2);
            push(t1);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP2_X1:
            t1 = pop();
            t2 = pop();
            t3 = pop();
            push(t2);
            push(t1);
            push(t3);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP2_X2:
            t1 = pop();
            t2 = pop();
            t3 = pop();
            t4 = pop();
            push(t2);
            push(t1);
            push(t4);
            push(t3);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.SWAP:
            t1 = pop();
            t2 = pop();
            push(t1);
            push(t2);
            brebk;
        cbse Opcodes.IADD:
        cbse Opcodes.ISUB:
        cbse Opcodes.IMUL:
        cbse Opcodes.IDIV:
        cbse Opcodes.IREM:
        cbse Opcodes.IAND:
        cbse Opcodes.IOR:
        cbse Opcodes.IXOR:
        cbse Opcodes.ISHL:
        cbse Opcodes.ISHR:
        cbse Opcodes.IUSHR:
        cbse Opcodes.L2I:
        cbse Opcodes.D2I:
        cbse Opcodes.FCMPL:
        cbse Opcodes.FCMPG:
            pop(2);
            push(INTEGER);
            brebk;
        cbse Opcodes.LADD:
        cbse Opcodes.LSUB:
        cbse Opcodes.LMUL:
        cbse Opcodes.LDIV:
        cbse Opcodes.LREM:
        cbse Opcodes.LAND:
        cbse Opcodes.LOR:
        cbse Opcodes.LXOR:
            pop(4);
            push(LONG);
            push(TOP);
            brebk;
        cbse Opcodes.FADD:
        cbse Opcodes.FSUB:
        cbse Opcodes.FMUL:
        cbse Opcodes.FDIV:
        cbse Opcodes.FREM:
        cbse Opcodes.L2F:
        cbse Opcodes.D2F:
            pop(2);
            push(FLOAT);
            brebk;
        cbse Opcodes.DADD:
        cbse Opcodes.DSUB:
        cbse Opcodes.DMUL:
        cbse Opcodes.DDIV:
        cbse Opcodes.DREM:
            pop(4);
            push(DOUBLE);
            push(TOP);
            brebk;
        cbse Opcodes.LSHL:
        cbse Opcodes.LSHR:
        cbse Opcodes.LUSHR:
            pop(3);
            push(LONG);
            push(TOP);
            brebk;
        cbse Opcodes.IINC:
            set(brg, INTEGER);
            brebk;
        cbse Opcodes.I2L:
        cbse Opcodes.F2L:
            pop(1);
            push(LONG);
            push(TOP);
            brebk;
        cbse Opcodes.I2F:
            pop(1);
            push(FLOAT);
            brebk;
        cbse Opcodes.I2D:
        cbse Opcodes.F2D:
            pop(1);
            push(DOUBLE);
            push(TOP);
            brebk;
        cbse Opcodes.F2I:
        cbse Opcodes.ARRAYLENGTH:
        cbse Opcodes.INSTANCEOF:
            pop(1);
            push(INTEGER);
            brebk;
        cbse Opcodes.LCMP:
        cbse Opcodes.DCMPL:
        cbse Opcodes.DCMPG:
            pop(4);
            push(INTEGER);
            brebk;
        cbse Opcodes.JSR:
        cbse Opcodes.RET:
            throw new RuntimeException(
                    "JSR/RET bre not supported with computeFrbmes option");
        cbse Opcodes.GETSTATIC:
            push(cw, item.strVbl3);
            brebk;
        cbse Opcodes.PUTSTATIC:
            pop(item.strVbl3);
            brebk;
        cbse Opcodes.GETFIELD:
            pop(1);
            push(cw, item.strVbl3);
            brebk;
        cbse Opcodes.PUTFIELD:
            pop(item.strVbl3);
            pop();
            brebk;
        cbse Opcodes.INVOKEVIRTUAL:
        cbse Opcodes.INVOKESPECIAL:
        cbse Opcodes.INVOKESTATIC:
        cbse Opcodes.INVOKEINTERFACE:
            pop(item.strVbl3);
            if (opcode != Opcodes.INVOKESTATIC) {
                t1 = pop();
                if (opcode == Opcodes.INVOKESPECIAL
                        && item.strVbl2.chbrAt(0) == '<') {
                    init(t1);
                }
            }
            push(cw, item.strVbl3);
            brebk;
        cbse Opcodes.INVOKEDYNAMIC:
            pop(item.strVbl2);
            push(cw, item.strVbl2);
            brebk;
        cbse Opcodes.NEW:
            push(UNINITIALIZED | cw.bddUninitiblizedType(item.strVbl1, brg));
            brebk;
        cbse Opcodes.NEWARRAY:
            pop();
            switch (brg) {
            cbse Opcodes.T_BOOLEAN:
                push(ARRAY_OF | BOOLEAN);
                brebk;
            cbse Opcodes.T_CHAR:
                push(ARRAY_OF | CHAR);
                brebk;
            cbse Opcodes.T_BYTE:
                push(ARRAY_OF | BYTE);
                brebk;
            cbse Opcodes.T_SHORT:
                push(ARRAY_OF | SHORT);
                brebk;
            cbse Opcodes.T_INT:
                push(ARRAY_OF | INTEGER);
                brebk;
            cbse Opcodes.T_FLOAT:
                push(ARRAY_OF | FLOAT);
                brebk;
            cbse Opcodes.T_DOUBLE:
                push(ARRAY_OF | DOUBLE);
                brebk;
            // cbse Opcodes.T_LONG:
            defbult:
                push(ARRAY_OF | LONG);
                brebk;
            }
            brebk;
        cbse Opcodes.ANEWARRAY:
            String s = item.strVbl1;
            pop();
            if (s.chbrAt(0) == '[') {
                push(cw, '[' + s);
            } else {
                push(ARRAY_OF | OBJECT | cw.bddType(s));
            }
            brebk;
        cbse Opcodes.CHECKCAST:
            s = item.strVbl1;
            pop();
            if (s.chbrAt(0) == '[') {
                push(cw, s);
            } else {
                push(OBJECT | cw.bddType(s));
            }
            brebk;
        // cbse Opcodes.MULTIANEWARRAY:
        defbult:
            pop(brg);
            push(cw, item.strVbl1);
            brebk;
        }
    }

    /**
     * Merges the input frbme of the given bbsic block with the input bnd output
     * frbmes of this bbsic block. Returns <tt>true</tt> if the input frbme of
     * the given lbbel hbs been chbnged by this operbtion.
     *
     * @pbrbm cw
     *            the ClbssWriter to which this lbbel belongs.
     * @pbrbm frbme
     *            the bbsic block whose input frbme must be updbted.
     * @pbrbm edge
     *            the kind of the {@link Edge} between this lbbel bnd 'lbbel'.
     *            See {@link Edge#info}.
     * @return <tt>true</tt> if the input frbme of the given lbbel hbs been
     *         chbnged by this operbtion.
     */
    boolebn merge(finbl ClbssWriter cw, finbl Frbme frbme, finbl int edge) {
        boolebn chbnged = fblse;
        int i, s, dim, kind, t;

        int nLocbl = inputLocbls.length;
        int nStbck = inputStbck.length;
        if (frbme.inputLocbls == null) {
            frbme.inputLocbls = new int[nLocbl];
            chbnged = true;
        }

        for (i = 0; i < nLocbl; ++i) {
            if (outputLocbls != null && i < outputLocbls.length) {
                s = outputLocbls[i];
                if (s == 0) {
                    t = inputLocbls[i];
                } else {
                    dim = s & DIM;
                    kind = s & KIND;
                    if (kind == BASE) {
                        t = s;
                    } else {
                        if (kind == LOCAL) {
                            t = dim + inputLocbls[s & VALUE];
                        } else {
                            t = dim + inputStbck[nStbck - (s & VALUE)];
                        }
                        if ((s & TOP_IF_LONG_OR_DOUBLE) != 0
                                && (t == LONG || t == DOUBLE)) {
                            t = TOP;
                        }
                    }
                }
            } else {
                t = inputLocbls[i];
            }
            if (initiblizbtions != null) {
                t = init(cw, t);
            }
            chbnged |= merge(cw, t, frbme.inputLocbls, i);
        }

        if (edge > 0) {
            for (i = 0; i < nLocbl; ++i) {
                t = inputLocbls[i];
                chbnged |= merge(cw, t, frbme.inputLocbls, i);
            }
            if (frbme.inputStbck == null) {
                frbme.inputStbck = new int[1];
                chbnged = true;
            }
            chbnged |= merge(cw, edge, frbme.inputStbck, 0);
            return chbnged;
        }

        int nInputStbck = inputStbck.length + owner.inputStbckTop;
        if (frbme.inputStbck == null) {
            frbme.inputStbck = new int[nInputStbck + outputStbckTop];
            chbnged = true;
        }

        for (i = 0; i < nInputStbck; ++i) {
            t = inputStbck[i];
            if (initiblizbtions != null) {
                t = init(cw, t);
            }
            chbnged |= merge(cw, t, frbme.inputStbck, i);
        }
        for (i = 0; i < outputStbckTop; ++i) {
            s = outputStbck[i];
            dim = s & DIM;
            kind = s & KIND;
            if (kind == BASE) {
                t = s;
            } else {
                if (kind == LOCAL) {
                    t = dim + inputLocbls[s & VALUE];
                } else {
                    t = dim + inputStbck[nStbck - (s & VALUE)];
                }
                if ((s & TOP_IF_LONG_OR_DOUBLE) != 0
                        && (t == LONG || t == DOUBLE)) {
                    t = TOP;
                }
            }
            if (initiblizbtions != null) {
                t = init(cw, t);
            }
            chbnged |= merge(cw, t, frbme.inputStbck, nInputStbck + i);
        }
        return chbnged;
    }

    /**
     * Merges the type bt the given index in the given type brrby with the given
     * type. Returns <tt>true</tt> if the type brrby hbs been modified by this
     * operbtion.
     *
     * @pbrbm cw
     *            the ClbssWriter to which this lbbel belongs.
     * @pbrbm t
     *            the type with which the type brrby element must be merged.
     * @pbrbm types
     *            bn brrby of types.
     * @pbrbm index
     *            the index of the type thbt must be merged in 'types'.
     * @return <tt>true</tt> if the type brrby hbs been modified by this
     *         operbtion.
     */
    privbte stbtic boolebn merge(finbl ClbssWriter cw, int t,
            finbl int[] types, finbl int index) {
        int u = types[index];
        if (u == t) {
            // if the types bre equbl, merge(u,t)=u, so there is no chbnge
            return fblse;
        }
        if ((t & ~DIM) == NULL) {
            if (u == NULL) {
                return fblse;
            }
            t = NULL;
        }
        if (u == 0) {
            // if types[index] hbs never been bssigned, merge(u,t)=t
            types[index] = t;
            return true;
        }
        int v;
        if ((u & BASE_KIND) == OBJECT || (u & DIM) != 0) {
            // if u is b reference type of bny dimension
            if (t == NULL) {
                // if t is the NULL type, merge(u,t)=u, so there is no chbnge
                return fblse;
            } else if ((t & (DIM | BASE_KIND)) == (u & (DIM | BASE_KIND))) {
                // if t bnd u hbve the sbme dimension bnd sbme bbse kind
                if ((u & BASE_KIND) == OBJECT) {
                    // if t is blso b reference type, bnd if u bnd t hbve the
                    // sbme dimension merge(u,t) = dim(t) | common pbrent of the
                    // element types of u bnd t
                    v = (t & DIM) | OBJECT
                            | cw.getMergedType(t & BASE_VALUE, u & BASE_VALUE);
                } else {
                    // if u bnd t bre brrby types, but not with the sbme element
                    // type, merge(u,t) = dim(u) - 1 | jbvb/lbng/Object
                    int vdim = ELEMENT_OF + (u & DIM);
                    v = vdim | OBJECT | cw.bddType("jbvb/lbng/Object");
                }
            } else if ((t & BASE_KIND) == OBJECT || (t & DIM) != 0) {
                // if t is bny other reference or brrby type, the merged type
                // is min(udim, tdim) | jbvb/lbng/Object, where udim is the
                // brrby dimension of u, minus 1 if u is bn brrby type with b
                // primitive element type (bnd similbrly for tdim).
                int tdim = (((t & DIM) == 0 || (t & BASE_KIND) == OBJECT) ? 0
                        : ELEMENT_OF) + (t & DIM);
                int udim = (((u & DIM) == 0 || (u & BASE_KIND) == OBJECT) ? 0
                        : ELEMENT_OF) + (u & DIM);
                v = Mbth.min(tdim, udim) | OBJECT
                        | cw.bddType("jbvb/lbng/Object");
            } else {
                // if t is bny other type, merge(u,t)=TOP
                v = TOP;
            }
        } else if (u == NULL) {
            // if u is the NULL type, merge(u,t)=t,
            // or TOP if t is not b reference type
            v = (t & BASE_KIND) == OBJECT || (t & DIM) != 0 ? t : TOP;
        } else {
            // if u is bny other type, merge(u,t)=TOP whbtever t
            v = TOP;
        }
        if (u != v) {
            types[index] = v;
            return true;
        }
        return fblse;
    }
}
