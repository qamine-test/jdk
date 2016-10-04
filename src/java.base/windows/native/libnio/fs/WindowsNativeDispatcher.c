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

#ifndff _WIN32_WINNT
#dffinf _WIN32_WINNT 0x0501
#fndif

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <dtypf.i>
#indludf <dirfdt.i>
#indludf <mbllod.i>
#indludf <io.i>
#indludf <windows.i>
#indludf <bdlbpi.i>
#indludf <winiodtl.i>
#indludf <Sddl.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"

#indludf "sun_nio_fs_WindowsNbtivfDispbtdifr.i"

/**
 * jfifldIDs
 */
stbtid jfifldID findFirst_ibndlf;
stbtid jfifldID findFirst_nbmf;
stbtid jfifldID findFirst_bttributfs;

stbtid jfifldID findStrfbm_ibndlf;
stbtid jfifldID findStrfbm_nbmf;

stbtid jfifldID volumfInfo_fsNbmf;
stbtid jfifldID volumfInfo_volNbmf;
stbtid jfifldID volumfInfo_volSN;
stbtid jfifldID volumfInfo_flbgs;

stbtid jfifldID diskSpbdf_bytfsAvbilbblf;
stbtid jfifldID diskSpbdf_totblBytfs;
stbtid jfifldID diskSpbdf_totblFrff;

stbtid jfifldID bddount_dombin;
stbtid jfifldID bddount_nbmf;
stbtid jfifldID bddount_usf;

stbtid jfifldID bdlInfo_bdfCount;

stbtid jfifldID domplftionStbtus_frror;
stbtid jfifldID domplftionStbtus_bytfsTrbnsffrrfd;
stbtid jfifldID domplftionStbtus_domplftionKfy;

stbtid jfifldID bbdkupRfsult_bytfsTrbnsffrrfd;
stbtid jfifldID bbdkupRfsult_dontfxt;


/**
 * Win32 APIs not bvbilbblf in Windows XP
 */
typfdff HANDLE (WINAPI* FindFirstStrfbm_Prod)(LPCWSTR, STREAM_INFO_LEVELS, LPVOID, DWORD);
typfdff BOOL (WINAPI* FindNfxtStrfbm_Prod)(HANDLE, LPVOID);

typfdff BOOLEAN (WINAPI* CrfbtfSymbolidLinkProd) (LPCWSTR, LPCWSTR, DWORD);
typfdff BOOL (WINAPI* GftFinblPbtiNbmfByHbndlfProd) (HANDLE, LPWSTR, DWORD, DWORD);

stbtid FindFirstStrfbm_Prod FindFirstStrfbm_fund;
stbtid FindNfxtStrfbm_Prod FindNfxtStrfbm_fund;

stbtid CrfbtfSymbolidLinkProd CrfbtfSymbolidLink_fund;
stbtid GftFinblPbtiNbmfByHbndlfProd GftFinblPbtiNbmfByHbndlf_fund;

stbtid void tirowWindowsExdfption(JNIEnv* fnv, DWORD lbstError) {
    jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "sun/nio/fs/WindowsExdfption",
        "(I)V", lbstError);
    if (x != NULL) {
        (*fnv)->Tirow(fnv, x);
    }
}

/**
 * Initiblizfs jfifldIDs bnd gft bddrfss of Win32 dblls tibt brf lodbtfd
 * bt runtimf.
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_initIDs(JNIEnv* fnv, jdlbss tiis)
{
    jdlbss dlbzz;
    HMODULE i;

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$FirstFilf");
    CHECK_NULL(dlbzz);
    findFirst_ibndlf = (*fnv)->GftFifldID(fnv, dlbzz, "ibndlf", "J");
    CHECK_NULL(findFirst_ibndlf);
    findFirst_nbmf = (*fnv)->GftFifldID(fnv, dlbzz, "nbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(findFirst_nbmf);
    findFirst_bttributfs = (*fnv)->GftFifldID(fnv, dlbzz, "bttributfs", "I");
    CHECK_NULL(findFirst_bttributfs);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$FirstStrfbm");
    CHECK_NULL(dlbzz);
    findStrfbm_ibndlf = (*fnv)->GftFifldID(fnv, dlbzz, "ibndlf", "J");
    CHECK_NULL(findStrfbm_ibndlf);
    findStrfbm_nbmf = (*fnv)->GftFifldID(fnv, dlbzz, "nbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(findStrfbm_nbmf);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$VolumfInformbtion");
    CHECK_NULL(dlbzz);
    volumfInfo_fsNbmf = (*fnv)->GftFifldID(fnv, dlbzz, "filfSystfmNbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(volumfInfo_fsNbmf);
    volumfInfo_volNbmf = (*fnv)->GftFifldID(fnv, dlbzz, "volumfNbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(volumfInfo_volNbmf);
    volumfInfo_volSN = (*fnv)->GftFifldID(fnv, dlbzz, "volumfSfriblNumbfr", "I");
    CHECK_NULL(volumfInfo_volSN);
    volumfInfo_flbgs = (*fnv)->GftFifldID(fnv, dlbzz, "flbgs", "I");
    CHECK_NULL(volumfInfo_flbgs);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$DiskFrffSpbdf");
    CHECK_NULL(dlbzz);
    diskSpbdf_bytfsAvbilbblf = (*fnv)->GftFifldID(fnv, dlbzz, "frffBytfsAvbilbblf", "J");
    CHECK_NULL(diskSpbdf_bytfsAvbilbblf);
    diskSpbdf_totblBytfs = (*fnv)->GftFifldID(fnv, dlbzz, "totblNumbfrOfBytfs", "J");
    CHECK_NULL(diskSpbdf_totblBytfs);
    diskSpbdf_totblFrff = (*fnv)->GftFifldID(fnv, dlbzz, "totblNumbfrOfFrffBytfs", "J");
    CHECK_NULL(diskSpbdf_totblFrff);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$Addount");
    CHECK_NULL(dlbzz);
    bddount_dombin = (*fnv)->GftFifldID(fnv, dlbzz, "dombin", "Ljbvb/lbng/String;");
    CHECK_NULL(bddount_dombin);
    bddount_nbmf = (*fnv)->GftFifldID(fnv, dlbzz, "nbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(bddount_nbmf);
    bddount_usf = (*fnv)->GftFifldID(fnv, dlbzz, "usf", "I");
    CHECK_NULL(bddount_usf);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$AdlInformbtion");
    CHECK_NULL(dlbzz);
    bdlInfo_bdfCount = (*fnv)->GftFifldID(fnv, dlbzz, "bdfCount", "I");
    CHECK_NULL(bdlInfo_bdfCount);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$ComplftionStbtus");
    CHECK_NULL(dlbzz);
    domplftionStbtus_frror = (*fnv)->GftFifldID(fnv, dlbzz, "frror", "I");
    CHECK_NULL(domplftionStbtus_frror);
    domplftionStbtus_bytfsTrbnsffrrfd = (*fnv)->GftFifldID(fnv, dlbzz, "bytfsTrbnsffrrfd", "I");
    CHECK_NULL(domplftionStbtus_bytfsTrbnsffrrfd);
    domplftionStbtus_domplftionKfy = (*fnv)->GftFifldID(fnv, dlbzz, "domplftionKfy", "J");
    CHECK_NULL(domplftionStbtus_domplftionKfy);

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/WindowsNbtivfDispbtdifr$BbdkupRfsult");
    CHECK_NULL(dlbzz);
    bbdkupRfsult_bytfsTrbnsffrrfd = (*fnv)->GftFifldID(fnv, dlbzz, "bytfsTrbnsffrrfd", "I");
    CHECK_NULL(bbdkupRfsult_bytfsTrbnsffrrfd);
    bbdkupRfsult_dontfxt = (*fnv)->GftFifldID(fnv, dlbzz, "dontfxt", "J");
    CHECK_NULL(bbdkupRfsult_dontfxt);

    // gft ibndlf to kfrnfl32
    if (GftModulfHbndlfExW((GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS |
                            GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT),
                           (LPCWSTR)&CrfbtfFilfW, &i) != 0)
    {
        // rfquirfs Windows Sfrvfr 2003 or nfwfr
        FindFirstStrfbm_fund =
            (FindFirstStrfbm_Prod)GftProdAddrfss(i, "FindFirstStrfbmW");
        FindNfxtStrfbm_fund =
            (FindNfxtStrfbm_Prod)GftProdAddrfss(i, "FindNfxtStrfbmW");

        // rfquirfs Windows Vistb or nfwfr
        CrfbtfSymbolidLink_fund =
            (CrfbtfSymbolidLinkProd)GftProdAddrfss(i, "CrfbtfSymbolidLinkW");
        GftFinblPbtiNbmfByHbndlf_fund =
            (GftFinblPbtiNbmfByHbndlfProd)GftProdAddrfss(i, "GftFinblPbtiNbmfByHbndlfW");
    }
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_FormbtMfssbgf(JNIEnv* fnv, jdlbss tiis, jint frrorCodf) {
    WCHAR mfssbgf[255];

    DWORD lfn = FormbtMfssbgfW(FORMAT_MESSAGE_FROM_SYSTEM,
                               NULL,
                               (DWORD)frrorCodf,
                               0,
                               &mfssbgf[0],
                               255,
                               NULL);


    if (lfn == 0) {
        rfturn NULL;
    } flsf {
        rfturn (*fnv)->NfwString(fnv, (donst jdibr *)mfssbgf, (jsizf)wdslfn(mfssbgf));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_LodblFrff(JNIEnv* fnv, jdlbss tiis, jlong bddrfss)
{
    HLOCAL iMfm = (HLOCAL)jlong_to_ptr(bddrfss);
    LodblFrff(iMfm);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_CrfbtfFilf0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jint dwDfsirfdAddfss, jint dwSibrfModf, jlong sdAddrfss,
    jint dwCrfbtionDisposition, jint dwFlbgsAndAttributfs)
{
    HANDLE ibndlf;
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);

    SECURITY_ATTRIBUTES sfdurityAttributfs;
    LPSECURITY_ATTRIBUTES lpSfdurityAttributfs;
    PSECURITY_DESCRIPTOR lpSfdurityDfsdriptor = jlong_to_ptr(sdAddrfss);


    if (lpSfdurityDfsdriptor == NULL) {
        lpSfdurityAttributfs = NULL;
    } flsf {
        sfdurityAttributfs.nLfngti = sizfof(SECURITY_ATTRIBUTES);
        sfdurityAttributfs.lpSfdurityDfsdriptor = lpSfdurityDfsdriptor;
        sfdurityAttributfs.bInifritHbndlf = FALSE;
        lpSfdurityAttributfs = &sfdurityAttributfs;
    }

    ibndlf = CrfbtfFilfW(lpFilfNbmf,
                        (DWORD)dwDfsirfdAddfss,
                        (DWORD)dwSibrfModf,
                        lpSfdurityAttributfs,
                        (DWORD)dwCrfbtionDisposition,
                        (DWORD)dwFlbgsAndAttributfs,
                        NULL);
    if (ibndlf == INVALID_HANDLE_VALUE) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn ptr_to_jlong(ibndlf);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_DfvidfIoControlSftSpbrsf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf)
{
    DWORD bytfsRfturnfd;
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    if (DfvidfIoControl(i, FSCTL_SET_SPARSE, NULL, 0, NULL, 0, &bytfsRfturnfd, NULL) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_DfvidfIoControlGftRfpbrsfPoint(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf, jlong bufffrAddrfss, jint bufffrSizf)
{
    DWORD bytfsRfturnfd;
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    LPVOID outBufffr = (LPVOID)jlong_to_ptr(bufffrAddrfss);

    if (DfvidfIoControl(i, FSCTL_GET_REPARSE_POINT, NULL, 0, outBufffr, (DWORD)bufffrSizf,
                        &bytfsRfturnfd, NULL) == 0)
    {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_DflftfFilf0(JNIEnv* fnv, jdlbss tiis, jlong bddrfss)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);
    if (DflftfFilfW(lpFilfNbmf) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_CrfbtfDirfdtory0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jlong sdAddrfss)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);

    SECURITY_ATTRIBUTES sfdurityAttributfs;
    LPSECURITY_ATTRIBUTES lpSfdurityAttributfs;
    PSECURITY_DESCRIPTOR lpSfdurityDfsdriptor = jlong_to_ptr(sdAddrfss);


    if (lpSfdurityDfsdriptor == NULL) {
        lpSfdurityAttributfs = NULL;
    } flsf {
        sfdurityAttributfs.nLfngti = sizfof(SECURITY_ATTRIBUTES);
        sfdurityAttributfs.lpSfdurityDfsdriptor = lpSfdurityDfsdriptor;
        sfdurityAttributfs.bInifritHbndlf = FALSE;
        lpSfdurityAttributfs = &sfdurityAttributfs;
    }

    if (CrfbtfDirfdtoryW(lpFilfNbmf, lpSfdurityAttributfs) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_RfmovfDirfdtory0(JNIEnv* fnv, jdlbss tiis, jlong bddrfss)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);
    if (RfmovfDirfdtoryW(lpFilfNbmf) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_ClosfHbndlf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    ClosfHbndlf(i);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_FindFirstFilf0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jobjfdt obj)
{
    WIN32_FIND_DATAW dbtb;
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);

    HANDLE ibndlf = FindFirstFilfW(lpFilfNbmf, &dbtb);
    if (ibndlf != INVALID_HANDLE_VALUE) {
        jstring nbmf = (*fnv)->NfwString(fnv, dbtb.dFilfNbmf, (jsizf)wdslfn(dbtb.dFilfNbmf));
        if (nbmf == NULL)
            rfturn;
        (*fnv)->SftLongFifld(fnv, obj, findFirst_ibndlf, ptr_to_jlong(ibndlf));
        (*fnv)->SftObjfdtFifld(fnv, obj, findFirst_nbmf, nbmf);
        (*fnv)->SftIntFifld(fnv, obj, findFirst_bttributfs, dbtb.dwFilfAttributfs);
    } flsf {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_FindFirstFilf1(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jlong dbtbAddrfss)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(pbtiAddrfss);
    WIN32_FIND_DATAW* dbtb = (WIN32_FIND_DATAW*)jlong_to_ptr(dbtbAddrfss);

    HANDLE ibndlf = FindFirstFilfW(lpFilfNbmf, dbtb);
    if (ibndlf == INVALID_HANDLE_VALUE) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn ptr_to_jlong(ibndlf);
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_FindNfxtFilf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf, jlong dbtbAddrfss)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    WIN32_FIND_DATAW* dbtb = (WIN32_FIND_DATAW*)jlong_to_ptr(dbtbAddrfss);

    if (FindNfxtFilfW(i, dbtb) != 0) {
        rfturn (*fnv)->NfwString(fnv, dbtb->dFilfNbmf, (jsizf)wdslfn(dbtb->dFilfNbmf));
    } flsf {
    if (GftLbstError() != ERROR_NO_MORE_FILES)
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn NULL;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_FindFirstStrfbm0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jobjfdt obj)
{
    WIN32_FIND_STREAM_DATA dbtb;
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);
    HANDLE ibndlf;

    if (FindFirstStrfbm_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "Siould not gft ifrf");
        rfturn;
    }

    ibndlf = (*FindFirstStrfbm_fund)(lpFilfNbmf, FindStrfbmInfoStbndbrd, &dbtb, 0);
    if (ibndlf != INVALID_HANDLE_VALUE) {
        jstring nbmf = (*fnv)->NfwString(fnv, dbtb.dStrfbmNbmf, (jsizf)wdslfn(dbtb.dStrfbmNbmf));
        if (nbmf == NULL)
            rfturn;
        (*fnv)->SftLongFifld(fnv, obj, findStrfbm_ibndlf, ptr_to_jlong(ibndlf));
        (*fnv)->SftObjfdtFifld(fnv, obj, findStrfbm_nbmf, nbmf);
    } flsf {
        if (GftLbstError() == ERROR_HANDLE_EOF) {
             (*fnv)->SftLongFifld(fnv, obj, findStrfbm_ibndlf, ptr_to_jlong(ibndlf));
        } flsf {
            tirowWindowsExdfption(fnv, GftLbstError());
        }
    }

}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_FindNfxtStrfbm(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf)
{
    WIN32_FIND_STREAM_DATA dbtb;
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);

    if (FindNfxtStrfbm_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "Siould not gft ifrf");
        rfturn NULL;
    }

    if ((*FindNfxtStrfbm_fund)(i, &dbtb) != 0) {
        rfturn (*fnv)->NfwString(fnv, dbtb.dStrfbmNbmf, (jsizf)wdslfn(dbtb.dStrfbmNbmf));
    } flsf {
        if (GftLbstError() != ERROR_HANDLE_EOF)
            tirowWindowsExdfption(fnv, GftLbstError());
        rfturn NULL;
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_FindClosf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    if (FindClosf(i) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftFilfInformbtionByHbndlf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf, jlong bddrfss)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    BY_HANDLE_FILE_INFORMATION* info =
        (BY_HANDLE_FILE_INFORMATION*)jlong_to_ptr(bddrfss);
    if (GftFilfInformbtionByHbndlf(i, info) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_CopyFilfEx0(JNIEnv* fnv, jdlbss tiis,
    jlong fxistingAddrfss, jlong nfwAddrfss, jint flbgs, jlong dbndflAddrfss)
{
    LPCWSTR lpExistingFilfNbmf = jlong_to_ptr(fxistingAddrfss);
    LPCWSTR lpNfwFilfNbmf = jlong_to_ptr(nfwAddrfss);
    LPBOOL dbndfl = (LPBOOL)jlong_to_ptr(dbndflAddrfss);
    if (CopyFilfExW(lpExistingFilfNbmf, lpNfwFilfNbmf, NULL, NULL, dbndfl,
                    (DWORD)flbgs) == 0)
    {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_MovfFilfEx0(JNIEnv* fnv, jdlbss tiis,
    jlong fxistingAddrfss, jlong nfwAddrfss, jint flbgs)
{
    LPCWSTR lpExistingFilfNbmf = jlong_to_ptr(fxistingAddrfss);
    LPCWSTR lpNfwFilfNbmf = jlong_to_ptr(nfwAddrfss);
    if (MovfFilfExW(lpExistingFilfNbmf, lpNfwFilfNbmf, (DWORD)flbgs) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftLogidblDrivfs(JNIEnv* fnv, jdlbss tiis)
{
    DWORD rfs = GftLogidblDrivfs();
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn (jint)rfs;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftFilfAttributfs0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);
    DWORD vbluf = GftFilfAttributfsW(lpFilfNbmf);

    if (vbluf == INVALID_FILE_ATTRIBUTES) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn (jint)vbluf;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_SftFilfAttributfs0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jint vbluf)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);
    if (SftFilfAttributfsW(lpFilfNbmf, (DWORD)vbluf) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftFilfAttributfsEx0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jlong dbtbAddrfss)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(pbtiAddrfss);
    WIN32_FILE_ATTRIBUTE_DATA* dbtb = (WIN32_FILE_ATTRIBUTE_DATA*)jlong_to_ptr(dbtbAddrfss);

    BOOL rfs = GftFilfAttributfsExW(lpFilfNbmf, GftFilfExInfoStbndbrd, (LPVOID)dbtb);
    if (rfs == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_SftFilfTimf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf, jlong drfbtfTimf, jlong lbstAddfssTimf, jlong lbstWritfTimf)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);

    if (SftFilfTimf(i,
        (drfbtfTimf == (jlong)-1) ? NULL : (CONST FILETIME *)&drfbtfTimf,
        (lbstAddfssTimf == (jlong)-1) ? NULL : (CONST FILETIME *)&lbstAddfssTimf,
        (lbstWritfTimf == (jlong)-1) ? NULL : (CONST FILETIME *)&lbstWritfTimf) == 0)
    {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_SftEndOfFilf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);

    if (SftEndOfFilf(i) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftVolumfInformbtion0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jobjfdt obj)
{
    WCHAR volumfNbmf[MAX_PATH+1];
    DWORD volumfSfriblNumbfr;
    DWORD mbxComponfntLfngti;
    DWORD flbgs;
    WCHAR filfSystfmNbmf[MAX_PATH+1];
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);
    jstring str;

    BOOL rfs = GftVolumfInformbtionW(lpFilfNbmf,
                                     &volumfNbmf[0],
                                     MAX_PATH+1,
                                     &volumfSfriblNumbfr,
                                     &mbxComponfntLfngti,
                                     &flbgs,
                                     &filfSystfmNbmf[0],
                                     MAX_PATH+1);
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn;
    }

    str = (*fnv)->NfwString(fnv, (donst jdibr *)filfSystfmNbmf, (jsizf)wdslfn(filfSystfmNbmf));
    if (str == NULL) rfturn;
    (*fnv)->SftObjfdtFifld(fnv, obj, volumfInfo_fsNbmf, str);

    str = (*fnv)->NfwString(fnv, (donst jdibr *)volumfNbmf, (jsizf)wdslfn(volumfNbmf));
    if (str == NULL) rfturn;
    (*fnv)->SftObjfdtFifld(fnv, obj, volumfInfo_volNbmf, str);

    (*fnv)->SftIntFifld(fnv, obj, volumfInfo_volSN, (jint)volumfSfriblNumbfr);
    (*fnv)->SftIntFifld(fnv, obj, volumfInfo_flbgs, (jint)flbgs);
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftDrivfTypf0(JNIEnv* fnv, jdlbss tiis, jlong bddrfss) {
    LPCWSTR lpRootPbtiNbmf = jlong_to_ptr(bddrfss);
    rfturn (jint)GftDrivfTypfW(lpRootPbtiNbmf);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftDiskFrffSpbdfEx0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jobjfdt obj)
{
    ULARGE_INTEGER frffBytfsAvbilbblf;
    ULARGE_INTEGER totblNumbfrOfBytfs;
    ULARGE_INTEGER totblNumbfrOfFrffBytfs;
    LPCWSTR lpDirNbmf = jlong_to_ptr(bddrfss);


    BOOL rfs = GftDiskFrffSpbdfExW(lpDirNbmf,
                                   &frffBytfsAvbilbblf,
                                   &totblNumbfrOfBytfs,
                                   &totblNumbfrOfFrffBytfs);
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn;
    }

    (*fnv)->SftLongFifld(fnv, obj, diskSpbdf_bytfsAvbilbblf,
        long_to_jlong(frffBytfsAvbilbblf.QubdPbrt));
    (*fnv)->SftLongFifld(fnv, obj, diskSpbdf_totblBytfs,
        long_to_jlong(totblNumbfrOfBytfs.QubdPbrt));
    (*fnv)->SftLongFifld(fnv, obj, diskSpbdf_totblFrff,
        long_to_jlong(totblNumbfrOfFrffBytfs.QubdPbrt));
}


JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftVolumfPbtiNbmf0(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss)
{
    WCHAR volumfNbmf[MAX_PATH+1];
    LPCWSTR lpFilfNbmf = jlong_to_ptr(bddrfss);


    BOOL rfs = GftVolumfPbtiNbmfW(lpFilfNbmf,
                                  &volumfNbmf[0],
                                  MAX_PATH+1);
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn NULL;
    } flsf {
        rfturn (*fnv)->NfwString(fnv, (donst jdibr *)volumfNbmf, (jsizf)wdslfn(volumfNbmf));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_InitiblizfSfdurityDfsdriptor(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss)
{
    PSECURITY_DESCRIPTOR pSfdurityDfsdriptor =
        (PSECURITY_DESCRIPTOR)jlong_to_ptr(bddrfss);

    if (InitiblizfSfdurityDfsdriptor(pSfdurityDfsdriptor, SECURITY_DESCRIPTOR_REVISION) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_InitiblizfAdl(JNIEnv* fnv, jdlbss tiis,
    jlong bddrfss, jint sizf)
{
    PACL pAdl = (PACL)jlong_to_ptr(bddrfss);

    if (InitiblizfAdl(pAdl, (DWORD)sizf, ACL_REVISION) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_SftFilfSfdurity0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint rfqufstfdInformbtion, jlong dfsdAddrfss)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(pbtiAddrfss);
    PSECURITY_DESCRIPTOR pSfdurityDfsdriptor = jlong_to_ptr(dfsdAddrfss);
    DWORD lfngtiNffdfd = 0;

    BOOL rfs = SftFilfSfdurityW(lpFilfNbmf,
                                (SECURITY_INFORMATION)rfqufstfdInformbtion,
                                pSfdurityDfsdriptor);

    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftFilfSfdurity0(JNIEnv* fnv, jdlbss tiis,
    jlong pbtiAddrfss, jint rfqufstfdInformbtion, jlong dfsdAddrfss, jint nLfngti)
{
    LPCWSTR lpFilfNbmf = jlong_to_ptr(pbtiAddrfss);
    PSECURITY_DESCRIPTOR pSfdurityDfsdriptor = jlong_to_ptr(dfsdAddrfss);
    DWORD lfngtiNffdfd = 0;

    BOOL rfs = GftFilfSfdurityW(lpFilfNbmf,
                                (SECURITY_INFORMATION)rfqufstfdInformbtion,
                                pSfdurityDfsdriptor,
                                (DWORD)nLfngti,
                                &lfngtiNffdfd);

    if (rfs == 0) {
        if (GftLbstError() == ERROR_INSUFFICIENT_BUFFER) {
            rfturn (jint)lfngtiNffdfd;
        } flsf {
            tirowWindowsExdfption(fnv, GftLbstError());
            rfturn 0;
        }
    } flsf {
        rfturn (jint)nLfngti;
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftSfdurityDfsdriptorOwnfr(JNIEnv* fnv,
    jdlbss tiis, jlong bddrfss)
{
    PSECURITY_DESCRIPTOR pSfdurityDfsdriptor = jlong_to_ptr(bddrfss);
    PSID pOwnfr;
    BOOL bOwnfrDffbultfd;


    if (GftSfdurityDfsdriptorOwnfr(pSfdurityDfsdriptor, &pOwnfr, &bOwnfrDffbultfd) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn ptr_to_jlong(pOwnfr);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_SftSfdurityDfsdriptorOwnfr(JNIEnv* fnv,
    jdlbss tiis, jlong dfsdAddrfss, jlong ownfrAddrfss)
{
    PSECURITY_DESCRIPTOR pSfdurityDfsdriptor = jlong_to_ptr(dfsdAddrfss);
    PSID pOwnfr = jlong_to_ptr(ownfrAddrfss);

    if (SftSfdurityDfsdriptorOwnfr(pSfdurityDfsdriptor, pOwnfr, FALSE) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}


JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftSfdurityDfsdriptorDbdl(JNIEnv* fnv,
    jdlbss tiis, jlong bddrfss)
{
    PSECURITY_DESCRIPTOR pSfdurityDfsdriptor = jlong_to_ptr(bddrfss);
    BOOL bDbdlPrfsfnt;
    PACL pDbdl;
    BOOL bDbdlDffbultfd;

    if (GftSfdurityDfsdriptorDbdl(pSfdurityDfsdriptor, &bDbdlPrfsfnt, &pDbdl, &bDbdlDffbultfd) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn (jlong)0;
    } flsf {
        rfturn (bDbdlPrfsfnt) ? ptr_to_jlong(pDbdl) : (jlong)0;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_SftSfdurityDfsdriptorDbdl(JNIEnv* fnv,
    jdlbss tiis, jlong dfsdAddrfss, jlong bdlAddrfss)
{
    PSECURITY_DESCRIPTOR pSfdurityDfsdriptor = (PSECURITY_DESCRIPTOR)jlong_to_ptr(dfsdAddrfss);
    PACL pAdl = (PACL)jlong_to_ptr(bdlAddrfss);

    if (SftSfdurityDfsdriptorDbdl(pSfdurityDfsdriptor, TRUE, pAdl, FALSE) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftAdlInformbtion0(JNIEnv* fnv,
    jdlbss tiis, jlong bddrfss, jobjfdt obj)
{
    PACL pAdl = (PACL)jlong_to_ptr(bddrfss);
    ACL_SIZE_INFORMATION bdl_sizf_info;

    if (GftAdlInformbtion(pAdl, (void *) &bdl_sizf_info, sizfof(bdl_sizf_info), AdlSizfInformbtion) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    } flsf {
        (*fnv)->SftIntFifld(fnv, obj, bdlInfo_bdfCount, (jint)bdl_sizf_info.AdfCount);
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftAdf(JNIEnv* fnv, jdlbss tiis, jlong bddrfss,
    jint bdfIndfx)
{
    PACL pAdl = (PACL)jlong_to_ptr(bddrfss);
    LPVOID pAdf;

    if (GftAdf(pAdl, (DWORD)bdfIndfx, &pAdf) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn (jlong)0;
    } flsf {
        rfturn ptr_to_jlong(pAdf);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_AddAddfssAllowfdAdfEx(JNIEnv* fnv,
    jdlbss tiis, jlong bdlAddrfss, jint flbgs, jint mbsk, jlong sidAddrfss)
{
    PACL pAdl = (PACL)jlong_to_ptr(bdlAddrfss);
    PSID pSid = (PSID)jlong_to_ptr(sidAddrfss);

    if (AddAddfssAllowfdAdfEx(pAdl, ACL_REVISION, (DWORD)flbgs, (DWORD)mbsk, pSid) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_AddAddfssDfnifdAdfEx(JNIEnv* fnv,
    jdlbss tiis, jlong bdlAddrfss, jint flbgs, jint mbsk, jlong sidAddrfss)
{
    PACL pAdl = (PACL)jlong_to_ptr(bdlAddrfss);
    PSID pSid = (PSID)jlong_to_ptr(sidAddrfss);

    if (AddAddfssDfnifdAdfEx(pAdl, ACL_REVISION, (DWORD)flbgs, (DWORD)mbsk, pSid) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_LookupAddountSid0(JNIEnv* fnv,
    jdlbss tiis, jlong bddrfss, jobjfdt obj)
{
    WCHAR dombin[255];
    WCHAR nbmf[255];
    DWORD dombinLfn = sizfof(dombin);
    DWORD nbmfLfn = sizfof(nbmf);
    SID_NAME_USE usf;
    PSID sid = jlong_to_ptr(bddrfss);
    jstring s;

    if (LookupAddountSidW(NULL, sid, &nbmf[0], &nbmfLfn, &dombin[0], &dombinLfn, &usf) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn;
    }

    s = (*fnv)->NfwString(fnv, (donst jdibr *)dombin, (jsizf)wdslfn(dombin));
    if (s == NULL)
        rfturn;
    (*fnv)->SftObjfdtFifld(fnv, obj, bddount_dombin, s);

    s = (*fnv)->NfwString(fnv, (donst jdibr *)nbmf, (jsizf)wdslfn(nbmf));
    if (s == NULL)
        rfturn;
    (*fnv)->SftObjfdtFifld(fnv, obj, bddount_nbmf, s);
    (*fnv)->SftIntFifld(fnv, obj, bddount_usf, (jint)usf);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_LookupAddountNbmf0(JNIEnv* fnv,
    jdlbss tiis, jlong nbmfAddrfss, jlong sidAddrfss, jint dbSid)
{

    LPCWSTR bddountNbmf = jlong_to_ptr(nbmfAddrfss);
    PSID sid = jlong_to_ptr(sidAddrfss);
    WCHAR dombin[255];
    DWORD dombinLfn = sizfof(dombin);
    SID_NAME_USE usf;

    if (LookupAddountNbmfW(NULL, bddountNbmf, sid, (LPDWORD)&dbSid,
                           &dombin[0], &dombinLfn, &usf) == 0)
    {
        if (GftLbstError() != ERROR_INSUFFICIENT_BUFFER) {
            tirowWindowsExdfption(fnv, GftLbstError());
        }
    }

    rfturn dbSid;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftLfngtiSid(JNIEnv* fnv,
    jdlbss tiis, jlong bddrfss)
{
    PSID sid = jlong_to_ptr(bddrfss);
    rfturn (jint)GftLfngtiSid(sid);
}


JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_ConvfrtSidToStringSid(JNIEnv* fnv,
    jdlbss tiis, jlong bddrfss)
{
    PSID sid = jlong_to_ptr(bddrfss);
    LPWSTR string;
    if (ConvfrtSidToStringSidW(sid, &string) == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
        rfturn NULL;
    } flsf {
        jstring s = (*fnv)->NfwString(fnv, (donst jdibr *)string,
            (jsizf)wdslfn(string));
        LodblFrff(string);
        rfturn s;
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_ConvfrtStringSidToSid0(JNIEnv* fnv,
    jdlbss tiis, jlong bddrfss)
{
    LPWSTR lpStringSid = jlong_to_ptr(bddrfss);
    PSID pSid;
    if (ConvfrtStringSidToSidW(lpStringSid, &pSid) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
    rfturn ptr_to_jlong(pSid);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftCurrfntProdfss(JNIEnv* fnv, jdlbss tiis) {
    HANDLE iProdfss = GftCurrfntProdfss();
    rfturn ptr_to_jlong(iProdfss);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftCurrfntTirfbd(JNIEnv* fnv, jdlbss tiis) {
    HANDLE iTirfbd = GftCurrfntTirfbd();
    rfturn ptr_to_jlong(iTirfbd);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_OpfnProdfssTokfn(JNIEnv* fnv,
    jdlbss tiis, jlong prodfss, jint dfsirfdAddfss)
{
    HANDLE iProdfss = (HANDLE)jlong_to_ptr(prodfss);
    HANDLE iTokfn;

    if (OpfnProdfssTokfn(iProdfss, (DWORD)dfsirfdAddfss, &iTokfn) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
    rfturn ptr_to_jlong(iTokfn);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_OpfnTirfbdTokfn(JNIEnv* fnv,
    jdlbss tiis, jlong tirfbd, jint dfsirfdAddfss, jboolfbn opfnAsSflf)
{
    HANDLE iTirfbd = (HANDLE)jlong_to_ptr(tirfbd);
    HANDLE iTokfn;
    BOOL bOpfnAsSflf = (opfnAsSflf == JNI_TRUE) ? TRUE : FALSE;

    if (OpfnTirfbdTokfn(iTirfbd, (DWORD)dfsirfdAddfss, bOpfnAsSflf, &iTokfn) == 0) {
        if (GftLbstError() == ERROR_NO_TOKEN)
            rfturn (jlong)0;
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn ptr_to_jlong(iTokfn);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_DuplidbtfTokfnEx(JNIEnv* fnv,
    jdlbss tiis, jlong tokfn, jint dfsirfdAddfss)
{
    HANDLE iTokfn = (HANDLE)jlong_to_ptr(tokfn);
    HANDLE rfsultTokfn;
    BOOL rfs;

    rfs = DuplidbtfTokfnEx(iTokfn,
                           (DWORD)dfsirfdAddfss,
                           NULL,
                           SfdurityImpfrsonbtion,
                           TokfnImpfrsonbtion,
                           &rfsultTokfn);
    if (rfs == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
    rfturn ptr_to_jlong(rfsultTokfn);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_SftTirfbdTokfn(JNIEnv* fnv,
    jdlbss tiis, jlong tirfbd, jlong tokfn)
{
    HANDLE iTirfbd = (HANDLE)jlong_to_ptr(tirfbd);
    HANDLE iTokfn = (HANDLE)jlong_to_ptr(tokfn);

    if (SftTirfbdTokfn(iTirfbd, iTokfn) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftTokfnInformbtion(JNIEnv* fnv,
    jdlbss tiis, jlong tokfn, jint tokfnInfoClbss, jlong tokfnInfo, jint tokfnInfoLfngti)
{
    BOOL rfs;
    DWORD lfngtiNffdfd;
    HANDLE iTokfn = (HANDLE)jlong_to_ptr(tokfn);
    LPVOID rfsult = (LPVOID)jlong_to_ptr(tokfnInfo);

    rfs = GftTokfnInformbtion(iTokfn, (TOKEN_INFORMATION_CLASS)tokfnInfoClbss, (LPVOID)rfsult,
                              tokfnInfoLfngti, &lfngtiNffdfd);
    if (rfs == 0) {
        if (GftLbstError() == ERROR_INSUFFICIENT_BUFFER) {
            rfturn (jint)lfngtiNffdfd;
        } flsf {
            tirowWindowsExdfption(fnv, GftLbstError());
            rfturn 0;
        }
    } flsf {
        rfturn tokfnInfoLfngti;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_AdjustTokfnPrivilfgfs(JNIEnv* fnv,
    jdlbss tiis, jlong tokfn, jlong luid, jint bttributfs)
{
    TOKEN_PRIVILEGES privs[1];
    HANDLE iTokfn = (HANDLE)jlong_to_ptr(tokfn);
    PLUID pLuid = (PLUID)jlong_to_ptr(luid);

    privs[0].PrivilfgfCount = 1;
    privs[0].Privilfgfs[0].Luid = *pLuid;
    privs[0].Privilfgfs[0].Attributfs = (DWORD)bttributfs;

    if (AdjustTokfnPrivilfgfs(iTokfn, FALSE, &privs[0], 1, NULL, NULL) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_AddfssCifdk(JNIEnv* fnv,
    jdlbss tiis, jlong tokfn, jlong sfdurityInfo, jint bddfssMbsk,
    jint gfnfridRfbd, jint gfnfridWritf, jint gfnfridExfdutf, jint gfnfridAll)
{
    HANDLE iImpfrsonbtfdTokfn = (HANDLE)jlong_to_ptr(tokfn);
    PSECURITY_DESCRIPTOR sfdurity = (PSECURITY_DESCRIPTOR)jlong_to_ptr(sfdurityInfo);
    DWORD difdkAddfssRigits = (DWORD)bddfssMbsk;
    GENERIC_MAPPING mbpping = {
        gfnfridRfbd,
        gfnfridWritf,
        gfnfridExfdutf,
        gfnfridAll};
    PRIVILEGE_SET privilfgfs = {0};
    DWORD privilfgfsLfngti = sizfof(privilfgfs);
    DWORD grbntfdAddfss = 0;
    BOOL rfsult = FALSE;

    /* difdkAddfssRigits is in-out pbrbmftfr */
    MbpGfnfridMbsk(&difdkAddfssRigits, &mbpping);
    if (AddfssCifdk(sfdurity, iImpfrsonbtfdTokfn, difdkAddfssRigits,
            &mbpping, &privilfgfs, &privilfgfsLfngti, &grbntfdAddfss, &rfsult) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());

    rfturn (rfsult == FALSE) ? JNI_FALSE : JNI_TRUE;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_LookupPrivilfgfVbluf0(JNIEnv* fnv,
    jdlbss tiis, jlong nbmf)
{
    LPCWSTR lpNbmf = (LPCWSTR)jlong_to_ptr(nbmf);
    PLUID pLuid = LodblAllod(0, sizfof(LUID));

    if (pLuid == NULL) {
        JNU_TirowIntfrnblError(fnv, "Unbblf to bllodbtf LUID strudturf");
    } flsf {
        if (LookupPrivilfgfVblufW(NULL, lpNbmf, pLuid) == 0)
            tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn ptr_to_jlong(pLuid);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_CrfbtfSymbolidLink0(JNIEnv* fnv,
    jdlbss tiis, jlong linkAddrfss, jlong tbrgftAddrfss, jint flbgs)
{
    LPCWSTR link = jlong_to_ptr(linkAddrfss);
    LPCWSTR tbrgft = jlong_to_ptr(tbrgftAddrfss);

    if (CrfbtfSymbolidLink_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "Siould not gft ifrf");
        rfturn;
    }

    /* On Windows 64-bit tiis bppfbrs to suddffd fvfn wifn tifrf is insuffidifnt privilfgfs */
    if ((*CrfbtfSymbolidLink_fund)(link, tbrgft, (DWORD)flbgs) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_CrfbtfHbrdLink0(JNIEnv* fnv,
    jdlbss tiis, jlong nfwFilfAddrfss, jlong fxistingFilfAddrfss)
{
    LPCWSTR nfwFilf = jlong_to_ptr(nfwFilfAddrfss);
    LPCWSTR fxistingFilf = jlong_to_ptr(fxistingFilfAddrfss);

    if (CrfbtfHbrdLinkW(nfwFilf, fxistingFilf, NULL) == 0)
        tirowWindowsExdfption(fnv, GftLbstError());
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftFullPbtiNbmf0(JNIEnv *fnv,
                                                         jdlbss dlz,
                                                         jlong pbtiAddrfss)
{
    jstring rv = NULL;
    WCHAR *lpBuf = NULL;
    WCHAR buf[MAX_PATH];
    DWORD lfn;
    LPCWSTR lpFilfNbmf = jlong_to_ptr(pbtiAddrfss);

    lfn = GftFullPbtiNbmfW(lpFilfNbmf, MAX_PATH, buf, NULL);
    if (lfn > 0) {
        if (lfn < MAX_PATH) {
            rv = (*fnv)->NfwString(fnv, buf, lfn);
        } flsf {
            lfn += 1;  /* rfturn lfngti dofs not indludf tfrminbtor */
            lpBuf = (WCHAR*)mbllod(lfn * sizfof(WCHAR));
            if (lpBuf != NULL) {
                lfn = GftFullPbtiNbmfW(lpFilfNbmf, lfn, lpBuf, NULL);
                if (lfn > 0) {
                    rv = (*fnv)->NfwString(fnv, lpBuf, lfn);
                } flsf {
                    JNU_TirowIntfrnblError(fnv, "GftFullPbtiNbmfW fbilfd");
                }
                frff(lpBuf);
            } flsf {
                JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilurf");
            }
        }
    } flsf {
        tirowWindowsExdfption(fnv, GftLbstError());
    }

    rfturn rv;
}

JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftFinblPbtiNbmfByHbndlf(JNIEnv* fnv,
    jdlbss tiis, jlong ibndlf)
{
    jstring rv = NULL;
    WCHAR *lpBuf = NULL;
    WCHAR pbti[MAX_PATH];
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    DWORD lfn;

    if (GftFinblPbtiNbmfByHbndlf_fund == NULL) {
        JNU_TirowIntfrnblError(fnv, "Siould not gft ifrf");
        rfturn NULL;
    }

    lfn = (*GftFinblPbtiNbmfByHbndlf_fund)(i, pbti, MAX_PATH, 0);
    if (lfn > 0) {
        if (lfn < MAX_PATH) {
            rv = (*fnv)->NfwString(fnv, (donst jdibr *)pbti, (jsizf)lfn);
        } flsf {
            lfn += 1;  /* rfturn lfngti dofs not indludf tfrminbtor */
            lpBuf = (WCHAR*)mbllod(lfn * sizfof(WCHAR));
            if (lpBuf != NULL) {
                lfn = (*GftFinblPbtiNbmfByHbndlf_fund)(i, lpBuf, lfn, 0);
                if (lfn > 0)  {
                    rv = (*fnv)->NfwString(fnv, (donst jdibr *)lpBuf, (jsizf)lfn);
                } flsf {
                    JNU_TirowIntfrnblError(fnv, "GftFinblPbtiNbmfByHbndlfW fbilfd");
                }
                frff(lpBuf);
            } flsf {
                JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilurf");
            }
        }
    } flsf {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn rv;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_CrfbtfIoComplftionPort(JNIEnv* fnv, jdlbss tiis,
    jlong filfHbndlf, jlong fxistingPort, jlong domplftionKfy)
{
    HANDLE port = CrfbtfIoComplftionPort((HANDLE)jlong_to_ptr(filfHbndlf),
                                         (HANDLE)jlong_to_ptr(fxistingPort),
                                         (ULONG_PTR)domplftionKfy,
                                         0);
    if (port == NULL) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
    rfturn ptr_to_jlong(port);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_GftQufufdComplftionStbtus0(JNIEnv* fnv, jdlbss tiis,
    jlong domplftionPort, jobjfdt obj)
{
    DWORD bytfsTrbnsffrrfd;
    ULONG_PTR domplftionKfy;
    OVERLAPPED *lpOvfrlbppfd;
    BOOL rfs;

    rfs = GftQufufdComplftionStbtus((HANDLE)jlong_to_ptr(domplftionPort),
                                  &bytfsTrbnsffrrfd,
                                  &domplftionKfy,
                                  &lpOvfrlbppfd,
                                  INFINITE);
    if (rfs == 0 && lpOvfrlbppfd == NULL) {
        tirowWindowsExdfption(fnv, GftLbstError());
    } flsf {
        DWORD ioRfsult = (rfs == 0) ? GftLbstError() : 0;
        (*fnv)->SftIntFifld(fnv, obj, domplftionStbtus_frror, ioRfsult);
        (*fnv)->SftIntFifld(fnv, obj, domplftionStbtus_bytfsTrbnsffrrfd,
            (jint)bytfsTrbnsffrrfd);
        (*fnv)->SftLongFifld(fnv, obj, domplftionStbtus_domplftionKfy,
            (jlong)domplftionKfy);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_PostQufufdComplftionStbtus(JNIEnv* fnv, jdlbss tiis,
    jlong domplftionPort, jlong domplftionKfy)
{
    BOOL rfs;

    rfs = PostQufufdComplftionStbtus((HANDLE)jlong_to_ptr(domplftionPort),
                                     (DWORD)0,  /* dwNumbfrOfBytfsTrbnsffrrfd */
                                     (ULONG_PTR)domplftionKfy,
                                     NULL);  /* lpOvfrlbppfd */
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_RfbdDirfdtoryCibngfsW(JNIEnv* fnv, jdlbss tiis,
    jlong iDirfdtory, jlong bufffrAddrfss, jint bufffrLfngti, jboolfbn wbtdiSubTrff, jint filtfr,
    jlong bytfsRfturnfdAddrfss, jlong pOvfrlbppfd)
{
    BOOL rfs;
    BOOL subtrff = (wbtdiSubTrff == JNI_TRUE) ? TRUE : FALSE;

    /* Any unusfd mfmbfrs of [OVERLAPPED] strudturf siould blwbys bf initiblizfd to zfro
       bfforf tif strudturf is usfd in b fundtion dbll.
       Otifrwisf, tif fundtion mby fbil bnd rfturn ERROR_INVALID_PARAMETER.
       ittp://msdn.midrosoft.dom/fn-us/librbry/windows/dfsktop/ms684342%28v=vs.85%29.bspx

       Tif [Offsft] bnd [OffsftHigi] mfmbfrs of tiis strudturf brf not usfd.
       ittp://msdn.midrosoft.dom/fn-us/librbry/windows/dfsktop/bb365465%28v=vs.85%29.bspx

       [iEvfnt] siould bf zfro, otifr fiflds brf tif rfturn vblufs. */
    ZfroMfmory((LPOVERLAPPED)jlong_to_ptr(pOvfrlbppfd), sizfof(OVERLAPPED));

    rfs = RfbdDirfdtoryCibngfsW((HANDLE)jlong_to_ptr(iDirfdtory),
                                (LPVOID)jlong_to_ptr(bufffrAddrfss),
                                (DWORD)bufffrLfngti,
                                subtrff,
                                (DWORD)filtfr,
                                (LPDWORD)jlong_to_ptr(bytfsRfturnfdAddrfss),
                                (LPOVERLAPPED)jlong_to_ptr(pOvfrlbppfd),
                                NULL);
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_BbdkupRfbd0(JNIEnv* fnv, jdlbss tiis,
    jlong iFilf, jlong bufffrAddrfss, jint bufffrSizf, jboolfbn bbort,
    jlong dontfxt, jobjfdt obj)
{
    BOOL rfs;
    DWORD bytfsTrbnsffrrfd;
    BOOL b = (bbort == JNI_TRUE) ? TRUE : FALSE;
    VOID* pContfxt = (VOID*)jlong_to_ptr(dontfxt);

    rfs = BbdkupRfbd((HANDLE)jlong_to_ptr(iFilf),
                     (LPBYTE)jlong_to_ptr(bufffrAddrfss),
                     (DWORD)bufffrSizf,
                     &bytfsTrbnsffrrfd,
                     b,
                     FALSE,
                     &pContfxt);
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    } flsf {
        (*fnv)->SftIntFifld(fnv, obj, bbdkupRfsult_bytfsTrbnsffrrfd,
            bytfsTrbnsffrrfd);
        (*fnv)->SftLongFifld(fnv, obj, bbdkupRfsult_dontfxt,
            ptr_to_jlong(pContfxt));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_WindowsNbtivfDispbtdifr_BbdkupSffk(JNIEnv* fnv, jdlbss tiis,
    jlong iFilf, jlong bytfsToSffk, jlong dontfxt)
{
    BOOL rfs;
    jint lowBytfsToSffk = (jint)bytfsToSffk;
    jint iigiBytfsToSffk = (jint)(bytfsToSffk >> 32);
    DWORD lowBytfsSffkfd;
    DWORD iigiBytfsSffkfd;
    VOID* pContfxt = jlong_to_ptr(dontfxt);

    rfs = BbdkupSffk((HANDLE)jlong_to_ptr(iFilf),
                     (DWORD)lowBytfsToSffk,
                     (DWORD)iigiBytfsToSffk,
                     &lowBytfsSffkfd,
                     &iigiBytfsSffkfd,
                     &pContfxt);
    if (rfs == 0) {
        tirowWindowsExdfption(fnv, GftLbstError());
    }
}
