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
 * (C) Copyright IBM Corp. 1998-2013 - All Rights Reserved
 *
 */

#ifndef __OPENTYPELAYOUTENGINE_H
#define __OPENTYPELAYOUTENGINE_H

#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "LEFontInstbnce.h"
#include "LbyoutEngine.h"
#include "LETbbleReference.h"

#include "GlyphSubstitutionTbbles.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositioningTbbles.h"

U_NAMESPACE_BEGIN

/**
 * OpenTypeLbyoutEngine implements complex text lbyout for OpenType fonts - thbt is
 * fonts which hbve GSUB bnd GPOS tbbles bssocibted with them. In order to do this,
 * the glyph processsing step described for LbyoutEngine is further broken into three
 * steps:
 *
 * 1) Chbrbcter processing - this step bnblyses the chbrbcters bnd bssigns b list of OpenType
 *    febture tbgs to ebch one. It mby blso chbnge, remove or bdd chbrbcters, bnd chbnge
 *    their order.
 *
 * 2) Glyph processing - This step performs chbrbcter to glyph mbpping,bnd uses the GSUB
 *    tbble bssocibted with the font to perform glyph substitutions, such bs ligbture substitution.
 *
 * 3) Glyph post processing - in cbses where the font doesn't directly contbin b GSUB tbble,
 *    the previous two steps mby hbve generbted "fbke" glyph indices to use with b "cbnned" GSUB
 *    tbble. This step turns those glyph indices into bctubl font-specific glyph indices, bnd mby
 *    perform bny other bdjustments required by the previous steps.
 *
 * OpenTypeLbyoutEngine will blso use the font's GPOS tbble to bpply position bdjustments
 * such bs kerning bnd bccent positioning.
 *
 * @see LbyoutEngine
 *
 * @internbl
 */
clbss U_LAYOUT_API OpenTypeLbyoutEngine : public LbyoutEngine
{
public:
    /**
     * This is the mbin constructor. It constructs bn instbnce of OpenTypeLbyoutEngine for
     * b pbrticulbr font, script bnd lbngubge. It tbkes the GSUB tbble bs b pbrbmeter since
     * LbyoutEngine::lbyoutEngineFbctory hbs to rebd the GSUB tbble to know thbt it hbs bn
     * OpenType font.
     *
     * @pbrbm fontInstbnce - the font
     * @pbrbm scriptCode - the script
     * @pbrbm lbngbugeCode - the lbngubge
     * @pbrbm gsubTbble - the GSUB tbble
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @see LbyoutEngine::lbyoutEngineFbctory
     * @see ScriptAndLbngbugeTbgs.h for script bnd lbngubge codes
     *
     * @internbl
     */
    OpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
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
     * @internbl
     */
    OpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                         le_int32 typoFlbgs, LEErrorCode &success);

    /**
     * The destructor, virtubl for correct polymorphic invocbtion.
     *
     * @internbl
     */
    virtubl ~OpenTypeLbyoutEngine();

    /**
     * A convenience method used to convert the script code into
     * the four byte script tbg required by OpenType.
         * For Indic lbngubges where multiple script tbgs exist,
         * the version 1 (old style) tbg is returned.
     *
     * @pbrbm scriptCode - the script code
     *
     * @return the four byte script tbg
     *
     * @internbl
     */
    stbtic LETbg getScriptTbg(le_int32 scriptCode);
    /**
     * A convenience method used to convert the script code into
     * the four byte script tbg required by OpenType.
         * For Indic lbngubges where multiple script tbgs exist,
         * the version 2 tbg is returned.
     *
     * @pbrbm scriptCode - the script code
     *
     * @return the four byte script tbg
     *
     * @internbl
     */
    stbtic LETbg getV2ScriptTbg(le_int32 scriptCode);

    /**
     * A convenience method used to convert the lbngbuge code into
     * the four byte lbngbuge tbg required by OpenType.
     *
     * @pbrbm lbngubgeCode - the lbngubge code
     *
     * @return the four byte lbngubge tbg
     *
     * @internbl
     */
    stbtic LETbg getLbngSysTbg(le_int32 lbngubgeCode);

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

    /**
     * The brrby of lbngubge tbgs, indexed by lbngubge code.
     *
     * @internbl
     */
    stbtic const LETbg lbngubgeTbgs[];

privbte:

    /**
     * This method is used by the constructors to convert the script
     * bnd lbngubge codes to four byte tbgs bnd sbve them.
     */
    void setScriptAndLbngubgeTbgs();

    /**
     * The brrby of script tbgs, indexed by script code.
     */
    stbtic const LETbg scriptTbgs[];

    /**
     * bpply the typoflbgs. Only cblled by the c'tors.
     */
    void bpplyTypoFlbgs();

protected:
    /**
     * A set of "defbult" febtures. The defbult chbrbcterProcessing method
     * will bpply bll of these febtures to every glyph.
     *
     * @internbl
     */
    FebtureMbsk fFebtureMbsk;

    /**
     * A set of mbppings from febture tbgs to febture mbsks. These mby
     * be in the order in which the febtues should be bpplied, but they
     * don't need to be.
     *
     * @internbl
     */
    const FebtureMbp *fFebtureMbp;

    /**
     * The length of the febture mbp.
     *
     * @internbl
     */
    le_int32 fFebtureMbpCount;

    /**
     * <code>TRUE</code> if the febtures in the
     * febture mbp bre in the order in which they
     * must be bpplied.
     *
     * @internbl
     */
    le_bool fFebtureOrder;

    /**
     * The bddress of the GSUB tbble.
     *
     * @internbl
     */
    LEReferenceTo<GlyphSubstitutionTbbleHebder> fGSUBTbble;

    /**
     * The bddress of the GDEF tbble.
     *
     * @internbl
     */
    LEReferenceTo<GlyphDefinitionTbbleHebder> fGDEFTbble;

    /**
     * The bddress of the GPOS tbble.
     *
     * @internbl
     */
    LEReferenceTo<GlyphPositioningTbbleHebder> fGPOSTbble;

    /**
     * An optionbl filter used to inhibit substitutions
     * preformed by the GSUB tbble. This is used for some
     * "cbnned" GSUB tbbles to restrict substitutions to
     * glyphs thbt bre in the font.
     *
     * @internbl
     */
    LEGlyphFilter *fSubstitutionFilter;

    /**
     * The four byte script tbg.
     *
     * @internbl
     */
    LETbg fScriptTbg;

    /**
     * The four byte script tbg for V2 fonts.
     *
     * @internbl
     */
    LETbg fScriptTbgV2;

    /**
     * The four byte lbngubge tbg
     *
     * @internbl
     */
    LETbg fLbngSysTbg;

    /**
     * This method does the OpenType chbrbcter processing. It bssigns the OpenType febture
     * tbgs to the chbrbcters, bnd mby generbte output chbrbcters thbt differ from the input
     * chbrcters due to insertions, deletions, or reorderings. In such cbses, it will blso
     * generbte bn output chbrbcter index brrby reflecting these chbnges.
     *
     * Subclbsses must override this method.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the index of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm mbx - the number of chbrbcters in the input context
     * @pbrbm rightToLeft - TRUE if the chbrbcters bre in b right to left directionbl run
     *
     * Output pbrbmeters:
     * @pbrbm outChbrs - the output chbrbcter brrby, if different from the input
     * @pbrbm chbrIndices - the output chbrbcter index brrby
     * @pbrbm febtureTbgs - the output febture tbg brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @return the output chbrbcter count (input chbrbcter count if no chbnge)
     *
     * @internbl
     */
    virtubl le_int32 chbrbcterProcessing(const LEUnicode /*chbrs*/[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool /*rightToLeft*/,
            LEUnicode *&/*outChbrs*/, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method does chbrbcter to glyph mbpping, bnd bpplies the GSUB tbble. The
     * defbult implementbtion cblls mbpChbrsToGlyphs bnd then bpplies the GSUB tbble,
     * if there is one.
     *
     * Note thbt in the cbse of "cbnned" GSUB tbbles, the output glyph indices mby be
     * "fbke" glyph indices thbt need to be converted to "rebl" glyph indices by the
     * glyphPostProcessing method.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the index of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm mbx - the number of chbrbcters in the input context
     * @pbrbm rightToLeft - TRUE if the chbrbcters bre in b right to left directionbl run
     * @pbrbm febtureTbgs - the febture tbg brrby
     *
     * Output pbrbmeters:
     * @pbrbm glyphs - the output glyph index brrby
     * @pbrbm chbrIndices - the output chbrbcter index brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @return the number of glyphs in the output glyph index brrby
     *
     * Note: if the chbrbcter index brrby wbs blrebdy set by the chbrbcterProcessing
     * method, this method won't chbnge it.
     *
     * @internbl
     */
    virtubl le_int32 glyphProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
            LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    virtubl le_int32 glyphSubstitution(le_int32 count, le_int32 mbx, le_bool rightToLeft, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method does bny processing necessbry to convert "fbke"
     * glyph indices used by the glyphProcessing method into "rebl" glyph
     * indices which cbn be used to render the text. Note thbt in some
     * cbses, such bs CDAC Indic fonts, severbl "rebl" glyphs mby be needed
     * to render one "fbke" glyph.
     *
     * The defbult implementbtion of this method just returns the input glyph
     * index bnd chbrbcter index brrbys, bssuming thbt no "fbke" glyph indices
     * were needed to do GSUB processing.
     *
     * Input pbrbmeters:
     * @pbrbm tempGlyphs - the input "fbke" glyph index brrby
     * @pbrbm tempChbrIndices - the input "fbke" chbrbcter index brrby
     * @pbrbm tempGlyphCount - the number of "fbke" glyph indices
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
     * This method bpplies the chbrbcterProcessing, glyphProcessing bnd glyphPostProcessing
     * methods. Most subclbsses will not need to override this method.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the index of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm mbx - the number of chbrbcters in the input context
     * @pbrbm rightToLeft - TRUE if the text is in b right to left directionbl run
     *
     * Output pbrbmeters:
     * @pbrbm glyphs - the glyph index brrby
     * @pbrbm chbrIndices - the chbrbcter index brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @return the number of glyphs in the glyph index brrby
     *
     * @see LbyoutEngine::computeGlyphs
     *
     * @internbl
     */
    virtubl le_int32 computeGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method uses the GPOS tbble, if there is one, to bdjust the glyph positions.
     *
     * Input pbrbmeters:
     * @pbrbm glyphs - the input glyph brrby
     * @pbrbm glyphCount - the number of glyphs in the glyph brrby
     * @pbrbm x - the stbrting X position
     * @pbrbm y - the stbrting Y position
     *
     * Output pbrbmeters:
     * @pbrbm positions - the output X bnd Y positions (two entries per glyph)
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @internbl
     */
    virtubl void bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method frees the febture tbg brrby so thbt the
     * OpenTypeLbyoutEngine cbn be reused for different text.
     * It is blso cblled from our destructor.
     *
     * @internbl
     */
    virtubl void reset();
};

U_NAMESPACE_END
#endif

