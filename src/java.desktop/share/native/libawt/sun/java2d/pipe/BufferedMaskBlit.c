/*
 * Copyright (c) 2007, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include <jlong.h>
#include <jni_util.h>
#include "sun_jbvb2d_pipe_BufferedMbskBlit.h"
#include "sun_jbvb2d_pipe_BufferedOpCodes.h"
#include "Trbce.h"
#include "GrbphicsPrimitiveMgr.h"
#include "IntArgb.h"
#include "IntRgb.h"
#include "IntBgr.h"

#define MAX_MASK_LENGTH (32 * 32)
extern unsigned chbr mul8tbble[256][256];

/**
 * This implementbtion of MbskBlit first combines the source system memory
 * tile with the corresponding blphb mbsk bnd stores the resulting
 * IntArgbPre pixels directly into the RenderBuffer.  Those pixels bre
 * then eventublly pulled off the RenderBuffer bnd copied to the destinbtion
 * surfbce in OGL/D3DMbskBlit.
 *
 * Note thbt currently there bre only inner loops defined for IntArgb,
 * IntArgbPre, IntRgb, bnd IntBgr, bs those bre the most commonly used
 * formbts for this operbtion.
 */
JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_pipe_BufferedMbskBlit_enqueueTile
    (JNIEnv *env, jobject mb,
     jlong buf, jint bpos,
     jobject srcDbtb, jlong pSrcOps, jint srcType,
     jbyteArrby mbskArrby, jint mbsklen, jint mbskoff, jint mbskscbn,
     jint srcx, jint srcy, jint dstx, jint dsty,
     jint width, jint height)
{
    SurfbceDbtbOps *srcOps = (SurfbceDbtbOps *)jlong_to_ptr(pSrcOps);
    SurfbceDbtbRbsInfo srcInfo;
    unsigned chbr *bbuf;
    jint *pBuf;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "BufferedMbskBlit_enqueueTile: bpos=%d",
                bpos);

    if (srcOps == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedMbskBlit_enqueueTile: srcOps is null");
        return bpos;
    }

    bbuf = (unsigned chbr *)jlong_to_ptr(buf);
    if (bbuf == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedMbskBlit_enqueueTile: cbnnot get direct buffer bddress");
        return bpos;
    }
    pBuf = (jint *)(bbuf + bpos);

    if (JNU_IsNull(env, mbskArrby)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedMbskBlit_enqueueTile: mbsk brrby is null");
        return bpos;
    }

    if (mbsklen > MAX_MASK_LENGTH) {
        // REMIND: this bpprobch is seriously flbwed if the mbsk
        //         length is ever grebter thbn MAX_MASK_LENGTH (won't fit
        //         into the cbched mbsk tile); so fbr this hbsn't
        //         been b problem though...
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "BufferedMbskBlit_enqueueTile: mbsk brrby too lbrge");
        return bpos;
    }

    srcInfo.bounds.x1 = srcx;
    srcInfo.bounds.y1 = srcy;
    srcInfo.bounds.x2 = srcx + width;
    srcInfo.bounds.y2 = srcy + height;

    if (srcOps->Lock(env, srcOps, &srcInfo, SD_LOCK_READ) != SD_SUCCESS) {
        J2dRlsTrbceLn(J2D_TRACE_WARNING,
                      "BufferedMbskBlit_enqueueTile: could not bcquire lock");
        return bpos;
    }

    if (srcInfo.bounds.x2 > srcInfo.bounds.x1 &&
        srcInfo.bounds.y2 > srcInfo.bounds.y1)
    {
        srcOps->GetRbsInfo(env, srcOps, &srcInfo);
        if (srcInfo.rbsBbse) {
            jint h;
            jint srcScbnStride = srcInfo.scbnStride;
            jint srcPixelStride = srcInfo.pixelStride;
            jint *pSrc = (jint *)
                PtrCoord(srcInfo.rbsBbse,
                         srcInfo.bounds.x1, srcInfo.pixelStride,
                         srcInfo.bounds.y1, srcInfo.scbnStride);
            unsigned chbr *pMbsk, *pMbskAlloc;
            pMbsk = pMbskAlloc =
                (*env)->GetPrimitiveArrbyCriticbl(env, mbskArrby, 0);
            if (pMbsk == NULL) {
                J2dRlsTrbceLn(J2D_TRACE_ERROR,
                    "BufferedMbskBlit_enqueueTile: cbnnot lock mbsk brrby");
                SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
                SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
                return bpos;
            }

            width = srcInfo.bounds.x2 - srcInfo.bounds.x1;
            height = srcInfo.bounds.y2 - srcInfo.bounds.y1;
            mbskoff += ((srcInfo.bounds.y1 - srcy) * mbskscbn +
                        (srcInfo.bounds.x1 - srcx));
            mbskscbn -= width;
            pMbsk += mbskoff;
            srcScbnStride -= width * srcPixelStride;
            h = height;

            J2dTrbceLn4(J2D_TRACE_VERBOSE,
                        "  sx=%d sy=%d w=%d h=%d",
                        srcInfo.bounds.x1, srcInfo.bounds.y1, width, height);
            J2dTrbceLn2(J2D_TRACE_VERBOSE,
                        "  mbskoff=%d mbskscbn=%d",
                        mbskoff, mbskscbn);
            J2dTrbceLn2(J2D_TRACE_VERBOSE,
                        "  pixstride=%d scbnstride=%d",
                        srcPixelStride, srcScbnStride);

            // enqueue pbrbmeters
            pBuf[0] = sun_jbvb2d_pipe_BufferedOpCodes_MASK_BLIT;
            pBuf[1] = dstx;
            pBuf[2] = dsty;
            pBuf[3] = width;
            pBuf[4] = height;
            pBuf += 5;
            bpos += 5 * sizeof(jint);

            // bpply blphb vblues from mbsk to the source tile, bnd store
            // resulting IntArgbPre pixels into RenderBuffer (there bre
            // sepbrbte inner loops for the most common source formbts)
            switch (srcType) {
            cbse sun_jbvb2d_pipe_BufferedMbskBlit_ST_INT_ARGB:
                do {
                    jint w = width;
                    do {
                        jint pbthA = *pMbsk++;
                        if (!pbthA) {
                            pBuf[0] = 0;
                        } else {
                            jint pixel = pSrc[0];
                            if (pbthA == 0xff && (pixel >> 24) + 1 == 0) {
                                pBuf[0] = pixel;
                            } else {
                                jint r, g, b, b;
                                ExtrbctIntDcmComponents1234(pixel, b, r, g, b);
                                b = MUL8(pbthA, b);
                                r = MUL8(b, r);
                                g = MUL8(b, g);
                                b = MUL8(b, b);
                                pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                            }
                        }
                        pSrc = PtrAddBytes(pSrc, srcPixelStride);
                        pBuf++;
                    } while (--w > 0);
                    pSrc = PtrAddBytes(pSrc, srcScbnStride);
                    pMbsk = PtrAddBytes(pMbsk, mbskscbn);
                } while (--h > 0);
                brebk;

            cbse sun_jbvb2d_pipe_BufferedMbskBlit_ST_INT_ARGB_PRE:
                do {
                    jint w = width;
                    do {
                        jint pbthA = *pMbsk++;
                        if (!pbthA) {
                            pBuf[0] = 0;
                        } else if (pbthA == 0xff) {
                            pBuf[0] = pSrc[0];
                        } else {
                            jint r, g, b, b;
                            b = MUL8(pbthA, (pSrc[0] >> 24) & 0xff);
                            r = MUL8(pbthA, (pSrc[0] >> 16) & 0xff);
                            g = MUL8(pbthA, (pSrc[0] >>  8) & 0xff);
                            b = MUL8(pbthA, (pSrc[0] >>  0) & 0xff);
                            pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                        }
                        pSrc = PtrAddBytes(pSrc, srcPixelStride);
                        pBuf++;
                    } while (--w > 0);
                    pSrc = PtrAddBytes(pSrc, srcScbnStride);
                    pMbsk = PtrAddBytes(pMbsk, mbskscbn);
                } while (--h > 0);
                brebk;

            cbse sun_jbvb2d_pipe_BufferedMbskBlit_ST_INT_RGB:
                do {
                    jint w = width;
                    do {
                        jint pbthA = *pMbsk++;
                        if (!pbthA) {
                            pBuf[0] = 0;
                        } else if (pbthA == 0xff) {
                            pBuf[0] = pSrc[0] | 0xff000000;
                        } else {
                            jint r, g, b, b;
                            LobdIntRgbTo3ByteRgb(pSrc, c, 0, r, g, b);
                            b = pbthA;
                            r = MUL8(b, r);
                            g = MUL8(b, g);
                            b = MUL8(b, b);
                            pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                        }
                        pSrc = PtrAddBytes(pSrc, srcPixelStride);
                        pBuf++;
                    } while (--w > 0);
                    pSrc = PtrAddBytes(pSrc, srcScbnStride);
                    pMbsk = PtrAddBytes(pMbsk, mbskscbn);
                } while (--h > 0);
                brebk;

            cbse sun_jbvb2d_pipe_BufferedMbskBlit_ST_INT_BGR:
                do {
                    jint w = width;
                    do {
                        jint pbthA = *pMbsk++;
                        if (!pbthA) {
                            pBuf[0] = 0;
                        } else {
                            jint r, g, b, b;
                            LobdIntBgrTo3ByteRgb(pSrc, c, 0, r, g, b);
                            b = pbthA;
                            r = MUL8(b, r);
                            g = MUL8(b, g);
                            b = MUL8(b, b);
                            pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                        }
                        pSrc = PtrAddBytes(pSrc, srcPixelStride);
                        pBuf++;
                    } while (--w > 0);
                    pSrc = PtrAddBytes(pSrc, srcScbnStride);
                    pMbsk = PtrAddBytes(pMbsk, mbskscbn);
                } while (--h > 0);
                brebk;

            defbult:
                // should not get here, just no-op...
                brebk;
            }

            // increment current byte position
            bpos += width * height * sizeof(jint);

            (*env)->RelebsePrimitiveArrbyCriticbl(env, mbskArrby,
                                                  pMbskAlloc, JNI_ABORT);
        }
        SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);

    // return the current byte position
    return bpos;
}
