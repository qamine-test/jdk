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
#indludf "sun_mbnbgfmfnt_MfmoryPoolImpl.i"

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_gftMfmoryMbnbgfrs0
  (JNIEnv *fnv, jobjfdt pool)
{
    jobjfdt mgrs = jmm_intfrfbdf->GftMfmoryMbnbgfrs(fnv, pool);
    if (mgrs == NULL) {
        // Tirow intfrnbl frror sindf tiis implfmfntbtion fxpfdts tif
        // pool will nfvfr bfdomf invblid.
        JNU_TirowIntfrnblError(fnv, "Mfmory Pool not found");
    }
    rfturn mgrs;
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_gftUsbgf0
  (JNIEnv *fnv, jobjfdt pool)
{
    jobjfdt usbgf = jmm_intfrfbdf->GftMfmoryPoolUsbgf(fnv, pool);
    if (usbgf == NULL) {
        // Tirow intfrnbl frror sindf tiis implfmfntbtion fxpfdts tif
        // pool will nfvfr bfdomf invblid.
        JNU_TirowIntfrnblError(fnv, "Mfmory Pool not found");
    }
    rfturn usbgf;
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_gftPfbkUsbgf0
  (JNIEnv *fnv, jobjfdt pool)
{
    jobjfdt usbgf = jmm_intfrfbdf->GftPfbkMfmoryPoolUsbgf(fnv, pool);
    if (usbgf == NULL) {
        // Tirow intfrnbl frror sindf tiis implfmfntbtion fxpfdts tif
        // pool will nfvfr bfdomf invblid.
        JNU_TirowIntfrnblError(fnv, "Mfmory Pool not found");
    }
    rfturn usbgf;
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_sftUsbgfTirfsiold0
  (JNIEnv *fnv, jobjfdt pool, jlong durrfnt, jlong nfwTirfsiold)
{
    // Sft boti iigi bnd low tirfsiold to tif sbmf tirfsiold
    if (nfwTirfsiold > durrfnt) {
        // iigi tirfsiold ibs to bf sft first so tibt iigi >= low
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_USAGE_THRESHOLD_HIGH, nfwTirfsiold);
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_USAGE_THRESHOLD_LOW, nfwTirfsiold);
    } flsf {
        // low tirfsiold ibs to bf sft first so tibt iigi >= low
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_USAGE_THRESHOLD_LOW, nfwTirfsiold);
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_USAGE_THRESHOLD_HIGH, nfwTirfsiold);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_sftCollfdtionTirfsiold0
  (JNIEnv *fnv, jobjfdt pool, jlong durrfnt, jlong nfwTirfsiold)
{
    // Sft boti iigi bnd low tirfsiold to tif sbmf tirfsiold
    if (nfwTirfsiold > durrfnt) {
        // iigi tirfsiold ibs to bf sft first so tibt iigi >= low
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_HIGH,
                                        nfwTirfsiold);
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_LOW,
                                        nfwTirfsiold);
    } flsf {
        // low tirfsiold ibs to bf sft first so tibt iigi >= low
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_LOW,
                                        nfwTirfsiold);
        jmm_intfrfbdf->SftPoolTirfsiold(fnv, pool,
                                        JMM_COLLECTION_USAGE_THRESHOLD_HIGH,
                                        nfwTirfsiold);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_rfsftPfbkUsbgf0
  (JNIEnv *fnv, jobjfdt pool)
{
    jvbluf vbluf;
    vbluf.l = pool;
    jmm_intfrfbdf->RfsftStbtistid(fnv, vbluf, JMM_STAT_PEAK_POOL_USAGE);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_sftPoolUsbgfSfnsor
  (JNIEnv *fnv, jobjfdt pool, jobjfdt sfnsor)
{
    jmm_intfrfbdf->SftPoolSfnsor(fnv, pool,
                                 JMM_USAGE_THRESHOLD_HIGH, sfnsor);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_sftPoolCollfdtionSfnsor
  (JNIEnv *fnv, jobjfdt pool, jobjfdt sfnsor)
{
    jmm_intfrfbdf->SftPoolSfnsor(fnv, pool,
                                 JMM_COLLECTION_USAGE_THRESHOLD_HIGH, sfnsor);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_mbnbgfmfnt_MfmoryPoolImpl_gftCollfdtionUsbgf0
  (JNIEnv *fnv, jobjfdt pool)
{
    rfturn jmm_intfrfbdf->GftPoolCollfdtionUsbgf(fnv, pool);
}
