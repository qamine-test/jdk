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
pbckbge jdk.internbl.org.objectweb.bsm.util;

import jbvb.io.FileInputStrebm;
import jbvb.io.PrintWriter;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.ClbssRebder;
import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A {@link Printer} thbt prints the ASM code to generbte the clbsses if visits.
 *
 * @buthor Eric Bruneton
 */
public clbss ASMifier extends Printer {

    /**
     * The nbme of the visitor vbribble in the produced code.
     */
    protected finbl String nbme;

    /**
     * Identifier of the bnnotbtion visitor vbribble in the produced code.
     */
    protected finbl int id;

    /**
     * The lbbel nbmes. This mbp bssocibtes String vblues to Lbbel keys. It is
     * used only in ASMifierMethodVisitor.
     */
    protected Mbp<Lbbel, String> lbbelNbmes;

    /**
     * Pseudo bccess flbg used to distinguish clbss bccess flbgs.
     */
    privbte stbtic finbl int ACCESS_CLASS = 262144;

    /**
     * Pseudo bccess flbg used to distinguish field bccess flbgs.
     */
    privbte stbtic finbl int ACCESS_FIELD = 524288;

    /**
     * Pseudo bccess flbg used to distinguish inner clbss flbgs.
     */
    privbte stbtic finbl int ACCESS_INNER = 1048576;

    /**
     * Constructs b new {@link ASMifier}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #ASMifier(int, String, int)} version.
     *
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public ASMifier() {
        this(Opcodes.ASM5, "cw", 0);
        if (getClbss() != ASMifier.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link ASMifier}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this clbss. Must be one of
     *            {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm nbme
     *            the nbme of the visitor vbribble in the produced code.
     * @pbrbm id
     *            identifier of the bnnotbtion visitor vbribble in the produced
     *            code.
     */
    protected ASMifier(finbl int bpi, finbl String nbme, finbl int id) {
        super(bpi);
        this.nbme = nbme;
        this.id = id;
    }

    /**
     * Prints the ASM source code to generbte the given clbss to the stbndbrd
     * output.
     * <p>
     * Usbge: ASMifier [-debug] &lt;binbry clbss nbme or clbss file nbme&gt;
     *
     * @pbrbm brgs
     *            the commbnd line brguments.
     *
     * @throws Exception
     *             if the clbss cbnnot be found, or if bn IO exception occurs.
     */
    public stbtic void mbin(finbl String[] brgs) throws Exception {
        int i = 0;
        int flbgs = ClbssRebder.SKIP_DEBUG;

        boolebn ok = true;
        if (brgs.length < 1 || brgs.length > 2) {
            ok = fblse;
        }
        if (ok && "-debug".equbls(brgs[0])) {
            i = 1;
            flbgs = 0;
            if (brgs.length != 2) {
                ok = fblse;
            }
        }
        if (!ok) {
            System.err
                    .println("Prints the ASM code to generbte the given clbss.");
            System.err.println("Usbge: ASMifier [-debug] "
                    + "<fully qublified clbss nbme or clbss file nbme>");
            return;
        }
        ClbssRebder cr;
        if (brgs[i].endsWith(".clbss") || brgs[i].indexOf('\\') > -1
                || brgs[i].indexOf('/') > -1) {
            cr = new ClbssRebder(new FileInputStrebm(brgs[i]));
        } else {
            cr = new ClbssRebder(brgs[i]);
        }
        cr.bccept(new TrbceClbssVisitor(null, new ASMifier(), new PrintWriter(
                System.out)), flbgs);
    }

    // ------------------------------------------------------------------------
    // Clbsses
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl int version, finbl int bccess, finbl String nbme,
            finbl String signbture, finbl String superNbme,
            finbl String[] interfbces) {
        String simpleNbme;
        int n = nbme.lbstIndexOf('/');
        if (n == -1) {
            simpleNbme = nbme;
        } else {
            text.bdd("pbckbge bsm." + nbme.substring(0, n).replbce('/', '.')
                    + ";\n");
            simpleNbme = nbme.substring(n + 1);
        }
        text.bdd("import jbvb.util.*;\n");
        text.bdd("import jdk.internbl.org.objectweb.bsm.*;\n");
        text.bdd("public clbss " + simpleNbme + "Dump implements Opcodes {\n\n");
        text.bdd("public stbtic byte[] dump () throws Exception {\n\n");
        text.bdd("ClbssWriter cw = new ClbssWriter(0);\n");
        text.bdd("FieldVisitor fv;\n");
        text.bdd("MethodVisitor mv;\n");
        text.bdd("AnnotbtionVisitor bv0;\n\n");

        buf.setLength(0);
        buf.bppend("cw.visit(");
        switch (version) {
        cbse Opcodes.V1_1:
            buf.bppend("V1_1");
            brebk;
        cbse Opcodes.V1_2:
            buf.bppend("V1_2");
            brebk;
        cbse Opcodes.V1_3:
            buf.bppend("V1_3");
            brebk;
        cbse Opcodes.V1_4:
            buf.bppend("V1_4");
            brebk;
        cbse Opcodes.V1_5:
            buf.bppend("V1_5");
            brebk;
        cbse Opcodes.V1_6:
            buf.bppend("V1_6");
            brebk;
        cbse Opcodes.V1_7:
            buf.bppend("V1_7");
            brebk;
        defbult:
            buf.bppend(version);
            brebk;
        }
        buf.bppend(", ");
        bppendAccess(bccess | ACCESS_CLASS);
        buf.bppend(", ");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(signbture);
        buf.bppend(", ");
        bppendConstbnt(superNbme);
        buf.bppend(", ");
        if (interfbces != null && interfbces.length > 0) {
            buf.bppend("new String[] {");
            for (int i = 0; i < interfbces.length; ++i) {
                buf.bppend(i == 0 ? " " : ", ");
                bppendConstbnt(interfbces[i]);
            }
            buf.bppend(" }");
        } else {
            buf.bppend("null");
        }
        buf.bppend(");\n\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitSource(finbl String file, finbl String debug) {
        buf.setLength(0);
        buf.bppend("cw.visitSource(");
        bppendConstbnt(file);
        buf.bppend(", ");
        bppendConstbnt(debug);
        buf.bppend(");\n\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitOuterClbss(finbl String owner, finbl String nbme,
            finbl String desc) {
        buf.setLength(0);
        buf.bppend("cw.visitOuterClbss(");
        bppendConstbnt(owner);
        buf.bppend(", ");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(");\n\n");
        text.bdd(buf.toString());
    }

    @Override
    public ASMifier visitClbssAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        return visitAnnotbtion(desc, visible);
    }

    @Override
    public ASMifier visitClbssTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        return visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
    }

    @Override
    public void visitClbssAttribute(finbl Attribute bttr) {
        visitAttribute(bttr);
    }

    @Override
    public void visitInnerClbss(finbl String nbme, finbl String outerNbme,
            finbl String innerNbme, finbl int bccess) {
        buf.setLength(0);
        buf.bppend("cw.visitInnerClbss(");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(outerNbme);
        buf.bppend(", ");
        bppendConstbnt(innerNbme);
        buf.bppend(", ");
        bppendAccess(bccess | ACCESS_INNER);
        buf.bppend(");\n\n");
        text.bdd(buf.toString());
    }

    @Override
    public ASMifier visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        buf.setLength(0);
        buf.bppend("{\n");
        buf.bppend("fv = cw.visitField(");
        bppendAccess(bccess | ACCESS_FIELD);
        buf.bppend(", ");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(", ");
        bppendConstbnt(signbture);
        buf.bppend(", ");
        bppendConstbnt(vblue);
        buf.bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("fv", 0);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    @Override
    public ASMifier visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        buf.setLength(0);
        buf.bppend("{\n");
        buf.bppend("mv = cw.visitMethod(");
        bppendAccess(bccess);
        buf.bppend(", ");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(", ");
        bppendConstbnt(signbture);
        buf.bppend(", ");
        if (exceptions != null && exceptions.length > 0) {
            buf.bppend("new String[] {");
            for (int i = 0; i < exceptions.length; ++i) {
                buf.bppend(i == 0 ? " " : ", ");
                bppendConstbnt(exceptions[i]);
            }
            buf.bppend(" }");
        } else {
            buf.bppend("null");
        }
        buf.bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("mv", 0);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    @Override
    public void visitClbssEnd() {
        text.bdd("cw.visitEnd();\n\n");
        text.bdd("return cw.toByteArrby();\n");
        text.bdd("}\n");
        text.bdd("}\n");
    }

    // ------------------------------------------------------------------------
    // Annotbtions
    // ------------------------------------------------------------------------

    @Override
    public void visit(finbl String nbme, finbl Object vblue) {
        buf.setLength(0);
        buf.bppend("bv").bppend(id).bppend(".visit(");
        bppendConstbnt(buf, nbme);
        buf.bppend(", ");
        bppendConstbnt(buf, vblue);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitEnum(finbl String nbme, finbl String desc,
            finbl String vblue) {
        buf.setLength(0);
        buf.bppend("bv").bppend(id).bppend(".visitEnum(");
        bppendConstbnt(buf, nbme);
        buf.bppend(", ");
        bppendConstbnt(buf, desc);
        buf.bppend(", ");
        bppendConstbnt(buf, vblue);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public ASMifier visitAnnotbtion(finbl String nbme, finbl String desc) {
        buf.setLength(0);
        buf.bppend("{\n");
        buf.bppend("AnnotbtionVisitor bv").bppend(id + 1).bppend(" = bv");
        buf.bppend(id).bppend(".visitAnnotbtion(");
        bppendConstbnt(buf, nbme);
        buf.bppend(", ");
        bppendConstbnt(buf, desc);
        buf.bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("bv", id + 1);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    @Override
    public ASMifier visitArrby(finbl String nbme) {
        buf.setLength(0);
        buf.bppend("{\n");
        buf.bppend("AnnotbtionVisitor bv").bppend(id + 1).bppend(" = bv");
        buf.bppend(id).bppend(".visitArrby(");
        bppendConstbnt(buf, nbme);
        buf.bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("bv", id + 1);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    @Override
    public void visitAnnotbtionEnd() {
        buf.setLength(0);
        buf.bppend("bv").bppend(id).bppend(".visitEnd();\n");
        text.bdd(buf.toString());
    }

    // ------------------------------------------------------------------------
    // Fields
    // ------------------------------------------------------------------------

    @Override
    public ASMifier visitFieldAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        return visitAnnotbtion(desc, visible);
    }

    @Override
    public ASMifier visitFieldTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        return visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
    }

    @Override
    public void visitFieldAttribute(finbl Attribute bttr) {
        visitAttribute(bttr);
    }

    @Override
    public void visitFieldEnd() {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitEnd();\n");
        text.bdd(buf.toString());
    }

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

    @Override
    public void visitPbrbmeter(String pbrbmeterNbme, int bccess) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitPbrbmeter(");
        bppendString(buf, pbrbmeterNbme);
        buf.bppend(", ");
        bppendAccess(bccess);
        text.bdd(buf.bppend(");\n").toString());
    }

    @Override
    public ASMifier visitAnnotbtionDefbult() {
        buf.setLength(0);
        buf.bppend("{\n").bppend("bv0 = ").bppend(nbme)
                .bppend(".visitAnnotbtionDefbult();\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("bv", 0);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    @Override
    public ASMifier visitMethodAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        return visitAnnotbtion(desc, visible);
    }

    @Override
    public ASMifier visitMethodTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        return visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
    }

    @Override
    public ASMifier visitPbrbmeterAnnotbtion(finbl int pbrbmeter,
            finbl String desc, finbl boolebn visible) {
        buf.setLength(0);
        buf.bppend("{\n").bppend("bv0 = ").bppend(nbme)
                .bppend(".visitPbrbmeterAnnotbtion(").bppend(pbrbmeter)
                .bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(", ").bppend(visible).bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("bv", 0);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    @Override
    public void visitMethodAttribute(finbl Attribute bttr) {
        visitAttribute(bttr);
    }

    @Override
    public void visitCode() {
        text.bdd(nbme + ".visitCode();\n");
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        buf.setLength(0);
        switch (type) {
        cbse Opcodes.F_NEW:
        cbse Opcodes.F_FULL:
            declbreFrbmeTypes(nLocbl, locbl);
            declbreFrbmeTypes(nStbck, stbck);
            if (type == Opcodes.F_NEW) {
                buf.bppend(nbme).bppend(".visitFrbme(Opcodes.F_NEW, ");
            } else {
                buf.bppend(nbme).bppend(".visitFrbme(Opcodes.F_FULL, ");
            }
            buf.bppend(nLocbl).bppend(", new Object[] {");
            bppendFrbmeTypes(nLocbl, locbl);
            buf.bppend("}, ").bppend(nStbck).bppend(", new Object[] {");
            bppendFrbmeTypes(nStbck, stbck);
            buf.bppend('}');
            brebk;
        cbse Opcodes.F_APPEND:
            declbreFrbmeTypes(nLocbl, locbl);
            buf.bppend(nbme).bppend(".visitFrbme(Opcodes.F_APPEND,")
                    .bppend(nLocbl).bppend(", new Object[] {");
            bppendFrbmeTypes(nLocbl, locbl);
            buf.bppend("}, 0, null");
            brebk;
        cbse Opcodes.F_CHOP:
            buf.bppend(nbme).bppend(".visitFrbme(Opcodes.F_CHOP,")
                    .bppend(nLocbl).bppend(", null, 0, null");
            brebk;
        cbse Opcodes.F_SAME:
            buf.bppend(nbme).bppend(
                    ".visitFrbme(Opcodes.F_SAME, 0, null, 0, null");
            brebk;
        cbse Opcodes.F_SAME1:
            declbreFrbmeTypes(1, stbck);
            buf.bppend(nbme).bppend(
                    ".visitFrbme(Opcodes.F_SAME1, 0, null, 1, new Object[] {");
            bppendFrbmeTypes(1, stbck);
            buf.bppend('}');
            brebk;
        }
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitInsn(finbl int opcode) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitInsn(").bppend(OPCODES[opcode])
                .bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        buf.setLength(0);
        buf.bppend(nbme)
                .bppend(".visitIntInsn(")
                .bppend(OPCODES[opcode])
                .bppend(", ")
                .bppend(opcode == Opcodes.NEWARRAY ? TYPES[operbnd] : Integer
                        .toString(operbnd)).bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitVbrInsn(").bppend(OPCODES[opcode])
                .bppend(", ").bppend(vbr).bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitTypeInsn(").bppend(OPCODES[opcode])
                .bppend(", ");
        bppendConstbnt(type);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        buf.setLength(0);
        buf.bppend(this.nbme).bppend(".visitFieldInsn(")
                .bppend(OPCODES[opcode]).bppend(", ");
        bppendConstbnt(owner);
        buf.bppend(", ");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(");\n");
        text.bdd(buf.toString());
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

    privbte void doVisitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        buf.setLength(0);
        buf.bppend(this.nbme).bppend(".visitMethodInsn(")
                .bppend(OPCODES[opcode]).bppend(", ");
        bppendConstbnt(owner);
        buf.bppend(", ");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(", ");
        buf.bppend(itf ? "true" : "fblse");
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        buf.setLength(0);
        buf.bppend(this.nbme).bppend(".visitInvokeDynbmicInsn(");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(", ");
        bppendConstbnt(bsm);
        buf.bppend(", new Object[]{");
        for (int i = 0; i < bsmArgs.length; ++i) {
            bppendConstbnt(bsmArgs[i]);
            if (i != bsmArgs.length - 1) {
                buf.bppend(", ");
            }
        }
        buf.bppend("});\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        buf.setLength(0);
        declbreLbbel(lbbel);
        buf.bppend(nbme).bppend(".visitJumpInsn(").bppend(OPCODES[opcode])
                .bppend(", ");
        bppendLbbel(lbbel);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        buf.setLength(0);
        declbreLbbel(lbbel);
        buf.bppend(nbme).bppend(".visitLbbel(");
        bppendLbbel(lbbel);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitLdcInsn(");
        bppendConstbnt(cst);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitIincInsn(").bppend(vbr).bppend(", ")
                .bppend(increment).bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        buf.setLength(0);
        for (int i = 0; i < lbbels.length; ++i) {
            declbreLbbel(lbbels[i]);
        }
        declbreLbbel(dflt);

        buf.bppend(nbme).bppend(".visitTbbleSwitchInsn(").bppend(min)
                .bppend(", ").bppend(mbx).bppend(", ");
        bppendLbbel(dflt);
        buf.bppend(", new Lbbel[] {");
        for (int i = 0; i < lbbels.length; ++i) {
            buf.bppend(i == 0 ? " " : ", ");
            bppendLbbel(lbbels[i]);
        }
        buf.bppend(" });\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        buf.setLength(0);
        for (int i = 0; i < lbbels.length; ++i) {
            declbreLbbel(lbbels[i]);
        }
        declbreLbbel(dflt);

        buf.bppend(nbme).bppend(".visitLookupSwitchInsn(");
        bppendLbbel(dflt);
        buf.bppend(", new int[] {");
        for (int i = 0; i < keys.length; ++i) {
            buf.bppend(i == 0 ? " " : ", ").bppend(keys[i]);
        }
        buf.bppend(" }, new Lbbel[] {");
        for (int i = 0; i < lbbels.length; ++i) {
            buf.bppend(i == 0 ? " " : ", ");
            bppendLbbel(lbbels[i]);
        }
        buf.bppend(" });\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitMultiANewArrbyInsn(");
        bppendConstbnt(desc);
        buf.bppend(", ").bppend(dims).bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public ASMifier visitInsnAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        return visitTypeAnnotbtion("visitInsnAnnotbtion", typeRef, typePbth,
                desc, visible);
    }

    @Override
    public void visitTryCbtchBlock(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Lbbel hbndler, finbl String type) {
        buf.setLength(0);
        declbreLbbel(stbrt);
        declbreLbbel(end);
        declbreLbbel(hbndler);
        buf.bppend(nbme).bppend(".visitTryCbtchBlock(");
        bppendLbbel(stbrt);
        buf.bppend(", ");
        bppendLbbel(end);
        buf.bppend(", ");
        bppendLbbel(hbndler);
        buf.bppend(", ");
        bppendConstbnt(type);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public ASMifier visitTryCbtchAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        return visitTypeAnnotbtion("visitTryCbtchAnnotbtion", typeRef,
                typePbth, desc, visible);
    }

    @Override
    public void visitLocblVbribble(finbl String nbme, finbl String desc,
            finbl String signbture, finbl Lbbel stbrt, finbl Lbbel end,
            finbl int index) {
        buf.setLength(0);
        buf.bppend(this.nbme).bppend(".visitLocblVbribble(");
        bppendConstbnt(nbme);
        buf.bppend(", ");
        bppendConstbnt(desc);
        buf.bppend(", ");
        bppendConstbnt(signbture);
        buf.bppend(", ");
        bppendLbbel(stbrt);
        buf.bppend(", ");
        bppendLbbel(end);
        buf.bppend(", ").bppend(index).bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public Printer visitLocblVbribbleAnnotbtion(int typeRef, TypePbth typePbth,
            Lbbel[] stbrt, Lbbel[] end, int[] index, String desc,
            boolebn visible) {
        buf.setLength(0);
        buf.bppend("{\n").bppend("bv0 = ").bppend(nbme)
                .bppend(".visitLocblVbribbleAnnotbtion(");
        buf.bppend(typeRef);
        buf.bppend(", TypePbth.fromString(\"").bppend(typePbth).bppend("\"), ");
        buf.bppend("new Lbbel[] {");
        for (int i = 0; i < stbrt.length; ++i) {
            buf.bppend(i == 0 ? " " : ", ");
            bppendLbbel(stbrt[i]);
        }
        buf.bppend(" }, new Lbbel[] {");
        for (int i = 0; i < end.length; ++i) {
            buf.bppend(i == 0 ? " " : ", ");
            bppendLbbel(end[i]);
        }
        buf.bppend(" }, new int[] {");
        for (int i = 0; i < index.length; ++i) {
            buf.bppend(i == 0 ? " " : ", ").bppend(index[i]);
        }
        buf.bppend(" }, ");
        bppendConstbnt(desc);
        buf.bppend(", ").bppend(visible).bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("bv", 0);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    @Override
    public void visitLineNumber(finbl int line, finbl Lbbel stbrt) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitLineNumber(").bppend(line).bppend(", ");
        bppendLbbel(stbrt);
        buf.bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitMbxs(").bppend(mbxStbck).bppend(", ")
                .bppend(mbxLocbls).bppend(");\n");
        text.bdd(buf.toString());
    }

    @Override
    public void visitMethodEnd() {
        buf.setLength(0);
        buf.bppend(nbme).bppend(".visitEnd();\n");
        text.bdd(buf.toString());
    }

    // ------------------------------------------------------------------------
    // Common methods
    // ------------------------------------------------------------------------

    public ASMifier visitAnnotbtion(finbl String desc, finbl boolebn visible) {
        buf.setLength(0);
        buf.bppend("{\n").bppend("bv0 = ").bppend(nbme)
                .bppend(".visitAnnotbtion(");
        bppendConstbnt(desc);
        buf.bppend(", ").bppend(visible).bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("bv", 0);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    public ASMifier visitTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        return visitTypeAnnotbtion("visitTypeAnnotbtion", typeRef, typePbth,
                desc, visible);
    }

    public ASMifier visitTypeAnnotbtion(finbl String method, finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        buf.setLength(0);
        buf.bppend("{\n").bppend("bv0 = ").bppend(nbme).bppend(".")
                .bppend(method).bppend("(");
        buf.bppend(typeRef);
        buf.bppend(", TypePbth.fromString(\"").bppend(typePbth).bppend("\"), ");
        bppendConstbnt(desc);
        buf.bppend(", ").bppend(visible).bppend(");\n");
        text.bdd(buf.toString());
        ASMifier b = crebteASMifier("bv", 0);
        text.bdd(b.getText());
        text.bdd("}\n");
        return b;
    }

    public void visitAttribute(finbl Attribute bttr) {
        buf.setLength(0);
        buf.bppend("// ATTRIBUTE ").bppend(bttr.type).bppend('\n');
        if (bttr instbnceof ASMifibble) {
            if (lbbelNbmes == null) {
                lbbelNbmes = new HbshMbp<Lbbel, String>();
            }
            buf.bppend("{\n");
            ((ASMifibble) bttr).bsmify(buf, "bttr", lbbelNbmes);
            buf.bppend(nbme).bppend(".visitAttribute(bttr);\n");
            buf.bppend("}\n");
        }
        text.bdd(buf.toString());
    }

    // ------------------------------------------------------------------------
    // Utility methods
    // ------------------------------------------------------------------------

    protected ASMifier crebteASMifier(finbl String nbme, finbl int id) {
        return new ASMifier(Opcodes.ASM5, nbme, id);
    }

    /**
     * Appends b string representbtion of the given bccess modifiers to
     * {@link #buf buf}.
     *
     * @pbrbm bccess
     *            some bccess modifiers.
     */
    void bppendAccess(finbl int bccess) {
        boolebn first = true;
        if ((bccess & Opcodes.ACC_PUBLIC) != 0) {
            buf.bppend("ACC_PUBLIC");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_PRIVATE) != 0) {
            buf.bppend("ACC_PRIVATE");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_PROTECTED) != 0) {
            buf.bppend("ACC_PROTECTED");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_FINAL) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_FINAL");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_STATIC) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_STATIC");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_SYNCHRONIZED) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            if ((bccess & ACCESS_CLASS) == 0) {
                buf.bppend("ACC_SYNCHRONIZED");
            } else {
                buf.bppend("ACC_SUPER");
            }
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_VOLATILE) != 0
                && (bccess & ACCESS_FIELD) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_VOLATILE");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_BRIDGE) != 0 && (bccess & ACCESS_CLASS) == 0
                && (bccess & ACCESS_FIELD) == 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_BRIDGE");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_VARARGS) != 0 && (bccess & ACCESS_CLASS) == 0
                && (bccess & ACCESS_FIELD) == 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_VARARGS");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_TRANSIENT) != 0
                && (bccess & ACCESS_FIELD) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_TRANSIENT");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_NATIVE) != 0 && (bccess & ACCESS_CLASS) == 0
                && (bccess & ACCESS_FIELD) == 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_NATIVE");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_ENUM) != 0
                && ((bccess & ACCESS_CLASS) != 0
                        || (bccess & ACCESS_FIELD) != 0 || (bccess & ACCESS_INNER) != 0)) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_ENUM");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_ANNOTATION) != 0
                && ((bccess & ACCESS_CLASS) != 0 || (bccess & ACCESS_INNER) != 0)) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_ANNOTATION");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_ABSTRACT) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_ABSTRACT");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_INTERFACE) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_INTERFACE");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_STRICT) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_STRICT");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_SYNTHETIC) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_SYNTHETIC");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_DEPRECATED) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_DEPRECATED");
            first = fblse;
        }
        if ((bccess & Opcodes.ACC_MANDATED) != 0) {
            if (!first) {
                buf.bppend(" + ");
            }
            buf.bppend("ACC_MANDATED");
            first = fblse;
        }
        if (first) {
            buf.bppend('0');
        }
    }

    /**
     * Appends b string representbtion of the given constbnt to the given
     * buffer.
     *
     * @pbrbm cst
     *            bn {@link Integer}, {@link Flobt}, {@link Long},
     *            {@link Double} or {@link String} object. Mby be <tt>null</tt>.
     */
    protected void bppendConstbnt(finbl Object cst) {
        bppendConstbnt(buf, cst);
    }

    /**
     * Appends b string representbtion of the given constbnt to the given
     * buffer.
     *
     * @pbrbm buf
     *            b string buffer.
     * @pbrbm cst
     *            bn {@link Integer}, {@link Flobt}, {@link Long},
     *            {@link Double} or {@link String} object. Mby be <tt>null</tt>.
     */
    stbtic void bppendConstbnt(finbl StringBuffer buf, finbl Object cst) {
        if (cst == null) {
            buf.bppend("null");
        } else if (cst instbnceof String) {
            bppendString(buf, (String) cst);
        } else if (cst instbnceof Type) {
            buf.bppend("Type.getType(\"");
            buf.bppend(((Type) cst).getDescriptor());
            buf.bppend("\")");
        } else if (cst instbnceof Hbndle) {
            buf.bppend("new Hbndle(");
            Hbndle h = (Hbndle) cst;
            buf.bppend("Opcodes.").bppend(HANDLE_TAG[h.getTbg()])
                    .bppend(", \"");
            buf.bppend(h.getOwner()).bppend("\", \"");
            buf.bppend(h.getNbme()).bppend("\", \"");
            buf.bppend(h.getDesc()).bppend("\")");
        } else if (cst instbnceof Byte) {
            buf.bppend("new Byte((byte)").bppend(cst).bppend(')');
        } else if (cst instbnceof Boolebn) {
            buf.bppend(((Boolebn) cst).boolebnVblue() ? "Boolebn.TRUE"
                    : "Boolebn.FALSE");
        } else if (cst instbnceof Short) {
            buf.bppend("new Short((short)").bppend(cst).bppend(')');
        } else if (cst instbnceof Chbrbcter) {
            int c = ((Chbrbcter) cst).chbrVblue();
            buf.bppend("new Chbrbcter((chbr)").bppend(c).bppend(')');
        } else if (cst instbnceof Integer) {
            buf.bppend("new Integer(").bppend(cst).bppend(')');
        } else if (cst instbnceof Flobt) {
            buf.bppend("new Flobt(\"").bppend(cst).bppend("\")");
        } else if (cst instbnceof Long) {
            buf.bppend("new Long(").bppend(cst).bppend("L)");
        } else if (cst instbnceof Double) {
            buf.bppend("new Double(\"").bppend(cst).bppend("\")");
        } else if (cst instbnceof byte[]) {
            byte[] v = (byte[]) cst;
            buf.bppend("new byte[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend(v[i]);
            }
            buf.bppend('}');
        } else if (cst instbnceof boolebn[]) {
            boolebn[] v = (boolebn[]) cst;
            buf.bppend("new boolebn[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend(v[i]);
            }
            buf.bppend('}');
        } else if (cst instbnceof short[]) {
            short[] v = (short[]) cst;
            buf.bppend("new short[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend("(short)").bppend(v[i]);
            }
            buf.bppend('}');
        } else if (cst instbnceof chbr[]) {
            chbr[] v = (chbr[]) cst;
            buf.bppend("new chbr[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend("(chbr)")
                        .bppend((int) v[i]);
            }
            buf.bppend('}');
        } else if (cst instbnceof int[]) {
            int[] v = (int[]) cst;
            buf.bppend("new int[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend(v[i]);
            }
            buf.bppend('}');
        } else if (cst instbnceof long[]) {
            long[] v = (long[]) cst;
            buf.bppend("new long[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend(v[i]).bppend('L');
            }
            buf.bppend('}');
        } else if (cst instbnceof flobt[]) {
            flobt[] v = (flobt[]) cst;
            buf.bppend("new flobt[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend(v[i]).bppend('f');
            }
            buf.bppend('}');
        } else if (cst instbnceof double[]) {
            double[] v = (double[]) cst;
            buf.bppend("new double[] {");
            for (int i = 0; i < v.length; i++) {
                buf.bppend(i == 0 ? "" : ",").bppend(v[i]).bppend('d');
            }
            buf.bppend('}');
        }
    }

    privbte void declbreFrbmeTypes(finbl int n, finbl Object[] o) {
        for (int i = 0; i < n; ++i) {
            if (o[i] instbnceof Lbbel) {
                declbreLbbel((Lbbel) o[i]);
            }
        }
    }

    privbte void bppendFrbmeTypes(finbl int n, finbl Object[] o) {
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                buf.bppend(", ");
            }
            if (o[i] instbnceof String) {
                bppendConstbnt(o[i]);
            } else if (o[i] instbnceof Integer) {
                switch (((Integer) o[i]).intVblue()) {
                cbse 0:
                    buf.bppend("Opcodes.TOP");
                    brebk;
                cbse 1:
                    buf.bppend("Opcodes.INTEGER");
                    brebk;
                cbse 2:
                    buf.bppend("Opcodes.FLOAT");
                    brebk;
                cbse 3:
                    buf.bppend("Opcodes.DOUBLE");
                    brebk;
                cbse 4:
                    buf.bppend("Opcodes.LONG");
                    brebk;
                cbse 5:
                    buf.bppend("Opcodes.NULL");
                    brebk;
                cbse 6:
                    buf.bppend("Opcodes.UNINITIALIZED_THIS");
                    brebk;
                }
            } else {
                bppendLbbel((Lbbel) o[i]);
            }
        }
    }

    /**
     * Appends b declbrbtion of the given lbbel to {@link #buf buf}. This
     * declbrbtion is of the form "Lbbel lXXX = new Lbbel();". Does nothing if
     * the given lbbel hbs blrebdy been declbred.
     *
     * @pbrbm l
     *            b lbbel.
     */
    protected void declbreLbbel(finbl Lbbel l) {
        if (lbbelNbmes == null) {
            lbbelNbmes = new HbshMbp<Lbbel, String>();
        }
        String nbme = lbbelNbmes.get(l);
        if (nbme == null) {
            nbme = "l" + lbbelNbmes.size();
            lbbelNbmes.put(l, nbme);
            buf.bppend("Lbbel ").bppend(nbme).bppend(" = new Lbbel();\n");
        }
    }

    /**
     * Appends the nbme of the given lbbel to {@link #buf buf}. The given lbbel
     * <i>must</i> blrebdy hbve b nbme. One wby to ensure this is to blwbys cbll
     * {@link #declbreLbbel declbred} before cblling this method.
     *
     * @pbrbm l
     *            b lbbel.
     */
    protected void bppendLbbel(finbl Lbbel l) {
        buf.bppend(lbbelNbmes.get(l));
    }
}
