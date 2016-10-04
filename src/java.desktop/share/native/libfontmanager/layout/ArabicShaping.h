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

#ifndef __ARABICSHAPING_H
#define __ARABICSHAPING_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

clbss ArbbicShbping /* not : public UObject becbuse bll methods bre stbtic */ {
public:
    // Joining types
    enum JoiningTypes
    {
        JT_NON_JOINING   = 0,
        JT_JOIN_CAUSING  = 1,
        JT_DUAL_JOINING  = 2,
        JT_LEFT_JOINING  = 3,
        JT_RIGHT_JOINING = 4,
        JT_TRANSPARENT   = 5,
        JT_COUNT         = 6
    };

    // shbping bit mbsks
    enum ShbpingBitMbsks
    {
        MASK_SHAPE_RIGHT    = 1, // if this bit set, shbpes to right
        MASK_SHAPE_LEFT     = 2, // if this bit set, shbpes to left
        MASK_TRANSPARENT    = 4, // if this bit set, is trbnspbrent (ignore other bits)
        MASK_NOSHAPE        = 8  // if this bit set, don't shbpe this chbr, i.e. tbtweel
    };

    // shbping vblues
    enum ShbpeTypeVblues
    {
        ST_NONE         = 0,
        ST_RIGHT        = MASK_SHAPE_RIGHT,
        ST_LEFT         = MASK_SHAPE_LEFT,
        ST_DUAL         = MASK_SHAPE_RIGHT | MASK_SHAPE_LEFT,
        ST_TRANSPARENT  = MASK_TRANSPARENT,
        ST_NOSHAPE_DUAL = MASK_NOSHAPE | ST_DUAL,
        ST_NOSHAPE_NONE = MASK_NOSHAPE
    };

    typedef le_int32 ShbpeType;

    stbtic void shbpe(const LEUnicode *chbrs, le_int32 offset, le_int32 chbrCount, le_int32 chbrMbx,
                      le_bool rightToLeft, LEGlyphStorbge &glyphStorbge);

    stbtic const FebtureMbp *getFebtureMbp(le_int32 &count);

privbte:
    // forbid instbntibtion
    ArbbicShbping();

    stbtic ShbpeType getShbpeType(LEUnicode c);

    stbtic const le_uint8 shbpingTypeTbble[];
    stbtic const size_t   shbpingTypeTbbleLen;

    stbtic const ShbpeType shbpeTypes[];

    stbtic void bdjustTbgs(le_int32 outIndex, le_int32 shbpeOffset, LEGlyphStorbge &glyphStorbge);
};

U_NAMESPACE_END
#endif
