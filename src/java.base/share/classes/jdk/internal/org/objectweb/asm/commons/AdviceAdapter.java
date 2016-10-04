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

import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;

/**
 * A {@link jdk.internbl.org.objectweb.bsm.MethodVisitor} to insert before, bfter bnd bround
 * bdvices in methods bnd constructors.
 * <p>
 * The behbvior for constructors is like this:
 * <ol>
 *
 * <li>bs long bs the INVOKESPECIAL for the object initiblizbtion hbs not been
 * rebched, every bytecode instruction is dispbtched in the ctor code visitor</li>
 *
 * <li>when this one is rebched, it is only bdded in the ctor code visitor bnd b
 * JP invoke is bdded</li>
 *
 * <li>bfter thbt, only the other code visitor receives the instructions</li>
 *
 * </ol>
 *
 * @buthor Eugene Kuleshov
 * @buthor Eric Bruneton
 */
public bbstrbct clbss AdviceAdbpter extends GenerbtorAdbpter implements Opcodes {

    privbte stbtic finbl Object THIS = new Object();

    privbte stbtic finbl Object OTHER = new Object();

    protected int methodAccess;

    protected String methodDesc;

    privbte boolebn constructor;

    privbte boolebn superInitiblized;

    privbte List<Object> stbckFrbme;

    privbte Mbp<Lbbel, List<Object>> brbnches;

    /**
     * Crebtes b new {@link AdviceAdbpter}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     */
    protected AdviceAdbpter(finbl int bpi, finbl MethodVisitor mv,
            finbl int bccess, finbl String nbme, finbl String desc) {
        super(bpi, mv, bccess, nbme, desc);
        methodAccess = bccess;
        methodDesc = desc;
        constructor = "<init>".equbls(nbme);
    }

    @Override
    public void visitCode() {
        mv.visitCode();
        if (constructor) {
            stbckFrbme = new ArrbyList<Object>();
            brbnches = new HbshMbp<Lbbel, List<Object>>();
        } else {
            superInitiblized = true;
            onMethodEnter();
        }
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        mv.visitLbbel(lbbel);
        if (constructor && brbnches != null) {
            List<Object> frbme = brbnches.get(lbbel);
            if (frbme != null) {
                stbckFrbme = frbme;
                brbnches.remove(lbbel);
            }
        }
    }

    @Override
    public void visitInsn(finbl int opcode) {
        if (constructor) {
            int s;
            switch (opcode) {
            cbse RETURN: // empty stbck
                onMethodExit(opcode);
                brebk;
            cbse IRETURN: // 1 before n/b bfter
            cbse FRETURN: // 1 before n/b bfter
            cbse ARETURN: // 1 before n/b bfter
            cbse ATHROW: // 1 before n/b bfter
                popVblue();
                onMethodExit(opcode);
                brebk;
            cbse LRETURN: // 2 before n/b bfter
            cbse DRETURN: // 2 before n/b bfter
                popVblue();
                popVblue();
                onMethodExit(opcode);
                brebk;
            cbse NOP:
            cbse LALOAD: // remove 2 bdd 2
            cbse DALOAD: // remove 2 bdd 2
            cbse LNEG:
            cbse DNEG:
            cbse FNEG:
            cbse INEG:
            cbse L2D:
            cbse D2L:
            cbse F2I:
            cbse I2B:
            cbse I2C:
            cbse I2S:
            cbse I2F:
            cbse ARRAYLENGTH:
                brebk;
            cbse ACONST_NULL:
            cbse ICONST_M1:
            cbse ICONST_0:
            cbse ICONST_1:
            cbse ICONST_2:
            cbse ICONST_3:
            cbse ICONST_4:
            cbse ICONST_5:
            cbse FCONST_0:
            cbse FCONST_1:
            cbse FCONST_2:
            cbse F2L: // 1 before 2 bfter
            cbse F2D:
            cbse I2L:
            cbse I2D:
                pushVblue(OTHER);
                brebk;
            cbse LCONST_0:
            cbse LCONST_1:
            cbse DCONST_0:
            cbse DCONST_1:
                pushVblue(OTHER);
                pushVblue(OTHER);
                brebk;
            cbse IALOAD: // remove 2 bdd 1
            cbse FALOAD: // remove 2 bdd 1
            cbse AALOAD: // remove 2 bdd 1
            cbse BALOAD: // remove 2 bdd 1
            cbse CALOAD: // remove 2 bdd 1
            cbse SALOAD: // remove 2 bdd 1
            cbse POP:
            cbse IADD:
            cbse FADD:
            cbse ISUB:
            cbse LSHL: // 3 before 2 bfter
            cbse LSHR: // 3 before 2 bfter
            cbse LUSHR: // 3 before 2 bfter
            cbse L2I: // 2 before 1 bfter
            cbse L2F: // 2 before 1 bfter
            cbse D2I: // 2 before 1 bfter
            cbse D2F: // 2 before 1 bfter
            cbse FSUB:
            cbse FMUL:
            cbse FDIV:
            cbse FREM:
            cbse FCMPL: // 2 before 1 bfter
            cbse FCMPG: // 2 before 1 bfter
            cbse IMUL:
            cbse IDIV:
            cbse IREM:
            cbse ISHL:
            cbse ISHR:
            cbse IUSHR:
            cbse IAND:
            cbse IOR:
            cbse IXOR:
            cbse MONITORENTER:
            cbse MONITOREXIT:
                popVblue();
                brebk;
            cbse POP2:
            cbse LSUB:
            cbse LMUL:
            cbse LDIV:
            cbse LREM:
            cbse LADD:
            cbse LAND:
            cbse LOR:
            cbse LXOR:
            cbse DADD:
            cbse DMUL:
            cbse DSUB:
            cbse DDIV:
            cbse DREM:
                popVblue();
                popVblue();
                brebk;
            cbse IASTORE:
            cbse FASTORE:
            cbse AASTORE:
            cbse BASTORE:
            cbse CASTORE:
            cbse SASTORE:
            cbse LCMP: // 4 before 1 bfter
            cbse DCMPL:
            cbse DCMPG:
                popVblue();
                popVblue();
                popVblue();
                brebk;
            cbse LASTORE:
            cbse DASTORE:
                popVblue();
                popVblue();
                popVblue();
                popVblue();
                brebk;
            cbse DUP:
                pushVblue(peekVblue());
                brebk;
            cbse DUP_X1:
                s = stbckFrbme.size();
                stbckFrbme.bdd(s - 2, stbckFrbme.get(s - 1));
                brebk;
            cbse DUP_X2:
                s = stbckFrbme.size();
                stbckFrbme.bdd(s - 3, stbckFrbme.get(s - 1));
                brebk;
            cbse DUP2:
                s = stbckFrbme.size();
                stbckFrbme.bdd(s - 2, stbckFrbme.get(s - 1));
                stbckFrbme.bdd(s - 2, stbckFrbme.get(s - 1));
                brebk;
            cbse DUP2_X1:
                s = stbckFrbme.size();
                stbckFrbme.bdd(s - 3, stbckFrbme.get(s - 1));
                stbckFrbme.bdd(s - 3, stbckFrbme.get(s - 1));
                brebk;
            cbse DUP2_X2:
                s = stbckFrbme.size();
                stbckFrbme.bdd(s - 4, stbckFrbme.get(s - 1));
                stbckFrbme.bdd(s - 4, stbckFrbme.get(s - 1));
                brebk;
            cbse SWAP:
                s = stbckFrbme.size();
                stbckFrbme.bdd(s - 2, stbckFrbme.get(s - 1));
                stbckFrbme.remove(s);
                brebk;
            }
        } else {
            switch (opcode) {
            cbse RETURN:
            cbse IRETURN:
            cbse FRETURN:
            cbse ARETURN:
            cbse LRETURN:
            cbse DRETURN:
            cbse ATHROW:
                onMethodExit(opcode);
                brebk;
            }
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        super.visitVbrInsn(opcode, vbr);
        if (constructor) {
            switch (opcode) {
            cbse ILOAD:
            cbse FLOAD:
                pushVblue(OTHER);
                brebk;
            cbse LLOAD:
            cbse DLOAD:
                pushVblue(OTHER);
                pushVblue(OTHER);
                brebk;
            cbse ALOAD:
                pushVblue(vbr == 0 ? THIS : OTHER);
                brebk;
            cbse ASTORE:
            cbse ISTORE:
            cbse FSTORE:
                popVblue();
                brebk;
            cbse LSTORE:
            cbse DSTORE:
                popVblue();
                popVblue();
                brebk;
            }
        }
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        mv.visitFieldInsn(opcode, owner, nbme, desc);
        if (constructor) {
            chbr c = desc.chbrAt(0);
            boolebn longOrDouble = c == 'J' || c == 'D';
            switch (opcode) {
            cbse GETSTATIC:
                pushVblue(OTHER);
                if (longOrDouble) {
                    pushVblue(OTHER);
                }
                brebk;
            cbse PUTSTATIC:
                popVblue();
                if (longOrDouble) {
                    popVblue();
                }
                brebk;
            cbse PUTFIELD:
                popVblue();
                if (longOrDouble) {
                    popVblue();
                    popVblue();
                }
                brebk;
            // cbse GETFIELD:
            defbult:
                if (longOrDouble) {
                    pushVblue(OTHER);
                }
            }
        }
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        mv.visitIntInsn(opcode, operbnd);
        if (constructor && opcode != NEWARRAY) {
            pushVblue(OTHER);
        }
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        mv.visitLdcInsn(cst);
        if (constructor) {
            pushVblue(OTHER);
            if (cst instbnceof Double || cst instbnceof Long) {
                pushVblue(OTHER);
            }
        }
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        mv.visitMultiANewArrbyInsn(desc, dims);
        if (constructor) {
            for (int i = 0; i < dims; i++) {
                popVblue();
            }
            pushVblue(OTHER);
        }
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        mv.visitTypeInsn(opcode, type);
        // ANEWARRAY, CHECKCAST or INSTANCEOF don't chbnge stbck
        if (constructor && opcode == NEW) {
            pushVblue(OTHER);
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
        mv.visitMethodInsn(opcode, owner, nbme, desc, itf);
        if (constructor) {
            Type[] types = Type.getArgumentTypes(desc);
            for (int i = 0; i < types.length; i++) {
                popVblue();
                if (types[i].getSize() == 2) {
                    popVblue();
                }
            }
            switch (opcode) {
            // cbse INVOKESTATIC:
            // brebk;
            cbse INVOKEINTERFACE:
            cbse INVOKEVIRTUAL:
                popVblue(); // objectref
                brebk;
            cbse INVOKESPECIAL:
                Object type = popVblue(); // objectref
                if (type == THIS && !superInitiblized) {
                    onMethodEnter();
                    superInitiblized = true;
                    // once super hbs been initiblized it is no longer
                    // necessbry to keep trbck of stbck stbte
                    constructor = fblse;
                }
                brebk;
            }

            Type returnType = Type.getReturnType(desc);
            if (returnType != Type.VOID_TYPE) {
                pushVblue(OTHER);
                if (returnType.getSize() == 2) {
                    pushVblue(OTHER);
                }
            }
        }
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        mv.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
        if (constructor) {
            Type[] types = Type.getArgumentTypes(desc);
            for (int i = 0; i < types.length; i++) {
                popVblue();
                if (types[i].getSize() == 2) {
                    popVblue();
                }
            }

            Type returnType = Type.getReturnType(desc);
            if (returnType != Type.VOID_TYPE) {
                pushVblue(OTHER);
                if (returnType.getSize() == 2) {
                    pushVblue(OTHER);
                }
            }
        }
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        mv.visitJumpInsn(opcode, lbbel);
        if (constructor) {
            switch (opcode) {
            cbse IFEQ:
            cbse IFNE:
            cbse IFLT:
            cbse IFGE:
            cbse IFGT:
            cbse IFLE:
            cbse IFNULL:
            cbse IFNONNULL:
                popVblue();
                brebk;
            cbse IF_ICMPEQ:
            cbse IF_ICMPNE:
            cbse IF_ICMPLT:
            cbse IF_ICMPGE:
            cbse IF_ICMPGT:
            cbse IF_ICMPLE:
            cbse IF_ACMPEQ:
            cbse IF_ACMPNE:
                popVblue();
                popVblue();
                brebk;
            cbse JSR:
                pushVblue(OTHER);
                brebk;
            }
            bddBrbnch(lbbel);
        }
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        mv.visitLookupSwitchInsn(dflt, keys, lbbels);
        if (constructor) {
            popVblue();
            bddBrbnches(dflt, lbbels);
        }
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        mv.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
        if (constructor) {
            popVblue();
            bddBrbnches(dflt, lbbels);
        }
    }

    @Override
    public void visitTryCbtchBlock(Lbbel stbrt, Lbbel end, Lbbel hbndler,
            String type) {
        super.visitTryCbtchBlock(stbrt, end, hbndler, type);
        if (constructor && !brbnches.contbinsKey(hbndler)) {
            List<Object> stbckFrbme = new ArrbyList<Object>();
            stbckFrbme.bdd(OTHER);
            brbnches.put(hbndler, stbckFrbme);
        }
    }

    privbte void bddBrbnches(finbl Lbbel dflt, finbl Lbbel[] lbbels) {
        bddBrbnch(dflt);
        for (int i = 0; i < lbbels.length; i++) {
            bddBrbnch(lbbels[i]);
        }
    }

    privbte void bddBrbnch(finbl Lbbel lbbel) {
        if (brbnches.contbinsKey(lbbel)) {
            return;
        }
        brbnches.put(lbbel, new ArrbyList<Object>(stbckFrbme));
    }

    privbte Object popVblue() {
        return stbckFrbme.remove(stbckFrbme.size() - 1);
    }

    privbte Object peekVblue() {
        return stbckFrbme.get(stbckFrbme.size() - 1);
    }

    privbte void pushVblue(finbl Object o) {
        stbckFrbme.bdd(o);
    }

    /**
     * Cblled bt the beginning of the method or bfter super clbss clbss cbll in
     * the constructor. <br>
     * <br>
     *
     * <i>Custom code cbn use or chbnge bll the locbl vbribbles, but should not
     * chbnge stbte of the stbck.</i>
     */
    protected void onMethodEnter() {
    }

    /**
     * Cblled before explicit exit from the method using either return or throw.
     * Top element on the stbck contbins the return vblue or exception instbnce.
     * For exbmple:
     *
     * <pre>
     *   public void onMethodExit(int opcode) {
     *     if(opcode==RETURN) {
     *         visitInsn(ACONST_NULL);
     *     } else if(opcode==ARETURN || opcode==ATHROW) {
     *         dup();
     *     } else {
     *         if(opcode==LRETURN || opcode==DRETURN) {
     *             dup2();
     *         } else {
     *             dup();
     *         }
     *         box(Type.getReturnType(this.methodDesc));
     *     }
     *     visitIntInsn(SIPUSH, opcode);
     *     visitMethodInsn(INVOKESTATIC, owner, "onExit", "(Ljbvb/lbng/Object;I)V");
     *   }
     *
     *   // bn bctubl cbll bbck method
     *   public stbtic void onExit(Object pbrbm, int opcode) {
     *     ...
     * </pre>
     *
     * <br>
     * <br>
     *
     * <i>Custom code cbn use or chbnge bll the locbl vbribbles, but should not
     * chbnge stbte of the stbck.</i>
     *
     * @pbrbm opcode
     *            one of the RETURN, IRETURN, FRETURN, ARETURN, LRETURN, DRETURN
     *            or ATHROW
     *
     */
    protected void onMethodExit(int opcode) {
    }

    // TODO onException, onMethodCbll
}
