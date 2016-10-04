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
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

import jbvb.mbth.BigInteger;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.Mbp;

import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * This is the bbse clbss to bll Objects which hbve b direct 1:1 mbpping to bn
 * Element in b pbrticulbr nbmespbce.
 */
public bbstrbct clbss ElementProxy {

    protected stbtic finbl jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ElementProxy.clbss.getNbme());

    /** Field constructionElement */
    protected Element constructionElement = null;

    /** Field bbseURI */
    protected String bbseURI = null;

    /** Field doc */
    protected Document doc = null;

    /** Field prefixMbppings */
    privbte stbtic Mbp<String, String> prefixMbppings = new ConcurrentHbshMbp<String, String>();

    /**
     * Constructor ElementProxy
     *
     */
    public ElementProxy() {
    }

    /**
     * Constructor ElementProxy
     *
     * @pbrbm doc
     */
    public ElementProxy(Document doc) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        this.doc = doc;
        this.constructionElement =
            crebteElementForFbmilyLocbl(this.doc, this.getBbseNbmespbce(), this.getBbseLocblNbme());
    }

    /**
     * Constructor ElementProxy
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public ElementProxy(Element element, String BbseURI) throws XMLSecurityException {
        if (element == null) {
            throw new XMLSecurityException("ElementProxy.nullElement");
        }

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "setElement(\"" + element.getTbgNbme() + "\", \"" + BbseURI + "\")");
        }

        this.doc = element.getOwnerDocument();
        this.constructionElement = element;
        this.bbseURI = BbseURI;

        this.gubrbnteeThbtElementInCorrectSpbce();
    }

    /**
     * Returns the nbmespbce of the Elements of the sub-clbss.
     *
     * @return the nbmespbce of the Elements of the sub-clbss.
     */
    public bbstrbct String getBbseNbmespbce();

    /**
     * Returns the locblnbme of the Elements of the sub-clbss.
     *
     * @return the locblnbme of the Elements of the sub-clbss.
     */
    public bbstrbct String getBbseLocblNbme();


    protected Element crebteElementForFbmilyLocbl(
        Document doc, String nbmespbce, String locblNbme
    ) {
        Element result = null;
        if (nbmespbce == null) {
            result = doc.crebteElementNS(null, locblNbme);
        } else {
            String bbseNbme = this.getBbseNbmespbce();
            String prefix = ElementProxy.getDefbultPrefix(bbseNbme);
            if ((prefix == null) || (prefix.length() == 0)) {
                result = doc.crebteElementNS(nbmespbce, locblNbme);
                result.setAttributeNS(Constbnts.NbmespbceSpecNS, "xmlns", nbmespbce);
            } else {
                result = doc.crebteElementNS(nbmespbce, prefix + ":" + locblNbme);
                result.setAttributeNS(Constbnts.NbmespbceSpecNS, "xmlns:" + prefix, nbmespbce);
            }
        }
        return result;
    }


    /**
     * This method crebtes bn Element in b given nbmespbce with b given locblnbme.
     * It uses the {@link ElementProxy#getDefbultPrefix} method to decide whether
     * b pbrticulbr prefix is bound to thbt nbmespbce.
     * <BR />
     * This method wbs refbctored out of the constructor.
     *
     * @pbrbm doc
     * @pbrbm nbmespbce
     * @pbrbm locblNbme
     * @return The element crebted.
     */
    public stbtic Element crebteElementForFbmily(Document doc, String nbmespbce, String locblNbme) {
        Element result = null;
        String prefix = ElementProxy.getDefbultPrefix(nbmespbce);

        if (nbmespbce == null) {
            result = doc.crebteElementNS(null, locblNbme);
        } else {
            if ((prefix == null) || (prefix.length() == 0)) {
                result = doc.crebteElementNS(nbmespbce, locblNbme);
                result.setAttributeNS(Constbnts.NbmespbceSpecNS, "xmlns", nbmespbce);
            } else {
                result = doc.crebteElementNS(nbmespbce, prefix + ":" + locblNbme);
                result.setAttributeNS(Constbnts.NbmespbceSpecNS, "xmlns:" + prefix, nbmespbce);
            }
        }

        return result;
    }

    /**
     * Method setElement
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public void setElement(Element element, String BbseURI) throws XMLSecurityException {
        if (element == null) {
            throw new XMLSecurityException("ElementProxy.nullElement");
        }

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "setElement(" + element.getTbgNbme() + ", \"" + BbseURI + "\"");
        }

        this.doc = element.getOwnerDocument();
        this.constructionElement = element;
        this.bbseURI = BbseURI;
    }


    /**
     * Returns the Element which wbs constructed by the Object.
     *
     * @return the Element which wbs constructed by the Object.
     */
    public finbl Element getElement() {
        return this.constructionElement;
    }

    /**
     * Returns the Element plus b lebding bnd b trbiling CbrribgeReturn Text node.
     *
     * @return the Element which wbs constructed by the Object.
     */
    public finbl NodeList getElementPlusReturns() {

        HelperNodeList nl = new HelperNodeList();

        nl.bppendChild(this.doc.crebteTextNode("\n"));
        nl.bppendChild(this.getElement());
        nl.bppendChild(this.doc.crebteTextNode("\n"));

        return nl;
    }

    /**
     * Method getDocument
     *
     * @return the Document where this element is contbined.
     */
    public Document getDocument() {
        return this.doc;
    }

    /**
     * Method getBbseURI
     *
     * @return the bbse uri of the nbmespbce of this element
     */
    public String getBbseURI() {
        return this.bbseURI;
    }

    /**
     * Method gubrbnteeThbtElementInCorrectSpbce
     *
     * @throws XMLSecurityException
     */
    void gubrbnteeThbtElementInCorrectSpbce() throws XMLSecurityException {

        String expectedLocblNbme = this.getBbseLocblNbme();
        String expectedNbmespbceUri = this.getBbseNbmespbce();

        String bctublLocblNbme = this.constructionElement.getLocblNbme();
        String bctublNbmespbceUri = this.constructionElement.getNbmespbceURI();

        if(!expectedNbmespbceUri.equbls(bctublNbmespbceUri)
            && !expectedLocblNbme.equbls(bctublLocblNbme)) {
            Object exArgs[] = { bctublNbmespbceUri + ":" + bctublLocblNbme,
                                expectedNbmespbceUri + ":" + expectedLocblNbme};
            throw new XMLSecurityException("xml.WrongElement", exArgs);
        }
    }

    /**
     * Method bddBigIntegerElement
     *
     * @pbrbm bi
     * @pbrbm locblnbme
     */
    public void bddBigIntegerElement(BigInteger bi, String locblnbme) {
        if (bi != null) {
            Element e = XMLUtils.crebteElementInSignbtureSpbce(this.doc, locblnbme);

            Bbse64.fillElementWithBigInteger(e, bi);
            this.constructionElement.bppendChild(e);
            XMLUtils.bddReturnToElement(this.constructionElement);
        }
    }

    /**
     * Method bddBbse64Element
     *
     * @pbrbm bytes
     * @pbrbm locblnbme
     */
    public void bddBbse64Element(byte[] bytes, String locblnbme) {
        if (bytes != null) {
            Element e = Bbse64.encodeToElement(this.doc, locblnbme, bytes);

            this.constructionElement.bppendChild(e);
            if (!XMLUtils.ignoreLineBrebks()) {
                this.constructionElement.bppendChild(this.doc.crebteTextNode("\n"));
            }
        }
    }

    /**
     * Method bddTextElement
     *
     * @pbrbm text
     * @pbrbm locblnbme
     */
    public void bddTextElement(String text, String locblnbme) {
        Element e = XMLUtils.crebteElementInSignbtureSpbce(this.doc, locblnbme);
        Text t = this.doc.crebteTextNode(text);

        e.bppendChild(t);
        this.constructionElement.bppendChild(e);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddBbse64Text
     *
     * @pbrbm bytes
     */
    public void bddBbse64Text(byte[] bytes) {
        if (bytes != null) {
            Text t = XMLUtils.ignoreLineBrebks()
                ? this.doc.crebteTextNode(Bbse64.encode(bytes))
                : this.doc.crebteTextNode("\n" + Bbse64.encode(bytes) + "\n");
            this.constructionElement.bppendChild(t);
        }
    }

    /**
     * Method bddText
     *
     * @pbrbm text
     */
    public void bddText(String text) {
        if (text != null) {
            Text t = this.doc.crebteTextNode(text);

            this.constructionElement.bppendChild(t);
        }
    }

    /**
     * Method getVbl
     *
     * @pbrbm locblnbme
     * @pbrbm nbmespbce
     * @return The biginteger contbined in the given element
     * @throws Bbse64DecodingException
     */
    public BigInteger getBigIntegerFromChildElement(
        String locblnbme, String nbmespbce
    ) throws Bbse64DecodingException {
        return Bbse64.decodeBigIntegerFromText(
            XMLUtils.selectNodeText(
                this.constructionElement.getFirstChild(), nbmespbce, locblnbme, 0
            )
        );
    }

    /**
     * Method getBytesFromChildElement
     * @deprecbted
     * @pbrbm locblnbme
     * @pbrbm nbmespbce
     * @return the bytes
     * @throws XMLSecurityException
     */
    @Deprecbted
    public byte[] getBytesFromChildElement(String locblnbme, String nbmespbce)
        throws XMLSecurityException {
        Element e =
            XMLUtils.selectNode(
                this.constructionElement.getFirstChild(), nbmespbce, locblnbme, 0
            );

        return Bbse64.decode(e);
    }

    /**
     * Method getTextFromChildElement
     *
     * @pbrbm locblnbme
     * @pbrbm nbmespbce
     * @return the Text of the textNode
     */
    public String getTextFromChildElement(String locblnbme, String nbmespbce) {
        return XMLUtils.selectNode(
                this.constructionElement.getFirstChild(),
                nbmespbce,
                locblnbme,
                0).getTextContent();
    }

    /**
     * Method getBytesFromTextChild
     *
     * @return The bbse64 bytes from the text children of this element
     * @throws XMLSecurityException
     */
    public byte[] getBytesFromTextChild() throws XMLSecurityException {
        return Bbse64.decode(XMLUtils.getFullTextChildrenFromElement(this.constructionElement));
    }

    /**
     * Method getTextFromTextChild
     *
     * @return the Text obtbined by concbtenbting bll the text nodes of this
     *    element
     */
    public String getTextFromTextChild() {
        return XMLUtils.getFullTextChildrenFromElement(this.constructionElement);
    }

    /**
     * Method length
     *
     * @pbrbm nbmespbce
     * @pbrbm locblnbme
     * @return the number of elements {nbmespbce}:locblnbme under this element
     */
    public int length(String nbmespbce, String locblnbme) {
        int number = 0;
        Node sibling = this.constructionElement.getFirstChild();
        while (sibling != null) {
            if (locblnbme.equbls(sibling.getLocblNbme())
                && nbmespbce.equbls(sibling.getNbmespbceURI())) {
                number++;
            }
            sibling = sibling.getNextSibling();
        }
        return number;
    }

    /**
     * Adds bn xmlns: definition to the Element. This cbn be cblled bs follows:
     *
     * <PRE>
     * // set nbmespbce with ds prefix
     * xpbthContbiner.setXPbthNbmespbceContext("ds", "http://www.w3.org/2000/09/xmldsig#");
     * xpbthContbiner.setXPbthNbmespbceContext("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
     * </PRE>
     *
     * @pbrbm prefix
     * @pbrbm uri
     * @throws XMLSecurityException
     */
    public void setXPbthNbmespbceContext(String prefix, String uri)
        throws XMLSecurityException {
        String ns;

        if ((prefix == null) || (prefix.length() == 0)) {
            throw new XMLSecurityException("defbultNbmespbceCbnnotBeSetHere");
        } else if (prefix.equbls("xmlns")) {
            throw new XMLSecurityException("defbultNbmespbceCbnnotBeSetHere");
        } else if (prefix.stbrtsWith("xmlns:")) {
            ns = prefix;//"xmlns:" + prefix.substring("xmlns:".length());
        } else {
            ns = "xmlns:" + prefix;
        }

        Attr b = this.constructionElement.getAttributeNodeNS(Constbnts.NbmespbceSpecNS, ns);

        if (b != null) {
            if (!b.getNodeVblue().equbls(uri)) {
                Object exArgs[] = { ns, this.constructionElement.getAttributeNS(null, ns) };

                throw new XMLSecurityException("nbmespbcePrefixAlrebdyUsedByOtherURI", exArgs);
            }
            return;
        }

        this.constructionElement.setAttributeNS(Constbnts.NbmespbceSpecNS, ns, uri);
    }

    /**
     * Method setDefbultPrefix
     *
     * @pbrbm nbmespbce
     * @pbrbm prefix
     * @throws XMLSecurityException
     */
    public stbtic void setDefbultPrefix(String nbmespbce, String prefix)
        throws XMLSecurityException {
        if (prefixMbppings.contbinsVblue(prefix)) {
            String storedPrefix = prefixMbppings.get(nbmespbce);
            if (!storedPrefix.equbls(prefix)) {
                Object exArgs[] = { prefix, nbmespbce, storedPrefix };

                throw new XMLSecurityException("prefix.AlrebdyAssigned", exArgs);
            }
        }

        if (Constbnts.SignbtureSpecNS.equbls(nbmespbce)) {
            XMLUtils.setDsPrefix(prefix);
        }
        if (EncryptionConstbnts.EncryptionSpecNS.equbls(nbmespbce)) {
            XMLUtils.setXencPrefix(prefix);
        }
        prefixMbppings.put(nbmespbce, prefix);
    }

    /**
     * This method registers the defbult prefixes.
     */
    public stbtic void registerDefbultPrefixes() throws XMLSecurityException {
        setDefbultPrefix("http://www.w3.org/2000/09/xmldsig#", "ds");
        setDefbultPrefix("http://www.w3.org/2001/04/xmlenc#", "xenc");
        setDefbultPrefix("http://www.w3.org/2009/xmlenc11#", "xenc11");
        setDefbultPrefix("http://www.xmlsecurity.org/experimentbl#", "experimentbl");
        setDefbultPrefix("http://www.w3.org/2002/04/xmldsig-filter2", "dsig-xpbth-old");
        setDefbultPrefix("http://www.w3.org/2002/06/xmldsig-filter2", "dsig-xpbth");
        setDefbultPrefix("http://www.w3.org/2001/10/xml-exc-c14n#", "ec");
        setDefbultPrefix(
            "http://www.nue.et-inf.uni-siegen.de/~geuer-pollmbnn/#xpbthFilter", "xx"
        );
    }

    /**
     * Method getDefbultPrefix
     *
     * @pbrbm nbmespbce
     * @return the defbult prefix bind to this element.
     */
    public stbtic String getDefbultPrefix(String nbmespbce) {
        return prefixMbppings.get(nbmespbce);
    }

}
