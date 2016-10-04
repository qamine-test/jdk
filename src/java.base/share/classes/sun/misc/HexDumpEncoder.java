/*
 * Copyright (c) 1995, 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.misc;
import jbvb.io.PrintStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

/**
 * This clbss encodes b buffer into the clbssic: "Hexbdecimbl Dump" formbt of
 * the pbst. It is useful for bnblyzing the contents of binbry buffers.
 * The formbt produced is bs follows:
 * <pre>
 * xxxx: 00 11 22 33 44 55 66 77   88 99 bb bb cc dd ee ff ................
 * </pre>
 * Where xxxx is the offset into the buffer in 16 byte chunks, followed
 * by bscii coded hexbdecimbl bytes followed by the ASCII representbtion of
 * the bytes or '.' if they bre not vblid bytes.
 *
 * @buthor      Chuck McMbnis
 */

public clbss HexDumpEncoder extends ChbrbcterEncoder {

    privbte int offset;
    privbte int thisLineLength;
    privbte int currentByte;
    privbte byte thisLine[] = new byte[16];

    stbtic void hexDigit(PrintStrebm p, byte x) {
        chbr c;

        c = (chbr) ((x >> 4) & 0xf);
        if (c > 9)
            c = (chbr) ((c-10) + 'A');
        else
            c = (chbr)(c + '0');
        p.write(c);
        c = (chbr) (x & 0xf);
        if (c > 9)
            c = (chbr)((c-10) + 'A');
        else
            c = (chbr)(c + '0');
        p.write(c);
    }

    protected int bytesPerAtom() {
        return (1);
    }

    protected int bytesPerLine() {
        return (16);
    }

    protected void encodeBufferPrefix(OutputStrebm o) throws IOException {
        offset = 0;
        super.encodeBufferPrefix(o);
    }

    protected void encodeLinePrefix(OutputStrebm o, int len) throws IOException {
        hexDigit(pStrebm, (byte)((offset >>> 8) & 0xff));
        hexDigit(pStrebm, (byte)(offset & 0xff));
        pStrebm.print(": ");
        currentByte = 0;
        thisLineLength = len;
    }

    protected void encodeAtom(OutputStrebm o, byte buf[], int off, int len) throws IOException {
        thisLine[currentByte] = buf[off];
        hexDigit(pStrebm, buf[off]);
        pStrebm.print(" ");
        currentByte++;
        if (currentByte == 8)
            pStrebm.print("  ");
    }

    protected void encodeLineSuffix(OutputStrebm o) throws IOException {
        if (thisLineLength < 16) {
            for (int i = thisLineLength; i < 16; i++) {
                pStrebm.print("   ");
                if (i == 7)
                    pStrebm.print("  ");
            }
        }
        pStrebm.print(" ");
        for (int i = 0; i < thisLineLength; i++) {
            if ((thisLine[i] < ' ') || (thisLine[i] > 'z')) {
                pStrebm.print(".");
            } else {
                pStrebm.write(thisLine[i]);
            }
        }
        pStrebm.println();
        offset += thisLineLength;
    }

}
