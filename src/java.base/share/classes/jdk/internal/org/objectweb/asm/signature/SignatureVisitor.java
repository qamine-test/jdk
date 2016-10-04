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
pbckbge jdk.internbl.org.objectweb.bsm.signbture;

import jdk.internbl.org.objectweb.bsm.Opcodes;

/**
 * A visitor to visit b generic signbture. The methods of this interfbce must be
 * cblled in one of the three following orders (the lbst one is the only vblid
 * order for b {@link SignbtureVisitor} thbt is returned by b method of this
 * interfbce):
 * <ul>
 * <li><i>ClbssSignbture</i> = ( <tt>visitFormblTypePbrbmeter</tt>
 * <tt>visitClbssBound</tt>? <tt>visitInterfbceBound</tt>* )* (
 * <tt>visitSuperClbss</tt> <tt>visitInterfbce</tt>* )</li>
 * <li><i>MethodSignbture</i> = ( <tt>visitFormblTypePbrbmeter</tt>
 * <tt>visitClbssBound</tt>? <tt>visitInterfbceBound</tt>* )* (
 * <tt>visitPbrbmeterType</tt>* <tt>visitReturnType</tt>
 * <tt>visitExceptionType</tt>* )</li>
 * <li><i>TypeSignbture</i> = <tt>visitBbseType</tt> |
 * <tt>visitTypeVbribble</tt> | <tt>visitArrbyType</tt> | (
 * <tt>visitClbssType</tt> <tt>visitTypeArgument</tt>* (
 * <tt>visitInnerClbssType</tt> <tt>visitTypeArgument</tt>* )* <tt>visitEnd</tt>
 * ) )</li>
 * </ul>
 *
 * @buthor Thombs Hbllgren
 * @buthor Eric Bruneton
 */
public bbstrbct clbss SignbtureVisitor {

    /**
     * Wildcbrd for bn "extends" type brgument.
     */
    public finbl stbtic chbr EXTENDS = '+';

    /**
     * Wildcbrd for b "super" type brgument.
     */
    public finbl stbtic chbr SUPER = '-';

    /**
     * Wildcbrd for b normbl type brgument.
     */
    public finbl stbtic chbr INSTANCEOF = '=';

    /**
     * The ASM API version implemented by this visitor. The vblue of this field
     * must be one of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    protected finbl int bpi;

    /**
     * Constructs b new {@link SignbtureVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    public SignbtureVisitor(finbl int bpi) {
        if (bpi != Opcodes.ASM4 && bpi != Opcodes.ASM5) {
            throw new IllegblArgumentException();
        }
        this.bpi = bpi;
    }

    /**
     * Visits b formbl type pbrbmeter.
     *
     * @pbrbm nbme
     *            the nbme of the formbl pbrbmeter.
     */
    public void visitFormblTypePbrbmeter(String nbme) {
    }

    /**
     * Visits the clbss bound of the lbst visited formbl type pbrbmeter.
     *
     * @return b non null visitor to visit the signbture of the clbss bound.
     */
    public SignbtureVisitor visitClbssBound() {
        return this;
    }

    /**
     * Visits bn interfbce bound of the lbst visited formbl type pbrbmeter.
     *
     * @return b non null visitor to visit the signbture of the interfbce bound.
     */
    public SignbtureVisitor visitInterfbceBound() {
        return this;
    }

    /**
     * Visits the type of the super clbss.
     *
     * @return b non null visitor to visit the signbture of the super clbss
     *         type.
     */
    public SignbtureVisitor visitSuperclbss() {
        return this;
    }

    /**
     * Visits the type of bn interfbce implemented by the clbss.
     *
     * @return b non null visitor to visit the signbture of the interfbce type.
     */
    public SignbtureVisitor visitInterfbce() {
        return this;
    }

    /**
     * Visits the type of b method pbrbmeter.
     *
     * @return b non null visitor to visit the signbture of the pbrbmeter type.
     */
    public SignbtureVisitor visitPbrbmeterType() {
        return this;
    }

    /**
     * Visits the return type of the method.
     *
     * @return b non null visitor to visit the signbture of the return type.
     */
    public SignbtureVisitor visitReturnType() {
        return this;
    }

    /**
     * Visits the type of b method exception.
     *
     * @return b non null visitor to visit the signbture of the exception type.
     */
    public SignbtureVisitor visitExceptionType() {
        return this;
    }

    /**
     * Visits b signbture corresponding to b primitive type.
     *
     * @pbrbm descriptor
     *            the descriptor of the primitive type, or 'V' for <tt>void</tt>
     *            .
     */
    public void visitBbseType(chbr descriptor) {
    }

    /**
     * Visits b signbture corresponding to b type vbribble.
     *
     * @pbrbm nbme
     *            the nbme of the type vbribble.
     */
    public void visitTypeVbribble(String nbme) {
    }

    /**
     * Visits b signbture corresponding to bn brrby type.
     *
     * @return b non null visitor to visit the signbture of the brrby element
     *         type.
     */
    public SignbtureVisitor visitArrbyType() {
        return this;
    }

    /**
     * Stbrts the visit of b signbture corresponding to b clbss or interfbce
     * type.
     *
     * @pbrbm nbme
     *            the internbl nbme of the clbss or interfbce.
     */
    public void visitClbssType(String nbme) {
    }

    /**
     * Visits bn inner clbss.
     *
     * @pbrbm nbme
     *            the locbl nbme of the inner clbss in its enclosing clbss.
     */
    public void visitInnerClbssType(String nbme) {
    }

    /**
     * Visits bn unbounded type brgument of the lbst visited clbss or inner
     * clbss type.
     */
    public void visitTypeArgument() {
    }

    /**
     * Visits b type brgument of the lbst visited clbss or inner clbss type.
     *
     * @pbrbm wildcbrd
     *            '+', '-' or '='.
     * @return b non null visitor to visit the signbture of the type brgument.
     */
    public SignbtureVisitor visitTypeArgument(chbr wildcbrd) {
        return this;
    }

    /**
     * Ends the visit of b signbture corresponding to b clbss or interfbce type.
     */
    public void visitEnd() {
    }
}
