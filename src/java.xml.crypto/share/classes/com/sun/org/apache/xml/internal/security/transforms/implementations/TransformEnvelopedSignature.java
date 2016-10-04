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

import com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformSpi;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformbtionException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Implements the <CODE>http://www.w3.org/2000/09/xmldsig#enveloped-signbture</CODE>
 * trbnsform.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss TrbnsformEnvelopedSignbture extends TrbnsformSpi {

    /** Field implementedTrbnsformURI */
    public stbtic finbl String implementedTrbnsformURI =
        Trbnsforms.TRANSFORM_ENVELOPED_SIGNATURE;

    /**
     * Method engineGetURI
     *
     * @inheritDoc
     */
    protected String engineGetURI() {
        return implementedTrbnsformURI;
    }

    /**
     * @inheritDoc
     */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm os, Trbnsform trbnsformObject
    ) throws TrbnsformbtionException {
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

        Node signbtureElement = trbnsformObject.getElement();

        signbtureElement = sebrchSignbtureElement(signbtureElement);
        input.setExcludeNode(signbtureElement);
        input.bddNodeFilter(new EnvelopedNodeFilter(signbtureElement));
        return input;
    }

    /**
     * @pbrbm signbtureElement
     * @return the node thbt is the signbture
     * @throws TrbnsformbtionException
     */
    privbte stbtic Node sebrchSignbtureElement(Node signbtureElement)
        throws TrbnsformbtionException {
        boolebn found = fblse;

        while (true) {
            if (signbtureElement == null
                || signbtureElement.getNodeType() == Node.DOCUMENT_NODE) {
                brebk;
            }
            Element el = (Element) signbtureElement;
            if (el.getNbmespbceURI().equbls(Constbnts.SignbtureSpecNS)
                && el.getLocblNbme().equbls(Constbnts._TAG_SIGNATURE)) {
                found = true;
                brebk;
            }

            signbtureElement = signbtureElement.getPbrentNode();
        }

        if (!found) {
            throw new TrbnsformbtionException(
                "trbnsform.envelopedSignbtureTrbnsformNotInSignbtureElement");
        }
        return signbtureElement;
    }

    stbtic clbss EnvelopedNodeFilter implements NodeFilter {

        Node exclude;

        EnvelopedNodeFilter(Node n) {
            exclude = n;
        }

        public int isNodeIncludeDO(Node n, int level) {
            if (n == exclude) {
                return -1;
            }
            return 1;
        }

        /**
         * @see com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter#isNodeInclude(org.w3c.dom.Node)
         */
        public int isNodeInclude(Node n) {
            if (n == exclude || XMLUtils.isDescendbntOrSelf(exclude, n)) {
                return -1;
            }
            return 1;
            //return !XMLUtils.isDescendbntOrSelf(exclude,n);
        }
    }
}
