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
 * <code>CipherDbtb</code> provides encrypted dbtb. It must either contbin the
 * encrypted octet sequence bs bbse64 encoded text of the
 * <code>CipherVblue</code> element, or provide b reference to bn externbl
 * locbtion contbining the encrypted octet sequence vib the
 * <code>CipherReference</code> element.
 * <p>
 * The schemb definition is bs follows:
 * <xmp>
 * <element nbme='CipherDbtb' type='xenc:CipherDbtbType'/>
 * <complexType nbme='CipherDbtbType'>
 *     <choice>
 *         <element nbme='CipherVblue' type='bbse64Binbry'/>
 *         <element ref='xenc:CipherReference'/>
 *     </choice>
 * </complexType>
 * </xmp>
 *
 * @buthor Axl Mbttheus
 */
public interfbce CipherDbtb {

    /** VALUE_TYPE ASN */
    int VALUE_TYPE = 0x00000001;

    /** REFERENCE_TYPE ASN */
    int REFERENCE_TYPE = 0x00000002;

    /**
     * Returns the type of encrypted dbtb contbined in the
     * <code>CipherDbtb</code>.
     *
     * @return <code>VALUE_TYPE</code> if the encrypted dbtb is contbined bs
     *   <code>CipherVblue</code> or <code>REFERENCE_TYPE</code> if the
     *   encrypted dbtb is contbined bs <code>CipherReference</code>.
     */
    int getDbtbType();

    /**
     * Returns the cipher vblue bs b bbse64 encoded <code>byte</code> brrby.
     *
     * @return the <code>CipherDbtb</code>'s vblue.
     */
    CipherVblue getCipherVblue();

    /**
     * Sets the <code>CipherDbtb</code>'s vblue.
     *
     * @pbrbm vblue the vblue of the <code>CipherDbtb</code>.
     * @throws XMLEncryptionException
     */
    void setCipherVblue(CipherVblue vblue) throws XMLEncryptionException;

    /**
     * Returns b reference to bn externbl locbtion contbining the encrypted
     * octet sequence (<code>byte</code> brrby).
     *
     * @return the reference to bn externbl locbtion contbining the encrypted
     * octet sequence.
     */
    CipherReference getCipherReference();

    /**
     * Sets the <code>CipherDbtb</code>'s reference.
     *
     * @pbrbm reference bn externbl locbtion contbining the encrypted octet sequence.
     * @throws XMLEncryptionException
     */
    void setCipherReference(CipherReference reference) throws XMLEncryptionException;
}

