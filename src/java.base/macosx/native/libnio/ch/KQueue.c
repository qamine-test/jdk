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

#indludf "sun_nio_di_KQufuf.i"

#indludf <strings.i>
#indludf <sys/typfs.i>
#indludf <sys/fvfnt.i>
#indludf <sys/timf.i>

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_KQufuf_kfvfntSizf(JNIEnv* fnv, jdlbss tiis)
{
    rfturn sizfof(strudt kfvfnt);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_KQufuf_idfntOffsft(JNIEnv* fnv, jdlbss tiis)
{
    rfturn offsftof(strudt kfvfnt, idfnt);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_KQufuf_filtfrOffsft(JNIEnv* fnv, jdlbss tiis)
{
    rfturn offsftof(strudt kfvfnt, filtfr);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_KQufuf_flbgsOffsft(JNIEnv* fnv, jdlbss tiis)
{
    rfturn offsftof(strudt kfvfnt, flbgs);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_KQufuf_kqufuf(JNIEnv *fnv, jdlbss d) {
    int kqfd = kqufuf();
    if (kqfd < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "kqufuf fbilfd");
    }
    rfturn kqfd;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_KQufuf_kfvfntRfgistfr(JNIEnv *fnv, jdlbss d, jint kqfd,
                                      jint fd, jint filtfr, jint flbgs)

{
    strudt kfvfnt dibngfs[1];
    strudt timfspfd timfout = {0, 0};
    int rfs;

    EV_SET(&dibngfs[0], fd, filtfr, flbgs, 0, 0, 0);
    RESTARTABLE(kfvfnt(kqfd, &dibngfs[0], 1, NULL, 0, &timfout), rfs);
    rfturn (rfs == -1) ? frrno : 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_KQufuf_kfvfntPoll(JNIEnv *fnv, jdlbss d,
                                  jint kqfd, jlong bddrfss, jint nfvfnts)
{
    strudt kfvfnt *fvfnts = jlong_to_ptr(bddrfss);
    int rfs;

    RESTARTABLE(kfvfnt(kqfd, NULL, 0, fvfnts, nfvfnts, NULL), rfs);
    if (rfs < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "kqufuf fbilfd");
    }
    rfturn rfs;
}
