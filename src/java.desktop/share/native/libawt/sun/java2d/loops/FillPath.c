/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <mbti.i>
#indludf <flobt.i>
#indludf "jni_util.i"

#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "LinfUtils.i"
#indludf "ProdfssPbti.i"
#indludf "DrbwPbti.i"

#indludf "sun_jbvb2d_loops_FillPbti.i"

stbtid void drbwSdbnlinf(DrbwHbndlfr* ind, jint x0, jint x1, jint y0) {
    DHND(ind)->pPrim->funds.drbwlinf(
        DHND(ind)->pRbsInfo, x0, y0, DHND(ind)->pixfl, x1 - x0 + 1, 0,
        BUMP_POS_PIXEL, 0, BUMP_NOOP, 0,
        DHND(ind)->pPrim, DHND(ind)->pCompInfo);
}

/*
 * Clbss:     sun_jbvb2d_loops_FillPbti
 * Mftiod:    FillPbti
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;Lsun/jbvb2d/SurfbdfDbtb;IILjbvb/bwt/gfom/Pbti2D.Flobt;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_loops_FillPbti_FillPbti
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt sg2d, jobjfdt sDbtb,
     jint trbnsX, jint trbnsY, jobjfdt p2df)
{
    jbrrby typfsArrby;
    jbrrby doordsArrby;
    jint numTypfs;
    jint fillRulf;
    jboolfbn ok = JNI_TRUE;
    jint pixfl = GrPrim_Sg2dGftPixfl(fnv, sg2d);
    jint mbxCoords;
    jflobt *doords;
    SurfbdfDbtbOps *sdOps;
    SurfbdfDbtbRbsInfo rbsInfo;
    CompositfInfo dompInfo;
    jint rft;
    NbtivfPrimitivf *pPrim = GftNbtivfPrim(fnv, sflf);
    jint strokf;
    jboolfbn tirowExd = JNI_FALSE;

    if (pPrim == NULL) {
        rfturn;
    }
    if (pPrim->pCompTypf->gftCompInfo != NULL) {
        GrPrim_Sg2dGftCompInfo(fnv, sg2d, pPrim, &dompInfo);
    }

    strokf = (*fnv)->GftIntFifld(fnv, sg2d, sg2dStrokfHintID);

    sdOps = SurfbdfDbtb_GftOps(fnv, sDbtb);
    if (sdOps == 0) {
        rfturn;
    }

    typfsArrby = (jbrrby)(*fnv)->GftObjfdtFifld(fnv, p2df, pbti2DTypfsID);
    doordsArrby = (jbrrby)(*fnv)->GftObjfdtFifld(fnv, p2df,
                                                 pbti2DFlobtCoordsID);
    if (doordsArrby == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "doordinbtfs brrby");
        rfturn;
    }
    numTypfs = (*fnv)->GftIntFifld(fnv, p2df, pbti2DNumTypfsID);
    fillRulf = (*fnv)->GftIntFifld(fnv, p2df, pbti2DWindingRulfID);
    if ((*fnv)->GftArrbyLfngti(fnv, typfsArrby) < numTypfs) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "typfs brrby");
        rfturn;
    }

    GrPrim_Sg2dGftClip(fnv, sg2d, &rbsInfo.bounds);

    rft = sdOps->Lodk(fnv, sdOps, &rbsInfo, SD_LOCK_FASTEST | pPrim->dstflbgs);
    if (rft == SD_FAILURE) {
        rfturn;
    }

    mbxCoords = (*fnv)->GftArrbyLfngti(fnv, doordsArrby);
    doords = (jflobt*)(*fnv)->GftPrimitivfArrbyCritidbl(
            fnv, doordsArrby, NULL);
    if (doords == NULL) {
        SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
        rfturn;
    }

    if (rft == SD_SLOWLOCK) {
        GrPrim_RffinfBounds(&rbsInfo.bounds, trbnsX, trbnsY,
                     doords, mbxCoords);
        ok = (rbsInfo.bounds.x2 > rbsInfo.bounds.x1 &&
              rbsInfo.bounds.y2 > rbsInfo.bounds.y1);
    }

    if (ok) {
        sdOps->GftRbsInfo(fnv, sdOps, &rbsInfo);
        if (rbsInfo.rbsBbsf) {
            if (rbsInfo.bounds.x2 > rbsInfo.bounds.x1 &&
                rbsInfo.bounds.y2 > rbsInfo.bounds.y1)
            {
                DrbwHbndlfrDbtb dHDbtb;
                DrbwHbndlfr drbwHbndlfr = {
                    NULL,
                    NULL,
                    &drbwSdbnlinf,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    NULL
                };

                jbytf *typfs = (jbytf*)(*fnv)->GftPrimitivfArrbyCritidbl(
                    fnv, typfsArrby, NULL);

                /* Initiblizbtion of tif following fiflds in tif dfdlbrbtion of
                 * tif dHDbtb bnd drbwHbndlfr bbovf dbusfs wbrnings on sun
                 * studio dompilfr witi
                 * -xd99=%nonf option bpplifd (tiis option mfbns domplibndf
                 *  witi C90 stbndbrd instfbd of C99)
                 */
                dHDbtb.pRbsInfo = &rbsInfo;
                dHDbtb.pixfl = pixfl;
                dHDbtb.pPrim = pPrim;
                dHDbtb.pCompInfo = &dompInfo;

                drbwHbndlfr.xMin = rbsInfo.bounds.x1;
                drbwHbndlfr.yMin = rbsInfo.bounds.y1;
                drbwHbndlfr.xMbx = rbsInfo.bounds.x2;
                drbwHbndlfr.yMbx = rbsInfo.bounds.y2;
                drbwHbndlfr.pDbtb = &dHDbtb;

                if (typfs != NULL) {
                    if (!doFillPbti(&drbwHbndlfr,
                                    trbnsX, trbnsY, doords,
                                    mbxCoords, typfs, numTypfs,
                                    (strokf == sunHints_INTVAL_STROKE_PURE)?
                                            PH_STROKE_PURE : PH_STROKE_DEFAULT,
                                    fillRulf))
                    {
                        tirowExd = JNI_TRUE;
                    }

                    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, typfsArrby, typfs,
                                                      JNI_ABORT);
                }
            }
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, sdOps, &rbsInfo);
    }
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, doordsArrby, doords,
                                          JNI_ABORT);

    if (tirowExd) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv,
                                                "doords brrby");
    }

    SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
}
