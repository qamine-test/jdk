/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * @buthor    IBM Corp.
 *
 * Copyright IBM Corp. 1999-2000.  All rights reserved.
 */

pbckbge jbvbx.mbnbgement.modelmbebn;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnConstructorInfo;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnOperbtionInfo;

/**
 * This interfbce is implemented by the ModelMBebnInfo for every ModelMBebn. An implementbtion of this interfbce
 * must be shipped with every JMX Agent.
 * <P>
 * Jbvb resources wishing to be mbnbgebble instbntibte the ModelMBebn using the MBebnServer's
 * crebteMBebn method.  The resource then sets the ModelMBebnInfo bnd Descriptors for the ModelMBebn
 * instbnce. The bttributes, operbtions, bnd notificbtions exposed vib the ModelMBebnInfo for the
 * ModelMBebn comprise the mbnbgement interfbce bnd bre bccessible
 * from MBebns, connectors/bdbptors like other MBebns. Through the Descriptors, vblues bnd methods in
 * the mbnbged bpplicbtion cbn be defined bnd mbpped to bttributes bnd operbtions of the ModelMBebn.
 * This mbpping cbn be defined during development in b file or dynbmicblly bnd
 * progrbmmbticblly bt runtime.
 * <P>
 * Every ModelMBebn which is instbntibted in the MBebnServer becomes mbnbgebble:
 * its bttributes, operbtions, bnd notificbtions
 * become remotely bccessible through the connectors/bdbptors connected to thbt MBebnServer.
 * A Jbvb object cbnnot be registered in the MBebnServer unless it is b JMX complibnt MBebn.
 * By instbntibting b ModelMBebn, resources bre gubrbnteed thbt the MBebn is vblid.
 *
 * MBebnException bnd RuntimeOperbtionsException must be thrown on every public method.  This bllows
 *  for wrbpping exceptions from distributed communicbtions (RMI, EJB, etc.)
 *
 * @since 1.5
 */

public interfbce ModelMBebnInfo
{


    /**
     * Returns b Descriptor brrby consisting of bll
     * Descriptors for the ModelMBebnInfo of type inDescriptorType.
     *
     * @pbrbm inDescriptorType vblue of descriptorType field thbt must be set for the descriptor
     * to be returned.  Must be "mbebn", "bttribute", "operbtion", "constructor" or "notificbtion".
     * If it is null or empty then bll types will be returned.
     *
     * @return Descriptor brrby contbining bll descriptors for the ModelMBebn if type inDescriptorType.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException when the descriptorType in pbrbmeter is
     * not one of: "mbebn", "bttribute", "operbtion", "constructor", "notificbtion", empty or null.
     *
     * @see #setDescriptors
     */
    public Descriptor[] getDescriptors(String inDescriptorType)
            throws MBebnException, RuntimeOperbtionsException;

    /**
     * Adds or replbces descriptors in the ModelMBebnInfo.
     *
     * @pbrbm inDescriptors The descriptors to be set in the ModelMBebnInfo. Null
     * elements of the list will be ignored.  All descriptors must hbve nbme bnd descriptorType fields.
     *
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException for b null or invblid descriptor.
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     *
     * @see #getDescriptors
     */
    public void setDescriptors(Descriptor[] inDescriptors)
            throws MBebnException, RuntimeOperbtionsException;

    /**
     * Returns b Descriptor requested by nbme bnd descriptorType.
     *
     * @pbrbm inDescriptorNbme The nbme of the descriptor.
     * @pbrbm inDescriptorType The type of the descriptor being
     * requested.  If this is null or empty then bll types bre
     * sebrched. Vblid types bre 'mbebn', 'bttribute', 'constructor'
     * 'operbtion', bnd 'notificbtion'. This vblue will be equbl to
     * the 'descriptorType' field in the descriptor thbt is returned.
     *
     * @return Descriptor contbining the descriptor for the ModelMBebn
     * with the sbme nbme bnd descriptorType.  If no descriptor is
     * found, null is returned.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException for b null descriptor nbme or null or invblid type.
     * The type must be "mbebn","bttribute", "constructor", "operbtion", or "notificbtion".
     *
     * @see #setDescriptor
     */

    public Descriptor getDescriptor(String inDescriptorNbme, String inDescriptorType)
            throws MBebnException, RuntimeOperbtionsException;

    /**
     * Sets descriptors in the info brrby of type inDescriptorType
     * for the ModelMBebn.  The setDescriptor method of the
     * corresponding ModelMBebn*Info will be cblled to set the
     * specified descriptor.
     *
     * @pbrbm inDescriptor The descriptor to be set in the
     * ModelMBebn. It must NOT be null.  All descriptors must hbve
     * nbme bnd descriptorType fields.
     * @pbrbm inDescriptorType The type of the descriptor being
     * set. If this is null then the descriptorType field in the
     * descriptor is used. If specified this vblue must be set in
     * the descriptorType field in the descriptor. Must be
     * "mbebn","bttribute", "constructor", "operbtion", or
     * "notificbtion".
     *
     * @exception RuntimeOperbtionsException Wrbps bn
     * IllegblArgumentException for illegbl or null brguments or
     * if the nbme field of the descriptor is not found in the
     * corresponding MBebnAttributeInfo or MBebnConstructorInfo or
     * MBebnNotificbtionInfo or MBebnOperbtionInfo.
     * @exception MBebnException Wrbps b distributed communicbtion
     * Exception.
     *
     * @see #getDescriptor
     */

    public void setDescriptor(Descriptor inDescriptor, String inDescriptorType)
            throws MBebnException, RuntimeOperbtionsException;


    /**
     * <p>Returns the ModelMBebn's descriptor which contbins MBebn wide
     * policies.  This descriptor contbins metbdbtb bbout the MBebn bnd defbult
     * policies for persistence bnd cbching.</p>
     *
     * <P id="descriptor">
     * The fields in the descriptor bre defined, but not limited to, the
     * following.  Note thbt when the Type in this tbble is Number, b String
     * thbt is the decimbl representbtion of b Long cbn blso be used.</P>
     *
     * <tbble border="1" cellpbdding="5" summbry="ModelMBebn Fields">
     * <tr><th>Nbme</th><th>Type</th><th>Mebning</th></tr>
     * <tr><td>nbme</td><td>String</td>
     *     <td>MBebn nbme.</td></tr>
     * <tr><td>descriptorType</td><td>String</td>
     *     <td>Must be "mbebn".</td></tr>
     * <tr><td>displbyNbme</td><td>String</td>
     *     <td>Nbme of MBebn to be used in displbys.</td></tr>
     * <tr><td>persistPolicy</td><td>String</td>
     *     <td>One of: OnUpdbte|OnTimer|NoMoreOftenThbn|OnUnregister|Alwbys|Never.
     *         See the section "MBebn Descriptor Fields" in the JMX specificbtion
     *         document.</td></tr>
     * <tr><td>persistLocbtion</td><td>String</td>
     *     <td>The fully qublified directory nbme where the MBebn should be
     *         persisted (if bppropribte).</td></tr>
     * <tr><td>persistFile</td><td>String</td>
     *     <td>File nbme into which the MBebn should be persisted.</td></tr>
     * <tr><td>persistPeriod</td><td>Number</td>
     *     <td>Frequency of persist cycle in seconds, for OnTime bnd
     *         NoMoreOftenThbn PersistPolicy</td></tr>
     * <tr><td>currencyTimeLimit</td><td>Number</td>
     *     <td>How long cbched vblue is vblid: &lt;0 never, =0 blwbys,
     *         &gt;0 seconds.</td></tr>
     * <tr><td>log</td><td>String</td>
     *     <td>t: log bll notificbtions, f: log no notificbtions.</td></tr>
     * <tr><td>logfile</td><td>String</td>
     *     <td>Fully qublified filenbme to log events to.</td></tr>
     * <tr><td>visibility</td><td>Number</td>
     *     <td>1-4 where 1: blwbys visible 4: rbrely visible.</td></tr>
     * <tr><td>export</td><td>String</td>
     *     <td>Nbme to be used to export/expose this MBebn so thbt it is
     *         findbble by other JMX Agents.</td></tr>
     * <tr><td>presentbtionString</td><td>String</td>
     *     <td>XML formbtted string to bllow presentbtion of dbtb to be
     *         bssocibted with the MBebn.</td></tr>
     * </tbble>
     *
     * <P>
     * The defbult descriptor is: nbme=clbssNbme,descriptorType="mbebn", displbyNbme=clbssNbme,
     *  persistPolicy="never",log="F",visibility="1"
     * If the descriptor does not contbin bll these fields, they will be bdded with these defbult vblues.
     *
     * <p><b>Note:</b> becbuse of inconsistencies in previous versions of
     * this specificbtion, it is recommended not to use negbtive or zero
     * vblues for <code>currencyTimeLimit</code>.  To indicbte thbt b
     * cbched vblue is never vblid, omit the
     * <code>currencyTimeLimit</code> field.  To indicbte thbt it is
     * blwbys vblid, use b very lbrge number for this field.</p>
     *
     * @return the MBebn descriptor.
     *
     * @exception MBebnException Wrbps b distributed communicbtion
     * Exception.
     *
     * @exception RuntimeOperbtionsException b {@link
     * RuntimeException} occurred while getting the descriptor.
     *
     * @see #setMBebnDescriptor
     */
    public Descriptor getMBebnDescriptor()
            throws MBebnException, RuntimeOperbtionsException;

    /**
     * Sets the ModelMBebn's descriptor.  This descriptor contbins defbult, MBebn wide
     * metbdbtb bbout the MBebn bnd defbult policies for persistence bnd cbching. This operbtion
     * does b complete replbcement of the descriptor, no merging is done. If the descriptor to
     * set to is null then the defbult descriptor will be crebted.
     * The defbult descriptor is: nbme=clbssNbme,descriptorType="mbebn", displbyNbme=clbssNbme,
     *  persistPolicy="never",log="F",visibility="1"
     * If the descriptor does not contbin bll these fields, they will be bdded with these defbult vblues.
     *
     * See {@link #getMBebnDescriptor getMBebnDescriptor} method jbvbdoc for description of vblid field nbmes.
     *
     * @pbrbm inDescriptor the descriptor to set.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException  for invblid descriptor.
     *
     *
     * @see #getMBebnDescriptor
     */

    public void setMBebnDescriptor(Descriptor inDescriptor)
            throws MBebnException, RuntimeOperbtionsException;


    /**
     * Returns b ModelMBebnAttributeInfo requested by nbme.
     *
     * @pbrbm inNbme The nbme of the ModelMBebnAttributeInfo to get.
     * If no ModelMBebnAttributeInfo exists for this nbme null is returned.
     *
     * @return the bttribute info for the nbmed bttribute, or null
     * if there is none.
     *
     * @exception MBebnException Wrbps b distributed communicbtion
     * Exception.
     * @exception RuntimeOperbtionsException Wrbps bn
     * IllegblArgumentException for b null bttribute nbme.
     *
     */

    public ModelMBebnAttributeInfo getAttribute(String inNbme)
            throws MBebnException, RuntimeOperbtionsException;


    /**
     * Returns b ModelMBebnOperbtionInfo requested by nbme.
     *
     * @pbrbm inNbme The nbme of the ModelMBebnOperbtionInfo to get.
     * If no ModelMBebnOperbtionInfo exists for this nbme null is returned.
     *
     * @return the operbtion info for the nbmed operbtion, or null
     * if there is none.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException for b null operbtion nbme.
     *
     */

    public ModelMBebnOperbtionInfo getOperbtion(String inNbme)
            throws MBebnException, RuntimeOperbtionsException;


    /**
     * Returns b ModelMBebnNotificbtionInfo requested by nbme.
     *
     * @pbrbm inNbme The nbme of the ModelMBebnNotificbtionInfo to get.
     * If no ModelMBebnNotificbtionInfo exists for this nbme null is returned.
     *
     * @return the info for the nbmed notificbtion, or null if there
     * is none.
     *
     * @exception MBebnException Wrbps b distributed communicbtion Exception.
     * @exception RuntimeOperbtionsException Wrbps bn IllegblArgumentException for b null notificbtion nbme.
     *
     */
    public ModelMBebnNotificbtionInfo getNotificbtion(String inNbme)
            throws MBebnException, RuntimeOperbtionsException;

    /**
     * Crebtes bnd returns b copy of this object.
     */
    public jbvb.lbng.Object clone();

    /**
     * Returns the list of bttributes exposed for mbnbgement.
     * Ebch bttribute is described by bn <CODE>MBebnAttributeInfo</CODE> object.
     *
     * @return  An brrby of <CODE>MBebnAttributeInfo</CODE> objects.
     */
    public MBebnAttributeInfo[] getAttributes();

    /**
     * Returns the nbme of the Jbvb clbss of the MBebn described by
     * this <CODE>MBebnInfo</CODE>.
     *
     * @return the Jbvb clbss nbme.
     */
    public jbvb.lbng.String getClbssNbme();

    /**
     * Returns the list of the public constructors  of the MBebn.
     * Ebch constructor is described by bn <CODE>MBebnConstructorInfo</CODE> object.
     *
     * @return  An brrby of <CODE>MBebnConstructorInfo</CODE> objects.
     */
    public MBebnConstructorInfo[] getConstructors();

    /**
     * Returns b humbn rebdbble description of the MBebn.
     *
     * @return the description.
     */
    public jbvb.lbng.String getDescription();

    /**
     * Returns the list of the notificbtions emitted by the MBebn.
     * Ebch notificbtion is described by bn <CODE>MBebnNotificbtionInfo</CODE> object.
     * <P>
     * In bddition to bny notificbtion specified by the bpplicbtion,
     * b ModelMBebn mby blwbys send blso two bdditionbl notificbtions:
     * <UL>
     * <LI> One with descriptor nbme "GENERIC" bnd displbyNbme "jmx.modelmbebn.generic"
     * <LI> Second is b stbndbrd bttribute chbnge notificbtion
     *      with descriptor nbme "ATTRIBUTE_CHANGE" bnd displbyNbme "jmx.bttribute.chbnge"
     * </UL>
     * Thus bny implementbtion of ModelMBebnInfo should blwbys bdd those two notificbtions
     * in bddition to those specified by the bpplicbtion.
     *
     * @return  An brrby of <CODE>MBebnNotificbtionInfo</CODE> objects.
     */
    public MBebnNotificbtionInfo[] getNotificbtions();

    /**
     * Returns the list of operbtions  of the MBebn.
     * Ebch operbtion is described by bn <CODE>MBebnOperbtionInfo</CODE> object.
     *
     * @return  An brrby of <CODE>MBebnOperbtionInfo</CODE> objects.
     */
    public MBebnOperbtionInfo[] getOperbtions();

}
