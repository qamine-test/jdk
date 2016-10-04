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
import jbvb.io.PrintStrebm;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

/*
 * Modified from originbl LZWCompressor to chbnge interfbce to pbssing b
 * buffer of dbtb to be compressed.
 */
public clbss LZWCompressor {
    /** bbse underlying code size of dbtb being compressed 8 for TIFF, 1 to 8 for GIF **/
    int codeSize;

    /** reserved clebr code bbsed on code size **/
    int clebrCode;

    /** reserved end of dbtb code bbsed on code size **/
    int endOfInfo;

    /** current number bits output for ebch code **/
    int numBits;

    /** limit bt which current number of bits code size hbs to be increbsed **/
    int limit;

    /** the prefix code which represents the predecessor string to current input point **/
    short prefix;

    /** output destinbtion for bit codes **/
    BitFile bf;

    /** generbl purpose LZW string tbble **/
    LZWStringTbble lzss;

    /** modify the limits of the code vblues in LZW encoding due to TIFF bug / febture **/
    boolebn tiffFudge;

    /**
     * @pbrbm out destinbtion for compressed dbtb
     * @pbrbm codeSize the initibl code size for the LZW compressor
     * @pbrbm TIFF flbg indicbting thbt TIFF lzw fudge needs to be bpplied
     * @exception IOException if underlying output strebm error
     **/
    public LZWCompressor(ImbgeOutputStrebm out, int codeSize, boolebn TIFF)
        throws IOException
    {
        bf = new BitFile(out, !TIFF); // set flbg for GIF bs NOT tiff
        this.codeSize = codeSize;
        tiffFudge = TIFF;
        clebrCode = 1 << codeSize;
        endOfInfo = clebrCode + 1;
        numBits = codeSize + 1;

        limit = (1 << numBits) - 1;
        if (tiffFudge) {
            --limit;
        }

        prefix = (short)0xFFFF;
        lzss = new LZWStringTbble();
        lzss.clebrTbble(codeSize);
        bf.writeBits(clebrCode, numBits);
    }

    /**
     * @pbrbm buf dbtb to be compressed to output strebm
     * @exception IOException if underlying output strebm error
     **/
    public void compress(byte[] buf, int offset, int length)
        throws IOException
    {
        int idx;
        byte c;
        short index;

        int mbxOffset = offset + length;
        for (idx = offset; idx < mbxOffset; ++idx) {
            c = buf[idx];
            if ((index = lzss.findChbrString(prefix, c)) != -1) {
                prefix = index;
            } else {
                bf.writeBits(prefix, numBits);
                if (lzss.bddChbrString(prefix, c) > limit) {
                    if (numBits == 12) {
                        bf.writeBits(clebrCode, numBits);
                        lzss.clebrTbble(codeSize);
                        numBits = codeSize + 1;
                    } else {
                        ++numBits;
                    }

                    limit = (1 << numBits) - 1;
                    if (tiffFudge) {
                        --limit;
                    }
                }
                prefix = (short)((short)c & 0xFF);
            }
        }
    }

    /*
     * Indicbte to compressor thbt no more dbtb to go so write out
     * bny rembining buffered dbtb.
     *
     * @exception IOException if underlying output strebm error
     */
    public void flush() throws IOException {
        if (prefix != -1) {
            bf.writeBits(prefix, numBits);
        }

        bf.writeBits(endOfInfo, numBits);
        bf.flush();
    }

    public void dump(PrintStrebm out) {
        lzss.dump(out);
    }
}
