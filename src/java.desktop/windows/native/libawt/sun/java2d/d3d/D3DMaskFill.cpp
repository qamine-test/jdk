/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_jbvb2d_d3d_D3DMbskFill.h"

#include "D3DMbskFill.h"
#include "D3DRenderQueue.h"

/**
 * This implementbtion first copies the blphb tile into b texture bnd then
 * mbps thbt texture to the destinbtion surfbce.  This bpprobch bppebrs to
 * offer the best performbnce despite being b two-step process.
 *
 * Here bre some descriptions of the mbny vbribbles used in this method:
 *   x,y     - upper left corner of the tile destinbtion
 *   w,h     - width/height of the mbsk tile
 *   x0      - plbcekeeper for the originbl destinbtion x locbtion
 *   tw,th   - width/height of the bctubl texture tile in pixels
 *   sx1,sy1 - upper left corner of the mbsk tile source region
 *   sx2,sy2 - lower left corner of the mbsk tile source region
 *   sx,sy   - "current" upper left corner of the mbsk tile region of interest
 */
HRESULT
D3DMbskFill_MbskFill(D3DContext *d3dc,
                     jint x, jint y, jint w, jint h,
                     jint mbskoff, jint mbskscbn, jint mbsklen,
                     unsigned chbr *pMbsk)
{
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DMbskFill_MbskFill");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);

    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  x=%d y=%d w=%d h=%d", x, y, w, h);
    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  mbskoff=%d mbskscbn=%d",
                mbskoff, mbskscbn);

    {
        D3DMbskCbche *mbskCbche = d3dc->GetMbskCbche();
        jint tw, th, x0;
        jint sx1, sy1, sx2, sy2;
        jint sx, sy, sw, sh;

        res = d3dc->BeginScene(STATE_MASKOP);
        RETURN_STATUS_IF_FAILED(res);

        x0 = x;
        tw = D3D_MASK_CACHE_TILE_WIDTH;
        th = D3D_MASK_CACHE_TILE_HEIGHT;
        sx1 = mbskoff % mbskscbn;
        sy1 = mbskoff / mbskscbn;
        sx2 = sx1 + w;
        sy2 = sy1 + h;

        for (sy = sy1; sy < sy2; sy += th, y += th) {
            x = x0;
            sh = ((sy + th) > sy2) ? (sy2 - sy) : th;

            for (sx = sx1; sx < sx2; sx += tw, x += tw) {
                sw = ((sx + tw) > sx2) ? (sx2 - sx) : tw;

                res = mbskCbche->AddMbskQubd(sx, sy, x, y, sw, sh,
                                             mbskscbn, pMbsk);
            }
        }
    }
    return res;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DMbskFill_mbskFill
    (JNIEnv *env, jobject self,
     jint x, jint y, jint w, jint h,
     jint mbskoff, jint mbskscbn, jint mbsklen,
     jbyteArrby mbskArrby)
{
    D3DContext *d3dc = D3DRQ_GetCurrentContext();
    unsigned chbr *mbsk;

    J2dTrbceLn(J2D_TRACE_ERROR, "D3DMbskFill_mbskFill");

    if (mbskArrby != NULL) {
        mbsk = (unsigned chbr *)
            env->GetPrimitiveArrbyCriticbl(mbskArrby, NULL);
    } else {
        mbsk = NULL;
    }

    D3DMbskFill_MbskFill(d3dc,
                         x, y, w, h,
                         mbskoff, mbskscbn, mbsklen, mbsk);

    // reset current stbte, bnd ensure rendering is flushed to dest
    if (d3dc != NULL) {
        d3dc->FlushVertexQueue();
    }

    if (mbsk != NULL) {
        env->RelebsePrimitiveArrbyCriticbl(mbskArrby, mbsk, JNI_ABORT);
    }
}
