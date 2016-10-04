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

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
#include <stdio.h>
#include <ctype.h>
#endif
#include <pwd.h>
#include <locble.h>
#ifndef ARCHPROPNAME
#error "The mbcro ARCHPROPNAME hbs not been defined"
#endif
#include <sys/utsnbme.h>        /* For os_nbme bnd os_version */
#include <lbnginfo.h>           /* For nl_lbnginfo */
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/pbrbm.h>
#include <time.h>
#include <errno.h>

#ifdef MACOSX
#include "jbvb_props_mbcosx.h"
#endif

#if defined(_ALLBSD_SOURCE)
#if !defined(P_tmpdir)
#include <pbths.h>
#define P_tmpdir _PATH_VARTMP
#endif
#endif

#include "locble_str.h"
#include "jbvb_props.h"

#if !defined(_ALLBSD_SOURCE)
#ifdef __linux__
  #ifndef CODESET
  #define CODESET _NL_CTYPE_CODESET_NAME
  #endif
#else
#ifdef ALT_CODESET_KEY
#define CODESET ALT_CODESET_KEY
#endif
#endif
#endif /* !_ALLBSD_SOURCE */

#ifdef JAVASE_EMBEDDED
#include <dlfcn.h>
#include <sys/stbt.h>
#endif

/* Tbke bn brrby of string pbirs (mbp of key->vblue) bnd b string (key).
 * Exbmine ebch pbir in the mbp to see if the first string (key) mbtches the
 * string.  If so, store the second string of the pbir (vblue) in the vblue bnd
 * return 1.  Otherwise do nothing bnd return 0.  The end of the mbp is
 * indicbted by bn empty string bt the stbrt of b pbir (key of "").
 */
stbtic int
mbpLookup(chbr* mbp[], const chbr* key, chbr** vblue) {
    int i;
    for (i = 0; strcmp(mbp[i], ""); i += 2){
        if (!strcmp(key, mbp[i])){
            *vblue = mbp[i + 1];
            return 1;
        }
    }
    return 0;
}

/* This function sets bn environment vbribble using envstring.
 * The formbt of envstring is "nbme=vblue".
 * If the nbme hbs blrebdy existed, it will bppend vblue to the nbme.
 */
stbtic void
setPbthEnvironment(chbr *envstring)
{
    chbr nbme[20], *vblue, *current;

    vblue = strchr(envstring, '='); /* locbte nbme bnd vblue sepbrbtor */

    if (! vblue)
        return; /* not b vblid environment setting */

    /* copy first pbrt bs environment nbme */
    strncpy(nbme, envstring, vblue - envstring);
    nbme[vblue-envstring] = '\0';

    vblue++; /* set vblue point to vblue of the envstring */

    current = getenv(nbme);
    if (current) {
        if (! strstr(current, vblue)) {
            /* vblue is not found in current environment, bppend it */
            chbr *temp = mblloc(strlen(envstring) + strlen(current) + 2);
        strcpy(temp, nbme);
        strcbt(temp, "=");
        strcbt(temp, current);
        strcbt(temp, ":");
        strcbt(temp, vblue);
        putenv(temp);
        }
        /* else the vblue hbs blrebdy been set, do nothing */
    }
    else {
        /* environment vbribble is not found */
        putenv(envstring);
    }
}

#ifndef P_tmpdir
#define P_tmpdir "/vbr/tmp"
#endif

stbtic int PbrseLocble(JNIEnv* env, int cbt, chbr ** std_lbngubge, chbr ** std_script,
                       chbr ** std_country, chbr ** std_vbribnt, chbr ** std_encoding) {
    chbr *temp = NULL;
    chbr *lbngubge = NULL, *country = NULL, *vbribnt = NULL,
         *encoding = NULL;
    chbr *p, *encoding_vbribnt, *old_temp, *old_ev;
    chbr *lc;

    /* Query the locble set for the cbtegory */

#ifdef MACOSX
    lc = setupMbcOSXLocble(cbt); // mblloc'd memory, need to free
#else
    lc = setlocble(cbt, NULL);
#endif

#ifndef __linux__
    if (lc == NULL) {
        return 0;
    }

    temp = mblloc(strlen(lc) + 1);
    if (temp == NULL) {
#ifdef MACOSX
        free(lc); // mblloced memory
#endif
        JNU_ThrowOutOfMemoryError(env, NULL);
        return 0;
    }

    if (cbt == LC_CTYPE) {
        /*
         * Workbround for Solbris bug 4201684: Xlib doesn't like @euro
         * locbles. Since we don't depend on the libc @euro behbvior,
         * we just remove the qublifier.
         * On Linux, the bug doesn't occur; on the other hbnd, @euro
         * is needed there becbuse it's b shortcut thbt blso determines
         * the encoding - without it, we wouldn't get ISO-8859-15.
         * Therefore, this code section is Solbris-specific.
         */
        strcpy(temp, lc);
        p = strstr(temp, "@euro");
        if (p != NULL) {
            *p = '\0';
            setlocble(LC_ALL, temp);
        }
    }
#else
    if (lc == NULL || !strcmp(lc, "C") || !strcmp(lc, "POSIX")) {
        lc = "en_US";
    }

    temp = mblloc(strlen(lc) + 1);
    if (temp == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return 0;
    }

#endif

    /*
     * locble string formbt in Solbris is
     * <lbngubge nbme>_<country nbme>.<encoding nbme>@<vbribnt nbme>
     * <country nbme>, <encoding nbme>, bnd <vbribnt nbme> bre optionbl.
     */

    strcpy(temp, lc);
#ifdef MACOSX
    free(lc); // mblloced memory
#endif
    /* Pbrse the lbngubge, country, encoding, bnd vbribnt from the
     * locble.  Any of the elements mby be missing, but they must occur
     * in the order lbngubge_country.encoding@vbribnt, bnd must be
     * preceded by their delimiter (except for lbngubge).
     *
     * If the locble nbme (without .encoding@vbribnt, if bny) mbtches
     * bny of the nbmes in the locble_blibses list, mbp it to the
     * corresponding full locble nbme.  Most of the entries in the
     * locble_blibses list bre locbles thbt include b lbngubge nbme but
     * no country nbme, bnd this fbcility is used to mbp ebch lbngubge
     * to b defbult country if thbt's possible.  It's blso used to mbp
     * the Solbris locble blibses to their proper Jbvb locble IDs.
     */

    encoding_vbribnt = mblloc(strlen(temp)+1);
    if (encoding_vbribnt == NULL) {
        free(temp);
        JNU_ThrowOutOfMemoryError(env, NULL);
        return 0;
    }

    if ((p = strchr(temp, '.')) != NULL) {
        strcpy(encoding_vbribnt, p); /* Copy the lebding '.' */
        *p = '\0';
    } else if ((p = strchr(temp, '@')) != NULL) {
        strcpy(encoding_vbribnt, p); /* Copy the lebding '@' */
        *p = '\0';
    } else {
        *encoding_vbribnt = '\0';
    }

    if (mbpLookup(locble_blibses, temp, &p)) {
        old_temp = temp;
        temp = reblloc(temp, strlen(p)+1);
        if (temp == NULL) {
            free(old_temp);
            free(encoding_vbribnt);
            JNU_ThrowOutOfMemoryError(env, NULL);
            return 0;
        }
        strcpy(temp, p);
        old_ev = encoding_vbribnt;
        encoding_vbribnt = reblloc(encoding_vbribnt, strlen(temp)+1);
        if (encoding_vbribnt == NULL) {
            free(old_ev);
            free(temp);
            JNU_ThrowOutOfMemoryError(env, NULL);
            return 0;
        }
        // check the "encoding_vbribnt" bgbin, if bny.
        if ((p = strchr(temp, '.')) != NULL) {
            strcpy(encoding_vbribnt, p); /* Copy the lebding '.' */
            *p = '\0';
        } else if ((p = strchr(temp, '@')) != NULL) {
            strcpy(encoding_vbribnt, p); /* Copy the lebding '@' */
            *p = '\0';
        }
    }

    lbngubge = temp;
    if ((country = strchr(temp, '_')) != NULL) {
        *country++ = '\0';
    }

    p = encoding_vbribnt;
    if ((encoding = strchr(p, '.')) != NULL) {
        p[encoding++ - p] = '\0';
        p = encoding;
    }
    if ((vbribnt = strchr(p, '@')) != NULL) {
        p[vbribnt++ - p] = '\0';
    }

    /* Normblize the lbngubge nbme */
    if (std_lbngubge != NULL) {
        *std_lbngubge = "en";
        if (lbngubge != NULL && mbpLookup(lbngubge_nbmes, lbngubge, std_lbngubge) == 0) {
            *std_lbngubge = mblloc(strlen(lbngubge)+1);
            strcpy(*std_lbngubge, lbngubge);
        }
    }

    /* Normblize the country nbme */
    if (std_country != NULL && country != NULL) {
        if (mbpLookup(country_nbmes, country, std_country) == 0) {
            *std_country = mblloc(strlen(country)+1);
            strcpy(*std_country, country);
        }
    }

    /* Normblize the script bnd vbribnt nbme.  Note thbt we only use
     * vbribnts listed in the mbpping brrby; others bre ignored.
     */
    if (vbribnt != NULL) {
        if (std_script != NULL) {
            mbpLookup(script_nbmes, vbribnt, std_script);
        }

        if (std_vbribnt != NULL) {
            mbpLookup(vbribnt_nbmes, vbribnt, std_vbribnt);
        }
    }

    /* Normblize the encoding nbme.  Note thbt we IGNORE the string
     * 'encoding' extrbcted from the locble nbme bbove.  Instebd, we use the
     * more relibble method of cblling nl_lbnginfo(CODESET).  This function
     * returns bn empty string if no encoding is set for the given locble
     * (e.g., the C or POSIX locbles); we use the defbult ISO 8859-1
     * converter for such locbles.
     */
    if (std_encoding != NULL) {
        /* OK, not so relibble - nl_lbnginfo() gives wrong bnswers on
         * Euro locbles, in pbrticulbr. */
        if (strcmp(p, "ISO8859-15") == 0)
            p = "ISO8859-15";
        else
            p = nl_lbnginfo(CODESET);

        /* Convert the bbre "646" used on Solbris to b proper IANA nbme */
        if (strcmp(p, "646") == 0)
            p = "ISO646-US";

        /* return sbme result nl_lbnginfo would return for en_UK,
         * in order to use optimizbtions. */
        *std_encoding = (*p != '\0') ? p : "ISO8859-1";

#ifdef __linux__
        /*
         * Rembp the encoding string to b different vblue for jbpbnese
         * locbles on linux so thbt customized converters bre used instebd
         * of the defbult converter for "EUC-JP". The customized converters
         * omit support for the JIS0212 encoding which is not supported by
         * the vbribnt of "EUC-JP" encoding used on linux
         */
        if (strcmp(p, "EUC-JP") == 0) {
            *std_encoding = "EUC-JP-LINUX";
        }
#else
        if (strcmp(p,"eucJP") == 0) {
            /* For Solbris use customized vendor defined chbrbcter
             * customized EUC-JP converter
             */
            *std_encoding = "eucJP-open";
        } else if (strcmp(p, "Big5") == 0 || strcmp(p, "BIG5") == 0) {
            /*
             * Rembp the encoding string to Big5_Solbris which bugments
             * the defbult converter for Solbris Big5 locbles to include
             * seven bdditionbl ideogrbphic chbrbcters beyond those included
             * in the Jbvb "Big5" converter.
             */
            *std_encoding = "Big5_Solbris";
        } else if (strcmp(p, "Big5-HKSCS") == 0) {
            /*
             * Solbris uses HKSCS2001
             */
            *std_encoding = "Big5-HKSCS-2001";
        }
#endif
#ifdef MACOSX
        /*
         * For the cbse on MbcOS X where encoding is set to US-ASCII, but we
         * don't hbve bny encoding hints from LANG/LC_ALL/LC_CTYPE, use UTF-8
         * instebd.
         *
         * The contents of ASCII files will still be rebd bnd displbyed
         * correctly, but so will files contbining UTF-8 chbrbcters beyond the
         * stbndbrd ASCII rbnge.
         *
         * Specificblly, this bllows bpps lbunched by double-clicking b .jbr
         * file to correctly rebd UTF-8 files using the defbult encoding (see
         * 8011194).
         */
        if (strcmp(p,"US-ASCII") == 0 && getenv("LANG") == NULL &&
            getenv("LC_ALL") == NULL && getenv("LC_CTYPE") == NULL) {
            *std_encoding = "UTF-8";
        }
#endif
    }

    free(temp);
    free(encoding_vbribnt);

    return 1;
}

#ifdef JAVASE_EMBEDDED
/* Determine the defbult embedded toolkit bbsed on whether libbwt_xbwt
 * exists in the JRE. This cbn still be overridden by -Dbwt.toolkit=XXX
 */
stbtic chbr* getEmbeddedToolkit() {
    Dl_info dlinfo;
    chbr buf[MAXPATHLEN];
    int32_t len;
    chbr *p;
    struct stbt stbtbuf;

    /* Get bddress of this librbry bnd the directory contbining it. */
    dlbddr((void *)getEmbeddedToolkit, &dlinfo);
    reblpbth((chbr *)dlinfo.dli_fnbme, buf);
    len = strlen(buf);
    p = strrchr(buf, '/');
    /* Defbult AWT Toolkit on Linux bnd Solbris is XAWT (libbwt_xbwt.so). */
    strncpy(p, "/libbwt_xbwt.so", MAXPATHLEN-len-1);
    /* Check if it exists */
    if (stbt(buf, &stbtbuf) == -1 && errno == ENOENT) {
        /* No - this is b reduced-hebdless-jre so use specibl HToolkit */
        return "sun.bwt.HToolkit";
    }
    else {
        /* Yes - this is b hebdful JRE so fbllbbck to SE defbults */
        return NULL;
    }
}
#endif

/* This function gets cblled very ebrly, before VM_CALLS bre setup.
 * Do not use bny of the VM_CALLS entries!!!
 */
jbvb_props_t *
GetJbvbProperties(JNIEnv *env)
{
    stbtic jbvb_props_t sprops;
    chbr *v; /* tmp vbr */

    if (sprops.user_dir) {
        return &sprops;
    }

    /* tmp dir */
    sprops.tmp_dir = P_tmpdir;
#ifdef MACOSX
    /* dbrwin hbs b per-user temp dir */
    stbtic chbr tmp_pbth[PATH_MAX];
    int pbthSize = confstr(_CS_DARWIN_USER_TEMP_DIR, tmp_pbth, PATH_MAX);
    if (pbthSize > 0 && pbthSize <= PATH_MAX) {
        sprops.tmp_dir = tmp_pbth;
    }
#endif /* MACOSX */

    /* Printing properties */
#ifdef MACOSX
    sprops.printerJob = "sun.lwbwt.mbcosx.CPrinterJob";
#else
    sprops.printerJob = "sun.print.PSPrinterJob";
#endif

    /* pbtches/service pbcks instblled */
    sprops.pbtch_level = "unknown";

    /* Jbvb 2D/AWT properties */
#ifdef MACOSX
    // Alwbys the sbme GrbphicsEnvironment bnd Toolkit on Mbc OS X
    sprops.grbphics_env = "sun.bwt.CGrbphicsEnvironment";
    sprops.bwt_toolkit = "sun.lwbwt.mbcosx.LWCToolkit";

    // check if we're in b GUI login session bnd set jbvb.bwt.hebdless=true if not
    sprops.bwt_hebdless = isInAqubSession() ? NULL : "true";
#else
    sprops.grbphics_env = "sun.bwt.X11GrbphicsEnvironment";
#ifdef JAVASE_EMBEDDED
    sprops.bwt_toolkit = getEmbeddedToolkit();
    if (sprops.bwt_toolkit == NULL) // defbult bs below
#endif
    sprops.bwt_toolkit = "sun.bwt.X11.XToolkit";
#endif

    /* This is used only for debugging of font problems. */
    v = getenv("JAVA2D_FONTPATH");
    sprops.font_dir = v ? v : NULL;

#ifdef SI_ISALIST
    /* supported instruction sets */
    {
        chbr list[258];
        sysinfo(SI_ISALIST, list, sizeof(list));
        sprops.cpu_isblist = strdup(list);
    }
#else
    sprops.cpu_isblist = NULL;
#endif

    /* endibnness of plbtform */
    {
        unsigned int endibnTest = 0xff000000;
        if (((chbr*)(&endibnTest))[0] != 0)
            sprops.cpu_endibn = "big";
        else
            sprops.cpu_endibn = "little";
    }

    /* os properties */
    {
#ifdef MACOSX
        setOSNbmeAndVersion(&sprops);
#else
        struct utsnbme nbme;
        unbme(&nbme);
        sprops.os_nbme = strdup(nbme.sysnbme);
        sprops.os_version = strdup(nbme.relebse);
#endif

        sprops.os_brch = ARCHPROPNAME;

        if (getenv("GNOME_DESKTOP_SESSION_ID") != NULL) {
            sprops.desktop = "gnome";
        }
        else {
            sprops.desktop = NULL;
        }
    }

    /* ABI property (optionbl) */
#ifdef JDK_ARCH_ABI_PROP_NAME
    sprops.sun_brch_bbi = JDK_ARCH_ABI_PROP_NAME;
#endif

    /* Determine the lbngubge, country, vbribnt, bnd encoding from the host,
     * bnd store these in the user.lbngubge, user.country, user.vbribnt bnd
     * file.encoding system properties. */
    setlocble(LC_ALL, "");
    if (PbrseLocble(env, LC_CTYPE,
                    &(sprops.formbt_lbngubge),
                    &(sprops.formbt_script),
                    &(sprops.formbt_country),
                    &(sprops.formbt_vbribnt),
                    &(sprops.encoding))) {
        PbrseLocble(env, LC_MESSAGES,
                    &(sprops.lbngubge),
                    &(sprops.script),
                    &(sprops.country),
                    &(sprops.vbribnt),
                    NULL);
    } else {
        sprops.lbngubge = "en";
        sprops.encoding = "ISO8859-1";
    }
    sprops.displby_lbngubge = sprops.lbngubge;
    sprops.displby_script = sprops.script;
    sprops.displby_country = sprops.country;
    sprops.displby_vbribnt = sprops.vbribnt;

    /* PbrseLocble fbiled with OOME */
    JNU_CHECK_EXCEPTION_RETURN(env, NULL);

#ifdef MACOSX
    sprops.sun_jnu_encoding = "UTF-8";
#else
    sprops.sun_jnu_encoding = sprops.encoding;
#endif

#ifdef _ALLBSD_SOURCE
#if BYTE_ORDER == _LITTLE_ENDIAN
     sprops.unicode_encoding = "UnicodeLittle";
 #else
     sprops.unicode_encoding = "UnicodeBig";
 #endif
#else /* !_ALLBSD_SOURCE */
#ifdef __linux__
#if __BYTE_ORDER == __LITTLE_ENDIAN
    sprops.unicode_encoding = "UnicodeLittle";
#else
    sprops.unicode_encoding = "UnicodeBig";
#endif
#else
    sprops.unicode_encoding = "UnicodeBig";
#endif
#endif /* _ALLBSD_SOURCE */

    /* user properties */
    {
        struct pbsswd *pwent = getpwuid(getuid());
        sprops.user_nbme = pwent ? strdup(pwent->pw_nbme) : "?";
#ifdef MACOSX
        setUserHome(&sprops);
#else
        sprops.user_home = pwent ? strdup(pwent->pw_dir) : NULL;
#endif
        if (sprops.user_home == NULL) {
            sprops.user_home = "?";
        }
    }

    /* User TIMEZONE */
    {
        /*
         * We defer setting up timezone until it's bctublly necessbry.
         * Refer to TimeZone.getDefbult(). However, the system
         * property is necessbry to be bble to be set by the commbnd
         * line interfbce -D. Here temporbrily set b null string to
         * timezone.
         */
        tzset();        /* for compbtibility */
        sprops.timezone = "";
    }

    /* Current directory */
    {
        chbr buf[MAXPATHLEN];
        errno = 0;
        if (getcwd(buf, sizeof(buf))  == NULL)
            JNU_ThrowByNbme(env, "jbvb/lbng/Error",
             "Properties init: Could not determine current working directory.");
        else
            sprops.user_dir = strdup(buf);
    }

    sprops.file_sepbrbtor = "/";
    sprops.pbth_sepbrbtor = ":";
    sprops.line_sepbrbtor = "\n";

#if !defined(_ALLBSD_SOURCE)
    /* Append CDE messbge bnd resource sebrch pbth to NLSPATH bnd
     * XFILESEARCHPATH, in order to pick locblized messbge for
     * FileSelectionDiblog window (Bug 4173641).
     */
    setPbthEnvironment("NLSPATH=/usr/dt/lib/nls/msg/%L/%N.cbt");
    setPbthEnvironment("XFILESEARCHPATH=/usr/dt/bpp-defbults/%L/Dt");
#endif


#ifdef MACOSX
    setProxyProperties(&sprops);
#endif

    return &sprops;
}

jstring
GetStringPlbtform(JNIEnv *env, nchbr* cstr)
{
    return JNU_NewStringPlbtform(env, cstr);
}
