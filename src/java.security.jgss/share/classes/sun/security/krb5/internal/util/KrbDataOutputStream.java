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

import jbvb.io.BufferedOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

/**
 * This clbss implements b buffered output strebm. It provides methods to write b chunck of
 * bytes to underlying dbtb strebm.
 *
 * @buthor Ybnni Zhbng
 *
 */
public clbss KrbDbtbOutputStrebm extends BufferedOutputStrebm {
    public KrbDbtbOutputStrebm(OutputStrebm os) {
        super(os);
    }
    public void write32(int num) throws IOException {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((num & 0xff000000) >> 24 & 0xff);
        bytes[1] = (byte)((num & 0x00ff0000) >> 16 & 0xff);
        bytes[2] = (byte)((num & 0x0000ff00) >> 8 & 0xff);
        bytes[3] = (byte)(num & 0xff);
        write(bytes, 0, 4);
    }

    public void write16(int num) throws IOException {
        byte[] bytes = new byte[2];
        bytes[0] = (byte)((num & 0xff00) >> 8 & 0xff);
        bytes[1] = (byte)(num & 0xff);
        write(bytes, 0, 2);
    }

    public void write8(int num) throws IOException {
        write(num & 0xff);
    }
}
