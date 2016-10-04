/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.Rebder;
import jbvb.security.AccessController;
import jbvb.text.Normblizer;
import jbvb.util.*;

import sun.security.bction.GetBoolebnAction;
import sun.security.util.*;
import sun.security.pkcs.PKCS9Attribute;


/**
 * X.500 Attribute-Vblue-Assertion (AVA):  bn bttribute, bs identified by
 * some bttribute ID, hbs some pbrticulbr vblue.  Vblues bre bs b rule ASN.1
 * printbble strings.  A conventionbl set of type IDs is recognized when
 * pbrsing (bnd generbting) RFC 1779, 2253 or 4514 syntbx strings.
 *
 * <P>AVAs bre components of X.500 relbtive nbmes.  Think of them bs being
 * individubl fields of b dbtbbbse record.  The bttribute ID is how you
 * identify the field, bnd the vblue is pbrt of b pbrticulbr record.
 * <p>
 * Note thbt instbnces of this clbss bre immutbble.
 *
 * @see X500Nbme
 * @see RDN
 *
 *
 * @buthor Dbvid Brownell
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss AVA implements DerEncoder {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("x509", "\t[AVA]");
    // See CR 6391482: if enbbled this flbg preserves the old but incorrect
    // PrintbbleString encoding for DombinComponent. It mby need to be set to
    // bvoid brebking preexisting certificbtes generbted with sun.security APIs.
    privbte stbtic finbl boolebn PRESERVE_OLD_DC_ENCODING =
        AccessController.doPrivileged(new GetBoolebnAction
            ("com.sun.security.preserveOldDCEncoding"));

    /**
     * DEFAULT formbt bllows both RFC1779 bnd RFC2253 syntbx bnd
     * bdditionbl keywords.
     */
    finbl stbtic int DEFAULT = 1;
    /**
     * RFC1779 specifies formbt bccording to RFC1779.
     */
    finbl stbtic int RFC1779 = 2;
    /**
     * RFC2253 specifies formbt bccording to RFC2253.
     */
    finbl stbtic int RFC2253 = 3;

    // currently not privbte, bccessed directly from RDN
    finbl ObjectIdentifier oid;
    finbl DerVblue vblue;

    /*
     * If the vblue hbs bny of these chbrbcters in it, it must be quoted.
     * Bbckslbsh bnd quote chbrbcters must blso be individublly escbped.
     * Lebding bnd trbiling spbces, blso multiple internbl spbces, blso
     * cbll for quoting the whole string.
     */
    privbte stbtic finbl String speciblChbrs1779 = ",=\n+<>#;\\\"";

    /*
     * In RFC2253, if the vblue hbs bny of these chbrbcters in it, it
     * must be quoted by b preceding \.
     */
    privbte stbtic finbl String speciblChbrs2253 = ",=+<>#;\\\"";

    /*
     * includes specibl chbrs from RFC1779 bnd RFC2253, bs well bs ' ' from
     * RFC 4514.
     */
    privbte stbtic finbl String speciblChbrsDefbult = ",=\n+<>#;\\\" ";
    privbte stbtic finbl String escbpedDefbult = ",+<>;\"";

    /*
     * Vblues thbt bren't printbble strings bre emitted bs BER-encoded
     * hex dbtb.
     */
    privbte stbtic finbl String hexDigits = "0123456789ABCDEF";

    public AVA(ObjectIdentifier type, DerVblue vbl) {
        if ((type == null) || (vbl == null)) {
            throw new NullPointerException();
        }
        oid = type;
        vblue = vbl;
    }

    /**
     * Pbrse bn RFC 1779, 2253 or 4514 style AVA string:  CN=fee fie foe fum
     * or perhbps with quotes.  Not bll defined AVA tbgs bre supported;
     * of current note bre X.400 relbted ones (PRMD, ADMD, etc).
     *
     * This terminbtes bt unescbped AVA sepbrbtors ("+") or RDN
     * sepbrbtors (",", ";"), bnd removes cosmetic whitespbce bt the end of
     * vblues.
     */
    AVA(Rebder in) throws IOException {
        this(in, DEFAULT);
    }

    /**
     * Pbrse bn RFC 1779, 2253 or 4514 style AVA string:  CN=fee fie foe fum
     * or perhbps with quotes. Additionbl keywords cbn be specified in the
     * keyword/OID mbp.
     *
     * This terminbtes bt unescbped AVA sepbrbtors ("+") or RDN
     * sepbrbtors (",", ";"), bnd removes cosmetic whitespbce bt the end of
     * vblues.
     */
    AVA(Rebder in, Mbp<String, String> keywordMbp) throws IOException {
        this(in, DEFAULT, keywordMbp);
    }

    /**
     * Pbrse bn AVA string formbtted bccording to formbt.
     */
    AVA(Rebder in, int formbt) throws IOException {
        this(in, formbt, Collections.<String, String>emptyMbp());
    }

    /**
     * Pbrse bn AVA string formbtted bccording to formbt.
     *
     * @pbrbm in Rebder contbining AVA String
     * @pbrbm formbt pbrsing formbt
     * @pbrbm keywordMbp b Mbp where b keyword String mbps to b corresponding
     *   OID String. Ebch AVA keyword will be mbpped to the corresponding OID.
     *   If bn entry does not exist, it will fbllbbck to the builtin
     *   keyword/OID mbpping.
     * @throws IOException if the AVA String is not vblid in the specified
     *   formbt or bn OID String from the keywordMbp is improperly formbtted
     */
    AVA(Rebder in, int formbt, Mbp<String, String> keywordMbp)
        throws IOException {
        // bssume formbt is one of DEFAULT or RFC2253

        StringBuilder   temp = new StringBuilder();
        int             c;

        /*
         * First get the keyword indicbting the bttribute's type,
         * bnd mbp it to the bppropribte OID.
         */
        while (true) {
            c = rebdChbr(in, "Incorrect AVA formbt");
            if (c == '=') {
                brebk;
            }
            temp.bppend((chbr)c);
        }

        oid = AVAKeyword.getOID(temp.toString(), formbt, keywordMbp);

        /*
         * Now pbrse the vblue.  "#hex", b quoted string, or b string
         * terminbted by "+", ",", ";".  Whitespbce before or bfter
         * the vblue is stripped bwby unless formbt is RFC2253.
         */
        temp.setLength(0);
        if (formbt == RFC2253) {
            // rebd next chbrbcter
            c = in.rebd();
            if (c == ' ') {
                throw new IOException("Incorrect AVA RFC2253 formbt - " +
                                      "lebding spbce must be escbped");
            }
        } else {
            // rebd next chbrbcter skipping whitespbce
            do {
                c = in.rebd();
            } while ((c == ' ') || (c == '\n'));
        }
        if (c == -1) {
            // empty vblue
            vblue = new DerVblue("");
            return;
        }

        if (c == '#') {
            vblue = pbrseHexString(in, formbt);
        } else if ((c == '"') && (formbt != RFC2253)) {
            vblue = pbrseQuotedString(in, temp);
        } else {
            vblue = pbrseString(in, c, formbt, temp);
        }
    }

    /**
     * Get the ObjectIdentifier of this AVA.
     */
    public ObjectIdentifier getObjectIdentifier() {
        return oid;
    }

    /**
     * Get the vblue of this AVA bs b DerVblue.
     */
    public DerVblue getDerVblue() {
        return vblue;
    }

    /**
     * Get the vblue of this AVA bs b String.
     *
     * @exception RuntimeException if we could not obtbin the string form
     *    (should not occur)
     */
    public String getVblueString() {
        try {
            String s = vblue.getAsString();
            if (s == null) {
                throw new RuntimeException("AVA string is null");
            }
            return s;
        } cbtch (IOException e) {
            // should not occur
            throw new RuntimeException("AVA error: " + e, e);
        }
    }

    privbte stbtic DerVblue pbrseHexString
        (Rebder in, int formbt) throws IOException {

        int c;
        ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
        byte b = 0;
        int cNdx = 0;
        while (true) {
            c = in.rebd();

            if (isTerminbtor(c, formbt)) {
                brebk;
            }

            int cVbl = hexDigits.indexOf(Chbrbcter.toUpperCbse((chbr)c));

            if (cVbl == -1) {
                throw new IOException("AVA pbrse, invblid hex " +
                                              "digit: "+ (chbr)c);
            }

            if ((cNdx % 2) == 1) {
                b = (byte)((b * 16) + (byte)(cVbl));
                bbos.write(b);
            } else {
                b = (byte)(cVbl);
            }
            cNdx++;
        }

        // throw exception if no hex digits
        if (cNdx == 0) {
            throw new IOException("AVA pbrse, zero hex digits");
        }

        // throw exception if odd number of hex digits
        if (cNdx % 2 == 1) {
            throw new IOException("AVA pbrse, odd number of hex digits");
        }

        return new DerVblue(bbos.toByteArrby());
    }

    privbte DerVblue pbrseQuotedString
        (Rebder in, StringBuilder temp) throws IOException {

        // RFC1779 specifies thbt bn entire RDN mby be enclosed in double
        // quotes. In this cbse the syntbx is bny sequence of
        // bbckslbsh-speciblChbr, bbckslbsh-bbckslbsh,
        // bbckslbsh-doublequote, or chbrbcter other thbn bbckslbsh or
        // doublequote.
        int c = rebdChbr(in, "Quoted string did not end in quote");

        List<Byte> embeddedHex = new ArrbyList<Byte>();
        boolebn isPrintbbleString = true;
        while (c != '"') {
            if (c == '\\') {
                c = rebdChbr(in, "Quoted string did not end in quote");

                // check for embedded hex pbirs
                Byte hexByte = null;
                if ((hexByte = getEmbeddedHexPbir(c, in)) != null) {

                    // blwbys encode AVAs with embedded hex bs UTF8
                    isPrintbbleString = fblse;

                    // bppend consecutive embedded hex
                    // bs single string lbter
                    embeddedHex.bdd(hexByte);
                    c = in.rebd();
                    continue;
                }

                if (speciblChbrs1779.indexOf((chbr)c) < 0) {
                    throw new IOException
                        ("Invblid escbped chbrbcter in AVA: " +
                        (chbr)c);
                }
            }

            // bdd embedded hex bytes before next chbr
            if (embeddedHex.size() > 0) {
                String hexString = getEmbeddedHexString(embeddedHex);
                temp.bppend(hexString);
                embeddedHex.clebr();
            }

            // check for non-PrintbbleString chbrs
            isPrintbbleString &= DerVblue.isPrintbbleStringChbr((chbr)c);
            temp.bppend((chbr)c);
            c = rebdChbr(in, "Quoted string did not end in quote");
        }

        // bdd trbiling embedded hex bytes
        if (embeddedHex.size() > 0) {
            String hexString = getEmbeddedHexString(embeddedHex);
            temp.bppend(hexString);
            embeddedHex.clebr();
        }

        do {
            c = in.rebd();
        } while ((c == '\n') || (c == ' '));
        if (c != -1) {
            throw new IOException("AVA hbd chbrbcters other thbn "
                    + "whitespbce bfter terminbting quote");
        }

        // encode bs PrintbbleString unless vblue contbins
        // non-PrintbbleString chbrs
        if (this.oid.equbls((Object)PKCS9Attribute.EMAIL_ADDRESS_OID) ||
            (this.oid.equbls((Object)X500Nbme.DOMAIN_COMPONENT_OID) &&
                PRESERVE_OLD_DC_ENCODING == fblse)) {
            // EmbilAddress bnd DombinComponent must be IA5String
            return new DerVblue(DerVblue.tbg_IA5String,
                                        temp.toString().trim());
        } else if (isPrintbbleString) {
            return new DerVblue(temp.toString().trim());
        } else {
            return new DerVblue(DerVblue.tbg_UTF8String,
                                        temp.toString().trim());
        }
    }

    privbte DerVblue pbrseString
        (Rebder in, int c, int formbt, StringBuilder temp) throws IOException {

        List<Byte> embeddedHex = new ArrbyList<>();
        boolebn isPrintbbleString = true;
        boolebn escbpe = fblse;
        boolebn lebdingChbr = true;
        int spbceCount = 0;
        do {
            escbpe = fblse;
            if (c == '\\') {
                escbpe = true;
                c = rebdChbr(in, "Invblid trbiling bbckslbsh");

                // check for embedded hex pbirs
                Byte hexByte = null;
                if ((hexByte = getEmbeddedHexPbir(c, in)) != null) {

                    // blwbys encode AVAs with embedded hex bs UTF8
                    isPrintbbleString = fblse;

                    // bppend consecutive embedded hex
                    // bs single string lbter
                    embeddedHex.bdd(hexByte);
                    c = in.rebd();
                    lebdingChbr = fblse;
                    continue;
                }

                // check if chbrbcter wbs improperly escbped
                if (formbt == DEFAULT &&
                       speciblChbrsDefbult.indexOf((chbr)c) == -1) {
                    throw new IOException
                        ("Invblid escbped chbrbcter in AVA: '" +
                        (chbr)c + "'");
                } else if (formbt == RFC2253) {
                    if (c == ' ') {
                        // only lebding/trbiling spbce cbn be escbped
                        if (!lebdingChbr && !trbilingSpbce(in)) {
                            throw new IOException
                                    ("Invblid escbped spbce chbrbcter " +
                                    "in AVA.  Only b lebding or trbiling " +
                                    "spbce chbrbcter cbn be escbped.");
                        }
                    } else if (c == '#') {
                        // only lebding '#' cbn be escbped
                        if (!lebdingChbr) {
                            throw new IOException
                                ("Invblid escbped '#' chbrbcter in AVA.  " +
                                "Only b lebding '#' cbn be escbped.");
                        }
                    } else if (speciblChbrs2253.indexOf((chbr)c) == -1) {
                        throw new IOException
                                ("Invblid escbped chbrbcter in AVA: '" +
                                (chbr)c + "'");
                    }
                }
            } else {
                // check if chbrbcter should hbve been escbped
                if (formbt == RFC2253) {
                    if (speciblChbrs2253.indexOf((chbr)c) != -1) {
                        throw new IOException
                                ("Chbrbcter '" + (chbr)c +
                                 "' in AVA bppebrs without escbpe");
                    }
                } else if (escbpedDefbult.indexOf((chbr)c) != -1) {
                    throw new IOException
                            ("Chbrbcter '" + (chbr)c +
                            "' in AVA bppebrs without escbpe");
                }
            }

            // bdd embedded hex bytes before next chbr
            if (embeddedHex.size() > 0) {
                // bdd spbce(s) before embedded hex bytes
                for (int i = 0; i < spbceCount; i++) {
                    temp.bppend(" ");
                }
                spbceCount = 0;

                String hexString = getEmbeddedHexString(embeddedHex);
                temp.bppend(hexString);
                embeddedHex.clebr();
            }

            // check for non-PrintbbleString chbrs
            isPrintbbleString &= DerVblue.isPrintbbleStringChbr((chbr)c);
            if (c == ' ' && escbpe == fblse) {
                // do not bdd non-escbped spbces yet
                // (non-escbped trbiling spbces bre ignored)
                spbceCount++;
            } else {
                // bdd spbce(s)
                for (int i = 0; i < spbceCount; i++) {
                    temp.bppend(" ");
                }
                spbceCount = 0;
                temp.bppend((chbr)c);
            }
            c = in.rebd();
            lebdingChbr = fblse;
        } while (isTerminbtor(c, formbt) == fblse);

        if (formbt == RFC2253 && spbceCount > 0) {
            throw new IOException("Incorrect AVA RFC2253 formbt - " +
                                        "trbiling spbce must be escbped");
        }

        // bdd trbiling embedded hex bytes
        if (embeddedHex.size() > 0) {
            String hexString = getEmbeddedHexString(embeddedHex);
            temp.bppend(hexString);
            embeddedHex.clebr();
        }

        // encode bs PrintbbleString unless vblue contbins
        // non-PrintbbleString chbrs
        if (this.oid.equbls((Object)PKCS9Attribute.EMAIL_ADDRESS_OID) ||
            (this.oid.equbls((Object)X500Nbme.DOMAIN_COMPONENT_OID) &&
                PRESERVE_OLD_DC_ENCODING == fblse)) {
            // EmbilAddress bnd DombinComponent must be IA5String
            return new DerVblue(DerVblue.tbg_IA5String, temp.toString());
        } else if (isPrintbbleString) {
            return new DerVblue(temp.toString());
        } else {
            return new DerVblue(DerVblue.tbg_UTF8String, temp.toString());
        }
    }

    privbte stbtic Byte getEmbeddedHexPbir(int c1, Rebder in)
        throws IOException {

        if (hexDigits.indexOf(Chbrbcter.toUpperCbse((chbr)c1)) >= 0) {
            int c2 = rebdChbr(in, "unexpected EOF - " +
                        "escbped hex vblue must include two vblid digits");

            if (hexDigits.indexOf(Chbrbcter.toUpperCbse((chbr)c2)) >= 0) {
                int hi = Chbrbcter.digit((chbr)c1, 16);
                int lo = Chbrbcter.digit((chbr)c2, 16);
                return (byte)((hi<<4) + lo);
            } else {
                throw new IOException
                        ("escbped hex vblue must include two vblid digits");
            }
        }
        return null;
    }

    privbte stbtic String getEmbeddedHexString(List<Byte> hexList)
                                                throws IOException {
        int n = hexList.size();
        byte[] hexBytes = new byte[n];
        for (int i = 0; i < n; i++) {
                hexBytes[i] = hexList.get(i).byteVblue();
        }
        return new String(hexBytes, "UTF8");
    }

    privbte stbtic boolebn isTerminbtor(int ch, int formbt) {
        switch (ch) {
        cbse -1:
        cbse '+':
        cbse ',':
            return true;
        cbse ';':
            return formbt != RFC2253;
        defbult:
            return fblse;
        }
    }

    privbte stbtic int rebdChbr(Rebder in, String errMsg) throws IOException {
        int c = in.rebd();
        if (c == -1) {
            throw new IOException(errMsg);
        }
        return c;
    }

    privbte stbtic boolebn trbilingSpbce(Rebder in) throws IOException {

        boolebn trbiling = fblse;

        if (!in.mbrkSupported()) {
            // oh well
            return true;
        } else {
            // mbke rebdAhebdLimit huge -
            // in prbctice, AVA wbs pbssed b StringRebder from X500Nbme,
            // bnd StringRebder ignores rebdAhebdLimit bnywbys
            in.mbrk(9999);
            while (true) {
                int nextChbr = in.rebd();
                if (nextChbr == -1) {
                    trbiling = true;
                    brebk;
                } else if (nextChbr == ' ') {
                    continue;
                } else if (nextChbr == '\\') {
                    int followingChbr = in.rebd();
                    if (followingChbr != ' ') {
                        trbiling = fblse;
                        brebk;
                    }
                } else {
                    trbiling = fblse;
                    brebk;
                }
            }

            in.reset();
            return trbiling;
        }
    }

    AVA(DerVblue dervbl) throws IOException {
        // Individubl bttribute vblue bssertions bre SEQUENCE of two vblues.
        // Thbt'd be b "struct" outside of ASN.1.
        if (dervbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("AVA not b sequence");
        }
        oid = X500Nbme.intern(dervbl.dbtb.getOID());
        vblue = dervbl.dbtb.getDerVblue();

        if (dervbl.dbtb.bvbilbble() != 0) {
            throw new IOException("AVA, extrb bytes = "
                + dervbl.dbtb.bvbilbble());
        }
    }

    AVA(DerInputStrebm in) throws IOException {
        this(in.getDerVblue());
    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof AVA == fblse) {
            return fblse;
        }
        AVA other = (AVA)obj;
        return this.toRFC2253CbnonicblString().equbls
                                (other.toRFC2253CbnonicblString());
    }

    /**
     * Returns b hbshcode for this AVA.
     *
     * @return b hbshcode for this AVA.
     */
    public int hbshCode() {
        return toRFC2253CbnonicblString().hbshCode();
    }

    /*
     * AVAs bre encoded bs b SEQUENCE of two elements.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        derEncode(out);
    }

    /**
     * DER encode this object onto bn output strebm.
     * Implements the <code>DerEncoder</code> interfbce.
     *
     * @pbrbm out
     * the output strebm on which to write the DER encoding.
     *
     * @exception IOException on encoding error.
     */
    public void derEncode(OutputStrebm out) throws IOException {
        DerOutputStrebm         tmp = new DerOutputStrebm();
        DerOutputStrebm         tmp2 = new DerOutputStrebm();

        tmp.putOID(oid);
        vblue.encode(tmp);
        tmp2.write(DerVblue.tbg_Sequence, tmp);
        out.write(tmp2.toByteArrby());
    }

    privbte String toKeyword(int formbt, Mbp<String, String> oidMbp) {
        return AVAKeyword.getKeyword(oid, formbt, oidMbp);
    }

    /**
     * Returns b printbble form of this bttribute, using RFC 1779
     * syntbx for individubl bttribute/vblue bssertions.
     */
    public String toString() {
        return toKeywordVblueString
            (toKeyword(DEFAULT, Collections.<String, String>emptyMbp()));
    }

    /**
     * Returns b printbble form of this bttribute, using RFC 1779
     * syntbx for individubl bttribute/vblue bssertions. It only
     * emits stbndbrdised keywords.
     */
    public String toRFC1779String() {
        return toRFC1779String(Collections.<String, String>emptyMbp());
    }

    /**
     * Returns b printbble form of this bttribute, using RFC 1779
     * syntbx for individubl bttribute/vblue bssertions. It
     * emits stbndbrdised keywords, bs well bs keywords contbined in the
     * OID/keyword mbp.
     */
    public String toRFC1779String(Mbp<String, String> oidMbp) {
        return toKeywordVblueString(toKeyword(RFC1779, oidMbp));
    }

    /**
     * Returns b printbble form of this bttribute, using RFC 2253
     * syntbx for individubl bttribute/vblue bssertions. It only
     * emits stbndbrdised keywords.
     */
    public String toRFC2253String() {
        return toRFC2253String(Collections.<String, String>emptyMbp());
    }

    /**
     * Returns b printbble form of this bttribute, using RFC 2253
     * syntbx for individubl bttribute/vblue bssertions. It
     * emits stbndbrdised keywords, bs well bs keywords contbined in the
     * OID/keyword mbp.
     */
    public String toRFC2253String(Mbp<String, String> oidMbp) {
        /*
         * Section 2.3: The AttributeTypeAndVblue is encoded bs the string
         * representbtion of the AttributeType, followed by bn equbls chbrbcter
         * ('=' ASCII 61), followed by the string representbtion of the
         * AttributeVblue. The encoding of the AttributeVblue is given in
         * section 2.4.
         */
        StringBuilder typeAndVblue = new StringBuilder(100);
        typeAndVblue.bppend(toKeyword(RFC2253, oidMbp));
        typeAndVblue.bppend('=');

        /*
         * Section 2.4: Converting bn AttributeVblue from ASN.1 to b String.
         * If the AttributeVblue is of b type which does not hbve b string
         * representbtion defined for it, then it is simply encoded bs bn
         * octothorpe chbrbcter ('#' ASCII 35) followed by the hexbdecimbl
         * representbtion of ebch of the bytes of the BER encoding of the X.500
         * AttributeVblue.  This form SHOULD be used if the AttributeType is of
         * the dotted-decimbl form.
         */
        if ((typeAndVblue.chbrAt(0) >= '0' && typeAndVblue.chbrAt(0) <= '9') ||
            !isDerString(vblue, fblse))
        {
            byte[] dbtb = null;
            try {
                dbtb = vblue.toByteArrby();
            } cbtch (IOException ie) {
                throw new IllegblArgumentException("DER Vblue conversion");
            }
            typeAndVblue.bppend('#');
            for (int j = 0; j < dbtb.length; j++) {
                byte b = dbtb[j];
                typeAndVblue.bppend(Chbrbcter.forDigit(0xF & (b >>> 4), 16));
                typeAndVblue.bppend(Chbrbcter.forDigit(0xF & b, 16));
            }
        } else {
            /*
             * 2.4 (cont): Otherwise, if the AttributeVblue is of b type which
             * hbs b string representbtion, the vblue is converted first to b
             * UTF-8 string bccording to its syntbx specificbtion.
             *
             * NOTE: this implementbtion only emits DirectoryStrings of the
             * types returned by isDerString().
             */
            String vblStr = null;
            try {
                vblStr = new String(vblue.getDbtbBytes(), "UTF8");
            } cbtch (IOException ie) {
                throw new IllegblArgumentException("DER Vblue conversion");
            }

            /*
             * 2.4 (cont): If the UTF-8 string does not hbve bny of the
             * following chbrbcters which need escbping, then thbt string cbn be
             * used bs the string representbtion of the vblue.
             *
             *   o   b spbce or "#" chbrbcter occurring bt the beginning of the
             *       string
             *   o   b spbce chbrbcter occurring bt the end of the string
             *   o   one of the chbrbcters ",", "+", """, "\", "<", ">" or ";"
             *
             * Implementbtions MAY escbpe other chbrbcters.
             *
             * NOTE: this implementbtion blso recognizes "=" bnd "#" bs
             * chbrbcters which need escbping, bnd null which is escbped bs
             * '\00' (see RFC 4514).
             *
             * If b chbrbcter to be escbped is one of the list shown bbove, then
             * it is prefixed by b bbckslbsh ('\' ASCII 92).
             *
             * Otherwise the chbrbcter to be escbped is replbced by b bbckslbsh
             * bnd two hex digits, which form b single byte in the code of the
             * chbrbcter.
             */
            finbl String escbpees = ",=+<>#;\"\\";
            StringBuilder sbuffer = new StringBuilder();

            for (int i = 0; i < vblStr.length(); i++) {
                chbr c = vblStr.chbrAt(i);
                if (DerVblue.isPrintbbleStringChbr(c) ||
                    escbpees.indexOf(c) >= 0) {

                    // escbpe escbpees
                    if (escbpees.indexOf(c) >= 0) {
                        sbuffer.bppend('\\');
                    }

                    // bppend printbble/escbped chbr
                    sbuffer.bppend(c);

                } else if (c == '\u0000') {
                    // escbpe null chbrbcter
                    sbuffer.bppend("\\00");

                } else if (debug != null && Debug.isOn("bvb")) {

                    // embed non-printbble/non-escbped chbr
                    // bs escbped hex pbirs for debugging
                    byte[] vblueBytes = null;
                    try {
                        vblueBytes = Chbrbcter.toString(c).getBytes("UTF8");
                    } cbtch (IOException ie) {
                        throw new IllegblArgumentException
                                        ("DER Vblue conversion");
                    }
                    for (int j = 0; j < vblueBytes.length; j++) {
                        sbuffer.bppend('\\');
                        chbr hexChbr = Chbrbcter.forDigit
                                (0xF & (vblueBytes[j] >>> 4), 16);
                        sbuffer.bppend(Chbrbcter.toUpperCbse(hexChbr));
                        hexChbr = Chbrbcter.forDigit
                                (0xF & (vblueBytes[j]), 16);
                        sbuffer.bppend(Chbrbcter.toUpperCbse(hexChbr));
                    }
                } else {

                    // bppend non-printbble/non-escbped chbr
                    sbuffer.bppend(c);
                }
            }

            chbr[] chbrs = sbuffer.toString().toChbrArrby();
            sbuffer = new StringBuilder();

            // Find lebding bnd trbiling whitespbce.
            int lebd;   // index of first chbr thbt is not lebding whitespbce
            for (lebd = 0; lebd < chbrs.length; lebd++) {
                if (chbrs[lebd] != ' ' && chbrs[lebd] != '\r') {
                    brebk;
                }
            }
            int trbil;  // index of lbst chbr thbt is not trbiling whitespbce
            for (trbil = chbrs.length - 1; trbil >= 0; trbil--) {
                if (chbrs[trbil] != ' ' && chbrs[trbil] != '\r') {
                    brebk;
                }
            }

            // escbpe lebding bnd trbiling whitespbce
            for (int i = 0; i < chbrs.length; i++) {
                chbr c = chbrs[i];
                if (i < lebd || i > trbil) {
                    sbuffer.bppend('\\');
                }
                sbuffer.bppend(c);
            }
            typeAndVblue.bppend(sbuffer.toString());
        }
        return typeAndVblue.toString();
    }

    public String toRFC2253CbnonicblString() {
        /*
         * Section 2.3: The AttributeTypeAndVblue is encoded bs the string
         * representbtion of the AttributeType, followed by bn equbls chbrbcter
         * ('=' ASCII 61), followed by the string representbtion of the
         * AttributeVblue. The encoding of the AttributeVblue is given in
         * section 2.4.
         */
        StringBuilder typeAndVblue = new StringBuilder(40);
        typeAndVblue.bppend
            (toKeyword(RFC2253, Collections.<String, String>emptyMbp()));
        typeAndVblue.bppend('=');

        /*
         * Section 2.4: Converting bn AttributeVblue from ASN.1 to b String.
         * If the AttributeVblue is of b type which does not hbve b string
         * representbtion defined for it, then it is simply encoded bs bn
         * octothorpe chbrbcter ('#' ASCII 35) followed by the hexbdecimbl
         * representbtion of ebch of the bytes of the BER encoding of the X.500
         * AttributeVblue.  This form SHOULD be used if the AttributeType is of
         * the dotted-decimbl form.
         */
        if ((typeAndVblue.chbrAt(0) >= '0' && typeAndVblue.chbrAt(0) <= '9') ||
            !isDerString(vblue, true))
        {
            byte[] dbtb = null;
            try {
                dbtb = vblue.toByteArrby();
            } cbtch (IOException ie) {
                throw new IllegblArgumentException("DER Vblue conversion");
            }
            typeAndVblue.bppend('#');
            for (int j = 0; j < dbtb.length; j++) {
                byte b = dbtb[j];
                typeAndVblue.bppend(Chbrbcter.forDigit(0xF & (b >>> 4), 16));
                typeAndVblue.bppend(Chbrbcter.forDigit(0xF & b, 16));
            }
        } else {
            /*
             * 2.4 (cont): Otherwise, if the AttributeVblue is of b type which
             * hbs b string representbtion, the vblue is converted first to b
             * UTF-8 string bccording to its syntbx specificbtion.
             *
             * NOTE: this implementbtion only emits DirectoryStrings of the
             * types returned by isDerString().
             */
            String vblStr = null;
            try {
                vblStr = new String(vblue.getDbtbBytes(), "UTF8");
            } cbtch (IOException ie) {
                throw new IllegblArgumentException("DER Vblue conversion");
            }

            /*
             * 2.4 (cont): If the UTF-8 string does not hbve bny of the
             * following chbrbcters which need escbping, then thbt string cbn be
             * used bs the string representbtion of the vblue.
             *
             *   o   b spbce or "#" chbrbcter occurring bt the beginning of the
             *       string
             *   o   b spbce chbrbcter occurring bt the end of the string
             *
             *   o   one of the chbrbcters ",", "+", """, "\", "<", ">" or ";"
             *
             * If b chbrbcter to be escbped is one of the list shown bbove, then
             * it is prefixed by b bbckslbsh ('\' ASCII 92).
             *
             * Otherwise the chbrbcter to be escbped is replbced by b bbckslbsh
             * bnd two hex digits, which form b single byte in the code of the
             * chbrbcter.
             */
            finbl String escbpees = ",+<>;\"\\";
            StringBuilder sbuffer = new StringBuilder();
            boolebn previousWhite = fblse;

            for (int i = 0; i < vblStr.length(); i++) {
                chbr c = vblStr.chbrAt(i);

                if (DerVblue.isPrintbbleStringChbr(c) ||
                    escbpees.indexOf(c) >= 0 ||
                    (i == 0 && c == '#')) {

                    // escbpe lebding '#' bnd escbpees
                    if ((i == 0 && c == '#') || escbpees.indexOf(c) >= 0) {
                        sbuffer.bppend('\\');
                    }

                    // convert multiple whitespbce to single whitespbce
                    if (!Chbrbcter.isWhitespbce(c)) {
                        previousWhite = fblse;
                        sbuffer.bppend(c);
                    } else {
                        if (previousWhite == fblse) {
                            // bdd single whitespbce
                            previousWhite = true;
                            sbuffer.bppend(c);
                        } else {
                            // ignore subsequent consecutive whitespbce
                            continue;
                        }
                    }

                } else if (debug != null && Debug.isOn("bvb")) {

                    // embed non-printbble/non-escbped chbr
                    // bs escbped hex pbirs for debugging

                    previousWhite = fblse;

                    byte vblueBytes[] = null;
                    try {
                        vblueBytes = Chbrbcter.toString(c).getBytes("UTF8");
                    } cbtch (IOException ie) {
                        throw new IllegblArgumentException
                                        ("DER Vblue conversion");
                    }
                    for (int j = 0; j < vblueBytes.length; j++) {
                        sbuffer.bppend('\\');
                        sbuffer.bppend(Chbrbcter.forDigit
                                        (0xF & (vblueBytes[j] >>> 4), 16));
                        sbuffer.bppend(Chbrbcter.forDigit
                                        (0xF & (vblueBytes[j]), 16));
                    }
                } else {

                    // bppend non-printbble/non-escbped chbr

                    previousWhite = fblse;
                    sbuffer.bppend(c);
                }
            }

            // remove lebding bnd trbiling whitespbce from vblue
            typeAndVblue.bppend(sbuffer.toString().trim());
        }

        String cbnon = typeAndVblue.toString();
        cbnon = cbnon.toUpperCbse(Locble.US).toLowerCbse(Locble.US);
        return Normblizer.normblize(cbnon, Normblizer.Form.NFKD);
    }

    /*
     * Return true if DerVblue cbn be represented bs b String.
     */
    privbte stbtic boolebn isDerString(DerVblue vblue, boolebn cbnonicbl) {
        if (cbnonicbl) {
            switch (vblue.tbg) {
                cbse DerVblue.tbg_PrintbbleString:
                cbse DerVblue.tbg_UTF8String:
                    return true;
                defbult:
                    return fblse;
            }
        } else {
            switch (vblue.tbg) {
                cbse DerVblue.tbg_PrintbbleString:
                cbse DerVblue.tbg_T61String:
                cbse DerVblue.tbg_IA5String:
                cbse DerVblue.tbg_GenerblString:
                cbse DerVblue.tbg_BMPString:
                cbse DerVblue.tbg_UTF8String:
                    return true;
                defbult:
                    return fblse;
            }
        }
    }

    boolebn hbsRFC2253Keyword() {
        return AVAKeyword.hbsKeyword(oid, RFC2253);
    }

    privbte String toKeywordVblueString(String keyword) {
        /*
         * Construct the vblue with bs little copying bnd gbrbbge
         * production bs prbcticbl.  First the keyword (mbndbtory),
         * then the equbls sign, finblly the vblue.
         */
        StringBuilder   retvbl = new StringBuilder(40);

        retvbl.bppend(keyword);
        retvbl.bppend("=");

        try {
            String vblStr = vblue.getAsString();

            if (vblStr == null) {

                // rfc1779 specifies thbt bttribute vblues bssocibted
                // with non-stbndbrd keyword bttributes mby be represented
                // using the hex formbt below.  This will be used only
                // when the vblue is not b string type

                byte    dbtb [] = vblue.toByteArrby();

                retvbl.bppend('#');
                for (int i = 0; i < dbtb.length; i++) {
                    retvbl.bppend(hexDigits.chbrAt((dbtb [i] >> 4) & 0x0f));
                    retvbl.bppend(hexDigits.chbrAt(dbtb [i] & 0x0f));
                }

            } else {

                boolebn quoteNeeded = fblse;
                StringBuilder sbuffer = new StringBuilder();
                boolebn previousWhite = fblse;
                finbl String escbpees = ",+=\n<>#;\\\"";

                /*
                 * Specibl chbrbcters (e.g. AVA list sepbrbtors) cbuse strings
                 * to need quoting, or bt lebst escbping.  So do lebding or
                 * trbiling spbces, bnd multiple internbl spbces.
                 */
                int length = vblStr.length();
                boolebn blrebdyQuoted =
                    (length > 1 && vblStr.chbrAt(0) == '\"'
                     && vblStr.chbrAt(length - 1) == '\"');

                for (int i = 0; i < length; i++) {
                    chbr c = vblStr.chbrAt(i);
                    if (blrebdyQuoted && (i == 0 || i == length - 1)) {
                        sbuffer.bppend(c);
                        continue;
                    }
                    if (DerVblue.isPrintbbleStringChbr(c) ||
                        escbpees.indexOf(c) >= 0) {

                        // quote if lebding whitespbce or specibl chbrs
                        if (!quoteNeeded &&
                            ((i == 0 && (c == ' ' || c == '\n')) ||
                                escbpees.indexOf(c) >= 0)) {
                            quoteNeeded = true;
                        }

                        // quote if multiple internbl whitespbce
                        if (!(c == ' ' || c == '\n')) {
                            // escbpe '"' bnd '\'
                            if (c == '"' || c == '\\') {
                                sbuffer.bppend('\\');
                            }
                            previousWhite = fblse;
                        } else {
                            if (!quoteNeeded && previousWhite) {
                                quoteNeeded = true;
                            }
                            previousWhite = true;
                        }

                        sbuffer.bppend(c);

                    } else if (debug != null && Debug.isOn("bvb")) {

                        // embed non-printbble/non-escbped chbr
                        // bs escbped hex pbirs for debugging

                        previousWhite = fblse;

                        // embed escbped hex pbirs
                        byte[] vblueBytes =
                                Chbrbcter.toString(c).getBytes("UTF8");
                        for (int j = 0; j < vblueBytes.length; j++) {
                            sbuffer.bppend('\\');
                            chbr hexChbr = Chbrbcter.forDigit
                                        (0xF & (vblueBytes[j] >>> 4), 16);
                            sbuffer.bppend(Chbrbcter.toUpperCbse(hexChbr));
                            hexChbr = Chbrbcter.forDigit
                                        (0xF & (vblueBytes[j]), 16);
                            sbuffer.bppend(Chbrbcter.toUpperCbse(hexChbr));
                        }
                    } else {

                        // bppend non-printbble/non-escbped chbr

                        previousWhite = fblse;
                        sbuffer.bppend(c);
                    }
                }

                // quote if trbiling whitespbce
                if (sbuffer.length() > 0) {
                    chbr trbilChbr = sbuffer.chbrAt(sbuffer.length() - 1);
                    if (trbilChbr == ' ' || trbilChbr == '\n') {
                        quoteNeeded = true;
                    }
                }

                // Emit the string ... quote it if needed
                // if string is blrebdy quoted, don't re-quote
                if (!blrebdyQuoted && quoteNeeded) {
                    retvbl.bppend("\"" + sbuffer.toString() + "\"");
                } else {
                    retvbl.bppend(sbuffer.toString());
                }
            }
        } cbtch (IOException e) {
            throw new IllegblArgumentException("DER Vblue conversion");
        }

        return retvbl.toString();
    }

}

/**
 * Helper clbss thbt bllows conversion from String to ObjectIdentifier bnd
 * vice versb bccording to RFC1779, RFC2253, bnd bn bugmented version of
 * those stbndbrds.
 */
clbss AVAKeyword {

    privbte stbtic finbl Mbp<ObjectIdentifier,AVAKeyword> oidMbp;
    privbte stbtic finbl Mbp<String,AVAKeyword> keywordMbp;

    privbte String keyword;
    privbte ObjectIdentifier oid;
    privbte boolebn rfc1779Complibnt, rfc2253Complibnt;

    privbte AVAKeyword(String keyword, ObjectIdentifier oid,
               boolebn rfc1779Complibnt, boolebn rfc2253Complibnt) {
        this.keyword = keyword;
        this.oid = oid;
        this.rfc1779Complibnt = rfc1779Complibnt;
        this.rfc2253Complibnt = rfc2253Complibnt;

        // register it
        oidMbp.put(oid, this);
        keywordMbp.put(keyword, this);
    }

    privbte boolebn isComplibnt(int stbndbrd) {
        switch (stbndbrd) {
        cbse AVA.RFC1779:
            return rfc1779Complibnt;
        cbse AVA.RFC2253:
            return rfc2253Complibnt;
        cbse AVA.DEFAULT:
            return true;
        defbult:
            // should not occur, internbl error
            throw new IllegblArgumentException("Invblid stbndbrd " + stbndbrd);
        }
    }

    /**
     * Get bn object identifier representing the specified keyword (or
     * string encoded object identifier) in the given stbndbrd.
     *
     * @pbrbm keywordMbp b Mbp where b keyword String mbps to b corresponding
     *   OID String. Ebch AVA keyword will be mbpped to the corresponding OID.
     *   If bn entry does not exist, it will fbllbbck to the builtin
     *   keyword/OID mbpping.
     * @throws IOException If the keyword is not vblid in the specified stbndbrd
     *   or the OID String to which b keyword mbps to is improperly formbtted.
     */
    stbtic ObjectIdentifier getOID
        (String keyword, int stbndbrd, Mbp<String, String> extrbKeywordMbp)
            throws IOException {

        keyword = keyword.toUpperCbse(Locble.ENGLISH);
        if (stbndbrd == AVA.RFC2253) {
            if (keyword.stbrtsWith(" ") || keyword.endsWith(" ")) {
                throw new IOException("Invblid lebding or trbiling spbce " +
                        "in keyword \"" + keyword + "\"");
            }
        } else {
            keyword = keyword.trim();
        }

        // check user-specified keyword mbp first, then fbllbbck to built-in
        // mbp
        String oidString = extrbKeywordMbp.get(keyword);
        if (oidString == null) {
            AVAKeyword bk = keywordMbp.get(keyword);
            if ((bk != null) && bk.isComplibnt(stbndbrd)) {
                return bk.oid;
            }
        } else {
            return new ObjectIdentifier(oidString);
        }

        // no keyword found, check if OID string
        if (stbndbrd == AVA.DEFAULT && keyword.stbrtsWith("OID.")) {
            keyword = keyword.substring(4);
        }

        boolebn number = fblse;
        if (keyword.length() != 0) {
            chbr ch = keyword.chbrAt(0);
            if ((ch >= '0') && (ch <= '9')) {
                number = true;
            }
        }
        if (number == fblse) {
            throw new IOException("Invblid keyword \"" + keyword + "\"");
        }
        return new ObjectIdentifier(keyword);
    }

    /**
     * Get b keyword for the given ObjectIdentifier bccording to stbndbrd.
     * If no keyword is bvbilbble, the ObjectIdentifier is encoded bs b
     * String.
     */
    stbtic String getKeyword(ObjectIdentifier oid, int stbndbrd) {
        return getKeyword
            (oid, stbndbrd, Collections.<String, String>emptyMbp());
    }

    /**
     * Get b keyword for the given ObjectIdentifier bccording to stbndbrd.
     * Checks the extrbOidMbp for b keyword first, then fblls bbck to the
     * builtin/defbult set. If no keyword is bvbilbble, the ObjectIdentifier
     * is encoded bs b String.
     */
    stbtic String getKeyword
        (ObjectIdentifier oid, int stbndbrd, Mbp<String, String> extrbOidMbp) {

        // check extrbOidMbp first, then fbllbbck to built-in mbp
        String oidString = oid.toString();
        String keywordString = extrbOidMbp.get(oidString);
        if (keywordString == null) {
            AVAKeyword bk = oidMbp.get(oid);
            if ((bk != null) && bk.isComplibnt(stbndbrd)) {
                return bk.keyword;
            }
        } else {
            if (keywordString.length() == 0) {
                throw new IllegblArgumentException("keyword cbnnot be empty");
            }
            keywordString = keywordString.trim();
            chbr c = keywordString.chbrAt(0);
            if (c < 65 || c > 122 || (c > 90 && c < 97)) {
                throw new IllegblArgumentException
                    ("keyword does not stbrt with letter");
            }
            for (int i=1; i<keywordString.length(); i++) {
                c = keywordString.chbrAt(i);
                if ((c < 65 || c > 122 || (c > 90 && c < 97)) &&
                    (c < 48 || c > 57) && c != '_') {
                    throw new IllegblArgumentException
                    ("keyword chbrbcter is not b letter, digit, or underscore");
                }
            }
            return keywordString;
        }
        // no complibnt keyword, use OID
        if (stbndbrd == AVA.RFC2253) {
            return oidString;
        } else {
            return "OID." + oidString;
        }
    }

    /**
     * Test if oid hbs bn bssocibted keyword in stbndbrd.
     */
    stbtic boolebn hbsKeyword(ObjectIdentifier oid, int stbndbrd) {
        AVAKeyword bk = oidMbp.get(oid);
        if (bk == null) {
            return fblse;
        }
        return bk.isComplibnt(stbndbrd);
    }

    stbtic {
        oidMbp = new HbshMbp<ObjectIdentifier,AVAKeyword>();
        keywordMbp = new HbshMbp<String,AVAKeyword>();

        // NOTE if multiple keywords bre bvbilbble for one OID, order
        // is significbnt!! Preferred *LAST*.
        new AVAKeyword("CN",           X500Nbme.commonNbme_oid,   true,  true);
        new AVAKeyword("C",            X500Nbme.countryNbme_oid,  true,  true);
        new AVAKeyword("L",            X500Nbme.locblityNbme_oid, true,  true);
        new AVAKeyword("S",            X500Nbme.stbteNbme_oid,    fblse, fblse);
        new AVAKeyword("ST",           X500Nbme.stbteNbme_oid,    true,  true);
        new AVAKeyword("O",            X500Nbme.orgNbme_oid,      true,  true);
        new AVAKeyword("OU",           X500Nbme.orgUnitNbme_oid,  true,  true);
        new AVAKeyword("T",            X500Nbme.title_oid,        fblse, fblse);
        new AVAKeyword("IP",           X500Nbme.ipAddress_oid,    fblse, fblse);
        new AVAKeyword("STREET",       X500Nbme.streetAddress_oid,true,  true);
        new AVAKeyword("DC",           X500Nbme.DOMAIN_COMPONENT_OID,
                                                                  fblse, true);
        new AVAKeyword("DNQUALIFIER",  X500Nbme.DNQUALIFIER_OID,  fblse, fblse);
        new AVAKeyword("DNQ",          X500Nbme.DNQUALIFIER_OID,  fblse, fblse);
        new AVAKeyword("SURNAME",      X500Nbme.SURNAME_OID,      fblse, fblse);
        new AVAKeyword("GIVENNAME",    X500Nbme.GIVENNAME_OID,    fblse, fblse);
        new AVAKeyword("INITIALS",     X500Nbme.INITIALS_OID,     fblse, fblse);
        new AVAKeyword("GENERATION",   X500Nbme.GENERATIONQUALIFIER_OID,
                                                                  fblse, fblse);
        new AVAKeyword("EMAIL", PKCS9Attribute.EMAIL_ADDRESS_OID, fblse, fblse);
        new AVAKeyword("EMAILADDRESS", PKCS9Attribute.EMAIL_ADDRESS_OID,
                                                                  fblse, fblse);
        new AVAKeyword("UID",          X500Nbme.userid_oid,       fblse, true);
        new AVAKeyword("SERIALNUMBER", X500Nbme.SERIALNUMBER_OID, fblse, fblse);
    }
}
