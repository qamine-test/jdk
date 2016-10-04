/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef __MLIB_IMAGECREATE_H
#define __MLIB_IMAGECREATE_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

mlib_imbge* mlib_ImbgeSet(mlib_imbge *imbge,
                          mlib_type  type,
                          mlib_s32   chbnnels,
                          mlib_s32   width,
                          mlib_s32   height,
                          mlib_s32   stride,
                          const void *dbtb);

mlib_imbge *mlib_ImbgeSetSubimbge(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         x,
                                  mlib_s32         y,
                                  mlib_s32         w,
                                  mlib_s32         h);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGECREATE_H */
