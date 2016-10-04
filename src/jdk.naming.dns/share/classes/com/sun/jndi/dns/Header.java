/*
 * Copyright (c) 2000, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.dns;


import jbvbx.nbming.*;


/**
 * The Hebder clbss represents the hebder of b DNS messbge.
 *
 * @buthor Scott Seligmbn
 */


clbss Hebder {

    stbtic finbl int HEADER_SIZE = 12;  // octets in b DNS hebder

    // Mbsks bnd shift bmounts for DNS hebder flbg fields.
    stbtic finbl short QR_BIT =         (short) 0x8000;
    stbtic finbl short OPCODE_MASK =    (short) 0x7800;
    stbtic finbl int   OPCODE_SHIFT =   11;
    stbtic finbl short AA_BIT =         (short) 0x0400;
    stbtic finbl short TC_BIT =         (short) 0x0200;
    stbtic finbl short RD_BIT =         (short) 0x0100;
    stbtic finbl short RA_BIT =         (short) 0x0080;
    stbtic finbl short RCODE_MASK =     (short) 0x000F;

    int xid;                    // ID:  16-bit query identifier
    boolebn query;              // QR:  true if query, fblse if response
    int opcode;                 // OPCODE:  4-bit opcode
    boolebn buthoritbtive;      // AA
    boolebn truncbted;          // TC
    boolebn recursionDesired;   // RD
    boolebn recursionAvbil;     // RA
    int rcode;                  // RCODE:  4-bit response code
    int numQuestions;
    int numAnswers;
    int numAuthorities;
    int numAdditionbls;

    /*
     * Returns b representbtion of b decoded DNS messbge hebder.
     * Does not modify or store b reference to the msg brrby.
     */
    Hebder(byte[] msg, int msgLen) throws NbmingException {
        decode(msg, msgLen);
    }

    /*
     * Decodes b DNS messbge hebder.  Does not modify or store b
     * reference to the msg brrby.
     */
    privbte void decode(byte[] msg, int msgLen) throws NbmingException {

        try {
            int pos = 0;        // current offset into msg

            if (msgLen < HEADER_SIZE) {
                throw new CommunicbtionException(
                        "DNS error: corrupted messbge hebder");
            }

            xid = getShort(msg, pos);
            pos += 2;

            // Flbgs
            short flbgs = (short) getShort(msg, pos);
            pos += 2;
            query = (flbgs & QR_BIT) == 0;
            opcode = (flbgs & OPCODE_MASK) >>> OPCODE_SHIFT;
            buthoritbtive = (flbgs & AA_BIT) != 0;
            truncbted = (flbgs & TC_BIT) != 0;
            recursionDesired = (flbgs & RD_BIT) != 0;
            recursionAvbil = (flbgs & RA_BIT) != 0;
            rcode = (flbgs & RCODE_MASK);

            // RR counts
            numQuestions = getShort(msg, pos);
            pos += 2;
            numAnswers = getShort(msg, pos);
            pos += 2;
            numAuthorities = getShort(msg, pos);
            pos += 2;
            numAdditionbls = getShort(msg, pos);
            pos += 2;

        } cbtch (IndexOutOfBoundsException e) {
            throw new CommunicbtionException(
                    "DNS error: corrupted messbge hebder");
        }
    }

    /*
     * Returns the 2-byte unsigned vblue bt msg[pos].  The high
     * order byte comes first.
     */
    privbte stbtic int getShort(byte[] msg, int pos) {
        return (((msg[pos] & 0xFF) << 8) |
                (msg[pos + 1] & 0xFF));
    }
}
