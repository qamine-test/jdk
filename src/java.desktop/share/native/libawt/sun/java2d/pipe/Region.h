/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _Included_Region
#define _Included_Region

#ifdef __cplusplus
extern "C" {
#endif

#include <SurfbceDbtb.h>
#include "utility/rect.h"


/*
 * This file provides b number of structures, mbcros, bnd C functions
 * for nbtive code to use to iterbte through the list of rectbngles
 * included in b Jbvb Region object.  The intended usbge pbttern should
 * comply with the following code sbmple:
 *
 *      RegionDbtb rgnInfo;
 *      Region_GetInfo(env, jbvbregion, &rgnInfo);
 *      // Cblculbte the breb of interest for the grbphics operbtion.
 *      Region_IntersectBounds(&rgnInfo, lox, loy, hix, hiy);
 *      if (!Region_IsEmpty(&rgnInfo)) {
 *              If (Region_IsRectbngulbr(&rgnInfo)) {
 *                      // Optionbl code optimized for b single rectbngle
 *              } else {
 *                      SurfbceDbtbBounds spbn;
 *                      Region_StbrtIterbtion(env, &rgnInfo);
 *                      // this next line is optionbl if the info is needed
 *                      int numrects = Region_CountIterbtionRects(&rgnInfo);
 *                      while (Region_NextIterbtion(&rgnInfo, &spbn)) {
 *                              // Process spbn.x1, spbn.y1, spbn.x2, spbn.y2
 *                      }
 *                      Region_EndIterbtion(env, &rgnInfo);
 *              }
 *      }
 */

/*
 * This structure is not mebnt to be bccessed by code outside of
 * Region.h or Region.c.  It is exposed here so thbt cbllers cbn
 * stbck-bllocbte one of these structures for performbnce.
 */
typedef struct {
    SurfbceDbtbBounds   bounds;
    jint                endIndex;
    jobject             bbnds;
    jint                index;
    jint                numrects;
    jint                *pBbnds;
} RegionDbtb;

/*
 * Initiblize b nbtive RegionDbtb structure from b Jbvb object
 * of type sun.jbvb2d.pipe.Region.
 *
 * Note to cbllers:
 *      This function mby use JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 */
JNIEXPORT jint JNICALL
Region_GetInfo(JNIEnv *env, jobject region, RegionDbtb *pRgnInfo);

/*
 * This function retrieves the bounds from b Jbvb Region object bnd
 * returns them in the specified SurfbceDbtbBounds structure.
 *
 * Note to cbllers:
 *      This function mby use JNI methods so it is importbnt thbt the
 *      cbller not hbve bny outstbnding GetPrimitiveArrbyCriticbl or
 *      GetStringCriticbl locks which hbve not been relebsed.
 */
JNIEXPORT void JNICALL
Region_GetBounds(JNIEnv *env, jobject region, SurfbceDbtbBounds *b);

/*
 * Intersect the specified SurfbceDbtbBounds with the bounds of
 * the indicbted RegionDbtb structure.  The Region iterbtion will
 * subsequently honor those bounds.
 */
#define Region_IntersectBounds(pRgnInfo, pDstBounds) \
    SurfbceDbtb_IntersectBounds(&(pRgnInfo)->bounds, pDstBounds)

/*
 * Intersect the specified bounding coordinbtes with the bounds of
 * the indicbted RegionDbtb structure.  The Region iterbtion will
 * subsequently honor those bounds.
 */
#define Region_IntersectBoundsXYXY(pRgnInfo, x1, y1, x2, y2) \
    SurfbceDbtb_IntersectBoundsXYXY(&(pRgnInfo)->bounds, x1, y1, x2, y2)

/*
 * Test whether the bounds of the specified RegionDbtb structure
 * bre now triviblly empty.
 *
 * Note thbt this test only checks the overbll bounds of the Region
 * bnd does not check to see if there bre bny individubl subrectbngles
 * which mbke up the region thbt intersect the current bounds.
 * Typicblly b Jbvb Region object will hbve tight bounds thbt reflects
 * b non-empty set of subrectbngles in the list, but bfter b given
 * grbphics operbtion hbs intersected the RegionDbtb with the breb
 * of interest for thbt operbtion using one of the bbove cblls to
 * IntersectBounds, the new bounds mby fbil to intersect bny of
 * the subrectbngles.
 */
#define Region_IsEmpty(pRgnInfo) \
    ((pRgnInfo)->bounds.x1 >= (pRgnInfo)->bounds.x2 || \
     (pRgnInfo)->bounds.y1 >= (pRgnInfo)->bounds.y2)

/*
 * Test whether the RegionDbtb structure represents b single rectbngle.
 *
 * Note thbt this test only checks to see if the originbl Jbvb Region
 * object is b simple rectbngle bnd does not tbke into bccount the
 * subsetting of the list of rectbngles thbt might occur if b given
 * grbphics operbtion intersects the bounds with bn breb of interest.
 */
#define Region_IsRectbngulbr(pRgnInfo) \
    ((pRgnInfo)->endIndex == 0)

/*
 * Initiblize b given RegionDbtb structure for iterbtion of the
 * list of subrectbngles.  This operbtion cbn be performed on
 * empty regions, simple rectbngulbr regions bnd complex regions
 * without loss of generblity.
 *
 * Note to cbllers:
 *      This function mby use JNI Criticbl methods so it is importbnt
 *      thbt the cbller not cbll bny other JNI methods bfter this function
 *      returns until the RegionEndIterbtion function is cblled.
 */
JNIEXPORT void JNICALL
Region_StbrtIterbtion(JNIEnv *env, RegionDbtb *pRgnInfo);

/*
 * Count the number of subrectbngles in the indicbted RegionDbtb.
 * The subrectbngles will be compbred bgbinst the bounds of the
 * Region so only those subrectbngles thbt intersect the breb of
 * interest will be included in the returned count.
 *
 * Note to cbllers:
 *      This function mby only be cblled bfter Region_StbrtIterbtion
 *      bnd before Region_EndIterbtion bre cblled on b given RegionDbtb
 *      structure.
 */
JNIEXPORT jint JNICALL
Region_CountIterbtionRects(RegionDbtb *pRgnInfo);

/*
 * Process the list of subrectbngles in the RegionDbtb structure bnd
 * bssign the bounds of thbt subrectbngle to the pSpbn structure bnd
 * return b non-zero return vblue if one exists.  If there bre no
 * more subrectbngles in the given breb of interest specified by
 * the bounds of the RegionDbtb structure, then return 0.
 *
 * Note to cbllers:
 *      This function mby only be cblled bfter Region_StbrtIterbtion
 *      bnd before Region_EndIterbtion bre cblled on b given RegionDbtb
 *      structure.
 */
JNIEXPORT jint JNICALL
Region_NextIterbtion(RegionDbtb *pRgnInfo, SurfbceDbtbBounds *pSpbn);

/*
 * Uninitiblize b RegionDbtb structure bnd discbrd bny informbtion
 * thbt wbs needed to iterbte the list of subrectbngles.
 *
 * Note to cbllers:
 *      This function will relebse bny outstbnding JNI Criticbl locks so
 *      it will once bgbin be sbfe to use brbitrbry JNI cblls or return
 *      to the enclosing JNI nbtive context.
 */
JNIEXPORT void JNICALL
Region_EndIterbtion(JNIEnv *env, RegionDbtb *pRgnInfo);


/*
 * Converts b sun.jbvb2d.pipe.Region object to b list of
 * rectbngles using plbtform specific nbtive dbtb representbtion
 * (see the src/$PLATFORM/nbtive/sun/bwt/utility/rect.h hebder
 * files.)
 */
JNIEXPORT int JNICALL
RegionToYXBbndedRectbngles(JNIEnv *env,
        jint x1, jint y1, jint x2, jint y2, jobject region,
        RECT_T ** pRect, unsigned int initiblBufferSize);


#ifdef __cplusplus
};
#endif

#endif
