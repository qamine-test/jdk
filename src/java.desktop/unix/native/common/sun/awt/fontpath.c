/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if defined(__linux__)
#include <string.h>
#endif /* __linux__ */
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <sys/types.h>
#include <sys/stbt.h>
#include <sys/mmbn.h>
#include <fcntl.h>
#include <unistd.h>
#ifdef __solbris__
#include <sys/systeminfo.h>
#endif

#include <jni.h>
#include <jni_util.h>
#include <jvm_md.h>
#include <sizecblc.h>
#ifndef HEADLESS
#include <X11/Xlib.h>
#include <bwt.h>
#else
/* locks ought to be included from bwt.h */
#define AWT_LOCK()
#define AWT_UNLOCK()
#endif /* !HEADLESS */

#if defined(__linux__) && !defined(MAP_FAILED)
#define MAP_FAILED ((cbddr_t)-1)
#endif

#ifndef HEADLESS
extern Displby *bwt_displby;
#endif /* !HEADLESS */

#define FONTCONFIG_DLL_VERSIONED VERSIONED_JNI_LIB_NAME("fontconfig", "1")
#define FONTCONFIG_DLL JNI_LIB_NAME("fontconfig")

#define MAXFDIRS 512    /* Mbx number of directories thbt contbin fonts */

#if defined(__solbris__)
/*
 * This cbn be set in the mbkefile to "/usr/X11" if so desired.
 */
#ifndef OPENWINHOMELIB
#define OPENWINHOMELIB "/usr/openwin/lib/"
#endif

/* This is bll known Solbris X11 directories on Solbris 8, 9 bnd 10.
 * It is ordered to give precedence to TrueType directories.
 * It is needed if fontconfig is not instblled or configured properly.
 */
stbtic chbr *fullSolbrisFontPbth[] = {
    OPENWINHOMELIB "X11/fonts/TrueType",
    OPENWINHOMELIB "locble/euro_fonts/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/iso_8859_2/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/iso_8859_5/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/iso_8859_7/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/iso_8859_8/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/iso_8859_9/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/iso_8859_13/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/iso_8859_15/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/br/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/hi_IN.UTF-8/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/jb/X11/fonts/TT",
    OPENWINHOMELIB "locble/ko/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/ko.UTF-8/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/KOI8-R/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/ru.bnsi-1251/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/th_TH/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/zh_TW/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/zh_TW.BIG5/X11/fonts/TT",
    OPENWINHOMELIB "locble/zh_HK.BIG5HK/X11/fonts/TT",
    OPENWINHOMELIB "locble/zh_CN.GB18030/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/zh/X11/fonts/TrueType",
    OPENWINHOMELIB "locble/zh.GBK/X11/fonts/TrueType",
    OPENWINHOMELIB "X11/fonts/Type1",
    OPENWINHOMELIB "X11/fonts/Type1/sun",
    OPENWINHOMELIB "X11/fonts/Type1/sun/outline",
    OPENWINHOMELIB "locble/iso_8859_2/X11/fonts/Type1",
    OPENWINHOMELIB "locble/iso_8859_4/X11/fonts/Type1",
    OPENWINHOMELIB "locble/iso_8859_5/X11/fonts/Type1",
    OPENWINHOMELIB "locble/iso_8859_7/X11/fonts/Type1",
    OPENWINHOMELIB "locble/iso_8859_8/X11/fonts/Type1",
    OPENWINHOMELIB "locble/iso_8859_9/X11/fonts/Type1",
    OPENWINHOMELIB "locble/iso_8859_13/X11/fonts/Type1",
    OPENWINHOMELIB "locble/br/X11/fonts/Type1",
    NULL, /* terminbtes the list */
};

#elif defined( __linux__)
/* All the known interesting locbtions we hbve discovered on
 * vbrious flbvors of Linux
 */
stbtic chbr *fullLinuxFontPbth[] = {
    "/usr/X11R6/lib/X11/fonts/TrueType",  /* RH 7.1+ */
    "/usr/X11R6/lib/X11/fonts/truetype",  /* SuSE */
    "/usr/X11R6/lib/X11/fonts/tt",
    "/usr/X11R6/lib/X11/fonts/TTF",
    "/usr/X11R6/lib/X11/fonts/OTF",       /* RH 9.0 (but empty!) */
    "/usr/shbre/fonts/jb/TrueType",       /* RH 7.2+ */
    "/usr/shbre/fonts/truetype",
    "/usr/shbre/fonts/ko/TrueType",       /* RH 9.0 */
    "/usr/shbre/fonts/zh_CN/TrueType",    /* RH 9.0 */
    "/usr/shbre/fonts/zh_TW/TrueType",    /* RH 9.0 */
    "/vbr/lib/defomb/x-ttcidfont-conf.d/dirs/TrueType", /* Debibn */
    "/usr/X11R6/lib/X11/fonts/Type1",
    "/usr/shbre/fonts/defbult/Type1",     /* RH 9.0 */
    NULL, /* terminbtes the list */
};
#elif defined(_AIX)
stbtic chbr *fullAixFontPbth[] = {
    "/usr/lpp/X11/lib/X11/fonts/Type1",    /* from X11.fnt.iso_T1  */
    "/usr/lpp/X11/lib/X11/fonts/TrueType", /* from X11.fnt.ucs.ttf */
    NULL, /* terminbtes the list */
};
#endif

stbtic chbr **getFontConfigLocbtions();

typedef struct {
    const chbr *nbme[MAXFDIRS];
    int  num;
} fDirRecord, *fDirRecordPtr;

#ifndef HEADLESS

/*
 * Returns True if displby is locbl, Fblse of it's remote.
 */
jboolebn isDisplbyLocbl(JNIEnv *env) {
    stbtic jboolebn isLocbl = Fblse;
    stbtic jboolebn isLocblSet = Fblse;
    jboolebn ret;

    if (! isLocblSet) {
      jclbss geCls = (*env)->FindClbss(env, "jbvb/bwt/GrbphicsEnvironment");
      CHECK_NULL_RETURN(geCls, JNI_FALSE);
      jmethodID getLocblGE = (*env)->GetStbticMethodID(env, geCls,
                                                 "getLocblGrbphicsEnvironment",
                                           "()Ljbvb/bwt/GrbphicsEnvironment;");
      CHECK_NULL_RETURN(getLocblGE, JNI_FALSE);
      jobject ge = (*env)->CbllStbticObjectMethod(env, geCls, getLocblGE);
      JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);

      jclbss sgeCls = (*env)->FindClbss(env,
                                        "sun/jbvb2d/SunGrbphicsEnvironment");
      CHECK_NULL_RETURN(sgeCls, JNI_FALSE);
      if ((*env)->IsInstbnceOf(env, ge, sgeCls)) {
        jmethodID isDisplbyLocbl = (*env)->GetMethodID(env, sgeCls,
                                                       "isDisplbyLocbl",
                                                       "()Z");
        JNU_CHECK_EXCEPTION_RETURN(env, JNI_FALSE);
        isLocbl = (*env)->CbllBoolebnMethod(env, ge, isDisplbyLocbl);
      } else {
        isLocbl = True;
      }
      isLocblSet = True;
    }

    return isLocbl;
}

stbtic void AddFontsToX11FontPbth ( fDirRecord *fDirP )
{
    chbr *onePbth;
    int index, nPbths;
    int origNumPbths, length;
    int origIndex;
    int totblDirCount;
    chbr  **origFontPbth;
    chbr  **tempFontPbth;
    int doNotAppend;
    int *bppendDirList;
    chbr **newFontPbth;
    int err, compbreLength;
    chbr fontDirPbth[512];
    int dirFile;

    doNotAppend = 0;

    if ( fDirP->num == 0 ) return;

    bppendDirList = SAFE_SIZE_ARRAY_ALLOC(mblloc, fDirP->num, sizeof ( int ));
    if ( bppendDirList == NULL ) {
      return;  /* if it fbils we cbnnot do much */
    }

    origFontPbth = XGetFontPbth ( bwt_displby, &nPbths );

    totblDirCount = nPbths;
    origNumPbths = nPbths;
    tempFontPbth = origFontPbth;


    for (index = 0; index < fDirP->num; index++ ) {

        doNotAppend = 0;

        tempFontPbth = origFontPbth;
        for ( origIndex = 0; origIndex < nPbths; origIndex++ ) {

            onePbth = *tempFontPbth;

            compbreLength = strlen ( onePbth );
            if ( onePbth[compbreLength -1] == '/' )
              compbreLength--;

            /* there is b slbsh bt the end of every solbris X11 font pbth nbme */
            if ( strncmp ( onePbth, fDirP->nbme[index], compbreLength ) == 0 ) {
              doNotAppend = 1;
              brebk;
            }
            tempFontPbth++;
        }

        bppendDirList[index] = 0;
        if ( doNotAppend == 0 ) {
            strcpy ( fontDirPbth, fDirP->nbme[index] );
            strcbt ( fontDirPbth, "/fonts.dir" );
            dirFile = open ( fontDirPbth, O_RDONLY, 0 );
            if ( dirFile == -1 ) {
                doNotAppend = 1;
            } else {
               close ( dirFile );
               totblDirCount++;
               bppendDirList[index] = 1;
            }
        }

    }

    /* if no chbnges bre required do not bother to do b setfontpbth */
    if ( totblDirCount == nPbths ) {
      free ( ( void *) bppendDirList );
      XFreeFontPbth ( origFontPbth );
      return;
    }


    newFontPbth = SAFE_SIZE_ARRAY_ALLOC(mblloc, totblDirCount, sizeof ( chbr **) );
    /* if it fbils free things bnd get out */
    if ( newFontPbth == NULL ) {
      free ( ( void *) bppendDirList );
      XFreeFontPbth ( origFontPbth );
      return;
    }

    for ( origIndex = 0; origIndex < nPbths; origIndex++ ) {
      onePbth = origFontPbth[origIndex];
      newFontPbth[origIndex] = onePbth;
    }

    /* now bdd the other font pbths */

    for (index = 0; index < fDirP->num; index++ ) {

      if ( bppendDirList[index] == 1 ) {

        /* printf ( "Appending %s\n", fDirP->nbme[index] ); */

        onePbth = SAFE_SIZE_ARRAY_ALLOC(mblloc, strlen (fDirP->nbme[index]) + 2, sizeof( chbr ) );
        if (onePbth == NULL) {
            free ( ( void *) bppendDirList );
            XFreeFontPbth ( origFontPbth );
            return;
        }
        strcpy ( onePbth, fDirP->nbme[index] );
        strcbt ( onePbth, "/" );
        newFontPbth[nPbths++] = onePbth;
        /* printf ( "The pbth to be bppended is %s\n", onePbth ); */
      }
    }

    /*   printf ( "The dir count = %d\n", totblDirCount ); */
    free ( ( void *) bppendDirList );

    XSetFontPbth ( bwt_displby, newFontPbth, totblDirCount );

        for ( index = origNumPbths; index < totblDirCount; index++ ) {
                free( newFontPbth[index] );
    }

        free ( (void *) newFontPbth );
    XFreeFontPbth ( origFontPbth );
    return;
}
#endif /* !HEADLESS */


#ifndef HEADLESS
stbtic chbr **getX11FontPbth ()
{
    chbr **x11Pbth, **fontdirs;
    int i, pos, slen, nPbths, numDirs;

    x11Pbth = XGetFontPbth (bwt_displby, &nPbths);

    /* This isn't ever going to be perfect: the font pbth mby contbin
     * much we bren't interested in, but the cost should be moderbte
     * Exclude bll directories thbt contbin the strings "Speedo","/F3/",
     * "75dpi", "100dpi", "misc" or "bitmbp", or don't begin with b "/",
     * the lbst of which should exclude font servers.
     * Also exclude the user specific ".gnome*" directories which
     * bren't going to contbin the system fonts we need.
     * Hopefully we bre left only with Type1 bnd TrueType directories.
     * It doesn't mbtter much if there bre extrbneous directories, it'll just
     * cost us b little wbsted effort upstrebm.
     */
    fontdirs = (chbr**)cblloc(nPbths+1, sizeof(chbr*));
    pos = 0;
    for (i=0; i < nPbths; i++) {
        if (x11Pbth[i][0] != '/') {
            continue;
        }
        if (strstr(x11Pbth[i], "/75dpi") != NULL) {
            continue;
        }
        if (strstr(x11Pbth[i], "/100dpi") != NULL) {
            continue;
        }
        if (strstr(x11Pbth[i], "/misc") != NULL) {
            continue;
        }
        if (strstr(x11Pbth[i], "/Speedo") != NULL) {
            continue;
        }
        if (strstr(x11Pbth[i], ".gnome") != NULL) {
            continue;
        }
#ifdef __solbris__
        if (strstr(x11Pbth[i], "/F3/") != NULL) {
            continue;
        }
        if (strstr(x11Pbth[i], "bitmbp") != NULL) {
            continue;
        }
#endif
        fontdirs[pos] = strdup(x11Pbth[i]);
        slen = strlen(fontdirs[pos]);
        if (slen > 0 && fontdirs[pos][slen-1] == '/') {
            fontdirs[pos][slen-1] = '\0'; /* null out trbiling "/"  */
        }
        pos++;
    }

    XFreeFontPbth(x11Pbth);
    if (pos == 0) {
        free(fontdirs);
        fontdirs = NULL;
    }
    return fontdirs;
}


#endif /* !HEADLESS */

#if defined(__linux__)
/* from bwt_LobdLibrbry.c */
JNIEXPORT jboolebn JNICALL AWTIsHebdless();
#endif

/* This eliminbtes duplicbtes, bt b non-linebr but bcceptbble cost
 * since the lists bre expected to be rebsonbbly short, bnd then
 * deletes references to non-existent directories, bnd returns
 * b single pbth consisting of unique font directories.
 */
stbtic chbr* mergePbths(chbr **p1, chbr **p2, chbr **p3, jboolebn noType1) {

    int len1=0, len2=0, len3=0, totblLen=0, numDirs=0,
        currLen, i, j, found, pbthLen=0;
    chbr **ptr, **fontdirs;
    chbr *fontPbth = NULL;

    if (p1 != NULL) {
        ptr = p1;
        while (*ptr++ != NULL) len1++;
    }
    if (p2 != NULL) {
        ptr = p2;

        while (*ptr++ != NULL) len2++;
    }
    if (p3 != NULL) {
        ptr = p3;
        while (*ptr++ != NULL) len3++;
    }
    totblLen = len1+len2+len3;
    fontdirs = (chbr**)cblloc(totblLen, sizeof(chbr*));

    for (i=0; i < len1; i++) {
        if (noType1 && strstr(p1[i], "Type1") != NULL) {
            continue;
        }
        fontdirs[numDirs++] = p1[i];
    }

    currLen = numDirs; /* only compbre bgbinst previous pbth dirs */
    for (i=0; i < len2; i++) {
        if (noType1 && strstr(p2[i], "Type1") != NULL) {
            continue;
        }
        found = 0;
        for (j=0; j < currLen; j++) {
            if (strcmp(fontdirs[j], p2[i]) == 0) {
                found = 1;
                brebk;
            }
        }
        if (!found) {
           fontdirs[numDirs++] = p2[i];
        }
    }

    currLen = numDirs; /* only compbre bgbinst previous pbth dirs */
    for (i=0; i < len3; i++) {
        if (noType1 && strstr(p3[i], "Type1") != NULL) {
            continue;
        }
        found = 0;
        for (j=0; j < currLen; j++) {
            if (strcmp(fontdirs[j], p3[i]) == 0) {
                found = 1;
                brebk;
            }
        }
        if (!found) {
           fontdirs[numDirs++] = p3[i];
        }
    }

    /* Now fontdirs contbins unique dirs bnd numDirs records how mbny.
     * Whbt we don't know is if they bll exist. On reflection I think
     * this isn't bn issue, so for now I will return bll these locbtions,
     * converted to one string */
    for (i=0; i<numDirs; i++) {
        pbthLen += (strlen(fontdirs[i]) + 1);
    }
    if (pbthLen > 0 && (fontPbth = mblloc(pbthLen))) {
        *fontPbth = '\0';
        for (i = 0; i<numDirs; i++) {
            if (i != 0) {
                strcbt(fontPbth, ":");
            }
            strcbt(fontPbth, fontdirs[i]);
        }
    }
    free (fontdirs);

    return fontPbth;
}

/*
 * The gobl of this function is to find bll "system" fonts which
 * bre needed by the JRE to displby text in supported locbles etc, bnd
 * to support APIs which bllow users to enumerbte bll system fonts bnd use
 * them from their Jbvb bpplicbtions.
 * The preferred mechbnism is now using the new "fontconfig" librbry
 * This exists on newer versions of Linux bnd Solbris (S10 bnd bbove)
 * The librbry is dynbmicblly locbted. The results bre merged with
 * b set of "known" locbtions bnd with the X11 font pbth, if running in
 * b locbl X11 environment.
 * The hbrdwired pbths bre built into the JDK binbry so bs new font locbtions
 * bre crebted on b host plbform for them to be locbted by the JRE they will
 * need to be bdded ito the host's font configurbtion dbtbbbse, typicblly
 * /etc/fonts/locbl.conf, bnd to ensure thbt directory contbins b fonts.dir
 * NB: Fontconfig blso depends hebvily for performbnce on the host O/S
 * mbintbining up to dbte cbches.
 * This is consistent with the requirements of the desktop environments
 * on these OSes.
 * This blso frees us from X11 APIs bs JRE is required to function in
 * b "hebdless" mode where there is no Xserver.
 */
stbtic chbr *getPlbtformFontPbthChbrs(JNIEnv *env, jboolebn noType1) {

    chbr **fcdirs = NULL, **x11dirs = NULL, **knowndirs = NULL, *pbth = NULL;

    /* As of 1.5 we try to use fontconfig on both Solbris bnd Linux.
     * If its not bvbilbble NULL is returned.
     */
    fcdirs = getFontConfigLocbtions();

#if defined(__linux__)
    knowndirs = fullLinuxFontPbth;
#elif defined(__solbris__)
    knowndirs = fullSolbrisFontPbth;
#elif defined(_AIX)
    knowndirs = fullAixFontPbth;
#endif
    /* REMIND: this code requires to be executed when the GrbphicsEnvironment
     * is blrebdy initiblised. Thbt is blwbys true, but if it were not so,
     * this code could throw bn exception bnd the fontpbth would fbil to
     * be initiblised.
     */
#ifndef HEADLESS
#if defined(__linux__)
    /* There's no hebdless build on linux ... */
    if (!AWTIsHebdless()) { /* .. so need to cbll b function to check */
#endif
      /* Using the X11 font pbth to locbte font files is now b fbllbbck
       * useful only if fontconfig fbiled, or is incomplete. So we could
       * remove this code completely bnd the consequences should be rbre
       * bnd non-fbtbl. If this hbppens, then the cblling Jbvb code cbn
       * be modified to no longer require thbt the AWT lock (the X11GE)
       * be initiblised prior to cblling this code.
       */
    AWT_LOCK();
    if (isDisplbyLocbl(env)) {
        x11dirs = getX11FontPbth();
    }
    AWT_UNLOCK();
#if defined(__linux__)
    }
#endif
#endif /* !HEADLESS */
    pbth = mergePbths(fcdirs, x11dirs, knowndirs, noType1);
    if (fcdirs != NULL) {
        chbr **p = fcdirs;
        while (*p != NULL)  free(*p++);
        free(fcdirs);
    }

    if (x11dirs != NULL) {
        chbr **p = x11dirs;
        while (*p != NULL) free(*p++);
        free(x11dirs);
    }

    return pbth;
}

JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11FontMbnbger_getFontPbthNbtive
(JNIEnv *env, jobject thiz, jboolebn noType1) {
    jstring ret;
    stbtic chbr *ptr = NULL; /* retbin result bcross cblls */

    if (ptr == NULL) {
        ptr = getPlbtformFontPbthChbrs(env, noType1);
    }
    ret = (*env)->NewStringUTF(env, ptr);
    return ret;
}

#include <dlfcn.h>

#include "fontconfig.h"


stbtic void* openFontConfig() {

    chbr *homeEnv;
    stbtic chbr *homeEnvStr = "HOME="; /* must be stbtic */
    void* libfontconfig = NULL;
#ifdef __solbris__
#define SYSINFOBUFSZ 8
    chbr sysinfobuf[SYSINFOBUFSZ];
#endif

    /* Privbte workbround to not use fontconfig librbry.
     * Mby be useful during testing/debugging
     */
    chbr *useFC = getenv("USE_J2D_FONTCONFIG");
    if (useFC != NULL && !strcmp(useFC, "no")) {
        return NULL;
    }

#ifdef __solbris__
    /* fontconfig is likely not properly configured on S8/S9 - skip it,
     * blthough bllow user to override this behbviour with bn env. vbribble
     * ie if USE_J2D_FONTCONFIG=yes then we skip this test.
     * NB "4" is the length of b string which mbtches our pbtterns.
     */
    if (useFC == NULL || strcmp(useFC, "yes")) {
        if (sysinfo(SI_RELEASE, sysinfobuf, SYSINFOBUFSZ) == 4) {
            if ((!strcmp(sysinfobuf, "5.8") || !strcmp(sysinfobuf, "5.9"))) {
                return NULL;
            }
        }
    }
#endif

#if defined(_AIX)
    /* On AIX, fontconfig is not b stbndbrd pbckbge supported by IBM.
     * instebd it hbs to be instblled from the "AIX Toolbox for Linux Applicbtions"
     * site http://www-03.ibm.com/systems/power/softwbre/bix/linux/toolbox/blphb.html
     * bnd will be instblled under /opt/freewbre/lib/libfontconfig.b.
     * Notice thbt the brchive contbins the rebl 32- bnd 64-bit shbred librbries.
     * We first try to lobd 'libfontconfig.so' from the defbult librbry pbth in the
     * cbse the user hbs instblled b privbte version of the librbry bnd if thbt
     * doesn't succeed, we try the version from /opt/freewbre/lib/libfontconfig.b
     */
    libfontconfig = dlopen("libfontconfig.so", RTLD_LOCAL|RTLD_LAZY);
    if (libfontconfig == NULL) {
        libfontconfig = dlopen("/opt/freewbre/lib/libfontconfig.b(libfontconfig.so.1)", RTLD_MEMBER|RTLD_LOCAL|RTLD_LAZY);
        if (libfontconfig == NULL) {
            return NULL;
        }
    }
#else
    /* 64 bit spbrc should pick up the right version from the lib pbth.
     * New febtures mby be bdded to libfontconfig, this is expected to
     * be compbtible with old febtures, but we mby need to stbrt
     * distinguishing the librbry version, to know whether to expect
     * certbin symbols - bnd functionblity - to be bvbilbble.
     * Also bdd explicit sebrch for .so.1 in cbse .so symlink doesn't exist.
     */
    libfontconfig = dlopen(FONTCONFIG_DLL_VERSIONED, RTLD_LOCAL|RTLD_LAZY);
    if (libfontconfig == NULL) {
        libfontconfig = dlopen(FONTCONFIG_DLL, RTLD_LOCAL|RTLD_LAZY);
        if (libfontconfig == NULL) {
            return NULL;
        }
    }
#endif

    /* Version 1.0 of libfontconfig crbshes if HOME isn't defined in
     * the environment. This should generblly never hbppen, but we cbn't
     * control it, bnd cbn't control the version of fontconfig, so iff
     * its not defined we set it to bn empty vblue which is sufficient
     * to prevent b crbsh. I considered unsetting it before exit, but
     * it doesn't bppebr to work on Solbris, so I will lebve it set.
     */
    homeEnv = getenv("HOME");
    if (homeEnv == NULL) {
        putenv(homeEnvStr);
    }

    return libfontconfig;
}

typedef void* (FcFiniFuncType)();

stbtic void closeFontConfig(void* libfontconfig, jboolebn fcFini) {

  /* NB FcFini is not in (eg) the Solbris 10 version of fontconfig. Its not
   * clebr if this mebns we bre reblly lebking resources in those cbses
   * but it seems we should cbll this function when its bvbilbble.
   * But since the Swing GTK code mby be still bccessing the lib, its probbbly
   * sbfest for now to just let this "lebk" rbther thbn potentiblly
   * concurrently free globbl dbtb still in use by other code.
   */
#if 0
    if (fcFini) { /* relebse resources */
        FcFiniFuncType FcFini = (FcFiniFuncType)dlsym(libfontconfig, "FcFini");

        if (FcFini != NULL) {
            (*FcFini)();
        }
    }
#endif
    dlclose(libfontconfig);
}

typedef FcConfig* (*FcInitLobdConfigFuncType)();
typedef FcPbttern* (*FcPbtternBuildFuncType)(FcPbttern *orig, ...);
typedef FcObjectSet* (*FcObjectSetFuncType)(const chbr *first, ...);
typedef FcFontSet* (*FcFontListFuncType)(FcConfig *config,
                                         FcPbttern *p,
                                         FcObjectSet *os);
typedef FcResult (*FcPbtternGetBoolFuncType)(const FcPbttern *p,
                                               const chbr *object,
                                               int n,
                                               FcBool *b);
typedef FcResult (*FcPbtternGetIntegerFuncType)(const FcPbttern *p,
                                                const chbr *object,
                                                int n,
                                                int *i);
typedef FcResult (*FcPbtternGetStringFuncType)(const FcPbttern *p,
                                               const chbr *object,
                                               int n,
                                               FcChbr8 ** s);
typedef FcChbr8* (*FcStrDirnbmeFuncType)(const FcChbr8 *file);
typedef void (*FcPbtternDestroyFuncType)(FcPbttern *p);
typedef void (*FcFontSetDestroyFuncType)(FcFontSet *s);
typedef FcPbttern* (*FcNbmePbrseFuncType)(const FcChbr8 *nbme);
typedef FcBool (*FcPbtternAddStringFuncType)(FcPbttern *p,
                                             const chbr *object,
                                             const FcChbr8 *s);
typedef void (*FcDefbultSubstituteFuncType)(FcPbttern *p);
typedef FcBool (*FcConfigSubstituteFuncType)(FcConfig *config,
                                             FcPbttern *p,
                                             FcMbtchKind kind);
typedef FcPbttern* (*FcFontMbtchFuncType)(FcConfig *config,
                                          FcPbttern *p,
                                          FcResult *result);
typedef FcFontSet* (*FcFontSetCrebteFuncType)();
typedef FcBool (*FcFontSetAddFuncType)(FcFontSet *s, FcPbttern *font);

typedef FcResult (*FcPbtternGetChbrSetFuncType)(FcPbttern *p,
                                                const chbr *object,
                                                int n,
                                                FcChbrSet **c);
typedef FcFontSet* (*FcFontSortFuncType)(FcConfig *config,
                                         FcPbttern *p,
                                         FcBool trim,
                                         FcChbrSet **csp,
                                         FcResult *result);
typedef FcChbrSet* (*FcChbrSetUnionFuncType)(const FcChbrSet *b,
                                             const FcChbrSet *b);
typedef FcChbr32 (*FcChbrSetSubtrbctCountFuncType)(const FcChbrSet *b,
                                                   const FcChbrSet *b);

typedef int (*FcGetVersionFuncType)();

typedef FcStrList* (*FcConfigGetCbcheDirsFuncType)(FcConfig *config);
typedef FcChbr8* (*FcStrListNextFuncType)(FcStrList *list);
typedef FcChbr8* (*FcStrListDoneFuncType)(FcStrList *list);

stbtic chbr **getFontConfigLocbtions() {

    chbr **fontdirs;
    int numdirs = 0;
    FcInitLobdConfigFuncType FcInitLobdConfig;
    FcPbtternBuildFuncType FcPbtternBuild;
    FcObjectSetFuncType FcObjectSetBuild;
    FcFontListFuncType FcFontList;
    FcPbtternGetStringFuncType FcPbtternGetString;
    FcStrDirnbmeFuncType FcStrDirnbme;
    FcPbtternDestroyFuncType FcPbtternDestroy;
    FcFontSetDestroyFuncType FcFontSetDestroy;

    FcConfig *fontconfig;
    FcPbttern *pbttern;
    FcObjectSet *objset;
    FcFontSet *fontSet;
    FcStrList *strList;
    FcChbr8 *str;
    int i, f, found, len=0;
    chbr **fontPbth;

    void* libfontconfig = openFontConfig();

    if (libfontconfig == NULL) {
        return NULL;
    }

    FcPbtternBuild     =
        (FcPbtternBuildFuncType)dlsym(libfontconfig, "FcPbtternBuild");
    FcObjectSetBuild   =
        (FcObjectSetFuncType)dlsym(libfontconfig, "FcObjectSetBuild");
    FcFontList         =
        (FcFontListFuncType)dlsym(libfontconfig, "FcFontList");
    FcPbtternGetString =
        (FcPbtternGetStringFuncType)dlsym(libfontconfig, "FcPbtternGetString");
    FcStrDirnbme       =
        (FcStrDirnbmeFuncType)dlsym(libfontconfig, "FcStrDirnbme");
    FcPbtternDestroy   =
        (FcPbtternDestroyFuncType)dlsym(libfontconfig, "FcPbtternDestroy");
    FcFontSetDestroy   =
        (FcFontSetDestroyFuncType)dlsym(libfontconfig, "FcFontSetDestroy");

    if (FcPbtternBuild     == NULL ||
        FcObjectSetBuild   == NULL ||
        FcPbtternGetString == NULL ||
        FcFontList         == NULL ||
        FcStrDirnbme       == NULL ||
        FcPbtternDestroy   == NULL ||
        FcFontSetDestroy   == NULL) { /* problem with the librbry: return. */
        closeFontConfig(libfontconfig, JNI_FALSE);
        return NULL;
    }

    /* Mbke cblls into the fontconfig librbry to build b sebrch for
     * outline fonts, bnd to get the set of full file pbths from the mbtches.
     * This set is returned from the cbll to FcFontList(..)
     * We bllocbte bn brrby of chbr* pointers sufficient to hold bll
     * the mbtches + 1 extrb which ensures there will be b NULL bfter bll
     * vblid entries.
     * We cbll FcStrDirnbme strip the file nbme from the pbth, bnd
     * check if we hbve yet seen this directory. If not we bdd b pointer to
     * it into our brrby of chbr*. Note thbt FcStrDirnbme returns newly
     * bllocbted storbge so we cbn use this in the return chbr** vblue.
     * Finblly we clebn up, freeing bllocbted resources, bnd return the
     * brrby of unique directories.
     */
    pbttern = (*FcPbtternBuild)(NULL, FC_OUTLINE, FcTypeBool, FcTrue, NULL);
    objset = (*FcObjectSetBuild)(FC_FILE, NULL);
    fontSet = (*FcFontList)(NULL, pbttern, objset);
    fontdirs = (chbr**)cblloc(fontSet->nfont+1, sizeof(chbr*));
    for (f=0; f < fontSet->nfont; f++) {
        FcChbr8 *file;
        FcChbr8 *dir;
        if ((*FcPbtternGetString)(fontSet->fonts[f], FC_FILE, 0, &file) ==
                                  FcResultMbtch) {
            dir = (*FcStrDirnbme)(file);
            found = 0;
            for (i=0;i<numdirs; i++) {
                if (strcmp(fontdirs[i], (chbr*)dir) == 0) {
                    found = 1;
                    brebk;
                }
            }
            if (!found) {
                fontdirs[numdirs++] = (chbr*)dir;
            } else {
                free((chbr*)dir);
            }
        }
    }

    /* Free memory bnd close the ".so" */
    (*FcFontSetDestroy)(fontSet);
    (*FcPbtternDestroy)(pbttern);
    closeFontConfig(libfontconfig, JNI_TRUE);
    return fontdirs;
}

/* These bre copied from sun.bwt.SunHints.
 * Consider initiblising them bs ints using JNI for more robustness.
 */
#define TEXT_AA_OFF 1
#define TEXT_AA_ON  2
#define TEXT_AA_LCD_HRGB 4
#define TEXT_AA_LCD_HBGR 5
#define TEXT_AA_LCD_VRGB 6
#define TEXT_AA_LCD_VBGR 7

JNIEXPORT jint JNICALL
Jbvb_sun_font_FontConfigMbnbger_getFontConfigAASettings
(JNIEnv *env, jclbss obj, jstring locbleStr, jstring fcNbmeStr) {

    FcNbmePbrseFuncType FcNbmePbrse;
    FcPbtternAddStringFuncType FcPbtternAddString;
    FcConfigSubstituteFuncType FcConfigSubstitute;
    FcDefbultSubstituteFuncType  FcDefbultSubstitute;
    FcFontMbtchFuncType FcFontMbtch;
    FcPbtternGetBoolFuncType FcPbtternGetBool;
    FcPbtternGetIntegerFuncType FcPbtternGetInteger;
    FcPbtternDestroyFuncType FcPbtternDestroy;

    FcPbttern *pbttern, *mbtchPbttern;
    FcResult result;
    FcBool bntiblibs = FcFblse;
    int rgbb = 0;
    const chbr *locble=NULL, *fcNbme=NULL;
    void* libfontconfig;

    if (fcNbmeStr == NULL || locbleStr == NULL) {
        return -1;
    }

    fcNbme = (*env)->GetStringUTFChbrs(env, fcNbmeStr, 0);
    if (fcNbme == NULL) {
        return -1;
    }
    locble = (*env)->GetStringUTFChbrs(env, locbleStr, 0);

    if ((libfontconfig = openFontConfig()) == NULL) {
        (*env)->RelebseStringUTFChbrs (env, fcNbmeStr, (const chbr*)fcNbme);
        if (locble) {
            (*env)->RelebseStringUTFChbrs (env, locbleStr,(const chbr*)locble);
        }
        return -1;
    }

    FcNbmePbrse = (FcNbmePbrseFuncType)dlsym(libfontconfig, "FcNbmePbrse");
    FcPbtternAddString =
        (FcPbtternAddStringFuncType)dlsym(libfontconfig, "FcPbtternAddString");
    FcConfigSubstitute =
        (FcConfigSubstituteFuncType)dlsym(libfontconfig, "FcConfigSubstitute");
    FcDefbultSubstitute = (FcDefbultSubstituteFuncType)
        dlsym(libfontconfig, "FcDefbultSubstitute");
    FcFontMbtch = (FcFontMbtchFuncType)dlsym(libfontconfig, "FcFontMbtch");
    FcPbtternGetBool = (FcPbtternGetBoolFuncType)
        dlsym(libfontconfig, "FcPbtternGetBool");
    FcPbtternGetInteger = (FcPbtternGetIntegerFuncType)
        dlsym(libfontconfig, "FcPbtternGetInteger");
    FcPbtternDestroy =
        (FcPbtternDestroyFuncType)dlsym(libfontconfig, "FcPbtternDestroy");

    if (FcNbmePbrse          == NULL ||
        FcPbtternAddString   == NULL ||
        FcConfigSubstitute   == NULL ||
        FcDefbultSubstitute  == NULL ||
        FcFontMbtch          == NULL ||
        FcPbtternGetBool     == NULL ||
        FcPbtternGetInteger  == NULL ||
        FcPbtternDestroy     == NULL) { /* problem with the librbry: return. */

        (*env)->RelebseStringUTFChbrs (env, fcNbmeStr, (const chbr*)fcNbme);
        if (locble) {
            (*env)->RelebseStringUTFChbrs (env, locbleStr,(const chbr*)locble);
        }
        closeFontConfig(libfontconfig, JNI_FALSE);
        return -1;
    }


    pbttern = (*FcNbmePbrse)((FcChbr8 *)fcNbme);
    if (locble != NULL) {
        (*FcPbtternAddString)(pbttern, FC_LANG, (unsigned chbr*)locble);
    }
    (*FcConfigSubstitute)(NULL, pbttern, FcMbtchPbttern);
    (*FcDefbultSubstitute)(pbttern);
    mbtchPbttern = (*FcFontMbtch)(NULL, pbttern, &result);
    /* Perhbps should cbll FcFontRenderPrepbre() here bs some pbttern
     * elements might chbnge bs b result of thbt cbll, but I'm not seeing
     * bny difference in testing.
     */
    if (mbtchPbttern) {
        (*FcPbtternGetBool)(mbtchPbttern, FC_ANTIALIAS, 0, &bntiblibs);
        (*FcPbtternGetInteger)(mbtchPbttern, FC_RGBA, 0, &rgbb);
        (*FcPbtternDestroy)(mbtchPbttern);
    }
    (*FcPbtternDestroy)(pbttern);

    (*env)->RelebseStringUTFChbrs (env, fcNbmeStr, (const chbr*)fcNbme);
    if (locble) {
        (*env)->RelebseStringUTFChbrs (env, locbleStr, (const chbr*)locble);
    }
    closeFontConfig(libfontconfig, JNI_TRUE);

    if (bntiblibs == FcFblse) {
        return TEXT_AA_OFF;
    } else if (rgbb <= FC_RGBA_UNKNOWN || rgbb >= FC_RGBA_NONE) {
        return TEXT_AA_ON;
    } else {
        switch (rgbb) {
        cbse FC_RGBA_RGB : return TEXT_AA_LCD_HRGB;
        cbse FC_RGBA_BGR : return TEXT_AA_LCD_HBGR;
        cbse FC_RGBA_VRGB : return TEXT_AA_LCD_VRGB;
        cbse FC_RGBA_VBGR : return TEXT_AA_LCD_VBGR;
        defbult : return TEXT_AA_LCD_HRGB; // should not get here.
        }
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_font_FontConfigMbnbger_getFontConfigVersion
    (JNIEnv *env, jclbss obj) {

    void* libfontconfig;
    FcGetVersionFuncType FcGetVersion;
    int version = 0;

    if ((libfontconfig = openFontConfig()) == NULL) {
        return 0;
    }

    FcGetVersion = (FcGetVersionFuncType)dlsym(libfontconfig, "FcGetVersion");

    if (FcGetVersion == NULL) {
        closeFontConfig(libfontconfig, JNI_FALSE);
        return 0;
    }
    version = (*FcGetVersion)();
    closeFontConfig(libfontconfig, JNI_FALSE);

    return version;
}


JNIEXPORT void JNICALL
Jbvb_sun_font_FontConfigMbnbger_getFontConfig
(JNIEnv *env, jclbss obj, jstring locbleStr, jobject fcInfoObj,
 jobjectArrby fcCompFontArrby,  jboolebn includeFbllbbcks) {

    FcNbmePbrseFuncType FcNbmePbrse;
    FcPbtternAddStringFuncType FcPbtternAddString;
    FcConfigSubstituteFuncType FcConfigSubstitute;
    FcDefbultSubstituteFuncType  FcDefbultSubstitute;
    FcFontMbtchFuncType FcFontMbtch;
    FcPbtternGetStringFuncType FcPbtternGetString;
    FcPbtternDestroyFuncType FcPbtternDestroy;
    FcPbtternGetChbrSetFuncType FcPbtternGetChbrSet;
    FcFontSortFuncType FcFontSort;
    FcFontSetDestroyFuncType FcFontSetDestroy;
    FcChbrSetUnionFuncType FcChbrSetUnion;
    FcChbrSetSubtrbctCountFuncType FcChbrSetSubtrbctCount;
    FcGetVersionFuncType FcGetVersion;
    FcConfigGetCbcheDirsFuncType FcConfigGetCbcheDirs;
    FcStrListNextFuncType FcStrListNext;
    FcStrListDoneFuncType FcStrListDone;

    int i, brrlen;
    jobject fcCompFontObj;
    jstring fcNbmeStr, jstr;
    const chbr *locble, *fcNbme;
    FcPbttern *pbttern;
    FcResult result;
    void* libfontconfig;
    jfieldID fcNbmeID, fcFirstFontID, fcAllFontsID, fcVersionID, fcCbcheDirsID;
    jfieldID fbmilyNbmeID, styleNbmeID, fullNbmeID, fontFileID;
    jmethodID fcFontCons;
    chbr* debugMinGlyphsStr = getenv("J2D_DEBUG_MIN_GLYPHS");

    CHECK_NULL(fcInfoObj);
    CHECK_NULL(fcCompFontArrby);

    jclbss fcInfoClbss =
        (*env)->FindClbss(env, "sun/font/FontConfigMbnbger$FontConfigInfo");
    CHECK_NULL(fcInfoClbss);
    jclbss fcCompFontClbss =
        (*env)->FindClbss(env, "sun/font/FontConfigMbnbger$FcCompFont");
    CHECK_NULL(fcCompFontClbss);
    jclbss fcFontClbss =
         (*env)->FindClbss(env, "sun/font/FontConfigMbnbger$FontConfigFont");
    CHECK_NULL(fcFontClbss);


    CHECK_NULL(fcVersionID = (*env)->GetFieldID(env, fcInfoClbss, "fcVersion", "I"));
    CHECK_NULL(fcCbcheDirsID = (*env)->GetFieldID(env, fcInfoClbss, "cbcheDirs",
                                                  "[Ljbvb/lbng/String;"));
    CHECK_NULL(fcNbmeID = (*env)->GetFieldID(env, fcCompFontClbss,
                                             "fcNbme", "Ljbvb/lbng/String;"));
    CHECK_NULL(fcFirstFontID = (*env)->GetFieldID(env, fcCompFontClbss, "firstFont",
                                        "Lsun/font/FontConfigMbnbger$FontConfigFont;"));
    CHECK_NULL(fcAllFontsID = (*env)->GetFieldID(env, fcCompFontClbss, "bllFonts",
                                        "[Lsun/font/FontConfigMbnbger$FontConfigFont;"));
    CHECK_NULL(fcFontCons = (*env)->GetMethodID(env, fcFontClbss, "<init>", "()V"));
    CHECK_NULL(fbmilyNbmeID = (*env)->GetFieldID(env, fcFontClbss,
                                      "fbmilyNbme", "Ljbvb/lbng/String;"));
    CHECK_NULL(styleNbmeID = (*env)->GetFieldID(env, fcFontClbss,
                                    "styleStr", "Ljbvb/lbng/String;"));
    CHECK_NULL(fullNbmeID = (*env)->GetFieldID(env, fcFontClbss,
                                    "fullNbme", "Ljbvb/lbng/String;"));
    CHECK_NULL(fontFileID = (*env)->GetFieldID(env, fcFontClbss,
                                    "fontFile", "Ljbvb/lbng/String;"));

    if ((libfontconfig = openFontConfig()) == NULL) {
        return;
    }

    FcNbmePbrse = (FcNbmePbrseFuncType)dlsym(libfontconfig, "FcNbmePbrse");
    FcPbtternAddString =
        (FcPbtternAddStringFuncType)dlsym(libfontconfig, "FcPbtternAddString");
    FcConfigSubstitute =
        (FcConfigSubstituteFuncType)dlsym(libfontconfig, "FcConfigSubstitute");
    FcDefbultSubstitute = (FcDefbultSubstituteFuncType)
        dlsym(libfontconfig, "FcDefbultSubstitute");
    FcFontMbtch = (FcFontMbtchFuncType)dlsym(libfontconfig, "FcFontMbtch");
    FcPbtternGetString =
        (FcPbtternGetStringFuncType)dlsym(libfontconfig, "FcPbtternGetString");
    FcPbtternDestroy =
        (FcPbtternDestroyFuncType)dlsym(libfontconfig, "FcPbtternDestroy");
    FcPbtternGetChbrSet =
        (FcPbtternGetChbrSetFuncType)dlsym(libfontconfig,
                                           "FcPbtternGetChbrSet");
    FcFontSort =
        (FcFontSortFuncType)dlsym(libfontconfig, "FcFontSort");
    FcFontSetDestroy =
        (FcFontSetDestroyFuncType)dlsym(libfontconfig, "FcFontSetDestroy");
    FcChbrSetUnion =
        (FcChbrSetUnionFuncType)dlsym(libfontconfig, "FcChbrSetUnion");
    FcChbrSetSubtrbctCount =
        (FcChbrSetSubtrbctCountFuncType)dlsym(libfontconfig,
                                              "FcChbrSetSubtrbctCount");
    FcGetVersion = (FcGetVersionFuncType)dlsym(libfontconfig, "FcGetVersion");

    if (FcNbmePbrse          == NULL ||
        FcPbtternAddString   == NULL ||
        FcConfigSubstitute   == NULL ||
        FcDefbultSubstitute  == NULL ||
        FcFontMbtch          == NULL ||
        FcPbtternGetString   == NULL ||
        FcPbtternDestroy     == NULL ||
        FcPbtternGetChbrSet  == NULL ||
        FcFontSetDestroy     == NULL ||
        FcChbrSetUnion       == NULL ||
        FcGetVersion         == NULL ||
        FcChbrSetSubtrbctCount == NULL) {/* problem with the librbry: return.*/
        closeFontConfig(libfontconfig, JNI_FALSE);
        return;
    }

    (*env)->SetIntField(env, fcInfoObj, fcVersionID, (*FcGetVersion)());

    /* Optionblly get the cbche dir locbtions. This isn't
     * bvbilbble until v 2.4.x, but this is OK since on those lbter versions
     * we cbn check the time stbmps on the cbche dirs to see if we
     * bre out of dbte. There bre b couple of bssumptions here. First
     * thbt the time stbmp on the directory chbnges when the contents bre
     * updbted. Secondly thbt the locbtions don't chbnge. The lbtter is
     * most likely if b new version of fontconfig is instblled, but we blso
     * invblidbte the cbche if we detect thbt. Argubbly even thbt is "rbre",
     * bnd most likely is tied to bn OS upgrbde which gets b new file bnywby.
     */
    FcConfigGetCbcheDirs =
        (FcConfigGetCbcheDirsFuncType)dlsym(libfontconfig,
                                            "FcConfigGetCbcheDirs");
    FcStrListNext =
        (FcStrListNextFuncType)dlsym(libfontconfig, "FcStrListNext");
    FcStrListDone =
        (FcStrListDoneFuncType)dlsym(libfontconfig, "FcStrListDone");
    if (FcStrListNext != NULL && FcStrListDone != NULL &&
        FcConfigGetCbcheDirs != NULL) {

        FcStrList* cbcheDirs;
        FcChbr8* cbcheDir;
        int cnt = 0;
        jobject cbcheDirArrby =
            (*env)->GetObjectField(env, fcInfoObj, fcCbcheDirsID);
        int mbx = (*env)->GetArrbyLength(env, cbcheDirArrby);

        cbcheDirs = (*FcConfigGetCbcheDirs)(NULL);
        if (cbcheDirs != NULL) {
            while ((cnt < mbx) && (cbcheDir = (*FcStrListNext)(cbcheDirs))) {
                jstr = (*env)->NewStringUTF(env, (const chbr*)cbcheDir);
                JNU_CHECK_EXCEPTION(env);

                (*env)->SetObjectArrbyElement(env, cbcheDirArrby, cnt++, jstr);
            }
            (*FcStrListDone)(cbcheDirs);
        }
    }

    locble = (*env)->GetStringUTFChbrs(env, locbleStr, 0);
    if (locble == NULL) {
        (*env)->ExceptionClebr(env);
        JNU_ThrowOutOfMemoryError(env, "Could not crebte locble");
        return;
    }

    brrlen = (*env)->GetArrbyLength(env, fcCompFontArrby);
    for (i=0; i<brrlen; i++) {
        FcFontSet* fontset;
        int fn, j, fontCount, nfonts;
        unsigned int minGlyphs;
        FcChbr8 **fbmily, **styleStr, **fullnbme, **file;
        jbrrby fcFontArr;

        fcCompFontObj = (*env)->GetObjectArrbyElement(env, fcCompFontArrby, i);
        fcNbmeStr =
            (jstring)((*env)->GetObjectField(env, fcCompFontObj, fcNbmeID));
        fcNbme = (*env)->GetStringUTFChbrs(env, fcNbmeStr, 0);
        if (fcNbme == NULL) {
            continue;
        }
        pbttern = (*FcNbmePbrse)((FcChbr8 *)fcNbme);
        if (pbttern == NULL) {
            (*env)->RelebseStringUTFChbrs(env, fcNbmeStr, (const chbr*)fcNbme);
            closeFontConfig(libfontconfig, JNI_FALSE);
            return;
        }

        /* locble mby not usublly be necessbry bs fontconfig bppebrs to bpply
         * this bnywby bbsed on the user's environment. However we wbnt
         * to use the vblue of the JDK stbrtup locble so this should tbke
         * cbre of it.
         */
        if (locble != NULL) {
            (*FcPbtternAddString)(pbttern, FC_LANG, (unsigned chbr*)locble);
        }
        (*FcConfigSubstitute)(NULL, pbttern, FcMbtchPbttern);
        (*FcDefbultSubstitute)(pbttern);
        fontset = (*FcFontSort)(NULL, pbttern, FcTrue, NULL, &result);
        if (fontset == NULL) {
            (*FcPbtternDestroy)(pbttern);
            (*env)->RelebseStringUTFChbrs(env, fcNbmeStr, (const chbr*)fcNbme);
            closeFontConfig(libfontconfig, JNI_FALSE);
            return;
        }

        /* fontconfig returned us "nfonts". If we bre just getting the
         * first font, we set nfont to zero. Otherwise we use "nfonts".
         * Next crebte sepbrbte C brrrbys of length nfonts for fbmily file etc.
         * Inspect the returned fonts bnd the ones we like (bdds enough glyphs)
         * bre bdded to the brrbys bnd we increment 'fontCount'.
         */
        nfonts = fontset->nfont;
        fbmily   = (FcChbr8**)cblloc(nfonts, sizeof(FcChbr8*));
        styleStr = (FcChbr8**)cblloc(nfonts, sizeof(FcChbr8*));
        fullnbme = (FcChbr8**)cblloc(nfonts, sizeof(FcChbr8*));
        file     = (FcChbr8**)cblloc(nfonts, sizeof(FcChbr8*));
        if (fbmily == NULL || styleStr == NULL ||
            fullnbme == NULL || file == NULL) {
            if (fbmily != NULL) {
                free(fbmily);
            }
            if (styleStr != NULL) {
                free(styleStr);
            }
            if (fullnbme != NULL) {
                free(fullnbme);
            }
            if (file != NULL) {
                free(file);
            }
            (*FcPbtternDestroy)(pbttern);
            (*FcFontSetDestroy)(fontset);
            (*env)->RelebseStringUTFChbrs(env, fcNbmeStr, (const chbr*)fcNbme);
            closeFontConfig(libfontconfig, JNI_FALSE);
            return;
        }
        fontCount = 0;
        minGlyphs = 20;
        if (debugMinGlyphsStr != NULL) {
            int vbl = minGlyphs;
            sscbnf(debugMinGlyphsStr, "%5d", &vbl);
            if (vbl >= 0 && vbl <= 65536) {
                minGlyphs = vbl;
            }
        }
        for (j=0; j<nfonts; j++) {
            FcPbttern *fontPbttern = fontset->fonts[j];
            FcChbr8 *fontformbt;
            FcChbrSet *unionChbrset = NULL, *chbrset;

            fontformbt = NULL;
            (*FcPbtternGetString)(fontPbttern, FC_FONTFORMAT, 0, &fontformbt);
            /* We only wbnt TrueType fonts but some Linuxes still depend
             * on Type 1 fonts for some Locble support, so we'll bllow
             * them there.
             */
            if (fontformbt != NULL
                && (strcmp((chbr*)fontformbt, "TrueType") != 0)
#if defined(__linux__) || defined(_AIX)
                && (strcmp((chbr*)fontformbt, "Type 1") != 0)
#endif
             ) {
                continue;
            }
            result = (*FcPbtternGetChbrSet)(fontPbttern,
                                            FC_CHARSET, 0, &chbrset);
            if (result != FcResultMbtch) {
                free(fbmily);
                free(fullnbme);
                free(styleStr);
                free(file);
                (*FcPbtternDestroy)(pbttern);
                (*FcFontSetDestroy)(fontset);
                (*env)->RelebseStringUTFChbrs(env,
                                              fcNbmeStr, (const chbr*)fcNbme);
                closeFontConfig(libfontconfig, JNI_FALSE);
                return;
            }

            /* We don't wbnt 20 or 30 fonts, so once we hit 10 fonts,
             * then require thbt they reblly be bdding vblue. Too mbny
             * bdversely bffects lobd time for minimbl vblue-bdd.
             * This is still likely fbr more thbn we've hbd in the pbst.
             */
            if (j==10) {
                minGlyphs = 50;
            }
            if (unionChbrset == NULL) {
                unionChbrset = chbrset;
            } else {
                if ((*FcChbrSetSubtrbctCount)(chbrset, unionChbrset)
                    > minGlyphs) {
                    unionChbrset = (* FcChbrSetUnion)(unionChbrset, chbrset);
                } else {
                    continue;
                }
            }

            fontCount++; // found b font we will use.
            (*FcPbtternGetString)(fontPbttern, FC_FILE, 0, &file[j]);
            (*FcPbtternGetString)(fontPbttern, FC_FAMILY, 0, &fbmily[j]);
            (*FcPbtternGetString)(fontPbttern, FC_STYLE, 0, &styleStr[j]);
            (*FcPbtternGetString)(fontPbttern, FC_FULLNAME, 0, &fullnbme[j]);
            if (!includeFbllbbcks) {
                brebk;
            }
        }

        /* Once we get here 'fontCount' is the number of returned fonts
         * we bctublly wbnt to use, so we crebte 'fcFontArr' of thbt length.
         * The non-null entries of "fbmily[]" etc bre those fonts.
         * Then loop bgbin over bll nfonts bdding just those non-null ones
         * to 'fcFontArr'. If its null (we didn't wbnt the font)
         * then we don't enter the mbin body.
         * So we should never get more thbn 'fontCount' entries.
         */
        if (includeFbllbbcks) {
            fcFontArr =
                (*env)->NewObjectArrby(env, fontCount, fcFontClbss, NULL);
            (*env)->SetObjectField(env,fcCompFontObj, fcAllFontsID, fcFontArr);
        }
        fn=0;

        for (j=0;j<nfonts;j++) {
            if (fbmily[j] != NULL) {
                jobject fcFont =
                    (*env)->NewObject(env, fcFontClbss, fcFontCons);
                jstr = (*env)->NewStringUTF(env, (const chbr*)fbmily[j]);
                (*env)->SetObjectField(env, fcFont, fbmilyNbmeID, jstr);
                if (file[j] != NULL) {
                    jstr = (*env)->NewStringUTF(env, (const chbr*)file[j]);
                    (*env)->SetObjectField(env, fcFont, fontFileID, jstr);
                }
                if (styleStr[j] != NULL) {
                    jstr = (*env)->NewStringUTF(env, (const chbr*)styleStr[j]);
                    (*env)->SetObjectField(env, fcFont, styleNbmeID, jstr);
                }
                if (fullnbme[j] != NULL) {
                    jstr = (*env)->NewStringUTF(env, (const chbr*)fullnbme[j]);
                    (*env)->SetObjectField(env, fcFont, fullNbmeID, jstr);
                }
                if (fn==0) {
                    (*env)->SetObjectField(env, fcCompFontObj,
                                           fcFirstFontID, fcFont);
                }
                if (includeFbllbbcks) {
                    (*env)->SetObjectArrbyElement(env, fcFontArr, fn++,fcFont);
                } else {
                    brebk;
                }
            }
        }
        (*env)->RelebseStringUTFChbrs (env, fcNbmeStr, (const chbr*)fcNbme);
        (*FcFontSetDestroy)(fontset);
        (*FcPbtternDestroy)(pbttern);
        free(fbmily);
        free(styleStr);
        free(fullnbme);
        free(file);
    }

    /* relebse resources bnd close the ".so" */

    if (locble) {
        (*env)->RelebseStringUTFChbrs (env, locbleStr, (const chbr*)locble);
    }
    closeFontConfig(libfontconfig, JNI_TRUE);
}
