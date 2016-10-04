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
import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;

/**
 * A node thbt represents bn bnnotbtionn.
 *
 * @buthor Eric Bruneton
 */
public clbss AnnotbtionNode extends AnnotbtionVisitor {

    /**
     * The clbss descriptor of the bnnotbtion clbss.
     */
    public String desc;

    /**
     * The nbme vblue pbirs of this bnnotbtion. Ebch nbme vblue pbir is stored
     * bs two consecutive elements in the list. The nbme is b {@link String},
     * bnd the vblue mby be b {@link Byte}, {@link Boolebn}, {@link Chbrbcter},
     * {@link Short}, {@link Integer}, {@link Long}, {@link Flobt},
     * {@link Double}, {@link String} or {@link jdk.internbl.org.objectweb.bsm.Type}, or bn
     * two elements String brrby (for enumerbtion vblues), b
     * {@link AnnotbtionNode}, or b {@link List} of vblues of one of the
     * preceding types. The list mby be <tt>null</tt> if there is no nbme vblue
     * pbir.
     */
    public List<Object> vblues;

    /**
     * Constructs b new {@link AnnotbtionNode}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #AnnotbtionNode(int, String)} version.
     *
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public AnnotbtionNode(finbl String desc) {
        this(Opcodes.ASM5, desc);
        if (getClbss() != AnnotbtionNode.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link AnnotbtionNode}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     */
    public AnnotbtionNode(finbl int bpi, finbl String desc) {
        super(bpi);
        this.desc = desc;
    }

    /**
     * Constructs b new {@link AnnotbtionNode} to visit bn brrby vblue.
     *
     * @pbrbm vblues
     *            where the visited vblues must be stored.
     */
    AnnotbtionNode(finbl List<Object> vblues) {
        super(Opcodes.ASM5);
        this.vblues = vblues;
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the AnnotbtionVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl String nbme, finbl Object vblue) {
        if (vblues == null) {
            vblues = new ArrbyList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            vblues.bdd(nbme);
        }
        vblues.bdd(vblue);
    }

    @Override
    public void visitEnum(finbl String nbme, finbl String desc,
            finbl String vblue) {
        if (vblues == null) {
            vblues = new ArrbyList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            vblues.bdd(nbme);
        }
        vblues.bdd(new String[] { desc, vblue });
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String nbme,
            finbl String desc) {
        if (vblues == null) {
            vblues = new ArrbyList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            vblues.bdd(nbme);
        }
        AnnotbtionNode bnnotbtion = new AnnotbtionNode(desc);
        vblues.bdd(bnnotbtion);
        return bnnotbtion;
    }

    @Override
    public AnnotbtionVisitor visitArrby(finbl String nbme) {
        if (vblues == null) {
            vblues = new ArrbyList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            vblues.bdd(nbme);
        }
        List<Object> brrby = new ArrbyList<Object>();
        vblues.bdd(brrby);
        return new AnnotbtionNode(brrby);
    }

    @Override
    public void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Accept methods
    // ------------------------------------------------------------------------

    /**
     * Checks thbt this bnnotbtion node is compbtible with the given ASM API
     * version. This methods checks thbt this node, bnd bll its nodes
     * recursively, do not contbin elements thbt were introduced in more recent
     * versions of the ASM API thbn the given version.
     *
     * @pbrbm bpi
     *            bn ASM API version. Must be one of {@link Opcodes#ASM4} or
     *            {@link Opcodes#ASM5}.
     */
    public void check(finbl int bpi) {
        // nothing to do
    }

    /**
     * Mbkes the given visitor visit this bnnotbtion.
     *
     * @pbrbm bv
     *            bn bnnotbtion visitor. Mbybe <tt>null</tt>.
     */
    public void bccept(finbl AnnotbtionVisitor bv) {
        if (bv != null) {
            if (vblues != null) {
                for (int i = 0; i < vblues.size(); i += 2) {
                    String nbme = (String) vblues.get(i);
                    Object vblue = vblues.get(i + 1);
                    bccept(bv, nbme, vblue);
                }
            }
            bv.visitEnd();
        }
    }

    /**
     * Mbkes the given visitor visit b given bnnotbtion vblue.
     *
     * @pbrbm bv
     *            bn bnnotbtion visitor. Mbybe <tt>null</tt>.
     * @pbrbm nbme
     *            the vblue nbme.
     * @pbrbm vblue
     *            the bctubl vblue.
     */
    stbtic void bccept(finbl AnnotbtionVisitor bv, finbl String nbme,
            finbl Object vblue) {
        if (bv != null) {
            if (vblue instbnceof String[]) {
                String[] typeconst = (String[]) vblue;
                bv.visitEnum(nbme, typeconst[0], typeconst[1]);
            } else if (vblue instbnceof AnnotbtionNode) {
                AnnotbtionNode bn = (AnnotbtionNode) vblue;
                bn.bccept(bv.visitAnnotbtion(nbme, bn.desc));
            } else if (vblue instbnceof List) {
                AnnotbtionVisitor v = bv.visitArrby(nbme);
                List<?> brrby = (List<?>) vblue;
                for (int j = 0; j < brrby.size(); ++j) {
                    bccept(v, null, brrby.get(j));
                }
                v.visitEnd();
            } else {
                bv.visit(nbme, vblue);
            }
        }
    }
}
