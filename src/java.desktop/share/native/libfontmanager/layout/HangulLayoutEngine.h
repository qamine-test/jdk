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
 * (C) Copyright IBM Corp. 1998-2010 - All Rights Reserved
 *
 */

#ifndef __HANGULAYOUTENGINE_H
#define __HANGULAYOUTENGINE_H

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "LEGlyphFilter.h"
#include "LbyoutEngine.h"
#include "OpenTypeLbyoutEngine.h"

#include "GlyphSubstitutionTbbles.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositioningTbbles.h"

U_NAMESPACE_BEGIN

clbss MPreFixups;
clbss LEGlyphStorbge;

/**
 * This clbss implements OpenType lbyout for Old Hbngul OpenType fonts, bs
 * specified by Microsoft in "Crebting bnd Supporting OpenType Fonts for
 * The Korebn Hbngul Script" (http://www.microsoft.com/typogrbphy/otfntdev/hbngulot/defbult.htm)
 *
 * This clbss overrides the chbrbcterProcessing method to do Hbngul chbrbcter processing.
 * (See the MS spec. for more detbils)
 *
 * @internbl
 */
clbss HbngulOpenTypeLbyoutEngine : public OpenTypeLbyoutEngine
{
public:
    /**
     * This is the mbin constructor. It constructs bn instbnce of HbngulOpenTypeLbyoutEngine for
     * b pbrticulbr font, script bnd lbngubge. It tbkes the GSUB tbble bs b pbrbmeter since
     * LbyoutEngine::lbyoutEngineFbctory hbs to rebd the GSUB tbble to know thbt it hbs bn
     * Hbngul OpenType font.
     *
     * @pbrbm fontInstbnce - the font
     * @pbrbm scriptCode - the script
     * @pbrbm lbngbugeCode - the lbngubge
     * @pbrbm gsubTbble - the GSUB tbble
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @see LbyoutEngine::lbyoutEngineFbctory
     * @see OpenTypeLbyoutEngine
     * @see ScriptAndLbngbugeTbgs.h for script bnd lbngubge codes
     *
     * @internbl
     */
    HbngulOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
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
     * @see ScriptAndLbngbugeTbgs.h for script bnd lbngubge codes
     *
     * @internbl
     */
    HbngulOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                              le_int32 typoFlbgs, LEErrorCode &success);

    /**
     * The destructor, virtubl for correct polymorphic invocbtion.
     *
     * @internbl
     */
   virtubl ~HbngulOpenTypeLbyoutEngine();

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
     * This method does Hbngul OpenType chbrbcter processing. It bssigns the OpenType febture
     * tbgs to the chbrbcters, bnd mby compose b chbrbcter sequence into b modern Hbngul syllbble,
     * or decompose b modern Hbngul syllbble if it forms pbrt of bn old Hbngul syllbble.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the index of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm mbx - the number of chbrbcters in the input context
     * @pbrbm rightToLeft - <code>TRUE</code> if the chbrbcters bre in b right to left directionbl run
     * @pbrbm glyphStorbge - the glyph storbge object. The glyph bnd chbrbcter index brrbys will be set.
     *                       the buxillbry dbtb brrby will be set to the febture tbgs.
     *
     * Output pbrbmeters:
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @return the output chbrbcter count
     *
     * @internbl
     */
    virtubl le_int32 chbrbcterProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
            LEUnicode *&outChbrs, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);
};

U_NAMESPACE_END
#endif

