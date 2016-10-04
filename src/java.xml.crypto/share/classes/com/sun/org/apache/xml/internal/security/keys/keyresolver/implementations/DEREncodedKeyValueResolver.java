/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;

import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.DEREncodedKeyVblue;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * KeyResolverSpi implementbtion which resolves public keys from b
 * <code>dsig11:DEREncodedKeyVblue</code> element.
 *
 * @buthor Brent Putmbn (putmbnb@georgetown.edu)
 */
public clbss DEREncodedKeyVblueResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(DEREncodedKeyVblueResolver.clbss.getNbme());

    /** {@inheritDoc}. */
    public boolebn engineCbnResolve(Element element, String bbseURI, StorbgeResolver storbge) {
        return XMLUtils.elementIsInSignbture11Spbce(element, Constbnts._TAG_DERENCODEDKEYVALUE);
    }

    /** {@inheritDoc}. */
    public PublicKey engineLookupAndResolvePublicKey(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme());
        }

        if (!engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }

        try {
            DEREncodedKeyVblue derKeyVblue = new DEREncodedKeyVblue(element, bbseURI);
            return derKeyVblue.getPublicKey();
        } cbtch (XMLSecurityException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", e);
            }
        }

        return null;
    }

    /** {@inheritDoc}. */
    public X509Certificbte engineLookupResolveX509Certificbte(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {
        return null;
    }

    /** {@inheritDoc}. */
    public SecretKey engineLookupAndResolveSecretKey(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {
        return null;
    }

    /** {@inheritDoc}. */
    public PrivbteKey engineLookupAndResolvePrivbteKey(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {
        return null;
    }



}
