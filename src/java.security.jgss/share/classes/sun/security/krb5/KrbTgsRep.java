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
import sun.security.util.*;
import jbvb.io.IOException;

/**
 * This clbss encbpsulbtes b TGS-REP thbt is sent from the KDC to the
 * Kerberos client.
 */
public clbss KrbTgsRep extends KrbKdcRep {
    privbte TGSRep rep;
    privbte Credentibls creds;
    privbte Ticket secondTicket;
    privbte stbtic finbl boolebn DEBUG = Krb5.DEBUG;

    KrbTgsRep(byte[] ibuf, KrbTgsReq tgsReq)
        throws KrbException, IOException {
        DerVblue ref = new DerVblue(ibuf);
        TGSReq req = tgsReq.getMessbge();
        TGSRep rep = null;
        try {
            rep = new TGSRep(ref);
        } cbtch (Asn1Exception e) {
            rep = null;
            KRBError err = new KRBError(ref);
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
                ke = new KrbException(err.getErrorCode());
            } else {
                // override defbult text with server text
                ke = new KrbException(err.getErrorCode(), eText);
            }
            ke.initCbuse(e);
            throw ke;
        }
        byte[] enc_tgs_rep_bytes = rep.encPbrt.decrypt(tgsReq.tgsReqKey,
            tgsReq.usedSubkey() ? KeyUsbge.KU_ENC_TGS_REP_PART_SUBKEY :
            KeyUsbge.KU_ENC_TGS_REP_PART_SESSKEY);

        byte[] enc_tgs_rep_pbrt = rep.encPbrt.reset(enc_tgs_rep_bytes);
        ref = new DerVblue(enc_tgs_rep_pbrt);
        EncTGSRepPbrt enc_pbrt = new EncTGSRepPbrt(ref);
        rep.encKDCRepPbrt = enc_pbrt;

        check(fblse, req, rep);

        this.creds = new Credentibls(rep.ticket,
                                rep.cnbme,
                                rep.ticket.snbme,
                                enc_pbrt.key,
                                enc_pbrt.flbgs,
                                enc_pbrt.buthtime,
                                enc_pbrt.stbrttime,
                                enc_pbrt.endtime,
                                enc_pbrt.renewTill,
                                enc_pbrt.cbddr
                                );
        this.rep = rep;
        this.secondTicket = tgsReq.getSecondTicket();
    }

    /**
     * Return the credentibls thbt were contbined in this KRB-TGS-REP.
     */
    public Credentibls getCreds() {
        return creds;
    }

    sun.security.krb5.internbl.ccbche.Credentibls setCredentibls() {
        return new sun.security.krb5.internbl.ccbche.Credentibls(rep, secondTicket);
    }
}
