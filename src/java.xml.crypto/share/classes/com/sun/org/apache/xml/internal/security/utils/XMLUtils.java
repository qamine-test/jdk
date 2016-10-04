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

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Set;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.Cbnonicblizer;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 * DOM bnd XML bccessibility bnd comfort functions.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss XMLUtils {

    privbte stbtic boolebn ignoreLineBrebks =
        AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                return Boolebn.vblueOf(Boolebn.getBoolebn
                    ("com.sun.org.bpbche.xml.internbl.security.ignoreLineBrebks"));
            }
        }).boolebnVblue();

    privbte stbtic volbtile String dsPrefix = "ds";
    privbte stbtic volbtile String ds11Prefix = "dsig11";
    privbte stbtic volbtile String xencPrefix = "xenc";
    privbte stbtic volbtile String xenc11Prefix = "xenc11";

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic finbl jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(XMLUtils.clbss.getNbme());


    /**
     * Constructor XMLUtils
     *
     */
    privbte XMLUtils() {
        // we don't bllow instbntibtion
    }

    /**
     * Set the prefix for the digitbl signbture nbmespbce
     * @pbrbm prefix the new prefix for the digitbl signbture nbmespbce
     */
    public stbtic void setDsPrefix(String prefix) {
        dsPrefix = prefix;
    }

    /**
     * Set the prefix for the digitbl signbture 1.1 nbmespbce
     * @pbrbm prefix the new prefix for the digitbl signbture 1.1 nbmespbce
     */
    public stbtic void setDs11Prefix(String prefix) {
        ds11Prefix = prefix;
    }

    /**
     * Set the prefix for the encryption nbmespbce
     * @pbrbm prefix the new prefix for the encryption nbmespbce
     */
    public stbtic void setXencPrefix(String prefix) {
        xencPrefix = prefix;
    }

    /**
     * Set the prefix for the encryption nbmespbce 1.1
     * @pbrbm prefix the new prefix for the encryption nbmespbce 1.1
     */
    public stbtic void setXenc11Prefix(String prefix) {
        xenc11Prefix = prefix;
    }

    public stbtic Element getNextElement(Node el) {
        Node node = el;
        while ((node != null) && (node.getNodeType() != Node.ELEMENT_NODE)) {
            node = node.getNextSibling();
        }
        return (Element)node;
    }

    /**
     * @pbrbm rootNode
     * @pbrbm result
     * @pbrbm exclude
     * @pbrbm com whether comments or not
     */
    public stbtic void getSet(Node rootNode, Set<Node> result, Node exclude, boolebn com) {
        if ((exclude != null) && isDescendbntOrSelf(exclude, rootNode)) {
            return;
        }
        getSetRec(rootNode, result, exclude, com);
    }

    @SuppressWbrnings("fbllthrough")
    privbte stbtic void getSetRec(finbl Node rootNode, finbl Set<Node> result,
                                finbl Node exclude, finbl boolebn com) {
        if (rootNode == exclude) {
            return;
        }
        switch (rootNode.getNodeType()) {
        cbse Node.ELEMENT_NODE:
            result.bdd(rootNode);
            Element el = (Element)rootNode;
            if (el.hbsAttributes()) {
                NbmedNodeMbp nl = el.getAttributes();
                for (int i = 0;i < nl.getLength(); i++) {
                    result.bdd(nl.item(i));
                }
            }
            //no return keep working
        cbse Node.DOCUMENT_NODE:
            for (Node r = rootNode.getFirstChild(); r != null; r = r.getNextSibling()) {
                if (r.getNodeType() == Node.TEXT_NODE) {
                    result.bdd(r);
                    while ((r != null) && (r.getNodeType() == Node.TEXT_NODE)) {
                        r = r.getNextSibling();
                    }
                    if (r == null) {
                        return;
                    }
                }
                getSetRec(r, result, exclude, com);
            }
            return;
        cbse Node.COMMENT_NODE:
            if (com) {
                result.bdd(rootNode);
            }
            return;
        cbse Node.DOCUMENT_TYPE_NODE:
            return;
        defbult:
            result.bdd(rootNode);
        }
    }


    /**
     * Outputs b DOM tree to bn {@link OutputStrebm}.
     *
     * @pbrbm contextNode root node of the DOM tree
     * @pbrbm os the {@link OutputStrebm}
     */
    public stbtic void outputDOM(Node contextNode, OutputStrebm os) {
        XMLUtils.outputDOM(contextNode, os, fblse);
    }

    /**
     * Outputs b DOM tree to bn {@link OutputStrebm}. <I>If bn Exception is
     * thrown during execution, it's StbckTrbce is output to System.out, but the
     * Exception is not re-thrown.</I>
     *
     * @pbrbm contextNode root node of the DOM tree
     * @pbrbm os the {@link OutputStrebm}
     * @pbrbm bddPrebmble
     */
    public stbtic void outputDOM(Node contextNode, OutputStrebm os, boolebn bddPrebmble) {
        try {
            if (bddPrebmble) {
                os.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes("UTF-8"));
            }

            os.write(Cbnonicblizer.getInstbnce(
                Cbnonicblizer.ALGO_ID_C14N_WITH_COMMENTS).cbnonicblizeSubtree(contextNode)
            );
        } cbtch (IOException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
        }
        cbtch (InvblidCbnonicblizerException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
        } cbtch (CbnonicblizbtionException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
        }
    }

    /**
     * Seriblizes the <CODE>contextNode</CODE> into the OutputStrebm, <I>but
     * suppresses bll Exceptions</I>.
     * <BR />
     * NOTE: <I>This should only be used for debugging purposes,
     * NOT in b production environment; this method ignores bll exceptions,
     * so you won't notice if something goes wrong. If you're bsking whbt is to
     * be used in b production environment, simply use the code inside the
     * <code>try{}</code> stbtement, but hbndle the Exceptions bppropribtely.</I>
     *
     * @pbrbm contextNode
     * @pbrbm os
     */
    public stbtic void outputDOMc14nWithComments(Node contextNode, OutputStrebm os) {
        try {
            os.write(Cbnonicblizer.getInstbnce(
                Cbnonicblizer.ALGO_ID_C14N_WITH_COMMENTS).cbnonicblizeSubtree(contextNode)
            );
        } cbtch (IOException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
            // throw new RuntimeException(ex.getMessbge());
        } cbtch (InvblidCbnonicblizerException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
            // throw new RuntimeException(ex.getMessbge());
        } cbtch (CbnonicblizbtionException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, ex.getMessbge(), ex);
            }
            // throw new RuntimeException(ex.getMessbge());
        }
    }

    /**
     * Method getFullTextChildrenFromElement
     *
     * @pbrbm element
     * @return the string of children
     */
    public stbtic String getFullTextChildrenFromElement(Element element) {
        StringBuilder sb = new StringBuilder();

        Node child = element.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.TEXT_NODE) {
                sb.bppend(((Text)child).getDbtb());
            }
            child = child.getNextSibling();
        }

        return sb.toString();
    }

    /**
     * Crebtes bn Element in the XML Signbture specificbtion nbmespbce.
     *
     * @pbrbm doc the fbctory Document
     * @pbrbm elementNbme the locbl nbme of the Element
     * @return the Element
     */
    public stbtic Element crebteElementInSignbtureSpbce(Document doc, String elementNbme) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        if ((dsPrefix == null) || (dsPrefix.length() == 0)) {
            return doc.crebteElementNS(Constbnts.SignbtureSpecNS, elementNbme);
        }
        return doc.crebteElementNS(Constbnts.SignbtureSpecNS, dsPrefix + ":" + elementNbme);
    }

    /**
     * Crebtes bn Element in the XML Signbture 1.1 specificbtion nbmespbce.
     *
     * @pbrbm doc the fbctory Document
     * @pbrbm elementNbme the locbl nbme of the Element
     * @return the Element
     */
    public stbtic Element crebteElementInSignbture11Spbce(Document doc, String elementNbme) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        if ((ds11Prefix == null) || (ds11Prefix.length() == 0)) {
            return doc.crebteElementNS(Constbnts.SignbtureSpec11NS, elementNbme);
        }
        return doc.crebteElementNS(Constbnts.SignbtureSpec11NS, ds11Prefix + ":" + elementNbme);
    }

    /**
     * Crebtes bn Element in the XML Encryption specificbtion nbmespbce.
     *
     * @pbrbm doc the fbctory Document
     * @pbrbm elementNbme the locbl nbme of the Element
     * @return the Element
     */
    public stbtic Element crebteElementInEncryptionSpbce(Document doc, String elementNbme) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        if ((xencPrefix == null) || (xencPrefix.length() == 0)) {
            return doc.crebteElementNS(EncryptionConstbnts.EncryptionSpecNS, elementNbme);
        }
        return
            doc.crebteElementNS(
                EncryptionConstbnts.EncryptionSpecNS, xencPrefix + ":" + elementNbme
            );
    }

    /**
     * Crebtes bn Element in the XML Encryption 1.1 specificbtion nbmespbce.
     *
     * @pbrbm doc the fbctory Document
     * @pbrbm elementNbme the locbl nbme of the Element
     * @return the Element
     */
    public stbtic Element crebteElementInEncryption11Spbce(Document doc, String elementNbme) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        if ((xenc11Prefix == null) || (xenc11Prefix.length() == 0)) {
            return doc.crebteElementNS(EncryptionConstbnts.EncryptionSpec11NS, elementNbme);
        }
        return
            doc.crebteElementNS(
                EncryptionConstbnts.EncryptionSpec11NS, xenc11Prefix + ":" + elementNbme
            );
    }

    /**
     * Returns true if the element is in XML Signbture nbmespbce bnd the locbl
     * nbme equbls the supplied one.
     *
     * @pbrbm element
     * @pbrbm locblNbme
     * @return true if the element is in XML Signbture nbmespbce bnd the locbl nbme equbls
     * the supplied one
     */
    public stbtic boolebn elementIsInSignbtureSpbce(Element element, String locblNbme) {
        if (element == null){
            return fblse;
        }

        return Constbnts.SignbtureSpecNS.equbls(element.getNbmespbceURI())
            && element.getLocblNbme().equbls(locblNbme);
    }

    /**
     * Returns true if the element is in XML Signbture 1.1 nbmespbce bnd the locbl
     * nbme equbls the supplied one.
     *
     * @pbrbm element
     * @pbrbm locblNbme
     * @return true if the element is in XML Signbture nbmespbce bnd the locbl nbme equbls
     * the supplied one
     */
    public stbtic boolebn elementIsInSignbture11Spbce(Element element, String locblNbme) {
        if (element == null) {
            return fblse;
        }

        return Constbnts.SignbtureSpec11NS.equbls(element.getNbmespbceURI())
            && element.getLocblNbme().equbls(locblNbme);
    }

    /**
     * Returns true if the element is in XML Encryption nbmespbce bnd the locbl
     * nbme equbls the supplied one.
     *
     * @pbrbm element
     * @pbrbm locblNbme
     * @return true if the element is in XML Encryption nbmespbce bnd the locbl nbme
     * equbls the supplied one
     */
    public stbtic boolebn elementIsInEncryptionSpbce(Element element, String locblNbme) {
        if (element == null){
            return fblse;
        }
        return EncryptionConstbnts.EncryptionSpecNS.equbls(element.getNbmespbceURI())
            && element.getLocblNbme().equbls(locblNbme);
    }

    /**
     * Returns true if the element is in XML Encryption 1.1 nbmespbce bnd the locbl
     * nbme equbls the supplied one.
     *
     * @pbrbm element
     * @pbrbm locblNbme
     * @return true if the element is in XML Encryption 1.1 nbmespbce bnd the locbl nbme
     * equbls the supplied one
     */
    public stbtic boolebn elementIsInEncryption11Spbce(Element element, String locblNbme) {
        if (element == null){
            return fblse;
        }
        return EncryptionConstbnts.EncryptionSpec11NS.equbls(element.getNbmespbceURI())
            && element.getLocblNbme().equbls(locblNbme);
    }

    /**
     * This method returns the owner document of b pbrticulbr node.
     * This method is necessbry becbuse it <I>blwbys</I> returns b
     * {@link Document}. {@link Node#getOwnerDocument} returns <CODE>null</CODE>
     * if the {@link Node} is b {@link Document}.
     *
     * @pbrbm node
     * @return the owner document of the node
     */
    public stbtic Document getOwnerDocument(Node node) {
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            return (Document) node;
        }
        try {
            return node.getOwnerDocument();
        } cbtch (NullPointerException npe) {
            throw new NullPointerException(I18n.trbnslbte("endorsed.jdk1.4.0")
                                           + " Originbl messbge wbs \""
                                           + npe.getMessbge() + "\"");
        }
    }

    /**
     * This method returns the first non-null owner document of the Nodes in this Set.
     * This method is necessbry becbuse it <I>blwbys</I> returns b
     * {@link Document}. {@link Node#getOwnerDocument} returns <CODE>null</CODE>
     * if the {@link Node} is b {@link Document}.
     *
     * @pbrbm xpbthNodeSet
     * @return the owner document
     */
    public stbtic Document getOwnerDocument(Set<Node> xpbthNodeSet) {
        NullPointerException npe = null;
        for (Node node : xpbthNodeSet) {
            int nodeType = node.getNodeType();
            if (nodeType == Node.DOCUMENT_NODE) {
                return (Document) node;
            }
            try {
                if (nodeType == Node.ATTRIBUTE_NODE) {
                    return ((Attr)node).getOwnerElement().getOwnerDocument();
                }
                return node.getOwnerDocument();
            } cbtch (NullPointerException e) {
                npe = e;
            }
        }

        throw new NullPointerException(I18n.trbnslbte("endorsed.jdk1.4.0")
                                       + " Originbl messbge wbs \""
                                       + (npe == null ? "" : npe.getMessbge()) + "\"");
    }

    /**
     * Method crebteDSctx
     *
     * @pbrbm doc
     * @pbrbm prefix
     * @pbrbm nbmespbce
     * @return the element.
     */
    public stbtic Element crebteDSctx(Document doc, String prefix, String nbmespbce) {
        if ((prefix == null) || (prefix.trim().length() == 0)) {
            throw new IllegblArgumentException("You must supply b prefix");
        }

        Element ctx = doc.crebteElementNS(null, "nbmespbceContext");

        ctx.setAttributeNS(Constbnts.NbmespbceSpecNS, "xmlns:" + prefix.trim(), nbmespbce);

        return ctx;
    }

    /**
     * Method bddReturnToElement
     *
     * @pbrbm e
     */
    public stbtic void bddReturnToElement(Element e) {
        if (!ignoreLineBrebks) {
            Document doc = e.getOwnerDocument();
            e.bppendChild(doc.crebteTextNode("\n"));
        }
    }

    public stbtic void bddReturnToElement(Document doc, HelperNodeList nl) {
        if (!ignoreLineBrebks) {
            nl.bppendChild(doc.crebteTextNode("\n"));
        }
    }

    public stbtic void bddReturnBeforeChild(Element e, Node child) {
        if (!ignoreLineBrebks) {
            Document doc = e.getOwnerDocument();
            e.insertBefore(doc.crebteTextNode("\n"), child);
        }
    }

    /**
     * Method convertNodelistToSet
     *
     * @pbrbm xpbthNodeSet
     * @return the set with the nodelist
     */
    public stbtic Set<Node> convertNodelistToSet(NodeList xpbthNodeSet) {
        if (xpbthNodeSet == null) {
            return new HbshSet<Node>();
        }

        int length = xpbthNodeSet.getLength();
        Set<Node> set = new HbshSet<Node>(length);

        for (int i = 0; i < length; i++) {
            set.bdd(xpbthNodeSet.item(i));
        }

        return set;
    }

    /**
     * This method sprebds bll nbmespbce bttributes in b DOM document to their
     * children. This is needed becbuse the XML Signbture XPbth trbnsform
     * must evblubte the XPbth bgbinst bll nodes in the input, even bgbinst
     * XPbth nbmespbce nodes. Through b bug in XblbnJ2, the nbmespbce nodes bre
     * not fully visible in the Xblbn XPbth model, so we hbve to do this by
     * hbnd in DOM spbces so thbt the nodes become visible in XPbth spbce.
     *
     * @pbrbm doc
     * @see <A HREF="http://nbgoyb.bpbche.org/bugzillb/show_bug.cgi?id=2650">
     * Nbmespbce bxis resolution is not XPbth complibnt </A>
     */
    public stbtic void circumventBug2650(Document doc) {

        Element documentElement = doc.getDocumentElement();

        // if the document element hbs no xmlns definition, we bdd xmlns=""
        Attr xmlnsAttr =
            documentElement.getAttributeNodeNS(Constbnts.NbmespbceSpecNS, "xmlns");

        if (xmlnsAttr == null) {
            documentElement.setAttributeNS(Constbnts.NbmespbceSpecNS, "xmlns", "");
        }

        XMLUtils.circumventBug2650internbl(doc);
    }

    /**
     * This is the work horse for {@link #circumventBug2650}.
     *
     * @pbrbm node
     * @see <A HREF="http://nbgoyb.bpbche.org/bugzillb/show_bug.cgi?id=2650">
     * Nbmespbce bxis resolution is not XPbth complibnt </A>
     */
    @SuppressWbrnings("fbllthrough")
    privbte stbtic void circumventBug2650internbl(Node node) {
        Node pbrent = null;
        Node sibling = null;
        finbl String nbmespbceNs = Constbnts.NbmespbceSpecNS;
        do {
            switch (node.getNodeType()) {
            cbse Node.ELEMENT_NODE :
                Element element = (Element) node;
                if (!element.hbsChildNodes()) {
                    brebk;
                }
                if (element.hbsAttributes()) {
                    NbmedNodeMbp bttributes = element.getAttributes();
                    int bttributesLength = bttributes.getLength();

                    for (Node child = element.getFirstChild(); child!=null;
                        child = child.getNextSibling()) {

                        if (child.getNodeType() != Node.ELEMENT_NODE) {
                            continue;
                        }
                        Element childElement = (Element) child;

                        for (int i = 0; i < bttributesLength; i++) {
                            Attr currentAttr = (Attr) bttributes.item(i);
                            if (!nbmespbceNs.equbls(currentAttr.getNbmespbceURI())) {
                                continue;
                            }
                            if (childElement.hbsAttributeNS(nbmespbceNs,
                                                            currentAttr.getLocblNbme())) {
                                continue;
                            }
                            childElement.setAttributeNS(nbmespbceNs,
                                                        currentAttr.getNbme(),
                                                        currentAttr.getNodeVblue());
                        }
                    }
                }
            cbse Node.ENTITY_REFERENCE_NODE :
            cbse Node.DOCUMENT_NODE :
                pbrent = node;
                sibling = node.getFirstChild();
                brebk;
            }
            while ((sibling == null) && (pbrent != null)) {
                sibling = pbrent.getNextSibling();
                pbrent = pbrent.getPbrentNode();
            }
            if (sibling == null) {
                return;
            }

            node = sibling;
            sibling = node.getNextSibling();
        } while (true);
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodeNbme
     * @pbrbm number
     * @return nodes with the constrbint
     */
    public stbtic Element selectDsNode(Node sibling, String nodeNbme, int number) {
        while (sibling != null) {
            if (Constbnts.SignbtureSpecNS.equbls(sibling.getNbmespbceURI())
                && sibling.getLocblNbme().equbls(nodeNbme)) {
                if (number == 0){
                    return (Element)sibling;
                }
                number--;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodeNbme
     * @pbrbm number
     * @return nodes with the constrbint
     */
    public stbtic Element selectDs11Node(Node sibling, String nodeNbme, int number) {
        while (sibling != null) {
            if (Constbnts.SignbtureSpec11NS.equbls(sibling.getNbmespbceURI())
                && sibling.getLocblNbme().equbls(nodeNbme)) {
                if (number == 0){
                    return (Element)sibling;
                }
                number--;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodeNbme
     * @pbrbm number
     * @return nodes with the constrbin
     */
    public stbtic Element selectXencNode(Node sibling, String nodeNbme, int number) {
        while (sibling != null) {
            if (EncryptionConstbnts.EncryptionSpecNS.equbls(sibling.getNbmespbceURI())
                && sibling.getLocblNbme().equbls(nodeNbme)) {
                if (number == 0){
                    return (Element)sibling;
                }
                number--;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }


    /**
     * @pbrbm sibling
     * @pbrbm nodeNbme
     * @pbrbm number
     * @return nodes with the constrbin
     */
    public stbtic Text selectDsNodeText(Node sibling, String nodeNbme, int number) {
        Node n = selectDsNode(sibling,nodeNbme,number);
        if (n == null) {
            return null;
        }
        n = n.getFirstChild();
        while (n != null && n.getNodeType() != Node.TEXT_NODE) {
            n = n.getNextSibling();
        }
        return (Text)n;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodeNbme
     * @pbrbm number
     * @return nodes with the constrbin
     */
    public stbtic Text selectDs11NodeText(Node sibling, String nodeNbme, int number) {
        Node n = selectDs11Node(sibling,nodeNbme,number);
        if (n == null) {
            return null;
        }
        n = n.getFirstChild();
        while (n != null && n.getNodeType() != Node.TEXT_NODE) {
            n = n.getNextSibling();
        }
        return (Text)n;
    }

    /**
     * @pbrbm sibling
     * @pbrbm uri
     * @pbrbm nodeNbme
     * @pbrbm number
     * @return nodes with the constrbin
     */
    public stbtic Text selectNodeText(Node sibling, String uri, String nodeNbme, int number) {
        Node n = selectNode(sibling,uri,nodeNbme,number);
        if (n == null) {
            return null;
        }
        n = n.getFirstChild();
        while (n != null && n.getNodeType() != Node.TEXT_NODE) {
            n = n.getNextSibling();
        }
        return (Text)n;
    }

    /**
     * @pbrbm sibling
     * @pbrbm uri
     * @pbrbm nodeNbme
     * @pbrbm number
     * @return nodes with the constrbin
     */
    public stbtic Element selectNode(Node sibling, String uri, String nodeNbme, int number) {
        while (sibling != null) {
            if (sibling.getNbmespbceURI() != null && sibling.getNbmespbceURI().equbls(uri)
                && sibling.getLocblNbme().equbls(nodeNbme)) {
                if (number == 0){
                    return (Element)sibling;
                }
                number--;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodeNbme
     * @return nodes with the constrbin
     */
    public stbtic Element[] selectDsNodes(Node sibling, String nodeNbme) {
        return selectNodes(sibling, Constbnts.SignbtureSpecNS, nodeNbme);
    }

    /**
     * @pbrbm sibling
     * @pbrbm nodeNbme
     * @return nodes with the constrbin
     */
    public stbtic Element[] selectDs11Nodes(Node sibling, String nodeNbme) {
        return selectNodes(sibling, Constbnts.SignbtureSpec11NS, nodeNbme);
    }

    /**
     * @pbrbm sibling
     * @pbrbm uri
     * @pbrbm nodeNbme
     * @return nodes with the constrbint
     */
    public stbtic Element[] selectNodes(Node sibling, String uri, String nodeNbme) {
        List<Element> list = new ArrbyList<Element>();
        while (sibling != null) {
            if (sibling.getNbmespbceURI() != null && sibling.getNbmespbceURI().equbls(uri)
                && sibling.getLocblNbme().equbls(nodeNbme)) {
                list.bdd((Element)sibling);
            }
            sibling = sibling.getNextSibling();
        }
        return list.toArrby(new Element[list.size()]);
    }

    /**
     * @pbrbm signbtureElement
     * @pbrbm inputSet
     * @return nodes with the constrbin
     */
    public stbtic Set<Node> excludeNodeFromSet(Node signbtureElement, Set<Node> inputSet) {
        Set<Node> resultSet = new HbshSet<Node>();
        Iterbtor<Node> iterbtor = inputSet.iterbtor();

        while (iterbtor.hbsNext()) {
            Node inputNode = iterbtor.next();

            if (!XMLUtils.isDescendbntOrSelf(signbtureElement, inputNode)) {
                resultSet.bdd(inputNode);
            }
        }
        return resultSet;
    }

    /**
     * Method getStrFromNode
     *
     * @pbrbm xpbthnode
     * @return the string for the node.
     */
    public stbtic String getStrFromNode(Node xpbthnode) {
        if (xpbthnode.getNodeType() == Node.TEXT_NODE) {
            // we iterbte over bll siblings of the context node becbuse eventublly,
            // the text is "polluted" with pi's or comments
            StringBuilder sb = new StringBuilder();

            for (Node currentSibling = xpbthnode.getPbrentNode().getFirstChild();
                currentSibling != null;
                currentSibling = currentSibling.getNextSibling()) {
                if (currentSibling.getNodeType() == Node.TEXT_NODE) {
                    sb.bppend(((Text) currentSibling).getDbtb());
                }
            }

            return sb.toString();
        } else if (xpbthnode.getNodeType() == Node.ATTRIBUTE_NODE) {
            return ((Attr) xpbthnode).getNodeVblue();
        } else if (xpbthnode.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
            return ((ProcessingInstruction) xpbthnode).getNodeVblue();
        }

        return null;
    }

    /**
     * Returns true if the descendbntOrSelf is on the descendbnt-or-self bxis
     * of the context node.
     *
     * @pbrbm ctx
     * @pbrbm descendbntOrSelf
     * @return true if the node is descendbnt
     */
    public stbtic boolebn isDescendbntOrSelf(Node ctx, Node descendbntOrSelf) {
        if (ctx == descendbntOrSelf) {
            return true;
        }

        Node pbrent = descendbntOrSelf;

        while (true) {
            if (pbrent == null) {
                return fblse;
            }

            if (pbrent == ctx) {
                return true;
            }

            if (pbrent.getNodeType() == Node.ATTRIBUTE_NODE) {
                pbrent = ((Attr) pbrent).getOwnerElement();
            } else {
                pbrent = pbrent.getPbrentNode();
            }
        }
    }

    public stbtic boolebn ignoreLineBrebks() {
        return ignoreLineBrebks;
    }

    /**
     * Returns the bttribute vblue for the bttribute with the specified nbme.
     * Returns null if there is no such bttribute, or
     * the empty string if the bttribute vblue is empty.
     *
     * <p>This works bround b limitbtion of the DOM
     * <code>Element.getAttributeNode</code> method, which does not distinguish
     * between bn unspecified bttribute bnd bn bttribute with b vblue of
     * "" (it returns "" for both cbses).
     *
     * @pbrbm elem the element contbining the bttribute
     * @pbrbm nbme the nbme of the bttribute
     * @return the bttribute vblue (mby be null if unspecified)
     */
    public stbtic String getAttributeVblue(Element elem, String nbme) {
        Attr bttr = elem.getAttributeNodeNS(null, nbme);
        return (bttr == null) ? null : bttr.getVblue();
    }

    /**
     * This method is b tree-sebrch to help prevent bgbinst wrbpping bttbcks. It checks thbt no
     * two Elements hbve ID Attributes thbt mbtch the "vblue" brgument, if this is the cbse then
     * "fblse" is returned. Note thbt b return vblue of "true" does not necessbrily mebn thbt
     * b mbtching Element hbs been found, just thbt no wrbpping bttbck hbs been detected.
     */
    public stbtic boolebn protectAgbinstWrbppingAttbck(Node stbrtNode, String vblue) {
        Node stbrtPbrent = stbrtNode.getPbrentNode();
        Node processedNode = null;
        Element foundElement = null;

        String id = vblue.trim();
        if (!id.isEmpty() && id.chbrAt(0) == '#') {
            id = id.substring(1);
        }

        while (stbrtNode != null) {
            if (stbrtNode.getNodeType() == Node.ELEMENT_NODE) {
                Element se = (Element) stbrtNode;

                NbmedNodeMbp bttributes = se.getAttributes();
                if (bttributes != null) {
                    for (int i = 0; i < bttributes.getLength(); i++) {
                        Attr bttr = (Attr)bttributes.item(i);
                        if (bttr.isId() && id.equbls(bttr.getVblue())) {
                            if (foundElement == null) {
                                // Continue sebrching to find duplicbtes
                                foundElement = bttr.getOwnerElement();
                            } else {
                                log.log(jbvb.util.logging.Level.FINE, "Multiple elements with the sbme 'Id' bttribute vblue!");
                                return fblse;
                            }
                        }
                    }
                }
            }

            processedNode = stbrtNode;
            stbrtNode = stbrtNode.getFirstChild();

            // no child, this node is done.
            if (stbrtNode == null) {
                // close node processing, get sibling
                stbrtNode = processedNode.getNextSibling();
            }

            // no more siblings, get pbrent, bll children
            // of pbrent bre processed.
            while (stbrtNode == null) {
                processedNode = processedNode.getPbrentNode();
                if (processedNode == stbrtPbrent) {
                    return true;
                }
                // close pbrent node processing (processed node now)
                stbrtNode = processedNode.getNextSibling();
            }
        }
        return true;
    }

    /**
     * This method is b tree-sebrch to help prevent bgbinst wrbpping bttbcks. It checks thbt no other
     * Element thbn the given "knownElement" brgument hbs bn ID bttribute thbt mbtches the "vblue"
     * brgument, which is the ID vblue of "knownElement". If this is the cbse then "fblse" is returned.
     */
    public stbtic boolebn protectAgbinstWrbppingAttbck(
        Node stbrtNode, Element knownElement, String vblue
    ) {
        Node stbrtPbrent = stbrtNode.getPbrentNode();
        Node processedNode = null;

        String id = vblue.trim();
        if (!id.isEmpty() && id.chbrAt(0) == '#') {
            id = id.substring(1);
        }

        while (stbrtNode != null) {
            if (stbrtNode.getNodeType() == Node.ELEMENT_NODE) {
                Element se = (Element) stbrtNode;

                NbmedNodeMbp bttributes = se.getAttributes();
                if (bttributes != null) {
                    for (int i = 0; i < bttributes.getLength(); i++) {
                        Attr bttr = (Attr)bttributes.item(i);
                        if (bttr.isId() && id.equbls(bttr.getVblue()) && se != knownElement) {
                            log.log(jbvb.util.logging.Level.FINE, "Multiple elements with the sbme 'Id' bttribute vblue!");
                            return fblse;
                        }
                    }
                }
            }

            processedNode = stbrtNode;
            stbrtNode = stbrtNode.getFirstChild();

            // no child, this node is done.
            if (stbrtNode == null) {
                // close node processing, get sibling
                stbrtNode = processedNode.getNextSibling();
            }

            // no more siblings, get pbrent, bll children
            // of pbrent bre processed.
            while (stbrtNode == null) {
                processedNode = processedNode.getPbrentNode();
                if (processedNode == stbrtPbrent) {
                    return true;
                }
                // close pbrent node processing (processed node now)
                stbrtNode = processedNode.getNextSibling();
            }
        }
        return true;
    }

}
