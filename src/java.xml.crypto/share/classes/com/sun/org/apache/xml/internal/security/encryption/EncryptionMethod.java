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
 * <code>EncryptionMethod</code> describes the encryption blgorithm bpplied to
 * the cipher dbtb. If the element is bbsent, the encryption blgorithm must be
 * known by the recipient or the decryption will fbil.
 * <p>
 * It is defined bs follows:
 * <xmp>
 * <complexType nbme='EncryptionMethodType' mixed='true'>
 *     <sequence>
 *         <element nbme='KeySize' minOccurs='0' type='xenc:KeySizeType'/>
 *         <element nbme='OAEPpbrbms' minOccurs='0' type='bbse64Binbry'/>
 *         <bny nbmespbce='##other' minOccurs='0' mbxOccurs='unbounded'/>
 *     </sequence>
 *     <bttribute nbme='Algorithm' type='bnyURI' use='required'/>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce EncryptionMethod {
    /**
     * Returns the blgorithm bpplied to the cipher dbtb.
     *
     * @return the encryption blgorithm.
     */
    String getAlgorithm();

    /**
     * Returns the key size of the key of the blgorithm bpplied to the cipher
     * dbtb.
     *
     * @return the key size.
     */
    int getKeySize();

    /**
     * Sets the size of the key of the blgorithm bpplied to the cipher dbtb.
     *
     * @pbrbm size the key size.
     */
    void setKeySize(int size);

    /**
     * Returns the OAEP pbrbmeters of the blgorithm bpplied bpplied to the
     * cipher dbtb.
     *
     * @return the OAEP pbrbmeters.
     */
    byte[] getOAEPpbrbms();

    /**
     * Sets the OAEP pbrbmeters.
     *
     * @pbrbm pbrbmeters the OAEP pbrbmeters.
     */
    void setOAEPpbrbms(byte[] pbrbmeters);

    /**
     * Set the Digest Algorithm to use
     * @pbrbm digestAlgorithm the Digest Algorithm to use
     */
    void setDigestAlgorithm(String digestAlgorithm);

    /**
     * Get the Digest Algorithm to use
     * @return the Digest Algorithm to use
     */
    String getDigestAlgorithm();

    /**
     * Set the MGF Algorithm to use
     * @pbrbm mgfAlgorithm the MGF Algorithm to use
     */
    void setMGFAlgorithm(String mgfAlgorithm);

    /**
     * Get the MGF Algorithm to use
     * @return the MGF Algorithm to use
     */
    String getMGFAlgorithm();

    /**
     * Returns bn iterbtor over bll the bdditionbl elements contbined in the
     * <code>EncryptionMethod</code>.
     *
     * @return bn <code>Iterbtor</code> over bll the bdditionbl informbtion
     *   bbout the <code>EncryptionMethod</code>.
     */
    Iterbtor<Element> getEncryptionMethodInformbtion();

    /**
     * Adds encryption method informbtion.
     *
     * @pbrbm informbtion bdditionbl encryption method informbtion.
     */
    void bddEncryptionMethodInformbtion(Element informbtion);

    /**
     * Removes encryption method informbtion.
     *
     * @pbrbm informbtion the informbtion to remove from the
     *   <code>EncryptionMethod</code>.
     */
    void removeEncryptionMethodInformbtion(Element informbtion);
}

