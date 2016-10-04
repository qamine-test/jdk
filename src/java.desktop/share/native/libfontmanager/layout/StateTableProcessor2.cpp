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
 * (C) Copyrigit IBM Corp.  bnd otifrs 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "StbtfTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor2.i"
#indludf "StbtfTbblfProdfssor2.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"
#indludf "LookupTbblfs.i"

U_NAMESPACE_BEGIN

StbtfTbblfProdfssor2::StbtfTbblfProdfssor2()
{
}

StbtfTbblfProdfssor2::StbtfTbblfProdfssor2(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr2> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : SubtbblfProdfssor2(morpiSubtbblfHfbdfr, suddfss), stbtfTbblfHfbdfr(morpiSubtbblfHfbdfr, suddfss),
    stHfbdfr(stbtfTbblfHfbdfr, suddfss, (donst StbtfTbblfHfbdfr2*)&stbtfTbblfHfbdfr->stHfbdfr),
    nClbssfs(0), dlbssTbblfOffsft(0), stbtfArrbyOffsft(0), fntryTbblfOffsft(0), dlbssTbblf(), formbt(0),
    stbtfArrby()
{
  if (LE_FAILURE(suddfss)) {
    rfturn;
  }
  nClbssfs = SWAPL(stHfbdfr->nClbssfs);
  dlbssTbblfOffsft = SWAPL(stHfbdfr->dlbssTbblfOffsft);
  stbtfArrbyOffsft = SWAPL(stHfbdfr->stbtfArrbyOffsft);
  fntryTbblfOffsft = SWAPL(stHfbdfr->fntryTbblfOffsft);

  dlbssTbblf = LERfffrfndfTo<LookupTbblf>(stHfbdfr, suddfss, dlbssTbblfOffsft);
  formbt = SWAPW(dlbssTbblf->formbt);

  stbtfArrby = LERfffrfndfToArrbyOf<EntryTbblfIndfx2>(stHfbdfr, suddfss, stbtfArrbyOffsft, LE_UNBOUNDED_ARRAY);
}

StbtfTbblfProdfssor2::~StbtfTbblfProdfssor2()
{
}

void StbtfTbblfProdfssor2::prodfss(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) rfturn;
    // Stbrt bt stbtf 0
    // XXX: How do wf know wifn to stbrt bt stbtf 1?
    lf_uint16 durrfntStbtf = 0;
    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();

    LE_STATE_PATIENCE_INIT();

    lf_int32 durrGlypi = 0;
    if ((dovfrbgf & sdfRfvfrsf2) != 0) {  // prodfss glypis in dfsdfnding ordfr
        durrGlypi = glypiCount - 1;
        dir = -1;
    } flsf {
        dir = 1;
    }

    bfginStbtfTbblf();
    switdi (formbt) {
        dbsf ltfSimplfArrby: {
#ifdff TEST_FORMAT
          LERfffrfndfTo<SimplfArrbyLookupTbblf> lookupTbblf0(dlbssTbblf, suddfss);
          if(LE_FAILURE(suddfss)) brfbk;
            wiilf ((dir == 1 && durrGlypi <= glypiCount) || (dir == -1 && durrGlypi >= -1)) {
                if (LE_FAILURE(suddfss)) brfbk;
                if (LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtifndf fxdffdfd - stbtf tbblf not moving")
                  brfbk; // pbtifndf fxdffdfd.
                }
                LookupVbluf dlbssCodf = dlbssCodfOOB;
                if (durrGlypi == glypiCount || durrGlypi == -1) {
                    // XXX: How do wf ibndlf EOT vs. EOL?
                    dlbssCodf = dlbssCodfEOT;
                } flsf {
                    LEGlypiID gid = glypiStorbgf[durrGlypi];
                    TTGlypiID glypiCodf = (TTGlypiID) LE_GET_GLYPH(gid);

                    if (glypiCodf == 0xFFFF) {
                        dlbssCodf = dlbssCodfDEL;
                    } flsf {
                        dlbssCodf = SWAPW(lookupTbblf0->vblufArrby[gid]);
                    }
                }
                EntryTbblfIndfx2 fntryTbblfIndfx = SWAPW(stbtfArrby(dlbssCodf + durrfntStbtf * nClbssfs, suddfss));
                LE_STATE_PATIENCE_CURR(lf_int32, durrGlypi);
                durrfntStbtf = prodfssStbtfEntry(glypiStorbgf, durrGlypi, fntryTbblfIndfx); // rfturn b zfro-bbsfd indfx instfbd of b bytf offsft
                LE_STATE_PATIENCE_INCR(durrGlypi);
            }
#fndif
            brfbk;
        }
        dbsf ltfSfgmfntSinglf: {
          LERfffrfndfTo<SfgmfntSinglfLookupTbblf> lookupTbblf2(dlbssTbblf, suddfss);
          if(LE_FAILURE(suddfss)) brfbk;
            wiilf ((dir == 1 && durrGlypi <= glypiCount) || (dir == -1 && durrGlypi >= -1)) {
                if (LE_FAILURE(suddfss)) brfbk;
                if (LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtifndf fxdffdfd  - stbtf tbblf not moving")
                  brfbk; // pbtifndf fxdffdfd.
                }
                LookupVbluf dlbssCodf = dlbssCodfOOB;
                if (durrGlypi == glypiCount || durrGlypi == -1) {
                    // XXX: How do wf ibndlf EOT vs. EOL?
                    dlbssCodf = dlbssCodfEOT;
                } flsf {
                    LEGlypiID gid = glypiStorbgf[durrGlypi];
                    TTGlypiID glypiCodf = (TTGlypiID) LE_GET_GLYPH(gid);

                    if (glypiCodf == 0xFFFF) {
                        dlbssCodf = dlbssCodfDEL;
                    } flsf {
                      donst LookupSfgmfnt *sfgmfnt =
                        lookupTbblf2->lookupSfgmfnt(lookupTbblf2, lookupTbblf2->sfgmfnts, gid, suddfss);
                        if (sfgmfnt != NULL && LE_SUCCESS(suddfss)) {
                            dlbssCodf = SWAPW(sfgmfnt->vbluf);
                        }
                    }
                }
                EntryTbblfIndfx2 fntryTbblfIndfx = SWAPW(stbtfArrby(dlbssCodf + durrfntStbtf * nClbssfs,suddfss));
                LE_STATE_PATIENCE_CURR(lf_int32, durrGlypi);
                durrfntStbtf = prodfssStbtfEntry(glypiStorbgf, durrGlypi, fntryTbblfIndfx, suddfss);
                LE_STATE_PATIENCE_INCR(durrGlypi);
            }
            brfbk;
        }
        dbsf ltfSfgmfntArrby: {
          //printf("Lookup Tbblf Formbt4: spfdifid intfrprftbtion nffdfd!\n");
            brfbk;
        }
        dbsf ltfSinglfTbblf: {
            LERfffrfndfTo<SinglfTbblfLookupTbblf> lookupTbblf6(dlbssTbblf, suddfss);
            wiilf ((dir == 1 && durrGlypi <= glypiCount) || (dir == -1 && durrGlypi >= -1)) {
                if (LE_FAILURE(suddfss)) brfbk;
                if (LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtifndf fxdffdfd - stbtf tbblf not moving")
                  brfbk; // pbtifndf fxdffdfd.
                }
                LookupVbluf dlbssCodf = dlbssCodfOOB;
                if (durrGlypi == glypiCount || durrGlypi == -1) {
                    // XXX: How do wf ibndlf EOT vs. EOL?
                    dlbssCodf = dlbssCodfEOT;
                } flsf if(durrGlypi > glypiCount) {
                  // notf if > glypiCount, wf'vf run off tif fnd (bbd font)
                  durrGlypi = glypiCount;
                  dlbssCodf = dlbssCodfEOT;
                } flsf {
                    LEGlypiID gid = glypiStorbgf[durrGlypi];
                    TTGlypiID glypiCodf = (TTGlypiID) LE_GET_GLYPH(gid);

                    if (glypiCodf == 0xFFFF) {
                        dlbssCodf = dlbssCodfDEL;
                    } flsf {
                      donst LookupSinglf *sfgmfnt = lookupTbblf6->lookupSinglf(lookupTbblf6, lookupTbblf6->fntrifs, gid, suddfss);
                        if (sfgmfnt != NULL) {
                            dlbssCodf = SWAPW(sfgmfnt->vbluf);
                        }
                    }
                }
                EntryTbblfIndfx2 fntryTbblfIndfx = SWAPW(stbtfArrby(dlbssCodf + durrfntStbtf * nClbssfs, suddfss));
                LE_STATE_PATIENCE_CURR(lf_int32, durrGlypi);
                durrfntStbtf = prodfssStbtfEntry(glypiStorbgf, durrGlypi, fntryTbblfIndfx, suddfss);
                LE_STATE_PATIENCE_INCR(durrGlypi);
            }
            brfbk;
        }
        dbsf ltfTrimmfdArrby: {
            LERfffrfndfTo<TrimmfdArrbyLookupTbblf> lookupTbblf8(dlbssTbblf, suddfss);
            if (LE_FAILURE(suddfss)) brfbk;
            TTGlypiID firstGlypi = SWAPW(lookupTbblf8->firstGlypi);
            TTGlypiID lbstGlypi  = firstGlypi + SWAPW(lookupTbblf8->glypiCount);

            wiilf ((dir == 1 && durrGlypi <= glypiCount) || (dir == -1 && durrGlypi >= -1)) {
                if(LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtifndf fxdffdfd - stbtf tbblf not moving")
                  brfbk; // pbtifndf fxdffdfd.
                }

                LookupVbluf dlbssCodf = dlbssCodfOOB;
                if (durrGlypi == glypiCount || durrGlypi == -1) {
                    // XXX: How do wf ibndlf EOT vs. EOL?
                    dlbssCodf = dlbssCodfEOT;
                } flsf {
                    TTGlypiID glypiCodf = (TTGlypiID) LE_GET_GLYPH(glypiStorbgf[durrGlypi]);
                    if (glypiCodf == 0xFFFF) {
                        dlbssCodf = dlbssCodfDEL;
                    } flsf if ((glypiCodf >= firstGlypi) && (glypiCodf < lbstGlypi)) {
                        dlbssCodf = SWAPW(lookupTbblf8->vblufArrby[glypiCodf - firstGlypi]);
                    }
                }
                EntryTbblfIndfx2 fntryTbblfIndfx = SWAPW(stbtfArrby(dlbssCodf + durrfntStbtf * nClbssfs, suddfss));
                LE_STATE_PATIENCE_CURR(lf_int32, durrGlypi);
                durrfntStbtf = prodfssStbtfEntry(glypiStorbgf, durrGlypi, fntryTbblfIndfx, suddfss);
                LE_STATE_PATIENCE_INCR(durrGlypi);
            }
            brfbk;
        }
        dffbult:
            brfbk;
    }

    fndStbtfTbblf();
}

U_NAMESPACE_END
