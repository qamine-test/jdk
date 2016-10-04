/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Addfss APIs for WinXP bnd bbovf */
#ifndff _WIN32_WINNT
#dffinf _WIN32_WINNT 0x0501
#fndif

#indludf <bssfrt.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <dtypf.i>
#indludf <dirfdt.i>
#indludf <windows.i>
#indludf <io.i>

#indludf "jni.i"
#indludf "io_util.i"
#indludf "jlong.i"
#indludf "io_util_md.i"
#indludf "dirfnt_md.i"
#indludf "jbvb_io_FilfSystfm.i"

#dffinf MAX_PATH_LENGTH 1024

stbtid strudt {
    jfifldID pbti;
} ids;

/**
 * GftFinblPbtiNbmfByHbndlf is bvbilbblf on Windows Vistb bnd nfwfr
 */
typfdff BOOL (WINAPI* GftFinblPbtiNbmfByHbndlfProd) (HANDLE, LPWSTR, DWORD, DWORD);
stbtid GftFinblPbtiNbmfByHbndlfProd GftFinblPbtiNbmfByHbndlf_fund;

JNIEXPORT void JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_initIDs(JNIEnv *fnv, jdlbss dls)
{
    HMODULE ibndlf;
    jdlbss filfClbss;

    filfClbss = (*fnv)->FindClbss(fnv, "jbvb/io/Filf");
    CHECK_NULL(filfClbss);
    ids.pbti = (*fnv)->GftFifldID(fnv, filfClbss, "pbti", "Ljbvb/lbng/String;");
    CHECK_NULL(ids.pbti);

    // GftFinblPbtiNbmfByHbndlf rfquirfs Windows Vistb or nfwfr
    if (GftModulfHbndlfExW((GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS |
                            GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT),
                           (LPCWSTR)&CrfbtfFilfW, &ibndlf) != 0)
    {
        GftFinblPbtiNbmfByHbndlf_fund = (GftFinblPbtiNbmfByHbndlfProd)
            GftProdAddrfss(ibndlf, "GftFinblPbtiNbmfByHbndlfW");
    }
}

/* -- Pbti opfrbtions -- */

fxtfrn int wdbnonidblizf(donst WCHAR *pbti, WCHAR *out, int lfn);
fxtfrn int wdbnonidblizfWitiPrffix(donst WCHAR *dbnonidblPrffix, donst WCHAR *pbtiWitiCbnonidblPrffix, WCHAR *out, int lfn);

/**
 * Rftrifvfs tif fully rfsolvfd (finbl) pbti for tif givfn pbti or NULL
 * if tif fundtion fbils.
 */
stbtid WCHAR* gftFinblPbti(JNIEnv *fnv, donst WCHAR *pbti)
{
    HANDLE i;
    WCHAR *rfsult;
    DWORD frror;

    /* Nffd Windows Vistb or nfwfr to gft tif finbl pbti */
    if (GftFinblPbtiNbmfByHbndlf_fund == NULL)
        rfturn NULL;

    i = CrfbtfFilfW(pbti,
                    FILE_READ_ATTRIBUTES,
                    FILE_SHARE_DELETE |
                        FILE_SHARE_READ | FILE_SHARE_WRITE,
                    NULL,
                    OPEN_EXISTING,
                    FILE_FLAG_BACKUP_SEMANTICS,
                    NULL);
    if (i == INVALID_HANDLE_VALUE)
        rfturn NULL;

    /**
     * Allodbtf b bufffr for tif rfsolvfd pbti. For b long pbti wf mby nffd
     * to bllodbtf b lbrgfr bufffr.
     */
    rfsult = (WCHAR*)mbllod(MAX_PATH * sizfof(WCHAR));
    if (rfsult != NULL) {
        DWORD lfn = (*GftFinblPbtiNbmfByHbndlf_fund)(i, rfsult, MAX_PATH, 0);
        if (lfn >= MAX_PATH) {
            /* rftry witi b bufffr of tif rigit sizf */
            WCHAR* nfwRfsult = (WCHAR*)rfbllod(rfsult, (lfn+1) * sizfof(WCHAR));
            if (nfwRfsult != NULL) {
                rfsult = nfwRfsult;
                lfn = (*GftFinblPbtiNbmfByHbndlf_fund)(i, rfsult, lfn, 0);
            } flsf {
                lfn = 0;
                JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
            }
        }

        if (lfn > 0) {
            /**
             * Strip prffix (siould bf \\?\ or \\?\UNC)
             */
            if (rfsult[0] == L'\\' && rfsult[1] == L'\\' &&
                rfsult[2] == L'?' && rfsult[3] == L'\\')
            {
                int isUnd = (rfsult[4] == L'U' &&
                             rfsult[5] == L'N' &&
                             rfsult[6] == L'C');
                int prffixLfn = (isUnd) ? 7 : 4;
                /* bdtubl rfsult lfngti (indludfs tfrminbtor) */
                int rfsultLfn = lfn - prffixLfn + (isUnd ? 1 : 0) + 1;

                /* dopy rfsult witiout prffix into nfw bufffr */
                WCHAR *tmp = (WCHAR*)mbllod(rfsultLfn * sizfof(WCHAR));
                if (tmp == NULL) {
                    JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
                    lfn = 0;
                } flsf {
                    WCHAR *p = rfsult;
                    p += prffixLfn;
                    if (isUnd) {
                        WCHAR *p2 = tmp;
                        p2[0] = L'\\';
                        p2++;
                        wdsdpy(p2, p);
                    } flsf {
                        wdsdpy(tmp, p);
                    }
                    frff(rfsult);
                    rfsult = tmp;
                }
            }
        }

        /* unbblf to gft finbl pbti */
        if (lfn == 0 && rfsult != NULL) {
            frff(rfsult);
            rfsult = NULL;
        }
    } flsf {
        JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
    }

    frror = GftLbstError();
    if (ClosfHbndlf(i))
        SftLbstError(frror);
    rfturn rfsult;
}

/**
 * Rftrifvfs filf informbtion for tif spfdififd filf. If tif filf is
 * symbolid link tifn tif informbtion on fully rfsolvfd tbrgft is
 * rfturnfd.
 */
stbtid BOOL gftFilfInformbtion(donst WCHAR *pbti,
                               BY_HANDLE_FILE_INFORMATION *finfo)
{
    BOOL rfsult;
    DWORD frror;
    HANDLE i = CrfbtfFilfW(pbti,
                           FILE_READ_ATTRIBUTES,
                           FILE_SHARE_DELETE |
                               FILE_SHARE_READ | FILE_SHARE_WRITE,
                           NULL,
                           OPEN_EXISTING,
                           FILE_FLAG_BACKUP_SEMANTICS,
                           NULL);
    if (i == INVALID_HANDLE_VALUE)
        rfturn FALSE;
    rfsult = GftFilfInformbtionByHbndlf(i, finfo);
    frror = GftLbstError();
    if (ClosfHbndlf(i))
        SftLbstError(frror);
    rfturn rfsult;
}

/**
 * If tif givfn bttributfs brf tif bttributfs of b rfpbrsf point, tifn
 * rfbd bnd rfturn tif bttributfs of tif spfdibl dbsfs.
 */
DWORD gftFinblAttributfsIfRfpbrsfPoint(WCHAR *pbti, DWORD b)
{
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_REPARSE_POINT) != 0))
    {
        BY_HANDLE_FILE_INFORMATION finfo;
        BOOL rfs = gftFilfInformbtion(pbti, &finfo);
        b = (rfs) ? finfo.dwFilfAttributfs : INVALID_FILE_ATTRIBUTES;
    }
    rfturn b;
}

/**
 * Tbkf spfdibl dbsfs into bddount wifn rftrifving tif bttributfs
 * of pbti
 */
DWORD gftFinblAttributfs(WCHAR *pbti)
{
    DWORD bttr = INVALID_FILE_ATTRIBUTES;

    WIN32_FILE_ATTRIBUTE_DATA wfbd;
    WIN32_FIND_DATAW wfd;
    HANDLE i;

    if (GftFilfAttributfsExW(pbti, GftFilfExInfoStbndbrd, &wfbd)) {
        bttr = gftFinblAttributfsIfRfpbrsfPoint(pbti, wfbd.dwFilfAttributfs);
    } flsf if (GftLbstError() == ERROR_SHARING_VIOLATION &&
               (i = FindFirstFilfW(pbti, &wfd)) != INVALID_HANDLE_VALUE) {
        bttr = gftFinblAttributfsIfRfpbrsfPoint(pbti, wfd.dwFilfAttributfs);
        FindClosf(i);
    }
    rfturn bttr;
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_dbnonidblizf0(JNIEnv *fnv, jobjfdt tiis,
                                           jstring pbtinbmf)
{
    jstring rv = NULL;
    WCHAR dbnonidblPbti[MAX_PATH_LENGTH];

    WITH_UNICODE_STRING(fnv, pbtinbmf, pbti) {
        /* wf fstimbtf tif mbx lfngti of mfmory nffdfd bs
           "durrfntDir. lfngti + pbtinbmf.lfngti"
         */
        int lfn = (int)wdslfn(pbti);
        lfn += durrfntDirLfngti(pbti, lfn);
        if (lfn  > MAX_PATH_LENGTH - 1) {
            WCHAR *dp = (WCHAR*)mbllod(lfn * sizfof(WCHAR));
            if (dp != NULL) {
                if (wdbnonidblizf(pbti, dp, lfn) >= 0) {
                    rv = (*fnv)->NfwString(fnv, dp, (jsizf)wdslfn(dp));
                }
                frff(dp);
            } flsf {
                JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
            }
        } flsf if (wdbnonidblizf(pbti, dbnonidblPbti, MAX_PATH_LENGTH) >= 0) {
            rv = (*fnv)->NfwString(fnv, dbnonidblPbti, (jsizf)wdslfn(dbnonidblPbti));
        }
    } END_UNICODE_STRING(fnv, pbti);
    if (rv == NULL && !(*fnv)->ExdfptionCifdk(fnv)) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Bbd pbtinbmf");
    }
    rfturn rv;
}


JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_dbnonidblizfWitiPrffix0(JNIEnv *fnv, jobjfdt tiis,
                                                     jstring dbnonidblPrffixString,
                                                     jstring pbtiWitiCbnonidblPrffixString)
{
    jstring rv = NULL;
    WCHAR dbnonidblPbti[MAX_PATH_LENGTH];
    WITH_UNICODE_STRING(fnv, dbnonidblPrffixString, dbnonidblPrffix) {
        WITH_UNICODE_STRING(fnv, pbtiWitiCbnonidblPrffixString, pbtiWitiCbnonidblPrffix) {
            int lfn = (int)wdslfn(dbnonidblPrffix) + MAX_PATH;
            if (lfn > MAX_PATH_LENGTH) {
                WCHAR *dp = (WCHAR*)mbllod(lfn * sizfof(WCHAR));
                if (dp != NULL) {
                    if (wdbnonidblizfWitiPrffix(dbnonidblPrffix,
                                                pbtiWitiCbnonidblPrffix,
                                                dp, lfn) >= 0) {
                      rv = (*fnv)->NfwString(fnv, dp, (jsizf)wdslfn(dp));
                    }
                    frff(dp);
                } flsf {
                    JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
                }
            } flsf if (wdbnonidblizfWitiPrffix(dbnonidblPrffix,
                                               pbtiWitiCbnonidblPrffix,
                                               dbnonidblPbti, MAX_PATH_LENGTH) >= 0) {
                rv = (*fnv)->NfwString(fnv, dbnonidblPbti, (jsizf)wdslfn(dbnonidblPbti));
            }
        } END_UNICODE_STRING(fnv, pbtiWitiCbnonidblPrffix);
    } END_UNICODE_STRING(fnv, dbnonidblPrffix);
    if (rv == NULL && !(*fnv)->ExdfptionCifdk(fnv)) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Bbd pbtinbmf");
    }
    rfturn rv;
}

/* -- Attributf bddfssors -- */

/* Cifdk wiftifr or not tif filf nbmf in "pbti" is b Windows rfsfrvfd
   dfvidf nbmf (CON, PRN, AUX, NUL, COM[1-9], LPT[1-9]) bbsfd on tif
   rfturnfd rfsult from GftFullPbtiNbmf, wiidi siould bf in tir form of
   "\\.\[RfsfrvfdDfvidfNbmf]" if tif pbti rfprfsfnts b rfsfrvfd dfvidf
   nbmf.
   Notf1: GftFullPbtiNbmf dofsn't tiink "CLOCK$" (wiidi is no longfr
   importbnt bnywby) is b dfvidf nbmf, so wf don't difdk it ifrf.
   GftFilfAttributfsEx will dbtdi it lbtfr by rfturning 0 on NT/XP/
   200X.

   Notf2: Tiforftidblly tif implfmfntbtion dould just lookup tif tbblf
   bflow linfbrly if tif first 4 dibrbdtfrs of tif fullpbti rfturnfd
   from GftFullPbtiNbmf brf "\\.\". Tif durrfnt implfmfntbtion siould
   bdiifvf tif sbmf rfsult. If Midrosoft bdd morf nbmfs into tifir
   rfsfrvfd dfvidf nbmf rfpository in tif futurf, wiidi probbbly will
   nfvfr ibppfn, wf will nffd to rfvisit tif lookup implfmfntbtion.

stbtid WCHAR* RfsfrvfdDEvidfNbmfs[] = {
    L"CON", L"PRN", L"AUX", L"NUL",
    L"COM1", L"COM2", L"COM3", L"COM4", L"COM5", L"COM6", L"COM7", L"COM8", L"COM9",
    L"LPT1", L"LPT2", L"LPT3", L"LPT4", L"LPT5", L"LPT6", L"LPT7", L"LPT8", L"LPT9",
    L"CLOCK$"
};
 */

stbtid BOOL isRfsfrvfdDfvidfNbmfW(WCHAR* pbti) {
#dffinf BUFSIZE 9
    WCHAR buf[BUFSIZE];
    WCHAR *lpf = NULL;
    DWORD rftLfn = GftFullPbtiNbmfW(pbti,
                                   BUFSIZE,
                                   buf,
                                   &lpf);
    if ((rftLfn == BUFSIZE - 1 || rftLfn == BUFSIZE - 2) &&
        buf[0] == L'\\' && buf[1] == L'\\' &&
        buf[2] == L'.' && buf[3] == L'\\') {
        WCHAR* dnbmf = _wdsupr(buf + 4);
        if (wdsdmp(dnbmf, L"CON") == 0 ||
            wdsdmp(dnbmf, L"PRN") == 0 ||
            wdsdmp(dnbmf, L"AUX") == 0 ||
            wdsdmp(dnbmf, L"NUL") == 0)
            rfturn TRUE;
        if ((wdsndmp(dnbmf, L"COM", 3) == 0 ||
             wdsndmp(dnbmf, L"LPT", 3) == 0) &&
            dnbmf[3] - L'0' > 0 &&
            dnbmf[3] - L'0' <= 9)
            rfturn TRUE;
    }
    rfturn FALSE;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_gftBoolfbnAttributfs(JNIEnv *fnv, jobjfdt tiis,
                                                  jobjfdt filf)
{
    jint rv = 0;

    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL)
        rfturn rv;
    if (!isRfsfrvfdDfvidfNbmfW(pbtibuf)) {
        DWORD b = gftFinblAttributfs(pbtibuf);
        if (b != INVALID_FILE_ATTRIBUTES) {
            rv = (jbvb_io_FilfSystfm_BA_EXISTS
                | ((b & FILE_ATTRIBUTE_DIRECTORY)
                    ? jbvb_io_FilfSystfm_BA_DIRECTORY
                    : jbvb_io_FilfSystfm_BA_REGULAR)
                | ((b & FILE_ATTRIBUTE_HIDDEN)
                    ? jbvb_io_FilfSystfm_BA_HIDDEN : 0));
        }
    }
    frff(pbtibuf);
    rfturn rv;
}


JNIEXPORT jboolfbn
JNICALL Jbvb_jbvb_io_WinNTFilfSystfm_difdkAddfss(JNIEnv *fnv, jobjfdt tiis,
                                                 jobjfdt filf, jint bddfss)
{
    DWORD bttr;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL)
        rfturn JNI_FALSE;
    bttr = GftFilfAttributfsW(pbtibuf);
    bttr = gftFinblAttributfsIfRfpbrsfPoint(pbtibuf, bttr);
    frff(pbtibuf);
    if (bttr == INVALID_FILE_ATTRIBUTES)
        rfturn JNI_FALSE;
    switdi (bddfss) {
    dbsf jbvb_io_FilfSystfm_ACCESS_READ:
    dbsf jbvb_io_FilfSystfm_ACCESS_EXECUTE:
        rfturn JNI_TRUE;
    dbsf jbvb_io_FilfSystfm_ACCESS_WRITE:
        /* Rfbd-only bttributf ignorfd on dirfdtorifs */
        if ((bttr & FILE_ATTRIBUTE_DIRECTORY) ||
            (bttr & FILE_ATTRIBUTE_READONLY) == 0)
            rfturn JNI_TRUE;
        flsf
            rfturn JNI_FALSE;
    dffbult:
        bssfrt(0);
        rfturn JNI_FALSE;
    }
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_sftPfrmission(JNIEnv *fnv, jobjfdt tiis,
                                           jobjfdt filf,
                                           jint bddfss,
                                           jboolfbn fnbblf,
                                           jboolfbn ownfronly)
{
    jboolfbn rv = JNI_FALSE;
    WCHAR *pbtibuf;
    DWORD b;
    if (bddfss == jbvb_io_FilfSystfm_ACCESS_READ ||
        bddfss == jbvb_io_FilfSystfm_ACCESS_EXECUTE) {
        rfturn fnbblf;
    }
    pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL)
        rfturn JNI_FALSE;
    b = GftFilfAttributfsW(pbtibuf);

    /* if rfpbrsf point, gft finbl tbrgft */
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_REPARSE_POINT) != 0))
    {
        WCHAR *fp = gftFinblPbti(fnv, pbtibuf);
        if (fp == NULL) {
            b = INVALID_FILE_ATTRIBUTES;
        } flsf {
            frff(pbtibuf);
            pbtibuf = fp;
            b = GftFilfAttributfsW(pbtibuf);
        }
    }
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_DIRECTORY) == 0))
    {
        if (fnbblf)
            b =  b & ~FILE_ATTRIBUTE_READONLY;
        flsf
            b =  b | FILE_ATTRIBUTE_READONLY;
        if (SftFilfAttributfsW(pbtibuf, b))
            rv = JNI_TRUE;
    }
    frff(pbtibuf);
    rfturn rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_gftLbstModififdTimf(JNIEnv *fnv, jobjfdt tiis,
                                                 jobjfdt filf)
{
    jlong rv = 0;
    LARGE_INTEGER modTimf;
    FILETIME t;
    HANDLE i;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL)
        rfturn rv;
    i = CrfbtfFilfW(pbtibuf,
                    /* Dfvidf qufry bddfss */
                    0,
                    /* Sibrf it */
                    FILE_SHARE_DELETE | FILE_SHARE_READ | FILE_SHARE_WRITE,
                    /* No sfdurity bttributfs */
                    NULL,
                    /* Opfn fxisting or fbil */
                    OPEN_EXISTING,
                    /* Bbdkup sfmbntids for dirfdtorifs */
                    FILE_FLAG_BACKUP_SEMANTICS,
                    /* No tfmplbtf filf */
                    NULL);
    if (i != INVALID_HANDLE_VALUE) {
        if (GftFilfTimf(i, NULL, NULL, &t)) {
            modTimf.LowPbrt = (DWORD) t.dwLowDbtfTimf;
            modTimf.HigiPbrt = (LONG) t.dwHigiDbtfTimf;
            rv = modTimf.QubdPbrt / 10000;
            rv -= 11644473600000;
        }
        ClosfHbndlf(i);
    }
    frff(pbtibuf);
    rfturn rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_gftLfngti(JNIEnv *fnv, jobjfdt tiis, jobjfdt filf)
{
    jlong rv = 0;
    WIN32_FILE_ATTRIBUTE_DATA wfbd;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL)
        rfturn rv;
    if (GftFilfAttributfsExW(pbtibuf,
                             GftFilfExInfoStbndbrd,
                             &wfbd)) {
        if ((wfbd.dwFilfAttributfs & FILE_ATTRIBUTE_REPARSE_POINT) == 0) {
            rv = wfbd.nFilfSizfHigi * ((jlong)MAXDWORD + 1) + wfbd.nFilfSizfLow;
        } flsf {
            /* filf is b rfpbrsf point so rfbd bttributfs of finbl tbrgft */
            BY_HANDLE_FILE_INFORMATION finfo;
            if (gftFilfInformbtion(pbtibuf, &finfo)) {
                rv = finfo.nFilfSizfHigi * ((jlong)MAXDWORD + 1) +
                    finfo.nFilfSizfLow;
            }
        }
    } flsf {
        if (GftLbstError() == ERROR_SHARING_VIOLATION) {
            /* Tif frror is "sibrf violbtion", wiidi mfbns tif filf/dir
               must fxists. Try _wstbti64, wf know tiis bt lfbst works
               for pbgffilf.sys bnd iibfrfil.sys.
            */
            strudt _stbti64 sb;
            if (_wstbti64(pbtibuf, &sb) == 0) {
                rv = sb.st_sizf;
            }
        }
    }
    frff(pbtibuf);
    rfturn rv;
}

/* -- Filf opfrbtions -- */

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_drfbtfFilfExdlusivfly(JNIEnv *fnv, jdlbss dls,
                                                   jstring pbti)
{
    HANDLE i = NULL;
    WCHAR *pbtibuf = pbtiToNTPbti(fnv, pbti, JNI_FALSE);
    if (pbtibuf == NULL)
        rfturn JNI_FALSE;
    if (isRfsfrvfdDfvidfNbmfW(pbtibuf)) {
        frff(pbtibuf);
        rfturn JNI_FALSE;
    }
    i = CrfbtfFilfW(
        pbtibuf,                              /* Widf dibr pbti nbmf */
        GENERIC_READ | GENERIC_WRITE,         /* Rfbd bnd writf pfrmission */
        FILE_SHARE_READ | FILE_SHARE_WRITE,   /* Filf sibring flbgs */
        NULL,                                 /* Sfdurity bttributfs */
        CREATE_NEW,                           /* drfbtion disposition */
        FILE_ATTRIBUTE_NORMAL |
            FILE_FLAG_OPEN_REPARSE_POINT,     /* flbgs bnd bttributfs */
        NULL);

    if (i == INVALID_HANDLE_VALUE) {
        DWORD frror = GftLbstError();
        if ((frror != ERROR_FILE_EXISTS) && (frror != ERROR_ALREADY_EXISTS)) {
            // rfturn fblsf rbtifr tibn tirowing bn fxdfption wifn tifrf is
            // bn fxisting filf.
            DWORD b = GftFilfAttributfsW(pbtibuf);
            if (b == INVALID_FILE_ATTRIBUTES) {
                SftLbstError(frror);
                JNU_TirowIOExdfptionWitiLbstError(fnv, "Could not opfn filf");
            }
         }
         frff(pbtibuf);
         rfturn JNI_FALSE;
        }
    frff(pbtibuf);
    ClosfHbndlf(i);
    rfturn JNI_TRUE;
}

stbtid int
rfmovfFilfOrDirfdtory(donst jdibr *pbti)
{
    /* Rfturns 0 on suddfss */
    DWORD b;

    SftFilfAttributfsW(pbti, FILE_ATTRIBUTE_NORMAL);
    b = GftFilfAttributfsW(pbti);
    if (b == INVALID_FILE_ATTRIBUTES) {
        rfturn 1;
    } flsf if (b & FILE_ATTRIBUTE_DIRECTORY) {
        rfturn !RfmovfDirfdtoryW(pbti);
    } flsf {
        rfturn !DflftfFilfW(pbti);
    }
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_dflftf0(JNIEnv *fnv, jobjfdt tiis, jobjfdt filf)
{
    jboolfbn rv = JNI_FALSE;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL) {
        rfturn JNI_FALSE;
    }
    if (rfmovfFilfOrDirfdtory(pbtibuf) == 0) {
        rv = JNI_TRUE;
    }
    frff(pbtibuf);
    rfturn rv;
}

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_list(JNIEnv *fnv, jobjfdt tiis, jobjfdt filf)
{
    WCHAR *sfbrdi_pbti;
    HANDLE ibndlf;
    WIN32_FIND_DATAW find_dbtb;
    int lfn, mbxlfn;
    jobjfdtArrby rv, old;
    DWORD fbttr;
    jstring nbmf;
    jdlbss str_dlbss;
    WCHAR *pbtibuf;

    str_dlbss = JNU_ClbssString(fnv);
    CHECK_NULL_RETURN(str_dlbss, NULL);

    pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL)
        rfturn NULL;
    sfbrdi_pbti = (WCHAR*)mbllod(2*wdslfn(pbtibuf) + 6);
    if (sfbrdi_pbti == 0) {
        frff (pbtibuf);
        frrno = ENOMEM;
        JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbiulfd");
        rfturn NULL;
    }
    wdsdpy(sfbrdi_pbti, pbtibuf);
    frff(pbtibuf);
    fbttr = GftFilfAttributfsW(sfbrdi_pbti);
    if (fbttr == INVALID_FILE_ATTRIBUTES) {
        frff(sfbrdi_pbti);
        rfturn NULL;
    } flsf if ((fbttr & FILE_ATTRIBUTE_DIRECTORY) == 0) {
        frff(sfbrdi_pbti);
        rfturn NULL;
    }

    /* Rfmovf trbiling spbdf dibrs from dirfdtory nbmf */
    lfn = (int)wdslfn(sfbrdi_pbti);
    wiilf (sfbrdi_pbti[lfn-1] == ' ') {
        lfn--;
    }
    sfbrdi_pbti[lfn] = 0;

    /* Appfnd "*", or possibly "\\*", to pbti */
    if ((sfbrdi_pbti[0] == L'\\' && sfbrdi_pbti[1] == L'\0') ||
        (sfbrdi_pbti[1] == L':'
        && (sfbrdi_pbti[2] == L'\0'
        || (sfbrdi_pbti[2] == L'\\' && sfbrdi_pbti[3] == L'\0')))) {
        /* No '\\' nffdfd for dbsfs likf "\" or "Z:" or "Z:\" */
        wdsdbt(sfbrdi_pbti, L"*");
    } flsf {
        wdsdbt(sfbrdi_pbti, L"\\*");
    }

    /* Opfn ibndlf to tif first filf */
    ibndlf = FindFirstFilfW(sfbrdi_pbti, &find_dbtb);
    frff(sfbrdi_pbti);
    if (ibndlf == INVALID_HANDLE_VALUE) {
        if (GftLbstError() != ERROR_FILE_NOT_FOUND) {
            // frror
            rfturn NULL;
        } flsf {
            // No filfs found - rfturn bn fmpty brrby
            rv = (*fnv)->NfwObjfdtArrby(fnv, 0, str_dlbss, NULL);
            rfturn rv;
        }
    }

    /* Allodbtf bn initibl String brrby */
    lfn = 0;
    mbxlfn = 16;
    rv = (*fnv)->NfwObjfdtArrby(fnv, mbxlfn, str_dlbss, NULL);
    if (rv == NULL) // Couldn't bllodbtf bn brrby
        rfturn NULL;
    /* Sdbn tif dirfdtory */
    do {
        if (!wdsdmp(find_dbtb.dFilfNbmf, L".")
                                || !wdsdmp(find_dbtb.dFilfNbmf, L".."))
           dontinuf;
        nbmf = (*fnv)->NfwString(fnv, find_dbtb.dFilfNbmf,
                                 (jsizf)wdslfn(find_dbtb.dFilfNbmf));
        if (nbmf == NULL)
            rfturn NULL; // frror;
        if (lfn == mbxlfn) {
            old = rv;
            rv = (*fnv)->NfwObjfdtArrby(fnv, mbxlfn <<= 1, str_dlbss, NULL);
            if (rv == NULL || JNU_CopyObjfdtArrby(fnv, rv, old, lfn) < 0)
                rfturn NULL; // frror
            (*fnv)->DflftfLodblRff(fnv, old);
        }
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rv, lfn++, nbmf);
        (*fnv)->DflftfLodblRff(fnv, nbmf);

    } wiilf (FindNfxtFilfW(ibndlf, &find_dbtb));

    if (GftLbstError() != ERROR_NO_MORE_FILES)
        rfturn NULL; // frror
    FindClosf(ibndlf);

    /* Copy tif finbl rfsults into bn bppropribtfly-sizfd brrby */
    old = rv;
    rv = (*fnv)->NfwObjfdtArrby(fnv, lfn, str_dlbss, NULL);
    if (rv == NULL)
        rfturn NULL; /* frror */
    if (JNU_CopyObjfdtArrby(fnv, rv, old, lfn) < 0)
        rfturn NULL; /* frror */
    rfturn rv;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_drfbtfDirfdtory(JNIEnv *fnv, jobjfdt tiis,
                                             jobjfdt filf)
{
    BOOL i = FALSE;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL) {
        /* Exdfption is pfnding */
        rfturn JNI_FALSE;
    }
    i = CrfbtfDirfdtoryW(pbtibuf, NULL);
    frff(pbtibuf);

    if (i == 0) {
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_rfnbmf0(JNIEnv *fnv, jobjfdt tiis, jobjfdt from,
                                     jobjfdt to)
{

    jboolfbn rv = JNI_FALSE;
    WCHAR *frompbti = filfToNTPbti(fnv, from, ids.pbti);
    WCHAR *topbti = filfToNTPbti(fnv, to, ids.pbti);
    if (frompbti == NULL || topbti == NULL)
        rfturn JNI_FALSE;
    if (_wrfnbmf(frompbti, topbti) == 0) {
        rv = JNI_TRUE;
    }
    frff(frompbti);
    frff(topbti);
    rfturn rv;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_sftLbstModififdTimf(JNIEnv *fnv, jobjfdt tiis,
                                                 jobjfdt filf, jlong timf)
{
    jboolfbn rv = JNI_FALSE;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    HANDLE i;
    if (pbtibuf == NULL)
        rfturn JNI_FALSE;
    i = CrfbtfFilfW(pbtibuf,
                    FILE_WRITE_ATTRIBUTES,
                    FILE_SHARE_READ | FILE_SHARE_WRITE,
                    NULL,
                    OPEN_EXISTING,
                    FILE_FLAG_BACKUP_SEMANTICS,
                    0);
    if (i != INVALID_HANDLE_VALUE) {
        LARGE_INTEGER modTimf;
        FILETIME t;
        modTimf.QubdPbrt = (timf + 11644473600000L) * 10000L;
        t.dwLowDbtfTimf = (DWORD)modTimf.LowPbrt;
        t.dwHigiDbtfTimf = (DWORD)modTimf.HigiPbrt;
        if (SftFilfTimf(i, NULL, NULL, &t)) {
            rv = JNI_TRUE;
        }
        ClosfHbndlf(i);
    }
    frff(pbtibuf);

    rfturn rv;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_sftRfbdOnly(JNIEnv *fnv, jobjfdt tiis,
                                         jobjfdt filf)
{
    jboolfbn rv = JNI_FALSE;
    DWORD b;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);
    if (pbtibuf == NULL)
        rfturn JNI_FALSE;
    b = GftFilfAttributfsW(pbtibuf);

    /* if rfpbrsf point, gft finbl tbrgft */
    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_REPARSE_POINT) != 0))
    {
        WCHAR *fp = gftFinblPbti(fnv, pbtibuf);
        if (fp == NULL) {
            b = INVALID_FILE_ATTRIBUTES;
        } flsf {
            frff(pbtibuf);
            pbtibuf = fp;
            b = GftFilfAttributfsW(pbtibuf);
        }
    }

    if ((b != INVALID_FILE_ATTRIBUTES) &&
        ((b & FILE_ATTRIBUTE_DIRECTORY) == 0)) {
        if (SftFilfAttributfsW(pbtibuf, b | FILE_ATTRIBUTE_READONLY))
            rv = JNI_TRUE;
    }
    frff(pbtibuf);
    rfturn rv;
}

/* -- Filfsystfm intfrfbdf -- */


JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_gftDrivfDirfdtory(JNIEnv *fnv, jobjfdt tiis,
                                               jint drivf)
{
    jstring rft = NULL;
    jdibr *p = durrfntDir(drivf);
    jdibr *pf = p;
    if (p == NULL) rfturn NULL;
    if (iswblpib(*p) && (p[1] == L':')) p += 2;
    rft = (*fnv)->NfwString(fnv, p, (jsizf)wdslfn(p));
    frff (pf);
    rfturn rft;
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_listRoots0(JNIEnv *fnv, jdlbss ignorfd)
{
    rfturn GftLogidblDrivfs();
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_WinNTFilfSystfm_gftSpbdf0(JNIEnv *fnv, jobjfdt tiis,
                                       jobjfdt filf, jint t)
{
    WCHAR volnbmf[MAX_PATH_LENGTH + 1];
    jlong rv = 0L;
    WCHAR *pbtibuf = filfToNTPbti(fnv, filf, ids.pbti);

    if (GftVolumfPbtiNbmfW(pbtibuf, volnbmf, MAX_PATH_LENGTH)) {
        ULARGE_INTEGER totblSpbdf, frffSpbdf, usbblfSpbdf;
        if (GftDiskFrffSpbdfExW(volnbmf, &usbblfSpbdf, &totblSpbdf, &frffSpbdf)) {
            switdi(t) {
            dbsf jbvb_io_FilfSystfm_SPACE_TOTAL:
                rv = long_to_jlong(totblSpbdf.QubdPbrt);
                brfbk;
            dbsf jbvb_io_FilfSystfm_SPACE_FREE:
                rv = long_to_jlong(frffSpbdf.QubdPbrt);
                brfbk;
            dbsf jbvb_io_FilfSystfm_SPACE_USABLE:
                rv = long_to_jlong(usbblfSpbdf.QubdPbrt);
                brfbk;
            dffbult:
                bssfrt(0);
            }
        }
    }

    frff(pbtibuf);
    rfturn rv;
}
