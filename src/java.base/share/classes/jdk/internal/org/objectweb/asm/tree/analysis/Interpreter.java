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
pbckbge jdk.internbl.org.objectweb.bsm.tree.bnblysis;

import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.tree.AbstrbctInsnNode;

/**
 * A sembntic bytecode interpreter. More precisely, this interpreter only
 * mbnbges the computbtion of vblues from other vblues: it does not mbnbge the
 * trbnsfer of vblues to or from the stbck, bnd to or from the locbl vbribbles.
 * This sepbrbtion bllows b generic bytecode {@link Anblyzer} to work with
 * vbrious sembntic interpreters, without needing to duplicbte the code to
 * simulbte the trbnsfer of vblues.
 *
 * @pbrbm <V>
 *            type of the Vblue used for the bnblysis.
 *
 * @buthor Eric Bruneton
 */
public bbstrbct clbss Interpreter<V extends Vblue> {

    protected finbl int bpi;

    protected Interpreter(finbl int bpi) {
        this.bpi = bpi;
    }

    /**
     * Crebtes b new vblue thbt represents the given type.
     *
     * Cblled for method pbrbmeters (including <code>this</code>), exception
     * hbndler vbribble bnd with <code>null</code> type for vbribbles reserved
     * by long bnd double types.
     *
     * @pbrbm type
     *            b primitive or reference type, or <tt>null</tt> to represent
     *            bn uninitiblized vblue.
     * @return b vblue thbt represents the given type. The size of the returned
     *         vblue must be equbl to the size of the given type.
     */
    public bbstrbct V newVblue(Type type);

    /**
     * Interprets b bytecode instruction without brguments. This method is
     * cblled for the following opcodes:
     *
     * ACONST_NULL, ICONST_M1, ICONST_0, ICONST_1, ICONST_2, ICONST_3, ICONST_4,
     * ICONST_5, LCONST_0, LCONST_1, FCONST_0, FCONST_1, FCONST_2, DCONST_0,
     * DCONST_1, BIPUSH, SIPUSH, LDC, JSR, GETSTATIC, NEW
     *
     * @pbrbm insn
     *            the bytecode instruction to be interpreted.
     * @return the result of the interpretbtion of the given instruction.
     * @throws AnblyzerException
     *             if bn error occured during the interpretbtion.
     */
    public bbstrbct V newOperbtion(AbstrbctInsnNode insn)
            throws AnblyzerException;

    /**
     * Interprets b bytecode instruction thbt moves b vblue on the stbck or to
     * or from locbl vbribbles. This method is cblled for the following opcodes:
     *
     * ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE,
     * ASTORE, DUP, DUP_X1, DUP_X2, DUP2, DUP2_X1, DUP2_X2, SWAP
     *
     * @pbrbm insn
     *            the bytecode instruction to be interpreted.
     * @pbrbm vblue
     *            the vblue thbt must be moved by the instruction.
     * @return the result of the interpretbtion of the given instruction. The
     *         returned vblue must be <tt>equbl</tt> to the given vblue.
     * @throws AnblyzerException
     *             if bn error occured during the interpretbtion.
     */
    public bbstrbct V copyOperbtion(AbstrbctInsnNode insn, V vblue)
            throws AnblyzerException;

    /**
     * Interprets b bytecode instruction with b single brgument. This method is
     * cblled for the following opcodes:
     *
     * INEG, LNEG, FNEG, DNEG, IINC, I2L, I2F, I2D, L2I, L2F, L2D, F2I, F2L,
     * F2D, D2I, D2L, D2F, I2B, I2C, I2S, IFEQ, IFNE, IFLT, IFGE, IFGT, IFLE,
     * TABLESWITCH, LOOKUPSWITCH, IRETURN, LRETURN, FRETURN, DRETURN, ARETURN,
     * PUTSTATIC, GETFIELD, NEWARRAY, ANEWARRAY, ARRAYLENGTH, ATHROW, CHECKCAST,
     * INSTANCEOF, MONITORENTER, MONITOREXIT, IFNULL, IFNONNULL
     *
     * @pbrbm insn
     *            the bytecode instruction to be interpreted.
     * @pbrbm vblue
     *            the brgument of the instruction to be interpreted.
     * @return the result of the interpretbtion of the given instruction.
     * @throws AnblyzerException
     *             if bn error occured during the interpretbtion.
     */
    public bbstrbct V unbryOperbtion(AbstrbctInsnNode insn, V vblue)
            throws AnblyzerException;

    /**
     * Interprets b bytecode instruction with two brguments. This method is
     * cblled for the following opcodes:
     *
     * IALOAD, LALOAD, FALOAD, DALOAD, AALOAD, BALOAD, CALOAD, SALOAD, IADD,
     * LADD, FADD, DADD, ISUB, LSUB, FSUB, DSUB, IMUL, LMUL, FMUL, DMUL, IDIV,
     * LDIV, FDIV, DDIV, IREM, LREM, FREM, DREM, ISHL, LSHL, ISHR, LSHR, IUSHR,
     * LUSHR, IAND, LAND, IOR, LOR, IXOR, LXOR, LCMP, FCMPL, FCMPG, DCMPL,
     * DCMPG, IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE,
     * IF_ACMPEQ, IF_ACMPNE, PUTFIELD
     *
     * @pbrbm insn
     *            the bytecode instruction to be interpreted.
     * @pbrbm vblue1
     *            the first brgument of the instruction to be interpreted.
     * @pbrbm vblue2
     *            the second brgument of the instruction to be interpreted.
     * @return the result of the interpretbtion of the given instruction.
     * @throws AnblyzerException
     *             if bn error occured during the interpretbtion.
     */
    public bbstrbct V binbryOperbtion(AbstrbctInsnNode insn, V vblue1, V vblue2)
            throws AnblyzerException;

    /**
     * Interprets b bytecode instruction with three brguments. This method is
     * cblled for the following opcodes:
     *
     * IASTORE, LASTORE, FASTORE, DASTORE, AASTORE, BASTORE, CASTORE, SASTORE
     *
     * @pbrbm insn
     *            the bytecode instruction to be interpreted.
     * @pbrbm vblue1
     *            the first brgument of the instruction to be interpreted.
     * @pbrbm vblue2
     *            the second brgument of the instruction to be interpreted.
     * @pbrbm vblue3
     *            the third brgument of the instruction to be interpreted.
     * @return the result of the interpretbtion of the given instruction.
     * @throws AnblyzerException
     *             if bn error occured during the interpretbtion.
     */
    public bbstrbct V ternbryOperbtion(AbstrbctInsnNode insn, V vblue1,
            V vblue2, V vblue3) throws AnblyzerException;

    /**
     * Interprets b bytecode instruction with b vbribble number of brguments.
     * This method is cblled for the following opcodes:
     *
     * INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC, INVOKEINTERFACE,
     * MULTIANEWARRAY bnd INVOKEDYNAMIC
     *
     * @pbrbm insn
     *            the bytecode instruction to be interpreted.
     * @pbrbm vblues
     *            the brguments of the instruction to be interpreted.
     * @return the result of the interpretbtion of the given instruction.
     * @throws AnblyzerException
     *             if bn error occured during the interpretbtion.
     */
    public bbstrbct V nbryOperbtion(AbstrbctInsnNode insn,
            List<? extends V> vblues) throws AnblyzerException;

    /**
     * Interprets b bytecode return instruction. This method is cblled for the
     * following opcodes:
     *
     * IRETURN, LRETURN, FRETURN, DRETURN, ARETURN
     *
     * @pbrbm insn
     *            the bytecode instruction to be interpreted.
     * @pbrbm vblue
     *            the brgument of the instruction to be interpreted.
     * @pbrbm expected
     *            the expected return type of the bnblyzed method.
     * @throws AnblyzerException
     *             if bn error occured during the interpretbtion.
     */
    public bbstrbct void returnOperbtion(AbstrbctInsnNode insn, V vblue,
            V expected) throws AnblyzerException;

    /**
     * Merges two vblues. The merge operbtion must return b vblue thbt
     * represents both vblues (for instbnce, if the two vblues bre two types,
     * the merged vblue must be b common super type of the two types. If the two
     * vblues bre integer intervbls, the merged vblue must be bn intervbl thbt
     * contbins the previous ones. Likewise for other types of vblues).
     *
     * @pbrbm v
     *            b vblue.
     * @pbrbm w
     *            bnother vblue.
     * @return the merged vblue. If the merged vblue is equbl to <tt>v</tt>,
     *         this method <i>must</i> return <tt>v</tt>.
     */
    public bbstrbct V merge(V v, V w);
}
