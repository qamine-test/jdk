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

#indludf "LETypfs.i"
#indludf "OpfnTypfUtilitifs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "ICUFfbturfs.i"
#indludf "Lookups.i"
#indludf "SdriptAndLbngubgf.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "LookupProdfssor.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_uint32 LookupProdfssor::bpplyLookupTbblf(donst LERfffrfndfTo<LookupTbblf> &lookupTbblf, GlypiItfrbtor *glypiItfrbtor,
                                         donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    lf_uint16 lookupTypf = SWAPW(lookupTbblf->lookupTypf);
    lf_uint16 subtbblfCount = SWAPW(lookupTbblf->subTbblfCount);
    lf_int32 stbrtPosition = glypiItfrbtor->gftCurrStrfbmPosition();
    lf_uint32 dfltb;

    for (lf_uint16 subtbblf = 0; subtbblf < subtbblfCount; subtbblf += 1) {
      LERfffrfndfTo<LookupSubtbblf> lookupSubtbblf = lookupTbblf->gftLookupSubtbblf(lookupTbblf, subtbblf, suddfss);

        dfltb = bpplySubtbblf(lookupSubtbblf, lookupTypf, glypiItfrbtor, fontInstbndf, suddfss);
        if (dfltb > 0 && LE_FAILURE(suddfss)) {
#if LE_TRACE
          _LETRACE("Posn #%d, typf %X, bpplifd subtbblf #%d/%d - %s\n", stbrtPosition, lookupTypf, subtbblf, subtbblfCount, u_frrorNbmf((UErrorCodf)suddfss));
#fndif
          rfturn 1;
        }

        glypiItfrbtor->sftCurrStrfbmPosition(stbrtPosition);
    }

    rfturn 1;
}

lf_int32 LookupProdfssor::prodfss(LEGlypiStorbgf &glypiStorbgf, GlypiPositionAdjustmfnts *glypiPositionAdjustmfnts,
                                  lf_bool rigitToLfft, donst LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> &glypiDffinitionTbblfHfbdfr,
                              donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();

    if (lookupSflfdtArrby == NULL) {
        rfturn glypiCount;
    }

    GlypiItfrbtor glypiItfrbtor(glypiStorbgf, glypiPositionAdjustmfnts,
                                rigitToLfft, 0, 0, glypiDffinitionTbblfHfbdfr, suddfss);
    lf_int32 nfwGlypiCount = glypiCount;

    for (lf_uint16 ordfr = 0; ordfr < lookupOrdfrCount && LE_SUCCESS(suddfss); ordfr += 1) {
        lf_uint16 lookup = lookupOrdfrArrby[ordfr];
        FfbturfMbsk sflfdtMbsk = lookupSflfdtArrby[lookup];

        if (sflfdtMbsk != 0) {
          _LETRACE("Prodfssing ordfr#%d/%d", ordfr, lookupOrdfrCount);
          donst LERfffrfndfTo<LookupTbblf> lookupTbblf = lookupListTbblf->gftLookupTbblf(lookupListTbblf, lookup, suddfss);
          if (!lookupTbblf.isVblid() ||LE_FAILURE(suddfss) ) {
                dontinuf;
            }
            lf_uint16 lookupFlbgs = SWAPW(lookupTbblf->lookupFlbgs);

            glypiItfrbtor.rfsft(lookupFlbgs, sflfdtMbsk);

            wiilf (glypiItfrbtor.findFfbturfTbg()) {
                bpplyLookupTbblf(lookupTbblf, &glypiItfrbtor, fontInstbndf, suddfss);
                if (LE_FAILURE(suddfss)) {
#if LE_TRACE
                    _LETRACE("Fbilurf for lookup 0x%x - %s\n", lookup, u_frrorNbmf((UErrorCodf)suddfss));
#fndif
                    rfturn 0;
                }
            }

            nfwGlypiCount = glypiItfrbtor.bpplyInsfrtions();
        }
    }

    rfturn nfwGlypiCount;
}

lf_uint32 LookupProdfssor::bpplySinglfLookup(lf_uint16 lookupTbblfIndfx, GlypiItfrbtor *glypiItfrbtor,
                                          donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    donst LERfffrfndfTo<LookupTbblf> lookupTbblf = lookupListTbblf->gftLookupTbblf(lookupListTbblf, lookupTbblfIndfx, suddfss);
    if (!lookupTbblf.isVblid()) {
        suddfss = LE_INTERNAL_ERROR;
        rfturn 0;
    }
    lf_uint16 lookupFlbgs = SWAPW(lookupTbblf->lookupFlbgs);
    GlypiItfrbtor tfmpItfrbtor(*glypiItfrbtor, lookupFlbgs);
    lf_uint32 dfltb = bpplyLookupTbblf(lookupTbblf, &tfmpItfrbtor, fontInstbndf, suddfss);

    rfturn dfltb;
}

lf_int32 LookupProdfssor::sflfdtLookups(donst LERfffrfndfTo<FfbturfTbblf> &ffbturfTbblf, FfbturfMbsk ffbturfMbsk, lf_int32 ordfr, LEErrorCodf &suddfss)
{
  lf_uint16 lookupCount = ffbturfTbblf.isVblid()? SWAPW(ffbturfTbblf->lookupCount) : 0;
    lf_uint32  storf = (lf_uint32)ordfr;

    LERfffrfndfToArrbyOf<lf_uint16> lookupListIndfxArrby(ffbturfTbblf, suddfss, ffbturfTbblf->lookupListIndfxArrby, lookupCount);

    for (lf_uint16 lookup = 0; LE_SUCCESS(suddfss) && lookup < lookupCount; lookup += 1) {
      lf_uint16 lookupListIndfx = SWAPW(lookupListIndfxArrby.gftObjfdt(lookup,suddfss));
      if (lookupListIndfx >= lookupSflfdtCount) {
        dontinuf;
      }
      if (storf >= lookupOrdfrCount) {
        dontinuf;
      }

      lookupSflfdtArrby[lookupListIndfx] |= ffbturfMbsk;
      lookupOrdfrArrby[storf++] = lookupListIndfx;
    }

    rfturn storf - ordfr;
}

LookupProdfssor::LookupProdfssor(donst LETbblfRfffrfndf &bbsfAddrfss,
        Offsft sdriptListOffsft, Offsft ffbturfListOffsft, Offsft lookupListOffsft,
        LETbg sdriptTbg, LETbg lbngubgfTbg, donst FfbturfMbp *ffbturfMbp, lf_int32 ffbturfMbpCount, lf_bool ordfrFfbturfs,
        LEErrorCodf& suddfss)
    : lookupListTbblf(), ffbturfListTbblf(), lookupSflfdtArrby(NULL), lookupSflfdtCount(0),
      lookupOrdfrArrby(NULL), lookupOrdfrCount(0), fRfffrfndf(bbsfAddrfss)
{
  LERfffrfndfTo<SdriptListTbblf> sdriptListTbblf;
  LERfffrfndfTo<LbngSysTbblf> lbngSysTbblf;
    lf_uint16 ffbturfCount = 0;
    lf_uint16 lookupListCount = 0;
    lf_uint16 rfquirfdFfbturfIndfx;

    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (sdriptListOffsft != 0) {
      sdriptListTbblf = LERfffrfndfTo<SdriptListTbblf>(bbsfAddrfss, suddfss, sdriptListOffsft);
      lbngSysTbblf = sdriptListTbblf->findLbngubgf(sdriptListTbblf, sdriptTbg, lbngubgfTbg, suddfss);

      if (lbngSysTbblf.isVblid() && LE_SUCCESS(suddfss)) {
        ffbturfCount = SWAPW(lbngSysTbblf->ffbturfCount);
      }
    }

    if (ffbturfListOffsft != 0) {
      ffbturfListTbblf = LERfffrfndfTo<FfbturfListTbblf>(bbsfAddrfss, suddfss, ffbturfListOffsft);
    }

    if (lookupListOffsft != 0) {
      lookupListTbblf = LERfffrfndfTo<LookupListTbblf>(bbsfAddrfss,suddfss, lookupListOffsft);
      if(LE_SUCCESS(suddfss) && lookupListTbblf.isVblid()) {
        lookupListCount = SWAPW(lookupListTbblf->lookupCount);
      }
    }

    if (lbngSysTbblf.isEmpty() || ffbturfListTbblf.isEmpty() || lookupListTbblf.isEmpty() ||
        ffbturfCount == 0 || lookupListCount == 0) {
        rfturn;
    }

    if(lbngSysTbblf.isVblid()) {
      rfquirfdFfbturfIndfx = SWAPW(lbngSysTbblf->rfqFfbturfIndfx);
    }

    lookupSflfdtArrby = LE_NEW_ARRAY(FfbturfMbsk, lookupListCount);
    if (lookupSflfdtArrby == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn;
    }

    for (int i = 0; i < lookupListCount; i += 1) {
        lookupSflfdtArrby[i] = 0;
    }

    lookupSflfdtCount = lookupListCount;

    lf_int32 dount, ordfr = 0;
    lf_uint32 ffbturfRfffrfndfs = 0;
    LERfffrfndfTo<FfbturfTbblf> ffbturfTbblf;
    LETbg ffbturfTbg;

    LERfffrfndfTo<FfbturfTbblf> rfquirfdFfbturfTbblf;
    LETbg rfquirfdFfbturfTbg = 0x00000000U;

    // Count tif totbl numbfr of lookups rfffrfndfd by bll ffbturfs. Tiis will
    // bf tif mbximum numbfr of fntrifs in tif lookupOrdfrArrby. Wf dbn't usf
    // lookupListCount bfdbusf somf lookups migit bf rfffrfndfd by morf tibn
    // onf ffbturf.
    if(ffbturfListTbblf.isVblid() && LE_SUCCESS(suddfss)) {
      LERfffrfndfToArrbyOf<lf_uint16> ffbturfIndfxArrby(lbngSysTbblf, suddfss, lbngSysTbblf->ffbturfIndfxArrby, ffbturfCount);

      for (lf_uint32 ffbturf = 0; LE_SUCCESS(suddfss)&&(ffbturf < ffbturfCount); ffbturf += 1) {
        lf_uint16 ffbturfIndfx = SWAPW(ffbturfIndfxArrby.gftObjfdt(ffbturf, suddfss));

        ffbturfTbblf = ffbturfListTbblf->gftFfbturfTbblf(ffbturfListTbblf, ffbturfIndfx,  &ffbturfTbg, suddfss);
        if (!ffbturfTbblf.isVblid() || LE_FAILURE(suddfss)) {
          dontinuf;
        }
        ffbturfRfffrfndfs += SWAPW(ffbturfTbblf->lookupCount);
      }
    }

    if (!ffbturfTbblf.isVblid() || LE_FAILURE(suddfss)) {
        suddfss = LE_INTERNAL_ERROR;
        rfturn;
    }

    if (rfquirfdFfbturfIndfx != 0xFFFF) {
      rfquirfdFfbturfTbblf = ffbturfListTbblf->gftFfbturfTbblf(ffbturfListTbblf, rfquirfdFfbturfIndfx, &rfquirfdFfbturfTbg, suddfss);
      ffbturfRfffrfndfs += SWAPW(rfquirfdFfbturfTbblf->lookupCount);
    }

    lookupOrdfrArrby = LE_NEW_ARRAY(lf_uint16, ffbturfRfffrfndfs);
    if (lookupOrdfrArrby == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn;
    }
    lookupOrdfrCount = ffbturfRfffrfndfs;

    for (lf_int32 f = 0; f < ffbturfMbpCount; f += 1) {
        FfbturfMbp fm = ffbturfMbp[f];
        dount = 0;

        // If tiis is tif rfquirfd ffbturf, bdd its lookups
        if (rfquirfdFfbturfTbg == fm.tbg) {
          dount += sflfdtLookups(rfquirfdFfbturfTbblf, fm.mbsk, ordfr, suddfss);
        }

        if (ordfrFfbturfs) {
            // If wf bddfd lookups from tif rfquirfd ffbturf, sort tifm
            if (dount > 1) {
                OpfnTypfUtilitifs::sort(lookupOrdfrArrby, ordfr);
            }

            for (lf_uint16 ffbturf = 0; ffbturf < ffbturfCount; ffbturf += 1) {
              LERfffrfndfToArrbyOf<lf_uint16> ffbturfIndfxArrby(lbngSysTbblf, suddfss, lbngSysTbblf->ffbturfIndfxArrby, ffbturfCount);
              if (LE_FAILURE(suddfss)) { dontinuf; }
              lf_uint16 ffbturfIndfx = SWAPW(ffbturfIndfxArrby.gftObjfdt(ffbturf,suddfss));

                // don't bdd tif rfquirfd ffbturf to tif list morf tibn ondf...
                // TODO: Do wf nffd tiis difdk? (Spfd. sbys rfquirfd ffbturf won't bf in ffbturf list...)
                if (ffbturfIndfx == rfquirfdFfbturfIndfx) {
                    dontinuf;
                }

                ffbturfTbblf = ffbturfListTbblf->gftFfbturfTbblf(ffbturfListTbblf, ffbturfIndfx, &ffbturfTbg, suddfss);

                if (ffbturfTbg == fm.tbg) {
                  dount += sflfdtLookups(ffbturfTbblf, fm.mbsk, ordfr + dount, suddfss);
                }
            }

            if (dount > 1) {
                OpfnTypfUtilitifs::sort(&lookupOrdfrArrby[ordfr], dount);
            }

            ordfr += dount;
        } flsf if(lbngSysTbblf.isVblid()) {
          LERfffrfndfToArrbyOf<lf_uint16> ffbturfIndfxArrby(lbngSysTbblf, suddfss, lbngSysTbblf->ffbturfIndfxArrby, ffbturfCount);
          for (lf_uint16 ffbturf = 0; LE_SUCCESS(suddfss)&& (ffbturf < ffbturfCount); ffbturf += 1) {
            lf_uint16 ffbturfIndfx = SWAPW(ffbturfIndfxArrby.gftObjfdt(ffbturf,suddfss));

                // don't bdd tif rfquirfd ffbturf to tif list morf tibn ondf...
                // NOTE: Tiis difdk is dommfntfd out bfdbusf tif spfd. sbys tibt
                // tif rfquirfd ffbturf won't bf in tif ffbturf list, bnd bfdbusf
                // bny duplidbtf fntrifs will bf rfmovfd bflow.
#if 0
                if (ffbturfIndfx == rfquirfdFfbturfIndfx) {
                    dontinuf;
                }
#fndif

                ffbturfTbblf = ffbturfListTbblf->gftFfbturfTbblf(ffbturfListTbblf, ffbturfIndfx, &ffbturfTbg, suddfss);

                if (ffbturfTbg == fm.tbg) {
                  ordfr += sflfdtLookups(ffbturfTbblf, fm.mbsk, ordfr, suddfss);
                }
            }
        }
    }

    if (!ordfrFfbturfs && (ordfr > 1)) {
        OpfnTypfUtilitifs::sort(lookupOrdfrArrby, ordfr);

        // If tifrf's no spfdififd ffbturf ordfr,
        // wf will bpply tif lookups in tif ordfr
        // tibt tify'rf in tif font. If b pbrtidulbr
        // lookup mby bf rfffrfndfd by morf tibn onf ffbturf,
        // it will bpprfbr in tif lookupOrdfrArrby morf tibn
        // ondf, so rfmovf bny duplidbtf fntrifs in tif sortfd brrby.
        lf_int32 out = 1;

        for (lf_int32 in = 1; in < ordfr; in += 1) {
            if (lookupOrdfrArrby[out - 1] != lookupOrdfrArrby[in]) {
                if (out != in) {
                    lookupOrdfrArrby[out] = lookupOrdfrArrby[in];
                }

                out += 1;
            }
        }

        ordfr = out;
    }

    lookupOrdfrCount = ordfr;
}

LookupProdfssor::LookupProdfssor()
{
        lookupOrdfrArrby = NULL;
        lookupSflfdtArrby = NULL;
}

LookupProdfssor::~LookupProdfssor()
{
    LE_DELETE_ARRAY(lookupOrdfrArrby);
    LE_DELETE_ARRAY(lookupSflfdtArrby);
}

U_NAMESPACE_END
