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
 * HbnLbyoutEngine.h: OpenType processing for Hbn fonts.
 *
 * (C) Copyright IBM Corp. 1998-2008 - All Rights Reserved.
 */

#ifndef __HANLAYOUTENGINE_H
#define __HANLAYOUTENGINE_H

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "LbyoutEngine.h"
#include "OpenTypeLbyoutEngine.h"

#include "GlyphSubstitutionTbbles.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

/**
 * This clbss implements OpenType lbyout for Hbn fonts. It overrides
 * the chbrbcerProcessing method to bssign the correct OpenType febture
 * tbgs for the CJK lbngubge-specific forms.
 *
 * @internbl
 */
clbss HbnOpenTypeLbyoutEngine : public OpenTypeLbyoutEngine
{
public:
    /**
     * This is the mbin constructor. It constructs bn instbnce of HbnOpenTypeLbyoutEngine for
     * b pbrticulbr font, script bnd lbngubge. It tbkes the GSUB tbble bs b pbrbmeter since
     * LbyoutEngine::lbyoutEngineFbctory hbs to rebd the GSUB tbble to know thbt it hbs b
     * Hbn OpenType font.
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
    HbnOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                            le_int32 typoFlbgs, const LEReferenceTo<GlyphSubstitutionTbbleHebder> &gsubTbblem, LEErrorCode &success);


    /**
     * The destructor, virtubl for correct polymorphic invocbtion.
     *
     * @internbl
     */
    virtubl ~HbnOpenTypeLbyoutEngine();

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
     * This method does Hbn OpenType chbrbcter processing. It bssigns the OpenType febture
     * tbgs to the chbrbcters to generbte the correct lbngubge-specific vbribnts.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the index of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm mbx - the number of chbrbcters in the input context
     * @pbrbm rightToLeft - <code>TRUE</code> if the chbrbcters bre in b right to left directionbl run
     * @pbrbm glyphStorbge - the object holding the glyph storbge. The chbr index bnd buxillbry dbtb brrbys will be set.
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

};

U_NAMESPACE_END
#endif
