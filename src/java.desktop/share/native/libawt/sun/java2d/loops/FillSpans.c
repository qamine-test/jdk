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

#indludf "jni_util.i"
#indludf "jlong.i"

#indludf "sun_jbvb2d_loops_FillSpbns.i"

#indludf "GrbpiidsPrimitivfMgr.i"

/*
 * Clbss:     sun_jbvb2d_loops_FillSpbns
 * Mftiod:    FillSpbns
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;Lsun/jbvb2d/SurfbdfDbtb;Lsun/jbvb2d/pipf/SpbnItfrbtor;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_FillSpbns_FillSpbns
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt sg2d, jobjfdt sDbtb, jint pixfl, jlong pItfrbtor, jobjfdt si)
{
    SpbnItfrbtorFunds *pSpbnFunds;
    SurfbdfDbtbOps *sdOps;
    SurfbdfDbtbRbsInfo rbsInfo;
    void *siDbtb;
    jint bbox[4];
    NbtivfPrimitivf *pPrim;
    CompositfInfo dompInfo;

    pSpbnFunds = (SpbnItfrbtorFunds *) jlong_to_ptr(pItfrbtor);
    if (pSpbnFunds == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "nbtivf itfrbtor not supplifd");
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
    if (sdOps == NULL) {
        rfturn;
    }

    siDbtb = (*pSpbnFunds->opfn)(fnv, si);

    (*pSpbnFunds->gftPbtiBox)(fnv, siDbtb, bbox);
    rbsInfo.bounds.x1 = bbox[0];
    rbsInfo.bounds.y1 = bbox[1];
    rbsInfo.bounds.x2 = bbox[2];
    rbsInfo.bounds.y2 = bbox[3];

    if (sdOps->Lodk(fnv, sdOps, &rbsInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        /* Lodk tirfw bn fxdfption */
        (*pSpbnFunds->dlosf)(fnv, siDbtb);
        rfturn;
    }
    (*pSpbnFunds->intfrsfdtClipBox)(fnv, siDbtb,
                                    rbsInfo.bounds.x1,
                                    rbsInfo.bounds.y1,
                                    rbsInfo.bounds.x2,
                                    rbsInfo.bounds.y2);

    sdOps->GftRbsInfo(fnv, sdOps, &rbsInfo);
    /* Protfdt bgbinst silfnt fbilurf of GftRbsInfo */
    if (rbsInfo.rbsBbsf != NULL) {
        pPrim->funds.fillspbns(&rbsInfo, pSpbnFunds, siDbtb,
                               pixfl, pPrim, &dompInfo);
    }

    SurfbdfDbtb_InvokfRflfbsf(fnv, sdOps, &rbsInfo);
    (*pSpbnFunds->dlosf)(fnv, siDbtb);
    SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
}
