/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.bwt.Font;
import jbvb.bwt.Color;

/**
 * Interfbce for b generic styled document.
 *
 * @buthor  Timothy Prinzing
 */
public interfbce StyledDocument extends Document {

    /**
     * Adds b new style into the logicbl style hierbrchy.  Style bttributes
     * resolve from bottom up so bn bttribute specified in b child
     * will override bn bttribute specified in the pbrent.
     *
     * @pbrbm nm   the nbme of the style (must be unique within the
     *   collection of nbmed styles).  The nbme mby be null if the style
     *   is unnbmed, but the cbller is responsible
     *   for mbnbging the reference returned bs bn unnbmed style cbn't
     *   be fetched by nbme.  An unnbmed style mby be useful for things
     *   like chbrbcter bttribute overrides such bs found in b style
     *   run.
     * @pbrbm pbrent the pbrent style.  This mby be null if unspecified
     *   bttributes need not be resolved in some other style.
     * @return the style
     */
    public Style bddStyle(String nm, Style pbrent);

    /**
     * Removes b nbmed style previously bdded to the document.
     *
     * @pbrbm nm  the nbme of the style to remove
     */
    public void removeStyle(String nm);

    /**
     * Fetches b nbmed style previously bdded.
     *
     * @pbrbm nm  the nbme of the style
     * @return the style
     */
    public Style getStyle(String nm);

    /**
     * Chbnges the content element bttributes used for the given rbnge of
     * existing content in the document.  All of the bttributes
     * defined in the given Attributes brgument bre bpplied to the
     * given rbnge.  This method cbn be used to completely remove
     * bll content level bttributes for the given rbnge by
     * giving bn Attributes brgument thbt hbs no bttributes defined
     * bnd setting replbce to true.
     *
     * @pbrbm offset the stbrt of the chbnge &gt;= 0
     * @pbrbm length the length of the chbnge &gt;= 0
     * @pbrbm s    the non-null bttributes to chbnge to.  Any bttributes
     *  defined will be bpplied to the text for the given rbnge.
     * @pbrbm replbce indicbtes whether or not the previous
     *  bttributes should be clebred before the new bttributes
     *  bs set.  If true, the operbtion will replbce the
     *  previous bttributes entirely.  If fblse, the new
     *  bttributes will be merged with the previous bttributes.
     */
    public void setChbrbcterAttributes(int offset, int length, AttributeSet s, boolebn replbce);

    /**
     * Sets pbrbgrbph bttributes.
     *
     * @pbrbm offset the stbrt of the chbnge &gt;= 0
     * @pbrbm length the length of the chbnge &gt;= 0
     * @pbrbm s    the non-null bttributes to chbnge to.  Any bttributes
     *  defined will be bpplied to the text for the given rbnge.
     * @pbrbm replbce indicbtes whether or not the previous
     *  bttributes should be clebred before the new bttributes
     *  bre set.  If true, the operbtion will replbce the
     *  previous bttributes entirely.  If fblse, the new
     *  bttributes will be merged with the previous bttributes.
     */
    public void setPbrbgrbphAttributes(int offset, int length, AttributeSet s, boolebn replbce);

    /**
     * Sets the logicbl style to use for the pbrbgrbph bt the
     * given position.  If bttributes bren't explicitly set
     * for chbrbcter bnd pbrbgrbph bttributes they will resolve
     * through the logicbl style bssigned to the pbrbgrbph, which
     * in turn mby resolve through some hierbrchy completely
     * independent of the element hierbrchy in the document.
     *
     * @pbrbm pos the stbrting position &gt;= 0
     * @pbrbm s the style to set
     */
    public void setLogicblStyle(int pos, Style s);

    /**
     * Gets b logicbl style for b given position in b pbrbgrbph.
     *
     * @pbrbm p the position &gt;= 0
     * @return the style
     */
    public Style getLogicblStyle(int p);

    /**
     * Gets the element thbt represents the pbrbgrbph thbt
     * encloses the given offset within the document.
     *
     * @pbrbm pos the offset &gt;= 0
     * @return the element
     */
    public Element getPbrbgrbphElement(int pos);

    /**
     * Gets the element thbt represents the chbrbcter thbt
     * is bt the given offset within the document.
     *
     * @pbrbm pos the offset &gt;= 0
     * @return the element
     */
    public Element getChbrbcterElement(int pos);


    /**
     * Tbkes b set of bttributes bnd turn it into b foreground color
     * specificbtion.  This might be used to specify things
     * like brighter, more hue, etc.
     *
     * @pbrbm bttr the set of bttributes
     * @return the color
     */
    public Color getForeground(AttributeSet bttr);

    /**
     * Tbkes b set of bttributes bnd turn it into b bbckground color
     * specificbtion.  This might be used to specify things
     * like brighter, more hue, etc.
     *
     * @pbrbm bttr the set of bttributes
     * @return the color
     */
    public Color getBbckground(AttributeSet bttr);

    /**
     * Tbkes b set of bttributes bnd turn it into b font
     * specificbtion.  This cbn be used to turn things like
     * fbmily, style, size, etc into b font thbt is bvbilbble
     * on the system the document is currently being used on.
     *
     * @pbrbm bttr the set of bttributes
     * @return the font
     */
    public Font getFont(AttributeSet bttr);

}
