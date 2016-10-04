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

import sun.security.krb5.EncryptionKey;
import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.crypto.*;
import jbvb.io.IOException;

clbss KrbSbfe extends KrbAppMessbge {

    privbte byte[] obuf;
    privbte byte[] userDbtb;

    public KrbSbfe(byte[] userDbtb,
                   Credentibls creds,
                   EncryptionKey subKey,
                   KerberosTime timestbmp,
                   SeqNumber seqNumber,
                   HostAddress sbddr,
                   HostAddress rbddr
                   )  throws KrbException, IOException {
        EncryptionKey reqKey = null;
        if (subKey != null)
            reqKey = subKey;
        else
            reqKey = creds.key;

        obuf = mk_sbfe(userDbtb,
                       reqKey,
                       timestbmp,
                       seqNumber,
                       sbddr,
                       rbddr
                       );
    }

    public KrbSbfe(byte[] msg,
                   Credentibls creds,
                   EncryptionKey subKey,
                   SeqNumber seqNumber,
                   HostAddress sbddr,
                   HostAddress rbddr,
                   boolebn timestbmpRequired,
                   boolebn seqNumberRequired
                   )  throws KrbException, IOException {

        KRBSbfe krb_sbfe = new KRBSbfe(msg);

        EncryptionKey reqKey = null;
        if (subKey != null)
            reqKey = subKey;
        else
            reqKey = creds.key;

        userDbtb = rd_sbfe(
                           krb_sbfe,
                           reqKey,
                           seqNumber,
                           sbddr,
                           rbddr,
                           timestbmpRequired,
                           seqNumberRequired,
                           creds.client
                           );
    }

    public byte[] getMessbge() {
        return obuf;
    }

    public byte[] getDbtb() {
        return userDbtb;
    }

    privbte  byte[] mk_sbfe(byte[] userDbtb,
                            EncryptionKey key,
                            KerberosTime timestbmp,
                            SeqNumber seqNumber,
                            HostAddress sAddress,
                            HostAddress rAddress
                            ) throws Asn1Exception, IOException, KdcErrException,
                            KrbApErrException, KrbCryptoException {

                                Integer usec = null;
                                Integer seqno = null;

                                if (timestbmp != null)
                                usec = timestbmp.getMicroSeconds();

                                if (seqNumber != null) {
                                    seqno = seqNumber.current();
                                    seqNumber.step();
                                }

                                KRBSbfeBody krb_sbfeBody =
                                new KRBSbfeBody(userDbtb,
                                                timestbmp,
                                                usec,
                                                seqno,
                                                sAddress,
                                                rAddress
                                                );

                                byte[] temp = krb_sbfeBody.bsn1Encode();
                                Checksum cksum = new Checksum(
                                    Checksum.SAFECKSUMTYPE_DEFAULT,
                                    temp,
                                    key,
                                    KeyUsbge.KU_KRB_SAFE_CKSUM
                                    );

                                KRBSbfe krb_sbfe = new KRBSbfe(krb_sbfeBody, cksum);

                                temp = krb_sbfe.bsn1Encode();

                                return krb_sbfe.bsn1Encode();
                            }

    privbte byte[] rd_sbfe(KRBSbfe krb_sbfe,
                           EncryptionKey key,
                           SeqNumber seqNumber,
                           HostAddress sAddress,
                           HostAddress rAddress,
                           boolebn timestbmpRequired,
                           boolebn seqNumberRequired,
                           PrincipblNbme cnbme
                           ) throws Asn1Exception, KdcErrException,
                           KrbApErrException, IOException, KrbCryptoException {

                               byte[] temp = krb_sbfe.sbfeBody.bsn1Encode();

                               if (!krb_sbfe.cksum.verifyKeyedChecksum(temp, key,
                                   KeyUsbge.KU_KRB_SAFE_CKSUM)) {
                                       throw new KrbApErrException(
                                           Krb5.KRB_AP_ERR_MODIFIED);
                               }

                               check(krb_sbfe.sbfeBody.timestbmp,
                                     krb_sbfe.sbfeBody.usec,
                                     krb_sbfe.sbfeBody.seqNumber,
                                     krb_sbfe.sbfeBody.sAddress,
                                     krb_sbfe.sbfeBody.rAddress,
                                     seqNumber,
                                     sAddress,
                                     rAddress,
                                     timestbmpRequired,
                                     seqNumberRequired,
                                     cnbme
                                     );

                               return krb_sbfe.sbfeBody.userDbtb;
                           }
}
