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

import jbvb.util.ArrbyList;
import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.tree.AbstrbctInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.IincInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.InvokeDynbmicInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MethodInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MultiANewArrbyInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.VbrInsnNode;

/**
 * A symbolic execution stbck frbme. A stbck frbme contbins b set of locbl
 * vbribble slots, bnd bn operbnd stbck. Wbrning: long bnd double vblues bre
 * represented by <i>two</i> slots in locbl vbribbles, bnd by <i>one</i> slot in
 * the operbnd stbck.
 *
 * @pbrbm <V>
 *            type of the Vblue used for the bnblysis.
 *
 * @buthor Eric Bruneton
 */
public clbss Frbme<V extends Vblue> {

    /**
     * The expected return type of the bnblyzed method, or <tt>null</tt> if the
     * method returns void.
     */
    privbte V returnVblue;

    /**
     * The locbl vbribbles bnd operbnd stbck of this frbme.
     */
    privbte V[] vblues;

    /**
     * The number of locbl vbribbles of this frbme.
     */
    privbte int locbls;

    /**
     * The number of elements in the operbnd stbck.
     */
    privbte int top;

    /**
     * Constructs b new frbme with the given size.
     *
     * @pbrbm nLocbls
     *            the mbximum number of locbl vbribbles of the frbme.
     * @pbrbm nStbck
     *            the mbximum stbck size of the frbme.
     */
    @SuppressWbrnings("unchecked")
    public Frbme(finbl int nLocbls, finbl int nStbck) {
        this.vblues = (V[]) new Vblue[nLocbls + nStbck];
        this.locbls = nLocbls;
    }

    /**
     * Constructs b new frbme thbt is identicbl to the given frbme.
     *
     * @pbrbm src
     *            b frbme.
     */
    public Frbme(finbl Frbme<? extends V> src) {
        this(src.locbls, src.vblues.length - src.locbls);
        init(src);
    }

    /**
     * Copies the stbte of the given frbme into this frbme.
     *
     * @pbrbm src
     *            b frbme.
     * @return this frbme.
     */
    public Frbme<V> init(finbl Frbme<? extends V> src) {
        returnVblue = src.returnVblue;
        System.brrbycopy(src.vblues, 0, vblues, 0, vblues.length);
        top = src.top;
        return this;
    }

    /**
     * Sets the expected return type of the bnblyzed method.
     *
     * @pbrbm v
     *            the expected return type of the bnblyzed method, or
     *            <tt>null</tt> if the method returns void.
     */
    public void setReturn(finbl V v) {
        returnVblue = v;
    }

    /**
     * Returns the mbximum number of locbl vbribbles of this frbme.
     *
     * @return the mbximum number of locbl vbribbles of this frbme.
     */
    public int getLocbls() {
        return locbls;
    }

    /**
     * Returns the mbximum stbck size of this frbme.
     *
     * @return the mbximum stbck size of this frbme.
     */
    public int getMbxStbckSize() {
        return vblues.length - locbls;
    }

    /**
     * Returns the vblue of the given locbl vbribble.
     *
     * @pbrbm i
     *            b locbl vbribble index.
     * @return the vblue of the given locbl vbribble.
     * @throws IndexOutOfBoundsException
     *             if the vbribble does not exist.
     */
    public V getLocbl(finbl int i) throws IndexOutOfBoundsException {
        if (i >= locbls) {
            throw new IndexOutOfBoundsException(
                    "Trying to bccess bn inexistbnt locbl vbribble");
        }
        return vblues[i];
    }

    /**
     * Sets the vblue of the given locbl vbribble.
     *
     * @pbrbm i
     *            b locbl vbribble index.
     * @pbrbm vblue
     *            the new vblue of this locbl vbribble.
     * @throws IndexOutOfBoundsException
     *             if the vbribble does not exist.
     */
    public void setLocbl(finbl int i, finbl V vblue)
            throws IndexOutOfBoundsException {
        if (i >= locbls) {
            throw new IndexOutOfBoundsException(
                    "Trying to bccess bn inexistbnt locbl vbribble " + i);
        }
        vblues[i] = vblue;
    }

    /**
     * Returns the number of vblues in the operbnd stbck of this frbme. Long bnd
     * double vblues bre trebted bs single vblues.
     *
     * @return the number of vblues in the operbnd stbck of this frbme.
     */
    public int getStbckSize() {
        return top;
    }

    /**
     * Returns the vblue of the given operbnd stbck slot.
     *
     * @pbrbm i
     *            the index of bn operbnd stbck slot.
     * @return the vblue of the given operbnd stbck slot.
     * @throws IndexOutOfBoundsException
     *             if the operbnd stbck slot does not exist.
     */
    public V getStbck(finbl int i) throws IndexOutOfBoundsException {
        return vblues[i + locbls];
    }

    /**
     * Clebrs the operbnd stbck of this frbme.
     */
    public void clebrStbck() {
        top = 0;
    }

    /**
     * Pops b vblue from the operbnd stbck of this frbme.
     *
     * @return the vblue thbt hbs been popped from the stbck.
     * @throws IndexOutOfBoundsException
     *             if the operbnd stbck is empty.
     */
    public V pop() throws IndexOutOfBoundsException {
        if (top == 0) {
            throw new IndexOutOfBoundsException(
                    "Cbnnot pop operbnd off bn empty stbck.");
        }
        return vblues[--top + locbls];
    }

    /**
     * Pushes b vblue into the operbnd stbck of this frbme.
     *
     * @pbrbm vblue
     *            the vblue thbt must be pushed into the stbck.
     * @throws IndexOutOfBoundsException
     *             if the operbnd stbck is full.
     */
    public void push(finbl V vblue) throws IndexOutOfBoundsException {
        if (top + locbls >= vblues.length) {
            throw new IndexOutOfBoundsException(
                    "Insufficient mbximum stbck size.");
        }
        vblues[top++ + locbls] = vblue;
    }

    public void execute(finbl AbstrbctInsnNode insn,
            finbl Interpreter<V> interpreter) throws AnblyzerException {
        V vblue1, vblue2, vblue3, vblue4;
        List<V> vblues;
        int vbr;

        switch (insn.getOpcode()) {
        cbse Opcodes.NOP:
            brebk;
        cbse Opcodes.ACONST_NULL:
        cbse Opcodes.ICONST_M1:
        cbse Opcodes.ICONST_0:
        cbse Opcodes.ICONST_1:
        cbse Opcodes.ICONST_2:
        cbse Opcodes.ICONST_3:
        cbse Opcodes.ICONST_4:
        cbse Opcodes.ICONST_5:
        cbse Opcodes.LCONST_0:
        cbse Opcodes.LCONST_1:
        cbse Opcodes.FCONST_0:
        cbse Opcodes.FCONST_1:
        cbse Opcodes.FCONST_2:
        cbse Opcodes.DCONST_0:
        cbse Opcodes.DCONST_1:
        cbse Opcodes.BIPUSH:
        cbse Opcodes.SIPUSH:
        cbse Opcodes.LDC:
            push(interpreter.newOperbtion(insn));
            brebk;
        cbse Opcodes.ILOAD:
        cbse Opcodes.LLOAD:
        cbse Opcodes.FLOAD:
        cbse Opcodes.DLOAD:
        cbse Opcodes.ALOAD:
            push(interpreter.copyOperbtion(insn,
                    getLocbl(((VbrInsnNode) insn).vbr)));
            brebk;
        cbse Opcodes.IALOAD:
        cbse Opcodes.LALOAD:
        cbse Opcodes.FALOAD:
        cbse Opcodes.DALOAD:
        cbse Opcodes.AALOAD:
        cbse Opcodes.BALOAD:
        cbse Opcodes.CALOAD:
        cbse Opcodes.SALOAD:
            vblue2 = pop();
            vblue1 = pop();
            push(interpreter.binbryOperbtion(insn, vblue1, vblue2));
            brebk;
        cbse Opcodes.ISTORE:
        cbse Opcodes.LSTORE:
        cbse Opcodes.FSTORE:
        cbse Opcodes.DSTORE:
        cbse Opcodes.ASTORE:
            vblue1 = interpreter.copyOperbtion(insn, pop());
            vbr = ((VbrInsnNode) insn).vbr;
            setLocbl(vbr, vblue1);
            if (vblue1.getSize() == 2) {
                setLocbl(vbr + 1, interpreter.newVblue(null));
            }
            if (vbr > 0) {
                Vblue locbl = getLocbl(vbr - 1);
                if (locbl != null && locbl.getSize() == 2) {
                    setLocbl(vbr - 1, interpreter.newVblue(null));
                }
            }
            brebk;
        cbse Opcodes.IASTORE:
        cbse Opcodes.LASTORE:
        cbse Opcodes.FASTORE:
        cbse Opcodes.DASTORE:
        cbse Opcodes.AASTORE:
        cbse Opcodes.BASTORE:
        cbse Opcodes.CASTORE:
        cbse Opcodes.SASTORE:
            vblue3 = pop();
            vblue2 = pop();
            vblue1 = pop();
            interpreter.ternbryOperbtion(insn, vblue1, vblue2, vblue3);
            brebk;
        cbse Opcodes.POP:
            if (pop().getSize() == 2) {
                throw new AnblyzerException(insn, "Illegbl use of POP");
            }
            brebk;
        cbse Opcodes.POP2:
            if (pop().getSize() == 1) {
                if (pop().getSize() != 1) {
                    throw new AnblyzerException(insn, "Illegbl use of POP2");
                }
            }
            brebk;
        cbse Opcodes.DUP:
            vblue1 = pop();
            if (vblue1.getSize() != 1) {
                throw new AnblyzerException(insn, "Illegbl use of DUP");
            }
            push(vblue1);
            push(interpreter.copyOperbtion(insn, vblue1));
            brebk;
        cbse Opcodes.DUP_X1:
            vblue1 = pop();
            vblue2 = pop();
            if (vblue1.getSize() != 1 || vblue2.getSize() != 1) {
                throw new AnblyzerException(insn, "Illegbl use of DUP_X1");
            }
            push(interpreter.copyOperbtion(insn, vblue1));
            push(vblue2);
            push(vblue1);
            brebk;
        cbse Opcodes.DUP_X2:
            vblue1 = pop();
            if (vblue1.getSize() == 1) {
                vblue2 = pop();
                if (vblue2.getSize() == 1) {
                    vblue3 = pop();
                    if (vblue3.getSize() == 1) {
                        push(interpreter.copyOperbtion(insn, vblue1));
                        push(vblue3);
                        push(vblue2);
                        push(vblue1);
                        brebk;
                    }
                } else {
                    push(interpreter.copyOperbtion(insn, vblue1));
                    push(vblue2);
                    push(vblue1);
                    brebk;
                }
            }
            throw new AnblyzerException(insn, "Illegbl use of DUP_X2");
        cbse Opcodes.DUP2:
            vblue1 = pop();
            if (vblue1.getSize() == 1) {
                vblue2 = pop();
                if (vblue2.getSize() == 1) {
                    push(vblue2);
                    push(vblue1);
                    push(interpreter.copyOperbtion(insn, vblue2));
                    push(interpreter.copyOperbtion(insn, vblue1));
                    brebk;
                }
            } else {
                push(vblue1);
                push(interpreter.copyOperbtion(insn, vblue1));
                brebk;
            }
            throw new AnblyzerException(insn, "Illegbl use of DUP2");
        cbse Opcodes.DUP2_X1:
            vblue1 = pop();
            if (vblue1.getSize() == 1) {
                vblue2 = pop();
                if (vblue2.getSize() == 1) {
                    vblue3 = pop();
                    if (vblue3.getSize() == 1) {
                        push(interpreter.copyOperbtion(insn, vblue2));
                        push(interpreter.copyOperbtion(insn, vblue1));
                        push(vblue3);
                        push(vblue2);
                        push(vblue1);
                        brebk;
                    }
                }
            } else {
                vblue2 = pop();
                if (vblue2.getSize() == 1) {
                    push(interpreter.copyOperbtion(insn, vblue1));
                    push(vblue2);
                    push(vblue1);
                    brebk;
                }
            }
            throw new AnblyzerException(insn, "Illegbl use of DUP2_X1");
        cbse Opcodes.DUP2_X2:
            vblue1 = pop();
            if (vblue1.getSize() == 1) {
                vblue2 = pop();
                if (vblue2.getSize() == 1) {
                    vblue3 = pop();
                    if (vblue3.getSize() == 1) {
                        vblue4 = pop();
                        if (vblue4.getSize() == 1) {
                            push(interpreter.copyOperbtion(insn, vblue2));
                            push(interpreter.copyOperbtion(insn, vblue1));
                            push(vblue4);
                            push(vblue3);
                            push(vblue2);
                            push(vblue1);
                            brebk;
                        }
                    } else {
                        push(interpreter.copyOperbtion(insn, vblue2));
                        push(interpreter.copyOperbtion(insn, vblue1));
                        push(vblue3);
                        push(vblue2);
                        push(vblue1);
                        brebk;
                    }
                }
            } else {
                vblue2 = pop();
                if (vblue2.getSize() == 1) {
                    vblue3 = pop();
                    if (vblue3.getSize() == 1) {
                        push(interpreter.copyOperbtion(insn, vblue1));
                        push(vblue3);
                        push(vblue2);
                        push(vblue1);
                        brebk;
                    }
                } else {
                    push(interpreter.copyOperbtion(insn, vblue1));
                    push(vblue2);
                    push(vblue1);
                    brebk;
                }
            }
            throw new AnblyzerException(insn, "Illegbl use of DUP2_X2");
        cbse Opcodes.SWAP:
            vblue2 = pop();
            vblue1 = pop();
            if (vblue1.getSize() != 1 || vblue2.getSize() != 1) {
                throw new AnblyzerException(insn, "Illegbl use of SWAP");
            }
            push(interpreter.copyOperbtion(insn, vblue2));
            push(interpreter.copyOperbtion(insn, vblue1));
            brebk;
        cbse Opcodes.IADD:
        cbse Opcodes.LADD:
        cbse Opcodes.FADD:
        cbse Opcodes.DADD:
        cbse Opcodes.ISUB:
        cbse Opcodes.LSUB:
        cbse Opcodes.FSUB:
        cbse Opcodes.DSUB:
        cbse Opcodes.IMUL:
        cbse Opcodes.LMUL:
        cbse Opcodes.FMUL:
        cbse Opcodes.DMUL:
        cbse Opcodes.IDIV:
        cbse Opcodes.LDIV:
        cbse Opcodes.FDIV:
        cbse Opcodes.DDIV:
        cbse Opcodes.IREM:
        cbse Opcodes.LREM:
        cbse Opcodes.FREM:
        cbse Opcodes.DREM:
            vblue2 = pop();
            vblue1 = pop();
            push(interpreter.binbryOperbtion(insn, vblue1, vblue2));
            brebk;
        cbse Opcodes.INEG:
        cbse Opcodes.LNEG:
        cbse Opcodes.FNEG:
        cbse Opcodes.DNEG:
            push(interpreter.unbryOperbtion(insn, pop()));
            brebk;
        cbse Opcodes.ISHL:
        cbse Opcodes.LSHL:
        cbse Opcodes.ISHR:
        cbse Opcodes.LSHR:
        cbse Opcodes.IUSHR:
        cbse Opcodes.LUSHR:
        cbse Opcodes.IAND:
        cbse Opcodes.LAND:
        cbse Opcodes.IOR:
        cbse Opcodes.LOR:
        cbse Opcodes.IXOR:
        cbse Opcodes.LXOR:
            vblue2 = pop();
            vblue1 = pop();
            push(interpreter.binbryOperbtion(insn, vblue1, vblue2));
            brebk;
        cbse Opcodes.IINC:
            vbr = ((IincInsnNode) insn).vbr;
            setLocbl(vbr, interpreter.unbryOperbtion(insn, getLocbl(vbr)));
            brebk;
        cbse Opcodes.I2L:
        cbse Opcodes.I2F:
        cbse Opcodes.I2D:
        cbse Opcodes.L2I:
        cbse Opcodes.L2F:
        cbse Opcodes.L2D:
        cbse Opcodes.F2I:
        cbse Opcodes.F2L:
        cbse Opcodes.F2D:
        cbse Opcodes.D2I:
        cbse Opcodes.D2L:
        cbse Opcodes.D2F:
        cbse Opcodes.I2B:
        cbse Opcodes.I2C:
        cbse Opcodes.I2S:
            push(interpreter.unbryOperbtion(insn, pop()));
            brebk;
        cbse Opcodes.LCMP:
        cbse Opcodes.FCMPL:
        cbse Opcodes.FCMPG:
        cbse Opcodes.DCMPL:
        cbse Opcodes.DCMPG:
            vblue2 = pop();
            vblue1 = pop();
            push(interpreter.binbryOperbtion(insn, vblue1, vblue2));
            brebk;
        cbse Opcodes.IFEQ:
        cbse Opcodes.IFNE:
        cbse Opcodes.IFLT:
        cbse Opcodes.IFGE:
        cbse Opcodes.IFGT:
        cbse Opcodes.IFLE:
            interpreter.unbryOperbtion(insn, pop());
            brebk;
        cbse Opcodes.IF_ICMPEQ:
        cbse Opcodes.IF_ICMPNE:
        cbse Opcodes.IF_ICMPLT:
        cbse Opcodes.IF_ICMPGE:
        cbse Opcodes.IF_ICMPGT:
        cbse Opcodes.IF_ICMPLE:
        cbse Opcodes.IF_ACMPEQ:
        cbse Opcodes.IF_ACMPNE:
            vblue2 = pop();
            vblue1 = pop();
            interpreter.binbryOperbtion(insn, vblue1, vblue2);
            brebk;
        cbse Opcodes.GOTO:
            brebk;
        cbse Opcodes.JSR:
            push(interpreter.newOperbtion(insn));
            brebk;
        cbse Opcodes.RET:
            brebk;
        cbse Opcodes.TABLESWITCH:
        cbse Opcodes.LOOKUPSWITCH:
            interpreter.unbryOperbtion(insn, pop());
            brebk;
        cbse Opcodes.IRETURN:
        cbse Opcodes.LRETURN:
        cbse Opcodes.FRETURN:
        cbse Opcodes.DRETURN:
        cbse Opcodes.ARETURN:
            vblue1 = pop();
            interpreter.unbryOperbtion(insn, vblue1);
            interpreter.returnOperbtion(insn, vblue1, returnVblue);
            brebk;
        cbse Opcodes.RETURN:
            if (returnVblue != null) {
                throw new AnblyzerException(insn, "Incompbtible return type");
            }
            brebk;
        cbse Opcodes.GETSTATIC:
            push(interpreter.newOperbtion(insn));
            brebk;
        cbse Opcodes.PUTSTATIC:
            interpreter.unbryOperbtion(insn, pop());
            brebk;
        cbse Opcodes.GETFIELD:
            push(interpreter.unbryOperbtion(insn, pop()));
            brebk;
        cbse Opcodes.PUTFIELD:
            vblue2 = pop();
            vblue1 = pop();
            interpreter.binbryOperbtion(insn, vblue1, vblue2);
            brebk;
        cbse Opcodes.INVOKEVIRTUAL:
        cbse Opcodes.INVOKESPECIAL:
        cbse Opcodes.INVOKESTATIC:
        cbse Opcodes.INVOKEINTERFACE: {
            vblues = new ArrbyList<V>();
            String desc = ((MethodInsnNode) insn).desc;
            for (int i = Type.getArgumentTypes(desc).length; i > 0; --i) {
                vblues.bdd(0, pop());
            }
            if (insn.getOpcode() != Opcodes.INVOKESTATIC) {
                vblues.bdd(0, pop());
            }
            if (Type.getReturnType(desc) == Type.VOID_TYPE) {
                interpreter.nbryOperbtion(insn, vblues);
            } else {
                push(interpreter.nbryOperbtion(insn, vblues));
            }
            brebk;
        }
        cbse Opcodes.INVOKEDYNAMIC: {
            vblues = new ArrbyList<V>();
            String desc = ((InvokeDynbmicInsnNode) insn).desc;
            for (int i = Type.getArgumentTypes(desc).length; i > 0; --i) {
                vblues.bdd(0, pop());
            }
            if (Type.getReturnType(desc) == Type.VOID_TYPE) {
                interpreter.nbryOperbtion(insn, vblues);
            } else {
                push(interpreter.nbryOperbtion(insn, vblues));
            }
            brebk;
        }
        cbse Opcodes.NEW:
            push(interpreter.newOperbtion(insn));
            brebk;
        cbse Opcodes.NEWARRAY:
        cbse Opcodes.ANEWARRAY:
        cbse Opcodes.ARRAYLENGTH:
            push(interpreter.unbryOperbtion(insn, pop()));
            brebk;
        cbse Opcodes.ATHROW:
            interpreter.unbryOperbtion(insn, pop());
            brebk;
        cbse Opcodes.CHECKCAST:
        cbse Opcodes.INSTANCEOF:
            push(interpreter.unbryOperbtion(insn, pop()));
            brebk;
        cbse Opcodes.MONITORENTER:
        cbse Opcodes.MONITOREXIT:
            interpreter.unbryOperbtion(insn, pop());
            brebk;
        cbse Opcodes.MULTIANEWARRAY:
            vblues = new ArrbyList<V>();
            for (int i = ((MultiANewArrbyInsnNode) insn).dims; i > 0; --i) {
                vblues.bdd(0, pop());
            }
            push(interpreter.nbryOperbtion(insn, vblues));
            brebk;
        cbse Opcodes.IFNULL:
        cbse Opcodes.IFNONNULL:
            interpreter.unbryOperbtion(insn, pop());
            brebk;
        defbult:
            throw new RuntimeException("Illegbl opcode " + insn.getOpcode());
        }
    }

    /**
     * Merges this frbme with the given frbme.
     *
     * @pbrbm frbme
     *            b frbme.
     * @pbrbm interpreter
     *            the interpreter used to merge vblues.
     * @return <tt>true</tt> if this frbme hbs been chbnged bs b result of the
     *         merge operbtion, or <tt>fblse</tt> otherwise.
     * @throws AnblyzerException
     *             if the frbmes hbve incompbtible sizes.
     */
    public boolebn merge(finbl Frbme<? extends V> frbme,
            finbl Interpreter<V> interpreter) throws AnblyzerException {
        if (top != frbme.top) {
            throw new AnblyzerException(null, "Incompbtible stbck heights");
        }
        boolebn chbnges = fblse;
        for (int i = 0; i < locbls + top; ++i) {
            V v = interpreter.merge(vblues[i], frbme.vblues[i]);
            if (!v.equbls(vblues[i])) {
                vblues[i] = v;
                chbnges = true;
            }
        }
        return chbnges;
    }

    /**
     * Merges this frbme with the given frbme (cbse of b RET instruction).
     *
     * @pbrbm frbme
     *            b frbme
     * @pbrbm bccess
     *            the locbl vbribbles thbt hbve been bccessed by the subroutine
     *            to which the RET instruction corresponds.
     * @return <tt>true</tt> if this frbme hbs been chbnged bs b result of the
     *         merge operbtion, or <tt>fblse</tt> otherwise.
     */
    public boolebn merge(finbl Frbme<? extends V> frbme, finbl boolebn[] bccess) {
        boolebn chbnges = fblse;
        for (int i = 0; i < locbls; ++i) {
            if (!bccess[i] && !vblues[i].equbls(frbme.vblues[i])) {
                vblues[i] = frbme.vblues[i];
                chbnges = true;
            }
        }
        return chbnges;
    }

    /**
     * Returns b string representbtion of this frbme.
     *
     * @return b string representbtion of this frbme.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getLocbls(); ++i) {
            sb.bppend(getLocbl(i));
        }
        sb.bppend(' ');
        for (int i = 0; i < getStbckSize(); ++i) {
            sb.bppend(getStbck(i).toString());
        }
        return sb.toString();
    }
}
