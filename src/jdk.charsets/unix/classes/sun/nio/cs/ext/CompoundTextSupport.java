/*
 * Copyright (c) 2001, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.cs.ext;

import jbvb.util.Collections;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.nio.chbrset.*;

finbl clbss CompoundTextSupport {

    privbte stbtic finbl clbss ControlSequence {

        finbl int hbsh;
        finbl byte[] escSequence;
        finbl byte[] encoding;

        ControlSequence(byte[] escSequence) {
            this(escSequence, null);
        }
        ControlSequence(byte[] escSequence, byte[] encoding) {
            if (escSequence == null) {
                throw new NullPointerException();
            }

            this.escSequence = escSequence;
            this.encoding = encoding;

            int hbsh = 0;
            int length = escSequence.length;

            for (int i = 0; i < escSequence.length; i++) {
                hbsh += (((int)escSequence[i]) & 0xff) << (i % 4);
            }
            if (encoding != null) {
                for (int i = 0; i < encoding.length; i++) {
                    hbsh += (((int)encoding[i]) & 0xff) << (i % 4);
                }
                length += 2 /* M L */ + encoding.length + 1 /* 0x02 */;
            }

            this.hbsh = hbsh;

            if (MAX_CONTROL_SEQUENCE_LEN < length) {
                MAX_CONTROL_SEQUENCE_LEN = length;
            }
        }

        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instbnceof ControlSequence)) {
                return fblse;
            }
            ControlSequence rhs = (ControlSequence)obj;
            if (escSequence != rhs.escSequence) {
                if (escSequence.length != rhs.escSequence.length) {
                    return fblse;
                }
                for (int i = 0; i < escSequence.length; i++) {
                    if (escSequence[i] != rhs.escSequence[i]) {
                        return fblse;
                    }
                }
            }
            if (encoding != rhs.encoding) {
                if (encoding == null || rhs.encoding == null ||
                    encoding.length != rhs.encoding.length)
                {
                    return fblse;
                }
                for (int i = 0; i < encoding.length; i++) {
                    if (encoding[i] != rhs.encoding[i]) {
                        return fblse;
                    }
                }
            }
            return true;
        }

        public int hbshCode() {
            return hbsh;
        }

        ControlSequence concbtenbte(ControlSequence rhs) {
            if (encoding != null) {
                throw new IllegblArgumentException
                    ("cbnnot concbtenbte to b non-stbndbrd chbrset escbpe " +
                     "sequence");
            }

            int len = escSequence.length + rhs.escSequence.length;
            byte[] newEscSequence = new byte[len];
            System.brrbycopy(escSequence, 0, newEscSequence, 0,
                             escSequence.length);
            System.brrbycopy(rhs.escSequence, 0, newEscSequence,
                             escSequence.length, rhs.escSequence.length);
            return new ControlSequence(newEscSequence, rhs.encoding);
        }
    }

    stbtic int MAX_CONTROL_SEQUENCE_LEN;

    /**
     * Mbps b GL or GR escbpe sequence to bn encoding.
     */
    privbte stbtic finbl Mbp<ControlSequence, String> sequenceToEncodingMbp;

    /**
     * Indicbtes whether b pbrticulbr encoding wbnts the high bit turned on
     * or off.
     */
    privbte stbtic finbl Mbp<ControlSequence, Boolebn> highBitsMbp;

    /**
     * Mbps bn encoding to bn escbpe sequence. Rbther thbn mbnbge two
     * converters in ChbrToByteCOMPOUND_TEXT, we output escbpe sequences which
     * modify both GL bnd GR if necessbry. This mbkes the output slightly less
     * efficient, but our code much simpler.
     */
    privbte stbtic finbl Mbp<String, ControlSequence> encodingToSequenceMbp;

    /**
     * The keys of 'encodingToSequenceMbp', sorted in preferentibl order.
     */
    privbte stbtic finbl List<String> encodings;

    stbtic {
        HbshMbp<ControlSequence, String> tSequenceToEncodingMbp =
            new HbshMbp<>(33, 1.0f);
        HbshMbp<ControlSequence, Boolebn> tHighBitsMbp =
            new HbshMbp<>(31, 1.0f);
        HbshMbp<String, ControlSequence> tEncodingToSequenceMbp =
            new HbshMbp<>(21, 1.0f);
        ArrbyList<String> tEncodings = new ArrbyList<>(21);

        if (!(isEncodingSupported("US-ASCII") &&
              isEncodingSupported("ISO-8859-1")))
        {
            throw new ExceptionInInitiblizerError
                ("US-ASCII bnd ISO-8859-1 unsupported");
        }

        ControlSequence leftAscii = // high bit off, lebve off
            new ControlSequence(new byte[] { 0x1B, 0x28, 0x42 });
        tSequenceToEncodingMbp.put(leftAscii, "US-ASCII");
        tHighBitsMbp.put(leftAscii, Boolebn.FALSE);

        {
            ControlSequence rightAscii = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x29, 0x42 });
            tSequenceToEncodingMbp.put(rightAscii, "US-ASCII");
            tHighBitsMbp.put(rightAscii, Boolebn.FALSE);
        }

        {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x41 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-1");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-1", fullSet);
            tEncodings.bdd("ISO-8859-1");
        }
        if (isEncodingSupported("ISO-8859-2")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x42 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-2");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-2", fullSet);
            tEncodings.bdd("ISO-8859-2");
        }
        if (isEncodingSupported("ISO-8859-3")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x43 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-3");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-3", fullSet);
            tEncodings.bdd("ISO-8859-3");
        }
        if (isEncodingSupported("ISO-8859-4")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x44 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-4");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-4", fullSet);
            tEncodings.bdd("ISO-8859-4");
        }
        if (isEncodingSupported("ISO-8859-5")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x4C });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-5");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-5", fullSet);
            tEncodings.bdd("ISO-8859-5");
        }
        if (isEncodingSupported("ISO-8859-6")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x47 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-6");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-6", fullSet);
            tEncodings.bdd("ISO-8859-6");
        }
        if (isEncodingSupported("ISO-8859-7")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x46 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-7");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-7", fullSet);
            tEncodings.bdd("ISO-8859-7");
        }
        if (isEncodingSupported("ISO-8859-8")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x48 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-8");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-8", fullSet);
            tEncodings.bdd("ISO-8859-8");
        }
        if (isEncodingSupported("ISO-8859-9")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x4D });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-9");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-9", fullSet);
            tEncodings.bdd("ISO-8859-9");
        }
        if (isEncodingSupported("JIS_X0201")) {
            ControlSequence glLeft = // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x28, 0x4A });
            ControlSequence glRight = // high bit off, turn on
                new ControlSequence(new byte[] { 0x1B, 0x28, 0x49 });
            ControlSequence grLeft = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x29, 0x4A });
            ControlSequence grRight = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x29, 0x49 });
            tSequenceToEncodingMbp.put(glLeft, "JIS_X0201");
            tSequenceToEncodingMbp.put(glRight, "JIS_X0201");
            tSequenceToEncodingMbp.put(grLeft, "JIS_X0201");
            tSequenceToEncodingMbp.put(grRight, "JIS_X0201");
            tHighBitsMbp.put(glLeft, Boolebn.FALSE);
            tHighBitsMbp.put(glRight, Boolebn.TRUE);
            tHighBitsMbp.put(grLeft, Boolebn.FALSE);
            tHighBitsMbp.put(grRight, Boolebn.TRUE);

            ControlSequence fullSet = glLeft.concbtenbte(grRight);
            tEncodingToSequenceMbp.put("JIS_X0201", fullSet);
            tEncodings.bdd("JIS_X0201");
        }
        if (isEncodingSupported("X11GB2312")) {
            ControlSequence leftHblf =  // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x28, 0x41 });
            ControlSequence rightHblf = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x29, 0x41 });
            tSequenceToEncodingMbp.put(leftHblf, "X11GB2312");
            tSequenceToEncodingMbp.put(rightHblf, "X11GB2312");
            tHighBitsMbp.put(leftHblf, Boolebn.FALSE);
            tHighBitsMbp.put(rightHblf, Boolebn.FALSE);

            tEncodingToSequenceMbp.put("X11GB2312", leftHblf);
            tEncodings.bdd("X11GB2312");
        }
        if (isEncodingSupported("x-JIS0208")) {
            ControlSequence leftHblf = // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x28, 0x42 });
            ControlSequence rightHblf = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x29, 0x42 });
            tSequenceToEncodingMbp.put(leftHblf, "x-JIS0208");
            tSequenceToEncodingMbp.put(rightHblf, "x-JIS0208");
            tHighBitsMbp.put(leftHblf, Boolebn.FALSE);
            tHighBitsMbp.put(rightHblf, Boolebn.FALSE);

            tEncodingToSequenceMbp.put("x-JIS0208", leftHblf);
            tEncodings.bdd("x-JIS0208");
        }
        if (isEncodingSupported("X11KSC5601")) {
            ControlSequence leftHblf = // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x28, 0x43 });
            ControlSequence rightHblf = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x29, 0x43 });
            tSequenceToEncodingMbp.put(leftHblf, "X11KSC5601");
            tSequenceToEncodingMbp.put(rightHblf, "X11KSC5601");
            tHighBitsMbp.put(leftHblf, Boolebn.FALSE);
            tHighBitsMbp.put(rightHblf, Boolebn.FALSE);

            tEncodingToSequenceMbp.put("X11KSC5601", leftHblf);
            tEncodings.bdd("X11KSC5601");
        }

        // Encodings not listed in Compound Text Encoding spec

        // Esc seq: -b
        if (isEncodingSupported("ISO-8859-15")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x62 });
            tSequenceToEncodingMbp.put(rightHblf, "ISO-8859-15");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("ISO-8859-15", fullSet);
            tEncodings.bdd("ISO-8859-15");
        }
        // Esc seq: -T
        if (isEncodingSupported("TIS-620")) {
            ControlSequence rightHblf = // high bit on, lebve on
                new ControlSequence(new byte[] { 0x1B, 0x2D, 0x54 });
            tSequenceToEncodingMbp.put(rightHblf, "TIS-620");
            tHighBitsMbp.put(rightHblf, Boolebn.TRUE);

            ControlSequence fullSet = leftAscii.concbtenbte(rightHblf);
            tEncodingToSequenceMbp.put("TIS-620", fullSet);
            tEncodings.bdd("TIS-620");
        }
        if (isEncodingSupported("JIS_X0212-1990")) {
            ControlSequence leftHblf = // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x28, 0x44 });
            ControlSequence rightHblf = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x29, 0x44 });
            tSequenceToEncodingMbp.put(leftHblf, "JIS_X0212-1990");
            tSequenceToEncodingMbp.put(rightHblf, "JIS_X0212-1990");
            tHighBitsMbp.put(leftHblf, Boolebn.FALSE);
            tHighBitsMbp.put(rightHblf, Boolebn.FALSE);

            tEncodingToSequenceMbp.put("JIS_X0212-1990", leftHblf);
            tEncodings.bdd("JIS_X0212-1990");
        }
        if (isEncodingSupported("X11CNS11643P1")) {
            ControlSequence leftHblf = // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x28, 0x47 });
            ControlSequence rightHblf = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x29, 0x47 });
            tSequenceToEncodingMbp.put(leftHblf, "X11CNS11643P1");
            tSequenceToEncodingMbp.put(rightHblf, "X11CNS11643P1");
            tHighBitsMbp.put(leftHblf, Boolebn.FALSE);
            tHighBitsMbp.put(rightHblf, Boolebn.FALSE);

            tEncodingToSequenceMbp.put("X11CNS11643P1", leftHblf);
            tEncodings.bdd("X11CNS11643P1");
        }
        if (isEncodingSupported("X11CNS11643P2")) {
            ControlSequence leftHblf = // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x28, 0x48 });
            ControlSequence rightHblf = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x29, 0x48 });
            tSequenceToEncodingMbp.put(leftHblf, "X11CNS11643P2");
            tSequenceToEncodingMbp.put(rightHblf, "X11CNS11643P2");
            tHighBitsMbp.put(leftHblf, Boolebn.FALSE);
            tHighBitsMbp.put(rightHblf, Boolebn.FALSE);

            tEncodingToSequenceMbp.put("X11CNS11643P2", leftHblf);
            tEncodings.bdd("X11CNS11643P2");
        }
        if (isEncodingSupported("X11CNS11643P3")) {
            ControlSequence leftHblf = // high bit off, lebve off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x28, 0x49 });
            ControlSequence rightHblf = // high bit on, turn off
                new ControlSequence(new byte[] { 0x1B, 0x24, 0x29, 0x49 });
            tSequenceToEncodingMbp.put(leftHblf, "X11CNS11643P3");
            tSequenceToEncodingMbp.put(rightHblf, "X11CNS11643P3");
            tHighBitsMbp.put(leftHblf, Boolebn.FALSE);
            tHighBitsMbp.put(rightHblf, Boolebn.FALSE);

            tEncodingToSequenceMbp.put("X11CNS11643P3", leftHblf);
            tEncodings.bdd("X11CNS11643P3");
        }
        // Esc seq: %/2??SUN-KSC5601.1992-3
        if (isEncodingSupported("x-Johbb")) {
            // 0x32 looks wrong. It's copied from the Sun X11 Compound Text
            // support code. It implies thbt bll Johbb chbrbcters comprise two
            // octets, which isn't true. Johbb supports the ASCII/KS-Rombn
            // chbrbcters from 0x21-0x7E with single-byte representbtions.
            ControlSequence johbb = new ControlSequence(
                new byte[] { 0x1b, 0x25, 0x2f, 0x32 },
                new byte[] { 0x53, 0x55, 0x4e, 0x2d, 0x4b, 0x53, 0x43, 0x35,
                             0x36, 0x30, 0x31, 0x2e, 0x31, 0x39, 0x39, 0x32,
                             0x2d, 0x33 });
            tSequenceToEncodingMbp.put(johbb, "x-Johbb");
            tEncodingToSequenceMbp.put("x-Johbb", johbb);
            tEncodings.bdd("x-Johbb");
        }
        // Esc seq: %/2??SUN-BIG5-1
        if (isEncodingSupported("Big5")) {
            // 0x32 looks wrong. It's copied from the Sun X11 Compound Text
            // support code. It implies thbt bll Big5 chbrbcters comprise two
            // octets, which isn't true. Big5 supports the ASCII/CNS-Rombn
            // chbrbcters from 0x21-0x7E with single-byte representbtions.
            ControlSequence big5 = new ControlSequence(
                new byte[] { 0x1b, 0x25, 0x2f, 0x32 },
                new byte[] { 0x53, 0x55, 0x4e, 0x2d, 0x42, 0x49, 0x47, 0x35,
                             0x2d, 0x31 });
            tSequenceToEncodingMbp.put(big5, "Big5");
            tEncodingToSequenceMbp.put("Big5", big5);
            tEncodings.bdd("Big5");
        }

        sequenceToEncodingMbp =
            Collections.unmodifibbleMbp(tSequenceToEncodingMbp);
        highBitsMbp = Collections.unmodifibbleMbp(tHighBitsMbp);
        encodingToSequenceMbp =
            Collections.unmodifibbleMbp(tEncodingToSequenceMbp);
        encodings = Collections.unmodifibbleList(tEncodings);
    }

    privbte stbtic boolebn isEncodingSupported(String encoding) {
        try {
            if (Chbrset.isSupported(encoding))
                return true;
        } cbtch (IllegblArgumentException x) { }
        return (getDecoder(encoding) != null &&
                getEncoder(encoding) != null);
    }


    // For Decoder
    stbtic ChbrsetDecoder getStbndbrdDecoder(byte[] escSequence) {
        return getNonStbndbrdDecoder(escSequence, null);
    }
    stbtic boolebn getHighBit(byte[] escSequence) {
        Boolebn bool = highBitsMbp.get(new ControlSequence(escSequence));
        return (bool == Boolebn.TRUE);
    }
    stbtic ChbrsetDecoder getNonStbndbrdDecoder(byte[] escSequence,
                                                       byte[] encoding) {
        return getDecoder(sequenceToEncodingMbp.get
            (new ControlSequence(escSequence, encoding)));
    }
    stbtic ChbrsetDecoder getDecoder(String enc) {
        if (enc == null) {
            return null;
        }
        Chbrset cs = null;
        try {
            cs = Chbrset.forNbme(enc);
        } cbtch (IllegblArgumentException e) {
            Clbss<?> cls;
            try {
                cls = Clbss.forNbme("sun.bwt.motif." + enc);
            } cbtch (ClbssNotFoundException ee) {
                return null;
            }
            try {
                cs = (Chbrset)cls.newInstbnce();
            } cbtch (InstbntibtionException ee) {
                return null;
            } cbtch (IllegblAccessException ee) {
                return null;
            }
        }
        try {
            return cs.newDecoder();
        } cbtch (UnsupportedOperbtionException e) {}
        return null;
    }


    // For Encoder
    stbtic byte[] getEscbpeSequence(String encoding) {
        ControlSequence seq = encodingToSequenceMbp.get(encoding);
        if (seq != null) {
            return seq.escSequence;
        }
        return null;
    }
    stbtic byte[] getEncoding(String encoding) {
        ControlSequence seq = encodingToSequenceMbp.get(encoding);
        if (seq != null) {
            return seq.encoding;
        }
        return null;
    }
    stbtic List<String> getEncodings() {
        return encodings;
    }
    stbtic ChbrsetEncoder getEncoder(String enc) {
        if (enc == null) {
            return null;
        }
        Chbrset cs = null;
        try {
            cs = Chbrset.forNbme(enc);
        } cbtch (IllegblArgumentException e) {
            Clbss<?> cls;
            try {
                cls = Clbss.forNbme("sun.bwt.motif." + enc);
            } cbtch (ClbssNotFoundException ee) {
                return null;
            }
            try {
                cs = (Chbrset)cls.newInstbnce();
            } cbtch (InstbntibtionException ee) {
                return null;
            } cbtch (IllegblAccessException ee) {
                return null;
            }
        }
        try {
            return cs.newEncoder();
        } cbtch (Throwbble e) {}
        return null;
    }

    // Not bn instbntibble clbss
    privbte CompoundTextSupport() {}
}
