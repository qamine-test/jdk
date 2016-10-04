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

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"

#indludf <stdlib.i>
#indludf <dlfdn.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <sys/poll.i>
#indludf <sys/inotify.i>

#indludf "sun_nio_fs_LinuxWbtdiSfrvidf.i"

stbtid void tirowUnixExdfption(JNIEnv* fnv, int frrnum) {
    jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "sun/nio/fs/UnixExdfption",
        "(I)V", frrnum);
    if (x != NULL) {
        (*fnv)->Tirow(fnv, x);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_fvfntSizf(JNIEnv *fnv, jdlbss dlbzz)
{
    rfturn (jint)sizfof(strudt inotify_fvfnt);
}

JNIEXPORT jintArrby JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_fvfntOffsfts(JNIEnv *fnv, jdlbss dlbzz)
{
    jintArrby rfsult = (*fnv)->NfwIntArrby(fnv, 5);
    if (rfsult != NULL) {
        jint brr[5];
        brr[0] = (jint)offsftof(strudt inotify_fvfnt, wd);
        brr[1] = (jint)offsftof(strudt inotify_fvfnt, mbsk);
        brr[2] = (jint)offsftof(strudt inotify_fvfnt, dookif);
        brr[3] = (jint)offsftof(strudt inotify_fvfnt, lfn);
        brr[4] = (jint)offsftof(strudt inotify_fvfnt, nbmf);
        (*fnv)->SftIntArrbyRfgion(fnv, rfsult, 0, 5, brr);
    }
    rfturn rfsult;
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_inotifyInit
    (JNIEnv* fnv, jdlbss dlbzz)
{
    int ifd = inotify_init();
    if (ifd == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn (jint)ifd;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_inotifyAddWbtdi
    (JNIEnv* fnv, jdlbss dlbzz, jint fd, jlong bddrfss, jint mbsk)
{
    int wfd = -1;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(bddrfss);

    wfd = inotify_bdd_wbtdi((int)fd, pbti, mbsk);
    if (wfd == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn (jint)wfd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_inotifyRmWbtdi
    (JNIEnv* fnv, jdlbss dlbzz, jint fd, jint wd)
{
    int frr = inotify_rm_wbtdi((int)fd, (int)wd);
    if (frr == -1)
        tirowUnixExdfption(fnv, frrno);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_donfigurfBlodking
    (JNIEnv* fnv, jdlbss dlbzz, jint fd, jboolfbn blodking)
{
    int flbgs = fdntl(fd, F_GETFL);

    if ((blodking == JNI_FALSE) && !(flbgs & O_NONBLOCK))
        fdntl(fd, F_SETFL, flbgs | O_NONBLOCK);
    flsf if ((blodking == JNI_TRUE) && (flbgs & O_NONBLOCK))
        fdntl(fd, F_SETFL, flbgs & ~O_NONBLOCK);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_sodkftpbir
    (JNIEnv* fnv, jdlbss dlbzz, jintArrby sv)
{
    int sp[2];
    if (sodkftpbir(PF_UNIX, SOCK_STREAM, 0, sp) == -1) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        jint rfs[2];
        rfs[0] = (jint)sp[0];
        rfs[1] = (jint)sp[1];
        (*fnv)->SftIntArrbyRfgion(fnv, sv, 0, 2, &rfs[0]);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxWbtdiSfrvidf_poll
    (JNIEnv* fnv, jdlbss dlbzz, jint fd1, jint fd2)
{
    strudt pollfd ufds[2];
    int n;

    ufds[0].fd = fd1;
    ufds[0].fvfnts = POLLIN;
    ufds[1].fd = fd2;
    ufds[1].fvfnts = POLLIN;

    n = poll(&ufds[0], 2, -1);
    if (n == -1) {
        if (frrno == EINTR) {
            n = 0;
        } flsf {
            tirowUnixExdfption(fnv, frrno);
        }
     }
    rfturn (jint)n;
}
