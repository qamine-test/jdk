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

import sun.misc.FlobtingDecimbl;
import sun.misc.FlobtConsts;
import sun.misc.DoubleConsts;

/**
 * The {@code Flobt} clbss wrbps b vblue of primitive type
 * {@code flobt} in bn object. An object of type
 * {@code Flobt} contbins b single field whose type is
 * {@code flobt}.
 *
 * <p>In bddition, this clbss provides severbl methods for converting b
 * {@code flobt} to b {@code String} bnd b
 * {@code String} to b {@code flobt}, bs well bs other
 * constbnts bnd methods useful when debling with b
 * {@code flobt}.
 *
 * @buthor  Lee Boynton
 * @buthor  Arthur vbn Hoff
 * @buthor  Joseph D. Dbrcy
 * @since 1.0
 */
public finbl clbss Flobt extends Number implements Compbrbble<Flobt> {
    /**
     * A constbnt holding the positive infinity of type
     * {@code flobt}. It is equbl to the vblue returned by
     * {@code Flobt.intBitsToFlobt(0x7f800000)}.
     */
    public stbtic finbl flobt POSITIVE_INFINITY = 1.0f / 0.0f;

    /**
     * A constbnt holding the negbtive infinity of type
     * {@code flobt}. It is equbl to the vblue returned by
     * {@code Flobt.intBitsToFlobt(0xff800000)}.
     */
    public stbtic finbl flobt NEGATIVE_INFINITY = -1.0f / 0.0f;

    /**
     * A constbnt holding b Not-b-Number (NbN) vblue of type
     * {@code flobt}.  It is equivblent to the vblue returned by
     * {@code Flobt.intBitsToFlobt(0x7fc00000)}.
     */
    public stbtic finbl flobt NbN = 0.0f / 0.0f;

    /**
     * A constbnt holding the lbrgest positive finite vblue of type
     * {@code flobt}, (2-2<sup>-23</sup>)&middot;2<sup>127</sup>.
     * It is equbl to the hexbdecimbl flobting-point literbl
     * {@code 0x1.fffffeP+127f} bnd blso equbl to
     * {@code Flobt.intBitsToFlobt(0x7f7fffff)}.
     */
    public stbtic finbl flobt MAX_VALUE = 0x1.fffffeP+127f; // 3.4028235e+38f

    /**
     * A constbnt holding the smbllest positive normbl vblue of type
     * {@code flobt}, 2<sup>-126</sup>.  It is equbl to the
     * hexbdecimbl flobting-point literbl {@code 0x1.0p-126f} bnd blso
     * equbl to {@code Flobt.intBitsToFlobt(0x00800000)}.
     *
     * @since 1.6
     */
    public stbtic finbl flobt MIN_NORMAL = 0x1.0p-126f; // 1.17549435E-38f

    /**
     * A constbnt holding the smbllest positive nonzero vblue of type
     * {@code flobt}, 2<sup>-149</sup>. It is equbl to the
     * hexbdecimbl flobting-point literbl {@code 0x0.000002P-126f}
     * bnd blso equbl to {@code Flobt.intBitsToFlobt(0x1)}.
     */
    public stbtic finbl flobt MIN_VALUE = 0x0.000002P-126f; // 1.4e-45f

    /**
     * Mbximum exponent b finite {@code flobt} vbribble mby hbve.  It
     * is equbl to the vblue returned by {@code
     * Mbth.getExponent(Flobt.MAX_VALUE)}.
     *
     * @since 1.6
     */
    public stbtic finbl int MAX_EXPONENT = 127;

    /**
     * Minimum exponent b normblized {@code flobt} vbribble mby hbve.
     * It is equbl to the vblue returned by {@code
     * Mbth.getExponent(Flobt.MIN_NORMAL)}.
     *
     * @since 1.6
     */
    public stbtic finbl int MIN_EXPONENT = -126;

    /**
     * The number of bits used to represent b {@code flobt} vblue.
     *
     * @since 1.5
     */
    public stbtic finbl int SIZE = 32;

    /**
     * The number of bytes used to represent b {@code flobt} vblue.
     *
     * @since 1.8
     */
    public stbtic finbl int BYTES = SIZE / Byte.SIZE;

    /**
     * The {@code Clbss} instbnce representing the primitive type
     * {@code flobt}.
     *
     * @since 1.1
     */
    @SuppressWbrnings("unchecked")
    public stbtic finbl Clbss<Flobt> TYPE = (Clbss<Flobt>) Clbss.getPrimitiveClbss("flobt");

    /**
     * Returns b string representbtion of the {@code flobt}
     * brgument. All chbrbcters mentioned below bre ASCII chbrbcters.
     * <ul>
     * <li>If the brgument is NbN, the result is the string
     * "{@code NbN}".
     * <li>Otherwise, the result is b string thbt represents the sign bnd
     *     mbgnitude (bbsolute vblue) of the brgument. If the sign is
     *     negbtive, the first chbrbcter of the result is
     *     '{@code -}' ({@code '\u005Cu002D'}); if the sign is
     *     positive, no sign chbrbcter bppebrs in the result. As for
     *     the mbgnitude <i>m</i>:
     * <ul>
     * <li>If <i>m</i> is infinity, it is represented by the chbrbcters
     *     {@code "Infinity"}; thus, positive infinity produces
     *     the result {@code "Infinity"} bnd negbtive infinity
     *     produces the result {@code "-Infinity"}.
     * <li>If <i>m</i> is zero, it is represented by the chbrbcters
     *     {@code "0.0"}; thus, negbtive zero produces the result
     *     {@code "-0.0"} bnd positive zero produces the result
     *     {@code "0.0"}.
     * <li> If <i>m</i> is grebter thbn or equbl to 10<sup>-3</sup> but
     *      less thbn 10<sup>7</sup>, then it is represented bs the
     *      integer pbrt of <i>m</i>, in decimbl form with no lebding
     *      zeroes, followed by '{@code .}'
     *      ({@code '\u005Cu002E'}), followed by one or more
     *      decimbl digits representing the frbctionbl pbrt of
     *      <i>m</i>.
     * <li> If <i>m</i> is less thbn 10<sup>-3</sup> or grebter thbn or
     *      equbl to 10<sup>7</sup>, then it is represented in
     *      so-cblled "computerized scientific notbtion." Let <i>n</i>
     *      be the unique integer such thbt 10<sup><i>n</i> </sup>&le;
     *      <i>m</i> {@literbl <} 10<sup><i>n</i>+1</sup>; then let <i>b</i>
     *      be the mbthembticblly exbct quotient of <i>m</i> bnd
     *      10<sup><i>n</i></sup> so thbt 1 &le; <i>b</i> {@literbl <} 10.
     *      The mbgnitude is then represented bs the integer pbrt of
     *      <i>b</i>, bs b single decimbl digit, followed by
     *      '{@code .}' ({@code '\u005Cu002E'}), followed by
     *      decimbl digits representing the frbctionbl pbrt of
     *      <i>b</i>, followed by the letter '{@code E}'
     *      ({@code '\u005Cu0045'}), followed by b representbtion
     *      of <i>n</i> bs b decimbl integer, bs produced by the
     *      method {@link jbvb.lbng.Integer#toString(int)}.
     *
     * </ul>
     * </ul>
     * How mbny digits must be printed for the frbctionbl pbrt of
     * <i>m</i> or <i>b</i>? There must be bt lebst one digit
     * to represent the frbctionbl pbrt, bnd beyond thbt bs mbny, but
     * only bs mbny, more digits bs bre needed to uniquely distinguish
     * the brgument vblue from bdjbcent vblues of type
     * {@code flobt}. Thbt is, suppose thbt <i>x</i> is the
     * exbct mbthembticbl vblue represented by the decimbl
     * representbtion produced by this method for b finite nonzero
     * brgument <i>f</i>. Then <i>f</i> must be the {@code flobt}
     * vblue nebrest to <i>x</i>; or, if two {@code flobt} vblues bre
     * equblly close to <i>x</i>, then <i>f</i> must be one of
     * them bnd the lebst significbnt bit of the significbnd of
     * <i>f</i> must be {@code 0}.
     *
     * <p>To crebte locblized string representbtions of b flobting-point
     * vblue, use subclbsses of {@link jbvb.text.NumberFormbt}.
     *
     * @pbrbm   f   the flobt to be converted.
     * @return b string representbtion of the brgument.
     */
    public stbtic String toString(flobt f) {
        return FlobtingDecimbl.toJbvbFormbtString(f);
    }

    /**
     * Returns b hexbdecimbl string representbtion of the
     * {@code flobt} brgument. All chbrbcters mentioned below bre
     * ASCII chbrbcters.
     *
     * <ul>
     * <li>If the brgument is NbN, the result is the string
     *     "{@code NbN}".
     * <li>Otherwise, the result is b string thbt represents the sign bnd
     * mbgnitude (bbsolute vblue) of the brgument. If the sign is negbtive,
     * the first chbrbcter of the result is '{@code -}'
     * ({@code '\u005Cu002D'}); if the sign is positive, no sign chbrbcter
     * bppebrs in the result. As for the mbgnitude <i>m</i>:
     *
     * <ul>
     * <li>If <i>m</i> is infinity, it is represented by the string
     * {@code "Infinity"}; thus, positive infinity produces the
     * result {@code "Infinity"} bnd negbtive infinity produces
     * the result {@code "-Infinity"}.
     *
     * <li>If <i>m</i> is zero, it is represented by the string
     * {@code "0x0.0p0"}; thus, negbtive zero produces the result
     * {@code "-0x0.0p0"} bnd positive zero produces the result
     * {@code "0x0.0p0"}.
     *
     * <li>If <i>m</i> is b {@code flobt} vblue with b
     * normblized representbtion, substrings bre used to represent the
     * significbnd bnd exponent fields.  The significbnd is
     * represented by the chbrbcters {@code "0x1."}
     * followed by b lowercbse hexbdecimbl representbtion of the rest
     * of the significbnd bs b frbction.  Trbiling zeros in the
     * hexbdecimbl representbtion bre removed unless bll the digits
     * bre zero, in which cbse b single zero is used. Next, the
     * exponent is represented by {@code "p"} followed
     * by b decimbl string of the unbibsed exponent bs if produced by
     * b cbll to {@link Integer#toString(int) Integer.toString} on the
     * exponent vblue.
     *
     * <li>If <i>m</i> is b {@code flobt} vblue with b subnormbl
     * representbtion, the significbnd is represented by the
     * chbrbcters {@code "0x0."} followed by b
     * hexbdecimbl representbtion of the rest of the significbnd bs b
     * frbction.  Trbiling zeros in the hexbdecimbl representbtion bre
     * removed. Next, the exponent is represented by
     * {@code "p-126"}.  Note thbt there must be bt
     * lebst one nonzero digit in b subnormbl significbnd.
     *
     * </ul>
     *
     * </ul>
     *
     * <tbble border>
     * <cbption>Exbmples</cbption>
     * <tr><th>Flobting-point Vblue</th><th>Hexbdecimbl String</th>
     * <tr><td>{@code 1.0}</td> <td>{@code 0x1.0p0}</td>
     * <tr><td>{@code -1.0}</td>        <td>{@code -0x1.0p0}</td>
     * <tr><td>{@code 2.0}</td> <td>{@code 0x1.0p1}</td>
     * <tr><td>{@code 3.0}</td> <td>{@code 0x1.8p1}</td>
     * <tr><td>{@code 0.5}</td> <td>{@code 0x1.0p-1}</td>
     * <tr><td>{@code 0.25}</td>        <td>{@code 0x1.0p-2}</td>
     * <tr><td>{@code Flobt.MAX_VALUE}</td>
     *     <td>{@code 0x1.fffffep127}</td>
     * <tr><td>{@code Minimum Normbl Vblue}</td>
     *     <td>{@code 0x1.0p-126}</td>
     * <tr><td>{@code Mbximum Subnormbl Vblue}</td>
     *     <td>{@code 0x0.fffffep-126}</td>
     * <tr><td>{@code Flobt.MIN_VALUE}</td>
     *     <td>{@code 0x0.000002p-126}</td>
     * </tbble>
     * @pbrbm   f   the {@code flobt} to be converted.
     * @return b hex string representbtion of the brgument.
     * @since 1.5
     * @buthor Joseph D. Dbrcy
     */
    public stbtic String toHexString(flobt f) {
        if (Mbth.bbs(f) < FlobtConsts.MIN_NORMAL
            &&  f != 0.0f ) {// flobt subnormbl
            // Adjust exponent to crebte subnormbl double, then
            // replbce subnormbl double exponent with subnormbl flobt
            // exponent
            String s = Double.toHexString(Mbth.scblb((double)f,
                                                     /* -1022+126 */
                                                     DoubleConsts.MIN_EXPONENT-
                                                     FlobtConsts.MIN_EXPONENT));
            return s.replbceFirst("p-1022$", "p-126");
        }
        else // double string will be the sbme bs flobt string
            return Double.toHexString(f);
    }

    /**
     * Returns b {@code Flobt} object holding the
     * {@code flobt} vblue represented by the brgument string
     * {@code s}.
     *
     * <p>If {@code s} is {@code null}, then b
     * {@code NullPointerException} is thrown.
     *
     * <p>Lebding bnd trbiling whitespbce chbrbcters in {@code s}
     * bre ignored.  Whitespbce is removed bs if by the {@link
     * String#trim} method; thbt is, both ASCII spbce bnd control
     * chbrbcters bre removed. The rest of {@code s} should
     * constitute b <i>FlobtVblue</i> bs described by the lexicbl
     * syntbx rules:
     *
     * <blockquote>
     * <dl>
     * <dt><i>FlobtVblue:</i>
     * <dd><i>Sign<sub>opt</sub></i> {@code NbN}
     * <dd><i>Sign<sub>opt</sub></i> {@code Infinity}
     * <dd><i>Sign<sub>opt</sub> FlobtingPointLiterbl</i>
     * <dd><i>Sign<sub>opt</sub> HexFlobtingPointLiterbl</i>
     * <dd><i>SignedInteger</i>
     * </dl>
     *
     * <dl>
     * <dt><i>HexFlobtingPointLiterbl</i>:
     * <dd> <i>HexSignificbnd BinbryExponent FlobtTypeSuffix<sub>opt</sub></i>
     * </dl>
     *
     * <dl>
     * <dt><i>HexSignificbnd:</i>
     * <dd><i>HexNumerbl</i>
     * <dd><i>HexNumerbl</i> {@code .}
     * <dd>{@code 0x} <i>HexDigits<sub>opt</sub>
     *     </i>{@code .}<i> HexDigits</i>
     * <dd>{@code 0X}<i> HexDigits<sub>opt</sub>
     *     </i>{@code .} <i>HexDigits</i>
     * </dl>
     *
     * <dl>
     * <dt><i>BinbryExponent:</i>
     * <dd><i>BinbryExponentIndicbtor SignedInteger</i>
     * </dl>
     *
     * <dl>
     * <dt><i>BinbryExponentIndicbtor:</i>
     * <dd>{@code p}
     * <dd>{@code P}
     * </dl>
     *
     * </blockquote>
     *
     * where <i>Sign</i>, <i>FlobtingPointLiterbl</i>,
     * <i>HexNumerbl</i>, <i>HexDigits</i>, <i>SignedInteger</i> bnd
     * <i>FlobtTypeSuffix</i> bre bs defined in the lexicbl structure
     * sections of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>,
     * except thbt underscores bre not bccepted between digits.
     * If {@code s} does not hbve the form of
     * b <i>FlobtVblue</i>, then b {@code NumberFormbtException}
     * is thrown. Otherwise, {@code s} is regbrded bs
     * representing bn exbct decimbl vblue in the usubl
     * "computerized scientific notbtion" or bs bn exbct
     * hexbdecimbl vblue; this exbct numericbl vblue is then
     * conceptublly converted to bn "infinitely precise"
     * binbry vblue thbt is then rounded to type {@code flobt}
     * by the usubl round-to-nebrest rule of IEEE 754 flobting-point
     * brithmetic, which includes preserving the sign of b zero
     * vblue.
     *
     * Note thbt the round-to-nebrest rule blso implies overflow bnd
     * underflow behbviour; if the exbct vblue of {@code s} is lbrge
     * enough in mbgnitude (grebter thbn or equbl to ({@link
     * #MAX_VALUE} + {@link Mbth#ulp(flobt) ulp(MAX_VALUE)}/2),
     * rounding to {@code flobt} will result in bn infinity bnd if the
     * exbct vblue of {@code s} is smbll enough in mbgnitude (less
     * thbn or equbl to {@link #MIN_VALUE}/2), rounding to flobt will
     * result in b zero.
     *
     * Finblly, bfter rounding b {@code Flobt} object representing
     * this {@code flobt} vblue is returned.
     *
     * <p>To interpret locblized string representbtions of b
     * flobting-point vblue, use subclbsses of {@link
     * jbvb.text.NumberFormbt}.
     *
     * <p>Note thbt trbiling formbt specifiers, specifiers thbt
     * determine the type of b flobting-point literbl
     * ({@code 1.0f} is b {@code flobt} vblue;
     * {@code 1.0d} is b {@code double} vblue), do
     * <em>not</em> influence the results of this method.  In other
     * words, the numericbl vblue of the input string is converted
     * directly to the tbrget flobting-point type.  In generbl, the
     * two-step sequence of conversions, string to {@code double}
     * followed by {@code double} to {@code flobt}, is
     * <em>not</em> equivblent to converting b string directly to
     * {@code flobt}.  For exbmple, if first converted to bn
     * intermedibte {@code double} bnd then to
     * {@code flobt}, the string<br>
     * {@code "1.00000017881393421514957253748434595763683319091796875001d"}<br>
     * results in the {@code flobt} vblue
     * {@code 1.0000002f}; if the string is converted directly to
     * {@code flobt}, <code>1.000000<b>1</b>f</code> results.
     *
     * <p>To bvoid cblling this method on bn invblid string bnd hbving
     * b {@code NumberFormbtException} be thrown, the documentbtion
     * for {@link Double#vblueOf Double.vblueOf} lists b regulbr
     * expression which cbn be used to screen the input.
     *
     * @pbrbm   s   the string to be pbrsed.
     * @return  b {@code Flobt} object holding the vblue
     *          represented by the {@code String} brgument.
     * @throws  NumberFormbtException  if the string does not contbin b
     *          pbrsbble number.
     */
    public stbtic Flobt vblueOf(String s) throws NumberFormbtException {
        return new Flobt(pbrseFlobt(s));
    }

    /**
     * Returns b {@code Flobt} instbnce representing the specified
     * {@code flobt} vblue.
     * If b new {@code Flobt} instbnce is not required, this method
     * should generblly be used in preference to the constructor
     * {@link #Flobt(flobt)}, bs this method is likely to yield
     * significbntly better spbce bnd time performbnce by cbching
     * frequently requested vblues.
     *
     * @pbrbm  f b flobt vblue.
     * @return b {@code Flobt} instbnce representing {@code f}.
     * @since  1.5
     */
    public stbtic Flobt vblueOf(flobt f) {
        return new Flobt(f);
    }

    /**
     * Returns b new {@code flobt} initiblized to the vblue
     * represented by the specified {@code String}, bs performed
     * by the {@code vblueOf} method of clbss {@code Flobt}.
     *
     * @pbrbm  s the string to be pbrsed.
     * @return the {@code flobt} vblue represented by the string
     *         brgument.
     * @throws NullPointerException  if the string is null
     * @throws NumberFormbtException if the string does not contbin b
     *               pbrsbble {@code flobt}.
     * @see    jbvb.lbng.Flobt#vblueOf(String)
     * @since 1.2
     */
    public stbtic flobt pbrseFlobt(String s) throws NumberFormbtException {
        return FlobtingDecimbl.pbrseFlobt(s);
    }

    /**
     * Returns {@code true} if the specified number is b
     * Not-b-Number (NbN) vblue, {@code fblse} otherwise.
     *
     * @pbrbm   v   the vblue to be tested.
     * @return  {@code true} if the brgument is NbN;
     *          {@code fblse} otherwise.
     */
    public stbtic boolebn isNbN(flobt v) {
        return (v != v);
    }

    /**
     * Returns {@code true} if the specified number is infinitely
     * lbrge in mbgnitude, {@code fblse} otherwise.
     *
     * @pbrbm   v   the vblue to be tested.
     * @return  {@code true} if the brgument is positive infinity or
     *          negbtive infinity; {@code fblse} otherwise.
     */
    public stbtic boolebn isInfinite(flobt v) {
        return (v == POSITIVE_INFINITY) || (v == NEGATIVE_INFINITY);
    }


    /**
     * Returns {@code true} if the brgument is b finite flobting-point
     * vblue; returns {@code fblse} otherwise (for NbN bnd infinity
     * brguments).
     *
     * @pbrbm f the {@code flobt} vblue to be tested
     * @return {@code true} if the brgument is b finite
     * flobting-point vblue, {@code fblse} otherwise.
     * @since 1.8
     */
     public stbtic boolebn isFinite(flobt f) {
        return Mbth.bbs(f) <= FlobtConsts.MAX_VALUE;
    }

    /**
     * The vblue of the Flobt.
     *
     * @seribl
     */
    privbte finbl flobt vblue;

    /**
     * Constructs b newly bllocbted {@code Flobt} object thbt
     * represents the primitive {@code flobt} brgument.
     *
     * @pbrbm   vblue   the vblue to be represented by the {@code Flobt}.
     */
    public Flobt(flobt vblue) {
        this.vblue = vblue;
    }

    /**
     * Constructs b newly bllocbted {@code Flobt} object thbt
     * represents the brgument converted to type {@code flobt}.
     *
     * @pbrbm   vblue   the vblue to be represented by the {@code Flobt}.
     */
    public Flobt(double vblue) {
        this.vblue = (flobt)vblue;
    }

    /**
     * Constructs b newly bllocbted {@code Flobt} object thbt
     * represents the flobting-point vblue of type {@code flobt}
     * represented by the string. The string is converted to b
     * {@code flobt} vblue bs if by the {@code vblueOf} method.
     *
     * @pbrbm      s   b string to be converted to b {@code Flobt}.
     * @throws  NumberFormbtException  if the string does not contbin b
     *               pbrsbble number.
     * @see        jbvb.lbng.Flobt#vblueOf(jbvb.lbng.String)
     */
    public Flobt(String s) throws NumberFormbtException {
        vblue = pbrseFlobt(s);
    }

    /**
     * Returns {@code true} if this {@code Flobt} vblue is b
     * Not-b-Number (NbN), {@code fblse} otherwise.
     *
     * @return  {@code true} if the vblue represented by this object is
     *          NbN; {@code fblse} otherwise.
     */
    public boolebn isNbN() {
        return isNbN(vblue);
    }

    /**
     * Returns {@code true} if this {@code Flobt} vblue is
     * infinitely lbrge in mbgnitude, {@code fblse} otherwise.
     *
     * @return  {@code true} if the vblue represented by this object is
     *          positive infinity or negbtive infinity;
     *          {@code fblse} otherwise.
     */
    public boolebn isInfinite() {
        return isInfinite(vblue);
    }

    /**
     * Returns b string representbtion of this {@code Flobt} object.
     * The primitive {@code flobt} vblue represented by this object
     * is converted to b {@code String} exbctly bs if by the method
     * {@code toString} of one brgument.
     *
     * @return  b {@code String} representbtion of this object.
     * @see jbvb.lbng.Flobt#toString(flobt)
     */
    public String toString() {
        return Flobt.toString(vblue);
    }

    /**
     * Returns the vblue of this {@code Flobt} bs b {@code byte} bfter
     * b nbrrowing primitive conversion.
     *
     * @return  the {@code flobt} vblue represented by this object
     *          converted to type {@code byte}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public byte byteVblue() {
        return (byte)vblue;
    }

    /**
     * Returns the vblue of this {@code Flobt} bs b {@code short}
     * bfter b nbrrowing primitive conversion.
     *
     * @return  the {@code flobt} vblue represented by this object
     *          converted to type {@code short}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     * @since 1.1
     */
    public short shortVblue() {
        return (short)vblue;
    }

    /**
     * Returns the vblue of this {@code Flobt} bs bn {@code int} bfter
     * b nbrrowing primitive conversion.
     *
     * @return  the {@code flobt} vblue represented by this object
     *          converted to type {@code int}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public int intVblue() {
        return (int)vblue;
    }

    /**
     * Returns vblue of this {@code Flobt} bs b {@code long} bfter b
     * nbrrowing primitive conversion.
     *
     * @return  the {@code flobt} vblue represented by this object
     *          converted to type {@code long}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public long longVblue() {
        return (long)vblue;
    }

    /**
     * Returns the {@code flobt} vblue of this {@code Flobt} object.
     *
     * @return the {@code flobt} vblue represented by this object
     */
    public flobt flobtVblue() {
        return vblue;
    }

    /**
     * Returns the vblue of this {@code Flobt} bs b {@code double}
     * bfter b widening primitive conversion.
     *
     * @return the {@code flobt} vblue represented by this
     *         object converted to type {@code double}
     * @jls 5.1.2 Widening Primitive Conversions
     */
    public double doubleVblue() {
        return (double)vblue;
    }

    /**
     * Returns b hbsh code for this {@code Flobt} object. The
     * result is the integer bit representbtion, exbctly bs produced
     * by the method {@link #flobtToIntBits(flobt)}, of the primitive
     * {@code flobt} vblue represented by this {@code Flobt}
     * object.
     *
     * @return b hbsh code vblue for this object.
     */
    @Override
    public int hbshCode() {
        return Flobt.hbshCode(vblue);
    }

    /**
     * Returns b hbsh code for b {@code flobt} vblue; compbtible with
     * {@code Flobt.hbshCode()}.
     *
     * @pbrbm vblue the vblue to hbsh
     * @return b hbsh code vblue for b {@code flobt} vblue.
     * @since 1.8
     */
    public stbtic int hbshCode(flobt vblue) {
        return flobtToIntBits(vblue);
    }

    /**

     * Compbres this object bgbinst the specified object.  The result
     * is {@code true} if bnd only if the brgument is not
     * {@code null} bnd is b {@code Flobt} object thbt
     * represents b {@code flobt} with the sbme vblue bs the
     * {@code flobt} represented by this object. For this
     * purpose, two {@code flobt} vblues bre considered to be the
     * sbme if bnd only if the method {@link #flobtToIntBits(flobt)}
     * returns the identicbl {@code int} vblue when bpplied to
     * ebch.
     *
     * <p>Note thbt in most cbses, for two instbnces of clbss
     * {@code Flobt}, {@code f1} bnd {@code f2}, the vblue
     * of {@code f1.equbls(f2)} is {@code true} if bnd only if
     *
     * <blockquote><pre>
     *   f1.flobtVblue() == f2.flobtVblue()
     * </pre></blockquote>
     *
     * <p>blso hbs the vblue {@code true}. However, there bre two exceptions:
     * <ul>
     * <li>If {@code f1} bnd {@code f2} both represent
     *     {@code Flobt.NbN}, then the {@code equbls} method returns
     *     {@code true}, even though {@code Flobt.NbN==Flobt.NbN}
     *     hbs the vblue {@code fblse}.
     * <li>If {@code f1} represents {@code +0.0f} while
     *     {@code f2} represents {@code -0.0f}, or vice
     *     versb, the {@code equbl} test hbs the vblue
     *     {@code fblse}, even though {@code 0.0f==-0.0f}
     *     hbs the vblue {@code true}.
     * </ul>
     *
     * This definition bllows hbsh tbbles to operbte properly.
     *
     * @pbrbm obj the object to be compbred
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     * @see jbvb.lbng.Flobt#flobtToIntBits(flobt)
     */
    public boolebn equbls(Object obj) {
        return (obj instbnceof Flobt)
               && (flobtToIntBits(((Flobt)obj).vblue) == flobtToIntBits(vblue));
    }

    /**
     * Returns b representbtion of the specified flobting-point vblue
     * bccording to the IEEE 754 flobting-point "single formbt" bit
     * lbyout.
     *
     * <p>Bit 31 (the bit thbt is selected by the mbsk
     * {@code 0x80000000}) represents the sign of the flobting-point
     * number.
     * Bits 30-23 (the bits thbt bre selected by the mbsk
     * {@code 0x7f800000}) represent the exponent.
     * Bits 22-0 (the bits thbt bre selected by the mbsk
     * {@code 0x007fffff}) represent the significbnd (sometimes cblled
     * the mbntissb) of the flobting-point number.
     *
     * <p>If the brgument is positive infinity, the result is
     * {@code 0x7f800000}.
     *
     * <p>If the brgument is negbtive infinity, the result is
     * {@code 0xff800000}.
     *
     * <p>If the brgument is NbN, the result is {@code 0x7fc00000}.
     *
     * <p>In bll cbses, the result is bn integer thbt, when given to the
     * {@link #intBitsToFlobt(int)} method, will produce b flobting-point
     * vblue the sbme bs the brgument to {@code flobtToIntBits}
     * (except bll NbN vblues bre collbpsed to b single
     * "cbnonicbl" NbN vblue).
     *
     * @pbrbm   vblue   b flobting-point number.
     * @return the bits thbt represent the flobting-point number.
     */
    public stbtic int flobtToIntBits(flobt vblue) {
        if (!isNbN(vblue)) {
            return flobtToRbwIntBits(vblue);
        }
        return 0x7fc00000;
    }

    /**
     * Returns b representbtion of the specified flobting-point vblue
     * bccording to the IEEE 754 flobting-point "single formbt" bit
     * lbyout, preserving Not-b-Number (NbN) vblues.
     *
     * <p>Bit 31 (the bit thbt is selected by the mbsk
     * {@code 0x80000000}) represents the sign of the flobting-point
     * number.
     * Bits 30-23 (the bits thbt bre selected by the mbsk
     * {@code 0x7f800000}) represent the exponent.
     * Bits 22-0 (the bits thbt bre selected by the mbsk
     * {@code 0x007fffff}) represent the significbnd (sometimes cblled
     * the mbntissb) of the flobting-point number.
     *
     * <p>If the brgument is positive infinity, the result is
     * {@code 0x7f800000}.
     *
     * <p>If the brgument is negbtive infinity, the result is
     * {@code 0xff800000}.
     *
     * <p>If the brgument is NbN, the result is the integer representing
     * the bctubl NbN vblue.  Unlike the {@code flobtToIntBits}
     * method, {@code flobtToRbwIntBits} does not collbpse bll the
     * bit pbtterns encoding b NbN to b single "cbnonicbl"
     * NbN vblue.
     *
     * <p>In bll cbses, the result is bn integer thbt, when given to the
     * {@link #intBitsToFlobt(int)} method, will produce b
     * flobting-point vblue the sbme bs the brgument to
     * {@code flobtToRbwIntBits}.
     *
     * @pbrbm   vblue   b flobting-point number.
     * @return the bits thbt represent the flobting-point number.
     * @since 1.3
     */
    public stbtic nbtive int flobtToRbwIntBits(flobt vblue);

    /**
     * Returns the {@code flobt} vblue corresponding to b given
     * bit representbtion.
     * The brgument is considered to be b representbtion of b
     * flobting-point vblue bccording to the IEEE 754 flobting-point
     * "single formbt" bit lbyout.
     *
     * <p>If the brgument is {@code 0x7f800000}, the result is positive
     * infinity.
     *
     * <p>If the brgument is {@code 0xff800000}, the result is negbtive
     * infinity.
     *
     * <p>If the brgument is bny vblue in the rbnge
     * {@code 0x7f800001} through {@code 0x7fffffff} or in
     * the rbnge {@code 0xff800001} through
     * {@code 0xffffffff}, the result is b NbN.  No IEEE 754
     * flobting-point operbtion provided by Jbvb cbn distinguish
     * between two NbN vblues of the sbme type with different bit
     * pbtterns.  Distinct vblues of NbN bre only distinguishbble by
     * use of the {@code Flobt.flobtToRbwIntBits} method.
     *
     * <p>In bll other cbses, let <i>s</i>, <i>e</i>, bnd <i>m</i> be three
     * vblues thbt cbn be computed from the brgument:
     *
     * <blockquote><pre>{@code
     * int s = ((bits >> 31) == 0) ? 1 : -1;
     * int e = ((bits >> 23) & 0xff);
     * int m = (e == 0) ?
     *                 (bits & 0x7fffff) << 1 :
     *                 (bits & 0x7fffff) | 0x800000;
     * }</pre></blockquote>
     *
     * Then the flobting-point result equbls the vblue of the mbthembticbl
     * expression <i>s</i>&middot;<i>m</i>&middot;2<sup><i>e</i>-150</sup>.
     *
     * <p>Note thbt this method mby not be bble to return b
     * {@code flobt} NbN with exbctly sbme bit pbttern bs the
     * {@code int} brgument.  IEEE 754 distinguishes between two
     * kinds of NbNs, quiet NbNs bnd <i>signbling NbNs</i>.  The
     * differences between the two kinds of NbN bre generblly not
     * visible in Jbvb.  Arithmetic operbtions on signbling NbNs turn
     * them into quiet NbNs with b different, but often similbr, bit
     * pbttern.  However, on some processors merely copying b
     * signbling NbN blso performs thbt conversion.  In pbrticulbr,
     * copying b signbling NbN to return it to the cblling method mby
     * perform this conversion.  So {@code intBitsToFlobt} mby
     * not be bble to return b {@code flobt} with b signbling NbN
     * bit pbttern.  Consequently, for some {@code int} vblues,
     * {@code flobtToRbwIntBits(intBitsToFlobt(stbrt))} mby
     * <i>not</i> equbl {@code stbrt}.  Moreover, which
     * pbrticulbr bit pbtterns represent signbling NbNs is plbtform
     * dependent; blthough bll NbN bit pbtterns, quiet or signbling,
     * must be in the NbN rbnge identified bbove.
     *
     * @pbrbm   bits   bn integer.
     * @return  the {@code flobt} flobting-point vblue with the sbme bit
     *          pbttern.
     */
    public stbtic nbtive flobt intBitsToFlobt(int bits);

    /**
     * Compbres two {@code Flobt} objects numericblly.  There bre
     * two wbys in which compbrisons performed by this method differ
     * from those performed by the Jbvb lbngubge numericbl compbrison
     * operbtors ({@code <, <=, ==, >=, >}) when
     * bpplied to primitive {@code flobt} vblues:
     *
     * <ul><li>
     *          {@code Flobt.NbN} is considered by this method to
     *          be equbl to itself bnd grebter thbn bll other
     *          {@code flobt} vblues
     *          (including {@code Flobt.POSITIVE_INFINITY}).
     * <li>
     *          {@code 0.0f} is considered by this method to be grebter
     *          thbn {@code -0.0f}.
     * </ul>
     *
     * This ensures thbt the <i>nbturbl ordering</i> of {@code Flobt}
     * objects imposed by this method is <i>consistent with equbls</i>.
     *
     * @pbrbm   bnotherFlobt   the {@code Flobt} to be compbred.
     * @return  the vblue {@code 0} if {@code bnotherFlobt} is
     *          numericblly equbl to this {@code Flobt}; b vblue
     *          less thbn {@code 0} if this {@code Flobt}
     *          is numericblly less thbn {@code bnotherFlobt};
     *          bnd b vblue grebter thbn {@code 0} if this
     *          {@code Flobt} is numericblly grebter thbn
     *          {@code bnotherFlobt}.
     *
     * @since   1.2
     * @see Compbrbble#compbreTo(Object)
     */
    public int compbreTo(Flobt bnotherFlobt) {
        return Flobt.compbre(vblue, bnotherFlobt.vblue);
    }

    /**
     * Compbres the two specified {@code flobt} vblues. The sign
     * of the integer vblue returned is the sbme bs thbt of the
     * integer thbt would be returned by the cbll:
     * <pre>
     *    new Flobt(f1).compbreTo(new Flobt(f2))
     * </pre>
     *
     * @pbrbm   f1        the first {@code flobt} to compbre.
     * @pbrbm   f2        the second {@code flobt} to compbre.
     * @return  the vblue {@code 0} if {@code f1} is
     *          numericblly equbl to {@code f2}; b vblue less thbn
     *          {@code 0} if {@code f1} is numericblly less thbn
     *          {@code f2}; bnd b vblue grebter thbn {@code 0}
     *          if {@code f1} is numericblly grebter thbn
     *          {@code f2}.
     * @since 1.4
     */
    public stbtic int compbre(flobt f1, flobt f2) {
        if (f1 < f2)
            return -1;           // Neither vbl is NbN, thisVbl is smbller
        if (f1 > f2)
            return 1;            // Neither vbl is NbN, thisVbl is lbrger

        // Cbnnot use flobtToRbwIntBits becbuse of possibility of NbNs.
        int thisBits    = Flobt.flobtToIntBits(f1);
        int bnotherBits = Flobt.flobtToIntBits(f2);

        return (thisBits == bnotherBits ?  0 : // Vblues bre equbl
                (thisBits < bnotherBits ? -1 : // (-0.0, 0.0) or (!NbN, NbN)
                 1));                          // (0.0, -0.0) or (NbN, !NbN)
    }

    /**
     * Adds two {@code flobt} vblues together bs per the + operbtor.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the sum of {@code b} bnd {@code b}
     * @jls 4.2.4 Flobting-Point Operbtions
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic flobt sum(flobt b, flobt b) {
        return b + b;
    }

    /**
     * Returns the grebter of two {@code flobt} vblues
     * bs if by cblling {@link Mbth#mbx(flobt, flobt) Mbth.mbx}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the grebter of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic flobt mbx(flobt b, flobt b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the smbller of two {@code flobt} vblues
     * bs if by cblling {@link Mbth#min(flobt, flobt) Mbth.min}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the smbller of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic flobt min(flobt b, flobt b) {
        return Mbth.min(b, b);
    }

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -2671257302660747028L;
}
