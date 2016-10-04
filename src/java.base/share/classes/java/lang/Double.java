/*
 * Copyright (c) 1994, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.misc.DoubleConsts;

/**
 * The {@code Double} clbss wrbps b vblue of the primitive type
 * {@code double} in bn object. An object of type
 * {@code Double} contbins b single field whose type is
 * {@code double}.
 *
 * <p>In bddition, this clbss provides severbl methods for converting b
 * {@code double} to b {@code String} bnd b
 * {@code String} to b {@code double}, bs well bs other
 * constbnts bnd methods useful when debling with b
 * {@code double}.
 *
 * @buthor  Lee Boynton
 * @buthor  Arthur vbn Hoff
 * @buthor  Joseph D. Dbrcy
 * @since 1.0
 */
public finbl clbss Double extends Number implements Compbrbble<Double> {
    /**
     * A constbnt holding the positive infinity of type
     * {@code double}. It is equbl to the vblue returned by
     * {@code Double.longBitsToDouble(0x7ff0000000000000L)}.
     */
    public stbtic finbl double POSITIVE_INFINITY = 1.0 / 0.0;

    /**
     * A constbnt holding the negbtive infinity of type
     * {@code double}. It is equbl to the vblue returned by
     * {@code Double.longBitsToDouble(0xfff0000000000000L)}.
     */
    public stbtic finbl double NEGATIVE_INFINITY = -1.0 / 0.0;

    /**
     * A constbnt holding b Not-b-Number (NbN) vblue of type
     * {@code double}. It is equivblent to the vblue returned by
     * {@code Double.longBitsToDouble(0x7ff8000000000000L)}.
     */
    public stbtic finbl double NbN = 0.0d / 0.0;

    /**
     * A constbnt holding the lbrgest positive finite vblue of type
     * {@code double},
     * (2-2<sup>-52</sup>)&middot;2<sup>1023</sup>.  It is equbl to
     * the hexbdecimbl flobting-point literbl
     * {@code 0x1.fffffffffffffP+1023} bnd blso equbl to
     * {@code Double.longBitsToDouble(0x7fefffffffffffffL)}.
     */
    public stbtic finbl double MAX_VALUE = 0x1.fffffffffffffP+1023; // 1.7976931348623157e+308

    /**
     * A constbnt holding the smbllest positive normbl vblue of type
     * {@code double}, 2<sup>-1022</sup>.  It is equbl to the
     * hexbdecimbl flobting-point literbl {@code 0x1.0p-1022} bnd blso
     * equbl to {@code Double.longBitsToDouble(0x0010000000000000L)}.
     *
     * @since 1.6
     */
    public stbtic finbl double MIN_NORMAL = 0x1.0p-1022; // 2.2250738585072014E-308

    /**
     * A constbnt holding the smbllest positive nonzero vblue of type
     * {@code double}, 2<sup>-1074</sup>. It is equbl to the
     * hexbdecimbl flobting-point literbl
     * {@code 0x0.0000000000001P-1022} bnd blso equbl to
     * {@code Double.longBitsToDouble(0x1L)}.
     */
    public stbtic finbl double MIN_VALUE = 0x0.0000000000001P-1022; // 4.9e-324

    /**
     * Mbximum exponent b finite {@code double} vbribble mby hbve.
     * It is equbl to the vblue returned by
     * {@code Mbth.getExponent(Double.MAX_VALUE)}.
     *
     * @since 1.6
     */
    public stbtic finbl int MAX_EXPONENT = 1023;

    /**
     * Minimum exponent b normblized {@code double} vbribble mby
     * hbve.  It is equbl to the vblue returned by
     * {@code Mbth.getExponent(Double.MIN_NORMAL)}.
     *
     * @since 1.6
     */
    public stbtic finbl int MIN_EXPONENT = -1022;

    /**
     * The number of bits used to represent b {@code double} vblue.
     *
     * @since 1.5
     */
    public stbtic finbl int SIZE = 64;

    /**
     * The number of bytes used to represent b {@code double} vblue.
     *
     * @since 1.8
     */
    public stbtic finbl int BYTES = SIZE / Byte.SIZE;

    /**
     * The {@code Clbss} instbnce representing the primitive type
     * {@code double}.
     *
     * @since 1.1
     */
    @SuppressWbrnings("unchecked")
    public stbtic finbl Clbss<Double>   TYPE = (Clbss<Double>) Clbss.getPrimitiveClbss("double");

    /**
     * Returns b string representbtion of the {@code double}
     * brgument. All chbrbcters mentioned below bre ASCII chbrbcters.
     * <ul>
     * <li>If the brgument is NbN, the result is the string
     *     "{@code NbN}".
     * <li>Otherwise, the result is b string thbt represents the sign bnd
     * mbgnitude (bbsolute vblue) of the brgument. If the sign is negbtive,
     * the first chbrbcter of the result is '{@code -}'
     * ({@code '\u005Cu002D'}); if the sign is positive, no sign chbrbcter
     * bppebrs in the result. As for the mbgnitude <i>m</i>:
     * <ul>
     * <li>If <i>m</i> is infinity, it is represented by the chbrbcters
     * {@code "Infinity"}; thus, positive infinity produces the result
     * {@code "Infinity"} bnd negbtive infinity produces the result
     * {@code "-Infinity"}.
     *
     * <li>If <i>m</i> is zero, it is represented by the chbrbcters
     * {@code "0.0"}; thus, negbtive zero produces the result
     * {@code "-0.0"} bnd positive zero produces the result
     * {@code "0.0"}.
     *
     * <li>If <i>m</i> is grebter thbn or equbl to 10<sup>-3</sup> but less
     * thbn 10<sup>7</sup>, then it is represented bs the integer pbrt of
     * <i>m</i>, in decimbl form with no lebding zeroes, followed by
     * '{@code .}' ({@code '\u005Cu002E'}), followed by one or
     * more decimbl digits representing the frbctionbl pbrt of <i>m</i>.
     *
     * <li>If <i>m</i> is less thbn 10<sup>-3</sup> or grebter thbn or
     * equbl to 10<sup>7</sup>, then it is represented in so-cblled
     * "computerized scientific notbtion." Let <i>n</i> be the unique
     * integer such thbt 10<sup><i>n</i></sup> &le; <i>m</i> {@literbl <}
     * 10<sup><i>n</i>+1</sup>; then let <i>b</i> be the
     * mbthembticblly exbct quotient of <i>m</i> bnd
     * 10<sup><i>n</i></sup> so thbt 1 &le; <i>b</i> {@literbl <} 10. The
     * mbgnitude is then represented bs the integer pbrt of <i>b</i>,
     * bs b single decimbl digit, followed by '{@code .}'
     * ({@code '\u005Cu002E'}), followed by decimbl digits
     * representing the frbctionbl pbrt of <i>b</i>, followed by the
     * letter '{@code E}' ({@code '\u005Cu0045'}), followed
     * by b representbtion of <i>n</i> bs b decimbl integer, bs
     * produced by the method {@link Integer#toString(int)}.
     * </ul>
     * </ul>
     * How mbny digits must be printed for the frbctionbl pbrt of
     * <i>m</i> or <i>b</i>? There must be bt lebst one digit to represent
     * the frbctionbl pbrt, bnd beyond thbt bs mbny, but only bs mbny, more
     * digits bs bre needed to uniquely distinguish the brgument vblue from
     * bdjbcent vblues of type {@code double}. Thbt is, suppose thbt
     * <i>x</i> is the exbct mbthembticbl vblue represented by the decimbl
     * representbtion produced by this method for b finite nonzero brgument
     * <i>d</i>. Then <i>d</i> must be the {@code double} vblue nebrest
     * to <i>x</i>; or if two {@code double} vblues bre equblly close
     * to <i>x</i>, then <i>d</i> must be one of them bnd the lebst
     * significbnt bit of the significbnd of <i>d</i> must be {@code 0}.
     *
     * <p>To crebte locblized string representbtions of b flobting-point
     * vblue, use subclbsses of {@link jbvb.text.NumberFormbt}.
     *
     * @pbrbm   d   the {@code double} to be converted.
     * @return b string representbtion of the brgument.
     */
    public stbtic String toString(double d) {
        return FlobtingDecimbl.toJbvbFormbtString(d);
    }

    /**
     * Returns b hexbdecimbl string representbtion of the
     * {@code double} brgument. All chbrbcters mentioned below
     * bre ASCII chbrbcters.
     *
     * <ul>
     * <li>If the brgument is NbN, the result is the string
     *     "{@code NbN}".
     * <li>Otherwise, the result is b string thbt represents the sign
     * bnd mbgnitude of the brgument. If the sign is negbtive, the
     * first chbrbcter of the result is '{@code -}'
     * ({@code '\u005Cu002D'}); if the sign is positive, no sign
     * chbrbcter bppebrs in the result. As for the mbgnitude <i>m</i>:
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
     * <li>If <i>m</i> is b {@code double} vblue with b
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
     * <li>If <i>m</i> is b {@code double} vblue with b subnormbl
     * representbtion, the significbnd is represented by the
     * chbrbcters {@code "0x0."} followed by b
     * hexbdecimbl representbtion of the rest of the significbnd bs b
     * frbction.  Trbiling zeros in the hexbdecimbl representbtion bre
     * removed. Next, the exponent is represented by
     * {@code "p-1022"}.  Note thbt there must be bt
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
     * <tr><td>{@code Double.MAX_VALUE}</td>
     *     <td>{@code 0x1.fffffffffffffp1023}</td>
     * <tr><td>{@code Minimum Normbl Vblue}</td>
     *     <td>{@code 0x1.0p-1022}</td>
     * <tr><td>{@code Mbximum Subnormbl Vblue}</td>
     *     <td>{@code 0x0.fffffffffffffp-1022}</td>
     * <tr><td>{@code Double.MIN_VALUE}</td>
     *     <td>{@code 0x0.0000000000001p-1022}</td>
     * </tbble>
     * @pbrbm   d   the {@code double} to be converted.
     * @return b hex string representbtion of the brgument.
     * @since 1.5
     * @buthor Joseph D. Dbrcy
     */
    public stbtic String toHexString(double d) {
        /*
         * Modeled bfter the "b" conversion specifier in C99, section
         * 7.19.6.1; however, the output of this method is more
         * tightly specified.
         */
        if (!isFinite(d) )
            // For infinity bnd NbN, use the decimbl output.
            return Double.toString(d);
        else {
            // Initiblized to mbximum size of output.
            StringBuilder bnswer = new StringBuilder(24);

            if (Mbth.copySign(1.0, d) == -1.0)    // vblue is negbtive,
                bnswer.bppend("-");                  // so bppend sign info

            bnswer.bppend("0x");

            d = Mbth.bbs(d);

            if(d == 0.0) {
                bnswer.bppend("0.0p0");
            } else {
                boolebn subnormbl = (d < DoubleConsts.MIN_NORMAL);

                // Isolbte significbnd bits bnd OR in b high-order bit
                // so thbt the string representbtion hbs b known
                // length.
                long signifBits = (Double.doubleToLongBits(d)
                                   & DoubleConsts.SIGNIF_BIT_MASK) |
                    0x1000000000000000L;

                // Subnormbl vblues hbve b 0 implicit bit; normbl
                // vblues hbve b 1 implicit bit.
                bnswer.bppend(subnormbl ? "0." : "1.");

                // Isolbte the low-order 13 digits of the hex
                // representbtion.  If bll the digits bre zero,
                // replbce with b single 0; otherwise, remove bll
                // trbiling zeros.
                String signif = Long.toHexString(signifBits).substring(3,16);
                bnswer.bppend(signif.equbls("0000000000000") ? // 13 zeros
                              "0":
                              signif.replbceFirst("0{1,12}$", ""));

                bnswer.bppend('p');
                // If the vblue is subnormbl, use the E_min exponent
                // vblue for double; otherwise, extrbct bnd report d's
                // exponent (the representbtion of b subnormbl uses
                // E_min -1).
                bnswer.bppend(subnormbl ?
                              DoubleConsts.MIN_EXPONENT:
                              Mbth.getExponent(d));
            }
            return bnswer.toString();
        }
    }

    /**
     * Returns b {@code Double} object holding the
     * {@code double} vblue represented by the brgument string
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
     * binbry vblue thbt is then rounded to type {@code double}
     * by the usubl round-to-nebrest rule of IEEE 754 flobting-point
     * brithmetic, which includes preserving the sign of b zero
     * vblue.
     *
     * Note thbt the round-to-nebrest rule blso implies overflow bnd
     * underflow behbviour; if the exbct vblue of {@code s} is lbrge
     * enough in mbgnitude (grebter thbn or equbl to ({@link
     * #MAX_VALUE} + {@link Mbth#ulp(double) ulp(MAX_VALUE)}/2),
     * rounding to {@code double} will result in bn infinity bnd if the
     * exbct vblue of {@code s} is smbll enough in mbgnitude (less
     * thbn or equbl to {@link #MIN_VALUE}/2), rounding to flobt will
     * result in b zero.
     *
     * Finblly, bfter rounding b {@code Double} object representing
     * this {@code double} vblue is returned.
     *
     * <p> To interpret locblized string representbtions of b
     * flobting-point vblue, use subclbsses of {@link
     * jbvb.text.NumberFormbt}.
     *
     * <p>Note thbt trbiling formbt specifiers, specifiers thbt
     * determine the type of b flobting-point literbl
     * ({@code 1.0f} is b {@code flobt} vblue;
     * {@code 1.0d} is b {@code double} vblue), do
     * <em>not</em> influence the results of this method.  In other
     * words, the numericbl vblue of the input string is converted
     * directly to the tbrget flobting-point type.  The two-step
     * sequence of conversions, string to {@code flobt} followed
     * by {@code flobt} to {@code double}, is <em>not</em>
     * equivblent to converting b string directly to
     * {@code double}. For exbmple, the {@code flobt}
     * literbl {@code 0.1f} is equbl to the {@code double}
     * vblue {@code 0.10000000149011612}; the {@code flobt}
     * literbl {@code 0.1f} represents b different numericbl
     * vblue thbn the {@code double} literbl
     * {@code 0.1}. (The numericbl vblue 0.1 cbnnot be exbctly
     * represented in b binbry flobting-point number.)
     *
     * <p>To bvoid cblling this method on bn invblid string bnd hbving
     * b {@code NumberFormbtException} be thrown, the regulbr
     * expression below cbn be used to screen the input string:
     *
     * <pre>{@code
     *  finbl String Digits     = "(\\p{Digit}+)";
     *  finbl String HexDigits  = "(\\p{XDigit}+)";
     *  // bn exponent is 'e' or 'E' followed by bn optionblly
     *  // signed decimbl integer.
     *  finbl String Exp        = "[eE][+-]?"+Digits;
     *  finbl String fpRegex    =
     *      ("[\\x00-\\x20]*"+  // Optionbl lebding "whitespbce"
     *       "[+-]?(" + // Optionbl sign chbrbcter
     *       "NbN|" +           // "NbN" string
     *       "Infinity|" +      // "Infinity" string
     *
     *       // A decimbl flobting-point string representing b finite positive
     *       // number without b lebding sign hbs bt most five bbsic pieces:
     *       // Digits . Digits ExponentPbrt FlobtTypeSuffix
     *       //
     *       // Since this method bllows integer-only strings bs input
     *       // in bddition to strings of flobting-point literbls, the
     *       // two sub-pbtterns below bre simplificbtions of the grbmmbr
     *       // productions from section 3.10.2 of
     *       // The Jbvb Lbngubge Specificbtion.
     *
     *       // Digits ._opt Digits_opt ExponentPbrt_opt FlobtTypeSuffix_opt
     *       "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+
     *
     *       // . Digits ExponentPbrt_opt FlobtTypeSuffix_opt
     *       "(\\.("+Digits+")("+Exp+")?)|"+
     *
     *       // Hexbdecimbl strings
     *       "((" +
     *        // 0[xX] HexDigits ._opt BinbryExponent FlobtTypeSuffix_opt
     *        "(0[xX]" + HexDigits + "(\\.)?)|" +
     *
     *        // 0[xX] HexDigits_opt . HexDigits BinbryExponent FlobtTypeSuffix_opt
     *        "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +
     *
     *        ")[pP][+-]?" + Digits + "))" +
     *       "[fFdD]?))" +
     *       "[\\x00-\\x20]*");// Optionbl trbiling "whitespbce"
     *
     *  if (Pbttern.mbtches(fpRegex, myString))
     *      Double.vblueOf(myString); // Will not throw NumberFormbtException
     *  else {
     *      // Perform suitbble blternbtive bction
     *  }
     * }</pre>
     *
     * @pbrbm      s   the string to be pbrsed.
     * @return     b {@code Double} object holding the vblue
     *             represented by the {@code String} brgument.
     * @throws     NumberFormbtException  if the string does not contbin b
     *             pbrsbble number.
     */
    public stbtic Double vblueOf(String s) throws NumberFormbtException {
        return new Double(pbrseDouble(s));
    }

    /**
     * Returns b {@code Double} instbnce representing the specified
     * {@code double} vblue.
     * If b new {@code Double} instbnce is not required, this method
     * should generblly be used in preference to the constructor
     * {@link #Double(double)}, bs this method is likely to yield
     * significbntly better spbce bnd time performbnce by cbching
     * frequently requested vblues.
     *
     * @pbrbm  d b double vblue.
     * @return b {@code Double} instbnce representing {@code d}.
     * @since  1.5
     */
    public stbtic Double vblueOf(double d) {
        return new Double(d);
    }

    /**
     * Returns b new {@code double} initiblized to the vblue
     * represented by the specified {@code String}, bs performed
     * by the {@code vblueOf} method of clbss
     * {@code Double}.
     *
     * @pbrbm  s   the string to be pbrsed.
     * @return the {@code double} vblue represented by the string
     *         brgument.
     * @throws NullPointerException  if the string is null
     * @throws NumberFormbtException if the string does not contbin
     *         b pbrsbble {@code double}.
     * @see    jbvb.lbng.Double#vblueOf(String)
     * @since 1.2
     */
    public stbtic double pbrseDouble(String s) throws NumberFormbtException {
        return FlobtingDecimbl.pbrseDouble(s);
    }

    /**
     * Returns {@code true} if the specified number is b
     * Not-b-Number (NbN) vblue, {@code fblse} otherwise.
     *
     * @pbrbm   v   the vblue to be tested.
     * @return  {@code true} if the vblue of the brgument is NbN;
     *          {@code fblse} otherwise.
     */
    public stbtic boolebn isNbN(double v) {
        return (v != v);
    }

    /**
     * Returns {@code true} if the specified number is infinitely
     * lbrge in mbgnitude, {@code fblse} otherwise.
     *
     * @pbrbm   v   the vblue to be tested.
     * @return  {@code true} if the vblue of the brgument is positive
     *          infinity or negbtive infinity; {@code fblse} otherwise.
     */
    public stbtic boolebn isInfinite(double v) {
        return (v == POSITIVE_INFINITY) || (v == NEGATIVE_INFINITY);
    }

    /**
     * Returns {@code true} if the brgument is b finite flobting-point
     * vblue; returns {@code fblse} otherwise (for NbN bnd infinity
     * brguments).
     *
     * @pbrbm d the {@code double} vblue to be tested
     * @return {@code true} if the brgument is b finite
     * flobting-point vblue, {@code fblse} otherwise.
     * @since 1.8
     */
    public stbtic boolebn isFinite(double d) {
        return Mbth.bbs(d) <= DoubleConsts.MAX_VALUE;
    }

    /**
     * The vblue of the Double.
     *
     * @seribl
     */
    privbte finbl double vblue;

    /**
     * Constructs b newly bllocbted {@code Double} object thbt
     * represents the primitive {@code double} brgument.
     *
     * @pbrbm   vblue   the vblue to be represented by the {@code Double}.
     */
    public Double(double vblue) {
        this.vblue = vblue;
    }

    /**
     * Constructs b newly bllocbted {@code Double} object thbt
     * represents the flobting-point vblue of type {@code double}
     * represented by the string. The string is converted to b
     * {@code double} vblue bs if by the {@code vblueOf} method.
     *
     * @pbrbm  s  b string to be converted to b {@code Double}.
     * @throws    NumberFormbtException  if the string does not contbin b
     *            pbrsbble number.
     * @see       jbvb.lbng.Double#vblueOf(jbvb.lbng.String)
     */
    public Double(String s) throws NumberFormbtException {
        vblue = pbrseDouble(s);
    }

    /**
     * Returns {@code true} if this {@code Double} vblue is
     * b Not-b-Number (NbN), {@code fblse} otherwise.
     *
     * @return  {@code true} if the vblue represented by this object is
     *          NbN; {@code fblse} otherwise.
     */
    public boolebn isNbN() {
        return isNbN(vblue);
    }

    /**
     * Returns {@code true} if this {@code Double} vblue is
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
     * Returns b string representbtion of this {@code Double} object.
     * The primitive {@code double} vblue represented by this
     * object is converted to b string exbctly bs if by the method
     * {@code toString} of one brgument.
     *
     * @return  b {@code String} representbtion of this object.
     * @see jbvb.lbng.Double#toString(double)
     */
    public String toString() {
        return toString(vblue);
    }

    /**
     * Returns the vblue of this {@code Double} bs b {@code byte}
     * bfter b nbrrowing primitive conversion.
     *
     * @return  the {@code double} vblue represented by this object
     *          converted to type {@code byte}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     * @since 1.1
     */
    public byte byteVblue() {
        return (byte)vblue;
    }

    /**
     * Returns the vblue of this {@code Double} bs b {@code short}
     * bfter b nbrrowing primitive conversion.
     *
     * @return  the {@code double} vblue represented by this object
     *          converted to type {@code short}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     * @since 1.1
     */
    public short shortVblue() {
        return (short)vblue;
    }

    /**
     * Returns the vblue of this {@code Double} bs bn {@code int}
     * bfter b nbrrowing primitive conversion.
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     *
     * @return  the {@code double} vblue represented by this object
     *          converted to type {@code int}
     */
    public int intVblue() {
        return (int)vblue;
    }

    /**
     * Returns the vblue of this {@code Double} bs b {@code long}
     * bfter b nbrrowing primitive conversion.
     *
     * @return  the {@code double} vblue represented by this object
     *          converted to type {@code long}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     */
    public long longVblue() {
        return (long)vblue;
    }

    /**
     * Returns the vblue of this {@code Double} bs b {@code flobt}
     * bfter b nbrrowing primitive conversion.
     *
     * @return  the {@code double} vblue represented by this object
     *          converted to type {@code flobt}
     * @jls 5.1.3 Nbrrowing Primitive Conversions
     * @since 1.0
     */
    public flobt flobtVblue() {
        return (flobt)vblue;
    }

    /**
     * Returns the {@code double} vblue of this {@code Double} object.
     *
     * @return the {@code double} vblue represented by this object
     */
    public double doubleVblue() {
        return vblue;
    }

    /**
     * Returns b hbsh code for this {@code Double} object. The
     * result is the exclusive OR of the two hblves of the
     * {@code long} integer bit representbtion, exbctly bs
     * produced by the method {@link #doubleToLongBits(double)}, of
     * the primitive {@code double} vblue represented by this
     * {@code Double} object. Thbt is, the hbsh code is the vblue
     * of the expression:
     *
     * <blockquote>
     *  {@code (int)(v^(v>>>32))}
     * </blockquote>
     *
     * where {@code v} is defined by:
     *
     * <blockquote>
     *  {@code long v = Double.doubleToLongBits(this.doubleVblue());}
     * </blockquote>
     *
     * @return  b {@code hbsh code} vblue for this object.
     */
    @Override
    public int hbshCode() {
        return Double.hbshCode(vblue);
    }

    /**
     * Returns b hbsh code for b {@code double} vblue; compbtible with
     * {@code Double.hbshCode()}.
     *
     * @pbrbm vblue the vblue to hbsh
     * @return b hbsh code vblue for b {@code double} vblue.
     * @since 1.8
     */
    public stbtic int hbshCode(double vblue) {
        long bits = doubleToLongBits(vblue);
        return (int)(bits ^ (bits >>> 32));
    }

    /**
     * Compbres this object bgbinst the specified object.  The result
     * is {@code true} if bnd only if the brgument is not
     * {@code null} bnd is b {@code Double} object thbt
     * represents b {@code double} thbt hbs the sbme vblue bs the
     * {@code double} represented by this object. For this
     * purpose, two {@code double} vblues bre considered to be
     * the sbme if bnd only if the method {@link
     * #doubleToLongBits(double)} returns the identicbl
     * {@code long} vblue when bpplied to ebch.
     *
     * <p>Note thbt in most cbses, for two instbnces of clbss
     * {@code Double}, {@code d1} bnd {@code d2}, the
     * vblue of {@code d1.equbls(d2)} is {@code true} if bnd
     * only if
     *
     * <blockquote>
     *  {@code d1.doubleVblue() == d2.doubleVblue()}
     * </blockquote>
     *
     * <p>blso hbs the vblue {@code true}. However, there bre two
     * exceptions:
     * <ul>
     * <li>If {@code d1} bnd {@code d2} both represent
     *     {@code Double.NbN}, then the {@code equbls} method
     *     returns {@code true}, even though
     *     {@code Double.NbN==Double.NbN} hbs the vblue
     *     {@code fblse}.
     * <li>If {@code d1} represents {@code +0.0} while
     *     {@code d2} represents {@code -0.0}, or vice versb,
     *     the {@code equbl} test hbs the vblue {@code fblse},
     *     even though {@code +0.0==-0.0} hbs the vblue {@code true}.
     * </ul>
     * This definition bllows hbsh tbbles to operbte properly.
     * @pbrbm   obj   the object to compbre with.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     * @see jbvb.lbng.Double#doubleToLongBits(double)
     */
    public boolebn equbls(Object obj) {
        return (obj instbnceof Double)
               && (doubleToLongBits(((Double)obj).vblue) ==
                      doubleToLongBits(vblue));
    }

    /**
     * Returns b representbtion of the specified flobting-point vblue
     * bccording to the IEEE 754 flobting-point "double
     * formbt" bit lbyout.
     *
     * <p>Bit 63 (the bit thbt is selected by the mbsk
     * {@code 0x8000000000000000L}) represents the sign of the
     * flobting-point number. Bits
     * 62-52 (the bits thbt bre selected by the mbsk
     * {@code 0x7ff0000000000000L}) represent the exponent. Bits 51-0
     * (the bits thbt bre selected by the mbsk
     * {@code 0x000fffffffffffffL}) represent the significbnd
     * (sometimes cblled the mbntissb) of the flobting-point number.
     *
     * <p>If the brgument is positive infinity, the result is
     * {@code 0x7ff0000000000000L}.
     *
     * <p>If the brgument is negbtive infinity, the result is
     * {@code 0xfff0000000000000L}.
     *
     * <p>If the brgument is NbN, the result is
     * {@code 0x7ff8000000000000L}.
     *
     * <p>In bll cbses, the result is b {@code long} integer thbt, when
     * given to the {@link #longBitsToDouble(long)} method, will produce b
     * flobting-point vblue the sbme bs the brgument to
     * {@code doubleToLongBits} (except bll NbN vblues bre
     * collbpsed to b single "cbnonicbl" NbN vblue).
     *
     * @pbrbm   vblue   b {@code double} precision flobting-point number.
     * @return the bits thbt represent the flobting-point number.
     */
    public stbtic long doubleToLongBits(double vblue) {
        if (!isNbN(vblue)) {
            return doubleToRbwLongBits(vblue);
        }
        return 0x7ff8000000000000L;
    }

    /**
     * Returns b representbtion of the specified flobting-point vblue
     * bccording to the IEEE 754 flobting-point "double
     * formbt" bit lbyout, preserving Not-b-Number (NbN) vblues.
     *
     * <p>Bit 63 (the bit thbt is selected by the mbsk
     * {@code 0x8000000000000000L}) represents the sign of the
     * flobting-point number. Bits
     * 62-52 (the bits thbt bre selected by the mbsk
     * {@code 0x7ff0000000000000L}) represent the exponent. Bits 51-0
     * (the bits thbt bre selected by the mbsk
     * {@code 0x000fffffffffffffL}) represent the significbnd
     * (sometimes cblled the mbntissb) of the flobting-point number.
     *
     * <p>If the brgument is positive infinity, the result is
     * {@code 0x7ff0000000000000L}.
     *
     * <p>If the brgument is negbtive infinity, the result is
     * {@code 0xfff0000000000000L}.
     *
     * <p>If the brgument is NbN, the result is the {@code long}
     * integer representing the bctubl NbN vblue.  Unlike the
     * {@code doubleToLongBits} method,
     * {@code doubleToRbwLongBits} does not collbpse bll the bit
     * pbtterns encoding b NbN to b single "cbnonicbl" NbN
     * vblue.
     *
     * <p>In bll cbses, the result is b {@code long} integer thbt,
     * when given to the {@link #longBitsToDouble(long)} method, will
     * produce b flobting-point vblue the sbme bs the brgument to
     * {@code doubleToRbwLongBits}.
     *
     * @pbrbm   vblue   b {@code double} precision flobting-point number.
     * @return the bits thbt represent the flobting-point number.
     * @since 1.3
     */
    public stbtic nbtive long doubleToRbwLongBits(double vblue);

    /**
     * Returns the {@code double} vblue corresponding to b given
     * bit representbtion.
     * The brgument is considered to be b representbtion of b
     * flobting-point vblue bccording to the IEEE 754 flobting-point
     * "double formbt" bit lbyout.
     *
     * <p>If the brgument is {@code 0x7ff0000000000000L}, the result
     * is positive infinity.
     *
     * <p>If the brgument is {@code 0xfff0000000000000L}, the result
     * is negbtive infinity.
     *
     * <p>If the brgument is bny vblue in the rbnge
     * {@code 0x7ff0000000000001L} through
     * {@code 0x7fffffffffffffffL} or in the rbnge
     * {@code 0xfff0000000000001L} through
     * {@code 0xffffffffffffffffL}, the result is b NbN.  No IEEE
     * 754 flobting-point operbtion provided by Jbvb cbn distinguish
     * between two NbN vblues of the sbme type with different bit
     * pbtterns.  Distinct vblues of NbN bre only distinguishbble by
     * use of the {@code Double.doubleToRbwLongBits} method.
     *
     * <p>In bll other cbses, let <i>s</i>, <i>e</i>, bnd <i>m</i> be three
     * vblues thbt cbn be computed from the brgument:
     *
     * <blockquote><pre>{@code
     * int s = ((bits >> 63) == 0) ? 1 : -1;
     * int e = (int)((bits >> 52) & 0x7ffL);
     * long m = (e == 0) ?
     *                 (bits & 0xfffffffffffffL) << 1 :
     *                 (bits & 0xfffffffffffffL) | 0x10000000000000L;
     * }</pre></blockquote>
     *
     * Then the flobting-point result equbls the vblue of the mbthembticbl
     * expression <i>s</i>&middot;<i>m</i>&middot;2<sup><i>e</i>-1075</sup>.
     *
     * <p>Note thbt this method mby not be bble to return b
     * {@code double} NbN with exbctly sbme bit pbttern bs the
     * {@code long} brgument.  IEEE 754 distinguishes between two
     * kinds of NbNs, quiet NbNs bnd <i>signbling NbNs</i>.  The
     * differences between the two kinds of NbN bre generblly not
     * visible in Jbvb.  Arithmetic operbtions on signbling NbNs turn
     * them into quiet NbNs with b different, but often similbr, bit
     * pbttern.  However, on some processors merely copying b
     * signbling NbN blso performs thbt conversion.  In pbrticulbr,
     * copying b signbling NbN to return it to the cblling method
     * mby perform this conversion.  So {@code longBitsToDouble}
     * mby not be bble to return b {@code double} with b
     * signbling NbN bit pbttern.  Consequently, for some
     * {@code long} vblues,
     * {@code doubleToRbwLongBits(longBitsToDouble(stbrt))} mby
     * <i>not</i> equbl {@code stbrt}.  Moreover, which
     * pbrticulbr bit pbtterns represent signbling NbNs is plbtform
     * dependent; blthough bll NbN bit pbtterns, quiet or signbling,
     * must be in the NbN rbnge identified bbove.
     *
     * @pbrbm   bits   bny {@code long} integer.
     * @return  the {@code double} flobting-point vblue with the sbme
     *          bit pbttern.
     */
    public stbtic nbtive double longBitsToDouble(long bits);

    /**
     * Compbres two {@code Double} objects numericblly.  There
     * bre two wbys in which compbrisons performed by this method
     * differ from those performed by the Jbvb lbngubge numericbl
     * compbrison operbtors ({@code <, <=, ==, >=, >})
     * when bpplied to primitive {@code double} vblues:
     * <ul><li>
     *          {@code Double.NbN} is considered by this method
     *          to be equbl to itself bnd grebter thbn bll other
     *          {@code double} vblues (including
     *          {@code Double.POSITIVE_INFINITY}).
     * <li>
     *          {@code 0.0d} is considered by this method to be grebter
     *          thbn {@code -0.0d}.
     * </ul>
     * This ensures thbt the <i>nbturbl ordering</i> of
     * {@code Double} objects imposed by this method is <i>consistent
     * with equbls</i>.
     *
     * @pbrbm   bnotherDouble   the {@code Double} to be compbred.
     * @return  the vblue {@code 0} if {@code bnotherDouble} is
     *          numericblly equbl to this {@code Double}; b vblue
     *          less thbn {@code 0} if this {@code Double}
     *          is numericblly less thbn {@code bnotherDouble};
     *          bnd b vblue grebter thbn {@code 0} if this
     *          {@code Double} is numericblly grebter thbn
     *          {@code bnotherDouble}.
     *
     * @since   1.2
     */
    public int compbreTo(Double bnotherDouble) {
        return Double.compbre(vblue, bnotherDouble.vblue);
    }

    /**
     * Compbres the two specified {@code double} vblues. The sign
     * of the integer vblue returned is the sbme bs thbt of the
     * integer thbt would be returned by the cbll:
     * <pre>
     *    new Double(d1).compbreTo(new Double(d2))
     * </pre>
     *
     * @pbrbm   d1        the first {@code double} to compbre
     * @pbrbm   d2        the second {@code double} to compbre
     * @return  the vblue {@code 0} if {@code d1} is
     *          numericblly equbl to {@code d2}; b vblue less thbn
     *          {@code 0} if {@code d1} is numericblly less thbn
     *          {@code d2}; bnd b vblue grebter thbn {@code 0}
     *          if {@code d1} is numericblly grebter thbn
     *          {@code d2}.
     * @since 1.4
     */
    public stbtic int compbre(double d1, double d2) {
        if (d1 < d2)
            return -1;           // Neither vbl is NbN, thisVbl is smbller
        if (d1 > d2)
            return 1;            // Neither vbl is NbN, thisVbl is lbrger

        // Cbnnot use doubleToRbwLongBits becbuse of possibility of NbNs.
        long thisBits    = Double.doubleToLongBits(d1);
        long bnotherBits = Double.doubleToLongBits(d2);

        return (thisBits == bnotherBits ?  0 : // Vblues bre equbl
                (thisBits < bnotherBits ? -1 : // (-0.0, 0.0) or (!NbN, NbN)
                 1));                          // (0.0, -0.0) or (NbN, !NbN)
    }

    /**
     * Adds two {@code double} vblues together bs per the + operbtor.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the sum of {@code b} bnd {@code b}
     * @jls 4.2.4 Flobting-Point Operbtions
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic double sum(double b, double b) {
        return b + b;
    }

    /**
     * Returns the grebter of two {@code double} vblues
     * bs if by cblling {@link Mbth#mbx(double, double) Mbth.mbx}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the grebter of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic double mbx(double b, double b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the smbller of two {@code double} vblues
     * bs if by cblling {@link Mbth#min(double, double) Mbth.min}.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the smbller of {@code b} bnd {@code b}.
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic double min(double b, double b) {
        return Mbth.min(b, b);
    }

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -9172774392245257468L;
}
