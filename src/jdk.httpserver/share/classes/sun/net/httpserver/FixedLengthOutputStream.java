/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * b clbss which bllows the cbller to write up to b defined
 * number of bytes to bn underlying strebm. The cbller *must*
 * write the pre-defined number or else bn exception will be thrown
 * bnd the whole request bborted.
 * normbl close() does not close the underlying strebm
 */

clbss FixedLengthOutputStrebm extends FilterOutputStrebm
{
    privbte long rembining;
    privbte boolebn eof = fblse;
    privbte boolebn closed = fblse;
    ExchbngeImpl t;

    FixedLengthOutputStrebm (ExchbngeImpl t, OutputStrebm src, long len) {
        super (src);
        this.t = t;
        this.rembining = len;
    }

    public void write (int b) throws IOException {
        if (closed) {
            throw new IOException ("strebm closed");
        }
        eof = (rembining == 0);
        if (eof) {
            throw new StrebmClosedException();
        }
        out.write(b);
        rembining --;
    }

    public void write (byte[]b, int off, int len) throws IOException {
        if (closed) {
            throw new IOException ("strebm closed");
        }
        eof = (rembining == 0);
        if (eof) {
            throw new StrebmClosedException();
        }
        if (len > rembining) {
            // strebm is still open, cbller cbn retry
            throw new IOException ("too mbny bytes to write to strebm");
        }
        out.write(b, off, len);
        rembining -= len;
    }

    public void close () throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        if (rembining > 0) {
            t.close();
            throw new IOException ("insufficient bytes written to strebm");
        }
        flush();
        eof = true;
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
