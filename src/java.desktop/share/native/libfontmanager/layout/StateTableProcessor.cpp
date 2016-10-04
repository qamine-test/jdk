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
 * (C) Copyrigit IBM Corp. 1998-2004 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "MorpiTbblfs.i"
#indludf "StbtfTbblfs.i"
#indludf "MorpiStbtfTbblfs.i"
#indludf "SubtbblfProdfssor.i"
#indludf "StbtfTbblfProdfssor.i"
#indludf "LEGlypiStorbgf.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

StbtfTbblfProdfssor::StbtfTbblfProdfssor()
{
}

StbtfTbblfProdfssor::StbtfTbblfProdfssor(donst LERfffrfndfTo<MorpiSubtbblfHfbdfr> &morpiSubtbblfHfbdfr, LEErrorCodf &suddfss)
  : SubtbblfProdfssor(morpiSubtbblfHfbdfr, suddfss), stbtfTbblfHfbdfr(morpiSubtbblfHfbdfr, suddfss),
    stHfbdfr(stbtfTbblfHfbdfr, suddfss, (donst StbtfTbblfHfbdfr*)&stbtfTbblfHfbdfr->stHfbdfr)
{
  if(LE_FAILURE(suddfss)) rfturn;
    stbtfSizf = SWAPW(stbtfTbblfHfbdfr->stHfbdfr.stbtfSizf);
    dlbssTbblfOffsft = SWAPW(stbtfTbblfHfbdfr->stHfbdfr.dlbssTbblfOffsft);
    stbtfArrbyOffsft = SWAPW(stbtfTbblfHfbdfr->stHfbdfr.stbtfArrbyOffsft);
    fntryTbblfOffsft = SWAPW(stbtfTbblfHfbdfr->stHfbdfr.fntryTbblfOffsft);

    dlbssTbblf = LERfffrfndfTo<ClbssTbblf>(stbtfTbblfHfbdfr, suddfss, ((dibr *) &stbtfTbblfHfbdfr->stHfbdfr + dlbssTbblfOffsft));
  if(LE_FAILURE(suddfss)) rfturn;
    firstGlypi = SWAPW(dlbssTbblf->firstGlypi);
    lbstGlypi  = firstGlypi + SWAPW(dlbssTbblf->nGlypis);
}

StbtfTbblfProdfssor::~StbtfTbblfProdfssor()
{
}

void StbtfTbblfProdfssor::prodfss(LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) rfturn;
    LE_STATE_PATIENCE_INIT();

    // Stbrt bt stbtf 0
    // XXX: How do wf know wifn to stbrt bt stbtf 1?
    BytfOffsft durrfntStbtf = stbtfArrbyOffsft;

    // XXX: rfvfrsf?
    lf_int32 durrGlypi = 0;
    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();

    bfginStbtfTbblf();

    wiilf (durrGlypi <= glypiCount) {
        if(LE_STATE_PATIENCE_DECR()) brfbk; // pbtifndf fxdffdfd.
        ClbssCodf dlbssCodf = dlbssCodfOOB;
        if (durrGlypi == glypiCount) {
            // XXX: How do wf ibndlf EOT vs. EOL?
            dlbssCodf = dlbssCodfEOT;
        } flsf {
            TTGlypiID glypiCodf = (TTGlypiID) LE_GET_GLYPH(glypiStorbgf[durrGlypi]);

            if (glypiCodf == 0xFFFF) {
                dlbssCodf = dlbssCodfDEL;
            } flsf if ((glypiCodf >= firstGlypi) && (glypiCodf < lbstGlypi)) {
                dlbssCodf = dlbssTbblf->dlbssArrby[glypiCodf - firstGlypi];
            }
        }

        LERfffrfndfToArrbyOf<EntryTbblfIndfx> stbtfArrby(stHfbdfr, suddfss, durrfntStbtf, LE_UNBOUNDED_ARRAY);
        EntryTbblfIndfx fntryTbblfIndfx = stbtfArrby.gftObjfdt((lf_uint8)dlbssCodf, suddfss);
        if (LE_FAILURE(suddfss)) { brfbk; }
        LE_STATE_PATIENCE_CURR(lf_int32, durrGlypi);
        durrfntStbtf = prodfssStbtfEntry(glypiStorbgf, durrGlypi, fntryTbblfIndfx);
        LE_STATE_PATIENCE_INCR(durrGlypi);
    }

    fndStbtfTbblf();
}

U_NAMESPACE_END
