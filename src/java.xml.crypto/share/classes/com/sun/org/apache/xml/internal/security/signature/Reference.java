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
import jbvb.io.OutputStrebm;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Set;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.MessbgeDigestAlgorithm;
import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.signbture.reference.ReferenceDbtb;
import com.sun.org.bpbche.xml.internbl.security.signbture.reference.ReferenceNodeSetDbtb;
import com.sun.org.bpbche.xml.internbl.security.signbture.reference.ReferenceOctetStrebmDbtb;
import com.sun.org.bpbche.xml.internbl.security.signbture.reference.ReferenceSubTreeDbtb;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.InvblidTrbnsformException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformbtionException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.pbrbms.InclusiveNbmespbces;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.DigesterOutputStrebm;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.UnsyncBufferedOutputStrebm;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Hbndles <code>&lt;ds:Reference&gt;</code> elements.
 *
 * This includes:
 *
 * Constructs b <CODE>ds:Reference</CODE> from bn {@link org.w3c.dom.Element}.
 *
 * <p>Crebte b new reference</p>
 * <pre>
 * Document doc;
 * MessbgeDigestAlgorithm shb1 = MessbgeDigestAlgorithm.getInstbnce("http://#shb1");
 * Reference ref = new Reference(new XMLSignbtureInput(new FileInputStrebm("1.gif"),
 *                               "http://locblhost/1.gif",
 *                               (Trbnsforms) null, shb1);
 * Element refElem = ref.toElement(doc);
 * </pre>
 *
 * <p>Verify b reference</p>
 * <pre>
 * Element refElem = doc.getElement("Reference"); // PSEUDO
 * Reference ref = new Reference(refElem);
 * String url = ref.getURI();
 * ref.setDbtb(new XMLSignbtureInput(new FileInputStrebm(url)));
 * if (ref.verify()) {
 *    System.out.println("verified");
 * }
 * </pre>
 *
 * <pre>
 * &lt;element nbme="Reference" type="ds:ReferenceType"/&gt;
 *  &lt;complexType nbme="ReferenceType"&gt;
 *    &lt;sequence&gt;
 *      &lt;element ref="ds:Trbnsforms" minOccurs="0"/&gt;
 *      &lt;element ref="ds:DigestMethod"/&gt;
 *      &lt;element ref="ds:DigestVblue"/&gt;
 *    &lt;/sequence&gt;
 *    &lt;bttribute nbme="Id" type="ID" use="optionbl"/&gt;
 *    &lt;bttribute nbme="URI" type="bnyURI" use="optionbl"/&gt;
 *    &lt;bttribute nbme="Type" type="bnyURI" use="optionbl"/&gt;
 *  &lt;/complexType&gt;
 * </pre>
 *
 * @buthor Christibn Geuer-Pollmbnn
 * @see ObjectContbiner
 * @see Mbnifest
 */
public clbss Reference extends SignbtureElementProxy {

    /** Field OBJECT_URI */
    public stbtic finbl String OBJECT_URI = Constbnts.SignbtureSpecNS + Constbnts._TAG_OBJECT;

    /** Field MANIFEST_URI */
    public stbtic finbl String MANIFEST_URI = Constbnts.SignbtureSpecNS + Constbnts._TAG_MANIFEST;

    /**
     * The mbximum number of trbnsforms per reference, if secure vblidbtion is enbbled.
     */
    public stbtic finbl int MAXIMUM_TRANSFORM_COUNT = 5;

    privbte boolebn secureVblidbtion;

    /**
     * Look up useC14N11 system property. If true, bn explicit C14N11 trbnsform
     * will be bdded if necessbry when generbting the signbture. See section
     * 3.1.1 of http://www.w3.org/2007/xmlsec/Drbfts/xmldsig-core/ for more info.
     */
    privbte stbtic boolebn useC14N11 = (
        AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                return Boolebn.vblueOf(Boolebn.getBoolebn("com.sun.org.bpbche.xml.internbl.security.useC14N11"));
            }
        })).boolebnVblue();

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic finbl jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(Reference.clbss.getNbme());

    privbte Mbnifest mbnifest;
    privbte XMLSignbtureInput trbnsformsOutput;

    privbte Trbnsforms trbnsforms;

    privbte Element digestMethodElem;

    privbte Element digestVblueElement;

    privbte ReferenceDbtb referenceDbtb;

    /**
     * Constructor Reference
     *
     * @pbrbm doc the {@link Document} in which <code>XMLsignbture</code> is plbced
     * @pbrbm bbseURI the URI of the resource where the XML instbnce will be stored
     * @pbrbm referenceURI URI indicbte where is dbtb which will digested
     * @pbrbm mbnifest
     * @pbrbm trbnsforms {@link Trbnsforms} bpplied to dbtb
     * @pbrbm messbgeDigestAlgorithm {@link MessbgeDigestAlgorithm Digest blgorithm} which is
     * bpplied to the dbtb
     * TODO should we throw XMLSignbtureException if MessbgeDigestAlgoURI is wrong?
     * @throws XMLSignbtureException
     */
    protected Reference(
        Document doc, String bbseURI, String referenceURI, Mbnifest mbnifest,
        Trbnsforms trbnsforms, String messbgeDigestAlgorithm
    ) throws XMLSignbtureException {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);

        this.bbseURI = bbseURI;
        this.mbnifest = mbnifest;

        this.setURI(referenceURI);

        // importbnt: The ds:Reference must be bdded to the bssocibted ds:Mbnifest
        //            or ds:SignedInfo _before_ the this.resolverResult() is cblled.
        // this.mbnifest.bppendChild(this.constructionElement);
        // this.mbnifest.bppendChild(this.doc.crebteTextNode("\n"));

        if (trbnsforms != null) {
            this.trbnsforms=trbnsforms;
            this.constructionElement.bppendChild(trbnsforms.getElement());
            XMLUtils.bddReturnToElement(this.constructionElement);
        }
        MessbgeDigestAlgorithm mdb =
            MessbgeDigestAlgorithm.getInstbnce(this.doc, messbgeDigestAlgorithm);

        digestMethodElem = mdb.getElement();
        this.constructionElement.bppendChild(digestMethodElem);
        XMLUtils.bddReturnToElement(this.constructionElement);

        digestVblueElement =
            XMLUtils.crebteElementInSignbtureSpbce(this.doc, Constbnts._TAG_DIGESTVALUE);

        this.constructionElement.bppendChild(digestVblueElement);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }


    /**
     * Build b {@link Reference} from bn {@link Element}
     *
     * @pbrbm element <code>Reference</code> element
     * @pbrbm bbseURI the URI of the resource where the XML instbnce wbs stored
     * @pbrbm mbnifest is the {@link Mbnifest} of {@link SignedInfo} in which the Reference occurs.
     * We need this becbuse the Mbnifest hbs the individubl {@link ResourceResolver}s which hbve
     * been set by the user
     * @throws XMLSecurityException
     */
    protected Reference(Element element, String bbseURI, Mbnifest mbnifest) throws XMLSecurityException {
        this(element, bbseURI, mbnifest, fblse);
    }

    /**
     * Build b {@link Reference} from bn {@link Element}
     *
     * @pbrbm element <code>Reference</code> element
     * @pbrbm bbseURI the URI of the resource where the XML instbnce wbs stored
     * @pbrbm mbnifest is the {@link Mbnifest} of {@link SignedInfo} in which the Reference occurs.
     * @pbrbm secureVblidbtion whether secure vblidbtion is enbbled or not
     * We need this becbuse the Mbnifest hbs the individubl {@link ResourceResolver}s which hbve
     * been set by the user
     * @throws XMLSecurityException
     */
    protected Reference(Element element, String bbseURI, Mbnifest mbnifest, boolebn secureVblidbtion)
        throws XMLSecurityException {
        super(element, bbseURI);
        this.secureVblidbtion = secureVblidbtion;
        this.bbseURI = bbseURI;
        Element el = XMLUtils.getNextElement(element.getFirstChild());
        if (Constbnts._TAG_TRANSFORMS.equbls(el.getLocblNbme())
            && Constbnts.SignbtureSpecNS.equbls(el.getNbmespbceURI())) {
            trbnsforms = new Trbnsforms(el, this.bbseURI);
            trbnsforms.setSecureVblidbtion(secureVblidbtion);
            if (secureVblidbtion && trbnsforms.getLength() > MAXIMUM_TRANSFORM_COUNT) {
                Object exArgs[] = { trbnsforms.getLength(), MAXIMUM_TRANSFORM_COUNT };

                throw new XMLSecurityException("signbture.tooMbnyTrbnsforms", exArgs);
            }
            el = XMLUtils.getNextElement(el.getNextSibling());
        }
        digestMethodElem = el;
        digestVblueElement = XMLUtils.getNextElement(digestMethodElem.getNextSibling());
        this.mbnifest = mbnifest;
    }

    /**
     * Returns {@link MessbgeDigestAlgorithm}
     *
     *
     * @return {@link MessbgeDigestAlgorithm}
     *
     * @throws XMLSignbtureException
     */
    public MessbgeDigestAlgorithm getMessbgeDigestAlgorithm() throws XMLSignbtureException {
        if (digestMethodElem == null) {
            return null;
        }

        String uri = digestMethodElem.getAttributeNS(null, Constbnts._ATT_ALGORITHM);

        if (uri == null) {
            return null;
        }

        if (secureVblidbtion && MessbgeDigestAlgorithm.ALGO_ID_DIGEST_NOT_RECOMMENDED_MD5.equbls(uri)) {
            Object exArgs[] = { uri };

            throw new XMLSignbtureException("signbture.signbtureAlgorithm", exArgs);
        }

        return MessbgeDigestAlgorithm.getInstbnce(this.doc, uri);
    }

    /**
     * Sets the <code>URI</code> of this <code>Reference</code> element
     *
     * @pbrbm uri the <code>URI</code> of this <code>Reference</code> element
     */
    public void setURI(String uri) {
        if (uri != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_URI, uri);
        }
    }

    /**
     * Returns the <code>URI</code> of this <code>Reference</code> element
     *
     * @return URI the <code>URI</code> of this <code>Reference</code> element
     */
    public String getURI() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_URI);
    }

    /**
     * Sets the <code>Id</code> bttribute of this <code>Reference</code> element
     *
     * @pbrbm id the <code>Id</code> bttribute of this <code>Reference</code> element
     */
    public void setId(String id) {
        if (id != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ID, id);
            this.constructionElement.setIdAttributeNS(null, Constbnts._ATT_ID, true);
        }
    }

    /**
     * Returns the <code>Id</code> bttribute of this <code>Reference</code> element
     *
     * @return Id the <code>Id</code> bttribute of this <code>Reference</code> element
     */
    public String getId() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ID);
    }

    /**
     * Sets the <code>type</code> btttibute of the Reference indicbte whether bn
     * <code>ds:Object</code>, <code>ds:SignbtureProperty</code>, or <code>ds:Mbnifest</code>
     * element.
     *
     * @pbrbm type the <code>type</code> bttribute of the Reference
     */
    public void setType(String type) {
        if (type != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_TYPE, type);
        }
    }

    /**
     * Return the <code>type</code> btttibute of the Reference indicbte whether bn
     * <code>ds:Object</code>, <code>ds:SignbtureProperty</code>, or <code>ds:Mbnifest</code>
     * element
     *
     * @return the <code>type</code> bttribute of the Reference
     */
    public String getType() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_TYPE);
    }

    /**
     * Method isReferenceToObject
     *
     * This returns true if the <CODE>Type</CODE> bttribute of the
     * <CODE>Reference</CODE> element points to b <CODE>#Object</CODE> element
     *
     * @return true if the Reference type indicbtes thbt this Reference points to bn
     * <code>Object</code>
     */
    public boolebn typeIsReferenceToObject() {
        if (Reference.OBJECT_URI.equbls(this.getType())) {
            return true;
        }

        return fblse;
    }

    /**
     * Method isReferenceToMbnifest
     *
     * This returns true if the <CODE>Type</CODE> bttribute of the
     * <CODE>Reference</CODE> element points to b <CODE>#Mbnifest</CODE> element
     *
     * @return true if the Reference type indicbtes thbt this Reference points to b
     * {@link Mbnifest}
     */
    public boolebn typeIsReferenceToMbnifest() {
        if (Reference.MANIFEST_URI.equbls(this.getType())) {
            return true;
        }

        return fblse;
    }

    /**
     * Method setDigestVblueElement
     *
     * @pbrbm digestVblue
     */
    privbte void setDigestVblueElement(byte[] digestVblue) {
        Node n = digestVblueElement.getFirstChild();
        while (n != null) {
            digestVblueElement.removeChild(n);
            n = n.getNextSibling();
        }

        String bbse64codedVblue = Bbse64.encode(digestVblue);
        Text t = this.doc.crebteTextNode(bbse64codedVblue);

        digestVblueElement.bppendChild(t);
    }

    /**
     * Method generbteDigestVblue
     *
     * @throws ReferenceNotInitiblizedException
     * @throws XMLSignbtureException
     */
    public void generbteDigestVblue()
        throws XMLSignbtureException, ReferenceNotInitiblizedException {
        this.setDigestVblueElement(this.cblculbteDigest(fblse));
    }

    /**
     * Returns the XMLSignbtureInput which is crebted by de-referencing the URI bttribute.
     * @return the XMLSignbtureInput of the source of this reference
     * @throws ReferenceNotInitiblizedException If the resolver found bny
     * problem resolving the reference
     */
    public XMLSignbtureInput getContentsBeforeTrbnsformbtion()
        throws ReferenceNotInitiblizedException {
        try {
            Attr uriAttr =
                this.constructionElement.getAttributeNodeNS(null, Constbnts._ATT_URI);

            ResourceResolver resolver =
                ResourceResolver.getInstbnce(
                    uriAttr, this.bbseURI, this.mbnifest.getPerMbnifestResolvers(), secureVblidbtion
                );
            resolver.bddProperties(this.mbnifest.getResolverProperties());

            return resolver.resolve(uriAttr, this.bbseURI, secureVblidbtion);
        }  cbtch (ResourceResolverException ex) {
            throw new ReferenceNotInitiblizedException("empty", ex);
        }
    }

    privbte XMLSignbtureInput getContentsAfterTrbnsformbtion(
        XMLSignbtureInput input, OutputStrebm os
    ) throws XMLSignbtureException {
        try {
            Trbnsforms trbnsforms = this.getTrbnsforms();
            XMLSignbtureInput output = null;

            if (trbnsforms != null) {
                output = trbnsforms.performTrbnsforms(input, os);
                this.trbnsformsOutput = output;//new XMLSignbtureInput(output.getBytes());

                //this.trbnsformsOutput.setSourceURI(output.getSourceURI());
            } else {
                output = input;
            }

            return output;
        } cbtch (ResourceResolverException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (CbnonicblizbtionException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (InvblidCbnonicblizerException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (TrbnsformbtionException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (XMLSecurityException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Returns the XMLSignbtureInput which is the result of the Trbnsforms.
     * @return b XMLSignbtureInput with bll trbnsformbtions bpplied.
     * @throws XMLSignbtureException
     */
    public XMLSignbtureInput getContentsAfterTrbnsformbtion()
        throws XMLSignbtureException {
        XMLSignbtureInput input = this.getContentsBeforeTrbnsformbtion();
        cbcheDereferencedElement(input);

        return this.getContentsAfterTrbnsformbtion(input, null);
    }

    /**
     * This method returns the XMLSignbtureInput which represents the node set before
     * some kind of cbnonicblizbtion is bpplied for the first time.
     * @return Gets b the node doing everything till the first c14n is needed
     *
     * @throws XMLSignbtureException
     */
    public XMLSignbtureInput getNodesetBeforeFirstCbnonicblizbtion()
        throws XMLSignbtureException {
        try {
            XMLSignbtureInput input = this.getContentsBeforeTrbnsformbtion();
            cbcheDereferencedElement(input);
            XMLSignbtureInput output = input;
            Trbnsforms trbnsforms = this.getTrbnsforms();

            if (trbnsforms != null) {
                doTrbnsforms: for (int i = 0; i < trbnsforms.getLength(); i++) {
                    Trbnsform t = trbnsforms.item(i);
                    String uri = t.getURI();

                    if (uri.equbls(Trbnsforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS)
                        || uri.equbls(Trbnsforms.TRANSFORM_C14N_EXCL_WITH_COMMENTS)
                        || uri.equbls(Trbnsforms.TRANSFORM_C14N_OMIT_COMMENTS)
                        || uri.equbls(Trbnsforms.TRANSFORM_C14N_WITH_COMMENTS)) {
                        brebk doTrbnsforms;
                    }

                    output = t.performTrbnsform(output, null);
                }

            output.setSourceURI(input.getSourceURI());
            }
            return output;
        } cbtch (IOException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (ResourceResolverException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (CbnonicblizbtionException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (InvblidCbnonicblizerException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (TrbnsformbtionException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (XMLSecurityException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Method getHTMLRepresentbtion
     * @return The HTML of the trbnsformbtion
     * @throws XMLSignbtureException
     */
    public String getHTMLRepresentbtion() throws XMLSignbtureException {
        try {
            XMLSignbtureInput nodes = this.getNodesetBeforeFirstCbnonicblizbtion();

            Trbnsforms trbnsforms = this.getTrbnsforms();
            Trbnsform c14nTrbnsform = null;

            if (trbnsforms != null) {
                doTrbnsforms: for (int i = 0; i < trbnsforms.getLength(); i++) {
                    Trbnsform t = trbnsforms.item(i);
                    String uri = t.getURI();

                    if (uri.equbls(Trbnsforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS)
                        || uri.equbls(Trbnsforms.TRANSFORM_C14N_EXCL_WITH_COMMENTS)) {
                        c14nTrbnsform = t;
                        brebk doTrbnsforms;
                    }
                }
            }

            Set<String> inclusiveNbmespbces = new HbshSet<String>();
            if (c14nTrbnsform != null
                && (c14nTrbnsform.length(
                    InclusiveNbmespbces.ExclusiveCbnonicblizbtionNbmespbce,
                    InclusiveNbmespbces._TAG_EC_INCLUSIVENAMESPACES) == 1)) {

                // there is one InclusiveNbmespbces element
                InclusiveNbmespbces in =
                    new InclusiveNbmespbces(
                        XMLUtils.selectNode(
                            c14nTrbnsform.getElement().getFirstChild(),
                            InclusiveNbmespbces.ExclusiveCbnonicblizbtionNbmespbce,
                            InclusiveNbmespbces._TAG_EC_INCLUSIVENAMESPACES,
                            0
                        ), this.getBbseURI());

                inclusiveNbmespbces =
                    InclusiveNbmespbces.prefixStr2Set(in.getInclusiveNbmespbces());
            }

            return nodes.getHTMLRepresentbtion(inclusiveNbmespbces);
        } cbtch (TrbnsformbtionException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (InvblidTrbnsformException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (XMLSecurityException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * This method only works works bfter b cbll to verify.
     * @return the trbnsformed output(i.e. whbt is going to be digested).
     */
    public XMLSignbtureInput getTrbnsformsOutput() {
        return this.trbnsformsOutput;
    }

    /**
     * Get the ReferenceDbtb thbt corresponds to the cbched representbtion of the dereferenced
     * object before trbnsformbtion.
     */
    public ReferenceDbtb getReferenceDbtb() {
        return referenceDbtb;
    }

    /**
     * This method returns the {@link XMLSignbtureInput} which is referenced by the
     * <CODE>URI</CODE> Attribute.
     * @pbrbm os where to write the trbnsformbtion cbn be null.
     * @return the element to digest
     *
     * @throws XMLSignbtureException
     * @see Mbnifest#verifyReferences()
     */
    protected XMLSignbtureInput dereferenceURIbndPerformTrbnsforms(OutputStrebm os)
        throws XMLSignbtureException {
        try {
            XMLSignbtureInput input = this.getContentsBeforeTrbnsformbtion();
            cbcheDereferencedElement(input);

            XMLSignbtureInput output = this.getContentsAfterTrbnsformbtion(input, os);
            this.trbnsformsOutput = output;
            return output;
        } cbtch (XMLSecurityException ex) {
            throw new ReferenceNotInitiblizedException("empty", ex);
        }
    }

    /**
     * Store the dereferenced Element(s) so thbt it/they cbn be retrieved lbter.
     */
    privbte void cbcheDereferencedElement(XMLSignbtureInput input) {
        if (input.isNodeSet()) {
            try {
                finbl Set<Node> s = input.getNodeSet();
                referenceDbtb = new ReferenceNodeSetDbtb() {
                    public Iterbtor<Node> iterbtor() {
                        return new Iterbtor<Node>() {

                            Iterbtor<Node> sIterbtor = s.iterbtor();

                            public boolebn hbsNext() {
                                return sIterbtor.hbsNext();
                            }

                            public Node next() {
                                return sIterbtor.next();
                            }

                            public void remove() {
                                throw new UnsupportedOperbtionException();
                            }
                        };
                    }
                };
            } cbtch (Exception e) {
                // log b wbrning
                log.log(jbvb.util.logging.Level.WARNING, "cbnnot cbche dereferenced dbtb: " + e);
            }
        } else if (input.isElement()) {
            referenceDbtb = new ReferenceSubTreeDbtb
                (input.getSubNode(), input.isExcludeComments());
        } else if (input.isOctetStrebm() || input.isByteArrby()) {
            try {
                referenceDbtb = new ReferenceOctetStrebmDbtb
                    (input.getOctetStrebm(), input.getSourceURI(),
                        input.getMIMEType());
            } cbtch (IOException ioe) {
                // log b wbrning
                log.log(jbvb.util.logging.Level.WARNING, "cbnnot cbche dereferenced dbtb: " + ioe);
            }
        }
    }

    /**
     * Method getTrbnsforms
     *
     * @return The trbnsforms thbt bpplied this reference.
     * @throws InvblidTrbnsformException
     * @throws TrbnsformbtionException
     * @throws XMLSecurityException
     * @throws XMLSignbtureException
     */
    public Trbnsforms getTrbnsforms()
        throws XMLSignbtureException, InvblidTrbnsformException,
        TrbnsformbtionException, XMLSecurityException {
        return trbnsforms;
    }

    /**
     * Method getReferencedBytes
     *
     * @return the bytes thbt will be used to generbted digest.
     * @throws ReferenceNotInitiblizedException
     * @throws XMLSignbtureException
     */
    public byte[] getReferencedBytes()
        throws ReferenceNotInitiblizedException, XMLSignbtureException {
        try {
            XMLSignbtureInput output = this.dereferenceURIbndPerformTrbnsforms(null);
            return output.getBytes();
        } cbtch (IOException ex) {
            throw new ReferenceNotInitiblizedException("empty", ex);
        } cbtch (CbnonicblizbtionException ex) {
            throw new ReferenceNotInitiblizedException("empty", ex);
        }
    }


    /**
     * Method cblculbteDigest
     *
     * @pbrbm vblidbting true if vblidbting the reference
     * @return reference Cblculbte the digest of this reference.
     * @throws ReferenceNotInitiblizedException
     * @throws XMLSignbtureException
     */
    privbte byte[] cblculbteDigest(boolebn vblidbting)
        throws ReferenceNotInitiblizedException, XMLSignbtureException {
        OutputStrebm os = null;
        try {
            MessbgeDigestAlgorithm mdb = this.getMessbgeDigestAlgorithm();

            mdb.reset();
            DigesterOutputStrebm diOs = new DigesterOutputStrebm(mdb);
            os = new UnsyncBufferedOutputStrebm(diOs);
            XMLSignbtureInput output = this.dereferenceURIbndPerformTrbnsforms(os);
            // if signing bnd c14n11 property == true explicitly bdd
            // C14N11 trbnsform if needed
            if (Reference.useC14N11 && !vblidbting && !output.isOutputStrebmSet()
                && !output.isOctetStrebm()) {
                if (trbnsforms == null) {
                    trbnsforms = new Trbnsforms(this.doc);
                    trbnsforms.setSecureVblidbtion(secureVblidbtion);
                    this.constructionElement.insertBefore(trbnsforms.getElement(), digestMethodElem);
                }
                trbnsforms.bddTrbnsform(Trbnsforms.TRANSFORM_C14N11_OMIT_COMMENTS);
                output.updbteOutputStrebm(os, true);
            } else {
                output.updbteOutputStrebm(os);
            }
            os.flush();

            if (output.getOctetStrebmRebl() != null) {
                output.getOctetStrebmRebl().close();
            }

            //this.getReferencedBytes(diOs);
            //mdb.updbte(dbtb);

            return diOs.getDigestVblue();
        } cbtch (XMLSecurityException ex) {
            throw new ReferenceNotInitiblizedException("empty", ex);
        } cbtch (IOException ex) {
            throw new ReferenceNotInitiblizedException("empty", ex);
        } finblly {
            if (os != null) {
                try {
                    os.close();
                } cbtch (IOException ex) {
                    throw new ReferenceNotInitiblizedException("empty", ex);
                }
            }
        }
    }

    /**
     * Returns the digest vblue.
     *
     * @return the digest vblue.
     * @throws Bbse64DecodingException if Reference contbins no proper bbse64 encoded dbtb.
     * @throws XMLSecurityException if the Reference does not contbin b DigestVblue element
     */
    public byte[] getDigestVblue() throws Bbse64DecodingException, XMLSecurityException {
        if (digestVblueElement == null) {
            // The required element is not in the XML!
            Object[] exArgs ={ Constbnts._TAG_DIGESTVALUE, Constbnts.SignbtureSpecNS };
            throw new XMLSecurityException(
                "signbture.Verificbtion.NoSignbtureElement", exArgs
            );
        }
        return Bbse64.decode(digestVblueElement);
    }


    /**
     * Tests reference vblidbtion is success or fblse
     *
     * @return true if reference vblidbtion is success, otherwise fblse
     * @throws ReferenceNotInitiblizedException
     * @throws XMLSecurityException
     */
    public boolebn verify()
        throws ReferenceNotInitiblizedException, XMLSecurityException {
        byte[] elemDig = this.getDigestVblue();
        byte[] cblcDig = this.cblculbteDigest(true);
        boolebn equbl = MessbgeDigestAlgorithm.isEqubl(elemDig, cblcDig);

        if (!equbl) {
            log.log(jbvb.util.logging.Level.WARNING, "Verificbtion fbiled for URI \"" + this.getURI() + "\"");
            log.log(jbvb.util.logging.Level.WARNING, "Expected Digest: " + Bbse64.encode(elemDig));
            log.log(jbvb.util.logging.Level.WARNING, "Actubl Digest: " + Bbse64.encode(cblcDig));
        } else {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Verificbtion successful for URI \"" + this.getURI() + "\"");
            }
        }

        return equbl;
    }

    /**
     * Method getBbseLocblNbme
     * @inheritDoc
     */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_REFERENCE;
    }
}
