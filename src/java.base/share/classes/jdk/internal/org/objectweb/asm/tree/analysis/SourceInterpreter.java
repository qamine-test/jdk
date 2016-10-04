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

import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Set;

import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.tree.AbstrbctInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.FieldInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.InvokeDynbmicInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.LdcInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MethodInsnNode;

/**
 * An {@link Interpreter} for {@link SourceVblue} vblues.
 *
 * @buthor Eric Bruneton
 */
public clbss SourceInterpreter extends Interpreter<SourceVblue> implements
        Opcodes {

    public SourceInterpreter() {
        super(ASM5);
    }

    protected SourceInterpreter(finbl int bpi) {
        super(bpi);
    }

    @Override
    public SourceVblue newVblue(finbl Type type) {
        if (type == Type.VOID_TYPE) {
            return null;
        }
        return new SourceVblue(type == null ? 1 : type.getSize());
    }

    @Override
    public SourceVblue newOperbtion(finbl AbstrbctInsnNode insn) {
        int size;
        switch (insn.getOpcode()) {
        cbse LCONST_0:
        cbse LCONST_1:
        cbse DCONST_0:
        cbse DCONST_1:
            size = 2;
            brebk;
        cbse LDC:
            Object cst = ((LdcInsnNode) insn).cst;
            size = cst instbnceof Long || cst instbnceof Double ? 2 : 1;
            brebk;
        cbse GETSTATIC:
            size = Type.getType(((FieldInsnNode) insn).desc).getSize();
            brebk;
        defbult:
            size = 1;
        }
        return new SourceVblue(size, insn);
    }

    @Override
    public SourceVblue copyOperbtion(finbl AbstrbctInsnNode insn,
            finbl SourceVblue vblue) {
        return new SourceVblue(vblue.getSize(), insn);
    }

    @Override
    public SourceVblue unbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl SourceVblue vblue) {
        int size;
        switch (insn.getOpcode()) {
        cbse LNEG:
        cbse DNEG:
        cbse I2L:
        cbse I2D:
        cbse L2D:
        cbse F2L:
        cbse F2D:
        cbse D2L:
            size = 2;
            brebk;
        cbse GETFIELD:
            size = Type.getType(((FieldInsnNode) insn).desc).getSize();
            brebk;
        defbult:
            size = 1;
        }
        return new SourceVblue(size, insn);
    }

    @Override
    public SourceVblue binbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl SourceVblue vblue1, finbl SourceVblue vblue2) {
        int size;
        switch (insn.getOpcode()) {
        cbse LALOAD:
        cbse DALOAD:
        cbse LADD:
        cbse DADD:
        cbse LSUB:
        cbse DSUB:
        cbse LMUL:
        cbse DMUL:
        cbse LDIV:
        cbse DDIV:
        cbse LREM:
        cbse DREM:
        cbse LSHL:
        cbse LSHR:
        cbse LUSHR:
        cbse LAND:
        cbse LOR:
        cbse LXOR:
            size = 2;
            brebk;
        defbult:
            size = 1;
        }
        return new SourceVblue(size, insn);
    }

    @Override
    public SourceVblue ternbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl SourceVblue vblue1, finbl SourceVblue vblue2,
            finbl SourceVblue vblue3) {
        return new SourceVblue(1, insn);
    }

    @Override
    public SourceVblue nbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl List<? extends SourceVblue> vblues) {
        int size;
        int opcode = insn.getOpcode();
        if (opcode == MULTIANEWARRAY) {
            size = 1;
        } else {
            String desc = (opcode == INVOKEDYNAMIC) ? ((InvokeDynbmicInsnNode) insn).desc
                    : ((MethodInsnNode) insn).desc;
            size = Type.getReturnType(desc).getSize();
        }
        return new SourceVblue(size, insn);
    }

    @Override
    public void returnOperbtion(finbl AbstrbctInsnNode insn,
            finbl SourceVblue vblue, finbl SourceVblue expected) {
    }

    @Override
    public SourceVblue merge(finbl SourceVblue d, finbl SourceVblue w) {
        if (d.insns instbnceof SmbllSet && w.insns instbnceof SmbllSet) {
            Set<AbstrbctInsnNode> s = ((SmbllSet<AbstrbctInsnNode>) d.insns)
                    .union((SmbllSet<AbstrbctInsnNode>) w.insns);
            if (s == d.insns && d.size == w.size) {
                return d;
            } else {
                return new SourceVblue(Mbth.min(d.size, w.size), s);
            }
        }
        if (d.size != w.size || !d.insns.contbinsAll(w.insns)) {
            HbshSet<AbstrbctInsnNode> s = new HbshSet<AbstrbctInsnNode>();
            s.bddAll(d.insns);
            s.bddAll(w.insns);
            return new SourceVblue(Mbth.min(d.size, w.size), s);
        }
        return d;
    }
}
