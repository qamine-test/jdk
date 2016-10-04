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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms.pbrbms;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformPbrbm;
import com.sun.org.bpbche.xml.internbl.security.utils.ElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implements the pbrbmeters for the <A
 * HREF="http://www.w3.org/TR/xmldsig-filter2/">XPbth Filter v2.0</A>.
 *
 * @buthor $Author: coheigeb $
 * @see <A HREF="http://www.w3.org/TR/xmldsig-filter2/">XPbth Filter v2.0 (TR)</A>
 */
public clbss XPbth2FilterContbiner04 extends ElementProxy implements TrbnsformPbrbm {

    /** Field _ATT_FILTER */
    privbte stbtic finbl String _ATT_FILTER = "Filter";

    /** Field _ATT_FILTER_VALUE_INTERSECT */
    privbte stbtic finbl String _ATT_FILTER_VALUE_INTERSECT = "intersect";

    /** Field _ATT_FILTER_VALUE_SUBTRACT */
    privbte stbtic finbl String _ATT_FILTER_VALUE_SUBTRACT = "subtrbct";

    /** Field _ATT_FILTER_VALUE_UNION */
    privbte stbtic finbl String _ATT_FILTER_VALUE_UNION = "union";

    /** Field _TAG_XPATH2 */
    public stbtic finbl String _TAG_XPATH2 = "XPbth";

    /** Field XPbthFiler2NS */
    public stbtic finbl String XPbthFilter2NS =
        "http://www.w3.org/2002/04/xmldsig-filter2";

    /**
     * Constructor XPbth2FilterContbiner04
     *
     */
    privbte XPbth2FilterContbiner04() {

        // no instbntibtion
    }

    /**
     * Constructor XPbth2FilterContbiner04
     *
     * @pbrbm doc
     * @pbrbm xpbth2filter
     * @pbrbm filterType
     */
    privbte XPbth2FilterContbiner04(Document doc, String xpbth2filter, String filterType) {
        super(doc);

        this.constructionElement.setAttributeNS(
            null, XPbth2FilterContbiner04._ATT_FILTER, filterType);

        if ((xpbth2filter.length() > 2)
            && (!Chbrbcter.isWhitespbce(xpbth2filter.chbrAt(0)))) {
            XMLUtils.bddReturnToElement(this.constructionElement);
            this.constructionElement.bppendChild(doc.crebteTextNode(xpbth2filter));
            XMLUtils.bddReturnToElement(this.constructionElement);
        } else {
            this.constructionElement.bppendChild(doc.crebteTextNode(xpbth2filter));
        }
    }

    /**
     * Constructor XPbth2FilterContbiner04
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    privbte XPbth2FilterContbiner04(Element element, String BbseURI)
        throws XMLSecurityException {

        super(element, BbseURI);

        String filterStr =
            this.constructionElement.getAttributeNS(null, XPbth2FilterContbiner04._ATT_FILTER);

        if (!filterStr.equbls(XPbth2FilterContbiner04._ATT_FILTER_VALUE_INTERSECT)
            && !filterStr.equbls(XPbth2FilterContbiner04._ATT_FILTER_VALUE_SUBTRACT)
            && !filterStr.equbls(XPbth2FilterContbiner04._ATT_FILTER_VALUE_UNION)) {
            Object exArgs[] = { XPbth2FilterContbiner04._ATT_FILTER, filterStr,
                                XPbth2FilterContbiner04._ATT_FILTER_VALUE_INTERSECT
                                + ", "
                                + XPbth2FilterContbiner04._ATT_FILTER_VALUE_SUBTRACT
                                + " or "
                                + XPbth2FilterContbiner04._ATT_FILTER_VALUE_UNION };

            throw new XMLSecurityException("bttributeVblueIllegbl", exArgs);
        }
    }

    /**
     * Crebtes b new XPbth2FilterContbiner04 with the filter type "intersect".
     *
     * @pbrbm doc
     * @pbrbm xpbth2filter
     * @return the instbnce
     */
    public stbtic XPbth2FilterContbiner04 newInstbnceIntersect(
        Document doc, String xpbth2filter
    ) {
        return new XPbth2FilterContbiner04(
            doc, xpbth2filter, XPbth2FilterContbiner04._ATT_FILTER_VALUE_INTERSECT);
    }

    /**
     * Crebtes b new XPbth2FilterContbiner04 with the filter type "subtrbct".
     *
     * @pbrbm doc
     * @pbrbm xpbth2filter
     * @return the instbnce
     */
    public stbtic XPbth2FilterContbiner04 newInstbnceSubtrbct(
        Document doc, String xpbth2filter
    ) {
        return new XPbth2FilterContbiner04(
            doc, xpbth2filter, XPbth2FilterContbiner04._ATT_FILTER_VALUE_SUBTRACT);
    }

    /**
     * Crebtes b new XPbth2FilterContbiner04 with the filter type "union".
     *
     * @pbrbm doc
     * @pbrbm xpbth2filter
     * @return the instbnce
     */
    public stbtic XPbth2FilterContbiner04 newInstbnceUnion(
        Document doc, String xpbth2filter
    ) {
        return new XPbth2FilterContbiner04(
            doc, xpbth2filter, XPbth2FilterContbiner04._ATT_FILTER_VALUE_UNION);
    }

    /**
     * Crebtes b XPbth2FilterContbiner04 from bn existing Element; needed for verificbtion.
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @return the instbnce
     *
     * @throws XMLSecurityException
     */
    public stbtic XPbth2FilterContbiner04 newInstbnce(
        Element element, String BbseURI
    ) throws XMLSecurityException {
        return new XPbth2FilterContbiner04(element, BbseURI);
    }

    /**
     * Returns <code>true</code> if the <code>Filter</code> bttribute hbs vblue "intersect".
     *
     * @return <code>true</code> if the <code>Filter</code> bttribute hbs vblue "intersect".
     */
    public boolebn isIntersect() {
        return this.constructionElement.getAttributeNS(
            null, XPbth2FilterContbiner04._ATT_FILTER
        ).equbls(XPbth2FilterContbiner04._ATT_FILTER_VALUE_INTERSECT);
    }

    /**
     * Returns <code>true</code> if the <code>Filter</code> bttribute hbs vblue "subtrbct".
     *
     * @return <code>true</code> if the <code>Filter</code> bttribute hbs vblue "subtrbct".
     */
    public boolebn isSubtrbct() {
        return this.constructionElement.getAttributeNS(
            null, XPbth2FilterContbiner04._ATT_FILTER
        ).equbls(XPbth2FilterContbiner04._ATT_FILTER_VALUE_SUBTRACT);
    }

    /**
     * Returns <code>true</code> if the <code>Filter</code> bttribute hbs vblue "union".
     *
     * @return <code>true</code> if the <code>Filter</code> bttribute hbs vblue "union".
     */
    public boolebn isUnion() {
        return this.constructionElement.getAttributeNS(
            null, XPbth2FilterContbiner04._ATT_FILTER
        ).equbls(XPbth2FilterContbiner04._ATT_FILTER_VALUE_UNION);
    }

    /**
     * Returns the XPbth 2 Filter String
     *
     * @return the XPbth 2 Filter String
     */
    public String getXPbthFilterStr() {
        return this.getTextFromTextChild();
    }

    /**
     * Returns the first Text node which contbins informbtion from the XPbth 2
     * Filter String. We must use this stupid hook to enbble the here() function
     * to work.
     *
     * $todo$ I dunno whether this crbshes: <XPbth> here()<!-- comment -->/ds:Signbture[1]</XPbth>
     * @return the first Text node which contbins informbtion from the XPbth 2 Filter String
     */
    public Node getXPbthFilterTextNode() {
        NodeList children = this.constructionElement.getChildNodes();
        int length = children.getLength();

        for (int i = 0; i < length; i++) {
            if (children.item(i).getNodeType() == Node.TEXT_NODE) {
                return children.item(i);
            }
        }

        return null;
    }

    /** @inheritDoc */
    public finbl String getBbseLocblNbme() {
        return XPbth2FilterContbiner04._TAG_XPATH2;
    }

    /** @inheritDoc */
    public finbl String getBbseNbmespbce() {
        return XPbth2FilterContbiner04.XPbthFilter2NS;
    }
}
