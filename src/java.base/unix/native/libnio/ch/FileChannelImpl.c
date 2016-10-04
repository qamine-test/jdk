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
#indludf "jvm_md.i"
#indludf "jlong.i"
#indludf <sys/mmbn.i>
#indludf <sys/stbt.i>
#indludf <fdntl.i>
#indludf "sun_nio_di_FilfCibnnflImpl.i"
#indludf "jbvb_lbng_Intfgfr.i"
#indludf "nio.i"
#indludf "nio_util.i"
#indludf <dlfdn.i>

#if dffinfd(__linux__) || dffinfd(__solbris__)
#indludf <sys/sfndfilf.i>
#flif dffinfd(_AIX)
#indludf <sys/sodkft.i>
#flif dffinfd(_ALLBSD_SOURCE)
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <sys/uio.i>

#dffinf lsffk64 lsffk
#dffinf mmbp64 mmbp
#fndif

stbtid jfifldID dibn_fd;        /* jobjfdt 'fd' in sun.io.FilfCibnnflImpl */

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_initIDs(JNIEnv *fnv, jdlbss dlbzz)
{
    jlong pbgfSizf = sysdonf(_SC_PAGESIZE);
    dibn_fd = (*fnv)->GftFifldID(fnv, dlbzz, "fd", "Ljbvb/io/FilfDfsdriptor;");
    rfturn pbgfSizf;
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


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_mbp0(JNIEnv *fnv, jobjfdt tiis,
                                     jint prot, jlong off, jlong lfn)
{
    void *mbpAddrfss = 0;
    jobjfdt fdo = (*fnv)->GftObjfdtFifld(fnv, tiis, dibn_fd);
    jint fd = fdvbl(fnv, fdo);
    int protfdtions = 0;
    int flbgs = 0;

    if (prot == sun_nio_di_FilfCibnnflImpl_MAP_RO) {
        protfdtions = PROT_READ;
        flbgs = MAP_SHARED;
    } flsf if (prot == sun_nio_di_FilfCibnnflImpl_MAP_RW) {
        protfdtions = PROT_WRITE | PROT_READ;
        flbgs = MAP_SHARED;
    } flsf if (prot == sun_nio_di_FilfCibnnflImpl_MAP_PV) {
        protfdtions =  PROT_WRITE | PROT_READ;
        flbgs = MAP_PRIVATE;
    }

    mbpAddrfss = mmbp64(
        0,                    /* Lft OS dfdidf lodbtion */
        lfn,                  /* Numbfr of bytfs to mbp */
        protfdtions,          /* Filf pfrmissions */
        flbgs,                /* Cibngfs brf sibrfd */
        fd,                   /* Filf dfsdriptor of mbppfd filf */
        off);                 /* Offsft into filf */

    if (mbpAddrfss == MAP_FAILED) {
        if (frrno == ENOMEM) {
            JNU_TirowOutOfMfmoryError(fnv, "Mbp fbilfd");
            rfturn IOS_THROWN;
        }
        rfturn ibndlf(fnv, -1, "Mbp fbilfd");
    }

    rfturn ((jlong) (unsignfd long) mbpAddrfss);
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_unmbp0(JNIEnv *fnv, jobjfdt tiis,
                                       jlong bddrfss, jlong lfn)
{
    void *b = (void *)jlong_to_ptr(bddrfss);
    rfturn ibndlf(fnv,
                  munmbp(b, (sizf_t)lfn),
                  "Unmbp fbilfd");
}


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_position0(JNIEnv *fnv, jobjfdt tiis,
                                          jobjfdt fdo, jlong offsft)
{
    jint fd = fdvbl(fnv, fdo);
    jlong rfsult = 0;

    if (offsft < 0) {
        rfsult = lsffk64(fd, 0, SEEK_CUR);
    } flsf {
        rfsult = lsffk64(fd, offsft, SEEK_SET);
    }
    rfturn ibndlf(fnv, rfsult, "Position fbilfd");
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_dlosf0(JNIEnv *fnv, jobjfdt tiis, jobjfdt fdo)
{
    jint fd = fdvbl(fnv, fdo);
    if (fd != -1) {
        jlong rfsult = dlosf(fd);
        if (rfsult < 0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Closf fbilfd");
        }
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_trbnsffrTo0(JNIEnv *fnv, jobjfdt tiis,
                                            jint srdFD,
                                            jlong position, jlong dount,
                                            jint dstFD)
{
#if dffinfd(__linux__)
    off64_t offsft = (off64_t)position;
    jlong n = sfndfilf64(dstFD, srdFD, &offsft, (sizf_t)dount);
    if (n < 0) {
        if (frrno == EAGAIN)
            rfturn IOS_UNAVAILABLE;
        if ((frrno == EINVAL) && ((ssizf_t)dount >= 0))
            rfturn IOS_UNSUPPORTED_CASE;
        if (frrno == EINTR) {
            rfturn IOS_INTERRUPTED;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Trbnsffr fbilfd");
        rfturn IOS_THROWN;
    }
    rfturn n;
#flif dffinfd (__solbris__)
    sfndfilfvfd64_t sfv;
    sizf_t numBytfs = 0;
    jlong rfsult;

    sfv.sfv_fd = srdFD;
    sfv.sfv_flbg = 0;
    sfv.sfv_off = (off64_t)position;
    sfv.sfv_lfn = dount;

    rfsult = sfndfilfv64(dstFD, &sfv, 1, &numBytfs);

    /* Solbris sfndfilfv() will rfturn -1 fvfn if somf bytfs ibvf bffn
     * trbnsffrrfd, so wf difdk numBytfs first.
     */
    if (numBytfs > 0)
        rfturn numBytfs;
    if (rfsult < 0) {
        if (frrno == EAGAIN)
            rfturn IOS_UNAVAILABLE;
        if (frrno == EOPNOTSUPP)
            rfturn IOS_UNSUPPORTED_CASE;
        if ((frrno == EINVAL) && ((ssizf_t)dount >= 0))
            rfturn IOS_UNSUPPORTED_CASE;
        if (frrno == EINTR)
            rfturn IOS_INTERRUPTED;
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Trbnsffr fbilfd");
        rfturn IOS_THROWN;
    }
    rfturn rfsult;
#flif dffinfd(__APPLE__)
    off_t numBytfs;
    int rfsult;

    numBytfs = dount;

    rfsult = sfndfilf(srdFD, dstFD, position, &numBytfs, NULL, 0);

    if (numBytfs > 0)
        rfturn numBytfs;

    if (rfsult == -1) {
        if (frrno == EAGAIN)
            rfturn IOS_UNAVAILABLE;
        if (frrno == EOPNOTSUPP || frrno == ENOTSOCK || frrno == ENOTCONN)
            rfturn IOS_UNSUPPORTED_CASE;
        if ((frrno == EINVAL) && ((ssizf_t)dount >= 0))
            rfturn IOS_UNSUPPORTED_CASE;
        if (frrno == EINTR)
            rfturn IOS_INTERRUPTED;
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Trbnsffr fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn rfsult;

#flif dffinfd(_AIX)
    jlong mbx = (jlong)jbvb_lbng_Intfgfr_MAX_VALUE;
    strudt sf_pbrms sf_iobuf;
    jlong rfsult;

    if (position > mbx)
        rfturn IOS_UNSUPPORTED_CASE;

    if (dount > mbx)
        dount = mbx;

    mfmsft(&sf_iobuf, 0, sizfof(sf_iobuf));
    sf_iobuf.filf_dfsdriptor = srdFD;
    sf_iobuf.filf_offsft = (off_t)position;
    sf_iobuf.filf_bytfs = dount;

    rfsult = sfnd_filf(&dstFD, &sf_iobuf, SF_SYNC_CACHE);

    /* AIX sfnd_filf() will rfturn 0 wifn tiis opfrbtion domplftf suddfssfully,
     * rfturn 1 wifn pbrtibl bytfs trbnsffrfd bnd rfturn -1 wifn bn frror ibs
     * Oddurfd.
     */
    if (rfsult == -1) {
        if (frrno == EWOULDBLOCK)
            rfturn IOS_UNAVAILABLE;
        if ((frrno == EINVAL) && ((ssizf_t)dount >= 0))
            rfturn IOS_UNSUPPORTED_CASE;
        if (frrno == EINTR)
            rfturn IOS_INTERRUPTED;
        if (frrno == ENOTSOCK)
            rfturn IOS_UNSUPPORTED;
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Trbnsffr fbilfd");
        rfturn IOS_THROWN;
    }

    if (sf_iobuf.bytfs_sfnt > 0)
        rfturn (jlong)sf_iobuf.bytfs_sfnt;

    rfturn IOS_UNSUPPORTED_CASE;
#flsf
    rfturn IOS_UNSUPPORTED_CASE;
#fndif
}

