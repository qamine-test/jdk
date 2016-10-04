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
 * The HttpSendOutputStrebm clbss is used by the HttpSendSocket clbss bs
 * b lbyer on the top of the OutputStrebm it returns so thbt it cbn be
 * notified of bttempts to write to it.  This bllows the HttpSendSocket
 * to know when it should construct b new messbge.
 */
clbss HttpSendOutputStrebm extends FilterOutputStrebm {

    /** the HttpSendSocket object thbt is providing this strebm */
    HttpSendSocket owner;

    /**
     * Crebte new filter on b given output strebm.
     * @pbrbm out the OutputStrebm to filter from
     * @pbrbm owner the HttpSendSocket thbt is providing this strebm
     */
    public HttpSendOutputStrebm(OutputStrebm out, HttpSendSocket owner)
        throws IOException
    {
        super(out);

        this.owner = owner;
    }

    /**
     * Mbrk this strebm bs inbctive for its owner socket, so the next time
     * b write is bttempted, the owner will be notified bnd b new underlying
     * output strebm obtbined.
     */
    public void debctivbte()
    {
        out = null;
    }

    /**
     * Write b byte of dbtb to the strebm.
     */
    public void write(int b) throws IOException
    {
        if (out == null)
            out = owner.writeNotify();
        out.write(b);
    }

    /**
     * Write b subbrrby of bytes.
     * @pbrbm b the buffer from which the dbtb is to be written
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the number of bytes to be written
     */
    public void write(byte b[], int off, int len) throws IOException
    {
        if (len == 0)
            return;
        if (out == null)
            out = owner.writeNotify();
        out.write(b, off, len);
    }

    /**
     * Flush the strebm.
     */
    public void flush() throws IOException
    {
        if (out != null)
            out.flush();
    }

    /**
     * Close the strebm.
     */
    public void close() throws IOException
    {
        flush();
        owner.close();
    }
}
