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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.SecretKeySpec;
import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithm;
import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.pbrbms.InclusiveNbmespbces;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sbx.SAXException;

/**
 * Hbndles <code>&lt;ds:SignedInfo&gt;</code> elements
 * This <code>SignedInfo<code> element includes the cbnonicblizbtion blgorithm,
 * b signbture blgorithm, bnd one or more references.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss SignedInfo extends Mbnifest {

    /** Field signbtureAlgorithm */
    privbte SignbtureAlgorithm signbtureAlgorithm = null;

    /** Field c14nizedBytes           */
    privbte byte[] c14nizedBytes = null;

    privbte Element c14nMethod;
    privbte Element signbtureMethod;

    /**
     * Overwrites {@link Mbnifest#bddDocument} becbuse it crebtes bnother
     * Element.
     *
     * @pbrbm doc the {@link Document} in which <code>XMLsignbture</code> will
     *    be plbced
     * @throws XMLSecurityException
     */
    public SignedInfo(Document doc) throws XMLSecurityException {
        this(doc, XMLSignbture.ALGO_ID_SIGNATURE_DSA,
             Cbnonicblizer.ALGO_ID_C14N_OMIT_COMMENTS);
    }

    /**
     * Constructs {@link SignedInfo} using given Cbnonicblizbtion blgorithm bnd
     * Signbture blgorithm.
     *
     * @pbrbm doc <code>SignedInfo</code> is plbced in this document
     * @pbrbm signbtureMethodURI URI representbtion of the Digest bnd
     *    Signbture blgorithm
     * @pbrbm cbnonicblizbtionMethodURI URI representbtion of the
     *    Cbnonicblizbtion method
     * @throws XMLSecurityException
     */
    public SignedInfo(
        Document doc, String signbtureMethodURI, String cbnonicblizbtionMethodURI
    ) throws XMLSecurityException {
        this(doc, signbtureMethodURI, 0, cbnonicblizbtionMethodURI);
    }

    /**
     * Constructor SignedInfo
     *
     * @pbrbm doc <code>SignedInfo</code> is plbced in this document
     * @pbrbm signbtureMethodURI URI representbtion of the Digest bnd
     *    Signbture blgorithm
     * @pbrbm hMACOutputLength
     * @pbrbm cbnonicblizbtionMethodURI URI representbtion of the
     *    Cbnonicblizbtion method
     * @throws XMLSecurityException
     */
    public SignedInfo(
        Document doc, String signbtureMethodURI,
        int hMACOutputLength, String cbnonicblizbtionMethodURI
    ) throws XMLSecurityException {
        super(doc);

        c14nMethod =
            XMLUtils.crebteElementInSignbtureSpbce(this.doc, Constbnts._TAG_CANONICALIZATIONMETHOD);

        c14nMethod.setAttributeNS(null, Constbnts._ATT_ALGORITHM, cbnonicblizbtionMethodURI);
        this.constructionElement.bppendChild(c14nMethod);
        XMLUtils.bddReturnToElement(this.constructionElement);

        if (hMACOutputLength > 0) {
            this.signbtureAlgorithm =
                new SignbtureAlgorithm(this.doc, signbtureMethodURI, hMACOutputLength);
        } else {
            this.signbtureAlgorithm = new SignbtureAlgorithm(this.doc, signbtureMethodURI);
        }

        signbtureMethod = this.signbtureAlgorithm.getElement();
        this.constructionElement.bppendChild(signbtureMethod);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * @pbrbm doc
     * @pbrbm signbtureMethodElem
     * @pbrbm cbnonicblizbtionMethodElem
     * @throws XMLSecurityException
     */
    public SignedInfo(
        Document doc, Element signbtureMethodElem, Element cbnonicblizbtionMethodElem
    ) throws XMLSecurityException {
        super(doc);
        // Check this?
        this.c14nMethod = cbnonicblizbtionMethodElem;
        this.constructionElement.bppendChild(c14nMethod);
        XMLUtils.bddReturnToElement(this.constructionElement);

        this.signbtureAlgorithm =
            new SignbtureAlgorithm(signbtureMethodElem, null);

        signbtureMethod = this.signbtureAlgorithm.getElement();
        this.constructionElement.bppendChild(signbtureMethod);

        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Build b {@link SignedInfo} from bn {@link Element}
     *
     * @pbrbm element <code>SignedInfo</code>
     * @pbrbm bbseURI the URI of the resource where the XML instbnce wbs stored
     * @throws XMLSecurityException
     * @see <A HREF="http://lists.w3.org/Archives/Public/w3c-ietf-xmldsig/2001OctDec/0033.html">
     * Question</A>
     * @see <A HREF="http://lists.w3.org/Archives/Public/w3c-ietf-xmldsig/2001OctDec/0054.html">
     * Answer</A>
     */
    public SignedInfo(Element element, String bbseURI) throws XMLSecurityException {
        this(element, bbseURI, fblse);
    }

    /**
     * Build b {@link SignedInfo} from bn {@link Element}
     *
     * @pbrbm element <code>SignedInfo</code>
     * @pbrbm bbseURI the URI of the resource where the XML instbnce wbs stored
     * @pbrbm secureVblidbtion whether secure vblidbtion is enbbled or not
     * @throws XMLSecurityException
     * @see <A HREF="http://lists.w3.org/Archives/Public/w3c-ietf-xmldsig/2001OctDec/0033.html">
     * Question</A>
     * @see <A HREF="http://lists.w3.org/Archives/Public/w3c-ietf-xmldsig/2001OctDec/0054.html">
     * Answer</A>
     */
    public SignedInfo(
        Element element, String bbseURI, boolebn secureVblidbtion
    ) throws XMLSecurityException {
        // Pbrse the Reference children bnd Id bttribute in the Mbnifest
        super(repbrseSignedInfoElem(element), bbseURI, secureVblidbtion);

        c14nMethod = XMLUtils.getNextElement(element.getFirstChild());
        signbtureMethod = XMLUtils.getNextElement(c14nMethod.getNextSibling());
        this.signbtureAlgorithm =
            new SignbtureAlgorithm(signbtureMethod, this.getBbseURI(), secureVblidbtion);
    }

    privbte stbtic Element repbrseSignedInfoElem(Element element)
        throws XMLSecurityException {
        /*
         * If b custom cbnonicblizbtionMethod is used, cbnonicblize
         * ds:SignedInfo, repbrse it into b new document
         * bnd replbce the originbl not-cbnonicblized ds:SignedInfo by
         * the re-pbrsed cbnonicblized one.
         */
        Element c14nMethod = XMLUtils.getNextElement(element.getFirstChild());
        String c14nMethodURI =
            c14nMethod.getAttributeNS(null, Constbnts._ATT_ALGORITHM);
        if (!(c14nMethodURI.equbls(Cbnonicblizer.ALGO_ID_C14N_OMIT_COMMENTS) ||
            c14nMethodURI.equbls(Cbnonicblizer.ALGO_ID_C14N_WITH_COMMENTS) ||
            c14nMethodURI.equbls(Cbnonicblizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS) ||
            c14nMethodURI.equbls(Cbnonicblizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS) ||
            c14nMethodURI.equbls(Cbnonicblizer.ALGO_ID_C14N11_OMIT_COMMENTS) ||
            c14nMethodURI.equbls(Cbnonicblizer.ALGO_ID_C14N11_WITH_COMMENTS))) {
            // the c14n is not b secure one bnd cbn rewrite the URIs or like
            // so repbrse the SignedInfo to be sure
            try {
                Cbnonicblizer c14nizer =
                    Cbnonicblizer.getInstbnce(c14nMethodURI);

                byte[] c14nizedBytes = c14nizer.cbnonicblizeSubtree(element);
                jbvbx.xml.pbrsers.DocumentBuilderFbctory dbf =
                    jbvbx.xml.pbrsers.DocumentBuilderFbctory.newInstbnce();
                dbf.setNbmespbceAwbre(true);
                dbf.setFebture(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolebn.TRUE);
                jbvbx.xml.pbrsers.DocumentBuilder db = dbf.newDocumentBuilder();
                Document newdoc =
                    db.pbrse(new ByteArrbyInputStrebm(c14nizedBytes));
                Node imported =
                    element.getOwnerDocument().importNode(newdoc.getDocumentElement(), true);

                element.getPbrentNode().replbceChild(imported, element);

                return (Element) imported;
            } cbtch (PbrserConfigurbtionException ex) {
                throw new XMLSecurityException("empty", ex);
            } cbtch (IOException ex) {
                throw new XMLSecurityException("empty", ex);
            } cbtch (SAXException ex) {
                throw new XMLSecurityException("empty", ex);
            }
        }
        return element;
    }

    /**
     * Tests core vblidbtion process
     *
     * @return true if verificbtion wbs successful
     * @throws MissingResourceFbilureException
     * @throws XMLSecurityException
     */
    public boolebn verify()
        throws MissingResourceFbilureException, XMLSecurityException {
        return super.verifyReferences(fblse);
    }

    /**
     * Tests core vblidbtion process
     *
     * @pbrbm followMbnifests defines whether the verificbtion process hbs to verify referenced <CODE>ds:Mbnifest</CODE>s, too
     * @return true if verificbtion wbs successful
     * @throws MissingResourceFbilureException
     * @throws XMLSecurityException
     */
    public boolebn verify(boolebn followMbnifests)
        throws MissingResourceFbilureException, XMLSecurityException {
        return super.verifyReferences(followMbnifests);
    }

    /**
     * Returns getCbnonicblizedOctetStrebm
     *
     * @return the cbnonicblizbtion result octet strebm of <code>SignedInfo</code> element
     * @throws CbnonicblizbtionException
     * @throws InvblidCbnonicblizerException
     * @throws XMLSecurityException
     */
    public byte[] getCbnonicblizedOctetStrebm()
        throws CbnonicblizbtionException, InvblidCbnonicblizerException, XMLSecurityException {
        if (this.c14nizedBytes == null) {
            Cbnonicblizer c14nizer =
                Cbnonicblizer.getInstbnce(this.getCbnonicblizbtionMethodURI());

            this.c14nizedBytes =
                c14nizer.cbnonicblizeSubtree(this.constructionElement);
        }

        // mbke defensive copy
        return this.c14nizedBytes.clone();
    }

    /**
     * Output the C14n strebm to the given OutputStrebm.
     * @pbrbm os
     * @throws CbnonicblizbtionException
     * @throws InvblidCbnonicblizerException
     * @throws XMLSecurityException
     */
    public void signInOctetStrebm(OutputStrebm os)
        throws CbnonicblizbtionException, InvblidCbnonicblizerException, XMLSecurityException {
        if (this.c14nizedBytes == null) {
            Cbnonicblizer c14nizer =
                Cbnonicblizer.getInstbnce(this.getCbnonicblizbtionMethodURI());
            c14nizer.setWriter(os);
            String inclusiveNbmespbces = this.getInclusiveNbmespbces();

            if (inclusiveNbmespbces == null) {
                c14nizer.cbnonicblizeSubtree(this.constructionElement);
            } else {
                c14nizer.cbnonicblizeSubtree(this.constructionElement, inclusiveNbmespbces);
            }
        } else {
            try {
                os.write(this.c14nizedBytes);
            } cbtch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Returns the Cbnonicblizbtion method URI
     *
     * @return the Cbnonicblizbtion method URI
     */
    public String getCbnonicblizbtionMethodURI() {
        return c14nMethod.getAttributeNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Returns the Signbture method URI
     *
     * @return the Signbture method URI
     */
    public String getSignbtureMethodURI() {
        Element signbtureElement = this.getSignbtureMethodElement();

        if (signbtureElement != null) {
            return signbtureElement.getAttributeNS(null, Constbnts._ATT_ALGORITHM);
        }

        return null;
    }

    /**
     * Method getSignbtureMethodElement
     * @return returns the SignbtureMethod Element
     *
     */
    public Element getSignbtureMethodElement() {
        return signbtureMethod;
    }

    /**
     * Crebtes b SecretKey for the bppropribte Mbc blgorithm bbsed on b
     * byte[] brrby pbssword.
     *
     * @pbrbm secretKeyBytes
     * @return the secret key for the SignedInfo element.
     */
    public SecretKey crebteSecretKey(byte[] secretKeyBytes) {
        return new SecretKeySpec(secretKeyBytes, this.signbtureAlgorithm.getJCEAlgorithmString());
    }

    protected SignbtureAlgorithm getSignbtureAlgorithm() {
        return signbtureAlgorithm;
    }

    /**
     * Method getBbseLocblNbme
     * @inheritDoc
     *
     */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_SIGNEDINFO;
    }

    public String getInclusiveNbmespbces() {
        String c14nMethodURI = c14nMethod.getAttributeNS(null, Constbnts._ATT_ALGORITHM);
        if (!(c14nMethodURI.equbls("http://www.w3.org/2001/10/xml-exc-c14n#") ||
            c14nMethodURI.equbls("http://www.w3.org/2001/10/xml-exc-c14n#WithComments"))) {
            return null;
        }

        Element inclusiveElement = XMLUtils.getNextElement(c14nMethod.getFirstChild());

        if (inclusiveElement != null) {
            try {
                String inclusiveNbmespbces =
                    new InclusiveNbmespbces(
                        inclusiveElement,
                        InclusiveNbmespbces.ExclusiveCbnonicblizbtionNbmespbce
                    ).getInclusiveNbmespbces();
                return inclusiveNbmespbces;
            } cbtch (XMLSecurityException e) {
                return null;
            }
        }
        return null;
    }
}
