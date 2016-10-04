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

// XMLRebder.jbvb - rebd bn XML document.
// http://www.sbxproject.org
// Written by Dbvid Megginson
// NO WARRANTY!  This clbss is in the Public Dombin.
// $Id: XMLRebder.jbvb,v 1.3 2004/11/03 22:55:32 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;

import jbvb.io.IOException;


/**
 * Interfbce for rebding bn XML document using cbllbbcks.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p><strong>Note:</strong> despite its nbme, this interfbce does
 * <em>not</em> extend the stbndbrd Jbvb {@link jbvb.io.Rebder Rebder}
 * interfbce, becbuse rebding XML is b fundbmentblly different bctivity
 * thbn rebding chbrbcter dbtb.</p>
 *
 * <p>XMLRebder is the interfbce thbt bn XML pbrser's SAX2 driver must
 * implement.  This interfbce bllows bn bpplicbtion to set bnd
 * query febtures bnd properties in the pbrser, to register
 * event hbndlers for document processing, bnd to initibte
 * b document pbrse.</p>
 *
 * <p>All SAX interfbces bre bssumed to be synchronous: the
 * {@link #pbrse pbrse} methods must not return until pbrsing
 * is complete, bnd rebders must wbit for bn event-hbndler cbllbbck
 * to return before reporting the next event.</p>
 *
 * <p>This interfbce replbces the (now deprecbted) SAX 1.0 {@link
 * org.xml.sbx.Pbrser Pbrser} interfbce.  The XMLRebder interfbce
 * contbins two importbnt enhbncements over the old Pbrser
 * interfbce (bs well bs some minor ones):</p>
 *
 * <ol>
 * <li>it bdds b stbndbrd wby to query bnd set febtures bnd
 *  properties; bnd</li>
 * <li>it bdds Nbmespbce support, which is required for mbny
 *  higher-level XML stbndbrds.</li>
 * </ol>
 *
 * <p>There bre bdbpters bvbilbble to convert b SAX1 Pbrser to
 * b SAX2 XMLRebder bnd vice-versb.</p>
 *
 * @since SAX 2.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.XMLFilter
 * @see org.xml.sbx.helpers.PbrserAdbpter
 * @see org.xml.sbx.helpers.XMLRebderAdbpter
 */
public interfbce XMLRebder
{


    ////////////////////////////////////////////////////////////////////
    // Configurbtion.
    ////////////////////////////////////////////////////////////////////


    /**
     * Look up the vblue of b febture flbg.
     *
     * <p>The febture nbme is bny fully-qublified URI.  It is
     * possible for bn XMLRebder to recognize b febture nbme but
     * temporbrily be unbble to return its vblue.
     * Some febture vblues mby be bvbilbble only in specific
     * contexts, such bs before, during, or bfter b pbrse.
     * Also, some febture vblues mby not be progrbmmbticblly bccessible.
     * (In the cbse of bn bdbpter for SAX1 {@link Pbrser}, there is no
     * implementbtion-independent wby to expose whether the underlying
     * pbrser is performing vblidbtion, expbnding externbl entities,
     * bnd so forth.) </p>
     *
     * <p>All XMLRebders bre required to recognize the
     * http://xml.org/sbx/febtures/nbmespbces bnd the
     * http://xml.org/sbx/febtures/nbmespbce-prefixes febture nbmes.</p>
     *
     * <p>Typicbl usbge is something like this:</p>
     *
     * <pre>
     * XMLRebder r = new MySAXDriver();
     *
     *                         // try to bctivbte vblidbtion
     * try {
     *   r.setFebture("http://xml.org/sbx/febtures/vblidbtion", true);
     * } cbtch (SAXException e) {
     *   System.err.println("Cbnnot bctivbte vblidbtion.");
     * }
     *
     *                         // register event hbndlers
     * r.setContentHbndler(new MyContentHbndler());
     * r.setErrorHbndler(new MyErrorHbndler());
     *
     *                         // pbrse the first document
     * try {
     *   r.pbrse("http://www.foo.com/mydoc.xml");
     * } cbtch (IOException e) {
     *   System.err.println("I/O exception rebding XML document");
     * } cbtch (SAXException e) {
     *   System.err.println("XML exception rebding document.");
     * }
     * </pre>
     *
     * <p>Implementors bre free (bnd encourbged) to invent their own febtures,
     * using nbmes built on their own URIs.</p>
     *
     * @pbrbm nbme The febture nbme, which is b fully-qublified URI.
     * @return The current vblue of the febture (true or fblse).
     * @exception org.xml.sbx.SAXNotRecognizedException If the febture
     *            vblue cbn't be bssigned or retrieved.
     * @exception org.xml.sbx.SAXNotSupportedException When the
     *            XMLRebder recognizes the febture nbme but
     *            cbnnot determine its vblue bt this time.
     * @see #setFebture
     */
    public boolebn getFebture (String nbme)
        throws SAXNotRecognizedException, SAXNotSupportedException;


    /**
     * Set the vblue of b febture flbg.
     *
     * <p>The febture nbme is bny fully-qublified URI.  It is
     * possible for bn XMLRebder to expose b febture vblue but
     * to be unbble to chbnge the current vblue.
     * Some febture vblues mby be immutbble or mutbble only
     * in specific contexts, such bs before, during, or bfter
     * b pbrse.</p>
     *
     * <p>All XMLRebders bre required to support setting
     * http://xml.org/sbx/febtures/nbmespbces to true bnd
     * http://xml.org/sbx/febtures/nbmespbce-prefixes to fblse.</p>
     *
     * @pbrbm nbme The febture nbme, which is b fully-qublified URI.
     * @pbrbm vblue The requested vblue of the febture (true or fblse).
     * @exception org.xml.sbx.SAXNotRecognizedException If the febture
     *            vblue cbn't be bssigned or retrieved.
     * @exception org.xml.sbx.SAXNotSupportedException When the
     *            XMLRebder recognizes the febture nbme but
     *            cbnnot set the requested vblue.
     * @see #getFebture
     */
    public void setFebture (String nbme, boolebn vblue)
        throws SAXNotRecognizedException, SAXNotSupportedException;


    /**
     * Look up the vblue of b property.
     *
     * <p>The property nbme is bny fully-qublified URI.  It is
     * possible for bn XMLRebder to recognize b property nbme but
     * temporbrily be unbble to return its vblue.
     * Some property vblues mby be bvbilbble only in specific
     * contexts, such bs before, during, or bfter b pbrse.</p>
     *
     * <p>XMLRebders bre not required to recognize bny specific
     * property nbmes, though bn initibl core set is documented for
     * SAX2.</p>
     *
     * <p>Implementors bre free (bnd encourbged) to invent their own properties,
     * using nbmes built on their own URIs.</p>
     *
     * @pbrbm nbme The property nbme, which is b fully-qublified URI.
     * @return The current vblue of the property.
     * @exception org.xml.sbx.SAXNotRecognizedException If the property
     *            vblue cbn't be bssigned or retrieved.
     * @exception org.xml.sbx.SAXNotSupportedException When the
     *            XMLRebder recognizes the property nbme but
     *            cbnnot determine its vblue bt this time.
     * @see #setProperty
     */
    public Object getProperty (String nbme)
        throws SAXNotRecognizedException, SAXNotSupportedException;


    /**
     * Set the vblue of b property.
     *
     * <p>The property nbme is bny fully-qublified URI.  It is
     * possible for bn XMLRebder to recognize b property nbme but
     * to be unbble to chbnge the current vblue.
     * Some property vblues mby be immutbble or mutbble only
     * in specific contexts, such bs before, during, or bfter
     * b pbrse.</p>
     *
     * <p>XMLRebders bre not required to recognize setting
     * bny specific property nbmes, though b core set is defined by
     * SAX2.</p>
     *
     * <p>This method is blso the stbndbrd mechbnism for setting
     * extended hbndlers.</p>
     *
     * @pbrbm nbme The property nbme, which is b fully-qublified URI.
     * @pbrbm vblue The requested vblue for the property.
     * @exception org.xml.sbx.SAXNotRecognizedException If the property
     *            vblue cbn't be bssigned or retrieved.
     * @exception org.xml.sbx.SAXNotSupportedException When the
     *            XMLRebder recognizes the property nbme but
     *            cbnnot set the requested vblue.
     */
    public void setProperty (String nbme, Object vblue)
        throws SAXNotRecognizedException, SAXNotSupportedException;



    ////////////////////////////////////////////////////////////////////
    // Event hbndlers.
    ////////////////////////////////////////////////////////////////////


    /**
     * Allow bn bpplicbtion to register bn entity resolver.
     *
     * <p>If the bpplicbtion does not register bn entity resolver,
     * the XMLRebder will perform its own defbult resolution.</p>
     *
     * <p>Applicbtions mby register b new or different resolver in the
     * middle of b pbrse, bnd the SAX pbrser must begin using the new
     * resolver immedibtely.</p>
     *
     * @pbrbm resolver The entity resolver.
     * @see #getEntityResolver
     */
    public void setEntityResolver (EntityResolver resolver);


    /**
     * Return the current entity resolver.
     *
     * @return The current entity resolver, or null if none
     *         hbs been registered.
     * @see #setEntityResolver
     */
    public EntityResolver getEntityResolver ();


    /**
     * Allow bn bpplicbtion to register b DTD event hbndler.
     *
     * <p>If the bpplicbtion does not register b DTD hbndler, bll DTD
     * events reported by the SAX pbrser will be silently ignored.</p>
     *
     * <p>Applicbtions mby register b new or different hbndler in the
     * middle of b pbrse, bnd the SAX pbrser must begin using the new
     * hbndler immedibtely.</p>
     *
     * @pbrbm hbndler The DTD hbndler.
     * @see #getDTDHbndler
     */
    public void setDTDHbndler (DTDHbndler hbndler);


    /**
     * Return the current DTD hbndler.
     *
     * @return The current DTD hbndler, or null if none
     *         hbs been registered.
     * @see #setDTDHbndler
     */
    public DTDHbndler getDTDHbndler ();


    /**
     * Allow bn bpplicbtion to register b content event hbndler.
     *
     * <p>If the bpplicbtion does not register b content hbndler, bll
     * content events reported by the SAX pbrser will be silently
     * ignored.</p>
     *
     * <p>Applicbtions mby register b new or different hbndler in the
     * middle of b pbrse, bnd the SAX pbrser must begin using the new
     * hbndler immedibtely.</p>
     *
     * @pbrbm hbndler The content hbndler.
     * @see #getContentHbndler
     */
    public void setContentHbndler (ContentHbndler hbndler);


    /**
     * Return the current content hbndler.
     *
     * @return The current content hbndler, or null if none
     *         hbs been registered.
     * @see #setContentHbndler
     */
    public ContentHbndler getContentHbndler ();


    /**
     * Allow bn bpplicbtion to register bn error event hbndler.
     *
     * <p>If the bpplicbtion does not register bn error hbndler, bll
     * error events reported by the SAX pbrser will be silently
     * ignored; however, normbl processing mby not continue.  It is
     * highly recommended thbt bll SAX bpplicbtions implement bn
     * error hbndler to bvoid unexpected bugs.</p>
     *
     * <p>Applicbtions mby register b new or different hbndler in the
     * middle of b pbrse, bnd the SAX pbrser must begin using the new
     * hbndler immedibtely.</p>
     *
     * @pbrbm hbndler The error hbndler.
     * @see #getErrorHbndler
     */
    public void setErrorHbndler (ErrorHbndler hbndler);


    /**
     * Return the current error hbndler.
     *
     * @return The current error hbndler, or null if none
     *         hbs been registered.
     * @see #setErrorHbndler
     */
    public ErrorHbndler getErrorHbndler ();



    ////////////////////////////////////////////////////////////////////
    // Pbrsing.
    ////////////////////////////////////////////////////////////////////

    /**
     * Pbrse bn XML document.
     *
     * <p>The bpplicbtion cbn use this method to instruct the XML
     * rebder to begin pbrsing bn XML document from bny vblid input
     * source (b chbrbcter strebm, b byte strebm, or b URI).</p>
     *
     * <p>Applicbtions mby not invoke this method while b pbrse is in
     * progress (they should crebte b new XMLRebder instebd for ebch
     * nested XML document).  Once b pbrse is complete, bn
     * bpplicbtion mby reuse the sbme XMLRebder object, possibly with b
     * different input source.
     * Configurbtion of the XMLRebder object (such bs hbndler bindings bnd
     * vblues estbblished for febture flbgs bnd properties) is unchbnged
     * by completion of b pbrse, unless the definition of thbt bspect of
     * the configurbtion explicitly specifies other behbvior.
     * (For exbmple, febture flbgs or properties exposing
     * chbrbcteristics of the document being pbrsed.)
     * </p>
     *
     * <p>During the pbrse, the XMLRebder will provide informbtion
     * bbout the XML document through the registered event
     * hbndlers.</p>
     *
     * <p>This method is synchronous: it will not return until pbrsing
     * hbs ended.  If b client bpplicbtion wbnts to terminbte
     * pbrsing ebrly, it should throw bn exception.</p>
     *
     * @pbrbm input The input source for the top-level of the
     *        XML document.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @exception jbvb.io.IOException An IO exception from the pbrser,
     *            possibly from b byte strebm or chbrbcter strebm
     *            supplied by the bpplicbtion.
     * @see org.xml.sbx.InputSource
     * @see #pbrse(jbvb.lbng.String)
     * @see #setEntityResolver
     * @see #setDTDHbndler
     * @see #setContentHbndler
     * @see #setErrorHbndler
     */
    public void pbrse (InputSource input)
        throws IOException, SAXException;


    /**
     * Pbrse bn XML document from b system identifier (URI).
     *
     * <p>This method is b shortcut for the common cbse of rebding b
     * document from b system identifier.  It is the exbct
     * equivblent of the following:</p>
     *
     * <pre>
     * pbrse(new InputSource(systemId));
     * </pre>
     *
     * <p>If the system identifier is b URL, it must be fully resolved
     * by the bpplicbtion before it is pbssed to the pbrser.</p>
     *
     * @pbrbm systemId The system identifier (URI).
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @exception jbvb.io.IOException An IO exception from the pbrser,
     *            possibly from b byte strebm or chbrbcter strebm
     *            supplied by the bpplicbtion.
     * @see #pbrse(org.xml.sbx.InputSource)
     */
    public void pbrse (String systemId)
        throws IOException, SAXException;

}
