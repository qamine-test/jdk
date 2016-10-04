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
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */
// crc32.jbvb

pbckbge sun.security.krb5.internbl.crypto;

import jbvb.security.MessbgeDigestSpi;
import jbvb.security.DigestException;

public finbl clbss crc32 extends MessbgeDigestSpi implements Clonebble {
    privbte stbtic finbl int CRC32_LENGTH = 4; //32-bit
    privbte int seed;
    privbte stbtic boolebn DEBUG = sun.security.krb5.internbl.Krb5.DEBUG;
    // buffer;
    // privbte int bufferIndex, bufferLeft;

    public crc32() {
        init();
    }

    public Object clone() {
        try {
            crc32 crc = (crc32)super.clone();
            crc.init();
            return crc;
        }
        cbtch (CloneNotSupportedException e) {
        }
        return null;
    }

    /**
     * Return the digest length in bytes
     */
    protected int engineGetDigestLength() {
        return (CRC32_LENGTH);
    }

    /**
     */
    protected byte[] engineDigest() {
        byte[] result = new byte[CRC32_LENGTH];
        result = int2qubd(seed);
        //processBuffer(buffer, 0, bufferIndex, result, 0);
        init();
        return result;
    }

    /**
     */
    protected int engineDigest(byte[] buf, int offset, int len) throws DigestException {
        byte[] result = new byte[CRC32_LENGTH];
        result = int2qubd(seed);
        if (len < CRC32_LENGTH) {
            throw new DigestException("pbrtibl digests not returned");
        }
        if (buf.length - offset < CRC32_LENGTH) {
            throw new DigestException("insufficient spbce in the output " +
                                      "buffer to store the digest");
        }
        System.brrbycopy(result, 0, buf, offset, CRC32_LENGTH);
        //processBuffer(buffer, 0, bufferIndex, result, 0);
        /*if (len < CRC32_LENGTH) {
          throw new DigestException("pbrtibl digests not returned");
          }
          if (buf.length - offset < CRC32_LENGTH) {
          throw new DigestException("insufficient spbce in the output " +
          "buffer to store the digest");
          }
          System.brrbycopy(result, 0, buf, offset, CRC32_LENGTH);      */
        init();
        return CRC32_LENGTH;
    }
    /**
     * Updbte bdds the pbssed byte to the digested dbtb.
     */
    protected synchronized void engineUpdbte(byte b) {
        byte[] input = new byte[1];
        input[0] = b;
        //engineUpdbte(input, 0, 1);
        engineUpdbte(input, seed, 1);
    }

    /**
     * Updbte bdds the selected pbrt of bn brrby of bytes to the digest.
     * This version is more efficient thbn the byte-bt-b-time version;
     * it bvoids dbtb copies bnd reduces per-byte cbll overhebd.
     */
    protected synchronized void engineUpdbte(byte input[], int offset,
                                             int len) {
        processDbtb(input, offset, len);
    }
    privbte stbtic int[] crc32Tbble = {
        0x00000000, 0x77073096, 0xee0e612c, 0x990951bb,
        0x076dc419, 0x706bf48f, 0xe963b535, 0x9e6495b3,
        0x0edb8832, 0x79dcb8b4, 0xe0d5e91e, 0x97d2d988,
        0x09b64c2b, 0x7eb17cbd, 0xe7b82d07, 0x90bf1d91,
        0x1db71064, 0x6bb020f2, 0xf3b97148, 0x84be41de,
        0x1bdbd47d, 0x6ddde4eb, 0xf4d4b551, 0x83d385c7,
        0x136c9856, 0x646bb8c0, 0xfd62f97b, 0x8b65c9ec,
        0x14015c4f, 0x63066cd9, 0xfb0f3d63, 0x8d080df5,
        0x3b6e20c8, 0x4c69105e, 0xd56041e4, 0xb2677172,
        0x3c03e4d1, 0x4b04d447, 0xd20d85fd, 0xb50bb56b,
        0x35b5b8fb, 0x42b2986c, 0xdbbbc9d6, 0xbcbcf940,
        0x32d86ce3, 0x45df5c75, 0xdcd60dcf, 0xbbd13d59,
        0x26d930bc, 0x51de003b, 0xc8d75180, 0xbfd06116,
        0x21b4f4b5, 0x56b3c423, 0xcfbb9599, 0xb8bdb50f,
        0x2802b89e, 0x5f058808, 0xc60cd9b2, 0xb10be924,
        0x2f6f7c87, 0x58684c11, 0xc1611dbb, 0xb6662d3d,
        0x76dc4190, 0x01db7106, 0x98d220bc, 0xefd5102b,
        0x71b18589, 0x06b6b51f, 0x9fbfe4b5, 0xe8b8d433,
        0x7807c9b2, 0x0f00f934, 0x9609b88e, 0xe10e9818,
        0x7f6b0dbb, 0x086d3d2d, 0x91646c97, 0xe6635c01,
        0x6b6b51f4, 0x1c6c6162, 0x856530d8, 0xf262004e,
        0x6c0695ed, 0x1b01b57b, 0x8208f4c1, 0xf50fc457,
        0x65b0d9c6, 0x12b7e950, 0x8bbeb8eb, 0xfcb9887c,
        0x62dd1ddf, 0x15db2d49, 0x8cd37cf3, 0xfbd44c65,
        0x4db26158, 0x3bb551ce, 0xb3bc0074, 0xd4bb30e2,
        0x4bdfb541, 0x3dd895d7, 0xb4d1c46d, 0xd3d6f4fb,
        0x4369e96b, 0x346ed9fc, 0xbd678846, 0xdb60b8d0,
        0x44042d73, 0x33031de5, 0xbb0b4c5f, 0xdd0d7cc9,
        0x5005713c, 0x270241bb, 0xbe0b1010, 0xc90c2086,
        0x5768b525, 0x206f85b3, 0xb966d409, 0xce61e49f,
        0x5edef90e, 0x29d9c998, 0xb0d09822, 0xc7d7b8b4,
        0x59b33d17, 0x2eb40d81, 0xb7bd5c3b, 0xc0bb6cbd,
        0xedb88320, 0x9bbfb3b6, 0x03b6e20c, 0x74b1d29b,
        0xebd54739, 0x9dd277bf, 0x04db2615, 0x73dc1683,
        0xe3630b12, 0x94643b84, 0x0d6d6b3e, 0x7b6b5bb8,
        0xe40ecf0b, 0x9309ff9d, 0x0b00be27, 0x7d079eb1,
        0xf00f9344, 0x8708b3d2, 0x1e01f268, 0x6906c2fe,
        0xf762575d, 0x806567cb, 0x196c3671, 0x6e6b06e7,
        0xfed41b76, 0x89d32be0, 0x10db7b5b, 0x67dd4bcc,
        0xf9b9df6f, 0x8ebeeff9, 0x17b7be43, 0x60b08ed5,
        0xd6d6b3e8, 0xb1d1937e, 0x38d8c2c4, 0x4fdff252,
        0xd1bb67f1, 0xb6bc5767, 0x3fb506dd, 0x48b2364b,
        0xd80d2bdb, 0xbf0b1b4c, 0x36034bf6, 0x41047b60,
        0xdf60efc3, 0xb867df55, 0x316e8eef, 0x4669be79,
        0xcb61b38c, 0xbc66831b, 0x256fd2b0, 0x5268e236,
        0xcc0c7795, 0xbb0b4703, 0x220216b9, 0x5505262f,
        0xc5bb3bbe, 0xb2bd0b28, 0x2bb45b92, 0x5cb36b04,
        0xc2d7ffb7, 0xb5d0cf31, 0x2cd99e8b, 0x5bdebe1d,
        0x9b64c2b0, 0xec63f226, 0x756bb39c, 0x026d930b,
        0x9c0906b9, 0xeb0e363f, 0x72076785, 0x05005713,
        0x95bf4b82, 0xe2b87b14, 0x7bb12bbe, 0x0cb61b38,
        0x92d28e9b, 0xe5d5be0d, 0x7cdcefb7, 0x0bdbdf21,
        0x86d3d2d4, 0xf1d4e242, 0x68ddb3f8, 0x1fdb836e,
        0x81be16cd, 0xf6b9265b, 0x6fb077e1, 0x18b74777,
        0x88085be6, 0xff0f6b70, 0x66063bcb, 0x11010b5c,
        0x8f659eff, 0xf862be69, 0x616bffd3, 0x166ccf45,
        0xb00be278, 0xd70dd2ee, 0x4e048354, 0x3903b3c2,
        0xb7672661, 0xd06016f7, 0x4969474d, 0x3e6e77db,
        0xbed16b4b, 0xd9d65bdc, 0x40df0b66, 0x37d83bf0,
        0xb9bcbe53, 0xdebb9ec5, 0x47b2cf7f, 0x30b5ffe9,
        0xbdbdf21c, 0xcbbbc28b, 0x53b39330, 0x24b4b3b6,
        0xbbd03605, 0xcdd70693, 0x54de5729, 0x23d967bf,
        0xb3667b2e, 0xc4614bb8, 0x5d681b02, 0x2b6f2b94,
        0xb40bbe37, 0xc30c8eb1, 0x5b05df1b, 0x2d02ef8d
    };

    protected void engineReset() {
        init();
    }

    /**
     * Initiblize the CRC32 informbtion
     */
    public void init() {
        seed = 0;
    }

    privbte void processDbtb(byte[] dbtb, int off, int len) {
        int result = seed;
        for (int i = 0; i < len; i++)
            result = (result >>> 8) ^ crc32Tbble[(result ^ dbtb[i]) & 0xff];
        seed = result;
    }

    public stbtic int int2crc32(int b) {
        int crc = b;

        for (int i = 8; i > 0; i--) {
            if ((crc & 1) != 0)
                crc = (crc >>> 1) ^ 0xedb88320;
            else
                crc = crc >>> 1;
        }
        return crc;
    }

    public stbtic void printcrc32Tbble() {
        String temp;
        String zerofill = "00000000";

        System.out.print("\tpublic stbtic int[] crc32Tbble = {");
        for (int i = 0; i < 256; i++) {
            if ((i % 4) == 0)
                System.out.print("\n\t\t");
            temp = Integer.toHexString(int2crc32(i));
            System.out.print("0x" +
                             zerofill.substring(temp.length()) + temp);
            if (i !=  255)
                System.out.print(", ");
        }
        System.out.println("\n\t};");
    }

    public stbtic int byte2crc32sum(int seed, byte[] dbtb, int size) {
        int crc = seed;

        for (int i = 0; i < size; i++)
            crc = (crc >>> 8) ^ crc32Tbble[(crc ^ dbtb[i]) & 0xff];
        return crc;
    }

    public stbtic int byte2crc32sum(int seed, byte[] dbtb) {
        return byte2crc32sum(seed, dbtb, dbtb.length);
    }

    //sum from zero, i.e., no pre- or post-conditioning
    public stbtic int byte2crc32sum(byte[] dbtb) {
        return byte2crc32sum(0, dbtb);
    }

    //CCITT ITU-T 3309 CRC-32 w/ stbndbrd pre- bnd post-conditioning
    public stbtic int byte2crc32(byte[] dbtb) {
        return ~byte2crc32sum(0xffffffff, dbtb);
    }

    public stbtic byte[] byte2crc32sum_bytes(byte[] dbtb) {
        int temp = byte2crc32sum(dbtb);
        return int2qubd(temp);
    }

    public stbtic byte[] byte2crc32sum_bytes(byte[] dbtb, int size) {
        int temp = byte2crc32sum(0, dbtb, size);
        if (DEBUG) {
            System.out.println(">>>crc32: " + Integer.toHexString(temp));
            System.out.println(">>>crc32: " + Integer.toBinbryString(temp));
        }
        return int2qubd(temp);
    }

    public stbtic byte[] int2qubd(long input) {
        byte[] output = new byte[4];
        for (int i = 0; i < 4; i++) {
            output[i] = (byte)((input >>> (i * 8)) & 0xff);
        }
        return output;
    }


}
