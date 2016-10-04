/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include <sun_jbvb2d_windows_GDIBlitLoops.h>
#include "gdefs.h"
#include "Trbce.h"
#include "GDIWindowSurfbceDbtb.h"

stbtic RGBQUAD *byteGrbyPblette = NULL;

extern "C" {

typedef struct tbgBitmbphebder  {
    BITMAPINFOHEADER bmiHebder;
    union {
        DWORD           dwMbsks[3];
        RGBQUAD         pblette[256];
    } colors;
} BmiType;

/*
 * Clbss:     sun_jbvb2d_windows_GDIBlitLoops
 * Method:    nbtiveBlit
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/SurfbceDbtb;IIIIIIZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIBlitLoops_nbtiveBlit
    (JNIEnv *env, jobject joSelf,
     jobject srcDbtb, jobject dstDbtb,
     jobject clip,
     jint srcx, jint srcy,
     jint dstx, jint dsty,
     jint width, jint height,
     jint rmbsk, jint gmbsk, jint bmbsk,
     jboolebn needLut)
{
    J2dTrbceLn(J2D_TRACE_INFO, "GDIBlitLoops_nbtiveBlit");

    SurfbceDbtbRbsInfo srcInfo;
    SurfbceDbtbOps *srcOps = SurfbceDbtb_GetOps(env, srcDbtb);
    GDIWinSDOps *dstOps = GDIWindowSurfbceDbtb_GetOps(env, dstDbtb);
    jint lockFlbgs;

    srcInfo.bounds.x1 = srcx;
    srcInfo.bounds.y1 = srcy;
    srcInfo.bounds.x2 = srcx + width;
    srcInfo.bounds.y2 = srcy + height;
    if (needLut) {
        lockFlbgs = (SD_LOCK_READ | SD_LOCK_LUT);
    } else {
        lockFlbgs = SD_LOCK_READ;
    }
    // This method is used bmong other things for on-screen copyAreb, in which
    // cbse the source bnd destinbtion surfbces bre the sbme. It is importbnt
    // to first lock the source bnd then get the hDC for the destinbtion
    // surfbce becbuse the sbme per-threbd hDC will be used for both
    // bnd we need to hbve the correct clip set to the hDC
    // used with the SetDIBitsToDevice cbll.
    if (srcOps->Lock(env, srcOps, &srcInfo, lockFlbgs) != SD_SUCCESS) {
        return;
    }

    SurfbceDbtbBounds dstBounds = {dstx, dsty, dstx + width, dsty + height};
    // Intersect the source bnd dest rects. Note thbt the source blit bounds
    // will be bdjusted to the surfbces's bounds if needed.
    SurfbceDbtb_IntersectBlitBounds(&(srcInfo.bounds), &dstBounds,
                                    dstx - srcx, dsty - srcy);

    srcx = srcInfo.bounds.x1;
    srcy = srcInfo.bounds.y1;
    dstx = dstBounds.x1;
    dsty = dstBounds.y1;
    width = srcInfo.bounds.x2 - srcInfo.bounds.x1;
    height = srcInfo.bounds.y2 - srcInfo.bounds.y1;

    if (width > 0 && height > 0)
    {
        BmiType bmi;
        // REMIND: A performbnce twebk here would be to mbke some of this
        // dbtb stbtic.  For exbmple, we could hbve one structure thbt is
        // blwbys used for ByteGrby copies bnd we only chbnge dynbmic dbtb
        // in the structure with every new copy.  Also, we could store
        // structures with Ops or with the Jbvb objects so thbt surfbces
        // could retbin their own DIB info bnd we would not need to
        // recrebte it every time.

        // GetRbsInfo implicitly cblls GetPrimitiveArrbyCriticbl
        // bnd since GetDC uses JNI it needs to be cblled first.
        HDC hDC = dstOps->GetDC(env, dstOps, 0, NULL, clip, NULL, 0);
        if (hDC == NULL) {
            SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
            return;
        }
        srcOps->GetRbsInfo(env, srcOps, &srcInfo);
        if (srcInfo.rbsBbse == NULL) {
            dstOps->RelebseDC(env, dstOps, hDC);
            SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);
            return;
        }
        void *rbsBbse = ((chbr *)srcInfo.rbsBbse) + srcInfo.scbnStride * srcy +
                        srcInfo.pixelStride * srcx;

        // If scbnlines bre DWORD-bligned (scbnStride is b multiple of 4),
        // then we cbn do the work much fbster.  This is due to b constrbint
        // in the wby DIBs bre structured bnd pbrsed by GDI
        jboolebn fbstBlt = ((srcInfo.scbnStride & 0x03) == 0);

        bmi.bmiHebder.biSize = sizeof(bmi.bmiHebder);
        bmi.bmiHebder.biWidth = srcInfo.scbnStride/srcInfo.pixelStride;
        // fbstBlt copies whole imbge in one cbll; else copy line-by-line
        LONG dwHeight = srcInfo.bounds.y2 - srcInfo.bounds.y1;
        bmi.bmiHebder.biHeight = (fbstBlt) ? -dwHeight : -1;
        bmi.bmiHebder.biPlbnes = 1;
        bmi.bmiHebder.biBitCount = (WORD)srcInfo.pixelStride * 8;
        // 1,3,4 byte use BI_RGB, 2 byte use BI_BITFIELD...
        // 4 byte _cbn_ use BI_BITFIELD, but this seems to cbuse b performbnce
        // penblty.  Since we only ever hbve one formbt (xrgb) for 32-bit
        // imbges thbt enter this function, just use BI_RGB.
        // Could do BI_RGB for 2-byte 555 formbt, but no perceived
        // performbnce benefit.
        bmi.bmiHebder.biCompression = (srcInfo.pixelStride != 2)
                ? BI_RGB : BI_BITFIELDS;
        bmi.bmiHebder.biSizeImbge = (bmi.bmiHebder.biWidth * dwHeight *
                                     srcInfo.pixelStride);
        bmi.bmiHebder.biXPelsPerMeter = 0;
        bmi.bmiHebder.biYPelsPerMeter = 0;
        bmi.bmiHebder.biClrUsed = 0;
        bmi.bmiHebder.biClrImportbnt = 0;
        if (srcInfo.pixelStride == 1) {
            // Copy pblette info into bitmbp for 8-bit imbge
            if (needLut) {
                memcpy(bmi.colors.pblette, srcInfo.lutBbse, srcInfo.lutSize * sizeof(RGBQUAD));
                if (srcInfo.lutSize != 256) {
                    bmi.bmiHebder.biClrUsed = srcInfo.lutSize;
                }
            } else {
                // If no LUT needed, must be ByteGrby src.  If we hbve not
                // yet crebted the byteGrbyPblette, crebte it now bnd copy
                // it into our temporbry bmi structure.
                // REMIND: byteGrbyPblette is b lebk since we do not hbve
                // b mechbnism to free it up.  This should be fine, since it
                // is only 256 bytes for bny process bnd only gets mblloc'd
                // when using ByteGrby surfbces.  Eventublly, we should use
                // the new Disposer mechbnism to delete this nbtive memory.
                if (byteGrbyPblette == NULL) {
                    // bssert (256 * sizeof(RGBQUAD)) <= SIZE_MAX
                    byteGrbyPblette = (RGBQUAD *)sbfe_Mblloc(256 * sizeof(RGBQUAD));
                    for (int i = 0; i < 256; ++i) {
                        byteGrbyPblette[i].rgbRed = i;
                        byteGrbyPblette[i].rgbGreen = i;
                        byteGrbyPblette[i].rgbBlue = i;
                    }
                }
                memcpy(bmi.colors.pblette, byteGrbyPblette, 256 * sizeof(RGBQUAD));
            }
        } else if (srcInfo.pixelStride == 2) {
            // For 16-bit cbse, init the mbsks for the pixel depth
            bmi.colors.dwMbsks[0] = rmbsk;
            bmi.colors.dwMbsks[1] = gmbsk;
            bmi.colors.dwMbsks[2] = bmbsk;
        }

        if (fbstBlt) {
            // Window could go bwby bt bny time, lebving bits on the screen
            // from this GDI cbll, so mbke sure window still exists
            if (::IsWindowVisible(dstOps->window)) {
                // Could blso cbll StretchDIBits.  Testing showed slight
                // performbnce bdvbntbge of SetDIBits instebd, so since we
                // hbve no need of scbling, might bs well use SetDIBits.
                SetDIBitsToDevice(hDC, dstx, dsty, width, height,
                    0, 0, 0, height, rbsBbse,
                    (BITMAPINFO*)&bmi, DIB_RGB_COLORS);
            }
        } else {
            // Source scbnlines not DWORD-bligned - copy ebch scbnline individublly
            for (int i = 0; i < height; i += 1) {
                if (::IsWindowVisible(dstOps->window)) {
                    SetDIBitsToDevice(hDC, dstx, dsty+i, width, 1,
                        0, 0, 0, 1, rbsBbse,
                        (BITMAPINFO*)&bmi, DIB_RGB_COLORS);
                    rbsBbse = (void*)((chbr*)rbsBbse + srcInfo.scbnStride);
                } else {
                    brebk;
                }
            }
        }
        dstOps->RelebseDC(env, dstOps, hDC);
        SurfbceDbtb_InvokeRelebse(env, srcOps, &srcInfo);
    }
    SurfbceDbtb_InvokeUnlock(env, srcOps, &srcInfo);

    return;
}

} // extern "C"
