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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions;

import jbvb.io.OutputStrebm;

import jbvbx.xml.trbnsform.TrbnsformerException;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityRuntimeException;
import com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformSpi;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformbtionException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.XPbthAPI;
import com.sun.org.bpbche.xml.internbl.security.utils.XPbthFbctory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Clbss TrbnsformXPbth
 *
 * Implements the <CODE>http://www.w3.org/TR/1999/REC-xpbth-19991116</CODE>
 * trbnsform.
 *
 * @buthor Christibn Geuer-Pollmbnn
 * @see <b href="http://www.w3.org/TR/1999/REC-xpbth-19991116">XPbth</b>
 *
 */
public clbss TrbnsformXPbth extends TrbnsformSpi {

    /** Field implementedTrbnsformURI */
    public stbtic finbl String implementedTrbnsformURI = Trbnsforms.TRANSFORM_XPATH;

    /**
     * Method engineGetURI
     *
     * @inheritDoc
     */
    protected String engineGetURI() {
        return implementedTrbnsformURI;
    }

    /**
     * Method enginePerformTrbnsform
     * @inheritDoc
     * @pbrbm input
     *
     * @throws TrbnsformbtionException
     */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm os, Trbnsform trbnsformObject
    ) throws TrbnsformbtionException {
        try {
            /**
             * If the bctubl input is bn octet strebm, then the bpplicbtion MUST
             * convert the octet strebm to bn XPbth node-set suitbble for use by
             * Cbnonicbl XML with Comments. (A subsequent bpplicbtion of the
             * REQUIRED Cbnonicbl XML blgorithm would strip bwby these comments.)
             *
             * ...
             *
             * The evblubtion of this expression includes bll of the document's nodes
             * (including comments) in the node-set representing the octet strebm.
             */
            Element xpbthElement =
                XMLUtils.selectDsNode(
                    trbnsformObject.getElement().getFirstChild(), Constbnts._TAG_XPATH, 0);

            if (xpbthElement == null) {
                Object exArgs[] = { "ds:XPbth", "Trbnsform" };

                throw new TrbnsformbtionException("xml.WrongContent", exArgs);
            }
            Node xpbthnode = xpbthElement.getChildNodes().item(0);
            String str = XMLUtils.getStrFromNode(xpbthnode);
            input.setNeedsToBeExpbnded(needsCircumvent(str));
            if (xpbthnode == null) {
                throw new DOMException(
                    DOMException.HIERARCHY_REQUEST_ERR, "Text must be in ds:Xpbth"
                );
            }

            XPbthFbctory xpbthFbctory = XPbthFbctory.newInstbnce();
            XPbthAPI xpbthAPIInstbnce = xpbthFbctory.newXPbthAPI();
            input.bddNodeFilter(new XPbthNodeFilter(xpbthElement, xpbthnode, str, xpbthAPIInstbnce));
            input.setNodeSet(true);
            return input;
        } cbtch (DOMException ex) {
            throw new TrbnsformbtionException("empty", ex);
        }
    }

    /**
     * @pbrbm str
     * @return true if needs to be circumvent for bug.
     */
    privbte boolebn needsCircumvent(String str) {
        return (str.indexOf("nbmespbce") != -1) || (str.indexOf("nbme()") != -1);
    }

    stbtic clbss XPbthNodeFilter implements NodeFilter {

        XPbthAPI xPbthAPI;
        Node xpbthnode;
        Element xpbthElement;
        String str;

        XPbthNodeFilter(Element xpbthElement, Node xpbthnode, String str, XPbthAPI xPbthAPI) {
            this.xpbthnode = xpbthnode;
            this.str = str;
            this.xpbthElement = xpbthElement;
            this.xPbthAPI = xPbthAPI;
        }

        /**
         * @see com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter#isNodeInclude(org.w3c.dom.Node)
         */
        public int isNodeInclude(Node currentNode) {
            try {
                boolebn include = xPbthAPI.evblubte(currentNode, xpbthnode, str, xpbthElement);
                if (include) {
                    return 1;
                }
                return 0;
            } cbtch (TrbnsformerException e) {
                Object[] eArgs = {currentNode};
                throw new XMLSecurityRuntimeException("signbture.Trbnsform.node", eArgs, e);
            } cbtch (Exception e) {
                Object[] eArgs = {currentNode, Short.vblueOf(currentNode.getNodeType())};
                throw new XMLSecurityRuntimeException("signbture.Trbnsform.nodeAndType",eArgs, e);
            }
        }

        public int isNodeIncludeDO(Node n, int level) {
            return isNodeInclude(n);
        }

    }
}
