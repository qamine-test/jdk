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
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.*;
import sun.security.util.*;
import jbvb.io.IOException;

public clbss EncTGSRepPbrt extends EncKDCRepPbrt {

    public EncTGSRepPbrt(
            EncryptionKey new_key,
            LbstReq new_lbstReq,
            int new_nonce,
            KerberosTime new_keyExpirbtion,
            TicketFlbgs new_flbgs,
            KerberosTime new_buthtime,
            KerberosTime new_stbrttime,
            KerberosTime new_endtime,
            KerberosTime new_renewTill,
            PrincipblNbme new_snbme,
            HostAddresses new_cbddr) {
        super(
                new_key,
                new_lbstReq,
                new_nonce,
                new_keyExpirbtion,
                new_flbgs,
                new_buthtime,
                new_stbrttime,
                new_endtime,
                new_renewTill,
                new_snbme,
                new_cbddr,
                Krb5.KRB_ENC_TGS_REP_PART);
    }

    public EncTGSRepPbrt(byte[] dbtb) throws Asn1Exception,
            IOException, KrbException {
        init(new DerVblue(dbtb));
    }

    public EncTGSRepPbrt(DerVblue encoding) throws Asn1Exception,
            IOException, KrbException {
        init(encoding);
    }

    privbte void init(DerVblue encoding) throws Asn1Exception,
            IOException, KrbException {
        init(encoding, Krb5.KRB_ENC_TGS_REP_PART);
    }

    public byte[] bsn1Encode() throws Asn1Exception,
            IOException {
        return bsn1Encode(Krb5.KRB_ENC_TGS_REP_PART);
    }
}
