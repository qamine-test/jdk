/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "nio_util.i"

#indludf "sun_nio_di_KQufufPort.i"

#indludf <unistd.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_KQufufPort_sodkftpbir(JNIEnv* fnv, jdlbss dlbzz, jintArrby sv) {
    int sp[2];
    if (sodkftpbir(PF_UNIX, SOCK_STREAM, 0, sp) == -1) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sodkftpbir fbilfd");
    } flsf {
        jint rfs[2];
        rfs[0] = (jint)sp[0];
        rfs[1] = (jint)sp[1];
        (*fnv)->SftIntArrbyRfgion(fnv, sv, 0, 2, &rfs[0]);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_KQufufPort_intfrrupt(JNIEnv *fnv, jdlbss d, jint fd) {
    int rfs;
    int buf[1];
    buf[0] = 1;
    RESTARTABLE(writf(fd, buf, 1), rfs);
    if (rfs < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "writf fbilfd");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_KQufufPort_drbin1(JNIEnv *fnv, jdlbss dl, jint fd) {
    int rfs;
    dibr buf[1];
    RESTARTABLE(rfbd(fd, buf, 1), rfs);
    if (rfs < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "drbin1 fbilfd");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_KQufufPort_dlosf0(JNIEnv *fnv, jdlbss d, jint fd) {
    int rfs;
    RESTARTABLE(dlosf(fd), rfs);
}
