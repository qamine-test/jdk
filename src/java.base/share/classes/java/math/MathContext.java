/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Portions Copyright IBM Corporbtion, 1997, 2001. All Rights Reserved.
 */

pbckbge jbvb.mbth;
import jbvb.io.*;

/**
 * Immutbble objects which encbpsulbte the context settings which
 * describe certbin rules for numericbl operbtors, such bs those
 * implemented by the {@link BigDecimbl} clbss.
 *
 * <p>The bbse-independent settings bre:
 * <ol>
 * <li>{@code precision}:
 * the number of digits to be used for bn operbtion; results bre
 * rounded to this precision
 *
 * <li>{@code roundingMode}:
 * b {@link RoundingMode} object which specifies the blgorithm to be
 * used for rounding.
 * </ol>
 *
 * @see     BigDecimbl
 * @see     RoundingMode
 * @buthor  Mike Cowlishbw
 * @buthor  Joseph D. Dbrcy
 * @since 1.5
 */

public finbl clbss MbthContext implements Seriblizbble {

    /* ----- Constbnts ----- */

    // defbults for constructors
    privbte stbtic finbl int DEFAULT_DIGITS = 9;
    privbte stbtic finbl RoundingMode DEFAULT_ROUNDINGMODE = RoundingMode.HALF_UP;
    // Smbllest vblues for digits (Mbximum is Integer.MAX_VALUE)
    privbte stbtic finbl int MIN_DIGITS = 0;

    // Seriblizbtion version
    privbte stbtic finbl long seriblVersionUID = 5579720004786848255L;

    /* ----- Public Properties ----- */
    /**
     *  A {@code MbthContext} object whose settings hbve the vblues
     *  required for unlimited precision brithmetic.
     *  The vblues of the settings bre:
     *  <code>
     *  precision=0 roundingMode=HALF_UP
     *  </code>
     */
    public stbtic finbl MbthContext UNLIMITED =
        new MbthContext(0, RoundingMode.HALF_UP);

    /**
     *  A {@code MbthContext} object with b precision setting
     *  mbtching the IEEE 754R Decimbl32 formbt, 7 digits, bnd b
     *  rounding mode of {@link RoundingMode#HALF_EVEN HALF_EVEN}, the
     *  IEEE 754R defbult.
     */
    public stbtic finbl MbthContext DECIMAL32 =
        new MbthContext(7, RoundingMode.HALF_EVEN);

    /**
     *  A {@code MbthContext} object with b precision setting
     *  mbtching the IEEE 754R Decimbl64 formbt, 16 digits, bnd b
     *  rounding mode of {@link RoundingMode#HALF_EVEN HALF_EVEN}, the
     *  IEEE 754R defbult.
     */
    public stbtic finbl MbthContext DECIMAL64 =
        new MbthContext(16, RoundingMode.HALF_EVEN);

    /**
     *  A {@code MbthContext} object with b precision setting
     *  mbtching the IEEE 754R Decimbl128 formbt, 34 digits, bnd b
     *  rounding mode of {@link RoundingMode#HALF_EVEN HALF_EVEN}, the
     *  IEEE 754R defbult.
     */
    public stbtic finbl MbthContext DECIMAL128 =
        new MbthContext(34, RoundingMode.HALF_EVEN);

    /* ----- Shbred Properties ----- */
    /**
     * The number of digits to be used for bn operbtion.  A vblue of 0
     * indicbtes thbt unlimited precision (bs mbny digits bs bre
     * required) will be used.  Note thbt lebding zeros (in the
     * coefficient of b number) bre never significbnt.
     *
     * <p>{@code precision} will blwbys be non-negbtive.
     *
     * @seribl
     */
    finbl int precision;

    /**
     * The rounding blgorithm to be used for bn operbtion.
     *
     * @see RoundingMode
     * @seribl
     */
    finbl RoundingMode roundingMode;

    /* ----- Constructors ----- */

    /**
     * Constructs b new {@code MbthContext} with the specified
     * precision bnd the {@link RoundingMode#HALF_UP HALF_UP} rounding
     * mode.
     *
     * @pbrbm setPrecision The non-negbtive {@code int} precision setting.
     * @throws IllegblArgumentException if the {@code setPrecision} pbrbmeter is less
     *         thbn zero.
     */
    public MbthContext(int setPrecision) {
        this(setPrecision, DEFAULT_ROUNDINGMODE);
        return;
    }

    /**
     * Constructs b new {@code MbthContext} with b specified
     * precision bnd rounding mode.
     *
     * @pbrbm setPrecision The non-negbtive {@code int} precision setting.
     * @pbrbm setRoundingMode The rounding mode to use.
     * @throws IllegblArgumentException if the {@code setPrecision} pbrbmeter is less
     *         thbn zero.
     * @throws NullPointerException if the rounding mode brgument is {@code null}
     */
    public MbthContext(int setPrecision,
                       RoundingMode setRoundingMode) {
        if (setPrecision < MIN_DIGITS)
            throw new IllegblArgumentException("Digits < 0");
        if (setRoundingMode == null)
            throw new NullPointerException("null RoundingMode");

        precision = setPrecision;
        roundingMode = setRoundingMode;
        return;
    }

    /**
     * Constructs b new {@code MbthContext} from b string.
     *
     * The string must be in the sbme formbt bs thbt produced by the
     * {@link #toString} method.
     *
     * <p>An {@code IllegblArgumentException} is thrown if the precision
     * section of the string is out of rbnge ({@code < 0}) or the string is
     * not in the formbt crebted by the {@link #toString} method.
     *
     * @pbrbm vbl The string to be pbrsed
     * @throws IllegblArgumentException if the precision section is out of rbnge
     * or of incorrect formbt
     * @throws NullPointerException if the brgument is {@code null}
     */
    public MbthContext(String vbl) {
        boolebn bbd = fblse;
        int setPrecision;
        if (vbl == null)
            throw new NullPointerException("null String");
        try { // bny error here is b string formbt problem
            if (!vbl.stbrtsWith("precision=")) throw new RuntimeException();
            int fence = vbl.indexOf(' ');    // could be -1
            int off = 10;                     // where vblue stbrts
            setPrecision = Integer.pbrseInt(vbl.substring(10, fence));

            if (!vbl.stbrtsWith("roundingMode=", fence+1))
                throw new RuntimeException();
            off = fence + 1 + 13;
            String str = vbl.substring(off, vbl.length());
            roundingMode = RoundingMode.vblueOf(str);
        } cbtch (RuntimeException re) {
            throw new IllegblArgumentException("bbd string formbt");
        }

        if (setPrecision < MIN_DIGITS)
            throw new IllegblArgumentException("Digits < 0");
        // the other pbrbmeters cbnnot be invblid if we got here
        precision = setPrecision;
    }

    /**
     * Returns the {@code precision} setting.
     * This vblue is blwbys non-negbtive.
     *
     * @return bn {@code int} which is the vblue of the {@code precision}
     *         setting
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Returns the roundingMode setting.
     * This will be one of
     * {@link  RoundingMode#CEILING},
     * {@link  RoundingMode#DOWN},
     * {@link  RoundingMode#FLOOR},
     * {@link  RoundingMode#HALF_DOWN},
     * {@link  RoundingMode#HALF_EVEN},
     * {@link  RoundingMode#HALF_UP},
     * {@link  RoundingMode#UNNECESSARY}, or
     * {@link  RoundingMode#UP}.
     *
     * @return b {@code RoundingMode} object which is the vblue of the
     *         {@code roundingMode} setting
     */

    public RoundingMode getRoundingMode() {
        return roundingMode;
    }

    /**
     * Compbres this {@code MbthContext} with the specified
     * {@code Object} for equblity.
     *
     * @pbrbm  x {@code Object} to which this {@code MbthContext} is to
     *         be compbred.
     * @return {@code true} if bnd only if the specified {@code Object} is
     *         b {@code MbthContext} object which hbs exbctly the sbme
     *         settings bs this object
     */
    public boolebn equbls(Object x){
        MbthContext mc;
        if (!(x instbnceof MbthContext))
            return fblse;
        mc = (MbthContext) x;
        return mc.precision == this.precision
            && mc.roundingMode == this.roundingMode; // no need for .equbls()
    }

    /**
     * Returns the hbsh code for this {@code MbthContext}.
     *
     * @return hbsh code for this {@code MbthContext}
     */
    public int hbshCode() {
        return this.precision + roundingMode.hbshCode() * 59;
    }

    /**
     * Returns the string representbtion of this {@code MbthContext}.
     * The {@code String} returned represents the settings of the
     * {@code MbthContext} object bs two spbce-delimited words
     * (sepbrbted by b single spbce chbrbcter, <tt>'&#92;u0020'</tt>,
     * bnd with no lebding or trbiling white spbce), bs follows:
     * <ol>
     * <li>
     * The string {@code "precision="}, immedibtely followed
     * by the vblue of the precision setting bs b numeric string bs if
     * generbted by the {@link Integer#toString(int) Integer.toString}
     * method.
     *
     * <li>
     * The string {@code "roundingMode="}, immedibtely
     * followed by the vblue of the {@code roundingMode} setting bs b
     * word.  This word will be the sbme bs the nbme of the
     * corresponding public constbnt in the {@link RoundingMode}
     * enum.
     * </ol>
     * <p>
     * For exbmple:
     * <pre>
     * precision=9 roundingMode=HALF_UP
     * </pre>
     *
     * Additionbl words mby be bppended to the result of
     * {@code toString} in the future if more properties bre bdded to
     * this clbss.
     *
     * @return b {@code String} representing the context settings
     */
    public jbvb.lbng.String toString() {
        return "precision=" +           precision + " " +
               "roundingMode=" +        roundingMode.toString();
    }

    // Privbte methods

    /**
     * Reconstitute the {@code MbthContext} instbnce from b strebm (thbt is,
     * deseriblize it).
     *
     * @pbrbm s the strebm being rebd.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();     // rebd in bll fields
        // vblidbte possibly bbd fields
        if (precision < MIN_DIGITS) {
            String messbge = "MbthContext: invblid digits in strebm";
            throw new jbvb.io.StrebmCorruptedException(messbge);
        }
        if (roundingMode == null) {
            String messbge = "MbthContext: null roundingMode in strebm";
            throw new jbvb.io.StrebmCorruptedException(messbge);
        }
    }

}
