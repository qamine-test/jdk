/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

import jbvb.security.Key;
import jbvb.security.KeyStore;
import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;
import jbvbx.crypto.SecretKey;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves b SecretKey within b KeyStore bbsed on the KeyNbme.
 * The KeyNbme is the key entry blibs within the KeyStore.
 */
public clbss SecretKeyResolver extends KeyResolverSpi
{
    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(SecretKeyResolver.clbss.getNbme());

    privbte KeyStore keyStore;
    privbte chbr[] pbssword;

    /**
     * Constructor.
     */
    public SecretKeyResolver(KeyStore keyStore, chbr[] pbssword) {
        this.keyStore = keyStore;
        this.pbssword = pbssword;
    }

    /**
     * This method returns whether the KeyResolverSpi is bble to perform the requested bction.
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return whether the KeyResolverSpi is bble to perform the requested bction.
     */
    public boolebn engineCbnResolve(Element element, String bbseURI, StorbgeResolver storbge) {
        return XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYNAME);
    }

    /**
     * Method engineLookupAndResolvePublicKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return null if no {@link PublicKey} could be obtbined
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return null;
    }

    /**
     * Method engineResolveX509Certificbte
     * @inheritDoc
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @throws KeyResolverException
     */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return null;
    }

    /**
     * Method engineResolveSecretKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved SecretKey key or null if no {@link SecretKey} could be obtbined
     *
     * @throws KeyResolverException
     */
    public SecretKey engineResolveSecretKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme() + "?");
        }

        if (XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYNAME)) {
            String keyNbme = element.getFirstChild().getNodeVblue();
            try {
                Key key = keyStore.getKey(keyNbme, pbssword);
                if (key instbnceof SecretKey) {
                    return (SecretKey) key;
                }
            } cbtch (Exception e) {
                log.log(jbvb.util.logging.Level.FINE, "Cbnnot recover the key", e);
            }
        }

        log.log(jbvb.util.logging.Level.FINE, "I cbn't");
        return null;
    }

    /**
     * Method engineResolvePrivbteKey
     * @inheritDoc
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved PrivbteKey key or null if no {@link PrivbteKey} could be obtbined
     * @throws KeyResolverException
     */
    public PrivbteKey engineLookupAndResolvePrivbteKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return null;
    }
}
