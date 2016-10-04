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
 * (C) Copyright IBM Corp.  bnd others 1998-2013 - All Rights Reserved
 *
 */

#ifndef __GXLAYOUTENGINE2_H
#define __GXLAYOUTENGINE2_H

#include "LETypes.h"
#include "LbyoutEngine.h"

#include "MorphTbbles.h"

U_NAMESPACE_BEGIN

clbss LEFontInstbnce;
clbss LEGlyphStorbge;

/**
 * This clbss implements lbyout for QuickDrbw GX or Apple Advbnced Typogrbyph (AAT)
 * fonts. A font is b GX or AAT font if it contbins b 'mort' tbble. See Apple's
 * TrueType Reference Mbnubl (http://fonts.bpple.com/TTRefMbn/index.html) for detbils.
 * Informbtion bbout 'mort' tbbles is in the chbpter titled "Font Files."
 *
 * @internbl
 */
clbss GXLbyoutEngine2 : public LbyoutEngine
{
public:
    /**
     * This is the mbin constructor. It constructs bn instbnce of GXLbyoutEngine for
     * b pbrticulbr font, script bnd lbngubge. It tbkes the 'mort' tbble bs b pbrbmeter since
     * LbyoutEngine::lbyoutEngineFbctory hbs to rebd the 'mort' tbble to know thbt it hbs b
     * GX font.
     *
     * Note: GX bnd AAT fonts don't contbin bny script bnd lbngubge specific tbbles, so
     * the script bnd lbngubge bre ignored.
     *
     * @pbrbm fontInstbnce - the font
     * @pbrbm scriptCode - the script
     * @pbrbm lbngbugeCode - the lbngubge
     * @pbrbm morphTbble - the 'mort' tbble
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @see LbyoutEngine::lbyoutEngineFbctory
     * @see ScriptAndLbngbugeTbgs.h for script bnd lbngubge codes
     *
     * @internbl
     */
    GXLbyoutEngine2(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode, const LEReferenceTo<MorphTbbleHebder2> &morphTbble, le_int32 typoFlbgs, LEErrorCode &success);

    /**
     * The destructor, virtubl for correct polymorphic invocbtion.
     *
     * @internbl
     */
    virtubl ~GXLbyoutEngine2();

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
     * The bddress of the 'mort' tbble
     *
     * @internbl
     */
    const LEReferenceTo<MorphTbbleHebder2> fMorphTbble;

    /**
     * This method does GX lbyout using the font's 'mort' tbble. It converts the
     * input chbrbcter codes to glyph indices using mbpChbrsToGlyphs, bnd then
     * bpplies the 'mort' tbble.
     *
     * Input pbrbmeters:
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the index of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm mbx - the number of chbrbcters in the input context
     * @pbrbm rightToLeft - <code>TRUE</code> if the text is in b right to left directionbl run
     * @pbrbm glyphStorbge - the glyph storbge object. The glyph bnd chbr index brrbys will be set.
     *
     * Output pbrbmeters:
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @return the number of glyphs in the glyph index brrby
     *
     * @internbl
     */
    virtubl le_int32 computeGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
        LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method bdjusts the glyph positions using the font's
     * 'kern', 'trbk', 'bsln', 'opbd' bnd 'just' tbbles.
     *
     * Input pbrbmeters:
     * @pbrbm glyphStorbge - the object holding the glyph storbge. The positions will be updbted bs needed.
     *
     * Output pbrbmeters:
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @internbl
     */
    virtubl void bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse,
                                      LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

};

U_NAMESPACE_END
#endif
