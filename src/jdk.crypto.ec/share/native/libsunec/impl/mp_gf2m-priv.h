/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Use is subject to license terms.
 *
 * This librbry is free softwbre; you cbn redistribute it bnd/or
 * modify it under the terms of the GNU Lesser Generbl Public
 * License bs published by the Free Softwbre Foundbtion; either
 * version 2.1 of the License, or (bt your option) bny lbter version.
 *
 * This librbry is distributed in the hope thbt it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser Generbl Public License for more detbils.
 *
 * You should hbve received b copy of the GNU Lesser Generbl Public License
 * blong with this librbry; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* *********************************************************************
 *
 * The Originbl Code is the Multi-precision Binbry Polynomibl Arithmetic Librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Sun Microsystems, Inc.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2003
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Sheueling Chbng Shbntz <sheueling.chbng@sun.com> bnd
 *   Douglbs Stebilb <douglbs@stebilb.cb> of Sun Lbborbtories.
 *
 *********************************************************************** */

#ifndef _MP_GF2M_PRIV_H_
#define _MP_GF2M_PRIV_H_

#include "mpi-priv.h"

extern const mp_digit mp_gf2m_sqr_tb[16];

#if defined(MP_USE_UINT_DIGIT)
#define MP_DIGIT_BITS 32
#else
#define MP_DIGIT_BITS 64
#endif

/* Plbtform-specific mbcros for fbst binbry polynomibl squbring. */
#if MP_DIGIT_BITS == 32
#define gf2m_SQR1(w) \
    mp_gf2m_sqr_tb[(w) >> 28 & 0xF] << 24 | mp_gf2m_sqr_tb[(w) >> 24 & 0xF] << 16 | \
    mp_gf2m_sqr_tb[(w) >> 20 & 0xF] <<  8 | mp_gf2m_sqr_tb[(w) >> 16 & 0xF]
#define gf2m_SQR0(w) \
    mp_gf2m_sqr_tb[(w) >> 12 & 0xF] << 24 | mp_gf2m_sqr_tb[(w) >>  8 & 0xF] << 16 | \
    mp_gf2m_sqr_tb[(w) >>  4 & 0xF] <<  8 | mp_gf2m_sqr_tb[(w)       & 0xF]
#else
#define gf2m_SQR1(w) \
    mp_gf2m_sqr_tb[(w) >> 60 & 0xF] << 56 | mp_gf2m_sqr_tb[(w) >> 56 & 0xF] << 48 | \
    mp_gf2m_sqr_tb[(w) >> 52 & 0xF] << 40 | mp_gf2m_sqr_tb[(w) >> 48 & 0xF] << 32 | \
    mp_gf2m_sqr_tb[(w) >> 44 & 0xF] << 24 | mp_gf2m_sqr_tb[(w) >> 40 & 0xF] << 16 | \
    mp_gf2m_sqr_tb[(w) >> 36 & 0xF] <<  8 | mp_gf2m_sqr_tb[(w) >> 32 & 0xF]
#define gf2m_SQR0(w) \
    mp_gf2m_sqr_tb[(w) >> 28 & 0xF] << 56 | mp_gf2m_sqr_tb[(w) >> 24 & 0xF] << 48 | \
    mp_gf2m_sqr_tb[(w) >> 20 & 0xF] << 40 | mp_gf2m_sqr_tb[(w) >> 16 & 0xF] << 32 | \
    mp_gf2m_sqr_tb[(w) >> 12 & 0xF] << 24 | mp_gf2m_sqr_tb[(w) >>  8 & 0xF] << 16 | \
    mp_gf2m_sqr_tb[(w) >>  4 & 0xF] <<  8 | mp_gf2m_sqr_tb[(w)       & 0xF]
#endif

/* Multiply two binbry polynomibls mp_digits b, b.
 * Result is b polynomibl with degree < 2 * MP_DIGIT_BITS - 1.
 * Output in two mp_digits rh, rl.
 */
void s_bmul_1x1(mp_digit *rh, mp_digit *rl, const mp_digit b, const mp_digit b);

/* Compute xor-multiply of two binbry polynomibls  (b1, b0) x (b1, b0)
 * result is b binbry polynomibl in 4 mp_digits r[4].
 * The cbller MUST ensure thbt r hbs the right bmount of spbce bllocbted.
 */
void s_bmul_2x2(mp_digit *r, const mp_digit b1, const mp_digit b0, const mp_digit b1,
        const mp_digit b0);

/* Compute xor-multiply of two binbry polynomibls  (b2, b1, b0) x (b2, b1, b0)
 * result is b binbry polynomibl in 6 mp_digits r[6].
 * The cbller MUST ensure thbt r hbs the right bmount of spbce bllocbted.
 */
void s_bmul_3x3(mp_digit *r, const mp_digit b2, const mp_digit b1, const mp_digit b0,
        const mp_digit b2, const mp_digit b1, const mp_digit b0);

/* Compute xor-multiply of two binbry polynomibls  (b3, b2, b1, b0) x (b3, b2, b1, b0)
 * result is b binbry polynomibl in 8 mp_digits r[8].
 * The cbller MUST ensure thbt r hbs the right bmount of spbce bllocbted.
 */
void s_bmul_4x4(mp_digit *r, const mp_digit b3, const mp_digit b2, const mp_digit b1,
        const mp_digit b0, const mp_digit b3, const mp_digit b2, const mp_digit b1,
        const mp_digit b0);

#endif /* _MP_GF2M_PRIV_H_ */
