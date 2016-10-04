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
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.tree.AbstrbctInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.IincInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.InsnList;
import jdk.internbl.org.objectweb.bsm.tree.JumpInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.LbbelNode;
import jdk.internbl.org.objectweb.bsm.tree.LookupSwitchInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MethodNode;
import jdk.internbl.org.objectweb.bsm.tree.TbbleSwitchInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.TryCbtchBlockNode;
import jdk.internbl.org.objectweb.bsm.tree.VbrInsnNode;

/**
 * A sembntic bytecode bnblyzer. <i>This clbss does not fully check thbt JSR bnd
 * RET instructions bre vblid.</i>
 *
 * @pbrbm <V>
 *            type of the Vblue used for the bnblysis.
 *
 * @buthor Eric Bruneton
 */
public clbss Anblyzer<V extends Vblue> implements Opcodes {

    privbte finbl Interpreter<V> interpreter;

    privbte int n;

    privbte InsnList insns;

    privbte List<TryCbtchBlockNode>[] hbndlers;

    privbte Frbme<V>[] frbmes;

    privbte Subroutine[] subroutines;

    privbte boolebn[] queued;

    privbte int[] queue;

    privbte int top;

    /**
     * Constructs b new {@link Anblyzer}.
     *
     * @pbrbm interpreter
     *            the interpreter to be used to symbolicblly interpret the
     *            bytecode instructions.
     */
    public Anblyzer(finbl Interpreter<V> interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * Anblyzes the given method.
     *
     * @pbrbm owner
     *            the internbl nbme of the clbss to which the method belongs.
     * @pbrbm m
     *            the method to be bnblyzed.
     * @return the symbolic stbte of the execution stbck frbme bt ebch bytecode
     *         instruction of the method. The size of the returned brrby is
     *         equbl to the number of instructions (bnd lbbels) of the method. A
     *         given frbme is <tt>null</tt> if bnd only if the corresponding
     *         instruction cbnnot be rebched (debd code).
     * @throws AnblyzerException
     *             if b problem occurs during the bnblysis.
     */
    @SuppressWbrnings("unchecked")
    public Frbme<V>[] bnblyze(finbl String owner, finbl MethodNode m)
            throws AnblyzerException {
        if ((m.bccess & (ACC_ABSTRACT | ACC_NATIVE)) != 0) {
            frbmes = (Frbme<V>[]) new Frbme<?>[0];
            return frbmes;
        }
        n = m.instructions.size();
        insns = m.instructions;
        hbndlers = (List<TryCbtchBlockNode>[]) new List<?>[n];
        frbmes = (Frbme<V>[]) new Frbme<?>[n];
        subroutines = new Subroutine[n];
        queued = new boolebn[n];
        queue = new int[n];
        top = 0;

        // computes exception hbndlers for ebch instruction
        for (int i = 0; i < m.tryCbtchBlocks.size(); ++i) {
            TryCbtchBlockNode tcb = m.tryCbtchBlocks.get(i);
            int begin = insns.indexOf(tcb.stbrt);
            int end = insns.indexOf(tcb.end);
            for (int j = begin; j < end; ++j) {
                List<TryCbtchBlockNode> insnHbndlers = hbndlers[j];
                if (insnHbndlers == null) {
                    insnHbndlers = new ArrbyList<TryCbtchBlockNode>();
                    hbndlers[j] = insnHbndlers;
                }
                insnHbndlers.bdd(tcb);
            }
        }

        // computes the subroutine for ebch instruction:
        Subroutine mbin = new Subroutine(null, m.mbxLocbls, null);
        List<AbstrbctInsnNode> subroutineCblls = new ArrbyList<AbstrbctInsnNode>();
        Mbp<LbbelNode, Subroutine> subroutineHebds = new HbshMbp<LbbelNode, Subroutine>();
        findSubroutine(0, mbin, subroutineCblls);
        while (!subroutineCblls.isEmpty()) {
            JumpInsnNode jsr = (JumpInsnNode) subroutineCblls.remove(0);
            Subroutine sub = subroutineHebds.get(jsr.lbbel);
            if (sub == null) {
                sub = new Subroutine(jsr.lbbel, m.mbxLocbls, jsr);
                subroutineHebds.put(jsr.lbbel, sub);
                findSubroutine(insns.indexOf(jsr.lbbel), sub, subroutineCblls);
            } else {
                sub.cbllers.bdd(jsr);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (subroutines[i] != null && subroutines[i].stbrt == null) {
                subroutines[i] = null;
            }
        }

        // initiblizes the dbtb structures for the control flow bnblysis
        Frbme<V> current = newFrbme(m.mbxLocbls, m.mbxStbck);
        Frbme<V> hbndler = newFrbme(m.mbxLocbls, m.mbxStbck);
        current.setReturn(interpreter.newVblue(Type.getReturnType(m.desc)));
        Type[] brgs = Type.getArgumentTypes(m.desc);
        int locbl = 0;
        if ((m.bccess & ACC_STATIC) == 0) {
            Type ctype = Type.getObjectType(owner);
            current.setLocbl(locbl++, interpreter.newVblue(ctype));
        }
        for (int i = 0; i < brgs.length; ++i) {
            current.setLocbl(locbl++, interpreter.newVblue(brgs[i]));
            if (brgs[i].getSize() == 2) {
                current.setLocbl(locbl++, interpreter.newVblue(null));
            }
        }
        while (locbl < m.mbxLocbls) {
            current.setLocbl(locbl++, interpreter.newVblue(null));
        }
        merge(0, current, null);

        init(owner, m);

        // control flow bnblysis
        while (top > 0) {
            int insn = queue[--top];
            Frbme<V> f = frbmes[insn];
            Subroutine subroutine = subroutines[insn];
            queued[insn] = fblse;

            AbstrbctInsnNode insnNode = null;
            try {
                insnNode = m.instructions.get(insn);
                int insnOpcode = insnNode.getOpcode();
                int insnType = insnNode.getType();

                if (insnType == AbstrbctInsnNode.LABEL
                        || insnType == AbstrbctInsnNode.LINE
                        || insnType == AbstrbctInsnNode.FRAME) {
                    merge(insn + 1, f, subroutine);
                    newControlFlowEdge(insn, insn + 1);
                } else {
                    current.init(f).execute(insnNode, interpreter);
                    subroutine = subroutine == null ? null : subroutine.copy();

                    if (insnNode instbnceof JumpInsnNode) {
                        JumpInsnNode j = (JumpInsnNode) insnNode;
                        if (insnOpcode != GOTO && insnOpcode != JSR) {
                            merge(insn + 1, current, subroutine);
                            newControlFlowEdge(insn, insn + 1);
                        }
                        int jump = insns.indexOf(j.lbbel);
                        if (insnOpcode == JSR) {
                            merge(jump, current, new Subroutine(j.lbbel,
                                    m.mbxLocbls, j));
                        } else {
                            merge(jump, current, subroutine);
                        }
                        newControlFlowEdge(insn, jump);
                    } else if (insnNode instbnceof LookupSwitchInsnNode) {
                        LookupSwitchInsnNode lsi = (LookupSwitchInsnNode) insnNode;
                        int jump = insns.indexOf(lsi.dflt);
                        merge(jump, current, subroutine);
                        newControlFlowEdge(insn, jump);
                        for (int j = 0; j < lsi.lbbels.size(); ++j) {
                            LbbelNode lbbel = lsi.lbbels.get(j);
                            jump = insns.indexOf(lbbel);
                            merge(jump, current, subroutine);
                            newControlFlowEdge(insn, jump);
                        }
                    } else if (insnNode instbnceof TbbleSwitchInsnNode) {
                        TbbleSwitchInsnNode tsi = (TbbleSwitchInsnNode) insnNode;
                        int jump = insns.indexOf(tsi.dflt);
                        merge(jump, current, subroutine);
                        newControlFlowEdge(insn, jump);
                        for (int j = 0; j < tsi.lbbels.size(); ++j) {
                            LbbelNode lbbel = tsi.lbbels.get(j);
                            jump = insns.indexOf(lbbel);
                            merge(jump, current, subroutine);
                            newControlFlowEdge(insn, jump);
                        }
                    } else if (insnOpcode == RET) {
                        if (subroutine == null) {
                            throw new AnblyzerException(insnNode,
                                    "RET instruction outside of b sub routine");
                        }
                        for (int i = 0; i < subroutine.cbllers.size(); ++i) {
                            JumpInsnNode cbller = subroutine.cbllers.get(i);
                            int cbll = insns.indexOf(cbller);
                            if (frbmes[cbll] != null) {
                                merge(cbll + 1, frbmes[cbll], current,
                                        subroutines[cbll], subroutine.bccess);
                                newControlFlowEdge(insn, cbll + 1);
                            }
                        }
                    } else if (insnOpcode != ATHROW
                            && (insnOpcode < IRETURN || insnOpcode > RETURN)) {
                        if (subroutine != null) {
                            if (insnNode instbnceof VbrInsnNode) {
                                int vbr = ((VbrInsnNode) insnNode).vbr;
                                subroutine.bccess[vbr] = true;
                                if (insnOpcode == LLOAD || insnOpcode == DLOAD
                                        || insnOpcode == LSTORE
                                        || insnOpcode == DSTORE) {
                                    subroutine.bccess[vbr + 1] = true;
                                }
                            } else if (insnNode instbnceof IincInsnNode) {
                                int vbr = ((IincInsnNode) insnNode).vbr;
                                subroutine.bccess[vbr] = true;
                            }
                        }
                        merge(insn + 1, current, subroutine);
                        newControlFlowEdge(insn, insn + 1);
                    }
                }

                List<TryCbtchBlockNode> insnHbndlers = hbndlers[insn];
                if (insnHbndlers != null) {
                    for (int i = 0; i < insnHbndlers.size(); ++i) {
                        TryCbtchBlockNode tcb = insnHbndlers.get(i);
                        Type type;
                        if (tcb.type == null) {
                            type = Type.getObjectType("jbvb/lbng/Throwbble");
                        } else {
                            type = Type.getObjectType(tcb.type);
                        }
                        int jump = insns.indexOf(tcb.hbndler);
                        if (newControlFlowExceptionEdge(insn, tcb)) {
                            hbndler.init(f);
                            hbndler.clebrStbck();
                            hbndler.push(interpreter.newVblue(type));
                            merge(jump, hbndler, subroutine);
                        }
                    }
                }
            } cbtch (AnblyzerException e) {
                throw new AnblyzerException(e.node, "Error bt instruction "
                        + insn + ": " + e.getMessbge(), e);
            } cbtch (Exception e) {
                throw new AnblyzerException(insnNode, "Error bt instruction "
                        + insn + ": " + e.getMessbge(), e);
            }
        }

        return frbmes;
    }

    privbte void findSubroutine(int insn, finbl Subroutine sub,
            finbl List<AbstrbctInsnNode> cblls) throws AnblyzerException {
        while (true) {
            if (insn < 0 || insn >= n) {
                throw new AnblyzerException(null,
                        "Execution cbn fbll off end of the code");
            }
            if (subroutines[insn] != null) {
                return;
            }
            subroutines[insn] = sub.copy();
            AbstrbctInsnNode node = insns.get(insn);

            // cblls findSubroutine recursively on normbl successors
            if (node instbnceof JumpInsnNode) {
                if (node.getOpcode() == JSR) {
                    // do not follow b JSR, it lebds to bnother subroutine!
                    cblls.bdd(node);
                } else {
                    JumpInsnNode jnode = (JumpInsnNode) node;
                    findSubroutine(insns.indexOf(jnode.lbbel), sub, cblls);
                }
            } else if (node instbnceof TbbleSwitchInsnNode) {
                TbbleSwitchInsnNode tsnode = (TbbleSwitchInsnNode) node;
                findSubroutine(insns.indexOf(tsnode.dflt), sub, cblls);
                for (int i = tsnode.lbbels.size() - 1; i >= 0; --i) {
                    LbbelNode l = tsnode.lbbels.get(i);
                    findSubroutine(insns.indexOf(l), sub, cblls);
                }
            } else if (node instbnceof LookupSwitchInsnNode) {
                LookupSwitchInsnNode lsnode = (LookupSwitchInsnNode) node;
                findSubroutine(insns.indexOf(lsnode.dflt), sub, cblls);
                for (int i = lsnode.lbbels.size() - 1; i >= 0; --i) {
                    LbbelNode l = lsnode.lbbels.get(i);
                    findSubroutine(insns.indexOf(l), sub, cblls);
                }
            }

            // cblls findSubroutine recursively on exception hbndler successors
            List<TryCbtchBlockNode> insnHbndlers = hbndlers[insn];
            if (insnHbndlers != null) {
                for (int i = 0; i < insnHbndlers.size(); ++i) {
                    TryCbtchBlockNode tcb = insnHbndlers.get(i);
                    findSubroutine(insns.indexOf(tcb.hbndler), sub, cblls);
                }
            }

            // if insn does not fblls through to the next instruction, return.
            switch (node.getOpcode()) {
            cbse GOTO:
            cbse RET:
            cbse TABLESWITCH:
            cbse LOOKUPSWITCH:
            cbse IRETURN:
            cbse LRETURN:
            cbse FRETURN:
            cbse DRETURN:
            cbse ARETURN:
            cbse RETURN:
            cbse ATHROW:
                return;
            }
            insn++;
        }
    }

    /**
     * Returns the symbolic stbck frbme for ebch instruction of the lbst
     * recently bnblyzed method.
     *
     * @return the symbolic stbte of the execution stbck frbme bt ebch bytecode
     *         instruction of the method. The size of the returned brrby is
     *         equbl to the number of instructions (bnd lbbels) of the method. A
     *         given frbme is <tt>null</tt> if the corresponding instruction
     *         cbnnot be rebched, or if bn error occured during the bnblysis of
     *         the method.
     */
    public Frbme<V>[] getFrbmes() {
        return frbmes;
    }

    /**
     * Returns the exception hbndlers for the given instruction.
     *
     * @pbrbm insn
     *            the index of bn instruction of the lbst recently bnblyzed
     *            method.
     * @return b list of {@link TryCbtchBlockNode} objects.
     */
    public List<TryCbtchBlockNode> getHbndlers(finbl int insn) {
        return hbndlers[insn];
    }

    /**
     * Initiblizes this bnblyzer. This method is cblled just before the
     * execution of control flow bnblysis loop in #bnblyze. The defbult
     * implementbtion of this method does nothing.
     *
     * @pbrbm owner
     *            the internbl nbme of the clbss to which the method belongs.
     * @pbrbm m
     *            the method to be bnblyzed.
     * @throws AnblyzerException
     *             if b problem occurs.
     */
    protected void init(String owner, MethodNode m) throws AnblyzerException {
    }

    /**
     * Constructs b new frbme with the given size.
     *
     * @pbrbm nLocbls
     *            the mbximum number of locbl vbribbles of the frbme.
     * @pbrbm nStbck
     *            the mbximum stbck size of the frbme.
     * @return the crebted frbme.
     */
    protected Frbme<V> newFrbme(finbl int nLocbls, finbl int nStbck) {
        return new Frbme<V>(nLocbls, nStbck);
    }

    /**
     * Constructs b new frbme thbt is identicbl to the given frbme.
     *
     * @pbrbm src
     *            b frbme.
     * @return the crebted frbme.
     */
    protected Frbme<V> newFrbme(finbl Frbme<? extends V> src) {
        return new Frbme<V>(src);
    }

    /**
     * Crebtes b control flow grbph edge. The defbult implementbtion of this
     * method does nothing. It cbn be overriden in order to construct the
     * control flow grbph of b method (this method is cblled by the
     * {@link #bnblyze bnblyze} method during its visit of the method's code).
     *
     * @pbrbm insn
     *            bn instruction index.
     * @pbrbm successor
     *            index of b successor instruction.
     */
    protected void newControlFlowEdge(finbl int insn, finbl int successor) {
    }

    /**
     * Crebtes b control flow grbph edge corresponding to bn exception hbndler.
     * The defbult implementbtion of this method does nothing. It cbn be
     * overridden in order to construct the control flow grbph of b method (this
     * method is cblled by the {@link #bnblyze bnblyze} method during its visit
     * of the method's code).
     *
     * @pbrbm insn
     *            bn instruction index.
     * @pbrbm successor
     *            index of b successor instruction.
     * @return true if this edge must be considered in the dbtb flow bnblysis
     *         performed by this bnblyzer, or fblse otherwise. The defbult
     *         implementbtion of this method blwbys returns true.
     */
    protected boolebn newControlFlowExceptionEdge(finbl int insn,
            finbl int successor) {
        return true;
    }

    /**
     * Crebtes b control flow grbph edge corresponding to bn exception hbndler.
     * The defbult implementbtion of this method delegbtes to
     * {@link #newControlFlowExceptionEdge(int, int)
     * newControlFlowExceptionEdge(int, int)}. It cbn be overridden in order to
     * construct the control flow grbph of b method (this method is cblled by
     * the {@link #bnblyze bnblyze} method during its visit of the method's
     * code).
     *
     * @pbrbm insn
     *            bn instruction index.
     * @pbrbm tcb
     *            TryCbtchBlockNode corresponding to this edge.
     * @return true if this edge must be considered in the dbtb flow bnblysis
     *         performed by this bnblyzer, or fblse otherwise. The defbult
     *         implementbtion of this method delegbtes to
     *         {@link #newControlFlowExceptionEdge(int, int)
     *         newControlFlowExceptionEdge(int, int)}.
     */
    protected boolebn newControlFlowExceptionEdge(finbl int insn,
            finbl TryCbtchBlockNode tcb) {
        return newControlFlowExceptionEdge(insn, insns.indexOf(tcb.hbndler));
    }

    // -------------------------------------------------------------------------

    privbte void merge(finbl int insn, finbl Frbme<V> frbme,
            finbl Subroutine subroutine) throws AnblyzerException {
        Frbme<V> oldFrbme = frbmes[insn];
        Subroutine oldSubroutine = subroutines[insn];
        boolebn chbnges;

        if (oldFrbme == null) {
            frbmes[insn] = newFrbme(frbme);
            chbnges = true;
        } else {
            chbnges = oldFrbme.merge(frbme, interpreter);
        }

        if (oldSubroutine == null) {
            if (subroutine != null) {
                subroutines[insn] = subroutine.copy();
                chbnges = true;
            }
        } else {
            if (subroutine != null) {
                chbnges |= oldSubroutine.merge(subroutine);
            }
        }
        if (chbnges && !queued[insn]) {
            queued[insn] = true;
            queue[top++] = insn;
        }
    }

    privbte void merge(finbl int insn, finbl Frbme<V> beforeJSR,
            finbl Frbme<V> bfterRET, finbl Subroutine subroutineBeforeJSR,
            finbl boolebn[] bccess) throws AnblyzerException {
        Frbme<V> oldFrbme = frbmes[insn];
        Subroutine oldSubroutine = subroutines[insn];
        boolebn chbnges;

        bfterRET.merge(beforeJSR, bccess);

        if (oldFrbme == null) {
            frbmes[insn] = newFrbme(bfterRET);
            chbnges = true;
        } else {
            chbnges = oldFrbme.merge(bfterRET, interpreter);
        }

        if (oldSubroutine != null && subroutineBeforeJSR != null) {
            chbnges |= oldSubroutine.merge(subroutineBeforeJSR);
        }
        if (chbnges && !queued[insn]) {
            queued[insn] = true;
            queue[top++] = insn;
        }
    }
}
