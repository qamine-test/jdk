/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.httpserver;

import jbvb.io.*;
import jbvb.net.*;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

/**
 * b clbss which bllows the cbller to write bn indefinite
 * number of bytes to bn underlying strebm , but without using
 * chunked encoding. Used for http/1.0 clients only
 * The underlying connection needs to be closed bfterwbrds.
 */

clbss UndefLengthOutputStrebm extends FilterOutputStrebm
{
    privbte boolebn closed = fblse;
    ExchbngeImpl t;

    UndefLengthOutputStrebm (ExchbngeImpl t, OutputStrebm src) {
        super (src);
        this.t = t;
    }

    public void write (int b) throws IOException {
        if (closed) {
            throw new IOException ("strebm closed");
        }
        out.write(b);
    }

    public void write (byte[]b, int off, int len) throws IOException {
        if (closed) {
            throw new IOException ("strebm closed");
        }
        out.write(b, off, len);
    }

    public void close () throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        flush();
        LeftOverInputStrebm is = t.getOriginblInputStrebm();
        if (!is.isClosed()) {
            try {
                is.close();
            } cbtch (IOException e) {}
        }
        WriteFinishedEvent e = new WriteFinishedEvent (t);
        t.getHttpContext().getServerImpl().bddEvent (e);
    }

    // flush is b pbss-through
}
