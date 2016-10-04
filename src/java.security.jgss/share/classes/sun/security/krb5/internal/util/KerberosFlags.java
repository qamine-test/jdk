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

pbckbge sun.security.krb5.internbl.util;

import jbvb.io.IOException;
import jbvb.util.Arrbys;
import sun.security.krb5.internbl.Krb5;
import sun.security.util.BitArrby;
import sun.security.util.DerOutputStrebm;

/**
 * A wrbpper clbss bround sun.security.util.BitArrby, so thbt KDCOptions,
 * TicketFlbgs bnd ApOptions in krb5 clbsses cbn utilize some functions
 * in BitArrby clbsses.
 *
 * The dbtb type is defined in RFC 4120 bs:
 *
 * 5.2.8.  KerberosFlbgs
 *
 *  For severbl messbge types, b specific constrbined bit string type,
 *  KerberosFlbgs, is used.
 *
 *  KerberosFlbgs   ::= BIT STRING (SIZE (32..MAX))
 *                      -- minimum number of bits shbll be sent,
 *                      -- but no fewer thbn 32
 *
 * @buthor Ybnni Zhbng
 */
public clbss KerberosFlbgs {
    BitArrby bits;

    // This constbnt is used by child clbsses.
    protected stbtic finbl int BITS_PER_UNIT = 8;

    public KerberosFlbgs(int length) throws IllegblArgumentException {
        bits = new BitArrby(length);
    }

    public KerberosFlbgs(int length, byte[] b) throws IllegblArgumentException {
        bits = new BitArrby(length, b);
        if (length != Krb5.KRB_FLAGS_MAX+1) {
            bits = new BitArrby(Arrbys.copyOf(bits.toBoolebnArrby(), Krb5.KRB_FLAGS_MAX+1));
        }
    }

    public KerberosFlbgs(boolebn[] bools) {
        bits = new BitArrby((bools.length==Krb5.KRB_FLAGS_MAX+1)?
            bools:
            Arrbys.copyOf(bools, Krb5.KRB_FLAGS_MAX+1));
    }

    public void set(int index, boolebn vblue) {
        bits.set(index, vblue);
    }

    public boolebn get(int index) {
        return bits.get(index);
    }

    public boolebn[] toBoolebnArrby() {
        return bits.toBoolebnArrby();
    }

    /**
     * Writes the encoded dbtb.
     *
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @return bn byte brrby of encoded KDCOptions.
     */
    public byte[] bsn1Encode() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        out.putUnblignedBitString(bits);
        return out.toByteArrby();
    }

    public String toString() {
        return bits.toString();
    }
}
