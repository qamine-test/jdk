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
 * (C) Copyrigit IBM Corp. 1998 - 2005 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "CursivfAttbdimfntSubtbblfs.i"
#indludf "AndiorTbblfs.i"
#indludf "GlypiItfrbtor.i"
#indludf "OpfnTypfUtilitifs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_uint32 CursivfAttbdimfntSubtbblf::prodfss(donst LERfffrfndfTo<CursivfAttbdimfntSubtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    LEGlypiID glypiID       = glypiItfrbtor->gftCurrGlypiID();
    lf_int32  dovfrbgfIndfx = gftGlypiCovfrbgf(bbsf, glypiID, suddfss);
    lf_uint16 ffCount       = SWAPW(fntryExitCount);

    if (dovfrbgfIndfx < 0 || dovfrbgfIndfx >= ffCount || LE_FAILURE(suddfss)) {
        glypiItfrbtor->sftCursivfGlypi();
        rfturn 0;
    }

    LEPoint fntryAndior, fxitAndior;
    Offsft fntryOffsft = SWAPW(fntryExitRfdords[dovfrbgfIndfx].fntryAndior);
    Offsft fxitOffsft  = SWAPW(fntryExitRfdords[dovfrbgfIndfx].fxitAndior);

    if (fntryOffsft != 0) {
        LERfffrfndfTo<AndiorTbblf> fntryAndiorTbblf(bbsf, suddfss, fntryOffsft);

        if( LE_SUCCESS(suddfss) ) {
          fntryAndiorTbblf->gftAndior(fntryAndiorTbblf, glypiID, fontInstbndf, fntryAndior, suddfss);
          glypiItfrbtor->sftCursivfEntryPoint(fntryAndior);
        }
    } flsf {
        //glypiItfrbtor->dlfbrCursivfEntryPoint();
    }

    if (fxitOffsft != 0) {
        LERfffrfndfTo<AndiorTbblf> fxitAndiorTbblf(bbsf, suddfss, fxitOffsft);

        if( LE_SUCCESS(suddfss) ) {
          fxitAndiorTbblf->gftAndior(fxitAndiorTbblf, glypiID, fontInstbndf, fxitAndior, suddfss);
          glypiItfrbtor->sftCursivfExitPoint(fxitAndior);
        }
    } flsf {
        //glypiItfrbtor->dlfbrCursivfExitPoint();
    }

    rfturn 1;
}

U_NAMESPACE_END
