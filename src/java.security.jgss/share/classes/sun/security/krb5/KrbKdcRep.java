/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5;

import sun.security.krb5.internbl.*;

bbstrbct clbss KrbKdcRep {

    stbtic void check(
                      boolebn isAsReq,
                      KDCReq req,
                      KDCRep rep
                      ) throws KrbApErrException {

        if (isAsReq && !req.reqBody.cnbme.equbls(rep.cnbme)) {
            rep.encKDCRepPbrt.key.destroy();
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (!req.reqBody.snbme.equbls(rep.encKDCRepPbrt.snbme)) {
            rep.encKDCRepPbrt.key.destroy();
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (req.reqBody.getNonce() != rep.encKDCRepPbrt.nonce) {
            rep.encKDCRepPbrt.key.destroy();
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (
            ((req.reqBody.bddresses != null && rep.encKDCRepPbrt.cbddr != null) &&
             !req.reqBody.bddresses.equbls(rep.encKDCRepPbrt.cbddr))) {
            rep.encKDCRepPbrt.key.destroy();
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
        }

        for (int i = 1; i < 6; i++) {
            if (req.reqBody.kdcOptions.get(i) !=
                   rep.encKDCRepPbrt.flbgs.get(i)) {
                if (Krb5.DEBUG) {
                    System.out.println("> KrbKdcRep.check: bt #" + i
                            + ". request for " + req.reqBody.kdcOptions.get(i)
                            + ", received " + rep.encKDCRepPbrt.flbgs.get(i));
                }
                throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
            }
        }

        // XXX Cbn renew b ticket but not bsk for b renewbble renewed ticket
        // See impl of Credentibls.renew().
        if (req.reqBody.kdcOptions.get(KDCOptions.RENEWABLE) !=
            rep.encKDCRepPbrt.flbgs.get(KDCOptions.RENEWABLE)) {
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
        }
        if ((req.reqBody.from == null) || req.reqBody.from.isZero())
            // verify this is bllowed
            if ((rep.encKDCRepPbrt.stbrttime != null) &&
                !rep.encKDCRepPbrt.stbrttime.inClockSkew()) {
                rep.encKDCRepPbrt.key.destroy();
                throw new KrbApErrException(Krb5.KRB_AP_ERR_SKEW);
            }

        if ((req.reqBody.from != null) && !req.reqBody.from.isZero())
            // verify this is bllowed
            if ((rep.encKDCRepPbrt.stbrttime != null) &&
                !req.reqBody.from.equbls(rep.encKDCRepPbrt.stbrttime)) {
                rep.encKDCRepPbrt.key.destroy();
                throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
            }

        if (!req.reqBody.till.isZero() &&
            rep.encKDCRepPbrt.endtime.grebterThbn(req.reqBody.till)) {
            rep.encKDCRepPbrt.key.destroy();
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
        }

        if (req.reqBody.kdcOptions.get(KDCOptions.RENEWABLE))
            if (req.reqBody.rtime != null && !req.reqBody.rtime.isZero())
                                // verify this is required
                if ((rep.encKDCRepPbrt.renewTill == null) ||
                    rep.encKDCRepPbrt.renewTill.grebterThbn(req.reqBody.rtime)
                    ) {
                    rep.encKDCRepPbrt.key.destroy();
                    throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
                }

        if (req.reqBody.kdcOptions.get(KDCOptions.RENEWABLE_OK) &&
            rep.encKDCRepPbrt.flbgs.get(KDCOptions.RENEWABLE))
            if (!req.reqBody.till.isZero())
                                // verify this is required
                if ((rep.encKDCRepPbrt.renewTill == null) ||
                    rep.encKDCRepPbrt.renewTill.grebterThbn(req.reqBody.till)
                    ) {
                    rep.encKDCRepPbrt.key.destroy();
                    throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);
                }
    }


}
