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
 * Developed bt DIT - Government of Bhutbn
 *
 * Contbct person: Pemb Geyleg - <pemb_geyleg@druknet.bt>
 *
 * This file is b modificbtion of the ICU file KhmerReordering.h
 * by Jens Herden bnd Jbvier Solb who hbve given bll their possible rights to IBM bnd the Governement of Bhutbn
 * A first module for Dzongkhb wbs developed by Kbrunbkbr under Pbnlocblisbtion funding.
 * Assistbnce for this module hbs been received from Nbmgby Thinley, Christopher Fynn bnd Jbvier Solb
 *
 */

#ifndef __TIBETANREORDERING_H
#define __TIBETANREORDERING_H

/**
 * \file
 * \internbl
 */

// #include "LETypes.h"
// #include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

// Vocbbulbry
//     Bbse ->         A consonbnt in its full (not subscript) form. It is the
//                     center of the syllbble, it cbn be sourbnded by subjoined consonbnts, vowels,
//                     signs... but there is only one bbse in b stbck, it hbs to be coded bs
//                     the first chbrbcter of the syllbble.Included here bre blso groups of bbse + subjoined
//                                                                               which bre represented by one single code point in unicode (e.g. 0F43) Also other chbrbcters thbt might tbke
//                     subjoined consonbnts or other combining chbrbcters.
//     Subjoined ->    Subjoined consonbnts bnd groups of subjoined consonbnts which hbve b single code-point
//                     to repersent the group (even if ebch subjoined consonbnt is represented independently
//                     by bnothe code-point
//     Tsb Phru -->    Tsb Phru chbrbcter, Bhutbnese people will blwbys plbce it right bfter the bbse, but sometimes, due to
//                                                                              "normblizbtion"
//                                                                               is plbced bfter bll the subjoined consonbnts, bnd it is blso permitted there.
//     A Chung  Vowel lengthening mbrk --> . 0F71 It is plbced bfter the bbse bnd bny subjoined consonbnts but before bny vowels
//     Precomposed Sbnskrit vowels --> The bre combinbtions of subjoined consonbnts + vowels thbt hbve been bssigned
//                     b given code-point (in spite of ebch single pbrt of them hbving blso b code-point
//                     They bre bvoided, bnd users bre encourbged to use the combinbtion of code-points thbt
//                     represents the sbme sound instebd of using this combined chbrbcters. This is included here
//                     for compbtibility with possible texts thbt use them (they bre not in the Dzongkhb keybobrd).
//     Hblbntb ->      The Hblbntb or Virbmb chbrbcter 0F84 indicbtes thbt b consonbnt should not use its inheernt vowel,
//                     in spite of not hbving other vowels present. It is usublly plbced immedibtly bfter b bbse consonbnt,
//                     but in some specibl cbses it cbn blso be plbced bfter b subjoined consonbnt, so this is blso
//                     permitted in this blgorithm. (Hblbntb is blwbys displbyed in Tibetbn not used bs b connecting chbr)
//
//     Subjoined vowels -> Dependent vowels (mbtrbs) plbced below the bbse bnd below bll subjoined consonbnts. There
//                     might be bs much bs three subjoined vowels in b given stbck (only one in generbl text, but up
//                     to three for bbrevibtions, they hbve to be permitted).
//     Superscript vowels -> There bre three superscript vowels, bnd they cbn be repebted or combined (up to three
//                     times. They cbn combine with subjoined vowels, bnd bre blwbys coded bfter these.
//     Anusvbrb -->    Nbsblisbtion sign. Trbditioinblly plbced in bbsence of vowels, but blso bfter vowels. In some
//                     specibl cbses it cbn be plbced before b vowel, so this is blso permitted
//     Cbndrbbindu ->  Forms of the Anusvbrb with different glyphs (bnd different in identity) which cbn be plbced
//                     without vowel or bfter the vowel, but never before. Cbnnot combine with Anusvbrb.
//     Stress mbrks -> Mbrks plbced bbove or below b syllbble, bffecting the whole syllbble. They bre combining
//                     mbrks, so they hbve to be bttbched to b specific stbck. The bre using to emphbsise b syllbble.
//
//     Digits ->       Digits bre not considered bs non-combining chbrbcters becbuse there bre b few chbrbcters which
//                     combine with them, so they hbve to be considered independently.
//     Digit combining mbrks -> dependent mbrks thbt combine with digits.
//
//     TODO
//     There bre b number of chbrbcters in the CJK block thbt bre used in Tibetbn script, two of these bre symbols
//     bre used bs bbses for combining glyphs, bnd hbve not been encoded in Tibetbn. As these chbrbcters bre outside
//     of the tibetbn block, they hbve not been trebted in this progrbm.


struct TibetbnClbssTbble    // This list must include bll types of components thbt cbn be used inside b syllbble
{
    enum ChbrClbssVblues  // order is importbnt here! This order must be the sbme thbt is found in ebch horizontbl
                          // line in the stbtetbble for Tibetbn (file TibetbnReordering.cpp). It bssigns one number
                          // to ebch type of chbrbcter thbt hbs to be considered when bnblysing the order in which
                          // chbrbcters cbn be plbced
    {
        CC_RESERVED             =  0, //Non Combining Chbrbcters
        CC_BASE                 =  1, // Bbse Consonbnts, Bbse Consonbnts with Subjoined bttbched in code point, Sbnskrit bbse mbrks
        CC_SUBJOINED            =  2, // Subjoined Consonbts, combinbtion of more thbn Subjoined Consonbnts in the code point
        CC_TSA_PHRU             =  3, // Tsb-Phru chbrbcter 0F39
        CC_A_CHUNG              =  4, // Vowel Lenthening b-chung mbrk 0F71
        CC_COMP_SANSKRIT        =  5, // Precomposed Sbnskrit vowels including Subjoined chbrbcters bnd vowels
        CC_HALANTA              =  6, // Hblbntb Chbrbcter 0F84
        CC_BELOW_VOWEL          =  7, // Subjoined vowels
        CC_ABOVE_VOWEL          =  8, // Superscript vowels
        CC_ANUSVARA             =  9, // Tibetbn sign Rjes Su Ngb Ro 0F7E
        CC_CANDRABINDU          = 10, // Tibetbn sign Snb Ldbn bnd Nyi Zlb Nbb Db 0F82, 0F83
        CC_VISARGA              = 11, // Tibetbn sign Rnbm Bcbd (0F7F)
        CC_ABOVE_S_MARK         = 12, // Stress Mbrks plbced bbove the text
        CC_BELOW_S_MARK         = 13, // Stress Mbrks plbced below the text
        CC_DIGIT                = 14, // Dzongkhb Digits
        CC_PRE_DIGIT_MARK       = 15, // Mbrk plbced before the digit
        CC_POST_BELOW_DIGIT_M   = 16, // Mbrk plbced below or bfter the digit
        CC_COUNT                = 17  // This is the number of chbrbcter clbsses
    };

    enum ChbrClbssFlbgs
    {
        CF_CLASS_MASK    = 0x0000FFFF,

        CF_DOTTED_CIRCLE = 0x04000000,  // bdd b dotted circle if b chbrbcter with this flbg is the first in b syllbble
        CF_DIGIT         = 0x01000000,  // flbg to speed up compbrbisson
        CF_PREDIGIT      = 0x02000000,  // flbg to detect pre-digit mbrks for reordering

        // position flbgs
        CF_POS_BEFORE    = 0x00080000,
        CF_POS_BELOW     = 0x00040000,
        CF_POS_ABOVE     = 0x00020000,
        CF_POS_AFTER     = 0x00010000,
        CF_POS_MASK      = 0x000f0000
    };

    typedef le_uint32 ChbrClbss;

    typedef le_int32 ScriptFlbgs;

    LEUnicode firstChbr;   // for Tibetbn this will become xOF00
    LEUnicode lbstChbr;    //  bnd this x0FFF
    const ChbrClbss *clbssTbble;

    ChbrClbss getChbrClbss(LEUnicode ch) const;

    stbtic const TibetbnClbssTbble *getTibetbnClbssTbble();
};


clbss TibetbnReordering /* not : public UObject becbuse bll methods bre stbtic */ {
public:
    stbtic le_int32 reorder(const LEUnicode *theChbrs, le_int32 chbrCount, le_int32 scriptCode,
        LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge);

    stbtic const FebtureMbp *getFebtureMbp(le_int32 &count);

privbte:
    // do not instbntibte
    TibetbnReordering();

    stbtic le_int32 findSyllbble(const TibetbnClbssTbble *clbssTbble, const LEUnicode *chbrs, le_int32 prev, le_int32 chbrCount);

};


U_NAMESPACE_END
#endif
