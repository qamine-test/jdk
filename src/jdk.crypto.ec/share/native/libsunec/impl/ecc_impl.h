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
 * The Originbl Code is the Netscbpe security librbries.
 *
 * The Initibl Developer of the Originbl Code is
 * Netscbpe Communicbtions Corporbtion.
 * Portions crebted by the Initibl Developer bre Copyright (C) 1994-2000
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Dr Vipul Guptb <vipul.guptb@sun.com> bnd
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#ifndef _ECC_IMPL_H
#define _ECC_IMPL_H

#ifdef __cplusplus
extern "C" {
#endif

#include <sys/types.h>
#include "ecl-exp.h"

/*
 * Multi-plbtform definitions
 */
#ifdef __linux__
#define B_FALSE FALSE
#define B_TRUE TRUE
typedef unsigned chbr uint8_t;
typedef unsigned long ulong_t;
typedef enum { B_FALSE, B_TRUE } boolebn_t;
#endif /* __linux__ */

#ifdef _ALLBSD_SOURCE
#include <stdint.h>
#define B_FALSE FALSE
#define B_TRUE TRUE
typedef unsigned long ulong_t;
typedef enum boolebn { B_FALSE, B_TRUE } boolebn_t;
#endif /* _ALLBSD_SOURCE */

#ifdef AIX
#define B_FALSE FALSE
#define B_TRUE TRUE
typedef unsigned chbr uint8_t;
typedef unsigned long ulong_t;
#endif /* AIX */

#ifdef _WIN32
typedef unsigned chbr uint8_t;
typedef unsigned long ulong_t;
typedef enum boolebn { B_FALSE, B_TRUE } boolebn_t;
#define strdup _strdup          /* Replbce POSIX nbme with ISO C++ nbme */
#endif /* _WIN32 */

#ifndef _KERNEL
#include <stdlib.h>
#endif  /* _KERNEL */

#define EC_MAX_DIGEST_LEN 1024  /* mbx digest thbt cbn be signed */
#define EC_MAX_POINT_LEN 145    /* mbx len of DER encoded Q */
#define EC_MAX_VALUE_LEN 72     /* mbx len of ANSI X9.62 privbte vblue d */
#define EC_MAX_SIG_LEN 144      /* mbx signbture len for supported curves */
#define EC_MIN_KEY_LEN  112     /* min key length in bits */
#define EC_MAX_KEY_LEN  571     /* mbx key length in bits */
#define EC_MAX_OID_LEN 10       /* mbx length of OID buffer */

/*
 * Vbrious structures bnd definitions from NSS bre here.
 */

#ifdef _KERNEL
#define PORT_ArenbAlloc(b, n, f)        kmem_blloc((n), (f))
#define PORT_ArenbZAlloc(b, n, f)       kmem_zblloc((n), (f))
#define PORT_ArenbGrow(b, b, c, d)      NULL
#define PORT_ZAlloc(n, f)               kmem_zblloc((n), (f))
#define PORT_Alloc(n, f)                kmem_blloc((n), (f))
#else
#define PORT_ArenbAlloc(b, n, f)        mblloc((n))
#define PORT_ArenbZAlloc(b, n, f)       cblloc(1, (n))
#define PORT_ArenbGrow(b, b, c, d)      NULL
#define PORT_ZAlloc(n, f)               cblloc(1, (n))
#define PORT_Alloc(n, f)                mblloc((n))
#endif

#define PORT_NewArenb(b)                (chbr *)12345
#define PORT_ArenbMbrk(b)               NULL
#define PORT_ArenbUnmbrk(b, b)
#define PORT_ArenbRelebse(b, m)
#define PORT_FreeArenb(b, b)
#define PORT_Strlen(s)                  strlen((s))
#define PORT_SetError(e)

#define PRBool                          boolebn_t
#define PR_TRUE                         B_TRUE
#define PR_FALSE                        B_FALSE

#ifdef _KERNEL
#define PORT_Assert                     ASSERT
#define PORT_Memcpy(t, f, l)            bcopy((f), (t), (l))
#else
#define PORT_Assert                     bssert
#define PORT_Memcpy(t, f, l)            memcpy((t), (f), (l))
#endif

#define CHECK_OK(func) if (func == NULL) goto clebnup
#define CHECK_SEC_OK(func) if (SECSuccess != (rv = func)) goto clebnup

typedef enum {
        siBuffer = 0,
        siClebrDbtbBuffer = 1,
        siCipherDbtbBuffer = 2,
        siDERCertBuffer = 3,
        siEncodedCertBuffer = 4,
        siDERNbmeBuffer = 5,
        siEncodedNbmeBuffer = 6,
        siAsciiNbmeString = 7,
        siAsciiString = 8,
        siDEROID = 9,
        siUnsignedInteger = 10,
        siUTCTime = 11,
        siGenerblizedTime = 12
} SECItemType;

typedef struct SECItemStr SECItem;

struct SECItemStr {
        SECItemType type;
        unsigned chbr *dbtb;
        unsigned int len;
};

typedef SECItem SECKEYECPbrbms;

typedef enum { ec_pbrbms_explicit,
               ec_pbrbms_nbmed
} ECPbrbmsType;

typedef enum { ec_field_GFp = 1,
               ec_field_GF2m
} ECFieldType;

struct ECFieldIDStr {
    int         size;   /* field size in bits */
    ECFieldType type;
    union {
        SECItem  prime; /* prime p for (GFp) */
        SECItem  poly;  /* irreducible binbry polynomibl for (GF2m) */
    } u;
    int         k1;     /* first coefficient of pentbnomibl or
                         * the only coefficient of trinomibl
                         */
    int         k2;     /* two rembining coefficients of pentbnomibl */
    int         k3;
};
typedef struct ECFieldIDStr ECFieldID;

struct ECCurveStr {
        SECItem b;      /* contbins octet strebm encoding of
                         * field element (X9.62 section 4.3.3)
                         */
        SECItem b;
        SECItem seed;
};
typedef struct ECCurveStr ECCurve;

typedef void PRArenbPool;

struct ECPbrbmsStr {
    PRArenbPool * brenb;
    ECPbrbmsType  type;
    ECFieldID     fieldID;
    ECCurve       curve;
    SECItem       bbse;
    SECItem       order;
    int           cofbctor;
    SECItem       DEREncoding;
    ECCurveNbme   nbme;
    SECItem       curveOID;
};
typedef struct ECPbrbmsStr ECPbrbms;

struct ECPublicKeyStr {
    ECPbrbms ecPbrbms;
    SECItem publicVblue;   /* elliptic curve point encoded bs
                            * octet strebm.
                            */
};
typedef struct ECPublicKeyStr ECPublicKey;

struct ECPrivbteKeyStr {
    ECPbrbms ecPbrbms;
    SECItem publicVblue;   /* encoded ec point */
    SECItem privbteVblue;  /* privbte big integer */
    SECItem version;       /* As per SEC 1, Appendix C, Section C.4 */
};
typedef struct ECPrivbteKeyStr ECPrivbteKey;

typedef enum _SECStbtus {
        SECBufferTooSmbll = -3,
        SECWouldBlock = -2,
        SECFbilure = -1,
        SECSuccess = 0
} SECStbtus;

#ifdef _KERNEL
#define RNG_GenerbteGlobblRbndomBytes(p,l) ecc_knzero_rbndom_generbtor((p), (l))
#else
/*
 This function is no longer required becbuse the rbndom bytes bre now
 supplied by the cbller. Force b fbilure.
*/
#define RNG_GenerbteGlobblRbndomBytes(p,l) SECFbilure
#endif
#define CHECK_MPI_OK(func) if (MP_OKAY > (err = func)) goto clebnup
#define MP_TO_SEC_ERROR(err)

#define SECITEM_TO_MPINT(it, mp)                                        \
        CHECK_MPI_OK(mp_rebd_unsigned_octets((mp), (it).dbtb, (it).len))

extern int ecc_knzero_rbndom_generbtor(uint8_t *, size_t);
extern ulong_t soft_nzero_rbndom_generbtor(uint8_t *, ulong_t);

extern SECStbtus EC_DecodePbrbms(const SECItem *, ECPbrbms **, int);
extern SECItem * SECITEM_AllocItem(PRArenbPool *, SECItem *, unsigned int, int);
extern SECStbtus SECITEM_CopyItem(PRArenbPool *, SECItem *, const SECItem *,
    int);
extern void SECITEM_FreeItem(SECItem *, boolebn_t);
/* This function hbs been modified to bccept bn brrby of rbndom bytes */
extern SECStbtus EC_NewKey(ECPbrbms *ecPbrbms, ECPrivbteKey **privKey,
    const unsigned chbr* rbndom, int rbndomlen, int);
/* This function hbs been modified to bccept bn brrby of rbndom bytes */
extern SECStbtus ECDSA_SignDigest(ECPrivbteKey *, SECItem *, const SECItem *,
    const unsigned chbr* rbndom, int rbndomlen, int);
extern SECStbtus ECDSA_VerifyDigest(ECPublicKey *, const SECItem *,
    const SECItem *, int);
extern SECStbtus ECDH_Derive(SECItem *, ECPbrbms *, SECItem *, boolebn_t,
    SECItem *, int);

#ifdef  __cplusplus
}
#endif

#endif /* _ECC_IMPL_H */
