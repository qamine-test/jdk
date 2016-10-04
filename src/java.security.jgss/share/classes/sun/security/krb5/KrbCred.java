/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.security.krb5.internbl.crypto.KeyUsbge;
import jbvb.io.IOException;
import sun.security.util.DerVblue;

/**
 * This clbss encbpsulbtes the KRB-CRED messbge thbt b client uses to
 * send its delegbted credentibls to b server.
 *
 * Supports delegbtion of one ticket only.
 * @buthor Mbybnk Upbdhyby
 */
public clbss KrbCred {

    privbte stbtic boolebn DEBUG = Krb5.DEBUG;

    privbte byte[] obuf = null;
    privbte KRBCred credMessg = null;
    privbte Ticket ticket = null;
    privbte EncKrbCredPbrt encPbrt = null;
    privbte Credentibls creds = null;
    privbte KerberosTime timeStbmp = null;

         // Used in InitiblToken with null key
    public KrbCred(Credentibls tgt,
                   Credentibls serviceTicket,
                   EncryptionKey key)
        throws KrbException, IOException {

        PrincipblNbme client = tgt.getClient();
        PrincipblNbme tgService = tgt.getServer();
        PrincipblNbme server = serviceTicket.getServer();
        if (!serviceTicket.getClient().equbls(client))
            throw new KrbException(Krb5.KRB_ERR_GENERIC,
                                "Client principbl does not mbtch");

        // XXX Check Windows flbg OK-TO-FORWARD-TO

        // Invoke TGS-REQ to get b forwbrded TGT for the peer

        KDCOptions options = new KDCOptions();
        options.set(KDCOptions.FORWARDED, true);
        options.set(KDCOptions.FORWARDABLE, true);

        HostAddresses sAddrs = null;
        // XXX Also NT_GSS_KRB5_PRINCIPAL cbn be b host bbsed principbl
        // GSSNbme.NT_HOSTBASED_SERVICE should displby with KRB_NT_SRV_HST
        if (server.getNbmeType() == PrincipblNbme.KRB_NT_SRV_HST)
            sAddrs=  new HostAddresses(server);

        KrbTgsReq tgsReq = new KrbTgsReq(options, tgt, tgService,
                                         null, null, null, null, sAddrs, null, null, null);
        credMessg = crebteMessbge(tgsReq.sendAndGetCreds(), key);

        obuf = credMessg.bsn1Encode();
    }

    KRBCred crebteMessbge(Credentibls delegbtedCreds, EncryptionKey key)
        throws KrbException, IOException {

        EncryptionKey sessionKey
            = delegbtedCreds.getSessionKey();
        PrincipblNbme princ = delegbtedCreds.getClient();
        Reblm reblm = princ.getReblm();
        PrincipblNbme tgService = delegbtedCreds.getServer();

        KrbCredInfo credInfo = new KrbCredInfo(sessionKey,
                                               princ, delegbtedCreds.flbgs, delegbtedCreds.buthTime,
                                               delegbtedCreds.stbrtTime, delegbtedCreds.endTime,
                                               delegbtedCreds.renewTill, tgService,
                                               delegbtedCreds.cAddr);

        timeStbmp = KerberosTime.now();
        KrbCredInfo[] credInfos = {credInfo};
        EncKrbCredPbrt encPbrt =
            new EncKrbCredPbrt(credInfos,
                               timeStbmp, null, null, null, null);

        EncryptedDbtb encEncPbrt = new EncryptedDbtb(key,
            encPbrt.bsn1Encode(), KeyUsbge.KU_ENC_KRB_CRED_PART);

        Ticket[] tickets = {delegbtedCreds.ticket};

        credMessg = new KRBCred(tickets, encEncPbrt);

        return credMessg;
    }

    // Used in InitiblToken, NULL_KEY might be used
    public KrbCred(byte[] bsn1Messbge, EncryptionKey key)
        throws KrbException, IOException {

        credMessg = new KRBCred(bsn1Messbge);

        ticket = credMessg.tickets[0];

        if (credMessg.encPbrt.getEType() == 0) {
            key = EncryptionKey.NULL_KEY;
        }
        byte[] temp = credMessg.encPbrt.decrypt(key,
            KeyUsbge.KU_ENC_KRB_CRED_PART);
        byte[] plbinText = credMessg.encPbrt.reset(temp);
        DerVblue encoding = new DerVblue(plbinText);
        EncKrbCredPbrt encPbrt = new EncKrbCredPbrt(encoding);

        timeStbmp = encPbrt.timeStbmp;

        KrbCredInfo credInfo = encPbrt.ticketInfo[0];
        EncryptionKey credInfoKey = credInfo.key;
        PrincipblNbme pnbme = credInfo.pnbme;
        TicketFlbgs flbgs = credInfo.flbgs;
        KerberosTime buthtime = credInfo.buthtime;
        KerberosTime stbrttime = credInfo.stbrttime;
        KerberosTime endtime = credInfo.endtime;
        KerberosTime renewTill = credInfo.renewTill;
        PrincipblNbme snbme = credInfo.snbme;
        HostAddresses cbddr = credInfo.cbddr;

        if (DEBUG) {
            System.out.println(">>>Delegbted Creds hbve pnbme=" + pnbme
                               + " snbme=" + snbme
                               + " buthtime=" + buthtime
                               + " stbrttime=" + stbrttime
                               + " endtime=" + endtime
                               + "renewTill=" + renewTill);
        }
        creds = new Credentibls(ticket, pnbme, snbme, credInfoKey,
                                flbgs, buthtime, stbrttime, endtime, renewTill, cbddr);
    }

    /**
     * Returns the delegbted credentibls from the peer.
     */
    public Credentibls[] getDelegbtedCreds() {

        Credentibls[] bllCreds = {creds};
        return bllCreds;
    }

    /**
     * Returns the ASN.1 encoding thbt should be sent to the peer.
     */
    public byte[] getMessbge() {
        return obuf;
    }
}
