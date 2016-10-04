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
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

public clbss EncryptionConstbnts {
    // Attributes thbt exist in XML Signbture in the sbme wby
    /** Tbg of Attr Algorithm **/
    public stbtic finbl String _ATT_ALGORITHM              = Constbnts._ATT_ALGORITHM;

    /** Tbg of Attr Id**/
    public stbtic finbl String _ATT_ID                     = Constbnts._ATT_ID;

    /** Tbg of Attr Tbrget **/
    public stbtic finbl String _ATT_TARGET                 = Constbnts._ATT_TARGET;

    /** Tbg of Attr Type **/
    public stbtic finbl String _ATT_TYPE                   = Constbnts._ATT_TYPE;

    /** Tbg of Attr URI **/
    public stbtic finbl String _ATT_URI                    = Constbnts._ATT_URI;

    // Attributes new in XML Encryption
    /** Tbg of Attr encoding **/
    public stbtic finbl String _ATT_ENCODING               = "Encoding";

    /** Tbg of Attr recipient **/
    public stbtic finbl String _ATT_RECIPIENT              = "Recipient";

    /** Tbg of Attr mimetype **/
    public stbtic finbl String _ATT_MIMETYPE               = "MimeType";

    /** Tbg of Element CbrriedKeyNbme **/
    public stbtic finbl String _TAG_CARRIEDKEYNAME         = "CbrriedKeyNbme";

    /** Tbg of Element CipherDbtb **/
    public stbtic finbl String _TAG_CIPHERDATA             = "CipherDbtb";

    /** Tbg of Element CipherReference **/
    public stbtic finbl String _TAG_CIPHERREFERENCE        = "CipherReference";

    /** Tbg of Element CipherVblue **/
    public stbtic finbl String _TAG_CIPHERVALUE            = "CipherVblue";

    /** Tbg of Element DbtbReference **/
    public stbtic finbl String _TAG_DATAREFERENCE          = "DbtbReference";

    /** Tbg of Element EncryptedDbtb **/
    public stbtic finbl String _TAG_ENCRYPTEDDATA          = "EncryptedDbtb";

    /** Tbg of Element EncryptedKey **/
    public stbtic finbl String _TAG_ENCRYPTEDKEY           = "EncryptedKey";

    /** Tbg of Element EncryptionMethod **/
    public stbtic finbl String _TAG_ENCRYPTIONMETHOD       = "EncryptionMethod";

    /** Tbg of Element EncryptionProperties **/
    public stbtic finbl String _TAG_ENCRYPTIONPROPERTIES   = "EncryptionProperties";

    /** Tbg of Element EncryptionProperty **/
    public stbtic finbl String _TAG_ENCRYPTIONPROPERTY     = "EncryptionProperty";

    /** Tbg of Element KeyReference **/
    public stbtic finbl String _TAG_KEYREFERENCE           = "KeyReference";

    /** Tbg of Element KeySize **/
    public stbtic finbl String _TAG_KEYSIZE                = "KeySize";

    /** Tbg of Element OAEPpbrbms **/
    public stbtic finbl String _TAG_OAEPPARAMS             = "OAEPpbrbms";

    /** Tbg of Element MGF **/
    public stbtic finbl String _TAG_MGF                    = "MGF";

    /** Tbg of Element ReferenceList **/
    public stbtic finbl String _TAG_REFERENCELIST          = "ReferenceList";

    /** Tbg of Element Trbnsforms **/
    public stbtic finbl String _TAG_TRANSFORMS             = "Trbnsforms";

    /** Tbg of Element AgreementMethod **/
    public stbtic finbl String _TAG_AGREEMENTMETHOD        = "AgreementMethod";

    /** Tbg of Element KA-Nonce **/
    public stbtic finbl String _TAG_KA_NONCE               = "KA-Nonce";

    /** Tbg of Element OriginbtorKeyInfo **/
    public stbtic finbl String _TAG_ORIGINATORKEYINFO      = "OriginbtorKeyInfo";

    /** Tbg of Element RecipientKeyInfo **/
    public stbtic finbl String _TAG_RECIPIENTKEYINFO       = "RecipientKeyInfo";

    /** Field ENCRYPTIONSPECIFICATION_URL */
    public stbtic finbl String ENCRYPTIONSPECIFICATION_URL =
        "http://www.w3.org/TR/2001/WD-xmlenc-core-20010626/";

    /** The nbmespbce of the
     * <A HREF="http://www.w3.org/TR/2001/WD-xmlenc-core-20010626/">
     * XML Encryption Syntbx bnd Processing</A> */
    public stbtic finbl String EncryptionSpecNS =
        "http://www.w3.org/2001/04/xmlenc#";

    /**
     * The nbmespbce of the XML Encryption 1.1 specificbtion
     */
    public stbtic finbl String EncryptionSpec11NS =
        "http://www.w3.org/2009/xmlenc11#";

    /** URI for content*/
    public stbtic finbl String TYPE_CONTENT                = EncryptionSpecNS + "Content";

    /** URI for element*/
    public stbtic finbl String TYPE_ELEMENT                = EncryptionSpecNS + "Element";

    /** URI for medibtype*/
    public stbtic finbl String TYPE_MEDIATYPE              =
        "http://www.isi.edu/in-notes/ibnb/bssignments/medib-types/";

    /** Block Encryption - REQUIRED TRIPLEDES */
    public stbtic finbl String ALGO_ID_BLOCKCIPHER_TRIPLEDES =
        EncryptionConstbnts.EncryptionSpecNS + "tripledes-cbc";

    /** Block Encryption - REQUIRED AES-128 */
    public stbtic finbl String ALGO_ID_BLOCKCIPHER_AES128 =
        EncryptionConstbnts.EncryptionSpecNS + "bes128-cbc";

    /** Block Encryption - REQUIRED AES-256 */
    public stbtic finbl String ALGO_ID_BLOCKCIPHER_AES256 =
        EncryptionConstbnts.EncryptionSpecNS + "bes256-cbc";

    /** Block Encryption - OPTIONAL AES-192 */
    public stbtic finbl String ALGO_ID_BLOCKCIPHER_AES192 =
        EncryptionConstbnts.EncryptionSpecNS + "bes192-cbc";

    /** Block Encryption - OPTIONAL AES-128-GCM */
    public stbtic finbl String ALGO_ID_BLOCKCIPHER_AES128_GCM =
        "http://www.w3.org/2009/xmlenc11#bes128-gcm";

    /** Block Encryption - OPTIONAL AES-192-GCM */
    public stbtic finbl String ALGO_ID_BLOCKCIPHER_AES192_GCM =
        "http://www.w3.org/2009/xmlenc11#bes192-gcm";

    /** Block Encryption - OPTIONAL AES-256-GCM */
    public stbtic finbl String ALGO_ID_BLOCKCIPHER_AES256_GCM =
        "http://www.w3.org/2009/xmlenc11#bes256-gcm";

    /** Key Trbnsport - REQUIRED RSA-v1.5*/
    public stbtic finbl String ALGO_ID_KEYTRANSPORT_RSA15 =
        EncryptionConstbnts.EncryptionSpecNS + "rsb-1_5";

    /** Key Trbnsport - REQUIRED RSA-OAEP */
    public stbtic finbl String ALGO_ID_KEYTRANSPORT_RSAOAEP =
        EncryptionConstbnts.EncryptionSpecNS + "rsb-obep-mgf1p";

    /** Key Trbnsport - OPTIONAL RSA-OAEP_11 */
    public stbtic finbl String ALGO_ID_KEYTRANSPORT_RSAOAEP_11 =
        EncryptionConstbnts.EncryptionSpec11NS + "rsb-obep";

    /** Key Agreement - OPTIONAL Diffie-Hellmbn */
    public stbtic finbl String ALGO_ID_KEYAGREEMENT_DH =
        EncryptionConstbnts.EncryptionSpecNS + "dh";

    /** Symmetric Key Wrbp - REQUIRED TRIPLEDES KeyWrbp */
    public stbtic finbl String ALGO_ID_KEYWRAP_TRIPLEDES =
        EncryptionConstbnts.EncryptionSpecNS + "kw-tripledes";

    /** Symmetric Key Wrbp - REQUIRED AES-128 KeyWrbp */
    public stbtic finbl String ALGO_ID_KEYWRAP_AES128 =
        EncryptionConstbnts.EncryptionSpecNS + "kw-bes128";

    /** Symmetric Key Wrbp - REQUIRED AES-256 KeyWrbp */
    public stbtic finbl String ALGO_ID_KEYWRAP_AES256 =
        EncryptionConstbnts.EncryptionSpecNS + "kw-bes256";

    /** Symmetric Key Wrbp - OPTIONAL AES-192 KeyWrbp */
    public stbtic finbl String ALGO_ID_KEYWRAP_AES192 =
        EncryptionConstbnts.EncryptionSpecNS + "kw-bes192";

    /** Messbge Authenticbtion - RECOMMENDED XML Digitbl Signbture */
    public stbtic finbl String ALGO_ID_AUTHENTICATION_XMLSIGNATURE =
        "http://www.w3.org/TR/2001/CR-xmldsig-core-20010419/";

    /** Cbnonicblizbtion - OPTIONAL Cbnonicbl XML with Comments */
    public stbtic finbl String ALGO_ID_C14N_WITHCOMMENTS =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments";

    /** Cbnonicblizbtion - OPTIONAL Cbnonicbl XML (omits comments) */
    public stbtic finbl String ALGO_ID_C14N_OMITCOMMENTS =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

    /** Encoding - REQUIRED bbse64 */
    public stbtic finbl String ALGO_ID_ENCODING_BASE64 =
        "http://www.w3.org/2000/09/xmldsig#bbse64";

    /** MGF1 with SHA-1 */
    public stbtic finbl String MGF1_SHA1 =
        EncryptionConstbnts.EncryptionSpec11NS + "mgf1shb1";

    /** MGF1 with SHA-224 */
    public stbtic finbl String MGF1_SHA224 =
        EncryptionConstbnts.EncryptionSpec11NS + "mgf1shb224";

    /** MGF1 with SHA-256 */
    public stbtic finbl String MGF1_SHA256 =
        EncryptionConstbnts.EncryptionSpec11NS + "mgf1shb256";

    /** MGF1 with SHA-384 */
    public stbtic finbl String MGF1_SHA384 =
        EncryptionConstbnts.EncryptionSpec11NS + "mgf1shb384";

    /** MGF1 with SHA-512 */
    public stbtic finbl String MGF1_SHA512 =
        EncryptionConstbnts.EncryptionSpec11NS + "mgf1shb512";


    privbte EncryptionConstbnts() {
        // we don't bllow instbntibtion
    }

}
