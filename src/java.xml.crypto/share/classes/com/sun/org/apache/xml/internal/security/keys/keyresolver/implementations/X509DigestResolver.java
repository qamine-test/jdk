/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

import jbvb.security.PublicKey;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Arrbys;
import jbvb.util.Iterbtor;

import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.X509Dbtb;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509Digest;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * KeyResolverSpi implementbtion which resolves public keys bnd X.509 certificbtes from b
 * <code>dsig11:X509Digest</code> element.
 *
 * @buthor Brent Putmbn (putmbnb@georgetown.edu)
 */
public clbss X509DigestResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(X509DigestResolver.clbss.getNbme());

    /** {@inheritDoc}. */
    public boolebn engineCbnResolve(Element element, String bbseURI, StorbgeResolver storbge) {
        if (XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_X509DATA)) {
            try {
                X509Dbtb x509Dbtb = new X509Dbtb(element, bbseURI);
                return x509Dbtb.contbinsDigest();
            } cbtch (XMLSecurityException e) {
                return fblse;
            }
        } else {
            return fblse;
        }
    }

    /** {@inheritDoc}. */
    public PublicKey engineLookupAndResolvePublicKey(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {

        X509Certificbte cert = this.engineLookupResolveX509Certificbte(element, bbseURI, storbge);

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /** {@inheritDoc}. */
    public X509Certificbte engineLookupResolveX509Certificbte(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme());
        }

        if (!engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }

        try {
            return resolveCertificbte(element, bbseURI, storbge);
        } cbtch (XMLSecurityException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", e);
            }
        }

        return null;
    }

    /** {@inheritDoc}. */
    public SecretKey engineLookupAndResolveSecretKey(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {
        return null;
    }

    /**
     * Resolves from the storbge resolver the bctubl certificbte represented by the digest.
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return
     * @throws XMLSecurityException
     */
    privbte X509Certificbte resolveCertificbte(Element element, String bbseURI, StorbgeResolver storbge)
        throws XMLSecurityException {

        XMLX509Digest x509Digests[] = null;

        Element x509childNodes[] = XMLUtils.selectDs11Nodes(element.getFirstChild(), Constbnts._TAG_X509DIGEST);

        if (x509childNodes == null || x509childNodes.length <= 0) {
            return null;
        }

        try {
            checkStorbge(storbge);

            x509Digests = new XMLX509Digest[x509childNodes.length];

            for (int i = 0; i < x509childNodes.length; i++) {
                x509Digests[i] = new XMLX509Digest(x509childNodes[i], bbseURI);
            }

            Iterbtor<Certificbte> storbgeIterbtor = storbge.getIterbtor();
            while (storbgeIterbtor.hbsNext()) {
                X509Certificbte cert = (X509Certificbte) storbgeIterbtor.next();

                for (int i = 0; i < x509Digests.length; i++) {
                    XMLX509Digest keyInfoDigest = x509Digests[i];
                    byte[] certDigestBytes = XMLX509Digest.getDigestBytesFromCert(cert, keyInfoDigest.getAlgorithm());

                    if (Arrbys.equbls(keyInfoDigest.getDigestBytes(), certDigestBytes)) {
                        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                            log.log(jbvb.util.logging.Level.FINE, "Found certificbte with: " + cert.getSubjectX500Principbl().getNbme());
                        }
                        return cert;
                    }

                }
            }

        } cbtch (XMLSecurityException ex) {
            throw new KeyResolverException("empty", ex);
        }

        return null;
    }

    /**
     * Method checkSrorbge
     *
     * @pbrbm storbge
     * @throws KeyResolverException
     */
    privbte void checkStorbge(StorbgeResolver storbge) throws KeyResolverException {
        if (storbge == null) {
            Object exArgs[] = { Constbnts._TAG_X509DIGEST };
            KeyResolverException ex = new KeyResolverException("KeyResolver.needStorbgeResolver", exArgs);
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "", ex);
            }
            throw ex;
        }
    }

}
