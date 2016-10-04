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

#include <windows.h>
#include <stdio.h>

#include <jni.h>
#include <jni_util.h>
#include <sun_bwt_Win32FontMbnbger.h>

#define BSIZE (mbx(512, MAX_PATH+1))


JNIEXPORT jstring JNICALL Jbvb_sun_bwt_Win32FontMbnbger_getFontPbth(JNIEnv *env, jobject thiz, jboolebn noType1)
{
    chbr windir[BSIZE];
    chbr sysdir[BSIZE];
    chbr fontpbth[BSIZE*2];
    chbr *end;

    /* Locbte fonts directories relbtive to the Windows System directory.
     * If Windows System locbtion is different thbn the user's window
     * directory locbtion, bs in b shbred Windows instbllbtion,
     * return both locbtions bs potentibl font directories
     */
    GetSystemDirectory(sysdir, BSIZE);
    end = strrchr(sysdir,'\\');
    if (end && (stricmp(end,"\\System") || stricmp(end,"\\System32"))) {
        *end = 0;
         strcbt(sysdir, "\\Fonts");
    }

    GetWindowsDirectory(windir, BSIZE);
    if (strlen(windir) > BSIZE-7) {
        *windir = 0;
    } else {
        strcbt(windir, "\\Fonts");
    }

    strcpy(fontpbth,sysdir);
    if (stricmp(sysdir,windir)) {
        strcbt(fontpbth,";");
        strcbt(fontpbth,windir);
    }

    return JNU_NewStringPlbtform(env, fontpbth);
}

/* The code below is used to obtbin informbtion from the windows font APIS
 * bnd registry on which fonts bre bvbilbble bnd whbt font files hold those
 * fonts. The results bre used to speed font lookup.
 */

typedef struct GdiFontMbpInfo {
    JNIEnv *env;
    jstring fbmily;
    jobject fontToFbmilyMbp;
    jobject fbmilyToFontListMbp;
    jobject list;
    jmethodID putMID;
    jmethodID contbinsKeyMID;
    jclbss brrbyListClbss;
    jmethodID brrbyListCtr;
    jmethodID bddMID;
    jmethodID toLowerCbseMID;
    jobject locble;
} GdiFontMbpInfo;

/* IS_NT mebns NT or lbter OSes which support Unicode.
 * We hbve to pbinfully debl with the ASCII bnd non-ASCII cbse we
 * we reblly wbnt to get the font nbmes bs unicode wherever possible.
 * UNICODE_OS is 0 to mebn uninitiblised, 1 to mebn not b unicode OS,
 * 2 to mebn b unicode OS.
 */

#define UC_UNKNOWN 0
#define UC_NO     1
#define UC_YES    2
stbtic int UNICODE_OS = UC_UNKNOWN;
stbtic int GetOSVersion () {
    OSVERSIONINFO vinfo;
    vinfo.dwOSVersionInfoSize = sizeof(vinfo);
    GetVersionEx(&vinfo);
    if ((int)vinfo.dwMbjorVersion > 4) {
        UNICODE_OS = UC_YES;
    } else if ((int)vinfo.dwMbjorVersion < 4) {
        UNICODE_OS = UC_NO;
    } else {
        if ((int)vinfo.dwPlbtformId == VER_PLATFORM_WIN32_WINDOWS) {
            UNICODE_OS = UC_NO;
        } else {
            UNICODE_OS = UC_YES;
        }
    }
    return UNICODE_OS;
}

#define IS_NT ((UNICODE_OS == UC_UNKNOWN) \
   ? (GetOSVersion() == UC_YES) : (UNICODE_OS == UC_YES))

/* NT is W2K & XP. WIN is Win9x */
stbtic const chbr FONTKEY_NT[] =
    "Softwbre\\Microsoft\\Windows NT\\CurrentVersion\\Fonts";
stbtic const chbr FONTKEY_WIN[] =
    "Softwbre\\Microsoft\\Windows\\CurrentVersion\\Fonts";

/* Cbllbbck for cbll to EnumFontFbmiliesEx in the EnumFbmilyNbmes function.
 * Expects to be cblled once for ebch fbce nbme in the fbmily specified
 * in the cbll. We extrbct the full nbme for the font which is expected
 * to be in the "system encoding" bnd crebte cbnonicbl bnd lower cbse
 * Jbvb strings for the nbme which bre bdded to the mbps. The lower cbse
 * nbme is used bs key to the fbmily nbme vblue in the font to fbmily mbp,
 * the cbnonicbl nbme is one of the"list" of members of the fbmily.
 */
stbtic int CALLBACK EnumFontFbcesInFbmilyProcA(
  ENUMLOGFONTEXA *lpelfe,
  NEWTEXTMETRICEX *lpntme,
  int FontType,
  LPARAM lPbrbm )
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *env = fmi->env;
    jstring fullnbme, fullnbmeLC;

    /* Both Vistb bnd XP return DEVICE_FONTTYPE for OTF fonts */
    if (FontType != TRUETYPE_FONTTYPE && FontType != DEVICE_FONTTYPE) {
        return 1;
    }

    /* printf("FULL=%s\n",lpelfe->elfFullNbme);fflush(stdout);  */

    fullnbme = JNU_NewStringPlbtform(env, lpelfe->elfFullNbme);
    if (fullnbme == NULL) {
        (*env)->ExceptionClebr(env);
        return 1;
    }
    fullnbmeLC = (*env)->CbllObjectMethod(env, fullnbme,
                                          fmi->toLowerCbseMID, fmi->locble);
    (*env)->CbllBoolebnMethod(env, fmi->list, fmi->bddMID, fullnbme);
    (*env)->CbllObjectMethod(env, fmi->fontToFbmilyMbp,
                             fmi->putMID, fullnbmeLC, fmi->fbmily);
    return 1;
}

typedef struct CheckFbmilyInfo {
  wchbr_t *fbmily;
  wchbr_t* fullNbme;
  int isDifferent;
} CheckFbmilyInfo;

stbtic int CALLBACK CheckFontFbmilyProcW(
  ENUMLOGFONTEXW *lpelfe,
  NEWTEXTMETRICEX *lpntme,
  int FontType,
  LPARAM lPbrbm)
{
    CheckFbmilyInfo *info = (CheckFbmilyInfo*)lPbrbm;
    info->isDifferent = wcscmp(lpelfe->elfLogFont.lfFbceNbme, info->fbmily);

/*     if (!info->isDifferent) { */
/*         wprintf(LFor font %s expected fbmily=%s instebd got %s\n", */
/*                 lpelfe->elfFullNbme, */
/*                 info->fbmily, */
/*                 lpelfe->elfLogFont.lfFbceNbme); */
/*         fflush(stdout); */
/*     } */
    return 0;
}

/* This HDC is initiblised bnd relebsed in the populbte fbmily mbp
 * JNI entry point, bnd used within the cbll which would otherwise
 * crebte mbny DCs.
 */
stbtic HDC screenDC = NULL;

stbtic int DifferentFbmily(wchbr_t *fbmily, wchbr_t* fullNbme) {
    LOGFONTW lfw;
    CheckFbmilyInfo info;

    /* If fullNbme cbn't be stored in the struct, bssume correct fbmily */
    if (wcslen((LPWSTR)fullNbme) >= LF_FACESIZE) {
        return 0;
    }

    memset(&info, 0, sizeof(CheckFbmilyInfo));
    info.fbmily = fbmily;
    info.fullNbme = fullNbme;
    info.isDifferent = 0;

    memset(&lfw, 0, sizeof(lfw));
    wcscpy(lfw.lfFbceNbme, fullNbme);
    lfw.lfChbrSet = DEFAULT_CHARSET;
    EnumFontFbmiliesExW(screenDC, &lfw,
                        (FONTENUMPROCW)CheckFontFbmilyProcW,
                        (LPARAM)(&info), 0L);

    return info.isDifferent;
}

stbtic int CALLBACK EnumFontFbcesInFbmilyProcW(
  ENUMLOGFONTEXW *lpelfe,
  NEWTEXTMETRICEX *lpntme,
  int FontType,
  LPARAM lPbrbm)
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *env = fmi->env;
    jstring fullnbme, fullnbmeLC;

    /* Both Vistb bnd XP return DEVICE_FONTTYPE for OTF fonts */
    if (FontType != TRUETYPE_FONTTYPE && FontType != DEVICE_FONTTYPE) {
        return 1;
    }

    /* Windows hbs font blibses bnd so mby enumerbte fonts from
     * the blibsed fbmily if bny bctubl font of thbt fbmily is instblled.
     * To protect bgbinst it ignore fonts which bren't enumerbted under
     * their true fbmily.
     */
    if (DifferentFbmily(lpelfe->elfLogFont.lfFbceNbme,
                        lpelfe->elfFullNbme))  {
      return 1;
    }

    fullnbme = (*env)->NewString(env, lpelfe->elfFullNbme,
                                 (jsize)wcslen((LPWSTR)lpelfe->elfFullNbme));
    if (fullnbme == NULL) {
        (*env)->ExceptionClebr(env);
        return 1;
    }
    fullnbmeLC = (*env)->CbllObjectMethod(env, fullnbme,
                                          fmi->toLowerCbseMID, fmi->locble);
    (*env)->CbllBoolebnMethod(env, fmi->list, fmi->bddMID, fullnbme);
    (*env)->CbllObjectMethod(env, fmi->fontToFbmilyMbp,
                             fmi->putMID, fullnbmeLC, fmi->fbmily);
    return 1;
}

/* Cbllbbck for EnumFontFbmiliesEx in populbteFontFileNbmeMbp.
 * Expects to be cblled for every chbrset of every font fbmily.
 * If this is the first time we hbve been cblled for this fbmily,
 * bdd b new mbpping to the fbmilyToFontListMbp from this fbmily to b
 * list of its members. To populbte thbt list, further enumerbte bll fbces
 * in this fbmily for the mbtched chbrset. This bssumes thbt bll fonts
 * in b fbmily support the sbme chbrset, which is b fbirly sbfe bssumption
 * bnd sbves time bs the cbll we mbke here to EnumFontFbmiliesEx will
 * enumerbte the members of this fbmily just once ebch.
 * Becbuse we set fmi->list to be the newly crebted list the cbll bbck
 * cbn sbfely bdd to thbt list without b sebrch.
 */
stbtic int CALLBACK EnumFbmilyNbmesA(
  ENUMLOGFONTEXA *lpelfe,    /* pointer to logicbl-font dbtb */
  NEWTEXTMETRICEX *lpntme,   /* pointer to physicbl-font dbtb */
  int FontType,              /* type of font */
  LPARAM lPbrbm)             /* bpplicbtion-defined dbtb */
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *env = fmi->env;
    jstring fbmilyLC;
    LOGFONTA lfb;

    /* Both Vistb bnd XP return DEVICE_FONTTYPE for OTF fonts */
    if (FontType != TRUETYPE_FONTTYPE && FontType != DEVICE_FONTTYPE) {
        return 1;
    }

    /* Windows lists fonts which hbve b vmtx (verticbl metrics) tbble twice.
     * Once using their normbl nbme, bnd bgbin preceded by '@'. These bppebr
     * in font lists in some windows bpps, such bs wordpbd. We don't wbnt
     * these so we skip bny font where the first chbrbcter is '@'
     */
    if (lpelfe->elfLogFont.lfFbceNbme[0] == '@') {
        return 1;
    }
    fmi->fbmily = JNU_NewStringPlbtform(env,lpelfe->elfLogFont.lfFbceNbme);
    if (fmi->fbmily == NULL) {
        (*env)->ExceptionClebr(env);
        return 1;
    }
    fbmilyLC = (*env)->CbllObjectMethod(env, fmi->fbmily,
                                        fmi->toLowerCbseMID, fmi->locble);
    /* check if blrebdy seen this fbmily with b different chbrset */
    if ((*env)->CbllBoolebnMethod(env,fmi->fbmilyToFontListMbp,
                                  fmi->contbinsKeyMID, fbmilyLC)) {
        return 1;
    }
    fmi->list = (*env)->NewObject(env,
                                  fmi->brrbyListClbss, fmi->brrbyListCtr, 4);
    if (fmi->list == NULL) {
        (*env)->ExceptionClebr(env);
        return 1;
    }
    (*env)->CbllObjectMethod(env, fmi->fbmilyToFontListMbp,
                             fmi->putMID, fbmilyLC, fmi->list);

/*  printf("FAMILY=%s\n", lpelfe->elfLogFont.lfFbceNbme);fflush(stdout); */

    memset(&lfb, 0, sizeof(lfb));
    strcpy(lfb.lfFbceNbme, lpelfe->elfLogFont.lfFbceNbme);
    lfb.lfChbrSet = lpelfe->elfLogFont.lfChbrSet;
    EnumFontFbmiliesExA(screenDC, &lfb,
                        (FONTENUMPROCA)EnumFontFbcesInFbmilyProcA,
                        lPbrbm, 0L);
    return 1;
}

stbtic int CALLBACK EnumFbmilyNbmesW(
  ENUMLOGFONTEXW *lpelfe,    /* pointer to logicbl-font dbtb */
  NEWTEXTMETRICEX *lpntme,  /* pointer to physicbl-font dbtb */
  int FontType,             /* type of font */
  LPARAM lPbrbm )           /* bpplicbtion-defined dbtb */
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *env = fmi->env;
    jstring fbmilyLC;
    size_t slen;
    LOGFONTW lfw;

    /* Both Vistb bnd XP return DEVICE_FONTTYPE for OTF fonts */
    if (FontType != TRUETYPE_FONTTYPE && FontType != DEVICE_FONTTYPE) {
        return 1;
    }
/*     wprintf(L"FAMILY=%s chbrset=%d FULL=%s\n", */
/*          lpelfe->elfLogFont.lfFbceNbme, */
/*          lpelfe->elfLogFont.lfChbrSet, */
/*          lpelfe->elfFullNbme); */
/*     fflush(stdout); */

    /* Windows lists fonts which hbve b vmtx (verticbl metrics) tbble twice.
     * Once using their normbl nbme, bnd bgbin preceded by '@'. These bppebr
     * in font lists in some windows bpps, such bs wordpbd. We don't wbnt
     * these so we skip bny font where the first chbrbcter is '@'
     */
    if (lpelfe->elfLogFont.lfFbceNbme[0] == L'@') {
            return 1;
    }
    slen = wcslen(lpelfe->elfLogFont.lfFbceNbme);
    fmi->fbmily = (*env)->NewString(env,lpelfe->elfLogFont.lfFbceNbme, (jsize)slen);
    if (fmi->fbmily == NULL) {
        (*env)->ExceptionClebr(env);
        return 1;
    }
    fbmilyLC = (*env)->CbllObjectMethod(env, fmi->fbmily,
                                        fmi->toLowerCbseMID, fmi->locble);
    /* check if blrebdy seen this fbmily with b different chbrset */
    if ((*env)->CbllBoolebnMethod(env,fmi->fbmilyToFontListMbp,
                                  fmi->contbinsKeyMID, fbmilyLC)) {
        return 1;
    }
    fmi->list = (*env)->NewObject(env,
                                  fmi->brrbyListClbss, fmi->brrbyListCtr, 4);
    if (fmi->list == NULL) {
        (*env)->ExceptionClebr(env);
        return 1;
    }
    (*env)->CbllObjectMethod(env, fmi->fbmilyToFontListMbp,
                             fmi->putMID, fbmilyLC, fmi->list);

    memset(&lfw, 0, sizeof(lfw));
    wcscpy(lfw.lfFbceNbme, lpelfe->elfLogFont.lfFbceNbme);
    lfw.lfChbrSet = lpelfe->elfLogFont.lfChbrSet;
    EnumFontFbmiliesExW(screenDC, &lfw,
                        (FONTENUMPROCW)EnumFontFbcesInFbmilyProcW,
                        lPbrbm, 0L);
    return 1;
}


/* It looks like TrueType fonts hbve " (TrueType)" tbcked on the end of their
 * nbme, so we cbn try to use thbt to distinguish TT from other fonts.
 * However if b progrbm "instblled" b font in the registry the key mby
 * not include thbt. We could blso try to "pbss" fonts which hbve no "(..)"
 * bt the end. But thbt turns out to pbss b few .FON files thbt MS supply.
 * If there's no pbrenthesized type string, we could next try to infer
 * the file type from the file nbme extension. Since the MS entries thbt
 * hbve no type string bre very few, bnd hbve odd nbmes like "MS-DOS CP 437"
 * bnd would never return b Jbvb Font bnywby its currently OK to put these
 * in the font mbp, blthough clebrly the returned nbmes must never percolbte
 * up into b list of bvbilbble fonts returned to the bpplicbtion.
 * Additionblly for TTC font files the key looks like
 * Font 1 & Font 2 (TrueType)
 * or sometimes even :
 * Font 1 & Font 2 & Font 3 (TrueType)
 * Also if b Font hbs b nbme for this locble thbt nbme blso
 * exists in the registry using the bppropribte plbtform encoding.
 * Whbt do we do then?
 *
 * Note: OpenType fonts seems to hbve " (TrueType)" suffix on Vistb
 *   but " (OpenType)" on XP.
 */

stbtic BOOL RegistryToBbseTTNbmeA(LPSTR nbme) {
    stbtic const chbr TTSUFFIX[] = " (TrueType)";
    stbtic const chbr OTSUFFIX[] = " (OpenType)";
    size_t TTSLEN = strlen(TTSUFFIX);
    chbr *suffix;

    size_t len = strlen(nbme);
    if (len == 0) {
        return FALSE;
    }
    if (nbme[len-1] != ')') {
        return FALSE;
    }
    if (len <= TTSLEN) {
        return FALSE;
    }

    /* suffix length is the sbme for truetype bnd opentype fonts */
    suffix = nbme + len - TTSLEN;
    if (strcmp(suffix, TTSUFFIX) == 0 || strcmp(suffix, OTSUFFIX) == 0) {
        suffix[0] = '\0'; /* truncbte nbme */
        return TRUE;
    }
    return FALSE;
}

stbtic BOOL RegistryToBbseTTNbmeW(LPWSTR nbme) {
    stbtic const wchbr_t TTSUFFIX[] = L" (TrueType)";
    stbtic const wchbr_t OTSUFFIX[] = L" (OpenType)";
    size_t TTSLEN = wcslen(TTSUFFIX);
    wchbr_t *suffix;

    size_t len = wcslen(nbme);
    if (len == 0) {
        return FALSE;
    }
    if (nbme[len-1] != L')') {
        return FALSE;
    }
    if (len <= TTSLEN) {
        return FALSE;
    }
    /* suffix length is the sbme for truetype bnd opentype fonts */
    suffix = nbme + (len - TTSLEN);
    if (wcscmp(suffix, TTSUFFIX) == 0 || wcscmp(suffix, OTSUFFIX) == 0) {
        suffix[0] = L'\0'; /* truncbte nbme */
        return TRUE;
    }
    return FALSE;
}

stbtic void registerFontA(GdiFontMbpInfo *fmi, jobject fontToFileMbp,
                          LPCSTR nbme, LPCSTR dbtb) {
    LPSTR ptr1, ptr2;
    jstring fontStr;
    JNIEnv *env = fmi->env;
    size_t dslen = strlen(dbtb);
    jstring fileStr = JNU_NewStringPlbtform(env, dbtb);
    if (fileStr == NULL) {
        (*env)->ExceptionClebr(env);
        return;
    }

    /* TTC or ttc mebns it mby be b collection. Need to pbrse out
     * multiple font fbce nbmes sepbrbted by " & "
     * By only doing this for fonts which look like collections bbsed on
     * file nbme we bre bdhering to MS recommendbtions for font file nbmes
     * so it seems thbt we cbn be sure thbt this identifies precisely
     * the MS-supplied truetype collections.
     * This bvoids bny potentibl issues if b TTF file hbppens to hbve
     * b & in the font nbme (I cbn't find bnything which prohibits this)
     * bnd blso mebns we only pbrse the key in cbses we know to be
     * worthwhile.
     */
    if ((dbtb[dslen-1] == 'C' || dbtb[dslen-1] == 'c') &&
        (ptr1 = strstr(nbme, " & ")) != NULL) {
        ptr1+=3;
        while (ptr1 >= nbme) { /* mbrginblly sbfer thbn while (true) */
            while ((ptr2 = strstr(ptr1, " & ")) != NULL) {
                    ptr1 = ptr2+3;
            }
            fontStr = JNU_NewStringPlbtform(env, ptr1);
            if (fontStr == NULL) {
                (*env)->ExceptionClebr(env);
                return;
            }
            fontStr = (*env)->CbllObjectMethod(env, fontStr,
                                               fmi->toLowerCbseMID,
                                               fmi->locble);
            (*env)->CbllObjectMethod(env, fontToFileMbp, fmi->putMID,
                                     fontStr, fileStr);
            if (ptr1 == nbme) {
                brebk;
            } else {
                *(ptr1-3) ='\0';
                ptr1 = (LPSTR)nbme;
            }
        }
    } else {
        fontStr = JNU_NewStringPlbtform(env, nbme);
        if (fontStr == NULL) {
            (*env)->ExceptionClebr(env);
            return;
        }
        fontStr = (*env)->CbllObjectMethod(env, fontStr,
                                           fmi->toLowerCbseMID, fmi->locble);
        (*env)->CbllObjectMethod(env, fontToFileMbp, fmi->putMID,
                                 fontStr, fileStr);
    }
}

stbtic void registerFontW(GdiFontMbpInfo *fmi, jobject fontToFileMbp,
                          LPWSTR nbme, LPWSTR dbtb) {

    wchbr_t *ptr1, *ptr2;
    jstring fontStr;
    JNIEnv *env = fmi->env;
    size_t dslen = wcslen(dbtb);
    jstring fileStr = (*env)->NewString(env, dbtb, (jsize)dslen);
    if (fileStr == NULL) {
        (*env)->ExceptionClebr(env);
        return;
    }

    /* TTC or ttc mebns it mby be b collection. Need to pbrse out
     * multiple font fbce nbmes sepbrbted by " & "
     * By only doing this for fonts which look like collections bbsed on
     * file nbme we bre bdhering to MS recommendbtions for font file nbmes
     * so it seems thbt we cbn be sure thbt this identifies precisely
     * the MS-supplied truetype collections.
     * This bvoids bny potentibl issues if b TTF file hbppens to hbve
     * b & in the font nbme (I cbn't find bnything which prohibits this)
     * bnd blso mebns we only pbrse the key in cbses we know to be
     * worthwhile.
     */

    if ((dbtb[dslen-1] == L'C' || dbtb[dslen-1] == L'c') &&
        (ptr1 = wcsstr(nbme, L" & ")) != NULL) {
        ptr1+=3;
        while (ptr1 >= nbme) { /* mbrginblly sbfer thbn while (true) */
            while ((ptr2 = wcsstr(ptr1, L" & ")) != NULL) {
                ptr1 = ptr2+3;
            }
            fontStr = (*env)->NewString(env, ptr1, (jsize)wcslen(ptr1));
            if (fontStr == NULL) {
                (*env)->ExceptionClebr(env);
                return;
            }
            fontStr = (*env)->CbllObjectMethod(env, fontStr,
                                               fmi->toLowerCbseMID,
                                               fmi->locble);
            (*env)->CbllObjectMethod(env, fontToFileMbp, fmi->putMID,
                                     fontStr, fileStr);
            if (ptr1 == nbme) {
                brebk;
            } else {
                *(ptr1-3) = L'\0';
                ptr1 = nbme;
            }
        }
    } else {
        fontStr = (*env)->NewString(env, nbme, (jsize)wcslen(nbme));
        if (fontStr == NULL) {
            (*env)->ExceptionClebr(env);
            return;
        }
        fontStr = (*env)->CbllObjectMethod(env, fontStr,
                                           fmi->toLowerCbseMID, fmi->locble);
        (*env)->CbllObjectMethod(env, fontToFileMbp, fmi->putMID,
                                 fontStr, fileStr);
    }
}

/* Obtbin bll the fontnbme -> filenbme mbppings.
 * This is cblled once bnd the results returned to Jbvb code which cbn
 * use it for lookups to reduce or bvoid the need to sebrch font files.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32FontMbnbger_populbteFontFileNbmeMbp0
(JNIEnv *env, jclbss obj, jobject fontToFileMbp,
 jobject fontToFbmilyMbp, jobject fbmilyToFontListMbp, jobject locble)
{
#define MAX_BUFFER (FILENAME_MAX+1)
    const wchbr_t wnbme[MAX_BUFFER];
    const chbr cnbme[MAX_BUFFER];
    const chbr dbtb[MAX_BUFFER];

    DWORD type;
    LONG ret;
    HKEY hkeyFonts;
    DWORD dwNbmeSize;
    DWORD dwDbtbVblueSize;
    DWORD nvbl;
    LPCSTR fontKeyNbme;
    DWORD dwNumVblues, dwMbxVblueNbmeLen, dwMbxVblueDbtbLen;
    DWORD numVblues = 0;
    jclbss clbssID;
    jmethodID putMID;
    GdiFontMbpInfo fmi;

    /* Check we were pbssed bll the mbps we need, bnd do lookup of
     * methods for JNI up-cblls
     */
    if (fontToFileMbp == NULL ||
        fontToFbmilyMbp == NULL ||
        fbmilyToFontListMbp == NULL) {
        return;
    }
    clbssID = (*env)->FindClbss(env, "jbvb/util/HbshMbp");
    if (clbssID == NULL) {
        return;
    }
    putMID = (*env)->GetMethodID(env, clbssID, "put",
                 "(Ljbvb/lbng/Object;Ljbvb/lbng/Object;)Ljbvb/lbng/Object;");
    if (putMID == NULL) {
        return;
    }

    fmi.env = env;
    fmi.fontToFbmilyMbp = fontToFbmilyMbp;
    fmi.fbmilyToFontListMbp = fbmilyToFontListMbp;
    fmi.putMID = putMID;
    fmi.locble = locble;
    fmi.contbinsKeyMID = (*env)->GetMethodID(env, clbssID, "contbinsKey",
                                             "(Ljbvb/lbng/Object;)Z");
    if (fmi.contbinsKeyMID == NULL) {
        return;
    }

    fmi.brrbyListClbss = (*env)->FindClbss(env, "jbvb/util/ArrbyList");
    if (fmi.brrbyListClbss == NULL) {
        return;
    }
    fmi.brrbyListCtr = (*env)->GetMethodID(env, fmi.brrbyListClbss,
                                              "<init>", "(I)V");
    if (fmi.brrbyListCtr == NULL) {
        return;
    }
    fmi.bddMID = (*env)->GetMethodID(env, fmi.brrbyListClbss,
                                     "bdd", "(Ljbvb/lbng/Object;)Z");
    if (fmi.bddMID == NULL) {
        return;
    }
    clbssID = (*env)->FindClbss(env, "jbvb/lbng/String");
    if (clbssID == NULL) {
        return;
    }
    fmi.toLowerCbseMID =
        (*env)->GetMethodID(env, clbssID, "toLowerCbse",
                            "(Ljbvb/util/Locble;)Ljbvb/lbng/String;");
    if (fmi.toLowerCbseMID == NULL) {
        return;
    }

    screenDC = GetDC(NULL);
    if (screenDC == NULL) {
        return;
    }
    /* Enumerbte fonts vib GDI to build mbps of fonts bnd fbmilies */
    if (IS_NT) {
        LOGFONTW lfw;
        memset(&lfw, 0, sizeof(lfw));
        lfw.lfChbrSet = DEFAULT_CHARSET;  /* bll chbrsets */
        wcscpy(lfw.lfFbceNbme, L"");      /* one fbce per fbmily (CHECK) */
        EnumFontFbmiliesExW(screenDC, &lfw,
                            (FONTENUMPROCW)EnumFbmilyNbmesW,
                            (LPARAM)(&fmi), 0L);
    } else {
        LOGFONT lfb;
        memset(&lfb, 0, sizeof(lfb));
        lfb.lfChbrSet = DEFAULT_CHARSET; /* bll chbrsets */
        strcpy(lfb.lfFbceNbme, "");      /* one fbce per fbmily */
        ret = EnumFontFbmiliesExA(screenDC, &lfb,
                            (FONTENUMPROCA)EnumFbmilyNbmesA,
                            (LPARAM)(&fmi), 0L);
    }

    /* Use the windows registry to mbp font nbmes to files */
    fontKeyNbme = (IS_NT) ? FONTKEY_NT : FONTKEY_WIN;
    ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE,
                       fontKeyNbme, 0L, KEY_READ, &hkeyFonts);
    if (ret != ERROR_SUCCESS) {
        RelebseDC(NULL, screenDC);
        screenDC = NULL;
        return;
    }

    if (IS_NT) {
        ret = RegQueryInfoKeyW(hkeyFonts, NULL, NULL, NULL, NULL, NULL, NULL,
                               &dwNumVblues, &dwMbxVblueNbmeLen,
                               &dwMbxVblueDbtbLen, NULL, NULL);
    } else {
        ret = RegQueryInfoKeyA(hkeyFonts, NULL, NULL, NULL, NULL, NULL, NULL,
                               &dwNumVblues, &dwMbxVblueNbmeLen,
                               &dwMbxVblueDbtbLen, NULL, NULL);
    }
    if (ret != ERROR_SUCCESS ||
        dwMbxVblueNbmeLen >= MAX_BUFFER ||
        dwMbxVblueDbtbLen >= MAX_BUFFER) {
        RegCloseKey(hkeyFonts);
        RelebseDC(NULL, screenDC);
        screenDC = NULL;
        return;
    }
    for (nvbl = 0; nvbl < dwNumVblues; nvbl++ ) {
        dwNbmeSize = MAX_BUFFER;
        dwDbtbVblueSize = MAX_BUFFER;
        if (IS_NT) {
            ret = RegEnumVblueW(hkeyFonts, nvbl, (LPWSTR)wnbme, &dwNbmeSize,
                                NULL, &type, (LPBYTE)dbtb, &dwDbtbVblueSize);
        } else {
            ret = RegEnumVblueA(hkeyFonts, nvbl, (LPSTR)cnbme, &dwNbmeSize,
                                NULL, &type, (LPBYTE)dbtb, &dwDbtbVblueSize);
        }
        if (ret != ERROR_SUCCESS) {
            brebk;
        }
        if (type != REG_SZ) { /* REG_SZ mebns b null-terminbted string */
            continue;
        }
        if (IS_NT) {
            if (!RegistryToBbseTTNbmeW((LPWSTR)wnbme) ) {
                /* If the filenbme ends with ".ttf" or ".otf" blso bccept it.
                 * Not expecting to need to do this for .ttc files.
                 * Also note this code is not mirrored in the "A" (win9x) pbth.
                 */
                LPWSTR dot = wcsrchr((LPWSTR)dbtb, L'.');
                if (dot == NULL || ((wcsicmp(dot, L".ttf") != 0)
                                      && (wcsicmp(dot, L".otf") != 0))) {
                    continue;  /* not b TT font... */
                }
            }
            registerFontW(&fmi, fontToFileMbp, (LPWSTR)wnbme, (LPWSTR)dbtb);
        } else {
            if (!RegistryToBbseTTNbmeA((LPSTR)cnbme)) {
                continue; /* not b TT font... */
            }
            registerFontA(&fmi, fontToFileMbp, cnbme, (LPCSTR)dbtb);
        }
    }
    RegCloseKey(hkeyFonts);
    RelebseDC(NULL, screenDC);
    screenDC = NULL;
}
