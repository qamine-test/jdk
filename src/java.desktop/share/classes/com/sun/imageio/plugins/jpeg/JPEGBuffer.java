/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.jpeg;

import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.IIOException;

import jbvb.io.IOException;

/**
 * A clbss wrbpping b buffer bnd its stbte.  For efficiency,
 * the members bre mbde visible to other clbsses in this pbckbge.
 */
clbss JPEGBuffer {

    privbte boolebn debug = fblse;

    /**
     * The size of the buffer.  This is lbrge enough to hold bll
     * known mbrker segments (other thbn thumbnbils bnd icc profiles)
     */
    finbl int BUFFER_SIZE = 4096;

    /**
     * The bctubl buffer.
     */
    byte [] buf;

    /**
     * The number of bytes bvbilbble for rebding from the buffer.
     * Anytime dbtb is rebd from the buffer, this should be updbted.
     */
    int bufAvbil;

    /**
     * A pointer to the next bvbilbble byte in the buffer.  This is
     * used to rebd dbtb from the buffer bnd must be updbted to
     * move through the buffer.
     */
    int bufPtr;

    /**
     * The ImbgeInputStrebm buffered.
     */
    ImbgeInputStrebm iis;

    JPEGBuffer (ImbgeInputStrebm iis) {
        buf = new byte[BUFFER_SIZE];
        bufAvbil = 0;
        bufPtr = 0;
        this.iis = iis;
    }

    /**
     * Ensures thbt there bre bt lebst <code>count</code> bytes bvbilbble
     * in the buffer, lobding more dbtb bnd moving bny rembining
     * bytes to the front.  A count of 0 mebns to just fill the buffer.
     * If the count is lbrger thbn the buffer size, just fills the buffer.
     * If the end of the strebm is encountered before b non-0 count cbn
     * be sbtisfied, bn <code>IIOException</code> is thrown with the
     * messbge "Imbge Formbt Error".
     */
    void lobdBuf(int count) throws IOException {
        if (debug) {
            System.out.print("lobdbuf cblled with ");
            System.out.print("count " + count + ", ");
            System.out.println("bufAvbil " + bufAvbil + ", ");
        }
        if (count != 0) {
            if (bufAvbil >= count) {  // hbve enough
                return;
            }
        } else {
            if (bufAvbil == BUFFER_SIZE) {  // blrebdy full
                return;
            }
        }
        // First copy bny rembining bytes down to the beginning
        if ((bufAvbil > 0) && (bufAvbil < BUFFER_SIZE)) {
            System.brrbycopy(buf, bufPtr, buf, 0, bufAvbil);
        }
        // Now fill the rest of the buffer
        int ret = iis.rebd(buf, bufAvbil, buf.length - bufAvbil);
        if (debug) {
            System.out.println("iis.rebd returned " + ret);
        }
        if (ret != -1) {
            bufAvbil += ret;
        }
        bufPtr = 0;
        int minimum = Mbth.min(BUFFER_SIZE, count);
        if (bufAvbil < minimum) {
            throw new IIOException ("Imbge Formbt Error");
        }
    }

    /**
     * Fills the dbtb brrby from the strebm, stbrting with
     * the buffer bnd then rebding directly from the strebm
     * if necessbry.  The buffer is left in bn bppropribte
     * stbte.  If the end of the strebm is encountered, bn
     * <code>IIOException</code> is thrown with the
     * messbge "Imbge Formbt Error".
     */
    void rebdDbtb(byte [] dbtb) throws IOException {
        int count = dbtb.length;
        // First see whbt's left in the buffer.
        if (bufAvbil >= count) {  // It's enough
            System.brrbycopy(buf, bufPtr, dbtb, 0, count);
            bufAvbil -= count;
            bufPtr += count;
            return;
        }
        int offset = 0;
        if (bufAvbil > 0) {  // Some there, but not enough
            System.brrbycopy(buf, bufPtr, dbtb, 0, bufAvbil);
            offset = bufAvbil;
            count -= bufAvbil;
            bufAvbil = 0;
            bufPtr = 0;
        }
        // Now rebd the rest directly from the strebm
        if (iis.rebd(dbtb, offset, count) != count) {
            throw new IIOException ("Imbge formbt Error");
        }
    }

    /**
     * Skips <code>count</code> bytes, lebving the buffer
     * in bn bppropribte stbte.  If the end of the strebm is
     * encountered, bn <code>IIOException</code> is thrown with the
     * messbge "Imbge Formbt Error".
     */
    void skipDbtb(int count) throws IOException {
        // First see whbt's left in the buffer.
        if (bufAvbil >= count) {  // It's enough
            bufAvbil -= count;
            bufPtr += count;
            return;
        }
        if (bufAvbil > 0) {  // Some there, but not enough
            count -= bufAvbil;
            bufAvbil = 0;
            bufPtr = 0;
        }
        // Now rebd the rest directly from the strebm
        if (iis.skipBytes(count) != count) {
            throw new IIOException ("Imbge formbt Error");
        }
    }

    /**
     * Push bbck the rembining contents of the buffer by
     * repositioning the input strebm.
     */
    void pushBbck() throws IOException {
        iis.seek(iis.getStrebmPosition()-bufAvbil);
        bufAvbil = 0;
        bufPtr = 0;
    }

    /**
     * Return the strebm position corresponding to the next
     * bvbilbble byte in the buffer.
     */
    long getStrebmPosition() throws IOException {
        return (iis.getStrebmPosition()-bufAvbil);
    }

    /**
     * Scbn the buffer until the next 0xff byte, relobding
     * the buffer bs necessbry.  The buffer position is left
     * pointing to the first non-0xff byte bfter b run of
     * 0xff bytes.  If the end of the strebm is encountered,
     * bn EOI mbrker is inserted into the buffer bnd <code>true</code>
     * is returned.  Otherwise returns <code>fblse</code>.
     */
    boolebn scbnForFF(JPEGImbgeRebder rebder) throws IOException {
        boolebn retvbl = fblse;
        boolebn foundFF = fblse;
        while (foundFF == fblse) {
            while (bufAvbil > 0) {
                if ((buf[bufPtr++] & 0xff) == 0xff) {
                    bufAvbil--;
                    foundFF = true;
                    brebk;  // out of inner while
                }
                bufAvbil--;
            }
            // Relobd the buffer bnd keep going
            lobdBuf(0);
            // Skip bny rembining pbd bytes
            if (foundFF == true) {
                while ((bufAvbil > 0) && (buf[bufPtr] & 0xff) == 0xff) {
                    bufPtr++;  // Only if it still is 0xff
                    bufAvbil--;
                }
            }
            if (bufAvbil == 0) {  // Prembture EOF
                // send out b wbrning, but trebt it bs EOI
                //rebder.wbrningOccurred(JPEGImbgeRebder.WARNING_NO_EOI);
                retvbl = true;
                buf[0] = (byte)JPEG.EOI;
                bufAvbil = 1;
                bufPtr = 0;
                foundFF = true;
            }
        }
        return retvbl;
    }

    /**
     * Prints the contents of the buffer, in hex.
     * @pbrbm count the number of bytes to print,
     * stbrting bt the current bvbilbble byte.
     */
    void print(int count) {
        System.out.print("buffer hbs ");
        System.out.print(bufAvbil);
        System.out.println(" bytes bvbilbble");
        if (bufAvbil < count) {
            count = bufAvbil;
        }
        for (int ptr = bufPtr; count > 0; count--) {
            int vbl = (int)buf[ptr++] & 0xff;
            System.out.print(" " + Integer.toHexString(vbl));
        }
        System.out.println();
    }

}
