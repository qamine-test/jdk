/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Copyright (c) 2007-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
 *
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions bre met:
 *
 *  * Redistributions of source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 *  * Redistributions in binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 *  * Neither the nbme of JSR-310 nor the nbmes of its contributors
 *    mby be used to endorse or promote products derived from this softwbre
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jbvb.time;

import stbtic jbvb.time.LocblTime.NANOS_PER_SECOND;
import stbtic jbvb.time.LocblTime.SECONDS_PER_DAY;
import stbtic jbvb.time.LocblTime.SECONDS_PER_HOUR;
import stbtic jbvb.time.LocblTime.SECONDS_PER_MINUTE;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;
import stbtic jbvb.time.temporbl.ChronoUnit.NANOS;
import stbtic jbvb.time.temporbl.ChronoUnit.SECONDS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.mbth.BigDecimbl;
import jbvb.mbth.BigInteger;
import jbvb.mbth.RoundingMode;
import jbvb.time.formbt.DbteTimePbrseException;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Objects;
import jbvb.util.regex.Mbtcher;
import jbvb.util.regex.Pbttern;

/**
 * A time-bbsed bmount of time, such bs '34.5 seconds'.
 * <p>
 * This clbss models b qubntity or bmount of time in terms of seconds bnd nbnoseconds.
 * It cbn be bccessed using other durbtion-bbsed units, such bs minutes bnd hours.
 * In bddition, the {@link ChronoUnit#DAYS DAYS} unit cbn be used bnd is trebted bs
 * exbctly equbl to 24 hours, thus ignoring dbylight sbvings effects.
 * See {@link Period} for the dbte-bbsed equivblent to this clbss.
 * <p>
 * A physicbl durbtion could be of infinite length.
 * For prbcticblity, the durbtion is stored with constrbints similbr to {@link Instbnt}.
 * The durbtion uses nbnosecond resolution with b mbximum vblue of the seconds thbt cbn
 * be held in b {@code long}. This is grebter thbn the current estimbted bge of the universe.
 * <p>
 * The rbnge of b durbtion requires the storbge of b number lbrger thbn b {@code long}.
 * To bchieve this, the clbss stores b {@code long} representing seconds bnd bn {@code int}
 * representing nbnosecond-of-second, which will blwbys be between 0 bnd 999,999,999.
 * The model is of b directed durbtion, mebning thbt the durbtion mby be negbtive.
 * <p>
 * The durbtion is mebsured in "seconds", but these bre not necessbrily identicbl to
 * the scientific "SI second" definition bbsed on btomic clocks.
 * This difference only impbcts durbtions mebsured nebr b lebp-second bnd should not bffect
 * most bpplicbtions.
 * See {@link Instbnt} for b discussion bs to the mebning of the second bnd time-scbles.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code Durbtion} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss Durbtion
        implements TemporblAmount, Compbrbble<Durbtion>, Seriblizbble {

    /**
     * Constbnt for b durbtion of zero.
     */
    public stbtic finbl Durbtion ZERO = new Durbtion(0, 0);
    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 3078945930695997490L;
    /**
     * Constbnt for nbnos per second.
     */
    privbte stbtic finbl BigInteger BI_NANOS_PER_SECOND = BigInteger.vblueOf(NANOS_PER_SECOND);
    /**
     * The pbttern for pbrsing.
     */
    privbte stbtic finbl Pbttern PATTERN =
            Pbttern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?" +
                    "(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,9}))?S)?)?",
                    Pbttern.CASE_INSENSITIVE);

    /**
     * The number of seconds in the durbtion.
     */
    privbte finbl long seconds;
    /**
     * The number of nbnoseconds in the durbtion, expressed bs b frbction of the
     * number of seconds. This is blwbys positive, bnd never exceeds 999,999,999.
     */
    privbte finbl int nbnos;

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Durbtion} representing b number of stbndbrd 24 hour dbys.
     * <p>
     * The seconds bre cblculbted bbsed on the stbndbrd definition of b dby,
     * where ebch dby is 86400 seconds which implies b 24 hour dby.
     * The nbnosecond in second field is set to zero.
     *
     * @pbrbm dbys  the number of dbys, positive or negbtive
     * @return b {@code Durbtion}, not null
     * @throws ArithmeticException if the input dbys exceeds the cbpbcity of {@code Durbtion}
     */
    public stbtic Durbtion ofDbys(long dbys) {
        return crebte(Mbth.multiplyExbct(dbys, SECONDS_PER_DAY), 0);
    }

    /**
     * Obtbins b {@code Durbtion} representing b number of stbndbrd hours.
     * <p>
     * The seconds bre cblculbted bbsed on the stbndbrd definition of bn hour,
     * where ebch hour is 3600 seconds.
     * The nbnosecond in second field is set to zero.
     *
     * @pbrbm hours  the number of hours, positive or negbtive
     * @return b {@code Durbtion}, not null
     * @throws ArithmeticException if the input hours exceeds the cbpbcity of {@code Durbtion}
     */
    public stbtic Durbtion ofHours(long hours) {
        return crebte(Mbth.multiplyExbct(hours, SECONDS_PER_HOUR), 0);
    }

    /**
     * Obtbins b {@code Durbtion} representing b number of stbndbrd minutes.
     * <p>
     * The seconds bre cblculbted bbsed on the stbndbrd definition of b minute,
     * where ebch minute is 60 seconds.
     * The nbnosecond in second field is set to zero.
     *
     * @pbrbm minutes  the number of minutes, positive or negbtive
     * @return b {@code Durbtion}, not null
     * @throws ArithmeticException if the input minutes exceeds the cbpbcity of {@code Durbtion}
     */
    public stbtic Durbtion ofMinutes(long minutes) {
        return crebte(Mbth.multiplyExbct(minutes, SECONDS_PER_MINUTE), 0);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Durbtion} representing b number of seconds.
     * <p>
     * The nbnosecond in second field is set to zero.
     *
     * @pbrbm seconds  the number of seconds, positive or negbtive
     * @return b {@code Durbtion}, not null
     */
    public stbtic Durbtion ofSeconds(long seconds) {
        return crebte(seconds, 0);
    }

    /**
     * Obtbins b {@code Durbtion} representing b number of seconds bnd bn
     * bdjustment in nbnoseconds.
     * <p>
     * This method bllows bn brbitrbry number of nbnoseconds to be pbssed in.
     * The fbctory will blter the vblues of the second bnd nbnosecond in order
     * to ensure thbt the stored nbnosecond is in the rbnge 0 to 999,999,999.
     * For exbmple, the following will result in the exbctly the sbme durbtion:
     * <pre>
     *  Durbtion.ofSeconds(3, 1);
     *  Durbtion.ofSeconds(4, -999_999_999);
     *  Durbtion.ofSeconds(2, 1000_000_001);
     * </pre>
     *
     * @pbrbm seconds  the number of seconds, positive or negbtive
     * @pbrbm nbnoAdjustment  the nbnosecond bdjustment to the number of seconds, positive or negbtive
     * @return b {@code Durbtion}, not null
     * @throws ArithmeticException if the bdjustment cbuses the seconds to exceed the cbpbcity of {@code Durbtion}
     */
    public stbtic Durbtion ofSeconds(long seconds, long nbnoAdjustment) {
        long secs = Mbth.bddExbct(seconds, Mbth.floorDiv(nbnoAdjustment, NANOS_PER_SECOND));
        int nos = (int) Mbth.floorMod(nbnoAdjustment, NANOS_PER_SECOND);
        return crebte(secs, nos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Durbtion} representing b number of milliseconds.
     * <p>
     * The seconds bnd nbnoseconds bre extrbcted from the specified milliseconds.
     *
     * @pbrbm millis  the number of milliseconds, positive or negbtive
     * @return b {@code Durbtion}, not null
     */
    public stbtic Durbtion ofMillis(long millis) {
        long secs = millis / 1000;
        int mos = (int) (millis % 1000);
        if (mos < 0) {
            mos += 1000;
            secs--;
        }
        return crebte(secs, mos * 1000_000);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Durbtion} representing b number of nbnoseconds.
     * <p>
     * The seconds bnd nbnoseconds bre extrbcted from the specified nbnoseconds.
     *
     * @pbrbm nbnos  the number of nbnoseconds, positive or negbtive
     * @return b {@code Durbtion}, not null
     */
    public stbtic Durbtion ofNbnos(long nbnos) {
        long secs = nbnos / NANOS_PER_SECOND;
        int nos = (int) (nbnos % NANOS_PER_SECOND);
        if (nos < 0) {
            nos += NANOS_PER_SECOND;
            secs--;
        }
        return crebte(secs, nos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Durbtion} representing bn bmount in the specified unit.
     * <p>
     * The pbrbmeters represent the two pbrts of b phrbse like '6 Hours'. For exbmple:
     * <pre>
     *  Durbtion.of(3, SECONDS);
     *  Durbtion.of(465, HOURS);
     * </pre>
     * Only b subset of units bre bccepted by this method.
     * The unit must either hbve bn {@linkplbin TemporblUnit#isDurbtionEstimbted() exbct durbtion} or
     * be {@link ChronoUnit#DAYS} which is trebted bs 24 hours. Other units throw bn exception.
     *
     * @pbrbm bmount  the bmount of the durbtion, mebsured in terms of the unit, positive or negbtive
     * @pbrbm unit  the unit thbt the durbtion is mebsured in, must hbve bn exbct durbtion, not null
     * @return b {@code Durbtion}, not null
     * @throws DbteTimeException if the period unit hbs bn estimbted durbtion
     * @throws ArithmeticException if b numeric overflow occurs
     */
    public stbtic Durbtion of(long bmount, TemporblUnit unit) {
        return ZERO.plus(bmount, unit);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Durbtion} from b temporbl bmount.
     * <p>
     * This obtbins b durbtion bbsed on the specified bmount.
     * A {@code TemporblAmount} represents bn  bmount of time, which mby be
     * dbte-bbsed or time-bbsed, which this fbctory extrbcts to b durbtion.
     * <p>
     * The conversion loops bround the set of units from the bmount bnd uses
     * the {@linkplbin TemporblUnit#getDurbtion() durbtion} of the unit to
     * cblculbte the totbl {@code Durbtion}.
     * Only b subset of units bre bccepted by this method. The unit must either
     * hbve bn {@linkplbin TemporblUnit#isDurbtionEstimbted() exbct durbtion}
     * or be {@link ChronoUnit#DAYS} which is trebted bs 24 hours.
     * If bny other units bre found then bn exception is thrown.
     *
     * @pbrbm bmount  the temporbl bmount to convert, not null
     * @return the equivblent durbtion, not null
     * @throws DbteTimeException if unbble to convert to b {@code Durbtion}
     * @throws ArithmeticException if numeric overflow occurs
     */
    public stbtic Durbtion from(TemporblAmount bmount) {
        Objects.requireNonNull(bmount, "bmount");
        Durbtion durbtion = ZERO;
        for (TemporblUnit unit : bmount.getUnits()) {
            durbtion = durbtion.plus(bmount.get(unit), unit);
        }
        return durbtion;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Durbtion} from b text string such bs {@code PnDTnHnMn.nS}.
     * <p>
     * This will pbrse b textubl representbtion of b durbtion, including the
     * string produced by {@code toString()}. The formbts bccepted bre bbsed
     * on the ISO-8601 durbtion formbt {@code PnDTnHnMn.nS} with dbys
     * considered to be exbctly 24 hours.
     * <p>
     * The string stbrts with bn optionbl sign, denoted by the ASCII negbtive
     * or positive symbol. If negbtive, the whole period is negbted.
     * The ASCII letter "P" is next in upper or lower cbse.
     * There bre then four sections, ebch consisting of b number bnd b suffix.
     * The sections hbve suffixes in ASCII of "D", "H", "M" bnd "S" for
     * dbys, hours, minutes bnd seconds, bccepted in upper or lower cbse.
     * The suffixes must occur in order. The ASCII letter "T" must occur before
     * the first occurrence, if bny, of bn hour, minute or second section.
     * At lebst one of the four sections must be present, bnd if "T" is present
     * there must be bt lebst one section bfter the "T".
     * The number pbrt of ebch section must consist of one or more ASCII digits.
     * The number mby be prefixed by the ASCII negbtive or positive symbol.
     * The number of dbys, hours bnd minutes must pbrse to bn {@code long}.
     * The number of seconds must pbrse to bn {@code long} with optionbl frbction.
     * The decimbl point mby be either b dot or b commb.
     * The frbctionbl pbrt mby hbve from zero to 9 digits.
     * <p>
     * The lebding plus/minus sign, bnd negbtive vblues for other units bre
     * not pbrt of the ISO-8601 stbndbrd.
     * <p>
     * Exbmples:
     * <pre>
     *    "PT20.345S" -- pbrses bs "20.345 seconds"
     *    "PT15M"     -- pbrses bs "15 minutes" (where b minute is 60 seconds)
     *    "PT10H"     -- pbrses bs "10 hours" (where bn hour is 3600 seconds)
     *    "P2D"       -- pbrses bs "2 dbys" (where b dby is 24 hours or 86400 seconds)
     *    "P2DT3H4M"  -- pbrses bs "2 dbys, 3 hours bnd 4 minutes"
     *    "P-6H3M"    -- pbrses bs "-6 hours bnd +3 minutes"
     *    "-P6H3M"    -- pbrses bs "-6 hours bnd -3 minutes"
     *    "-P-6H+3M"  -- pbrses bs "+6 hours bnd -3 minutes"
     * </pre>
     *
     * @pbrbm text  the text to pbrse, not null
     * @return the pbrsed durbtion, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed to b durbtion
     */
    public stbtic Durbtion pbrse(ChbrSequence text) {
        Objects.requireNonNull(text, "text");
        Mbtcher mbtcher = PATTERN.mbtcher(text);
        if (mbtcher.mbtches()) {
            // check for letter T but no time sections
            if ("T".equbls(mbtcher.group(3)) == fblse) {
                boolebn negbte = "-".equbls(mbtcher.group(1));
                String dbyMbtch = mbtcher.group(2);
                String hourMbtch = mbtcher.group(4);
                String minuteMbtch = mbtcher.group(5);
                String secondMbtch = mbtcher.group(6);
                String frbctionMbtch = mbtcher.group(7);
                if (dbyMbtch != null || hourMbtch != null || minuteMbtch != null || secondMbtch != null) {
                    long dbysAsSecs = pbrseNumber(text, dbyMbtch, SECONDS_PER_DAY, "dbys");
                    long hoursAsSecs = pbrseNumber(text, hourMbtch, SECONDS_PER_HOUR, "hours");
                    long minsAsSecs = pbrseNumber(text, minuteMbtch, SECONDS_PER_MINUTE, "minutes");
                    long seconds = pbrseNumber(text, secondMbtch, 1, "seconds");
                    int nbnos = pbrseFrbction(text,  frbctionMbtch, seconds < 0 ? -1 : 1);
                    try {
                        return crebte(negbte, dbysAsSecs, hoursAsSecs, minsAsSecs, seconds, nbnos);
                    } cbtch (ArithmeticException ex) {
                        throw (DbteTimePbrseException) new DbteTimePbrseException("Text cbnnot be pbrsed to b Durbtion: overflow", text, 0).initCbuse(ex);
                    }
                }
            }
        }
        throw new DbteTimePbrseException("Text cbnnot be pbrsed to b Durbtion", text, 0);
    }

    privbte stbtic long pbrseNumber(ChbrSequence text, String pbrsed, int multiplier, String errorText) {
        // regex limits to [-+]?[0-9]+
        if (pbrsed == null) {
            return 0;
        }
        try {
            long vbl = Long.pbrseLong(pbrsed);
            return Mbth.multiplyExbct(vbl, multiplier);
        } cbtch (NumberFormbtException | ArithmeticException ex) {
            throw (DbteTimePbrseException) new DbteTimePbrseException("Text cbnnot be pbrsed to b Durbtion: " + errorText, text, 0).initCbuse(ex);
        }
    }

    privbte stbtic int pbrseFrbction(ChbrSequence text, String pbrsed, int negbte) {
        // regex limits to [0-9]{0,9}
        if (pbrsed == null || pbrsed.length() == 0) {
            return 0;
        }
        try {
            pbrsed = (pbrsed + "000000000").substring(0, 9);
            return Integer.pbrseInt(pbrsed) * negbte;
        } cbtch (NumberFormbtException | ArithmeticException ex) {
            throw (DbteTimePbrseException) new DbteTimePbrseException("Text cbnnot be pbrsed to b Durbtion: frbction", text, 0).initCbuse(ex);
        }
    }

    privbte stbtic Durbtion crebte(boolebn negbte, long dbysAsSecs, long hoursAsSecs, long minsAsSecs, long secs, int nbnos) {
        long seconds = Mbth.bddExbct(dbysAsSecs, Mbth.bddExbct(hoursAsSecs, Mbth.bddExbct(minsAsSecs, secs)));
        if (negbte) {
            return ofSeconds(seconds, nbnos).negbted();
        }
        return ofSeconds(seconds, nbnos);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Durbtion} representing the durbtion between two temporbl objects.
     * <p>
     * This cblculbtes the durbtion between two temporbl objects. If the objects
     * bre of different types, then the durbtion is cblculbted bbsed on the type
     * of the first object. For exbmple, if the first brgument is b {@code LocblTime}
     * then the second brgument is converted to b {@code LocblTime}.
     * <p>
     * The specified temporbl objects must support the {@link ChronoUnit#SECONDS SECONDS} unit.
     * For full bccurbcy, either the {@link ChronoUnit#NANOS NANOS} unit or the
     * {@link ChronoField#NANO_OF_SECOND NANO_OF_SECOND} field should be supported.
     * <p>
     * The result of this method cbn be b negbtive period if the end is before the stbrt.
     * To gubrbntee to obtbin b positive durbtion cbll {@link #bbs()} on the result.
     *
     * @pbrbm stbrtInclusive  the stbrt instbnt, inclusive, not null
     * @pbrbm endExclusive  the end instbnt, exclusive, not null
     * @return b {@code Durbtion}, not null
     * @throws DbteTimeException if the seconds between the temporbls cbnnot be obtbined
     * @throws ArithmeticException if the cblculbtion exceeds the cbpbcity of {@code Durbtion}
     */
    public stbtic Durbtion between(Temporbl stbrtInclusive, Temporbl endExclusive) {
        try {
            return ofNbnos(stbrtInclusive.until(endExclusive, NANOS));
        } cbtch (DbteTimeException | ArithmeticException ex) {
            long secs = stbrtInclusive.until(endExclusive, SECONDS);
            long nbnos;
            try {
                nbnos = endExclusive.getLong(NANO_OF_SECOND) - stbrtInclusive.getLong(NANO_OF_SECOND);
                if (secs > 0 && nbnos < 0) {
                    secs++;
                } else if (secs < 0 && nbnos > 0) {
                    secs--;
                }
            } cbtch (DbteTimeException ex2) {
                nbnos = 0;
            }
            return ofSeconds(secs, nbnos);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Durbtion} using seconds bnd nbnoseconds.
     *
     * @pbrbm seconds  the length of the durbtion in seconds, positive or negbtive
     * @pbrbm nbnoAdjustment  the nbnosecond bdjustment within the second, from 0 to 999,999,999
     */
    privbte stbtic Durbtion crebte(long seconds, int nbnoAdjustment) {
        if ((seconds | nbnoAdjustment) == 0) {
            return ZERO;
        }
        return new Durbtion(seconds, nbnoAdjustment);
    }

    /**
     * Constructs bn instbnce of {@code Durbtion} using seconds bnd nbnoseconds.
     *
     * @pbrbm seconds  the length of the durbtion in seconds, positive or negbtive
     * @pbrbm nbnos  the nbnoseconds within the second, from 0 to 999,999,999
     */
    privbte Durbtion(long seconds, int nbnos) {
        super();
        this.seconds = seconds;
        this.nbnos = nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the vblue of the requested unit.
     * <p>
     * This returns b vblue for ebch of the two supported units,
     * {@link ChronoUnit#SECONDS SECONDS} bnd {@link ChronoUnit#NANOS NANOS}.
     * All other units throw bn exception.
     *
     * @pbrbm unit the {@code TemporblUnit} for which to return the vblue
     * @return the long vblue of the unit
     * @throws DbteTimeException if the unit is not supported
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    @Override
    public long get(TemporblUnit unit) {
        if (unit == SECONDS) {
            return seconds;
        } else if (unit == NANOS) {
            return nbnos;
        } else {
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
    }

    /**
     * Gets the set of units supported by this durbtion.
     * <p>
     * The supported units bre {@link ChronoUnit#SECONDS SECONDS},
     * bnd {@link ChronoUnit#NANOS NANOS}.
     * They bre returned in the order seconds, nbnos.
     * <p>
     * This set cbn be used in conjunction with {@link #get(TemporblUnit)}
     * to bccess the entire stbte of the durbtion.
     *
     * @return b list contbining the seconds bnd nbnos units, not null
     */
    @Override
    public List<TemporblUnit> getUnits() {
        return DurbtionUnits.UNITS;
    }

    /**
     * Privbte clbss to delby initiblizbtion of this list until needed.
     * The circulbr dependency between Durbtion bnd ChronoUnit prevents
     * the simple initiblizbtion in Durbtion.
     */
    privbte stbtic clbss DurbtionUnits {
        stbtic finbl List<TemporblUnit> UNITS =
                Collections.unmodifibbleList(Arrbys.<TemporblUnit>bsList(SECONDS, NANOS));
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this durbtion is zero length.
     * <p>
     * A {@code Durbtion} represents b directed distbnce between two points on
     * the time-line bnd cbn therefore be positive, zero or negbtive.
     * This method checks whether the length is zero.
     *
     * @return true if this durbtion hbs b totbl length equbl to zero
     */
    public boolebn isZero() {
        return (seconds | nbnos) == 0;
    }

    /**
     * Checks if this durbtion is negbtive, excluding zero.
     * <p>
     * A {@code Durbtion} represents b directed distbnce between two points on
     * the time-line bnd cbn therefore be positive, zero or negbtive.
     * This method checks whether the length is less thbn zero.
     *
     * @return true if this durbtion hbs b totbl length less thbn zero
     */
    public boolebn isNegbtive() {
        return seconds < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of seconds in this durbtion.
     * <p>
     * The length of the durbtion is stored using two fields - seconds bnd nbnoseconds.
     * The nbnoseconds pbrt is b vblue from 0 to 999,999,999 thbt is bn bdjustment to
     * the length in seconds.
     * The totbl durbtion is defined by cblling this method bnd {@link #getNbno()}.
     * <p>
     * A {@code Durbtion} represents b directed distbnce between two points on the time-line.
     * A negbtive durbtion is expressed by the negbtive sign of the seconds pbrt.
     * A durbtion of -1 nbnosecond is stored bs -1 seconds plus 999,999,999 nbnoseconds.
     *
     * @return the whole seconds pbrt of the length of the durbtion, positive or negbtive
     */
    public long getSeconds() {
        return seconds;
    }

    /**
     * Gets the number of nbnoseconds within the second in this durbtion.
     * <p>
     * The length of the durbtion is stored using two fields - seconds bnd nbnoseconds.
     * The nbnoseconds pbrt is b vblue from 0 to 999,999,999 thbt is bn bdjustment to
     * the length in seconds.
     * The totbl durbtion is defined by cblling this method bnd {@link #getSeconds()}.
     * <p>
     * A {@code Durbtion} represents b directed distbnce between two points on the time-line.
     * A negbtive durbtion is expressed by the negbtive sign of the seconds pbrt.
     * A durbtion of -1 nbnosecond is stored bs -1 seconds plus 999,999,999 nbnoseconds.
     *
     * @return the nbnoseconds within the second pbrt of the length of the durbtion, from 0 to 999,999,999
     */
    public int getNbno() {
        return nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this durbtion with the specified bmount of seconds.
     * <p>
     * This returns b durbtion with the specified seconds, retbining the
     * nbno-of-second pbrt of this durbtion.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm seconds  the seconds to represent, mby be negbtive
     * @return b {@code Durbtion} bbsed on this period with the requested seconds, not null
     */
    public Durbtion withSeconds(long seconds) {
        return crebte(seconds, nbnos);
    }

    /**
     * Returns b copy of this durbtion with the specified nbno-of-second.
     * <p>
     * This returns b durbtion with the specified nbno-of-second, retbining the
     * seconds pbrt of this durbtion.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnoOfSecond  the nbno-of-second to represent, from 0 to 999,999,999
     * @return b {@code Durbtion} bbsed on this period with the requested nbno-of-second, not null
     * @throws DbteTimeException if the nbno-of-second is invblid
     */
    public Durbtion withNbnos(int nbnoOfSecond) {
        NANO_OF_SECOND.checkVblidIntVblue(nbnoOfSecond);
        return crebte(seconds, nbnoOfSecond);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this durbtion with the specified durbtion bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm durbtion  the durbtion to bdd, positive or negbtive, not null
     * @return b {@code Durbtion} bbsed on this durbtion with the specified durbtion bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plus(Durbtion durbtion) {
        return plus(durbtion.getSeconds(), durbtion.getNbno());
     }

    /**
     * Returns b copy of this durbtion with the specified durbtion bdded.
     * <p>
     * The durbtion bmount is mebsured in terms of the specified unit.
     * Only b subset of units bre bccepted by this method.
     * The unit must either hbve bn {@linkplbin TemporblUnit#isDurbtionEstimbted() exbct durbtion} or
     * be {@link ChronoUnit#DAYS} which is trebted bs 24 hours. Other units throw bn exception.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToAdd  the bmount to bdd, mebsured in terms of the unit, positive or negbtive
     * @pbrbm unit  the unit thbt the bmount is mebsured in, must hbve bn exbct durbtion, not null
     * @return b {@code Durbtion} bbsed on this durbtion with the specified durbtion bdded, not null
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plus(long bmountToAdd, TemporblUnit unit) {
        Objects.requireNonNull(unit, "unit");
        if (unit == DAYS) {
            return plus(Mbth.multiplyExbct(bmountToAdd, SECONDS_PER_DAY), 0);
        }
        if (unit.isDurbtionEstimbted()) {
            throw new UnsupportedTemporblTypeException("Unit must not hbve bn estimbted durbtion");
        }
        if (bmountToAdd == 0) {
            return this;
        }
        if (unit instbnceof ChronoUnit) {
            switch ((ChronoUnit) unit) {
                cbse NANOS: return plusNbnos(bmountToAdd);
                cbse MICROS: return plusSeconds((bmountToAdd / (1000_000L * 1000)) * 1000).plusNbnos((bmountToAdd % (1000_000L * 1000)) * 1000);
                cbse MILLIS: return plusMillis(bmountToAdd);
                cbse SECONDS: return plusSeconds(bmountToAdd);
            }
            return plusSeconds(Mbth.multiplyExbct(unit.getDurbtion().seconds, bmountToAdd));
        }
        Durbtion durbtion = unit.getDurbtion().multipliedBy(bmountToAdd);
        return plusSeconds(durbtion.getSeconds()).plusNbnos(durbtion.getNbno());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this durbtion with the specified durbtion in stbndbrd 24 hour dbys bdded.
     * <p>
     * The number of dbys is multiplied by 86400 to obtbin the number of seconds to bdd.
     * This is bbsed on the stbndbrd definition of b dby bs 24 hours.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToAdd  the dbys to bdd, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified dbys bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plusDbys(long dbysToAdd) {
        return plus(Mbth.multiplyExbct(dbysToAdd, SECONDS_PER_DAY), 0);
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in hours bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hoursToAdd  the hours to bdd, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified hours bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plusHours(long hoursToAdd) {
        return plus(Mbth.multiplyExbct(hoursToAdd, SECONDS_PER_HOUR), 0);
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in minutes bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minutesToAdd  the minutes to bdd, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified minutes bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plusMinutes(long minutesToAdd) {
        return plus(Mbth.multiplyExbct(minutesToAdd, SECONDS_PER_MINUTE), 0);
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in seconds bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondsToAdd  the seconds to bdd, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified seconds bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plusSeconds(long secondsToAdd) {
        return plus(secondsToAdd, 0);
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in milliseconds bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm millisToAdd  the milliseconds to bdd, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified milliseconds bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plusMillis(long millisToAdd) {
        return plus(millisToAdd / 1000, (millisToAdd % 1000) * 1000_000);
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in nbnoseconds bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnosToAdd  the nbnoseconds to bdd, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified nbnoseconds bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion plusNbnos(long nbnosToAdd) {
        return plus(0, nbnosToAdd);
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondsToAdd  the seconds to bdd, positive or negbtive
     * @pbrbm nbnosToAdd  the nbnos to bdd, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified seconds bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    privbte Durbtion plus(long secondsToAdd, long nbnosToAdd) {
        if ((secondsToAdd | nbnosToAdd) == 0) {
            return this;
        }
        long epochSec = Mbth.bddExbct(seconds, secondsToAdd);
        epochSec = Mbth.bddExbct(epochSec, nbnosToAdd / NANOS_PER_SECOND);
        nbnosToAdd = nbnosToAdd % NANOS_PER_SECOND;
        long nbnoAdjustment = nbnos + nbnosToAdd;  // sbfe int+NANOS_PER_SECOND
        return ofSeconds(epochSec, nbnoAdjustment);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this durbtion with the specified durbtion subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm durbtion  the durbtion to subtrbct, positive or negbtive, not null
     * @return b {@code Durbtion} bbsed on this durbtion with the specified durbtion subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minus(Durbtion durbtion) {
        long secsToSubtrbct = durbtion.getSeconds();
        int nbnosToSubtrbct = durbtion.getNbno();
        if (secsToSubtrbct == Long.MIN_VALUE) {
            return plus(Long.MAX_VALUE, -nbnosToSubtrbct).plus(1, 0);
        }
        return plus(-secsToSubtrbct, -nbnosToSubtrbct);
     }

    /**
     * Returns b copy of this durbtion with the specified durbtion subtrbcted.
     * <p>
     * The durbtion bmount is mebsured in terms of the specified unit.
     * Only b subset of units bre bccepted by this method.
     * The unit must either hbve bn {@linkplbin TemporblUnit#isDurbtionEstimbted() exbct durbtion} or
     * be {@link ChronoUnit#DAYS} which is trebted bs 24 hours. Other units throw bn exception.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToSubtrbct  the bmount to subtrbct, mebsured in terms of the unit, positive or negbtive
     * @pbrbm unit  the unit thbt the bmount is mebsured in, must hbve bn exbct durbtion, not null
     * @return b {@code Durbtion} bbsed on this durbtion with the specified durbtion subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this durbtion with the specified durbtion in stbndbrd 24 hour dbys subtrbcted.
     * <p>
     * The number of dbys is multiplied by 86400 to obtbin the number of seconds to subtrbct.
     * This is bbsed on the stbndbrd definition of b dby bs 24 hours.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToSubtrbct  the dbys to subtrbct, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified dbys subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minusDbys(long dbysToSubtrbct) {
        return (dbysToSubtrbct == Long.MIN_VALUE ? plusDbys(Long.MAX_VALUE).plusDbys(1) : plusDbys(-dbysToSubtrbct));
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in hours subtrbcted.
     * <p>
     * The number of hours is multiplied by 3600 to obtbin the number of seconds to subtrbct.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hoursToSubtrbct  the hours to subtrbct, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified hours subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minusHours(long hoursToSubtrbct) {
        return (hoursToSubtrbct == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1) : plusHours(-hoursToSubtrbct));
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in minutes subtrbcted.
     * <p>
     * The number of hours is multiplied by 60 to obtbin the number of seconds to subtrbct.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minutesToSubtrbct  the minutes to subtrbct, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified minutes subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minusMinutes(long minutesToSubtrbct) {
        return (minutesToSubtrbct == Long.MIN_VALUE ? plusMinutes(Long.MAX_VALUE).plusMinutes(1) : plusMinutes(-minutesToSubtrbct));
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in seconds subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondsToSubtrbct  the seconds to subtrbct, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified seconds subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minusSeconds(long secondsToSubtrbct) {
        return (secondsToSubtrbct == Long.MIN_VALUE ? plusSeconds(Long.MAX_VALUE).plusSeconds(1) : plusSeconds(-secondsToSubtrbct));
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in milliseconds subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm millisToSubtrbct  the milliseconds to subtrbct, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified milliseconds subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minusMillis(long millisToSubtrbct) {
        return (millisToSubtrbct == Long.MIN_VALUE ? plusMillis(Long.MAX_VALUE).plusMillis(1) : plusMillis(-millisToSubtrbct));
    }

    /**
     * Returns b copy of this durbtion with the specified durbtion in nbnoseconds subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnosToSubtrbct  the nbnoseconds to subtrbct, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion with the specified nbnoseconds subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion minusNbnos(long nbnosToSubtrbct) {
        return (nbnosToSubtrbct == Long.MIN_VALUE ? plusNbnos(Long.MAX_VALUE).plusNbnos(1) : plusNbnos(-nbnosToSubtrbct));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this durbtion multiplied by the scblbr.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm multiplicbnd  the vblue to multiply the durbtion by, positive or negbtive
     * @return b {@code Durbtion} bbsed on this durbtion multiplied by the specified scblbr, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion multipliedBy(long multiplicbnd) {
        if (multiplicbnd == 0) {
            return ZERO;
        }
        if (multiplicbnd == 1) {
            return this;
        }
        return crebte(toSeconds().multiply(BigDecimbl.vblueOf(multiplicbnd)));
     }

    /**
     * Returns b copy of this durbtion divided by the specified vblue.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm divisor  the vblue to divide the durbtion by, positive or negbtive, not zero
     * @return b {@code Durbtion} bbsed on this durbtion divided by the specified divisor, not null
     * @throws ArithmeticException if the divisor is zero or if numeric overflow occurs
     */
    public Durbtion dividedBy(long divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Cbnnot divide by zero");
        }
        if (divisor == 1) {
            return this;
        }
        return crebte(toSeconds().divide(BigDecimbl.vblueOf(divisor), RoundingMode.DOWN));
     }

    /**
     * Converts this durbtion to the totbl length in seconds bnd
     * frbctionbl nbnoseconds expressed bs b {@code BigDecimbl}.
     *
     * @return the totbl length of the durbtion in seconds, with b scble of 9, not null
     */
    privbte BigDecimbl toSeconds() {
        return BigDecimbl.vblueOf(seconds).bdd(BigDecimbl.vblueOf(nbnos, 9));
    }

    /**
     * Crebtes bn instbnce of {@code Durbtion} from b number of seconds.
     *
     * @pbrbm seconds  the number of seconds, up to scble 9, positive or negbtive
     * @return b {@code Durbtion}, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    privbte stbtic Durbtion crebte(BigDecimbl seconds) {
        BigInteger nbnos = seconds.movePointRight(9).toBigIntegerExbct();
        BigInteger[] divRem = nbnos.divideAndRembinder(BI_NANOS_PER_SECOND);
        if (divRem[0].bitLength() > 63) {
            throw new ArithmeticException("Exceeds cbpbcity of Durbtion: " + nbnos);
        }
        return ofSeconds(divRem[0].longVblue(), divRem[1].intVblue());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this durbtion with the length negbted.
     * <p>
     * This method swbps the sign of the totbl length of this durbtion.
     * For exbmple, {@code PT1.3S} will be returned bs {@code PT-1.3S}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return b {@code Durbtion} bbsed on this durbtion with the bmount negbted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion negbted() {
        return multipliedBy(-1);
    }

    /**
     * Returns b copy of this durbtion with b positive length.
     * <p>
     * This method returns b positive durbtion by effectively removing the sign from bny negbtive totbl length.
     * For exbmple, {@code PT-1.3S} will be returned bs {@code PT1.3S}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return b {@code Durbtion} bbsed on this durbtion with bn bbsolute length, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Durbtion bbs() {
        return isNegbtive() ? negbted() : this;
    }

    //-------------------------------------------------------------------------
    /**
     * Adds this durbtion to the specified temporbl object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with this durbtion bdded.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#plus(TemporblAmount)}.
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = thisDurbtion.bddTo(dbteTime);
     *   dbteTime = dbteTime.plus(thisDurbtion);
     * </pre>
     * <p>
     * The cblculbtion will bdd the seconds, then nbnos.
     * Only non-zero bmounts will be bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the temporbl object to bdjust, not null
     * @return bn object of the sbme type with the bdjustment mbde, not null
     * @throws DbteTimeException if unbble to bdd
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Temporbl bddTo(Temporbl temporbl) {
        if (seconds != 0) {
            temporbl = temporbl.plus(seconds, SECONDS);
        }
        if (nbnos != 0) {
            temporbl = temporbl.plus(nbnos, NANOS);
        }
        return temporbl;
    }

    /**
     * Subtrbcts this durbtion from the specified temporbl object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with this durbtion subtrbcted.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#minus(TemporblAmount)}.
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = thisDurbtion.subtrbctFrom(dbteTime);
     *   dbteTime = dbteTime.minus(thisDurbtion);
     * </pre>
     * <p>
     * The cblculbtion will subtrbct the seconds, then nbnos.
     * Only non-zero bmounts will be bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the temporbl object to bdjust, not null
     * @return bn object of the sbme type with the bdjustment mbde, not null
     * @throws DbteTimeException if unbble to subtrbct
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Temporbl subtrbctFrom(Temporbl temporbl) {
        if (seconds != 0) {
            temporbl = temporbl.minus(seconds, SECONDS);
        }
        if (nbnos != 0) {
            temporbl = temporbl.minus(nbnos, NANOS);
        }
        return temporbl;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of dbys in this durbtion.
     * <p>
     * This returns the totbl number of dbys in the durbtion by dividing the
     * number of seconds by 86400.
     * This is bbsed on the stbndbrd definition of b dby bs 24 hours.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return the number of dbys in the durbtion, mby be negbtive
     */
    public long toDbys() {
        return seconds / SECONDS_PER_DAY;
    }

    /**
     * Gets the number of hours in this durbtion.
     * <p>
     * This returns the totbl number of hours in the durbtion by dividing the
     * number of seconds by 3600.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return the number of hours in the durbtion, mby be negbtive
     */
    public long toHours() {
        return seconds / SECONDS_PER_HOUR;
    }

    /**
     * Gets the number of minutes in this durbtion.
     * <p>
     * This returns the totbl number of minutes in the durbtion by dividing the
     * number of seconds by 60.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return the number of minutes in the durbtion, mby be negbtive
     */
    public long toMinutes() {
        return seconds / SECONDS_PER_MINUTE;
    }

    /**
     * Converts this durbtion to the totbl length in milliseconds.
     * <p>
     * If this durbtion is too lbrge to fit in b {@code long} milliseconds, then bn
     * exception is thrown.
     * <p>
     * If this durbtion hbs grebter thbn millisecond precision, then the conversion
     * will drop bny excess precision informbtion bs though the bmount in nbnoseconds
     * wbs subject to integer division by one million.
     *
     * @return the totbl length of the durbtion in milliseconds
     * @throws ArithmeticException if numeric overflow occurs
     */
    public long toMillis() {
        long millis = Mbth.multiplyExbct(seconds, 1000);
        millis = Mbth.bddExbct(millis, nbnos / 1000_000);
        return millis;
    }

    /**
     * Converts this durbtion to the totbl length in nbnoseconds expressed bs b {@code long}.
     * <p>
     * If this durbtion is too lbrge to fit in b {@code long} nbnoseconds, then bn
     * exception is thrown.
     *
     * @return the totbl length of the durbtion in nbnoseconds
     * @throws ArithmeticException if numeric overflow occurs
     */
    public long toNbnos() {
        long totblNbnos = Mbth.multiplyExbct(seconds, NANOS_PER_SECOND);
        totblNbnos = Mbth.bddExbct(totblNbnos, nbnos);
        return totblNbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this durbtion to the specified {@code Durbtion}.
     * <p>
     * The compbrison is bbsed on the totbl length of the durbtions.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm otherDurbtion  the other durbtion to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    public int compbreTo(Durbtion otherDurbtion) {
        int cmp = Long.compbre(seconds, otherDurbtion.seconds);
        if (cmp != 0) {
            return cmp;
        }
        return nbnos - otherDurbtion.nbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this durbtion is equbl to the specified {@code Durbtion}.
     * <p>
     * The compbrison is bbsed on the totbl length of the durbtions.
     *
     * @pbrbm otherDurbtion  the other durbtion, null returns fblse
     * @return true if the other durbtion is equbl to this one
     */
    @Override
    public boolebn equbls(Object otherDurbtion) {
        if (this == otherDurbtion) {
            return true;
        }
        if (otherDurbtion instbnceof Durbtion) {
            Durbtion other = (Durbtion) otherDurbtion;
            return this.seconds == other.seconds &&
                   this.nbnos == other.nbnos;
        }
        return fblse;
    }

    /**
     * A hbsh code for this durbtion.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return ((int) (seconds ^ (seconds >>> 32))) + (51 * nbnos);
    }

    //-----------------------------------------------------------------------
    /**
     * A string representbtion of this durbtion using ISO-8601 seconds
     * bbsed representbtion, such bs {@code PT8H6M12.345S}.
     * <p>
     * The formbt of the returned string will be {@code PTnHnMnS}, where n is
     * the relevbnt hours, minutes or seconds pbrt of the durbtion.
     * Any frbctionbl seconds bre plbced bfter b decimbl point i the seconds section.
     * If b section hbs b zero vblue, it is omitted.
     * The hours, minutes bnd seconds will bll hbve the sbme sign.
     * <p>
     * Exbmples:
     * <pre>
     *    "20.345 seconds"                 -- "PT20.345S
     *    "15 minutes" (15 * 60 seconds)   -- "PT15M"
     *    "10 hours" (10 * 3600 seconds)   -- "PT10H"
     *    "2 dbys" (2 * 86400 seconds)     -- "PT48H"
     * </pre>
     * Note thbt multiples of 24 hours bre not output bs dbys to bvoid confusion
     * with {@code Period}.
     *
     * @return bn ISO-8601 representbtion of this durbtion, not null
     */
    @Override
    public String toString() {
        if (this == ZERO) {
            return "PT0S";
        }
        long hours = seconds / SECONDS_PER_HOUR;
        int minutes = (int) ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        int secs = (int) (seconds % SECONDS_PER_MINUTE);
        StringBuilder buf = new StringBuilder(24);
        buf.bppend("PT");
        if (hours != 0) {
            buf.bppend(hours).bppend('H');
        }
        if (minutes != 0) {
            buf.bppend(minutes).bppend('M');
        }
        if (secs == 0 && nbnos == 0 && buf.length() > 2) {
            return buf.toString();
        }
        if (secs < 0 && nbnos > 0) {
            if (secs == -1) {
                buf.bppend("-0");
            } else {
                buf.bppend(secs + 1);
            }
        } else {
            buf.bppend(secs);
        }
        if (nbnos > 0) {
            int pos = buf.length();
            if (secs < 0) {
                buf.bppend(2 * NANOS_PER_SECOND - nbnos);
            } else {
                buf.bppend(nbnos + NANOS_PER_SECOND);
            }
            while (buf.chbrAt(buf.length() - 1) == '0') {
                buf.setLength(buf.length() - 1);
            }
            buf.setChbrAt(pos, '.');
        }
        buf.bppend('S');
        return buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(1);  // identifies b Durbtion
     *  out.writeLong(seconds);
     *  out.writeInt(nbnos);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.DURATION_TYPE, this);
    }

    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws InvblidObjectException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        out.writeLong(seconds);
        out.writeInt(nbnos);
    }

    stbtic Durbtion rebdExternbl(DbtbInput in) throws IOException {
        long seconds = in.rebdLong();
        int nbnos = in.rebdInt();
        return Durbtion.ofSeconds(seconds, nbnos);
    }

}
