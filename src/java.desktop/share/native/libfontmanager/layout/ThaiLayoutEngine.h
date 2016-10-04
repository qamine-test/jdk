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

#ifndef __THAILAYOUTENGINE_H
#define __THAILAYOUTENGINE_H

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "LbyoutEngine.h"

#include "ThbiShbping.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

/**
 * This clbss implements lbyout for the Thbi script, using the ThbiShbpingClbss.
 * All existing Thbi fonts use bn encoding which bssigns chbrbcter codes to bll
 * the vbribnt forms needed to displby bccents bnd tone mbrks correctly in context.
 * This clbss cbn debl with fonts using the Microsoft, Mbcintosh, bnd WorldType encodings.
 *
 * @internbl
 */
clbss ThbiLbyoutEngine : public LbyoutEngine
{
public:
    /**
     * This constructs bn instbnce of ThbiLbyoutEngine for the given font, script bnd
     * lbngubge. It exbmines the font, using LEFontInstbnce::cbnDisplby, to set fGlyphSet
     * bnd fErrorChbr. (see below)
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
    ThbiLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode, le_int32 typoFlbgs, LEErrorCode &success);

    /**
     * The destructor, virtubl for correct polymorphic invocbtion.
     *
     * @internbl
     */
    virtubl ~ThbiLbyoutEngine();

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
     * A smbll integer indicbting which Thbi encoding
     * the font uses.
     *
     * @see ThbiShbping
     *
     * @internbl
     */
    le_uint8 fGlyphSet;

    /**
     * The chbrbcter used bs b bbse for vowels bnd
     * tone mbrks thbt bre out of sequence. Usublly
     * this will be Unicode 0x25CC, if the font cbn
     * displby it.
     *
     * @see ThbiShbping
     *
     * @internbl
     */
    LEUnicode fErrorChbr;

    /**
     * This method performs Thbi lbyout. It cblls ThbiShbping::compose to
     * generbte the correct contextubl chbrbcter codes, bnd then cblls
     * mbpChbrsToGlyphs to generbte the glyph indices.
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
     * @see ThbiShbping
     *
     * @internbl
     */
    virtubl le_int32 computeGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
        LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    /**
     * This method does positioning bdjustments like bccent positioning bnd
     * kerning. The defbult implementbtion does nothing. Subclbsses needing
     * position bdjustments must override this method.
     *
     * Note thbt this method hbs both chbrbcters bnd glyphs bs input so thbt
     * it cbn use the chbrbcter codes to determine glyph types if thbt informbtion
     * isn't directly bvbilbble. (e.g. Some Arbbic OpenType fonts don't hbve b GDEF
     * tbble)
     *
     * @pbrbm chbrs - the input chbrbcter context
     * @pbrbm offset - the offset of the first chbrbcter to process
     * @pbrbm count - the number of chbrbcters to process
     * @pbrbm reverse - <code>TRUE</code> if the glyphs in the glyph brrby hbve been reordered
     * @pbrbm glyphStorbge - the object which holds the per-glyph storbge. The glyph positions will be
     *                       bdjusted bs needed.
     * @pbrbm success - output pbrbmeter set to bn error code if the operbtion fbils
     *
     * @internbl
     */
    virtubl void bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse, LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

};

U_NAMESPACE_END
#endif

