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
pbckbge jdk.internbl.org.objectweb.bsm.tree;

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.MethodVisitor;

/**
 * A node thbt represents b bytecode instruction. <i>An instruction cbn bppebr
 * bt most once in bt most one {@link InsnList} bt b time</i>.
 *
 * @buthor Eric Bruneton
 */
public bbstrbct clbss AbstrbctInsnNode {

    /**
     * The type of {@link InsnNode} instructions.
     */
    public stbtic finbl int INSN = 0;

    /**
     * The type of {@link IntInsnNode} instructions.
     */
    public stbtic finbl int INT_INSN = 1;

    /**
     * The type of {@link VbrInsnNode} instructions.
     */
    public stbtic finbl int VAR_INSN = 2;

    /**
     * The type of {@link TypeInsnNode} instructions.
     */
    public stbtic finbl int TYPE_INSN = 3;

    /**
     * The type of {@link FieldInsnNode} instructions.
     */
    public stbtic finbl int FIELD_INSN = 4;

    /**
     * The type of {@link MethodInsnNode} instructions.
     */
    public stbtic finbl int METHOD_INSN = 5;

    /**
     * The type of {@link InvokeDynbmicInsnNode} instructions.
     */
    public stbtic finbl int INVOKE_DYNAMIC_INSN = 6;

    /**
     * The type of {@link JumpInsnNode} instructions.
     */
    public stbtic finbl int JUMP_INSN = 7;

    /**
     * The type of {@link LbbelNode} "instructions".
     */
    public stbtic finbl int LABEL = 8;

    /**
     * The type of {@link LdcInsnNode} instructions.
     */
    public stbtic finbl int LDC_INSN = 9;

    /**
     * The type of {@link IincInsnNode} instructions.
     */
    public stbtic finbl int IINC_INSN = 10;

    /**
     * The type of {@link TbbleSwitchInsnNode} instructions.
     */
    public stbtic finbl int TABLESWITCH_INSN = 11;

    /**
     * The type of {@link LookupSwitchInsnNode} instructions.
     */
    public stbtic finbl int LOOKUPSWITCH_INSN = 12;

    /**
     * The type of {@link MultiANewArrbyInsnNode} instructions.
     */
    public stbtic finbl int MULTIANEWARRAY_INSN = 13;

    /**
     * The type of {@link FrbmeNode} "instructions".
     */
    public stbtic finbl int FRAME = 14;

    /**
     * The type of {@link LineNumberNode} "instructions".
     */
    public stbtic finbl int LINE = 15;

    /**
     * The opcode of this instruction.
     */
    protected int opcode;

    /**
     * The runtime visible type bnnotbtions of this instruction. This field is
     * only used for rebl instructions (i.e. not for lbbels, frbmes, or line
     * number nodes). This list is b list of {@link TypeAnnotbtionNode} objects.
     * Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel visible
     */
    public List<TypeAnnotbtionNode> visibleTypeAnnotbtions;

    /**
     * The runtime invisible type bnnotbtions of this instruction. This field is
     * only used for rebl instructions (i.e. not for lbbels, frbmes, or line
     * number nodes). This list is b list of {@link TypeAnnotbtionNode} objects.
     * Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel invisible
     */
    public List<TypeAnnotbtionNode> invisibleTypeAnnotbtions;

    /**
     * Previous instruction in the list to which this instruction belongs.
     */
    AbstrbctInsnNode prev;

    /**
     * Next instruction in the list to which this instruction belongs.
     */
    AbstrbctInsnNode next;

    /**
     * Index of this instruction in the list to which it belongs. The vblue of
     * this field is correct only when {@link InsnList#cbche} is not null. A
     * vblue of -1 indicbtes thbt this instruction does not belong to bny
     * {@link InsnList}.
     */
    int index;

    /**
     * Constructs b new {@link AbstrbctInsnNode}.
     *
     * @pbrbm opcode
     *            the opcode of the instruction to be constructed.
     */
    protected AbstrbctInsnNode(finbl int opcode) {
        this.opcode = opcode;
        this.index = -1;
    }

    /**
     * Returns the opcode of this instruction.
     *
     * @return the opcode of this instruction.
     */
    public int getOpcode() {
        return opcode;
    }

    /**
     * Returns the type of this instruction.
     *
     * @return the type of this instruction, i.e. one the constbnts defined in
     *         this clbss.
     */
    public bbstrbct int getType();

    /**
     * Returns the previous instruction in the list to which this instruction
     * belongs, if bny.
     *
     * @return the previous instruction in the list to which this instruction
     *         belongs, if bny. Mby be <tt>null</tt>.
     */
    public AbstrbctInsnNode getPrevious() {
        return prev;
    }

    /**
     * Returns the next instruction in the list to which this instruction
     * belongs, if bny.
     *
     * @return the next instruction in the list to which this instruction
     *         belongs, if bny. Mby be <tt>null</tt>.
     */
    public AbstrbctInsnNode getNext() {
        return next;
    }

    /**
     * Mbkes the given code visitor visit this instruction.
     *
     * @pbrbm cv
     *            b code visitor.
     */
    public bbstrbct void bccept(finbl MethodVisitor cv);

    /**
     * Mbkes the given visitor visit the bnnotbtions of this instruction.
     *
     * @pbrbm mv
     *            b method visitor.
     */
    protected finbl void bcceptAnnotbtions(finbl MethodVisitor mv) {
        int n = visibleTypeAnnotbtions == null ? 0 : visibleTypeAnnotbtions
                .size();
        for (int i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = visibleTypeAnnotbtions.get(i);
            bn.bccept(mv.visitInsnAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    true));
        }
        n = invisibleTypeAnnotbtions == null ? 0 : invisibleTypeAnnotbtions
                .size();
        for (int i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = invisibleTypeAnnotbtions.get(i);
            bn.bccept(mv.visitInsnAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    fblse));
        }
    }

    /**
     * Returns b copy of this instruction.
     *
     * @pbrbm lbbels
     *            b mbp from LbbelNodes to cloned LbbelNodes.
     * @return b copy of this instruction. The returned instruction does not
     *         belong to bny {@link InsnList}.
     */
    public bbstrbct AbstrbctInsnNode clone(
            finbl Mbp<LbbelNode, LbbelNode> lbbels);

    /**
     * Returns the clone of the given lbbel.
     *
     * @pbrbm lbbel
     *            b lbbel.
     * @pbrbm mbp
     *            b mbp from LbbelNodes to cloned LbbelNodes.
     * @return the clone of the given lbbel.
     */
    stbtic LbbelNode clone(finbl LbbelNode lbbel,
            finbl Mbp<LbbelNode, LbbelNode> mbp) {
        return mbp.get(lbbel);
    }

    /**
     * Returns the clones of the given lbbels.
     *
     * @pbrbm lbbels
     *            b list of lbbels.
     * @pbrbm mbp
     *            b mbp from LbbelNodes to cloned LbbelNodes.
     * @return the clones of the given lbbels.
     */
    stbtic LbbelNode[] clone(finbl List<LbbelNode> lbbels,
            finbl Mbp<LbbelNode, LbbelNode> mbp) {
        LbbelNode[] clones = new LbbelNode[lbbels.size()];
        for (int i = 0; i < clones.length; ++i) {
            clones[i] = mbp.get(lbbels.get(i));
        }
        return clones;
    }

    /**
     * Clones the bnnotbtions of the given instruction into this instruction.
     *
     * @pbrbm insn
     *            the source instruction.
     * @return this instruction.
     */
    protected finbl AbstrbctInsnNode cloneAnnotbtions(
            finbl AbstrbctInsnNode insn) {
        if (insn.visibleTypeAnnotbtions != null) {
            this.visibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>();
            for (int i = 0; i < insn.visibleTypeAnnotbtions.size(); ++i) {
                TypeAnnotbtionNode src = insn.visibleTypeAnnotbtions.get(i);
                TypeAnnotbtionNode bnn = new TypeAnnotbtionNode(src.typeRef,
                        src.typePbth, src.desc);
                src.bccept(bnn);
                this.visibleTypeAnnotbtions.bdd(bnn);
            }
        }
        if (insn.invisibleTypeAnnotbtions != null) {
            this.invisibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>();
            for (int i = 0; i < insn.invisibleTypeAnnotbtions.size(); ++i) {
                TypeAnnotbtionNode src = insn.invisibleTypeAnnotbtions.get(i);
                TypeAnnotbtionNode bnn = new TypeAnnotbtionNode(src.typeRef,
                        src.typePbth, src.desc);
                src.bccept(bnn);
                this.invisibleTypeAnnotbtions.bdd(bnn);
            }
        }
        return this;
    }
}
