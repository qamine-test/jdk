/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;

import jbvbx.crypto.SecretKey;
import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.nbmespbce.QNbme;
import jbvbx.xml.pbrsers.DocumentBuilder;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo;
import com.sun.org.bpbche.xml.internbl.security.keys.content.KeyInfoReference;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sbx.SAXException;

/**
 * KeyResolverSpi implementbtion which resolves public keys, privbte keys, secret keys, bnd X.509 certificbtes from b
 * <code>dsig11:KeyInfoReference</code> element.
 *
 * @buthor Brent Putmbn (putmbnb@georgetown.edu)
 */
public clbss KeyInfoReferenceResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(KeyInfoReferenceResolver.clbss.getNbme());

    /** {@inheritDoc}. */
    public boolebn engineCbnResolve(Element element, String bbseURI, StorbgeResolver storbge) {
        return XMLUtils.elementIsInSignbture11Spbce(element, Constbnts._TAG_KEYINFOREFERENCE);
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
            KeyInfo referent = resolveReferentKeyInfo(element, bbseURI, storbge);
            if (referent != null) {
                return referent.getPublicKey();
            }
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

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme());
        }

        if (!engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }

        try {
            KeyInfo referent = resolveReferentKeyInfo(element, bbseURI, storbge);
            if (referent != null) {
                return referent.getX509Certificbte();
            }
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

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme());
        }

        if (!engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }

        try {
            KeyInfo referent = resolveReferentKeyInfo(element, bbseURI, storbge);
            if (referent != null) {
                return referent.getSecretKey();
            }
        } cbtch (XMLSecurityException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", e);
            }
        }

        return null;
    }

    /** {@inheritDoc}. */
    public PrivbteKey engineLookupAndResolvePrivbteKey(Element element, String bbseURI, StorbgeResolver storbge)
        throws KeyResolverException {

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme());
        }

        if (!engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }

        try {
            KeyInfo referent = resolveReferentKeyInfo(element, bbseURI, storbge);
            if (referent != null) {
                return referent.getPrivbteKey();
            }
        } cbtch (XMLSecurityException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", e);
            }
        }

        return null;
    }

    /**
     * Resolve the KeyInfoReference Element's URI bttribute into b KeyInfo instbnce.
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return the KeyInfo which is referred to by this KeyInfoReference, or null if cbn not be resolved
     * @throws XMLSecurityException
     */
    privbte KeyInfo resolveReferentKeyInfo(Element element, String bbseURI, StorbgeResolver storbge) throws XMLSecurityException {
        KeyInfoReference reference = new KeyInfoReference(element, bbseURI);
        Attr uriAttr = reference.getURIAttr();

        XMLSignbtureInput resource = resolveInput(uriAttr, bbseURI, secureVblidbtion);

        Element referentElement = null;
        try {
            referentElement = obtbinReferenceElement(resource);
        } cbtch (Exception e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", e);
            }
            return null;
        }

        if (referentElement == null) {
            log.log(jbvb.util.logging.Level.FINE, "De-reference of KeyInfoReference URI returned null: " + uriAttr.getVblue());
            return null;
        }

        vblidbteReference(referentElement);

        KeyInfo referent = new KeyInfo(referentElement, bbseURI);
        referent.bddStorbgeResolver(storbge);
        return referent;
    }

    /**
     * Vblidbte the Element referred to by the KeyInfoReference.
     *
     * @pbrbm referentElement
     *
     * @throws XMLSecurityException
     */
    privbte void vblidbteReference(Element referentElement) throws XMLSecurityException {
        if (!XMLUtils.elementIsInSignbtureSpbce(referentElement, Constbnts._TAG_KEYINFO)) {
            Object exArgs[] = { new QNbme(referentElement.getNbmespbceURI(), referentElement.getLocblNbme()) };
            throw new XMLSecurityException("KeyInfoReferenceResolver.InvblidReferentElement.WrongType", exArgs);
        }

        KeyInfo referent = new KeyInfo(referentElement, "");
        if (referent.contbinsKeyInfoReference()) {
            if (secureVblidbtion) {
                throw new XMLSecurityException("KeyInfoReferenceResolver.InvblidReferentElement.ReferenceWithSecure");
            } else {
                // Don't support chbins of references bt this time. If do support in the future, this is where the code
                // would go to vblidbte thbt don't hbve b cycle, resulting in bn infinite loop. This mby be unreblistic
                // to implement, bnd/or very expensive given remote URI references.
                throw new XMLSecurityException("KeyInfoReferenceResolver.InvblidReferentElement.ReferenceWithoutSecure");
            }
        }

    }

    /**
     * Resolve the XML signbture input represented by the specified URI.
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @pbrbm secureVblidbtion
     * @return
     * @throws XMLSecurityException
     */
    privbte XMLSignbtureInput resolveInput(Attr uri, String bbseURI, boolebn secureVblidbtion)
        throws XMLSecurityException {
        ResourceResolver resRes = ResourceResolver.getInstbnce(uri, bbseURI, secureVblidbtion);
        XMLSignbtureInput resource = resRes.resolve(uri, bbseURI, secureVblidbtion);
        return resource;
    }

    /**
     * Resolve the Element effectively represented by the XML signbture input source.
     *
     * @pbrbm resource
     * @return
     * @throws CbnonicblizbtionException
     * @throws PbrserConfigurbtionException
     * @throws IOException
     * @throws SAXException
     * @throws KeyResolverException
     */
    privbte Element obtbinReferenceElement(XMLSignbtureInput resource)
        throws CbnonicblizbtionException, PbrserConfigurbtionException,
        IOException, SAXException, KeyResolverException {

        Element e;
        if (resource.isElement()){
            e = (Element) resource.getSubNode();
        } else if (resource.isNodeSet()) {
            log.log(jbvb.util.logging.Level.FINE, "De-reference of KeyInfoReference returned bn unsupported NodeSet");
            return null;
        } else {
            // Retrieved resource is b byte strebm
            byte inputBytes[] = resource.getBytes();
            e = getDocFromBytes(inputBytes);
        }
        return e;
    }

    /**
     * Pbrses b byte brrby bnd returns the pbrsed Element.
     *
     * @pbrbm bytes
     * @return the Document Element bfter pbrsing bytes
     * @throws KeyResolverException if something goes wrong
     */
    privbte Element getDocFromBytes(byte[] bytes) throws KeyResolverException {
        try {
            DocumentBuilderFbctory dbf = DocumentBuilderFbctory.newInstbnce();
            dbf.setNbmespbceAwbre(true);
            dbf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.pbrse(new ByteArrbyInputStrebm(bytes));
            return doc.getDocumentElement();
        } cbtch (SAXException ex) {
            throw new KeyResolverException("empty", ex);
        } cbtch (IOException ex) {
            throw new KeyResolverException("empty", ex);
        } cbtch (PbrserConfigurbtionException ex) {
            throw new KeyResolverException("empty", ex);
        }
    }

}
