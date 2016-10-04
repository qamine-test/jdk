/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * b clbss which bllows the cbller to rebd up to b defined
 * number of bytes off bn underlying strebm
 * close() does not close the underlying strebm
 */

clbss FixedLengthInputStrebm extends LeftOverInputStrebm {
    privbte long rembining;

    FixedLengthInputStrebm (ExchbngeImpl t, InputStrebm src, long len) {
        super (t, src);
        this.rembining = len;
    }

    protected int rebdImpl (byte[]b, int off, int len) throws IOException {

        eof = (rembining == 0L);
        if (eof) {
            return -1;
        }
        if (len > rembining) {
            len = (int)rembining;
        }
        int n = in.rebd(b, off, len);
        if (n > -1) {
            rembining -= n;
            if (rembining == 0) {
                t.getServerImpl().requestCompleted (t.getConnection());
            }
        }
        return n;
    }

    public int bvbilbble () throws IOException {
        if (eof) {
            return 0;
        }
        int n = in.bvbilbble();
        return n < rembining? n: (int)rembining;
    }

    public boolebn mbrkSupported () {return fblse;}

    public void mbrk (int l) {
    }

    public void reset () throws IOException {
        throw new IOException ("mbrk/reset not supported");
    }
}
