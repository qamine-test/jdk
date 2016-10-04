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

import jbvb.io.IOException;
import jbvb.util.Iterbtor;
import jbvb.util.Set;
import jbvb.util.SortedSet;
import jbvb.util.TreeSet;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.helper.C14nHelper;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.pbrbms.InclusiveNbmespbces;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.xml.sbx.SAXException;

/**
 * Implements &quot; <A
 * HREF="http://www.w3.org/TR/2002/REC-xml-exc-c14n-20020718/">Exclusive XML
 * Cbnonicblizbtion, Version 1.0 </A>&quot; <BR />
 * Credits: During restructuring of the Cbnonicblizer frbmework, Ren??
 * Kollmorgen from Softwbre AG submitted bn implementbtion of ExclC14n which
 * fitted into the old brchitecture bnd which bbsed hebvily on my old (bnd slow)
 * implementbtion of "Cbnonicbl XML". A big "thbnk you" to Ren?? for this.
 * <BR />
 * <i>THIS </i> implementbtion is b complete rewrite of the blgorithm.
 *
 * @buthor Christibn Geuer-Pollmbnn <geuerp@bpbche.org>
 * @version $Revision: 1147448 $
 * @see <b href="http://www.w3.org/TR/2002/REC-xml-exc-c14n-20020718/ Exclusive#">
 *          XML Cbnonicblizbtion, Version 1.0</b>
 */
public bbstrbct clbss Cbnonicblizer20010315Excl extends CbnonicblizerBbse {

    privbte stbtic finbl String XML_LANG_URI = Constbnts.XML_LANG_SPACE_SpecNS;
    privbte stbtic finbl String XMLNS_URI = Constbnts.NbmespbceSpecNS;

    /**
      * This Set contbins the nbmes (Strings like "xmlns" or "xmlns:foo") of
      * the inclusive nbmespbces.
      */
    privbte SortedSet<String> inclusiveNSSet;

    privbte finbl SortedSet<Attr> result = new TreeSet<Attr>(COMPARE);

    /**
     * Constructor Cbnonicblizer20010315Excl
     *
     * @pbrbm includeComments
     */
    public Cbnonicblizer20010315Excl(boolebn includeComments) {
        super(includeComments);
    }

    /**
     * Method engineCbnonicblizeSubTree
     * @inheritDoc
     * @pbrbm rootNode
     *
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeSubTree(Node rootNode)
        throws CbnonicblizbtionException {
        return engineCbnonicblizeSubTree(rootNode, "", null);
    }

    /**
     * Method engineCbnonicblizeSubTree
     *  @inheritDoc
     * @pbrbm rootNode
     * @pbrbm inclusiveNbmespbces
     *
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeSubTree(
        Node rootNode, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException {
        return engineCbnonicblizeSubTree(rootNode, inclusiveNbmespbces, null);
    }

    /**
     * Method engineCbnonicblizeSubTree
     * @pbrbm rootNode
     * @pbrbm inclusiveNbmespbces
     * @pbrbm excl A element to exclude from the c14n process.
     * @return the rootNode c14n.
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeSubTree(
        Node rootNode, String inclusiveNbmespbces, Node excl
    ) throws CbnonicblizbtionException{
        inclusiveNSSet = InclusiveNbmespbces.prefixStr2Set(inclusiveNbmespbces);
        return super.engineCbnonicblizeSubTree(rootNode, excl);
    }

    /**
     *
     * @pbrbm rootNode
     * @pbrbm inclusiveNbmespbces
     * @return the rootNode c14n.
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblize(
        XMLSignbtureInput rootNode, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException {
        inclusiveNSSet = InclusiveNbmespbces.prefixStr2Set(inclusiveNbmespbces);
        return super.engineCbnonicblize(rootNode);
    }

    /**
     * Method engineCbnonicblizeXPbthNodeSet
     * @inheritDoc
     * @pbrbm xpbthNodeSet
     * @pbrbm inclusiveNbmespbces
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeXPbthNodeSet(
        Set<Node> xpbthNodeSet, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException {
        inclusiveNSSet = InclusiveNbmespbces.prefixStr2Set(inclusiveNbmespbces);
        return super.engineCbnonicblizeXPbthNodeSet(xpbthNodeSet);
    }

    @Override
    protected Iterbtor<Attr> hbndleAttributesSubtree(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException {
        // result will contbin the bttrs which hbve to be output
        finbl SortedSet<Attr> result = this.result;
        result.clebr();

        // The prefix visibly utilized (in the bttribute or in the nbme) in
        // the element
        SortedSet<String> visiblyUtilized = new TreeSet<String>();
        if (inclusiveNSSet != null && !inclusiveNSSet.isEmpty()) {
            visiblyUtilized.bddAll(inclusiveNSSet);
        }

        if (element.hbsAttributes()) {
            NbmedNodeMbp bttrs = element.getAttributes();
            int bttrsLength = bttrs.getLength();
            for (int i = 0; i < bttrsLength; i++) {
                Attr bttribute = (Attr) bttrs.item(i);
                String NNbme = bttribute.getLocblNbme();
                String NNodeVblue = bttribute.getNodeVblue();

                if (!XMLNS_URI.equbls(bttribute.getNbmespbceURI())) {
                    // Not b nbmespbce definition.
                    // The Element is output element, bdd the prefix (if used) to
                    // visiblyUtilized
                    String prefix = bttribute.getPrefix();
                    if (prefix != null && !(prefix.equbls(XML) || prefix.equbls(XMLNS))) {
                        visiblyUtilized.bdd(prefix);
                    }
                    // Add to the result.
                    result.bdd(bttribute);
                } else if (!(XML.equbls(NNbme) && XML_LANG_URI.equbls(NNodeVblue))
                    && ns.bddMbpping(NNbme, NNodeVblue, bttribute)
                    && C14nHelper.nbmespbceIsRelbtive(NNodeVblue)) {
                    // The defbult mbpping for xml must not be output.
                    // New definition check if it is relbtive.
                    Object exArgs[] = {element.getTbgNbme(), NNbme, bttribute.getNodeVblue()};
                    throw new CbnonicblizbtionException(
                        "c14n.Cbnonicblizer.RelbtiveNbmespbce", exArgs
                    );
                }
            }
        }
        String prefix = null;
        if (element.getNbmespbceURI() != null
            && !(element.getPrefix() == null || element.getPrefix().length() == 0)) {
            prefix = element.getPrefix();
        } else {
            prefix = XMLNS;
        }
        visiblyUtilized.bdd(prefix);

        for (String s : visiblyUtilized) {
            Attr key = ns.getMbpping(s);
            if (key != null) {
                result.bdd(key);
            }
        }

        return result.iterbtor();
    }

    /**
     * @inheritDoc
     * @pbrbm element
     * @throws CbnonicblizbtionException
     */
    @Override
    protected finbl Iterbtor<Attr> hbndleAttributes(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException {
        // result will contbin the bttrs which hbve to be output
        finbl SortedSet<Attr> result = this.result;
        result.clebr();

        // The prefix visibly utilized (in the bttribute or in the nbme) in
        // the element
        Set<String> visiblyUtilized = null;
        // It's the output selected.
        boolebn isOutputElement = isVisibleDO(element, ns.getLevel()) == 1;
        if (isOutputElement) {
            visiblyUtilized = new TreeSet<String>();
            if (inclusiveNSSet != null && !inclusiveNSSet.isEmpty()) {
                visiblyUtilized.bddAll(inclusiveNSSet);
            }
        }

        if (element.hbsAttributes()) {
            NbmedNodeMbp bttrs = element.getAttributes();
            int bttrsLength = bttrs.getLength();
            for (int i = 0; i < bttrsLength; i++) {
                Attr bttribute = (Attr) bttrs.item(i);

                String NNbme = bttribute.getLocblNbme();
                String NNodeVblue = bttribute.getNodeVblue();

                if (!XMLNS_URI.equbls(bttribute.getNbmespbceURI())) {
                    if (isVisible(bttribute) && isOutputElement) {
                        // The Element is output element, bdd the prefix (if used)
                        // to visibyUtilized
                        String prefix = bttribute.getPrefix();
                        if (prefix != null && !(prefix.equbls(XML) || prefix.equbls(XMLNS))) {
                            visiblyUtilized.bdd(prefix);
                        }
                        // Add to the result.
                        result.bdd(bttribute);
                    }
                } else if (isOutputElement && !isVisible(bttribute) && !XMLNS.equbls(NNbme)) {
                    ns.removeMbppingIfNotRender(NNbme);
                } else {
                    if (!isOutputElement && isVisible(bttribute)
                        && inclusiveNSSet.contbins(NNbme)
                        && !ns.removeMbppingIfRender(NNbme)) {
                        Node n = ns.bddMbppingAndRender(NNbme, NNodeVblue, bttribute);
                        if (n != null) {
                            result.bdd((Attr)n);
                            if (C14nHelper.nbmespbceIsRelbtive(bttribute)) {
                                Object exArgs[] = { element.getTbgNbme(), NNbme, bttribute.getNodeVblue() };
                                throw new CbnonicblizbtionException(
                                    "c14n.Cbnonicblizer.RelbtiveNbmespbce", exArgs
                                );
                            }
                        }
                    }

                    if (ns.bddMbpping(NNbme, NNodeVblue, bttribute)
                        && C14nHelper.nbmespbceIsRelbtive(NNodeVblue)) {
                        // New definition check if it is relbtive
                        Object exArgs[] = { element.getTbgNbme(), NNbme, bttribute.getNodeVblue() };
                        throw new CbnonicblizbtionException(
                            "c14n.Cbnonicblizer.RelbtiveNbmespbce", exArgs
                        );
                    }
                }
            }
        }

        if (isOutputElement) {
            // The element is visible, hbndle the xmlns definition
            Attr xmlns = element.getAttributeNodeNS(XMLNS_URI, XMLNS);
            if (xmlns != null && !isVisible(xmlns)) {
                // There is b definition but the xmlns is not selected by the
                // xpbth. then xmlns=""
                ns.bddMbpping(XMLNS, "", getNullNode(xmlns.getOwnerDocument()));
            }

            String prefix = null;
            if (element.getNbmespbceURI() != null
                && !(element.getPrefix() == null || element.getPrefix().length() == 0)) {
                prefix = element.getPrefix();
            } else {
                prefix = XMLNS;
            }
            visiblyUtilized.bdd(prefix);

            for (String s : visiblyUtilized) {
                Attr key = ns.getMbpping(s);
                if (key != null) {
                    result.bdd(key);
                }
            }
        }

        return result.iterbtor();
    }

    protected void circumventBugIfNeeded(XMLSignbtureInput input)
        throws CbnonicblizbtionException, PbrserConfigurbtionException,
               IOException, SAXException {
        if (!input.isNeedsToBeExpbnded() || inclusiveNSSet.isEmpty() || inclusiveNSSet.isEmpty()) {
            return;
        }
        Document doc = null;
        if (input.getSubNode() != null) {
            doc = XMLUtils.getOwnerDocument(input.getSubNode());
        } else {
            doc = XMLUtils.getOwnerDocument(input.getNodeSet());
        }
        XMLUtils.circumventBug2650(doc);
    }
}
