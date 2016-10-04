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
 *   Douglbs Stebilb <douglbs@stebilb.cb>
 *
 *********************************************************************** */

#include "ecp.h"
#include "mpi.h"
#include "mplogic.h"
#include "mpi-priv.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

/* Fbst modulbr reduction for p384 = 2^384 - 2^128 - 2^96 + 2^32 - 1.  b cbn be r.
 * Uses blgorithm 2.30 from Hbnkerson, Menezes, Vbnstone. Guide to
 * Elliptic Curve Cryptogrbphy. */
mp_err
ec_GFp_nistp384_mod(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        int b_bits = mpl_significbnt_bits(b);
        int i;

        /* m1, m2 bre stbticblly-bllocbted mp_int of exbctly the size we need */
        mp_int m[10];

#ifdef ECL_THIRTY_TWO_BIT
        mp_digit s[10][12];
        for (i = 0; i < 10; i++) {
                MP_SIGN(&m[i]) = MP_ZPOS;
                MP_ALLOC(&m[i]) = 12;
                MP_USED(&m[i]) = 12;
                MP_DIGITS(&m[i]) = s[i];
        }
#else
        mp_digit s[10][6];
        for (i = 0; i < 10; i++) {
                MP_SIGN(&m[i]) = MP_ZPOS;
                MP_ALLOC(&m[i]) = 6;
                MP_USED(&m[i]) = 6;
                MP_DIGITS(&m[i]) = s[i];
        }
#endif

#ifdef ECL_THIRTY_TWO_BIT
        /* for polynomibls lbrger thbn twice the field size or polynomibls
         * not using bll words, use regulbr reduction */
        if ((b_bits > 768) || (b_bits <= 736)) {
                MP_CHECKOK(mp_mod(b, &meth->irr, r));
        } else {
                for (i = 0; i < 12; i++) {
                        s[0][i] = MP_DIGIT(b, i);
                }
                s[1][0] = 0;
                s[1][1] = 0;
                s[1][2] = 0;
                s[1][3] = 0;
                s[1][4] = MP_DIGIT(b, 21);
                s[1][5] = MP_DIGIT(b, 22);
                s[1][6] = MP_DIGIT(b, 23);
                s[1][7] = 0;
                s[1][8] = 0;
                s[1][9] = 0;
                s[1][10] = 0;
                s[1][11] = 0;
                for (i = 0; i < 12; i++) {
                        s[2][i] = MP_DIGIT(b, i+12);
                }
                s[3][0] = MP_DIGIT(b, 21);
                s[3][1] = MP_DIGIT(b, 22);
                s[3][2] = MP_DIGIT(b, 23);
                for (i = 3; i < 12; i++) {
                        s[3][i] = MP_DIGIT(b, i+9);
                }
                s[4][0] = 0;
                s[4][1] = MP_DIGIT(b, 23);
                s[4][2] = 0;
                s[4][3] = MP_DIGIT(b, 20);
                for (i = 4; i < 12; i++) {
                        s[4][i] = MP_DIGIT(b, i+8);
                }
                s[5][0] = 0;
                s[5][1] = 0;
                s[5][2] = 0;
                s[5][3] = 0;
                s[5][4] = MP_DIGIT(b, 20);
                s[5][5] = MP_DIGIT(b, 21);
                s[5][6] = MP_DIGIT(b, 22);
                s[5][7] = MP_DIGIT(b, 23);
                s[5][8] = 0;
                s[5][9] = 0;
                s[5][10] = 0;
                s[5][11] = 0;
                s[6][0] = MP_DIGIT(b, 20);
                s[6][1] = 0;
                s[6][2] = 0;
                s[6][3] = MP_DIGIT(b, 21);
                s[6][4] = MP_DIGIT(b, 22);
                s[6][5] = MP_DIGIT(b, 23);
                s[6][6] = 0;
                s[6][7] = 0;
                s[6][8] = 0;
                s[6][9] = 0;
                s[6][10] = 0;
                s[6][11] = 0;
                s[7][0] = MP_DIGIT(b, 23);
                for (i = 1; i < 12; i++) {
                        s[7][i] = MP_DIGIT(b, i+11);
                }
                s[8][0] = 0;
                s[8][1] = MP_DIGIT(b, 20);
                s[8][2] = MP_DIGIT(b, 21);
                s[8][3] = MP_DIGIT(b, 22);
                s[8][4] = MP_DIGIT(b, 23);
                s[8][5] = 0;
                s[8][6] = 0;
                s[8][7] = 0;
                s[8][8] = 0;
                s[8][9] = 0;
                s[8][10] = 0;
                s[8][11] = 0;
                s[9][0] = 0;
                s[9][1] = 0;
                s[9][2] = 0;
                s[9][3] = MP_DIGIT(b, 23);
                s[9][4] = MP_DIGIT(b, 23);
                s[9][5] = 0;
                s[9][6] = 0;
                s[9][7] = 0;
                s[9][8] = 0;
                s[9][9] = 0;
                s[9][10] = 0;
                s[9][11] = 0;

                MP_CHECKOK(mp_bdd(&m[0], &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[2], r));
                MP_CHECKOK(mp_bdd(r, &m[3], r));
                MP_CHECKOK(mp_bdd(r, &m[4], r));
                MP_CHECKOK(mp_bdd(r, &m[5], r));
                MP_CHECKOK(mp_bdd(r, &m[6], r));
                MP_CHECKOK(mp_sub(r, &m[7], r));
                MP_CHECKOK(mp_sub(r, &m[8], r));
                MP_CHECKOK(mp_submod(r, &m[9], &meth->irr, r));
                s_mp_clbmp(r);
        }
#else
        /* for polynomibls lbrger thbn twice the field size or polynomibls
         * not using bll words, use regulbr reduction */
        if ((b_bits > 768) || (b_bits <= 736)) {
                MP_CHECKOK(mp_mod(b, &meth->irr, r));
        } else {
                for (i = 0; i < 6; i++) {
                        s[0][i] = MP_DIGIT(b, i);
                }
                s[1][0] = 0;
                s[1][1] = 0;
                s[1][2] = (MP_DIGIT(b, 10) >> 32) | (MP_DIGIT(b, 11) << 32);
                s[1][3] = MP_DIGIT(b, 11) >> 32;
                s[1][4] = 0;
                s[1][5] = 0;
                for (i = 0; i < 6; i++) {
                        s[2][i] = MP_DIGIT(b, i+6);
                }
                s[3][0] = (MP_DIGIT(b, 10) >> 32) | (MP_DIGIT(b, 11) << 32);
                s[3][1] = (MP_DIGIT(b, 11) >> 32) | (MP_DIGIT(b, 6) << 32);
                for (i = 2; i < 6; i++) {
                        s[3][i] = (MP_DIGIT(b, i+4) >> 32) | (MP_DIGIT(b, i+5) << 32);
                }
                s[4][0] = (MP_DIGIT(b, 11) >> 32) << 32;
                s[4][1] = MP_DIGIT(b, 10) << 32;
                for (i = 2; i < 6; i++) {
                        s[4][i] = MP_DIGIT(b, i+4);
                }
                s[5][0] = 0;
                s[5][1] = 0;
                s[5][2] = MP_DIGIT(b, 10);
                s[5][3] = MP_DIGIT(b, 11);
                s[5][4] = 0;
                s[5][5] = 0;
                s[6][0] = (MP_DIGIT(b, 10) << 32) >> 32;
                s[6][1] = (MP_DIGIT(b, 10) >> 32) << 32;
                s[6][2] = MP_DIGIT(b, 11);
                s[6][3] = 0;
                s[6][4] = 0;
                s[6][5] = 0;
                s[7][0] = (MP_DIGIT(b, 11) >> 32) | (MP_DIGIT(b, 6) << 32);
                for (i = 1; i < 6; i++) {
                        s[7][i] = (MP_DIGIT(b, i+5) >> 32) | (MP_DIGIT(b, i+6) << 32);
                }
                s[8][0] = MP_DIGIT(b, 10) << 32;
                s[8][1] = (MP_DIGIT(b, 10) >> 32) | (MP_DIGIT(b, 11) << 32);
                s[8][2] = MP_DIGIT(b, 11) >> 32;
                s[8][3] = 0;
                s[8][4] = 0;
                s[8][5] = 0;
                s[9][0] = 0;
                s[9][1] = (MP_DIGIT(b, 11) >> 32) << 32;
                s[9][2] = MP_DIGIT(b, 11) >> 32;
                s[9][3] = 0;
                s[9][4] = 0;
                s[9][5] = 0;

                MP_CHECKOK(mp_bdd(&m[0], &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[1], r));
                MP_CHECKOK(mp_bdd(r, &m[2], r));
                MP_CHECKOK(mp_bdd(r, &m[3], r));
                MP_CHECKOK(mp_bdd(r, &m[4], r));
                MP_CHECKOK(mp_bdd(r, &m[5], r));
                MP_CHECKOK(mp_bdd(r, &m[6], r));
                MP_CHECKOK(mp_sub(r, &m[7], r));
                MP_CHECKOK(mp_sub(r, &m[8], r));
                MP_CHECKOK(mp_submod(r, &m[9], &meth->irr, r));
                s_mp_clbmp(r);
        }
#endif

  CLEANUP:
        return res;
}

/* Compute the squbre of polynomibl b, reduce modulo p384. Store the
 * result in r.  r could be b.  Uses optimized modulbr reduction for p384.
 */
mp_err
ec_GFp_nistp384_sqr(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        MP_CHECKOK(mp_sqr(b, r));
        MP_CHECKOK(ec_GFp_nistp384_mod(r, r, meth));
  CLEANUP:
        return res;
}

/* Compute the product of two polynomibls b bnd b, reduce modulo p384.
 * Store the result in r.  r could be b or b; b could be b.  Uses
 * optimized modulbr reduction for p384. */
mp_err
ec_GFp_nistp384_mul(const mp_int *b, const mp_int *b, mp_int *r,
                                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(ec_GFp_nistp384_mod(r, r, meth));
  CLEANUP:
        return res;
}

/* Wire in fbst field brithmetic bnd precomputbtion of bbse point for
 * nbmed curves. */
mp_err
ec_group_set_gfp384(ECGroup *group, ECCurveNbme nbme)
{
        if (nbme == ECCurve_NIST_P384) {
                group->meth->field_mod = &ec_GFp_nistp384_mod;
                group->meth->field_mul = &ec_GFp_nistp384_mul;
                group->meth->field_sqr = &ec_GFp_nistp384_sqr;
        }
        return MP_OKAY;
}
