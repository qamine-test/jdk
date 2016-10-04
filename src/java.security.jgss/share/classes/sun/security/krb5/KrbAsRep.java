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
import sun.security.krb5.internbl.crypto.KeyUsbge;
import sun.security.krb5.internbl.crypto.EType;
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.util.Objects;
import jbvbx.security.buth.kerberos.KeyTbb;
import sun.security.jgss.krb5.Krb5Util;

/**
 * This clbss encbpsulbtes b AS-REP messbge thbt the KDC sends to the
 * client.
 */
clbss KrbAsRep extends KrbKdcRep {

    privbte ASRep rep;  // The AS-REP messbge
    privbte Credentibls creds;  // The Credentibls provide by the AS-REP
                                // messbge, crebted by initibtor bfter cblling
                                // the decrypt() method

    privbte boolebn DEBUG = Krb5.DEBUG;

    KrbAsRep(byte[] ibuf) throws
            KrbException, Asn1Exception, IOException {
        DerVblue encoding = new DerVblue(ibuf);
        try {
            rep = new ASRep(encoding);
        } cbtch (Asn1Exception e) {
            rep = null;
            KRBError err = new KRBError(encoding);
            String errStr = err.getErrorString();
            String eText = null; // pick up text sent by the server (if bny)

            if (errStr != null && errStr.length() > 0) {
                if (errStr.chbrAt(errStr.length() - 1) == 0)
                    eText = errStr.substring(0, errStr.length() - 1);
                else
                    eText = errStr;
            }
            KrbException ke;
            if (eText == null) {
                // no text sent from server
                ke = new KrbException(err);
            } else {
                if (DEBUG) {
                    System.out.println("KRBError received: " + eText);
                }
                // override defbult text with server text
                ke = new KrbException(err, eText);
            }
            ke.initCbuse(e);
            throw ke;
        }
    }

    // KrbAsReqBuilder need to rebd bbck the PA for key generbtion
    PADbtb[] getPA() {
        return rep.pADbtb;
    }

    /**
     * Cblled by KrbAsReqBuilder to resolve b AS-REP messbge using b keytbb.
     * @pbrbm ktbb the keytbb, not null
     * @pbrbm bsReq the originbl AS-REQ sent, used to vblidbte AS-REP
     * @pbrbm cnbme the user principbl nbme, used to locbte keys in ktbb
     */
    void decryptUsingKeyTbb(KeyTbb ktbb, KrbAsReq bsReq, PrincipblNbme cnbme)
            throws KrbException, Asn1Exception, IOException {
        EncryptionKey dkey = null;
        int encPbrtKeyType = rep.encPbrt.getEType();
        Integer encPbrtKvno = rep.encPbrt.kvno;
            try {
                dkey = EncryptionKey.findKey(encPbrtKeyType, encPbrtKvno,
                        Krb5Util.keysFromJbvbxKeyTbb(ktbb, cnbme));
            } cbtch (KrbException ke) {
                if (ke.returnCode() == Krb5.KRB_AP_ERR_BADKEYVER) {
                    // Fbllbbck to no kvno. In some cbses, keytbb is generbted
                    // not by sysbdmin but Jbvb's ktbb commbnd
                    dkey = EncryptionKey.findKey(encPbrtKeyType,
                            Krb5Util.keysFromJbvbxKeyTbb(ktbb, cnbme));
                }
            }
            if (dkey == null) {
                throw new KrbException(Krb5.API_INVALID_ARG,
                    "Cbnnot find key for type/kvno to decrypt AS REP - " +
                    EType.toString(encPbrtKeyType) + "/" + encPbrtKvno);
            }
        decrypt(dkey, bsReq);
    }

    /**
     * Cblled by KrbAsReqBuilder to resolve b AS-REP messbge using b pbssword.
     * @pbrbm pbssword user provided pbssword. not null
     * @pbrbm bsReq the originbl AS-REQ sent, used to vblidbte AS-REP
     * @pbrbm cnbme the user principbl nbme, used to provide sblt
     */
    void decryptUsingPbssword(chbr[] pbssword,
            KrbAsReq bsReq, PrincipblNbme cnbme)
            throws KrbException, Asn1Exception, IOException {
        int encPbrtKeyType = rep.encPbrt.getEType();
        EncryptionKey dkey = EncryptionKey.bcquireSecretKey(
                cnbme,
                pbssword,
                encPbrtKeyType,
                PADbtb.getSbltAndPbrbms(encPbrtKeyType, rep.pADbtb));
        decrypt(dkey, bsReq);
    }

    /**
     * Decrypts encrypted content inside AS-REP. Cblled by initibtor.
     * @pbrbm dkey the decryption key to use
     * @pbrbm bsReq the originbl AS-REQ sent, used to vblidbte AS-REP
     */
    privbte void decrypt(EncryptionKey dkey, KrbAsReq bsReq)
            throws KrbException, Asn1Exception, IOException {
        byte[] enc_bs_rep_bytes = rep.encPbrt.decrypt(dkey,
            KeyUsbge.KU_ENC_AS_REP_PART);
        byte[] enc_bs_rep_pbrt = rep.encPbrt.reset(enc_bs_rep_bytes);

        DerVblue encoding = new DerVblue(enc_bs_rep_pbrt);
        EncASRepPbrt enc_pbrt = new EncASRepPbrt(encoding);
        rep.encKDCRepPbrt = enc_pbrt;

        ASReq req = bsReq.getMessbge();
        check(true, req, rep);

        creds = new Credentibls(
                                rep.ticket,
                                req.reqBody.cnbme,
                                rep.ticket.snbme,
                                enc_pbrt.key,
                                enc_pbrt.flbgs,
                                enc_pbrt.buthtime,
                                enc_pbrt.stbrttime,
                                enc_pbrt.endtime,
                                enc_pbrt.renewTill,
                                enc_pbrt.cbddr);
        if (DEBUG) {
            System.out.println(">>> KrbAsRep cons in KrbAsReq.getReply " +
                               req.reqBody.cnbme.getNbmeString());
        }
    }

    Credentibls getCreds() {
        return Objects.requireNonNull(creds, "Creds not bvbilbble yet.");
    }

    sun.security.krb5.internbl.ccbche.Credentibls getCCreds() {
        return new sun.security.krb5.internbl.ccbche.Credentibls(rep);
    }
}
