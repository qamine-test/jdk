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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.keyvblues.DSAKeyVblue;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

public clbss DSAKeyVblueResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(DSAKeyVblueResolver.clbss.getNbme());


    /**
     * Method engineResolvePublicKey
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     * @return null if no {@link PublicKey} could be obtbined
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String BbseURI, StorbgeResolver storbge
    ) {
        if (element == null) {
            return null;
        }
        Element dsbKeyElement = null;
        boolebn isKeyVblue =
            XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYVALUE);
        if (isKeyVblue) {
            dsbKeyElement =
                XMLUtils.selectDsNode(element.getFirstChild(), Constbnts._TAG_DSAKEYVALUE, 0);
        } else if (XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_DSAKEYVALUE)) {
            // this trick is needed to bllow the RetrievblMethodResolver to ebt b
            // ds:DSAKeyVblue directly (without KeyVblue)
            dsbKeyElement = element;
        }

        if (dsbKeyElement == null) {
            return null;
        }

        try {
            DSAKeyVblue dsbKeyVblue = new DSAKeyVblue(dsbKeyElement, BbseURI);
            PublicKey pk = dsbKeyVblue.getPublicKey();

            return pk;
        } cbtch (XMLSecurityException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
            //do nothing
        }

        return null;
    }


    /** @inheritDoc */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String BbseURI, StorbgeResolver storbge
    ) {
        return null;
    }

    /** @inheritDoc */
    public jbvbx.crypto.SecretKey engineLookupAndResolveSecretKey(
        Element element, String BbseURI, StorbgeResolver storbge
    ) {
        return null;
    }
}
