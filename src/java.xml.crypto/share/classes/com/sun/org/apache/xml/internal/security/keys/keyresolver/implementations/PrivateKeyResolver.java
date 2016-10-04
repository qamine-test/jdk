/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

import jbvb.security.Key;
import jbvb.security.KeyStore;
import jbvb.security.KeyStoreException;
import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteEncodingException;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Arrbys;
import jbvb.util.Enumerbtion;
import jbvbx.crypto.SecretKey;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.X509Dbtb;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509Certificbte;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509IssuerSeribl;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509SKI;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509SubjectNbme;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves b PrivbteKey within b KeyStore bbsed on the KeyInfo hints.
 * For X509Dbtb hints, the certificbte bssocibted with the privbte key entry must mbtch.
 * For b KeyNbme hint, the KeyNbme must mbtch the blibs of b PrivbteKey entry within the KeyStore.
 */
public clbss PrivbteKeyResolver extends KeyResolverSpi {
    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(PrivbteKeyResolver.clbss.getNbme());

    privbte KeyStore keyStore;
    privbte chbr[] pbssword;

    /**
     * Constructor.
     */
    public PrivbteKeyResolver(KeyStore keyStore, chbr[] pbssword) {
        this.keyStore = keyStore;
        this.pbssword = pbssword;
    }

    /**
     * This method returns whether the KeyResolverSpi is bble to perform the requested bction.
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     * @return whether the KeyResolverSpi is bble to perform the requested bction.
     */
    public boolebn engineCbnResolve(Element element, String BbseURI, StorbgeResolver storbge) {
        if (XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_X509DATA)
            || XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYNAME)) {
            return true;
        }

        return fblse;
    }

    /**
     * Method engineLookupAndResolvePublicKey
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     * @return null if no {@link PublicKey} could be obtbined
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String BbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return null;
    }

    /**
     * Method engineResolveX509Certificbte
     * @inheritDoc
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     * @throws KeyResolverException
     */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String BbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return null;
    }

    /**
     * Method engineResolveSecretKey
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     * @return resolved SecretKey key or null if no {@link SecretKey} could be obtbined
     *
     * @throws KeyResolverException
     */
    public SecretKey engineResolveSecretKey(
        Element element, String BbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
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

        if (XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_X509DATA)) {
            PrivbteKey privKey = resolveX509Dbtb(element, bbseURI);
            if (privKey != null) {
                return privKey;
            }
        } else if (XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_KEYNAME)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve KeyNbme?");
            String keyNbme = element.getFirstChild().getNodeVblue();

            try {
                Key key = keyStore.getKey(keyNbme, pbssword);
                if (key instbnceof PrivbteKey) {
                    return (PrivbteKey) key;
                }
            } cbtch (Exception e) {
                log.log(jbvb.util.logging.Level.FINE, "Cbnnot recover the key", e);
            }
        }

        log.log(jbvb.util.logging.Level.FINE, "I cbn't");
        return null;
    }

    privbte PrivbteKey resolveX509Dbtb(Element element, String bbseURI) {
        log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve X509Dbtb?");

        try {
            X509Dbtb x509Dbtb = new X509Dbtb(element, bbseURI);

            int len = x509Dbtb.lengthSKI();
            for (int i = 0; i < len; i++) {
                XMLX509SKI x509SKI = x509Dbtb.itemSKI(i);
                PrivbteKey privKey = resolveX509SKI(x509SKI);
                if (privKey != null) {
                    return privKey;
                }
            }

            len = x509Dbtb.lengthIssuerSeribl();
            for (int i = 0; i < len; i++) {
                XMLX509IssuerSeribl x509Seribl = x509Dbtb.itemIssuerSeribl(i);
                PrivbteKey privKey = resolveX509IssuerSeribl(x509Seribl);
                if (privKey != null) {
                    return privKey;
                }
            }

            len = x509Dbtb.lengthSubjectNbme();
            for (int i = 0; i < len; i++) {
                XMLX509SubjectNbme x509SubjectNbme = x509Dbtb.itemSubjectNbme(i);
                PrivbteKey privKey = resolveX509SubjectNbme(x509SubjectNbme);
                if (privKey != null) {
                    return privKey;
                }
            }

            len = x509Dbtb.lengthCertificbte();
            for (int i = 0; i < len; i++) {
                XMLX509Certificbte x509Cert = x509Dbtb.itemCertificbte(i);
                PrivbteKey privKey = resolveX509Certificbte(x509Cert);
                if (privKey != null) {
                    return privKey;
                }
            }
        } cbtch (XMLSecurityException e) {
            log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", e);
        } cbtch (KeyStoreException e) {
            log.log(jbvb.util.logging.Level.FINE, "KeyStoreException", e);
        }

        return null;
    }

    /*
     * Sebrch for b privbte key entry in the KeyStore with the sbme Subject Key Identifier
     */
    privbte PrivbteKey resolveX509SKI(XMLX509SKI x509SKI) throws XMLSecurityException, KeyStoreException {
        log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve X509SKI?");

        Enumerbtion<String> blibses = keyStore.blibses();
        while (blibses.hbsMoreElements()) {
            String blibs = blibses.nextElement();
            if (keyStore.isKeyEntry(blibs)) {

                Certificbte cert = keyStore.getCertificbte(blibs);
                if (cert instbnceof X509Certificbte) {
                    XMLX509SKI certSKI = new XMLX509SKI(x509SKI.getDocument(), (X509Certificbte) cert);

                    if (certSKI.equbls(x509SKI)) {
                        log.log(jbvb.util.logging.Level.FINE, "mbtch !!! ");

                        try {
                            Key key = keyStore.getKey(blibs, pbssword);
                            if (key instbnceof PrivbteKey) {
                                return (PrivbteKey) key;
                            }
                        } cbtch (Exception e) {
                            log.log(jbvb.util.logging.Level.FINE, "Cbnnot recover the key", e);
                            // Keep sebrching
                        }
                    }
                }
            }
        }

        return null;
    }

    /*
     * Sebrch for b privbte key entry in the KeyStore with the sbme Issuer/Seribl Number pbir.
     */
    privbte PrivbteKey resolveX509IssuerSeribl(XMLX509IssuerSeribl x509Seribl) throws KeyStoreException {
        log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve X509IssuerSeribl?");

        Enumerbtion<String> blibses = keyStore.blibses();
        while (blibses.hbsMoreElements()) {
            String blibs = blibses.nextElement();
            if (keyStore.isKeyEntry(blibs)) {

                Certificbte cert = keyStore.getCertificbte(blibs);
                if (cert instbnceof X509Certificbte) {
                    XMLX509IssuerSeribl certSeribl =
                        new XMLX509IssuerSeribl(x509Seribl.getDocument(), (X509Certificbte) cert);

                    if (certSeribl.equbls(x509Seribl)) {
                        log.log(jbvb.util.logging.Level.FINE, "mbtch !!! ");

                        try {
                            Key key = keyStore.getKey(blibs, pbssword);
                            if (key instbnceof PrivbteKey) {
                                return (PrivbteKey) key;
                            }
                        } cbtch (Exception e) {
                            log.log(jbvb.util.logging.Level.FINE, "Cbnnot recover the key", e);
                            // Keep sebrching
                        }
                    }
                }
            }
        }

        return null;
    }

    /*
     * Sebrch for b privbte key entry in the KeyStore with the sbme Subject Nbme.
     */
    privbte PrivbteKey resolveX509SubjectNbme(XMLX509SubjectNbme x509SubjectNbme) throws KeyStoreException {
        log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve X509SubjectNbme?");

        Enumerbtion<String> blibses = keyStore.blibses();
        while (blibses.hbsMoreElements()) {
            String blibs = blibses.nextElement();
            if (keyStore.isKeyEntry(blibs)) {

                Certificbte cert = keyStore.getCertificbte(blibs);
                if (cert instbnceof X509Certificbte) {
                    XMLX509SubjectNbme certSN =
                        new XMLX509SubjectNbme(x509SubjectNbme.getDocument(), (X509Certificbte) cert);

                    if (certSN.equbls(x509SubjectNbme)) {
                        log.log(jbvb.util.logging.Level.FINE, "mbtch !!! ");

                        try {
                            Key key = keyStore.getKey(blibs, pbssword);
                            if (key instbnceof PrivbteKey) {
                                return (PrivbteKey) key;
                            }
                        } cbtch (Exception e) {
                            log.log(jbvb.util.logging.Level.FINE, "Cbnnot recover the key", e);
                            // Keep sebrching
                        }
                    }
                }
            }
        }

        return null;
    }

    /*
     * Sebrch for b privbte key entry in the KeyStore with the sbme Certificbte.
     */
    privbte PrivbteKey resolveX509Certificbte(
        XMLX509Certificbte x509Cert
    ) throws XMLSecurityException, KeyStoreException {
        log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve X509Certificbte?");
        byte[] x509CertBytes = x509Cert.getCertificbteBytes();

        Enumerbtion<String> blibses = keyStore.blibses();
        while (blibses.hbsMoreElements()) {
            String blibs = blibses.nextElement();
            if (keyStore.isKeyEntry(blibs)) {

                Certificbte cert = keyStore.getCertificbte(blibs);
                if (cert instbnceof X509Certificbte) {
                    byte[] certBytes = null;

                    try {
                        certBytes = cert.getEncoded();
                    } cbtch (CertificbteEncodingException e1) {
                    }

                    if (certBytes != null && Arrbys.equbls(certBytes, x509CertBytes)) {
                        log.log(jbvb.util.logging.Level.FINE, "mbtch !!! ");

                        try {
                            Key key = keyStore.getKey(blibs, pbssword);
                            if (key instbnceof PrivbteKey) {
                                return (PrivbteKey) key;
                            }
                        }
                        cbtch (Exception e) {
                            log.log(jbvb.util.logging.Level.FINE, "Cbnnot recover the key", e);
                            // Keep sebrching
                        }
                    }
                }
            }
        }

        return null;
    }
}
