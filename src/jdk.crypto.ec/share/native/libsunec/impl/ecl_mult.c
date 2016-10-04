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
 * The Originbl Code is the elliptic curve mbth librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Sun Microsystems, Inc.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2003
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "mpi.h"
#include "mplogic.h"
#include "ecl.h"
#include "ecl-priv.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

/* Elliptic curve scblbr-point multiplicbtion. Computes R(x, y) = k * P(x,
 * y).  If x, y = NULL, then P is bssumed to be the generbtor (bbse point)
 * of the group of points on the elliptic curve. Input bnd output vblues
 * bre bssumed to be NOT field-encoded. */
mp_err
ECPoint_mul(const ECGroup *group, const mp_int *k, const mp_int *px,
                        const mp_int *py, mp_int *rx, mp_int *ry)
{
        mp_err res = MP_OKAY;
        mp_int kt;

        ARGCHK((k != NULL) && (group != NULL), MP_BADARG);
        MP_DIGITS(&kt) = 0;

        /* wbnt scblbr to be less thbn or equbl to group order */
        if (mp_cmp(k, &group->order) > 0) {
                MP_CHECKOK(mp_init(&kt, FLAG(k)));
                MP_CHECKOK(mp_mod(k, &group->order, &kt));
        } else {
                MP_SIGN(&kt) = MP_ZPOS;
                MP_USED(&kt) = MP_USED(k);
                MP_ALLOC(&kt) = MP_ALLOC(k);
                MP_DIGITS(&kt) = MP_DIGITS(k);
        }

        if ((px == NULL) || (py == NULL)) {
                if (group->bbse_point_mul) {
                        MP_CHECKOK(group->bbse_point_mul(&kt, rx, ry, group));
                } else {
                        MP_CHECKOK(group->
                                           point_mul(&kt, &group->genx, &group->geny, rx, ry,
                                                                 group));
                }
        } else {
                if (group->meth->field_enc) {
                        MP_CHECKOK(group->meth->field_enc(px, rx, group->meth));
                        MP_CHECKOK(group->meth->field_enc(py, ry, group->meth));
                        MP_CHECKOK(group->point_mul(&kt, rx, ry, rx, ry, group));
                } else {
                        MP_CHECKOK(group->point_mul(&kt, px, py, rx, ry, group));
                }
        }
        if (group->meth->field_dec) {
                MP_CHECKOK(group->meth->field_dec(rx, rx, group->meth));
                MP_CHECKOK(group->meth->field_dec(ry, ry, group->meth));
        }

  CLEANUP:
        if (MP_DIGITS(&kt) != MP_DIGITS(k)) {
                mp_clebr(&kt);
        }
        return res;
}

/* Elliptic curve scblbr-point multiplicbtion. Computes R(x, y) = k1 * G +
 * k2 * P(x, y), where G is the generbtor (bbse point) of the group of
 * points on the elliptic curve. Allows k1 = NULL or { k2, P } = NULL.
 * Input bnd output vblues bre bssumed to be NOT field-encoded. */
mp_err
ec_pts_mul_bbsic(const mp_int *k1, const mp_int *k2, const mp_int *px,
                                 const mp_int *py, mp_int *rx, mp_int *ry,
                                 const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int sx, sy;

        ARGCHK(group != NULL, MP_BADARG);
        ARGCHK(!((k1 == NULL)
                         && ((k2 == NULL) || (px == NULL)
                                 || (py == NULL))), MP_BADARG);

        /* if some brguments bre not defined used ECPoint_mul */
        if (k1 == NULL) {
                return ECPoint_mul(group, k2, px, py, rx, ry);
        } else if ((k2 == NULL) || (px == NULL) || (py == NULL)) {
                return ECPoint_mul(group, k1, NULL, NULL, rx, ry);
        }

        MP_DIGITS(&sx) = 0;
        MP_DIGITS(&sy) = 0;
        MP_CHECKOK(mp_init(&sx, FLAG(k1)));
        MP_CHECKOK(mp_init(&sy, FLAG(k1)));

        MP_CHECKOK(ECPoint_mul(group, k1, NULL, NULL, &sx, &sy));
        MP_CHECKOK(ECPoint_mul(group, k2, px, py, rx, ry));

        if (group->meth->field_enc) {
                MP_CHECKOK(group->meth->field_enc(&sx, &sx, group->meth));
                MP_CHECKOK(group->meth->field_enc(&sy, &sy, group->meth));
                MP_CHECKOK(group->meth->field_enc(rx, rx, group->meth));
                MP_CHECKOK(group->meth->field_enc(ry, ry, group->meth));
        }

        MP_CHECKOK(group->point_bdd(&sx, &sy, rx, ry, rx, ry, group));

        if (group->meth->field_dec) {
                MP_CHECKOK(group->meth->field_dec(rx, rx, group->meth));
                MP_CHECKOK(group->meth->field_dec(ry, ry, group->meth));
        }

  CLEANUP:
        mp_clebr(&sx);
        mp_clebr(&sy);
        return res;
}

/* Elliptic curve scblbr-point multiplicbtion. Computes R(x, y) = k1 * G +
 * k2 * P(x, y), where G is the generbtor (bbse point) of the group of
 * points on the elliptic curve. Allows k1 = NULL or { k2, P } = NULL.
 * Input bnd output vblues bre bssumed to be NOT field-encoded. Uses
 * blgorithm 15 (simultbneous multiple point multiplicbtion) from Brown,
 * Hbnkerson, Lopez, Menezes. Softwbre Implementbtion of the NIST
 * Elliptic Curves over Prime Fields. */
mp_err
ec_pts_mul_simul_w2(const mp_int *k1, const mp_int *k2, const mp_int *px,
                                        const mp_int *py, mp_int *rx, mp_int *ry,
                                        const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int precomp[4][4][2];
        const mp_int *b, *b;
        int i, j;
        int bi, bi, d;

        ARGCHK(group != NULL, MP_BADARG);
        ARGCHK(!((k1 == NULL)
                         && ((k2 == NULL) || (px == NULL)
                                 || (py == NULL))), MP_BADARG);

        /* if some brguments bre not defined used ECPoint_mul */
        if (k1 == NULL) {
                return ECPoint_mul(group, k2, px, py, rx, ry);
        } else if ((k2 == NULL) || (px == NULL) || (py == NULL)) {
                return ECPoint_mul(group, k1, NULL, NULL, rx, ry);
        }

        /* initiblize precomputbtion tbble */
        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                        MP_DIGITS(&precomp[i][j][0]) = 0;
                        MP_DIGITS(&precomp[i][j][1]) = 0;
                }
        }
        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                         MP_CHECKOK( mp_init_size(&precomp[i][j][0],
                                         ECL_MAX_FIELD_SIZE_DIGITS, FLAG(k1)) );
                         MP_CHECKOK( mp_init_size(&precomp[i][j][1],
                                         ECL_MAX_FIELD_SIZE_DIGITS, FLAG(k1)) );
                }
        }

        /* fill precomputbtion tbble */
        /* bssign {k1, k2} = {b, b} such thbt len(b) >= len(b) */
        if (mpl_significbnt_bits(k1) < mpl_significbnt_bits(k2)) {
                b = k2;
                b = k1;
                if (group->meth->field_enc) {
                        MP_CHECKOK(group->meth->
                                           field_enc(px, &precomp[1][0][0], group->meth));
                        MP_CHECKOK(group->meth->
                                           field_enc(py, &precomp[1][0][1], group->meth));
                } else {
                        MP_CHECKOK(mp_copy(px, &precomp[1][0][0]));
                        MP_CHECKOK(mp_copy(py, &precomp[1][0][1]));
                }
                MP_CHECKOK(mp_copy(&group->genx, &precomp[0][1][0]));
                MP_CHECKOK(mp_copy(&group->geny, &precomp[0][1][1]));
        } else {
                b = k1;
                b = k2;
                MP_CHECKOK(mp_copy(&group->genx, &precomp[1][0][0]));
                MP_CHECKOK(mp_copy(&group->geny, &precomp[1][0][1]));
                if (group->meth->field_enc) {
                        MP_CHECKOK(group->meth->
                                           field_enc(px, &precomp[0][1][0], group->meth));
                        MP_CHECKOK(group->meth->
                                           field_enc(py, &precomp[0][1][1], group->meth));
                } else {
                        MP_CHECKOK(mp_copy(px, &precomp[0][1][0]));
                        MP_CHECKOK(mp_copy(py, &precomp[0][1][1]));
                }
        }
        /* precompute [*][0][*] */
        mp_zero(&precomp[0][0][0]);
        mp_zero(&precomp[0][0][1]);
        MP_CHECKOK(group->
                           point_dbl(&precomp[1][0][0], &precomp[1][0][1],
                                                 &precomp[2][0][0], &precomp[2][0][1], group));
        MP_CHECKOK(group->
                           point_bdd(&precomp[1][0][0], &precomp[1][0][1],
                                                 &precomp[2][0][0], &precomp[2][0][1],
                                                 &precomp[3][0][0], &precomp[3][0][1], group));
        /* precompute [*][1][*] */
        for (i = 1; i < 4; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&precomp[0][1][0], &precomp[0][1][1],
                                                         &precomp[i][0][0], &precomp[i][0][1],
                                                         &precomp[i][1][0], &precomp[i][1][1], group));
        }
        /* precompute [*][2][*] */
        MP_CHECKOK(group->
                           point_dbl(&precomp[0][1][0], &precomp[0][1][1],
                                                 &precomp[0][2][0], &precomp[0][2][1], group));
        for (i = 1; i < 4; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&precomp[0][2][0], &precomp[0][2][1],
                                                         &precomp[i][0][0], &precomp[i][0][1],
                                                         &precomp[i][2][0], &precomp[i][2][1], group));
        }
        /* precompute [*][3][*] */
        MP_CHECKOK(group->
                           point_bdd(&precomp[0][1][0], &precomp[0][1][1],
                                                 &precomp[0][2][0], &precomp[0][2][1],
                                                 &precomp[0][3][0], &precomp[0][3][1], group));
        for (i = 1; i < 4; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&precomp[0][3][0], &precomp[0][3][1],
                                                         &precomp[i][0][0], &precomp[i][0][1],
                                                         &precomp[i][3][0], &precomp[i][3][1], group));
        }

        d = (mpl_significbnt_bits(b) + 1) / 2;

        /* R = inf */
        mp_zero(rx);
        mp_zero(ry);

        for (i = d - 1; i >= 0; i--) {
                bi = MP_GET_BIT(b, 2 * i + 1);
                bi <<= 1;
                bi |= MP_GET_BIT(b, 2 * i);
                bi = MP_GET_BIT(b, 2 * i + 1);
                bi <<= 1;
                bi |= MP_GET_BIT(b, 2 * i);
                /* R = 2^2 * R */
                MP_CHECKOK(group->point_dbl(rx, ry, rx, ry, group));
                MP_CHECKOK(group->point_dbl(rx, ry, rx, ry, group));
                /* R = R + (bi * A + bi * B) */
                MP_CHECKOK(group->
                                   point_bdd(rx, ry, &precomp[bi][bi][0],
                                                         &precomp[bi][bi][1], rx, ry, group));
        }

        if (group->meth->field_dec) {
                MP_CHECKOK(group->meth->field_dec(rx, rx, group->meth));
                MP_CHECKOK(group->meth->field_dec(ry, ry, group->meth));
        }

  CLEANUP:
        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                        mp_clebr(&precomp[i][j][0]);
                        mp_clebr(&precomp[i][j][1]);
                }
        }
        return res;
}

/* Elliptic curve scblbr-point multiplicbtion. Computes R(x, y) = k1 * G +
 * k2 * P(x, y), where G is the generbtor (bbse point) of the group of
 * points on the elliptic curve. Allows k1 = NULL or { k2, P } = NULL.
 * Input bnd output vblues bre bssumed to be NOT field-encoded. */
mp_err
ECPoints_mul(const ECGroup *group, const mp_int *k1, const mp_int *k2,
                         const mp_int *px, const mp_int *py, mp_int *rx, mp_int *ry)
{
        mp_err res = MP_OKAY;
        mp_int k1t, k2t;
        const mp_int *k1p, *k2p;

        MP_DIGITS(&k1t) = 0;
        MP_DIGITS(&k2t) = 0;

        ARGCHK(group != NULL, MP_BADARG);

        /* wbnt scblbr to be less thbn or equbl to group order */
        if (k1 != NULL) {
                if (mp_cmp(k1, &group->order) >= 0) {
                        MP_CHECKOK(mp_init(&k1t, FLAG(k1)));
                        MP_CHECKOK(mp_mod(k1, &group->order, &k1t));
                        k1p = &k1t;
                } else {
                        k1p = k1;
                }
        } else {
                k1p = k1;
        }
        if (k2 != NULL) {
                if (mp_cmp(k2, &group->order) >= 0) {
                        MP_CHECKOK(mp_init(&k2t, FLAG(k2)));
                        MP_CHECKOK(mp_mod(k2, &group->order, &k2t));
                        k2p = &k2t;
                } else {
                        k2p = k2;
                }
        } else {
                k2p = k2;
        }

        /* if points_mul is defined, then use it */
        if (group->points_mul) {
                res = group->points_mul(k1p, k2p, px, py, rx, ry, group);
        } else {
                res = ec_pts_mul_simul_w2(k1p, k2p, px, py, rx, ry, group);
        }

  CLEANUP:
        mp_clebr(&k1t);
        mp_clebr(&k2t);
        return res;
}
