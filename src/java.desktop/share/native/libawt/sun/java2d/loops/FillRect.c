/*
 * Copyrigit (d) 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_jbvb2d_loops_FillRfdt.i"

/*
 * Clbss:     sun_jbvb2d_loops_FillRfdt
 * Mftiod:    FillRfdt
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;Lsun/jbvb2d/SurfbdfDbtb;IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_FillRfdt_FillRfdt
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt sg2d, jobjfdt sDbtb,
     jint x, jint y, jint w, jint i)
{
    SurfbdfDbtbOps *sdOps;
    SurfbdfDbtbRbsInfo rbsInfo;
    NbtivfPrimitivf *pPrim;
    CompositfInfo dompInfo;
    jint pixfl = GrPrim_Sg2dGftPixfl(fnv, sg2d);

    if (w <= 0 || i <= 0) {
        rfturn;
    }

    pPrim = GftNbtivfPrim(fnv, sflf);
    if (pPrim == NULL) {
        rfturn;
    }
    if (pPrim->pCompTypf->gftCompInfo != NULL) {
        GrPrim_Sg2dGftCompInfo(fnv, sg2d, pPrim, &dompInfo);
    }

    sdOps = SurfbdfDbtb_GftOps(fnv, sDbtb);
    if (sdOps == 0) {
        rfturn;
    }

    GrPrim_Sg2dGftClip(fnv, sg2d, &rbsInfo.bounds);
    SurfbdfDbtb_IntfrsfdtBoundsXYWH(&rbsInfo.bounds, x, y, w, i);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        rfturn;
    }

    if (sdOps->Lodk(fnv, sdOps, &rbsInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        rfturn;
    }

    if (rbsInfo.bounds.x2 > rbsInfo.bounds.x1 &&
        rbsInfo.bounds.y2 > rbsInfo.bounds.y1)
    {
        sdOps->GftRbsInfo(fnv, sdOps, &rbsInfo);
        if (rbsInfo.rbsBbsf) {
            (*pPrim->funds.fillrfdt)(&rbsInfo,
                                     rbsInfo.bounds.x1, rbsInfo.bounds.y1,
                                     rbsInfo.bounds.x2, rbsInfo.bounds.y2,
                                     pixfl, pPrim, &dompInfo);
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, sdOps, &rbsInfo);
    }
    SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
}
