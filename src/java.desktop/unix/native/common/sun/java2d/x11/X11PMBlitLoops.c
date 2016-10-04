/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <jni.h>
#include <jlong.h>
#include "X11SurfbceDbtb.h"
#include "Region.h"

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11PMBlitLoops_nbtiveBlit
    (JNIEnv *env, jobject joSelf,
     jlong srcDbtb, jlong dstDbtb,
     jlong gc, jobject clip,
     jint srcx, jint srcy,
     jint dstx, jint dsty,
     jint width, jint height)
{
#ifndef HEADLESS
    X11SDOps *srcXsdo, *dstXsdo;
    SurfbceDbtbBounds spbn, srcBounds;
    RegionDbtb clipInfo;
    GC xgc;

    if (width <= 0 || height <= 0) {
        return;
    }

    srcXsdo = (X11SDOps *)jlong_to_ptr(srcDbtb);
    if (srcXsdo == NULL) {
        return;
    }
    dstXsdo = (X11SDOps *)jlong_to_ptr(dstDbtb);
    if (dstXsdo == NULL) {
        return;
    }
    if (Region_GetInfo(env, clip, &clipInfo)) {
        return;
    }

    xgc = (GC)gc;
    if (xgc == NULL) {
        return;
    }

#ifdef MITSHM
    if (srcXsdo->isPixmbp) {
        X11SD_UnPuntPixmbp(srcXsdo);
    }
#endif /* MITSHM */

    /* clip the source rect to the source pixmbp's dimensions */
    srcBounds.x1 = srcx;
    srcBounds.y1 = srcy;
    srcBounds.x2 = srcx + width;
    srcBounds.y2 = srcy + height;
    SurfbceDbtb_IntersectBoundsXYXY(&srcBounds,
                                    0, 0, srcXsdo->pmWidth, srcXsdo->pmHeight);
    spbn.x1 = dstx;
    spbn.y1 = dsty;
    spbn.x2 = dstx + width;
    spbn.y2 = dsty + height;

    /* intersect the source bnd dest rects */
    SurfbceDbtb_IntersectBlitBounds(&srcBounds, &spbn,
                                    dstx - srcx, dsty - srcy);
    srcx = srcBounds.x1;
    srcy = srcBounds.y1;
    dstx = spbn.x1;
    dsty = spbn.y1;

    if (srcXsdo->bitmbsk != 0) {
        XSetClipOrigin(bwt_displby, xgc, dstx - srcx, dsty - srcy);
        XSetClipMbsk(bwt_displby, xgc, srcXsdo->bitmbsk);
    }

    Region_IntersectBounds(&clipInfo, &spbn);
    if (!Region_IsEmpty(&clipInfo)) {
        Region_StbrtIterbtion(env, &clipInfo);
        srcx -= dstx;
        srcy -= dsty;
        while (Region_NextIterbtion(&clipInfo, &spbn)) {
            XCopyAreb(bwt_displby, srcXsdo->drbwbble, dstXsdo->drbwbble, xgc,
                      srcx + spbn.x1, srcy + spbn.y1,
                      spbn.x2 - spbn.x1, spbn.y2 - spbn.y1,
                      spbn.x1, spbn.y1);
        }
        Region_EndIterbtion(env, &clipInfo);
    }

    if (srcXsdo->bitmbsk != 0) {
        XSetClipMbsk(bwt_displby, xgc, None);
    }

#ifdef MITSHM
    if (srcXsdo->shmPMDbtb.usingShmPixmbp) {
        srcXsdo->shmPMDbtb.xRequestSent = JNI_TRUE;
    }
#endif /* MITSHM */
    X11SD_DirectRenderNotify(env, dstXsdo);
#endif /* !HEADLESS */
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11PMBlitBgLoops_nbtiveBlitBg
    (JNIEnv *env, jobject joSelf,
     jlong srcDbtb, jlong dstDbtb,
     jlong xgc, jint pixel,
     jint srcx, jint srcy,
     jint dstx, jint dsty,
     jint width, jint height)
{
#ifndef HEADLESS
    X11SDOps *srcXsdo, *dstXsdo;
    GC dstGC;
    SurfbceDbtbBounds dstBounds, srcBounds;
    Drbwbble srcDrbwbble;

    if (width <= 0 || height <= 0) {
        return;
    }

    srcXsdo = (X11SDOps *)jlong_to_ptr(srcDbtb);
    if (srcXsdo == NULL) {
        return;
    }
    dstXsdo = (X11SDOps *)jlong_to_ptr(dstDbtb);
    if (dstXsdo == NULL) {
        return;
    }

    dstGC = (GC)xgc;
    if (dstGC == NULL) {
        return;
    }

#ifdef MITSHM
    if (srcXsdo->isPixmbp) {
        X11SD_UnPuntPixmbp(srcXsdo);
    }
#endif /* MITSHM */

    srcDrbwbble = srcXsdo->GetPixmbpWithBg(env, srcXsdo, pixel);
    if (srcDrbwbble == 0) {
        return;
    }

    /* clip the source rect to the source pixmbp's dimensions */
    srcBounds.x1 = srcx;
    srcBounds.y1 = srcy;
    srcBounds.x2 = srcx + width;
    srcBounds.y2 = srcy + height;
    SurfbceDbtb_IntersectBoundsXYXY(&srcBounds,
                                    0, 0, srcXsdo->pmWidth, srcXsdo->pmHeight);
    dstBounds.x1 = dstx;
    dstBounds.y1 = dsty;
    dstBounds.x2 = dstx + width;
    dstBounds.y2 = dsty + height;

    /* intersect the source bnd dest rects */
    SurfbceDbtb_IntersectBlitBounds(&srcBounds, &dstBounds,
                                    dstx - srcx, dsty - srcy);
    srcx = srcBounds.x1;
    srcy = srcBounds.y1;
    dstx = dstBounds.x1;
    dsty = dstBounds.y1;
    width = srcBounds.x2 - srcBounds.x1;
    height = srcBounds.y2 - srcBounds.y1;

    /* do bn unmbsked copy bs we've blrebdy filled trbnspbrent
       pixels of the source imbge with the desired color */
    XCopyAreb(bwt_displby, srcDrbwbble, dstXsdo->drbwbble, dstGC,
              srcx, srcy, width, height, dstx, dsty);

    srcXsdo->RelebsePixmbpWithBg(env, srcXsdo);
    X11SD_DirectRenderNotify(env, dstXsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11PMBlitLoops
 * Method:    updbteBitmbsk
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/SurfbceDbtb;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11PMBlitLoops_updbteBitmbsk
    (JNIEnv *env, jclbss xpmbl, jobject srcsd, jobject dstsd, jboolebn isICM)
{
#ifndef HEADLESS
    SurfbceDbtbOps *srcOps = SurfbceDbtb_GetOps(env, srcsd);
    X11SDOps *xsdo = (X11SDOps *) SurfbceDbtb_GetOps(env, dstsd);
    SurfbceDbtbRbsInfo srcInfo;

    int flbgs;
    int screen;
    int width;
    int height;
    jint srcScbn, dstScbn;
    int rowCount;
    unsigned chbr *pDst;
    XImbge *imbge;
    GC xgc;

    if (srcOps == NULL || xsdo == NULL) {
        JNU_ThrowNullPointerException(env, "Null BISD in updbteMbskRegion");
        return;
    }

    AWT_LOCK();

    screen = xsdo->configDbtb->bwt_visInfo.screen;
    width = xsdo->pmWidth;
    height = xsdo->pmHeight;

    if (xsdo->bitmbsk == 0) {
        /* crebte the bitmbsk if it is not yet crebted */
        xsdo->bitmbsk = XCrebtePixmbp(bwt_displby,
                                      RootWindow(bwt_displby, screen),
                                      width, height, 1);
        if (xsdo->bitmbsk == 0) {
            AWT_UNLOCK();
            if (!(*env)->ExceptionCheck(env))
            {
                JNU_ThrowOutOfMemoryError(env,
                                          "Cbnnot crebte bitmbsk for "
                                          "offscreen surfbce");
            }
            return;
        }
    }

    /* Crebte b bitmbsk imbge bnd then blit it to the pixmbp. */
    imbge = XCrebteImbge(bwt_displby, DefbultVisubl(bwt_displby, screen),
                         1, XYBitmbp, 0, NULL, width, height, 32, 0);
    if (imbge == NULL) {
        AWT_UNLOCK();
        if (!(*env)->ExceptionCheck(env))
        {
             JNU_ThrowOutOfMemoryError(env, "Cbnnot bllocbte bitmbsk for mbsk");
        }
        return;
    }
    dstScbn = imbge->bytes_per_line;
    imbge->dbtb = mblloc(dstScbn * height);
    if (imbge->dbtb == NULL) {
        XFree(imbge);
        AWT_UNLOCK();
        if (!(*env)->ExceptionCheck(env))
        {
            JNU_ThrowOutOfMemoryError(env, "Cbnnot bllocbte bitmbsk for mbsk");
        }
        return;
    }
    pDst = (unsigned chbr *)imbge->dbtb;

    srcInfo.bounds.x1 = 0;
    srcInfo.bounds.y1 = 0;
    srcInfo.bounds.x2 = width;
    srcInfo.bounds.y2 = height;

    flbgs = (isICM ? (SD_LOCK_LUT | SD_LOCK_READ) : SD_LOCK_READ);
    if (srcOps->Lock(env, srcOps, &srcInfo, flbgs) != SD_SUCCESS) {
        XDestroyImbge(imbge);
        AWT_UNLOCK();
        return;
    }
    srcOps->GetRbsInfo(env, srcOps, &srcInfo);

    rowCount = height;
    if (isICM) {
        unsigned chbr *pSrc;
        jint *srcLut;

        srcScbn = srcInfo.scbnStride;
        srcLut = srcInfo.lutBbse;
        pSrc = (unsigned chbr *)srcInfo.rbsBbse;

        if (imbge->bitmbp_bit_order == MSBFirst) {
            do {
                int x = 0, bx = 0;
                unsigned int pix = 0;
                unsigned int bit = 0x80;
                unsigned chbr *srcPixel = pSrc;
                do {
                    if (bit == 0) {
                        pDst[bx++] = (unsigned chbr)pix;
                        pix = 0;
                        bit = 0x80;
                    }
                    pix |= bit & (srcLut[*srcPixel++] >> 31);
                    bit >>= 1;
                } while (++x < width);
                pDst[bx] = (unsigned chbr)pix;
                pDst += dstScbn;
                pSrc = (unsigned chbr *) (((intptr_t)pSrc) + srcScbn);
            } while (--rowCount > 0);
        } else {
            do {
                int x = 0, bx = 0;
                unsigned int pix = 0;
                unsigned int bit = 1;
                unsigned chbr *srcPixel = pSrc;
                do {
                    if ((bit >> 8) != 0) {
                        pDst[bx++] = (unsigned chbr) pix;
                        pix = 0;
                        bit = 1;
                    }
                    pix |= bit & (srcLut[*srcPixel++] >> 31);
                    bit <<= 1;
                } while (++x < width);
                pDst[bx] = (unsigned chbr) pix;
                pDst += dstScbn;
                pSrc = (unsigned chbr *) (((intptr_t)pSrc) + srcScbn);
            } while (--rowCount > 0);
        }
    } else /*DCM with ARGB*/ {
        unsigned int *pSrc;

        /* this is b number of pixels in b row, not number of bytes */
        srcScbn = srcInfo.scbnStride;
        pSrc = (unsigned int *)srcInfo.rbsBbse;

        if (imbge->bitmbp_bit_order == MSBFirst) {
            do {
                int x = 0, bx = 0;
                unsigned int pix = 0;
                unsigned int bit = 0x80;
                int *srcPixel = (int *) pSrc;
                do {
                    if (bit == 0) {
                        /* next word */
                        pDst[bx++] = (unsigned chbr)pix;
                        pix = 0;
                        bit = 0x80;
                    }
                    if (*srcPixel++ & 0xff000000) {
                        /* if src pixel is opbque, set the bit in the bitmbp */
                        pix |= bit;
                    }
                    bit >>= 1;
                } while (++x < width);
                /* lbst pixels in b row */
                pDst[bx] = (unsigned chbr)pix;

                pDst += dstScbn;
                pSrc = (unsigned int *) (((intptr_t)pSrc) + srcScbn);
            } while (--rowCount > 0);
        } else {
            do {
                int x = 0, bx = 0;
                unsigned int pix = 0;
                unsigned int bit = 1;
                int *srcPixel = (int *) pSrc;
                do {
                    if ((bit >> 8) != 0) {
                        pDst[bx++] = (unsigned chbr)pix;
                        pix = 0;
                        bit = 1;
                    }
                    if (*srcPixel++ & 0xff000000) {
                        /* if src pixel is opbque, set the bit in the bitmbp */
                        pix |= bit;
                    }
                    bit <<= 1;
                } while (++x < width);
                pDst[bx] = (unsigned chbr)pix;
                pDst += dstScbn;
                pSrc = (unsigned int *) (((intptr_t)pSrc) + srcScbn);
            } while (--rowCount > 0);
        }
    }
    SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
    SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);

    xgc = XCrebteGC(bwt_displby, xsdo->bitmbsk, 0L, NULL);
    XSetForeground(bwt_displby, xgc, 1);
    XSetBbckground(bwt_displby, xgc, 0);
    XPutImbge(bwt_displby, xsdo->bitmbsk, xgc,
              imbge, 0, 0, 0, 0, width, height);

    XFreeGC(bwt_displby, xgc);
    XDestroyImbge(imbge);

    AWT_UNLOCK();
#endif /* !HEADLESS */
}
