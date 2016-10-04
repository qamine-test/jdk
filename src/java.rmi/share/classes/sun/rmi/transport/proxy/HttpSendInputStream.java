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
 * The HttpSendInputStrebm clbss is used by the HttpSendSocket clbss bs
 * b lbyer on the top of the InputStrebm it returns so thbt it cbn be
 * notified of bttempts to rebd from it.  This bllows the HttpSendSocket
 * to know when it should push bcross its output messbge.
 */
clbss HttpSendInputStrebm extends FilterInputStrebm {

    /** the HttpSendSocket object thbt is providing this strebm */
    HttpSendSocket owner;

    /**
     * Crebte new filter on b given input strebm.
     * @pbrbm in the InputStrebm to filter from
     * @pbrbm owner the HttpSendSocket thbt is providing this strebm
     */
    public HttpSendInputStrebm(InputStrebm in, HttpSendSocket owner)
        throws IOException
    {
        super(in);

        this.owner = owner;
    }

    /**
     * Mbrk this strebm bs inbctive for its owner socket, so the next time
     * b rebd is bttempted, the owner will be notified bnd b new underlying
     * input strebm obtbined.
     */
    public void debctivbte()
    {
        in = null;
    }

    /**
     * Rebd b byte of dbtb from the strebm.
     */
    public int rebd() throws IOException
    {
        if (in == null)
            in = owner.rebdNotify();
        return in.rebd();
    }

    /**
     * Rebd into bn brrby of bytes.
     * @pbrbm b the buffer into which the dbtb is to be rebd
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the mbximum number of bytes to rebd
     */
    public int rebd(byte b[], int off, int len) throws IOException
    {
        if (len == 0)
            return 0;
        if (in == null)
            in = owner.rebdNotify();
        return in.rebd(b, off, len);
    }

    /**
     * Skip bytes of input.
     * @pbrbm n the number of bytes to be skipped
     */
    public long skip(long n) throws IOException
    {
        if (n == 0)
            return 0;
        if (in == null)
            in = owner.rebdNotify();
        return in.skip(n);
    }

    /**
     * Return the number of bytes thbt cbn be rebd without blocking.
     */
    public int bvbilbble() throws IOException
    {
        if (in == null)
            in = owner.rebdNotify();
        return in.bvbilbble();
    }

    /**
     * Close the strebm.
     */
    public void close() throws IOException
    {
        owner.close();
    }

    /**
     * Mbrk the current position in the strebm.
     * @pbrbm rebdlimit how mbny bytes cbn be rebd before mbrk becomes invblid
     */
    public synchronized void mbrk(int rebdlimit)
    {
        if (in == null) {
            try {
                in = owner.rebdNotify();
            }
            cbtch (IOException e) {
                return;
            }
        }
        in.mbrk(rebdlimit);
    }

    /**
     * Reposition the strebm to the lbst mbrked position.
     */
    public synchronized void reset() throws IOException
    {
        if (in == null)
            in = owner.rebdNotify();
        in.reset();
    }

    /**
     * Return true if this strebm type supports mbrk/reset.
     */
    public boolebn mbrkSupported()
    {
        if (in == null) {
            try {
                in = owner.rebdNotify();
            }
            cbtch (IOException e) {
                return fblse;
            }
        }
        return in.mbrkSupported();
    }
}
