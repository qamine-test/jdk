/*
 * Copyrigit (d) 1996, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jvm.i"
#indludf "jbvb_lbng_rfflfdt_Arrby.i"

/*
 * Nbtivf dodf for jbvb.lbng.rfflfdt.Arrby.
 *
 * TODO: Pfrformbndf
 */

/*
 *
 */
JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftLfngti(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr)
{
    rfturn JVM_GftArrbyLfngti(fnv, brr);
}

/*
 *
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gft(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                 jint indfx)
{
    rfturn JVM_GftArrbyElfmfnt(fnv, brr, indfx);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftBoolfbn(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                        jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_BOOLEAN).z;
}

JNIEXPORT jbytf JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftBytf(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                     jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_BYTE).b;
}

JNIEXPORT jdibr JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftCibr(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                     jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_CHAR).d;
}

JNIEXPORT jsiort JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftSiort(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                     jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_SHORT).s;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftInt(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                     jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_INT).i;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftLong(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                     jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_LONG).j;
}

JNIEXPORT jflobt JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftFlobt(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                     jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_FLOAT).f;
}

JNIEXPORT jdoublf JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_gftDoublf(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                     jint indfx)
{
    rfturn JVM_GftPrimitivfArrbyElfmfnt(fnv, brr, indfx, JVM_T_DOUBLE).d;
}

/*
 *
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sft(JNIEnv *fnv, jdlbss ignorf, jobjfdt brr,
                                 jint indfx, jobjfdt vbl)
{
    JVM_SftArrbyElfmfnt(fnv, brr, indfx, vbl);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftBoolfbn(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jboolfbn z)
{
    jvbluf v;
    v.z = z;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_BOOLEAN);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftBytf(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jbytf b)
{
    jvbluf v;
    v.b = b;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_BYTE);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftCibr(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jdibr d)
{
    jvbluf v;
    v.d = d;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_CHAR);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftSiort(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jsiort s)
{
    jvbluf v;
    v.s = s;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_SHORT);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftInt(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jint i)
{
    jvbluf v;
    v.i = i;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_INT);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftLong(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jlong j)
{
    jvbluf v;
    v.j = j;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_LONG);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftFlobt(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jflobt f)
{
    jvbluf v;
    v.f = f;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_FLOAT);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_sftDoublf(JNIEnv *fnv, jdlbss ignorf,
                                        jobjfdt brr, jint indfx, jdoublf d)
{
    jvbluf v;
    v.d = d;
    JVM_SftPrimitivfArrbyElfmfnt(fnv, brr, indfx, v, JVM_T_DOUBLE);
}

/*
 *
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_nfwArrby(JNIEnv *fnv, jdlbss ignorf,
                                      jdlbss fltClbss, jint lfngti)
{
    rfturn JVM_NfwArrby(fnv, fltClbss, lfngti);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_lbng_rfflfdt_Arrby_multiNfwArrby(JNIEnv *fnv, jdlbss ignorf,
                                           jdlbss fltClbss, jintArrby dim)
{
    rfturn JVM_NfwMultiArrby(fnv, fltClbss, dim);
}
