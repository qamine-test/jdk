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

import stbtic jbvb.time.temporbl.ChronoField.ERA;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.PROLEPTIC_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR_OF_ERA;
import stbtic jbvb.time.temporbl.ChronoUnit.CENTURIES;
import stbtic jbvb.time.temporbl.ChronoUnit.DECADES;
import stbtic jbvb.time.temporbl.ChronoUnit.ERAS;
import stbtic jbvb.time.temporbl.ChronoUnit.MILLENNIA;
import stbtic jbvb.time.temporbl.ChronoUnit.MONTHS;
import stbtic jbvb.time.temporbl.ChronoUnit.YEARS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.chrono.Chronology;
import jbvb.time.chrono.IsoChronology;
import jbvb.time.formbt.DbteTimeFormbtter;
import jbvb.time.formbt.DbteTimeFormbtterBuilder;
import jbvb.time.formbt.DbteTimePbrseException;
import jbvb.time.formbt.SignStyle;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Objects;

/**
 * A yebr-month in the ISO-8601 cblendbr system, such bs {@code 2007-12}.
 * <p>
 * {@code YebrMonth} is bn immutbble dbte-time object thbt represents the combinbtion
 * of b yebr bnd month. Any field thbt cbn be derived from b yebr bnd month, such bs
 * qubrter-of-yebr, cbn be obtbined.
 * <p>
 * This clbss does not store or represent b dby, time or time-zone.
 * For exbmple, the vblue "October 2007" cbn be stored in b {@code YebrMonth}.
 * <p>
 * The ISO-8601 cblendbr system is the modern civil cblendbr system used todby
 * in most of the world. It is equivblent to the proleptic Gregoribn cblendbr
 * system, in which todby's rules for lebp yebrs bre bpplied for bll time.
 * For most bpplicbtions written todby, the ISO-8601 rules bre entirely suitbble.
 * However, bny bpplicbtion thbt mbkes use of historicbl dbtes, bnd requires them
 * to be bccurbte will find the ISO-8601 bpprobch unsuitbble.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code YebrMonth} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss YebrMonth
        implements Temporbl, TemporblAdjuster, Compbrbble<YebrMonth>, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 4183400860270640070L;
    /**
     * Pbrser.
     */
    privbte stbtic finbl DbteTimeFormbtter PARSER = new DbteTimeFormbtterBuilder()
        .bppendVblue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
        .bppendLiterbl('-')
        .bppendVblue(MONTH_OF_YEAR, 2)
        .toFormbtter();

    /**
     * The yebr.
     */
    privbte finbl int yebr;
    /**
     * The month-of-yebr, not null.
     */
    privbte finbl int month;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current yebr-month from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current yebr-month.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current yebr-month using the system clock bnd defbult time-zone, not null
     */
    public stbtic YebrMonth now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current yebr-month from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current yebr-month.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current yebr-month using the system clock, not null
     */
    public stbtic YebrMonth now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current yebr-month from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current yebr-month.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current yebr-month, not null
     */
    public stbtic YebrMonth now(Clock clock) {
        finbl LocblDbte now = LocblDbte.now(clock);  // cblled once
        return YebrMonth.of(now.getYebr(), now.getMonth());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code YebrMonth} from b yebr bnd month.
     *
     * @pbrbm yebr  the yebr to represent, from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, not null
     * @return the yebr-month, not null
     * @throws DbteTimeException if the yebr vblue is invblid
     */
    public stbtic YebrMonth of(int yebr, Month month) {
        Objects.requireNonNull(month, "month");
        return of(yebr, month.getVblue());
    }

    /**
     * Obtbins bn instbnce of {@code YebrMonth} from b yebr bnd month.
     *
     * @pbrbm yebr  the yebr to represent, from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, from 1 (Jbnubry) to 12 (December)
     * @return the yebr-month, not null
     * @throws DbteTimeException if either field vblue is invblid
     */
    public stbtic YebrMonth of(int yebr, int month) {
        YEAR.checkVblidVblue(yebr);
        MONTH_OF_YEAR.checkVblidVblue(month);
        return new YebrMonth(yebr, month);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code YebrMonth} from b temporbl object.
     * <p>
     * This obtbins b yebr-month bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code YebrMonth}.
     * <p>
     * The conversion extrbcts the {@link ChronoField#YEAR YEAR} bnd
     * {@link ChronoField#MONTH_OF_YEAR MONTH_OF_YEAR} fields.
     * The extrbction is only permitted if the temporbl object hbs bn ISO
     * chronology, or cbn be converted to b {@code LocblDbte}.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code YebrMonth::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the yebr-month, not null
     * @throws DbteTimeException if unbble to convert to b {@code YebrMonth}
     */
    public stbtic YebrMonth from(TemporblAccessor temporbl) {
        if (temporbl instbnceof YebrMonth) {
            return (YebrMonth) temporbl;
        }
        Objects.requireNonNull(temporbl, "temporbl");
        try {
            if (IsoChronology.INSTANCE.equbls(Chronology.from(temporbl)) == fblse) {
                temporbl = LocblDbte.from(temporbl);
            }
            return of(temporbl.get(YEAR), temporbl.get(MONTH_OF_YEAR));
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin YebrMonth from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme(), ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code YebrMonth} from b text string such bs {@code 2007-12}.
     * <p>
     * The string must represent b vblid yebr-month.
     * The formbt must be {@code uuuu-MM}.
     * Yebrs outside the rbnge 0000 to 9999 must be prefixed by the plus or minus symbol.
     *
     * @pbrbm text  the text to pbrse such bs "2007-12", not null
     * @return the pbrsed yebr-month, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic YebrMonth pbrse(ChbrSequence text) {
        return pbrse(text, PARSER);
    }

    /**
     * Obtbins bn instbnce of {@code YebrMonth} from b text string using b specific formbtter.
     * <p>
     * The text is pbrsed using the formbtter, returning b yebr-month.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the pbrsed yebr-month, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic YebrMonth pbrse(ChbrSequence text, DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.pbrse(text, YebrMonth::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @pbrbm yebr  the yebr to represent, vblidbted from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, vblidbted from 1 (Jbnubry) to 12 (December)
     */
    privbte YebrMonth(int yebr, int month) {
        this.yebr = yebr;
        this.month = month;
    }

    /**
     * Returns b copy of this yebr-month with the new yebr bnd month, checking
     * to see if b new object is in fbct required.
     *
     * @pbrbm newYebr  the yebr to represent, vblidbted from MIN_YEAR to MAX_YEAR
     * @pbrbm newMonth  the month-of-yebr to represent, vblidbted not null
     * @return the yebr-month, not null
     */
    privbte YebrMonth with(int newYebr, int newMonth) {
        if (yebr == newYebr && month == newMonth) {
            return this;
        }
        return new YebrMonth(newYebr, newMonth);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this yebr-month cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge},
     * {@link #get(TemporblField) get} bnd {@link #with(TemporblField, long)}
     * methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The supported fields bre:
     * <ul>
     * <li>{@code MONTH_OF_YEAR}
     * <li>{@code PROLEPTIC_MONTH}
     * <li>{@code YEAR_OF_ERA}
     * <li>{@code YEAR}
     * <li>{@code ERA}
     * </ul>
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this yebr-month, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field == YEAR || field == MONTH_OF_YEAR ||
                    field == PROLEPTIC_MONTH || field == YEAR_OF_ERA || field == ERA;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to, or subtrbcted from, this yebr-month.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     * <p>
     * If the unit is b {@link ChronoUnit} then the query is implemented here.
     * The supported units bre:
     * <ul>
     * <li>{@code MONTHS}
     * <li>{@code YEARS}
     * <li>{@code DECADES}
     * <li>{@code CENTURIES}
     * <li>{@code MILLENNIA}
     * <li>{@code ERAS}
     * </ul>
     * All other {@code ChronoUnit} instbnces will return fblse.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.isSupportedBy(Temporbl)}
     * pbssing {@code this} bs the brgument.
     * Whether the unit is supported is determined by the unit.
     *
     * @pbrbm unit  the unit to check, null returns fblse
     * @return true if the unit cbn be bdded/subtrbcted, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            return unit == MONTHS || unit == YEARS || unit == DECADES || unit == CENTURIES || unit == MILLENNIA || unit == ERAS;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This yebr-month is used to enhbnce the bccurbcy of the returned rbnge.
     * If it is not possible to return the rbnge, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return
     * bppropribte rbnge instbnces.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.rbngeRefinedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the rbnge cbn be obtbined is determined by the field.
     *
     * @pbrbm field  the field to query the rbnge for, not null
     * @return the rbnge of vblid vblues for the field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     */
    @Override
    public VblueRbnge rbnge(TemporblField field) {
        if (field == YEAR_OF_ERA) {
            return (getYebr() <= 0 ? VblueRbnge.of(1, Yebr.MAX_VALUE + 1) : VblueRbnge.of(1, Yebr.MAX_VALUE));
        }
        return Temporbl.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this yebr-month bs bn {@code int}.
     * <p>
     * This queries this yebr-month for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this yebr-month, except {@code PROLEPTIC_MONTH} which is too
     * lbrge to fit in bn {@code int} bnd throw b {@code DbteTimeException}.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument. Whether the vblue cbn be obtbined,
     * bnd whbt the vblue represents, is determined by the field.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined or
     *         the vblue is outside the rbnge of vblid vblues for the field
     * @throws UnsupportedTemporblTypeException if the field is not supported or
     *         the rbnge of vblues exceeds bn {@code int}
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override  // override for Jbvbdoc
    public int get(TemporblField field) {
        return rbnge(field).checkVblidIntVblue(getLong(field), field);
    }

    /**
     * Gets the vblue of the specified field from this yebr-month bs b {@code long}.
     * <p>
     * This queries this yebr-month for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this yebr-month.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument. Whether the vblue cbn be obtbined,
     * bnd whbt the vblue represents, is determined by the field.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long getLong(TemporblField field) {
        if (field instbnceof ChronoField) {
            switch ((ChronoField) field) {
                cbse MONTH_OF_YEAR: return month;
                cbse PROLEPTIC_MONTH: return getProlepticMonth();
                cbse YEAR_OF_ERA: return (yebr < 1 ? 1 - yebr : yebr);
                cbse YEAR: return yebr;
                cbse ERA: return (yebr < 1 ? 0 : 1);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    privbte long getProlepticMonth() {
        return (yebr * 12L + month - 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the yebr field.
     * <p>
     * This method returns the primitive {@code int} vblue for the yebr.
     * <p>
     * The yebr returned by this method is proleptic bs per {@code get(YEAR)}.
     *
     * @return the yebr, from MIN_YEAR to MAX_YEAR
     */
    public int getYebr() {
        return yebr;
    }

    /**
     * Gets the month-of-yebr field from 1 to 12.
     * <p>
     * This method returns the month bs bn {@code int} from 1 to 12.
     * Applicbtion code is frequently clebrer if the enum {@link Month}
     * is used by cblling {@link #getMonth()}.
     *
     * @return the month-of-yebr, from 1 to 12
     * @see #getMonth()
     */
    public int getMonthVblue() {
        return month;
    }

    /**
     * Gets the month-of-yebr field using the {@code Month} enum.
     * <p>
     * This method returns the enum {@link Month} for the month.
     * This bvoids confusion bs to whbt {@code int} vblues mebn.
     * If you need bccess to the primitive {@code int} vblue then the enum
     * provides the {@link Month#getVblue() int vblue}.
     *
     * @return the month-of-yebr, not null
     * @see #getMonthVblue()
     */
    public Month getMonth() {
        return Month.of(month);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the yebr is b lebp yebr, bccording to the ISO proleptic
     * cblendbr system rules.
     * <p>
     * This method bpplies the current rules for lebp yebrs bcross the whole time-line.
     * In generbl, b yebr is b lebp yebr if it is divisible by four without
     * rembinder. However, yebrs divisible by 100, bre not lebp yebrs, with
     * the exception of yebrs divisible by 400 which bre.
     * <p>
     * For exbmple, 1904 is b lebp yebr it is divisible by 4.
     * 1900 wbs not b lebp yebr bs it is divisible by 100, however 2000 wbs b
     * lebp yebr bs it is divisible by 400.
     * <p>
     * The cblculbtion is proleptic - bpplying the sbme rules into the fbr future bnd fbr pbst.
     * This is historicblly inbccurbte, but is correct for the ISO-8601 stbndbrd.
     *
     * @return true if the yebr is lebp, fblse otherwise
     */
    public boolebn isLebpYebr() {
        return IsoChronology.INSTANCE.isLebpYebr(yebr);
    }

    /**
     * Checks if the dby-of-month is vblid for this yebr-month.
     * <p>
     * This method checks whether this yebr bnd month bnd the input dby form
     * b vblid dbte.
     *
     * @pbrbm dbyOfMonth  the dby-of-month to vblidbte, from 1 to 31, invblid vblue returns fblse
     * @return true if the dby is vblid for this yebr-month
     */
    public boolebn isVblidDby(int dbyOfMonth) {
        return dbyOfMonth >= 1 && dbyOfMonth <= lengthOfMonth();
    }

    /**
     * Returns the length of the month, tbking bccount of the yebr.
     * <p>
     * This returns the length of the month in dbys.
     * For exbmple, b dbte in Jbnubry would return 31.
     *
     * @return the length of the month in dbys, from 28 to 31
     */
    public int lengthOfMonth() {
        return getMonth().length(isLebpYebr());
    }

    /**
     * Returns the length of the yebr.
     * <p>
     * This returns the length of the yebr in dbys, either 365 or 366.
     *
     * @return 366 if the yebr is lebp, 365 otherwise
     */
    public int lengthOfYebr() {
        return (isLebpYebr() ? 366 : 365);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns bn bdjusted copy of this yebr-month.
     * <p>
     * This returns b {@code YebrMonth}, bbsed on this one, with the yebr-month bdjusted.
     * The bdjustment tbkes plbce using the specified bdjuster strbtegy object.
     * Rebd the documentbtion of the bdjuster to understbnd whbt bdjustment will be mbde.
     * <p>
     * A simple bdjuster might simply set the one of the fields, such bs the yebr field.
     * A more complex bdjuster might set the yebr-month to the next month thbt
     * Hblley's comet will pbss the Ebrth.
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblAdjuster#bdjustInto(Temporbl)} method on the
     * specified bdjuster pbssing {@code this} bs the brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bdjuster the bdjuster to use, not null
     * @return b {@code YebrMonth} bbsed on {@code this} with the bdjustment mbde, not null
     * @throws DbteTimeException if the bdjustment cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public YebrMonth with(TemporblAdjuster bdjuster) {
        return (YebrMonth) bdjuster.bdjustInto(this);
    }

    /**
     * Returns b copy of this yebr-month with the specified field set to b new vblue.
     * <p>
     * This returns b {@code YebrMonth}, bbsed on this one, with the vblue
     * for the specified field chbnged.
     * This cbn be used to chbnge bny supported field, such bs the yebr or month.
     * If it is not possible to set the vblue, becbuse the field is not supported or for
     * some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the bdjustment is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code MONTH_OF_YEAR} -
     *  Returns b {@code YebrMonth} with the specified month-of-yebr.
     *  The yebr will be unchbnged.
     * <li>{@code PROLEPTIC_MONTH} -
     *  Returns b {@code YebrMonth} with the specified proleptic-month.
     *  This completely replbces the yebr bnd month of this object.
     * <li>{@code YEAR_OF_ERA} -
     *  Returns b {@code YebrMonth} with the specified yebr-of-erb
     *  The month bnd erb will be unchbnged.
     * <li>{@code YEAR} -
     *  Returns b {@code YebrMonth} with the specified yebr.
     *  The month will be unchbnged.
     * <li>{@code ERA} -
     *  Returns b {@code YebrMonth} with the specified erb.
     *  The month bnd yebr-of-erb will be unchbnged.
     * </ul>
     * <p>
     * In bll cbses, if the new vblue is outside the vblid rbnge of vblues for the field
     * then b {@code DbteTimeException} will be thrown.
     * <p>
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.bdjustInto(Temporbl, long)}
     * pbssing {@code this} bs the brgument. In this cbse, the field determines
     * whether bnd how to bdjust the instbnt.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm field  the field to set in the result, not null
     * @pbrbm newVblue  the new vblue of the field in the result
     * @return b {@code YebrMonth} bbsed on {@code this} with the specified field set, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public YebrMonth with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            f.checkVblidVblue(newVblue);
            switch (f) {
                cbse MONTH_OF_YEAR: return withMonth((int) newVblue);
                cbse PROLEPTIC_MONTH: return plusMonths(newVblue - getProlepticMonth());
                cbse YEAR_OF_ERA: return withYebr((int) (yebr < 1 ? 1 - newVblue : newVblue));
                cbse YEAR: return withYebr((int) newVblue);
                cbse ERA: return (getLong(ERA) == newVblue ? this : withYebr(1 - yebr));
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.bdjustInto(this, newVblue);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code YebrMonth} with the yebr bltered.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebr  the yebr to set in the returned yebr-month, from MIN_YEAR to MAX_YEAR
     * @return b {@code YebrMonth} bbsed on this yebr-month with the requested yebr, not null
     * @throws DbteTimeException if the yebr vblue is invblid
     */
    public YebrMonth withYebr(int yebr) {
        YEAR.checkVblidVblue(yebr);
        return with(yebr, month);
    }

    /**
     * Returns b copy of this {@code YebrMonth} with the month-of-yebr bltered.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm month  the month-of-yebr to set in the returned yebr-month, from 1 (Jbnubry) to 12 (December)
     * @return b {@code YebrMonth} bbsed on this yebr-month with the requested month, not null
     * @throws DbteTimeException if the month-of-yebr vblue is invblid
     */
    public YebrMonth withMonth(int month) {
        MONTH_OF_YEAR.checkVblidVblue(month);
        return with(yebr, month);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this yebr-month with the specified bmount bdded.
     * <p>
     * This returns b {@code YebrMonth}, bbsed on this one, with the specified bmount bdded.
     * The bmount is typicblly {@link Period} but mby be bny other type implementing
     * the {@link TemporblAmount} interfbce.
     * <p>
     * The cblculbtion is delegbted to the bmount object by cblling
     * {@link TemporblAmount#bddTo(Temporbl)}. The bmount implementbtion is free
     * to implement the bddition in bny wby it wishes, however it typicblly
     * cblls bbck to {@link #plus(long, TemporblUnit)}. Consult the documentbtion
     * of the bmount implementbtion to determine if it cbn be successfully bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToAdd  the bmount to bdd, not null
     * @return b {@code YebrMonth} bbsed on this yebr-month with the bddition mbde, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public YebrMonth plus(TemporblAmount bmountToAdd) {
        return (YebrMonth) bmountToAdd.bddTo(this);
    }

    /**
     * Returns b copy of this yebr-month with the specified bmount bdded.
     * <p>
     * This returns b {@code YebrMonth}, bbsed on this one, with the bmount
     * in terms of the unit bdded. If it is not possible to bdd the bmount, becbuse the
     * unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoUnit} then the bddition is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code MONTHS} -
     *  Returns b {@code YebrMonth} with the specified number of months bdded.
     *  This is equivblent to {@link #plusMonths(long)}.
     * <li>{@code YEARS} -
     *  Returns b {@code YebrMonth} with the specified number of yebrs bdded.
     *  This is equivblent to {@link #plusYebrs(long)}.
     * <li>{@code DECADES} -
     *  Returns b {@code YebrMonth} with the specified number of decbdes bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 10.
     * <li>{@code CENTURIES} -
     *  Returns b {@code YebrMonth} with the specified number of centuries bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 100.
     * <li>{@code MILLENNIA} -
     *  Returns b {@code YebrMonth} with the specified number of millennib bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 1,000.
     * <li>{@code ERAS} -
     *  Returns b {@code YebrMonth} with the specified number of erbs bdded.
     *  Only two erbs bre supported so the bmount must be one, zero or minus one.
     *  If the bmount is non-zero then the yebr is chbnged such thbt the yebr-of-erb
     *  is unchbnged.
     * </ul>
     * <p>
     * All other {@code ChronoUnit} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.bddTo(Temporbl, long)}
     * pbssing {@code this} bs the brgument. In this cbse, the unit determines
     * whether bnd how to perform the bddition.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToAdd  the bmount of the unit to bdd to the result, mby be negbtive
     * @pbrbm unit  the unit of the bmount to bdd, not null
     * @return b {@code YebrMonth} bbsed on this yebr-month with the specified bmount bdded, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public YebrMonth plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            switch ((ChronoUnit) unit) {
                cbse MONTHS: return plusMonths(bmountToAdd);
                cbse YEARS: return plusYebrs(bmountToAdd);
                cbse DECADES: return plusYebrs(Mbth.multiplyExbct(bmountToAdd, 10));
                cbse CENTURIES: return plusYebrs(Mbth.multiplyExbct(bmountToAdd, 100));
                cbse MILLENNIA: return plusYebrs(Mbth.multiplyExbct(bmountToAdd, 1000));
                cbse ERAS: return with(ERA, Mbth.bddExbct(getLong(ERA), bmountToAdd));
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.bddTo(this, bmountToAdd);
    }

    /**
     * Returns b copy of this {@code YebrMonth} with the specified number of yebrs bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToAdd  the yebrs to bdd, mby be negbtive
     * @return b {@code YebrMonth} bbsed on this yebr-month with the yebrs bdded, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public YebrMonth plusYebrs(long yebrsToAdd) {
        if (yebrsToAdd == 0) {
            return this;
        }
        int newYebr = YEAR.checkVblidIntVblue(yebr + yebrsToAdd);  // sbfe overflow
        return with(newYebr, month);
    }

    /**
     * Returns b copy of this {@code YebrMonth} with the specified number of months bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToAdd  the months to bdd, mby be negbtive
     * @return b {@code YebrMonth} bbsed on this yebr-month with the months bdded, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public YebrMonth plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = yebr * 12L + (month - 1);
        long cblcMonths = monthCount + monthsToAdd;  // sbfe overflow
        int newYebr = YEAR.checkVblidIntVblue(Mbth.floorDiv(cblcMonths, 12));
        int newMonth = (int)Mbth.floorMod(cblcMonths, 12) + 1;
        return with(newYebr, newMonth);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this yebr-month with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code YebrMonth}, bbsed on this one, with the specified bmount subtrbcted.
     * The bmount is typicblly {@link Period} but mby be bny other type implementing
     * the {@link TemporblAmount} interfbce.
     * <p>
     * The cblculbtion is delegbted to the bmount object by cblling
     * {@link TemporblAmount#subtrbctFrom(Temporbl)}. The bmount implementbtion is free
     * to implement the subtrbction in bny wby it wishes, however it typicblly
     * cblls bbck to {@link #minus(long, TemporblUnit)}. Consult the documentbtion
     * of the bmount implementbtion to determine if it cbn be successfully subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToSubtrbct  the bmount to subtrbct, not null
     * @return b {@code YebrMonth} bbsed on this yebr-month with the subtrbction mbde, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public YebrMonth minus(TemporblAmount bmountToSubtrbct) {
        return (YebrMonth) bmountToSubtrbct.subtrbctFrom(this);
    }

    /**
     * Returns b copy of this yebr-month with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code YebrMonth}, bbsed on this one, with the bmount
     * in terms of the unit subtrbcted. If it is not possible to subtrbct the bmount,
     * becbuse the unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * This method is equivblent to {@link #plus(long, TemporblUnit)} with the bmount negbted.
     * See thbt method for b full description of how bddition, bnd thus subtrbction, works.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToSubtrbct  the bmount of the unit to subtrbct from the result, mby be negbtive
     * @pbrbm unit  the unit of the bmount to subtrbct, not null
     * @return b {@code YebrMonth} bbsed on this yebr-month with the specified bmount subtrbcted, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public YebrMonth minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    /**
     * Returns b copy of this {@code YebrMonth} with the specified number of yebrs subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToSubtrbct  the yebrs to subtrbct, mby be negbtive
     * @return b {@code YebrMonth} bbsed on this yebr-month with the yebrs subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public YebrMonth minusYebrs(long yebrsToSubtrbct) {
        return (yebrsToSubtrbct == Long.MIN_VALUE ? plusYebrs(Long.MAX_VALUE).plusYebrs(1) : plusYebrs(-yebrsToSubtrbct));
    }

    /**
     * Returns b copy of this {@code YebrMonth} with the specified number of months subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToSubtrbct  the months to subtrbct, mby be negbtive
     * @return b {@code YebrMonth} bbsed on this yebr-month with the months subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public YebrMonth minusMonths(long monthsToSubtrbct) {
        return (monthsToSubtrbct == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-monthsToSubtrbct));
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this yebr-month using the specified query.
     * <p>
     * This queries this yebr-month using the specified query strbtegy object.
     * The {@code TemporblQuery} object defines the logic to be used to
     * obtbin the result. Rebd the documentbtion of the query to understbnd
     * whbt the result of this method will be.
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblQuery#queryFrom(TemporblAccessor)} method on the
     * specified query pbssing {@code this} bs the brgument.
     *
     * @pbrbm <R> the type of the result
     * @pbrbm query  the query to invoke, not null
     * @return the query result, null mby be returned (defined by the query)
     * @throws DbteTimeException if unbble to query (defined by the query)
     * @throws ArithmeticException if numeric overflow occurs (defined by the query)
     */
    @SuppressWbrnings("unchecked")
    @Override
    public <R> R query(TemporblQuery<R> query) {
        if (query == TemporblQueries.chronology()) {
            return (R) IsoChronology.INSTANCE;
        } else if (query == TemporblQueries.precision()) {
            return (R) MONTHS;
        }
        return Temporbl.super.query(query);
    }

    /**
     * Adjusts the specified temporbl object to hbve this yebr-month.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the yebr bnd month chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#PROLEPTIC_MONTH} bs the field.
     * If the specified temporbl object does not use the ISO cblendbr system then
     * b {@code DbteTimeException} is thrown.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisYebrMonth.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisYebrMonth);
     * </pre>
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the tbrget object to be bdjusted, not null
     * @return the bdjusted object, not null
     * @throws DbteTimeException if unbble to mbke the bdjustment
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Temporbl bdjustInto(Temporbl temporbl) {
        if (Chronology.from(temporbl).equbls(IsoChronology.INSTANCE) == fblse) {
            throw new DbteTimeException("Adjustment only supported on ISO dbte-time");
        }
        return temporbl.with(PROLEPTIC_MONTH, getProlepticMonth());
    }

    /**
     * Cblculbtes the bmount of time until bnother yebr-month in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two {@code YebrMonth}
     * objects in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified yebr-month.
     * The result will be negbtive if the end is before the stbrt.
     * The {@code Temporbl} pbssed to this method is converted to b
     * {@code YebrMonth} using {@link #from(TemporblAccessor)}.
     * For exbmple, the bmount in yebrs between two yebr-months cbn be cblculbted
     * using {@code stbrtYebrMonth.until(endYebrMonth, YEARS)}.
     * <p>
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two yebr-months.
     * For exbmple, the bmount in decbdes between 2012-06 bnd 2032-05
     * will only be one decbde bs it is one month short of two decbdes.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method.
     * The second is to use {@link TemporblUnit#between(Temporbl, Temporbl)}:
     * <pre>
     *   // these two lines bre equivblent
     *   bmount = stbrt.until(end, MONTHS);
     *   bmount = MONTHS.between(stbrt, end);
     * </pre>
     * The choice should be mbde bbsed on which mbkes the code more rebdbble.
     * <p>
     * The cblculbtion is implemented in this method for {@link ChronoUnit}.
     * The units {@code MONTHS}, {@code YEARS}, {@code DECADES},
     * {@code CENTURIES}, {@code MILLENNIA} bnd {@code ERAS} bre supported.
     * Other {@code ChronoUnit} vblues will throw bn exception.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.between(Temporbl, Temporbl)}
     * pbssing {@code this} bs the first brgument bnd the converted input temporbl
     * bs the second brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm endExclusive  the end dbte, exclusive, which is converted to b {@code YebrMonth}, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this yebr-month bnd the end yebr-month
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to b {@code YebrMonth}
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        YebrMonth end = YebrMonth.from(endExclusive);
        if (unit instbnceof ChronoUnit) {
            long monthsUntil = end.getProlepticMonth() - getProlepticMonth();  // no overflow
            switch ((ChronoUnit) unit) {
                cbse MONTHS: return monthsUntil;
                cbse YEARS: return monthsUntil / 12;
                cbse DECADES: return monthsUntil / 120;
                cbse CENTURIES: return monthsUntil / 1200;
                cbse MILLENNIA: return monthsUntil / 12000;
                cbse ERAS: return end.getLong(ERA) - getLong(ERA);
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    /**
     * Formbts this yebr-month using the specified formbtter.
     * <p>
     * This yebr-month will be pbssed to the formbtter to produce b string.
     *
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the formbtted yebr-month string, not null
     * @throws DbteTimeException if bn error occurs during printing
     */
    public String formbt(DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this yebr-month with b dby-of-month to crebte b {@code LocblDbte}.
     * <p>
     * This returns b {@code LocblDbte} formed from this yebr-month bnd the specified dby-of-month.
     * <p>
     * The dby-of-month vblue must be vblid for the yebr-month.
     * <p>
     * This method cbn be used bs pbrt of b chbin to produce b dbte:
     * <pre>
     *  LocblDbte dbte = yebr.btMonth(month).btDby(dby);
     * </pre>
     *
     * @pbrbm dbyOfMonth  the dby-of-month to use, from 1 to 31
     * @return the dbte formed from this yebr-month bnd the specified dby, not null
     * @throws DbteTimeException if the dby is invblid for the yebr-month
     * @see #isVblidDby(int)
     */
    public LocblDbte btDby(int dbyOfMonth) {
        return LocblDbte.of(yebr, month, dbyOfMonth);
    }

    /**
     * Returns b {@code LocblDbte} bt the end of the month.
     * <p>
     * This returns b {@code LocblDbte} bbsed on this yebr-month.
     * The dby-of-month is set to the lbst vblid dby of the month, tbking
     * into bccount lebp yebrs.
     * <p>
     * This method cbn be used bs pbrt of b chbin to produce b dbte:
     * <pre>
     *  LocblDbte dbte = yebr.btMonth(month).btEndOfMonth();
     * </pre>
     *
     * @return the lbst vblid dbte of this yebr-month, not null
     */
    public LocblDbte btEndOfMonth() {
        return LocblDbte.of(yebr, month, lengthOfMonth());
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this yebr-month to bnother yebr-month.
     * <p>
     * The compbrison is bbsed first on the vblue of the yebr, then on the vblue of the month.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm other  the other yebr-month to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    public int compbreTo(YebrMonth other) {
        int cmp = (yebr - other.yebr);
        if (cmp == 0) {
            cmp = (month - other.month);
        }
        return cmp;
    }

    /**
     * Checks if this yebr-month is bfter the specified yebr-month.
     *
     * @pbrbm other  the other yebr-month to compbre to, not null
     * @return true if this is bfter the specified yebr-month
     */
    public boolebn isAfter(YebrMonth other) {
        return compbreTo(other) > 0;
    }

    /**
     * Checks if this yebr-month is before the specified yebr-month.
     *
     * @pbrbm other  the other yebr-month to compbre to, not null
     * @return true if this point is before the specified yebr-month
     */
    public boolebn isBefore(YebrMonth other) {
        return compbreTo(other) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this yebr-month is equbl to bnother yebr-month.
     * <p>
     * The compbrison is bbsed on the time-line position of the yebr-months.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other yebr-month
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof YebrMonth) {
            YebrMonth other = (YebrMonth) obj;
            return yebr == other.yebr && month == other.month;
        }
        return fblse;
    }

    /**
     * A hbsh code for this yebr-month.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return yebr ^ (month << 27);
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this yebr-month bs b {@code String}, such bs {@code 2007-12}.
     * <p>
     * The output will be in the formbt {@code uuuu-MM}:
     *
     * @return b string representbtion of this yebr-month, not null
     */
    @Override
    public String toString() {
        int bbsYebr = Mbth.bbs(yebr);
        StringBuilder buf = new StringBuilder(9);
        if (bbsYebr < 1000) {
            if (yebr < 0) {
                buf.bppend(yebr - 10000).deleteChbrAt(1);
            } else {
                buf.bppend(yebr + 10000).deleteChbrAt(0);
            }
        } else {
            buf.bppend(yebr);
        }
        return buf.bppend(month < 10 ? "-0" : "-")
            .bppend(month)
            .toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(12);  // identifies b YebrMonth
     *  out.writeInt(yebr);
     *  out.writeByte(month);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.YEAR_MONTH_TYPE, this);
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
        out.writeInt(yebr);
        out.writeByte(month);
    }

    stbtic YebrMonth rebdExternbl(DbtbInput in) throws IOException {
        int yebr = in.rebdInt();
        byte month = in.rebdByte();
        return YebrMonth.of(yebr, month);
    }

}
