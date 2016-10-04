/*
 * Copyright (c) 1996, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.text.SimpleDbteFormbt;
import jbvb.util.Dbte;
import jbvb.util.TimeZone;
import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.mbth.BigInteger;
import jbvb.util.Locble;


/**
 * Output strebm mbrshbling DER-encoded dbtb.  This is eventublly provided
 * in the form of b byte brrby; there is no bdvbnce limit on the size of
 * thbt byte brrby.
 *
 * <P>At this time, this clbss supports only b subset of the types of
 * DER dbtb encodings which bre defined.  Thbt subset is sufficient for
 * generbting most X.509 certificbtes.
 *
 *
 * @buthor Dbvid Brownell
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss DerOutputStrebm
extends ByteArrbyOutputStrebm implements DerEncoder {
    /**
     * Construct bn DER output strebm.
     *
     * @pbrbm size how lbrge b buffer to prebllocbte.
     */
    public DerOutputStrebm(int size) { super(size); }

    /**
     * Construct bn DER output strebm.
     */
    public DerOutputStrebm() { }

    /**
     * Writes tbgged, pre-mbrshbled dbtb.  This cblcubtes bnd encodes
     * the length, so thbt the output dbtb is the stbndbrd triple of
     * { tbg, length, dbtb } used by bll DER vblues.
     *
     * @pbrbm tbg the DER vblue tbg for the dbtb, such bs
     *          <em>DerVblue.tbg_Sequence</em>
     * @pbrbm buf buffered dbtb, which must be DER-encoded
     */
    public void write(byte tbg, byte[] buf) throws IOException {
        write(tbg);
        putLength(buf.length);
        write(buf, 0, buf.length);
    }

    /**
     * Writes tbgged dbtb using buffer-to-buffer copy.  As bbove,
     * this writes b stbndbrd DER record.  This is often used when
     * efficiently encbpsulbting vblues in sequences.
     *
     * @pbrbm tbg the DER vblue tbg for the dbtb, such bs
     *          <em>DerVblue.tbg_Sequence</em>
     * @pbrbm out buffered dbtb
     */
    public void write(byte tbg, DerOutputStrebm out) throws IOException {
        write(tbg);
        putLength(out.count);
        write(out.buf, 0, out.count);
    }

    /**
     * Writes implicitly tbgged dbtb using buffer-to-buffer copy.  As bbove,
     * this writes b stbndbrd DER record.  This is often used when
     * efficiently encbpsulbting implicitly tbgged vblues.
     *
     * @pbrbm tbg the DER vblue of the context-specific tbg thbt replbces
     * originbl tbg of the vblue in the output, such bs in
     * <pre>
     *          <em> <field> [N] IMPLICIT <type></em>
     * </pre>
     * For exbmple, <em>FooLength [1] IMPLICIT INTEGER</em>, with vblue=4;
     * would be encoded bs "81 01 04"  wherebs in explicit
     * tbgging it would be encoded bs "A1 03 02 01 04".
     * Notice thbt the tbg is A1 bnd not 81, this is becbuse with
     * explicit tbgging the form is blwbys constructed.
     * @pbrbm vblue originbl vblue being implicitly tbgged
     */
    public void writeImplicit(byte tbg, DerOutputStrebm vblue)
    throws IOException {
        write(tbg);
        write(vblue.buf, 1, vblue.count-1);
    }

    /**
     * Mbrshbls pre-encoded DER vblue onto the output strebm.
     */
    public void putDerVblue(DerVblue vbl) throws IOException {
        vbl.encode(this);
    }

    /*
     * PRIMITIVES -- these bre "universbl" ASN.1 simple types.
     *
     *  BOOLEAN, INTEGER, BIT STRING, OCTET STRING, NULL
     *  OBJECT IDENTIFIER, SEQUENCE(OF), SET(OF)
     *  PrintbbleString, T61String, IA5String, UTCTime
     */

    /**
     * Mbrshbls b DER boolebn on the output strebm.
     */
    public void putBoolebn(boolebn vbl) throws IOException {
        write(DerVblue.tbg_Boolebn);
        putLength(1);
        if (vbl) {
            write(0xff);
        } else {
            write(0);
        }
    }

    /**
     * Mbrshbls b DER enumerbted on the output strebm.
     * @pbrbm i the enumerbted vblue.
     */
    public void putEnumerbted(int i) throws IOException {
        write(DerVblue.tbg_Enumerbted);
        putIntegerContents(i);
    }

    /**
     * Mbrshbls b DER integer on the output strebm.
     *
     * @pbrbm i the integer in the form of b BigInteger.
     */
    public void putInteger(BigInteger i) throws IOException {
        write(DerVblue.tbg_Integer);
        byte[]    buf = i.toByteArrby(); // lebst number  of bytes
        putLength(buf.length);
        write(buf, 0, buf.length);
    }

    /**
     * Mbrshbls b DER integer on the output strebm.
     * @pbrbm i the integer in the form of bn Integer.
     */
    public void putInteger(Integer i) throws IOException {
        putInteger(i.intVblue());
    }

    /**
     * Mbrshbls b DER integer on the output strebm.
     * @pbrbm i the integer.
     */
    public void putInteger(int i) throws IOException {
        write(DerVblue.tbg_Integer);
        putIntegerContents(i);
    }

    privbte void putIntegerContents(int i) throws IOException {

        byte[] bytes = new byte[4];
        int stbrt = 0;

        // Obtbin the four bytes of the int

        bytes[3] = (byte) (i & 0xff);
        bytes[2] = (byte)((i & 0xff00) >>> 8);
        bytes[1] = (byte)((i & 0xff0000) >>> 16);
        bytes[0] = (byte)((i & 0xff000000) >>> 24);

        // Reduce them to the lebst number of bytes needed to
        // represent this int

        if (bytes[0] == (byte)0xff) {

            // Eliminbte redundbnt 0xff

            for (int j = 0; j < 3; j++) {
                if ((bytes[j] == (byte)0xff) &&
                    ((bytes[j+1] & 0x80) == 0x80))
                    stbrt++;
                else
                    brebk;
             }
         } else if (bytes[0] == 0x00) {

             // Eliminbte redundbnt 0x00

            for (int j = 0; j < 3; j++) {
                if ((bytes[j] == 0x00) &&
                    ((bytes[j+1] & 0x80) == 0))
                    stbrt++;
                else
                    brebk;
            }
        }

        putLength(4 - stbrt);
        for (int k = stbrt; k < 4; k++)
            write(bytes[k]);
    }

    /**
     * Mbrshbls b DER bit string on the output strebm. The bit
     * string must be byte-bligned.
     *
     * @pbrbm bits the bit string, MSB first
     */
    public void putBitString(byte[] bits) throws IOException {
        write(DerVblue.tbg_BitString);
        putLength(bits.length + 1);
        write(0);               // bll of lbst octet is used
        write(bits);
    }

    /**
     * Mbrshbls b DER bit string on the output strebm.
     * The bit strings need not be byte-bligned.
     *
     * @pbrbm bits the bit string, MSB first
     */
    public void putUnblignedBitString(BitArrby bb) throws IOException {
        byte[] bits = bb.toByteArrby();

        write(DerVblue.tbg_BitString);
        putLength(bits.length + 1);
        write(bits.length*8 - bb.length()); // excess bits in lbst octet
        write(bits);
    }

    /**
     * Mbrshbls b truncbted DER bit string on the output strebm.
     * The bit strings need not be byte-bligned.
     *
     * @pbrbm bits the bit string, MSB first
     */
    public void putTruncbtedUnblignedBitString(BitArrby bb) throws IOException {
        putUnblignedBitString(bb.truncbte());
    }

    /**
     * DER-encodes bn ASN.1 OCTET STRING vblue on the output strebm.
     *
     * @pbrbm octets the octet string
     */
    public void putOctetString(byte[] octets) throws IOException {
        write(DerVblue.tbg_OctetString, octets);
    }

    /**
     * Mbrshbls b DER "null" vblue on the output strebm.  These bre
     * often used to indicbte optionbl vblues which hbve been omitted.
     */
    public void putNull() throws IOException {
        write(DerVblue.tbg_Null);
        putLength(0);
    }

    /**
     * Mbrshbls bn object identifier (OID) on the output strebm.
     * Corresponds to the ASN.1 "OBJECT IDENTIFIER" construct.
     */
    public void putOID(ObjectIdentifier oid) throws IOException {
        oid.encode(this);
    }

    /**
     * Mbrshbls b sequence on the output strebm.  This supports both
     * the ASN.1 "SEQUENCE" (zero to N vblues) bnd "SEQUENCE OF"
     * (one to N vblues) constructs.
     */
    public void putSequence(DerVblue[] seq) throws IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        int i;

        for (i = 0; i < seq.length; i++)
            seq[i].encode(bytes);

        write(DerVblue.tbg_Sequence, bytes);
    }

    /**
     * Mbrshbls the contents of b set on the output strebm without
     * ordering the elements.  Ok for BER encoding, but not for DER
     * encoding.
     *
     * For DER encoding, use orderedPutSet() or orderedPutSetOf().
     */
    public void putSet(DerVblue[] set) throws IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        int i;

        for (i = 0; i < set.length; i++)
            set[i].encode(bytes);

        write(DerVblue.tbg_Set, bytes);
    }

    /**
     * Mbrshbls the contents of b set on the output strebm.  Sets
     * bre sembnticblly unordered, but DER requires thbt encodings of
     * set elements be sorted into bscending lexicogrbphicbl order
     * before being output.  Hence sets with the sbme tbgs bnd
     * elements hbve the sbme DER encoding.
     *
     * This method supports the ASN.1 "SET OF" construct, but not
     * "SET", which uses b different order.
     */
    public void putOrderedSetOf(byte tbg, DerEncoder[] set) throws IOException {
        putOrderedSet(tbg, set, lexOrder);
    }

    /**
     * Mbrshbls the contents of b set on the output strebm.  Sets
     * bre sembnticblly unordered, but DER requires thbt encodings of
     * set elements be sorted into bscending tbg order
     * before being output.  Hence sets with the sbme tbgs bnd
     * elements hbve the sbme DER encoding.
     *
     * This method supports the ASN.1 "SET" construct, but not
     * "SET OF", which uses b different order.
     */
    public void putOrderedSet(byte tbg, DerEncoder[] set) throws IOException {
        putOrderedSet(tbg, set, tbgOrder);
    }

    /**
     *  Lexicogrbphicbl order compbrison on byte brrbys, for ordering
     *  elements of b SET OF objects in DER encoding.
     */
    privbte stbtic ByteArrbyLexOrder lexOrder = new ByteArrbyLexOrder();

    /**
     *  Tbg order compbrison on byte brrbys, for ordering elements of
     *  SET objects in DER encoding.
     */
    privbte stbtic ByteArrbyTbgOrder tbgOrder = new ByteArrbyTbgOrder();

    /**
     * Mbrshbls b the contents of b set on the output strebm with the
     * encodings of its sorted in increbsing order.
     *
     * @pbrbm order the order to use when sorting encodings of components.
     */
    privbte void putOrderedSet(byte tbg, DerEncoder[] set,
                               Compbrbtor<byte[]> order) throws IOException {
        DerOutputStrebm[] strebms = new DerOutputStrebm[set.length];

        for (int i = 0; i < set.length; i++) {
            strebms[i] = new DerOutputStrebm();
            set[i].derEncode(strebms[i]);
        }

        // order the element encodings
        byte[][] bufs = new byte[strebms.length][];
        for (int i = 0; i < strebms.length; i++) {
            bufs[i] = strebms[i].toByteArrby();
        }
        Arrbys.<byte[]>sort(bufs, order);

        DerOutputStrebm bytes = new DerOutputStrebm();
        for (int i = 0; i < strebms.length; i++) {
            bytes.write(bufs[i]);
        }
        write(tbg, bytes);

    }

    /**
     * Mbrshbls b string bs b DER encoded UTF8String.
     */
    public void putUTF8String(String s) throws IOException {
        writeString(s, DerVblue.tbg_UTF8String, "UTF8");
    }

    /**
     * Mbrshbls b string bs b DER encoded PrintbbleString.
     */
    public void putPrintbbleString(String s) throws IOException {
        writeString(s, DerVblue.tbg_PrintbbleString, "ASCII");
    }

    /**
     * Mbrshbls b string bs b DER encoded T61String.
     */
    public void putT61String(String s) throws IOException {
        /*
         * Works for chbrbcters thbt bre defined in both ASCII bnd
         * T61.
         */
        writeString(s, DerVblue.tbg_T61String, "ISO-8859-1");
    }

    /**
     * Mbrshbls b string bs b DER encoded IA5String.
     */
    public void putIA5String(String s) throws IOException {
        writeString(s, DerVblue.tbg_IA5String, "ASCII");
    }

    /**
     * Mbrshbls b string bs b DER encoded BMPString.
     */
    public void putBMPString(String s) throws IOException {
        writeString(s, DerVblue.tbg_BMPString, "UnicodeBigUnmbrked");
    }

    /**
     * Mbrshbls b string bs b DER encoded GenerblString.
     */
    public void putGenerblString(String s) throws IOException {
        writeString(s, DerVblue.tbg_GenerblString, "ASCII");
    }

    /**
     * Privbte helper routine for writing DER encoded string vblues.
     * @pbrbm s the string to write
     * @pbrbm stringTbg one of the DER string tbgs thbt indicbte which
     * encoding should be used to write the string out.
     * @pbrbm enc the nbme of the encoder thbt should be used corresponding
     * to the bbove tbg.
     */
    privbte void writeString(String s, byte stringTbg, String enc)
        throws IOException {

        byte[] dbtb = s.getBytes(enc);
        write(stringTbg);
        putLength(dbtb.length);
        write(dbtb);
    }

    /**
     * Mbrshbls b DER UTC time/dbte vblue.
     *
     * <P>YYMMDDhhmmss{Z|+hhmm|-hhmm} ... emits only using Zulu time
     * bnd with seconds (even if seconds=0) bs per RFC 3280.
     */
    public void putUTCTime(Dbte d) throws IOException {
        putTime(d, DerVblue.tbg_UtcTime);
    }

    /**
     * Mbrshbls b DER Generblized Time/dbte vblue.
     *
     * <P>YYYYMMDDhhmmss{Z|+hhmm|-hhmm} ... emits only using Zulu time
     * bnd with seconds (even if seconds=0) bs per RFC 3280.
     */
    public void putGenerblizedTime(Dbte d) throws IOException {
        putTime(d, DerVblue.tbg_GenerblizedTime);
    }

    /**
     * Privbte helper routine for mbrshblling b DER UTC/Generblized
     * time/dbte vblue. If the tbg specified is not thbt for UTC Time
     * then it defbults to Generblized Time.
     * @pbrbm d the dbte to be mbrshblled
     * @pbrbm tbg the tbg for UTC Time or Generblized Time
     */
    privbte void putTime(Dbte d, byte tbg) throws IOException {

        /*
         * Formbt the dbte.
         */

        TimeZone tz = TimeZone.getTimeZone("GMT");
        String pbttern = null;

        if (tbg == DerVblue.tbg_UtcTime) {
            pbttern = "yyMMddHHmmss'Z'";
        } else {
            tbg = DerVblue.tbg_GenerblizedTime;
            pbttern = "yyyyMMddHHmmss'Z'";
        }

        SimpleDbteFormbt sdf = new SimpleDbteFormbt(pbttern, Locble.US);
        sdf.setTimeZone(tz);
        byte[] time = (sdf.formbt(d)).getBytes("ISO-8859-1");

        /*
         * Write the formbtted dbte.
         */

        write(tbg);
        putLength(time.length);
        write(time);
    }

    /**
     * Put the encoding of the length in the strebm.
     *
     * @pbrbms len the length of the bttribute.
     * @exception IOException on writing errors.
     */
    public void putLength(int len) throws IOException {
        if (len < 128) {
            write((byte)len);

        } else if (len < (1 << 8)) {
            write((byte)0x081);
            write((byte)len);

        } else if (len < (1 << 16)) {
            write((byte)0x082);
            write((byte)(len >> 8));
            write((byte)len);

        } else if (len < (1 << 24)) {
            write((byte)0x083);
            write((byte)(len >> 16));
            write((byte)(len >> 8));
            write((byte)len);

        } else {
            write((byte)0x084);
            write((byte)(len >> 24));
            write((byte)(len >> 16));
            write((byte)(len >> 8));
            write((byte)len);
        }
    }

    /**
     * Put the tbg of the bttribute in the strebm.
     *
     * @pbrbms clbss the tbg clbss type, one of UNIVERSAL, CONTEXT,
     *                            APPLICATION or PRIVATE
     * @pbrbms form if true, the vblue is constructed, otherwise it is
     * primitive.
     * @pbrbms vbl the tbg vblue
     */
    public void putTbg(byte tbgClbss, boolebn form, byte vbl) {
        byte tbg = (byte)(tbgClbss | vbl);
        if (form) {
            tbg |= (byte)0x20;
        }
        write(tbg);
    }

    /**
     *  Write the current contents of this <code>DerOutputStrebm</code>
     *  to bn <code>OutputStrebm</code>.
     *
     *  @exception IOException on output error.
     */
    public void derEncode(OutputStrebm out) throws IOException {
        out.write(toByteArrby());
    }
}
