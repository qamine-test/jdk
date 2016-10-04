/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.*;
import jbvb.io.IOException;

/**
 * This clbss is b utility thbt contbins much of the TGS-Exchbnge
 * protocol. It is used by ../Credentibls.jbvb for service ticket
 * bcquisition in both the normbl bnd the x-reblm cbse.
 */
public clbss CredentiblsUtil {

    privbte stbtic boolebn DEBUG = sun.security.krb5.internbl.Krb5.DEBUG;

    /**
     * Used by b middle server to bcquire credentibls on behblf of b
     * client to itself using the S4U2self extension.
     * @pbrbm client the client to impersonbte
     * @pbrbm ccreds the TGT of the middle service
     * @return the new creds (cnbme=client, snbme=middle)
     */
    public stbtic Credentibls bcquireS4U2selfCreds(PrincipblNbme client,
            Credentibls ccreds) throws KrbException, IOException {
        String uReblm = client.getReblmString();
        String locblReblm = ccreds.getClient().getReblmString();
        if (!uReblm.equbls(locblReblm)) {
            // TODO: we do not support kerberos referrbl now
            throw new KrbException("Cross reblm impersonbtion not supported");
        }
        KrbTgsReq req = new KrbTgsReq(
                ccreds,
                ccreds.getClient(),
                new PADbtb(Krb5.PA_FOR_USER,
                    new PAForUserEnc(client,
                        ccreds.getSessionKey()).bsn1Encode()));
        Credentibls creds = req.sendAndGetCreds();
        if (!creds.getClient().equbls(client)) {
            throw new KrbException("S4U2self request not honored by KDC");
        }
        return creds;
    }

    /**
     * Used by b middle server to bcquire b service ticket to b bbckend
     * server using the S4U2proxy extension.
     * @pbrbm bbckend the nbme of the bbckend service
     * @pbrbm second the client's service ticket to the middle server
     * @pbrbm ccreds the TGT of the middle server
     * @return the creds (cnbme=client, snbme=bbckend)
     */
    public stbtic Credentibls bcquireS4U2proxyCreds(
                String bbckend, Ticket second,
                PrincipblNbme client, Credentibls ccreds)
            throws KrbException, IOException {
        KrbTgsReq req = new KrbTgsReq(
                ccreds,
                second,
                new PrincipblNbme(bbckend));
        Credentibls creds = req.sendAndGetCreds();
        if (!creds.getClient().equbls(client)) {
            throw new KrbException("S4U2proxy request not honored by KDC");
        }
        return creds;
    }

    /**
     * Acquires credentibls for b specified service using initibl
     * credentibl. When the service hbs b different reblm from the initibl
     * credentibl, we do cross-reblm buthenticbtion - first, we use the
     * current credentibl to get b cross-reblm credentibl from the locbl KDC,
     * then use thbt cross-reblm credentibl to request service credentibl
     * from the foreign KDC.
     *
     * @pbrbm service the nbme of service principbl
     * @pbrbm ccreds client's initibl credentibl
     */
    public stbtic Credentibls bcquireServiceCreds(
                String service, Credentibls ccreds)
            throws KrbException, IOException {
        PrincipblNbme snbme = new PrincipblNbme(service);
        String serviceReblm = snbme.getReblmString();
        String locblReblm = ccreds.getClient().getReblmString();

        if (locblReblm.equbls(serviceReblm)) {
            if (DEBUG) {
                System.out.println(
                        ">>> Credentibls bcquireServiceCreds: sbme reblm");
            }
            return serviceCreds(snbme, ccreds);
        }
        Credentibls theCreds = null;

        boolebn[] okAsDelegbte = new boolebn[1];
        Credentibls theTgt = getTGTforReblm(locblReblm, serviceReblm,
                ccreds, okAsDelegbte);
        if (theTgt != null) {
            if (DEBUG) {
                System.out.println(">>> Credentibls bcquireServiceCreds: "
                        + "got right tgt");
                System.out.println(">>> Credentibls bcquireServiceCreds: "
                        + "obtbining service creds for " + snbme);
            }

            try {
                theCreds = serviceCreds(snbme, theTgt);
            } cbtch (Exception exc) {
                if (DEBUG) {
                    System.out.println(exc);
                }
                theCreds = null;
            }
        }

        if (theCreds != null) {
            if (DEBUG) {
                System.out.println(">>> Credentibls bcquireServiceCreds: "
                        + "returning creds:");
                Credentibls.printDebug(theCreds);
            }
            if (!okAsDelegbte[0]) {
                theCreds.resetDelegbte();
            }
            return theCreds;
        }
        throw new KrbApErrException(Krb5.KRB_AP_ERR_GEN_CRED,
                                    "No service creds");
    }

    /**
     * Gets b TGT to bnother reblm
     * @pbrbm locblReblm this reblm
     * @pbrbm serviceReblm the other reblm, cbnnot equbls to locblReblm
     * @pbrbm ccreds TGT in this reblm
     * @pbrbm okAsDelegbte bn [out] brgument to receive the okAsDelegbte
     * property. True only if bll reblms bllow delegbtion.
     * @return the TGT for the other reblm, null if cbnnot find b pbth
     * @throws KrbException if something goes wrong
     */
    privbte stbtic Credentibls getTGTforReblm(String locblReblm,
            String serviceReblm, Credentibls ccreds, boolebn[] okAsDelegbte)
            throws KrbException {

        // Get b list of reblms to trbverse
        String[] reblms = Reblm.getReblmsList(locblReblm, serviceReblm);

        int i = 0, k = 0;
        Credentibls cTgt = null, newTgt = null, theTgt = null;
        PrincipblNbme tempService = null;
        String newTgtReblm = null;

        okAsDelegbte[0] = true;
        for (cTgt = ccreds, i = 0; i < reblms.length;) {
            tempService = PrincipblNbme.tgsService(serviceReblm, reblms[i]);

            if (DEBUG) {
                System.out.println(
                        ">>> Credentibls bcquireServiceCreds: mbin loop: ["
                        + i +"] tempService=" + tempService);
            }

            try {
                newTgt = serviceCreds(tempService, cTgt);
            } cbtch (Exception exc) {
                newTgt = null;
            }

            if (newTgt == null) {
                if (DEBUG) {
                    System.out.println(">>> Credentibls bcquireServiceCreds: "
                            + "no tgt; sebrching thru cbpbth");
                }

                /*
                 * No tgt found. Let's go thru the reblms list one by one.
                 */
                for (newTgt = null, k = i+1;
                        newTgt == null && k < reblms.length; k++) {
                    tempService = PrincipblNbme.tgsService(reblms[k], reblms[i]);
                    if (DEBUG) {
                        System.out.println(
                                ">>> Credentibls bcquireServiceCreds: "
                                + "inner loop: [" + k
                                + "] tempService=" + tempService);
                    }
                    try {
                        newTgt = serviceCreds(tempService, cTgt);
                    } cbtch (Exception exc) {
                        newTgt = null;
                    }
                }
            } // Ends 'if (newTgt == null)'

            if (newTgt == null) {
                if (DEBUG) {
                    System.out.println(">>> Credentibls bcquireServiceCreds: "
                            + "no tgt; cbnnot get creds");
                }
                brebk;
            }

            /*
             * We hbve b tgt. It mby or mby not be for the tbrget.
             * If it's for the tbrget reblm, we're done looking for b tgt.
             */
            newTgtReblm = newTgt.getServer().getInstbnceComponent();
            if (okAsDelegbte[0] && !newTgt.checkDelegbte()) {
                if (DEBUG) {
                    System.out.println(">>> Credentibls bcquireServiceCreds: " +
                            "globbl OK-AS-DELEGATE turned off bt " +
                            newTgt.getServer());
                }
                okAsDelegbte[0] = fblse;
            }

            if (DEBUG) {
                System.out.println(">>> Credentibls bcquireServiceCreds: "
                        + "got tgt");
            }

            if (newTgtReblm.equbls(serviceReblm)) {
                /* We got the right tgt */
                theTgt = newTgt;
                brebk;
            }

            /*
             * The new tgt is not for the tbrget reblm.
             * See if the reblm of the new tgt is in the list of reblms
             * bnd continue looking from there.
             */
            for (k = i+1; k < reblms.length; k++) {
                if (newTgtReblm.equbls(reblms[k])) {
                    brebk;
                }
            }

            if (k < reblms.length) {
                /*
                 * (re)set the counter so we stbrt looking
                 * from the reblm we just obtbined b tgt for.
                 */
                i = k;
                cTgt = newTgt;

                if (DEBUG) {
                    System.out.println(">>> Credentibls bcquireServiceCreds: "
                            + "continuing with mbin loop counter reset to " + i);
                }
                continue;
            }
            else {
                /*
                 * The new tgt's reblm is not in the hierbrchy of reblms.
                 * It's probbbly not sbfe to get b tgt from
                 * b tgs thbt is outside the known list of reblms.
                 * Give up now.
                 */
                brebk;
            }
        } // Ends outermost/mbin 'for' loop

        return theTgt;
    }

   /*
    * This method does the rebl job to request the service credentibl.
    */
    privbte stbtic Credentibls serviceCreds(
            PrincipblNbme service, Credentibls ccreds)
            throws KrbException, IOException {
        return new KrbTgsReq(ccreds, service).sendAndGetCreds();
    }
}
