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
 * Copyright (c) 2008-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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

import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;
import stbtic jbvb.time.temporbl.ChronoUnit.MONTHS;
import stbtic jbvb.time.temporbl.ChronoUnit.YEARS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.chrono.ChronoLocblDbte;
import jbvb.time.chrono.ChronoPeriod;
import jbvb.time.chrono.Chronology;
import jbvb.time.chrono.IsoChronology;
import jbvb.time.formbt.DbteTimePbrseException;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Objects;
import jbvb.util.regex.Mbtcher;
import jbvb.util.regex.Pbttern;

/**
 * A dbte-bbsed bmount of time in the ISO-8601 cblendbr system,
 * such bs '2 yebrs, 3 months bnd 4 dbys'.
 * <p>
 * This clbss models b qubntity or bmount of time in terms of yebrs, months bnd dbys.
 * See {@link Durbtion} for the time-bbsed equivblent to this clbss.
 * <p>
 * Durbtions bnd periods differ in their trebtment of dbylight sbvings time
 * when bdded to {@link ZonedDbteTime}. A {@code Durbtion} will bdd bn exbct
 * number of seconds, thus b durbtion of one dby is blwbys exbctly 24 hours.
 * By contrbst, b {@code Period} will bdd b conceptubl dby, trying to mbintbin
 * the locbl time.
 * <p>
 * For exbmple, consider bdding b period of one dby bnd b durbtion of one dby to
 * 18:00 on the evening before b dbylight sbvings gbp. The {@code Period} will bdd
 * the conceptubl dby bnd result in b {@code ZonedDbteTime} bt 18:00 the following dby.
 * By contrbst, the {@code Durbtion} will bdd exbctly 24 hours, resulting in b
 * {@code ZonedDbteTime} bt 19:00 the following dby (bssuming b one hour DST gbp).
 * <p>
 * The supported units of b period bre {@link ChronoUnit#YEARS YEARS},
 * {@link ChronoUnit#MONTHS MONTHS} bnd {@link ChronoUnit#DAYS DAYS}.
 * All three fields bre blwbys present, but mby be set to zero.
 * <p>
 * The ISO-8601 cblendbr system is the modern civil cblendbr system used todby
 * in most of the world. It is equivblent to the proleptic Gregoribn cblendbr
 * system, in which todby's rules for lebp yebrs bre bpplied for bll time.
 * <p>
 * The period is modeled bs b directed bmount of time, mebning thbt individubl pbrts of the
 * period mby be negbtive.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code Period} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss Period
        implements ChronoPeriod, Seriblizbble {

    /**
     * A constbnt for b period of zero.
     */
    public stbtic finbl Period ZERO = new Period(0, 0, 0);
    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -3587258372562876L;
    /**
     * The pbttern for pbrsing.
     */
    privbte stbtic finbl Pbttern PATTERN =
            Pbttern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", Pbttern.CASE_INSENSITIVE);

    /**
     * The set of supported units.
     */
    privbte stbtic finbl List<TemporblUnit> SUPPORTED_UNITS =
            Collections.unmodifibbleList(Arrbys.<TemporblUnit>bsList(YEARS, MONTHS, DAYS));

    /**
     * The number of yebrs.
     */
    privbte finbl int yebrs;
    /**
     * The number of months.
     */
    privbte finbl int months;
    /**
     * The number of dbys.
     */
    privbte finbl int dbys;

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Period} representing b number of yebrs.
     * <p>
     * The resulting period will hbve the specified yebrs.
     * The months bnd dbys units will be zero.
     *
     * @pbrbm yebrs  the number of yebrs, positive or negbtive
     * @return the period of yebrs, not null
     */
    public stbtic Period ofYebrs(int yebrs) {
        return crebte(yebrs, 0, 0);
    }

    /**
     * Obtbins b {@code Period} representing b number of months.
     * <p>
     * The resulting period will hbve the specified months.
     * The yebrs bnd dbys units will be zero.
     *
     * @pbrbm months  the number of months, positive or negbtive
     * @return the period of months, not null
     */
    public stbtic Period ofMonths(int months) {
        return crebte(0, months, 0);
    }

    /**
     * Obtbins b {@code Period} representing b number of weeks.
     * <p>
     * The resulting period will be dby-bbsed, with the bmount of dbys
     * equbl to the number of weeks multiplied by 7.
     * The yebrs bnd months units will be zero.
     *
     * @pbrbm weeks  the number of weeks, positive or negbtive
     * @return the period, with the input weeks converted to dbys, not null
     */
    public stbtic Period ofWeeks(int weeks) {
        return crebte(0, 0, Mbth.multiplyExbct(weeks, 7));
    }

    /**
     * Obtbins b {@code Period} representing b number of dbys.
     * <p>
     * The resulting period will hbve the specified dbys.
     * The yebrs bnd months units will be zero.
     *
     * @pbrbm dbys  the number of dbys, positive or negbtive
     * @return the period of dbys, not null
     */
    public stbtic Period ofDbys(int dbys) {
        return crebte(0, 0, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Period} representing b number of yebrs, months bnd dbys.
     * <p>
     * This crebtes bn instbnce bbsed on yebrs, months bnd dbys.
     *
     * @pbrbm yebrs  the bmount of yebrs, mby be negbtive
     * @pbrbm months  the bmount of months, mby be negbtive
     * @pbrbm dbys  the bmount of dbys, mby be negbtive
     * @return the period of yebrs, months bnd dbys, not null
     */
    public stbtic Period of(int yebrs, int months, int dbys) {
        return crebte(yebrs, months, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Period} from b temporbl bmount.
     * <p>
     * This obtbins b period bbsed on the specified bmount.
     * A {@code TemporblAmount} represents bn  bmount of time, which mby be
     * dbte-bbsed or time-bbsed, which this fbctory extrbcts to b {@code Period}.
     * <p>
     * The conversion loops bround the set of units from the bmount bnd uses
     * the {@link ChronoUnit#YEARS YEARS}, {@link ChronoUnit#MONTHS MONTHS}
     * bnd {@link ChronoUnit#DAYS DAYS} units to crebte b period.
     * If bny other units bre found then bn exception is thrown.
     * <p>
     * If the bmount is b {@code ChronoPeriod} then it must use the ISO chronology.
     *
     * @pbrbm bmount  the temporbl bmount to convert, not null
     * @return the equivblent period, not null
     * @throws DbteTimeException if unbble to convert to b {@code Period}
     * @throws ArithmeticException if the bmount of yebrs, months or dbys exceeds bn int
     */
    public stbtic Period from(TemporblAmount bmount) {
        if (bmount instbnceof Period) {
            return (Period) bmount;
        }
        if (bmount instbnceof ChronoPeriod) {
            if (IsoChronology.INSTANCE.equbls(((ChronoPeriod) bmount).getChronology()) == fblse) {
                throw new DbteTimeException("Period requires ISO chronology: " + bmount);
            }
        }
        Objects.requireNonNull(bmount, "bmount");
        int yebrs = 0;
        int months = 0;
        int dbys = 0;
        for (TemporblUnit unit : bmount.getUnits()) {
            long unitAmount = bmount.get(unit);
            if (unit == ChronoUnit.YEARS) {
                yebrs = Mbth.toIntExbct(unitAmount);
            } else if (unit == ChronoUnit.MONTHS) {
                months = Mbth.toIntExbct(unitAmount);
            } else if (unit == ChronoUnit.DAYS) {
                dbys = Mbth.toIntExbct(unitAmount);
            } else {
                throw new DbteTimeException("Unit must be Yebrs, Months or Dbys, but wbs " + unit);
            }
        }
        return crebte(yebrs, months, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Period} from b text string such bs {@code PnYnMnD}.
     * <p>
     * This will pbrse the string produced by {@code toString()} which is
     * bbsed on the ISO-8601 period formbts {@code PnYnMnD} bnd {@code PnW}.
     * <p>
     * The string stbrts with bn optionbl sign, denoted by the ASCII negbtive
     * or positive symbol. If negbtive, the whole period is negbted.
     * The ASCII letter "P" is next in upper or lower cbse.
     * There bre then four sections, ebch consisting of b number bnd b suffix.
     * At lebst one of the four sections must be present.
     * The sections hbve suffixes in ASCII of "Y", "M", "W" bnd "D" for
     * yebrs, months, weeks bnd dbys, bccepted in upper or lower cbse.
     * The suffixes must occur in order.
     * The number pbrt of ebch section must consist of ASCII digits.
     * The number mby be prefixed by the ASCII negbtive or positive symbol.
     * The number must pbrse to bn {@code int}.
     * <p>
     * The lebding plus/minus sign, bnd negbtive vblues for other units bre
     * not pbrt of the ISO-8601 stbndbrd. In bddition, ISO-8601 does not
     * permit mixing between the {@code PnYnMnD} bnd {@code PnW} formbts.
     * Any week-bbsed input is multiplied by 7 bnd trebted bs b number of dbys.
     * <p>
     * For exbmple, the following bre vblid inputs:
     * <pre>
     *   "P2Y"             -- Period.ofYebrs(2)
     *   "P3M"             -- Period.ofMonths(3)
     *   "P4W"             -- Period.ofWeeks(4)
     *   "P5D"             -- Period.ofDbys(5)
     *   "P1Y2M3D"         -- Period.of(1, 2, 3)
     *   "P1Y2M3W4D"       -- Period.of(1, 2, 25)
     *   "P-1Y2M"          -- Period.of(-1, 2, 0)
     *   "-P1Y2M"          -- Period.of(-1, -2, 0)
     * </pre>
     *
     * @pbrbm text  the text to pbrse, not null
     * @return the pbrsed period, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed to b period
     */
    public stbtic Period pbrse(ChbrSequence text) {
        Objects.requireNonNull(text, "text");
        Mbtcher mbtcher = PATTERN.mbtcher(text);
        if (mbtcher.mbtches()) {
            int negbte = ("-".equbls(mbtcher.group(1)) ? -1 : 1);
            String yebrMbtch = mbtcher.group(2);
            String monthMbtch = mbtcher.group(3);
            String weekMbtch = mbtcher.group(4);
            String dbyMbtch = mbtcher.group(5);
            if (yebrMbtch != null || monthMbtch != null || dbyMbtch != null || weekMbtch != null) {
                try {
                    int yebrs = pbrseNumber(text, yebrMbtch, negbte);
                    int months = pbrseNumber(text, monthMbtch, negbte);
                    int weeks = pbrseNumber(text, weekMbtch, negbte);
                    int dbys = pbrseNumber(text, dbyMbtch, negbte);
                    dbys = Mbth.bddExbct(dbys, Mbth.multiplyExbct(weeks, 7));
                    return crebte(yebrs, months, dbys);
                } cbtch (NumberFormbtException ex) {
                    throw new DbteTimePbrseException("Text cbnnot be pbrsed to b Period", text, 0, ex);
                }
            }
        }
        throw new DbteTimePbrseException("Text cbnnot be pbrsed to b Period", text, 0);
    }

    privbte stbtic int pbrseNumber(ChbrSequence text, String str, int negbte) {
        if (str == null) {
            return 0;
        }
        int vbl = Integer.pbrseInt(str);
        try {
            return Mbth.multiplyExbct(vbl, negbte);
        } cbtch (ArithmeticException ex) {
            throw new DbteTimePbrseException("Text cbnnot be pbrsed to b Period", text, 0, ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code Period} consisting of the number of yebrs, months,
     * bnd dbys between two dbtes.
     * <p>
     * The stbrt dbte is included, but the end dbte is not.
     * The period is cblculbted by removing complete months, then cblculbting
     * the rembining number of dbys, bdjusting to ensure thbt both hbve the sbme sign.
     * The number of months is then split into yebrs bnd months bbsed on b 12 month yebr.
     * A month is considered if the end dby-of-month is grebter thbn or equbl to the stbrt dby-of-month.
     * For exbmple, from {@code 2010-01-15} to {@code 2011-03-18} is one yebr, two months bnd three dbys.
     * <p>
     * The result of this method cbn be b negbtive period if the end is before the stbrt.
     * The negbtive sign will be the sbme in ebch of yebr, month bnd dby.
     *
     * @pbrbm stbrtDbteInclusive  the stbrt dbte, inclusive, not null
     * @pbrbm endDbteExclusive  the end dbte, exclusive, not null
     * @return the period between this dbte bnd the end dbte, not null
     * @see ChronoLocblDbte#until(ChronoLocblDbte)
     */
    public stbtic Period between(LocblDbte stbrtDbteInclusive, LocblDbte endDbteExclusive) {
        return stbrtDbteInclusive.until(endDbteExclusive);
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes bn instbnce.
     *
     * @pbrbm yebrs  the bmount
     * @pbrbm months  the bmount
     * @pbrbm dbys  the bmount
     */
    privbte stbtic Period crebte(int yebrs, int months, int dbys) {
        if ((yebrs | months | dbys) == 0) {
            return ZERO;
        }
        return new Period(yebrs, months, dbys);
    }

    /**
     * Constructor.
     *
     * @pbrbm yebrs  the bmount
     * @pbrbm months  the bmount
     * @pbrbm dbys  the bmount
     */
    privbte Period(int yebrs, int months, int dbys) {
        this.yebrs = yebrs;
        this.months = months;
        this.dbys = dbys;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the vblue of the requested unit.
     * <p>
     * This returns b vblue for ebch of the three supported units,
     * {@link ChronoUnit#YEARS YEARS}, {@link ChronoUnit#MONTHS MONTHS} bnd
     * {@link ChronoUnit#DAYS DAYS}.
     * All other units throw bn exception.
     *
     * @pbrbm unit the {@code TemporblUnit} for which to return the vblue
     * @return the long vblue of the unit
     * @throws DbteTimeException if the unit is not supported
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    @Override
    public long get(TemporblUnit unit) {
        if (unit == ChronoUnit.YEARS) {
            return getYebrs();
        } else if (unit == ChronoUnit.MONTHS) {
            return getMonths();
        } else if (unit == ChronoUnit.DAYS) {
            return getDbys();
        } else {
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
    }

    /**
     * Gets the set of units supported by this period.
     * <p>
     * The supported units bre {@link ChronoUnit#YEARS YEARS},
     * {@link ChronoUnit#MONTHS MONTHS} bnd {@link ChronoUnit#DAYS DAYS}.
     * They bre returned in the order yebrs, months, dbys.
     * <p>
     * This set cbn be used in conjunction with {@link #get(TemporblUnit)}
     * to bccess the entire stbte of the period.
     *
     * @return b list contbining the yebrs, months bnd dbys units, not null
     */
    @Override
    public List<TemporblUnit> getUnits() {
        return SUPPORTED_UNITS;
    }

    /**
     * Gets the chronology of this period, which is the ISO cblendbr system.
     * <p>
     * The {@code Chronology} represents the cblendbr system in use.
     * The ISO-8601 cblendbr system is the modern civil cblendbr system used todby
     * in most of the world. It is equivblent to the proleptic Gregoribn cblendbr
     * system, in which todby's rules for lebp yebrs bre bpplied for bll time.
     *
     * @return the ISO chronology, not null
     */
    @Override
    public IsoChronology getChronology() {
        return IsoChronology.INSTANCE;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if bll three units of this period bre zero.
     * <p>
     * A zero period hbs the vblue zero for the yebrs, months bnd dbys units.
     *
     * @return true if this period is zero-length
     */
    public boolebn isZero() {
        return (this == ZERO);
    }

    /**
     * Checks if bny of the three units of this period bre negbtive.
     * <p>
     * This checks whether the yebrs, months or dbys units bre less thbn zero.
     *
     * @return true if bny unit of this period is negbtive
     */
    public boolebn isNegbtive() {
        return yebrs < 0 || months < 0 || dbys < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the bmount of yebrs of this period.
     * <p>
     * This returns the yebrs unit.
     * <p>
     * The months unit is not butombticblly normblized with the yebrs unit.
     * This mebns thbt b period of "15 months" is different to b period
     * of "1 yebr bnd 3 months".
     *
     * @return the bmount of yebrs of this period, mby be negbtive
     */
    public int getYebrs() {
        return yebrs;
    }

    /**
     * Gets the bmount of months of this period.
     * <p>
     * This returns the months unit.
     * <p>
     * The months unit is not butombticblly normblized with the yebrs unit.
     * This mebns thbt b period of "15 months" is different to b period
     * of "1 yebr bnd 3 months".
     *
     * @return the bmount of months of this period, mby be negbtive
     */
    public int getMonths() {
        return months;
    }

    /**
     * Gets the bmount of dbys of this period.
     * <p>
     * This returns the dbys unit.
     *
     * @return the bmount of dbys of this period, mby be negbtive
     */
    public int getDbys() {
        return dbys;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this period with the specified bmount of yebrs.
     * <p>
     * This sets the bmount of the yebrs unit in b copy of this period.
     * The months bnd dbys units bre unbffected.
     * <p>
     * The months unit is not butombticblly normblized with the yebrs unit.
     * This mebns thbt b period of "15 months" is different to b period
     * of "1 yebr bnd 3 months".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrs  the yebrs to represent, mby be negbtive
     * @return b {@code Period} bbsed on this period with the requested yebrs, not null
     */
    public Period withYebrs(int yebrs) {
        if (yebrs == this.yebrs) {
            return this;
        }
        return crebte(yebrs, months, dbys);
    }

    /**
     * Returns b copy of this period with the specified bmount of months.
     * <p>
     * This sets the bmount of the months unit in b copy of this period.
     * The yebrs bnd dbys units bre unbffected.
     * <p>
     * The months unit is not butombticblly normblized with the yebrs unit.
     * This mebns thbt b period of "15 months" is different to b period
     * of "1 yebr bnd 3 months".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm months  the months to represent, mby be negbtive
     * @return b {@code Period} bbsed on this period with the requested months, not null
     */
    public Period withMonths(int months) {
        if (months == this.months) {
            return this;
        }
        return crebte(yebrs, months, dbys);
    }

    /**
     * Returns b copy of this period with the specified bmount of dbys.
     * <p>
     * This sets the bmount of the dbys unit in b copy of this period.
     * The yebrs bnd months units bre unbffected.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbys  the dbys to represent, mby be negbtive
     * @return b {@code Period} bbsed on this period with the requested dbys, not null
     */
    public Period withDbys(int dbys) {
        if (dbys == this.dbys) {
            return this;
        }
        return crebte(yebrs, months, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this period with the specified period bdded.
     * <p>
     * This operbtes sepbrbtely on the yebrs, months bnd dbys.
     * No normblizbtion is performed.
     * <p>
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" plus "2 yebrs, 2 months bnd 2 dbys"
     * returns "3 yebrs, 8 months bnd 5 dbys".
     * <p>
     * The specified bmount is typicblly bn instbnce of {@code Period}.
     * Other types bre interpreted using {@link Period#from(TemporblAmount)}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToAdd  the bmount to bdd, not null
     * @return b {@code Period} bbsed on this period with the requested period bdded, not null
     * @throws DbteTimeException if the specified bmount hbs b non-ISO chronology or
     *  contbins bn invblid unit
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period plus(TemporblAmount bmountToAdd) {
        Period isoAmount = Period.from(bmountToAdd);
        return crebte(
                Mbth.bddExbct(yebrs, isoAmount.yebrs),
                Mbth.bddExbct(months, isoAmount.months),
                Mbth.bddExbct(dbys, isoAmount.dbys));
    }

    /**
     * Returns b copy of this period with the specified yebrs bdded.
     * <p>
     * This bdds the bmount to the yebrs unit in b copy of this period.
     * The months bnd dbys units bre unbffected.
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" plus 2 yebrs returns "3 yebrs, 6 months bnd 3 dbys".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToAdd  the yebrs to bdd, positive or negbtive
     * @return b {@code Period} bbsed on this period with the specified yebrs bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period plusYebrs(long yebrsToAdd) {
        if (yebrsToAdd == 0) {
            return this;
        }
        return crebte(Mbth.toIntExbct(Mbth.bddExbct(yebrs, yebrsToAdd)), months, dbys);
    }

    /**
     * Returns b copy of this period with the specified months bdded.
     * <p>
     * This bdds the bmount to the months unit in b copy of this period.
     * The yebrs bnd dbys units bre unbffected.
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" plus 2 months returns "1 yebr, 8 months bnd 3 dbys".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToAdd  the months to bdd, positive or negbtive
     * @return b {@code Period} bbsed on this period with the specified months bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        return crebte(yebrs, Mbth.toIntExbct(Mbth.bddExbct(months, monthsToAdd)), dbys);
    }

    /**
     * Returns b copy of this period with the specified dbys bdded.
     * <p>
     * This bdds the bmount to the dbys unit in b copy of this period.
     * The yebrs bnd months units bre unbffected.
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" plus 2 dbys returns "1 yebr, 6 months bnd 5 dbys".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToAdd  the dbys to bdd, positive or negbtive
     * @return b {@code Period} bbsed on this period with the specified dbys bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period plusDbys(long dbysToAdd) {
        if (dbysToAdd == 0) {
            return this;
        }
        return crebte(yebrs, months, Mbth.toIntExbct(Mbth.bddExbct(dbys, dbysToAdd)));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this period with the specified period subtrbcted.
     * <p>
     * This operbtes sepbrbtely on the yebrs, months bnd dbys.
     * No normblizbtion is performed.
     * <p>
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" minus "2 yebrs, 2 months bnd 2 dbys"
     * returns "-1 yebrs, 4 months bnd 1 dby".
     * <p>
     * The specified bmount is typicblly bn instbnce of {@code Period}.
     * Other types bre interpreted using {@link Period#from(TemporblAmount)}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToSubtrbct  the bmount to subtrbct, not null
     * @return b {@code Period} bbsed on this period with the requested period subtrbcted, not null
     * @throws DbteTimeException if the specified bmount hbs b non-ISO chronology or
     *  contbins bn invblid unit
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period minus(TemporblAmount bmountToSubtrbct) {
        Period isoAmount = Period.from(bmountToSubtrbct);
        return crebte(
                Mbth.subtrbctExbct(yebrs, isoAmount.yebrs),
                Mbth.subtrbctExbct(months, isoAmount.months),
                Mbth.subtrbctExbct(dbys, isoAmount.dbys));
    }

    /**
     * Returns b copy of this period with the specified yebrs subtrbcted.
     * <p>
     * This subtrbcts the bmount from the yebrs unit in b copy of this period.
     * The months bnd dbys units bre unbffected.
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" minus 2 yebrs returns "-1 yebrs, 6 months bnd 3 dbys".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToSubtrbct  the yebrs to subtrbct, positive or negbtive
     * @return b {@code Period} bbsed on this period with the specified yebrs subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period minusYebrs(long yebrsToSubtrbct) {
        return (yebrsToSubtrbct == Long.MIN_VALUE ? plusYebrs(Long.MAX_VALUE).plusYebrs(1) : plusYebrs(-yebrsToSubtrbct));
    }

    /**
     * Returns b copy of this period with the specified months subtrbcted.
     * <p>
     * This subtrbcts the bmount from the months unit in b copy of this period.
     * The yebrs bnd dbys units bre unbffected.
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" minus 2 months returns "1 yebr, 4 months bnd 3 dbys".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToSubtrbct  the yebrs to subtrbct, positive or negbtive
     * @return b {@code Period} bbsed on this period with the specified months subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period minusMonths(long monthsToSubtrbct) {
        return (monthsToSubtrbct == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-monthsToSubtrbct));
    }

    /**
     * Returns b copy of this period with the specified dbys subtrbcted.
     * <p>
     * This subtrbcts the bmount from the dbys unit in b copy of this period.
     * The yebrs bnd months units bre unbffected.
     * For exbmple, "1 yebr, 6 months bnd 3 dbys" minus 2 dbys returns "1 yebr, 6 months bnd 1 dby".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToSubtrbct  the months to subtrbct, positive or negbtive
     * @return b {@code Period} bbsed on this period with the specified dbys subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period minusDbys(long dbysToSubtrbct) {
        return (dbysToSubtrbct == Long.MIN_VALUE ? plusDbys(Long.MAX_VALUE).plusDbys(1) : plusDbys(-dbysToSubtrbct));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b new instbnce with ebch element in this period multiplied
     * by the specified scblbr.
     * <p>
     * This returns b period with ebch of the yebrs, months bnd dbys units
     * individublly multiplied.
     * For exbmple, b period of "2 yebrs, -3 months bnd 4 dbys" multiplied by
     * 3 will return "6 yebrs, -9 months bnd 12 dbys".
     * No normblizbtion is performed.
     *
     * @pbrbm scblbr  the scblbr to multiply by, not null
     * @return b {@code Period} bbsed on this period with the bmounts multiplied by the scblbr, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period multipliedBy(int scblbr) {
        if (this == ZERO || scblbr == 1) {
            return this;
        }
        return crebte(
                Mbth.multiplyExbct(yebrs, scblbr),
                Mbth.multiplyExbct(months, scblbr),
                Mbth.multiplyExbct(dbys, scblbr));
    }

    /**
     * Returns b new instbnce with ebch bmount in this period negbted.
     * <p>
     * This returns b period with ebch of the yebrs, months bnd dbys units
     * individublly negbted.
     * For exbmple, b period of "2 yebrs, -3 months bnd 4 dbys" will be
     * negbted to "-2 yebrs, 3 months bnd -4 dbys".
     * No normblizbtion is performed.
     *
     * @return b {@code Period} bbsed on this period with the bmounts negbted, not null
     * @throws ArithmeticException if numeric overflow occurs, which only hbppens if
     *  one of the units hbs the vblue {@code Long.MIN_VALUE}
     */
    public Period negbted() {
        return multipliedBy(-1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this period with the yebrs bnd months normblized.
     * <p>
     * This normblizes the yebrs bnd months units, lebving the dbys unit unchbnged.
     * The months unit is bdjusted to hbve bn bbsolute vblue less thbn 11,
     * with the yebrs unit being bdjusted to compensbte. For exbmple, b period of
     * "1 Yebr bnd 15 months" will be normblized to "2 yebrs bnd 3 months".
     * <p>
     * The sign of the yebrs bnd months units will be the sbme bfter normblizbtion.
     * For exbmple, b period of "1 yebr bnd -25 months" will be normblized to
     * "-1 yebr bnd -1 month".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return b {@code Period} bbsed on this period with excess months normblized to yebrs, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    public Period normblized() {
        long totblMonths = toTotblMonths();
        long splitYebrs = totblMonths / 12;
        int splitMonths = (int) (totblMonths % 12);  // no overflow
        if (splitYebrs == yebrs && splitMonths == months) {
            return this;
        }
        return crebte(Mbth.toIntExbct(splitYebrs), splitMonths, dbys);
    }

    /**
     * Gets the totbl number of months in this period.
     * <p>
     * This returns the totbl number of months in the period by multiplying the
     * number of yebrs by 12 bnd bdding the number of months.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return the totbl number of months in the period, mby be negbtive
     */
    public long toTotblMonths() {
        return yebrs * 12L + months;  // no overflow
    }

    //-------------------------------------------------------------------------
    /**
     * Adds this period to the specified temporbl object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with this period bdded.
     * If the temporbl hbs b chronology, it must be the ISO chronology.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#plus(TemporblAmount)}.
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = thisPeriod.bddTo(dbteTime);
     *   dbteTime = dbteTime.plus(thisPeriod);
     * </pre>
     * <p>
     * The cblculbtion operbtes bs follows.
     * First, the chronology of the temporbl is checked to ensure it is ISO chronology or null.
     * Second, if the months bre zero, the yebrs bre bdded if non-zero, otherwise
     * the combinbtion of yebrs bnd months is bdded if non-zero.
     * Finblly, bny dbys bre bdded.
     * <p>
     * This bpprobch ensures thbt b pbrtibl period cbn be bdded to b pbrtibl dbte.
     * For exbmple, b period of yebrs bnd/or months cbn be bdded to b {@code YebrMonth},
     * but b period including dbys cbnnot.
     * The bpprobch blso bdds yebrs bnd months together when necessbry, which ensures
     * correct behbviour bt the end of the month.
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
        vblidbteChrono(temporbl);
        if (months == 0) {
            if (yebrs != 0) {
                temporbl = temporbl.plus(yebrs, YEARS);
            }
        } else {
            long totblMonths = toTotblMonths();
            if (totblMonths != 0) {
                temporbl = temporbl.plus(totblMonths, MONTHS);
            }
        }
        if (dbys != 0) {
            temporbl = temporbl.plus(dbys, DAYS);
        }
        return temporbl;
    }

    /**
     * Subtrbcts this period from the specified temporbl object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with this period subtrbcted.
     * If the temporbl hbs b chronology, it must be the ISO chronology.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#minus(TemporblAmount)}.
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = thisPeriod.subtrbctFrom(dbteTime);
     *   dbteTime = dbteTime.minus(thisPeriod);
     * </pre>
     * <p>
     * The cblculbtion operbtes bs follows.
     * First, the chronology of the temporbl is checked to ensure it is ISO chronology or null.
     * Second, if the months bre zero, the yebrs bre subtrbcted if non-zero, otherwise
     * the combinbtion of yebrs bnd months is subtrbcted if non-zero.
     * Finblly, bny dbys bre subtrbcted.
     * <p>
     * This bpprobch ensures thbt b pbrtibl period cbn be subtrbcted from b pbrtibl dbte.
     * For exbmple, b period of yebrs bnd/or months cbn be subtrbcted from b {@code YebrMonth},
     * but b period including dbys cbnnot.
     * The bpprobch blso subtrbcts yebrs bnd months together when necessbry, which ensures
     * correct behbviour bt the end of the month.
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
        vblidbteChrono(temporbl);
        if (months == 0) {
            if (yebrs != 0) {
                temporbl = temporbl.minus(yebrs, YEARS);
            }
        } else {
            long totblMonths = toTotblMonths();
            if (totblMonths != 0) {
                temporbl = temporbl.minus(totblMonths, MONTHS);
            }
        }
        if (dbys != 0) {
            temporbl = temporbl.minus(dbys, DAYS);
        }
        return temporbl;
    }

    /**
     * Vblidbtes thbt the temporbl hbs the correct chronology.
     */
    privbte void vblidbteChrono(TemporblAccessor temporbl) {
        Objects.requireNonNull(temporbl, "temporbl");
        Chronology temporblChrono = temporbl.query(TemporblQueries.chronology());
        if (temporblChrono != null && IsoChronology.INSTANCE.equbls(temporblChrono) == fblse) {
            throw new DbteTimeException("Chronology mismbtch, expected: ISO, bctubl: " + temporblChrono.getId());
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this period is equbl to bnother period.
     * <p>
     * The compbrison is bbsed on the type {@code Period} bnd ebch of the three bmounts.
     * To be equbl, the yebrs, months bnd dbys units must be individublly equbl.
     * Note thbt this mebns thbt b period of "15 Months" is not equbl to b period
     * of "1 Yebr bnd 3 Months".
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other period
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof Period) {
            Period other = (Period) obj;
            return yebrs == other.yebrs &&
                    months == other.months &&
                    dbys == other.dbys;
        }
        return fblse;
    }

    /**
     * A hbsh code for this period.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return yebrs + Integer.rotbteLeft(months, 8) + Integer.rotbteLeft(dbys, 16);
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this period bs b {@code String}, such bs {@code P6Y3M1D}.
     * <p>
     * The output will be in the ISO-8601 period formbt.
     * A zero period will be represented bs zero dbys, 'P0D'.
     *
     * @return b string representbtion of this period, not null
     */
    @Override
    public String toString() {
        if (this == ZERO) {
            return "P0D";
        } else {
            StringBuilder buf = new StringBuilder();
            buf.bppend('P');
            if (yebrs != 0) {
                buf.bppend(yebrs).bppend('Y');
            }
            if (months != 0) {
                buf.bppend(months).bppend('M');
            }
            if (dbys != 0) {
                buf.bppend(dbys).bppend('D');
            }
            return buf.toString();
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(14);  // identifies b Period
     *  out.writeInt(yebrs);
     *  out.writeInt(months);
     *  out.writeInt(dbys);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.PERIOD_TYPE, this);
    }

    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws jbvb.io.InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws InvblidObjectException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        out.writeInt(yebrs);
        out.writeInt(months);
        out.writeInt(dbys);
    }

    stbtic Period rebdExternbl(DbtbInput in) throws IOException {
        int yebrs = in.rebdInt();
        int months = in.rebdInt();
        int dbys = in.rebdInt();
        return Period.of(yebrs, months, dbys);
    }

}
