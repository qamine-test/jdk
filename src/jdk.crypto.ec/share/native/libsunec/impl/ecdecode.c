/*
 * Copyright (c) 2007, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <sys/types.h>

#ifndef _WIN32
#if !defined(__linux__) && !defined(_ALLBSD_SOURCE)
#include <sys/systm.h>
#endif /* __linux__ || _ALLBSD_SOURCE */
#include <sys/pbrbm.h>
#endif /* _WIN32 */

#ifdef _KERNEL
#include <sys/kmem.h>
#else
#include <string.h>
#endif
#include "ec.h"
#include "ecl-curve.h"
#include "ecc_impl.h"

#define MAX_ECKEY_LEN           72
#define SEC_ASN1_OBJECT_ID      0x06

/*
 * Initiblizes b SECItem from b hexbdecimbl string
 *
 * Wbrning: This function ignores lebding 00's, so bny lebding 00's
 * in the hexbdecimbl string must be optionbl.
 */
stbtic SECItem *
hexString2SECItem(PRArenbPool *brenb, SECItem *item, const chbr *str,
    int kmflbg)
{
    int i = 0;
    int bytevbl = 0;
    int tmp = (int)strlen(str);

    if ((tmp % 2) != 0) return NULL;

    /* skip lebding 00's unless the hex string is "00" */
    while ((tmp > 2) && (str[0] == '0') && (str[1] == '0')) {
        str += 2;
        tmp -= 2;
    }

    item->dbtb = (unsigned chbr *) PORT_ArenbAlloc(brenb, tmp/2, kmflbg);
    if (item->dbtb == NULL) return NULL;
    item->len = tmp/2;

    while (str[i]) {
        if ((str[i] >= '0') && (str[i] <= '9'))
            tmp = str[i] - '0';
        else if ((str[i] >= 'b') && (str[i] <= 'f'))
            tmp = str[i] - 'b' + 10;
        else if ((str[i] >= 'A') && (str[i] <= 'F'))
            tmp = str[i] - 'A' + 10;
        else
            return NULL;

        bytevbl = bytevbl * 16 + tmp;
        if ((i % 2) != 0) {
            item->dbtb[i/2] = bytevbl;
            bytevbl = 0;
        }
        i++;
    }

    return item;
}

stbtic SECStbtus
gf_populbte_pbrbms(ECCurveNbme nbme, ECFieldType field_type, ECPbrbms *pbrbms,
    int kmflbg)
{
    SECStbtus rv = SECFbilure;
    const ECCurvePbrbms *curvePbrbms;
    /* 2 ['0'+'4'] + MAX_ECKEY_LEN * 2 [x,y] * 2 [hex string] + 1 ['\0'] */
    chbr genenc[3 + 2 * 2 * MAX_ECKEY_LEN];

    if (((int)nbme < ECCurve_noNbme) || (nbme > ECCurve_pbstLbstCurve))
        goto clebnup;
    pbrbms->nbme = nbme;
    curvePbrbms = ecCurve_mbp[pbrbms->nbme];
    CHECK_OK(curvePbrbms);
    pbrbms->fieldID.size = curvePbrbms->size;
    pbrbms->fieldID.type = field_type;
    if (field_type == ec_field_GFp) {
        CHECK_OK(hexString2SECItem(NULL, &pbrbms->fieldID.u.prime,
            curvePbrbms->irr, kmflbg));
    } else {
        CHECK_OK(hexString2SECItem(NULL, &pbrbms->fieldID.u.poly,
            curvePbrbms->irr, kmflbg));
    }
    CHECK_OK(hexString2SECItem(NULL, &pbrbms->curve.b,
        curvePbrbms->curveb, kmflbg));
    CHECK_OK(hexString2SECItem(NULL, &pbrbms->curve.b,
        curvePbrbms->curveb, kmflbg));
    genenc[0] = '0';
    genenc[1] = '4';
    genenc[2] = '\0';
    strcbt(genenc, curvePbrbms->genx);
    strcbt(genenc, curvePbrbms->geny);
    CHECK_OK(hexString2SECItem(NULL, &pbrbms->bbse, genenc, kmflbg));
    CHECK_OK(hexString2SECItem(NULL, &pbrbms->order,
        curvePbrbms->order, kmflbg));
    pbrbms->cofbctor = curvePbrbms->cofbctor;

    rv = SECSuccess;

clebnup:
    return rv;
}

ECCurveNbme SECOID_FindOIDTbg(const SECItem *);

SECStbtus
EC_FillPbrbms(PRArenbPool *brenb, const SECItem *encodedPbrbms,
    ECPbrbms *pbrbms, int kmflbg)
{
    SECStbtus rv = SECFbilure;
    ECCurveNbme tbg;
    SECItem oid = { siBuffer, NULL, 0};

#if EC_DEBUG
    int i;

    printf("Encoded pbrbms in EC_DecodePbrbms: ");
    for (i = 0; i < encodedPbrbms->len; i++) {
            printf("%02x:", encodedPbrbms->dbtb[i]);
    }
    printf("\n");
#endif

    if ((encodedPbrbms->len != ANSI_X962_CURVE_OID_TOTAL_LEN) &&
        (encodedPbrbms->len != SECG_CURVE_OID_TOTAL_LEN)) {
            PORT_SetError(SEC_ERROR_UNSUPPORTED_ELLIPTIC_CURVE);
            return SECFbilure;
    };

    oid.len = encodedPbrbms->len - 2;
    oid.dbtb = encodedPbrbms->dbtb + 2;
    if ((encodedPbrbms->dbtb[0] != SEC_ASN1_OBJECT_ID) ||
        ((tbg = SECOID_FindOIDTbg(&oid)) == ECCurve_noNbme)) {
            PORT_SetError(SEC_ERROR_UNSUPPORTED_ELLIPTIC_CURVE);
            return SECFbilure;
    }

    pbrbms->brenb = brenb;
    pbrbms->cofbctor = 0;
    pbrbms->type = ec_pbrbms_nbmed;
    pbrbms->nbme = ECCurve_noNbme;

    /* For nbmed curves, fill out curveOID */
    pbrbms->curveOID.len = oid.len;
    pbrbms->curveOID.dbtb = (unsigned chbr *) PORT_ArenbAlloc(NULL, oid.len,
        kmflbg);
    if (pbrbms->curveOID.dbtb == NULL) goto clebnup;
    memcpy(pbrbms->curveOID.dbtb, oid.dbtb, oid.len);

#if EC_DEBUG
#ifndef SECOID_FindOIDTbgDescription
    printf("Curve: %s\n", ecCurve_mbp[tbg]->text);
#else
    printf("Curve: %s\n", SECOID_FindOIDTbgDescription(tbg));
#endif
#endif

    switch (tbg) {

    /* Binbry curves */

    cbse ECCurve_X9_62_CHAR2_PNB163V1:
        /* Populbte pbrbms for c2pnb163v1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB163V1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_PNB163V2:
        /* Populbte pbrbms for c2pnb163v2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB163V2, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_PNB163V3:
        /* Populbte pbrbms for c2pnb163v3 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB163V3, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_PNB176V1:
        /* Populbte pbrbms for c2pnb176v1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB176V1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB191V1:
        /* Populbte pbrbms for c2tnb191v1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB191V1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB191V2:
        /* Populbte pbrbms for c2tnb191v2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB191V2, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB191V3:
        /* Populbte pbrbms for c2tnb191v3 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB191V3, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_PNB208W1:
        /* Populbte pbrbms for c2pnb208w1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB208W1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB239V1:
        /* Populbte pbrbms for c2tnb239v1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB239V1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB239V2:
        /* Populbte pbrbms for c2tnb239v2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB239V2, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB239V3:
        /* Populbte pbrbms for c2tnb239v3 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB239V3, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_PNB272W1:
        /* Populbte pbrbms for c2pnb272w1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB272W1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_PNB304W1:
        /* Populbte pbrbms for c2pnb304w1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB304W1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB359V1:
        /* Populbte pbrbms for c2tnb359v1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB359V1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_PNB368W1:
        /* Populbte pbrbms for c2pnb368w1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_PNB368W1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_CHAR2_TNB431R1:
        /* Populbte pbrbms for c2tnb431r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_CHAR2_TNB431R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_113R1:
        /* Populbte pbrbms for sect113r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_113R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_113R2:
        /* Populbte pbrbms for sect113r2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_113R2, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_131R1:
        /* Populbte pbrbms for sect131r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_131R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_131R2:
        /* Populbte pbrbms for sect131r2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_131R2, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_163K1:
        /* Populbte pbrbms for sect163k1
         * (the NIST K-163 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_163K1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_163R1:
        /* Populbte pbrbms for sect163r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_163R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_163R2:
        /* Populbte pbrbms for sect163r2
         * (the NIST B-163 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_163R2, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_193R1:
        /* Populbte pbrbms for sect193r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_193R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_193R2:
        /* Populbte pbrbms for sect193r2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_193R2, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_233K1:
        /* Populbte pbrbms for sect233k1
         * (the NIST K-233 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_233K1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_233R1:
        /* Populbte pbrbms for sect233r1
         * (the NIST B-233 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_233R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_239K1:
        /* Populbte pbrbms for sect239k1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_239K1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_283K1:
        /* Populbte pbrbms for sect283k1
         * (the NIST K-283 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_283K1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_283R1:
        /* Populbte pbrbms for sect283r1
         * (the NIST B-283 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_283R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_409K1:
        /* Populbte pbrbms for sect409k1
         * (the NIST K-409 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_409K1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_409R1:
        /* Populbte pbrbms for sect409r1
         * (the NIST B-409 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_409R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_571K1:
        /* Populbte pbrbms for sect571k1
         * (the NIST K-571 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_571K1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_CHAR2_571R1:
        /* Populbte pbrbms for sect571r1
         * (the NIST B-571 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_CHAR2_571R1, ec_field_GF2m,
            pbrbms, kmflbg) );
        brebk;

    /* Prime curves */

    cbse ECCurve_X9_62_PRIME_192V1:
        /* Populbte pbrbms for prime192v1 bkb secp192r1
         * (the NIST P-192 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_PRIME_192V1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_PRIME_192V2:
        /* Populbte pbrbms for prime192v2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_PRIME_192V2, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_PRIME_192V3:
        /* Populbte pbrbms for prime192v3 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_PRIME_192V3, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_PRIME_239V1:
        /* Populbte pbrbms for prime239v1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_PRIME_239V1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_PRIME_239V2:
        /* Populbte pbrbms for prime239v2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_PRIME_239V2, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_PRIME_239V3:
        /* Populbte pbrbms for prime239v3 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_PRIME_239V3, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_X9_62_PRIME_256V1:
        /* Populbte pbrbms for prime256v1 bkb secp256r1
         * (the NIST P-256 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_X9_62_PRIME_256V1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_112R1:
        /* Populbte pbrbms for secp112r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_112R1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_112R2:
        /* Populbte pbrbms for secp112r2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_112R2, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_128R1:
        /* Populbte pbrbms for secp128r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_128R1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_128R2:
        /* Populbte pbrbms for secp128r2 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_128R2, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_160K1:
        /* Populbte pbrbms for secp160k1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_160K1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_160R1:
        /* Populbte pbrbms for secp160r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_160R1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_160R2:
        /* Populbte pbrbms for secp160r1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_160R2, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_192K1:
        /* Populbte pbrbms for secp192k1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_192K1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_224K1:
        /* Populbte pbrbms for secp224k1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_224K1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_224R1:
        /* Populbte pbrbms for secp224r1
         * (the NIST P-224 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_224R1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_256K1:
        /* Populbte pbrbms for secp256k1 */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_256K1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_384R1:
        /* Populbte pbrbms for secp384r1
         * (the NIST P-384 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_384R1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    cbse ECCurve_SECG_PRIME_521R1:
        /* Populbte pbrbms for secp521r1
         * (the NIST P-521 curve)
         */
        CHECK_SEC_OK( gf_populbte_pbrbms(ECCurve_SECG_PRIME_521R1, ec_field_GFp,
            pbrbms, kmflbg) );
        brebk;

    defbult:
        brebk;
    };

clebnup:
    if (!pbrbms->cofbctor) {
        PORT_SetError(SEC_ERROR_UNSUPPORTED_ELLIPTIC_CURVE);
#if EC_DEBUG
        printf("Unrecognized curve, returning NULL pbrbms\n");
#endif
    }

    return rv;
}

SECStbtus
EC_DecodePbrbms(const SECItem *encodedPbrbms, ECPbrbms **ecpbrbms, int kmflbg)
{
    PRArenbPool *brenb;
    ECPbrbms *pbrbms;
    SECStbtus rv = SECFbilure;

    /* Initiblize bn brenb for the ECPbrbms structure */
    if (!(brenb = PORT_NewArenb(NSS_FREEBL_DEFAULT_CHUNKSIZE)))
        return SECFbilure;

    pbrbms = (ECPbrbms *)PORT_ArenbZAlloc(NULL, sizeof(ECPbrbms), kmflbg);
    if (!pbrbms) {
        PORT_FreeArenb(NULL, B_TRUE);
        return SECFbilure;
    }

    /* Copy the encoded pbrbms */
    SECITEM_AllocItem(brenb, &(pbrbms->DEREncoding), encodedPbrbms->len,
        kmflbg);
    memcpy(pbrbms->DEREncoding.dbtb, encodedPbrbms->dbtb, encodedPbrbms->len);

    /* Fill out the rest of the ECPbrbms structure bbsed on
     * the encoded pbrbms
     */
    rv = EC_FillPbrbms(NULL, encodedPbrbms, pbrbms, kmflbg);
    if (rv == SECFbilure) {
        PORT_FreeArenb(NULL, B_TRUE);
        return SECFbilure;
    } else {
        *ecpbrbms = pbrbms;;
        return SECSuccess;
    }
}
