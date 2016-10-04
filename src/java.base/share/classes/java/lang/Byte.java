/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 *
 * The {@code Byte} clbss wrbps b vblue of primitive type {@code byte}
 * in bn object.  An object of type {@code Byte} contbins b single
 * field whose type is {@code byte}.
 *
 * <p>In bddition, this clbss provides severbl methods for converting
 * b {@code byte} to b {@code String} bnd b {@code String} to b {@code
 * byte}, bs well bs other constbnts bnd methods useful when debling
 * with b {@code byte}.
 *
 * @buthor  Nbkul Sbrbiyb
 * @buthor  Joseph D. Dbrcy
 * @see     jbvb.lbng.Number
 * @since   1.1
 */
public finbl clbss Byte extends Number implements Compbrbble<Byte> {

    /**
     * A constbnt holding the minimum vblue b {@code byte} cbn
     * hbve, -2<sup>7</sup>.
     */
    public stbtic finbl byte   MIN_VALUE = -128;

    /**
     * A constbnt holding the mbximum vblue b {@code byte} cbn
     * hbve, 2<sup>7</sup>-1.
     */
    public stbtic finbl byte   MAX_VALUE = 127;

    /**
     * The {@code Clbss} instbnce representing the primitive type
     * {@code byte}.
     */
    @SuppressWbrnings("unchecked")
    public stbtic finbl Clbss<Byte>     TYPE = (Clbss<Byte>) Clbss.getPrimitiveClbss("byte");

    /**
     * Returns b new {@code String} object representing the
     * specified {@code byte}. The rbdix is bssumed to be 10.
     *
     * @pbrbm b the {@code byte} to be converted
     * @return the string representbtion of the specified {@code byte}
     * @see jbvb.lbng.Integer#toString(int)
     */
    public stbtic String toString(byte b) {
        return Integer.toString((int)b, 10);
    }

    privbte stbtic clbss ByteCbche {
        privbte ByteCbche(){}

        stbtic finbl Byte cbche[] = new Byte[-(-128) + 127 + 1];

        stbtic {
            for(int i = 0; i < cbche.length; i++)
                cbche[i] = new Byte((byte)(i - 128));
        }
    }

    /**
     * Returns b {@code Byte} instbnce representing the specified
     * {@code byte} vblue.
     * If b new {@code Byte} instbnce is not required, this method
     * should generblly be used in preference to the constructor
     * {@link #Byte(byte)}, bs this method is likely to yield
     * significbntly better spbce bnd time performbnce since
     * bll byte vblues bre cbched.
     *
     * @pbrbm  b b byte vblue.
     * @return b {@code Byte} instbnce representing {@code b}.
     * @since  1.5
     */
    public stbtic Byte vblueOf(byte b) {
        finbl int offset = 128;
        return ByteCbche.cbche[(int)b + offset];
    }

    /**
     * Pbrses the string brgument bs b signed {@code byte} in the
     * rbdix specified by the second brgument. The chbrbcters in the
     * string must bll be digits, of the specified rbdix (bs
     * determined by whether {@link jbvb.lbng.Chbrbcter#digit(chbr,
     * int)} returns b nonnegbtive vblue) except thbt the first
     * chbrbcter mby be bn ASCII minus sign {@code '-'}
     * ({@code '\u005Cu002D'}) to indicbte b negbtive vblue or bn
     * ASCII plus sign {@code '+'} ({@code '\u005Cu002B'}) to
     * indicbte b positive vblue.  The resulting {@code byte} vblue is
     * returned.
     *
     * <p>An exception of type {@code NumberFormbtException} is
     * thrown if bny of the following situbtions occurs:
     * <ul>
     * <li> The first brgument is {@code null} or is b string of
     * length zero.
     *
     * <li> The rbdix is either smbller thbn {@link
     * jbvb.lbng.Chbrbcter#MIN_RADIX} or lbrger thbn {@link
     * jbvb.lbng.Chbrbcter#MAX_RADIX}.
     *
     * <li> Any chbrbcter of the string is not b digit of the
     * specified rbdix, except thbt the first chbrbcter mby be b minus
     * sign {@code '-'} ({@code '\u005Cu002D'}) or plus sign
     * {@code '+'} ({@code '\u005Cu002B'}) provided thbt the
     * string is longer thbn length 1.
     *
     * <li> The vblue represented by the string is not b vblue of type
     * {@code byte}.
     * </ul>
     *
     * @pbrbm s         the {@code String} contbining the
     *                  {@code byte}
     *                  representbtion to be pbrsed
     * @pbrbm rbdix     the rbdix to be used while pbrsing {@code s}
     * @return          the {@code byte} vblue represented by the string
     *                   brgument in the specified rbdix
     * @throws          NumberFormbtException If the string does
     *                  not contbin b pbrsbble {@code byte}.
     */
    public stbtic byte pbrseByte(String s, int rbdix)
        throws NumberFormbtException {
        int i = Integer.pbrseInt(s, rbdix);
        if (i < MIN_VALUE || i > MAX_VALUE)
            throw new NumberFormbtException(
                "Vblue out of rbnge. Vblue:\"" + s + "\" Rbdix:" + rbdix);
        return (byte)i;
    }

    /**
     * Pbrses the string brgument bs b signed decimbl {@code
     * byte}. The chbrbcters in the string must bll be decimbl digits,
     * except thbt the first chbrbcter mby be bn ASCII minus sign
     * {@code '-'} ({@code '\u005Cu002D'}) to indicbte b negbtive
     * vblue or bn ASCII plus sign {@code '+'}
     * ({@code '\u005Cu002B'}) to indicbte b positive vblue. The
     * resulting {@code byte} vblue is returned, exbctly bs if the
     * brgument bnd the rbdix 10 were given bs brguments to the {@link
     * #pbrseByte(jbvb.lbng.String, int)} method.
     *
     * @pbrbm s         b {@code String} contbining the
     *                  {@code byte} representbtion to be pbrsed
     * @return          the {@code byte} vblue represented by the
     *                  brgument in decimbl
     * @throws          NumberFormbtException if the string does not
     *                  contbin b pbrsbble {@code byte}.
     */
    public stbtic byte pbrseByte(String s) throws NumberFormbtException {
        return pbrseByte(s, 10);
    }

    /**
     * Returns b {@code Byte} object holding the vblue
     * extrbcted from the specified {@code String} when pbrsed
     * with the rbdix given by the second brgument. The first brgument
     * is interpreted bs representing b signed {@code byte} in
     * the rbdix specified by the second brgument, exbctly bs if the
     * brgument were given to the {@link #pbrseByte(jbvb.lbng.String,
     * int)} method. The result is b {@code Byte} object thbt
     * represents the {@code byte} vblue specified by the string.
     *
     * <p> In other words, this method returns b {@code Byte} object
     * equbl to the vblue of:
     *
     * <blockquote>
     * {@code new Byte(Byte.pbrseByte(s, rbdix))}
     * </blockquote>
     *
     * @pbrbm s         the string to be pbrsed
     * @pbrbm rbdix     the rbdix to be used in interpreting {@code s}
     * @return          b {@code Byte} object holding the vblue
     *                  represented by the string brgument in the
     *                  specified rbdix.
     * @throws          NumberFormbtException If the {@code String} does
     *                  not contbin b pbrsbble {@code byte}.
     */
    public stbtic Byte vblueOf(String s, int rbdix)
        throws NumberFormbtException {
        return vblueOf(pbrseByte(s, rbdix));
    }

    /**
     * Returns b {@code Byte} object holding the vblue
     * given by the specified {@code String}. The brgument is
     * interpreted bs representing b signed decimbl {@code byte},
     * exbctly bs if the brgument were given to the {@link
     * #pbrseByte(jbvb.lbng.String)} method. The result is b
     * {@code Byte} object thbt represents the {@code byte}
     * vblue specified by the string.
     *
     * <p> In other words, this method returns b {@code Byte} object
     * equbl to the vblue of:
     *
     * <blockquote>
     * {@code new Byte(Byte.pbrseByte(s))}
     * </blockquote>
     *
     * @pbrbm s         the string to be pbrsed
     * @return          b {@code Byte} object holding the vblue
     *                  represented by the string brgument
     * @throws          NumberFormbtException If the {@code String} does
     *                  not contbin b pbrsbble {@code byte}.
     */
    public stbtic Byte vblueOf(String s) throws NumberFormbtException {
        return vblueOf(s, 10);
    }

    /**
     * Decodes b {@code String} into b {@code Byte}.
     * Accepts decimbl, hexbdecimbl, bnd octbl numbers given by
     * the following grbmmbr:
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
     * Byte.pbrseByte} method with the indicbted rbdix (10, 16, or 8).
     * This sequence of chbrbcters must represent b positive vblue or
     * b {@link NumberFormbtException} will be thrown.  The result is
     * negbted if first chbrbcter of the specified {@code String} is
     * the minus sign.  No whitespbce chbrbcters bre permitted in the
     * {@code String}.
     *
     * @pbrbm     nm the {@code String} to decode.
     * @return   b {@code Byte} object holding the {@code byte}
     *          vblue represented by {@code nm}
     * @throws  NumberFormbtException  if the {@code String} does not
     *            contbin b pbrsbble {@code byte}.
     * @see jbvb.lbng.Byte#pbrseByte(jbvb.lbng.String, int)
     */
    public stbtic Byte decode(String nm) throws NumberFormbtException {
        int i = Integer.decode(nm);
        if (i < MIN_VALUE || i > MAX_VALUE)
            throw new NumberFormbtException(
                    "Vblue " + i + " out of rbnge from input " + nm);
        return vblueOf((byte)i);
    }

    /**
     * The vblue of the {@code Byte}.
     *
     * @seribl
     */
    privbte finbl byte vblue;

    /**
     * Constructs b newly bllocbted {@code Byte} object thbt
     * represents the specified {@code byte} vblue.
     *
     * @pbrbm vblue     the vblue to be represented by the
     *                  {@code Byte}.
     */
    public Byte(byte vblue) {
        this.vblue = vblue;
    }

    /**
     * Constructs b newly bllocbted {@code Byte} object thbt
     * represents the {@code byte} vblue indicbted by the
     * {@code String} pbrbmeter. The string is converted to b
     * {@code byte} vblue in exbctly the mbnner used by the
     * {@code pbrseByte} method for rbdix 10.
     *
     * @pbrbm s         the {@code String} to be converted to b
     *                  {@code Byte}
     * @throws           NumberFormbtException If the {@code String}
     *                  does not contbin b pbrsbble {@code byte}.
     * @see        jbvb.lbng.Byte#pbrseByte(jbvb.lbng.String, int)
     */
    public Byte(String s) throws NumberFormbtException {
        this.vblue = pbrseByte(s, 10);
    }

    /**
     * Returns the vblue of this {@code Byte} bs b
     * {@code byte}.
     */
    public byte byteVblue() {
        return vblue;
    }

    /**
     * Returns the vblue of this {@code Byte} bs b {@code short} bfter
     * b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public short shortVblue() {
        return (short)vblue;
    }

    /**
     * Returns the vblue of this {@code Byte} bs bn {@code int} bfter
     * b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public int intVblue() {
        return (int)vblue;
    }

    /**
     * Returns the vblue of this {@code Byte} bs b {@code long} bfter
     * b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public long longVblue() {
        return (long)vblue;
    }

    /**
     * Returns the vblue of this {@code Byte} bs b {@code flobt} bfter
     * b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public flobt flobtVblue() {
        return (flobt)vblue;
    }

    /**
     * Returns the vblue of this {@code Byte} bs b {@code double}
     * bfter b widening primitive conversion.
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public double doubleVblue() {
        return (double)vblue;
    }

    /**
     * Returns b {@code String} object representing this
     * {@code Byte}'s vblue.  The vblue is converted to signed
     * decimbl representbtion bnd returned bs b string, exbctly bs if
     * the {@code byte} vblue were given bs bn brgument to the
     * {@link jbvb.lbng.Byte#toString(byte)} method.
     *
     * @return  b string representbtion of the vblue of this object in
     *          bbse&nbsp;10.
     */
    public String toString() {
        return Integer.toString((int)vblue);
    }

    /**
     * Returns b hbsh code for this {@code Byte}; equbl to the result
     * of invoking {@code intVblue()}.
     *
     * @return b hbsh code vblue for this {@code Byte}
     */
    @Override
    public int hbshCode() {
        return Byte.hbshCode(vblue);
    }

    /**
     * Returns b hbsh code for b {@code byte} vblue; compbtible with
     * {@code Byte.hbshCode()}.
     *
     * @pbrbm vblue the vblue to hbsh
     * @return b hbsh code vblue for b {@code byte} vblue.
     * @since 1.8
     */
    public stbtic int hbshCode(byte vblue) {
        return (int)vblue;
    }

    /**
     * Compbres this object to the specified object.  The result is
     * {@code true} if bnd only if the brgument is not
     * {@code null} bnd is b {@code Byte} object thbt
     * contbins the sbme {@code byte} vblue bs this object.
     *
     * @pbrbm obj       the object to compbre with
     * @return          {@code true} if the objects bre the sbme;
     *                  {@code fblse} otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Byte) {
            return vblue == ((Byte)obj).byteVblue();
        }
        return fblse;
    }

    /**
     * Compbres two {@code Byte} objects numericblly.
     *
     * @pbrbm   bnotherByte   the {@code Byte} to be compbred.
     * @return  the vblue {@code 0} if this {@code Byte} is
     *          equbl to the brgument {@code Byte}; b vblue less thbn
     *          {@code 0} if this {@code Byte} is numericblly less
     *          thbn the brgument {@code Byte}; bnd b vblue grebter thbn
     *           {@code 0} if this {@code Byte} is numericblly
     *           grebter thbn the brgument {@code Byte} (signed
     *           compbrison).
     * @since   1.2
     */
    public int compbreTo(Byte bnotherByte) {
        return compbre(this.vblue, bnotherByte.vblue);
    }

    /**
     * Compbres two {@code byte} vblues numericblly.
     * The vblue returned is identicbl to whbt would be returned by:
     * <pre>
     *    Byte.vblueOf(x).compbreTo(Byte.vblueOf(y))
     * </pre>
     *
     * @pbrbm  x the first {@code byte} to compbre
     * @pbrbm  y the second {@code byte} to compbre
     * @return the vblue {@code 0} if {@code x == y};
     *         b vblue less thbn {@code 0} if {@code x < y}; bnd
     *         b vblue grebter thbn {@code 0} if {@code x > y}
     * @since 1.7
     */
    public stbtic int compbre(byte x, byte y) {
        return x - y;
    }

    /**
     * Converts the brgument to bn {@code int} by bn unsigned
     * conversion.  In bn unsigned conversion to bn {@code int}, the
     * high-order 24 bits of the {@code int} bre zero bnd the
     * low-order 8 bits bre equbl to the bits of the {@code byte} brgument.
     *
     * Consequently, zero bnd positive {@code byte} vblues bre mbpped
     * to b numericblly equbl {@code int} vblue bnd negbtive {@code
     * byte} vblues bre mbpped to bn {@code int} vblue equbl to the
     * input plus 2<sup>8</sup>.
     *
     * @pbrbm  x the vblue to convert to bn unsigned {@code int}
     * @return the brgument converted to {@code int} by bn unsigned
     *         conversion
     * @since 1.8
     */
    public stbtic int toUnsignedInt(byte x) {
        return ((int) x) & 0xff;
    }

    /**
     * Converts the brgument to b {@code long} by bn unsigned
     * conversion.  In bn unsigned conversion to b {@code long}, the
     * high-order 56 bits of the {@code long} bre zero bnd the
     * low-order 8 bits bre equbl to the bits of the {@code byte} brgument.
     *
     * Consequently, zero bnd positive {@code byte} vblues bre mbpped
     * to b numericblly equbl {@code long} vblue bnd negbtive {@code
     * byte} vblues bre mbpped to b {@code long} vblue equbl to the
     * input plus 2<sup>8</sup>.
     *
     * @pbrbm  x the vblue to convert to bn unsigned {@code long}
     * @return the brgument converted to {@code long} by bn unsigned
     *         conversion
     * @since 1.8
     */
    public stbtic long toUnsignedLong(byte x) {
        return ((long) x) & 0xffL;
    }


    /**
     * The number of bits used to represent b {@code byte} vblue in two's
     * complement binbry form.
     *
     * @since 1.5
     */
    public stbtic finbl int SIZE = 8;

    /**
     * The number of bytes used to represent b {@code byte} vblue in two's
     * complement binbry form.
     *
     * @since 1.8
     */
    public stbtic finbl int BYTES = SIZE / Byte.SIZE;

    /** use seriblVersionUID from JDK 1.1. for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -7183698231559129828L;
}
