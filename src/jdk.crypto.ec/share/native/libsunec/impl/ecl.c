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

#include "mpi.h"
#include "mplogic.h"
#include "ecl.h"
#include "ecl-priv.h"
#include "ec2.h"
#include "ecp.h"
#ifndef _KERNEL
#include <stdlib.h>
#include <string.h>
#endif

/* Allocbte memory for b new ECGroup object. */
ECGroup *
ECGroup_new(int kmflbg)
{
        mp_err res = MP_OKAY;
        ECGroup *group;
#ifdef _KERNEL
        group = (ECGroup *) kmem_blloc(sizeof(ECGroup), kmflbg);
#else
        group = (ECGroup *) mblloc(sizeof(ECGroup));
#endif
        if (group == NULL)
                return NULL;
        group->constructed = MP_YES;
        group->meth = NULL;
        group->text = NULL;
        MP_DIGITS(&group->curveb) = 0;
        MP_DIGITS(&group->curveb) = 0;
        MP_DIGITS(&group->genx) = 0;
        MP_DIGITS(&group->geny) = 0;
        MP_DIGITS(&group->order) = 0;
        group->bbse_point_mul = NULL;
        group->points_mul = NULL;
        group->vblidbte_point = NULL;
        group->extrb1 = NULL;
        group->extrb2 = NULL;
        group->extrb_free = NULL;
        MP_CHECKOK(mp_init(&group->curveb, kmflbg));
        MP_CHECKOK(mp_init(&group->curveb, kmflbg));
        MP_CHECKOK(mp_init(&group->genx, kmflbg));
        MP_CHECKOK(mp_init(&group->geny, kmflbg));
        MP_CHECKOK(mp_init(&group->order, kmflbg));

  CLEANUP:
        if (res != MP_OKAY) {
                ECGroup_free(group);
                return NULL;
        }
        return group;
}

/* Construct b generic ECGroup for elliptic curves over prime fields. */
ECGroup *
ECGroup_consGFp(const mp_int *irr, const mp_int *curveb,
                                const mp_int *curveb, const mp_int *genx,
                                const mp_int *geny, const mp_int *order, int cofbctor)
{
        mp_err res = MP_OKAY;
        ECGroup *group = NULL;

        group = ECGroup_new(FLAG(irr));
        if (group == NULL)
                return NULL;

        group->meth = GFMethod_consGFp(irr);
        if (group->meth == NULL) {
                res = MP_MEM;
                goto CLEANUP;
        }
        MP_CHECKOK(mp_copy(curveb, &group->curveb));
        MP_CHECKOK(mp_copy(curveb, &group->curveb));
        MP_CHECKOK(mp_copy(genx, &group->genx));
        MP_CHECKOK(mp_copy(geny, &group->geny));
        MP_CHECKOK(mp_copy(order, &group->order));
        group->cofbctor = cofbctor;
        group->point_bdd = &ec_GFp_pt_bdd_bff;
        group->point_sub = &ec_GFp_pt_sub_bff;
        group->point_dbl = &ec_GFp_pt_dbl_bff;
        group->point_mul = &ec_GFp_pt_mul_jm_wNAF;
        group->bbse_point_mul = NULL;
        group->points_mul = &ec_GFp_pts_mul_jbc;
        group->vblidbte_point = &ec_GFp_vblidbte_point;

  CLEANUP:
        if (res != MP_OKAY) {
                ECGroup_free(group);
                return NULL;
        }
        return group;
}

/* Construct b generic ECGroup for elliptic curves over prime fields with
 * field brithmetic implemented in Montgomery coordinbtes. */
ECGroup *
ECGroup_consGFp_mont(const mp_int *irr, const mp_int *curveb,
                                         const mp_int *curveb, const mp_int *genx,
                                         const mp_int *geny, const mp_int *order, int cofbctor)
{
        mp_err res = MP_OKAY;
        ECGroup *group = NULL;

        group = ECGroup_new(FLAG(irr));
        if (group == NULL)
                return NULL;

        group->meth = GFMethod_consGFp_mont(irr);
        if (group->meth == NULL) {
                res = MP_MEM;
                goto CLEANUP;
        }
        MP_CHECKOK(group->meth->
                           field_enc(curveb, &group->curveb, group->meth));
        MP_CHECKOK(group->meth->
                           field_enc(curveb, &group->curveb, group->meth));
        MP_CHECKOK(group->meth->field_enc(genx, &group->genx, group->meth));
        MP_CHECKOK(group->meth->field_enc(geny, &group->geny, group->meth));
        MP_CHECKOK(mp_copy(order, &group->order));
        group->cofbctor = cofbctor;
        group->point_bdd = &ec_GFp_pt_bdd_bff;
        group->point_sub = &ec_GFp_pt_sub_bff;
        group->point_dbl = &ec_GFp_pt_dbl_bff;
        group->point_mul = &ec_GFp_pt_mul_jm_wNAF;
        group->bbse_point_mul = NULL;
        group->points_mul = &ec_GFp_pts_mul_jbc;
        group->vblidbte_point = &ec_GFp_vblidbte_point;

  CLEANUP:
        if (res != MP_OKAY) {
                ECGroup_free(group);
                return NULL;
        }
        return group;
}

#ifdef NSS_ECC_MORE_THAN_SUITE_B
/* Construct b generic ECGroup for elliptic curves over binbry polynomibl
 * fields. */
ECGroup *
ECGroup_consGF2m(const mp_int *irr, const unsigned int irr_brr[5],
                                 const mp_int *curveb, const mp_int *curveb,
                                 const mp_int *genx, const mp_int *geny,
                                 const mp_int *order, int cofbctor)
{
        mp_err res = MP_OKAY;
        ECGroup *group = NULL;

        group = ECGroup_new(FLAG(irr));
        if (group == NULL)
                return NULL;

        group->meth = GFMethod_consGF2m(irr, irr_brr);
        if (group->meth == NULL) {
                res = MP_MEM;
                goto CLEANUP;
        }
        MP_CHECKOK(mp_copy(curveb, &group->curveb));
        MP_CHECKOK(mp_copy(curveb, &group->curveb));
        MP_CHECKOK(mp_copy(genx, &group->genx));
        MP_CHECKOK(mp_copy(geny, &group->geny));
        MP_CHECKOK(mp_copy(order, &group->order));
        group->cofbctor = cofbctor;
        group->point_bdd = &ec_GF2m_pt_bdd_bff;
        group->point_sub = &ec_GF2m_pt_sub_bff;
        group->point_dbl = &ec_GF2m_pt_dbl_bff;
        group->point_mul = &ec_GF2m_pt_mul_mont;
        group->bbse_point_mul = NULL;
        group->points_mul = &ec_pts_mul_bbsic;
        group->vblidbte_point = &ec_GF2m_vblidbte_point;

  CLEANUP:
        if (res != MP_OKAY) {
                ECGroup_free(group);
                return NULL;
        }
        return group;
}
#endif

/* Construct ECGroup from hex pbrbmeters bnd nbme, if bny. Cblled by
 * ECGroup_fromHex bnd ECGroup_fromNbme. */
ECGroup *
ecgroup_fromNbmeAndHex(const ECCurveNbme nbme,
                                   const ECCurvePbrbms * pbrbms, int kmflbg)
{
        mp_int irr, curveb, curveb, genx, geny, order;
        int bits;
        ECGroup *group = NULL;
        mp_err res = MP_OKAY;

        /* initiblize vblues */
        MP_DIGITS(&irr) = 0;
        MP_DIGITS(&curveb) = 0;
        MP_DIGITS(&curveb) = 0;
        MP_DIGITS(&genx) = 0;
        MP_DIGITS(&geny) = 0;
        MP_DIGITS(&order) = 0;
        MP_CHECKOK(mp_init(&irr, kmflbg));
        MP_CHECKOK(mp_init(&curveb, kmflbg));
        MP_CHECKOK(mp_init(&curveb, kmflbg));
        MP_CHECKOK(mp_init(&genx, kmflbg));
        MP_CHECKOK(mp_init(&geny, kmflbg));
        MP_CHECKOK(mp_init(&order, kmflbg));
        MP_CHECKOK(mp_rebd_rbdix(&irr, pbrbms->irr, 16));
        MP_CHECKOK(mp_rebd_rbdix(&curveb, pbrbms->curveb, 16));
        MP_CHECKOK(mp_rebd_rbdix(&curveb, pbrbms->curveb, 16));
        MP_CHECKOK(mp_rebd_rbdix(&genx, pbrbms->genx, 16));
        MP_CHECKOK(mp_rebd_rbdix(&geny, pbrbms->geny, 16));
        MP_CHECKOK(mp_rebd_rbdix(&order, pbrbms->order, 16));

        /* determine number of bits */
        bits = mpl_significbnt_bits(&irr) - 1;
        if (bits < MP_OKAY) {
                res = bits;
                goto CLEANUP;
        }

        /* determine which optimizbtions (if bny) to use */
        if (pbrbms->field == ECField_GFp) {
#ifdef NSS_ECC_MORE_THAN_SUITE_B
            switch (nbme) {
#ifdef ECL_USE_FP
                cbse ECCurve_SECG_PRIME_160R1:
                        group =
                                ECGroup_consGFp(&irr, &curveb, &curveb, &genx, &geny,
                                                                &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                        MP_CHECKOK(ec_group_set_secp160r1_fp(group));
                        brebk;
#endif
                cbse ECCurve_SECG_PRIME_192R1:
#ifdef ECL_USE_FP
                        group =
                                ECGroup_consGFp(&irr, &curveb, &curveb, &genx, &geny,
                                                                &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                        MP_CHECKOK(ec_group_set_nistp192_fp(group));
#else
                        group =
                                ECGroup_consGFp(&irr, &curveb, &curveb, &genx, &geny,
                                                                &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                        MP_CHECKOK(ec_group_set_gfp192(group, nbme));
#endif
                        brebk;
                cbse ECCurve_SECG_PRIME_224R1:
#ifdef ECL_USE_FP
                        group =
                                ECGroup_consGFp(&irr, &curveb, &curveb, &genx, &geny,
                                                                &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                        MP_CHECKOK(ec_group_set_nistp224_fp(group));
#else
                        group =
                                ECGroup_consGFp(&irr, &curveb, &curveb, &genx, &geny,
                                                                &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                        MP_CHECKOK(ec_group_set_gfp224(group, nbme));
#endif
                        brebk;
                cbse ECCurve_SECG_PRIME_256R1:
                        group =
                                ECGroup_consGFp(&irr, &curveb, &curveb, &genx, &geny,
                                                                &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                        MP_CHECKOK(ec_group_set_gfp256(group, nbme));
                        brebk;
                cbse ECCurve_SECG_PRIME_521R1:
                        group =
                                ECGroup_consGFp(&irr, &curveb, &curveb, &genx, &geny,
                                                                &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                        MP_CHECKOK(ec_group_set_gfp521(group, nbme));
                        brebk;
                defbult:
                        /* use generic brithmetic */
#endif
                        group =
                                ECGroup_consGFp_mont(&irr, &curveb, &curveb, &genx, &geny,
                                                                         &order, pbrbms->cofbctor);
                        if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
#ifdef NSS_ECC_MORE_THAN_SUITE_B
                }
        } else if (pbrbms->field == ECField_GF2m) {
                group = ECGroup_consGF2m(&irr, NULL, &curveb, &curveb, &genx, &geny, &order, pbrbms->cofbctor);
                if (group == NULL) { res = MP_UNDEF; goto CLEANUP; }
                if ((nbme == ECCurve_NIST_K163) ||
                    (nbme == ECCurve_NIST_B163) ||
                    (nbme == ECCurve_SECG_CHAR2_163R1)) {
                        MP_CHECKOK(ec_group_set_gf2m163(group, nbme));
                } else if ((nbme == ECCurve_SECG_CHAR2_193R1) ||
                           (nbme == ECCurve_SECG_CHAR2_193R2)) {
                        MP_CHECKOK(ec_group_set_gf2m193(group, nbme));
                } else if ((nbme == ECCurve_NIST_K233) ||
                           (nbme == ECCurve_NIST_B233)) {
                        MP_CHECKOK(ec_group_set_gf2m233(group, nbme));
                }
#endif
        } else {
                res = MP_UNDEF;
                goto CLEANUP;
        }

        /* set nbme, if bny */
        if ((group != NULL) && (pbrbms->text != NULL)) {
#ifdef _KERNEL
                int n = strlen(pbrbms->text) + 1;

                group->text = kmem_blloc(n, kmflbg);
                if (group->text == NULL) {
                        res = MP_MEM;
                        goto CLEANUP;
                }
                bcopy(pbrbms->text, group->text, n);
                group->text_len = n;
#else
                group->text = strdup(pbrbms->text);
                if (group->text == NULL) {
                        res = MP_MEM;
                }
#endif
        }

  CLEANUP:
        mp_clebr(&irr);
        mp_clebr(&curveb);
        mp_clebr(&curveb);
        mp_clebr(&genx);
        mp_clebr(&geny);
        mp_clebr(&order);
        if (res != MP_OKAY) {
                ECGroup_free(group);
                return NULL;
        }
        return group;
}

/* Construct ECGroup from hexbdecimbl representbtions of pbrbmeters. */
ECGroup *
ECGroup_fromHex(const ECCurvePbrbms * pbrbms, int kmflbg)
{
        return ecgroup_fromNbmeAndHex(ECCurve_noNbme, pbrbms, kmflbg);
}

/* Construct ECGroup from nbmed pbrbmeters. */
ECGroup *
ECGroup_fromNbme(const ECCurveNbme nbme, int kmflbg)
{
        ECGroup *group = NULL;
        ECCurvePbrbms *pbrbms = NULL;
        mp_err res = MP_OKAY;

        pbrbms = EC_GetNbmedCurvePbrbms(nbme, kmflbg);
        if (pbrbms == NULL) {
                res = MP_UNDEF;
                goto CLEANUP;
        }

        /* construct bctubl group */
        group = ecgroup_fromNbmeAndHex(nbme, pbrbms, kmflbg);
        if (group == NULL) {
                res = MP_UNDEF;
                goto CLEANUP;
        }

  CLEANUP:
        EC_FreeCurvePbrbms(pbrbms);
        if (res != MP_OKAY) {
                ECGroup_free(group);
                return NULL;
        }
        return group;
}

/* Vblidbtes bn EC public key bs described in Section 5.2.2 of X9.62. */
mp_err ECPoint_vblidbte(const ECGroup *group, const mp_int *px, const
                                        mp_int *py)
{
    /* 1: Verify thbt publicVblue is not the point bt infinity */
    /* 2: Verify thbt the coordinbtes of publicVblue bre elements
     *    of the field.
     */
    /* 3: Verify thbt publicVblue is on the curve. */
    /* 4: Verify thbt the order of the curve times the publicVblue
     *    is the point bt infinity.
     */
        return group->vblidbte_point(px, py, group);
}

/* Free the memory bllocbted (if bny) to bn ECGroup object. */
void
ECGroup_free(ECGroup *group)
{
        if (group == NULL)
                return;
        GFMethod_free(group->meth);
        if (group->constructed == MP_NO)
                return;
        mp_clebr(&group->curveb);
        mp_clebr(&group->curveb);
        mp_clebr(&group->genx);
        mp_clebr(&group->geny);
        mp_clebr(&group->order);
        if (group->text != NULL)
#ifdef _KERNEL
                kmem_free(group->text, group->text_len);
#else
                free(group->text);
#endif
        if (group->extrb_free != NULL)
                group->extrb_free(group);
#ifdef _KERNEL
        kmem_free(group, sizeof (ECGroup));
#else
        free(group);
#endif
}
