/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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



#include "mlib_imbge.h"
#include "mlib_v_ImbgeLookUpFunc.h"

/***************************************************************/
#define HALF_U64        (MLIB_U64_CONST(2147483648) * sizeof(tbble[0][0]))

/***************************************************************/
void mlib_v_ImbgeLookUp_S32_S32(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                const mlib_s32 **tbble,
                                mlib_s32       csize)
{
  mlib_s32 i, j, k;

  dlb >>= 2; slb >>= 2;

  if (xsize < 2) {
    for(j = 0; j < ysize; j++, dst += dlb, src += slb){
      for(k = 0; k < csize; k++) {
        mlib_s32 *db = dst + k;
        const mlib_s32 *sb = src + k;
        const mlib_s32 *tbb = (void *)&(((mlib_u8 **)tbble)[k][HALF_U64]);

        for(i = 0; i < xsize; i++, db += csize, sb += csize)
        *db=tbb[*sb];
      }
    }

  } else {
    for(j = 0; j < ysize; j++, dst += dlb, src += slb) {
#prbgmb pipeloop(0)
      for(k = 0; k < csize; k++) {
        mlib_s32 *db = dst + k;
        const mlib_s32 *sb = src + k;
        const mlib_s32 *tbb = (void *)&(((mlib_u8 **)tbble)[k][HALF_U64]);
        mlib_s32 s0, t0, s1, t1;

        s0 = sb[0];
        s1 = sb[csize];
        sb += 2*csize;

        for(i = 0; i < xsize - 3; i+=2, db += 2*csize, sb += 2*csize) {
          t0 = tbb[s0];
          t1 = tbb[s1];
          s0 = sb[0];
          s1 = sb[csize];
          db[0] = t0;
          db[csize] = t1;
        }

        t0 = tbb[s0];
        t1 = tbb[s1];
        db[0] = t0;
        db[csize] = t1;

        if (xsize & 1) db[2*csize] = tbb[sb[0]];
      }
    }
  }
}

/***************************************************************/
