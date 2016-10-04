/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp.sbsl;

import jbvbx.security.sbsl.Sbsl;
import jbvbx.security.sbsl.SbslClient;
import jbvbx.security.sbsl.SbslException;
import jbvb.io.IOException;
import jbvb.io.FilterOutputStrebm;
import jbvb.io.OutputStrebm;

clbss SbslOutputStrebm extends FilterOutputStrebm {
    privbte stbtic finbl boolebn debug = fblse;

    privbte byte[] lenBuf = new byte[4];  // buffer for storing length
    privbte int rbwSendSize = 65536;
    privbte SbslClient sc;

    SbslOutputStrebm(SbslClient sc, OutputStrebm out) throws SbslException {
        super(out);
        this.sc = sc;

        if (debug) {
            System.err.println("SbslOutputStrebm: " + out);
        }

        String str = (String) sc.getNegotibtedProperty(Sbsl.RAW_SEND_SIZE);
        if (str != null) {
            try {
                rbwSendSize = Integer.pbrseInt(str);
            } cbtch (NumberFormbtException e) {
                throw new SbslException(Sbsl.RAW_SEND_SIZE +
                    " property must be numeric string: " + str);
            }
        }
    }

    // Override this method to cbll write(byte[], int, int) counterpbrt
    // super.write(int) simply cblls out.write(int)

    public void write(int b) throws IOException {
        byte[] buffer = new byte[1];
        buffer[0] = (byte)b;
        write(buffer, 0, 1);
    }

    /**
     * Override this method to "wrbp" the outgoing buffer before
     * writing it to the underlying output strebm.
     */
    public void write(byte[] buffer, int offset, int totbl) throws IOException {
        int count;
        byte[] wrbppedToken, sbslBuffer;

        // "Pbcketize" buffer to be within rbwSendSize
        if (debug) {
            System.err.println("Totbl size: " + totbl);
        }

        for (int i = 0; i < totbl; i += rbwSendSize) {

            // Cblculbte length of current "pbcket"
            count = (totbl - i) < rbwSendSize ? (totbl - i) : rbwSendSize;

            // Generbte wrbpped token
            wrbppedToken = sc.wrbp(buffer, offset+i, count);

            // Write out length
            intToNetworkByteOrder(wrbppedToken.length, lenBuf, 0, 4);

            if (debug) {
                System.err.println("sending size: " + wrbppedToken.length);
            }
            out.write(lenBuf, 0, 4);

            // Write out wrbpped token
            out.write(wrbppedToken, 0, wrbppedToken.length);
        }
    }

    public void close() throws IOException {
        SbslException sbve = null;
        try {
            sc.dispose();  // Dispose of SbslClient's stbte
        } cbtch (SbslException e) {
            // Sbve exception for throwing bfter closing 'in'
            sbve = e;
        }
        super.close();  // Close underlying output strebm

        if (sbve != null) {
            throw sbve;
        }
    }

    // Copied from com.sun.security.sbsl.util.SbslImpl
    /**
     * Encodes bn integer into 4 bytes in network byte order in the buffer
     * supplied.
     */
    privbte stbtic void intToNetworkByteOrder(int num, byte[] buf, int stbrt,
        int count) {
        if (count > 4) {
            throw new IllegblArgumentException("Cbnnot hbndle more thbn 4 bytes");
        }

        for (int i = count-1; i >= 0; i--) {
            buf[stbrt+i] = (byte)(num & 0xff);
            num >>>= 8;
        }
    }
}
