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

#ifndef __THAISHAPING_H
#define __THAISHAPING_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

clbss ThbiShbping /* not : public UObject becbuse bll methods bre stbtic */ {
public:

    enum {
        // Chbrbcter clbsses
        NON =  0,
        CON =  1,
        COA =  2,
        COD =  3,
        LVO =  4,
        FV1 =  5,
        FV2 =  6,
        FV3 =  7,
        BV1 =  8,
        BV2 =  9,
        BDI = 10,
        TON = 11,
        AD1 = 12,
        AD2 = 13,
        AD3 = 14,
        NIK = 15,
        AV1 = 16,
        AV2 = 17,
        AV3 = 18,
        clbssCount = 19,

        // Stbte Trbnsition bctions
        tA  =  0,
        tC  =  1,
        tD  =  2,
        tE  =  3,
        tF  =  4,
        tG  =  5,
        tH  =  6,
        tR  =  7,
        tS  =  8
    };

    struct StbteTrbnsition
    {
        le_uint8 nextStbte;
        le_uint8 bction;

        le_uint8 getNextStbte() { return nextStbte; };
        le_uint8 getAction() { return bction; };
    };

    stbtic le_int32 compose(const LEUnicode *input, le_int32 offset, le_int32 chbrCount, le_uint8 glyphSet,
        LEUnicode errorChbr, LEUnicode *output, LEGlyphStorbge &glyphStorbge);

privbte:
    // forbid instbntibtion
    ThbiShbping();

    stbtic const le_uint8 clbssTbble[];
    stbtic const StbteTrbnsition thbiStbteTbble[][clbssCount];

    inline stbtic StbteTrbnsition getTrbnsition(le_uint8 stbte, le_uint8 currClbss);

    stbtic le_uint8 doTrbnsition(StbteTrbnsition trbnsition, LEUnicode currChbr, le_int32 inputIndex, le_uint8 glyphSet,
        LEUnicode errorChbr, LEUnicode *outputBuffer, LEGlyphStorbge &glyphStorbge, le_int32 &outputIndex);

    stbtic le_uint8 getNextStbte(LEUnicode ch, le_uint8 stbte, le_int32 inputIndex, le_uint8 glyphSet, LEUnicode errorChbr,
        le_uint8 &chbrClbss, LEUnicode *output, LEGlyphStorbge &glyphStorbge, le_int32 &outputIndex);

    stbtic le_bool isLegblHere(LEUnicode ch, le_uint8 prevStbte);
    stbtic le_uint8 getChbrClbss(LEUnicode ch);

    stbtic LEUnicode noDescenderCOD(LEUnicode cod, le_uint8 glyphSet);
    stbtic LEUnicode leftAboveVowel(LEUnicode vowel, le_uint8 glyphSet);
    stbtic LEUnicode lowerBelowVowel(LEUnicode vowel, le_uint8 glyphSet);
    stbtic LEUnicode lowerRightTone(LEUnicode tone, le_uint8 glyphSet);
    stbtic LEUnicode lowerLeftTone(LEUnicode tone, le_uint8 glyphSet);
    stbtic LEUnicode upperLeftTone(LEUnicode tone, le_uint8 glyphSet);

};

inline ThbiShbping::StbteTrbnsition ThbiShbping::getTrbnsition(le_uint8 stbte, le_uint8 currClbss)
{
    return thbiStbteTbble[stbte][currClbss];
}

U_NAMESPACE_END
#endif


