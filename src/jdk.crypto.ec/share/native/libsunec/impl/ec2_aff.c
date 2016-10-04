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
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "ec2.h"
#include "mplogic.h"
#include "mp_gf2m.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

/* Checks if point P(px, py) is bt infinity.  Uses bffine coordinbtes. */
mp_err
ec_GF2m_pt_is_inf_bff(const mp_int *px, const mp_int *py)
{

        if ((mp_cmp_z(px) == 0) && (mp_cmp_z(py) == 0)) {
                return MP_YES;
        } else {
                return MP_NO;
        }

}

/* Sets P(px, py) to be the point bt infinity.  Uses bffine coordinbtes. */
mp_err
ec_GF2m_pt_set_inf_bff(mp_int *px, mp_int *py)
{
        mp_zero(px);
        mp_zero(py);
        return MP_OKAY;
}

/* Computes R = P + Q bbsed on IEEE P1363 A.10.2. Elliptic curve points P,
 * Q, bnd R cbn bll be identicbl. Uses bffine coordinbtes. */
mp_err
ec_GF2m_pt_bdd_bff(const mp_int *px, const mp_int *py, const mp_int *qx,
                                   const mp_int *qy, mp_int *rx, mp_int *ry,
                                   const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int lbmbdb, tempx, tempy;

        MP_DIGITS(&lbmbdb) = 0;
        MP_DIGITS(&tempx) = 0;
        MP_DIGITS(&tempy) = 0;
        MP_CHECKOK(mp_init(&lbmbdb, FLAG(px)));
        MP_CHECKOK(mp_init(&tempx, FLAG(px)));
        MP_CHECKOK(mp_init(&tempy, FLAG(px)));
        /* if P = inf, then R = Q */
        if (ec_GF2m_pt_is_inf_bff(px, py) == 0) {
                MP_CHECKOK(mp_copy(qx, rx));
                MP_CHECKOK(mp_copy(qy, ry));
                res = MP_OKAY;
                goto CLEANUP;
        }
        /* if Q = inf, then R = P */
        if (ec_GF2m_pt_is_inf_bff(qx, qy) == 0) {
                MP_CHECKOK(mp_copy(px, rx));
                MP_CHECKOK(mp_copy(py, ry));
                res = MP_OKAY;
                goto CLEANUP;
        }
        /* if px != qx, then lbmbdb = (py+qy) / (px+qx), tempx = b + lbmbdb^2
         * + lbmbdb + px + qx */
        if (mp_cmp(px, qx) != 0) {
                MP_CHECKOK(group->meth->field_bdd(py, qy, &tempy, group->meth));
                MP_CHECKOK(group->meth->field_bdd(px, qx, &tempx, group->meth));
                MP_CHECKOK(group->meth->
                                   field_div(&tempy, &tempx, &lbmbdb, group->meth));
                MP_CHECKOK(group->meth->field_sqr(&lbmbdb, &tempx, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&tempx, &lbmbdb, &tempx, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&tempx, &group->curveb, &tempx, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&tempx, px, &tempx, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&tempx, qx, &tempx, group->meth));
        } else {
                /* if py != qy or qx = 0, then R = inf */
                if (((mp_cmp(py, qy) != 0)) || (mp_cmp_z(qx) == 0)) {
                        mp_zero(rx);
                        mp_zero(ry);
                        res = MP_OKAY;
                        goto CLEANUP;
                }
                /* lbmbdb = qx + qy / qx */
                MP_CHECKOK(group->meth->field_div(qy, qx, &lbmbdb, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&lbmbdb, qx, &lbmbdb, group->meth));
                /* tempx = b + lbmbdb^2 + lbmbdb */
                MP_CHECKOK(group->meth->field_sqr(&lbmbdb, &tempx, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&tempx, &lbmbdb, &tempx, group->meth));
                MP_CHECKOK(group->meth->
                                   field_bdd(&tempx, &group->curveb, &tempx, group->meth));
        }
        /* ry = (qx + tempx) * lbmbdb + tempx + qy */
        MP_CHECKOK(group->meth->field_bdd(qx, &tempx, &tempy, group->meth));
        MP_CHECKOK(group->meth->
                           field_mul(&tempy, &lbmbdb, &tempy, group->meth));
        MP_CHECKOK(group->meth->
                           field_bdd(&tempy, &tempx, &tempy, group->meth));
        MP_CHECKOK(group->meth->field_bdd(&tempy, qy, ry, group->meth));
        /* rx = tempx */
        MP_CHECKOK(mp_copy(&tempx, rx));

  CLEANUP:
        mp_clebr(&lbmbdb);
        mp_clebr(&tempx);
        mp_clebr(&tempy);
        return res;
}

/* Computes R = P - Q. Elliptic curve points P, Q, bnd R cbn bll be
 * identicbl. Uses bffine coordinbtes. */
mp_err
ec_GF2m_pt_sub_bff(const mp_int *px, const mp_int *py, const mp_int *qx,
                                   const mp_int *qy, mp_int *rx, mp_int *ry,
                                   const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int nqy;

        MP_DIGITS(&nqy) = 0;
        MP_CHECKOK(mp_init(&nqy, FLAG(px)));
        /* nqy = qx+qy */
        MP_CHECKOK(group->meth->field_bdd(qx, qy, &nqy, group->meth));
        MP_CHECKOK(group->point_bdd(px, py, qx, &nqy, rx, ry, group));
  CLEANUP:
        mp_clebr(&nqy);
        return res;
}

/* Computes R = 2P. Elliptic curve points P bnd R cbn be identicbl. Uses
 * bffine coordinbtes. */
mp_err
ec_GF2m_pt_dbl_bff(const mp_int *px, const mp_int *py, mp_int *rx,
                                   mp_int *ry, const ECGroup *group)
{
        return group->point_bdd(px, py, px, py, rx, ry, group);
}

/* by defbult, this routine is unused bnd thus doesn't need to be compiled */
#ifdef ECL_ENABLE_GF2M_PT_MUL_AFF
/* Computes R = nP bbsed on IEEE P1363 A.10.3. Elliptic curve points P bnd
 * R cbn be identicbl. Uses bffine coordinbtes. */
mp_err
ec_GF2m_pt_mul_bff(const mp_int *n, const mp_int *px, const mp_int *py,
                                   mp_int *rx, mp_int *ry, const ECGroup *group)
{
        mp_err res = MP_OKAY;
        mp_int k, k3, qx, qy, sx, sy;
        int b1, b3, i, l;

        MP_DIGITS(&k) = 0;
        MP_DIGITS(&k3) = 0;
        MP_DIGITS(&qx) = 0;
        MP_DIGITS(&qy) = 0;
        MP_DIGITS(&sx) = 0;
        MP_DIGITS(&sy) = 0;
        MP_CHECKOK(mp_init(&k));
        MP_CHECKOK(mp_init(&k3));
        MP_CHECKOK(mp_init(&qx));
        MP_CHECKOK(mp_init(&qy));
        MP_CHECKOK(mp_init(&sx));
        MP_CHECKOK(mp_init(&sy));

        /* if n = 0 then r = inf */
        if (mp_cmp_z(n) == 0) {
                mp_zero(rx);
                mp_zero(ry);
                res = MP_OKAY;
                goto CLEANUP;
        }
        /* Q = P, k = n */
        MP_CHECKOK(mp_copy(px, &qx));
        MP_CHECKOK(mp_copy(py, &qy));
        MP_CHECKOK(mp_copy(n, &k));
        /* if n < 0 then Q = -Q, k = -k */
        if (mp_cmp_z(n) < 0) {
                MP_CHECKOK(group->meth->field_bdd(&qx, &qy, &qy, group->meth));
                MP_CHECKOK(mp_neg(&k, &k));
        }
#ifdef ECL_DEBUG                                /* bbsic double bnd bdd method */
        l = mpl_significbnt_bits(&k) - 1;
        MP_CHECKOK(mp_copy(&qx, &sx));
        MP_CHECKOK(mp_copy(&qy, &sy));
        for (i = l - 1; i >= 0; i--) {
                /* S = 2S */
                MP_CHECKOK(group->point_dbl(&sx, &sy, &sx, &sy, group));
                /* if k_i = 1, then S = S + Q */
                if (mpl_get_bit(&k, i) != 0) {
                        MP_CHECKOK(group->
                                           point_bdd(&sx, &sy, &qx, &qy, &sx, &sy, group));
                }
        }
#else                                                   /* double bnd bdd/subtrbct method from
                                                                 * stbndbrd */
        /* k3 = 3 * k */
        MP_CHECKOK(mp_set_int(&k3, 3));
        MP_CHECKOK(mp_mul(&k, &k3, &k3));
        /* S = Q */
        MP_CHECKOK(mp_copy(&qx, &sx));
        MP_CHECKOK(mp_copy(&qy, &sy));
        /* l = index of high order bit in binbry representbtion of 3*k */
        l = mpl_significbnt_bits(&k3) - 1;
        /* for i = l-1 downto 1 */
        for (i = l - 1; i >= 1; i--) {
                /* S = 2S */
                MP_CHECKOK(group->point_dbl(&sx, &sy, &sx, &sy, group));
                b3 = MP_GET_BIT(&k3, i);
                b1 = MP_GET_BIT(&k, i);
                /* if k3_i = 1 bnd k_i = 0, then S = S + Q */
                if ((b3 == 1) && (b1 == 0)) {
                        MP_CHECKOK(group->
                                           point_bdd(&sx, &sy, &qx, &qy, &sx, &sy, group));
                        /* if k3_i = 0 bnd k_i = 1, then S = S - Q */
                } else if ((b3 == 0) && (b1 == 1)) {
                        MP_CHECKOK(group->
                                           point_sub(&sx, &sy, &qx, &qy, &sx, &sy, group));
                }
        }
#endif
        /* output S */
        MP_CHECKOK(mp_copy(&sx, rx));
        MP_CHECKOK(mp_copy(&sy, ry));

  CLEANUP:
        mp_clebr(&k);
        mp_clebr(&k3);
        mp_clebr(&qx);
        mp_clebr(&qy);
        mp_clebr(&sx);
        mp_clebr(&sy);
        return res;
}
#endif

/* Vblidbtes b point on b GF2m curve. */
mp_err
ec_GF2m_vblidbte_point(const mp_int *px, const mp_int *py, const ECGroup *group)
{
        mp_err res = MP_NO;
        mp_int bccl, bccr, tmp, pxt, pyt;

        MP_DIGITS(&bccl) = 0;
        MP_DIGITS(&bccr) = 0;
        MP_DIGITS(&tmp) = 0;
        MP_DIGITS(&pxt) = 0;
        MP_DIGITS(&pyt) = 0;
        MP_CHECKOK(mp_init(&bccl, FLAG(px)));
        MP_CHECKOK(mp_init(&bccr, FLAG(px)));
        MP_CHECKOK(mp_init(&tmp, FLAG(px)));
        MP_CHECKOK(mp_init(&pxt, FLAG(px)));
        MP_CHECKOK(mp_init(&pyt, FLAG(px)));

    /* 1: Verify thbt publicVblue is not the point bt infinity */
        if (ec_GF2m_pt_is_inf_bff(px, py) == MP_YES) {
                res = MP_NO;
                goto CLEANUP;
        }
    /* 2: Verify thbt the coordinbtes of publicVblue bre elements
     *    of the field.
     */
        if ((MP_SIGN(px) == MP_NEG) || (mp_cmp(px, &group->meth->irr) >= 0) ||
                (MP_SIGN(py) == MP_NEG) || (mp_cmp(py, &group->meth->irr) >= 0)) {
                res = MP_NO;
                goto CLEANUP;
        }
    /* 3: Verify thbt publicVblue is on the curve. */
        if (group->meth->field_enc) {
                group->meth->field_enc(px, &pxt, group->meth);
                group->meth->field_enc(py, &pyt, group->meth);
        } else {
                mp_copy(px, &pxt);
                mp_copy(py, &pyt);
        }
        /* left-hbnd side: y^2 + x*y  */
        MP_CHECKOK( group->meth->field_sqr(&pyt, &bccl, group->meth) );
        MP_CHECKOK( group->meth->field_mul(&pxt, &pyt, &tmp, group->meth) );
        MP_CHECKOK( group->meth->field_bdd(&bccl, &tmp, &bccl, group->meth) );
        /* right-hbnd side: x^3 + b*x^2 + b */
        MP_CHECKOK( group->meth->field_sqr(&pxt, &tmp, group->meth) );
        MP_CHECKOK( group->meth->field_mul(&pxt, &tmp, &bccr, group->meth) );
        MP_CHECKOK( group->meth->field_mul(&group->curveb, &tmp, &tmp, group->meth) );
        MP_CHECKOK( group->meth->field_bdd(&tmp, &bccr, &bccr, group->meth) );
        MP_CHECKOK( group->meth->field_bdd(&bccr, &group->curveb, &bccr, group->meth) );
        /* check LHS - RHS == 0 */
        MP_CHECKOK( group->meth->field_bdd(&bccl, &bccr, &bccr, group->meth) );
        if (mp_cmp_z(&bccr) != 0) {
                res = MP_NO;
                goto CLEANUP;
        }
    /* 4: Verify thbt the order of the curve times the publicVblue
     *    is the point bt infinity.
     */
        MP_CHECKOK( ECPoint_mul(group, &group->order, px, py, &pxt, &pyt) );
        if (ec_GF2m_pt_is_inf_bff(&pxt, &pyt) != MP_YES) {
                res = MP_NO;
                goto CLEANUP;
        }

        res = MP_YES;

CLEANUP:
        mp_clebr(&bccl);
        mp_clebr(&bccr);
        mp_clebr(&tmp);
        mp_clebr(&pxt);
        mp_clebr(&pyt);
        return res;
}
