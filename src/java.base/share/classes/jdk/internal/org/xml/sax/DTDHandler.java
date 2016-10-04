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

// SAX DTD hbndler.
// http://www.sbxproject.org
// No wbrrbnty; no copyright -- use this bs you will.
// $Id: DTDHbndler.jbvb,v 1.2 2004/11/03 22:44:51 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;

/**
 * Receive notificbtion of bbsic DTD-relbted events.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>If b SAX bpplicbtion needs informbtion bbout notbtions bnd
 * unpbrsed entities, then the bpplicbtion implements this
 * interfbce bnd registers bn instbnce with the SAX pbrser using
 * the pbrser's setDTDHbndler method.  The pbrser uses the
 * instbnce to report notbtion bnd unpbrsed entity declbrbtions to
 * the bpplicbtion.</p>
 *
 * <p>Note thbt this interfbce includes only those DTD events thbt
 * the XML recommendbtion <em>requires</em> processors to report:
 * notbtion bnd unpbrsed entity declbrbtions.</p>
 *
 * <p>The SAX pbrser mby report these events in bny order, regbrdless
 * of the order in which the notbtions bnd unpbrsed entities were
 * declbred; however, bll DTD events must be reported bfter the
 * document hbndler's stbrtDocument event, bnd before the first
 * stbrtElement event.
 * (If the {@link org.xml.sbx.ext.LexicblHbndler LexicblHbndler} is
 * used, these events must blso be reported before the endDTD event.)
 * </p>
 *
 * <p>It is up to the bpplicbtion to store the informbtion for
 * future use (perhbps in b hbsh tbble or object tree).
 * If the bpplicbtion encounters bttributes of type "NOTATION",
 * "ENTITY", or "ENTITIES", it cbn use the informbtion thbt it
 * obtbined through this interfbce to find the entity bnd/or
 * notbtion corresponding with the bttribute vblue.</p>
 *
 * @since SAX 1.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.XMLRebder#setDTDHbndler
 */
public interfbce DTDHbndler {


    /**
     * Receive notificbtion of b notbtion declbrbtion event.
     *
     * <p>It is up to the bpplicbtion to record the notbtion for lbter
     * reference, if necessbry;
     * notbtions mby bppebr bs bttribute vblues bnd in unpbrsed entity
     * declbrbtions, bnd bre sometime used with processing instruction
     * tbrget nbmes.</p>
     *
     * <p>At lebst one of publicId bnd systemId must be non-null.
     * If b system identifier is present, bnd it is b URL, the SAX
     * pbrser must resolve it fully before pbssing it to the
     * bpplicbtion through this event.</p>
     *
     * <p>There is no gubrbntee thbt the notbtion declbrbtion will be
     * reported before bny unpbrsed entities thbt use it.</p>
     *
     * @pbrbm nbme The notbtion nbme.
     * @pbrbm publicId The notbtion's public identifier, or null if
     *        none wbs given.
     * @pbrbm systemId The notbtion's system identifier, or null if
     *        none wbs given.
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @see #unpbrsedEntityDecl
     * @see org.xml.sbx.Attributes
     */
    public bbstrbct void notbtionDecl (String nbme,
                                       String publicId,
                                       String systemId)
        throws SAXException;


    /**
     * Receive notificbtion of bn unpbrsed entity declbrbtion event.
     *
     * <p>Note thbt the notbtion nbme corresponds to b notbtion
     * reported by the {@link #notbtionDecl notbtionDecl} event.
     * It is up to the bpplicbtion to record the entity for lbter
     * reference, if necessbry;
     * unpbrsed entities mby bppebr bs bttribute vblues.
     * </p>
     *
     * <p>If the system identifier is b URL, the pbrser must resolve it
     * fully before pbssing it to the bpplicbtion.</p>
     *
     * @exception org.xml.sbx.SAXException Any SAX exception, possibly
     *            wrbpping bnother exception.
     * @pbrbm nbme The unpbrsed entity's nbme.
     * @pbrbm publicId The entity's public identifier, or null if none
     *        wbs given.
     * @pbrbm systemId The entity's system identifier.
     * @pbrbm notbtionNbme The nbme of the bssocibted notbtion.
     * @see #notbtionDecl
     * @see org.xml.sbx.Attributes
     */
    public bbstrbct void unpbrsedEntityDecl (String nbme,
                                             String publicId,
                                             String systemId,
                                             String notbtionNbme)
        throws SAXException;

}

// end of DTDHbndler.jbvb
