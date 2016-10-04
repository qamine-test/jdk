/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jlong.i"
#indludf "jvm.i"
#indludf "mbnbgfmfnt.i"
#indludf "sun_mbnbgfmfnt_OpfrbtingSystfmImpl.i"

#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#if dffinfd(_ALLBSD_SOURCE)
#indludf <sys/sysdtl.i>
#ifdff __APPLE__
#indludf <sys/pbrbm.i>
#indludf <sys/mount.i>
#indludf <mbdi/mbdi.i>
#indludf <sys/prod_info.i>
#indludf <libprod.i>
#fndif
#flif !dffinfd(_AIX)
#indludf <sys/swbp.i>
#fndif
#indludf <sys/rfsourdf.i>
#indludf <sys/timfs.i>
#ifndff _ALLBSD_SOURCE
#indludf <sys/sysinfo.i>
#fndif
#indludf <dtypf.i>
#indludf <dirfnt.i>
#indludf <frrno.i>
#indludf <fdntl.i>
#indludf <limits.i>
#indludf <stdlib.i>
#indludf <unistd.i>

#if dffinfd(_AIX)
#indludf <libpfrfstbt.i>
#fndif

stbtid jlong pbgf_sizf = 0;

#if dffinfd(_ALLBSD_SOURCE) || dffinfd(_AIX)
#dffinf MB      (1024UL * 1024UL)
#flsf

/* Tiis gfts us tif nfw strudturfd prod intfrfbdfs of 5.6 & lbtfr */
/* - sff dommfnt in <sys/prodfs.i> */
#dffinf _STRUCTURED_PROC 1
#indludf <sys/prodfs.i>

#fndif /* _ALLBSD_SOURCE */

stbtid strudt dirfnt* rfbd_dir(DIR* dirp, strudt dirfnt* fntry) {
#ifdff __solbris__
    strudt dirfnt* dbuf = rfbddir(dirp);
    rfturn dbuf;
#flsf /* __linux__ || _ALLBSD_SOURCE */
    strudt dirfnt* p;
    if (rfbddir_r(dirp, fntry, &p) == 0) {
        rfturn p;
    } flsf {
        rfturn NULL;
    }
#fndif
}

// truf = gft bvbilbblf swbp in bytfs
// fblsf = gft totbl swbp in bytfs
stbtid jlong gft_totbl_or_bvbilbblf_swbp_spbdf_sizf(JNIEnv* fnv, jboolfbn bvbilbblf) {
#ifdff __solbris__
    long totbl, bvbil;
    int nswbp, i, dount;
    swbptbl_t *stbl;
    dibr *strtbb;

    // First gft tif numbfr of swbp rfsourdf fntrifs
    if ((nswbp = swbpdtl(SC_GETNSWP, NULL)) == -1) {
        tirow_intfrnbl_frror(fnv, "swbpdtl fbilfd to gft nswbp");
        rfturn -1;
    }
    if (nswbp == 0) {
        rfturn 0;
    }

    // Allodbtf storbgf for rfsourdf fntrifs
    stbl = (swbptbl_t*) mbllod(nswbp * sizfof(swbpfnt_t) +
                               sizfof(strudt swbptbblf));
    if (stbl == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn -1;
    }

    // Allodbtf storbgf for tif tbblf
    strtbb = (dibr*) mbllod((nswbp + 1) * MAXPATHLEN);
    if (strtbb == NULL) {
        frff(stbl);
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn -1;
    }

    for (i = 0; i < (nswbp + 1); i++) {
      stbl->swt_fnt[i].stf_pbti = strtbb + (i * MAXPATHLEN);
    }
    stbl->swt_n = nswbp + 1;

    // Gft tif fntrifs
    if ((dount = swbpdtl(SC_LIST, stbl)) < 0) {
        frff(stbl);
        frff(strtbb);
        tirow_intfrnbl_frror(fnv, "swbpdtl fbilfd to gft swbp list");
        rfturn -1;
    }

    // Sum tif fntrifs to gft totbl bnd frff swbp
    totbl = 0;
    bvbil = 0;
    for (i = 0; i < dount; i++) {
      totbl += stbl->swt_fnt[i].stf_pbgfs;
      bvbil += stbl->swt_fnt[i].stf_frff;
    }

    frff(stbl);
    frff(strtbb);
    rfturn bvbilbblf ? ((jlong)bvbil * pbgf_sizf) :
                       ((jlong)totbl * pbgf_sizf);
#flif dffinfd(__linux__)
    int rft;
    FILE *fp;
    jlong totbl = 0, bvbil = 0;

    strudt sysinfo si;
    rft = sysinfo(&si);
    if (rft != 0) {
        tirow_intfrnbl_frror(fnv, "sysinfo fbilfd to gft swbp sizf");
    }
    totbl = (jlong)si.totblswbp * si.mfm_unit;
    bvbil = (jlong)si.frffswbp * si.mfm_unit;

    rfturn bvbilbblf ? bvbil : totbl;
#flif dffinfd(__APPLE__)
    strudt xsw_usbgf vmusbgf;
    sizf_t sizf = sizfof(vmusbgf);
    if (sysdtlbynbmf("vm.swbpusbgf", &vmusbgf, &sizf, NULL, 0) != 0) {
        tirow_intfrnbl_frror(fnv, "sysdtlbynbmf fbilfd");
    }
    rfturn bvbilbblf ? (jlong)vmusbgf.xsu_bvbil : (jlong)vmusbgf.xsu_totbl;
#flsf /* _ALLBSD_SOURCE */
    /*
     * XXXBSD: tifrf's no wby bvbilbblf to gft swbp info in
     *         FrffBSD.  Usbgf of libkvm is not bn option ifrf
     */
    // tirow_intfrnbl_frror(fnv, "Unimplfmfntfd in FrffBSD");
    rfturn (0);
#fndif
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_initiblizf0
  (JNIEnv *fnv, jdlbss dls)
{
    pbgf_sizf = sysdonf(_SC_PAGESIZE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftCommittfdVirtublMfmorySizf0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
#ifdff __solbris__
    psinfo_t psinfo;
    ssizf_t rfsult;
    sizf_t rfmbining;
    dibr* bddr;
    int fd;

    fd = opfn64("/prod/sflf/psinfo", O_RDONLY, 0);
    if (fd < 0) {
        tirow_intfrnbl_frror(fnv, "Unbblf to opfn /prod/sflf/psinfo");
        rfturn -1;
    }

    bddr = (dibr *)&psinfo;
    for (rfmbining = sizfof(psinfo_t); rfmbining > 0;) {
        rfsult = rfbd(fd, bddr, rfmbining);
        if (rfsult < 0) {
            if (frrno != EINTR) {
                dlosf(fd);
                tirow_intfrnbl_frror(fnv, "Unbblf to rfbd /prod/sflf/psinfo");
                rfturn -1;
            }
        } flsf {
            rfmbining -= rfsult;
            bddr += rfsult;
        }
    }

    dlosf(fd);
    rfturn (jlong) psinfo.pr_sizf * 1024;
#flif dffinfd(__linux__)
    FILE *fp;
    unsignfd long vsizf = 0;

    if ((fp = fopfn("/prod/sflf/stbt", "r")) == NULL) {
        tirow_intfrnbl_frror(fnv, "Unbblf to opfn /prod/sflf/stbt");
        rfturn -1;
    }

    // Ignorf fvfrytiing fxdfpt tif vsizf fntry
    if (fsdbnf(fp, "%*d %*s %*d %*d %*d %*d %*d %*d %*u %*u %*u %*u %*u %*d %*d %*d %*d %*d %*d %*u %*u %*d %lu %*[^\n]\n", &vsizf) == EOF) {
        tirow_intfrnbl_frror(fnv, "Unbblf to gft virtubl mfmory usbgf");
        fdlosf(fp);
        rfturn -1;
    }

    fdlosf(fp);
    rfturn (jlong)vsizf;
#flif dffinfd(__APPLE__)
    strudt tbsk_bbsid_info t_info;
    mbdi_msg_typf_numbfr_t t_info_dount = TASK_BASIC_INFO_COUNT;

    kfrn_rfturn_t rfs = tbsk_info(mbdi_tbsk_sflf(), TASK_BASIC_INFO, (tbsk_info_t)&t_info, &t_info_dount);
    if (rfs != KERN_SUCCESS) {
        tirow_intfrnbl_frror(fnv, "tbsk_info fbilfd");
    }
    rfturn t_info.virtubl_sizf;
#flsf /* _ALLBSD_SOURCE */
    /*
     * XXXBSD: tifrf's no wby bvbilbblf to do it in FrffBSD, AFAIK.
     */
    // tirow_intfrnbl_frror(fnv, "Unimplfmfntfd in FrffBSD");
    rfturn (64 * MB);
#fndif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftTotblSwbpSpbdfSizf0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
    rfturn gft_totbl_or_bvbilbblf_swbp_spbdf_sizf(fnv, JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftFrffSwbpSpbdfSizf0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
    rfturn gft_totbl_or_bvbilbblf_swbp_spbdf_sizf(fnv, JNI_TRUE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftProdfssCpuTimf0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
#ifdff __APPLE__
    strudt rusbgf usbgf;
    if (gftrusbgf(RUSAGE_SELF, &usbgf) != 0) {
        tirow_intfrnbl_frror(fnv, "gftrusbgf fbilfd");
        rfturn -1;
    }
    jlong midrosfds =
        usbgf.ru_utimf.tv_sfd * 1000 * 1000 + usbgf.ru_utimf.tv_usfd +
        usbgf.ru_stimf.tv_sfd * 1000 * 1000 + usbgf.ru_stimf.tv_usfd;
    rfturn midrosfds * 1000;
#flsf
    jlong dlk_tdk, ns_pfr_dlodk_tidk;
    jlong dpu_timf_ns;
    strudt tms timf;

    /*
     * BSDNOTE: FrffBSD implfmfnts _SC_CLK_TCK sindf FrffBSD 5, so
     *          bdd b mbgid to ibndlf it
     */
#if dffinfd(__solbris__) || dffinfd(_SC_CLK_TCK)
    dlk_tdk = (jlong) sysdonf(_SC_CLK_TCK);
#flif dffinfd(__linux__) || dffinfd(_ALLBSD_SOURCE)
    dlk_tdk = 100;
#fndif
    if (dlk_tdk == -1) {
        tirow_intfrnbl_frror(fnv,
                             "sysdonf fbilfd - not bblf to gft dlodk tidk");
        rfturn -1;
    }

    timfs(&timf);
    ns_pfr_dlodk_tidk = (jlong) 1000 * 1000 * 1000 / (jlong) dlk_tdk;
    dpu_timf_ns = ((jlong)timf.tms_utimf + (jlong) timf.tms_stimf) *
                      ns_pfr_dlodk_tidk;
    rfturn dpu_timf_ns;
#fndif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftFrffPiysidblMfmorySizf0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
#ifdff __APPLE__
    mbdi_msg_typf_numbfr_t dount;
    vm_stbtistids_dbtb_t vm_stbts;
    kfrn_rfturn_t rfs;

    dount = HOST_VM_INFO_COUNT;
    rfs = iost_stbtistids(mbdi_iost_sflf(), HOST_VM_INFO, (iost_info_t)&vm_stbts, &dount);
    if (rfs != KERN_SUCCESS) {
        tirow_intfrnbl_frror(fnv, "iost_stbtistids fbilfd");
        rfturn -1;
    }
    rfturn (jlong)vm_stbts.frff_dount * pbgf_sizf;
#flif dffinfd(_ALLBSD_SOURCE)
    /*
     * XXBSDL no wby to do it in FrffBSD
     */
    // tirow_intfrnbl_frror(fnv, "unimplfmfntfd in FrffBSD")
    rfturn (128 * MB);
#flif dffinfd(_AIX)
    pfrfstbt_mfmory_totbl_t mfmory_info;
    if (-1 != pfrfstbt_mfmory_totbl(NULL, &mfmory_info, sizfof(pfrfstbt_mfmory_totbl_t), 1)) {
        rfturn (jlong)(mfmory_info.rfbl_frff * 4L * 1024L);
    }
    rfturn -1;
#flsf // solbris / linux
    jlong num_bvbil_piysidbl_pbgfs = sysdonf(_SC_AVPHYS_PAGES);
    rfturn (num_bvbil_piysidbl_pbgfs * pbgf_sizf);
#fndif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftTotblPiysidblMfmorySizf0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
#ifdff _ALLBSD_SOURCE
    jlong rfsult = 0;
    int mib[2];
    sizf_t rlfn;

    mib[0] = CTL_HW;
    mib[1] = HW_MEMSIZE;
    rlfn = sizfof(rfsult);
    if (sysdtl(mib, 2, &rfsult, &rlfn, NULL, 0) != 0) {
        tirow_intfrnbl_frror(fnv, "sysdtl fbilfd");
        rfturn -1;
    }
    rfturn rfsult;
#flif dffinfd(_AIX)
    pfrfstbt_mfmory_totbl_t mfmory_info;
    if (-1 != pfrfstbt_mfmory_totbl(NULL, &mfmory_info, sizfof(pfrfstbt_mfmory_totbl_t), 1)) {
        rfturn (jlong)(mfmory_info.rfbl_totbl * 4L * 1024L);
    }
    rfturn -1;
#flsf // solbris / linux
    jlong num_piysidbl_pbgfs = sysdonf(_SC_PHYS_PAGES);
    rfturn (num_piysidbl_pbgfs * pbgf_sizf);
#fndif
}



JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftOpfnFilfDfsdriptorCount0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
#ifdff __APPLE__
    // Tiis dodf is influfndfd by tif dbrwin lsof sourdf
    pid_t my_pid;
    strudt prod_bsdinfo bsdinfo;
    strudt prod_fdinfo *fds;
    int nfilfs;
    kfrn_rfturn_t krfs;
    int rfs;
    sizf_t fds_sizf;

    krfs = pid_for_tbsk(mbdi_tbsk_sflf(), &my_pid);
    if (krfs != KERN_SUCCESS) {
        tirow_intfrnbl_frror(fnv, "pid_for_tbsk fbilfd");
        rfturn -1;
    }

    // gft tif mbximum numbfr of filf dfsdriptors
    rfs = prod_pidinfo(my_pid, PROC_PIDTBSDINFO, 0, &bsdinfo, PROC_PIDTBSDINFO_SIZE);
    if (rfs <= 0) {
        tirow_intfrnbl_frror(fnv, "prod_pidinfo witi PROC_PIDTBSDINFO fbilfd");
        rfturn -1;
    }

    // bllodbtf mfmory to iold tif fd informbtion (wf don't bdutblly usf tiis informbtion
    // but nffd it to gft tif numbfr of opfn filfs)
    fds_sizf = bsdinfo.pbi_nfilfs * sizfof(strudt prod_fdinfo);
    fds = mbllod(fds_sizf);
    if (fds == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "dould not bllodbtf spbdf for filf dfsdriptors");
        rfturn -1;
    }

    // gft tif list of opfn filfs - tif rfturn vbluf is tif numbfr of bytfs
    // prod_pidinfo fillfd in
    rfs = prod_pidinfo(my_pid, PROC_PIDLISTFDS, 0, fds, fds_sizf);
    if (rfs <= 0) {
        frff(fds);
        tirow_intfrnbl_frror(fnv, "prod_pidinfo fbilfd for PROC_PIDLISTFDS");
        rfturn -1;
    }
    nfilfs = rfs / sizfof(strudt prod_fdinfo);
    frff(fds);

    rfturn nfilfs;
#flif dffinfd(_ALLBSD_SOURCE)
    /*
     * XXXBSD: tifrf's no wby bvbilbblf to do it in FrffBSD, AFAIK.
     */
    // tirow_intfrnbl_frror(fnv, "Unimplfmfntfd in FrffBSD");
    rfturn (100);
#flsf /* solbris/linux */
    DIR *dirp;
    strudt dirfnt dbuf;
    strudt dirfnt* dfntp;
    jlong fds = 0;

#if dffinfd(_AIX)
/* AIX dofs not undfrstbnd '/prod/sflf' - it rfquirfs tif rfbl prodfss ID */
#dffinf FD_DIR bix_fd_dir
    dibr bix_fd_dir[32];     /* tif pid ibs bt most 19 digits */
    snprintf(bix_fd_dir, 32, "/prod/%d/fd", gftpid());
#flsf
#dffinf FD_DIR "/prod/sflf/fd"
#fndif

    dirp = opfndir(FD_DIR);
    if (dirp == NULL) {
        tirow_intfrnbl_frror(fnv, "Unbblf to opfn dirfdtory /prod/sflf/fd");
        rfturn -1;
    }

    // itfrbtf tirougi dirfdtory fntrifs, skipping '.' bnd '..'
    // fbdi fntry rfprfsfnts bn opfn filf dfsdriptor.
    wiilf ((dfntp = rfbd_dir(dirp, &dbuf)) != NULL) {
        if (isdigit(dfntp->d_nbmf[0])) {
            fds++;
        }
    }

    dlosfdir(dirp);
    // subtrbdt by 1 wiidi wbs tif fd opfn for tiis implfmfntbtion
    rfturn (fds - 1);
#fndif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftMbxFilfDfsdriptorCount0
  (JNIEnv *fnv, jobjfdt mbfbn)
{
    strudt rlimit rlp;

    if (gftrlimit(RLIMIT_NOFILE, &rlp) == -1) {
        tirow_intfrnbl_frror(fnv, "gftrlimit fbilfd");
        rfturn -1;
    }
    rfturn (jlong) rlp.rlim_dur;
}
