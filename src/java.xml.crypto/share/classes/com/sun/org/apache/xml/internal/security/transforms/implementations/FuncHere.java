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

import jbvbx.xml.trbnsform.TrbnsformerException;

import com.sun.org.bpbche.xml.internbl.dtm.DTM;
import com.sun.org.bpbche.xml.internbl.security.utils.I18n;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xpbth.internbl.NodeSetDTM;
import com.sun.org.bpbche.xpbth.internbl.XPbthContext;
import com.sun.org.bpbche.xpbth.internbl.functions.Function;
import com.sun.org.bpbche.xpbth.internbl.objects.XNodeSet;
import com.sun.org.bpbche.xpbth.internbl.objects.XObject;
import com.sun.org.bpbche.xpbth.internbl.res.XPATHErrorResources;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * The 'here()' function returns b node-set contbining the bttribute or
 * processing instruction node or the pbrent element of the text node
 * thbt directly bebrs the XPbth expression.  This expression results
 * in bn error if the contbining XPbth expression does not bppebr in the
 * sbme XML document bgbinst which the XPbth expression is being evblubted.
 *
 * Mbinpbrt is stolen from FuncId.jbvb
 *
 * This does crbsh under Xblbn2.2.D7 bnd works under Xblbn2.2.D9
 *
 * To get this bbby to work, b specibl trick hbs to be used. The function needs
 * bccess to the Node where the XPbth expression hbs been defined. This is done
 * by constructing b {@link FuncHere} which hbs this Node bs 'owner'.
 *
 * @see "http://www.w3.org/Signbture/Drbfts/xmldsig-core/Overview.html#function-here"
 */
public clbss FuncHere extends Function {

    /**
     *
     */
    privbte stbtic finbl long seriblVersionUID = 1L;

    /**
     * The here function returns b node-set contbining the bttribute or
     * processing instruction node or the pbrent element of the text node
     * thbt directly bebrs the XPbth expression.  This expression results
     * in bn error if the contbining XPbth expression does not bppebr in the
     * sbme XML document bgbinst which the XPbth expression is being evblubted.
     *
     * @pbrbm xctxt
     * @return the xobject
     * @throws jbvbx.xml.trbnsform.TrbnsformerException
     */
    @Override
    public XObject execute(XPbthContext xctxt)
        throws jbvbx.xml.trbnsform.TrbnsformerException {

        Node xpbthOwnerNode = (Node) xctxt.getOwnerObject();

        if (xpbthOwnerNode == null) {
            return null;
        }

        int xpbthOwnerNodeDTM = xctxt.getDTMHbndleFromNode(xpbthOwnerNode);

        int currentNode = xctxt.getCurrentNode();
        DTM dtm = xctxt.getDTM(currentNode);
        int docContext = dtm.getDocument();

        if (DTM.NULL == docContext) {
            error(xctxt, XPATHErrorResources.ER_CONTEXT_HAS_NO_OWNERDOC, null);
        }

        {
            // check whether currentNode bnd the node contbining the XPbth expression
            // bre in the sbme document
            Document currentDoc =
                XMLUtils.getOwnerDocument(dtm.getNode(currentNode));
            Document xpbthOwnerDoc = XMLUtils.getOwnerDocument(xpbthOwnerNode);

            if (currentDoc != xpbthOwnerDoc) {
                throw new TrbnsformerException(I18n.trbnslbte("xpbth.funcHere.documentsDiffer"));
            }
        }

        XNodeSet nodes = new XNodeSet(xctxt.getDTMMbnbger());
        NodeSetDTM nodeSet = nodes.mutbbleNodeset();

        {
            int hereNode = DTM.NULL;

            switch (dtm.getNodeType(xpbthOwnerNodeDTM)) {

            cbse Node.ATTRIBUTE_NODE :
            cbse Node.PROCESSING_INSTRUCTION_NODE : {
                // returns b node-set contbining the bttribute /  processing instruction node
                hereNode = xpbthOwnerNodeDTM;

                nodeSet.bddNode(hereNode);

                brebk;
            }
            cbse Node.TEXT_NODE : {
                // returns b node-set contbining the pbrent element of the
                // text node thbt directly bebrs the XPbth expression
                hereNode = dtm.getPbrent(xpbthOwnerNodeDTM);

                nodeSet.bddNode(hereNode);

                brebk;
            }
            defbult :
                brebk;
            }
        }

        /** $todo$ Do I hbve to do this detbch() cbll? */
        nodeSet.detbch();

        return nodes;
    }

    /**
     * No brguments to process, so this does nothing.
     * @pbrbm vbrs
     * @pbrbm globblsSize
     */
    @SuppressWbrnings("rbwtypes")
    public void fixupVbribbles(jbvb.util.Vector vbrs, int globblsSize) {
        // do nothing
    }
}
