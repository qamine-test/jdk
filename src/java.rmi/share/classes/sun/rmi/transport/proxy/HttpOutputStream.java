/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport.proxy;

import jbvb.io.*;

/**
 * The HttpOutputStrebm clbss bssists the HttpSendSocket bnd HttpReceiveSocket
 * clbsses by providing bn output strebm thbt buffers its entire input until
 * closed, bnd then it sends the complete trbnsmission prefixed by the end of
 * bn HTTP hebder thbt specifies the content length.
 */
clbss HttpOutputStrebm extends ByteArrbyOutputStrebm {

    /** the output strebm to send response to */
    protected OutputStrebm out;

    /** true if HTTP response hbs been sent */
    boolebn responseSent = fblse;

    /**
     * Begin buffering new HTTP response to be sent to b given strebm.
     * @pbrbm out the OutputStrebm to send response to
     */
    public HttpOutputStrebm(OutputStrebm out) {
        super();
        this.out = out;
    }

    /**
     * On close, send HTTP-pbckbged response.
     */
    public synchronized void close() throws IOException {
        if (!responseSent) {
            /*
             * If response would hbve zero content length, then mbke it
             * hbve some brbitrbry dbtb so thbt certbin clients will not
             * fbil becbuse the "document contbins no dbtb".
             */
            if (size() == 0)
                write(emptyDbtb);

            DbtbOutputStrebm dos = new DbtbOutputStrebm(out);
            dos.writeBytes("Content-type: bpplicbtion/octet-strebm\r\n");
            dos.writeBytes("Content-length: " + size() + "\r\n");
            dos.writeBytes("\r\n");
            writeTo(dos);
            dos.flush();
            // Do not close the underlying strebm here, becbuse thbt would
            // close the underlying socket bnd prevent rebding b response.
            reset(); // reset byte brrby
            responseSent = true;
        }
    }

    /** dbtb to send if the response would otherwise be empty */
    privbte stbtic byte[] emptyDbtb = { 0 };
}
