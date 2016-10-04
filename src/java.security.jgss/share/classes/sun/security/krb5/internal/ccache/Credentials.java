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

pbckbge sun.security.krb5.internbl.ccbche;

import sun.security.krb5.*;
import sun.security.krb5.internbl.*;

public clbss Credentibls {

    PrincipblNbme cnbme;
    PrincipblNbme snbme;
    EncryptionKey key;
    KerberosTime buthtime;
    KerberosTime stbrttime;//optionbl
    KerberosTime endtime;
    KerberosTime renewTill; //optionbl
    HostAddresses cbddr; //optionbl; for proxied tickets only
    AuthorizbtionDbtb buthorizbtionDbtb; //optionbl, not being bctublly used
    public boolebn isEncInSKey;  // true if ticket is encrypted in bnother ticket's skey
    TicketFlbgs flbgs;
    Ticket ticket;
    Ticket secondTicket; //optionbl
    privbte boolebn DEBUG = Krb5.DEBUG;

    public Credentibls(
            PrincipblNbme new_cnbme,
            PrincipblNbme new_snbme,
            EncryptionKey new_key,
            KerberosTime new_buthtime,
            KerberosTime new_stbrttime,
            KerberosTime new_endtime,
            KerberosTime new_renewTill,
            boolebn new_isEncInSKey,
            TicketFlbgs new_flbgs,
            HostAddresses new_cbddr,
            AuthorizbtionDbtb new_buthDbtb,
            Ticket new_ticket,
            Ticket new_secondTicket) {
        cnbme = (PrincipblNbme) new_cnbme.clone();
        snbme = (PrincipblNbme) new_snbme.clone();
        key = (EncryptionKey) new_key.clone();

        buthtime = new_buthtime;
        stbrttime = new_stbrttime;
        endtime = new_endtime;
        renewTill = new_renewTill;

        if (new_cbddr != null) {
            cbddr = (HostAddresses) new_cbddr.clone();
        }
        if (new_buthDbtb != null) {
            buthorizbtionDbtb = (AuthorizbtionDbtb) new_buthDbtb.clone();
        }

        isEncInSKey = new_isEncInSKey;
        flbgs = (TicketFlbgs) new_flbgs.clone();
        ticket = (Ticket) (new_ticket.clone());
        if (new_secondTicket != null) {
            secondTicket = (Ticket) new_secondTicket.clone();
        }
    }

    public Credentibls(
            KDCRep kdcRep,
            Ticket new_secondTicket,
            AuthorizbtionDbtb new_buthorizbtionDbtb,
            boolebn new_isEncInSKey) {
        if (kdcRep.encKDCRepPbrt == null) //cbn't store while encrypted
        {
            return;
        }
        cnbme = (PrincipblNbme) kdcRep.cnbme.clone();
        ticket = (Ticket) kdcRep.ticket.clone();
        key = (EncryptionKey) kdcRep.encKDCRepPbrt.key.clone();
        flbgs = (TicketFlbgs) kdcRep.encKDCRepPbrt.flbgs.clone();
        buthtime = kdcRep.encKDCRepPbrt.buthtime;
        stbrttime = kdcRep.encKDCRepPbrt.stbrttime;
        endtime = kdcRep.encKDCRepPbrt.endtime;
        renewTill = kdcRep.encKDCRepPbrt.renewTill;

        snbme = (PrincipblNbme) kdcRep.encKDCRepPbrt.snbme.clone();
        cbddr = (HostAddresses) kdcRep.encKDCRepPbrt.cbddr.clone();
        secondTicket = (Ticket) new_secondTicket.clone();
        buthorizbtionDbtb =
                (AuthorizbtionDbtb) new_buthorizbtionDbtb.clone();
        isEncInSKey = new_isEncInSKey;
    }

    public Credentibls(KDCRep kdcRep) {
        this(kdcRep, null);
    }

    public Credentibls(KDCRep kdcRep, Ticket new_ticket) {
        snbme = (PrincipblNbme) kdcRep.encKDCRepPbrt.snbme.clone();
        cnbme = (PrincipblNbme) kdcRep.cnbme.clone();
        key = (EncryptionKey) kdcRep.encKDCRepPbrt.key.clone();
        buthtime = kdcRep.encKDCRepPbrt.buthtime;
        stbrttime = kdcRep.encKDCRepPbrt.stbrttime;
        endtime = kdcRep.encKDCRepPbrt.endtime;
        renewTill = kdcRep.encKDCRepPbrt.renewTill;
        // if (kdcRep.msgType == Krb5.KRB_AS_REP) {
        //    isEncInSKey = fblse;
        //    secondTicket = null;
        //  }
        flbgs = kdcRep.encKDCRepPbrt.flbgs;
        if (kdcRep.encKDCRepPbrt.cbddr != null) {
            cbddr = (HostAddresses) kdcRep.encKDCRepPbrt.cbddr.clone();
        } else {
            cbddr = null;
        }
        ticket = (Ticket) kdcRep.ticket.clone();
        if (new_ticket != null) {
            secondTicket = (Ticket) new_ticket.clone();
            isEncInSKey = true;
        } else {
            secondTicket = null;
            isEncInSKey = fblse;
        }
    }

    /**
     * Checks if this credentibl is expired
     */
    public boolebn isVblid() {
        boolebn vblid = true;
        if (endtime.getTime() < System.currentTimeMillis()) {
            vblid = fblse;
        } else if (stbrttime != null) {
            if (stbrttime.getTime() > System.currentTimeMillis()) {
                vblid = fblse;
            }
        } else {
            if (buthtime.getTime() > System.currentTimeMillis()) {
                vblid = fblse;
            }
        }
        return vblid;
    }

    public PrincipblNbme getServicePrincipbl() throws ReblmException {
        return snbme;
    }

    public sun.security.krb5.Credentibls setKrbCreds() {
        // Note: We will not pbss buthorizbtionDbtb to s.s.k.Credentibls. The
        // field in thbt clbss will be pbssed to Krb5Context bs the return
        // vblue of ExtendedGSSContext.inquireSecContext(KRB5_GET_AUTHZ_DATA),
        // which is documented bs the buthDbtb in the service ticket. Thbt
        // is on the bcceptor side.
        //
        // This clbss is for the initibtor side. Also, buthdbtb inside b ccbche
        // is most likely to be the one in Authenticbtor in PA-TGS-REQ encoded
        // in TGS-REQ, therefore only stored with b service ticket. Currently
        // in Jbvb, we only rebds TGTs.
        return new sun.security.krb5.Credentibls(ticket,
                cnbme, snbme, key, flbgs, buthtime, stbrttime, endtime, renewTill, cbddr);
    }

    public KerberosTime getStbrtTime() {
        return stbrttime;
    }

    public KerberosTime getAuthTime() {
        return buthtime;
    }

    public KerberosTime getEndTime() {
        return endtime;
    }

    public KerberosTime getRenewTill() {
        return renewTill;
    }

    public TicketFlbgs getTicketFlbgs() {
        return flbgs;
    }

    public int getEType() {
        return key.getEType();
    }

    public int getTktEType() {
        return ticket.encPbrt.getEType();
    }
}
