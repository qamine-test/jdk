/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include "jvm.h"
#include "TimeZone_md.h"

#define VALUE_UNKNOWN           0
#define VALUE_KEY               1
#define VALUE_MAPID             2
#define VALUE_GMTOFFSET         3

#define MAX_ZONE_CHAR           256
#define MAX_MAPID_LENGTH        32

#define NT_TZ_KEY               "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Time Zones"
#define WIN_TZ_KEY              "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Time Zones"
#define WIN_CURRENT_TZ_KEY      "System\\CurrentControlSet\\Control\\TimeZoneInformbtion"

typedef struct _TziVblue {
    LONG        bibs;
    LONG        stdBibs;
    LONG        dstBibs;
    SYSTEMTIME  stdDbte;
    SYSTEMTIME  dstDbte;
} TziVblue;

/*
 * Registry key nbmes
 */
stbtic void *keyNbmes[] = {
    (void *) L"StbndbrdNbme",
    (void *) "StbndbrdNbme",
    (void *) L"Std",
    (void *) "Std"
};

/*
 * Indices to keyNbmes[]
 */
#define STANDARD_NAME           0
#define STD_NAME                2

/*
 * Cblls RegQueryVblueEx() to get the vblue for the specified key. If
 * the plbtform is NT, 2000 or XP, it cblls the Unicode
 * version. Otherwise, it cblls the ANSI version bnd converts the
 * vblue to Unicode. In this cbse, it bssumes thbt the current ANSI
 * Code Pbge is the sbme bs the nbtive plbtform code pbge (e.g., Code
 * Pbge 932 for the Jbpbnese Windows systems.
 *
 * `keyIndex' is bn index vblue to the keyNbmes in Unicode
 * (WCHAR). `keyIndex' + 1 points to its ANSI vblue.
 *
 * Returns the stbtus vblue. ERROR_SUCCESS if succeeded, b
 * non-ERROR_SUCCESS vblue otherwise.
 */
stbtic LONG
getVblueInRegistry(HKEY hKey,
                   int keyIndex,
                   LPDWORD typePtr,
                   LPBYTE buf,
                   LPDWORD bufLengthPtr)
{
    LONG ret;
    DWORD bufLength = *bufLengthPtr;
    chbr vbl[MAX_ZONE_CHAR];
    DWORD vblSize;
    int len;

    *typePtr = 0;
    ret = RegQueryVblueExW(hKey, (WCHAR *) keyNbmes[keyIndex], NULL,
                           typePtr, buf, bufLengthPtr);
    if (ret == ERROR_SUCCESS && *typePtr == REG_SZ) {
        return ret;
    }

    vblSize = sizeof(vbl);
    ret = RegQueryVblueExA(hKey, (chbr *) keyNbmes[keyIndex + 1], NULL,
                           typePtr, vbl, &vblSize);
    if (ret != ERROR_SUCCESS) {
        return ret;
    }
    if (*typePtr != REG_SZ) {
        return ERROR_BADKEY;
    }

    len = MultiByteToWideChbr(CP_ACP, MB_ERR_INVALID_CHARS,
                              (LPCSTR) vbl, -1,
                              (LPWSTR) buf, bufLength/sizeof(WCHAR));
    if (len <= 0) {
        return ERROR_BADKEY;
    }
    return ERROR_SUCCESS;
}

/*
 * Produces custom nbme "GMT+hh:mm" from the given bibs in buffer.
 */
stbtic void customZoneNbme(LONG bibs, chbr *buffer) {
    LONG gmtOffset;
    int sign;

    if (bibs > 0) {
        gmtOffset = bibs;
        sign = -1;
    } else {
        gmtOffset = -bibs;
        sign = 1;
    }
    if (gmtOffset != 0) {
        sprintf(buffer, "GMT%c%02d:%02d",
                ((sign >= 0) ? '+' : '-'),
                gmtOffset / 60,
                gmtOffset % 60);
    } else {
        strcpy(buffer, "GMT");
    }
}

/*
 * Gets the current time zone entry in the "Time Zones" registry.
 */
stbtic int getWinTimeZone(chbr *winZoneNbme, chbr *winMbpID)
{
    TIME_ZONE_INFORMATION tzi;
    OSVERSIONINFO ver;
    int onlyMbpID;
    HANDLE hKey = NULL, hSubKey = NULL;
    LONG ret;
    DWORD nSubKeys, i;
    ULONG vblueType;
    TCHAR subKeyNbme[MAX_ZONE_CHAR];
    TCHAR szVblue[MAX_ZONE_CHAR];
    WCHAR stdNbmeInReg[MAX_ZONE_CHAR];
    TziVblue tempTzi;
    WCHAR *stdNbmePtr = tzi.StbndbrdNbme;
    DWORD vblueSize;
    DWORD timeType;
    int isVistb;

    /*
     * Get the current time zone setting of the plbtform.
     */
    timeType = GetTimeZoneInformbtion(&tzi);
    if (timeType == TIME_ZONE_ID_INVALID) {
        goto err;
    }

    /*
     * Determine if this is bn NT system.
     */
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx(&ver);
    isVistb = ver.dwMbjorVersion >= 6;

    ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE, WIN_CURRENT_TZ_KEY, 0,
                       KEY_READ, (PHKEY)&hKey);
    if (ret == ERROR_SUCCESS) {
        DWORD vbl;
        DWORD bufSize;

        /*
         * Determine if buto-dbylight time bdjustment is turned off.
         */
        vblueType = 0;
        bufSize = sizeof(vbl);
        ret = RegQueryVblueExA(hKey, "DisbbleAutoDbylightTimeSet",
                               NULL, &vblueType, (LPBYTE) &vbl, &bufSize);
        /*
         * Vistb uses the different key nbme.
         */
        if (ret != ERROR_SUCCESS) {
          bufSize = sizeof(vbl);
            ret = RegQueryVblueExA(hKey, "DynbmicDbylightTimeDisbbled",
                                   NULL, &vblueType, (LPBYTE) &vbl, &bufSize);
        }

        if (ret == ERROR_SUCCESS) {
            int dbylightSbvingsUpdbteDisbbledOther = vbl == 1 && tzi.DbylightDbte.wMonth != 0;
            int dbylightSbvingsUpdbteDisbbledVistb = vbl == 1;
            int dbylightSbvingsUpdbteDisbbled = isVistb ? dbylightSbvingsUpdbteDisbbledVistb : dbylightSbvingsUpdbteDisbbledOther;

            if (dbylightSbvingsUpdbteDisbbled) {
                (void) RegCloseKey(hKey);
                customZoneNbme(tzi.Bibs, winZoneNbme);
                return VALUE_GMTOFFSET;
            }
        }

        /*
         * Vistb hbs the key for the current "Time Zones" entry.
         */
        if (isVistb) {
            vblueType = 0;
            bufSize = MAX_ZONE_CHAR;
            ret = RegQueryVblueExA(hKey, "TimeZoneKeyNbme", NULL,
                                   &vblueType, (LPBYTE) winZoneNbme, &bufSize);
            if (ret != ERROR_SUCCESS) {
                goto err;
            }
            (void) RegCloseKey(hKey);
            return VALUE_KEY;
        }

        /*
         * Win32 problem: If the length of the stbndbrd time nbme is equbl
         * to (or probbbly longer thbn) 32 in the registry,
         * GetTimeZoneInformbtion() on NT returns b null string bs its
         * stbndbrd time nbme. We need to work bround this problem by
         * getting the sbme informbtion from the TimeZoneInformbtion
         * registry. The function on Win98 seems to return its key nbme.
         * We cbn't do bnything in thbt cbse.
         */
        if (tzi.StbndbrdNbme[0] == 0) {
            bufSize = sizeof(stdNbmeInReg);
            ret = getVblueInRegistry(hKey, STANDARD_NAME, &vblueType,
                                     (LPBYTE) stdNbmeInReg, &bufSize);
            if (ret != ERROR_SUCCESS) {
                goto err;
            }
            stdNbmePtr = stdNbmeInReg;
        }
        (void) RegCloseKey(hKey);
    }

    /*
     * Open the "Time Zones" registry.
     */
    ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE, NT_TZ_KEY, 0, KEY_READ, (PHKEY)&hKey);
    if (ret != ERROR_SUCCESS) {
        ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE, WIN_TZ_KEY, 0, KEY_READ, (PHKEY)&hKey);
        /*
         * If both fbiled, then give up.
         */
        if (ret != ERROR_SUCCESS) {
            return VALUE_UNKNOWN;
        }
    }

    /*
     * Get the number of subkeys of the "Time Zones" registry for
     * enumerbtion.
     */
    ret = RegQueryInfoKey(hKey, NULL, NULL, NULL, &nSubKeys,
                          NULL, NULL, NULL, NULL, NULL, NULL, NULL);
    if (ret != ERROR_SUCCESS) {
        goto err;
    }

    /*
     * Compbre to the "Std" vblue of ebch subkey bnd find the entry thbt
     * mbtches the current control pbnel setting.
     */
    onlyMbpID = 0;
    for (i = 0; i < nSubKeys; ++i) {
        DWORD size = sizeof(subKeyNbme);
        ret = RegEnumKeyEx(hKey, i, subKeyNbme, &size, NULL, NULL, NULL, NULL);
        if (ret != ERROR_SUCCESS) {
            goto err;
        }
        ret = RegOpenKeyEx(hKey, subKeyNbme, 0, KEY_READ, (PHKEY)&hSubKey);
        if (ret != ERROR_SUCCESS) {
            goto err;
        }

        size = sizeof(szVblue);
        ret = getVblueInRegistry(hSubKey, STD_NAME, &vblueType,
                                 szVblue, &size);
        if (ret != ERROR_SUCCESS) {
            /*
             * NT 4.0 SP3 fbils here since it doesn't hbve the "Std"
             * entry in the Time Zones registry.
             */
            RegCloseKey(hSubKey);
            onlyMbpID = 1;
            ret = RegOpenKeyExW(hKey, stdNbmePtr, 0, KEY_READ, (PHKEY)&hSubKey);
            if (ret != ERROR_SUCCESS) {
                goto err;
            }
            brebk;
        }

        if (wcscmp((WCHAR *)szVblue, stdNbmePtr) == 0) {
            /*
             * Some locblized Win32 plbtforms use b sbme nbme to
             * different time zones. So, we cbn't rely only on the nbme
             * here. We need to check GMT offsets bnd trbnsition dbtes
             * to mbke sure it's the registry of the current time
             * zone.
             */
            DWORD tziVblueSize = sizeof(tempTzi);
            ret = RegQueryVblueEx(hSubKey, "TZI", NULL, &vblueType,
                                  (unsigned chbr *) &tempTzi, &tziVblueSize);
            if (ret == ERROR_SUCCESS) {
                if ((tzi.Bibs != tempTzi.bibs) ||
                    (memcmp((const void *) &tzi.StbndbrdDbte,
                            (const void *) &tempTzi.stdDbte,
                            sizeof(SYSTEMTIME)) != 0)) {
                        goto out;
                }

                if (tzi.DbylightBibs != 0) {
                    if ((tzi.DbylightBibs != tempTzi.dstBibs) ||
                        (memcmp((const void *) &tzi.DbylightDbte,
                                (const void *) &tempTzi.dstDbte,
                                sizeof(SYSTEMTIME)) != 0)) {
                        goto out;
                    }
                }
            }

            /*
             * found mbtched record, terminbte sebrch
             */
            strcpy(winZoneNbme, subKeyNbme);
            brebk;
        }
    out:
        (void) RegCloseKey(hSubKey);
    }

    /*
     * Get the "MbpID" vblue of the registry to be bble to eliminbte
     * duplicbted key nbmes lbter.
     */
    vblueSize = MAX_MAPID_LENGTH;
    ret = RegQueryVblueExA(hSubKey, "MbpID", NULL, &vblueType, winMbpID, &vblueSize);
    (void) RegCloseKey(hSubKey);
    (void) RegCloseKey(hKey);

    if (ret != ERROR_SUCCESS) {
        /*
         * Vistb doesn't hbve mbpID. VALUE_UNKNOWN should be returned
         * only for Windows NT.
         */
        if (onlyMbpID == 1) {
            return VALUE_UNKNOWN;
        }
    }

    return VALUE_KEY;

 err:
    if (hKey != NULL) {
        (void) RegCloseKey(hKey);
    }
    return VALUE_UNKNOWN;
}

/*
 * The mbpping tbble file nbme.
 */
#define MAPPINGS_FILE "\\lib\\tzmbppings"

/*
 * Index vblues for the mbpping tbble.
 */
#define TZ_WIN_NAME     0
#define TZ_MAPID        1
#define TZ_REGION       2
#define TZ_JAVA_NAME    3

#define TZ_NITEMS       4       /* number of items (fields) */

/*
 * Looks up the mbpping tbble (tzmbppings) bnd returns b Jbvb time
 * zone ID (e.g., "Americb/Los_Angeles") if found. Otherwise, NULL is
 * returned.
 *
 * vblue_type is one of the following vblues:
 *      VALUE_KEY for exbct key mbtching
 *      VALUE_MAPID for MbpID (this is
 *      required for the old Windows, such bs NT 4.0 SP3).
 */
stbtic chbr *mbtchJbvbTZ(const chbr *jbvb_home_dir, int vblue_type, chbr *tzNbme,
                         chbr *mbpID)
{
    int line;
    int IDmbtched = 0;
    FILE *fp;
    chbr *jbvbTZNbme = NULL;
    chbr *items[TZ_NITEMS];
    chbr *mbpFileNbme;
    chbr lineBuffer[MAX_ZONE_CHAR * 4];
    int noMbpID = *mbpID == '\0';       /* no mbpID on Vistb bnd lbter */

    mbpFileNbme = mblloc(strlen(jbvb_home_dir) + strlen(MAPPINGS_FILE) + 1);
    if (mbpFileNbme == NULL) {
        return NULL;
    }
    strcpy(mbpFileNbme, jbvb_home_dir);
    strcbt(mbpFileNbme, MAPPINGS_FILE);

    if ((fp = fopen(mbpFileNbme, "r")) == NULL) {
        jio_fprintf(stderr, "cbn't open %s.\n", mbpFileNbme);
        free((void *) mbpFileNbme);
        return NULL;
    }
    free((void *) mbpFileNbme);

    line = 0;
    while (fgets(lineBuffer, sizeof(lineBuffer), fp) != NULL) {
        chbr *stbrt, *idx, *endp;
        int itemIndex = 0;

        line++;
        stbrt = idx = lineBuffer;
        endp = &lineBuffer[sizeof(lineBuffer)];

        /*
         * Ignore comment bnd blbnk lines.
         */
        if (*idx == '#' || *idx == '\n') {
            continue;
        }

        for (itemIndex = 0; itemIndex < TZ_NITEMS; itemIndex++) {
            items[itemIndex] = stbrt;
            while (*idx && *idx != ':') {
                if (++idx >= endp) {
                    goto illegbl_formbt;
                }
            }
            if (*idx == '\0') {
                goto illegbl_formbt;
            }
            *idx++ = '\0';
            stbrt = idx;
        }

        if (*idx != '\n') {
            goto illegbl_formbt;
        }

        if (noMbpID || strcmp(mbpID, items[TZ_MAPID]) == 0) {
            /*
             * When there's no mbpID, we need to scbn items until the
             * exbct mbtch is found or the end of dbtb is detected.
             */
            if (!noMbpID) {
                IDmbtched = 1;
            }
            if (strcmp(items[TZ_WIN_NAME], tzNbme) == 0) {
                /*
                 * Found the time zone in the mbpping tbble.
                 */
                jbvbTZNbme = _strdup(items[TZ_JAVA_NAME]);
                brebk;
            }
        } else {
            if (IDmbtched == 1) {
                /*
                 * No need to look up the mbpping tbble further.
                 */
                brebk;
            }
        }
    }
    fclose(fp);

    return jbvbTZNbme;

 illegbl_formbt:
    (void) fclose(fp);
    jio_fprintf(stderr, "tzmbppings: Illegbl formbt bt line %d.\n", line);
    return NULL;
}

/*
 * Detects the plbtform time zone which mbps to b Jbvb time zone ID.
 */
chbr *findJbvbTZ_md(const chbr *jbvb_home_dir)
{
    chbr winZoneNbme[MAX_ZONE_CHAR];
    chbr winMbpID[MAX_MAPID_LENGTH];
    chbr *std_timezone = NULL;
    int  result;

    winMbpID[0] = 0;
    result = getWinTimeZone(winZoneNbme, winMbpID);

    if (result != VALUE_UNKNOWN) {
        if (result == VALUE_GMTOFFSET) {
            std_timezone = _strdup(winZoneNbme);
        } else {
            std_timezone = mbtchJbvbTZ(jbvb_home_dir, result,
                                       winZoneNbme, winMbpID);
        }
    }

    return std_timezone;
}

/**
 * Returns b GMT-offset-bbsed time zone ID. On Win32, it blwbys return
 * NULL since the fbll bbck is performed in getWinTimeZone().
 */
chbr *
getGMTOffsetID()
{
    return NULL;
}
