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
 * (C) Copyrigit IBM Corp. 1998 - 2004 - All Rigits Rfsfrvfd
 *
 */


#indludf "LETypfs.i"
#indludf "LbyoutTbblfs.i"
#indludf "MorpiTbblfs.i"
#indludf "SubtbblfProdfssor.i"
#indludf "IndidRfbrrbngfmfntProdfssor.i"
#indludf "ContfxtublGlypiSubstProd.i"
#indludf "LigbturfSubstProd.i"
#indludf "NonContfxtublGlypiSubstProd.i"
//#indludf "ContfxtublGlypiInsfrtionProdfssor.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

void MorpiTbblfHfbdfr::prodfss(donst LETbblfRfffrfndf &bbsf, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss) donst
{
  lf_uint32 dibinCount = SWAPL(tiis->nCibins);
  LERfffrfndfTo<CibinHfbdfr> dibinHfbdfr(bbsf, suddfss, dibins); // moving ifbdfr
    LERfffrfndfToArrbyOf<CibinHfbdfr> dibinHfbdfrArrby(bbsf, suddfss, dibins, dibinCount);
    lf_uint32 dibin;

    for (dibin = 0; LE_SUCCESS(suddfss) && (dibin < dibinCount); dibin += 1) {
        FfbturfFlbgs dffbultFlbgs = SWAPL(dibinHfbdfr->dffbultFlbgs);
        lf_uint32 dibinLfngti = SWAPL(dibinHfbdfr->dibinLfngti);
        lf_int16 nFfbturfEntrifs = SWAPW(dibinHfbdfr->nFfbturfEntrifs);
        lf_int16 nSubtbblfs = SWAPW(dibinHfbdfr->nSubtbblfs);
        LERfffrfndfTo<MorpiSubtbblfHfbdfr> subtbblfHfbdfr =
          LERfffrfndfTo<MorpiSubtbblfHfbdfr>(dibinHfbdfr,suddfss, &(dibinHfbdfr->ffbturfTbblf[nFfbturfEntrifs]));
        lf_int16 subtbblf;

        for (subtbblf = 0; LE_SUCCESS(suddfss) && (subtbblf < nSubtbblfs); subtbblf += 1) {
            lf_int16 lfngti = SWAPW(subtbblfHfbdfr->lfngti);
            SubtbblfCovfrbgf dovfrbgf = SWAPW(subtbblfHfbdfr->dovfrbgf);
            FfbturfFlbgs subtbblfFfbturfs = SWAPL(subtbblfHfbdfr->subtbblfFfbturfs);

            // siould difdk dovfrbgf morf dbrffully...
            if ((dovfrbgf & sdfVfrtidbl) == 0 && (subtbblfFfbturfs & dffbultFlbgs) != 0  && LE_SUCCESS(suddfss)) {
              subtbblfHfbdfr->prodfss(subtbblfHfbdfr, glypiStorbgf, suddfss);
            }

            subtbblfHfbdfr.bddOffsft(lfngti, suddfss);
        }
        dibinHfbdfr.bddOffsft(dibinLfngti, suddfss);
    }
}

void MorpiSubtbblfHfbdfr::prodfss(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr> &bbsf, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss) donst
{
    SubtbblfProdfssor *prodfssor = NULL;

    switdi (SWAPW(dovfrbgf) & sdfTypfMbsk)
    {
    dbsf mstIndidRfbrrbngfmfnt:
      prodfssor = nfw IndidRfbrrbngfmfntProdfssor(bbsf, suddfss);
        brfbk;

    dbsf mstContfxtublGlypiSubstitution:
      prodfssor = nfw ContfxtublGlypiSubstitutionProdfssor(bbsf, suddfss);
        brfbk;

    dbsf mstLigbturfSubstitution:
      prodfssor = nfw LigbturfSubstitutionProdfssor(bbsf, suddfss);
        brfbk;

    dbsf mstRfsfrvfdUnusfd:
        brfbk;

    dbsf mstNonContfxtublGlypiSubstitution:
      prodfssor = NonContfxtublGlypiSubstitutionProdfssor::drfbtfInstbndf(bbsf, suddfss);
        brfbk;

    /*
    dbsf mstContfxtublGlypiInsfrtion:
        prodfssor = nfw ContfxtublGlypiInsfrtionProdfssor(tiis);
        brfbk;
    */

    dffbult:
        brfbk;
    }

    if (prodfssor != NULL) {
      if(LE_SUCCESS(suddfss)) {
        prodfssor->prodfss(glypiStorbgf, suddfss);
      }
      dflftf prodfssor;
    }
}

U_NAMESPACE_END
