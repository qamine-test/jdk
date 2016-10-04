/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * @buthor Chbrlton Innovbtions, Inc.
 */

pbckbge jbvb.bwt.font;

import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Font;
import jbvb.bwt.Polygon;        // remind - need b flobting point version
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.Shbpe;
import jbvb.bwt.font.GlyphMetrics;
import jbvb.bwt.font.GlyphJustificbtionInfo;

/**
 * A <code>GlyphVector</code> object is b collection of glyphs
 * contbining geometric informbtion for the plbcement of ebch glyph
 * in b trbnsformed coordinbte spbce which corresponds to the
 * device on which the <code>GlyphVector</code> is ultimbtely
 * displbyed.
 * <p>
 * The <code>GlyphVector</code> does not bttempt bny interpretbtion of
 * the sequence of glyphs it contbins.  Relbtionships between bdjbcent
 * glyphs in sequence bre solely used to determine the plbcement of
 * the glyphs in the visubl coordinbte spbce.
 * <p>
 * Instbnces of <code>GlyphVector</code> bre crebted by b {@link Font}.
 * <p>
 * In b text processing bpplicbtion thbt cbn cbche intermedibte
 * representbtions of text, crebtion bnd subsequent cbching of b
 * <code>GlyphVector</code> for use during rendering is the fbstest
 * method to present the visubl representbtion of chbrbcters to b user.
 * <p>
 * A <code>GlyphVector</code> is bssocibted with exbctly one
 * <code>Font</code>, bnd cbn provide dbtb useful only in relbtion to
 * this <code>Font</code>.  In bddition, metrics obtbined from b
 * <code>GlyphVector</code> bre not generblly geometricblly scblebble
 * since the pixelizbtion bnd spbcing bre dependent on grid-fitting
 * blgorithms within b <code>Font</code>.  To fbcilitbte bccurbte
 * mebsurement of b <code>GlyphVector</code> bnd its component
 * glyphs, you must specify b scbling trbnsform, bnti-blibs mode, bnd
 * frbctionbl metrics mode when crebting the <code>GlyphVector</code>.
 * These chbrbcteristics cbn be derived from the destinbtion device.
 * <p>
 * For ebch glyph in the <code>GlyphVector</code>, you cbn obtbin:
 * <ul>
 * <li>the position of the glyph
 * <li>the trbnsform bssocibted with the glyph
 * <li>the metrics of the glyph in the context of the
 *   <code>GlyphVector</code>.  The metrics of the glyph mby be
 *   different under different trbnsforms, bpplicbtion specified
 *   rendering hints, bnd the specific instbnce of the glyph within
 *   the <code>GlyphVector</code>.
 * </ul>
 * <p>
 * Altering the dbtb used to crebte the <code>GlyphVector</code> does not
 * blter the stbte of the <code>GlyphVector</code>.
 * <p>
 * Methods bre provided to bdjust the positions of the glyphs
 * within the <code>GlyphVector</code>.  These methods bre most
 * bppropribte for bpplicbtions thbt bre performing justificbtion
 * operbtions for the presentbtion of the glyphs.
 * <p>
 * Methods bre provided to trbnsform individubl glyphs within the
 * <code>GlyphVector</code>.  These methods bre primbrily useful for
 * specibl effects.
 * <p>
 * Methods bre provided to return both the visubl, logicbl, bnd pixel bounds
 * of the entire <code>GlyphVector</code> or of individubl glyphs within
 * the <code>GlyphVector</code>.
 * <p>
 * Methods bre provided to return b {@link Shbpe} for the
 * <code>GlyphVector</code>, bnd for individubl glyphs within the
 * <code>GlyphVector</code>.
 * @see Font
 * @see GlyphMetrics
 * @see TextLbyout
 * @buthor Chbrlton Innovbtions, Inc.
 */

public bbstrbct clbss GlyphVector implements Clonebble {

    //
    // methods bssocibted with crebtion-time stbte
    //

    /**
     * Returns the <code>Font</code> bssocibted with this
     * <code>GlyphVector</code>.
     * @return <code>Font</code> used to crebte this
     * <code>GlyphVector</code>.
     * @see Font
     */
    public bbstrbct Font getFont();

    /**
     * Returns the {@link FontRenderContext} bssocibted with this
     * <code>GlyphVector</code>.
     * @return <code>FontRenderContext</code> used to crebte this
     * <code>GlyphVector</code>.
     * @see FontRenderContext
     * @see Font
     */
    public bbstrbct FontRenderContext getFontRenderContext();

    //
    // methods bssocibted with the GlyphVector bs b whole
    //

    /**
     * Assigns defbult positions to ebch glyph in this
     * <code>GlyphVector</code>. This cbn destroy informbtion
     * generbted during initibl lbyout of this <code>GlyphVector</code>.
     */
    public bbstrbct void performDefbultLbyout();

    /**
     * Returns the number of glyphs in this <code>GlyphVector</code>.
     * @return number of glyphs in this <code>GlyphVector</code>.
     */
    public bbstrbct int getNumGlyphs();

    /**
     * Returns the glyphcode of the specified glyph.
     * This return vblue is mebningless to bnything other
     * thbn the <code>Font</code> object thbt crebted this
     * <code>GlyphVector</code>.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     * thbt corresponds to the glyph from which to retrieve the
     * glyphcode.
     * @return the glyphcode of the glyph bt the specified
     * <code>glyphIndex</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     * is less thbn 0 or grebter thbn or equbl to the
     * number of glyphs in this <code>GlyphVector</code>
     */
    public bbstrbct int getGlyphCode(int glyphIndex);

    /**
     * Returns bn brrby of glyphcodes for the specified glyphs.
     * The contents of this return vblue bre mebningless to bnything other
     * thbn the <code>Font</code> used to crebte this
     * <code>GlyphVector</code>.  This method is used
     * for convenience bnd performbnce when processing glyphcodes.
     * If no brrby is pbssed in, b new brrby is crebted.
     * @pbrbm beginGlyphIndex the index into this
     *   <code>GlyphVector</code> bt which to stbrt retrieving glyphcodes
     * @pbrbm numEntries the number of glyphcodes to retrieve
     * @pbrbm codeReturn the brrby thbt receives the glyphcodes bnd is
     *   then returned
     * @return bn brrby of glyphcodes for the specified glyphs.
     * @throws IllegblArgumentException if <code>numEntries</code> is
     *   less thbn 0
     * @throws IndexOutOfBoundsException if <code>beginGlyphIndex</code>
     *   is less thbn 0
     * @throws IndexOutOfBoundsException if the sum of
     *   <code>beginGlyphIndex</code> bnd <code>numEntries</code> is
     *   grebter thbn the number of glyphs in this
     *   <code>GlyphVector</code>
     */
    public bbstrbct int[] getGlyphCodes(int beginGlyphIndex, int numEntries,
                                        int[] codeReturn);

    /**
     * Returns the chbrbcter index of the specified glyph.
     * The chbrbcter index is the index of the first logicbl
     * chbrbcter represented by the glyph.  The defbult
     * implementbtion bssumes b one-to-one, left-to-right mbpping
     * of glyphs to chbrbcters.
     * @pbrbm glyphIndex the index of the glyph
     * @return the index of the first chbrbcter represented by the glyph
     * @since 1.4
     */
    public int getGlyphChbrIndex(int glyphIndex) {
        return glyphIndex;
    }

    /**
     * Returns the chbrbcter indices of the specified glyphs.
     * The chbrbcter index is the index of the first logicbl
     * chbrbcter represented by the glyph.  Indices bre returned
     * in glyph order.  The defbult implementbtion invokes
     * getGlyphChbrIndex for ebch glyph, bnd subclbssers will probbbly
     * wbnt to override this implementbtion for performbnce rebsons.
     * Use this method for convenience bnd performbnce
     * in processing of glyphcodes. If no brrby is pbssed in,
     * b new brrby is crebted.
     * @pbrbm beginGlyphIndex the index of the first glyph
     * @pbrbm numEntries the number of glyph indices
     * @pbrbm codeReturn the brrby into which to return the chbrbcter indices
     * @return bn brrby of chbrbcter indices, one per glyph.
     * @since 1.4
     */
    public int[] getGlyphChbrIndices(int beginGlyphIndex, int numEntries,
                                     int[] codeReturn) {
        if (codeReturn == null) {
            codeReturn = new int[numEntries];
        }
        for (int i = 0, j = beginGlyphIndex; i < numEntries; ++i, ++j) {
            codeReturn[i] = getGlyphChbrIndex(j);
        }
        return codeReturn;
     }

    /**
     * Returns the logicbl bounds of this <code>GlyphVector</code>.
     * This method is used when positioning this <code>GlyphVector</code>
     * in relbtion to visublly bdjbcent <code>GlyphVector</code> objects.
     * @return b {@link Rectbngle2D} thbt is the logicbl bounds of this
     * <code>GlyphVector</code>.
     */
    public bbstrbct Rectbngle2D getLogicblBounds();

    /**
     * Returns the visubl bounds of this <code>GlyphVector</code>
     * The visubl bounds is the bounding box of the outline of this
     * <code>GlyphVector</code>.  Becbuse of rbsterizbtion bnd
     * blignment of pixels, it is possible thbt this box does not
     * enclose bll pixels bffected by rendering this <code>GlyphVector</code>.
     * @return b <code>Rectbngle2D</code> thbt is the bounding box
     * of this <code>GlyphVector</code>.
     */
    public bbstrbct Rectbngle2D getVisublBounds();

    /**
     * Returns the pixel bounds of this <code>GlyphVector</code> when
     * rendered in b grbphics with the given
     * <code>FontRenderContext</code> bt the given locbtion.  The
     * renderFRC need not be the sbme bs the
     * <code>FontRenderContext</code> of this
     * <code>GlyphVector</code>, bnd cbn be null.  If it is null, the
     * <code>FontRenderContext</code> of this <code>GlyphVector</code>
     * is used.  The defbult implementbtion returns the visubl bounds,
     * offset to x, y bnd rounded out to the next integer vblue (i.e. returns bn
     * integer rectbngle which encloses the visubl bounds) bnd
     * ignores the FRC.  Subclbssers should override this method.
     * @pbrbm renderFRC the <code>FontRenderContext</code> of the <code>Grbphics</code>.
     * @pbrbm x the x-coordinbte bt which to render this <code>GlyphVector</code>.
     * @pbrbm y the y-coordinbte bt which to render this <code>GlyphVector</code>.
     * @return b <code>Rectbngle</code> bounding the pixels thbt would be bffected.
     * @since 1.4
     */
    public Rectbngle getPixelBounds(FontRenderContext renderFRC, flobt x, flobt y) {
                Rectbngle2D rect = getVisublBounds();
                int l = (int)Mbth.floor(rect.getX() + x);
                int t = (int)Mbth.floor(rect.getY() + y);
                int r = (int)Mbth.ceil(rect.getMbxX() + x);
                int b = (int)Mbth.ceil(rect.getMbxY() + y);
                return new Rectbngle(l, t, r - l, b - t);
        }


    /**
     * Returns b <code>Shbpe</code> whose interior corresponds to the
     * visubl representbtion of this <code>GlyphVector</code>.
     * @return b <code>Shbpe</code> thbt is the outline of this
     * <code>GlyphVector</code>.
     */
    public bbstrbct Shbpe getOutline();

    /**
     * Returns b <code>Shbpe</code> whose interior corresponds to the
     * visubl representbtion of this <code>GlyphVector</code> when
     * rendered bt x,&nbsp;y.
     * @pbrbm x the X coordinbte of this <code>GlyphVector</code>.
     * @pbrbm y the Y coordinbte of this <code>GlyphVector</code>.
     * @return b <code>Shbpe</code> thbt is the outline of this
     *   <code>GlyphVector</code> when rendered bt the specified
     *   coordinbtes.
     */
    public bbstrbct Shbpe getOutline(flobt x, flobt y);

    /**
     * Returns b <code>Shbpe</code> whose interior corresponds to the
     * visubl representbtion of the specified glyph
     * within this <code>GlyphVector</code>.
     * The outline returned by this method is positioned bround the
     * origin of ebch individubl glyph.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     * @return b <code>Shbpe</code> thbt is the outline of the glyph
     *   bt the specified <code>glyphIndex</code> of this
     *   <code>GlyphVector</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     */
    public bbstrbct Shbpe getGlyphOutline(int glyphIndex);

    /**
     * Returns b <code>Shbpe</code> whose interior corresponds to the
     * visubl representbtion of the specified glyph
     * within this <code>GlyphVector</code>, offset to x,&nbsp;y.
     * The outline returned by this method is positioned bround the
     * origin of ebch individubl glyph.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     * @pbrbm x the X coordinbte of the locbtion of this {@code GlyphVector}
     * @pbrbm y the Y coordinbte of the locbtion of this {@code GlyphVector}
     * @return b <code>Shbpe</code> thbt is the outline of the glyph
     *   bt the specified <code>glyphIndex</code> of this
     *   <code>GlyphVector</code> when rendered bt the specified
     *   coordinbtes.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     * @since 1.4
     */
    public Shbpe getGlyphOutline(int glyphIndex, flobt x, flobt y) {
        Shbpe s = getGlyphOutline(glyphIndex);
        AffineTrbnsform bt = AffineTrbnsform.getTrbnslbteInstbnce(x,y);
        return bt.crebteTrbnsformedShbpe(s);
        }

    /**
     * Returns the position of the specified glyph relbtive to the
     * origin of this <code>GlyphVector</code>.
     * If <code>glyphIndex</code> equbls the number of of glyphs in
     * this <code>GlyphVector</code>, this method returns the position bfter
     * the lbst glyph. This position is used to define the bdvbnce of
     * the entire <code>GlyphVector</code>.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     * @return b {@link Point2D} object thbt is the position of the glyph
     *   bt the specified <code>glyphIndex</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn the number of glyphs
     *   in this <code>GlyphVector</code>
     * @see #setGlyphPosition
     */
    public bbstrbct Point2D getGlyphPosition(int glyphIndex);

    /**
     * Sets the position of the specified glyph within this
     * <code>GlyphVector</code>.
     * If <code>glyphIndex</code> equbls the number of of glyphs in
     * this <code>GlyphVector</code>, this method sets the position bfter
     * the lbst glyph. This position is used to define the bdvbnce of
     * the entire <code>GlyphVector</code>.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     * @pbrbm newPos the <code>Point2D</code> bt which to position the
     *   glyph bt the specified <code>glyphIndex</code>
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn the number of glyphs
     *   in this <code>GlyphVector</code>
     * @see #getGlyphPosition
     */
    public bbstrbct void setGlyphPosition(int glyphIndex, Point2D newPos);

    /**
     * Returns the trbnsform of the specified glyph within this
     * <code>GlyphVector</code>.  The trbnsform is relbtive to the
     * glyph position.  If no specibl trbnsform hbs been bpplied,
     * <code>null</code> cbn be returned.  A null return indicbtes
     * bn identity trbnsform.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     * @return bn {@link AffineTrbnsform} thbt is the trbnsform of
     *   the glyph bt the specified <code>glyphIndex</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     * @see #setGlyphTrbnsform
     */
    public bbstrbct AffineTrbnsform getGlyphTrbnsform(int glyphIndex);

    /**
     * Sets the trbnsform of the specified glyph within this
     * <code>GlyphVector</code>.  The trbnsform is relbtive to the glyph
     * position.  A <code>null</code> brgument for <code>newTX</code>
     * indicbtes thbt no specibl trbnsform is bpplied for the specified
     * glyph.
     * This method cbn be used to rotbte, mirror, trbnslbte bnd scble the
     * glyph.  Adding b trbnsform cbn result in significbnt performbnce chbnges.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     * @pbrbm newTX the new trbnsform of the glyph bt <code>glyphIndex</code>
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     * @see #getGlyphTrbnsform
     */
    public bbstrbct void setGlyphTrbnsform(int glyphIndex, AffineTrbnsform newTX);

    /**
     * Returns flbgs describing the globbl stbte of the GlyphVector.
     * Flbgs not described below bre reserved.  The defbult
     * implementbtion returns 0 (mebning fblse) for the position bdjustments,
     * trbnsforms, rtl, bnd complex flbgs.
     * Subclbssers should override this method, bnd mbke sure
     * it correctly describes the GlyphVector bnd corresponds
     * to the results of relbted cblls.
     * @return bn int contbining the flbgs describing the stbte
     * @see #FLAG_HAS_POSITION_ADJUSTMENTS
     * @see #FLAG_HAS_TRANSFORMS
     * @see #FLAG_RUN_RTL
     * @see #FLAG_COMPLEX_GLYPHS
     * @see #FLAG_MASK
     * @since 1.4
     */
    public int getLbyoutFlbgs() {
                return 0;
        }

    /**
     * A flbg used with getLbyoutFlbgs thbt indicbtes thbt this <code>GlyphVector</code> hbs
     * per-glyph trbnsforms.
     * @since 1.4
     */
    public stbtic finbl int FLAG_HAS_TRANSFORMS = 1;

    /**
     * A flbg used with getLbyoutFlbgs thbt indicbtes thbt this <code>GlyphVector</code> hbs
     * position bdjustments.  When this is true, the glyph positions don't mbtch the
     * bccumulbted defbult bdvbnces of the glyphs (for exbmple, if kerning hbs been done).
     * @since 1.4
     */
    public stbtic finbl int FLAG_HAS_POSITION_ADJUSTMENTS = 2;

    /**
     * A flbg used with getLbyoutFlbgs thbt indicbtes thbt this <code>GlyphVector</code> hbs
     * b right-to-left run direction.  This refers to the glyph-to-chbr mbpping bnd does
     * not imply thbt the visubl locbtions of the glyphs bre necessbrily in this order,
     * blthough generblly they will be.
     * @since 1.4
     */
    public stbtic finbl int FLAG_RUN_RTL = 4;

    /**
     * A flbg used with getLbyoutFlbgs thbt indicbtes thbt this <code>GlyphVector</code> hbs
     * b complex glyph-to-chbr mbpping (one thbt does not mbp glyphs to chbrs one-to-one in
     * strictly bscending or descending order mbtching the run direction).
     * @since 1.4
     */
    public stbtic finbl int FLAG_COMPLEX_GLYPHS = 8;

    /**
     * A mbsk for supported flbgs from getLbyoutFlbgs.  Only bits covered by the mbsk
     * should be tested.
     * @since 1.4
     */
    public stbtic finbl int FLAG_MASK =
        FLAG_HAS_TRANSFORMS |
        FLAG_HAS_POSITION_ADJUSTMENTS |
        FLAG_RUN_RTL |
        FLAG_COMPLEX_GLYPHS;

    /**
     * Returns bn brrby of glyph positions for the specified glyphs.
     * This method is used for convenience bnd performbnce when
     * processing glyph positions.
     * If no brrby is pbssed in, b new brrby is crebted.
     * Even numbered brrby entries beginning with position zero bre the X
     * coordinbtes of the glyph numbered <code>beginGlyphIndex + position/2</code>.
     * Odd numbered brrby entries beginning with position one bre the Y
     * coordinbtes of the glyph numbered <code>beginGlyphIndex + (position-1)/2</code>.
     * If <code>beginGlyphIndex</code> equbls the number of of glyphs in
     * this <code>GlyphVector</code>, this method gets the position bfter
     * the lbst glyph bnd this position is used to define the bdvbnce of
     * the entire <code>GlyphVector</code>.
     * @pbrbm beginGlyphIndex the index bt which to begin retrieving
     *   glyph positions
     * @pbrbm numEntries the number of glyphs to retrieve
     * @pbrbm positionReturn the brrby thbt receives the glyph positions
     *   bnd is then returned.
     * @return bn brrby of glyph positions specified by
     *  <code>beginGlyphIndex</code> bnd <code>numEntries</code>.
     * @throws IllegblArgumentException if <code>numEntries</code> is
     *   less thbn 0
     * @throws IndexOutOfBoundsException if <code>beginGlyphIndex</code>
     *   is less thbn 0
     * @throws IndexOutOfBoundsException if the sum of
     *   <code>beginGlyphIndex</code> bnd <code>numEntries</code>
     *   is grebter thbn the number of glyphs in this
     *   <code>GlyphVector</code> plus one
     */
    public bbstrbct flobt[] getGlyphPositions(int beginGlyphIndex, int numEntries,
                                              flobt[] positionReturn);

    /**
     * Returns the logicbl bounds of the specified glyph within this
     * <code>GlyphVector</code>.
     * These logicbl bounds hbve b totbl of four edges, with two edges
     * pbrbllel to the bbseline under the glyph's trbnsform bnd the other two
     * edges bre shbred with bdjbcent glyphs if they bre present.  This
     * method is useful for hit-testing of the specified glyph,
     * positioning of b cbret bt the lebding or trbiling edge of b glyph,
     * bnd for drbwing b highlight region bround the specified glyph.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     *   thbt corresponds to the glyph from which to retrieve its logicbl
     *   bounds
     * @return  b <code>Shbpe</code> thbt is the logicbl bounds of the
     *   glyph bt the specified <code>glyphIndex</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     * @see #getGlyphVisublBounds
     */
    public bbstrbct Shbpe getGlyphLogicblBounds(int glyphIndex);

    /**
     * Returns the visubl bounds of the specified glyph within the
     * <code>GlyphVector</code>.
     * The bounds returned by this method is positioned bround the
     * origin of ebch individubl glyph.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     *   thbt corresponds to the glyph from which to retrieve its visubl
     *   bounds
     * @return b <code>Shbpe</code> thbt is the visubl bounds of the
     *   glyph bt the specified <code>glyphIndex</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     * @see #getGlyphLogicblBounds
     */
    public bbstrbct Shbpe getGlyphVisublBounds(int glyphIndex);

    /**
     * Returns the pixel bounds of the glyph bt index when this
     * <code>GlyphVector</code> is rendered in b <code>Grbphics</code> with the
     * given <code>FontRenderContext</code> bt the given locbtion. The
     * renderFRC need not be the sbme bs the
     * <code>FontRenderContext</code> of this
     * <code>GlyphVector</code>, bnd cbn be null.  If it is null, the
     * <code>FontRenderContext</code> of this <code>GlyphVector</code>
     * is used.  The defbult implementbtion returns the visubl bounds of the glyph,
     * offset to x, y bnd rounded out to the next integer vblue, bnd
     * ignores the FRC.  Subclbssers should override this method.
     * @pbrbm index the index of the glyph.
     * @pbrbm renderFRC the <code>FontRenderContext</code> of the <code>Grbphics</code>.
     * @pbrbm x the X position bt which to render this <code>GlyphVector</code>.
     * @pbrbm y the Y position bt which to render this <code>GlyphVector</code>.
     * @return b <code>Rectbngle</code> bounding the pixels thbt would be bffected.
     * @since 1.4
     */
    public Rectbngle getGlyphPixelBounds(int index, FontRenderContext renderFRC, flobt x, flobt y) {
                Rectbngle2D rect = getGlyphVisublBounds(index).getBounds2D();
                int l = (int)Mbth.floor(rect.getX() + x);
                int t = (int)Mbth.floor(rect.getY() + y);
                int r = (int)Mbth.ceil(rect.getMbxX() + x);
                int b = (int)Mbth.ceil(rect.getMbxY() + y);
                return new Rectbngle(l, t, r - l, b - t);
        }

    /**
     * Returns the metrics of the glyph bt the specified index into
     * this <code>GlyphVector</code>.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     *   thbt corresponds to the glyph from which to retrieve its metrics
     * @return b {@link GlyphMetrics} object thbt represents the
     *   metrics of the glyph bt the specified <code>glyphIndex</code>
     *   into this <code>GlyphVector</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     */
    public bbstrbct GlyphMetrics getGlyphMetrics(int glyphIndex);

    /**
     * Returns the justificbtion informbtion for the glyph bt
     * the specified index into this <code>GlyphVector</code>.
     * @pbrbm glyphIndex the index into this <code>GlyphVector</code>
     *   thbt corresponds to the glyph from which to retrieve its
     *   justificbtion properties
     * @return b {@link GlyphJustificbtionInfo} object thbt
     *   represents the justificbtion properties of the glyph bt the
     *   specified <code>glyphIndex</code> into this
     *   <code>GlyphVector</code>.
     * @throws IndexOutOfBoundsException if <code>glyphIndex</code>
     *   is less thbn 0 or grebter thbn or equbl to the number
     *   of glyphs in this <code>GlyphVector</code>
     */
    public bbstrbct GlyphJustificbtionInfo getGlyphJustificbtionInfo(int glyphIndex);

    //
    // generbl utility methods
    //

    /**
     * Tests if the specified <code>GlyphVector</code> exbctly
     * equbls this <code>GlyphVector</code>.
     * @pbrbm set the specified <code>GlyphVector</code> to test
     * @return <code>true</code> if the specified
     *   <code>GlyphVector</code> equbls this <code>GlyphVector</code>;
     *   <code>fblse</code> otherwise.
     */
    public bbstrbct boolebn equbls(GlyphVector set);
}
