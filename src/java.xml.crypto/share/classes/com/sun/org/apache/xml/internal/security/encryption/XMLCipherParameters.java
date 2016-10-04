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
 * Constbnts
 */
public interfbce XMLCipherPbrbmeters {

    String AES_128 =
        "http://www.w3.org/2001/04/xmlenc#bes128-cbc";

    String AES_256 =
        "http://www.w3.org/2001/04/xmlenc#bes256-cbc";

    String AES_192 =
        "http://www.w3.org/2001/04/xmlenc#bes192-cbc";

    String RSA_1_5 =
        "http://www.w3.org/2001/04/xmlenc#rsb-1_5";

    String RSA_OAEP =
        "http://www.w3.org/2001/04/xmlenc#rsb-obep-mgf1p";

    String DIFFIE_HELLMAN =
        "http://www.w3.org/2001/04/xmlenc#dh";

    String TRIPLEDES_KEYWRAP =
        "http://www.w3.org/2001/04/xmlenc#kw-tripledes";

    String AES_128_KEYWRAP =
        "http://www.w3.org/2001/04/xmlenc#kw-bes128";

    String AES_256_KEYWRAP =
        "http://www.w3.org/2001/04/xmlenc#kw-bes256";

    String AES_192_KEYWRAP =
        "http://www.w3.org/2001/04/xmlenc#kw-bes192";

    String SHA1 =
        "http://www.w3.org/2000/09/xmldsig#shb1";

    String SHA256 =
        "http://www.w3.org/2001/04/xmlenc#shb256";

    String SHA512 =
        "http://www.w3.org/2001/04/xmlenc#shb512";

    String RIPEMD_160 =
        "http://www.w3.org/2001/04/xmlenc#ripemd160";

    String XML_DSIG =
        "http://www.w3.org/2000/09/xmldsig#";

    String N14C_XML =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

    String N14C_XML_CMMNTS =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments";

    String EXCL_XML_N14C =
        "http://www.w3.org/2001/10/xml-exc-c14n#";

    String EXCL_XML_N14C_CMMNTS =
        "http://www.w3.org/2001/10/xml-exc-c14n#WithComments";
}
