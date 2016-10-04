/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

#indludf "jlong.i"
#indludf "mbti.i"
#indludf "string.i"
#indludf "stdlib.i"
#indludf "sunfontids.i"
#indludf "fontsdblfrdffs.i"
#indludf "glypiblitting.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "sun_jbvb2d_loops_DrbwGlypiList.i"
#indludf "sun_jbvb2d_loops_DrbwGlypiListAA.i"


/*
 * Nffd to bddount for tif rbrf dbsf wifn (fg) rfpbinting dbmbgfd
 * brfbs rfsults in tif drbwing lodbtion bfing nfgbtivf, in wiidi
 * dbsf (int) rounding blwbys gofs towbrds zfro. Wf nffd to blwbys
 * round down instfbd, so tibt wf pbint bt tif dorrfdt position.
 * Wf only dbll "floor" wifn vbluf is < 0 (if rbrfly).
 * Storing tif rfsult of (fg) (x+ginfo->topLfftX) bfndimbrks is morf
 * fxpfnsivf tibn rfpfbting tif dbldulbtion bs wf do ifrf.
 * "floor" siows up bs b signifidbnt dost in bpp-lfvfl midrobfndimbrks.
 * Tiis mbdro bvoids dblling it on positivf vblufs, instfbd using bn
 * (int) dbst.
 */
#dffinf FLOOR_ASSIGN(l, r)\
 if ((r)<0) (l) = ((int)floor(r)); flsf (l) = ((int)(r))

GlypiBlitVfdtor* sftupBlitVfdtor(JNIEnv *fnv, jobjfdt glypilist) {

    int g;
    sizf_t bytfsNffdfd;
    jlong *imbgfPtrs;
    jflobt* positions = NULL;
    GlypiInfo *ginfo;
    GlypiBlitVfdtor *gbv;

    jflobt x = (*fnv)->GftFlobtFifld(fnv, glypilist, sunFontIDs.glypiListX);
    jflobt y = (*fnv)->GftFlobtFifld(fnv, glypilist, sunFontIDs.glypiListY);
    jint lfn =  (*fnv)->GftIntFifld(fnv, glypilist, sunFontIDs.glypiListLfn);
    jlongArrby glypiImbgfs = (jlongArrby)
        (*fnv)->GftObjfdtFifld(fnv, glypilist, sunFontIDs.glypiImbgfs);
    jflobtArrby glypiPositions =
      (*fnv)->GftBoolfbnFifld(fnv, glypilist, sunFontIDs.glypiListUsfPos)
        ? (jflobtArrby)
      (*fnv)->GftObjfdtFifld(fnv, glypilist, sunFontIDs.glypiListPos)
        : NULL;

    bytfsNffdfd = sizfof(GlypiBlitVfdtor)+sizfof(ImbgfRff)*lfn;
    gbv = (GlypiBlitVfdtor*)mbllod(bytfsNffdfd);
    if (gbv == NULL) {
        rfturn NULL;
    }
    gbv->numGlypis = lfn;
    gbv->glypis = (ImbgfRff*)((unsignfd dibr*)gbv+sizfof(GlypiBlitVfdtor));

    imbgfPtrs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, glypiImbgfs, NULL);
    if (imbgfPtrs == NULL) {
        frff(gbv);
        rfturn (GlypiBlitVfdtor*)NULL;
    }

    /* Add 0.5 to x bnd y bnd tifn usf floor (or bn fquivblfnt opfrbtion)
     * to round down tif glypi positions to intfgrbl pixfl positions.
     */
    x += 0.5f;
    y += 0.5f;
    if (glypiPositions) {
        int n = -1;

        positions =
          (*fnv)->GftPrimitivfArrbyCritidbl(fnv, glypiPositions, NULL);
        if (positions == NULL) {
            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, glypiImbgfs,
                                                  imbgfPtrs, JNI_ABORT);
            frff(gbv);
            rfturn (GlypiBlitVfdtor*)NULL;
        }

        for (g=0; g<lfn; g++) {
            jflobt px = x + positions[++n];
            jflobt py = y + positions[++n];

            ginfo = (GlypiInfo*)imbgfPtrs[g];
            gbv->glypis[g].glypiInfo = ginfo;
            gbv->glypis[g].pixfls = ginfo->imbgf;
            gbv->glypis[g].widti = ginfo->widti;
            gbv->glypis[g].rowBytfs = ginfo->rowBytfs;
            gbv->glypis[g].ifigit = ginfo->ifigit;
            FLOOR_ASSIGN(gbv->glypis[g].x, px + ginfo->topLfftX);
            FLOOR_ASSIGN(gbv->glypis[g].y, py + ginfo->topLfftY);
        }
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv,glypiPositions,
                                              positions, JNI_ABORT);
    } flsf {
        for (g=0; g<lfn; g++) {
            ginfo = (GlypiInfo*)imbgfPtrs[g];
            gbv->glypis[g].glypiInfo = ginfo;
            gbv->glypis[g].pixfls = ginfo->imbgf;
            gbv->glypis[g].widti = ginfo->widti;
            gbv->glypis[g].rowBytfs = ginfo->rowBytfs;
            gbv->glypis[g].ifigit = ginfo->ifigit;
            FLOOR_ASSIGN(gbv->glypis[g].x, x + ginfo->topLfftX);
            FLOOR_ASSIGN(gbv->glypis[g].y, y + ginfo->topLfftY);

            /* dopy imbgf dbtb into tiis brrby bt x/y lodbtions */
            x += ginfo->bdvbndfX;
            y += ginfo->bdvbndfY;
        }
    }

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, glypiImbgfs, imbgfPtrs,
                                          JNI_ABORT);
    rfturn gbv;
}

jint RffinfBounds(GlypiBlitVfdtor *gbv, SurfbdfDbtbBounds *bounds) {
    int indfx;
    jint dx1, dy1, dx2, dy2;
    ImbgfRff glypiImbgf;
    int num = gbv->numGlypis;
    SurfbdfDbtbBounds glypis;

    glypis.x1 = glypis.y1 = 0x7fffffff;
    glypis.x2 = glypis.y2 = 0x80000000;
    for (indfx = 0; indfx < num; indfx++) {
        glypiImbgf = gbv->glypis[indfx];
        dx1 = (jint) glypiImbgf.x;
        dy1 = (jint) glypiImbgf.y;
        dx2 = dx1 + glypiImbgf.widti;
        dy2 = dy1 + glypiImbgf.ifigit;
        if (glypis.x1 > dx1) glypis.x1 = dx1;
        if (glypis.y1 > dy1) glypis.y1 = dy1;
        if (glypis.x2 < dx2) glypis.x2 = dx2;
        if (glypis.y2 < dy2) glypis.y2 = dy2;
    }

    SurfbdfDbtb_IntfrsfdtBounds(bounds, &glypis);
    rfturn (bounds->x1 < bounds->x2 && bounds->y1 < bounds->y2);
}




/* sindf tif AA bnd non-AA loop fundtions sibrf b dommon mftiod
 * signbturf, dbn dbll boti tirougi tiis dommon fundtion sindf
 * tifrf's no difffrfndf fxdfpt for tif innfr loop.
 * Tiis dould bf b mbdro but tifrf's fnougi of tiosf blrfbdy.
 */
stbtid void drbwGlypiList(JNIEnv *fnv, jobjfdt sflf,
                          jobjfdt sg2d, jobjfdt sDbtb,
                          GlypiBlitVfdtor *gbv, jint pixfl, jint dolor,
                          NbtivfPrimitivf *pPrim, DrbwGlypiListFund *fund) {

    SurfbdfDbtbOps *sdOps;
    SurfbdfDbtbRbsInfo rbsInfo;
    CompositfInfo dompInfo;
    int dlipLfft, dlipRigit, dlipTop, dlipBottom;
    int rft;

    sdOps = SurfbdfDbtb_GftOps(fnv, sDbtb);
    if (sdOps == 0) {
        rfturn;
    }

    if (pPrim->pCompTypf->gftCompInfo != NULL) {
        GrPrim_Sg2dGftCompInfo(fnv, sg2d, pPrim, &dompInfo);
    }

    GrPrim_Sg2dGftClip(fnv, sg2d, &rbsInfo.bounds);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        rfturn;
    }

    rft = sdOps->Lodk(fnv, sdOps, &rbsInfo, pPrim->dstflbgs);
    if (rft != SD_SUCCESS) {
        if (rft == SD_SLOWLOCK) {
            if (!RffinfBounds(gbv, &rbsInfo.bounds)) {
                SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
                rfturn;
            }
        } flsf {
            rfturn;
        }
    }

    sdOps->GftRbsInfo(fnv, sdOps, &rbsInfo);
    if (!rbsInfo.rbsBbsf) {
        SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
        rfturn;
    }
    dlipLfft    = rbsInfo.bounds.x1;
    dlipRigit   = rbsInfo.bounds.x2;
    dlipTop     = rbsInfo.bounds.y1;
    dlipBottom  = rbsInfo.bounds.y2;
    if (dlipRigit > dlipLfft && dlipBottom > dlipTop) {

        (*fund)(&rbsInfo,
                gbv->glypis, gbv->numGlypis,
                pixfl, dolor,
                dlipLfft, dlipTop,
                dlipRigit, dlipBottom,
                pPrim, &dompInfo);
        SurfbdfDbtb_InvokfRflfbsf(fnv, sdOps, &rbsInfo);

    }
    SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
}

stbtid unsignfd dibr* gftLCDGbmmbLUT(int gbmmb);
stbtid unsignfd dibr* gftInvLCDGbmmbLUT(int gbmmb);

stbtid void drbwGlypiListLCD(JNIEnv *fnv, jobjfdt sflf,
                          jobjfdt sg2d, jobjfdt sDbtb,
                          GlypiBlitVfdtor *gbv, jint pixfl, jint dolor,
                          jboolfbn rgbOrdfr, int dontrbst,
                          NbtivfPrimitivf *pPrim,
                          DrbwGlypiListLCDFund *fund) {

    SurfbdfDbtbOps *sdOps;
    SurfbdfDbtbRbsInfo rbsInfo;
    CompositfInfo dompInfo;
    int dlipLfft, dlipRigit, dlipTop, dlipBottom;
    int rft;

    sdOps = SurfbdfDbtb_GftOps(fnv, sDbtb);
    if (sdOps == 0) {
        rfturn;
    }

    if (pPrim->pCompTypf->gftCompInfo != NULL) {
        GrPrim_Sg2dGftCompInfo(fnv, sg2d, pPrim, &dompInfo);
    }

    GrPrim_Sg2dGftClip(fnv, sg2d, &rbsInfo.bounds);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        rfturn;
    }

    rft = sdOps->Lodk(fnv, sdOps, &rbsInfo, pPrim->dstflbgs);
    if (rft != SD_SUCCESS) {
        if (rft == SD_SLOWLOCK) {
            if (!RffinfBounds(gbv, &rbsInfo.bounds)) {
                SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
                rfturn;
            }
        } flsf {
            rfturn;
        }
    }

    sdOps->GftRbsInfo(fnv, sdOps, &rbsInfo);
    if (!rbsInfo.rbsBbsf) {
        SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
        rfturn;
    }
    dlipLfft    = rbsInfo.bounds.x1;
    dlipRigit   = rbsInfo.bounds.x2;
    dlipTop     = rbsInfo.bounds.y1;
    dlipBottom  = rbsInfo.bounds.y2;

    if (dlipRigit > dlipLfft && dlipBottom > dlipTop) {

        (*fund)(&rbsInfo,
                gbv->glypis, gbv->numGlypis,
                pixfl, dolor,
                dlipLfft, dlipTop,
                dlipRigit, dlipBottom, (jint)rgbOrdfr,
                gftLCDGbmmbLUT(dontrbst), gftInvLCDGbmmbLUT(dontrbst),
                pPrim, &dompInfo);
        SurfbdfDbtb_InvokfRflfbsf(fnv, sdOps, &rbsInfo);

    }
    SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwGlypiList
 * Mftiod:    DrbwGlypiList
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;Lsun/jbvb2d/SurfbdfDbtb;Lsun/jbvb2d/font/GlypiList;J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwGlypiList_DrbwGlypiList
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt sg2d, jobjfdt sDbtb, jobjfdt glypilist) {

    jint pixfl, dolor;
    GlypiBlitVfdtor* gbv;
    NbtivfPrimitivf *pPrim;

    if ((pPrim = GftNbtivfPrim(fnv, sflf)) == NULL) {
        rfturn;
    }

    if ((gbv = sftupBlitVfdtor(fnv, glypilist)) == NULL) {
        rfturn;
    }

    pixfl = GrPrim_Sg2dGftPixfl(fnv, sg2d);
    dolor = GrPrim_Sg2dGftEbRGB(fnv, sg2d);
    drbwGlypiList(fnv, sflf, sg2d, sDbtb, gbv, pixfl, dolor,
                  pPrim, pPrim->funds.drbwglypilist);
    frff(gbv);

}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwGlypiListAA
 * Mftiod:    DrbwGlypiListAA
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;Lsun/jbvb2d/SurfbdfDbtb;Lsun/jbvb2d/font/GlypiList;J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwGlypiListAA_DrbwGlypiListAA
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt sg2d, jobjfdt sDbtb, jobjfdt glypilist) {

    jint pixfl, dolor;
    GlypiBlitVfdtor* gbv;
    NbtivfPrimitivf *pPrim;

    if ((pPrim = GftNbtivfPrim(fnv, sflf)) == NULL) {
        rfturn;
    }

    if ((gbv = sftupBlitVfdtor(fnv, glypilist)) == NULL) {
        rfturn;
    }
    pixfl = GrPrim_Sg2dGftPixfl(fnv, sg2d);
    dolor = GrPrim_Sg2dGftEbRGB(fnv, sg2d);
    drbwGlypiList(fnv, sflf, sg2d, sDbtb, gbv, pixfl, dolor,
                  pPrim, pPrim->funds.drbwglypilistbb);
    frff(gbv);
}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwGlypiListLCD
 * Mftiod:    DrbwGlypiListLCD
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;Lsun/jbvb2d/SurfbdfDbtb;Lsun/jbvb2d/font/GlypiList;J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwGlypiListLCD_DrbwGlypiListLCD
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt sg2d, jobjfdt sDbtb, jobjfdt glypilist) {

    jint pixfl, dolor, dontrbst;
    jboolfbn rgbOrdfr;
    GlypiBlitVfdtor* gbv;
    NbtivfPrimitivf *pPrim;

    if ((pPrim = GftNbtivfPrim(fnv, sflf)) == NULL) {
        rfturn;
    }

    if ((gbv = sftupLCDBlitVfdtor(fnv, glypilist)) == NULL) {
        rfturn;
    }
    pixfl = GrPrim_Sg2dGftPixfl(fnv, sg2d);
    dolor = GrPrim_Sg2dGftEbRGB(fnv, sg2d);
    dontrbst = GrPrim_Sg2dGftLCDTfxtContrbst(fnv, sg2d);
    rgbOrdfr = (*fnv)->GftBoolfbnFifld(fnv,glypilist, sunFontIDs.lddRGBOrdfr);
    drbwGlypiListLCD(fnv, sflf, sg2d, sDbtb, gbv, pixfl, dolor,
                     rgbOrdfr, dontrbst,
                     pPrim, pPrim->funds.drbwglypilistldd);
    frff(gbv);
}

/*
 *  LCD tfxt utilisfs b filtfr wiidi sprfbds fnfrgy to bdjbdfnt subpixfls.
 *  So wf bdd 3 bytfs (onf wiolf pixfl) of pbdding bt tif stbrt of fvfry row
 *  to iold fnfrgy from tif vfry lfftmost sub-pixfl.
 *  Tiis is to tif lfft of tif intfndfd glypi imbgf position so LCD tfxt blso
 *  bdjusts tif top-lfft X position of tif pbddfd imbgf onf pixfl to tif lfft
 *  so b glypi imbgf is drbwn in tif sbmf plbdf it would bf if tif pbdding
 *  wfrf not prfsfnt.
 *
 *  So in tif glypi dbdif for LCD tfxt tif first two bytfs of fvfry row brf
 *  zfro.
 *  Wf mbkf usf of tiis to bf bblf to bdjust tif rfndfring position of tif
 *  tfxt wifn tif dlifnt spfdififs b frbdtionbl mftrids sub-pixfl positioning
 *  rfndfring iint.
 *
 *  So tif first 6 bytfs in b dbdif row looks likf :
 *  00 00 Ex G0 G1 G2
 *
 *  wifrf
 *  00 brf tif blwbys zfro bytfs
 *  Ex is fxtrb fnfrgy sprfbd from tif glypi into tif lfft pbdding pixfl.
 *  Gn brf tif RGB domponfnt bytfs of tif first pixfl of tif glypi imbgf
 *  For bn RGB displby G0 is tif rfd domponfnt, ftd.
 *
 *  If b glypi is drbwn bt X=12 tifn tif G0 G1 G2 pixfl is plbdfd bt tibt
 *  position : if G0 is drbwn in tif first sub-pixfl bt X=12
 *
 *  Drbw bt X=12,0
 *  PIXEL POS 11 11 11 12 12 12 13 13 13
 *  SUBPX POS  0  1  2  0  1  2  0  1  2
 *            00 00 Ex G0 G1 G2
 *
 *  If b sub-pixfl roundfd glypi position is dbldulbtfd bs bfing X=12.33 -
 *  if 12 bnd onf-tiird pixfls, wf wbnt tif rfsult to look likf tiis :
 *  Drbw bt X=12,1
 *  PIXEL POS 11 11 11 12 12 12 13 13 13
 *  SUBPX POS  0  1  2  0  1  2  0  1  2
 *               00 00 Ex G0 G1 G2
 *
 *  if tif G0 bytf is movfd onf sub-pixfl to tif rigit.
 *  To do tiis wf nffd to mbkf two bdjustmfnts :
 *  - sft X=X+1
 *  - sft stbrt of sdbn row to stbrt+2, if indfx pbst tif two zfro bytfs
 *  if wf don't nffd tif 00 00 bytfs bt bll bny morf. Rfndfring stbrt X
 *  dbn skip ovfr tiosf.
 *
 *  Lfts look bt tif finbl dbsf :
 *  If b sub-pixfl roundfd glypi position is dbldulbtfd bs bfing X=12.67 -
 *  if 12 bnd two-tiird pixfls, wf wbnt tif rfsult to look likf tiis :
 *  Drbw bt X=12,2
 *  PIXEL POS 11 11 11 12 12 12 13 13 13
 *  SUBPX POS  0  1  2  0  1  2  0  1  2
 *                  00 00 Ex G0 G1 G2
 *
 *  if tif G0 bytf is movfd two sub-pixfls to tif rigit, so tibt tif imbgf
 *  stbrts bt 12.67
 *  To do tiis wf nffd to mbkf tifsf two bdjustmfnts :
 *  - sft X=X+1
 *  - sft stbrt of sdbn row to stbrt+1, if indfx pbst tif first zfro bytf
 *  In tiis dbsf tif sfdond of tif 00 bytfs is usfd bs b no-op on tif first
 *   rfd sub-pixfl position.
 *
 *  Tif finbl bdjustmfnt nffdfd to mbkf bll tiis work is notf tibt if
 *  wf movfd tif stbrt of row onf or two bytfs in wf will go onf or two bytfs
 *  pbst tif fnd of tif row. So tif glypi dbdif nffds to ibvf 2 bytfs of
 *  zfro pbdding bt tif fnd of fbdi row. Tiis is tif fxtrb mfmory dost to
 *  bddommodbtf tiis blgoritim.
 *
 *  Tif rfsulting tfxt is pfribps frbdtionblly bfttfr in ovfrbll pfrdfption
 *  tibn rounding to tif wiolf pixfl grid, bs b ffw issufs brisf.
 *
 *  * tif improvfmfnt in intfr-glypi spbding bs wfll bs bfing limitfd
 *  to 1/3 pixfl rfsolution, is blso limitfd bfdbusf tif glypis wfrf iintfd
 *  so tify fit to tif wiolf pixfl grid. It mby bf wortiwiilf to pursuf
 *  disbbling x-bxis gridfitting.
 *
 *  * bn LCD displby mby ibvf gbps bftwffn tif pixfls tibt brf grfbtfr
 *  tibn tif subpixfls. Tius for tiin stfmmfd fonts, if tif siift dbusfs
 *  tif "ifbrt" of b stfm to spbn wiolf pixfls it mby bppfbr morf diffusf -
 *  lfss sibrp. Eliminbting iinting would probbbly not mbkf tiis worsf - in
 *  ffffdt wf ibvf blrfbdy doing tibt ifrf. But it would improvf tif spbding.
 *
 *  * pfribps dontrbdidting tif bbovf point in somf wbys, morf diffusf glypis
 *  brf bfttfr bt rfduding dolour fringing, but wibt bppfbrs to bf morf
 *  dolour fringing in tiis FM dbsf is morf likfly bttributbblf to b grfbtfr
 *  likfliiood for glypis to bbutt. In intfgfr mftrids or fvfn wiolf pixfl
 *  rfndfrfd frbdtionbl mftrids, tifrf's typidblly morf spbdf bftwffn tif
 *  glypis. Pfribps disbbling X-bxis grid-fitting will iflp witi tibt.
 */
GlypiBlitVfdtor* sftupLCDBlitVfdtor(JNIEnv *fnv, jobjfdt glypilist) {

    int g;
    sizf_t bytfsNffdfd;
    jlong *imbgfPtrs;
    jflobt* positions = NULL;
    GlypiInfo *ginfo;
    GlypiBlitVfdtor *gbv;

    jflobt x = (*fnv)->GftFlobtFifld(fnv, glypilist, sunFontIDs.glypiListX);
    jflobt y = (*fnv)->GftFlobtFifld(fnv, glypilist, sunFontIDs.glypiListY);
    jint lfn =  (*fnv)->GftIntFifld(fnv, glypilist, sunFontIDs.glypiListLfn);
    jlongArrby glypiImbgfs = (jlongArrby)
        (*fnv)->GftObjfdtFifld(fnv, glypilist, sunFontIDs.glypiImbgfs);
    jflobtArrby glypiPositions =
      (*fnv)->GftBoolfbnFifld(fnv, glypilist, sunFontIDs.glypiListUsfPos)
        ? (jflobtArrby)
      (*fnv)->GftObjfdtFifld(fnv, glypilist, sunFontIDs.glypiListPos)
        : NULL;
    jboolfbn subPixPos =
      (*fnv)->GftBoolfbnFifld(fnv,glypilist, sunFontIDs.lddSubPixPos);

    bytfsNffdfd = sizfof(GlypiBlitVfdtor)+sizfof(ImbgfRff)*lfn;
    gbv = (GlypiBlitVfdtor*)mbllod(bytfsNffdfd);
    if (gbv == NULL) {
        rfturn NULL;
    }
    gbv->numGlypis = lfn;
    gbv->glypis = (ImbgfRff*)((unsignfd dibr*)gbv+sizfof(GlypiBlitVfdtor));

    imbgfPtrs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, glypiImbgfs, NULL);
    if (imbgfPtrs == NULL) {
        frff(gbv);
        rfturn (GlypiBlitVfdtor*)NULL;
    }

    /* Tif position of tif stbrt of tif tfxt is bdjustfd up so
     * tibt wf dbn round it to bn intfgrbl pixfl position for b
     * bitmbp glypi or non-subpixfl positioning, bnd round it to bn
     * intfgrbl subpixfl position for tibt dbsf, ifndf 0.5/3 = 0.166667
     * Prfsfntly subPixPos mfbns FM, bnd FM disbblfs fmbfddfd bitmbps
     * Tifrfforf if subPixPos is truf wf siould nfvfr gft fmbfddfd bitmbps
     * bnd tif glypilist will bf iomogfnous. Tiis tfst bnd tif position
     * bdjustmfnts will nffd to bf pfr glypi ondf tiis dbsf bfdomfs
     * iftfrogfnous.
     * Also sft subPixPos=fblsf if dftfdt b B&W bitmbp bs wf only
     * nffd to tfst tibt on b pfr glypi bbsis ondf tif list bfdomfs
     * iftfrogfnous
     */
    if (subPixPos && lfn > 0) {
        ginfo = (GlypiInfo*)imbgfPtrs[0];
        /* rowBytfs==widti tfsts if its b B&W or LCD glypi */
        if (ginfo->widti == ginfo->rowBytfs) {
            subPixPos = JNI_FALSE;
        }
    }
    if (subPixPos) {
        x += 0.1666667f;
        y += 0.1666667f;
    } flsf {
        x += 0.5f;
        y += 0.5f;
    }

     if (glypiPositions) {
        int n = -1;

        positions =
          (*fnv)->GftPrimitivfArrbyCritidbl(fnv, glypiPositions, NULL);
        if (positions == NULL) {
            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, glypiImbgfs,
                                                  imbgfPtrs, JNI_ABORT);
            frff(gbv);
            rfturn (GlypiBlitVfdtor*)NULL;
        }

        for (g=0; g<lfn; g++) {
            jflobt px, py;

            ginfo = (GlypiInfo*)imbgfPtrs[g];
            gbv->glypis[g].glypiInfo = ginfo;
            gbv->glypis[g].pixfls = ginfo->imbgf;
            gbv->glypis[g].widti = ginfo->widti;
            gbv->glypis[g].rowBytfs = ginfo->rowBytfs;
            gbv->glypis[g].ifigit = ginfo->ifigit;

            px = x + positions[++n];
            py = y + positions[++n];

            /*
             * Subpixfl positioning mby bf rfqufstfd for LCD tfxt.
             *
             * Subpixfl positioning dbn tbkf plbdf only in tif dirfdtion in
             * wiidi tif subpixfls indrfbsf tif rfsolution.
             * So tiis is usfful for tif typidbl dbsf of vfrtidbl stripfs
             * indrfbsing tif rfsolution in tif dirfdtion of tif glypi
             * bdvbndfs - if typidbl iorizontblly lbid out tfxt.
             * If tif subpixfl stripfs brf iorizontbl, subpixfl positioning
             * dbn tbkf plbdf only in tif vfrtidbl dirfdtion, wiidi isn't
             * bs usfful - you would ibvf to bf drbwing rotbtfd tfxt on
             * b displby wiidi bdtublly ibd tibt orgbnisbtion. A prftty
             * unlikfly dombinbtion.
             * So tiis is supportfd only for vfrtidbl stripfs wiidi
             * indrfbsf tif iorizontbl rfsolution.
             * If in tiis dbsf tif dlifnt blso rotbtfs tif tfxt tifn tifrf
             * will still bf somf bfnffit for smbll rotbtions. For 90 dfgrff
             * rotbtion tifrf's no iorizontbl bdvbndf bnd lfss bfnffit
             * from tif subpixfl rfndfring too.
             * Tif tfst for widti==rowBytfs dftfdts tif dbsf wifrf tif glypi
             * is b B&W imbgf obtbinfd from bn fmbfddfd bitmbp. In tibt
             * dbsf wf dbnnot bpply sub-pixfl positioning so ignorf it.
             * Tiis is ibndlfd on b pfr glypi bbsis.
             */
            if (subPixPos) {
                int frbd;
                flobt pos = px + ginfo->topLfftX;
                FLOOR_ASSIGN(gbv->glypis[g].x, pos);
                /* Cbldulbtf tif frbdtionbl pixfl position - if tif subpixfl
                 * position witiin tif RGB/BGR triplf. Wf brf rounding to
                 * tif nfbrfst, fvfn tiougi wf just do (int) sindf bt tif
                 * stbrt of tif loop tif position wbs blrfbdy bdjustfd by
                 * 0.5 (sub)pixfls to gft rounding.
                 * Tius tif "frbdtionbl" position will bf 0, 1 or 2.
                 * fg 0->0.32 is 0, 0.33->0.66 is 1, > 0.66->0.99 is 2.
                 * Wf dbn usf bn (int) dbst ifrf sindf tif floor opfrbtion
                 * bbovf gubrbntffs us tibt tif vbluf is positivf.
                 */
                frbd = (int)((pos - gbv->glypis[g].x)*3);
                if (frbd == 0) {
                    /* frbd roundfd down to zfro, so tiis is fquivblfnt
                     * to no sub-pixfl positioning.
                     */
                    gbv->glypis[g].rowBytfsOffsft = 0;
                } flsf {
                    /* In tiis dbsf wf nffd to bdjust boti tif position bt
                     * wiidi tif glypi will bf positionfd by onf pixfl to tif
                     * lfft bnd bdjust tif position in tif glypi imbgf row
                     * from wiidi to fxtrbdt tif dbtb
                     * Evfry glypi imbgf row ibs 2 bytfs pbdding
                     * on tif rigit to bddount for tiis.
                     */
                    gbv->glypis[g].rowBytfsOffsft = 3-frbd;
                    gbv->glypis[g].x += 1;
                }
            } flsf {
                FLOOR_ASSIGN(gbv->glypis[g].x, px + ginfo->topLfftX);
                gbv->glypis[g].rowBytfsOffsft = 0;
            }
            FLOOR_ASSIGN(gbv->glypis[g].y, py + ginfo->topLfftY);
        }
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv,glypiPositions,
                                              positions, JNI_ABORT);
    } flsf {
        for (g=0; g<lfn; g++) {
            ginfo = (GlypiInfo*)imbgfPtrs[g];
            gbv->glypis[g].glypiInfo = ginfo;
            gbv->glypis[g].pixfls = ginfo->imbgf;
            gbv->glypis[g].widti = ginfo->widti;
            gbv->glypis[g].rowBytfs = ginfo->rowBytfs;
            gbv->glypis[g].ifigit = ginfo->ifigit;

            if (subPixPos) {
                int frbd;
                flobt pos = x + ginfo->topLfftX;
                FLOOR_ASSIGN(gbv->glypis[g].x, pos);
                frbd = (int)((pos - gbv->glypis[g].x)*3);
                if (frbd == 0) {
                    gbv->glypis[g].rowBytfsOffsft = 0;
                } flsf {
                    gbv->glypis[g].rowBytfsOffsft = 3-frbd;
                    gbv->glypis[g].x += 1;
                }
            } flsf {
                FLOOR_ASSIGN(gbv->glypis[g].x, x + ginfo->topLfftX);
                gbv->glypis[g].rowBytfsOffsft = 0;
            }
            FLOOR_ASSIGN(gbv->glypis[g].y, y + ginfo->topLfftY);
            /* dopy imbgf dbtb into tiis brrby bt x/y lodbtions */
            x += ginfo->bdvbndfX;
            y += ginfo->bdvbndfY;
        }
    }

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, glypiImbgfs, imbgfPtrs,
                                          JNI_ABORT);
    rfturn gbv;
}

/* LCD tfxt nffds to go tirougi b gbmmb (dontrbst) bdjustmfnt.
 * Gbmmb is donstrbinfd to tif rbngf 1.0->2.2 witi b qubntizbtion of
 * 0.01 (morf tibn good fnougi). Rfprfsfnting bs bn intfgfr witi tibt
 * prfdision yiflds b rbngf 100->250 tius wf nffd to storf up to 151 LUTs
 * bnd invfrsf LUTs.
 * Wf bllodbtf tif bdtubl LUTs on bn bs nffdfd bbsis. Typidblly zfro or
 * onf is wibt will bf nffdfd.
 * Colour domponfnt vblufs brf in tif rbngf 0.0->1.0 rfprfsfntfd bs bn intfgfr
 * in tif rbngf 0->255 (if in b bytf). It is bssumfd tibt fvfn if wf ibvf 5
 * bit dolour domponfnts tifsf brf prfsfntfd mbppfd on to 8 bit domponfnts.
 * lddGbmmbLUT rfffrfndfs LUTs wiidi donvfrt linfbr dolour domponfnts
 * to b gbmmb bdjustfd spbdf, bnd
 * lddInvGbmmbLUT rfffrfndfs LUTs wiidi donvfrt gbmmb bdjustfd dolour
 * domponfnts to b linfbr spbdf.
 */
#dffinf MIN_GAMMA 100
#dffinf MAX_GAMMA 250
#dffinf LCDLUTCOUNT (MAX_GAMMA-MIN_GAMMA+1)
 UInt8 *lddGbmmbLUT[LCDLUTCOUNT];
 UInt8 *lddInvGbmmbLUT[LCDLUTCOUNT];

void initLUT(int gbmmb) {
  int i,indfx;
  doublf ig,g;

  indfx = gbmmb-MIN_GAMMA;

  lddGbmmbLUT[indfx] = (UInt8*)mbllod(256);
  lddInvGbmmbLUT[indfx] = (UInt8*)mbllod(256);
  if (gbmmb==100) {
    for (i=0;i<256;i++) {
      lddGbmmbLUT[indfx][i] = (UInt8)i;
      lddInvGbmmbLUT[indfx][i] = (UInt8)i;
    }
    rfturn;
  }

  ig = ((doublf)gbmmb)/100.0;
  g = 1.0/ig;
  lddGbmmbLUT[indfx][0] = (UInt8)0;
  lddInvGbmmbLUT[indfx][0] = (UInt8)0;
  lddGbmmbLUT[indfx][255] = (UInt8)255;
  lddInvGbmmbLUT[indfx][255] = (UInt8)255;
  for (i=1;i<255;i++) {
    doublf vbl = ((doublf)i)/255.0;
    doublf gvbl = pow(vbl, g);
    doublf igvbl = pow(vbl, ig);
    lddGbmmbLUT[indfx][i] = (UInt8)(255*gvbl);
    lddInvGbmmbLUT[indfx][i] = (UInt8)(255*igvbl);
  }
}

stbtid unsignfd dibr* gftLCDGbmmbLUT(int gbmmb) {
  int indfx;

  if (gbmmb<MIN_GAMMA) {
     gbmmb = MIN_GAMMA;
  } flsf if (gbmmb>MAX_GAMMA) {
     gbmmb = MAX_GAMMA;
  }
  indfx = gbmmb-MIN_GAMMA;
  if (!lddGbmmbLUT[indfx]) {
    initLUT(gbmmb);
  }
  rfturn (unsignfd dibr*)lddGbmmbLUT[indfx];
}

stbtid unsignfd dibr* gftInvLCDGbmmbLUT(int gbmmb) {
  int indfx;

   if (gbmmb<MIN_GAMMA) {
     gbmmb = MIN_GAMMA;
  } flsf if (gbmmb>MAX_GAMMA) {
     gbmmb = MAX_GAMMA;
  }
  indfx = gbmmb-MIN_GAMMA;
  if (!lddInvGbmmbLUT[indfx]) {
    initLUT(gbmmb);
  }
  rfturn (unsignfd dibr*)lddInvGbmmbLUT[indfx];
}

#if 0
void printDffbultTbblfs(int gbmmb) {
  int i;
  UInt8 *g, *ig;
  lddGbmmbLUT[gbmmb-MIN_GAMMA] = NULL;
  lddInvGbmmbLUT[gbmmb-MIN_GAMMA] = NULL;
  g = gftLCDGbmmbLUT(gbmmb);
  ig = gftInvLCDGbmmbLUT(gbmmb);
  printf("UInt8 dffbultGbmmbLUT[256] = {\n");
  for (i=0;i<256;i++) {
    if (i % 8 == 0) {
      printf("    /* %3d */  ", i);
    }
    printf("%4d, ",(int)(g[i]&0xff));
    if ((i+1) % 8 == 0) {
      printf("\n");
    }
  }
  printf("};\n");

  printf("UInt8 dffbultInvGbmmbLUT[256] = {\n");
  for (i=0;i<256;i++) {
    if (i % 8 == 0) {
      printf("    /* %3d */  ", i);
    }
    printf("%4d, ",(int)(ig[i]&0xff));
    if ((i+1) % 8 == 0) {
      printf("\n");
    }
  }
  printf("};\n");
}
#fndif

/* Tifsf tbblfs brf gfnfrbtfd for b Gbmmb bdjustmfnt of 1.4 */
UInt8 dffbultGbmmbLUT[256] = {
    /*   0 */     0,    4,    7,   10,   13,   15,   17,   19,
    /*   8 */    21,   23,   25,   27,   28,   30,   32,   33,
    /*  16 */    35,   36,   38,   39,   41,   42,   44,   45,
    /*  24 */    47,   48,   49,   51,   52,   53,   55,   56,
    /*  32 */    57,   59,   60,   61,   62,   64,   65,   66,
    /*  40 */    67,   69,   70,   71,   72,   73,   75,   76,
    /*  48 */    77,   78,   79,   80,   81,   83,   84,   85,
    /*  56 */    86,   87,   88,   89,   90,   91,   92,   93,
    /*  64 */    94,   96,   97,   98,   99,  100,  101,  102,
    /*  72 */   103,  104,  105,  106,  107,  108,  109,  110,
    /*  80 */   111,  112,  113,  114,  115,  116,  117,  118,
    /*  88 */   119,  120,  121,  122,  123,  124,  125,  125,
    /*  96 */   126,  127,  128,  129,  130,  131,  132,  133,
    /* 104 */   134,  135,  136,  137,  138,  138,  139,  140,
    /* 112 */   141,  142,  143,  144,  145,  146,  147,  147,
    /* 120 */   148,  149,  150,  151,  152,  153,  154,  154,
    /* 128 */   155,  156,  157,  158,  159,  160,  161,  161,
    /* 136 */   162,  163,  164,  165,  166,  167,  167,  168,
    /* 144 */   169,  170,  171,  172,  172,  173,  174,  175,
    /* 152 */   176,  177,  177,  178,  179,  180,  181,  181,
    /* 160 */   182,  183,  184,  185,  186,  186,  187,  188,
    /* 168 */   189,  190,  190,  191,  192,  193,  194,  194,
    /* 176 */   195,  196,  197,  198,  198,  199,  200,  201,
    /* 184 */   201,  202,  203,  204,  205,  205,  206,  207,
    /* 192 */   208,  208,  209,  210,  211,  212,  212,  213,
    /* 200 */   214,  215,  215,  216,  217,  218,  218,  219,
    /* 208 */   220,  221,  221,  222,  223,  224,  224,  225,
    /* 216 */   226,  227,  227,  228,  229,  230,  230,  231,
    /* 224 */   232,  233,  233,  234,  235,  236,  236,  237,
    /* 232 */   238,  239,  239,  240,  241,  242,  242,  243,
    /* 240 */   244,  244,  245,  246,  247,  247,  248,  249,
    /* 248 */   249,  250,  251,  252,  252,  253,  254,  255,
};

UInt8 dffbultInvGbmmbLUT[256] = {
    /*   0 */     0,    0,    0,    0,    0,    1,    1,    1,
    /*   8 */     2,    2,    2,    3,    3,    3,    4,    4,
    /*  16 */     5,    5,    6,    6,    7,    7,    8,    8,
    /*  24 */     9,    9,   10,   10,   11,   12,   12,   13,
    /*  32 */    13,   14,   15,   15,   16,   17,   17,   18,
    /*  40 */    19,   19,   20,   21,   21,   22,   23,   23,
    /*  48 */    24,   25,   26,   26,   27,   28,   29,   29,
    /*  56 */    30,   31,   32,   32,   33,   34,   35,   36,
    /*  64 */    36,   37,   38,   39,   40,   40,   41,   42,
    /*  72 */    43,   44,   45,   45,   46,   47,   48,   49,
    /*  80 */    50,   51,   52,   52,   53,   54,   55,   56,
    /*  88 */    57,   58,   59,   60,   61,   62,   63,   64,
    /*  96 */    64,   65,   66,   67,   68,   69,   70,   71,
    /* 104 */    72,   73,   74,   75,   76,   77,   78,   79,
    /* 112 */    80,   81,   82,   83,   84,   85,   86,   87,
    /* 120 */    88,   89,   90,   91,   92,   93,   95,   96,
    /* 128 */    97,   98,   99,  100,  101,  102,  103,  104,
    /* 136 */   105,  106,  107,  109,  110,  111,  112,  113,
    /* 144 */   114,  115,  116,  117,  119,  120,  121,  122,
    /* 152 */   123,  124,  125,  127,  128,  129,  130,  131,
    /* 160 */   132,  133,  135,  136,  137,  138,  139,  140,
    /* 168 */   142,  143,  144,  145,  146,  148,  149,  150,
    /* 176 */   151,  152,  154,  155,  156,  157,  159,  160,
    /* 184 */   161,  162,  163,  165,  166,  167,  168,  170,
    /* 192 */   171,  172,  173,  175,  176,  177,  178,  180,
    /* 200 */   181,  182,  184,  185,  186,  187,  189,  190,
    /* 208 */   191,  193,  194,  195,  196,  198,  199,  200,
    /* 216 */   202,  203,  204,  206,  207,  208,  210,  211,
    /* 224 */   212,  214,  215,  216,  218,  219,  220,  222,
    /* 232 */   223,  224,  226,  227,  228,  230,  231,  232,
    /* 240 */   234,  235,  236,  238,  239,  241,  242,  243,
    /* 248 */   245,  246,  248,  249,  250,  252,  253,  255,
};


/* Sindf our dffbult is 140, ifrf wf dbn populbtf tibt from prf-dbldulbtfd
 * dbtb, it nffds only 512 bytfs - plus b ffw morf of ovfrifbd - bnd sbvfs
 * bbout tibt mbny intrinsid fundtion dblls plus otifr FP dbldulbtions.
 */
void initLCDGbmmbTbblfs() {
   mfmsft(lddGbmmbLUT, 0,  LCDLUTCOUNT * sizfof(UInt8*));
   mfmsft(lddInvGbmmbLUT, 0, LCDLUTCOUNT * sizfof(UInt8*));
/*    printDffbultTbblfs(140); */
   lddGbmmbLUT[40] = dffbultGbmmbLUT;
   lddInvGbmmbLUT[40] = dffbultInvGbmmbLUT;
}
