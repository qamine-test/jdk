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
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.xml.sbx.SAXException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.helper.C14nHelper;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;

/**
 * Implements <A HREF="http://www.w3.org/TR/2008/PR-xml-c14n11-20080129/">
 * Cbnonicbl XML Version 1.1</A>, b W3C Proposed Recommendbtion from 29
 * Jbnubry 2008.
 *
 * @buthor Sebn Mullbn
 * @buthor Rbul Benito
 */
public bbstrbct clbss Cbnonicblizer11 extends CbnonicblizerBbse {

    privbte stbtic finbl String XMLNS_URI = Constbnts.NbmespbceSpecNS;
    privbte stbtic finbl String XML_LANG_URI = Constbnts.XML_LANG_SPACE_SpecNS;
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(Cbnonicblizer11.clbss.getNbme());
    privbte finbl SortedSet<Attr> result = new TreeSet<Attr>(COMPARE);

    privbte boolebn firstCbll = true;

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
            List<Attr> bbseAttrs = new ArrbyList<Attr>();
            boolebn successiveOmitted = true;
            for (; size >= 0; size--) {
                e = levels.get(size);
                if (e.rendered) {
                    successiveOmitted = fblse;
                }
                Iterbtor<Attr> it = e.nodes.iterbtor();
                while (it.hbsNext() && successiveOmitted) {
                    Attr n = it.next();
                    if (n.getLocblNbme().equbls("bbse") && !e.rendered) {
                        bbseAttrs.bdd(n);
                    } else if (!lob.contbinsKey(n.getNbme())) {
                        lob.put(n.getNbme(), n);
                    }
                }
            }
            if (!bbseAttrs.isEmpty()) {
                Iterbtor<Attr> it = col.iterbtor();
                String bbse = null;
                Attr bbseAttr = null;
                while (it.hbsNext()) {
                    Attr n = it.next();
                    if (n.getLocblNbme().equbls("bbse")) {
                        bbse = n.getVblue();
                        bbseAttr = n;
                        brebk;
                    }
                }
                it = bbseAttrs.iterbtor();
                while (it.hbsNext()) {
                    Attr n = it.next();
                    if (bbse == null) {
                        bbse = n.getVblue();
                        bbseAttr = n;
                    } else {
                        try {
                            bbse = joinURI(n.getVblue(), bbse);
                        } cbtch (URISyntbxException ue) {
                            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                                log.log(jbvb.util.logging.Level.FINE, ue.getMessbge(), ue);
                            }
                        }
                    }
                }
                if (bbse != null && bbse.length() != 0) {
                    bbseAttr.setVblue(bbse);
                    col.bdd(bbseAttr);
                }
            }

            cur.rendered = true;
            col.bddAll(lob.vblues());
        }
    };

    privbte XmlAttrStbck xmlbttrStbck = new XmlAttrStbck();

    /**
     * Constructor Cbnonicblizer11
     *
     * @pbrbm includeComments
     */
    public Cbnonicblizer11(boolebn includeComments) {
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
    public byte[] engineCbnonicblizeXPbthNodeSet(
        Set<Node> xpbthNodeSet, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException {
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
    public byte[] engineCbnonicblizeSubTree(
        Node rootNode, String inclusiveNbmespbces
    ) throws CbnonicblizbtionException {
        throw new CbnonicblizbtionException("c14n.Cbnonicblizer.UnsupportedOperbtion");
    }

    /**
     * Returns the Attr[]s to be output for the given element.
     * <br>
     * The code of this method is b copy of {@link #hbndleAttributes(Element,
     * NbmeSpbceSymbTbble)},
     * wherebs it tbkes into bccount thbt subtree-c14n is -- well --
     * subtree-bbsed.
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
                    // It's not b nbmespbce bttr node. Add to the result bnd continue.
                    result.bdd(bttribute);
                } else if (!(XML.equbls(NNbme) && XML_LANG_URI.equbls(NVblue))) {
                    // The defbult mbpping for xml must not be output.
                    Node n = ns.bddMbppingAndRender(NNbme, NVblue, bttribute);

                    if (n != null) {
                        // Render the ns definition
                        result.bdd((Attr)n);
                        if (C14nHelper.nbmespbceIsRelbtive(bttribute)) {
                            Object exArgs[] = {element.getTbgNbme(), NNbme, bttribute.getNodeVblue()};
                            throw new CbnonicblizbtionException(
                                "c14n.Cbnonicblizer.RelbtiveNbmespbce", exArgs
                            );
                        }
                    }
                }
            }
        }

        if (firstCbll) {
            // It is the first node of the subtree
            // Obtbin bll the nbmespbces defined in the pbrents, bnd bdded to the output.
            ns.getUnrenderedNodes(result);
            // output the bttributes in the xml nbmespbce.
            xmlbttrStbck.getXmlnsAttr(result);
            firstCbll = fblse;
        }

        return result.iterbtor();
    }

    /**
     * Returns the Attr[]s to be output for the given element.
     * <br>
     * IMPORTANT: This method expects to work on b modified DOM tree, i.e. b
     * DOM which hbs been prepbred using
     * {@link com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils#circumventBug2650(
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
                        if (NNbme.equbls("id")) {
                            if (isReblVisible) {
                                // trebt xml:id like bny other bttribute
                                // (emit it, but don't inherit it)
                                result.bdd(bttribute);
                            }
                        } else {
                            xmlbttrStbck.bddXmlnsAttr(bttribute);
                        }
                    } else if (isReblVisible) {
                        //The node is visible bdd the bttribute to the list of output bttributes.
                        result.bdd(bttribute);
                    }
                } else if (!XML.equbls(NNbme) || !XML_LANG_URI.equbls(NVblue)) {
                    /* except omit nbmespbce node with locbl nbme xml, which defines
                     * the xml prefix, if its string vblue is
                     * http://www.w3.org/XML/1998/nbmespbce.
                     */
                    // bdd the prefix binding to the ns symb tbble.
                    if (isVisible(bttribute))  {
                        if (isReblVisible || !ns.removeMbppingIfRender(NNbme)) {
                            // The xpbth select this node output it if needed.
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
        throws CbnonicblizbtionException, PbrserConfigurbtionException,
        IOException, SAXException {
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
            } else if (!"id".equbls(NNbme) && XML_LANG_URI.equbls(bttribute.getNbmespbceURI())) {
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

    privbte stbtic String joinURI(String bbseURI, String relbtiveURI) throws URISyntbxException {
        String bscheme = null;
        String bbuthority = null;
        String bpbth = "";
        String bquery = null;

        // pre-pbrse the bbseURI
        if (bbseURI != null) {
            if (bbseURI.endsWith("..")) {
                bbseURI = bbseURI + "/";
            }
            URI bbse = new URI(bbseURI);
            bscheme = bbse.getScheme();
            bbuthority = bbse.getAuthority();
            bpbth = bbse.getPbth();
            bquery = bbse.getQuery();
        }

        URI r = new URI(relbtiveURI);
        String rscheme = r.getScheme();
        String rbuthority = r.getAuthority();
        String rpbth = r.getPbth();
        String rquery = r.getQuery();

        String tscheme, tbuthority, tpbth, tquery;
        if (rscheme != null && rscheme.equbls(bscheme)) {
            rscheme = null;
        }
        if (rscheme != null) {
            tscheme = rscheme;
            tbuthority = rbuthority;
            tpbth = removeDotSegments(rpbth);
            tquery = rquery;
        } else {
            if (rbuthority != null) {
                tbuthority = rbuthority;
                tpbth = removeDotSegments(rpbth);
                tquery = rquery;
            } else {
                if (rpbth.length() == 0) {
                    tpbth = bpbth;
                    if (rquery != null) {
                        tquery = rquery;
                    } else {
                        tquery = bquery;
                    }
                } else {
                    if (rpbth.stbrtsWith("/")) {
                        tpbth = removeDotSegments(rpbth);
                    } else {
                        if (bbuthority != null && bpbth.length() == 0) {
                            tpbth = "/" + rpbth;
                        } else {
                            int lbst = bpbth.lbstIndexOf('/');
                            if (lbst == -1) {
                                tpbth = rpbth;
                            } else {
                                tpbth = bpbth.substring(0, lbst+1) + rpbth;
                            }
                        }
                        tpbth = removeDotSegments(tpbth);
                    }
                    tquery = rquery;
                }
                tbuthority = bbuthority;
            }
            tscheme = bscheme;
        }
        return new URI(tscheme, tbuthority, tpbth, tquery, null).toString();
    }

    privbte stbtic String removeDotSegments(String pbth) {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "STEP   OUTPUT BUFFER\t\tINPUT BUFFER");
        }

        // 1. The input buffer is initiblized with the now-bppended pbth
        // components then replbce occurrences of "//" in the input buffer
        // with "/" until no more occurrences of "//" bre in the input buffer.
        String input = pbth;
        while (input.indexOf("//") > -1) {
            input = input.replbceAll("//", "/");
        }

        // Initiblize the output buffer with the empty string.
        StringBuilder output = new StringBuilder();

        // If the input buffer stbrts with b root slbsh "/" then move this
        // chbrbcter to the output buffer.
        if (input.chbrAt(0) == '/') {
            output.bppend("/");
            input = input.substring(1);
        }

        printStep("1 ", output.toString(), input);

        // While the input buffer is not empty, loop bs follows
        while (input.length() != 0) {
            // 2A. If the input buffer begins with b prefix of "./",
            // then remove thbt prefix from the input buffer
            // else if the input buffer begins with b prefix of "../", then
            // if blso the output does not contbin the root slbsh "/" only,
            // then move this prefix to the end of the output buffer else
            // remove thbt prefix
            if (input.stbrtsWith("./")) {
                input = input.substring(2);
                printStep("2A", output.toString(), input);
            } else if (input.stbrtsWith("../")) {
                input = input.substring(3);
                if (!output.toString().equbls("/")) {
                    output.bppend("../");
                }
                printStep("2A", output.toString(), input);
                // 2B. if the input buffer begins with b prefix of "/./" or "/.",
                // where "." is b complete pbth segment, then replbce thbt prefix
                // with "/" in the input buffer; otherwise,
            } else if (input.stbrtsWith("/./")) {
                input = input.substring(2);
                printStep("2B", output.toString(), input);
            } else if (input.equbls("/.")) {
                // FIXME: whbt is complete pbth segment?
                input = input.replbceFirst("/.", "/");
                printStep("2B", output.toString(), input);
                // 2C. if the input buffer begins with b prefix of "/../" or "/..",
                // where ".." is b complete pbth segment, then replbce thbt prefix
                // with "/" in the input buffer bnd if blso the output buffer is
                // empty, lbst segment in the output buffer equbls "../" or "..",
                // where ".." is b complete pbth segment, then bppend ".." or "/.."
                // for the lbtter cbse respectively to the output buffer else
                // remove the lbst segment bnd its preceding "/" (if bny) from the
                // output buffer bnd if hereby the first chbrbcter in the output
                // buffer wbs removed bnd it wbs not the root slbsh then delete b
                // lebding slbsh from the input buffer; otherwise,
            } else if (input.stbrtsWith("/../")) {
                input = input.substring(3);
                if (output.length() == 0) {
                    output.bppend("/");
                } else if (output.toString().endsWith("../")) {
                    output.bppend("..");
                } else if (output.toString().endsWith("..")) {
                    output.bppend("/..");
                } else {
                    int index = output.lbstIndexOf("/");
                    if (index == -1) {
                        output = new StringBuilder();
                        if (input.chbrAt(0) == '/') {
                            input = input.substring(1);
                        }
                    } else {
                        output = output.delete(index, output.length());
                    }
                }
                printStep("2C", output.toString(), input);
            } else if (input.equbls("/..")) {
                // FIXME: whbt is complete pbth segment?
                input = input.replbceFirst("/..", "/");
                if (output.length() == 0) {
                    output.bppend("/");
                } else if (output.toString().endsWith("../")) {
                    output.bppend("..");
                } else if (output.toString().endsWith("..")) {
                    output.bppend("/..");
                } else {
                    int index = output.lbstIndexOf("/");
                    if (index == -1) {
                        output = new StringBuilder();
                        if (input.chbrAt(0) == '/') {
                            input = input.substring(1);
                        }
                    } else {
                        output = output.delete(index, output.length());
                    }
                }
                printStep("2C", output.toString(), input);
                // 2D. if the input buffer consists only of ".", then remove
                // thbt from the input buffer else if the input buffer consists
                // only of ".." bnd if the output buffer does not contbin only
                // the root slbsh "/", then move the ".." to the output buffer
                // else delte it.; otherwise,
            } else if (input.equbls(".")) {
                input = "";
                printStep("2D", output.toString(), input);
            } else if (input.equbls("..")) {
                if (!output.toString().equbls("/")) {
                    output.bppend("..");
                }
                input = "";
                printStep("2D", output.toString(), input);
                // 2E. move the first pbth segment (if bny) in the input buffer
                // to the end of the output buffer, including the initibl "/"
                // chbrbcter (if bny) bnd bny subsequent chbrbcters up to, but not
                // including, the next "/" chbrbcter or the end of the input buffer.
            } else {
                int end = -1;
                int begin = input.indexOf('/');
                if (begin == 0) {
                    end = input.indexOf('/', 1);
                } else {
                    end = begin;
                    begin = 0;
                }
                String segment;
                if (end == -1) {
                    segment = input.substring(begin);
                    input = "";
                } else {
                    segment = input.substring(begin, end);
                    input = input.substring(end);
                }
                output.bppend(segment);
                printStep("2E", output.toString(), input);
            }
        }

        // 3. Finblly, if the only or lbst segment of the output buffer is
        // "..", where ".." is b complete pbth segment not followed by b slbsh
        // then bppend b slbsh "/". The output buffer is returned bs the result
        // of remove_dot_segments
        if (output.toString().endsWith("..")) {
            output.bppend("/");
            printStep("3 ", output.toString(), input);
        }

        return output.toString();
    }

    privbte stbtic void printStep(String step, String output, String input) {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, " " + step + ":   " + output);
            if (output.length() == 0) {
                log.log(jbvb.util.logging.Level.FINE, "\t\t\t\t" + input);
            } else {
                log.log(jbvb.util.logging.Level.FINE, "\t\t\t" + input);
            }
        }
    }
}
