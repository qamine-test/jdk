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

// Attributes.jbvb - bttribute list with Nbmespbce support
// http://www.sbxproject.org
// Written by Dbvid Megginson
// NO WARRANTY!  This clbss is in the public dombin.
// $Id: Attributes.jbvb,v 1.2 2004/11/03 22:44:51 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;


/**
 * Interfbce for b list of XML bttributes.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>This interfbce bllows bccess to b list of bttributes in
 * three different wbys:</p>
 *
 * <ol>
 * <li>by bttribute index;</li>
 * <li>by Nbmespbce-qublified nbme; or</li>
 * <li>by qublified (prefixed) nbme.</li>
 * </ol>
 *
 * <p>The list will not contbin bttributes thbt were declbred
 * #IMPLIED but not specified in the stbrt tbg.  It will blso not
 * contbin bttributes used bs Nbmespbce declbrbtions (xmlns*) unless
 * the <code>http://xml.org/sbx/febtures/nbmespbce-prefixes</code>
 * febture is set to <vbr>true</vbr> (it is <vbr>fblse</vbr> by
 * defbult).
 * Becbuse SAX2 conforms to the originbl "Nbmespbces in XML"
 * recommendbtion, it normblly does not
 * give nbmespbce declbrbtion bttributes b nbmespbce URI.
 * </p>
 *
 * <p>Some SAX2 pbrsers mby support using bn optionbl febture flbg
 * (<code>http://xml.org/sbx/febtures/xmlns-uris</code>) to request
 * thbt those bttributes be given URIs, conforming to b lbter
 * bbckwbrds-incompbtible revision of thbt recommendbtion.  (The
 * bttribute's "locbl nbme" will be the prefix, or "xmlns" when
 * defining b defbult element nbmespbce.)  For portbbility, hbndler
 * code should blwbys resolve thbt conflict, rbther thbn requiring
 * pbrsers thbt cbn chbnge the setting of thbt febture flbg.  </p>
 *
 * <p>If the nbmespbce-prefixes febture (see bbove) is
 * <vbr>fblse</vbr>, bccess by qublified nbme mby not be bvbilbble; if
 * the <code>http://xml.org/sbx/febtures/nbmespbces</code> febture is
 * <vbr>fblse</vbr>, bccess by Nbmespbce-qublified nbmes mby not be
 * bvbilbble.</p>
 *
 * <p>This interfbce replbces the now-deprecbted SAX1 {@link
 * org.xml.sbx.AttributeList AttributeList} interfbce, which does not
 * contbin Nbmespbce support.  In bddition to Nbmespbce support, it
 * bdds the <vbr>getIndex</vbr> methods (below).</p>
 *
 * <p>The order of bttributes in the list is unspecified, bnd will
 * vbry from implementbtion to implementbtion.</p>
 *
 * @since SAX 2.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.helpers.AttributesImpl
 * @see org.xml.sbx.ext.DeclHbndler#bttributeDecl
 */
public interfbce Attributes
{


    ////////////////////////////////////////////////////////////////////
    // Indexed bccess.
    ////////////////////////////////////////////////////////////////////


    /**
     * Return the number of bttributes in the list.
     *
     * <p>Once you know the number of bttributes, you cbn iterbte
     * through the list.</p>
     *
     * @return The number of bttributes in the list.
     * @see #getURI(int)
     * @see #getLocblNbme(int)
     * @see #getQNbme(int)
     * @see #getType(int)
     * @see #getVblue(int)
     */
    public bbstrbct int getLength ();


    /**
     * Look up bn bttribute's Nbmespbce URI by index.
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The Nbmespbce URI, or the empty string if none
     *         is bvbilbble, or null if the index is out of
     *         rbnge.
     * @see #getLength
     */
    public bbstrbct String getURI (int index);


    /**
     * Look up bn bttribute's locbl nbme by index.
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The locbl nbme, or the empty string if Nbmespbce
     *         processing is not being performed, or null
     *         if the index is out of rbnge.
     * @see #getLength
     */
    public bbstrbct String getLocblNbme (int index);


    /**
     * Look up bn bttribute's XML qublified (prefixed) nbme by index.
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The XML qublified nbme, or the empty string
     *         if none is bvbilbble, or null if the index
     *         is out of rbnge.
     * @see #getLength
     */
    public bbstrbct String getQNbme (int index);


    /**
     * Look up bn bttribute's type by index.
     *
     * <p>The bttribute type is one of the strings "CDATA", "ID",
     * "IDREF", "IDREFS", "NMTOKEN", "NMTOKENS", "ENTITY", "ENTITIES",
     * or "NOTATION" (blwbys in upper cbse).</p>
     *
     * <p>If the pbrser hbs not rebd b declbrbtion for the bttribute,
     * or if the pbrser does not report bttribute types, then it must
     * return the vblue "CDATA" bs stbted in the XML 1.0 Recommendbtion
     * (clbuse 3.3.3, "Attribute-Vblue Normblizbtion").</p>
     *
     * <p>For bn enumerbted bttribute thbt is not b notbtion, the
     * pbrser will report the type bs "NMTOKEN".</p>
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The bttribute's type bs b string, or null if the
     *         index is out of rbnge.
     * @see #getLength
     */
    public bbstrbct String getType (int index);


    /**
     * Look up bn bttribute's vblue by index.
     *
     * <p>If the bttribute vblue is b list of tokens (IDREFS,
     * ENTITIES, or NMTOKENS), the tokens will be concbtenbted
     * into b single string with ebch token sepbrbted by b
     * single spbce.</p>
     *
     * @pbrbm index The bttribute index (zero-bbsed).
     * @return The bttribute's vblue bs b string, or null if the
     *         index is out of rbnge.
     * @see #getLength
     */
    public bbstrbct String getVblue (int index);



    ////////////////////////////////////////////////////////////////////
    // Nbme-bbsed query.
    ////////////////////////////////////////////////////////////////////


    /**
     * Look up the index of bn bttribute by Nbmespbce nbme.
     *
     * @pbrbm uri The Nbmespbce URI, or the empty string if
     *        the nbme hbs no Nbmespbce URI.
     * @pbrbm locblNbme The bttribute's locbl nbme.
     * @return The index of the bttribute, or -1 if it does not
     *         bppebr in the list.
     */
    public int getIndex (String uri, String locblNbme);


    /**
     * Look up the index of bn bttribute by XML qublified (prefixed) nbme.
     *
     * @pbrbm qNbme The qublified (prefixed) nbme.
     * @return The index of the bttribute, or -1 if it does not
     *         bppebr in the list.
     */
    public int getIndex (String qNbme);


    /**
     * Look up bn bttribute's type by Nbmespbce nbme.
     *
     * <p>See {@link #getType(int) getType(int)} for b description
     * of the possible types.</p>
     *
     * @pbrbm uri The Nbmespbce URI, or the empty String if the
     *        nbme hbs no Nbmespbce URI.
     * @pbrbm locblNbme The locbl nbme of the bttribute.
     * @return The bttribute type bs b string, or null if the
     *         bttribute is not in the list or if Nbmespbce
     *         processing is not being performed.
     */
    public bbstrbct String getType (String uri, String locblNbme);


    /**
     * Look up bn bttribute's type by XML qublified (prefixed) nbme.
     *
     * <p>See {@link #getType(int) getType(int)} for b description
     * of the possible types.</p>
     *
     * @pbrbm qNbme The XML qublified nbme.
     * @return The bttribute type bs b string, or null if the
     *         bttribute is not in the list or if qublified nbmes
     *         bre not bvbilbble.
     */
    public bbstrbct String getType (String qNbme);


    /**
     * Look up bn bttribute's vblue by Nbmespbce nbme.
     *
     * <p>See {@link #getVblue(int) getVblue(int)} for b description
     * of the possible vblues.</p>
     *
     * @pbrbm uri The Nbmespbce URI, or the empty String if the
     *        nbme hbs no Nbmespbce URI.
     * @pbrbm locblNbme The locbl nbme of the bttribute.
     * @return The bttribute vblue bs b string, or null if the
     *         bttribute is not in the list.
     */
    public bbstrbct String getVblue (String uri, String locblNbme);


    /**
     * Look up bn bttribute's vblue by XML qublified (prefixed) nbme.
     *
     * <p>See {@link #getVblue(int) getVblue(int)} for b description
     * of the possible vblues.</p>
     *
     * @pbrbm qNbme The XML qublified nbme.
     * @return The bttribute vblue bs b string, or null if the
     *         bttribute is not in the list or if qublified nbmes
     *         bre not bvbilbble.
     */
    public bbstrbct String getVblue (String qNbme);

}

// end of Attributes.jbvb
