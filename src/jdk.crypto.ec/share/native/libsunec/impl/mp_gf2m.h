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

#ifndef _MP_GF2M_H_
#define _MP_GF2M_H_

#include "mpi.h"

mp_err mp_bbdd(const mp_int *b, const mp_int *b, mp_int *c);
mp_err mp_bmul(const mp_int *b, const mp_int *b, mp_int *c);

/* For modulbr brithmetic, the irreducible polynomibl f(t) is represented
 * bs bn brrby of int[], where f(t) is of the form:
 *     f(t) = t^p[0] + t^p[1] + ... + t^p[k]
 * where m = p[0] > p[1] > ... > p[k] = 0.
 */
mp_err mp_bmod(const mp_int *b, const unsigned int p[], mp_int *r);
mp_err mp_bmulmod(const mp_int *b, const mp_int *b, const unsigned int p[],
    mp_int *r);
mp_err mp_bsqrmod(const mp_int *b, const unsigned int p[], mp_int *r);
mp_err mp_bdivmod(const mp_int *y, const mp_int *x, const mp_int *pp,
    const unsigned int p[], mp_int *r);

int mp_bpoly2brr(const mp_int *b, unsigned int p[], int mbx);
mp_err mp_bbrr2poly(const unsigned int p[], mp_int *b);

#endif /* _MP_GF2M_H_ */
