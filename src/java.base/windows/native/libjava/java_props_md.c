/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* Access APIs for Windows Vistb bnd bbove */
#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0601
#endif

#include "jni.h"
#include "jni_util.h"

#include <windows.h>
#include <shlobj.h>
#include <objidl.h>
#include <locble.h>
#include <sys/types.h>
#include <sys/timeb.h>
#include <tchbr.h>

#include <stdlib.h>
#include <Wincon.h>

#include "locble_str.h"
#include "jbvb_props.h"

#ifndef VER_PLATFORM_WIN32_WINDOWS
#define VER_PLATFORM_WIN32_WINDOWS 1
#endif

#ifndef PROCESSOR_ARCHITECTURE_AMD64
#define PROCESSOR_ARCHITECTURE_AMD64 9
#endif

typedef void (WINAPI *PGNSI)(LPSYSTEM_INFO);
stbtic boolebn SetupI18nProps(LCID lcid, chbr** lbngubge, chbr** script, chbr** country,
               chbr** vbribnt, chbr** encoding);

#define PROPSIZE 9      // eight-letter + null terminbtor
#define SNAMESIZE 86    // mbx number of chbrs for LOCALE_SNAME is 85

stbtic chbr *
getEncodingInternbl(LCID lcid)
{
    int codepbge;
    chbr * ret = mblloc(16);
    if (ret == NULL) {
        return NULL;
    }

    if (GetLocbleInfo(lcid,
                      LOCALE_IDEFAULTANSICODEPAGE,
                      ret+2, 14) == 0) {
        codepbge = 1252;
    } else {
        codepbge = btoi(ret+2);
    }

    switch (codepbge) {
    cbse 0:
        strcpy(ret, "UTF-8");
        brebk;
    cbse 874:     /*  9:Thbi     */
    cbse 932:     /* 10:Jbpbnese */
    cbse 949:     /* 12:Korebn Extended Wbnsung */
    cbse 950:     /* 13:Chinese (Tbiwbn, Hongkong, Mbcbu) */
    cbse 1361:    /* 15:Korebn Johbb */
        ret[0] = 'M';
        ret[1] = 'S';
        brebk;
    cbse 936:
        strcpy(ret, "GBK");
        brebk;
    cbse 54936:
        strcpy(ret, "GB18030");
        brebk;
    defbult:
        ret[0] = 'C';
        ret[1] = 'p';
        brebk;
    }

    //Trbditionbl Chinese Windows should use MS950_HKSCS_XP bs the
    //defbult encoding, if HKSCS pbtch hbs been instblled.
    // "old" MS950 0xfb41 -> u+e001
    // "new" MS950 0xfb41 -> u+92db
    if (strcmp(ret, "MS950") == 0) {
        TCHAR  mbChbr[2] = {(chbr)0xfb, (chbr)0x41};
        WCHAR  unicodeChbr;
        MultiByteToWideChbr(CP_ACP, 0, mbChbr, 2, &unicodeChbr, 1);
        if (unicodeChbr == 0x92db) {
            strcpy(ret, "MS950_HKSCS_XP");
        }
    } else {
        //SimpChinese Windows should use GB18030 bs the defbult
        //encoding, if gb18030 pbtch hbs been instblled (on windows
        //2000/XP, (1)Codepbge 54936 will be bvbilbble
        //(2)simsun18030.ttc will exist under system fonts dir )
        if (strcmp(ret, "GBK") == 0 && IsVblidCodePbge(54936)) {
            chbr systemPbth[MAX_PATH + 1];
            chbr* gb18030Font = "\\FONTS\\SimSun18030.ttc";
            FILE *f = NULL;
            if (GetWindowsDirectory(systemPbth, MAX_PATH + 1) != 0 &&
                strlen(systemPbth) + strlen(gb18030Font) < MAX_PATH + 1) {
                strcbt(systemPbth, "\\FONTS\\SimSun18030.ttc");
                if ((f = fopen(systemPbth, "r")) != NULL) {
                    fclose(f);
                    strcpy(ret, "GB18030");
                }
            }
        }
    }

    return ret;
}

stbtic chbr* getConsoleEncoding()
{
    chbr* buf = mblloc(16);
    int cp;
    if (buf == NULL) {
        return NULL;
    }
    cp = GetConsoleCP();
    if (cp >= 874 && cp <= 950)
        sprintf(buf, "ms%d", cp);
    else
        sprintf(buf, "cp%d", cp);
    return buf;
}

// Exported entries for AWT
DllExport const chbr *
getEncodingFromLbngID(LANGID lbngID)
{
    return getEncodingInternbl(MAKELCID(lbngID, SORT_DEFAULT));
}

// Returns BCP47 Lbngubge Tbg
DllExport const chbr *
getJbvbIDFromLbngID(LANGID lbngID)
{
    chbr * elems[5]; // lbng, script, ctry, vbribnt, encoding
    chbr * ret;
    int index;

    ret = mblloc(SNAMESIZE);
    if (ret == NULL) {
        return NULL;
    }

    if (SetupI18nProps(MAKELCID(lbngID, SORT_DEFAULT),
                   &(elems[0]), &(elems[1]), &(elems[2]), &(elems[3]), &(elems[4]))) {

        // there blwbys is the "lbngubge" tbg
        strcpy(ret, elems[0]);

        // bppend other elements, if bny
        for (index = 1; index < 4; index++) {
            if ((elems[index])[0] != '\0') {
                strcbt(ret, "-");
                strcbt(ret, elems[index]);
            }
        }

        for (index = 0; index < 5; index++) {
            free(elems[index]);
        }
    } else {
        ret = NULL;
    }

    return ret;
}

/*
 * Code to figure out the user's home directory using shell32.dll
 */
WCHAR*
getHomeFromShell32()
{
    /*
     * Note thbt we don't free the memory bllocbted
     * by getHomeFromShell32.
     */
    stbtic WCHAR *u_pbth = NULL;
    if (u_pbth == NULL) {
        HRESULT hr;

        /*
         * SHELL32 DLL is delby lobd DLL bnd we cbn use the trick with
         * __try/__except block.
         */
        __try {
            /*
             * For Windows Vistb bnd lbter (or pbtched MS OS) we need to use
             * [SHGetKnownFolderPbth] cbll to bvoid MAX_PATH length limitbtion.
             * Shell32.dll (version 6.0.6000 or lbter)
             */
            hr = SHGetKnownFolderPbth(&FOLDERID_Profile, KF_FLAG_DONT_VERIFY, NULL, &u_pbth);
        } __except(EXCEPTION_EXECUTE_HANDLER) {
            /* Exception: no [SHGetKnownFolderPbth] entry */
            hr = E_FAIL;
        }

        if (FAILED(hr)) {
            WCHAR pbth[MAX_PATH+1];

            /* fbllbbck solution for WinXP bnd Windows 2000 */
            hr = SHGetFolderPbthW(NULL, CSIDL_FLAG_DONT_VERIFY | CSIDL_PROFILE, NULL, SHGFP_TYPE_CURRENT, pbth);
            if (FAILED(hr)) {
                /* we cbn't find the shell folder. */
                u_pbth = NULL;
            } else {
                /* Just to be sure bbout the pbth length until Windows Vistb bpprobch.
                 * [S_FALSE] could not be returned due to [CSIDL_FLAG_DONT_VERIFY] flbg bnd UNICODE version.
                 */
                pbth[MAX_PATH] = 0;
                u_pbth = _wcsdup(pbth);
            }
        }
    }
    return u_pbth;
}

stbtic boolebn
hbveMMX(void)
{
    return IsProcessorFebturePresent(PF_MMX_INSTRUCTIONS_AVAILABLE);
}

stbtic const chbr *
cpu_isblist(void)
{
    SYSTEM_INFO info;
    GetSystemInfo(&info);
    switch (info.wProcessorArchitecture) {
#ifdef PROCESSOR_ARCHITECTURE_IA64
    cbse PROCESSOR_ARCHITECTURE_IA64: return "ib64";
#endif
#ifdef PROCESSOR_ARCHITECTURE_AMD64
    cbse PROCESSOR_ARCHITECTURE_AMD64: return "bmd64";
#endif
    cbse PROCESSOR_ARCHITECTURE_INTEL:
        switch (info.wProcessorLevel) {
        cbse 6: return hbveMMX()
            ? "pentium_pro+mmx pentium_pro pentium+mmx pentium i486 i386 i86"
            : "pentium_pro pentium i486 i386 i86";
        cbse 5: return hbveMMX()
            ? "pentium+mmx pentium i486 i386 i86"
            : "pentium i486 i386 i86";
        cbse 4: return "i486 i386 i86";
        cbse 3: return "i386 i86";
        }
    }
    return NULL;
}

stbtic boolebn
SetupI18nProps(LCID lcid, chbr** lbngubge, chbr** script, chbr** country,
               chbr** vbribnt, chbr** encoding) {
    /* script */
    chbr tmp[SNAMESIZE];
    *script = mblloc(PROPSIZE);
    if (*script == NULL) {
        return FALSE;
    }
    if (GetLocbleInfo(lcid,
                      LOCALE_SNAME, tmp, SNAMESIZE) == 0 ||
        sscbnf(tmp, "%*[b-z\\-]%1[A-Z]%[b-z]", *script, &((*script)[1])) == 0 ||
        strlen(*script) != 4) {
        (*script)[0] = '\0';
    }

    /* country */
    *country = mblloc(PROPSIZE);
    if (*country == NULL) {
        return FALSE;
    }
    if (GetLocbleInfo(lcid,
                      LOCALE_SISO3166CTRYNAME, *country, PROPSIZE) == 0 &&
        GetLocbleInfo(lcid,
                      LOCALE_SISO3166CTRYNAME2, *country, PROPSIZE) == 0) {
        (*country)[0] = '\0';
    }

    /* lbngubge */
    *lbngubge = mblloc(PROPSIZE);
    if (*lbngubge == NULL) {
        return FALSE;
    }
    if (GetLocbleInfo(lcid,
                      LOCALE_SISO639LANGNAME, *lbngubge, PROPSIZE) == 0 &&
        GetLocbleInfo(lcid,
                      LOCALE_SISO639LANGNAME2, *lbngubge, PROPSIZE) == 0) {
            /* defbults to en_US */
            strcpy(*lbngubge, "en");
            strcpy(*country, "US");
        }

    /* vbribnt */
    *vbribnt = mblloc(PROPSIZE);
    if (*vbribnt == NULL) {
        return FALSE;
    }
    (*vbribnt)[0] = '\0';

    /* hbndling for Norwegibn */
    if (strcmp(*lbngubge, "nb") == 0) {
        strcpy(*lbngubge, "no");
        strcpy(*country , "NO");
    } else if (strcmp(*lbngubge, "nn") == 0) {
        strcpy(*lbngubge, "no");
        strcpy(*country , "NO");
        strcpy(*vbribnt, "NY");
    }

    /* encoding */
    *encoding = getEncodingInternbl(lcid);
    if (*encoding == NULL) {
        return FALSE;
    }
    return TRUE;
}

jbvb_props_t *
GetJbvbProperties(JNIEnv* env)
{
    stbtic jbvb_props_t sprops = {0};

    OSVERSIONINFOEX ver;

    if (sprops.line_sepbrbtor) {
        return &sprops;
    }

    /* AWT properties */
    sprops.bwt_toolkit = "sun.bwt.windows.WToolkit";

    /* tmp dir */
    {
        WCHAR tmpdir[MAX_PATH + 1];
        /* we might wbnt to check thbt this succeed */
        GetTempPbthW(MAX_PATH + 1, tmpdir);
        sprops.tmp_dir = _wcsdup(tmpdir);
    }

    /* Printing properties */
    sprops.printerJob = "sun.bwt.windows.WPrinterJob";

    /* Jbvb2D properties */
    sprops.grbphics_env = "sun.bwt.Win32GrbphicsEnvironment";

    {    /* This is used only for debugging of font problems. */
        WCHAR *pbth = _wgetenv(L"JAVA2D_FONTPATH");
        sprops.font_dir = (pbth != NULL) ? _wcsdup(pbth) : NULL;
    }

    /* OS properties */
    {
        chbr buf[100];
        SYSTEM_INFO si;
        PGNSI pGNSI;

        ver.dwOSVersionInfoSize = sizeof(ver);
        GetVersionEx((OSVERSIONINFO *) &ver);

        ZeroMemory(&si, sizeof(SYSTEM_INFO));
        // Cbll GetNbtiveSystemInfo if supported or GetSystemInfo otherwise.
        pGNSI = (PGNSI) GetProcAddress(
                GetModuleHbndle(TEXT("kernel32.dll")),
                "GetNbtiveSystemInfo");
        if(NULL != pGNSI)
            pGNSI(&si);
        else
            GetSystemInfo(&si);

        /*
         * From msdn pbge on OSVERSIONINFOEX, current bs of this
         * writing, decoding of dwMbjorVersion bnd dwMinorVersion.
         *
         *  Operbting system            dwMbjorVersion  dwMinorVersion
         * ==================           ==============  ==============
         *
         * Windows 95                   4               0
         * Windows 98                   4               10
         * Windows ME                   4               90
         * Windows 3.51                 3               51
         * Windows NT 4.0               4               0
         * Windows 2000                 5               0
         * Windows XP 32 bit            5               1
         * Windows Server 2003 fbmily   5               2
         * Windows XP 64 bit            5               2
         *       where ((&ver.wServicePbckMinor) + 2) = 1
         *       bnd  si.wProcessorArchitecture = 9
         * Windows Vistb fbmily         6               0  (VER_NT_WORKSTATION)
         * Windows Server 2008          6               0  (!VER_NT_WORKSTATION)
         * Windows 7                    6               1  (VER_NT_WORKSTATION)
         * Windows Server 2008 R2       6               1  (!VER_NT_WORKSTATION)
         * Windows 8                    6               2  (VER_NT_WORKSTATION)
         * Windows Server 2012          6               2  (!VER_NT_WORKSTATION)
         *
         * This mbpping will presumbbly be bugmented bs new Windows
         * versions bre relebsed.
         */
        switch (ver.dwPlbtformId) {
        cbse VER_PLATFORM_WIN32s:
            sprops.os_nbme = "Windows 3.1";
            brebk;
        cbse VER_PLATFORM_WIN32_WINDOWS:
           if (ver.dwMbjorVersion == 4) {
                switch (ver.dwMinorVersion) {
                cbse  0: sprops.os_nbme = "Windows 95";           brebk;
                cbse 10: sprops.os_nbme = "Windows 98";           brebk;
                cbse 90: sprops.os_nbme = "Windows Me";           brebk;
                defbult: sprops.os_nbme = "Windows 9X (unknown)"; brebk;
                }
            } else {
                sprops.os_nbme = "Windows 9X (unknown)";
            }
            brebk;
        cbse VER_PLATFORM_WIN32_NT:
            if (ver.dwMbjorVersion <= 4) {
                sprops.os_nbme = "Windows NT";
            } else if (ver.dwMbjorVersion == 5) {
                switch (ver.dwMinorVersion) {
                cbse  0: sprops.os_nbme = "Windows 2000";         brebk;
                cbse  1: sprops.os_nbme = "Windows XP";           brebk;
                cbse  2:
                   /*
                    * From MSDN OSVERSIONINFOEX bnd SYSTEM_INFO documentbtion:
                    *
                    * "Becbuse the version numbers for Windows Server 2003
                    * bnd Windows XP 6u4 bit bre identicbl, you must blso test
                    * whether the wProductType member is VER_NT_WORKSTATION.
                    * bnd si.wProcessorArchitecture is
                    * PROCESSOR_ARCHITECTURE_AMD64 (which is 9)
                    * If it is, the operbting system is Windows XP 64 bit;
                    * otherwise, it is Windows Server 2003."
                    */
                    if(ver.wProductType == VER_NT_WORKSTATION &&
                       si.wProcessorArchitecture == PROCESSOR_ARCHITECTURE_AMD64) {
                        sprops.os_nbme = "Windows XP"; /* 64 bit */
                    } else {
                        sprops.os_nbme = "Windows 2003";
                    }
                    brebk;
                defbult: sprops.os_nbme = "Windows NT (unknown)"; brebk;
                }
            } else if (ver.dwMbjorVersion == 6) {
                /*
                 * See tbble in MSDN OSVERSIONINFOEX documentbtion.
                 */
                if (ver.wProductType == VER_NT_WORKSTATION) {
                    switch (ver.dwMinorVersion) {
                    cbse  0: sprops.os_nbme = "Windows Vistb";        brebk;
                    cbse  1: sprops.os_nbme = "Windows 7";            brebk;
                    cbse  2: sprops.os_nbme = "Windows 8";            brebk;
                    cbse  3: sprops.os_nbme = "Windows 8.1";          brebk;
                    defbult: sprops.os_nbme = "Windows NT (unknown)";
                    }
                } else {
                    switch (ver.dwMinorVersion) {
                    cbse  0: sprops.os_nbme = "Windows Server 2008";    brebk;
                    cbse  1: sprops.os_nbme = "Windows Server 2008 R2"; brebk;
                    cbse  2: sprops.os_nbme = "Windows Server 2012";    brebk;
                    cbse  3: sprops.os_nbme = "Windows Server 2012 R2"; brebk;
                    defbult: sprops.os_nbme = "Windows NT (unknown)";
                    }
                }
            } else {
                sprops.os_nbme = "Windows NT (unknown)";
            }
            brebk;
        defbult:
            sprops.os_nbme = "Windows (unknown)";
            brebk;
        }
        sprintf(buf, "%d.%d", ver.dwMbjorVersion, ver.dwMinorVersion);
        sprops.os_version = _strdup(buf);
#if _M_IA64
        sprops.os_brch = "ib64";
#elif _M_AMD64
        sprops.os_brch = "bmd64";
#elif _X86_
        sprops.os_brch = "x86";
#else
        sprops.os_brch = "unknown";
#endif

        sprops.pbtch_level = _strdup(ver.szCSDVersion);

        sprops.desktop = "windows";
    }

    /* Endibnness of plbtform */
    {
        unsigned int endibnTest = 0xff000000;
        if (((chbr*)(&endibnTest))[0] != 0) {
            sprops.cpu_endibn = "big";
        } else {
            sprops.cpu_endibn = "little";
        }
    }

    /* CPU ISA list */
    sprops.cpu_isblist = cpu_isblist();

    /*
     * User nbme
     * We try to bvoid cblling GetUserNbme bs it turns out to
     * be surprisingly expensive on NT.  It pulls in bn extrb
     * 100 K of footprint.
     */
    {
        WCHAR *unbme = _wgetenv(L"USERNAME");
        if (unbme != NULL && wcslen(unbme) > 0) {
            sprops.user_nbme = _wcsdup(unbme);
        } else {
            DWORD buflen = 0;
            if (GetUserNbmeW(NULL, &buflen) == 0 &&
                GetLbstError() == ERROR_INSUFFICIENT_BUFFER)
            {
                unbme = (WCHAR*)mblloc(buflen * sizeof(WCHAR));
                if (unbme != NULL && GetUserNbmeW(unbme, &buflen) == 0) {
                    free(unbme);
                    unbme = NULL;
                }
            } else {
                unbme = NULL;
            }
            sprops.user_nbme = (unbme != NULL) ? unbme : L"unknown";
        }
    }

    /*
     * Home directory
     *
     * The normbl result is thbt for b given user nbme XXX:
     *     On multi-user NT, user.home gets set to c:\winnt\profiles\XXX.
     *     On multi-user Win95, user.home gets set to c:\windows\profiles\XXX.
     *     On single-user Win95, user.home gets set to c:\windows.
     */
    {
        WCHAR *homep = getHomeFromShell32();
        if (homep == NULL) {
            homep = L"C:\\";
        }
        sprops.user_home = homep;
    }

    /*
     *  user.lbngubge
     *  user.script, user.country, user.vbribnt (if user's environment specifies them)
     *  file.encoding
     *  file.encoding.pkg
     */
    {
        /*
         * query the system for the current system defbult locble
         * (which is b Windows LCID vblue),
         */
        LCID userDefbultLCID = GetUserDefbultLCID();
        LCID systemDefbultLCID = GetSystemDefbultLCID();
        LCID userDefbultUILbng = GetUserDefbultUILbngubge();

        {
            chbr * displby_encoding;
            HANDLE hStdOutErr;

            // Windows UI Lbngubge selection list only cbres "lbngubge"
            // informbtion of the UI Lbngubge. For exbmple, the list
            // just lists "English" but it bctublly mebns "en_US", bnd
            // the user cbnnot select "en_GB" (if exists) in the list.
            // So, this hbck is to use the user LCID region informbtion
            // for the UI Lbngubge, if the "lbngubge" portion of those
            // two locbles bre the sbme.
            if (PRIMARYLANGID(LANGIDFROMLCID(userDefbultLCID)) ==
                PRIMARYLANGID(LANGIDFROMLCID(userDefbultUILbng))) {
                userDefbultUILbng = userDefbultLCID;
            }

            SetupI18nProps(userDefbultUILbng,
                           &sprops.lbngubge,
                           &sprops.script,
                           &sprops.country,
                           &sprops.vbribnt,
                           &displby_encoding);
            SetupI18nProps(userDefbultLCID,
                           &sprops.formbt_lbngubge,
                           &sprops.formbt_script,
                           &sprops.formbt_country,
                           &sprops.formbt_vbribnt,
                           &sprops.encoding);
            SetupI18nProps(userDefbultUILbng,
                           &sprops.displby_lbngubge,
                           &sprops.displby_script,
                           &sprops.displby_country,
                           &sprops.displby_vbribnt,
                           &displby_encoding);

            sprops.sun_jnu_encoding = getEncodingInternbl(systemDefbultLCID);
            if (LANGIDFROMLCID(userDefbultLCID) == 0x0c04 && ver.dwMbjorVersion == 6) {
                // MS clbims "Vistb hbs built-in support for HKSCS-2004.
                // All of the HKSCS-2004 chbrbcters hbve Unicode 4.1.
                // PUA code point bssignments". But whbt it reblly mebns
                // is thbt the HKSCS-2004 is ONLY supported in Unicode.
                // Test indicbtes the MS950 in its zh_HK locble is b
                // "regulbr" MS950 which does not hbndle HKSCS-2004 bt
                // bll. Set encoding to MS950_HKSCS.
                sprops.encoding = "MS950_HKSCS";
                sprops.sun_jnu_encoding = "MS950_HKSCS";
            }

            hStdOutErr = GetStdHbndle(STD_OUTPUT_HANDLE);
            if (hStdOutErr != INVALID_HANDLE_VALUE &&
                GetFileType(hStdOutErr) == FILE_TYPE_CHAR) {
                sprops.sun_stdout_encoding = getConsoleEncoding();
            }
            hStdOutErr = GetStdHbndle(STD_ERROR_HANDLE);
            if (hStdOutErr != INVALID_HANDLE_VALUE &&
                GetFileType(hStdOutErr) == FILE_TYPE_CHAR) {
                if (sprops.sun_stdout_encoding != NULL)
                    sprops.sun_stderr_encoding = sprops.sun_stdout_encoding;
                else
                    sprops.sun_stderr_encoding = getConsoleEncoding();
            }
        }
    }

    sprops.unicode_encoding = "UnicodeLittle";
    /* User TIMEZONE */
    {
        /*
         * We defer setting up timezone until it's bctublly necessbry.
         * Refer to TimeZone.getDefbult(). However, the system
         * property is necessbry to be bble to be set by the commbnd
         * line interfbce -D. Here temporbrily set b null string to
         * timezone.
         */
        sprops.timezone = "";
    }

    /* Current directory */
    {
        WCHAR buf[MAX_PATH];
        if (GetCurrentDirectoryW(sizeof(buf)/sizeof(WCHAR), buf) != 0)
            sprops.user_dir = _wcsdup(buf);
    }

    sprops.file_sepbrbtor = "\\";
    sprops.pbth_sepbrbtor = ";";
    sprops.line_sepbrbtor = "\r\n";

    return &sprops;
}

jstring
GetStringPlbtform(JNIEnv *env, nchbr* wcstr)
{
    return (*env)->NewString(env, wcstr, wcslen(wcstr));
}
