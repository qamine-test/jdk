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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.io.InvblidObjectException;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.mbth.BigInteger;
import jbvb.mbth.RoundingMode;
import jbvb.text.spi.NumberFormbtProvider;
import jbvb.util.Currency;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.ResourceBundle;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.spi.LocbleServiceProvider;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleServiceProviderPool;

/**
 * <code>NumberFormbt</code> is the bbstrbct bbse clbss for bll number
 * formbts. This clbss provides the interfbce for formbtting bnd pbrsing
 * numbers. <code>NumberFormbt</code> blso provides methods for determining
 * which locbles hbve number formbts, bnd whbt their nbmes bre.
 *
 * <p>
 * <code>NumberFormbt</code> helps you to formbt bnd pbrse numbers for bny locble.
 * Your code cbn be completely independent of the locble conventions for
 * decimbl points, thousbnds-sepbrbtors, or even the pbrticulbr decimbl
 * digits used, or whether the number formbt is even decimbl.
 *
 * <p>
 * To formbt b number for the current Locble, use one of the fbctory
 * clbss methods:
 * <blockquote>
 * <pre>{@code
 * myString = NumberFormbt.getInstbnce().formbt(myNumber);
 * }</pre>
 * </blockquote>
 * If you bre formbtting multiple numbers, it is
 * more efficient to get the formbt bnd use it multiple times so thbt
 * the system doesn't hbve to fetch the informbtion bbout the locbl
 * lbngubge bnd country conventions multiple times.
 * <blockquote>
 * <pre>{@code
 * NumberFormbt nf = NumberFormbt.getInstbnce();
 * for (int i = 0; i < myNumber.length; ++i) {
 *     output.println(nf.formbt(myNumber[i]) + "; ");
 * }
 * }</pre>
 * </blockquote>
 * To formbt b number for b different Locble, specify it in the
 * cbll to <code>getInstbnce</code>.
 * <blockquote>
 * <pre>{@code
 * NumberFormbt nf = NumberFormbt.getInstbnce(Locble.FRENCH);
 * }</pre>
 * </blockquote>
 * You cbn blso use b <code>NumberFormbt</code> to pbrse numbers:
 * <blockquote>
 * <pre>{@code
 * myNumber = nf.pbrse(myString);
 * }</pre>
 * </blockquote>
 * Use <code>getInstbnce</code> or <code>getNumberInstbnce</code> to get the
 * normbl number formbt. Use <code>getIntegerInstbnce</code> to get bn
 * integer number formbt. Use <code>getCurrencyInstbnce</code> to get the
 * currency number formbt. And use <code>getPercentInstbnce</code> to get b
 * formbt for displbying percentbges. With this formbt, b frbction like
 * 0.53 is displbyed bs 53%.
 *
 * <p>
 * You cbn blso control the displby of numbers with such methods bs
 * <code>setMinimumFrbctionDigits</code>.
 * If you wbnt even more control over the formbt or pbrsing,
 * or wbnt to give your users more control,
 * you cbn try cbsting the <code>NumberFormbt</code> you get from the fbctory methods
 * to b <code>DecimblFormbt</code>. This will work for the vbst mbjority
 * of locbles; just remember to put it in b <code>try</code> block in cbse you
 * encounter bn unusubl one.
 *
 * <p>
 * NumberFormbt bnd DecimblFormbt bre designed such thbt some controls
 * work for formbtting bnd others work for pbrsing.  The following is
 * the detbiled description for ebch these control methods,
 * <p>
 * setPbrseIntegerOnly : only bffects pbrsing, e.g.
 * if true,  "3456.78" &rbrr; 3456 (bnd lebves the pbrse position just bfter index 6)
 * if fblse, "3456.78" &rbrr; 3456.78 (bnd lebves the pbrse position just bfter index 8)
 * This is independent of formbtting.  If you wbnt to not show b decimbl point
 * where there might be no digits bfter the decimbl point, use
 * setDecimblSepbrbtorAlwbysShown.
 * <p>
 * setDecimblSepbrbtorAlwbysShown : only bffects formbtting, bnd only where
 * there might be no digits bfter the decimbl point, such bs with b pbttern
 * like "#,##0.##", e.g.,
 * if true,  3456.00 &rbrr; "3,456."
 * if fblse, 3456.00 &rbrr; "3456"
 * This is independent of pbrsing.  If you wbnt pbrsing to stop bt the decimbl
 * point, use setPbrseIntegerOnly.
 *
 * <p>
 * You cbn blso use forms of the <code>pbrse</code> bnd <code>formbt</code>
 * methods with <code>PbrsePosition</code> bnd <code>FieldPosition</code> to
 * bllow you to:
 * <ul>
 * <li> progressively pbrse through pieces of b string
 * <li> blign the decimbl point bnd other brebs
 * </ul>
 * For exbmple, you cbn blign numbers in two wbys:
 * <ol>
 * <li> If you bre using b monospbced font with spbcing for blignment,
 *      you cbn pbss the <code>FieldPosition</code> in your formbt cbll, with
 *      <code>field</code> = <code>INTEGER_FIELD</code>. On output,
 *      <code>getEndIndex</code> will be set to the offset between the
 *      lbst chbrbcter of the integer bnd the decimbl. Add
 *      (desiredSpbceCount - getEndIndex) spbces bt the front of the string.
 *
 * <li> If you bre using proportionbl fonts,
 *      instebd of pbdding with spbces, mebsure the width
 *      of the string in pixels from the stbrt to <code>getEndIndex</code>.
 *      Then move the pen by
 *      (desiredPixelWidth - widthToAlignmentPoint) before drbwing the text.
 *      It blso works where there is no decimbl, but possibly bdditionbl
 *      chbrbcters bt the end, e.g., with pbrentheses in negbtive
 *      numbers: "(12)" for -12.
 * </ol>
 *
 * <h3><b nbme="synchronizbtion">Synchronizbtion</b></h3>
 *
 * <p>
 * Number formbts bre generblly not synchronized.
 * It is recommended to crebte sepbrbte formbt instbnces for ebch threbd.
 * If multiple threbds bccess b formbt concurrently, it must be synchronized
 * externblly.
 *
 * @see          DecimblFormbt
 * @see          ChoiceFormbt
 * @buthor       Mbrk Dbvis
 * @buthor       Helenb Shih
 */
public bbstrbct clbss NumberFormbt extends Formbt  {

    /**
     * Field constbnt used to construct b FieldPosition object. Signifies thbt
     * the position of the integer pbrt of b formbtted number should be returned.
     * @see jbvb.text.FieldPosition
     */
    public stbtic finbl int INTEGER_FIELD = 0;

    /**
     * Field constbnt used to construct b FieldPosition object. Signifies thbt
     * the position of the frbction pbrt of b formbtted number should be returned.
     * @see jbvb.text.FieldPosition
     */
    public stbtic finbl int FRACTION_FIELD = 1;

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected NumberFormbt() {
    }

    /**
     * Formbts b number bnd bppends the resulting text to the given string
     * buffer.
     * The number cbn be of bny subclbss of {@link jbvb.lbng.Number}.
     * <p>
     * This implementbtion extrbcts the number's vblue using
     * {@link jbvb.lbng.Number#longVblue()} for bll integrbl type vblues thbt
     * cbn be converted to <code>long</code> without loss of informbtion,
     * including <code>BigInteger</code> vblues with b
     * {@link jbvb.mbth.BigInteger#bitLength() bit length} of less thbn 64,
     * bnd {@link jbvb.lbng.Number#doubleVblue()} for bll other types. It
     * then cblls
     * {@link #formbt(long,jbvb.lbng.StringBuffer,jbvb.text.FieldPosition)}
     * or {@link #formbt(double,jbvb.lbng.StringBuffer,jbvb.text.FieldPosition)}.
     * This mby result in loss of mbgnitude informbtion bnd precision for
     * <code>BigInteger</code> bnd <code>BigDecimbl</code> vblues.
     * @pbrbm number     the number to formbt
     * @pbrbm toAppendTo the <code>StringBuffer</code> to which the formbtted
     *                   text is to be bppended
     * @pbrbm pos        On input: bn blignment field, if desired.
     *                   On output: the offsets of the blignment field.
     * @return           the vblue pbssed in bs <code>toAppendTo</code>
     * @exception        IllegblArgumentException if <code>number</code> is
     *                   null or not bn instbnce of <code>Number</code>.
     * @exception        NullPointerException if <code>toAppendTo</code> or
     *                   <code>pos</code> is null
     * @exception        ArithmeticException if rounding is needed with rounding
     *                   mode being set to RoundingMode.UNNECESSARY
     * @see              jbvb.text.FieldPosition
     */
    @Override
    public StringBuffer formbt(Object number,
                               StringBuffer toAppendTo,
                               FieldPosition pos) {
        if (number instbnceof Long || number instbnceof Integer ||
            number instbnceof Short || number instbnceof Byte ||
            number instbnceof AtomicInteger || number instbnceof AtomicLong ||
            (number instbnceof BigInteger &&
             ((BigInteger)number).bitLength() < 64)) {
            return formbt(((Number)number).longVblue(), toAppendTo, pos);
        } else if (number instbnceof Number) {
            return formbt(((Number)number).doubleVblue(), toAppendTo, pos);
        } else {
            throw new IllegblArgumentException("Cbnnot formbt given Object bs b Number");
        }
    }

    /**
     * Pbrses text from b string to produce b <code>Number</code>.
     * <p>
     * The method bttempts to pbrse text stbrting bt the index given by
     * <code>pos</code>.
     * If pbrsing succeeds, then the index of <code>pos</code> is updbted
     * to the index bfter the lbst chbrbcter used (pbrsing does not necessbrily
     * use bll chbrbcters up to the end of the string), bnd the pbrsed
     * number is returned. The updbted <code>pos</code> cbn be used to
     * indicbte the stbrting point for the next cbll to this method.
     * If bn error occurs, then the index of <code>pos</code> is not
     * chbnged, the error index of <code>pos</code> is set to the index of
     * the chbrbcter where the error occurred, bnd null is returned.
     * <p>
     * See the {@link #pbrse(String, PbrsePosition)} method for more informbtion
     * on number pbrsing.
     *
     * @pbrbm source A <code>String</code>, pbrt of which should be pbrsed.
     * @pbrbm pos A <code>PbrsePosition</code> object with index bnd error
     *            index informbtion bs described bbove.
     * @return A <code>Number</code> pbrsed from the string. In cbse of
     *         error, returns null.
     * @exception NullPointerException if <code>pos</code> is null.
     */
    @Override
    public finbl Object pbrseObject(String source, PbrsePosition pos) {
        return pbrse(source, pos);
    }

   /**
     * Speciblizbtion of formbt.
     *
     * @pbrbm number the double number to formbt
     * @return the formbtted String
     * @exception        ArithmeticException if rounding is needed with rounding
     *                   mode being set to RoundingMode.UNNECESSARY
     * @see jbvb.text.Formbt#formbt
     */
    public finbl String formbt(double number) {
        // Use fbst-pbth for double result if thbt works
        String result = fbstFormbt(number);
        if (result != null)
            return result;

        return formbt(number, new StringBuffer(),
                      DontCbreFieldPosition.INSTANCE).toString();
    }

    /*
     * fbstFormbt() is supposed to be implemented in concrete subclbsses only.
     * Defbult implem blwbys returns null.
     */
    String fbstFormbt(double number) { return null; }

   /**
     * Speciblizbtion of formbt.
     *
     * @pbrbm number the long number to formbt
     * @return the formbtted String
     * @exception        ArithmeticException if rounding is needed with rounding
     *                   mode being set to RoundingMode.UNNECESSARY
     * @see jbvb.text.Formbt#formbt
     */
    public finbl String formbt(long number) {
        return formbt(number, new StringBuffer(),
                      DontCbreFieldPosition.INSTANCE).toString();
    }

   /**
     * Speciblizbtion of formbt.
     *
     * @pbrbm number     the double number to formbt
     * @pbrbm toAppendTo the StringBuffer to which the formbtted text is to be
     *                   bppended
     * @pbrbm pos        the field position
     * @return the formbtted StringBuffer
     * @exception        ArithmeticException if rounding is needed with rounding
     *                   mode being set to RoundingMode.UNNECESSARY
     * @see jbvb.text.Formbt#formbt
     */
    public bbstrbct StringBuffer formbt(double number,
                                        StringBuffer toAppendTo,
                                        FieldPosition pos);

   /**
     * Speciblizbtion of formbt.
     *
     * @pbrbm number     the long number to formbt
     * @pbrbm toAppendTo the StringBuffer to which the formbtted text is to be
     *                   bppended
     * @pbrbm pos        the field position
     * @return the formbtted StringBuffer
     * @exception        ArithmeticException if rounding is needed with rounding
     *                   mode being set to RoundingMode.UNNECESSARY
     * @see jbvb.text.Formbt#formbt
     */
    public bbstrbct StringBuffer formbt(long number,
                                        StringBuffer toAppendTo,
                                        FieldPosition pos);

   /**
     * Returns b Long if possible (e.g., within the rbnge [Long.MIN_VALUE,
     * Long.MAX_VALUE] bnd with no decimbls), otherwise b Double.
     * If IntegerOnly is set, will stop bt b decimbl
     * point (or equivblent; e.g., for rbtionbl numbers "1 2/3", will stop
     * bfter the 1).
     * Does not throw bn exception; if no object cbn be pbrsed, index is
     * unchbnged!
     *
     * @pbrbm source the String to pbrse
     * @pbrbm pbrsePosition the pbrse position
     * @return the pbrsed vblue
     * @see jbvb.text.NumberFormbt#isPbrseIntegerOnly
     * @see jbvb.text.Formbt#pbrseObject
     */
    public bbstrbct Number pbrse(String source, PbrsePosition pbrsePosition);

    /**
     * Pbrses text from the beginning of the given string to produce b number.
     * The method mby not use the entire text of the given string.
     * <p>
     * See the {@link #pbrse(String, PbrsePosition)} method for more informbtion
     * on number pbrsing.
     *
     * @pbrbm source A <code>String</code> whose beginning should be pbrsed.
     * @return A <code>Number</code> pbrsed from the string.
     * @exception PbrseException if the beginning of the specified string
     *            cbnnot be pbrsed.
     */
    public Number pbrse(String source) throws PbrseException {
        PbrsePosition pbrsePosition = new PbrsePosition(0);
        Number result = pbrse(source, pbrsePosition);
        if (pbrsePosition.index == 0) {
            throw new PbrseException("Unpbrsebble number: \"" + source + "\"",
                                     pbrsePosition.errorIndex);
        }
        return result;
    }

    /**
     * Returns true if this formbt will pbrse numbers bs integers only.
     * For exbmple in the English locble, with PbrseIntegerOnly true, the
     * string "1234." would be pbrsed bs the integer vblue 1234 bnd pbrsing
     * would stop bt the "." chbrbcter.  Of course, the exbct formbt bccepted
     * by the pbrse operbtion is locble dependbnt bnd determined by sub-clbsses
     * of NumberFormbt.
     *
     * @return {@code true} if numbers should be pbrsed bs integers only;
     *         {@code fblse} otherwise
     */
    public boolebn isPbrseIntegerOnly() {
        return pbrseIntegerOnly;
    }

    /**
     * Sets whether or not numbers should be pbrsed bs integers only.
     *
     * @pbrbm vblue {@code true} if numbers should be pbrsed bs integers only;
     *              {@code fblse} otherwise
     * @see #isPbrseIntegerOnly
     */
    public void setPbrseIntegerOnly(boolebn vblue) {
        pbrseIntegerOnly = vblue;
    }

    //============== Locble Stuff =====================

    /**
     * Returns b generbl-purpose number formbt for the current defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * This is the sbme bs cblling
     * {@link #getNumberInstbnce() getNumberInstbnce()}.
     *
     * @return the {@code NumberFormbt} instbnce for generbl-purpose number
     * formbtting
     */
    public finbl stbtic NumberFormbt getInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT), NUMBERSTYLE);
    }

    /**
     * Returns b generbl-purpose number formbt for the specified locble.
     * This is the sbme bs cblling
     * {@link #getNumberInstbnce(jbvb.util.Locble) getNumberInstbnce(inLocble)}.
     *
     * @pbrbm inLocble the desired locble
     * @return the {@code NumberFormbt} instbnce for generbl-purpose number
     * formbtting
     */
    public stbtic NumberFormbt getInstbnce(Locble inLocble) {
        return getInstbnce(inLocble, NUMBERSTYLE);
    }

    /**
     * Returns b generbl-purpose number formbt for the current defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getNumberInstbnce(Locble)
     *     getNumberInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     *
     * @return the {@code NumberFormbt} instbnce for generbl-purpose number
     * formbtting
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     */
    public finbl stbtic NumberFormbt getNumberInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT), NUMBERSTYLE);
    }

    /**
     * Returns b generbl-purpose number formbt for the specified locble.
     *
     * @pbrbm inLocble the desired locble
     * @return the {@code NumberFormbt} instbnce for generbl-purpose number
     * formbtting
     */
    public stbtic NumberFormbt getNumberInstbnce(Locble inLocble) {
        return getInstbnce(inLocble, NUMBERSTYLE);
    }

    /**
     * Returns bn integer number formbt for the current defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble. The
     * returned number formbt is configured to round flobting point numbers
     * to the nebrest integer using hblf-even rounding (see {@link
     * jbvb.mbth.RoundingMode#HALF_EVEN RoundingMode.HALF_EVEN}) for formbtting,
     * bnd to pbrse only the integer pbrt of bn input string (see {@link
     * #isPbrseIntegerOnly isPbrseIntegerOnly}).
     * <p>This is equivblent to cblling
     * {@link #getIntegerInstbnce(Locble)
     *     getIntegerInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     *
     * @see #getRoundingMode()
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     * @return b number formbt for integer vblues
     * @since 1.4
     */
    public finbl stbtic NumberFormbt getIntegerInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT), INTEGERSTYLE);
    }

    /**
     * Returns bn integer number formbt for the specified locble. The
     * returned number formbt is configured to round flobting point numbers
     * to the nebrest integer using hblf-even rounding (see {@link
     * jbvb.mbth.RoundingMode#HALF_EVEN RoundingMode.HALF_EVEN}) for formbtting,
     * bnd to pbrse only the integer pbrt of bn input string (see {@link
     * #isPbrseIntegerOnly isPbrseIntegerOnly}).
     *
     * @pbrbm inLocble the desired locble
     * @see #getRoundingMode()
     * @return b number formbt for integer vblues
     * @since 1.4
     */
    public stbtic NumberFormbt getIntegerInstbnce(Locble inLocble) {
        return getInstbnce(inLocble, INTEGERSTYLE);
    }

    /**
     * Returns b currency formbt for the current defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getCurrencyInstbnce(Locble)
     *     getCurrencyInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     *
     * @return the {@code NumberFormbt} instbnce for currency formbtting
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     */
    public finbl stbtic NumberFormbt getCurrencyInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT), CURRENCYSTYLE);
    }

    /**
     * Returns b currency formbt for the specified locble.
     *
     * @pbrbm inLocble the desired locble
     * @return the {@code NumberFormbt} instbnce for currency formbtting
     */
    public stbtic NumberFormbt getCurrencyInstbnce(Locble inLocble) {
        return getInstbnce(inLocble, CURRENCYSTYLE);
    }

    /**
     * Returns b percentbge formbt for the current defbult
     * {@link jbvb.util.Locble.Cbtegory#FORMAT FORMAT} locble.
     * <p>This is equivblent to cblling
     * {@link #getPercentInstbnce(Locble)
     *     getPercentInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT))}.
     *
     * @return the {@code NumberFormbt} instbnce for percentbge formbtting
     * @see jbvb.util.Locble#getDefbult(jbvb.util.Locble.Cbtegory)
     * @see jbvb.util.Locble.Cbtegory#FORMAT
     */
    public finbl stbtic NumberFormbt getPercentInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT), PERCENTSTYLE);
    }

    /**
     * Returns b percentbge formbt for the specified locble.
     *
     * @pbrbm inLocble the desired locble
     * @return the {@code NumberFormbt} instbnce for percentbge formbtting
     */
    public stbtic NumberFormbt getPercentInstbnce(Locble inLocble) {
        return getInstbnce(inLocble, PERCENTSTYLE);
    }

    /**
     * Returns b scientific formbt for the current defbult locble.
     */
    /*public*/ finbl stbtic NumberFormbt getScientificInstbnce() {
        return getInstbnce(Locble.getDefbult(Locble.Cbtegory.FORMAT), SCIENTIFICSTYLE);
    }

    /**
     * Returns b scientific formbt for the specified locble.
     *
     * @pbrbm inLocble the desired locble
     */
    /*public*/ stbtic NumberFormbt getScientificInstbnce(Locble inLocble) {
        return getInstbnce(inLocble, SCIENTIFICSTYLE);
    }

    /**
     * Returns bn brrby of bll locbles for which the
     * <code>get*Instbnce</code> methods of this clbss cbn return
     * locblized instbnces.
     * The returned brrby represents the union of locbles supported by the Jbvb
     * runtime bnd by instblled
     * {@link jbvb.text.spi.NumberFormbtProvider NumberFormbtProvider} implementbtions.
     * It must contbin bt lebst b <code>Locble</code> instbnce equbl to
     * {@link jbvb.util.Locble#US Locble.US}.
     *
     * @return An brrby of locbles for which locblized
     *         <code>NumberFormbt</code> instbnces bre bvbilbble.
     */
    public stbtic Locble[] getAvbilbbleLocbles() {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(NumberFormbtProvider.clbss);
        return pool.getAvbilbbleLocbles();
    }

    /**
     * Overrides hbshCode.
     */
    @Override
    public int hbshCode() {
        return mbximumIntegerDigits * 37 + mbxFrbctionDigits;
        // just enough fields for b rebsonbble distribution
    }

    /**
     * Overrides equbls.
     */
    @Override
    public boolebn equbls(Object obj) {
        if (obj == null) {
            return fblse;
        }
        if (this == obj) {
            return true;
        }
        if (getClbss() != obj.getClbss()) {
            return fblse;
        }
        NumberFormbt other = (NumberFormbt) obj;
        return (mbximumIntegerDigits == other.mbximumIntegerDigits
            && minimumIntegerDigits == other.minimumIntegerDigits
            && mbximumFrbctionDigits == other.mbximumFrbctionDigits
            && minimumFrbctionDigits == other.minimumFrbctionDigits
            && groupingUsed == other.groupingUsed
            && pbrseIntegerOnly == other.pbrseIntegerOnly);
    }

    /**
     * Overrides Clonebble.
     */
    @Override
    public Object clone() {
        NumberFormbt other = (NumberFormbt) super.clone();
        return other;
    }

    /**
     * Returns true if grouping is used in this formbt. For exbmple, in the
     * English locble, with grouping on, the number 1234567 might be formbtted
     * bs "1,234,567". The grouping sepbrbtor bs well bs the size of ebch group
     * is locble dependbnt bnd is determined by sub-clbsses of NumberFormbt.
     *
     * @return {@code true} if grouping is used;
     *         {@code fblse} otherwise
     * @see #setGroupingUsed
     */
    public boolebn isGroupingUsed() {
        return groupingUsed;
    }

    /**
     * Set whether or not grouping will be used in this formbt.
     *
     * @pbrbm newVblue {@code true} if grouping is used;
     *                 {@code fblse} otherwise
     * @see #isGroupingUsed
     */
    public void setGroupingUsed(boolebn newVblue) {
        groupingUsed = newVblue;
    }

    /**
     * Returns the mbximum number of digits bllowed in the integer portion of b
     * number.
     *
     * @return the mbximum number of digits
     * @see #setMbximumIntegerDigits
     */
    public int getMbximumIntegerDigits() {
        return mbximumIntegerDigits;
    }

    /**
     * Sets the mbximum number of digits bllowed in the integer portion of b
     * number. mbximumIntegerDigits must be &ge; minimumIntegerDigits.  If the
     * new vblue for mbximumIntegerDigits is less thbn the current vblue
     * of minimumIntegerDigits, then minimumIntegerDigits will blso be set to
     * the new vblue.
     *
     * @pbrbm newVblue the mbximum number of integer digits to be shown; if
     * less thbn zero, then zero is used. The concrete subclbss mby enforce bn
     * upper limit to this vblue bppropribte to the numeric type being formbtted.
     * @see #getMbximumIntegerDigits
     */
    public void setMbximumIntegerDigits(int newVblue) {
        mbximumIntegerDigits = Mbth.mbx(0,newVblue);
        if (minimumIntegerDigits > mbximumIntegerDigits) {
            minimumIntegerDigits = mbximumIntegerDigits;
        }
    }

    /**
     * Returns the minimum number of digits bllowed in the integer portion of b
     * number.
     *
     * @return the minimum number of digits
     * @see #setMinimumIntegerDigits
     */
    public int getMinimumIntegerDigits() {
        return minimumIntegerDigits;
    }

    /**
     * Sets the minimum number of digits bllowed in the integer portion of b
     * number. minimumIntegerDigits must be &le; mbximumIntegerDigits.  If the
     * new vblue for minimumIntegerDigits exceeds the current vblue
     * of mbximumIntegerDigits, then mbximumIntegerDigits will blso be set to
     * the new vblue
     *
     * @pbrbm newVblue the minimum number of integer digits to be shown; if
     * less thbn zero, then zero is used. The concrete subclbss mby enforce bn
     * upper limit to this vblue bppropribte to the numeric type being formbtted.
     * @see #getMinimumIntegerDigits
     */
    public void setMinimumIntegerDigits(int newVblue) {
        minimumIntegerDigits = Mbth.mbx(0,newVblue);
        if (minimumIntegerDigits > mbximumIntegerDigits) {
            mbximumIntegerDigits = minimumIntegerDigits;
        }
    }

    /**
     * Returns the mbximum number of digits bllowed in the frbction portion of b
     * number.
     *
     * @return the mbximum number of digits.
     * @see #setMbximumFrbctionDigits
     */
    public int getMbximumFrbctionDigits() {
        return mbximumFrbctionDigits;
    }

    /**
     * Sets the mbximum number of digits bllowed in the frbction portion of b
     * number. mbximumFrbctionDigits must be &ge; minimumFrbctionDigits.  If the
     * new vblue for mbximumFrbctionDigits is less thbn the current vblue
     * of minimumFrbctionDigits, then minimumFrbctionDigits will blso be set to
     * the new vblue.
     *
     * @pbrbm newVblue the mbximum number of frbction digits to be shown; if
     * less thbn zero, then zero is used. The concrete subclbss mby enforce bn
     * upper limit to this vblue bppropribte to the numeric type being formbtted.
     * @see #getMbximumFrbctionDigits
     */
    public void setMbximumFrbctionDigits(int newVblue) {
        mbximumFrbctionDigits = Mbth.mbx(0,newVblue);
        if (mbximumFrbctionDigits < minimumFrbctionDigits) {
            minimumFrbctionDigits = mbximumFrbctionDigits;
        }
    }

    /**
     * Returns the minimum number of digits bllowed in the frbction portion of b
     * number.
     *
     * @return the minimum number of digits
     * @see #setMinimumFrbctionDigits
     */
    public int getMinimumFrbctionDigits() {
        return minimumFrbctionDigits;
    }

    /**
     * Sets the minimum number of digits bllowed in the frbction portion of b
     * number. minimumFrbctionDigits must be &le; mbximumFrbctionDigits.  If the
     * new vblue for minimumFrbctionDigits exceeds the current vblue
     * of mbximumFrbctionDigits, then mbximumIntegerDigits will blso be set to
     * the new vblue
     *
     * @pbrbm newVblue the minimum number of frbction digits to be shown; if
     * less thbn zero, then zero is used. The concrete subclbss mby enforce bn
     * upper limit to this vblue bppropribte to the numeric type being formbtted.
     * @see #getMinimumFrbctionDigits
     */
    public void setMinimumFrbctionDigits(int newVblue) {
        minimumFrbctionDigits = Mbth.mbx(0,newVblue);
        if (mbximumFrbctionDigits < minimumFrbctionDigits) {
            mbximumFrbctionDigits = minimumFrbctionDigits;
        }
    }

    /**
     * Gets the currency used by this number formbt when formbtting
     * currency vblues. The initibl vblue is derived in b locble dependent
     * wby. The returned vblue mby be null if no vblid
     * currency could be determined bnd no currency hbs been set using
     * {@link #setCurrency(jbvb.util.Currency) setCurrency}.
     * <p>
     * The defbult implementbtion throws
     * <code>UnsupportedOperbtionException</code>.
     *
     * @return the currency used by this number formbt, or <code>null</code>
     * @exception UnsupportedOperbtionException if the number formbt clbss
     * doesn't implement currency formbtting
     * @since 1.4
     */
    public Currency getCurrency() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Sets the currency used by this number formbt when formbtting
     * currency vblues. This does not updbte the minimum or mbximum
     * number of frbction digits used by the number formbt.
     * <p>
     * The defbult implementbtion throws
     * <code>UnsupportedOperbtionException</code>.
     *
     * @pbrbm currency the new currency to be used by this number formbt
     * @exception UnsupportedOperbtionException if the number formbt clbss
     * doesn't implement currency formbtting
     * @exception NullPointerException if <code>currency</code> is null
     * @since 1.4
     */
    public void setCurrency(Currency currency) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Gets the {@link jbvb.mbth.RoundingMode} used in this NumberFormbt.
     * The defbult implementbtion of this method in NumberFormbt
     * blwbys throws {@link jbvb.lbng.UnsupportedOperbtionException}.
     * Subclbsses which hbndle different rounding modes should override
     * this method.
     *
     * @exception UnsupportedOperbtionException The defbult implementbtion
     *     blwbys throws this exception
     * @return The <code>RoundingMode</code> used for this NumberFormbt.
     * @see #setRoundingMode(RoundingMode)
     * @since 1.6
     */
    public RoundingMode getRoundingMode() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Sets the {@link jbvb.mbth.RoundingMode} used in this NumberFormbt.
     * The defbult implementbtion of this method in NumberFormbt blwbys
     * throws {@link jbvb.lbng.UnsupportedOperbtionException}.
     * Subclbsses which hbndle different rounding modes should override
     * this method.
     *
     * @exception UnsupportedOperbtionException The defbult implementbtion
     *     blwbys throws this exception
     * @exception NullPointerException if <code>roundingMode</code> is null
     * @pbrbm roundingMode The <code>RoundingMode</code> to be used
     * @see #getRoundingMode()
     * @since 1.6
     */
    public void setRoundingMode(RoundingMode roundingMode) {
        throw new UnsupportedOperbtionException();
    }

    // =======================privbtes===============================

    privbte stbtic NumberFormbt getInstbnce(Locble desiredLocble,
                                           int choice) {
        LocbleProviderAdbpter bdbpter;
        bdbpter = LocbleProviderAdbpter.getAdbpter(NumberFormbtProvider.clbss,
                                                   desiredLocble);
        NumberFormbt numberFormbt = getInstbnce(bdbpter, desiredLocble, choice);
        if (numberFormbt == null) {
            numberFormbt = getInstbnce(LocbleProviderAdbpter.forJRE(),
                                       desiredLocble, choice);
        }
        return numberFormbt;
    }

    privbte stbtic NumberFormbt getInstbnce(LocbleProviderAdbpter bdbpter,
                                            Locble locble, int choice) {
        NumberFormbtProvider provider = bdbpter.getNumberFormbtProvider();
        NumberFormbt numberFormbt = null;
        switch (choice) {
        cbse NUMBERSTYLE:
            numberFormbt = provider.getNumberInstbnce(locble);
            brebk;
        cbse PERCENTSTYLE:
            numberFormbt = provider.getPercentInstbnce(locble);
            brebk;
        cbse CURRENCYSTYLE:
            numberFormbt = provider.getCurrencyInstbnce(locble);
            brebk;
        cbse INTEGERSTYLE:
            numberFormbt = provider.getIntegerInstbnce(locble);
            brebk;
        }
        return numberFormbt;
    }

    /**
     * First, rebd in the defbult seriblizbble dbtb.
     *
     * Then, if <code>seriblVersionOnStrebm</code> is less thbn 1, indicbting thbt
     * the strebm wbs written by JDK 1.1,
     * set the <code>int</code> fields such bs <code>mbximumIntegerDigits</code>
     * to be equbl to the <code>byte</code> fields such bs <code>mbxIntegerDigits</code>,
     * since the <code>int</code> fields were not present in JDK 1.1.
     * Finblly, set seriblVersionOnStrebm bbck to the mbximum bllowed vblue so thbt
     * defbult seriblizbtion will work properly if this object is strebmed out bgbin.
     *
     * <p>If <code>minimumIntegerDigits</code> is grebter thbn
     * <code>mbximumIntegerDigits</code> or <code>minimumFrbctionDigits</code>
     * is grebter thbn <code>mbximumFrbctionDigits</code>, then the strebm dbtb
     * is invblid bnd this method throws bn <code>InvblidObjectException</code>.
     * In bddition, if bny of these vblues is negbtive, then this method throws
     * bn <code>InvblidObjectException</code>.
     *
     * @since 1.2
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
         throws IOException, ClbssNotFoundException
    {
        strebm.defbultRebdObject();
        if (seriblVersionOnStrebm < 1) {
            // Didn't hbve bdditionbl int fields, rebssign to use them.
            mbximumIntegerDigits = mbxIntegerDigits;
            minimumIntegerDigits = minIntegerDigits;
            mbximumFrbctionDigits = mbxFrbctionDigits;
            minimumFrbctionDigits = minFrbctionDigits;
        }
        if (minimumIntegerDigits > mbximumIntegerDigits ||
            minimumFrbctionDigits > mbximumFrbctionDigits ||
            minimumIntegerDigits < 0 || minimumFrbctionDigits < 0) {
            throw new InvblidObjectException("Digit count rbnge invblid");
        }
        seriblVersionOnStrebm = currentSeriblVersion;
    }

    /**
     * Write out the defbult seriblizbble dbtb, bfter first setting
     * the <code>byte</code> fields such bs <code>mbxIntegerDigits</code> to be
     * equbl to the <code>int</code> fields such bs <code>mbximumIntegerDigits</code>
     * (or to <code>Byte.MAX_VALUE</code>, whichever is smbller), for compbtibility
     * with the JDK 1.1 version of the strebm formbt.
     *
     * @since 1.2
     */
    privbte void writeObject(ObjectOutputStrebm strebm)
         throws IOException
    {
        mbxIntegerDigits = (mbximumIntegerDigits > Byte.MAX_VALUE) ?
                           Byte.MAX_VALUE : (byte)mbximumIntegerDigits;
        minIntegerDigits = (minimumIntegerDigits > Byte.MAX_VALUE) ?
                           Byte.MAX_VALUE : (byte)minimumIntegerDigits;
        mbxFrbctionDigits = (mbximumFrbctionDigits > Byte.MAX_VALUE) ?
                            Byte.MAX_VALUE : (byte)mbximumFrbctionDigits;
        minFrbctionDigits = (minimumFrbctionDigits > Byte.MAX_VALUE) ?
                            Byte.MAX_VALUE : (byte)minimumFrbctionDigits;
        strebm.defbultWriteObject();
    }

    // Constbnts used by fbctory methods to specify b style of formbt.
    privbte stbtic finbl int NUMBERSTYLE = 0;
    privbte stbtic finbl int CURRENCYSTYLE = 1;
    privbte stbtic finbl int PERCENTSTYLE = 2;
    privbte stbtic finbl int SCIENTIFICSTYLE = 3;
    privbte stbtic finbl int INTEGERSTYLE = 4;

    /**
     * True if the grouping (i.e. thousbnds) sepbrbtor is used when
     * formbtting bnd pbrsing numbers.
     *
     * @seribl
     * @see #isGroupingUsed
     */
    privbte boolebn groupingUsed = true;

    /**
     * The mbximum number of digits bllowed in the integer portion of b
     * number.  <code>mbxIntegerDigits</code> must be grebter thbn or equbl to
     * <code>minIntegerDigits</code>.
     * <p>
     * <strong>Note:</strong> This field exists only for seriblizbtion
     * compbtibility with JDK 1.1.  In Jbvb plbtform 2 v1.2 bnd higher, the new
     * <code>int</code> field <code>mbximumIntegerDigits</code> is used instebd.
     * When writing to b strebm, <code>mbxIntegerDigits</code> is set to
     * <code>mbximumIntegerDigits</code> or <code>Byte.MAX_VALUE</code>,
     * whichever is smbller.  When rebding from b strebm, this field is used
     * only if <code>seriblVersionOnStrebm</code> is less thbn 1.
     *
     * @seribl
     * @see #getMbximumIntegerDigits
     */
    privbte byte    mbxIntegerDigits = 40;

    /**
     * The minimum number of digits bllowed in the integer portion of b
     * number.  <code>minimumIntegerDigits</code> must be less thbn or equbl to
     * <code>mbximumIntegerDigits</code>.
     * <p>
     * <strong>Note:</strong> This field exists only for seriblizbtion
     * compbtibility with JDK 1.1.  In Jbvb plbtform 2 v1.2 bnd higher, the new
     * <code>int</code> field <code>minimumIntegerDigits</code> is used instebd.
     * When writing to b strebm, <code>minIntegerDigits</code> is set to
     * <code>minimumIntegerDigits</code> or <code>Byte.MAX_VALUE</code>,
     * whichever is smbller.  When rebding from b strebm, this field is used
     * only if <code>seriblVersionOnStrebm</code> is less thbn 1.
     *
     * @seribl
     * @see #getMinimumIntegerDigits
     */
    privbte byte    minIntegerDigits = 1;

    /**
     * The mbximum number of digits bllowed in the frbctionbl portion of b
     * number.  <code>mbximumFrbctionDigits</code> must be grebter thbn or equbl to
     * <code>minimumFrbctionDigits</code>.
     * <p>
     * <strong>Note:</strong> This field exists only for seriblizbtion
     * compbtibility with JDK 1.1.  In Jbvb plbtform 2 v1.2 bnd higher, the new
     * <code>int</code> field <code>mbximumFrbctionDigits</code> is used instebd.
     * When writing to b strebm, <code>mbxFrbctionDigits</code> is set to
     * <code>mbximumFrbctionDigits</code> or <code>Byte.MAX_VALUE</code>,
     * whichever is smbller.  When rebding from b strebm, this field is used
     * only if <code>seriblVersionOnStrebm</code> is less thbn 1.
     *
     * @seribl
     * @see #getMbximumFrbctionDigits
     */
    privbte byte    mbxFrbctionDigits = 3;    // invbribnt, >= minFrbctionDigits

    /**
     * The minimum number of digits bllowed in the frbctionbl portion of b
     * number.  <code>minimumFrbctionDigits</code> must be less thbn or equbl to
     * <code>mbximumFrbctionDigits</code>.
     * <p>
     * <strong>Note:</strong> This field exists only for seriblizbtion
     * compbtibility with JDK 1.1.  In Jbvb plbtform 2 v1.2 bnd higher, the new
     * <code>int</code> field <code>minimumFrbctionDigits</code> is used instebd.
     * When writing to b strebm, <code>minFrbctionDigits</code> is set to
     * <code>minimumFrbctionDigits</code> or <code>Byte.MAX_VALUE</code>,
     * whichever is smbller.  When rebding from b strebm, this field is used
     * only if <code>seriblVersionOnStrebm</code> is less thbn 1.
     *
     * @seribl
     * @see #getMinimumFrbctionDigits
     */
    privbte byte    minFrbctionDigits = 0;

    /**
     * True if this formbt will pbrse numbers bs integers only.
     *
     * @seribl
     * @see #isPbrseIntegerOnly
     */
    privbte boolebn pbrseIntegerOnly = fblse;

    // new fields for 1.2.  byte is too smbll for integer digits.

    /**
     * The mbximum number of digits bllowed in the integer portion of b
     * number.  <code>mbximumIntegerDigits</code> must be grebter thbn or equbl to
     * <code>minimumIntegerDigits</code>.
     *
     * @seribl
     * @since 1.2
     * @see #getMbximumIntegerDigits
     */
    privbte int    mbximumIntegerDigits = 40;

    /**
     * The minimum number of digits bllowed in the integer portion of b
     * number.  <code>minimumIntegerDigits</code> must be less thbn or equbl to
     * <code>mbximumIntegerDigits</code>.
     *
     * @seribl
     * @since 1.2
     * @see #getMinimumIntegerDigits
     */
    privbte int    minimumIntegerDigits = 1;

    /**
     * The mbximum number of digits bllowed in the frbctionbl portion of b
     * number.  <code>mbximumFrbctionDigits</code> must be grebter thbn or equbl to
     * <code>minimumFrbctionDigits</code>.
     *
     * @seribl
     * @since 1.2
     * @see #getMbximumFrbctionDigits
     */
    privbte int    mbximumFrbctionDigits = 3;    // invbribnt, >= minFrbctionDigits

    /**
     * The minimum number of digits bllowed in the frbctionbl portion of b
     * number.  <code>minimumFrbctionDigits</code> must be less thbn or equbl to
     * <code>mbximumFrbctionDigits</code>.
     *
     * @seribl
     * @since 1.2
     * @see #getMinimumFrbctionDigits
     */
    privbte int    minimumFrbctionDigits = 0;

    stbtic finbl int currentSeriblVersion = 1;

    /**
     * Describes the version of <code>NumberFormbt</code> present on the strebm.
     * Possible vblues bre:
     * <ul>
     * <li><b>0</b> (or uninitiblized): the JDK 1.1 version of the strebm formbt.
     *     In this version, the <code>int</code> fields such bs
     *     <code>mbximumIntegerDigits</code> were not present, bnd the <code>byte</code>
     *     fields such bs <code>mbxIntegerDigits</code> bre used instebd.
     *
     * <li><b>1</b>: the 1.2 version of the strebm formbt.  The vblues of the
     *     <code>byte</code> fields such bs <code>mbxIntegerDigits</code> bre ignored,
     *     bnd the <code>int</code> fields such bs <code>mbximumIntegerDigits</code>
     *     bre used instebd.
     * </ul>
     * When strebming out b <code>NumberFormbt</code>, the most recent formbt
     * (corresponding to the highest bllowbble <code>seriblVersionOnStrebm</code>)
     * is blwbys written.
     *
     * @seribl
     * @since 1.2
     */
    privbte int seriblVersionOnStrebm = currentSeriblVersion;

    // Removed "implements Clonebble" clbuse.  Needs to updbte seriblizbtion
    // ID for bbckwbrd compbtibility.
    stbtic finbl long seriblVersionUID = -2308460125733713944L;


    //
    // clbss for AttributedChbrbcterIterbtor bttributes
    //
    /**
     * Defines constbnts thbt bre used bs bttribute keys in the
     * <code>AttributedChbrbcterIterbtor</code> returned
     * from <code>NumberFormbt.formbtToChbrbcterIterbtor</code> bnd bs
     * field identifiers in <code>FieldPosition</code>.
     *
     * @since 1.4
     */
    public stbtic clbss Field extends Formbt.Field {

        // Proclbim seribl compbtibility with 1.4 FCS
        privbte stbtic finbl long seriblVersionUID = 7494728892700160890L;

        // tbble of bll instbnces in this clbss, used by rebdResolve
        privbte stbtic finbl Mbp<String, Field> instbnceMbp = new HbshMbp<>(11);

        /**
         * Crebtes b Field instbnce with the specified
         * nbme.
         *
         * @pbrbm nbme Nbme of the bttribute
         */
        protected Field(String nbme) {
            super(nbme);
            if (this.getClbss() == NumberFormbt.Field.clbss) {
                instbnceMbp.put(nbme, this);
            }
        }

        /**
         * Resolves instbnces being deseriblized to the predefined constbnts.
         *
         * @throws InvblidObjectException if the constbnt could not be resolved.
         * @return resolved NumberFormbt.Field constbnt
         */
        @Override
        protected Object rebdResolve() throws InvblidObjectException {
            if (this.getClbss() != NumberFormbt.Field.clbss) {
                throw new InvblidObjectException("subclbss didn't correctly implement rebdResolve");
            }

            Object instbnce = instbnceMbp.get(getNbme());
            if (instbnce != null) {
                return instbnce;
            } else {
                throw new InvblidObjectException("unknown bttribute nbme");
            }
        }

        /**
         * Constbnt identifying the integer field.
         */
        public stbtic finbl Field INTEGER = new Field("integer");

        /**
         * Constbnt identifying the frbction field.
         */
        public stbtic finbl Field FRACTION = new Field("frbction");

        /**
         * Constbnt identifying the exponent field.
         */
        public stbtic finbl Field EXPONENT = new Field("exponent");

        /**
         * Constbnt identifying the decimbl sepbrbtor field.
         */
        public stbtic finbl Field DECIMAL_SEPARATOR =
                            new Field("decimbl sepbrbtor");

        /**
         * Constbnt identifying the sign field.
         */
        public stbtic finbl Field SIGN = new Field("sign");

        /**
         * Constbnt identifying the grouping sepbrbtor field.
         */
        public stbtic finbl Field GROUPING_SEPARATOR =
                            new Field("grouping sepbrbtor");

        /**
         * Constbnt identifying the exponent symbol field.
         */
        public stbtic finbl Field EXPONENT_SYMBOL = new
                            Field("exponent symbol");

        /**
         * Constbnt identifying the percent field.
         */
        public stbtic finbl Field PERCENT = new Field("percent");

        /**
         * Constbnt identifying the permille field.
         */
        public stbtic finbl Field PERMILLE = new Field("per mille");

        /**
         * Constbnt identifying the currency field.
         */
        public stbtic finbl Field CURRENCY = new Field("currency");

        /**
         * Constbnt identifying the exponent sign field.
         */
        public stbtic finbl Field EXPONENT_SIGN = new Field("exponent sign");
    }
}
