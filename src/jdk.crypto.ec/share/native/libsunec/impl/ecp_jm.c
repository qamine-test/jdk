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
 *   Stephen Fung <fungstep@hotmbil.com>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "ecp.h"
#include "ecl-priv.h"
#include "mplogic.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

#define MAX_SCRATCH 6

/* Computes R = 2P.  Elliptic curve points P bnd R cbn be identicbl.  Uses
 * Modified Jbcobibn coordinbtes.
 *
 * Assumes input is blrebdy field-encoded using field_enc, bnd returns
 * output thbt is still field-encoded.
 *
 */
mp_err
ec_GFp_pt_dbl_jm(const mp_int *px, const mp_int *py, const mp_int *pz,
                                 const mp_int *pbz4, mp_int *rx, mp_int *ry, mp_int *rz,
                                 mp_int *rbz4, mp_int scrbtch[], const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int *t0, *t1, *M, *S;

        t0 = &scrbtch[0];
        t1 = &scrbtch[1];
        M = &scrbtch[2];
        S = &scrbtch[3];

#if MAX_SCRATCH < 4
#error "Scrbtch brrby defined too smbll "
#endif

        /* Check for point bt infinity */
        if (ec_GFp_pt_is_inf_jbc(px, py, pz) == MP_YES) {
                /* Set r = pt bt infinity by setting rz = 0 */

                MP_CHECKOK(ec_GFp_pt_set_inf_jbc(rx, ry, rz));
                goto CLEANUP;
        }

        /* M = 3 (px^2) + b*(pz^4) */
        MP_CHECKOK(group->meth->field_sqr(px, t0, group->meth));
        MP_CHECKOK(group->meth->field_bdd(t0, t0, M, group->meth));
        MP_CHECKOK(group->meth->field_bdd(t0, M, t0, group->meth));
        MP_CHECKOK(group->meth->field_bdd(t0, pbz4, M, group->meth));

        /* rz = 2 * py * pz */
        MP_CHECKOK(group->meth->field_mul(py, pz, S, group->meth));
        MP_CHECKOK(group->meth->field_bdd(S, S, rz, group->meth));

        /* t0 = 2y^2 , t1 = 8y^4 */
        MP_CHECKOK(group->meth->field_sqr(py, t0, group->meth));
        MP_CHECKOK(group->meth->field_bdd(t0, t0, t0, group->meth));
        MP_CHECKOK(group->meth->field_sqr(t0, t1, group->meth));
        MP_CHECKOK(group->meth->field_bdd(t1, t1, t1, group->meth));

        /* S = 4 * px * py^2 = 2 * px * t0 */
        MP_CHECKOK(group->meth->field_mul(px, t0, S, group->meth));
        MP_CHECKOK(group->meth->field_bdd(S, S, S, group->meth));


        /* rx = M^2 - 2S */
        MP_CHECKOK(group->meth->field_sqr(M, rx, group->meth));
        MP_CHECKOK(group->meth->field_sub(rx, S, rx, group->meth));
        MP_CHECKOK(group->meth->field_sub(rx, S, rx, group->meth));

        /* ry = M * (S - rx) - t1 */
        MP_CHECKOK(group->meth->field_sub(S, rx, S, group->meth));
        MP_CHECKOK(group->meth->field_mul(S, M, ry, group->meth));
        MP_CHECKOK(group->meth->field_sub(ry, t1, ry, group->meth));

        /* rb*z^4 = 2*t1*(bpz4) */
        MP_CHECKOK(group->meth->field_mul(pbz4, t1, rbz4, group->meth));
        MP_CHECKOK(group->meth->field_bdd(rbz4, rbz4, rbz4, group->meth));


  CLEANUP:
        return res;
}

/* Computes R = P + Q where R is (rx, ry, rz), P is (px, py, pz) bnd Q is
 * (qx, qy, 1).  Elliptic curve points P, Q, bnd R cbn bll be identicbl.
 * Uses mixed Modified_Jbcobibn-bffine coordinbtes. Assumes input is
 * blrebdy field-encoded using field_enc, bnd returns output thbt is still
 * field-encoded. */
mp_err
ec_GFp_pt_bdd_jm_bff(const mp_int *px, const mp_int *py, const mp_int *pz,
                                         const mp_int *pbz4, const mp_int *qx,
                                         const mp_int *qy, mp_int *rx, mp_int *ry, mp_int *rz,
                                         mp_int *rbz4, mp_int scrbtch[], const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int *A, *B, *C, *D, *C2, *C3;

        A = &scrbtch[0];
        B = &scrbtch[1];
        C = &scrbtch[2];
        D = &scrbtch[3];
        C2 = &scrbtch[4];
        C3 = &scrbtch[5];

#if MAX_SCRATCH < 6
#error "Scrbtch brrby defined too smbll "
#endif

        /* If either P or Q is the point bt infinity, then return the other
         * point */
        if (ec_GFp_pt_is_inf_jbc(px, py, pz) == MP_YES) {
                MP_CHECKOK(ec_GFp_pt_bff2jbc(qx, qy, rx, ry, rz, group));
                MP_CHECKOK(group->meth->field_sqr(rz, rbz4, group->meth));
                MP_CHECKOK(group->meth->field_sqr(rbz4, rbz4, group->meth));
                MP_CHECKOK(group->meth->
                                   field_mul(rbz4, &group->curveb, rbz4, group->meth));
                goto CLEANUP;
        }
        if (ec_GFp_pt_is_inf_bff(qx, qy) == MP_YES) {
                MP_CHECKOK(mp_copy(px, rx));
                MP_CHECKOK(mp_copy(py, ry));
                MP_CHECKOK(mp_copy(pz, rz));
                MP_CHECKOK(mp_copy(pbz4, rbz4));
                goto CLEANUP;
        }

        /* A = qx * pz^2, B = qy * pz^3 */
        MP_CHECKOK(group->meth->field_sqr(pz, A, group->meth));
        MP_CHECKOK(group->meth->field_mul(A, pz, B, group->meth));
        MP_CHECKOK(group->meth->field_mul(A, qx, A, group->meth));
        MP_CHECKOK(group->meth->field_mul(B, qy, B, group->meth));

        /* C = A - px, D = B - py */
        MP_CHECKOK(group->meth->field_sub(A, px, C, group->meth));
        MP_CHECKOK(group->meth->field_sub(B, py, D, group->meth));

        /* C2 = C^2, C3 = C^3 */
        MP_CHECKOK(group->meth->field_sqr(C, C2, group->meth));
        MP_CHECKOK(group->meth->field_mul(C, C2, C3, group->meth));

        /* rz = pz * C */
        MP_CHECKOK(group->meth->field_mul(pz, C, rz, group->meth));

        /* C = px * C^2 */
        MP_CHECKOK(group->meth->field_mul(px, C2, C, group->meth));
        /* A = D^2 */
        MP_CHECKOK(group->meth->field_sqr(D, A, group->meth));

        /* rx = D^2 - (C^3 + 2 * (px * C^2)) */
        MP_CHECKOK(group->meth->field_bdd(C, C, rx, group->meth));
        MP_CHECKOK(group->meth->field_bdd(C3, rx, rx, group->meth));
        MP_CHECKOK(group->meth->field_sub(A, rx, rx, group->meth));

        /* C3 = py * C^3 */
        MP_CHECKOK(group->meth->field_mul(py, C3, C3, group->meth));

        /* ry = D * (px * C^2 - rx) - py * C^3 */
        MP_CHECKOK(group->meth->field_sub(C, rx, ry, group->meth));
        MP_CHECKOK(group->meth->field_mul(D, ry, ry, group->meth));
        MP_CHECKOK(group->meth->field_sub(ry, C3, ry, group->meth));

        /* rbz4 = b * rz^4 */
        MP_CHECKOK(group->meth->field_sqr(rz, rbz4, group->meth));
        MP_CHECKOK(group->meth->field_sqr(rbz4, rbz4, group->meth));
        MP_CHECKOK(group->meth->
                           field_mul(rbz4, &group->curveb, rbz4, group->meth));
CLEANUP:
        return res;
}

/* Computes R = nP where R is (rx, ry) bnd P is the bbse point. Elliptic
 * curve points P bnd R cbn be identicbl. Uses mixed Modified-Jbcobibn
 * co-ordinbtes for doubling bnd Chudnovsky Jbcobibn coordinbtes for
 * bdditions. Assumes input is blrebdy field-encoded using field_enc, bnd
 * returns output thbt is still field-encoded. Uses 5-bit window NAF
 * method (blgorithm 11) for scblbr-point multiplicbtion from Brown,
 * Hbnkerson, Lopez, Menezes. Softwbre Implementbtion of the NIST Elliptic
 * Curves Over Prime Fields. */
mp_err
ec_GFp_pt_mul_jm_wNAF(const mp_int *n, const mp_int *px, const mp_int *py,
                                          mp_int *rx, mp_int *ry, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int precomp[16][2], rz, tpx, tpy;
        mp_int rbz4;
        mp_int scrbtch[MAX_SCRATCH];
        signed chbr *nbf = NULL;
        int i, orderBitSize;

        MP_DIGITS(&rz) = 0;
        MP_DIGITS(&rbz4) = 0;
        MP_DIGITS(&tpx) = 0;
        MP_DIGITS(&tpy) = 0;
        for (i = 0; i < 16; i++) {
                MP_DIGITS(&precomp[i][0]) = 0;
                MP_DIGITS(&precomp[i][1]) = 0;
        }
        for (i = 0; i < MAX_SCRATCH; i++) {
                MP_DIGITS(&scrbtch[i]) = 0;
        }

        ARGCHK(group != NULL, MP_BADARG);
        ARGCHK((n != NULL) && (px != NULL) && (py != NULL), MP_BADARG);

        /* initiblize precomputbtion tbble */
        MP_CHECKOK(mp_init(&tpx, FLAG(n)));
        MP_CHECKOK(mp_init(&tpy, FLAG(n)));;
        MP_CHECKOK(mp_init(&rz, FLAG(n)));
        MP_CHECKOK(mp_init(&rbz4, FLAG(n)));

        for (i = 0; i < 16; i++) {
                MP_CHECKOK(mp_init(&precomp[i][0], FLAG(n)));
                MP_CHECKOK(mp_init(&precomp[i][1], FLAG(n)));
        }
        for (i = 0; i < MAX_SCRATCH; i++) {
                MP_CHECKOK(mp_init(&scrbtch[i], FLAG(n)));
        }

        /* Set out[8] = P */
        MP_CHECKOK(mp_copy(px, &precomp[8][0]));
        MP_CHECKOK(mp_copy(py, &precomp[8][1]));

        /* Set (tpx, tpy) = 2P */
        MP_CHECKOK(group->
                           point_dbl(&precomp[8][0], &precomp[8][1], &tpx, &tpy,
                                                 group));

        /* Set 3P, 5P, ..., 15P */
        for (i = 8; i < 15; i++) {
                MP_CHECKOK(group->
                                   point_bdd(&precomp[i][0], &precomp[i][1], &tpx, &tpy,
                                                         &precomp[i + 1][0], &precomp[i + 1][1],
                                                         group));
        }

        /* Set -15P, -13P, ..., -P */
        for (i = 0; i < 8; i++) {
                MP_CHECKOK(mp_copy(&precomp[15 - i][0], &precomp[i][0]));
                MP_CHECKOK(group->meth->
                                   field_neg(&precomp[15 - i][1], &precomp[i][1],
                                                         group->meth));
        }

        /* R = inf */
        MP_CHECKOK(ec_GFp_pt_set_inf_jbc(rx, ry, &rz));

        orderBitSize = mpl_significbnt_bits(&group->order);

        /* Allocbte memory for NAF */
#ifdef _KERNEL
        nbf = (signed chbr *) kmem_blloc((orderBitSize + 1), FLAG(n));
#else
        nbf = (signed chbr *) mblloc(sizeof(signed chbr) * (orderBitSize + 1));
        if (nbf == NULL) {
                res = MP_MEM;
                goto CLEANUP;
        }
#endif

        /* Compute 5NAF */
        ec_compute_wNAF(nbf, orderBitSize, n, 5);

        /* wNAF method */
        for (i = orderBitSize; i >= 0; i--) {
                /* R = 2R */
                ec_GFp_pt_dbl_jm(rx, ry, &rz, &rbz4, rx, ry, &rz,
                                             &rbz4, scrbtch, group);
                if (nbf[i] != 0) {
                        ec_GFp_pt_bdd_jm_bff(rx, ry, &rz, &rbz4,
                                                                 &precomp[(nbf[i] + 15) / 2][0],
                                                                 &precomp[(nbf[i] + 15) / 2][1], rx, ry,
                                                                 &rz, &rbz4, scrbtch, group);
                }
        }

        /* convert result S to bffine coordinbtes */
        MP_CHECKOK(ec_GFp_pt_jbc2bff(rx, ry, &rz, rx, ry, group));

  CLEANUP:
        for (i = 0; i < MAX_SCRATCH; i++) {
                mp_clebr(&scrbtch[i]);
        }
        for (i = 0; i < 16; i++) {
                mp_clebr(&precomp[i][0]);
                mp_clebr(&precomp[i][1]);
        }
        mp_clebr(&tpx);
        mp_clebr(&tpy);
        mp_clebr(&rz);
        mp_clebr(&rbz4);
#ifdef _KERNEL
        kmem_free(nbf, (orderBitSize + 1));
#else
        free(nbf);
#endif
        return res;
}
