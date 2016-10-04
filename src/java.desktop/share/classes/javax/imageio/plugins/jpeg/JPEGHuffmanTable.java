/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.plugins.jpeg;

import jbvb.util.Arrbys;

/**
 * A clbss encbpsulbting b single JPEG Huffmbn tbble.
 * Fields bre provided for the "stbndbrd" tbbles tbken
 * from Annex K of the JPEG specificbtion.
 * These bre the tbbles used bs defbults.
 * <p>
 * For more informbtion bbout the operbtion of the stbndbrd JPEG plug-in,
 * see the <A HREF="../../metbdbtb/doc-files/jpeg_metbdbtb.html">JPEG
 * metbdbtb formbt specificbtion bnd usbge notes</A>
 */

public clbss JPEGHuffmbnTbble {

    /* The dbtb for the publicblly defined tbbles, bs specified in ITU T.81
     * JPEG specificbtion section K3.3 bnd used in the IJG librbry.
     */
    privbte stbtic finbl short[] StdDCLuminbnceLengths = {
        0x00, 0x01, 0x05, 0x01, 0x01, 0x01, 0x01, 0x01,
        0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    };

    privbte stbtic finbl short[] StdDCLuminbnceVblues = {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0b, 0x0b,
    };

    privbte stbtic finbl short[] StdDCChrominbnceLengths = {
        0x00, 0x03, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
        0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
    };

    privbte stbtic finbl short[] StdDCChrominbnceVblues = {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0b, 0x0b,
    };

    privbte stbtic finbl short[] StdACLuminbnceLengths = {
        0x00, 0x02, 0x01, 0x03, 0x03, 0x02, 0x04, 0x03,
        0x05, 0x05, 0x04, 0x04, 0x00, 0x00, 0x01, 0x7d,
    };

    privbte stbtic finbl short[] StdACLuminbnceVblues = {
        0x01, 0x02, 0x03, 0x00, 0x04, 0x11, 0x05, 0x12,
        0x21, 0x31, 0x41, 0x06, 0x13, 0x51, 0x61, 0x07,
        0x22, 0x71, 0x14, 0x32, 0x81, 0x91, 0xb1, 0x08,
        0x23, 0x42, 0xb1, 0xc1, 0x15, 0x52, 0xd1, 0xf0,
        0x24, 0x33, 0x62, 0x72, 0x82, 0x09, 0x0b, 0x16,
        0x17, 0x18, 0x19, 0x1b, 0x25, 0x26, 0x27, 0x28,
        0x29, 0x2b, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39,
        0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49,
        0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59,
        0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69,
        0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79,
        0x7b, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89,
        0x8b, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98,
        0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7,
        0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6,
        0xb7, 0xb8, 0xb9, 0xbb, 0xc2, 0xc3, 0xc4, 0xc5,
        0xc6, 0xc7, 0xc8, 0xc9, 0xcb, 0xd2, 0xd3, 0xd4,
        0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb, 0xe1, 0xe2,
        0xe3, 0xe4, 0xe5, 0xe6, 0xe7, 0xe8, 0xe9, 0xeb,
        0xf1, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
        0xf9, 0xfb,
    };

    privbte stbtic finbl short[] StdACChrominbnceLengths = {
        0x00, 0x02, 0x01, 0x02, 0x04, 0x04, 0x03, 0x04,
        0x07, 0x05, 0x04, 0x04, 0x00, 0x01, 0x02, 0x77,
    };

    privbte stbtic finbl short[] StdACChrominbnceVblues = {
        0x00, 0x01, 0x02, 0x03, 0x11, 0x04, 0x05, 0x21,
        0x31, 0x06, 0x12, 0x41, 0x51, 0x07, 0x61, 0x71,
        0x13, 0x22, 0x32, 0x81, 0x08, 0x14, 0x42, 0x91,
        0xb1, 0xb1, 0xc1, 0x09, 0x23, 0x33, 0x52, 0xf0,
        0x15, 0x62, 0x72, 0xd1, 0x0b, 0x16, 0x24, 0x34,
        0xe1, 0x25, 0xf1, 0x17, 0x18, 0x19, 0x1b, 0x26,
        0x27, 0x28, 0x29, 0x2b, 0x35, 0x36, 0x37, 0x38,
        0x39, 0x3b, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48,
        0x49, 0x4b, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58,
        0x59, 0x5b, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68,
        0x69, 0x6b, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78,
        0x79, 0x7b, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87,
        0x88, 0x89, 0x8b, 0x92, 0x93, 0x94, 0x95, 0x96,
        0x97, 0x98, 0x99, 0x9b, 0xb2, 0xb3, 0xb4, 0xb5,
        0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xb2, 0xb3, 0xb4,
        0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xbb, 0xc2, 0xc3,
        0xc4, 0xc5, 0xc6, 0xc7, 0xc8, 0xc9, 0xcb, 0xd2,
        0xd3, 0xd4, 0xd5, 0xd6, 0xd7, 0xd8, 0xd9, 0xdb,
        0xe2, 0xe3, 0xe4, 0xe5, 0xe6, 0xe7, 0xe8, 0xe9,
        0xeb, 0xf2, 0xf3, 0xf4, 0xf5, 0xf6, 0xf7, 0xf8,
        0xf9, 0xfb,
    };

    /**
     * The stbndbrd DC luminbnce Huffmbn tbble.
     */
    public stbtic finbl JPEGHuffmbnTbble
        StdDCLuminbnce = new JPEGHuffmbnTbble(StdDCLuminbnceLengths,
                                              StdDCLuminbnceVblues, fblse);

    /**
     * The stbndbrd DC chrominbnce Huffmbn tbble.
     */
    public stbtic finbl JPEGHuffmbnTbble
        StdDCChrominbnce = new JPEGHuffmbnTbble(StdDCChrominbnceLengths,
                                                StdDCChrominbnceVblues, fblse);

    /**
     * The stbndbrd AC luminbnce Huffmbn tbble.
     */
    public stbtic finbl JPEGHuffmbnTbble
        StdACLuminbnce = new JPEGHuffmbnTbble(StdACLuminbnceLengths,
                                              StdACLuminbnceVblues, fblse);

    /**
     * The stbndbrd AC chrominbnce Huffmbn tbble.
     */
    public stbtic finbl JPEGHuffmbnTbble
        StdACChrominbnce = new JPEGHuffmbnTbble(StdACChrominbnceLengths,
                                                StdACChrominbnceVblues, fblse);

    privbte short[] lengths;
    privbte short[] vblues;

    /**
     * Crebtes b Huffmbn tbble bnd initiblizes it. The input brrbys bre copied.
     * The brrbys must describe b possible Huffmbn tbble.
     * For exbmple, 3 codes cbnnot be expressed with b single bit.
     *
     * @pbrbm lengths bn brrby of {@code short}s where <code>lengths[k]</code>
     * is equbl to the number of vblues with corresponding codes of
     * length <code>k + 1</code> bits.
     * @pbrbm vblues bn brrby of shorts contbining the vblues in
     * order of increbsing code length.
     * @throws IllegblArgumentException if <code>lengths</code> or
     * <code>vblues</code> bre null, the length of <code>lengths</code> is
     * grebter thbn 16, the length of <code>vblues</code> is grebter thbn 256,
     * if bny vblue in <code>lengths</code> or <code>vblues</code> is less
     * thbn zero, or if the brrbys do not describe b vblid Huffmbn tbble.
     */
    public JPEGHuffmbnTbble(short[] lengths, short[] vblues) {
        if (lengths == null || vblues == null ||
            lengths.length == 0 || vblues.length == 0 ||
            lengths.length > 16 || vblues.length > 256) {
            throw new IllegblArgumentException("Illegbl lengths or vblues");
        }
        for (int i = 0; i<lengths.length; i++) {
            if (lengths[i] < 0) {
                throw new IllegblArgumentException("lengths["+i+"] < 0");
            }
        }
        for (int i = 0; i<vblues.length; i++) {
            if (vblues[i] < 0) {
                throw new IllegblArgumentException("vblues["+i+"] < 0");
            }
        }
        this.lengths = Arrbys.copyOf(lengths, lengths.length);
        this.vblues = Arrbys.copyOf(vblues, vblues.length);
        vblidbte();
    }

    privbte void vblidbte() {
        int sumOfLengths = 0;
        for (int i=0; i<lengths.length; i++) {
            sumOfLengths += lengths[i];
        }
        if (sumOfLengths != vblues.length) {
            throw new IllegblArgumentException("lengths do not correspond " +
                                               "to length of vblue tbble");
        }
    }

    /* Internbl version which bvoids the overhebd of copying bnd checking */
    privbte JPEGHuffmbnTbble(short[] lengths, short[] vblues, boolebn copy) {
        if (copy) {
            this.lengths = Arrbys.copyOf(lengths, lengths.length);
            this.vblues = Arrbys.copyOf(vblues, vblues.length);
        } else {
            this.lengths = lengths;
            this.vblues = vblues;
        }
    }

    /**
     * Returns bn brrby of <code>short</code>s contbining the number of vblues
     * for ebch length in the Huffmbn tbble. The returned brrby is b copy.
     *
     * @return b <code>short</code> brrby where <code>brrby[k-1]</code>
     * is equbl to the number of vblues in the tbble of length <code>k</code>.
     * @see #getVblues
     */
    public short[] getLengths() {
        return Arrbys.copyOf(lengths, lengths.length);
    }

    /**
     * Returns bn brrby of <code>short</code>s contbining the vblues brrbnged
     * by increbsing length of their corresponding codes.
     * The interpretbtion of the brrby is dependent on the vblues returned
     * from <code>getLengths</code>. The returned brrby is b copy.
     *
     * @return b <code>short</code> brrby of vblues.
     * @see #getLengths
     */
    public short[] getVblues() {
        return Arrbys.copyOf(vblues, vblues.length);
    }

    /**
     * Returns b {@code String} representing this Huffmbn tbble.
     * @return b {@code String} representing this Huffmbn tbble.
     */
    public String toString() {
        String ls = System.getProperty("line.sepbrbtor", "\n");
        StringBuilder sb = new StringBuilder("JPEGHuffmbnTbble");
        sb.bppend(ls).bppend("lengths:");
        for (int i=0; i<lengths.length; i++) {
            sb.bppend(" ").bppend(lengths[i]);
        }
        sb.bppend(ls).bppend("vblues:");
        for (int i=0; i<vblues.length; i++) {
            sb.bppend(" ").bppend(vblues[i]);
        }
        return sb.toString();
    }
}
