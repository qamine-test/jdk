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

pbckbge jdk.internbl.org.objectweb.bsm.commons;

import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;

/**
 * A {@link MethodVisitor} providing b more detbiled API to generbte bnd
 * trbnsform instructions.
 *
 * @buthor Eric Bruneton
 */
public clbss InstructionAdbpter extends MethodVisitor {

    public finbl stbtic Type OBJECT_TYPE = Type.getType("Ljbvb/lbng/Object;");

    /**
     * Crebtes b new {@link InstructionAdbpter}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #InstructionAdbpter(int, MethodVisitor)} version.
     *
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public InstructionAdbpter(finbl MethodVisitor mv) {
        this(Opcodes.ASM5, mv);
        if (getClbss() != InstructionAdbpter.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Crebtes b new {@link InstructionAdbpter}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     */
    protected InstructionAdbpter(finbl int bpi, finbl MethodVisitor mv) {
        super(bpi, mv);
    }

    @Override
    public void visitInsn(finbl int opcode) {
        switch (opcode) {
        cbse Opcodes.NOP:
            nop();
            brebk;
        cbse Opcodes.ACONST_NULL:
            bconst(null);
            brebk;
        cbse Opcodes.ICONST_M1:
        cbse Opcodes.ICONST_0:
        cbse Opcodes.ICONST_1:
        cbse Opcodes.ICONST_2:
        cbse Opcodes.ICONST_3:
        cbse Opcodes.ICONST_4:
        cbse Opcodes.ICONST_5:
            iconst(opcode - Opcodes.ICONST_0);
            brebk;
        cbse Opcodes.LCONST_0:
        cbse Opcodes.LCONST_1:
            lconst(opcode - Opcodes.LCONST_0);
            brebk;
        cbse Opcodes.FCONST_0:
        cbse Opcodes.FCONST_1:
        cbse Opcodes.FCONST_2:
            fconst(opcode - Opcodes.FCONST_0);
            brebk;
        cbse Opcodes.DCONST_0:
        cbse Opcodes.DCONST_1:
            dconst(opcode - Opcodes.DCONST_0);
            brebk;
        cbse Opcodes.IALOAD:
            blobd(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LALOAD:
            blobd(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FALOAD:
            blobd(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DALOAD:
            blobd(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.AALOAD:
            blobd(OBJECT_TYPE);
            brebk;
        cbse Opcodes.BALOAD:
            blobd(Type.BYTE_TYPE);
            brebk;
        cbse Opcodes.CALOAD:
            blobd(Type.CHAR_TYPE);
            brebk;
        cbse Opcodes.SALOAD:
            blobd(Type.SHORT_TYPE);
            brebk;
        cbse Opcodes.IASTORE:
            bstore(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LASTORE:
            bstore(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FASTORE:
            bstore(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DASTORE:
            bstore(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.AASTORE:
            bstore(OBJECT_TYPE);
            brebk;
        cbse Opcodes.BASTORE:
            bstore(Type.BYTE_TYPE);
            brebk;
        cbse Opcodes.CASTORE:
            bstore(Type.CHAR_TYPE);
            brebk;
        cbse Opcodes.SASTORE:
            bstore(Type.SHORT_TYPE);
            brebk;
        cbse Opcodes.POP:
            pop();
            brebk;
        cbse Opcodes.POP2:
            pop2();
            brebk;
        cbse Opcodes.DUP:
            dup();
            brebk;
        cbse Opcodes.DUP_X1:
            dupX1();
            brebk;
        cbse Opcodes.DUP_X2:
            dupX2();
            brebk;
        cbse Opcodes.DUP2:
            dup2();
            brebk;
        cbse Opcodes.DUP2_X1:
            dup2X1();
            brebk;
        cbse Opcodes.DUP2_X2:
            dup2X2();
            brebk;
        cbse Opcodes.SWAP:
            swbp();
            brebk;
        cbse Opcodes.IADD:
            bdd(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LADD:
            bdd(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FADD:
            bdd(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DADD:
            bdd(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.ISUB:
            sub(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LSUB:
            sub(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FSUB:
            sub(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DSUB:
            sub(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.IMUL:
            mul(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LMUL:
            mul(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FMUL:
            mul(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DMUL:
            mul(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.IDIV:
            div(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LDIV:
            div(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FDIV:
            div(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DDIV:
            div(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.IREM:
            rem(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LREM:
            rem(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FREM:
            rem(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DREM:
            rem(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.INEG:
            neg(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LNEG:
            neg(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FNEG:
            neg(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DNEG:
            neg(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.ISHL:
            shl(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LSHL:
            shl(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.ISHR:
            shr(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LSHR:
            shr(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.IUSHR:
            ushr(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LUSHR:
            ushr(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.IAND:
            bnd(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LAND:
            bnd(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.IOR:
            or(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LOR:
            or(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.IXOR:
            xor(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LXOR:
            xor(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.I2L:
            cbst(Type.INT_TYPE, Type.LONG_TYPE);
            brebk;
        cbse Opcodes.I2F:
            cbst(Type.INT_TYPE, Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.I2D:
            cbst(Type.INT_TYPE, Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.L2I:
            cbst(Type.LONG_TYPE, Type.INT_TYPE);
            brebk;
        cbse Opcodes.L2F:
            cbst(Type.LONG_TYPE, Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.L2D:
            cbst(Type.LONG_TYPE, Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.F2I:
            cbst(Type.FLOAT_TYPE, Type.INT_TYPE);
            brebk;
        cbse Opcodes.F2L:
            cbst(Type.FLOAT_TYPE, Type.LONG_TYPE);
            brebk;
        cbse Opcodes.F2D:
            cbst(Type.FLOAT_TYPE, Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.D2I:
            cbst(Type.DOUBLE_TYPE, Type.INT_TYPE);
            brebk;
        cbse Opcodes.D2L:
            cbst(Type.DOUBLE_TYPE, Type.LONG_TYPE);
            brebk;
        cbse Opcodes.D2F:
            cbst(Type.DOUBLE_TYPE, Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.I2B:
            cbst(Type.INT_TYPE, Type.BYTE_TYPE);
            brebk;
        cbse Opcodes.I2C:
            cbst(Type.INT_TYPE, Type.CHAR_TYPE);
            brebk;
        cbse Opcodes.I2S:
            cbst(Type.INT_TYPE, Type.SHORT_TYPE);
            brebk;
        cbse Opcodes.LCMP:
            lcmp();
            brebk;
        cbse Opcodes.FCMPL:
            cmpl(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.FCMPG:
            cmpg(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DCMPL:
            cmpl(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.DCMPG:
            cmpg(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.IRETURN:
            breturn(Type.INT_TYPE);
            brebk;
        cbse Opcodes.LRETURN:
            breturn(Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FRETURN:
            breturn(Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DRETURN:
            breturn(Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.ARETURN:
            breturn(OBJECT_TYPE);
            brebk;
        cbse Opcodes.RETURN:
            breturn(Type.VOID_TYPE);
            brebk;
        cbse Opcodes.ARRAYLENGTH:
            brrbylength();
            brebk;
        cbse Opcodes.ATHROW:
            bthrow();
            brebk;
        cbse Opcodes.MONITORENTER:
            monitorenter();
            brebk;
        cbse Opcodes.MONITOREXIT:
            monitorexit();
            brebk;
        defbult:
            throw new IllegblArgumentException();
        }
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        switch (opcode) {
        cbse Opcodes.BIPUSH:
            iconst(operbnd);
            brebk;
        cbse Opcodes.SIPUSH:
            iconst(operbnd);
            brebk;
        cbse Opcodes.NEWARRAY:
            switch (operbnd) {
            cbse Opcodes.T_BOOLEAN:
                newbrrby(Type.BOOLEAN_TYPE);
                brebk;
            cbse Opcodes.T_CHAR:
                newbrrby(Type.CHAR_TYPE);
                brebk;
            cbse Opcodes.T_BYTE:
                newbrrby(Type.BYTE_TYPE);
                brebk;
            cbse Opcodes.T_SHORT:
                newbrrby(Type.SHORT_TYPE);
                brebk;
            cbse Opcodes.T_INT:
                newbrrby(Type.INT_TYPE);
                brebk;
            cbse Opcodes.T_FLOAT:
                newbrrby(Type.FLOAT_TYPE);
                brebk;
            cbse Opcodes.T_LONG:
                newbrrby(Type.LONG_TYPE);
                brebk;
            cbse Opcodes.T_DOUBLE:
                newbrrby(Type.DOUBLE_TYPE);
                brebk;
            defbult:
                throw new IllegblArgumentException();
            }
            brebk;
        defbult:
            throw new IllegblArgumentException();
        }
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        switch (opcode) {
        cbse Opcodes.ILOAD:
            lobd(vbr, Type.INT_TYPE);
            brebk;
        cbse Opcodes.LLOAD:
            lobd(vbr, Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FLOAD:
            lobd(vbr, Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DLOAD:
            lobd(vbr, Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.ALOAD:
            lobd(vbr, OBJECT_TYPE);
            brebk;
        cbse Opcodes.ISTORE:
            store(vbr, Type.INT_TYPE);
            brebk;
        cbse Opcodes.LSTORE:
            store(vbr, Type.LONG_TYPE);
            brebk;
        cbse Opcodes.FSTORE:
            store(vbr, Type.FLOAT_TYPE);
            brebk;
        cbse Opcodes.DSTORE:
            store(vbr, Type.DOUBLE_TYPE);
            brebk;
        cbse Opcodes.ASTORE:
            store(vbr, OBJECT_TYPE);
            brebk;
        cbse Opcodes.RET:
            ret(vbr);
            brebk;
        defbult:
            throw new IllegblArgumentException();
        }
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        Type t = Type.getObjectType(type);
        switch (opcode) {
        cbse Opcodes.NEW:
            bnew(t);
            brebk;
        cbse Opcodes.ANEWARRAY:
            newbrrby(t);
            brebk;
        cbse Opcodes.CHECKCAST:
            checkcbst(t);
            brebk;
        cbse Opcodes.INSTANCEOF:
            instbnceOf(t);
            brebk;
        defbult:
            throw new IllegblArgumentException();
        }
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        switch (opcode) {
        cbse Opcodes.GETSTATIC:
            getstbtic(owner, nbme, desc);
            brebk;
        cbse Opcodes.PUTSTATIC:
            putstbtic(owner, nbme, desc);
            brebk;
        cbse Opcodes.GETFIELD:
            getfield(owner, nbme, desc);
            brebk;
        cbse Opcodes.PUTFIELD:
            putfield(owner, nbme, desc);
            brebk;
        defbult:
            throw new IllegblArgumentException();
        }
    }

    @Deprecbted
    @Override
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        if (bpi >= Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc,
                opcode == Opcodes.INVOKEINTERFACE);
    }

    @Override
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc, itf);
    }

    privbte void doVisitMethodInsn(int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        switch (opcode) {
        cbse Opcodes.INVOKESPECIAL:
            invokespecibl(owner, nbme, desc, itf);
            brebk;
        cbse Opcodes.INVOKEVIRTUAL:
            invokevirtubl(owner, nbme, desc, itf);
            brebk;
        cbse Opcodes.INVOKESTATIC:
            invokestbtic(owner, nbme, desc, itf);
            brebk;
        cbse Opcodes.INVOKEINTERFACE:
            invokeinterfbce(owner, nbme, desc);
            brebk;
        defbult:
            throw new IllegblArgumentException();
        }
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        invokedynbmic(nbme, desc, bsm, bsmArgs);
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        switch (opcode) {
        cbse Opcodes.IFEQ:
            ifeq(lbbel);
            brebk;
        cbse Opcodes.IFNE:
            ifne(lbbel);
            brebk;
        cbse Opcodes.IFLT:
            iflt(lbbel);
            brebk;
        cbse Opcodes.IFGE:
            ifge(lbbel);
            brebk;
        cbse Opcodes.IFGT:
            ifgt(lbbel);
            brebk;
        cbse Opcodes.IFLE:
            ifle(lbbel);
            brebk;
        cbse Opcodes.IF_ICMPEQ:
            ificmpeq(lbbel);
            brebk;
        cbse Opcodes.IF_ICMPNE:
            ificmpne(lbbel);
            brebk;
        cbse Opcodes.IF_ICMPLT:
            ificmplt(lbbel);
            brebk;
        cbse Opcodes.IF_ICMPGE:
            ificmpge(lbbel);
            brebk;
        cbse Opcodes.IF_ICMPGT:
            ificmpgt(lbbel);
            brebk;
        cbse Opcodes.IF_ICMPLE:
            ificmple(lbbel);
            brebk;
        cbse Opcodes.IF_ACMPEQ:
            ifbcmpeq(lbbel);
            brebk;
        cbse Opcodes.IF_ACMPNE:
            ifbcmpne(lbbel);
            brebk;
        cbse Opcodes.GOTO:
            goTo(lbbel);
            brebk;
        cbse Opcodes.JSR:
            jsr(lbbel);
            brebk;
        cbse Opcodes.IFNULL:
            ifnull(lbbel);
            brebk;
        cbse Opcodes.IFNONNULL:
            ifnonnull(lbbel);
            brebk;
        defbult:
            throw new IllegblArgumentException();
        }
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        mbrk(lbbel);
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        if (cst instbnceof Integer) {
            int vbl = ((Integer) cst).intVblue();
            iconst(vbl);
        } else if (cst instbnceof Byte) {
            int vbl = ((Byte) cst).intVblue();
            iconst(vbl);
        } else if (cst instbnceof Chbrbcter) {
            int vbl = ((Chbrbcter) cst).chbrVblue();
            iconst(vbl);
        } else if (cst instbnceof Short) {
            int vbl = ((Short) cst).intVblue();
            iconst(vbl);
        } else if (cst instbnceof Boolebn) {
            int vbl = ((Boolebn) cst).boolebnVblue() ? 1 : 0;
            iconst(vbl);
        } else if (cst instbnceof Flobt) {
            flobt vbl = ((Flobt) cst).flobtVblue();
            fconst(vbl);
        } else if (cst instbnceof Long) {
            long vbl = ((Long) cst).longVblue();
            lconst(vbl);
        } else if (cst instbnceof Double) {
            double vbl = ((Double) cst).doubleVblue();
            dconst(vbl);
        } else if (cst instbnceof String) {
            bconst(cst);
        } else if (cst instbnceof Type) {
            tconst((Type) cst);
        } else if (cst instbnceof Hbndle) {
            hconst((Hbndle) cst);
        } else {
            throw new IllegblArgumentException();
        }
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        iinc(vbr, increment);
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        tbbleswitch(min, mbx, dflt, lbbels);
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        lookupswitch(dflt, keys, lbbels);
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        multibnewbrrby(desc, dims);
    }

    // -----------------------------------------------------------------------

    public void nop() {
        mv.visitInsn(Opcodes.NOP);
    }

    public void bconst(finbl Object cst) {
        if (cst == null) {
            mv.visitInsn(Opcodes.ACONST_NULL);
        } else {
            mv.visitLdcInsn(cst);
        }
    }

    public void iconst(finbl int cst) {
        if (cst >= -1 && cst <= 5) {
            mv.visitInsn(Opcodes.ICONST_0 + cst);
        } else if (cst >= Byte.MIN_VALUE && cst <= Byte.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.BIPUSH, cst);
        } else if (cst >= Short.MIN_VALUE && cst <= Short.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.SIPUSH, cst);
        } else {
            mv.visitLdcInsn(new Integer(cst));
        }
    }

    public void lconst(finbl long cst) {
        if (cst == 0L || cst == 1L) {
            mv.visitInsn(Opcodes.LCONST_0 + (int) cst);
        } else {
            mv.visitLdcInsn(new Long(cst));
        }
    }

    public void fconst(finbl flobt cst) {
        int bits = Flobt.flobtToIntBits(cst);
        if (bits == 0L || bits == 0x3f800000 || bits == 0x40000000) { // 0..2
            mv.visitInsn(Opcodes.FCONST_0 + (int) cst);
        } else {
            mv.visitLdcInsn(new Flobt(cst));
        }
    }

    public void dconst(finbl double cst) {
        long bits = Double.doubleToLongBits(cst);
        if (bits == 0L || bits == 0x3ff0000000000000L) { // +0.0d bnd 1.0d
            mv.visitInsn(Opcodes.DCONST_0 + (int) cst);
        } else {
            mv.visitLdcInsn(new Double(cst));
        }
    }

    public void tconst(finbl Type type) {
        mv.visitLdcInsn(type);
    }

    public void hconst(finbl Hbndle hbndle) {
        mv.visitLdcInsn(hbndle);
    }

    public void lobd(finbl int vbr, finbl Type type) {
        mv.visitVbrInsn(type.getOpcode(Opcodes.ILOAD), vbr);
    }

    public void blobd(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IALOAD));
    }

    public void store(finbl int vbr, finbl Type type) {
        mv.visitVbrInsn(type.getOpcode(Opcodes.ISTORE), vbr);
    }

    public void bstore(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IASTORE));
    }

    public void pop() {
        mv.visitInsn(Opcodes.POP);
    }

    public void pop2() {
        mv.visitInsn(Opcodes.POP2);
    }

    public void dup() {
        mv.visitInsn(Opcodes.DUP);
    }

    public void dup2() {
        mv.visitInsn(Opcodes.DUP2);
    }

    public void dupX1() {
        mv.visitInsn(Opcodes.DUP_X1);
    }

    public void dupX2() {
        mv.visitInsn(Opcodes.DUP_X2);
    }

    public void dup2X1() {
        mv.visitInsn(Opcodes.DUP2_X1);
    }

    public void dup2X2() {
        mv.visitInsn(Opcodes.DUP2_X2);
    }

    public void swbp() {
        mv.visitInsn(Opcodes.SWAP);
    }

    public void bdd(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IADD));
    }

    public void sub(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.ISUB));
    }

    public void mul(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IMUL));
    }

    public void div(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IDIV));
    }

    public void rem(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IREM));
    }

    public void neg(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.INEG));
    }

    public void shl(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.ISHL));
    }

    public void shr(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.ISHR));
    }

    public void ushr(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IUSHR));
    }

    public void bnd(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IAND));
    }

    public void or(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IOR));
    }

    public void xor(finbl Type type) {
        mv.visitInsn(type.getOpcode(Opcodes.IXOR));
    }

    public void iinc(finbl int vbr, finbl int increment) {
        mv.visitIincInsn(vbr, increment);
    }

    public void cbst(finbl Type from, finbl Type to) {
        if (from != to) {
            if (from == Type.DOUBLE_TYPE) {
                if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.D2F);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.D2L);
                } else {
                    mv.visitInsn(Opcodes.D2I);
                    cbst(Type.INT_TYPE, to);
                }
            } else if (from == Type.FLOAT_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.F2D);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.F2L);
                } else {
                    mv.visitInsn(Opcodes.F2I);
                    cbst(Type.INT_TYPE, to);
                }
            } else if (from == Type.LONG_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.L2D);
                } else if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.L2F);
                } else {
                    mv.visitInsn(Opcodes.L2I);
                    cbst(Type.INT_TYPE, to);
                }
            } else {
                if (to == Type.BYTE_TYPE) {
                    mv.visitInsn(Opcodes.I2B);
                } else if (to == Type.CHAR_TYPE) {
                    mv.visitInsn(Opcodes.I2C);
                } else if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.I2D);
                } else if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.I2F);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.I2L);
                } else if (to == Type.SHORT_TYPE) {
                    mv.visitInsn(Opcodes.I2S);
                }
            }
        }
    }

    public void lcmp() {
        mv.visitInsn(Opcodes.LCMP);
    }

    public void cmpl(finbl Type type) {
        mv.visitInsn(type == Type.FLOAT_TYPE ? Opcodes.FCMPL : Opcodes.DCMPL);
    }

    public void cmpg(finbl Type type) {
        mv.visitInsn(type == Type.FLOAT_TYPE ? Opcodes.FCMPG : Opcodes.DCMPG);
    }

    public void ifeq(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFEQ, lbbel);
    }

    public void ifne(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFNE, lbbel);
    }

    public void iflt(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFLT, lbbel);
    }

    public void ifge(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFGE, lbbel);
    }

    public void ifgt(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFGT, lbbel);
    }

    public void ifle(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFLE, lbbel);
    }

    public void ificmpeq(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ICMPEQ, lbbel);
    }

    public void ificmpne(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ICMPNE, lbbel);
    }

    public void ificmplt(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ICMPLT, lbbel);
    }

    public void ificmpge(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, lbbel);
    }

    public void ificmpgt(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, lbbel);
    }

    public void ificmple(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, lbbel);
    }

    public void ifbcmpeq(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ACMPEQ, lbbel);
    }

    public void ifbcmpne(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IF_ACMPNE, lbbel);
    }

    public void goTo(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.GOTO, lbbel);
    }

    public void jsr(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.JSR, lbbel);
    }

    public void ret(finbl int vbr) {
        mv.visitVbrInsn(Opcodes.RET, vbr);
    }

    public void tbbleswitch(finbl int min, finbl int mbx, finbl Lbbel dflt,
            finbl Lbbel... lbbels) {
        mv.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
    }

    public void lookupswitch(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        mv.visitLookupSwitchInsn(dflt, keys, lbbels);
    }

    public void breturn(finbl Type t) {
        mv.visitInsn(t.getOpcode(Opcodes.IRETURN));
    }

    public void getstbtic(finbl String owner, finbl String nbme,
            finbl String desc) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, owner, nbme, desc);
    }

    public void putstbtic(finbl String owner, finbl String nbme,
            finbl String desc) {
        mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, nbme, desc);
    }

    public void getfield(finbl String owner, finbl String nbme,
            finbl String desc) {
        mv.visitFieldInsn(Opcodes.GETFIELD, owner, nbme, desc);
    }

    public void putfield(finbl String owner, finbl String nbme,
            finbl String desc) {
        mv.visitFieldInsn(Opcodes.PUTFIELD, owner, nbme, desc);
    }

    @Deprecbted
    public void invokevirtubl(finbl String owner, finbl String nbme,
            finbl String desc) {
        if (bpi >= Opcodes.ASM5) {
            invokevirtubl(owner, nbme, desc, fblse);
            return;
        }
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, owner, nbme, desc);
    }

    public void invokevirtubl(finbl String owner, finbl String nbme,
            finbl String desc, finbl boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            if (itf) {
                throw new IllegblArgumentException(
                        "INVOKEVIRTUAL on interfbces require ASM 5");
            }
            invokevirtubl(owner, nbme, desc);
            return;
        }
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, owner, nbme, desc, itf);
    }

    @Deprecbted
    public void invokespecibl(finbl String owner, finbl String nbme,
            finbl String desc) {
        if (bpi >= Opcodes.ASM5) {
            invokespecibl(owner, nbme, desc, fblse);
            return;
        }
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, owner, nbme, desc, fblse);
    }

    public void invokespecibl(finbl String owner, finbl String nbme,
            finbl String desc, finbl boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            if (itf) {
                throw new IllegblArgumentException(
                        "INVOKESPECIAL on interfbces require ASM 5");
            }
            invokespecibl(owner, nbme, desc);
            return;
        }
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, owner, nbme, desc, itf);
    }

    @Deprecbted
    public void invokestbtic(finbl String owner, finbl String nbme,
            finbl String desc) {
        if (bpi >= Opcodes.ASM5) {
            invokestbtic(owner, nbme, desc, fblse);
            return;
        }
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, owner, nbme, desc, fblse);
    }

    public void invokestbtic(finbl String owner, finbl String nbme,
            finbl String desc, finbl boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            if (itf) {
                throw new IllegblArgumentException(
                        "INVOKESTATIC on interfbces require ASM 5");
            }
            invokestbtic(owner, nbme, desc);
            return;
        }
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, owner, nbme, desc, itf);
    }

    public void invokeinterfbce(finbl String owner, finbl String nbme,
            finbl String desc) {
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, owner, nbme, desc, true);
    }

    public void invokedynbmic(String nbme, String desc, Hbndle bsm,
            Object[] bsmArgs) {
        mv.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
    }

    public void bnew(finbl Type type) {
        mv.visitTypeInsn(Opcodes.NEW, type.getInternblNbme());
    }

    public void newbrrby(finbl Type type) {
        int typ;
        switch (type.getSort()) {
        cbse Type.BOOLEAN:
            typ = Opcodes.T_BOOLEAN;
            brebk;
        cbse Type.CHAR:
            typ = Opcodes.T_CHAR;
            brebk;
        cbse Type.BYTE:
            typ = Opcodes.T_BYTE;
            brebk;
        cbse Type.SHORT:
            typ = Opcodes.T_SHORT;
            brebk;
        cbse Type.INT:
            typ = Opcodes.T_INT;
            brebk;
        cbse Type.FLOAT:
            typ = Opcodes.T_FLOAT;
            brebk;
        cbse Type.LONG:
            typ = Opcodes.T_LONG;
            brebk;
        cbse Type.DOUBLE:
            typ = Opcodes.T_DOUBLE;
            brebk;
        defbult:
            mv.visitTypeInsn(Opcodes.ANEWARRAY, type.getInternblNbme());
            return;
        }
        mv.visitIntInsn(Opcodes.NEWARRAY, typ);
    }

    public void brrbylength() {
        mv.visitInsn(Opcodes.ARRAYLENGTH);
    }

    public void bthrow() {
        mv.visitInsn(Opcodes.ATHROW);
    }

    public void checkcbst(finbl Type type) {
        mv.visitTypeInsn(Opcodes.CHECKCAST, type.getInternblNbme());
    }

    public void instbnceOf(finbl Type type) {
        mv.visitTypeInsn(Opcodes.INSTANCEOF, type.getInternblNbme());
    }

    public void monitorenter() {
        mv.visitInsn(Opcodes.MONITORENTER);
    }

    public void monitorexit() {
        mv.visitInsn(Opcodes.MONITOREXIT);
    }

    public void multibnewbrrby(finbl String desc, finbl int dims) {
        mv.visitMultiANewArrbyInsn(desc, dims);
    }

    public void ifnull(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFNULL, lbbel);
    }

    public void ifnonnull(finbl Lbbel lbbel) {
        mv.visitJumpInsn(Opcodes.IFNONNULL, lbbel);
    }

    public void mbrk(finbl Lbbel lbbel) {
        mv.visitLbbel(lbbel);
    }
}
