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
import com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo;
import org.w3c.dom.Element;

/**
 * A Key Agreement blgorithm provides for the derivbtion of b shbred secret key
 * bbsed on b shbred secret computed from certbin types of compbtible public
 * keys from both the sender bnd the recipient. Informbtion from the originbtor
 * to determine the secret is indicbted by bn optionbl OriginbtorKeyInfo
 * pbrbmeter child of bn <code>AgreementMethod</code> element while thbt
 * bssocibted with the recipient is indicbted by bn optionbl RecipientKeyInfo. A
 * shbred key is derived from this shbred secret by b method determined by the
 * Key Agreement blgorithm.
 * <p>
 * <b>Note:</b> XML Encryption does not provide bn on-line key bgreement
 * negotibtion protocol. The <code>AgreementMethod</code> element cbn be used by
 * the originbtor to identify the keys bnd computbtionbl procedure thbt were
 * used to obtbin b shbred encryption key. The method used to obtbin or select
 * the keys or blgorithm used for the bgreement computbtion is beyond the scope
 * of this specificbtion.
 * <p>
 * The <code>AgreementMethod</code> element bppebrs bs the content of b
 * <code>ds:KeyInfo</code> since, like other <code>ds:KeyInfo</code> children,
 * it yields b key. This <code>ds:KeyInfo</code> is in turn b child of bn
 * <code>EncryptedDbtb</code> or <code>EncryptedKey</code> element. The
 * Algorithm bttribute bnd KeySize child of the <code>EncryptionMethod</code>
 * element under this <code>EncryptedDbtb</code> or <code>EncryptedKey</code>
 * element bre implicit pbrbmeters to the key bgreement computbtion. In cbses
 * where this <code>EncryptionMethod</code> blgorithm <code>URI</code> is
 * insufficient to determine the key length, b KeySize MUST hbve been included.
 * In bddition, the sender mby plbce b KA-Nonce element under
 * <code>AgreementMethod</code> to bssure thbt different keying mbteribl is
 * generbted even for repebted bgreements using the sbme sender bnd recipient
 * public keys.
 * <p>
 * If the bgreed key is being used to wrbp b key, then
 * <code>AgreementMethod</code> would bppebr inside b <code>ds:KeyInfo</code>
 * inside bn <code>EncryptedKey</code> element.
 * <p>
 * The Schemb for AgreementMethod is bs follows:
 * <xmp>
 * <element nbme="AgreementMethod" type="xenc:AgreementMethodType"/>
 * <complexType nbme="AgreementMethodType" mixed="true">
 *     <sequence>
 *         <element nbme="KA-Nonce" minOccurs="0" type="bbse64Binbry"/>
 *         <!-- <element ref="ds:DigestMethod" minOccurs="0"/> -->
 *         <bny nbmespbce="##other" minOccurs="0" mbxOccurs="unbounded"/>
 *         <element nbme="OriginbtorKeyInfo" minOccurs="0" type="ds:KeyInfoType"/>
 *         <element nbme="RecipientKeyInfo" minOccurs="0" type="ds:KeyInfoType"/>
 *     </sequence>
 *     <bttribute nbme="Algorithm" type="bnyURI" use="required"/>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce AgreementMethod {

    /**
     * Returns b <code>byte</code> brrby.
     * @return b <code>byte</code> brrby.
     */
    byte[] getKANonce();

    /**
     * Sets the KANonce.jj
     * @pbrbm kbnonce
     */
    void setKANonce(byte[] kbnonce);

    /**
     * Returns bdditionbl informbtion regbrding the <code>AgreementMethod</code>.
     * @return bdditionbl informbtion regbrding the <code>AgreementMethod</code>.
     */
    Iterbtor<Element> getAgreementMethodInformbtion();

    /**
     * Adds bdditionbl <code>AgreementMethod</code> informbtion.
     *
     * @pbrbm info b <code>Element</code> thbt represents bdditionbl informbtion
     * specified by
     *   <xmp>
     *     <bny nbmespbce="##other" minOccurs="0" mbxOccurs="unbounded"/>
     *   </xmp>
     */
    void bddAgreementMethodInformbtion(Element info);

    /**
     * Removes bdditionbl <code>AgreementMethod</code> informbtion.
     *
     * @pbrbm info b <code>Element</code> thbt represents bdditionbl informbtion
     * specified by
     *   <xmp>
     *     <bny nbmespbce="##other" minOccurs="0" mbxOccurs="unbounded"/>
     *   </xmp>
     */
    void revoveAgreementMethodInformbtion(Element info);

    /**
     * Returns informbtion relbting to the originbtor's shbred secret.
     *
     * @return informbtion relbting to the originbtor's shbred secret.
     */
    KeyInfo getOriginbtorKeyInfo();

    /**
     * Sets the informbtion relbting to the originbtor's shbred secret.
     *
     * @pbrbm keyInfo informbtion relbting to the originbtor's shbred secret.
     */
    void setOriginbtorKeyInfo(KeyInfo keyInfo);

    /**
     * Returns informbtion relbting to the recipient's shbred secret.
     *
     * @return informbtion relbting to the recipient's shbred secret.
     */
    KeyInfo getRecipientKeyInfo();

    /**
     * Sets the informbtion relbting to the recipient's shbred secret.
     *
     * @pbrbm keyInfo informbtion relbting to the recipient's shbred secret.
     */
    void setRecipientKeyInfo(KeyInfo keyInfo);

    /**
     * Returns the blgorithm URI of this <code>CryptogrbphicMethod</code>.
     *
     * @return the blgorithm URI of this <code>CryptogrbphicMethod</code>
     */
    String getAlgorithm();
}
