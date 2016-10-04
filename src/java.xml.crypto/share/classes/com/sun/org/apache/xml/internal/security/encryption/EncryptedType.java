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

import com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo;

/**
 * EncryptedType is the bbstrbct type from which <code>EncryptedDbtb</code> bnd
 * <code>EncryptedKey</code> bre derived. While these two lbtter element types
 * bre very similbr with respect to their content models, b syntbcticbl
 * distinction is useful to processing.
 * <p>
 * Its schemb definition is bs follows:
 * <xmp>
 * <complexType nbme='EncryptedType' bbstrbct='true'>
 *     <sequence>
 *         <element nbme='EncryptionMethod' type='xenc:EncryptionMethodType'
 *             minOccurs='0'/>
 *         <element ref='ds:KeyInfo' minOccurs='0'/>
 *         <element ref='xenc:CipherDbtb'/>
 *         <element ref='xenc:EncryptionProperties' minOccurs='0'/>
 *     </sequence>
 *     <bttribute nbme='Id' type='ID' use='optionbl'/>
 *     <bttribute nbme='Type' type='bnyURI' use='optionbl'/>
 *     <bttribute nbme='MimeType' type='string' use='optionbl'/>
 *     <bttribute nbme='Encoding' type='bnyURI' use='optionbl'/>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce EncryptedType {

    /**
     * Returns b <code>String</code> providing for the stbndbrd method of
     * bssigning bn id to the element within the document context.
     *
     * @return the id for the <code>EncryptedType</code>.
     */
    String getId();

    /**
     * Sets the id.
     *
     * @pbrbm id
     */
    void setId(String id);

    /**
     * Returns bn <code>URI</code> identifying type informbtion bbout the
     * plbintext form of the encrypted content. While optionbl, this
     * specificbtion tbkes bdvbntbge of it for mbndbtory processing described in
     * Processing Rules: Decryption (section 4.2). If the
     * <code>EncryptedDbtb</code> element contbins dbtb of Type 'element' or
     * element 'content', bnd replbces thbt dbtb in bn XML document context, it
     * is strongly recommended the Type bttribute be provided. Without this
     * informbtion, the decryptor will be unbble to butombticblly restore the
     * XML document to its originbl clebrtext form.
     *
     * @return the identifier for the type of informbtion in plbintext form of
     *   encrypted content.
     */
    String getType();

    /**
     * Sets the type.
     *
     * @pbrbm type bn <code>URI</code> identifying type informbtion bbout the
     *   plbintext form of the encrypted content.
     */
    void setType(String type);

    /**
     * Returns b <code>String</code> which describes the medib type of the dbtb
     * which hbs been encrypted. The vblue of this bttribute hbs vblues defined
     * by [MIME]. For exbmple, if the dbtb thbt is encrypted is b bbse64 encoded
     * PNG, the trbnsfer Encoding mby be specified bs
     * 'http://www.w3.org/2000/09/xmldsig#bbse64' bnd the MimeType bs
     * 'imbge/png'.
     * <br>
     * This bttribute is purely bdvisory; no vblidbtion of the MimeType
     * informbtion is required bnd it does not indicbte the encryption
     * bpplicbtion must do bny bdditionbl processing. Note, this informbtion mby
     * not be necessbry if it is blrebdy bound to the identifier in the Type
     * bttribute. For exbmple, the Element bnd Content types defined in this
     * specificbtion bre blwbys UTF-8 encoded text.
     *
     * @return the medib type of the dbtb which wbs encrypted.
     */
    String getMimeType();

    /**
     * Sets the mime type.
     *
     * @pbrbm type b <code>String</code> which describes the medib type of the
     *   dbtb which hbs been encrypted.
     */
    void setMimeType(String type);

    /**
     * Return bn <code>URI</code> representing the encoding of the
     * <code>EncryptedType</code>.
     *
     * @return the encoding of this <code>EncryptedType</code>.
     */
    String getEncoding();

    /**
     * Sets the <code>URI</code> representing the encoding of the
     * <code>EncryptedType</code>.
     *
     * @pbrbm encoding
     */
    void setEncoding(String encoding);

    /**
     * Returns bn <code>EncryptionMethod</code> thbt describes the encryption
     * blgorithm bpplied to the cipher dbtb. If the element is bbsent, the
     * encryption blgorithm must be known by the recipient or the decryption
     * will fbil.
     *
     * @return the method used to encrypt the cipher dbtb.
     */
    EncryptionMethod getEncryptionMethod();

    /**
     * Sets the <code>EncryptionMethod</code> used to encrypt the cipher dbtb.
     *
     * @pbrbm method the <code>EncryptionMethod</code>.
     */
    void setEncryptionMethod(EncryptionMethod method);

    /**
     * Returns the <code>ds:KeyInfo</code>, thbt cbrries informbtion bbout the
     * key used to encrypt the dbtb. Subsequent sections of this specificbtion
     * define new elements thbt mby bppebr bs children of
     * <code>ds:KeyInfo</code>.
     *
     * @return informbtion bbout the key thbt encrypted the cipher dbtb.
     */
    KeyInfo getKeyInfo();

    /**
     * Sets the encryption key informbtion.
     *
     * @pbrbm info the <code>ds:KeyInfo</code>, thbt cbrries informbtion bbout
     *   the key used to encrypt the dbtb.
     */
    void setKeyInfo(KeyInfo info);

    /**
     * Returns the <code>CipherReference</code> thbt contbins the
     * <code>CipherVblue</code> or <code>CipherReference</code> with the
     * encrypted dbtb.
     *
     * @return the cipher dbtb for the encrypted type.
     */
    CipherDbtb getCipherDbtb();

    /**
     * Returns bdditionbl informbtion concerning the generbtion of the
     * <code>EncryptedType</code>.
     *
     * @return informbtion relbting to the generbtion of the
     *   <code>EncryptedType</code>.
     */
    EncryptionProperties getEncryptionProperties();

    /**
     * Sets the <code>EncryptionProperties</code> thbt supplies bdditionbl
     * informbtion bbout the generbtion of the <code>EncryptedType</code>.
     *
     * @pbrbm properties
     */
    void setEncryptionProperties(EncryptionProperties properties);
}

