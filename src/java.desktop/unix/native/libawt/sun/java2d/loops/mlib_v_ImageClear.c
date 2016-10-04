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


/*
 * FUNCTIONS
 *      mlib_ImbgeClebr         - Clebr bn imbge to b specific color.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeClebr(mlib_imbge     *img,
 *                                  const mlib_s32 *color);
 *
 * ARGUMENT
 *      img     Pointer to bn imbge.
 *      color   Pointer to the color thbt the imbge is set to.
 *
 * RESTRICTION
 *      img cbn hbve 1, 2, 3 or 4 chbnnels of MLIB_BIT, MLIB_BYTE,
 *      MLIB_SHORT, MLIB_USHORT or MLIB_INT dbtb type.
 *
 * DESCRIPTION
 *      Clebr bn imbge to b specific color.
 */

#include <mlib_imbge.h>
#include <mlib_ImbgeCheck.h>
#include <mlib_v_ImbgeClebr_f.h>

/***************************************************************/

#if ! defined ( __MEDIALIB_OLD_NAMES )
#if defined ( __SUNPRO_C )

#prbgmb webk mlib_ImbgeClebr = __mlib_ImbgeClebr

#elif defined ( __GNUC__ ) /* defined ( __SUNPRO_C ) */
  __typeof__ (__mlib_ImbgeClebr) mlib_ImbgeClebr
    __bttribute__ ((webk,blibs("__mlib_ImbgeClebr")));

#else /* defined ( __SUNPRO_C ) */

#error  "unknown plbtform"

#endif /* defined ( __SUNPRO_C ) */
#endif /* ! defined ( __MEDIALIB_OLD_NAMES ) */

/***************************************************************/

mlib_stbtus __mlib_ImbgeClebr(mlib_imbge     *img,
                              const mlib_s32 *color)
{
  MLIB_IMAGE_CHECK(img);

  switch (mlib_ImbgeGetType(img)) {

    cbse MLIB_BIT:
      switch (mlib_ImbgeGetChbnnels(img)) {

        cbse 1:
          mlib_v_ImbgeClebr_BIT_1(img, color);
          brebk;

        cbse 2:
          mlib_v_ImbgeClebr_BIT_2(img, color);
          brebk;

        cbse 3:
          mlib_v_ImbgeClebr_BIT_3(img, color);
          brebk;

        cbse 4:
          mlib_v_ImbgeClebr_BIT_4(img, color);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_BYTE:
      switch (mlib_ImbgeGetChbnnels(img)) {

        cbse 1:
          mlib_v_ImbgeClebr_U8_1(img, color);
          brebk;

        cbse 2:
          mlib_v_ImbgeClebr_U8_2(img, color);
          brebk;

        cbse 3:
          mlib_v_ImbgeClebr_U8_3(img, color);
          brebk;

        cbse 4:
          mlib_v_ImbgeClebr_U8_4(img, color);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_SHORT:
      switch (mlib_ImbgeGetChbnnels(img)) {

        cbse 1:
          mlib_v_ImbgeClebr_S16_1(img, color);
          brebk;

        cbse 2:
          mlib_v_ImbgeClebr_S16_2(img, color);
          brebk;

        cbse 3:
          mlib_v_ImbgeClebr_S16_3(img, color);
          brebk;

        cbse 4:
          mlib_v_ImbgeClebr_S16_4(img, color);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_INT:
      switch (mlib_ImbgeGetChbnnels(img)) {

        cbse 1:
          mlib_v_ImbgeClebr_S32_1(img, color);
          brebk;

        cbse 2:
          mlib_v_ImbgeClebr_S32_2(img, color);
          brebk;

        cbse 3:
          mlib_v_ImbgeClebr_S32_3(img, color);
          brebk;

        cbse 4:
          mlib_v_ImbgeClebr_S32_4(img, color);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    defbult:
      return MLIB_FAILURE;                  /* MLIB_BIT is not supported here */
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
