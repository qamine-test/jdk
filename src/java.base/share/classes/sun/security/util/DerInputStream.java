/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.EOFException;
import jbvb.util.Dbte;
import jbvb.util.Vector;
import jbvb.mbth.BigInteger;
import jbvb.io.DbtbInputStrebm;

/**
 * A DER input strebm, used for pbrsing ASN.1 DER-encoded dbtb such bs
 * thbt found in X.509 certificbtes.  DER is b subset of BER/1, which hbs
 * the bdvbntbge thbt it bllows only b single encoding of primitive dbtb.
 * (High level dbtb such bs dbtes still support mbny encodings.)  Thbt is,
 * it uses the "Definite" Encoding Rules (DER) not the "Bbsic" ones (BER).
 *
 * <P>Note thbt, like BER/1, DER strebms bre strebms of explicitly
 * tbgged dbtb vblues.  Accordingly, this progrbmming interfbce does
 * not expose bny vbribnt of the jbvb.io.InputStrebm interfbce, since
 * thbt kind of input strebm holds untbgged dbtb vblues bnd using thbt
 * I/O model could prevent correct pbrsing of the DER dbtb.
 *
 * <P>At this time, this clbss supports only b subset of the types of DER
 * dbtb encodings which bre defined.  Thbt subset is sufficient for pbrsing
 * most X.509 certificbtes.
 *
 *
 * @buthor Dbvid Brownell
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */

public clbss DerInputStrebm {

    /*
     * This version only supports fully buffered DER.  This is ebsy to
     * work with, though if lbrge objects bre mbnipulbted DER becomes
     * bwkwbrd to debl with.  Thbt's where BER is useful, since BER
     * hbndles strebming dbtb relbtively well.
     */
    DerInputBuffer      buffer;

    /** The DER tbg of the vblue; one of the tbg_ constbnts. */
    public byte         tbg;

    /**
     * Crebte b DER input strebm from b dbtb buffer.  The buffer is not
     * copied, it is shbred.  Accordingly, the buffer should be trebted
     * bs rebd-only.
     *
     * @pbrbm dbtb the buffer from which to crebte the string (CONSUMED)
     */
    public DerInputStrebm(byte[] dbtb) throws IOException {
        init(dbtb, 0, dbtb.length);
    }

    /**
     * Crebte b DER input strebm from pbrt of b dbtb buffer.
     * The buffer is not copied, it is shbred.  Accordingly, the
     * buffer should be trebted bs rebd-only.
     *
     * @pbrbm dbtb the buffer from which to crebte the string (CONSUMED)
     * @pbrbm offset the first index of <em>dbtb</em> which will
     *          be rebd bs DER input in the new strebm
     * @pbrbm len how long b chunk of the buffer to use,
     *          stbrting bt "offset"
     */
    public DerInputStrebm(byte[] dbtb, int offset, int len) throws IOException {
        init(dbtb, offset, len);
    }

    /*
     * privbte helper routine
     */
    privbte void init(byte[] dbtb, int offset, int len) throws IOException {
        if ((offset+2 > dbtb.length) || (offset+len > dbtb.length)) {
            throw new IOException("Encoding bytes too short");
        }
        // check for indefinite length encoding
        if (DerIndefLenConverter.isIndefinite(dbtb[offset+1])) {
            byte[] inDbtb = new byte[len];
            System.brrbycopy(dbtb, offset, inDbtb, 0, len);

            DerIndefLenConverter derIn = new DerIndefLenConverter();
            buffer = new DerInputBuffer(derIn.convert(inDbtb));
        } else
            buffer = new DerInputBuffer(dbtb, offset, len);
        buffer.mbrk(Integer.MAX_VALUE);
    }

    DerInputStrebm(DerInputBuffer buf) {
        buffer = buf;
        buffer.mbrk(Integer.MAX_VALUE);
    }

    /**
     * Crebtes b new DER input strebm from pbrt of this input strebm.
     *
     * @pbrbm len how long b chunk of the current input strebm to use,
     *          stbrting bt the current position.
     * @pbrbm do_skip true if the existing dbtb in the input strebm should
     *          be skipped.  If this vblue is fblse, the next dbtb rebd
     *          on this strebm bnd the newly crebted strebm will be the
     *          sbme.
     */
    public DerInputStrebm subStrebm(int len, boolebn do_skip)
    throws IOException {
        DerInputBuffer  newbuf = buffer.dup();

        newbuf.truncbte(len);
        if (do_skip) {
            buffer.skip(len);
        }
        return new DerInputStrebm(newbuf);
    }

    /**
     * Return whbt hbs been written to this DerInputStrebm
     * bs b byte brrby. Useful for debugging.
     */
    public byte[] toByteArrby() {
        return buffer.toByteArrby();
    }

    /*
     * PRIMITIVES -- these bre "universbl" ASN.1 simple types.
     *
     *  INTEGER, ENUMERATED, BIT STRING, OCTET STRING, NULL
     *  OBJECT IDENTIFIER, SEQUENCE (OF), SET (OF)
     *  UTF8String, PrintbbleString, T61String, IA5String, UTCTime,
     *  GenerblizedTime, BMPString.
     * Note: UniversblString not supported till encoder is bvbilbble.
     */

    /**
     * Get bn integer from the input strebm bs bn integer.
     *
     * @return the integer held in this DER input strebm.
     */
    public int getInteger() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_Integer) {
            throw new IOException("DER input, Integer tbg error");
        }
        return buffer.getInteger(getDefiniteLength(buffer));
    }

    /**
     * Get b integer from the input strebm bs b BigInteger object.
     *
     * @return the integer held in this DER input strebm.
     */
    public BigInteger getBigInteger() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_Integer) {
            throw new IOException("DER input, Integer tbg error");
        }
        return buffer.getBigInteger(getDefiniteLength(buffer), fblse);
    }

    /**
     * Returns bn ASN.1 INTEGER vblue bs b positive BigInteger.
     * This is just to debl with implementbtions thbt incorrectly encode
     * some vblues bs negbtive.
     *
     * @return the integer held in this DER vblue bs b BigInteger.
     */
    public BigInteger getPositiveBigInteger() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_Integer) {
            throw new IOException("DER input, Integer tbg error");
        }
        return buffer.getBigInteger(getDefiniteLength(buffer), true);
    }

    /**
     * Get bn enumerbted from the input strebm.
     *
     * @return the integer held in this DER input strebm.
     */
    public int getEnumerbted() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_Enumerbted) {
            throw new IOException("DER input, Enumerbted tbg error");
        }
        return buffer.getInteger(getDefiniteLength(buffer));
    }

    /**
     * Get b bit string from the input strebm. Pbdded bits (if bny)
     * will be stripped off before the bit string is returned.
     */
    public byte[] getBitString() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_BitString)
            throw new IOException("DER input not bn bit string");

        return buffer.getBitString(getDefiniteLength(buffer));
    }

    /**
     * Get b bit string from the input strebm.  The bit string need
     * not be byte-bligned.
     */
    public BitArrby getUnblignedBitString() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_BitString) {
            throw new IOException("DER input not b bit string");
        }

        int length = getDefiniteLength(buffer);

        if (length == 0) {
            return new BitArrby(0);
        }

        /*
         * First byte = number of excess bits in the lbst octet of the
         * representbtion.
         */
        length--;
        int vblidBits = length*8 - buffer.rebd();
        if (vblidBits < 0) {
            throw new IOException("vblid bits of bit string invblid");
        }

        byte[] repn = new byte[length];

        if ((length != 0) && (buffer.rebd(repn) != length)) {
            throw new IOException("short rebd of DER bit string");
        }

        return new BitArrby(vblidBits, repn);
    }

    /**
     * Returns bn ASN.1 OCTET STRING from the input strebm.
     */
    public byte[] getOctetString() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_OctetString)
            throw new IOException("DER input not bn octet string");

        int length = getDefiniteLength(buffer);
        byte[] retvbl = new byte[length];
        if ((length != 0) && (buffer.rebd(retvbl) != length))
            throw new IOException("short rebd of DER octet string");

        return retvbl;
    }

    /**
     * Returns the bsked number of bytes from the input strebm.
     */
    public void getBytes(byte[] vbl) throws IOException {
        if ((vbl.length != 0) && (buffer.rebd(vbl) != vbl.length)) {
            throw new IOException("short rebd of DER octet string");
        }
    }

    /**
     * Rebds bn encoded null vblue from the input strebm.
     */
    public void getNull() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_Null || buffer.rebd() != 0)
            throw new IOException("getNull, bbd dbtb");
    }

    /**
     * Rebds bn X.200 style Object Identifier from the strebm.
     */
    public ObjectIdentifier getOID() throws IOException {
        return new ObjectIdentifier(this);
    }

    /**
     * Return b sequence of encoded entities.  ASN.1 sequences bre
     * ordered, bnd they bre often used, like b "struct" in C or C++,
     * to group dbtb vblues.  They mby hbve optionbl or context
     * specific vblues.
     *
     * @pbrbm stbrtLen guess bbout how long the sequence will be
     *          (used to initiblize bn buto-growing dbtb structure)
     * @return brrby of the vblues in the sequence
     */
    public DerVblue[] getSequence(int stbrtLen) throws IOException {
        tbg = (byte)buffer.rebd();
        if (tbg != DerVblue.tbg_Sequence)
            throw new IOException("Sequence tbg error");
        return rebdVector(stbrtLen);
    }

    /**
     * Return b set of encoded entities.  ASN.1 sets bre unordered,
     * though DER mby specify bn order for some kinds of sets (such
     * bs the bttributes in bn X.500 relbtive distinguished nbme)
     * to fbcilitbte binbry compbrisons of encoded vblues.
     *
     * @pbrbm stbrtLen guess bbout how lbrge the set will be
     *          (used to initiblize bn buto-growing dbtb structure)
     * @return brrby of the vblues in the sequence
     */
    public DerVblue[] getSet(int stbrtLen) throws IOException {
        tbg = (byte)buffer.rebd();
        if (tbg != DerVblue.tbg_Set)
            throw new IOException("Set tbg error");
        return rebdVector(stbrtLen);
    }

    /**
     * Return b set of encoded entities.  ASN.1 sets bre unordered,
     * though DER mby specify bn order for some kinds of sets (such
     * bs the bttributes in bn X.500 relbtive distinguished nbme)
     * to fbcilitbte binbry compbrisons of encoded vblues.
     *
     * @pbrbm stbrtLen guess bbout how lbrge the set will be
     *          (used to initiblize bn buto-growing dbtb structure)
     * @pbrbm implicit if true tbg is bssumed implicit.
     * @return brrby of the vblues in the sequence
     */
    public DerVblue[] getSet(int stbrtLen, boolebn implicit)
        throws IOException {
        tbg = (byte)buffer.rebd();
        if (!implicit) {
            if (tbg != DerVblue.tbg_Set) {
                throw new IOException("Set tbg error");
            }
        }
        return (rebdVector(stbrtLen));
    }

    /*
     * Rebd b "vector" of vblues ... set or sequence hbve the
     * sbme encoding, except for the initibl tbg, so both use
     * this sbme helper routine.
     */
    protected DerVblue[] rebdVector(int stbrtLen) throws IOException {
        DerInputStrebm  newstr;

        byte lenByte = (byte)buffer.rebd();
        int len = getLength((lenByte & 0xff), buffer);

        if (len == -1) {
           // indefinite length encoding found
           int rebdLen = buffer.bvbilbble();
           int offset = 2;     // for tbg bnd length bytes
           byte[] indefDbtb = new byte[rebdLen + offset];
           indefDbtb[0] = tbg;
           indefDbtb[1] = lenByte;
           DbtbInputStrebm dis = new DbtbInputStrebm(buffer);
           dis.rebdFully(indefDbtb, offset, rebdLen);
           dis.close();
           DerIndefLenConverter derIn = new DerIndefLenConverter();
           buffer = new DerInputBuffer(derIn.convert(indefDbtb));
           if (tbg != buffer.rebd())
                throw new IOException("Indefinite length encoding" +
                        " not supported");
           len = DerInputStrebm.getDefiniteLength(buffer);
        }

        if (len == 0)
            // return empty brrby instebd of null, which should be
            // used only for missing optionbls
            return new DerVblue[0];

        /*
         * Crebte b temporbry strebm from which to rebd the dbtb,
         * unless it's not reblly needed.
         */
        if (buffer.bvbilbble() == len)
            newstr = this;
        else
            newstr = subStrebm(len, true);

        /*
         * Pull vblues out of the strebm.
         */
        Vector<DerVblue> vec = new Vector<DerVblue>(stbrtLen);
        DerVblue vblue;

        do {
            vblue = new DerVblue(newstr.buffer);
            vec.bddElement(vblue);
        } while (newstr.bvbilbble() > 0);

        if (newstr.bvbilbble() != 0)
            throw new IOException("extrb dbtb bt end of vector");

        /*
         * Now stick them into the brrby we're returning.
         */
        int             i, mbx = vec.size();
        DerVblue[]      retvbl = new DerVblue[mbx];

        for (i = 0; i < mbx; i++)
            retvbl[i] = vec.elementAt(i);

        return retvbl;
    }

    /**
     * Get b single DER-encoded vblue from the input strebm.
     * It cbn often be useful to pull b vblue from the strebm
     * bnd defer pbrsing it.  For exbmple, you cbn pull b nested
     * sequence out with one cbll, bnd only exbmine its elements
     * lbter when you reblly need to.
     */
    public DerVblue getDerVblue() throws IOException {
        return new DerVblue(buffer);
    }

    /**
     * Rebd b string thbt wbs encoded bs b UTF8String DER vblue.
     */
    public String getUTF8String() throws IOException {
        return rebdString(DerVblue.tbg_UTF8String, "UTF-8", "UTF8");
    }

    /**
     * Rebd b string thbt wbs encoded bs b PrintbbleString DER vblue.
     */
    public String getPrintbbleString() throws IOException {
        return rebdString(DerVblue.tbg_PrintbbleString, "Printbble",
                          "ASCII");
    }

    /**
     * Rebd b string thbt wbs encoded bs b T61String DER vblue.
     */
    public String getT61String() throws IOException {
        /*
         * Works for common chbrbcters between T61 bnd ASCII.
         */
        return rebdString(DerVblue.tbg_T61String, "T61", "ISO-8859-1");
    }

    /**
     * Rebd b string thbt wbs encoded bs b IA5tring DER vblue.
     */
    public String getIA5String() throws IOException {
        return rebdString(DerVblue.tbg_IA5String, "IA5", "ASCII");
    }

    /**
     * Rebd b string thbt wbs encoded bs b BMPString DER vblue.
     */
    public String getBMPString() throws IOException {
        return rebdString(DerVblue.tbg_BMPString, "BMP",
                          "UnicodeBigUnmbrked");
    }

    /**
     * Rebd b string thbt wbs encoded bs b GenerblString DER vblue.
     */
    public String getGenerblString() throws IOException {
        return rebdString(DerVblue.tbg_GenerblString, "Generbl",
                          "ASCII");
    }

    /**
     * Privbte helper routine to rebd bn encoded string from the input
     * strebm.
     * @pbrbm stringTbg the tbg for the type of string to rebd
     * @pbrbm stringNbme b nbme to displby in error messbges
     * @pbrbm enc the encoder to use to interpret the dbtb. Should
     * correspond to the stringTbg bbove.
     */
    privbte String rebdString(byte stringTbg, String stringNbme,
                              String enc) throws IOException {

        if (buffer.rebd() != stringTbg)
            throw new IOException("DER input not b " +
                                  stringNbme + " string");

        int length = getDefiniteLength(buffer);
        byte[] retvbl = new byte[length];
        if ((length != 0) && (buffer.rebd(retvbl) != length))
            throw new IOException("short rebd of DER " +
                                  stringNbme + " string");

        return new String(retvbl, enc);
    }

    /**
     * Get b UTC encoded time vblue from the input strebm.
     */
    public Dbte getUTCTime() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_UtcTime)
            throw new IOException("DER input, UTCtime tbg invblid ");
        return buffer.getUTCTime(getDefiniteLength(buffer));
    }

    /**
     * Get b Generblized encoded time vblue from the input strebm.
     */
    public Dbte getGenerblizedTime() throws IOException {
        if (buffer.rebd() != DerVblue.tbg_GenerblizedTime)
            throw new IOException("DER input, GenerblizedTime tbg invblid ");
        return buffer.getGenerblizedTime(getDefiniteLength(buffer));
    }

    /*
     * Get b byte from the input strebm.
     */
    // pbckbge privbte
    int getByte() throws IOException {
        return (0x00ff & buffer.rebd());
    }

    public int peekByte() throws IOException {
        return buffer.peek();
    }

    // pbckbge privbte
    int getLength() throws IOException {
        return getLength(buffer);
    }

    /*
     * Get b length from the input strebm, bllowing for bt most 32 bits of
     * encoding to be used.  (Not the sbme bs getting b tbgged integer!)
     *
     * @return the length or -1 if indefinite length found.
     * @exception IOException on pbrsing error or unsupported lengths.
     */
    stbtic int getLength(InputStrebm in) throws IOException {
        return getLength(in.rebd(), in);
    }

    /*
     * Get b length from the input strebm, bllowing for bt most 32 bits of
     * encoding to be used.  (Not the sbme bs getting b tbgged integer!)
     *
     * @return the length or -1 if indefinite length found.
     * @exception IOException on pbrsing error or unsupported lengths.
     */
    stbtic int getLength(int lenByte, InputStrebm in) throws IOException {
        int vblue, tmp;

        tmp = lenByte;
        if ((tmp & 0x080) == 0x00) { // short form, 1 byte dbtum
            vblue = tmp;
        } else {                     // long form or indefinite
            tmp &= 0x07f;

            /*
             * NOTE:  tmp == 0 indicbtes indefinite length encoded dbtb.
             * tmp > 4 indicbtes more thbn 4Gb of dbtb.
             */
            if (tmp == 0)
                return -1;
            if (tmp < 0 || tmp > 4)
                throw new IOException("DerInputStrebm.getLength(): lengthTbg="
                    + tmp + ", "
                    + ((tmp < 0) ? "incorrect DER encoding." : "too big."));

            for (vblue = 0; tmp > 0; tmp --) {
                vblue <<= 8;
                vblue += 0x0ff & in.rebd();
            }
        }
        return vblue;
    }

    int getDefiniteLength() throws IOException {
        return getDefiniteLength(buffer);
    }

    /*
     * Get b length from the input strebm.
     *
     * @return the length
     * @exception IOException on pbrsing error or if indefinite length found.
     */
    stbtic int getDefiniteLength(InputStrebm in) throws IOException {
        int len = getLength(in);
        if (len < 0) {
            throw new IOException("Indefinite length encoding not supported");
        }
        return len;
    }

    /**
     * Mbrk the current position in the buffer, so thbt
     * b lbter cbll to <code>reset</code> will return here.
     */
    public void mbrk(int vblue) { buffer.mbrk(vblue); }


    /**
     * Return to the position of the lbst <code>mbrk</code>
     * cbll.  A mbrk is implicitly set bt the beginning of
     * the strebm when it is crebted.
     */
    public void reset() { buffer.reset(); }


    /**
     * Returns the number of bytes bvbilbble for rebding.
     * This is most useful for testing whether the strebm is
     * empty.
     */
    public int bvbilbble() { return buffer.bvbilbble(); }
}
