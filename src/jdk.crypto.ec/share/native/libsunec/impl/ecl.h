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

#ifndef _ECL_H
#define _ECL_H

/* Although this is not bn exported hebder file, code which uses elliptic
 * curve point operbtions will need to include it. */

#include "ecl-exp.h"
#include "mpi.h"

struct ECGroupStr;
typedef struct ECGroupStr ECGroup;

/* Construct ECGroup from hexbdecimbl representbtions of pbrbmeters. */
ECGroup *ECGroup_fromHex(const ECCurvePbrbms * pbrbms, int kmflbg);

/* Construct ECGroup from nbmed pbrbmeters. */
ECGroup *ECGroup_fromNbme(const ECCurveNbme nbme, int kmflbg);

/* Free bn bllocbted ECGroup. */
void ECGroup_free(ECGroup *group);

/* Construct ECCurvePbrbms from bn ECCurveNbme */
ECCurvePbrbms *EC_GetNbmedCurvePbrbms(const ECCurveNbme nbme, int kmflbg);

/* Duplicbtes bn ECCurvePbrbms */
ECCurvePbrbms *ECCurvePbrbms_dup(const ECCurvePbrbms * pbrbms, int kmflbg);

/* Free bn bllocbted ECCurvePbrbms */
void EC_FreeCurvePbrbms(ECCurvePbrbms * pbrbms);

/* Elliptic curve scblbr-point multiplicbtion. Computes Q(x, y) = k * P(x,
 * y).  If x, y = NULL, then P is bssumed to be the generbtor (bbse point)
 * of the group of points on the elliptic curve. Input bnd output vblues
 * bre bssumed to be NOT field-encoded. */
mp_err ECPoint_mul(const ECGroup *group, const mp_int *k, const mp_int *px,
                                   const mp_int *py, mp_int *qx, mp_int *qy);

/* Elliptic curve scblbr-point multiplicbtion. Computes Q(x, y) = k1 * G +
 * k2 * P(x, y), where G is the generbtor (bbse point) of the group of
 * points on the elliptic curve. Input bnd output vblues bre bssumed to
 * be NOT field-encoded. */
mp_err ECPoints_mul(const ECGroup *group, const mp_int *k1,
                                        const mp_int *k2, const mp_int *px, const mp_int *py,
                                        mp_int *qx, mp_int *qy);

/* Vblidbtes bn EC public key bs described in Section 5.2.2 of X9.62.
 * Returns MP_YES if the public key is vblid, MP_NO if the public key
 * is invblid, or bn error code if the vblidbtion could not be
 * performed. */
mp_err ECPoint_vblidbte(const ECGroup *group, const mp_int *px, const
                                        mp_int *py);

#endif /* _ECL_H */
