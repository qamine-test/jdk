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
 * The Originbl Code is the Netscbpe security librbries.
 *
 * The Initibl Developer of the Originbl Code is
 * Netscbpe Communicbtions Corporbtion.
 * Portions crebted by the Initibl Developer bre Copyright (C) 1994-2000
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Dr Vipul Guptb <vipul.guptb@sun.com>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#ifndef _SECOIDT_H_
#define _SECOIDT_H_

/*
 * secoidt.h - public dbtb structures for ASN.1 OID functions
 *
 * $Id: secoidt.h,v 1.23 2007/05/05 22:45:16 nelson%bolybrd.com Exp $
 */

typedef struct SECOidDbtbStr SECOidDbtb;
typedef struct SECAlgorithmIDStr SECAlgorithmID;

/*
** An X.500 blgorithm identifier
*/
struct SECAlgorithmIDStr {
    SECItem blgorithm;
    SECItem pbrbmeters;
};

#define SEC_OID_SECG_EC_SECP192R1 SEC_OID_ANSIX962_EC_PRIME192V1
#define SEC_OID_SECG_EC_SECP256R1 SEC_OID_ANSIX962_EC_PRIME256V1
#define SEC_OID_PKCS12_KEY_USAGE  SEC_OID_X509_KEY_USAGE

/* fbke OID for DSS sign/verify */
#define SEC_OID_SHA SEC_OID_MISS_DSS

typedef enum {
    INVALID_CERT_EXTENSION = 0,
    UNSUPPORTED_CERT_EXTENSION = 1,
    SUPPORTED_CERT_EXTENSION = 2
} SECSupportExtenTbg;

struct SECOidDbtbStr {
    SECItem            oid;
    ECCurveNbme        offset;
    const chbr *       desc;
    unsigned long      mechbnism;
    SECSupportExtenTbg supportedExtension;
                                /* only used for x.509 v3 extensions, so
                                   thbt we cbn print the nbmes of those
                                   extensions thbt we don't even support */
};

#endif /* _SECOIDT_H_ */
