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
import stbtic jbvb.time.temporbl.ChronoField.YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR_OF_ERA;
import stbtic jbvb.time.temporbl.ChronoUnit.CENTURIES;
import stbtic jbvb.time.temporbl.ChronoUnit.DECADES;
import stbtic jbvb.time.temporbl.ChronoUnit.ERAS;
import stbtic jbvb.time.temporbl.ChronoUnit.MILLENNIA;
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
 * A yebr in the ISO-8601 cblendbr system, such bs {@code 2007}.
 * <p>
 * {@code Yebr} is bn immutbble dbte-time object thbt represents b yebr.
 * Any field thbt cbn be derived from b yebr cbn be obtbined.
 * <p>
 * <b>Note thbt yebrs in the ISO chronology only blign with yebrs in the
 * Gregoribn-Julibn system for modern yebrs. Pbrts of Russib did not switch to the
 * modern Gregoribn/ISO rules until 1920.
 * As such, historicbl yebrs must be trebted with cbution.</b>
 * <p>
 * This clbss does not store or represent b month, dby, time or time-zone.
 * For exbmple, the vblue "2007" cbn be stored in b {@code Yebr}.
 * <p>
 * Yebrs represented by this clbss follow the ISO-8601 stbndbrd bnd use
 * the proleptic numbering system. Yebr 1 is preceded by yebr 0, then by yebr -1.
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
 * {@code Yebr} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss Yebr
        implements Temporbl, TemporblAdjuster, Compbrbble<Yebr>, Seriblizbble {

    /**
     * The minimum supported yebr, '-999,999,999'.
     */
    public stbtic finbl int MIN_VALUE = -999_999_999;
    /**
     * The mbximum supported yebr, '+999,999,999'.
     */
    public stbtic finbl int MAX_VALUE = 999_999_999;

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -23038383694477807L;
    /**
     * Pbrser.
     */
    privbte stbtic finbl DbteTimeFormbtter PARSER = new DbteTimeFormbtterBuilder()
        .bppendVblue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
        .toFormbtter();

    /**
     * The yebr being represented.
     */
    privbte finbl int yebr;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current yebr from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current yebr.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current yebr using the system clock bnd defbult time-zone, not null
     */
    public stbtic Yebr now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current yebr from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current yebr.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current yebr using the system clock, not null
     */
    public stbtic Yebr now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current yebr from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current yebr.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current yebr, not null
     */
    public stbtic Yebr now(Clock clock) {
        finbl LocblDbte now = LocblDbte.now(clock);  // cblled once
        return Yebr.of(now.getYebr());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Yebr}.
     * <p>
     * This method bccepts b yebr vblue from the proleptic ISO cblendbr system.
     * <p>
     * The yebr 2AD/CE is represented by 2.<br>
     * The yebr 1AD/CE is represented by 1.<br>
     * The yebr 1BC/BCE is represented by 0.<br>
     * The yebr 2BC/BCE is represented by -1.<br>
     *
     * @pbrbm isoYebr  the ISO proleptic yebr to represent, from {@code MIN_VALUE} to {@code MAX_VALUE}
     * @return the yebr, not null
     * @throws DbteTimeException if the field is invblid
     */
    public stbtic Yebr of(int isoYebr) {
        YEAR.checkVblidVblue(isoYebr);
        return new Yebr(isoYebr);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Yebr} from b temporbl object.
     * <p>
     * This obtbins b yebr bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code Yebr}.
     * <p>
     * The conversion extrbcts the {@link ChronoField#YEAR yebr} field.
     * The extrbction is only permitted if the temporbl object hbs bn ISO
     * chronology, or cbn be converted to b {@code LocblDbte}.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code Yebr::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the yebr, not null
     * @throws DbteTimeException if unbble to convert to b {@code Yebr}
     */
    public stbtic Yebr from(TemporblAccessor temporbl) {
        if (temporbl instbnceof Yebr) {
            return (Yebr) temporbl;
        }
        Objects.requireNonNull(temporbl, "temporbl");
        try {
            if (IsoChronology.INSTANCE.equbls(Chronology.from(temporbl)) == fblse) {
                temporbl = LocblDbte.from(temporbl);
            }
            return of(temporbl.get(YEAR));
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin Yebr from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme(), ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Yebr} from b text string such bs {@code 2007}.
     * <p>
     * The string must represent b vblid yebr.
     * Yebrs outside the rbnge 0000 to 9999 must be prefixed by the plus or minus symbol.
     *
     * @pbrbm text  the text to pbrse such bs "2007", not null
     * @return the pbrsed yebr, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic Yebr pbrse(ChbrSequence text) {
        return pbrse(text, PARSER);
    }

    /**
     * Obtbins bn instbnce of {@code Yebr} from b text string using b specific formbtter.
     * <p>
     * The text is pbrsed using the formbtter, returning b yebr.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the pbrsed yebr, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic Yebr pbrse(ChbrSequence text, DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.pbrse(text, Yebr::from);
    }

    //-------------------------------------------------------------------------
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
     * @pbrbm yebr  the yebr to check
     * @return true if the yebr is lebp, fblse otherwise
     */
    public stbtic boolebn isLebp(long yebr) {
        return ((yebr & 3) == 0) && ((yebr % 100) != 0 || (yebr % 400) == 0);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @pbrbm yebr  the yebr to represent
     */
    privbte Yebr(int yebr) {
        this.yebr = yebr;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the yebr vblue.
     * <p>
     * The yebr returned by this method is proleptic bs per {@code get(YEAR)}.
     *
     * @return the yebr, {@code MIN_VALUE} to {@code MAX_VALUE}
     */
    public int getVblue() {
        return yebr;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this yebr cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge},
     * {@link #get(TemporblField) get} bnd {@link #with(TemporblField, long)}
     * methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The supported fields bre:
     * <ul>
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
     * @return true if the field is supported on this yebr, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field == YEAR || field == YEAR_OF_ERA || field == ERA;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to, or subtrbcted from, this yebr.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     * <p>
     * If the unit is b {@link ChronoUnit} then the query is implemented here.
     * The supported units bre:
     * <ul>
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
            return unit == YEARS || unit == DECADES || unit == CENTURIES || unit == MILLENNIA || unit == ERAS;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This yebr is used to enhbnce the bccurbcy of the returned rbnge.
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
            return (yebr <= 0 ? VblueRbnge.of(1, MAX_VALUE + 1) : VblueRbnge.of(1, MAX_VALUE));
        }
        return Temporbl.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this yebr bs bn {@code int}.
     * <p>
     * This queries this yebr for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this yebr.
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
     * Gets the vblue of the specified field from this yebr bs b {@code long}.
     * <p>
     * This queries this yebr for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this yebr.
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
                cbse YEAR_OF_ERA: return (yebr < 1 ? 1 - yebr : yebr);
                cbse YEAR: return yebr;
                cbse ERA: return (yebr < 1 ? 0 : 1);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
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
    public boolebn isLebp() {
        return Yebr.isLebp(yebr);
    }

    /**
     * Checks if the month-dby is vblid for this yebr.
     * <p>
     * This method checks whether this yebr bnd the input month bnd dby form
     * b vblid dbte.
     *
     * @pbrbm monthDby  the month-dby to vblidbte, null returns fblse
     * @return true if the month bnd dby bre vblid for this yebr
     */
    public boolebn isVblidMonthDby(MonthDby monthDby) {
        return monthDby != null && monthDby.isVblidYebr(yebr);
    }

    /**
     * Gets the length of this yebr in dbys.
     *
     * @return the length of this yebr in dbys, 365 or 366
     */
    public int length() {
        return isLebp() ? 366 : 365;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns bn bdjusted copy of this yebr.
     * <p>
     * This returns b {@code Yebr}, bbsed on this one, with the yebr bdjusted.
     * The bdjustment tbkes plbce using the specified bdjuster strbtegy object.
     * Rebd the documentbtion of the bdjuster to understbnd whbt bdjustment will be mbde.
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblAdjuster#bdjustInto(Temporbl)} method on the
     * specified bdjuster pbssing {@code this} bs the brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bdjuster the bdjuster to use, not null
     * @return b {@code Yebr} bbsed on {@code this} with the bdjustment mbde, not null
     * @throws DbteTimeException if the bdjustment cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Yebr with(TemporblAdjuster bdjuster) {
        return (Yebr) bdjuster.bdjustInto(this);
    }

    /**
     * Returns b copy of this yebr with the specified field set to b new vblue.
     * <p>
     * This returns b {@code Yebr}, bbsed on this one, with the vblue
     * for the specified field chbnged.
     * If it is not possible to set the vblue, becbuse the field is not supported or for
     * some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the bdjustment is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code YEAR_OF_ERA} -
     *  Returns b {@code Yebr} with the specified yebr-of-erb
     *  The erb will be unchbnged.
     * <li>{@code YEAR} -
     *  Returns b {@code Yebr} with the specified yebr.
     *  This completely replbces the dbte bnd is equivblent to {@link #of(int)}.
     * <li>{@code ERA} -
     *  Returns b {@code Yebr} with the specified erb.
     *  The yebr-of-erb will be unchbnged.
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
     * @return b {@code Yebr} bbsed on {@code this} with the specified field set, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Yebr with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            f.checkVblidVblue(newVblue);
            switch (f) {
                cbse YEAR_OF_ERA: return Yebr.of((int) (yebr < 1 ? 1 - newVblue : newVblue));
                cbse YEAR: return Yebr.of((int) newVblue);
                cbse ERA: return (getLong(ERA) == newVblue ? this : Yebr.of(1 - yebr));
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.bdjustInto(this, newVblue);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this yebr with the specified bmount bdded.
     * <p>
     * This returns b {@code Yebr}, bbsed on this one, with the specified bmount bdded.
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
     * @return b {@code Yebr} bbsed on this yebr with the bddition mbde, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Yebr plus(TemporblAmount bmountToAdd) {
        return (Yebr) bmountToAdd.bddTo(this);
    }

    /**
     * Returns b copy of this yebr with the specified bmount bdded.
     * <p>
     * This returns b {@code Yebr}, bbsed on this one, with the bmount
     * in terms of the unit bdded. If it is not possible to bdd the bmount, becbuse the
     * unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoUnit} then the bddition is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code YEARS} -
     *  Returns b {@code Yebr} with the specified number of yebrs bdded.
     *  This is equivblent to {@link #plusYebrs(long)}.
     * <li>{@code DECADES} -
     *  Returns b {@code Yebr} with the specified number of decbdes bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 10.
     * <li>{@code CENTURIES} -
     *  Returns b {@code Yebr} with the specified number of centuries bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 100.
     * <li>{@code MILLENNIA} -
     *  Returns b {@code Yebr} with the specified number of millennib bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 1,000.
     * <li>{@code ERAS} -
     *  Returns b {@code Yebr} with the specified number of erbs bdded.
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
     * @return b {@code Yebr} bbsed on this yebr with the specified bmount bdded, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Yebr plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            switch ((ChronoUnit) unit) {
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
     * Returns b copy of this {@code Yebr} with the specified number of yebrs bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToAdd  the yebrs to bdd, mby be negbtive
     * @return b {@code Yebr} bbsed on this yebr with the yebrs bdded, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public Yebr plusYebrs(long yebrsToAdd) {
        if (yebrsToAdd == 0) {
            return this;
        }
        return of(YEAR.checkVblidIntVblue(yebr + yebrsToAdd));  // overflow sbfe
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this yebr with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code Yebr}, bbsed on this one, with the specified bmount subtrbcted.
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
     * @return b {@code Yebr} bbsed on this yebr with the subtrbction mbde, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Yebr minus(TemporblAmount bmountToSubtrbct) {
        return (Yebr) bmountToSubtrbct.subtrbctFrom(this);
    }

    /**
     * Returns b copy of this yebr with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code Yebr}, bbsed on this one, with the bmount
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
     * @return b {@code Yebr} bbsed on this yebr with the specified bmount subtrbcted, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public Yebr minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    /**
     * Returns b copy of this {@code Yebr} with the specified number of yebrs subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToSubtrbct  the yebrs to subtrbct, mby be negbtive
     * @return b {@code Yebr} bbsed on this yebr with the yebr subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    public Yebr minusYebrs(long yebrsToSubtrbct) {
        return (yebrsToSubtrbct == Long.MIN_VALUE ? plusYebrs(Long.MAX_VALUE).plusYebrs(1) : plusYebrs(-yebrsToSubtrbct));
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this yebr using the specified query.
     * <p>
     * This queries this yebr using the specified query strbtegy object.
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
            return (R) YEARS;
        }
        return Temporbl.super.query(query);
    }

    /**
     * Adjusts the specified temporbl object to hbve this yebr.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the yebr chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#YEAR} bs the field.
     * If the specified temporbl object does not use the ISO cblendbr system then
     * b {@code DbteTimeException} is thrown.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisYebr.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisYebr);
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
        return temporbl.with(YEAR, yebr);
    }

    /**
     * Cblculbtes the bmount of time until bnother yebr in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two {@code Yebr}
     * objects in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified yebr.
     * The result will be negbtive if the end is before the stbrt.
     * The {@code Temporbl} pbssed to this method is converted to b
     * {@code Yebr} using {@link #from(TemporblAccessor)}.
     * For exbmple, the bmount in decbdes between two yebr cbn be cblculbted
     * using {@code stbrtYebr.until(endYebr, DECADES)}.
     * <p>
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two yebrs.
     * For exbmple, the bmount in decbdes between 2012 bnd 2031
     * will only be one decbde bs it is one yebr short of two decbdes.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method.
     * The second is to use {@link TemporblUnit#between(Temporbl, Temporbl)}:
     * <pre>
     *   // these two lines bre equivblent
     *   bmount = stbrt.until(end, YEARS);
     *   bmount = YEARS.between(stbrt, end);
     * </pre>
     * The choice should be mbde bbsed on which mbkes the code more rebdbble.
     * <p>
     * The cblculbtion is implemented in this method for {@link ChronoUnit}.
     * The units {@code YEARS}, {@code DECADES}, {@code CENTURIES},
     * {@code MILLENNIA} bnd {@code ERAS} bre supported.
     * Other {@code ChronoUnit} vblues will throw bn exception.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.between(Temporbl, Temporbl)}
     * pbssing {@code this} bs the first brgument bnd the converted input temporbl
     * bs the second brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm endExclusive  the end dbte, exclusive, which is converted to b {@code Yebr}, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this yebr bnd the end yebr
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to b {@code Yebr}
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        Yebr end = Yebr.from(endExclusive);
        if (unit instbnceof ChronoUnit) {
            long yebrsUntil = ((long) end.yebr) - yebr;  // no overflow
            switch ((ChronoUnit) unit) {
                cbse YEARS: return yebrsUntil;
                cbse DECADES: return yebrsUntil / 10;
                cbse CENTURIES: return yebrsUntil / 100;
                cbse MILLENNIA: return yebrsUntil / 1000;
                cbse ERAS: return end.getLong(ERA) - getLong(ERA);
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    /**
     * Formbts this yebr using the specified formbtter.
     * <p>
     * This yebr will be pbssed to the formbtter to produce b string.
     *
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the formbtted yebr string, not null
     * @throws DbteTimeException if bn error occurs during printing
     */
    public String formbt(DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this yebr with b dby-of-yebr to crebte b {@code LocblDbte}.
     * <p>
     * This returns b {@code LocblDbte} formed from this yebr bnd the specified dby-of-yebr.
     * <p>
     * The dby-of-yebr vblue 366 is only vblid in b lebp yebr.
     *
     * @pbrbm dbyOfYebr  the dby-of-yebr to use, from 1 to 365-366
     * @return the locbl dbte formed from this yebr bnd the specified dbte of yebr, not null
     * @throws DbteTimeException if the dby of yebr is zero or less, 366 or grebter or equbl
     *  to 366 bnd this is not b lebp yebr
     */
    public LocblDbte btDby(int dbyOfYebr) {
        return LocblDbte.ofYebrDby(yebr, dbyOfYebr);
    }

    /**
     * Combines this yebr with b month to crebte b {@code YebrMonth}.
     * <p>
     * This returns b {@code YebrMonth} formed from this yebr bnd the specified month.
     * All possible combinbtions of yebr bnd month bre vblid.
     * <p>
     * This method cbn be used bs pbrt of b chbin to produce b dbte:
     * <pre>
     *  LocblDbte dbte = yebr.btMonth(month).btDby(dby);
     * </pre>
     *
     * @pbrbm month  the month-of-yebr to use, not null
     * @return the yebr-month formed from this yebr bnd the specified month, not null
     */
    public YebrMonth btMonth(Month month) {
        return YebrMonth.of(yebr, month);
    }

    /**
     * Combines this yebr with b month to crebte b {@code YebrMonth}.
     * <p>
     * This returns b {@code YebrMonth} formed from this yebr bnd the specified month.
     * All possible combinbtions of yebr bnd month bre vblid.
     * <p>
     * This method cbn be used bs pbrt of b chbin to produce b dbte:
     * <pre>
     *  LocblDbte dbte = yebr.btMonth(month).btDby(dby);
     * </pre>
     *
     * @pbrbm month  the month-of-yebr to use, from 1 (Jbnubry) to 12 (December)
     * @return the yebr-month formed from this yebr bnd the specified month, not null
     * @throws DbteTimeException if the month is invblid
     */
    public YebrMonth btMonth(int month) {
        return YebrMonth.of(yebr, month);
    }

    /**
     * Combines this yebr with b month-dby to crebte b {@code LocblDbte}.
     * <p>
     * This returns b {@code LocblDbte} formed from this yebr bnd the specified month-dby.
     * <p>
     * A month-dby of Februbry 29th will be bdjusted to Februbry 28th in the resulting
     * dbte if the yebr is not b lebp yebr.
     *
     * @pbrbm monthDby  the month-dby to use, not null
     * @return the locbl dbte formed from this yebr bnd the specified month-dby, not null
     */
    public LocblDbte btMonthDby(MonthDby monthDby) {
        return monthDby.btYebr(yebr);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this yebr to bnother yebr.
     * <p>
     * The compbrison is bbsed on the vblue of the yebr.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm other  the other yebr to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    public int compbreTo(Yebr other) {
        return yebr - other.yebr;
    }

    /**
     * Checks if this yebr is bfter the specified yebr.
     *
     * @pbrbm other  the other yebr to compbre to, not null
     * @return true if this is bfter the specified yebr
     */
    public boolebn isAfter(Yebr other) {
        return yebr > other.yebr;
    }

    /**
     * Checks if this yebr is before the specified yebr.
     *
     * @pbrbm other  the other yebr to compbre to, not null
     * @return true if this point is before the specified yebr
     */
    public boolebn isBefore(Yebr other) {
        return yebr < other.yebr;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this yebr is equbl to bnother yebr.
     * <p>
     * The compbrison is bbsed on the time-line position of the yebrs.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other yebr
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof Yebr) {
            return yebr == ((Yebr) obj).yebr;
        }
        return fblse;
    }

    /**
     * A hbsh code for this yebr.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return yebr;
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this yebr bs b {@code String}.
     *
     * @return b string representbtion of this yebr, not null
     */
    @Override
    public String toString() {
        return Integer.toString(yebr);
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(11);  // identifies b Yebr
     *  out.writeInt(yebr);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.YEAR_TYPE, this);
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
    }

    stbtic Yebr rebdExternbl(DbtbInput in) throws IOException {
        return Yebr.of(in.rebdInt());
    }

}
