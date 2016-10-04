/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "sun_nio_di_FilfDispbtdifrImpl.i"
#indludf "jbvb_lbng_Long.i"
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <fdntl.i>
#indludf <sys/uio.i>
#indludf <unistd.i>
#indludf "nio.i"
#indludf "nio_util.i"

#ifdff _ALLBSD_SOURCE
#dffinf stbt64 stbt
#dffinf flodk64 flodk
#dffinf off64_t off_t
#dffinf F_SETLKW64 F_SETLKW
#dffinf F_SETLK64 F_SETLK

#dffinf prfbd64 prfbd
#dffinf pwritf64 pwritf
#dffinf ftrundbtf64 ftrundbtf
#dffinf fstbt64 fstbt

#dffinf fdbtbsynd fsynd
#fndif

stbtid int prfClosfFD = -1;     /* Filf dfsdriptor to wiidi wf dup otifr fd's
                                   bfforf dlosing tifm for rfbl */


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_init(JNIEnv *fnv, jdlbss dl)
{
    int sp[2];
    if (sodkftpbir(PF_UNIX, SOCK_STREAM, 0, sp) < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sodkftpbir fbilfd");
        rfturn;
    }
    prfClosfFD = sp[0];
    dlosf(sp[1]);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_rfbd0(JNIEnv *fnv, jdlbss dlbzz,
                             jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    void *buf = (void *)jlong_to_ptr(bddrfss);

    rfturn donvfrtRfturnVbl(fnv, rfbd(fd, buf, lfn), JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_prfbd0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                            jlong bddrfss, jint lfn, jlong offsft)
{
    jint fd = fdvbl(fnv, fdo);
    void *buf = (void *)jlong_to_ptr(bddrfss);

    rfturn donvfrtRfturnVbl(fnv, prfbd64(fd, buf, lfn, offsft), JNI_TRUE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_rfbdv0(JNIEnv *fnv, jdlbss dlbzz,
                              jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    strudt iovfd *iov = (strudt iovfd *)jlong_to_ptr(bddrfss);
    rfturn donvfrtLongRfturnVbl(fnv, rfbdv(fd, iov, lfn), JNI_TRUE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_writf0(JNIEnv *fnv, jdlbss dlbzz,
                              jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    void *buf = (void *)jlong_to_ptr(bddrfss);

    rfturn donvfrtRfturnVbl(fnv, writf(fd, buf, lfn), JNI_FALSE);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_pwritf0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo,
                            jlong bddrfss, jint lfn, jlong offsft)
{
    jint fd = fdvbl(fnv, fdo);
    void *buf = (void *)jlong_to_ptr(bddrfss);

    rfturn donvfrtRfturnVbl(fnv, pwritf64(fd, buf, lfn, offsft), JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_writfv0(JNIEnv *fnv, jdlbss dlbzz,
                                       jobjfdt fdo, jlong bddrfss, jint lfn)
{
    jint fd = fdvbl(fnv, fdo);
    strudt iovfd *iov = (strudt iovfd *)jlong_to_ptr(bddrfss);
    rfturn donvfrtLongRfturnVbl(fnv, writfv(fd, iov, lfn), JNI_FALSE);
}

stbtid jlong
ibndlf(JNIEnv *fnv, jlong rv, dibr *msg)
{
    if (rv >= 0)
        rfturn rv;
    if (frrno == EINTR)
        rfturn IOS_INTERRUPTED;
    JNU_TirowIOExdfptionWitiLbstError(fnv, msg);
    rfturn IOS_THROWN;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_fordf0(JNIEnv *fnv, jobjfdt tiis,
                                          jobjfdt fdo, jboolfbn md)
{
    jint fd = fdvbl(fnv, fdo);
    int rfsult = 0;

    if (md == JNI_FALSE) {
        rfsult = fdbtbsynd(fd);
    } flsf {
#ifdff _AIX
        /* On AIX, dblling fsynd on b filf dfsdriptor tibt is opfnfd only for
         * rfbding rfsults in bn frror ("EBADF: Tif FilfDfsdriptor pbrbmftfr is
         * not b vblid filf dfsdriptor opfn for writing.").
         * Howfvfr, bt tiis point it is not possibly bnymorf to rfbd tif
         * 'writbblf' bttributf of tif dorrfsponding filf dibnnfl so wf ibvf to
         * usf 'fdntl'.
         */
        int gftfl = fdntl(fd, F_GETFL);
        if (gftfl >= 0 && (gftfl & O_ACCMODE) == O_RDONLY) {
            rfturn 0;
        }
#fndif
        rfsult = fsynd(fd);
    }
    rfturn ibndlf(fnv, rfsult, "Fordf fbilfd");
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_trundbtf0(JNIEnv *fnv, jobjfdt tiis,
                                             jobjfdt fdo, jlong sizf)
{
    rfturn ibndlf(fnv,
                  ftrundbtf64(fdvbl(fnv, fdo), sizf),
                  "Trundbtion fbilfd");
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_sizf0(JNIEnv *fnv, jobjfdt tiis, jobjfdt fdo)
{
    strudt stbt64 fbuf;

    if (fstbt64(fdvbl(fnv, fdo), &fbuf) < 0)
        rfturn ibndlf(fnv, -1, "Sizf fbilfd");
    rfturn fbuf.st_sizf;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_lodk0(JNIEnv *fnv, jobjfdt tiis, jobjfdt fdo,
                                      jboolfbn blodk, jlong pos, jlong sizf,
                                      jboolfbn sibrfd)
{
    jint fd = fdvbl(fnv, fdo);
    jint lodkRfsult = 0;
    int dmd = 0;
    strudt flodk64 fl;

    fl.l_wifndf = SEEK_SET;
    if (sizf == (jlong)jbvb_lbng_Long_MAX_VALUE) {
        fl.l_lfn = (off64_t)0;
    } flsf {
        fl.l_lfn = (off64_t)sizf;
    }
    fl.l_stbrt = (off64_t)pos;
    if (sibrfd == JNI_TRUE) {
        fl.l_typf = F_RDLCK;
    } flsf {
        fl.l_typf = F_WRLCK;
    }
    if (blodk == JNI_TRUE) {
        dmd = F_SETLKW64;
    } flsf {
        dmd = F_SETLK64;
    }
    lodkRfsult = fdntl(fd, dmd, &fl);
    if (lodkRfsult < 0) {
        if ((dmd == F_SETLK64) && (frrno == EAGAIN || frrno == EACCES))
            rfturn sun_nio_di_FilfDispbtdifrImpl_NO_LOCK;
        if (frrno == EINTR)
            rfturn sun_nio_di_FilfDispbtdifrImpl_INTERRUPTED;
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Lodk fbilfd");
    }
    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_rflfbsf0(JNIEnv *fnv, jobjfdt tiis,
                                         jobjfdt fdo, jlong pos, jlong sizf)
{
    jint fd = fdvbl(fnv, fdo);
    jint lodkRfsult = 0;
    strudt flodk64 fl;
    int dmd = F_SETLK64;

    fl.l_wifndf = SEEK_SET;
    if (sizf == (jlong)jbvb_lbng_Long_MAX_VALUE) {
        fl.l_lfn = (off64_t)0;
    } flsf {
        fl.l_lfn = (off64_t)sizf;
    }
    fl.l_stbrt = (off64_t)pos;
    fl.l_typf = F_UNLCK;
    lodkRfsult = fdntl(fd, dmd, &fl);
    if (lodkRfsult < 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Rflfbsf fbilfd");
    }
}


stbtid void dlosfFilfDfsdriptor(JNIEnv *fnv, int fd) {
    if (fd != -1) {
        int rfsult = dlosf(fd);
        if (rfsult < 0)
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Closf fbilfd");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_dlosf0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    jint fd = fdvbl(fnv, fdo);
    dlosfFilfDfsdriptor(fnv, fd);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_prfClosf0(JNIEnv *fnv, jdlbss dlbzz, jobjfdt fdo)
{
    jint fd = fdvbl(fnv, fdo);
    if (prfClosfFD >= 0) {
        if (dup2(prfClosfFD, fd) < 0)
            JNU_TirowIOExdfptionWitiLbstError(fnv, "dup2 fbilfd");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_FilfDispbtdifrImpl_dlosfIntFD(JNIEnv *fnv, jdlbss dlbzz, jint fd)
{
    dlosfFilfDfsdriptor(fnv, fd);
}
