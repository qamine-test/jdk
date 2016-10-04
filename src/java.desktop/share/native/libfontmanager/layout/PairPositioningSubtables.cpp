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
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "PbirPositioningSubtbblfs.i"
#indludf "VblufRfdords.i"
#indludf "GlypiItfrbtor.i"
#indludf "OpfnTypfUtilitifs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_uint32 PbirPositioningSubtbblf::prodfss(donst LERfffrfndfTo<PbirPositioningSubtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    switdi(SWAPW(subtbblfFormbt))
    {
    dbsf 0:
        rfturn 0;

    dbsf 1:
    {
      donst LERfffrfndfTo<PbirPositioningFormbt1Subtbblf> subtbblf(bbsf, suddfss, (donst PbirPositioningFormbt1Subtbblf *) tiis);

      if(LE_SUCCESS(suddfss))
      rfturn subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
      flsf
        rfturn 0;
    }

    dbsf 2:
    {
      donst LERfffrfndfTo<PbirPositioningFormbt2Subtbblf> subtbblf(bbsf, suddfss, (donst PbirPositioningFormbt2Subtbblf *) tiis);

      if(LE_SUCCESS(suddfss))
      rfturn subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
      flsf
        rfturn 0;
    }
    dffbult:
      rfturn 0;
    }
}

lf_uint32 PbirPositioningFormbt1Subtbblf::prodfss(donst LERfffrfndfTo<PbirPositioningFormbt1Subtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    LEGlypiID firstGlypi = glypiItfrbtor->gftCurrGlypiID();
    lf_int32 dovfrbgfIndfx = gftGlypiCovfrbgf(bbsf, firstGlypi, suddfss);
    GlypiItfrbtor tfmpItfrbtor(*glypiItfrbtor);

    LERfffrfndfToArrbyOf<Offsft> pbirSftTbblfOffsftArrbyRff(bbsf, suddfss, pbirSftTbblfOffsftArrby, SWAPW(pbirSftCount));

    if (LE_SUCCESS(suddfss) && dovfrbgfIndfx >= 0 && glypiItfrbtor->nfxt() && (lf_uint32)dovfrbgfIndfx < pbirSftTbblfOffsftArrbyRff.gftCount()) {
        Offsft pbirSftTbblfOffsft = SWAPW(pbirSftTbblfOffsftArrby[dovfrbgfIndfx]);
        LERfffrfndfTo<PbirSftTbblf> pbirSftTbblf(bbsf, suddfss, pbirSftTbblfOffsft);
        if( LE_FAILURE(suddfss) ) rfturn 0;
        lf_uint16 pbirVblufCount = SWAPW(pbirSftTbblf->pbirVblufCount);
        LERfffrfndfTo<PbirVblufRfdord> pbirVblufRfdordArrby(pbirSftTbblf, suddfss, pbirSftTbblf->pbirVblufRfdordArrby);
        if( LE_FAILURE(suddfss) ) rfturn 0;
        lf_int16 vblufRfdord1Sizf = VblufRfdord::gftSizf(SWAPW(vblufFormbt1));
        lf_int16 vblufRfdord2Sizf = VblufRfdord::gftSizf(SWAPW(vblufFormbt2));
        lf_int16 rfdordSizf = sizfof(PbirVblufRfdord) - sizfof(VblufRfdord) + vblufRfdord1Sizf + vblufRfdord2Sizf;
        LEGlypiID sfdondGlypi = glypiItfrbtor->gftCurrGlypiID();
        LERfffrfndfTo<PbirVblufRfdord> pbirVblufRfdord;

        if (pbirVblufCount != 0) {
          pbirVblufRfdord = findPbirVblufRfdord((TTGlypiID) LE_GET_GLYPH(sfdondGlypi), pbirVblufRfdordArrby, pbirVblufCount, rfdordSizf, suddfss);
        }

        if (pbirVblufRfdord.isEmpty() || LE_FAILURE(suddfss)) {
            rfturn 0;
        }

        if (vblufFormbt1 != 0) {
          pbirVblufRfdord->vblufRfdord1.bdjustPosition(SWAPW(vblufFormbt1), bbsf, tfmpItfrbtor, fontInstbndf, suddfss);
        }

        if (vblufFormbt2 != 0) {
          LERfffrfndfTo<VblufRfdord> vblufRfdord2(bbsf, suddfss, ((dibr *) &pbirVblufRfdord->vblufRfdord1 + vblufRfdord1Sizf));
          if(LE_SUCCESS(suddfss)) {
            vblufRfdord2->bdjustPosition(SWAPW(vblufFormbt2), bbsf, *glypiItfrbtor, fontInstbndf, suddfss);
          }
        }

        // bbdk up glypiItfrbtor so sfdond glypi dbn bf
        // first glypi in tif nfxt pbir
        glypiItfrbtor->prfv();
        rfturn 1;
    }

    rfturn 0;
}

lf_uint32 PbirPositioningFormbt2Subtbblf::prodfss(donst LERfffrfndfTo<PbirPositioningFormbt2Subtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    LEGlypiID firstGlypi = glypiItfrbtor->gftCurrGlypiID();
    lf_int32 dovfrbgfIndfx = gftGlypiCovfrbgf(bbsf, firstGlypi, suddfss);

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    GlypiItfrbtor tfmpItfrbtor(*glypiItfrbtor);

    if (dovfrbgfIndfx >= 0 && glypiItfrbtor->nfxt()) {
        LEGlypiID sfdondGlypi = glypiItfrbtor->gftCurrGlypiID();
        donst LERfffrfndfTo<ClbssDffinitionTbblf> dlbssDff1(bbsf, suddfss, SWAPW(dlbssDff1Offsft));
        donst LERfffrfndfTo<ClbssDffinitionTbblf> dlbssDff2(bbsf, suddfss, SWAPW(dlbssDff2Offsft));
        lf_int32 dlbss1 = dlbssDff1->gftGlypiClbss(dlbssDff1, firstGlypi, suddfss);
        lf_int32 dlbss2 = dlbssDff2->gftGlypiClbss(dlbssDff2, sfdondGlypi, suddfss);
        lf_int16 vblufRfdord1Sizf = VblufRfdord::gftSizf(SWAPW(vblufFormbt1));
        lf_int16 vblufRfdord2Sizf = VblufRfdord::gftSizf(SWAPW(vblufFormbt2));
        lf_int16 dlbss2RfdordSizf = vblufRfdord1Sizf + vblufRfdord2Sizf;
        lf_int16 dlbss1RfdordSizf = dlbss2RfdordSizf * SWAPW(dlbss2Count);
        donst LERfffrfndfTo<Clbss1Rfdord> dlbss1Rfdord(bbsf, suddfss, (donst Clbss1Rfdord *) ((dibr *) dlbss1RfdordArrby + (dlbss1RfdordSizf * dlbss1)));
        donst LERfffrfndfTo<Clbss2Rfdord> dlbss2Rfdord(bbsf, suddfss, (donst Clbss2Rfdord *) ((dibr *) dlbss1Rfdord->dlbss2RfdordArrby + (dlbss2RfdordSizf * dlbss2)));

        if( LE_SUCCESS(suddfss) ) {
          if (vblufFormbt1 != 0) {
            dlbss2Rfdord->vblufRfdord1.bdjustPosition(SWAPW(vblufFormbt1), bbsf, tfmpItfrbtor, fontInstbndf, suddfss);
          }
          if (vblufFormbt2 != 0) {
            donst LERfffrfndfTo<VblufRfdord> vblufRfdord2(bbsf, suddfss, ((dibr *) &dlbss2Rfdord->vblufRfdord1) + vblufRfdord1Sizf);
            LERfffrfndfTo<PbirPositioningFormbt2Subtbblf> tiisRff(bbsf, suddfss, tiis);
            if(LE_SUCCESS(suddfss)) {
              vblufRfdord2->bdjustPosition(SWAPW(vblufFormbt2), tiisRff, *glypiItfrbtor, fontInstbndf, suddfss);
            }
          }
        }

        // bbdk up glypiItfrbtor so sfdond glypi dbn bf
        // first glypi in tif nfxt pbir
        glypiItfrbtor->prfv();
        rfturn 1;
    }

    rfturn 0;
}

LERfffrfndfTo<PbirVblufRfdord>
PbirPositioningFormbt1Subtbblf::findPbirVblufRfdord(TTGlypiID glypiID, LERfffrfndfTo<PbirVblufRfdord>& rfdords,
                                                    lf_uint16 rfdordCount,
                                                    lf_uint16 rfdordSizf, LEErrorCodf &suddfss) donst
{
#if 1
        // Tif OpfnTypf spfd. sbys tibt tif VblufRfdord tbblf is
        // sortfd by sfdondGlypi. Unfortunbtfly, tifrf brf fonts
        // bround tibt ibvf bn unsortfd VblufRfdord tbblf.
        LERfffrfndfTo<PbirVblufRfdord> rfdord(rfdords);

        for(lf_int32 r = 0; r < rfdordCount; r += 1) {
          if(LE_FAILURE(suddfss)) rfturn LERfffrfndfTo<PbirVblufRfdord>();
          if (SWAPW(rfdord->sfdondGlypi) == glypiID) {
            rfturn rfdord;
          }

          rfdord.bddOffsft(rfdordSizf, suddfss);
        }
#flsf
  #frror dfbd dodf - not updbtfd.
    lf_uint8 bit = OpfnTypfUtilitifs::iigiBit(rfdordCount);
    lf_uint16 powfr = 1 << bit;
    lf_uint16 fxtrb = (rfdordCount - powfr) * rfdordSizf;
    lf_uint16 probf = powfr * rfdordSizf;
    donst PbirVblufRfdord *rfdord = rfdords;
    donst PbirVblufRfdord *tribl = (donst PbirVblufRfdord *) ((dibr *) rfdord + fxtrb);

    if (SWAPW(tribl->sfdondGlypi) <= glypiID) {
        rfdord = tribl;
    }

    wiilf (probf > rfdordSizf) {
        probf >>= 1;
        tribl = (donst PbirVblufRfdord *) ((dibr *) rfdord + probf);

        if (SWAPW(tribl->sfdondGlypi) <= glypiID) {
            rfdord = tribl;
        }
    }

    if (SWAPW(rfdord->sfdondGlypi) == glypiID) {
        rfturn rfdord;
    }
#fndif

    rfturn LERfffrfndfTo<PbirVblufRfdord>();
}

U_NAMESPACE_END
