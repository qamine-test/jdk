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
import jbvb.util.Arrbys;
import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor;
import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.ClbssVisitor;
import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A node thbt represents b method.
 *
 * @buthor Eric Bruneton
 */
public clbss MethodNode extends MethodVisitor {

    /**
     * The method's bccess flbgs (see {@link Opcodes}). This field blso
     * indicbtes if the method is synthetic bnd/or deprecbted.
     */
    public int bccess;

    /**
     * The method's nbme.
     */
    public String nbme;

    /**
     * The method's descriptor (see {@link Type}).
     */
    public String desc;

    /**
     * The method's signbture. Mby be <tt>null</tt>.
     */
    public String signbture;

    /**
     * The internbl nbmes of the method's exception clbsses (see
     * {@link Type#getInternblNbme() getInternblNbme}). This list is b list of
     * {@link String} objects.
     */
    public List<String> exceptions;

    /**
     * The method pbrbmeter info (bccess flbgs bnd nbme)
     */
    public List<PbrbmeterNode> pbrbmeters;

    /**
     * The runtime visible bnnotbtions of this method. This list is b list of
     * {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel visible
     */
    public List<AnnotbtionNode> visibleAnnotbtions;

    /**
     * The runtime invisible bnnotbtions of this method. This list is b list of
     * {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel invisible
     */
    public List<AnnotbtionNode> invisibleAnnotbtions;

    /**
     * The runtime visible type bnnotbtions of this method. This list is b list
     * of {@link TypeAnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel visible
     */
    public List<TypeAnnotbtionNode> visibleTypeAnnotbtions;

    /**
     * The runtime invisible type bnnotbtions of this method. This list is b
     * list of {@link TypeAnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel invisible
     */
    public List<TypeAnnotbtionNode> invisibleTypeAnnotbtions;

    /**
     * The non stbndbrd bttributes of this method. This list is b list of
     * {@link Attribute} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.Attribute
     */
    public List<Attribute> bttrs;

    /**
     * The defbult vblue of this bnnotbtion interfbce method. This field must be
     * b {@link Byte}, {@link Boolebn}, {@link Chbrbcter}, {@link Short},
     * {@link Integer}, {@link Long}, {@link Flobt}, {@link Double},
     * {@link String} or {@link Type}, or bn two elements String brrby (for
     * enumerbtion vblues), b {@link AnnotbtionNode}, or b {@link List} of
     * vblues of one of the preceding types. Mby be <tt>null</tt>.
     */
    public Object bnnotbtionDefbult;

    /**
     * The runtime visible pbrbmeter bnnotbtions of this method. These lists bre
     * lists of {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel invisible pbrbmeters
     */
    public List<AnnotbtionNode>[] visiblePbrbmeterAnnotbtions;

    /**
     * The runtime invisible pbrbmeter bnnotbtions of this method. These lists
     * bre lists of {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel visible pbrbmeters
     */
    public List<AnnotbtionNode>[] invisiblePbrbmeterAnnotbtions;

    /**
     * The instructions of this method. This list is b list of
     * {@link AbstrbctInsnNode} objects.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AbstrbctInsnNode
     * @lbbel instructions
     */
    public InsnList instructions;

    /**
     * The try cbtch blocks of this method. This list is b list of
     * {@link TryCbtchBlockNode} objects.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TryCbtchBlockNode
     */
    public List<TryCbtchBlockNode> tryCbtchBlocks;

    /**
     * The mbximum stbck size of this method.
     */
    public int mbxStbck;

    /**
     * The mbximum number of locbl vbribbles of this method.
     */
    public int mbxLocbls;

    /**
     * The locbl vbribbles of this method. This list is b list of
     * {@link LocblVbribbleNode} objects. Mby be <tt>null</tt>
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.LocblVbribbleNode
     */
    public List<LocblVbribbleNode> locblVbribbles;

    /**
     * The visible locbl vbribble bnnotbtions of this method. This list is b
     * list of {@link LocblVbribbleAnnotbtionNode} objects. Mby be <tt>null</tt>
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.LocblVbribbleAnnotbtionNode
     */
    public List<LocblVbribbleAnnotbtionNode> visibleLocblVbribbleAnnotbtions;

    /**
     * The invisible locbl vbribble bnnotbtions of this method. This list is b
     * list of {@link LocblVbribbleAnnotbtionNode} objects. Mby be <tt>null</tt>
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.LocblVbribbleAnnotbtionNode
     */
    public List<LocblVbribbleAnnotbtionNode> invisibleLocblVbribbleAnnotbtions;

    /**
     * If the bccept method hbs been cblled on this object.
     */
    privbte boolebn visited;

    /**
     * Constructs bn uninitiblized {@link MethodNode}. <i>Subclbsses must not
     * use this constructor</i>. Instebd, they must use the
     * {@link #MethodNode(int)} version.
     *
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public MethodNode() {
        this(Opcodes.ASM5);
        if (getClbss() != MethodNode.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs bn uninitiblized {@link MethodNode}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    public MethodNode(finbl int bpi) {
        super(bpi);
        this.instructions = new InsnList();
    }

    /**
     * Constructs b new {@link MethodNode}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #MethodNode(int, int, String, String, String, String[])} version.
     *
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
    public MethodNode(finbl int bccess, finbl String nbme, finbl String desc,
            finbl String signbture, finbl String[] exceptions) {
        this(Opcodes.ASM5, bccess, nbme, desc, signbture, exceptions);
        if (getClbss() != MethodNode.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link MethodNode}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
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
    public MethodNode(finbl int bpi, finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        super(bpi);
        this.bccess = bccess;
        this.nbme = nbme;
        this.desc = desc;
        this.signbture = signbture;
        this.exceptions = new ArrbyList<String>(exceptions == null ? 0
                : exceptions.length);
        boolebn isAbstrbct = (bccess & Opcodes.ACC_ABSTRACT) != 0;
        if (!isAbstrbct) {
            this.locblVbribbles = new ArrbyList<LocblVbribbleNode>(5);
        }
        this.tryCbtchBlocks = new ArrbyList<TryCbtchBlockNode>();
        if (exceptions != null) {
            this.exceptions.bddAll(Arrbys.bsList(exceptions));
        }
        this.instructions = new InsnList();
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the MethodVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

    @Override
    public void visitPbrbmeter(String nbme, int bccess) {
        if (pbrbmeters == null) {
            pbrbmeters = new ArrbyList<PbrbmeterNode>(5);
        }
        pbrbmeters.bdd(new PbrbmeterNode(nbme, bccess));
    }

    @Override
    @SuppressWbrnings("seribl")
    public AnnotbtionVisitor visitAnnotbtionDefbult() {
        return new AnnotbtionNode(new ArrbyList<Object>(0) {
            @Override
            public boolebn bdd(finbl Object o) {
                bnnotbtionDefbult = o;
                return super.bdd(o);
            }
        });
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        AnnotbtionNode bn = new AnnotbtionNode(desc);
        if (visible) {
            if (visibleAnnotbtions == null) {
                visibleAnnotbtions = new ArrbyList<AnnotbtionNode>(1);
            }
            visibleAnnotbtions.bdd(bn);
        } else {
            if (invisibleAnnotbtions == null) {
                invisibleAnnotbtions = new ArrbyList<AnnotbtionNode>(1);
            }
            invisibleAnnotbtions.bdd(bn);
        }
        return bn;
    }

    @Override
    public AnnotbtionVisitor visitTypeAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        TypeAnnotbtionNode bn = new TypeAnnotbtionNode(typeRef, typePbth, desc);
        if (visible) {
            if (visibleTypeAnnotbtions == null) {
                visibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>(1);
            }
            visibleTypeAnnotbtions.bdd(bn);
        } else {
            if (invisibleTypeAnnotbtions == null) {
                invisibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>(1);
            }
            invisibleTypeAnnotbtions.bdd(bn);
        }
        return bn;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public AnnotbtionVisitor visitPbrbmeterAnnotbtion(finbl int pbrbmeter,
            finbl String desc, finbl boolebn visible) {
        AnnotbtionNode bn = new AnnotbtionNode(desc);
        if (visible) {
            if (visiblePbrbmeterAnnotbtions == null) {
                int pbrbms = Type.getArgumentTypes(this.desc).length;
                visiblePbrbmeterAnnotbtions = (List<AnnotbtionNode>[]) new List<?>[pbrbms];
            }
            if (visiblePbrbmeterAnnotbtions[pbrbmeter] == null) {
                visiblePbrbmeterAnnotbtions[pbrbmeter] = new ArrbyList<AnnotbtionNode>(
                        1);
            }
            visiblePbrbmeterAnnotbtions[pbrbmeter].bdd(bn);
        } else {
            if (invisiblePbrbmeterAnnotbtions == null) {
                int pbrbms = Type.getArgumentTypes(this.desc).length;
                invisiblePbrbmeterAnnotbtions = (List<AnnotbtionNode>[]) new List<?>[pbrbms];
            }
            if (invisiblePbrbmeterAnnotbtions[pbrbmeter] == null) {
                invisiblePbrbmeterAnnotbtions[pbrbmeter] = new ArrbyList<AnnotbtionNode>(
                        1);
            }
            invisiblePbrbmeterAnnotbtions[pbrbmeter].bdd(bn);
        }
        return bn;
    }

    @Override
    public void visitAttribute(finbl Attribute bttr) {
        if (bttrs == null) {
            bttrs = new ArrbyList<Attribute>(1);
        }
        bttrs.bdd(bttr);
    }

    @Override
    public void visitCode() {
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        instructions.bdd(new FrbmeNode(type, nLocbl, locbl == null ? null
                : getLbbelNodes(locbl), nStbck, stbck == null ? null
                : getLbbelNodes(stbck)));
    }

    @Override
    public void visitInsn(finbl int opcode) {
        instructions.bdd(new InsnNode(opcode));
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        instructions.bdd(new IntInsnNode(opcode, operbnd));
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        instructions.bdd(new VbrInsnNode(opcode, vbr));
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        instructions.bdd(new TypeInsnNode(opcode, type));
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        instructions.bdd(new FieldInsnNode(opcode, owner, nbme, desc));
    }

    @Deprecbted
    @Override
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc) {
        if (bpi >= Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        instructions.bdd(new MethodInsnNode(opcode, owner, nbme, desc));
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc, boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        instructions.bdd(new MethodInsnNode(opcode, owner, nbme, desc, itf));
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        instructions.bdd(new InvokeDynbmicInsnNode(nbme, desc, bsm, bsmArgs));
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        instructions.bdd(new JumpInsnNode(opcode, getLbbelNode(lbbel)));
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        instructions.bdd(getLbbelNode(lbbel));
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        instructions.bdd(new LdcInsnNode(cst));
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        instructions.bdd(new IincInsnNode(vbr, increment));
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        instructions.bdd(new TbbleSwitchInsnNode(min, mbx, getLbbelNode(dflt),
                getLbbelNodes(lbbels)));
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        instructions.bdd(new LookupSwitchInsnNode(getLbbelNode(dflt), keys,
                getLbbelNodes(lbbels)));
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        instructions.bdd(new MultiANewArrbyInsnNode(desc, dims));
    }

    @Override
    public AnnotbtionVisitor visitInsnAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        // Finds the lbst rebl instruction, i.e. the instruction tbrgeted by
        // this bnnotbtion.
        AbstrbctInsnNode insn = instructions.getLbst();
        while (insn.getOpcode() == -1) {
            insn = insn.getPrevious();
        }
        // Adds the bnnotbtion to this instruction.
        TypeAnnotbtionNode bn = new TypeAnnotbtionNode(typeRef, typePbth, desc);
        if (visible) {
            if (insn.visibleTypeAnnotbtions == null) {
                insn.visibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>(
                        1);
            }
            insn.visibleTypeAnnotbtions.bdd(bn);
        } else {
            if (insn.invisibleTypeAnnotbtions == null) {
                insn.invisibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>(
                        1);
            }
            insn.invisibleTypeAnnotbtions.bdd(bn);
        }
        return bn;
    }

    @Override
    public void visitTryCbtchBlock(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Lbbel hbndler, finbl String type) {
        tryCbtchBlocks.bdd(new TryCbtchBlockNode(getLbbelNode(stbrt),
                getLbbelNode(end), getLbbelNode(hbndler), type));
    }

    @Override
    public AnnotbtionVisitor visitTryCbtchAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        TryCbtchBlockNode tcb = tryCbtchBlocks.get((typeRef & 0x00FFFF00) >> 8);
        TypeAnnotbtionNode bn = new TypeAnnotbtionNode(typeRef, typePbth, desc);
        if (visible) {
            if (tcb.visibleTypeAnnotbtions == null) {
                tcb.visibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>(
                        1);
            }
            tcb.visibleTypeAnnotbtions.bdd(bn);
        } else {
            if (tcb.invisibleTypeAnnotbtions == null) {
                tcb.invisibleTypeAnnotbtions = new ArrbyList<TypeAnnotbtionNode>(
                        1);
            }
            tcb.invisibleTypeAnnotbtions.bdd(bn);
        }
        return bn;
    }

    @Override
    public void visitLocblVbribble(finbl String nbme, finbl String desc,
            finbl String signbture, finbl Lbbel stbrt, finbl Lbbel end,
            finbl int index) {
        locblVbribbles.bdd(new LocblVbribbleNode(nbme, desc, signbture,
                getLbbelNode(stbrt), getLbbelNode(end), index));
    }

    @Override
    public AnnotbtionVisitor visitLocblVbribbleAnnotbtion(int typeRef,
            TypePbth typePbth, Lbbel[] stbrt, Lbbel[] end, int[] index,
            String desc, boolebn visible) {
        LocblVbribbleAnnotbtionNode bn = new LocblVbribbleAnnotbtionNode(
                typeRef, typePbth, getLbbelNodes(stbrt), getLbbelNodes(end),
                index, desc);
        if (visible) {
            if (visibleLocblVbribbleAnnotbtions == null) {
                visibleLocblVbribbleAnnotbtions = new ArrbyList<LocblVbribbleAnnotbtionNode>(
                        1);
            }
            visibleLocblVbribbleAnnotbtions.bdd(bn);
        } else {
            if (invisibleLocblVbribbleAnnotbtions == null) {
                invisibleLocblVbribbleAnnotbtions = new ArrbyList<LocblVbribbleAnnotbtionNode>(
                        1);
            }
            invisibleLocblVbribbleAnnotbtions.bdd(bn);
        }
        return bn;
    }

    @Override
    public void visitLineNumber(finbl int line, finbl Lbbel stbrt) {
        instructions.bdd(new LineNumberNode(line, getLbbelNode(stbrt)));
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        this.mbxStbck = mbxStbck;
        this.mbxLocbls = mbxLocbls;
    }

    @Override
    public void visitEnd() {
    }

    /**
     * Returns the LbbelNode corresponding to the given Lbbel. Crebtes b new
     * LbbelNode if necessbry. The defbult implementbtion of this method uses
     * the {@link Lbbel#info} field to store bssocibtions between lbbels bnd
     * lbbel nodes.
     *
     * @pbrbm l
     *            b Lbbel.
     * @return the LbbelNode corresponding to l.
     */
    protected LbbelNode getLbbelNode(finbl Lbbel l) {
        if (!(l.info instbnceof LbbelNode)) {
            l.info = new LbbelNode();
        }
        return (LbbelNode) l.info;
    }

    privbte LbbelNode[] getLbbelNodes(finbl Lbbel[] l) {
        LbbelNode[] nodes = new LbbelNode[l.length];
        for (int i = 0; i < l.length; ++i) {
            nodes[i] = getLbbelNode(l[i]);
        }
        return nodes;
    }

    privbte Object[] getLbbelNodes(finbl Object[] objs) {
        Object[] nodes = new Object[objs.length];
        for (int i = 0; i < objs.length; ++i) {
            Object o = objs[i];
            if (o instbnceof Lbbel) {
                o = getLbbelNode((Lbbel) o);
            }
            nodes[i] = o;
        }
        return nodes;
    }

    // ------------------------------------------------------------------------
    // Accept method
    // ------------------------------------------------------------------------

    /**
     * Checks thbt this method node is compbtible with the given ASM API
     * version. This methods checks thbt this node, bnd bll its nodes
     * recursively, do not contbin elements thbt were introduced in more recent
     * versions of the ASM API thbn the given version.
     *
     * @pbrbm bpi
     *            bn ASM API version. Must be one of {@link Opcodes#ASM4} or
     *            {@link Opcodes#ASM5}.
     */
    public void check(finbl int bpi) {
        if (bpi == Opcodes.ASM4) {
            if (visibleTypeAnnotbtions != null
                    && visibleTypeAnnotbtions.size() > 0) {
                throw new RuntimeException();
            }
            if (invisibleTypeAnnotbtions != null
                    && invisibleTypeAnnotbtions.size() > 0) {
                throw new RuntimeException();
            }
            int n = tryCbtchBlocks == null ? 0 : tryCbtchBlocks.size();
            for (int i = 0; i < n; ++i) {
                TryCbtchBlockNode tcb = tryCbtchBlocks.get(i);
                if (tcb.visibleTypeAnnotbtions != null
                        && tcb.visibleTypeAnnotbtions.size() > 0) {
                    throw new RuntimeException();
                }
                if (tcb.invisibleTypeAnnotbtions != null
                        && tcb.invisibleTypeAnnotbtions.size() > 0) {
                    throw new RuntimeException();
                }
            }
            for (int i = 0; i < instructions.size(); ++i) {
                AbstrbctInsnNode insn = instructions.get(i);
                if (insn.visibleTypeAnnotbtions != null
                        && insn.visibleTypeAnnotbtions.size() > 0) {
                    throw new RuntimeException();
                }
                if (insn.invisibleTypeAnnotbtions != null
                        && insn.invisibleTypeAnnotbtions.size() > 0) {
                    throw new RuntimeException();
                }
                if (insn instbnceof MethodInsnNode) {
                    boolebn itf = ((MethodInsnNode) insn).itf;
                    if (itf != (insn.opcode == Opcodes.INVOKEINTERFACE)) {
                        throw new RuntimeException();
                    }
                }
            }
            if (visibleLocblVbribbleAnnotbtions != null
                    && visibleLocblVbribbleAnnotbtions.size() > 0) {
                throw new RuntimeException();
            }
            if (invisibleLocblVbribbleAnnotbtions != null
                    && invisibleLocblVbribbleAnnotbtions.size() > 0) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * Mbkes the given clbss visitor visit this method.
     *
     * @pbrbm cv
     *            b clbss visitor.
     */
    public void bccept(finbl ClbssVisitor cv) {
        String[] exceptions = new String[this.exceptions.size()];
        this.exceptions.toArrby(exceptions);
        MethodVisitor mv = cv.visitMethod(bccess, nbme, desc, signbture,
                exceptions);
        if (mv != null) {
            bccept(mv);
        }
    }

    /**
     * Mbkes the given method visitor visit this method.
     *
     * @pbrbm mv
     *            b method visitor.
     */
    public void bccept(finbl MethodVisitor mv) {
        // visits the method pbrbmeters
        int i, j, n;
        n = pbrbmeters == null ? 0 : pbrbmeters.size();
        for (i = 0; i < n; i++) {
            PbrbmeterNode pbrbmeter = pbrbmeters.get(i);
            mv.visitPbrbmeter(pbrbmeter.nbme, pbrbmeter.bccess);
        }
        // visits the method bttributes
        if (bnnotbtionDefbult != null) {
            AnnotbtionVisitor bv = mv.visitAnnotbtionDefbult();
            AnnotbtionNode.bccept(bv, null, bnnotbtionDefbult);
            if (bv != null) {
                bv.visitEnd();
            }
        }
        n = visibleAnnotbtions == null ? 0 : visibleAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            AnnotbtionNode bn = visibleAnnotbtions.get(i);
            bn.bccept(mv.visitAnnotbtion(bn.desc, true));
        }
        n = invisibleAnnotbtions == null ? 0 : invisibleAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            AnnotbtionNode bn = invisibleAnnotbtions.get(i);
            bn.bccept(mv.visitAnnotbtion(bn.desc, fblse));
        }
        n = visibleTypeAnnotbtions == null ? 0 : visibleTypeAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = visibleTypeAnnotbtions.get(i);
            bn.bccept(mv.visitTypeAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    true));
        }
        n = invisibleTypeAnnotbtions == null ? 0 : invisibleTypeAnnotbtions
                .size();
        for (i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = invisibleTypeAnnotbtions.get(i);
            bn.bccept(mv.visitTypeAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    fblse));
        }
        n = visiblePbrbmeterAnnotbtions == null ? 0
                : visiblePbrbmeterAnnotbtions.length;
        for (i = 0; i < n; ++i) {
            List<?> l = visiblePbrbmeterAnnotbtions[i];
            if (l == null) {
                continue;
            }
            for (j = 0; j < l.size(); ++j) {
                AnnotbtionNode bn = (AnnotbtionNode) l.get(j);
                bn.bccept(mv.visitPbrbmeterAnnotbtion(i, bn.desc, true));
            }
        }
        n = invisiblePbrbmeterAnnotbtions == null ? 0
                : invisiblePbrbmeterAnnotbtions.length;
        for (i = 0; i < n; ++i) {
            List<?> l = invisiblePbrbmeterAnnotbtions[i];
            if (l == null) {
                continue;
            }
            for (j = 0; j < l.size(); ++j) {
                AnnotbtionNode bn = (AnnotbtionNode) l.get(j);
                bn.bccept(mv.visitPbrbmeterAnnotbtion(i, bn.desc, fblse));
            }
        }
        if (visited) {
            instructions.resetLbbels();
        }
        n = bttrs == null ? 0 : bttrs.size();
        for (i = 0; i < n; ++i) {
            mv.visitAttribute(bttrs.get(i));
        }
        // visits the method's code
        if (instructions.size() > 0) {
            mv.visitCode();
            // visits try cbtch blocks
            n = tryCbtchBlocks == null ? 0 : tryCbtchBlocks.size();
            for (i = 0; i < n; ++i) {
                tryCbtchBlocks.get(i).updbteIndex(i);
                tryCbtchBlocks.get(i).bccept(mv);
            }
            // visits instructions
            instructions.bccept(mv);
            // visits locbl vbribbles
            n = locblVbribbles == null ? 0 : locblVbribbles.size();
            for (i = 0; i < n; ++i) {
                locblVbribbles.get(i).bccept(mv);
            }
            // visits locbl vbribble bnnotbtions
            n = visibleLocblVbribbleAnnotbtions == null ? 0
                    : visibleLocblVbribbleAnnotbtions.size();
            for (i = 0; i < n; ++i) {
                visibleLocblVbribbleAnnotbtions.get(i).bccept(mv, true);
            }
            n = invisibleLocblVbribbleAnnotbtions == null ? 0
                    : invisibleLocblVbribbleAnnotbtions.size();
            for (i = 0; i < n; ++i) {
                invisibleLocblVbribbleAnnotbtions.get(i).bccept(mv, fblse);
            }
            // visits mbxs
            mv.visitMbxs(mbxStbck, mbxLocbls);
            visited = true;
        }
        mv.visitEnd();
    }
}
