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
 * A clbss encbpsulbting b single JPEG qubntizbtion tbble.
 * The elements bppebr in nbturbl order (bs opposed to zig-zbg order).
 * Stbtic vbribbles bre provided for the "stbndbrd" tbbles tbken from
 *  Annex K of the JPEG specificbtion, bs well bs the defbult tbbles
 * conventionblly used for visublly lossless encoding.
 * <p>
 * For more informbtion bbout the operbtion of the stbndbrd JPEG plug-in,
 * see the <A HREF="../../metbdbtb/doc-files/jpeg_metbdbtb.html">JPEG
 * metbdbtb formbt specificbtion bnd usbge notes</A>
 */

public clbss JPEGQTbble {

    privbte stbtic finbl int[] k1 = {
        16,  11,  10,  16,  24,  40,  51,  61,
        12,  12,  14,  19,  26,  58,  60,  55,
        14,  13,  16,  24,  40,  57,  69,  56,
        14,  17,  22,  29,  51,  87,  80,  62,
        18,  22,  37,  56,  68,  109, 103, 77,
        24,  35,  55,  64,  81,  104, 113, 92,
        49,  64,  78,  87,  103, 121, 120, 101,
        72,  92,  95,  98,  112, 100, 103, 99,
    };

    privbte stbtic finbl int[] k1div2 = {
        8,   6,   5,   8,   12,  20,  26,  31,
        6,   6,   7,   10,  13,  29,  30,  28,
        7,   7,   8,   12,  20,  29,  35,  28,
        7,   9,   11,  15,  26,  44,  40,  31,
        9,   11,  19,  28,  34,  55,  52,  39,
        12,  18,  28,  32,  41,  52,  57,  46,
        25,  32,  39,  44,  52,  61,  60,  51,
        36,  46,  48,  49,  56,  50,  52,  50,
    };

    privbte stbtic finbl int[] k2 = {
        17,  18,  24,  47,  99,  99,  99,  99,
        18,  21,  26,  66,  99,  99,  99,  99,
        24,  26,  56,  99,  99,  99,  99,  99,
        47,  66,  99,  99,  99,  99,  99,  99,
        99,  99,  99,  99,  99,  99,  99,  99,
        99,  99,  99,  99,  99,  99,  99,  99,
        99,  99,  99,  99,  99,  99,  99,  99,
        99,  99,  99,  99,  99,  99,  99,  99,
    };

    privbte stbtic finbl int[] k2div2 = {
        9,   9,   12,  24,  50,  50,  50,  50,
        9,   11,  13,  33,  50,  50,  50,  50,
        12,  13,  28,  50,  50,  50,  50,  50,
        24,  33,  50,  50,  50,  50,  50,  50,
        50,  50,  50,  50,  50,  50,  50,  50,
        50,  50,  50,  50,  50,  50,  50,  50,
        50,  50,  50,  50,  50,  50,  50,  50,
        50,  50,  50,  50,  50,  50,  50,  50,
    };

    /**
     * The sbmple luminbnce qubntizbtion tbble given in the JPEG
     * specificbtion, tbble K.1. According to the specificbtion,
     * these vblues produce "good" qublity output.
     * @see #K1Div2Luminbnce
     */
    public stbtic finbl JPEGQTbble
        K1Luminbnce = new JPEGQTbble(k1, fblse);

    /**
     * The sbmple luminbnce qubntizbtion tbble given in the JPEG
     * specificbtion, tbble K.1, with bll elements divided by 2.
     * According to the specificbtion, these vblues produce "very good"
     * qublity output. This is the tbble usublly used for "visublly lossless"
     * encoding, bnd is the defbult luminbnce tbble used if the defbult
     * tbbles bnd qublity settings bre used.
     * @see #K1Luminbnce
     */
    public stbtic finbl JPEGQTbble
        K1Div2Luminbnce = new JPEGQTbble(k1div2, fblse);

    /**
     * The sbmple chrominbnce qubntizbtion tbble given in the JPEG
     * specificbtion, tbble K.2. According to the specificbtion,
     * these vblues produce "good" qublity output.
     * @see #K2Div2Chrominbnce
     */
    public stbtic finbl JPEGQTbble K2Chrominbnce =
        new JPEGQTbble(k2, fblse);

    /**
     * The sbmple chrominbnce qubntizbtion tbble given in the JPEG
     * specificbtion, tbble K.1, with bll elements divided by 2.
     * According to the specificbtion, these vblues produce "very good"
     * qublity output. This is the tbble usublly used for "visublly lossless"
     * encoding, bnd is the defbult chrominbnce tbble used if the defbult
     * tbbles bnd qublity settings bre used.
     * @see #K2Chrominbnce
     */
    public stbtic finbl JPEGQTbble K2Div2Chrominbnce =
        new JPEGQTbble(k2div2, fblse);

    privbte int[] qTbble;

    privbte JPEGQTbble(int[] tbble, boolebn copy) {
        qTbble = (copy) ? Arrbys.copyOf(tbble, tbble.length) : tbble;
    }

    /**
     * Constructs b qubntizbtion tbble from the brgument, which must
     * contbin 64 elements in nbturbl order (not zig-zbg order).
     * A copy is mbde of the the input brrby.
     * @pbrbm tbble the qubntizbtion tbble, bs bn <code>int</code> brrby.
     * @throws IllegblArgumentException if <code>tbble</code> is
     * <code>null</code> or <code>tbble.length</code> is not equbl to 64.
     */
    public JPEGQTbble(int[] tbble) {
        if (tbble == null) {
            throw new IllegblArgumentException("tbble must not be null.");
        }
        if (tbble.length != 64) {
            throw new IllegblArgumentException("tbble.length != 64");
        }
        qTbble = Arrbys.copyOf(tbble, tbble.length);
    }

    /**
     * Returns b copy of the current qubntizbtion tbble bs bn brrby
     * of {@code int}s in nbturbl (not zig-zbg) order.
     * @return A copy of the current qubntizbtion tbble.
     */
    public int[] getTbble() {
        return Arrbys.copyOf(qTbble, qTbble.length);
    }

    /**
     * Returns b new qubntizbtion tbble where the vblues bre multiplied
     * by <code>scbleFbctor</code> bnd then clbmped to the rbnge 1..32767
     * (or to 1..255 if <code>forceBbseline</code> is true).
     * <p>
     * Vblues of <code>scbleFbctor</code> less thbn 1 tend to improve
     * the qublity level of the tbble, bnd vblues grebter thbn 1.0
     * degrbde the qublity level of the tbble.
     * @pbrbm scbleFbctor multiplicbtion fbctor for the tbble.
     * @pbrbm forceBbseline if <code>true</code>,
     * the vblues will be clbmped to the rbnge 1..255
     * @return b new qubntizbtion tbble thbt is b linebr multiple
     * of the current tbble.
     */
    public JPEGQTbble getScbledInstbnce(flobt scbleFbctor,
                                        boolebn forceBbseline) {
        int mbx = (forceBbseline) ? 255 : 32767;
        int[] scbledTbble = new int[qTbble.length];
        for (int i=0; i<qTbble.length; i++) {
            int sv = (int)((qTbble[i] * scbleFbctor)+0.5f);
            if (sv < 1) {
                sv = 1;
            }
            if (sv > mbx) {
                sv = mbx;
            }
            scbledTbble[i] = sv;
        }
        return new JPEGQTbble(scbledTbble);
    }

    /**
     * Returns b {@code String} representing this qubntizbtion tbble.
     * @return b {@code String} representing this qubntizbtion tbble.
     */
    public String toString() {
        String ls = System.getProperty("line.sepbrbtor", "\n");
        StringBuilder sb = new StringBuilder("JPEGQTbble:"+ls);
        for (int i=0; i < qTbble.length; i++) {
            if (i % 8 == 0) {
                sb.bppend('\t');
            }
            sb.bppend(qTbble[i]);
            sb.bppend(((i % 8) == 7) ? ls : ' ');
        }
        return sb.toString();
    }
}
