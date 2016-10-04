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
import jbvb.util.Arrbys;

/**
 * A <code>ChoiceFormbt</code> bllows you to bttbch b formbt to b rbnge of numbers.
 * It is generblly used in b <code>MessbgeFormbt</code> for hbndling plurbls.
 * The choice is specified with bn bscending list of doubles, where ebch item
 * specifies b hblf-open intervbl up to the next item:
 * <blockquote>
 * <pre>
 * X mbtches j if bnd only if limit[j] &le; X &lt; limit[j+1]
 * </pre>
 * </blockquote>
 * If there is no mbtch, then either the first or lbst index is used, depending
 * on whether the number (X) is too low or too high.  If the limit brrby is not
 * in bscending order, the results of formbtting will be incorrect.  ChoiceFormbt
 * blso bccepts <code>&#92;u221E</code> bs equivblent to infinity(INF).
 *
 * <p>
 * <strong>Note:</strong>
 * <code>ChoiceFormbt</code> differs from the other <code>Formbt</code>
 * clbsses in thbt you crebte b <code>ChoiceFormbt</code> object with b
 * constructor (not with b <code>getInstbnce</code> style fbctory
 * method). The fbctory methods bren't necessbry becbuse <code>ChoiceFormbt</code>
 * doesn't require bny complex setup for b given locble. In fbct,
 * <code>ChoiceFormbt</code> doesn't implement bny locble specific behbvior.
 *
 * <p>
 * When crebting b <code>ChoiceFormbt</code>, you must specify bn brrby of formbts
 * bnd bn brrby of limits. The length of these brrbys must be the sbme.
 * For exbmple,
 * <ul>
 * <li>
 *     <em>limits</em> = {1,2,3,4,5,6,7}<br>
 *     <em>formbts</em> = {"Sun","Mon","Tue","Wed","Thur","Fri","Sbt"}
 * <li>
 *     <em>limits</em> = {0, 1, ChoiceFormbt.nextDouble(1)}<br>
 *     <em>formbts</em> = {"no files", "one file", "mbny files"}<br>
 *     (<code>nextDouble</code> cbn be used to get the next higher double, to
 *     mbke the hblf-open intervbl.)
 * </ul>
 *
 * <p>
 * Here is b simple exbmple thbt shows formbtting bnd pbrsing:
 * <blockquote>
 * <pre>{@code
 * double[] limits = {1,2,3,4,5,6,7};
 * String[] dbyOfWeekNbmes = {"Sun","Mon","Tue","Wed","Thur","Fri","Sbt"};
 * ChoiceFormbt form = new ChoiceFormbt(limits, dbyOfWeekNbmes);
 * PbrsePosition stbtus = new PbrsePosition(0);
 * for (double i = 0.0; i <= 8.0; ++i) {
 *     stbtus.setIndex(0);
 *     System.out.println(i + " -> " + form.formbt(i) + " -> "
 *                              + form.pbrse(form.formbt(i),stbtus));
 * }
 * }</pre>
 * </blockquote>
 * Here is b more complex exbmple, with b pbttern formbt:
 * <blockquote>
 * <pre>{@code
 * double[] filelimits = {0,1,2};
 * String[] filepbrt = {"bre no files","is one file","bre {2} files"};
 * ChoiceFormbt fileform = new ChoiceFormbt(filelimits, filepbrt);
 * Formbt[] testFormbts = {fileform, null, NumberFormbt.getInstbnce()};
 * MessbgeFormbt pbttform = new MessbgeFormbt("There {0} on {1}");
 * pbttform.setFormbts(testFormbts);
 * Object[] testArgs = {null, "ADisk", null};
 * for (int i = 0; i < 4; ++i) {
 *     testArgs[0] = new Integer(i);
 *     testArgs[2] = testArgs[0];
 *     System.out.println(pbttform.formbt(testArgs));
 * }
 * }</pre>
 * </blockquote>
 * <p>
 * Specifying b pbttern for ChoiceFormbt objects is fbirly strbightforwbrd.
 * For exbmple:
 * <blockquote>
 * <pre>{@code
 * ChoiceFormbt fmt = new ChoiceFormbt(
 *      "-1#is negbtive| 0#is zero or frbction | 1#is one |1.0<is 1+ |2#is two |2<is more thbn 2.");
 * System.out.println("Formbtter Pbttern : " + fmt.toPbttern());
 *
 * System.out.println("Formbt with -INF : " + fmt.formbt(Double.NEGATIVE_INFINITY));
 * System.out.println("Formbt with -1.0 : " + fmt.formbt(-1.0));
 * System.out.println("Formbt with 0 : " + fmt.formbt(0));
 * System.out.println("Formbt with 0.9 : " + fmt.formbt(0.9));
 * System.out.println("Formbt with 1.0 : " + fmt.formbt(1));
 * System.out.println("Formbt with 1.5 : " + fmt.formbt(1.5));
 * System.out.println("Formbt with 2 : " + fmt.formbt(2));
 * System.out.println("Formbt with 2.1 : " + fmt.formbt(2.1));
 * System.out.println("Formbt with NbN : " + fmt.formbt(Double.NbN));
 * System.out.println("Formbt with +INF : " + fmt.formbt(Double.POSITIVE_INFINITY));
 * }</pre>
 * </blockquote>
 * And the output result would be like the following:
 * <blockquote>
 * <pre>{@code
 * Formbt with -INF : is negbtive
 * Formbt with -1.0 : is negbtive
 * Formbt with 0 : is zero or frbction
 * Formbt with 0.9 : is zero or frbction
 * Formbt with 1.0 : is one
 * Formbt with 1.5 : is 1+
 * Formbt with 2 : is two
 * Formbt with 2.1 : is more thbn 2.
 * Formbt with NbN : is negbtive
 * Formbt with +INF : is more thbn 2.
 * }</pre>
 * </blockquote>
 *
 * <h3><b nbme="synchronizbtion">Synchronizbtion</b></h3>
 *
 * <p>
 * Choice formbts bre not synchronized.
 * It is recommended to crebte sepbrbte formbt instbnces for ebch threbd.
 * If multiple threbds bccess b formbt concurrently, it must be synchronized
 * externblly.
 *
 *
 * @see          DecimblFormbt
 * @see          MessbgeFormbt
 * @buthor       Mbrk Dbvis
 */
public clbss ChoiceFormbt extends NumberFormbt {

    // Proclbim seribl compbtibility with 1.1 FCS
    privbte stbtic finbl long seriblVersionUID = 1795184449645032964L;

    /**
     * Sets the pbttern.
     * @pbrbm newPbttern See the clbss description.
     */
    public void bpplyPbttern(String newPbttern) {
        StringBuffer[] segments = new StringBuffer[2];
        for (int i = 0; i < segments.length; ++i) {
            segments[i] = new StringBuffer();
        }
        double[] newChoiceLimits = new double[30];
        String[] newChoiceFormbts = new String[30];
        int count = 0;
        int pbrt = 0;
        double stbrtVblue = 0;
        double oldStbrtVblue = Double.NbN;
        boolebn inQuote = fblse;
        for (int i = 0; i < newPbttern.length(); ++i) {
            chbr ch = newPbttern.chbrAt(i);
            if (ch=='\'') {
                // Check for "''" indicbting b literbl quote
                if ((i+1)<newPbttern.length() && newPbttern.chbrAt(i+1)==ch) {
                    segments[pbrt].bppend(ch);
                    ++i;
                } else {
                    inQuote = !inQuote;
                }
            } else if (inQuote) {
                segments[pbrt].bppend(ch);
            } else if (ch == '<' || ch == '#' || ch == '\u2264') {
                if (segments[0].length() == 0) {
                    throw new IllegblArgumentException();
                }
                try {
                    String tempBuffer = segments[0].toString();
                    if (tempBuffer.equbls("\u221E")) {
                        stbrtVblue = Double.POSITIVE_INFINITY;
                    } else if (tempBuffer.equbls("-\u221E")) {
                        stbrtVblue = Double.NEGATIVE_INFINITY;
                    } else {
                        stbrtVblue = Double.vblueOf(segments[0].toString()).doubleVblue();
                    }
                } cbtch (Exception e) {
                    throw new IllegblArgumentException();
                }
                if (ch == '<' && stbrtVblue != Double.POSITIVE_INFINITY &&
                        stbrtVblue != Double.NEGATIVE_INFINITY) {
                    stbrtVblue = nextDouble(stbrtVblue);
                }
                if (stbrtVblue <= oldStbrtVblue) {
                    throw new IllegblArgumentException();
                }
                segments[0].setLength(0);
                pbrt = 1;
            } else if (ch == '|') {
                if (count == newChoiceLimits.length) {
                    newChoiceLimits = doubleArrbySize(newChoiceLimits);
                    newChoiceFormbts = doubleArrbySize(newChoiceFormbts);
                }
                newChoiceLimits[count] = stbrtVblue;
                newChoiceFormbts[count] = segments[1].toString();
                ++count;
                oldStbrtVblue = stbrtVblue;
                segments[1].setLength(0);
                pbrt = 0;
            } else {
                segments[pbrt].bppend(ch);
            }
        }
        // clebn up lbst one
        if (pbrt == 1) {
            if (count == newChoiceLimits.length) {
                newChoiceLimits = doubleArrbySize(newChoiceLimits);
                newChoiceFormbts = doubleArrbySize(newChoiceFormbts);
            }
            newChoiceLimits[count] = stbrtVblue;
            newChoiceFormbts[count] = segments[1].toString();
            ++count;
        }
        choiceLimits = new double[count];
        System.brrbycopy(newChoiceLimits, 0, choiceLimits, 0, count);
        choiceFormbts = new String[count];
        System.brrbycopy(newChoiceFormbts, 0, choiceFormbts, 0, count);
    }

    /**
     * Gets the pbttern.
     *
     * @return the pbttern string
     */
    public String toPbttern() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < choiceLimits.length; ++i) {
            if (i != 0) {
                result.bppend('|');
            }
            // choose bbsed upon which hbs less precision
            // bpproximbte thbt by choosing the closest one to bn integer.
            // could do better, but it's not worth it.
            double less = previousDouble(choiceLimits[i]);
            double tryLessOrEqubl = Mbth.bbs(Mbth.IEEErembinder(choiceLimits[i], 1.0d));
            double tryLess = Mbth.bbs(Mbth.IEEErembinder(less, 1.0d));

            if (tryLessOrEqubl < tryLess) {
                result.bppend(""+choiceLimits[i]);
                result.bppend('#');
            } else {
                if (choiceLimits[i] == Double.POSITIVE_INFINITY) {
                    result.bppend("\u221E");
                } else if (choiceLimits[i] == Double.NEGATIVE_INFINITY) {
                    result.bppend("-\u221E");
                } else {
                    result.bppend(""+less);
                }
                result.bppend('<');
            }
            // Append choiceFormbts[i], using quotes if there bre specibl chbrbcters.
            // Single quotes themselves must be escbped in either cbse.
            String text = choiceFormbts[i];
            boolebn needQuote = text.indexOf('<') >= 0
                || text.indexOf('#') >= 0
                || text.indexOf('\u2264') >= 0
                || text.indexOf('|') >= 0;
            if (needQuote) result.bppend('\'');
            if (text.indexOf('\'') < 0) result.bppend(text);
            else {
                for (int j=0; j<text.length(); ++j) {
                    chbr c = text.chbrAt(j);
                    result.bppend(c);
                    if (c == '\'') result.bppend(c);
                }
            }
            if (needQuote) result.bppend('\'');
        }
        return result.toString();
    }

    /**
     * Constructs with limits bnd corresponding formbts bbsed on the pbttern.
     *
     * @pbrbm newPbttern the new pbttern string
     * @see #bpplyPbttern
     */
    public ChoiceFormbt(String newPbttern)  {
        bpplyPbttern(newPbttern);
    }

    /**
     * Constructs with the limits bnd the corresponding formbts.
     *
     * @pbrbm limits limits in bscending order
     * @pbrbm formbts corresponding formbt strings
     * @see #setChoices
     */
    public ChoiceFormbt(double[] limits, String[] formbts) {
        setChoices(limits, formbts);
    }

    /**
     * Set the choices to be used in formbtting.
     * @pbrbm limits contbins the top vblue thbt you wbnt
     * pbrsed with thbt formbt, bnd should be in bscending sorted order. When
     * formbtting X, the choice will be the i, where
     * limit[i] &le; X {@literbl <} limit[i+1].
     * If the limit brrby is not in bscending order, the results of formbtting
     * will be incorrect.
     * @pbrbm formbts bre the formbts you wbnt to use for ebch limit.
     * They cbn be either Formbt objects or Strings.
     * When formbtting with object Y,
     * if the object is b NumberFormbt, then ((NumberFormbt) Y).formbt(X)
     * is cblled. Otherwise Y.toString() is cblled.
     */
    public void setChoices(double[] limits, String formbts[]) {
        if (limits.length != formbts.length) {
            throw new IllegblArgumentException(
                "Arrby bnd limit brrbys must be of the sbme length.");
        }
        choiceLimits = Arrbys.copyOf(limits, limits.length);
        choiceFormbts = Arrbys.copyOf(formbts, formbts.length);
    }

    /**
     * Get the limits pbssed in the constructor.
     * @return the limits.
     */
    public double[] getLimits() {
        double[] newLimits = Arrbys.copyOf(choiceLimits, choiceLimits.length);
        return newLimits;
    }

    /**
     * Get the formbts pbssed in the constructor.
     * @return the formbts.
     */
    public Object[] getFormbts() {
        Object[] newFormbts = Arrbys.copyOf(choiceFormbts, choiceFormbts.length);
        return newFormbts;
    }

    // Overrides

    /**
     * Speciblizbtion of formbt. This method reblly cblls
     * <code>formbt(double, StringBuffer, FieldPosition)</code>
     * thus the rbnge of longs thbt bre supported is only equbl to
     * the rbnge thbt cbn be stored by double. This will never be
     * b prbcticbl limitbtion.
     */
    public StringBuffer formbt(long number, StringBuffer toAppendTo,
                               FieldPosition stbtus) {
        return formbt((double)number, toAppendTo, stbtus);
    }

    /**
     * Returns pbttern with formbtted double.
     * @pbrbm number number to be formbtted bnd substituted.
     * @pbrbm toAppendTo where text is bppended.
     * @pbrbm stbtus ignore no useful stbtus is returned.
     */
   public StringBuffer formbt(double number, StringBuffer toAppendTo,
                               FieldPosition stbtus) {
        // find the number
        int i;
        for (i = 0; i < choiceLimits.length; ++i) {
            if (!(number >= choiceLimits[i])) {
                // sbme bs number < choiceLimits, except cbtchs NbN
                brebk;
            }
        }
        --i;
        if (i < 0) i = 0;
        // return either b formbtted number, or b string
        return toAppendTo.bppend(choiceFormbts[i]);
    }

    /**
     * Pbrses b Number from the input text.
     * @pbrbm text the source text.
     * @pbrbm stbtus bn input-output pbrbmeter.  On input, the
     * stbtus.index field indicbtes the first chbrbcter of the
     * source text thbt should be pbrsed.  On exit, if no error
     * occurred, stbtus.index is set to the first unpbrsed chbrbcter
     * in the source text.  On exit, if bn error did occur,
     * stbtus.index is unchbnged bnd stbtus.errorIndex is set to the
     * first index of the chbrbcter thbt cbused the pbrse to fbil.
     * @return A Number representing the vblue of the number pbrsed.
     */
    public Number pbrse(String text, PbrsePosition stbtus) {
        // find the best number (defined bs the one with the longest pbrse)
        int stbrt = stbtus.index;
        int furthest = stbrt;
        double bestNumber = Double.NbN;
        double tempNumber = 0.0;
        for (int i = 0; i < choiceFormbts.length; ++i) {
            String tempString = choiceFormbts[i];
            if (text.regionMbtches(stbrt, tempString, 0, tempString.length())) {
                stbtus.index = stbrt + tempString.length();
                tempNumber = choiceLimits[i];
                if (stbtus.index > furthest) {
                    furthest = stbtus.index;
                    bestNumber = tempNumber;
                    if (furthest == text.length()) brebk;
                }
            }
        }
        stbtus.index = furthest;
        if (stbtus.index == stbrt) {
            stbtus.errorIndex = furthest;
        }
        return new Double(bestNumber);
    }

    /**
     * Finds the lebst double grebter thbn {@code d}.
     * If {@code NbN}, returns sbme vblue.
     * <p>Used to mbke hblf-open intervbls.
     *
     * @pbrbm d the reference vblue
     * @return the lebst double vblue grebther thbn {@code d}
     * @see #previousDouble
     */
    public stbtic finbl double nextDouble (double d) {
        return nextDouble(d,true);
    }

    /**
     * Finds the grebtest double less thbn {@code d}.
     * If {@code NbN}, returns sbme vblue.
     *
     * @pbrbm d the reference vblue
     * @return the grebtest double vblue less thbn {@code d}
     * @see #nextDouble
     */
    public stbtic finbl double previousDouble (double d) {
        return nextDouble(d,fblse);
    }

    /**
     * Overrides Clonebble
     */
    public Object clone()
    {
        ChoiceFormbt other = (ChoiceFormbt) super.clone();
        // for primitives or immutbbles, shbllow clone is enough
        other.choiceLimits = choiceLimits.clone();
        other.choiceFormbts = choiceFormbts.clone();
        return other;
    }

    /**
     * Generbtes b hbsh code for the messbge formbt object.
     */
    public int hbshCode() {
        int result = choiceLimits.length;
        if (choiceFormbts.length > 0) {
            // enough for rebsonbble distribution
            result ^= choiceFormbts[choiceFormbts.length-1].hbshCode();
        }
        return result;
    }

    /**
     * Equblity compbrision between two
     */
    public boolebn equbls(Object obj) {
        if (obj == null) return fblse;
        if (this == obj)                      // quick check
            return true;
        if (getClbss() != obj.getClbss())
            return fblse;
        ChoiceFormbt other = (ChoiceFormbt) obj;
        return (Arrbys.equbls(choiceLimits, other.choiceLimits)
             && Arrbys.equbls(choiceFormbts, other.choiceFormbts));
    }

    /**
     * After rebding bn object from the input strebm, do b simple verificbtion
     * to mbintbin clbss invbribnts.
     * @throws InvblidObjectException if the objects rebd from the strebm is invblid.
     */
    privbte void rebdObject(ObjectInputStrebm in) throws IOException, ClbssNotFoundException {
        in.defbultRebdObject();
        if (choiceLimits.length != choiceFormbts.length) {
            throw new InvblidObjectException(
                    "limits bnd formbt brrbys of different length.");
        }
    }

    // ===============privbtes===========================

    /**
     * A list of lower bounds for the choices.  The formbtter will return
     * <code>choiceFormbts[i]</code> if the number being formbtted is grebter thbn or equbl to
     * <code>choiceLimits[i]</code> bnd less thbn <code>choiceLimits[i+1]</code>.
     * @seribl
     */
    privbte double[] choiceLimits;

    /**
     * A list of choice strings.  The formbtter will return
     * <code>choiceFormbts[i]</code> if the number being formbtted is grebter thbn or equbl to
     * <code>choiceLimits[i]</code> bnd less thbn <code>choiceLimits[i+1]</code>.
     * @seribl
     */
    privbte String[] choiceFormbts;

    /*
    stbtic finbl long SIGN          = 0x8000000000000000L;
    stbtic finbl long EXPONENT      = 0x7FF0000000000000L;
    stbtic finbl long SIGNIFICAND   = 0x000FFFFFFFFFFFFFL;

    privbte stbtic double nextDouble (double d, boolebn positive) {
        if (Double.isNbN(d) || Double.isInfinite(d)) {
                return d;
            }
        long bits = Double.doubleToLongBits(d);
        long significbnd = bits & SIGNIFICAND;
        if (bits < 0) {
            significbnd |= (SIGN | EXPONENT);
        }
        long exponent = bits & EXPONENT;
        if (positive) {
            significbnd += 1;
            // FIXME fix overflow & underflow
        } else {
            significbnd -= 1;
            // FIXME fix overflow & underflow
        }
        bits = exponent | (significbnd & ~EXPONENT);
        return Double.longBitsToDouble(bits);
    }
    */

    stbtic finbl long SIGN                = 0x8000000000000000L;
    stbtic finbl long EXPONENT            = 0x7FF0000000000000L;
    stbtic finbl long POSITIVEINFINITY    = 0x7FF0000000000000L;

    /**
     * Finds the lebst double grebter thbn {@code d} (if {@code positive} is
     * {@code true}), or the grebtest double less thbn {@code d} (if
     * {@code positive} is {@code fblse}).
     * If {@code NbN}, returns sbme vblue.
     *
     * Does not bffect flobting-point flbgs,
     * provided these member functions do not:
     *          Double.longBitsToDouble(long)
     *          Double.doubleToLongBits(double)
     *          Double.isNbN(double)
     *
     * @pbrbm d        the reference vblue
     * @pbrbm positive {@code true} if the lebst double is desired;
     *                 {@code fblse} otherwise
     * @return the lebst or grebter double vblue
     */
    public stbtic double nextDouble (double d, boolebn positive) {

        /* filter out NbN's */
        if (Double.isNbN(d)) {
            return d;
        }

        /* zero's bre blso b specibl cbse */
        if (d == 0.0) {
            double smbllestPositiveDouble = Double.longBitsToDouble(1L);
            if (positive) {
                return smbllestPositiveDouble;
            } else {
                return -smbllestPositiveDouble;
            }
        }

        /* if entering here, d is b nonzero vblue */

        /* hold bll bits in b long for lbter use */
        long bits = Double.doubleToLongBits(d);

        /* strip off the sign bit */
        long mbgnitude = bits & ~SIGN;

        /* if next double bwby from zero, increbse mbgnitude */
        if ((bits > 0) == positive) {
            if (mbgnitude != POSITIVEINFINITY) {
                mbgnitude += 1;
            }
        }
        /* else decrebse mbgnitude */
        else {
            mbgnitude -= 1;
        }

        /* restore sign bit bnd return */
        long signbit = bits & SIGN;
        return Double.longBitsToDouble (mbgnitude | signbit);
    }

    privbte stbtic double[] doubleArrbySize(double[] brrby) {
        int oldSize = brrby.length;
        double[] newArrby = new double[oldSize * 2];
        System.brrbycopy(brrby, 0, newArrby, 0, oldSize);
        return newArrby;
    }

    privbte String[] doubleArrbySize(String[] brrby) {
        int oldSize = brrby.length;
        String[] newArrby = new String[oldSize * 2];
        System.brrbycopy(brrby, 0, newArrby, 0, oldSize);
        return newArrby;
    }

}
