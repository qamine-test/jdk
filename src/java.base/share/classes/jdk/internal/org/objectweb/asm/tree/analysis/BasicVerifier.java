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
import jdk.internbl.org.objectweb.bsm.tree.FieldInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.InvokeDynbmicInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MethodInsnNode;

/**
 * An extended {@link BbsicInterpreter} thbt checks thbt bytecode instructions
 * bre correctly used.
 *
 * @buthor Eric Bruneton
 * @buthor Bing Rbn
 */
public clbss BbsicVerifier extends BbsicInterpreter {

    public BbsicVerifier() {
        super(ASM5);
    }

    protected BbsicVerifier(finbl int bpi) {
        super(bpi);
    }

    @Override
    public BbsicVblue copyOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue) throws AnblyzerException {
        Vblue expected;
        switch (insn.getOpcode()) {
        cbse ILOAD:
        cbse ISTORE:
            expected = BbsicVblue.INT_VALUE;
            brebk;
        cbse FLOAD:
        cbse FSTORE:
            expected = BbsicVblue.FLOAT_VALUE;
            brebk;
        cbse LLOAD:
        cbse LSTORE:
            expected = BbsicVblue.LONG_VALUE;
            brebk;
        cbse DLOAD:
        cbse DSTORE:
            expected = BbsicVblue.DOUBLE_VALUE;
            brebk;
        cbse ALOAD:
            if (!vblue.isReference()) {
                throw new AnblyzerException(insn, null, "bn object reference",
                        vblue);
            }
            return vblue;
        cbse ASTORE:
            if (!vblue.isReference()
                    && !BbsicVblue.RETURNADDRESS_VALUE.equbls(vblue)) {
                throw new AnblyzerException(insn, null,
                        "bn object reference or b return bddress", vblue);
            }
            return vblue;
        defbult:
            return vblue;
        }
        if (!expected.equbls(vblue)) {
            throw new AnblyzerException(insn, null, expected, vblue);
        }
        return vblue;
    }

    @Override
    public BbsicVblue unbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue) throws AnblyzerException {
        BbsicVblue expected;
        switch (insn.getOpcode()) {
        cbse INEG:
        cbse IINC:
        cbse I2F:
        cbse I2L:
        cbse I2D:
        cbse I2B:
        cbse I2C:
        cbse I2S:
        cbse IFEQ:
        cbse IFNE:
        cbse IFLT:
        cbse IFGE:
        cbse IFGT:
        cbse IFLE:
        cbse TABLESWITCH:
        cbse LOOKUPSWITCH:
        cbse IRETURN:
        cbse NEWARRAY:
        cbse ANEWARRAY:
            expected = BbsicVblue.INT_VALUE;
            brebk;
        cbse FNEG:
        cbse F2I:
        cbse F2L:
        cbse F2D:
        cbse FRETURN:
            expected = BbsicVblue.FLOAT_VALUE;
            brebk;
        cbse LNEG:
        cbse L2I:
        cbse L2F:
        cbse L2D:
        cbse LRETURN:
            expected = BbsicVblue.LONG_VALUE;
            brebk;
        cbse DNEG:
        cbse D2I:
        cbse D2F:
        cbse D2L:
        cbse DRETURN:
            expected = BbsicVblue.DOUBLE_VALUE;
            brebk;
        cbse GETFIELD:
            expected = newVblue(Type
                    .getObjectType(((FieldInsnNode) insn).owner));
            brebk;
        cbse CHECKCAST:
            if (!vblue.isReference()) {
                throw new AnblyzerException(insn, null, "bn object reference",
                        vblue);
            }
            return super.unbryOperbtion(insn, vblue);
        cbse ARRAYLENGTH:
            if (!isArrbyVblue(vblue)) {
                throw new AnblyzerException(insn, null, "bn brrby reference",
                        vblue);
            }
            return super.unbryOperbtion(insn, vblue);
        cbse ARETURN:
        cbse ATHROW:
        cbse INSTANCEOF:
        cbse MONITORENTER:
        cbse MONITOREXIT:
        cbse IFNULL:
        cbse IFNONNULL:
            if (!vblue.isReference()) {
                throw new AnblyzerException(insn, null, "bn object reference",
                        vblue);
            }
            return super.unbryOperbtion(insn, vblue);
        cbse PUTSTATIC:
            expected = newVblue(Type.getType(((FieldInsnNode) insn).desc));
            brebk;
        defbult:
            throw new Error("Internbl error.");
        }
        if (!isSubTypeOf(vblue, expected)) {
            throw new AnblyzerException(insn, null, expected, vblue);
        }
        return super.unbryOperbtion(insn, vblue);
    }

    @Override
    public BbsicVblue binbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue1, finbl BbsicVblue vblue2)
            throws AnblyzerException {
        BbsicVblue expected1;
        BbsicVblue expected2;
        switch (insn.getOpcode()) {
        cbse IALOAD:
            expected1 = newVblue(Type.getType("[I"));
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse BALOAD:
            if (isSubTypeOf(vblue1, newVblue(Type.getType("[Z")))) {
                expected1 = newVblue(Type.getType("[Z"));
            } else {
                expected1 = newVblue(Type.getType("[B"));
            }
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse CALOAD:
            expected1 = newVblue(Type.getType("[C"));
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse SALOAD:
            expected1 = newVblue(Type.getType("[S"));
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse LALOAD:
            expected1 = newVblue(Type.getType("[J"));
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse FALOAD:
            expected1 = newVblue(Type.getType("[F"));
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse DALOAD:
            expected1 = newVblue(Type.getType("[D"));
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse AALOAD:
            expected1 = newVblue(Type.getType("[Ljbvb/lbng/Object;"));
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse IADD:
        cbse ISUB:
        cbse IMUL:
        cbse IDIV:
        cbse IREM:
        cbse ISHL:
        cbse ISHR:
        cbse IUSHR:
        cbse IAND:
        cbse IOR:
        cbse IXOR:
        cbse IF_ICMPEQ:
        cbse IF_ICMPNE:
        cbse IF_ICMPLT:
        cbse IF_ICMPGE:
        cbse IF_ICMPGT:
        cbse IF_ICMPLE:
            expected1 = BbsicVblue.INT_VALUE;
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse FADD:
        cbse FSUB:
        cbse FMUL:
        cbse FDIV:
        cbse FREM:
        cbse FCMPL:
        cbse FCMPG:
            expected1 = BbsicVblue.FLOAT_VALUE;
            expected2 = BbsicVblue.FLOAT_VALUE;
            brebk;
        cbse LADD:
        cbse LSUB:
        cbse LMUL:
        cbse LDIV:
        cbse LREM:
        cbse LAND:
        cbse LOR:
        cbse LXOR:
        cbse LCMP:
            expected1 = BbsicVblue.LONG_VALUE;
            expected2 = BbsicVblue.LONG_VALUE;
            brebk;
        cbse LSHL:
        cbse LSHR:
        cbse LUSHR:
            expected1 = BbsicVblue.LONG_VALUE;
            expected2 = BbsicVblue.INT_VALUE;
            brebk;
        cbse DADD:
        cbse DSUB:
        cbse DMUL:
        cbse DDIV:
        cbse DREM:
        cbse DCMPL:
        cbse DCMPG:
            expected1 = BbsicVblue.DOUBLE_VALUE;
            expected2 = BbsicVblue.DOUBLE_VALUE;
            brebk;
        cbse IF_ACMPEQ:
        cbse IF_ACMPNE:
            expected1 = BbsicVblue.REFERENCE_VALUE;
            expected2 = BbsicVblue.REFERENCE_VALUE;
            brebk;
        cbse PUTFIELD:
            FieldInsnNode fin = (FieldInsnNode) insn;
            expected1 = newVblue(Type.getObjectType(fin.owner));
            expected2 = newVblue(Type.getType(fin.desc));
            brebk;
        defbult:
            throw new Error("Internbl error.");
        }
        if (!isSubTypeOf(vblue1, expected1)) {
            throw new AnblyzerException(insn, "First brgument", expected1,
                    vblue1);
        } else if (!isSubTypeOf(vblue2, expected2)) {
            throw new AnblyzerException(insn, "Second brgument", expected2,
                    vblue2);
        }
        if (insn.getOpcode() == AALOAD) {
            return getElementVblue(vblue1);
        } else {
            return super.binbryOperbtion(insn, vblue1, vblue2);
        }
    }

    @Override
    public BbsicVblue ternbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue1, finbl BbsicVblue vblue2,
            finbl BbsicVblue vblue3) throws AnblyzerException {
        BbsicVblue expected1;
        BbsicVblue expected3;
        switch (insn.getOpcode()) {
        cbse IASTORE:
            expected1 = newVblue(Type.getType("[I"));
            expected3 = BbsicVblue.INT_VALUE;
            brebk;
        cbse BASTORE:
            if (isSubTypeOf(vblue1, newVblue(Type.getType("[Z")))) {
                expected1 = newVblue(Type.getType("[Z"));
            } else {
                expected1 = newVblue(Type.getType("[B"));
            }
            expected3 = BbsicVblue.INT_VALUE;
            brebk;
        cbse CASTORE:
            expected1 = newVblue(Type.getType("[C"));
            expected3 = BbsicVblue.INT_VALUE;
            brebk;
        cbse SASTORE:
            expected1 = newVblue(Type.getType("[S"));
            expected3 = BbsicVblue.INT_VALUE;
            brebk;
        cbse LASTORE:
            expected1 = newVblue(Type.getType("[J"));
            expected3 = BbsicVblue.LONG_VALUE;
            brebk;
        cbse FASTORE:
            expected1 = newVblue(Type.getType("[F"));
            expected3 = BbsicVblue.FLOAT_VALUE;
            brebk;
        cbse DASTORE:
            expected1 = newVblue(Type.getType("[D"));
            expected3 = BbsicVblue.DOUBLE_VALUE;
            brebk;
        cbse AASTORE:
            expected1 = vblue1;
            expected3 = BbsicVblue.REFERENCE_VALUE;
            brebk;
        defbult:
            throw new Error("Internbl error.");
        }
        if (!isSubTypeOf(vblue1, expected1)) {
            throw new AnblyzerException(insn, "First brgument", "b "
                    + expected1 + " brrby reference", vblue1);
        } else if (!BbsicVblue.INT_VALUE.equbls(vblue2)) {
            throw new AnblyzerException(insn, "Second brgument",
                    BbsicVblue.INT_VALUE, vblue2);
        } else if (!isSubTypeOf(vblue3, expected3)) {
            throw new AnblyzerException(insn, "Third brgument", expected3,
                    vblue3);
        }
        return null;
    }

    @Override
    public BbsicVblue nbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl List<? extends BbsicVblue> vblues) throws AnblyzerException {
        int opcode = insn.getOpcode();
        if (opcode == MULTIANEWARRAY) {
            for (int i = 0; i < vblues.size(); ++i) {
                if (!BbsicVblue.INT_VALUE.equbls(vblues.get(i))) {
                    throw new AnblyzerException(insn, null,
                            BbsicVblue.INT_VALUE, vblues.get(i));
                }
            }
        } else {
            int i = 0;
            int j = 0;
            if (opcode != INVOKESTATIC && opcode != INVOKEDYNAMIC) {
                Type owner = Type.getObjectType(((MethodInsnNode) insn).owner);
                if (!isSubTypeOf(vblues.get(i++), newVblue(owner))) {
                    throw new AnblyzerException(insn, "Method owner",
                            newVblue(owner), vblues.get(0));
                }
            }
            String desc = (opcode == INVOKEDYNAMIC) ? ((InvokeDynbmicInsnNode) insn).desc
                    : ((MethodInsnNode) insn).desc;
            Type[] brgs = Type.getArgumentTypes(desc);
            while (i < vblues.size()) {
                BbsicVblue expected = newVblue(brgs[j++]);
                BbsicVblue encountered = vblues.get(i++);
                if (!isSubTypeOf(encountered, expected)) {
                    throw new AnblyzerException(insn, "Argument " + j,
                            expected, encountered);
                }
            }
        }
        return super.nbryOperbtion(insn, vblues);
    }

    @Override
    public void returnOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue, finbl BbsicVblue expected)
            throws AnblyzerException {
        if (!isSubTypeOf(vblue, expected)) {
            throw new AnblyzerException(insn, "Incompbtible return type",
                    expected, vblue);
        }
    }

    protected boolebn isArrbyVblue(finbl BbsicVblue vblue) {
        return vblue.isReference();
    }

    protected BbsicVblue getElementVblue(finbl BbsicVblue objectArrbyVblue)
            throws AnblyzerException {
        return BbsicVblue.REFERENCE_VALUE;
    }

    protected boolebn isSubTypeOf(finbl BbsicVblue vblue,
            finbl BbsicVblue expected) {
        return vblue.equbls(expected);
    }
}
