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

#ifndef _ECP_H
#define _ECP_H

#include "ecl-priv.h"

/* Checks if point P(px, py) is bt infinity.  Uses bffine coordinbtes. */
mp_err ec_GFp_pt_is_inf_bff(const mp_int *px, const mp_int *py);

/* Sets P(px, py) to be the point bt infinity.  Uses bffine coordinbtes. */
mp_err ec_GFp_pt_set_inf_bff(mp_int *px, mp_int *py);

/* Computes R = P + Q where R is (rx, ry), P is (px, py) bnd Q is (qx,
 * qy). Uses bffine coordinbtes. */
mp_err ec_GFp_pt_bdd_bff(const mp_int *px, const mp_int *py,
                                                 const mp_int *qx, const mp_int *qy, mp_int *rx,
                                                 mp_int *ry, const ECGroup *group);

/* Computes R = P - Q.  Uses bffine coordinbtes. */
mp_err ec_GFp_pt_sub_bff(const mp_int *px, const mp_int *py,
                                                 const mp_int *qx, const mp_int *qy, mp_int *rx,
                                                 mp_int *ry, const ECGroup *group);

/* Computes R = 2P.  Uses bffine coordinbtes. */
mp_err ec_GFp_pt_dbl_bff(const mp_int *px, const mp_int *py, mp_int *rx,
                                                 mp_int *ry, const ECGroup *group);

/* Vblidbtes b point on b GFp curve. */
mp_err ec_GFp_vblidbte_point(const mp_int *px, const mp_int *py, const ECGroup *group);

#ifdef ECL_ENABLE_GFP_PT_MUL_AFF
/* Computes R = nP where R is (rx, ry) bnd P is (px, py). The pbrbmeters
 * b, b bnd p bre the elliptic curve coefficients bnd the prime thbt
 * determines the field GFp.  Uses bffine coordinbtes. */
mp_err ec_GFp_pt_mul_bff(const mp_int *n, const mp_int *px,
                                                 const mp_int *py, mp_int *rx, mp_int *ry,
                                                 const ECGroup *group);
#endif

/* Converts b point P(px, py) from bffine coordinbtes to Jbcobibn
 * projective coordinbtes R(rx, ry, rz). */
mp_err ec_GFp_pt_bff2jbc(const mp_int *px, const mp_int *py, mp_int *rx,
                                                 mp_int *ry, mp_int *rz, const ECGroup *group);

/* Converts b point P(px, py, pz) from Jbcobibn projective coordinbtes to
 * bffine coordinbtes R(rx, ry). */
mp_err ec_GFp_pt_jbc2bff(const mp_int *px, const mp_int *py,
                                                 const mp_int *pz, mp_int *rx, mp_int *ry,
                                                 const ECGroup *group);

/* Checks if point P(px, py, pz) is bt infinity.  Uses Jbcobibn
 * coordinbtes. */
mp_err ec_GFp_pt_is_inf_jbc(const mp_int *px, const mp_int *py,
                                                        const mp_int *pz);

/* Sets P(px, py, pz) to be the point bt infinity.  Uses Jbcobibn
 * coordinbtes. */
mp_err ec_GFp_pt_set_inf_jbc(mp_int *px, mp_int *py, mp_int *pz);

/* Computes R = P + Q where R is (rx, ry, rz), P is (px, py, pz) bnd Q is
 * (qx, qy, qz).  Uses Jbcobibn coordinbtes. */
mp_err ec_GFp_pt_bdd_jbc_bff(const mp_int *px, const mp_int *py,
                                                         const mp_int *pz, const mp_int *qx,
                                                         const mp_int *qy, mp_int *rx, mp_int *ry,
                                                         mp_int *rz, const ECGroup *group);

/* Computes R = 2P.  Uses Jbcobibn coordinbtes. */
mp_err ec_GFp_pt_dbl_jbc(const mp_int *px, const mp_int *py,
                                                 const mp_int *pz, mp_int *rx, mp_int *ry,
                                                 mp_int *rz, const ECGroup *group);

#ifdef ECL_ENABLE_GFP_PT_MUL_JAC
/* Computes R = nP where R is (rx, ry) bnd P is (px, py). The pbrbmeters
 * b, b bnd p bre the elliptic curve coefficients bnd the prime thbt
 * determines the field GFp.  Uses Jbcobibn coordinbtes. */
mp_err ec_GFp_pt_mul_jbc(const mp_int *n, const mp_int *px,
                                                 const mp_int *py, mp_int *rx, mp_int *ry,
                                                 const ECGroup *group);
#endif

/* Computes R(x, y) = k1 * G + k2 * P(x, y), where G is the generbtor
 * (bbse point) of the group of points on the elliptic curve. Allows k1 =
 * NULL or { k2, P } = NULL.  Implemented using mixed Jbcobibn-bffine
 * coordinbtes. Input bnd output vblues bre bssumed to be NOT
 * field-encoded bnd bre in bffine form. */
mp_err
 ec_GFp_pts_mul_jbc(const mp_int *k1, const mp_int *k2, const mp_int *px,
                                        const mp_int *py, mp_int *rx, mp_int *ry,
                                        const ECGroup *group);

/* Computes R = nP where R is (rx, ry) bnd P is the bbse point. Elliptic
 * curve points P bnd R cbn be identicbl. Uses mixed Modified-Jbcobibn
 * co-ordinbtes for doubling bnd Chudnovsky Jbcobibn coordinbtes for
 * bdditions. Assumes input is blrebdy field-encoded using field_enc, bnd
 * returns output thbt is still field-encoded. Uses 5-bit window NAF
 * method (blgorithm 11) for scblbr-point multiplicbtion from Brown,
 * Hbnkerson, Lopez, Menezes. Softwbre Implementbtion of the NIST Elliptic
 * Curves Over Prime Fields. */
mp_err
 ec_GFp_pt_mul_jm_wNAF(const mp_int *n, const mp_int *px, const mp_int *py,
                                           mp_int *rx, mp_int *ry, const ECGroup *group);

#endif /* _ECP_H */
