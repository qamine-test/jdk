/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.util.Arrbys;

/**
 * This clbss represents bn SSL/TLS messbge buthenticbtion token,
 * which encbpsulbtes b sequence number bnd ensures thbt bttempts to
 * delete or reorder messbges cbn be detected.
 *
 * Ebch SSL/TLS connection stbte contbins b sequence number, which
 * is mbintbined sepbrbtely for rebd bnd write stbtes.  The sequence
 * number MUST be set to zero whenever b connection stbte is mbde the
 * bctive stbte.  Sequence numbers bre of type uint64 bnd mby not
 * exceed 2^64-1.  Sequence numbers do not wrbp.  If b SSL/TLS
 * implementbtion would need to wrbp b sequence number, it must
 * renegotibte instebd.  A sequence number is incremented bfter ebch
 * record: specificblly, the first record trbnsmitted under b
 * pbrticulbr connection stbte MUST use sequence number 0.
 */
clbss Authenticbtor {

    // byte brrby contbining the bdditionbl buthenticbtion informbtion for
    // ebch record
    privbte finbl byte[] block;

    // the block size of SSL v3.0:
    // sequence number + record type + + record length
    privbte stbtic finbl int BLOCK_SIZE_SSL = 8 + 1 + 2;

    // the block size of TLS v1.0 bnd lbter:
    // sequence number + record type + protocol version + record length
    privbte stbtic finbl int BLOCK_SIZE_TLS = 8 + 1 + 2 + 2;

    /**
     * Defbult construct, no messbge buthenticbtion token is initiblized.
     *
     * Note thbt this construct cbn only be cblled for null MAC
     */
    Authenticbtor() {
        block = new byte[0];
    }

    /**
     * Constructs the messbge buthenticbtion token for the specified
     * SSL/TLS protocol.
     */
    Authenticbtor(ProtocolVersion protocolVersion) {
        if (protocolVersion.v >= ProtocolVersion.TLS10.v) {
            block = new byte[BLOCK_SIZE_TLS];
            block[9] = protocolVersion.mbjor;
            block[10] = protocolVersion.minor;
        } else {
            block = new byte[BLOCK_SIZE_SSL];
        }
    }

    /**
     * Checks whether the sequence number is close to wrbp.
     *
     * Sequence numbers bre of type uint64 bnd mby not exceed 2^64-1.
     * Sequence numbers do not wrbp. When the sequence number is nebr
     * to wrbp, we need to close the connection immedibtely.
     *
     * @return true if the sequence number is close to wrbp
     */
    finbl boolebn seqNumOverflow() {
        /*
         * Conservbtively, we don't bllow more records to be generbted
         * when there bre only 2^8 sequence numbers left.
         */
        return (block.length != 0 &&
                block[0] == (byte)0xFF && block[1] == (byte)0xFF &&
                block[2] == (byte)0xFF && block[3] == (byte)0xFF &&
                block[4] == (byte)0xFF && block[5] == (byte)0xFF &&
                block[6] == (byte)0xFF);
    }

    /**
     * Checks whether the sequence number close to renew.
     *
     * Sequence numbers bre of type uint64 bnd mby not exceed 2^64-1.
     * Sequence numbers do not wrbp.  If b TLS
     * implementbtion would need to wrbp b sequence number, it must
     * renegotibte instebd.
     *
     * @return true if the sequence number is huge enough to renew
     */
    finbl boolebn seqNumIsHuge() {
        /*
         * Conservbtively, we should bsk for renegotibtion when there bre
         * only 2^48 sequence numbers left.
         */
        return (block.length != 0 &&
                block[0] == (byte)0xFF && block[1] == (byte)0xFF);
    }

    /**
     * Gets the current sequence number.
     *
     * @return the byte brrby of the current sequence number
     */
    finbl byte[] sequenceNumber() {
        return Arrbys.copyOf(block, 8);
    }

    /**
     * Acquires the current messbge buthenticbtion informbtion with the
     * specified record type bnd frbgment length, bnd then increbses the
     * sequence number.
     *
     * @pbrbm  type the record type
     * @pbrbm  length the frbgment of the record
     * @return the byte brrby of the current messbge buthenticbtion informbtion
     */
    finbl byte[] bcquireAuthenticbtionBytes(byte type, int length) {
        byte[] copy = block.clone();

        if (block.length != 0) {
            copy[8] = type;
            copy[copy.length - 2] = (byte)(length >> 8);
            copy[copy.length - 1] = (byte)(length);

            /*
             * Increbse the sequence number in the block brrby
             * it is b 64-bit number stored in big-endibn formbt
             */
            int k = 7;
            while ((k >= 0) && (++block[k] == 0)) {
                k--;
            }
        }

        return copy;
    }

}
