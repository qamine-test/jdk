/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

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
 * Resolves b single Key bbsed on the KeyNbme.
 */
public clbss SingleKeyResolver extends KeyResolverSpi
{
    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(SingleKeyResolver.clbss.getNbme());

    privbte String keyNbme;
    privbte PublicKey publicKey;
    privbte PrivbteKey privbteKey;
    privbte SecretKey secretKey;

    /**
     * Constructor.
     * @pbrbm keyNbme
     * @pbrbm publicKey
     */
    public SingleKeyResolver(String keyNbme, PublicKey publicKey) {
        this.keyNbme = keyNbme;
        this.publicKey = publicKey;
    }

    /**
     * Constructor.
     * @pbrbm keyNbme
     * @pbrbm privbteKey
     */
    public SingleKeyResolver(String keyNbme, PrivbteKey privbteKey) {
        this.keyNbme = keyNbme;
        this.privbteKey = privbteKey;
    }

    /**
     * Constructor.
     * @pbrbm keyNbme
     * @pbrbm secretKey
     */
    public SingleKeyResolver(String keyNbme, SecretKey secretKey) {
        this.keyNbme = keyNbme;
        this.secretKey = secretKey;
    }

    /**
     * This method returns whether the KeyResolverSpi is bble to perform the requested bction.
     *
     * @pbrbm element
     * @pbrbm BbseURI
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
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme() + "?");
        }

        if (publicKey != null
            && XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYNAME)) {
            String nbme = element.getFirstChild().getNodeVblue();
            if (keyNbme.equbls(nbme)) {
                return publicKey;
            }
        }

        log.log(jbvb.util.logging.Level.FINE, "I cbn't");
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

        if (secretKey != null
            && XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYNAME)) {
            String nbme = element.getFirstChild().getNodeVblue();
            if (keyNbme.equbls(nbme)) {
                return secretKey;
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
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme() + "?");
        }

        if (privbteKey != null
            && XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYNAME)) {
            String nbme = element.getFirstChild().getNodeVblue();
            if (keyNbme.equbls(nbme)) {
                return privbteKey;
            }
        }

        log.log(jbvb.util.logging.Level.FINE, "I cbn't");
        return null;
    }
}
