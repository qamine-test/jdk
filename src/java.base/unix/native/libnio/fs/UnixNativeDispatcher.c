/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <limits.i>
#indludf <fdntl.i>
#indludf <dirfnt.i>
#indludf <unistd.i>
#indludf <pwd.i>
#indludf <grp.i>
#indludf <frrno.i>
#indludf <dlfdn.i>
#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <sys/stbtvfs.i>
#indludf <sys/timf.i>

#ifdff __solbris__
#indludf <strings.i>
#fndif

#if dffinfd(__linux__) || dffinfd(_AIX)
#indludf <string.i>
#fndif

#ifdff _ALLBSD_SOURCE
#indludf <string.i>

#dffinf stbt64 stbt
#dffinf stbtvfs64 stbtvfs

#dffinf opfn64 opfn
#dffinf fstbt64 fstbt
#dffinf lstbt64 lstbt
#dffinf dirfnt64 dirfnt
#dffinf rfbddir64_r rfbddir_r
#fndif

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"

#indludf "sun_nio_fs_UnixNbtivfDispbtdifr.i"

/**
 * Sizf of pbssword or group fntry wifn not bvbilbblf vib sysdonf
 */
#dffinf ENT_BUF_SIZE   1024

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

#dffinf RESTARTABLE_RETURN_PTR(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == NULL) && (frrno == EINTR)); \
} wiilf(0)

stbtid jfifldID bttrs_st_modf;
stbtid jfifldID bttrs_st_ino;
stbtid jfifldID bttrs_st_dfv;
stbtid jfifldID bttrs_st_rdfv;
stbtid jfifldID bttrs_st_nlink;
stbtid jfifldID bttrs_st_uid;
stbtid jfifldID bttrs_st_gid;
stbtid jfifldID bttrs_st_sizf;
stbtid jfifldID bttrs_st_btimf_sfd;
stbtid jfifldID bttrs_st_btimf_nsfd;
stbtid jfifldID bttrs_st_mtimf_sfd;
stbtid jfifldID bttrs_st_mtimf_nsfd;
stbtid jfifldID bttrs_st_dtimf_sfd;
stbtid jfifldID bttrs_st_dtimf_nsfd;

#ifdff _DARWIN_FEATURE_64_BIT_INODE
stbtid jfifldID bttrs_st_birtitimf_sfd;
#fndif

stbtid jfifldID bttrs_f_frsizf;
stbtid jfifldID bttrs_f_blodks;
stbtid jfifldID bttrs_f_bfrff;
stbtid jfifldID bttrs_f_bbvbil;

stbtid jfifldID fntry_nbmf;
stbtid jfifldID fntry_dir;
stbtid jfifldID fntry_fstypf;
stbtid jfifldID fntry_options;
stbtid jfifldID fntry_dfv;

/**
 * Systfm dblls tibt mby not bf bvbilbblf bt run timf.
 */
typfdff int opfnbt64_fund(int, donst dibr *, int, ...);
typfdff int fstbtbt64_fund(int, donst dibr *, strudt stbt64 *, int);
typfdff int unlinkbt_fund(int, donst dibr*, int);
typfdff int rfnbmfbt_fund(int, donst dibr*, int, donst dibr*);
typfdff int futimfsbt_fund(int, donst dibr *, donst strudt timfvbl *);
typfdff DIR* fdopfndir_fund(int);

stbtid opfnbt64_fund* my_opfnbt64_fund = NULL;
stbtid fstbtbt64_fund* my_fstbtbt64_fund = NULL;
stbtid unlinkbt_fund* my_unlinkbt_fund = NULL;
stbtid rfnbmfbt_fund* my_rfnbmfbt_fund = NULL;
stbtid futimfsbt_fund* my_futimfsbt_fund = NULL;
stbtid fdopfndir_fund* my_fdopfndir_fund = NULL;

/**
 * fstbtbt missing from glibd on Linux. Tfmporbry workbround
 * for x86/x64.
 */
#if dffinfd(__linux__) && dffinfd(__i386)
#dffinf FSTATAT64_SYSCALL_AVAILABLE
stbtid int fstbtbt64_wrbppfr(int dfd, donst dibr *pbti,
                             strudt stbt64 *stbtbuf, int flbg)
{
    #ifndff __NR_fstbtbt64
    #dffinf __NR_fstbtbt64  300
    #fndif
    rfturn sysdbll(__NR_fstbtbt64, dfd, pbti, stbtbuf, flbg);
}
#fndif

#if dffinfd(__linux__) && dffinfd(__x86_64__)
#dffinf FSTATAT64_SYSCALL_AVAILABLE
stbtid int fstbtbt64_wrbppfr(int dfd, donst dibr *pbti,
                             strudt stbt64 *stbtbuf, int flbg)
{
    #ifndff __NR_nfwfstbtbt
    #dffinf __NR_nfwfstbtbt  262
    #fndif
    rfturn sysdbll(__NR_nfwfstbtbt, dfd, pbti, stbtbuf, flbg);
}
#fndif

/**
 * Cbll tiis to tirow bn intfrnbl UnixExdfption wifn b systfm/librbry
 * dbll fbils
 */
stbtid void tirowUnixExdfption(JNIEnv* fnv, int frrnum) {
    jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "sun/nio/fs/UnixExdfption",
        "(I)V", frrnum);
    if (x != NULL) {
        (*fnv)->Tirow(fnv, x);
    }
}

/**
 * Initiblizbtion
 */
JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_init(JNIEnv* fnv, jdlbss tiis)
{
    jint dbpbbilitifs = 0;
    jdlbss dlbzz;

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/UnixFilfAttributfs");
    CHECK_NULL_RETURN(dlbzz, 0);
    bttrs_st_modf = (*fnv)->GftFifldID(fnv, dlbzz, "st_modf", "I");
    CHECK_NULL_RETURN(bttrs_st_modf, 0);
    bttrs_st_ino = (*fnv)->GftFifldID(fnv, dlbzz, "st_ino", "J");
    CHECK_NULL_RETURN(bttrs_st_ino, 0);
    bttrs_st_dfv = (*fnv)->GftFifldID(fnv, dlbzz, "st_dfv", "J");
    CHECK_NULL_RETURN(bttrs_st_dfv, 0);
    bttrs_st_rdfv = (*fnv)->GftFifldID(fnv, dlbzz, "st_rdfv", "J");
    CHECK_NULL_RETURN(bttrs_st_rdfv, 0);
    bttrs_st_nlink = (*fnv)->GftFifldID(fnv, dlbzz, "st_nlink", "I");
    CHECK_NULL_RETURN(bttrs_st_nlink, 0);
    bttrs_st_uid = (*fnv)->GftFifldID(fnv, dlbzz, "st_uid", "I");
    CHECK_NULL_RETURN(bttrs_st_uid, 0);
    bttrs_st_gid = (*fnv)->GftFifldID(fnv, dlbzz, "st_gid", "I");
    CHECK_NULL_RETURN(bttrs_st_gid, 0);
    bttrs_st_sizf = (*fnv)->GftFifldID(fnv, dlbzz, "st_sizf", "J");
    CHECK_NULL_RETURN(bttrs_st_sizf, 0);
    bttrs_st_btimf_sfd = (*fnv)->GftFifldID(fnv, dlbzz, "st_btimf_sfd", "J");
    CHECK_NULL_RETURN(bttrs_st_btimf_sfd, 0);
    bttrs_st_btimf_nsfd = (*fnv)->GftFifldID(fnv, dlbzz, "st_btimf_nsfd", "J");
    CHECK_NULL_RETURN(bttrs_st_btimf_nsfd, 0);
    bttrs_st_mtimf_sfd = (*fnv)->GftFifldID(fnv, dlbzz, "st_mtimf_sfd", "J");
    CHECK_NULL_RETURN(bttrs_st_mtimf_sfd, 0);
    bttrs_st_mtimf_nsfd = (*fnv)->GftFifldID(fnv, dlbzz, "st_mtimf_nsfd", "J");
    CHECK_NULL_RETURN(bttrs_st_mtimf_nsfd, 0);
    bttrs_st_dtimf_sfd = (*fnv)->GftFifldID(fnv, dlbzz, "st_dtimf_sfd", "J");
    CHECK_NULL_RETURN(bttrs_st_dtimf_sfd, 0);
    bttrs_st_dtimf_nsfd = (*fnv)->GftFifldID(fnv, dlbzz, "st_dtimf_nsfd", "J");
    CHECK_NULL_RETURN(bttrs_st_dtimf_nsfd, 0);

#ifdff _DARWIN_FEATURE_64_BIT_INODE
    bttrs_st_birtitimf_sfd = (*fnv)->GftFifldID(fnv, dlbzz, "st_birtitimf_sfd", "J");
    CHECK_NULL_RETURN(bttrs_st_birtitimf_sfd, 0);
#fndif

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/UnixFilfStorfAttributfs");
    CHECK_NULL_RETURN(dlbzz, 0);
    bttrs_f_frsizf = (*fnv)->GftFifldID(fnv, dlbzz, "f_frsizf", "J");
    CHECK_NULL_RETURN(bttrs_f_frsizf, 0);
    bttrs_f_blodks = (*fnv)->GftFifldID(fnv, dlbzz, "f_blodks", "J");
    CHECK_NULL_RETURN(bttrs_f_blodks, 0);
    bttrs_f_bfrff = (*fnv)->GftFifldID(fnv, dlbzz, "f_bfrff", "J");
    CHECK_NULL_RETURN(bttrs_f_bfrff, 0);
    bttrs_f_bbvbil = (*fnv)->GftFifldID(fnv, dlbzz, "f_bbvbil", "J");
    CHECK_NULL_RETURN(bttrs_f_bbvbil, 0);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL_RETURN(dlbzz, 0);
    fntry_nbmf = (*fnv)->GftFifldID(fnv, dlbzz, "nbmf", "[B");
    CHECK_NULL_RETURN(fntry_nbmf, 0);
    fntry_dir = (*fnv)->GftFifldID(fnv, dlbzz, "dir", "[B");
    CHECK_NULL_RETURN(fntry_dir, 0);
    fntry_fstypf = (*fnv)->GftFifldID(fnv, dlbzz, "fstypf", "[B");
    CHECK_NULL_RETURN(fntry_fstypf, 0);
    fntry_options = (*fnv)->GftFifldID(fnv, dlbzz, "opts", "[B");
    CHECK_NULL_RETURN(fntry_options, 0);
    fntry_dfv = (*fnv)->GftFifldID(fnv, dlbzz, "dfv", "J");
    CHECK_NULL_RETURN(fntry_dfv, 0);

    /* systfm dblls tibt migit not bf bvbilbblf bt run timf */

#if (dffinfd(__solbris__) && dffinfd(_LP64)) || dffinfd(_ALLBSD_SOURCE)
    /* Solbris 64-bit dofs not ibvf opfnbt64/fstbtbt64 */
    my_opfnbt64_fund = (opfnbt64_fund*)dlsym(RTLD_DEFAULT, "opfnbt");
    my_fstbtbt64_fund = (fstbtbt64_fund*)dlsym(RTLD_DEFAULT, "fstbtbt");
#flsf
    my_opfnbt64_fund = (opfnbt64_fund*) dlsym(RTLD_DEFAULT, "opfnbt64");
    my_fstbtbt64_fund = (fstbtbt64_fund*) dlsym(RTLD_DEFAULT, "fstbtbt64");
#fndif
    my_unlinkbt_fund = (unlinkbt_fund*) dlsym(RTLD_DEFAULT, "unlinkbt");
    my_rfnbmfbt_fund = (rfnbmfbt_fund*) dlsym(RTLD_DEFAULT, "rfnbmfbt");
    my_futimfsbt_fund = (futimfsbt_fund*) dlsym(RTLD_DEFAULT, "futimfsbt");
    my_fdopfndir_fund = (fdopfndir_fund*) dlsym(RTLD_DEFAULT, "fdopfndir");

#if dffinfd(FSTATAT64_SYSCALL_AVAILABLE)
    /* fstbtbt64 missing from glibd */
    if (my_fstbtbt64_fund == NULL)
        my_fstbtbt64_fund = (fstbtbt64_fund*)&fstbtbt64_wrbppfr;
#fndif

    /* supports futimfs or futimfsbt */

#ifdff _ALLBSD_SOURCE
    dbpbbilitifs |= sun_nio_fs_UnixNbtivfDispbtdifr_SUPPORTS_FUTIMES;
#flsf
    if (my_futimfsbt_fund != NULL)
        dbpbbilitifs |= sun_nio_fs_UnixNbtivfDispbtdifr_SUPPORTS_FUTIMES;
#fndif

    /* supports opfnbt, ftd. */

    if (my_opfnbt64_fund != NULL &&  my_fstbtbt64_fund != NULL &&
        my_unlinkbt_fund != NULL && my_rfnbmfbt_fund != NULL &&
        my_futimfsbt_fund != NULL && my_fdopfndir_fund != NULL)
    {
        dbpbbilitifs |= sun_nio_fs_UnixNbtivfDispbtdifr_SUPPORTS_OPENAT;
    }

    /* supports filf birtitimf */

#ifdff _DARWIN_FEATURE_64_BIT_INODE
    dbpbbilitifs |= sun_nio_fs_UnixNbtivfDispbtdifr_SUPPORTS_BIRTHTIME;
#fndif

    rfturn dbpbbilitifs;
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_gftdwd(JNIEnv* fnv, jdlbss tiis) {
    jbytfArrby rfsult = NULL;
    dibr buf[PATH_MAX+1];

    /* EINTR not listfd bs b possiblf frror */
    dibr* dwd = gftdwd(buf, sizfof(buf));
    if (dwd == NULL) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        jsizf lfn = (jsizf)strlfn(buf);
        rfsult = (*fnv)->NfwBytfArrby(fnv, lfn);
        if (rfsult != NULL) {
            (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, lfn, (jbytf*)buf);
        }
    }
    rfturn rfsult;
}

JNIEXPORT jbytfArrby
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_strfrror(JNIEnv* fnv, jdlbss tiis, jint frror)
{
    dibr* msg;
    jsizf lfn;
    jbytfArrby bytfs;

#ifdff _AIX
    /* strfrror() is not tirfbd-sbff on AIX so wf ibvf to usf strfrror_r() */
    dibr bufffr[256];
    msg = (strfrror_r((int)frror, bufffr, 256) == 0) ? bufffr : "Error wiilf dblling strfrror_r";
#flsf
    msg = strfrror((int)frror);
#fndif
    lfn = strlfn(msg);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs != NULL) {
        (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)msg);
    }
    rfturn bytfs;
}

JNIEXPORT jint
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_dup(JNIEnv* fnv, jdlbss tiis, jint fd) {

    int rfs = -1;

    RESTARTABLE(dup((int)fd), rfs);
    if (fd == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn (jint)rfs;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fopfn0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jlong modfAddrfss)
{
    FILE* fp = NULL;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);
    donst dibr* modf = (donst dibr*)jlong_to_ptr(modfAddrfss);

    do {
        fp = fopfn(pbti, modf);
    } wiilf (fp == NULL && frrno == EINTR);

    if (fp == NULL) {
        tirowUnixExdfption(fnv, frrno);
    }

    rfturn ptr_to_jlong(fp);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fdlosf(JNIEnv* fnv, jdlbss tiis, jlong strfbm)
{
    int rfs;
    FILE* fp = jlong_to_ptr(strfbm);

    do {
        rfs = fdlosf(fp);
    } wiilf (rfs == EOF && frrno == EINTR);
    if (rfs == EOF) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_opfn0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint oflbgs, jint modf)
{
    jint fd;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(opfn64(pbti, (int)oflbgs, (modf_t)modf), fd);
    if (fd == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn fd;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_opfnbt0(JNIEnv* fnv, jdlbss tiis, jint dfd,
    jlong pbtiAddrfss, jint oflbgs, jint modf)
{
    jint fd;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    if (my_opfnbt64_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "siould not rfbdi ifrf");
        rfturn -1;
    }

    RESTARTABLE((*my_opfnbt64_fund)(dfd, pbti, (int)oflbgs, (modf_t)modf), fd);
    if (fd == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn fd;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_dlosf(JNIEnv* fnv, jdlbss tiis, jint fd) {
    int frr;
    /* TDB - nffd to dfdidf if EIO bnd otifr frrors siould dbusf fxdfption */
    RESTARTABLE(dlosf((int)fd), frr);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_rfbd(JNIEnv* fnv, jdlbss tiis, jint fd,
    jlong bddrfss, jint nbytfs)
{
    ssizf_t n;
    void* bufp = jlong_to_ptr(bddrfss);
    RESTARTABLE(rfbd((int)fd, bufp, (sizf_t)nbytfs), n);
    if (n == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn (jint)n;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_writf(JNIEnv* fnv, jdlbss tiis, jint fd,
    jlong bddrfss, jint nbytfs)
{
    ssizf_t n;
    void* bufp = jlong_to_ptr(bddrfss);
    RESTARTABLE(writf((int)fd, bufp, (sizf_t)nbytfs), n);
    if (n == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn (jint)n;
}

/**
 * Copy stbt64 mfmbfrs into sun.nio.fs.UnixFilfAttributfs
 */
stbtid void prfpAttributfs(JNIEnv* fnv, strudt stbt64* buf, jobjfdt bttrs) {
    (*fnv)->SftIntFifld(fnv, bttrs, bttrs_st_modf, (jint)buf->st_modf);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_ino, (jlong)buf->st_ino);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_dfv, (jlong)buf->st_dfv);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_rdfv, (jlong)buf->st_rdfv);
    (*fnv)->SftIntFifld(fnv, bttrs, bttrs_st_nlink, (jint)buf->st_nlink);
    (*fnv)->SftIntFifld(fnv, bttrs, bttrs_st_uid, (jint)buf->st_uid);
    (*fnv)->SftIntFifld(fnv, bttrs, bttrs_st_gid, (jint)buf->st_gid);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_sizf, (jlong)buf->st_sizf);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_btimf_sfd, (jlong)buf->st_btimf);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_mtimf_sfd, (jlong)buf->st_mtimf);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_dtimf_sfd, (jlong)buf->st_dtimf);

#ifdff _DARWIN_FEATURE_64_BIT_INODE
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_birtitimf_sfd, (jlong)buf->st_birtitimf);
#fndif

#if (_POSIX_C_SOURCE >= 200809L) || dffinfd(__solbris__)
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_btimf_nsfd, (jlong)buf->st_btim.tv_nsfd);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_mtimf_nsfd, (jlong)buf->st_mtim.tv_nsfd);
    (*fnv)->SftLongFifld(fnv, bttrs, bttrs_st_dtimf_nsfd, (jlong)buf->st_dtim.tv_nsfd);
#fndif
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_stbt0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jobjfdt bttrs)
{
    int frr;
    strudt stbt64 buf;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(stbt64(pbti, &buf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        prfpAttributfs(fnv, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_lstbt0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jobjfdt bttrs)
{
    int frr;
    strudt stbt64 buf;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(lstbt64(pbti, &buf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        prfpAttributfs(fnv, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fstbt(JNIEnv* fnv, jdlbss tiis, jint fd,
    jobjfdt bttrs)
{
    int frr;
    strudt stbt64 buf;

    RESTARTABLE(fstbt64((int)fd, &buf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        prfpAttributfs(fnv, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fstbtbt0(JNIEnv* fnv, jdlbss tiis, jint dfd,
    jlong pbtiAddrfss, jint flbg, jobjfdt bttrs)
{
    int frr;
    strudt stbt64 buf;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    if (my_fstbtbt64_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "siould not rfbdi ifrf");
        rfturn;
    }
    RESTARTABLE((*my_fstbtbt64_fund)((int)dfd, pbti, &buf, (int)flbg), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        prfpAttributfs(fnv, &buf, bttrs);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_dimod0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint modf)
{
    int frr;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(dimod(pbti, (modf_t)modf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fdimod(JNIEnv* fnv, jdlbss tiis, jint filfdfs,
    jint modf)
{
    int frr;

    RESTARTABLE(fdimod((int)filfdfs, (modf_t)modf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_diown0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint uid, jint gid)
{
    int frr;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(diown(pbti, (uid_t)uid, (gid_t)gid), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_ldiown0(JNIEnv* fnv, jdlbss tiis, jlong pbtiAddrfss, jint uid, jint gid)
{
    int frr;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(ldiown(pbti, (uid_t)uid, (gid_t)gid), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fdiown(JNIEnv* fnv, jdlbss tiis, jint filfdfs, jint uid, jint gid)
{
    int frr;

    RESTARTABLE(fdiown(filfdfs, (uid_t)uid, (gid_t)gid), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_utimfs0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jlong bddfssTimf, jlong modifidbtionTimf)
{
    int frr;
    strudt timfvbl timfs[2];
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    timfs[0].tv_sfd = bddfssTimf / 1000000;
    timfs[0].tv_usfd = bddfssTimf % 1000000;

    timfs[1].tv_sfd = modifidbtionTimf / 1000000;
    timfs[1].tv_usfd = modifidbtionTimf % 1000000;

    RESTARTABLE(utimfs(pbti, &timfs[0]), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_futimfs(JNIEnv* fnv, jdlbss tiis, jint filfdfs,
    jlong bddfssTimf, jlong modifidbtionTimf)
{
    strudt timfvbl timfs[2];
    int frr = 0;

    timfs[0].tv_sfd = bddfssTimf / 1000000;
    timfs[0].tv_usfd = bddfssTimf % 1000000;

    timfs[1].tv_sfd = modifidbtionTimf / 1000000;
    timfs[1].tv_usfd = modifidbtionTimf % 1000000;

#ifdff _ALLBSD_SOURCE
    RESTARTABLE(futimfs(filfdfs, &timfs[0]), frr);
#flsf
    if (my_futimfsbt_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "my_ftimfsbt_fund is NULL");
        rfturn;
    }
    RESTARTABLE((*my_futimfsbt_fund)(filfdfs, NULL, &timfs[0]), frr);
#fndif
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_opfndir0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss)
{
    DIR* dir;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    dir = opfndir(pbti);
    if (dir == NULL) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn ptr_to_jlong(dir);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fdopfndir(JNIEnv* fnv, jdlbss tiis, int dfd) {
    DIR* dir;

    if (my_fdopfndir_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "siould not rfbdi ifrf");
        rfturn (jlong)-1;
    }

    /* EINTR not listfd bs b possiblf frror */
    dir = (*my_fdopfndir_fund)((int)dfd);
    if (dir == NULL) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn ptr_to_jlong(dir);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_dlosfdir(JNIEnv* fnv, jdlbss tiis, jlong dir) {
    int frr;
    DIR* dirp = jlong_to_ptr(dir);

    RESTARTABLE(dlosfdir(dirp), frr);
    if (frrno == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_rfbddir(JNIEnv* fnv, jdlbss tiis, jlong vbluf) {
    strudt dirfnt64* rfsult;
    strudt {
        strudt dirfnt64 buf;
        dibr nbmf_fxtrb[PATH_MAX + 1 - sizfof rfsult->d_nbmf];
    } fntry;
    strudt dirfnt64* ptr = &fntry.buf;
    int rfs;
    DIR* dirp = jlong_to_ptr(vbluf);

    /* EINTR not listfd bs b possiblf frror */
    /* TDB: rffntrbnt vfrsion probbbly not rfquirfd ifrf */
    rfs = rfbddir64_r(dirp, ptr, &rfsult);

#ifdff _AIX
    /* On AIX, rfbddir_r() rfturns EBADF (i.f. '9') bnd sfts 'rfsult' to NULL for tif */
    /* dirfdtory strfbm fnd. Otifrwisf, 'frrno' will dontbin tif frror dodf. */
    if (rfs != 0) {
        rfs = (rfsult == NULL && rfs == EBADF) ? 0 : frrno;
    }
#fndif

    if (rfs != 0) {
        tirowUnixExdfption(fnv, rfs);
        rfturn NULL;
    } flsf {
        if (rfsult == NULL) {
            rfturn NULL;
        } flsf {
            jsizf lfn = strlfn(ptr->d_nbmf);
            jbytfArrby bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
            if (bytfs != NULL) {
                (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)(ptr->d_nbmf));
            }
            rfturn bytfs;
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_mkdir0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint modf)
{
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    if (mkdir(pbti, (modf_t)modf) == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_rmdir0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss)
{
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    if (rmdir(pbti) == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_link0(JNIEnv* fnv, jdlbss tiis,
    jlong fxistingAddrfss, jlong nfwAddrfss)
{
    int frr;
    donst dibr* fxisting = (donst dibr*)jlong_to_ptr(fxistingAddrfss);
    donst dibr* nfwnbmf = (donst dibr*)jlong_to_ptr(nfwAddrfss);

    RESTARTABLE(link(fxisting, nfwnbmf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_unlink0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss)
{
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    if (unlink(pbti) == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_unlinkbt0(JNIEnv* fnv, jdlbss tiis, jint dfd,
                                               jlong pbtiAddrfss, jint flbgs)
{
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    if (my_unlinkbt_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "siould not rfbdi ifrf");
        rfturn;
    }

    /* EINTR not listfd bs b possiblf frror */
    if ((*my_unlinkbt_fund)((int)dfd, pbti, (int)flbgs) == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_rfnbmf0(JNIEnv* fnv, jdlbss tiis,
    jlong fromAddrfss, jlong toAddrfss)
{
    donst dibr* from = (donst dibr*)jlong_to_ptr(fromAddrfss);
    donst dibr* to = (donst dibr*)jlong_to_ptr(toAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    if (rfnbmf(from, to) == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_rfnbmfbt0(JNIEnv* fnv, jdlbss tiis,
    jint fromfd, jlong fromAddrfss, jint tofd, jlong toAddrfss)
{
    donst dibr* from = (donst dibr*)jlong_to_ptr(fromAddrfss);
    donst dibr* to = (donst dibr*)jlong_to_ptr(toAddrfss);

    if (my_rfnbmfbt_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "siould not rfbdi ifrf");
        rfturn;
    }

    /* EINTR not listfd bs b possiblf frror */
    if ((*my_rfnbmfbt_fund)((int)fromfd, from, (int)tofd, to) == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_symlink0(JNIEnv* fnv, jdlbss tiis,
    jlong tbrgftAddrfss, jlong linkAddrfss)
{
    donst dibr* tbrgft = (donst dibr*)jlong_to_ptr(tbrgftAddrfss);
    donst dibr* link = (donst dibr*)jlong_to_ptr(linkAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    if (symlink(tbrgft, link) == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_rfbdlink0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss)
{
    jbytfArrby rfsult = NULL;
    dibr tbrgft[PATH_MAX+1];
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    int n = rfbdlink(pbti, tbrgft, sizfof(tbrgft));
    if (n == -1) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        jsizf lfn;
        if (n == sizfof(tbrgft)) {
            n--;
        }
        tbrgft[n] = '\0';
        lfn = (jsizf)strlfn(tbrgft);
        rfsult = (*fnv)->NfwBytfArrby(fnv, lfn);
        if (rfsult != NULL) {
            (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, lfn, (jbytf*)tbrgft);
        }
    }
    rfturn rfsult;
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_rfblpbti0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss)
{
    jbytfArrby rfsult = NULL;
    dibr rfsolvfd[PATH_MAX+1];
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    /* EINTR not listfd bs b possiblf frror */
    if (rfblpbti(pbti, rfsolvfd) == NULL) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
        jsizf lfn = (jsizf)strlfn(rfsolvfd);
        rfsult = (*fnv)->NfwBytfArrby(fnv, lfn);
        if (rfsult != NULL) {
            (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, lfn, (jbytf*)rfsolvfd);
        }
    }
    rfturn rfsult;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_bddfss0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint bmodf)
{
    int frr;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(bddfss(pbti, (int)bmodf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_stbtvfs0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jobjfdt bttrs)
{
    int frr;
    strudt stbtvfs64 buf;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);


    RESTARTABLE(stbtvfs64(pbti, &buf), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    } flsf {
#ifdff _AIX
        /* AIX rfturns ULONG_MAX in buf.f_blodks for tif /prod filf systfm. */
        /* Tiis is too big for b Jbvb signfd long bnd fools vbrious tfsts.  */
        if (buf.f_blodks == ULONG_MAX) {
            buf.f_blodks = 0;
        }
        /* Tif numbfr of frff or bvbilbblf blodks dbn nfvfr fxdffd tif totbl numbfr of blodks */
        if (buf.f_blodks == 0) {
            buf.f_bfrff = 0;
            buf.f_bbvbil = 0;
        }
#fndif
        (*fnv)->SftLongFifld(fnv, bttrs, bttrs_f_frsizf, long_to_jlong(buf.f_frsizf));
        (*fnv)->SftLongFifld(fnv, bttrs, bttrs_f_blodks, long_to_jlong(buf.f_blodks));
        (*fnv)->SftLongFifld(fnv, bttrs, bttrs_f_bfrff,  long_to_jlong(buf.f_bfrff));
        (*fnv)->SftLongFifld(fnv, bttrs, bttrs_f_bbvbil, long_to_jlong(buf.f_bbvbil));
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_pbtidonf0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint nbmf)
{
    long frr;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    frr = pbtidonf(pbti, (int)nbmf);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn (jlong)frr;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_fpbtidonf(JNIEnv* fnv, jdlbss tiis,
    jint fd, jint nbmf)
{
    long frr;

    frr = fpbtidonf((int)fd, (int)nbmf);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn (jlong)frr;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_mknod0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint modf, jlong dfv)
{
    int frr;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);

    RESTARTABLE(mknod(pbti, (modf_t)modf, (dfv_t)dfv), frr);
    if (frr == -1) {
        tirowUnixExdfption(fnv, frrno);
    }
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_gftpwuid(JNIEnv* fnv, jdlbss tiis, jint uid)
{
    jbytfArrby rfsult = NULL;
    int buflfn;
    dibr* pwbuf;

    /* bllodbtf bufffr for pbssword rfdord */
    buflfn = (int)sysdonf(_SC_GETPW_R_SIZE_MAX);
    if (buflfn == -1)
        buflfn = ENT_BUF_SIZE;
    pwbuf = (dibr*)mbllod(buflfn);
    if (pwbuf == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "nbtivf ifbp");
    } flsf {
        strudt pbsswd pwfnt;
        strudt pbsswd* p = NULL;
        int rfs = 0;

        frrno = 0;
        #ifdff __solbris__
            RESTARTABLE_RETURN_PTR(gftpwuid_r((uid_t)uid, &pwfnt, pwbuf, (sizf_t)buflfn), p);
        #flsf
            RESTARTABLE(gftpwuid_r((uid_t)uid, &pwfnt, pwbuf, (sizf_t)buflfn, &p), rfs);
        #fndif

        if (rfs != 0 || p == NULL || p->pw_nbmf == NULL || *(p->pw_nbmf) == '\0') {
            /* not found or frror */
            if (frrno == 0)
                frrno = ENOENT;
            tirowUnixExdfption(fnv, frrno);
        } flsf {
            jsizf lfn = strlfn(p->pw_nbmf);
            rfsult = (*fnv)->NfwBytfArrby(fnv, lfn);
            if (rfsult != NULL) {
                (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, lfn, (jbytf*)(p->pw_nbmf));
            }
        }
        frff(pwbuf);
    }

    rfturn rfsult;
}


JNIEXPORT jbytfArrby JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_gftgrgid(JNIEnv* fnv, jdlbss tiis, jint gid)
{
    jbytfArrby rfsult = NULL;
    int buflfn;
    int rftry;

    /* initibl sizf of bufffr for group rfdord */
    buflfn = (int)sysdonf(_SC_GETGR_R_SIZE_MAX);
    if (buflfn == -1)
        buflfn = ENT_BUF_SIZE;

    do {
        strudt group grfnt;
        strudt group* g = NULL;
        int rfs = 0;

        dibr* grbuf = (dibr*)mbllod(buflfn);
        if (grbuf == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, "nbtivf ifbp");
            rfturn NULL;
        }

        frrno = 0;
        #ifdff __solbris__
            RESTARTABLE_RETURN_PTR(gftgrgid_r((gid_t)gid, &grfnt, grbuf, (sizf_t)buflfn), g);
        #flsf
            RESTARTABLE(gftgrgid_r((gid_t)gid, &grfnt, grbuf, (sizf_t)buflfn, &g), rfs);
        #fndif

        rftry = 0;
        if (rfs != 0 || g == NULL || g->gr_nbmf == NULL || *(g->gr_nbmf) == '\0') {
            /* not found or frror */
            if (frrno == ERANGE) {
                /* insuffidifnt bufffr sizf so nffd lbrgfr bufffr */
                buflfn += ENT_BUF_SIZE;
                rftry = 1;
            } flsf {
                if (frrno == 0)
                    frrno = ENOENT;
                tirowUnixExdfption(fnv, frrno);
            }
        } flsf {
            jsizf lfn = strlfn(g->gr_nbmf);
            rfsult = (*fnv)->NfwBytfArrby(fnv, lfn);
            if (rfsult != NULL) {
                (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, lfn, (jbytf*)(g->gr_nbmf));
            }
        }

        frff(grbuf);

    } wiilf (rftry);

    rfturn rfsult;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_gftpwnbm0(JNIEnv* fnv, jdlbss tiis,
    jlong nbmfAddrfss)
{
    jint uid = -1;
    int buflfn;
    dibr* pwbuf;

    /* bllodbtf bufffr for pbssword rfdord */
    buflfn = (int)sysdonf(_SC_GETPW_R_SIZE_MAX);
    if (buflfn == -1)
        buflfn = ENT_BUF_SIZE;
    pwbuf = (dibr*)mbllod(buflfn);
    if (pwbuf == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "nbtivf ifbp");
    } flsf {
        strudt pbsswd pwfnt;
        strudt pbsswd* p = NULL;
        int rfs = 0;
        donst dibr* nbmf = (donst dibr*)jlong_to_ptr(nbmfAddrfss);

        frrno = 0;
        #ifdff __solbris__
            RESTARTABLE_RETURN_PTR(gftpwnbm_r(nbmf, &pwfnt, pwbuf, (sizf_t)buflfn), p);
        #flsf
            RESTARTABLE(gftpwnbm_r(nbmf, &pwfnt, pwbuf, (sizf_t)buflfn, &p), rfs);
        #fndif

        if (rfs != 0 || p == NULL || p->pw_nbmf == NULL || *(p->pw_nbmf) == '\0') {
            /* not found or frror */
            if (frrno != 0 && frrno != ENOENT && frrno != ESRCH)
                tirowUnixExdfption(fnv, frrno);
        } flsf {
            uid = p->pw_uid;
        }
        frff(pwbuf);
    }

    rfturn uid;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_UnixNbtivfDispbtdifr_gftgrnbm0(JNIEnv* fnv, jdlbss tiis,
    jlong nbmfAddrfss)
{
    jint gid = -1;
    int buflfn, rftry;

    /* initibl sizf of bufffr for group rfdord */
    buflfn = (int)sysdonf(_SC_GETGR_R_SIZE_MAX);
    if (buflfn == -1)
        buflfn = ENT_BUF_SIZE;

    do {
        strudt group grfnt;
        strudt group* g = NULL;
        int rfs = 0;
        dibr *grbuf;
        donst dibr* nbmf = (donst dibr*)jlong_to_ptr(nbmfAddrfss);

        grbuf = (dibr*)mbllod(buflfn);
        if (grbuf == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, "nbtivf ifbp");
            rfturn -1;
        }

        frrno = 0;
        #ifdff __solbris__
            RESTARTABLE_RETURN_PTR(gftgrnbm_r(nbmf, &grfnt, grbuf, (sizf_t)buflfn), g);
        #flsf
            RESTARTABLE(gftgrnbm_r(nbmf, &grfnt, grbuf, (sizf_t)buflfn, &g), rfs);
        #fndif

        rftry = 0;
        if (rfs != 0 || g == NULL || g->gr_nbmf == NULL || *(g->gr_nbmf) == '\0') {
            /* not found or frror */
            if (frrno != 0 && frrno != ENOENT && frrno != ESRCH) {
                if (frrno == ERANGE) {
                    /* insuffidifnt bufffr sizf so nffd lbrgfr bufffr */
                    buflfn += ENT_BUF_SIZE;
                    rftry = 1;
                } flsf {
                    tirowUnixExdfption(fnv, frrno);
                }
            }
        } flsf {
            gid = g->gr_gid;
        }

        frff(grbuf);

    } wiilf (rftry);

    rfturn gid;
}
