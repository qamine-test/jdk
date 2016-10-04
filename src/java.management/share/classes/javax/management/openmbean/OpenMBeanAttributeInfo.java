/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.openmbebn;


// jbvb import
//


// jmx import
//


/**
 * <p>Describes bn bttribute of bn open MBebn.</p>
 *
 * <p>This interfbce declbres the sbme methods bs the clbss {@link
 * jbvbx.mbnbgement.MBebnAttributeInfo}.  A clbss implementing this
 * interfbce (typicblly {@link OpenMBebnAttributeInfoSupport}) should
 * extend {@link jbvbx.mbnbgement.MBebnAttributeInfo}.</p>
 *
 *
 * @since 1.5
 */
public interfbce OpenMBebnAttributeInfo extends OpenMBebnPbrbmeterInfo {


    // Re-declbres the methods thbt bre in clbss MBebnAttributeInfo of JMX 1.0
    // (these will be removed when MBebnAttributeInfo is mbde b pbrent interfbce of this interfbce)

    /**
     * Returns <tt>true</tt> if the bttribute described by this <tt>OpenMBebnAttributeInfo</tt> instbnce is rebdbble,
     * <tt>fblse</tt> otherwise.
     *
     * @return true if the bttribute is rebdbble.
     */
    public boolebn isRebdbble() ;

    /**
     * Returns <tt>true</tt> if the bttribute described by this <tt>OpenMBebnAttributeInfo</tt> instbnce is writbble,
     * <tt>fblse</tt> otherwise.
     *
     * @return true if the bttribute is writbble.
     */
    public boolebn isWritbble() ;

    /**
     * Returns <tt>true</tt> if the bttribute described by this <tt>OpenMBebnAttributeInfo</tt> instbnce
     * is bccessed through b <tt>is<i>XXX</i></tt> getter (bpplies only to <tt>boolebn</tt> bnd <tt>Boolebn</tt> vblues),
     * <tt>fblse</tt> otherwise.
     *
     * @return true if the bttribute is bccessed through <tt>is<i>XXX</i></tt>.
     */
    public boolebn isIs() ;


    // commodity methods
    //

    /**
     * Compbres the specified <vbr>obj</vbr> pbrbmeter with this <code>OpenMBebnAttributeInfo</code> instbnce for equblity.
     * <p>
     * Returns <tt>true</tt> if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implements the <code>OpenMBebnAttributeInfo</code> interfbce,</li>
     * <li>their nbmes bre equbl</li>
     * <li>their open types bre equbl</li>
     * <li>their bccess properties (isRebdbble, isWritbble bnd isIs) bre equbl</li>
     * <li>their defbult, min, mbx bnd legbl vblues bre equbl.</li>
     * </ul>
     * This ensures thbt this <tt>equbls</tt> method works properly for <vbr>obj</vbr> pbrbmeters which bre
     * different implementbtions of the <code>OpenMBebnAttributeInfo</code> interfbce.
     * <br>&nbsp;
     * @pbrbm  obj  the object to be compbred for equblity with this <code>OpenMBebnAttributeInfo</code> instbnce;
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>OpenMBebnAttributeInfo</code> instbnce.
     */
    public boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this <code>OpenMBebnAttributeInfo</code> instbnce.
     * <p>
     * The hbsh code of bn <code>OpenMBebnAttributeInfo</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: its nbme, its <i>open type</i>, bnd its defbult, min, mbx bnd legbl vblues).
     * <p>
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>OpenMBebnAttributeInfo</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     *
     * @return  the hbsh code vblue for this <code>OpenMBebnAttributeInfo</code> instbnce
     */
    public int hbshCode();

    /**
     * Returns b string representbtion of this <code>OpenMBebnAttributeInfo</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.OpenMBebnAttributeInfo</code>),
     * the string representbtion of the nbme bnd open type of the described bttribute,
     * bnd the string representbtion of its defbult, min, mbx bnd legbl vblues.
     *
     * @return  b string representbtion of this <code>OpenMBebnAttributeInfo</code> instbnce
     */
    public String toString();


    // methods specific to open MBebns bre inherited from
    // OpenMBebnPbrbmeterInfo
    //

}
