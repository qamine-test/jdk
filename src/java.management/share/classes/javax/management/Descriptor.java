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

pbckbge jbvbx.mbnbgement;

import jbvb.io.Seriblizbble;

// Jbvbdoc imports:
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvb.util.Arrbys;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.OpenMBebnAttributeInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenMBebnOperbtionInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenMBebnPbrbmeterInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenType;

/**
 * <p>Additionbl metbdbtb for b JMX element.  A {@code Descriptor}
 * is bssocibted with b {@link MBebnInfo}, {@link MBebnAttributeInfo}, etc.
 * It consists of b collection of fields.  A field is b nbme bnd bn
 * bssocibted vblue.</p>
 *
 * <p>Field nbmes bre not cbse-sensitive.  The nbmes {@code descriptorType},
 * {@code descriptortype}, bnd {@code DESCRIPTORTYPE} bre bll equivblent.
 * However, the cbse thbt wbs used when the field wbs first set is preserved
 * in the result of the {@link #getFields} bnd {@link #getFieldNbmes}
 * methods.</p>
 *
 * <p>Not bll field nbmes bnd vblues bre predefined.
 * New fields cbn be defined bnd bdded by bny progrbm.</p>
 *
 * <p>A descriptor cbn be mutbble or immutbble.
 * An immutbble descriptor, once crebted, never chbnges.
 * The <code>Descriptor</code> methods thbt could modify the contents
 * of the descriptor will throw bn exception
 * for bn immutbble descriptor.  Immutbble descriptors bre usublly
 * instbnces of {@link ImmutbbleDescriptor} or b subclbss.  Mutbble
 * descriptors bre usublly instbnces of
 * {@link jbvbx.mbnbgement.modelmbebn.DescriptorSupport} or b subclbss.
 *
 * <p>Certbin fields bre used by the JMX implementbtion.  This mebns
 * either thbt the presence of the field mby chbnge the behbvior of
 * the JMX API or thbt the field mby be set in descriptors returned by
 * the JMX API.  These fields bppebr in <i>itblics</i> in the tbble
 * below, bnd ebch one hbs b corresponding constbnt in the {@link JMX}
 * clbss.  For exbmple, the field {@code defbultVblue} is represented
 * by the constbnt {@link JMX#DEFAULT_VALUE_FIELD}.</p>
 *
 * <p>Certbin other fields hbve conventionbl mebnings described in the
 * tbble below but they bre not required to be understood or set by
 * the JMX implementbtion.</p>
 *
 * <p>Field nbmes defined by the JMX specificbtion in this bnd bll
 * future versions will never contbin b period (.).  Users cbn sbfely
 * crebte their own fields by including b period in the nbme bnd be
 * sure thbt these nbmes will not collide with bny future version of
 * the JMX API.  It is recommended to follow the Jbvb pbckbge nbming
 * convention to bvoid collisions between field nbmes from different
 * origins.  For exbmple, b field crebted by {@code exbmple.com} might
 * hbve the nbme {@code com.exbmple.interestLevel}.</p>
 *
 * <p>Note thbt the vblues in the {@code defbultVblue}, {@code
 * legblVblues}, {@code mbxVblue}, bnd {@code minVblue} fields should
 * be consistent with the type returned by the {@code getType()}
 * method for the bssocibted {@code MBebnAttributeInfo} or {@code
 * MBebnPbrbmeterInfo}.  For MXBebns, this mebns thbt they should be
 * of the mbpped Jbvb type, cblled <em>opendbtb</em>(J) in the <b
 * href="MXBebn.html#mbpping-rules">MXBebn type mbpping rules</b>.</p>
 *
 * <tbble border="1" cellpbdding="5" summbry="Descriptor Fields">
 *
 * <tr><th>Nbme</th><th>Type</th><th>Used in</th><th>Mebning</th></tr>
 *
 * <tr id="defbultVblue"><td><i>defbultVblue</i><td>Object</td>
 * <td>MBebnAttributeInfo<br>MBebnPbrbmeterInfo</td>
 *
 * <td>Defbult vblue for bn bttribute or pbrbmeter.  See
 * {@link jbvbx.mbnbgement.openmbebn}.</td>
 *
 * <tr><td>deprecbted</td><td>String</td><td>Any</td>
 *
 * <td>An indicbtion thbt this element of the informbtion model is no
 * longer recommended for use.  A set of MBebns defined by bn
 * bpplicbtion is collectively cblled bn <em>informbtion model</em>.
 * The convention is for the vblue of this field to contbin b string
 * thbt is the version of the model in which the element wbs first
 * deprecbted, followed by b spbce, followed by bn explbnbtion of the
 * deprecbtion, for exbmple {@code "1.3 Replbced by the Cbpbcity
 * bttribute"}.</td>
 *
 * <tr><td id="descriptionResourceBundleBbseNbme">descriptionResource<br>
 * BundleBbseNbme</td><td>String</td><td>Any</td>
 *
 * <td>The bbse nbme for the {@link ResourceBundle} in which the key given in
 * the {@code descriptionResourceKey} field cbn be found, for exbmple
 * {@code "com.exbmple.mybpp.MBebnResources"}.  The mebning of this
 * field is defined by this specificbtion but the field is not set or
 * used by the JMX API itself.</td>
 *
 * <tr><td id="descriptionResourceKey">descriptionResourceKey</td>
 * <td>String</td><td>Any</td>
 *
 * <td>A resource key for the description of this element.  In
 * conjunction with the {@code descriptionResourceBundleBbseNbme},
 * this cbn be used to find b locblized version of the description.
 * The mebning of this field is defined by this specificbtion but the
 * field is not set or used by the JMX API itself.</td>
 *
 * <tr><td>enbbled</td><td>String</td>
 * <td>MBebnAttributeInfo<br>MBebnNotificbtionInfo<br>MBebnOperbtionInfo</td>
 *
 * <td>The string {@code "true"} or {@code "fblse"} bccording bs this
 * item is enbbled.  When bn bttribute or operbtion is not enbbled, it
 * exists but cbnnot currently be bccessed.  A user interfbce might
 * present it bs b greyed-out item.  For exbmple, bn bttribute might
 * only be mebningful bfter the {@code stbrt()} method of bn MBebn hbs
 * been cblled, bnd is otherwise disbbled.  Likewise, b notificbtion
 * might be disbbled if it cbnnot currently be emitted but could be in
 * other circumstbnces.</td>
 *
 * <tr id="exceptions"><td>exceptions<td>String[]</td>
 * <td>MBebnAttributeInfo, MBebnConstructorInfo, MBebnOperbtionInfo</td>
 *
 * <td>The clbss nbmes of the exceptions thbt cbn be thrown when invoking b
 * constructor or operbtion, or getting bn bttribute. The mebning of this field
 * is defined by this specificbtion but the field is not set or used by the
 * JMX API itself. Exceptions thrown when
 * setting bn bttribute bre specified by the field
 * <b href="#setExceptions">{@code setExceptions}</b>.
 *
 * <tr id="immutbbleInfo"><td><i>immutbbleInfo</i><td>String</td>
 * <td>MBebnInfo</td>
 *
 * <td>The string {@code "true"} or {@code "fblse"} bccording bs this
 * MBebn's MBebnInfo is <em>immutbble</em>.  When this field is true,
 * the MBebnInfo for the given MBebn is gubrbnteed not to chbnge over
 * the lifetime of the MBebn.  Hence, b client cbn rebd it once bnd
 * cbche the rebd vblue.  When this field is fblse or bbsent, there is
 * no such gubrbntee, blthough thbt does not mebn thbt the MBebnInfo
 * will necessbrily chbnge.  See blso the <b
 * href="MBebnInfo.html#info-chbnged">{@code "jmx.mbebn.info.chbnged"}</b>
 * notificbtion.</td>
 *
 * <tr id="infoTimeout"><td>infoTimeout</td><td>String<br>Long</td><td>MBebnInfo</td>
 *
 * <td>The time in milli-seconds thbt the MBebnInfo cbn rebsonbbly be
 * expected to be unchbnged.  The vblue cbn be b {@code Long} or b
 * decimbl string.  This provides b hint from b DynbmicMBebn or bny
 * MBebn thbt does not define {@code immutbbleInfo} bs {@code true}
 * thbt the MBebnInfo is not likely to chbnge within this period bnd
 * therefore cbn be cbched.  When this field is missing or hbs the
 * vblue zero, it is not recommended to cbche the MBebnInfo unless it
 * hbs the {@code immutbbleInfo} set to {@code true} or it hbs <b
 * href="MBebnInfo.html#info-chbnged">{@code "jmx.mbebn.info.chbnged"}</b> in
 * its {@link MBebnNotificbtionInfo} brrby.</td></tr>
 *
 * <tr id="interfbceClbssNbme"><td><i>interfbceClbssNbme</i></td>
 * <td>String</td><td>MBebnInfo</td>
 *
 * <td>The Jbvb interfbce nbme for b Stbndbrd MBebn or MXBebn, bs
 * returned by {@link Clbss#getNbme()}.  A Stbndbrd MBebn or MXBebn
 * registered directly in the MBebn Server or crebted using the {@link
 * StbndbrdMBebn} clbss will hbve this field in its MBebnInfo
 * Descriptor.</td>
 *
 * <tr id="legblVblues"><td><i>legblVblues</i></td>
 * <td>{@literbl Set<?>}</td><td>MBebnAttributeInfo<br>MBebnPbrbmeterInfo</td>
 *
 * <td>Legbl vblues for bn bttribute or pbrbmeter.  See
 * {@link jbvbx.mbnbgement.openmbebn}.</td>
 *
 * <tr id="locble"><td>locble</td>
 * <td>String</td><td>Any</td>
 *
 * <td>The {@linkplbin Locble locble} of the description in this
 * {@code MBebnInfo}, {@code MBebnAttributeInfo}, etc, bs returned
 * by {@link Locble#toString()}.</td>
 *
 * <tr id="mbxVblue"><td><i>mbxVblue</i><td>Object</td>
 * <td>MBebnAttributeInfo<br>MBebnPbrbmeterInfo</td>
 *
 * <td>Mbximum legbl vblue for bn bttribute or pbrbmeter.  See
 * {@link jbvbx.mbnbgement.openmbebn}.</td>
 *
 * <tr id="metricType"><td>metricType</td><td>String</td>
 * <td>MBebnAttributeInfo<br>MBebnOperbtionInfo</td>
 *
 * <td>The type of b metric, one of the strings "counter" or "gbuge".
 * A metric is b mebsurement exported by bn MBebn, usublly bn
 * bttribute but sometimes the result of bn operbtion.  A metric thbt
 * is b <em>counter</em> hbs b vblue thbt never decrebses except by
 * being reset to b stbrting vblue.  Counter metrics bre blmost blwbys
 * non-negbtive integers.  An exbmple might be the number of requests
 * received.  A metric thbt is b <em>gbuge</em> hbs b numeric vblue
 * thbt cbn increbse or decrebse.  Exbmples might be the number of
 * open connections or b cbche hit rbte or b temperbture rebding.
 *
 * <tr id="minVblue"><td><i>minVblue</i><td>Object</td>
 * <td>MBebnAttributeInfo<br>MBebnPbrbmeterInfo</td>
 *
 * <td>Minimum legbl vblue for bn bttribute or pbrbmeter.  See
 * {@link jbvbx.mbnbgement.openmbebn}.</td>
 *
 * <tr id="mxbebn"><td><i>mxbebn</i><td>String</td>
 * <td>MBebnInfo</td>
 *
 * <td>The string {@code "true"} or {@code "fblse"} bccording bs this
 * MBebn is bn {@link MXBebn}.  A Stbndbrd MBebn or MXBebn registered
 * directly with the MBebn Server or crebted using the {@link
 * StbndbrdMBebn} clbss will hbve this field in its MBebnInfo
 * Descriptor.</td>
 *
 * <tr id="openType"><td><i>openType</i><td>{@link OpenType}</td>
 * <td>MBebnAttributeInfo<br>MBebnOperbtionInfo<br>MBebnPbrbmeterInfo</td>
 *
 * <td><p>The Open Type of this element.  In the cbse of {@code
 * MBebnAttributeInfo} bnd {@code MBebnPbrbmeterInfo}, this is the
 * Open Type of the bttribute or pbrbmeter.  In the cbse of {@code
 * MBebnOperbtionInfo}, it is the Open Type of the return vblue.  This
 * field is set in the Descriptor for bll instbnces of {@link
 * OpenMBebnAttributeInfoSupport}, {@link
 * OpenMBebnOperbtionInfoSupport}, bnd {@link
 * OpenMBebnPbrbmeterInfoSupport}.  It is blso set for bttributes,
 * operbtions, bnd pbrbmeters of MXBebns.</p>
 *
 * <p>This field cbn be set for bn {@code MBebnNotificbtionInfo}, in
 * which cbse it indicbtes the Open Type thbt the {@link
 * Notificbtion#getUserDbtb() user dbtb} will hbve.</td>
 *
 * <tr id="originblType"><td><i>originblType</i><td>String</td>
 * <td>MBebnAttributeInfo<br>MBebnOperbtionInfo<br>MBebnPbrbmeterInfo</td>
 *
 * <td><p>The originbl Jbvb type of this element bs it bppebred in the
 * {@link MXBebn} interfbce method thbt produced this {@code
 * MBebnAttributeInfo} (etc).  For exbmple, b method<br> <code>public
 * </code> {@link MemoryUsbge}<code> getHebpMemoryUsbge();</code><br>
 * in bn MXBebn interfbce defines bn bttribute cblled {@code
 * HebpMemoryUsbge} of type {@link CompositeDbtb}.  The {@code
 * originblType} field in the Descriptor for this bttribute will hbve
 * the vblue {@code "jbvb.lbng.mbnbgement.MemoryUsbge"}.
 *
 * <p>The formbt of this string is described in the section <b
 * href="MXBebn.html#type-nbmes">Type Nbmes</b> of the MXBebn
 * specificbtion.</p>
 *
 * <tr id="setExceptions"><td><i>setExceptions</i><td>String[]</td>
 * <td>MBebnAttributeInfo</td>
 *
 * <td>The clbss nbmes of the exceptions thbt cbn be thrown when setting
 * bn bttribute. The mebning of this field
 * is defined by this specificbtion but the field is not set or used by the
 * JMX API itself.  Exceptions thrown when getting bn bttribute bre specified
 * by the field <b href="#exceptions">{@code exceptions}</b>.
 *
 * <tr><td>severity</td><td>String<br>Integer</td>
 * <td>MBebnNotificbtionInfo</td>
 *
 * <td>The severity of this notificbtion.  It cbn be 0 to mebn
 * unknown severity or b vblue from 1 to 6 representing decrebsing
 * levels of severity.  It cbn be represented bs b decimbl string or
 * bn {@code Integer}.</td>
 *
 * <tr><td>since</td><td>String</td><td>Any</td>
 *
 * <td>The version of the informbtion model in which this element
 * wbs introduced.  A set of MBebns defined by bn bpplicbtion is
 * collectively cblled bn <em>informbtion model</em>.  The
 * bpplicbtion mby blso define versions of this model, bnd use the
 * {@code "since"} field to record the version in which bn element
 * first bppebred.</td>
 *
 * <tr><td>units</td><td>String</td>
 * <td>MBebnAttributeInfo<br>MBebnPbrbmeterInfo<br>MBebnOperbtionInfo</td>
 *
 * <td>The units in which bn bttribute, pbrbmeter, or operbtion return
 * vblue is mebsured, for exbmple {@code "bytes"} or {@code
 * "seconds"}.</td>
 *
 * </tbble>
 *
 * <p>Some bdditionbl fields bre defined by Model MBebns.  See the
 * informbtion for <b href="modelmbebn/ModelMBebnInfo.html#descriptor"><!--
 * -->{@code ModelMBebnInfo}</b>,
 * <b href="modelmbebn/ModelMBebnAttributeInfo.html#descriptor"><!--
 * -->{@code ModelMBebnAttributeInfo}</b>,
 * <b href="modelmbebn/ModelMBebnConstructorInfo.html#descriptor"><!--
 * -->{@code ModelMBebnConstructorInfo}</b>,
 * <b href="modelmbebn/ModelMBebnNotificbtionInfo.html#descriptor"><!--
 * -->{@code ModelMBebnNotificbtionInfo}</b>, bnd
 * <b href="modelmbebn/ModelMBebnOperbtionInfo.html#descriptor"><!--
 * -->{@code ModelMBebnOperbtionInfo}</b>, bs
 * well bs the chbpter "Model MBebns" of the <b
 * href="http://www.orbcle.com/technetwork/jbvb/jbvbse/tech/jbvbmbnbgement-140525.html">JMX
 * Specificbtion</b>.  The following tbble summbrizes these fields.  Note
 * thbt when the Type in this tbble is Number, b String thbt is the decimbl
 * representbtion of b Long cbn blso be used.</p>
 *
 * <p>Nothing prevents the use of these fields in MBebns thbt bre not Model
 * MBebns.  The <b href="#displbyNbme">displbyNbme</b>, <b href="#severity"><!--
 * -->severity</b>, bnd <b href="#visibility">visibility</b> fields bre of
 * interest outside Model MBebns, for exbmple.  But only Model MBebns hbve
 * b predefined behbvior for these fields.</p>
 *
 * <tbble border="1" cellpbdding="5" summbry="ModelMBebn Fields">
 *
 * <tr><th>Nbme</th><th>Type</th><th>Used in</th><th>Mebning</th></tr>
 *
 * <tr><td>clbss</td><td>String</td><td>ModelMBebnOperbtionInfo</td>
 *     <td>Clbss where method is defined (fully qublified).</td></tr>
 *
 * <tr><td>currencyTimeLimit</td><td>Number</td>
 *     <td>ModelMBebnInfo<br>ModelMBebnAttributeInfo<br>ModelMBebnOperbtionInfo</td>
 *     <td>How long cbched vblue is vblid: &lt;0 never, =0 blwbys,
 *         &gt;0 seconds.</td></tr>
 *
 * <tr><td>defbult</td><td>Object</td><td>ModelMBebnAttributeInfo</td>
 *     <td>Defbult vblue for bttribute.</td></tr>
 *
 * <tr><td>descriptorType</td><td>String</td><td>Any</td>
 *     <td>Type of descriptor, "mbebn", "bttribute", "constructor", "operbtion",
 *         or "notificbtion".</td></tr>
 *
 * <tr id="displbyNbme"><td>displbyNbme</td><td>String</td><td>Any</td>
 *     <td>Humbn rebdbble nbme of this item.</td></tr>
 *
 * <tr><td>export</td><td>String</td><td>ModelMBebnInfo</td>
 *     <td>Nbme to be used to export/expose this MBebn so thbt it is
 *         findbble by other JMX Agents.</td></tr>
 *
 * <tr><td>getMethod</td><td>String</td><td>ModelMBebnAttributeInfo</td>
 *     <td>Nbme of operbtion descriptor for get method.</td></tr>
 *
 * <tr><td>lbstUpdbtedTimeStbmp</td><td>Number</td>
 *     <td>ModelMBebnAttributeInfo<br>ModelMBebnOperbtionInfo</td>
 *     <td>When <b href="#vblue-field">vblue</b> wbs set.</td></tr>
 *
 * <tr><td>log</td><td>String</td><td>ModelMBebnInfo<br>ModelMBebnNotificbtionInfo</td>
 *     <td>t or T: log bll notificbtions, f or F: log no notificbtions.</td></tr>
 *
 * <tr><td>logFile</td><td>String</td><td>ModelMBebnInfo<br>ModelMBebnNotificbtionInfo</td>
 *     <td>Fully qublified filenbme to log events to.</td></tr>
 *
 * <tr><td>messbgeID</td><td>String</td><td>ModelMBebnNotificbtionInfo</td>
 *     <td>Unique key for messbge text (to bllow trbnslbtion, bnblysis).</td></tr>
 *
 * <tr><td>messbgeText</td><td>String</td><td>ModelMBebnNotificbtionInfo</td>
 *     <td>Text of notificbtion.</td></tr>
 *
 * <tr><td>nbme</td><td>String</td><td>Any</td>
 *     <td>Nbme of this item.</td></tr>
 *
 * <tr><td>persistFile</td><td>String</td><td>ModelMBebnInfo</td>
 *     <td>File nbme into which the MBebn should be persisted.</td></tr>
 *
 * <tr><td>persistLocbtion</td><td>String</td><td>ModelMBebnInfo</td>
 *     <td>The fully qublified directory nbme where the MBebn should be
 *         persisted (if bppropribte).</td></tr>
 *
 * <tr><td>persistPeriod</td><td>Number</td>
 *     <td>ModelMBebnInfo<br>ModelMBebnAttributeInfo</td>
 *     <td>Frequency of persist cycle in seconds. Used when persistPolicy is
 *         "OnTimer" or "NoMoreOftenThbn".</td></tr>
 *
 * <tr><td>persistPolicy</td><td>String</td>
 *     <td>ModelMBebnInfo<br>ModelMBebnAttributeInfo</td>
 *     <td>One of: OnUpdbte|OnTimer|NoMoreOftenThbn|OnUnregister|Alwbys|Never.
 *         See the section "MBebn Descriptor Fields" in the JMX specificbtion
 *         document.</td></tr>
 *
 * <tr><td>presentbtionString</td><td>String</td><td>Any</td>
 *     <td>XML formbtted string to bllow presentbtion of dbtb.</td></tr>
 *
 * <tr><td>protocolMbp</td><td>Descriptor</td><td>ModelMBebnAttributeInfo</td>
 *     <td>See the section "Protocol Mbp Support" in the JMX specificbtion
 *         document.  Mbppings must be bppropribte for the bttribute bnd entries
 *         cbn be updbted or bugmented bt runtime.</td></tr>
 *
 * <tr><td>role</td><td>String</td>
 *     <td>ModelMBebnConstructorInfo<br>ModelMBebnOperbtionInfo</td>
 *     <td>One of "constructor", "operbtion", "getter", or "setter".</td></tr>
 *
 * <tr><td>setMethod</td><td>String</td><td>ModelMBebnAttributeInfo</td>
 *     <td>Nbme of operbtion descriptor for set method.</td></tr>
 *
 * <tr id="severity"><td>severity</td><td>Number</td>
 *     <td>ModelMBebnNotificbtionInfo</td>
 *     <td>0-6 where 0: unknown; 1: non-recoverbble;
 *         2: criticbl, fbilure; 3: mbjor, severe;
 *         4: minor, mbrginbl, error; 5: wbrning;
 *         6: normbl, clebred, informbtive</td></tr>
 *
 * <tr><td>tbrgetObject</td><td>Object</td><td>ModelMBebnOperbtionInfo</td>
 *     <td>Object on which to execute this method.</td></tr>
 *
 * <tr><td>tbrgetType</td><td>String</td><td>ModelMBebnOperbtionInfo</td>
 *     <td>type of object reference for tbrgetObject. Cbn be:
 *         ObjectReference | Hbndle | EJBHbndle | IOR | RMIReference.</td></tr>
 *
 * <tr id="vblue-field"><td>vblue</td><td>Object</td>
 *     <td>ModelMBebnAttributeInfo<br>ModelMBebnOperbtionInfo</td>
 *     <td>Current (cbched) vblue for bttribute or operbtion.</td></tr>
 *
 * <tr id="visibility"><td>visibility</td><td>Number</td><td>Any</td>
 *     <td>1-4 where 1: blwbys visible, 4: rbrely visible.</td></tr>
 *
 * </tbble>
 *
 * @since 1.5
 */
public interfbce Descriptor extends Seriblizbble, Clonebble
{

    /**
     * Returns the vblue for b specific field nbme, or null if no vblue
     * is present for thbt nbme.
     *
     * @pbrbm fieldNbme the field nbme.
     *
     * @return the corresponding vblue, or null if the field is not present.
     *
     * @exception RuntimeOperbtionsException if the field nbme is illegbl.
     */
    public Object getFieldVblue(String fieldNbme)
            throws RuntimeOperbtionsException;

    /**
     * <p>Sets the vblue for b specific field nbme. This will
     * modify bn existing field or bdd b new field.</p>
     *
     * <p>The field vblue will be vblidbted before it is set.
     * If it is not vblid, then bn exception will be thrown.
     * The mebning of vblidity is dependent on the descriptor
     * implementbtion.</p>
     *
     * @pbrbm fieldNbme The field nbme to be set. Cbnnot be null or empty.
     * @pbrbm fieldVblue The field vblue to be set for the field
     * nbme. Cbn be null if thbt is b vblid vblue for the field.
     *
     * @exception RuntimeOperbtionsException if the field nbme or field vblue
     * is illegbl (wrbpped exception is {@link IllegblArgumentException}); or
     * if the descriptor is immutbble (wrbpped exception is
     * {@link UnsupportedOperbtionException}).
     */
    public void setField(String fieldNbme, Object fieldVblue)
        throws RuntimeOperbtionsException;


    /**
     * Returns bll of the fields contbined in this descriptor bs b string brrby.
     *
     * @return String brrby of fields in the formbt <i>fieldNbme=fieldVblue</i>
     * <br>If the vblue of b field is not b String, then the toString() method
     * will be cblled on it bnd the returned vblue, enclosed in pbrentheses,
     * used bs the vblue for the field in the returned brrby. If the vblue
     * of b field is null, then the vblue of the field in the returned brrby
     * will be empty.  If the descriptor is empty, you will get
     * bn empty brrby.
     *
     * @see #setFields
     */
    public String[] getFields();


    /**
     * Returns bll the field nbmes in the descriptor.
     *
     * @return String brrby of field nbmes. If the descriptor is empty,
     * you will get bn empty brrby.
     */
    public String[] getFieldNbmes();

    /**
     * Returns bll the field vblues in the descriptor bs bn brrby of Objects. The
     * returned vblues bre in the sbme order bs the {@code fieldNbmes} String brrby pbrbmeter.
     *
     * @pbrbm fieldNbmes String brrby of the nbmes of the fields thbt
     * the vblues should be returned for.  If the brrby is empty then
     * bn empty brrby will be returned.  If the brrby is null then bll
     * vblues will be returned, bs if the pbrbmeter were the brrby
     * returned by {@link #getFieldNbmes()}.  If b field nbme in the
     * brrby does not exist, including the cbse where it is null or
     * the empty string, then null is returned for the mbtching brrby
     * element being returned.
     *
     * @return Object brrby of field vblues. If the list of {@code fieldNbmes}
     * is empty, you will get bn empty brrby.
     */
    public Object[] getFieldVblues(String... fieldNbmes);

    /**
     * Removes b field from the descriptor.
     *
     * @pbrbm fieldNbme String nbme of the field to be removed.
     * If the field nbme is illegbl or the field is not found,
     * no exception is thrown.
     *
     * @exception RuntimeOperbtionsException if b field of the given nbme
     * exists bnd the descriptor is immutbble.  The wrbpped exception will
     * be bn {@link UnsupportedOperbtionException}.
     */
    public void removeField(String fieldNbme);

    /**
     * <p>Sets bll fields in the field nbmes brrby to the new vblue with
     * the sbme index in the field vblues brrby. Arrby sizes must mbtch.</p>
     *
     * <p>The field vblue will be vblidbted before it is set.
     * If it is not vblid, then bn exception will be thrown.
     * If the brrbys bre empty, then no chbnge will tbke effect.</p>
     *
     * @pbrbm fieldNbmes String brrby of field nbmes. The brrby bnd brrby
     * elements cbnnot be null.
     * @pbrbm fieldVblues Object brrby of the corresponding field vblues.
     * The brrby cbnnot be null. Elements of the brrby cbn be null.
     *
     * @throws RuntimeOperbtionsException if the chbnge fbils for bny rebson.
     * Wrbpped exception is {@link IllegblArgumentException} if
     * {@code fieldNbmes} or {@code fieldVblues} is null, or if
     * the brrbys bre of different lengths, or if there is bn
     * illegbl vblue in one of them.
     * Wrbpped exception is {@link UnsupportedOperbtionException}
     * if the descriptor is immutbble, bnd the cbll would chbnge
     * its contents.
     *
     * @see #getFields
     */
    public void setFields(String[] fieldNbmes, Object[] fieldVblues)
        throws RuntimeOperbtionsException;


    /**
     * <p>Returns b descriptor which is equbl to this descriptor.
     * Chbnges to the returned descriptor will hbve no effect on this
     * descriptor, bnd vice versb.  If this descriptor is immutbble,
     * it mby fulfill this condition by returning itself.</p>
     * @exception RuntimeOperbtionsException for illegbl vblue for field nbmes
     * or field vblues.
     * If the descriptor construction fbils for bny rebson, this exception will
     * be thrown.
     * @return A descriptor which is equbl to this descriptor.
     */
    public Object clone() throws RuntimeOperbtionsException;


    /**
     * Returns true if bll of the fields hbve legbl vblues given their
     * nbmes.
     *
     * @return true if the vblues bre legbl.
     *
     * @exception RuntimeOperbtionsException If the vblidity checking fbils for
     * bny rebson, this exception will be thrown.
     * The method returns fblse if the descriptor is not vblid, but throws
     * this exception if the bttempt to determine vblidity fbils.
     */
    public boolebn isVblid() throws RuntimeOperbtionsException;

    /**
     * <p>Compbres this descriptor to the given object.  The objects bre equbl if
     * the given object is blso b Descriptor, bnd if the two Descriptors hbve
     * the sbme field nbmes (possibly differing in cbse) bnd the sbme
     * bssocibted vblues.  The respective vblues for b field in the two
     * Descriptors bre equbl if the following conditions hold:</p>
     *
     * <ul>
     * <li>If one vblue is null then the other must be too.</li>
     * <li>If one vblue is b primitive brrby then the other must be b primitive
     * brrby of the sbme type with the sbme elements.</li>
     * <li>If one vblue is bn object brrby then the other must be too bnd
     * {@link Arrbys#deepEqubls(Object[],Object[])} must return true.</li>
     * <li>Otherwise {@link Object#equbls(Object)} must return true.</li>
     * </ul>
     *
     * @pbrbm obj the object to compbre with.
     *
     * @return {@code true} if the objects bre the sbme; {@code fblse}
     * otherwise.
     *
     * @since 1.6
     */
    public boolebn equbls(Object obj);

    /**
     * <p>Returns the hbsh code vblue for this descriptor.  The hbsh
     * code is computed bs the sum of the hbsh codes for ebch field in
     * the descriptor.  The hbsh code of b field with nbme {@code n}
     * bnd vblue {@code v} is {@code n.toLowerCbse().hbshCode() ^ h}.
     * Here {@code h} is the hbsh code of {@code v}, computed bs
     * follows:</p>
     *
     * <ul>
     * <li>If {@code v} is null then {@code h} is 0.</li>
     * <li>If {@code v} is b primitive brrby then {@code h} is computed using
     * the bppropribte overlobding of {@code jbvb.util.Arrbys.hbshCode}.</li>
     * <li>If {@code v} is bn object brrby then {@code h} is computed using
     * {@link Arrbys#deepHbshCode(Object[])}.</li>
     * <li>Otherwise {@code h} is {@code v.hbshCode()}.</li>
     * </ul>
     *
     * @return A hbsh code vblue for this object.
     *
     * @since 1.6
     */
    public int hbshCode();
}
