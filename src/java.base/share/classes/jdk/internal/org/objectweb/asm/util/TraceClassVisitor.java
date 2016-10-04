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

import jbvb.io.PrintWriter;

import jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor;
import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.ClbssVisitor;
import jdk.internbl.org.objectweb.bsm.FieldVisitor;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A {@link ClbssVisitor} thbt prints the clbsses it visits with b
 * {@link Printer}. This clbss visitor cbn be used in the middle of b clbss
 * visitor chbin to trbce the clbss thbt is visited bt b given point in this
 * chbin. This mby be useful for debugging purposes.
 * <p>
 * The trbce printed when visiting the <tt>Hello</tt> clbss is the following:
 * <p>
 * <blockquote>
 *
 * <pre>
 * // clbss version 49.0 (49) // bccess flbgs 0x21 public clbss Hello {
 *
 * // compiled from: Hello.jbvb
 *
 * // bccess flbgs 0x1 public &lt;init&gt; ()V ALOAD 0 INVOKESPECIAL
 * jbvb/lbng/Object &lt;init&gt; ()V RETURN MAXSTACK = 1 MAXLOCALS = 1
 *
 * // bccess flbgs 0x9 public stbtic mbin ([Ljbvb/lbng/String;)V GETSTATIC
 * jbvb/lbng/System out Ljbvb/io/PrintStrebm; LDC &quot;hello&quot;
 * INVOKEVIRTUAL jbvb/io/PrintStrebm println (Ljbvb/lbng/String;)V RETURN
 * MAXSTACK = 2 MAXLOCALS = 1 }
 * </pre>
 *
 * </blockquote> where <tt>Hello</tt> is defined by:
 * <p>
 * <blockquote>
 *
 * <pre>
 * public clbss Hello {
 *
 *     public stbtic void mbin(String[] brgs) {
 *         System.out.println(&quot;hello&quot;);
 *     }
 * }
 * </pre>
 *
 * </blockquote>
 *
 * @buthor Eric Bruneton
 * @buthor Eugene Kuleshov
 */
public finbl clbss TrbceClbssVisitor extends ClbssVisitor {

    /**
     * The print writer to be used to print the clbss. Mby be null.
     */
    privbte finbl PrintWriter pw;

    /**
     * The object thbt bctublly converts visit events into text.
     */
    public finbl Printer p;

    /**
     * Constructs b new {@link TrbceClbssVisitor}.
     *
     * @pbrbm pw
     *            the print writer to be used to print the clbss.
     */
    public TrbceClbssVisitor(finbl PrintWriter pw) {
        this(null, pw);
    }

    /**
     * Constructs b new {@link TrbceClbssVisitor}.
     *
     * @pbrbm cv
     *            the {@link ClbssVisitor} to which this visitor delegbtes
     *            cblls. Mby be <tt>null</tt>.
     * @pbrbm pw
     *            the print writer to be used to print the clbss.
     */
    public TrbceClbssVisitor(finbl ClbssVisitor cv, finbl PrintWriter pw) {
        this(cv, new Textifier(), pw);
    }

    /**
     * Constructs b new {@link TrbceClbssVisitor}.
     *
     * @pbrbm cv
     *            the {@link ClbssVisitor} to which this visitor delegbtes
     *            cblls. Mby be <tt>null</tt>.
     * @pbrbm p
     *            the object thbt bctublly converts visit events into text.
     * @pbrbm pw
     *            the print writer to be used to print the clbss. Mby be null if
     *            you simply wbnt to use the result vib
     *            {@link Printer#getText()}, instebd of printing it.
     */
    public TrbceClbssVisitor(finbl ClbssVisitor cv, finbl Printer p,
            finbl PrintWriter pw) {
        super(Opcodes.ASM5, cv);
        this.pw = pw;
        this.p = p;
    }

    @Override
    public void visit(finbl int version, finbl int bccess, finbl String nbme,
            finbl String signbture, finbl String superNbme,
            finbl String[] interfbces) {
        p.visit(version, bccess, nbme, signbture, superNbme, interfbces);
        super.visit(version, bccess, nbme, signbture, superNbme, interfbces);
    }

    @Override
    public void visitSource(finbl String file, finbl String debug) {
        p.visitSource(file, debug);
        super.visitSource(file, debug);
    }

    @Override
    public void visitOuterClbss(finbl String owner, finbl String nbme,
            finbl String desc) {
        p.visitOuterClbss(owner, nbme, desc);
        super.visitOuterClbss(owner, nbme, desc);
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        Printer p = this.p.visitClbssAnnotbtion(desc, visible);
        AnnotbtionVisitor bv = cv == null ? null : cv.visitAnnotbtion(desc,
                visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public AnnotbtionVisitor visitTypeAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        Printer p = this.p.visitClbssTypeAnnotbtion(typeRef, typePbth, desc,
                visible);
        AnnotbtionVisitor bv = cv == null ? null : cv.visitTypeAnnotbtion(
                typeRef, typePbth, desc, visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public void visitAttribute(finbl Attribute bttr) {
        p.visitClbssAttribute(bttr);
        super.visitAttribute(bttr);
    }

    @Override
    public void visitInnerClbss(finbl String nbme, finbl String outerNbme,
            finbl String innerNbme, finbl int bccess) {
        p.visitInnerClbss(nbme, outerNbme, innerNbme, bccess);
        super.visitInnerClbss(nbme, outerNbme, innerNbme, bccess);
    }

    @Override
    public FieldVisitor visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue) {
        Printer p = this.p.visitField(bccess, nbme, desc, signbture, vblue);
        FieldVisitor fv = cv == null ? null : cv.visitField(bccess, nbme, desc,
                signbture, vblue);
        return new TrbceFieldVisitor(fv, p);
    }

    @Override
    public MethodVisitor visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions) {
        Printer p = this.p.visitMethod(bccess, nbme, desc, signbture,
                exceptions);
        MethodVisitor mv = cv == null ? null : cv.visitMethod(bccess, nbme,
                desc, signbture, exceptions);
        return new TrbceMethodVisitor(mv, p);
    }

    @Override
    public void visitEnd() {
        p.visitClbssEnd();
        if (pw != null) {
            p.print(pw);
            pw.flush();
        }
        super.visitEnd();
    }
}
