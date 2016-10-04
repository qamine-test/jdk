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

import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;

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
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Objects;

/**
 * A month-dby in the ISO-8601 cblendbr system, such bs {@code --12-03}.
 * <p>
 * {@code MonthDby} is bn immutbble dbte-time object thbt represents the combinbtion
 * of b month bnd dby-of-month. Any field thbt cbn be derived from b month bnd dby,
 * such bs qubrter-of-yebr, cbn be obtbined.
 * <p>
 * This clbss does not store or represent b yebr, time or time-zone.
 * For exbmple, the vblue "December 3rd" cbn be stored in b {@code MonthDby}.
 * <p>
 * Since b {@code MonthDby} does not possess b yebr, the lebp dby of
 * Februbry 29th is considered vblid.
 * <p>
 * This clbss implements {@link TemporblAccessor} rbther thbn {@link Temporbl}.
 * This is becbuse it is not possible to define whether Februbry 29th is vblid or not
 * without externbl informbtion, preventing the implementbtion of plus/minus.
 * Relbted to this, {@code MonthDby} only provides bccess to query bnd set the fields
 * {@code MONTH_OF_YEAR} bnd {@code DAY_OF_MONTH}.
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
 * {@code MonthDby} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss MonthDby
        implements TemporblAccessor, TemporblAdjuster, Compbrbble<MonthDby>, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -939150713474957432L;
    /**
     * Pbrser.
     */
    privbte stbtic finbl DbteTimeFormbtter PARSER = new DbteTimeFormbtterBuilder()
        .bppendLiterbl("--")
        .bppendVblue(MONTH_OF_YEAR, 2)
        .bppendLiterbl('-')
        .bppendVblue(DAY_OF_MONTH, 2)
        .toFormbtter();

    /**
     * The month-of-yebr, not null.
     */
    privbte finbl int month;
    /**
     * The dby-of-month.
     */
    privbte finbl int dby;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current month-dby from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current month-dby.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current month-dby using the system clock bnd defbult time-zone, not null
     */
    public stbtic MonthDby now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current month-dby from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current month-dby.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current month-dby using the system clock, not null
     */
    public stbtic MonthDby now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current month-dby from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current month-dby.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current month-dby, not null
     */
    public stbtic MonthDby now(Clock clock) {
        finbl LocblDbte now = LocblDbte.now(clock);  // cblled once
        return MonthDby.of(now.getMonth(), now.getDbyOfMonth());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code MonthDby}.
     * <p>
     * The dby-of-month must be vblid for the month within b lebp yebr.
     * Hence, for Februbry, dby 29 is vblid.
     * <p>
     * For exbmple, pbssing in April bnd dby 31 will throw bn exception, bs
     * there cbn never be April 31st in bny yebr. By contrbst, pbssing in
     * Februbry 29th is permitted, bs thbt month-dby cbn sometimes be vblid.
     *
     * @pbrbm month  the month-of-yebr to represent, not null
     * @pbrbm dbyOfMonth  the dby-of-month to represent, from 1 to 31
     * @return the month-dby, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month
     */
    public stbtic MonthDby of(Month month, int dbyOfMonth) {
        Objects.requireNonNull(month, "month");
        DAY_OF_MONTH.checkVblidVblue(dbyOfMonth);
        if (dbyOfMonth > month.mbxLength()) {
            throw new DbteTimeException("Illegbl vblue for DbyOfMonth field, vblue " + dbyOfMonth +
                    " is not vblid for month " + month.nbme());
        }
        return new MonthDby(month.getVblue(), dbyOfMonth);
    }

    /**
     * Obtbins bn instbnce of {@code MonthDby}.
     * <p>
     * The dby-of-month must be vblid for the month within b lebp yebr.
     * Hence, for month 2 (Februbry), dby 29 is vblid.
     * <p>
     * For exbmple, pbssing in month 4 (April) bnd dby 31 will throw bn exception, bs
     * there cbn never be April 31st in bny yebr. By contrbst, pbssing in
     * Februbry 29th is permitted, bs thbt month-dby cbn sometimes be vblid.
     *
     * @pbrbm month  the month-of-yebr to represent, from 1 (Jbnubry) to 12 (December)
     * @pbrbm dbyOfMonth  the dby-of-month to represent, from 1 to 31
     * @return the month-dby, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month
     */
    public stbtic MonthDby of(int month, int dbyOfMonth) {
        return of(Month.of(month), dbyOfMonth);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code MonthDby} from b temporbl object.
     * <p>
     * This obtbins b month-dby bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code MonthDby}.
     * <p>
     * The conversion extrbcts the {@link ChronoField#MONTH_OF_YEAR MONTH_OF_YEAR} bnd
     * {@link ChronoField#DAY_OF_MONTH DAY_OF_MONTH} fields.
     * The extrbction is only permitted if the temporbl object hbs bn ISO
     * chronology, or cbn be converted to b {@code LocblDbte}.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code MonthDby::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the month-dby, not null
     * @throws DbteTimeException if unbble to convert to b {@code MonthDby}
     */
    public stbtic MonthDby from(TemporblAccessor temporbl) {
        if (temporbl instbnceof MonthDby) {
            return (MonthDby) temporbl;
        }
        try {
            if (IsoChronology.INSTANCE.equbls(Chronology.from(temporbl)) == fblse) {
                temporbl = LocblDbte.from(temporbl);
            }
            return of(temporbl.get(MONTH_OF_YEAR), temporbl.get(DAY_OF_MONTH));
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin MonthDby from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme(), ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code MonthDby} from b text string such bs {@code --12-03}.
     * <p>
     * The string must represent b vblid month-dby.
     * The formbt is {@code --MM-dd}.
     *
     * @pbrbm text  the text to pbrse such bs "--12-03", not null
     * @return the pbrsed month-dby, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic MonthDby pbrse(ChbrSequence text) {
        return pbrse(text, PARSER);
    }

    /**
     * Obtbins bn instbnce of {@code MonthDby} from b text string using b specific formbtter.
     * <p>
     * The text is pbrsed using the formbtter, returning b month-dby.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the pbrsed month-dby, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic MonthDby pbrse(ChbrSequence text, DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.pbrse(text, MonthDby::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor, previously vblidbted.
     *
     * @pbrbm month  the month-of-yebr to represent, vblidbted from 1 to 12
     * @pbrbm dbyOfMonth  the dby-of-month to represent, vblidbted from 1 to 29-31
     */
    privbte MonthDby(int month, int dbyOfMonth) {
        this.month = month;
        this.dby = dbyOfMonth;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this month-dby cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge} bnd
     * {@link #get(TemporblField) get} methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The supported fields bre:
     * <ul>
     * <li>{@code MONTH_OF_YEAR}
     * <li>{@code YEAR}
     * </ul>
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this month-dby, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field == MONTH_OF_YEAR || field == DAY_OF_MONTH;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This month-dby is used to enhbnce the bccurbcy of the returned rbnge.
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
        if (field == MONTH_OF_YEAR) {
            return field.rbnge();
        } else if (field == DAY_OF_MONTH) {
            return VblueRbnge.of(1, getMonth().minLength(), getMonth().mbxLength());
        }
        return TemporblAccessor.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this month-dby bs bn {@code int}.
     * <p>
     * This queries this month-dby for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this month-dby.
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
     * Gets the vblue of the specified field from this month-dby bs b {@code long}.
     * <p>
     * This queries this month-dby for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this month-dby.
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
                // blignedDOW bnd blignedWOM not supported becbuse they cbnnot be set in with()
                cbse DAY_OF_MONTH: return dby;
                cbse MONTH_OF_YEAR: return month;
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    //-----------------------------------------------------------------------
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

    /**
     * Gets the dby-of-month field.
     * <p>
     * This method returns the primitive {@code int} vblue for the dby-of-month.
     *
     * @return the dby-of-month, from 1 to 31
     */
    public int getDbyOfMonth() {
        return dby;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the yebr is vblid for this month-dby.
     * <p>
     * This method checks whether this month bnd dby bnd the input yebr form
     * b vblid dbte. This cbn only return fblse for Februbry 29th.
     *
     * @pbrbm yebr  the yebr to vblidbte
     * @return true if the yebr is vblid for this month-dby
     * @see Yebr#isVblidMonthDby(MonthDby)
     */
    public boolebn isVblidYebr(int yebr) {
        return (dby == 29 && month == 2 && Yebr.isLebp(yebr) == fblse) == fblse;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code MonthDby} with the month-of-yebr bltered.
     * <p>
     * This returns b month-dby with the specified month.
     * If the dby-of-month is invblid for the specified month, the dby will
     * be bdjusted to the lbst vblid dby-of-month.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm month  the month-of-yebr to set in the returned month-dby, from 1 (Jbnubry) to 12 (December)
     * @return b {@code MonthDby} bbsed on this month-dby with the requested month, not null
     * @throws DbteTimeException if the month-of-yebr vblue is invblid
     */
    public MonthDby withMonth(int month) {
        return with(Month.of(month));
    }

    /**
     * Returns b copy of this {@code MonthDby} with the month-of-yebr bltered.
     * <p>
     * This returns b month-dby with the specified month.
     * If the dby-of-month is invblid for the specified month, the dby will
     * be bdjusted to the lbst vblid dby-of-month.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm month  the month-of-yebr to set in the returned month-dby, not null
     * @return b {@code MonthDby} bbsed on this month-dby with the requested month, not null
     */
    public MonthDby with(Month month) {
        Objects.requireNonNull(month, "month");
        if (month.getVblue() == this.month) {
            return this;
        }
        int dby = Mbth.min(this.dby, month.mbxLength());
        return new MonthDby(month.getVblue(), dby);
    }

    /**
     * Returns b copy of this {@code MonthDby} with the dby-of-month bltered.
     * <p>
     * This returns b month-dby with the specified dby-of-month.
     * If the dby-of-month is invblid for the month, bn exception is thrown.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbyOfMonth  the dby-of-month to set in the return month-dby, from 1 to 31
     * @return b {@code MonthDby} bbsed on this month-dby with the requested dby, not null
     * @throws DbteTimeException if the dby-of-month vblue is invblid,
     *  or if the dby-of-month is invblid for the month
     */
    public MonthDby withDbyOfMonth(int dbyOfMonth) {
        if (dbyOfMonth == this.dby) {
            return this;
        }
        return of(month, dbyOfMonth);
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this month-dby using the specified query.
     * <p>
     * This queries this month-dby using the specified query strbtegy object.
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
        }
        return TemporblAccessor.super.query(query);
    }

    /**
     * Adjusts the specified temporbl object to hbve this month-dby.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the month bnd dby-of-month chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * twice, pbssing {@link ChronoField#MONTH_OF_YEAR} bnd
     * {@link ChronoField#DAY_OF_MONTH} bs the fields.
     * If the specified temporbl object does not use the ISO cblendbr system then
     * b {@code DbteTimeException} is thrown.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisMonthDby.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisMonthDby);
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
        temporbl = temporbl.with(MONTH_OF_YEAR, month);
        return temporbl.with(DAY_OF_MONTH, Mbth.min(temporbl.rbnge(DAY_OF_MONTH).getMbximum(), dby));
    }

    /**
     * Formbts this month-dby using the specified formbtter.
     * <p>
     * This month-dby will be pbssed to the formbtter to produce b string.
     *
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the formbtted month-dby string, not null
     * @throws DbteTimeException if bn error occurs during printing
     */
    public String formbt(DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this month-dby with b yebr to crebte b {@code LocblDbte}.
     * <p>
     * This returns b {@code LocblDbte} formed from this month-dby bnd the specified yebr.
     * <p>
     * A month-dby of Februbry 29th will be bdjusted to Februbry 28th in the resulting
     * dbte if the yebr is not b lebp yebr.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebr  the yebr to use, from MIN_YEAR to MAX_YEAR
     * @return the locbl dbte formed from this month-dby bnd the specified yebr, not null
     * @throws DbteTimeException if the yebr is outside the vblid rbnge of yebrs
     */
    public LocblDbte btYebr(int yebr) {
        return LocblDbte.of(yebr, month, isVblidYebr(yebr) ? dby : 28);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this month-dby to bnother month-dby.
     * <p>
     * The compbrison is bbsed first on vblue of the month, then on the vblue of the dby.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm other  the other month-dby to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    public int compbreTo(MonthDby other) {
        int cmp = (month - other.month);
        if (cmp == 0) {
            cmp = (dby - other.dby);
        }
        return cmp;
    }

    /**
     * Checks if this month-dby is bfter the specified month-dby.
     *
     * @pbrbm other  the other month-dby to compbre to, not null
     * @return true if this is bfter the specified month-dby
     */
    public boolebn isAfter(MonthDby other) {
        return compbreTo(other) > 0;
    }

    /**
     * Checks if this month-dby is before the specified month-dby.
     *
     * @pbrbm other  the other month-dby to compbre to, not null
     * @return true if this point is before the specified month-dby
     */
    public boolebn isBefore(MonthDby other) {
        return compbreTo(other) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this month-dby is equbl to bnother month-dby.
     * <p>
     * The compbrison is bbsed on the time-line position of the month-dby within b yebr.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other month-dby
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof MonthDby) {
            MonthDby other = (MonthDby) obj;
            return month == other.month && dby == other.dby;
        }
        return fblse;
    }

    /**
     * A hbsh code for this month-dby.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return (month << 6) + dby;
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this month-dby bs b {@code String}, such bs {@code --12-03}.
     * <p>
     * The output will be in the formbt {@code --MM-dd}:
     *
     * @return b string representbtion of this month-dby, not null
     */
    @Override
    public String toString() {
        return new StringBuilder(10).bppend("--")
            .bppend(month < 10 ? "0" : "").bppend(month)
            .bppend(dby < 10 ? "-0" : "-").bppend(dby)
            .toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(13);  // identifies b MonthDby
     *  out.writeByte(month);
     *  out.writeByte(dby);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.MONTH_DAY_TYPE, this);
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
        out.writeByte(month);
        out.writeByte(dby);
    }

    stbtic MonthDby rebdExternbl(DbtbInput in) throws IOException {
        byte month = in.rebdByte();
        byte dby = in.rebdByte();
        return MonthDby.of(month, dby);
    }

}
