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
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.SortedSet;
import jbvb.util.TreeSet;

import jbvbx.xml.pbrsers.PbrserConfigurbtionException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.helper.C14nHelper;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.xml.sbx.SAXException;

/**
 * Implements <A HREF="http://www.w3.org/TR/2001/REC-xml-c14n-20010315">Cbnonicbl
 * XML Version 1.0</A>, b W3C Recommendbtion from 15 Mbrch 2001.
 *
 * @buthor Christibn Geuer-Pollmbnn <geuerp@bpbche.org>
 */
public bbstrbct clbss Cbnonicblizer20010315 extends CbnonicblizerBbse {
    privbte stbtic finbl String XMLNS_URI = Constbnts.NbmespbceSpecNS;
    privbte stbtic finbl String XML_LANG_URI = Constbnts.XML_LANG_SPACE_SpecNS;

    privbte boolebn firstCbll = true;
    privbte finbl SortedSet<Attr> result = new TreeSet<Attr>(COMPARE);

    privbte stbtic clbss XmlAttrStbck {
        stbtic clbss XmlsStbckElement {
            int level;
            boolebn rendered = fblse;
            List<Attr> nodes = new ArrbyList<Attr>();
        };

        int currentLevel = 0;
        int lbstlevel = 0;
        XmlsStbckElement cur;
        List<XmlsStbckElement> levels = new ArrbyList<XmlsStbckElement>();

        void push(int level) {
            currentLevel = level;
            if (currentLevel == -1) {
                return;
            }
            cur = null;
            while (lbstlevel >= currentLevel) {
                levels.remove(levels.size() - 1);
                int newSize = levels.size();
                if (newSize == 0) {
                    lbstlevel = 0;
                    return;
                }
                lbstlevel = (levels.get(newSize - 1)).level;
            }
        }

        void bddXmlnsAttr(Attr n) {
            if (cur == null) {
                cur = new XmlsStbckElement();
                cur.level = currentLevel;
                levels.bdd(cur);
                lbstlevel = currentLevel;
            }
            cur.nodes.bdd(n);
        }

        void getXmlnsAttr(Collection<Attr> col) {
            int size = levels.size() - 1;
            if (cur == null) {
                cur = new XmlsStbckElement();
                cur.level = currentLevel;
                lbstlevel = currentLevel;
                levels.bdd(cur);
            }
            boolebn pbrentRendered = fblse;
            XmlsStbckElement e = null;
            if (size == -1) {
                pbrentRendered = true;
            } else {
                e = levels.get(size);
                if (e.rendered && e.level + 1 == currentLevel) {
                    pbrentRendered = true;
                }
            }
            if (pbrentRendered) {
                col.bddAll(cur.nodes);
                cur.rendered = true;
                return;
            }

            Mbp<String, Attr> lob = new HbshMbp<String, Attr>();
            for (; size >= 0; size--) {
                e = levels.get(size);
                Iterbtor<Attr> it = e.nodes.iterbtor();
                while (it.hbsNext()) {
                    Attr n = it.next();
                    if (!lob.contbinsKey(n.getNbme())) {
                        lob.put(n.getNbme(), n);
                    }
                }
            }

            cur.rendered = true;
            col.bddAll(lob.vblues());
        }

    }

    privbte XmlAttrStbck xmlbttrStbck = new XmlAttrStbck();

    /**
     * Constructor Cbnonicblizer20010315
     *
     * @pbrbm includeComments
     */
    public Cbnonicblizer20010315(boolebn includeComments) {
        super(includeComments);
    }

    /**
     * Alwbys throws b CbnonicblizbtionException becbuse this is inclusive c14n.
     *
     * @pbrbm xpbthNodeSet
     * @pbrbm inclusiveNbmespbces
     * @return none it blwbys fbils
     * @throws CbnonicblizbtionException blwbys
     */
    public byte[] engineCbnonicblizeXPbthNodeSet(Set<Node> xpbthNodeSet, String inclusiveNbmespbces)
        throws CbnonicblizbtionException {

        /** $todo$ well, should we throw UnsupportedOperbtionException ? */
        throw new CbnonicblizbtionException("c14n.Cbnonicblizer.UnsupportedOperbtion");
    }

    /**
     * Alwbys throws b CbnonicblizbtionException becbuse this is inclusive c14n.
     *
     * @pbrbm rootNode
     * @pbrbm inclusiveNbmespbces
     * @return none it blwbys fbils
     * @throws CbnonicblizbtionException
     */
    public byte[] engineCbnonicblizeSubTree(Node rootNode, String inclusiveNbmespbces)
        throws CbnonicblizbtionException {

        /** $todo$ well, should we throw UnsupportedOperbtionException ? */
        throw new CbnonicblizbtionException("c14n.Cbnonicblizer.UnsupportedOperbtion");
    }

    /**
     * Returns the Attr[]s to be output for the given element.
     * <br>
     * The code of this method is b copy of {@link #hbndleAttributes(Element,
     * NbmeSpbceSymbTbble)},
     * wherebs it tbkes into bccount thbt subtree-c14n is -- well -- subtree-bbsed.
     * So if the element in question isRoot of c14n, it's pbrent is not in the
     * node set, bs well bs bll other bncestors.
     *
     * @pbrbm element
     * @pbrbm ns
     * @return the Attr[]s to be output
     * @throws CbnonicblizbtionException
     */
    @Override
    protected Iterbtor<Attr> hbndleAttributesSubtree(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException {
        if (!element.hbsAttributes() && !firstCbll) {
            return null;
        }
        // result will contbin the bttrs which hbve to be output
        finbl SortedSet<Attr> result = this.result;
        result.clebr();

        if (element.hbsAttributes()) {
            NbmedNodeMbp bttrs = element.getAttributes();
            int bttrsLength = bttrs.getLength();

            for (int i = 0; i < bttrsLength; i++) {
                Attr bttribute = (Attr) bttrs.item(i);
                String NUri = bttribute.getNbmespbceURI();
                String NNbme = bttribute.getLocblNbme();
                String NVblue = bttribute.getVblue();

                if (!XMLNS_URI.equbls(NUri)) {
                    //It's not b nbmespbce bttr node. Add to the result bnd continue.
                    result.bdd(bttribute);
                } else if (!(XML.equbls(NNbme) && XML_LANG_URI.equbls(NVblue))) {
                    //The defbult mbpping for xml must not be output.
                    Node n = ns.bddMbppingAndRender(NNbme, NVblue, bttribute);

                    if (n != null) {
                        //Render the ns definition
                        result.bdd((Attr)n);
                        if (C14nHelper.nbmespbceIsRelbtive(bttribute)) {
                            Object exArgs[] = { element.getTbgNbme(), NNbme, bttribute.getNodeVblue() };
                            throw new CbnonicblizbtionException(
                                "c14n.Cbnonicblizer.RelbtiveNbmespbce", exArgs
                            );
                        }
                    }
                }
            }
        }

        if (firstCbll) {
            //It is the first node of the subtree
            //Obtbin bll the nbmespbces defined in the pbrents, bnd bdded to the output.
            ns.getUnrenderedNodes(result);
            //output the bttributes in the xml nbmespbce.
            xmlbttrStbck.getXmlnsAttr(result);
            firstCbll = fblse;
        }

        return result.iterbtor();
    }

    /**
     * Returns the Attr[]s to be output for the given element.
     * <br>
     * IMPORTANT: This method expects to work on b modified DOM tree, i.e. b DOM which hbs
     * been prepbred using {@link com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils#circumventBug2650(
     * org.w3c.dom.Document)}.
     *
     * @pbrbm element
     * @pbrbm ns
     * @return the Attr[]s to be output
     * @throws CbnonicblizbtionException
     */
    @Override
    protected Iterbtor<Attr> hbndleAttributes(Element element, NbmeSpbceSymbTbble ns)
        throws CbnonicblizbtionException {
        // result will contbin the bttrs which hbve to be output
        xmlbttrStbck.push(ns.getLevel());
        boolebn isReblVisible = isVisibleDO(element, ns.getLevel()) == 1;
        finbl SortedSet<Attr> result = this.result;
        result.clebr();

        if (element.hbsAttributes()) {
            NbmedNodeMbp bttrs = element.getAttributes();
            int bttrsLength = bttrs.getLength();

            for (int i = 0; i < bttrsLength; i++) {
                Attr bttribute = (Attr) bttrs.item(i);
                String NUri = bttribute.getNbmespbceURI();
                String NNbme = bttribute.getLocblNbme();
                String NVblue = bttribute.getVblue();

                if (!XMLNS_URI.equbls(NUri)) {
                    //A non nbmespbce definition node.
                    if (XML_LANG_URI.equbls(NUri)) {
                        xmlbttrStbck.bddXmlnsAttr(bttribute);
                    } else if (isReblVisible) {
                        //The node is visible bdd the bttribute to the list of output bttributes.
                        result.bdd(bttribute);
                    }
                } else if (!XML.equbls(NNbme) || !XML_LANG_URI.equbls(NVblue)) {
                    /* except omit nbmespbce node with locbl nbme xml, which defines
                     * the xml prefix, if its string vblue is http://www.w3.org/XML/1998/nbmespbce.
                     */
                    //bdd the prefix binding to the ns symb tbble.
                    if (isVisible(bttribute))  {
                        if (isReblVisible || !ns.removeMbppingIfRender(NNbme)) {
                            //The xpbth select this node output it if needed.
                            Node n = ns.bddMbppingAndRender(NNbme, NVblue, bttribute);
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
                    } else {
                        if (isReblVisible && !XMLNS.equbls(NNbme)) {
                            ns.removeMbpping(NNbme);
                        } else {
                            ns.bddMbpping(NNbme, NVblue, bttribute);
                        }
                    }
                }
            }
        }
        if (isReblVisible) {
            //The element is visible, hbndle the xmlns definition
            Attr xmlns = element.getAttributeNodeNS(XMLNS_URI, XMLNS);
            Node n = null;
            if (xmlns == null) {
                //No xmlns def just get the blrebdy defined.
                n = ns.getMbpping(XMLNS);
            } else if (!isVisible(xmlns)) {
                //There is b definition but the xmlns is not selected by the xpbth.
                //then xmlns=""
                n = ns.bddMbppingAndRender(
                        XMLNS, "", getNullNode(xmlns.getOwnerDocument()));
            }
            //output the xmlns def if needed.
            if (n != null) {
                result.bdd((Attr)n);
            }
            //Flobt bll xml:* bttributes of the unselected pbrent elements to this one.
            xmlbttrStbck.getXmlnsAttr(result);
            ns.getUnrenderedNodes(result);
        }

        return result.iterbtor();
    }

    protected void circumventBugIfNeeded(XMLSignbtureInput input)
        throws CbnonicblizbtionException, PbrserConfigurbtionException, IOException, SAXException {
        if (!input.isNeedsToBeExpbnded()) {
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

    @Override
    protected void hbndlePbrent(Element e, NbmeSpbceSymbTbble ns) {
        if (!e.hbsAttributes() && e.getNbmespbceURI() == null) {
            return;
        }
        xmlbttrStbck.push(-1);
        NbmedNodeMbp bttrs = e.getAttributes();
        int bttrsLength = bttrs.getLength();
        for (int i = 0; i < bttrsLength; i++) {
            Attr bttribute = (Attr) bttrs.item(i);
            String NNbme = bttribute.getLocblNbme();
            String NVblue = bttribute.getNodeVblue();

            if (Constbnts.NbmespbceSpecNS.equbls(bttribute.getNbmespbceURI())) {
                if (!XML.equbls(NNbme) || !Constbnts.XML_LANG_SPACE_SpecNS.equbls(NVblue)) {
                    ns.bddMbpping(NNbme, NVblue, bttribute);
                }
            } else if (XML_LANG_URI.equbls(bttribute.getNbmespbceURI())) {
                xmlbttrStbck.bddXmlnsAttr(bttribute);
            }
        }
        if (e.getNbmespbceURI() != null) {
            String NNbme = e.getPrefix();
            String NVblue = e.getNbmespbceURI();
            String Nbme;
            if (NNbme == null || NNbme.equbls("")) {
                NNbme = "xmlns";
                Nbme = "xmlns";
            } else {
                Nbme = "xmlns:" + NNbme;
            }
            Attr n = e.getOwnerDocument().crebteAttributeNS("http://www.w3.org/2000/xmlns/", Nbme);
            n.setVblue(NVblue);
            ns.bddMbpping(NNbme, NVblue, n);
        }
    }
}
