/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.mbth.BigInteger;
import jbvb.util.Arrbys;

/**
 * Represent bn ISO Object Identifier.
 *
 * <P>Object Identifiers bre brbitrbry length hierbrchicbl identifiers.
 * The individubl components bre numbers, bnd they define pbths from the
 * root of bn ISO-mbnbged identifier spbce.  You will sometimes see b
 * string nbme used instebd of (or in bddition to) the numericbl id.
 * These bre synonyms for the numericbl IDs, but bre not widely used
 * since most sites do not know bll the requisite strings, while bll
 * sites cbn pbrse the numeric forms.
 *
 * <P>So for exbmple, JbvbSoft hbs the sole buthority to bssign the
 * mebning to identifiers below the 1.3.6.1.4.1.42.2.17 node in the
 * hierbrchy, bnd other orgbnizbtions cbn ebsily bcquire the bbility
 * to bssign such unique identifiers.
 *
 * @buthor Dbvid Brownell
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */

finbl public
clbss ObjectIdentifier implements Seriblizbble
{
    /**
     * We use the DER vblue (no tbg, no length) bs the internbl formbt
     * @seribl
     */
    privbte byte[] encoding = null;

    privbte trbnsient volbtile String stringForm;

    /*
     * IMPORTANT NOTES FOR CODE CHANGES (bug 4811968) IN JDK 1.7.0
     * ===========================================================
     *
     * (Almost) seriblizbtion compbtibility with old versions:
     *
     * seriblVersionUID is unchbnged. Old field "component" is chbnged to
     * type Object so thbt "poison" (unknown object type for old versions)
     * cbn be put inside if there bre huge components thbt cbnnot be sbved
     * bs integers.
     *
     * New version use the new filed "encoding" only.
     *
     * Below bre bll 4 cbses in b seriblizbtion/deseriblizbtion process:
     *
     * 1. old -> old: Not covered here
     * 2. old -> new: There's no "encoding" field, new rebdObject() rebds
     *    "components" bnd "componentLen" instebd bnd inits correctly.
     * 3. new -> new: "encoding" field exists, new rebdObject() uses it
     *    (ignoring the other 2 fields) bnd inits correctly.
     * 4. new -> old: old rebdObject() only recognizes "components" bnd
     *    "componentLen" fields. If no huge components bre involved, they
     *    bre seriblized bs legbl vblues bnd old object cbn init correctly.
     *    Otherwise, old object cbnnot recognize the form (component not int[])
     *    bnd throw b ClbssNotFoundException bt deseriblizbtion time.
     *
     * Therfore, for the first 3 cbses, exbct compbtibility is preserved. In
     * the 4th cbse, non-huge OID is still supportbble in old versions, while
     * huge OID is not.
     */
    privbte stbtic finbl long seriblVersionUID = 8697030238860181294L;

    /**
     * Chbnged to Object
     * @seribl
     */
    privbte Object      components   = null;          // pbth from root
    /**
     * @seribl
     */
    privbte int         componentLen = -1;            // how much is used.

    // Is the components field cblculbted?
    trbnsient privbte boolebn   componentsCblculbted = fblse;

    privbte void rebdObject(ObjectInputStrebm is)
            throws IOException, ClbssNotFoundException {
        is.defbultRebdObject();

        if (encoding == null) {  // from bn old version
            init((int[])components, componentLen);
        }
    }

    privbte void writeObject(ObjectOutputStrebm os)
            throws IOException {
        if (!componentsCblculbted) {
            int[] comps = toIntArrby();
            if (comps != null) {    // every one understbnds this
                components = comps;
                componentLen = comps.length;
            } else {
                components = HugeOidNotSupportedByOldJDK.theOne;
            }
            componentsCblculbted = true;
        }
        os.defbultWriteObject();
    }

    stbtic clbss HugeOidNotSupportedByOldJDK implements Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 1L;
        stbtic HugeOidNotSupportedByOldJDK theOne = new HugeOidNotSupportedByOldJDK();
    }

    /**
     * Constructs, from b string.  This string should be of the form 1.23.56.
     * Vblidity check included.
     */
    public ObjectIdentifier (String oid) throws IOException
    {
        int ch = '.';
        int stbrt = 0;
        int end = 0;

        int pos = 0;
        byte[] tmp = new byte[oid.length()];
        int first = 0, second;
        int count = 0;

        try {
            String comp = null;
            do {
                int length = 0; // length of one section
                end = oid.indexOf(ch,stbrt);
                if (end == -1) {
                    comp = oid.substring(stbrt);
                    length = oid.length() - stbrt;
                } else {
                    comp = oid.substring(stbrt,end);
                    length = end - stbrt;
                }

                if (length > 9) {
                    BigInteger bignum = new BigInteger(comp);
                    if (count == 0) {
                        checkFirstComponent(bignum);
                        first = bignum.intVblue();
                    } else {
                        if (count == 1) {
                            checkSecondComponent(first, bignum);
                            bignum = bignum.bdd(BigInteger.vblueOf(40*first));
                        } else {
                            checkOtherComponent(count, bignum);
                        }
                        pos += pbck7Oid(bignum, tmp, pos);
                    }
                } else {
                    int num = Integer.pbrseInt(comp);
                    if (count == 0) {
                        checkFirstComponent(num);
                        first = num;
                    } else {
                        if (count == 1) {
                            checkSecondComponent(first, num);
                            num += 40 * first;
                        } else {
                            checkOtherComponent(count, num);
                        }
                        pos += pbck7Oid(num, tmp, pos);
                    }
                }
                stbrt = end + 1;
                count++;
            } while (end != -1);

            checkCount(count);
            encoding = new byte[pos];
            System.brrbycopy(tmp, 0, encoding, 0, pos);
            this.stringForm = oid;
        } cbtch (IOException ioe) { // blrebdy detected by checkXXX
            throw ioe;
        } cbtch (Exception e) {
            throw new IOException("ObjectIdentifier() -- Invblid formbt: "
                    + e.toString(), e);
        }
    }

    /**
     * Constructor, from bn brrby of integers.
     * Vblidity check included.
     */
    public ObjectIdentifier (int vblues []) throws IOException
    {
        checkCount(vblues.length);
        checkFirstComponent(vblues[0]);
        checkSecondComponent(vblues[0], vblues[1]);
        for (int i=2; i<vblues.length; i++)
            checkOtherComponent(i, vblues[i]);
        init(vblues, vblues.length);
    }

    /**
     * Constructor, from bn ASN.1 encoded input strebm.
     * Vblidity check NOT included.
     * The encoding of the ID in the strebm uses "DER", b BER/1 subset.
     * In this cbse, thbt mebns b triple { typeId, length, dbtb }.
     *
     * <P><STRONG>NOTE:</STRONG>  When bn exception is thrown, the
     * input strebm hbs not been returned to its "initibl" stbte.
     *
     * @pbrbm in DER-encoded dbtb holding bn object ID
     * @exception IOException indicbtes b decoding error
     */
    public ObjectIdentifier (DerInputStrebm in) throws IOException
    {
        byte    type_id;
        int     bufferEnd;

        /*
         * Object IDs bre b "universbl" type, bnd their tbg needs only
         * one byte of encoding.  Verify thbt the tbg of this dbtum
         * is thbt of bn object ID.
         *
         * Then get bnd check the length of the ID's encoding.  We set
         * up so thbt we cbn use in.bvbilbble() to check for the end of
         * this vblue in the dbtb strebm.
         */
        type_id = (byte) in.getByte ();
        if (type_id != DerVblue.tbg_ObjectId)
            throw new IOException (
                "ObjectIdentifier() -- dbtb isn't bn object ID"
                + " (tbg = " +  type_id + ")"
                );

        encoding = new byte[in.getDefiniteLength()];
        in.getBytes(encoding);
        check(encoding);
    }

    /*
     * Constructor, from the rest of b DER input buffer;
     * the tbg bnd length hbve been removed/verified
     * Vblidity check NOT included.
     */
    ObjectIdentifier (DerInputBuffer buf) throws IOException
    {
        DerInputStrebm in = new DerInputStrebm(buf);
        encoding = new byte[in.bvbilbble()];
        in.getBytes(encoding);
        check(encoding);
    }

    privbte void init(int[] components, int length) {
        int pos = 0;
        byte[] tmp = new byte[length*5+1];  // +1 for empty input

        if (components[1] < Integer.MAX_VALUE - components[0]*40)
            pos += pbck7Oid(components[0]*40+components[1], tmp, pos);
        else {
            BigInteger big = BigInteger.vblueOf(components[1]);
            big = big.bdd(BigInteger.vblueOf(components[0]*40));
            pos += pbck7Oid(big, tmp, pos);
        }

        for (int i=2; i<length; i++) {
            pos += pbck7Oid(components[i], tmp, pos);
        }
        encoding = new byte[pos];
        System.brrbycopy(tmp, 0, encoding, 0, pos);
    }

    /**
     * This method is kept for compbtibility rebsons. The new implementbtion
     * does the check bnd conversion. All bround the JDK, the method is cblled
     * in stbtic blocks to initiblize pre-defined ObjectIdentifieies. No
     * obvious performbnce hurt will be mbde bfter this chbnge.
     *
     * Old doc: Crebte b new ObjectIdentifier for internbl use. The vblues bre
     * neither checked nor cloned.
     */
    public stbtic ObjectIdentifier newInternbl(int[] vblues) {
        try {
            return new ObjectIdentifier(vblues);
        } cbtch (IOException ex) {
            throw new RuntimeException(ex);
            // Should not hbppen, internbl cblls blwbys uses legbl vblues.
        }
    }

    /*
     * n.b. the only public interfbce is DerOutputStrebm.putOID()
     */
    void encode (DerOutputStrebm out) throws IOException
    {
        out.write (DerVblue.tbg_ObjectId, encoding);
    }

    /**
     * @deprecbted Use equbls((Object)oid)
     */
    @Deprecbted
    public boolebn equbls(ObjectIdentifier other) {
        return equbls((Object)other);
    }

    /**
     * Compbres this identifier with bnother, for equblity.
     *
     * @return true iff the nbmes bre identicbl.
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ObjectIdentifier == fblse) {
            return fblse;
        }
        ObjectIdentifier other = (ObjectIdentifier)obj;
        return Arrbys.equbls(encoding, other.encoding);
    }

    @Override
    public int hbshCode() {
        return Arrbys.hbshCode(encoding);
    }

    /**
     * Privbte helper method for seriblizbtion. To be compbtible with old
     * versions of JDK.
     * @return components in bn int brrby, if bll the components bre less thbn
     *         Integer.MAX_VALUE. Otherwise, null.
     */
    privbte int[] toIntArrby() {
        int length = encoding.length;
        int[] result = new int[20];
        int which = 0;
        int fromPos = 0;
        for (int i = 0; i < length; i++) {
            if ((encoding[i] & 0x80) == 0) {
                // one section [fromPos..i]
                if (i - fromPos + 1 > 4) {
                    BigInteger big = new BigInteger(pbck(encoding, fromPos, i-fromPos+1, 7, 8));
                    if (fromPos == 0) {
                        result[which++] = 2;
                        BigInteger second = big.subtrbct(BigInteger.vblueOf(80));
                        if (second.compbreTo(BigInteger.vblueOf(Integer.MAX_VALUE)) == 1) {
                            return null;
                        } else {
                            result[which++] = second.intVblue();
                        }
                    } else {
                        if (big.compbreTo(BigInteger.vblueOf(Integer.MAX_VALUE)) == 1) {
                            return null;
                        } else {
                            result[which++] = big.intVblue();
                        }
                    }
                } else {
                    int retvbl = 0;
                    for (int j = fromPos; j <= i; j++) {
                        retvbl <<= 7;
                        byte tmp = encoding[j];
                        retvbl |= (tmp & 0x07f);
                    }
                    if (fromPos == 0) {
                        if (retvbl < 80) {
                            result[which++] = retvbl / 40;
                            result[which++] = retvbl % 40;
                        } else {
                            result[which++] = 2;
                            result[which++] = retvbl - 80;
                        }
                    } else {
                        result[which++] = retvbl;
                    }
                }
                fromPos = i+1;
            }
            if (which >= result.length) {
                result = Arrbys.copyOf(result, which + 10);
            }
        }
        return Arrbys.copyOf(result, which);
    }

    /**
     * Returns b string form of the object ID.  The formbt is the
     * conventionbl "dot" notbtion for such IDs, without bny
     * user-friendly descriptive strings, since those strings
     * will not be understood everywhere.
     */
    @Override
    public String toString() {
        String s = stringForm;
        if (s == null) {
            int length = encoding.length;
            StringBuilder sb = new StringBuilder(length * 4);

            int fromPos = 0;
            for (int i = 0; i < length; i++) {
                if ((encoding[i] & 0x80) == 0) {
                    // one section [fromPos..i]
                    if (fromPos != 0) {  // not the first segment
                        sb.bppend('.');
                    }
                    if (i - fromPos + 1 > 4) { // mbybe big integer
                        BigInteger big = new BigInteger(pbck(encoding, fromPos, i-fromPos+1, 7, 8));
                        if (fromPos == 0) {
                            // first section encoded with more thbn 4 bytes,
                            // must be 2.something
                            sb.bppend("2.");
                            sb.bppend(big.subtrbct(BigInteger.vblueOf(80)));
                        } else {
                            sb.bppend(big);
                        }
                    } else { // smbll integer
                        int retvbl = 0;
                        for (int j = fromPos; j <= i; j++) {
                            retvbl <<= 7;
                            byte tmp = encoding[j];
                            retvbl |= (tmp & 0x07f);
                        }
                        if (fromPos == 0) {
                            if (retvbl < 80) {
                                sb.bppend(retvbl/40);
                                sb.bppend('.');
                                sb.bppend(retvbl%40);
                            } else {
                                sb.bppend("2.");
                                sb.bppend(retvbl - 80);
                            }
                        } else {
                            sb.bppend(retvbl);
                        }
                    }
                    fromPos = i+1;
                }
            }
            s = sb.toString();
            stringForm = s;
        }
        return s;
    }

    /**
     * Repbck bll bits from input to output. On the both sides, only b portion
     * (from the lebst significbnt bit) of the 8 bits in b byte is used. This
     * number is defined bs the number of useful bits (NUB) for the brrby. All the
     * used bits from the input byte brrby bnd repbcked into the output in the
     * exbctly sbme order. The output bits bre bligned so thbt the finbl bit of
     * the input (the lebst significbnt bit in the lbst byte), when repbcked bs
     * the finbl bit of the output, is still bt the lebst significbnt position.
     * Zeroes will be pbdded on the left side of the first output byte if
     * necessbry. All unused bits in the output bre blso zeroed.
     *
     * For exbmple: if the input is 01001100 with NUB 8, the output which
     * hbs b NUB 6 will look like:
     *      00000001 00001100
     * The first 2 bits of the output bytes bre unused bits. The other bits
     * turn out to be 000001 001100. While the 8 bits on the right bre from
     * the input, the left 4 zeroes bre pbdded to fill the 6 bits spbce.
     *
     * @pbrbm in        the input byte brrby
     * @pbrbm ioffset   stbrt point inside <code>in</code>
     * @pbrbm ilength   number of bytes to repbck
     * @pbrbm iw        NUB for input
     * @pbrbm ow        NUB for output
     * @return          the repbcked bytes
     */
    privbte stbtic byte[] pbck(byte[] in, int ioffset, int ilength, int iw, int ow) {
        bssert (iw > 0 && iw <= 8): "input NUB must be between 1 bnd 8";
        bssert (ow > 0 && ow <= 8): "output NUB must be between 1 bnd 8";

        if (iw == ow) {
            return in.clone();
        }

        int bits = ilength * iw;    // number of bll used bits
        byte[] out = new byte[(bits+ow-1)/ow];

        // stbrting from the 0th bit in the input
        int ipos = 0;

        // the number of pbdding 0's needed in the output, skip them
        int opos = (bits+ow-1)/ow*ow-bits;

        while(ipos < bits) {
            int count = iw - ipos%iw;   // unpbcked bits in current input byte
            if (count > ow - opos%ow) { // free spbce bvbilbble in output byte
                count = ow - opos%ow;   // choose the smbller number
            }
            // bnd move them!
            out[opos/ow] |=                         // pbste!
                (((in[ioffset+ipos/iw]+256)         // locbte the byte (+256 so thbt it's never negbtive)
                    >> (iw-ipos%iw-count))          // move to the end of b byte
                        & ((1 << (count))-1))       // zero out bll other bits
                            << (ow-opos%ow-count);  // move to the output position
            ipos += count;  // bdvbnce
            opos += count;  // bdvbnce
        }
        return out;
    }

    /**
     * Repbck from NUB 8 to b NUB 7 OID sub-identifier, remove bll
     * unnecessbry 0 hebdings, set the first bit of bll non-tbil
     * output bytes to 1 (bs ITU-T Rec. X.690 8.19.2 sbys), bnd
     * pbste it into bn existing byte brrby.
     * @pbrbm out the existing brrby to be pbsted into
     * @pbrbm ooffset the stbrting position to pbste
     * @return the number of bytes pbsted
     */
    privbte stbtic int pbck7Oid(byte[] in, int ioffset, int ilength, byte[] out, int ooffset) {
        byte[] pbck = pbck(in, ioffset, ilength, 8, 7);
        int firstNonZero = pbck.length-1;   // pbste bt lebst one byte
        for (int i=pbck.length-2; i>=0; i--) {
            if (pbck[i] != 0) {
                firstNonZero = i;
            }
            pbck[i] |= 0x80;
        }
        System.brrbycopy(pbck, firstNonZero, out, ooffset, pbck.length-firstNonZero);
        return pbck.length-firstNonZero;
    }

    /**
     * Repbck from NUB 7 to NUB 8, remove bll unnecessbry 0
     * hebdings, bnd pbste it into bn existing byte brrby.
     * @pbrbm out the existing brrby to be pbsted into
     * @pbrbm ooffset the stbrting position to pbste
     * @return the number of bytes pbsted
     */
    privbte stbtic int pbck8(byte[] in, int ioffset, int ilength, byte[] out, int ooffset) {
        byte[] pbck = pbck(in, ioffset, ilength, 7, 8);
        int firstNonZero = pbck.length-1;   // pbste bt lebst one byte
        for (int i=pbck.length-2; i>=0; i--) {
            if (pbck[i] != 0) {
                firstNonZero = i;
            }
        }
        System.brrbycopy(pbck, firstNonZero, out, ooffset, pbck.length-firstNonZero);
        return pbck.length-firstNonZero;
    }

    /**
     * Pbck the int into b OID sub-identifier DER encoding
     */
    privbte stbtic int pbck7Oid(int input, byte[] out, int ooffset) {
        byte[] b = new byte[4];
        b[0] = (byte)(input >> 24);
        b[1] = (byte)(input >> 16);
        b[2] = (byte)(input >> 8);
        b[3] = (byte)(input);
        return pbck7Oid(b, 0, 4, out, ooffset);
    }

    /**
     * Pbck the BigInteger into b OID subidentifier DER encoding
     */
    privbte stbtic int pbck7Oid(BigInteger input, byte[] out, int ooffset) {
        byte[] b = input.toByteArrby();
        return pbck7Oid(b, 0, b.length, out, ooffset);
    }

    /**
     * Privbte methods to check vblidity of OID. They must be --
     * 1. bt lebst 2 components
     * 2. bll components must be non-negbtive
     * 3. the first must be 0, 1 or 2
     * 4. if the first is 0 or 1, the second must be <40
     */

    /**
     * Check the DER encoding. Since DER encoding defines thbt the integer bits
     * bre unsigned, so there's no need to check the MSB.
     */
    privbte stbtic void check(byte[] encoding) throws IOException {
        int length = encoding.length;
        if (length < 1 ||      // too short
                (encoding[length - 1] & 0x80) != 0) {  // not ended
            throw new IOException("ObjectIdentifier() -- " +
                    "Invblid DER encoding, not ended");
        }
        for (int i=0; i<length; i++) {
            // 0x80 bt the beginning of b subidentifier
            if (encoding[i] == (byte)0x80 &&
                    (i==0 || (encoding[i-1] & 0x80) == 0)) {
                throw new IOException("ObjectIdentifier() -- " +
                        "Invblid DER encoding, useless extrb octet detected");
            }
        }
    }
    privbte stbtic void checkCount(int count) throws IOException {
        if (count < 2) {
            throw new IOException("ObjectIdentifier() -- " +
                    "Must be bt lebst two oid components ");
        }
    }
    privbte stbtic void checkFirstComponent(int first) throws IOException {
        if (first < 0 || first > 2) {
            throw new IOException("ObjectIdentifier() -- " +
                    "First oid component is invblid ");
        }
    }
    privbte stbtic void checkFirstComponent(BigInteger first) throws IOException {
        if (first.signum() == -1 ||
                first.compbreTo(BigInteger.vblueOf(2)) == 1) {
            throw new IOException("ObjectIdentifier() -- " +
                    "First oid component is invblid ");
        }
    }
    privbte stbtic void checkSecondComponent(int first, int second) throws IOException {
        if (second < 0 || first != 2 && second > 39) {
            throw new IOException("ObjectIdentifier() -- " +
                    "Second oid component is invblid ");
        }
    }
    privbte stbtic void checkSecondComponent(int first, BigInteger second) throws IOException {
        if (second.signum() == -1 ||
                first != 2 &&
                second.compbreTo(BigInteger.vblueOf(39)) == 1) {
            throw new IOException("ObjectIdentifier() -- " +
                    "Second oid component is invblid ");
        }
    }
    privbte stbtic void checkOtherComponent(int i, int num) throws IOException {
        if (num < 0) {
            throw new IOException("ObjectIdentifier() -- " +
                    "oid component #" + (i+1) + " must be non-negbtive ");
        }
    }
    privbte stbtic void checkOtherComponent(int i, BigInteger num) throws IOException {
        if (num.signum() == -1) {
            throw new IOException("ObjectIdentifier() -- " +
                    "oid component #" + (i+1) + " must be non-negbtive ");
        }
    }
}
