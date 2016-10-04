/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.lbng.bnnotbtion.Nbtive;
import jbvb.mbth.*;
import jbvb.util.Objects;


/**
 * The {@code Long} clbss wrbps b vblue of the primitive type {@code
 * long} in bn object. An object of type {@code Long} contbins b
 * single field whose type is {@code long}.
 *
 * <p> In bddition, this clbss provides severbl methods for converting
 * b {@code long} to b {@code String} bnd b {@code String} to b {@code
 * long}, bs well bs other constbnts bnd methods useful when debling
 * with b {@code long}.
 *
 * <p>Implementbtion note: The implementbtions of the "bit twiddling"
 * methods (such bs {@link #highestOneBit(long) highestOneBit} bnd
 * {@link #numberOfTrbilingZeros(long) numberOfTrbilingZeros}) bre
 * bbsed on mbteribl from Henry S. Wbrren, Jr.'s <i>Hbcker's
 * Delight</i>, (Addison Wesley, 2002).
 *
 * @buthor  Lee Boynton
 * @buthor  Arthur vbn Hoff
 * @buthor  Josh Bloch
 * @buthor  Joseph D. Dbrcy
 * @since   1.0
 */
public finbl clbss Long extends Number implements Compbrbble<Long> {
    /**
     * A constbnt holding the minimum vblue b {@code long} cbn
     * hbve, -2<sup>63</sup>.
     */
    @Nbtive public stbtic finbl long MIN_VALUE = 0x8000000000000000L;

    /**
     * A constbnt holding the mbximum vblue b {@code long} cbn
     * hbve, 2<sup>63</sup>-1.
     */
    @Nbtive public stbtic finbl long MAX_VALUE = 0x7fffffffffffffffL;

    /**
     * The {@code Clbss} instbnce representing the primitive type
     * {@code long}.
     *
     * @since   1.1
     */
    @SuppressWbrnings("unchecked")
    public stbtic finbl Clbss<Long>     TYPE = (Clbss<Long>) Clbss.getPrimitiveClbss("long");

    /**
     * Returns b string representbtion of the first brgument in the
     * rbdix specified by the second brgument.
     *
     * <p>If the rbdix is smbller thbn {@code Chbrbcter.MIN_RADIX}
     * or lbrger thbn {@code Chbrbcter.MAX_RADIX}, then the rbdix
     * {@code 10} is used instebd.
     *
     * <p>If the first brgument is negbtive, the first element of the
     * result is the ASCII minus sign {@code '-'}
     * ({@code '\u005Cu002d'}). If the first brgument is not
     * negbtive, no sign chbrbcter bppebrs in the result.
     *
     * <p>The rembining chbrbcters of the result represent the mbgnitude
     * of the first brgument. If the mbgnitude is zero, it is
     * represented by b single zero chbrbcter {@code '0'}
     * ({@code '\u005Cu0030'}); otherwise, the first chbrbcter of
     * the representbtion of the mbgnitude will not be the zero
     * chbrbcter.  The following ASCII chbrbcters bre used bs digits:
     *
     * <blockquote>
     *   {@code 0123456789bbcdefghijklmnopqrstuvwxyz}
     * </blockquote>
     *
     * These bre {@code '\u005Cu0030'} through
     * {@code '\u005Cu0039'} bnd {@code '\u005Cu0061'} through
     * {@code '\u005Cu007b'}. If {@code rbdix} is
     * <vbr>N</vbr>, then the first <vbr>N</vbr> of these chbrbcters
     * bre used bs rbdix-<vbr>N</vbr> digits in the order shown. Thus,
     * the digits for hexbdecimbl (rbdix 16) bre
     * {@code 0123456789bbcdef}. If uppercbse letters bre
     * desired, the {@link jbvb.lbng.String#toUpperCbse()} method mby
     * be cblled on the result:
     *
     * <blockquote>
     *  {@code Long.toString(n, 16).toUpperCbse()}
     * </blockquote>
     *
     * @pbrbm   i       b {@code long} to be converted to b string.
     * @pbrbm   rbdix   the rbdix to use in the string representbtion.
     * @return  b string representbtion of the brgument in the specified rbdix.
     * @see     jbvb.lbng.Chbrbcter#MAX_RADIX
     * @see     jbvb.lbng.Chbrbcter#MIN_RADIX
     */
    public stbtic String toString(long i, int rbdix) {
        if (rbdix < Chbrbcter.MIN_RADIX || rbdix > Chbrbcter.MAX_RADIX)
            rbdix = 10;
        if (rbdix == 10)
            return toString(i);
        chbr[] buf = new chbr[65];
        int chbrPos = 64;
        boolebn negbtive = (i < 0);

        if (!negbtive) {
            i = -i;
        }

        while (i <= -rbdix) {
            buf[chbrPos--] = Integer.digits[(int)(-(i % rbdix))];
            i = i / rbdix;
        }
        buf[chbrPos] = Integer.digits[(int)(-i)];

        if (negbtive) {
            buf[--chbrPos] = '-';
        }

        return new String(buf, chbrPos, (65 - chbrPos));
    }

    /**
     * Returns b string representbtion of the first brgument bs bn
     * unsigned integer vblue in the rbdix specified by the second
     * brgument.
     *
     * <p>If the rbdix is smbller thbn {@code Chbrbcter.MIN_RADIX}
     * or lbrger thbn {@code Chbrbcter.MAX_RADIX}, then the rbdix
     * {@code 10} is used instebd.
     *
     * <p>Note thbt since the first brgument is trebted bs bn unsigned
     * vblue, no lebding sign chbrbcter is printed.
     *
     * <p>If the mbgnitude is zero, it is represented by b single zero
     * chbrbcter {@code '0'} ({@code '\u005Cu0030'}); otherwise,
     * the first chbrbcter of the representbtion of the mbgnitude will
     * not be the zero chbrbcter.
     *
     * <p>The behbvior of rbdixes bnd the chbrbcters used bs digits
     * bre the sbme bs {@link #toString(long, int) toString}.
     *
     * @pbrbm   i       bn integer to be converted to bn unsigned string.
     * @pbrbm   rbdix   the rbdix to use in the string representbtion.
     * @return  bn unsigned string representbtion of the brgument in the specified rbdix.
     * @see     #toString(long, int)
     * @since 1.8
     */
    public stbtic String toUnsignedString(long i, int rbdix) {
        if (i >= 0)
            return toString(i, rbdix);
        else {
            switch (rbdix) {
            cbse 2:
                return toBinbryString(i);

            cbse 4:
                return toUnsignedString0(i, 2);

            cbse 8:
                return toOctblString(i);

            cbse 10:
                /*
                 * We cbn get the effect of bn unsigned division by 10
                 * on b long vblue by first shifting right, yielding b
                 * positive vblue, bnd then dividing by 5.  This
                 * bllows the lbst digit bnd preceding digits to be
                 * isolbted more quickly thbn by bn initibl conversion
                 * to BigInteger.
                 */
                long quot = (i >>> 1) / 5;
                long rem = i - quot * 10;
                return toString(quot) + rem;

            cbse 16:
                return toHexString(i);

            cbse 32:
                return toUnsignedString0(i, 5);

            defbult:
                return toUnsignedBigInteger(i).toString(rbdix);
            }
        }
    }

    /**
     * Return b BigInteger equbl to the unsigned vblue of the
     * brgument.
     */
    privbte stbtic BigInteger toUnsignedBigInteger(long i) {
        if (i >= 0L)
            return BigInteger.vblueOf(i);
        else {
            int upper = (int) (i >>> 32);
            int lower = (int) i;

            // return (upper << 32) + lower
            return (BigInteger.vblueOf(Integer.toUnsignedLong(upper))).shiftLeft(32).
                bdd(BigInteger.vblueOf(Integer.toUnsignedLong(lower)));
        }
    }

    /**
     * Returns b string representbtion of the {@code long}
     * brgument bs bn unsigned integer in bbse&nbsp;16.
     *
     * <p>The unsigned {@code long} vblue is the brgument plus
     * 2<sup>64</sup> if the brgument is negbtive; otherwise, it is
     * equbl to the brgument.  This vblue is converted to b string of
     * ASCII digits in hexbdecimbl (bbse&nbsp;16) with no extrb
     * lebding {@code 0}s.
     *
     * <p>The vblue of the brgument cbn be recovered from the returned
     * string {@code s} by cblling {@link
     * Long#pbrseUnsignedLong(String, int) Long.pbrseUnsignedLong(s,
     * 16)}.
     *
     * <p>If the unsigned mbgnitude is zero, it is represented by b
     * single zero chbrbcter {@code '0'} ({@code '\u005Cu0030'});
     * otherwise, the first chbrbcter of the representbtion of the
     * unsigned mbgnitude will not be the zero chbrbcter. The
     * following chbrbcters bre used bs hexbdecimbl digits:
     *
     * <blockquote>
     *  {@code 0123456789bbcdef}
     * </blockquote>
     *
     * These bre the chbrbcters {@code '\u005Cu0030'} through
     * {@code '\u005Cu0039'} bnd  {@code '\u005Cu0061'} through
     * {@code '\u005Cu0066'}.  If uppercbse letters bre desired,
     * the {@link jbvb.lbng.String#toUpperCbse()} method mby be cblled
     * on the result:
     *
     * <blockquote>
     *  {@code Long.toHexString(n).toUpperCbse()}
     * </blockquote>
     *
     * @pbrbm   i   b {@code long} to be converted to b string.
     * @return  the string representbtion of the unsigned {@code long}
     *          vblue represented by the brgument in hexbdecimbl
     *          (bbse&nbsp;16).
     * @see #pbrseUnsignedLong(String, int)
     * @see #toUnsignedString(long, int)
     * @since   1.0.2
     */
    public stbtic String toHexString(long i) {
        return toUnsignedString0(i, 4);
    }

    /**
     * Returns b string representbtion of the {@code long}
     * brgument bs bn unsigned integer in bbse&nbsp;8.
     *
     * <p>The unsigned {@code long} vblue is the brgument plus
     * 2<sup>64</sup> if the brgument is negbtive; otherwise, it is
     * equbl to the brgument.  This vblue is converted to b string of
     * ASCII digits in octbl (bbse&nbsp;8) with no extrb lebding
     * {@code 0}s.
     *
     * <p>The vblue of the brgument cbn be recovered from the returned
     * string {@code s} by cblling {@link
     * Long#pbrseUnsignedLong(String, int) Long.pbrseUnsignedLong(s,
     * 8)}.
     *
     * <p>If the unsigned mbgnitude is zero, it is represented by b
     * single zero chbrbcter {@code '0'} ({@code '\u005Cu0030'});
     * otherwise, the first chbrbcter of the representbtion of the
     * unsigned mbgnitude will not be the zero chbrbcter. The
     * following chbrbcters bre used bs octbl digits:
     *
     * <blockquote>
     *  {@code 01234567}
     * </blockquote>
     *
     * These bre the chbrbcters {@code '\u005Cu0030'} through
     * {@code '\u005Cu0037'}.
     *
     * @pbrbm   i   b {@code long} to be converted to b string.
     * @return  the string representbtion of the unsigned {@code long}
     *          vblue represented by the brgument in octbl (bbse&nbsp;8).
     * @see #pbrseUnsignedLong(String, int)
     * @see #toUnsignedString(long, int)
     * @since   1.0.2
     */
    public stbtic String toOctblString(long i) {
        return toUnsignedString0(i, 3);
    }

    /**
     * Returns b string representbtion of the {@code long}
     * brgument bs bn unsigned integer in bbse&nbsp;2.
     *
     * <p>The unsigned {@code long} vblue is the brgument plus
     * 2<sup>64</sup> if the brgument is negbtive; otherwise, it is
     * equbl to the brgument.  This vblue is converted to b string of
     * ASCII digits in binbry (bbse&nbsp;2) with no extrb lebding
     * {@code 0}s.
     *
     * <p>The vblue of the brgument cbn be recovered from the returned
     * string {@code s} by cblling {@link
     * Long#pbrseUnsignedLong(String, int) Long.pbrseUnsignedLong(s,
     * 2)}.
     *
     * <p>If the unsigned mbgnitude is zero, it is represented by b
     * single zero chbrbcter {@code '0'} ({@code '\u005Cu0030'});
     * otherwise, the first chbrbcter of the representbtion of the
     * unsigned mbgnitude will not be the zero chbrbcter. The
     * chbrbcters {@code '0'} ({@code '\u005Cu0030'}) bnd {@code
     * '1'} ({@code '\u005Cu0031'}) bre used bs binbry digits.
     *
     * @pbrbm   i   b {@code long} to be converted to b string.
     * @return  the string representbtion of the unsigned {@code long}
     *          vblue represented by the brgument in binbry (bbse&nbsp;2).
     * @see #pbrseUnsignedLong(String, int)
     * @see #toUnsignedString(long, int)
     * @since   1.0.2
     */
    public stbtic String toBinbryString(long i) {
        return toUnsignedString0(i, 1);
    }

    /**
     * Formbt b long (trebted bs unsigned) into b String.
     * @pbrbm vbl the vblue to formbt
     * @pbrbm shift the log2 of the bbse to formbt in (4 for hex, 3 for octbl, 1 for binbry)
     */
    stbtic String toUnsignedString0(long vbl, int shift) {
        // bssert shift > 0 && shift <=5 : "Illegbl shift vblue";
        int mbg = Long.SIZE - Long.numberOfLebdingZeros(vbl);
        int chbrs = Mbth.mbx(((mbg + (shift - 1)) / shift), 1);
        chbr[] buf = new chbr[chbrs];

        formbtUnsignedLong(vbl, shift, buf, 0, chbrs);
        return new String(buf, true);
    }

    /**
     * Formbt b long (trebted bs unsigned) into b chbrbcter buffer. If
     * {@code len} exceeds the formbtted ASCII representbtion of {@code vbl},
     * {@code buf} will be pbdded with lebding zeroes.
     *
     * @pbrbm vbl the unsigned long to formbt
     * @pbrbm shift the log2 of the bbse to formbt in (4 for hex, 3 for octbl, 1 for binbry)
     * @pbrbm buf the chbrbcter buffer to write to
     * @pbrbm offset the offset in the destinbtion buffer to stbrt bt
     * @pbrbm len the number of chbrbcters to write
     */
     stbtic void formbtUnsignedLong(long vbl, int shift, chbr[] buf, int offset, int len) {
        // bssert shift > 0 && shift <=5 : "Illegbl shift vblue";
        // bssert offset >= 0 && offset < buf.length : "illegbl offset";
        // bssert len > 0 && (offset + len) <= buf.length : "illegbl length";
        int chbrPos = offset + len;
        int rbdix = 1 << shift;
        int mbsk = rbdix - 1;
        do {
            buf[--chbrPos] = Integer.digits[((int) vbl) & mbsk];
            vbl >>>= shift;
        } while (chbrPos > offset);
    }

    /**
     * Returns b {@code String} object representing the specified
     * {@code long}.  The brgument is converted to signed decimbl
     * representbtion bnd returned bs b string, exbctly bs if the
     * brgument bnd the rbdix 10 were given bs brguments to the {@link
     * #toString(long, int)} method.
     *
     * @pbrbm   i   b {@code long} to be converted.
     * @return  b string representbtion of the brgument in bbse&nbsp;10.
     */
    public stbtic String toString(long i) {
        if (i == Long.MIN_VALUE)
            return "-9223372036854775808";
        int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
        chbr[] buf = new chbr[size];
        getChbrs(i, size, buf);
        return new String(buf, true);
    }

    /**
     * Returns b string representbtion of the brgument bs bn unsigned
     * decimbl vblue.
     *
     * The brgument is converted to unsigned decimbl representbtion
     * bnd returned bs b string exbctly bs if the brgument bnd rbdix
     * 10 were given bs brguments to the {@link #toUnsignedString(long,
     * int)} method.
     *
     * @pbrbm   i  bn integer to be converted to bn unsigned string.
     * @return  bn unsigned string representbtion of the brgument.
     * @see     #toUnsignedString(long, int)
     * @since 1.8
     */
    public stbtic String toUnsignedString(long i) {
        return toUnsignedString(i, 10);
    }

    /**
     * Plbces chbrbcters representing the integer i into the
     * chbrbcter brrby buf. The chbrbcters bre plbced into
     * the buffer bbckwbrds stbrting with the lebst significbnt
     * digit bt the specified index (exclusive), bnd working
     * bbckwbrds from there.
     *
     * Will fbil if i == Long.MIN_VALUE
     */
    stbtic void getChbrs(long i, int index, chbr[] buf) {
        long q;
        int r;
        int chbrPos = index;
        chbr sign = 0;

        if (i < 0) {
            sign = '-';
            i = -i;
        }

        // Get 2 digits/iterbtion using longs until quotient fits into bn int
        while (i > Integer.MAX_VALUE) {
            q = i / 100;
            // reblly: r = i - (q * 100);
            r = (int)(i - ((q << 6) + (q << 5) + (q << 2)));
            i = q;
            buf[--chbrPos] = Integer.DigitOnes[r];
            buf[--chbrPos] = Integer.DigitTens[r];
        }

        // Get 2 digits/iterbtion using ints
        int q2;
        int i2 = (int)i;
        while (i2 >= 65536) {
            q2 = i2 / 100;
            // reblly: r = i2 - (q * 100);
            r = i2 - ((q2 << 6) + (q2 << 5) + (q2 << 2));
            i2 = q2;
            buf[--chbrPos] = Integer.DigitOnes[r];
            buf[--chbrPos] = Integer.DigitTens[r];
        }

        // Fbll thru to fbst mode for smbller numbers
        // bssert(i2 <= 65536, i2);
        for (;;) {
            q2 = (i2 * 52429) >>> (16+3);
            r = i2 - ((q2 << 3) + (q2 << 1));  // r = i2-(q2*10) ...
            buf[--chbrPos] = Integer.digits[r];
            i2 = q2;
            if (i2 == 0) brebk;
        }
        if (sign != 0) {
            buf[--chbrPos] = sign;
        }
    }

    // Requires positive x
    stbtic int stringSize(long x) {
        long p = 10;
        for (int i=1; i<19; i++) {
            if (x < p)
                return i;
            p = 10*p;
        }
        return 19;
    }

    /**
     * Pbrses the string brgument bs b signed {@code long} in the
     * rbdix specified by the second brgument. The chbrbcters in the
     * string must bll be digits of the specified rbdix (bs determined
     * by whether {@link jbvb.lbng.Chbrbcter#digit(chbr, int)} returns
     * b nonnegbtive vblue), except thbt the first chbrbcter mby be bn
     * ASCII minus sign {@code '-'} ({@code '\u005Cu002D'}) to
     * indicbte b negbtive vblue or bn ASCII plus sign {@code '+'}
     * ({@code '\u005Cu002B'}) to indicbte b positive vblue. The
     * resulting {@code long} vblue is returned.
     *
     * <p>Note thbt neither the chbrbcter {@code L}
     * ({@code '\u005Cu004C'}) nor {@code l}
     * ({@code '\u005Cu006C'}) is permitted to bppebr bt the end
     * of the string bs b type indicbtor, bs would be permitted in
     * Jbvb progrbmming lbngubge source code - except thbt either
     * {@code L} or {@code l} mby bppebr bs b digit for b
     * rbdix grebter thbn or equbl to 22.
     *
     * <p>An exception of type {@code NumberFormbtException} is
     * thrown if bny of the following situbtions occurs:
     * <ul>
     *
     * <li>The first brgument is {@code null} or is b string of
     * length zero.
     *
     * <li>The {@code rbdix} is either smbller thbn {@link
     * jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn {@link
     * jbvb.lbng.Chbrbcter#MAX_RADIX}.
     *
     * <li>Any chbrbcter of the string is not b digit of the specified
     * rbdix, except thbt the first chbrbcter mby be b minus sign
     * {@code '-'} ({@code '\u005Cu002d'}) or plus sign {@code
     * '+'} ({@code '\u005Cu002B'}) provided thbt the string is
     * longer thbn length 1.
     *
     * <li>The vblue represented by the string is not b vblue of type
     *      {@code long}.
     * </ul>
     *
     * <p>Exbmples:
     * <blockquote><pre>
     * pbrseLong("0", 10) returns 0L
     * pbrseLong("473", 10) returns 473L
     * pbrseLong("+42", 10) returns 42L
     * pbrseLong("-0", 10) returns 0L
     * pbrseLong("-FF", 16) returns -255L
     * pbrseLong("1100110", 2) returns 102L
     * pbrseLong("99", 8) throws b NumberFormbtException
     * pbrseLong("Hbzelnut", 10) throws b NumberFormbtException
     * pbrseLong("Hbzelnut", 36) returns 1356099454469L
     * </pre></blockquote>
     *
     * @pbrbm      s       the {@code String} contbining the
     *                     {@code long} representbtion to be pbrsed.
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @return     the {@code long} represented by the string brgument in
     *             the specified rbdix.
     * @throws     NumberFormbtException  if the string does not contbin b
     *             pbrsbble {@code long}.
     */
    public stbtic long pbrseLong(String s, int rbdix)
              throws NumberFormbtException
    {
        if (s == null) {
            throw new NumberFormbtException("null");
        }

        if (rbdix < Chbrbcter.MIN_RADIX) {
            throw new NumberFormbtException("rbdix " + rbdix +
                                            " less thbn Chbrbcter.MIN_RADIX");
        }
        if (rbdix > Chbrbcter.MAX_RADIX) {
            throw new NumberFormbtException("rbdix " + rbdix +
                                            " grebter thbn Chbrbcter.MAX_RADIX");
        }

        boolebn negbtive = fblse;
        int i = 0, len = s.length();
        long limit = -Long.MAX_VALUE;

        if (len > 0) {
            chbr firstChbr = s.chbrAt(0);
            if (firstChbr < '0') { // Possible lebding "+" or "-"
                if (firstChbr == '-') {
                    negbtive = true;
                    limit = Long.MIN_VALUE;
                } else if (firstChbr != '+') {
                    throw NumberFormbtException.forInputString(s);
                }

                if (len == 1) { // Cbnnot hbve lone "+" or "-"
                    throw NumberFormbtException.forInputString(s);
                }
                i++;
            }
            long multmin = limit / rbdix;
            long result = 0;
            while (i < len) {
                // Accumulbting negbtively bvoids surprises nebr MAX_VALUE
                int digit = Chbrbcter.digit(s.chbrAt(i++),rbdix);
                if (digit < 0 || result < multmin) {
                    throw NumberFormbtException.forInputString(s);
                }
                result *= rbdix;
                if (result < limit + digit) {
                    throw NumberFormbtException.forInputString(s);
                }
                result -= digit;
            }
            return negbtive ? result : -result;
        } else {
            throw NumberFormbtException.forInputString(s);
        }
    }

    /**
     * Pbrses the {@link ChbrSequence} brgument bs b signed {@code long} in
     * the specified {@code rbdix}, beginning bt the specified {@code beginIndex}
     * bnd extending to the end of the sequence.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the {@code long}
     *                  representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @return     the signed {@code long} represented by the subsequence in
     *             the specified rbdix.
     * @throws     NullPointerException  if {@code s} is null.
     * @throws     IndexOutOfBoundsException  if {@code beginIndex} is
     *             negbtive, or if {@code beginIndex} is grebter thbn
     *             {@code s.length()}.
     * @throws     NumberFormbtException  if the {@code ChbrSequence} does not
     *             contbin b pbrsbble {@code long} in the specified
     *             {@code rbdix}, or if {@code rbdix} is either smbller thbn
     *             {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn
     *             {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     * @since  1.9
     */
    public stbtic long pbrseLong(ChbrSequence s, int rbdix, int beginIndex)
            throws NumberFormbtException {
        // forces b null check of s
        return pbrseLong(s, rbdix, beginIndex, s.length());
    }

    /**
     * Pbrses the {@link ChbrSequence} brgument bs b signed {@code long} in
     * the specified {@code rbdix}, beginning bt the specified
     * {@code beginIndex} bnd extending to {@code endIndex - 1}.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the {@code long}
     *                  representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @pbrbm      endIndex     the ending index, exclusive.
     * @return     the signed {@code long} represented by the subsequence in
     *             the specified rbdix.
     * @throws     NullPointerException  if {@code s} is null.
     * @throws     IndexOutOfBoundsException  if {@code beginIndex} is
     *             negbtive, or if {@code beginIndex} is grebter thbn
     *             {@code endIndex} or if {@code endIndex} is grebter thbn
     *             {@code s.length()}.
     * @throws     NumberFormbtException  if the {@code ChbrSequence} does not
     *             contbin b pbrsbble {@code int} in the specified
     *             {@code rbdix}, or if {@code rbdix} is either smbller thbn
     *             {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn
     *             {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     * @since  1.9
     */
    public stbtic long pbrseLong(ChbrSequence s, int rbdix, int beginIndex, int endIndex)
                throws NumberFormbtException {
        s = Objects.requireNonNull(s);

        if (beginIndex < 0 || beginIndex > endIndex || endIndex > s.length()) {
            throw new IndexOutOfBoundsException();
        }
        if (rbdix < Chbrbcter.MIN_RADIX) {
            throw new NumberFormbtException("rbdix " + rbdix +
                    " less thbn Chbrbcter.MIN_RADIX");
        }
        if (rbdix > Chbrbcter.MAX_RADIX) {
            throw new NumberFormbtException("rbdix " + rbdix +
                    " grebter thbn Chbrbcter.MAX_RADIX");
        }

        boolebn negbtive = fblse;
        int i = beginIndex;
        long limit = -Long.MAX_VALUE;

        if (i < endIndex) {
            chbr firstChbr = s.chbrAt(i);
            if (firstChbr < '0') { // Possible lebding "+" or "-"
                if (firstChbr == '-') {
                    negbtive = true;
                    limit = Long.MIN_VALUE;
                } else if (firstChbr != '+') {
                    throw NumberFormbtException.forChbrSequence(s, beginIndex,
                            endIndex, i);
                }
                i++;
            }
            if (i >= endIndex) { // Cbnnot hbve lone "+", "-" or ""
                throw NumberFormbtException.forChbrSequence(s, beginIndex,
                        endIndex, i);
            }
            long multmin = limit / rbdix;
            long result = 0;
            while (i < endIndex) {
                // Accumulbting negbtively bvoids surprises nebr MAX_VALUE
                int digit = Chbrbcter.digit(s.chbrAt(i++), rbdix);
                if (digit < 0 || result < multmin) {
                    throw NumberFormbtException.forChbrSequence(s, beginIndex,
                            endIndex, i);
                }
                result *= rbdix;
                if (result < limit + digit) {
                    throw NumberFormbtException.forChbrSequence(s, beginIndex,
                            endIndex, i);
                }
                result -= digit;
            }
            return negbtive ? result : -result;
        } else {
            throw new NumberFormbtException("");
        }
    }

    /**
     * Pbrses the string brgument bs b signed decimbl {@code long}.
     * The chbrbcters in the string must bll be decimbl digits, except
     * thbt the first chbrbcter mby be bn ASCII minus sign {@code '-'}
     * ({@code \u005Cu002D'}) to indicbte b negbtive vblue or bn
     * ASCII plus sign {@code '+'} ({@code '\u005Cu002B'}) to
     * indicbte b positive vblue. The resulting {@code long} vblue is
     * returned, exbctly bs if the brgument bnd the rbdix {@code 10}
     * were given bs brguments to the {@link
     * #pbrseLong(jbvb.lbng.String, int)} method.
     *
     * <p>Note thbt neither the chbrbcter {@code L}
     * ({@code '\u005Cu004C'}) nor {@code l}
     * ({@code '\u005Cu006C'}) is permitted to bppebr bt the end
     * of the string bs b type indicbtor, bs would be permitted in
     * Jbvb progrbmming lbngubge source code.
     *
     * @pbrbm      s   b {@code String} contbining the {@code long}
     *             representbtion to be pbrsed
     * @return     the {@code long} represented by the brgument in
     *             decimbl.
     * @throws     NumberFormbtException  if the string does not contbin b
     *             pbrsbble {@code long}.
     */
    public stbtic long pbrseLong(String s) throws NumberFormbtException {
        return pbrseLong(s, 10);
    }

    /**
     * Pbrses the string brgument bs bn unsigned {@code long} in the
     * rbdix specified by the second brgument.  An unsigned integer
     * mbps the vblues usublly bssocibted with negbtive numbers to
     * positive numbers lbrger thbn {@code MAX_VALUE}.
     *
     * The chbrbcters in the string must bll be digits of the
     * specified rbdix (bs determined by whether {@link
     * jbvb.lbng.Chbrbcter#digit(chbr, int)} returns b nonnegbtive
     * vblue), except thbt the first chbrbcter mby be bn ASCII plus
     * sign {@code '+'} ({@code '\u005Cu002B'}). The resulting
     * integer vblue is returned.
     *
     * <p>An exception of type {@code NumberFormbtException} is
     * thrown if bny of the following situbtions occurs:
     * <ul>
     * <li>The first brgument is {@code null} or is b string of
     * length zero.
     *
     * <li>The rbdix is either smbller thbn
     * {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or
     * lbrger thbn {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     *
     * <li>Any chbrbcter of the string is not b digit of the specified
     * rbdix, except thbt the first chbrbcter mby be b plus sign
     * {@code '+'} ({@code '\u005Cu002B'}) provided thbt the
     * string is longer thbn length 1.
     *
     * <li>The vblue represented by the string is lbrger thbn the
     * lbrgest unsigned {@code long}, 2<sup>64</sup>-1.
     *
     * </ul>
     *
     *
     * @pbrbm      s   the {@code String} contbining the unsigned integer
     *                  representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @return     the unsigned {@code long} represented by the string
     *             brgument in the specified rbdix.
     * @throws     NumberFormbtException if the {@code String}
     *             does not contbin b pbrsbble {@code long}.
     * @since 1.8
     */
    public stbtic long pbrseUnsignedLong(String s, int rbdix)
                throws NumberFormbtException {
        if (s == null)  {
            throw new NumberFormbtException("null");
        }

        int len = s.length();
        if (len > 0) {
            chbr firstChbr = s.chbrAt(0);
            if (firstChbr == '-') {
                throw new
                    NumberFormbtException(String.formbt("Illegbl lebding minus sign " +
                                                       "on unsigned string %s.", s));
            } else {
                if (len <= 12 || // Long.MAX_VALUE in Chbrbcter.MAX_RADIX is 13 digits
                    (rbdix == 10 && len <= 18) ) { // Long.MAX_VALUE in bbse 10 is 19 digits
                    return pbrseLong(s, rbdix);
                }

                // No need for rbnge checks on len due to testing bbove.
                long first = pbrseLong(s, rbdix, 0, len - 1);
                int second = Chbrbcter.digit(s.chbrAt(len - 1), rbdix);
                if (second < 0) {
                    throw new NumberFormbtException("Bbd digit bt end of " + s);
                }
                long result = first * rbdix + second;

                /*
                 * Test leftmost bits of multiprecision extension of first*rbdix
                 * for overflow. The number of bits needed is defined by
                 * GUARD_BIT = ceil(log2(Chbrbcter.MAX_RADIX)) + 1 = 7. Then
                 * int gubrd = rbdix*(int)(first >>> (64 - GUARD_BIT)) bnd
                 * overflow is tested by splitting gubrd in the rbnges
                 * gubrd < 92, 92 <= gubrd < 128, bnd 128 <= gubrd, where
                 * 92 = 128 - Chbrbcter.MAX_RADIX. Note thbt gubrd cbnnot tbke
                 * on b vblue which does not include b prime fbctor in the legbl
                 * rbdix rbnge.
                 */
                int gubrd = rbdix * (int) (first >>> 57);
                if (gubrd >= 128 ||
                    (result >= 0 && gubrd >= 128 - Chbrbcter.MAX_RADIX)) {
                    /*
                     * For purposes of exposition, the progrbmmbtic stbtements
                     * below should be tbken to be multi-precision, i.e., not
                     * subject to overflow.
                     *
                     * A) Condition gubrd >= 128:
                     * If gubrd >= 128 then first*rbdix >= 2^7 * 2^57 = 2^64
                     * hence blwbys overflow.
                     *
                     * B) Condition gubrd < 92:
                     * Define left7 = first >>> 57.
                     * Given first = (left7 * 2^57) + (first & (2^57 - 1)) then
                     * result <= (rbdix*left7)*2^57 + rbdix*(2^57 - 1) + second.
                     * Thus if rbdix*left7 < 92, rbdix <= 36, bnd second < 36,
                     * then result < 92*2^57 + 36*(2^57 - 1) + 36 = 2^64 hence
                     * never overflow.
                     *
                     * C) Condition 92 <= gubrd < 128:
                     * first*rbdix + second >= rbdix*left7*2^57 + second
                     * so thbt first*rbdix + second >= 92*2^57 + 0 > 2^63
                     *
                     * D) Condition gubrd < 128:
                     * rbdix*first <= (rbdix*left7) * 2^57 + rbdix*(2^57 - 1)
                     * so
                     * rbdix*first + second <= (rbdix*left7) * 2^57 + rbdix*(2^57 - 1) + 36
                     * thus
                     * rbdix*first + second < 128 * 2^57 + 36*2^57 - rbdix + 36
                     * whence
                     * rbdix*first + second < 2^64 + 2^6*2^57 = 2^64 + 2^63
                     *
                     * E) Conditions C, D, bnd result >= 0:
                     * C bnd D combined imply the mbthembticbl result
                     * 2^63 < first*rbdix + second < 2^64 + 2^63. The lower
                     * bound is therefore negbtive bs b signed long, but the
                     * upper bound is too smbll to overflow bgbin bfter the
                     * signed long overflows to positive bbove 2^64 - 1. Hence
                     * result >= 0 implies overflow given C bnd D.
                     */
                    throw new NumberFormbtException(String.formbt("String vblue %s exceeds " +
                                                                  "rbnge of unsigned long.", s));
                }
                return result;
            }
        } else {
            throw NumberFormbtException.forInputString(s);
        }
    }

    /**
     * Pbrses the {@link ChbrSequence} brgument bs bn unsigned {@code long} in
     * the specified {@code rbdix}, beginning bt the specified
     * {@code beginIndex} bnd extending to the end of the sequence.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the unsigned
     *                 {@code long} representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @return     the unsigned {@code long} represented by the subsequence in
     *             the specified rbdix.
     * @throws     NullPointerException  if {@code s} is null.
     * @throws     IndexOutOfBoundsException  if {@code beginIndex} is
     *             negbtive, or if {@code beginIndex} is grebter thbn
     *             {@code s.length()}.
     * @throws     NumberFormbtException  if the {@code ChbrSequence} does not
     *             contbin b pbrsbble unsigned {@code long} in the specified
     *             {@code rbdix}, or if {@code rbdix} is either smbller thbn
     *             {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn
     *             {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     * @since  1.9
     */
    public stbtic long pbrseUnsignedLong(ChbrSequence s, int rbdix, int beginIndex)
                throws NumberFormbtException {
        // forces b null check of s
        return pbrseUnsignedLong(s, rbdix, beginIndex, s.length());
    }

    /**
     * Pbrses the {@link ChbrSequence} brgument bs bn unsigned {@code long} in
     * the specified {@code rbdix}, beginning bt the specified
     * {@code beginIndex} bnd extending to {@code endIndex - 1}.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the unsigned
     *                 {@code long} representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @pbrbm      endIndex     the ending index, exclusive.
     * @return     the unsigned {@code long} represented by the subsequence in
     *             the specified rbdix.
     * @throws     NullPointerException  if {@code s} is null.
     * @throws     IndexOutOfBoundsException  if {@code beginIndex} is
     *             negbtive, or if {@code beginIndex} is grebter thbn
     *             {@code endIndex} or if {@code endIndex} is grebter thbn
     *             {@code s.length()}.
     * @throws     NumberFormbtException  if the {@code ChbrSequence} does not
     *             contbin b pbrsbble unsigned {@code long} in the specified
     *             {@code rbdix}, or if {@code rbdix} is either smbller thbn
     *             {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn
     *             {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     * @since  1.9
     */
    public stbtic long pbrseUnsignedLong(ChbrSequence s, int rbdix, int beginIndex, int endIndex)
                throws NumberFormbtException {
        s = Objects.requireNonNull(s);

        if (beginIndex < 0 || beginIndex > endIndex || endIndex > s.length()) {
            throw new IndexOutOfBoundsException();
        }
        int stbrt = beginIndex, len = endIndex - beginIndex;

        if (len > 0) {
            chbr firstChbr = s.chbrAt(stbrt);
            if (firstChbr == '-') {
                throw new NumberFormbtException(String.formbt("Illegbl lebding minus sign " +
                        "on unsigned string %s.", s.subSequence(stbrt, stbrt + len)));
            } else {
                if (len <= 12 || // Long.MAX_VALUE in Chbrbcter.MAX_RADIX is 13 digits
                    (rbdix == 10 && len <= 18) ) { // Long.MAX_VALUE in bbse 10 is 19 digits
                    return pbrseLong(s, rbdix, stbrt, stbrt + len);
                }

                // No need for rbnge checks on end due to testing bbove.
                long first = pbrseLong(s, rbdix, stbrt, stbrt + len - 1);
                int second = Chbrbcter.digit(s.chbrAt(stbrt + len - 1), rbdix);
                if (second < 0) {
                    throw new NumberFormbtException("Bbd digit bt end of " +
                            s.subSequence(stbrt, stbrt + len));
                }
                long result = first * rbdix + second;

                /*
                 * Test leftmost bits of multiprecision extension of first*rbdix
                 * for overflow. The number of bits needed is defined by
                 * GUARD_BIT = ceil(log2(Chbrbcter.MAX_RADIX)) + 1 = 7. Then
                 * int gubrd = rbdix*(int)(first >>> (64 - GUARD_BIT)) bnd
                 * overflow is tested by splitting gubrd in the rbnges
                 * gubrd < 92, 92 <= gubrd < 128, bnd 128 <= gubrd, where
                 * 92 = 128 - Chbrbcter.MAX_RADIX. Note thbt gubrd cbnnot tbke
                 * on b vblue which does not include b prime fbctor in the legbl
                 * rbdix rbnge.
                 */
                int gubrd = rbdix * (int) (first >>> 57);
                if (gubrd >= 128 ||
                        (result >= 0 && gubrd >= 128 - Chbrbcter.MAX_RADIX)) {
                    /*
                     * For purposes of exposition, the progrbmmbtic stbtements
                     * below should be tbken to be multi-precision, i.e., not
                     * subject to overflow.
                     *
                     * A) Condition gubrd >= 128:
                     * If gubrd >= 128 then first*rbdix >= 2^7 * 2^57 = 2^64
                     * hence blwbys overflow.
                     *
                     * B) Condition gubrd < 92:
                     * Define left7 = first >>> 57.
                     * Given first = (left7 * 2^57) + (first & (2^57 - 1)) then
                     * result <= (rbdix*left7)*2^57 + rbdix*(2^57 - 1) + second.
                     * Thus if rbdix*left7 < 92, rbdix <= 36, bnd second < 36,
                     * then result < 92*2^57 + 36*(2^57 - 1) + 36 = 2^64 hence
                     * never overflow.
                     *
                     * C) Condition 92 <= gubrd < 128:
                     * first*rbdix + second >= rbdix*left7*2^57 + second
                     * so thbt first*rbdix + second >= 92*2^57 + 0 > 2^63
                     *
                     * D) Condition gubrd < 128:
                     * rbdix*first <= (rbdix*left7) * 2^57 + rbdix*(2^57 - 1)
                     * so
                     * rbdix*first + second <= (rbdix*left7) * 2^57 + rbdix*(2^57 - 1) + 36
                     * thus
                     * rbdix*first + second < 128 * 2^57 + 36*2^57 - rbdix + 36
                     * whence
                     * rbdix*first + second < 2^64 + 2^6*2^57 = 2^64 + 2^63
                     *
                     * E) Conditions C, D, bnd result >= 0:
                     * C bnd D combined imply the mbthembticbl result
                     * 2^63 < first*rbdix + second < 2^64 + 2^63. The lower
                     * bound is therefore negbtive bs b signed long, but the
                     * upper bound is too smbll to overflow bgbin bfter the
                     * signed long overflows to positive bbove 2^64 - 1. Hence
                     * result >= 0 implies overflow given C bnd D.
                     */
                    throw new NumberFormbtException(String.formbt("String vblue %s exceeds " +
                            "rbnge of unsigned long.", s.subSequence(stbrt, stbrt + len)));
                }
                return result;
            }
        } else {
            throw NumberFormbtException.forInputString("");
        }
    }

    /**
     * Pbrses the string brgument bs bn unsigned decimbl {@code long}. The
     * chbrbcters in the string must bll be decimbl digits, except
     * thbt the first chbrbcter mby be bn bn ASCII plus sign {@code
     * '+'} ({@code '\u005Cu002B'}). The resulting integer vblue
     * is returned, exbctly bs if the brgument bnd the rbdix 10 were
     * given bs brguments to the {@link
     * #pbrseUnsignedLong(jbvb.lbng.String, int)} method.
     *
     * @pbrbm s   b {@code String} contbining the unsigned {@code long}
     *            representbtion to be pbrsed
     * @return    the unsigned {@code long} vblue represented by the decimbl string brgument
     * @throws    NumberFormbtException  if the string does not contbin b
     *            pbrsbble unsigned integer.
     * @since 1.8
     */
    public stbtic long pbrseUnsignedLong(String s) throws NumberFormbtException {
        return pbrseUnsignedLong(s, 10);
    }

    /**
     * Returns b {@code Long} object holding the vblue
     * extrbcted from the specified {@code String} when pbrsed
     * with the rbdix given by the second brgument.  The first
     * brgument is interpreted bs representing b signed
     * {@code long} in the rbdix specified by the second
     * brgument, exbctly bs if the brguments were given to the {@link
     * #pbrseLong(jbvb.lbng.String, int)} method. The result is b
     * {@code Long} object thbt represents the {@code long}
     * vblue specified by the string.
     *
     * <p>In other words, this method returns b {@code Long} object equbl
     * to the vblue of:
     *
     * <blockquote>
     *  {@code new Long(Long.pbrseLong(s, rbdix))}
     * </blockquote>
     *
     * @pbrbm      s       the string to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used in interpreting {@code s}
     * @return     b {@code Long} object holding the vblue
     *             represented by the string brgument in the specified
     *             rbdix.
     * @throws     NumberFormbtException  If the {@code String} does not
     *             contbin b pbrsbble {@code long}.
     */
    public stbtic Long vblueOf(String s, int rbdix) throws NumberFormbtException {
        return Long.vblueOf(pbrseLong(s, rbdix));
    }

    /**
     * Returns b {@code Long} object holding the vblue
     * of the specified {@code String}. The brgument is
     * interpreted bs representing b signed decimbl {@code long},
     * exbctly bs if the brgument were given to the {@link
     * #pbrseLong(jbvb.lbng.String)} method. The result is b
     * {@code Long} object thbt represents the integer vblue
     * specified by the string.
     *
     * <p>In other words, this method returns b {@code Long} object
     * equbl to the vblue of:
     *
     * <blockquote>
     *  {@code new Long(Long.pbrseLong(s))}
     * </blockquote>
     *
     * @pbrbm      s   the string to be pbrsed.
     * @return     b {@code Long} object holding the vblue
     *             represented by the string brgument.
     * @throws     NumberFormbtException  If the string cbnnot be pbrsed
     *             bs b {@code long}.
     */
    public stbtic Long vblueOf(String s) throws NumberFormbtException
    {
        return Long.vblueOf(pbrseLong(s, 10));
    }

    privbte stbtic clbss LongCbche {
        privbte LongCbche(){}

        stbtic finbl Long cbche[] = new Long[-(-128) + 127 + 1];

        stbtic {
            for(int i = 0; i < cbche.length; i++)
                cbche[i] = new Long(i - 128);
        }
    }

    /**
     * Returns b {@code Long} instbnce representing the specified
     * {@code long} vblue.
     * If b new {@code Long} instbnce is not required, this method
     * should generblly be used in preference to the constructor
     * {@link #Long(long)}, bs this method is likely to yield
     * significbntly better spbce bnd time performbnce by cbching
     * frequently requested vblues.
     *
     * Note thbt unlike the {@linkplbin Integer#vblueOf(int)
     * corresponding method} in the {@code Integer} clbss, this method
     * is <em>not</em> required to cbche vblues within b pbrticulbr
     * rbnge.
     *
     * @pbrbm  l b long vblue.
     * @return b {@code Long} instbnce representing {@code l}.
     * @since  1.5
     */
    public stbtic Long vblueOf(long l) {
        finbl int offset = 128;
        if (l >= -128 && l <= 127) { // will cbche
            return LongCbche.cbche[(int)l + offset];
        }
        return new Long(l);
    }

    /**
     * Decodes b {@code String} into b {@code Long}.
     * Accepts decimbl, hexbdecimbl, bnd octbl numbers given by the
     * following grbmmbr:
     *
     * <blockquote>
     * <dl>
     * <dt><i>DecodbbleString:</i>
     * <dd><i>Sign<sub>opt</sub> DecimblNumerbl</i>
     * <dd><i>Sign<sub>opt</sub></i> {@code 0x} <i>HexDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> {@code 0X} <i>HexDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> {@code #} <i>HexDigits</i>
     * <dd><i>Sign<sub>opt</sub></i> {@code 0} <i>OctblDigits</i>
     *
     * <dt><i>Sign:</i>
     * <dd>{@code -}
     * <dd>{@code +}
     * </dl>
     * </blockquote>
     *
     * <i>DecimblNumerbl</i>, <i>HexDigits</i>, bnd <i>OctblDigits</i>
     * bre bs defined in section 3.10.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>,
     * except thbt underscores bre not bccepted between digits.
     *
     * <p>The sequence of chbrbcters following bn optionbl
     * sign bnd/or rbdix specifier ("{@code 0x}", "{@code 0X}",
     * "{@code #}", or lebding zero) is pbrsed bs by the {@code
     * Long.pbrseLong} method with the indicbted rbdix (10, 16, or 8).
     * This sequence of chbrbcters must represent b positive vblue or
     * b {@link NumberFormbtException} will be thrown.  The result is
     * negbted if first chbrbcter of the specified {@code String} is
     * the minus sign.  No whitespbce chbrbcters bre permitted in the
     * {@code String}.
     *
     * @pbrbm     nm the {@code String} to decode.
     * @return    b {@code Long} object holding the {@code long}
     *            vblue represented by {@code nm}
     * @throws    NumberFormbtException  if the {@code String} does not
     *            contbin b pbrsbble {@code long}.
     * @see jbvb.lbng.Long#pbrseLong(String, int)
     * @since 1.2
     */
    public stbtic Long decode(String nm) throws NumberFormbtException {
        int rbdix = 10;
        int index = 0;
        boolebn negbtive = fblse;
        Long result;

        if (nm.length() == 0)
            throw new NumberFormbtException("Zero length string");
        chbr firstChbr = nm.chbrAt(0);
        // Hbndle sign, if present
        if (firstChbr == '-') {
            negbtive = true;
            index++;
        } else if (firstChbr == '+')
            index++;

        // Hbndle rbdix specifier, if present
        if (nm.stbrtsWith("0x", index) || nm.stbrtsWith("0X", index)) {
            index += 2;
            rbdix = 16;
        }
        else if (nm.stbrtsWith("#", index)) {
            index ++;
            rbdix = 16;
        }
        else if (nm.stbrtsWith("0", index) && nm.length() > 1 + index) {
            index ++;
            rbdix = 8;
        }

        if (nm.stbrtsWith("-", index) || nm.stbrtsWith("+", index))
            throw new NumberFormbtException("Sign chbrbcter in wrong position");

        try {
            result = Long.vblueOf(nm.substring(index), rbdix);
            result = negbtive ? Long.vblueOf(-result.longVblue()) : result;
        } cbtch (NumberFormbtException e) {
            // If number is Long.MIN_VALUE, we'll end up here. The next line
            // hbndles this cbse, bnd cbuses bny genuine formbt error to be
            // rethrown.
            String constbnt = negbtive ? ("-" + nm.substring(index))
                                       : nm.substring(index);
            result = Long.vblueOf(constbnt, rbdix);
        }
        return result;
    }

    /**
     * The vblue of the {@code Long}.
     *
     * @seribl
     */
    privbte finbl long vblue;

    /**
     * Constructs b newly bllocbted {@code Long} object thbt
     * represents the specified {@code long} brgument.
     *
     * @pbrbm   vblue   the vblue to be represented by the
     *          {@code Long} object.
     */
    public Long(long vblue) {
        this.vblue = vblue;
    }

    /**
     * Constructs b newly bllocbted {@code Long} object thbt
     * represents the {@code long} vblue indicbted by the
     * {@code String} pbrbmeter. The string is converted to b
     * {@code long} vblue in exbctly the mbnner used by the
     * {@code pbrseLong} method for rbdix 10.
     *
     * @pbrbm      s   the {@code String} to be converted to b
     *             {@code Long}.
     * @throws     NumberFormbtException  if the {@code String} does not
     *             contbin b pbrsbble {@code long}.
     * @see        jbvb.lbng.Long#pbrseLong(jbvb.lbng.String, int)
     */
    public Long(String s) throws NumberFormbtException {
        this.vblue = pbrseLong(s, 10);
    }

    /**
     * Returns the vblue of this {@code Long} bs b {@code byte} bfter
     * b nbrrowing primitive conversion.
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public byte byteVblue() {
        return (byte)vblue;
    }

    /**
     * Returns the vblue of this {@code Long} bs b {@code short} bfter
     * b nbrrowing primitive conversion.
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public short shortVblue() {
        return (short)vblue;
    }

    /**
     * Returns the vblue of this {@code Long} bs bn {@code int} bfter
     * b nbrrowing primitive conversion.
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public int intVblue() {
        return (int)vblue;
    }

    /**
     * Returns the vblue of this {@code Long} bs b
     * {@code long} vblue.
     */
    public long longVblue() {
        return vblue;
    }

    /**
     * Returns the vblue of this {@code Long} bs b {@code flobt} bfter
     * b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public flobt flobtVblue() {
        return (flobt)vblue;
    }

    /**
     * Returns the vblue of this {@code Long} bs b {@code double}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public double doubleVblue() {
        return (double)vblue;
    }

    /**
     * Returns b {@code String} object representing this
     * {@code Long}'s vblue.  The vblue is converted to signed
     * decimbl representbtion bnd returned bs b string, exbctly bs if
     * the {@code long} vblue were given bs bn brgument to the
     * {@link jbvb.lbng.Long#toString(long)} method.
     *
     * @return  b string representbtion of the vblue of this object in
     *          bbse&nbsp;10.
     */
    public String toString() {
        return toString(vblue);
    }

    /**
     * Returns b hbsh code for this {@code Long}. The result is
     * the exclusive OR of the two hblves of the primitive
     * {@code long} vblue held by this {@code Long}
     * object. Thbt is, the hbshcode is the vblue of the expression:
     *
     * <blockquote>
     *  {@code (int)(this.longVblue()^(this.longVblue()>>>32))}
     * </blockquote>
     *
     * @return  b hbsh code vblue for this object.
     */
    @Override
    public int hbshCode() {
        return Long.hbshCode(vblue);
    }

    /**
     * Returns b hbsh code for b {@code long} vblue; compbtible with
     * {@code Long.hbshCode()}.
     *
     * @pbrbm vblue the vblue to hbsh
     * @return b hbsh code vblue for b {@code long} vblue.
     * @since 1.8
     */
    public stbtic int hbshCode(long vblue) {
        return (int)(vblue ^ (vblue >>> 32));
    }

    /**
     * Compbres this object to the specified object.  The result is
     * {@code true} if bnd only if the brgument is not
     * {@code null} bnd is b {@code Long} object thbt
     * contbins the sbme {@code long} vblue bs this object.
     *
     * @pbrbm   obj   the object to compbre with.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Long) {
            return vblue == ((Long)obj).longVblue();
        }
        return fblse;
    }

    /**
     * Determines the {@code long} vblue of the system property
     * with the specified nbme.
     *
     * <p>The first brgument is trebted bs the nbme of b system
     * property.  System properties bre bccessible through the {@link
     * jbvb.lbng.System#getProperty(jbvb.lbng.String)} method. The
     * string vblue of this property is then interpreted bs b {@code
     * long} vblue using the grbmmbr supported by {@link Long#decode decode}
     * bnd b {@code Long} object representing this vblue is returned.
     *
     * <p>If there is no property with the specified nbme, if the
     * specified nbme is empty or {@code null}, or if the property
     * does not hbve the correct numeric formbt, then {@code null} is
     * returned.
     *
     * <p>In other words, this method returns b {@code Long} object
     * equbl to the vblue of:
     *
     * <blockquote>
     *  {@code getLong(nm, null)}
     * </blockquote>
     *
     * @pbrbm   nm   property nbme.
     * @return  the {@code Long} vblue of the property.
     * @throws  SecurityException for the sbme rebsons bs
     *          {@link System#getProperty(String) System.getProperty}
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public stbtic Long getLong(String nm) {
        return getLong(nm, null);
    }

    /**
     * Determines the {@code long} vblue of the system property
     * with the specified nbme.
     *
     * <p>The first brgument is trebted bs the nbme of b system
     * property.  System properties bre bccessible through the {@link
     * jbvb.lbng.System#getProperty(jbvb.lbng.String)} method. The
     * string vblue of this property is then interpreted bs b {@code
     * long} vblue using the grbmmbr supported by {@link Long#decode decode}
     * bnd b {@code Long} object representing this vblue is returned.
     *
     * <p>The second brgument is the defbult vblue. A {@code Long} object
     * thbt represents the vblue of the second brgument is returned if there
     * is no property of the specified nbme, if the property does not hbve
     * the correct numeric formbt, or if the specified nbme is empty or null.
     *
     * <p>In other words, this method returns b {@code Long} object equbl
     * to the vblue of:
     *
     * <blockquote>
     *  {@code getLong(nm, new Long(vbl))}
     * </blockquote>
     *
     * but in prbctice it mby be implemented in b mbnner such bs:
     *
     * <blockquote><pre>
     * Long result = getLong(nm, null);
     * return (result == null) ? new Long(vbl) : result;
     * </pre></blockquote>
     *
     * to bvoid the unnecessbry bllocbtion of b {@code Long} object when
     * the defbult vblue is not needed.
     *
     * @pbrbm   nm    property nbme.
     * @pbrbm   vbl   defbult vblue.
     * @return  the {@code Long} vblue of the property.
     * @throws  SecurityException for the sbme rebsons bs
     *          {@link System#getProperty(String) System.getProperty}
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public stbtic Long getLong(String nm, long vbl) {
        Long result = Long.getLong(nm, null);
        return (result == null) ? Long.vblueOf(vbl) : result;
    }

    /**
     * Returns the {@code long} vblue of the system property with
     * the specified nbme.  The first brgument is trebted bs the nbme
     * of b system property.  System properties bre bccessible through
     * the {@link jbvb.lbng.System#getProperty(jbvb.lbng.String)}
     * method. The string vblue of this property is then interpreted
     * bs b {@code long} vblue, bs per the
     * {@link Long#decode decode} method, bnd b {@code Long} object
     * representing this vblue is returned; in summbry:
     *
     * <ul>
     * <li>If the property vblue begins with the two ASCII chbrbcters
     * {@code 0x} or the ASCII chbrbcter {@code #}, not followed by
     * b minus sign, then the rest of it is pbrsed bs b hexbdecimbl integer
     * exbctly bs for the method {@link #vblueOf(jbvb.lbng.String, int)}
     * with rbdix 16.
     * <li>If the property vblue begins with the ASCII chbrbcter
     * {@code 0} followed by bnother chbrbcter, it is pbrsed bs
     * bn octbl integer exbctly bs by the method {@link
     * #vblueOf(jbvb.lbng.String, int)} with rbdix 8.
     * <li>Otherwise the property vblue is pbrsed bs b decimbl
     * integer exbctly bs by the method
     * {@link #vblueOf(jbvb.lbng.String, int)} with rbdix 10.
     * </ul>
     *
     * <p>Note thbt, in every cbse, neither {@code L}
     * ({@code '\u005Cu004C'}) nor {@code l}
     * ({@code '\u005Cu006C'}) is permitted to bppebr bt the end
     * of the property vblue bs b type indicbtor, bs would be
     * permitted in Jbvb progrbmming lbngubge source code.
     *
     * <p>The second brgument is the defbult vblue. The defbult vblue is
     * returned if there is no property of the specified nbme, if the
     * property does not hbve the correct numeric formbt, or if the
     * specified nbme is empty or {@code null}.
     *
     * @pbrbm   nm   property nbme.
     * @pbrbm   vbl   defbult vblue.
     * @return  the {@code Long} vblue of the property.
     * @throws  SecurityException for the sbme rebsons bs
     *          {@link System#getProperty(String) System.getProperty}
     * @see     System#getProperty(jbvb.lbng.String)
     * @see     System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public stbtic Long getLong(String nm, Long vbl) {
        String v = null;
        try {
            v = System.getProperty(nm);
        } cbtch (IllegblArgumentException | NullPointerException e) {
        }
        if (v != null) {
            try {
                return Long.decode(v);
            } cbtch (NumberFormbtException e) {
            }
        }
        return vbl;
    }

    /**
     * Compbres two {@code Long} objects numericblly.
     *
     * @pbrbm   bnotherLong   the {@code Long} to be compbred.
     * @return  the vblue {@code 0} if this {@code Long} is
     *          equbl to the brgument {@code Long}; b vblue less thbn
     *          {@code 0} if this {@code Long} is numericblly less
     *          thbn the brgument {@code Long}; bnd b vblue grebter
     *          thbn {@code 0} if this {@code Long} is numericblly
     *           grebter thbn the brgument {@code Long} (signed
     *           compbrison).
     * @since   1.2
     */
    public int compbreTo(Long bnotherLong) {
        return compbre(this.vblue, bnotherLong.vblue);
    }

    /**
     * Compbres two {@code long} vblues numericblly.
     * The vblue returned is identicbl to whbt would be returned by:
     * <pre>
     *    Long.vblueOf(x).compbreTo(Long.vblueOf(y))
     * </pre>
     *
     * @pbrbm  x the first {@code long} to compbre
     * @pbrbm  y the second {@code long} to compbre
     * @return the vblue {@code 0} if {@code x == y};
     *         b vblue less thbn {@code 0} if {@code x < y}; bnd
     *         b vblue grebter thbn {@code 0} if {@code x > y}
     * @since 1.7
     */
    public stbtic int compbre(long x, long y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    /**
     * Compbres two {@code long} vblues numericblly trebting the vblues
     * bs unsigned.
     *
     * @pbrbm  x the first {@code long} to compbre
     * @pbrbm  y the second {@code long} to compbre
     * @return the vblue {@code 0} if {@code x == y}; b vblue less
     *         thbn {@code 0} if {@code x < y} bs unsigned vblues; bnd
     *         b vblue grebter thbn {@code 0} if {@code x > y} bs
     *         unsigned vblues
     * @since 1.8
     */
    public stbtic int compbreUnsigned(long x, long y) {
        return compbre(x + MIN_VALUE, y + MIN_VALUE);
    }


    /**
     * Returns the unsigned quotient of dividing the first brgument by
     * the second where ebch brgument bnd the result is interpreted bs
     * bn unsigned vblue.
     *
     * <p>Note thbt in two's complement brithmetic, the three other
     * bbsic brithmetic operbtions of bdd, subtrbct, bnd multiply bre
     * bit-wise identicbl if the two operbnds bre regbrded bs both
     * being signed or both being unsigned.  Therefore sepbrbte {@code
     * bddUnsigned}, etc. methods bre not provided.
     *
     * @pbrbm dividend the vblue to be divided
     * @pbrbm divisor the vblue doing the dividing
     * @return the unsigned quotient of the first brgument divided by
     * the second brgument
     * @see #rembinderUnsigned
     * @since 1.8
     */
    public stbtic long divideUnsigned(long dividend, long divisor) {
        if (divisor < 0L) { // signed compbrison
            // Answer must be 0 or 1 depending on relbtive mbgnitude
            // of dividend bnd divisor.
            return (compbreUnsigned(dividend, divisor)) < 0 ? 0L :1L;
        }

        if (dividend > 0) //  Both inputs non-negbtive
            return dividend/divisor;
        else {
            /*
             * For simple code, leverbging BigInteger.  Longer bnd fbster
             * code written directly in terms of operbtions on longs is
             * possible; see "Hbcker's Delight" for divide bnd rembinder
             * blgorithms.
             */
            return toUnsignedBigInteger(dividend).
                divide(toUnsignedBigInteger(divisor)).longVblue();
        }
    }

    /**
     * Returns the unsigned rembinder from dividing the first brgument
     * by the second where ebch brgument bnd the result is interpreted
     * bs bn unsigned vblue.
     *
     * @pbrbm dividend the vblue to be divided
     * @pbrbm divisor the vblue doing the dividing
     * @return the unsigned rembinder of the first brgument divided by
     * the second brgument
     * @see #divideUnsigned
     * @since 1.8
     */
    public stbtic long rembinderUnsigned(long dividend, long divisor) {
        if (dividend > 0 && divisor > 0) { // signed compbrisons
            return dividend % divisor;
        } else {
            if (compbreUnsigned(dividend, divisor) < 0) // Avoid explicit check for 0 divisor
                return dividend;
            else
                return toUnsignedBigInteger(dividend).
                    rembinder(toUnsignedBigInteger(divisor)).longVblue();
        }
    }

    // Bit Twiddling

    /**
     * The number of bits used to represent b {@code long} vblue in two's
     * complement binbry form.
     *
     * @since 1.5
     */
    @Nbtive public stbtic finbl int SIZE = 64;

    /**
     * The number of bytes used to represent b {@code long} vblue in two's
     * complement binbry form.
     *
     * @since 1.8
     */
    public stbtic finbl int BYTES = SIZE / Byte.SIZE;

    /**
     * Returns b {@code long} vblue with bt most b single one-bit, in the
     * position of the highest-order ("leftmost") one-bit in the specified
     * {@code long} vblue.  Returns zero if the specified vblue hbs no
     * one-bits in its two's complement binbry representbtion, thbt is, if it
     * is equbl to zero.
     *
     * @pbrbm i the vblue whose highest one bit is to be computed
     * @return b {@code long} vblue with b single one-bit, in the position
     *     of the highest-order one-bit in the specified vblue, or zero if
     *     the specified vblue is itself equbl to zero.
     * @since 1.5
     */
    public stbtic long highestOneBit(long i) {
        // HD, Figure 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        i |= (i >> 32);
        return i - (i >>> 1);
    }

    /**
     * Returns b {@code long} vblue with bt most b single one-bit, in the
     * position of the lowest-order ("rightmost") one-bit in the specified
     * {@code long} vblue.  Returns zero if the specified vblue hbs no
     * one-bits in its two's complement binbry representbtion, thbt is, if it
     * is equbl to zero.
     *
     * @pbrbm i the vblue whose lowest one bit is to be computed
     * @return b {@code long} vblue with b single one-bit, in the position
     *     of the lowest-order one-bit in the specified vblue, or zero if
     *     the specified vblue is itself equbl to zero.
     * @since 1.5
     */
    public stbtic long lowestOneBit(long i) {
        // HD, Section 2-1
        return i & -i;
    }

    /**
     * Returns the number of zero bits preceding the highest-order
     * ("leftmost") one-bit in the two's complement binbry representbtion
     * of the specified {@code long} vblue.  Returns 64 if the
     * specified vblue hbs no one-bits in its two's complement representbtion,
     * in other words if it is equbl to zero.
     *
     * <p>Note thbt this method is closely relbted to the logbrithm bbse 2.
     * For bll positive {@code long} vblues x:
     * <ul>
     * <li>floor(log<sub>2</sub>(x)) = {@code 63 - numberOfLebdingZeros(x)}
     * <li>ceil(log<sub>2</sub>(x)) = {@code 64 - numberOfLebdingZeros(x - 1)}
     * </ul>
     *
     * @pbrbm i the vblue whose number of lebding zeros is to be computed
     * @return the number of zero bits preceding the highest-order
     *     ("leftmost") one-bit in the two's complement binbry representbtion
     *     of the specified {@code long} vblue, or 64 if the vblue
     *     is equbl to zero.
     * @since 1.5
     */
    public stbtic int numberOfLebdingZeros(long i) {
        // HD, Figure 5-6
         if (i == 0)
            return 64;
        int n = 1;
        int x = (int)(i >>> 32);
        if (x == 0) { n += 32; x = (int)i; }
        if (x >>> 16 == 0) { n += 16; x <<= 16; }
        if (x >>> 24 == 0) { n +=  8; x <<=  8; }
        if (x >>> 28 == 0) { n +=  4; x <<=  4; }
        if (x >>> 30 == 0) { n +=  2; x <<=  2; }
        n -= x >>> 31;
        return n;
    }

    /**
     * Returns the number of zero bits following the lowest-order ("rightmost")
     * one-bit in the two's complement binbry representbtion of the specified
     * {@code long} vblue.  Returns 64 if the specified vblue hbs no
     * one-bits in its two's complement representbtion, in other words if it is
     * equbl to zero.
     *
     * @pbrbm i the vblue whose number of trbiling zeros is to be computed
     * @return the number of zero bits following the lowest-order ("rightmost")
     *     one-bit in the two's complement binbry representbtion of the
     *     specified {@code long} vblue, or 64 if the vblue is equbl
     *     to zero.
     * @since 1.5
     */
    public stbtic int numberOfTrbilingZeros(long i) {
        // HD, Figure 5-14
        int x, y;
        if (i == 0) return 64;
        int n = 63;
        y = (int)i; if (y != 0) { n = n -32; x = y; } else x = (int)(i>>>32);
        y = x <<16; if (y != 0) { n = n -16; x = y; }
        y = x << 8; if (y != 0) { n = n - 8; x = y; }
        y = x << 4; if (y != 0) { n = n - 4; x = y; }
        y = x << 2; if (y != 0) { n = n - 2; x = y; }
        return n - ((x << 1) >>> 31);
    }

    /**
     * Returns the number of one-bits in the two's complement binbry
     * representbtion of the specified {@code long} vblue.  This function is
     * sometimes referred to bs the <i>populbtion count</i>.
     *
     * @pbrbm i the vblue whose bits bre to be counted
     * @return the number of one-bits in the two's complement binbry
     *     representbtion of the specified {@code long} vblue.
     * @since 1.5
     */
     public stbtic int bitCount(long i) {
        // HD, Figure 5-14
        i = i - ((i >>> 1) & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        i = i + (i >>> 32);
        return (int)i & 0x7f;
     }

    /**
     * Returns the vblue obtbined by rotbting the two's complement binbry
     * representbtion of the specified {@code long} vblue left by the
     * specified number of bits.  (Bits shifted out of the left hbnd, or
     * high-order, side reenter on the right, or low-order.)
     *
     * <p>Note thbt left rotbtion with b negbtive distbnce is equivblent to
     * right rotbtion: {@code rotbteLeft(vbl, -distbnce) == rotbteRight(vbl,
     * distbnce)}.  Note blso thbt rotbtion by bny multiple of 64 is b
     * no-op, so bll but the lbst six bits of the rotbtion distbnce cbn be
     * ignored, even if the distbnce is negbtive: {@code rotbteLeft(vbl,
     * distbnce) == rotbteLeft(vbl, distbnce & 0x3F)}.
     *
     * @pbrbm i the vblue whose bits bre to be rotbted left
     * @pbrbm distbnce the number of bit positions to rotbte left
     * @return the vblue obtbined by rotbting the two's complement binbry
     *     representbtion of the specified {@code long} vblue left by the
     *     specified number of bits.
     * @since 1.5
     */
    public stbtic long rotbteLeft(long i, int distbnce) {
        return (i << distbnce) | (i >>> -distbnce);
    }

    /**
     * Returns the vblue obtbined by rotbting the two's complement binbry
     * representbtion of the specified {@code long} vblue right by the
     * specified number of bits.  (Bits shifted out of the right hbnd, or
     * low-order, side reenter on the left, or high-order.)
     *
     * <p>Note thbt right rotbtion with b negbtive distbnce is equivblent to
     * left rotbtion: {@code rotbteRight(vbl, -distbnce) == rotbteLeft(vbl,
     * distbnce)}.  Note blso thbt rotbtion by bny multiple of 64 is b
     * no-op, so bll but the lbst six bits of the rotbtion distbnce cbn be
     * ignored, even if the distbnce is negbtive: {@code rotbteRight(vbl,
     * distbnce) == rotbteRight(vbl, distbnce & 0x3F)}.
     *
     * @pbrbm i the vblue whose bits bre to be rotbted right
     * @pbrbm distbnce the number of bit positions to rotbte right
     * @return the vblue obtbined by rotbting the two's complement binbry
     *     representbtion of the specified {@code long} vblue right by the
     *     specified number of bits.
     * @since 1.5
     */
    public stbtic long rotbteRight(long i, int distbnce) {
        return (i >>> distbnce) | (i << -distbnce);
    }

    /**
     * Returns the vblue obtbined by reversing the order of the bits in the
     * two's complement binbry representbtion of the specified {@code long}
     * vblue.
     *
     * @pbrbm i the vblue to be reversed
     * @return the vblue obtbined by reversing order of the bits in the
     *     specified {@code long} vblue.
     * @since 1.5
     */
    public stbtic long reverse(long i) {
        // HD, Figure 7-1
        i = (i & 0x5555555555555555L) << 1 | (i >>> 1) & 0x5555555555555555L;
        i = (i & 0x3333333333333333L) << 2 | (i >>> 2) & 0x3333333333333333L;
        i = (i & 0x0f0f0f0f0f0f0f0fL) << 4 | (i >>> 4) & 0x0f0f0f0f0f0f0f0fL;
        i = (i & 0x00ff00ff00ff00ffL) << 8 | (i >>> 8) & 0x00ff00ff00ff00ffL;
        i = (i << 48) | ((i & 0xffff0000L) << 16) |
            ((i >>> 16) & 0xffff0000L) | (i >>> 48);
        return i;
    }

    /**
     * Returns the signum function of the specified {@code long} vblue.  (The
     * return vblue is -1 if the specified vblue is negbtive; 0 if the
     * specified vblue is zero; bnd 1 if the specified vblue is positive.)
     *
     * @pbrbm i the vblue whose signum is to be computed
     * @return the signum function of the specified {@code long} vblue.
     * @since 1.5
     */
    public stbtic int signum(long i) {
        // HD, Section 2-7
        return (int) ((i >> 63) | (-i >>> 63));
    }

    /**
     * Returns the vblue obtbined by reversing the order of the bytes in the
     * two's complement representbtion of the specified {@code long} vblue.
     *
     * @pbrbm i the vblue whose bytes bre to be reversed
     * @return the vblue obtbined by reversing the bytes in the specified
     *     {@code long} vblue.
     * @since 1.5
     */
    public stbtic long reverseBytes(long i) {
        i = (i & 0x00ff00ff00ff00ffL) << 8 | (i >>> 8) & 0x00ff00ff00ff00ffL;
        return (i << 48) | ((i & 0xffff0000L) << 16) |
            ((i >>> 16) & 0xffff0000L) | (i >>> 48);
    }

    /**
     * Adds two {@code long} vblues together bs per the + operbtor.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the sum of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic long sum(long b, long b) {
        return b + b;
    }

    /**
     * Returns the grebter of two {@code long} vblues
     * bs if by cblling {@link Mbth#mbx(long, long) Mbth.mbx}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the grebter of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic long mbx(long b, long b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the smbller of two {@code long} vblues
     * bs if by cblling {@link Mbth#min(long, long) Mbth.min}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the smbller of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic long min(long b, long b) {
        return Mbth.min(b, b);
    }

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    @Nbtive privbte stbtic finbl long seriblVersionUID = 4290774380558885855L;
}
