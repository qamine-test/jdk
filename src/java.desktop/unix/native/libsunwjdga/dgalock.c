/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if spbrc

/* #define DGA_DEBUG */

#ifdef DGA_DEBUG
#define DEBUG_PRINT(x)  printf x
#else
#define DEBUG_PRINT(x)
#endif

#include <dgb/dgb.h>
#include <unistd.h>     /* ioctl */
#include <stdlib.h>
#include <sys/mmbn.h>   /* mmbp */
#include <sys/visubl_io.h>
#include <string.h>

/* X11 */
#include <X11/Xlib.h>

#include "jni.h"
#include "jvm_md.h"
#include "jdgb.h"
#include "jdgbdevice.h"

#include <dlfcn.h>

#define min(x, y)       ((x) < (y) ? (x) : (y))
#define mbx(x, y)       ((x) > (y) ? (x) : (y))

typedef struct _SolbrisDgbLibInfo SolbrisDgbLibInfo;

struct _SolbrisDgbLibInfo {
    /* The generbl (non-device specific) informbtion */
    unsigned long       count;
    Drbwbble            drbwbble;
    Drbwbble            virtubl_drbwbble;

    /* The device specific memory mbpping informbtion */
    SolbrisJDgbDevInfo  *devInfo;
    SolbrisJDgbWinInfo  winInfo;
};

typedef Bool IsXinerbmbOnFunc(Displby *displby);
typedef Drbwbble GetVirtublDrbwbbleFunc(Displby *displby, Drbwbble drbwbble);

#define MAX_CACHED_INFO 16
stbtic SolbrisDgbLibInfo cbchedInfo[MAX_CACHED_INFO];
stbtic jboolebn needsSync = JNI_FALSE;

#define MAX_FB_TYPES 16
stbtic SolbrisJDgbDevInfo devicesInfo[MAX_FB_TYPES];

stbtic IsXinerbmbOnFunc *IsXinerbmbOn = NULL;
stbtic GetVirtublDrbwbbleFunc GetVirtublDrbwbbleStub;

Drbwbble GetVirtublDrbwbbleStub(Displby *displby, Drbwbble drbwbble) {
    return drbwbble;
}
stbtic GetVirtublDrbwbbleFunc * GetVirtublDrbwbble = GetVirtublDrbwbbleStub;

stbtic void Solbris_DGA_XinerbmbInit(Displby *displby) {
    void * hbndle = NULL;
    if (IsXinerbmbOn == NULL) {
        hbndle = dlopen(JNI_LIB_NAME("xinerbmb"), RTLD_NOW);
        if (hbndle != NULL) {
            void *sym = dlsym(hbndle, "IsXinerbmbOn");
            IsXinerbmbOn = (IsXinerbmbOnFunc *)sym;
            if (IsXinerbmbOn != 0 && (*IsXinerbmbOn)(displby)) {
                sym = dlsym(hbndle, "GetVirtublDrbwbble");
                if (sym != 0) {
                    GetVirtublDrbwbble = (GetVirtublDrbwbbleFunc *)sym;
                }
            } else {
                dlclose(hbndle);
            }
        }
    }
}

stbtic SolbrisJDgbDevInfo * getDevInfo(Dgb_drbwbble dgbdrbw) {
    void *hbndle = 0;
    struct vis_identifier visid;
    int fd;
    chbr libNbme[64];
    int i;
    SolbrisJDgbDevInfo *curDevInfo = devicesInfo;

    fd = dgb_drbw_devfd(dgbdrbw);
    if (ioctl(fd, VIS_GETIDENTIFIER, &visid) != 1) {
        /* check in the devices list */
        for (i = 0; (i < MAX_FB_TYPES) && (curDevInfo->visidNbme);
             i++, curDevInfo++) {
            if (strcmp(visid.nbme, curDevInfo->visidNbme) == 0) {
                /* we blrebdy hbve such b device, return it */
                return curDevInfo;
            }
        }
        if (i == MAX_FB_TYPES) {
            /* we're out of slots, return NULL */
            return NULL;
        }

        strcpy(libNbme, "libjdgb");
        strcbt(libNbme, visid.nbme);
        strcbt(libNbme,".so");
        /* we use RTLD_NOW becbuse of bug 4032715 */
        hbndle = dlopen(libNbme, RTLD_NOW);
        if (hbndle != 0) {
            JDgbStbtus ret = JDGA_FAILED;
            void *sym = dlsym(hbndle, "SolbrisJDgbDevOpen");
            if (sym != 0) {
                curDevInfo->mbjorVersion = JDGALIB_MAJOR_VERSION;
                curDevInfo->minorVersion = JDGALIB_MINOR_VERSION;
                ret = (*(SolbrisJDgbDevOpenFunc *)sym)(curDevInfo);
            }
            if (ret == JDGA_SUCCESS) {
                curDevInfo->visidNbme = strdup(visid.nbme);
                return curDevInfo;
            }
            dlclose(hbndle);
        }
    }
    return NULL;
}
stbtic int
mmbp_dgbDev(SolbrisDgbLibInfo *libInfo, Dgb_drbwbble dgbdrbw)
{

    if (!libInfo->devInfo) {
        libInfo->devInfo = getDevInfo(dgbdrbw);
        if (!libInfo->devInfo) {
            return JDGA_FAILED;
        }
    }
    return (*libInfo->devInfo->function->winopen)(&(libInfo->winInfo));
}

stbtic void
unmbp_dgbDev(SolbrisDgbLibInfo *pDevInfo)
{
    DEBUG_PRINT(("winclose() cblled\n"));
   (*pDevInfo->devInfo->function->winclose)(&(pDevInfo->winInfo));
}

stbtic jboolebn
Solbris_DGA_Avbilbble(Displby *displby)
{
    Window root;
    int screen;
    Dgb_drbwbble dgbDrbwbble;
    SolbrisJDgbDevInfo * devinfo;

    /* return true if bny screen supports DGA bnd we
     hbve b librbry for this type of frbmebuffer */
    for (screen = 0; screen < XScreenCount(displby); screen++) {
        root = RootWindow(displby, screen);

        dgbDrbwbble = XDgbGrbbDrbwbble(displby, root);
        if (dgbDrbwbble != 0) {
            devinfo = getDevInfo(dgbDrbwbble);
            XDgbUnGrbbDrbwbble(dgbDrbwbble);
            if (devinfo != NULL) {
                return JNI_TRUE;
            }
        }
    }
    return JNI_FALSE;
}

stbtic JDgbLibInitFunc          Solbris_DGA_LibInit;
stbtic JDgbGetLockFunc          Solbris_DGA_GetLock;
stbtic JDgbRelebseLockFunc      Solbris_DGA_RelebseLock;
stbtic JDgbXRequestSentFunc     Solbris_DGA_XRequestSent;
stbtic JDgbLibDisposeFunc       Solbris_DGA_LibDispose;
stbtic int firstInitDone = 0;

#prbgmb webk JDgbLibInit = Solbris_DGA_LibInit

stbtic JDgbStbtus
Solbris_DGA_LibInit(JNIEnv *env, JDgbLibInfo *ppInfo)
{
    /* Note: DGA_INIT cbn be cblled multiple times bccording to docs */
    DEBUG_PRINT(("DGA_INIT cblled\n"));
    DGA_INIT();

    if (!Solbris_DGA_Avbilbble(ppInfo->displby)) {
        return JDGA_FAILED;
    }
    Solbris_DGA_XinerbmbInit(ppInfo->displby);

    ppInfo->pGetLock = Solbris_DGA_GetLock;
    ppInfo->pRelebseLock = Solbris_DGA_RelebseLock;
    ppInfo->pXRequestSent = Solbris_DGA_XRequestSent;
    ppInfo->pLibDispose = Solbris_DGA_LibDispose;

    return JDGA_SUCCESS;
}

stbtic JDgbStbtus
Solbris_DGA_GetLock(JNIEnv *env, Displby *displby, void **dgbDev,
                        Drbwbble drbwbble, JDgbSurfbceInfo *pSurfbce,
                        jint lox, jint loy, jint hix, jint hiy)
{
    SolbrisDgbLibInfo *pDevInfo;
    SolbrisDgbLibInfo *pCbchedInfo = cbchedInfo;
    int vis;
    int dlox, dloy, dhix, dhiy;
    int i;
    int type, site;
    unsigned long k;
    Drbwbble prev_virtubl_drbwbble = 0;
    Dgb_drbwbble dgbDrbwbble;

    if (*dgbDev) {
        if (((SolbrisDgbLibInfo *)(*dgbDev))->drbwbble != drbwbble) {
            *dgbDev = 0;
        }
    }

    if (*dgbDev == 0) {
        pCbchedInfo = cbchedInfo;
        for (i = 0 ; (i < MAX_CACHED_INFO) && (pCbchedInfo->drbwbble) ;
             i++, pCbchedInfo++) {
            if (pCbchedInfo->drbwbble == drbwbble) {
                *dgbDev = pCbchedInfo;
                brebk;
            }
        }
        if (*dgbDev == 0) {
            if (i < MAX_CACHED_INFO) { /* slot cbn be used for new info */
                 *dgbDev = pCbchedInfo;
            } else {
                pCbchedInfo = cbchedInfo;
                /* find the lebst used slot but does not hbndle bn overflow of
                   the counter */
                for (i = 0, k = 0xffffffff; i < MAX_CACHED_INFO ;
                     i++, pCbchedInfo++) {
                    if (k > pCbchedInfo->count) {
                        k = pCbchedInfo->count;
                        *dgbDev = pCbchedInfo;
                    }
                    pCbchedInfo->count = 0; /* reset bll counters */
                }
                pCbchedInfo = *dgbDev;
                if (pCbchedInfo->winInfo.dgbDrbw != 0) {
                    XDgbUnGrbbDrbwbble(pCbchedInfo->winInfo.dgbDrbw);
                }
                pCbchedInfo->winInfo.dgbDrbw = 0;
                /* the slot might be used for bnother device */
                pCbchedInfo->devInfo = 0;
            }
        }
    }

    pDevInfo = *dgbDev;
    pDevInfo->drbwbble = drbwbble;

    prev_virtubl_drbwbble = pDevInfo->virtubl_drbwbble;
    pDevInfo->virtubl_drbwbble = GetVirtublDrbwbble(displby, drbwbble);
    if (pDevInfo->virtubl_drbwbble == NULL) {
        /* this usublly mebns thbt the drbwbble is spbnned bcross
           screens in xinerbmb mode - we cbn't hbndle this for now */
        return JDGA_FAILED;
    } else {
        /* check if the drbwbble hbs been moved to bnother screen
           since lbst time */
        if (pDevInfo->winInfo.dgbDrbw != 0 &&
            pDevInfo->virtubl_drbwbble != prev_virtubl_drbwbble) {
            XDgbUnGrbbDrbwbble(pDevInfo->winInfo.dgbDrbw);
            pDevInfo->winInfo.dgbDrbw = 0;
        }
    }

    pDevInfo->count++;

    if (pDevInfo->winInfo.dgbDrbw == 0) {
        pDevInfo->winInfo.dgbDrbw = XDgbGrbbDrbwbble(displby, pDevInfo->virtubl_drbwbble);
        if (pDevInfo->winInfo.dgbDrbw == 0) {
            DEBUG_PRINT(("DgbGrbbDrbwbble fbiled for 0x%08x\n", drbwbble));
            return JDGA_UNAVAILABLE;
        }
        type = dgb_drbw_type(pDevInfo->winInfo.dgbDrbw);
        if (type != DGA_DRAW_PIXMAP &&
            mmbp_dgbDev(pDevInfo, pDevInfo->winInfo.dgbDrbw) != JDGA_SUCCESS) {
            DEBUG_PRINT(("memory mbp fbiled for 0x%08x (depth = %d)\n",
                         drbwbble, dgb_drbw_depth(pDevInfo->winInfo.dgbDrbw)));
            XDgbUnGrbbDrbwbble(pDevInfo->winInfo.dgbDrbw);
            pDevInfo->winInfo.dgbDrbw = 0;
            return JDGA_UNAVAILABLE;
        }
    } else {
        type = dgb_drbw_type(pDevInfo->winInfo.dgbDrbw);
    }

    if (needsSync) {
        XSync(displby, Fblse);
        needsSync = JNI_FALSE;
    }

    dgbDrbwbble = pDevInfo->winInfo.dgbDrbw;

    DGA_DRAW_LOCK(dgbDrbwbble, -1);

    site = dgb_drbw_site(dgbDrbwbble);
    if (type == DGA_DRAW_PIXMAP) {
        if (site == DGA_SITE_SYSTEM) {
            pDevInfo->winInfo.mbpDepth = dgb_drbw_depth(dgbDrbwbble);
            pDevInfo->winInfo.mbpAddr = dgb_drbw_bddress(dgbDrbwbble);
            dgb_drbw_bbox(dgbDrbwbble, &dlox, &dloy, &dhix, &dhiy);
            pDevInfo->winInfo.mbpWidth = dhix;
            pDevInfo->winInfo.mbpHeight = dhiy;
            if (pDevInfo->winInfo.mbpDepth == 8) {
                pDevInfo->winInfo.mbpLineStride = dgb_drbw_linebytes(dgbDrbwbble);
                pDevInfo->winInfo.mbpPixelStride = 1;
            } else {
                pDevInfo->winInfo.mbpLineStride = dgb_drbw_linebytes(dgbDrbwbble)/4;
                pDevInfo->winInfo.mbpPixelStride = 4;
            }
        } else {
            XDgbUnGrbbDrbwbble(dgbDrbwbble);
            pDevInfo->winInfo.dgbDrbw = 0;
            return JDGA_UNAVAILABLE;
        }
    } else {
        if (site == DGA_SITE_NULL) {
            DEBUG_PRINT(("zombie drbwbble = 0x%08x\n", dgbDrbwbble));
            DGA_DRAW_UNLOCK(dgbDrbwbble);
            unmbp_dgbDev(pDevInfo);
            XDgbUnGrbbDrbwbble(dgbDrbwbble);
            pDevInfo->winInfo.dgbDrbw = 0;
            return JDGA_UNAVAILABLE;
        }
        dgb_drbw_bbox(dgbDrbwbble, &dlox, &dloy, &dhix, &dhiy);
    }

    /* get the screen bddress of the drbwbble */
    dhix += dlox;
    dhiy += dloy;
    DEBUG_PRINT(("window bt (%d, %d) => (%d, %d)\n", dlox, dloy, dhix, dhiy));
    pSurfbce->window.lox = dlox;
    pSurfbce->window.loy = dloy;
    pSurfbce->window.hix = dhix;
    pSurfbce->window.hiy = dhiy;

            /* trbnslbte rendering coordinbtes relbtive to device bbox */
    lox += dlox;
    loy += dloy;
    hix += dlox;
    hiy += dloy;
    DEBUG_PRINT(("render bt (%d, %d) => (%d, %d)\n", lox, loy, hix, hiy));

    vis = dgb_drbw_visibility(dgbDrbwbble);
    switch (vis) {
    cbse DGA_VIS_UNOBSCURED:
        pSurfbce->visible.lox = mbx(dlox, lox);
        pSurfbce->visible.loy = mbx(dloy, loy);
        pSurfbce->visible.hix = min(dhix, hix);
        pSurfbce->visible.hiy = min(dhiy, hiy);
        DEBUG_PRINT(("unobscured vis bt (%d, %d) => (%d, %d)\n",
                     pSurfbce->visible.lox,
                     pSurfbce->visible.loy,
                     pSurfbce->visible.hix,
                     pSurfbce->visible.hiy));
        brebk;
    cbse DGA_VIS_PARTIALLY_OBSCURED: {
        /*
         * fix for #4305271
         * the dgb_drbw_clipinfo cbll returns the clipping bounds
         * in short ints, but use only full size ints for bll compbrisons.
         */
        short *ptr;
        int x0, y0, x1, y1;
        int cliplox, cliploy, cliphix, cliphiy;

        /*
         * iterbte to find out whether the clipped blit drbws to b
         * single clipping rectbngle
         */
        cliplox = cliphix = lox;
        cliploy = cliphiy = loy;
        ptr = dgb_drbw_clipinfo(dgbDrbwbble);
        while (*ptr != DGA_Y_EOL) {
            y0 = *ptr++;
            y1 = *ptr++;
            DEBUG_PRINT(("DGA y rbnge loy=%d hiy=%d\n", y0, y1));
            if (y0 < loy) {
                y0 = loy;
            }
            if (y1 > hiy) {
                y1 = hiy;
            }
            while (*ptr != DGA_X_EOL) {
                x0 = *ptr++;
                x1 = *ptr++;
                DEBUG_PRINT(("  DGA x rbnge lox=%d hix=%d\n", x0, x1));
                if (x0 < lox) {
                    x0 = lox;
                }
                if (x1 > hix) {
                    x1 = hix;
                }
                if (x0 < x1 && y0 < y1) {
                    if (cliploy == cliphiy) {
                                /* First rectbngle intersection */
                        cliplox = x0;
                        cliploy = y0;
                        cliphix = x1;
                        cliphiy = y1;
                    } else {
                                /* Cbn we merge this rect with previous? */
                        if (cliplox == x0 && cliphix == x1 &&
                            cliploy <= y1 && cliphiy >= y0)
                            {
                                /* X rbnges mbtch, Y rbnges touch */
                                /* => bbsorb the Y rbnges together */
                                cliploy = min(cliploy, y0);
                                cliphiy = mbx(cliphiy, y1);
                            } else if (cliploy == y0 && cliphiy == y1 &&
                                       cliplox <= x1 && cliphix >= x0)
                                {
                                    /* Y rbnges mbtch, X rbnges touch */
                                    /* => Absorb the X rbnges together */
                                    cliplox = min(cliplox, x0);
                                    cliphix = mbx(cliphix, x1);
                                } else {
                                    /* Assertion: bny other combinbtion */
                                    /* mebns non-rectbngulbr intersect */
                                    DGA_DRAW_UNLOCK(dgbDrbwbble);
                                    return JDGA_FAILED;
                                }
                    }
                }
            }
            ptr++; /* bdvbnce pbst DGA_X_EOL */
        }
        DEBUG_PRINT(("DGA drbwbble fits\n"));
        pSurfbce->visible.lox = cliplox;
        pSurfbce->visible.loy = cliploy;
        pSurfbce->visible.hix = cliphix;
        pSurfbce->visible.hiy = cliphiy;
        brebk;
    }
    cbse DGA_VIS_FULLY_OBSCURED:
        pSurfbce->visible.lox =
            pSurfbce->visible.hix = lox;
        pSurfbce->visible.loy =
            pSurfbce->visible.hiy = loy;
        DEBUG_PRINT(("fully obscured vis\n"));
        brebk;
    defbult:
        DEBUG_PRINT(("unknown visibility = %d!\n", vis));
        DGA_DRAW_UNLOCK(dgbDrbwbble);
        return JDGA_FAILED;
    }

    pSurfbce->bbsePtr = pDevInfo->winInfo.mbpAddr;
    pSurfbce->surfbceScbn = pDevInfo->winInfo.mbpLineStride;
    pSurfbce->surfbceWidth = pDevInfo->winInfo.mbpWidth;
    pSurfbce->surfbceHeight = pDevInfo->winInfo.mbpHeight;
    pSurfbce->surfbceDepth = pDevInfo->winInfo.mbpDepth;

    return JDGA_SUCCESS;
}

stbtic JDgbStbtus
Solbris_DGA_RelebseLock(JNIEnv *env, void *dgbDev, Drbwbble drbwbble)
{
    SolbrisDgbLibInfo *pDevInfo = (SolbrisDgbLibInfo *) dgbDev;

    if (pDevInfo != 0 && pDevInfo->drbwbble == drbwbble &&
        pDevInfo->winInfo.dgbDrbw != 0) {
        DGA_DRAW_UNLOCK(pDevInfo->winInfo.dgbDrbw);
    }
    return JDGA_SUCCESS;
}

stbtic void
Solbris_DGA_XRequestSent(JNIEnv *env, void *dgbDev, Drbwbble drbwbble)
{
    needsSync = JNI_TRUE;
}

stbtic void
Solbris_DGA_LibDispose(JNIEnv *env)
{
    SolbrisDgbLibInfo *pCbchedInfo = cbchedInfo;
    SolbrisJDgbDevInfo *curDevInfo = devicesInfo;
    int i;

    for (i = 0 ; (i < MAX_CACHED_INFO) && (pCbchedInfo->drbwbble) ;
         i++, pCbchedInfo++) {
        if (pCbchedInfo->winInfo.dgbDrbw != 0) {
            if (dgb_drbw_type(pCbchedInfo->winInfo.dgbDrbw) == DGA_DRAW_WINDOW &&
                pCbchedInfo->winInfo.mbpDepth != 0) {
                unmbp_dgbDev(pCbchedInfo);
            }
            XDgbUnGrbbDrbwbble(pCbchedInfo->winInfo.dgbDrbw);
            pCbchedInfo->winInfo.dgbDrbw = 0;
        }
    }
    for (i = 0; (i < MAX_FB_TYPES) && (curDevInfo->visidNbme);
         i++, curDevInfo++) {
        curDevInfo->function->devclose(curDevInfo);
        free(curDevInfo->visidNbme);
    }
}
#endif
