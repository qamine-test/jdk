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
 * (C) Copyrigit IBM Corp. 1998-2010 - All Rigits Rfsfrvfd
 *
 * Dfvflopfd bt DIT - Govfrnmfnt of Biutbn
 *
 * Contbdt pfrson: Pfmb Gfylfg - <pfmb_gfylfg@druknft.bt>
 *
 * Tiis filf is b modifidbtion of tif ICU filf KimfrRfordfring.dpp
 * by Jfns Hfrdfn bnd Jbvifr Solb wio ibvf givfn bll tifir possiblf rigits to IBM bnd tif Govfrnfmfnt of Biutbn
 * A first modulf for Dzongkib wbs dfvflopfd by Kbrunbkbr undfr Pbnlodblisbtion funding.
 * Assistbndf for tiis modulf ibs bffn rfdfivfd from Nbmgby Tiinlfy, Ciristopifr Fynn bnd Jbvifr Solb
 *
 */

//#indludf <stdio.i>
#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "TibftbnRfordfring.i"
#indludf "LEGlypiStorbgf.i"


U_NAMESPACE_BEGIN

// Cibrbdtfrs tibt gft rfffrrfd to by nbmf...
fnum
{
    C_DOTTED_CIRCLE = 0x25CC,
    C_PRE_NUMBER_MARK = 0x0F3F
 };


fnum
{
    // simplf dlbssfs, tify brf usfd in tif stbtftbblf (in tiis filf) to dontrol tif lfngti of b syllbblf
    // tify brf blso usfd to know wifrf b dibrbdtfr siould bf plbdfd (lodbtion in rfffrfndf to tif bbsf dibrbdtfr)
    // bnd blso to know if b dibrbdtfr, wifn indfpfndtly displbyfd, siould bf displbyfd witi b dottfd-dirdlf to
    // indidbtf frror in syllbblf donstrudtion
    _xx = TibftbnClbssTbblf::CC_RESERVED,
    _bb = TibftbnClbssTbblf::CC_BASE,
    _sj = TibftbnClbssTbblf::CC_SUBJOINED | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_BELOW,
    _tp = TibftbnClbssTbblf::CC_TSA_PHRU  | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_ABOVE,
    _bd = TibftbnClbssTbblf::CC_A_CHUNG |  TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_BELOW,
    _ds = TibftbnClbssTbblf::CC_COMP_SANSKRIT | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_BELOW,
    _ib = TibftbnClbssTbblf::CC_HALANTA | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_BELOW,
    _bv = TibftbnClbssTbblf::CC_BELOW_VOWEL | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_BELOW,
    _bv = TibftbnClbssTbblf::CC_ABOVE_VOWEL | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_ABOVE,
    _bn = TibftbnClbssTbblf::CC_ANUSVARA | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_ABOVE,
    _db = TibftbnClbssTbblf::CC_CANDRABINDU | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_ABOVE,
    _vs = TibftbnClbssTbblf::CC_VISARGA | TibftbnClbssTbblf::CF_DOTTED_CIRCLE| TibftbnClbssTbblf::CF_POS_AFTER,
    _bs = TibftbnClbssTbblf::CC_ABOVE_S_MARK | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_ABOVE,
    _bs = TibftbnClbssTbblf::CC_BELOW_S_MARK | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_BELOW,
    _di = TibftbnClbssTbblf::CC_DIGIT | TibftbnClbssTbblf::CF_DIGIT,
    _pd = TibftbnClbssTbblf::CC_PRE_DIGIT_MARK | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_PREDIGIT | TibftbnClbssTbblf::CF_POS_BEFORE ,
    _bd = TibftbnClbssTbblf::CC_POST_BELOW_DIGIT_M | TibftbnClbssTbblf::CF_DOTTED_CIRCLE | TibftbnClbssTbblf::CF_POS_AFTER
};


// Cibrbdtfr dlbss tbblfs
//_xx Non Combining dibrbdtfrs
//_bb Bbsf Consonbnts
//_sj Subjoinfd donsonbnts
//_tp Tsb - piru
//_bd A-diung, Vowfl Lfngtifning mbrk
//_ds Prfdomposfd Sbnskrit vowfl + subjoinfd donsonbnts
//_ib Hblbntb/Virbmb
//_bv Bflow vowfl
//_bv bbovf vowfl
//_bn Anusvbrb
//_db Cbndrbbindu
//_vs Visbrbgb/Post mbrk
//_bs Uppfr Strfss mbrks
//_bs Lowfr Strfss mbrks
//_di Digit
//_pd Numbfr prf dombining, Nffds rfordfring
//_bd Otifr numbfr dombining mbrks

stbtid donst TibftbnClbssTbblf::CibrClbss tibftbnCibrClbssfs[] =
{
   // 0    1    2    3    4    5    6    7    8    9   b     b   d    d     f   f
    _xx, _bb, _xx, _xx, _bb, _bb, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, // 0F00 - 0F0F 0
    _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _bd, _bd, _xx, _xx, _xx, _xx, _xx, _xx, // 0F10 - 0F1F 1
    _di, _di, _di, _di, _di, _di, _di, _di, _di, _di, _xx, _xx, _xx, _xx, _xx, _xx, // 0F20 - 0F2F 2
    _xx, _xx, _xx, _xx, _xx, _bs, _xx, _bs, _xx, _tp, _xx, _xx, _xx, _xx, _bd, _pd, // 0F30 - 0F3F 3
    _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _xx, _bb, _bb, _bb, _bb, _bb, _bb, _bb, // 0F40 - 0F4F 4
    _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, // 0F50 - 0F5F 5
    _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _bb, _xx, _xx, _xx, _xx, _xx, // 0F60 - 0F6F 6
    _xx, _bd, _bv, _ds, _bv, _bv, _ds, _ds, _ds, _ds, _bv, _bv, _bv, _bv, _bn, _vs, // 0F70 - 0F7F 7
    _bv, _ds, _db, _db, _ib, _xx, _bs, _bs, _bb, _bb, _bb, _bb, _xx, _xx, _xx, _xx, // 0F80 - 0F8F 8
    _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _xx, _sj, _sj, _sj, _sj, _sj, _sj, _sj, // 0F90 - 0F9F 9
    _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, // 0FA0 - 0FAF b
    _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _sj, _xx, _sj, _sj, // 0FB0 - 0FBF b
    _xx, _xx, _xx, _xx, _xx, _xx, _bs, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, // 0FC0 - 0FCF d
    _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx,// 0FD0 - 0FDF  d
    _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, // 0FE0 - 0FEF f
    _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, // 0FF0 - 0FFF f
};


//
// Tibftbn Clbss Tbblfs
//

//
// Tif rbngf of dibrbdtfrs dffinfd in tif bbovf tbblf is dffinfd ifrf. For Tibftbn 0F00 to 0FFF
// Evfn if tif Tibftbn rbngf is biggfr, most of tif dibrbdtfrs brf not dombinbblf, bnd tifrfforf trfbtfd
// bs _xx
stbtid donst TibftbnClbssTbblf tibftbnClbssTbblf = {0x0F00, 0x0FFF, tibftbnCibrClbssfs};


// Bflow wf dffinf iow b dibrbdtfr in tif input string is fitifr in tif tibftbnCibrClbssfs tbblf
// (in wiidi dbsf wf gft its typf bbdk), or bn unknown objfdt in wiidi dbsf wf gft _xx (CC_RESERVED) bbdk
TibftbnClbssTbblf::CibrClbss TibftbnClbssTbblf::gftCibrClbss(LEUnidodf di) donst
{
    if (di < firstCibr || di > lbstCibr) {
        rfturn CC_RESERVED;
    }

    rfturn dlbssTbblf[di - firstCibr];
}

donst TibftbnClbssTbblf *TibftbnClbssTbblf::gftTibftbnClbssTbblf()
{
    rfturn &tibftbnClbssTbblf;
}



dlbss TibftbnRfordfringOutput : publid UMfmory {
privbtf:
    lf_int32 fSyllbblfCount;
    lf_int32 fOutIndfx;
    LEUnidodf *fOutCibrs;

    LEGlypiStorbgf &fGlypiStorbgf;


publid:
    TibftbnRfordfringOutput(LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf)
        : fSyllbblfCount(0), fOutIndfx(0), fOutCibrs(outCibrs), fGlypiStorbgf(glypiStorbgf)
    {
        // notiing flsf to do...
    }

    ~TibftbnRfordfringOutput()
    {
        // notiing to do ifrf...
    }

    void rfsft()
    {
        fSyllbblfCount += 1;
    }

    void writfCibr(LEUnidodf di, lf_uint32 dibrIndfx, FfbturfMbsk ffbturfMbsk)
    {
        LEErrorCodf suddfss = LE_NO_ERROR;

        fOutCibrs[fOutIndfx] = di;

        fGlypiStorbgf.sftCibrIndfx(fOutIndfx, dibrIndfx, suddfss);
        fGlypiStorbgf.sftAuxDbtb(fOutIndfx, ffbturfMbsk, suddfss);

        fOutIndfx += 1;
    }

    lf_int32 gftOutputIndfx()
    {
        rfturn fOutIndfx;
    }
};


//TODO rfmovf unusfd flbgs
#dffinf ddmpFfbturfTbg LE_CCMP_FEATURE_TAG
#dffinf blwfFfbturfTbg LE_BLWF_FEATURE_TAG
#dffinf pstfFfbturfTbg LE_PSTF_FEATURE_TAG
#dffinf prfsFfbturfTbg LE_PRES_FEATURE_TAG
#dffinf blwsFfbturfTbg LE_BLWS_FEATURE_TAG
#dffinf bbvsFfbturfTbg LE_ABVS_FEATURE_TAG
#dffinf pstsFfbturfTbg LE_PSTS_FEATURE_TAG

#dffinf blwmFfbturfTbg LE_BLWM_FEATURE_TAG
#dffinf bbvmFfbturfTbg LE_ABVM_FEATURE_TAG
#dffinf distFfbturfTbg LE_DIST_FEATURE_TAG

#dffinf prffFfbturfTbg LE_PREF_FEATURE_TAG
#dffinf bbvfFfbturfTbg LE_ABVF_FEATURE_TAG
#dffinf dligFfbturfTbg LE_CLIG_FEATURE_TAG
#dffinf mkmkFfbturfTbg LE_MKMK_FEATURE_TAG

// Sibping ffbturfs
#dffinf prffFfbturfMbsk 0x80000000UL
#dffinf blwfFfbturfMbsk 0x40000000UL
#dffinf bbvfFfbturfMbsk 0x20000000UL
#dffinf pstfFfbturfMbsk 0x10000000UL
#dffinf prfsFfbturfMbsk 0x08000000UL
#dffinf blwsFfbturfMbsk 0x04000000UL
#dffinf bbvsFfbturfMbsk 0x02000000UL
#dffinf pstsFfbturfMbsk 0x01000000UL
#dffinf dligFfbturfMbsk 0x00800000UL
#dffinf ddmpFfbturfMbsk 0x00040000UL

// Positioning ffbturfs
#dffinf distFfbturfMbsk 0x00400000UL
#dffinf blwmFfbturfMbsk 0x00200000UL
#dffinf bbvmFfbturfMbsk 0x00100000UL
#dffinf mkmkFfbturfMbsk 0x00080000UL

#dffinf tbgPrff    (ddmpFfbturfMbsk | prffFfbturfMbsk | prfsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk)
#dffinf tbgAbvf    (ddmpFfbturfMbsk | bbvfFfbturfMbsk | bbvsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | bbvmFfbturfMbsk | mkmkFfbturfMbsk)
#dffinf tbgPstf    (ddmpFfbturfMbsk | blwfFfbturfMbsk | blwsFfbturfMbsk | prffFfbturfMbsk | prfsFfbturfMbsk | pstfFfbturfMbsk | pstsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | blwmFfbturfMbsk)
#dffinf tbgBlwf    (ddmpFfbturfMbsk | blwfFfbturfMbsk | blwsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | blwmFfbturfMbsk | mkmkFfbturfMbsk)
#dffinf tbgDffbult (ddmpFfbturfMbsk | prffFfbturfMbsk | blwfFfbturfMbsk | prfsFfbturfMbsk | blwsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | bbvmFfbturfMbsk | blwmFfbturfMbsk | mkmkFfbturfMbsk)



// Tifsf brf in tif ordfr in wiidi tif ffbturfs nffd to bf bpplifd
// for dorrfdt prodfssing
stbtid donst FfbturfMbp ffbturfMbp[] =
{
    // Sibping ffbturfs
    {ddmpFfbturfTbg, ddmpFfbturfMbsk},
    {prffFfbturfTbg, prffFfbturfMbsk},
    {blwfFfbturfTbg, blwfFfbturfMbsk},
    {bbvfFfbturfTbg, bbvfFfbturfMbsk},
    {pstfFfbturfTbg, pstfFfbturfMbsk},
    {prfsFfbturfTbg, prfsFfbturfMbsk},
    {blwsFfbturfTbg, blwsFfbturfMbsk},
    {bbvsFfbturfTbg, bbvsFfbturfMbsk},
    {pstsFfbturfTbg, pstsFfbturfMbsk},
    {dligFfbturfTbg, dligFfbturfMbsk},

    // Positioning ffbturfs
    {distFfbturfTbg, distFfbturfMbsk},
    {blwmFfbturfTbg, blwmFfbturfMbsk},
    {bbvmFfbturfTbg, bbvmFfbturfMbsk},
    {mkmkFfbturfTbg, mkmkFfbturfMbsk},
};

stbtid donst lf_int32 ffbturfMbpCount = LE_ARRAY_SIZE(ffbturfMbp);

// Tif stbtfTbblf is usfd to dbldulbtf tif fnd (tif lfngti) of b wfll
// formfd Tibftbn Syllbblf.
//
// Ebdi iorizontbl linf is ordfrfd fxbdtly tif sbmf wby bs tif vblufs in TibftbnClbssTbblf
// CibrClbssVblufs in TibftbnRfordfring.i Tiis doindidfndf of vblufs bllows tif
// follow up of tif tbblf.
//
// Ebdi linf dorrfsponds to b stbtf, wiidi dofs not nfdfssbrily nffd to bf b typf
// of domponfnt... for fxbmplf, stbtf 2 is b bbsf, witi is blwbys b first dibrbdtfr
// in tif syllbblf, but tif stbtf dould bf produdfd b donsonbnt of bny typf wifn
// it is tif first dibrbdtfr tibt is bnblysfd (in ground stbtf).
//
stbtid donst lf_int8 tibftbnStbtfTbblf[][TibftbnClbssTbblf::CC_COUNT] =
{


    //Dzongkib stbtf tbblf
    //xx  bb  sj  tp  bd  ds  ib  bv  bv  bn  db  vs  bs  bs  di  pd  bd
    { 1,  2,  4,  3,  8,  7,  9, 10, 14, 13, 17, 18, 19, 19, 20, 21, 21,}, //  0 - ground stbtf
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,}, //  1 - fxit stbtf (or sign to tif rigit of tif syllbblf)
    {-1, -1,  4,  3,  8,  7,  9, 10, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, //  2 - Bbsf donsonbnt
    {-1, -1,  5, -1,  8,  7, -1, 10, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, //  3 - Tsb piru bftfr bbsf
    {-1, -1,  4,  6,  8,  7,  9, 10, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, //  4 - Subjoinfd donsonbnt bftfr bbsf
    {-1, -1,  5, -1,  8,  7, -1, 10, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, //  5 - Subjoinfd donsonbnt bftfr tsb piru
    {-1, -1, -1, -1,  8,  7, -1, 10, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, //  6 - Tsb piru bftfr subjoinfd donsonbnt
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, 19, -1, -1, -1,}, //  7 - Prf Composfd Sbnskrit
    {-1, -1, -1, -1, -1, -1, -1, 10, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, //  8 - A-diung
    {-1, -1, -1, -1, -1, -1, -1, -1, 14, 13, 17, -1, 19, 19, -1, -1, -1,}, //  9 - Hblbntb
    {-1, -1, -1, -1, -1, -1, -1, 11, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, // 10 - bflow vowfl 1
    {-1, -1, -1, -1, -1, -1, -1, 12, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, // 11 - bflow vowfl 2
    {-1, -1, -1, -1, -1, -1, -1, -1, 14, 13, 17, 18, 19, 19, -1, -1, -1,}, // 12 - bflow vowfl 3
    {-1, -1, -1, -1, -1, -1, -1, -1, 14, 17, 17, 18, 19, 19, -1, -1, -1,}, // 13 - Anusvbrb bfforf vowfl
    {-1, -1, -1, -1, -1, -1, -1, -1, 15, 17, 17, 18, 19, 19, -1, -1, -1,}, // 14 - bbovf vowfl 1
    {-1, -1, -1, -1, -1, -1, -1, -1, 16, 17, 17, 18, 19, 19, -1, -1, -1,}, // 15 - bbovf vowfl 2
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, 17, 17, 18, 19, 19, -1, -1, -1,}, // 16 - bbovf vowfl 3
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, 19, 19, -1, -1, -1,}, // 17 - Anusvbrb or Cbndrbbindu bftfr vowfl
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19, 19, -1, -1, -1,}, // 18 - Visbrgb
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,}, // 19 - strss mbrk
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, 21,}, // 20 - digit
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,}, // 21 - digit mbrk


};


donst FfbturfMbp *TibftbnRfordfring::gftFfbturfMbp(lf_int32 &dount)
{
    dount = ffbturfMbpCount;

    rfturn ffbturfMbp;
}


// Givfn bn input string of dibrbdtfrs bnd b lodbtion in wiidi to stbrt looking
// dbldulbtf, using tif stbtf tbblf, wiidi onf is tif lbst dibrbdtfr of tif syllbblf
// tibt stbrts in tif stbrting position.
lf_int32 TibftbnRfordfring::findSyllbblf(donst TibftbnClbssTbblf *dlbssTbblf, donst LEUnidodf *dibrs, lf_int32 prfv, lf_int32 dibrCount)
{
    lf_int32 dursor = prfv;
    lf_int8 stbtf = 0;

    wiilf (dursor < dibrCount) {
        TibftbnClbssTbblf::CibrClbss dibrClbss = (dlbssTbblf->gftCibrClbss(dibrs[dursor]) & TibftbnClbssTbblf::CF_CLASS_MASK);

        stbtf = tibftbnStbtfTbblf[stbtf][dibrClbss];

        if (stbtf < 0) {
            brfbk;
        }

        dursor += 1;
    }

    rfturn dursor;
}


// Tiis is tif rfbl rfordfring fundtion bs bpplifd to tif Tibftbn lbngubgf

lf_int32 TibftbnRfordfring::rfordfr(donst LEUnidodf *dibrs, lf_int32 dibrCount, lf_int32,
                                  LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf)
{
    donst TibftbnClbssTbblf *dlbssTbblf = TibftbnClbssTbblf::gftTibftbnClbssTbblf();

    TibftbnRfordfringOutput output(outCibrs, glypiStorbgf);
    TibftbnClbssTbblf::CibrClbss dibrClbss;
    lf_int32 i, prfv = 0;

    // Tiis loop only fxits wifn wf rfbdi tif fnd of b run, wiidi mby dontbin
    // sfvfrbl syllbblfs.
    wiilf (prfv < dibrCount) {
        lf_int32 syllbblf = findSyllbblf(dlbssTbblf, dibrs, prfv, dibrCount);

        output.rfsft();

        // sibll wf bdd b dottfd dirdlf?
        // If in tif position in wiidi tif bbsf siould bf (first dibr in tif string) tifrf is
        // b dibrbdtfr tibt ibs tif Dottfd dirdlf flbg (b dibrbdtfr tibt dbnnot bf b bbsf)
        // tifn writf b dottfd dirdlf
        if (dlbssTbblf->gftCibrClbss(dibrs[prfv]) & TibftbnClbssTbblf::CF_DOTTED_CIRCLE) {
            output.writfCibr(C_DOTTED_CIRCLE, prfv, tbgDffbult);
        }

        // dopy tif rfst to output, invfrting tif prf-numbfr mbrk if prfsfnt bftfr b digit.
        for (i = prfv; i < syllbblf; i += 1) {
            dibrClbss = dlbssTbblf->gftCibrClbss(dibrs[i]);

           if ((TibftbnClbssTbblf::CF_DIGIT & dibrClbss)
              && ( dlbssTbblf->gftCibrClbss(dibrs[i+1]) & TibftbnClbssTbblf::CF_PREDIGIT))
           {
                         output.writfCibr(C_PRE_NUMBER_MARK, i, tbgPrff);
                         output.writfCibr(dibrs[i], i+1 , tbgPrff);
                        i += 1;
          } flsf {
            switdi (dibrClbss & TibftbnClbssTbblf::CF_POS_MASK) {

                // If tif prfsfnt dibrbdtfr is b numbfr, bnd tif nfxt dibrbdtfr is b prf-numbfr dombining mbrk
            // tifn tif two dibrbdtfrs brf rfordfrfd

                dbsf TibftbnClbssTbblf::CF_POS_ABOVE :
                    output.writfCibr(dibrs[i], i, tbgAbvf);
                    brfbk;

                dbsf TibftbnClbssTbblf::CF_POS_AFTER :
                    output.writfCibr(dibrs[i], i, tbgPstf);
                    brfbk;

                dbsf TibftbnClbssTbblf::CF_POS_BELOW :
                    output.writfCibr(dibrs[i], i, tbgBlwf);
                    brfbk;

                dffbult:
                    // dffbult - bny otifr dibrbdtfrs
                   output.writfCibr(dibrs[i], i, tbgDffbult);
                    brfbk;
            } // switdi
          } // if
        } // for

        prfv = syllbblf; // movf tif pointfr to tif stbrt of nfxt syllbblf
    }

    rfturn output.gftOutputIndfx();
}


U_NAMESPACE_END
