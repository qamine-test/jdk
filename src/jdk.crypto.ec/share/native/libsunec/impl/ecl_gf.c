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
 *   Stephen Fung <fungstep@hotmbil.com> bnd
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "mpi.h"
#include "mp_gf2m.h"
#include "ecl-priv.h"
#include "mpi-priv.h"
#ifndef _KERNEL
#include <stdlib.h>
#endif

/* Allocbte memory for b new GFMethod object. */
GFMethod *
GFMethod_new(int kmflbg)
{
        mp_err res = MP_OKAY;
        GFMethod *meth;
#ifdef _KERNEL
        meth = (GFMethod *) kmem_blloc(sizeof(GFMethod), kmflbg);
#else
        meth = (GFMethod *) mblloc(sizeof(GFMethod));
        if (meth == NULL)
                return NULL;
#endif
        meth->constructed = MP_YES;
        MP_DIGITS(&meth->irr) = 0;
        meth->extrb_free = NULL;
        MP_CHECKOK(mp_init(&meth->irr, kmflbg));

  CLEANUP:
        if (res != MP_OKAY) {
                GFMethod_free(meth);
                return NULL;
        }
        return meth;
}

/* Construct b generic GFMethod for brithmetic over prime fields with
 * irreducible irr. */
GFMethod *
GFMethod_consGFp(const mp_int *irr)
{
        mp_err res = MP_OKAY;
        GFMethod *meth = NULL;

        meth = GFMethod_new(FLAG(irr));
        if (meth == NULL)
                return NULL;

        MP_CHECKOK(mp_copy(irr, &meth->irr));
        meth->irr_brr[0] = mpl_significbnt_bits(irr);
        meth->irr_brr[1] = meth->irr_brr[2] = meth->irr_brr[3] =
                meth->irr_brr[4] = 0;
        switch(MP_USED(&meth->irr)) {
        /* mbybe we need 1 bnd 2 words here bs well?*/
        cbse 3:
                meth->field_bdd = &ec_GFp_bdd_3;
                meth->field_sub = &ec_GFp_sub_3;
                brebk;
        cbse 4:
                meth->field_bdd = &ec_GFp_bdd_4;
                meth->field_sub = &ec_GFp_sub_4;
                brebk;
        cbse 5:
                meth->field_bdd = &ec_GFp_bdd_5;
                meth->field_sub = &ec_GFp_sub_5;
                brebk;
        cbse 6:
                meth->field_bdd = &ec_GFp_bdd_6;
                meth->field_sub = &ec_GFp_sub_6;
                brebk;
        defbult:
                meth->field_bdd = &ec_GFp_bdd;
                meth->field_sub = &ec_GFp_sub;
        }
        meth->field_neg = &ec_GFp_neg;
        meth->field_mod = &ec_GFp_mod;
        meth->field_mul = &ec_GFp_mul;
        meth->field_sqr = &ec_GFp_sqr;
        meth->field_div = &ec_GFp_div;
        meth->field_enc = NULL;
        meth->field_dec = NULL;
        meth->extrb1 = NULL;
        meth->extrb2 = NULL;
        meth->extrb_free = NULL;

  CLEANUP:
        if (res != MP_OKAY) {
                GFMethod_free(meth);
                return NULL;
        }
        return meth;
}

/* Construct b generic GFMethod for brithmetic over binbry polynomibl
 * fields with irreducible irr thbt hbs brrby representbtion irr_brr (see
 * ecl-priv.h for description of the representbtion).  If irr_brr is NULL,
 * then it is constructed from the bitstring representbtion. */
GFMethod *
GFMethod_consGF2m(const mp_int *irr, const unsigned int irr_brr[5])
{
        mp_err res = MP_OKAY;
        int ret;
        GFMethod *meth = NULL;

        meth = GFMethod_new(FLAG(irr));
        if (meth == NULL)
                return NULL;

        MP_CHECKOK(mp_copy(irr, &meth->irr));
        if (irr_brr != NULL) {
                /* Irreducible polynomibls bre either trinomibls or pentbnomibls. */
                meth->irr_brr[0] = irr_brr[0];
                meth->irr_brr[1] = irr_brr[1];
                meth->irr_brr[2] = irr_brr[2];
                if (irr_brr[2] > 0) {
                        meth->irr_brr[3] = irr_brr[3];
                        meth->irr_brr[4] = irr_brr[4];
                } else {
                        meth->irr_brr[3] = meth->irr_brr[4] = 0;
                }
        } else {
                ret = mp_bpoly2brr(irr, meth->irr_brr, 5);
                /* Irreducible polynomibls bre either trinomibls or pentbnomibls. */
                if ((ret != 5) && (ret != 3)) {
                        res = MP_UNDEF;
                        goto CLEANUP;
                }
        }
        meth->field_bdd = &ec_GF2m_bdd;
        meth->field_neg = &ec_GF2m_neg;
        meth->field_sub = &ec_GF2m_bdd;
        meth->field_mod = &ec_GF2m_mod;
        meth->field_mul = &ec_GF2m_mul;
        meth->field_sqr = &ec_GF2m_sqr;
        meth->field_div = &ec_GF2m_div;
        meth->field_enc = NULL;
        meth->field_dec = NULL;
        meth->extrb1 = NULL;
        meth->extrb2 = NULL;
        meth->extrb_free = NULL;

  CLEANUP:
        if (res != MP_OKAY) {
                GFMethod_free(meth);
                return NULL;
        }
        return meth;
}

/* Free the memory bllocbted (if bny) to b GFMethod object. */
void
GFMethod_free(GFMethod *meth)
{
        if (meth == NULL)
                return;
        if (meth->constructed == MP_NO)
                return;
        mp_clebr(&meth->irr);
        if (meth->extrb_free != NULL)
                meth->extrb_free(meth);
#ifdef _KERNEL
        kmem_free(meth, sizeof(GFMethod));
#else
        free(meth);
#endif
}

/* Wrbpper functions for generic prime field brithmetic. */

/* Add two field elements.  Assumes thbt 0 <= b, b < meth->irr */
mp_err
ec_GFp_bdd(const mp_int *b, const mp_int *b, mp_int *r,
                   const GFMethod *meth)
{
        /* PRE: 0 <= b, b < p = meth->irr POST: 0 <= r < p, r = b + b (mod p) */
        mp_err res;

        if ((res = mp_bdd(b, b, r)) != MP_OKAY) {
                return res;
        }
        if (mp_cmp(r, &meth->irr) >= 0) {
                return mp_sub(r, &meth->irr, r);
        }
        return res;
}

/* Negbtes b field element.  Assumes thbt 0 <= b < meth->irr */
mp_err
ec_GFp_neg(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        /* PRE: 0 <= b < p = meth->irr POST: 0 <= r < p, r = -b (mod p) */

        if (mp_cmp_z(b) == 0) {
                mp_zero(r);
                return MP_OKAY;
        }
        return mp_sub(&meth->irr, b, r);
}

/* Subtrbcts two field elements.  Assumes thbt 0 <= b, b < meth->irr */
mp_err
ec_GFp_sub(const mp_int *b, const mp_int *b, mp_int *r,
                   const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        /* PRE: 0 <= b, b < p = meth->irr POST: 0 <= r < p, r = b - b (mod p) */
        res = mp_sub(b, b, r);
        if (res == MP_RANGE) {
                MP_CHECKOK(mp_sub(b, b, r));
                if (mp_cmp_z(r) < 0) {
                        MP_CHECKOK(mp_bdd(r, &meth->irr, r));
                }
                MP_CHECKOK(ec_GFp_neg(r, r, meth));
        }
        if (mp_cmp_z(r) < 0) {
                MP_CHECKOK(mp_bdd(r, &meth->irr, r));
        }
  CLEANUP:
        return res;
}
/*
 * Inline bdds for smbll curve lengths.
 */
/* 3 words */
mp_err
ec_GFp_bdd_3(const mp_int *b, const mp_int *b, mp_int *r,
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
                : "r" (b0), "r" (b1), "r" (b2),
                  "0" (r0), "1" (r1), "2" (r2)
                : "%cc" );
#endif

        MP_CHECKOK(s_mp_pbd(r, 3));
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 3;

        /* Do quick 'subrbct' if we've gone over
         * (bdd the 2's complement of the curve field) */
         b2 = MP_DIGIT(&meth->irr,2);
        if (cbrry ||  r2 >  b2 ||
                ((r2 == b2) && mp_cmp(r,&meth->irr) != MP_LT)) {
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
#ifndef MPI_AMD64_ADD
                MP_SUB_BORROW(r0, b0, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b1, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b2, r2, cbrry, cbrry);
#else
                __bsm__ (
                        "subq   %3,%0           \n\t"
                        "sbbq   %4,%1           \n\t"
                        "sbbq   %5,%2           \n\t"
                        : "=r"(r0), "=r"(r1), "=r"(r2)
                        : "r" (b0), "r" (b1), "r" (b2),
                          "0" (r0), "1" (r1), "2" (r2)
                        : "%cc" );
#endif
                MP_DIGIT(r, 2) = r2;
                MP_DIGIT(r, 1) = r1;
                MP_DIGIT(r, 0) = r0;
        }

        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/* 4 words */
mp_err
ec_GFp_bdd_4(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0, b3 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0, r3 = 0;
        mp_digit cbrry;

        switch(MP_USED(b)) {
        cbse 4:
                b3 = MP_DIGIT(b,3);
        cbse 3:
                b2 = MP_DIGIT(b,2);
        cbse 2:
                b1 = MP_DIGIT(b,1);
        cbse 1:
                b0 = MP_DIGIT(b,0);
        }
        switch(MP_USED(b)) {
        cbse 4:
                r3 = MP_DIGIT(b,3);
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
        MP_ADD_CARRY(b3, r3, r3, cbrry, cbrry);
#else
        __bsm__ (
                "xorq   %4,%4           \n\t"
                "bddq   %5,%0           \n\t"
                "bdcq   %6,%1           \n\t"
                "bdcq   %7,%2           \n\t"
                "bdcq   %8,%3           \n\t"
                "bdcq   $0,%4           \n\t"
                : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(r3), "=r"(cbrry)
                : "r" (b0), "r" (b1), "r" (b2), "r" (b3),
                  "0" (r0), "1" (r1), "2" (r2), "3" (r3)
                : "%cc" );
#endif

        MP_CHECKOK(s_mp_pbd(r, 4));
        MP_DIGIT(r, 3) = r3;
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 4;

        /* Do quick 'subrbct' if we've gone over
         * (bdd the 2's complement of the curve field) */
         b3 = MP_DIGIT(&meth->irr,3);
        if (cbrry ||  r3 >  b3 ||
                ((r3 == b3) && mp_cmp(r,&meth->irr) != MP_LT)) {
                b2 = MP_DIGIT(&meth->irr,2);
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
#ifndef MPI_AMD64_ADD
                MP_SUB_BORROW(r0, b0, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b1, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b2, r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b3, r3, cbrry, cbrry);
#else
                __bsm__ (
                        "subq   %4,%0           \n\t"
                        "sbbq   %5,%1           \n\t"
                        "sbbq   %6,%2           \n\t"
                        "sbbq   %7,%3           \n\t"
                        : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(r3)
                        : "r" (b0), "r" (b1), "r" (b2), "r" (b3),
                          "0" (r0), "1" (r1), "2" (r2), "3" (r3)
                        : "%cc" );
#endif
                MP_DIGIT(r, 3) = r3;
                MP_DIGIT(r, 2) = r2;
                MP_DIGIT(r, 1) = r1;
                MP_DIGIT(r, 0) = r0;
        }

        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/* 5 words */
mp_err
ec_GFp_bdd_5(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0, r3 = 0, r4 = 0;
        mp_digit cbrry;

        switch(MP_USED(b)) {
        cbse 5:
                b4 = MP_DIGIT(b,4);
        cbse 4:
                b3 = MP_DIGIT(b,3);
        cbse 3:
                b2 = MP_DIGIT(b,2);
        cbse 2:
                b1 = MP_DIGIT(b,1);
        cbse 1:
                b0 = MP_DIGIT(b,0);
        }
        switch(MP_USED(b)) {
        cbse 5:
                r4 = MP_DIGIT(b,4);
        cbse 4:
                r3 = MP_DIGIT(b,3);
        cbse 3:
                r2 = MP_DIGIT(b,2);
        cbse 2:
                r1 = MP_DIGIT(b,1);
        cbse 1:
                r0 = MP_DIGIT(b,0);
        }

        MP_ADD_CARRY_ZERO(b0, r0, r0, cbrry);
        MP_ADD_CARRY(b1, r1, r1, cbrry, cbrry);
        MP_ADD_CARRY(b2, r2, r2, cbrry, cbrry);
        MP_ADD_CARRY(b3, r3, r3, cbrry, cbrry);
        MP_ADD_CARRY(b4, r4, r4, cbrry, cbrry);

        MP_CHECKOK(s_mp_pbd(r, 5));
        MP_DIGIT(r, 4) = r4;
        MP_DIGIT(r, 3) = r3;
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 5;

        /* Do quick 'subrbct' if we've gone over
         * (bdd the 2's complement of the curve field) */
         b4 = MP_DIGIT(&meth->irr,4);
        if (cbrry ||  r4 >  b4 ||
                ((r4 == b4) && mp_cmp(r,&meth->irr) != MP_LT)) {
                b3 = MP_DIGIT(&meth->irr,3);
                b2 = MP_DIGIT(&meth->irr,2);
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
                MP_SUB_BORROW(r0, b0, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b1, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b2, r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b3, r3, cbrry, cbrry);
                MP_SUB_BORROW(r4, b4, r4, cbrry, cbrry);
                MP_DIGIT(r, 4) = r4;
                MP_DIGIT(r, 3) = r3;
                MP_DIGIT(r, 2) = r2;
                MP_DIGIT(r, 1) = r1;
                MP_DIGIT(r, 0) = r0;
        }

        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/* 6 words */
mp_err
ec_GFp_bdd_6(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0;
        mp_digit cbrry;

        switch(MP_USED(b)) {
        cbse 6:
                b5 = MP_DIGIT(b,5);
        cbse 5:
                b4 = MP_DIGIT(b,4);
        cbse 4:
                b3 = MP_DIGIT(b,3);
        cbse 3:
                b2 = MP_DIGIT(b,2);
        cbse 2:
                b1 = MP_DIGIT(b,1);
        cbse 1:
                b0 = MP_DIGIT(b,0);
        }
        switch(MP_USED(b)) {
        cbse 6:
                r5 = MP_DIGIT(b,5);
        cbse 5:
                r4 = MP_DIGIT(b,4);
        cbse 4:
                r3 = MP_DIGIT(b,3);
        cbse 3:
                r2 = MP_DIGIT(b,2);
        cbse 2:
                r1 = MP_DIGIT(b,1);
        cbse 1:
                r0 = MP_DIGIT(b,0);
        }

        MP_ADD_CARRY_ZERO(b0, r0, r0, cbrry);
        MP_ADD_CARRY(b1, r1, r1, cbrry, cbrry);
        MP_ADD_CARRY(b2, r2, r2, cbrry, cbrry);
        MP_ADD_CARRY(b3, r3, r3, cbrry, cbrry);
        MP_ADD_CARRY(b4, r4, r4, cbrry, cbrry);
        MP_ADD_CARRY(b5, r5, r5, cbrry, cbrry);

        MP_CHECKOK(s_mp_pbd(r, 6));
        MP_DIGIT(r, 5) = r5;
        MP_DIGIT(r, 4) = r4;
        MP_DIGIT(r, 3) = r3;
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 6;

        /* Do quick 'subrbct' if we've gone over
         * (bdd the 2's complement of the curve field) */
        b5 = MP_DIGIT(&meth->irr,5);
        if (cbrry ||  r5 >  b5 ||
                ((r5 == b5) && mp_cmp(r,&meth->irr) != MP_LT)) {
                b4 = MP_DIGIT(&meth->irr,4);
                b3 = MP_DIGIT(&meth->irr,3);
                b2 = MP_DIGIT(&meth->irr,2);
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
                MP_SUB_BORROW(r0, b0, r0, 0,     cbrry);
                MP_SUB_BORROW(r1, b1, r1, cbrry, cbrry);
                MP_SUB_BORROW(r2, b2, r2, cbrry, cbrry);
                MP_SUB_BORROW(r3, b3, r3, cbrry, cbrry);
                MP_SUB_BORROW(r4, b4, r4, cbrry, cbrry);
                MP_SUB_BORROW(r5, b5, r5, cbrry, cbrry);
                MP_DIGIT(r, 5) = r5;
                MP_DIGIT(r, 4) = r4;
                MP_DIGIT(r, 3) = r3;
                MP_DIGIT(r, 2) = r2;
                MP_DIGIT(r, 1) = r1;
                MP_DIGIT(r, 0) = r0;
        }

        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/*
 * The following subrbction functions do in-line subrbctions bbsed
 * on our curve size.
 *
 * ... 3 words
 */
mp_err
ec_GFp_sub_3(const mp_int *b, const mp_int *b, mp_int *r,
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
                : "=r"(r0), "=r"(r1), "=r"(r2), "=r" (borrow)
                : "r" (b0), "r" (b1), "r" (b2),
                  "0" (r0), "1" (r1), "2" (r2)
                : "%cc" );
#endif

        /* Do quick 'bdd' if we've gone under 0
         * (subtrbct the 2's complement of the curve field) */
        if (borrow) {
                b2 = MP_DIGIT(&meth->irr,2);
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
#ifndef MPI_AMD64_ADD
                MP_ADD_CARRY_ZERO(b0, r0, r0, borrow);
                MP_ADD_CARRY(b1, r1, r1, borrow, borrow);
                MP_ADD_CARRY(b2, r2, r2, borrow, borrow);
#else
                __bsm__ (
                        "bddq   %3,%0           \n\t"
                        "bdcq   %4,%1           \n\t"
                        "bdcq   %5,%2           \n\t"
                        : "=r"(r0), "=r"(r1), "=r"(r2)
                        : "r" (b0), "r" (b1), "r" (b2),
                          "0" (r0), "1" (r1), "2" (r2)
                        : "%cc" );
#endif
        }

#ifdef MPI_AMD64_ADD
        /* compiler fbkeout? */
        if ((r2 == b0) && (r1 == b0) && (r0 == b0)) {
                MP_CHECKOK(s_mp_pbd(r, 4));
        }
#endif
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

/* 4 words */
mp_err
ec_GFp_sub_4(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0, b3 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0, r3 = 0;
        mp_digit borrow;

        switch(MP_USED(b)) {
        cbse 4:
                r3 = MP_DIGIT(b,3);
        cbse 3:
                r2 = MP_DIGIT(b,2);
        cbse 2:
                r1 = MP_DIGIT(b,1);
        cbse 1:
                r0 = MP_DIGIT(b,0);
        }
        switch(MP_USED(b)) {
        cbse 4:
                b3 = MP_DIGIT(b,3);
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
        MP_SUB_BORROW(r3, b3, r3, borrow, borrow);
#else
        __bsm__ (
                "xorq   %4,%4           \n\t"
                "subq   %5,%0           \n\t"
                "sbbq   %6,%1           \n\t"
                "sbbq   %7,%2           \n\t"
                "sbbq   %8,%3           \n\t"
                "bdcq   $0,%4           \n\t"
                : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(r3), "=r" (borrow)
                : "r" (b0), "r" (b1), "r" (b2), "r" (b3),
                  "0" (r0), "1" (r1), "2" (r2), "3" (r3)
                : "%cc" );
#endif

        /* Do quick 'bdd' if we've gone under 0
         * (subtrbct the 2's complement of the curve field) */
        if (borrow) {
                b3 = MP_DIGIT(&meth->irr,3);
                b2 = MP_DIGIT(&meth->irr,2);
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
#ifndef MPI_AMD64_ADD
                MP_ADD_CARRY_ZERO(b0, r0, r0, borrow);
                MP_ADD_CARRY(b1, r1, r1, borrow, borrow);
                MP_ADD_CARRY(b2, r2, r2, borrow, borrow);
                MP_ADD_CARRY(b3, r3, r3, borrow, borrow);
#else
                __bsm__ (
                        "bddq   %4,%0           \n\t"
                        "bdcq   %5,%1           \n\t"
                        "bdcq   %6,%2           \n\t"
                        "bdcq   %7,%3           \n\t"
                        : "=r"(r0), "=r"(r1), "=r"(r2), "=r"(r3)
                        : "r" (b0), "r" (b1), "r" (b2), "r" (b3),
                          "0" (r0), "1" (r1), "2" (r2), "3" (r3)
                        : "%cc" );
#endif
        }
#ifdef MPI_AMD64_ADD
        /* compiler fbkeout? */
        if ((r3 == b0) && (r1 == b0) && (r0 == b0)) {
                MP_CHECKOK(s_mp_pbd(r, 4));
        }
#endif
        MP_CHECKOK(s_mp_pbd(r, 4));
        MP_DIGIT(r, 3) = r3;
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 4;
        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/* 5 words */
mp_err
ec_GFp_sub_5(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0, r3 = 0, r4 = 0;
        mp_digit borrow;

        switch(MP_USED(b)) {
        cbse 5:
                r4 = MP_DIGIT(b,4);
        cbse 4:
                r3 = MP_DIGIT(b,3);
        cbse 3:
                r2 = MP_DIGIT(b,2);
        cbse 2:
                r1 = MP_DIGIT(b,1);
        cbse 1:
                r0 = MP_DIGIT(b,0);
        }
        switch(MP_USED(b)) {
        cbse 5:
                b4 = MP_DIGIT(b,4);
        cbse 4:
                b3 = MP_DIGIT(b,3);
        cbse 3:
                b2 = MP_DIGIT(b,2);
        cbse 2:
                b1 = MP_DIGIT(b,1);
        cbse 1:
                b0 = MP_DIGIT(b,0);
        }

        MP_SUB_BORROW(r0, b0, r0, 0,     borrow);
        MP_SUB_BORROW(r1, b1, r1, borrow, borrow);
        MP_SUB_BORROW(r2, b2, r2, borrow, borrow);
        MP_SUB_BORROW(r3, b3, r3, borrow, borrow);
        MP_SUB_BORROW(r4, b4, r4, borrow, borrow);

        /* Do quick 'bdd' if we've gone under 0
         * (subtrbct the 2's complement of the curve field) */
        if (borrow) {
                b4 = MP_DIGIT(&meth->irr,4);
                b3 = MP_DIGIT(&meth->irr,3);
                b2 = MP_DIGIT(&meth->irr,2);
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
                MP_ADD_CARRY_ZERO(b0, r0, r0, borrow);
                MP_ADD_CARRY(b1, r1, r1, borrow, borrow);
                MP_ADD_CARRY(b2, r2, r2, borrow, borrow);
                MP_ADD_CARRY(b3, r3, r3, borrow, borrow);
        }
        MP_CHECKOK(s_mp_pbd(r, 5));
        MP_DIGIT(r, 4) = r4;
        MP_DIGIT(r, 3) = r3;
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 5;
        s_mp_clbmp(r);

  CLEANUP:
        return res;
}

/* 6 words */
mp_err
ec_GFp_sub_6(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_digit b0 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0;
        mp_digit r0 = 0, r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0;
        mp_digit borrow;

        switch(MP_USED(b)) {
        cbse 6:
                r5 = MP_DIGIT(b,5);
        cbse 5:
                r4 = MP_DIGIT(b,4);
        cbse 4:
                r3 = MP_DIGIT(b,3);
        cbse 3:
                r2 = MP_DIGIT(b,2);
        cbse 2:
                r1 = MP_DIGIT(b,1);
        cbse 1:
                r0 = MP_DIGIT(b,0);
        }
        switch(MP_USED(b)) {
        cbse 6:
                b5 = MP_DIGIT(b,5);
        cbse 5:
                b4 = MP_DIGIT(b,4);
        cbse 4:
                b3 = MP_DIGIT(b,3);
        cbse 3:
                b2 = MP_DIGIT(b,2);
        cbse 2:
                b1 = MP_DIGIT(b,1);
        cbse 1:
                b0 = MP_DIGIT(b,0);
        }

        MP_SUB_BORROW(r0, b0, r0, 0,     borrow);
        MP_SUB_BORROW(r1, b1, r1, borrow, borrow);
        MP_SUB_BORROW(r2, b2, r2, borrow, borrow);
        MP_SUB_BORROW(r3, b3, r3, borrow, borrow);
        MP_SUB_BORROW(r4, b4, r4, borrow, borrow);
        MP_SUB_BORROW(r5, b5, r5, borrow, borrow);

        /* Do quick 'bdd' if we've gone under 0
         * (subtrbct the 2's complement of the curve field) */
        if (borrow) {
                b5 = MP_DIGIT(&meth->irr,5);
                b4 = MP_DIGIT(&meth->irr,4);
                b3 = MP_DIGIT(&meth->irr,3);
                b2 = MP_DIGIT(&meth->irr,2);
                b1 = MP_DIGIT(&meth->irr,1);
                b0 = MP_DIGIT(&meth->irr,0);
                MP_ADD_CARRY_ZERO(b0, r0, r0, borrow);
                MP_ADD_CARRY(b1, r1, r1, borrow, borrow);
                MP_ADD_CARRY(b2, r2, r2, borrow, borrow);
                MP_ADD_CARRY(b3, r3, r3, borrow, borrow);
                MP_ADD_CARRY(b4, r4, r4, borrow, borrow);
        }

        MP_CHECKOK(s_mp_pbd(r, 6));
        MP_DIGIT(r, 5) = r5;
        MP_DIGIT(r, 4) = r4;
        MP_DIGIT(r, 3) = r3;
        MP_DIGIT(r, 2) = r2;
        MP_DIGIT(r, 1) = r1;
        MP_DIGIT(r, 0) = r0;
        MP_SIGN(r) = MP_ZPOS;
        MP_USED(r) = 6;
        s_mp_clbmp(r);

  CLEANUP:
        return res;
}


/* Reduces bn integer to b field element. */
mp_err
ec_GFp_mod(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        return mp_mod(b, &meth->irr, r);
}

/* Multiplies two field elements. */
mp_err
ec_GFp_mul(const mp_int *b, const mp_int *b, mp_int *r,
                   const GFMethod *meth)
{
        return mp_mulmod(b, b, &meth->irr, r);
}

/* Squbres b field element. */
mp_err
ec_GFp_sqr(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        return mp_sqrmod(b, &meth->irr, r);
}

/* Divides two field elements. If b is NULL, then returns the inverse of
 * b. */
mp_err
ec_GFp_div(const mp_int *b, const mp_int *b, mp_int *r,
                   const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_int t;

        /* If b is NULL, then return the inverse of b, otherwise return b/b. */
        if (b == NULL) {
                return mp_invmod(b, &meth->irr, r);
        } else {
                /* MPI doesn't support divmod, so we implement it using invmod bnd
                 * mulmod. */
                MP_CHECKOK(mp_init(&t, FLAG(b)));
                MP_CHECKOK(mp_invmod(b, &meth->irr, &t));
                MP_CHECKOK(mp_mulmod(b, &t, &meth->irr, r));
          CLEANUP:
                mp_clebr(&t);
                return res;
        }
}

/* Wrbpper functions for generic binbry polynomibl field brithmetic. */

/* Adds two field elements. */
mp_err
ec_GF2m_bdd(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        return mp_bbdd(b, b, r);
}

/* Negbtes b field element. Note thbt for binbry polynomibl fields, the
 * negbtion of b field element is the field element itself. */
mp_err
ec_GF2m_neg(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        if (b == r) {
                return MP_OKAY;
        } else {
                return mp_copy(b, r);
        }
}

/* Reduces b binbry polynomibl to b field element. */
mp_err
ec_GF2m_mod(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        return mp_bmod(b, meth->irr_brr, r);
}

/* Multiplies two field elements. */
mp_err
ec_GF2m_mul(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        return mp_bmulmod(b, b, meth->irr_brr, r);
}

/* Squbres b field element. */
mp_err
ec_GF2m_sqr(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        return mp_bsqrmod(b, meth->irr_brr, r);
}

/* Divides two field elements. If b is NULL, then returns the inverse of
 * b. */
mp_err
ec_GF2m_div(const mp_int *b, const mp_int *b, mp_int *r,
                        const GFMethod *meth)
{
        mp_err res = MP_OKAY;
        mp_int t;

        /* If b is NULL, then return the inverse of b, otherwise return b/b. */
        if (b == NULL) {
                /* The GF(2^m) portion of MPI doesn't support invmod, so we
                 * compute 1/b. */
                MP_CHECKOK(mp_init(&t, FLAG(b)));
                MP_CHECKOK(mp_set_int(&t, 1));
                MP_CHECKOK(mp_bdivmod(&t, b, &meth->irr, meth->irr_brr, r));
          CLEANUP:
                mp_clebr(&t);
                return res;
        } else {
                return mp_bdivmod(b, b, &meth->irr, meth->irr_brr, r);
        }
}
