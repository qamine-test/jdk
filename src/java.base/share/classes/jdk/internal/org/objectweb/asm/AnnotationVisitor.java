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
 * A visitor to visit b Jbvb bnnotbtion. The methods of this clbss must be
 * cblled in the following order: ( <tt>visit</tt> | <tt>visitEnum</tt> |
 * <tt>visitAnnotbtion</tt> | <tt>visitArrby</tt> )* <tt>visitEnd</tt>.
 *
 * @buthor Eric Bruneton
 * @buthor Eugene Kuleshov
 */
public bbstrbct clbss AnnotbtionVisitor {

    /**
     * The ASM API version implemented by this visitor. The vblue of this field
     * must be one of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    protected finbl int bpi;

    /**
     * The bnnotbtion visitor to which this visitor must delegbte method cblls.
     * Mby be null.
     */
    protected AnnotbtionVisitor bv;

    /**
     * Constructs b new {@link AnnotbtionVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    public AnnotbtionVisitor(finbl int bpi) {
        this(bpi, null);
    }

    /**
     * Constructs b new {@link AnnotbtionVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm bv
     *            the bnnotbtion visitor to which this visitor must delegbte
     *            method cblls. Mby be null.
     */
    public AnnotbtionVisitor(finbl int bpi, finbl AnnotbtionVisitor bv) {
        if (bpi != Opcodes.ASM4 && bpi != Opcodes.ASM5) {
            throw new IllegblArgumentException();
        }
        this.bpi = bpi;
        this.bv = bv;
    }

    /**
     * Visits b primitive vblue of the bnnotbtion.
     *
     * @pbrbm nbme
     *            the vblue nbme.
     * @pbrbm vblue
     *            the bctubl vblue, whose type must be {@link Byte},
     *            {@link Boolebn}, {@link Chbrbcter}, {@link Short},
     *            {@link Integer} , {@link Long}, {@link Flobt}, {@link Double},
     *            {@link String} or {@link Type} or OBJECT or ARRAY sort. This
     *            vblue cbn blso be bn brrby of byte, boolebn, short, chbr, int,
     *            long, flobt or double vblues (this is equivblent to using
     *            {@link #visitArrby visitArrby} bnd visiting ebch brrby element
     *            in turn, but is more convenient).
     */
    public void visit(String nbme, Object vblue) {
        if (bv != null) {
            bv.visit(nbme, vblue);
        }
    }

    /**
     * Visits bn enumerbtion vblue of the bnnotbtion.
     *
     * @pbrbm nbme
     *            the vblue nbme.
     * @pbrbm desc
     *            the clbss descriptor of the enumerbtion clbss.
     * @pbrbm vblue
     *            the bctubl enumerbtion vblue.
     */
    public void visitEnum(String nbme, String desc, String vblue) {
        if (bv != null) {
            bv.visitEnum(nbme, desc, vblue);
        }
    }

    /**
     * Visits b nested bnnotbtion vblue of the bnnotbtion.
     *
     * @pbrbm nbme
     *            the vblue nbme.
     * @pbrbm desc
     *            the clbss descriptor of the nested bnnotbtion clbss.
     * @return b visitor to visit the bctubl nested bnnotbtion vblue, or
     *         <tt>null</tt> if this visitor is not interested in visiting this
     *         nested bnnotbtion. <i>The nested bnnotbtion vblue must be fully
     *         visited before cblling other methods on this bnnotbtion
     *         visitor</i>.
     */
    public AnnotbtionVisitor visitAnnotbtion(String nbme, String desc) {
        if (bv != null) {
            return bv.visitAnnotbtion(nbme, desc);
        }
        return null;
    }

    /**
     * Visits bn brrby vblue of the bnnotbtion. Note thbt brrbys of primitive
     * types (such bs byte, boolebn, short, chbr, int, long, flobt or double)
     * cbn be pbssed bs vblue to {@link #visit visit}. This is whbt
     * {@link ClbssRebder} does.
     *
     * @pbrbm nbme
     *            the vblue nbme.
     * @return b visitor to visit the bctubl brrby vblue elements, or
     *         <tt>null</tt> if this visitor is not interested in visiting these
     *         vblues. The 'nbme' pbrbmeters pbssed to the methods of this
     *         visitor bre ignored. <i>All the brrby vblues must be visited
     *         before cblling other methods on this bnnotbtion visitor</i>.
     */
    public AnnotbtionVisitor visitArrby(String nbme) {
        if (bv != null) {
            return bv.visitArrby(nbme);
        }
        return null;
    }

    /**
     * Visits the end of the bnnotbtion.
     */
    public void visitEnd() {
        if (bv != null) {
            bv.visitEnd();
        }
    }
}
