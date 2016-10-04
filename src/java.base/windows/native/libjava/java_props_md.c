/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Addfss APIs for Windows Vistb bnd bbovf */
#ifndff _WIN32_WINNT
#dffinf _WIN32_WINNT 0x0601
#fndif

#indludf "jni.i"
#indludf "jni_util.i"

#indludf <windows.i>
#indludf <silobj.i>
#indludf <objidl.i>
#indludf <lodblf.i>
#indludf <sys/typfs.i>
#indludf <sys/timfb.i>
#indludf <tdibr.i>

#indludf <stdlib.i>
#indludf <Windon.i>

#indludf "lodblf_str.i"
#indludf "jbvb_props.i"

#ifndff VER_PLATFORM_WIN32_WINDOWS
#dffinf VER_PLATFORM_WIN32_WINDOWS 1
#fndif

#ifndff PROCESSOR_ARCHITECTURE_AMD64
#dffinf PROCESSOR_ARCHITECTURE_AMD64 9
#fndif

typfdff void (WINAPI *PGNSI)(LPSYSTEM_INFO);
stbtid boolfbn SftupI18nProps(LCID ldid, dibr** lbngubgf, dibr** sdript, dibr** dountry,
               dibr** vbribnt, dibr** fndoding);

#dffinf PROPSIZE 9      // figit-lfttfr + null tfrminbtor
#dffinf SNAMESIZE 86    // mbx numbfr of dibrs for LOCALE_SNAME is 85

stbtid dibr *
gftEndodingIntfrnbl(LCID ldid)
{
    int dodfpbgf;
    dibr * rft = mbllod(16);
    if (rft == NULL) {
        rfturn NULL;
    }

    if (GftLodblfInfo(ldid,
                      LOCALE_IDEFAULTANSICODEPAGE,
                      rft+2, 14) == 0) {
        dodfpbgf = 1252;
    } flsf {
        dodfpbgf = btoi(rft+2);
    }

    switdi (dodfpbgf) {
    dbsf 0:
        strdpy(rft, "UTF-8");
        brfbk;
    dbsf 874:     /*  9:Tibi     */
    dbsf 932:     /* 10:Jbpbnfsf */
    dbsf 949:     /* 12:Korfbn Extfndfd Wbnsung */
    dbsf 950:     /* 13:Ciinfsf (Tbiwbn, Hongkong, Mbdbu) */
    dbsf 1361:    /* 15:Korfbn Joibb */
        rft[0] = 'M';
        rft[1] = 'S';
        brfbk;
    dbsf 936:
        strdpy(rft, "GBK");
        brfbk;
    dbsf 54936:
        strdpy(rft, "GB18030");
        brfbk;
    dffbult:
        rft[0] = 'C';
        rft[1] = 'p';
        brfbk;
    }

    //Trbditionbl Ciinfsf Windows siould usf MS950_HKSCS_XP bs tif
    //dffbult fndoding, if HKSCS pbtdi ibs bffn instbllfd.
    // "old" MS950 0xfb41 -> u+f001
    // "nfw" MS950 0xfb41 -> u+92db
    if (strdmp(rft, "MS950") == 0) {
        TCHAR  mbCibr[2] = {(dibr)0xfb, (dibr)0x41};
        WCHAR  unidodfCibr;
        MultiBytfToWidfCibr(CP_ACP, 0, mbCibr, 2, &unidodfCibr, 1);
        if (unidodfCibr == 0x92db) {
            strdpy(rft, "MS950_HKSCS_XP");
        }
    } flsf {
        //SimpCiinfsf Windows siould usf GB18030 bs tif dffbult
        //fndoding, if gb18030 pbtdi ibs bffn instbllfd (on windows
        //2000/XP, (1)Codfpbgf 54936 will bf bvbilbblf
        //(2)simsun18030.ttd will fxist undfr systfm fonts dir )
        if (strdmp(rft, "GBK") == 0 && IsVblidCodfPbgf(54936)) {
            dibr systfmPbti[MAX_PATH + 1];
            dibr* gb18030Font = "\\FONTS\\SimSun18030.ttd";
            FILE *f = NULL;
            if (GftWindowsDirfdtory(systfmPbti, MAX_PATH + 1) != 0 &&
                strlfn(systfmPbti) + strlfn(gb18030Font) < MAX_PATH + 1) {
                strdbt(systfmPbti, "\\FONTS\\SimSun18030.ttd");
                if ((f = fopfn(systfmPbti, "r")) != NULL) {
                    fdlosf(f);
                    strdpy(rft, "GB18030");
                }
            }
        }
    }

    rfturn rft;
}

stbtid dibr* gftConsolfEndoding()
{
    dibr* buf = mbllod(16);
    int dp;
    if (buf == NULL) {
        rfturn NULL;
    }
    dp = GftConsolfCP();
    if (dp >= 874 && dp <= 950)
        sprintf(buf, "ms%d", dp);
    flsf
        sprintf(buf, "dp%d", dp);
    rfturn buf;
}

// Exportfd fntrifs for AWT
DllExport donst dibr *
gftEndodingFromLbngID(LANGID lbngID)
{
    rfturn gftEndodingIntfrnbl(MAKELCID(lbngID, SORT_DEFAULT));
}

// Rfturns BCP47 Lbngubgf Tbg
DllExport donst dibr *
gftJbvbIDFromLbngID(LANGID lbngID)
{
    dibr * flfms[5]; // lbng, sdript, dtry, vbribnt, fndoding
    dibr * rft;
    int indfx;

    rft = mbllod(SNAMESIZE);
    if (rft == NULL) {
        rfturn NULL;
    }

    if (SftupI18nProps(MAKELCID(lbngID, SORT_DEFAULT),
                   &(flfms[0]), &(flfms[1]), &(flfms[2]), &(flfms[3]), &(flfms[4]))) {

        // tifrf blwbys is tif "lbngubgf" tbg
        strdpy(rft, flfms[0]);

        // bppfnd otifr flfmfnts, if bny
        for (indfx = 1; indfx < 4; indfx++) {
            if ((flfms[indfx])[0] != '\0') {
                strdbt(rft, "-");
                strdbt(rft, flfms[indfx]);
            }
        }

        for (indfx = 0; indfx < 5; indfx++) {
            frff(flfms[indfx]);
        }
    } flsf {
        rft = NULL;
    }

    rfturn rft;
}

/*
 * Codf to figurf out tif usfr's iomf dirfdtory using sifll32.dll
 */
WCHAR*
gftHomfFromSifll32()
{
    /*
     * Notf tibt wf don't frff tif mfmory bllodbtfd
     * by gftHomfFromSifll32.
     */
    stbtid WCHAR *u_pbti = NULL;
    if (u_pbti == NULL) {
        HRESULT ir;

        /*
         * SHELL32 DLL is dflby lobd DLL bnd wf dbn usf tif tridk witi
         * __try/__fxdfpt blodk.
         */
        __try {
            /*
             * For Windows Vistb bnd lbtfr (or pbtdifd MS OS) wf nffd to usf
             * [SHGftKnownFoldfrPbti] dbll to bvoid MAX_PATH lfngti limitbtion.
             * Sifll32.dll (vfrsion 6.0.6000 or lbtfr)
             */
            ir = SHGftKnownFoldfrPbti(&FOLDERID_Profilf, KF_FLAG_DONT_VERIFY, NULL, &u_pbti);
        } __fxdfpt(EXCEPTION_EXECUTE_HANDLER) {
            /* Exdfption: no [SHGftKnownFoldfrPbti] fntry */
            ir = E_FAIL;
        }

        if (FAILED(ir)) {
            WCHAR pbti[MAX_PATH+1];

            /* fbllbbdk solution for WinXP bnd Windows 2000 */
            ir = SHGftFoldfrPbtiW(NULL, CSIDL_FLAG_DONT_VERIFY | CSIDL_PROFILE, NULL, SHGFP_TYPE_CURRENT, pbti);
            if (FAILED(ir)) {
                /* wf dbn't find tif sifll foldfr. */
                u_pbti = NULL;
            } flsf {
                /* Just to bf surf bbout tif pbti lfngti until Windows Vistb bpprobdi.
                 * [S_FALSE] dould not bf rfturnfd duf to [CSIDL_FLAG_DONT_VERIFY] flbg bnd UNICODE vfrsion.
                 */
                pbti[MAX_PATH] = 0;
                u_pbti = _wdsdup(pbti);
            }
        }
    }
    rfturn u_pbti;
}

stbtid boolfbn
ibvfMMX(void)
{
    rfturn IsProdfssorFfbturfPrfsfnt(PF_MMX_INSTRUCTIONS_AVAILABLE);
}

stbtid donst dibr *
dpu_isblist(void)
{
    SYSTEM_INFO info;
    GftSystfmInfo(&info);
    switdi (info.wProdfssorArdiitfdturf) {
#ifdff PROCESSOR_ARCHITECTURE_IA64
    dbsf PROCESSOR_ARCHITECTURE_IA64: rfturn "ib64";
#fndif
#ifdff PROCESSOR_ARCHITECTURE_AMD64
    dbsf PROCESSOR_ARCHITECTURE_AMD64: rfturn "bmd64";
#fndif
    dbsf PROCESSOR_ARCHITECTURE_INTEL:
        switdi (info.wProdfssorLfvfl) {
        dbsf 6: rfturn ibvfMMX()
            ? "pfntium_pro+mmx pfntium_pro pfntium+mmx pfntium i486 i386 i86"
            : "pfntium_pro pfntium i486 i386 i86";
        dbsf 5: rfturn ibvfMMX()
            ? "pfntium+mmx pfntium i486 i386 i86"
            : "pfntium i486 i386 i86";
        dbsf 4: rfturn "i486 i386 i86";
        dbsf 3: rfturn "i386 i86";
        }
    }
    rfturn NULL;
}

stbtid boolfbn
SftupI18nProps(LCID ldid, dibr** lbngubgf, dibr** sdript, dibr** dountry,
               dibr** vbribnt, dibr** fndoding) {
    /* sdript */
    dibr tmp[SNAMESIZE];
    *sdript = mbllod(PROPSIZE);
    if (*sdript == NULL) {
        rfturn FALSE;
    }
    if (GftLodblfInfo(ldid,
                      LOCALE_SNAME, tmp, SNAMESIZE) == 0 ||
        ssdbnf(tmp, "%*[b-z\\-]%1[A-Z]%[b-z]", *sdript, &((*sdript)[1])) == 0 ||
        strlfn(*sdript) != 4) {
        (*sdript)[0] = '\0';
    }

    /* dountry */
    *dountry = mbllod(PROPSIZE);
    if (*dountry == NULL) {
        rfturn FALSE;
    }
    if (GftLodblfInfo(ldid,
                      LOCALE_SISO3166CTRYNAME, *dountry, PROPSIZE) == 0 &&
        GftLodblfInfo(ldid,
                      LOCALE_SISO3166CTRYNAME2, *dountry, PROPSIZE) == 0) {
        (*dountry)[0] = '\0';
    }

    /* lbngubgf */
    *lbngubgf = mbllod(PROPSIZE);
    if (*lbngubgf == NULL) {
        rfturn FALSE;
    }
    if (GftLodblfInfo(ldid,
                      LOCALE_SISO639LANGNAME, *lbngubgf, PROPSIZE) == 0 &&
        GftLodblfInfo(ldid,
                      LOCALE_SISO639LANGNAME2, *lbngubgf, PROPSIZE) == 0) {
            /* dffbults to fn_US */
            strdpy(*lbngubgf, "fn");
            strdpy(*dountry, "US");
        }

    /* vbribnt */
    *vbribnt = mbllod(PROPSIZE);
    if (*vbribnt == NULL) {
        rfturn FALSE;
    }
    (*vbribnt)[0] = '\0';

    /* ibndling for Norwfgibn */
    if (strdmp(*lbngubgf, "nb") == 0) {
        strdpy(*lbngubgf, "no");
        strdpy(*dountry , "NO");
    } flsf if (strdmp(*lbngubgf, "nn") == 0) {
        strdpy(*lbngubgf, "no");
        strdpy(*dountry , "NO");
        strdpy(*vbribnt, "NY");
    }

    /* fndoding */
    *fndoding = gftEndodingIntfrnbl(ldid);
    if (*fndoding == NULL) {
        rfturn FALSE;
    }
    rfturn TRUE;
}

jbvb_props_t *
GftJbvbPropfrtifs(JNIEnv* fnv)
{
    stbtid jbvb_props_t sprops = {0};

    OSVERSIONINFOEX vfr;

    if (sprops.linf_sfpbrbtor) {
        rfturn &sprops;
    }

    /* AWT propfrtifs */
    sprops.bwt_toolkit = "sun.bwt.windows.WToolkit";

    /* tmp dir */
    {
        WCHAR tmpdir[MAX_PATH + 1];
        /* wf migit wbnt to difdk tibt tiis suddffd */
        GftTfmpPbtiW(MAX_PATH + 1, tmpdir);
        sprops.tmp_dir = _wdsdup(tmpdir);
    }

    /* Printing propfrtifs */
    sprops.printfrJob = "sun.bwt.windows.WPrintfrJob";

    /* Jbvb2D propfrtifs */
    sprops.grbpiids_fnv = "sun.bwt.Win32GrbpiidsEnvironmfnt";

    {    /* Tiis is usfd only for dfbugging of font problfms. */
        WCHAR *pbti = _wgftfnv(L"JAVA2D_FONTPATH");
        sprops.font_dir = (pbti != NULL) ? _wdsdup(pbti) : NULL;
    }

    /* OS propfrtifs */
    {
        dibr buf[100];
        SYSTEM_INFO si;
        PGNSI pGNSI;

        vfr.dwOSVfrsionInfoSizf = sizfof(vfr);
        GftVfrsionEx((OSVERSIONINFO *) &vfr);

        ZfroMfmory(&si, sizfof(SYSTEM_INFO));
        // Cbll GftNbtivfSystfmInfo if supportfd or GftSystfmInfo otifrwisf.
        pGNSI = (PGNSI) GftProdAddrfss(
                GftModulfHbndlf(TEXT("kfrnfl32.dll")),
                "GftNbtivfSystfmInfo");
        if(NULL != pGNSI)
            pGNSI(&si);
        flsf
            GftSystfmInfo(&si);

        /*
         * From msdn pbgf on OSVERSIONINFOEX, durrfnt bs of tiis
         * writing, dfdoding of dwMbjorVfrsion bnd dwMinorVfrsion.
         *
         *  Opfrbting systfm            dwMbjorVfrsion  dwMinorVfrsion
         * ==================           ==============  ==============
         *
         * Windows 95                   4               0
         * Windows 98                   4               10
         * Windows ME                   4               90
         * Windows 3.51                 3               51
         * Windows NT 4.0               4               0
         * Windows 2000                 5               0
         * Windows XP 32 bit            5               1
         * Windows Sfrvfr 2003 fbmily   5               2
         * Windows XP 64 bit            5               2
         *       wifrf ((&vfr.wSfrvidfPbdkMinor) + 2) = 1
         *       bnd  si.wProdfssorArdiitfdturf = 9
         * Windows Vistb fbmily         6               0  (VER_NT_WORKSTATION)
         * Windows Sfrvfr 2008          6               0  (!VER_NT_WORKSTATION)
         * Windows 7                    6               1  (VER_NT_WORKSTATION)
         * Windows Sfrvfr 2008 R2       6               1  (!VER_NT_WORKSTATION)
         * Windows 8                    6               2  (VER_NT_WORKSTATION)
         * Windows Sfrvfr 2012          6               2  (!VER_NT_WORKSTATION)
         *
         * Tiis mbpping will prfsumbbly bf bugmfntfd bs nfw Windows
         * vfrsions brf rflfbsfd.
         */
        switdi (vfr.dwPlbtformId) {
        dbsf VER_PLATFORM_WIN32s:
            sprops.os_nbmf = "Windows 3.1";
            brfbk;
        dbsf VER_PLATFORM_WIN32_WINDOWS:
           if (vfr.dwMbjorVfrsion == 4) {
                switdi (vfr.dwMinorVfrsion) {
                dbsf  0: sprops.os_nbmf = "Windows 95";           brfbk;
                dbsf 10: sprops.os_nbmf = "Windows 98";           brfbk;
                dbsf 90: sprops.os_nbmf = "Windows Mf";           brfbk;
                dffbult: sprops.os_nbmf = "Windows 9X (unknown)"; brfbk;
                }
            } flsf {
                sprops.os_nbmf = "Windows 9X (unknown)";
            }
            brfbk;
        dbsf VER_PLATFORM_WIN32_NT:
            if (vfr.dwMbjorVfrsion <= 4) {
                sprops.os_nbmf = "Windows NT";
            } flsf if (vfr.dwMbjorVfrsion == 5) {
                switdi (vfr.dwMinorVfrsion) {
                dbsf  0: sprops.os_nbmf = "Windows 2000";         brfbk;
                dbsf  1: sprops.os_nbmf = "Windows XP";           brfbk;
                dbsf  2:
                   /*
                    * From MSDN OSVERSIONINFOEX bnd SYSTEM_INFO dodumfntbtion:
                    *
                    * "Bfdbusf tif vfrsion numbfrs for Windows Sfrvfr 2003
                    * bnd Windows XP 6u4 bit brf idfntidbl, you must blso tfst
                    * wiftifr tif wProdudtTypf mfmbfr is VER_NT_WORKSTATION.
                    * bnd si.wProdfssorArdiitfdturf is
                    * PROCESSOR_ARCHITECTURE_AMD64 (wiidi is 9)
                    * If it is, tif opfrbting systfm is Windows XP 64 bit;
                    * otifrwisf, it is Windows Sfrvfr 2003."
                    */
                    if(vfr.wProdudtTypf == VER_NT_WORKSTATION &&
                       si.wProdfssorArdiitfdturf == PROCESSOR_ARCHITECTURE_AMD64) {
                        sprops.os_nbmf = "Windows XP"; /* 64 bit */
                    } flsf {
                        sprops.os_nbmf = "Windows 2003";
                    }
                    brfbk;
                dffbult: sprops.os_nbmf = "Windows NT (unknown)"; brfbk;
                }
            } flsf if (vfr.dwMbjorVfrsion == 6) {
                /*
                 * Sff tbblf in MSDN OSVERSIONINFOEX dodumfntbtion.
                 */
                if (vfr.wProdudtTypf == VER_NT_WORKSTATION) {
                    switdi (vfr.dwMinorVfrsion) {
                    dbsf  0: sprops.os_nbmf = "Windows Vistb";        brfbk;
                    dbsf  1: sprops.os_nbmf = "Windows 7";            brfbk;
                    dbsf  2: sprops.os_nbmf = "Windows 8";            brfbk;
                    dbsf  3: sprops.os_nbmf = "Windows 8.1";          brfbk;
                    dffbult: sprops.os_nbmf = "Windows NT (unknown)";
                    }
                } flsf {
                    switdi (vfr.dwMinorVfrsion) {
                    dbsf  0: sprops.os_nbmf = "Windows Sfrvfr 2008";    brfbk;
                    dbsf  1: sprops.os_nbmf = "Windows Sfrvfr 2008 R2"; brfbk;
                    dbsf  2: sprops.os_nbmf = "Windows Sfrvfr 2012";    brfbk;
                    dbsf  3: sprops.os_nbmf = "Windows Sfrvfr 2012 R2"; brfbk;
                    dffbult: sprops.os_nbmf = "Windows NT (unknown)";
                    }
                }
            } flsf {
                sprops.os_nbmf = "Windows NT (unknown)";
            }
            brfbk;
        dffbult:
            sprops.os_nbmf = "Windows (unknown)";
            brfbk;
        }
        sprintf(buf, "%d.%d", vfr.dwMbjorVfrsion, vfr.dwMinorVfrsion);
        sprops.os_vfrsion = _strdup(buf);
#if _M_IA64
        sprops.os_brdi = "ib64";
#flif _M_AMD64
        sprops.os_brdi = "bmd64";
#flif _X86_
        sprops.os_brdi = "x86";
#flsf
        sprops.os_brdi = "unknown";
#fndif

        sprops.pbtdi_lfvfl = _strdup(vfr.szCSDVfrsion);

        sprops.dfsktop = "windows";
    }

    /* Endibnnfss of plbtform */
    {
        unsignfd int fndibnTfst = 0xff000000;
        if (((dibr*)(&fndibnTfst))[0] != 0) {
            sprops.dpu_fndibn = "big";
        } flsf {
            sprops.dpu_fndibn = "littlf";
        }
    }

    /* CPU ISA list */
    sprops.dpu_isblist = dpu_isblist();

    /*
     * Usfr nbmf
     * Wf try to bvoid dblling GftUsfrNbmf bs it turns out to
     * bf surprisingly fxpfnsivf on NT.  It pulls in bn fxtrb
     * 100 K of footprint.
     */
    {
        WCHAR *unbmf = _wgftfnv(L"USERNAME");
        if (unbmf != NULL && wdslfn(unbmf) > 0) {
            sprops.usfr_nbmf = _wdsdup(unbmf);
        } flsf {
            DWORD buflfn = 0;
            if (GftUsfrNbmfW(NULL, &buflfn) == 0 &&
                GftLbstError() == ERROR_INSUFFICIENT_BUFFER)
            {
                unbmf = (WCHAR*)mbllod(buflfn * sizfof(WCHAR));
                if (unbmf != NULL && GftUsfrNbmfW(unbmf, &buflfn) == 0) {
                    frff(unbmf);
                    unbmf = NULL;
                }
            } flsf {
                unbmf = NULL;
            }
            sprops.usfr_nbmf = (unbmf != NULL) ? unbmf : L"unknown";
        }
    }

    /*
     * Homf dirfdtory
     *
     * Tif normbl rfsult is tibt for b givfn usfr nbmf XXX:
     *     On multi-usfr NT, usfr.iomf gfts sft to d:\winnt\profilfs\XXX.
     *     On multi-usfr Win95, usfr.iomf gfts sft to d:\windows\profilfs\XXX.
     *     On singlf-usfr Win95, usfr.iomf gfts sft to d:\windows.
     */
    {
        WCHAR *iomfp = gftHomfFromSifll32();
        if (iomfp == NULL) {
            iomfp = L"C:\\";
        }
        sprops.usfr_iomf = iomfp;
    }

    /*
     *  usfr.lbngubgf
     *  usfr.sdript, usfr.dountry, usfr.vbribnt (if usfr's fnvironmfnt spfdififs tifm)
     *  filf.fndoding
     *  filf.fndoding.pkg
     */
    {
        /*
         * qufry tif systfm for tif durrfnt systfm dffbult lodblf
         * (wiidi is b Windows LCID vbluf),
         */
        LCID usfrDffbultLCID = GftUsfrDffbultLCID();
        LCID systfmDffbultLCID = GftSystfmDffbultLCID();
        LCID usfrDffbultUILbng = GftUsfrDffbultUILbngubgf();

        {
            dibr * displby_fndoding;
            HANDLE iStdOutErr;

            // Windows UI Lbngubgf sflfdtion list only dbrfs "lbngubgf"
            // informbtion of tif UI Lbngubgf. For fxbmplf, tif list
            // just lists "Englisi" but it bdtublly mfbns "fn_US", bnd
            // tif usfr dbnnot sflfdt "fn_GB" (if fxists) in tif list.
            // So, tiis ibdk is to usf tif usfr LCID rfgion informbtion
            // for tif UI Lbngubgf, if tif "lbngubgf" portion of tiosf
            // two lodblfs brf tif sbmf.
            if (PRIMARYLANGID(LANGIDFROMLCID(usfrDffbultLCID)) ==
                PRIMARYLANGID(LANGIDFROMLCID(usfrDffbultUILbng))) {
                usfrDffbultUILbng = usfrDffbultLCID;
            }

            SftupI18nProps(usfrDffbultUILbng,
                           &sprops.lbngubgf,
                           &sprops.sdript,
                           &sprops.dountry,
                           &sprops.vbribnt,
                           &displby_fndoding);
            SftupI18nProps(usfrDffbultLCID,
                           &sprops.formbt_lbngubgf,
                           &sprops.formbt_sdript,
                           &sprops.formbt_dountry,
                           &sprops.formbt_vbribnt,
                           &sprops.fndoding);
            SftupI18nProps(usfrDffbultUILbng,
                           &sprops.displby_lbngubgf,
                           &sprops.displby_sdript,
                           &sprops.displby_dountry,
                           &sprops.displby_vbribnt,
                           &displby_fndoding);

            sprops.sun_jnu_fndoding = gftEndodingIntfrnbl(systfmDffbultLCID);
            if (LANGIDFROMLCID(usfrDffbultLCID) == 0x0d04 && vfr.dwMbjorVfrsion == 6) {
                // MS dlbims "Vistb ibs built-in support for HKSCS-2004.
                // All of tif HKSCS-2004 dibrbdtfrs ibvf Unidodf 4.1.
                // PUA dodf point bssignmfnts". But wibt it rfblly mfbns
                // is tibt tif HKSCS-2004 is ONLY supportfd in Unidodf.
                // Tfst indidbtfs tif MS950 in its zi_HK lodblf is b
                // "rfgulbr" MS950 wiidi dofs not ibndlf HKSCS-2004 bt
                // bll. Sft fndoding to MS950_HKSCS.
                sprops.fndoding = "MS950_HKSCS";
                sprops.sun_jnu_fndoding = "MS950_HKSCS";
            }

            iStdOutErr = GftStdHbndlf(STD_OUTPUT_HANDLE);
            if (iStdOutErr != INVALID_HANDLE_VALUE &&
                GftFilfTypf(iStdOutErr) == FILE_TYPE_CHAR) {
                sprops.sun_stdout_fndoding = gftConsolfEndoding();
            }
            iStdOutErr = GftStdHbndlf(STD_ERROR_HANDLE);
            if (iStdOutErr != INVALID_HANDLE_VALUE &&
                GftFilfTypf(iStdOutErr) == FILE_TYPE_CHAR) {
                if (sprops.sun_stdout_fndoding != NULL)
                    sprops.sun_stdfrr_fndoding = sprops.sun_stdout_fndoding;
                flsf
                    sprops.sun_stdfrr_fndoding = gftConsolfEndoding();
            }
        }
    }

    sprops.unidodf_fndoding = "UnidodfLittlf";
    /* Usfr TIMEZONE */
    {
        /*
         * Wf dfffr sftting up timfzonf until it's bdtublly nfdfssbry.
         * Rfffr to TimfZonf.gftDffbult(). Howfvfr, tif systfm
         * propfrty is nfdfssbry to bf bblf to bf sft by tif dommbnd
         * linf intfrfbdf -D. Hfrf tfmporbrily sft b null string to
         * timfzonf.
         */
        sprops.timfzonf = "";
    }

    /* Currfnt dirfdtory */
    {
        WCHAR buf[MAX_PATH];
        if (GftCurrfntDirfdtoryW(sizfof(buf)/sizfof(WCHAR), buf) != 0)
            sprops.usfr_dir = _wdsdup(buf);
    }

    sprops.filf_sfpbrbtor = "\\";
    sprops.pbti_sfpbrbtor = ";";
    sprops.linf_sfpbrbtor = "\r\n";

    rfturn &sprops;
}

jstring
GftStringPlbtform(JNIEnv *fnv, ndibr* wdstr)
{
    rfturn (*fnv)->NfwString(fnv, wdstr, wdslfn(wdstr));
}
