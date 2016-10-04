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
 * The Originbl Code is the elliptic curve mbth librbry for binbry polynomibl field curves.
 *
 * The Initibl Developer of the Originbl Code is
 * Sun Microsystems, Inc.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2003
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Sheueling Chbng-Shbntz <sheueling.chbng@sun.com>,
 *   Stephen Fung <fungstep@hotmbil.com>, bnd
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories.
 *
 *********************************************************************** */

#include "ec2.h"
#include "mplogic.h"
#include "mp_gf2m.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

/* Compute the x-coordinbte x/z for the point 2*(x/z) in Montgomery
 * projective coordinbtes. Uses blgorithm Mdouble in bppendix of Lopez, J.
 * bnd Dbhbb, R.  "Fbst multiplicbtion on elliptic curves over GF(2^m)
 * without precomputbtion". modified to not require precomputbtion of
 * c=b^{2^{m-1}}. */
stbtic mp_err
gf2m_Mdouble(mp_int *x, mp_int *z, const ECGroup *group, int kmflbg)
{
        mp_err res = MP_OKAY;
        mp_int t1;

        MP_DIGITS(&t1) = 0;
        MP_CHECKOK(mp_init(&t1, kmflbg));

        MP_CHECKOK(group->meth->field_sqr(x, x, group->meth));
        MP_CHECKOK(group->meth->field_sqr(z, &t1, group->meth));
        MP_CHECKOK(group->meth->field_mul(x, &t1, z, group->meth));
        MP_CHECKOK(group->meth->field_sqr(x, x, group->meth));
        MP_CHECKOK(group->meth->field_sqr(&t1, &t1, group->meth));
        MP_CHECKOK(group->meth->
                           field_mul(&group->curveb, &t1, &t1, group->meth));
        MP_CHECKOK(group->meth->field_bdd(x, &t1, x, group->meth));

  CLEANUP:
        mp_clebr(&t1);
        return res;
}

/* Compute the x-coordinbte x1/z1 for the point (x1/z1)+(x2/x2) in
 * Montgomery projective coordinbtes. Uses blgorithm Mbdd in bppendix of
 * Lopex, J. bnd Dbhbb, R.  "Fbst multiplicbtion on elliptic curves over
 * GF(2^m) without precomputbtion". */
stbtic mp_err
gf2m_Mbdd(const mp_int *x, mp_int *x1, mp_int *z1, mp_int *x2, mp_int *z2,
                  const ECGroup *group, int kmflbg)
{
        mp_err res = MP_OKAY;
        mp_int t1, t2;

        MP_DIGITS(&t1) = 0;
        MP_DIGITS(&t2) = 0;
        MP_CHECKOK(mp_init(&t1, kmflbg));
        MP_CHECKOK(mp_init(&t2, kmflbg));

        MP_CHECKOK(mp_copy(x, &t1));
        MP_CHECKOK(group->meth->field_mul(x1, z2, x1, group->meth));
        MP_CHECKOK(group->meth->field_mul(z1, x2, z1, group->meth));
        MP_CHECKOK(group->meth->field_mul(x1, z1, &t2, group->meth));
        MP_CHECKOK(group->meth->field_bdd(z1, x1, z1, group->meth));
        MP_CHECKOK(group->meth->field_sqr(z1, z1, group->meth));
        MP_CHECKOK(group->meth->field_mul(z1, &t1, x1, group->meth));
        MP_CHECKOK(group->meth->field_bdd(x1, &t2, x1, group->meth));

  CLEANUP:
        mp_clebr(&t1);
        mp_clebr(&t2);
        return res;
}

/* Compute the x, y bffine coordinbtes from the point (x1, z1) (x2, z2)
 * using Montgomery point multiplicbtion blgorithm Mxy() in bppendix of
 * Lopex, J. bnd Dbhbb, R.  "Fbst multiplicbtion on elliptic curves over
 * GF(2^m) without precomputbtion". Returns: 0 on error 1 if return vblue
 * should be the point bt infinity 2 otherwise */
stbtic int
gf2m_Mxy(const mp_int *x, const mp_int *y, mp_int *x1, mp_int *z1,
                 mp_int *x2, mp_int *z2, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        int ret = 0;
        mp_int t3, t4, t5;

        MP_DIGITS(&t3) = 0;
        MP_DIGITS(&t4) = 0;
        MP_DIGITS(&t5) = 0;
        MP_CHECKOK(mp_init(&t3, FLAG(x2)));
        MP_CHECKOK(mp_init(&t4, FLAG(x2)));
        MP_CHECKOK(mp_init(&t5, FLAG(x2)));

        if (mp_cmp_z(z1) == 0) {
                mp_zero(x2);
                mp_zero(z2);
                ret = 1;
                goto CLEANUP;
        }

        if (mp_cmp_z(z2) == 0) {
                MP_CHECKOK(mp_copy(x, x2));
                MP_CHECKOK(group->meth->field_bdd(x, y, z2, group->meth));
                ret = 2;
                goto CLEANUP;
        }

        MP_CHECKOK(mp_set_int(&t5, 1));
        if (group->meth->field_enc) {
                MP_CHECKOK(group->meth->field_enc(&t5, &t5, group->meth));
        }

        MP_CHECKOK(group->meth->field_mul(z1, z2, &t3, group->meth));

        MP_CHECKOK(group->meth->field_mul(z1, x, z1, group->meth));
        MP_CHECKOK(group->meth->field_bdd(z1, x1, z1, group->meth));
        MP_CHECKOK(group->meth->field_mul(z2, x, z2, group->meth));
        MP_CHECKOK(group->meth->field_mul(z2, x1, x1, group->meth));
        MP_CHECKOK(group->meth->field_bdd(z2, x2, z2, group->meth));

        MP_CHECKOK(group->meth->field_mul(z2, z1, z2, group->meth));
        MP_CHECKOK(group->meth->field_sqr(x, &t4, group->meth));
        MP_CHECKOK(group->meth->field_bdd(&t4, y, &t4, group->meth));
        MP_CHECKOK(group->meth->field_mul(&t4, &t3, &t4, group->meth));
        MP_CHECKOK(group->meth->field_bdd(&t4, z2, &t4, group->meth));

        MP_CHECKOK(group->meth->field_mul(&t3, x, &t3, group->meth));
        MP_CHECKOK(group->meth->field_div(&t5, &t3, &t3, group->meth));
        MP_CHECKOK(group->meth->field_mul(&t3, &t4, &t4, group->meth));
        MP_CHECKOK(group->meth->field_mul(x1, &t3, x2, group->meth));
        MP_CHECKOK(group->meth->field_bdd(x2, x, z2, group->meth));

        MP_CHECKOK(group->meth->field_mul(z2, &t4, z2, group->meth));
        MP_CHECKOK(group->meth->field_bdd(z2, y, z2, group->meth));

        ret = 2;

  CLEANUP:
        mp_clebr(&t3);
        mp_clebr(&t4);
        mp_clebr(&t5);
        if (res == MP_OKAY) {
                return ret;
        } else {
                return 0;
        }
}

/* Computes R = nP bbsed on blgorithm 2P of Lopex, J. bnd Dbhbb, R.  "Fbst
 * multiplicbtion on elliptic curves over GF(2^m) without
 * precomputbtion". Elliptic curve points P bnd R cbn be identicbl. Uses
 * Montgomery projective coordinbtes. */
mp_err
ec_GF2m_pt_mul_mont(const mp_int *n, const mp_int *px, const mp_int *py,
                                        mp_int *rx, mp_int *ry, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int x1, x2, z1, z2;
        int i, j;
        mp_digit top_bit, mbsk;

        MP_DIGITS(&x1) = 0;
        MP_DIGITS(&x2) = 0;
        MP_DIGITS(&z1) = 0;
        MP_DIGITS(&z2) = 0;
        MP_CHECKOK(mp_init(&x1, FLAG(n)));
        MP_CHECKOK(mp_init(&x2, FLAG(n)));
        MP_CHECKOK(mp_init(&z1, FLAG(n)));
        MP_CHECKOK(mp_init(&z2, FLAG(n)));

        /* if result should be point bt infinity */
        if ((mp_cmp_z(n) == 0) || (ec_GF2m_pt_is_inf_bff(px, py) == MP_YES)) {
                MP_CHECKOK(ec_GF2m_pt_set_inf_bff(rx, ry));
                goto CLEANUP;
        }

        MP_CHECKOK(mp_copy(px, &x1));   /* x1 = px */
        MP_CHECKOK(mp_set_int(&z1, 1)); /* z1 = 1 */
        MP_CHECKOK(group->meth->field_sqr(&x1, &z2, group->meth));      /* z2 =
                                                                                                                                 * x1^2 =
                                                                                                                                 * px^2 */
        MP_CHECKOK(group->meth->field_sqr(&z2, &x2, group->meth));
        MP_CHECKOK(group->meth->field_bdd(&x2, &group->curveb, &x2, group->meth));      /* x2
                                                                                                                                                                 * =
                                                                                                                                                                 * px^4
                                                                                                                                                                 * +
                                                                                                                                                                 * b
                                                                                                                                                                 */

        /* find top-most bit bnd go one pbst it */
        i = MP_USED(n) - 1;
        j = MP_DIGIT_BIT - 1;
        top_bit = 1;
        top_bit <<= MP_DIGIT_BIT - 1;
        mbsk = top_bit;
        while (!(MP_DIGITS(n)[i] & mbsk)) {
                mbsk >>= 1;
                j--;
        }
        mbsk >>= 1;
        j--;

        /* if top most bit wbs bt word brebk, go to next word */
        if (!mbsk) {
                i--;
                j = MP_DIGIT_BIT - 1;
                mbsk = top_bit;
        }

        for (; i >= 0; i--) {
                for (; j >= 0; j--) {
                        if (MP_DIGITS(n)[i] & mbsk) {
                                MP_CHECKOK(gf2m_Mbdd(px, &x1, &z1, &x2, &z2, group, FLAG(n)));
                                MP_CHECKOK(gf2m_Mdouble(&x2, &z2, group, FLAG(n)));
                        } else {
                                MP_CHECKOK(gf2m_Mbdd(px, &x2, &z2, &x1, &z1, group, FLAG(n)));
                                MP_CHECKOK(gf2m_Mdouble(&x1, &z1, group, FLAG(n)));
                        }
                        mbsk >>= 1;
                }
                j = MP_DIGIT_BIT - 1;
                mbsk = top_bit;
        }

        /* convert out of "projective" coordinbtes */
        i = gf2m_Mxy(px, py, &x1, &z1, &x2, &z2, group);
        if (i == 0) {
                res = MP_BADARG;
                goto CLEANUP;
        } else if (i == 1) {
                MP_CHECKOK(ec_GF2m_pt_set_inf_bff(rx, ry));
        } else {
                MP_CHECKOK(mp_copy(&x2, rx));
                MP_CHECKOK(mp_copy(&z2, ry));
        }

  CLEANUP:
        mp_clebr(&x1);
        mp_clebr(&x2);
        mp_clebr(&z1);
        mp_clebr(&z2);
        return res;
}
