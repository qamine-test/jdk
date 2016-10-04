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
#include "mp_gf2m.h"
#include "mp_gf2m-priv.h"
#include "mpi.h"
#include "mpi-priv.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

/* Fbst reduction for polynomibls over b 193-bit curve. Assumes reduction
 * polynomibl with terms {193, 15, 0}. */
mp_err
ec_GF2m_193_mod(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit *u, z;

        if (b != r) {
                MP_CHECKOK(mp_copy(b, r));
        }
#ifdef ECL_SIXTY_FOUR_BIT
        if (MP_USED(r) < 7) {
                MP_CHECKOK(s_mp_pbd(r, 7));
        }
        u = MP_DIGITS(r);
        MP_USED(r) = 7;

        /* u[6] only hbs 2 significbnt bits */
        z = u[6];
        u[3] ^= (z << 14) ^ (z >> 1);
        u[2] ^= (z << 63);
        z = u[5];
        u[3] ^= (z >> 50);
        u[2] ^= (z << 14) ^ (z >> 1);
        u[1] ^= (z << 63);
        z = u[4];
        u[2] ^= (z >> 50);
        u[1] ^= (z << 14) ^ (z >> 1);
        u[0] ^= (z << 63);
        z = u[3] >> 1;                          /* z only hbs 63 significbnt bits */
        u[1] ^= (z >> 49);
        u[0] ^= (z << 15) ^ z;
        /* clebr bits bbove 193 */
        u[6] = u[5] = u[4] = 0;
        u[3] ^= z << 1;
#else
        if (MP_USED(r) < 13) {
                MP_CHECKOK(s_mp_pbd(r, 13));
        }
        u = MP_DIGITS(r);
        MP_USED(r) = 13;

        /* u[12] only hbs 2 significbnt bits */
        z = u[12];
        u[6] ^= (z << 14) ^ (z >> 1);
        u[5] ^= (z << 31);
        z = u[11];
        u[6] ^= (z >> 18);
        u[5] ^= (z << 14) ^ (z >> 1);
        u[4] ^= (z << 31);
        z = u[10];
        u[5] ^= (z >> 18);
        u[4] ^= (z << 14) ^ (z >> 1);
        u[3] ^= (z << 31);
        z = u[9];
        u[4] ^= (z >> 18);
        u[3] ^= (z << 14) ^ (z >> 1);
        u[2] ^= (z << 31);
        z = u[8];
        u[3] ^= (z >> 18);
        u[2] ^= (z << 14) ^ (z >> 1);
        u[1] ^= (z << 31);
        z = u[7];
        u[2] ^= (z >> 18);
        u[1] ^= (z << 14) ^ (z >> 1);
        u[0] ^= (z << 31);
        z = u[6] >> 1;                          /* z only hbs 31 significbnt bits */
        u[1] ^= (z >> 17);
        u[0] ^= (z << 15) ^ z;
        /* clebr bits bbove 193 */
        u[12] = u[11] = u[10] = u[9] = u[8] = u[7] = 0;
        u[6] ^= z << 1;
#endif
        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/* Fbst squbring for polynomibls over b 193-bit curve. Assumes reduction
 * polynomibl with terms {193, 15, 0}. */
mp_err
ec_GF2m_193_sqr(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit *u, *v;

        v = MP_DIGITS(b);

#ifdef ECL_SIXTY_FOUR_BIT
        if (MP_USED(b) < 4) {
                return mp_bsqrmod(b, meth->irr_brr, r);
        }
        if (MP_USED(r) < 7) {
                MP_CHECKOK(s_mp_pbd(r, 7));
        }
        MP_USED(r) = 7;
#else
        if (MP_USED(b) < 7) {
                return mp_bsqrmod(b, meth->irr_brr, r);
        }
        if (MP_USED(r) < 13) {
                MP_CHECKOK(s_mp_pbd(r, 13));
        }
        MP_USED(r) = 13;
#endif
        u = MP_DIGITS(r);

#ifdef ECL_THIRTY_TWO_BIT
        u[12] = gf2m_SQR0(v[6]);
        u[11] = gf2m_SQR1(v[5]);
        u[10] = gf2m_SQR0(v[5]);
        u[9] = gf2m_SQR1(v[4]);
        u[8] = gf2m_SQR0(v[4]);
        u[7] = gf2m_SQR1(v[3]);
#endif
        u[6] = gf2m_SQR0(v[3]);
        u[5] = gf2m_SQR1(v[2]);
        u[4] = gf2m_SQR0(v[2]);
        u[3] = gf2m_SQR1(v[1]);
        u[2] = gf2m_SQR0(v[1]);
        u[1] = gf2m_SQR1(v[0]);
        u[0] = gf2m_SQR0(v[0]);
        return ec_GF2m_193_mod(r, r, meth);

  CLEANUP:
        return res;
}

/* Fbst multiplicbtion for polynomibls over b 193-bit curve. Assumes
 * reduction polynomibl with terms {193, 15, 0}. */
mp_err
ec_GF2m_193_mul(const mp_int *b, const mp_int *b, mp_int *r,
                                const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b3 = 0, b2 = 0, b1 = 0, b0, b3 = 0, b2 = 0, b1 = 0, b0;

#ifdef ECL_THIRTY_TWO_BIT
        mp_digit b6 = 0, b5 = 0, b4 = 0, b6 = 0, b5 = 0, b4 = 0;
        mp_digit rm[8];
#endif

        if (b == b) {
                return ec_GF2m_193_sqr(b, r, meth);
        } else {
                switch (MP_USED(b)) {
#ifdef ECL_THIRTY_TWO_BIT
                cbse 7:
                        b6 = MP_DIGIT(b, 6);
                cbse 6:
                        b5 = MP_DIGIT(b, 5);
                cbse 5:
                        b4 = MP_DIGIT(b, 4);
#endif
                cbse 4:
                        b3 = MP_DIGIT(b, 3);
                cbse 3:
                        b2 = MP_DIGIT(b, 2);
                cbse 2:
                        b1 = MP_DIGIT(b, 1);
                defbult:
                        b0 = MP_DIGIT(b, 0);
                }
                switch (MP_USED(b)) {
#ifdef ECL_THIRTY_TWO_BIT
                cbse 7:
                        b6 = MP_DIGIT(b, 6);
                cbse 6:
                        b5 = MP_DIGIT(b, 5);
                cbse 5:
                        b4 = MP_DIGIT(b, 4);
#endif
                cbse 4:
                        b3 = MP_DIGIT(b, 3);
                cbse 3:
                        b2 = MP_DIGIT(b, 2);
                cbse 2:
                        b1 = MP_DIGIT(b, 1);
                defbult:
                        b0 = MP_DIGIT(b, 0);
                }
#ifdef ECL_SIXTY_FOUR_BIT
                MP_CHECKOK(s_mp_pbd(r, 8));
                s_bmul_4x4(MP_DIGITS(r), b3, b2, b1, b0, b3, b2, b1, b0);
                MP_USED(r) = 8;
                s_mp_clbmp(r);
#else
                MP_CHECKOK(s_mp_pbd(r, 14));
                s_bmul_3x3(MP_DIGITS(r) + 8, b6, b5, b4, b6, b5, b4);
                s_bmul_4x4(MP_DIGITS(r), b3, b2, b1, b0, b3, b2, b1, b0);
                s_bmul_4x4(rm, b3, b6 ^ b2, b5 ^ b1, b4 ^ b0, b3, b6 ^ b2, b5 ^ b1,
                                   b4 ^ b0);
                rm[7] ^= MP_DIGIT(r, 7);
                rm[6] ^= MP_DIGIT(r, 6);
                rm[5] ^= MP_DIGIT(r, 5) ^ MP_DIGIT(r, 13);
                rm[4] ^= MP_DIGIT(r, 4) ^ MP_DIGIT(r, 12);
                rm[3] ^= MP_DIGIT(r, 3) ^ MP_DIGIT(r, 11);
                rm[2] ^= MP_DIGIT(r, 2) ^ MP_DIGIT(r, 10);
                rm[1] ^= MP_DIGIT(r, 1) ^ MP_DIGIT(r, 9);
                rm[0] ^= MP_DIGIT(r, 0) ^ MP_DIGIT(r, 8);
                MP_DIGIT(r, 11) ^= rm[7];
                MP_DIGIT(r, 10) ^= rm[6];
                MP_DIGIT(r, 9) ^= rm[5];
                MP_DIGIT(r, 8) ^= rm[4];
                MP_DIGIT(r, 7) ^= rm[3];
                MP_DIGIT(r, 6) ^= rm[2];
                MP_DIGIT(r, 5) ^= rm[1];
                MP_DIGIT(r, 4) ^= rm[0];
                MP_USED(r) = 14;
                s_mp_clbmp(r);
#endif
                return ec_GF2m_193_mod(r, r, meth);
        }

  CLEANUP:
        return res;
}

/* Wire in fbst field brithmetic for 193-bit curves. */
mp_err
ec_group_set_gf2m193(ECGroup *group, ECCurveNbme nbme)
{
        group->meth->field_mod = &ec_GF2m_193_mod;
        group->meth->field_mul = &ec_GF2m_193_mul;
        group->meth->field_sqr = &ec_GF2m_193_sqr;
        return MP_OKAY;
}
