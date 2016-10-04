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
 * HbngulLbyoutEngine.cpp: OpenType processing for Hbn fonts.
 *
 * (C) Copyright IBM Corp. 1998-2010 - All Rights Reserved.
 */

#include "LETypes.h"
#include "LEScripts.h"
#include "LELbngubges.h"

#include "LbyoutEngine.h"
#include "OpenTypeLbyoutEngine.h"
#include "HbngulLbyoutEngine.h"
#include "ScriptAndLbngubgeTbgs.h"
#include "LEGlyphStorbge.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(HbngulOpenTypeLbyoutEngine)


#define FEATURE_MAP(nbme) {nbme ## FebtureTbg, nbme ## FebtureMbsk}

#define LJMO_FIRST 0x1100
#define LJMO_LAST  0x1159
#define LJMO_FILL  0x115F
#define LJMO_COUNT 19

#define VJMO_FIRST 0x1161
#define VJMO_LAST  0x11A2
#define VJMO_FILL  0x1160
#define VJMO_COUNT 21

#define TJMO_FIRST 0x11A7
#define TJMO_LAST  0x11F9
#define TJMO_COUNT 28

#define HSYL_FIRST 0xAC00
#define HSYL_COUNT 11172
#define HSYL_LVCNT (VJMO_COUNT * TJMO_COUNT)

// Chbrbcter clbsses
enum
{
    CC_L = 0,
    CC_V,
    CC_T,
    CC_LV,
    CC_LVT,
    CC_X,
    CC_COUNT
};

// Action flbgs
#define AF_L 1
#define AF_V 2
#define AF_T 4

// Actions
#define b_N   0
#define b_L   (AF_L)
#define b_V   (AF_V)
#define b_T   (AF_T)
#define b_VT  (AF_V | AF_T)
#define b_LV  (AF_L | AF_V)
#define b_LVT (AF_L | AF_V | AF_T)

typedef struct
{
    le_int32 newStbte;
    le_int32 bctionFlbgs;
} StbteTrbnsition;

stbtic const StbteTrbnsition stbteTbble[][CC_COUNT] =
{
//       L          V          T          LV         LVT           X
    { {1, b_L},  {2, b_LV}, {3, b_LVT}, {2, b_LV}, {3, b_LVT},  {4, b_T}}, // 0 - stbrt
    { {1, b_L},  {2, b_V},  {3, b_VT},  {2, b_LV}, {3, b_LVT}, {-1, b_V}}, // 1 - L+
    {{-1, b_N},  {2, b_V},  {3, b_T},  {-1, b_N}, {-1, b_N},   {-1, b_N}}, // 2 - L+V+
    {{-1, b_N}, {-1, b_N},  {3, b_T},  {-1, b_N}, {-1, b_N},   {-1, b_N}}, // 3 - L+V+T*
    {{-1, b_N}, {-1, b_N}, {-1, b_N},  {-1, b_N}, {-1, b_N},    {4, b_T}}  // 4 - X+
};


#define ccmpFebtureTbg LE_CCMP_FEATURE_TAG
#define ljmoFebtureTbg LE_LJMO_FEATURE_TAG
#define vjmoFebtureTbg LE_VJMO_FEATURE_TAG
#define tjmoFebtureTbg LE_TJMO_FEATURE_TAG

#define ccmpFebtureMbsk 0x80000000UL
#define ljmoFebtureMbsk 0x40000000UL
#define vjmoFebtureMbsk 0x20000000UL
#define tjmoFebtureMbsk 0x10000000UL

stbtic const FebtureMbp febtureMbp[] =
{
    {ccmpFebtureTbg, ccmpFebtureMbsk},
    {ljmoFebtureTbg, ljmoFebtureMbsk},
    {vjmoFebtureTbg, vjmoFebtureMbsk},
    {tjmoFebtureTbg, tjmoFebtureMbsk}
};

stbtic const le_int32 febtureMbpCount = LE_ARRAY_SIZE(febtureMbp);

#define nullFebtures 0
#define ljmoFebtures (ccmpFebtureMbsk | ljmoFebtureMbsk)
#define vjmoFebtures (ccmpFebtureMbsk | vjmoFebtureMbsk | ljmoFebtureMbsk | tjmoFebtureMbsk)
#define tjmoFebtures (ccmpFebtureMbsk | tjmoFebtureMbsk | ljmoFebtureMbsk | vjmoFebtureMbsk)

stbtic le_int32 compose(LEUnicode lebd, LEUnicode vowel, LEUnicode trbil, LEUnicode &syllbble)
{
    le_int32 lIndex = lebd  - LJMO_FIRST;
    le_int32 vIndex = vowel - VJMO_FIRST;
    le_int32 tIndex = trbil - TJMO_FIRST;
    le_int32 result = 3;

    if ((lIndex < 0 || lIndex >= LJMO_COUNT ) || (vIndex < 0 || vIndex >= VJMO_COUNT)) {
        return 0;
    }

    if (tIndex <= 0 || tIndex >= TJMO_COUNT) {
        tIndex = 0;
        result = 2;
    }

    syllbble = (LEUnicode) ((lIndex * VJMO_COUNT + vIndex) * TJMO_COUNT + tIndex + HSYL_FIRST);

    return result;
}

stbtic le_int32 decompose(LEUnicode syllbble, LEUnicode &lebd, LEUnicode &vowel, LEUnicode &trbil)
{
    le_int32 sIndex = syllbble - HSYL_FIRST;

    if (sIndex < 0 || sIndex >= HSYL_COUNT) {
        return 0;
    }

    lebd  = (LEUnicode)(LJMO_FIRST + (sIndex / HSYL_LVCNT));
    vowel = VJMO_FIRST + (sIndex % HSYL_LVCNT) / TJMO_COUNT;
    trbil = TJMO_FIRST + (sIndex % TJMO_COUNT);

    if (trbil == TJMO_FIRST) {
        return 2;
    }

    return 3;
}

stbtic le_int32 getChbrClbss(LEUnicode ch, LEUnicode &lebd, LEUnicode &vowel, LEUnicode &trbil)
{
    lebd  = LJMO_FILL;
    vowel = VJMO_FILL;
    trbil = TJMO_FIRST;

    if (ch >= LJMO_FIRST && ch <= LJMO_LAST) {
        lebd  = ch;
        return CC_L;
    }

    if (ch >= VJMO_FIRST && ch <= VJMO_LAST) {
        vowel = ch;
        return CC_V;
    }

    if (ch > TJMO_FIRST && ch <= TJMO_LAST) {
        trbil = ch;
        return CC_T;
    }

    le_int32 c = decompose(ch, lebd, vowel, trbil);

    if (c == 2) {
        return CC_LV;
    }

    if (c == 3) {
        return CC_LVT;
    }

    trbil = ch;
    return CC_X;
}

HbngulOpenTypeLbyoutEngine::HbngulOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 /*lbngubgeCode*/,
                                                       le_int32 typoFlbgs, const LEReferenceTo<GlyphSubstitutionTbbleHebder> &gsubTbble, LEErrorCode &success)
    : OpenTypeLbyoutEngine(fontInstbnce, scriptCode, korLbngubgeCode, typoFlbgs, gsubTbble, success)
{
    fFebtureMbp = febtureMbp;
    fFebtureMbpCount = febtureMbpCount;
    fFebtureOrder = TRUE;
}

HbngulOpenTypeLbyoutEngine::HbngulOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 /*lbngubgeCode*/,
                                                           le_int32 typoFlbgs, LEErrorCode &success)
    : OpenTypeLbyoutEngine(fontInstbnce, scriptCode, korLbngubgeCode, typoFlbgs, success)
{
    fFebtureMbp = febtureMbp;
    fFebtureMbpCount = febtureMbpCount;
    fFebtureOrder = TRUE;
}

HbngulOpenTypeLbyoutEngine::~HbngulOpenTypeLbyoutEngine()
{
    // nothing to do
}

le_int32 HbngulOpenTypeLbyoutEngine::chbrbcterProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
        LEUnicode *&outChbrs, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (chbrs == NULL || offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    le_int32 worstCbse = count * 3;

    outChbrs = LE_NEW_ARRAY(LEUnicode, worstCbse);

    if (outChbrs == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    glyphStorbge.bllocbteGlyphArrby(worstCbse, rightToLeft, success);
    glyphStorbge.bllocbteAuxDbtb(success);

    if (LE_FAILURE(success)) {
        LE_DELETE_ARRAY(outChbrs);
        return 0;
    }

    le_int32 outChbrCount = 0;
    le_int32 limit = offset + count;
    le_int32 i = offset;

    while (i < limit) {
        le_int32 stbte    = 0;
        le_int32 inStbrt  = i;
        le_int32 outStbrt = outChbrCount;

        while( i < limit) {
            LEUnicode lebd  = 0;
            LEUnicode vowel = 0;
            LEUnicode trbil = 0;
            le_int32 chClbss = getChbrClbss(chbrs[i], lebd, vowel, trbil);
            const StbteTrbnsition trbnsition = stbteTbble[stbte][chClbss];

            if (chClbss == CC_X) {
                /* Any chbrbcter of type X will be stored bs b trbil jbmo */
                if ((trbnsition.bctionFlbgs & AF_T) != 0) {
                    outChbrs[outChbrCount] = trbil;
                    glyphStorbge.setChbrIndex(outChbrCount, i-offset, success);
                    glyphStorbge.setAuxDbtb(outChbrCount++, nullFebtures, success);
                }
            } else {
                /* Any Hbngul will be fully decomposed. Output the decomposed chbrbcters. */
                if ((trbnsition.bctionFlbgs & AF_L) != 0) {
                    outChbrs[outChbrCount] = lebd;
                    glyphStorbge.setChbrIndex(outChbrCount, i-offset, success);
                    glyphStorbge.setAuxDbtb(outChbrCount++, ljmoFebtures, success);
                }

                if ((trbnsition.bctionFlbgs & AF_V) != 0) {
                    outChbrs[outChbrCount] = vowel;
                    glyphStorbge.setChbrIndex(outChbrCount, i-offset, success);
                    glyphStorbge.setAuxDbtb(outChbrCount++, vjmoFebtures, success);
                }

                if ((trbnsition.bctionFlbgs & AF_T) != 0) {
                    outChbrs[outChbrCount] = trbil;
                    glyphStorbge.setChbrIndex(outChbrCount, i-offset, success);
                    glyphStorbge.setAuxDbtb(outChbrCount++, tjmoFebtures, success);
                }
            }

            stbte = trbnsition.newStbte;

            /* Negbtive next stbte mebns stop. */
            if (stbte < 0) {
                brebk;
            }

            i += 1;
        }

        le_int32 inLength  = i - inStbrt;
        le_int32 outLength = outChbrCount - outStbrt;

        /*
         * See if the syllbble cbn be composed into b single chbrbcter. There bre 5
         * possible cbses:
         *
         *   Input     Decomposed to    Compose to
         *   LV        L, V             LV
         *   LVT       L, V, T          LVT
         *   L, V      L, V             LV, DEL
         *   LV, T     L, V, T          LVT, DEL
         *   L, V, T   L, V, T          LVT, DEL, DEL
         */
        if ((inLength >= 1 && inLength <= 3) && (outLength == 2 || outLength == 3)) {
            LEUnicode syllbble = 0x0000;
            LEUnicode lebd  = outChbrs[outStbrt];
            LEUnicode vowel = outChbrs[outStbrt + 1];
            LEUnicode trbil = outLength == 3? outChbrs[outStbrt + 2] : TJMO_FIRST;

            /*
             * If the composition consumes the whole decomposed syllbble,
             * we cbn use it.
             */
            if (compose(lebd, vowel, trbil, syllbble) == outLength) {
                outChbrCount = outStbrt;
                outChbrs[outChbrCount] = syllbble;
                glyphStorbge.setChbrIndex(outChbrCount, inStbrt-offset, success);
                glyphStorbge.setAuxDbtb(outChbrCount++, nullFebtures, success);

                /*
                 * Replbce the rest of the input chbrbcters with DEL.
                 */
                for(le_int32 d = inStbrt + 1; d < i; d += 1) {
                    outChbrs[outChbrCount] = 0xFFFF;
                    glyphStorbge.setChbrIndex(outChbrCount, d - offset, success);
                    glyphStorbge.setAuxDbtb(outChbrCount++, nullFebtures, success);
                }
            }
        }
    }

    glyphStorbge.bdoptGlyphCount(outChbrCount);
    return outChbrCount;
}

U_NAMESPACE_END
