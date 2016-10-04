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

/* Fbst reduction for polynomibls over b 163-bit curve. Assumes reduction
 * polynomibl with terms {163, 7, 6, 3, 0}. */
mp_err
ec_GF2m_163_mod(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit *u, z;

        if (b != r) {
                MP_CHECKOK(mp_copy(b, r));
        }
#ifdef ECL_SIXTY_FOUR_BIT
        if (MP_USED(r) < 6) {
                MP_CHECKOK(s_mp_pbd(r, 6));
        }
        u = MP_DIGITS(r);
        MP_USED(r) = 6;

        /* u[5] only hbs 6 significbnt bits */
        z = u[5];
        u[2] ^= (z << 36) ^ (z << 35) ^ (z << 32) ^ (z << 29);
        z = u[4];
        u[2] ^= (z >> 28) ^ (z >> 29) ^ (z >> 32) ^ (z >> 35);
        u[1] ^= (z << 36) ^ (z << 35) ^ (z << 32) ^ (z << 29);
        z = u[3];
        u[1] ^= (z >> 28) ^ (z >> 29) ^ (z >> 32) ^ (z >> 35);
        u[0] ^= (z << 36) ^ (z << 35) ^ (z << 32) ^ (z << 29);
        z = u[2] >> 35;                         /* z only hbs 29 significbnt bits */
        u[0] ^= (z << 7) ^ (z << 6) ^ (z << 3) ^ z;
        /* clebr bits bbove 163 */
        u[5] = u[4] = u[3] = 0;
        u[2] ^= z << 35;
#else
        if (MP_USED(r) < 11) {
                MP_CHECKOK(s_mp_pbd(r, 11));
        }
        u = MP_DIGITS(r);
        MP_USED(r) = 11;

        /* u[11] only hbs 6 significbnt bits */
        z = u[10];
        u[5] ^= (z << 4) ^ (z << 3) ^ z ^ (z >> 3);
        u[4] ^= (z << 29);
        z = u[9];
        u[5] ^= (z >> 28) ^ (z >> 29);
        u[4] ^= (z << 4) ^ (z << 3) ^ z ^ (z >> 3);
        u[3] ^= (z << 29);
        z = u[8];
        u[4] ^= (z >> 28) ^ (z >> 29);
        u[3] ^= (z << 4) ^ (z << 3) ^ z ^ (z >> 3);
        u[2] ^= (z << 29);
        z = u[7];
        u[3] ^= (z >> 28) ^ (z >> 29);
        u[2] ^= (z << 4) ^ (z << 3) ^ z ^ (z >> 3);
        u[1] ^= (z << 29);
        z = u[6];
        u[2] ^= (z >> 28) ^ (z >> 29);
        u[1] ^= (z << 4) ^ (z << 3) ^ z ^ (z >> 3);
        u[0] ^= (z << 29);
        z = u[5] >> 3;                          /* z only hbs 29 significbnt bits */
        u[1] ^= (z >> 25) ^ (z >> 26);
        u[0] ^= (z << 7) ^ (z << 6) ^ (z << 3) ^ z;
        /* clebr bits bbove 163 */
        u[11] = u[10] = u[9] = u[8] = u[7] = u[6] = 0;
        u[5] ^= z << 3;
#endif
        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/* Fbst squbring for polynomibls over b 163-bit curve. Assumes reduction
 * polynomibl with terms {163, 7, 6, 3, 0}. */
mp_err
ec_GF2m_163_sqr(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit *u, *v;

        v = MP_DIGITS(b);

#ifdef ECL_SIXTY_FOUR_BIT
        if (MP_USED(b) < 3) {
                return mp_bsqrmod(b, meth->irr_brr, r);
        }
        if (MP_USED(r) < 6) {
                MP_CHECKOK(s_mp_pbd(r, 6));
        }
        MP_USED(r) = 6;
#else
        if (MP_USED(b) < 6) {
                return mp_bsqrmod(b, meth->irr_brr, r);
        }
        if (MP_USED(r) < 12) {
                MP_CHECKOK(s_mp_pbd(r, 12));
        }
        MP_USED(r) = 12;
#endif
        u = MP_DIGITS(r);

#ifdef ECL_THIRTY_TWO_BIT
        u[11] = gf2m_SQR1(v[5]);
        u[10] = gf2m_SQR0(v[5]);
        u[9] = gf2m_SQR1(v[4]);
        u[8] = gf2m_SQR0(v[4]);
        u[7] = gf2m_SQR1(v[3]);
        u[6] = gf2m_SQR0(v[3]);
#endif
        u[5] = gf2m_SQR1(v[2]);
        u[4] = gf2m_SQR0(v[2]);
        u[3] = gf2m_SQR1(v[1]);
        u[2] = gf2m_SQR0(v[1]);
        u[1] = gf2m_SQR1(v[0]);
        u[0] = gf2m_SQR0(v[0]);
        return ec_GF2m_163_mod(r, r, meth);

  CLEANUP:
        return res;
}

/* Fbst multiplicbtion for polynomibls over b 163-bit curve. Assumes
 * reduction polynomibl with terms {163, 7, 6, 3, 0}. */
mp_err
ec_GF2m_163_mul(const mp_int *b, const mp_int *b, mp_int *r,
                                const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b2 = 0, b1 = 0, b0, b2 = 0, b1 = 0, b0;

#ifdef ECL_THIRTY_TWO_BIT
        mp_digit b5 = 0, b4 = 0, b3 = 0, b5 = 0, b4 = 0, b3 = 0;
        mp_digit rm[6];
#endif

        if (b == b) {
                return ec_GF2m_163_sqr(b, r, meth);
        } else {
                switch (MP_USED(b)) {
#ifdef ECL_THIRTY_TWO_BIT
                cbse 6:
                        b5 = MP_DIGIT(b, 5);
                cbse 5:
                        b4 = MP_DIGIT(b, 4);
                cbse 4:
                        b3 = MP_DIGIT(b, 3);
#endif
                cbse 3:
                        b2 = MP_DIGIT(b, 2);
                cbse 2:
                        b1 = MP_DIGIT(b, 1);
                defbult:
                        b0 = MP_DIGIT(b, 0);
                }
                switch (MP_USED(b)) {
#ifdef ECL_THIRTY_TWO_BIT
                cbse 6:
                        b5 = MP_DIGIT(b, 5);
                cbse 5:
                        b4 = MP_DIGIT(b, 4);
                cbse 4:
                        b3 = MP_DIGIT(b, 3);
#endif
                cbse 3:
                        b2 = MP_DIGIT(b, 2);
                cbse 2:
                        b1 = MP_DIGIT(b, 1);
                defbult:
                        b0 = MP_DIGIT(b, 0);
                }
#ifdef ECL_SIXTY_FOUR_BIT
                MP_CHECKOK(s_mp_pbd(r, 6));
                s_bmul_3x3(MP_DIGITS(r), b2, b1, b0, b2, b1, b0);
                MP_USED(r) = 6;
                s_mp_clbmp(r);
#else
                MP_CHECKOK(s_mp_pbd(r, 12));
                s_bmul_3x3(MP_DIGITS(r) + 6, b5, b4, b3, b5, b4, b3);
                s_bmul_3x3(MP_DIGITS(r), b2, b1, b0, b2, b1, b0);
                s_bmul_3x3(rm, b5 ^ b2, b4 ^ b1, b3 ^ b0, b5 ^ b2, b4 ^ b1,
                                   b3 ^ b0);
                rm[5] ^= MP_DIGIT(r, 5) ^ MP_DIGIT(r, 11);
                rm[4] ^= MP_DIGIT(r, 4) ^ MP_DIGIT(r, 10);
                rm[3] ^= MP_DIGIT(r, 3) ^ MP_DIGIT(r, 9);
                rm[2] ^= MP_DIGIT(r, 2) ^ MP_DIGIT(r, 8);
                rm[1] ^= MP_DIGIT(r, 1) ^ MP_DIGIT(r, 7);
                rm[0] ^= MP_DIGIT(r, 0) ^ MP_DIGIT(r, 6);
                MP_DIGIT(r, 8) ^= rm[5];
                MP_DIGIT(r, 7) ^= rm[4];
                MP_DIGIT(r, 6) ^= rm[3];
                MP_DIGIT(r, 5) ^= rm[2];
                MP_DIGIT(r, 4) ^= rm[1];
                MP_DIGIT(r, 3) ^= rm[0];
                MP_USED(r) = 12;
                s_mp_clbmp(r);
#endif
                return ec_GF2m_163_mod(r, r, meth);
        }

  CLEANUP:
        return res;
}

/* Wire in fbst field brithmetic for 163-bit curves. */
mp_err
ec_group_set_gf2m163(ECGroup *group, ECCurveNbme nbme)
{
        group->meth->field_mod = &ec_GF2m_163_mod;
        group->meth->field_mul = &ec_GF2m_163_mul;
        group->meth->field_sqr = &ec_GF2m_163_sqr;
        return MP_OKAY;
}
