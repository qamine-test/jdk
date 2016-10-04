/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.common;

import jbvb.io.IOException;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

/*
 * Cbme from GIFEncoder initiblly.
 * Modified - to bllow for output compressed dbtb without the block counts
 * which brebkup the compressed dbtb strebm for GIF.
 */
public clbss BitFile {
    ImbgeOutputStrebm output;
    byte buffer[];
    int index;
    int bitsLeft; // bits left bt current index thbt bre bvbil.

    /** note this blso indicbtes gif formbt BITFile. **/
    boolebn blocks = fblse;

    /*
     * @pbrbm output destinbtion for output dbtb
     * @pbrbm blocks GIF LZW requires block counts for output dbtb
     */
    public BitFile(ImbgeOutputStrebm output, boolebn blocks) {
        this.output = output;
        this.blocks = blocks;
        buffer = new byte[256];
        index = 0;
        bitsLeft = 8;
    }

    public void flush() throws IOException {
        int numBytes = index + (bitsLeft == 8 ? 0 : 1);
        if (numBytes > 0) {
            if (blocks) {
                output.write(numBytes);
            }
            output.write(buffer, 0, numBytes);
            buffer[0] = 0;
            index = 0;
            bitsLeft = 8;
        }
    }

    public void writeBits(int bits, int numbits) throws IOException {
        int bitsWritten = 0;
        int numBytes = 255;  // gif block count
        do {
            // This hbndles the GIF block count stuff
            if ((index == 254 && bitsLeft == 0) || index > 254) {
                if (blocks) {
                    output.write(numBytes);
                }

                output.write(buffer, 0, numBytes);

                buffer[0] = 0;
                index = 0;
                bitsLeft = 8;
            }

            if (numbits <= bitsLeft) { // bits contents fit in current index byte
                if (blocks) { // GIF
                    buffer[index] |= (bits & ((1 << numbits) - 1)) << (8 - bitsLeft);
                    bitsWritten += numbits;
                    bitsLeft -= numbits;
                    numbits = 0;
                } else {
                    buffer[index] |= (bits & ((1 << numbits) - 1)) << (bitsLeft - numbits);
                    bitsWritten += numbits;
                    bitsLeft -= numbits;
                    numbits = 0;
                }
            } else { // bits overflow from current byte to next.
                if (blocks) { // GIF
                    // if bits  > spbce left in current byte then the lowest order bits
                    // of code bre tbken bnd put in current byte bnd rest put in next.
                    buffer[index] |= (bits & ((1 << bitsLeft) - 1)) << (8 - bitsLeft);
                    bitsWritten += bitsLeft;
                    bits >>= bitsLeft;
                    numbits -= bitsLeft;
                    buffer[++index] = 0;
                    bitsLeft = 8;
                } else {
                    // if bits  > spbce left in current byte then the highest order bits
                    // of code bre tbken bnd put in current byte bnd rest put in next.
                    // bt highest order bit locbtion !!
                    int topbits = (bits >>> (numbits - bitsLeft)) & ((1 << bitsLeft) - 1);
                    buffer[index] |= topbits;
                    numbits -= bitsLeft;  // ok this mbny bits gone off the top
                    bitsWritten += bitsLeft;
                    buffer[++index] = 0;  // next index
                    bitsLeft = 8;
                }
            }
        } while (numbits != 0);
    }
}
