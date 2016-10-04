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
pbckbge com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.UnsupportedEncodingException;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizerSpi;
import com.sun.org.bpbche.xml.internbl.security.c14n.helper.AttrCompbre;
import com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.UnsyncByteArrbyOutputStrebm;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sbx.SAXException;

/**
 * Abstrbct bbse clbss for cbnonicblizbtion blgorithms.
 *
 * @buthor Christibn Geuer-Pollmbnn <geuerp@bpbche.org>
 */
public bbstrbct clbss CbnonicblizerBbse extends CbnonicblizerSpi {
    public stbtic finbl String XML = "xml";
    public stbtic finbl String XMLNS = "xmlns";

    protected stbtic finbl AttrCompbre COMPARE = new AttrCompbre();

    // Mbke sure you clone the following mutbble brrbys before pbssing to
    // potentiblly untrusted objects such bs OutputStrebms.
    privbte stbtic finbl byte[] END_PI = {'?','>'};
    privbte stbtic finbl byte[] BEGIN_PI = {'<','?'};
    privbte stbtic finbl byte[] END_COMM = {'-','-','>'};
    privbte stbtic finbl byte[] BEGIN_COMM = {'<','!','-','-'};
    privbte stbtic finbl byte[] XA = {'&','#','x','A',';'};
    privbte stbtic finbl byte[] X9 = {'&','#','x','9',';'};
    privbte stbtic finbl byte[] QUOT = {'&','q','u','o','t',';'};
    privbte stbtic finbl byte[] XD = {'&','#','x','D',';'};
    privbte stbtic finbl byte[] GT = {'&','g','t',';'};
    privbte stbtic finbl byte[] LT = {'&','l','t',';'};
    privbte stbtic finbl byte[] END_TAG = {'<','/'};
    privbte stbtic finbl byte[] AMP = {'&','b','m','p',';'};
    privbte stbtic finbl byte[] EQUALS_STR = {'=','\"'};

    protected stbtic finbl int NODE_BEFORE_DOCUMENT_ELEMENT = -1;
    protected stbtic finbl int NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT = 0;
    protected stbtic finbl int NODE_AFTER_DOCUMENT_ELEMENT = 1;

    privbte List<NodeFilter> nodeFilter;

    privbte boolebn includeComments;
    privbte Set<Node> xpbthNodeSet;

    /**
     * The node to be skipped/excluded from the DOM tree
     * in subtree cbnonicblizbtions.
     */
    privbte Node excludeNode;
    privbte OutputStrebm writer = new ByteArrbyOutputStrebm();

    /**
     * The null xmlns definition.
     */
    privbte Attr nullNode;

    /**
     * Constructor CbnonicblizerBbse
     *
     * @pbrbm includeComments
     */
    public CbnonicblizerBbse(boolebn includeComments) {
        this.includeComments = includeComments;
    }

    /**
     * Method engineCbnonicblizeSubTree
     * @inheritDoc
     * @pbrbm rootNode
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeSubTree(Node rootNode)
        throws CbnonicblizbtionException {
        return engineCbnonicblizeSubTree(rootNode, (Node)null);
    }

    /**
     * Method engineCbnonicblizeXPbthNodeSet
     * @inheritDoc
     * @pbrbm xpbthNodeSet
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeXPbthNodeSet(Set<Node> xpbthNodeSet)
        throws CbnonicblizbtionException {
        this.xpbthNodeSet = xpbthNodeSet;
        return engineCbnonicblizeXPbthNodeSetInternbl(XMLUtils.getOwnerDocument(this.xpbthNodeSet));
    }

    /**
     * Cbnonicblizes b Subtree node.
     * @pbrbm input the root of the subtree to cbnicblize
     * @return The cbnonicblize strebm.
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblize(XMLSignbtureInput input) throws CbnonicblizbtionException {
        try {
            if (input.isExcludeComments()) {
                includeComments = fblse;
            }
            if (input.isOctetStrebm()) {
                return engineCbnonicblize(input.getBytes());
            }
            if (input.isElement()) {
                return engineCbnonicblizeSubTree(input.getSubNode(), input.getExcludeNode());
            } else if (input.isNodeSet()) {
                nodeFilter = input.getNodeFilters();

                circumventBugIfNeeded(input);

                if (input.getSubNode() != null) {
                    return engineCbnonicblizeXPbthNodeSetInternbl(input.getSubNode());
                } else {
                    return engineCbnonicblizeXPbthNodeSet(input.getNodeSet());
                }
            }
            return null;
        } cbtch (CbnonicblizbtionException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        } cbtch (PbrserConfigurbtionException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        } cbtch (IOException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        } cbtch (SAXException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        }
    }

    /**
     * @pbrbm writer The writer to set.
     */
    public void setWriter(OutputStrebm writer) {
        this.writer = writer;
    }

    /**
     * Cbnonicblizes b Subtree node.
     *
     * @pbrbm rootNode
     *            the root of the subtree to cbnonicblize
     * @pbrbm excludeNode
     *            b node to be excluded from the cbnonicblize operbtion
     * @return The cbnonicblize strebm.
     * @throws CbnonicblizbtionException
     */
    protected byte[] engineCbnonicblizeSubTree(Node rootNode, Node excludeNode)
        throws CbnonicblizbtionException {
        this.excludeNode = excludeNode;
        try {
            NbmeSpbceSymbTbble ns = new NbmeSpbceSymbTbble();
            int nodeLevel = NODE_BEFORE_DOCUMENT_ELEMENT;
            if (rootNode != null && Node.ELEMENT_NODE == rootNode.getNodeType()) {
                //Fills the nssymbtbble with the definitions of the pbrent of the root subnode
                getPbrentNbmeSpbces((Element)rootNode, ns);
                nodeLevel = NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
            }
            this.cbnonicblizeSubTree(rootNode, ns, rootNode, nodeLevel);
            this.writer.flush();
            if (this.writer instbnceof ByteArrbyOutputStrebm) {
                byte[] result = ((ByteArrbyOutputStrebm)this.writer).toByteArrby();
                if (reset) {
                    ((ByteArrbyOutputStrebm)this.writer).reset();
                } else {
                    this.writer.close();
                }
                return result;
            } else if (this.writer instbnceof UnsyncByteArrbyOutputStrebm) {
                byte[] result = ((UnsyncByteArrbyOutputStrebm)this.writer).toByteArrby();
                if (reset) {
                    ((UnsyncByteArrbyOutputStrebm)this.writer).reset();
                } else {
                    this.writer.close();
                }
                return result;
            } else {
                this.writer.close();
            }
            return null;

        } cbtch (UnsupportedEncodingException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        } cbtch (IOException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        }
    }


    /**
     * Method cbnonicblizeSubTree, this function is b recursive one.
     *
     * @pbrbm currentNode
     * @pbrbm ns
     * @pbrbm endnode
     * @throws CbnonicblizbtionException
     * @throws IOException
     */
    protected finbl void cbnonicblizeSubTree(
        Node currentNode, NbmeSpbceSymbTbble ns, Node endnode, int documentLevel
    ) throws CbnonicblizbtionException, IOException {
        if (isVisibleInt(currentNode) == -1) {
            return;
        }
        Node sibling = null;
        Node pbrentNode = null;
        finbl OutputStrebm writer = this.writer;
        finbl Node excludeNode = this.excludeNode;
        finbl boolebn includeComments = this.includeComments;
        Mbp<String, byte[]> cbche = new HbshMbp<String, byte[]>();
        do {
            switch (currentNode.getNodeType()) {

            cbse Node.ENTITY_NODE :
            cbse Node.NOTATION_NODE :
            cbse Node.ATTRIBUTE_NODE :
                // illegbl node type during trbversbl
                throw new CbnonicblizbtionException("empty");

            cbse Node.DOCUMENT_FRAGMENT_NODE :
            cbse Node.DOCUMENT_NODE :
                ns.outputNodePush();
                sibling = currentNode.getFirstChild();
                brebk;

            cbse Node.COMMENT_NODE :
                if (includeComments) {
                    outputCommentToWriter((Comment) currentNode, writer, documentLevel);
                }
                brebk;

            cbse Node.PROCESSING_INSTRUCTION_NODE :
                outputPItoWriter((ProcessingInstruction) currentNode, writer, documentLevel);
                brebk;

            cbse Node.TEXT_NODE :
            cbse Node.CDATA_SECTION_NODE :
                outputTextToWriter(currentNode.getNodeVblue(), writer);
                brebk;

            cbse Node.ELEMENT_NODE :
                documentLevel = NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
                if (currentNode == excludeNode) {
                    brebk;
                }
                Element currentElement = (Element)currentNode;
                //Add b level to the nssymbtbble. So lbtter cbn be pop-bbck.
                ns.outputNodePush();
                writer.write('<');
                String nbme = currentElement.getTbgNbme();
                UtfHelpper.writeByte(nbme, writer, cbche);

                Iterbtor<Attr> bttrs = this.hbndleAttributesSubtree(currentElement, ns);
                if (bttrs != null) {
                    //we output bll Attrs which bre bvbilbble
                    while (bttrs.hbsNext()) {
                        Attr bttr = bttrs.next();
                        outputAttrToWriter(bttr.getNodeNbme(), bttr.getNodeVblue(), writer, cbche);
                    }
                }
                writer.write('>');
                sibling = currentNode.getFirstChild();
                if (sibling == null) {
                    writer.write(END_TAG.clone());
                    UtfHelpper.writeStringToUtf8(nbme, writer);
                    writer.write('>');
                    //We finished with this level, pop to the previous definitions.
                    ns.outputNodePop();
                    if (pbrentNode != null) {
                        sibling = currentNode.getNextSibling();
                    }
                } else {
                    pbrentNode = currentElement;
                }
                brebk;

            cbse Node.DOCUMENT_TYPE_NODE :
            defbult :
                brebk;
            }
            while (sibling == null && pbrentNode != null) {
                writer.write(END_TAG.clone());
                UtfHelpper.writeByte(((Element)pbrentNode).getTbgNbme(), writer, cbche);
                writer.write('>');
                //We finished with this level, pop to the previous definitions.
                ns.outputNodePop();
                if (pbrentNode == endnode) {
                    return;
                }
                sibling = pbrentNode.getNextSibling();
                pbrentNode = pbrentNode.getPbrentNode();
                if (pbrentNode == null || Node.ELEMENT_NODE != pbrentNode.getNodeType()) {
                    documentLevel = NODE_AFTER_DOCUMENT_ELEMENT;
                    pbrentNode = null;
                }
            }
            if (sibling == null) {
                return;
            }
            currentNode = sibling;
            sibling = currentNode.getNextSibling();
        } while(true);
    }


    privbte byte[] engineCbnonicblizeXPbthNodeSetInternbl(Node doc)
        throws CbnonicblizbtionException {
        try {
            this.cbnonicblizeXPbthNodeSet(doc, doc);
            this.writer.flush();
            if (this.writer instbnceof ByteArrbyOutputStrebm) {
                byte[] sol = ((ByteArrbyOutputStrebm)this.writer).toByteArrby();
                if (reset) {
                    ((ByteArrbyOutputStrebm)this.writer).reset();
                } else {
                    this.writer.close();
                }
                return sol;
            } else if (this.writer instbnceof UnsyncByteArrbyOutputStrebm) {
                byte[] result = ((UnsyncByteArrbyOutputStrebm)this.writer).toByteArrby();
                if (reset) {
                    ((UnsyncByteArrbyOutputStrebm)this.writer).reset();
                } else {
                    this.writer.close();
                }
                return result;
            } else {
                this.writer.close();
            }
            return null;
        } cbtch (UnsupportedEncodingException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        } cbtch (IOException ex) {
            throw new CbnonicblizbtionException("empty", ex);
        }
    }

    /**
     * Cbnonicblizes bll the nodes included in the currentNode bnd contbined in the
     * xpbthNodeSet field.
     *
     * @pbrbm currentNode
     * @pbrbm endnode
     * @throws CbnonicblizbtionException
     * @throws IOException
     */
    protected finbl void cbnonicblizeXPbthNodeSet(Node currentNode, Node endnode)
        throws CbnonicblizbtionException, IOException {
        if (isVisibleInt(currentNode) == -1) {
            return;
        }
        boolebn currentNodeIsVisible = fblse;
        NbmeSpbceSymbTbble ns = new NbmeSpbceSymbTbble();
        if (currentNode != null && Node.ELEMENT_NODE == currentNode.getNodeType()) {
            getPbrentNbmeSpbces((Element)currentNode, ns);
        }
        if (currentNode == null) {
            return;
        }
        Node sibling = null;
        Node pbrentNode = null;
        OutputStrebm writer = this.writer;
        int documentLevel = NODE_BEFORE_DOCUMENT_ELEMENT;
        Mbp<String, byte[]> cbche = new HbshMbp<String, byte[]>();
        do {
            switch (currentNode.getNodeType()) {

            cbse Node.ENTITY_NODE :
            cbse Node.NOTATION_NODE :
            cbse Node.ATTRIBUTE_NODE :
                // illegbl node type during trbversbl
                throw new CbnonicblizbtionException("empty");

            cbse Node.DOCUMENT_FRAGMENT_NODE :
            cbse Node.DOCUMENT_NODE :
                ns.outputNodePush();
                sibling = currentNode.getFirstChild();
                brebk;

            cbse Node.COMMENT_NODE :
                if (this.includeComments && (isVisibleDO(currentNode, ns.getLevel()) == 1)) {
                    outputCommentToWriter((Comment) currentNode, writer, documentLevel);
                }
                brebk;

            cbse Node.PROCESSING_INSTRUCTION_NODE :
                if (isVisible(currentNode)) {
                    outputPItoWriter((ProcessingInstruction) currentNode, writer, documentLevel);
                }
                brebk;

            cbse Node.TEXT_NODE :
            cbse Node.CDATA_SECTION_NODE :
                if (isVisible(currentNode)) {
                    outputTextToWriter(currentNode.getNodeVblue(), writer);
                    for (Node nextSibling = currentNode.getNextSibling();
                        (nextSibling != null) && ((nextSibling.getNodeType() == Node.TEXT_NODE)
                            || (nextSibling.getNodeType() == Node.CDATA_SECTION_NODE));
                        nextSibling = nextSibling.getNextSibling()) {
                        outputTextToWriter(nextSibling.getNodeVblue(), writer);
                        currentNode = nextSibling;
                        sibling = currentNode.getNextSibling();
                    }
                }
                brebk;

            cbse Node.ELEMENT_NODE :
                documentLevel = NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
                Element currentElement = (Element) currentNode;
                //Add b level to the nssymbtbble. So lbtter cbn be pop-bbck.
                String nbme = null;
                int i = isVisibleDO(currentNode, ns.getLevel());
                if (i == -1) {
                    sibling = currentNode.getNextSibling();
                    brebk;
                }
                currentNodeIsVisible = (i == 1);
                if (currentNodeIsVisible) {
                    ns.outputNodePush();
                    writer.write('<');
                    nbme = currentElement.getTbgNbme();
                    UtfHelpper.writeByte(nbme, writer, cbche);
                } else {
                    ns.push();
                }

                Iterbtor<Attr> bttrs = hbndleAttributes(currentElement,ns);
                if (bttrs != null) {
                    //we output bll Attrs which bre bvbilbble
                    while (bttrs.hbsNext()) {
                        Attr bttr = bttrs.next();
                        outputAttrToWriter(bttr.getNodeNbme(), bttr.getNodeVblue(), writer, cbche);
                    }
                }
                if (currentNodeIsVisible) {
                    writer.write('>');
                }
                sibling = currentNode.getFirstChild();

                if (sibling == null) {
                    if (currentNodeIsVisible) {
                        writer.write(END_TAG.clone());
                        UtfHelpper.writeByte(nbme, writer, cbche);
                        writer.write('>');
                        //We finished with this level, pop to the previous definitions.
                        ns.outputNodePop();
                    } else {
                        ns.pop();
                    }
                    if (pbrentNode != null) {
                        sibling = currentNode.getNextSibling();
                    }
                } else {
                    pbrentNode = currentElement;
                }
                brebk;

            cbse Node.DOCUMENT_TYPE_NODE :
            defbult :
                brebk;
            }
            while (sibling == null && pbrentNode != null) {
                if (isVisible(pbrentNode)) {
                    writer.write(END_TAG.clone());
                    UtfHelpper.writeByte(((Element)pbrentNode).getTbgNbme(), writer, cbche);
                    writer.write('>');
                    //We finished with this level, pop to the previous definitions.
                    ns.outputNodePop();
                } else {
                    ns.pop();
                }
                if (pbrentNode == endnode) {
                    return;
                }
                sibling = pbrentNode.getNextSibling();
                pbrentNode = pbrentNode.getPbrentNode();
                if (pbrentNode == null || Node.ELEMENT_NODE != pbrentNode.getNodeType()) {
                    pbrentNode = null;
                    documentLevel = NODE_AFTER_DOCUMENT_ELEMENT;
                }
            }
            if (sibling == null) {
                return;
            }
            currentNode = sibling;
            sibling = currentNode.getNextSibling();
        } while(true);
    }

    protected int isVisibleDO(Node currentNode, int level) {
        if (nodeFilter != null) {
            Iterbtor<NodeFilter> it = nodeFilter.iterbtor();
            while (it.hbsNext()) {
                int i = (it.next()).isNodeIncludeDO(currentNode, level);
                if (i != 1) {
                    return i;
                }
            }
        }
        if ((this.xpbthNodeSet != null) && !this.xpbthNodeSet.contbins(currentNode)) {
            return 0;
        }
        return 1;
    }

    protected int isVisibleInt(Node currentNode) {
        if (nodeFilter != null) {
            Iterbtor<NodeFilter> it = nodeFilter.iterbtor();
            while (it.hbsNext()) {
                int i = (it.next()).isNodeInclude(currentNode);
                if (i != 1) {
                    return i;
                }
            }
        }
        if ((this.xpbthNodeSet != null) && !this.xpbthNodeSet.contbins(currentNode)) {
            return 0;
        }
        return 1;
    }

    protected boolebn isVisible(Node currentNode) {
        if (nodeFilter != null) {
            Iterbtor<NodeFilter> it = nodeFilter.iterbtor();
            while (it.hbsNext()) {
                if (it.next().isNodeInclude(currentNode) != 1) {
                    return fblse;
                }
            }
        }
        if ((this.xpbthNodeSet != null) && !this.xpbthNodeSet.contbins(currentNode)) {
            return fblse;
        }
        return true;
    }

    protected void hbndlePbrent(Element e, NbmeSpbceSymbTbble ns) {
        if (!e.hbsAttributes() && e.getNbmespbceURI() == null) {
            return;
        }
        NbmedNodeMbp bttrs = e.getAttributes();
        int bttrsLength = bttrs.getLength();
        for (int i = 0; i < bttrsLength; i++) {
            Attr bttribute = (Attr) bttrs.item(i);
            String NNbme = bttribute.getLocblNbme();
            String NVblue = bttribute.getNodeVblue();

            if (Constbnts.NbmespbceSpecNS.equbls(bttribute.getNbmespbceURI())
                && (!XML.equbls(NNbme) || !Constbnts.XML_LANG_SPACE_SpecNS.equbls(NVblue))) {
                ns.bddMbpping(NNbme, NVblue, bttribute);
            }
        }
        if (e.getNbmespbceURI() != null) {
            String NNbme = e.getPrefix();
            String NVblue = e.getNbmespbceURI();
            String Nbme;
            if (NNbme == null || NNbme.equbls("")) {
                NNbme = XMLNS;
                Nbme = XMLNS;
            } else {
                Nbme = XMLNS + ":" + NNbme;
            }
            Attr n = e.getOwnerDocument().crebteAttributeNS("http://www.w3.org/2000/xmlns/", Nbme);
            n.setVblue(NVblue);
            ns.bddMbpping(NNbme, NVblue, n);
        }
    }

    /**
     * Adds to ns the definitions from the pbrent elements of el
     * @pbrbm el
     * @pbrbm ns
     */
    protected finbl void getPbrentNbmeSpbces(Element el, NbmeSpbceSymbTbble ns)  {
        Node n1 = el.getPbrentNode();
        if (n1 == null || Node.ELEMENT_NODE != n1.getNodeType()) {
            return;
        }
        //Obtbin bll the pbrents of the element
        List<Element> pbrents = new ArrbyList<Element>();
        Node pbrent = n1;
        while (pbrent != null && Node.ELEMENT_NODE == pbrent.getNodeType()) {
            pbrents.bdd((Element)pbrent);
            pbrent = pbrent.getPbrentNode();
        }
        //Visit them in reverse order.
        ListIterbtor<Element> it = pbrents.listIterbtor(pbrents.size());
        while (it.hbsPrevious()) {
            Element ele = it.previous();
            hbndlePbrent(ele, ns);
        }
        pbrents.clebr();
        Attr nsprefix;
        if (((nsprefix = ns.getMbppingWithoutRendered(XMLNS)) != null)
                && "".equbls(nsprefix.getVblue())) {
            ns.bddMbppingAndRender(
                    XMLNS, "", getNullNode(nsprefix.getOwnerDocument()));
        }
    }

    /**
     * Obtbin the bttributes to output for this node in XPbthNodeSet c14n.
     *
     * @pbrbm element
     * @pbrbm ns
     * @return the bttributes nodes to output.
     * @throws CbnonicblizbtionException
     */
    bbstrbct Iterbtor<Attr> hbndleAttributes(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException;

    /**
     * Obtbin the bttributes to output for this node in b Subtree c14n.
     *
     * @pbrbm element
     * @pbrbm ns
     * @return the bttributes nodes to output.
     * @throws CbnonicblizbtionException
     */
    bbstrbct Iterbtor<Attr> hbndleAttributesSubtree(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException;

    bbstrbct void circumventBugIfNeeded(XMLSignbtureInput input)
        throws CbnonicblizbtionException, PbrserConfigurbtionException, IOException, SAXException;

    /**
     * Outputs bn Attribute to the internbl Writer.
     *
     * The string vblue of the node is modified by replbcing
     * <UL>
     * <LI>bll bmpersbnds (&) with <CODE>&bmp;bmp;</CODE></LI>
     * <LI>bll open bngle brbckets (<) with <CODE>&bmp;lt;</CODE></LI>
     * <LI>bll quotbtion mbrk chbrbcters with <CODE>&bmp;quot;</CODE></LI>
     * <LI>bnd the whitespbce chbrbcters <CODE>#x9</CODE>, #xA, bnd #xD, with chbrbcter
     * references. The chbrbcter references bre written in uppercbse
     * hexbdecimbl with no lebding zeroes (for exbmple, <CODE>#xD</CODE> is represented
     * by the chbrbcter reference <CODE>&bmp;#xD;</CODE>)</LI>
     * </UL>
     *
     * @pbrbm nbme
     * @pbrbm vblue
     * @pbrbm writer
     * @throws IOException
     */
    protected stbtic finbl void outputAttrToWriter(
        finbl String nbme, finbl String vblue,
        finbl OutputStrebm writer, finbl Mbp<String, byte[]> cbche
    ) throws IOException {
        writer.write(' ');
        UtfHelpper.writeByte(nbme, writer, cbche);
        writer.write(EQUALS_STR.clone());
        byte[] toWrite;
        finbl int length = vblue.length();
        int i = 0;
        while (i < length) {
            chbr c = vblue.chbrAt(i++);

            switch (c) {

            cbse '&' :
                toWrite = AMP.clone();
                brebk;

            cbse '<' :
                toWrite = LT.clone();
                brebk;

            cbse '"' :
                toWrite = QUOT.clone();
                brebk;

            cbse 0x09 :    // '\t'
                toWrite = X9.clone();
                brebk;

            cbse 0x0A :    // '\n'
                toWrite = XA.clone();
                brebk;

            cbse 0x0D :    // '\r'
                toWrite = XD.clone();
                brebk;

            defbult :
                if (c < 0x80) {
                    writer.write(c);
                } else {
                    UtfHelpper.writeChbrToUtf8(c, writer);
                }
                continue;
            }
            writer.write(toWrite);
        }

        writer.write('\"');
    }

    /**
     * Outputs b PI to the internbl Writer.
     *
     * @pbrbm currentPI
     * @pbrbm writer where to write the things
     * @throws IOException
     */
    protected void outputPItoWriter(
        ProcessingInstruction currentPI, OutputStrebm writer, int position
    ) throws IOException {
        if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
            writer.write('\n');
        }
        writer.write(BEGIN_PI.clone());

        finbl String tbrget = currentPI.getTbrget();
        int length = tbrget.length();

        for (int i = 0; i < length; i++) {
            chbr c = tbrget.chbrAt(i);
            if (c == 0x0D) {
                writer.write(XD.clone());
            } else {
                if (c < 0x80) {
                    writer.write(c);
                } else {
                    UtfHelpper.writeChbrToUtf8(c, writer);
                }
            }
        }

        finbl String dbtb = currentPI.getDbtb();

        length = dbtb.length();

        if (length > 0) {
            writer.write(' ');

            for (int i = 0; i < length; i++) {
                chbr c = dbtb.chbrAt(i);
                if (c == 0x0D) {
                    writer.write(XD.clone());
                } else {
                    UtfHelpper.writeChbrToUtf8(c, writer);
                }
            }
        }

        writer.write(END_PI.clone());
        if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
            writer.write('\n');
        }
    }

    /**
     * Method outputCommentToWriter
     *
     * @pbrbm currentComment
     * @pbrbm writer writer where to write the things
     * @throws IOException
     */
    protected void outputCommentToWriter(
        Comment currentComment, OutputStrebm writer, int position
    ) throws IOException {
        if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
            writer.write('\n');
        }
        writer.write(BEGIN_COMM.clone());

        finbl String dbtb = currentComment.getDbtb();
        finbl int length = dbtb.length();

        for (int i = 0; i < length; i++) {
            chbr c = dbtb.chbrAt(i);
            if (c == 0x0D) {
                writer.write(XD.clone());
            } else {
                if (c < 0x80) {
                    writer.write(c);
                } else {
                    UtfHelpper.writeChbrToUtf8(c, writer);
                }
            }
        }

        writer.write(END_COMM.clone());
        if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
            writer.write('\n');
        }
    }

    /**
     * Outputs b Text of CDATA section to the internbl Writer.
     *
     * @pbrbm text
     * @pbrbm writer writer where to write the things
     * @throws IOException
     */
    protected stbtic finbl void outputTextToWriter(
        finbl String text, finbl OutputStrebm writer
    ) throws IOException {
        finbl int length = text.length();
        byte[] toWrite;
        for (int i = 0; i < length; i++) {
            chbr c = text.chbrAt(i);

            switch (c) {

            cbse '&' :
                toWrite = AMP.clone();
                brebk;

            cbse '<' :
                toWrite = LT.clone();
                brebk;

            cbse '>' :
                toWrite = GT.clone();
                brebk;

            cbse 0xD :
                toWrite = XD.clone();
                brebk;

            defbult :
                if (c < 0x80) {
                    writer.write(c);
                } else {
                    UtfHelpper.writeChbrToUtf8(c, writer);
                }
                continue;
            }
            writer.write(toWrite);
        }
    }

    // The null xmlns definition.
    protected Attr getNullNode(Document ownerDocument) {
        if (nullNode == null) {
            try {
                nullNode = ownerDocument.crebteAttributeNS(
                                    Constbnts.NbmespbceSpecNS, XMLNS);
                nullNode.setVblue("");
            } cbtch (Exception e) {
                throw new RuntimeException("Unbble to crebte nullNode: " + e);
            }
        }
        return nullNode;
    }

}
