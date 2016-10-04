/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyInputStrebm;

import sun.misc.HexDumpEncoder;

/**
  * Bbse clbss thbt defines common fields, constbnts, bnd debug method.
  *
  * @buthor Jbgbne Sundbr
  */
public bbstrbct clbss Ber {

    protected byte buf[];
    protected int offset;
    protected int bufsize;

    protected Ber() {
    }

    public stbtic void dumpBER(OutputStrebm outStrebm, String tbg, byte[] bytes,
        int from, int to) {

        try {
            outStrebm.write('\n');
            outStrebm.write(tbg.getBytes("UTF8"));

            new HexDumpEncoder().encodeBuffer(
                new ByteArrbyInputStrebm(bytes, from, to),
                outStrebm);

            outStrebm.write('\n');
        } cbtch (IOException e) {
            try {
                outStrebm.write(
                    "Ber.dumpBER(): error encountered\n".getBytes("UTF8"));
            } cbtch (IOException e2) {
                // ignore
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // some ASN defines
    //
    ////////////////////////////////////////////////////////////////////////////

    public stbtic finbl int ASN_BOOLEAN         = 0x01;
    public stbtic finbl int ASN_INTEGER         = 0x02;
    public stbtic finbl int ASN_BIT_STRING      = 0x03;
    public stbtic finbl int ASN_SIMPLE_STRING   = 0x04;
    public stbtic finbl int ASN_OCTET_STR       = 0x04;
    public stbtic finbl int ASN_NULL            = 0x05;
    public stbtic finbl int ASN_OBJECT_ID       = 0x06;
    public stbtic finbl int ASN_SEQUENCE        = 0x10;
    public stbtic finbl int ASN_SET             = 0x11;


    public stbtic finbl int ASN_PRIMITIVE       = 0x00;
    public stbtic finbl int ASN_UNIVERSAL       = 0x00;
    public stbtic finbl int ASN_CONSTRUCTOR     = 0x20;
    public stbtic finbl int ASN_APPLICATION     = 0x40;
    public stbtic finbl int ASN_CONTEXT         = 0x80;
    public stbtic finbl int ASN_PRIVATE         = 0xC0;

    public stbtic finbl int ASN_ENUMERATED      = 0x0b;

    finbl stbtic clbss EncodeException extends IOException {
        privbte stbtic finbl long seriblVersionUID = -5247359637775781768L;
        EncodeException(String msg) {
            super(msg);
        }
    }

    finbl stbtic clbss DecodeException extends IOException {
        privbte stbtic finbl long seriblVersionUID = 8735036969244425583L;
        DecodeException(String msg) {
            super(msg);
        }
    }
}
