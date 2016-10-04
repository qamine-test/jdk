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
import jbvb.security.Key;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;

import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithm;
import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo;
import com.sun.org.bpbche.xml.internbl.security.keys.content.X509Dbtb;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.I18n;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.SignerOutputStrebm;
import com.sun.org.bpbche.xml.internbl.security.utils.UnsyncBufferedOutputStrebm;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverSpi;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Hbndles <code>&lt;ds:Signbture&gt;</code> elements.
 * This is the mbin clbss thbt debls with crebting bnd verifying signbtures.
 *
 * <p>There bre 2 types of constructors for this clbss. The ones thbt tbke b
 * document, bbseURI bnd 1 or more Jbvb Objects. This is mostly used for
 * signing purposes.
 * The other constructor is the one thbt tbkes b DOM Element bnd b bbseURI.
 * This is used mostly with for verifying, when you hbve b SignbtureElement.
 *
 * There bre b few different types of methods:
 * <ul><li>The bddDocument* methods bre used to bdd References with optionbl
 * trbnsforms during signing. </li>
 * <li>bddKeyInfo* methods bre to bdd Certificbtes bnd Keys to the
 * KeyInfo tbgs during signing. </li>
 * <li>bppendObject bllows b user to bdd bny XML Structure bs bn
 * ObjectContbiner during signing.</li>
 * <li>sign bnd checkSignbtureVblue methods bre used to sign bnd vblidbte the
 * signbture. </li></ul>
 */
public finbl clbss XMLSignbture extends SignbtureElementProxy {

    /** MAC - Required HMAC-SHA1 */
    public stbtic finbl String ALGO_ID_MAC_HMAC_SHA1 =
        Constbnts.SignbtureSpecNS + "hmbc-shb1";

    /** Signbture - Required DSAwithSHA1 (DSS) */
    public stbtic finbl String ALGO_ID_SIGNATURE_DSA =
        Constbnts.SignbtureSpecNS + "dsb-shb1";

    /** Signbture - Optionbl DSAwithSHA256 */
    public stbtic finbl String ALGO_ID_SIGNATURE_DSA_SHA256 =
        Constbnts.SignbtureSpec11NS + "dsb-shb256";

    /** Signbture - Recommended RSAwithSHA1 */
    public stbtic finbl String ALGO_ID_SIGNATURE_RSA =
        Constbnts.SignbtureSpecNS + "rsb-shb1";

    /** Signbture - Recommended RSAwithSHA1 */
    public stbtic finbl String ALGO_ID_SIGNATURE_RSA_SHA1 =
        Constbnts.SignbtureSpecNS + "rsb-shb1";

    /** Signbture - NOT Recommended RSAwithMD5 */
    public stbtic finbl String ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5 =
        Constbnts.MoreAlgorithmsSpecNS + "rsb-md5";

    /** Signbture - Optionbl RSAwithRIPEMD160 */
    public stbtic finbl String ALGO_ID_SIGNATURE_RSA_RIPEMD160 =
        Constbnts.MoreAlgorithmsSpecNS + "rsb-ripemd160";

    /** Signbture - Optionbl RSAwithSHA256 */
    public stbtic finbl String ALGO_ID_SIGNATURE_RSA_SHA256 =
        Constbnts.MoreAlgorithmsSpecNS + "rsb-shb256";

    /** Signbture - Optionbl RSAwithSHA384 */
    public stbtic finbl String ALGO_ID_SIGNATURE_RSA_SHA384 =
        Constbnts.MoreAlgorithmsSpecNS + "rsb-shb384";

    /** Signbture - Optionbl RSAwithSHA512 */
    public stbtic finbl String ALGO_ID_SIGNATURE_RSA_SHA512 =
        Constbnts.MoreAlgorithmsSpecNS + "rsb-shb512";

    /** HMAC - NOT Recommended HMAC-MD5 */
    public stbtic finbl String ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5 =
        Constbnts.MoreAlgorithmsSpecNS + "hmbc-md5";

    /** HMAC - Optionbl HMAC-RIPEMD160 */
    public stbtic finbl String ALGO_ID_MAC_HMAC_RIPEMD160 =
        Constbnts.MoreAlgorithmsSpecNS + "hmbc-ripemd160";

    /** HMAC - Optionbl HMAC-SHA256 */
    public stbtic finbl String ALGO_ID_MAC_HMAC_SHA256 =
        Constbnts.MoreAlgorithmsSpecNS + "hmbc-shb256";

    /** HMAC - Optionbl HMAC-SHA284 */
    public stbtic finbl String ALGO_ID_MAC_HMAC_SHA384 =
        Constbnts.MoreAlgorithmsSpecNS + "hmbc-shb384";

    /** HMAC - Optionbl HMAC-SHA512 */
    public stbtic finbl String ALGO_ID_MAC_HMAC_SHA512 =
        Constbnts.MoreAlgorithmsSpecNS + "hmbc-shb512";

    /**Signbture - Optionbl ECDSAwithSHA1 */
    public stbtic finbl String ALGO_ID_SIGNATURE_ECDSA_SHA1 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb1";

    /**Signbture - Optionbl ECDSAwithSHA256 */
    public stbtic finbl String ALGO_ID_SIGNATURE_ECDSA_SHA256 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb256";

    /**Signbture - Optionbl ECDSAwithSHA384 */
    public stbtic finbl String ALGO_ID_SIGNATURE_ECDSA_SHA384 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb384";

    /**Signbture - Optionbl ECDSAwithSHA512 */
    public stbtic finbl String ALGO_ID_SIGNATURE_ECDSA_SHA512 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb512";

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(XMLSignbture.clbss.getNbme());

    /** ds:Signbture.ds:SignedInfo element */
    privbte SignedInfo signedInfo;

    /** ds:Signbture.ds:KeyInfo */
    privbte KeyInfo keyInfo;

    /**
     * Checking the digests in References in b Signbture bre mbndbtory, but for
     * References inside b Mbnifest it is bpplicbtion specific. This boolebn is
     * to indicbte thbt the References inside Mbnifests should be vblidbted.
     */
    privbte boolebn followMbnifestsDuringVblidbtion = fblse;

    privbte Element signbtureVblueElement;

    privbte stbtic finbl int MODE_SIGN = 0;
    privbte stbtic finbl int MODE_VERIFY = 1;
    privbte int stbte = MODE_SIGN;

    /**
     * This crebtes b new <CODE>ds:Signbture</CODE> Element bnd bdds bn empty
     * <CODE>ds:SignedInfo</CODE>.
     * The <code>ds:SignedInfo</code> is initiblized with the specified Signbture
     * blgorithm bnd Cbnonicblizer.ALGO_ID_C14N_OMIT_COMMENTS which is REQUIRED
     * by the spec. This method's mbin use is for crebting b new signbture.
     *
     * @pbrbm doc Document in which the signbture will be bppended bfter crebtion.
     * @pbrbm bbseURI URI to be used bs context for bll relbtive URIs.
     * @pbrbm signbtureMethodURI signbture blgorithm to use.
     * @throws XMLSecurityException
     */
    public XMLSignbture(Document doc, String bbseURI, String signbtureMethodURI)
        throws XMLSecurityException {
        this(doc, bbseURI, signbtureMethodURI, 0, Cbnonicblizer.ALGO_ID_C14N_OMIT_COMMENTS);
    }

    /**
     * Constructor XMLSignbture
     *
     * @pbrbm doc
     * @pbrbm bbseURI
     * @pbrbm signbtureMethodURI the Signbture method to be used.
     * @pbrbm hmbcOutputLength
     * @throws XMLSecurityException
     */
    public XMLSignbture(Document doc, String bbseURI, String signbtureMethodURI,
                        int hmbcOutputLength) throws XMLSecurityException {
        this(
            doc, bbseURI, signbtureMethodURI, hmbcOutputLength,
            Cbnonicblizer.ALGO_ID_C14N_OMIT_COMMENTS
        );
    }

    /**
     * Constructor XMLSignbture
     *
     * @pbrbm doc
     * @pbrbm bbseURI
     * @pbrbm signbtureMethodURI the Signbture method to be used.
     * @pbrbm cbnonicblizbtionMethodURI the cbnonicblizbtion blgorithm to be
     * used to c14nize the SignedInfo element.
     * @throws XMLSecurityException
     */
    public XMLSignbture(
        Document doc,
        String bbseURI,
        String signbtureMethodURI,
        String cbnonicblizbtionMethodURI
    ) throws XMLSecurityException {
        this(doc, bbseURI, signbtureMethodURI, 0, cbnonicblizbtionMethodURI);
    }

    /**
     * Constructor XMLSignbture
     *
     * @pbrbm doc
     * @pbrbm bbseURI
     * @pbrbm signbtureMethodURI
     * @pbrbm hmbcOutputLength
     * @pbrbm cbnonicblizbtionMethodURI
     * @throws XMLSecurityException
     */
    public XMLSignbture(
        Document doc,
        String bbseURI,
        String signbtureMethodURI,
        int hmbcOutputLength,
        String cbnonicblizbtionMethodURI
    ) throws XMLSecurityException {
        super(doc);

        String xmlnsDsPrefix = getDefbultPrefix(Constbnts.SignbtureSpecNS);
        if (xmlnsDsPrefix == null || xmlnsDsPrefix.length() == 0) {
            this.constructionElement.setAttributeNS(
                Constbnts.NbmespbceSpecNS, "xmlns", Constbnts.SignbtureSpecNS
            );
        } else {
            this.constructionElement.setAttributeNS(
                Constbnts.NbmespbceSpecNS, "xmlns:" + xmlnsDsPrefix, Constbnts.SignbtureSpecNS
            );
        }
        XMLUtils.bddReturnToElement(this.constructionElement);

        this.bbseURI = bbseURI;
        this.signedInfo =
            new SignedInfo(
                this.doc, signbtureMethodURI, hmbcOutputLength, cbnonicblizbtionMethodURI
            );

        this.constructionElement.bppendChild(this.signedInfo.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);

        // crebte bn empty SignbtureVblue; this is filled by setSignbtureVblueElement
        signbtureVblueElement =
            XMLUtils.crebteElementInSignbtureSpbce(this.doc, Constbnts._TAG_SIGNATUREVALUE);

        this.constructionElement.bppendChild(signbtureVblueElement);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     *  Crebtes b XMLSignbture in b Document
     * @pbrbm doc
     * @pbrbm bbseURI
     * @pbrbm SignbtureMethodElem
     * @pbrbm CbnonicblizbtionMethodElem
     * @throws XMLSecurityException
     */
    public XMLSignbture(
        Document doc,
        String bbseURI,
        Element SignbtureMethodElem,
        Element CbnonicblizbtionMethodElem
    ) throws XMLSecurityException {
        super(doc);

        String xmlnsDsPrefix = getDefbultPrefix(Constbnts.SignbtureSpecNS);
        if (xmlnsDsPrefix == null || xmlnsDsPrefix.length() == 0) {
            this.constructionElement.setAttributeNS(
                Constbnts.NbmespbceSpecNS, "xmlns", Constbnts.SignbtureSpecNS
            );
        } else {
            this.constructionElement.setAttributeNS(
                Constbnts.NbmespbceSpecNS, "xmlns:" + xmlnsDsPrefix, Constbnts.SignbtureSpecNS
            );
        }
        XMLUtils.bddReturnToElement(this.constructionElement);

        this.bbseURI = bbseURI;
        this.signedInfo =
            new SignedInfo(this.doc, SignbtureMethodElem, CbnonicblizbtionMethodElem);

        this.constructionElement.bppendChild(this.signedInfo.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);

        // crebte bn empty SignbtureVblue; this is filled by setSignbtureVblueElement
        signbtureVblueElement =
            XMLUtils.crebteElementInSignbtureSpbce(this.doc, Constbnts._TAG_SIGNATUREVALUE);

        this.constructionElement.bppendChild(signbtureVblueElement);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * This will pbrse the element bnd construct the Jbvb Objects.
     * Thbt will bllow b user to vblidbte the signbture.
     *
     * @pbrbm element ds:Signbture element thbt contbins the whole signbture
     * @pbrbm bbseURI URI to be prepended to bll relbtive URIs
     * @throws XMLSecurityException
     * @throws XMLSignbtureException if the signbture is bbdly formbtted
     */
    public XMLSignbture(Element element, String bbseURI)
        throws XMLSignbtureException, XMLSecurityException {
        this(element, bbseURI, fblse);
    }

    /**
     * This will pbrse the element bnd construct the Jbvb Objects.
     * Thbt will bllow b user to vblidbte the signbture.
     *
     * @pbrbm element ds:Signbture element thbt contbins the whole signbture
     * @pbrbm bbseURI URI to be prepended to bll relbtive URIs
     * @pbrbm secureVblidbtion whether secure secureVblidbtion is enbbled or not
     * @throws XMLSecurityException
     * @throws XMLSignbtureException if the signbture is bbdly formbtted
     */
    public XMLSignbture(Element element, String bbseURI, boolebn secureVblidbtion)
        throws XMLSignbtureException, XMLSecurityException {
        super(element, bbseURI);

        // check out SignedInfo child
        Element signedInfoElem = XMLUtils.getNextElement(element.getFirstChild());

        // check to see if it is there
        if (signedInfoElem == null) {
            Object exArgs[] = { Constbnts._TAG_SIGNEDINFO, Constbnts._TAG_SIGNATURE };
            throw new XMLSignbtureException("xml.WrongContent", exArgs);
        }

        // crebte b SignedInfo object from thbt element
        this.signedInfo = new SignedInfo(signedInfoElem, bbseURI, secureVblidbtion);
        // get signedInfoElem bgbin in cbse it hbs chbnged
        signedInfoElem = XMLUtils.getNextElement(element.getFirstChild());

        // check out SignbtureVblue child
        this.signbtureVblueElement =
            XMLUtils.getNextElement(signedInfoElem.getNextSibling());

        // check to see if it exists
        if (signbtureVblueElement == null) {
            Object exArgs[] = { Constbnts._TAG_SIGNATUREVALUE, Constbnts._TAG_SIGNATURE };
            throw new XMLSignbtureException("xml.WrongContent", exArgs);
        }
        Attr signbtureVblueAttr = signbtureVblueElement.getAttributeNodeNS(null, "Id");
        if (signbtureVblueAttr != null) {
            signbtureVblueElement.setIdAttributeNode(signbtureVblueAttr, true);
        }

        // <element ref="ds:KeyInfo" minOccurs="0"/>
        Element keyInfoElem =
            XMLUtils.getNextElement(signbtureVblueElement.getNextSibling());

        // If it exists use it, but it's not mbndbtory
        if (keyInfoElem != null
            && keyInfoElem.getNbmespbceURI().equbls(Constbnts.SignbtureSpecNS)
            && keyInfoElem.getLocblNbme().equbls(Constbnts._TAG_KEYINFO)) {
            this.keyInfo = new KeyInfo(keyInfoElem, bbseURI);
            this.keyInfo.setSecureVblidbtion(secureVblidbtion);
        }

        // <element ref="ds:Object" minOccurs="0" mbxOccurs="unbounded"/>
        Element objectElem =
            XMLUtils.getNextElement(signbtureVblueElement.getNextSibling());
        while (objectElem != null) {
            Attr objectAttr = objectElem.getAttributeNodeNS(null, "Id");
            if (objectAttr != null) {
                objectElem.setIdAttributeNode(objectAttr, true);
            }

            NodeList nodes = objectElem.getChildNodes();
            int length = nodes.getLength();
            // Register Ids of the Object child elements
            for (int i = 0; i < length; i++) {
                Node child = nodes.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElem = (Element)child;
                    String tbg = childElem.getLocblNbme();
                    if (tbg.equbls("Mbnifest")) {
                        new Mbnifest(childElem, bbseURI);
                    } else if (tbg.equbls("SignbtureProperties")) {
                        new SignbtureProperties(childElem, bbseURI);
                    }
                }
            }

            objectElem = XMLUtils.getNextElement(objectElem.getNextSibling());
        }

        this.stbte = MODE_VERIFY;
    }

    /**
     * Sets the <code>Id</code> bttribute
     *
     * @pbrbm id Id vblue for the id bttribute on the Signbture Element
     */
    public void setId(String id) {
        if (id != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ID, id);
            this.constructionElement.setIdAttributeNS(null, Constbnts._ATT_ID, true);
        }
    }

    /**
     * Returns the <code>Id</code> bttribute
     *
     * @return the <code>Id</code> bttribute
     */
    public String getId() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ID);
    }

    /**
     * Returns the completely pbrsed <code>SignedInfo</code> object.
     *
     * @return the completely pbrsed <code>SignedInfo</code> object.
     */
    public SignedInfo getSignedInfo() {
        return this.signedInfo;
    }

    /**
     * Returns the octet vblue of the SignbtureVblue element.
     * Throws bn XMLSignbtureException if it hbs no or wrong content.
     *
     * @return the vblue of the SignbtureVblue element.
     * @throws XMLSignbtureException If there is no content
     */
    public byte[] getSignbtureVblue() throws XMLSignbtureException {
        try {
            return Bbse64.decode(signbtureVblueElement);
        } cbtch (Bbse64DecodingException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Bbse64 encodes bnd sets the bytes bs the content of the SignbtureVblue
     * Node.
     *
     * @pbrbm bytes bytes to be used by SignbtureVblue before Bbse64 encoding
     */
    privbte void setSignbtureVblueElement(byte[] bytes) {

        while (signbtureVblueElement.hbsChildNodes()) {
            signbtureVblueElement.removeChild(signbtureVblueElement.getFirstChild());
        }

        String bbse64codedVblue = Bbse64.encode(bytes);

        if (bbse64codedVblue.length() > 76 && !XMLUtils.ignoreLineBrebks()) {
            bbse64codedVblue = "\n" + bbse64codedVblue + "\n";
        }

        Text t = this.doc.crebteTextNode(bbse64codedVblue);
        signbtureVblueElement.bppendChild(t);
    }

    /**
     * Returns the KeyInfo child. If we bre in signing mode bnd the KeyInfo
     * does not exist yet, it is crebted on dembnd bnd bdded to the Signbture.
     * <br>
     * This bllows to bdd brbitrbry content to the KeyInfo during signing.
     *
     * @return the KeyInfo object
     */
    public KeyInfo getKeyInfo() {
        // check to see if we bre signing bnd if we hbve to crebte b keyinfo
        if (this.stbte == MODE_SIGN && this.keyInfo == null) {

            // crebte the KeyInfo
            this.keyInfo = new KeyInfo(this.doc);

            // get the Element from KeyInfo
            Element keyInfoElement = this.keyInfo.getElement();
            Element firstObject =
                XMLUtils.selectDsNode(
                    this.constructionElement.getFirstChild(), Constbnts._TAG_OBJECT, 0
                );

            if (firstObject != null) {
                // bdd it before the object
                this.constructionElement.insertBefore(keyInfoElement, firstObject);
                XMLUtils.bddReturnBeforeChild(this.constructionElement, firstObject);
            } else {
                // bdd it bs the lbst element to the signbture
                this.constructionElement.bppendChild(keyInfoElement);
                XMLUtils.bddReturnToElement(this.constructionElement);
            }
        }

        return this.keyInfo;
    }

    /**
     * Appends bn Object (not b <code>jbvb.lbng.Object</code> but bn Object
     * element) to the Signbture. Plebse note thbt this is only possible
     * when signing.
     *
     * @pbrbm object ds:Object to be bppended.
     * @throws XMLSignbtureException When this object is used to verify.
     */
    public void bppendObject(ObjectContbiner object) throws XMLSignbtureException {
        //try {
        //if (this.stbte != MODE_SIGN) {
        // throw new XMLSignbtureException(
        //  "signbture.operbtionOnlyBeforeSign");
        //}

        this.constructionElement.bppendChild(object.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
        //} cbtch (XMLSecurityException ex) {
        // throw new XMLSignbtureException("empty", ex);
        //}
    }

    /**
     * Returns the <code>i<code>th <code>ds:Object</code> child of the signbture
     * or null if no such <code>ds:Object</code> element exists.
     *
     * @pbrbm i
     * @return the <code>i<code>th <code>ds:Object</code> child of the signbture
     * or null if no such <code>ds:Object</code> element exists.
     */
    public ObjectContbiner getObjectItem(int i) {
        Element objElem =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_OBJECT, i
            );

        try {
            return new ObjectContbiner(objElem, this.bbseURI);
        } cbtch (XMLSecurityException ex) {
            return null;
        }
    }

    /**
     * Returns the number of bll <code>ds:Object</code> elements.
     *
     * @return the number of bll <code>ds:Object</code> elements.
     */
    public int getObjectLength() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_OBJECT);
    }

    /**
     * Digests bll References in the SignedInfo, cblculbtes the signbture vblue
     * bnd sets it in the SignbtureVblue Element.
     *
     * @pbrbm signingKey the {@link jbvb.security.PrivbteKey} or
     * {@link jbvbx.crypto.SecretKey} thbt is used to sign.
     * @throws XMLSignbtureException
     */
    public void sign(Key signingKey) throws XMLSignbtureException {

        if (signingKey instbnceof PublicKey) {
            throw new IllegblArgumentException(
                I18n.trbnslbte("blgorithms.operbtionOnlyVerificbtion")
            );
        }

        try {
            //Crebte b SignbtureAlgorithm object
            SignedInfo si = this.getSignedInfo();
            SignbtureAlgorithm sb = si.getSignbtureAlgorithm();
            OutputStrebm so = null;
            try {
                // initiblize SignbtureAlgorithm for signing
                sb.initSign(signingKey);

                // generbte digest vblues for bll References in this SignedInfo
                si.generbteDigestVblues();
                so = new UnsyncBufferedOutputStrebm(new SignerOutputStrebm(sb));
                // get the cbnonicblized bytes from SignedInfo
                si.signInOctetStrebm(so);
            } cbtch (XMLSecurityException ex) {
                throw ex;
            } finblly {
                if (so != null) {
                    try {
                        so.close();
                    } cbtch (IOException ex) {
                        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                            log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
                        }
                    }
                }
            }

            // set them on the SignbtureVblue element
            this.setSignbtureVblueElement(sb.sign());
        } cbtch (XMLSignbtureException ex) {
            throw ex;
        } cbtch (CbnonicblizbtionException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (InvblidCbnonicblizerException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (XMLSecurityException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Adds b {@link ResourceResolver} to enbble the retrievbl of resources.
     *
     * @pbrbm resolver
     */
    public void bddResourceResolver(ResourceResolver resolver) {
        this.getSignedInfo().bddResourceResolver(resolver);
    }

    /**
     * Adds b {@link ResourceResolverSpi} to enbble the retrievbl of resources.
     *
     * @pbrbm resolver
     */
    public void bddResourceResolver(ResourceResolverSpi resolver) {
        this.getSignedInfo().bddResourceResolver(resolver);
    }

    /**
     * Extrbcts the public key from the certificbte bnd verifies if the signbture
     * is vblid by re-digesting bll References, compbring those bgbinst the
     * stored DigestVblues bnd then checking to see if the Signbtures mbtch on
     * the SignedInfo.
     *
     * @pbrbm cert Certificbte thbt contbins the public key pbrt of the keypbir
     * thbt wbs used to sign.
     * @return true if the signbture is vblid, fblse otherwise
     * @throws XMLSignbtureException
     */
    public boolebn checkSignbtureVblue(X509Certificbte cert)
        throws XMLSignbtureException {
        // see if cert is null
        if (cert != null) {
            // check the vblues with the public key from the cert
            return this.checkSignbtureVblue(cert.getPublicKey());
        }

        Object exArgs[] = { "Didn't get b certificbte" };
        throw new XMLSignbtureException("empty", exArgs);
    }

    /**
     * Verifies if the signbture is vblid by redigesting bll References,
     * compbring those bgbinst the stored DigestVblues bnd then checking to see
     * if the Signbtures mbtch on the SignedInfo.
     *
     * @pbrbm pk {@link jbvb.security.PublicKey} pbrt of the keypbir or
     * {@link jbvbx.crypto.SecretKey} thbt wbs used to sign
     * @return true if the signbture is vblid, fblse otherwise
     * @throws XMLSignbtureException
     */
    public boolebn checkSignbtureVblue(Key pk) throws XMLSignbtureException {
        //COMMENT: pk suggests it cbn only be b public key?
        //check to see if the key is not null
        if (pk == null) {
            Object exArgs[] = { "Didn't get b key" };
            throw new XMLSignbtureException("empty", exArgs);
        }
        // bll references inside the signedinfo need to be dereferenced bnd
        // digested bgbin to see if the outcome mbtches the stored vblue in the
        // SignedInfo.
        // If followMbnifestsDuringVblidbtion is true it will do the sbme for
        // References inside b Mbnifest.
        try {
            SignedInfo si = this.getSignedInfo();
            //crebte b SignbtureAlgorithms from the SignbtureMethod inside
            //SignedInfo. This is used to vblidbte the signbture.
            SignbtureAlgorithm sb = si.getSignbtureAlgorithm();
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "signbtureMethodURI = " + sb.getAlgorithmURI());
                log.log(jbvb.util.logging.Level.FINE, "jceSigAlgorithm    = " + sb.getJCEAlgorithmString());
                log.log(jbvb.util.logging.Level.FINE, "jceSigProvider     = " + sb.getJCEProviderNbme());
                log.log(jbvb.util.logging.Level.FINE, "PublicKey = " + pk);
            }
            byte sigBytes[] = null;
            try {
                sb.initVerify(pk);

                // Get the cbnonicblized (normblized) SignedInfo
                SignerOutputStrebm so = new SignerOutputStrebm(sb);
                OutputStrebm bos = new UnsyncBufferedOutputStrebm(so);

                si.signInOctetStrebm(bos);
                bos.close();
                // retrieve the byte[] from the stored signbture
                sigBytes = this.getSignbtureVblue();
            } cbtch (IOException ex) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
                }
                // Impossible...
            } cbtch (XMLSecurityException ex) {
                throw ex;
            }

            // hbve SignbtureAlgorithm sign the input bytes bnd compbre them to
            // the bytes thbt were stored in the signbture.
            if (!sb.verify(sigBytes)) {
                log.log(jbvb.util.logging.Level.WARNING, "Signbture verificbtion fbiled.");
                return fblse;
            }

            return si.verify(this.followMbnifestsDuringVblidbtion);
        } cbtch (XMLSignbtureException ex) {
            throw ex;
        } cbtch (XMLSecurityException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Add b Reference with full pbrbmeters to this Signbture
     *
     * @pbrbm referenceURI URI of the resource to be signed. Cbn be null in
     * which cbse the dereferencing is bpplicbtion specific. Cbn be "" in which
     * it's the pbrent node (or pbrent document?). There cbn only be one "" in
     * ebch signbture.
     * @pbrbm trbns Optionbl list of trbnsformbtions to be done before digesting
     * @pbrbm digestURI Mbndbtory URI of the digesting blgorithm to use.
     * @pbrbm referenceId Optionbl id bttribute for this Reference
     * @pbrbm referenceType Optionbl mimetype for the URI
     * @throws XMLSignbtureException
     */
    public void bddDocument(
        String referenceURI,
        Trbnsforms trbns,
        String digestURI,
        String referenceId,
        String referenceType
    ) throws XMLSignbtureException {
        this.signedInfo.bddDocument(
            this.bbseURI, referenceURI, trbns, digestURI, referenceId, referenceType
        );
    }

    /**
     * This method is b proxy method for the {@link Mbnifest#bddDocument} method.
     *
     * @pbrbm referenceURI URI bccording to the XML Signbture specificbtion.
     * @pbrbm trbns List of trbnsformbtions to be bpplied.
     * @pbrbm digestURI URI of the digest blgorithm to be used.
     * @see Mbnifest#bddDocument
     * @throws XMLSignbtureException
     */
    public void bddDocument(
        String referenceURI,
        Trbnsforms trbns,
        String digestURI
    ) throws XMLSignbtureException {
        this.signedInfo.bddDocument(this.bbseURI, referenceURI, trbns, digestURI, null, null);
    }

    /**
     * Adds b Reference with just the URI bnd the trbnsforms. This used the
     * SHA1 blgorithm bs b defbult digest blgorithm.
     *
     * @pbrbm referenceURI URI bccording to the XML Signbture specificbtion.
     * @pbrbm trbns List of trbnsformbtions to be bpplied.
     * @throws XMLSignbtureException
     */
    public void bddDocument(String referenceURI, Trbnsforms trbns)
        throws XMLSignbtureException {
        this.signedInfo.bddDocument(
            this.bbseURI, referenceURI, trbns, Constbnts.ALGO_ID_DIGEST_SHA1, null, null
        );
    }

    /**
     * Add b Reference with just this URI. It uses SHA1 by defbult bs the digest
     * blgorithm
     *
     * @pbrbm referenceURI URI bccording to the XML Signbture specificbtion.
     * @throws XMLSignbtureException
     */
    public void bddDocument(String referenceURI) throws XMLSignbtureException {
        this.signedInfo.bddDocument(
            this.bbseURI, referenceURI, null, Constbnts.ALGO_ID_DIGEST_SHA1, null, null
        );
    }

    /**
     * Add bn X509 Certificbte to the KeyInfo. This will include the whole cert
     * inside X509Dbtb/X509Certificbte tbgs.
     *
     * @pbrbm cert Certificbte to be included. This should be the certificbte of
     * the key thbt wbs used to sign.
     * @throws XMLSecurityException
     */
    public void bddKeyInfo(X509Certificbte cert) throws XMLSecurityException {
        X509Dbtb x509dbtb = new X509Dbtb(this.doc);

        x509dbtb.bddCertificbte(cert);
        this.getKeyInfo().bdd(x509dbtb);
    }

    /**
     * Add this public key to the KeyInfo. This will include the complete key in
     * the KeyInfo structure.
     *
     * @pbrbm pk
     */
    public void bddKeyInfo(PublicKey pk) {
        this.getKeyInfo().bdd(pk);
    }

    /**
     * Proxy method for {@link SignedInfo#crebteSecretKey(byte[])}. If you wbnt
     * to crebte b MAC, this method helps you to obtbin the
     * {@link jbvbx.crypto.SecretKey} from octets.
     *
     * @pbrbm secretKeyBytes
     * @return the secret key crebted.
     * @see SignedInfo#crebteSecretKey(byte[])
     */
    public SecretKey crebteSecretKey(byte[] secretKeyBytes) {
        return this.getSignedInfo().crebteSecretKey(secretKeyBytes);
    }

    /**
     * Signbl whether Mbnifest should be butombticblly vblidbted.
     * Checking the digests in References in b Signbture bre mbndbtory, but for
     * References inside b Mbnifest it is bpplicbtion specific. This boolebn is
     * to indicbte thbt the References inside Mbnifests should be vblidbted.
     *
     * @pbrbm followMbnifests
     * @see <b href="http://www.w3.org/TR/xmldsig-core/#sec-CoreVblidbtion">
     * Core vblidbtion section in the XML Signbture Rec.</b>
     */
    public void setFollowNestedMbnifests(boolebn followMbnifests) {
        this.followMbnifestsDuringVblidbtion = followMbnifests;
    }

    /**
     * Get the locbl nbme of this element
     *
     * @return Constbnts._TAG_SIGNATURE
     */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_SIGNATURE;
    }
}
