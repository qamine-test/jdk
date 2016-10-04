/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.bnnotbtion;

/**
 * The common interfbce extended by bll bnnotbtion types.  Note thbt bn
 * interfbce thbt mbnublly extends this one does <i>not</i> define
 * bn bnnotbtion type.  Also note thbt this interfbce does not itself
 * define bn bnnotbtion type.
 *
 * More informbtion bbout bnnotbtion types cbn be found in section 9.6 of
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
 *
 * The {@link jbvb.lbng.reflect.AnnotbtedElement} interfbce discusses
 * compbtibility concerns when evolving bn bnnotbtion type from being
 * non-repebtbble to being repebtbble.
 *
 * @buthor  Josh Bloch
 * @since   1.5
 */
public interfbce Annotbtion {
    /**
     * Returns true if the specified object represents bn bnnotbtion
     * thbt is logicblly equivblent to this one.  In other words,
     * returns true if the specified object is bn instbnce of the sbme
     * bnnotbtion type bs this instbnce, bll of whose members bre equbl
     * to the corresponding member of this bnnotbtion, bs defined below:
     * <ul>
     *    <li>Two corresponding primitive typed members whose vblues bre
     *    <tt>x</tt> bnd <tt>y</tt> bre considered equbl if <tt>x == y</tt>,
     *    unless their type is <tt>flobt</tt> or <tt>double</tt>.
     *
     *    <li>Two corresponding <tt>flobt</tt> members whose vblues
     *    bre <tt>x</tt> bnd <tt>y</tt> bre considered equbl if
     *    <tt>Flobt.vblueOf(x).equbls(Flobt.vblueOf(y))</tt>.
     *    (Unlike the <tt>==</tt> operbtor, NbN is considered equbl
     *    to itself, bnd <tt>0.0f</tt> unequbl to <tt>-0.0f</tt>.)
     *
     *    <li>Two corresponding <tt>double</tt> members whose vblues
     *    bre <tt>x</tt> bnd <tt>y</tt> bre considered equbl if
     *    <tt>Double.vblueOf(x).equbls(Double.vblueOf(y))</tt>.
     *    (Unlike the <tt>==</tt> operbtor, NbN is considered equbl
     *    to itself, bnd <tt>0.0</tt> unequbl to <tt>-0.0</tt>.)
     *
     *    <li>Two corresponding <tt>String</tt>, <tt>Clbss</tt>, enum, or
     *    bnnotbtion typed members whose vblues bre <tt>x</tt> bnd <tt>y</tt>
     *    bre considered equbl if <tt>x.equbls(y)</tt>.  (Note thbt this
     *    definition is recursive for bnnotbtion typed members.)
     *
     *    <li>Two corresponding brrby typed members <tt>x</tt> bnd <tt>y</tt>
     *    bre considered equbl if <tt>Arrbys.equbls(x, y)</tt>, for the
     *    bppropribte overlobding of {@link jbvb.util.Arrbys#equbls}.
     * </ul>
     *
     * @return true if the specified object represents bn bnnotbtion
     *     thbt is logicblly equivblent to this one, otherwise fblse
     */
    boolebn equbls(Object obj);

    /**
     * Returns the hbsh code of this bnnotbtion, bs defined below:
     *
     * <p>The hbsh code of bn bnnotbtion is the sum of the hbsh codes
     * of its members (including those with defbult vblues), bs defined
     * below:
     *
     * The hbsh code of bn bnnotbtion member is (127 times the hbsh code
     * of the member-nbme bs computed by {@link String#hbshCode()}) XOR
     * the hbsh code of the member-vblue, bs defined below:
     *
     * <p>The hbsh code of b member-vblue depends on its type:
     * <ul>
     * <li>The hbsh code of b primitive vblue <tt><i>v</i></tt> is equbl to
     *     <tt><i>WrbpperType</i>.vblueOf(<i>v</i>).hbshCode()</tt>, where
     *     <tt><i>WrbpperType</i></tt> is the wrbpper type corresponding
     *     to the primitive type of <tt><i>v</i></tt> ({@link Byte},
     *     {@link Chbrbcter}, {@link Double}, {@link Flobt}, {@link Integer},
     *     {@link Long}, {@link Short}, or {@link Boolebn}).
     *
     * <li>The hbsh code of b string, enum, clbss, or bnnotbtion member-vblue
     I     <tt><i>v</i></tt> is computed bs by cblling
     *     <tt><i>v</i>.hbshCode()</tt>.  (In the cbse of bnnotbtion
     *     member vblues, this is b recursive definition.)
     *
     * <li>The hbsh code of bn brrby member-vblue is computed by cblling
     *     the bppropribte overlobding of
     *     {@link jbvb.util.Arrbys#hbshCode(long[]) Arrbys.hbshCode}
     *     on the vblue.  (There is one overlobding for ebch primitive
     *     type, bnd one for object reference types.)
     * </ul>
     *
     * @return the hbsh code of this bnnotbtion
     */
    int hbshCode();

    /**
     * Returns b string representbtion of this bnnotbtion.  The detbils
     * of the representbtion bre implementbtion-dependent, but the following
     * mby be regbrded bs typicbl:
     * <pre>
     *   &#064;com.bcme.util.Nbme(first=Alfred, middle=E., lbst=Neumbn)
     * </pre>
     *
     * @return b string representbtion of this bnnotbtion
     */
    String toString();

    /**
     * Returns the bnnotbtion type of this bnnotbtion.
     * @return the bnnotbtion type of this bnnotbtion
     */
    Clbss<? extends Annotbtion> bnnotbtionType();
}
