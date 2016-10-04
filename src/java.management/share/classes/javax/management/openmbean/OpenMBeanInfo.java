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
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.MBebnConstructorInfo;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;



/**
 * <p>Describes bn Open MBebn: bn Open MBebn is recognized bs such if
 * its {@link jbvbx.mbnbgement.DynbmicMBebn#getMBebnInfo()
 * getMBebnInfo()} method returns bn instbnce of b clbss which
 * implements the {@link OpenMBebnInfo} interfbce, typicblly {@link
 * OpenMBebnInfoSupport}.</p>
 *
 * <p>This interfbce declbres the sbme methods bs the clbss {@link
 * jbvbx.mbnbgement.MBebnInfo}.  A clbss implementing this interfbce
 * (typicblly {@link OpenMBebnInfoSupport}) should extend {@link
 * jbvbx.mbnbgement.MBebnInfo}.</p>
 *
 * <p>The {@link #getAttributes()}, {@link #getOperbtions()} bnd
 * {@link #getConstructors()} methods of the implementing clbss should
 * return bt runtime bn brrby of instbnces of b subclbss of {@link
 * MBebnAttributeInfo}, {@link MBebnOperbtionInfo} or {@link
 * MBebnConstructorInfo} respectively which implement the {@link
 * OpenMBebnAttributeInfo}, {@link OpenMBebnOperbtionInfo} or {@link
 * OpenMBebnConstructorInfo} interfbce respectively.
 *
 *
 * @since 1.5
 */
public interfbce OpenMBebnInfo {

    // Re-declbres the methods thbt bre in clbss MBebnInfo of JMX 1.0
    // (methods will be removed when MBebnInfo is mbde b pbrent interfbce of this interfbce)

    /**
     * Returns the fully qublified Jbvb clbss nbme of the open MBebn
     * instbnces this <tt>OpenMBebnInfo</tt> describes.
     *
     * @return the clbss nbme.
     */
    public String getClbssNbme() ;

    /**
     * Returns b humbn rebdbble description of the type of open MBebn
     * instbnces this <tt>OpenMBebnInfo</tt> describes.
     *
     * @return the description.
     */
    public String getDescription() ;

    /**
     * Returns bn brrby of <tt>OpenMBebnAttributeInfo</tt> instbnces
     * describing ebch bttribute in the open MBebn described by this
     * <tt>OpenMBebnInfo</tt> instbnce.  Ebch instbnce in the returned
     * brrby should bctublly be b subclbss of
     * <tt>MBebnAttributeInfo</tt> which implements the
     * <tt>OpenMBebnAttributeInfo</tt> interfbce (typicblly {@link
     * OpenMBebnAttributeInfoSupport}).
     *
     * @return the bttribute brrby.
     */
    public MBebnAttributeInfo[] getAttributes() ;

    /**
     * Returns bn brrby of <tt>OpenMBebnOperbtionInfo</tt> instbnces
     * describing ebch operbtion in the open MBebn described by this
     * <tt>OpenMBebnInfo</tt> instbnce.  Ebch instbnce in the returned
     * brrby should bctublly be b subclbss of
     * <tt>MBebnOperbtionInfo</tt> which implements the
     * <tt>OpenMBebnOperbtionInfo</tt> interfbce (typicblly {@link
     * OpenMBebnOperbtionInfoSupport}).
     *
     * @return the operbtion brrby.
     */
    public MBebnOperbtionInfo[] getOperbtions() ;

    /**
     * Returns bn brrby of <tt>OpenMBebnConstructorInfo</tt> instbnces
     * describing ebch constructor in the open MBebn described by this
     * <tt>OpenMBebnInfo</tt> instbnce.  Ebch instbnce in the returned
     * brrby should bctublly be b subclbss of
     * <tt>MBebnConstructorInfo</tt> which implements the
     * <tt>OpenMBebnConstructorInfo</tt> interfbce (typicblly {@link
     * OpenMBebnConstructorInfoSupport}).
     *
     * @return the constructor brrby.
     */
    public MBebnConstructorInfo[] getConstructors() ;

    /**
     * Returns bn brrby of <tt>MBebnNotificbtionInfo</tt> instbnces
     * describing ebch notificbtion emitted by the open MBebn
     * described by this <tt>OpenMBebnInfo</tt> instbnce.
     *
     * @return the notificbtion brrby.
     */
    public MBebnNotificbtionInfo[] getNotificbtions() ;


    // commodity methods
    //

    /**
     * Compbres the specified <vbr>obj</vbr> pbrbmeter with this <code>OpenMBebnInfo</code> instbnce for equblity.
     * <p>
     * Returns <tt>true</tt> if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implements the <code>OpenMBebnInfo</code> interfbce,</li>
     * <li>their clbss nbmes bre equbl</li>
     * <li>their infos on bttributes, constructors, operbtions bnd notificbtions bre equbl</li>
     * </ul>
     * This ensures thbt this <tt>equbls</tt> method works properly for <vbr>obj</vbr> pbrbmeters which bre
     * different implementbtions of the <code>OpenMBebnInfo</code> interfbce.
     * <br>&nbsp;
     * @pbrbm  obj  the object to be compbred for equblity with this <code>OpenMBebnInfo</code> instbnce;
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>OpenMBebnInfo</code> instbnce.
     */
    public boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this <code>OpenMBebnInfo</code> instbnce.
     * <p>
     * The hbsh code of bn <code>OpenMBebnInfo</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: its clbss nbme, bnd its infos on bttributes, constructors, operbtions bnd notificbtions,
     * where the hbshCode of ebch of these brrbys is cblculbted by b cbll to
     *  <tt>new jbvb.util.HbshSet(jbvb.util.Arrbys.bsList(this.getSignbture)).hbshCode()</tt>).
     * <p>
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>OpenMBebnInfo</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     *
     * @return  the hbsh code vblue for this <code>OpenMBebnInfo</code> instbnce
     */
    public int hbshCode();

    /**
     * Returns b string representbtion of this <code>OpenMBebnInfo</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.OpenMBebnInfo</code>),
     * the MBebn clbss nbme,
     * bnd the string representbtion of infos on bttributes, constructors, operbtions bnd notificbtions of the described MBebn.
     *
     * @return  b string representbtion of this <code>OpenMBebnInfo</code> instbnce
     */
    public String toString();

}
