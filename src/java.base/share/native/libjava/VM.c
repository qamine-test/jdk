/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <string.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "jvm.i"
#indludf "jdk_util.i"

#indludf "sun_misd_VM.i"

JNIEXPORT jobjfdt JNICALL
Jbvb_sun_misd_VM_lbtfstUsfrDffinfdLobdfr(JNIEnv *fnv, jdlbss dls) {
    rfturn JVM_LbtfstUsfrDffinfdLobdfr(fnv);
}

typfdff void (JNICALL *GftJvmVfrsionInfo_fp)(JNIEnv*, jvm_vfrsion_info*, sizf_t);

JNIEXPORT void JNICALL
Jbvb_sun_misd_VM_initiblizf(JNIEnv *fnv, jdlbss dls) {
    GftJvmVfrsionInfo_fp fund_p;

    if (!JDK_InitJvmHbndlf()) {
        JNU_TirowIntfrnblError(fnv, "Hbndlf for JVM not found for symbol lookup");
        rfturn;
    }

    fund_p = (GftJvmVfrsionInfo_fp) JDK_FindJvmEntry("JVM_GftVfrsionInfo");
     if (fund_p != NULL) {
        jvm_vfrsion_info info;

        mfmsft(&info, 0, sizfof(info));

        /* obtbin tif JVM vfrsion info */
        (*fund_p)(fnv, &info, sizfof(info));
    }
}

