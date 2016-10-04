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
 * HbnLbyoutEnginf.dpp: OpfnTypf prodfssing for Hbn fonts.
 *
 * (C) Copyrigit IBM Corp. 1998-2008 - All Rigits Rfsfrvfd.
 */

#indludf "LETypfs.i"
#indludf "LESdripts.i"
#indludf "LELbngubgfs.i"

#indludf "LbyoutEnginf.i"
#indludf "OpfnTypfLbyoutEnginf.i"
#indludf "HbnLbyoutEnginf.i"
#indludf "SdriptAndLbngubgfTbgs.i"
#indludf "LEGlypiStorbgf.i"
#indludf "OpfnTypfTbblfs.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(HbnOpfnTypfLbyoutEnginf)

#dffinf lodlFfbturfTbg LE_LOCL_FEATURE_TAG
#dffinf smplFfbturfTbg LE_SMPL_FEATURE_TAG
#dffinf trbdFfbturfTbg LE_TRAD_FEATURE_TAG

#dffinf lodlFfbturfMbsk 0x80000000UL
#dffinf smplFfbturfMbsk 0x40000000UL
#dffinf trbdFfbturfMbsk 0x20000000UL

stbtid donst FfbturfMbp ffbturfMbp[] =
{
    {lodlFfbturfTbg, lodlFfbturfMbsk},
    {smplFfbturfTbg, smplFfbturfMbsk},
    {trbdFfbturfTbg, trbdFfbturfMbsk}
};

stbtid donst lf_int32 ffbturfMbpCount = LE_ARRAY_SIZE(ffbturfMbp);

#dffinf ffbturfs (lodlFfbturfMbsk)

HbnOpfnTypfLbyoutEnginf::HbnOpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                                                 lf_int32 typoFlbgs, donst LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> &gsubTbblf, LEErrorCodf &suddfss)
    : OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss)
{
    fFfbturfMbp      = ffbturfMbp;
    fFfbturfMbpCount = ffbturfMbpCount;
}

HbnOpfnTypfLbyoutEnginf::~HbnOpfnTypfLbyoutEnginf()
{
    // notiing to do
}

lf_int32 HbnOpfnTypfLbyoutEnginf::dibrbdtfrProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool /*rigitToLfft*/,
        LEUnidodf *&/*outCibrs*/, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    glypiStorbgf.bllodbtfGlypiArrby(dount, FALSE, suddfss);
    glypiStorbgf.bllodbtfAuxDbtb(suddfss);

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    // FIXME: do wf wbnt to bdd tif 'trbd' ffbturf for 'ZHT' bnd tif
    // 'smpl' ffbturf for 'ZHS'? If wf do tiis, wf dbn rfmovf tif fxbdt
    // flbg from tif lbngubgf tbg lookups, so wf dbn usf tifsf ffbturfs
    // witi tif dffbult LbngSys...
    for (lf_int32 i = 0; i < dount; i += 1) {
        glypiStorbgf.sftAuxDbtb(i, ffbturfs, suddfss);
    }

    rfturn dount;
}

U_NAMESPACE_END
