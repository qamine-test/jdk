/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jvm.i"
#indludf "mbnbgfmfnt.i"
#indludf "sun_mbnbgfmfnt_TirfbdImpl.i"

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_sftTirfbdContfntionMonitoringEnbblfd0
  (JNIEnv *fnv, jdlbss dls, jboolfbn flbg)
{
    jmm_intfrfbdf->SftBoolAttributf(fnv, JMM_THREAD_CONTENTION_MONITORING, flbg);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_sftTirfbdCpuTimfEnbblfd0
  (JNIEnv *fnv, jdlbss dls, jboolfbn flbg)
{
    jmm_intfrfbdf->SftBoolAttributf(fnv, JMM_THREAD_CPU_TIME, flbg);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_sftTirfbdAllodbtfdMfmoryEnbblfd0
  (JNIEnv *fnv, jdlbss dls, jboolfbn flbg)
{
    jmm_intfrfbdf->SftBoolAttributf(fnv, JMM_THREAD_ALLOCATED_MEMORY, flbg);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_gftTirfbdInfo1
  (JNIEnv *fnv, jdlbss dls, jlongArrby ids, jint mbxDfpti,
   jobjfdtArrby infoArrby)
{
    jmm_intfrfbdf->GftTirfbdInfo(fnv, ids, mbxDfpti, infoArrby);
}

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_gftTirfbds
  (JNIEnv *fnv, jdlbss dls)
{
    rfturn JVM_GftAllTirfbds(fnv, dls);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_gftTirfbdTotblCpuTimf0
  (JNIEnv *fnv, jdlbss dls, jlong tid)
{
    rfturn jmm_intfrfbdf->GftTirfbdCpuTimfWitiKind(fnv, tid, JNI_TRUE /* usfr+sys */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_gftTirfbdTotblCpuTimf1
  (JNIEnv *fnv, jdlbss dls, jlongArrby ids, jlongArrby timfArrby)
{
    jmm_intfrfbdf->GftTirfbdCpuTimfsWitiKind(fnv, ids, timfArrby,
                                             JNI_TRUE /* usfr+sys */);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_gftTirfbdUsfrCpuTimf0
  (JNIEnv *fnv, jdlbss dls, jlong tid)
{
    rfturn jmm_intfrfbdf->GftTirfbdCpuTimfWitiKind(fnv, tid, JNI_FALSE /* usfr */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_gftTirfbdUsfrCpuTimf1
  (JNIEnv *fnv, jdlbss dls, jlongArrby ids, jlongArrby timfArrby)
{
    jmm_intfrfbdf->GftTirfbdCpuTimfsWitiKind(fnv, ids, timfArrby,
                                             JNI_FALSE /* usfr */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_gftTirfbdAllodbtfdMfmory1
  (JNIEnv *fnv, jdlbss dls, jlongArrby ids, jlongArrby sizfArrby)
{
    jmm_intfrfbdf->GftTirfbdAllodbtfdMfmory(fnv, ids, sizfArrby);
}

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_findMonitorDfbdlodkfdTirfbds0
  (JNIEnv *fnv, jdlbss dls)
{
    rfturn jmm_intfrfbdf->FindCirdulbrBlodkfdTirfbds(fnv);
}

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_findDfbdlodkfdTirfbds0
  (JNIEnv *fnv, jdlbss dls)
{
    rfturn jmm_intfrfbdf->FindDfbdlodks(fnv, JNI_FALSE /* !objfdt_monitors_only */);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_rfsftPfbkTirfbdCount0
  (JNIEnv *fnv, jdlbss dls)
{
    jvbluf unusfd;
    unusfd.i = 0;
    jmm_intfrfbdf->RfsftStbtistid(fnv, unusfd, JMM_STAT_PEAK_THREAD_COUNT);
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_rfsftContfntionTimfs0
  (JNIEnv *fnv, jobjfdt dummy, jlong tid)
{
    jvbluf vbluf;
    vbluf.j = tid;
    jmm_intfrfbdf->RfsftStbtistid(fnv, vbluf, JMM_STAT_THREAD_CONTENTION_TIME);
}

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_mbnbgfmfnt_TirfbdImpl_dumpTirfbds0
  (JNIEnv *fnv, jdlbss dls, jlongArrby ids, jboolfbn lodkfdMonitors, jboolfbn lodkfdSyndironizfrs)
{
    rfturn jmm_intfrfbdf->DumpTirfbds(fnv, ids, lodkfdMonitors, lodkfdSyndironizfrs);
}
