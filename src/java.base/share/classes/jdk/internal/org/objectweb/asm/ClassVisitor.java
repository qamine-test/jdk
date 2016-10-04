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
pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * A visitor to visit b Jbvb clbss. The methods of this clbss must be cblled in
 * the following order: <tt>visit</tt> [ <tt>visitSource</tt> ] [
 * <tt>visitOuterClbss</tt> ] ( <tt>visitAnnotbtion</tt> |
 * <tt>visitTypeAnnotbtion</tt> | <tt>visitAttribute</tt> )* (
 * <tt>visitInnerClbss</tt> | <tt>visitField</tt> | <tt>visitMethod</tt> )*
 * <tt>visitEnd</tt>.
 *
 * @buthor Eric Bruneton
 */
public bbstrbct clbss ClbssVisitor {

    /**
     * The ASM API version implemented by this visitor. The vblue of this field
     * must be one of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    protected finbl int bpi;

    /**
     * The clbss visitor to which this visitor must delegbte method cblls. Mby
     * be null.
     */
    protected ClbssVisitor cv;

    /**
     * Constructs b new {@link ClbssVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    public ClbssVisitor(finbl int bpi) {
        this(bpi, null);
    }

    /**
     * Constructs b new {@link ClbssVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm cv
     *            the clbss visitor to which this visitor must delegbte method
     *            cblls. Mby be null.
     */
    public ClbssVisitor(finbl int bpi, finbl ClbssVisitor cv) {
        if (bpi != Opcodes.ASM4 && bpi != Opcodes.ASM5) {
            throw new IllegblArgumentException();
        }
        this.bpi = bpi;
        this.cv = cv;
    }

    /**
     * Visits the hebder of the clbss.
     *
     * @pbrbm version
     *            the clbss version.
     * @pbrbm bccess
     *            the clbss's bccess flbgs (see {@link Opcodes}). This pbrbmeter
     *            blso indicbtes if the clbss is deprecbted.
     * @pbrbm nbme
     *            the internbl nbme of the clbss (see
     *            {@link Type#getInternblNbme() getInternblNbme}).
     * @pbrbm signbture
     *            the signbture of this clbss. Mby be <tt>null</tt> if the clbss
     *            is not b generic one, bnd does not extend or implement generic
     *            clbsses or interfbces.
     * @pbrbm superNbme
     *            the internbl of nbme of the super clbss (see
     *            {@link Type#getInternblNbme() getInternblNbme}). For
     *            interfbces, the super clbss is {@link Object}. Mby be
     *            <tt>null</tt>, but only for the {@link Object} clbss.
     * @pbrbm interfbces
     *            the internbl nbmes of the clbss's interfbces (see
     *            {@link Type#getInternblNbme() getInternblNbme}). Mby be
     *            <tt>null</tt>.
     */
    public void visit(int version, int bccess, String nbme, String signbture,
            String superNbme, String[] interfbces) {
        if (cv != null) {
            cv.visit(version, bccess, nbme, signbture, superNbme, interfbces);
        }
    }

    /**
     * Visits the source of the clbss.
     *
     * @pbrbm source
     *            the nbme of the source file from which the clbss wbs compiled.
     *            Mby be <tt>null</tt>.
     * @pbrbm debug
     *            bdditionbl debug informbtion to compute the correspondbnce
     *            between source bnd compiled elements of the clbss. Mby be
     *            <tt>null</tt>.
     */
    public void visitSource(String source, String debug) {
        if (cv != null) {
            cv.visitSource(source, debug);
        }
    }

    /**
     * Visits the enclosing clbss of the clbss. This method must be cblled only
     * if the clbss hbs bn enclosing clbss.
     *
     * @pbrbm owner
     *            internbl nbme of the enclosing clbss of the clbss.
     * @pbrbm nbme
     *            the nbme of the method thbt contbins the clbss, or
     *            <tt>null</tt> if the clbss is not enclosed in b method of its
     *            enclosing clbss.
     * @pbrbm desc
     *            the descriptor of the method thbt contbins the clbss, or
     *            <tt>null</tt> if the clbss is not enclosed in b method of its
     *            enclosing clbss.
     */
    public void visitOuterClbss(String owner, String nbme, String desc) {
        if (cv != null) {
            cv.visitOuterClbss(owner, nbme, desc);
        }
    }

    /**
     * Visits bn bnnotbtion of the clbss.
     *
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitAnnotbtion(String desc, boolebn visible) {
        if (cv != null) {
            return cv.visitAnnotbtion(desc, visible);
        }
        return null;
    }

    /**
     * Visits bn bnnotbtion on b type in the clbss signbture.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. The sort of this type
     *            reference must be {@link TypeReference#CLASS_TYPE_PARAMETER
     *            CLASS_TYPE_PARAMETER},
     *            {@link TypeReference#CLASS_TYPE_PARAMETER_BOUND
     *            CLASS_TYPE_PARAMETER_BOUND} or
     *            {@link TypeReference#CLASS_EXTENDS CLASS_EXTENDS}. See
     *            {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitTypeAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        if (bpi < Opcodes.ASM5) {
            throw new RuntimeException();
        }
        if (cv != null) {
            return cv.visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
        }
        return null;
    }

    /**
     * Visits b non stbndbrd bttribute of the clbss.
     *
     * @pbrbm bttr
     *            bn bttribute.
     */
    public void visitAttribute(Attribute bttr) {
        if (cv != null) {
            cv.visitAttribute(bttr);
        }
    }

    /**
     * Visits informbtion bbout bn inner clbss. This inner clbss is not
     * necessbrily b member of the clbss being visited.
     *
     * @pbrbm nbme
     *            the internbl nbme of bn inner clbss (see
     *            {@link Type#getInternblNbme() getInternblNbme}).
     * @pbrbm outerNbme
     *            the internbl nbme of the clbss to which the inner clbss
     *            belongs (see {@link Type#getInternblNbme() getInternblNbme}).
     *            Mby be <tt>null</tt> for not member clbsses.
     * @pbrbm innerNbme
     *            the (simple) nbme of the inner clbss inside its enclosing
     *            clbss. Mby be <tt>null</tt> for bnonymous inner clbsses.
     * @pbrbm bccess
     *            the bccess flbgs of the inner clbss bs originblly declbred in
     *            the enclosing clbss.
     */
    public void visitInnerClbss(String nbme, String outerNbme,
            String innerNbme, int bccess) {
        if (cv != null) {
            cv.visitInnerClbss(nbme, outerNbme, innerNbme, bccess);
        }
    }

    /**
     * Visits b field of the clbss.
     *
     * @pbrbm bccess
     *            the field's bccess flbgs (see {@link Opcodes}). This pbrbmeter
     *            blso indicbtes if the field is synthetic bnd/or deprecbted.
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor (see {@link Type Type}).
     * @pbrbm signbture
     *            the field's signbture. Mby be <tt>null</tt> if the field's
     *            type does not use generic types.
     * @pbrbm vblue
     *            the field's initibl vblue. This pbrbmeter, which mby be
     *            <tt>null</tt> if the field does not hbve bn initibl vblue,
     *            must be bn {@link Integer}, b {@link Flobt}, b {@link Long}, b
     *            {@link Double} or b {@link String} (for <tt>int</tt>,
     *            <tt>flobt</tt>, <tt>long</tt> or <tt>String</tt> fields
     *            respectively). <i>This pbrbmeter is only used for stbtic
     *            fields</i>. Its vblue is ignored for non stbtic fields, which
     *            must be initiblized through bytecode instructions in
     *            constructors or methods.
     * @return b visitor to visit field bnnotbtions bnd bttributes, or
     *         <tt>null</tt> if this clbss visitor is not interested in visiting
     *         these bnnotbtions bnd bttributes.
     */
    public FieldVisitor visitField(int bccess, String nbme, String desc,
            String signbture, Object vblue) {
        if (cv != null) {
            return cv.visitField(bccess, nbme, desc, signbture, vblue);
        }
        return null;
    }

    /**
     * Visits b method of the clbss. This method <i>must</i> return b new
     * {@link MethodVisitor} instbnce (or <tt>null</tt>) ebch time it is cblled,
     * i.e., it should not return b previously returned visitor.
     *
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}). This
     *            pbrbmeter blso indicbtes if the method is synthetic bnd/or
     *            deprecbted.
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm signbture
     *            the method's signbture. Mby be <tt>null</tt> if the method
     *            pbrbmeters, return type bnd exceptions do not use generic
     *            types.
     * @pbrbm exceptions
     *            the internbl nbmes of the method's exception clbsses (see
     *            {@link Type#getInternblNbme() getInternblNbme}). Mby be
     *            <tt>null</tt>.
     * @return bn object to visit the byte code of the method, or <tt>null</tt>
     *         if this clbss visitor is not interested in visiting the code of
     *         this method.
     */
    public MethodVisitor visitMethod(int bccess, String nbme, String desc,
            String signbture, String[] exceptions) {
        if (cv != null) {
            return cv.visitMethod(bccess, nbme, desc, signbture, exceptions);
        }
        return null;
    }

    /**
     * Visits the end of the clbss. This method, which is the lbst one to be
     * cblled, is used to inform the visitor thbt bll the fields bnd methods of
     * the clbss hbve been visited.
     */
    public void visitEnd() {
        if (cv != null) {
            cv.visitEnd();
        }
    }
}
