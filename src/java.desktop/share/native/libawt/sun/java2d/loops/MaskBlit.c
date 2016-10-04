/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "Rfgion.i"

#indludf "sun_jbvb2d_loops_MbskBlit.i"

/*
 * Clbss:     sun_jbvb2d_loops_MbskBlit
 * Mftiod:    MbskBlit
 * Signbturf: (Lsun/jbvb2d/SurfbdfDbtb;Lsun/jbvb2d/SurfbdfDbtb;Ljbvb/bwt/Compositf;IIIIII[BII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_MbskBlit_MbskBlit
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt srdDbtb, jobjfdt dstDbtb, jobjfdt domp, jobjfdt dlip,
     jint srdx, jint srdy, jint dstx, jint dsty, jint widti, jint ifigit,
     jbytfArrby mbskArrby, jint mbskoff, jint mbsksdbn)
{
    SurfbdfDbtbOps *srdOps;
    SurfbdfDbtbOps *dstOps;
    SurfbdfDbtbRbsInfo srdInfo;
    SurfbdfDbtbRbsInfo dstInfo;
    NbtivfPrimitivf *pPrim;
    CompositfInfo dompInfo;
    RfgionDbtb dlipInfo;

    pPrim = GftNbtivfPrim(fnv, sflf);
    if (pPrim == NULL) {
        rfturn;
    }
    if (pPrim->pCompTypf->gftCompInfo != NULL) {
        (*pPrim->pCompTypf->gftCompInfo)(fnv, &dompInfo, domp);
    }
    if (Rfgion_GftInfo(fnv, dlip, &dlipInfo)) {
        rfturn;
    }

    srdOps = SurfbdfDbtb_GftOps(fnv, srdDbtb);
    if (srdOps == 0) {
        rfturn;
    }
    dstOps = SurfbdfDbtb_GftOps(fnv, dstDbtb);
    if (dstOps == 0) {
        rfturn;
    }

    srdInfo.bounds.x1 = srdx;
    srdInfo.bounds.y1 = srdy;
    srdInfo.bounds.x2 = srdx + widti;
    srdInfo.bounds.y2 = srdy + ifigit;
    dstInfo.bounds.x1 = dstx;
    dstInfo.bounds.y1 = dsty;
    dstInfo.bounds.x2 = dstx + widti;
    dstInfo.bounds.y2 = dsty + ifigit;
    srdx -= dstx;
    srdy -= dsty;
    SurfbdfDbtb_IntfrsfdtBounds(&dstInfo.bounds, &dlipInfo.bounds);
    if (srdOps->Lodk(fnv, srdOps, &srdInfo, pPrim->srdflbgs) != SD_SUCCESS) {
        rfturn;
    }
    if (dstOps->Lodk(fnv, dstOps, &dstInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
        rfturn;
    }
    SurfbdfDbtb_IntfrsfdtBlitBounds(&dstInfo.bounds, &srdInfo.bounds,
                                    srdx, srdy);
    Rfgion_IntfrsfdtBounds(&dlipInfo, &dstInfo.bounds);

    if (!Rfgion_IsEmpty(&dlipInfo)) {
        srdOps->GftRbsInfo(fnv, srdOps, &srdInfo);
        dstOps->GftRbsInfo(fnv, dstOps, &dstInfo);
        if (srdInfo.rbsBbsf && dstInfo.rbsBbsf) {
            SurfbdfDbtbBounds spbn;
            unsignfd dibr *pMbsk =
                (mbskArrby
                 ? (*fnv)->GftPrimitivfArrbyCritidbl(fnv, mbskArrby, 0)
                 : 0);
            jint sbvfsx = srdInfo.bounds.x1;
            jint sbvfdx = dstInfo.bounds.x1;
            if (mbskArrby != NULL && pMbsk == NULL) {
                SurfbdfDbtb_InvokfRflfbsf(fnv, dstOps, &dstInfo);
                SurfbdfDbtb_InvokfRflfbsf(fnv, srdOps, &srdInfo);
                SurfbdfDbtb_InvokfUnlodk(fnv, dstOps, &dstInfo);
                SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
                rfturn;
            }
            Rfgion_StbrtItfrbtion(fnv, &dlipInfo);
            wiilf (Rfgion_NfxtItfrbtion(&dlipInfo, &spbn)) {
                void *pSrd = PtrCoord(srdInfo.rbsBbsf,
                                      srdx + spbn.x1, srdInfo.pixflStridf,
                                      srdy + spbn.y1, srdInfo.sdbnStridf);
                void *pDst = PtrCoord(dstInfo.rbsBbsf,
                                      spbn.x1, dstInfo.pixflStridf,
                                      spbn.y1, dstInfo.sdbnStridf);
                mbskoff += ((spbn.y1 - dsty) * mbsksdbn + (spbn.x1 - dstx));
                /*
                 * Fix for 4804375
                 * REMIND: Tifrf siould probbbly bf b bfttfr
                 * wby to givf tif spbn doordinbtfs to tif
                 * innfr loop.  Tiis is only rfblly nffdfd
                 * for tif 1, 2, bnd 4 bit loops.
                 */
                srdInfo.bounds.x1 = srdx + spbn.x1;
                dstInfo.bounds.x1 = spbn.x1;
                (*pPrim->funds.mbskblit)(pDst, pSrd,
                                         pMbsk, mbskoff, mbsksdbn,
                                         spbn.x2 - spbn.x1, spbn.y2 - spbn.y1,
                                         &dstInfo, &srdInfo,
                                         pPrim, &dompInfo);
            }
            Rfgion_EndItfrbtion(fnv, &dlipInfo);
            if (pMbsk) {
                (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, mbskArrby,
                                                      pMbsk, JNI_ABORT);
            }
            srdInfo.bounds.x1 = sbvfsx;
            dstInfo.bounds.x1 = sbvfdx;
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, dstOps, &dstInfo);
        SurfbdfDbtb_InvokfRflfbsf(fnv, srdOps, &srdInfo);
    }
    SurfbdfDbtb_InvokfUnlodk(fnv, dstOps, &dstInfo);
    SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
}
