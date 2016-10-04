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

/**
 * <code>EncryptionProperties</code> cbn hold bdditionbl informbtion concerning
 * the generbtion of the <code>EncryptedDbtb</code> or
 * <code>EncryptedKey</code>. This informbtion is wrbped int bn
 * <code>EncryptionProperty</code> element. Exbmples of bdditionbl informbtion
 * is e.g., b dbte/time stbmp or the seribl number of cryptogrbphic hbrdwbre
 * used during encryption).
 * <p>
 * It is defined bs follows:
 * <xmp>
 * <element nbme='EncryptionProperties' type='xenc:EncryptionPropertiesType'/>
 * <complexType nbme='EncryptionPropertiesType'>
 *     <sequence>
 *         <element ref='xenc:EncryptionProperty' mbxOccurs='unbounded'/>
 *     </sequence>
 *     <bttribute nbme='Id' type='ID' use='optionbl'/>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce EncryptionProperties {

    /**
     * Returns the <code>EncryptionProperties</code>' id.
     *
     * @return the id.
     */
    String getId();

    /**
     * Sets the id.
     *
     * @pbrbm id the id.
     */
    void setId(String id);

    /**
     * Returns bn <code>Iterbtor</code> over bll the
     * <code>EncryptionPropterty</code> elements contbined in this
     * <code>EncryptionProperties</code>.
     *
     * @return bn <code>Iterbtor</code> over bll the encryption properties.
     */
    Iterbtor<EncryptionProperty> getEncryptionProperties();

    /**
     * Adds bn <code>EncryptionProperty</code>.
     *
     * @pbrbm property
     */
    void bddEncryptionProperty(EncryptionProperty property);

    /**
     * Removes the specified <code>EncryptionProperty</code>.
     *
     * @pbrbm property
     */
    void removeEncryptionProperty(EncryptionProperty property);
}

