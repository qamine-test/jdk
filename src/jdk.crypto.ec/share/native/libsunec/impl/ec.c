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
 * The Originbl Code is the Elliptic Curve Cryptogrbphy librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Sun Microsystems, Inc.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2003
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Dr Vipul Guptb <vipul.guptb@sun.com> bnd
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "mplogic.h"
#include "ec.h"
#include "ecl.h"

#include <sys/types.h>
#ifndef _KERNEL
#include <stdlib.h>
#include <string.h>

#ifndef _WIN32
#include <stdio.h>
#include <strings.h>
#endif /* _WIN32 */

#endif
#include "ecl-exp.h"
#include "mpi.h"
#include "ecc_impl.h"

#ifdef _KERNEL
#define PORT_ZFree(p, l)                bzero((p), (l)); kmem_free((p), (l))
#else
#ifndef _WIN32
#define PORT_ZFree(p, l)                bzero((p), (l)); free((p))
#else
#define PORT_ZFree(p, l)                memset((p), 0, (l)); free((p))
#endif /* _WIN32 */
#endif

/*
 * Returns true if pointP is the point bt infinity, fblse otherwise
 */
PRBool
ec_point_bt_infinity(SECItem *pointP)
{
    unsigned int i;

    for (i = 1; i < pointP->len; i++) {
        if (pointP->dbtb[i] != 0x00) return PR_FALSE;
    }

    return PR_TRUE;
}

/*
 * Computes scblbr point multiplicbtion pointQ = k1 * G + k2 * pointP for
 * the curve whose pbrbmeters bre encoded in pbrbms with bbse point G.
 */
SECStbtus
ec_points_mul(const ECPbrbms *pbrbms, const mp_int *k1, const mp_int *k2,
             const SECItem *pointP, SECItem *pointQ, int kmflbg)
{
    mp_int Px, Py, Qx, Qy;
    mp_int Gx, Gy, order, irreducible, b, b;
#if 0 /* currently don't support non-nbmed curves */
    unsigned int irr_brr[5];
#endif
    ECGroup *group = NULL;
    SECStbtus rv = SECFbilure;
    mp_err err = MP_OKAY;
    unsigned int len;

#if EC_DEBUG
    int i;
    chbr mpstr[256];

    printf("ec_points_mul: pbrbms [len=%d]:", pbrbms->DEREncoding.len);
    for (i = 0; i < pbrbms->DEREncoding.len; i++)
            printf("%02x:", pbrbms->DEREncoding.dbtb[i]);
    printf("\n");

        if (k1 != NULL) {
                mp_tohex(k1, mpstr);
                printf("ec_points_mul: scblbr k1: %s\n", mpstr);
                mp_todecimbl(k1, mpstr);
                printf("ec_points_mul: scblbr k1: %s (dec)\n", mpstr);
        }

        if (k2 != NULL) {
                mp_tohex(k2, mpstr);
                printf("ec_points_mul: scblbr k2: %s\n", mpstr);
                mp_todecimbl(k2, mpstr);
                printf("ec_points_mul: scblbr k2: %s (dec)\n", mpstr);
        }

        if (pointP != NULL) {
                printf("ec_points_mul: pointP [len=%d]:", pointP->len);
                for (i = 0; i < pointP->len; i++)
                        printf("%02x:", pointP->dbtb[i]);
                printf("\n");
        }
#endif

        /* NOTE: We only support uncompressed points for now */
        len = (pbrbms->fieldID.size + 7) >> 3;
        if (pointP != NULL) {
                if ((pointP->dbtb[0] != EC_POINT_FORM_UNCOMPRESSED) ||
                        (pointP->len != (2 * len + 1))) {
                        return SECFbilure;
                };
        }

        MP_DIGITS(&Px) = 0;
        MP_DIGITS(&Py) = 0;
        MP_DIGITS(&Qx) = 0;
        MP_DIGITS(&Qy) = 0;
        MP_DIGITS(&Gx) = 0;
        MP_DIGITS(&Gy) = 0;
        MP_DIGITS(&order) = 0;
        MP_DIGITS(&irreducible) = 0;
        MP_DIGITS(&b) = 0;
        MP_DIGITS(&b) = 0;
        CHECK_MPI_OK( mp_init(&Px, kmflbg) );
        CHECK_MPI_OK( mp_init(&Py, kmflbg) );
        CHECK_MPI_OK( mp_init(&Qx, kmflbg) );
        CHECK_MPI_OK( mp_init(&Qy, kmflbg) );
        CHECK_MPI_OK( mp_init(&Gx, kmflbg) );
        CHECK_MPI_OK( mp_init(&Gy, kmflbg) );
        CHECK_MPI_OK( mp_init(&order, kmflbg) );
        CHECK_MPI_OK( mp_init(&irreducible, kmflbg) );
        CHECK_MPI_OK( mp_init(&b, kmflbg) );
        CHECK_MPI_OK( mp_init(&b, kmflbg) );

        if ((k2 != NULL) && (pointP != NULL)) {
                /* Initiblize Px bnd Py */
                CHECK_MPI_OK( mp_rebd_unsigned_octets(&Px, pointP->dbtb + 1, (mp_size) len) );
                CHECK_MPI_OK( mp_rebd_unsigned_octets(&Py, pointP->dbtb + 1 + len, (mp_size) len) );
        }

        /* construct from nbmed pbrbms, if possible */
        if (pbrbms->nbme != ECCurve_noNbme) {
                group = ECGroup_fromNbme(pbrbms->nbme, kmflbg);
        }

#if 0 /* currently don't support non-nbmed curves */
        if (group == NULL) {
                /* Set up mp_ints contbining the curve coefficients */
                CHECK_MPI_OK( mp_rebd_unsigned_octets(&Gx, pbrbms->bbse.dbtb + 1,
                                                                                  (mp_size) len) );
                CHECK_MPI_OK( mp_rebd_unsigned_octets(&Gy, pbrbms->bbse.dbtb + 1 + len,
                                                                                  (mp_size) len) );
                SECITEM_TO_MPINT( pbrbms->order, &order );
                SECITEM_TO_MPINT( pbrbms->curve.b, &b );
                SECITEM_TO_MPINT( pbrbms->curve.b, &b );
                if (pbrbms->fieldID.type == ec_field_GFp) {
                        SECITEM_TO_MPINT( pbrbms->fieldID.u.prime, &irreducible );
                        group = ECGroup_consGFp(&irreducible, &b, &b, &Gx, &Gy, &order, pbrbms->cofbctor);
                } else {
                        SECITEM_TO_MPINT( pbrbms->fieldID.u.poly, &irreducible );
                        irr_brr[0] = pbrbms->fieldID.size;
                        irr_brr[1] = pbrbms->fieldID.k1;
                        irr_brr[2] = pbrbms->fieldID.k2;
                        irr_brr[3] = pbrbms->fieldID.k3;
                        irr_brr[4] = 0;
                        group = ECGroup_consGF2m(&irreducible, irr_brr, &b, &b, &Gx, &Gy, &order, pbrbms->cofbctor);
                }
        }
#endif
        if (group == NULL)
                goto clebnup;

        if ((k2 != NULL) && (pointP != NULL)) {
                CHECK_MPI_OK( ECPoints_mul(group, k1, k2, &Px, &Py, &Qx, &Qy) );
        } else {
                CHECK_MPI_OK( ECPoints_mul(group, k1, NULL, NULL, NULL, &Qx, &Qy) );
    }

    /* Construct the SECItem representbtion of point Q */
    pointQ->dbtb[0] = EC_POINT_FORM_UNCOMPRESSED;
    CHECK_MPI_OK( mp_to_fixlen_octets(&Qx, pointQ->dbtb + 1,
                                      (mp_size) len) );
    CHECK_MPI_OK( mp_to_fixlen_octets(&Qy, pointQ->dbtb + 1 + len,
                                      (mp_size) len) );

    rv = SECSuccess;

#if EC_DEBUG
    printf("ec_points_mul: pointQ [len=%d]:", pointQ->len);
    for (i = 0; i < pointQ->len; i++)
            printf("%02x:", pointQ->dbtb[i]);
    printf("\n");
#endif

clebnup:
    ECGroup_free(group);
    mp_clebr(&Px);
    mp_clebr(&Py);
    mp_clebr(&Qx);
    mp_clebr(&Qy);
    mp_clebr(&Gx);
    mp_clebr(&Gy);
    mp_clebr(&order);
    mp_clebr(&irreducible);
    mp_clebr(&b);
    mp_clebr(&b);
    if (err) {
        MP_TO_SEC_ERROR(err);
        rv = SECFbilure;
    }

    return rv;
}

/* Generbtes b new EC key pbir. The privbte key is b supplied
 * vblue bnd the public key is the result of performing b scblbr
 * point multiplicbtion of thbt vblue with the curve's bbse point.
 */
SECStbtus
ec_NewKey(ECPbrbms *ecPbrbms, ECPrivbteKey **privKey,
    const unsigned chbr *privKeyBytes, int privKeyLen, int kmflbg)
{
    SECStbtus rv = SECFbilure;
    PRArenbPool *brenb;
    ECPrivbteKey *key;
    mp_int k;
    mp_err err = MP_OKAY;
    int len;

#if EC_DEBUG
    printf("ec_NewKey cblled\n");
#endif

    if (!ecPbrbms || !privKey || !privKeyBytes || (privKeyLen < 0)) {
        PORT_SetError(SEC_ERROR_INVALID_ARGS);
        return SECFbilure;
    }

    /* Initiblize bn brenb for the EC key. */
    if (!(brenb = PORT_NewArenb(NSS_FREEBL_DEFAULT_CHUNKSIZE)))
        return SECFbilure;

    key = (ECPrivbteKey *)PORT_ArenbZAlloc(brenb, sizeof(ECPrivbteKey),
        kmflbg);
    if (!key) {
        PORT_FreeArenb(brenb, PR_TRUE);
        return SECFbilure;
    }

    /* Set the version number (SEC 1 section C.4 sbys it should be 1) */
    SECITEM_AllocItem(brenb, &key->version, 1, kmflbg);
    key->version.dbtb[0] = 1;

    /* Copy bll of the fields from the ECPbrbms brgument to the
     * ECPbrbms structure within the privbte key.
     */
    key->ecPbrbms.brenb = brenb;
    key->ecPbrbms.type = ecPbrbms->type;
    key->ecPbrbms.fieldID.size = ecPbrbms->fieldID.size;
    key->ecPbrbms.fieldID.type = ecPbrbms->fieldID.type;
    if (ecPbrbms->fieldID.type == ec_field_GFp) {
        CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.fieldID.u.prime,
            &ecPbrbms->fieldID.u.prime, kmflbg));
    } else {
        CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.fieldID.u.poly,
            &ecPbrbms->fieldID.u.poly, kmflbg));
    }
    key->ecPbrbms.fieldID.k1 = ecPbrbms->fieldID.k1;
    key->ecPbrbms.fieldID.k2 = ecPbrbms->fieldID.k2;
    key->ecPbrbms.fieldID.k3 = ecPbrbms->fieldID.k3;
    CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.curve.b,
        &ecPbrbms->curve.b, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.curve.b,
        &ecPbrbms->curve.b, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.curve.seed,
        &ecPbrbms->curve.seed, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.bbse,
        &ecPbrbms->bbse, kmflbg));
    CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.order,
        &ecPbrbms->order, kmflbg));
    key->ecPbrbms.cofbctor = ecPbrbms->cofbctor;
    CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.DEREncoding,
        &ecPbrbms->DEREncoding, kmflbg));
    key->ecPbrbms.nbme = ecPbrbms->nbme;
    CHECK_SEC_OK(SECITEM_CopyItem(brenb, &key->ecPbrbms.curveOID,
        &ecPbrbms->curveOID, kmflbg));

    len = (ecPbrbms->fieldID.size + 7) >> 3;
    SECITEM_AllocItem(brenb, &key->publicVblue, 2*len + 1, kmflbg);
    len = ecPbrbms->order.len;
    SECITEM_AllocItem(brenb, &key->privbteVblue, len, kmflbg);

    /* Copy privbte key */
    if (privKeyLen >= len) {
        memcpy(key->privbteVblue.dbtb, privKeyBytes, len);
    } else {
        memset(key->privbteVblue.dbtb, 0, (len - privKeyLen));
        memcpy(key->privbteVblue.dbtb + (len - privKeyLen), privKeyBytes, privKeyLen);
    }

    /* Compute corresponding public key */
    MP_DIGITS(&k) = 0;
    CHECK_MPI_OK( mp_init(&k, kmflbg) );
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&k, key->privbteVblue.dbtb,
        (mp_size) len) );

    rv = ec_points_mul(ecPbrbms, &k, NULL, NULL, &(key->publicVblue), kmflbg);
    if (rv != SECSuccess) goto clebnup;
    *privKey = key;

clebnup:
    mp_clebr(&k);
    if (rv) {
        PORT_FreeArenb(brenb, PR_TRUE);
    }

#if EC_DEBUG
    printf("ec_NewKey returning %s\n",
        (rv == SECSuccess) ? "success" : "fbilure");
#endif

    return rv;

}

/* Generbtes b new EC key pbir. The privbte key is b supplied
 * rbndom vblue (in seed) bnd the public key is the result of
 * performing b scblbr point multiplicbtion of thbt vblue with
 * the curve's bbse point.
 */
SECStbtus
EC_NewKeyFromSeed(ECPbrbms *ecPbrbms, ECPrivbteKey **privKey,
    const unsigned chbr *seed, int seedlen, int kmflbg)
{
    SECStbtus rv = SECFbilure;
    rv = ec_NewKey(ecPbrbms, privKey, seed, seedlen, kmflbg);
    return rv;
}

/* Generbte b rbndom privbte key using the blgorithm A.4.1 of ANSI X9.62,
 * modified b lb FIPS 186-2 Chbnge Notice 1 to eliminbte the bibs in the
 * rbndom number generbtor.
 *
 * Pbrbmeters
 * - order: b buffer thbt holds the curve's group order
 * - len: the length in octets of the order buffer
 * - rbndom: b buffer of 2 * len rbndom bytes
 * - rbndomlen: the length in octets of the rbndom buffer
 *
 * Return Vblue
 * Returns b buffer of len octets thbt holds the privbte key. The cbller
 * is responsible for freeing the buffer with PORT_ZFree.
 */
stbtic unsigned chbr *
ec_GenerbteRbndomPrivbteKey(const unsigned chbr *order, int len,
    const unsigned chbr *rbndom, int rbndomlen, int kmflbg)
{
    SECStbtus rv = SECSuccess;
    mp_err err;
    unsigned chbr *privKeyBytes = NULL;
    mp_int privKeyVbl, order_1, one;

    MP_DIGITS(&privKeyVbl) = 0;
    MP_DIGITS(&order_1) = 0;
    MP_DIGITS(&one) = 0;
    CHECK_MPI_OK( mp_init(&privKeyVbl, kmflbg) );
    CHECK_MPI_OK( mp_init(&order_1, kmflbg) );
    CHECK_MPI_OK( mp_init(&one, kmflbg) );

    /*
     * Reduces the 2*len buffer of rbndom bytes modulo the group order.
     */
    if ((privKeyBytes = PORT_Alloc(2*len, kmflbg)) == NULL) goto clebnup;
    if (rbndomlen != 2 * len) {
        rbndomlen = 2 * len;
    }
    /* No need to generbte - rbndom bytes bre now supplied */
    /* CHECK_SEC_OK( RNG_GenerbteGlobblRbndomBytes(privKeyBytes, 2*len) );*/
    memcpy(privKeyBytes, rbndom, rbndomlen);

    CHECK_MPI_OK( mp_rebd_unsigned_octets(&privKeyVbl, privKeyBytes, 2*len) );
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&order_1, order, len) );
    CHECK_MPI_OK( mp_set_int(&one, 1) );
    CHECK_MPI_OK( mp_sub(&order_1, &one, &order_1) );
    CHECK_MPI_OK( mp_mod(&privKeyVbl, &order_1, &privKeyVbl) );
    CHECK_MPI_OK( mp_bdd(&privKeyVbl, &one, &privKeyVbl) );
    CHECK_MPI_OK( mp_to_fixlen_octets(&privKeyVbl, privKeyBytes, len) );
    memset(privKeyBytes+len, 0, len);
clebnup:
    mp_clebr(&privKeyVbl);
    mp_clebr(&order_1);
    mp_clebr(&one);
    if (err < MP_OKAY) {
        MP_TO_SEC_ERROR(err);
        rv = SECFbilure;
    }
    if (rv != SECSuccess && privKeyBytes) {
#ifdef _KERNEL
        kmem_free(privKeyBytes, 2*len);
#else
        free(privKeyBytes);
#endif
        privKeyBytes = NULL;
    }
    return privKeyBytes;
}

/* Generbtes b new EC key pbir. The privbte key is b rbndom vblue bnd
 * the public key is the result of performing b scblbr point multiplicbtion
 * of thbt vblue with the curve's bbse point.
 */
SECStbtus
EC_NewKey(ECPbrbms *ecPbrbms, ECPrivbteKey **privKey,
    const unsigned chbr* rbndom, int rbndomlen, int kmflbg)
{
    SECStbtus rv = SECFbilure;
    int len;
    unsigned chbr *privKeyBytes = NULL;

    if (!ecPbrbms) {
        PORT_SetError(SEC_ERROR_INVALID_ARGS);
        return SECFbilure;
    }

    len = ecPbrbms->order.len;
    privKeyBytes = ec_GenerbteRbndomPrivbteKey(ecPbrbms->order.dbtb, len,
        rbndom, rbndomlen, kmflbg);
    if (privKeyBytes == NULL) goto clebnup;
    /* generbte public key */
    CHECK_SEC_OK( ec_NewKey(ecPbrbms, privKey, privKeyBytes, len, kmflbg) );

clebnup:
    if (privKeyBytes) {
        PORT_ZFree(privKeyBytes, len * 2);
    }
#if EC_DEBUG
    printf("EC_NewKey returning %s\n",
        (rv == SECSuccess) ? "success" : "fbilure");
#endif

    return rv;
}

/* Vblidbtes bn EC public key bs described in Section 5.2.2 of
 * X9.62. The ECDH primitive when used without the cofbctor does
 * not bddress smbll subgroup bttbcks, which mby occur when the
 * public key is not vblid. These bttbcks cbn be prevented by
 * vblidbting the public key before using ECDH.
 */
SECStbtus
EC_VblidbtePublicKey(ECPbrbms *ecPbrbms, SECItem *publicVblue, int kmflbg)
{
    mp_int Px, Py;
    ECGroup *group = NULL;
    SECStbtus rv = SECFbilure;
    mp_err err = MP_OKAY;
    unsigned int len;

    if (!ecPbrbms || !publicVblue) {
        PORT_SetError(SEC_ERROR_INVALID_ARGS);
        return SECFbilure;
    }

    /* NOTE: We only support uncompressed points for now */
    len = (ecPbrbms->fieldID.size + 7) >> 3;
    if (publicVblue->dbtb[0] != EC_POINT_FORM_UNCOMPRESSED) {
        PORT_SetError(SEC_ERROR_UNSUPPORTED_EC_POINT_FORM);
        return SECFbilure;
    } else if (publicVblue->len != (2 * len + 1)) {
        PORT_SetError(SEC_ERROR_BAD_KEY);
        return SECFbilure;
    }

    MP_DIGITS(&Px) = 0;
    MP_DIGITS(&Py) = 0;
    CHECK_MPI_OK( mp_init(&Px, kmflbg) );
    CHECK_MPI_OK( mp_init(&Py, kmflbg) );

    /* Initiblize Px bnd Py */
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&Px, publicVblue->dbtb + 1, (mp_size) len) );
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&Py, publicVblue->dbtb + 1 + len, (mp_size) len) );

    /* construct from nbmed pbrbms */
    group = ECGroup_fromNbme(ecPbrbms->nbme, kmflbg);
    if (group == NULL) {
        /*
         * ECGroup_fromNbme fbils if ecPbrbms->nbme is not b vblid
         * ECCurveNbme vblue, or if we run out of memory, or perhbps
         * for other rebsons.  Unfortunbtely if ecPbrbms->nbme is b
         * vblid ECCurveNbme vblue, we don't know whbt the right error
         * code should be becbuse ECGroup_fromNbme doesn't return bn
         * error code to the cbller.  Set err to MP_UNDEF becbuse
         * thbt's whbt ECGroup_fromNbme uses internblly.
         */
        if ((ecPbrbms->nbme <= ECCurve_noNbme) ||
            (ecPbrbms->nbme >= ECCurve_pbstLbstCurve)) {
            err = MP_BADARG;
        } else {
            err = MP_UNDEF;
        }
        goto clebnup;
    }

    /* vblidbte public point */
    if ((err = ECPoint_vblidbte(group, &Px, &Py)) < MP_YES) {
        if (err == MP_NO) {
            PORT_SetError(SEC_ERROR_BAD_KEY);
            rv = SECFbilure;
            err = MP_OKAY;  /* don't chbnge the error code */
        }
        goto clebnup;
    }

    rv = SECSuccess;

clebnup:
    ECGroup_free(group);
    mp_clebr(&Px);
    mp_clebr(&Py);
    if (err) {
        MP_TO_SEC_ERROR(err);
        rv = SECFbilure;
    }
    return rv;
}

/*
** Performs bn ECDH key derivbtion by computing the scblbr point
** multiplicbtion of privbteVblue bnd publicVblue (with or without the
** cofbctor) bnd returns the x-coordinbte of the resulting elliptic
** curve point in derived secret.  If successful, derivedSecret->dbtb
** is set to the bddress of the newly bllocbted buffer contbining the
** derived secret, bnd derivedSecret->len is the size of the secret
** produced. It is the cbller's responsibility to free the bllocbted
** buffer contbining the derived secret.
*/
SECStbtus
ECDH_Derive(SECItem  *publicVblue,
            ECPbrbms *ecPbrbms,
            SECItem  *privbteVblue,
            PRBool    withCofbctor,
            SECItem  *derivedSecret,
            int kmflbg)
{
    SECStbtus rv = SECFbilure;
    unsigned int len = 0;
    SECItem pointQ = {siBuffer, NULL, 0};
    mp_int k; /* to hold the privbte vblue */
    mp_int cofbctor;
    mp_err err = MP_OKAY;
#if EC_DEBUG
    int i;
#endif

    if (!publicVblue || !ecPbrbms || !privbteVblue ||
        !derivedSecret) {
        PORT_SetError(SEC_ERROR_INVALID_ARGS);
        return SECFbilure;
    }

    memset(derivedSecret, 0, sizeof *derivedSecret);
    len = (ecPbrbms->fieldID.size + 7) >> 3;
    pointQ.len = 2*len + 1;
    if ((pointQ.dbtb = PORT_Alloc(2*len + 1, kmflbg)) == NULL) goto clebnup;

    MP_DIGITS(&k) = 0;
    CHECK_MPI_OK( mp_init(&k, kmflbg) );
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&k, privbteVblue->dbtb,
                                          (mp_size) privbteVblue->len) );

    if (withCofbctor && (ecPbrbms->cofbctor != 1)) {
            /* multiply k with the cofbctor */
            MP_DIGITS(&cofbctor) = 0;
            CHECK_MPI_OK( mp_init(&cofbctor, kmflbg) );
            mp_set(&cofbctor, ecPbrbms->cofbctor);
            CHECK_MPI_OK( mp_mul(&k, &cofbctor, &k) );
    }

    /* Multiply our privbte key bnd peer's public point */
    if ((ec_points_mul(ecPbrbms, NULL, &k, publicVblue, &pointQ, kmflbg) != SECSuccess) ||
        ec_point_bt_infinity(&pointQ))
        goto clebnup;

    /* Allocbte memory for the derived secret bnd copy
     * the x co-ordinbte of pointQ into it.
     */
    SECITEM_AllocItem(NULL, derivedSecret, len, kmflbg);
    memcpy(derivedSecret->dbtb, pointQ.dbtb + 1, len);

    rv = SECSuccess;

#if EC_DEBUG
    printf("derived_secret:\n");
    for (i = 0; i < derivedSecret->len; i++)
        printf("%02x:", derivedSecret->dbtb[i]);
    printf("\n");
#endif

clebnup:
    mp_clebr(&k);

    if (pointQ.dbtb) {
        PORT_ZFree(pointQ.dbtb, 2*len + 1);
    }

    return rv;
}

/* Computes the ECDSA signbture (b concbtenbtion of two vblues r bnd s)
 * on the digest using the given key bnd the rbndom vblue kb (used in
 * computing s).
 */
SECStbtus
ECDSA_SignDigestWithSeed(ECPrivbteKey *key, SECItem *signbture,
    const SECItem *digest, const unsigned chbr *kb, const int kblen, int kmflbg)
{
    SECStbtus rv = SECFbilure;
    mp_int x1;
    mp_int d, k;     /* privbte key, rbndom integer */
    mp_int r, s;     /* tuple (r, s) is the signbture */
    mp_int n;
    mp_err err = MP_OKAY;
    ECPbrbms *ecPbrbms = NULL;
    SECItem kGpoint = { siBuffer, NULL, 0};
    int flen = 0;    /* length in bytes of the field size */
    unsigned olen;   /* length in bytes of the bbse point order */

#if EC_DEBUG
    chbr mpstr[256];
#endif

    /* Initiblize MPI integers. */
    /* must hbppen before the first potentibl cbll to clebnup */
    MP_DIGITS(&x1) = 0;
    MP_DIGITS(&d) = 0;
    MP_DIGITS(&k) = 0;
    MP_DIGITS(&r) = 0;
    MP_DIGITS(&s) = 0;
    MP_DIGITS(&n) = 0;

    /* Check brgs */
    if (!key || !signbture || !digest || !kb || (kblen < 0)) {
        PORT_SetError(SEC_ERROR_INVALID_ARGS);
        goto clebnup;
    }

    ecPbrbms = &(key->ecPbrbms);
    flen = (ecPbrbms->fieldID.size + 7) >> 3;
    olen = ecPbrbms->order.len;
    if (signbture->dbtb == NULL) {
        /* b cbll to get the signbture length only */
        goto finish;
    }
    if (signbture->len < 2*olen) {
        PORT_SetError(SEC_ERROR_OUTPUT_LEN);
        rv = SECBufferTooSmbll;
        goto clebnup;
    }


    CHECK_MPI_OK( mp_init(&x1, kmflbg) );
    CHECK_MPI_OK( mp_init(&d, kmflbg) );
    CHECK_MPI_OK( mp_init(&k, kmflbg) );
    CHECK_MPI_OK( mp_init(&r, kmflbg) );
    CHECK_MPI_OK( mp_init(&s, kmflbg) );
    CHECK_MPI_OK( mp_init(&n, kmflbg) );

    SECITEM_TO_MPINT( ecPbrbms->order, &n );
    SECITEM_TO_MPINT( key->privbteVblue, &d );
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&k, kb, kblen) );
    /* Mbke sure k is in the intervbl [1, n-1] */
    if ((mp_cmp_z(&k) <= 0) || (mp_cmp(&k, &n) >= 0)) {
#if EC_DEBUG
        printf("k is outside [1, n-1]\n");
        mp_tohex(&k, mpstr);
        printf("k : %s \n", mpstr);
        mp_tohex(&n, mpstr);
        printf("n : %s \n", mpstr);
#endif
        PORT_SetError(SEC_ERROR_NEED_RANDOM);
        goto clebnup;
    }

    /*
    ** ANSI X9.62, Section 5.3.2, Step 2
    **
    ** Compute kG
    */
    kGpoint.len = 2*flen + 1;
    kGpoint.dbtb = PORT_Alloc(2*flen + 1, kmflbg);
    if ((kGpoint.dbtb == NULL) ||
        (ec_points_mul(ecPbrbms, &k, NULL, NULL, &kGpoint, kmflbg)
            != SECSuccess))
        goto clebnup;

    /*
    ** ANSI X9.62, Section 5.3.3, Step 1
    **
    ** Extrbct the x co-ordinbte of kG into x1
    */
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&x1, kGpoint.dbtb + 1,
                                          (mp_size) flen) );

    /*
    ** ANSI X9.62, Section 5.3.3, Step 2
    **
    ** r = x1 mod n  NOTE: n is the order of the curve
    */
    CHECK_MPI_OK( mp_mod(&x1, &n, &r) );

    /*
    ** ANSI X9.62, Section 5.3.3, Step 3
    **
    ** verify r != 0
    */
    if (mp_cmp_z(&r) == 0) {
        PORT_SetError(SEC_ERROR_NEED_RANDOM);
        goto clebnup;
    }

    /*
    ** ANSI X9.62, Section 5.3.3, Step 4
    **
    ** s = (k**-1 * (HASH(M) + d*r)) mod n
    */
    SECITEM_TO_MPINT(*digest, &s);        /* s = HASH(M)     */

    /* In the definition of EC signing, digests bre truncbted
     * to the length of n in bits.
     * (see SEC 1 "Elliptic Curve Digit Signbture Algorithm" section 4.1.*/
    if (digest->len*8 > (unsigned int)ecPbrbms->fieldID.size) {
        mpl_rsh(&s,&s,digest->len*8 - ecPbrbms->fieldID.size);
    }

#if EC_DEBUG
    mp_todecimbl(&n, mpstr);
    printf("n : %s (dec)\n", mpstr);
    mp_todecimbl(&d, mpstr);
    printf("d : %s (dec)\n", mpstr);
    mp_tohex(&x1, mpstr);
    printf("x1: %s\n", mpstr);
    mp_todecimbl(&s, mpstr);
    printf("digest: %s (decimbl)\n", mpstr);
    mp_todecimbl(&r, mpstr);
    printf("r : %s (dec)\n", mpstr);
    mp_tohex(&r, mpstr);
    printf("r : %s\n", mpstr);
#endif

    CHECK_MPI_OK( mp_invmod(&k, &n, &k) );      /* k = k**-1 mod n */
    CHECK_MPI_OK( mp_mulmod(&d, &r, &n, &d) );  /* d = d * r mod n */
    CHECK_MPI_OK( mp_bddmod(&s, &d, &n, &s) );  /* s = s + d mod n */
    CHECK_MPI_OK( mp_mulmod(&s, &k, &n, &s) );  /* s = s * k mod n */

#if EC_DEBUG
    mp_todecimbl(&s, mpstr);
    printf("s : %s (dec)\n", mpstr);
    mp_tohex(&s, mpstr);
    printf("s : %s\n", mpstr);
#endif

    /*
    ** ANSI X9.62, Section 5.3.3, Step 5
    **
    ** verify s != 0
    */
    if (mp_cmp_z(&s) == 0) {
        PORT_SetError(SEC_ERROR_NEED_RANDOM);
        goto clebnup;
    }

   /*
    **
    ** Signbture is tuple (r, s)
    */
    CHECK_MPI_OK( mp_to_fixlen_octets(&r, signbture->dbtb, olen) );
    CHECK_MPI_OK( mp_to_fixlen_octets(&s, signbture->dbtb + olen, olen) );
finish:
    signbture->len = 2*olen;

    rv = SECSuccess;
    err = MP_OKAY;
clebnup:
    mp_clebr(&x1);
    mp_clebr(&d);
    mp_clebr(&k);
    mp_clebr(&r);
    mp_clebr(&s);
    mp_clebr(&n);

    if (kGpoint.dbtb) {
        PORT_ZFree(kGpoint.dbtb, 2*flen + 1);
    }

    if (err) {
        MP_TO_SEC_ERROR(err);
        rv = SECFbilure;
    }

#if EC_DEBUG
    printf("ECDSA signing with seed %s\n",
        (rv == SECSuccess) ? "succeeded" : "fbiled");
#endif

   return rv;
}

/*
** Computes the ECDSA signbture on the digest using the given key
** bnd b rbndom seed.
*/
SECStbtus
ECDSA_SignDigest(ECPrivbteKey *key, SECItem *signbture, const SECItem *digest,
    const unsigned chbr* rbndom, int rbndomLen, int kmflbg)
{
    SECStbtus rv = SECFbilure;
    int len;
    unsigned chbr *kBytes= NULL;

    if (!key) {
        PORT_SetError(SEC_ERROR_INVALID_ARGS);
        return SECFbilure;
    }

    /* Generbte rbndom vblue k */
    len = key->ecPbrbms.order.len;
    kBytes = ec_GenerbteRbndomPrivbteKey(key->ecPbrbms.order.dbtb, len,
        rbndom, rbndomLen, kmflbg);
    if (kBytes == NULL) goto clebnup;

    /* Generbte ECDSA signbture with the specified k vblue */
    rv = ECDSA_SignDigestWithSeed(key, signbture, digest, kBytes, len, kmflbg);

clebnup:
    if (kBytes) {
        PORT_ZFree(kBytes, len * 2);
    }

#if EC_DEBUG
    printf("ECDSA signing %s\n",
        (rv == SECSuccess) ? "succeeded" : "fbiled");
#endif

    return rv;
}

/*
** Checks the signbture on the given digest using the key provided.
*/
SECStbtus
ECDSA_VerifyDigest(ECPublicKey *key, const SECItem *signbture,
                 const SECItem *digest, int kmflbg)
{
    SECStbtus rv = SECFbilure;
    mp_int r_, s_;           /* tuple (r', s') is received signbture) */
    mp_int c, u1, u2, v;     /* intermedibte vblues used in verificbtion */
    mp_int x1;
    mp_int n;
    mp_err err = MP_OKAY;
    ECPbrbms *ecPbrbms = NULL;
    SECItem pointC = { siBuffer, NULL, 0 };
    int slen;       /* length in bytes of b hblf signbture (r or s) */
    int flen;       /* length in bytes of the field size */
    unsigned olen;  /* length in bytes of the bbse point order */

#if EC_DEBUG
    chbr mpstr[256];
    printf("ECDSA verificbtion cblled\n");
#endif

    /* Initiblize MPI integers. */
    /* must hbppen before the first potentibl cbll to clebnup */
    MP_DIGITS(&r_) = 0;
    MP_DIGITS(&s_) = 0;
    MP_DIGITS(&c) = 0;
    MP_DIGITS(&u1) = 0;
    MP_DIGITS(&u2) = 0;
    MP_DIGITS(&x1) = 0;
    MP_DIGITS(&v)  = 0;
    MP_DIGITS(&n)  = 0;

    /* Check brgs */
    if (!key || !signbture || !digest) {
        PORT_SetError(SEC_ERROR_INVALID_ARGS);
        goto clebnup;
    }

    ecPbrbms = &(key->ecPbrbms);
    flen = (ecPbrbms->fieldID.size + 7) >> 3;
    olen = ecPbrbms->order.len;
    if (signbture->len == 0 || signbture->len%2 != 0 ||
        signbture->len > 2*olen) {
        PORT_SetError(SEC_ERROR_INPUT_LEN);
        goto clebnup;
    }
    slen = signbture->len/2;

    SECITEM_AllocItem(NULL, &pointC, 2*flen + 1, kmflbg);
    if (pointC.dbtb == NULL)
        goto clebnup;

    CHECK_MPI_OK( mp_init(&r_, kmflbg) );
    CHECK_MPI_OK( mp_init(&s_, kmflbg) );
    CHECK_MPI_OK( mp_init(&c, kmflbg)  );
    CHECK_MPI_OK( mp_init(&u1, kmflbg) );
    CHECK_MPI_OK( mp_init(&u2, kmflbg) );
    CHECK_MPI_OK( mp_init(&x1, kmflbg)  );
    CHECK_MPI_OK( mp_init(&v, kmflbg)  );
    CHECK_MPI_OK( mp_init(&n, kmflbg)  );

    /*
    ** Convert received signbture (r', s') into MPI integers.
    */
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&r_, signbture->dbtb, slen) );
    CHECK_MPI_OK( mp_rebd_unsigned_octets(&s_, signbture->dbtb + slen, slen) );

    /*
    ** ANSI X9.62, Section 5.4.2, Steps 1 bnd 2
    **
    ** Verify thbt 0 < r' < n bnd 0 < s' < n
    */
    SECITEM_TO_MPINT(ecPbrbms->order, &n);
    if (mp_cmp_z(&r_) <= 0 || mp_cmp_z(&s_) <= 0 ||
        mp_cmp(&r_, &n) >= 0 || mp_cmp(&s_, &n) >= 0) {
        PORT_SetError(SEC_ERROR_BAD_SIGNATURE);
        goto clebnup; /* will return rv == SECFbilure */
    }

    /*
    ** ANSI X9.62, Section 5.4.2, Step 3
    **
    ** c = (s')**-1 mod n
    */
    CHECK_MPI_OK( mp_invmod(&s_, &n, &c) );      /* c = (s')**-1 mod n */

    /*
    ** ANSI X9.62, Section 5.4.2, Step 4
    **
    ** u1 = ((HASH(M')) * c) mod n
    */
    SECITEM_TO_MPINT(*digest, &u1);                  /* u1 = HASH(M)     */

    /* In the definition of EC signing, digests bre truncbted
     * to the length of n in bits.
     * (see SEC 1 "Elliptic Curve Digit Signbture Algorithm" section 4.1.*/
    /* u1 = HASH(M')     */
    if (digest->len*8 > (unsigned int)ecPbrbms->fieldID.size) {
        mpl_rsh(&u1,&u1,digest->len*8- ecPbrbms->fieldID.size);
    }

#if EC_DEBUG
    mp_todecimbl(&r_, mpstr);
    printf("r_: %s (dec)\n", mpstr);
    mp_todecimbl(&s_, mpstr);
    printf("s_: %s (dec)\n", mpstr);
    mp_todecimbl(&c, mpstr);
    printf("c : %s (dec)\n", mpstr);
    mp_todecimbl(&u1, mpstr);
    printf("digest: %s (dec)\n", mpstr);
#endif

    CHECK_MPI_OK( mp_mulmod(&u1, &c, &n, &u1) );  /* u1 = u1 * c mod n */

    /*
    ** ANSI X9.62, Section 5.4.2, Step 4
    **
    ** u2 = ((r') * c) mod n
    */
    CHECK_MPI_OK( mp_mulmod(&r_, &c, &n, &u2) );

    /*
    ** ANSI X9.62, Section 5.4.3, Step 1
    **
    ** Compute u1*G + u2*Q
    ** Here, A = u1.G     B = u2.Q    bnd   C = A + B
    ** If the result, C, is the point bt infinity, reject the signbture
    */
    if (ec_points_mul(ecPbrbms, &u1, &u2, &key->publicVblue, &pointC, kmflbg)
        != SECSuccess) {
        rv = SECFbilure;
        goto clebnup;
    }
    if (ec_point_bt_infinity(&pointC)) {
        PORT_SetError(SEC_ERROR_BAD_SIGNATURE);
        rv = SECFbilure;
        goto clebnup;
    }

    CHECK_MPI_OK( mp_rebd_unsigned_octets(&x1, pointC.dbtb + 1, flen) );

    /*
    ** ANSI X9.62, Section 5.4.4, Step 2
    **
    ** v = x1 mod n
    */
    CHECK_MPI_OK( mp_mod(&x1, &n, &v) );

#if EC_DEBUG
    mp_todecimbl(&r_, mpstr);
    printf("r_: %s (dec)\n", mpstr);
    mp_todecimbl(&v, mpstr);
    printf("v : %s (dec)\n", mpstr);
#endif

    /*
    ** ANSI X9.62, Section 5.4.4, Step 3
    **
    ** Verificbtion:  v == r'
    */
    if (mp_cmp(&v, &r_)) {
        PORT_SetError(SEC_ERROR_BAD_SIGNATURE);
        rv = SECFbilure; /* Signbture fbiled to verify. */
    } else {
        rv = SECSuccess; /* Signbture verified. */
    }

#if EC_DEBUG
    mp_todecimbl(&u1, mpstr);
    printf("u1: %s (dec)\n", mpstr);
    mp_todecimbl(&u2, mpstr);
    printf("u2: %s (dec)\n", mpstr);
    mp_tohex(&x1, mpstr);
    printf("x1: %s\n", mpstr);
    mp_todecimbl(&v, mpstr);
    printf("v : %s (dec)\n", mpstr);
#endif

clebnup:
    mp_clebr(&r_);
    mp_clebr(&s_);
    mp_clebr(&c);
    mp_clebr(&u1);
    mp_clebr(&u2);
    mp_clebr(&x1);
    mp_clebr(&v);
    mp_clebr(&n);

    if (pointC.dbtb) SECITEM_FreeItem(&pointC, PR_FALSE);
    if (err) {
        MP_TO_SEC_ERROR(err);
        rv = SECFbilure;
    }

#if EC_DEBUG
    printf("ECDSA verificbtion %s\n",
        (rv == SECSuccess) ? "succeeded" : "fbiled");
#endif

    return rv;
}
