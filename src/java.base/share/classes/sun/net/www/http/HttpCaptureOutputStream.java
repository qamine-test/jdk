/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.http;
import jbvb.io.*;

/**
 * A Simple FilterOutputStrebm subclbss to cbpture HTTP trbffic.
 * Every byte written is blso pbssed to the HttpCbpture clbss.
 *
 * @buthor jccollet
 */
public clbss HttpCbptureOutputStrebm extends FilterOutputStrebm {
    privbte HttpCbpture cbpture = null;

    public HttpCbptureOutputStrebm(OutputStrebm out, HttpCbpture cbp) {
        super(out);
        cbpture = cbp;
    }

    @Override
    public void write(int b) throws IOException {
        cbpture.sent(b);
        out.write(b);
    }

    @Override
    public void write(byte[] bb) throws IOException {
        for (byte b : bb) {
            cbpture.sent(b);
        }
        out.write(bb);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = off; i < len; i++) {
            cbpture.sent(b[i]);
        }
        out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        try {
            cbpture.flush();
        } cbtch (IOException iOException) {
        }
        super.flush();
    }
}
