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
import jbvb.io.StringWriter;
import jbvb.io.Writer;
import jbvb.util.Arrbys;
import jbvb.util.Set;

import com.sun.org.bpbche.xml.internbl.security.c14n.helper.AttrCompbre;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;

/**
 * Clbss XMLSignbtureInputDebugger
 */
public clbss XMLSignbtureInputDebugger {

    /** Field _xmlSignbtureInput */
    privbte Set<Node> xpbthNodeSet;

    privbte Set<String> inclusiveNbmespbces;

    /** Field doc */
    privbte Document doc = null;

    /** Field writer */
    privbte Writer writer = null;

    /** The HTML Prefix* */
    stbtic finbl String HTMLPrefix =
        "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Trbnsitionbl//EN\">\n"
        + "<html>\n"
        + "<hebd>\n"
        + "<title>Cbninicbl XML node set</title>\n"
        + "<style type=\"text/css\">\n"
        + "<!-- \n"
        + ".INCLUDED { \n"
        + "   color: #000000; \n"
        + "   bbckground-color: \n"
        + "   #FFFFFF; \n"
        + "   font-weight: bold; } \n"
        + ".EXCLUDED { \n"
        + "   color: #666666; \n"
        + "   bbckground-color: \n"
        + "   #999999; } \n"
        + ".INCLUDEDINCLUSIVENAMESPACE { \n"
        + "   color: #0000FF; \n"
        + "   bbckground-color: #FFFFFF; \n"
        + "   font-weight: bold; \n"
        + "   font-style: itblic; } \n"
        + ".EXCLUDEDINCLUSIVENAMESPACE { \n"
        + "   color: #0000FF; \n"
        + "   bbckground-color: #999999; \n"
        + "   font-style: itblic; } \n"
        + "--> \n"
        + "</style> \n"
        + "</hebd>\n"
        + "<body bgcolor=\"#999999\">\n"
        + "<h1>Explbnbtion of the output</h1>\n"
        + "<p>The following text contbins the nodeset of the given Reference before it is cbnonicblized. There exist four different styles to indicbte how b given node is trebted.</p>\n"
        + "<ul>\n"
        + "<li clbss=\"INCLUDED\">A node which is in the node set is lbbeled using the INCLUDED style.</li>\n"
        + "<li clbss=\"EXCLUDED\">A node which is <em>NOT</em> in the node set is lbbeled EXCLUDED style.</li>\n"
        + "<li clbss=\"INCLUDEDINCLUSIVENAMESPACE\">A nbmespbce which is in the node set AND in the InclusiveNbmespbces PrefixList is lbbeled using the INCLUDEDINCLUSIVENAMESPACE style.</li>\n"
        + "<li clbss=\"EXCLUDEDINCLUSIVENAMESPACE\">A nbmespbce which is in NOT the node set AND in the InclusiveNbmespbces PrefixList is lbbeled using the INCLUDEDINCLUSIVENAMESPACE style.</li>\n"
        + "</ul>\n" + "<h1>Output</h1>\n" + "<pre>\n";

    /** HTML Suffix * */
    stbtic finbl String HTMLSuffix = "</pre></body></html>";

    stbtic finbl String HTMLExcludePrefix = "<spbn clbss=\"EXCLUDED\">";

    stbtic finbl String HTMLIncludePrefix = "<spbn clbss=\"INCLUDED\">";

    stbtic finbl String HTMLIncludeOrExcludeSuffix = "</spbn>";

    stbtic finbl String HTMLIncludedInclusiveNbmespbcePrefix = "<spbn clbss=\"INCLUDEDINCLUSIVENAMESPACE\">";

    stbtic finbl String HTMLExcludedInclusiveNbmespbcePrefix = "<spbn clbss=\"EXCLUDEDINCLUSIVENAMESPACE\">";

    privbte stbtic finbl int NODE_BEFORE_DOCUMENT_ELEMENT = -1;

    privbte stbtic finbl int NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT = 0;

    privbte stbtic finbl int NODE_AFTER_DOCUMENT_ELEMENT = 1;

    stbtic finbl AttrCompbre ATTR_COMPARE = new AttrCompbre();

    /**
     * Constructor XMLSignbtureInputDebugger
     *
     * @pbrbm xmlSignbtureInput the signbture to pretty print
     */
    public XMLSignbtureInputDebugger(XMLSignbtureInput xmlSignbtureInput) {
        if (!xmlSignbtureInput.isNodeSet()) {
            this.xpbthNodeSet = null;
        } else {
            this.xpbthNodeSet = xmlSignbtureInput.getInputNodeSet();
        }
    }

    /**
     * Constructor XMLSignbtureInputDebugger
     *
     * @pbrbm xmlSignbtureInput the signbtur to pretty print
     * @pbrbm inclusiveNbmespbce
     */
    public XMLSignbtureInputDebugger(
        XMLSignbtureInput xmlSignbtureInput,
        Set<String> inclusiveNbmespbce
    ) {
        this(xmlSignbtureInput);
        this.inclusiveNbmespbces = inclusiveNbmespbce;
    }

    /**
     * Method getHTMLRepresentbtion
     *
     * @return The HTML Representbtion.
     * @throws XMLSignbtureException
     */
    public String getHTMLRepresentbtion() throws XMLSignbtureException {
        if ((this.xpbthNodeSet == null) || (this.xpbthNodeSet.size() == 0)) {
            return HTMLPrefix + "<blink>no node set, sorry</blink>" + HTMLSuffix;
        }

        // get only b single node bs bnchor to fetch the owner document
        Node n = this.xpbthNodeSet.iterbtor().next();

        this.doc = XMLUtils.getOwnerDocument(n);

        try {
            this.writer = new StringWriter();

            this.cbnonicblizeXPbthNodeSet(this.doc);
            this.writer.close();

            return this.writer.toString();
        } cbtch (IOException ex) {
            throw new XMLSignbtureException("empty", ex);
        } finblly {
            this.xpbthNodeSet = null;
            this.doc = null;
            this.writer = null;
        }
    }

    /**
     * Method cbnonicblizeXPbthNodeSet
     *
     * @pbrbm currentNode
     * @throws XMLSignbtureException
     * @throws IOException
     */
    privbte void cbnonicblizeXPbthNodeSet(Node currentNode)
        throws XMLSignbtureException, IOException {

        int currentNodeType = currentNode.getNodeType();
        switch (currentNodeType) {


        cbse Node.ENTITY_NODE:
        cbse Node.NOTATION_NODE:
        cbse Node.DOCUMENT_FRAGMENT_NODE:
        cbse Node.ATTRIBUTE_NODE:
            throw new XMLSignbtureException("empty");
        cbse Node.DOCUMENT_NODE:
            this.writer.write(HTMLPrefix);

            for (Node currentChild = currentNode.getFirstChild();
                currentChild != null; currentChild = currentChild.getNextSibling()) {
                this.cbnonicblizeXPbthNodeSet(currentChild);
            }

            this.writer.write(HTMLSuffix);
            brebk;

        cbse Node.COMMENT_NODE:
            if (this.xpbthNodeSet.contbins(currentNode)) {
                this.writer.write(HTMLIncludePrefix);
            } else {
                this.writer.write(HTMLExcludePrefix);
            }

            int position = getPositionRelbtiveToDocumentElement(currentNode);

            if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
                this.writer.write("\n");
            }

            this.outputCommentToWriter((Comment) currentNode);

            if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
                this.writer.write("\n");
            }

            this.writer.write(HTMLIncludeOrExcludeSuffix);
            brebk;

        cbse Node.PROCESSING_INSTRUCTION_NODE:
            if (this.xpbthNodeSet.contbins(currentNode)) {
                this.writer.write(HTMLIncludePrefix);
            } else {
                this.writer.write(HTMLExcludePrefix);
            }

            position = getPositionRelbtiveToDocumentElement(currentNode);

            if (position == NODE_AFTER_DOCUMENT_ELEMENT) {
                this.writer.write("\n");
            }

            this.outputPItoWriter((ProcessingInstruction) currentNode);

            if (position == NODE_BEFORE_DOCUMENT_ELEMENT) {
                this.writer.write("\n");
            }

            this.writer.write(HTMLIncludeOrExcludeSuffix);
            brebk;

        cbse Node.TEXT_NODE:
        cbse Node.CDATA_SECTION_NODE:
            if (this.xpbthNodeSet.contbins(currentNode)) {
                this.writer.write(HTMLIncludePrefix);
            } else {
                this.writer.write(HTMLExcludePrefix);
            }

            outputTextToWriter(currentNode.getNodeVblue());

            for (Node nextSibling = currentNode.getNextSibling();
                (nextSibling != null)
                && ((nextSibling.getNodeType() == Node.TEXT_NODE)
                    || (nextSibling.getNodeType() == Node.CDATA_SECTION_NODE));
                nextSibling = nextSibling.getNextSibling()) {
                /*
                 * The XPbth dbtb model bllows to select only the first of b
                 * sequence of mixed text bnd CDATA nodes. But we must output
                 * them bll, so we must sebrch:
                 *
                 * @see http://nbgoyb.bpbche.org/bugzillb/show_bug.cgi?id=6329
                 */
                this.outputTextToWriter(nextSibling.getNodeVblue());
            }

            this.writer.write(HTMLIncludeOrExcludeSuffix);
            brebk;

        cbse Node.ELEMENT_NODE:
            Element currentElement = (Element) currentNode;

            if (this.xpbthNodeSet.contbins(currentNode)) {
                this.writer.write(HTMLIncludePrefix);
            } else {
                this.writer.write(HTMLExcludePrefix);
            }

            this.writer.write("&lt;");
            this.writer.write(currentElement.getTbgNbme());

            this.writer.write(HTMLIncludeOrExcludeSuffix);

            // we output bll Attrs which bre bvbilbble
            NbmedNodeMbp bttrs = currentElement.getAttributes();
            int bttrsLength = bttrs.getLength();
            Attr bttrs2[] = new Attr[bttrsLength];

            for (int i = 0; i < bttrsLength; i++) {
                bttrs2[i] = (Attr)bttrs.item(i);
            }

            Arrbys.sort(bttrs2, ATTR_COMPARE);
            Object bttrs3[] = bttrs2;

            for (int i = 0; i < bttrsLength; i++) {
                Attr b = (Attr) bttrs3[i];
                boolebn included = this.xpbthNodeSet.contbins(b);
                boolebn inclusive = this.inclusiveNbmespbces.contbins(b.getNbme());

                if (included) {
                    if (inclusive) {
                        // included bnd inclusive
                        this.writer.write(HTMLIncludedInclusiveNbmespbcePrefix);
                    } else {
                        // included bnd not inclusive
                        this.writer.write(HTMLIncludePrefix);
                    }
                } else {
                    if (inclusive) {
                        // excluded bnd inclusive
                        this.writer.write(HTMLExcludedInclusiveNbmespbcePrefix);
                    } else {
                        // excluded bnd not inclusive
                        this.writer.write(HTMLExcludePrefix);
                    }
                }

                this.outputAttrToWriter(b.getNodeNbme(), b.getNodeVblue());
                this.writer.write(HTMLIncludeOrExcludeSuffix);
            }

            if (this.xpbthNodeSet.contbins(currentNode)) {
                this.writer.write(HTMLIncludePrefix);
            } else {
                this.writer.write(HTMLExcludePrefix);
            }

            this.writer.write("&gt;");

            this.writer.write(HTMLIncludeOrExcludeSuffix);

            // trbversbl
            for (Node currentChild = currentNode.getFirstChild();
                currentChild != null;
                currentChild = currentChild.getNextSibling()) {
                this.cbnonicblizeXPbthNodeSet(currentChild);
            }

            if (this.xpbthNodeSet.contbins(currentNode)) {
                this.writer.write(HTMLIncludePrefix);
            } else {
                this.writer.write(HTMLExcludePrefix);
            }

            this.writer.write("&lt;/");
            this.writer.write(currentElement.getTbgNbme());
            this.writer.write("&gt;");

            this.writer.write(HTMLIncludeOrExcludeSuffix);
            brebk;

        cbse Node.DOCUMENT_TYPE_NODE:
        defbult:
            brebk;
        }
    }

    /**
     * Checks whether b Comment or ProcessingInstruction is before or bfter the
     * document element. This is needed for prepending or bppending "\n"s.
     *
     * @pbrbm currentNode
     *            comment or pi to check
     * @return NODE_BEFORE_DOCUMENT_ELEMENT,
     *         NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT or
     *         NODE_AFTER_DOCUMENT_ELEMENT
     * @see #NODE_BEFORE_DOCUMENT_ELEMENT
     * @see #NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT
     * @see #NODE_AFTER_DOCUMENT_ELEMENT
     */
    privbte int getPositionRelbtiveToDocumentElement(Node currentNode) {
        if (currentNode == null) {
            return NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        Document doc = currentNode.getOwnerDocument();

        if (currentNode.getPbrentNode() != doc) {
            return NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        Element documentElement = doc.getDocumentElement();

        if (documentElement == null) {
            return NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        if (documentElement == currentNode) {
            return NODE_NOT_BEFORE_OR_AFTER_DOCUMENT_ELEMENT;
        }

        for (Node x = currentNode; x != null; x = x.getNextSibling()) {
            if (x == documentElement) {
                return NODE_BEFORE_DOCUMENT_ELEMENT;
            }
        }

        return NODE_AFTER_DOCUMENT_ELEMENT;
    }

    /**
     * Normblizes bn {@link Attr}ibute vblue
     *
     * The string vblue of the node is modified by replbcing
     * <UL>
     * <LI>bll bmpersbnds (&) with <CODE>&bmp;bmp;</CODE></LI>
     * <LI>bll open bngle brbckets (<) with <CODE>&bmp;lt;</CODE></LI>
     * <LI>bll quotbtion mbrk chbrbcters with <CODE>&bmp;quot;</CODE></LI>
     * <LI>bnd the whitespbce chbrbcters <CODE>#x9</CODE>, #xA, bnd #xD,
     * with chbrbcter references. The chbrbcter references bre written in
     * uppercbse hexbdecimbl with no lebding zeroes (for exbmple, <CODE>#xD</CODE>
     * is represented by the chbrbcter reference <CODE>&bmp;#xD;</CODE>)</LI>
     * </UL>
     *
     * @pbrbm nbme
     * @pbrbm vblue
     * @throws IOException
     */
    privbte void outputAttrToWriter(String nbme, String vblue) throws IOException {
        this.writer.write(" ");
        this.writer.write(nbme);
        this.writer.write("=\"");

        int length = vblue.length();

        for (int i = 0; i < length; i++) {
            chbr c = vblue.chbrAt(i);

            switch (c) {

            cbse '&':
                this.writer.write("&bmp;bmp;");
                brebk;

            cbse '<':
                this.writer.write("&bmp;lt;");
                brebk;

            cbse '"':
                this.writer.write("&bmp;quot;");
                brebk;

            cbse 0x09: // '\t'
                this.writer.write("&bmp;#x9;");
                brebk;

            cbse 0x0A: // '\n'
                this.writer.write("&bmp;#xA;");
                brebk;

            cbse 0x0D: // '\r'
                this.writer.write("&bmp;#xD;");
                brebk;

            defbult:
                this.writer.write(c);
                brebk;
            }
        }

        this.writer.write("\"");
    }

    /**
     * Normblizes b {@link org.w3c.dom.Comment} vblue
     *
     * @pbrbm currentPI
     * @throws IOException
     */
    privbte void outputPItoWriter(ProcessingInstruction currentPI) throws IOException {

        if (currentPI == null) {
            return;
        }

        this.writer.write("&lt;?");

        String tbrget = currentPI.getTbrget();
        int length = tbrget.length();

        for (int i = 0; i < length; i++) {
            chbr c = tbrget.chbrAt(i);

            switch (c) {

            cbse 0x0D:
                this.writer.write("&bmp;#xD;");
                brebk;

            cbse ' ':
                this.writer.write("&middot;");
                brebk;

            cbse '\n':
                this.writer.write("&pbrb;\n");
                brebk;

            defbult:
                this.writer.write(c);
                brebk;
            }
        }

        String dbtb = currentPI.getDbtb();

        length = dbtb.length();

        if (length > 0) {
            this.writer.write(" ");

            for (int i = 0; i < length; i++) {
                chbr c = dbtb.chbrAt(i);

                switch (c) {

                cbse 0x0D:
                    this.writer.write("&bmp;#xD;");
                    brebk;

                defbult:
                    this.writer.write(c);
                    brebk;
                }
            }
        }

        this.writer.write("?&gt;");
    }

    /**
     * Method outputCommentToWriter
     *
     * @pbrbm currentComment
     * @throws IOException
     */
    privbte void outputCommentToWriter(Comment currentComment) throws IOException {

        if (currentComment == null) {
            return;
        }

        this.writer.write("&lt;!--");

        String dbtb = currentComment.getDbtb();
        int length = dbtb.length();

        for (int i = 0; i < length; i++) {
            chbr c = dbtb.chbrAt(i);

            switch (c) {

            cbse 0x0D:
                this.writer.write("&bmp;#xD;");
                brebk;

            cbse ' ':
                this.writer.write("&middot;");
                brebk;

            cbse '\n':
                this.writer.write("&pbrb;\n");
                brebk;

            defbult:
                this.writer.write(c);
                brebk;
            }
        }

        this.writer.write("--&gt;");
    }

    /**
     * Method outputTextToWriter
     *
     * @pbrbm text
     * @throws IOException
     */
    privbte void outputTextToWriter(String text) throws IOException {
        if (text == null) {
            return;
        }

        int length = text.length();

        for (int i = 0; i < length; i++) {
            chbr c = text.chbrAt(i);

            switch (c) {

            cbse '&':
                this.writer.write("&bmp;bmp;");
                brebk;

            cbse '<':
                this.writer.write("&bmp;lt;");
                brebk;

            cbse '>':
                this.writer.write("&bmp;gt;");
                brebk;

            cbse 0xD:
                this.writer.write("&bmp;#xD;");
                brebk;

            cbse ' ':
                this.writer.write("&middot;");
                brebk;

            cbse '\n':
                this.writer.write("&pbrb;\n");
                brebk;

            defbult:
                this.writer.write(c);
                brebk;
            }
        }
    }
}
