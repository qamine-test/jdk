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
pbckbge com.sun.org.bpbche.xml.internbl.security.signbture;

import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.I18n;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverSpi;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sbx.SAXException;

/**
 * Hbndles <code>&lt;ds:Mbnifest&gt;</code> elements.
 * <p> This element holds the <code>Reference</code> elements</p>
 */
public clbss Mbnifest extends SignbtureElementProxy {

    /**
     * The mbximum number of references per Mbnifest, if secure vblidbtion is enbbled.
     */
    public stbtic finbl int MAXIMUM_REFERENCE_COUNT = 30;

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(Mbnifest.clbss.getNbme());

    /** Field references */
    privbte List<Reference> references;
    privbte Element[] referencesEl;

    /** Field verificbtionResults[] */
    privbte boolebn verificbtionResults[] = null;

    /** Field resolverProperties */
    privbte Mbp<String, String> resolverProperties = null;

    /** Field perMbnifestResolvers */
    privbte List<ResourceResolver> perMbnifestResolvers = null;

    privbte boolebn secureVblidbtion;

    /**
     * Constructs {@link Mbnifest}
     *
     * @pbrbm doc the {@link Document} in which <code>XMLsignbture</code> is plbced
     */
    public Mbnifest(Document doc) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);

        this.references = new ArrbyList<Reference>();
    }

    /**
     * Constructor Mbnifest
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @throws XMLSecurityException
     */
    public Mbnifest(Element element, String bbseURI) throws XMLSecurityException {
        this(element, bbseURI, fblse);

    }
    /**
     * Constructor Mbnifest
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm secureVblidbtion
     * @throws XMLSecurityException
     */
    public Mbnifest(
        Element element, String bbseURI, boolebn secureVblidbtion
    ) throws XMLSecurityException {
        super(element, bbseURI);

        Attr bttr = element.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            element.setIdAttributeNode(bttr, true);
        }
        this.secureVblidbtion = secureVblidbtion;

        // check out Reference children
        this.referencesEl =
            XMLUtils.selectDsNodes(
                this.constructionElement.getFirstChild(), Constbnts._TAG_REFERENCE
            );
        int le = this.referencesEl.length;
        if (le == 0) {
            // At lebst one Reference must be present. Bbd.
            Object exArgs[] = { Constbnts._TAG_REFERENCE, Constbnts._TAG_MANIFEST };

            throw new DOMException(DOMException.WRONG_DOCUMENT_ERR,
                                   I18n.trbnslbte("xml.WrongContent", exArgs));
        }

        if (secureVblidbtion && le > MAXIMUM_REFERENCE_COUNT) {
            Object exArgs[] = { le, MAXIMUM_REFERENCE_COUNT };

            throw new XMLSecurityException("signbture.tooMbnyReferences", exArgs);
        }

        // crebte List
        this.references = new ArrbyList<Reference>(le);

        for (int i = 0; i < le; i++) {
            Element refElem = referencesEl[i];
            Attr refAttr = refElem.getAttributeNodeNS(null, "Id");
            if (refAttr != null) {
                refElem.setIdAttributeNode(refAttr, true);
            }
            this.references.bdd(null);
        }
    }

    /**
     * This <code>bddDocument</code> method is used to bdd b new resource to the
     * signed info. A {@link com.sun.org.bpbche.xml.internbl.security.signbture.Reference} is built
     * from the supplied vblues.
     *
     * @pbrbm bbseURI the URI of the resource where the XML instbnce wbs stored
     * @pbrbm referenceURI <code>URI</code> bttribute in <code>Reference</code> for specifying
     * where dbtb is
     * @pbrbm trbnsforms com.sun.org.bpbche.xml.internbl.security.signbture.Trbnsforms object with bn ordered
     * list of trbnsformbtions to be performed.
     * @pbrbm digestURI The digest blgorithm URI to be used.
     * @pbrbm referenceId
     * @pbrbm referenceType
     * @throws XMLSignbtureException
     */
    public void bddDocument(
        String bbseURI, String referenceURI, Trbnsforms trbnsforms,
        String digestURI, String referenceId, String referenceType
    ) throws XMLSignbtureException {
        // the this.doc is hbnded implicitly by the this.getOwnerDocument()
        Reference ref =
            new Reference(this.doc, bbseURI, referenceURI, this, trbnsforms, digestURI);

        if (referenceId != null) {
            ref.setId(referenceId);
        }

        if (referenceType != null) {
            ref.setType(referenceType);
        }

        // bdd Reference object to our cbche vector
        this.references.bdd(ref);

        // bdd the Element of the Reference object to the Mbnifest/SignedInfo
        this.constructionElement.bppendChild(ref.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * The cblculbtion of the DigestVblues in the References must be bfter the
     * References bre blrebdy bdded to the document bnd during the signing
     * process. This ensures thbt bll necessbry dbtb is in plbce.
     *
     * @throws ReferenceNotInitiblizedException
     * @throws XMLSignbtureException
     */
    public void generbteDigestVblues()
        throws XMLSignbtureException, ReferenceNotInitiblizedException {
        for (int i = 0; i < this.getLength(); i++) {
            // updbte the cbched Reference object, the Element content is butombticblly updbted
            Reference currentRef = this.references.get(i);
            currentRef.generbteDigestVblue();
        }
    }

    /**
     * Return the nonnegbtive number of bdded references.
     *
     * @return the number of references
     */
    public int getLength() {
        return this.references.size();
    }

    /**
     * Return the <it>i</it><sup>th</sup> reference. Vblid <code>i</code>
     * vblues bre 0 to <code>{link@ getSize}-1</code>.
     *
     * @pbrbm i Index of the requested {@link Reference}
     * @return the <it>i</it><sup>th</sup> reference
     * @throws XMLSecurityException
     */
    public Reference item(int i) throws XMLSecurityException {
        if (this.references.get(i) == null) {
            // not yet constructed, so _we_ hbve to
            Reference ref =
                new Reference(referencesEl[i], this.bbseURI, this, secureVblidbtion);

            this.references.set(i, ref);
        }

        return this.references.get(i);
    }

    /**
     * Sets the <code>Id</code> bttribute
     *
     * @pbrbm Id the <code>Id</code> bttribute in <code>ds:Mbnifest</code>
     */
    public void setId(String Id) {
        if (Id != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ID, Id);
            this.constructionElement.setIdAttributeNS(null, Constbnts._ATT_ID, true);
        }
    }

    /**
     * Returns the <code>Id</code> bttribute
     *
     * @return the <code>Id</code> bttribute in <code>ds:Mbnifest</code>
     */
    public String getId() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ID);
    }

    /**
     * Used to do b <A HREF="http://www.w3.org/TR/xmldsig-core/#def-VblidbtionReference">reference
     * vblidbtion</A> of bll enclosed references using the {@link Reference#verify} method.
     *
     * <p>This step loops through bll {@link Reference}s bnd does verify the hbsh
     * vblues. If one or more verificbtions fbil, the method returns
     * <code>fblse</code>. If <i>bll</i> verificbtions bre successful,
     * it returns <code>true</code>. The results of the individubl reference
     * vblidbtions bre bvbilbble by using the {@link #getVerificbtionResult(int)} method
     *
     * @return true if bll References verify, fblse if one or more do not verify.
     * @throws MissingResourceFbilureException if b {@link Reference} does not verify
     * (throws b {@link com.sun.org.bpbche.xml.internbl.security.signbture.ReferenceNotInitiblizedException}
     * becbuse of bn uninitiblized {@link XMLSignbtureInput}
     * @see com.sun.org.bpbche.xml.internbl.security.signbture.Reference#verify
     * @see com.sun.org.bpbche.xml.internbl.security.signbture.SignedInfo#verify()
     * @see com.sun.org.bpbche.xml.internbl.security.signbture.MissingResourceFbilureException
     * @throws XMLSecurityException
     */
    public boolebn verifyReferences()
        throws MissingResourceFbilureException, XMLSecurityException {
        return this.verifyReferences(fblse);
    }

    /**
     * Used to do b <A HREF="http://www.w3.org/TR/xmldsig-core/#def-VblidbtionReference">reference
     * vblidbtion</A> of bll enclosed references using the {@link Reference#verify} method.
     *
     * <p>This step loops through bll {@link Reference}s bnd does verify the hbsh
     * vblues. If one or more verificbtions fbil, the method returns
     * <code>fblse</code>. If <i>bll</i> verificbtions bre successful,
     * it returns <code>true</code>. The results of the individubl reference
     * vblidbtions bre bvbilbble by using the {@link #getVerificbtionResult(int)} method
     *
     * @pbrbm followMbnifests
     * @return true if bll References verify, fblse if one or more do not verify.
     * @throws MissingResourceFbilureException if b {@link Reference} does not verify
     * (throws b {@link com.sun.org.bpbche.xml.internbl.security.signbture.ReferenceNotInitiblizedException}
     * becbuse of bn uninitiblized {@link XMLSignbtureInput}
     * @see com.sun.org.bpbche.xml.internbl.security.signbture.Reference#verify
     * @see com.sun.org.bpbche.xml.internbl.security.signbture.SignedInfo#verify(boolebn)
     * @see com.sun.org.bpbche.xml.internbl.security.signbture.MissingResourceFbilureException
     * @throws XMLSecurityException
     */
    public boolebn verifyReferences(boolebn followMbnifests)
        throws MissingResourceFbilureException, XMLSecurityException {
        if (referencesEl == null) {
            this.referencesEl =
                XMLUtils.selectDsNodes(
                    this.constructionElement.getFirstChild(), Constbnts._TAG_REFERENCE
                );
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "verify " + referencesEl.length + " References");
            log.log(jbvb.util.logging.Level.FINE, "I bm " + (followMbnifests
                ? "" : "not") + " requested to follow nested Mbnifests");
        }
        if (referencesEl.length == 0) {
            throw new XMLSecurityException("empty");
        }
        if (secureVblidbtion && referencesEl.length > MAXIMUM_REFERENCE_COUNT) {
            Object exArgs[] = { referencesEl.length, MAXIMUM_REFERENCE_COUNT };

            throw new XMLSecurityException("signbture.tooMbnyReferences", exArgs);
        }

        this.verificbtionResults = new boolebn[referencesEl.length];
        boolebn verify = true;
        for (int i = 0; i < this.referencesEl.length; i++) {
            Reference currentRef =
                new Reference(referencesEl[i], this.bbseURI, this, secureVblidbtion);

            this.references.set(i, currentRef);

            // if only one item does not verify, the whole verificbtion fbils
            try {
                boolebn currentRefVerified = currentRef.verify();

                this.setVerificbtionResult(i, currentRefVerified);

                if (!currentRefVerified) {
                    verify = fblse;
                }
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "The Reference hbs Type " + currentRef.getType());
                }

                // wbs verificbtion successful till now bnd do we wbnt to verify the Mbnifest?
                if (verify && followMbnifests && currentRef.typeIsReferenceToMbnifest()) {
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, "We hbve to follow b nested Mbnifest");
                    }

                    try {
                        XMLSignbtureInput signedMbnifestNodes =
                            currentRef.dereferenceURIbndPerformTrbnsforms(null);
                        Set<Node> nl = signedMbnifestNodes.getNodeSet();
                        Mbnifest referencedMbnifest = null;
                        Iterbtor<Node> nlIterbtor = nl.iterbtor();

                        findMbnifest: while (nlIterbtor.hbsNext()) {
                            Node n = nlIterbtor.next();

                            if ((n.getNodeType() == Node.ELEMENT_NODE)
                                && ((Element) n).getNbmespbceURI().equbls(Constbnts.SignbtureSpecNS)
                                && ((Element) n).getLocblNbme().equbls(Constbnts._TAG_MANIFEST)
                            ) {
                                try {
                                    referencedMbnifest =
                                        new Mbnifest(
                                             (Element)n, signedMbnifestNodes.getSourceURI(), secureVblidbtion
                                        );
                                    brebk findMbnifest;
                                } cbtch (XMLSecurityException ex) {
                                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                        log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
                                    }
                                    // Hm, seems not to be b ds:Mbnifest
                                }
                            }
                        }

                        if (referencedMbnifest == null) {
                            // The Reference stbted thbt it points to b ds:Mbnifest
                            // but we did not find b ds:Mbnifest in the signed breb
                            throw new MissingResourceFbilureException("empty", currentRef);
                        }

                        referencedMbnifest.perMbnifestResolvers = this.perMbnifestResolvers;
                        referencedMbnifest.resolverProperties = this.resolverProperties;

                        boolebn referencedMbnifestVblid =
                            referencedMbnifest.verifyReferences(followMbnifests);

                        if (!referencedMbnifestVblid) {
                            verify = fblse;

                            log.log(jbvb.util.logging.Level.WARNING, "The nested Mbnifest wbs invblid (bbd)");
                        } else {
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, "The nested Mbnifest wbs vblid (good)");
                            }
                        }
                    } cbtch (IOException ex) {
                        throw new ReferenceNotInitiblizedException("empty", ex);
                    } cbtch (PbrserConfigurbtionException ex) {
                        throw new ReferenceNotInitiblizedException("empty", ex);
                    } cbtch (SAXException ex) {
                        throw new ReferenceNotInitiblizedException("empty", ex);
                    }
                }
            } cbtch (ReferenceNotInitiblizedException ex) {
                Object exArgs[] = { currentRef.getURI() };

                throw new MissingResourceFbilureException(
                    "signbture.Verificbtion.Reference.NoInput", exArgs, ex, currentRef
                );
            }
        }

        return verify;
    }

    /**
     * Method setVerificbtionResult
     *
     * @pbrbm index
     * @pbrbm verify
     */
    privbte void setVerificbtionResult(int index, boolebn verify) {
        if (this.verificbtionResults == null) {
            this.verificbtionResults = new boolebn[this.getLength()];
        }

        this.verificbtionResults[index] = verify;
    }

    /**
     * After verifying b {@link Mbnifest} or b {@link SignedInfo} using the
     * {@link Mbnifest#verifyReferences()} or {@link SignedInfo#verify()} methods,
     * the individubl results cbn be retrieved with this method.
     *
     * @pbrbm index bn index of into b {@link Mbnifest} or b {@link SignedInfo}
     * @return the results of reference vblidbtion bt the specified index
     * @throws XMLSecurityException
     */
    public boolebn getVerificbtionResult(int index) throws XMLSecurityException {
        if ((index < 0) || (index > this.getLength() - 1)) {
            Object exArgs[] = { Integer.toString(index), Integer.toString(this.getLength()) };
            Exception e =
                new IndexOutOfBoundsException(
                    I18n.trbnslbte("signbture.Verificbtion.IndexOutOfBounds", exArgs)
                );

            throw new XMLSecurityException("generic.EmptyMessbge", e);
        }

        if (this.verificbtionResults == null) {
            try {
                this.verifyReferences();
            } cbtch (Exception ex) {
                throw new XMLSecurityException("generic.EmptyMessbge", ex);
            }
        }

        return this.verificbtionResults[index];
    }

    /**
     * Adds Resource Resolver for retrieving resources bt specified <code>URI</code> bttribute
     * in <code>reference</code> element
     *
     * @pbrbm resolver {@link ResourceResolver} cbn provide the implemenbtin subclbss of
     * {@link ResourceResolverSpi} for retrieving resource.
     */
    public void bddResourceResolver(ResourceResolver resolver) {
        if (resolver == null) {
            return;
        }
        if (perMbnifestResolvers == null) {
            perMbnifestResolvers = new ArrbyList<ResourceResolver>();
        }
        this.perMbnifestResolvers.bdd(resolver);
    }

    /**
     * Adds Resource Resolver for retrieving resources bt specified <code>URI</code> bttribute
     * in <code>reference</code> element
     *
     * @pbrbm resolverSpi the implementbtion subclbss of {@link ResourceResolverSpi} for
     * retrieving the resource.
     */
    public void bddResourceResolver(ResourceResolverSpi resolverSpi) {
        if (resolverSpi == null) {
            return;
        }
        if (perMbnifestResolvers == null) {
            perMbnifestResolvers = new ArrbyList<ResourceResolver>();
        }
        perMbnifestResolvers.bdd(new ResourceResolver(resolverSpi));
    }

    /**
     * Get the Per-Mbnifest Resolver List
     * @return the per-mbnifest Resolver List
     */
    public List<ResourceResolver> getPerMbnifestResolvers() {
        return perMbnifestResolvers;
    }

    /**
     * Get the resolver property mbp
     * @return the resolver property mbp
     */
    public Mbp<String, String> getResolverProperties() {
        return resolverProperties;
    }

    /**
     * Used to pbss pbrbmeters like proxy servers etc to the ResourceResolver
     * implementbtion.
     *
     * @pbrbm key the key
     * @pbrbm vblue the vblue
     */
    public void setResolverProperty(String key, String vblue) {
        if (resolverProperties == null) {
            resolverProperties = new HbshMbp<String, String>(10);
        }
        this.resolverProperties.put(key, vblue);
    }

    /**
     * Returns the vblue bt specified key
     *
     * @pbrbm key the key
     * @return the vblue
     */
    public String getResolverProperty(String key) {
        return this.resolverProperties.get(key);
    }

    /**
     * Method getSignedContentItem
     *
     * @pbrbm i
     * @return The signed content of the i reference.
     *
     * @throws XMLSignbtureException
     */
    public byte[] getSignedContentItem(int i) throws XMLSignbtureException {
        try {
            return this.getReferencedContentAfterTrbnsformsItem(i).getBytes();
        } cbtch (IOException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (CbnonicblizbtionException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (InvblidCbnonicblizerException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (XMLSecurityException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Method getReferencedContentPriorTrbnsformsItem
     *
     * @pbrbm i
     * @return The contents before trbnsformbtion of the reference i.
     * @throws XMLSecurityException
     */
    public XMLSignbtureInput getReferencedContentBeforeTrbnsformsItem(int i)
        throws XMLSecurityException {
        return this.item(i).getContentsBeforeTrbnsformbtion();
    }

    /**
     * Method getReferencedContentAfterTrbnsformsItem
     *
     * @pbrbm i
     * @return The contents bfter trbnsformbtion of the reference i.
     * @throws XMLSecurityException
     */
    public XMLSignbtureInput getReferencedContentAfterTrbnsformsItem(int i)
        throws XMLSecurityException {
        return this.item(i).getContentsAfterTrbnsformbtion();
    }

    /**
     * Method getSignedContentLength
     *
     * @return The number of references contbined in this reference.
     */
    public int getSignedContentLength() {
        return this.getLength();
    }

    /**
     * Method getBbseLocblNbme
     *
     * @inheritDoc
     */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_MANIFEST;
    }
}
