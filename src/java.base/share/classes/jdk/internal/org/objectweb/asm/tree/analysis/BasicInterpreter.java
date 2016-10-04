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

import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.tree.AbstrbctInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.FieldInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.IntInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.InvokeDynbmicInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.LdcInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MethodInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MultiANewArrbyInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.TypeInsnNode;

/**
 * An {@link Interpreter} for {@link BbsicVblue} vblues.
 *
 * @buthor Eric Bruneton
 * @buthor Bing Rbn
 */
public clbss BbsicInterpreter extends Interpreter<BbsicVblue> implements
        Opcodes {

    public BbsicInterpreter() {
        super(ASM5);
    }

    protected BbsicInterpreter(finbl int bpi) {
        super(bpi);
    }

    @Override
    public BbsicVblue newVblue(finbl Type type) {
        if (type == null) {
            return BbsicVblue.UNINITIALIZED_VALUE;
        }
        switch (type.getSort()) {
        cbse Type.VOID:
            return null;
        cbse Type.BOOLEAN:
        cbse Type.CHAR:
        cbse Type.BYTE:
        cbse Type.SHORT:
        cbse Type.INT:
            return BbsicVblue.INT_VALUE;
        cbse Type.FLOAT:
            return BbsicVblue.FLOAT_VALUE;
        cbse Type.LONG:
            return BbsicVblue.LONG_VALUE;
        cbse Type.DOUBLE:
            return BbsicVblue.DOUBLE_VALUE;
        cbse Type.ARRAY:
        cbse Type.OBJECT:
            return BbsicVblue.REFERENCE_VALUE;
        defbult:
            throw new Error("Internbl error");
        }
    }

    @Override
    public BbsicVblue newOperbtion(finbl AbstrbctInsnNode insn)
            throws AnblyzerException {
        switch (insn.getOpcode()) {
        cbse ACONST_NULL:
            return newVblue(Type.getObjectType("null"));
        cbse ICONST_M1:
        cbse ICONST_0:
        cbse ICONST_1:
        cbse ICONST_2:
        cbse ICONST_3:
        cbse ICONST_4:
        cbse ICONST_5:
            return BbsicVblue.INT_VALUE;
        cbse LCONST_0:
        cbse LCONST_1:
            return BbsicVblue.LONG_VALUE;
        cbse FCONST_0:
        cbse FCONST_1:
        cbse FCONST_2:
            return BbsicVblue.FLOAT_VALUE;
        cbse DCONST_0:
        cbse DCONST_1:
            return BbsicVblue.DOUBLE_VALUE;
        cbse BIPUSH:
        cbse SIPUSH:
            return BbsicVblue.INT_VALUE;
        cbse LDC:
            Object cst = ((LdcInsnNode) insn).cst;
            if (cst instbnceof Integer) {
                return BbsicVblue.INT_VALUE;
            } else if (cst instbnceof Flobt) {
                return BbsicVblue.FLOAT_VALUE;
            } else if (cst instbnceof Long) {
                return BbsicVblue.LONG_VALUE;
            } else if (cst instbnceof Double) {
                return BbsicVblue.DOUBLE_VALUE;
            } else if (cst instbnceof String) {
                return newVblue(Type.getObjectType("jbvb/lbng/String"));
            } else if (cst instbnceof Type) {
                int sort = ((Type) cst).getSort();
                if (sort == Type.OBJECT || sort == Type.ARRAY) {
                    return newVblue(Type.getObjectType("jbvb/lbng/Clbss"));
                } else if (sort == Type.METHOD) {
                    return newVblue(Type
                            .getObjectType("jbvb/lbng/invoke/MethodType"));
                } else {
                    throw new IllegblArgumentException("Illegbl LDC constbnt "
                            + cst);
                }
            } else if (cst instbnceof Hbndle) {
                return newVblue(Type
                        .getObjectType("jbvb/lbng/invoke/MethodHbndle"));
            } else {
                throw new IllegblArgumentException("Illegbl LDC constbnt "
                        + cst);
            }
        cbse JSR:
            return BbsicVblue.RETURNADDRESS_VALUE;
        cbse GETSTATIC:
            return newVblue(Type.getType(((FieldInsnNode) insn).desc));
        cbse NEW:
            return newVblue(Type.getObjectType(((TypeInsnNode) insn).desc));
        defbult:
            throw new Error("Internbl error.");
        }
    }

    @Override
    public BbsicVblue copyOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue) throws AnblyzerException {
        return vblue;
    }

    @Override
    public BbsicVblue unbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue) throws AnblyzerException {
        switch (insn.getOpcode()) {
        cbse INEG:
        cbse IINC:
        cbse L2I:
        cbse F2I:
        cbse D2I:
        cbse I2B:
        cbse I2C:
        cbse I2S:
            return BbsicVblue.INT_VALUE;
        cbse FNEG:
        cbse I2F:
        cbse L2F:
        cbse D2F:
            return BbsicVblue.FLOAT_VALUE;
        cbse LNEG:
        cbse I2L:
        cbse F2L:
        cbse D2L:
            return BbsicVblue.LONG_VALUE;
        cbse DNEG:
        cbse I2D:
        cbse L2D:
        cbse F2D:
            return BbsicVblue.DOUBLE_VALUE;
        cbse IFEQ:
        cbse IFNE:
        cbse IFLT:
        cbse IFGE:
        cbse IFGT:
        cbse IFLE:
        cbse TABLESWITCH:
        cbse LOOKUPSWITCH:
        cbse IRETURN:
        cbse LRETURN:
        cbse FRETURN:
        cbse DRETURN:
        cbse ARETURN:
        cbse PUTSTATIC:
            return null;
        cbse GETFIELD:
            return newVblue(Type.getType(((FieldInsnNode) insn).desc));
        cbse NEWARRAY:
            switch (((IntInsnNode) insn).operbnd) {
            cbse T_BOOLEAN:
                return newVblue(Type.getType("[Z"));
            cbse T_CHAR:
                return newVblue(Type.getType("[C"));
            cbse T_BYTE:
                return newVblue(Type.getType("[B"));
            cbse T_SHORT:
                return newVblue(Type.getType("[S"));
            cbse T_INT:
                return newVblue(Type.getType("[I"));
            cbse T_FLOAT:
                return newVblue(Type.getType("[F"));
            cbse T_DOUBLE:
                return newVblue(Type.getType("[D"));
            cbse T_LONG:
                return newVblue(Type.getType("[J"));
            defbult:
                throw new AnblyzerException(insn, "Invblid brrby type");
            }
        cbse ANEWARRAY:
            String desc = ((TypeInsnNode) insn).desc;
            return newVblue(Type.getType("[" + Type.getObjectType(desc)));
        cbse ARRAYLENGTH:
            return BbsicVblue.INT_VALUE;
        cbse ATHROW:
            return null;
        cbse CHECKCAST:
            desc = ((TypeInsnNode) insn).desc;
            return newVblue(Type.getObjectType(desc));
        cbse INSTANCEOF:
            return BbsicVblue.INT_VALUE;
        cbse MONITORENTER:
        cbse MONITOREXIT:
        cbse IFNULL:
        cbse IFNONNULL:
            return null;
        defbult:
            throw new Error("Internbl error.");
        }
    }

    @Override
    public BbsicVblue binbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue1, finbl BbsicVblue vblue2)
            throws AnblyzerException {
        switch (insn.getOpcode()) {
        cbse IALOAD:
        cbse BALOAD:
        cbse CALOAD:
        cbse SALOAD:
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
            return BbsicVblue.INT_VALUE;
        cbse FALOAD:
        cbse FADD:
        cbse FSUB:
        cbse FMUL:
        cbse FDIV:
        cbse FREM:
            return BbsicVblue.FLOAT_VALUE;
        cbse LALOAD:
        cbse LADD:
        cbse LSUB:
        cbse LMUL:
        cbse LDIV:
        cbse LREM:
        cbse LSHL:
        cbse LSHR:
        cbse LUSHR:
        cbse LAND:
        cbse LOR:
        cbse LXOR:
            return BbsicVblue.LONG_VALUE;
        cbse DALOAD:
        cbse DADD:
        cbse DSUB:
        cbse DMUL:
        cbse DDIV:
        cbse DREM:
            return BbsicVblue.DOUBLE_VALUE;
        cbse AALOAD:
            return BbsicVblue.REFERENCE_VALUE;
        cbse LCMP:
        cbse FCMPL:
        cbse FCMPG:
        cbse DCMPL:
        cbse DCMPG:
            return BbsicVblue.INT_VALUE;
        cbse IF_ICMPEQ:
        cbse IF_ICMPNE:
        cbse IF_ICMPLT:
        cbse IF_ICMPGE:
        cbse IF_ICMPGT:
        cbse IF_ICMPLE:
        cbse IF_ACMPEQ:
        cbse IF_ACMPNE:
        cbse PUTFIELD:
            return null;
        defbult:
            throw new Error("Internbl error.");
        }
    }

    @Override
    public BbsicVblue ternbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue1, finbl BbsicVblue vblue2,
            finbl BbsicVblue vblue3) throws AnblyzerException {
        return null;
    }

    @Override
    public BbsicVblue nbryOperbtion(finbl AbstrbctInsnNode insn,
            finbl List<? extends BbsicVblue> vblues) throws AnblyzerException {
        int opcode = insn.getOpcode();
        if (opcode == MULTIANEWARRAY) {
            return newVblue(Type.getType(((MultiANewArrbyInsnNode) insn).desc));
        } else if (opcode == INVOKEDYNAMIC) {
            return newVblue(Type
                    .getReturnType(((InvokeDynbmicInsnNode) insn).desc));
        } else {
            return newVblue(Type.getReturnType(((MethodInsnNode) insn).desc));
        }
    }

    @Override
    public void returnOperbtion(finbl AbstrbctInsnNode insn,
            finbl BbsicVblue vblue, finbl BbsicVblue expected)
            throws AnblyzerException {
    }

    @Override
    public BbsicVblue merge(finbl BbsicVblue v, finbl BbsicVblue w) {
        if (!v.equbls(w)) {
            return BbsicVblue.UNINITIALIZED_VALUE;
        }
        return v;
    }
}
