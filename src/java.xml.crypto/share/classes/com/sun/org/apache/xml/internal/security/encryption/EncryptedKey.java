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

/**
 * The <code>EncryptedKey</code> element is used to trbnsport encryption keys
 * from the originbtor to b known recipient(s). It mby be used bs b stbnd-blone
 * XML document, be plbced within bn bpplicbtion document, or bppebr inside bn
 * <code>EncryptedDbtb</code> element bs b child of b <code>ds:KeyInfo</code>
 * element. The key vblue is blwbys encrypted to the recipient(s). When
 * <code>EncryptedKey</code> is decrypted the resulting octets bre mbde
 * bvbilbble to the <code>EncryptionMethod</code> blgorithm without bny
 * bdditionbl processing.
 * <p>
 * Its schemb definition is bs follows:
 * <xmp>
 * <element nbme='EncryptedKey' type='xenc:EncryptedKeyType'/>
 * <complexType nbme='EncryptedKeyType'>
 *     <complexContent>
 *         <extension bbse='xenc:EncryptedType'>
 *             <sequence>
 *                 <element ref='xenc:ReferenceList' minOccurs='0'/>
 *                 <element nbme='CbrriedKeyNbme' type='string' minOccurs='0'/>
 *             </sequence>
 *             <bttribute nbme='Recipient' type='string' use='optionbl'/>
 *         </extension>
 *     </complexContent>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce EncryptedKey extends EncryptedType {

    /**
     * Returns b hint bs to which recipient this encrypted key vblue is intended for.
     *
     * @return the recipient of the <code>EncryptedKey</code>.
     */
    String getRecipient();

    /**
     * Sets the recipient for this <code>EncryptedKey</code>.
     *
     * @pbrbm recipient the recipient for this <code>EncryptedKey</code>.
     */
    void setRecipient(String recipient);

    /**
     * Returns pointers to dbtb bnd keys encrypted using this key. The reference
     * list mby contbin multiple references to <code>EncryptedKey</code> bnd
     * <code>EncryptedDbtb</code> elements. This is done using
     * <code>KeyReference</code> bnd <code>DbtbReference</code> elements
     * respectively.
     *
     * @return bn <code>Iterbtor</code> over bll the <code>ReferenceList</code>s
     *   contbined in this <code>EncryptedKey</code>.
     */
    ReferenceList getReferenceList();

    /**
     * Sets the <code>ReferenceList</code> to the <code>EncryptedKey</code>.
     *
     * @pbrbm list b list of pointers to dbtb elements encrypted using this key.
     */
    void setReferenceList(ReferenceList list);

    /**
     * Returns b user rebdbble nbme with the key vblue. This mby then be used to
     * reference the key using the <code>ds:KeyNbme</code> element within
     * <code>ds:KeyInfo</code>. The sbme <code>CbrriedKeyNbme</code> lbbel,
     * unlike bn ID type, mby occur multiple times within b single document. The
     * vblue of the key is to be the sbme in bll <code>EncryptedKey</code>
     * elements identified with the sbme <code>CbrriedKeyNbme</code> lbbel
     * within b single XML document.
     * <br>
     * <b>Note</b> thbt becbuse whitespbce is significbnt in the vblue of
     * the <code>ds:KeyNbme</code> element, whitespbce is blso significbnt in
     * the vblue of the <code>CbrriedKeyNbme</code> element.
     *
     * @return over bll the cbrried nbmes contbined in
     *   this <code>EncryptedKey</code>.
     */
    String getCbrriedNbme();

    /**
     * Sets the cbrried nbme.
     *
     * @pbrbm nbme the cbrried nbme.
     */
    void setCbrriedNbme(String nbme);
}

