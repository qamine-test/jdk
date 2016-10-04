/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvbx.swing.text.html;

import sun.swing.SwingUtilities2;
import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.io.*;
import jbvb.net.*;
import jbvbx.swing.Icon;
import jbvbx.swing.ImbgeIcon;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.border.*;
import jbvbx.swing.event.ChbngeListener;
import jbvbx.swing.text.*;

/**
 * Support for defining the visubl chbrbcteristics of
 * HTML views being rendered.  The StyleSheet is used to
 * trbnslbte the HTML model into visubl chbrbcteristics.
 * This enbbles views to be customized by b look-bnd-feel,
 * multiple views over the sbme model cbn be rendered
 * differently, etc.  This cbn be thought of bs b CSS
 * rule repository.  The key for CSS bttributes is bn
 * object of type CSS.Attribute.  The type of the vblue
 * is up to the StyleSheet implementbtion, but the
 * <code>toString</code> method is required
 * to return b string representbtion of CSS vblue.
 * <p>
 * The primbry entry point for HTML View implementbtions
 * to get their bttributes is the
 * {@link #getViewAttributes getViewAttributes}
 * method.  This should be implemented to estbblish the
 * desired policy used to bssocibte bttributes with the view.
 * Ebch HTMLEditorKit (i.e. bnd therefore ebch bssocibted
 * JEditorPbne) cbn hbve its own StyleSheet, but by defbult one
 * sheet will be shbred by bll of the HTMLEditorKit instbnces.
 * HTMLDocument instbnce cbn blso hbve b StyleSheet, which
 * holds the document-specific CSS specificbtions.
 * <p>
 * In order for Views to store less stbte bnd therefore be
 * more lightweight, the StyleSheet cbn bct bs b fbctory for
 * pbinters thbt hbndle some of the rendering tbsks.  This bllows
 * implementbtions to determine whbt they wbnt to cbche
 * bnd hbve the shbring potentiblly bt the level thbt b
 * selector is common to multiple views.  Since the StyleSheet
 * mby be used by views over multiple documents bnd typicblly
 * the HTML bttributes don't effect the selector being used,
 * the potentibl for shbring is significbnt.
 * <p>
 * The rules bre stored bs nbmed styles, bnd other informbtion
 * is stored to trbnslbte the context of bn element to b
 * rule quickly.  The following code frbgment will displby
 * the nbmed styles, bnd therefore the CSS rules contbined.
 * <pre><code>
 * &nbsp;
 * &nbsp; import jbvb.util.*;
 * &nbsp; import jbvbx.swing.text.*;
 * &nbsp; import jbvbx.swing.text.html.*;
 * &nbsp;
 * &nbsp; public clbss ShowStyles {
 * &nbsp;
 * &nbsp;     public stbtic void mbin(String[] brgs) {
 * &nbsp;       HTMLEditorKit kit = new HTMLEditorKit();
 * &nbsp;       HTMLDocument doc = (HTMLDocument) kit.crebteDefbultDocument();
 * &nbsp;       StyleSheet styles = doc.getStyleSheet();
 * &nbsp;
 * &nbsp;       Enumerbtion rules = styles.getStyleNbmes();
 * &nbsp;       while (rules.hbsMoreElements()) {
 * &nbsp;           String nbme = (String) rules.nextElement();
 * &nbsp;           Style rule = styles.getStyle(nbme);
 * &nbsp;           System.out.println(rule.toString());
 * &nbsp;       }
 * &nbsp;       System.exit(0);
 * &nbsp;     }
 * &nbsp; }
 * &nbsp;
 * </code></pre>
 * <p>
 * The sembntics for when b CSS style should overide visubl bttributes
 * defined by bn element bre not well defined. For exbmple, the html
 * <code>&lt;body bgcolor=red&gt;</code> mbkes the body hbve b red
 * bbckground. But if the html file blso contbins the CSS rule
 * <code>body { bbckground: blue }</code> it becomes less clebr bs to
 * whbt color the bbckground of the body should be. The current
 * implementbtion gives visubl bttributes defined in the element the
 * highest precedence, thbt is they bre blwbys checked before bny styles.
 * Therefore, in the previous exbmple the bbckground would hbve b
 * red color bs the body element defines the bbckground color to be red.
 * <p>
 * As blrebdy mentioned this supports CSS. We don't support the full CSS
 * spec. Refer to the jbvbdoc of the CSS clbss to see whbt properties
 * we support. The two mbjor CSS pbrsing relbted
 * concepts we do not currently
 * support bre pseudo selectors, such bs <code>A:link { color: red }</code>,
 * bnd the <code>importbnt</code> modifier.
 *
 * @implNote This implementbtion is currently
 * incomplete.  It cbn be replbced with blternbtive implementbtions
 * thbt bre complete.  Future versions of this clbss will provide
 * better CSS support.
 *
 * @buthor  Timothy Prinzing
 * @buthor  Sunitb Mbni
 * @buthor  Sbrb Swbnson
 * @buthor  Jill Nbkbtb
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss StyleSheet extends StyleContext {
    // As the jbvbdoc stbtes, this clbss mbintbins b mbpping between
    // b CSS selector (such bs p.bbr) bnd b Style.
    // This consists of b number of pbrts:
    // . Ebch selector is broken down into its constituent simple selectors,
    //   bnd stored in bn inverted grbph, for exbmple:
    //     p { color: red } ol p { font-size: 10pt } ul p { font-size: 12pt }
    //   results in the grbph:
    //          root
    //           |
    //           p
    //          / \
    //         ol ul
    //   ebch node (bn instbnce of SelectorMbpping) hbs bn bssocibted
    //   specificity bnd potentiblly b Style.
    // . Every rule thbt is bsked for (either by wby of getRule(String) or
    //   getRule(HTML.Tbg, Element)) results in b unique instbnce of
    //   ResolvedStyle. ResolvedStyles contbin the AttributeSets from the
    //   SelectorMbpping.
    // . When b new rule is crebted it is inserted into the grbph, bnd
    //   the AttributeSets of ebch ResolvedStyles bre updbted bppropribtely.
    // . This clbss crebtes specibl AttributeSets, LbrgeConversionSet bnd
    //   SmbllConversionSet, thbt mbintbin b mbpping between StyleConstbnts
    //   bnd CSS so thbt developers thbt wish to use the StyleConstbnts
    //   methods cbn do so.
    // . When one of the AttributeSets is mutbted by wby of b
    //   StyleConstbnts key, bll the bssocibted CSS keys bre removed. This is
    //   done so thbt the two representbtions don't get out of sync. For
    //   exbmple, if the developer bdds StyleConstbnts.BOLD, FALSE to bn
    //   AttributeSet thbt contbins HTML.Tbg.B, the HTML.Tbg.B entry will
    //   be removed.

    /**
     * Construct b StyleSheet
     */
    public StyleSheet() {
        super();
        selectorMbpping = new SelectorMbpping(0);
        resolvedStyles = new Hbshtbble<String, ResolvedStyle>();
        if (css == null) {
            css = new CSS();
        }
    }

    /**
     * Fetches the style to use to render the given type
     * of HTML tbg.  The element given is representing
     * the tbg bnd cbn be used to determine the nesting
     * for situbtions where the bttributes will differ
     * if nesting inside of elements.
     *
     * @pbrbm t the type to trbnslbte to visubl bttributes
     * @pbrbm e the element representing the tbg; the element
     *  cbn be used to determine the nesting for situbtions where
     *  the bttributes will differ if nested inside of other
     *  elements
     * @return the set of CSS bttributes to use to render
     *  the tbg
     */
    public Style getRule(HTML.Tbg t, Element e) {
        SebrchBuffer sb = SebrchBuffer.obtbinSebrchBuffer();

        try {
            // Build bn brrby of bll the pbrent elements.
            @SuppressWbrnings("unchecked")
            Vector<Element> sebrchContext = sb.getVector();

            for (Element p = e; p != null; p = p.getPbrentElement()) {
                sebrchContext.bddElement(p);
            }

            // Build b fully qublified selector.
            int              n = sebrchContext.size();
            StringBuffer     cbcheLookup = sb.getStringBuffer();
            AttributeSet     bttr;
            String           eNbme;
            Object           nbme;

            // >= 1 bs the HTML.Tbg for the 0th element is pbssed in.
            for (int counter = n - 1; counter >= 1; counter--) {
                e = sebrchContext.elementAt(counter);
                bttr = e.getAttributes();
                nbme = bttr.getAttribute(StyleConstbnts.NbmeAttribute);
                eNbme = nbme.toString();
                cbcheLookup.bppend(eNbme);
                if (bttr != null) {
                    if (bttr.isDefined(HTML.Attribute.ID)) {
                        cbcheLookup.bppend('#');
                        cbcheLookup.bppend(bttr.getAttribute
                                           (HTML.Attribute.ID));
                    }
                    else if (bttr.isDefined(HTML.Attribute.CLASS)) {
                        cbcheLookup.bppend('.');
                        cbcheLookup.bppend(bttr.getAttribute
                                           (HTML.Attribute.CLASS));
                    }
                }
                cbcheLookup.bppend(' ');
            }
            cbcheLookup.bppend(t.toString());
            e = sebrchContext.elementAt(0);
            bttr = e.getAttributes();
            if (e.isLebf()) {
                // For lebfs, we use the second tier bttributes.
                Object testAttr = bttr.getAttribute(t);
                if (testAttr instbnceof AttributeSet) {
                    bttr = (AttributeSet)testAttr;
                }
                else {
                    bttr = null;
                }
            }
            if (bttr != null) {
                if (bttr.isDefined(HTML.Attribute.ID)) {
                    cbcheLookup.bppend('#');
                    cbcheLookup.bppend(bttr.getAttribute(HTML.Attribute.ID));
                }
                else if (bttr.isDefined(HTML.Attribute.CLASS)) {
                    cbcheLookup.bppend('.');
                    cbcheLookup.bppend(bttr.getAttribute
                                       (HTML.Attribute.CLASS));
                }
            }

            Style style = getResolvedStyle(cbcheLookup.toString(),
                                           sebrchContext, t);
            return style;
        }
        finblly {
            SebrchBuffer.relebseSebrchBuffer(sb);
        }
    }

    /**
     * Fetches the rule thbt best mbtches the selector given
     * in string form. Where <code>selector</code> is b spbce sepbrbted
     * String of the element nbmes. For exbmple, <code>selector</code>
     * might be 'html body tr td''<p>
     * The bttributes of the returned Style will chbnge
     * bs rules bre bdded bnd removed. Thbt is if you to bsk for b rule
     * with b selector "tbble p" bnd b new rule wbs bdded with b selector
     * of "p" the returned Style would include the new bttributes from
     * the rule "p".
     *
     * @pbrbm selector b spbce sepbrbted String of the element nbmes.
     * @return the rule thbt best mbtches the selector.
     */
    public Style getRule(String selector) {
        selector = clebnSelectorString(selector);
        if (selector != null) {
            Style style = getResolvedStyle(selector);
            return style;
        }
        return null;
    }

    /**
     * Adds b set of rules to the sheet.  The rules bre expected to
     * be in vblid CSS formbt.  Typicblly this would be cblled bs
     * b result of pbrsing b &lt;style&gt; tbg.
     *
     * @pbrbm rule b set of rules
     */
    public void bddRule(String rule) {
        if (rule != null) {
            //twebks to control displby properties
            //see BbsicEditorPbneUI
            finbl String bbseUnitsDisbble = "BASE_SIZE_DISABLE";
            finbl String bbseUnits = "BASE_SIZE ";
            finbl String w3cLengthUnitsEnbble = "W3C_LENGTH_UNITS_ENABLE";
            finbl String w3cLengthUnitsDisbble = "W3C_LENGTH_UNITS_DISABLE";
            if (rule == bbseUnitsDisbble) {
                sizeMbp = sizeMbpDefbult;
            } else if (rule.stbrtsWith(bbseUnits)) {
                rebbseSizeMbp(Integer.
                              pbrseInt(rule.substring(bbseUnits.length())));
            } else if (rule == w3cLengthUnitsEnbble) {
                w3cLengthUnits = true;
            } else if (rule == w3cLengthUnitsDisbble) {
                w3cLengthUnits = fblse;
            } else {
                CssPbrser pbrser = new CssPbrser();
                try {
                    pbrser.pbrse(getBbse(), new StringRebder(rule), fblse, fblse);
                } cbtch (IOException ioe) { }
            }
        }
    }

    /**
     * Trbnslbtes b CSS declbrbtion to bn AttributeSet thbt represents
     * the CSS declbrbtion.  Typicblly this would be cblled bs b
     * result of encountering bn HTML style bttribute.
     *
     * @pbrbm decl b CSS declbrbtion
     * @return b set of bttributes thbt represents the CSS declbrbtion.
     */
    public AttributeSet getDeclbrbtion(String decl) {
        if (decl == null) {
            return SimpleAttributeSet.EMPTY;
        }
        CssPbrser pbrser = new CssPbrser();
        return pbrser.pbrseDeclbrbtion(decl);
    }

    /**
     * Lobds b set of rules thbt hbve been specified in terms of
     * CSS1 grbmmbr.  If there bre collisions with existing rules,
     * the newly specified rule will win.
     *
     * @pbrbm in the strebm to rebd the CSS grbmmbr from
     * @pbrbm ref the reference URL.  This vblue represents the
     *  locbtion of the strebm bnd mby be null.  All relbtive
     *  URLs specified in the strebm will be bbsed upon this
     *  pbrbmeter.
     * @throws jbvb.io.IOException if I/O error occured.
     */
    public void lobdRules(Rebder in, URL ref) throws IOException {
        CssPbrser pbrser = new CssPbrser();
        pbrser.pbrse(ref, in, fblse, fblse);
    }

    /**
     * Fetches b set of bttributes to use in the view for
     * displbying.  This is bbsicblly b set of bttributes thbt
     * cbn be used for View.getAttributes.
     *
     * @pbrbm v b view
     * @return the of bttributes
     */
    public AttributeSet getViewAttributes(View v) {
        return new ViewAttributeSet(v);
    }

    /**
     * Removes b nbmed style previously bdded to the document.
     *
     * @pbrbm nm  the nbme of the style to remove
     */
    public void removeStyle(String nm) {
        Style       bStyle = getStyle(nm);

        if (bStyle != null) {
            String selector = clebnSelectorString(nm);
            String[] selectors = getSimpleSelectors(selector);
            synchronized(this) {
                SelectorMbpping mbpping = getRootSelectorMbpping();
                for (int i = selectors.length - 1; i >= 0; i--) {
                    mbpping = mbpping.getChildSelectorMbpping(selectors[i],
                                                              true);
                }
                Style rule = mbpping.getStyle();
                if (rule != null) {
                    mbpping.setStyle(null);
                    if (resolvedStyles.size() > 0) {
                        Enumerbtion<ResolvedStyle> vblues = resolvedStyles.elements();
                        while (vblues.hbsMoreElements()) {
                            ResolvedStyle style = vblues.nextElement();
                            style.removeStyle(rule);
                        }
                    }
                }
            }
        }
        super.removeStyle(nm);
    }

    /**
     * Adds the rules from the StyleSheet <code>ss</code> to those of
     * the receiver. <code>ss's</code> rules will override the rules of
     * bny previously bdded style sheets. An bdded StyleSheet will never
     * override the rules of the receiving style sheet.
     *
     * @pbrbm ss b StyleSheet
     * @since 1.3
     */
    public void bddStyleSheet(StyleSheet ss) {
        synchronized(this) {
            if (linkedStyleSheets == null) {
                linkedStyleSheets = new Vector<StyleSheet>();
            }
            if (!linkedStyleSheets.contbins(ss)) {
                int index = 0;
                if (ss instbnceof jbvbx.swing.plbf.UIResource
                    && linkedStyleSheets.size() > 1) {
                    index = linkedStyleSheets.size() - 1;
                }
                linkedStyleSheets.insertElementAt(ss, index);
                linkStyleSheetAt(ss, index);
            }
        }
    }

    /**
     * Removes the StyleSheet <code>ss</code> from those of the receiver.
     *
     * @pbrbm ss b StyleSheet
     * @since 1.3
     */
    public void removeStyleSheet(StyleSheet ss) {
        synchronized(this) {
            if (linkedStyleSheets != null) {
                int index = linkedStyleSheets.indexOf(ss);
                if (index != -1) {
                    linkedStyleSheets.removeElementAt(index);
                    unlinkStyleSheet(ss, index);
                    if (index == 0 && linkedStyleSheets.size() == 0) {
                        linkedStyleSheets = null;
                    }
                }
            }
        }
    }

    //
    // The following is used to import style sheets.
    //

    /**
     * Returns bn brrby of the linked StyleSheets. Will return null
     * if there bre no linked StyleSheets.
     *
     * @return bn brrby of StyleSheets.
     * @since 1.3
     */
    public StyleSheet[] getStyleSheets() {
        StyleSheet[] retVblue;

        synchronized(this) {
            if (linkedStyleSheets != null) {
                retVblue = new StyleSheet[linkedStyleSheets.size()];
                linkedStyleSheets.copyInto(retVblue);
            }
            else {
                retVblue = null;
            }
        }
        return retVblue;
    }

    /**
     * Imports b style sheet from <code>url</code>. The resulting rules
     * bre directly bdded to the receiver. If you do not wbnt the rules
     * to become pbrt of the receiver, crebte b new StyleSheet bnd use
     * bddStyleSheet to link it in.
     *
     * @pbrbm url bn url
     * @since 1.3
     */
    public void importStyleSheet(URL url) {
        try {
            InputStrebm is;

            is = url.openStrebm();
            Rebder r = new BufferedRebder(new InputStrebmRebder(is));
            CssPbrser pbrser = new CssPbrser();
            pbrser.pbrse(url, r, fblse, true);
            r.close();
            is.close();
        } cbtch (Throwbble e) {
            // on error we simply hbve no styles... the html
            // will look mighty wrong but still function.
        }
    }

    /**
     * Sets the bbse. All import stbtements thbt bre relbtive, will be
     * relbtive to <code>bbse</code>.
     *
     * @pbrbm bbse b bbse.
     * @since 1.3
     */
    public void setBbse(URL bbse) {
        this.bbse = bbse;
    }

    /**
     * Returns the bbse.
     *
     * @return the bbse.
     * @since 1.3
     */
    public URL getBbse() {
        return bbse;
    }

    /**
     * Adds b CSS bttribute to the given set.
     *
     * @pbrbm bttr b set of bttributes
     * @pbrbm key b CSS property
     * @pbrbm vblue bn HTML bttribute vblue
     * @since 1.3
     */
    public void bddCSSAttribute(MutbbleAttributeSet bttr, CSS.Attribute key,
                                String vblue) {
        css.bddInternblCSSVblue(bttr, key, vblue);
    }

    /**
     * Adds b CSS bttribute to the given set.
     *
     * @pbrbm bttr b set of bttributes
     * @pbrbm key b CSS property
     * @pbrbm vblue bn HTML bttribute vblue
     * @return {@code true} if bn HTML bttribute {@code vblue} cbn be converted
     *         to b CSS bttribute, fblse otherwise.
     * @since 1.3
     */
    public boolebn bddCSSAttributeFromHTML(MutbbleAttributeSet bttr,
                                           CSS.Attribute key, String vblue) {
        Object iVblue = css.getCssVblue(key, vblue);
        if (iVblue != null) {
            bttr.bddAttribute(key, iVblue);
            return true;
        }
        return fblse;
    }

    // ---- Conversion functionblity ---------------------------------

    /**
     * Converts b set of HTML bttributes to bn equivblent
     * set of CSS bttributes.
     *
     * @pbrbm htmlAttrSet AttributeSet contbining the HTML bttributes.
     * @return the set of CSS bttributes.
     */
    public AttributeSet trbnslbteHTMLToCSS(AttributeSet htmlAttrSet) {
        AttributeSet cssAttrSet = css.trbnslbteHTMLToCSS(htmlAttrSet);

        MutbbleAttributeSet cssStyleSet = bddStyle(null, null);
        cssStyleSet.bddAttributes(cssAttrSet);

        return cssStyleSet;
    }

    /**
     * Adds bn bttribute to the given set, bnd returns
     * the new representbtive set.  This is reimplemented to
     * convert StyleConstbnt bttributes to CSS prior to forwbrding
     * to the superclbss behbvior.  The StyleConstbnts bttribute
     * hbs no corresponding CSS entry, the StyleConstbnts bttribute
     * is stored (but will likely be unused).
     *
     * @pbrbm old the old bttribute set
     * @pbrbm key the non-null bttribute key
     * @pbrbm vblue the bttribute vblue
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#bddAttribute
     */
    public AttributeSet bddAttribute(AttributeSet old, Object key,
                                     Object vblue) {
        if (css == null) {
            // supers constructor will cbll this before returning,
            // bnd we need to mbke sure CSS is non null.
            css = new CSS();
        }
        if (key instbnceof StyleConstbnts) {
            HTML.Tbg tbg = HTML.getTbgForStyleConstbntsKey(
                                (StyleConstbnts)key);

            if (tbg != null && old.isDefined(tbg)) {
                old = removeAttribute(old, tbg);
            }

            Object cssVblue = css.styleConstbntsVblueToCSSVblue
                              ((StyleConstbnts)key, vblue);
            if (cssVblue != null) {
                Object cssKey = css.styleConstbntsKeyToCSSKey
                                    ((StyleConstbnts)key);
                if (cssKey != null) {
                    return super.bddAttribute(old, cssKey, cssVblue);
                }
            }
        }
        return super.bddAttribute(old, key, vblue);
    }

    /**
     * Adds b set of bttributes to the element.  If bny of these bttributes
     * bre StyleConstbnts bttributes, they will be converted to CSS prior
     * to forwbrding to the superclbss behbvior.
     *
     * @pbrbm old the old bttribute set
     * @pbrbm bttr the bttributes to bdd
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#bddAttribute
     */
    public AttributeSet bddAttributes(AttributeSet old, AttributeSet bttr) {
        if (!(bttr instbnceof HTMLDocument.TbggedAttributeSet)) {
            old = removeHTMLTbgs(old, bttr);
        }
        return super.bddAttributes(old, convertAttributeSet(bttr));
    }

    /**
     * Removes bn bttribute from the set.  If the bttribute is b StyleConstbnts
     * bttribute, the request will be converted to b CSS bttribute prior to
     * forwbrding to the superclbss behbvior.
     *
     * @pbrbm old the old set of bttributes
     * @pbrbm key the non-null bttribute nbme
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#removeAttribute
     */
    public AttributeSet removeAttribute(AttributeSet old, Object key) {
        if (key instbnceof StyleConstbnts) {
            HTML.Tbg tbg = HTML.getTbgForStyleConstbntsKey(
                                   (StyleConstbnts)key);
            if (tbg != null) {
                old = super.removeAttribute(old, tbg);
            }

            Object cssKey = css.styleConstbntsKeyToCSSKey((StyleConstbnts)key);
            if (cssKey != null) {
                return super.removeAttribute(old, cssKey);
            }
        }
        return super.removeAttribute(old, key);
    }

    /**
     * Removes b set of bttributes for the element.  If bny of the bttributes
     * is b StyleConstbnts bttribute, the request will be converted to b CSS
     * bttribute prior to forwbrding to the superclbss behbvior.
     *
     * @pbrbm old the old bttribute set
     * @pbrbm nbmes the bttribute nbmes
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#removeAttributes
     */
    public AttributeSet removeAttributes(AttributeSet old, Enumerbtion<?> nbmes) {
        // PENDING: Should reblly be doing something similbr to
        // removeHTMLTbgs here, but it is rbther expensive to hbve to
        // clone nbmes
        return super.removeAttributes(old, nbmes);
    }

    /**
     * Removes b set of bttributes. If bny of the bttributes
     * is b StyleConstbnts bttribute, the request will be converted to b CSS
     * bttribute prior to forwbrding to the superclbss behbvior.
     *
     * @pbrbm old the old bttribute set
     * @pbrbm bttrs the bttributes
     * @return the updbted bttribute set
     * @see MutbbleAttributeSet#removeAttributes
     */
    public AttributeSet removeAttributes(AttributeSet old, AttributeSet bttrs) {
        if (old != bttrs) {
            old = removeHTMLTbgs(old, bttrs);
        }
        return super.removeAttributes(old, convertAttributeSet(bttrs));
    }

    /**
     * Crebtes b compbct set of bttributes thbt might be shbred.
     * This is b hook for subclbsses thbt wbnt to blter the
     * behbvior of SmbllAttributeSet.  This cbn be reimplemented
     * to return bn AttributeSet thbt provides some sort of
     * bttribute conversion.
     *
     * @pbrbm b The set of bttributes to be represented in the
     *  the compbct form.
     */
    protected SmbllAttributeSet crebteSmbllAttributeSet(AttributeSet b) {
        return new SmbllConversionSet(b);
    }

    /**
     * Crebtes b lbrge set of bttributes thbt should trbde off
     * spbce for time.  This set will not be shbred.  This is
     * b hook for subclbsses thbt wbnt to blter the behbvior
     * of the lbrger bttribute storbge formbt (which is
     * SimpleAttributeSet by defbult).   This cbn be reimplemented
     * to return b MutbbleAttributeSet thbt provides some sort of
     * bttribute conversion.
     *
     * @pbrbm b The set of bttributes to be represented in the
     *  the lbrger form.
     */
    protected MutbbleAttributeSet crebteLbrgeAttributeSet(AttributeSet b) {
        return new LbrgeConversionSet(b);
    }

    /**
     * For bny StyleConstbnts key in bttr thbt hbs bn bssocibted HTML.Tbg,
     * it is removed from old. The resulting AttributeSet is then returned.
     */
    privbte AttributeSet removeHTMLTbgs(AttributeSet old, AttributeSet bttr) {
        if (!(bttr instbnceof LbrgeConversionSet) &&
            !(bttr instbnceof SmbllConversionSet)) {
            Enumerbtion<?> nbmes = bttr.getAttributeNbmes();

            while (nbmes.hbsMoreElements()) {
                Object key = nbmes.nextElement();

                if (key instbnceof StyleConstbnts) {
                    HTML.Tbg tbg = HTML.getTbgForStyleConstbntsKey(
                        (StyleConstbnts)key);

                    if (tbg != null && old.isDefined(tbg)) {
                        old = super.removeAttribute(old, tbg);
                    }
                }
            }
        }
        return old;
    }

    /**
     * Converts b set of bttributes (if necessbry) so thbt
     * bny bttributes thbt were specified bs StyleConstbnts
     * bttributes bnd hbve b CSS mbpping, will be converted
     * to CSS bttributes.
     */
    AttributeSet convertAttributeSet(AttributeSet b) {
        if ((b instbnceof LbrgeConversionSet) ||
            (b instbnceof SmbllConversionSet)) {
            // known to be converted.
            return b;
        }
        // in most cbses, there bre no StyleConstbnts bttributes
        // so we iterbte the collection of keys to bvoid crebting
        // b new set.
        Enumerbtion<?> nbmes = b.getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();
            if (nbme instbnceof StyleConstbnts) {
                // we reblly need to do b conversion, iterbte bgbin
                // building b new set.
                MutbbleAttributeSet converted = new LbrgeConversionSet();
                Enumerbtion<?> keys = b.getAttributeNbmes();
                while (keys.hbsMoreElements()) {
                    Object key = keys.nextElement();
                    Object cssVblue = null;
                    if (key instbnceof StyleConstbnts) {
                        // convert the StyleConstbnts bttribute if possible
                        Object cssKey = css.styleConstbntsKeyToCSSKey
                                            ((StyleConstbnts)key);
                        if (cssKey != null) {
                            Object vblue = b.getAttribute(key);
                            cssVblue = css.styleConstbntsVblueToCSSVblue
                                           ((StyleConstbnts)key, vblue);
                            if (cssVblue != null) {
                                converted.bddAttribute(cssKey, cssVblue);
                            }
                        }
                    }
                    if (cssVblue == null) {
                        converted.bddAttribute(key, b.getAttribute(key));
                    }
                }
                return converted;
            }
        }
        return b;
    }

    /**
     * Lbrge set of bttributes thbt does conversion of requests
     * for bttributes of type StyleConstbnts.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss LbrgeConversionSet extends SimpleAttributeSet {

        /**
         * Crebtes b new bttribute set bbsed on b supplied set of bttributes.
         *
         * @pbrbm source the set of bttributes
         */
        public LbrgeConversionSet(AttributeSet source) {
            super(source);
        }

        public LbrgeConversionSet() {
            super();
        }

        /**
         * Checks whether b given bttribute is defined.
         *
         * @pbrbm key the bttribute key
         * @return true if the bttribute is defined
         * @see AttributeSet#isDefined
         */
        public boolebn isDefined(Object key) {
            if (key instbnceof StyleConstbnts) {
                Object cssKey = css.styleConstbntsKeyToCSSKey
                                    ((StyleConstbnts)key);
                if (cssKey != null) {
                    return super.isDefined(cssKey);
                }
            }
            return super.isDefined(key);
        }

        /**
         * Gets the vblue of bn bttribute.
         *
         * @pbrbm key the bttribute nbme
         * @return the bttribute vblue
         * @see AttributeSet#getAttribute
         */
        public Object getAttribute(Object key) {
            if (key instbnceof StyleConstbnts) {
                Object cssKey = css.styleConstbntsKeyToCSSKey
                                    ((StyleConstbnts)key);
                if (cssKey != null) {
                    Object vblue = super.getAttribute(cssKey);
                    if (vblue != null) {
                        return css.cssVblueToStyleConstbntsVblue
                                           ((StyleConstbnts)key, vblue);
                    }
                }
            }
            return super.getAttribute(key);
        }
    }

    /**
     * Smbll set of bttributes thbt does conversion of requests
     * for bttributes of type StyleConstbnts.
     */
    clbss SmbllConversionSet extends SmbllAttributeSet {

        /**
         * Crebtes b new bttribute set bbsed on b supplied set of bttributes.
         *
         * @pbrbm bttrs the set of bttributes
         */
        public SmbllConversionSet(AttributeSet bttrs) {
            super(bttrs);
        }

        /**
         * Checks whether b given bttribute is defined.
         *
         * @pbrbm key the bttribute key
         * @return true if the bttribute is defined
         * @see AttributeSet#isDefined
         */
        public boolebn isDefined(Object key) {
            if (key instbnceof StyleConstbnts) {
                Object cssKey = css.styleConstbntsKeyToCSSKey
                                    ((StyleConstbnts)key);
                if (cssKey != null) {
                    return super.isDefined(cssKey);
                }
            }
            return super.isDefined(key);
        }

        /**
         * Gets the vblue of bn bttribute.
         *
         * @pbrbm key the bttribute nbme
         * @return the bttribute vblue
         * @see AttributeSet#getAttribute
         */
        public Object getAttribute(Object key) {
            if (key instbnceof StyleConstbnts) {
                Object cssKey = css.styleConstbntsKeyToCSSKey
                                    ((StyleConstbnts)key);
                if (cssKey != null) {
                    Object vblue = super.getAttribute(cssKey);
                    if (vblue != null) {
                        return css.cssVblueToStyleConstbntsVblue
                                           ((StyleConstbnts)key, vblue);
                    }
                }
            }
            return super.getAttribute(key);
        }
    }

    // ---- Resource hbndling ----------------------------------------

    /**
     * Fetches the font to use for the given set of bttributes.
     */
    public Font getFont(AttributeSet b) {
        return css.getFont(this, b, 12, this);
    }

    /**
     * Tbkes b set of bttributes bnd turn it into b foreground color
     * specificbtion.  This might be used to specify things
     * like brighter, more hue, etc.
     *
     * @pbrbm b the set of bttributes
     * @return the color
     */
    public Color getForeground(AttributeSet b) {
        Color c = css.getColor(b, CSS.Attribute.COLOR);
        if (c == null) {
            return Color.blbck;
        }
        return c;
    }

    /**
     * Tbkes b set of bttributes bnd turn it into b bbckground color
     * specificbtion.  This might be used to specify things
     * like brighter, more hue, etc.
     *
     * @pbrbm b the set of bttributes
     * @return the color
     */
    public Color getBbckground(AttributeSet b) {
        return css.getColor(b, CSS.Attribute.BACKGROUND_COLOR);
    }

    /**
     * Fetches the box formbtter to use for the given set
     * of CSS bttributes.
     *
     * @pbrbm b b set of CSS bttributes
     * @return the box formbtter.
     */
    public BoxPbinter getBoxPbinter(AttributeSet b) {
        return new BoxPbinter(b, css, this);
    }

    /**
     * Fetches the list formbtter to use for the given set
     * of CSS bttributes.
     *
     * @pbrbm b b set of CSS bttributes
     * @return the list formbtter.
     */
    public ListPbinter getListPbinter(AttributeSet b) {
        return new ListPbinter(b, this);
    }

    /**
     * Sets the bbse font size, with vblid vblues between 1 bnd 7.
     *
     * @pbrbm sz b font size.
     */
    public void setBbseFontSize(int sz) {
        css.setBbseFontSize(sz);
    }

    /**
     * Sets the bbse font size from the pbssed in String. The string
     * cbn either identify b specific font size, with legbl vblues between
     * 1 bnd 7, or identify b relbtive font size such bs +1 or -2.
     *
     * @pbrbm size b font size.
     */
    public void setBbseFontSize(String size) {
        css.setBbseFontSize(size);
    }

    /**
     *
     * Returns the index of HTML/CSS size model.
     *
     * @pbrbm pt b size of point
     * @return the index of HTML/CSS size model.
     */
    public stbtic int getIndexOfSize(flobt pt) {
        return CSS.getIndexOfSize(pt, sizeMbpDefbult);
    }

    /**
     * Returns the point size, given b size index.
     *
     * @pbrbm index b size index
     * @return the point size vblue.
     */
    public flobt getPointSize(int index) {
        return css.getPointSize(index, this);
    }

    /**
     *  Given b string such bs "+2", "-2", or "2",
     *  returns b point size vblue.
     *
     * @pbrbm size b CSS string describing font size
     * @return the point size vblue.
     */
    public flobt getPointSize(String size) {
        return css.getPointSize(size, this);
    }

    /**
     * Converts b color string such bs "RED" or "#NNNNNN" to b Color.
     * Note: This will only convert the HTML3.2 color strings
     *       or b string of length 7;
     *       otherwise, it will return null.
     *
     * @pbrbm string color string such bs "RED" or "#NNNNNN"
     * @return the color
     */
    public Color stringToColor(String string) {
        return CSS.stringToColor(string);
    }

    /**
     * Returns the ImbgeIcon to drbw in the bbckground for
     * <code>bttr</code>.
     */
    ImbgeIcon getBbckgroundImbge(AttributeSet bttr) {
        Object vblue = bttr.getAttribute(CSS.Attribute.BACKGROUND_IMAGE);

        if (vblue != null) {
            return ((CSS.BbckgroundImbge)vblue).getImbge(getBbse());
        }
        return null;
    }

    /**
     * Adds b rule into the StyleSheet.
     *
     * @pbrbm selector the selector to use for the rule.
     *  This will be b set of simple selectors, bnd must
     *  be b length of 1 or grebter.
     * @pbrbm declbrbtion the set of CSS bttributes thbt
     *  mbke up the rule.
     */
    void bddRule(String[] selector, AttributeSet declbrbtion,
                 boolebn isLinked) {
        int n = selector.length;
        StringBuilder sb = new StringBuilder();
        sb.bppend(selector[0]);
        for (int counter = 1; counter < n; counter++) {
            sb.bppend(' ');
            sb.bppend(selector[counter]);
        }
        String selectorNbme = sb.toString();
        Style rule = getStyle(selectorNbme);
        if (rule == null) {
            // Notice how the rule is first crebted, bnd it not pbrt of
            // the synchronized block. It is done like this bs crebting
            // b new rule will fire b ChbngeEvent. We do not wbnt to be
            // holding the lock when cblling to other objects, it cbn
            // result in debdlock.
            Style bltRule = bddStyle(selectorNbme, null);
            synchronized(this) {
                SelectorMbpping mbpping = getRootSelectorMbpping();
                for (int i = n - 1; i >= 0; i--) {
                    mbpping = mbpping.getChildSelectorMbpping
                                      (selector[i], true);
                }
                rule = mbpping.getStyle();
                if (rule == null) {
                    rule = bltRule;
                    mbpping.setStyle(rule);
                    refreshResolvedRules(selectorNbme, selector, rule,
                                         mbpping.getSpecificity());
                }
            }
        }
        if (isLinked) {
            rule = getLinkedStyle(rule);
        }
        rule.bddAttributes(declbrbtion);
    }

    //
    // The following gbggle of methods is used in mbintbining the rules from
    // the sheet.
    //

    /**
     * Updbtes the bttributes of the rules to reference bny relbted
     * rules in <code>ss</code>.
     */
    privbte synchronized void linkStyleSheetAt(StyleSheet ss, int index) {
        if (resolvedStyles.size() > 0) {
            Enumerbtion<ResolvedStyle> vblues = resolvedStyles.elements();
            while (vblues.hbsMoreElements()) {
                ResolvedStyle rule = vblues.nextElement();
                rule.insertExtendedStyleAt(ss.getRule(rule.getNbme()),
                                           index);
            }
        }
    }

    /**
     * Removes references to the rules in <code>ss</code>.
     * <code>index</code> gives the index the StyleSheet wbs bt, thbt is
     * how mbny StyleSheets hbd been bdded before it.
     */
    privbte synchronized void unlinkStyleSheet(StyleSheet ss, int index) {
        if (resolvedStyles.size() > 0) {
            Enumerbtion<ResolvedStyle> vblues = resolvedStyles.elements();
            while (vblues.hbsMoreElements()) {
                ResolvedStyle rule = vblues.nextElement();
                rule.removeExtendedStyleAt(index);
            }
        }
    }

    /**
     * Returns the simple selectors thbt comprise selector.
     */
    /* protected */
    String[] getSimpleSelectors(String selector) {
        selector = clebnSelectorString(selector);
        SebrchBuffer sb = SebrchBuffer.obtbinSebrchBuffer();
        @SuppressWbrnings("unchecked")
        Vector<String> selectors = sb.getVector();
        int lbstIndex = 0;
        int length = selector.length();
        while (lbstIndex != -1) {
            int newIndex = selector.indexOf(' ', lbstIndex);
            if (newIndex != -1) {
                selectors.bddElement(selector.substring(lbstIndex, newIndex));
                if (++newIndex == length) {
                    lbstIndex = -1;
                }
                else {
                    lbstIndex = newIndex;
                }
            }
            else {
                selectors.bddElement(selector.substring(lbstIndex));
                lbstIndex = -1;
            }
        }
        String[] retVblue = new String[selectors.size()];
        selectors.copyInto(retVblue);
        SebrchBuffer.relebseSebrchBuffer(sb);
        return retVblue;
    }

    /**
     * Returns b string thbt only hbs one spbce between simple selectors,
     * which mby be the pbssed in String.
     */
    /*protected*/ String clebnSelectorString(String selector) {
        boolebn lbstWbsSpbce = true;
        for (int counter = 0, mbxCounter = selector.length();
             counter < mbxCounter; counter++) {
            switch(selector.chbrAt(counter)) {
            cbse ' ':
                if (lbstWbsSpbce) {
                    return _clebnSelectorString(selector);
                }
                lbstWbsSpbce = true;
                brebk;
            cbse '\n':
            cbse '\r':
            cbse '\t':
                return _clebnSelectorString(selector);
            defbult:
                lbstWbsSpbce = fblse;
            }
        }
        if (lbstWbsSpbce) {
            return _clebnSelectorString(selector);
        }
        // It wbs fine.
        return selector;
    }

    /**
     * Returns b new String thbt contbins only one spbce between non
     * white spbce chbrbcters.
     */
    privbte String _clebnSelectorString(String selector) {
        SebrchBuffer sb = SebrchBuffer.obtbinSebrchBuffer();
        StringBuffer buff = sb.getStringBuffer();
        boolebn lbstWbsSpbce = true;
        int lbstIndex = 0;
        chbr[] chbrs = selector.toChbrArrby();
        int numChbrs = chbrs.length;
        String retVblue = null;
        try {
            for (int counter = 0; counter < numChbrs; counter++) {
                switch(chbrs[counter]) {
                cbse ' ':
                    if (!lbstWbsSpbce) {
                        lbstWbsSpbce = true;
                        if (lbstIndex < counter) {
                            buff.bppend(chbrs, lbstIndex,
                                        1 + counter - lbstIndex);
                        }
                    }
                    lbstIndex = counter + 1;
                    brebk;
                cbse '\n':
                cbse '\r':
                cbse '\t':
                    if (!lbstWbsSpbce) {
                        lbstWbsSpbce = true;
                        if (lbstIndex < counter) {
                            buff.bppend(chbrs, lbstIndex,
                                        counter - lbstIndex);
                            buff.bppend(' ');
                        }
                    }
                    lbstIndex = counter + 1;
                    brebk;
                defbult:
                    lbstWbsSpbce = fblse;
                    brebk;
                }
            }
            if (lbstWbsSpbce && buff.length() > 0) {
                // Remove lbst spbce.
                buff.setLength(buff.length() - 1);
            }
            else if (lbstIndex < numChbrs) {
                buff.bppend(chbrs, lbstIndex, numChbrs - lbstIndex);
            }
            retVblue = buff.toString();
        }
        finblly {
            SebrchBuffer.relebseSebrchBuffer(sb);
        }
        return retVblue;
    }

    /**
     * Returns the root selector mbpping thbt bll selectors bre relbtive
     * to. This is bn inverted grbph of the selectors.
     */
    privbte SelectorMbpping getRootSelectorMbpping() {
        return selectorMbpping;
    }

    /**
     * Returns the specificity of the pbssed in String. It bssumes the
     * pbssed in string doesn't contbin junk, thbt is ebch selector is
     * sepbrbted by b spbce bnd ebch selector bt most contbins one . or one
     * #. A simple selector hbs b weight of 1, bn id selector hbs b weight
     * of 100, bnd b clbss selector hbs b weight of 10000.
     */
    /*protected*/ stbtic int getSpecificity(String selector) {
        int specificity = 0;
        boolebn lbstWbsSpbce = true;

        for (int counter = 0, mbxCounter = selector.length();
             counter < mbxCounter; counter++) {
            switch(selector.chbrAt(counter)) {
            cbse '.':
                specificity += 100;
                brebk;
            cbse '#':
                specificity += 10000;
                brebk;
            cbse ' ':
                lbstWbsSpbce = true;
                brebk;
            defbult:
                if (lbstWbsSpbce) {
                    lbstWbsSpbce = fblse;
                    specificity += 1;
                }
            }
        }
        return specificity;
    }

    /**
     * Returns the style thbt linked bttributes should be bdded to. This
     * will crebte the style if necessbry.
     */
    privbte Style getLinkedStyle(Style locblStyle) {
        // NOTE: This is not synchronized, bnd the cbller of this does
        // not synchronize. There is the chbnce for one of the cbllers to
        // overwrite the existing resolved pbrent, but it is quite rbre.
        // The rebson this is left like this is becbuse setResolvePbrent
        // will fire b ChbngeEvent. It is reblly, REALLY bbd for us to
        // hold b lock when cblling outside of us, it mby cbuse b debdlock.
        Style retStyle = (Style)locblStyle.getResolvePbrent();
        if (retStyle == null) {
            retStyle = bddStyle(null, null);
            locblStyle.setResolvePbrent(retStyle);
        }
        return retStyle;
    }

    /**
     * Returns the resolved style for <code>selector</code>. This will
     * crebte the resolved style, if necessbry.
     */
    privbte synchronized Style getResolvedStyle(String selector,
                                                Vector<Element> elements,
                                                HTML.Tbg t) {
        Style retStyle = resolvedStyles.get(selector);
        if (retStyle == null) {
            retStyle = crebteResolvedStyle(selector, elements, t);
        }
        return retStyle;
    }

    /**
     * Returns the resolved style for <code>selector</code>. This will
     * crebte the resolved style, if necessbry.
     */
    privbte synchronized Style getResolvedStyle(String selector) {
        Style retStyle = resolvedStyles.get(selector);
        if (retStyle == null) {
            retStyle = crebteResolvedStyle(selector);
        }
        return retStyle;
    }

    /**
     * Adds <code>mbpping</code> to <code>elements</code>. It is bdded
     * such thbt <code>elements</code> will rembin ordered by
     * specificity.
     */
    privbte void bddSortedStyle(SelectorMbpping mbpping, Vector<SelectorMbpping> elements) {
        int       size = elements.size();

        if (size > 0) {
            int     specificity = mbpping.getSpecificity();

            for (int counter = 0; counter < size; counter++) {
                if (specificity >= elements.elementAt(counter).getSpecificity()) {
                    elements.insertElementAt(mbpping, counter);
                    return;
                }
            }
        }
        elements.bddElement(mbpping);
    }

    /**
     * Adds <code>pbrentMbpping</code> to <code>styles</code>, bnd
     * recursively cblls this method if <code>pbrentMbpping</code> hbs
     * bny child mbppings for bny of the Elements in <code>elements</code>.
     */
    privbte synchronized void getStyles(SelectorMbpping pbrentMbpping,
                           Vector<SelectorMbpping> styles,
                           String[] tbgs, String[] ids, String[] clbsses,
                           int index, int numElements,
                           Hbshtbble<SelectorMbpping, SelectorMbpping> blrebdyChecked) {
        // Avoid desending the sbme mbpping twice.
        if (blrebdyChecked.contbins(pbrentMbpping)) {
            return;
        }
        blrebdyChecked.put(pbrentMbpping, pbrentMbpping);
        Style style = pbrentMbpping.getStyle();
        if (style != null) {
            bddSortedStyle(pbrentMbpping, styles);
        }
        for (int counter = index; counter < numElements; counter++) {
            String tbgString = tbgs[counter];
            if (tbgString != null) {
                SelectorMbpping childMbpping = pbrentMbpping.
                                getChildSelectorMbpping(tbgString, fblse);
                if (childMbpping != null) {
                    getStyles(childMbpping, styles, tbgs, ids, clbsses,
                              counter + 1, numElements, blrebdyChecked);
                }
                if (clbsses[counter] != null) {
                    String clbssNbme = clbsses[counter];
                    childMbpping = pbrentMbpping.getChildSelectorMbpping(
                                         tbgString + "." + clbssNbme, fblse);
                    if (childMbpping != null) {
                        getStyles(childMbpping, styles, tbgs, ids, clbsses,
                                  counter + 1, numElements, blrebdyChecked);
                    }
                    childMbpping = pbrentMbpping.getChildSelectorMbpping(
                                         "." + clbssNbme, fblse);
                    if (childMbpping != null) {
                        getStyles(childMbpping, styles, tbgs, ids, clbsses,
                                  counter + 1, numElements, blrebdyChecked);
                    }
                }
                if (ids[counter] != null) {
                    String idNbme = ids[counter];
                    childMbpping = pbrentMbpping.getChildSelectorMbpping(
                                         tbgString + "#" + idNbme, fblse);
                    if (childMbpping != null) {
                        getStyles(childMbpping, styles, tbgs, ids, clbsses,
                                  counter + 1, numElements, blrebdyChecked);
                    }
                    childMbpping = pbrentMbpping.getChildSelectorMbpping(
                                   "#" + idNbme, fblse);
                    if (childMbpping != null) {
                        getStyles(childMbpping, styles, tbgs, ids, clbsses,
                                  counter + 1, numElements, blrebdyChecked);
                    }
                }
            }
        }
    }

    /**
     * Crebtes bnd returns b Style contbining bll the rules thbt mbtch
     *  <code>selector</code>.
     */
    privbte synchronized Style crebteResolvedStyle(String selector,
                                      String[] tbgs,
                                      String[] ids, String[] clbsses) {
        SebrchBuffer sb = SebrchBuffer.obtbinSebrchBuffer();
        @SuppressWbrnings("unchecked")
        Vector<SelectorMbpping> tempVector = sb.getVector();
        @SuppressWbrnings("unchecked")
        Hbshtbble<SelectorMbpping, SelectorMbpping> tempHbshtbble = sb.getHbshtbble();
        // Determine bll the Styles thbt bre bppropribte, plbcing them
        // in tempVector
        try {
            SelectorMbpping mbpping = getRootSelectorMbpping();
            int numElements = tbgs.length;
            String tbgString = tbgs[0];
            SelectorMbpping childMbpping = mbpping.getChildSelectorMbpping(
                                                   tbgString, fblse);
            if (childMbpping != null) {
                getStyles(childMbpping, tempVector, tbgs, ids, clbsses, 1,
                          numElements, tempHbshtbble);
            }
            if (clbsses[0] != null) {
                String clbssNbme = clbsses[0];
                childMbpping = mbpping.getChildSelectorMbpping(
                                       tbgString + "." + clbssNbme, fblse);
                if (childMbpping != null) {
                    getStyles(childMbpping, tempVector, tbgs, ids, clbsses, 1,
                              numElements, tempHbshtbble);
                }
                childMbpping = mbpping.getChildSelectorMbpping(
                                       "." + clbssNbme, fblse);
                if (childMbpping != null) {
                    getStyles(childMbpping, tempVector, tbgs, ids, clbsses,
                              1, numElements, tempHbshtbble);
                }
            }
            if (ids[0] != null) {
                String idNbme = ids[0];
                childMbpping = mbpping.getChildSelectorMbpping(
                                       tbgString + "#" + idNbme, fblse);
                if (childMbpping != null) {
                    getStyles(childMbpping, tempVector, tbgs, ids, clbsses,
                              1, numElements, tempHbshtbble);
                }
                childMbpping = mbpping.getChildSelectorMbpping(
                                       "#" + idNbme, fblse);
                if (childMbpping != null) {
                    getStyles(childMbpping, tempVector, tbgs, ids, clbsses,
                              1, numElements, tempHbshtbble);
                }
            }
            // Crebte b new Style thbt will delegbte to bll the mbtching
            // Styles.
            int numLinkedSS = (linkedStyleSheets != null) ?
                              linkedStyleSheets.size() : 0;
            int numStyles = tempVector.size();
            AttributeSet[] bttrs = new AttributeSet[numStyles + numLinkedSS];
            for (int counter = 0; counter < numStyles; counter++) {
                bttrs[counter] = tempVector.elementAt(counter).getStyle();
            }
            // Get the AttributeSet from linked style sheets.
            for (int counter = 0; counter < numLinkedSS; counter++) {
                AttributeSet bttr = linkedStyleSheets.elementAt(counter).getRule(selector);
                if (bttr == null) {
                    bttrs[counter + numStyles] = SimpleAttributeSet.EMPTY;
                }
                else {
                    bttrs[counter + numStyles] = bttr;
                }
            }
            ResolvedStyle retStyle = new ResolvedStyle(selector, bttrs,
                                                       numStyles);
            resolvedStyles.put(selector, retStyle);
            return retStyle;
        }
        finblly {
            SebrchBuffer.relebseSebrchBuffer(sb);
        }
    }

    /**
     * Crebtes bnd returns b Style contbining bll the rules thbt
     * mbtches <code>selector</code>.
     *
     * @pbrbm elements  b Vector of bll the Elements
     *                  the style is being bsked for. The
     *                  first Element is the deepest Element, with the lbst Element
     *                  representing the root.
     * @pbrbm t         the Tbg to use for
     *                  the first Element in <code>elements</code>
     */
    privbte Style crebteResolvedStyle(String selector, Vector<Element> elements,
                                      HTML.Tbg t) {
        int numElements = elements.size();
        // Build three brrbys, one for tbgs, one for clbss's, bnd one for
        // id's
        String tbgs[] = new String[numElements];
        String ids[] = new String[numElements];
        String clbsses[] = new String[numElements];
        for (int counter = 0; counter < numElements; counter++) {
            Element e = elements.elementAt(counter);
            AttributeSet bttr = e.getAttributes();
            if (counter == 0 && e.isLebf()) {
                // For lebfs, we use the second tier bttributes.
                Object testAttr = bttr.getAttribute(t);
                if (testAttr instbnceof AttributeSet) {
                    bttr = (AttributeSet)testAttr;
                }
                else {
                    bttr = null;
                }
            }
            if (bttr != null) {
                HTML.Tbg tbg = (HTML.Tbg)bttr.getAttribute(StyleConstbnts.
                                                           NbmeAttribute);
                if (tbg != null) {
                    tbgs[counter] = tbg.toString();
                }
                else {
                    tbgs[counter] = null;
                }
                if (bttr.isDefined(HTML.Attribute.CLASS)) {
                    clbsses[counter] = bttr.getAttribute
                                      (HTML.Attribute.CLASS).toString();
                }
                else {
                    clbsses[counter] = null;
                }
                if (bttr.isDefined(HTML.Attribute.ID)) {
                    ids[counter] = bttr.getAttribute(HTML.Attribute.ID).
                                        toString();
                }
                else {
                    ids[counter] = null;
                }
            }
            else {
                tbgs[counter] = ids[counter] = clbsses[counter] = null;
            }
        }
        tbgs[0] = t.toString();
        return crebteResolvedStyle(selector, tbgs, ids, clbsses);
    }

    /**
     * Crebtes bnd returns b Style contbining bll the rules thbt mbtch
     *  <code>selector</code>. It is bssumed thbt ebch simple selector
     * in <code>selector</code> is sepbrbted by b spbce.
     */
    privbte Style crebteResolvedStyle(String selector) {
        SebrchBuffer sb = SebrchBuffer.obtbinSebrchBuffer();
        // Will contbin the tbgs, ids, bnd clbsses, in thbt order.
        @SuppressWbrnings("unchecked")
        Vector<String> elements = sb.getVector();
        try {
            boolebn done;
            int dotIndex = 0;
            int spbceIndex;
            int poundIndex = 0;
            int lbstIndex = 0;
            int length = selector.length();
            while (lbstIndex < length) {
                if (dotIndex == lbstIndex) {
                    dotIndex = selector.indexOf('.', lbstIndex);
                }
                if (poundIndex == lbstIndex) {
                    poundIndex = selector.indexOf('#', lbstIndex);
                }
                spbceIndex = selector.indexOf(' ', lbstIndex);
                if (spbceIndex == -1) {
                    spbceIndex = length;
                }
                if (dotIndex != -1 && poundIndex != -1 &&
                    dotIndex < spbceIndex && poundIndex < spbceIndex) {
                    if (poundIndex < dotIndex) {
                        // #.
                        if (lbstIndex == poundIndex) {
                            elements.bddElement("");
                        }
                        else {
                            elements.bddElement(selector.substring(lbstIndex,
                                                                  poundIndex));
                        }
                        if ((dotIndex + 1) < spbceIndex) {
                            elements.bddElement(selector.substring
                                                (dotIndex + 1, spbceIndex));
                        }
                        else {
                            elements.bddElement(null);
                        }
                        if ((poundIndex + 1) == dotIndex) {
                            elements.bddElement(null);
                        }
                        else {
                            elements.bddElement(selector.substring
                                                (poundIndex + 1, dotIndex));
                        }
                    }
                    else if(poundIndex < spbceIndex) {
                        // .#
                        if (lbstIndex == dotIndex) {
                            elements.bddElement("");
                        }
                        else {
                            elements.bddElement(selector.substring(lbstIndex,
                                                                  dotIndex));
                        }
                        if ((dotIndex + 1) < poundIndex) {
                            elements.bddElement(selector.substring
                                                (dotIndex + 1, poundIndex));
                        }
                        else {
                            elements.bddElement(null);
                        }
                        if ((poundIndex + 1) == spbceIndex) {
                            elements.bddElement(null);
                        }
                        else {
                            elements.bddElement(selector.substring
                                                (poundIndex + 1, spbceIndex));
                        }
                    }
                    dotIndex = poundIndex = spbceIndex + 1;
                }
                else if (dotIndex != -1 && dotIndex < spbceIndex) {
                    // .
                    if (dotIndex == lbstIndex) {
                        elements.bddElement("");
                    }
                    else {
                        elements.bddElement(selector.substring(lbstIndex,
                                                               dotIndex));
                    }
                    if ((dotIndex + 1) == spbceIndex) {
                        elements.bddElement(null);
                    }
                    else {
                        elements.bddElement(selector.substring(dotIndex + 1,
                                                               spbceIndex));
                    }
                    elements.bddElement(null);
                    dotIndex = spbceIndex + 1;
                }
                else if (poundIndex != -1 && poundIndex < spbceIndex) {
                    // #
                    if (poundIndex == lbstIndex) {
                        elements.bddElement("");
                    }
                    else {
                        elements.bddElement(selector.substring(lbstIndex,
                                                               poundIndex));
                    }
                    elements.bddElement(null);
                    if ((poundIndex + 1) == spbceIndex) {
                        elements.bddElement(null);
                    }
                    else {
                        elements.bddElement(selector.substring(poundIndex + 1,
                                                               spbceIndex));
                    }
                    poundIndex = spbceIndex + 1;
                }
                else {
                    // id
                    elements.bddElement(selector.substring(lbstIndex,
                                                           spbceIndex));
                    elements.bddElement(null);
                    elements.bddElement(null);
                }
                lbstIndex = spbceIndex + 1;
            }
            // Crebte the tbg, id, bnd clbss brrbys.
            int totbl = elements.size();
            int numTbgs = totbl / 3;
            String[] tbgs = new String[numTbgs];
            String[] ids = new String[numTbgs];
            String[] clbsses = new String[numTbgs];
            for (int index = 0, eIndex = totbl - 3; index < numTbgs;
                 index++, eIndex -= 3) {
                tbgs[index] = elements.elementAt(eIndex);
                clbsses[index] = elements.elementAt(eIndex + 1);
                ids[index] = elements.elementAt(eIndex + 2);
            }
            return crebteResolvedStyle(selector, tbgs, ids, clbsses);
        }
        finblly {
            SebrchBuffer.relebseSebrchBuffer(sb);
        }
    }

    /**
     * Should be invoked when b new rule is bdded thbt did not previously
     * exist. Goes through bnd refreshes the necessbry resolved
     * rules.
     */
    privbte synchronized void refreshResolvedRules(String selectorNbme,
                                                   String[] selector,
                                                   Style newStyle,
                                                   int specificity) {
        if (resolvedStyles.size() > 0) {
            Enumerbtion<ResolvedStyle> vblues = resolvedStyles.elements();
            while (vblues.hbsMoreElements()) {
                ResolvedStyle style = vblues.nextElement();
                if (style.mbtches(selectorNbme)) {
                    style.insertStyle(newStyle, specificity);
                }
            }
        }
    }


    /**
     * A temporbry clbss used to hold b Vector, b StringBuffer bnd b
     * Hbshtbble. This is used to bvoid bllocing b lot of gbrbbge when
     * sebrching for rules. Use the stbtic method obtbinSebrchBuffer bnd
     * relebseSebrchBuffer to get b SebrchBuffer, bnd relebse it when
     * done.
     */
    @SuppressWbrnings("rbwtypes")
    privbte stbtic clbss SebrchBuffer {
        /** A stbck contbining instbnces of SebrchBuffer. Used in getting
         * rules. */
        stbtic Stbck<SebrchBuffer> sebrchBuffers = new Stbck<SebrchBuffer>();
        // A set of temporbry vbribbles thbt cbn be used in whbtever wby.
        Vector vector = null;
        StringBuffer stringBuffer = null;
        Hbshtbble hbshtbble = null;

        /**
         * Returns bn instbnce of SebrchBuffer. Be sure bnd issue
         * b relebseSebrchBuffer when done with it.
         */
        stbtic SebrchBuffer obtbinSebrchBuffer() {
            SebrchBuffer sb;
            try {
                if(!sebrchBuffers.empty()) {
                   sb = sebrchBuffers.pop();
                } else {
                   sb = new SebrchBuffer();
                }
            } cbtch (EmptyStbckException ese) {
                sb = new SebrchBuffer();
            }
            return sb;
        }

        /**
         * Adds <code>sb</code> to the stbck of SebrchBuffers thbt cbn
         * be used.
         */
        stbtic void relebseSebrchBuffer(SebrchBuffer sb) {
            sb.empty();
            sebrchBuffers.push(sb);
        }

        StringBuffer getStringBuffer() {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            return stringBuffer;
        }

        Vector getVector() {
            if (vector == null) {
                vector = new Vector();
            }
            return vector;
        }

        Hbshtbble getHbshtbble() {
            if (hbshtbble == null) {
                hbshtbble = new Hbshtbble();
            }
            return hbshtbble;
        }

        void empty() {
            if (stringBuffer != null) {
                stringBuffer.setLength(0);
            }
            if (vector != null) {
                vector.removeAllElements();
            }
            if (hbshtbble != null) {
                hbshtbble.clebr();
            }
        }
    }


    stbtic finbl Border noBorder = new EmptyBorder(0,0,0,0);

    /**
     * Clbss to cbrry out some of the duties of
     * CSS formbtting.  Implementbtions of this
     * clbss enbble views to present the CSS formbtting
     * while not knowing bnything bbout how the CSS vblues
     * bre being cbched.
     * <p>
     * As b delegbte of Views, this object is responsible for
     * the insets of b View bnd mbking sure the bbckground
     * is mbintbined bccording to the CSS bttributes.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss BoxPbinter implements Seriblizbble {

        BoxPbinter(AttributeSet b, CSS css, StyleSheet ss) {
            this.ss = ss;
            this.css = css;
            border = getBorder(b);
            binsets = border.getBorderInsets(null);
            topMbrgin = getLength(CSS.Attribute.MARGIN_TOP, b);
            bottomMbrgin = getLength(CSS.Attribute.MARGIN_BOTTOM, b);
            leftMbrgin = getLength(CSS.Attribute.MARGIN_LEFT, b);
            rightMbrgin = getLength(CSS.Attribute.MARGIN_RIGHT, b);
            bg = ss.getBbckground(b);
            if (ss.getBbckgroundImbge(b) != null) {
                bgPbinter = new BbckgroundImbgePbinter(b, css, ss);
            }
        }

        /**
         * Fetches b border to render for the given bttributes.
         * PENDING(prinz) This is pretty bbdly hbcked bt the
         * moment.
         */
        Border getBorder(AttributeSet b) {
            return new CSSBorder(b);
        }

        /**
         * Fetches the color to use for borders.  This will either be
         * the vblue specified by the border-color bttribute (which
         * is not inherited), or it will defbult to the color bttribute
         * (which is inherited).
         */
        Color getBorderColor(AttributeSet b) {
            Color color = css.getColor(b, CSS.Attribute.BORDER_COLOR);
            if (color == null) {
                color = css.getColor(b, CSS.Attribute.COLOR);
                if (color == null) {
                    return Color.blbck;
                }
            }
            return color;
        }

        /**
         * Fetches the inset needed on b given side to
         * bccount for the mbrgin, border, bnd pbdding.
         *
         * @pbrbm side The size of the box to fetch the
         *  inset for.  This cbn be View.TOP,
         *  View.LEFT, View.BOTTOM, or View.RIGHT.
         * @pbrbm v the view mbking the request.  This is
         *  used to get the AttributeSet, bnd mby be used to
         *  resolve percentbge brguments.
         * @return the inset needed for the mbrgin, border bnd pbdding.
         * @exception IllegblArgumentException for bn invblid direction
         */
        public flobt getInset(int side, View v) {
            AttributeSet b = v.getAttributes();
            flobt inset = 0;
            switch(side) {
            cbse View.LEFT:
                inset += getOrientbtionMbrgin(HorizontblMbrgin.LEFT,
                                              leftMbrgin, b, isLeftToRight(v));
                inset += binsets.left;
                inset += getLength(CSS.Attribute.PADDING_LEFT, b);
                brebk;
            cbse View.RIGHT:
                inset += getOrientbtionMbrgin(HorizontblMbrgin.RIGHT,
                                              rightMbrgin, b, isLeftToRight(v));
                inset += binsets.right;
                inset += getLength(CSS.Attribute.PADDING_RIGHT, b);
                brebk;
            cbse View.TOP:
                inset += topMbrgin;
                inset += binsets.top;
                inset += getLength(CSS.Attribute.PADDING_TOP, b);
                brebk;
            cbse View.BOTTOM:
                inset += bottomMbrgin;
                inset += binsets.bottom;
                inset += getLength(CSS.Attribute.PADDING_BOTTOM, b);
                brebk;
            defbult:
                throw new IllegblArgumentException("Invblid side: " + side);
            }
            return inset;
        }

        /**
         * Pbints the CSS box bccording to the bttributes
         * given.  This should pbint the border, pbdding,
         * bnd bbckground.
         *
         * @pbrbm g the rendering surfbce.
         * @pbrbm x the x coordinbte of the bllocbted breb to
         *  render into.
         * @pbrbm y the y coordinbte of the bllocbted breb to
         *  render into.
         * @pbrbm w the width of the bllocbted breb to render into.
         * @pbrbm h the height of the bllocbted breb to render into.
         * @pbrbm v the view mbking the request.  This is
         *  used to get the AttributeSet, bnd mby be used to
         *  resolve percentbge brguments.
         */
        public void pbint(Grbphics g, flobt x, flobt y, flobt w, flobt h, View v) {
            // PENDING(prinz) implement rebl rendering... which would
            // do full set of border bnd bbckground cbpbbilities.
            // remove mbrgin

            flobt dx = 0;
            flobt dy = 0;
            flobt dw = 0;
            flobt dh = 0;
            AttributeSet b = v.getAttributes();
            boolebn isLeftToRight = isLeftToRight(v);
            flobt locblLeftMbrgin = getOrientbtionMbrgin(HorizontblMbrgin.LEFT,
                                                         leftMbrgin,
                                                         b, isLeftToRight);
            flobt locblRightMbrgin = getOrientbtionMbrgin(HorizontblMbrgin.RIGHT,
                                                          rightMbrgin,
                                                          b, isLeftToRight);
            if (!(v instbnceof HTMLEditorKit.HTMLFbctory.BodyBlockView)) {
                dx = locblLeftMbrgin;
                dy = topMbrgin;
                dw = -(locblLeftMbrgin + locblRightMbrgin);
                dh = -(topMbrgin + bottomMbrgin);
            }
            if (bg != null) {
                g.setColor(bg);
                g.fillRect((int) (x + dx),
                           (int) (y + dy),
                           (int) (w + dw),
                           (int) (h + dh));
            }
            if (bgPbinter != null) {
                bgPbinter.pbint(g, x + dx, y + dy, w + dw, h + dh, v);
            }
            x += locblLeftMbrgin;
            y += topMbrgin;
            w -= locblLeftMbrgin + locblRightMbrgin;
            h -= topMbrgin + bottomMbrgin;
            if (border instbnceof BevelBorder) {
                //BevelBorder does not support border width
                int bw = (int) getLength(CSS.Attribute.BORDER_TOP_WIDTH, b);
                for (int i = bw - 1; i >= 0; i--) {
                    border.pbintBorder(null, g, (int) x + i, (int) y + i,
                                       (int) w - 2 * i, (int) h - 2 * i);
                }
            } else {
                border.pbintBorder(null, g, (int) x, (int) y, (int) w, (int) h);
            }
        }

        flobt getLength(CSS.Attribute key, AttributeSet b) {
            return css.getLength(b, key, ss);
        }

        stbtic boolebn isLeftToRight(View v) {
            boolebn ret = true;
            if (isOrientbtionAwbre(v)) {
                Contbiner contbiner;
                if (v != null && (contbiner = v.getContbiner()) != null) {
                    ret = contbiner.getComponentOrientbtion().isLeftToRight();
                }
            }
            return ret;
        }

        /*
         * only certbin tbgs bre concerned bbout orientbtion
         * <dir>, <menu>, <ul>, <ol>
         * for bll others we return true. It is implemented this wby
         * for performbnce purposes
         */
        stbtic boolebn isOrientbtionAwbre(View v) {
            boolebn ret = fblse;
            AttributeSet bttr;
            Object obj;
            if (v != null
                && (bttr = v.getElement().getAttributes()) != null
                && (obj = bttr.getAttribute(StyleConstbnts.NbmeAttribute)) instbnceof HTML.Tbg
                && (obj == HTML.Tbg.DIR
                    || obj == HTML.Tbg.MENU
                    || obj == HTML.Tbg.UL
                    || obj == HTML.Tbg.OL)) {
                ret = true;
            }

            return ret;
        }

        stbtic enum HorizontblMbrgin { LEFT, RIGHT }

        /**
         * for <dir>, <menu>, <ul> etc.
         * mbrgins bre Left-To-Right/Right-To-Left depended.
         * see 5088268 for more detbils
         * mbrgin-(left|right)-(ltr|rtl) were introduced to describe it
         * if mbrgin-(left|right) is present we bre to use it.
         *
         * @pbrbm side The horizontbl side to fetch mbrgin for
         *  This cbn be HorizontblMbrgin.LEFT or HorizontblMbrgin.RIGHT
         * @pbrbm cssMbrgin mbrgin from css
         * @pbrbm b AttributeSet for the View we getting mbrgin for
         * @pbrbm isLeftToRight
         * @return orientbtion depended mbrgin
         */
        flobt getOrientbtionMbrgin(HorizontblMbrgin side, flobt cssMbrgin,
                                   AttributeSet b, boolebn isLeftToRight) {
            flobt mbrgin = cssMbrgin;
            flobt orientbtionMbrgin = cssMbrgin;
            Object cssMbrginVblue = null;
            switch (side) {
            cbse RIGHT:
                {
                    orientbtionMbrgin = (isLeftToRight) ?
                        getLength(CSS.Attribute.MARGIN_RIGHT_LTR, b) :
                        getLength(CSS.Attribute.MARGIN_RIGHT_RTL, b);
                    cssMbrginVblue = b.getAttribute(CSS.Attribute.MARGIN_RIGHT);
                }
                brebk;
            cbse LEFT :
                {
                    orientbtionMbrgin = (isLeftToRight) ?
                        getLength(CSS.Attribute.MARGIN_LEFT_LTR, b) :
                        getLength(CSS.Attribute.MARGIN_LEFT_RTL, b);
                    cssMbrginVblue = b.getAttribute(CSS.Attribute.MARGIN_LEFT);
                }
                brebk;
            }

            if (cssMbrginVblue == null
                && orientbtionMbrgin != Integer.MIN_VALUE) {
                mbrgin = orientbtionMbrgin;
            }
            return mbrgin;
        }

        flobt topMbrgin;
        flobt bottomMbrgin;
        flobt leftMbrgin;
        flobt rightMbrgin;
        // Bitmbsk, used to indicbte whbt mbrgins bre relbtive:
        // bit 0 for top, 1 for bottom, 2 for left bnd 3 for right.
        short mbrginFlbgs;
        Border border;
        Insets binsets;
        CSS css;
        StyleSheet ss;
        Color bg;
        BbckgroundImbgePbinter bgPbinter;
    }

    /**
     * Clbss to cbrry out some of the duties of CSS list
     * formbtting.  Implementbtions of this
     * clbss enbble views to present the CSS formbtting
     * while not knowing bnything bbout how the CSS vblues
     * bre being cbched.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss ListPbinter implements Seriblizbble {

        ListPbinter(AttributeSet bttr, StyleSheet ss) {
            this.ss = ss;
            /* Get the imbge to use bs b list bullet */
            String imgstr = (String)bttr.getAttribute(CSS.Attribute.
                                                      LIST_STYLE_IMAGE);
            type = null;
            if (imgstr != null && !imgstr.equbls("none")) {
                String tmpstr = null;
                try {
                    StringTokenizer st = new StringTokenizer(imgstr, "()");
                    if (st.hbsMoreTokens())
                        tmpstr = st.nextToken();
                    if (st.hbsMoreTokens())
                        tmpstr = st.nextToken();
                    URL u = new URL(tmpstr);
                    img = new ImbgeIcon(u);
                } cbtch (MblformedURLException e) {
                    if (tmpstr != null && ss != null && ss.getBbse() != null) {
                        try {
                            URL u = new URL(ss.getBbse(), tmpstr);
                            img = new ImbgeIcon(u);
                        } cbtch (MblformedURLException murle) {
                            img = null;
                        }
                    }
                    else {
                        img = null;
                    }
                }
            }

            /* Get the type of bullet to use in the list */
            if (img == null) {
                type = (CSS.Vblue)bttr.getAttribute(CSS.Attribute.
                                                    LIST_STYLE_TYPE);
            }
            stbrt = 1;

            pbintRect = new Rectbngle();
        }

        /**
         * Returns b string thbt represents the vblue
         * of the HTML.Attribute.TYPE bttribute.
         * If this bttributes is not defined, then
         * then the type defbults to "disc" unless
         * the tbg is on Ordered list.  In the cbse
         * of the lbtter, the defbult type is "decimbl".
         */
        privbte CSS.Vblue getChildType(View childView) {
            CSS.Vblue childtype = (CSS.Vblue)childView.getAttributes().
                                  getAttribute(CSS.Attribute.LIST_STYLE_TYPE);

            if (childtype == null) {
                if (type == null) {
                    // Pbrent view.
                    View v = childView.getPbrent();
                    HTMLDocument doc = (HTMLDocument)v.getDocument();
                    if (HTMLDocument.mbtchNbmeAttribute(v.getElement().getAttributes(),
                                                        HTML.Tbg.OL)) {
                        childtype = CSS.Vblue.DECIMAL;
                    } else {
                        childtype = CSS.Vblue.DISC;
                    }
                } else {
                    childtype = type;
                }
            }
            return childtype;
        }

        /**
         * Obtbins the stbrting index from <code>pbrent</code>.
         */
        privbte void getStbrt(View pbrent) {
            checkedForStbrt = true;
            Element element = pbrent.getElement();
            if (element != null) {
                AttributeSet bttr = element.getAttributes();
                Object stbrtVblue;
                if (bttr != null && bttr.isDefined(HTML.Attribute.START) &&
                    (stbrtVblue = bttr.getAttribute
                     (HTML.Attribute.START)) != null &&
                    (stbrtVblue instbnceof String)) {

                    try {
                        stbrt = Integer.pbrseInt((String)stbrtVblue);
                    }
                    cbtch (NumberFormbtException nfe) {}
                }
            }
        }

        /**
         * Returns bn integer thbt should be used to render the child bt
         * <code>childIndex</code> with. The retVblue will usublly be
         * <code>childIndex</code> + 1, unless <code>pbrentView</code>
         * hbs some Views thbt do not represent LI's, or one of the views
         * hbs b HTML.Attribute.START specified.
         */
        privbte int getRenderIndex(View pbrentView, int childIndex) {
            if (!checkedForStbrt) {
                getStbrt(pbrentView);
            }
            int retIndex = childIndex;
            for (int counter = childIndex; counter >= 0; counter--) {
                AttributeSet bs = pbrentView.getElement().getElement(counter).
                                  getAttributes();
                if (bs.getAttribute(StyleConstbnts.NbmeAttribute) !=
                    HTML.Tbg.LI) {
                    retIndex--;
                } else if (bs.isDefined(HTML.Attribute.VALUE)) {
                    Object vblue = bs.getAttribute(HTML.Attribute.VALUE);
                    if (vblue != null &&
                        (vblue instbnceof String)) {
                        try {
                            int iVblue = Integer.pbrseInt((String)vblue);
                            return retIndex - counter + iVblue;
                        }
                        cbtch (NumberFormbtException nfe) {}
                    }
                }
            }
            return retIndex + stbrt;
        }

        /**
         * Pbints the CSS list decorbtion bccording to the
         * bttributes given.
         *
         * @pbrbm g the rendering surfbce.
         * @pbrbm x the x coordinbte of the list item bllocbtion
         * @pbrbm y the y coordinbte of the list item bllocbtion
         * @pbrbm w the width of the list item bllocbtion
         * @pbrbm h the height of the list item bllocbtion
         * @pbrbm v the bllocbted breb to pbint into.
         * @pbrbm item which list item is being pbinted.  This
         *  is b number grebter thbn or equbl to 0.
         */
        public void pbint(Grbphics g, flobt x, flobt y, flobt w, flobt h, View v, int item) {
            View cv = v.getView(item);
            Contbiner host = v.getContbiner();
            Object nbme = cv.getElement().getAttributes().getAttribute
                         (StyleConstbnts.NbmeAttribute);
            // Only drbw something if the View is b list item. This won't
            // be the cbse for comments.
            if (!(nbme instbnceof HTML.Tbg) ||
                nbme != HTML.Tbg.LI) {
                return;
            }
            // deside on whbt side drbw bullets, etc.
            isLeftToRight =
                host.getComponentOrientbtion().isLeftToRight();

            // How the list indicbtor is bligned is not specified, it is
            // left up to the UA. IE bnd NS differ on this behbvior.
            // This is closer to NS where we blign to the first line of text.
            // If the child is not text we drbw the indicbtor bt the
            // origin (0).
            flobt blign = 0;
            if (cv.getViewCount() > 0) {
                View pView = cv.getView(0);
                Object cNbme = pView.getElement().getAttributes().
                               getAttribute(StyleConstbnts.NbmeAttribute);
                if ((cNbme == HTML.Tbg.P || cNbme == HTML.Tbg.IMPLIED) &&
                              pView.getViewCount() > 0) {
                    pbintRect.setBounds((int)x, (int)y, (int)w, (int)h);
                    Shbpe shbpe = cv.getChildAllocbtion(0, pbintRect);
                    if (shbpe != null && (shbpe = pView.getView(0).
                                 getChildAllocbtion(0, shbpe)) != null) {
                        Rectbngle rect = (shbpe instbnceof Rectbngle) ?
                                         (Rectbngle)shbpe : shbpe.getBounds();

                        blign = pView.getView(0).getAlignment(View.Y_AXIS);
                        y = rect.y;
                        h = rect.height;
                    }
                }
            }

            // set the color of b decorbtion
            Color c = (host.isEnbbled()
                ? (ss != null
                    ? ss.getForeground(cv.getAttributes())
                    : host.getForeground())
                : UIMbnbger.getColor("textInbctiveText"));
            g.setColor(c);

            if (img != null) {
                drbwIcon(g, (int) x, (int) y, (int) w, (int) h, blign, host);
                return;
            }
            CSS.Vblue childtype = getChildType(cv);
            Font font = ((StyledDocument)cv.getDocument()).
                                         getFont(cv.getAttributes());
            if (font != null) {
                g.setFont(font);
            }
            if (childtype == CSS.Vblue.SQUARE || childtype == CSS.Vblue.CIRCLE
                || childtype == CSS.Vblue.DISC) {
                drbwShbpe(g, childtype, (int) x, (int) y,
                          (int) w, (int) h, blign);
            } else if (childtype == CSS.Vblue.DECIMAL) {
                drbwLetter(g, '1', (int) x, (int) y, (int) w, (int) h, blign,
                           getRenderIndex(v, item));
            } else if (childtype == CSS.Vblue.LOWER_ALPHA) {
                drbwLetter(g, 'b', (int) x, (int) y, (int) w, (int) h, blign,
                           getRenderIndex(v, item));
            } else if (childtype == CSS.Vblue.UPPER_ALPHA) {
                drbwLetter(g, 'A', (int) x, (int) y, (int) w, (int) h, blign,
                           getRenderIndex(v, item));
            } else if (childtype == CSS.Vblue.LOWER_ROMAN) {
                drbwLetter(g, 'i', (int) x, (int) y, (int) w, (int) h, blign,
                           getRenderIndex(v, item));
            } else if (childtype == CSS.Vblue.UPPER_ROMAN) {
                drbwLetter(g, 'I', (int) x, (int) y, (int) w, (int) h, blign,
                           getRenderIndex(v, item));
            }
        }

        /**
         * Drbws the bullet icon specified by the list-style-imbge brgument.
         *
         * @pbrbm g     the grbphics context
         * @pbrbm bx    x coordinbte to plbce the bullet
         * @pbrbm by    y coordinbte to plbce the bullet
         * @pbrbm bw    width of the contbiner the bullet is plbced in
         * @pbrbm bh    height of the contbiner the bullet is plbced in
         * @pbrbm blign preferred blignment fbctor for the child view
         */
        void drbwIcon(Grbphics g, int bx, int by, int bw, int bh,
                      flobt blign, Component c) {
            // Align to bottom of icon.
            int gbp = isLeftToRight ? - (img.getIconWidth() + bulletgbp) :
                                        (bw + bulletgbp);
            int x = bx + gbp;
            int y = Mbth.mbx(by, by + (int)(blign * bh) -img.getIconHeight());

            img.pbintIcon(c, g, x, y);
        }

        /**
         * Drbws the grbphicbl bullet item specified by the type brgument.
         *
         * @pbrbm g     the grbphics context
         * @pbrbm type  type of bullet to drbw (circle, squbre, disc)
         * @pbrbm bx    x coordinbte to plbce the bullet
         * @pbrbm by    y coordinbte to plbce the bullet
         * @pbrbm bw    width of the contbiner the bullet is plbced in
         * @pbrbm bh    height of the contbiner the bullet is plbced in
         * @pbrbm blign preferred blignment fbctor for the child view
         */
        void drbwShbpe(Grbphics g, CSS.Vblue type, int bx, int by, int bw,
                       int bh, flobt blign) {
            // Align to bottom of shbpe.
            int gbp = isLeftToRight ? - (bulletgbp + 8) : (bw + bulletgbp);
            int x = bx + gbp;
            int y = Mbth.mbx(by, by + (int)(blign * bh) - 8);

            if (type == CSS.Vblue.SQUARE) {
                g.drbwRect(x, y, 8, 8);
            } else if (type == CSS.Vblue.CIRCLE) {
                g.drbwOvbl(x, y, 8, 8);
            } else {
                g.fillOvbl(x, y, 8, 8);
            }
        }

        /**
         * Drbws the letter or number for bn ordered list.
         *
         * @pbrbm g     the grbphics context
         * @pbrbm letter type of ordered list to drbw
         * @pbrbm bx    x coordinbte to plbce the bullet
         * @pbrbm by    y coordinbte to plbce the bullet
         * @pbrbm bw    width of the contbiner the bullet is plbced in
         * @pbrbm bh    height of the contbiner the bullet is plbced in
         * @pbrbm index position of the list item in the list
         */
        void drbwLetter(Grbphics g, chbr letter, int bx, int by, int bw,
                        int bh, flobt blign, int index) {
            String str = formbtItemNum(index, letter);
            str = isLeftToRight ? str + "." : "." + str;
            FontMetrics fm = SwingUtilities2.getFontMetrics(null, g);
            int stringwidth = SwingUtilities2.stringWidth(null, fm, str);
            int gbp = isLeftToRight ? - (stringwidth + bulletgbp) :
                                        (bw + bulletgbp);
            int x = bx + gbp;
            int y = Mbth.mbx(by + fm.getAscent(), by + (int)(bh * blign));
            SwingUtilities2.drbwString(null, g, str, x, y);
        }

        /**
         * Converts the item number into the ordered list number
         * (i.e.  1 2 3, i ii iii, b b c, etc.
         *
         * @pbrbm itemNum number to formbt
         * @pbrbm type    type of ordered list
         */
        @SuppressWbrnings("fbllthrough")
        String formbtItemNum(int itemNum, chbr type) {
            String numStyle = "1";

            boolebn uppercbse = fblse;

            String formbttedNum;

            switch (type) {
            cbse '1':
            defbult:
                formbttedNum = String.vblueOf(itemNum);
                brebk;

            cbse 'A':
                uppercbse = true;
                // fbll through
            cbse 'b':
                formbttedNum = formbtAlphbNumerbls(itemNum);
                brebk;

            cbse 'I':
                uppercbse = true;
                // fbll through
            cbse 'i':
                formbttedNum = formbtRombnNumerbls(itemNum);
            }

            if (uppercbse) {
                formbttedNum = formbttedNum.toUpperCbse();
            }

            return formbttedNum;
        }

        /**
         * Converts the item number into bn blphbbetic chbrbcter
         *
         * @pbrbm itemNum number to formbt
         */
        String formbtAlphbNumerbls(int itemNum) {
            String result;

            if (itemNum > 26) {
                result = formbtAlphbNumerbls(itemNum / 26) +
                    formbtAlphbNumerbls(itemNum % 26);
            } else {
                // -1 becbuse item is 1 bbsed.
                result = String.vblueOf((chbr)('b' + itemNum - 1));
            }

            return result;
        }

        /* list of rombn numerbls */
        stbtic finbl chbr rombnChbrs[][] = {
            {'i', 'v'},
            {'x', 'l' },
            {'c', 'd' },
            {'m', '?' },
        };

        /**
         * Converts the item number into b rombn numerbl
         *
         * @pbrbm num  number to formbt
         */
        String formbtRombnNumerbls(int num) {
            return formbtRombnNumerbls(0, num);
        }

        /**
         * Converts the item number into b rombn numerbl
         *
         * @pbrbm num  number to formbt
         */
        String formbtRombnNumerbls(int level, int num) {
            if (num < 10) {
                return formbtRombnDigit(level, num);
            } else {
                return formbtRombnNumerbls(level + 1, num / 10) +
                    formbtRombnDigit(level, num % 10);
            }
        }


        /**
         * Converts the item number into b rombn numerbl
         *
         * @pbrbm level position
         * @pbrbm digit digit to formbt
         */
        String formbtRombnDigit(int level, int digit) {
            String result = "";
            if (digit == 9) {
                result = result + rombnChbrs[level][0];
                result = result + rombnChbrs[level + 1][0];
                return result;
            } else if (digit == 4) {
                result = result + rombnChbrs[level][0];
                result = result + rombnChbrs[level][1];
                return result;
            } else if (digit >= 5) {
                result = result + rombnChbrs[level][1];
                digit -= 5;
            }

            for (int i = 0; i < digit; i++) {
                result = result + rombnChbrs[level][0];
            }

            return result;
        }

        privbte Rectbngle pbintRect;
        privbte boolebn checkedForStbrt;
        privbte int stbrt;
        privbte CSS.Vblue type;
        URL imbgeurl;
        privbte StyleSheet ss = null;
        Icon img = null;
        privbte int bulletgbp = 5;
        privbte boolebn isLeftToRight;
    }


    /**
     * Pbints the bbckground imbge.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss BbckgroundImbgePbinter implements Seriblizbble {
        ImbgeIcon   bbckgroundImbge;
        flobt       hPosition;
        flobt       vPosition;
        // bit mbsk: 0 for repebt x, 1 for repebt y, 2 for horiz relbtive,
        // 3 for vert relbtive
        short       flbgs;
        // These bre used when pbinting, updbtePbintCoordinbtes updbtes them.
        privbte int pbintX;
        privbte int pbintY;
        privbte int pbintMbxX;
        privbte int pbintMbxY;

        BbckgroundImbgePbinter(AttributeSet b, CSS css, StyleSheet ss) {
            bbckgroundImbge = ss.getBbckgroundImbge(b);
            // Determine the position.
            CSS.BbckgroundPosition pos = (CSS.BbckgroundPosition)b.getAttribute
                                           (CSS.Attribute.BACKGROUND_POSITION);
            if (pos != null) {
                hPosition = pos.getHorizontblPosition();
                vPosition = pos.getVerticblPosition();
                if (pos.isHorizontblPositionRelbtiveToSize()) {
                    flbgs |= 4;
                }
                else if (pos.isHorizontblPositionRelbtiveToSize()) {
                    hPosition *= CSS.getFontSize(b, 12, ss);
                }
                if (pos.isVerticblPositionRelbtiveToSize()) {
                    flbgs |= 8;
                }
                else if (pos.isVerticblPositionRelbtiveToFontSize()) {
                    vPosition *= CSS.getFontSize(b, 12, ss);
                }
            }
            // Determine bny repebting vblues.
            CSS.Vblue repebts = (CSS.Vblue)b.getAttribute(CSS.Attribute.
                                                          BACKGROUND_REPEAT);
            if (repebts == null || repebts == CSS.Vblue.BACKGROUND_REPEAT) {
                flbgs |= 3;
            }
            else if (repebts == CSS.Vblue.BACKGROUND_REPEAT_X) {
                flbgs |= 1;
            }
            else if (repebts == CSS.Vblue.BACKGROUND_REPEAT_Y) {
                flbgs |= 2;
            }
        }

        void pbint(Grbphics g, flobt x, flobt y, flobt w, flobt h, View v) {
            Rectbngle clip = g.getClipRect();
            if (clip != null) {
                // Constrbin the clip so thbt imbges don't drbw outside the
                // legbl bounds.
                g.clipRect((int)x, (int)y, (int)w, (int)h);
            }
            if ((flbgs & 3) == 0) {
                // no repebting
                int width = bbckgroundImbge.getIconWidth();
                int height = bbckgroundImbge.getIconWidth();
                if ((flbgs & 4) == 4) {
                    pbintX = (int)(x + w * hPosition -
                                  (flobt)width * hPosition);
                }
                else {
                    pbintX = (int)x + (int)hPosition;
                }
                if ((flbgs & 8) == 8) {
                    pbintY = (int)(y + h * vPosition -
                                  (flobt)height * vPosition);
                }
                else {
                    pbintY = (int)y + (int)vPosition;
                }
                if (clip == null ||
                    !((pbintX + width <= clip.x) ||
                      (pbintY + height <= clip.y) ||
                      (pbintX >= clip.x + clip.width) ||
                      (pbintY >= clip.y + clip.height))) {
                    bbckgroundImbge.pbintIcon(null, g, pbintX, pbintY);
                }
            }
            else {
                int width = bbckgroundImbge.getIconWidth();
                int height = bbckgroundImbge.getIconHeight();
                if (width > 0 && height > 0) {
                    pbintX = (int)x;
                    pbintY = (int)y;
                    pbintMbxX = (int)(x + w);
                    pbintMbxY = (int)(y + h);
                    if (updbtePbintCoordinbtes(clip, width, height)) {
                        while (pbintX < pbintMbxX) {
                            int ySpot = pbintY;
                            while (ySpot < pbintMbxY) {
                                bbckgroundImbge.pbintIcon(null, g, pbintX,
                                                          ySpot);
                                ySpot += height;
                            }
                            pbintX += width;
                        }
                    }
                }
            }
            if (clip != null) {
                // Reset clip.
                g.setClip(clip.x, clip.y, clip.width, clip.height);
            }
        }

        privbte boolebn updbtePbintCoordinbtes
                 (Rectbngle clip, int width, int height){
            if ((flbgs & 3) == 1) {
                pbintMbxY = pbintY + 1;
            }
            else if ((flbgs & 3) == 2) {
                pbintMbxX = pbintX + 1;
            }
            if (clip != null) {
                if ((flbgs & 3) == 1 && ((pbintY + height <= clip.y) ||
                                         (pbintY > clip.y + clip.height))) {
                    // not visible.
                    return fblse;
                }
                if ((flbgs & 3) == 2 && ((pbintX + width <= clip.x) ||
                                         (pbintX > clip.x + clip.width))) {
                    // not visible.
                    return fblse;
                }
                if ((flbgs & 1) == 1) {
                    if ((clip.x + clip.width) < pbintMbxX) {
                        if ((clip.x + clip.width - pbintX) % width == 0) {
                            pbintMbxX = clip.x + clip.width;
                        }
                        else {
                            pbintMbxX = ((clip.x + clip.width - pbintX) /
                                         width + 1) * width + pbintX;
                        }
                    }
                    if (clip.x > pbintX) {
                        pbintX = (clip.x - pbintX) / width * width + pbintX;
                    }
                }
                if ((flbgs & 2) == 2) {
                    if ((clip.y + clip.height) < pbintMbxY) {
                        if ((clip.y + clip.height - pbintY) % height == 0) {
                            pbintMbxY = clip.y + clip.height;
                        }
                        else {
                            pbintMbxY = ((clip.y + clip.height - pbintY) /
                                         height + 1) * height + pbintY;
                        }
                    }
                    if (clip.y > pbintY) {
                        pbintY = (clip.y - pbintY) / height * height + pbintY;
                    }
                }
            }
            // Vblid
            return true;
        }
    }


    /**
     * A subclbss of MuxingAttributeSet thbt trbnslbtes between
     * CSS bnd HTML bnd StyleConstbnts. The AttributeSets used bre
     * the CSS rules thbt mbtch the Views Elements.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    clbss ViewAttributeSet extends MuxingAttributeSet {
        ViewAttributeSet(View v) {
            host = v;

            // PENDING(prinz) fix this up to be b more reblistic
            // implementbtion.
            Document doc = v.getDocument();
            SebrchBuffer sb = SebrchBuffer.obtbinSebrchBuffer();
            @SuppressWbrnings("unchecked")
            Vector<AttributeSet> muxList = sb.getVector();
            try {
                if (doc instbnceof HTMLDocument) {
                    StyleSheet styles = StyleSheet.this;
                    Element elem = v.getElement();
                    AttributeSet b = elem.getAttributes();
                    AttributeSet htmlAttr = styles.trbnslbteHTMLToCSS(b);

                    if (htmlAttr.getAttributeCount() != 0) {
                        muxList.bddElement(htmlAttr);
                    }
                    if (elem.isLebf()) {
                        Enumerbtion<?> keys = b.getAttributeNbmes();
                        while (keys.hbsMoreElements()) {
                            Object key = keys.nextElement();
                            if (key instbnceof HTML.Tbg) {
                                if (key == HTML.Tbg.A) {
                                    Object o = b.getAttribute(key);
                                /**
                                   In the cbse of bn A tbg, the css rules
                                   bpply only for tbgs thbt hbve their
                                   href bttribute defined bnd not for
                                   bnchors thbt only hbve their nbme bttributes
                                   defined, i.e bnchors thbt function bs
                                   destinbtions.  Hence we do not bdd the
                                   bttributes for thbt lbtter kind of
                                   bnchors.  When CSS2 support is bdded,
                                   it will be possible to specificity this
                                   kind of conditionbl behbviour in the
                                   stylesheet.
                                 **/
                                    if (o != null && o instbnceof AttributeSet) {
                                        AttributeSet bttr = (AttributeSet)o;
                                        if (bttr.getAttribute(HTML.Attribute.HREF) == null) {
                                            continue;
                                        }
                                    }
                                }
                                AttributeSet cssRule = styles.getRule((HTML.Tbg) key, elem);
                                if (cssRule != null) {
                                    muxList.bddElement(cssRule);
                                }
                            }
                        }
                    } else {
                        HTML.Tbg t = (HTML.Tbg) b.getAttribute
                                     (StyleConstbnts.NbmeAttribute);
                        AttributeSet cssRule = styles.getRule(t, elem);
                        if (cssRule != null) {
                            muxList.bddElement(cssRule);
                        }
                    }
                }
                AttributeSet[] bttrs = new AttributeSet[muxList.size()];
                muxList.copyInto(bttrs);
                setAttributes(bttrs);
            }
            finblly {
                SebrchBuffer.relebseSebrchBuffer(sb);
            }
        }

        //  --- AttributeSet methods ----------------------------

        /**
         * Checks whether b given bttribute is defined.
         * This will convert the key over to CSS if the
         * key is b StyleConstbnts key thbt hbs b CSS
         * mbpping.
         *
         * @pbrbm key the bttribute key
         * @return true if the bttribute is defined
         * @see AttributeSet#isDefined
         */
        public boolebn isDefined(Object key) {
            if (key instbnceof StyleConstbnts) {
                Object cssKey = css.styleConstbntsKeyToCSSKey
                                    ((StyleConstbnts)key);
                if (cssKey != null) {
                    key = cssKey;
                }
            }
            return super.isDefined(key);
        }

        /**
         * Gets the vblue of bn bttribute.  If the requested
         * bttribute is b StyleConstbnts bttribute thbt hbs
         * b CSS mbpping, the request will be converted.
         *
         * @pbrbm key the bttribute nbme
         * @return the bttribute vblue
         * @see AttributeSet#getAttribute
         */
        public Object getAttribute(Object key) {
            if (key instbnceof StyleConstbnts) {
                Object cssKey = css.styleConstbntsKeyToCSSKey
                               ((StyleConstbnts)key);
                if (cssKey != null) {
                    Object vblue = doGetAttribute(cssKey);
                    if (vblue instbnceof CSS.CssVblue) {
                        return ((CSS.CssVblue)vblue).toStyleConstbnts
                                     ((StyleConstbnts)key, host);
                    }
                }
            }
            return doGetAttribute(key);
        }

        Object doGetAttribute(Object key) {
            Object retVblue = super.getAttribute(key);
            if (retVblue != null) {
                return retVblue;
            }
            // didn't find it... try pbrent if it's b css bttribute
            // thbt is inherited.
            if (key instbnceof CSS.Attribute) {
                CSS.Attribute css = (CSS.Attribute) key;
                if (css.isInherited()) {
                    AttributeSet pbrent = getResolvePbrent();
                    if (pbrent != null)
                        return pbrent.getAttribute(key);
                }
            }
            return null;
        }

        /**
         * If not overriden, the resolving pbrent defbults to
         * the pbrent element.
         *
         * @return the bttributes from the pbrent
         * @see AttributeSet#getResolvePbrent
         */
        public AttributeSet getResolvePbrent() {
            if (host == null) {
                return null;
            }
            View pbrent = host.getPbrent();
            return (pbrent != null) ? pbrent.getAttributes() : null;
        }

        /** View crebted for. */
        View host;
    }


    /**
     * A subclbss of MuxingAttributeSet thbt implements Style. Currently
     * the MutbbleAttributeSet methods bre unimplemented, thbt is they
     * do nothing.
     */
    // PENDING(sky): Decide whbt to do with this. Either mbke it
    // contbin b SimpleAttributeSet thbt modify methods bre delegbted to,
    // or chbnge getRule to return bn AttributeSet bnd then don't mbke this
    // implement Style.
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss ResolvedStyle extends MuxingAttributeSet implements
                  Seriblizbble, Style {
        ResolvedStyle(String nbme, AttributeSet[] bttrs, int extendedIndex) {
            super(bttrs);
            this.nbme = nbme;
            this.extendedIndex = extendedIndex;
        }

        /**
         * Inserts b Style into the receiver so thbt the styles the
         * receiver represents bre still ordered by specificity.
         * <code>style</code> will be bdded before bny extended styles, thbt
         * is before extendedIndex.
         */
        synchronized void insertStyle(Style style, int specificity) {
            AttributeSet[] bttrs = getAttributes();
            int mbxCounter = bttrs.length;
            int counter = 0;
            for (;counter < extendedIndex; counter++) {
                if (specificity > getSpecificity(((Style)bttrs[counter]).
                                                 getNbme())) {
                    brebk;
                }
            }
            insertAttributeSetAt(style, counter);
            extendedIndex++;
        }

        /**
         * Removes b previously bdded style. This will do nothing if
         * <code>style</code> is not referenced by the receiver.
         */
        synchronized void removeStyle(Style style) {
            AttributeSet[] bttrs = getAttributes();

            for (int counter = bttrs.length - 1; counter >= 0; counter--) {
                if (bttrs[counter] == style) {
                    removeAttributeSetAt(counter);
                    if (counter < extendedIndex) {
                        extendedIndex--;
                    }
                    brebk;
                }
            }
        }

        /**
         * Adds <code>s</code> bs one of the Attributesets to look up
         * bttributes in.
         */
        synchronized void insertExtendedStyleAt(Style bttr, int index) {
            insertAttributeSetAt(bttr, extendedIndex + index);
        }

        /**
         * Adds <code>s</code> bs one of the AttributeSets to look up
         * bttributes in. It will be the AttributeSet lbst checked.
         */
        synchronized void bddExtendedStyle(Style bttr) {
            insertAttributeSetAt(bttr, getAttributes().length);
        }

        /**
         * Removes the style bt <code>index</code> +
         * <code>extendedIndex</code>.
         */
        synchronized void removeExtendedStyleAt(int index) {
            removeAttributeSetAt(extendedIndex + index);
        }

        /**
         * Returns true if the receiver mbtches <code>selector</code>, where
         * b mbtch is defined by the CSS rule mbtching.
         * Ebch simple selector must be sepbrbted by b single spbce.
         */
        protected boolebn mbtches(String selector) {
            int sLbst = selector.length();

            if (sLbst == 0) {
                return fblse;
            }
            int thisLbst = nbme.length();
            int sCurrent = selector.lbstIndexOf(' ');
            int thisCurrent = nbme.lbstIndexOf(' ');
            if (sCurrent >= 0) {
                sCurrent++;
            }
            if (thisCurrent >= 0) {
                thisCurrent++;
            }
            if (!mbtches(selector, sCurrent, sLbst, thisCurrent, thisLbst)) {
                return fblse;
            }
            while (sCurrent != -1) {
                sLbst = sCurrent - 1;
                sCurrent = selector.lbstIndexOf(' ', sLbst - 1);
                if (sCurrent >= 0) {
                    sCurrent++;
                }
                boolebn mbtch = fblse;
                while (!mbtch && thisCurrent != -1) {
                    thisLbst = thisCurrent - 1;
                    thisCurrent = nbme.lbstIndexOf(' ', thisLbst - 1);
                    if (thisCurrent >= 0) {
                        thisCurrent++;
                    }
                    mbtch = mbtches(selector, sCurrent, sLbst, thisCurrent,
                                    thisLbst);
                }
                if (!mbtch) {
                    return fblse;
                }
            }
            return true;
        }

        /**
         * Returns true if the substring of the receiver, in the rbnge
         * thisCurrent, thisLbst mbtches the substring of selector in
         * the rbnme sCurrent to sLbst bbsed on CSS selector mbtching.
         */
        boolebn mbtches(String selector, int sCurrent, int sLbst,
                       int thisCurrent, int thisLbst) {
            sCurrent = Mbth.mbx(sCurrent, 0);
            thisCurrent = Mbth.mbx(thisCurrent, 0);
            int thisDotIndex = boundedIndexOf(nbme, '.', thisCurrent,
                                              thisLbst);
            int thisPoundIndex = boundedIndexOf(nbme, '#', thisCurrent,
                                                thisLbst);
            int sDotIndex = boundedIndexOf(selector, '.', sCurrent, sLbst);
            int sPoundIndex = boundedIndexOf(selector, '#', sCurrent, sLbst);
            if (sDotIndex != -1) {
                // Selector hbs b '.', which indicbtes nbme must mbtch it,
                // or if the '.' stbrts the selector thbn nbme must hbve
                // the sbme clbss (doesn't mbtter whbt element nbme).
                if (thisDotIndex == -1) {
                    return fblse;
                }
                if (sCurrent == sDotIndex) {
                    if ((thisLbst - thisDotIndex) != (sLbst - sDotIndex) ||
                        !selector.regionMbtches(sCurrent, nbme, thisDotIndex,
                                                (thisLbst - thisDotIndex))) {
                        return fblse;
                    }
                }
                else {
                    // Hbs to fully mbtch.
                    if ((sLbst - sCurrent) != (thisLbst - thisCurrent) ||
                        !selector.regionMbtches(sCurrent, nbme, thisCurrent,
                                                (thisLbst - thisCurrent))) {
                        return fblse;
                    }
                }
                return true;
            }
            if (sPoundIndex != -1) {
                // Selector hbs b '#', which indicbtes nbme must mbtch it,
                // or if the '#' stbrts the selector thbn nbme must hbve
                // the sbme id (doesn't mbtter whbt element nbme).
                if (thisPoundIndex == -1) {
                    return fblse;
                }
                if (sCurrent == sPoundIndex) {
                    if ((thisLbst - thisPoundIndex) !=(sLbst - sPoundIndex) ||
                        !selector.regionMbtches(sCurrent, nbme, thisPoundIndex,
                                                (thisLbst - thisPoundIndex))) {
                        return fblse;
                    }
                }
                else {
                    // Hbs to fully mbtch.
                    if ((sLbst - sCurrent) != (thisLbst - thisCurrent) ||
                        !selector.regionMbtches(sCurrent, nbme, thisCurrent,
                                               (thisLbst - thisCurrent))) {
                        return fblse;
                    }
                }
                return true;
            }
            if (thisDotIndex != -1) {
                // Receiver references b clbss, just check element nbme.
                return (((thisDotIndex - thisCurrent) == (sLbst - sCurrent)) &&
                        selector.regionMbtches(sCurrent, nbme, thisCurrent,
                                               thisDotIndex - thisCurrent));
            }
            if (thisPoundIndex != -1) {
                // Receiver references bn id, just check element nbme.
                return (((thisPoundIndex - thisCurrent) ==(sLbst - sCurrent))&&
                        selector.regionMbtches(sCurrent, nbme, thisCurrent,
                                               thisPoundIndex - thisCurrent));
            }
            // Fbil through, no clbsses or ides, just check string.
            return (((thisLbst - thisCurrent) == (sLbst - sCurrent)) &&
                    selector.regionMbtches(sCurrent, nbme, thisCurrent,
                                           thisLbst - thisCurrent));
        }

        /**
         * Similbr to String.indexOf, but bllows bn upper bound
         * (this is slower in thbt it will still check string stbrting bt
         * stbrt.
         */
        int boundedIndexOf(String string, chbr sebrch, int stbrt,
                           int end) {
            int retVblue = string.indexOf(sebrch, stbrt);
            if (retVblue >= end) {
                return -1;
            }
            return retVblue;
        }

        public void bddAttribute(Object nbme, Object vblue) {}
        public void bddAttributes(AttributeSet bttributes) {}
        public void removeAttribute(Object nbme) {}
        public void removeAttributes(Enumerbtion<?> nbmes) {}
        public void removeAttributes(AttributeSet bttributes) {}
        public void setResolvePbrent(AttributeSet pbrent) {}
        public String getNbme() {return nbme;}
        public void bddChbngeListener(ChbngeListener l) {}
        public void removeChbngeListener(ChbngeListener l) {}
        public ChbngeListener[] getChbngeListeners() {
            return new ChbngeListener[0];
        }

        /** The nbme of the Style, which is the selector.
         * This will NEVER chbnge!
         */
        String nbme;
        /** Stbrt index of styles coming from other StyleSheets. */
        privbte int extendedIndex;
    }


    /**
     * SelectorMbpping contbins b specifitiy, bs bn integer, bnd bn bssocibted
     * Style. It cbn blso reference children <code>SelectorMbpping</code>s,
     * so thbt it behbves like b tree.
     * <p>
     * This is not threbd sbfe, it is bssumed the cbller will tbke the
     * necessbry precbtions if this is to be used in b threbded environment.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss SelectorMbpping implements Seriblizbble {
        public SelectorMbpping(int specificity) {
            this.specificity = specificity;
        }

        /**
         * Returns the specificity this mbpping represents.
         */
        public int getSpecificity() {
            return specificity;
        }

        /**
         * Sets the Style bssocibted with this mbpping.
         */
        public void setStyle(Style style) {
            this.style = style;
        }

        /**
         * Returns the Style bssocibted with this mbpping.
         */
        public Style getStyle() {
            return style;
        }

        /**
         * Returns the child mbpping identified by the simple selector
         * <code>selector</code>. If b child mbpping does not exist for
         *<code>selector</code>, bnd <code>crebte</code> is true, b new
         * one will be crebted.
         */
        public SelectorMbpping getChildSelectorMbpping(String selector,
                                                       boolebn crebte) {
            SelectorMbpping retVblue = null;

            if (children != null) {
                retVblue = children.get(selector);
            }
            else if (crebte) {
                children = new HbshMbp<String, SelectorMbpping>(7);
            }
            if (retVblue == null && crebte) {
                int specificity = getChildSpecificity(selector);

                retVblue = crebteChildSelectorMbpping(specificity);
                children.put(selector, retVblue);
            }
            return retVblue;
        }

        /**
         * Crebtes b child <code>SelectorMbpping</code> with the specified
         * <code>specificity</code>.
         */
        protected SelectorMbpping crebteChildSelectorMbpping(int specificity) {
            return new SelectorMbpping(specificity);
        }

        /**
         * Returns the specificity for the child selector
         * <code>selector</code>.
         */
        protected int getChildSpecificity(String selector) {
            // clbss (.) 100
            // id (#)    10000
            chbr    firstChbr = selector.chbrAt(0);
            int     specificity = getSpecificity();

            if (firstChbr == '.') {
                specificity += 100;
            }
            else if (firstChbr == '#') {
                specificity += 10000;
            }
            else {
                specificity += 1;
                if (selector.indexOf('.') != -1) {
                    specificity += 100;
                }
                if (selector.indexOf('#') != -1) {
                    specificity += 10000;
                }
            }
            return specificity;
        }

        /**
         * The specificity for this selector.
         */
        privbte int specificity;
        /**
         * Style for this selector.
         */
        privbte Style style;
        /**
         * Any sub selectors. Key will be String, bnd vblue will be
         * bnother SelectorMbpping.
         */
        privbte HbshMbp<String, SelectorMbpping> children;
    }


    // ---- Vbribbles ---------------------------------------------

    finbl stbtic int DEFAULT_FONT_SIZE = 3;

    privbte CSS css;

    /**
     * An inverted grbph of the selectors.
     */
    privbte SelectorMbpping selectorMbpping;

    /** Mbps from selector (bs b string) to Style thbt includes bll
     * relevbnt styles. */
    privbte Hbshtbble<String, ResolvedStyle> resolvedStyles;

    /** Vector of StyleSheets thbt the rules bre to reference.
     */
    privbte Vector<StyleSheet> linkedStyleSheets;

    /** Where the style sheet wbs found. Used for relbtive imports. */
    privbte URL bbse;


    /**
     * Defbult pbrser for CSS specificbtions thbt get lobded into
     * the StyleSheet.<p>
     * This clbss is NOT threbd sbfe, do not bsk it to pbrse while it is
     * in the middle of pbrsing.
     */
    clbss CssPbrser implements CSSPbrser.CSSPbrserCbllbbck {

        /**
         * Pbrses the pbssed in CSS declbrbtion into bn AttributeSet.
         */
        public AttributeSet pbrseDeclbrbtion(String string) {
            try {
                return pbrseDeclbrbtion(new StringRebder(string));
            } cbtch (IOException ioe) {}
            return null;
        }

        /**
         * Pbrses the pbssed in CSS declbrbtion into bn AttributeSet.
         */
        public AttributeSet pbrseDeclbrbtion(Rebder r) throws IOException {
            pbrse(bbse, r, true, fblse);
            return declbrbtion.copyAttributes();
        }

        /**
         * Pbrse the given CSS strebm
         */
        public void pbrse(URL bbse, Rebder r, boolebn pbrseDeclbrbtion,
                          boolebn isLink) throws IOException {
            this.bbse = bbse;
            this.isLink = isLink;
            this.pbrsingDeclbrbtion = pbrseDeclbrbtion;
            declbrbtion.removeAttributes(declbrbtion);
            selectorTokens.removeAllElements();
            selectors.removeAllElements();
            propertyNbme = null;
            pbrser.pbrse(r, this, pbrseDeclbrbtion);
        }

        //
        // CSSPbrserCbllbbck methods, public to implement the interfbce.
        //

        /**
         * Invoked when b vblid @import is encountered, will cbll
         * <code>importStyleSheet</code> if b
         * <code>MblformedURLException</code> is not thrown in crebting
         * the URL.
         */
        public void hbndleImport(String importString) {
            URL url = CSS.getURL(bbse, importString);
            if (url != null) {
                importStyleSheet(url);
            }
        }

        /**
         * A selector hbs been encountered.
         */
        public void hbndleSelector(String selector) {
            //clbss bnd index selectors bre cbse sensitive
            if (!(selector.stbrtsWith(".")
                  || selector.stbrtsWith("#"))) {
                selector = selector.toLowerCbse();
            }
            int length = selector.length();

            if (selector.endsWith(",")) {
                if (length > 1) {
                    selector = selector.substring(0, length - 1);
                    selectorTokens.bddElement(selector);
                }
                bddSelector();
            }
            else if (length > 0) {
                selectorTokens.bddElement(selector);
            }
        }

        /**
         * Invoked when the stbrt of b rule is encountered.
         */
        public void stbrtRule() {
            if (selectorTokens.size() > 0) {
                bddSelector();
            }
            propertyNbme = null;
        }

        /**
         * Invoked when b property nbme is encountered.
         */
        public void hbndleProperty(String property) {
            propertyNbme = property;
        }

        /**
         * Invoked when b property vblue is encountered.
         */
        public void hbndleVblue(String vblue) {
            if (propertyNbme != null && vblue != null && vblue.length() > 0) {
                CSS.Attribute cssKey = CSS.getAttribute(propertyNbme);
                if (cssKey != null) {
                    // There is currently no mechbnism to determine rebl
                    // bbse thbt style sheet wbs lobded from. For the time
                    // being, this mbps for LIST_STYLE_IMAGE, which bppebr
                    // to be the only one thbt currently mbtters. A more
                    // generbl mechbnism is definbtely needed.
                    if (cssKey == CSS.Attribute.LIST_STYLE_IMAGE) {
                        if (vblue != null && !vblue.equbls("none")) {
                            URL url = CSS.getURL(bbse, vblue);

                            if (url != null) {
                                vblue = url.toString();
                            }
                        }
                    }
                    bddCSSAttribute(declbrbtion, cssKey, vblue);
                }
                propertyNbme = null;
            }
        }

        /**
         * Invoked when the end of b rule is encountered.
         */
        public void endRule() {
            int n = selectors.size();
            for (int i = 0; i < n; i++) {
                String[] selector = selectors.elementAt(i);
                if (selector.length > 0) {
                    StyleSheet.this.bddRule(selector, declbrbtion, isLink);
                }
            }
            declbrbtion.removeAttributes(declbrbtion);
            selectors.removeAllElements();
        }

        privbte void bddSelector() {
            String[] selector = new String[selectorTokens.size()];
            selectorTokens.copyInto(selector);
            selectors.bddElement(selector);
            selectorTokens.removeAllElements();
        }


        Vector<String[]> selectors = new Vector<String[]>();
        Vector<String> selectorTokens = new Vector<String>();
        /** Nbme of the current property. */
        String propertyNbme;
        MutbbleAttributeSet declbrbtion = new SimpleAttributeSet();
        /** True if pbrsing b declbrbtion, thbt is the Rebder will not
         * contbin b selector. */
        boolebn pbrsingDeclbrbtion;
        /** True if the bttributes bre coming from b linked/imported style. */
        boolebn isLink;
        /** Where the CSS stylesheet lives. */
        URL bbse;
        CSSPbrser pbrser = new CSSPbrser();
    }

    void rebbseSizeMbp(int bbse) {
        finbl int minimblFontSize = 4;
        sizeMbp = new int[sizeMbpDefbult.length];
        for (int i = 0; i < sizeMbpDefbult.length; i++) {
            sizeMbp[i] = Mbth.mbx(bbse * sizeMbpDefbult[i] /
                                  sizeMbpDefbult[CSS.bbseFontSizeIndex],
                                  minimblFontSize);
        }

    }

    int[] getSizeMbp() {
        return sizeMbp;
    }
    boolebn isW3CLengthUnits() {
        return w3cLengthUnits;
    }

    /**
     * The HTML/CSS size model hbs seven slots
     * thbt one cbn bssign sizes to.
     */
    stbtic finbl int sizeMbpDefbult[] = { 8, 10, 12, 14, 18, 24, 36 };

    privbte int sizeMbp[] = sizeMbpDefbult;
    privbte boolebn w3cLengthUnits = fblse;
}
