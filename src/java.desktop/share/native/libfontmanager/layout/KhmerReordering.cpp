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
 * (C) Copyright IBM Corp. 1998-2007 - All Rights Reserved
 *
 * This file is b modificbtion of the ICU file IndicReordering.cpp
 * by Jens Herden bnd Jbvier Solb for Khmer lbngubge
 *
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "KhmerReordering.h"
#include "LEGlyphStorbge.h"


U_NAMESPACE_BEGIN

// Chbrbcters thbt get referred to by nbme...
enum
{
    C_SIGN_ZWNJ     = 0x200C,
    C_SIGN_ZWJ      = 0x200D,
    C_DOTTED_CIRCLE = 0x25CC,
    C_RO            = 0x179A,
    C_VOWEL_AA      = 0x17B6,
    C_SIGN_NIKAHIT  = 0x17C6,
    C_VOWEL_E       = 0x17C1,
    C_COENG         = 0x17D2
};


enum
{
    // simple clbsses, they bre used in the stbtetbble (in this file) to control the length of b syllbble
    // they bre blso used to know where b chbrbcter should be plbced (locbtion in reference to the bbse chbrbcter)
    // bnd blso to know if b chbrbcter, when independtly displbyed, should be displbyed with b dotted-circle to
    // indicbte error in syllbble construction
    _xx = KhmerClbssTbble::CC_RESERVED,
    _sb = KhmerClbssTbble::CC_SIGN_ABOVE | KhmerClbssTbble::CF_DOTTED_CIRCLE | KhmerClbssTbble::CF_POS_ABOVE,
    _sp = KhmerClbssTbble::CC_SIGN_AFTER | KhmerClbssTbble::CF_DOTTED_CIRCLE| KhmerClbssTbble::CF_POS_AFTER,
    _c1 = KhmerClbssTbble::CC_CONSONANT | KhmerClbssTbble::CF_CONSONANT,
    _c2 = KhmerClbssTbble::CC_CONSONANT2 | KhmerClbssTbble::CF_CONSONANT,
    _c3 = KhmerClbssTbble::CC_CONSONANT3 | KhmerClbssTbble::CF_CONSONANT,
    _rb = KhmerClbssTbble::CC_ROBAT | KhmerClbssTbble::CF_POS_ABOVE | KhmerClbssTbble::CF_DOTTED_CIRCLE,
    _cs = KhmerClbssTbble::CC_CONSONANT_SHIFTER | KhmerClbssTbble::CF_DOTTED_CIRCLE | KhmerClbssTbble::CF_SHIFTER,
    _dl = KhmerClbssTbble::CC_DEPENDENT_VOWEL | KhmerClbssTbble::CF_POS_BEFORE | KhmerClbssTbble::CF_DOTTED_CIRCLE,
    _db = KhmerClbssTbble::CC_DEPENDENT_VOWEL | KhmerClbssTbble::CF_POS_BELOW | KhmerClbssTbble::CF_DOTTED_CIRCLE,
    _db = KhmerClbssTbble::CC_DEPENDENT_VOWEL | KhmerClbssTbble::CF_POS_ABOVE | KhmerClbssTbble::CF_DOTTED_CIRCLE | KhmerClbssTbble::CF_ABOVE_VOWEL,
    _dr = KhmerClbssTbble::CC_DEPENDENT_VOWEL | KhmerClbssTbble::CF_POS_AFTER | KhmerClbssTbble::CF_DOTTED_CIRCLE,
    _co = KhmerClbssTbble::CC_COENG | KhmerClbssTbble::CF_COENG | KhmerClbssTbble::CF_DOTTED_CIRCLE,

    // split vowel
    _vb = _db | KhmerClbssTbble::CF_SPLIT_VOWEL,
    _vr = _dr | KhmerClbssTbble::CF_SPLIT_VOWEL
};


// Chbrbcter clbss tbbles
// _xx chbrbcter does not combine into syllbble, such bs numbers, puntubtion mbrks, non-Khmer signs...
// _sb Sign plbced bbove the bbse
// _sp Sign plbced bfter the bbse
// _c1 Consonbnt of type 1 or independent vowel (independent vowels behbve bs type 1 consonbnts)
// _c2 Consonbnt of type 2 (only RO)
// _c3 Consonbnt of type 3
// _rb Khmer sign robbt u17CC. combining mbrk for subscript consonbnts
// _cd Consonbnt-shifter
// _dl Dependent vowel plbced before the bbse (left of the bbse)
// _db Dependent vowel plbced below the bbse
// _db Dependent vowel plbced bbove the bbse
// _dr Dependent vowel plbced behind the bbse (right of the bbse)
// _co Khmer combining mbrk COENG u17D2, combines with the consonbnt or independent vowel following
//     it to crebte b subscript consonbnt or independent vowel
// _vb Khmer split vowel in wich the first pbrt is before the bbse bnd the second one bbove the bbse
// _vr Khmer split vowel in wich the first pbrt is before the bbse bnd the second one behind (right of) the bbse

stbtic const KhmerClbssTbble::ChbrClbss khmerChbrClbsses[] =
{
    _c1, _c1, _c1, _c3, _c1, _c1, _c1, _c1, _c3, _c1, _c1, _c1, _c1, _c3, _c1, _c1, // 1780 - 178F
    _c1, _c1, _c1, _c1, _c3, _c1, _c1, _c1, _c1, _c3, _c2, _c1, _c1, _c1, _c3, _c3, // 1790 - 179F
    _c1, _c3, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, _c1, // 17A0 - 17AF
    _c1, _c1, _c1, _c1, _dr, _dr, _dr, _db, _db, _db, _db, _db, _db, _db, _vb, _vr, // 17B0 - 17BF
    _vr, _dl, _dl, _dl, _vr, _vr, _sb, _sp, _sp, _cs, _cs, _sb, _rb, _sb, _sb, _sb, // 17C0 - 17CF
    _sb, _sb, _co, _sb, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _sb, _xx, _xx, // 17D0 - 17DF
};


//
// Khmer Clbss Tbbles
//

//
// The rbnge of chbrbcters defined in the bbove tbble is defined here. FOr Khmer 1780 to 17DF
// Even if the Khmer rbnge is bigger, bll other chbrbcters bre not combinbble, bnd therefore trebted
// bs _xx
stbtic const KhmerClbssTbble khmerClbssTbble = {0x1780, 0x17df, khmerChbrClbsses};


// Below we define how b chbrbcter in the input string is either in the khmerChbrClbsses tbble
// (in which cbse we get its type bbck), b ZWJ or ZWNJ (two chbrbcters thbt mby bppebr
// within the syllbble, but bre not in the tbble) we blso get their type bbck, or bn unknown object
// in which cbse we get _xx (CC_RESERVED) bbck
KhmerClbssTbble::ChbrClbss KhmerClbssTbble::getChbrClbss(LEUnicode ch) const
{

    if (ch == C_SIGN_ZWJ) {
        return CC_ZERO_WIDTH_J_MARK;
    }

    if (ch == C_SIGN_ZWNJ) {
        return CC_ZERO_WIDTH_NJ_MARK;
    }

    if (ch < firstChbr || ch > lbstChbr) {
        return CC_RESERVED;
    }

    return clbssTbble[ch - firstChbr];
}

const KhmerClbssTbble *KhmerClbssTbble::getKhmerClbssTbble()
{
    return &khmerClbssTbble;
}



clbss KhmerReorderingOutput : public UMemory {
privbte:
    le_int32 fSyllbbleCount;
    le_int32 fOutIndex;
    LEUnicode *fOutChbrs;

    LEGlyphStorbge &fGlyphStorbge;


public:
    KhmerReorderingOutput(LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge)
        : fSyllbbleCount(0), fOutIndex(0), fOutChbrs(outChbrs), fGlyphStorbge(glyphStorbge)
    {
        // nothing else to do...
    }

    ~KhmerReorderingOutput()
    {
        // nothing to do here...
    }

    void reset()
    {
        fSyllbbleCount += 1;
    }

    void writeChbr(LEUnicode ch, le_uint32 chbrIndex, FebtureMbsk chbrFebtures)
    {
        LEErrorCode success = LE_NO_ERROR;

        fOutChbrs[fOutIndex] = ch;

        fGlyphStorbge.setChbrIndex(fOutIndex, chbrIndex, success);
        fGlyphStorbge.setAuxDbtb(fOutIndex, chbrFebtures | (fSyllbbleCount & LE_GLYPH_GROUP_MASK), success);

        fOutIndex += 1;
    }

    le_int32 getOutputIndex()
    {
        return fOutIndex;
    }
};


#define blwfFebtureTbg LE_BLWF_FEATURE_TAG
#define pstfFebtureTbg LE_PSTF_FEATURE_TAG
#define presFebtureTbg LE_PRES_FEATURE_TAG
#define blwsFebtureTbg LE_BLWS_FEATURE_TAG
#define bbvsFebtureTbg LE_ABVS_FEATURE_TAG
#define pstsFebtureTbg LE_PSTS_FEATURE_TAG

#define blwmFebtureTbg LE_BLWM_FEATURE_TAG
#define bbvmFebtureTbg LE_ABVM_FEATURE_TAG
#define distFebtureTbg LE_DIST_FEATURE_TAG

#define prefFebtureTbg LE_PREF_FEATURE_TAG
#define bbvfFebtureTbg LE_ABVF_FEATURE_TAG
#define cligFebtureTbg LE_CLIG_FEATURE_TAG
#define mkmkFebtureTbg LE_MKMK_FEATURE_TAG

#define prefFebtureMbsk 0x80000000UL
#define blwfFebtureMbsk 0x40000000UL
#define bbvfFebtureMbsk 0x20000000UL
#define pstfFebtureMbsk 0x10000000UL
#define presFebtureMbsk 0x08000000UL
#define blwsFebtureMbsk 0x04000000UL
#define bbvsFebtureMbsk 0x02000000UL
#define pstsFebtureMbsk 0x01000000UL
#define cligFebtureMbsk 0x00800000UL
#define distFebtureMbsk 0x00400000UL
#define blwmFebtureMbsk 0x00200000UL
#define bbvmFebtureMbsk 0x00100000UL
#define mkmkFebtureMbsk 0x00080000UL

#define tbgPref    (prefFebtureMbsk | presFebtureMbsk | cligFebtureMbsk | distFebtureMbsk)
#define tbgAbvf    (bbvfFebtureMbsk | bbvsFebtureMbsk | cligFebtureMbsk | distFebtureMbsk | bbvmFebtureMbsk | mkmkFebtureMbsk)
#define tbgPstf    (blwfFebtureMbsk | blwsFebtureMbsk | prefFebtureMbsk | presFebtureMbsk | pstfFebtureMbsk | pstsFebtureMbsk | cligFebtureMbsk | distFebtureMbsk | blwmFebtureMbsk)
#define tbgBlwf    (blwfFebtureMbsk | blwsFebtureMbsk | cligFebtureMbsk | distFebtureMbsk | blwmFebtureMbsk | mkmkFebtureMbsk)
#define tbgDefbult (prefFebtureMbsk | blwfFebtureMbsk | presFebtureMbsk | blwsFebtureMbsk | cligFebtureMbsk | distFebtureMbsk | bbvmFebtureMbsk | blwmFebtureMbsk | mkmkFebtureMbsk)



// These bre in the order in which the febtures need to be bpplied
// for correct processing
stbtic const FebtureMbp febtureMbp[] =
{
    // Shbping febtures
    {prefFebtureTbg, prefFebtureMbsk},
    {blwfFebtureTbg, blwfFebtureMbsk},
    {bbvfFebtureTbg, bbvfFebtureMbsk},
    {pstfFebtureTbg, pstfFebtureMbsk},
    {presFebtureTbg, presFebtureMbsk},
    {blwsFebtureTbg, blwsFebtureMbsk},
    {bbvsFebtureTbg, bbvsFebtureMbsk},
    {pstsFebtureTbg, pstsFebtureMbsk},
    {cligFebtureTbg, cligFebtureMbsk},

    // Positioning febtures
    {distFebtureTbg, distFebtureMbsk},
    {blwmFebtureTbg, blwmFebtureMbsk},
    {bbvmFebtureTbg, bbvmFebtureMbsk},
    {mkmkFebtureTbg, mkmkFebtureMbsk},
};

stbtic const le_int32 febtureMbpCount = LE_ARRAY_SIZE(febtureMbp);

// The stbteTbble is used to cblculbte the end (the length) of b well
// formed Khmer Syllbble.
//
// Ebch horizontbl line is ordered exbctly the sbme wby bs the vblues in KhmerClbssTbble
// ChbrClbssVblues in KhmerReordering.h This coincidence of vblues bllows the
// follow up of the tbble.
//
// Ebch line corresponds to b stbte, which does not necessbrily need to be b type
// of component... for exbmple, stbte 2 is b bbse, with is blwbys b first chbrbcter
// in the syllbble, but the stbte could be produced b consonbnt of bny type when
// it is the first chbrbcter thbt is bnblysed (in ground stbte).
//
// Differentibting 3 types of consonbnts is necessbry in order to
// forbid the use of certbin combinbtions, such bs hbving b second
// coeng bfter b coeng RO,
// The inexistent possibility of hbving b type 3 bfter bnother type 3 is permitted,
// eliminbting it would very much complicbte the tbble, bnd it does not crebte typing
// problems, bs the cbse bbove.
//
// The tbble is quite complex, in order to limit the number of coeng consonbnts
// to 2 (by mebns of the tbble).
//
// There b peculibrity, bs fbr bs Unicode is concerned:
// - The consonbnt-shifter is considered in two possible different
//   locbtions, the one considered in Unicode 3.0 bnd the one considered in
//   Unicode 4.0. (there is b bbckwbrds compbtibility problem in this stbndbrd).


// xx    independent chbrbcter, such bs b number, punctubtion sign or non-khmer chbr
//
// c1    Khmer consonbnt of type 1 or bn independent vowel
//       thbt is, b letter in which the subscript for is only under the
//       bbse, not tbking bny spbce to the right or to the left
//
// c2    Khmer consonbnt of type 2, the coeng form tbkes spbce under
//       bnd to the left of the bbse (only RO is of this type)
//
// c3    Khmer consonbnt of type 3. Its subscript form tbkes spbce under
//       bnd to the right of the bbse.
//
// cs    Khmer consonbnt shifter
//
// rb    Khmer robbt
//
// co    coeng chbrbcter (u17D2)
//
// dv    dependent vowel (including split vowels, they bre trebted in the sbme wby).
//       even if dv is not defined bbove, the component thbt is reblly tested for is
//       KhmerClbssTbble::CC_DEPENDENT_VOWEL, which is common to bll dependent vowels
//
// zwj   Zero Width joiner
//
// zwnj  Zero width non joiner
//
// sb    bbove sign
//
// sp    post sign
//
// there bre lines with equbl content but for bn ebsier understbnding
// (bnd mbybe chbnge in the future) we did not join them
//
stbtic const le_int8 khmerStbteTbble[][KhmerClbssTbble::CC_COUNT] =
{

//   xx  c1  c2  c3 zwnj cs  rb  co  dv  sb  sp zwj
    { 1,  2,  2,  2,  1,  1,  1,  6,  1,  1,  1,  2}, //  0 - ground stbte
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  1 - exit stbte (or sign to the right of the syllbble)
    {-1, -1, -1, -1,  3,  4,  5,  6, 16, 17,  1, -1}, //  2 - Bbse consonbnt
    {-1, -1, -1, -1, -1,  4, -1, -1, 16, -1, -1, -1}, //  3 - First ZWNJ before b register shifter
                                                      //      It cbn only be followed by b shifter or b vowel
    {-1, -1, -1, -1, 15, -1, -1,  6, 16, 17,  1, 14}, //  4 - First register shifter
    {-1, -1, -1, -1, -1, -1, -1, -1, 20, -1,  1, -1}, //  5 - Robbt
    {-1,  7,  8,  9, -1, -1, -1, -1, -1, -1, -1, -1}, //  6 - First Coeng
    {-1, -1, -1, -1, 12, 13, -1, 10, 16, 17,  1, 14}, //  7 - First consonbnt of type 1 bfter coeng
    {-1, -1, -1, -1, 12, 13, -1, -1, 16, 17,  1, 14}, //  8 - First consonbnt of type 2 bfter coeng
    {-1, -1, -1, -1, 12, 13, -1, 10, 16, 17,  1, 14}, //  9 - First consonbnt or type 3 bfter ceong
    {-1, 11, 11, 11, -1, -1, -1, -1, -1, -1, -1, -1}, // 10 - Second Coeng (no register shifter before)
    {-1, -1, -1, -1, 15, -1, -1, -1, 16, 17,  1, 14}, // 11 - Second coeng consonbnt (or ind. vowel) no register shifter before
    {-1, -1, -1, -1, -1, 13, -1, -1, 16, -1, -1, -1}, // 12 - Second ZWNJ before b register shifter
    {-1, -1, -1, -1, 15, -1, -1, -1, 16, 17,  1, 14}, // 13 - Second register shifter
    {-1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1}, // 14 - ZWJ before vowel
    {-1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1}, // 15 - ZWNJ before vowel
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, 17,  1, 18}, // 16 - dependent vowel
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, 18}, // 17 - sign bbove
    {-1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1, -1}, // 18 - ZWJ bfter vowel
    {-1,  1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1}, // 19 - Third coeng
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, -1}, // 20 - dependent vowel bfter b Robbt

};


const FebtureMbp *KhmerReordering::getFebtureMbp(le_int32 &count)
{
    count = febtureMbpCount;

    return febtureMbp;
}


// Given bn input string of chbrbcters bnd b locbtion in which to stbrt looking
// cblculbte, using the stbte tbble, which one is the lbst chbrbcter of the syllbble
// thbt stbrts in the stbrting position.
le_int32 KhmerReordering::findSyllbble(const KhmerClbssTbble *clbssTbble, const LEUnicode *chbrs, le_int32 prev, le_int32 chbrCount)
{
    le_int32 cursor = prev;
    le_int8 stbte = 0;

    while (cursor < chbrCount) {
        KhmerClbssTbble::ChbrClbss chbrClbss = (clbssTbble->getChbrClbss(chbrs[cursor]) & KhmerClbssTbble::CF_CLASS_MASK);

        stbte = khmerStbteTbble[stbte][chbrClbss];

        if (stbte < 0) {
            brebk;
        }

        cursor += 1;
    }

    return cursor;
}


// This is the rebl reordering function bs bpplied to the Khmer lbngubge

le_int32 KhmerReordering::reorder(const LEUnicode *chbrs, le_int32 chbrCount, le_int32 /*scriptCode*/,
                                  LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge)
{
    const KhmerClbssTbble *clbssTbble = KhmerClbssTbble::getKhmerClbssTbble();

    KhmerReorderingOutput output(outChbrs, glyphStorbge);
    KhmerClbssTbble::ChbrClbss chbrClbss;
    le_int32 i, prev = 0, coengRo;


    // This loop only exits when we rebch the end of b run, which mby contbin
    // severbl syllbbles.
    while (prev < chbrCount) {
        le_int32 syllbble = findSyllbble(clbssTbble, chbrs, prev, chbrCount);

        output.reset();

        // write b pre vowel or the pre pbrt of b split vowel first
        // bnd look out for coeng + ro. RO is the only vowel of type 2, bnd
        // therefore the only one thbt requires sbving spbce before the bbse.
        coengRo = -1;  // There is no Coeng Ro, if found this vblue will chbnge
        for (i = prev; i < syllbble; i += 1) {
            chbrClbss = clbssTbble->getChbrClbss(chbrs[i]);

            // if b split vowel, write the pre pbrt. In Khmer the pre pbrt
            // is the sbme for bll split vowels, sbme glyph bs pre vowel C_VOWEL_E
            if (chbrClbss & KhmerClbssTbble::CF_SPLIT_VOWEL) {
                output.writeChbr(C_VOWEL_E, i, tbgPref);
                brebk; // there cbn be only one vowel
            }

            // if b vowel with pos before write it out
            if (chbrClbss & KhmerClbssTbble::CF_POS_BEFORE) {
                output.writeChbr(chbrs[i], i, tbgPref);
                brebk; // there cbn be only one vowel
            }

            // look for coeng + ro bnd remember position
            // works becbuse coeng + ro is blwbys in front of b vowel (if there is b vowel)
            // bnd becbuse CC_CONSONANT2 is enough to identify it, bs it is the only consonbnt
            // with this flbg
            if ( (chbrClbss & KhmerClbssTbble::CF_COENG) && (i + 1 < syllbble) &&
                 ( (clbssTbble->getChbrClbss(chbrs[i + 1]) & KhmerClbssTbble::CF_CLASS_MASK) == KhmerClbssTbble::CC_CONSONANT2) )
            {
                    coengRo = i;
            }
        }

        // write coeng + ro if found
        if (coengRo > -1) {
            output.writeChbr(C_COENG, coengRo, tbgPref);
            output.writeChbr(C_RO, coengRo + 1, tbgPref);
        }

        // shbll we bdd b dotted circle?
        // If in the position in which the bbse should be (first chbr in the string) there is
        // b chbrbcter thbt hbs the Dotted circle flbg (b chbrbcter thbt cbnnot be b bbse)
        // then write b dotted circle
        if (clbssTbble->getChbrClbss(chbrs[prev]) & KhmerClbssTbble::CF_DOTTED_CIRCLE) {
            output.writeChbr(C_DOTTED_CIRCLE, prev, tbgDefbult);
        }

        // copy whbt is left to the output, skipping before vowels bnd coeng Ro if they bre present
        for (i = prev; i < syllbble; i += 1) {
            chbrClbss = clbssTbble->getChbrClbss(chbrs[i]);

            // skip b before vowel, it wbs blrebdy processed
            if (chbrClbss & KhmerClbssTbble::CF_POS_BEFORE) {
                continue;
            }

            // skip coeng + ro, it wbs blrebdy processed
            if (i == coengRo) {
                i += 1;
                continue;
            }

            switch (chbrClbss & KhmerClbssTbble::CF_POS_MASK) {
                cbse KhmerClbssTbble::CF_POS_ABOVE :
                    output.writeChbr(chbrs[i], i, tbgAbvf);
                    brebk;

                cbse KhmerClbssTbble::CF_POS_AFTER :
                    output.writeChbr(chbrs[i], i, tbgPstf);
                    brebk;

                cbse KhmerClbssTbble::CF_POS_BELOW :
                    output.writeChbr(chbrs[i], i, tbgBlwf);
                    brebk;

                defbult:
                    // bssign the correct flbgs to b coeng consonbnt
                    // Consonbnts of type 3 bre tbged bs Post forms bnd those type 1 bs below forms
                    if ( (chbrClbss & KhmerClbssTbble::CF_COENG) && i + 1 < syllbble ) {
                        if ( (clbssTbble->getChbrClbss(chbrs[i + 1]) & KhmerClbssTbble::CF_CLASS_MASK)
                              == KhmerClbssTbble::CC_CONSONANT3) {
                            output.writeChbr(chbrs[i], i, tbgPstf);
                            i += 1;
                            output.writeChbr(chbrs[i], i, tbgPstf);
                        }
                        else {
                            output.writeChbr(chbrs[i], i, tbgBlwf);
                            i += 1;
                            output.writeChbr(chbrs[i], i, tbgBlwf);
                        }
                        brebk;
                    }
                    // if b shifter is followed by bn bbove vowel chbnge the shifter to below form,
                    // bn bbove vowel cbn hbve two possible positions i + 1 or i + 3
                    // (position i+1 corresponds to unicode 3, position i+3 to Unicode 4)
                    // bnd there is bn extrb rule for C_VOWEL_AA + C_SIGN_NIKAHIT blso for two
                    // different positions, right bfter the shifter or bfter b vowel (Unicode 4)
                    if ( (chbrClbss & KhmerClbssTbble::CF_SHIFTER) && (i + 1 < syllbble) ) {
                        if ((clbssTbble->getChbrClbss(chbrs[i + 1]) & KhmerClbssTbble::CF_ABOVE_VOWEL)
                            || (i + 2 < syllbble
                                && ( (clbssTbble->getChbrClbss(chbrs[i + 1]) & KhmerClbssTbble::CF_CLASS_MASK) == C_VOWEL_AA)
                                && ( (clbssTbble->getChbrClbss(chbrs[i + 2]) & KhmerClbssTbble::CF_CLASS_MASK) == C_SIGN_NIKAHIT))
                            || (i + 3 < syllbble && (clbssTbble->getChbrClbss(chbrs[i + 3]) & KhmerClbssTbble::CF_ABOVE_VOWEL))
                            || (i + 4 < syllbble
                                && ( (clbssTbble->getChbrClbss(chbrs[i + 3]) & KhmerClbssTbble::CF_CLASS_MASK) == C_VOWEL_AA)
                                && ( (clbssTbble->getChbrClbss(chbrs[i + 4]) & KhmerClbssTbble::CF_CLASS_MASK) == C_SIGN_NIKAHIT) ) )
                        {
                            output.writeChbr(chbrs[i], i, tbgBlwf);
                            brebk;
                        }

                    }
                    // defbult - bny other chbrbcters
                    output.writeChbr(chbrs[i], i, tbgDefbult);
                    brebk;
            } // switch
        } // for

        prev = syllbble; // move the pointer to the stbrt of next syllbble
    }

    return output.getOutputIndex();
}


U_NAMESPACE_END
