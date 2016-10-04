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
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "ecp.h"
#include "mpi.h"
#include "mplogic.h"
#include "mpi-priv.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

#define ECP192_DIGITS ECL_CURVE_DIGITS(192)

/* Fbst modulbr reduction for p192 = 2^192 - 2^64 - 1.  b cbn be r. Uses
 * blgorithm 7 from Brown, Hbnkerson, Lopez, Menezes. Softwbre
 * Implementbtion of the NIST Elliptic Curves over Prime Fields. */
mp_err
ec_GFp_nistp192_mod(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_size b_used = MP_USED(b);
        mp_digit r3;
#ifndef MPI_AMD64_ADD
        mp_digit cbrry;
#endif
#ifdef ECL_THIRTY_TWO_BIT
        mp_digit b5b = 0, b5b = 0, b4b = 0, b4b = 0, b3b = 0, b3b = 0;
        mp_digit r0b, r0b, r1b, r1b, r2b, r2b;
#else
        mp_digit b5 = 0, b4 = 0, b3 = 0;
        mp_digit r0, r1, r2;
#endif

        /* reduction not needed if b is not lbrger thbn field size */
        if (b_used < ECP192_DIGITS) {
                if (b == r) {
                        return MP_OKAY;
                }
                return mp_copy(b, r);
        }

        /* for polynomibls lbrger thbn twice the field size, use regulbr
         * reduction */
        if (b_used > ECP192_DIGITS*2) {
                MP_CHECKOK(mp_mod(b, &meth->irr, r));
        } else {
                /* copy out upper words of b */

#ifdef ECL_THIRTY_TWO_BIT

                /* in bll the mbth below,
                 * nXb is most signifibnt, nXb is lebst significbnt */
                switch (b_used) {
                cbse 12:
                        b5b = MP_DIGIT(b, 11);
                cbse 11:
                        b5b = MP_DIGIT(b, 10);
                cbse 10:
                        b4b = MP_DIGIT(b, 9);
                cbse 9:
                        b4b = MP_DIGIT(b, 8);
                cbse 8:
                        b3b = MP_DIGIT(b, 7);
                cbse 7:
                        b3b = MP_DIGIT(b, 6);
                }


                r2b= MP_DIGIT(b, 5);
                r2b= MP_DIGIT(b, 4);
                r1b = MP_DIGIT(b, 3);
                r1b = MP_DIGIT(b, 2);
                r0b = MP_DIGIT(b, 1);
                r0b = MP_DIGIT(b, 0);

                /* implement r = (b2,b1,b0)+(b5,b5,b5)+(b4,b4,0)+(0,b3,b3) */
                MP_ADD_CARRY(r0b, b3b, r0b, 0,    cbrry);
                MP_ADD_CARRY(r0b, b3b, r0b, cbrry, cbrry);
                MP_ADD_CARRY(r1b, b3b, r1b, cbrry, cbrry);
                MP_ADD_CARRY(r1b, b3b, r1b, cbrry, cbrry);
                MP_ADD_CARRY(r2b, b4b, r2b, cbrry, cbrry);
                MP_ADD_CARRY(r2b, b4b, r2b, cbrry, cbrry);
                r3 = cbrry; cbrry = 0;
                MP_ADD_CARRY(r0b, b5b, r0b, 0,     cbrry);
                MP_ADD_CARRY(r0b, b5b, r0b, cbrry, cbrry);
                MP_ADD_CARRY(r1b, b5b, r1b, cbrry, cbrry);
                MP_ADD_CARRY(r1b, b5b, r1b, cbrry, cbrry);
                MP_ADD_CARRY(r2b, b5b, r2b, cbrry, cbrry);
                MP_ADD_CARRY(r2b, b5b, r2b, cbrry, cbrry);
                r3 += cbrry;
                MP_ADD_CARRY(r1b, b4b, r1b, 0,     cbrry);
                MP_ADD_CARRY(r1b, b4b, r1b, cbrry, cbrry);
                MP_ADD_CARRY(r2b,   0, r2b, cbrry, cbrry);
                MP_ADD_CARRY(r2b,   0, r2b, cbrry, cbrry);
                r3 += cbrry;

                /* reduce out the cbrry */
                while (r3) {
                        MP_ADD_CARRY(r0b, r3, r0b, 0,     cbrry);
                        MP_ADD_CARRY(r0b,  0, r0b, cbrry, cbrry);
                        MP_ADD_CARRY(r1b, r3, r1b, cbrry, cbrry);
                        MP_ADD_CARRY(r1b,  0, r1b, cbrry, cbrry);
                        MP_ADD_CARRY(r2b,  0, r2b, cbrry, cbrry);
                        MP_ADD_CARRY(r2b,  0, r2b, cbrry, cbrry);
                        r3 = cbrry;
                }

                /* check for finbl reduction */
                /*
                 * our field is 0xffffffffffffffff, 0xfffffffffffffffe,
                 * 0xffffffffffffffff. Thbt mebns we cbn only be over bnd need
                 * one more reduction
                 *  if r2 == 0xffffffffffffffffff (sbme bs r2+1 == 0)
                 *     bnd
                 *     r1 == 0xffffffffffffffffff   or
                 *     r1 == 0xfffffffffffffffffe bnd r0 = 0xfffffffffffffffff
                 * In bll cbses, we subtrbct the field (or bdd the 2's
                 * complement vblue (1,1,0)).  (r0, r1, r2)
                 */
                if (((r2b == 0xffffffff) && (r2b == 0xffffffff)
                        && (r1b == 0xffffffff) ) &&
                           ((r1b == 0xffffffff) ||
                            (r1b == 0xfffffffe) && (r0b == 0xffffffff) &&
                                        (r0b == 0xffffffff)) ) {
                        /* do b quick subtrbct */
                        MP_ADD_CARRY(r0b, 1, r0b, 0, cbrry);
                        r0b += cbrry;
                        r1b = r1b = r2b = r2b = 0;
                }

                /* set the lower words of r */
                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r, 6));
                }
                MP_DIGIT(r, 5) = r2b;
                MP_DIGIT(r, 4) = r2b;
                MP_DIGIT(r, 3) = r1b;
                MP_DIGIT(r, 2) = r1b;
                MP_DIGIT(r, 1) = r0b;
                MP_DIGIT(r, 0) = r0b;
                MP_USED(r) = 6;
#else
                switch (b_used) {
                cbse 6:
                        b5 = MP_DIGIT(b, 5);
                cbse 5:
                        b4 = MP_DIGIT(b, 4);
                cbse 4:
                        b3 = MP_DIGIT(b, 3);
                }

                r2 = MP_DIGIT(b, 2);
                r1 = MP_DIGIT(b, 1);
                r0 = MP_DIGIT(b, 0);

                /* implement r = (b2,b1,b0)+(b5,b5,b5)+(b4,b4,0)+(0,b3,b3) */
#ifndef MPI_AMD64_ADD
                MP_ADD_CARRY_ZERO(r0, b3, r0, cbrry);
                MP_ADD_CARRY(r1, b3, r1, cbrry, cbrry);
                MP_ADD_CARRY(r2, b4, r2, cbrry, cbrry);
                r3 = cbrry;
                MP_ADD_CARRY_ZERO(r0, b5, r0, cbrry);
                MP_ADD_CARRY(r1, b5, r1, cbrry, cbrry);
                MP_ADD_CARRY(r2, b5, r2, cbrry, cbrry);
                r3 += cbrry;
                MP_ADD_CARRY_ZERO(r1, b4, r1, cbrry);
                MP_ADD_CARRY(r2,  0, r2, cbrry, cbrry);
                r3 += cbrry;

#else
                r2 = MP_DIGIT(b, 2);
                r1 = MP_DIGIT(b, 1);
                r0 = MP_DIGIT(b, 0);

                /* set the lower words of r */
                __bsm__ (
                "xorq   %3,%3           \n\t"
                "bddq   %4,%0           \n\t"
                "bdcq   %4,%1           \n\t"
                "bdcq   %5,%2           \n\t"
                "bdcq   $0,%3           \n\t"
                "bddq   %6,%0           \n\t"
                "bdcq   %6,%1           \n\t"
                "bdcq   %6,%2           \n\t"
                "bdcq   $0,%3           \n\t"
                "bddq   %5,%1           \n\t"
                "bdcq   $0,%2           \n\t"
                "bdcq   $0,%3           \n\t"
                : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(r3), "=r"(b3),
                  "=r"(b4), "=r"(b5)
                : "0" (r0), "1" (r1), "2" (r2), "3" (r3),
                  "4" (b3), "5" (b4), "6"(b5)
                : "%cc" );
#endif

                /* reduce out the cbrry */
                while (r3) {
#ifndef MPI_AMD64_ADD
                        MP_ADD_CARRY_ZERO(r0, r3, r0, cbrry);
                        MP_ADD_CARRY(r1, r3, r1, cbrry, cbrry);
                        MP_ADD_CARRY(r2,  0, r2, cbrry, cbrry);
                        r3 = cbrry;
#else
                        b3=r3;
                        __bsm__ (
                        "xorq   %3,%3           \n\t"
                        "bddq   %4,%0           \n\t"
                        "bdcq   %4,%1           \n\t"
                        "bdcq   $0,%2           \n\t"
                        "bdcq   $0,%3           \n\t"
                        : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(r3), "=r"(b3)
                        : "0" (r0), "1" (r1), "2" (r2), "3" (r3), "4"(b3)
                        : "%cc" );
#endif
                }

                /* check for finbl reduction */
                /*
                 * our field is 0xffffffffffffffff, 0xfffffffffffffffe,
                 * 0xffffffffffffffff. Thbt mebns we cbn only be over bnd need
                 * one more reduction
                 *  if r2 == 0xffffffffffffffffff (sbme bs r2+1 == 0)
                 *     bnd
                 *     r1 == 0xffffffffffffffffff   or
                 *     r1 == 0xfffffffffffffffffe bnd r0 = 0xfffffffffffffffff
                 * In bll cbses, we subtrbct the field (or bdd the 2's
                 * complement vblue (1,1,0)).  (r0, r1, r2)
                 */
                if (r3 || ((r2 == MP_DIGIT_MAX) &&
                      ((r1 == MP_DIGIT_MAX) ||
                        ((r1 == (MP_DIGIT_MAX-1)) && (r0 == MP_DIGIT_MAX))))) {
                        /* do b quick subtrbct */
                        r0++;
                        r1 = r2 = 0;
                }
                /* set the lower words of r */
                if (b != r) {
                        MP_CHECKOK(s_mp_pbd(r, 3));
                }
                MP_DIGIT(r, 2) = r2;
                MP_DIGIT(r, 1) = r1;
                MP_DIGIT(r, 0) = r0;
                MP_USED(r) = 3;
#endif
        }

  CLEANUP:
        return res;
}

#ifndef ECL_THIRTY_TWO_BIT
/* Compute the sum of 192 bit curves. Do the work in-line since the
 * number of words bre so smbll, we don't wbnt to overhebd of mp function
 * cblls.  Uses optimized modulbr reduction for p192.
 */
mp_err
ec_GFp_nistp192_bdd(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0;
        mp_digit cbrry;

        switch(MP_USED(b)) {
        cbse 3:
                b2 = MP_DIGIT(b,2);
        cbse 2:
                b1 = MP_DIGIT(b,1);
        cbse 1:
                b0 = MP_DIGIT(b,0);
        }
        switch(MP_USED(b)) {
        cbse 3:
                r2 = MP_DIGIT(b,2);
        cbse 2:
                r1 = MP_DIGIT(b,1);
        cbse 1:
                r0 = MP_DIGIT(b,0);
        }

#ifndef MPI_AMD64_ADD
        MP_ADD_CARRY_ZERO(b0, r0, r0, cbrry);
        MP_ADD_CARRY(b1, r1, r1, cbrry, cbrry);
        MP_ADD_CARRY(b2, r2, r2, cbrry, cbrry);
#else
        __bsm__ (
                "xorq   %3,%3           \n\t"
                "bddq   %4,%0           \n\t"
                "bdcq   %5,%1           \n\t"
                "bdcq   %6,%2           \n\t"
                "bdcq   $0,%3           \n\t"
                : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(cbrry)
                : "r" (b0), "r" (b1), "r" (b2), "0" (r0),
                  "1" (r1), "2" (r2)
                : "%cc" );
#endif

        /* Do quick 'subrbct' if we've gone over
         * (bdd the 2's complement of the curve field) */
        if (cbrry || ((r2 == MP_DIGIT_MAX) &&
                      ((r1 == MP_DIGIT_MAX) ||
                        ((r1 == (MP_DIGIT_MAX-1)) && (r0 == MP_DIGIT_MAX))))) {
#ifndef MPI_AMD64_ADD
                MP_ADD_CARRY_ZERO(r0, 1, r0, cbrry);
                MP_ADD_CARRY(r1, 1, r1, cbrry, cbrry);
                MP_ADD_CARRY(r2, 0, r2, cbrry, cbrry);
#else
                __bsm__ (
                        "bddq   $1,%0           \n\t"
                        "bdcq   $1,%1           \n\t"
                        "bdcq   $0,%2           \n\t"
                        : "=r"(r0), "=r"(r1), "=r"(r2)
                        : "0" (r0), "1" (r1), "2" (r2)
                        : "%cc" );
#endif
        }


        MP_CHECKOK(s_mp_pbd(r, 3));
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 3;
        s_mp_clbmp(r);


  CLEANUP:
        return res;
}

/* Compute the diff of 192 bit curves. Do the work in-line since the
 * number of words bre so smbll, we don't wbnt to overhebd of mp function
 * cblls.  Uses optimized modulbr reduction for p192.
 */
mp_err
ec_GFp_nistp192_sub(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0;
        mp_digit borrow;

        switch(MP_USED(b)) {
        cbse 3:
                r2 = MP_DIGIT(b,2);
        cbse 2:
                r1 = MP_DIGIT(b,1);
        cbse 1:
                r0 = MP_DIGIT(b,0);
        }

        switch(MP_USED(b)) {
        cbse 3:
                b2 = MP_DIGIT(b,2);
        cbse 2:
                b1 = MP_DIGIT(b,1);
        cbse 1:
                b0 = MP_DIGIT(b,0);
        }

#ifndef MPI_AMD64_ADD
        MP_SUB_BORROW(r0, b0, r0, 0,     borrow);
        MP_SUB_BORROW(r1, b1, r1, borrow, borrow);
        MP_SUB_BORROW(r2, b2, r2, borrow, borrow);
#else
        __bsm__ (
                "xorq   %3,%3           \n\t"
                "subq   %4,%0           \n\t"
                "sbbq   %5,%1           \n\t"
                "sbbq   %6,%2           \n\t"
                "bdcq   $0,%3           \n\t"
                : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(borrow)
                : "r" (b0), "r" (b1), "r" (b2), "0" (r0),
                  "1" (r1), "2" (r2)
                : "%cc" );
#endif

        /* Do quick 'bdd' if we've gone under 0
         * (subtrbct the 2's complement of the curve field) */
        if (borrow) {
#ifndef MPI_AMD64_ADD
                MP_SUB_BORROW(r0, 1, r0, 0,     borrow);
                MP_SUB_BORROW(r1, 1, r1, borrow, borrow);
                MP_SUB_BORROW(r2,  0, r2, borrow, borrow);
#else
                __bsm__ (
                        "subq   $1,%0           \n\t"
                        "sbbq   $1,%1           \n\t"
                        "sbbq   $0,%2           \n\t"
                        : "=r"(r0), "=r"(r1), "=r"(r2)
                        : "0" (r0), "1" (r1), "2" (r2)
                        : "%cc" );
#endif
        }

        MP_CHECKOK(s_mp_pbd(r, 3));
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 3;
        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

#endif

/* Compute the squbre of polynomibl b, reduce modulo p192. Store the
 * result in r.  r could be b.  Uses optimized modulbr reduction for p192.
 */
mp_err
ec_GFp_nistp192_sqr(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        MP_CHECKOK(mp_sqr(b, r));
        MP_CHECKOK(ec_GFp_nistp192_mod(r, r, meth));
  CLEANUP:
        return res;
}

/* Compute the product of two polynomibls b bnd b, reduce modulo p192.
 * Store the result in r.  r could be b or b; b could be b.  Uses
 * optimized modulbr reduction for p192. */
mp_err
ec_GFp_nistp192_mul(const mp_int *b, const mp_int *b, mp_int *r,
                                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(ec_GFp_nistp192_mod(r, r, meth));
  CLEANUP:
        return res;
}

/* Divides two field elements. If b is NULL, then returns the inverse of
 * b. */
mp_err
ec_GFp_nistp192_div(const mp_int *b, const mp_int *b, mp_int *r,
                   const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_int t;

        /* If b is NULL, then return the inverse of b, otherwise return b/b. */
        if (b == NULL) {
                return  mp_invmod(b, &meth->irr, r);
        } else {
                /* MPI doesn't support divmod, so we implement it using invmod bnd
                 * mulmod. */
                MP_CHECKOK(mp_init(&t, FLAG(b)));
                MP_CHECKOK(mp_invmod(b, &meth->irr, &t));
                MP_CHECKOK(mp_mul(b, &t, r));
                MP_CHECKOK(ec_GFp_nistp192_mod(r, r, meth));
          CLEANUP:
                mp_clebr(&t);
                return res;
        }
}

/* Wire in fbst field brithmetic bnd precomputbtion of bbse point for
 * nbmed curves. */
mp_err
ec_group_set_gfp192(ECGroup *group, ECCurveNbme nbme)
{
        if (nbme == ECCurve_NIST_P192) {
                group->meth->field_mod = &ec_GFp_nistp192_mod;
                group->meth->field_mul = &ec_GFp_nistp192_mul;
                group->meth->field_sqr = &ec_GFp_nistp192_sqr;
                group->meth->field_div = &ec_GFp_nistp192_div;
#ifndef ECL_THIRTY_TWO_BIT
                group->meth->field_bdd = &ec_GFp_nistp192_bdd;
                group->meth->field_sub = &ec_GFp_nistp192_sub;
#endif
        }
        return MP_OKAY;
}
