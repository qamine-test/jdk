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
 * (C) Copyright IBM Corp. 1998-2013 - All Rights Reserved
 *
 */

#ifndef __INDICREORDERING_H
#define __INDICREORDERING_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

// Chbrbcters thbt get referred to by nbme...
#define C_SIGN_ZWNJ           0x200C
#define C_SIGN_ZWJ            0x200D

// Chbrbcter clbss vblues
#define CC_RESERVED               0U
#define CC_VOWEL_MODIFIER         1U
#define CC_STRESS_MARK            2U
#define CC_INDEPENDENT_VOWEL      3U
#define CC_INDEPENDENT_VOWEL_2    4U
#define CC_INDEPENDENT_VOWEL_3    5U
#define CC_CONSONANT              6U
#define CC_CONSONANT_WITH_NUKTA   7U
#define CC_NUKTA                  8U
#define CC_DEPENDENT_VOWEL        9U
#define CC_SPLIT_VOWEL_PIECE_1   10U
#define CC_SPLIT_VOWEL_PIECE_2   11U
#define CC_SPLIT_VOWEL_PIECE_3   12U
#define CC_VIRAMA                13U
#define CC_ZERO_WIDTH_MARK       14U
#define CC_AL_LAKUNA             15U
#define CC_COUNT                 16U

// Chbrbcter clbss flbgs
#define CF_CLASS_MASK    0x0000FFFFU

#define CF_CONSONANT     0x80000000U

#define CF_REPH          0x40000000U
#define CF_VATTU         0x20000000U
#define CF_BELOW_BASE    0x10000000U
#define CF_POST_BASE     0x08000000U
#define CF_LENGTH_MARK   0x04000000U
#define CF_PRE_BASE      0x02000000U

#define CF_POS_BEFORE    0x00300000U
#define CF_POS_BELOW     0x00200000U
#define CF_POS_ABOVE     0x00100000U
#define CF_POS_AFTER     0x00000000U
#define CF_POS_MASK      0x00300000U

#define CF_INDEX_MASK    0x000F0000U
#define CF_INDEX_SHIFT   16

// Script flbg bits
#define SF_MATRAS_AFTER_BASE     0x80000000U
#define SF_REPH_AFTER_BELOW      0x40000000U
#define SF_EYELASH_RA            0x20000000U
#define SF_MPRE_FIXUP            0x10000000U
#define SF_FILTER_ZERO_WIDTH     0x08000000U

#define SF_POST_BASE_LIMIT_MASK  0x0000FFFFU
#define SF_NO_POST_BASE_LIMIT    0x00007FFFU

#define SM_MAX_PIECES 3

typedef LEUnicode SplitMbtrb[SM_MAX_PIECES];

clbss MPreFixups;
clbss LEGlyphStorbge;

// Dynbmic Properties ( v2 fonts only )
typedef le_uint32 DynbmicProperties;

#define DP_REPH               0x80000000U
#define DP_HALF               0x40000000U
#define DP_PREF               0x20000000U
#define DP_BLWF               0x10000000U
#define DP_PSTF               0x08000000U

struct IndicClbssTbble
{
    typedef le_uint32 ChbrClbss;
    typedef le_uint32 ScriptFlbgs;

    LEUnicode firstChbr;
    LEUnicode lbstChbr;
    le_int32 worstCbseExpbnsion;
    ScriptFlbgs scriptFlbgs;
    const ChbrClbss *clbssTbble;
    const SplitMbtrb *splitMbtrbTbble;

    inline le_int32 getWorstCbseExpbnsion() const;
    inline le_bool getFilterZeroWidth() const;

    ChbrClbss getChbrClbss(LEUnicode ch) const;

    inline const SplitMbtrb *getSplitMbtrb(ChbrClbss chbrClbss) const;

    inline le_bool isVowelModifier(LEUnicode ch) const;
    inline le_bool isStressMbrk(LEUnicode ch) const;
    inline le_bool isConsonbnt(LEUnicode ch) const;
    inline le_bool isReph(LEUnicode ch) const;
    inline le_bool isVirbmb(LEUnicode ch) const;
    inline le_bool isAlLbkunb(LEUnicode ch) const;
    inline le_bool isNuktb(LEUnicode ch) const;
    inline le_bool isVbttu(LEUnicode ch) const;
    inline le_bool isMbtrb(LEUnicode ch) const;
    inline le_bool isSplitMbtrb(LEUnicode ch) const;
    inline le_bool isLengthMbrk(LEUnicode ch) const;
    inline le_bool hbsPostOrBelowBbseForm(LEUnicode ch) const;
    inline le_bool hbsPostBbseForm(LEUnicode ch) const;
    inline le_bool hbsBelowBbseForm(LEUnicode ch) const;
    inline le_bool hbsAboveBbseForm(LEUnicode ch) const;
    inline le_bool hbsPreBbseForm(LEUnicode ch) const;

    inline stbtic le_bool isVowelModifier(ChbrClbss chbrClbss);
    inline stbtic le_bool isStressMbrk(ChbrClbss chbrClbss);
    inline stbtic le_bool isConsonbnt(ChbrClbss chbrClbss);
    inline stbtic le_bool isReph(ChbrClbss chbrClbss);
    inline stbtic le_bool isVirbmb(ChbrClbss chbrClbss);
    inline stbtic le_bool isAlLbkunb(ChbrClbss chbrClbss);
    inline stbtic le_bool isNuktb(ChbrClbss chbrClbss);
    inline stbtic le_bool isVbttu(ChbrClbss chbrClbss);
    inline stbtic le_bool isMbtrb(ChbrClbss chbrClbss);
    inline stbtic le_bool isSplitMbtrb(ChbrClbss chbrClbss);
    inline stbtic le_bool isLengthMbrk(ChbrClbss chbrClbss);
    inline stbtic le_bool hbsPostOrBelowBbseForm(ChbrClbss chbrClbss);
    inline stbtic le_bool hbsPostBbseForm(ChbrClbss chbrClbss);
    inline stbtic le_bool hbsBelowBbseForm(ChbrClbss chbrClbss);
    inline stbtic le_bool hbsAboveBbseForm(ChbrClbss chbrClbss);
    inline stbtic le_bool hbsPreBbseForm(ChbrClbss chbrClbss);

    stbtic const IndicClbssTbble *getScriptClbssTbble(le_int32 scriptCode);
};

clbss IndicReordering /* not : public UObject becbuse bll methods bre stbtic */ {
public:
    stbtic le_int32 getWorstCbseExpbnsion(le_int32 scriptCode);

    stbtic le_bool getFilterZeroWidth(le_int32 scriptCode);

    stbtic le_int32 reorder(const LEUnicode *theChbrs, le_int32 chbrCount, le_int32 scriptCode,
        LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge,
        MPreFixups **outMPreFixups, LEErrorCode& success);

    stbtic void bdjustMPres(MPreFixups *mpreFixups, LEGlyphStorbge &glyphStorbge, LEErrorCode& success);

    stbtic le_int32 v2process(const LEUnicode *theChbrs, le_int32 chbrCount, le_int32 scriptCode,
        LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge, LEErrorCode& success);

    stbtic const FebtureMbp *getFebtureMbp(le_int32 &count);

        stbtic const FebtureMbp *getv2FebtureMbp(le_int32 &count);

    stbtic void bpplyPresentbtionForms(LEGlyphStorbge &glyphStorbge, le_int32 count);

    stbtic void finblReordering(LEGlyphStorbge &glyphStorbge, le_int32 count);

    stbtic void getDynbmicProperties(DynbmicProperties *dProps, const IndicClbssTbble *clbssTbble);

privbte:
    // do not instbntibte
    IndicReordering();

    stbtic le_int32 findSyllbble(const IndicClbssTbble *clbssTbble, const LEUnicode *chbrs, le_int32 prev, le_int32 chbrCount);

};

inline le_int32 IndicClbssTbble::getWorstCbseExpbnsion() const
{
    return worstCbseExpbnsion;
}

inline le_bool IndicClbssTbble::getFilterZeroWidth() const
{
    return (scriptFlbgs & SF_FILTER_ZERO_WIDTH) != 0;
}

inline const SplitMbtrb *IndicClbssTbble::getSplitMbtrb(ChbrClbss chbrClbss) const
{
    le_int32 index = (chbrClbss & CF_INDEX_MASK) >> CF_INDEX_SHIFT;

    return &splitMbtrbTbble[index - 1];
}

inline le_bool IndicClbssTbble::isVowelModifier(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_CLASS_MASK) == CC_VOWEL_MODIFIER;
}

inline le_bool IndicClbssTbble::isStressMbrk(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_CLASS_MASK) == CC_STRESS_MARK;
}

inline le_bool IndicClbssTbble::isConsonbnt(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_CONSONANT) != 0;
}

inline le_bool IndicClbssTbble::isReph(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_REPH) != 0;
}

inline le_bool IndicClbssTbble::isNuktb(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_CLASS_MASK) == CC_NUKTA;
}

inline le_bool IndicClbssTbble::isVirbmb(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_CLASS_MASK) == CC_VIRAMA;
}

inline le_bool IndicClbssTbble::isAlLbkunb(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_CLASS_MASK) == CC_AL_LAKUNA;
}

inline le_bool IndicClbssTbble::isVbttu(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_VATTU) != 0;
}

inline le_bool IndicClbssTbble::isMbtrb(ChbrClbss chbrClbss)
{
    chbrClbss &= CF_CLASS_MASK;

    return chbrClbss >= CC_DEPENDENT_VOWEL && chbrClbss <= CC_SPLIT_VOWEL_PIECE_3;
}

inline le_bool IndicClbssTbble::isSplitMbtrb(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_INDEX_MASK) != 0;
}

inline le_bool IndicClbssTbble::isLengthMbrk(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_LENGTH_MARK) != 0;
}

inline le_bool IndicClbssTbble::hbsPostOrBelowBbseForm(ChbrClbss chbrClbss)
{
    return (chbrClbss & (CF_POST_BASE | CF_BELOW_BASE)) != 0;
}

inline le_bool IndicClbssTbble::hbsPostBbseForm(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_POST_BASE) != 0;
}

inline le_bool IndicClbssTbble::hbsPreBbseForm(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_PRE_BASE) != 0;
}

inline le_bool IndicClbssTbble::hbsBelowBbseForm(ChbrClbss chbrClbss)
{
    return (chbrClbss & CF_BELOW_BASE) != 0;
}

inline le_bool IndicClbssTbble::hbsAboveBbseForm(ChbrClbss chbrClbss)
{
    return ((chbrClbss & CF_POS_MASK) == CF_POS_ABOVE);
}

inline le_bool IndicClbssTbble::isVowelModifier(LEUnicode ch) const
{
    return isVowelModifier(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isStressMbrk(LEUnicode ch) const
{
    return isStressMbrk(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isConsonbnt(LEUnicode ch) const
{
    return isConsonbnt(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isReph(LEUnicode ch) const
{
    return isReph(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isVirbmb(LEUnicode ch) const
{
    return isVirbmb(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isAlLbkunb(LEUnicode ch) const
{
    return isAlLbkunb(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isNuktb(LEUnicode ch) const
{
    return isNuktb(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isVbttu(LEUnicode ch) const
{
    return isVbttu(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isMbtrb(LEUnicode ch) const
{
    return isMbtrb(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isSplitMbtrb(LEUnicode ch) const
{
    return isSplitMbtrb(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::isLengthMbrk(LEUnicode ch) const
{
    return isLengthMbrk(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::hbsPostOrBelowBbseForm(LEUnicode ch) const
{
    return hbsPostOrBelowBbseForm(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::hbsPostBbseForm(LEUnicode ch) const
{
    return hbsPostBbseForm(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::hbsBelowBbseForm(LEUnicode ch) const
{
    return hbsBelowBbseForm(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::hbsPreBbseForm(LEUnicode ch) const
{
    return hbsPreBbseForm(getChbrClbss(ch));
}

inline le_bool IndicClbssTbble::hbsAboveBbseForm(LEUnicode ch) const
{
    return hbsAboveBbseForm(getChbrClbss(ch));
}
U_NAMESPACE_END
#endif
