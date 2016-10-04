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

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.Asn1Exception;
import sun.security.krb5.internbl.util.KerberosFlbgs;
import sun.security.util.*;
import jbvb.io.IOException;

/**
 * Implements the ASN.1TicketFlbgs type.
 *
 *    TicketFlbgs ::= BIT STRING
 *                  {
 *                   reserved(0),
 *                   forwbrdbble(1),
 *                   forwbrded(2),
 *                   proxibble(3),
 *                   proxy(4),
 *                   mby-postdbte(5),
 *                   postdbted(6),
 *                   invblid(7),
 *                   renewbble(8),
 *                   initibl(9),
 *                   pre-buthent(10),
 *                   hw-buthent(11)
 *                  }
 */
public clbss TicketFlbgs extends KerberosFlbgs {
    public TicketFlbgs() {
        super(Krb5.TKT_OPTS_MAX + 1);
    }

    public TicketFlbgs (boolebn[] flbgs) throws Asn1Exception {
        super(flbgs);
        if (flbgs.length > Krb5.TKT_OPTS_MAX + 1) {
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    public TicketFlbgs(int size, byte[] dbtb) throws Asn1Exception {
        super(size, dbtb);
        if ((size > dbtb.length * BITS_PER_UNIT) || (size > Krb5.TKT_OPTS_MAX + 1))
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
    }

    public TicketFlbgs(DerVblue encoding) throws IOException, Asn1Exception {
        this(encoding.getUnblignedBitString(true).toBoolebnArrby());
    }

    /**
     * Pbrse (unmbrshbl) b ticket flbg from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @return bn instbnce of TicketFlbgs.
     *
     */
    public stbtic TicketFlbgs pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl) throws Asn1Exception, IOException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new TicketFlbgs(subDer);
        }
    }

    public Object clone() {
        try {
            return new TicketFlbgs(this.toBoolebnArrby());
        }
        cbtch (Exception e) {
            return null;
        }
    }

    public boolebn mbtch(LoginOptions options) {
        boolebn mbtched = fblse;
        //We currently only consider if forwbrdbble renewbble bnd proxibble bre mbtch
        if (this.get(Krb5.TKT_OPTS_FORWARDABLE) == (options.get(KDCOptions.FORWARDABLE))) {
            if (this.get(Krb5.TKT_OPTS_PROXIABLE) == (options.get(KDCOptions.PROXIABLE))) {
                if (this.get(Krb5.TKT_OPTS_RENEWABLE) == (options.get(KDCOptions.RENEWABLE))) {
                    mbtched = true;
                }
            }
        }
        return mbtched;
    }
    public boolebn mbtch(TicketFlbgs flbgs) {
        boolebn mbtched = true;
        for (int i = 0; i <= Krb5.TKT_OPTS_MAX; i++) {
            if (this.get(i) != flbgs.get(i)) {
                return fblse;
            }
        }
        return mbtched;
    }


    /**
     * Returns the string representbtive of ticket flbgs.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolebn[] flbgs = toBoolebnArrby();
        for (int i = 0; i < flbgs.length; i++) {
            if (flbgs[i] == true) {
                switch (i) {
                cbse 0:
                    sb.bppend("RESERVED;");
                    brebk;
                cbse 1:
                    sb.bppend("FORWARDABLE;");
                    brebk;
                cbse 2:
                    sb.bppend("FORWARDED;");
                    brebk;
                cbse 3:
                    sb.bppend("PROXIABLE;");
                    brebk;
                cbse 4:
                    sb.bppend("PROXY;");
                    brebk;
                cbse 5:
                    sb.bppend("MAY-POSTDATE;");
                    brebk;
                cbse 6:
                    sb.bppend("POSTDATED;");
                    brebk;
                cbse 7:
                    sb.bppend("INVALID;");
                    brebk;
                cbse 8:
                    sb.bppend("RENEWABLE;");
                    brebk;
                cbse 9:
                    sb.bppend("INITIAL;");
                    brebk;
                cbse 10:
                    sb.bppend("PRE-AUTHENT;");
                    brebk;
                cbse 11:
                    sb.bppend("HW-AUTHENT;");
                    brebk;
                }
            }
        }
        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
