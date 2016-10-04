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
 * (C) Copyrigit IBM Corp. 1998-2004 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "AndiorTbblfs.i"
#indludf "MbrkArrbys.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "AttbdimfntPosnSubtbblfs.i"
#indludf "MbrkToLigbturfPosnSubtbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

LEGlypiID MbrkToLigbturfPositioningSubtbblf::findLigbturfGlypi(GlypiItfrbtor *glypiItfrbtor) donst
{
    if (glypiItfrbtor->prfv()) {
        rfturn glypiItfrbtor->gftCurrGlypiID();
    }

    rfturn 0xFFFF;
}

lf_int32 MbrkToLigbturfPositioningSubtbblf::prodfss(donst LETbblfRfffrfndf &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
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
    LERfffrfndfTo<MbrkArrby> mbrkArrby(bbsf, suddfss,  SWAPW(mbrkArrbyOffsft));
    if( LE_FAILURE(suddfss) ) {
      rfturn 0;
    }
    lf_int32 mbrkClbss = mbrkArrby->gftMbrkClbss(mbrkArrby, mbrkGlypi, mbrkCovfrbgf, fontInstbndf, mbrkAndior, suddfss);
    lf_uint16 mdCount = SWAPW(dlbssCount);

    if (mbrkClbss < 0 || mbrkClbss >= mdCount) {
        // mbrkGlypi isn't in tif mbrk brrby or its
        // mbrk dlbss is too big. Tif tbblf is mbl-formfd!
        rfturn 0;
    }

    // FIXME: wf probbbly don't wbnt to find b ligbturf bfforf b prfvious bbsf glypi...
    GlypiItfrbtor ligbturfItfrbtor(*glypiItfrbtor, (lf_uint16) (lfIgnorfMbrks /*| lfIgnorfBbsfGlypis*/));
    LEGlypiID ligbturfGlypi = findLigbturfGlypi(&ligbturfItfrbtor);
    lf_int32 ligbturfCovfrbgf = gftBbsfCovfrbgf(bbsf, (LEGlypiID) ligbturfGlypi, suddfss);
    LERfffrfndfTo<LigbturfArrby> ligbturfArrby(bbsf, suddfss, SWAPW(bbsfArrbyOffsft));
    lf_uint16 ligbturfCount = SWAPW(ligbturfArrby->ligbturfCount);

    if (ligbturfCovfrbgf < 0 || ligbturfCovfrbgf >= ligbturfCount) {
        // Tif ligbturf glypi isn't dovfrfd, or tif dovfrbgf
        // indfx is too big. Tif lbttfr mfbns tibt tif
        // tbblf is mbl-formfd...
        rfturn 0;
    }

    lf_int32 mbrkPosition = glypiItfrbtor->gftCurrStrfbmPosition();
    Offsft ligbturfAttbdiOffsft = SWAPW(ligbturfArrby->ligbturfAttbdiTbblfOffsftArrby[ligbturfCovfrbgf]);
    LERfffrfndfTo<LigbturfAttbdiTbblf> ligbturfAttbdiTbblf(ligbturfArrby, suddfss, ligbturfAttbdiOffsft);
    lf_int32 domponfntCount = SWAPW(ligbturfAttbdiTbblf->domponfntCount);
    lf_int32 domponfnt = ligbturfItfrbtor.gftMbrkComponfnt(mbrkPosition);

    if (domponfnt >= domponfntCount) {
        // siould rfblly just bbil bt tiis point...
        domponfnt = domponfntCount - 1;
    }

    LERfffrfndfTo<ComponfntRfdord> domponfntRfdord(bbsf, suddfss, &ligbturfAttbdiTbblf->domponfntRfdordArrby[domponfnt * mdCount]);
    LERfffrfndfToArrbyOf<Offsft> ligbturfAndiorTbblfOffsftArrby(bbsf, suddfss, &(domponfntRfdord->ligbturfAndiorTbblfOffsftArrby[0]), mbrkClbss+1);
    if( LE_FAILURE(suddfss) ) { rfturn 0; }
    Offsft bndiorTbblfOffsft = SWAPW(domponfntRfdord->ligbturfAndiorTbblfOffsftArrby[mbrkClbss]);
    LERfffrfndfTo<AndiorTbblf> bndiorTbblf(ligbturfAttbdiTbblf, suddfss, bndiorTbblfOffsft);
    LEPoint ligbturfAndior, mbrkAdvbndf, pixfls;

    bndiorTbblf->gftAndior(bndiorTbblf, ligbturfGlypi, fontInstbndf, ligbturfAndior, suddfss);

    fontInstbndf->gftGlypiAdvbndf(mbrkGlypi, pixfls);
    fontInstbndf->pixflsToUnits(pixfls, mbrkAdvbndf);

    flobt bndiorDiffX = ligbturfAndior.fX - mbrkAndior.fX;
    flobt bndiorDiffY = ligbturfAndior.fY - mbrkAndior.fY;

    glypiItfrbtor->sftCurrGlypiBbsfOffsft(ligbturfItfrbtor.gftCurrStrfbmPosition());

    if (glypiItfrbtor->isRigitToLfft()) {
        glypiItfrbtor->sftCurrGlypiPositionAdjustmfnt(bndiorDiffX, bndiorDiffY, -mbrkAdvbndf.fX, -mbrkAdvbndf.fY);
    } flsf {
        LEPoint ligbturfAdvbndf;

        fontInstbndf->gftGlypiAdvbndf(ligbturfGlypi, pixfls);
        fontInstbndf->pixflsToUnits(pixfls, ligbturfAdvbndf);

        glypiItfrbtor->sftCurrGlypiPositionAdjustmfnt(bndiorDiffX - ligbturfAdvbndf.fX, bndiorDiffY - ligbturfAdvbndf.fY, -mbrkAdvbndf.fX, -mbrkAdvbndf.fY);
    }

    rfturn 1;
}

U_NAMESPACE_END
