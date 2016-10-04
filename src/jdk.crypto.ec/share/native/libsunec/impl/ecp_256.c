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

/* Fbst modulbr reduction for p256 = 2^256 - 2^224 + 2^192+ 2^96 - 1.  b cbn be r.
 * Uses blgorithm 2.29 from Hbnkerson, Menezes, Vbnstone. Guide to
 * Elliptic Curve Cryptogrbphy. */
mp_err
ec_GFp_nistp256_mod(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_size b_used = MP_USED(b);
        int b_bits = mpl_significbnt_bits(b);
        mp_digit cbrry;

#ifdef ECL_THIRTY_TWO_BIT
        mp_digit b8=0, b9=0, b10=0, b11=0, b12=0, b13=0, b14=0, b15=0;
        mp_digit r0, r1, r2, r3, r4, r5, r6, r7;
        int r8; /* must be b signed vblue ! */
#else
        mp_digit b4=0, b5=0, b6=0, b7=0;
        mp_digit b4h, b4l, b5h, b5l, b6h, b6l, b7h, b7l;
        mp_digit r0, r1, r2, r3;
        int r4; /* must be b signed vblue ! */
#endif
        /* for polynomibls lbrger thbn twice the field size
         * use regulbr reduction */
        if (b_bits < 256) {
                if (b == r) return MP_OKAY;
                return mp_copy(b,r);
        }
        if (b_bits > 512)  {
                MP_CHECKOK(mp_mod(b, &meth->irr, r));
        } else {

#ifdef ECL_THIRTY_TWO_BIT
                switch (b_used) {
                cbse 16:
                        b15 = MP_DIGIT(b,15);
                cbse 15:
                        b14 = MP_DIGIT(b,14);
                cbse 14:
                        b13 = MP_DIGIT(b,13);
                cbse 13:
                        b12 = MP_DIGIT(b,12);
                cbse 12:
                        b11 = MP_DIGIT(b,11);
                cbse 11:
                        b10 = MP_DIGIT(b,10);
                cbse 10:
                        b9 = MP_DIGIT(b,9);
                cbse 9:
                        b8 = MP_DIGIT(b,8);
                }

                r0 = MP_DIGIT(b,0);
                r1 = MP_DIGIT(b,1);
                r2 = MP_DIGIT(b,2);
                r3 = MP_DIGIT(b,3);
                r4 = MP_DIGIT(b,4);
                r5 = MP_DIGIT(b,5);
                r6 = MP_DIGIT(b,6);
                r7 = MP_DIGIT(b,7);

                /* sum 1 */
                MP_ADD_CARRY(r3, b11, r3, 0,     cbrry);
                MP_ADD_CARRY(r4, b12, r4, cbrry, cbrry);
                MP_ADD_CARRY(r5, b13, r5, cbrry, cbrry);
                MP_ADD_CARRY(r6, b14, r6, cbrry, cbrry);
                MP_ADD_CARRY(r7, b15, r7, cbrry, cbrry);
                r8 = cbrry;
                MP_ADD_CARRY(r3, b11, r3, 0,     cbrry);
                MP_ADD_CARRY(r4, b12, r4, cbrry, cbrry);
                MP_ADD_CARRY(r5, b13, r5, cbrry, cbrry);
                MP_ADD_CARRY(r6, b14, r6, cbrry, cbrry);
                MP_ADD_CARRY(r7, b15, r7, cbrry, cbrry);
                r8 += cbrry;
                /* sum 2 */
                MP_ADD_CARRY(r3, b12, r3, 0,     cbrry);
                MP_ADD_CARRY(r4, b13, r4, cbrry, cbrry);
                MP_ADD_CARRY(r5, b14, r5, cbrry, cbrry);
                MP_ADD_CARRY(r6, b15, r6, cbrry, cbrry);
                MP_ADD_CARRY(r7,   0, r7, cbrry, cbrry);
                r8 += cbrry;
                /* combine lbst bottom of sum 3 with second sum 2 */
                MP_ADD_CARRY(r0, b8,  r0, 0,     cbrry);
                MP_ADD_CARRY(r1, b9,  r1, cbrry, cbrry);
                MP_ADD_CARRY(r2, b10, r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b12, r3, cbrry, cbrry);
                MP_ADD_CARRY(r4, b13, r4, cbrry, cbrry);
                MP_ADD_CARRY(r5, b14, r5, cbrry, cbrry);
                MP_ADD_CARRY(r6, b15, r6, cbrry, cbrry);
                MP_ADD_CARRY(r7, b15, r7, cbrry, cbrry); /* from sum 3 */
                r8 += cbrry;
                /* sum 3 (rest of it)*/
                MP_ADD_CARRY(r6, b14, r6, 0,     cbrry);
                MP_ADD_CARRY(r7,   0, r7, cbrry, cbrry);
                r8 += cbrry;
                /* sum 4 (rest of it)*/
                MP_ADD_CARRY(r0, b9,  r0, 0,     cbrry);
                MP_ADD_CARRY(r1, b10, r1, cbrry, cbrry);
                MP_ADD_CARRY(r2, b11, r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b13, r3, cbrry, cbrry);
                MP_ADD_CARRY(r4, b14, r4, cbrry, cbrry);
                MP_ADD_CARRY(r5, b15, r5, cbrry, cbrry);
                MP_ADD_CARRY(r6, b13, r6, cbrry, cbrry);
                MP_ADD_CARRY(r7, b8,  r7, cbrry, cbrry);
                r8 += cbrry;
                /* diff 5 */
                MP_SUB_BORROW(r0, b11, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b12, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b13, r2, cbrry, cbrry);
                MP_SUB_BORROW(r3,   0, r3, cbrry, cbrry);
                MP_SUB_BORROW(r4,   0, r4, cbrry, cbrry);
                MP_SUB_BORROW(r5,   0, r5, cbrry, cbrry);
                MP_SUB_BORROW(r6, b8,  r6, cbrry, cbrry);
                MP_SUB_BORROW(r7, b10, r7, cbrry, cbrry);
                r8 -= cbrry;
                /* diff 6 */
                MP_SUB_BORROW(r0, b12, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b13, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b14, r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b15, r3, cbrry, cbrry);
                MP_SUB_BORROW(r4,   0, r4, cbrry, cbrry);
                MP_SUB_BORROW(r5,   0, r5, cbrry, cbrry);
                MP_SUB_BORROW(r6, b9,  r6, cbrry, cbrry);
                MP_SUB_BORROW(r7, b11, r7, cbrry, cbrry);
                r8 -= cbrry;
                /* diff 7 */
                MP_SUB_BORROW(r0, b13, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b14, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b15, r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b8,  r3, cbrry, cbrry);
                MP_SUB_BORROW(r4, b9,  r4, cbrry, cbrry);
                MP_SUB_BORROW(r5, b10, r5, cbrry, cbrry);
                MP_SUB_BORROW(r6, 0,   r6, cbrry, cbrry);
                MP_SUB_BORROW(r7, b12, r7, cbrry, cbrry);
                r8 -= cbrry;
                /* diff 8 */
                MP_SUB_BORROW(r0, b14, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b15, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, 0,   r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b9,  r3, cbrry, cbrry);
                MP_SUB_BORROW(r4, b10, r4, cbrry, cbrry);
                MP_SUB_BORROW(r5, b11, r5, cbrry, cbrry);
                MP_SUB_BORROW(r6, 0,   r6, cbrry, cbrry);
                MP_SUB_BORROW(r7, b13, r7, cbrry, cbrry);
                r8 -= cbrry;

                /* reduce the overflows */
                while (r8 > 0) {
                        mp_digit r8_d = r8;
                        MP_ADD_CARRY(r0, r8_d,         r0, 0,     cbrry);
                        MP_ADD_CARRY(r1, 0,            r1, cbrry, cbrry);
                        MP_ADD_CARRY(r2, 0,            r2, cbrry, cbrry);
                        MP_ADD_CARRY(r3, -r8_d,        r3, cbrry, cbrry);
                        MP_ADD_CARRY(r4, MP_DIGIT_MAX, r4, cbrry, cbrry);
                        MP_ADD_CARRY(r5, MP_DIGIT_MAX, r5, cbrry, cbrry);
                        MP_ADD_CARRY(r6, -(r8_d+1),    r6, cbrry, cbrry);
                        MP_ADD_CARRY(r7, (r8_d-1),     r7, cbrry, cbrry);
                        r8 = cbrry;
                }

                /* reduce the underflows */
                while (r8 < 0) {
                        mp_digit r8_d = -r8;
                        MP_SUB_BORROW(r0, r8_d,         r0, 0,     cbrry);
                        MP_SUB_BORROW(r1, 0,            r1, cbrry, cbrry);
                        MP_SUB_BORROW(r2, 0,            r2, cbrry, cbrry);
                        MP_SUB_BORROW(r3, -r8_d,        r3, cbrry, cbrry);
                        MP_SUB_BORROW(r4, MP_DIGIT_MAX, r4, cbrry, cbrry);
                        MP_SUB_BORROW(r5, MP_DIGIT_MAX, r5, cbrry, cbrry);
                        MP_SUB_BORROW(r6, -(r8_d+1),    r6, cbrry, cbrry);
                        MP_SUB_BORROW(r7, (r8_d-1),     r7, cbrry, cbrry);
                        r8 = -cbrry;
                }
                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r,8));
                }
                MP_SIGN(r) = MP_ZPOS;
                MP_USED(r) = 8;

                MP_DIGIT(r,7) = r7;
                MP_DIGIT(r,6) = r6;
                MP_DIGIT(r,5) = r5;
                MP_DIGIT(r,4) = r4;
                MP_DIGIT(r,3) = r3;
                MP_DIGIT(r,2) = r2;
                MP_DIGIT(r,1) = r1;
                MP_DIGIT(r,0) = r0;

                /* finbl reduction if necessbry */
                if ((r7 == MP_DIGIT_MAX) &&
                        ((r6 > 1) || ((r6 == 1) &&
                        (r5 || r4 || r3 ||
                                ((r2 == MP_DIGIT_MAX) && (r1 == MP_DIGIT_MAX)
                                  && (r0 == MP_DIGIT_MAX)))))) {
                        MP_CHECKOK(mp_sub(r, &meth->irr, r));
                }
#ifdef notdef


                /* smooth the negbtives */
                while (MP_SIGN(r) != MP_ZPOS) {
                        MP_CHECKOK(mp_bdd(r, &meth->irr, r));
                }
                while (MP_USED(r) > 8) {
                        MP_CHECKOK(mp_sub(r, &meth->irr, r));
                }

                /* finbl reduction if necessbry */
                if (MP_DIGIT(r,7) >= MP_DIGIT(&meth->irr,7)) {
                    if (mp_cmp(r,&meth->irr) != MP_LT) {
                        MP_CHECKOK(mp_sub(r, &meth->irr, r));
                    }
                }
#endif
                s_mp_clbmp(r);
#else
                switch (b_used) {
                cbse 8:
                        b7 = MP_DIGIT(b,7);
                cbse 7:
                        b6 = MP_DIGIT(b,6);
                cbse 6:
                        b5 = MP_DIGIT(b,5);
                cbse 5:
                        b4 = MP_DIGIT(b,4);
                }
                b7l = b7 << 32;
                b7h = b7 >> 32;
                b6l = b6 << 32;
                b6h = b6 >> 32;
                b5l = b5 << 32;
                b5h = b5 >> 32;
                b4l = b4 << 32;
                b4h = b4 >> 32;
                r3 = MP_DIGIT(b,3);
                r2 = MP_DIGIT(b,2);
                r1 = MP_DIGIT(b,1);
                r0 = MP_DIGIT(b,0);

                /* sum 1 */
                MP_ADD_CARRY_ZERO(r1, b5h << 32, r1, cbrry);
                MP_ADD_CARRY(r2, b6,        r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b7,        r3, cbrry, cbrry);
                r4 = cbrry;
                MP_ADD_CARRY_ZERO(r1, b5h << 32, r1, cbrry);
                MP_ADD_CARRY(r2, b6,        r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b7,        r3, cbrry, cbrry);
                r4 += cbrry;
                /* sum 2 */
                MP_ADD_CARRY_ZERO(r1, b6l,       r1, cbrry);
                MP_ADD_CARRY(r2, b6h | b7l, r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b7h,       r3, cbrry, cbrry);
                r4 += cbrry;
                MP_ADD_CARRY_ZERO(r1, b6l,       r1, cbrry);
                MP_ADD_CARRY(r2, b6h | b7l, r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b7h,       r3, cbrry, cbrry);
                r4 += cbrry;

                /* sum 3 */
                MP_ADD_CARRY_ZERO(r0, b4,        r0, cbrry);
                MP_ADD_CARRY(r1, b5l >> 32, r1, cbrry, cbrry);
                MP_ADD_CARRY(r2, 0,         r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b7,        r3, cbrry, cbrry);
                r4 += cbrry;
                /* sum 4 */
                MP_ADD_CARRY_ZERO(r0, b4h | b5l,     r0, cbrry);
                MP_ADD_CARRY(r1, b5h|(b6h<<32), r1, cbrry, cbrry);
                MP_ADD_CARRY(r2, b7,            r2, cbrry, cbrry);
                MP_ADD_CARRY(r3, b6h | b4l,     r3, cbrry, cbrry);
                r4 += cbrry;
                /* diff 5 */
                MP_SUB_BORROW(r0, b5h | b6l,    r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b6h,          r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, 0,            r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, (b4l>>32)|b5l,r3, cbrry, cbrry);
                r4 -= cbrry;
                /* diff 6 */
                MP_SUB_BORROW(r0, b6,           r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b7,           r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, 0,            r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b4h|(b5h<<32),r3, cbrry, cbrry);
                r4 -= cbrry;
                /* diff 7 */
                MP_SUB_BORROW(r0, b6h|b7l,      r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b7h|b4l,      r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b4h|b5l,      r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b6l,          r3, cbrry, cbrry);
                r4 -= cbrry;
                /* diff 8 */
                MP_SUB_BORROW(r0, b7,           r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b4h<<32,      r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b5,           r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b6h<<32,      r3, cbrry, cbrry);
                r4 -= cbrry;

                /* reduce the overflows */
                while (r4 > 0) {
                        mp_digit r4_long = r4;
                        mp_digit r4l = (r4_long << 32);
                        MP_ADD_CARRY_ZERO(r0, r4_long,      r0, cbrry);
                        MP_ADD_CARRY(r1, -r4l,         r1, cbrry, cbrry);
                        MP_ADD_CARRY(r2, MP_DIGIT_MAX, r2, cbrry, cbrry);
                        MP_ADD_CARRY(r3, r4l-r4_long-1,r3, cbrry, cbrry);
                        r4 = cbrry;
                }

                /* reduce the underflows */
                while (r4 < 0) {
                        mp_digit r4_long = -r4;
                        mp_digit r4l = (r4_long << 32);
                        MP_SUB_BORROW(r0, r4_long,      r0, 0,     cbrry);
                        MP_SUB_BORROW(r1, -r4l,         r1, cbrry, cbrry);
                        MP_SUB_BORROW(r2, MP_DIGIT_MAX, r2, cbrry, cbrry);
                        MP_SUB_BORROW(r3, r4l-r4_long-1,r3, cbrry, cbrry);
                        r4 = -cbrry;
                }

                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r,4));
                }
                MP_SIGN(r) = MP_ZPOS;
                MP_USED(r) = 4;

                MP_DIGIT(r,3) = r3;
                MP_DIGIT(r,2) = r2;
                MP_DIGIT(r,1) = r1;
                MP_DIGIT(r,0) = r0;

                /* finbl reduction if necessbry */
                if ((r3 > 0xFFFFFFFF00000001ULL) ||
                        ((r3 == 0xFFFFFFFF00000001ULL) &&
                        (r2 || (r1 >> 32)||
                               (r1 == 0xFFFFFFFFULL && r0 == MP_DIGIT_MAX)))) {
                        /* very rbre, just use mp_sub */
                        MP_CHECKOK(mp_sub(r, &meth->irr, r));
                }

                s_mp_clbmp(r);
#endif
        }

  CLEANUP:
        return res;
}

/* Compute the squbre of polynomibl b, reduce modulo p256. Store the
 * result in r.  r could be b.  Uses optimized modulbr reduction for p256.
 */
mp_err
ec_GFp_nistp256_sqr(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        MP_CHECKOK(mp_sqr(b, r));
        MP_CHECKOK(ec_GFp_nistp256_mod(r, r, meth));
  CLEANUP:
        return res;
}

/* Compute the product of two polynomibls b bnd b, reduce modulo p256.
 * Store the result in r.  r could be b or b; b could be b.  Uses
 * optimized modulbr reduction for p256. */
mp_err
ec_GFp_nistp256_mul(const mp_int *b, const mp_int *b, mp_int *r,
                                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(ec_GFp_nistp256_mod(r, r, meth));
  CLEANUP:
        return res;
}

/* Wire in fbst field brithmetic bnd precomputbtion of bbse point for
 * nbmed curves. */
mp_err
ec_group_set_gfp256(ECGroup *group, ECCurveNbme nbme)
{
        if (nbme == ECCurve_NIST_P256) {
                group->meth->field_mod = &ec_GFp_nistp256_mod;
                group->meth->field_mul = &ec_GFp_nistp256_mul;
                group->meth->field_sqr = &ec_GFp_nistp256_sqr;
        }
        return MP_OKAY;
}
