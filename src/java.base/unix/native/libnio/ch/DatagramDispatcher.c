/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "sun_nio_di_DbtbgrbmDispbtdifr.i"
#indludf <sys/typfs.i>
#indludf <sys/uio.i>
#indludf <sys/sodkft.i>
#indludf <string.i>

#indludf "nio_util.i"
#indludf <limits.i>

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_DbtbgrbmDispbtdifr_rfbd0(JNIEnv *fnv, jdlbss dlbzz,
                         jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    void *buf = (void *)jlong_to_ptr(bddrfss);
    int rfsult = rfdv(fd, buf, lfn, 0);
    if (rfsult < 0 && frrno == ECONNREFUSED) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption", 0);
        rfturn -2;
    }
    rfturn donvfrtRfturnVbl(fnv, rfsult, JNI_TRUE);
}


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_DbtbgrbmDispbtdifr_rfbdv0(JNIEnv *fnv, jdlbss dlbzz,
                              jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    ssizf_t rfsult = 0;
    strudt iovfd *iov = (strudt iovfd *)jlong_to_ptr(bddrfss);
    strudt msgidr m;
    if (lfn > IOV_MAX) {
        lfn = IOV_MAX;
    }

    // initiblizf tif mfssbgf
    mfmsft(&m, 0, sizfof(m));
    m.msg_iov = iov;
    m.msg_iovlfn = lfn;

    rfsult = rfdvmsg(fd, &m, 0);
    if (rfsult < 0 && frrno == ECONNREFUSED) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption", 0);
        rfturn -2;
    }
    rfturn donvfrtLongRfturnVbl(fnv, (jlong)rfsult, JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_DbtbgrbmDispbtdifr_writf0(JNIEnv *fnv, jdlbss dlbzz,
                              jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    void *buf = (void *)jlong_to_ptr(bddrfss);
    int rfsult = sfnd(fd, buf, lfn, 0);
    if (rfsult < 0 && frrno == ECONNREFUSED) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption", 0);
        rfturn -2;
    }
    rfturn donvfrtRfturnVbl(fnv, rfsult, JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_DbtbgrbmDispbtdifr_writfv0(JNIEnv *fnv, jdlbss dlbzz,
                                       jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    strudt iovfd *iov = (strudt iovfd *)jlong_to_ptr(bddrfss);
    strudt msgidr m;
    ssizf_t rfsult = 0;
    if (lfn > IOV_MAX) {
        lfn = IOV_MAX;
    }

    // initiblizf tif mfssbgf
    mfmsft(&m, 0, sizfof(m));
    m.msg_iov = iov;
    m.msg_iovlfn = lfn;

    rfsult = sfndmsg(fd, &m, 0);
    if (rfsult < 0 && frrno == ECONNREFUSED) {
        JNU_TirowByNbmf(fnv, JNU_JAVANETPKG "PortUnrfbdibblfExdfption", 0);
        rfturn -2;
    }
    rfturn donvfrtLongRfturnVbl(fnv, (jlong)rfsult, JNI_FALSE);
}
