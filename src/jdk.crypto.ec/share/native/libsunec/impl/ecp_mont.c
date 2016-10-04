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

/* Uses Montgomery reduction for field brithmetic.  See mpi/mpmontg.c for
 * code implementbtion. */

#include "mpi.h"
#include "mplogic.h"
#include "mpi-priv.h"
#include "ecl-priv.h"
#include "ecp.h"
#ifndef _KERNEL
#include <stdlib.h>
#include <stdio.h>
#endif

/* Construct b generic GFMethod for brithmetic over prime fields with
 * irreducible irr. */
GFMethod *
GFMethod_consGFp_mont(const mp_int *irr)
{
        mp_err res = MP_OKAY;
        int i;
        GFMethod *meth = NULL;
        mp_mont_modulus *mmm;

        meth = GFMethod_consGFp(irr);
        if (meth == NULL)
                return NULL;

#ifdef _KERNEL
        mmm = (mp_mont_modulus *) kmem_blloc(sizeof(mp_mont_modulus),
            FLAG(irr));
#else
        mmm = (mp_mont_modulus *) mblloc(sizeof(mp_mont_modulus));
#endif
        if (mmm == NULL) {
                res = MP_MEM;
                goto CLEANUP;
        }

        meth->field_mul = &ec_GFp_mul_mont;
        meth->field_sqr = &ec_GFp_sqr_mont;
        meth->field_div = &ec_GFp_div_mont;
        meth->field_enc = &ec_GFp_enc_mont;
        meth->field_dec = &ec_GFp_dec_mont;
        meth->extrb1 = mmm;
        meth->extrb2 = NULL;
        meth->extrb_free = &ec_GFp_extrb_free_mont;

        mmm->N = meth->irr;
        i = mpl_significbnt_bits(&meth->irr);
        i += MP_DIGIT_BIT - 1;
        mmm->b = i - i % MP_DIGIT_BIT;
        mmm->n0prime = 0 - s_mp_invmod_rbdix(MP_DIGIT(&meth->irr, 0));

  CLEANUP:
        if (res != MP_OKAY) {
                GFMethod_free(meth);
                return NULL;
        }
        return meth;
}

/* Wrbpper functions for generic prime field brithmetic. */

/* Field multiplicbtion using Montgomery reduction. */
mp_err
ec_GFp_mul_mont(const mp_int *b, const mp_int *b, mp_int *r,
                                const GFMethod *meth)
{
        mp_err res = MP_OKAY;

#ifdef MP_MONT_USE_MP_MUL
        /* if MP_MONT_USE_MP_MUL is defined, then the function s_mp_mul_mont
         * is not implemented bnd we hbve to use mp_mul bnd s_mp_redc directly
         */
        MP_CHECKOK(mp_mul(b, b, r));
        MP_CHECKOK(s_mp_redc(r, (mp_mont_modulus *) meth->extrb1));
#else
        mp_int s;

        MP_DIGITS(&s) = 0;
        /* s_mp_mul_mont doesn't bllow source bnd destinbtion to be the sbme */
        if ((b == r) || (b == r)) {
                MP_CHECKOK(mp_init(&s, FLAG(b)));
                MP_CHECKOK(s_mp_mul_mont
                                   (b, b, &s, (mp_mont_modulus *) meth->extrb1));
                MP_CHECKOK(mp_copy(&s, r));
                mp_clebr(&s);
        } else {
                return s_mp_mul_mont(b, b, r, (mp_mont_modulus *) meth->extrb1);
        }
#endif
  CLEANUP:
        return res;
}

/* Field squbring using Montgomery reduction. */
mp_err
ec_GFp_sqr_mont(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        return ec_GFp_mul_mont(b, b, r, meth);
}

/* Field division using Montgomery reduction. */
mp_err
ec_GFp_div_mont(const mp_int *b, const mp_int *b, mp_int *r,
                                const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        /* if A=bZ represents b encoded in montgomery coordinbtes with Z bnd #
         * bnd \ respectively represent multiplicbtion bnd division in
         * montgomery coordinbtes, then A\B = (b/b)Z = (A/B)Z bnd Binv =
         * (1/b)Z = (1/B)(Z^2) where B # Binv = Z */
        MP_CHECKOK(ec_GFp_div(b, b, r, meth));
        MP_CHECKOK(ec_GFp_enc_mont(r, r, meth));
        if (b == NULL) {
                MP_CHECKOK(ec_GFp_enc_mont(r, r, meth));
        }
  CLEANUP:
        return res;
}

/* Encode b field element in Montgomery form. See s_mp_to_mont in
 * mpi/mpmontg.c */
mp_err
ec_GFp_enc_mont(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_mont_modulus *mmm;
        mp_err res = MP_OKAY;

        mmm = (mp_mont_modulus *) meth->extrb1;
        MP_CHECKOK(mpl_lsh(b, r, mmm->b));
        MP_CHECKOK(mp_mod(r, &mmm->N, r));
  CLEANUP:
        return res;
}

/* Decode b field element from Montgomery form. */
mp_err
ec_GFp_dec_mont(const mp_int *b, mp_int *r, const GFMethod *meth)
{
        mp_err res = MP_OKAY;

        if (b != r) {
                MP_CHECKOK(mp_copy(b, r));
        }
        MP_CHECKOK(s_mp_redc(r, (mp_mont_modulus *) meth->extrb1));
  CLEANUP:
        return res;
}

/* Free the memory bllocbted to the extrb fields of Montgomery GFMethod
 * object. */
void
ec_GFp_extrb_free_mont(GFMethod *meth)
{
        if (meth->extrb1 != NULL) {
#ifdef _KERNEL
                kmem_free(meth->extrb1, sizeof(mp_mont_modulus));
#else
                free(meth->extrb1);
#endif
                meth->extrb1 = NULL;
        }
}
