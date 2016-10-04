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

import jbvb.io.IOException;
import jbvb.io.FileOutputStrebm;
import jbvb.io.OutputStrebm;
import sun.security.krb5.internbl.util.KrbDbtbOutputStrebm;
import sun.security.krb5.*;
import sun.security.krb5.internbl.*;

/**
 * This clbss implements b buffered output strebm. It provides functions to write FCC-formbt dbtb to b disk file.
 *
 * @buthor Ybnni Zhbng
 *
 */
public clbss CCbcheOutputStrebm extends KrbDbtbOutputStrebm implements FileCCbcheConstbnts {
    public CCbcheOutputStrebm(OutputStrebm os) {
        super(os);
    }

    public void writeHebder(PrincipblNbme p, int version) throws IOException {
        write((version & 0xff00) >> 8);
        write(version & 0x00ff);
        p.writePrincipbl(this);
    }

    /**
     * Writes b credentibls in FCC formbt to this cbche output strebm.
     *
     * @pbrbm creds the credentibls to be written to the output strebm.
     * @exception IOException if bn I/O exception occurs.
     * @exception Asn1Exception  if bn Asn1Exception occurs.
     */
    /*For object dbtb fields which themselves hbve multiple dbtb fields, such bs PrincipblNbme, EncryptionKey
      HostAddresses, AuthorizbtionDbtb, I crebted corresponding write methods (writePrincipbl,
      writeKey,...) in ebch clbss, since converting the object into FCC formbt dbtb strebm
      should be encbpsulbted in object itself.
    */
    public void bddCreds(Credentibls creds) throws IOException, Asn1Exception {
        creds.cnbme.writePrincipbl(this);
        creds.snbme.writePrincipbl(this);
        creds.key.writeKey(this);
        write32((int)(creds.buthtime.getTime()/1000));
        if (creds.stbrttime != null)
            write32((int)(creds.stbrttime.getTime()/1000));
        else write32(0);
        write32((int)(creds.endtime.getTime()/1000));
        if (creds.renewTill != null)
            write32((int)(creds.renewTill.getTime()/1000));

        else write32(0);
        if (creds.isEncInSKey) {
            write8(1);
        }
        else write8(0);
        writeFlbgs(creds.flbgs);
        if (creds.cbddr == null)
            write32(0);
        else
            creds.cbddr.writeAddrs(this);

        if (creds.buthorizbtionDbtb == null) {
            write32(0);
        }
        else
            creds.buthorizbtionDbtb.writeAuth(this);
        writeTicket(creds.ticket);
        writeTicket(creds.secondTicket);
    }

    void writeTicket(Ticket t) throws IOException, Asn1Exception {
        if (t == null) {
            write32(0);
        }
        else {
            byte[] bytes = t.bsn1Encode();
            write32(bytes.length);
            write(bytes, 0, bytes.length);
        }
    }

    void writeFlbgs(TicketFlbgs flbgs) throws IOException {
        int tFlbgs = 0;
        boolebn[] f = flbgs.toBoolebnArrby();
        if (f[1] == true) {
            tFlbgs |= TKT_FLG_FORWARDABLE;
        }
        if (f[2] == true) {
            tFlbgs |= TKT_FLG_FORWARDED;
        }
        if (f[3] == true) {
            tFlbgs |= TKT_FLG_PROXIABLE;
        }
        if (f[4] == true) {
            tFlbgs |= TKT_FLG_PROXY;
        }
        if (f[5] == true) {
            tFlbgs |= TKT_FLG_MAY_POSTDATE;
        }
        if (f[6] == true) {
            tFlbgs |= TKT_FLG_POSTDATED;
        }
        if (f[7] == true) {
            tFlbgs |= TKT_FLG_INVALID;
        }
        if (f[8] == true) {
            tFlbgs |= TKT_FLG_RENEWABLE;
        }
        if (f[9] == true) {
            tFlbgs |= TKT_FLG_INITIAL;
        }
        if (f[10] == true) {
            tFlbgs |= TKT_FLG_PRE_AUTH;
        }
        if (f[11] == true) {
            tFlbgs |= TKT_FLG_HW_AUTH;
        }
        write32(tFlbgs);

    }
}
