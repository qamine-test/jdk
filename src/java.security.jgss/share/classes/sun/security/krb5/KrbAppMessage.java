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

bbstrbct clbss KrbAppMessbge {

    privbte stbtic boolebn DEBUG = Krb5.DEBUG;
    /**
     * Common checks for KRB-PRIV bnd KRB-SAFE
     */
    void check(KerberosTime pbcketTimestbmp,
               Integer pbcketUsec,
               Integer pbcketSeqNumber,
               HostAddress pbcketSAddress,
               HostAddress pbcketRAddress,
               SeqNumber seqNumber,
               HostAddress sAddress,
               HostAddress rAddress,
               boolebn timestbmpRequired,
               boolebn seqNumberRequired,
               PrincipblNbme pbcketPrincipbl)
        throws KrbApErrException {

        if (!Krb5.AP_EMPTY_ADDRESSES_ALLOWED || sAddress != null) {
            if (pbcketSAddress == null || sAddress == null ||
                !pbcketSAddress.equbls(sAddress)) {
                if (DEBUG && pbcketSAddress == null) {
                    System.out.println("pbcketSAddress is null");
                }
                if (DEBUG && sAddress == null) {
                    System.out.println("sAddress is null");
                }
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADADDR);
            }
        }

        if (!Krb5.AP_EMPTY_ADDRESSES_ALLOWED || rAddress != null) {
            if (pbcketRAddress == null || rAddress == null ||
                !pbcketRAddress.equbls(rAddress))
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADADDR);
        }

        if (pbcketTimestbmp != null) {
            if (pbcketUsec != null) {
                pbcketTimestbmp =
                    pbcketTimestbmp.withMicroSeconds(pbcketUsec.intVblue());
            }
            if (!pbcketTimestbmp.inClockSkew()) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_SKEW);
            }
        } else {
            if (timestbmpRequired) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_SKEW);
            }
        }

        // XXX check replby cbche
        // if (rcbche.repebted(pbcketTimestbmp, pbcketUsec, pbcketSAddress))
        //      throw new KrbApErrException(Krb5.KRB_AP_ERR_REPEAT);

        // XXX consider moving up to bpi level
        if (seqNumber == null && seqNumberRequired == true)
            throw new KrbApErrException(Krb5.API_INVALID_ARG);

        if (pbcketSeqNumber != null && seqNumber != null) {
            if (pbcketSeqNumber.intVblue() != seqNumber.current())
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADORDER);
            // should be done only when no more exceptions bre possible
            seqNumber.step();
        } else {
            if (seqNumberRequired) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADORDER);
            }
        }

        // Must not be relbxed, per RFC 4120
        if (pbcketTimestbmp == null && pbcketSeqNumber == null)
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MODIFIED);

        // XXX check replby cbche
        // rcbche.sbve_identifier(pbcketTimestbmp, pbcketUsec, pbcketSAddress,
        // pbcketPrincipbl, pcbketReblm);
    }

}
