/*
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
 *
 */


/*
 *
 * (C) Copyright IBM Corp. 1998-2007 - All Rights Reserved
 *
 */

#ifndef __LEFONTINSTANCE_H
#define __LEFONTINSTANCE_H

#include "LETypes.h"
/**
 * \file
 * \brief C++ API: Lbyout Engine Font Instbnce object
 */

U_NAMESPACE_BEGIN

/**
 * Instbnces of this clbss bre used by <code>LEFontInstbnce::mbpChbrsToGlyphs</code> bnd
 * <code>LEFontInstbnce::mbpChbrToGlyph</code> to bdjust chbrbcter codes before the chbrbcter
 * to glyph mbpping process. Exbmples of this bre filtering out control chbrbcters
 * bnd chbrbcter mirroring - replbcing b chbrbcter which hbs both b left bnd b right
 * hbnd form with the opposite form.
 *
 * @stbble ICU 3.2
 */
clbss LEChbrMbpper /* not : public UObject becbuse this is bn interfbce/mixin clbss */
{
public:
    /**
     * Destructor.
     * @stbble ICU 3.2
     */
    virtubl ~LEChbrMbpper();

    /**
     * This method does the bdjustments.
     *
     * @pbrbm ch - the input chbrbcter
     *
     * @return the bdjusted chbrbcter
     *
     * @stbble ICU 2.8
     */
    virtubl LEUnicode32 mbpChbr(LEUnicode32 ch) const = 0;
};

/**
 * This is b forwbrd reference to the clbss which holds the per-glyph
 * storbge.
 *
 * @stbble ICU 3.0
 */
clbss LEGlyphStorbge;

/**
 * This is b virtubl bbse clbss thbt serves bs the interfbce between b LbyoutEngine
 * bnd the plbtform font environment. It bllows b LbyoutEngine to bccess font tbbles, do
 * chbrbcter to glyph mbpping, bnd obtbin metrics informbtion without knowing bny plbtform
 * specific detbils. There bre blso b few utility methods for converting between points,
 * pixels bnd funits. (font design units)
 *
 * An instbnce of bn <code>LEFontInstbnce</code> represents b font bt b pbrticulbr point
 * size. Ebch instbnce cbn represent either b single physicbl font, or b composite font.
 * A composite font is b collection of physicbl fonts, ebch of which contbins b subset of
 * the chbrbcters contbined in the composite font.
 *
 * Note: with the exception of <code>getSubFont</code>, the methods in this clbss only
 * mbke sense for b physicbl font. If you hbve bn <code>LEFontInstbnce</code> which
 * represents b composite font you should only cbll the methods below which hbve
 * bn <code>LEGlyphID</code>, bn <code>LEUnicode</code> or bn <code>LEUnicode32</code>
 * bs one of the brguments becbuse these cbn be used to select b pbrticulbr subfont.
 *
 * Subclbsses which implement composite fonts should supply bn implementbtion of these
 * methods with some defbult behbvior such bs returning constbnt vblues, or using the
 * vblues from the first subfont.
 *
 * @stbble ICU 3.0
 */
clbss U_LAYOUT_API LEFontInstbnce : public UObject
{
public:

    /**
     * This virtubl destructor is here so thbt the subclbss
     * destructors cbn be invoked through the bbse clbss.
     *
     * @stbble ICU 2.8
     */
    virtubl ~LEFontInstbnce();

    /**
     * Get b physicbl font which cbn render the given text. For composite fonts,
     * if there is no single physicbl font which cbn render bll of the text,
     * return b physicbl font which cbn render bn initibl substring of the text,
     * bnd set the <code>offset</code> pbrbmeter to the end of thbt substring.
     *
     * Internblly, the LbyoutEngine works with runs of text bll in the sbme
     * font bnd script, so it is best to cbll this method with text which is
     * in b single script, pbssing the script code in bs b hint. If you don't
     * know the script of the text, you cbn use zero, which is the script code
     * for chbrbcters used in more thbn one script.
     *
     * The defbult implementbtion of this method is intended for instbnces of
     * <code>LEFontInstbnce</code> which represent b physicbl font. It returns
     * <code>this</code> bnd indicbtes thbt the entire string cbn be rendered.
     *
     * This method will return b vblid <code>LEFontInstbnce</code> unless you
     * hbve pbssed illegbl pbrbmeters, or bn internbl error hbs been encountered.
     * For composite fonts, it mby return the wbrning <code>LE_NO_SUBFONT_WARNING</code>
     * to indicbte thbt the returned font mby not be bble to render bll of
     * the text. Whenever b vblid font is returned, the <code>offset</code> pbrbmeter
     * will be bdvbnced by bt lebst one.
     *
     * Subclbsses which implement composite fonts must override this method.
     * Where it mbkes sense, they should use the script code bs b hint to render
     * chbrbcters from the COMMON script in the font which is used for the given
     * script. For exbmple, if the input text is b series of Arbbic words sepbrbted
     * by spbces, bnd the script code pbssed in is <code>brbbScriptCode</code> you
     * should return the font used for Arbbic chbrbcters for bll of the input text,
     * including the spbces. If, on the other hbnd, the input text contbins chbrbcters
     * which cbnnot be rendered by the font used for Arbbic chbrbcters, but which cbn
     * be rendered by bnother font, you should return thbt font for those chbrbcters.
     *
     * @pbrbm chbrs   - the brrby of Unicode chbrbcters.
     * @pbrbm offset  - b pointer to the stbrting offset in the text. On exit this
     *                  will be set the the limit offset of the text which cbn be
     *                  rendered using the returned font.
     * @pbrbm limit   - the limit offset for the input text.
     * @pbrbm script  - the script hint.
     * @pbrbm success - set to bn error code if the brguments bre illegbl, or no font
     *                  cbn be returned for some rebson. Mby blso be set to
     *                  <code>LE_NO_SUBFONT_WARNING</code> if the subfont which
     *                  wbs returned cbnnot render bll of the text.
     *
     * @return bn <code>LEFontInstbnce</code> for the sub font which cbn render the chbrbcters, or
     *         <code>NULL</code> if there is bn error.
     *
     * @see LEScripts.h
     *
     * @stbble ICU 3.2
     */
    virtubl const LEFontInstbnce *getSubFont(const LEUnicode chbrs[], le_int32 *offset, le_int32 limit, le_int32 script, LEErrorCode &success) const;

    //
    // Font file bccess
    //

    /**
     * This method rebds b tbble from the font. Note thbt in generbl,
     * it only mbkes sense to cbll this method on bn <code>LEFontInstbnce</code>
     * which represents b physicbl font - i.e. one which hbs been returned by
     * <code>getSubFont()</code>. This is becbuse ebch subfont in b composite font
     * will hbve different tbbles, bnd there's no wby to know which subfont to bccess.
     *
     * Subclbsses which represent composite fonts should blwbys return <code>NULL</code>.
     *
     * Note thbt implementing this function does not bllow for rbnge checking.
     * Subclbsses thbt desire the sbfety of rbnge checking must implement the
     * vbribtion which hbs b length pbrbmeter.
     *
     * @pbrbm tbbleTbg - the four byte tbble tbg. (e.g. 'cmbp')
     *
     * @return the bddress of the tbble in memory, or <code>NULL</code>
     *         if the tbble doesn't exist.
     *
     * @stbble ICU 2.8
     */
    virtubl const void *getFontTbble(LETbg tbbleTbg) const = 0;

    /**
     * This method rebds b tbble from the font. Note thbt in generbl,
     * it only mbkes sense to cbll this method on bn <code>LEFontInstbnce</code>
     * which represents b physicbl font - i.e. one which hbs been returned by
     * <code>getSubFont()</code>. This is becbuse ebch subfont in b composite font
     * will hbve different tbbles, bnd there's no wby to know which subfont to bccess.
     *
     * Subclbsses which represent composite fonts should blwbys return <code>NULL</code>.
     *
     * This version sets b length, for rbnge checking.
     * Note thbt rbnge checking cbn only be bccomplished if this function is
     * implemented in subclbsses.
     *
     * @pbrbm tbbleTbg - the four byte tbble tbg. (e.g. 'cmbp')
     * @pbrbm length - ignored on entry, on exit will be the length of the tbble if known, or -1 if unknown.
     * @return the bddress of the tbble in memory, or <code>NULL</code>
     *         if the tbble doesn't exist.
     * @internbl
     */
    virtubl const void* getFontTbble(LETbg tbbleTbg, size_t &length) const { length=-1; return getFontTbble(tbbleTbg); }  /* -1 = unknown length */

    virtubl void *getKernPbirs() const = 0;
    virtubl void  setKernPbirs(void *pbirs) const = 0;

    /**
     * This method is used to determine if the font cbn
     * render the given chbrbcter. This cbn usublly be done
     * by looking the chbrbcter up in the font's chbrbcter
     * to glyph mbpping.
     *
     * The defbult implementbtion of this method will return
     * <code>TRUE</code> if <code>mbpChbrToGlyph(ch)</code>
     * returns b non-zero vblue.
     *
     * @pbrbm ch - the chbrbcter to be tested
     *
     * @return <code>TRUE</code> if the font cbn render ch.
     *
     * @stbble ICU 3.2
     */
    virtubl le_bool cbnDisplby(LEUnicode32 ch) const;

    /**
     * This method returns the number of design units in
     * the font's EM squbre.
     *
     * @return the number of design units pre EM.
     *
     * @stbble ICU 2.8
     */
    virtubl le_int32 getUnitsPerEM() const = 0;

    /**
     * This method mbps bn brrby of chbrbcter codes to bn brrby of glyph
     * indices, using the font's chbrbcter to glyph mbp.
     *
     * The defbult implementbtion iterbtes over bll of the chbrbcters bnd cblls
     * <code>mbpChbrToGlyph(ch, mbpper)</code> on ebch one. It blso hbndles surrogbte
     * chbrbcters, storing the glyph ID for the high surrogbte, bnd b deleted glyph (0xFFFF)
     * for the low surrogbte.
     *
     * Most sublcbsses will not need to implement this method.
     *
     * @pbrbm chbrs - the chbrbcter brrby
     * @pbrbm offset - the index of the first chbrbcter
     * @pbrbm count - the number of chbrbcters
     * @pbrbm reverse - if <code>TRUE</code>, store the glyph indices in reverse order.
     * @pbrbm mbpper - the chbrbcter mbpper.
     * @pbrbm filterZeroWidth - <code>TRUE</code> if ZWJ / ZWNJ chbrbcters should mbp to b glyph w/ no contours.
     * @pbrbm glyphStorbge - the object which contbins the output glyph brrby
     *
     * @see LEChbrMbpper
     *
     * @stbble ICU 3.6
     */
    virtubl void mbpChbrsToGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse, const LEChbrMbpper *mbpper, le_bool filterZeroWidth, LEGlyphStorbge &glyphStorbge) const;

    /**
     * This method mbps b single chbrbcter to b glyph index, using the
     * font's chbrbcter to glyph mbp. The defbult implementbtion of this
     * method cblls the mbpper, bnd then cblls <code>mbpChbrToGlyph(mbppedCh)</code>.
     *
     * @pbrbm ch - the chbrbcter
     * @pbrbm mbpper - the chbrbcter mbpper
     * @pbrbm filterZeroWidth - <code>TRUE</code> if ZWJ / ZWNJ chbrbcters should mbp to b glyph w/ no contours.
     *
     * @return the glyph index
     *
     * @see LEChbrMbpper
     *
     * @stbble ICU 3.6
     */
    virtubl LEGlyphID mbpChbrToGlyph(LEUnicode32 ch, const LEChbrMbpper *mbpper, le_bool filterZeroWidth) const;

    /**
     * This method mbps b single chbrbcter to b glyph index, using the
     * font's chbrbcter to glyph mbp. The defbult implementbtion of this
     * method cblls the mbpper, bnd then cblls <code>mbpChbrToGlyph(mbppedCh)</code>.
     *
     * @pbrbm ch - the chbrbcter
     * @pbrbm mbpper - the chbrbcter mbpper
     *
     * @return the glyph index
     *
     * @see LEChbrMbpper
     *
     * @stbble ICU 3.2
     */
    virtubl LEGlyphID mbpChbrToGlyph(LEUnicode32 ch, const LEChbrMbpper *mbpper) const;

    /**
     * This method mbps b single chbrbcter to b glyph index, using the
     * font's chbrbcter to glyph mbp. There is no defbult implementbtion
     * of this method becbuse it requires informbtion bbout the plbtform
     * font implementbtion.
     *
     * @pbrbm ch - the chbrbcter
     *
     * @return the glyph index
     *
     * @stbble ICU 3.2
     */
    virtubl LEGlyphID mbpChbrToGlyph(LEUnicode32 ch) const = 0;

    //
    // Metrics
    //

    /**
     * This method gets the X bnd Y bdvbnce of b pbrticulbr glyph, in pixels.
     *
     * @pbrbm glyph - the glyph index
     * @pbrbm bdvbnce - the X bnd Y pixel vblues will be stored here
     *
     * @stbble ICU 3.2
     */
    virtubl void getGlyphAdvbnce(LEGlyphID glyph, LEPoint &bdvbnce) const = 0;

    virtubl void getKerningAdjustment(LEPoint &bdjustment) const = 0;

    /**
     * This method gets the hinted X bnd Y pixel coordinbtes of b pbrticulbr
     * point in the outline of the given glyph.
     *
     * @pbrbm glyph - the glyph index
     * @pbrbm pointNumber - the number of the point
     * @pbrbm point - the point's X bnd Y pixel vblues will be stored here
     *
     * @return <code>TRUE</code> if the point coordinbtes could be stored.
     *
     * @stbble ICU 2.8
     */
    virtubl le_bool getGlyphPoint(LEGlyphID glyph, le_int32 pointNumber, LEPoint &point) const = 0;

    /**
     * This method returns the width of the font's EM squbre
     * in pixels.
     *
     * @return the pixel width of the EM squbre
     *
     * @stbble ICU 2.8
     */
    virtubl flobt getXPixelsPerEm() const = 0;

    /**
     * This method returns the height of the font's EM squbre
     * in pixels.
     *
     * @return the pixel height of the EM squbre
     *
     * @stbble ICU 2.8
     */
    virtubl flobt getYPixelsPerEm() const = 0;

    /**
     * This method converts font design units in the
     * X direction to points.
     *
     * @pbrbm xUnits - design units in the X direction
     *
     * @return points in the X direction
     *
     * @stbble ICU 3.2
     */
    virtubl flobt xUnitsToPoints(flobt xUnits) const;

    /**
     * This method converts font design units in the
     * Y direction to points.
     *
     * @pbrbm yUnits - design units in the Y direction
     *
     * @return points in the Y direction
     *
     * @stbble ICU 3.2
     */
    virtubl flobt yUnitsToPoints(flobt yUnits) const;

    /**
     * This method converts font design units to points.
     *
     * @pbrbm units - X bnd Y design units
     * @pbrbm points - set to X bnd Y points
     *
     * @stbble ICU 3.2
     */
    virtubl void unitsToPoints(LEPoint &units, LEPoint &points) const;

    /**
     * This method converts pixels in the
     * X direction to font design units.
     *
     * @pbrbm xPixels - pixels in the X direction
     *
     * @return font design units in the X direction
     *
     * @stbble ICU 3.2
     */
    virtubl flobt xPixelsToUnits(flobt xPixels) const;

    /**
     * This method converts pixels in the
     * Y direction to font design units.
     *
     * @pbrbm yPixels - pixels in the Y direction
     *
     * @return font design units in the Y direction
     *
     * @stbble ICU 3.2
     */
    virtubl flobt yPixelsToUnits(flobt yPixels) const;

    /**
     * This method converts pixels to font design units.
     *
     * @pbrbm pixels - X bnd Y pixel
     * @pbrbm units - set to X bnd Y font design units
     *
     * @stbble ICU 3.2
     */
    virtubl void pixelsToUnits(LEPoint &pixels, LEPoint &units) const;

    /**
     * Get the X scble fbctor from the font's trbnsform. The defbult
     * implementbtion of <code>trbnsformFunits()</code> will cbll this method.
     *
     * @return the X scble fbctor.
     *
     *
     * @see trbnsformFunits
     *
     * @stbble ICU 3.2
     */
    virtubl flobt getScbleFbctorX() const = 0;

    /**
     * Get the Y scble fbctor from the font's trbnsform. The defbult
     * implementbtion of <code>trbnsformFunits()</code> will cbll this method.
     *
     * @return the Yscble fbctor.
     *
     * @see trbnsformFunits
     *
     * @stbble ICU 3.2
     */
    virtubl flobt getScbleFbctorY() const = 0;

    /**
     * This method trbnsforms bn X, Y point in font design units to b
     * pixel coordinbte, bpplying the font's trbnsform. The defbult
     * implementbtion of this method cblls <code>getScbleFbctorX()</code>
     * bnd <code>getScbleFbctorY()</code>.
     *
     * @pbrbm xFunits - the X coordinbte in font design units
     * @pbrbm yFunits - the Y coordinbte in font design units
     * @pbrbm pixels - the trbnformed co-ordinbte in pixels
     *
     * @see getScbleFbctorX
     * @see getScbleFbctorY
     *
     * @stbble ICU 3.2
     */
    virtubl void trbnsformFunits(flobt xFunits, flobt yFunits, LEPoint &pixels) const;

    /**
     * This is b convenience method used to convert
     * vblues in b 16.16 fixed point formbt to flobting point.
     *
     * @pbrbm fixed - the fixed point vblue
     *
     * @return the flobting point vblue
     *
     * @stbble ICU 2.8
     */
    stbtic inline flobt fixedToFlobt(le_int32 fixed);

    /**
     * This is b convenience method used to convert
     * flobting point vblues to 16.16 fixed point formbt.
     *
     * @pbrbm theFlobt - the flobting point vblue
     *
     * @return the fixed point vblue
     *
     * @stbble ICU 2.8
     */
    stbtic inline le_int32 flobtToFixed(flobt theFlobt);

    //
    // These methods won't ever be cblled by the LbyoutEngine,
    // but bre useful for clients of <code>LEFontInstbnce</code> who
    // need to render text.
    //

    /**
     * Get the font's bscent.
     *
     * @return the font's bscent, in points. This vblue
     * will blwbys be positive.
     *
     * @stbble ICU 3.2
     */
    virtubl le_int32 getAscent() const = 0;

    /**
     * Get the font's descent.
     *
     * @return the font's descent, in points. This vblue
     * will blwbys be positive.
     *
     * @stbble ICU 3.2
     */
    virtubl le_int32 getDescent() const = 0;

    /**
     * Get the font's lebding.
     *
     * @return the font's lebding, in points. This vblue
     * will blwbys be positive.
     *
     * @stbble ICU 3.2
     */
    virtubl le_int32 getLebding() const = 0;

    /**
     * Get the line height required to displby text in
     * this font. The defbult implementbtion of this method
     * returns the sum of the bscent, descent, bnd lebding.
     *
     * @return the line height, in points. This vbule will
     * blwbys be positive.
     *
     * @stbble ICU 3.2
     */
    virtubl le_int32 getLineHeight() const;

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for the bctubl clbss.
     *
     * @stbble ICU 3.2
     */
    virtubl UClbssID getDynbmicClbssID() const;

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for this clbss.
     *
     * @stbble ICU 3.2
     */
    stbtic UClbssID getStbticClbssID();

};

inline flobt LEFontInstbnce::fixedToFlobt(le_int32 fixed)
{
    return (flobt) (fixed / 65536.0);
}

inline le_int32 LEFontInstbnce::flobtToFixed(flobt theFlobt)
{
    return (le_int32) (theFlobt * 65536.0);
}

U_NAMESPACE_END
#endif
