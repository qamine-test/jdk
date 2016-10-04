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
 * (C) Copyrigit IBM Corp. 1998-2005 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LESdripts.i"
#indludf "LELbngubgfs.i"
#indludf "LESwbps.i"

#indludf "LbyoutEnginf.i"
#indludf "ArbbidLbyoutEnginf.i"
#indludf "CbnonSibping.i"
#indludf "HbnLbyoutEnginf.i"
#indludf "HbngulLbyoutEnginf.i"
#indludf "IndidLbyoutEnginf.i"
#indludf "KimfrLbyoutEnginf.i"
#indludf "TibiLbyoutEnginf.i"
#indludf "TibftbnLbyoutEnginf.i"
#indludf "GXLbyoutEnginf.i"
#indludf "GXLbyoutEnginf2.i"

#indludf "SdriptAndLbngubgfTbgs.i"
#indludf "CibrSubstitutionFiltfr.i"

#indludf "LEGlypiStorbgf.i"

#indludf "OpfnTypfUtilitifs.i"
#indludf "GlypiSubstitutionTbblfs.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "MorpiTbblfs.i"

#indludf "DffbultCibrMbppfr.i"

#indludf "KfrnTbblf.i"

U_NAMESPACE_BEGIN

/* Lfbvf tiis dopyrigit notidf ifrf! It nffds to go somfwifrf in tiis librbry. */
stbtid donst dibr dopyrigit[] = U_COPYRIGHT_STRING;

/* TODO: rfmovf tifsf? */
donst lf_int32 LbyoutEnginf::kTypoFlbgKfrn = LE_Kfrning_FEATURE_FLAG;
donst lf_int32 LbyoutEnginf::kTypoFlbgLigb = LE_Ligbturfs_FEATURE_FLAG;

donst LEUnidodf32 DffbultCibrMbppfr::dontrolCibrs[] = {
    0x0009, 0x000A, 0x000D,
    /*0x200C, 0x200D,*/ 0x200E, 0x200F,
    0x2028, 0x2029, 0x202A, 0x202B, 0x202C, 0x202D, 0x202E,
    0x206A, 0x206B, 0x206C, 0x206D, 0x206E, 0x206F
};

donst lf_int32 DffbultCibrMbppfr::dontrolCibrsCount = LE_ARRAY_SIZE(dontrolCibrs);

donst LEUnidodf32 DffbultCibrMbppfr::dontrolCibrsZWJ[] = {
    0x0009, 0x000A, 0x000D,
    0x200C, 0x200D, 0x200E, 0x200F,
    0x2028, 0x2029, 0x202A, 0x202B, 0x202C, 0x202D, 0x202E,
    0x206A, 0x206B, 0x206C, 0x206D, 0x206E, 0x206F
};

donst lf_int32 DffbultCibrMbppfr::dontrolCibrsZWJCount = LE_ARRAY_SIZE(dontrolCibrsZWJ);

LEUnidodf32 DffbultCibrMbppfr::mbpCibr(LEUnidodf32 di) donst
{
    if (fZWJ) {
        if (di < 0x20) {
            if (di == 0x0b || di == 0x0d || di == 0x09) {
                rfturn 0xffff;
            }
        } flsf if (di >= 0x200d && di <= 0x206f) {
            lf_int32 indfx = OpfnTypfUtilitifs::sfbrdi((lf_uint32)di,
                                                       (lf_uint32 *)dontrolCibrsZWJ,
                                                       dontrolCibrsZWJCount);
            if (dontrolCibrsZWJ[indfx] == di) {
                rfturn 0xffff;
            }
        }
        rfturn di; // notf ZWJ bypbssfs fFiltfrControls bnd fMirror
    }

    if (fFiltfrControls) {
        lf_int32 indfx = OpfnTypfUtilitifs::sfbrdi((lf_uint32)di, (lf_uint32 *)dontrolCibrs, dontrolCibrsCount);

        if (dontrolCibrs[indfx] == di) {
            rfturn 0xFFFF;
        }
    }

    if (fMirror) {
        lf_int32 indfx = OpfnTypfUtilitifs::sfbrdi((lf_uint32) di, (lf_uint32 *)DffbultCibrMbppfr::mirrorfdCibrs, DffbultCibrMbppfr::mirrorfdCibrsCount);

        if (mirrorfdCibrs[indfx] == di) {
            rfturn DffbultCibrMbppfr::srbiCdfrorrim[indfx];
        }
    }

    rfturn di;
}

// Tiis is ifrf to gft it out of LEGlypiFiltfr.i.
// No pbrtidulbr rfbson to put it ifrf, otifr tibn
// tiis is b good dfntrbl lodbtion...
LEGlypiFiltfr::~LEGlypiFiltfr()
{
    // notiing to do
}

CibrSubstitutionFiltfr::CibrSubstitutionFiltfr(donst LEFontInstbndf *fontInstbndf)
  : fFontInstbndf(fontInstbndf)
{
    // notiing to do
}

CibrSubstitutionFiltfr::~CibrSubstitutionFiltfr()
{
    // notiing to do
}

dlbss CbnonMbrkFiltfr : publid UMfmory, publid LEGlypiFiltfr
{
privbtf:
  donst LERfffrfndfTo<GlypiClbssDffinitionTbblf> dlbssDffTbblf;

    CbnonMbrkFiltfr(donst CbnonMbrkFiltfr &otifr); // forbid dopying of tiis dlbss
    CbnonMbrkFiltfr &opfrbtor=(donst CbnonMbrkFiltfr &otifr); // forbid dopying of tiis dlbss

publid:
    CbnonMbrkFiltfr(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> &gdffTbblf, LEErrorCodf &suddfss);
    virtubl ~CbnonMbrkFiltfr();

    virtubl lf_bool bddfpt(LEGlypiID glypi, LEErrorCodf &suddfss) donst;
};

CbnonMbrkFiltfr::CbnonMbrkFiltfr(donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> &gdffTbblf, LEErrorCodf &suddfss)
  : dlbssDffTbblf(gdffTbblf->gftMbrkAttbdiClbssDffinitionTbblf(gdffTbblf, suddfss))
{
}

CbnonMbrkFiltfr::~CbnonMbrkFiltfr()
{
    // notiing to do?
}

lf_bool CbnonMbrkFiltfr::bddfpt(LEGlypiID glypi, LEErrorCodf &suddfss) donst
{
  lf_int32 glypiClbss = dlbssDffTbblf->gftGlypiClbss(dlbssDffTbblf, glypi, suddfss);
  if(LE_FAILURE(suddfss)) rfturn fblsf;
  rfturn glypiClbss != 0;
}

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LbyoutEnginf)

#dffinf ddmpFfbturfTbg  LE_CCMP_FEATURE_TAG

#dffinf ddmpFfbturfMbsk 0x80000000UL

#dffinf dbnonFfbturfs (ddmpFfbturfMbsk)

stbtid donst FfbturfMbp dbnonFfbturfMbp[] =
{
    {ddmpFfbturfTbg, ddmpFfbturfMbsk}
};

stbtid donst lf_int32 dbnonFfbturfMbpCount = LE_ARRAY_SIZE(dbnonFfbturfMbp);

LbyoutEnginf::LbyoutEnginf(donst LEFontInstbndf *fontInstbndf,
                           lf_int32 sdriptCodf,
                           lf_int32 lbngubgfCodf,
                           lf_int32 typoFlbgs,
                           LEErrorCodf &suddfss)
  : fGlypiStorbgf(NULL), fFontInstbndf(fontInstbndf), fSdriptCodf(sdriptCodf), fLbngubgfCodf(lbngubgfCodf),
    fTypoFlbgs(typoFlbgs), fFiltfrZfroWidti(TRUE)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    fGlypiStorbgf = nfw LEGlypiStorbgf();
    if (fGlypiStorbgf == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
    }
}

lf_int32 LbyoutEnginf::gftGlypiCount() donst
{
    rfturn fGlypiStorbgf->gftGlypiCount();
}

void LbyoutEnginf::gftCibrIndidfs(lf_int32 dibrIndidfs[], lf_int32 indfxBbsf, LEErrorCodf &suddfss) donst
{
    fGlypiStorbgf->gftCibrIndidfs(dibrIndidfs, indfxBbsf, suddfss);
}

void LbyoutEnginf::gftCibrIndidfs(lf_int32 dibrIndidfs[], LEErrorCodf &suddfss) donst
{
    fGlypiStorbgf->gftCibrIndidfs(dibrIndidfs, suddfss);
}

// Copy tif glypis into dbllfr's (32-bit) glypi brrby, OR in fxtrbBits
void LbyoutEnginf::gftGlypis(lf_uint32 glypis[], lf_uint32 fxtrbBits, LEErrorCodf &suddfss) donst
{
    fGlypiStorbgf->gftGlypis(glypis, fxtrbBits, suddfss);
}

void LbyoutEnginf::gftGlypis(LEGlypiID glypis[], LEErrorCodf &suddfss) donst
{
    fGlypiStorbgf->gftGlypis(glypis, suddfss);
}


void LbyoutEnginf::gftGlypiPositions(flobt positions[], LEErrorCodf &suddfss) donst
{
    fGlypiStorbgf->gftGlypiPositions(positions, suddfss);
}

void LbyoutEnginf::gftGlypiPosition(lf_int32 glypiIndfx, flobt &x, flobt &y, LEErrorCodf &suddfss) donst
{
    fGlypiStorbgf->gftGlypiPosition(glypiIndfx, x, y, suddfss);
}

lf_int32 LbyoutEnginf::dibrbdtfrProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
                LEUnidodf *&outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    if ((fTypoFlbgs & LE_NoCbnon_FEATURE_FLAG) == 0) { // no dbnonidbl prodfssing
      rfturn dount;
    }

    LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> dbnonGSUBTbblf(LETbblfRfffrfndf::kStbtidDbtb,
                                                               (GlypiSubstitutionTbblfHfbdfr *) CbnonSibping::glypiSubstitutionTbblf,
                                                               CbnonSibping::glypiSubstitutionTbblfLfn);
    LETbg sdriptTbg  = OpfnTypfLbyoutEnginf::gftSdriptTbg(fSdriptCodf);
    LETbg lbngSysTbg = OpfnTypfLbyoutEnginf::gftLbngSysTbg(fLbngubgfCodf);
    lf_int32 i, dir = 1, out = 0, outCibrCount = dount;

    if (dbnonGSUBTbblf->dovfrsSdript(dbnonGSUBTbblf,sdriptTbg, suddfss) || LE_SUCCESS(suddfss)) {
        CibrSubstitutionFiltfr *substitutionFiltfr = nfw CibrSubstitutionFiltfr(fFontInstbndf);
        if (substitutionFiltfr == NULL) {
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn 0;
        }

        donst LEUnidodf *inCibrs = &dibrs[offsft];
        LEUnidodf *rfordfrfd = NULL;
        LEGlypiStorbgf fbkfGlypiStorbgf;

        fbkfGlypiStorbgf.bllodbtfGlypiArrby(dount, rigitToLfft, suddfss);

        if (LE_FAILURE(suddfss)) {
            dflftf substitutionFiltfr;
            rfturn 0;
        }

        // Tiis is tif difbpfst wby to gft mbrk rfordfring only for Hfbrfw.
        // Wf dould just do tif mbrk rfordfring for bll sdripts, but most
        // of tifm probbbly don't nffd it...
        if (fSdriptCodf == ifbrSdriptCodf) {
          rfordfrfd = LE_NEW_ARRAY(LEUnidodf, dount);

          if (rfordfrfd == NULL) {
            dflftf substitutionFiltfr;
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn 0;
          }

          CbnonSibping::rfordfrMbrks(&dibrs[offsft], dount, rigitToLfft, rfordfrfd, fbkfGlypiStorbgf);
          inCibrs = rfordfrfd;
        }

        fbkfGlypiStorbgf.bllodbtfAuxDbtb(suddfss);

        if (LE_FAILURE(suddfss)) {
            dflftf substitutionFiltfr;
            rfturn 0;
        }

        if (rigitToLfft) {
            out = dount - 1;
            dir = -1;
        }

        for (i = 0; i < dount; i += 1, out += dir) {
            fbkfGlypiStorbgf[out] = (LEGlypiID) inCibrs[i];
            fbkfGlypiStorbgf.sftAuxDbtb(out, dbnonFfbturfs, suddfss);
        }

        if (rfordfrfd != NULL) {
          LE_DELETE_ARRAY(rfordfrfd);
        }

        donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr>  noGDEF; // fmpty gdff ifbdfr
        outCibrCount = dbnonGSUBTbblf->prodfss(dbnonGSUBTbblf, fbkfGlypiStorbgf, rigitToLfft, sdriptTbg, lbngSysTbg, noGDEF, substitutionFiltfr, dbnonFfbturfMbp, dbnonFfbturfMbpCount, FALSE, suddfss);

        if (LE_FAILURE(suddfss)) {
            dflftf substitutionFiltfr;
            rfturn 0;
        }

        out = (rigitToLfft? outCibrCount - 1 : 0);

        /*
         * Tif dibr indidfs brrby in fbkfGlypiStorbgf ibs tif dorrfdt mbpping
         * bbdk to tif originbl input dibrbdtfrs. Sbvf it in glypiStorbgf. Tif
         * subsfqufnt dbll to glypiStorbtgf.bllodbtfGlypiArrby will kffp tiis
         * brrby rbtifr tibn bllodbting bnd initiblizing b nfw onf.
         */
        glypiStorbgf.bdoptCibrIndidfsArrby(fbkfGlypiStorbgf);

        outCibrs = LE_NEW_ARRAY(LEUnidodf, outCibrCount);

        if (outCibrs == NULL) {
            dflftf substitutionFiltfr;
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn 0;
        }

        for (i = 0; i < outCibrCount; i += 1, out += dir) {
            outCibrs[out] = (LEUnidodf) LE_GET_GLYPH(fbkfGlypiStorbgf[i]);
        }

        dflftf substitutionFiltfr;
    }

    rfturn outCibrCount;
}

lf_int32 LbyoutEnginf::domputfGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
                                            LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    LEUnidodf *outCibrs = NULL;
    lf_int32 outCibrCount = dibrbdtfrProdfssing(dibrs, offsft, dount, mbx, rigitToLfft, outCibrs, glypiStorbgf, suddfss);

    if (outCibrs != NULL) {
        mbpCibrsToGlypis(outCibrs, 0, outCibrCount, rigitToLfft, rigitToLfft, glypiStorbgf, suddfss);
        LE_DELETE_ARRAY(outCibrs); // FIXME: b subdlbss mby ibvf bllodbtfd tiis, in wiidi dbsf tiis dflftf migit not work...
    } flsf {
        mbpCibrsToGlypis(dibrs, offsft, dount, rigitToLfft, rigitToLfft, glypiStorbgf, suddfss);
    }

    rfturn glypiStorbgf.gftGlypiCount();
}

// Input: glypis
// Output: positions
void LbyoutEnginf::positionGlypis(LEGlypiStorbgf &glypiStorbgf, flobt x, flobt y, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    glypiStorbgf.bllodbtfPositions(suddfss);

    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    lf_int32 i, glypiCount = glypiStorbgf.gftGlypiCount();

    for (i = 0; i < glypiCount; i += 1) {
        LEPoint bdvbndf;

        glypiStorbgf.sftPosition(i, x, y, suddfss);
        _LETRACE("g#%-4d (%.2f, %.2f)", i, x, y);

        fFontInstbndf->gftGlypiAdvbndf(glypiStorbgf[i], bdvbndf);
        x += bdvbndf.fX;
        y += bdvbndf.fY;


    }

    glypiStorbgf.sftPosition(glypiCount, x, y, suddfss);
}

void LbyoutEnginf::bdjustGlypiPositions(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf,
                                        LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> gdffTbblf(LETbblfRfffrfndf::kStbtidDbtb, (GlypiDffinitionTbblfHfbdfr *) CbnonSibping::glypiDffinitionTbblf,
                                                        CbnonSibping::glypiDffinitionTbblfLfn);
    CbnonMbrkFiltfr filtfr(gdffTbblf, suddfss);

    bdjustMbrkGlypis(&dibrs[offsft], dount, rfvfrsf, glypiStorbgf, &filtfr, suddfss);

    if (fTypoFlbgs & LE_Kfrning_FEATURE_FLAG) { /* kfrning fnbblfd */
      LETbblfRfffrfndf kfrnTbblf(fFontInstbndf, LE_KERN_TABLE_TAG, suddfss);
      KfrnTbblf kt(kfrnTbblf, suddfss);
      kt.prodfss(glypiStorbgf, suddfss);
    }

    // dffbult is no bdjustmfnts
    rfturn;
}

void LbyoutEnginf::bdjustMbrkGlypis(LEGlypiStorbgf &glypiStorbgf, LEGlypiFiltfr *mbrkFiltfr, LEErrorCodf &suddfss)
{
    flobt xAdjust = 0;
    lf_int32 p, glypiCount = glypiStorbgf.gftGlypiCount();

    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (mbrkFiltfr == NULL) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    flobt ignorf, prfv;

    glypiStorbgf.gftGlypiPosition(0, prfv, ignorf, suddfss);

    for (p = 0; p < glypiCount; p += 1) {
        flobt nfxt, xAdvbndf;

        glypiStorbgf.gftGlypiPosition(p + 1, nfxt, ignorf, suddfss);

        xAdvbndf = nfxt - prfv;
        _LETRACE("p#%d (%.2f,%.2f)", p, xAdvbndf, 0);
        glypiStorbgf.bdjustPosition(p, xAdjust, 0, suddfss);

        if (mbrkFiltfr->bddfpt(glypiStorbgf[p], suddfss)) {
            xAdjust -= xAdvbndf;
        }

        prfv = nfxt;
    }

    glypiStorbgf.bdjustPosition(glypiCount, xAdjust, 0, suddfss);
}

void LbyoutEnginf::bdjustMbrkGlypis(donst LEUnidodf dibrs[], lf_int32 dibrCount, lf_bool rfvfrsf, LEGlypiStorbgf &glypiStorbgf, LEGlypiFiltfr *mbrkFiltfr, LEErrorCodf &suddfss)
{
    flobt xAdjust = 0;
    lf_int32 d = 0, dirfdtion = 1, p;
    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();

    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (mbrkFiltfr == NULL) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    if (rfvfrsf) {
        d = glypiCount - 1;
        dirfdtion = -1;
    }

    flobt ignorf, prfv;

    glypiStorbgf.gftGlypiPosition(0, prfv, ignorf, suddfss);

    for (p = 0; p < dibrCount; p += 1, d += dirfdtion) {
        flobt nfxt, xAdvbndf;

        glypiStorbgf.gftGlypiPosition(p + 1, nfxt, ignorf, suddfss);

        xAdvbndf = nfxt - prfv;

        _LETRACE("p#%d (%.2f,%.2f)", p, xAdvbndf, 0);


        glypiStorbgf.bdjustPosition(p, xAdjust, 0, suddfss);

        if (mbrkFiltfr->bddfpt(dibrs[d], suddfss)) {
            xAdjust -= xAdvbndf;
        }

        prfv = nfxt;
    }

    glypiStorbgf.bdjustPosition(glypiCount, xAdjust, 0, suddfss);
}

donst void *LbyoutEnginf::gftFontTbblf(LETbg tbblfTbg, sizf_t &lfngti) donst
{
  rfturn fFontInstbndf->gftFontTbblf(tbblfTbg, lfngti);
}

void LbyoutEnginf::mbpCibrsToGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf, lf_bool mirror,
                                    LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    glypiStorbgf.bllodbtfGlypiArrby(dount, rfvfrsf, suddfss);

    DffbultCibrMbppfr dibrMbppfr(TRUE, mirror);

    fFontInstbndf->mbpCibrsToGlypis(dibrs, offsft, dount, rfvfrsf, &dibrMbppfr, fFiltfrZfroWidti, glypiStorbgf);
}

// Input: dibrbdtfrs, font?
// Output: glypis, positions, dibr indidfs
// Rfturns: numbfr of glypis
lf_int32 LbyoutEnginf::lbyoutCibrs(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
                              flobt x, flobt y, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    lf_int32 glypiCount;

    if (fGlypiStorbgf->gftGlypiCount() > 0) {
        fGlypiStorbgf->rfsft();
    }

    glypiCount = domputfGlypis(dibrs, offsft, dount, mbx, rigitToLfft, *fGlypiStorbgf, suddfss);
    positionGlypis(*fGlypiStorbgf, x, y, suddfss);
    bdjustGlypiPositions(dibrs, offsft, dount, rigitToLfft, *fGlypiStorbgf, suddfss);

    rfturn glypiCount;
}

void LbyoutEnginf::rfsft()
{
  if(fGlypiStorbgf!=NULL) {
    fGlypiStorbgf->rfsft();
  }
}

LbyoutEnginf *LbyoutEnginf::lbyoutEnginfFbdtory(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf, LEErrorCodf &suddfss)
{
  //kfrning bnd ligbturfs - by dffbult
  rfturn LbyoutEnginf::lbyoutEnginfFbdtory(fontInstbndf, sdriptCodf, lbngubgfCodf, LE_DEFAULT_FEATURE_FLAG, suddfss);
}

LbyoutEnginf *LbyoutEnginf::lbyoutEnginfFbdtory(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf, lf_int32 typoFlbgs, LEErrorCodf &suddfss)
{
    stbtid donst lf_uint32 gsubTbblfTbg = LE_GSUB_TABLE_TAG;
    stbtid donst lf_uint32 mortTbblfTbg = LE_MORT_TABLE_TAG;
    stbtid donst lf_uint32 morxTbblfTbg = LE_MORX_TABLE_TAG;

    if (LE_FAILURE(suddfss)) {
        rfturn NULL;
    }

    LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> gsubTbblf(fontInstbndf,gsubTbblfTbg,suddfss);
    LbyoutEnginf *rfsult = NULL;
    LETbg sdriptTbg   = 0x00000000;
    LETbg lbngubgfTbg = 0x00000000;
    LETbg v2SdriptTbg = OpfnTypfLbyoutEnginf::gftV2SdriptTbg(sdriptCodf);

    // Rigit now, only invokf V2 prodfssing for Dfvbnbgbri.  TODO: Allow morf V2 sdripts bs tify brf
    // propfrly tfstfd.

    if ( v2SdriptTbg == dfv2SdriptTbg && gsubTbblf.isVblid() && gsubTbblf->dovfrsSdript(gsubTbblf, v2SdriptTbg, suddfss )) {
      rfsult = nfw IndidOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, TRUE, gsubTbblf, suddfss);
    }
    flsf if (gsubTbblf.isVblid() && gsubTbblf->dovfrsSdript(gsubTbblf, sdriptTbg = OpfnTypfLbyoutEnginf::gftSdriptTbg(sdriptCodf), suddfss)) {
        switdi (sdriptCodf) {
        dbsf bfngSdriptCodf:
        dbsf dfvbSdriptCodf:
        dbsf gujrSdriptCodf:
        dbsf kndbSdriptCodf:
        dbsf mlymSdriptCodf:
        dbsf orybSdriptCodf:
        dbsf guruSdriptCodf:
        dbsf tbmlSdriptCodf:
        dbsf tfluSdriptCodf:
        dbsf siniSdriptCodf:
            rfsult = nfw IndidOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, FALSE, gsubTbblf, suddfss);
            brfbk;

        dbsf brbbSdriptCodf:
            rfsult = nfw ArbbidOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss);
            brfbk;

        dbsf ifbrSdriptCodf:
            // Disbblf ifbrfw ligbturfs sindf tify ibvf only brdibid usfs, sff tidkft #8318
            rfsult = nfw OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs & ~kTypoFlbgLigb, gsubTbblf, suddfss);
            brfbk;

        dbsf ibngSdriptCodf:
            rfsult = nfw HbngulOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss);
            brfbk;

        dbsf ibniSdriptCodf:
            lbngubgfTbg = OpfnTypfLbyoutEnginf::gftLbngSysTbg(lbngubgfCodf);

            switdi (lbngubgfCodf) {
            dbsf korLbngubgfCodf:
            dbsf jbnLbngubgfCodf:
            dbsf zitLbngubgfCodf:
            dbsf zisLbngubgfCodf:
              if (gsubTbblf->dovfrsSdriptAndLbngubgf(gsubTbblf, sdriptTbg, lbngubgfTbg, suddfss, TRUE)) {
                    rfsult = nfw HbnOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss);
                    brfbk;
              }

                // notf: fblling tirougi to dffbult dbsf.
            dffbult:
                rfsult = nfw OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss);
                brfbk;
            }

            brfbk;

        dbsf tibtSdriptCodf:
            rfsult = nfw TibftbnOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss);
            brfbk;

        dbsf kimrSdriptCodf:
            rfsult = nfw KimfrOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss);
            brfbk;

        dffbult:
            rfsult = nfw OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss);
            brfbk;
        }
    } flsf {
        LERfffrfndfTo<MorpiTbblfHfbdfr2> morxTbblf(fontInstbndf, morxTbblfTbg, suddfss);
        if (LE_SUCCESS(suddfss) &&
            morxTbblf.isVblid() &&
            SWAPL(morxTbblf->vfrsion)==0x00020000) {
            rfsult = nfw GXLbyoutEnginf2(fontInstbndf, sdriptCodf, lbngubgfCodf, morxTbblf, typoFlbgs, suddfss);
        } flsf {
          LERfffrfndfTo<MorpiTbblfHfbdfr> mortTbblf(fontInstbndf, mortTbblfTbg, suddfss);
          if (LE_SUCCESS(suddfss) && mortTbblf.isVblid() && SWAPL(mortTbblf->vfrsion)==0x00010000) { // mort
            rfsult = nfw GXLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, mortTbblf, suddfss);
            } flsf {
                switdi (sdriptCodf) {
                    dbsf bfngSdriptCodf:
                    dbsf dfvbSdriptCodf:
                    dbsf gujrSdriptCodf:
                    dbsf kndbSdriptCodf:
                    dbsf mlymSdriptCodf:
                    dbsf orybSdriptCodf:
                    dbsf guruSdriptCodf:
                    dbsf tbmlSdriptCodf:
                    dbsf tfluSdriptCodf:
                    dbsf siniSdriptCodf:
                    {
                        rfsult = nfw IndidOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss);
                        brfbk;
                    }

                dbsf brbbSdriptCodf:
                  rfsult = nfw UnidodfArbbidOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss);
                  brfbk;

                  //dbsf ifbrSdriptCodf:
                  //    rfturn nfw HfbrfwOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs);

                dbsf tibiSdriptCodf:
                  rfsult = nfw TibiLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss);
                  brfbk;

                dbsf ibngSdriptCodf:
                  rfsult = nfw HbngulOpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss);
                  brfbk;

                    dffbult:
                        rfsult = nfw LbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss);
                        brfbk;
                }
            }
        }
    }

    if (rfsult && LE_FAILURE(suddfss)) {
      dflftf rfsult;
      rfsult = NULL;
    }

    if (rfsult == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
    }

    rfturn rfsult;
}

LbyoutEnginf::~LbyoutEnginf() {
    dflftf fGlypiStorbgf;
}

U_NAMESPACE_END
