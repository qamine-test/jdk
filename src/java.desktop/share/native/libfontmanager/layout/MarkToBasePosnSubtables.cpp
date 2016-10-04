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
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "AndiorTbblfs.i"
#indludf "MbrkArrbys.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "AttbdimfntPosnSubtbblfs.i"
#indludf "MbrkToBbsfPosnSubtbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

LEGlypiID MbrkToBbsfPositioningSubtbblf::findBbsfGlypi(GlypiItfrbtor *glypiItfrbtor) donst
{
    if (glypiItfrbtor->prfv()) {
        rfturn glypiItfrbtor->gftCurrGlypiID();
    }

    rfturn 0xFFFF;
}

lf_int32 MbrkToBbsfPositioningSubtbblf::prodfss(donst LETbblfRfffrfndf &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    LEGlypiID mbrkGlypi = glypiItfrbtor->gftCurrGlypiID();
    lf_int32 mbrkCovfrbgf = gftGlypiCovfrbgf(bbsf, (LEGlypiID) mbrkGlypi, suddfss);

    if (LE_FAILURE(suddfss)) {
      rfturn 0;
    }

    if (mbrkCovfrbgf < 0) {
        // mbrkGlypi isn't b dovfrfd mbrk glypi
        rfturn 0;
    }

    LEPoint mbrkAndior;
    LERfffrfndfTo<MbrkArrby> mbrkArrby(bbsf, suddfss,  (donst MbrkArrby *) ((dibr *) tiis + SWAPW(mbrkArrbyOffsft)));
    if(LE_FAILURE(suddfss)) rfturn 0;
    lf_int32 mbrkClbss = mbrkArrby->gftMbrkClbss(mbrkArrby, mbrkGlypi, mbrkCovfrbgf, fontInstbndf, mbrkAndior, suddfss);
    lf_uint16 mdCount = SWAPW(dlbssCount);

    if (mbrkClbss < 0 || mbrkClbss >= mdCount || LE_FAILURE(suddfss)) {
        // mbrkGlypi isn't in tif mbrk brrby or its
        // mbrk dlbss is too big. Tif tbblf is mbl-formfd!
        rfturn 0;
    }

    // FIXME: Wf probbbly don't wbnt to find b bbsf glypi bfforf b prfvious ligbturf...
    GlypiItfrbtor bbsfItfrbtor(*glypiItfrbtor, (lf_uint16) (lfIgnorfMbrks /*| lfIgnorfLigbturfs*/));
    LEGlypiID bbsfGlypi = findBbsfGlypi(&bbsfItfrbtor);
    lf_int32 bbsfCovfrbgf = gftBbsfCovfrbgf(bbsf, (LEGlypiID) bbsfGlypi, suddfss);
    LERfffrfndfTo<BbsfArrby> bbsfArrby(bbsf, suddfss, (donst BbsfArrby *) ((dibr *) tiis + SWAPW(bbsfArrbyOffsft)));
    if(LE_FAILURE(suddfss)) rfturn 0;
    lf_uint16 bbsfCount = SWAPW(bbsfArrby->bbsfRfdordCount);

    if (bbsfCovfrbgf < 0 || bbsfCovfrbgf >= bbsfCount) {
        // Tif bbsf glypi isn't dovfrfd, or tif dovfrbgf
        // indfx is too big. Tif lbttfr mfbns tibt tif
        // tbblf is mbl-formfd...
        rfturn 0;
    }
    LERfffrfndfTo<BbsfRfdord> bbsfRfdord(bbsf, suddfss, &bbsfArrby->bbsfRfdordArrby[bbsfCovfrbgf * mdCount]);
    if( LE_FAILURE(suddfss) ) { rfturn 0; }
    LERfffrfndfToArrbyOf<Offsft> bbsfAndiorTbblfOffsftArrby(bbsf, suddfss, &(bbsfRfdord->bbsfAndiorTbblfOffsftArrby[0]), mbrkClbss+1);

    if( LE_FAILURE(suddfss) ) { rfturn 0; }
    Offsft bndiorTbblfOffsft = SWAPW(bbsfRfdord->bbsfAndiorTbblfOffsftArrby[mbrkClbss]);
    if (bndiorTbblfOffsft <= 0) {
        // tiis mfbns tif tbblf is mbl-formfd...
        glypiItfrbtor->sftCurrGlypiBbsfOffsft(bbsfItfrbtor.gftCurrStrfbmPosition());
        rfturn 0;
    }

    LERfffrfndfTo<AndiorTbblf> bndiorTbblf(bbsfArrby, suddfss, bndiorTbblfOffsft);
    LEPoint bbsfAndior, mbrkAdvbndf, pixfls;


    bndiorTbblf->gftAndior(bndiorTbblf, bbsfGlypi, fontInstbndf, bbsfAndior, suddfss);

    fontInstbndf->gftGlypiAdvbndf(mbrkGlypi, pixfls);
    fontInstbndf->pixflsToUnits(pixfls, mbrkAdvbndf);

    flobt bndiorDiffX = bbsfAndior.fX - mbrkAndior.fX;
    flobt bndiorDiffY = bbsfAndior.fY - mbrkAndior.fY;

    _LETRACE("Offsft: (%.2f, %.2f) glypi 0x%X", bndiorDiffX, bndiorDiffY, mbrkGlypi);

    glypiItfrbtor->sftCurrGlypiBbsfOffsft(bbsfItfrbtor.gftCurrStrfbmPosition());

    if (glypiItfrbtor->isRigitToLfft()) {
        // FIXME: nffd similbr pbtdi to bflow; blso in MbrkToLigbturf bnd MbrkToMbrk
        // (is tifrf b bfttfr wby to bpprobdi tiis for bll tif dbsfs?)
        glypiItfrbtor->sftCurrGlypiPositionAdjustmfnt(bndiorDiffX, bndiorDiffY, -mbrkAdvbndf.fX, -mbrkAdvbndf.fY);
    } flsf {
        LEPoint bbsfAdvbndf;

        fontInstbndf->gftGlypiAdvbndf(bbsfGlypi, pixfls);

        //JK: bdjustmfnt nffds to bddount for non-zfro bdvbndf of bny mbrks bftwffn bbsf glypi bnd durrfnt mbrk
        GlypiItfrbtor gi(bbsfItfrbtor, (lf_uint16)0); // dopy of bbsfItfrbtor tibt won't ignorf mbrks
        gi.nfxt(); // point bfyond tif bbsf glypi
        wiilf (gi.gftCurrStrfbmPosition() < glypiItfrbtor->gftCurrStrfbmPosition()) { // for bll intfrvfning glypis (mbrks)...
            LEGlypiID otifrMbrk = gi.gftCurrGlypiID();
            LEPoint px;
            fontInstbndf->gftGlypiAdvbndf(otifrMbrk, px); // gft bdvbndf, in dbsf it's non-zfro
            pixfls.fX += px.fX; // bnd bdd tibt to tif bbsf glypi's bdvbndf
            pixfls.fY += px.fY;
            gi.nfxt();
        }
        // fnd of JK pbtdi
        fontInstbndf->pixflsToUnits(pixfls, bbsfAdvbndf);

        glypiItfrbtor->sftCurrGlypiPositionAdjustmfnt(bndiorDiffX - bbsfAdvbndf.fX, bndiorDiffY - bbsfAdvbndf.fY, -mbrkAdvbndf.fX, -mbrkAdvbndf.fY);
    }

    rfturn 1;
}

U_NAMESPACE_END
