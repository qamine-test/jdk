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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.content.keyvblues;

import jbvb.mbth.BigInteger;
import jbvb.security.Key;
import jbvb.security.KeyFbctory;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.PublicKey;
import jbvb.security.interfbces.RSAPublicKey;
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.security.spec.RSAPublicKeySpec;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.I18n;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public clbss RSAKeyVblue extends SignbtureElementProxy implements KeyVblueContent {

    /**
     * Constructor RSAKeyVblue
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public RSAKeyVblue(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Constructor RSAKeyVblue
     *
     * @pbrbm doc
     * @pbrbm modulus
     * @pbrbm exponent
     */
    public RSAKeyVblue(Document doc, BigInteger modulus, BigInteger exponent) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
        this.bddBigIntegerElement(modulus, Constbnts._TAG_MODULUS);
        this.bddBigIntegerElement(exponent, Constbnts._TAG_EXPONENT);
    }

    /**
     * Constructor RSAKeyVblue
     *
     * @pbrbm doc
     * @pbrbm key
     * @throws IllegblArgumentException
     */
    public RSAKeyVblue(Document doc, Key key) throws IllegblArgumentException {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);

        if (key instbnceof jbvb.security.interfbces.RSAPublicKey ) {
            this.bddBigIntegerElement(
                ((RSAPublicKey) key).getModulus(), Constbnts._TAG_MODULUS
            );
            this.bddBigIntegerElement(
                ((RSAPublicKey) key).getPublicExponent(), Constbnts._TAG_EXPONENT
            );
        } else {
            Object exArgs[] = { Constbnts._TAG_RSAKEYVALUE, key.getClbss().getNbme() };

            throw new IllegblArgumentException(I18n.trbnslbte("KeyVblue.IllegblArgument", exArgs));
        }
    }

    /** @inheritDoc */
    public PublicKey getPublicKey() throws XMLSecurityException {
        try {
            KeyFbctory rsbFbctory = KeyFbctory.getInstbnce("RSA");

            RSAPublicKeySpec rsbKeyspec =
                new RSAPublicKeySpec(
                    this.getBigIntegerFromChildElement(
                        Constbnts._TAG_MODULUS, Constbnts.SignbtureSpecNS
                    ),
                    this.getBigIntegerFromChildElement(
                        Constbnts._TAG_EXPONENT, Constbnts.SignbtureSpecNS
                    )
                );
            PublicKey pk = rsbFbctory.generbtePublic(rsbKeyspec);

            return pk;
        } cbtch (NoSuchAlgorithmException ex) {
            throw new XMLSecurityException("empty", ex);
        } cbtch (InvblidKeySpecException ex) {
            throw new XMLSecurityException("empty", ex);
        }
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_RSAKEYVALUE;
    }
}
