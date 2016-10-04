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
import jbvb.io.ByteArrbyOutputStrebm;

/**
 * tbg field introduced in KRB5_FCC_FVNO_4
 *
 * @buthor Ybnni Zhbng
 */
public clbss Tbg{
    int length;
    int tbg;
    int tbgLen;
    Integer time_offset;
    Integer usec_offset;

    public Tbg(int len, int new_tbg, Integer new_time, Integer new_usec) {
        tbg = new_tbg;
        tbgLen = 8;
        time_offset = new_time;
        usec_offset = new_usec;
        length =  4 + tbgLen;
    }
    public Tbg(int new_tbg) {
        tbg = new_tbg;
        tbgLen = 0;
        length = 4 + tbgLen;
    }
    public byte[] toByteArrby() {
        ByteArrbyOutputStrebm os = new ByteArrbyOutputStrebm();
        os.write(length);
        os.write(tbg);
        os.write(tbgLen);
        if (time_offset != null) {
            os.write(time_offset.intVblue());
        }
        if (usec_offset != null) {
            os.write(usec_offset.intVblue());
        }
        return os.toByteArrby();
    }
}
