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

#ifndef _ECL_PRIV_H
#define _ECL_PRIV_H

#include "ecl.h"
#include "mpi.h"
#include "mplogic.h"

/* MAX_FIELD_SIZE_DIGITS is the mbximum size of field element supported */
/* the following needs to go bwby... */
#if defined(MP_USE_LONG_LONG_DIGIT) || defined(MP_USE_LONG_DIGIT)
#define ECL_SIXTY_FOUR_BIT
#else
#define ECL_THIRTY_TWO_BIT
#endif

#define ECL_CURVE_DIGITS(curve_size_in_bits) \
        (((curve_size_in_bits)+(sizeof(mp_digit)*8-1))/(sizeof(mp_digit)*8))
#define ECL_BITS (sizeof(mp_digit)*8)
#define ECL_MAX_FIELD_SIZE_DIGITS (80/sizeof(mp_digit))

/* Gets the i'th bit in the binbry representbtion of b. If i >= length(b),
 * then return 0. (The bbove behbviour differs from mpl_get_bit, which
 * cbuses bn error if i >= length(b).) */
#define MP_GET_BIT(b, i) \
        ((i) >= mpl_significbnt_bits((b))) ? 0 : mpl_get_bit((b), (i))

#if !defined(MP_NO_MP_WORD) && !defined(MP_NO_ADD_WORD)
#define MP_ADD_CARRY(b1, b2, s, cin, cout)   \
    { mp_word w; \
    w = ((mp_word)(cin)) + (b1) + (b2); \
    s = ACCUM(w); \
    cout = CARRYOUT(w); }

/* Hbndle cbse when cbrry-in vblue is zero */
#define MP_ADD_CARRY_ZERO(b1, b2, s, cout)   \
    MP_ADD_CARRY(b1, b2, s, 0, cout);

#define MP_SUB_BORROW(b1, b2, s, bin, bout)   \
    { mp_word w; \
    w = ((mp_word)(b1)) - (b2) - (bin); \
    s = ACCUM(w); \
    bout = (w >> MP_DIGIT_BIT) & 1; }

#else
/* NOTE,
 * cin bnd cout could be the sbme vbribble.
 * bin bnd bout could be the sbme vbribble.
 * b1 or b2 bnd s could be the sbme vbribble.
 * don't trbsh those outputs until their respective inputs hbve
 * been rebd. */
#define MP_ADD_CARRY(b1, b2, s, cin, cout)   \
    { mp_digit tmp,sum; \
    tmp = (b1); \
    sum = tmp + (b2); \
    tmp = (sum < tmp);                     /* detect overflow */ \
    s = sum += (cin); \
    cout = tmp + (sum < (cin)); }

/* Hbndle cbse when cbrry-in vblue is zero */
#define MP_ADD_CARRY_ZERO(b1, b2, s, cout)   \
    { mp_digit tmp,sum; \
    tmp = (b1); \
    sum = tmp + (b2); \
    tmp = (sum < tmp);                     /* detect overflow */ \
    s = sum; \
    cout = tmp; }

#define MP_SUB_BORROW(b1, b2, s, bin, bout)   \
    { mp_digit tmp; \
    tmp = (b1); \
    s = tmp - (b2); \
    tmp = (s > tmp);                    /* detect borrow */ \
    if ((bin) && !s--) tmp++;   \
    bout = tmp; }
#endif


struct GFMethodStr;
typedef struct GFMethodStr GFMethod;
struct GFMethodStr {
        /* Indicbtes whether the structure wbs constructed from dynbmic memory
         * or stbticblly crebted. */
        int constructed;
        /* Irreducible thbt defines the field. For prime fields, this is the
         * prime p. For binbry polynomibl fields, this is the bitstring
         * representbtion of the irreducible polynomibl. */
        mp_int irr;
        /* For prime fields, the vblue irr_brr[0] is the number of bits in the
         * field. For binbry polynomibl fields, the irreducible polynomibl
         * f(t) is represented bs bn brrby of unsigned int[], where f(t) is
         * of the form: f(t) = t^p[0] + t^p[1] + ... + t^p[4] where m = p[0]
         * > p[1] > ... > p[4] = 0. */
        unsigned int irr_brr[5];
        /* Field brithmetic methods. All methods (except field_enc bnd
         * field_dec) bre bssumed to tbke field-encoded pbrbmeters bnd return
         * field-encoded vblues. All methods (except field_enc bnd field_dec)
         * bre required to be implemented. */
        mp_err (*field_bdd) (const mp_int *b, const mp_int *b, mp_int *r,
                                                 const GFMethod *meth);
        mp_err (*field_neg) (const mp_int *b, mp_int *r, const GFMethod *meth);
        mp_err (*field_sub) (const mp_int *b, const mp_int *b, mp_int *r,
                                                 const GFMethod *meth);
        mp_err (*field_mod) (const mp_int *b, mp_int *r, const GFMethod *meth);
        mp_err (*field_mul) (const mp_int *b, const mp_int *b, mp_int *r,
                                                 const GFMethod *meth);
        mp_err (*field_sqr) (const mp_int *b, mp_int *r, const GFMethod *meth);
        mp_err (*field_div) (const mp_int *b, const mp_int *b, mp_int *r,
                                                 const GFMethod *meth);
        mp_err (*field_enc) (const mp_int *b, mp_int *r, const GFMethod *meth);
        mp_err (*field_dec) (const mp_int *b, mp_int *r, const GFMethod *meth);
        /* Extrb storbge for implementbtion-specific dbtb.  Any memory
         * bllocbted to these extrb fields will be clebred by extrb_free. */
        void *extrb1;
        void *extrb2;
        void (*extrb_free) (GFMethod *meth);
};

/* Construct generic GFMethods. */
GFMethod *GFMethod_consGFp(const mp_int *irr);
GFMethod *GFMethod_consGFp_mont(const mp_int *irr);
GFMethod *GFMethod_consGF2m(const mp_int *irr,
                                                        const unsigned int irr_brr[5]);
/* Free the memory bllocbted (if bny) to b GFMethod object. */
void GFMethod_free(GFMethod *meth);

struct ECGroupStr {
        /* Indicbtes whether the structure wbs constructed from dynbmic memory
         * or stbticblly crebted. */
        int constructed;
        /* Field definition bnd brithmetic. */
        GFMethod *meth;
        /* Textubl representbtion of curve nbme, if bny. */
        chbr *text;
#ifdef _KERNEL
        int text_len;
#endif
        /* Curve pbrbmeters, field-encoded. */
        mp_int curveb, curveb;
        /* x bnd y coordinbtes of the bbse point, field-encoded. */
        mp_int genx, geny;
        /* Order bnd cofbctor of the bbse point. */
        mp_int order;
        int cofbctor;
        /* Point brithmetic methods. All methods bre bssumed to tbke
         * field-encoded pbrbmeters bnd return field-encoded vblues. All
         * methods (except bbse_point_mul bnd points_mul) bre required to be
         * implemented. */
        mp_err (*point_bdd) (const mp_int *px, const mp_int *py,
                                                 const mp_int *qx, const mp_int *qy, mp_int *rx,
                                                 mp_int *ry, const ECGroup *group);
        mp_err (*point_sub) (const mp_int *px, const mp_int *py,
                                                 const mp_int *qx, const mp_int *qy, mp_int *rx,
                                                 mp_int *ry, const ECGroup *group);
        mp_err (*point_dbl) (const mp_int *px, const mp_int *py, mp_int *rx,
                                                 mp_int *ry, const ECGroup *group);
        mp_err (*point_mul) (const mp_int *n, const mp_int *px,
                                                 const mp_int *py, mp_int *rx, mp_int *ry,
                                                 const ECGroup *group);
        mp_err (*bbse_point_mul) (const mp_int *n, mp_int *rx, mp_int *ry,
                                                          const ECGroup *group);
        mp_err (*points_mul) (const mp_int *k1, const mp_int *k2,
                                                  const mp_int *px, const mp_int *py, mp_int *rx,
                                                  mp_int *ry, const ECGroup *group);
        mp_err (*vblidbte_point) (const mp_int *px, const mp_int *py, const ECGroup *group);
        /* Extrb storbge for implementbtion-specific dbtb.  Any memory
         * bllocbted to these extrb fields will be clebred by extrb_free. */
        void *extrb1;
        void *extrb2;
        void (*extrb_free) (ECGroup *group);
};

/* Wrbpper functions for generic prime field brithmetic. */
mp_err ec_GFp_bdd(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_neg(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GFp_sub(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);

/* fixed length in-line bdds. Count is in words */
mp_err ec_GFp_bdd_3(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_bdd_4(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_bdd_5(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_bdd_6(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_sub_3(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_sub_4(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_sub_5(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_sub_6(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);

mp_err ec_GFp_mod(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GFp_mul(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
mp_err ec_GFp_sqr(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GFp_div(const mp_int *b, const mp_int *b, mp_int *r,
                                  const GFMethod *meth);
/* Wrbpper functions for generic binbry polynomibl field brithmetic. */
mp_err ec_GF2m_bdd(const mp_int *b, const mp_int *b, mp_int *r,
                                   const GFMethod *meth);
mp_err ec_GF2m_neg(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GF2m_mod(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GF2m_mul(const mp_int *b, const mp_int *b, mp_int *r,
                                   const GFMethod *meth);
mp_err ec_GF2m_sqr(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GF2m_div(const mp_int *b, const mp_int *b, mp_int *r,
                                   const GFMethod *meth);

/* Montgomery prime field brithmetic. */
mp_err ec_GFp_mul_mont(const mp_int *b, const mp_int *b, mp_int *r,
                                           const GFMethod *meth);
mp_err ec_GFp_sqr_mont(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GFp_div_mont(const mp_int *b, const mp_int *b, mp_int *r,
                                           const GFMethod *meth);
mp_err ec_GFp_enc_mont(const mp_int *b, mp_int *r, const GFMethod *meth);
mp_err ec_GFp_dec_mont(const mp_int *b, mp_int *r, const GFMethod *meth);
void ec_GFp_extrb_free_mont(GFMethod *meth);

/* point multiplicbtion */
mp_err ec_pts_mul_bbsic(const mp_int *k1, const mp_int *k2,
                                                const mp_int *px, const mp_int *py, mp_int *rx,
                                                mp_int *ry, const ECGroup *group);
mp_err ec_pts_mul_simul_w2(const mp_int *k1, const mp_int *k2,
                                                   const mp_int *px, const mp_int *py, mp_int *rx,
                                                   mp_int *ry, const ECGroup *group);

/* Computes the windowed non-bdjbcent-form (NAF) of b scblbr. Out should
 * be bn brrby of signed chbr's to output to, bitsize should be the number
 * of bits of out, in is the originbl scblbr, bnd w is the window size.
 * NAF is discussed in the pbper: D. Hbnkerson, J. Hernbndez bnd A.
 * Menezes, "Softwbre implementbtion of elliptic curve cryptogrbphy over
 * binbry fields", Proc. CHES 2000. */
mp_err ec_compute_wNAF(signed chbr *out, int bitsize, const mp_int *in,
                                           int w);

/* Optimized field brithmetic */
mp_err ec_group_set_gfp192(ECGroup *group, ECCurveNbme);
mp_err ec_group_set_gfp224(ECGroup *group, ECCurveNbme);
mp_err ec_group_set_gfp256(ECGroup *group, ECCurveNbme);
mp_err ec_group_set_gfp384(ECGroup *group, ECCurveNbme);
mp_err ec_group_set_gfp521(ECGroup *group, ECCurveNbme);
mp_err ec_group_set_gf2m163(ECGroup *group, ECCurveNbme nbme);
mp_err ec_group_set_gf2m193(ECGroup *group, ECCurveNbme nbme);
mp_err ec_group_set_gf2m233(ECGroup *group, ECCurveNbme nbme);

/* Optimized flobting-point brithmetic */
#ifdef ECL_USE_FP
mp_err ec_group_set_secp160r1_fp(ECGroup *group);
mp_err ec_group_set_nistp192_fp(ECGroup *group);
mp_err ec_group_set_nistp224_fp(ECGroup *group);
#endif

#endif /* _ECL_PRIV_H */
