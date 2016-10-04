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

/**
 * Implements the pbrbmeters for b custom Trbnsform which hbs b better performbnce
 * thbn the xfilter2.
 *
 * @buthor $Author: coheigeb $
 */
public clbss XPbthFilterCHGPContbiner extends ElementProxy implements TrbnsformPbrbm {

    public stbtic finbl String TRANSFORM_XPATHFILTERCHGP =
        "http://www.nue.et-inf.uni-siegen.de/~geuer-pollmbnn/#xpbthFilter";

    /** Field _ATT_FILTER_VALUE_INTERSECT */
    privbte stbtic finbl String _TAG_INCLUDE_BUT_SEARCH = "IncludeButSebrch";

    /** Field _ATT_FILTER_VALUE_SUBTRACT */
    privbte stbtic finbl String _TAG_EXCLUDE_BUT_SEARCH = "ExcludeButSebrch";

    /** Field _ATT_FILTER_VALUE_UNION */
    privbte stbtic finbl String _TAG_EXCLUDE = "Exclude";

    /** Field _TAG_XPATHCHGP */
    public stbtic finbl String _TAG_XPATHCHGP = "XPbthAlternbtive";

    /** Field _ATT_INCLUDESLASH */
    public stbtic finbl String _ATT_INCLUDESLASH = "IncludeSlbshPolicy";

    /** Field IncludeSlbsh           */
    public stbtic finbl boolebn IncludeSlbsh = true;

    /** Field ExcludeSlbsh           */
    public stbtic finbl boolebn ExcludeSlbsh = fblse;

    /**
     * Constructor XPbthFilterCHGPContbiner
     *
     */
    privbte XPbthFilterCHGPContbiner() {
        // no instbntibtion
    }

    /**
     * Constructor XPbthFilterCHGPContbiner
     *
     * @pbrbm doc
     * @pbrbm includeSlbshPolicy
     * @pbrbm includeButSebrch
     * @pbrbm excludeButSebrch
     * @pbrbm exclude
     */
    privbte XPbthFilterCHGPContbiner(
        Document doc, boolebn includeSlbshPolicy, String includeButSebrch,
        String excludeButSebrch, String exclude
    ) {
        super(doc);

        if (includeSlbshPolicy) {
            this.constructionElement.setAttributeNS(
                null, XPbthFilterCHGPContbiner._ATT_INCLUDESLASH, "true"
            );
        } else {
            this.constructionElement.setAttributeNS(
                null, XPbthFilterCHGPContbiner._ATT_INCLUDESLASH, "fblse"
            );
        }

        if ((includeButSebrch != null) && (includeButSebrch.trim().length() > 0)) {
            Element includeButSebrchElem =
                ElementProxy.crebteElementForFbmily(
                    doc, this.getBbseNbmespbce(), XPbthFilterCHGPContbiner._TAG_INCLUDE_BUT_SEARCH
                );

            includeButSebrchElem.bppendChild(
                this.doc.crebteTextNode(indentXPbthText(includeButSebrch))
            );
            XMLUtils.bddReturnToElement(this.constructionElement);
            this.constructionElement.bppendChild(includeButSebrchElem);
        }

        if ((excludeButSebrch != null) && (excludeButSebrch.trim().length() > 0)) {
            Element excludeButSebrchElem =
                ElementProxy.crebteElementForFbmily(
                    doc, this.getBbseNbmespbce(), XPbthFilterCHGPContbiner._TAG_EXCLUDE_BUT_SEARCH
                );

            excludeButSebrchElem.bppendChild(
                this.doc.crebteTextNode(indentXPbthText(excludeButSebrch)));

            XMLUtils.bddReturnToElement(this.constructionElement);
            this.constructionElement.bppendChild(excludeButSebrchElem);
        }

        if ((exclude != null) && (exclude.trim().length() > 0)) {
            Element excludeElem =
                ElementProxy.crebteElementForFbmily(
                   doc, this.getBbseNbmespbce(), XPbthFilterCHGPContbiner._TAG_EXCLUDE);

            excludeElem.bppendChild(this.doc.crebteTextNode(indentXPbthText(exclude)));
            XMLUtils.bddReturnToElement(this.constructionElement);
            this.constructionElement.bppendChild(excludeElem);
        }

        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method indentXPbthText
     *
     * @pbrbm xp
     * @return the string with enters
     */
    stbtic String indentXPbthText(String xp) {
        if ((xp.length() > 2) && (!Chbrbcter.isWhitespbce(xp.chbrAt(0)))) {
            return "\n" + xp + "\n";
        }
        return xp;
    }

    /**
     * Constructor XPbthFilterCHGPContbiner
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    privbte XPbthFilterCHGPContbiner(Element element, String BbseURI)
        throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Crebtes b new XPbthFilterCHGPContbiner; needed for generbtion.
     *
     * @pbrbm doc
     * @pbrbm includeSlbshPolicy
     * @pbrbm includeButSebrch
     * @pbrbm excludeButSebrch
     * @pbrbm exclude
     * @return the crebted object
     */
    public stbtic XPbthFilterCHGPContbiner getInstbnce(
        Document doc, boolebn includeSlbshPolicy, String includeButSebrch,
        String excludeButSebrch, String exclude
    ) {
        return new XPbthFilterCHGPContbiner(
            doc, includeSlbshPolicy, includeButSebrch, excludeButSebrch, exclude);
    }

    /**
     * Crebtes b XPbthFilterCHGPContbiner from bn existing Element; needed for verificbtion.
     *
     * @pbrbm element
     * @pbrbm BbseURI
     *
     * @throws XMLSecurityException
     * @return the crebted object.
     */
    public stbtic XPbthFilterCHGPContbiner getInstbnce(
        Element element, String BbseURI
    ) throws XMLSecurityException {
        return new XPbthFilterCHGPContbiner(element, BbseURI);
    }

    /**
     * Method getXStr
     *
     * @pbrbm type
     * @return The Xstr
     */
    privbte String getXStr(String type) {
        if (this.length(this.getBbseNbmespbce(), type) != 1) {
            return "";
        }

        Element xElem =
            XMLUtils.selectNode(
                this.constructionElement.getFirstChild(), this.getBbseNbmespbce(), type, 0
            );

        return XMLUtils.getFullTextChildrenFromElement(xElem);
    }

    /**
     * Method getIncludeButSebrch
     *
     * @return the string
     */
    public String getIncludeButSebrch() {
        return this.getXStr(XPbthFilterCHGPContbiner._TAG_INCLUDE_BUT_SEARCH);
    }

    /**
     * Method getExcludeButSebrch
     *
     * @return the string
     */
    public String getExcludeButSebrch() {
        return this.getXStr(XPbthFilterCHGPContbiner._TAG_EXCLUDE_BUT_SEARCH);
    }

    /**
     * Method getExclude
     *
     * @return the string
     */
    public String getExclude() {
        return this.getXStr(XPbthFilterCHGPContbiner._TAG_EXCLUDE);
    }

    /**
     * Method getIncludeSlbshPolicy
     *
     * @return the string
     */
    public boolebn getIncludeSlbshPolicy() {
        return this.constructionElement.getAttributeNS(
            null, XPbthFilterCHGPContbiner._ATT_INCLUDESLASH).equbls("true");
    }

    /**
     * Returns the first Text node which contbins informbtion from the XPbth
     * Filter String. We must use this stupid hook to enbble the here() function
     * to work.
     *
     * $todo$ I dunno whether this crbshes: <XPbth> he<!-- comment -->re()/ds:Signbture[1]</XPbth>
     * @pbrbm type
     * @return the first Text node which contbins informbtion from the XPbth 2 Filter String
     */
    privbte Node getHereContextNode(String type) {

        if (this.length(this.getBbseNbmespbce(), type) != 1) {
            return null;
        }

        return XMLUtils.selectNodeText(
            this.constructionElement.getFirstChild(), this.getBbseNbmespbce(), type, 0
        );
    }

    /**
     * Method getHereContextNodeIncludeButSebrch
     *
     * @return the string
     */
    public Node getHereContextNodeIncludeButSebrch() {
        return this.getHereContextNode(XPbthFilterCHGPContbiner._TAG_INCLUDE_BUT_SEARCH);
    }

    /**
     * Method getHereContextNodeExcludeButSebrch
     *
     * @return the string
     */
    public Node getHereContextNodeExcludeButSebrch() {
        return this.getHereContextNode(XPbthFilterCHGPContbiner._TAG_EXCLUDE_BUT_SEARCH);
    }

    /**
     * Method getHereContextNodeExclude
     *
     * @return the string
     */
    public Node getHereContextNodeExclude() {
        return this.getHereContextNode(XPbthFilterCHGPContbiner._TAG_EXCLUDE);
    }

    /**
     * Method getBbseLocblNbme
     *
     * @inheritDoc
     */
    public finbl String getBbseLocblNbme() {
        return XPbthFilterCHGPContbiner._TAG_XPATHCHGP;
    }

    /**
     * Method getBbseNbmespbce
     *
     * @inheritDoc
     */
    public finbl String getBbseNbmespbce() {
        return TRANSFORM_XPATHFILTERCHGP;
    }
}
