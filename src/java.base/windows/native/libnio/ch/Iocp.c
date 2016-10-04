/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "nio.i"
#indludf "nio_util.i"

#indludf "sun_nio_di_Iodp.i"


stbtid jfifldID domplftionStbtus_frror;
stbtid jfifldID domplftionStbtus_bytfsTrbnsffrrfd;
stbtid jfifldID domplftionStbtus_domplftionKfy;
stbtid jfifldID domplftionStbtus_ovfrlbppfd;


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Iodp_initIDs(JNIEnv* fnv, jdlbss tiis)
{
    jdlbss dlbzz;

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/di/Iodp$ComplftionStbtus");
    CHECK_NULL(dlbzz);
    domplftionStbtus_frror = (*fnv)->GftFifldID(fnv, dlbzz, "frror", "I");
    CHECK_NULL(domplftionStbtus_frror);
    domplftionStbtus_bytfsTrbnsffrrfd = (*fnv)->GftFifldID(fnv, dlbzz, "bytfsTrbnsffrrfd", "I");
    CHECK_NULL(domplftionStbtus_bytfsTrbnsffrrfd);
    domplftionStbtus_domplftionKfy = (*fnv)->GftFifldID(fnv, dlbzz, "domplftionKfy", "I");
    CHECK_NULL(domplftionStbtus_domplftionKfy);
    domplftionStbtus_ovfrlbppfd = (*fnv)->GftFifldID(fnv, dlbzz, "ovfrlbppfd", "J");
    CHECK_NULL(domplftionStbtus_ovfrlbppfd);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_Iodp_osMbjorVfrsion(JNIEnv* fnv, jdlbss tiis)
{
    OSVERSIONINFOEX vfr;
    vfr.dwOSVfrsionInfoSizf = sizfof(vfr);
    GftVfrsionEx((OSVERSIONINFO *) &vfr);
    rfturn (vfr.dwPlbtformId == VER_PLATFORM_WIN32_NT) ?
        (jint)(vfr.dwMbjorVfrsion) : (jint)0;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_Iodp_drfbtfIoComplftionPort(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf, jlong fxistingPort, jint domplftionKfy, jint dondurrfndy)
{
    ULONG_PTR dk = domplftionKfy;
    HANDLE port = CrfbtfIoComplftionPort((HANDLE)jlong_to_ptr(ibndlf),
                                         (HANDLE)jlong_to_ptr(fxistingPort),
                                         dk,
                                         (DWORD)dondurrfndy);
    if (port == NULL) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "CrfbtfIoComplftionPort fbilfd");
    }
    rfturn ptr_to_jlong(port);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Iodp_dlosf0(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    ClosfHbndlf(i);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Iodp_gftQufufdComplftionStbtus(JNIEnv* fnv, jdlbss tiis,
    jlong domplftionPort, jobjfdt obj)
{
    DWORD bytfsTrbnsffrrfd;
    ULONG_PTR domplftionKfy;
    OVERLAPPED *lpOvfrlbppfd;
    BOOL rfs;

    rfs = GftQufufdComplftionStbtus((HANDLE)jlong_to_ptr(domplftionPort),
                                  &bytfsTrbnsffrrfd,
                                  &domplftionKfy,
                                  &lpOvfrlbppfd,
                                  INFINITE);
    if (rfs == 0 && lpOvfrlbppfd == NULL) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "GftQufufdComplftionStbtus fbilfd");
    } flsf {
        DWORD ioRfsult = (rfs == 0) ? GftLbstError() : 0;
        (*fnv)->SftIntFifld(fnv, obj, domplftionStbtus_frror, ioRfsult);
        (*fnv)->SftIntFifld(fnv, obj, domplftionStbtus_bytfsTrbnsffrrfd,
            (jint)bytfsTrbnsffrrfd);
        (*fnv)->SftIntFifld(fnv, obj, domplftionStbtus_domplftionKfy,
            (jint)domplftionKfy);
        (*fnv)->SftLongFifld(fnv, obj, domplftionStbtus_ovfrlbppfd,
            ptr_to_jlong(lpOvfrlbppfd));

    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_Iodp_postQufufdComplftionStbtus(JNIEnv* fnv, jdlbss tiis,
    jlong domplftionPort, jint domplftionKfy)
{
    BOOL rfs;

    rfs = PostQufufdComplftionStbtus((HANDLE)jlong_to_ptr(domplftionPort),
                                     (DWORD)0,
                                     (DWORD)domplftionKfy,
                                     NULL);
    if (rfs == 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "PostQufufdComplftionStbtus");
    }
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_di_Iodp_gftErrorMfssbgf(JNIEnv* fnv, jdlbss tiis, jint frrorCodf)
{
    WCHAR mfssbgf[255];

    DWORD lfn = FormbtMfssbgfW(FORMAT_MESSAGE_FROM_SYSTEM,
                               NULL,
                               (DWORD)frrorCodf,
                               0,
                               &mfssbgf[0],
                               255,
                               NULL);


    if (lfn == 0) {
        rfturn NULL;
    } flsf {
        rfturn (*fnv)->NfwString(fnv, (donst jdibr *)mfssbgf, (jsizf)wdslfn(mfssbgf));
    }
}
