/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.security.krb5.internbl.crypto.Nonce;
import sun.security.krb5.internbl.crypto.KeyUsbge;
import jbvb.io.IOException;

/**
 * This clbss encbpsulbtes the KRB-AS-REQ messbge thbt the client
 * sends to the KDC.
 */
public clbss KrbAsReq {
    privbte ASReq bsReqMessg;

    privbte boolebn DEBUG = Krb5.DEBUG;

    /**
     * Constructs bn AS-REQ messbge.
     */
                                                // Cbn be null? hbs defbult?
    public KrbAsReq(EncryptionKey pbkey,        // ok
                      KDCOptions options,       // ok, new KDCOptions()
                      PrincipblNbme cnbme,      // NO bnd must hbve reblm
                      PrincipblNbme snbme,      // ok, krgtgt@CREALM
                      KerberosTime from,        // ok
                      KerberosTime till,        // ok, will use
                      KerberosTime rtime,       // ok
                      int[] eTypes,             // NO
                      HostAddresses bddresses   // ok
                      )
            throws KrbException, IOException {

        if (options == null) {
            options = new KDCOptions();
        }

        // check if they bre vblid brguments. The optionbl fields should be
        // consistent with settings in KDCOptions. Mbr 17 2000
        if (options.get(KDCOptions.FORWARDED) ||
            options.get(KDCOptions.PROXY) ||
            options.get(KDCOptions.ENC_TKT_IN_SKEY) ||
            options.get(KDCOptions.RENEW) ||
            options.get(KDCOptions.VALIDATE)) {
            // this option is only specified in b request to the
            // ticket-grbnting server
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.POSTDATED)) {
            //  if (from == null)
            //          throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } else {
            if (from != null)  from = null;
        }
        if (options.get(KDCOptions.RENEWABLE)) {
            //  if (rtime == null)
            //          throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } else {
            if (rtime != null)  rtime = null;
        }

        PADbtb[] pbDbtb = null;
        if (pbkey != null) {
            PAEncTSEnc ts = new PAEncTSEnc();
            byte[] temp = ts.bsn1Encode();
            EncryptedDbtb encTs = new EncryptedDbtb(pbkey, temp,
                KeyUsbge.KU_PA_ENC_TS);
            pbDbtb = new PADbtb[1];
            pbDbtb[0] = new PADbtb( Krb5.PA_ENC_TIMESTAMP,
                                    encTs.bsn1Encode());
        }

        if (cnbme.getReblm() == null) {
            throw new ReblmException(Krb5.REALM_NULL,
                                     "defbult reblm not specified ");
        }

        if (DEBUG) {
            System.out.println(">>> KrbAsReq crebting messbge");
        }

        // check to use bddresses in tickets
        if (bddresses == null && Config.getInstbnce().useAddresses()) {
            bddresses = HostAddresses.getLocblAddresses();
        }

        if (snbme == null) {
            String reblm = cnbme.getReblmAsString();
            snbme = PrincipblNbme.tgsService(reblm, reblm);
        }

        if (till == null) {
            till = new KerberosTime(0); // Choose KDC mbximum bllowed
        }

        // enc-buthorizbtion-dbtb bnd bdditionbl-tickets never in AS-REQ
        KDCReqBody kdc_req_body = new KDCReqBody(options,
                                                 cnbme,
                                                 snbme,
                                                 from,
                                                 till,
                                                 rtime,
                                                 Nonce.vblue(),
                                                 eTypes,
                                                 bddresses,
                                                 null,
                                                 null);

        bsReqMessg = new ASReq(
                         pbDbtb,
                         kdc_req_body);
    }

    byte[] encoding() throws IOException, Asn1Exception {
        return bsReqMessg.bsn1Encode();
    }

    // Used by KrbAsRep to vblidbte AS-REP
    ASReq getMessbge() {
        return bsReqMessg;
    }
}
