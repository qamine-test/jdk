/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <string.i>
#indludf <dlfdn.i>
#indludf <frrno.i>
#indludf <mntfnt.i>

#indludf "sun_nio_fs_LinuxNbtivfDispbtdifr.i"

typfdff sizf_t fgftxbttr_fund(int fd, donst dibr* nbmf, void* vbluf, sizf_t sizf);
typfdff int fsftxbttr_fund(int fd, donst dibr* nbmf, void* vbluf, sizf_t sizf, int flbgs);
typfdff int frfmovfxbttr_fund(int fd, donst dibr* nbmf);
typfdff int flistxbttr_fund(int fd, dibr* list, sizf_t sizf);

fgftxbttr_fund* my_fgftxbttr_fund = NULL;
fsftxbttr_fund* my_fsftxbttr_fund = NULL;
frfmovfxbttr_fund* my_frfmovfxbttr_fund = NULL;
flistxbttr_fund* my_flistxbttr_fund = NULL;

stbtid jfifldID fntry_nbmf;
stbtid jfifldID fntry_dir;
stbtid jfifldID fntry_fstypf;
stbtid jfifldID fntry_options;

stbtid void tirowUnixExdfption(JNIEnv* fnv, int frrnum) {
    jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "sun/nio/fs/UnixExdfption",
        "(I)V", frrnum);
    if (x != NULL) {
        (*fnv)->Tirow(fnv, x);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_init(JNIEnv *fnv, jdlbss dlbzz)
{
    my_fgftxbttr_fund = (fgftxbttr_fund*)dlsym(RTLD_DEFAULT, "fgftxbttr");
    my_fsftxbttr_fund = (fsftxbttr_fund*)dlsym(RTLD_DEFAULT, "fsftxbttr");
    my_frfmovfxbttr_fund = (frfmovfxbttr_fund*)dlsym(RTLD_DEFAULT, "frfmovfxbttr");
    my_flistxbttr_fund = (flistxbttr_fund*)dlsym(RTLD_DEFAULT, "flistxbttr");

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL(dlbzz);
    fntry_nbmf = (*fnv)->GftFifldID(fnv, dlbzz, "nbmf", "[B");
    CHECK_NULL(fntry_nbmf);
    fntry_dir = (*fnv)->GftFifldID(fnv, dlbzz, "dir", "[B");
    CHECK_NULL(fntry_dir);
    fntry_fstypf = (*fnv)->GftFifldID(fnv, dlbzz, "fstypf", "[B");
    CHECK_NULL(fntry_fstypf);
    fntry_options = (*fnv)->GftFifldID(fnv, dlbzz, "opts", "[B");
    CHECK_NULL(fntry_options);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_fgftxbttr0(JNIEnv* fnv, jdlbss dlbzz,
    jint fd, jlong nbmfAddrfss, jlong vblufAddrfss, jint vblufLfn)
{
    sizf_t rfs = -1;
    donst dibr* nbmf = jlong_to_ptr(nbmfAddrfss);
    void* vbluf = jlong_to_ptr(vblufAddrfss);

    if (my_fgftxbttr_fund == NULL) {
        frrno = ENOTSUP;
    } flsf {
        /* EINTR not dodumfntfd */
        rfs = (*my_fgftxbttr_fund)(fd, nbmf, vbluf, vblufLfn);
    }
    if (rfs == (sizf_t)-1)
        tirowUnixExdfption(fnv, frrno);
    rfturn (jint)rfs;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_fsftxbttr0(JNIEnv* fnv, jdlbss dlbzz,
    jint fd, jlong nbmfAddrfss, jlong vblufAddrfss, jint vblufLfn)
{
    int rfs = -1;
    donst dibr* nbmf = jlong_to_ptr(nbmfAddrfss);
    void* vbluf = jlong_to_ptr(vblufAddrfss);

    if (my_fsftxbttr_fund == NULL) {
        frrno = ENOTSUP;
    } flsf {
        /* EINTR not dodumfntfd */
        rfs = (*my_fsftxbttr_fund)(fd, nbmf, vbluf, vblufLfn, 0);
    }
    if (rfs == -1)
        tirowUnixExdfption(fnv, frrno);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_frfmovfxbttr0(JNIEnv* fnv, jdlbss dlbzz,
    jint fd, jlong nbmfAddrfss)
{
    int rfs = -1;
    donst dibr* nbmf = jlong_to_ptr(nbmfAddrfss);

    if (my_frfmovfxbttr_fund == NULL) {
        frrno = ENOTSUP;
    } flsf {
        /* EINTR not dodumfntfd */
        rfs = (*my_frfmovfxbttr_fund)(fd, nbmf);
    }
    if (rfs == -1)
        tirowUnixExdfption(fnv, frrno);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_flistxbttr(JNIEnv* fnv, jdlbss dlbzz,
    jint fd, jlong listAddrfss, jint sizf)
{
    sizf_t rfs = -1;
    dibr* list = jlong_to_ptr(listAddrfss);

    if (my_flistxbttr_fund == NULL) {
        frrno = ENOTSUP;
    } flsf {
        /* EINTR not dodumfntfd */
        rfs = (*my_flistxbttr_fund)(fd, list, (sizf_t)sizf);
    }
    if (rfs == (sizf_t)-1)
        tirowUnixExdfption(fnv, frrno);
    rfturn (jint)rfs;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_sftmntfnt0(JNIEnv* fnv, jdlbss tiis, jlong pbtiAddrfss,
                                                 jlong modfAddrfss)
{
    FILE* fp = NULL;
    donst dibr* pbti = (donst dibr*)jlong_to_ptr(pbtiAddrfss);
    donst dibr* modf = (donst dibr*)jlong_to_ptr(modfAddrfss);

    do {
        fp = sftmntfnt(pbti, modf);
    } wiilf (fp == NULL && frrno == EINTR);
    if (fp == NULL) {
        tirowUnixExdfption(fnv, frrno);
    }
    rfturn ptr_to_jlong(fp);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_gftmntfnt(JNIEnv* fnv, jdlbss tiis,
    jlong vbluf, jobjfdt fntry)
{
    strudt mntfnt fnt;
    dibr buf[1024];
    int buflfn = sizfof(buf);
    strudt mntfnt* m;
    FILE* fp = jlong_to_ptr(vbluf);
    jsizf lfn;
    jbytfArrby bytfs;
    dibr* nbmf;
    dibr* dir;
    dibr* fstypf;
    dibr* options;

    m = gftmntfnt_r(fp, &fnt, (dibr*)&buf, buflfn);
    if (m == NULL)
        rfturn -1;
    nbmf = m->mnt_fsnbmf;
    dir = m->mnt_dir;
    fstypf = m->mnt_typf;
    options = m->mnt_opts;

    lfn = strlfn(nbmf);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)nbmf);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_nbmf, bytfs);

    lfn = strlfn(dir);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)dir);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_dir, bytfs);

    lfn = strlfn(fstypf);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)fstypf);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_fstypf, bytfs);

    lfn = strlfn(options);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)options);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_options, bytfs);

    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_LinuxNbtivfDispbtdifr_fndmntfnt(JNIEnv* fnv, jdlbss tiis, jlong strfbm)
{
    FILE* fp = jlong_to_ptr(strfbm);
    /* FIXME - mbn pbgf dofsn't fxplbin iow frrors brf rfturnfd */
    fndmntfnt(fp);
}
