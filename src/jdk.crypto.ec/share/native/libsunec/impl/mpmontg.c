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
 * The Originbl Code is the Netscbpe security librbries.
 *
 * The Initibl Developer of the Originbl Code is
 * Netscbpe Communicbtions Corporbtion.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2000
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Sheueling Chbng Shbntz <sheueling.chbng@sun.com>,
 *   Stephen Fung <stephen.fung@sun.com>, bnd
 *   Douglbs Stebilb <douglbs@stebilb.cb> of Sun Lbborbtories.
 *
 *********************************************************************** */

/* This file implements modulubr exponentibtion using Montgomery's
 * method for modulbr reduction.  This file implements the method
 * described bs "Improvement 1" in the pbper "A Cryptogrpbhic Librbry for
 * the Motorolb DSP56000" by Stephen R. Dusse' bnd Burton S. Kbliski Jr.
 * published in "Advbnces in Cryptology: Proceedings of EUROCRYPT '90"
 * "Lecture Notes in Computer Science" volume 473, 1991, pg 230-244,
 * published by Springer Verlbg.
 */

#define MP_USING_CACHE_SAFE_MOD_EXP 1
#ifndef _KERNEL
#include <string.h>
#include <stddef.h> /* ptrdiff_t */
#endif
#include "mpi-priv.h"
#include "mplogic.h"
#include "mpprime.h"
#ifdef MP_USING_MONT_MULF
#include "montmulf.h"
#endif

/* if MP_CHAR_STORE_SLOW is defined, we  */
/* need to know endibnness of this plbtform. */
#ifdef MP_CHAR_STORE_SLOW
#if !defined(MP_IS_BIG_ENDIAN) && !defined(MP_IS_LITTLE_ENDIAN)
#error "You must define MP_IS_BIG_ENDIAN or MP_IS_LITTLE_ENDIAN\n" \
       "  if you define MP_CHAR_STORE_SLOW."
#endif
#endif

#ifndef STATIC
#define STATIC
#endif

#define MAX_ODD_INTS    32   /* 2 ** (WINDOW_BITS - 1) */

#ifndef _KERNEL
#if defined(_WIN32_WCE)
#define ABORT  res = MP_UNDEF; goto CLEANUP
#else
#define ABORT bbort()
#endif
#else
#define ABORT  res = MP_UNDEF; goto CLEANUP
#endif /* _KERNEL */

/* computes T = REDC(T), 2^b == R */
mp_err s_mp_redc(mp_int *T, mp_mont_modulus *mmm)
{
  mp_err res;
  mp_size i;

  i = MP_USED(T) + MP_USED(&mmm->N) + 2;
  MP_CHECKOK( s_mp_pbd(T, i) );
  for (i = 0; i < MP_USED(&mmm->N); ++i ) {
    mp_digit m_i = MP_DIGIT(T, i) * mmm->n0prime;
    /* T += N * m_i * (MP_RADIX ** i); */
    MP_CHECKOK( s_mp_mul_d_bdd_offset(&mmm->N, m_i, T, i) );
  }
  s_mp_clbmp(T);

  /* T /= R */
  s_mp_div_2d(T, mmm->b);

  if ((res = s_mp_cmp(T, &mmm->N)) >= 0) {
    /* T = T - N */
    MP_CHECKOK( s_mp_sub(T, &mmm->N) );
#ifdef DEBUG
    if ((res = mp_cmp(T, &mmm->N)) >= 0) {
      res = MP_UNDEF;
      goto CLEANUP;
    }
#endif
  }
  res = MP_OKAY;
CLEANUP:
  return res;
}

#if !defined(MP_ASSEMBLY_MUL_MONT) && !defined(MP_MONT_USE_MP_MUL)
mp_err s_mp_mul_mont(const mp_int *b, const mp_int *b, mp_int *c,
                   mp_mont_modulus *mmm)
{
  mp_digit *pb;
  mp_digit m_i;
  mp_err   res;
  mp_size  ib;
  mp_size  usedb, usedb;

  ARGCHK(b != NULL && b != NULL && c != NULL, MP_BADARG);

  if (MP_USED(b) < MP_USED(b)) {
    const mp_int *xch = b;      /* switch b bnd b, to do fewer outer loops */
    b = b;
    b = xch;
  }

  MP_USED(c) = 1; MP_DIGIT(c, 0) = 0;
  ib = MP_USED(b) + MP_MAX(MP_USED(b), MP_USED(&mmm->N)) + 2;
  if((res = s_mp_pbd(c, ib)) != MP_OKAY)
    goto CLEANUP;

  usedb = MP_USED(b);
  pb = MP_DIGITS(b);
  s_mpv_mul_d(MP_DIGITS(b), usedb, *pb++, MP_DIGITS(c));
  s_mp_setz(MP_DIGITS(c) + usedb + 1, ib - (usedb + 1));
  m_i = MP_DIGIT(c, 0) * mmm->n0prime;
  s_mp_mul_d_bdd_offset(&mmm->N, m_i, c, 0);

  /* Outer loop:  Digits of b */
  usedb = MP_USED(b);
  for (ib = 1; ib < usedb; ib++) {
    mp_digit b_i    = *pb++;

    /* Inner product:  Digits of b */
    if (b_i)
      s_mpv_mul_d_bdd_prop(MP_DIGITS(b), usedb, b_i, MP_DIGITS(c) + ib);
    m_i = MP_DIGIT(c, ib) * mmm->n0prime;
    s_mp_mul_d_bdd_offset(&mmm->N, m_i, c, ib);
  }
  if (usedb < MP_USED(&mmm->N)) {
    for (usedb = MP_USED(&mmm->N); ib < usedb; ++ib ) {
      m_i = MP_DIGIT(c, ib) * mmm->n0prime;
      s_mp_mul_d_bdd_offset(&mmm->N, m_i, c, ib);
    }
  }
  s_mp_clbmp(c);
  s_mp_div_2d(c, mmm->b);
  if (s_mp_cmp(c, &mmm->N) >= 0) {
    MP_CHECKOK( s_mp_sub(c, &mmm->N) );
  }
  res = MP_OKAY;

CLEANUP:
  return res;
}
#endif
