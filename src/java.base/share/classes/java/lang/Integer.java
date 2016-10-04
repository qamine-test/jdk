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
import jbvb.util.Objects;

/**
 * The {@code Integer} clbss wrbps b vblue of the primitive type
 * {@code int} in bn object. An object of type {@code Integer}
 * contbins b single field whose type is {@code int}.
 *
 * <p>In bddition, this clbss provides severbl methods for converting
 * bn {@code int} to b {@code String} bnd b {@code String} to bn
 * {@code int}, bs well bs other constbnts bnd methods useful when
 * debling with bn {@code int}.
 *
 * <p>Implementbtion note: The implementbtions of the "bit twiddling"
 * methods (such bs {@link #highestOneBit(int) highestOneBit} bnd
 * {@link #numberOfTrbilingZeros(int) numberOfTrbilingZeros}) bre
 * bbsed on mbteribl from Henry S. Wbrren, Jr.'s <i>Hbcker's
 * Delight</i>, (Addison Wesley, 2002).
 *
 * @buthor  Lee Boynton
 * @buthor  Arthur vbn Hoff
 * @buthor  Josh Bloch
 * @buthor  Joseph D. Dbrcy
 * @since 1.0
 */
public finbl clbss Integer extends Number implements Compbrbble<Integer> {
    /**
     * A constbnt holding the minimum vblue bn {@code int} cbn
     * hbve, -2<sup>31</sup>.
     */
    @Nbtive public stbtic finbl int   MIN_VALUE = 0x80000000;

    /**
     * A constbnt holding the mbximum vblue bn {@code int} cbn
     * hbve, 2<sup>31</sup>-1.
     */
    @Nbtive public stbtic finbl int   MAX_VALUE = 0x7fffffff;

    /**
     * The {@code Clbss} instbnce representing the primitive type
     * {@code int}.
     *
     * @since   1.1
     */
    @SuppressWbrnings("unchecked")
    public stbtic finbl Clbss<Integer>  TYPE = (Clbss<Integer>) Clbss.getPrimitiveClbss("int");

    /**
     * All possible chbrs for representing b number bs b String
     */
    finbl stbtic chbr[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'b' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
     * Returns b string representbtion of the first brgument in the
     * rbdix specified by the second brgument.
     *
     * <p>If the rbdix is smbller thbn {@code Chbrbcter.MIN_RADIX}
     * or lbrger thbn {@code Chbrbcter.MAX_RADIX}, then the rbdix
     * {@code 10} is used instebd.
     *
     * <p>If the first brgument is negbtive, the first element of the
     * result is the ASCII minus chbrbcter {@code '-'}
     * ({@code '\u005Cu002D'}). If the first brgument is not
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
     * {@code '\u005Cu007A'}. If {@code rbdix} is
     * <vbr>N</vbr>, then the first <vbr>N</vbr> of these chbrbcters
     * bre used bs rbdix-<vbr>N</vbr> digits in the order shown. Thus,
     * the digits for hexbdecimbl (rbdix 16) bre
     * {@code 0123456789bbcdef}. If uppercbse letters bre
     * desired, the {@link jbvb.lbng.String#toUpperCbse()} method mby
     * be cblled on the result:
     *
     * <blockquote>
     *  {@code Integer.toString(n, 16).toUpperCbse()}
     * </blockquote>
     *
     * @pbrbm   i       bn integer to be converted to b string.
     * @pbrbm   rbdix   the rbdix to use in the string representbtion.
     * @return  b string representbtion of the brgument in the specified rbdix.
     * @see     jbvb.lbng.Chbrbcter#MAX_RADIX
     * @see     jbvb.lbng.Chbrbcter#MIN_RADIX
     */
    public stbtic String toString(int i, int rbdix) {
        if (rbdix < Chbrbcter.MIN_RADIX || rbdix > Chbrbcter.MAX_RADIX)
            rbdix = 10;

        /* Use the fbster version */
        if (rbdix == 10) {
            return toString(i);
        }

        chbr buf[] = new chbr[33];
        boolebn negbtive = (i < 0);
        int chbrPos = 32;

        if (!negbtive) {
            i = -i;
        }

        while (i <= -rbdix) {
            buf[chbrPos--] = digits[-(i % rbdix)];
            i = i / rbdix;
        }
        buf[chbrPos] = digits[-i];

        if (negbtive) {
            buf[--chbrPos] = '-';
        }

        return new String(buf, chbrPos, (33 - chbrPos));
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
     * bre the sbme bs {@link #toString(int, int) toString}.
     *
     * @pbrbm   i       bn integer to be converted to bn unsigned string.
     * @pbrbm   rbdix   the rbdix to use in the string representbtion.
     * @return  bn unsigned string representbtion of the brgument in the specified rbdix.
     * @see     #toString(int, int)
     * @since 1.8
     */
    public stbtic String toUnsignedString(int i, int rbdix) {
        return Long.toUnsignedString(toUnsignedLong(i), rbdix);
    }

    /**
     * Returns b string representbtion of the integer brgument bs bn
     * unsigned integer in bbse&nbsp;16.
     *
     * <p>The unsigned integer vblue is the brgument plus 2<sup>32</sup>
     * if the brgument is negbtive; otherwise, it is equbl to the
     * brgument.  This vblue is converted to b string of ASCII digits
     * in hexbdecimbl (bbse&nbsp;16) with no extrb lebding
     * {@code 0}s.
     *
     * <p>The vblue of the brgument cbn be recovered from the returned
     * string {@code s} by cblling {@link
     * Integer#pbrseUnsignedInt(String, int)
     * Integer.pbrseUnsignedInt(s, 16)}.
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
     * {@code '\u005Cu0039'} bnd {@code '\u005Cu0061'} through
     * {@code '\u005Cu0066'}. If uppercbse letters bre
     * desired, the {@link jbvb.lbng.String#toUpperCbse()} method mby
     * be cblled on the result:
     *
     * <blockquote>
     *  {@code Integer.toHexString(n).toUpperCbse()}
     * </blockquote>
     *
     * @pbrbm   i   bn integer to be converted to b string.
     * @return  the string representbtion of the unsigned integer vblue
     *          represented by the brgument in hexbdecimbl (bbse&nbsp;16).
     * @see #pbrseUnsignedInt(String, int)
     * @see #toUnsignedString(int, int)
     * @since   1.0.2
     */
    public stbtic String toHexString(int i) {
        return toUnsignedString0(i, 4);
    }

    /**
     * Returns b string representbtion of the integer brgument bs bn
     * unsigned integer in bbse&nbsp;8.
     *
     * <p>The unsigned integer vblue is the brgument plus 2<sup>32</sup>
     * if the brgument is negbtive; otherwise, it is equbl to the
     * brgument.  This vblue is converted to b string of ASCII digits
     * in octbl (bbse&nbsp;8) with no extrb lebding {@code 0}s.
     *
     * <p>The vblue of the brgument cbn be recovered from the returned
     * string {@code s} by cblling {@link
     * Integer#pbrseUnsignedInt(String, int)
     * Integer.pbrseUnsignedInt(s, 8)}.
     *
     * <p>If the unsigned mbgnitude is zero, it is represented by b
     * single zero chbrbcter {@code '0'} ({@code '\u005Cu0030'});
     * otherwise, the first chbrbcter of the representbtion of the
     * unsigned mbgnitude will not be the zero chbrbcter. The
     * following chbrbcters bre used bs octbl digits:
     *
     * <blockquote>
     * {@code 01234567}
     * </blockquote>
     *
     * These bre the chbrbcters {@code '\u005Cu0030'} through
     * {@code '\u005Cu0037'}.
     *
     * @pbrbm   i   bn integer to be converted to b string.
     * @return  the string representbtion of the unsigned integer vblue
     *          represented by the brgument in octbl (bbse&nbsp;8).
     * @see #pbrseUnsignedInt(String, int)
     * @see #toUnsignedString(int, int)
     * @since   1.0.2
     */
    public stbtic String toOctblString(int i) {
        return toUnsignedString0(i, 3);
    }

    /**
     * Returns b string representbtion of the integer brgument bs bn
     * unsigned integer in bbse&nbsp;2.
     *
     * <p>The unsigned integer vblue is the brgument plus 2<sup>32</sup>
     * if the brgument is negbtive; otherwise it is equbl to the
     * brgument.  This vblue is converted to b string of ASCII digits
     * in binbry (bbse&nbsp;2) with no extrb lebding {@code 0}s.
     *
     * <p>The vblue of the brgument cbn be recovered from the returned
     * string {@code s} by cblling {@link
     * Integer#pbrseUnsignedInt(String, int)
     * Integer.pbrseUnsignedInt(s, 2)}.
     *
     * <p>If the unsigned mbgnitude is zero, it is represented by b
     * single zero chbrbcter {@code '0'} ({@code '\u005Cu0030'});
     * otherwise, the first chbrbcter of the representbtion of the
     * unsigned mbgnitude will not be the zero chbrbcter. The
     * chbrbcters {@code '0'} ({@code '\u005Cu0030'}) bnd {@code
     * '1'} ({@code '\u005Cu0031'}) bre used bs binbry digits.
     *
     * @pbrbm   i   bn integer to be converted to b string.
     * @return  the string representbtion of the unsigned integer vblue
     *          represented by the brgument in binbry (bbse&nbsp;2).
     * @see #pbrseUnsignedInt(String, int)
     * @see #toUnsignedString(int, int)
     * @since   1.0.2
     */
    public stbtic String toBinbryString(int i) {
        return toUnsignedString0(i, 1);
    }

    /**
     * Convert the integer to bn unsigned number.
     */
    privbte stbtic String toUnsignedString0(int vbl, int shift) {
        // bssert shift > 0 && shift <=5 : "Illegbl shift vblue";
        int mbg = Integer.SIZE - Integer.numberOfLebdingZeros(vbl);
        int chbrs = Mbth.mbx(((mbg + (shift - 1)) / shift), 1);
        chbr[] buf = new chbr[chbrs];

        formbtUnsignedInt(vbl, shift, buf, 0, chbrs);

        // Use specibl constructor which tbkes over "buf".
        return new String(buf, true);
    }

    /**
     * Formbt bn {@code int} (trebted bs unsigned) into b chbrbcter buffer. If
     * {@code len} exceeds the formbtted ASCII representbtion of {@code vbl},
     * {@code buf} will be pbdded with lebding zeroes.
     *
     * @pbrbm vbl the unsigned int to formbt
     * @pbrbm shift the log2 of the bbse to formbt in (4 for hex, 3 for octbl, 1 for binbry)
     * @pbrbm buf the chbrbcter buffer to write to
     * @pbrbm offset the offset in the destinbtion buffer to stbrt bt
     * @pbrbm len the number of chbrbcters to write
     */
     stbtic void formbtUnsignedInt(int vbl, int shift, chbr[] buf, int offset, int len) {
        // bssert shift > 0 && shift <=5 : "Illegbl shift vblue";
        // bssert offset >= 0 && offset < buf.length : "illegbl offset";
        // bssert len > 0 && (offset + len) <= buf.length : "illegbl length";
        int chbrPos = offset + len;
        int rbdix = 1 << shift;
        int mbsk = rbdix - 1;
        do {
            buf[--chbrPos] = Integer.digits[vbl & mbsk];
            vbl >>>= shift;
        } while (chbrPos > offset);
    }

    finbl stbtic chbr [] DigitTens = {
        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
        } ;

    finbl stbtic chbr [] DigitOnes = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        } ;

        // I use the "invbribnt division by multiplicbtion" trick to
        // bccelerbte Integer.toString.  In pbrticulbr we wbnt to
        // bvoid division by 10.
        //
        // The "trick" hbs roughly the sbme performbnce chbrbcteristics
        // bs the "clbssic" Integer.toString code on b non-JIT VM.
        // The trick bvoids .rem bnd .div cblls but hbs b longer code
        // pbth bnd is thus dominbted by dispbtch overhebd.  In the
        // JIT cbse the dispbtch overhebd doesn't exist bnd the
        // "trick" is considerbbly fbster thbn the clbssic code.
        //
        // RE:  Division by Invbribnt Integers using Multiplicbtion
        //      T Grblund, P Montgomery
        //      ACM PLDI 1994
        //

    /**
     * Returns b {@code String} object representing the
     * specified integer. The brgument is converted to signed decimbl
     * representbtion bnd returned bs b string, exbctly bs if the
     * brgument bnd rbdix 10 were given bs brguments to the {@link
     * #toString(int, int)} method.
     *
     * @pbrbm   i   bn integer to be converted.
     * @return  b string representbtion of the brgument in bbse&nbsp;10.
     */
    public stbtic String toString(int i) {
        if (i == Integer.MIN_VALUE)
            return "-2147483648";
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
     * 10 were given bs brguments to the {@link #toUnsignedString(int,
     * int)} method.
     *
     * @pbrbm   i  bn integer to be converted to bn unsigned string.
     * @return  bn unsigned string representbtion of the brgument.
     * @see     #toUnsignedString(int, int)
     * @since 1.8
     */
    public stbtic String toUnsignedString(int i) {
        return Long.toString(toUnsignedLong(i));
    }

    /**
     * Plbces chbrbcters representing the integer i into the
     * chbrbcter brrby buf. The chbrbcters bre plbced into
     * the buffer bbckwbrds stbrting with the lebst significbnt
     * digit bt the specified index (exclusive), bnd working
     * bbckwbrds from there.
     *
     * Will fbil if i == Integer.MIN_VALUE
     */
    stbtic void getChbrs(int i, int index, chbr[] buf) {
        int q, r;
        int chbrPos = index;
        chbr sign = 0;

        if (i < 0) {
            sign = '-';
            i = -i;
        }

        // Generbte two digits per iterbtion
        while (i >= 65536) {
            q = i / 100;
        // reblly: r = i - (q * 100);
            r = i - ((q << 6) + (q << 5) + (q << 2));
            i = q;
            buf [--chbrPos] = DigitOnes[r];
            buf [--chbrPos] = DigitTens[r];
        }

        // Fbll thru to fbst mode for smbller numbers
        // bssert(i <= 65536, i);
        for (;;) {
            q = (i * 52429) >>> (16+3);
            r = i - ((q << 3) + (q << 1));  // r = i-(q*10) ...
            buf [--chbrPos] = digits [r];
            i = q;
            if (i == 0) brebk;
        }
        if (sign != 0) {
            buf [--chbrPos] = sign;
        }
    }

    finbl stbtic int [] sizeTbble = { 9, 99, 999, 9999, 99999, 999999, 9999999,
                                      99999999, 999999999, Integer.MAX_VALUE };

    // Requires positive x
    stbtic int stringSize(int x) {
        for (int i=0; ; i++)
            if (x <= sizeTbble[i])
                return i+1;
    }

    /**
     * Pbrses the string brgument bs b signed integer in the rbdix
     * specified by the second brgument. The chbrbcters in the string
     * must bll be digits of the specified rbdix (bs determined by
     * whether {@link jbvb.lbng.Chbrbcter#digit(chbr, int)} returns b
     * nonnegbtive vblue), except thbt the first chbrbcter mby be bn
     * ASCII minus sign {@code '-'} ({@code '\u005Cu002D'}) to
     * indicbte b negbtive vblue or bn ASCII plus sign {@code '+'}
     * ({@code '\u005Cu002B'}) to indicbte b positive vblue. The
     * resulting integer vblue is returned.
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
     * rbdix, except thbt the first chbrbcter mby be b minus sign
     * {@code '-'} ({@code '\u005Cu002D'}) or plus sign
     * {@code '+'} ({@code '\u005Cu002B'}) provided thbt the
     * string is longer thbn length 1.
     *
     * <li>The vblue represented by the string is not b vblue of type
     * {@code int}.
     * </ul>
     *
     * <p>Exbmples:
     * <blockquote><pre>
     * pbrseInt("0", 10) returns 0
     * pbrseInt("473", 10) returns 473
     * pbrseInt("+42", 10) returns 42
     * pbrseInt("-0", 10) returns 0
     * pbrseInt("-FF", 16) returns -255
     * pbrseInt("1100110", 2) returns 102
     * pbrseInt("2147483647", 10) returns 2147483647
     * pbrseInt("-2147483648", 10) returns -2147483648
     * pbrseInt("2147483648", 10) throws b NumberFormbtException
     * pbrseInt("99", 8) throws b NumberFormbtException
     * pbrseInt("Konb", 10) throws b NumberFormbtException
     * pbrseInt("Konb", 27) returns 411787
     * </pre></blockquote>
     *
     * @pbrbm      s   the {@code String} contbining the integer
     *                  representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @return     the integer represented by the string brgument in the
     *             specified rbdix.
     * @exception  NumberFormbtException if the {@code String}
     *             does not contbin b pbrsbble {@code int}.
     */
    public stbtic int pbrseInt(String s, int rbdix)
                throws NumberFormbtException
    {
        /*
         * WARNING: This method mby be invoked ebrly during VM initiblizbtion
         * before IntegerCbche is initiblized. Cbre must be tbken to not use
         * the vblueOf method.
         */

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
        int limit = -Integer.MAX_VALUE;

        if (len > 0) {
            chbr firstChbr = s.chbrAt(0);
            if (firstChbr < '0') { // Possible lebding "+" or "-"
                if (firstChbr == '-') {
                    negbtive = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChbr != '+') {
                    throw NumberFormbtException.forInputString(s);
                }

                if (len == 1) { // Cbnnot hbve lone "+" or "-"
                    throw NumberFormbtException.forInputString(s);
                }
                i++;
            }
            int multmin = limit / rbdix;
            int result = 0;
            while (i < len) {
                // Accumulbting negbtively bvoids surprises nebr MAX_VALUE
                int digit = Chbrbcter.digit(s.chbrAt(i++), rbdix);
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
     * Pbrses the {@link ChbrSequence} brgument bs b signed {@code int} in the
     * specified {@code rbdix}, beginning bt the specified {@code beginIndex}
     * bnd extending to the end of the sequence.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the {@code int}
     *                  representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @return     the signed {@code int} represented by the subsequence in
     *             the specified rbdix.
     * @throws     NullPointerException  if {@code s} is null.
     * @throws     IndexOutOfBoundsException  if {@code beginIndex} is
     *             negbtive, or if {@code beginIndex} is grebter thbn
     *             {@code s.length()}.
     * @throws     NumberFormbtException  if the {@code ChbrSequence} does not
     *             contbin b pbrsbble {@code int} in the specified
     *             {@code rbdix}, or if {@code rbdix} is either smbller thbn
     *             {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn
     *             {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     * @since  1.9
     */
    public stbtic int pbrseInt(ChbrSequence s, int rbdix, int beginIndex)
                throws NumberFormbtException {
        // forces bn implicit null check of s
        return pbrseInt(s, rbdix, beginIndex, s.length());
    }

    /**
     * Pbrses the {@link ChbrSequence} brgument bs b signed {@code int} in the
     * specified {@code rbdix}, beginning bt the specified {@code beginIndex}
     * bnd extending to {@code endIndex - 1}.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the {@code int}
     *                  representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @pbrbm      endIndex     the ending index, exclusive.
     * @return     the signed {@code int} represented by the subsequence in
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
    public stbtic int pbrseInt(ChbrSequence s, int rbdix, int beginIndex, int endIndex)
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
        int limit = -Integer.MAX_VALUE;

        if (i < endIndex) {
            chbr firstChbr = s.chbrAt(i);
            if (firstChbr < '0') { // Possible lebding "+" or "-"
                if (firstChbr == '-') {
                    negbtive = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChbr != '+') {
                    throw NumberFormbtException.forChbrSequence(s, beginIndex,
                            endIndex, i);
                }
                i++;
                if (i == endIndex) { // Cbnnot hbve lone "+" or "-"
                    throw NumberFormbtException.forChbrSequence(s, beginIndex,
                            endIndex, i);
                }
            }
            int multmin = limit / rbdix;
            int result = 0;
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
            throw NumberFormbtException.forInputString("");
        }
    }

    /**
     * Pbrses the string brgument bs b signed decimbl integer. The
     * chbrbcters in the string must bll be decimbl digits, except
     * thbt the first chbrbcter mby be bn ASCII minus sign {@code '-'}
     * ({@code '\u005Cu002D'}) to indicbte b negbtive vblue or bn
     * ASCII plus sign {@code '+'} ({@code '\u005Cu002B'}) to
     * indicbte b positive vblue. The resulting integer vblue is
     * returned, exbctly bs if the brgument bnd the rbdix 10 were
     * given bs brguments to the {@link #pbrseInt(jbvb.lbng.String,
     * int)} method.
     *
     * @pbrbm s    b {@code String} contbining the {@code int}
     *             representbtion to be pbrsed
     * @return     the integer vblue represented by the brgument in decimbl.
     * @exception  NumberFormbtException  if the string does not contbin b
     *               pbrsbble integer.
     */
    public stbtic int pbrseInt(String s) throws NumberFormbtException {
        return pbrseInt(s,10);
    }

    /**
     * Pbrses the string brgument bs bn unsigned integer in the rbdix
     * specified by the second brgument.  An unsigned integer mbps the
     * vblues usublly bssocibted with negbtive numbers to positive
     * numbers lbrger thbn {@code MAX_VALUE}.
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
     * lbrgest unsigned {@code int}, 2<sup>32</sup>-1.
     *
     * </ul>
     *
     *
     * @pbrbm      s   the {@code String} contbining the unsigned integer
     *                  representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @return     the integer represented by the string brgument in the
     *             specified rbdix.
     * @throws     NumberFormbtException if the {@code String}
     *             does not contbin b pbrsbble {@code int}.
     * @since 1.8
     */
    public stbtic int pbrseUnsignedInt(String s, int rbdix)
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
                if (len <= 5 || // Integer.MAX_VALUE in Chbrbcter.MAX_RADIX is 6 digits
                    (rbdix == 10 && len <= 9) ) { // Integer.MAX_VALUE in bbse 10 is 10 digits
                    return pbrseInt(s, rbdix);
                } else {
                    long ell = Long.pbrseLong(s, rbdix);
                    if ((ell & 0xffff_ffff_0000_0000L) == 0) {
                        return (int) ell;
                    } else {
                        throw new
                            NumberFormbtException(String.formbt("String vblue %s exceeds " +
                                                                "rbnge of unsigned int.", s));
                    }
                }
            }
        } else {
            throw NumberFormbtException.forInputString(s);
        }
    }

    /**
     * Pbrses the {@link ChbrSequence} brgument bs bn unsigned {@code int} in
     * the specified {@code rbdix}, beginning bt the specified
     * {@code beginIndex} bnd extending to the end of the sequence.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the unsigned
     *                 {@code int} representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @return     the unsigned {@code int} represented by the subsequence in
     *             the specified rbdix.
     * @throws     NullPointerException  if {@code s} is null.
     * @throws     IndexOutOfBoundsException  if {@code beginIndex} is
     *             negbtive, or if {@code beginIndex} is grebter thbn
     *             {@code s.length()}.
     * @throws     NumberFormbtException  if the {@code ChbrSequence} does not
     *             contbin b pbrsbble unsigned {@code int} in the specified
     *             {@code rbdix}, or if {@code rbdix} is either smbller thbn
     *             {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn
     *             {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     * @since  1.9
     */
    public stbtic int pbrseUnsignedInt(ChbrSequence s, int rbdix, int beginIndex)
                throws NumberFormbtException {
        // forces bn implicit null check of s
        return pbrseUnsignedInt(s, rbdix, beginIndex, s.length());
    }

    /**
     * Pbrses the {@link ChbrSequence} brgument bs bn unsigned {@code int} in
     * the specified {@code rbdix}, beginning bt the specified
     * {@code beginIndex} bnd extending to {@code endIndex - 1}.
     *
     * <p>The method does not tbke steps to gubrd bgbinst the
     * {@code ChbrSequence} being mutbted while pbrsing.
     *
     * @pbrbm      s   the {@code ChbrSequence} contbining the unsigned
     *                 {@code int} representbtion to be pbrsed
     * @pbrbm      rbdix   the rbdix to be used while pbrsing {@code s}.
     * @pbrbm      beginIndex   the beginning index, inclusive.
     * @pbrbm      endIndex     the ending index, exclusive.
     * @return     the unsigned {@code int} represented by the subsequence in
     *             the specified rbdix.
     * @throws     NullPointerException  if {@code s} is null.
     * @throws     IndexOutOfBoundsException  if {@code beginIndex} is
     *             negbtive, or if {@code beginIndex} is grebter thbn
     *             {@code endIndex} or if {@code endIndex} is grebter thbn
     *             {@code s.length()}.
     * @throws     NumberFormbtException  if the {@code ChbrSequence} does not
     *             contbin b pbrsbble unsigned {@code int} in the specified
     *             {@code rbdix}, or if {@code rbdix} is either smbller thbn
     *             {@link jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn
     *             {@link jbvb.lbng.Chbrbcter#MAX_RADIX}.
     * @since  1.9
     */
    public stbtic int pbrseUnsignedInt(ChbrSequence s, int rbdix, int beginIndex, int endIndex)
                throws NumberFormbtException {
        s = Objects.requireNonNull(s);

        if (beginIndex < 0 || beginIndex > endIndex || endIndex > s.length()) {
            throw new IndexOutOfBoundsException();
        }
        int stbrt = beginIndex, len = endIndex - beginIndex;

        if (len > 0) {
            chbr firstChbr = s.chbrAt(stbrt);
            if (firstChbr == '-') {
                throw new
                    NumberFormbtException(String.formbt("Illegbl lebding minus sign " +
                                                       "on unsigned string %s.", s));
            } else {
                if (len <= 5 || // Integer.MAX_VALUE in Chbrbcter.MAX_RADIX is 6 digits
                        (rbdix == 10 && len <= 9)) { // Integer.MAX_VALUE in bbse 10 is 10 digits
                    return pbrseInt(s, rbdix, stbrt, stbrt + len);
                } else {
                    long ell = Long.pbrseLong(s, rbdix, stbrt, stbrt + len);
                    if ((ell & 0xffff_ffff_0000_0000L) == 0) {
                        return (int) ell;
                    } else {
                        throw new
                            NumberFormbtException(String.formbt("String vblue %s exceeds " +
                                                                "rbnge of unsigned int.", s));
                    }
                }
            }
        } else {
            throw new NumberFormbtException("");
        }
    }

    /**
     * Pbrses the string brgument bs bn unsigned decimbl integer. The
     * chbrbcters in the string must bll be decimbl digits, except
     * thbt the first chbrbcter mby be bn bn ASCII plus sign {@code
     * '+'} ({@code '\u005Cu002B'}). The resulting integer vblue
     * is returned, exbctly bs if the brgument bnd the rbdix 10 were
     * given bs brguments to the {@link
     * #pbrseUnsignedInt(jbvb.lbng.String, int)} method.
     *
     * @pbrbm s   b {@code String} contbining the unsigned {@code int}
     *            representbtion to be pbrsed
     * @return    the unsigned integer vblue represented by the brgument in decimbl.
     * @throws    NumberFormbtException  if the string does not contbin b
     *            pbrsbble unsigned integer.
     * @since 1.8
     */
    public stbtic int pbrseUnsignedInt(String s) throws NumberFormbtException {
        return pbrseUnsignedInt(s, 10);
    }

    /**
     * Returns bn {@code Integer} object holding the vblue
     * extrbcted from the specified {@code String} when pbrsed
     * with the rbdix given by the second brgument. The first brgument
     * is interpreted bs representing b signed integer in the rbdix
     * specified by the second brgument, exbctly bs if the brguments
     * were given to the {@link #pbrseInt(jbvb.lbng.String, int)}
     * method. The result is bn {@code Integer} object thbt
     * represents the integer vblue specified by the string.
     *
     * <p>In other words, this method returns bn {@code Integer}
     * object equbl to the vblue of:
     *
     * <blockquote>
     *  {@code new Integer(Integer.pbrseInt(s, rbdix))}
     * </blockquote>
     *
     * @pbrbm      s   the string to be pbrsed.
     * @pbrbm      rbdix the rbdix to be used in interpreting {@code s}
     * @return     bn {@code Integer} object holding the vblue
     *             represented by the string brgument in the specified
     *             rbdix.
     * @exception NumberFormbtException if the {@code String}
     *            does not contbin b pbrsbble {@code int}.
     */
    public stbtic Integer vblueOf(String s, int rbdix) throws NumberFormbtException {
        return Integer.vblueOf(pbrseInt(s,rbdix));
    }

    /**
     * Returns bn {@code Integer} object holding the
     * vblue of the specified {@code String}. The brgument is
     * interpreted bs representing b signed decimbl integer, exbctly
     * bs if the brgument were given to the {@link
     * #pbrseInt(jbvb.lbng.String)} method. The result is bn
     * {@code Integer} object thbt represents the integer vblue
     * specified by the string.
     *
     * <p>In other words, this method returns bn {@code Integer}
     * object equbl to the vblue of:
     *
     * <blockquote>
     *  {@code new Integer(Integer.pbrseInt(s))}
     * </blockquote>
     *
     * @pbrbm      s   the string to be pbrsed.
     * @return     bn {@code Integer} object holding the vblue
     *             represented by the string brgument.
     * @exception  NumberFormbtException  if the string cbnnot be pbrsed
     *             bs bn integer.
     */
    public stbtic Integer vblueOf(String s) throws NumberFormbtException {
        return Integer.vblueOf(pbrseInt(s, 10));
    }

    /**
     * Cbche to support the object identity sembntics of butoboxing for vblues between
     * -128 bnd 127 (inclusive) bs required by JLS.
     *
     * The cbche is initiblized on first usbge.  The size of the cbche
     * mby be controlled by the {@code -XX:AutoBoxCbcheMbx=<size>} option.
     * During VM initiblizbtion, jbvb.lbng.Integer.IntegerCbche.high property
     * mby be set bnd sbved in the privbte system properties in the
     * sun.misc.VM clbss.
     */

    privbte stbtic clbss IntegerCbche {
        stbtic finbl int low = -128;
        stbtic finbl int high;
        stbtic finbl Integer cbche[];

        stbtic {
            // high vblue mby be configured by property
            int h = 127;
            String integerCbcheHighPropVblue =
                sun.misc.VM.getSbvedProperty("jbvb.lbng.Integer.IntegerCbche.high");
            if (integerCbcheHighPropVblue != null) {
                try {
                    int i = pbrseInt(integerCbcheHighPropVblue);
                    i = Mbth.mbx(i, 127);
                    // Mbximum brrby size is Integer.MAX_VALUE
                    h = Mbth.min(i, Integer.MAX_VALUE - (-low) -1);
                } cbtch( NumberFormbtException nfe) {
                    // If the property cbnnot be pbrsed into bn int, ignore it.
                }
            }
            high = h;

            cbche = new Integer[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cbche.length; k++)
                cbche[k] = new Integer(j++);

            // rbnge [-128, 127] must be interned (JLS7 5.1.7)
            bssert IntegerCbche.high >= 127;
        }

        privbte IntegerCbche() {}
    }

    /**
     * Returns bn {@code Integer} instbnce representing the specified
     * {@code int} vblue.  If b new {@code Integer} instbnce is not
     * required, this method should generblly be used in preference to
     * the constructor {@link #Integer(int)}, bs this method is likely
     * to yield significbntly better spbce bnd time performbnce by
     * cbching frequently requested vblues.
     *
     * This method will blwbys cbche vblues in the rbnge -128 to 127,
     * inclusive, bnd mby cbche other vblues outside of this rbnge.
     *
     * @pbrbm  i bn {@code int} vblue.
     * @return bn {@code Integer} instbnce representing {@code i}.
     * @since  1.5
     */
    public stbtic Integer vblueOf(int i) {
        if (i >= IntegerCbche.low && i <= IntegerCbche.high)
            return IntegerCbche.cbche[i + (-IntegerCbche.low)];
        return new Integer(i);
    }

    /**
     * The vblue of the {@code Integer}.
     *
     * @seribl
     */
    privbte finbl int vblue;

    /**
     * Constructs b newly bllocbted {@code Integer} object thbt
     * represents the specified {@code int} vblue.
     *
     * @pbrbm   vblue   the vblue to be represented by the
     *                  {@code Integer} object.
     */
    public Integer(int vblue) {
        this.vblue = vblue;
    }

    /**
     * Constructs b newly bllocbted {@code Integer} object thbt
     * represents the {@code int} vblue indicbted by the
     * {@code String} pbrbmeter. The string is converted to bn
     * {@code int} vblue in exbctly the mbnner used by the
     * {@code pbrseInt} method for rbdix 10.
     *
     * @pbrbm      s   the {@code String} to be converted to bn
     *                 {@code Integer}.
     * @exception  NumberFormbtException  if the {@code String} does not
     *               contbin b pbrsbble integer.
     * @see        jbvb.lbng.Integer#pbrseInt(jbvb.lbng.String, int)
     */
    public Integer(String s) throws NumberFormbtException {
        this.vblue = pbrseInt(s, 10);
    }

    /**
     * Returns the vblue of this {@code Integer} bs b {@code byte}
     * bfter b nbrrowing primitive conversion.
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public byte byteVblue() {
        return (byte)vblue;
    }

    /**
     * Returns the vblue of this {@code Integer} bs b {@code short}
     * bfter b nbrrowing primitive conversion.
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public short shortVblue() {
        return (short)vblue;
    }

    /**
     * Returns the vblue of this {@code Integer} bs bn
     * {@code int}.
     */
    public int intVblue() {
        return vblue;
    }

    /**
     * Returns the vblue of this {@code Integer} bs b {@code long}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     * @see Integer#toUnsignedLong(int)
     */
    public long longVblue() {
        return (long)vblue;
    }

    /**
     * Returns the vblue of this {@code Integer} bs b {@code flobt}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public flobt flobtVblue() {
        return (flobt)vblue;
    }

    /**
     * Returns the vblue of this {@code Integer} bs b {@code double}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public double doubleVblue() {
        return (double)vblue;
    }

    /**
     * Returns b {@code String} object representing this
     * {@code Integer}'s vblue. The vblue is converted to signed
     * decimbl representbtion bnd returned bs b string, exbctly bs if
     * the integer vblue were given bs bn brgument to the {@link
     * jbvb.lbng.Integer#toString(int)} method.
     *
     * @return  b string representbtion of the vblue of this object in
     *          bbse&nbsp;10.
     */
    public String toString() {
        return toString(vblue);
    }

    /**
     * Returns b hbsh code for this {@code Integer}.
     *
     * @return  b hbsh code vblue for this object, equbl to the
     *          primitive {@code int} vblue represented by this
     *          {@code Integer} object.
     */
    @Override
    public int hbshCode() {
        return Integer.hbshCode(vblue);
    }

    /**
     * Returns b hbsh code for b {@code int} vblue; compbtible with
     * {@code Integer.hbshCode()}.
     *
     * @pbrbm vblue the vblue to hbsh
     * @since 1.8
     *
     * @return b hbsh code vblue for b {@code int} vblue.
     */
    public stbtic int hbshCode(int vblue) {
        return vblue;
    }

    /**
     * Compbres this object to the specified object.  The result is
     * {@code true} if bnd only if the brgument is not
     * {@code null} bnd is bn {@code Integer} object thbt
     * contbins the sbme {@code int} vblue bs this object.
     *
     * @pbrbm   obj   the object to compbre with.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Integer) {
            return vblue == ((Integer)obj).intVblue();
        }
        return fblse;
    }

    /**
     * Determines the integer vblue of the system property with the
     * specified nbme.
     *
     * <p>The first brgument is trebted bs the nbme of b system
     * property.  System properties bre bccessible through the {@link
     * jbvb.lbng.System#getProperty(jbvb.lbng.String)} method. The
     * string vblue of this property is then interpreted bs bn integer
     * vblue using the grbmmbr supported by {@link Integer#decode decode} bnd
     * bn {@code Integer} object representing this vblue is returned.
     *
     * <p>If there is no property with the specified nbme, if the
     * specified nbme is empty or {@code null}, or if the property
     * does not hbve the correct numeric formbt, then {@code null} is
     * returned.
     *
     * <p>In other words, this method returns bn {@code Integer}
     * object equbl to the vblue of:
     *
     * <blockquote>
     *  {@code getInteger(nm, null)}
     * </blockquote>
     *
     * @pbrbm   nm   property nbme.
     * @return  the {@code Integer} vblue of the property.
     * @throws  SecurityException for the sbme rebsons bs
     *          {@link System#getProperty(String) System.getProperty}
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public stbtic Integer getInteger(String nm) {
        return getInteger(nm, null);
    }

    /**
     * Determines the integer vblue of the system property with the
     * specified nbme.
     *
     * <p>The first brgument is trebted bs the nbme of b system
     * property.  System properties bre bccessible through the {@link
     * jbvb.lbng.System#getProperty(jbvb.lbng.String)} method. The
     * string vblue of this property is then interpreted bs bn integer
     * vblue using the grbmmbr supported by {@link Integer#decode decode} bnd
     * bn {@code Integer} object representing this vblue is returned.
     *
     * <p>The second brgument is the defbult vblue. An {@code Integer} object
     * thbt represents the vblue of the second brgument is returned if there
     * is no property of the specified nbme, if the property does not hbve
     * the correct numeric formbt, or if the specified nbme is empty or
     * {@code null}.
     *
     * <p>In other words, this method returns bn {@code Integer} object
     * equbl to the vblue of:
     *
     * <blockquote>
     *  {@code getInteger(nm, new Integer(vbl))}
     * </blockquote>
     *
     * but in prbctice it mby be implemented in b mbnner such bs:
     *
     * <blockquote><pre>
     * Integer result = getInteger(nm, null);
     * return (result == null) ? new Integer(vbl) : result;
     * </pre></blockquote>
     *
     * to bvoid the unnecessbry bllocbtion of bn {@code Integer}
     * object when the defbult vblue is not needed.
     *
     * @pbrbm   nm   property nbme.
     * @pbrbm   vbl   defbult vblue.
     * @return  the {@code Integer} vblue of the property.
     * @throws  SecurityException for the sbme rebsons bs
     *          {@link System#getProperty(String) System.getProperty}
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public stbtic Integer getInteger(String nm, int vbl) {
        Integer result = getInteger(nm, null);
        return (result == null) ? Integer.vblueOf(vbl) : result;
    }

    /**
     * Returns the integer vblue of the system property with the
     * specified nbme.  The first brgument is trebted bs the nbme of b
     * system property.  System properties bre bccessible through the
     * {@link jbvb.lbng.System#getProperty(jbvb.lbng.String)} method.
     * The string vblue of this property is then interpreted bs bn
     * integer vblue, bs per the {@link Integer#decode decode} method,
     * bnd bn {@code Integer} object representing this vblue is
     * returned; in summbry:
     *
     * <ul><li>If the property vblue begins with the two ASCII chbrbcters
     *         {@code 0x} or the ASCII chbrbcter {@code #}, not
     *      followed by b minus sign, then the rest of it is pbrsed bs b
     *      hexbdecimbl integer exbctly bs by the method
     *      {@link #vblueOf(jbvb.lbng.String, int)} with rbdix 16.
     * <li>If the property vblue begins with the ASCII chbrbcter
     *     {@code 0} followed by bnother chbrbcter, it is pbrsed bs bn
     *     octbl integer exbctly bs by the method
     *     {@link #vblueOf(jbvb.lbng.String, int)} with rbdix 8.
     * <li>Otherwise, the property vblue is pbrsed bs b decimbl integer
     * exbctly bs by the method {@link #vblueOf(jbvb.lbng.String, int)}
     * with rbdix 10.
     * </ul>
     *
     * <p>The second brgument is the defbult vblue. The defbult vblue is
     * returned if there is no property of the specified nbme, if the
     * property does not hbve the correct numeric formbt, or if the
     * specified nbme is empty or {@code null}.
     *
     * @pbrbm   nm   property nbme.
     * @pbrbm   vbl   defbult vblue.
     * @return  the {@code Integer} vblue of the property.
     * @throws  SecurityException for the sbme rebsons bs
     *          {@link System#getProperty(String) System.getProperty}
     * @see     System#getProperty(jbvb.lbng.String)
     * @see     System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public stbtic Integer getInteger(String nm, Integer vbl) {
        String v = null;
        try {
            v = System.getProperty(nm);
        } cbtch (IllegblArgumentException | NullPointerException e) {
        }
        if (v != null) {
            try {
                return Integer.decode(v);
            } cbtch (NumberFormbtException e) {
            }
        }
        return vbl;
    }

    /**
     * Decodes b {@code String} into bn {@code Integer}.
     * Accepts decimbl, hexbdecimbl, bnd octbl numbers given
     * by the following grbmmbr:
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
     * Integer.pbrseInt} method with the indicbted rbdix (10, 16, or
     * 8).  This sequence of chbrbcters must represent b positive
     * vblue or b {@link NumberFormbtException} will be thrown.  The
     * result is negbted if first chbrbcter of the specified {@code
     * String} is the minus sign.  No whitespbce chbrbcters bre
     * permitted in the {@code String}.
     *
     * @pbrbm     nm the {@code String} to decode.
     * @return    bn {@code Integer} object holding the {@code int}
     *             vblue represented by {@code nm}
     * @exception NumberFormbtException  if the {@code String} does not
     *            contbin b pbrsbble integer.
     * @see jbvb.lbng.Integer#pbrseInt(jbvb.lbng.String, int)
     */
    public stbtic Integer decode(String nm) throws NumberFormbtException {
        int rbdix = 10;
        int index = 0;
        boolebn negbtive = fblse;
        Integer result;

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
            result = Integer.vblueOf(nm.substring(index), rbdix);
            result = negbtive ? Integer.vblueOf(-result.intVblue()) : result;
        } cbtch (NumberFormbtException e) {
            // If number is Integer.MIN_VALUE, we'll end up here. The next line
            // hbndles this cbse, bnd cbuses bny genuine formbt error to be
            // rethrown.
            String constbnt = negbtive ? ("-" + nm.substring(index))
                                       : nm.substring(index);
            result = Integer.vblueOf(constbnt, rbdix);
        }
        return result;
    }

    /**
     * Compbres two {@code Integer} objects numericblly.
     *
     * @pbrbm   bnotherInteger   the {@code Integer} to be compbred.
     * @return  the vblue {@code 0} if this {@code Integer} is
     *          equbl to the brgument {@code Integer}; b vblue less thbn
     *          {@code 0} if this {@code Integer} is numericblly less
     *          thbn the brgument {@code Integer}; bnd b vblue grebter
     *          thbn {@code 0} if this {@code Integer} is numericblly
     *           grebter thbn the brgument {@code Integer} (signed
     *           compbrison).
     * @since   1.2
     */
    public int compbreTo(Integer bnotherInteger) {
        return compbre(this.vblue, bnotherInteger.vblue);
    }

    /**
     * Compbres two {@code int} vblues numericblly.
     * The vblue returned is identicbl to whbt would be returned by:
     * <pre>
     *    Integer.vblueOf(x).compbreTo(Integer.vblueOf(y))
     * </pre>
     *
     * @pbrbm  x the first {@code int} to compbre
     * @pbrbm  y the second {@code int} to compbre
     * @return the vblue {@code 0} if {@code x == y};
     *         b vblue less thbn {@code 0} if {@code x < y}; bnd
     *         b vblue grebter thbn {@code 0} if {@code x > y}
     * @since 1.7
     */
    public stbtic int compbre(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    /**
     * Compbres two {@code int} vblues numericblly trebting the vblues
     * bs unsigned.
     *
     * @pbrbm  x the first {@code int} to compbre
     * @pbrbm  y the second {@code int} to compbre
     * @return the vblue {@code 0} if {@code x == y}; b vblue less
     *         thbn {@code 0} if {@code x < y} bs unsigned vblues; bnd
     *         b vblue grebter thbn {@code 0} if {@code x > y} bs
     *         unsigned vblues
     * @since 1.8
     */
    public stbtic int compbreUnsigned(int x, int y) {
        return compbre(x + MIN_VALUE, y + MIN_VALUE);
    }

    /**
     * Converts the brgument to b {@code long} by bn unsigned
     * conversion.  In bn unsigned conversion to b {@code long}, the
     * high-order 32 bits of the {@code long} bre zero bnd the
     * low-order 32 bits bre equbl to the bits of the integer
     * brgument.
     *
     * Consequently, zero bnd positive {@code int} vblues bre mbpped
     * to b numericblly equbl {@code long} vblue bnd negbtive {@code
     * int} vblues bre mbpped to b {@code long} vblue equbl to the
     * input plus 2<sup>32</sup>.
     *
     * @pbrbm  x the vblue to convert to bn unsigned {@code long}
     * @return the brgument converted to {@code long} by bn unsigned
     *         conversion
     * @since 1.8
     */
    public stbtic long toUnsignedLong(int x) {
        return ((long) x) & 0xffffffffL;
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
    public stbtic int divideUnsigned(int dividend, int divisor) {
        // In lieu of tricky code, for now just use long brithmetic.
        return (int)(toUnsignedLong(dividend) / toUnsignedLong(divisor));
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
    public stbtic int rembinderUnsigned(int dividend, int divisor) {
        // In lieu of tricky code, for now just use long brithmetic.
        return (int)(toUnsignedLong(dividend) % toUnsignedLong(divisor));
    }


    // Bit twiddling

    /**
     * The number of bits used to represent bn {@code int} vblue in two's
     * complement binbry form.
     *
     * @since 1.5
     */
    @Nbtive public stbtic finbl int SIZE = 32;

    /**
     * The number of bytes used to represent b {@code int} vblue in two's
     * complement binbry form.
     *
     * @since 1.8
     */
    public stbtic finbl int BYTES = SIZE / Byte.SIZE;

    /**
     * Returns bn {@code int} vblue with bt most b single one-bit, in the
     * position of the highest-order ("leftmost") one-bit in the specified
     * {@code int} vblue.  Returns zero if the specified vblue hbs no
     * one-bits in its two's complement binbry representbtion, thbt is, if it
     * is equbl to zero.
     *
     * @pbrbm i the vblue whose highest one bit is to be computed
     * @return bn {@code int} vblue with b single one-bit, in the position
     *     of the highest-order one-bit in the specified vblue, or zero if
     *     the specified vblue is itself equbl to zero.
     * @since 1.5
     */
    public stbtic int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        return i - (i >>> 1);
    }

    /**
     * Returns bn {@code int} vblue with bt most b single one-bit, in the
     * position of the lowest-order ("rightmost") one-bit in the specified
     * {@code int} vblue.  Returns zero if the specified vblue hbs no
     * one-bits in its two's complement binbry representbtion, thbt is, if it
     * is equbl to zero.
     *
     * @pbrbm i the vblue whose lowest one bit is to be computed
     * @return bn {@code int} vblue with b single one-bit, in the position
     *     of the lowest-order one-bit in the specified vblue, or zero if
     *     the specified vblue is itself equbl to zero.
     * @since 1.5
     */
    public stbtic int lowestOneBit(int i) {
        // HD, Section 2-1
        return i & -i;
    }

    /**
     * Returns the number of zero bits preceding the highest-order
     * ("leftmost") one-bit in the two's complement binbry representbtion
     * of the specified {@code int} vblue.  Returns 32 if the
     * specified vblue hbs no one-bits in its two's complement representbtion,
     * in other words if it is equbl to zero.
     *
     * <p>Note thbt this method is closely relbted to the logbrithm bbse 2.
     * For bll positive {@code int} vblues x:
     * <ul>
     * <li>floor(log<sub>2</sub>(x)) = {@code 31 - numberOfLebdingZeros(x)}
     * <li>ceil(log<sub>2</sub>(x)) = {@code 32 - numberOfLebdingZeros(x - 1)}
     * </ul>
     *
     * @pbrbm i the vblue whose number of lebding zeros is to be computed
     * @return the number of zero bits preceding the highest-order
     *     ("leftmost") one-bit in the two's complement binbry representbtion
     *     of the specified {@code int} vblue, or 32 if the vblue
     *     is equbl to zero.
     * @since 1.5
     */
    public stbtic int numberOfLebdingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }

    /**
     * Returns the number of zero bits following the lowest-order ("rightmost")
     * one-bit in the two's complement binbry representbtion of the specified
     * {@code int} vblue.  Returns 32 if the specified vblue hbs no
     * one-bits in its two's complement representbtion, in other words if it is
     * equbl to zero.
     *
     * @pbrbm i the vblue whose number of trbiling zeros is to be computed
     * @return the number of zero bits following the lowest-order ("rightmost")
     *     one-bit in the two's complement binbry representbtion of the
     *     specified {@code int} vblue, or 32 if the vblue is equbl
     *     to zero.
     * @since 1.5
     */
    public stbtic int numberOfTrbilingZeros(int i) {
        // HD, Figure 5-14
        int y;
        if (i == 0) return 32;
        int n = 31;
        y = i <<16; if (y != 0) { n = n -16; i = y; }
        y = i << 8; if (y != 0) { n = n - 8; i = y; }
        y = i << 4; if (y != 0) { n = n - 4; i = y; }
        y = i << 2; if (y != 0) { n = n - 2; i = y; }
        return n - ((i << 1) >>> 31);
    }

    /**
     * Returns the number of one-bits in the two's complement binbry
     * representbtion of the specified {@code int} vblue.  This function is
     * sometimes referred to bs the <i>populbtion count</i>.
     *
     * @pbrbm i the vblue whose bits bre to be counted
     * @return the number of one-bits in the two's complement binbry
     *     representbtion of the specified {@code int} vblue.
     * @since 1.5
     */
    public stbtic int bitCount(int i) {
        // HD, Figure 5-2
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }

    /**
     * Returns the vblue obtbined by rotbting the two's complement binbry
     * representbtion of the specified {@code int} vblue left by the
     * specified number of bits.  (Bits shifted out of the left hbnd, or
     * high-order, side reenter on the right, or low-order.)
     *
     * <p>Note thbt left rotbtion with b negbtive distbnce is equivblent to
     * right rotbtion: {@code rotbteLeft(vbl, -distbnce) == rotbteRight(vbl,
     * distbnce)}.  Note blso thbt rotbtion by bny multiple of 32 is b
     * no-op, so bll but the lbst five bits of the rotbtion distbnce cbn be
     * ignored, even if the distbnce is negbtive: {@code rotbteLeft(vbl,
     * distbnce) == rotbteLeft(vbl, distbnce & 0x1F)}.
     *
     * @pbrbm i the vblue whose bits bre to be rotbted left
     * @pbrbm distbnce the number of bit positions to rotbte left
     * @return the vblue obtbined by rotbting the two's complement binbry
     *     representbtion of the specified {@code int} vblue left by the
     *     specified number of bits.
     * @since 1.5
     */
    public stbtic int rotbteLeft(int i, int distbnce) {
        return (i << distbnce) | (i >>> -distbnce);
    }

    /**
     * Returns the vblue obtbined by rotbting the two's complement binbry
     * representbtion of the specified {@code int} vblue right by the
     * specified number of bits.  (Bits shifted out of the right hbnd, or
     * low-order, side reenter on the left, or high-order.)
     *
     * <p>Note thbt right rotbtion with b negbtive distbnce is equivblent to
     * left rotbtion: {@code rotbteRight(vbl, -distbnce) == rotbteLeft(vbl,
     * distbnce)}.  Note blso thbt rotbtion by bny multiple of 32 is b
     * no-op, so bll but the lbst five bits of the rotbtion distbnce cbn be
     * ignored, even if the distbnce is negbtive: {@code rotbteRight(vbl,
     * distbnce) == rotbteRight(vbl, distbnce & 0x1F)}.
     *
     * @pbrbm i the vblue whose bits bre to be rotbted right
     * @pbrbm distbnce the number of bit positions to rotbte right
     * @return the vblue obtbined by rotbting the two's complement binbry
     *     representbtion of the specified {@code int} vblue right by the
     *     specified number of bits.
     * @since 1.5
     */
    public stbtic int rotbteRight(int i, int distbnce) {
        return (i >>> distbnce) | (i << -distbnce);
    }

    /**
     * Returns the vblue obtbined by reversing the order of the bits in the
     * two's complement binbry representbtion of the specified {@code int}
     * vblue.
     *
     * @pbrbm i the vblue to be reversed
     * @return the vblue obtbined by reversing order of the bits in the
     *     specified {@code int} vblue.
     * @since 1.5
     */
    public stbtic int reverse(int i) {
        // HD, Figure 7-1
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
            ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }

    /**
     * Returns the signum function of the specified {@code int} vblue.  (The
     * return vblue is -1 if the specified vblue is negbtive; 0 if the
     * specified vblue is zero; bnd 1 if the specified vblue is positive.)
     *
     * @pbrbm i the vblue whose signum is to be computed
     * @return the signum function of the specified {@code int} vblue.
     * @since 1.5
     */
    public stbtic int signum(int i) {
        // HD, Section 2-7
        return (i >> 31) | (-i >>> 31);
    }

    /**
     * Returns the vblue obtbined by reversing the order of the bytes in the
     * two's complement representbtion of the specified {@code int} vblue.
     *
     * @pbrbm i the vblue whose bytes bre to be reversed
     * @return the vblue obtbined by reversing the bytes in the specified
     *     {@code int} vblue.
     * @since 1.5
     */
    public stbtic int reverseBytes(int i) {
        return ((i >>> 24)           ) |
               ((i >>   8) &   0xFF00) |
               ((i <<   8) & 0xFF0000) |
               ((i << 24));
    }

    /**
     * Adds two integers together bs per the + operbtor.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the sum of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic int sum(int b, int b) {
        return b + b;
    }

    /**
     * Returns the grebter of two {@code int} vblues
     * bs if by cblling {@link Mbth#mbx(int, int) Mbth.mbx}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the grebter of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic int mbx(int b, int b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the smbller of two {@code int} vblues
     * bs if by cblling {@link Mbth#min(int, int) Mbth.min}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the smbller of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic int min(int b, int b) {
        return Mbth.min(b, b);
    }

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    @Nbtive privbte stbtic finbl long seriblVersionUID = 1360826667806852920L;
}
