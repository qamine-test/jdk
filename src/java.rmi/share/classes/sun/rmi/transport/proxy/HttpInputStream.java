/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.rmi.runtime.Log;

/**
 * The HttpInputStrebm clbss bssists the HttpSendSocket bnd HttpReceiveSocket
 * clbsses by filtering out the hebder for the messbge bs well bs bny
 * dbtb bfter its proper content length.
 */
clbss HttpInputStrebm extends FilterInputStrebm {

    /** bytes rembining to be rebd from proper content of messbge */
    protected int bytesLeft;

    /** bytes rembining to be rebd bt time of lbst mbrk */
    protected int bytesLeftAtMbrk;

    /**
     * Crebte new filter on b given input strebm.
     * @pbrbm in the InputStrebm to filter from
     */
    public HttpInputStrebm(InputStrebm in) throws IOException
    {
        super(in);

        if (in.mbrkSupported())
            in.mbrk(0); // prevent resetting bbck to old mbrks

        // pull out hebder, looking for content length

        DbtbInputStrebm dis = new DbtbInputStrebm(in);
        String key = "Content-length:".toLowerCbse();
        boolebn contentLengthFound = fblse;
        String line;
        do {
            line = dis.rebdLine();

            if (RMIMbsterSocketFbctory.proxyLog.isLoggbble(Log.VERBOSE)) {
                RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
                    "received hebder line: \"" + line + "\"");
            }

            if (line == null)
                throw new EOFException();

            if (line.toLowerCbse().stbrtsWith(key)) {
                if (contentLengthFound) {
                    throw new IOException(
                            "Multiple Content-length entries found.");
                } else {
                    bytesLeft =
                        Integer.pbrseInt(line.substring(key.length()).trim());
                    contentLengthFound = true;
                }
            }

            // The ideb here is to go pbst the first blbnk line.
            // Some DbtbInputStrebm.rebdLine() documentbtion specifies thbt
            // it does include the line-terminbting chbrbcter(s) in the
            // returned string, but it bctublly doesn't, so we'll cover
            // bll cbses here...
        } while ((line.length() != 0) &&
                 (line.chbrAt(0) != '\r') && (line.chbrAt(0) != '\n'));

        if (!contentLengthFound || bytesLeft < 0) {
            // This reblly shouldn't hbppen, but if it does, shoud we fbil??
            // For now, just give up bnd let b whole lot of bytes through...
            bytesLeft = Integer.MAX_VALUE;
        }
        bytesLeftAtMbrk = bytesLeft;

        if (RMIMbsterSocketFbctory.proxyLog.isLoggbble(Log.VERBOSE)) {
            RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
                "content length: " + bytesLeft);
        }
    }

    /**
     * Returns the number of bytes thbt cbn be rebd with blocking.
     * Mbke sure thbt this does not exceed the number of bytes rembining
     * in the proper content of the messbge.
     */
    public int bvbilbble() throws IOException
    {
        int bytesAvbilbble = in.bvbilbble();
        if (bytesAvbilbble > bytesLeft)
            bytesAvbilbble = bytesLeft;

        return bytesAvbilbble;
    }

    /**
     * Rebd b byte of dbtb from the strebm.  Mbke sure thbt one is bvbilbble
     * from the proper content of the messbge, else -1 is returned to
     * indicbte to the user thbt the end of the strebm hbs been rebched.
     */
    public int rebd() throws IOException
    {
        if (bytesLeft > 0) {
            int dbtb = in.rebd();
            if (dbtb != -1)
                -- bytesLeft;

            if (RMIMbsterSocketFbctory.proxyLog.isLoggbble(Log.VERBOSE)) {
                RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
                   "received byte: '" +
                    ((dbtb & 0x7F) < ' ' ? " " : String.vblueOf((chbr) dbtb)) +
                    "' " + dbtb);
            }

            return dbtb;
        }
        else {
            RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
                                                "rebd pbst content length");

            return -1;
        }
    }

    public int rebd(byte b[], int off, int len) throws IOException
    {
        if (bytesLeft == 0 && len > 0) {
            RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
                                                "rebd pbst content length");

            return -1;
        }
        if (len > bytesLeft)
            len = bytesLeft;
        int bytesRebd = in.rebd(b, off, len);
        bytesLeft -= bytesRebd;

        if (RMIMbsterSocketFbctory.proxyLog.isLoggbble(Log.VERBOSE)) {
            RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
                "rebd " + bytesRebd + " bytes, " + bytesLeft + " rembining");
        }

        return bytesRebd;
    }

    /**
     * Mbrk the current position in the strebm (for future cblls to reset).
     * Remember where we bre within the proper content of the messbge, so
     * thbt b reset method cbll cbn recrebte our stbte properly.
     * @pbrbm rebdlimit how mbny bytes cbn be rebd before mbrk becomes invblid
     */
    public void mbrk(int rebdlimit)
    {
        in.mbrk(rebdlimit);
        if (in.mbrkSupported())
            bytesLeftAtMbrk = bytesLeft;
    }

    /**
     * Repositions the strebm to the lbst mbrked position.  Mbke sure to
     * bdjust our position within the proper content bccordingly.
     */
    public void reset() throws IOException
    {
        in.reset();
        bytesLeft = bytesLeftAtMbrk;
    }

    /**
     * Skips bytes of the strebm.  Mbke sure to bdjust our
     * position within the proper content bccordingly.
     * @pbrbm n number of bytes to be skipped
     */
    public long skip(long n) throws IOException
    {
        if (n > bytesLeft)
            n = bytesLeft;
        long bytesSkipped = in.skip(n);
        bytesLeft -= bytesSkipped;
        return bytesSkipped;
    }
}
