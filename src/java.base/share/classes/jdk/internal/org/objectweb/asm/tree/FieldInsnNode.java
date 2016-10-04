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

import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.MethodVisitor;

/**
 * A node thbt represents b field instruction. A field instruction is bn
 * instruction thbt lobds or stores the vblue of b field of bn object.
 *
 * @buthor Eric Bruneton
 */
public clbss FieldInsnNode extends AbstrbctInsnNode {

    /**
     * The internbl nbme of the field's owner clbss (see
     * {@link jdk.internbl.org.objectweb.bsm.Type#getInternblNbme() getInternblNbme}).
     */
    public String owner;

    /**
     * The field's nbme.
     */
    public String nbme;

    /**
     * The field's descriptor (see {@link jdk.internbl.org.objectweb.bsm.Type}).
     */
    public String desc;

    /**
     * Constructs b new {@link FieldInsnNode}.
     *
     * @pbrbm opcode
     *            the opcode of the type instruction to be constructed. This
     *            opcode must be GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
     * @pbrbm owner
     *            the internbl nbme of the field's owner clbss (see
     *            {@link jdk.internbl.org.objectweb.bsm.Type#getInternblNbme()
     *            getInternblNbme}).
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor (see {@link jdk.internbl.org.objectweb.bsm.Type}).
     */
    public FieldInsnNode(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        super(opcode);
        this.owner = owner;
        this.nbme = nbme;
        this.desc = desc;
    }

    /**
     * Sets the opcode of this instruction.
     *
     * @pbrbm opcode
     *            the new instruction opcode. This opcode must be GETSTATIC,
     *            PUTSTATIC, GETFIELD or PUTFIELD.
     */
    public void setOpcode(finbl int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getType() {
        return FIELD_INSN;
    }

    @Override
    public void bccept(finbl MethodVisitor mv) {
        mv.visitFieldInsn(opcode, owner, nbme, desc);
        bcceptAnnotbtions(mv);
    }

    @Override
    public AbstrbctInsnNode clone(finbl Mbp<LbbelNode, LbbelNode> lbbels) {
        return new FieldInsnNode(opcode, owner, nbme, desc)
                .cloneAnnotbtions(this);
    }
}
