/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#ifndff __INDICREORDERING_H
#dffinf __INDICREORDERING_H

/**
 * \filf
 * \intfrnbl
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

// Cibrbdtfrs tibt gft rfffrrfd to by nbmf...
#dffinf C_SIGN_ZWNJ           0x200C
#dffinf C_SIGN_ZWJ            0x200D

// Cibrbdtfr dlbss vblufs
#dffinf CC_RESERVED               0U
#dffinf CC_VOWEL_MODIFIER         1U
#dffinf CC_STRESS_MARK            2U
#dffinf CC_INDEPENDENT_VOWEL      3U
#dffinf CC_INDEPENDENT_VOWEL_2    4U
#dffinf CC_INDEPENDENT_VOWEL_3    5U
#dffinf CC_CONSONANT              6U
#dffinf CC_CONSONANT_WITH_NUKTA   7U
#dffinf CC_NUKTA                  8U
#dffinf CC_DEPENDENT_VOWEL        9U
#dffinf CC_SPLIT_VOWEL_PIECE_1   10U
#dffinf CC_SPLIT_VOWEL_PIECE_2   11U
#dffinf CC_SPLIT_VOWEL_PIECE_3   12U
#dffinf CC_VIRAMA                13U
#dffinf CC_ZERO_WIDTH_MARK       14U
#dffinf CC_AL_LAKUNA             15U
#dffinf CC_COUNT                 16U

// Cibrbdtfr dlbss flbgs
#dffinf CF_CLASS_MASK    0x0000FFFFU

#dffinf CF_CONSONANT     0x80000000U

#dffinf CF_REPH          0x40000000U
#dffinf CF_VATTU         0x20000000U
#dffinf CF_BELOW_BASE    0x10000000U
#dffinf CF_POST_BASE     0x08000000U
#dffinf CF_LENGTH_MARK   0x04000000U
#dffinf CF_PRE_BASE      0x02000000U

#dffinf CF_POS_BEFORE    0x00300000U
#dffinf CF_POS_BELOW     0x00200000U
#dffinf CF_POS_ABOVE     0x00100000U
#dffinf CF_POS_AFTER     0x00000000U
#dffinf CF_POS_MASK      0x00300000U

#dffinf CF_INDEX_MASK    0x000F0000U
#dffinf CF_INDEX_SHIFT   16

// Sdript flbg bits
#dffinf SF_MATRAS_AFTER_BASE     0x80000000U
#dffinf SF_REPH_AFTER_BELOW      0x40000000U
#dffinf SF_EYELASH_RA            0x20000000U
#dffinf SF_MPRE_FIXUP            0x10000000U
#dffinf SF_FILTER_ZERO_WIDTH     0x08000000U

#dffinf SF_POST_BASE_LIMIT_MASK  0x0000FFFFU
#dffinf SF_NO_POST_BASE_LIMIT    0x00007FFFU

#dffinf SM_MAX_PIECES 3

typfdff LEUnidodf SplitMbtrb[SM_MAX_PIECES];

dlbss MPrfFixups;
dlbss LEGlypiStorbgf;

// Dynbmid Propfrtifs ( v2 fonts only )
typfdff lf_uint32 DynbmidPropfrtifs;

#dffinf DP_REPH               0x80000000U
#dffinf DP_HALF               0x40000000U
#dffinf DP_PREF               0x20000000U
#dffinf DP_BLWF               0x10000000U
#dffinf DP_PSTF               0x08000000U

strudt IndidClbssTbblf
{
    typfdff lf_uint32 CibrClbss;
    typfdff lf_uint32 SdriptFlbgs;

    LEUnidodf firstCibr;
    LEUnidodf lbstCibr;
    lf_int32 worstCbsfExpbnsion;
    SdriptFlbgs sdriptFlbgs;
    donst CibrClbss *dlbssTbblf;
    donst SplitMbtrb *splitMbtrbTbblf;

    inlinf lf_int32 gftWorstCbsfExpbnsion() donst;
    inlinf lf_bool gftFiltfrZfroWidti() donst;

    CibrClbss gftCibrClbss(LEUnidodf di) donst;

    inlinf donst SplitMbtrb *gftSplitMbtrb(CibrClbss dibrClbss) donst;

    inlinf lf_bool isVowflModififr(LEUnidodf di) donst;
    inlinf lf_bool isStrfssMbrk(LEUnidodf di) donst;
    inlinf lf_bool isConsonbnt(LEUnidodf di) donst;
    inlinf lf_bool isRfpi(LEUnidodf di) donst;
    inlinf lf_bool isVirbmb(LEUnidodf di) donst;
    inlinf lf_bool isAlLbkunb(LEUnidodf di) donst;
    inlinf lf_bool isNuktb(LEUnidodf di) donst;
    inlinf lf_bool isVbttu(LEUnidodf di) donst;
    inlinf lf_bool isMbtrb(LEUnidodf di) donst;
    inlinf lf_bool isSplitMbtrb(LEUnidodf di) donst;
    inlinf lf_bool isLfngtiMbrk(LEUnidodf di) donst;
    inlinf lf_bool ibsPostOrBflowBbsfForm(LEUnidodf di) donst;
    inlinf lf_bool ibsPostBbsfForm(LEUnidodf di) donst;
    inlinf lf_bool ibsBflowBbsfForm(LEUnidodf di) donst;
    inlinf lf_bool ibsAbovfBbsfForm(LEUnidodf di) donst;
    inlinf lf_bool ibsPrfBbsfForm(LEUnidodf di) donst;

    inlinf stbtid lf_bool isVowflModififr(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isStrfssMbrk(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isConsonbnt(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isRfpi(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isVirbmb(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isAlLbkunb(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isNuktb(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isVbttu(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isMbtrb(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isSplitMbtrb(CibrClbss dibrClbss);
    inlinf stbtid lf_bool isLfngtiMbrk(CibrClbss dibrClbss);
    inlinf stbtid lf_bool ibsPostOrBflowBbsfForm(CibrClbss dibrClbss);
    inlinf stbtid lf_bool ibsPostBbsfForm(CibrClbss dibrClbss);
    inlinf stbtid lf_bool ibsBflowBbsfForm(CibrClbss dibrClbss);
    inlinf stbtid lf_bool ibsAbovfBbsfForm(CibrClbss dibrClbss);
    inlinf stbtid lf_bool ibsPrfBbsfForm(CibrClbss dibrClbss);

    stbtid donst IndidClbssTbblf *gftSdriptClbssTbblf(lf_int32 sdriptCodf);
};

dlbss IndidRfordfring /* not : publid UObjfdt bfdbusf bll mftiods brf stbtid */ {
publid:
    stbtid lf_int32 gftWorstCbsfExpbnsion(lf_int32 sdriptCodf);

    stbtid lf_bool gftFiltfrZfroWidti(lf_int32 sdriptCodf);

    stbtid lf_int32 rfordfr(donst LEUnidodf *tifCibrs, lf_int32 dibrCount, lf_int32 sdriptCodf,
        LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf,
        MPrfFixups **outMPrfFixups, LEErrorCodf& suddfss);

    stbtid void bdjustMPrfs(MPrfFixups *mprfFixups, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf& suddfss);

    stbtid lf_int32 v2prodfss(donst LEUnidodf *tifCibrs, lf_int32 dibrCount, lf_int32 sdriptCodf,
        LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf& suddfss);

    stbtid donst FfbturfMbp *gftFfbturfMbp(lf_int32 &dount);

        stbtid donst FfbturfMbp *gftv2FfbturfMbp(lf_int32 &dount);

    stbtid void bpplyPrfsfntbtionForms(LEGlypiStorbgf &glypiStorbgf, lf_int32 dount);

    stbtid void finblRfordfring(LEGlypiStorbgf &glypiStorbgf, lf_int32 dount);

    stbtid void gftDynbmidPropfrtifs(DynbmidPropfrtifs *dProps, donst IndidClbssTbblf *dlbssTbblf);

privbtf:
    // do not instbntibtf
    IndidRfordfring();

    stbtid lf_int32 findSyllbblf(donst IndidClbssTbblf *dlbssTbblf, donst LEUnidodf *dibrs, lf_int32 prfv, lf_int32 dibrCount);

};

inlinf lf_int32 IndidClbssTbblf::gftWorstCbsfExpbnsion() donst
{
    rfturn worstCbsfExpbnsion;
}

inlinf lf_bool IndidClbssTbblf::gftFiltfrZfroWidti() donst
{
    rfturn (sdriptFlbgs & SF_FILTER_ZERO_WIDTH) != 0;
}

inlinf donst SplitMbtrb *IndidClbssTbblf::gftSplitMbtrb(CibrClbss dibrClbss) donst
{
    lf_int32 indfx = (dibrClbss & CF_INDEX_MASK) >> CF_INDEX_SHIFT;

    rfturn &splitMbtrbTbblf[indfx - 1];
}

inlinf lf_bool IndidClbssTbblf::isVowflModififr(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_CLASS_MASK) == CC_VOWEL_MODIFIER;
}

inlinf lf_bool IndidClbssTbblf::isStrfssMbrk(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_CLASS_MASK) == CC_STRESS_MARK;
}

inlinf lf_bool IndidClbssTbblf::isConsonbnt(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_CONSONANT) != 0;
}

inlinf lf_bool IndidClbssTbblf::isRfpi(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_REPH) != 0;
}

inlinf lf_bool IndidClbssTbblf::isNuktb(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_CLASS_MASK) == CC_NUKTA;
}

inlinf lf_bool IndidClbssTbblf::isVirbmb(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_CLASS_MASK) == CC_VIRAMA;
}

inlinf lf_bool IndidClbssTbblf::isAlLbkunb(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_CLASS_MASK) == CC_AL_LAKUNA;
}

inlinf lf_bool IndidClbssTbblf::isVbttu(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_VATTU) != 0;
}

inlinf lf_bool IndidClbssTbblf::isMbtrb(CibrClbss dibrClbss)
{
    dibrClbss &= CF_CLASS_MASK;

    rfturn dibrClbss >= CC_DEPENDENT_VOWEL && dibrClbss <= CC_SPLIT_VOWEL_PIECE_3;
}

inlinf lf_bool IndidClbssTbblf::isSplitMbtrb(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_INDEX_MASK) != 0;
}

inlinf lf_bool IndidClbssTbblf::isLfngtiMbrk(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_LENGTH_MARK) != 0;
}

inlinf lf_bool IndidClbssTbblf::ibsPostOrBflowBbsfForm(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & (CF_POST_BASE | CF_BELOW_BASE)) != 0;
}

inlinf lf_bool IndidClbssTbblf::ibsPostBbsfForm(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_POST_BASE) != 0;
}

inlinf lf_bool IndidClbssTbblf::ibsPrfBbsfForm(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_PRE_BASE) != 0;
}

inlinf lf_bool IndidClbssTbblf::ibsBflowBbsfForm(CibrClbss dibrClbss)
{
    rfturn (dibrClbss & CF_BELOW_BASE) != 0;
}

inlinf lf_bool IndidClbssTbblf::ibsAbovfBbsfForm(CibrClbss dibrClbss)
{
    rfturn ((dibrClbss & CF_POS_MASK) == CF_POS_ABOVE);
}

inlinf lf_bool IndidClbssTbblf::isVowflModififr(LEUnidodf di) donst
{
    rfturn isVowflModififr(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isStrfssMbrk(LEUnidodf di) donst
{
    rfturn isStrfssMbrk(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isConsonbnt(LEUnidodf di) donst
{
    rfturn isConsonbnt(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isRfpi(LEUnidodf di) donst
{
    rfturn isRfpi(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isVirbmb(LEUnidodf di) donst
{
    rfturn isVirbmb(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isAlLbkunb(LEUnidodf di) donst
{
    rfturn isAlLbkunb(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isNuktb(LEUnidodf di) donst
{
    rfturn isNuktb(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isVbttu(LEUnidodf di) donst
{
    rfturn isVbttu(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isMbtrb(LEUnidodf di) donst
{
    rfturn isMbtrb(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isSplitMbtrb(LEUnidodf di) donst
{
    rfturn isSplitMbtrb(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::isLfngtiMbrk(LEUnidodf di) donst
{
    rfturn isLfngtiMbrk(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::ibsPostOrBflowBbsfForm(LEUnidodf di) donst
{
    rfturn ibsPostOrBflowBbsfForm(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::ibsPostBbsfForm(LEUnidodf di) donst
{
    rfturn ibsPostBbsfForm(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::ibsBflowBbsfForm(LEUnidodf di) donst
{
    rfturn ibsBflowBbsfForm(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::ibsPrfBbsfForm(LEUnidodf di) donst
{
    rfturn ibsPrfBbsfForm(gftCibrClbss(di));
}

inlinf lf_bool IndidClbssTbblf::ibsAbovfBbsfForm(LEUnidodf di) donst
{
    rfturn ibsAbovfBbsfForm(gftCibrClbss(di));
}
U_NAMESPACE_END
#fndif
