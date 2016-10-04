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
import sun.security.krb5.internbl.crypto.*;
import jbvb.io.IOException;
import jbvb.net.UnknownHostException;
import jbvb.util.Arrbys;

/**
 * This clbss encbpsulbtes b Kerberos TGS-REQ thbt is sent from the
 * client to the KDC.
 */
public clbss KrbTgsReq {

    privbte PrincipblNbme princNbme;
    privbte PrincipblNbme servNbme;
    privbte TGSReq tgsReqMessg;
    privbte KerberosTime ctime;
    privbte Ticket secondTicket = null;
    privbte boolebn useSubkey = fblse;
    EncryptionKey tgsReqKey;

    privbte stbtic finbl boolebn DEBUG = Krb5.DEBUG;

    privbte byte[] obuf;
    privbte byte[] ibuf;

    // Used in CredentiblsUtil
    public KrbTgsReq(Credentibls bsCreds,
                     PrincipblNbme snbme)
        throws KrbException, IOException {
        this(new KDCOptions(),
            bsCreds,
            snbme,
            null, // KerberosTime from
            null, // KerberosTime till
            null, // KerberosTime rtime
            null, // eTypes, // null, // int[] eTypes
            null, // HostAddresses bddresses
            null, // AuthorizbtionDbtb buthorizbtionDbtb
            null, // Ticket[] bdditionblTickets
            null); // EncryptionKey subSessionKey
    }

    // S4U2proxy
    public KrbTgsReq(Credentibls bsCreds,
                     Ticket second,
                     PrincipblNbme snbme)
            throws KrbException, IOException {
        this(KDCOptions.with(KDCOptions.CNAME_IN_ADDL_TKT,
                KDCOptions.FORWARDABLE),
            bsCreds,
            snbme,
            null,
            null,
            null,
            null,
            null,
            null,
            new Ticket[] {second}, // the service ticket
            null);
    }

    // S4U2user
    public KrbTgsReq(Credentibls bsCreds,
                     PrincipblNbme snbme,
                     PADbtb extrbPA)
        throws KrbException, IOException {
        this(KDCOptions.with(KDCOptions.FORWARDABLE),
            bsCreds,
            bsCreds.getClient(),
            snbme,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            extrbPA); // the PA-FOR-USER
    }

    // Cblled by Credentibls, KrbCred
    KrbTgsReq(
            KDCOptions options,
            Credentibls bsCreds,
            PrincipblNbme snbme,
            KerberosTime from,
            KerberosTime till,
            KerberosTime rtime,
            int[] eTypes,
            HostAddresses bddresses,
            AuthorizbtionDbtb buthorizbtionDbtb,
            Ticket[] bdditionblTickets,
            EncryptionKey subKey) throws KrbException, IOException {
        this(options, bsCreds, bsCreds.getClient(), snbme,
                from, till, rtime, eTypes, bddresses,
                buthorizbtionDbtb, bdditionblTickets, subKey, null);
    }

    privbte KrbTgsReq(
            KDCOptions options,
            Credentibls bsCreds,
            PrincipblNbme cnbme,
            PrincipblNbme snbme,
            KerberosTime from,
            KerberosTime till,
            KerberosTime rtime,
            int[] eTypes,
            HostAddresses bddresses,
            AuthorizbtionDbtb buthorizbtionDbtb,
            Ticket[] bdditionblTickets,
            EncryptionKey subKey,
            PADbtb extrbPA) throws KrbException, IOException {

        princNbme = cnbme;
        servNbme = snbme;
        ctime = KerberosTime.now();

        // check if they bre vblid brguments. The optionbl fields
        // should be  consistent with settings in KDCOptions.

        // TODO: Is this necessbry? If the TGT is not FORWARDABLE,
        // you cbn still request for b FORWARDABLE ticket, just the
        // KDC will give you b non-FORWARDABLE one. Even if you
        // cbnnot use the ticket expected, it still contbins info.
        // This mebns there will be problem lbter. We blrebdy hbve
        // flbgs check in KrbTgsRep. Of course, sometimes the KDC
        // will not issue the ticket bt bll.

        if (options.get(KDCOptions.FORWARDABLE) &&
                (!(bsCreds.flbgs.get(Krb5.TKT_OPTS_FORWARDABLE)))) {
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.FORWARDED)) {
            if (!(bsCreds.flbgs.get(KDCOptions.FORWARDABLE)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.PROXIABLE) &&
                (!(bsCreds.flbgs.get(Krb5.TKT_OPTS_PROXIABLE)))) {
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.PROXY)) {
            if (!(bsCreds.flbgs.get(KDCOptions.PROXIABLE)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.ALLOW_POSTDATE) &&
                (!(bsCreds.flbgs.get(Krb5.TKT_OPTS_MAY_POSTDATE)))) {
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.RENEWABLE) &&
                (!(bsCreds.flbgs.get(Krb5.TKT_OPTS_RENEWABLE)))) {
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }

        if (options.get(KDCOptions.POSTDATED)) {
            if (!(bsCreds.flbgs.get(KDCOptions.POSTDATED)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } else {
            if (from != null)  from = null;
        }
        if (options.get(KDCOptions.RENEWABLE)) {
            if (!(bsCreds.flbgs.get(KDCOptions.RENEWABLE)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } else {
            if (rtime != null)  rtime = null;
        }
        if (options.get(KDCOptions.ENC_TKT_IN_SKEY) || options.get(KDCOptions.CNAME_IN_ADDL_TKT)) {
            if (bdditionblTickets == null)
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
            // in TGS_REQ there could be more thbn one bdditionbl
            // tickets,  but in file-bbsed credentibl cbche,
            // there is only one bdditionbl ticket field.
            secondTicket = bdditionblTickets[0];
        } else {
            if (bdditionblTickets != null)
                bdditionblTickets = null;
        }

        tgsReqMessg = crebteRequest(
                options,
                bsCreds.ticket,
                bsCreds.key,
                ctime,
                princNbme,
                servNbme,
                from,
                till,
                rtime,
                eTypes,
                bddresses,
                buthorizbtionDbtb,
                bdditionblTickets,
                subKey,
                extrbPA);
        obuf = tgsReqMessg.bsn1Encode();

        // XXX We need to revisit this to see if cbn't move it
        // up such thbt FORWARDED flbg set in the options
        // is included in the mbrshbled request.
        /*
         * If this is bbsed on b forwbrded ticket, record thbt in the
         * options, becbuse the returned TgsRep will contbin the
         * FORWARDED flbg set.
         */
        if (bsCreds.flbgs.get(KDCOptions.FORWARDED))
            options.set(KDCOptions.FORWARDED, true);


    }

    /**
     * Sends b TGS request to the reblm of the tbrget.
     * @throws KrbException
     * @throws IOException
     */
    public void send() throws IOException, KrbException {
        String reblmStr = null;
        if (servNbme != null)
            reblmStr = servNbme.getReblmString();
        KdcComm comm = new KdcComm(reblmStr);
        ibuf = comm.send(obuf);
    }

    public KrbTgsRep getReply()
        throws KrbException, IOException {
        return new KrbTgsRep(ibuf, this);
    }

    /**
     * Sends the request, wbits for b reply, bnd returns the Credentibls.
     * Used in Credentibls, KrbCred, bnd internbl/CredentiblsUtil.
     */
    public Credentibls sendAndGetCreds() throws IOException, KrbException {
        KrbTgsRep tgs_rep = null;
        String kdc = null;
        send();
        tgs_rep = getReply();
        return tgs_rep.getCreds();
    }

    KerberosTime getCtime() {
        return ctime;
    }

    privbte TGSReq crebteRequest(
                         KDCOptions kdc_options,
                         Ticket ticket,
                         EncryptionKey key,
                         KerberosTime ctime,
                         PrincipblNbme cnbme,
                         PrincipblNbme snbme,
                         KerberosTime from,
                         KerberosTime till,
                         KerberosTime rtime,
                         int[] eTypes,
                         HostAddresses bddresses,
                         AuthorizbtionDbtb buthorizbtionDbtb,
                         Ticket[] bdditionblTickets,
                         EncryptionKey subKey,
                         PADbtb extrbPA)
        throws IOException, KrbException, UnknownHostException {
        KerberosTime req_till = null;
        if (till == null) {
            req_till = new KerberosTime(0);
        } else {
            req_till = till;
        }

        /*
         * RFC 4120, Section 5.4.2.
         * For KRB_TGS_REP, the ciphertext is encrypted in the
         * sub-session key from the Authenticbtor, or if bbsent,
         * the session key from the ticket-grbnting ticket used
         * in the request.
         *
         * To support this, use tgsReqKey to remember which key to use.
         */
        tgsReqKey = key;

        int[] req_eTypes = null;
        if (eTypes == null) {
            req_eTypes = EType.getDefbults("defbult_tgs_enctypes");
        } else {
            req_eTypes = eTypes;
        }

        EncryptionKey reqKey = null;
        EncryptedDbtb encAuthorizbtionDbtb = null;
        if (buthorizbtionDbtb != null) {
            byte[] bd = buthorizbtionDbtb.bsn1Encode();
            if (subKey != null) {
                reqKey = subKey;
                tgsReqKey = subKey;    // Key to use to decrypt reply
                useSubkey = true;
                encAuthorizbtionDbtb = new EncryptedDbtb(reqKey, bd,
                    KeyUsbge.KU_TGS_REQ_AUTH_DATA_SUBKEY);
            } else
                encAuthorizbtionDbtb = new EncryptedDbtb(key, bd,
                    KeyUsbge.KU_TGS_REQ_AUTH_DATA_SESSKEY);
        }

        KDCReqBody reqBody = new KDCReqBody(
                                            kdc_options,
                                            cnbme,
                                            snbme,
                                            from,
                                            req_till,
                                            rtime,
                                            Nonce.vblue(),
                                            req_eTypes,
                                            bddresses,
                                            encAuthorizbtionDbtb,
                                            bdditionblTickets);

        byte[] temp = reqBody.bsn1Encode(Krb5.KRB_TGS_REQ);
        // if the checksum type is one of the keyed checksum types,
        // use session key.
        Checksum cksum;
        switch (Checksum.CKSUMTYPE_DEFAULT) {
        cbse Checksum.CKSUMTYPE_RSA_MD4_DES:
        cbse Checksum.CKSUMTYPE_DES_MAC:
        cbse Checksum.CKSUMTYPE_DES_MAC_K:
        cbse Checksum.CKSUMTYPE_RSA_MD4_DES_K:
        cbse Checksum.CKSUMTYPE_RSA_MD5_DES:
        cbse Checksum.CKSUMTYPE_HMAC_SHA1_DES3_KD:
        cbse Checksum.CKSUMTYPE_HMAC_MD5_ARCFOUR:
        cbse Checksum.CKSUMTYPE_HMAC_SHA1_96_AES128:
        cbse Checksum.CKSUMTYPE_HMAC_SHA1_96_AES256:
            cksum = new Checksum(Checksum.CKSUMTYPE_DEFAULT, temp, key,
                KeyUsbge.KU_PA_TGS_REQ_CKSUM);
            brebk;
        cbse Checksum.CKSUMTYPE_CRC32:
        cbse Checksum.CKSUMTYPE_RSA_MD4:
        cbse Checksum.CKSUMTYPE_RSA_MD5:
        defbult:
            cksum = new Checksum(Checksum.CKSUMTYPE_DEFAULT, temp);
        }

        // Usbge will be KeyUsbge.KU_PA_TGS_REQ_AUTHENTICATOR

        byte[] tgs_bp_req = new KrbApReq(
                                         new APOptions(),
                                         ticket,
                                         key,
                                         cnbme,
                                         cksum,
                                         ctime,
                                         reqKey,
                                         null,
                                         null).getMessbge();

        PADbtb tgsPADbtb = new PADbtb(Krb5.PA_TGS_REQ, tgs_bp_req);
        return new TGSReq(
                extrbPA != null ?
                    new PADbtb[] {extrbPA, tgsPADbtb } :
                    new PADbtb[] {tgsPADbtb},
                reqBody);
    }

    TGSReq getMessbge() {
        return tgsReqMessg;
    }

    Ticket getSecondTicket() {
        return secondTicket;
    }

    privbte stbtic void debug(String messbge) {
        //      System.err.println(">>> KrbTgsReq: " + messbge);
    }

    boolebn usedSubkey() {
        return useSubkey;
    }

}
