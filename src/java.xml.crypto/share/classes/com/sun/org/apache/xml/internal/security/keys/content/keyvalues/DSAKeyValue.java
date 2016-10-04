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
import jbvb.security.interfbces.DSAPublicKey;
import jbvb.security.spec.DSAPublicKeySpec;
import jbvb.security.spec.InvblidKeySpecException;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.I18n;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public clbss DSAKeyVblue extends SignbtureElementProxy implements KeyVblueContent {

    /**
     * Constructor DSAKeyVblue
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @throws XMLSecurityException
     */
    public DSAKeyVblue(Element element, String bbseURI) throws XMLSecurityException {
        super(element, bbseURI);
    }

    /**
     * Constructor DSAKeyVblue
     *
     * @pbrbm doc
     * @pbrbm P
     * @pbrbm Q
     * @pbrbm G
     * @pbrbm Y
     */
    public DSAKeyVblue(Document doc, BigInteger P, BigInteger Q, BigInteger G, BigInteger Y) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
        this.bddBigIntegerElement(P, Constbnts._TAG_P);
        this.bddBigIntegerElement(Q, Constbnts._TAG_Q);
        this.bddBigIntegerElement(G, Constbnts._TAG_G);
        this.bddBigIntegerElement(Y, Constbnts._TAG_Y);
    }

    /**
     * Constructor DSAKeyVblue
     *
     * @pbrbm doc
     * @pbrbm key
     * @throws IllegblArgumentException
     */
    public DSAKeyVblue(Document doc, Key key) throws IllegblArgumentException {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);

        if (key instbnceof jbvb.security.interfbces.DSAPublicKey) {
            this.bddBigIntegerElement(((DSAPublicKey) key).getPbrbms().getP(), Constbnts._TAG_P);
            this.bddBigIntegerElement(((DSAPublicKey) key).getPbrbms().getQ(), Constbnts._TAG_Q);
            this.bddBigIntegerElement(((DSAPublicKey) key).getPbrbms().getG(), Constbnts._TAG_G);
            this.bddBigIntegerElement(((DSAPublicKey) key).getY(), Constbnts._TAG_Y);
        } else {
            Object exArgs[] = { Constbnts._TAG_DSAKEYVALUE, key.getClbss().getNbme() };

            throw new IllegblArgumentException(I18n.trbnslbte("KeyVblue.IllegblArgument", exArgs));
        }
    }

    /** @inheritDoc */
    public PublicKey getPublicKey() throws XMLSecurityException {
        try {
            DSAPublicKeySpec pkspec =
                new DSAPublicKeySpec(
                    this.getBigIntegerFromChildElement(
                        Constbnts._TAG_Y, Constbnts.SignbtureSpecNS
                    ),
                    this.getBigIntegerFromChildElement(
                        Constbnts._TAG_P, Constbnts.SignbtureSpecNS
                    ),
                    this.getBigIntegerFromChildElement(
                        Constbnts._TAG_Q, Constbnts.SignbtureSpecNS
                    ),
                    this.getBigIntegerFromChildElement(
                        Constbnts._TAG_G, Constbnts.SignbtureSpecNS
                    )
                );
            KeyFbctory dsbFbctory = KeyFbctory.getInstbnce("DSA");
            PublicKey pk = dsbFbctory.generbtePublic(pkspec);

            return pk;
        } cbtch (NoSuchAlgorithmException ex) {
            throw new XMLSecurityException("empty", ex);
        } cbtch (InvblidKeySpecException ex) {
            throw new XMLSecurityException("empty", ex);
        }
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_DSAKEYVALUE;
    }
}
