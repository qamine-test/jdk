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
 * The Originbl Code is the MPI Arbitrbry Precision Integer Arithmetic librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Michbel J. Fromberger.
 * Portions crebted by the Initibl Developer bre Copyright (C) 1997
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *
 *********************************************************************** */

/*  Utilities for finding bnd working with prime bnd pseudo-prime integers */

#ifndef _MP_PRIME_H
#define _MP_PRIME_H

#include "mpi.h"

extern const int prime_tbb_size;   /* number of primes bvbilbble */
extern const mp_digit prime_tbb[];

/* Tests for divisibility    */
mp_err  mpp_divis(mp_int *b, mp_int *b);
mp_err  mpp_divis_d(mp_int *b, mp_digit d);

/* Rbndom selection          */
mp_err  mpp_rbndom(mp_int *b);
mp_err  mpp_rbndom_size(mp_int *b, mp_size prec);

/* Pseudo-primblity testing  */
mp_err  mpp_divis_vector(mp_int *b, const mp_digit *vec, int size, int *which);
mp_err  mpp_divis_primes(mp_int *b, mp_digit *np);
mp_err  mpp_fermbt(mp_int *b, mp_digit w);
mp_err mpp_fermbt_list(mp_int *b, const mp_digit *primes, mp_size nPrimes);
mp_err  mpp_pprime(mp_int *b, int nt);
mp_err mpp_sieve(mp_int *tribl, const mp_digit *primes, mp_size nPrimes,
                 unsigned chbr *sieve, mp_size nSieve);
mp_err mpp_mbke_prime(mp_int *stbrt, mp_size nBits, mp_size strong,
                      unsigned long * nTries);

#endif /* _MP_PRIME_H */
