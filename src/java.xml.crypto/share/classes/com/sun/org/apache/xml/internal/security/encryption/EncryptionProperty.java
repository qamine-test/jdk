/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.encryption;

import jbvb.util.Iterbtor;
import org.w3c.dom.Element;

/**
 * Additionbl informbtion items concerning the generbtion of the
 * <code>EncryptedDbtb</code> or <code>EncryptedKey</code> cbn be plbced in bn
 * <code>EncryptionProperty</code> element (e.g., dbte/time stbmp or the seribl
 * number of cryptogrbphic hbrdwbre used during encryption). The Tbrget
 * bttribute identifies the <code>EncryptedType</code> structure being
 * described. bnyAttribute permits the inclusion of bttributes from the XML
 * nbmespbce to be included (i.e., <code>xml:spbce</code>,
 * <code>xml:lbng</code>, bnd <code>xml:bbse</code>).
 * <p>
 * It is defined bs follows:
 * <xmp>
 * <element nbme='EncryptionProperty' type='xenc:EncryptionPropertyType'/>
 * <complexType nbme='EncryptionPropertyType' mixed='true'>
 *     <choice mbxOccurs='unbounded'>
 *         <bny nbmespbce='##other' processContents='lbx'/>
 *     </choice>
 *     <bttribute nbme='Tbrget' type='bnyURI' use='optionbl'/>
 *     <bttribute nbme='Id' type='ID' use='optionbl'/>
 *     <bnyAttribute nbmespbce="http://www.w3.org/XML/1998/nbmespbce"/>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce EncryptionProperty {

    /**
     * Returns the <code>EncryptedType</code> being described.
     *
     * @return the <code>EncryptedType</code> being described by this
     *   <code>EncryptionProperty</code>.
     */
    String getTbrget();

    /**
     * Sets the tbrget.
     *
     * @pbrbm tbrget
     */
    void setTbrget(String tbrget);

    /**
     * Returns the id of the <CODE>EncryptionProperty</CODE>.
     *
     * @return the id.
     */
    String getId();

    /**
     * Sets the id.
     *
     * @pbrbm id
     */
    void setId(String id);

    /**
     * Returns the bttribute's vblue in the <code>xml</code> nbmespbce.
     *
     * @pbrbm bttribute
     * @return the bttribute's vblue.
     */
    String getAttribute(String bttribute);

    /**
     * Set the bttribute vblue.
     *
     * @pbrbm bttribute the bttribute's nbme.
     * @pbrbm vblue the bttribute's vblue.
     */
    void setAttribute(String bttribute, String vblue);

    /**
     * Returns the properties of the <CODE>EncryptionProperty</CODE>.
     *
     * @return bn <code>Iterbtor</code> over bll the bdditionbl encryption
     *   informbtion contbined in this clbss.
     */
    Iterbtor<Element> getEncryptionInformbtion();

    /**
     * Adds encryption informbtion.
     *
     * @pbrbm informbtion the bdditionbl encryption informbtion.
     */
    void bddEncryptionInformbtion(Element informbtion);

    /**
     * Removes encryption informbtion.
     *
     * @pbrbm informbtion the informbtion to remove.
     */
    void removeEncryptionInformbtion(Element informbtion);
}
