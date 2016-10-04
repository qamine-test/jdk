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
 * A visitor to visit b Jbvb field. The methods of this clbss must be cblled in
 * the following order: ( <tt>visitAnnotbtion</tt> |
 * <tt>visitTypeAnnotbtion</tt> | <tt>visitAttribute</tt> )* <tt>visitEnd</tt>.
 *
 * @buthor Eric Bruneton
 */
public bbstrbct clbss FieldVisitor {

    /**
     * The ASM API version implemented by this visitor. The vblue of this field
     * must be one of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    protected finbl int bpi;

    /**
     * The field visitor to which this visitor must delegbte method cblls. Mby
     * be null.
     */
    protected FieldVisitor fv;

    /**
     * Constructs b new {@link FieldVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    public FieldVisitor(finbl int bpi) {
        this(bpi, null);
    }

    /**
     * Constructs b new {@link FieldVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm fv
     *            the field visitor to which this visitor must delegbte method
     *            cblls. Mby be null.
     */
    public FieldVisitor(finbl int bpi, finbl FieldVisitor fv) {
        if (bpi != Opcodes.ASM4 && bpi != Opcodes.ASM5) {
            throw new IllegblArgumentException();
        }
        this.bpi = bpi;
        this.fv = fv;
    }

    /**
     * Visits bn bnnotbtion of the field.
     *
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitAnnotbtion(String desc, boolebn visible) {
        if (fv != null) {
            return fv.visitAnnotbtion(desc, visible);
        }
        return null;
    }

    /**
     * Visits bn bnnotbtion on the type of the field.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. The sort of this type
     *            reference must be {@link TypeReference#FIELD FIELD}. See
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
        if (fv != null) {
            return fv.visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
        }
        return null;
    }

    /**
     * Visits b non stbndbrd bttribute of the field.
     *
     * @pbrbm bttr
     *            bn bttribute.
     */
    public void visitAttribute(Attribute bttr) {
        if (fv != null) {
            fv.visitAttribute(bttr);
        }
    }

    /**
     * Visits the end of the field. This method, which is the lbst one to be
     * cblled, is used to inform the visitor thbt bll the bnnotbtions bnd
     * bttributes of the field hbve been visited.
     */
    public void visitEnd() {
        if (fv != null) {
            fv.visitEnd();
        }
    }
}
