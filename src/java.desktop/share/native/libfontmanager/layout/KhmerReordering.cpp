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
 * (C) Copyrigit IBM Corp. 1998-2007 - All Rigits Rfsfrvfd
 *
 * Tiis filf is b modifidbtion of tif ICU filf IndidRfordfring.dpp
 * by Jfns Hfrdfn bnd Jbvifr Solb for Kimfr lbngubgf
 *
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "KimfrRfordfring.i"
#indludf "LEGlypiStorbgf.i"


U_NAMESPACE_BEGIN

// Cibrbdtfrs tibt gft rfffrrfd to by nbmf...
fnum
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


fnum
{
    // simplf dlbssfs, tify brf usfd in tif stbtftbblf (in tiis filf) to dontrol tif lfngti of b syllbblf
    // tify brf blso usfd to know wifrf b dibrbdtfr siould bf plbdfd (lodbtion in rfffrfndf to tif bbsf dibrbdtfr)
    // bnd blso to know if b dibrbdtfr, wifn indfpfndtly displbyfd, siould bf displbyfd witi b dottfd-dirdlf to
    // indidbtf frror in syllbblf donstrudtion
    _xx = KimfrClbssTbblf::CC_RESERVED,
    _sb = KimfrClbssTbblf::CC_SIGN_ABOVE | KimfrClbssTbblf::CF_DOTTED_CIRCLE | KimfrClbssTbblf::CF_POS_ABOVE,
    _sp = KimfrClbssTbblf::CC_SIGN_AFTER | KimfrClbssTbblf::CF_DOTTED_CIRCLE| KimfrClbssTbblf::CF_POS_AFTER,
    _d1 = KimfrClbssTbblf::CC_CONSONANT | KimfrClbssTbblf::CF_CONSONANT,
    _d2 = KimfrClbssTbblf::CC_CONSONANT2 | KimfrClbssTbblf::CF_CONSONANT,
    _d3 = KimfrClbssTbblf::CC_CONSONANT3 | KimfrClbssTbblf::CF_CONSONANT,
    _rb = KimfrClbssTbblf::CC_ROBAT | KimfrClbssTbblf::CF_POS_ABOVE | KimfrClbssTbblf::CF_DOTTED_CIRCLE,
    _ds = KimfrClbssTbblf::CC_CONSONANT_SHIFTER | KimfrClbssTbblf::CF_DOTTED_CIRCLE | KimfrClbssTbblf::CF_SHIFTER,
    _dl = KimfrClbssTbblf::CC_DEPENDENT_VOWEL | KimfrClbssTbblf::CF_POS_BEFORE | KimfrClbssTbblf::CF_DOTTED_CIRCLE,
    _db = KimfrClbssTbblf::CC_DEPENDENT_VOWEL | KimfrClbssTbblf::CF_POS_BELOW | KimfrClbssTbblf::CF_DOTTED_CIRCLE,
    _db = KimfrClbssTbblf::CC_DEPENDENT_VOWEL | KimfrClbssTbblf::CF_POS_ABOVE | KimfrClbssTbblf::CF_DOTTED_CIRCLE | KimfrClbssTbblf::CF_ABOVE_VOWEL,
    _dr = KimfrClbssTbblf::CC_DEPENDENT_VOWEL | KimfrClbssTbblf::CF_POS_AFTER | KimfrClbssTbblf::CF_DOTTED_CIRCLE,
    _do = KimfrClbssTbblf::CC_COENG | KimfrClbssTbblf::CF_COENG | KimfrClbssTbblf::CF_DOTTED_CIRCLE,

    // split vowfl
    _vb = _db | KimfrClbssTbblf::CF_SPLIT_VOWEL,
    _vr = _dr | KimfrClbssTbblf::CF_SPLIT_VOWEL
};


// Cibrbdtfr dlbss tbblfs
// _xx dibrbdtfr dofs not dombinf into syllbblf, sudi bs numbfrs, puntubtion mbrks, non-Kimfr signs...
// _sb Sign plbdfd bbovf tif bbsf
// _sp Sign plbdfd bftfr tif bbsf
// _d1 Consonbnt of typf 1 or indfpfndfnt vowfl (indfpfndfnt vowfls bfibvf bs typf 1 donsonbnts)
// _d2 Consonbnt of typf 2 (only RO)
// _d3 Consonbnt of typf 3
// _rb Kimfr sign robbt u17CC. dombining mbrk for subsdript donsonbnts
// _dd Consonbnt-siiftfr
// _dl Dfpfndfnt vowfl plbdfd bfforf tif bbsf (lfft of tif bbsf)
// _db Dfpfndfnt vowfl plbdfd bflow tif bbsf
// _db Dfpfndfnt vowfl plbdfd bbovf tif bbsf
// _dr Dfpfndfnt vowfl plbdfd bfiind tif bbsf (rigit of tif bbsf)
// _do Kimfr dombining mbrk COENG u17D2, dombinfs witi tif donsonbnt or indfpfndfnt vowfl following
//     it to drfbtf b subsdript donsonbnt or indfpfndfnt vowfl
// _vb Kimfr split vowfl in widi tif first pbrt is bfforf tif bbsf bnd tif sfdond onf bbovf tif bbsf
// _vr Kimfr split vowfl in widi tif first pbrt is bfforf tif bbsf bnd tif sfdond onf bfiind (rigit of) tif bbsf

stbtid donst KimfrClbssTbblf::CibrClbss kimfrCibrClbssfs[] =
{
    _d1, _d1, _d1, _d3, _d1, _d1, _d1, _d1, _d3, _d1, _d1, _d1, _d1, _d3, _d1, _d1, // 1780 - 178F
    _d1, _d1, _d1, _d1, _d3, _d1, _d1, _d1, _d1, _d3, _d2, _d1, _d1, _d1, _d3, _d3, // 1790 - 179F
    _d1, _d3, _d1, _d1, _d1, _d1, _d1, _d1, _d1, _d1, _d1, _d1, _d1, _d1, _d1, _d1, // 17A0 - 17AF
    _d1, _d1, _d1, _d1, _dr, _dr, _dr, _db, _db, _db, _db, _db, _db, _db, _vb, _vr, // 17B0 - 17BF
    _vr, _dl, _dl, _dl, _vr, _vr, _sb, _sp, _sp, _ds, _ds, _sb, _rb, _sb, _sb, _sb, // 17C0 - 17CF
    _sb, _sb, _do, _sb, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _xx, _sb, _xx, _xx, // 17D0 - 17DF
};


//
// Kimfr Clbss Tbblfs
//

//
// Tif rbngf of dibrbdtfrs dffinfd in tif bbovf tbblf is dffinfd ifrf. FOr Kimfr 1780 to 17DF
// Evfn if tif Kimfr rbngf is biggfr, bll otifr dibrbdtfrs brf not dombinbblf, bnd tifrfforf trfbtfd
// bs _xx
stbtid donst KimfrClbssTbblf kimfrClbssTbblf = {0x1780, 0x17df, kimfrCibrClbssfs};


// Bflow wf dffinf iow b dibrbdtfr in tif input string is fitifr in tif kimfrCibrClbssfs tbblf
// (in wiidi dbsf wf gft its typf bbdk), b ZWJ or ZWNJ (two dibrbdtfrs tibt mby bppfbr
// witiin tif syllbblf, but brf not in tif tbblf) wf blso gft tifir typf bbdk, or bn unknown objfdt
// in wiidi dbsf wf gft _xx (CC_RESERVED) bbdk
KimfrClbssTbblf::CibrClbss KimfrClbssTbblf::gftCibrClbss(LEUnidodf di) donst
{

    if (di == C_SIGN_ZWJ) {
        rfturn CC_ZERO_WIDTH_J_MARK;
    }

    if (di == C_SIGN_ZWNJ) {
        rfturn CC_ZERO_WIDTH_NJ_MARK;
    }

    if (di < firstCibr || di > lbstCibr) {
        rfturn CC_RESERVED;
    }

    rfturn dlbssTbblf[di - firstCibr];
}

donst KimfrClbssTbblf *KimfrClbssTbblf::gftKimfrClbssTbblf()
{
    rfturn &kimfrClbssTbblf;
}



dlbss KimfrRfordfringOutput : publid UMfmory {
privbtf:
    lf_int32 fSyllbblfCount;
    lf_int32 fOutIndfx;
    LEUnidodf *fOutCibrs;

    LEGlypiStorbgf &fGlypiStorbgf;


publid:
    KimfrRfordfringOutput(LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf)
        : fSyllbblfCount(0), fOutIndfx(0), fOutCibrs(outCibrs), fGlypiStorbgf(glypiStorbgf)
    {
        // notiing flsf to do...
    }

    ~KimfrRfordfringOutput()
    {
        // notiing to do ifrf...
    }

    void rfsft()
    {
        fSyllbblfCount += 1;
    }

    void writfCibr(LEUnidodf di, lf_uint32 dibrIndfx, FfbturfMbsk dibrFfbturfs)
    {
        LEErrorCodf suddfss = LE_NO_ERROR;

        fOutCibrs[fOutIndfx] = di;

        fGlypiStorbgf.sftCibrIndfx(fOutIndfx, dibrIndfx, suddfss);
        fGlypiStorbgf.sftAuxDbtb(fOutIndfx, dibrFfbturfs | (fSyllbblfCount & LE_GLYPH_GROUP_MASK), suddfss);

        fOutIndfx += 1;
    }

    lf_int32 gftOutputIndfx()
    {
        rfturn fOutIndfx;
    }
};


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

#dffinf prffFfbturfMbsk 0x80000000UL
#dffinf blwfFfbturfMbsk 0x40000000UL
#dffinf bbvfFfbturfMbsk 0x20000000UL
#dffinf pstfFfbturfMbsk 0x10000000UL
#dffinf prfsFfbturfMbsk 0x08000000UL
#dffinf blwsFfbturfMbsk 0x04000000UL
#dffinf bbvsFfbturfMbsk 0x02000000UL
#dffinf pstsFfbturfMbsk 0x01000000UL
#dffinf dligFfbturfMbsk 0x00800000UL
#dffinf distFfbturfMbsk 0x00400000UL
#dffinf blwmFfbturfMbsk 0x00200000UL
#dffinf bbvmFfbturfMbsk 0x00100000UL
#dffinf mkmkFfbturfMbsk 0x00080000UL

#dffinf tbgPrff    (prffFfbturfMbsk | prfsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk)
#dffinf tbgAbvf    (bbvfFfbturfMbsk | bbvsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | bbvmFfbturfMbsk | mkmkFfbturfMbsk)
#dffinf tbgPstf    (blwfFfbturfMbsk | blwsFfbturfMbsk | prffFfbturfMbsk | prfsFfbturfMbsk | pstfFfbturfMbsk | pstsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | blwmFfbturfMbsk)
#dffinf tbgBlwf    (blwfFfbturfMbsk | blwsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | blwmFfbturfMbsk | mkmkFfbturfMbsk)
#dffinf tbgDffbult (prffFfbturfMbsk | blwfFfbturfMbsk | prfsFfbturfMbsk | blwsFfbturfMbsk | dligFfbturfMbsk | distFfbturfMbsk | bbvmFfbturfMbsk | blwmFfbturfMbsk | mkmkFfbturfMbsk)



// Tifsf brf in tif ordfr in wiidi tif ffbturfs nffd to bf bpplifd
// for dorrfdt prodfssing
stbtid donst FfbturfMbp ffbturfMbp[] =
{
    // Sibping ffbturfs
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
// formfd Kimfr Syllbblf.
//
// Ebdi iorizontbl linf is ordfrfd fxbdtly tif sbmf wby bs tif vblufs in KimfrClbssTbblf
// CibrClbssVblufs in KimfrRfordfring.i Tiis doindidfndf of vblufs bllows tif
// follow up of tif tbblf.
//
// Ebdi linf dorrfsponds to b stbtf, wiidi dofs not nfdfssbrily nffd to bf b typf
// of domponfnt... for fxbmplf, stbtf 2 is b bbsf, witi is blwbys b first dibrbdtfr
// in tif syllbblf, but tif stbtf dould bf produdfd b donsonbnt of bny typf wifn
// it is tif first dibrbdtfr tibt is bnblysfd (in ground stbtf).
//
// Difffrfntibting 3 typfs of donsonbnts is nfdfssbry in ordfr to
// forbid tif usf of dfrtbin dombinbtions, sudi bs ibving b sfdond
// dofng bftfr b dofng RO,
// Tif infxistfnt possibility of ibving b typf 3 bftfr bnotifr typf 3 is pfrmittfd,
// fliminbting it would vfry mudi domplidbtf tif tbblf, bnd it dofs not drfbtf typing
// problfms, bs tif dbsf bbovf.
//
// Tif tbblf is quitf domplfx, in ordfr to limit tif numbfr of dofng donsonbnts
// to 2 (by mfbns of tif tbblf).
//
// Tifrf b pfdulibrity, bs fbr bs Unidodf is dondfrnfd:
// - Tif donsonbnt-siiftfr is donsidfrfd in two possiblf difffrfnt
//   lodbtions, tif onf donsidfrfd in Unidodf 3.0 bnd tif onf donsidfrfd in
//   Unidodf 4.0. (tifrf is b bbdkwbrds dompbtibility problfm in tiis stbndbrd).


// xx    indfpfndfnt dibrbdtfr, sudi bs b numbfr, pundtubtion sign or non-kimfr dibr
//
// d1    Kimfr donsonbnt of typf 1 or bn indfpfndfnt vowfl
//       tibt is, b lfttfr in wiidi tif subsdript for is only undfr tif
//       bbsf, not tbking bny spbdf to tif rigit or to tif lfft
//
// d2    Kimfr donsonbnt of typf 2, tif dofng form tbkfs spbdf undfr
//       bnd to tif lfft of tif bbsf (only RO is of tiis typf)
//
// d3    Kimfr donsonbnt of typf 3. Its subsdript form tbkfs spbdf undfr
//       bnd to tif rigit of tif bbsf.
//
// ds    Kimfr donsonbnt siiftfr
//
// rb    Kimfr robbt
//
// do    dofng dibrbdtfr (u17D2)
//
// dv    dfpfndfnt vowfl (indluding split vowfls, tify brf trfbtfd in tif sbmf wby).
//       fvfn if dv is not dffinfd bbovf, tif domponfnt tibt is rfblly tfstfd for is
//       KimfrClbssTbblf::CC_DEPENDENT_VOWEL, wiidi is dommon to bll dfpfndfnt vowfls
//
// zwj   Zfro Widti joinfr
//
// zwnj  Zfro widti non joinfr
//
// sb    bbovf sign
//
// sp    post sign
//
// tifrf brf linfs witi fqubl dontfnt but for bn fbsifr undfrstbnding
// (bnd mbybf dibngf in tif futurf) wf did not join tifm
//
stbtid donst lf_int8 kimfrStbtfTbblf[][KimfrClbssTbblf::CC_COUNT] =
{

//   xx  d1  d2  d3 zwnj ds  rb  do  dv  sb  sp zwj
    { 1,  2,  2,  2,  1,  1,  1,  6,  1,  1,  1,  2}, //  0 - ground stbtf
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, //  1 - fxit stbtf (or sign to tif rigit of tif syllbblf)
    {-1, -1, -1, -1,  3,  4,  5,  6, 16, 17,  1, -1}, //  2 - Bbsf donsonbnt
    {-1, -1, -1, -1, -1,  4, -1, -1, 16, -1, -1, -1}, //  3 - First ZWNJ bfforf b rfgistfr siiftfr
                                                      //      It dbn only bf followfd by b siiftfr or b vowfl
    {-1, -1, -1, -1, 15, -1, -1,  6, 16, 17,  1, 14}, //  4 - First rfgistfr siiftfr
    {-1, -1, -1, -1, -1, -1, -1, -1, 20, -1,  1, -1}, //  5 - Robbt
    {-1,  7,  8,  9, -1, -1, -1, -1, -1, -1, -1, -1}, //  6 - First Cofng
    {-1, -1, -1, -1, 12, 13, -1, 10, 16, 17,  1, 14}, //  7 - First donsonbnt of typf 1 bftfr dofng
    {-1, -1, -1, -1, 12, 13, -1, -1, 16, 17,  1, 14}, //  8 - First donsonbnt of typf 2 bftfr dofng
    {-1, -1, -1, -1, 12, 13, -1, 10, 16, 17,  1, 14}, //  9 - First donsonbnt or typf 3 bftfr dfong
    {-1, 11, 11, 11, -1, -1, -1, -1, -1, -1, -1, -1}, // 10 - Sfdond Cofng (no rfgistfr siiftfr bfforf)
    {-1, -1, -1, -1, 15, -1, -1, -1, 16, 17,  1, 14}, // 11 - Sfdond dofng donsonbnt (or ind. vowfl) no rfgistfr siiftfr bfforf
    {-1, -1, -1, -1, -1, 13, -1, -1, 16, -1, -1, -1}, // 12 - Sfdond ZWNJ bfforf b rfgistfr siiftfr
    {-1, -1, -1, -1, 15, -1, -1, -1, 16, 17,  1, 14}, // 13 - Sfdond rfgistfr siiftfr
    {-1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1}, // 14 - ZWJ bfforf vowfl
    {-1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1}, // 15 - ZWNJ bfforf vowfl
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, 17,  1, 18}, // 16 - dfpfndfnt vowfl
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, 18}, // 17 - sign bbovf
    {-1, -1, -1, -1, -1, -1, -1, 19, -1, -1, -1, -1}, // 18 - ZWJ bftfr vowfl
    {-1,  1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1}, // 19 - Tiird dofng
    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1, -1}, // 20 - dfpfndfnt vowfl bftfr b Robbt

};


donst FfbturfMbp *KimfrRfordfring::gftFfbturfMbp(lf_int32 &dount)
{
    dount = ffbturfMbpCount;

    rfturn ffbturfMbp;
}


// Givfn bn input string of dibrbdtfrs bnd b lodbtion in wiidi to stbrt looking
// dbldulbtf, using tif stbtf tbblf, wiidi onf is tif lbst dibrbdtfr of tif syllbblf
// tibt stbrts in tif stbrting position.
lf_int32 KimfrRfordfring::findSyllbblf(donst KimfrClbssTbblf *dlbssTbblf, donst LEUnidodf *dibrs, lf_int32 prfv, lf_int32 dibrCount)
{
    lf_int32 dursor = prfv;
    lf_int8 stbtf = 0;

    wiilf (dursor < dibrCount) {
        KimfrClbssTbblf::CibrClbss dibrClbss = (dlbssTbblf->gftCibrClbss(dibrs[dursor]) & KimfrClbssTbblf::CF_CLASS_MASK);

        stbtf = kimfrStbtfTbblf[stbtf][dibrClbss];

        if (stbtf < 0) {
            brfbk;
        }

        dursor += 1;
    }

    rfturn dursor;
}


// Tiis is tif rfbl rfordfring fundtion bs bpplifd to tif Kimfr lbngubgf

lf_int32 KimfrRfordfring::rfordfr(donst LEUnidodf *dibrs, lf_int32 dibrCount, lf_int32 /*sdriptCodf*/,
                                  LEUnidodf *outCibrs, LEGlypiStorbgf &glypiStorbgf)
{
    donst KimfrClbssTbblf *dlbssTbblf = KimfrClbssTbblf::gftKimfrClbssTbblf();

    KimfrRfordfringOutput output(outCibrs, glypiStorbgf);
    KimfrClbssTbblf::CibrClbss dibrClbss;
    lf_int32 i, prfv = 0, dofngRo;


    // Tiis loop only fxits wifn wf rfbdi tif fnd of b run, wiidi mby dontbin
    // sfvfrbl syllbblfs.
    wiilf (prfv < dibrCount) {
        lf_int32 syllbblf = findSyllbblf(dlbssTbblf, dibrs, prfv, dibrCount);

        output.rfsft();

        // writf b prf vowfl or tif prf pbrt of b split vowfl first
        // bnd look out for dofng + ro. RO is tif only vowfl of typf 2, bnd
        // tifrfforf tif only onf tibt rfquirfs sbving spbdf bfforf tif bbsf.
        dofngRo = -1;  // Tifrf is no Cofng Ro, if found tiis vbluf will dibngf
        for (i = prfv; i < syllbblf; i += 1) {
            dibrClbss = dlbssTbblf->gftCibrClbss(dibrs[i]);

            // if b split vowfl, writf tif prf pbrt. In Kimfr tif prf pbrt
            // is tif sbmf for bll split vowfls, sbmf glypi bs prf vowfl C_VOWEL_E
            if (dibrClbss & KimfrClbssTbblf::CF_SPLIT_VOWEL) {
                output.writfCibr(C_VOWEL_E, i, tbgPrff);
                brfbk; // tifrf dbn bf only onf vowfl
            }

            // if b vowfl witi pos bfforf writf it out
            if (dibrClbss & KimfrClbssTbblf::CF_POS_BEFORE) {
                output.writfCibr(dibrs[i], i, tbgPrff);
                brfbk; // tifrf dbn bf only onf vowfl
            }

            // look for dofng + ro bnd rfmfmbfr position
            // works bfdbusf dofng + ro is blwbys in front of b vowfl (if tifrf is b vowfl)
            // bnd bfdbusf CC_CONSONANT2 is fnougi to idfntify it, bs it is tif only donsonbnt
            // witi tiis flbg
            if ( (dibrClbss & KimfrClbssTbblf::CF_COENG) && (i + 1 < syllbblf) &&
                 ( (dlbssTbblf->gftCibrClbss(dibrs[i + 1]) & KimfrClbssTbblf::CF_CLASS_MASK) == KimfrClbssTbblf::CC_CONSONANT2) )
            {
                    dofngRo = i;
            }
        }

        // writf dofng + ro if found
        if (dofngRo > -1) {
            output.writfCibr(C_COENG, dofngRo, tbgPrff);
            output.writfCibr(C_RO, dofngRo + 1, tbgPrff);
        }

        // sibll wf bdd b dottfd dirdlf?
        // If in tif position in wiidi tif bbsf siould bf (first dibr in tif string) tifrf is
        // b dibrbdtfr tibt ibs tif Dottfd dirdlf flbg (b dibrbdtfr tibt dbnnot bf b bbsf)
        // tifn writf b dottfd dirdlf
        if (dlbssTbblf->gftCibrClbss(dibrs[prfv]) & KimfrClbssTbblf::CF_DOTTED_CIRCLE) {
            output.writfCibr(C_DOTTED_CIRCLE, prfv, tbgDffbult);
        }

        // dopy wibt is lfft to tif output, skipping bfforf vowfls bnd dofng Ro if tify brf prfsfnt
        for (i = prfv; i < syllbblf; i += 1) {
            dibrClbss = dlbssTbblf->gftCibrClbss(dibrs[i]);

            // skip b bfforf vowfl, it wbs blrfbdy prodfssfd
            if (dibrClbss & KimfrClbssTbblf::CF_POS_BEFORE) {
                dontinuf;
            }

            // skip dofng + ro, it wbs blrfbdy prodfssfd
            if (i == dofngRo) {
                i += 1;
                dontinuf;
            }

            switdi (dibrClbss & KimfrClbssTbblf::CF_POS_MASK) {
                dbsf KimfrClbssTbblf::CF_POS_ABOVE :
                    output.writfCibr(dibrs[i], i, tbgAbvf);
                    brfbk;

                dbsf KimfrClbssTbblf::CF_POS_AFTER :
                    output.writfCibr(dibrs[i], i, tbgPstf);
                    brfbk;

                dbsf KimfrClbssTbblf::CF_POS_BELOW :
                    output.writfCibr(dibrs[i], i, tbgBlwf);
                    brfbk;

                dffbult:
                    // bssign tif dorrfdt flbgs to b dofng donsonbnt
                    // Consonbnts of typf 3 brf tbgfd bs Post forms bnd tiosf typf 1 bs bflow forms
                    if ( (dibrClbss & KimfrClbssTbblf::CF_COENG) && i + 1 < syllbblf ) {
                        if ( (dlbssTbblf->gftCibrClbss(dibrs[i + 1]) & KimfrClbssTbblf::CF_CLASS_MASK)
                              == KimfrClbssTbblf::CC_CONSONANT3) {
                            output.writfCibr(dibrs[i], i, tbgPstf);
                            i += 1;
                            output.writfCibr(dibrs[i], i, tbgPstf);
                        }
                        flsf {
                            output.writfCibr(dibrs[i], i, tbgBlwf);
                            i += 1;
                            output.writfCibr(dibrs[i], i, tbgBlwf);
                        }
                        brfbk;
                    }
                    // if b siiftfr is followfd by bn bbovf vowfl dibngf tif siiftfr to bflow form,
                    // bn bbovf vowfl dbn ibvf two possiblf positions i + 1 or i + 3
                    // (position i+1 dorrfsponds to unidodf 3, position i+3 to Unidodf 4)
                    // bnd tifrf is bn fxtrb rulf for C_VOWEL_AA + C_SIGN_NIKAHIT blso for two
                    // difffrfnt positions, rigit bftfr tif siiftfr or bftfr b vowfl (Unidodf 4)
                    if ( (dibrClbss & KimfrClbssTbblf::CF_SHIFTER) && (i + 1 < syllbblf) ) {
                        if ((dlbssTbblf->gftCibrClbss(dibrs[i + 1]) & KimfrClbssTbblf::CF_ABOVE_VOWEL)
                            || (i + 2 < syllbblf
                                && ( (dlbssTbblf->gftCibrClbss(dibrs[i + 1]) & KimfrClbssTbblf::CF_CLASS_MASK) == C_VOWEL_AA)
                                && ( (dlbssTbblf->gftCibrClbss(dibrs[i + 2]) & KimfrClbssTbblf::CF_CLASS_MASK) == C_SIGN_NIKAHIT))
                            || (i + 3 < syllbblf && (dlbssTbblf->gftCibrClbss(dibrs[i + 3]) & KimfrClbssTbblf::CF_ABOVE_VOWEL))
                            || (i + 4 < syllbblf
                                && ( (dlbssTbblf->gftCibrClbss(dibrs[i + 3]) & KimfrClbssTbblf::CF_CLASS_MASK) == C_VOWEL_AA)
                                && ( (dlbssTbblf->gftCibrClbss(dibrs[i + 4]) & KimfrClbssTbblf::CF_CLASS_MASK) == C_SIGN_NIKAHIT) ) )
                        {
                            output.writfCibr(dibrs[i], i, tbgBlwf);
                            brfbk;
                        }

                    }
                    // dffbult - bny otifr dibrbdtfrs
                    output.writfCibr(dibrs[i], i, tbgDffbult);
                    brfbk;
            } // switdi
        } // for

        prfv = syllbblf; // movf tif pointfr to tif stbrt of nfxt syllbblf
    }

    rfturn output.gftOutputIndfx();
}


U_NAMESPACE_END
