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
import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.ClbssVisitor;
import jdk.internbl.org.objectweb.bsm.FieldVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A node thbt represents b field.
 *
 * @buthor Eric Bruneton
 */
public clbss FieldNode extends FieldVisitor {

    /**
     * The field's bccess flbgs (see {@link jdk.internbl.org.objectweb.bsm.Opcodes}). This
     * field blso indicbtes if the field is synthetic bnd/or deprecbted.
     */
    public int bccess;

    /**
     * The field's nbme.
     */
    public String nbme;

    /**
     * The field's descriptor (see {@link jdk.internbl.org.objectweb.bsm.Type}).
     */
    public String desc;

    /**
     * The field's signbture. Mby be <tt>null</tt>.
     */
    public String signbture;

    /**
     * The field's initibl vblue. This field, which mby be <tt>null</tt> if the
     * field does not hbve bn initibl vblue, must be bn {@link Integer}, b
     * {@link Flobt}, b {@link Long}, b {@link Double} or b {@link String}.
     */
    public Object vblue;

    /**
     * The runtime visible bnnotbtions of this field. This list is b list of
     * {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel visible
     */
    public List<AnnotbtionNode> visibleAnnotbtions;

    /**
     * The runtime invisible bnnotbtions of this field. This list is b list of
     * {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel invisible
     */
    public List<AnnotbtionNode> invisibleAnnotbtions;

    /**
     * The runtime visible type bnnotbtions of this field. This list is b list
     * of {@link TypeAnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel visible
     */
    public List<TypeAnnotbtionNode> visibleTypeAnnotbtions;

    /**
     * The runtime invisible type bnnotbtions of this field. This list is b list
     * of {@link TypeAnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel invisible
     */
    public List<TypeAnnotbtionNode> invisibleTypeAnnotbtions;

    /**
     * The non stbndbrd bttributes of this field. This list is b list of
     * {@link Attribute} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.Attribute
     */
    public List<Attribute> bttrs;

    /**
     * Constructs b new {@link FieldNode}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #FieldNode(int, int, String, String, String, Object)} version.
     *
     * @pbrbm bccess
     *            the field's bccess flbgs (see
     *            {@link jdk.internbl.org.objectweb.bsm.Opcodes}). This pbrbmeter blso
     *            indicbtes if the field is synthetic bnd/or deprecbted.
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor (see {@link jdk.internbl.org.objectweb.bsm.Type
     *            Type}).
     * @pbrbm signbture
     *            the field's signbture.
     * @pbrbm vblue
     *            the field's initibl vblue. This pbrbmeter, which mby be
     *            <tt>null</tt> if the field does not hbve bn initibl vblue,
     *            must be bn {@link Integer}, b {@link Flobt}, b {@link Long}, b
     *            {@link Double} or b {@link String}.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public FieldNode(finbl int bccess, finbl String nbme, finbl String desc,
            finbl String signbture, finbl Object vblue) {
        this(Opcodes.ASM5, bccess, nbme, desc, signbture, vblue);
        if (getClbss() != FieldNode.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link FieldNode}. <i>Subclbsses must not use this
     * constructor</i>.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm bccess
     *            the field's bccess flbgs (see
     *            {@link jdk.internbl.org.objectweb.bsm.Opcodes}). This pbrbmeter blso
     *            indicbtes if the field is synthetic bnd/or deprecbted.
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor (see {@link jdk.internbl.org.objectweb.bsm.Type
     *            Type}).
     * @pbrbm signbture
     *            the field's signbture.
     * @pbrbm vblue
     *            the field's initibl vblue. This pbrbmeter, which mby be
     *            <tt>null</tt> if the field does not hbve bn initibl vblue,
     *            must be bn {@link Integer}, b {@link Flobt}, b {@link Long}, b
     *            {@link Double} or b {@link String}.
     */
    public FieldNode(finbl int bpi, finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        super(bpi);
        this.bccess = bccess;
        this.nbme = nbme;
        this.desc = desc;
        this.signbture = signbture;
        this.vblue = vblue;
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the FieldVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

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
    public void visitAttribute(finbl Attribute bttr) {
        if (bttrs == null) {
            bttrs = new ArrbyList<Attribute>(1);
        }
        bttrs.bdd(bttr);
    }

    @Override
    public void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Accept methods
    // ------------------------------------------------------------------------

    /**
     * Checks thbt this field node is compbtible with the given ASM API version.
     * This methods checks thbt this node, bnd bll its nodes recursively, do not
     * contbin elements thbt were introduced in more recent versions of the ASM
     * API thbn the given version.
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
        }
    }

    /**
     * Mbkes the given clbss visitor visit this field.
     *
     * @pbrbm cv
     *            b clbss visitor.
     */
    public void bccept(finbl ClbssVisitor cv) {
        FieldVisitor fv = cv.visitField(bccess, nbme, desc, signbture, vblue);
        if (fv == null) {
            return;
        }
        int i, n;
        n = visibleAnnotbtions == null ? 0 : visibleAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            AnnotbtionNode bn = visibleAnnotbtions.get(i);
            bn.bccept(fv.visitAnnotbtion(bn.desc, true));
        }
        n = invisibleAnnotbtions == null ? 0 : invisibleAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            AnnotbtionNode bn = invisibleAnnotbtions.get(i);
            bn.bccept(fv.visitAnnotbtion(bn.desc, fblse));
        }
        n = visibleTypeAnnotbtions == null ? 0 : visibleTypeAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = visibleTypeAnnotbtions.get(i);
            bn.bccept(fv.visitTypeAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    true));
        }
        n = invisibleTypeAnnotbtions == null ? 0 : invisibleTypeAnnotbtions
                .size();
        for (i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = invisibleTypeAnnotbtions.get(i);
            bn.bccept(fv.visitTypeAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    fblse));
        }
        n = bttrs == null ? 0 : bttrs.size();
        for (i = 0; i < n; ++i) {
            fv.visitAttribute(bttrs.get(i));
        }
        fv.visitEnd();
    }
}
