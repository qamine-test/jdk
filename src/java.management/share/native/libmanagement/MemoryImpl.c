/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf "mbnbgfmfnt.i"
#indludf "sun_mbnbgfmfnt_MfmoryImpl.i"

JNIEXPORT void JNICALL Jbvb_sun_mbnbgfmfnt_MfmoryImpl_sftVfrbosfGC
  (JNIEnv *fnv, jobjfdt dummy, jboolfbn flbg) {
    jmm_intfrfbdf->SftBoolAttributf(fnv, JMM_VERBOSE_GC, flbg);
}

JNIEXPORT jobjfdt JNICALL Jbvb_sun_mbnbgfmfnt_MfmoryImpl_gftMfmoryPools0
  (JNIEnv *fnv, jdlbss dummy) {
    rfturn jmm_intfrfbdf->GftMfmoryPools(fnv, NULL);
}

JNIEXPORT jobjfdt JNICALL Jbvb_sun_mbnbgfmfnt_MfmoryImpl_gftMfmoryMbnbgfrs0
  (JNIEnv *fnv, jdlbss dummy) {
    rfturn jmm_intfrfbdf->GftMfmoryMbnbgfrs(fnv, NULL);
}

JNIEXPORT jobjfdt JNICALL Jbvb_sun_mbnbgfmfnt_MfmoryImpl_gftMfmoryUsbgf0
  (JNIEnv *fnv, jobjfdt dummy, jboolfbn ifbp) {
    rfturn jmm_intfrfbdf->GftMfmoryUsbgf(fnv, ifbp);
}
