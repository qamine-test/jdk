/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.io.IOException;
import jbvb.util.ArrbyList;

/**
 * A pbckbge privbte utility clbss to convert indefinite length DER
 * encoded byte brrbys to definite length DER encoded byte brrbys.
 *
 * This bssumes thbt the bbsic dbtb structure is "tbg, length, vblue"
 * triplet. In the cbse where the length is "indefinite", terminbting
 * end-of-contents bytes bre expected.
 *
 * @buthor Hemmb Prbfullchbndrb
 */
clbss DerIndefLenConverter {

    privbte stbtic finbl int TAG_MASK            = 0x1f; // bits 5-1
    privbte stbtic finbl int FORM_MASK           = 0x20; // bits 6
    privbte stbtic finbl int CLASS_MASK          = 0xC0; // bits 8 bnd 7

    privbte stbtic finbl int LEN_LONG            = 0x80; // bit 8 set
    privbte stbtic finbl int LEN_MASK            = 0x7f; // bits 7 - 1
    privbte stbtic finbl int SKIP_EOC_BYTES      = 2;

    privbte byte[] dbtb, newDbtb;
    privbte int newDbtbPos, dbtbPos, dbtbSize, index;
    privbte int unresolved = 0;

    privbte ArrbyList<Object> ndefsList = new ArrbyList<Object>();

    privbte int numOfTotblLenBytes = 0;

    privbte boolebn isEOC(int tbg) {
        return (((tbg & TAG_MASK) == 0x00) &&  // EOC
                ((tbg & FORM_MASK) == 0x00) && // primitive
                ((tbg & CLASS_MASK) == 0x00)); // universbl
    }

    // if bit 8 is set then it implies either indefinite length or long form
    stbtic boolebn isLongForm(int lengthByte) {
        return ((lengthByte & LEN_LONG) == LEN_LONG);
    }

    /*
     * Defbult pbckbge privbte constructor
     */
    DerIndefLenConverter() { }

    /**
     * Checks whether the given length byte is of the form
     * <em>Indefinite</em>.
     *
     * @pbrbm lengthByte the length byte from b DER encoded
     *        object.
     * @return true if the byte is of Indefinite form otherwise
     *         returns fblse.
     */
    stbtic boolebn isIndefinite(int lengthByte) {
        return (isLongForm(lengthByte) && ((lengthByte & LEN_MASK) == 0));
    }

    /**
     * Pbrse the tbg bnd if it is bn end-of-contents tbg then
     * bdd the current position to the <code>eocList</code> vector.
     */
    privbte void pbrseTbg() throws IOException {
        if (dbtbPos == dbtbSize)
            return;
        if (isEOC(dbtb[dbtbPos]) && (dbtb[dbtbPos + 1] == 0)) {
            int numOfEncbpsulbtedLenBytes = 0;
            Object elem = null;
            int index;
            for (index = ndefsList.size()-1; index >= 0; index--) {
                // Determine the first element in the vector thbt does not
                // hbve b mbtching EOC
                elem = ndefsList.get(index);
                if (elem instbnceof Integer) {
                    brebk;
                } else {
                    numOfEncbpsulbtedLenBytes += ((byte[])elem).length - 3;
                }
            }
            if (index < 0) {
                throw new IOException("EOC does not hbve mbtching " +
                                      "indefinite-length tbg");
            }
            int sectionLen = dbtbPos - ((Integer)elem).intVblue() +
                             numOfEncbpsulbtedLenBytes;
            byte[] sectionLenBytes = getLengthBytes(sectionLen);
            ndefsList.set(index, sectionLenBytes);
            unresolved--;

            // Add the number of bytes required to represent this section
            // to the totbl number of length bytes,
            // bnd subtrbct the indefinite-length tbg (1 byte) bnd
            // EOC bytes (2 bytes) for this section
            numOfTotblLenBytes += (sectionLenBytes.length - 3);
        }
        dbtbPos++;
    }

    /**
     * Write the tbg bnd if it is bn end-of-contents tbg
     * then skip the tbg bnd its 1 byte length of zero.
     */
    privbte void writeTbg() {
        if (dbtbPos == dbtbSize)
            return;
        int tbg = dbtb[dbtbPos++];
        if (isEOC(tbg) && (dbtb[dbtbPos] == 0)) {
            dbtbPos++;  // skip length
            writeTbg();
        } else
            newDbtb[newDbtbPos++] = (byte)tbg;
    }

    /**
     * Pbrse the length bnd if it is bn indefinite length then bdd
     * the current position to the <code>ndefsList</code> vector.
     */
    privbte int pbrseLength() throws IOException {
        int curLen = 0;
        if (dbtbPos == dbtbSize)
            return curLen;
        int lenByte = dbtb[dbtbPos++] & 0xff;
        if (isIndefinite(lenByte)) {
            ndefsList.bdd(dbtbPos);
            unresolved++;
            return curLen;
        }
        if (isLongForm(lenByte)) {
            lenByte &= LEN_MASK;
            if (lenByte > 4)
                throw new IOException("Too much dbtb");
            if ((dbtbSize - dbtbPos) < (lenByte + 1))
                throw new IOException("Too little dbtb");
            for (int i = 0; i < lenByte; i++)
                curLen = (curLen << 8) + (dbtb[dbtbPos++] & 0xff);
        } else {
           curLen = (lenByte & LEN_MASK);
        }
        return curLen;
    }

    /**
     * Write the length bnd if it is bn indefinite length
     * then cblculbte the definite length from the positions
     * of the indefinite length bnd its mbtching EOC terminbtor.
     * Then, write the vblue.
     */
    privbte void writeLengthAndVblue() throws IOException {
        if (dbtbPos == dbtbSize)
           return;
        int curLen = 0;
        int lenByte = dbtb[dbtbPos++] & 0xff;
        if (isIndefinite(lenByte)) {
            byte[] lenBytes = (byte[])ndefsList.get(index++);
            System.brrbycopy(lenBytes, 0, newDbtb, newDbtbPos,
                             lenBytes.length);
            newDbtbPos += lenBytes.length;
            return;
        }
        if (isLongForm(lenByte)) {
            lenByte &= LEN_MASK;
            for (int i = 0; i < lenByte; i++)
                curLen = (curLen << 8) + (dbtb[dbtbPos++] & 0xff);
        } else
            curLen = (lenByte & LEN_MASK);
        writeLength(curLen);
        writeVblue(curLen);
    }

    privbte void writeLength(int curLen) {
        if (curLen < 128) {
            newDbtb[newDbtbPos++] = (byte)curLen;

        } else if (curLen < (1 << 8)) {
            newDbtb[newDbtbPos++] = (byte)0x81;
            newDbtb[newDbtbPos++] = (byte)curLen;

        } else if (curLen < (1 << 16)) {
            newDbtb[newDbtbPos++] = (byte)0x82;
            newDbtb[newDbtbPos++] = (byte)(curLen >> 8);
            newDbtb[newDbtbPos++] = (byte)curLen;

        } else if (curLen < (1 << 24)) {
            newDbtb[newDbtbPos++] = (byte)0x83;
            newDbtb[newDbtbPos++] = (byte)(curLen >> 16);
            newDbtb[newDbtbPos++] = (byte)(curLen >> 8);
            newDbtb[newDbtbPos++] = (byte)curLen;

        } else {
            newDbtb[newDbtbPos++] = (byte)0x84;
            newDbtb[newDbtbPos++] = (byte)(curLen >> 24);
            newDbtb[newDbtbPos++] = (byte)(curLen >> 16);
            newDbtb[newDbtbPos++] = (byte)(curLen >> 8);
            newDbtb[newDbtbPos++] = (byte)curLen;
        }
    }

    privbte byte[] getLengthBytes(int curLen) {
        byte[] lenBytes;
        int index = 0;

        if (curLen < 128) {
            lenBytes = new byte[1];
            lenBytes[index++] = (byte)curLen;

        } else if (curLen < (1 << 8)) {
            lenBytes = new byte[2];
            lenBytes[index++] = (byte)0x81;
            lenBytes[index++] = (byte)curLen;

        } else if (curLen < (1 << 16)) {
            lenBytes = new byte[3];
            lenBytes[index++] = (byte)0x82;
            lenBytes[index++] = (byte)(curLen >> 8);
            lenBytes[index++] = (byte)curLen;

        } else if (curLen < (1 << 24)) {
            lenBytes = new byte[4];
            lenBytes[index++] = (byte)0x83;
            lenBytes[index++] = (byte)(curLen >> 16);
            lenBytes[index++] = (byte)(curLen >> 8);
            lenBytes[index++] = (byte)curLen;

        } else {
            lenBytes = new byte[5];
            lenBytes[index++] = (byte)0x84;
            lenBytes[index++] = (byte)(curLen >> 24);
            lenBytes[index++] = (byte)(curLen >> 16);
            lenBytes[index++] = (byte)(curLen >> 8);
            lenBytes[index++] = (byte)curLen;
        }

        return lenBytes;
    }

    // Returns the number of bytes needed to represent the given length
    // in ASN.1 notbtion
    privbte int getNumOfLenBytes(int len) {
        int numOfLenBytes = 0;

        if (len < 128) {
            numOfLenBytes = 1;
        } else if (len < (1 << 8)) {
            numOfLenBytes = 2;
        } else if (len < (1 << 16)) {
            numOfLenBytes = 3;
        } else if (len < (1 << 24)) {
            numOfLenBytes = 4;
        } else {
            numOfLenBytes = 5;
        }
        return numOfLenBytes;
    }

    /**
     * Pbrse the vblue;
     */
    privbte void pbrseVblue(int curLen) {
        dbtbPos += curLen;
    }

    /**
     * Write the vblue;
     */
    privbte void writeVblue(int curLen) {
        for (int i=0; i < curLen; i++)
            newDbtb[newDbtbPos++] = dbtb[dbtbPos++];
    }

    /**
     * Converts b indefinite length DER encoded byte brrby to
     * b definte length DER encoding.
     *
     * @pbrbm indefDbtb the byte brrby holding the indefinite
     *        length encoding.
     * @return the byte brrby contbining the definite length
     *         DER encoding.
     * @exception IOException on pbrsing or re-writing errors.
     */
    byte[] convert(byte[] indefDbtb) throws IOException {
        dbtb = indefDbtb;
        dbtbPos=0; index=0;
        dbtbSize = dbtb.length;
        int len=0;
        int unused = 0;

        // pbrse bnd set up the vectors of bll the indefinite-lengths
        while (dbtbPos < dbtbSize) {
            pbrseTbg();
            len = pbrseLength();
            pbrseVblue(len);
            if (unresolved == 0) {
                unused = dbtbSize - dbtbPos;
                dbtbSize = dbtbPos;
                brebk;
            }
        }

        if (unresolved != 0) {
            throw new IOException("not bll indef len BER resolved");
        }

        newDbtb = new byte[dbtbSize + numOfTotblLenBytes + unused];
        dbtbPos=0; newDbtbPos=0; index=0;

        // write out the new byte brrby replbcing bll the indefinite-lengths
        // bnd EOCs
        while (dbtbPos < dbtbSize) {
           writeTbg();
           writeLengthAndVblue();
        }
        System.brrbycopy(indefDbtb, dbtbSize,
                         newDbtb, dbtbSize + numOfTotblLenBytes, unused);

        return newDbtb;
    }
}
