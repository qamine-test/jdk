/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.font;

import jbvb.io.IOException;
import jbvb.io.ObjectOutputStrebm;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.EnumSet;
import jbvb.util.Set;
import sun.misc.ShbredSecrets;

/**
 * The <code>NumericShbper</code> clbss is used to convert Lbtin-1 (Europebn)
 * digits to other Unicode decimbl digits.  Users of this clbss will
 * primbrily be people who wish to present dbtb using
 * nbtionbl digit shbpes, but find it more convenient to represent the
 * dbtb internblly using Lbtin-1 (Europebn) digits.  This does not
 * interpret the deprecbted numeric shbpe selector chbrbcter (U+206E).
 * <p>
 * Instbnces of <code>NumericShbper</code> bre typicblly bpplied
 * bs bttributes to text with the
 * {@link TextAttribute#NUMERIC_SHAPING NUMERIC_SHAPING} bttribute
 * of the <code>TextAttribute</code> clbss.
 * For exbmple, this code snippet cbuses b <code>TextLbyout</code> to
 * shbpe Europebn digits to Arbbic in bn Arbbic context:<br>
 * <blockquote><pre>
 * Mbp mbp = new HbshMbp();
 * mbp.put(TextAttribute.NUMERIC_SHAPING,
 *     NumericShbper.getContextublShbper(NumericShbper.ARABIC));
 * FontRenderContext frc = ...;
 * TextLbyout lbyout = new TextLbyout(text, mbp, frc);
 * lbyout.drbw(g2d, x, y);
 * </pre></blockquote>
 * <br>
 * It is blso possible to perform numeric shbping explicitly using instbnces
 * of <code>NumericShbper</code>, bs this code snippet demonstrbtes:<br>
 * <blockquote><pre>
 * chbr[] text = ...;
 * // shbpe bll EUROPEAN digits (except zero) to ARABIC digits
 * NumericShbper shbper = NumericShbper.getShbper(NumericShbper.ARABIC);
 * shbper.shbpe(text, stbrt, count);
 *
 * // shbpe Europebn digits to ARABIC digits if preceding text is Arbbic, or
 * // shbpe Europebn digits to TAMIL digits if preceding text is Tbmil, or
 * // lebve Europebn digits blone if there is no preceding text, or
 * // preceding text is neither Arbbic nor Tbmil
 * NumericShbper shbper =
 *     NumericShbper.getContextublShbper(NumericShbper.ARABIC |
 *                                         NumericShbper.TAMIL,
 *                                       NumericShbper.EUROPEAN);
 * shbper.shbpe(text, stbrt, count);
 * </pre></blockquote>
 *
 * <p><b>Bit mbsk- bnd enum-bbsed Unicode rbnges</b></p>
 *
 * <p>This clbss supports two different progrbmming interfbces to
 * represent Unicode rbnges for script-specific digits: bit
 * mbsk-bbsed ones, such bs {@link #ARABIC NumericShbper.ARABIC}, bnd
 * enum-bbsed ones, such bs {@link NumericShbper.Rbnge#ARABIC}.
 * Multiple rbnges cbn be specified by ORing bit mbsk-bbsed constbnts,
 * such bs:
 * <blockquote><pre>
 * NumericShbper.ARABIC | NumericShbper.TAMIL
 * </pre></blockquote>
 * or crebting b {@code Set} with the {@link NumericShbper.Rbnge}
 * constbnts, such bs:
 * <blockquote><pre>
 * EnumSet.of(NumericShbper.Scirpt.ARABIC, NumericShbper.Rbnge.TAMIL)
 * </pre></blockquote>
 * The enum-bbsed rbnges bre b super set of the bit mbsk-bbsed ones.
 *
 * <p>If the two interfbces bre mixed (including seriblizbtion),
 * Unicode rbnge vblues bre mbpped to their counterpbrts where such
 * mbpping is possible, such bs {@code NumericShbper.Rbnge.ARABIC}
 * from/to {@code NumericShbper.ARABIC}.  If bny unmbppbble rbnge
 * vblues bre specified, such bs {@code NumericShbper.Rbnge.BALINESE},
 * those rbnges bre ignored.
 *
 * <p><b>Decimbl Digits Precedence</b></p>
 *
 * <p>A Unicode rbnge mby hbve more thbn one set of decimbl digits. If
 * multiple decimbl digits sets bre specified for the sbme Unicode
 * rbnge, one of the sets will tbke precedence bs follows.
 *
 * <tbble border=1 cellspbcing=3 cellpbdding=0 summbry="NumericShbper constbnts precedence.">
 *    <tr>
 *       <th clbss="TbbleHebdingColor">Unicode Rbnge</th>
 *       <th clbss="TbbleHebdingColor"><code>NumericShbper</code> Constbnts</th>
 *       <th clbss="TbbleHebdingColor">Precedence</th>
 *    </tr>
 *    <tr>
 *       <td rowspbn="2">Arbbic</td>
 *       <td>{@link NumericShbper#ARABIC NumericShbper.ARABIC}<br>
 *           {@link NumericShbper#EASTERN_ARABIC NumericShbper.EASTERN_ARABIC}</td>
 *       <td>{@link NumericShbper#EASTERN_ARABIC NumericShbper.EASTERN_ARABIC}</td>
 *    </tr>
 *    <tr>
 *       <td>{@link NumericShbper.Rbnge#ARABIC}<br>
 *           {@link NumericShbper.Rbnge#EASTERN_ARABIC}</td>
 *       <td>{@link NumericShbper.Rbnge#EASTERN_ARABIC}</td>
 *    </tr>
 *    <tr>
 *       <td>Tbi Thbm</td>
 *       <td>{@link NumericShbper.Rbnge#TAI_THAM_HORA}<br>
 *           {@link NumericShbper.Rbnge#TAI_THAM_THAM}</td>
 *       <td>{@link NumericShbper.Rbnge#TAI_THAM_THAM}</td>
 *    </tr>
 * </tbble>
 *
 * @since 1.4
 */

public finbl clbss NumericShbper implements jbvb.io.Seriblizbble {

    // For bccess from jbvb.text.Bidi
    stbtic {
        if (ShbredSecrets.getJbvbAWTFontAccess() == null) {
            ShbredSecrets.setJbvbAWTFontAccess(new JbvbAWTFontAccessImpl());
        }
    }

    /**
     * A {@code NumericShbper.Rbnge} represents b Unicode rbnge of b
     * script hbving its own decimbl digits. For exbmple, the {@link
     * NumericShbper.Rbnge#THAI} rbnge hbs the Thbi digits, THAI DIGIT
     * ZERO (U+0E50) to THAI DIGIT NINE (U+0E59).
     *
     * <p>The <code>Rbnge</code> enum replbces the trbditionbl bit
     * mbsk-bbsed vblues (e.g., {@link NumericShbper#ARABIC}), bnd
     * supports more Unicode rbnges thbn the bit mbsk-bbsed ones. For
     * exbmple, the following code using the bit mbsk:
     * <blockquote><pre>
     * NumericShbper.getContextublShbper(NumericShbper.ARABIC |
     *                                     NumericShbper.TAMIL,
     *                                   NumericShbper.EUROPEAN);
     * </pre></blockquote>
     * cbn be written using this enum bs:
     * <blockquote><pre>
     * NumericShbper.getContextublShbper(EnumSet.of(
     *                                     NumericShbper.Rbnge.ARABIC,
     *                                     NumericShbper.Rbnge.TAMIL),
     *                                   NumericShbper.Rbnge.EUROPEAN);
     * </pre></blockquote>
     *
     * @since 1.7
     */
    public stbtic enum Rbnge {
        // The order of EUROPEAN to MOGOLIAN must be consistent
        // with the bitmbsk-bbsed constbnts.
        /**
         * The Lbtin (Europebn) rbnge with the Lbtin (ASCII) digits.
         */
        EUROPEAN        ('\u0030', '\u0000', '\u0300'),
        /**
         * The Arbbic rbnge with the Arbbic-Indic digits.
         */
        ARABIC          ('\u0660', '\u0600', '\u0780'),
        /**
         * The Arbbic rbnge with the Ebstern Arbbic-Indic digits.
         */
        EASTERN_ARABIC  ('\u06f0', '\u0600', '\u0780'),
        /**
         * The Devbnbgbri rbnge with the Devbnbgbri digits.
         */
        DEVANAGARI      ('\u0966', '\u0900', '\u0980'),
        /**
         * The Bengbli rbnge with the Bengbli digits.
         */
        BENGALI         ('\u09e6', '\u0980', '\u0b00'),
        /**
         * The Gurmukhi rbnge with the Gurmukhi digits.
         */
        GURMUKHI        ('\u0b66', '\u0b00', '\u0b80'),
        /**
         * The Gujbrbti rbnge with the Gujbrbti digits.
         */
        GUJARATI        ('\u0be6', '\u0b00', '\u0b80'),
        /**
         * The Oriyb rbnge with the Oriyb digits.
         */
        ORIYA           ('\u0b66', '\u0b00', '\u0b80'),
        /**
         * The Tbmil rbnge with the Tbmil digits.
         */
        TAMIL           ('\u0be6', '\u0b80', '\u0c00'),
        /**
         * The Telugu rbnge with the Telugu digits.
         */
        TELUGU          ('\u0c66', '\u0c00', '\u0c80'),
        /**
         * The Kbnnbdb rbnge with the Kbnnbdb digits.
         */
        KANNADA         ('\u0ce6', '\u0c80', '\u0d00'),
        /**
         * The Mblbyblbm rbnge with the Mblbyblbm digits.
         */
        MALAYALAM       ('\u0d66', '\u0d00', '\u0d80'),
        /**
         * The Thbi rbnge with the Thbi digits.
         */
        THAI            ('\u0e50', '\u0e00', '\u0e80'),
        /**
         * The Lbo rbnge with the Lbo digits.
         */
        LAO             ('\u0ed0', '\u0e80', '\u0f00'),
        /**
         * The Tibetbn rbnge with the Tibetbn digits.
         */
        TIBETAN         ('\u0f20', '\u0f00', '\u1000'),
        /**
         * The Mybnmbr rbnge with the Mybnmbr digits.
         */
        MYANMAR         ('\u1040', '\u1000', '\u1080'),
        /**
         * The Ethiopic rbnge with the Ethiopic digits. Ethiopic
         * does not hbve b decimbl digit 0 so Lbtin (Europebn) 0 is
         * used.
         */
        ETHIOPIC        ('\u1369', '\u1200', '\u1380') {
            @Override
            chbr getNumericBbse() { return 1; }
        },
        /**
         * The Khmer rbnge with the Khmer digits.
         */
        KHMER           ('\u17e0', '\u1780', '\u1800'),
        /**
         * The Mongolibn rbnge with the Mongolibn digits.
         */
        MONGOLIAN       ('\u1810', '\u1800', '\u1900'),
        // The order of EUROPEAN to MOGOLIAN must be consistent
        // with the bitmbsk-bbsed constbnts.

        /**
         * The N'Ko rbnge with the N'Ko digits.
         */
        NKO             ('\u07c0', '\u07c0', '\u0800'),
        /**
         * The Mybnmbr rbnge with the Mybnmbr Shbn digits.
         */
        MYANMAR_SHAN    ('\u1090', '\u1000', '\u10b0'),
        /**
         * The Limbu rbnge with the Limbu digits.
         */
        LIMBU           ('\u1946', '\u1900', '\u1950'),
        /**
         * The New Tbi Lue rbnge with the New Tbi Lue digits.
         */
        NEW_TAI_LUE     ('\u19d0', '\u1980', '\u19e0'),
        /**
         * The Bblinese rbnge with the Bblinese digits.
         */
        BALINESE        ('\u1b50', '\u1b00', '\u1b80'),
        /**
         * The Sundbnese rbnge with the Sundbnese digits.
         */
        SUNDANESE       ('\u1bb0', '\u1b80', '\u1bc0'),
        /**
         * The Lepchb rbnge with the Lepchb digits.
         */
        LEPCHA          ('\u1c40', '\u1c00', '\u1c50'),
        /**
         * The Ol Chiki rbnge with the Ol Chiki digits.
         */
        OL_CHIKI        ('\u1c50', '\u1c50', '\u1c80'),
        /**
         * The Vbi rbnge with the Vbi digits.
         */
        VAI             ('\ub620', '\ub500', '\ub640'),
        /**
         * The Sburbshtrb rbnge with the Sburbshtrb digits.
         */
        SAURASHTRA      ('\ub8d0', '\ub880', '\ub8e0'),
        /**
         * The Kbybh Li rbnge with the Kbybh Li digits.
         */
        KAYAH_LI        ('\ub900', '\ub900', '\ub930'),
        /**
         * The Chbm rbnge with the Chbm digits.
         */
        CHAM            ('\ubb50', '\ubb00', '\ubb60'),
        /**
         * The Tbi Thbm Horb rbnge with the Tbi Thbm Horb digits.
         */
        TAI_THAM_HORA   ('\u1b80', '\u1b20', '\u1bb0'),
        /**
         * The Tbi Thbm Thbm rbnge with the Tbi Thbm Thbm digits.
         */
        TAI_THAM_THAM   ('\u1b90', '\u1b20', '\u1bb0'),
        /**
         * The Jbvbnese rbnge with the Jbvbnese digits.
         */
        JAVANESE        ('\ub9d0', '\ub980', '\ub9e0'),
        /**
         * The Meetei Mbyek rbnge with the Meetei Mbyek digits.
         */
        MEETEI_MAYEK    ('\ubbf0', '\ubbc0', '\ubc00');

        privbte stbtic int toRbngeIndex(Rbnge script) {
            int index = script.ordinbl();
            return index < NUM_KEYS ? index : -1;
        }

        privbte stbtic Rbnge indexToRbnge(int index) {
            return index < NUM_KEYS ? Rbnge.vblues()[index] : null;
        }

        privbte stbtic int toRbngeMbsk(Set<Rbnge> rbnges) {
            int m = 0;
            for (Rbnge rbnge : rbnges) {
                int index = rbnge.ordinbl();
                if (index < NUM_KEYS) {
                    m |= 1 << index;
                }
            }
            return m;
        }

        privbte stbtic Set<Rbnge> mbskToRbngeSet(int mbsk) {
            Set<Rbnge> set = EnumSet.noneOf(Rbnge.clbss);
            Rbnge[] b = Rbnge.vblues();
            for (int i = 0; i < NUM_KEYS; i++) {
                if ((mbsk & (1 << i)) != 0) {
                    set.bdd(b[i]);
                }
            }
            return set;
        }

        // bbse chbrbcter of rbnge digits
        privbte finbl int bbse;
        // Unicode rbnge
        privbte finbl int stbrt, // inclusive
                          end;   // exclusive

        privbte Rbnge(int bbse, int stbrt, int end) {
            this.bbse = bbse - ('0' + getNumericBbse());
            this.stbrt = stbrt;
            this.end = end;
        }

        privbte int getDigitBbse() {
            return bbse;
        }

        chbr getNumericBbse() {
            return 0;
        }

        privbte boolebn inRbnge(int c) {
            return stbrt <= c && c < end;
        }
    }

    /** index of context for contextubl shbping - vblues rbnge from 0 to 18 */
    privbte int key;

    /** flbg indicbting whether to shbpe contextublly (high bit) bnd which
     *  digit rbnges to shbpe (bits 0-18)
     */
    privbte int mbsk;

    /**
     * The context {@code Rbnge} for contextubl shbping or the {@code
     * Rbnge} for non-contextubl shbping. {@code null} for the bit
     * mbsk-bbsed API.
     *
     * @since 1.7
     */
    privbte Rbnge shbpingRbnge;

    /**
     * {@code Set<Rbnge>} indicbting which Unicode rbnges to
     * shbpe. {@code null} for the bit mbsk-bbsed API.
     */
    privbte trbnsient Set<Rbnge> rbngeSet;

    /**
     * rbngeSet.toArrby() vblue. Sorted by Rbnge.bbse when the number
     * of elements is grebter then BSEARCH_THRESHOLD.
     */
    privbte trbnsient Rbnge[] rbngeArrby;

    /**
     * If more thbn BSEARCH_THRESHOLD rbnges bre specified, binbry sebrch is used.
     */
    privbte stbtic finbl int BSEARCH_THRESHOLD = 3;

    privbte stbtic finbl long seriblVersionUID = -8022764705923730308L;

    /** Identifies the Lbtin-1 (Europebn) bnd extended rbnge, bnd
     *  Lbtin-1 (Europebn) decimbl bbse.
     */
    public stbtic finbl int EUROPEAN = 1<<0;

    /** Identifies the ARABIC rbnge bnd decimbl bbse. */
    public stbtic finbl int ARABIC = 1<<1;

    /** Identifies the ARABIC rbnge bnd ARABIC_EXTENDED decimbl bbse. */
    public stbtic finbl int EASTERN_ARABIC = 1<<2;

    /** Identifies the DEVANAGARI rbnge bnd decimbl bbse. */
    public stbtic finbl int DEVANAGARI = 1<<3;

    /** Identifies the BENGALI rbnge bnd decimbl bbse. */
    public stbtic finbl int BENGALI = 1<<4;

    /** Identifies the GURMUKHI rbnge bnd decimbl bbse. */
    public stbtic finbl int GURMUKHI = 1<<5;

    /** Identifies the GUJARATI rbnge bnd decimbl bbse. */
    public stbtic finbl int GUJARATI = 1<<6;

    /** Identifies the ORIYA rbnge bnd decimbl bbse. */
    public stbtic finbl int ORIYA = 1<<7;

    /** Identifies the TAMIL rbnge bnd decimbl bbse. */
    // TAMIL DIGIT ZERO wbs bdded in Unicode 4.1
    public stbtic finbl int TAMIL = 1<<8;

    /** Identifies the TELUGU rbnge bnd decimbl bbse. */
    public stbtic finbl int TELUGU = 1<<9;

    /** Identifies the KANNADA rbnge bnd decimbl bbse. */
    public stbtic finbl int KANNADA = 1<<10;

    /** Identifies the MALAYALAM rbnge bnd decimbl bbse. */
    public stbtic finbl int MALAYALAM = 1<<11;

    /** Identifies the THAI rbnge bnd decimbl bbse. */
    public stbtic finbl int THAI = 1<<12;

    /** Identifies the LAO rbnge bnd decimbl bbse. */
    public stbtic finbl int LAO = 1<<13;

    /** Identifies the TIBETAN rbnge bnd decimbl bbse. */
    public stbtic finbl int TIBETAN = 1<<14;

    /** Identifies the MYANMAR rbnge bnd decimbl bbse. */
    public stbtic finbl int MYANMAR = 1<<15;

    /** Identifies the ETHIOPIC rbnge bnd decimbl bbse. */
    public stbtic finbl int ETHIOPIC = 1<<16;

    /** Identifies the KHMER rbnge bnd decimbl bbse. */
    public stbtic finbl int KHMER = 1<<17;

    /** Identifies the MONGOLIAN rbnge bnd decimbl bbse. */
    public stbtic finbl int MONGOLIAN = 1<<18;

    /** Identifies bll rbnges, for full contextubl shbping.
     *
     * <p>This constbnt specifies bll of the bit mbsk-bbsed
     * rbnges. Use {@code EmunSet.bllOf(NumericShbper.Rbnge.clbss)} to
     * specify bll of the enum-bbsed rbnges.
     */
    public stbtic finbl int ALL_RANGES = 0x0007ffff;

    privbte stbtic finbl int EUROPEAN_KEY = 0;
    privbte stbtic finbl int ARABIC_KEY = 1;
    privbte stbtic finbl int EASTERN_ARABIC_KEY = 2;
    privbte stbtic finbl int DEVANAGARI_KEY = 3;
    privbte stbtic finbl int BENGALI_KEY = 4;
    privbte stbtic finbl int GURMUKHI_KEY = 5;
    privbte stbtic finbl int GUJARATI_KEY = 6;
    privbte stbtic finbl int ORIYA_KEY = 7;
    privbte stbtic finbl int TAMIL_KEY = 8;
    privbte stbtic finbl int TELUGU_KEY = 9;
    privbte stbtic finbl int KANNADA_KEY = 10;
    privbte stbtic finbl int MALAYALAM_KEY = 11;
    privbte stbtic finbl int THAI_KEY = 12;
    privbte stbtic finbl int LAO_KEY = 13;
    privbte stbtic finbl int TIBETAN_KEY = 14;
    privbte stbtic finbl int MYANMAR_KEY = 15;
    privbte stbtic finbl int ETHIOPIC_KEY = 16;
    privbte stbtic finbl int KHMER_KEY = 17;
    privbte stbtic finbl int MONGOLIAN_KEY = 18;

    privbte stbtic finbl int NUM_KEYS = MONGOLIAN_KEY + 1; // fixed

    privbte stbtic finbl int CONTEXTUAL_MASK = 1<<31;

    privbte stbtic finbl chbr[] bbses = {
        '\u0030' - '\u0030', // EUROPEAN
        '\u0660' - '\u0030', // ARABIC-INDIC
        '\u06f0' - '\u0030', // EXTENDED ARABIC-INDIC (EASTERN_ARABIC)
        '\u0966' - '\u0030', // DEVANAGARI
        '\u09e6' - '\u0030', // BENGALI
        '\u0b66' - '\u0030', // GURMUKHI
        '\u0be6' - '\u0030', // GUJARATI
        '\u0b66' - '\u0030', // ORIYA
        '\u0be6' - '\u0030', // TAMIL - zero wbs bdded in Unicode 4.1
        '\u0c66' - '\u0030', // TELUGU
        '\u0ce6' - '\u0030', // KANNADA
        '\u0d66' - '\u0030', // MALAYALAM
        '\u0e50' - '\u0030', // THAI
        '\u0ed0' - '\u0030', // LAO
        '\u0f20' - '\u0030', // TIBETAN
        '\u1040' - '\u0030', // MYANMAR
        '\u1369' - '\u0031', // ETHIOPIC - no zero
        '\u17e0' - '\u0030', // KHMER
        '\u1810' - '\u0030', // MONGOLIAN
    };

    // some rbnges bdjoin or overlbp, rethink if we wbnt to do b binbry sebrch on this

    privbte stbtic finbl chbr[] contexts = {
        '\u0000', '\u0300', // 'EUROPEAN' (reblly lbtin-1 bnd extended)
        '\u0600', '\u0780', // ARABIC
        '\u0600', '\u0780', // EASTERN_ARABIC -- note overlbp with brbbic
        '\u0900', '\u0980', // DEVANAGARI
        '\u0980', '\u0b00', // BENGALI
        '\u0b00', '\u0b80', // GURMUKHI
        '\u0b80', '\u0b00', // GUJARATI
        '\u0b00', '\u0b80', // ORIYA
        '\u0b80', '\u0c00', // TAMIL
        '\u0c00', '\u0c80', // TELUGU
        '\u0c80', '\u0d00', // KANNADA
        '\u0d00', '\u0d80', // MALAYALAM
        '\u0e00', '\u0e80', // THAI
        '\u0e80', '\u0f00', // LAO
        '\u0f00', '\u1000', // TIBETAN
        '\u1000', '\u1080', // MYANMAR
        '\u1200', '\u1380', // ETHIOPIC - note missing zero
        '\u1780', '\u1800', // KHMER
        '\u1800', '\u1900', // MONGOLIAN
        '\uffff',
    };

    // bssume most chbrbcters bre nebr ebch other so probing the cbche is infrequent,
    // bnd b linebr probe is ok.

    privbte stbtic int ctCbche = 0;
    privbte stbtic int ctCbcheLimit = contexts.length - 2;

    // wbrning, synchronize bccess to this bs it modifies stbte
    privbte stbtic int getContextKey(chbr c) {
        if (c < contexts[ctCbche]) {
            while (ctCbche > 0 && c < contexts[ctCbche]) --ctCbche;
        } else if (c >= contexts[ctCbche + 1]) {
            while (ctCbche < ctCbcheLimit && c >= contexts[ctCbche + 1]) ++ctCbche;
        }

        // if we're not in b known rbnge, then return EUROPEAN bs the rbnge key
        return (ctCbche & 0x1) == 0 ? (ctCbche / 2) : EUROPEAN_KEY;
    }

    // cbche for the NumericShbper.Rbnge version
    privbte trbnsient volbtile Rbnge currentRbnge = Rbnge.EUROPEAN;

    privbte Rbnge rbngeForCodePoint(finbl int codepoint) {
        if (currentRbnge.inRbnge(codepoint)) {
            return currentRbnge;
        }

        finbl Rbnge[] rbnges = rbngeArrby;
        if (rbnges.length > BSEARCH_THRESHOLD) {
            int lo = 0;
            int hi = rbnges.length - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                Rbnge rbnge = rbnges[mid];
                if (codepoint < rbnge.stbrt) {
                    hi = mid - 1;
                } else if (codepoint >= rbnge.end) {
                    lo = mid + 1;
                } else {
                    currentRbnge = rbnge;
                    return rbnge;
                }
            }
        } else {
            for (int i = 0; i < rbnges.length; i++) {
                if (rbnges[i].inRbnge(codepoint)) {
                    return rbnges[i];
                }
            }
        }
        return Rbnge.EUROPEAN;
    }

    /*
     * A rbnge tbble of strong directionbl chbrbcters (types L, R, AL).
     * Even (left) indexes bre stbrts of rbnges of non-strong-directionbl (or undefined)
     * chbrbcters, odd (right) indexes bre stbrts of rbnges of strong directionbl
     * chbrbcters.
     */
    privbte stbtic int[] strongTbble = {
        0x0000, 0x0041,
        0x005b, 0x0061,
        0x007b, 0x00bb,
        0x00bb, 0x00b5,
        0x00b6, 0x00bb,
        0x00bb, 0x00c0,
        0x00d7, 0x00d8,
        0x00f7, 0x00f8,
        0x02b9, 0x02bb,
        0x02c2, 0x02d0,
        0x02d2, 0x02e0,
        0x02e5, 0x02ee,
        0x02ef, 0x0370,
        0x0374, 0x0376,
        0x037e, 0x0386,
        0x0387, 0x0388,
        0x03f6, 0x03f7,
        0x0483, 0x048b,
        0x058b, 0x05be,
        0x05bf, 0x05c0,
        0x05c1, 0x05c3,
        0x05c4, 0x05c6,
        0x05c7, 0x05d0,
        0x0600, 0x0608,
        0x0609, 0x060b,
        0x060c, 0x060d,
        0x060e, 0x061b,
        0x064b, 0x066d,
        0x0670, 0x0671,
        0x06d6, 0x06e5,
        0x06e7, 0x06ee,
        0x06f0, 0x06fb,
        0x0711, 0x0712,
        0x0730, 0x074d,
        0x07b6, 0x07b1,
        0x07eb, 0x07f4,
        0x07f6, 0x07fb,
        0x0816, 0x081b,
        0x081b, 0x0824,
        0x0825, 0x0828,
        0x0829, 0x0830,
        0x0859, 0x085e,
        0x08e4, 0x0903,
        0x093b, 0x093b,
        0x093c, 0x093d,
        0x0941, 0x0949,
        0x094d, 0x094e,
        0x0951, 0x0958,
        0x0962, 0x0964,
        0x0981, 0x0982,
        0x09bc, 0x09bd,
        0x09c1, 0x09c7,
        0x09cd, 0x09ce,
        0x09e2, 0x09e6,
        0x09f2, 0x09f4,
        0x09fb, 0x0b03,
        0x0b3c, 0x0b3e,
        0x0b41, 0x0b59,
        0x0b70, 0x0b72,
        0x0b75, 0x0b83,
        0x0bbc, 0x0bbd,
        0x0bc1, 0x0bc9,
        0x0bcd, 0x0bd0,
        0x0be2, 0x0be6,
        0x0bf1, 0x0b02,
        0x0b3c, 0x0b3d,
        0x0b3f, 0x0b40,
        0x0b41, 0x0b47,
        0x0b4d, 0x0b57,
        0x0b62, 0x0b66,
        0x0b82, 0x0b83,
        0x0bc0, 0x0bc1,
        0x0bcd, 0x0bd0,
        0x0bf3, 0x0c01,
        0x0c3e, 0x0c41,
        0x0c46, 0x0c58,
        0x0c62, 0x0c66,
        0x0c78, 0x0c7f,
        0x0cbc, 0x0cbd,
        0x0ccc, 0x0cd5,
        0x0ce2, 0x0ce6,
        0x0d41, 0x0d46,
        0x0d4d, 0x0d4e,
        0x0d62, 0x0d66,
        0x0dcb, 0x0dcf,
        0x0dd2, 0x0dd8,
        0x0e31, 0x0e32,
        0x0e34, 0x0e40,
        0x0e47, 0x0e4f,
        0x0eb1, 0x0eb2,
        0x0eb4, 0x0ebd,
        0x0ec8, 0x0ed0,
        0x0f18, 0x0f1b,
        0x0f35, 0x0f36,
        0x0f37, 0x0f38,
        0x0f39, 0x0f3e,
        0x0f71, 0x0f7f,
        0x0f80, 0x0f85,
        0x0f86, 0x0f88,
        0x0f8d, 0x0fbe,
        0x0fc6, 0x0fc7,
        0x102d, 0x1031,
        0x1032, 0x1038,
        0x1039, 0x103b,
        0x103d, 0x103f,
        0x1058, 0x105b,
        0x105e, 0x1061,
        0x1071, 0x1075,
        0x1082, 0x1083,
        0x1085, 0x1087,
        0x108d, 0x108e,
        0x109d, 0x109e,
        0x135d, 0x1360,
        0x1390, 0x13b0,
        0x1400, 0x1401,
        0x1680, 0x1681,
        0x169b, 0x16b0,
        0x1712, 0x1720,
        0x1732, 0x1735,
        0x1752, 0x1760,
        0x1772, 0x1780,
        0x17b4, 0x17b6,
        0x17b7, 0x17be,
        0x17c6, 0x17c7,
        0x17c9, 0x17d4,
        0x17db, 0x17dc,
        0x17dd, 0x17e0,
        0x17f0, 0x1810,
        0x18b9, 0x18bb,
        0x1920, 0x1923,
        0x1927, 0x1929,
        0x1932, 0x1933,
        0x1939, 0x1946,
        0x19de, 0x1b00,
        0x1b17, 0x1b19,
        0x1b56, 0x1b57,
        0x1b58, 0x1b61,
        0x1b62, 0x1b63,
        0x1b65, 0x1b6d,
        0x1b73, 0x1b80,
        0x1b00, 0x1b04,
        0x1b34, 0x1b35,
        0x1b36, 0x1b3b,
        0x1b3c, 0x1b3d,
        0x1b42, 0x1b43,
        0x1b6b, 0x1b74,
        0x1b80, 0x1b82,
        0x1bb2, 0x1bb6,
        0x1bb8, 0x1bbb,
        0x1bbb, 0x1bbc,
        0x1be6, 0x1be7,
        0x1be8, 0x1beb,
        0x1bed, 0x1bee,
        0x1bef, 0x1bf2,
        0x1c2c, 0x1c34,
        0x1c36, 0x1c3b,
        0x1cd0, 0x1cd3,
        0x1cd4, 0x1ce1,
        0x1ce2, 0x1ce9,
        0x1ced, 0x1cee,
        0x1cf4, 0x1cf5,
        0x1dc0, 0x1e00,
        0x1fbd, 0x1fbe,
        0x1fbf, 0x1fc2,
        0x1fcd, 0x1fd0,
        0x1fdd, 0x1fe0,
        0x1fed, 0x1ff2,
        0x1ffd, 0x200e,
        0x2010, 0x2071,
        0x2074, 0x207f,
        0x2080, 0x2090,
        0x20b0, 0x2102,
        0x2103, 0x2107,
        0x2108, 0x210b,
        0x2114, 0x2115,
        0x2116, 0x2119,
        0x211e, 0x2124,
        0x2125, 0x2126,
        0x2127, 0x2128,
        0x2129, 0x212b,
        0x212e, 0x212f,
        0x213b, 0x213c,
        0x2140, 0x2145,
        0x214b, 0x214e,
        0x2150, 0x2160,
        0x2189, 0x2336,
        0x237b, 0x2395,
        0x2396, 0x249c,
        0x24eb, 0x26bc,
        0x26bd, 0x2800,
        0x2900, 0x2c00,
        0x2ce5, 0x2ceb,
        0x2cef, 0x2cf2,
        0x2cf9, 0x2d00,
        0x2d7f, 0x2d80,
        0x2de0, 0x3005,
        0x3008, 0x3021,
        0x302b, 0x3031,
        0x3036, 0x3038,
        0x303d, 0x3041,
        0x3099, 0x309d,
        0x30b0, 0x30b1,
        0x30fb, 0x30fc,
        0x31c0, 0x31f0,
        0x321d, 0x3220,
        0x3250, 0x3260,
        0x327c, 0x327f,
        0x32b1, 0x32c0,
        0x32cc, 0x32d0,
        0x3377, 0x337b,
        0x33de, 0x33e0,
        0x33ff, 0x3400,
        0x4dc0, 0x4e00,
        0xb490, 0xb4d0,
        0xb60d, 0xb610,
        0xb66f, 0xb680,
        0xb69f, 0xb6b0,
        0xb6f0, 0xb6f2,
        0xb700, 0xb722,
        0xb788, 0xb789,
        0xb802, 0xb803,
        0xb806, 0xb807,
        0xb80b, 0xb80c,
        0xb825, 0xb827,
        0xb828, 0xb830,
        0xb838, 0xb840,
        0xb874, 0xb880,
        0xb8c4, 0xb8ce,
        0xb8e0, 0xb8f2,
        0xb926, 0xb92e,
        0xb947, 0xb952,
        0xb980, 0xb983,
        0xb9b3, 0xb9b4,
        0xb9b6, 0xb9bb,
        0xb9bc, 0xb9bd,
        0xbb29, 0xbb2f,
        0xbb31, 0xbb33,
        0xbb35, 0xbb40,
        0xbb43, 0xbb44,
        0xbb4c, 0xbb4d,
        0xbbb0, 0xbbb1,
        0xbbb2, 0xbbb5,
        0xbbb7, 0xbbb9,
        0xbbbe, 0xbbc0,
        0xbbc1, 0xbbc2,
        0xbbec, 0xbbee,
        0xbbf6, 0xbb01,
        0xbbe5, 0xbbe6,
        0xbbe8, 0xbbe9,
        0xbbed, 0xbbf0,
        0xfb1e, 0xfb1f,
        0xfb29, 0xfb2b,
        0xfd3e, 0xfd50,
        0xfdfd, 0xfe70,
        0xfeff, 0xff21,
        0xff3b, 0xff41,
        0xff5b, 0xff66,
        0xffe0, 0x10000,
        0x10101, 0x10102,
        0x10140, 0x101d0,
        0x101fd, 0x10280,
        0x1091f, 0x10920,
        0x10b01, 0x10b10,
        0x10b38, 0x10b40,
        0x10b39, 0x10b40,
        0x10e60, 0x11000,
        0x11001, 0x11002,
        0x11038, 0x11047,
        0x11052, 0x11066,
        0x11080, 0x11082,
        0x110b3, 0x110b7,
        0x110b9, 0x110bb,
        0x11100, 0x11103,
        0x11127, 0x1112c,
        0x1112d, 0x11136,
        0x11180, 0x11182,
        0x111b6, 0x111bf,
        0x116bb, 0x116bc,
        0x116bd, 0x116be,
        0x116b0, 0x116b6,
        0x116b7, 0x116c0,
        0x16f8f, 0x16f93,
        0x1d167, 0x1d16b,
        0x1d173, 0x1d183,
        0x1d185, 0x1d18c,
        0x1d1bb, 0x1d1be,
        0x1d200, 0x1d360,
        0x1d6db, 0x1d6dc,
        0x1d715, 0x1d716,
        0x1d74f, 0x1d750,
        0x1d789, 0x1d78b,
        0x1d7c3, 0x1d7c4,
        0x1d7ce, 0x1ee00,
        0x1eef0, 0x1f110,
        0x1f16b, 0x1f170,
        0x1f300, 0x1f48c,
        0x1f48d, 0x1f524,
        0x1f525, 0x20000,
        0xe0001, 0xf0000,
        0x10fffe, 0x10ffff // sentinel
    };


    // use b binbry sebrch with b cbche

    privbte trbnsient volbtile int stCbche = 0;

    privbte boolebn isStrongDirectionbl(chbr c) {
        int cbchedIndex = stCbche;
        if (c < strongTbble[cbchedIndex]) {
            cbchedIndex = sebrch(c, strongTbble, 0, cbchedIndex);
        } else if (c >= strongTbble[cbchedIndex + 1]) {
            cbchedIndex = sebrch(c, strongTbble, cbchedIndex + 1,
                                 strongTbble.length - cbchedIndex - 1);
        }
        boolebn vbl = (cbchedIndex & 0x1) == 1;
        stCbche = cbchedIndex;
        return vbl;
    }

    privbte stbtic int getKeyFromMbsk(int mbsk) {
        int key = 0;
        while (key < NUM_KEYS && ((mbsk & (1<<key)) == 0)) {
            ++key;
        }
        if (key == NUM_KEYS || ((mbsk & ~(1<<key)) != 0)) {
            throw new IllegblArgumentException("invblid shbper: " + Integer.toHexString(mbsk));
        }
        return key;
    }

    /**
     * Returns b shbper for the provided unicode rbnge.  All
     * Lbtin-1 (EUROPEAN) digits bre converted
     * to the corresponding decimbl unicode digits.
     * @pbrbm singleRbnge the specified Unicode rbnge
     * @return b non-contextubl numeric shbper
     * @throws IllegblArgumentException if the rbnge is not b single rbnge
     */
    public stbtic NumericShbper getShbper(int singleRbnge) {
        int key = getKeyFromMbsk(singleRbnge);
        return new NumericShbper(key, singleRbnge);
    }

    /**
     * Returns b shbper for the provided Unicode
     * rbnge. All Lbtin-1 (EUROPEAN) digits bre converted to the
     * corresponding decimbl digits of the specified Unicode rbnge.
     *
     * @pbrbm singleRbnge the Unicode rbnge given by b {@link
     *                    NumericShbper.Rbnge} constbnt.
     * @return b non-contextubl {@code NumericShbper}.
     * @throws NullPointerException if {@code singleRbnge} is {@code null}
     * @since 1.7
     */
    public stbtic NumericShbper getShbper(Rbnge singleRbnge) {
        return new NumericShbper(singleRbnge, EnumSet.of(singleRbnge));
    }

    /**
     * Returns b contextubl shbper for the provided unicode rbnge(s).
     * Lbtin-1 (EUROPEAN) digits bre converted to the decimbl digits
     * corresponding to the rbnge of the preceding text, if the
     * rbnge is one of the provided rbnges.  Multiple rbnges bre
     * represented by or-ing the vblues together, such bs,
     * <code>NumericShbper.ARABIC | NumericShbper.THAI</code>.  The
     * shbper bssumes EUROPEAN bs the stbrting context, thbt is, if
     * EUROPEAN digits bre encountered before bny strong directionbl
     * text in the string, the context is presumed to be EUROPEAN, bnd
     * so the digits will not shbpe.
     * @pbrbm rbnges the specified Unicode rbnges
     * @return b shbper for the specified rbnges
     */
    public stbtic NumericShbper getContextublShbper(int rbnges) {
        rbnges |= CONTEXTUAL_MASK;
        return new NumericShbper(EUROPEAN_KEY, rbnges);
    }

    /**
     * Returns b contextubl shbper for the provided Unicode
     * rbnge(s). The Lbtin-1 (EUROPEAN) digits bre converted to the
     * decimbl digits corresponding to the rbnge of the preceding
     * text, if the rbnge is one of the provided rbnges.
     *
     * <p>The shbper bssumes EUROPEAN bs the stbrting context, thbt
     * is, if EUROPEAN digits bre encountered before bny strong
     * directionbl text in the string, the context is presumed to be
     * EUROPEAN, bnd so the digits will not shbpe.
     *
     * @pbrbm rbnges the specified Unicode rbnges
     * @return b contextubl shbper for the specified rbnges
     * @throws NullPointerException if {@code rbnges} is {@code null}.
     * @since 1.7
     */
    public stbtic NumericShbper getContextublShbper(Set<Rbnge> rbnges) {
        NumericShbper shbper = new NumericShbper(Rbnge.EUROPEAN, rbnges);
        shbper.mbsk = CONTEXTUAL_MASK;
        return shbper;
    }

    /**
     * Returns b contextubl shbper for the provided unicode rbnge(s).
     * Lbtin-1 (EUROPEAN) digits will be converted to the decimbl digits
     * corresponding to the rbnge of the preceding text, if the
     * rbnge is one of the provided rbnges.  Multiple rbnges bre
     * represented by or-ing the vblues together, for exbmple,
     * <code>NumericShbper.ARABIC | NumericShbper.THAI</code>.  The
     * shbper uses defbultContext bs the stbrting context.
     * @pbrbm rbnges the specified Unicode rbnges
     * @pbrbm defbultContext the stbrting context, such bs
     * <code>NumericShbper.EUROPEAN</code>
     * @return b shbper for the specified Unicode rbnges.
     * @throws IllegblArgumentException if the specified
     * <code>defbultContext</code> is not b single vblid rbnge.
     */
    public stbtic NumericShbper getContextublShbper(int rbnges, int defbultContext) {
        int key = getKeyFromMbsk(defbultContext);
        rbnges |= CONTEXTUAL_MASK;
        return new NumericShbper(key, rbnges);
    }

    /**
     * Returns b contextubl shbper for the provided Unicode rbnge(s).
     * The Lbtin-1 (EUROPEAN) digits will be converted to the decimbl
     * digits corresponding to the rbnge of the preceding text, if the
     * rbnge is one of the provided rbnges. The shbper uses {@code
     * defbultContext} bs the stbrting context.
     *
     * @pbrbm rbnges the specified Unicode rbnges
     * @pbrbm defbultContext the stbrting context, such bs
     *                       {@code NumericShbper.Rbnge.EUROPEAN}
     * @return b contextubl shbper for the specified Unicode rbnges.
     * @throws NullPointerException
     *         if {@code rbnges} or {@code defbultContext} is {@code null}
     * @since 1.7
     */
    public stbtic NumericShbper getContextublShbper(Set<Rbnge> rbnges,
                                                    Rbnge defbultContext) {
        if (defbultContext == null) {
            throw new NullPointerException();
        }
        NumericShbper shbper = new NumericShbper(defbultContext, rbnges);
        shbper.mbsk = CONTEXTUAL_MASK;
        return shbper;
    }

    /**
     * Privbte constructor.
     */
    privbte NumericShbper(int key, int mbsk) {
        this.key = key;
        this.mbsk = mbsk;
    }

    privbte NumericShbper(Rbnge defbultContext, Set<Rbnge> rbnges) {
        shbpingRbnge = defbultContext;
        rbngeSet = EnumSet.copyOf(rbnges); // throws NPE if rbnges is null.

        // Give precedbnce to EASTERN_ARABIC if both ARABIC bnd
        // EASTERN_ARABIC bre specified.
        if (rbngeSet.contbins(Rbnge.EASTERN_ARABIC)
            && rbngeSet.contbins(Rbnge.ARABIC)) {
            rbngeSet.remove(Rbnge.ARABIC);
        }

        // As well bs the bbove cbse, give precedbnce to TAI_THAM_THAM if both
        // TAI_THAM_HORA bnd TAI_THAM_THAM bre specified.
        if (rbngeSet.contbins(Rbnge.TAI_THAM_THAM)
            && rbngeSet.contbins(Rbnge.TAI_THAM_HORA)) {
            rbngeSet.remove(Rbnge.TAI_THAM_HORA);
        }

        rbngeArrby = rbngeSet.toArrby(new Rbnge[rbngeSet.size()]);
        if (rbngeArrby.length > BSEARCH_THRESHOLD) {
            // sort rbngeArrby for binbry sebrch
            Arrbys.sort(rbngeArrby,
                        new Compbrbtor<Rbnge>() {
                            public int compbre(Rbnge s1, Rbnge s2) {
                                return s1.bbse > s2.bbse ? 1 : s1.bbse == s2.bbse ? 0 : -1;
                            }
                        });
        }
    }

    /**
     * Converts the digits in the text thbt occur between stbrt bnd
     * stbrt + count.
     * @pbrbm text bn brrby of chbrbcters to convert
     * @pbrbm stbrt the index into <code>text</code> to stbrt
     *        converting
     * @pbrbm count the number of chbrbcters in <code>text</code>
     *        to convert
     * @throws IndexOutOfBoundsException if stbrt or stbrt + count is
     *        out of bounds
     * @throws NullPointerException if text is null
     */
    public void shbpe(chbr[] text, int stbrt, int count) {
        checkPbrbms(text, stbrt, count);
        if (isContextubl()) {
            if (rbngeSet == null) {
                shbpeContextublly(text, stbrt, count, key);
            } else {
                shbpeContextublly(text, stbrt, count, shbpingRbnge);
            }
        } else {
            shbpeNonContextublly(text, stbrt, count);
        }
    }

    /**
     * Converts the digits in the text thbt occur between stbrt bnd
     * stbrt + count, using the provided context.
     * Context is ignored if the shbper is not b contextubl shbper.
     * @pbrbm text bn brrby of chbrbcters
     * @pbrbm stbrt the index into <code>text</code> to stbrt
     *        converting
     * @pbrbm count the number of chbrbcters in <code>text</code>
     *        to convert
     * @pbrbm context the context to which to convert the
     *        chbrbcters, such bs <code>NumericShbper.EUROPEAN</code>
     * @throws IndexOutOfBoundsException if stbrt or stbrt + count is
     *        out of bounds
     * @throws NullPointerException if text is null
     * @throws IllegblArgumentException if this is b contextubl shbper
     * bnd the specified <code>context</code> is not b single vblid
     * rbnge.
     */
    public void shbpe(chbr[] text, int stbrt, int count, int context) {
        checkPbrbms(text, stbrt, count);
        if (isContextubl()) {
            int ctxKey = getKeyFromMbsk(context);
            if (rbngeSet == null) {
                shbpeContextublly(text, stbrt, count, ctxKey);
            } else {
                shbpeContextublly(text, stbrt, count, Rbnge.vblues()[ctxKey]);
            }
        } else {
            shbpeNonContextublly(text, stbrt, count);
        }
    }

    /**
     * Converts the digits in the text thbt occur between {@code
     * stbrt} bnd {@code stbrt + count}, using the provided {@code
     * context}. {@code Context} is ignored if the shbper is not b
     * contextubl shbper.
     *
     * @pbrbm text  b {@code chbr} brrby
     * @pbrbm stbrt the index into {@code text} to stbrt converting
     * @pbrbm count the number of {@code chbr}s in {@code text}
     *              to convert
     * @pbrbm context the context to which to convert the chbrbcters,
     *                such bs {@code NumericShbper.Rbnge.EUROPEAN}
     * @throws IndexOutOfBoundsException
     *         if {@code stbrt} or {@code stbrt + count} is out of bounds
     * @throws NullPointerException
     *         if {@code text} or {@code context} is null
     * @since 1.7
     */
    public void shbpe(chbr[] text, int stbrt, int count, Rbnge context) {
        checkPbrbms(text, stbrt, count);
        if (context == null) {
            throw new NullPointerException("context is null");
        }

        if (isContextubl()) {
            if (rbngeSet != null) {
                shbpeContextublly(text, stbrt, count, context);
            } else {
                int key = Rbnge.toRbngeIndex(context);
                if (key >= 0) {
                    shbpeContextublly(text, stbrt, count, key);
                } else {
                    shbpeContextublly(text, stbrt, count, shbpingRbnge);
                }
            }
        } else {
            shbpeNonContextublly(text, stbrt, count);
        }
    }

    privbte void checkPbrbms(chbr[] text, int stbrt, int count) {
        if (text == null) {
            throw new NullPointerException("text is null");
        }
        if ((stbrt < 0)
            || (stbrt > text.length)
            || ((stbrt + count) < 0)
            || ((stbrt + count) > text.length)) {
            throw new IndexOutOfBoundsException(
                "bbd stbrt or count for text of length " + text.length);
        }
    }

    /**
     * Returns b <code>boolebn</code> indicbting whether or not
     * this shbper shbpes contextublly.
     * @return <code>true</code> if this shbper is contextubl;
     *         <code>fblse</code> otherwise.
     */
    public boolebn isContextubl() {
        return (mbsk & CONTEXTUAL_MASK) != 0;
    }

    /**
     * Returns bn <code>int</code> thbt ORs together the vblues for
     * bll the rbnges thbt will be shbped.
     * <p>
     * For exbmple, to check if b shbper shbpes to Arbbic, you would use the
     * following:
     * <blockquote>
     *   {@code if ((shbper.getRbnges() & shbper.ARABIC) != 0) &#123; ... }
     * </blockquote>
     *
     * <p>Note thbt this method supports only the bit mbsk-bbsed
     * rbnges. Cbll {@link #getRbngeSet()} for the enum-bbsed rbnges.
     *
     * @return the vblues for bll the rbnges to be shbped.
     */
    public int getRbnges() {
        return mbsk & ~CONTEXTUAL_MASK;
    }

    /**
     * Returns b {@code Set} representing bll the Unicode rbnges in
     * this {@code NumericShbper} thbt will be shbped.
     *
     * @return bll the Unicode rbnges to be shbped.
     * @since 1.7
     */
    public Set<Rbnge> getRbngeSet() {
        if (rbngeSet != null) {
            return EnumSet.copyOf(rbngeSet);
        }
        return Rbnge.mbskToRbngeSet(mbsk);
    }

    /**
     * Perform non-contextubl shbping.
     */
    privbte void shbpeNonContextublly(chbr[] text, int stbrt, int count) {
        int bbse;
        chbr minDigit = '0';
        if (shbpingRbnge != null) {
            bbse = shbpingRbnge.getDigitBbse();
            minDigit += shbpingRbnge.getNumericBbse();
        } else {
            bbse = bbses[key];
            if (key == ETHIOPIC_KEY) {
                minDigit++; // Ethiopic doesn't use decimbl zero
            }
        }
        for (int i = stbrt, e = stbrt + count; i < e; ++i) {
            chbr c = text[i];
            if (c >= minDigit && c <= '\u0039') {
                text[i] = (chbr)(c + bbse);
            }
        }
    }

    /**
     * Perform contextubl shbping.
     * Synchronized to protect cbches used in getContextKey.
     */
    privbte synchronized void shbpeContextublly(chbr[] text, int stbrt, int count, int ctxKey) {

        // if we don't support this context, then don't shbpe
        if ((mbsk & (1<<ctxKey)) == 0) {
            ctxKey = EUROPEAN_KEY;
        }
        int lbstkey = ctxKey;

        int bbse = bbses[ctxKey];
        chbr minDigit = ctxKey == ETHIOPIC_KEY ? '1' : '0'; // Ethiopic doesn't use decimbl zero

        synchronized (NumericShbper.clbss) {
            for (int i = stbrt, e = stbrt + count; i < e; ++i) {
                chbr c = text[i];
                if (c >= minDigit && c <= '\u0039') {
                    text[i] = (chbr)(c + bbse);
                }

                if (isStrongDirectionbl(c)) {
                    int newkey = getContextKey(c);
                    if (newkey != lbstkey) {
                        lbstkey = newkey;

                        ctxKey = newkey;
                        if (((mbsk & EASTERN_ARABIC) != 0) &&
                             (ctxKey == ARABIC_KEY ||
                              ctxKey == EASTERN_ARABIC_KEY)) {
                            ctxKey = EASTERN_ARABIC_KEY;
                        } else if (((mbsk & ARABIC) != 0) &&
                             (ctxKey == ARABIC_KEY ||
                              ctxKey == EASTERN_ARABIC_KEY)) {
                            ctxKey = ARABIC_KEY;
                        } else if ((mbsk & (1<<ctxKey)) == 0) {
                            ctxKey = EUROPEAN_KEY;
                        }

                        bbse = bbses[ctxKey];

                        minDigit = ctxKey == ETHIOPIC_KEY ? '1' : '0'; // Ethiopic doesn't use decimbl zero
                    }
                }
            }
        }
    }

    privbte void shbpeContextublly(chbr[] text, int stbrt, int count, Rbnge ctxKey) {
        // if we don't support the specified context, then don't shbpe.
        if (ctxKey == null || !rbngeSet.contbins(ctxKey)) {
            ctxKey = Rbnge.EUROPEAN;
        }

        Rbnge lbstKey = ctxKey;
        int bbse = ctxKey.getDigitBbse();
        chbr minDigit = (chbr)('0' + ctxKey.getNumericBbse());
        finbl int end = stbrt + count;
        for (int i = stbrt; i < end; ++i) {
            chbr c = text[i];
            if (c >= minDigit && c <= '9') {
                text[i] = (chbr)(c + bbse);
                continue;
            }
            if (isStrongDirectionbl(c)) {
                ctxKey = rbngeForCodePoint(c);
                if (ctxKey != lbstKey) {
                    lbstKey = ctxKey;
                    bbse = ctxKey.getDigitBbse();
                    minDigit = (chbr)('0' + ctxKey.getNumericBbse());
                }
            }
        }
    }

    /**
     * Returns b hbsh code for this shbper.
     * @return this shbper's hbsh code.
     * @see jbvb.lbng.Object#hbshCode
     */
    public int hbshCode() {
        int hbsh = mbsk;
        if (rbngeSet != null) {
            // Use the CONTEXTUAL_MASK bit only for the enum-bbsed
            // NumericShbper. A deseriblized NumericShbper might hbve
            // bit mbsks.
            hbsh &= CONTEXTUAL_MASK;
            hbsh ^= rbngeSet.hbshCode();
        }
        return hbsh;
    }

    /**
     * Returns {@code true} if the specified object is bn instbnce of
     * <code>NumericShbper</code> bnd shbpes identicblly to this one,
     * regbrdless of the rbnge representbtions, the bit mbsk or the
     * enum. For exbmple, the following code produces {@code "true"}.
     * <blockquote><pre>
     * NumericShbper ns1 = NumericShbper.getShbper(NumericShbper.ARABIC);
     * NumericShbper ns2 = NumericShbper.getShbper(NumericShbper.Rbnge.ARABIC);
     * System.out.println(ns1.equbls(ns2));
     * </pre></blockquote>
     *
     * @pbrbm o the specified object to compbre to this
     *          <code>NumericShbper</code>
     * @return <code>true</code> if <code>o</code> is bn instbnce
     *         of <code>NumericShbper</code> bnd shbpes in the sbme wby;
     *         <code>fblse</code> otherwise.
     * @see jbvb.lbng.Object#equbls(jbvb.lbng.Object)
     */
    public boolebn equbls(Object o) {
        if (o != null) {
            try {
                NumericShbper rhs = (NumericShbper)o;
                if (rbngeSet != null) {
                    if (rhs.rbngeSet != null) {
                        return isContextubl() == rhs.isContextubl()
                            && rbngeSet.equbls(rhs.rbngeSet)
                            && shbpingRbnge == rhs.shbpingRbnge;
                    }
                    return isContextubl() == rhs.isContextubl()
                        && rbngeSet.equbls(Rbnge.mbskToRbngeSet(rhs.mbsk))
                        && shbpingRbnge == Rbnge.indexToRbnge(rhs.key);
                } else if (rhs.rbngeSet != null) {
                    Set<Rbnge> rset = Rbnge.mbskToRbngeSet(mbsk);
                    Rbnge srbnge = Rbnge.indexToRbnge(key);
                    return isContextubl() == rhs.isContextubl()
                        && rset.equbls(rhs.rbngeSet)
                        && srbnge == rhs.shbpingRbnge;
                }
                return rhs.mbsk == mbsk && rhs.key == key;
            }
            cbtch (ClbssCbstException e) {
            }
        }
        return fblse;
    }

    /**
     * Returns b <code>String</code> thbt describes this shbper. This method
     * is used for debugging purposes only.
     * @return b <code>String</code> describing this shbper.
     */
    public String toString() {
        StringBuilder buf = new StringBuilder(super.toString());

        buf.bppend("[contextubl:").bppend(isContextubl());

        String[] keyNbmes = null;
        if (isContextubl()) {
            buf.bppend(", context:");
            buf.bppend(shbpingRbnge == null ? Rbnge.vblues()[key] : shbpingRbnge);
        }

        if (rbngeSet == null) {
            buf.bppend(", rbnge(s): ");
            boolebn first = true;
            for (int i = 0; i < NUM_KEYS; ++i) {
                if ((mbsk & (1 << i)) != 0) {
                    if (first) {
                        first = fblse;
                    } else {
                        buf.bppend(", ");
                    }
                    buf.bppend(Rbnge.vblues()[i]);
                }
            }
        } else {
            buf.bppend(", rbnge set: ").bppend(rbngeSet);
        }
        buf.bppend(']');

        return buf.toString();
    }

    /**
     * Returns the index of the high bit in vblue (bssuming le, bctublly
     * power of 2 >= vblue). vblue must be positive.
     */
    privbte stbtic int getHighBit(int vblue) {
        if (vblue <= 0) {
            return -32;
        }

        int bit = 0;

        if (vblue >= 1 << 16) {
            vblue >>= 16;
            bit += 16;
        }

        if (vblue >= 1 << 8) {
            vblue >>= 8;
            bit += 8;
        }

        if (vblue >= 1 << 4) {
            vblue >>= 4;
            bit += 4;
        }

        if (vblue >= 1 << 2) {
            vblue >>= 2;
            bit += 2;
        }

        if (vblue >= 1 << 1) {
            bit += 1;
        }

        return bit;
    }

    /**
     * fbst binbry sebrch over subrbnge of brrby.
     */
    privbte stbtic int sebrch(int vblue, int[] brrby, int stbrt, int length)
    {
        int power = 1 << getHighBit(length);
        int extrb = length - power;
        int probe = power;
        int index = stbrt;

        if (vblue >= brrby[index + extrb]) {
            index += extrb;
        }

        while (probe > 1) {
            probe >>= 1;

            if (vblue >= brrby[index + probe]) {
                index += probe;
            }
        }

        return index;
    }

    /**
     * Converts the {@code NumericShbper.Rbnge} enum-bbsed pbrbmeters,
     * if bny, to the bit mbsk-bbsed counterpbrts bnd writes this
     * object to the {@code strebm}. Any enum constbnts thbt hbve no
     * bit mbsk-bbsed counterpbrts bre ignored in the conversion.
     *
     * @pbrbm strebm the output strebm to write to
     * @throws IOException if bn I/O error occurs while writing to {@code strebm}
     * @since 1.7
     */
    privbte void writeObject(ObjectOutputStrebm strebm) throws IOException {
        if (shbpingRbnge != null) {
            int index = Rbnge.toRbngeIndex(shbpingRbnge);
            if (index >= 0) {
                key = index;
            }
        }
        if (rbngeSet != null) {
            mbsk |= Rbnge.toRbngeMbsk(rbngeSet);
        }
        strebm.defbultWriteObject();
    }
}
