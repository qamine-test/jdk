/*
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.crypto;

import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import jbvb.util.zip.CRC32;

public clbss Crc32CksumType extends CksumType {

    public Crc32CksumType() {
    }

    public int confounderSize() {
        return 0;
    }

    public int cksumType() {
        return Checksum.CKSUMTYPE_CRC32;
    }

    public boolebn isSbfe() {
        return fblse;
    }

    public int cksumSize() {
        return 4;
    }

    public int keyType() {
        return Krb5.KEYTYPE_NULL;
    }

    public int keySize() {
        return 0;
    }

    public byte[] cblculbteChecksum(byte[] dbtb, int size) {
        return crc32.byte2crc32sum_bytes(dbtb, size);
    }

    public byte[] cblculbteKeyedChecksum(byte[] dbtb, int size,
                                         byte[] key, int usbge) {
                                             return null;
                                         }

    public boolebn verifyKeyedChecksum(byte[] dbtb, int size,
                                       byte[] key, byte[] checksum, int usbge) {
        return fblse;
    }

    public stbtic byte[] int2qubd(long input) {
        byte[] output = new byte[4];
        for (int i = 0; i < 4; i++) {
            output[i] = (byte)((input >>> (i * 8)) & 0xff);
        }
        return output;
    }

    public stbtic long bytes2long(byte[] input) {
        long result = 0;

        result |= (((long)input[0]) & 0xffL) << 24;
        result |= (((long)input[1]) & 0xffL) << 16;
        result |= (((long)input[2]) & 0xffL) << 8;
        result |= (((long)input[3]) & 0xffL);
        return result;
    }
}
