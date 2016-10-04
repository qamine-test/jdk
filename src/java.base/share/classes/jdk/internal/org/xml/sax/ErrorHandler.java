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

// SAX error hbndler.
// http://www.sbxproject.org
// No wbrrbnty; no copyright -- use this bs you will.
// $Id: ErrorHbndler.jbvb,v 1.2 2004/11/03 22:44:52 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;


/**
 * Bbsic interfbce for SAX error hbndlers.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>If b SAX bpplicbtion needs to implement customized error
 * hbndling, it must implement this interfbce bnd then register bn
 * instbnce with the XML rebder using the
 * {@link org.xml.sbx.XMLRebder#setErrorHbndler setErrorHbndler}
 * method.  The pbrser will then report bll errors bnd wbrnings
 * through this interfbce.</p>
 *
 * <p><strong>WARNING:</strong> If bn bpplicbtion does <em>not</em>
 * register bn ErrorHbndler, XML pbrsing errors will go unreported,
 * except thbt <em>SAXPbrseException</em>s will be thrown for fbtbl errors.
 * In order to detect vblidity errors, bn ErrorHbndler thbt does something
 * with {@link #error error()} cblls must be registered.</p>
 *
 * <p>For XML processing errors, b SAX driver must use this interfbce
 * in preference to throwing bn exception: it is up to the bpplicbtion
 * to decide whether to throw bn exception for different types of
 * errors bnd wbrnings.  Note, however, thbt there is no requirement thbt
 * the pbrser continue to report bdditionbl errors bfter b cbll to
 * {@link #fbtblError fbtblError}.  In other words, b SAX driver clbss
 * mby throw bn exception bfter reporting bny fbtblError.
 * Also pbrsers mby throw bppropribte exceptions for non-XML errors.
 * For exbmple, {@link XMLRebder#pbrse XMLRebder.pbrse()} would throw
 * bn IOException for errors bccessing entities or the document.</p>
 *
 * @since SAX 1.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.XMLRebder#setErrorHbndler
 * @see org.xml.sbx.SAXPbrseException
 */
public interfbce ErrorHbndler {


    /**
     * Receive notificbtion of b wbrning.
     *
     * <p>SAX pbrsers will use this method to report conditions thbt
     * bre not errors or fbtbl errors bs defined by the XML
     * recommendbtion.  The defbult behbviour is to tbke no
     * bction.</p>
     *
     * <p>The SAX pbrser must continue to provide normbl pbrsing events
     * bfter invoking this method: it should still be possible for the
     * bpplicbtion to process the document through to the end.</p>
     *
     * <p>Filters mby use this method to report other, non-XML wbrnings
     * bs well.</p>
     *
     * @pbrbm exception The wbrning informbtion encbpsulbted in b
     *                  SAX pbrse exception.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.SAXPbrseException
     */
    public bbstrbct void wbrning (SAXPbrseException exception)
        throws SAXException;


    /**
     * Receive notificbtion of b recoverbble error.
     *
     * <p>This corresponds to the definition of "error" in section 1.2
     * of the W3C XML 1.0 Recommendbtion.  For exbmple, b vblidbting
     * pbrser would use this cbllbbck to report the violbtion of b
     * vblidity constrbint.  The defbult behbviour is to tbke no
     * bction.</p>
     *
     * <p>The SAX pbrser must continue to provide normbl pbrsing
     * events bfter invoking this method: it should still be possible
     * for the bpplicbtion to process the document through to the end.
     * If the bpplicbtion cbnnot do so, then the pbrser should report
     * b fbtbl error even if the XML recommendbtion does not require
     * it to do so.</p>
     *
     * <p>Filters mby use this method to report other, non-XML errors
     * bs well.</p>
     *
     * @pbrbm exception The error informbtion encbpsulbted in b
     *                  SAX pbrse exception.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.SAXPbrseException
     */
    public bbstrbct void error (SAXPbrseException exception)
        throws SAXException;


    /**
     * Receive notificbtion of b non-recoverbble error.
     *
     * <p><strong>There is bn bppbrent contrbdiction between the
     * documentbtion for this method bnd the documentbtion for {@link
     * org.xml.sbx.ContentHbndler#endDocument}.  Until this bmbiguity
     * is resolved in b future mbjor relebse, clients should mbke no
     * bssumptions bbout whether endDocument() will or will not be
     * invoked when the pbrser hbs reported b fbtblError() or thrown
     * bn exception.</strong></p>
     *
     * <p>This corresponds to the definition of "fbtbl error" in
     * section 1.2 of the W3C XML 1.0 Recommendbtion.  For exbmple, b
     * pbrser would use this cbllbbck to report the violbtion of b
     * well-formedness constrbint.</p>
     *
     * <p>The bpplicbtion must bssume thbt the document is unusbble
     * bfter the pbrser hbs invoked this method, bnd should continue
     * (if bt bll) only for the sbke of collecting bdditionbl error
     * messbges: in fbct, SAX pbrsers bre free to stop reporting bny
     * other events once this method hbs been invoked.</p>
     *
     * @pbrbm exception The error informbtion encbpsulbted in b
     *                  SAX pbrse exception.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.SAXPbrseException
     */
    public bbstrbct void fbtblError (SAXPbrseException exception)
        throws SAXException;

}

// end of ErrorHbndler.jbvb
