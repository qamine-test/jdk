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

// DefbultHbndler.jbvb - defbult implementbtion of the core hbndlers.
// http://www.sbxproject.org
// Written by Dbvid Megginson
// NO WARRANTY!  This clbss is in the public dombin.
// $Id: DefbultHbndler.jbvb,v 1.3 2006/04/13 02:06:32 jeffsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx.helpers;

import jbvb.io.IOException;

import jdk.internbl.org.xml.sbx.InputSource;
import jdk.internbl.org.xml.sbx.Locbtor;
import jdk.internbl.org.xml.sbx.Attributes;
import jdk.internbl.org.xml.sbx.EntityResolver;
import jdk.internbl.org.xml.sbx.DTDHbndler;
import jdk.internbl.org.xml.sbx.ContentHbndler;
import jdk.internbl.org.xml.sbx.ErrorHbndler;
import jdk.internbl.org.xml.sbx.SAXException;
import jdk.internbl.org.xml.sbx.SAXPbrseException;


/**
 * Defbult bbse clbss for SAX2 event hbndlers.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>This clbss is bvbilbble bs b convenience bbse clbss for SAX2
 * bpplicbtions: it provides defbult implementbtions for bll of the
 * cbllbbcks in the four core SAX2 hbndler clbsses:</p>
 *
 * <ul>
 * <li>{@link org.xml.sbx.EntityResolver EntityResolver}</li>
 * <li>{@link org.xml.sbx.DTDHbndler DTDHbndler}</li>
 * <li>{@link org.xml.sbx.ContentHbndler ContentHbndler}</li>
 * <li>{@link org.xml.sbx.ErrorHbndler ErrorHbndler}</li>
 * </ul>
 *
 * <p>Applicbtion writers cbn extend this clbss when they need to
 * implement only pbrt of bn interfbce; pbrser writers cbn
 * instbntibte this clbss to provide defbult hbndlers when the
 * bpplicbtion hbs not supplied its own.</p>
 *
 * <p>This clbss replbces the deprecbted SAX1
 * {@link org.xml.sbx.HbndlerBbse HbndlerBbse} clbss.</p>
 *
 * @since SAX 2.0
 * @buthor Dbvid Megginson,
 * @see org.xml.sbx.EntityResolver
 * @see org.xml.sbx.DTDHbndler
 * @see org.xml.sbx.ContentHbndler
 * @see org.xml.sbx.ErrorHbndler
 */
public clbss DefbultHbndler
    implements EntityResolver, DTDHbndler, ContentHbndler, ErrorHbndler
{


    ////////////////////////////////////////////////////////////////////
    // Defbult implementbtion of the EntityResolver interfbce.
    ////////////////////////////////////////////////////////////////////

    /**
     * Resolve bn externbl entity.
     *
     * <p>Alwbys return null, so thbt the pbrser will use the system
     * identifier provided in the XML document.  This method implements
     * the SAX defbult behbviour: bpplicbtion writers cbn override it
     * in b subclbss to do specibl trbnslbtions such bs cbtblog lookups
     * or URI redirection.</p>
     *
     * @pbrbm publicId The public identifier, or null if none is
     *                 bvbilbble.
     * @pbrbm systemId The system identifier provided in the XML
     *                 document.
     * @return The new input source, or null to require the
     *         defbult behbviour.
     * @exception jbvb.io.IOException If there is bn error setting
     *            up the new input source.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.EntityResolver#resolveEntity
     */
    public InputSource resolveEntity (String publicId, String systemId)
        throws IOException, SAXException
    {
        return null;
    }



    ////////////////////////////////////////////////////////////////////
    // Defbult implementbtion of DTDHbndler interfbce.
    ////////////////////////////////////////////////////////////////////


    /**
     * Receive notificbtion of b notbtion declbrbtion.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss if they wish to keep trbck of the notbtions
     * declbred in b document.</p>
     *
     * @pbrbm nbme The notbtion nbme.
     * @pbrbm publicId The notbtion public identifier, or null if not
     *                 bvbilbble.
     * @pbrbm systemId The notbtion system identifier.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.DTDHbndler#notbtionDecl
     */
    public void notbtionDecl (String nbme, String publicId, String systemId)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of bn unpbrsed entity declbrbtion.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to keep trbck of the unpbrsed entities
     * declbred in b document.</p>
     *
     * @pbrbm nbme The entity nbme.
     * @pbrbm publicId The entity public identifier, or null if not
     *                 bvbilbble.
     * @pbrbm systemId The entity system identifier.
     * @pbrbm notbtionNbme The nbme of the bssocibted notbtion.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.DTDHbndler#unpbrsedEntityDecl
     */
    public void unpbrsedEntityDecl (String nbme, String publicId,
                                    String systemId, String notbtionNbme)
        throws SAXException
    {
        // no op
    }



    ////////////////////////////////////////////////////////////////////
    // Defbult implementbtion of ContentHbndler interfbce.
    ////////////////////////////////////////////////////////////////////


    /**
     * Receive b Locbtor object for document events.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss if they wish to store the locbtor for use
     * with other document events.</p>
     *
     * @pbrbm locbtor A locbtor for bll SAX document events.
     * @see org.xml.sbx.ContentHbndler#setDocumentLocbtor
     * @see org.xml.sbx.Locbtor
     */
    public void setDocumentLocbtor (Locbtor locbtor)
    {
        // no op
    }


    /**
     * Receive notificbtion of the beginning of the document.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions bt the beginning
     * of b document (such bs bllocbting the root node of b tree or
     * crebting bn output file).</p>
     *
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#stbrtDocument
     */
    public void stbrtDocument ()
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of the end of the document.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions bt the end
     * of b document (such bs finblising b tree or closing bn output
     * file).</p>
     *
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#endDocument
     */
    public void endDocument ()
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of the stbrt of b Nbmespbce mbpping.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions bt the stbrt of
     * ebch Nbmespbce prefix scope (such bs storing the prefix mbpping).</p>
     *
     * @pbrbm prefix The Nbmespbce prefix being declbred.
     * @pbrbm uri The Nbmespbce URI mbpped to the prefix.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#stbrtPrefixMbpping
     */
    public void stbrtPrefixMbpping (String prefix, String uri)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of the end of b Nbmespbce mbpping.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions bt the end of
     * ebch prefix mbpping.</p>
     *
     * @pbrbm prefix The Nbmespbce prefix being declbred.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#endPrefixMbpping
     */
    public void endPrefixMbpping (String prefix)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of the stbrt of bn element.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions bt the stbrt of
     * ebch element (such bs bllocbting b new tree node or writing
     * output to b file).</p>
     *
     * @pbrbm uri The Nbmespbce URI, or the empty string if the
     *        element hbs no Nbmespbce URI or if Nbmespbce
     *        processing is not being performed.
     * @pbrbm locblNbme The locbl nbme (without prefix), or the
     *        empty string if Nbmespbce processing is not being
     *        performed.
     * @pbrbm qNbme The qublified nbme (with prefix), or the
     *        empty string if qublified nbmes bre not bvbilbble.
     * @pbrbm bttributes The bttributes bttbched to the element.  If
     *        there bre no bttributes, it shbll be bn empty
     *        Attributes object.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#stbrtElement
     */
    public void stbrtElement (String uri, String locblNbme,
                              String qNbme, Attributes bttributes)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of the end of bn element.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions bt the end of
     * ebch element (such bs finblising b tree node or writing
     * output to b file).</p>
     *
     * @pbrbm uri The Nbmespbce URI, or the empty string if the
     *        element hbs no Nbmespbce URI or if Nbmespbce
     *        processing is not being performed.
     * @pbrbm locblNbme The locbl nbme (without prefix), or the
     *        empty string if Nbmespbce processing is not being
     *        performed.
     * @pbrbm qNbme The qublified nbme (with prefix), or the
     *        empty string if qublified nbmes bre not bvbilbble.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#endElement
     */
    public void endElement (String uri, String locblNbme, String qNbme)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of chbrbcter dbtb inside bn element.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method to tbke specific bctions for ebch chunk of chbrbcter dbtb
     * (such bs bdding the dbtb to b node or buffer, or printing it to
     * b file).</p>
     *
     * @pbrbm ch The chbrbcters.
     * @pbrbm stbrt The stbrt position in the chbrbcter brrby.
     * @pbrbm length The number of chbrbcters to use from the
     *               chbrbcter brrby.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#chbrbcters
     */
    public void chbrbcters (chbr ch[], int stbrt, int length)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of ignorbble whitespbce in element content.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method to tbke specific bctions for ebch chunk of ignorbble
     * whitespbce (such bs bdding dbtb to b node or buffer, or printing
     * it to b file).</p>
     *
     * @pbrbm ch The whitespbce chbrbcters.
     * @pbrbm stbrt The stbrt position in the chbrbcter brrby.
     * @pbrbm length The number of chbrbcters to use from the
     *               chbrbcter brrby.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#ignorbbleWhitespbce
     */
    public void ignorbbleWhitespbce (chbr ch[], int stbrt, int length)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of b processing instruction.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions for ebch
     * processing instruction, such bs setting stbtus vbribbles or
     * invoking other methods.</p>
     *
     * @pbrbm tbrget The processing instruction tbrget.
     * @pbrbm dbtb The processing instruction dbtb, or null if
     *             none is supplied.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#processingInstruction
     */
    public void processingInstruction (String tbrget, String dbtb)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of b skipped entity.
     *
     * <p>By defbult, do nothing.  Applicbtion writers mby override this
     * method in b subclbss to tbke specific bctions for ebch
     * processing instruction, such bs setting stbtus vbribbles or
     * invoking other methods.</p>
     *
     * @pbrbm nbme The nbme of the skipped entity.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ContentHbndler#processingInstruction
     */
    public void skippedEntity (String nbme)
        throws SAXException
    {
        // no op
    }



    ////////////////////////////////////////////////////////////////////
    // Defbult implementbtion of the ErrorHbndler interfbce.
    ////////////////////////////////////////////////////////////////////


    /**
     * Receive notificbtion of b pbrser wbrning.
     *
     * <p>The defbult implementbtion does nothing.  Applicbtion writers
     * mby override this method in b subclbss to tbke specific bctions
     * for ebch wbrning, such bs inserting the messbge in b log file or
     * printing it to the console.</p>
     *
     * @pbrbm e The wbrning informbtion encoded bs bn exception.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ErrorHbndler#wbrning
     * @see org.xml.sbx.SAXPbrseException
     */
    public void wbrning (SAXPbrseException e)
        throws SAXException
    {
        // no op
    }


    /**
     * Receive notificbtion of b recoverbble pbrser error.
     *
     * <p>The defbult implementbtion does nothing.  Applicbtion writers
     * mby override this method in b subclbss to tbke specific bctions
     * for ebch error, such bs inserting the messbge in b log file or
     * printing it to the console.</p>
     *
     * @pbrbm e The error informbtion encoded bs bn exception.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ErrorHbndler#wbrning
     * @see org.xml.sbx.SAXPbrseException
     */
    public void error (SAXPbrseException e)
        throws SAXException
    {
        // no op
    }


    /**
     * Report b fbtbl XML pbrsing error.
     *
     * <p>The defbult implementbtion throws b SAXPbrseException.
     * Applicbtion writers mby override this method in b subclbss if
     * they need to tbke specific bctions for ebch fbtbl error (such bs
     * collecting bll of the errors into b single report): in bny cbse,
     * the bpplicbtion must stop bll regulbr processing when this
     * method is invoked, since the document is no longer relibble, bnd
     * the pbrser mby no longer report pbrsing events.</p>
     *
     * @pbrbm e The error informbtion encoded bs bn exception.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see org.xml.sbx.ErrorHbndler#fbtblError
     * @see org.xml.sbx.SAXPbrseException
     */
    public void fbtblError (SAXPbrseException e)
        throws SAXException
    {
        throw e;
    }

}

// end of DefbultHbndler.jbvb
