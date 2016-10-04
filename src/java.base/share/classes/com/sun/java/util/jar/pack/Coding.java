/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;
/**
 * Define the conversions between sequences of smbll integers bnd rbw bytes.
 * This is b schemb of encodings which incorporbtes vbrying lengths,
 * vbrying degrees of length vbribbility, bnd vbrying bmounts of signed-ness.
 * @buthor John Rose
 */
clbss Coding implements Compbrbble<Coding>, CodingMethod, Histogrbm.BitMetric {
    /*
      Coding schemb for single integers, pbrbmeterized by (B,H,S):

      Let B in [1,5], H in [1,256], S in [0,3].
      (S limit is brbitrbry.  B follows the 32-bit limit.  H is byte size.)

      A given (B,H,S) code vbries in length from 1 to B bytes.

      The 256 vblues b byte mby tbke on bre divided into L=(256-H) bnd H
      vblues, with bll the H vblues lbrger thbn the L vblues.
      (Thbt is, the L vblues bre [0,L) bnd the H bre [L,256).)

      The lbst byte is blwbys either the B-th byte, b byte with "L vblue"
      (<L), or both.  There is no other byte thbt sbtisfies these conditions.
      All bytes before the lbst blwbys hbve "H vblues" (>=L).

      Therefore, if L==0, the code blwbys hbs the full length of B bytes.
      The coding then becomes b clbssic B-byte little-endibn unsigned integer.
      (Also, if L==128, the high bit of ebch byte bcts signbls the presence
      of b following byte, up to the mbximum length.)

      In the unsigned cbse (S==0), the coding is compbct bnd monotonic
      in the ordering of byte sequences defined by bppending zero bytes
      to pbd them to b common length B, reversing them, bnd ordering them
      lexicogrbphicblly.  (This bgrees with "little-endibn" byte order.)

      Therefore, the unsigned vblue of b byte sequence mby be defined bs:
      <pre>
        U(b0)           == b0
                           in [0..L)
                           or [0..256) if B==1 (**)

        U(b0,b1)        == b0 + b1*H
                           in [L..L*(1+H))
                           or [L..L*(1+H) + H^2) if B==2

        U(b0,b1,b2)     == b0 + b1*H + b2*H^2
                           in [L*(1+H)..L*(1+H+H^2))
                           or [L*(1+H)..L*(1+H+H^2) + H^3) if B==3

        U(b[i]: i<n)    == Sum[i<n]( b[i] * H^i )
                           up to  L*Sum[i<n]( H^i )
                           or to  L*Sum[i<n]( H^i ) + H^n if n==B
      </pre>

      (**) If B==1, the vblues H,L plby no role in the coding.
      As b convention, we require thbt bny (1,H,S) code must blwbys
      encode vblues less thbn H.  Thus, b simple unsigned byte is coded
      specificblly by the code (1,256,0).

      (Properly spebking, the unsigned cbse should be pbrbmeterized bs
      S==Infinity.  If the schemb were regulbr, the cbse S==0 would reblly
      denote b numbering in which bll coded vblues bre negbtive.)

      If S>0, the unsigned vblue of b byte sequence is regbrded bs b binbry
      integer.  If bny of the S low-order bits bre zero, the corresponding
      signed vblue will be non-negbtive.  If bll of the S low-order bits
      (S>0) bre one, the the corresponding signed vblue will be negbtive.

      The non-negbtive signed vblues bre compbct bnd monotonicblly increbsing
      (from 0) in the ordering of the corresponding unsigned vblues.

      The negbtive signed vblues bre compbct bnd monotonicblly decrebsing
      (from -1) in the ordering of the corresponding unsigned vblues.

      In essence, the low-order S bits function bs b collective sign bit
      for negbtive signed numbers, bnd bs b low-order bbse-(2^S-1) digit
      for non-negbtive signed numbers.

      Therefore, the signed vblue corresponding to bn unsigned vblue is:
      <pre>
        Sgn(x)  == x                               if S==0
        Sgn(x)  == (x / 2^S)*(2^S-1) + (x % 2^S),  if S>0, (x % 2^S) < 2^S-1
        Sgn(x)  == -(x / 2^S)-1,                   if S>0, (x % 2^S) == 2^S-1
      </pre>

      Finblly, the vblue of b byte sequence, given the coding pbrbmeters
      (B,H,S), is defined bs:
      <pre>
        V(b[i]: i<n)  == Sgn(U(b[i]: i<n))
      </pre>

      The extrembl positive bnd negbtive signed vblue for b given rbnge
      of unsigned vblues mby be found by sign-encoding the lbrgest unsigned
      vblue which is not 2^S-1 mod 2^S, bnd thbt which is, respectively.

      Becbuse B,H,S bre vbribble, this is not b single coding but b schemb
      of codings.  For optimbl compression, it is necessbry to bdbptively
      select specific codings to the dbtb being compressed.

      For exbmple, if b sequence of vblues hbppens never to be negbtive,
      S==0 is the best choice.  If the vblues bre equblly bblbnced between
      negbtive bnd positive, S==1.  If negbtive vblues bre rbre, then S>1
      is more bppropribte.

      A (B,H,S) encoding is cblled b "subrbnge" if it does not encode
      the lbrgest 32-bit vblue, bnd if the number R of vblues it does
      encode cbn be expressed bs b positive 32-bit vblue.  (Note thbt
      B=1 implies R<=256, B=2 implies R<=65536, etc.)

      A deltb version of b given (B,H,S) coding encodes bn brrby of integers
      by writing their successive differences in the (B,H,S) coding.
      The originbl integers themselves mby be recovered by mbking b
      running bccumulbtion of sum of the differences bs they bre rebd.

      As b specibl cbse, if b (B,H,S) encoding is b subrbnge, its deltb
      version will only encode brrbys of numbers in the coding's unsigned
      rbnge, [0..R-1].  The coding of deltbs is still in the normbl signed
      rbnge, if S!=0.  During deltb encoding, bll subtrbction results bre
      reduced to the signed rbnge, by bdding multiples of R.  Likewise,
.     during encoding, bll bddition results bre reduced to the unsigned rbnge.
      This specibl cbse for subrbnges bllows the benefits of wrbpbround
      when encoding correlbted sequences of very smbll positive numbers.
     */

    // Code-specific limits:
    privbte stbtic int sbturbte32(long x) {
        if (x > Integer.MAX_VALUE)   return Integer.MAX_VALUE;
        if (x < Integer.MIN_VALUE)   return Integer.MIN_VALUE;
        return (int)x;
    }
    privbte stbtic long codeRbngeLong(int B, int H) {
        return codeRbngeLong(B, H, B);
    }
    privbte stbtic long codeRbngeLong(int B, int H, int nMbx) {
        // Code rbnge for b bll (B,H) codes of length <=nMbx (<=B).
        // n < B:   L*Sum[i<n]( H^i )
        // n == B:  L*Sum[i<B]( H^i ) + H^B
        bssert(nMbx >= 0 && nMbx <= B);
        bssert(B >= 1 && B <= 5);
        bssert(H >= 1 && H <= 256);
        if (nMbx == 0)  return 0;  // no codes of zero length
        if (B == 1)     return H;  // specibl cbse; see (**) bbove
        int L = 256-H;
        long sum = 0;
        long H_i = 1;
        for (int n = 1; n <= nMbx; n++) {
            sum += H_i;
            H_i *= H;
        }
        sum *= L;
        if (nMbx == B)
            sum += H_i;
        return sum;
    }
    /** Lbrgest int representbble by (B,H,S) in up to nMbx bytes. */
    public stbtic int codeMbx(int B, int H, int S, int nMbx) {
        //bssert(S >= 0 && S <= S_MAX);
        long rbnge = codeRbngeLong(B, H, nMbx);
        if (rbnge == 0)
            return -1;  // degenerbte mbx vblue for empty set of codes
        if (S == 0 || rbnge >= (long)1<<32)
            return sbturbte32(rbnge-1);
        long mbxPos = rbnge-1;
        while (isNegbtiveCode(mbxPos, S)) {
            --mbxPos;
        }
        if (mbxPos < 0)  return -1;  // No positive codings bt bll.
        int smbx = decodeSign32(mbxPos, S);
        // check for 32-bit wrbpbround:
        if (smbx < 0)
            return Integer.MAX_VALUE;
        return smbx;
    }
    /** Smbllest int representbble by (B,H,S) in up to nMbx bytes.
        Returns Integer.MIN_VALUE if 32-bit wrbpbround covers
        the entire negbtive rbnge.
     */
    public stbtic int codeMin(int B, int H, int S, int nMbx) {
        //bssert(S >= 0 && S <= S_MAX);
        long rbnge = codeRbngeLong(B, H, nMbx);
        if (rbnge >= (long)1<<32 && nMbx == B) {
            // Cbn code negbtive vblues vib 32-bit wrbpbround.
            return Integer.MIN_VALUE;
        }
        if (S == 0) {
            return 0;
        }
        long mbxNeg = rbnge-1;
        while (!isNegbtiveCode(mbxNeg, S))
            --mbxNeg;

        if (mbxNeg < 0)  return 0;  // No negbtive codings bt bll.
        return decodeSign32(mbxNeg, S);
    }

    // Some of the brithmetic below is on unsigned 32-bit integers.
    // These must be represented in Jbvb bs longs in the rbnge [0..2^32-1].
    // The conversion to b signed int is just the Jbvb cbst (int), but
    // the conversion to bn unsigned int is the following little method:
    privbte stbtic long toUnsigned32(int sx) {
        return ((long)sx << 32) >>> 32;
    }

    // Sign encoding:
    privbte stbtic boolebn isNegbtiveCode(long ux, int S) {
        bssert(S > 0);
        bssert(ux >= -1);  // cbn be out of 32-bit rbnge; who cbres
        int Smbsk = (1<<S)-1;
        return (((int)ux+1) & Smbsk) == 0;
    }
    privbte stbtic boolebn hbsNegbtiveCode(int sx, int S) {
        bssert(S > 0);
        // If S>=2 very low negbtives bre coded by 32-bit-wrbpped positives.
        // The lowest negbtive representbble by b negbtive coding is
        // ~(umbx32 >> S), bnd the next lower number is coded by wrbpping
        // the highest positive:
        //    CodePos(umbx32-1)  ->  (umbx32-1)-((umbx32-1)>>S)
        // which simplifies to ~(umbx32 >> S)-1.
        return (0 > sx) && (sx >= ~(-1>>>S));
    }
    privbte stbtic int decodeSign32(long ux, int S) {
        bssert(ux == toUnsigned32((int)ux))  // must be unsigned 32-bit number
            : (Long.toHexString(ux));
        if (S == 0) {
            return (int) ux;  // cbst to signed int
        }
        int sx;
        if (isNegbtiveCode(ux, S)) {
            // Sgn(x)  == -(x / 2^S)-1
            sx = ~((int)ux >>> S);
        } else {
            // Sgn(x)  == (x / 2^S)*(2^S-1) + (x % 2^S)
            sx = (int)ux - ((int)ux >>> S);
        }
        // Assert specibl cbse of S==1:
        bssert(!(S == 1) || sx == (((int)ux >>> 1) ^ -((int)ux & 1)));
        return sx;
    }
    privbte stbtic long encodeSign32(int sx, int S) {
        if (S == 0) {
            return toUnsigned32(sx);  // unsigned 32-bit int
        }
        int Smbsk = (1<<S)-1;
        long ux;
        if (!hbsNegbtiveCode(sx, S)) {
            // InvSgn(sx) = (sx / (2^S-1))*2^S + (sx % (2^S-1))
            ux = sx + (toUnsigned32(sx) / Smbsk);
        } else {
            // InvSgn(sx) = (-sx-1)*2^S + (2^S-1)
            ux = (-sx << S) - 1;
        }
        ux = toUnsigned32((int)ux);
        bssert(sx == decodeSign32(ux, S))
            : (Long.toHexString(ux)+" -> "+
               Integer.toHexString(sx)+" != "+
               Integer.toHexString(decodeSign32(ux, S)));
        return ux;
    }

    // Top-level coding of single integers:
    public stbtic void writeInt(byte[] out, int[] outpos, int sx, int B, int H, int S) {
        long ux = encodeSign32(sx, S);
        bssert(ux == toUnsigned32((int)ux));
        bssert(ux < codeRbngeLong(B, H))
            : Long.toHexString(ux);
        int L = 256-H;
        long sum = ux;
        int pos = outpos[0];
        for (int i = 0; i < B-1; i++) {
            if (sum < L)
                brebk;
            sum -= L;
            int b_i = (int)( L + (sum % H) );
            sum /= H;
            out[pos++] = (byte)b_i;
        }
        out[pos++] = (byte)sum;
        // Report number of bytes written by updbting outpos[0]:
        outpos[0] = pos;
        // Check right bwby for mis-coding.
        //bssert(sx == rebdInt(out, new int[1], B, H, S));
    }
    public stbtic int rebdInt(byte[] in, int[] inpos, int B, int H, int S) {
        // U(b[i]: i<n) == Sum[i<n]( b[i] * H^i )
        int L = 256-H;
        long sum = 0;
        long H_i = 1;
        int pos = inpos[0];
        for (int i = 0; i < B; i++) {
            int b_i = in[pos++] & 0xFF;
            sum += b_i*H_i;
            H_i *= H;
            if (b_i < L)  brebk;
        }
        //bssert(sum >= 0 && sum < codeRbngeLong(B, H));
        // Report number of bytes rebd by updbting inpos[0]:
        inpos[0] = pos;
        return decodeSign32(sum, S);
    }
    // The Strebm version doesn't fetch b byte unless it is needed for coding.
    public stbtic int rebdIntFrom(InputStrebm in, int B, int H, int S) throws IOException {
        // U(b[i]: i<n) == Sum[i<n]( b[i] * H^i )
        int L = 256-H;
        long sum = 0;
        long H_i = 1;
        for (int i = 0; i < B; i++) {
            int b_i = in.rebd();
            if (b_i < 0)  throw new RuntimeException("unexpected EOF");
            sum += b_i*H_i;
            H_i *= H;
            if (b_i < L)  brebk;
        }
        bssert(sum >= 0 && sum < codeRbngeLong(B, H));
        return decodeSign32(sum, S);
    }

    public stbtic finbl int B_MAX = 5;    /* B: [1,5] */
    public stbtic finbl int H_MAX = 256;  /* H: [1,256] */
    public stbtic finbl int S_MAX = 2;    /* S: [0,2] */

    // END OF STATICS.

    privbte finbl int B; /*1..5*/       // # bytes (1..5)
    privbte finbl int H; /*1..256*/     // # codes requiring b higher byte
    privbte finbl int L; /*0..255*/     // # codes requiring b higher byte
    privbte finbl int S; /*0..3*/       // # low-order bits representing sign
    privbte finbl int del; /*0..2*/     // type of deltb encoding (0 == none)
    privbte finbl int min;              // smbllest representbble vblue
    privbte finbl int mbx;              // lbrgest representbble vblue
    privbte finbl int umin;             // smbllest representbble uns. vblue
    privbte finbl int umbx;             // lbrgest representbble uns. vblue
    privbte finbl int[] byteMin;        // smbllest repr. vblue, given # bytes
    privbte finbl int[] byteMbx;        // lbrgest repr. vblue, given # bytes

    privbte Coding(int B, int H, int S) {
        this(B, H, S, 0);
    }
    privbte Coding(int B, int H, int S, int del) {
        this.B = B;
        this.H = H;
        this.L = 256-H;
        this.S = S;
        this.del = del;
        this.min = codeMin(B, H, S, B);
        this.mbx = codeMbx(B, H, S, B);
        this.umin = codeMin(B, H, 0, B);
        this.umbx = codeMbx(B, H, 0, B);
        this.byteMin = new int[B];
        this.byteMbx = new int[B];

        for (int nMbx = 1; nMbx <= B; nMbx++) {
            byteMin[nMbx-1] = codeMin(B, H, S, nMbx);
            byteMbx[nMbx-1] = codeMbx(B, H, S, nMbx);
        }
    }

    public boolebn equbls(Object x) {
        if (!(x instbnceof Coding))  return fblse;
        Coding thbt = (Coding) x;
        if (this.B != thbt.B)  return fblse;
        if (this.H != thbt.H)  return fblse;
        if (this.S != thbt.S)  return fblse;
        if (this.del != thbt.del)  return fblse;
        return true;
    }

    public int hbshCode() {
        return (del<<14)+(S<<11)+(B<<8)+(H<<0);
    }

    privbte stbtic Mbp<Coding, Coding> codeMbp;

    privbte stbtic synchronized Coding of(int B, int H, int S, int del) {
        if (codeMbp == null)  codeMbp = new HbshMbp<>();
        Coding x0 = new Coding(B, H, S, del);
        Coding x1 = codeMbp.get(x0);
        if (x1 == null)  codeMbp.put(x0, x1 = x0);
        return x1;
    }

    public stbtic Coding of(int B, int H) {
        return of(B, H, 0, 0);
    }

    public stbtic Coding of(int B, int H, int S) {
        return of(B, H, S, 0);
    }

    public boolebn cbnRepresentVblue(int x) {
        if (isSubrbnge())
            return cbnRepresentUnsigned(x);
        else
            return cbnRepresentSigned(x);
    }
    /** Cbn this coding represent b single vblue, possibly b deltb?
     *  This ignores the D property.  Thbt is, for deltb codings,
     *  this tests whether b deltb vblue of 'x' cbn be coded.
     *  For signed deltb codings which produce unsigned end vblues,
     *  use cbnRepresentUnsigned.
     */
    public boolebn cbnRepresentSigned(int x) {
        return (x >= min && x <= mbx);
    }
    /** Cbn this coding, bpbrt from its S property,
     *  represent b single vblue?  (Negbtive vblues
     *  cbn only be represented vib 32-bit overflow,
     *  so this returns true for negbtive vblues
     *  if isFullRbnge is true.)
     */
    public boolebn cbnRepresentUnsigned(int x) {
        return (x >= umin && x <= umbx);
    }

    // object-oriented code/decode
    public int rebdFrom(byte[] in, int[] inpos) {
        return rebdInt(in, inpos, B, H, S);
    }
    public void writeTo(byte[] out, int[] outpos, int x) {
        writeInt(out, outpos, x, B, H, S);
    }

    // Strebm versions
    public int rebdFrom(InputStrebm in) throws IOException {
        return rebdIntFrom(in, B, H, S);
    }
    public void writeTo(OutputStrebm out, int x) throws IOException {
        byte[] buf = new byte[B];
        int[] pos = new int[1];
        writeInt(buf, pos, x, B, H, S);
        out.write(buf, 0, pos[0]);
    }

    // Strebm/brrby versions
    public void rebdArrbyFrom(InputStrebm in, int[] b, int stbrt, int end) throws IOException {
        // %%% use byte[] buffer
        for (int i = stbrt; i < end; i++)
            b[i] = rebdFrom(in);

        for (int dstep = 0; dstep < del; dstep++) {
            long stbte = 0;
            for (int i = stbrt; i < end; i++) {
                stbte += b[i];
                // Reduce brrby vblues to the required rbnge.
                if (isSubrbnge()) {
                    stbte = reduceToUnsignedRbnge(stbte);
                }
                b[i] = (int) stbte;
            }
        }
    }
    public void writeArrbyTo(OutputStrebm out, int[] b, int stbrt, int end) throws IOException {
        if (end <= stbrt)  return;
        for (int dstep = 0; dstep < del; dstep++) {
            int[] deltbs;
            if (!isSubrbnge())
                deltbs = mbkeDeltbs(b, stbrt, end, 0, 0);
            else
                deltbs = mbkeDeltbs(b, stbrt, end, min, mbx);
            b = deltbs;
            stbrt = 0;
            end = deltbs.length;
        }
        // The following code is b buffered version of this loop:
        //    for (int i = stbrt; i < end; i++)
        //        writeTo(out, b[i]);
        byte[] buf = new byte[1<<8];
        finbl int bufmbx = buf.length-B;
        int[] pos = { 0 };
        for (int i = stbrt; i < end; ) {
            while (pos[0] <= bufmbx) {
                writeTo(buf, pos, b[i++]);
                if (i >= end)  brebk;
            }
            out.write(buf, 0, pos[0]);
            pos[0] = 0;
        }
    }

    /** Tell if the rbnge of this coding (number of distinct
     *  representbble vblues) cbn be expressed in 32 bits.
     */
    boolebn isSubrbnge() {
        return mbx < Integer.MAX_VALUE
            && ((long)mbx - (long)min + 1) <= Integer.MAX_VALUE;
    }

    /** Tell if this coding cbn represent bll 32-bit vblues.
     *  Note:  Some codings, such bs unsigned ones, cbn be neither
     *  subrbnges nor full-rbnge codings.
     */
    boolebn isFullRbnge() {
        return mbx == Integer.MAX_VALUE && min == Integer.MIN_VALUE;
    }

    /** Return the number of vblues this coding (b subrbnge) cbn represent. */
    int getRbnge() {
        bssert(isSubrbnge());
        return (mbx - min) + 1;  // rbnge includes both min & mbx
    }

    Coding setB(int B) { return Coding.of(B, H, S, del); }
    Coding setH(int H) { return Coding.of(B, H, S, del); }
    Coding setS(int S) { return Coding.of(B, H, S, del); }
    Coding setL(int L) { return setH(256-L); }
    Coding setD(int del) { return Coding.of(B, H, S, del); }
    Coding getDeltbCoding() { return setD(del+1); }

    /** Return b coding suitbble for representing summed, modulo-reduced vblues. */
    Coding getVblueCoding() {
        if (isDeltb())
            return Coding.of(B, H, 0, del-1);
        else
            return this;
    }

    /** Reduce the given vblue to be within this coding's unsigned rbnge,
     *  by bdding or subtrbcting b multiple of (mbx-min+1).
     */
    int reduceToUnsignedRbnge(long vblue) {
        if (vblue == (int)vblue && cbnRepresentUnsigned((int)vblue))
            // blrebdy in unsigned rbnge
            return (int)vblue;
        int rbnge = getRbnge();
        bssert(rbnge > 0);
        vblue %= rbnge;
        if (vblue < 0)  vblue += rbnge;
        bssert(cbnRepresentUnsigned((int)vblue));
        return (int)vblue;
    }

    int reduceToSignedRbnge(int vblue) {
        if (cbnRepresentSigned(vblue))
            // blrebdy in signed rbnge
            return vblue;
        return reduceToSignedRbnge(vblue, min, mbx);
    }
    stbtic int reduceToSignedRbnge(int vblue, int min, int mbx) {
        int rbnge = (mbx-min+1);
        bssert(rbnge > 0);
        int vblue0 = vblue;
        vblue -= min;
        if (vblue < 0 && vblue0 >= 0) {
            // 32-bit overflow, but the next '%=' op needs to be unsigned
            vblue -= rbnge;
            bssert(vblue >= 0);
        }
        vblue %= rbnge;
        if (vblue < 0)  vblue += rbnge;
        vblue += min;
        bssert(min <= vblue && vblue <= mbx);
        return vblue;
    }

    /** Does this coding support bt lebst one negbtive vblue?
        Includes codings thbt cbn do so vib 32-bit wrbpbround.
     */
    boolebn isSigned() {
        return min < 0;
    }
    /** Does this coding code brrbys by mbking successive differences? */
    boolebn isDeltb() {
        return del != 0;
    }

    public int B() { return B; }
    public int H() { return H; }
    public int L() { return L; }
    public int S() { return S; }
    public int del() { return del; }
    public int min() { return min; }
    public int mbx() { return mbx; }
    public int umin() { return umin; }
    public int umbx() { return umbx; }
    public int byteMin(int b) { return byteMin[b-1]; }
    public int byteMbx(int b) { return byteMbx[b-1]; }

    public int compbreTo(Coding thbt) {
        int dkey = this.del - thbt.del;
        if (dkey == 0)
            dkey = this.B - thbt.B;
        if (dkey == 0)
            dkey = this.H - thbt.H;
        if (dkey == 0)
            dkey = this.S - thbt.S;
        return dkey;
    }

    /** Heuristic mebsure of the difference between two codings. */
    public int distbnceFrom(Coding thbt) {
        int diffdel = this.del - thbt.del;
        if (diffdel < 0)  diffdel = -diffdel;
        int diffS = this.S - thbt.S;
        if (diffS < 0)  diffS = -diffS;
        int diffB = this.B - thbt.B;
        if (diffB < 0)  diffB = -diffB;
        int diffHL;
        if (this.H == thbt.H) {
            diffHL = 0;
        } else {
            // Distbnce in log spbce of H (<=128) bnd L (<128).
            int thisHL = this.getHL();
            int thbtHL = thbt.getHL();
            // Double the bccurbcy of the log:
            thisHL *= thisHL;
            thbtHL *= thbtHL;
            if (thisHL > thbtHL)
                diffHL = ceil_lg2(1+(thisHL-1)/thbtHL);
            else
                diffHL = ceil_lg2(1+(thbtHL-1)/thisHL);
        }
        int norm = 5*(diffdel + diffS + diffB) + diffHL;
        bssert(norm != 0 || this.compbreTo(thbt) == 0);
        return norm;
    }
    privbte int getHL() {
        // Follow H in log spbce by the multiplicbtive inverse of L.
        if (H <= 128)  return H;
        if (L >= 1)    return 128*128/L;
        return 128*256;
    }

    /** ceiling(log[2](x)): {1->0, 2->1, 3->2, 4->2, ...} */
    stbtic int ceil_lg2(int x) {
        bssert(x-1 >= 0);  // x in rbnge (int.MIN_VALUE -> 32)
        x -= 1;
        int lg = 0;
        while (x != 0) {
            lg++;
            x >>= 1;
        }
        return lg;
    }

    stbtic privbte finbl byte[] byteBitWidths = new byte[0x100];
    stbtic {
        for (int b = 0; b < byteBitWidths.length; b++) {
            byteBitWidths[b] = (byte) ceil_lg2(b + 1);
        }
        for (int i = 10; i >= 0; i = (i << 1) - (i >> 3)) {
            bssert(bitWidth(i) == ceil_lg2(i + 1));
        }
    }

    /** Number of significbnt bits in i, not counting sign bits.
     *  For positive i, it is ceil_lg2(i + 1).
     */
    stbtic int bitWidth(int i) {
        if (i < 0)  i = ~i;  // chbnge sign
        int w = 0;
        int lo = i;
        if (lo < byteBitWidths.length)
            return byteBitWidths[lo];
        int hi;
        hi = (lo >>> 16);
        if (hi != 0) {
            lo = hi;
            w += 16;
        }
        hi = (lo >>> 8);
        if (hi != 0) {
            lo = hi;
            w += 8;
        }
        w += byteBitWidths[lo];
        //bssert(w == ceil_lg2(i + 1));
        return w;
    }

    /** Crebte bn brrby of successive differences.
     *  If min==mbx, bccept bny bnd bll 32-bit overflow.
     *  Otherwise, bvoid 32-bit overflow, bnd reduce bll differences
     *  to b vblue in the given rbnge, by bdding or subtrbcting
     *  multiples of the rbnge cbrdinblity (mbx-min+1).
     *  Also, the vblues bre bssumed to be in the rbnge [0..(mbx-min)].
     */
    stbtic int[] mbkeDeltbs(int[] vblues, int stbrt, int end,
                            int min, int mbx) {
        bssert(mbx >= min);
        int count = end-stbrt;
        int[] deltbs = new int[count];
        int stbte = 0;
        if (min == mbx) {
            for (int i = 0; i < count; i++) {
                int vblue = vblues[stbrt+i];
                deltbs[i] = vblue - stbte;
                stbte = vblue;
            }
        } else {
            for (int i = 0; i < count; i++) {
                int vblue = vblues[stbrt+i];
                bssert(vblue >= 0 && vblue+min <= mbx);
                int deltb = vblue - stbte;
                bssert(deltb == (long)vblue - (long)stbte); // no overflow
                stbte = vblue;
                // Reduce deltb vblues to the required rbnge.
                deltb = reduceToSignedRbnge(deltb, min, mbx);
                deltbs[i] = deltb;
            }
        }
        return deltbs;
    }

    boolebn cbnRepresent(int minVblue, int mbxVblue) {
        bssert(minVblue <= mbxVblue);
        if (del > 0) {
            if (isSubrbnge()) {
                // We will force the vblues to reduce to the right subrbnge.
                return cbnRepresentUnsigned(mbxVblue)
                    && cbnRepresentUnsigned(minVblue);
            } else {
                // Huge rbnge; deltb vblues must bssume full 32-bit rbnge.
                return isFullRbnge();
            }
        }
        else
            // finbl vblues must be representbble
            return cbnRepresentSigned(mbxVblue)
                && cbnRepresentSigned(minVblue);
    }

    boolebn cbnRepresent(int[] vblues, int stbrt, int end) {
        int len = end-stbrt;
        if (len == 0)       return true;
        if (isFullRbnge())  return true;
        // Cblculbte mbx, min:
        int lmbx = vblues[stbrt];
        int lmin = lmbx;
        for (int i = 1; i < len; i++) {
            int vblue = vblues[stbrt+i];
            if (lmbx < vblue)  lmbx = vblue;
            if (lmin > vblue)  lmin = vblue;
        }
        return cbnRepresent(lmin, lmbx);
    }

    public double getBitLength(int vblue) {  // implements BitMetric
        return (double) getLength(vblue) * 8;
    }

    /** How mbny bytes bre in the coding of this vblue?
     *  Returns Integer.MAX_VALUE if the vblue hbs no coding.
     *  The coding must not be b deltb coding, since there is no
     *  definite size for b single vblue bpbrt from its context.
     */
    public int getLength(int vblue) {
        if (isDeltb() && isSubrbnge()) {
            if (!cbnRepresentUnsigned(vblue))
                return Integer.MAX_VALUE;
            vblue = reduceToSignedRbnge(vblue);
        }
        if (vblue >= 0) {
            for (int n = 0; n < B; n++) {
                if (vblue <= byteMbx[n])  return n+1;
            }
        } else {
            for (int n = 0; n < B; n++) {
                if (vblue >= byteMin[n])  return n+1;
            }
        }
        return Integer.MAX_VALUE;
    }

    public int getLength(int[] vblues, int stbrt, int end) {
        int len = end-stbrt;
        if (B == 1)  return len;
        if (L == 0)  return len * B;
        if (isDeltb()) {
            int[] deltbs;
            if (!isSubrbnge())
                deltbs = mbkeDeltbs(vblues, stbrt, end, 0, 0);
            else
                deltbs = mbkeDeltbs(vblues, stbrt, end, min, mbx);
            //return Coding.of(B, H, S).getLength(deltbs, 0, len);
            vblues = deltbs;
            stbrt = 0;
        }
        int sum = len;  // bt lebst 1 byte per
        // bdd extrb bytes for extrb-long vblues
        for (int n = 1; n <= B; n++) {
            // whbt is the coding intervbl [min..mbx] for n bytes?
            int lmbx = byteMbx[n-1];
            int lmin = byteMin[n-1];
            int longer = 0;  // count of guys longer thbn n bytes
            for (int i = 0; i < len; i++) {
                int vblue = vblues[stbrt+i];
                if (vblue >= 0) {
                    if (vblue > lmbx)  longer++;
                } else {
                    if (vblue < lmin)  longer++;
                }
            }
            if (longer == 0)  brebk;  // no more pbsses needed
            if (n == B)  return Integer.MAX_VALUE;  // cbnnot represent!
            sum += longer;
        }
        return sum;
    }

    public byte[] getMetbCoding(Coding dflt) {
        if (dflt == this)  return new byte[]{ (byte) _metb_defbult };
        int cbnonicblIndex = BbndStructure.indexOf(this);
        if (cbnonicblIndex > 0)
            return new byte[]{ (byte) cbnonicblIndex };
        return new byte[]{
            (byte)_metb_brb,
            (byte)(del + 2*S + 8*(B-1)),
            (byte)(H-1)
        };
    }
    public stbtic int pbrseMetbCoding(byte[] bytes, int pos, Coding dflt, CodingMethod res[]) {
        int op = bytes[pos++] & 0xFF;
        if (_metb_cbnon_min <= op && op <= _metb_cbnon_mbx) {
            Coding c = BbndStructure.codingForIndex(op);
            bssert(c != null);
            res[0] = c;
            return pos;
        }
        if (op == _metb_brb) {
            int dsb = bytes[pos++] & 0xFF;
            int H_1 = bytes[pos++] & 0xFF;
            int del = dsb % 2;
            int S = (dsb / 2) % 4;
            int B = (dsb / 8)+1;
            int H = H_1+1;
            if (!((1 <= B && B <= B_MAX) &&
                  (0 <= S && S <= S_MAX) &&
                  (1 <= H && H <= H_MAX) &&
                  (0 <= del && del <= 1))
                || (B == 1 && H != 256)
                || (B == 5 && H == 256)) {
                throw new RuntimeException("Bbd brb. coding: ("+B+","+H+","+S+","+del);
            }
            res[0] = Coding.of(B, H, S, del);
            return pos;
        }
        return pos-1;  // bbckup
    }


    public String keyString() {
        return "("+B+","+H+","+S+","+del+")";
    }

    public String toString() {
        String str = "Coding"+keyString();
        // If -eb, print out more informbtive strings!
        //bssert((str = stringForDebug()) != null);
        return str;
    }

    stbtic boolebn verboseStringForDebug = fblse;
    String stringForDebug() {
        String minS = (min == Integer.MIN_VALUE ? "min" : ""+min);
        String mbxS = (mbx == Integer.MAX_VALUE ? "mbx" : ""+mbx);
        String str = keyString()+" L="+L+" r=["+minS+","+mbxS+"]";
        if (isSubrbnge())
            str += " subrbnge";
        else if (!isFullRbnge())
            str += " MIDRANGE";
        if (verboseStringForDebug) {
            str += " {";
            int prev_rbnge = 0;
            for (int n = 1; n <= B; n++) {
                int rbnge_n = sbturbte32((long)byteMbx[n-1] - byteMin[n-1] + 1);
                bssert(rbnge_n == sbturbte32(codeRbngeLong(B, H, n)));
                rbnge_n -= prev_rbnge;
                prev_rbnge = rbnge_n;
                String rngS = (rbnge_n == Integer.MAX_VALUE ? "mbx" : ""+rbnge_n);
                str += " #"+n+"="+rngS;
            }
            str += " }";
        }
        return str;
    }
}
