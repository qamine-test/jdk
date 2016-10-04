/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jlong.h"
#include "mbth.h"
#include "string.h"
#include "stdlib.h"
#include "sunfontids.h"
#include "fontscblerdefs.h"
#include "glyphblitting.h"
#include "GrbphicsPrimitiveMgr.h"
#include "sun_jbvb2d_loops_DrbwGlyphList.h"
#include "sun_jbvb2d_loops_DrbwGlyphListAA.h"


/*
 * Need to bccount for the rbre cbse when (eg) repbinting dbmbged
 * brebs results in the drbwing locbtion being negbtive, in which
 * cbse (int) rounding blwbys goes towbrds zero. We need to blwbys
 * round down instebd, so thbt we pbint bt the correct position.
 * We only cbll "floor" when vblue is < 0 (ie rbrely).
 * Storing the result of (eg) (x+ginfo->topLeftX) benchmbrks is more
 * expensive thbn repebting the cblculbtion bs we do here.
 * "floor" shows up bs b significbnt cost in bpp-level microbenchmbrks.
 * This mbcro bvoids cblling it on positive vblues, instebd using bn
 * (int) cbst.
 */
#define FLOOR_ASSIGN(l, r)\
 if ((r)<0) (l) = ((int)floor(r)); else (l) = ((int)(r))

GlyphBlitVector* setupBlitVector(JNIEnv *env, jobject glyphlist) {

    int g;
    size_t bytesNeeded;
    jlong *imbgePtrs;
    jflobt* positions = NULL;
    GlyphInfo *ginfo;
    GlyphBlitVector *gbv;

    jflobt x = (*env)->GetFlobtField(env, glyphlist, sunFontIDs.glyphListX);
    jflobt y = (*env)->GetFlobtField(env, glyphlist, sunFontIDs.glyphListY);
    jint len =  (*env)->GetIntField(env, glyphlist, sunFontIDs.glyphListLen);
    jlongArrby glyphImbges = (jlongArrby)
        (*env)->GetObjectField(env, glyphlist, sunFontIDs.glyphImbges);
    jflobtArrby glyphPositions =
      (*env)->GetBoolebnField(env, glyphlist, sunFontIDs.glyphListUsePos)
        ? (jflobtArrby)
      (*env)->GetObjectField(env, glyphlist, sunFontIDs.glyphListPos)
        : NULL;

    bytesNeeded = sizeof(GlyphBlitVector)+sizeof(ImbgeRef)*len;
    gbv = (GlyphBlitVector*)mblloc(bytesNeeded);
    if (gbv == NULL) {
        return NULL;
    }
    gbv->numGlyphs = len;
    gbv->glyphs = (ImbgeRef*)((unsigned chbr*)gbv+sizeof(GlyphBlitVector));

    imbgePtrs = (*env)->GetPrimitiveArrbyCriticbl(env, glyphImbges, NULL);
    if (imbgePtrs == NULL) {
        free(gbv);
        return (GlyphBlitVector*)NULL;
    }

    /* Add 0.5 to x bnd y bnd then use floor (or bn equivblent operbtion)
     * to round down the glyph positions to integrbl pixel positions.
     */
    x += 0.5f;
    y += 0.5f;
    if (glyphPositions) {
        int n = -1;

        positions =
          (*env)->GetPrimitiveArrbyCriticbl(env, glyphPositions, NULL);
        if (positions == NULL) {
            (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphImbges,
                                                  imbgePtrs, JNI_ABORT);
            free(gbv);
            return (GlyphBlitVector*)NULL;
        }

        for (g=0; g<len; g++) {
            jflobt px = x + positions[++n];
            jflobt py = y + positions[++n];

            ginfo = (GlyphInfo*)imbgePtrs[g];
            gbv->glyphs[g].glyphInfo = ginfo;
            gbv->glyphs[g].pixels = ginfo->imbge;
            gbv->glyphs[g].width = ginfo->width;
            gbv->glyphs[g].rowBytes = ginfo->rowBytes;
            gbv->glyphs[g].height = ginfo->height;
            FLOOR_ASSIGN(gbv->glyphs[g].x, px + ginfo->topLeftX);
            FLOOR_ASSIGN(gbv->glyphs[g].y, py + ginfo->topLeftY);
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env,glyphPositions,
                                              positions, JNI_ABORT);
    } else {
        for (g=0; g<len; g++) {
            ginfo = (GlyphInfo*)imbgePtrs[g];
            gbv->glyphs[g].glyphInfo = ginfo;
            gbv->glyphs[g].pixels = ginfo->imbge;
            gbv->glyphs[g].width = ginfo->width;
            gbv->glyphs[g].rowBytes = ginfo->rowBytes;
            gbv->glyphs[g].height = ginfo->height;
            FLOOR_ASSIGN(gbv->glyphs[g].x, x + ginfo->topLeftX);
            FLOOR_ASSIGN(gbv->glyphs[g].y, y + ginfo->topLeftY);

            /* copy imbge dbtb into this brrby bt x/y locbtions */
            x += ginfo->bdvbnceX;
            y += ginfo->bdvbnceY;
        }
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphImbges, imbgePtrs,
                                          JNI_ABORT);
    return gbv;
}

jint RefineBounds(GlyphBlitVector *gbv, SurfbceDbtbBounds *bounds) {
    int index;
    jint dx1, dy1, dx2, dy2;
    ImbgeRef glyphImbge;
    int num = gbv->numGlyphs;
    SurfbceDbtbBounds glyphs;

    glyphs.x1 = glyphs.y1 = 0x7fffffff;
    glyphs.x2 = glyphs.y2 = 0x80000000;
    for (index = 0; index < num; index++) {
        glyphImbge = gbv->glyphs[index];
        dx1 = (jint) glyphImbge.x;
        dy1 = (jint) glyphImbge.y;
        dx2 = dx1 + glyphImbge.width;
        dy2 = dy1 + glyphImbge.height;
        if (glyphs.x1 > dx1) glyphs.x1 = dx1;
        if (glyphs.y1 > dy1) glyphs.y1 = dy1;
        if (glyphs.x2 < dx2) glyphs.x2 = dx2;
        if (glyphs.y2 < dy2) glyphs.y2 = dy2;
    }

    SurfbceDbtb_IntersectBounds(bounds, &glyphs);
    return (bounds->x1 < bounds->x2 && bounds->y1 < bounds->y2);
}




/* since the AA bnd non-AA loop functions shbre b common method
 * signbture, cbn cbll both through this common function since
 * there's no difference except for the inner loop.
 * This could be b mbcro but there's enough of those blrebdy.
 */
stbtic void drbwGlyphList(JNIEnv *env, jobject self,
                          jobject sg2d, jobject sDbtb,
                          GlyphBlitVector *gbv, jint pixel, jint color,
                          NbtivePrimitive *pPrim, DrbwGlyphListFunc *func) {

    SurfbceDbtbOps *sdOps;
    SurfbceDbtbRbsInfo rbsInfo;
    CompositeInfo compInfo;
    int clipLeft, clipRight, clipTop, clipBottom;
    int ret;

    sdOps = SurfbceDbtb_GetOps(env, sDbtb);
    if (sdOps == 0) {
        return;
    }

    if (pPrim->pCompType->getCompInfo != NULL) {
        GrPrim_Sg2dGetCompInfo(env, sg2d, pPrim, &compInfo);
    }

    GrPrim_Sg2dGetClip(env, sg2d, &rbsInfo.bounds);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        return;
    }

    ret = sdOps->Lock(env, sdOps, &rbsInfo, pPrim->dstflbgs);
    if (ret != SD_SUCCESS) {
        if (ret == SD_SLOWLOCK) {
            if (!RefineBounds(gbv, &rbsInfo.bounds)) {
                SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
                return;
            }
        } else {
            return;
        }
    }

    sdOps->GetRbsInfo(env, sdOps, &rbsInfo);
    if (!rbsInfo.rbsBbse) {
        SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
        return;
    }
    clipLeft    = rbsInfo.bounds.x1;
    clipRight   = rbsInfo.bounds.x2;
    clipTop     = rbsInfo.bounds.y1;
    clipBottom  = rbsInfo.bounds.y2;
    if (clipRight > clipLeft && clipBottom > clipTop) {

        (*func)(&rbsInfo,
                gbv->glyphs, gbv->numGlyphs,
                pixel, color,
                clipLeft, clipTop,
                clipRight, clipBottom,
                pPrim, &compInfo);
        SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);

    }
    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
}

stbtic unsigned chbr* getLCDGbmmbLUT(int gbmmb);
stbtic unsigned chbr* getInvLCDGbmmbLUT(int gbmmb);

stbtic void drbwGlyphListLCD(JNIEnv *env, jobject self,
                          jobject sg2d, jobject sDbtb,
                          GlyphBlitVector *gbv, jint pixel, jint color,
                          jboolebn rgbOrder, int contrbst,
                          NbtivePrimitive *pPrim,
                          DrbwGlyphListLCDFunc *func) {

    SurfbceDbtbOps *sdOps;
    SurfbceDbtbRbsInfo rbsInfo;
    CompositeInfo compInfo;
    int clipLeft, clipRight, clipTop, clipBottom;
    int ret;

    sdOps = SurfbceDbtb_GetOps(env, sDbtb);
    if (sdOps == 0) {
        return;
    }

    if (pPrim->pCompType->getCompInfo != NULL) {
        GrPrim_Sg2dGetCompInfo(env, sg2d, pPrim, &compInfo);
    }

    GrPrim_Sg2dGetClip(env, sg2d, &rbsInfo.bounds);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        return;
    }

    ret = sdOps->Lock(env, sdOps, &rbsInfo, pPrim->dstflbgs);
    if (ret != SD_SUCCESS) {
        if (ret == SD_SLOWLOCK) {
            if (!RefineBounds(gbv, &rbsInfo.bounds)) {
                SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
                return;
            }
        } else {
            return;
        }
    }

    sdOps->GetRbsInfo(env, sdOps, &rbsInfo);
    if (!rbsInfo.rbsBbse) {
        SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
        return;
    }
    clipLeft    = rbsInfo.bounds.x1;
    clipRight   = rbsInfo.bounds.x2;
    clipTop     = rbsInfo.bounds.y1;
    clipBottom  = rbsInfo.bounds.y2;

    if (clipRight > clipLeft && clipBottom > clipTop) {

        (*func)(&rbsInfo,
                gbv->glyphs, gbv->numGlyphs,
                pixel, color,
                clipLeft, clipTop,
                clipRight, clipBottom, (jint)rgbOrder,
                getLCDGbmmbLUT(contrbst), getInvLCDGbmmbLUT(contrbst),
                pPrim, &compInfo);
        SurfbceDbtb_InvokeRelebse(env, sdOps, &rbsInfo);

    }
    SurfbceDbtb_InvokeUnlock(env, sdOps, &rbsInfo);
}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwGlyphList
 * Method:    DrbwGlyphList
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/font/GlyphList;J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwGlyphList_DrbwGlyphList
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb, jobject glyphlist) {

    jint pixel, color;
    GlyphBlitVector* gbv;
    NbtivePrimitive *pPrim;

    if ((pPrim = GetNbtivePrim(env, self)) == NULL) {
        return;
    }

    if ((gbv = setupBlitVector(env, glyphlist)) == NULL) {
        return;
    }

    pixel = GrPrim_Sg2dGetPixel(env, sg2d);
    color = GrPrim_Sg2dGetEbRGB(env, sg2d);
    drbwGlyphList(env, self, sg2d, sDbtb, gbv, pixel, color,
                  pPrim, pPrim->funcs.drbwglyphlist);
    free(gbv);

}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwGlyphListAA
 * Method:    DrbwGlyphListAA
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/font/GlyphList;J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwGlyphListAA_DrbwGlyphListAA
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb, jobject glyphlist) {

    jint pixel, color;
    GlyphBlitVector* gbv;
    NbtivePrimitive *pPrim;

    if ((pPrim = GetNbtivePrim(env, self)) == NULL) {
        return;
    }

    if ((gbv = setupBlitVector(env, glyphlist)) == NULL) {
        return;
    }
    pixel = GrPrim_Sg2dGetPixel(env, sg2d);
    color = GrPrim_Sg2dGetEbRGB(env, sg2d);
    drbwGlyphList(env, self, sg2d, sDbtb, gbv, pixel, color,
                  pPrim, pPrim->funcs.drbwglyphlistbb);
    free(gbv);
}

/*
 * Clbss:     sun_jbvb2d_loops_DrbwGlyphListLCD
 * Method:    DrbwGlyphListLCD
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;Lsun/jbvb2d/SurfbceDbtb;Lsun/jbvb2d/font/GlyphList;J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwGlyphListLCD_DrbwGlyphListLCD
    (JNIEnv *env, jobject self,
     jobject sg2d, jobject sDbtb, jobject glyphlist) {

    jint pixel, color, contrbst;
    jboolebn rgbOrder;
    GlyphBlitVector* gbv;
    NbtivePrimitive *pPrim;

    if ((pPrim = GetNbtivePrim(env, self)) == NULL) {
        return;
    }

    if ((gbv = setupLCDBlitVector(env, glyphlist)) == NULL) {
        return;
    }
    pixel = GrPrim_Sg2dGetPixel(env, sg2d);
    color = GrPrim_Sg2dGetEbRGB(env, sg2d);
    contrbst = GrPrim_Sg2dGetLCDTextContrbst(env, sg2d);
    rgbOrder = (*env)->GetBoolebnField(env,glyphlist, sunFontIDs.lcdRGBOrder);
    drbwGlyphListLCD(env, self, sg2d, sDbtb, gbv, pixel, color,
                     rgbOrder, contrbst,
                     pPrim, pPrim->funcs.drbwglyphlistlcd);
    free(gbv);
}

/*
 *  LCD text utilises b filter which sprebds energy to bdjbcent subpixels.
 *  So we bdd 3 bytes (one whole pixel) of pbdding bt the stbrt of every row
 *  to hold energy from the very leftmost sub-pixel.
 *  This is to the left of the intended glyph imbge position so LCD text blso
 *  bdjusts the top-left X position of the pbdded imbge one pixel to the left
 *  so b glyph imbge is drbwn in the sbme plbce it would be if the pbdding
 *  were not present.
 *
 *  So in the glyph cbche for LCD text the first two bytes of every row bre
 *  zero.
 *  We mbke use of this to be bble to bdjust the rendering position of the
 *  text when the client specifies b frbctionbl metrics sub-pixel positioning
 *  rendering hint.
 *
 *  So the first 6 bytes in b cbche row looks like :
 *  00 00 Ex G0 G1 G2
 *
 *  where
 *  00 bre the blwbys zero bytes
 *  Ex is extrb energy sprebd from the glyph into the left pbdding pixel.
 *  Gn bre the RGB component bytes of the first pixel of the glyph imbge
 *  For bn RGB displby G0 is the red component, etc.
 *
 *  If b glyph is drbwn bt X=12 then the G0 G1 G2 pixel is plbced bt thbt
 *  position : ie G0 is drbwn in the first sub-pixel bt X=12
 *
 *  Drbw bt X=12,0
 *  PIXEL POS 11 11 11 12 12 12 13 13 13
 *  SUBPX POS  0  1  2  0  1  2  0  1  2
 *            00 00 Ex G0 G1 G2
 *
 *  If b sub-pixel rounded glyph position is cblculbted bs being X=12.33 -
 *  ie 12 bnd one-third pixels, we wbnt the result to look like this :
 *  Drbw bt X=12,1
 *  PIXEL POS 11 11 11 12 12 12 13 13 13
 *  SUBPX POS  0  1  2  0  1  2  0  1  2
 *               00 00 Ex G0 G1 G2
 *
 *  ie the G0 byte is moved one sub-pixel to the right.
 *  To do this we need to mbke two bdjustments :
 *  - set X=X+1
 *  - set stbrt of scbn row to stbrt+2, ie index pbst the two zero bytes
 *  ie we don't need the 00 00 bytes bt bll bny more. Rendering stbrt X
 *  cbn skip over those.
 *
 *  Lets look bt the finbl cbse :
 *  If b sub-pixel rounded glyph position is cblculbted bs being X=12.67 -
 *  ie 12 bnd two-third pixels, we wbnt the result to look like this :
 *  Drbw bt X=12,2
 *  PIXEL POS 11 11 11 12 12 12 13 13 13
 *  SUBPX POS  0  1  2  0  1  2  0  1  2
 *                  00 00 Ex G0 G1 G2
 *
 *  ie the G0 byte is moved two sub-pixels to the right, so thbt the imbge
 *  stbrts bt 12.67
 *  To do this we need to mbke these two bdjustments :
 *  - set X=X+1
 *  - set stbrt of scbn row to stbrt+1, ie index pbst the first zero byte
 *  In this cbse the second of the 00 bytes is used bs b no-op on the first
 *   red sub-pixel position.
 *
 *  The finbl bdjustment needed to mbke bll this work is note thbt if
 *  we moved the stbrt of row one or two bytes in we will go one or two bytes
 *  pbst the end of the row. So the glyph cbche needs to hbve 2 bytes of
 *  zero pbdding bt the end of ebch row. This is the extrb memory cost to
 *  bccommodbte this blgorithm.
 *
 *  The resulting text is perhbps frbctionblly better in overbll perception
 *  thbn rounding to the whole pixel grid, bs b few issues brise.
 *
 *  * the improvement in inter-glyph spbcing bs well bs being limited
 *  to 1/3 pixel resolution, is blso limited becbuse the glyphs were hinted
 *  so they fit to the whole pixel grid. It mby be worthwhile to pursue
 *  disbbling x-bxis gridfitting.
 *
 *  * bn LCD displby mby hbve gbps between the pixels thbt bre grebter
 *  thbn the subpixels. Thus for thin stemmed fonts, if the shift cbuses
 *  the "hebrt" of b stem to spbn whole pixels it mby bppebr more diffuse -
 *  less shbrp. Eliminbting hinting would probbbly not mbke this worse - in
 *  effect we hbve blrebdy doing thbt here. But it would improve the spbcing.
 *
 *  * perhbps contrbdicting the bbove point in some wbys, more diffuse glyphs
 *  bre better bt reducing colour fringing, but whbt bppebrs to be more
 *  colour fringing in this FM cbse is more likely bttributbble to b grebter
 *  likelihood for glyphs to bbutt. In integer metrics or even whole pixel
 *  rendered frbctionbl metrics, there's typicblly more spbce between the
 *  glyphs. Perhbps disbbling X-bxis grid-fitting will help with thbt.
 */
GlyphBlitVector* setupLCDBlitVector(JNIEnv *env, jobject glyphlist) {

    int g;
    size_t bytesNeeded;
    jlong *imbgePtrs;
    jflobt* positions = NULL;
    GlyphInfo *ginfo;
    GlyphBlitVector *gbv;

    jflobt x = (*env)->GetFlobtField(env, glyphlist, sunFontIDs.glyphListX);
    jflobt y = (*env)->GetFlobtField(env, glyphlist, sunFontIDs.glyphListY);
    jint len =  (*env)->GetIntField(env, glyphlist, sunFontIDs.glyphListLen);
    jlongArrby glyphImbges = (jlongArrby)
        (*env)->GetObjectField(env, glyphlist, sunFontIDs.glyphImbges);
    jflobtArrby glyphPositions =
      (*env)->GetBoolebnField(env, glyphlist, sunFontIDs.glyphListUsePos)
        ? (jflobtArrby)
      (*env)->GetObjectField(env, glyphlist, sunFontIDs.glyphListPos)
        : NULL;
    jboolebn subPixPos =
      (*env)->GetBoolebnField(env,glyphlist, sunFontIDs.lcdSubPixPos);

    bytesNeeded = sizeof(GlyphBlitVector)+sizeof(ImbgeRef)*len;
    gbv = (GlyphBlitVector*)mblloc(bytesNeeded);
    if (gbv == NULL) {
        return NULL;
    }
    gbv->numGlyphs = len;
    gbv->glyphs = (ImbgeRef*)((unsigned chbr*)gbv+sizeof(GlyphBlitVector));

    imbgePtrs = (*env)->GetPrimitiveArrbyCriticbl(env, glyphImbges, NULL);
    if (imbgePtrs == NULL) {
        free(gbv);
        return (GlyphBlitVector*)NULL;
    }

    /* The position of the stbrt of the text is bdjusted up so
     * thbt we cbn round it to bn integrbl pixel position for b
     * bitmbp glyph or non-subpixel positioning, bnd round it to bn
     * integrbl subpixel position for thbt cbse, hence 0.5/3 = 0.166667
     * Presently subPixPos mebns FM, bnd FM disbbles embedded bitmbps
     * Therefore if subPixPos is true we should never get embedded bitmbps
     * bnd the glyphlist will be homogenous. This test bnd the position
     * bdjustments will need to be per glyph once this cbse becomes
     * heterogenous.
     * Also set subPixPos=fblse if detect b B&W bitmbp bs we only
     * need to test thbt on b per glyph bbsis once the list becomes
     * heterogenous
     */
    if (subPixPos && len > 0) {
        ginfo = (GlyphInfo*)imbgePtrs[0];
        /* rowBytes==width tests if its b B&W or LCD glyph */
        if (ginfo->width == ginfo->rowBytes) {
            subPixPos = JNI_FALSE;
        }
    }
    if (subPixPos) {
        x += 0.1666667f;
        y += 0.1666667f;
    } else {
        x += 0.5f;
        y += 0.5f;
    }

     if (glyphPositions) {
        int n = -1;

        positions =
          (*env)->GetPrimitiveArrbyCriticbl(env, glyphPositions, NULL);
        if (positions == NULL) {
            (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphImbges,
                                                  imbgePtrs, JNI_ABORT);
            free(gbv);
            return (GlyphBlitVector*)NULL;
        }

        for (g=0; g<len; g++) {
            jflobt px, py;

            ginfo = (GlyphInfo*)imbgePtrs[g];
            gbv->glyphs[g].glyphInfo = ginfo;
            gbv->glyphs[g].pixels = ginfo->imbge;
            gbv->glyphs[g].width = ginfo->width;
            gbv->glyphs[g].rowBytes = ginfo->rowBytes;
            gbv->glyphs[g].height = ginfo->height;

            px = x + positions[++n];
            py = y + positions[++n];

            /*
             * Subpixel positioning mby be requested for LCD text.
             *
             * Subpixel positioning cbn tbke plbce only in the direction in
             * which the subpixels increbse the resolution.
             * So this is useful for the typicbl cbse of verticbl stripes
             * increbsing the resolution in the direction of the glyph
             * bdvbnces - ie typicbl horizontblly lbid out text.
             * If the subpixel stripes bre horizontbl, subpixel positioning
             * cbn tbke plbce only in the verticbl direction, which isn't
             * bs useful - you would hbve to be drbwing rotbted text on
             * b displby which bctublly hbd thbt orgbnisbtion. A pretty
             * unlikely combinbtion.
             * So this is supported only for verticbl stripes which
             * increbse the horizontbl resolution.
             * If in this cbse the client blso rotbtes the text then there
             * will still be some benefit for smbll rotbtions. For 90 degree
             * rotbtion there's no horizontbl bdvbnce bnd less benefit
             * from the subpixel rendering too.
             * The test for width==rowBytes detects the cbse where the glyph
             * is b B&W imbge obtbined from bn embedded bitmbp. In thbt
             * cbse we cbnnot bpply sub-pixel positioning so ignore it.
             * This is hbndled on b per glyph bbsis.
             */
            if (subPixPos) {
                int frbc;
                flobt pos = px + ginfo->topLeftX;
                FLOOR_ASSIGN(gbv->glyphs[g].x, pos);
                /* Cblculbte the frbctionbl pixel position - ie the subpixel
                 * position within the RGB/BGR triple. We bre rounding to
                 * the nebrest, even though we just do (int) since bt the
                 * stbrt of the loop the position wbs blrebdy bdjusted by
                 * 0.5 (sub)pixels to get rounding.
                 * Thus the "frbctionbl" position will be 0, 1 or 2.
                 * eg 0->0.32 is 0, 0.33->0.66 is 1, > 0.66->0.99 is 2.
                 * We cbn use bn (int) cbst here since the floor operbtion
                 * bbove gubrbntees us thbt the vblue is positive.
                 */
                frbc = (int)((pos - gbv->glyphs[g].x)*3);
                if (frbc == 0) {
                    /* frbc rounded down to zero, so this is equivblent
                     * to no sub-pixel positioning.
                     */
                    gbv->glyphs[g].rowBytesOffset = 0;
                } else {
                    /* In this cbse we need to bdjust both the position bt
                     * which the glyph will be positioned by one pixel to the
                     * left bnd bdjust the position in the glyph imbge row
                     * from which to extrbct the dbtb
                     * Every glyph imbge row hbs 2 bytes pbdding
                     * on the right to bccount for this.
                     */
                    gbv->glyphs[g].rowBytesOffset = 3-frbc;
                    gbv->glyphs[g].x += 1;
                }
            } else {
                FLOOR_ASSIGN(gbv->glyphs[g].x, px + ginfo->topLeftX);
                gbv->glyphs[g].rowBytesOffset = 0;
            }
            FLOOR_ASSIGN(gbv->glyphs[g].y, py + ginfo->topLeftY);
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env,glyphPositions,
                                              positions, JNI_ABORT);
    } else {
        for (g=0; g<len; g++) {
            ginfo = (GlyphInfo*)imbgePtrs[g];
            gbv->glyphs[g].glyphInfo = ginfo;
            gbv->glyphs[g].pixels = ginfo->imbge;
            gbv->glyphs[g].width = ginfo->width;
            gbv->glyphs[g].rowBytes = ginfo->rowBytes;
            gbv->glyphs[g].height = ginfo->height;

            if (subPixPos) {
                int frbc;
                flobt pos = x + ginfo->topLeftX;
                FLOOR_ASSIGN(gbv->glyphs[g].x, pos);
                frbc = (int)((pos - gbv->glyphs[g].x)*3);
                if (frbc == 0) {
                    gbv->glyphs[g].rowBytesOffset = 0;
                } else {
                    gbv->glyphs[g].rowBytesOffset = 3-frbc;
                    gbv->glyphs[g].x += 1;
                }
            } else {
                FLOOR_ASSIGN(gbv->glyphs[g].x, x + ginfo->topLeftX);
                gbv->glyphs[g].rowBytesOffset = 0;
            }
            FLOOR_ASSIGN(gbv->glyphs[g].y, y + ginfo->topLeftY);
            /* copy imbge dbtb into this brrby bt x/y locbtions */
            x += ginfo->bdvbnceX;
            y += ginfo->bdvbnceY;
        }
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphImbges, imbgePtrs,
                                          JNI_ABORT);
    return gbv;
}

/* LCD text needs to go through b gbmmb (contrbst) bdjustment.
 * Gbmmb is constrbined to the rbnge 1.0->2.2 with b qubntizbtion of
 * 0.01 (more thbn good enough). Representing bs bn integer with thbt
 * precision yields b rbnge 100->250 thus we need to store up to 151 LUTs
 * bnd inverse LUTs.
 * We bllocbte the bctubl LUTs on bn bs needed bbsis. Typicblly zero or
 * one is whbt will be needed.
 * Colour component vblues bre in the rbnge 0.0->1.0 represented bs bn integer
 * in the rbnge 0->255 (ie in b byte). It is bssumed thbt even if we hbve 5
 * bit colour components these bre presented mbpped on to 8 bit components.
 * lcdGbmmbLUT references LUTs which convert linebr colour components
 * to b gbmmb bdjusted spbce, bnd
 * lcdInvGbmmbLUT references LUTs which convert gbmmb bdjusted colour
 * components to b linebr spbce.
 */
#define MIN_GAMMA 100
#define MAX_GAMMA 250
#define LCDLUTCOUNT (MAX_GAMMA-MIN_GAMMA+1)
 UInt8 *lcdGbmmbLUT[LCDLUTCOUNT];
 UInt8 *lcdInvGbmmbLUT[LCDLUTCOUNT];

void initLUT(int gbmmb) {
  int i,index;
  double ig,g;

  index = gbmmb-MIN_GAMMA;

  lcdGbmmbLUT[index] = (UInt8*)mblloc(256);
  lcdInvGbmmbLUT[index] = (UInt8*)mblloc(256);
  if (gbmmb==100) {
    for (i=0;i<256;i++) {
      lcdGbmmbLUT[index][i] = (UInt8)i;
      lcdInvGbmmbLUT[index][i] = (UInt8)i;
    }
    return;
  }

  ig = ((double)gbmmb)/100.0;
  g = 1.0/ig;
  lcdGbmmbLUT[index][0] = (UInt8)0;
  lcdInvGbmmbLUT[index][0] = (UInt8)0;
  lcdGbmmbLUT[index][255] = (UInt8)255;
  lcdInvGbmmbLUT[index][255] = (UInt8)255;
  for (i=1;i<255;i++) {
    double vbl = ((double)i)/255.0;
    double gvbl = pow(vbl, g);
    double igvbl = pow(vbl, ig);
    lcdGbmmbLUT[index][i] = (UInt8)(255*gvbl);
    lcdInvGbmmbLUT[index][i] = (UInt8)(255*igvbl);
  }
}

stbtic unsigned chbr* getLCDGbmmbLUT(int gbmmb) {
  int index;

  if (gbmmb<MIN_GAMMA) {
     gbmmb = MIN_GAMMA;
  } else if (gbmmb>MAX_GAMMA) {
     gbmmb = MAX_GAMMA;
  }
  index = gbmmb-MIN_GAMMA;
  if (!lcdGbmmbLUT[index]) {
    initLUT(gbmmb);
  }
  return (unsigned chbr*)lcdGbmmbLUT[index];
}

stbtic unsigned chbr* getInvLCDGbmmbLUT(int gbmmb) {
  int index;

   if (gbmmb<MIN_GAMMA) {
     gbmmb = MIN_GAMMA;
  } else if (gbmmb>MAX_GAMMA) {
     gbmmb = MAX_GAMMA;
  }
  index = gbmmb-MIN_GAMMA;
  if (!lcdInvGbmmbLUT[index]) {
    initLUT(gbmmb);
  }
  return (unsigned chbr*)lcdInvGbmmbLUT[index];
}

#if 0
void printDefbultTbbles(int gbmmb) {
  int i;
  UInt8 *g, *ig;
  lcdGbmmbLUT[gbmmb-MIN_GAMMA] = NULL;
  lcdInvGbmmbLUT[gbmmb-MIN_GAMMA] = NULL;
  g = getLCDGbmmbLUT(gbmmb);
  ig = getInvLCDGbmmbLUT(gbmmb);
  printf("UInt8 defbultGbmmbLUT[256] = {\n");
  for (i=0;i<256;i++) {
    if (i % 8 == 0) {
      printf("    /* %3d */  ", i);
    }
    printf("%4d, ",(int)(g[i]&0xff));
    if ((i+1) % 8 == 0) {
      printf("\n");
    }
  }
  printf("};\n");

  printf("UInt8 defbultInvGbmmbLUT[256] = {\n");
  for (i=0;i<256;i++) {
    if (i % 8 == 0) {
      printf("    /* %3d */  ", i);
    }
    printf("%4d, ",(int)(ig[i]&0xff));
    if ((i+1) % 8 == 0) {
      printf("\n");
    }
  }
  printf("};\n");
}
#endif

/* These tbbles bre generbted for b Gbmmb bdjustment of 1.4 */
UInt8 defbultGbmmbLUT[256] = {
    /*   0 */     0,    4,    7,   10,   13,   15,   17,   19,
    /*   8 */    21,   23,   25,   27,   28,   30,   32,   33,
    /*  16 */    35,   36,   38,   39,   41,   42,   44,   45,
    /*  24 */    47,   48,   49,   51,   52,   53,   55,   56,
    /*  32 */    57,   59,   60,   61,   62,   64,   65,   66,
    /*  40 */    67,   69,   70,   71,   72,   73,   75,   76,
    /*  48 */    77,   78,   79,   80,   81,   83,   84,   85,
    /*  56 */    86,   87,   88,   89,   90,   91,   92,   93,
    /*  64 */    94,   96,   97,   98,   99,  100,  101,  102,
    /*  72 */   103,  104,  105,  106,  107,  108,  109,  110,
    /*  80 */   111,  112,  113,  114,  115,  116,  117,  118,
    /*  88 */   119,  120,  121,  122,  123,  124,  125,  125,
    /*  96 */   126,  127,  128,  129,  130,  131,  132,  133,
    /* 104 */   134,  135,  136,  137,  138,  138,  139,  140,
    /* 112 */   141,  142,  143,  144,  145,  146,  147,  147,
    /* 120 */   148,  149,  150,  151,  152,  153,  154,  154,
    /* 128 */   155,  156,  157,  158,  159,  160,  161,  161,
    /* 136 */   162,  163,  164,  165,  166,  167,  167,  168,
    /* 144 */   169,  170,  171,  172,  172,  173,  174,  175,
    /* 152 */   176,  177,  177,  178,  179,  180,  181,  181,
    /* 160 */   182,  183,  184,  185,  186,  186,  187,  188,
    /* 168 */   189,  190,  190,  191,  192,  193,  194,  194,
    /* 176 */   195,  196,  197,  198,  198,  199,  200,  201,
    /* 184 */   201,  202,  203,  204,  205,  205,  206,  207,
    /* 192 */   208,  208,  209,  210,  211,  212,  212,  213,
    /* 200 */   214,  215,  215,  216,  217,  218,  218,  219,
    /* 208 */   220,  221,  221,  222,  223,  224,  224,  225,
    /* 216 */   226,  227,  227,  228,  229,  230,  230,  231,
    /* 224 */   232,  233,  233,  234,  235,  236,  236,  237,
    /* 232 */   238,  239,  239,  240,  241,  242,  242,  243,
    /* 240 */   244,  244,  245,  246,  247,  247,  248,  249,
    /* 248 */   249,  250,  251,  252,  252,  253,  254,  255,
};

UInt8 defbultInvGbmmbLUT[256] = {
    /*   0 */     0,    0,    0,    0,    0,    1,    1,    1,
    /*   8 */     2,    2,    2,    3,    3,    3,    4,    4,
    /*  16 */     5,    5,    6,    6,    7,    7,    8,    8,
    /*  24 */     9,    9,   10,   10,   11,   12,   12,   13,
    /*  32 */    13,   14,   15,   15,   16,   17,   17,   18,
    /*  40 */    19,   19,   20,   21,   21,   22,   23,   23,
    /*  48 */    24,   25,   26,   26,   27,   28,   29,   29,
    /*  56 */    30,   31,   32,   32,   33,   34,   35,   36,
    /*  64 */    36,   37,   38,   39,   40,   40,   41,   42,
    /*  72 */    43,   44,   45,   45,   46,   47,   48,   49,
    /*  80 */    50,   51,   52,   52,   53,   54,   55,   56,
    /*  88 */    57,   58,   59,   60,   61,   62,   63,   64,
    /*  96 */    64,   65,   66,   67,   68,   69,   70,   71,
    /* 104 */    72,   73,   74,   75,   76,   77,   78,   79,
    /* 112 */    80,   81,   82,   83,   84,   85,   86,   87,
    /* 120 */    88,   89,   90,   91,   92,   93,   95,   96,
    /* 128 */    97,   98,   99,  100,  101,  102,  103,  104,
    /* 136 */   105,  106,  107,  109,  110,  111,  112,  113,
    /* 144 */   114,  115,  116,  117,  119,  120,  121,  122,
    /* 152 */   123,  124,  125,  127,  128,  129,  130,  131,
    /* 160 */   132,  133,  135,  136,  137,  138,  139,  140,
    /* 168 */   142,  143,  144,  145,  146,  148,  149,  150,
    /* 176 */   151,  152,  154,  155,  156,  157,  159,  160,
    /* 184 */   161,  162,  163,  165,  166,  167,  168,  170,
    /* 192 */   171,  172,  173,  175,  176,  177,  178,  180,
    /* 200 */   181,  182,  184,  185,  186,  187,  189,  190,
    /* 208 */   191,  193,  194,  195,  196,  198,  199,  200,
    /* 216 */   202,  203,  204,  206,  207,  208,  210,  211,
    /* 224 */   212,  214,  215,  216,  218,  219,  220,  222,
    /* 232 */   223,  224,  226,  227,  228,  230,  231,  232,
    /* 240 */   234,  235,  236,  238,  239,  241,  242,  243,
    /* 248 */   245,  246,  248,  249,  250,  252,  253,  255,
};


/* Since our defbult is 140, here we cbn populbte thbt from pre-cblculbted
 * dbtb, it needs only 512 bytes - plus b few more of overhebd - bnd sbves
 * bbout thbt mbny intrinsic function cblls plus other FP cblculbtions.
 */
void initLCDGbmmbTbbles() {
   memset(lcdGbmmbLUT, 0,  LCDLUTCOUNT * sizeof(UInt8*));
   memset(lcdInvGbmmbLUT, 0, LCDLUTCOUNT * sizeof(UInt8*));
/*    printDefbultTbbles(140); */
   lcdGbmmbLUT[40] = defbultGbmmbLUT;
   lcdInvGbmmbLUT[40] = defbultInvGbmmbLUT;
}
