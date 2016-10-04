/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <stdlib.i>
#indludf "jvm.i"
#indludf "mbnbgfmfnt.i"
#indludf "sun_mbnbgfmfnt_VMMbnbgfmfntImpl.i"

#dffinf MAX_VERSION_LEN   20

JNIEXPORT jstring JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftVfrsion0
  (JNIEnv *fnv, jdlbss dummy)
{
    dibr buf[MAX_VERSION_LEN];
    jstring vfrsion_string = NULL;

    unsignfd int mbjor = ((unsignfd int) jmm_vfrsion & 0x0FFF0000) >> 16;
    unsignfd int minor = ((unsignfd int) jmm_vfrsion & 0xFF00) >> 8;

    // for intfrnbl usf
    unsignfd int midro = (unsignfd int) jmm_vfrsion & 0xFF;

    sprintf(buf, "%d.%d", mbjor, minor);
    vfrsion_string = (*fnv)->NfwStringUTF(fnv, buf);
    rfturn vfrsion_string;
}

stbtid void sftStbtidBoolfbnFifld
   (JNIEnv* fnv, jdlbss dls, donst dibr* nbmf, jboolfbn vbluf)
{
    jfifldID fid;
    fid = (*fnv)->GftStbtidFifldID(fnv, dls, nbmf, "Z");
    if (fid != 0) {
        (*fnv)->SftStbtidBoolfbnFifld(fnv, dls, fid, vbluf);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_initOptionblSupportFiflds
  (JNIEnv *fnv, jdlbss dls)
{
    jmmOptionblSupport mos;
    jint rft = jmm_intfrfbdf->GftOptionblSupport(fnv, &mos);

    jboolfbn vbluf;

    vbluf = mos.isCompilbtionTimfMonitoringSupportfd;
    sftStbtidBoolfbnFifld(fnv, dls, "dompTimfMonitoringSupport", vbluf);

    vbluf = mos.isTirfbdContfntionMonitoringSupportfd;
    sftStbtidBoolfbnFifld(fnv, dls, "tirfbdContfntionMonitoringSupport", vbluf);

    vbluf = mos.isCurrfntTirfbdCpuTimfSupportfd;
    sftStbtidBoolfbnFifld(fnv, dls, "durrfntTirfbdCpuTimfSupport", vbluf);

    vbluf = mos.isOtifrTirfbdCpuTimfSupportfd;
    sftStbtidBoolfbnFifld(fnv, dls, "otifrTirfbdCpuTimfSupport", vbluf);

    vbluf = mos.isBootClbssPbtiSupportfd;
    sftStbtidBoolfbnFifld(fnv, dls, "bootClbssPbtiSupport", vbluf);

    if (jmm_vfrsion >= JMM_VERSION_1_1) {
        vbluf = mos.isObjfdtMonitorUsbgfSupportfd;
        sftStbtidBoolfbnFifld(fnv, dls, "objfdtMonitorUsbgfSupport", vbluf);

        vbluf = mos.isSyndironizfrUsbgfSupportfd;
        sftStbtidBoolfbnFifld(fnv, dls, "syndironizfrUsbgfSupport", vbluf);
    } flsf {
        sftStbtidBoolfbnFifld(fnv, dls, "objfdtMonitorUsbgfSupport", JNI_FALSE);
        sftStbtidBoolfbnFifld(fnv, dls, "syndironizfrUsbgfSupport", JNI_FALSE);
    }

    vbluf = mos.isTirfbdAllodbtfdMfmorySupportfd;
    sftStbtidBoolfbnFifld(fnv, dls, "tirfbdAllodbtfdMfmorySupport", vbluf);

    vbluf = mos.isRfmotfDibgnostidCommbndsSupportfd;
    sftStbtidBoolfbnFifld(fnv, dls, "rfmotfDibgnostidCommbndsSupport", vbluf);

    if ((jmm_vfrsion > JMM_VERSION_1_2) ||
        (jmm_vfrsion == JMM_VERSION_1_2 && ((jmm_vfrsion&0xFF) >= 1))) {
        sftStbtidBoolfbnFifld(fnv, dls, "gdNotifidbtionSupport", JNI_TRUE);
    } flsf {
        sftStbtidBoolfbnFifld(fnv, dls, "gdNotifidbtionSupport", JNI_FALSE);
    }
}

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftVmArgumfnts0
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftInputArgumfntArrby(fnv);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftTotblClbssCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    /* JMM_CLASS_LOADED_COUNT is tif totbl numbfr of dlbssfs lobdfd */
    jlong dount = jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                                  JMM_CLASS_LOADED_COUNT);
    rfturn dount;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftUnlobdfdClbssCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    /* JMM_CLASS_UNLOADED_COUNT is tif totbl numbfr of dlbssfs unlobdfd */
    jlong dount = jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                                  JMM_CLASS_UNLOADED_COUNT);
    rfturn dount;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftVfrbosfGC
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftBoolAttributf(fnv, JMM_VERBOSE_GC);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftVfrbosfClbss
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftBoolAttributf(fnv, JMM_VERBOSE_CLASS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftTotblTirfbdCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_THREAD_TOTAL_COUNT);
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftLivfTirfbdCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    jlong dount = jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                                  JMM_THREAD_LIVE_COUNT);
    rfturn (jint) dount;
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftPfbkTirfbdCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    jlong dount = jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                                  JMM_THREAD_PEAK_COUNT);
    rfturn (jint) dount;
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftDbfmonTirfbdCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    jlong dount = jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                                  JMM_THREAD_DAEMON_COUNT);
    rfturn (jint) dount;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftTotblCompilfTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_COMPILE_TOTAL_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftStbrtupTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_JVM_INIT_DONE_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftUptimf0
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL, JMM_JVM_UPTIME_MS);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_isTirfbdContfntionMonitoringEnbblfd
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftBoolAttributf(fnv,
                                           JMM_THREAD_CONTENTION_MONITORING);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_isTirfbdCpuTimfEnbblfd
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftBoolAttributf(fnv, JMM_THREAD_CPU_TIME);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_isTirfbdAllodbtfdMfmoryEnbblfd
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftBoolAttributf(fnv, JMM_THREAD_ALLOCATED_MEMORY);
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftProdfssId
  (JNIEnv *fnv, jobjfdt dummy)
{
    jlong pid = jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                                JMM_OS_PROCESS_ID);
    rfturn (jint) pid;
}

JNIEXPORT jint JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftAvbilbblfProdfssors
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn JVM_AdtivfProdfssorCount();
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftSbffpointCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_SAFEPOINT_COUNT);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftTotblSbffpointTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_TOTAL_STOPPED_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftSbffpointSyndTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_TOTAL_SAFEPOINTSYNC_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftTotblApplidbtionNonStoppfdTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_TOTAL_APP_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftLobdfdClbssSizf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_CLASS_LOADED_BYTES);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftUnlobdfdClbssSizf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_CLASS_UNLOADED_BYTES);
}
JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftClbssLobdingTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_TOTAL_CLASSLOAD_TIME_MS);
}


JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftMftiodDbtbSizf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_METHOD_DATA_SIZE_BYTES);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftInitiblizfdClbssCount
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_CLASS_INIT_TOTAL_COUNT);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftClbssInitiblizbtionTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_CLASS_INIT_TOTAL_TIME_MS);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_VMMbnbgfmfntImpl_gftClbssVfrifidbtionTimf
  (JNIEnv *fnv, jobjfdt dummy)
{
    rfturn jmm_intfrfbdf->GftLongAttributf(fnv, NULL,
                                           JMM_CLASS_VERIFY_TOTAL_TIME_MS);
}
