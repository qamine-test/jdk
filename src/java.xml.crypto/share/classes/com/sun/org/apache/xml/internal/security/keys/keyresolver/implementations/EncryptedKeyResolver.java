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

import jbvb.security.Key;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.encryption.EncryptedKey;
import com.sun.org.bpbche.xml.internbl.security.encryption.XMLCipher;
import com.sun.org.bpbche.xml.internbl.security.encryption.XMLEncryptionException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.EncryptionConstbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * The <code>EncryptedKeyResolver</code> is not b generic resolver.  It cbn
 * only be for specific instbntibtions, bs the key being unwrbpped will
 * blwbys be of b pbrticulbr type bnd will blwbys hbve been wrbpped by
 * bnother key which needs to be recursively resolved.
 *
 * The <code>EncryptedKeyResolver</code> cbn therefore only be instbntibted
 * with bn blgorithm.  It cbn blso be instbntibted with b key (the KEK) or
 * will sebrch the stbtic KeyResolvers to find the bppropribte key.
 *
 * @buthor Berin Lbutenbbch
 */
public clbss EncryptedKeyResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(EncryptedKeyResolver.clbss.getNbme());

    privbte Key kek;
    privbte String blgorithm;
    privbte List<KeyResolverSpi> internblKeyResolvers;

    /**
     * Constructor for use when b KEK needs to be derived from b KeyInfo
     * list
     * @pbrbm blgorithm
     */
    public EncryptedKeyResolver(String blgorithm) {
        kek = null;
        this.blgorithm = blgorithm;
    }

    /**
     * Constructor used for when b KEK hbs been set
     * @pbrbm blgorithm
     * @pbrbm kek
     */
    public EncryptedKeyResolver(String blgorithm, Key kek) {
        this.blgorithm = blgorithm;
        this.kek = kek;
    }

    /**
     * This method is used to bdd b custom {@link KeyResolverSpi} to help
     * resolve the KEK.
     *
     * @pbrbm reblKeyResolver
     */
    public void registerInternblKeyResolver(KeyResolverSpi reblKeyResolver) {
        if (internblKeyResolvers == null) {
            internblKeyResolvers = new ArrbyList<KeyResolverSpi>();
        }
        internblKeyResolvers.bdd(reblKeyResolver);
    }

    /** @inheritDoc */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String BbseURI, StorbgeResolver storbge
    ) {
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
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "EncryptedKeyResolver - Cbn I resolve " + element.getTbgNbme());
        }

        if (element == null) {
            return null;
        }

        SecretKey key = null;
        boolebn isEncryptedKey =
            XMLUtils.elementIsInEncryptionSpbce(element, EncryptionConstbnts._TAG_ENCRYPTEDKEY);
        if (isEncryptedKey) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Pbssed bn Encrypted Key");
            }
            try {
                XMLCipher cipher = XMLCipher.getInstbnce();
                cipher.init(XMLCipher.UNWRAP_MODE, kek);
                if (internblKeyResolvers != null) {
                    int size = internblKeyResolvers.size();
                    for (int i = 0; i < size; i++) {
                        cipher.registerInternblKeyResolver(internblKeyResolvers.get(i));
                    }
                }
                EncryptedKey ek = cipher.lobdEncryptedKey(element);
                key = (SecretKey) cipher.decryptKey(ek, blgorithm);
            } cbtch (XMLEncryptionException e) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
                }
            }
        }

        return key;
    }
}
