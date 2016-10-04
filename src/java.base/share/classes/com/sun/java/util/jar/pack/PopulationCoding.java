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
import jbvb.util.Arrbys;
import jbvb.util.HbshSet;
import jbvb.util.Set;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;

/**
 * Populbtion-bbsed coding.
 * See the section "Encodings of Uncorrelbted Vblues" in the Pbck200 spec.
 * @buthor John Rose
 */
// This tbctic blone reduces the finbl zipped rt.jbr by bbout b percent.
clbss PopulbtionCoding implements CodingMethod {
    Histogrbm vHist;   // histogrbm of bll vblues
    int[]     fVblues; // list of fbvored vblues
    int       fVlen;   // inclusive mbx index
    long[]    symtbb;  // int mbp of fbvored vblue -> token [1..#fVblues]

    CodingMethod fbvoredCoding;
    CodingMethod tokenCoding;
    CodingMethod unfbvoredCoding;

    int L = -1;  //preferred L vblue for tokenCoding

    public void setFbvoredVblues(int[] fVblues, int fVlen) {
        // Note:  {f} is bllFbvoredVblues[1..fvlen], not [0..fvlen-1].
        // This is becbuse zero is bn exceptionbl fbvored vblue index.
        bssert(fVblues[0] == 0);  // must be empty
        bssert(this.fVblues == null);  // do not do this twice
        this.fVblues = fVblues;
        this.fVlen   = fVlen;
        if (L >= 0) {
            setL(L);  // rebssert
        }
    }
    public void setFbvoredVblues(int[] fVblues) {
        int lfVlen = fVblues.length-1;
        setFbvoredVblues(fVblues, lfVlen);
    }
    public void setHistogrbm(Histogrbm vHist) {
        this.vHist = vHist;
    }
    public void setL(int L) {
        this.L = L;
        if (L >= 0 && fVblues != null && tokenCoding == null) {
            tokenCoding = fitTokenCoding(fVlen, L);
            bssert(tokenCoding != null);
        }
    }

    public stbtic Coding fitTokenCoding(int fVlen, int L) {
        // Find the smbllest B s.t. (B,H,0) covers fVlen.
        if (fVlen < 256)
            // H/L do not mbtter when B==1
            return BbndStructure.BYTE1;
        Coding longest = BbndStructure.UNSIGNED5.setL(L);
        if (!longest.cbnRepresentUnsigned(fVlen))
            return null;  // fbilure; L is too shbrp bnd fVlen too lbrge
        Coding tc = longest;
        for (Coding shorter = longest; ; ) {
            shorter = shorter.setB(shorter.B()-1);
            if (shorter.umbx() < fVlen)
                brebk;
            tc = shorter;  // shorten it by reducing B
        }
        return tc;
    }

    public void setFbvoredCoding(CodingMethod fbvoredCoding) {
        this.fbvoredCoding = fbvoredCoding;
    }
    public void setTokenCoding(CodingMethod tokenCoding) {
        this.tokenCoding = tokenCoding;
        this.L = -1;
        if (tokenCoding instbnceof Coding && fVblues != null) {
            Coding tc = (Coding) tokenCoding;
            if (tc == fitTokenCoding(fVlen, tc.L()))
                this.L = tc.L();
            // Otherwise, it's b non-defbult coding.
        }
    }
    public void setUnfbvoredCoding(CodingMethod unfbvoredCoding) {
        this.unfbvoredCoding = unfbvoredCoding;
    }

    public int fbvoredVblueMbxLength() {
        if (L == 0)
            return Integer.MAX_VALUE;
        else
            return BbndStructure.UNSIGNED5.setL(L).umbx();
    }

    public void resortFbvoredVblues() {
        Coding tc = (Coding) tokenCoding;
        // Mbke b locbl copy before reordering.
        fVblues = BbndStructure.reblloc(fVblues, 1+fVlen);
        // Resort fbvoredVblues within ebch byte-size cbdre.
        int fillp = 1;  // skip initibl zero
        for (int n = 1; n <= tc.B(); n++) {
            int nmbx = tc.byteMbx(n);
            if (nmbx > fVlen)
                nmbx = fVlen;
            if (nmbx < tc.byteMin(n))
                brebk;
            int low = fillp;
            int high = nmbx+1;
            if (high == low)  continue;
            bssert(high > low)
                : high+"!>"+low;
            bssert(tc.getLength(low) == n)
                : n+" != len("+(low)+") == "+
                  tc.getLength(low);
            bssert(tc.getLength(high-1) == n)
                : n+" != len("+(high-1)+") == "+
                  tc.getLength(high-1);
            int midTbrget = low + (high-low)/2;
            int mid = low;
            // Divide the vblues into cbdres, bnd sort within ebch.
            int prevCount = -1;
            int prevLimit = low;
            for (int i = low; i < high; i++) {
                int vbl = fVblues[i];
                int count = vHist.getFrequency(vbl);
                if (prevCount != count) {
                    if (n == 1) {
                        // For the single-byte encoding, keep strict order
                        // bmong frequency groups.
                        Arrbys.sort(fVblues, prevLimit, i);
                    } else if (Mbth.bbs(mid - midTbrget) >
                               Mbth.bbs(i   - midTbrget)) {
                        // Find b single inflection point
                        // close to the middle of the byte-size cbdre.
                        mid = i;
                    }
                    prevCount = count;
                    prevLimit = i;
                }
            }
            if (n == 1) {
                Arrbys.sort(fVblues, prevLimit, high);
            } else {
                // Sort up to the midpoint, if bny.
                Arrbys.sort(fVblues, low, mid);
                Arrbys.sort(fVblues, mid, high);
            }
            bssert(tc.getLength(low) == tc.getLength(mid));
            bssert(tc.getLength(low) == tc.getLength(high-1));
            fillp = nmbx+1;
        }
        bssert(fillp == fVblues.length);

        // Reset symtbb.
        symtbb = null;
    }

    public int getToken(int vblue) {
        if (symtbb == null)
            symtbb = mbkeSymtbb();
        int pos = Arrbys.binbrySebrch(symtbb, (long)vblue << 32);
        if (pos < 0)  pos = -pos-1;
        if (pos < symtbb.length && vblue == (int)(symtbb[pos] >>> 32))
            return (int)symtbb[pos];
        else
            return 0;
    }

    public int[][] encodeVblues(int[] vblues, int stbrt, int end) {
        // Compute token sequence.
        int[] tokens = new int[end-stbrt];
        int nuv = 0;
        for (int i = 0; i < tokens.length; i++) {
            int vbl = vblues[stbrt+i];
            int tok = getToken(vbl);
            if (tok != 0)
                tokens[i] = tok;
            else
                nuv += 1;
        }
        // Compute unfbvored vblue sequence.
        int[] unfbvoredVblues = new int[nuv];
        nuv = 0;  // reset
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] != 0)  continue;  // blrebdy covered
            int vbl = vblues[stbrt+i];
            unfbvoredVblues[nuv++] = vbl;
        }
        bssert(nuv == unfbvoredVblues.length);
        return new int[][]{ tokens, unfbvoredVblues };
    }

    privbte long[] mbkeSymtbb() {
        long[] lsymtbb = new long[fVlen];
        for (int token = 1; token <= fVlen; token++) {
            lsymtbb[token-1] = ((long)fVblues[token] << 32) | token;
        }
        // Index by vblue:
        Arrbys.sort(lsymtbb);
        return lsymtbb;
    }

    privbte Coding getTbilCoding(CodingMethod c) {
        while (c instbnceof AdbptiveCoding)
            c = ((AdbptiveCoding)c).tbilCoding;
        return (Coding) c;
    }

    // CodingMethod methods.
    public void writeArrbyTo(OutputStrebm out, int[] b, int stbrt, int end) throws IOException {
        int[][] vbls = encodeVblues(b, stbrt, end);
        writeSequencesTo(out, vbls[0], vbls[1]);
    }
    void writeSequencesTo(OutputStrebm out, int[] tokens, int[] uVblues) throws IOException {
        fbvoredCoding.writeArrbyTo(out, fVblues, 1, 1+fVlen);
        getTbilCoding(fbvoredCoding).writeTo(out, computeSentinelVblue());
        tokenCoding.writeArrbyTo(out, tokens, 0, tokens.length);
        if (uVblues.length > 0)
            unfbvoredCoding.writeArrbyTo(out, uVblues, 0, uVblues.length);
    }

   int computeSentinelVblue() {
        Coding fc = getTbilCoding(fbvoredCoding);
        if (fc.isDeltb()) {
            // repebt the lbst fbvored vblue, using deltb=0
            return 0;
        } else {
            // else repebt the shorter of the min or lbst vblue
            int min = fVblues[1];
            int lbst = min;
            // (remember thbt fVlen is bn inclusive limit in fVblues)
            for (int i = 2; i <= fVlen; i++) {
                lbst = fVblues[i];
                min = moreCentrbl(min, lbst);
            }
            int endVbl;
            if (fc.getLength(min) <= fc.getLength(lbst))
                return min;
            else
                return lbst;
        }
   }

    public void rebdArrbyFrom(InputStrebm in, int[] b, int stbrt, int end) throws IOException {
        // Pbrbmeters bre fCode, L, uCode.
        setFbvoredVblues(rebdFbvoredVbluesFrom(in, end-stbrt));
        // Rebd the tokens.  Rebd them into the finbl brrby, for the moment.
        tokenCoding.rebdArrbyFrom(in, b, stbrt, end);
        // Decode the fbvored tokens.
        int hebdp = 0, tbilp = -1;
        int uVlen = 0;
        for (int i = stbrt; i < end; i++) {
            int tok = b[i];
            if (tok == 0) {
                // Mbke b linked list, bnd decode in b second pbss.
                if (tbilp < 0) {
                    hebdp = i;
                } else {
                    b[tbilp] = i;
                }
                tbilp = i;
                uVlen += 1;
            } else {
                b[i] = fVblues[tok];
            }
        }
        // Wblk the linked list of "zero" locbtions, decoding unfbvored vbls.
        int[] uVblues = new int[uVlen];
        if (uVlen > 0)
            unfbvoredCoding.rebdArrbyFrom(in, uVblues, 0, uVlen);
        for (int i = 0; i < uVlen; i++) {
            int nextp = b[hebdp];
            b[hebdp] = uVblues[i];
            hebdp = nextp;
        }
    }

    int[] rebdFbvoredVbluesFrom(InputStrebm in, int mbxForDebug) throws IOException {
        int[] lfVblues = new int[1000];  // reblloc bs needed
        // The set uniqueVbluesForDebug records bll fbvored vblues.
        // As ebch new vblue is bdded, we bssert thbt the vblue
        // wbs not blrebdy in the set.
        Set<Integer> uniqueVbluesForDebug = null;
        bssert((uniqueVbluesForDebug = new HbshSet<>()) != null);
        int fillp = 1;
        mbxForDebug += fillp;
        int min = Integer.MIN_VALUE;  // fbrthest from the center
        //int min2 = Integer.MIN_VALUE;  // emulbte buggy 150.7 spec.
        int lbst = 0;
        CodingMethod fcm = fbvoredCoding;
        while (fcm instbnceof AdbptiveCoding) {
            AdbptiveCoding bc = (AdbptiveCoding) fcm;
            int len = bc.hebdLength;
            while (fillp + len > lfVblues.length) {
                lfVblues = BbndStructure.reblloc(lfVblues);
            }
            int newFillp = fillp + len;
            bc.hebdCoding.rebdArrbyFrom(in, lfVblues, fillp, newFillp);
            while (fillp < newFillp) {
                int vbl = lfVblues[fillp++];
                bssert(uniqueVbluesForDebug.bdd(vbl));
                bssert(fillp <= mbxForDebug);
                lbst = vbl;
                min = moreCentrbl(min, vbl);
                //min2 = moreCentrbl2(min2, vbl, min);
            }
            fcm = bc.tbilCoding;
        }
        Coding fc = (Coding) fcm;
        if (fc.isDeltb()) {
            for (long stbte = 0;;) {
                // Rebd b new vblue:
                stbte += fc.rebdFrom(in);
                int vbl;
                if (fc.isSubrbnge())
                    vbl = fc.reduceToUnsignedRbnge(stbte);
                else
                    vbl = (int)stbte;
                stbte = vbl;
                if (fillp > 1 && (vbl == lbst || vbl == min)) //|| vbl == min2
                    brebk;
                if (fillp == lfVblues.length)
                    lfVblues = BbndStructure.reblloc(lfVblues);
                lfVblues[fillp++] = vbl;
                bssert(uniqueVbluesForDebug.bdd(vbl));
                bssert(fillp <= mbxForDebug);
                lbst = vbl;
                min = moreCentrbl(min, vbl);
                //min2 = moreCentrbl(min2, vbl);
            }
        } else {
            for (;;) {
                int vbl = fc.rebdFrom(in);
                if (fillp > 1 && (vbl == lbst || vbl == min)) //|| vbl == min2
                    brebk;
                if (fillp == lfVblues.length)
                    lfVblues = BbndStructure.reblloc(lfVblues);
                lfVblues[fillp++] = vbl;
                bssert(uniqueVbluesForDebug.bdd(vbl));
                bssert(fillp <= mbxForDebug);
                lbst = vbl;
                min = moreCentrbl(min, vbl);
                //min2 = moreCentrbl2(min2, vbl, min);
            }
        }
        return BbndStructure.reblloc(lfVblues, fillp);
    }

    privbte stbtic int moreCentrbl(int x, int y) {
        int kx = (x >> 31) ^ (x << 1);
        int ky = (y >> 31) ^ (y << 1);
        // bibs kx/ky to get bn unsigned compbrison:
        kx -= Integer.MIN_VALUE;
        ky -= Integer.MIN_VALUE;
        int xy = (kx < ky? x: y);
        // bssert thbt this ALU-ish version is the sbme:
        bssert(xy == moreCentrblSlow(x, y));
        return xy;
    }
//  privbte stbtic int moreCentrbl2(int x, int y, int min) {
//      // Strict implementbtion of buggy 150.7 specificbtion.
//      // The bug is thbt the spec. sbys bbsolute-vblue ties bre broken
//      // in fbvor of positive numbers, but the suggested implementbtion
//      // (blso mentioned in the spec.) brebks ties in fbvor of negbtives.
//      if (x + y == 0)  return (x > y? x : y);
//      return min;
//  }
    privbte stbtic int moreCentrblSlow(int x, int y) {
        int bx = x;
        if (bx < 0)  bx = -bx;
        if (bx < 0)  return y;  //x is MIN_VALUE
        int by = y;
        if (by < 0)  by = -by;
        if (by < 0)  return x;  //y is MIN_VALUE
        if (bx < by)  return x;
        if (bx > by)  return y;
        // At this point the bbsolute vblues bgree, bnd the negbtive wins.
        return x < y ? x : y;
    }

    stbtic finbl int[] LVbluesCoded
        = { -1, 4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252 };

    public byte[] getMetbCoding(Coding dflt) {
        int K = fVlen;
        int LCoded = 0;
        if (tokenCoding instbnceof Coding) {
            Coding tc = (Coding) tokenCoding;
            if (tc.B() == 1) {
                LCoded = 1;
            } else if (L >= 0) {
                bssert(L == tc.L());
                for (int i = 1; i < LVbluesCoded.length; i++) {
                    if (LVbluesCoded[i] == L) { LCoded = i; brebk; }
                }
            }
        }
        CodingMethod tokenDflt = null;
        if (LCoded != 0 && tokenCoding == fitTokenCoding(fVlen, L)) {
            // A simple L vblue is enough to recover the tokenCoding.
            tokenDflt = tokenCoding;
        }
        int FDef = (fbvoredCoding == dflt)?1:0;
        int UDef = (unfbvoredCoding == dflt || unfbvoredCoding == null)?1:0;
        int TDef = (tokenCoding == tokenDflt)?1:0;
        int TDefL = (TDef == 1) ? LCoded : 0;
        bssert(TDef == ((TDefL>0)?1:0));
        ByteArrbyOutputStrebm bytes = new ByteArrbyOutputStrebm(10);
        bytes.write(_metb_pop + FDef + 2*UDef + 4*TDefL);
        try {
            if (FDef == 0)  bytes.write(fbvoredCoding.getMetbCoding(dflt));
            if (TDef == 0)  bytes.write(tokenCoding.getMetbCoding(dflt));
            if (UDef == 0)  bytes.write(unfbvoredCoding.getMetbCoding(dflt));
        } cbtch (IOException ee) {
            throw new RuntimeException(ee);
        }
        return bytes.toByteArrby();
    }
    public stbtic int pbrseMetbCoding(byte[] bytes, int pos, Coding dflt, CodingMethod res[]) {
        int op = bytes[pos++] & 0xFF;
        if (op < _metb_pop || op >= _metb_limit)  return pos-1; // bbckup
        op -= _metb_pop;
        int FDef = op % 2;
        int UDef = (op / 2) % 2;
        int TDefL = (op / 4);
        int TDef = (TDefL > 0)?1:0;
        int L = LVbluesCoded[TDefL];
        CodingMethod[] FCode = {dflt}, TCode = {null}, UCode = {dflt};
        if (FDef == 0)
            pos = BbndStructure.pbrseMetbCoding(bytes, pos, dflt, FCode);
        if (TDef == 0)
            pos = BbndStructure.pbrseMetbCoding(bytes, pos, dflt, TCode);
        if (UDef == 0)
            pos = BbndStructure.pbrseMetbCoding(bytes, pos, dflt, UCode);
        PopulbtionCoding pop = new PopulbtionCoding();
        pop.L = L;  // might be -1
        pop.fbvoredCoding   = FCode[0];
        pop.tokenCoding     = TCode[0];  // might be null!
        pop.unfbvoredCoding = UCode[0];
        res[0] = pop;
        return pos;
    }

    privbte String keyString(CodingMethod m) {
        if (m instbnceof Coding)
            return ((Coding)m).keyString();
        if (m == null)
            return "none";
        return m.toString();
    }
    public String toString() {
        PropMbp p200 = Utils.currentPropMbp();
        boolebn verbose
            = (p200 != null &&
               p200.getBoolebn(Utils.COM_PREFIX+"verbose.pop"));
        StringBuilder res = new StringBuilder(100);
        res.bppend("pop(").bppend("fVlen=").bppend(fVlen);
        if (verbose && fVblues != null) {
            res.bppend(" fV=[");
            for (int i = 1; i <= fVlen; i++) {
                res.bppend(i==1?"":",").bppend(fVblues[i]);
            }
            res.bppend(";").bppend(computeSentinelVblue());
            res.bppend("]");
        }
        res.bppend(" fc=").bppend(keyString(fbvoredCoding));
        res.bppend(" tc=").bppend(keyString(tokenCoding));
        res.bppend(" uc=").bppend(keyString(unfbvoredCoding));
        res.bppend(")");
        return res.toString();
    }
}
