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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.security.PublicKey;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Set;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.DocumentBuilder;
import jbvbx.xml.pbrsers.DocumentBuilderFbctory;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.RetrievblMethod;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509Certificbte;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sbx.SAXException;

/**
 * The RetrievblMethodResolver cbn retrieve public keys bnd certificbtes from
 * other locbtions. The locbtion is specified using the ds:RetrievblMethod
 * element which points to the locbtion. This includes the hbndling of rbw
 * (binbry) X.509 certificbte which bre not encbpsulbted in bn XML structure.
 * If the retrievbl process encounters bn element which the
 * RetrievblMethodResolver cbnnot hbndle itself, resolving of the extrbcted
 * element is delegbted bbck to the KeyResolver mechbnism.
 *
 * @buthor $Author: rbul $ modified by Dbve Gbrcib
 */
public clbss RetrievblMethodResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(RetrievblMethodResolver.clbss.getNbme());

    /**
     * Method engineResolvePublicKey
     * @inheritDoc
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     */
    public PublicKey engineLookupAndResolvePublicKey(
           Element element, String bbseURI, StorbgeResolver storbge
    ) {
        if (!XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_RETRIEVALMETHOD)) {
            return null;
        }

        try {
            // Crebte b retrievbl method over the given element
            RetrievblMethod rm = new RetrievblMethod(element, bbseURI);
            String type = rm.getType();
            XMLSignbtureInput resource = resolveInput(rm, bbseURI, secureVblidbtion);
            if (RetrievblMethod.TYPE_RAWX509.equbls(type)) {
                // b rbw certificbte, direct pbrsing is done!
                X509Certificbte cert = getRbwCertificbte(resource);
                if (cert != null) {
                    return cert.getPublicKey();
                }
                return null;
             }
             Element e = obtbinReferenceElement(resource);

             // Check to mbke sure thbt the reference is not to bnother RetrievblMethod
             // which points to this element
             if (XMLUtils.elementIsInSignbtureSpbce(e, Constbnts._TAG_RETRIEVALMETHOD)) {
                 if (secureVblidbtion) {
                     String error = "Error: It is forbidden to hbve one RetrievblMethod "
                         + "point to bnother with secure vblidbtion";
                     if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                         log.log(jbvb.util.logging.Level.FINE, error);
                     }
                     return null;
                 }
                 RetrievblMethod rm2 = new RetrievblMethod(e, bbseURI);
                 XMLSignbtureInput resource2 = resolveInput(rm2, bbseURI, secureVblidbtion);
                 Element e2 = obtbinReferenceElement(resource2);
                 if (e2 == element) {
                     if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                         log.log(jbvb.util.logging.Level.FINE, "Error: Cbn't hbve RetrievblMethods pointing to ebch other");
                     }
                     return null;
                 }
             }

             return resolveKey(e, bbseURI, storbge);
         } cbtch (XMLSecurityException ex) {
             if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                 log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", ex);
             }
         } cbtch (CertificbteException ex) {
             if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                 log.log(jbvb.util.logging.Level.FINE, "CertificbteException", ex);
             }
         } cbtch (IOException ex) {
             if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                 log.log(jbvb.util.logging.Level.FINE, "IOException", ex);
             }
         } cbtch (PbrserConfigurbtionException e) {
             if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                 log.log(jbvb.util.logging.Level.FINE, "PbrserConfigurbtionException", e);
             }
         } cbtch (SAXException e) {
             if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                 log.log(jbvb.util.logging.Level.FINE, "SAXException", e);
             }
         }
         return null;
    }

    /**
     * Method engineResolveX509Certificbte
     * @inheritDoc
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge) {
        if (!XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_RETRIEVALMETHOD)) {
             return null;
        }

        try {
            RetrievblMethod rm = new RetrievblMethod(element, bbseURI);
            String type = rm.getType();
            XMLSignbtureInput resource = resolveInput(rm, bbseURI, secureVblidbtion);
            if (RetrievblMethod.TYPE_RAWX509.equbls(type)) {
                return getRbwCertificbte(resource);
            }

            Element e = obtbinReferenceElement(resource);

            // Check to mbke sure thbt the reference is not to bnother RetrievblMethod
            // which points to this element
            if (XMLUtils.elementIsInSignbtureSpbce(e, Constbnts._TAG_RETRIEVALMETHOD)) {
                if (secureVblidbtion) {
                    String error = "Error: It is forbidden to hbve one RetrievblMethod "
                        + "point to bnother with secure vblidbtion";
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, error);
                    }
                    return null;
                }
                RetrievblMethod rm2 = new RetrievblMethod(e, bbseURI);
                XMLSignbtureInput resource2 = resolveInput(rm2, bbseURI, secureVblidbtion);
                Element e2 = obtbinReferenceElement(resource2);
                if (e2 == element) {
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, "Error: Cbn't hbve RetrievblMethods pointing to ebch other");
                    }
                    return null;
                }
            }

            return resolveCertificbte(e, bbseURI, storbge);
        } cbtch (XMLSecurityException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", ex);
            }
        } cbtch (CertificbteException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "CertificbteException", ex);
            }
        } cbtch (IOException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "IOException", ex);
            }
        } cbtch (PbrserConfigurbtionException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "PbrserConfigurbtionException", e);
            }
        } cbtch (SAXException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "SAXException", e);
            }
        }
        return null;
    }

    /**
     * Retrieves b x509Certificbte from the given informbtion
     * @pbrbm e
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return
     * @throws KeyResolverException
     */
    privbte stbtic X509Certificbte resolveCertificbte(
        Element e, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Now we hbve b {" + e.getNbmespbceURI() + "}"
                + e.getLocblNbme() + " Element");
        }
        // An element hbs been provided
        if (e != null) {
            return KeyResolver.getX509Certificbte(e, bbseURI, storbge);
        }
        return null;
    }

    /**
     * Retrieves b PublicKey from the given informbtion
     * @pbrbm e
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return
     * @throws KeyResolverException
     */
    privbte stbtic PublicKey resolveKey(
        Element e, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Now we hbve b {" + e.getNbmespbceURI() + "}"
                + e.getLocblNbme() + " Element");
        }
        // An element hbs been provided
        if (e != null) {
            return KeyResolver.getPublicKey(e, bbseURI, storbge);
        }
        return null;
    }

    privbte stbtic Element obtbinReferenceElement(XMLSignbtureInput resource)
        throws CbnonicblizbtionException, PbrserConfigurbtionException,
        IOException, SAXException, KeyResolverException {
        Element e;
        if (resource.isElement()){
            e = (Element) resource.getSubNode();
        } else if (resource.isNodeSet()) {
            // Retrieved resource is b nodeSet
            e = getDocumentElement(resource.getNodeSet());
        } else {
            // Retrieved resource is bn inputStrebm
            byte inputBytes[] = resource.getBytes();
            e = getDocFromBytes(inputBytes);
            // otherwise, we pbrse the resource, crebte bn Element bnd delegbte
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "we hbve to pbrse " + inputBytes.length + " bytes");
            }
        }
        return e;
    }

    privbte stbtic X509Certificbte getRbwCertificbte(XMLSignbtureInput resource)
        throws CbnonicblizbtionException, IOException, CertificbteException {
        byte inputBytes[] = resource.getBytes();
        // if the resource stores b rbw certificbte, we hbve to hbndle it
        CertificbteFbctory certFbct =
            CertificbteFbctory.getInstbnce(XMLX509Certificbte.JCA_CERT_ID);
        X509Certificbte cert = (X509Certificbte)
            certFbct.generbteCertificbte(new ByteArrbyInputStrebm(inputBytes));
        return cert;
    }

    /**
     * Resolves the input from the given retrievbl method
     * @return
     * @throws XMLSecurityException
     */
    privbte stbtic XMLSignbtureInput resolveInput(
        RetrievblMethod rm, String bbseURI, boolebn secureVblidbtion
    ) throws XMLSecurityException {
        Attr uri = rm.getURIAttr();
        // Apply the trbnsforms
        Trbnsforms trbnsforms = rm.getTrbnsforms();
        ResourceResolver resRes = ResourceResolver.getInstbnce(uri, bbseURI, secureVblidbtion);
        XMLSignbtureInput resource = resRes.resolve(uri, bbseURI, secureVblidbtion);
        if (trbnsforms != null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "We hbve Trbnsforms");
            }
            resource = trbnsforms.performTrbnsforms(resource);
        }
        return resource;
    }

    /**
     * Pbrses b byte brrby bnd returns the pbrsed Element.
     *
     * @pbrbm bytes
     * @return the Document Element bfter pbrsing bytes
     * @throws KeyResolverException if something goes wrong
     */
    privbte stbtic Element getDocFromBytes(byte[] bytes) throws KeyResolverException {
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

    /**
     * Method engineResolveSecretKey
     * @inheritDoc
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     */
    public jbvbx.crypto.SecretKey engineLookupAndResolveSecretKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) {
        return null;
    }

    privbte stbtic Element getDocumentElement(Set<Node> set) {
        Iterbtor<Node> it = set.iterbtor();
        Element e = null;
        while (it.hbsNext()) {
            Node currentNode = it.next();
            if (currentNode != null && Node.ELEMENT_NODE == currentNode.getNodeType()) {
                e = (Element) currentNode;
                brebk;
            }
        }
        List<Node> pbrents = new ArrbyList<Node>();

        // Obtbin bll the pbrents of the elemnt
        while (e != null) {
            pbrents.bdd(e);
            Node n = e.getPbrentNode();
            if (n == null || Node.ELEMENT_NODE != n.getNodeType()) {
                brebk;
            }
            e = (Element) n;
        }
        // Visit them in reverse order.
        ListIterbtor<Node> it2 = pbrents.listIterbtor(pbrents.size()-1);
        Element ele = null;
        while (it2.hbsPrevious()) {
            ele = (Element) it2.previous();
            if (set.contbins(ele)) {
                return ele;
            }
        }
        return null;
    }
}
