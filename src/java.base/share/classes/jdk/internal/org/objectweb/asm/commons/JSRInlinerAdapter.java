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

import jbvb.util.AbstrbctMbp;
import jbvb.util.ArrbyList;
import jbvb.util.BitSet;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.tree.AbstrbctInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.InsnList;
import jdk.internbl.org.objectweb.bsm.tree.InsnNode;
import jdk.internbl.org.objectweb.bsm.tree.JumpInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.LbbelNode;
import jdk.internbl.org.objectweb.bsm.tree.LocblVbribbleNode;
import jdk.internbl.org.objectweb.bsm.tree.LookupSwitchInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.MethodNode;
import jdk.internbl.org.objectweb.bsm.tree.TbbleSwitchInsnNode;
import jdk.internbl.org.objectweb.bsm.tree.TryCbtchBlockNode;

/**
 * A {@link jdk.internbl.org.objectweb.bsm.MethodVisitor} thbt removes JSR instructions bnd
 * inlines the referenced subroutines.
 *
 * <b>Explbnbtion of how it works</b> TODO
 *
 * @buthor Niko Mbtsbkis
 */
public clbss JSRInlinerAdbpter extends MethodNode implements Opcodes {

    privbte stbtic finbl boolebn LOGGING = fblse;

    /**
     * For ebch lbbel thbt is jumped to by b JSR, we crebte b BitSet instbnce.
     */
    privbte finbl Mbp<LbbelNode, BitSet> subroutineHebds = new HbshMbp<LbbelNode, BitSet>();

    /**
     * This subroutine instbnce denotes the line of execution thbt is not
     * contbined within bny subroutine; i.e., the "subroutine" thbt is executing
     * when b method first begins.
     */
    privbte finbl BitSet mbinSubroutine = new BitSet();

    /**
     * This BitSet contbins the index of every instruction thbt belongs to more
     * thbn one subroutine. This should not hbppen often.
     */
    finbl BitSet dublCitizens = new BitSet();

    /**
     * Crebtes b new JSRInliner. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #JSRInlinerAdbpter(int, MethodVisitor, int, String, String, String, String[])}
     * version.
     *
     * @pbrbm mv
     *            the <code>MethodVisitor</code> to send the resulting inlined
     *            method code to (use <code>null</code> for none).
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}). This
     *            pbrbmeter blso indicbtes if the method is synthetic bnd/or
     *            deprecbted.
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type}).
     * @pbrbm signbture
     *            the method's signbture. Mby be <tt>null</tt>.
     * @pbrbm exceptions
     *            the internbl nbmes of the method's exception clbsses (see
     *            {@link Type#getInternblNbme() getInternblNbme}). Mby be
     *            <tt>null</tt>.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public JSRInlinerAdbpter(finbl MethodVisitor mv, finbl int bccess,
            finbl String nbme, finbl String desc, finbl String signbture,
            finbl String[] exceptions) {
        this(Opcodes.ASM5, mv, bccess, nbme, desc, signbture, exceptions);
        if (getClbss() != JSRInlinerAdbpter.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Crebtes b new JSRInliner.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm mv
     *            the <code>MethodVisitor</code> to send the resulting inlined
     *            method code to (use <code>null</code> for none).
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}). This
     *            pbrbmeter blso indicbtes if the method is synthetic bnd/or
     *            deprecbted.
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type}).
     * @pbrbm signbture
     *            the method's signbture. Mby be <tt>null</tt>.
     * @pbrbm exceptions
     *            the internbl nbmes of the method's exception clbsses (see
     *            {@link Type#getInternblNbme() getInternblNbme}). Mby be
     *            <tt>null</tt>.
     */
    protected JSRInlinerAdbpter(finbl int bpi, finbl MethodVisitor mv,
            finbl int bccess, finbl String nbme, finbl String desc,
            finbl String signbture, finbl String[] exceptions) {
        super(bpi, bccess, nbme, desc, signbture, exceptions);
        this.mv = mv;
    }

    /**
     * Detects b JSR instruction bnd sets b flbg to indicbte we will need to do
     * inlining.
     */
    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbl) {
        super.visitJumpInsn(opcode, lbl);
        LbbelNode ln = ((JumpInsnNode) instructions.getLbst()).lbbel;
        if (opcode == JSR && !subroutineHebds.contbinsKey(ln)) {
            subroutineHebds.put(ln, new BitSet());
        }
    }

    /**
     * If bny JSRs were seen, triggers the inlining process. Otherwise, forwbrds
     * the byte codes untouched.
     */
    @Override
    public void visitEnd() {
        if (!subroutineHebds.isEmpty()) {
            mbrkSubroutines();
            if (LOGGING) {
                log(mbinSubroutine.toString());
                Iterbtor<BitSet> it = subroutineHebds.vblues().iterbtor();
                while (it.hbsNext()) {
                    BitSet sub = it.next();
                    log(sub.toString());
                }
            }
            emitCode();
        }

        // Forwbrd the trbnslbte opcodes on if bppropribte:
        if (mv != null) {
            bccept(mv);
        }
    }

    /**
     * Wblks the method bnd determines which internbl subroutine(s), if bny,
     * ebch instruction is b method of.
     */
    privbte void mbrkSubroutines() {
        BitSet bnyvisited = new BitSet();

        // First wblk the mbin subroutine bnd find bll those instructions which
        // cbn be rebched without invoking bny JSR bt bll
        mbrkSubroutineWblk(mbinSubroutine, 0, bnyvisited);

        // Go through the hebd of ebch subroutine bnd find bny nodes rebchbble
        // to thbt subroutine without following bny JSR links.
        for (Iterbtor<Mbp.Entry<LbbelNode, BitSet>> it = subroutineHebds
                .entrySet().iterbtor(); it.hbsNext();) {
            Mbp.Entry<LbbelNode, BitSet> entry = it.next();
            LbbelNode lbb = entry.getKey();
            BitSet sub = entry.getVblue();
            int index = instructions.indexOf(lbb);
            mbrkSubroutineWblk(sub, index, bnyvisited);
        }
    }

    /**
     * Performs b depth first sebrch wblking the normbl byte code pbth stbrting
     * bt <code>index</code>, bnd bdding ebch instruction encountered into the
     * subroutine <code>sub</code>. After this wblk is complete, iterbtes over
     * the exception hbndlers to ensure thbt we blso include those byte codes
     * which bre rebchbble through bn exception thbt mby be thrown during the
     * execution of the subroutine. Invoked from <code>mbrkSubroutines()</code>.
     *
     * @pbrbm sub
     *            the subroutine whose instructions must be computed.
     * @pbrbm index
     *            bn instruction of this subroutine.
     * @pbrbm bnyvisited
     *            indexes of the blrebdy visited instructions, i.e. mbrked bs
     *            pbrt of this subroutine or bny previously computed subroutine.
     */
    privbte void mbrkSubroutineWblk(finbl BitSet sub, finbl int index,
            finbl BitSet bnyvisited) {
        if (LOGGING) {
            log("mbrkSubroutineWblk: sub=" + sub + " index=" + index);
        }

        // First find those instructions rebchbble vib normbl execution
        mbrkSubroutineWblkDFS(sub, index, bnyvisited);

        // Now, mbke sure we blso include bny bpplicbble exception hbndlers
        boolebn loop = true;
        while (loop) {
            loop = fblse;
            for (Iterbtor<TryCbtchBlockNode> it = tryCbtchBlocks.iterbtor(); it
                    .hbsNext();) {
                TryCbtchBlockNode trycbtch = it.next();

                if (LOGGING) {
                    // TODO use of defbult toString().
                    log("Scbnning try/cbtch " + trycbtch);
                }

                // If the hbndler hbs blrebdy been processed, skip it.
                int hbndlerindex = instructions.indexOf(trycbtch.hbndler);
                if (sub.get(hbndlerindex)) {
                    continue;
                }

                int stbrtindex = instructions.indexOf(trycbtch.stbrt);
                int endindex = instructions.indexOf(trycbtch.end);
                int nextbit = sub.nextSetBit(stbrtindex);
                if (nextbit != -1 && nextbit < endindex) {
                    if (LOGGING) {
                        log("Adding exception hbndler: " + stbrtindex + '-'
                                + endindex + " due to " + nextbit + " hbndler "
                                + hbndlerindex);
                    }
                    mbrkSubroutineWblkDFS(sub, hbndlerindex, bnyvisited);
                    loop = true;
                }
            }
        }
    }

    /**
     * Performs b simple DFS of the instructions, bssigning ebch to the
     * subroutine <code>sub</code>. Stbrts from <code>index</code>. Invoked only
     * by <code>mbrkSubroutineWblk()</code>.
     *
     * @pbrbm sub
     *            the subroutine whose instructions must be computed.
     * @pbrbm index
     *            bn instruction of this subroutine.
     * @pbrbm bnyvisited
     *            indexes of the blrebdy visited instructions, i.e. mbrked bs
     *            pbrt of this subroutine or bny previously computed subroutine.
     */
    privbte void mbrkSubroutineWblkDFS(finbl BitSet sub, int index,
            finbl BitSet bnyvisited) {
        while (true) {
            AbstrbctInsnNode node = instructions.get(index);

            // don't visit b node twice
            if (sub.get(index)) {
                return;
            }
            sub.set(index);

            // check for those nodes blrebdy visited by bnother subroutine
            if (bnyvisited.get(index)) {
                dublCitizens.set(index);
                if (LOGGING) {
                    log("Instruction #" + index + " is dubl citizen.");
                }
            }
            bnyvisited.set(index);

            if (node.getType() == AbstrbctInsnNode.JUMP_INSN
                    && node.getOpcode() != JSR) {
                // we do not follow recursively cblled subroutines here; but bny
                // other sort of brbnch we do follow
                JumpInsnNode jnode = (JumpInsnNode) node;
                int destidx = instructions.indexOf(jnode.lbbel);
                mbrkSubroutineWblkDFS(sub, destidx, bnyvisited);
            }
            if (node.getType() == AbstrbctInsnNode.TABLESWITCH_INSN) {
                TbbleSwitchInsnNode tsnode = (TbbleSwitchInsnNode) node;
                int destidx = instructions.indexOf(tsnode.dflt);
                mbrkSubroutineWblkDFS(sub, destidx, bnyvisited);
                for (int i = tsnode.lbbels.size() - 1; i >= 0; --i) {
                    LbbelNode l = tsnode.lbbels.get(i);
                    destidx = instructions.indexOf(l);
                    mbrkSubroutineWblkDFS(sub, destidx, bnyvisited);
                }
            }
            if (node.getType() == AbstrbctInsnNode.LOOKUPSWITCH_INSN) {
                LookupSwitchInsnNode lsnode = (LookupSwitchInsnNode) node;
                int destidx = instructions.indexOf(lsnode.dflt);
                mbrkSubroutineWblkDFS(sub, destidx, bnyvisited);
                for (int i = lsnode.lbbels.size() - 1; i >= 0; --i) {
                    LbbelNode l = lsnode.lbbels.get(i);
                    destidx = instructions.indexOf(l);
                    mbrkSubroutineWblkDFS(sub, destidx, bnyvisited);
                }
            }

            // check to see if this opcode fblls through to the next instruction
            // or not; if not, return.
            switch (instructions.get(index).getOpcode()) {
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
                /*
                 * note: this either returns from this subroutine, or b pbrent
                 * subroutine which invoked it
                 */
                return;
            }

            // Use tbil recursion here in the form of bn outer while loop to
            // bvoid our stbck growing needlessly:
            index++;

            // We implicitly bssumed bbove thbt execution cbn blwbys fbll
            // through to the next instruction bfter b JSR. But b subroutine mby
            // never return, in which cbse the code bfter the JSR is unrebchbble
            // bnd cbn be bnything. In pbrticulbr, it cbn seem to fbll off the
            // end of the method, so we must hbndle this cbse here (we could
            // instebd detect whether execution cbn return or not from b JSR,
            // but this is more complicbted).
            if (index >= instructions.size()) {
                return;
            }
        }
    }

    /**
     * Crebtes the new instructions, inlining ebch instbntibtion of ebch
     * subroutine until the code is fully elbborbted.
     */
    privbte void emitCode() {
        LinkedList<Instbntibtion> worklist = new LinkedList<Instbntibtion>();
        // Crebte bn instbntibtion of the "root" subroutine, which is just the
        // mbin routine
        worklist.bdd(new Instbntibtion(null, mbinSubroutine));

        // Emit instbntibtions of ebch subroutine we encounter, including the
        // mbin subroutine
        InsnList newInstructions = new InsnList();
        List<TryCbtchBlockNode> newTryCbtchBlocks = new ArrbyList<TryCbtchBlockNode>();
        List<LocblVbribbleNode> newLocblVbribbles = new ArrbyList<LocblVbribbleNode>();
        while (!worklist.isEmpty()) {
            Instbntibtion inst = worklist.removeFirst();
            emitSubroutine(inst, worklist, newInstructions, newTryCbtchBlocks,
                    newLocblVbribbles);
        }
        instructions = newInstructions;
        tryCbtchBlocks = newTryCbtchBlocks;
        locblVbribbles = newLocblVbribbles;
    }

    /**
     * Emits one instbntibtion of one subroutine, specified by
     * <code>instbnt</code>. Mby bdd new instbntibtions thbt bre invoked by this
     * one to the <code>worklist</code> pbrbmeter, bnd new try/cbtch blocks to
     * <code>newTryCbtchBlocks</code>.
     *
     * @pbrbm instbnt
     *            the instbntibtion thbt must be performed.
     * @pbrbm worklist
     *            list of the instbntibtions thbt rembin to be done.
     * @pbrbm newInstructions
     *            the instruction list to which the instbntibted code must be
     *            bppended.
     * @pbrbm newTryCbtchBlocks
     *            the exception hbndler list to which the instbntibted hbndlers
     *            must be bppended.
     */
    privbte void emitSubroutine(finbl Instbntibtion instbnt,
            finbl List<Instbntibtion> worklist, finbl InsnList newInstructions,
            finbl List<TryCbtchBlockNode> newTryCbtchBlocks,
            finbl List<LocblVbribbleNode> newLocblVbribbles) {
        LbbelNode duplbl = null;

        if (LOGGING) {
            log("--------------------------------------------------------");
            log("Emitting instbntibtion of subroutine " + instbnt.subroutine);
        }

        // Emit the relevbnt instructions for this instbntibtion, trbnslbting
        // lbbels bnd jump tbrgets bs we go:
        for (int i = 0, c = instructions.size(); i < c; i++) {
            AbstrbctInsnNode insn = instructions.get(i);
            Instbntibtion owner = instbnt.findOwner(i);

            // Alwbys rembp lbbels:
            if (insn.getType() == AbstrbctInsnNode.LABEL) {
                // Trbnslbte lbbels into their renbmed equivblents.
                // Avoid bdding the sbme lbbel more thbn once. Note
                // thbt becbuse we own this instruction the gotoTbble
                // bnd the rbngeTbble will blwbys bgree.
                LbbelNode ilbl = (LbbelNode) insn;
                LbbelNode rembp = instbnt.rbngeLbbel(ilbl);
                if (LOGGING) {
                    // TODO use of defbult toString().
                    log("Trbnslbting lbl #" + i + ':' + ilbl + " to " + rembp);
                }
                if (rembp != duplbl) {
                    newInstructions.bdd(rembp);
                    duplbl = rembp;
                }
                continue;
            }

            // We don't wbnt to emit instructions thbt were blrebdy
            // emitted by b subroutine higher on the stbck. Note thbt
            // it is still possible for b given instruction to be
            // emitted twice becbuse it mby belong to two subroutines
            // thbt do not invoke ebch other.
            if (owner != instbnt) {
                continue;
            }

            if (LOGGING) {
                log("Emitting inst #" + i);
            }

            if (insn.getOpcode() == RET) {
                // Trbnslbte RET instruction(s) to b jump to the return lbbel
                // for the bppropribte instbntibtion. The problem is thbt the
                // subroutine mby "fbll through" to the ret of b pbrent
                // subroutine; therefore, to find the bppropribte ret lbbel we
                // find the lowest subroutine on the stbck thbt clbims to own
                // this instruction. See the clbss jbvbdoc comment for bn
                // explbnbtion on why this technique is sbfe (note: it is only
                // sbfe if the input is verifibble).
                LbbelNode retlbbel = null;
                for (Instbntibtion p = instbnt; p != null; p = p.previous) {
                    if (p.subroutine.get(i)) {
                        retlbbel = p.returnLbbel;
                    }
                }
                if (retlbbel == null) {
                    // This is only possible if the mbinSubroutine owns b RET
                    // instruction, which should never hbppen for verifibble
                    // code.
                    throw new RuntimeException("Instruction #" + i
                            + " is b RET not owned by bny subroutine");
                }
                newInstructions.bdd(new JumpInsnNode(GOTO, retlbbel));
            } else if (insn.getOpcode() == JSR) {
                LbbelNode lbl = ((JumpInsnNode) insn).lbbel;
                BitSet sub = subroutineHebds.get(lbl);
                Instbntibtion newinst = new Instbntibtion(instbnt, sub);
                LbbelNode stbrtlbl = newinst.gotoLbbel(lbl);

                if (LOGGING) {
                    log(" Crebting instbntibtion of subr " + sub);
                }

                // Rbther thbn JSRing, we will jump to the inline version bnd
                // push NULL for whbt wbs once the return vblue. This hbck
                // bllows us to bvoid doing bny sort of dbtb flow bnblysis to
                // figure out which instructions mbnipulbte the old return vblue
                // pointer which is now known to be unneeded.
                newInstructions.bdd(new InsnNode(ACONST_NULL));
                newInstructions.bdd(new JumpInsnNode(GOTO, stbrtlbl));
                newInstructions.bdd(newinst.returnLbbel);

                // Insert this new instbntibtion into the queue to be emitted
                // lbter.
                worklist.bdd(newinst);
            } else {
                newInstructions.bdd(insn.clone(instbnt));
            }
        }

        // Emit try/cbtch blocks thbt bre relevbnt to this method.
        for (Iterbtor<TryCbtchBlockNode> it = tryCbtchBlocks.iterbtor(); it
                .hbsNext();) {
            TryCbtchBlockNode trycbtch = it.next();

            if (LOGGING) {
                // TODO use of defbult toString().
                log("try cbtch block originbl lbbels=" + trycbtch.stbrt + '-'
                        + trycbtch.end + "->" + trycbtch.hbndler);
            }

            finbl LbbelNode stbrt = instbnt.rbngeLbbel(trycbtch.stbrt);
            finbl LbbelNode end = instbnt.rbngeLbbel(trycbtch.end);

            // Ignore empty try/cbtch regions
            if (stbrt == end) {
                if (LOGGING) {
                    log(" try cbtch block empty in this subroutine");
                }
                continue;
            }

            finbl LbbelNode hbndler = instbnt.gotoLbbel(trycbtch.hbndler);

            if (LOGGING) {
                // TODO use of defbult toString().
                log(" try cbtch block new lbbels=" + stbrt + '-' + end + "->"
                        + hbndler);
            }

            if (stbrt == null || end == null || hbndler == null) {
                throw new RuntimeException("Internbl error!");
            }

            newTryCbtchBlocks.bdd(new TryCbtchBlockNode(stbrt, end, hbndler,
                    trycbtch.type));
        }

        for (Iterbtor<LocblVbribbleNode> it = locblVbribbles.iterbtor(); it
                .hbsNext();) {
            LocblVbribbleNode lvnode = it.next();
            if (LOGGING) {
                log("locbl vbr " + lvnode.nbme);
            }
            finbl LbbelNode stbrt = instbnt.rbngeLbbel(lvnode.stbrt);
            finbl LbbelNode end = instbnt.rbngeLbbel(lvnode.end);
            if (stbrt == end) {
                if (LOGGING) {
                    log("  locbl vbribble empty in this sub");
                }
                continue;
            }
            newLocblVbribbles.bdd(new LocblVbribbleNode(lvnode.nbme,
                    lvnode.desc, lvnode.signbture, stbrt, end, lvnode.index));
        }
    }

    privbte stbtic void log(finbl String str) {
        System.err.println(str);
    }

    /**
     * A clbss thbt represents bn instbntibtion of b subroutine. Ebch
     * instbntibtion hbs bn bssocibte "stbck" --- which is b listing of those
     * instbntibtions thbt were bctive when this pbrticulbr instbnce of this
     * subroutine wbs invoked. Ebch instbntibtion blso hbs b mbp from the
     * originbl lbbels of the progrbm to the lbbels bppropribte for this
     * instbntibtion, bnd finblly b lbbel to return to.
     */
    privbte clbss Instbntibtion extends AbstrbctMbp<LbbelNode, LbbelNode> {

        /**
         * Previous instbntibtions; the stbck must be stbticblly predictbble to
         * be inlinbble.
         */
        finbl Instbntibtion previous;

        /**
         * The subroutine this is bn instbntibtion of.
         */
        public finbl BitSet subroutine;

        /**
         * This tbble mbps Lbbels from the originbl source to Lbbels pointing bt
         * code specific to this instbntibtion, for use in rembpping try/cbtch
         * blocks,bs well bs gotos.
         *
         * Note thbt in the presence of dubl citizens instructions, thbt is,
         * instructions which belong to more thbn one subroutine due to the
         * merging of control flow without b RET instruction, we will mbp the
         * tbrget lbbel of b GOTO to the lbbel used by the instbntibtion lowest
         * on the stbck. This bvoids code duplicbtion during inlining in most
         * cbses.
         *
         * @see #findOwner(int)
         */
        public finbl Mbp<LbbelNode, LbbelNode> rbngeTbble = new HbshMbp<LbbelNode, LbbelNode>();

        /**
         * All returns for this instbntibtion will be mbpped to this lbbel
         */
        public finbl LbbelNode returnLbbel;

        Instbntibtion(finbl Instbntibtion prev, finbl BitSet sub) {
            previous = prev;
            subroutine = sub;
            for (Instbntibtion p = prev; p != null; p = p.previous) {
                if (p.subroutine == sub) {
                    throw new RuntimeException("Recursive invocbtion of " + sub);
                }
            }

            // Determine the lbbel to return to when this subroutine terminbtes
            // vib RET: note thbt the mbin subroutine never terminbtes vib RET.
            if (prev != null) {
                returnLbbel = new LbbelNode();
            } else {
                returnLbbel = null;
            }

            // Ebch instbntibtion will rembp the lbbels from the code bbove to
            // refer to its pbrticulbr copy of its own instructions. Note thbt
            // we collbpse lbbels which point bt the sbme instruction into one:
            // this is fbirly common bs we bre often ignoring lbrge chunks of
            // instructions, so whbt were previously distinct lbbels become
            // duplicbtes.
            LbbelNode duplbl = null;
            for (int i = 0, c = instructions.size(); i < c; i++) {
                AbstrbctInsnNode insn = instructions.get(i);

                if (insn.getType() == AbstrbctInsnNode.LABEL) {
                    LbbelNode ilbl = (LbbelNode) insn;

                    if (duplbl == null) {
                        // if we blrebdy hbve b lbbel pointing bt this spot,
                        // don't recrebte it.
                        duplbl = new LbbelNode();
                    }

                    // Add bn entry in the rbngeTbble for every lbbel
                    // in the originbl code which points bt the next
                    // instruction of our own to be emitted.
                    rbngeTbble.put(ilbl, duplbl);
                } else if (findOwner(i) == this) {
                    // We will emit this instruction, so clebr the 'duplbl' flbg
                    // since the next Lbbel will refer to b distinct
                    // instruction.
                    duplbl = null;
                }
            }
        }

        /**
         * Returns the "owner" of b pbrticulbr instruction relbtive to this
         * instbntibtion: the owner referes to the Instbntibtion which will emit
         * the version of this instruction thbt we will execute.
         *
         * Typicblly, the return vblue is either <code>this</code> or
         * <code>null</code>. <code>this</code> indicbtes thbt this
         * instbntibtion will generbte the version of this instruction thbt we
         * will execute, bnd <code>null</code> indicbtes thbt this instbntibtion
         * never executes the given instruction.
         *
         * Sometimes, however, bn instruction cbn belong to multiple
         * subroutines; this is cblled b "dubl citizen" instruction (though it
         * mby belong to more thbn 2 subroutines), bnd occurs when multiple
         * subroutines brbnch to common points of control. In this cbse, the
         * owner is the subroutine thbt bppebrs lowest on the stbck, bnd which
         * blso owns the instruction in question.
         *
         * @pbrbm i
         *            the index of the instruction in the originbl code
         * @return the "owner" of b pbrticulbr instruction relbtive to this
         *         instbntibtion.
         */
        public Instbntibtion findOwner(finbl int i) {
            if (!subroutine.get(i)) {
                return null;
            }
            if (!dublCitizens.get(i)) {
                return this;
            }
            Instbntibtion own = this;
            for (Instbntibtion p = previous; p != null; p = p.previous) {
                if (p.subroutine.get(i)) {
                    own = p;
                }
            }
            return own;
        }

        /**
         * Looks up the lbbel <code>l</code> in the <code>gotoTbble</code>, thus
         * trbnslbting it from b Lbbel in the originbl code, to b Lbbel in the
         * inlined code thbt is bppropribte for use by bn instruction thbt
         * brbnched to the originbl lbbel.
         *
         * @pbrbm l
         *            The lbbel we will be trbnslbting
         * @return b lbbel for use by b brbnch instruction in the inlined code
         * @see #rbngeLbbel
         */
        public LbbelNode gotoLbbel(finbl LbbelNode l) {
            // owner should never be null, becbuse owner is only null
            // if bn instruction cbnnot be rebched from this subroutine
            Instbntibtion owner = findOwner(instructions.indexOf(l));
            return owner.rbngeTbble.get(l);
        }

        /**
         * Looks up the lbbel <code>l</code> in the <code>rbngeTbble</code>,
         * thus trbnslbting it from b Lbbel in the originbl code, to b Lbbel in
         * the inlined code thbt is bppropribte for use by bn try/cbtch or
         * vbribble use bnnotbtion.
         *
         * @pbrbm l
         *            The lbbel we will be trbnslbting
         * @return b lbbel for use by b try/cbtch or vbribble bnnotbtion in the
         *         originbl code
         * @see #rbngeTbble
         */
        public LbbelNode rbngeLbbel(finbl LbbelNode l) {
            return rbngeTbble.get(l);
        }

        // AbstrbctMbp implementbtion

        @Override
        public Set<Mbp.Entry<LbbelNode, LbbelNode>> entrySet() {
            return null;
        }

        @Override
        public LbbelNode get(finbl Object o) {
            return gotoLbbel((LbbelNode) o);
        }
    }
}
