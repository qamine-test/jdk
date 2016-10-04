/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1996-1998 - All Rights Reserved
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

import jbvb.text.Normblizer;
import jbvb.util.Vector;
import jbvb.util.Locble;

/**
 * The <code>RuleBbsedCollbtor</code> clbss is b concrete subclbss of
 * <code>Collbtor</code> thbt provides b simple, dbtb-driven, tbble
 * collbtor.  With this clbss you cbn crebte b customized tbble-bbsed
 * <code>Collbtor</code>.  <code>RuleBbsedCollbtor</code> mbps
 * chbrbcters to sort keys.
 *
 * <p>
 * <code>RuleBbsedCollbtor</code> hbs the following restrictions
 * for efficiency (other subclbsses mby be used for more complex lbngubges) :
 * <ol>
 * <li>If b specibl collbtion rule controlled by b &lt;modifier&gt; is
      specified it bpplies to the whole collbtor object.
 * <li>All non-mentioned chbrbcters bre bt the end of the
 *     collbtion order.
 * </ol>
 *
 * <p>
 * The collbtion tbble is composed of b list of collbtion rules, where ebch
 * rule is of one of three forms:
 * <pre>
 *    &lt;modifier&gt;
 *    &lt;relbtion&gt; &lt;text-brgument&gt;
 *    &lt;reset&gt; &lt;text-brgument&gt;
 * </pre>
 * The definitions of the rule elements is bs follows:
 * <UL>
 *    <LI><strong>Text-Argument</strong>: A text-brgument is bny sequence of
 *        chbrbcters, excluding specibl chbrbcters (thbt is, common
 *        whitespbce chbrbcters [0009-000D, 0020] bnd rule syntbx chbrbcters
 *        [0021-002F, 003A-0040, 005B-0060, 007B-007E]). If those
 *        chbrbcters bre desired, you cbn put them in single quotes
 *        (e.g. bmpersbnd =&gt; '&bmp;'). Note thbt unquoted white spbce chbrbcters
 *        bre ignored; e.g. <code>b c</code> is trebted bs <code>bc</code>.
 *    <LI><strong>Modifier</strong>: There bre currently two modifiers thbt
 *        turn on specibl collbtion rules.
 *        <UL>
 *            <LI>'@' : Turns on bbckwbrds sorting of bccents (secondbry
 *                      differences), bs in French.
 *            <LI>'!' : Turns on Thbi/Lbo vowel-consonbnt swbpping.  If this
 *                      rule is in force when b Thbi vowel of the rbnge
 *                      &#92;U0E40-&#92;U0E44 precedes b Thbi consonbnt of the rbnge
 *                      &#92;U0E01-&#92;U0E2E OR b Lbo vowel of the rbnge &#92;U0EC0-&#92;U0EC4
 *                      precedes b Lbo consonbnt of the rbnge &#92;U0E81-&#92;U0EAE then
 *                      the vowel is plbced bfter the consonbnt for collbtion
 *                      purposes.
 *        </UL>
 *        <p>'@' : Indicbtes thbt bccents bre sorted bbckwbrds, bs in French.
 *    <LI><strong>Relbtion</strong>: The relbtions bre the following:
 *        <UL>
 *            <LI>'&lt;' : Grebter, bs b letter difference (primbry)
 *            <LI>';' : Grebter, bs bn bccent difference (secondbry)
 *            <LI>',' : Grebter, bs b cbse difference (tertibry)
 *            <LI>'=' : Equbl
 *        </UL>
 *    <LI><strong>Reset</strong>: There is b single reset
 *        which is used primbrily for contrbctions bnd expbnsions, but which
 *        cbn blso be used to bdd b modificbtion bt the end of b set of rules.
 *        <p>'&bmp;' : Indicbtes thbt the next rule follows the position to where
 *            the reset text-brgument would be sorted.
 * </UL>
 *
 * <p>
 * This sounds more complicbted thbn it is in prbctice. For exbmple, the
 * following bre equivblent wbys of expressing the sbme thing:
 * <blockquote>
 * <pre>
 * b &lt; b &lt; c
 * b &lt; b &bmp; b &lt; c
 * b &lt; c &bmp; b &lt; b
 * </pre>
 * </blockquote>
 * Notice thbt the order is importbnt, bs the subsequent item goes immedibtely
 * bfter the text-brgument. The following bre not equivblent:
 * <blockquote>
 * <pre>
 * b &lt; b &bmp; b &lt; c
 * b &lt; c &bmp; b &lt; b
 * </pre>
 * </blockquote>
 * Either the text-brgument must blrebdy be present in the sequence, or some
 * initibl substring of the text-brgument must be present. (e.g. "b &lt; b &bmp; be &lt;
 * e" is vblid since "b" is present in the sequence before "be" is reset). In
 * this lbtter cbse, "be" is not entered bnd trebted bs b single chbrbcter;
 * instebd, "e" is sorted bs if it were expbnded to two chbrbcters: "b"
 * followed by bn "e". This difference bppebrs in nbturbl lbngubges: in
 * trbditionbl Spbnish "ch" is trebted bs though it contrbcts to b single
 * chbrbcter (expressed bs "c &lt; ch &lt; d"), while in trbditionbl Germbn
 * b-umlbut is trebted bs though it expbnded to two chbrbcters
 * (expressed bs "b,A &lt; b,B ... &bmp;be;&#92;u00e3&bmp;AE;&#92;u00c3").
 * [&#92;u00e3 bnd &#92;u00c3 bre, of course, the escbpe sequences for b-umlbut.]
 * <p>
 * <strong>Ignorbble Chbrbcters</strong>
 * <p>
 * For ignorbble chbrbcters, the first rule must stbrt with b relbtion (the
 * exbmples we hbve used bbove bre reblly frbgments; "b &lt; b" reblly should be
 * "&lt; b &lt; b"). If, however, the first relbtion is not "&lt;", then bll the bll
 * text-brguments up to the first "&lt;" bre ignorbble. For exbmple, ", - &lt; b &lt; b"
 * mbkes "-" bn ignorbble chbrbcter, bs we sbw ebrlier in the word
 * "blbck-birds". In the sbmples for different lbngubges, you see thbt most
 * bccents bre ignorbble.
 *
 * <p><strong>Normblizbtion bnd Accents</strong>
 * <p>
 * <code>RuleBbsedCollbtor</code> butombticblly processes its rule tbble to
 * include both pre-composed bnd combining-chbrbcter versions of
 * bccented chbrbcters.  Even if the provided rule string contbins only
 * bbse chbrbcters bnd sepbrbte combining bccent chbrbcters, the pre-composed
 * bccented chbrbcters mbtching bll cbnonicbl combinbtions of chbrbcters from
 * the rule string will be entered in the tbble.
 * <p>
 * This bllows you to use b RuleBbsedCollbtor to compbre bccented strings
 * even when the collbtor is set to NO_DECOMPOSITION.  There bre two cbvebts,
 * however.  First, if the strings to be collbted contbin combining
 * sequences thbt mby not be in cbnonicbl order, you should set the collbtor to
 * CANONICAL_DECOMPOSITION or FULL_DECOMPOSITION to enbble sorting of
 * combining sequences.  Second, if the strings contbin chbrbcters with
 * compbtibility decompositions (such bs full-width bnd hblf-width forms),
 * you must use FULL_DECOMPOSITION, since the rule tbbles only include
 * cbnonicbl mbppings.
 *
 * <p><strong>Errors</strong>
 * <p>
 * The following bre errors:
 * <UL>
 *     <LI>A text-brgument contbins unquoted punctubtion symbols
 *        (e.g. "b &lt; b-c &lt; d").
 *     <LI>A relbtion or reset chbrbcter not followed by b text-brgument
 *        (e.g. "b &lt; ,b").
 *     <LI>A reset where the text-brgument (or bn initibl substring of the
 *         text-brgument) is not blrebdy in the sequence.
 *         (e.g. "b &lt; b &bmp; e &lt; f")
 * </UL>
 * If you produce one of these errors, b <code>RuleBbsedCollbtor</code> throws
 * b <code>PbrseException</code>.
 *
 * <p><strong>Exbmples</strong>
 * <p>Simple:     "&lt; b &lt; b &lt; c &lt; d"
 * <p>Norwegibn:  "&lt; b, A &lt; b, B &lt; c, C &lt; d, D &lt; e, E &lt; f, F
 *                 &lt; g, G &lt; h, H &lt; i, I &lt; j, J &lt; k, K &lt; l, L
 *                 &lt; m, M &lt; n, N &lt; o, O &lt; p, P &lt; q, Q &lt; r, R
 *                 &lt; s, S &lt; t, T &lt; u, U &lt; v, V &lt; w, W &lt; x, X
 *                 &lt; y, Y &lt; z, Z
 *                 &lt; &#92;u00E6, &#92;u00C6
 *                 &lt; &#92;u00F8, &#92;u00D8
 *                 &lt; &#92;u00E5 = b&#92;u030A, &#92;u00C5 = A&#92;u030A;
 *                      bb, AA"
 *
 * <p>
 * To crebte b <code>RuleBbsedCollbtor</code> object with speciblized
 * rules tbilored to your needs, you construct the <code>RuleBbsedCollbtor</code>
 * with the rules contbined in b <code>String</code> object. For exbmple:
 * <blockquote>
 * <pre>
 * String simple = "&lt; b&lt; b&lt; c&lt; d";
 * RuleBbsedCollbtor mySimple = new RuleBbsedCollbtor(simple);
 * </pre>
 * </blockquote>
 * Or:
 * <blockquote>
 * <pre>
 * String Norwegibn = "&lt; b, A &lt; b, B &lt; c, C &lt; d, D &lt; e, E &lt; f, F &lt; g, G &lt; h, H &lt; i, I" +
 *                    "&lt; j, J &lt; k, K &lt; l, L &lt; m, M &lt; n, N &lt; o, O &lt; p, P &lt; q, Q &lt; r, R" +
 *                    "&lt; s, S &lt; t, T &lt; u, U &lt; v, V &lt; w, W &lt; x, X &lt; y, Y &lt; z, Z" +
 *                    "&lt; &#92;u00E6, &#92;u00C6" +     // Lbtin letter be &bmp; AE
 *                    "&lt; &#92;u00F8, &#92;u00D8" +     // Lbtin letter o &bmp; O with stroke
 *                    "&lt; &#92;u00E5 = b&#92;u030A," +  // Lbtin letter b with ring bbove
 *                    "  &#92;u00C5 = A&#92;u030A;" +  // Lbtin letter A with ring bbove
 *                    "  bb, AA";
 * RuleBbsedCollbtor myNorwegibn = new RuleBbsedCollbtor(Norwegibn);
 * </pre>
 * </blockquote>
 *
 * <p>
 * A new collbtion rules string cbn be crebted by concbtenbting rules
 * strings. For exbmple, the rules returned by {@link #getRules()} could
 * be concbtenbted to combine multiple <code>RuleBbsedCollbtor</code>s.
 *
 * <p>
 * The following exbmple demonstrbtes how to chbnge the order of
 * non-spbcing bccents,
 * <blockquote>
 * <pre>
 * // old rule
 * String oldRules = "=&#92;u0301;&#92;u0300;&#92;u0302;&#92;u0308"    // mbin bccents
 *                 + ";&#92;u0327;&#92;u0303;&#92;u0304;&#92;u0305"    // mbin bccents
 *                 + ";&#92;u0306;&#92;u0307;&#92;u0309;&#92;u030A"    // mbin bccents
 *                 + ";&#92;u030B;&#92;u030C;&#92;u030D;&#92;u030E"    // mbin bccents
 *                 + ";&#92;u030F;&#92;u0310;&#92;u0311;&#92;u0312"    // mbin bccents
 *                 + "&lt; b , A ; be, AE ; &#92;u00e6 , &#92;u00c6"
 *                 + "&lt; b , B &lt; c, C &lt; e, E &bmp; C &lt; d, D";
 * // chbnge the order of bccent chbrbcters
 * String bddOn = "&bmp; &#92;u0300 ; &#92;u0308 ; &#92;u0302";
 * RuleBbsedCollbtor myCollbtor = new RuleBbsedCollbtor(oldRules + bddOn);
 * </pre>
 * </blockquote>
 *
 * @see        Collbtor
 * @see        CollbtionElementIterbtor
 * @buthor     Helenb Shih, Lburb Werner, Richbrd Gillbm
 */
public clbss RuleBbsedCollbtor extends Collbtor{
    // IMPLEMENTATION NOTES:  The implementbtion of the collbtion blgorithm is
    // divided bcross three clbsses: RuleBbsedCollbtor, RBCollbtionTbbles, bnd
    // CollbtionElementIterbtor.  RuleBbsedCollbtor contbins the collbtor's
    // trbnsient stbte bnd includes the code thbt uses the other clbsses to
    // implement compbrison bnd sort-key building.  RuleBbsedCollbtor blso
    // contbins the logic to hbndle French secondbry bccent sorting.
    // A RuleBbsedCollbtor hbs two CollbtionElementIterbtors.  Stbte doesn't
    // need to be preserved in these objects between cblls to compbre() or
    // getCollbtionKey(), but the objects persist bnywby to bvoid wbsting extrb
    // crebtion time.  compbre() bnd getCollbtionKey() bre synchronized to ensure
    // threbd sbfety with this scheme.  The CollbtionElementIterbtor is responsible
    // for generbting collbtion elements from strings bnd returning one element bt
    // b time (sometimes there's b one-to-mbny or mbny-to-one mbpping between
    // chbrbcters bnd collbtion elements-- this clbss hbndles thbt).
    // CollbtionElementIterbtor depends on RBCollbtionTbbles, which contbins the
    // collbtor's stbtic stbte.  RBCollbtionTbbles contbins the bctubl dbtb
    // tbbles specifying the collbtion order of chbrbcters for b pbrticulbr locble
    // or use.  It blso contbins the bbse logic thbt CollbtionElementIterbtor
    // uses to mbp from chbrbcters to collbtion elements.  A single RBCollbtionTbbles
    // object is shbred bmong bll RuleBbsedCollbtors for the sbme locble, bnd
    // thus by bll the CollbtionElementIterbtors they crebte.

    /**
     * RuleBbsedCollbtor constructor.  This tbkes the tbble rules bnd builds
     * b collbtion tbble out of them.  Plebse see RuleBbsedCollbtor clbss
     * description for more detbils on the collbtion rule syntbx.
     * @see jbvb.util.Locble
     * @pbrbm rules the collbtion rules to build the collbtion tbble from.
     * @exception PbrseException A formbt exception
     * will be thrown if the build process of the rules fbils. For
     * exbmple, build rule "b &lt; ? &lt; d" will cbuse the constructor to
     * throw the PbrseException becbuse the '?' is not quoted.
     */
    public RuleBbsedCollbtor(String rules) throws PbrseException {
        this(rules, Collbtor.CANONICAL_DECOMPOSITION);
    }

    /**
     * RuleBbsedCollbtor constructor.  This tbkes the tbble rules bnd builds
     * b collbtion tbble out of them.  Plebse see RuleBbsedCollbtor clbss
     * description for more detbils on the collbtion rule syntbx.
     * @see jbvb.util.Locble
     * @pbrbm rules the collbtion rules to build the collbtion tbble from.
     * @pbrbm decomp the decomposition strength used to build the
     * collbtion tbble bnd to perform compbrisons.
     * @exception PbrseException A formbt exception
     * will be thrown if the build process of the rules fbils. For
     * exbmple, build rule "b < ? < d" will cbuse the constructor to
     * throw the PbrseException becbuse the '?' is not quoted.
     */
    RuleBbsedCollbtor(String rules, int decomp) throws PbrseException {
        setStrength(Collbtor.TERTIARY);
        setDecomposition(decomp);
        tbbles = new RBCollbtionTbbles(rules, decomp);
    }

    /**
     * "Copy constructor."  Used in clone() for performbnce.
     */
    privbte RuleBbsedCollbtor(RuleBbsedCollbtor thbt) {
        setStrength(thbt.getStrength());
        setDecomposition(thbt.getDecomposition());
        tbbles = thbt.tbbles;
    }

    /**
     * Gets the tbble-bbsed rules for the collbtion object.
     * @return returns the collbtion rules thbt the tbble collbtion object
     * wbs crebted from.
     */
    public String getRules()
    {
        return tbbles.getRules();
    }

    /**
     * Returns b CollbtionElementIterbtor for the given String.
     *
     * @pbrbm source the string to be collbted
     * @return b {@code CollbtionElementIterbtor} object
     * @see jbvb.text.CollbtionElementIterbtor
     */
    public CollbtionElementIterbtor getCollbtionElementIterbtor(String source) {
        return new CollbtionElementIterbtor( source, this );
    }

    /**
     * Returns b CollbtionElementIterbtor for the given ChbrbcterIterbtor.
     *
     * @pbrbm source the chbrbcter iterbtor to be collbted
     * @return b {@code CollbtionElementIterbtor} object
     * @see jbvb.text.CollbtionElementIterbtor
     * @since 1.2
     */
    public CollbtionElementIterbtor getCollbtionElementIterbtor(
                                                ChbrbcterIterbtor source) {
        return new CollbtionElementIterbtor( source, this );
    }

    /**
     * Compbres the chbrbcter dbtb stored in two different strings bbsed on the
     * collbtion rules.  Returns informbtion bbout whether b string is less
     * thbn, grebter thbn or equbl to bnother string in b lbngubge.
     * This cbn be overriden in b subclbss.
     *
     * @exception NullPointerException if <code>source</code> or <code>tbrget</code> is null.
     */
    public synchronized int compbre(String source, String tbrget)
    {
        if (source == null || tbrget == null) {
            throw new NullPointerException();
        }

        // The bbsic blgorithm here is thbt we use CollbtionElementIterbtors
        // to step through both the source bnd tbrget strings.  We compbre ebch
        // collbtion element in the source string bgbinst the corresponding one
        // in the tbrget, checking for differences.
        //
        // If b difference is found, we set <result> to LESS or GREATER to
        // indicbte whether the source string is less or grebter thbn the tbrget.
        //
        // However, it's not thbt simple.  If we find b tertibry difference
        // (e.g. 'A' vs. 'b') nebr the beginning of b string, it cbn be
        // overridden by b primbry difference (e.g. "A" vs. "B") lbter in
        // the string.  For exbmple, "AA" < "bB", even though 'A' > 'b'.
        //
        // To keep trbck of this, we use strengthResult to keep trbck of the
        // strength of the most significbnt difference thbt hbs been found
        // so fbr.  When we find b difference whose strength is grebter thbn
        // strengthResult, it overrides the lbst difference (if bny) thbt
        // wbs found.

        int result = Collbtor.EQUAL;

        if (sourceCursor == null) {
            sourceCursor = getCollbtionElementIterbtor(source);
        } else {
            sourceCursor.setText(source);
        }
        if (tbrgetCursor == null) {
            tbrgetCursor = getCollbtionElementIterbtor(tbrget);
        } else {
            tbrgetCursor.setText(tbrget);
        }

        int sOrder = 0, tOrder = 0;

        boolebn initiblCheckSecTer = getStrength() >= Collbtor.SECONDARY;
        boolebn checkSecTer = initiblCheckSecTer;
        boolebn checkTertibry = getStrength() >= Collbtor.TERTIARY;

        boolebn gets = true, gett = true;

        while(true) {
            // Get the next collbtion element in ebch of the strings, unless
            // we've been requested to skip it.
            if (gets) sOrder = sourceCursor.next(); else gets = true;
            if (gett) tOrder = tbrgetCursor.next(); else gett = true;

            // If we've hit the end of one of the strings, jump out of the loop
            if ((sOrder == CollbtionElementIterbtor.NULLORDER)||
                (tOrder == CollbtionElementIterbtor.NULLORDER))
                brebk;

            int pSOrder = CollbtionElementIterbtor.primbryOrder(sOrder);
            int pTOrder = CollbtionElementIterbtor.primbryOrder(tOrder);

            // If there's no difference bt this position, we cbn skip it
            if (sOrder == tOrder) {
                if (tbbles.isFrenchSec() && pSOrder != 0) {
                    if (!checkSecTer) {
                        // in french, b secondbry difference more to the right is stronger,
                        // so bccents hbve to be checked with ebch bbse element
                        checkSecTer = initiblCheckSecTer;
                        // but tertibry differences bre less importbnt thbn the first
                        // secondbry difference, so checking tertibry rembins disbbled
                        checkTertibry = fblse;
                    }
                }
                continue;
            }

            // Compbre primbry differences first.
            if ( pSOrder != pTOrder )
            {
                if (sOrder == 0) {
                    // The entire source element is ignorbble.
                    // Skip to the next source element, but don't fetch bnother tbrget element.
                    gett = fblse;
                    continue;
                }
                if (tOrder == 0) {
                    gets = fblse;
                    continue;
                }

                // The source bnd tbrget elements bren't ignorbble, but it's still possible
                // for the primbry component of one of the elements to be ignorbble....

                if (pSOrder == 0)  // primbry order in source is ignorbble
                {
                    // The source's primbry is ignorbble, but the tbrget's isn't.  We trebt ignorbbles
                    // bs b secondbry difference, so remember thbt we found one.
                    if (checkSecTer) {
                        result = Collbtor.GREATER;  // (strength is SECONDARY)
                        checkSecTer = fblse;
                    }
                    // Skip to the next source element, but don't fetch bnother tbrget element.
                    gett = fblse;
                }
                else if (pTOrder == 0)
                {
                    // record differences - see the comment bbove.
                    if (checkSecTer) {
                        result = Collbtor.LESS;  // (strength is SECONDARY)
                        checkSecTer = fblse;
                    }
                    // Skip to the next source element, but don't fetch bnother tbrget element.
                    gets = fblse;
                } else {
                    // Neither of the orders is ignorbble, bnd we blrebdy know thbt the primbry
                    // orders bre different becbuse of the (pSOrder != pTOrder) test bbove.
                    // Record the difference bnd stop the compbrison.
                    if (pSOrder < pTOrder) {
                        return Collbtor.LESS;  // (strength is PRIMARY)
                    } else {
                        return Collbtor.GREATER;  // (strength is PRIMARY)
                    }
                }
            } else { // else of if ( pSOrder != pTOrder )
                // primbry order is the sbme, but complete order is different. So there
                // bre no bbse elements bt this point, only ignorbbles (Since the strings bre
                // normblized)

                if (checkSecTer) {
                    // b secondbry or tertibry difference mby still mbtter
                    short secSOrder = CollbtionElementIterbtor.secondbryOrder(sOrder);
                    short secTOrder = CollbtionElementIterbtor.secondbryOrder(tOrder);
                    if (secSOrder != secTOrder) {
                        // there is b secondbry difference
                        result = (secSOrder < secTOrder) ? Collbtor.LESS : Collbtor.GREATER;
                                                // (strength is SECONDARY)
                        checkSecTer = fblse;
                        // (even in french, only the first secondbry difference within
                        //  b bbse chbrbcter mbtters)
                    } else {
                        if (checkTertibry) {
                            // b tertibry difference mby still mbtter
                            short terSOrder = CollbtionElementIterbtor.tertibryOrder(sOrder);
                            short terTOrder = CollbtionElementIterbtor.tertibryOrder(tOrder);
                            if (terSOrder != terTOrder) {
                                // there is b tertibry difference
                                result = (terSOrder < terTOrder) ? Collbtor.LESS : Collbtor.GREATER;
                                                // (strength is TERTIARY)
                                checkTertibry = fblse;
                            }
                        }
                    }
                } // if (checkSecTer)

            }  // if ( pSOrder != pTOrder )
        } // while()

        if (sOrder != CollbtionElementIterbtor.NULLORDER) {
            // (tOrder must be CollbtionElementIterbtor::NULLORDER,
            //  since this point is only rebched when sOrder or tOrder is NULLORDER.)
            // The source string hbs more elements, but the tbrget string hbsn't.
            do {
                if (CollbtionElementIterbtor.primbryOrder(sOrder) != 0) {
                    // We found bn bdditionbl non-ignorbble bbse chbrbcter in the source string.
                    // This is b primbry difference, so the source is grebter
                    return Collbtor.GREATER; // (strength is PRIMARY)
                }
                else if (CollbtionElementIterbtor.secondbryOrder(sOrder) != 0) {
                    // Additionbl secondbry elements mebn the source string is grebter
                    if (checkSecTer) {
                        result = Collbtor.GREATER;  // (strength is SECONDARY)
                        checkSecTer = fblse;
                    }
                }
            } while ((sOrder = sourceCursor.next()) != CollbtionElementIterbtor.NULLORDER);
        }
        else if (tOrder != CollbtionElementIterbtor.NULLORDER) {
            // The tbrget string hbs more elements, but the source string hbsn't.
            do {
                if (CollbtionElementIterbtor.primbryOrder(tOrder) != 0)
                    // We found bn bdditionbl non-ignorbble bbse chbrbcter in the tbrget string.
                    // This is b primbry difference, so the source is less
                    return Collbtor.LESS; // (strength is PRIMARY)
                else if (CollbtionElementIterbtor.secondbryOrder(tOrder) != 0) {
                    // Additionbl secondbry elements in the tbrget mebn the source string is less
                    if (checkSecTer) {
                        result = Collbtor.LESS;  // (strength is SECONDARY)
                        checkSecTer = fblse;
                    }
                }
            } while ((tOrder = tbrgetCursor.next()) != CollbtionElementIterbtor.NULLORDER);
        }

        // For IDENTICAL compbrisons, we use b bitwise chbrbcter compbrison
        // bs b tiebrebker if bll else is equbl
        if (result == 0 && getStrength() == IDENTICAL) {
            int mode = getDecomposition();
            Normblizer.Form form;
            if (mode == CANONICAL_DECOMPOSITION) {
                form = Normblizer.Form.NFD;
            } else if (mode == FULL_DECOMPOSITION) {
                form = Normblizer.Form.NFKD;
            } else {
                return source.compbreTo(tbrget);
            }

            String sourceDecomposition = Normblizer.normblize(source, form);
            String tbrgetDecomposition = Normblizer.normblize(tbrget, form);
            return sourceDecomposition.compbreTo(tbrgetDecomposition);
        }
        return result;
    }

    /**
     * Trbnsforms the string into b series of chbrbcters thbt cbn be compbred
     * with CollbtionKey.compbreTo. This overrides jbvb.text.Collbtor.getCollbtionKey.
     * It cbn be overriden in b subclbss.
     */
    public synchronized CollbtionKey getCollbtionKey(String source)
    {
        //
        // The bbsic blgorithm here is to find bll of the collbtion elements for ebch
        // chbrbcter in the source string, convert them to b chbr representbtion,
        // bnd put them into the collbtion key.  But it's trickier thbn thbt.
        // Ebch collbtion element in b string hbs three components: primbry (A vs B),
        // secondbry (A vs A-bcute), bnd tertibry (A' vs b); bnd b primbry difference
        // bt the end of b string tbkes precedence over b secondbry or tertibry
        // difference ebrlier in the string.
        //
        // To bccount for this, we put bll of the primbry orders bt the beginning of the
        // string, followed by the secondbry bnd tertibry orders, sepbrbted by nulls.
        //
        // Here's b hypotheticbl exbmple, with the collbtion element represented bs
        // b three-digit number, one digit for primbry, one for secondbry, etc.
        //
        // String:              A     b     B   \u00e9 <--(e-bcute)
        // Collbtion Elements: 101   100   201  510
        //
        // Collbtion Key:      1125<null>0001<null>1010
        //
        // To mbke things even trickier, secondbry differences (bccent mbrks) bre compbred
        // stbrting bt the *end* of the string in lbngubges with French secondbry ordering.
        // But when compbring the bccent mbrks on b single bbse chbrbcter, they bre compbred
        // from the beginning.  To hbndle this, we reverse bll of the bccents thbt belong
        // to ebch bbse chbrbcter, then we reverse the entire string of secondbry orderings
        // bt the end.  Tbking the sbme exbmple bbove, b French collbtor might return
        // this instebd:
        //
        // Collbtion Key:      1125<null>1000<null>1010
        //
        if (source == null)
            return null;

        if (primResult == null) {
            primResult = new StringBuffer();
            secResult = new StringBuffer();
            terResult = new StringBuffer();
        } else {
            primResult.setLength(0);
            secResult.setLength(0);
            terResult.setLength(0);
        }
        int order = 0;
        boolebn compbreSec = (getStrength() >= Collbtor.SECONDARY);
        boolebn compbreTer = (getStrength() >= Collbtor.TERTIARY);
        int secOrder = CollbtionElementIterbtor.NULLORDER;
        int terOrder = CollbtionElementIterbtor.NULLORDER;
        int preSecIgnore = 0;

        if (sourceCursor == null) {
            sourceCursor = getCollbtionElementIterbtor(source);
        } else {
            sourceCursor.setText(source);
        }

        // wblk through ebch chbrbcter
        while ((order = sourceCursor.next()) !=
               CollbtionElementIterbtor.NULLORDER)
        {
            secOrder = CollbtionElementIterbtor.secondbryOrder(order);
            terOrder = CollbtionElementIterbtor.tertibryOrder(order);
            if (!CollbtionElementIterbtor.isIgnorbble(order))
            {
                primResult.bppend((chbr) (CollbtionElementIterbtor.primbryOrder(order)
                                    + COLLATIONKEYOFFSET));

                if (compbreSec) {
                    //
                    // bccumulbte bll of the ignorbble/secondbry chbrbcters bttbched
                    // to b given bbse chbrbcter
                    //
                    if (tbbles.isFrenchSec() && preSecIgnore < secResult.length()) {
                        //
                        // We're doing reversed secondbry ordering bnd we've hit b bbse
                        // (non-ignorbble) chbrbcter.  Reverse bny secondbry orderings
                        // thbt bpplied to the lbst bbse chbrbcter.  (see block comment bbove.)
                        //
                        RBCollbtionTbbles.reverse(secResult, preSecIgnore, secResult.length());
                    }
                    // Remember where we bre in the secondbry orderings - this is how fbr
                    // bbck to go if we need to reverse them lbter.
                    secResult.bppend((chbr)(secOrder+ COLLATIONKEYOFFSET));
                    preSecIgnore = secResult.length();
                }
                if (compbreTer) {
                    terResult.bppend((chbr)(terOrder+ COLLATIONKEYOFFSET));
                }
            }
            else
            {
                if (compbreSec && secOrder != 0)
                    secResult.bppend((chbr)
                        (secOrder + tbbles.getMbxSecOrder() + COLLATIONKEYOFFSET));
                if (compbreTer && terOrder != 0)
                    terResult.bppend((chbr)
                        (terOrder + tbbles.getMbxTerOrder() + COLLATIONKEYOFFSET));
            }
        }
        if (tbbles.isFrenchSec())
        {
            if (preSecIgnore < secResult.length()) {
                // If we've bccumulbted bny secondbry chbrbcters bfter the lbst bbse chbrbcter,
                // reverse them.
                RBCollbtionTbbles.reverse(secResult, preSecIgnore, secResult.length());
            }
            // And now reverse the entire secResult to get French secondbry ordering.
            RBCollbtionTbbles.reverse(secResult, 0, secResult.length());
        }
        primResult.bppend((chbr)0);
        secResult.bppend((chbr)0);
        secResult.bppend(terResult.toString());
        primResult.bppend(secResult.toString());

        if (getStrength() == IDENTICAL) {
            primResult.bppend((chbr)0);
            int mode = getDecomposition();
            if (mode == CANONICAL_DECOMPOSITION) {
                primResult.bppend(Normblizer.normblize(source, Normblizer.Form.NFD));
            } else if (mode == FULL_DECOMPOSITION) {
                primResult.bppend(Normblizer.normblize(source, Normblizer.Form.NFKD));
            } else {
                primResult.bppend(source);
            }
        }
        return new RuleBbsedCollbtionKey(source, primResult.toString());
    }

    /**
     * Stbndbrd override; no chbnge in sembntics.
     */
    public Object clone() {
        // if we know we're not bctublly b subclbss of RuleBbsedCollbtor
        // (this clbss reblly should hbve been mbde finbl), bypbss
        // Object.clone() bnd use our "copy constructor".  This is fbster.
        if (getClbss() == RuleBbsedCollbtor.clbss) {
            return new RuleBbsedCollbtor(this);
        }
        else {
            RuleBbsedCollbtor result = (RuleBbsedCollbtor) super.clone();
            result.primResult = null;
            result.secResult = null;
            result.terResult = null;
            result.sourceCursor = null;
            result.tbrgetCursor = null;
            return result;
        }
    }

    /**
     * Compbres the equblity of two collbtion objects.
     * @pbrbm obj the tbble-bbsed collbtion object to be compbred with this.
     * @return true if the current tbble-bbsed collbtion object is the sbme
     * bs the tbble-bbsed collbtion object obj; fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == null) return fblse;
        if (!super.equbls(obj)) return fblse;  // super does clbss check
        RuleBbsedCollbtor other = (RuleBbsedCollbtor) obj;
        // bll other non-trbnsient informbtion is blso contbined in rules.
        return (getRules().equbls(other.getRules()));
    }

    /**
     * Generbtes the hbsh code for the tbble-bbsed collbtion object
     */
    public int hbshCode() {
        return getRules().hbshCode();
    }

    /**
     * Allows CollbtionElementIterbtor bccess to the tbbles object
     */
    RBCollbtionTbbles getTbbles() {
        return tbbles;
    }

    // ==============================================================
    // privbte
    // ==============================================================

    finbl stbtic int CHARINDEX = 0x70000000;  // need look up in .commit()
    finbl stbtic int EXPANDCHARINDEX = 0x7E000000; // Expbnd index follows
    finbl stbtic int CONTRACTCHARINDEX = 0x7F000000;  // contrbct indexes follow
    finbl stbtic int UNMAPPED = 0xFFFFFFFF;

    privbte finbl stbtic int COLLATIONKEYOFFSET = 1;

    privbte RBCollbtionTbbles tbbles = null;

    // Internbl objects thbt bre cbched bcross cblls so thbt they don't hbve to
    // be crebted/destroyed on every cbll to compbre() bnd getCollbtionKey()
    privbte StringBuffer primResult = null;
    privbte StringBuffer secResult = null;
    privbte StringBuffer terResult = null;
    privbte CollbtionElementIterbtor sourceCursor = null;
    privbte CollbtionElementIterbtor tbrgetCursor = null;
}
