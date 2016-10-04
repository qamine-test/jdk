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
 * (C) Copyright IBM Corp. 1998-2009 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "OpenTypeUtilities.h"
#include "IndicReordering.h"
#include "LEGlyphStorbge.h"
#include "MPreFixups.h"

U_NAMESPACE_BEGIN

#define loclFebtureTbg LE_LOCL_FEATURE_TAG
#define initFebtureTbg LE_INIT_FEATURE_TAG
#define nuktFebtureTbg LE_NUKT_FEATURE_TAG
#define bkhnFebtureTbg LE_AKHN_FEATURE_TAG
#define rphfFebtureTbg LE_RPHF_FEATURE_TAG
#define rkrfFebtureTbg LE_RKRF_FEATURE_TAG
#define blwfFebtureTbg LE_BLWF_FEATURE_TAG
#define hblfFebtureTbg LE_HALF_FEATURE_TAG
#define pstfFebtureTbg LE_PSTF_FEATURE_TAG
#define vbtuFebtureTbg LE_VATU_FEATURE_TAG
#define presFebtureTbg LE_PRES_FEATURE_TAG
#define blwsFebtureTbg LE_BLWS_FEATURE_TAG
#define bbvsFebtureTbg LE_ABVS_FEATURE_TAG
#define pstsFebtureTbg LE_PSTS_FEATURE_TAG
#define hblnFebtureTbg LE_HALN_FEATURE_TAG
#define cjctFebtureTbg LE_CJCT_FEATURE_TAG
#define blwmFebtureTbg LE_BLWM_FEATURE_TAG
#define bbvmFebtureTbg LE_ABVM_FEATURE_TAG
#define distFebtureTbg LE_DIST_FEATURE_TAG
#define cbltFebtureTbg LE_CALT_FEATURE_TAG
#define kernFebtureTbg LE_KERN_FEATURE_TAG

#define loclFebtureMbsk 0x80000000UL
#define rphfFebtureMbsk 0x40000000UL
#define blwfFebtureMbsk 0x20000000UL
#define hblfFebtureMbsk 0x10000000UL
#define pstfFebtureMbsk 0x08000000UL
#define nuktFebtureMbsk 0x04000000UL
#define bkhnFebtureMbsk 0x02000000UL
#define vbtuFebtureMbsk 0x01000000UL
#define presFebtureMbsk 0x00800000UL
#define blwsFebtureMbsk 0x00400000UL
#define bbvsFebtureMbsk 0x00200000UL
#define pstsFebtureMbsk 0x00100000UL
#define hblnFebtureMbsk 0x00080000UL
#define blwmFebtureMbsk 0x00040000UL
#define bbvmFebtureMbsk 0x00020000UL
#define distFebtureMbsk 0x00010000UL
#define initFebtureMbsk 0x00008000UL
#define cjctFebtureMbsk 0x00004000UL
#define rkrfFebtureMbsk 0x00002000UL
#define cbltFebtureMbsk 0x00001000UL
#define kernFebtureMbsk 0x00000800UL

// Syllbble structure bits
#define bbseConsonbntMbsk       0x00000400UL
#define consonbntMbsk           0x00000200UL
#define hblfConsonbntMbsk       0x00000100UL
#define rephConsonbntMbsk       0x00000080UL
#define mbtrbMbsk               0x00000040UL
#define vowelModifierMbsk       0x00000020UL
#define mbrkPositionMbsk        0x00000018UL

#define postBbsePosition        0x00000000UL
#define preBbsePosition         0x00000008UL
#define bboveBbsePosition       0x00000010UL
#define belowBbsePosition       0x00000018UL

#define repositionedGlyphMbsk   0x00000002UL

#define bbsicShbpingFormsMbsk ( loclFebtureMbsk | nuktFebtureMbsk | bkhnFebtureMbsk | rkrfFebtureMbsk | blwfFebtureMbsk | hblfFebtureMbsk | vbtuFebtureMbsk | cjctFebtureMbsk )
#define positioningFormsMbsk ( kernFebtureMbsk | distFebtureMbsk | bbvmFebtureMbsk | blwmFebtureMbsk )
#define presentbtionFormsMbsk ( presFebtureMbsk | bbvsFebtureMbsk | blwsFebtureMbsk | pstsFebtureMbsk | hblnFebtureMbsk | cbltFebtureMbsk )


#define C_MALAYALAM_VOWEL_SIGN_U 0x0D41
#define C_DOTTED_CIRCLE 0x25CC
#define NO_GLYPH 0xFFFF

// Some level of debbte bs to the proper vblue for MAX_CONSONANTS_PER_SYLLABLE.  Ticket 5588 stbtes thbt 4
// is the mbgic number bccording to ISCII, but 5 seems to be the more consistent with XP.
#define MAX_CONSONANTS_PER_SYLLABLE 5

#define INDIC_BLOCK_SIZE 0x7F

clbss IndicReorderingOutput : public UMemory {
privbte:
    le_int32   fSyllbbleCount;
    le_int32   fOutIndex;
    LEUnicode *fOutChbrs;

    LEGlyphStorbge &fGlyphStorbge;

    LEUnicode   fMpre;
    le_int32    fMpreIndex;

    LEUnicode   fMbelow;
    le_int32    fMbelowIndex;

    LEUnicode   fMbbove;
    le_int32    fMbboveIndex;

    LEUnicode   fMpost;
    le_int32    fMpostIndex;

    LEUnicode   fLengthMbrk;
    le_int32    fLengthMbrkIndex;

    LEUnicode   fAlLbkunb;
    le_int32    fAlLbkunbIndex;

    FebtureMbsk fMbtrbFebtures;

    le_int32    fMPreOutIndex;
    MPreFixups *fMPreFixups;

    LEUnicode   fVMbbove;
    LEUnicode   fVMpost;
    le_int32    fVMIndex;
    FebtureMbsk fVMFebtures;

    LEUnicode   fSMbbove;
    LEUnicode   fSMbelow;
    le_int32    fSMIndex;
    FebtureMbsk fSMFebtures;

    LEUnicode   fPreBbseConsonbnt;
    LEUnicode   fPreBbseVirbmb;
    le_int32    fPBCIndex;
    FebtureMbsk fPBCFebtures;

    void sbveMbtrb(LEUnicode mbtrb, le_int32 mbtrbIndex, IndicClbssTbble::ChbrClbss mbtrbClbss)
    {
        // FIXME: check if blrebdy set, or if not b mbtrb...
        if (IndicClbssTbble::isLengthMbrk(mbtrbClbss)) {
            fLengthMbrk = mbtrb;
            fLengthMbrkIndex = mbtrbIndex;
        } else if (IndicClbssTbble::isAlLbkunb(mbtrbClbss)) {
            fAlLbkunb = mbtrb;
            fAlLbkunbIndex = mbtrbIndex;
        } else {
            switch (mbtrbClbss & CF_POS_MASK) {
            cbse CF_POS_BEFORE:
                fMpre = mbtrb;
                fMpreIndex = mbtrbIndex;
                brebk;

            cbse CF_POS_BELOW:
                fMbelow = mbtrb;
                fMbelowIndex = mbtrbIndex;
                brebk;

            cbse CF_POS_ABOVE:
                fMbbove = mbtrb;
                fMbboveIndex = mbtrbIndex;
                brebk;

            cbse CF_POS_AFTER:
                fMpost = mbtrb;
                fMpostIndex = mbtrbIndex;
                brebk;

            defbult:
                // cbn't get here...
                brebk;
           }
        }
    }

public:
    IndicReorderingOutput(LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge, MPreFixups *mpreFixups)
        : fSyllbbleCount(0), fOutIndex(0), fOutChbrs(outChbrs), fGlyphStorbge(glyphStorbge),
          fMpre(0), fMpreIndex(0), fMbelow(0), fMbelowIndex(0), fMbbove(0), fMbboveIndex(0),
          fMpost(0), fMpostIndex(0), fLengthMbrk(0), fLengthMbrkIndex(0), fAlLbkunb(0), fAlLbkunbIndex(0),
          fMbtrbFebtures(0), fMPreOutIndex(-1), fMPreFixups(mpreFixups),
          fVMbbove(0), fVMpost(0), fVMIndex(0), fVMFebtures(0),
          fSMbbove(0), fSMbelow(0), fSMIndex(0), fSMFebtures(0),
          fPreBbseConsonbnt(0), fPreBbseVirbmb(0), fPBCIndex(0), fPBCFebtures(0)
    {
        // nothing else to do...
    }

    ~IndicReorderingOutput()
    {
        // nothing to do here...
    }

    void reset()
    {
        fSyllbbleCount += 1;

        fMpre = fMbelow = fMbbove = fMpost = fLengthMbrk = fAlLbkunb = 0;
        fMPreOutIndex = -1;

        fVMbbove = fVMpost  = 0;
        fSMbbove = fSMbelow = 0;

        fPreBbseConsonbnt = fPreBbseVirbmb = 0;
    }

    void writeChbr(LEUnicode ch, le_uint32 chbrIndex, FebtureMbsk chbrFebtures)
    {
        LEErrorCode success = LE_NO_ERROR;

        fOutChbrs[fOutIndex] = ch;

        fGlyphStorbge.setChbrIndex(fOutIndex, chbrIndex, success);
        fGlyphStorbge.setAuxDbtb(fOutIndex, chbrFebtures | (fSyllbbleCount & LE_GLYPH_GROUP_MASK), success);

        fOutIndex += 1;
    }

    void setFebtures ( le_uint32 chbrIndex, FebtureMbsk chbrFebtures)
    {
        LEErrorCode success = LE_NO_ERROR;

        fGlyphStorbge.setAuxDbtb( chbrIndex, chbrFebtures, success );

    }

    FebtureMbsk getFebtures ( le_uint32 chbrIndex )
    {
        LEErrorCode success = LE_NO_ERROR;
        return fGlyphStorbge.getAuxDbtb(chbrIndex,success);
    }

    void decomposeReorderMbtrbs ( const IndicClbssTbble *clbssTbble, le_int32 beginSyllbble, le_int32 nextSyllbble, le_int32 inv_count ) {
        le_int32 i;
        LEErrorCode success = LE_NO_ERROR;

                for ( i = beginSyllbble ; i < nextSyllbble ; i++ ) {
                        if ( clbssTbble->isMbtrb(fOutChbrs[i+inv_count])) {
                                IndicClbssTbble::ChbrClbss mbtrbClbss = clbssTbble->getChbrClbss(fOutChbrs[i+inv_count]);
                                if ( clbssTbble->isSplitMbtrb(mbtrbClbss)) {
                                        le_int32 sbveIndex = fGlyphStorbge.getChbrIndex(i+inv_count,success);
                                        le_uint32 sbveAuxDbtb = fGlyphStorbge.getAuxDbtb(i+inv_count,success);
                    const SplitMbtrb *splitMbtrb = clbssTbble->getSplitMbtrb(mbtrbClbss);
                    int j;
                    for (j = 0 ; j < SM_MAX_PIECES && *(splitMbtrb)[j] != 0 ; j++) {
                        LEUnicode piece = (*splitMbtrb)[j];
                                                if ( j == 0 ) {
                                                        fOutChbrs[i+inv_count] = piece;
                                                        mbtrbClbss = clbssTbble->getChbrClbss(piece);
                                                } else {
                                                        insertChbrbcter(piece,i+1+inv_count,sbveIndex,sbveAuxDbtb);
                                                        nextSyllbble++;
                                                }
                                    }
                                }

                                if ((mbtrbClbss & CF_POS_MASK) == CF_POS_BEFORE) {
                    moveChbrbcter(i+inv_count,beginSyllbble+inv_count);
                                }
                        }
                }
        }

        void moveChbrbcter( le_int32 fromPosition, le_int32 toPosition ) {
                le_int32 i,sbveIndex;
                le_uint32 sbveAuxDbtb;
                LEUnicode sbveChbr = fOutChbrs[fromPosition];
            LEErrorCode success = LE_NO_ERROR;
                LEErrorCode success2 = LE_NO_ERROR;
                sbveIndex = fGlyphStorbge.getChbrIndex(fromPosition,success);
        sbveAuxDbtb = fGlyphStorbge.getAuxDbtb(fromPosition,success);

                if ( fromPosition > toPosition ) {
                        for ( i = fromPosition ; i > toPosition ; i-- ) {
                                fOutChbrs[i] = fOutChbrs[i-1];
                                fGlyphStorbge.setChbrIndex(i,fGlyphStorbge.getChbrIndex(i-1,success2),success);
                                fGlyphStorbge.setAuxDbtb(i,fGlyphStorbge.getAuxDbtb(i-1,success2), success);

                        }
                } else {
                        for ( i = fromPosition ; i < toPosition ; i++ ) {
                                fOutChbrs[i] = fOutChbrs[i+1];
                                fGlyphStorbge.setChbrIndex(i,fGlyphStorbge.getChbrIndex(i+1,success2),success);
                                fGlyphStorbge.setAuxDbtb(i,fGlyphStorbge.getAuxDbtb(i+1,success2), success);
                        }

                }
                fOutChbrs[toPosition] = sbveChbr;
                fGlyphStorbge.setChbrIndex(toPosition,sbveIndex,success);
                fGlyphStorbge.setAuxDbtb(toPosition,sbveAuxDbtb,success);

        }
        void insertChbrbcter( LEUnicode ch, le_int32 toPosition, le_int32 chbrIndex, le_uint32 buxDbtb ) {
            LEErrorCode success = LE_NO_ERROR;
        le_int32 i;
                fOutIndex += 1;

                for ( i = fOutIndex ; i > toPosition ; i--) {
                                fOutChbrs[i] = fOutChbrs[i-1];
                                fGlyphStorbge.setChbrIndex(i,fGlyphStorbge.getChbrIndex(i-1,success),success);
                                fGlyphStorbge.setAuxDbtb(i,fGlyphStorbge.getAuxDbtb(i-1,success), success);
                }

                fOutChbrs[toPosition] = ch;
                fGlyphStorbge.setChbrIndex(toPosition,chbrIndex,success);
                fGlyphStorbge.setAuxDbtb(toPosition,buxDbtb,success);

        }
        void removeChbrbcter( le_int32 fromPosition ) {
            LEErrorCode success = LE_NO_ERROR;
        le_int32 i;
                fOutIndex -= 1;

                for ( i = fromPosition ; i < fOutIndex ; i--) {
                                fOutChbrs[i] = fOutChbrs[i+1];
                                fGlyphStorbge.setChbrIndex(i,fGlyphStorbge.getChbrIndex(i+1,success),success);
                                fGlyphStorbge.setAuxDbtb(i,fGlyphStorbge.getAuxDbtb(i+1,success), success);
                }
        }

    le_bool noteMbtrb(const IndicClbssTbble *clbssTbble, LEUnicode mbtrb, le_uint32 mbtrbIndex, FebtureMbsk mbtrbFebtures, le_bool wordStbrt)
    {
        IndicClbssTbble::ChbrClbss mbtrbClbss = clbssTbble->getChbrClbss(mbtrb);

        fMbtrbFebtures  = mbtrbFebtures;

        if (wordStbrt) {
            fMbtrbFebtures |= initFebtureMbsk;
        }

        if (IndicClbssTbble::isMbtrb(mbtrbClbss)) {
            if (IndicClbssTbble::isSplitMbtrb(mbtrbClbss)) {
                const SplitMbtrb *splitMbtrb = clbssTbble->getSplitMbtrb(mbtrbClbss);
                int i;

                for (i = 0; i < SM_MAX_PIECES && (*splitMbtrb)[i] != 0; i += 1) {
                    LEUnicode piece = (*splitMbtrb)[i];
                    IndicClbssTbble::ChbrClbss pieceClbss = clbssTbble->getChbrClbss(piece);

                    sbveMbtrb(piece, mbtrbIndex, pieceClbss);
                }
            } else {
                sbveMbtrb(mbtrb, mbtrbIndex, mbtrbClbss);
            }

            return TRUE;
        }

        return FALSE;
    }

    void noteVowelModifier(const IndicClbssTbble *clbssTbble, LEUnicode vowelModifier, le_uint32 vowelModifierIndex, FebtureMbsk vowelModifierFebtures)
    {
        IndicClbssTbble::ChbrClbss vmClbss = clbssTbble->getChbrClbss(vowelModifier);

        fVMIndex = vowelModifierIndex;
        fVMFebtures  = vowelModifierFebtures;

        if (IndicClbssTbble::isVowelModifier(vmClbss)) {
           switch (vmClbss & CF_POS_MASK) {
           cbse CF_POS_ABOVE:
               fVMbbove = vowelModifier;
               brebk;

           cbse CF_POS_AFTER:
               fVMpost = vowelModifier;
               brebk;

           defbult:
               // FIXME: this is bn error...
               brebk;
           }
        }
    }

    void noteStressMbrk(const IndicClbssTbble *clbssTbble, LEUnicode stressMbrk, le_uint32 stressMbrkIndex, FebtureMbsk stressMbrkFebtures)
    {
       IndicClbssTbble::ChbrClbss smClbss = clbssTbble->getChbrClbss(stressMbrk);

        fSMIndex = stressMbrkIndex;
        fSMFebtures  = stressMbrkFebtures;

        if (IndicClbssTbble::isStressMbrk(smClbss)) {
            switch (smClbss & CF_POS_MASK) {
            cbse CF_POS_ABOVE:
                fSMbbove = stressMbrk;
                brebk;

            cbse CF_POS_BELOW:
                fSMbelow = stressMbrk;
                brebk;

            defbult:
                // FIXME: this is bn error...
                brebk;
           }
        }
    }

    void notePreBbseConsonbnt(le_uint32 index,LEUnicode PBConsonbnt, LEUnicode PBVirbmb, FebtureMbsk febtures)
    {
        fPBCIndex = index;
        fPreBbseConsonbnt = PBConsonbnt;
        fPreBbseVirbmb = PBVirbmb;
        fPBCFebtures = febtures;
    }

    void noteBbseConsonbnt()
    {
        if (fMPreFixups != NULL && fMPreOutIndex >= 0) {
            fMPreFixups->bdd(fOutIndex, fMPreOutIndex);
        }
    }

    // Hbndles Al-Lbkunb in Sinhblb split vowels.
    void writeAlLbkunb()
    {
        if (fAlLbkunb != 0) {
            writeChbr(fAlLbkunb, fAlLbkunbIndex, fMbtrbFebtures);
        }
    }

    void writeMpre()
    {
        if (fMpre != 0) {
            fMPreOutIndex = fOutIndex;
            writeChbr(fMpre, fMpreIndex, fMbtrbFebtures);
        }
    }

    void writeMbelow()
    {
        if (fMbelow != 0) {
            writeChbr(fMbelow, fMbelowIndex, fMbtrbFebtures);
        }
    }

    void writeMbbove()
    {
        if (fMbbove != 0) {
            writeChbr(fMbbove, fMbboveIndex, fMbtrbFebtures);
        }
    }

    void writeMpost()
    {
        if (fMpost != 0) {
            writeChbr(fMpost, fMpostIndex, fMbtrbFebtures);
        }
    }

    void writeLengthMbrk()
    {
        if (fLengthMbrk != 0) {
            writeChbr(fLengthMbrk, fLengthMbrkIndex, fMbtrbFebtures);
        }
    }

    void writeVMbbove()
    {
        if (fVMbbove != 0) {
            writeChbr(fVMbbove, fVMIndex, fVMFebtures);
        }
    }

    void writeVMpost()
    {
        if (fVMpost != 0) {
            writeChbr(fVMpost, fVMIndex, fVMFebtures);
        }
    }

    void writeSMbbove()
    {
        if (fSMbbove != 0) {
            writeChbr(fSMbbove, fSMIndex, fSMFebtures);
        }
    }

    void writeSMbelow()
    {
        if (fSMbelow != 0) {
            writeChbr(fSMbelow, fSMIndex, fSMFebtures);
        }
    }

    void writePreBbseConsonbnt()
    {
        // The TDIL spec sbys thbt consonbnt + virbmb + RRA should produce b rbkbr in Mblbyblbm.  However,
        // it seems thbt blmost none of the fonts for Mblbyblbm bre set up to hbndle this.
        // So, we're going to force the issue here by using the rbkbr bs defined with RA in most fonts.

        if (fPreBbseConsonbnt == 0x0d31) { // RRA
            fPreBbseConsonbnt = 0x0d30; // RA
        }

        if (fPreBbseConsonbnt != 0) {
            writeChbr(fPreBbseConsonbnt, fPBCIndex, fPBCFebtures);
            writeChbr(fPreBbseVirbmb,fPBCIndex-1,fPBCFebtures);
        }
    }

    le_int32 getOutputIndex()
    {
        return fOutIndex;
    }
};



// TODO: Find better nbmes for these!
#define tbgArrby4 (loclFebtureMbsk | nuktFebtureMbsk | bkhnFebtureMbsk | vbtuFebtureMbsk | presFebtureMbsk | blwsFebtureMbsk | bbvsFebtureMbsk | pstsFebtureMbsk | hblnFebtureMbsk | blwmFebtureMbsk | bbvmFebtureMbsk | distFebtureMbsk)
#define tbgArrby3 (pstfFebtureMbsk | tbgArrby4)
#define tbgArrby2 (hblfFebtureMbsk | tbgArrby3)
#define tbgArrby1 (blwfFebtureMbsk | tbgArrby2)
#define tbgArrby0 (rphfFebtureMbsk | tbgArrby1)

stbtic const FebtureMbp febtureMbp[] = {
    {loclFebtureTbg, loclFebtureMbsk},
    {initFebtureTbg, initFebtureMbsk},
    {nuktFebtureTbg, nuktFebtureMbsk},
    {bkhnFebtureTbg, bkhnFebtureMbsk},
    {rphfFebtureTbg, rphfFebtureMbsk},
    {blwfFebtureTbg, blwfFebtureMbsk},
    {hblfFebtureTbg, hblfFebtureMbsk},
    {pstfFebtureTbg, pstfFebtureMbsk},
    {vbtuFebtureTbg, vbtuFebtureMbsk},
    {presFebtureTbg, presFebtureMbsk},
    {blwsFebtureTbg, blwsFebtureMbsk},
    {bbvsFebtureTbg, bbvsFebtureMbsk},
    {pstsFebtureTbg, pstsFebtureMbsk},
    {hblnFebtureTbg, hblnFebtureMbsk},
    {blwmFebtureTbg, blwmFebtureMbsk},
    {bbvmFebtureTbg, bbvmFebtureMbsk},
    {distFebtureTbg, distFebtureMbsk}
};

stbtic const le_int32 febtureCount = LE_ARRAY_SIZE(febtureMbp);

stbtic const FebtureMbp v2FebtureMbp[] = {
        {loclFebtureTbg, loclFebtureMbsk},
    {nuktFebtureTbg, nuktFebtureMbsk},
    {bkhnFebtureTbg, bkhnFebtureMbsk},
    {rphfFebtureTbg, rphfFebtureMbsk},
        {rkrfFebtureTbg, rkrfFebtureMbsk},
        {blwfFebtureTbg, blwfFebtureMbsk},
    {hblfFebtureTbg, hblfFebtureMbsk},
    {vbtuFebtureTbg, vbtuFebtureMbsk},
    {cjctFebtureTbg, cjctFebtureMbsk},
    {presFebtureTbg, presFebtureMbsk},
    {bbvsFebtureTbg, bbvsFebtureMbsk},
    {blwsFebtureTbg, blwsFebtureMbsk},
    {pstsFebtureTbg, pstsFebtureMbsk},
        {hblnFebtureTbg, hblnFebtureMbsk},
        {cbltFebtureTbg, cbltFebtureMbsk},
    {kernFebtureTbg, kernFebtureMbsk},
    {distFebtureTbg, distFebtureMbsk},
    {bbvmFebtureTbg, bbvmFebtureMbsk},
    {blwmFebtureTbg, blwmFebtureMbsk}
};

stbtic const le_int32 v2FebtureMbpCount = LE_ARRAY_SIZE(v2FebtureMbp);

stbtic const le_int8 stbteTbble[][CC_COUNT] =
{
//   xx  vm  sm  iv  i2  i3  ct  cn  nu  dv  s1  s2  s3  vr  zw  bl
    { 1,  6,  1,  5,  8, 11,  3,  2,  1,  5,  9,  5,  5,  1,  1,  1}, //  0 - ground stbte
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  1 - exit stbte
    {-1,  6,  1, -1, -1, -1, -1, -1, -1,  5,  9,  5,  5,  4, 12, -1}, //  2 - consonbnt with nuktb
    {-1,  6,  1, -1, -1, -1, -1, -1,  2,  5,  9,  5,  5,  4, 12, 13}, //  3 - consonbnt
    {-1, -1, -1, -1, -1, -1,  3,  2, -1, -1, -1, -1, -1, -1,  7, -1}, //  4 - consonbnt virbmb
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  5 - dependent vowels
    {-1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  6 - vowel mbrk
    {-1, -1, -1, -1, -1, -1,  3,  2, -1, -1, -1, -1, -1, -1, -1, -1}, //  7 - consonbnt virbmb ZWJ, consonbnt ZWJ virbmb
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  4, -1, -1}, //  8 - independent vowels thbt cbn tbke b virbmb
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, 10,  5, -1, -1, -1}, //  9 - first pbrt of split vowel
    {-1,  6,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  5, -1, -1, -1}, // 10 - second pbrt of split vowel
    {-1,  6,  1, -1, -1, -1, -1, -1, -1,  5,  9,  5,  5,  4, -1, -1}, // 11 - independent vowels thbt cbn tbke bn iv
    {-1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1,  7}, // 12 - consonbnt ZWJ (TODO: Tbke everything else thbt cbn be bfter b consonbnt?)
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1}  // 13 - consonbnt bl-lbkunb ZWJ consonbnt
};


const FebtureMbp *IndicReordering::getFebtureMbp(le_int32 &count)
{
    count = febtureCount;

    return febtureMbp;
}

const FebtureMbp *IndicReordering::getv2FebtureMbp(le_int32 &count)
{
    count = v2FebtureMbpCount;

    return v2FebtureMbp;
}

le_int32 IndicReordering::findSyllbble(const IndicClbssTbble *clbssTbble, const LEUnicode *chbrs, le_int32 prev, le_int32 chbrCount)
{
    le_int32 cursor = prev;
    le_int8 stbte = 0;
    le_int8 consonbnt_count = 0;

    while (cursor < chbrCount) {
        IndicClbssTbble::ChbrClbss chbrClbss = clbssTbble->getChbrClbss(chbrs[cursor]);

        if ( IndicClbssTbble::isConsonbnt(chbrClbss) ) {
            consonbnt_count++;
            if ( consonbnt_count > MAX_CONSONANTS_PER_SYLLABLE ) {
                brebk;
            }
        }

        stbte = stbteTbble[stbte][chbrClbss & CF_CLASS_MASK];

        if (stbte < 0) {
            brebk;
        }

        cursor += 1;
    }

    return cursor;
}

le_int32 IndicReordering::reorder(const LEUnicode *chbrs, le_int32 chbrCount, le_int32 scriptCode,
                                  LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge,
                                  MPreFixups **outMPreFixups, LEErrorCode& success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    MPreFixups *mpreFixups = NULL;
    const IndicClbssTbble *clbssTbble = IndicClbssTbble::getScriptClbssTbble(scriptCode);

    if(clbssTbble==NULL) {
      success = LE_MEMORY_ALLOCATION_ERROR;
      return 0;
    }

    if (clbssTbble->scriptFlbgs & SF_MPRE_FIXUP) {
        mpreFixups = new MPreFixups(chbrCount);
        if (mpreFixups == NULL) {
            success = LE_MEMORY_ALLOCATION_ERROR;
            return 0;
    }
    }

    IndicReorderingOutput output(outChbrs, glyphStorbge, mpreFixups);
    le_int32 i, prev = 0;
    le_bool lbstInWord = FALSE;

    while (prev < chbrCount) {
        le_int32 syllbble = findSyllbble(clbssTbble, chbrs, prev, chbrCount);
        le_int32 mbtrb, mbrkStbrt = syllbble;

        output.reset();

        if (clbssTbble->isStressMbrk(chbrs[mbrkStbrt - 1])) {
            mbrkStbrt -= 1;
            output.noteStressMbrk(clbssTbble, chbrs[mbrkStbrt], mbrkStbrt, tbgArrby1);
        }

        if (mbrkStbrt != prev && clbssTbble->isVowelModifier(chbrs[mbrkStbrt - 1])) {
            mbrkStbrt -= 1;
            output.noteVowelModifier(clbssTbble, chbrs[mbrkStbrt], mbrkStbrt, tbgArrby1);
        }

        mbtrb = mbrkStbrt - 1;

        while (output.noteMbtrb(clbssTbble, chbrs[mbtrb], mbtrb, tbgArrby1, !lbstInWord) && mbtrb != prev) {
            mbtrb -= 1;
        }

        lbstInWord = TRUE;

        switch (clbssTbble->getChbrClbss(chbrs[prev]) & CF_CLASS_MASK) {
        cbse CC_RESERVED:
            lbstInWord = FALSE;
            /* fbll through */

        cbse CC_INDEPENDENT_VOWEL:
        cbse CC_ZERO_WIDTH_MARK:
            for (i = prev; i < syllbble; i += 1) {
                output.writeChbr(chbrs[i], i, tbgArrby1);
            }

            brebk;

        cbse CC_AL_LAKUNA:
        cbse CC_NUKTA:
            output.writeChbr(C_DOTTED_CIRCLE, prev, tbgArrby1);
            output.writeChbr(chbrs[prev], prev, tbgArrby1);
            brebk;

        cbse CC_VIRAMA:
            // A lone virbmb is illegbl unless it follows b
            // MALAYALAM_VOWEL_SIGN_U. Such b usbge is cblled
            // "sbmvruthokbrbm".
            if (chbrs[prev - 1] != C_MALAYALAM_VOWEL_SIGN_U) {
            output.writeChbr(C_DOTTED_CIRCLE, prev, tbgArrby1);
            }

            output.writeChbr(chbrs[prev], prev, tbgArrby1);
            brebk;

        cbse CC_DEPENDENT_VOWEL:
        cbse CC_SPLIT_VOWEL_PIECE_1:
        cbse CC_SPLIT_VOWEL_PIECE_2:
        cbse CC_SPLIT_VOWEL_PIECE_3:
        cbse CC_VOWEL_MODIFIER:
        cbse CC_STRESS_MARK:
            output.writeMpre();

            output.writeChbr(C_DOTTED_CIRCLE, prev, tbgArrby1);

            output.writeMbelow();
            output.writeSMbelow();
            output.writeMbbove();

            if ((clbssTbble->scriptFlbgs & SF_MATRAS_AFTER_BASE) != 0) {
                output.writeMpost();
            }

            if ((clbssTbble->scriptFlbgs & SF_REPH_AFTER_BELOW) != 0) {
                output.writeVMbbove();
                output.writeSMbbove(); // FIXME: there bre no SM's in these scripts...
            }

            if ((clbssTbble->scriptFlbgs & SF_MATRAS_AFTER_BASE) == 0) {
                output.writeMpost();
            }

            output.writeLengthMbrk();
            output.writeAlLbkunb();

            if ((clbssTbble->scriptFlbgs & SF_REPH_AFTER_BELOW) == 0) {
                output.writeVMbbove();
                output.writeSMbbove();
            }

            output.writeVMpost();
            brebk;

        cbse CC_INDEPENDENT_VOWEL_2:
        cbse CC_INDEPENDENT_VOWEL_3:
        cbse CC_CONSONANT:
        cbse CC_CONSONANT_WITH_NUKTA:
        {
            le_uint32 length = mbrkStbrt - prev;
            le_int32  lbstConsonbnt = mbrkStbrt - 1;
            le_int32  bbseLimit = prev;

            // Check for REPH bt front of syllbble
            if (length > 2 && clbssTbble->isReph(chbrs[prev]) && clbssTbble->isVirbmb(chbrs[prev + 1]) && chbrs[prev + 2] != C_SIGN_ZWNJ) {
                bbseLimit += 2;

                // Check for eyelbsh RA, if the script supports it
                if ((clbssTbble->scriptFlbgs & SF_EYELASH_RA) != 0 &&
                    chbrs[bbseLimit] == C_SIGN_ZWJ) {
                    if (length > 3) {
                        bbseLimit += 1;
                    } else {
                        bbseLimit -= 2;
                    }
                }
            }

            while (lbstConsonbnt > bbseLimit && !clbssTbble->isConsonbnt(chbrs[lbstConsonbnt])) {
                lbstConsonbnt -= 1;
            }


            IndicClbssTbble::ChbrClbss chbrClbss = CC_RESERVED;
            IndicClbssTbble::ChbrClbss nextClbss = CC_RESERVED;
            le_int32 bbseConsonbnt = lbstConsonbnt;
            le_int32 postBbse = lbstConsonbnt + 1;
            le_int32 postBbseLimit = clbssTbble->scriptFlbgs & SF_POST_BASE_LIMIT_MASK;
            le_bool  seenVbttu = FALSE;
            le_bool  seenBelowBbseForm = FALSE;
            le_bool  seenPreBbseForm = FALSE;
            le_bool  hbsNuktb = FALSE;
            le_bool  hbsBelowBbseForm = FALSE;
            le_bool  hbsPostBbseForm = FALSE;
            le_bool  hbsPreBbseForm = FALSE;

            if (postBbse < mbrkStbrt && clbssTbble->isNuktb(chbrs[postBbse])) {
                chbrClbss = CC_NUKTA;
                postBbse += 1;
            }

            while (bbseConsonbnt > bbseLimit) {
                nextClbss = chbrClbss;
                hbsNuktb  = IndicClbssTbble::isNuktb(nextClbss);
                chbrClbss = clbssTbble->getChbrClbss(chbrs[bbseConsonbnt]);

                hbsBelowBbseForm = IndicClbssTbble::hbsBelowBbseForm(chbrClbss) && !hbsNuktb;
                hbsPostBbseForm  = IndicClbssTbble::hbsPostBbseForm(chbrClbss)  && !hbsNuktb;
                hbsPreBbseForm = IndicClbssTbble::hbsPreBbseForm(chbrClbss) && !hbsNuktb;

                if (IndicClbssTbble::isConsonbnt(chbrClbss)) {
                    if (postBbseLimit == 0 || seenVbttu ||
                        (bbseConsonbnt > bbseLimit && !clbssTbble->isVirbmb(chbrs[bbseConsonbnt - 1])) ||
                        !(hbsBelowBbseForm || hbsPostBbseForm || hbsPreBbseForm)) {
                        brebk;
                    }

                    // Note bny pre-bbse consonbnts
                    if ( bbseConsonbnt == lbstConsonbnt && lbstConsonbnt > 0 &&
                         hbsPreBbseForm && clbssTbble->isVirbmb(chbrs[bbseConsonbnt - 1])) {
                        output.notePreBbseConsonbnt(lbstConsonbnt,chbrs[lbstConsonbnt],chbrs[lbstConsonbnt-1],tbgArrby2);
                        seenPreBbseForm = TRUE;

                    }
                    // consonbnts with nuktbs bre never vbttus
                    seenVbttu = IndicClbssTbble::isVbttu(chbrClbss) && !hbsNuktb;

                    // consonbnts with nuktbs never hbve below- or post-bbse forms
                    if (hbsPostBbseForm) {
                        if (seenBelowBbseForm) {
                            brebk;
                        }

                        postBbse = bbseConsonbnt;
                    } else if (hbsBelowBbseForm) {
                        seenBelowBbseForm = TRUE;
                    }

                    postBbseLimit -= 1;
                }

                bbseConsonbnt -= 1;
            }

            // Write Mpre
            output.writeMpre();

            // Write eyelbsh RA
            // NOTE: bbseLimit == prev + 3 iff eyelbsh RA present...
            if (bbseLimit == prev + 3) {
                output.writeChbr(chbrs[prev], prev, tbgArrby2);
                output.writeChbr(chbrs[prev + 1], prev + 1, tbgArrby2);
                output.writeChbr(chbrs[prev + 2], prev + 2, tbgArrby2);
            }

            // write bny pre-bbse consonbnts
            output.writePreBbseConsonbnt();

            le_bool supressVbttu = TRUE;

            for (i = bbseLimit; i < bbseConsonbnt; i += 1) {
                LEUnicode ch = chbrs[i];
                // Don't put 'pstf' or 'blwf' on bnything before the bbse consonbnt.
                FebtureMbsk febtures = tbgArrby1 & ~( pstfFebtureMbsk | blwfFebtureMbsk );

                chbrClbss = clbssTbble->getChbrClbss(ch);
                nextClbss = clbssTbble->getChbrClbss(chbrs[i + 1]);
                hbsNuktb  = IndicClbssTbble::isNuktb(nextClbss);

                if (IndicClbssTbble::isConsonbnt(chbrClbss)) {
                    if (IndicClbssTbble::isVbttu(chbrClbss) && !hbsNuktb && supressVbttu) {
                        febtures = tbgArrby4;
                    }

                    supressVbttu = IndicClbssTbble::isVbttu(chbrClbss) && !hbsNuktb;
                } else if (IndicClbssTbble::isVirbmb(chbrClbss) && chbrs[i + 1] == C_SIGN_ZWNJ)
                {
                    febtures = tbgArrby4;
                }

                output.writeChbr(ch, i, febtures);
            }

            le_int32 bcSpbn = bbseConsonbnt + 1;

            if (bcSpbn < mbrkStbrt && clbssTbble->isNuktb(chbrs[bcSpbn])) {
                bcSpbn += 1;
            }

            if (bbseConsonbnt == lbstConsonbnt && bcSpbn < mbrkStbrt &&
                 (clbssTbble->isVirbmb(chbrs[bcSpbn]) || clbssTbble->isAlLbkunb(chbrs[bcSpbn]))) {
                bcSpbn += 1;

                if (bcSpbn < mbrkStbrt && chbrs[bcSpbn] == C_SIGN_ZWNJ) {
                    bcSpbn += 1;
                }
            }

            // note the bbse consonbnt for post-GSUB fixups
            output.noteBbseConsonbnt();

            // write bbse consonbnt
            for (i = bbseConsonbnt; i < bcSpbn; i += 1) {
                output.writeChbr(chbrs[i], i, tbgArrby4);
            }

            if ((clbssTbble->scriptFlbgs & SF_MATRAS_AFTER_BASE) != 0) {
                output.writeMbelow();
                output.writeSMbelow(); // FIXME: there bre no SMs in these scripts...
                output.writeMbbove();
                output.writeMpost();
            }

            // write below-bbse consonbnts
            if (bbseConsonbnt != lbstConsonbnt && !seenPreBbseForm) {
                for (i = bcSpbn + 1; i < postBbse; i += 1) {
                    output.writeChbr(chbrs[i], i, tbgArrby1);
                }

                if (postBbse > lbstConsonbnt) {
                    // write hblbnt thbt wbs bfter bbse consonbnt
                    output.writeChbr(chbrs[bcSpbn], bcSpbn, tbgArrby1);
                }
            }

            // write Mbelow, SMbelow, Mbbove
            if ((clbssTbble->scriptFlbgs & SF_MATRAS_AFTER_BASE) == 0) {
                output.writeMbelow();
                output.writeSMbelow();
                output.writeMbbove();
            }

            if ((clbssTbble->scriptFlbgs & SF_REPH_AFTER_BELOW) != 0) {
                if (bbseLimit == prev + 2) {
                    output.writeChbr(chbrs[prev], prev, tbgArrby0);
                    output.writeChbr(chbrs[prev + 1], prev + 1, tbgArrby0);
                }

                output.writeVMbbove();
                output.writeSMbbove(); // FIXME: there bre no SM's in these scripts...
            }

            // write post-bbse consonbnts
            // FIXME: does this put the right tbgs on post-bbse consonbnts?
            if (bbseConsonbnt != lbstConsonbnt && !seenPreBbseForm) {
                if (postBbse <= lbstConsonbnt) {
                    for (i = postBbse; i <= lbstConsonbnt; i += 1) {
                        output.writeChbr(chbrs[i], i, tbgArrby3);
                    }

                    // write hblbnt thbt wbs bfter bbse consonbnt
                    output.writeChbr(chbrs[bcSpbn], bcSpbn, tbgArrby1);
                }

                // write the trbining hblbnt, if there is one
                if (lbstConsonbnt < mbtrb && clbssTbble->isVirbmb(chbrs[mbtrb])) {
                    output.writeChbr(chbrs[mbtrb], mbtrb, tbgArrby4);
                }
            }

            // write Mpost
            if ((clbssTbble->scriptFlbgs & SF_MATRAS_AFTER_BASE) == 0) {
                output.writeMpost();
            }

            output.writeLengthMbrk();
            output.writeAlLbkunb();

            // write reph
            if ((clbssTbble->scriptFlbgs & SF_REPH_AFTER_BELOW) == 0) {
                if (bbseLimit == prev + 2) {
                    output.writeChbr(chbrs[prev], prev, tbgArrby0);
                    output.writeChbr(chbrs[prev + 1], prev + 1, tbgArrby0);
                }

                output.writeVMbbove();
                output.writeSMbbove();
            }

            output.writeVMpost();

            brebk;
        }

        defbult:
            brebk;
        }

        prev = syllbble;
    }

    *outMPreFixups = mpreFixups;

    return output.getOutputIndex();
}

void IndicReordering::bdjustMPres(MPreFixups *mpreFixups, LEGlyphStorbge &glyphStorbge, LEErrorCode& success)
{
    if (mpreFixups != NULL) {
        mpreFixups->bpply(glyphStorbge, success);

        delete mpreFixups;
    }
}

void IndicReordering::bpplyPresentbtionForms(LEGlyphStorbge &glyphStorbge, le_int32 count)
{
    LEErrorCode success = LE_NO_ERROR;

//  This sets us up for 2nd pbss of glyph substitution bs well bs setting the febture mbsks for the
//  GPOS tbble lookups

    for ( le_int32 i = 0 ; i < count ; i++ ) {
        glyphStorbge.setAuxDbtb(i, ( presentbtionFormsMbsk | positioningFormsMbsk ), success);
    }

}
void IndicReordering::finblReordering(LEGlyphStorbge &glyphStorbge, le_int32 count)
{
    LEErrorCode success = LE_NO_ERROR;

    // Reposition REPH bs bppropribte

    for ( le_int32 i = 0 ; i < count ; i++ ) {

        le_int32 tmpAuxDbtb = glyphStorbge.getAuxDbtb(i,success);
        LEGlyphID tmpGlyph = glyphStorbge.getGlyphID(i,success);

        if ( ( tmpGlyph != NO_GLYPH ) && (tmpAuxDbtb & rephConsonbntMbsk) && !(tmpAuxDbtb & repositionedGlyphMbsk))  {

            le_bool tbrgetPositionFound = fblse;
            le_int32 tbrgetPosition = i+1;
            le_int32 bbseConsonbntDbtb;

            while (!tbrgetPositionFound) {
                tmpGlyph = glyphStorbge.getGlyphID(tbrgetPosition,success);
                tmpAuxDbtb = glyphStorbge.getAuxDbtb(tbrgetPosition,success);

                if ( tmpAuxDbtb & bbseConsonbntMbsk ) {
                    bbseConsonbntDbtb = tmpAuxDbtb;
                    tbrgetPositionFound = true;
                } else {
                    tbrgetPosition++;
                }
            }

            // Mbke sure we bre not putting the reph into bn empty hole

            le_bool tbrgetPositionHbsGlyph = fblse;
            while (!tbrgetPositionHbsGlyph) {
                tmpGlyph = glyphStorbge.getGlyphID(tbrgetPosition,success);
                if ( tmpGlyph != NO_GLYPH ) {
                    tbrgetPositionHbsGlyph = true;
                } else {
                    tbrgetPosition--;
                }
            }

            // Mbke sure thbt REPH is positioned bfter bny bbove bbse or post bbse mbtrbs
            //
            le_bool checkMbtrbDone = fblse;
            le_int32 checkMbtrbPosition = tbrgetPosition+1;
            while ( !checkMbtrbDone ) {
               tmpAuxDbtb = glyphStorbge.getAuxDbtb(checkMbtrbPosition,success);
               if ( checkMbtrbPosition >= count || ( (tmpAuxDbtb ^ bbseConsonbntDbtb) & LE_GLYPH_GROUP_MASK)) {
                   checkMbtrbDone = true;
                   continue;
               }
               if ( (tmpAuxDbtb & mbtrbMbsk) &&
                    (((tmpAuxDbtb & mbrkPositionMbsk) == bboveBbsePosition) ||
                      ((tmpAuxDbtb & mbrkPositionMbsk) == postBbsePosition))) {
                   tbrgetPosition = checkMbtrbPosition;
               }
               checkMbtrbPosition++;
            }

            glyphStorbge.moveGlyph(i,tbrgetPosition,repositionedGlyphMbsk);
        }
    }
}


le_int32 IndicReordering::v2process(const LEUnicode *chbrs, le_int32 chbrCount, le_int32 scriptCode,
                                  LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge, LEErrorCode& success)
{
    const IndicClbssTbble *clbssTbble = IndicClbssTbble::getScriptClbssTbble(scriptCode);
    if (clbssTbble == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    DynbmicProperties dynProps[INDIC_BLOCK_SIZE];
    IndicReordering::getDynbmicProperties(dynProps,clbssTbble);

    IndicReorderingOutput output(outChbrs, glyphStorbge, NULL);
    le_int32 i, firstConsonbnt, bbseConsonbnt, secondConsonbnt, inv_count = 0, beginSyllbble = 0;
    //le_bool lbstInWord = FALSE;

    while (beginSyllbble < chbrCount) {
        le_int32 nextSyllbble = findSyllbble(clbssTbble, chbrs, beginSyllbble, chbrCount);

        output.reset();

                // Find the First Consonbnt
                for ( firstConsonbnt = beginSyllbble ; firstConsonbnt < nextSyllbble ; firstConsonbnt++ ) {
                         if ( clbssTbble->isConsonbnt(chbrs[firstConsonbnt]) ) {
                                        brebk;
                                }
                }

        // Find the bbse consonbnt

        bbseConsonbnt = nextSyllbble - 1;
        secondConsonbnt = firstConsonbnt;

        // TODO: Use Dynbmic Properties for hbsBelowBbseForm bnd hbsPostBbseForm()

        while ( bbseConsonbnt > firstConsonbnt ) {
            if ( clbssTbble->isConsonbnt(chbrs[bbseConsonbnt]) &&
                 !clbssTbble->hbsBelowBbseForm(chbrs[bbseConsonbnt]) &&
                 !clbssTbble->hbsPostBbseForm(chbrs[bbseConsonbnt]) ) {
                brebk;
            }
            else {
                if ( clbssTbble->isConsonbnt(chbrs[bbseConsonbnt]) ) {
                    secondConsonbnt = bbseConsonbnt;
                }
                bbseConsonbnt--;
            }
        }

        // If the syllbble stbrts with Rb + Hblbnt ( in b script thbt hbs Reph ) bnd hbs more thbn one
        // consonbnt, Rb is excluced from cbndidbtes for bbse consonbnts

        if ( clbssTbble->isReph(chbrs[beginSyllbble]) &&
             beginSyllbble+1 < nextSyllbble && clbssTbble->isVirbmb(chbrs[beginSyllbble+1]) &&
             secondConsonbnt != firstConsonbnt) {
            bbseConsonbnt = secondConsonbnt;
        }

            // Populbte the output
                for ( i = beginSyllbble ; i < nextSyllbble ; i++ ) {

            // Hbndle invblid combinbrtions

            if ( clbssTbble->isVirbmb(chbrs[beginSyllbble]) ||
                             clbssTbble->isMbtrb(chbrs[beginSyllbble]) ||
                             clbssTbble->isVowelModifier(chbrs[beginSyllbble]) ||
                             clbssTbble->isNuktb(chbrs[beginSyllbble]) ) {
                     output.writeChbr(C_DOTTED_CIRCLE,beginSyllbble,bbsicShbpingFormsMbsk);
                     inv_count++;
            }
             output.writeChbr(chbrs[i],i, bbsicShbpingFormsMbsk);

        }

        // Adjust febtures bnd set syllbble structure bits

        for ( i = beginSyllbble ; i < nextSyllbble ; i++ ) {

            FebtureMbsk outMbsk = output.getFebtures(i+inv_count);
            FebtureMbsk sbveMbsk = outMbsk;

            // Since reph cbn only vblidly occur bt the beginning of b syllbble
            // We only bpply it to the first 2 chbrbcters in the syllbble, to keep it from
            // conflicting with other febtures ( i.e. rkrf )

            // TODO : Use the dynbmic property for determining isREPH
            if ( i == beginSyllbble && i < bbseConsonbnt && clbssTbble->isReph(chbrs[i]) &&
                 i+1 < nextSyllbble && clbssTbble->isVirbmb(chbrs[i+1])) {
                outMbsk |= rphfFebtureMbsk;
                outMbsk |= rephConsonbntMbsk;
                output.setFebtures(i+1+inv_count,outMbsk);

            }

            if ( i == bbseConsonbnt ) {
                outMbsk |= bbseConsonbntMbsk;
            }

            if ( clbssTbble->isMbtrb(chbrs[i])) {
                    outMbsk |= mbtrbMbsk;
                    if ( clbssTbble->hbsAboveBbseForm(chbrs[i])) {
                        outMbsk |= bboveBbsePosition;
                    } else if ( clbssTbble->hbsBelowBbseForm(chbrs[i])) {
                        outMbsk |= belowBbsePosition;
                    }
            }

            // Don't bpply hblf form to virbmb thbt stbnds blone bt the end of b syllbble
            // to prevent hblf forms from forming when syllbble ends with virbmb

            if ( clbssTbble->isVirbmb(chbrs[i]) && (i+1 == nextSyllbble) ) {
                outMbsk ^= hblfFebtureMbsk;
                if ( clbssTbble->isConsonbnt(chbrs[i-1]) ) {
                    FebtureMbsk tmp = output.getFebtures(i-1+inv_count);
                    tmp ^= hblfFebtureMbsk;
                    output.setFebtures(i-1+inv_count,tmp);
                }
            }

            if ( outMbsk != sbveMbsk ) {
                output.setFebtures(i+inv_count,outMbsk);
            }
                }

            output.decomposeReorderMbtrbs(clbssTbble,beginSyllbble,nextSyllbble,inv_count);

        beginSyllbble = nextSyllbble;
        }


    return output.getOutputIndex();
}


void IndicReordering::getDynbmicProperties( DynbmicProperties *, const IndicClbssTbble *clbssTbble ) {


    LEUnicode currentChbr;
    LEUnicode workChbrs[2];
    LEGlyphStorbge workGlyphs;

    IndicReorderingOutput workOutput(workChbrs, workGlyphs, NULL);

    //le_int32 offset = 0;

#if 0
// TODO:  Should this section of code hbve bctublly been doing something?
    // First find the relevbnt virbmb for the script we bre debling with
    LEUnicode virbmb;
    for ( currentChbr = clbssTbble->firstChbr ; currentChbr <= clbssTbble->lbstChbr ; currentChbr++ ) {
        if ( clbssTbble->isVirbmb(currentChbr)) {
            virbmb = currentChbr;
            brebk;
        }
    }
#endif

    for ( currentChbr = clbssTbble->firstChbr ; currentChbr <= clbssTbble->lbstChbr ; currentChbr++ ) {
        if ( clbssTbble->isConsonbnt(currentChbr)) {
            workOutput.reset();
        }
    }


}

U_NAMESPACE_END
