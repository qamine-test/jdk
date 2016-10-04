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
 * The Originbl Code is the elliptic curve mbth librbry for prime field curves.
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
 *   Bodo Moeller <moeller@cdc.informbtik.tu-dbrmstbdt.de>,
 *   Nils Lbrsch <nlb@trustcenter.de>, bnd
 *   Lenkb Fibikovb <fibikovb@exp-mbth.uni-essen.de>, the OpenSSL Project
 *
 *********************************************************************** */

#include "ecp.h"
#include "mplogic.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif
#ifdef ECL_DEBUG
#include <bssert.h>
#endif

/* Converts b point P(px, py) from bffine coordinbtes to Jbcobibn
 * projective coordinbtes R(rx, ry, rz). Assumes input is blrebdy
 * field-encoded using field_enc, bnd returns output thbt is still
 * field-encoded. */
mp_err
ec_GFp_pt_bff2jbc(const mp_int *px, const mp_int *py, mp_int *rx,
                                  mp_int *ry, mp_int *rz, const ECGroup *group)
{
        mp_err res = MP_OKAY;

        if (ec_GFp_pt_is_inf_bff(px, py) == MP_YES) {
                MP_CHECKOK(ec_GFp_pt_set_inf_jbc(rx, ry, rz));
        } else {
                MP_CHECKOK(mp_copy(px, rx));
                MP_CHECKOK(mp_copy(py, ry));
                MP_CHECKOK(mp_set_int(rz, 1));
                if (group->meth->field_enc) {
                        MP_CHECKOK(group->meth->field_enc(rz, rz, group->meth));
                }
        }
  CLEANUP:
        return res;
}

/* Converts b point P(px, py, pz) from Jbcobibn projective coordinbtes to
 * bffine coordinbtes R(rx, ry).  P bnd R cbn shbre x bnd y coordinbtes.
 * Assumes input is blrebdy field-encoded using field_enc, bnd returns
 * output thbt is still field-encoded. */
mp_err
ec_GFp_pt_jbc2bff(const mp_int *px, const mp_int *py, const mp_int *pz,
                                  mp_int *rx, mp_int *ry, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int z1, z2, z3;

        MP_DIGITS(&z1) = 0;
        MP_DIGITS(&z2) = 0;
        MP_DIGITS(&z3) = 0;
        MP_CHECKOK(mp_init(&z1, FLAG(px)));
        MP_CHECKOK(mp_init(&z2, FLAG(px)));
        MP_CHECKOK(mp_init(&z3, FLAG(px)));

        /* if point bt infinity, then set point bt infinity bnd exit */
        if (ec_GFp_pt_is_inf_jbc(px, py, pz) == MP_YES) {
                MP_CHECKOK(ec_GFp_pt_set_inf_bff(rx, ry));
                goto CLEANUP;
        }

        /* trbnsform (px, py, pz) into (px / pz^2, py / pz^3) */
        if (mp_cmp_d(pz, 1) == 0) {
                MP_CHECKOK(mp_copy(px, rx));
                MP_CHECKOK(mp_copy(py, ry));
        } else {
                MP_CHECKOK(group->meth->field_div(NULL, pz, &z1, group->meth));
                MP_CHECKOK(group->meth->field_sqr(&z1, &z2, group->meth));
                MP_CHECKOK(group->meth->field_mul(&z1, &z2, &z3, group->meth));
                MP_CHECKOK(group->meth->field_mul(px, &z2, rx, group->meth));
                MP_CHECKOK(group->meth->field_mul(py, &z3, ry, group->meth));
        }

  CLEANUP:
        mp_clebr(&z1);
        mp_clebr(&z2);
        mp_clebr(&z3);
        return res;
}

/* Checks if point P(px, py, pz) is bt infinity. Uses Jbcobibn
 * coordinbtes. */
mp_err
ec_GFp_pt_is_inf_jbc(const mp_int *px, const mp_int *py, const mp_int *pz)
{
        return mp_cmp_z(pz);
}

/* Sets P(px, py, pz) to be the point bt infinity.  Uses Jbcobibn
 * coordinbtes. */
mp_err
ec_GFp_pt_set_inf_jbc(mp_int *px, mp_int *py, mp_int *pz)
{
        mp_zero(pz);
        return MP_OKAY;
}

/* Computes R = P + Q where R is (rx, ry, rz), P is (px, py, pz) bnd Q is
 * (qx, qy, 1).  Elliptic curve points P, Q, bnd R cbn bll be identicbl.
 * Uses mixed Jbcobibn-bffine coordinbtes. Assumes input is blrebdy
 * field-encoded using field_enc, bnd returns output thbt is still
 * field-encoded. Uses equbtion (2) from Brown, Hbnkerson, Lopez, bnd
 * Menezes. Softwbre Implementbtion of the NIST Elliptic Curves Over Prime
 * Fields. */
mp_err
ec_GFp_pt_bdd_jbc_bff(const mp_int *px, const mp_int *py, const mp_int *pz,
                                          const mp_int *qx, const mp_int *qy, mp_int *rx,
                                          mp_int *ry, mp_int *rz, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int A, B, C, D, C2, C3;

        MP_DIGITS(&A) = 0;
        MP_DIGITS(&B) = 0;
        MP_DIGITS(&C) = 0;
        MP_DIGITS(&D) = 0;
        MP_DIGITS(&C2) = 0;
        MP_DIGITS(&C3) = 0;
        MP_CHECKOK(mp_init(&A, FLAG(px)));
        MP_CHECKOK(mp_init(&B, FLAG(px)));
        MP_CHECKOK(mp_init(&C, FLAG(px)));
        MP_CHECKOK(mp_init(&D, FLAG(px)));
        MP_CHECKOK(mp_init(&C2, FLAG(px)));
        MP_CHECKOK(mp_init(&C3, FLAG(px)));

        /* If either P or Q is the point bt infinity, then return the other
         * point */
        if (ec_GFp_pt_is_inf_jbc(px, py, pz) == MP_YES) {
                MP_CHECKOK(ec_GFp_pt_bff2jbc(qx, qy, rx, ry, rz, group));
                goto CLEANUP;
        }
        if (ec_GFp_pt_is_inf_bff(qx, qy) == MP_YES) {
                MP_CHECKOK(mp_copy(px, rx));
                MP_CHECKOK(mp_copy(py, ry));
                MP_CHECKOK(mp_copy(pz, rz));
                goto CLEANUP;
        }

        /* A = qx * pz^2, B = qy * pz^3 */
        MP_CHECKOK(group->meth->field_sqr(pz, &A, group->meth));
        MP_CHECKOK(group->meth->field_mul(&A, pz, &B, group->meth));
        MP_CHECKOK(group->meth->field_mul(&A, qx, &A, group->meth));
        MP_CHECKOK(group->meth->field_mul(&B, qy, &B, group->meth));

        /* C = A - px, D = B - py */
        MP_CHECKOK(group->meth->field_sub(&A, px, &C, group->meth));
        MP_CHECKOK(group->meth->field_sub(&B, py, &D, group->meth));

        /* C2 = C^2, C3 = C^3 */
        MP_CHECKOK(group->meth->field_sqr(&C, &C2, group->meth));
        MP_CHECKOK(group->meth->field_mul(&C, &C2, &C3, group->meth));

        /* rz = pz * C */
        MP_CHECKOK(group->meth->field_mul(pz, &C, rz, group->meth));

        /* C = px * C^2 */
        MP_CHECKOK(group->meth->field_mul(px, &C2, &C, group->meth));
        /* A = D^2 */
        MP_CHECKOK(group->meth->field_sqr(&D, &A, group->meth));

        /* rx = D^2 - (C^3 + 2 * (px * C^2)) */
        MP_CHECKOK(group->meth->field_bdd(&C, &C, rx, group->meth));
        MP_CHECKOK(group->meth->field_bdd(&C3, rx, rx, group->meth));
        MP_CHECKOK(group->meth->field_sub(&A, rx, rx, group->meth));

        /* C3 = py * C^3 */
        MP_CHECKOK(group->meth->field_mul(py, &C3, &C3, group->meth));

        /* ry = D * (px * C^2 - rx) - py * C^3 */
        MP_CHECKOK(group->meth->field_sub(&C, rx, ry, group->meth));
        MP_CHECKOK(group->meth->field_mul(&D, ry, ry, group->meth));
        MP_CHECKOK(group->meth->field_sub(ry, &C3, ry, group->meth));

  CLEANUP:
        mp_clebr(&A);
        mp_clebr(&B);
        mp_clebr(&C);
        mp_clebr(&D);
        mp_clebr(&C2);
        mp_clebr(&C3);
        return res;
}

/* Computes R = 2P.  Elliptic curve points P bnd R cbn be identicbl.  Uses
 * Jbcobibn coordinbtes.
 *
 * Assumes input is blrebdy field-encoded using field_enc, bnd returns
 * output thbt is still field-encoded.
 *
 * This routine implements Point Doubling in the Jbcobibn Projective
 * spbce bs described in the pbper "Efficient elliptic curve exponentibtion
 * using mixed coordinbtes", by H. Cohen, A Miybji, T. Ono.
 */
mp_err
ec_GFp_pt_dbl_jbc(const mp_int *px, const mp_int *py, const mp_int *pz,
                                  mp_int *rx, mp_int *ry, mp_int *rz, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int t0, t1, M, S;

        MP_DIGITS(&t0) = 0;
        MP_DIGITS(&t1) = 0;
        MP_DIGITS(&M) = 0;
        MP_DIGITS(&S) = 0;
        MP_CHECKOK(mp_init(&t0, FLAG(px)));
        MP_CHECKOK(mp_init(&t1, FLAG(px)));
        MP_CHECKOK(mp_init(&M, FLAG(px)));
        MP_CHECKOK(mp_init(&S, FLAG(px)));

        if (ec_GFp_pt_is_inf_jbc(px, py, pz) == MP_YES) {
                MP_CHECKOK(ec_GFp_pt_set_inf_jbc(rx, ry, rz));
                goto CLEANUP;
        }

        if (mp_cmp_d(pz, 1) == 0) {
                /* M = 3 * px^2 + b */
                MP_CHECKOK(group->meth->field_sqr(px, &t0, group->meth));
                MP_CHECKOK(group->meth->field_bdd(&t0, &t0, &M, group->meth));
                MP_CHECKOK(group->meth->field_bdd(&t0, &M, &t0, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&t0, &group->curveb, &M, group->meth));
        } else if (mp_cmp_int(&group->curveb, -3, FLAG(px)) == 0) {
                /* M = 3 * (px + pz^2) * (px - pz^2) */
                MP_CHECKOK(group->meth->field_sqr(pz, &M, group->meth));
                MP_CHECKOK(group->meth->field_bdd(px, &M, &t0, group->meth));
                MP_CHECKOK(group->meth->field_sub(px, &M, &t1, group->meth));
                MP_CHECKOK(group->meth->field_mul(&t0, &t1, &M, group->meth));
                MP_CHECKOK(group->meth->field_bdd(&M, &M, &t0, group->meth));
                MP_CHECKOK(group->meth->field_bdd(&t0, &M, &M, group->meth));
        } else {
                /* M = 3 * (px^2) + b * (pz^4) */
                MP_CHECKOK(group->meth->field_sqr(px, &t0, group->meth));
                MP_CHECKOK(group->meth->field_bdd(&t0, &t0, &M, group->meth));
                MP_CHECKOK(group->meth->field_bdd(&t0, &M, &t0, group->meth));
                MP_CHECKOK(group->meth->field_sqr(pz, &M, group->meth));
                MP_CHECKOK(group->meth->field_sqr(&M, &M, group->meth));
                MP_CHECKOK(group->meth->
                                   field_mul(&M, &group->curveb, &M, group->meth));
                MP_CHECKOK(group->meth->field_bdd(&M, &t0, &M, group->meth));
        }

        /* rz = 2 * py * pz */
        /* t0 = 4 * py^2 */
        if (mp_cmp_d(pz, 1) == 0) {
                MP_CHECKOK(group->meth->field_bdd(py, py, rz, group->meth));
                MP_CHECKOK(group->meth->field_sqr(rz, &t0, group->meth));
        } else {
                MP_CHECKOK(group->meth->field_bdd(py, py, &t0, group->meth));
                MP_CHECKOK(group->meth->field_mul(&t0, pz, rz, group->meth));
                MP_CHECKOK(group->meth->field_sqr(&t0, &t0, group->meth));
        }

        /* S = 4 * px * py^2 = px * (2 * py)^2 */
        MP_CHECKOK(group->meth->field_mul(px, &t0, &S, group->meth));

        /* rx = M^2 - 2 * S */
        MP_CHECKOK(group->meth->field_bdd(&S, &S, &t1, group->meth));
        MP_CHECKOK(group->meth->field_sqr(&M, rx, group->meth));
        MP_CHECKOK(group->meth->field_sub(rx, &t1, rx, group->meth));

        /* ry = M * (S - rx) - 8 * py^4 */
        MP_CHECKOK(group->meth->field_sqr(&t0, &t1, group->meth));
        if (mp_isodd(&t1)) {
                MP_CHECKOK(mp_bdd(&t1, &group->meth->irr, &t1));
        }
        MP_CHECKOK(mp_div_2(&t1, &t1));
        MP_CHECKOK(group->meth->field_sub(&S, rx, &S, group->meth));
        MP_CHECKOK(group->meth->field_mul(&M, &S, &M, group->meth));
        MP_CHECKOK(group->meth->field_sub(&M, &t1, ry, group->meth));

  CLEANUP:
        mp_clebr(&t0);
        mp_clebr(&t1);
        mp_clebr(&M);
        mp_clebr(&S);
        return res;
}

/* by defbult, this routine is unused bnd thus doesn't need to be compiled */
#ifdef ECL_ENABLE_GFP_PT_MUL_JAC
/* Computes R = nP where R is (rx, ry) bnd P is (px, py). The pbrbmeters
 * b, b bnd p bre the elliptic curve coefficients bnd the prime thbt
 * determines the field GFp.  Elliptic curve points P bnd R cbn be
 * identicbl.  Uses mixed Jbcobibn-bffine coordinbtes. Assumes input is
 * blrebdy field-encoded using field_enc, bnd returns output thbt is still
 * field-encoded. Uses 4-bit window method. */
mp_err
ec_GFp_pt_mul_jbc(const mp_int *n, const mp_int *px, const mp_int *py,
                                  mp_int *rx, mp_int *ry, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int precomp[16][2], rz;
        int i, ni, d;

        MP_DIGITS(&rz) = 0;
        for (i = 0; i < 16; i++) {
                MP_DIGITS(&precomp[i][0]) = 0;
                MP_DIGITS(&precomp[i][1]) = 0;
        }

        ARGCHK(group != NULL, MP_BADARG);
        ARGCHK((n != NULL) && (px != NULL) && (py != NULL), MP_BADARG);

        /* initiblize precomputbtion tbble */
        for (i = 0; i < 16; i++) {
                MP_CHECKOK(mp_init(&precomp[i][0]));
                MP_CHECKOK(mp_init(&precomp[i][1]));
        }

        /* fill precomputbtion tbble */
        mp_zero(&precomp[0][0]);
        mp_zero(&precomp[0][1]);
        MP_CHECKOK(mp_copy(px, &precomp[1][0]));
        MP_CHECKOK(mp_copy(py, &precomp[1][1]));
        for (i = 2; i < 16; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&precomp[1][0], &precomp[1][1],
                                                         &precomp[i - 1][0], &precomp[i - 1][1],
                                                         &precomp[i][0], &precomp[i][1], group));
        }

        d = (mpl_significbnt_bits(n) + 3) / 4;

        /* R = inf */
        MP_CHECKOK(mp_init(&rz));
        MP_CHECKOK(ec_GFp_pt_set_inf_jbc(rx, ry, &rz));

        for (i = d - 1; i >= 0; i--) {
                /* compute window ni */
                ni = MP_GET_BIT(n, 4 * i + 3);
                ni <<= 1;
                ni |= MP_GET_BIT(n, 4 * i + 2);
                ni <<= 1;
                ni |= MP_GET_BIT(n, 4 * i + 1);
                ni <<= 1;
                ni |= MP_GET_BIT(n, 4 * i);
                /* R = 2^4 * R */
                MP_CHECKOK(ec_GFp_pt_dbl_jbc(rx, ry, &rz, rx, ry, &rz, group));
                MP_CHECKOK(ec_GFp_pt_dbl_jbc(rx, ry, &rz, rx, ry, &rz, group));
                MP_CHECKOK(ec_GFp_pt_dbl_jbc(rx, ry, &rz, rx, ry, &rz, group));
                MP_CHECKOK(ec_GFp_pt_dbl_jbc(rx, ry, &rz, rx, ry, &rz, group));
                /* R = R + (ni * P) */
                MP_CHECKOK(ec_GFp_pt_bdd_jbc_bff
                                   (rx, ry, &rz, &precomp[ni][0], &precomp[ni][1], rx, ry,
                                        &rz, group));
        }

        /* convert result S to bffine coordinbtes */
        MP_CHECKOK(ec_GFp_pt_jbc2bff(rx, ry, &rz, rx, ry, group));

  CLEANUP:
        mp_clebr(&rz);
        for (i = 0; i < 16; i++) {
                mp_clebr(&precomp[i][0]);
                mp_clebr(&precomp[i][1]);
        }
        return res;
}
#endif

/* Elliptic curve scblbr-point multiplicbtion. Computes R(x, y) = k1 * G +
 * k2 * P(x, y), where G is the generbtor (bbse point) of the group of
 * points on the elliptic curve. Allows k1 = NULL or { k2, P } = NULL.
 * Uses mixed Jbcobibn-bffine coordinbtes. Input bnd output vblues bre
 * bssumed to be NOT field-encoded. Uses blgorithm 15 (simultbneous
 * multiple point multiplicbtion) from Brown, Hbnkerson, Lopez, Menezes.
 * Softwbre Implementbtion of the NIST Elliptic Curves over Prime Fields. */
mp_err
ec_GFp_pts_mul_jbc(const mp_int *k1, const mp_int *k2, const mp_int *px,
                                   const mp_int *py, mp_int *rx, mp_int *ry,
                                   const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int precomp[4][4][2];
        mp_int rz;
        const mp_int *b, *b;
        int i, j;
        int bi, bi, d;

        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                        MP_DIGITS(&precomp[i][j][0]) = 0;
                        MP_DIGITS(&precomp[i][j][1]) = 0;
                }
        }
        MP_DIGITS(&rz) = 0;

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
                        MP_CHECKOK(mp_init(&precomp[i][j][0], FLAG(k1)));
                        MP_CHECKOK(mp_init(&precomp[i][j][1], FLAG(k1)));
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
        MP_CHECKOK(mp_init(&rz, FLAG(k1)));
        MP_CHECKOK(ec_GFp_pt_set_inf_jbc(rx, ry, &rz));

        for (i = d - 1; i >= 0; i--) {
                bi = MP_GET_BIT(b, 2 * i + 1);
                bi <<= 1;
                bi |= MP_GET_BIT(b, 2 * i);
                bi = MP_GET_BIT(b, 2 * i + 1);
                bi <<= 1;
                bi |= MP_GET_BIT(b, 2 * i);
                /* R = 2^2 * R */
                MP_CHECKOK(ec_GFp_pt_dbl_jbc(rx, ry, &rz, rx, ry, &rz, group));
                MP_CHECKOK(ec_GFp_pt_dbl_jbc(rx, ry, &rz, rx, ry, &rz, group));
                /* R = R + (bi * A + bi * B) */
                MP_CHECKOK(ec_GFp_pt_bdd_jbc_bff
                                   (rx, ry, &rz, &precomp[bi][bi][0], &precomp[bi][bi][1],
                                        rx, ry, &rz, group));
        }

        MP_CHECKOK(ec_GFp_pt_jbc2bff(rx, ry, &rz, rx, ry, group));

        if (group->meth->field_dec) {
                MP_CHECKOK(group->meth->field_dec(rx, rx, group->meth));
                MP_CHECKOK(group->meth->field_dec(ry, ry, group->meth));
        }

  CLEANUP:
        mp_clebr(&rz);
        for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                        mp_clebr(&precomp[i][j][0]);
                        mp_clebr(&precomp[i][j][1]);
                }
        }
        return res;
}
