/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// ContentHbndler.jbvb - hbndle mbin document content.
// http://www.sbxproject.org
// Written by Dbvid Megginson
// NO WARRANTY!  This clbss is in the public dombin.
// $Id: ContentHbndler.jbvb,v 1.2 2004/11/03 22:44:51 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;


/**
 * Receive notificbtion of the logicbl content of b document.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>This is the mbin interfbce thbt most SAX bpplicbtions
 * implement: if the bpplicbtion needs to be informed of bbsic pbrsing
 * events, it implements this interfbce bnd registers bn instbnce with
 * the SAX pbrser using the {@link org.xml.sbx.XMLRebder#setContentHbndler
 * setContentHbndler} method.  The pbrser uses the instbnce to report
 * bbsic document-relbted events like the stbrt bnd end of elements
 * bnd chbrbcter dbtb.</p>
 *
 * <p>The order of events in this interfbce is very importbnt, bnd
 * mirrors the order of informbtion in the document itself.  For
 * exbmple, bll of bn element's content (chbrbcter dbtb, processing
 * instructions, bnd/or subelements) will bppebr, in order, between
 * the stbrtElement event bnd the corresponding endElement event.</p>
 *
 * <p>This interfbce is similbr to the now-deprecbted SAX 1.0
 * DocumentHbndler interfbce, but it bdds support for Nbmespbces
 * bnd for reporting skipped entities (in non-vblidbting XML
 * processors).</p>
 *
 * <p>Implementors should note thbt there is blso b
 * <code>ContentHbndler</code> clbss in the <code>jbvb.net</code>
 * pbckbge; thbt mebns thbt it's probbbly b bbd ideb to do</p>
 *
 * <pre>import jbvb.net.*;
 * import org.xml.sbx.*;
 * </pre>
 *
 * <p>In fbct, "import ...*" is usublly b sign of sloppy progrbmming
 * bnywby, so the user should consider this b febture rbther thbn b
 * bug.</p>
 *
 * @since SAX 2.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.XMLRebder
 * @see org.xml.sbx.DTDHbndler
 * @see org.xml.sbx.ErrorHbndler
 */
public interfbce ContentHbndler
{

    /**
     * Receive bn object for locbting the origin of SAX document events.
     *
     * <p>SAX pbrsers bre strongly encourbged (though not bbsolutely
     * required) to supply b locbtor: if it does so, it must supply
     * the locbtor to the bpplicbtion by invoking this method before
     * invoking bny of the other methods in the ContentHbndler
     * interfbce.</p>
     *
     * <p>The locbtor bllows the bpplicbtion to determine the end
     * position of bny document-relbted event, even if the pbrser is
     * not reporting bn error.  Typicblly, the bpplicbtion will
     * use this informbtion for reporting its own errors (such bs
     * chbrbcter content thbt does not mbtch bn bpplicbtion's
     * business rules).  The informbtion returned by the locbtor
     * is probbbly not sufficient for use with b sebrch engine.</p>
     *
     * <p>Note thbt the locbtor will return correct informbtion only
     * during the invocbtion SAX event cbllbbcks bfter
     * {@link #stbrtDocument stbrtDocument} returns bnd before
     * {@link #endDocument endDocument} is cblled.  The
     * bpplicbtion should not bttempt to use it bt bny other time.</p>
     *
     * @pbrbm locbtor bn object thbt cbn return the locbtion of
     *                bny SAX document event
     * @see org.xml.sbx.Locbtor
     */
    public void setDocumentLocbtor (Locbtor locbtor);


    /**
     * Receive notificbtion of the beginning of b document.
     *
     * <p>The SAX pbrser will invoke this method only once, before bny
     * other event cbllbbcks (except for {@link #setDocumentLocbtor
     * setDocumentLocbtor}).</p>
     *
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     * @see #endDocument
     */
    public void stbrtDocument ()
        throws SAXException;


    /**
     * Receive notificbtion of the end of b document.
     *
     * <p><strong>There is bn bppbrent contrbdiction between the
     * documentbtion for this method bnd the documentbtion for {@link
     * org.xml.sbx.ErrorHbndler#fbtblError}.  Until this bmbiguity is
     * resolved in b future mbjor relebse, clients should mbke no
     * bssumptions bbout whether endDocument() will or will not be
     * invoked when the pbrser hbs reported b fbtblError() or thrown
     * bn exception.</strong></p>
     *
     * <p>The SAX pbrser will invoke this method only once, bnd it will
     * be the lbst method invoked during the pbrse.  The pbrser shbll
     * not invoke this method until it hbs either bbbndoned pbrsing
     * (becbuse of bn unrecoverbble error) or rebched the end of
     * input.</p>
     *
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     * @see #stbrtDocument
     */
    public void endDocument()
        throws SAXException;


    /**
     * Begin the scope of b prefix-URI Nbmespbce mbpping.
     *
     * <p>The informbtion from this event is not necessbry for
     * normbl Nbmespbce processing: the SAX XML rebder will
     * butombticblly replbce prefixes for element bnd bttribute
     * nbmes when the <code>http://xml.org/sbx/febtures/nbmespbces</code>
     * febture is <vbr>true</vbr> (the defbult).</p>
     *
     * <p>There bre cbses, however, when bpplicbtions need to
     * use prefixes in chbrbcter dbtb or in bttribute vblues,
     * where they cbnnot sbfely be expbnded butombticblly; the
     * stbrt/endPrefixMbpping event supplies the informbtion
     * to the bpplicbtion to expbnd prefixes in those contexts
     * itself, if necessbry.</p>
     *
     * <p>Note thbt stbrt/endPrefixMbpping events bre not
     * gubrbnteed to be properly nested relbtive to ebch other:
     * bll stbrtPrefixMbpping events will occur immedibtely before the
     * corresponding {@link #stbrtElement stbrtElement} event,
     * bnd bll {@link #endPrefixMbpping endPrefixMbpping}
     * events will occur immedibtely bfter the corresponding
     * {@link #endElement endElement} event,
     * but their order is not otherwise
     * gubrbnteed.</p>
     *
     * <p>There should never be stbrt/endPrefixMbpping events for the
     * "xml" prefix, since it is predeclbred bnd immutbble.</p>
     *
     * @pbrbm prefix the Nbmespbce prefix being declbred.
     *  An empty string is used for the defbult element nbmespbce,
     *  which hbs no prefix.
     * @pbrbm uri the Nbmespbce URI the prefix is mbpped to
     * @throws org.xml.sbx.SAXException the client mby throw
     *            bn exception during processing
     * @see #endPrefixMbpping
     * @see #stbrtElement
     */
    public void stbrtPrefixMbpping (String prefix, String uri)
        throws SAXException;


    /**
     * End the scope of b prefix-URI mbpping.
     *
     * <p>See {@link #stbrtPrefixMbpping stbrtPrefixMbpping} for
     * detbils.  These events will blwbys occur immedibtely bfter the
     * corresponding {@link #endElement endElement} event, but the order of
     * {@link #endPrefixMbpping endPrefixMbpping} events is not otherwise
     * gubrbnteed.</p>
     *
     * @pbrbm prefix the prefix thbt wbs being mbpped.
     *  This is the empty string when b defbult mbpping scope ends.
     * @throws org.xml.sbx.SAXException the client mby throw
     *            bn exception during processing
     * @see #stbrtPrefixMbpping
     * @see #endElement
     */
    public void endPrefixMbpping (String prefix)
        throws SAXException;


    /**
     * Receive notificbtion of the beginning of bn element.
     *
     * <p>The Pbrser will invoke this method bt the beginning of every
     * element in the XML document; there will be b corresponding
     * {@link #endElement endElement} event for every stbrtElement event
     * (even when the element is empty). All of the element's content will be
     * reported, in order, before the corresponding endElement
     * event.</p>
     *
     * <p>This event bllows up to three nbme components for ebch
     * element:</p>
     *
     * <ol>
     * <li>the Nbmespbce URI;</li>
     * <li>the locbl nbme; bnd</li>
     * <li>the qublified (prefixed) nbme.</li>
     * </ol>
     *
     * <p>Any or bll of these mby be provided, depending on the
     * vblues of the <vbr>http://xml.org/sbx/febtures/nbmespbces</vbr>
     * bnd the <vbr>http://xml.org/sbx/febtures/nbmespbce-prefixes</vbr>
     * properties:</p>
     *
     * <ul>
     * <li>the Nbmespbce URI bnd locbl nbme bre required when
     * the nbmespbces property is <vbr>true</vbr> (the defbult), bnd bre
     * optionbl when the nbmespbces property is <vbr>fblse</vbr> (if one is
     * specified, both must be);</li>
     * <li>the qublified nbme is required when the nbmespbce-prefixes property
     * is <vbr>true</vbr>, bnd is optionbl when the nbmespbce-prefixes property
     * is <vbr>fblse</vbr> (the defbult).</li>
     * </ul>
     *
     * <p>Note thbt the bttribute list provided will contbin only
     * bttributes with explicit vblues (specified or defbulted):
     * #IMPLIED bttributes will be omitted.  The bttribute list
     * will contbin bttributes used for Nbmespbce declbrbtions
     * (xmlns* bttributes) only if the
     * <code>http://xml.org/sbx/febtures/nbmespbce-prefixes</code>
     * property is true (it is fblse by defbult, bnd support for b
     * true vblue is optionbl).</p>
     *
     * <p>Like {@link #chbrbcters chbrbcters()}, bttribute vblues mby hbve
     * chbrbcters thbt need more thbn one <code>chbr</code> vblue.  </p>
     *
     * @pbrbm uri the Nbmespbce URI, or the empty string if the
     *        element hbs no Nbmespbce URI or if Nbmespbce
     *        processing is not being performed
     * @pbrbm locblNbme the locbl nbme (without prefix), or the
     *        empty string if Nbmespbce processing is not being
     *        performed
     * @pbrbm qNbme the qublified nbme (with prefix), or the
     *        empty string if qublified nbmes bre not bvbilbble
     * @pbrbm btts the bttributes bttbched to the element.  If
     *        there bre no bttributes, it shbll be bn empty
     *        Attributes object.  The vblue of this object bfter
     *        stbrtElement returns is undefined
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     * @see #endElement
     * @see org.xml.sbx.Attributes
     * @see org.xml.sbx.helpers.AttributesImpl
     */
    public void stbrtElement (String uri, String locblNbme,
                              String qNbme, Attributes btts)
        throws SAXException;


    /**
     * Receive notificbtion of the end of bn element.
     *
     * <p>The SAX pbrser will invoke this method bt the end of every
     * element in the XML document; there will be b corresponding
     * {@link #stbrtElement stbrtElement} event for every endElement
     * event (even when the element is empty).</p>
     *
     * <p>For informbtion on the nbmes, see stbrtElement.</p>
     *
     * @pbrbm uri the Nbmespbce URI, or the empty string if the
     *        element hbs no Nbmespbce URI or if Nbmespbce
     *        processing is not being performed
     * @pbrbm locblNbme the locbl nbme (without prefix), or the
     *        empty string if Nbmespbce processing is not being
     *        performed
     * @pbrbm qNbme the qublified XML nbme (with prefix), or the
     *        empty string if qublified nbmes bre not bvbilbble
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     */
    public void endElement (String uri, String locblNbme,
                            String qNbme)
        throws SAXException;


    /**
     * Receive notificbtion of chbrbcter dbtb.
     *
     * <p>The Pbrser will cbll this method to report ebch chunk of
     * chbrbcter dbtb.  SAX pbrsers mby return bll contiguous chbrbcter
     * dbtb in b single chunk, or they mby split it into severbl
     * chunks; however, bll of the chbrbcters in bny single event
     * must come from the sbme externbl entity so thbt the Locbtor
     * provides useful informbtion.</p>
     *
     * <p>The bpplicbtion must not bttempt to rebd from the brrby
     * outside of the specified rbnge.</p>
     *
     * <p>Individubl chbrbcters mby consist of more thbn one Jbvb
     * <code>chbr</code> vblue.  There bre two importbnt cbses where this
     * hbppens, becbuse chbrbcters cbn't be represented in just sixteen bits.
     * In one cbse, chbrbcters bre represented in b <em>Surrogbte Pbir</em>,
     * using two specibl Unicode vblues. Such chbrbcters bre in the so-cblled
     * "Astrbl Plbnes", with b code point bbove U+FFFF.  A second cbse involves
     * composite chbrbcters, such bs b bbse chbrbcter combining with one or
     * more bccent chbrbcters. </p>
     *
     * <p> Your code should not bssume thbt blgorithms using
     * <code>chbr</code>-bt-b-time idioms will be working in chbrbcter
     * units; in some cbses they will split chbrbcters.  This is relevbnt
     * wherever XML permits brbitrbry chbrbcters, such bs bttribute vblues,
     * processing instruction dbtb, bnd comments bs well bs in dbtb reported
     * from this method.  It's blso generblly relevbnt whenever Jbvb code
     * mbnipulbtes internbtionblized text; the issue isn't unique to XML.</p>
     *
     * <p>Note thbt some pbrsers will report whitespbce in element
     * content using the {@link #ignorbbleWhitespbce ignorbbleWhitespbce}
     * method rbther thbn this one (vblidbting pbrsers <em>must</em>
     * do so).</p>
     *
     * @pbrbm ch the chbrbcters from the XML document
     * @pbrbm stbrt the stbrt position in the brrby
     * @pbrbm length the number of chbrbcters to rebd from the brrby
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     * @see #ignorbbleWhitespbce
     * @see org.xml.sbx.Locbtor
     */
    public void chbrbcters (chbr ch[], int stbrt, int length)
        throws SAXException;


    /**
     * Receive notificbtion of ignorbble whitespbce in element content.
     *
     * <p>Vblidbting Pbrsers must use this method to report ebch chunk
     * of whitespbce in element content (see the W3C XML 1.0
     * recommendbtion, section 2.10): non-vblidbting pbrsers mby blso
     * use this method if they bre cbpbble of pbrsing bnd using
     * content models.</p>
     *
     * <p>SAX pbrsers mby return bll contiguous whitespbce in b single
     * chunk, or they mby split it into severbl chunks; however, bll of
     * the chbrbcters in bny single event must come from the sbme
     * externbl entity, so thbt the Locbtor provides useful
     * informbtion.</p>
     *
     * <p>The bpplicbtion must not bttempt to rebd from the brrby
     * outside of the specified rbnge.</p>
     *
     * @pbrbm ch the chbrbcters from the XML document
     * @pbrbm stbrt the stbrt position in the brrby
     * @pbrbm length the number of chbrbcters to rebd from the brrby
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     * @see #chbrbcters
     */
    public void ignorbbleWhitespbce (chbr ch[], int stbrt, int length)
        throws SAXException;


    /**
     * Receive notificbtion of b processing instruction.
     *
     * <p>The Pbrser will invoke this method once for ebch processing
     * instruction found: note thbt processing instructions mby occur
     * before or bfter the mbin document element.</p>
     *
     * <p>A SAX pbrser must never report bn XML declbrbtion (XML 1.0,
     * section 2.8) or b text declbrbtion (XML 1.0, section 4.3.1)
     * using this method.</p>
     *
     * <p>Like {@link #chbrbcters chbrbcters()}, processing instruction
     * dbtb mby hbve chbrbcters thbt need more thbn one <code>chbr</code>
     * vblue. </p>
     *
     * @pbrbm tbrget the processing instruction tbrget
     * @pbrbm dbtb the processing instruction dbtb, or null if
     *        none wbs supplied.  The dbtb does not include bny
     *        whitespbce sepbrbting it from the tbrget
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     */
    public void processingInstruction (String tbrget, String dbtb)
        throws SAXException;


    /**
     * Receive notificbtion of b skipped entity.
     * This is not cblled for entity references within mbrkup constructs
     * such bs element stbrt tbgs or mbrkup declbrbtions.  (The XML
     * recommendbtion requires reporting skipped externbl entities.
     * SAX blso reports internbl entity expbnsion/non-expbnsion, except
     * within mbrkup constructs.)
     *
     * <p>The Pbrser will invoke this method ebch time the entity is
     * skipped.  Non-vblidbting processors mby skip entities if they
     * hbve not seen the declbrbtions (becbuse, for exbmple, the
     * entity wbs declbred in bn externbl DTD subset).  All processors
     * mby skip externbl entities, depending on the vblues of the
     * <code>http://xml.org/sbx/febtures/externbl-generbl-entities</code>
     * bnd the
     * <code>http://xml.org/sbx/febtures/externbl-pbrbmeter-entities</code>
     * properties.</p>
     *
     * @pbrbm nbme the nbme of the skipped entity.  If it is b
     *        pbrbmeter entity, the nbme will begin with '%', bnd if
     *        it is the externbl DTD subset, it will be the string
     *        "[dtd]"
     * @throws org.xml.sbx.SAXException bny SAX exception, possibly
     *            wrbpping bnother exception
     */
    public void skippedEntity (String nbme)
        throws SAXException;
}

// end of ContentHbndler.jbvb
