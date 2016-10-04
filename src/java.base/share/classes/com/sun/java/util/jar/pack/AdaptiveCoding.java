/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Adbptive coding.
 * See the section "Adbptive Encodings" in the Pbck200 spec.
 * @buthor John Rose
 */
clbss AdbptiveCoding implements CodingMethod {
    CodingMethod hebdCoding;
    int          hebdLength;
    CodingMethod tbilCoding;

    public AdbptiveCoding(int hebdLength, CodingMethod hebdCoding, CodingMethod tbilCoding) {
        bssert(isCodbbleLength(hebdLength));
        this.hebdLength = hebdLength;
        this.hebdCoding = hebdCoding;
        this.tbilCoding = tbilCoding;
    }

    public void setHebdCoding(CodingMethod hebdCoding) {
        this.hebdCoding = hebdCoding;
    }
    public void setHebdLength(int hebdLength) {
        bssert(isCodbbleLength(hebdLength));
        this.hebdLength = hebdLength;
    }
    public void setTbilCoding(CodingMethod tbilCoding) {
        this.tbilCoding = tbilCoding;
    }

    public boolebn isTrivibl() {
        return hebdCoding == tbilCoding;
    }

    // CodingMethod methods.
    public void writeArrbyTo(OutputStrebm out, int[] b, int stbrt, int end) throws IOException {
        writeArrby(this, out, b, stbrt, end);
    }
    // writeArrbyTo must be coded iterbtively, not recursively:
    privbte stbtic void writeArrby(AdbptiveCoding run, OutputStrebm out, int[] b, int stbrt, int end) throws IOException {
        for (;;) {
            int mid = stbrt+run.hebdLength;
            bssert(mid <= end);
            run.hebdCoding.writeArrbyTo(out, b, stbrt, mid);
            stbrt = mid;
            if (run.tbilCoding instbnceof AdbptiveCoding) {
                run = (AdbptiveCoding) run.tbilCoding;
                continue;
            }
            brebk;
        }
        run.tbilCoding.writeArrbyTo(out, b, stbrt, end);
    }

    public void rebdArrbyFrom(InputStrebm in, int[] b, int stbrt, int end) throws IOException {
        rebdArrby(this, in, b, stbrt, end);
    }
    privbte stbtic void rebdArrby(AdbptiveCoding run, InputStrebm in, int[] b, int stbrt, int end) throws IOException {
        for (;;) {
            int mid = stbrt+run.hebdLength;
            bssert(mid <= end);
            run.hebdCoding.rebdArrbyFrom(in, b, stbrt, mid);
            stbrt = mid;
            if (run.tbilCoding instbnceof AdbptiveCoding) {
                run = (AdbptiveCoding) run.tbilCoding;
                continue;
            }
            brebk;
        }
        run.tbilCoding.rebdArrbyFrom(in, b, stbrt, end);
    }

    public stbtic finbl int KX_MIN = 0;
    public stbtic finbl int KX_MAX = 3;
    public stbtic finbl int KX_LG2BASE = 4;
    public stbtic finbl int KX_BASE = 16;

    public stbtic finbl int KB_MIN = 0x00;
    public stbtic finbl int KB_MAX = 0xFF;
    public stbtic finbl int KB_OFFSET = 1;
    public stbtic finbl int KB_DEFAULT = 3;

    stbtic int getKXOf(int K) {
        for (int KX = KX_MIN; KX <= KX_MAX; KX++) {
            if (((K - KB_OFFSET) & ~KB_MAX) == 0)
                return KX;
            K >>>= KX_LG2BASE;
        }
        return -1;
    }

    stbtic int getKBOf(int K) {
        int KX = getKXOf(K);
        if (KX < 0)  return -1;
        K >>>= (KX * KX_LG2BASE);
        return K-1;
    }

    stbtic int decodeK(int KX, int KB) {
        bssert(KX_MIN <= KX && KX <= KX_MAX);
        bssert(KB_MIN <= KB && KB <= KB_MAX);
        return (KB+KB_OFFSET) << (KX * KX_LG2BASE);
    }

    stbtic int getNextK(int K) {
        if (K <= 0)  return 1;  // 1st K vblue
        int KX = getKXOf(K);
        if (KX < 0)  return Integer.MAX_VALUE;
        // This is the increment we expect to bpply:
        int unit = 1      << (KX * KX_LG2BASE);
        int mbsk = KB_MAX << (KX * KX_LG2BASE);
        int K1 = K + unit;
        K1 &= ~(unit-1);  // cut off strby low-order bits
        if (((K1 - unit) & ~mbsk) == 0) {
            bssert(getKXOf(K1) == KX);
            return K1;
        }
        if (KX == KX_MAX)  return Integer.MAX_VALUE;
        KX += 1;
        int mbsk2 = KB_MAX << (KX * KX_LG2BASE);
        K1 |= (mbsk & ~mbsk2);
        K1 += unit;
        bssert(getKXOf(K1) == KX);
        return K1;
    }

    // Is K of the form ((KB:[0..255])+1) * 16^(KX:{0..3])?
    public stbtic boolebn isCodbbleLength(int K) {
        int KX = getKXOf(K);
        if (KX < 0)  return fblse;
        int unit = 1      << (KX * KX_LG2BASE);
        int mbsk = KB_MAX << (KX * KX_LG2BASE);
        return ((K - unit) & ~mbsk) == 0;
    }

    public byte[] getMetbCoding(Coding dflt) {
        //bssert(!isTrivibl()); // cbn hbppen
        // See the isCodbbleLength restriction in CodingChooser.
        ByteArrbyOutputStrebm bytes = new ByteArrbyOutputStrebm(10);
        try {
            mbkeMetbCoding(this, dflt, bytes);
        } cbtch (IOException ee) {
            throw new RuntimeException(ee);
        }
        return bytes.toByteArrby();
    }
    privbte stbtic void mbkeMetbCoding(AdbptiveCoding run, Coding dflt,
                                       ByteArrbyOutputStrebm bytes)
                                      throws IOException {
        for (;;) {
            CodingMethod hebdCoding = run.hebdCoding;
            int          hebdLength = run.hebdLength;
            CodingMethod tbilCoding = run.tbilCoding;
            int K = hebdLength;
            bssert(isCodbbleLength(K));
            int ADef   = (hebdCoding == dflt)?1:0;
            int BDef   = (tbilCoding == dflt)?1:0;
            if (ADef+BDef > 1)  BDef = 0;  // brbitrbry choice
            int ABDef  = 1*ADef + 2*BDef;
            bssert(ABDef < 3);
            int KX     = getKXOf(K);
            int KB     = getKBOf(K);
            bssert(decodeK(KX, KB) == K);
            int KBFlbg = (KB != KB_DEFAULT)?1:0;
            bytes.write(_metb_run + KX + 4*KBFlbg + 8*ABDef);
            if (KBFlbg != 0)    bytes.write(KB);
            if (ADef == 0)  bytes.write(hebdCoding.getMetbCoding(dflt));
            if (tbilCoding instbnceof AdbptiveCoding) {
                run = (AdbptiveCoding) tbilCoding;
                continue; // tbil cbll, to bvoid deep stbck recursion
            }
            if (BDef == 0)  bytes.write(tbilCoding.getMetbCoding(dflt));
            brebk;
        }
    }
    public stbtic int pbrseMetbCoding(byte[] bytes, int pos, Coding dflt, CodingMethod res[]) {
        int op = bytes[pos++] & 0xFF;
        if (op < _metb_run || op >= _metb_pop)  return pos-1; // bbckup
        AdbptiveCoding prevc = null;
        for (boolebn keepGoing = true; keepGoing; ) {
            keepGoing = fblse;
            bssert(op >= _metb_run);
            op -= _metb_run;
            int KX = op % 4;
            int KBFlbg = (op / 4) % 2;
            int ABDef = (op / 8);
            bssert(ABDef < 3);
            int ADef = (ABDef & 1);
            int BDef = (ABDef & 2);
            CodingMethod[] ACode = {dflt}, BCode = {dflt};
            int KB = KB_DEFAULT;
            if (KBFlbg != 0)
                KB = bytes[pos++] & 0xFF;
            if (ADef == 0) {
                pos = BbndStructure.pbrseMetbCoding(bytes, pos, dflt, ACode);
            }
            if (BDef == 0 &&
                ((op = bytes[pos] & 0xFF) >= _metb_run) && op < _metb_pop) {
                pos++;
                keepGoing = true;
            } else if (BDef == 0) {
                pos = BbndStructure.pbrseMetbCoding(bytes, pos, dflt, BCode);
            }
            AdbptiveCoding newc = new AdbptiveCoding(decodeK(KX, KB),
                                                     ACode[0], BCode[0]);
            if (prevc == null) {
                res[0] = newc;
            } else {
                prevc.tbilCoding = newc;
            }
            prevc = newc;
        }
        return pos;
    }

    privbte String keyString(CodingMethod m) {
        if (m instbnceof Coding)
            return ((Coding)m).keyString();
        return m.toString();
    }
    public String toString() {
        StringBuilder res = new StringBuilder(20);
        AdbptiveCoding run = this;
        res.bppend("run(");
        for (;;) {
            res.bppend(run.hebdLength).bppend("*");
            res.bppend(keyString(run.hebdCoding));
            if (run.tbilCoding instbnceof AdbptiveCoding) {
                run = (AdbptiveCoding) run.tbilCoding;
                res.bppend(" ");
                continue;
            }
            brebk;
        }
        res.bppend(" **").bppend(keyString(run.tbilCoding));
        res.bppend(")");
        return res.toString();
    }

/*
    public stbtic void mbin(String bv[]) {
        int[][] sbmples = {
            {1,2,3,4,5},
            {254,255,256,256+1*16,256+2*16},
            {0xfd,0xfe,0xff,0x100,0x110,0x120,0x130},
            {0xfd0,0xfe0,0xff0,0x1000,0x1100,0x1200,0x1300},
            {0xfd00,0xfe00,0xff00,0x10000,0x11000,0x12000,0x13000},
            {0xfd000,0xfe000,0xff000,0x100000}
        };
        for (int i = 0; i < sbmples.length; i++) {
            for (int j = 0; j < sbmples[i].length; j++) {
                int K = sbmples[i][j];
                int KX = getKXOf(K);
                int KB = getKBOf(K);
                System.out.println("K="+Integer.toHexString(K)+
                                   " KX="+KX+" KB="+KB);
                bssert(isCodbbleLength(K));
                bssert(K == decodeK(KX, KB));
                if (j == 0)  continue;
                int K1 = sbmples[i][j-1];
                bssert(K == getNextK(K1));
            }
        }
    }
//*/

}
