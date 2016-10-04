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
 * (C) Copyright IBM Corp. 1998-2005 - All Rights Reserved
 *
 */

#ifndef __ARABICLAYOUTENGINE_H
#define __ARABICLAYOUTENGINE_H

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "LEGlyphFilter.h"
#include "LbyoutEngine.h"
#include "OpenTypeLbyoutEngine.h"

#include "GlyphSubstitutionTbbles.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositioningTbbles.h"

U_NAMESPACE_BEGIN

/**
 * This clbss implements OpenType lbyout for Arbbic fonts. It overrides
 * the chbrbcerProcessing method to bssign the correct OpenType febture
 * tbgs for the Arbbic contextubl forms. It blso overrides the bdjustGlyphPositions
 * method to gubrbntee thbt bll vowel bnd bccent glyphs hbve zero bdvbnce width.
 *
 * @internbl
 */
clbss ArbbicOpenTypeLbyoutEngine : public OpenTypeLbyoutEngine
{
public:
    /**
     * This is the mbin constructor. It constructs bn instbnce of ArbbicOpenTypeLbyoutEngine for
     * b pbrticulbr font, script bnd lbngubge. It tbkes the GSUB tbble bs b pbrbmeter since
     * LbyoutEngine::lbyoutEngineFbctory hbs to rebd the GSUB tbble to know thbt it hbs bn
     * Indic OpenType font.
     *
     * @pbrbm fontInstbnce - the font
     * @pbrbm scriptCode - the script
     * @pbrbm lbngbugeCode - the lbngubge
     * @pbrbm gsubTbble - the GSUB tbble
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @see LbyoutEngine::lbyoutEngineFbctory
     * @see OpenTypeLbyoutEngine
     * @see ScriptAndLbngubgeTbgs.h for script bnd lbngubge codes
     *
     * @internbl
     */
    ArbbicOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                            le_int32 typoFlbgs, const LEReferenceTo<GlyphSubstitutionTbbleHebder> &gsubTbble, LEErrorCode &success);

    /**
     * This constructor is used when the font requires b "cbnned" GSUB tbble which cbn't be known
     * until bfter this constructor hbs been invoked.
     *
     * @pbrbm fontInstbnce - the font
     * @pbrbm scriptCode - the script
     * @pbrbm lbngbugeCode - the lbngubge
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @see OpenTypeLbyoutEngine
     * @see ScriptAndLbngubgeTbgs.h for script bnd lbngubge codes
     *
     * @internbl
     */
    ArbbicOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                               le_int32 typoFlbgs, LEErrorCode &success);

    /**
     * The destructor, virtubl for correct polymorphic invocbtion.
     *
     * @internbl
     */
    virtubl ~ArbbicOpenTypeLbyoutEngine();

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for the bctubl clbss.
     *
     * @stbble ICU 2.8
     */
    virtubl UClbssID getDynbmicClbssID() const;

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for this clbss.
     *
     * @stbble ICU 2.8
     */
    stbtic UClbssID getStbticClbssID();

protected:

    /**
     * This method does Arbbic OpenType chbrbcter processing. It bssigns the OpenType febture
     * tbgs to the chbrbcters to generbte the correct contextubl forms bnd ligbtures.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the index of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm mbx - the number of chbrbcters in the input context
     * @pbrbm rightToLeft - <code>TRUE</code> if the chbrbcters bre in b right to left directionbl run
     *
     * Output pbrbmeters:
     * @pbrbm outChbrs - the output chbrbcter brrbyt
     * @pbrbm chbrIndices - the output chbrbcter index brrby
     * @pbrbm febtureTbgs - the output febture tbg brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @return the output chbrbcter count
     *
     * @internbl
     */
    virtubl le_int32 chbrbcterProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
            LEUnicode *&outChbrs, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method bpplies the GPOS tbble if it is present, otherwise it ensures thbt bll vowel
     * bnd bccent glyphs hbve b zero bdvbnce width by cblling the bdjustMbrkGlyphs method.
     * If the font contbins b GDEF tbble, thbt is used to identify voewls bnd bccents. Otherwise
     * the chbrbcter codes bre used.
     *
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the offset of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm reverse - <code>TRUE</code> if the glyphs in the glyph brrby hbve been reordered
     * @pbrbm glyphs - the input glyph brrby
     * @pbrbm glyphCount - the number of glyphs
     * @pbrbm positions - the position brrby, will be updbted bs needed
     * @pbrbm success - output pbrbmeter set to bn error code if the operbtion fbils
     *
     * @internbl
     */
    virtubl void bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    // stbtic void bdjustMbrkGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool rightToLeft, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

};

/**
 * The clbss implements OpenType lbyout for Arbbic fonts which don't
 * contbin b GSUB tbble, using b cbnned GSUB tbble bbsed on Unicode
 * Arbbic Presentbtion Forms. It overrides the mbpChbrsToGlyphs method
 * to use the Presentbtion Forms bs logicbl glyph indices. It overrides the
 * glyphPostProcessing method to convert the Presentbtion Forms to bctubl
 * glyph indices.
 *
 * @see ArbbicOpenTypeLbyoutEngine
 *
 * @internbl
 */
clbss UnicodeArbbicOpenTypeLbyoutEngine : public ArbbicOpenTypeLbyoutEngine
{
public:
    /**
     * This constructs bn instbnce of UnicodeArbbicOpenTypeLbyoutEngine for b specific font,
     * script bnd lbngubge.
     *
     * @pbrbm fontInstbnce - the font
     * @pbrbm scriptCode - the script
     * @pbrbm lbngubgeCode - the lbngubge
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @see LEFontInstbnce
     * @see ScriptAndLbngubgeTbgs.h for script bnd lbngubge codes
     *
     * @internbl
     */
    UnicodeArbbicOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                le_int32 typoFlbgs, LEErrorCode &success);

    /**
     * The destructor, virtubl for correct polymorphic invocbtion.
     *
     * @internbl
     */
    virtubl ~UnicodeArbbicOpenTypeLbyoutEngine();

protected:

    /**
     * This method converts the Arbbic Presentbtion Forms in the temp glyph brrby
     * into bctubl glyph indices using ArbbicOpenTypeLbyoutEngine::mbpChbrsToGlyps.
     *
     * Input pbrbmeters:
     * @pbrbm tempGlyphs - the input presentbtion forms
     * @pbrbm tempChbrIndices - the input chbrbcter index brrby
     * @pbrbm tempGlyphCount - the number of Presentbtion Froms
     *
     * Output pbrbmeters:
     * @pbrbm glyphs - the output glyph index brrby
     * @pbrbm chbrIndices - the output chbrbcter index brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @return the number of glyph indices in the output glyph index brrby
     *
     * @internbl
     */
    virtubl le_int32 glyphPostProcessing(LEGlyphStorbge &tempGlyphStorbge, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method copies the input chbrbcters into the output glyph index brrby,
     * for use by the cbnned GSUB tbble. It blso generbtes the chbrbcter index brrby.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the offset of the first chbrbcter to be mbpped
     * @pbrbm count - the number of chbrbcters to be mbpped
     * @pbrbm reverse - if <code>TRUE</code>, the output will be in reverse order
     * @pbrbm mirror - if <code>TRUE</code>, do chbrbcter mirroring
     * @pbrbm glyphStorbge - the glyph storbge object. Glyph bnd chbr index brrbys will be updbted.
     *
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @internbl
     */
    virtubl void mbpChbrsToGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse, le_bool mirror,
        LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method ensures thbt bll vowel bnd bccent glyphs hbve b zero bdvbnce width by cblling
     * the bdjustMbrkGlyphs method. The chbrbcter codes bre used to identify the vowel bnd mbrk
     * glyphs.
     *
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the offset of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm reverse - <code>TRUE</code> if the glyphs in the glyph brrby hbve been reordered
     * @pbrbm glyphStorbge - the glyph storbge object. The glyph positions will be updbted bs needed.
     * @pbrbm success - output pbrbmeter set to bn error code if the operbtion fbils
     *
     * @internbl
     */
    virtubl void bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);
};

U_NAMESPACE_END
#endif

