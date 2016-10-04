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
import jbvbx.mbnbgement.MBebnPbrbmeterInfo;


/**
 * <p>Describes b constructor of bn Open MBebn.</p>
 *
 * <p>This interfbce declbres the sbme methods bs the clbss {@link
 * jbvbx.mbnbgement.MBebnConstructorInfo}.  A clbss implementing this
 * interfbce (typicblly {@link OpenMBebnConstructorInfoSupport})
 * should extend {@link jbvbx.mbnbgement.MBebnConstructorInfo}.</p>
 *
 * <p>The {@link #getSignbture()} method should return bt runtime bn
 * brrby of instbnces of b subclbss of {@link MBebnPbrbmeterInfo}
 * which implements the {@link OpenMBebnPbrbmeterInfo} interfbce
 * (typicblly {@link OpenMBebnPbrbmeterInfoSupport}).</p>
 *
 *
 *
 * @since 1.5
 */
public interfbce OpenMBebnConstructorInfo {

    // Re-declbres the methods thbt bre in clbss MBebnConstructorInfo of JMX 1.0
    // (methods will be removed when MBebnConstructorInfo is mbde b pbrent interfbce of this interfbce)

    /**
     * Returns b humbn rebdbble description of the constructor
     * described by this <tt>OpenMBebnConstructorInfo</tt> instbnce.
     *
     * @return the description.
     */
    public String getDescription() ;

    /**
     * Returns the nbme of the constructor
     * described by this <tt>OpenMBebnConstructorInfo</tt> instbnce.
     *
     * @return the nbme.
     */
    public String getNbme() ;

    /**
     * Returns bn brrby of <tt>OpenMBebnPbrbmeterInfo</tt> instbnces
     * describing ebch pbrbmeter in the signbture of the constructor
     * described by this <tt>OpenMBebnConstructorInfo</tt> instbnce.
     *
     * @return the signbture.
     */
    public MBebnPbrbmeterInfo[] getSignbture() ;


    // commodity methods
    //

    /**
     * Compbres the specified <vbr>obj</vbr> pbrbmeter with this <code>OpenMBebnConstructorInfo</code> instbnce for equblity.
     * <p>
     * Returns <tt>true</tt> if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implements the <code>OpenMBebnConstructorInfo</code> interfbce,</li>
     * <li>their nbmes bre equbl</li>
     * <li>their signbtures bre equbl.</li>
     * </ul>
     * This ensures thbt this <tt>equbls</tt> method works properly for <vbr>obj</vbr> pbrbmeters which bre
     * different implementbtions of the <code>OpenMBebnConstructorInfo</code> interfbce.
     * <br>&nbsp;
     * @pbrbm  obj  the object to be compbred for equblity with this <code>OpenMBebnConstructorInfo</code> instbnce;
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>OpenMBebnConstructorInfo</code> instbnce.
     */
    public boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this <code>OpenMBebnConstructorInfo</code> instbnce.
     * <p>
     * The hbsh code of bn <code>OpenMBebnConstructorInfo</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: its nbme bnd signbture, where the signbture hbshCode is cblculbted by b cbll to
     *  <tt>jbvb.util.Arrbys.bsList(this.getSignbture).hbshCode()</tt>).
     * <p>
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>OpenMBebnConstructorInfo</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     *
     * @return  the hbsh code vblue for this <code>OpenMBebnConstructorInfo</code> instbnce
     */
    public int hbshCode();

    /**
     * Returns b string representbtion of this <code>OpenMBebnConstructorInfo</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.OpenMBebnConstructorInfo</code>),
     * bnd the nbme bnd signbture of the described constructor.
     *
     * @return  b string representbtion of this <code>OpenMBebnConstructorInfo</code> instbnce
     */
    public String toString();

}
