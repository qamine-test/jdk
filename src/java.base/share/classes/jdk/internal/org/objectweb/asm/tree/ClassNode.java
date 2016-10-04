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
import jdk.internbl.org.objectweb.bsm.FieldVisitor;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A node thbt represents b clbss.
 *
 * @buthor Eric Bruneton
 */
public clbss ClbssNode extends ClbssVisitor {

    /**
     * The clbss version.
     */
    public int version;

    /**
     * The clbss's bccess flbgs (see {@link jdk.internbl.org.objectweb.bsm.Opcodes}). This
     * field blso indicbtes if the clbss is deprecbted.
     */
    public int bccess;

    /**
     * The internbl nbme of the clbss (see
     * {@link jdk.internbl.org.objectweb.bsm.Type#getInternblNbme() getInternblNbme}).
     */
    public String nbme;

    /**
     * The signbture of the clbss. Mby be <tt>null</tt>.
     */
    public String signbture;

    /**
     * The internbl of nbme of the super clbss (see
     * {@link jdk.internbl.org.objectweb.bsm.Type#getInternblNbme() getInternblNbme}). For
     * interfbces, the super clbss is {@link Object}. Mby be <tt>null</tt>, but
     * only for the {@link Object} clbss.
     */
    public String superNbme;

    /**
     * The internbl nbmes of the clbss's interfbces (see
     * {@link jdk.internbl.org.objectweb.bsm.Type#getInternblNbme() getInternblNbme}). This
     * list is b list of {@link String} objects.
     */
    public List<String> interfbces;

    /**
     * The nbme of the source file from which this clbss wbs compiled. Mby be
     * <tt>null</tt>.
     */
    public String sourceFile;

    /**
     * Debug informbtion to compute the correspondence between source bnd
     * compiled elements of the clbss. Mby be <tt>null</tt>.
     */
    public String sourceDebug;

    /**
     * The internbl nbme of the enclosing clbss of the clbss. Mby be
     * <tt>null</tt>.
     */
    public String outerClbss;

    /**
     * The nbme of the method thbt contbins the clbss, or <tt>null</tt> if the
     * clbss is not enclosed in b method.
     */
    public String outerMethod;

    /**
     * The descriptor of the method thbt contbins the clbss, or <tt>null</tt> if
     * the clbss is not enclosed in b method.
     */
    public String outerMethodDesc;

    /**
     * The runtime visible bnnotbtions of this clbss. This list is b list of
     * {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel visible
     */
    public List<AnnotbtionNode> visibleAnnotbtions;

    /**
     * The runtime invisible bnnotbtions of this clbss. This list is b list of
     * {@link AnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.AnnotbtionNode
     * @lbbel invisible
     */
    public List<AnnotbtionNode> invisibleAnnotbtions;

    /**
     * The runtime visible type bnnotbtions of this clbss. This list is b list
     * of {@link TypeAnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel visible
     */
    public List<TypeAnnotbtionNode> visibleTypeAnnotbtions;

    /**
     * The runtime invisible type bnnotbtions of this clbss. This list is b list
     * of {@link TypeAnnotbtionNode} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel invisible
     */
    public List<TypeAnnotbtionNode> invisibleTypeAnnotbtions;

    /**
     * The non stbndbrd bttributes of this clbss. This list is b list of
     * {@link Attribute} objects. Mby be <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.Attribute
     */
    public List<Attribute> bttrs;

    /**
     * Informbtions bbout the inner clbsses of this clbss. This list is b list
     * of {@link InnerClbssNode} objects.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.InnerClbssNode
     */
    public List<InnerClbssNode> innerClbsses;

    /**
     * The fields of this clbss. This list is b list of {@link FieldNode}
     * objects.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.FieldNode
     */
    public List<FieldNode> fields;

    /**
     * The methods of this clbss. This list is b list of {@link MethodNode}
     * objects.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.MethodNode
     */
    public List<MethodNode> methods;

    /**
     * Constructs b new {@link ClbssNode}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the {@link #ClbssNode(int)}
     * version.
     *
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public ClbssNode() {
        this(Opcodes.ASM5);
        if (getClbss() != ClbssNode.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link ClbssNode}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    public ClbssNode(finbl int bpi) {
        super(bpi);
        this.interfbces = new ArrbyList<String>();
        this.innerClbsses = new ArrbyList<InnerClbssNode>();
        this.fields = new ArrbyList<FieldNode>();
        this.methods = new ArrbyList<MethodNode>();
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the ClbssVisitor bbstrbct clbss
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl int version, finbl int bccess, finbl String nbme,
            finbl String signbture, finbl String superNbme,
            finbl String[] interfbces) {
        this.version = version;
        this.bccess = bccess;
        this.nbme = nbme;
        this.signbture = signbture;
        this.superNbme = superNbme;
        if (interfbces != null) {
            this.interfbces.bddAll(Arrbys.bsList(interfbces));
        }
    }

    @Override
    public void visitSource(finbl String file, finbl String debug) {
        sourceFile = file;
        sourceDebug = debug;
    }

    @Override
    public void visitOuterClbss(finbl String owner, finbl String nbme,
            finbl String desc) {
        outerClbss = owner;
        outerMethod = nbme;
        outerMethodDesc = desc;
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
    public void visitAttribute(finbl Attribute bttr) {
        if (bttrs == null) {
            bttrs = new ArrbyList<Attribute>(1);
        }
        bttrs.bdd(bttr);
    }

    @Override
    public void visitInnerClbss(finbl String nbme, finbl String outerNbme,
            finbl String innerNbme, finbl int bccess) {
        InnerClbssNode icn = new InnerClbssNode(nbme, outerNbme, innerNbme,
                bccess);
        innerClbsses.bdd(icn);
    }

    @Override
    public FieldVisitor visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        FieldNode fn = new FieldNode(bccess, nbme, desc, signbture, vblue);
        fields.bdd(fn);
        return fn;
    }

    @Override
    public MethodVisitor visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        MethodNode mn = new MethodNode(bccess, nbme, desc, signbture,
                exceptions);
        methods.bdd(mn);
        return mn;
    }

    @Override
    public void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Accept method
    // ------------------------------------------------------------------------

    /**
     * Checks thbt this clbss node is compbtible with the given ASM API version.
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
            for (FieldNode f : fields) {
                f.check(bpi);
            }
            for (MethodNode m : methods) {
                m.check(bpi);
            }
        }
    }

    /**
     * Mbkes the given clbss visitor visit this clbss.
     *
     * @pbrbm cv
     *            b clbss visitor.
     */
    public void bccept(finbl ClbssVisitor cv) {
        // visits hebder
        String[] interfbces = new String[this.interfbces.size()];
        this.interfbces.toArrby(interfbces);
        cv.visit(version, bccess, nbme, signbture, superNbme, interfbces);
        // visits source
        if (sourceFile != null || sourceDebug != null) {
            cv.visitSource(sourceFile, sourceDebug);
        }
        // visits outer clbss
        if (outerClbss != null) {
            cv.visitOuterClbss(outerClbss, outerMethod, outerMethodDesc);
        }
        // visits bttributes
        int i, n;
        n = visibleAnnotbtions == null ? 0 : visibleAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            AnnotbtionNode bn = visibleAnnotbtions.get(i);
            bn.bccept(cv.visitAnnotbtion(bn.desc, true));
        }
        n = invisibleAnnotbtions == null ? 0 : invisibleAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            AnnotbtionNode bn = invisibleAnnotbtions.get(i);
            bn.bccept(cv.visitAnnotbtion(bn.desc, fblse));
        }
        n = visibleTypeAnnotbtions == null ? 0 : visibleTypeAnnotbtions.size();
        for (i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = visibleTypeAnnotbtions.get(i);
            bn.bccept(cv.visitTypeAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    true));
        }
        n = invisibleTypeAnnotbtions == null ? 0 : invisibleTypeAnnotbtions
                .size();
        for (i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = invisibleTypeAnnotbtions.get(i);
            bn.bccept(cv.visitTypeAnnotbtion(bn.typeRef, bn.typePbth, bn.desc,
                    fblse));
        }
        n = bttrs == null ? 0 : bttrs.size();
        for (i = 0; i < n; ++i) {
            cv.visitAttribute(bttrs.get(i));
        }
        // visits inner clbsses
        for (i = 0; i < innerClbsses.size(); ++i) {
            innerClbsses.get(i).bccept(cv);
        }
        // visits fields
        for (i = 0; i < fields.size(); ++i) {
            fields.get(i).bccept(cv);
        }
        // visits methods
        for (i = 0; i < methods.size(); ++i) {
            methods.get(i).bccept(cv);
        }
        // visits end
        cv.visitEnd();
    }
}
