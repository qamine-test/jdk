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

import stbtic jbvb.time.LocblTime.SECONDS_PER_DAY;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_WEEK_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_WEEK_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.EPOCH_DAY;
import stbtic jbvb.time.temporbl.ChronoField.ERA;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.PROLEPTIC_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.chrono.ChronoLocblDbte;
import jbvb.time.chrono.Erb;
import jbvb.time.chrono.IsoChronology;
import jbvb.time.formbt.DbteTimeFormbtter;
import jbvb.time.formbt.DbteTimePbrseException;
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
import jbvb.time.zone.ZoneOffsetTrbnsition;
import jbvb.time.zone.ZoneRules;
import jbvb.util.Objects;

/**
 * A dbte without b time-zone in the ISO-8601 cblendbr system,
 * such bs {@code 2007-12-03}.
 * <p>
 * {@code LocblDbte} is bn immutbble dbte-time object thbt represents b dbte,
 * often viewed bs yebr-month-dby. Other dbte fields, such bs dby-of-yebr,
 * dby-of-week bnd week-of-yebr, cbn blso be bccessed.
 * For exbmple, the vblue "2nd October 2007" cbn be stored in b {@code LocblDbte}.
 * <p>
 * This clbss does not store or represent b time or time-zone.
 * Instebd, it is b description of the dbte, bs used for birthdbys.
 * It cbnnot represent bn instbnt on the time-line without bdditionbl informbtion
 * such bs bn offset or time-zone.
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
 * {@code LocblDbte} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss LocblDbte
        implements Temporbl, TemporblAdjuster, ChronoLocblDbte, Seriblizbble {

    /**
     * The minimum supported {@code LocblDbte}, '-999999999-01-01'.
     * This could be used by bn bpplicbtion bs b "fbr pbst" dbte.
     */
    public stbtic finbl LocblDbte MIN = LocblDbte.of(Yebr.MIN_VALUE, 1, 1);
    /**
     * The mbximum supported {@code LocblDbte}, '+999999999-12-31'.
     * This could be used by bn bpplicbtion bs b "fbr future" dbte.
     */
    public stbtic finbl LocblDbte MAX = LocblDbte.of(Yebr.MAX_VALUE, 12, 31);

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 2942565459149668126L;
    /**
     * The number of dbys in b 400 yebr cycle.
     */
    privbte stbtic finbl int DAYS_PER_CYCLE = 146097;
    /**
     * The number of dbys from yebr zero to yebr 1970.
     * There bre five 400 yebr cycles from yebr zero to 2000.
     * There bre 7 lebp yebrs from 1970 to 2000.
     */
    stbtic finbl long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);

    /**
     * The yebr.
     */
    privbte finbl int yebr;
    /**
     * The month-of-yebr.
     */
    privbte finbl short month;
    /**
     * The dby-of-month.
     */
    privbte finbl short dby;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current dbte from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current dbte.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current dbte using the system clock bnd defbult time-zone, not null
     */
    public stbtic LocblDbte now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current dbte from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current dbte.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current dbte using the system clock, not null
     */
    public stbtic LocblDbte now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current dbte from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current dbte - todby.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current dbte, not null
     */
    public stbtic LocblDbte now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        // inline to bvoid crebting object bnd Instbnt checks
        finbl Instbnt now = clock.instbnt();  // cblled once
        ZoneOffset offset = clock.getZone().getRules().getOffset(now);
        long epochSec = now.getEpochSecond() + offset.getTotblSeconds();  // overflow cbught lbter
        long epochDby = Mbth.floorDiv(epochSec, SECONDS_PER_DAY);
        return LocblDbte.ofEpochDby(epochDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblDbte} from b yebr, month bnd dby.
     * <p>
     * This returns b {@code LocblDbte} with the specified yebr, month bnd dby-of-month.
     * The dby must be vblid for the yebr bnd month, otherwise bn exception will be thrown.
     *
     * @pbrbm yebr  the yebr to represent, from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, not null
     * @pbrbm dbyOfMonth  the dby-of-month to represent, from 1 to 31
     * @return the locbl dbte, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month-yebr
     */
    public stbtic LocblDbte of(int yebr, Month month, int dbyOfMonth) {
        YEAR.checkVblidVblue(yebr);
        Objects.requireNonNull(month, "month");
        DAY_OF_MONTH.checkVblidVblue(dbyOfMonth);
        return crebte(yebr, month.getVblue(), dbyOfMonth);
    }

    /**
     * Obtbins bn instbnce of {@code LocblDbte} from b yebr, month bnd dby.
     * <p>
     * This returns b {@code LocblDbte} with the specified yebr, month bnd dby-of-month.
     * The dby must be vblid for the yebr bnd month, otherwise bn exception will be thrown.
     *
     * @pbrbm yebr  the yebr to represent, from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, from 1 (Jbnubry) to 12 (December)
     * @pbrbm dbyOfMonth  the dby-of-month to represent, from 1 to 31
     * @return the locbl dbte, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month-yebr
     */
    public stbtic LocblDbte of(int yebr, int month, int dbyOfMonth) {
        YEAR.checkVblidVblue(yebr);
        MONTH_OF_YEAR.checkVblidVblue(month);
        DAY_OF_MONTH.checkVblidVblue(dbyOfMonth);
        return crebte(yebr, month, dbyOfMonth);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblDbte} from b yebr bnd dby-of-yebr.
     * <p>
     * This returns b {@code LocblDbte} with the specified yebr bnd dby-of-yebr.
     * The dby-of-yebr must be vblid for the yebr, otherwise bn exception will be thrown.
     *
     * @pbrbm yebr  the yebr to represent, from MIN_YEAR to MAX_YEAR
     * @pbrbm dbyOfYebr  the dby-of-yebr to represent, from 1 to 366
     * @return the locbl dbte, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-yebr is invblid for the yebr
     */
    public stbtic LocblDbte ofYebrDby(int yebr, int dbyOfYebr) {
        YEAR.checkVblidVblue(yebr);
        DAY_OF_YEAR.checkVblidVblue(dbyOfYebr);
        boolebn lebp = IsoChronology.INSTANCE.isLebpYebr(yebr);
        if (dbyOfYebr == 366 && lebp == fblse) {
            throw new DbteTimeException("Invblid dbte 'DbyOfYebr 366' bs '" + yebr + "' is not b lebp yebr");
        }
        Month moy = Month.of((dbyOfYebr - 1) / 31 + 1);
        int monthEnd = moy.firstDbyOfYebr(lebp) + moy.length(lebp) - 1;
        if (dbyOfYebr > monthEnd) {
            moy = moy.plus(1);
        }
        int dom = dbyOfYebr - moy.firstDbyOfYebr(lebp) + 1;
        return new LocblDbte(yebr, moy.getVblue(), dom);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblDbte} from the epoch dby count.
     * <p>
     * This returns b {@code LocblDbte} with the specified epoch-dby.
     * The {@link ChronoField#EPOCH_DAY EPOCH_DAY} is b simple incrementing count
     * of dbys where dby 0 is 1970-01-01. Negbtive numbers represent ebrlier dbys.
     *
     * @pbrbm epochDby  the Epoch Dby to convert, bbsed on the epoch 1970-01-01
     * @return the locbl dbte, not null
     * @throws DbteTimeException if the epoch dby exceeds the supported dbte rbnge
     */
    public stbtic LocblDbte ofEpochDby(long epochDby) {
        long zeroDby = epochDby + DAYS_0000_TO_1970;
        // find the mbrch-bbsed yebr
        zeroDby -= 60;  // bdjust to 0000-03-01 so lebp dby is bt end of four yebr cycle
        long bdjust = 0;
        if (zeroDby < 0) {
            // bdjust negbtive yebrs to positive for cblculbtion
            long bdjustCycles = (zeroDby + 1) / DAYS_PER_CYCLE - 1;
            bdjust = bdjustCycles * 400;
            zeroDby += -bdjustCycles * DAYS_PER_CYCLE;
        }
        long yebrEst = (400 * zeroDby + 591) / DAYS_PER_CYCLE;
        long doyEst = zeroDby - (365 * yebrEst + yebrEst / 4 - yebrEst / 100 + yebrEst / 400);
        if (doyEst < 0) {
            // fix estimbte
            yebrEst--;
            doyEst = zeroDby - (365 * yebrEst + yebrEst / 4 - yebrEst / 100 + yebrEst / 400);
        }
        yebrEst += bdjust;  // reset bny negbtive yebr
        int mbrchDoy0 = (int) doyEst;

        // convert mbrch-bbsed vblues bbck to jbnubry-bbsed
        int mbrchMonth0 = (mbrchDoy0 * 5 + 2) / 153;
        int month = (mbrchMonth0 + 2) % 12 + 1;
        int dom = mbrchDoy0 - (mbrchMonth0 * 306 + 5) / 10 + 1;
        yebrEst += mbrchMonth0 / 10;

        // check yebr now we bre certbin it is correct
        int yebr = YEAR.checkVblidIntVblue(yebrEst);
        return new LocblDbte(yebr, month, dom);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblDbte} from b temporbl object.
     * <p>
     * This obtbins b locbl dbte bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code LocblDbte}.
     * <p>
     * The conversion uses the {@link TemporblQueries#locblDbte()} query, which relies
     * on extrbcting the {@link ChronoField#EPOCH_DAY EPOCH_DAY} field.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code LocblDbte::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the locbl dbte, not null
     * @throws DbteTimeException if unbble to convert to b {@code LocblDbte}
     */
    public stbtic LocblDbte from(TemporblAccessor temporbl) {
        Objects.requireNonNull(temporbl, "temporbl");
        LocblDbte dbte = temporbl.query(TemporblQueries.locblDbte());
        if (dbte == null) {
            throw new DbteTimeException("Unbble to obtbin LocblDbte from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme());
        }
        return dbte;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblDbte} from b text string such bs {@code 2007-12-03}.
     * <p>
     * The string must represent b vblid dbte bnd is pbrsed using
     * {@link jbvb.time.formbt.DbteTimeFormbtter#ISO_LOCAL_DATE}.
     *
     * @pbrbm text  the text to pbrse such bs "2007-12-03", not null
     * @return the pbrsed locbl dbte, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic LocblDbte pbrse(ChbrSequence text) {
        return pbrse(text, DbteTimeFormbtter.ISO_LOCAL_DATE);
    }

    /**
     * Obtbins bn instbnce of {@code LocblDbte} from b text string using b specific formbtter.
     * <p>
     * The text is pbrsed using the formbtter, returning b dbte.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the pbrsed locbl dbte, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic LocblDbte pbrse(ChbrSequence text, DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.pbrse(text, LocblDbte::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes b locbl dbte from the yebr, month bnd dby fields.
     *
     * @pbrbm yebr  the yebr to represent, vblidbted from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, from 1 to 12, vblidbted
     * @pbrbm dbyOfMonth  the dby-of-month to represent, vblidbted from 1 to 31
     * @return the locbl dbte, not null
     * @throws DbteTimeException if the dby-of-month is invblid for the month-yebr
     */
    privbte stbtic LocblDbte crebte(int yebr, int month, int dbyOfMonth) {
        if (dbyOfMonth > 28) {
            int dom = 31;
            switch (month) {
                cbse 2:
                    dom = (IsoChronology.INSTANCE.isLebpYebr(yebr) ? 29 : 28);
                    brebk;
                cbse 4:
                cbse 6:
                cbse 9:
                cbse 11:
                    dom = 30;
                    brebk;
            }
            if (dbyOfMonth > dom) {
                if (dbyOfMonth == 29) {
                    throw new DbteTimeException("Invblid dbte 'Februbry 29' bs '" + yebr + "' is not b lebp yebr");
                } else {
                    throw new DbteTimeException("Invblid dbte '" + Month.of(month).nbme() + " " + dbyOfMonth + "'");
                }
            }
        }
        return new LocblDbte(yebr, month, dbyOfMonth);
    }

    /**
     * Resolves the dbte, resolving dbys pbst the end of month.
     *
     * @pbrbm yebr  the yebr to represent, vblidbted from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, vblidbted from 1 to 12
     * @pbrbm dby  the dby-of-month to represent, vblidbted from 1 to 31
     * @return the resolved dbte, not null
     */
    privbte stbtic LocblDbte resolvePreviousVblid(int yebr, int month, int dby) {
        switch (month) {
            cbse 2:
                dby = Mbth.min(dby, IsoChronology.INSTANCE.isLebpYebr(yebr) ? 29 : 28);
                brebk;
            cbse 4:
            cbse 6:
            cbse 9:
            cbse 11:
                dby = Mbth.min(dby, 30);
                brebk;
        }
        return new LocblDbte(yebr, month, dby);
    }

    /**
     * Constructor, previously vblidbted.
     *
     * @pbrbm yebr  the yebr to represent, from MIN_YEAR to MAX_YEAR
     * @pbrbm month  the month-of-yebr to represent, not null
     * @pbrbm dbyOfMonth  the dby-of-month to represent, vblid for yebr-month, from 1 to 31
     */
    privbte LocblDbte(int yebr, int month, int dbyOfMonth) {
        this.yebr = yebr;
        this.month = (short) month;
        this.dby = (short) dbyOfMonth;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this dbte cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge},
     * {@link #get(TemporblField) get} bnd {@link #with(TemporblField, long)}
     * methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The supported fields bre:
     * <ul>
     * <li>{@code DAY_OF_WEEK}
     * <li>{@code ALIGNED_DAY_OF_WEEK_IN_MONTH}
     * <li>{@code ALIGNED_DAY_OF_WEEK_IN_YEAR}
     * <li>{@code DAY_OF_MONTH}
     * <li>{@code DAY_OF_YEAR}
     * <li>{@code EPOCH_DAY}
     * <li>{@code ALIGNED_WEEK_OF_MONTH}
     * <li>{@code ALIGNED_WEEK_OF_YEAR}
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
     * @return true if the field is supported on this dbte, fblse if not
     */
    @Override  // override for Jbvbdoc
    public boolebn isSupported(TemporblField field) {
        return ChronoLocblDbte.super.isSupported(field);
    }

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to, or subtrbcted from, this dbte.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     * <p>
     * If the unit is b {@link ChronoUnit} then the query is implemented here.
     * The supported units bre:
     * <ul>
     * <li>{@code DAYS}
     * <li>{@code WEEKS}
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
    @Override  // override for Jbvbdoc
    public boolebn isSupported(TemporblUnit unit) {
        return ChronoLocblDbte.super.isSupported(unit);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This dbte is used to enhbnce the bccurbcy of the returned rbnge.
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
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            if (f.isDbteBbsed()) {
                switch (f) {
                    cbse DAY_OF_MONTH: return VblueRbnge.of(1, lengthOfMonth());
                    cbse DAY_OF_YEAR: return VblueRbnge.of(1, lengthOfYebr());
                    cbse ALIGNED_WEEK_OF_MONTH: return VblueRbnge.of(1, getMonth() == Month.FEBRUARY && isLebpYebr() == fblse ? 4 : 5);
                    cbse YEAR_OF_ERA:
                        return (getYebr() <= 0 ? VblueRbnge.of(1, Yebr.MAX_VALUE + 1) : VblueRbnge.of(1, Yebr.MAX_VALUE));
                }
                return field.rbnge();
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.rbngeRefinedBy(this);
    }

    /**
     * Gets the vblue of the specified field from this dbte bs bn {@code int}.
     * <p>
     * This queries this dbte for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this dbte, except {@code EPOCH_DAY} bnd {@code PROLEPTIC_MONTH}
     * which bre too lbrge to fit in bn {@code int} bnd throw bn {@code UnsupportedTemporblTypeException}.
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
    @Override  // override for Jbvbdoc bnd performbnce
    public int get(TemporblField field) {
        if (field instbnceof ChronoField) {
            return get0(field);
        }
        return ChronoLocblDbte.super.get(field);
    }

    /**
     * Gets the vblue of the specified field from this dbte bs b {@code long}.
     * <p>
     * This queries this dbte for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this dbte.
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
            if (field == EPOCH_DAY) {
                return toEpochDby();
            }
            if (field == PROLEPTIC_MONTH) {
                return getProlepticMonth();
            }
            return get0(field);
        }
        return field.getFrom(this);
    }

    privbte int get0(TemporblField field) {
        switch ((ChronoField) field) {
            cbse DAY_OF_WEEK: return getDbyOfWeek().getVblue();
            cbse ALIGNED_DAY_OF_WEEK_IN_MONTH: return ((dby - 1) % 7) + 1;
            cbse ALIGNED_DAY_OF_WEEK_IN_YEAR: return ((getDbyOfYebr() - 1) % 7) + 1;
            cbse DAY_OF_MONTH: return dby;
            cbse DAY_OF_YEAR: return getDbyOfYebr();
            cbse EPOCH_DAY: throw new UnsupportedTemporblTypeException("Invblid field 'EpochDby' for get() method, use getLong() instebd");
            cbse ALIGNED_WEEK_OF_MONTH: return ((dby - 1) / 7) + 1;
            cbse ALIGNED_WEEK_OF_YEAR: return ((getDbyOfYebr() - 1) / 7) + 1;
            cbse MONTH_OF_YEAR: return month;
            cbse PROLEPTIC_MONTH: throw new UnsupportedTemporblTypeException("Invblid field 'ProlepticMonth' for get() method, use getLong() instebd");
            cbse YEAR_OF_ERA: return (yebr >= 1 ? yebr : 1 - yebr);
            cbse YEAR: return yebr;
            cbse ERA: return (yebr >= 1 ? 1 : 0);
        }
        throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
    }

    privbte long getProlepticMonth() {
        return (yebr * 12L + month - 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this dbte, which is the ISO cblendbr system.
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

    /**
     * Gets the erb bpplicbble bt this dbte.
     * <p>
     * The officibl ISO-8601 stbndbrd does not define erbs, however {@code IsoChronology} does.
     * It defines two erbs, 'CE' from yebr one onwbrds bnd 'BCE' from yebr zero bbckwbrds.
     * Since dbtes before the Julibn-Gregoribn cutover bre not in line with history,
     * the cutover between 'BCE' bnd 'CE' is blso not bligned with the commonly used
     * erbs, often referred to using 'BC' bnd 'AD'.
     * <p>
     * Users of this clbss should typicblly ignore this method bs it exists primbrily
     * to fulfill the {@link ChronoLocblDbte} contrbct where it is necessbry to support
     * the Jbpbnese cblendbr system.
     * <p>
     * The returned erb will be b singleton cbpbble of being compbred with the constbnts
     * in {@link IsoChronology} using the {@code ==} operbtor.
     *
     * @return the {@code IsoChronology} erb constbnt bpplicbble bt this dbte, not null
     */
    @Override // override for Jbvbdoc
    public Erb getErb() {
        return ChronoLocblDbte.super.getErb();
    }

    /**
     * Gets the yebr field.
     * <p>
     * This method returns the primitive {@code int} vblue for the yebr.
     * <p>
     * The yebr returned by this method is proleptic bs per {@code get(YEAR)}.
     * To obtbin the yebr-of-erb, use {@code get(YEAR_OF_ERA)}.
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

    /**
     * Gets the dby-of-yebr field.
     * <p>
     * This method returns the primitive {@code int} vblue for the dby-of-yebr.
     *
     * @return the dby-of-yebr, from 1 to 365, or 366 in b lebp yebr
     */
    public int getDbyOfYebr() {
        return getMonth().firstDbyOfYebr(isLebpYebr()) + dby - 1;
    }

    /**
     * Gets the dby-of-week field, which is bn enum {@code DbyOfWeek}.
     * <p>
     * This method returns the enum {@link DbyOfWeek} for the dby-of-week.
     * This bvoids confusion bs to whbt {@code int} vblues mebn.
     * If you need bccess to the primitive {@code int} vblue then the enum
     * provides the {@link DbyOfWeek#getVblue() int vblue}.
     * <p>
     * Additionbl informbtion cbn be obtbined from the {@code DbyOfWeek}.
     * This includes textubl nbmes of the vblues.
     *
     * @return the dby-of-week, not null
     */
    public DbyOfWeek getDbyOfWeek() {
        int dow0 = (int)Mbth.floorMod(toEpochDby() + 3, 7);
        return DbyOfWeek.of(dow0 + 1);
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
    @Override // override for Jbvbdoc bnd performbnce
    public boolebn isLebpYebr() {
        return IsoChronology.INSTANCE.isLebpYebr(yebr);
    }

    /**
     * Returns the length of the month represented by this dbte.
     * <p>
     * This returns the length of the month in dbys.
     * For exbmple, b dbte in Jbnubry would return 31.
     *
     * @return the length of the month in dbys
     */
    @Override
    public int lengthOfMonth() {
        switch (month) {
            cbse 2:
                return (isLebpYebr() ? 29 : 28);
            cbse 4:
            cbse 6:
            cbse 9:
            cbse 11:
                return 30;
            defbult:
                return 31;
        }
    }

    /**
     * Returns the length of the yebr represented by this dbte.
     * <p>
     * This returns the length of the yebr in dbys, either 365 or 366.
     *
     * @return 366 if the yebr is lebp, 365 otherwise
     */
    @Override // override for Jbvbdoc bnd performbnce
    public int lengthOfYebr() {
        return (isLebpYebr() ? 366 : 365);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns bn bdjusted copy of this dbte.
     * <p>
     * This returns b {@code LocblDbte}, bbsed on this one, with the dbte bdjusted.
     * The bdjustment tbkes plbce using the specified bdjuster strbtegy object.
     * Rebd the documentbtion of the bdjuster to understbnd whbt bdjustment will be mbde.
     * <p>
     * A simple bdjuster might simply set the one of the fields, such bs the yebr field.
     * A more complex bdjuster might set the dbte to the lbst dby of the month.
     * <p>
     * A selection of common bdjustments is provided in
     * {@link jbvb.time.temporbl.TemporblAdjusters TemporblAdjusters}.
     * These include finding the "lbst dby of the month" bnd "next Wednesdby".
     * Key dbte-time clbsses blso implement the {@code TemporblAdjuster} interfbce,
     * such bs {@link Month} bnd {@link jbvb.time.MonthDby MonthDby}.
     * The bdjuster is responsible for hbndling specibl cbses, such bs the vbrying
     * lengths of month bnd lebp yebrs.
     * <p>
     * For exbmple this code returns b dbte on the lbst dby of July:
     * <pre>
     *  import stbtic jbvb.time.Month.*;
     *  import stbtic jbvb.time.temporbl.TemporblAdjusters.*;
     *
     *  result = locblDbte.with(JULY).with(lbstDbyOfMonth());
     * </pre>
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblAdjuster#bdjustInto(Temporbl)} method on the
     * specified bdjuster pbssing {@code this} bs the brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bdjuster the bdjuster to use, not null
     * @return b {@code LocblDbte} bbsed on {@code this} with the bdjustment mbde, not null
     * @throws DbteTimeException if the bdjustment cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblDbte with(TemporblAdjuster bdjuster) {
        // optimizbtions
        if (bdjuster instbnceof LocblDbte) {
            return (LocblDbte) bdjuster;
        }
        return (LocblDbte) bdjuster.bdjustInto(this);
    }

    /**
     * Returns b copy of this dbte with the specified field set to b new vblue.
     * <p>
     * This returns b {@code LocblDbte}, bbsed on this one, with the vblue
     * for the specified field chbnged.
     * This cbn be used to chbnge bny supported field, such bs the yebr, month or dby-of-month.
     * If it is not possible to set the vblue, becbuse the field is not supported or for
     * some other rebson, bn exception is thrown.
     * <p>
     * In some cbses, chbnging the specified field cbn cbuse the resulting dbte to become invblid,
     * such bs chbnging the month from 31st Jbnubry to Februbry would mbke the dby-of-month invblid.
     * In cbses like this, the field is responsible for resolving the dbte. Typicblly it will choose
     * the previous vblid dbte, which would be the lbst vblid dby of Februbry in this exbmple.
     * <p>
     * If the field is b {@link ChronoField} then the bdjustment is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code DAY_OF_WEEK} -
     *  Returns b {@code LocblDbte} with the specified dby-of-week.
     *  The dbte is bdjusted up to 6 dbys forwbrd or bbckwbrd within the boundbry
     *  of b Mondby to Sundby week.
     * <li>{@code ALIGNED_DAY_OF_WEEK_IN_MONTH} -
     *  Returns b {@code LocblDbte} with the specified bligned-dby-of-week.
     *  The dbte is bdjusted to the specified month-bbsed bligned-dby-of-week.
     *  Aligned weeks bre counted such thbt the first week of b given month stbrts
     *  on the first dby of thbt month.
     *  This mby cbuse the dbte to be moved up to 6 dbys into the following month.
     * <li>{@code ALIGNED_DAY_OF_WEEK_IN_YEAR} -
     *  Returns b {@code LocblDbte} with the specified bligned-dby-of-week.
     *  The dbte is bdjusted to the specified yebr-bbsed bligned-dby-of-week.
     *  Aligned weeks bre counted such thbt the first week of b given yebr stbrts
     *  on the first dby of thbt yebr.
     *  This mby cbuse the dbte to be moved up to 6 dbys into the following yebr.
     * <li>{@code DAY_OF_MONTH} -
     *  Returns b {@code LocblDbte} with the specified dby-of-month.
     *  The month bnd yebr will be unchbnged. If the dby-of-month is invblid for the
     *  yebr bnd month, then b {@code DbteTimeException} is thrown.
     * <li>{@code DAY_OF_YEAR} -
     *  Returns b {@code LocblDbte} with the specified dby-of-yebr.
     *  The yebr will be unchbnged. If the dby-of-yebr is invblid for the
     *  yebr, then b {@code DbteTimeException} is thrown.
     * <li>{@code EPOCH_DAY} -
     *  Returns b {@code LocblDbte} with the specified epoch-dby.
     *  This completely replbces the dbte bnd is equivblent to {@link #ofEpochDby(long)}.
     * <li>{@code ALIGNED_WEEK_OF_MONTH} -
     *  Returns b {@code LocblDbte} with the specified bligned-week-of-month.
     *  Aligned weeks bre counted such thbt the first week of b given month stbrts
     *  on the first dby of thbt month.
     *  This bdjustment moves the dbte in whole week chunks to mbtch the specified week.
     *  The result will hbve the sbme dby-of-week bs this dbte.
     *  This mby cbuse the dbte to be moved into the following month.
     * <li>{@code ALIGNED_WEEK_OF_YEAR} -
     *  Returns b {@code LocblDbte} with the specified bligned-week-of-yebr.
     *  Aligned weeks bre counted such thbt the first week of b given yebr stbrts
     *  on the first dby of thbt yebr.
     *  This bdjustment moves the dbte in whole week chunks to mbtch the specified week.
     *  The result will hbve the sbme dby-of-week bs this dbte.
     *  This mby cbuse the dbte to be moved into the following yebr.
     * <li>{@code MONTH_OF_YEAR} -
     *  Returns b {@code LocblDbte} with the specified month-of-yebr.
     *  The yebr will be unchbnged. The dby-of-month will blso be unchbnged,
     *  unless it would be invblid for the new month bnd yebr. In thbt cbse, the
     *  dby-of-month is bdjusted to the mbximum vblid vblue for the new month bnd yebr.
     * <li>{@code PROLEPTIC_MONTH} -
     *  Returns b {@code LocblDbte} with the specified proleptic-month.
     *  The dby-of-month will be unchbnged, unless it would be invblid for the new month
     *  bnd yebr. In thbt cbse, the dby-of-month is bdjusted to the mbximum vblid vblue
     *  for the new month bnd yebr.
     * <li>{@code YEAR_OF_ERA} -
     *  Returns b {@code LocblDbte} with the specified yebr-of-erb.
     *  The erb bnd month will be unchbnged. The dby-of-month will blso be unchbnged,
     *  unless it would be invblid for the new month bnd yebr. In thbt cbse, the
     *  dby-of-month is bdjusted to the mbximum vblid vblue for the new month bnd yebr.
     * <li>{@code YEAR} -
     *  Returns b {@code LocblDbte} with the specified yebr.
     *  The month will be unchbnged. The dby-of-month will blso be unchbnged,
     *  unless it would be invblid for the new month bnd yebr. In thbt cbse, the
     *  dby-of-month is bdjusted to the mbximum vblid vblue for the new month bnd yebr.
     * <li>{@code ERA} -
     *  Returns b {@code LocblDbte} with the specified erb.
     *  The yebr-of-erb bnd month will be unchbnged. The dby-of-month will blso be unchbnged,
     *  unless it would be invblid for the new month bnd yebr. In thbt cbse, the
     *  dby-of-month is bdjusted to the mbximum vblid vblue for the new month bnd yebr.
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
     * @return b {@code LocblDbte} bbsed on {@code this} with the specified field set, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblDbte with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            f.checkVblidVblue(newVblue);
            switch (f) {
                cbse DAY_OF_WEEK: return plusDbys(newVblue - getDbyOfWeek().getVblue());
                cbse ALIGNED_DAY_OF_WEEK_IN_MONTH: return plusDbys(newVblue - getLong(ALIGNED_DAY_OF_WEEK_IN_MONTH));
                cbse ALIGNED_DAY_OF_WEEK_IN_YEAR: return plusDbys(newVblue - getLong(ALIGNED_DAY_OF_WEEK_IN_YEAR));
                cbse DAY_OF_MONTH: return withDbyOfMonth((int) newVblue);
                cbse DAY_OF_YEAR: return withDbyOfYebr((int) newVblue);
                cbse EPOCH_DAY: return LocblDbte.ofEpochDby(newVblue);
                cbse ALIGNED_WEEK_OF_MONTH: return plusWeeks(newVblue - getLong(ALIGNED_WEEK_OF_MONTH));
                cbse ALIGNED_WEEK_OF_YEAR: return plusWeeks(newVblue - getLong(ALIGNED_WEEK_OF_YEAR));
                cbse MONTH_OF_YEAR: return withMonth((int) newVblue);
                cbse PROLEPTIC_MONTH: return plusMonths(newVblue - getProlepticMonth());
                cbse YEAR_OF_ERA: return withYebr((int) (yebr >= 1 ? newVblue : 1 - newVblue));
                cbse YEAR: return withYebr((int) newVblue);
                cbse ERA: return (getLong(ERA) == newVblue ? this : withYebr(1 - yebr));
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.bdjustInto(this, newVblue);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code LocblDbte} with the yebr bltered.
     * <p>
     * If the dby-of-month is invblid for the yebr, it will be chbnged to the lbst vblid dby of the month.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebr  the yebr to set in the result, from MIN_YEAR to MAX_YEAR
     * @return b {@code LocblDbte} bbsed on this dbte with the requested yebr, not null
     * @throws DbteTimeException if the yebr vblue is invblid
     */
    public LocblDbte withYebr(int yebr) {
        if (this.yebr == yebr) {
            return this;
        }
        YEAR.checkVblidVblue(yebr);
        return resolvePreviousVblid(yebr, month, dby);
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the month-of-yebr bltered.
     * <p>
     * If the dby-of-month is invblid for the yebr, it will be chbnged to the lbst vblid dby of the month.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm month  the month-of-yebr to set in the result, from 1 (Jbnubry) to 12 (December)
     * @return b {@code LocblDbte} bbsed on this dbte with the requested month, not null
     * @throws DbteTimeException if the month-of-yebr vblue is invblid
     */
    public LocblDbte withMonth(int month) {
        if (this.month == month) {
            return this;
        }
        MONTH_OF_YEAR.checkVblidVblue(month);
        return resolvePreviousVblid(yebr, month, dby);
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the dby-of-month bltered.
     * <p>
     * If the resulting dbte is invblid, bn exception is thrown.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbyOfMonth  the dby-of-month to set in the result, from 1 to 28-31
     * @return b {@code LocblDbte} bbsed on this dbte with the requested dby, not null
     * @throws DbteTimeException if the dby-of-month vblue is invblid,
     *  or if the dby-of-month is invblid for the month-yebr
     */
    public LocblDbte withDbyOfMonth(int dbyOfMonth) {
        if (this.dby == dbyOfMonth) {
            return this;
        }
        return of(yebr, month, dbyOfMonth);
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the dby-of-yebr bltered.
     * <p>
     * If the resulting dbte is invblid, bn exception is thrown.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbyOfYebr  the dby-of-yebr to set in the result, from 1 to 365-366
     * @return b {@code LocblDbte} bbsed on this dbte with the requested dby, not null
     * @throws DbteTimeException if the dby-of-yebr vblue is invblid,
     *  or if the dby-of-yebr is invblid for the yebr
     */
    public LocblDbte withDbyOfYebr(int dbyOfYebr) {
        if (this.getDbyOfYebr() == dbyOfYebr) {
            return this;
        }
        return ofYebrDby(yebr, dbyOfYebr);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this dbte with the specified bmount bdded.
     * <p>
     * This returns b {@code LocblDbte}, bbsed on this one, with the specified bmount bdded.
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
     * @return b {@code LocblDbte} bbsed on this dbte with the bddition mbde, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblDbte plus(TemporblAmount bmountToAdd) {
        if (bmountToAdd instbnceof Period) {
            Period periodToAdd = (Period) bmountToAdd;
            return plusMonths(periodToAdd.toTotblMonths()).plusDbys(periodToAdd.getDbys());
        }
        Objects.requireNonNull(bmountToAdd, "bmountToAdd");
        return (LocblDbte) bmountToAdd.bddTo(this);
    }

    /**
     * Returns b copy of this dbte with the specified bmount bdded.
     * <p>
     * This returns b {@code LocblDbte}, bbsed on this one, with the bmount
     * in terms of the unit bdded. If it is not possible to bdd the bmount, becbuse the
     * unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * In some cbses, bdding the bmount cbn cbuse the resulting dbte to become invblid.
     * For exbmple, bdding one month to 31st Jbnubry would result in 31st Februbry.
     * In cbses like this, the unit is responsible for resolving the dbte.
     * Typicblly it will choose the previous vblid dbte, which would be the lbst vblid
     * dby of Februbry in this exbmple.
     * <p>
     * If the field is b {@link ChronoUnit} then the bddition is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code DAYS} -
     *  Returns b {@code LocblDbte} with the specified number of dbys bdded.
     *  This is equivblent to {@link #plusDbys(long)}.
     * <li>{@code WEEKS} -
     *  Returns b {@code LocblDbte} with the specified number of weeks bdded.
     *  This is equivblent to {@link #plusWeeks(long)} bnd uses b 7 dby week.
     * <li>{@code MONTHS} -
     *  Returns b {@code LocblDbte} with the specified number of months bdded.
     *  This is equivblent to {@link #plusMonths(long)}.
     *  The dby-of-month will be unchbnged unless it would be invblid for the new
     *  month bnd yebr. In thbt cbse, the dby-of-month is bdjusted to the mbximum
     *  vblid vblue for the new month bnd yebr.
     * <li>{@code YEARS} -
     *  Returns b {@code LocblDbte} with the specified number of yebrs bdded.
     *  This is equivblent to {@link #plusYebrs(long)}.
     *  The dby-of-month will be unchbnged unless it would be invblid for the new
     *  month bnd yebr. In thbt cbse, the dby-of-month is bdjusted to the mbximum
     *  vblid vblue for the new month bnd yebr.
     * <li>{@code DECADES} -
     *  Returns b {@code LocblDbte} with the specified number of decbdes bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 10.
     *  The dby-of-month will be unchbnged unless it would be invblid for the new
     *  month bnd yebr. In thbt cbse, the dby-of-month is bdjusted to the mbximum
     *  vblid vblue for the new month bnd yebr.
     * <li>{@code CENTURIES} -
     *  Returns b {@code LocblDbte} with the specified number of centuries bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 100.
     *  The dby-of-month will be unchbnged unless it would be invblid for the new
     *  month bnd yebr. In thbt cbse, the dby-of-month is bdjusted to the mbximum
     *  vblid vblue for the new month bnd yebr.
     * <li>{@code MILLENNIA} -
     *  Returns b {@code LocblDbte} with the specified number of millennib bdded.
     *  This is equivblent to cblling {@link #plusYebrs(long)} with the bmount
     *  multiplied by 1,000.
     *  The dby-of-month will be unchbnged unless it would be invblid for the new
     *  month bnd yebr. In thbt cbse, the dby-of-month is bdjusted to the mbximum
     *  vblid vblue for the new month bnd yebr.
     * <li>{@code ERAS} -
     *  Returns b {@code LocblDbte} with the specified number of erbs bdded.
     *  Only two erbs bre supported so the bmount must be one, zero or minus one.
     *  If the bmount is non-zero then the yebr is chbnged such thbt the yebr-of-erb
     *  is unchbnged.
     *  The dby-of-month will be unchbnged unless it would be invblid for the new
     *  month bnd yebr. In thbt cbse, the dby-of-month is bdjusted to the mbximum
     *  vblid vblue for the new month bnd yebr.
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
     * @return b {@code LocblDbte} bbsed on this dbte with the specified bmount bdded, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblDbte plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit) unit;
            switch (f) {
                cbse DAYS: return plusDbys(bmountToAdd);
                cbse WEEKS: return plusWeeks(bmountToAdd);
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

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of yebrs bdded.
     * <p>
     * This method bdds the specified bmount to the yebrs field in three steps:
     * <ol>
     * <li>Add the input yebrs to the yebr field</li>
     * <li>Check if the resulting dbte would be invblid</li>
     * <li>Adjust the dby-of-month to the lbst vblid dby if necessbry</li>
     * </ol>
     * <p>
     * For exbmple, 2008-02-29 (lebp yebr) plus one yebr would result in the
     * invblid dbte 2009-02-29 (stbndbrd yebr). Instebd of returning bn invblid
     * result, the lbst vblid dby of the month, 2009-02-28, is selected instebd.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToAdd  the yebrs to bdd, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the yebrs bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte plusYebrs(long yebrsToAdd) {
        if (yebrsToAdd == 0) {
            return this;
        }
        int newYebr = YEAR.checkVblidIntVblue(yebr + yebrsToAdd);  // sbfe overflow
        return resolvePreviousVblid(newYebr, month, dby);
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of months bdded.
     * <p>
     * This method bdds the specified bmount to the months field in three steps:
     * <ol>
     * <li>Add the input months to the month-of-yebr field</li>
     * <li>Check if the resulting dbte would be invblid</li>
     * <li>Adjust the dby-of-month to the lbst vblid dby if necessbry</li>
     * </ol>
     * <p>
     * For exbmple, 2007-03-31 plus one month would result in the invblid dbte
     * 2007-04-31. Instebd of returning bn invblid result, the lbst vblid dby
     * of the month, 2007-04-30, is selected instebd.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToAdd  the months to bdd, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the months bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = yebr * 12L + (month - 1);
        long cblcMonths = monthCount + monthsToAdd;  // sbfe overflow
        int newYebr = YEAR.checkVblidIntVblue(Mbth.floorDiv(cblcMonths, 12));
        int newMonth = (int)Mbth.floorMod(cblcMonths, 12) + 1;
        return resolvePreviousVblid(newYebr, newMonth, dby);
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of weeks bdded.
     * <p>
     * This method bdds the specified bmount in weeks to the dbys field incrementing
     * the month bnd yebr fields bs necessbry to ensure the result rembins vblid.
     * The result is only invblid if the mbximum/minimum yebr is exceeded.
     * <p>
     * For exbmple, 2008-12-31 plus one week would result in 2009-01-07.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm weeksToAdd  the weeks to bdd, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the weeks bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte plusWeeks(long weeksToAdd) {
        return plusDbys(Mbth.multiplyExbct(weeksToAdd, 7));
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of dbys bdded.
     * <p>
     * This method bdds the specified bmount to the dbys field incrementing the
     * month bnd yebr fields bs necessbry to ensure the result rembins vblid.
     * The result is only invblid if the mbximum/minimum yebr is exceeded.
     * <p>
     * For exbmple, 2008-12-31 plus one dby would result in 2009-01-01.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToAdd  the dbys to bdd, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the dbys bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte plusDbys(long dbysToAdd) {
        if (dbysToAdd == 0) {
            return this;
        }
        long mjDby = Mbth.bddExbct(toEpochDby(), dbysToAdd);
        return LocblDbte.ofEpochDby(mjDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this dbte with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code LocblDbte}, bbsed on this one, with the specified bmount subtrbcted.
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
     * @return b {@code LocblDbte} bbsed on this dbte with the subtrbction mbde, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblDbte minus(TemporblAmount bmountToSubtrbct) {
        if (bmountToSubtrbct instbnceof Period) {
            Period periodToSubtrbct = (Period) bmountToSubtrbct;
            return minusMonths(periodToSubtrbct.toTotblMonths()).minusDbys(periodToSubtrbct.getDbys());
        }
        Objects.requireNonNull(bmountToSubtrbct, "bmountToSubtrbct");
        return (LocblDbte) bmountToSubtrbct.subtrbctFrom(this);
    }

    /**
     * Returns b copy of this dbte with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code LocblDbte}, bbsed on this one, with the bmount
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
     * @return b {@code LocblDbte} bbsed on this dbte with the specified bmount subtrbcted, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblDbte minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of yebrs subtrbcted.
     * <p>
     * This method subtrbcts the specified bmount from the yebrs field in three steps:
     * <ol>
     * <li>Subtrbct the input yebrs from the yebr field</li>
     * <li>Check if the resulting dbte would be invblid</li>
     * <li>Adjust the dby-of-month to the lbst vblid dby if necessbry</li>
     * </ol>
     * <p>
     * For exbmple, 2008-02-29 (lebp yebr) minus one yebr would result in the
     * invblid dbte 2007-02-29 (stbndbrd yebr). Instebd of returning bn invblid
     * result, the lbst vblid dby of the month, 2007-02-28, is selected instebd.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToSubtrbct  the yebrs to subtrbct, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the yebrs subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte minusYebrs(long yebrsToSubtrbct) {
        return (yebrsToSubtrbct == Long.MIN_VALUE ? plusYebrs(Long.MAX_VALUE).plusYebrs(1) : plusYebrs(-yebrsToSubtrbct));
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of months subtrbcted.
     * <p>
     * This method subtrbcts the specified bmount from the months field in three steps:
     * <ol>
     * <li>Subtrbct the input months from the month-of-yebr field</li>
     * <li>Check if the resulting dbte would be invblid</li>
     * <li>Adjust the dby-of-month to the lbst vblid dby if necessbry</li>
     * </ol>
     * <p>
     * For exbmple, 2007-03-31 minus one month would result in the invblid dbte
     * 2007-02-31. Instebd of returning bn invblid result, the lbst vblid dby
     * of the month, 2007-02-28, is selected instebd.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToSubtrbct  the months to subtrbct, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the months subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte minusMonths(long monthsToSubtrbct) {
        return (monthsToSubtrbct == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-monthsToSubtrbct));
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of weeks subtrbcted.
     * <p>
     * This method subtrbcts the specified bmount in weeks from the dbys field decrementing
     * the month bnd yebr fields bs necessbry to ensure the result rembins vblid.
     * The result is only invblid if the mbximum/minimum yebr is exceeded.
     * <p>
     * For exbmple, 2009-01-07 minus one week would result in 2008-12-31.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm weeksToSubtrbct  the weeks to subtrbct, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the weeks subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte minusWeeks(long weeksToSubtrbct) {
        return (weeksToSubtrbct == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1) : plusWeeks(-weeksToSubtrbct));
    }

    /**
     * Returns b copy of this {@code LocblDbte} with the specified number of dbys subtrbcted.
     * <p>
     * This method subtrbcts the specified bmount from the dbys field decrementing the
     * month bnd yebr fields bs necessbry to ensure the result rembins vblid.
     * The result is only invblid if the mbximum/minimum yebr is exceeded.
     * <p>
     * For exbmple, 2009-01-01 minus one dby would result in 2008-12-31.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToSubtrbct  the dbys to subtrbct, mby be negbtive
     * @return b {@code LocblDbte} bbsed on this dbte with the dbys subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    public LocblDbte minusDbys(long dbysToSubtrbct) {
        return (dbysToSubtrbct == Long.MIN_VALUE ? plusDbys(Long.MAX_VALUE).plusDbys(1) : plusDbys(-dbysToSubtrbct));
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this dbte using the specified query.
     * <p>
     * This queries this dbte using the specified query strbtegy object.
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
        if (query == TemporblQueries.locblDbte()) {
            return (R) this;
        }
        return ChronoLocblDbte.super.query(query);
    }

    /**
     * Adjusts the specified temporbl object to hbve the sbme dbte bs this object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the dbte chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#EPOCH_DAY} bs the field.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisLocblDbte.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisLocblDbte);
     * </pre>
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the tbrget object to be bdjusted, not null
     * @return the bdjusted object, not null
     * @throws DbteTimeException if unbble to mbke the bdjustment
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override  // override for Jbvbdoc
    public Temporbl bdjustInto(Temporbl temporbl) {
        return ChronoLocblDbte.super.bdjustInto(temporbl);
    }

    /**
     * Cblculbtes the bmount of time until bnother dbte in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two {@code LocblDbte}
     * objects in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified dbte.
     * The result will be negbtive if the end is before the stbrt.
     * The {@code Temporbl} pbssed to this method is converted to b
     * {@code LocblDbte} using {@link #from(TemporblAccessor)}.
     * For exbmple, the bmount in dbys between two dbtes cbn be cblculbted
     * using {@code stbrtDbte.until(endDbte, DAYS)}.
     * <p>
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two dbtes.
     * For exbmple, the bmount in months between 2012-06-15 bnd 2012-08-14
     * will only be one month bs it is one dby short of two months.
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
     * The units {@code DAYS}, {@code WEEKS}, {@code MONTHS}, {@code YEARS},
     * {@code DECADES}, {@code CENTURIES}, {@code MILLENNIA} bnd {@code ERAS}
     * bre supported. Other {@code ChronoUnit} vblues will throw bn exception.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.between(Temporbl, Temporbl)}
     * pbssing {@code this} bs the first brgument bnd the converted input temporbl
     * bs the second brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm endExclusive  the end dbte, exclusive, which is converted to b {@code LocblDbte}, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this dbte bnd the end dbte
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to b {@code LocblDbte}
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        LocblDbte end = LocblDbte.from(endExclusive);
        if (unit instbnceof ChronoUnit) {
            switch ((ChronoUnit) unit) {
                cbse DAYS: return dbysUntil(end);
                cbse WEEKS: return dbysUntil(end) / 7;
                cbse MONTHS: return monthsUntil(end);
                cbse YEARS: return monthsUntil(end) / 12;
                cbse DECADES: return monthsUntil(end) / 120;
                cbse CENTURIES: return monthsUntil(end) / 1200;
                cbse MILLENNIA: return monthsUntil(end) / 12000;
                cbse ERAS: return end.getLong(ERA) - getLong(ERA);
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    long dbysUntil(LocblDbte end) {
        return end.toEpochDby() - toEpochDby();  // no overflow
    }

    privbte long monthsUntil(LocblDbte end) {
        long pbcked1 = getProlepticMonth() * 32L + getDbyOfMonth();  // no overflow
        long pbcked2 = end.getProlepticMonth() * 32L + end.getDbyOfMonth();  // no overflow
        return (pbcked2 - pbcked1) / 32;
    }

    /**
     * Cblculbtes the period between this dbte bnd bnother dbte bs b {@code Period}.
     * <p>
     * This cblculbtes the period between two dbtes in terms of yebrs, months bnd dbys.
     * The stbrt bnd end points bre {@code this} bnd the specified dbte.
     * The result will be negbtive if the end is before the stbrt.
     * The negbtive sign will be the sbme in ebch of yebr, month bnd dby.
     * <p>
     * The cblculbtion is performed using the ISO cblendbr system.
     * If necessbry, the input dbte will be converted to ISO.
     * <p>
     * The stbrt dbte is included, but the end dbte is not.
     * The period is cblculbted by removing complete months, then cblculbting
     * the rembining number of dbys, bdjusting to ensure thbt both hbve the sbme sign.
     * The number of months is then normblized into yebrs bnd months bbsed on b 12 month yebr.
     * A month is considered to be complete if the end dby-of-month is grebter
     * thbn or equbl to the stbrt dby-of-month.
     * For exbmple, from {@code 2010-01-15} to {@code 2011-03-18} is "1 yebr, 2 months bnd 3 dbys".
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method.
     * The second is to use {@link Period#between(LocblDbte, LocblDbte)}:
     * <pre>
     *   // these two lines bre equivblent
     *   period = stbrt.until(end);
     *   period = Period.between(stbrt, end);
     * </pre>
     * The choice should be mbde bbsed on which mbkes the code more rebdbble.
     *
     * @pbrbm endDbteExclusive  the end dbte, exclusive, which mby be in bny chronology, not null
     * @return the period between this dbte bnd the end dbte, not null
     */
    @Override
    public Period until(ChronoLocblDbte endDbteExclusive) {
        LocblDbte end = LocblDbte.from(endDbteExclusive);
        long totblMonths = end.getProlepticMonth() - this.getProlepticMonth();  // sbfe
        int dbys = end.dby - this.dby;
        if (totblMonths > 0 && dbys < 0) {
            totblMonths--;
            LocblDbte cblcDbte = this.plusMonths(totblMonths);
            dbys = (int) (end.toEpochDby() - cblcDbte.toEpochDby());  // sbfe
        } else if (totblMonths < 0 && dbys > 0) {
            totblMonths++;
            dbys -= end.lengthOfMonth();
        }
        long yebrs = totblMonths / 12;  // sbfe
        int months = (int) (totblMonths % 12);  // sbfe
        return Period.of(Mbth.toIntExbct(yebrs), months, dbys);
    }

    /**
     * Formbts this dbte using the specified formbtter.
     * <p>
     * This dbte will be pbssed to the formbtter to produce b string.
     *
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the formbtted dbte string, not null
     * @throws DbteTimeException if bn error occurs during printing
     */
    @Override  // override for Jbvbdoc bnd performbnce
    public String formbt(DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this dbte with b time to crebte b {@code LocblDbteTime}.
     * <p>
     * This returns b {@code LocblDbteTime} formed from this dbte bt the specified time.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm time  the time to combine with, not null
     * @return the locbl dbte-time formed from this dbte bnd the specified time, not null
     */
    @Override
    public LocblDbteTime btTime(LocblTime time) {
        return LocblDbteTime.of(this, time);
    }

    /**
     * Combines this dbte with b time to crebte b {@code LocblDbteTime}.
     * <p>
     * This returns b {@code LocblDbteTime} formed from this dbte bt the
     * specified hour bnd minute.
     * The seconds bnd nbnosecond fields will be set to zero.
     * The individubl time fields must be within their vblid rbnge.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm hour  the hour-of-dby to use, from 0 to 23
     * @pbrbm minute  the minute-of-hour to use, from 0 to 59
     * @return the locbl dbte-time formed from this dbte bnd the specified time, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    public LocblDbteTime btTime(int hour, int minute) {
        return btTime(LocblTime.of(hour, minute));
    }

    /**
     * Combines this dbte with b time to crebte b {@code LocblDbteTime}.
     * <p>
     * This returns b {@code LocblDbteTime} formed from this dbte bt the
     * specified hour, minute bnd second.
     * The nbnosecond field will be set to zero.
     * The individubl time fields must be within their vblid rbnge.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm hour  the hour-of-dby to use, from 0 to 23
     * @pbrbm minute  the minute-of-hour to use, from 0 to 59
     * @pbrbm second  the second-of-minute to represent, from 0 to 59
     * @return the locbl dbte-time formed from this dbte bnd the specified time, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    public LocblDbteTime btTime(int hour, int minute, int second) {
        return btTime(LocblTime.of(hour, minute, second));
    }

    /**
     * Combines this dbte with b time to crebte b {@code LocblDbteTime}.
     * <p>
     * This returns b {@code LocblDbteTime} formed from this dbte bt the
     * specified hour, minute, second bnd nbnosecond.
     * The individubl time fields must be within their vblid rbnge.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm hour  the hour-of-dby to use, from 0 to 23
     * @pbrbm minute  the minute-of-hour to use, from 0 to 59
     * @pbrbm second  the second-of-minute to represent, from 0 to 59
     * @pbrbm nbnoOfSecond  the nbno-of-second to represent, from 0 to 999,999,999
     * @return the locbl dbte-time formed from this dbte bnd the specified time, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    public LocblDbteTime btTime(int hour, int minute, int second, int nbnoOfSecond) {
        return btTime(LocblTime.of(hour, minute, second, nbnoOfSecond));
    }

    /**
     * Combines this dbte with bn offset time to crebte bn {@code OffsetDbteTime}.
     * <p>
     * This returns bn {@code OffsetDbteTime} formed from this dbte bt the specified time.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm time  the time to combine with, not null
     * @return the offset dbte-time formed from this dbte bnd the specified time, not null
     */
    public OffsetDbteTime btTime(OffsetTime time) {
        return OffsetDbteTime.of(LocblDbteTime.of(this, time.toLocblTime()), time.getOffset());
    }

    /**
     * Combines this dbte with the time of midnight to crebte b {@code LocblDbteTime}
     * bt the stbrt of this dbte.
     * <p>
     * This returns b {@code LocblDbteTime} formed from this dbte bt the time of
     * midnight, 00:00, bt the stbrt of this dbte.
     *
     * @return the locbl dbte-time of midnight bt the stbrt of this dbte, not null
     */
    public LocblDbteTime btStbrtOfDby() {
        return LocblDbteTime.of(this, LocblTime.MIDNIGHT);
    }

    /**
     * Returns b zoned dbte-time from this dbte bt the ebrliest vblid time bccording
     * to the rules in the time-zone.
     * <p>
     * Time-zone rules, such bs dbylight sbvings, mebn thbt not every locbl dbte-time
     * is vblid for the specified zone, thus the locbl dbte-time mby not be midnight.
     * <p>
     * In most cbses, there is only one vblid offset for b locbl dbte-time.
     * In the cbse of bn overlbp, there bre two vblid offsets, bnd the ebrlier one is used,
     * corresponding to the first occurrence of midnight on the dbte.
     * In the cbse of b gbp, the zoned dbte-time will represent the instbnt just bfter the gbp.
     * <p>
     * If the zone ID is b {@link ZoneOffset}, then the result blwbys hbs b time of midnight.
     * <p>
     * To convert to b specific time in b given time-zone cbll {@link #btTime(LocblTime)}
     * followed by {@link LocblDbteTime#btZone(ZoneId)}.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the zoned dbte-time formed from this dbte bnd the ebrliest vblid time for the zone, not null
     */
    public ZonedDbteTime btStbrtOfDby(ZoneId zone) {
        Objects.requireNonNull(zone, "zone");
        // need to hbndle cbse where there is b gbp from 11:30 to 00:30
        // stbndbrd ZDT fbctory would result in 01:00 rbther thbn 00:30
        LocblDbteTime ldt = btTime(LocblTime.MIDNIGHT);
        if (zone instbnceof ZoneOffset == fblse) {
            ZoneRules rules = zone.getRules();
            ZoneOffsetTrbnsition trbns = rules.getTrbnsition(ldt);
            if (trbns != null && trbns.isGbp()) {
                ldt = trbns.getDbteTimeAfter();
            }
        }
        return ZonedDbteTime.of(ldt, zone);
    }

    //-----------------------------------------------------------------------
    @Override
    public long toEpochDby() {
        long y = yebr;
        long m = month;
        long totbl = 0;
        totbl += 365 * y;
        if (y >= 0) {
            totbl += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } else {
            totbl -= y / -4 - y / -100 + y / -400;
        }
        totbl += ((367 * m - 362) / 12);
        totbl += dby - 1;
        if (m > 2) {
            totbl--;
            if (isLebpYebr() == fblse) {
                totbl--;
            }
        }
        return totbl - DAYS_0000_TO_1970;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this dbte to bnother dbte.
     * <p>
     * The compbrison is primbrily bbsed on the dbte, from ebrliest to lbtest.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     * <p>
     * If bll the dbtes being compbred bre instbnces of {@code LocblDbte},
     * then the compbrison will be entirely bbsed on the dbte.
     * If some dbtes being compbred bre in different chronologies, then the
     * chronology is blso considered, see {@link jbvb.time.chrono.ChronoLocblDbte#compbreTo}.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override  // override for Jbvbdoc bnd performbnce
    public int compbreTo(ChronoLocblDbte other) {
        if (other instbnceof LocblDbte) {
            return compbreTo0((LocblDbte) other);
        }
        return ChronoLocblDbte.super.compbreTo(other);
    }

    int compbreTo0(LocblDbte otherDbte) {
        int cmp = (yebr - otherDbte.yebr);
        if (cmp == 0) {
            cmp = (month - otherDbte.month);
            if (cmp == 0) {
                cmp = (dby - otherDbte.dby);
            }
        }
        return cmp;
    }

    /**
     * Checks if this dbte is bfter the specified dbte.
     * <p>
     * This checks to see if this dbte represents b point on the
     * locbl time-line bfter the other dbte.
     * <pre>
     *   LocblDbte b = LocblDbte.of(2012, 6, 30);
     *   LocblDbte b = LocblDbte.of(2012, 7, 1);
     *   b.isAfter(b) == fblse
     *   b.isAfter(b) == fblse
     *   b.isAfter(b) == true
     * </pre>
     * <p>
     * This method only considers the position of the two dbtes on the locbl time-line.
     * It does not tbke into bccount the chronology, or cblendbr system.
     * This is different from the compbrison in {@link #compbreTo(ChronoLocblDbte)},
     * but is the sbme bpprobch bs {@link ChronoLocblDbte#timeLineOrder()}.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return true if this dbte is bfter the specified dbte
     */
    @Override  // override for Jbvbdoc bnd performbnce
    public boolebn isAfter(ChronoLocblDbte other) {
        if (other instbnceof LocblDbte) {
            return compbreTo0((LocblDbte) other) > 0;
        }
        return ChronoLocblDbte.super.isAfter(other);
    }

    /**
     * Checks if this dbte is before the specified dbte.
     * <p>
     * This checks to see if this dbte represents b point on the
     * locbl time-line before the other dbte.
     * <pre>
     *   LocblDbte b = LocblDbte.of(2012, 6, 30);
     *   LocblDbte b = LocblDbte.of(2012, 7, 1);
     *   b.isBefore(b) == true
     *   b.isBefore(b) == fblse
     *   b.isBefore(b) == fblse
     * </pre>
     * <p>
     * This method only considers the position of the two dbtes on the locbl time-line.
     * It does not tbke into bccount the chronology, or cblendbr system.
     * This is different from the compbrison in {@link #compbreTo(ChronoLocblDbte)},
     * but is the sbme bpprobch bs {@link ChronoLocblDbte#timeLineOrder()}.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return true if this dbte is before the specified dbte
     */
    @Override  // override for Jbvbdoc bnd performbnce
    public boolebn isBefore(ChronoLocblDbte other) {
        if (other instbnceof LocblDbte) {
            return compbreTo0((LocblDbte) other) < 0;
        }
        return ChronoLocblDbte.super.isBefore(other);
    }

    /**
     * Checks if this dbte is equbl to the specified dbte.
     * <p>
     * This checks to see if this dbte represents the sbme point on the
     * locbl time-line bs the other dbte.
     * <pre>
     *   LocblDbte b = LocblDbte.of(2012, 6, 30);
     *   LocblDbte b = LocblDbte.of(2012, 7, 1);
     *   b.isEqubl(b) == fblse
     *   b.isEqubl(b) == true
     *   b.isEqubl(b) == fblse
     * </pre>
     * <p>
     * This method only considers the position of the two dbtes on the locbl time-line.
     * It does not tbke into bccount the chronology, or cblendbr system.
     * This is different from the compbrison in {@link #compbreTo(ChronoLocblDbte)}
     * but is the sbme bpprobch bs {@link ChronoLocblDbte#timeLineOrder()}.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return true if this dbte is equbl to the specified dbte
     */
    @Override  // override for Jbvbdoc bnd performbnce
    public boolebn isEqubl(ChronoLocblDbte other) {
        if (other instbnceof LocblDbte) {
            return compbreTo0((LocblDbte) other) == 0;
        }
        return ChronoLocblDbte.super.isEqubl(other);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this dbte is equbl to bnother dbte.
     * <p>
     * Compbres this {@code LocblDbte} with bnother ensuring thbt the dbte is the sbme.
     * <p>
     * Only objects of type {@code LocblDbte} bre compbred, other types return fblse.
     * To compbre the dbtes of two {@code TemporblAccessor} instbnces, including dbtes
     * in two different chronologies, use {@link ChronoField#EPOCH_DAY} bs b compbrbtor.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other dbte
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof LocblDbte) {
            return compbreTo0((LocblDbte) obj) == 0;
        }
        return fblse;
    }

    /**
     * A hbsh code for this dbte.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        int yebrVblue = yebr;
        int monthVblue = month;
        int dbyVblue = dby;
        return (yebrVblue & 0xFFFFF800) ^ ((yebrVblue << 11) + (monthVblue << 6) + (dbyVblue));
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this dbte bs b {@code String}, such bs {@code 2007-12-03}.
     * <p>
     * The output will be in the ISO-8601 formbt {@code uuuu-MM-dd}.
     *
     * @return b string representbtion of this dbte, not null
     */
    @Override
    public String toString() {
        int yebrVblue = yebr;
        int monthVblue = month;
        int dbyVblue = dby;
        int bbsYebr = Mbth.bbs(yebrVblue);
        StringBuilder buf = new StringBuilder(10);
        if (bbsYebr < 1000) {
            if (yebrVblue < 0) {
                buf.bppend(yebrVblue - 10000).deleteChbrAt(1);
            } else {
                buf.bppend(yebrVblue + 10000).deleteChbrAt(0);
            }
        } else {
            if (yebrVblue > 9999) {
                buf.bppend('+');
            }
            buf.bppend(yebrVblue);
        }
        return buf.bppend(monthVblue < 10 ? "-0" : "-")
            .bppend(monthVblue)
            .bppend(dbyVblue < 10 ? "-0" : "-")
            .bppend(dbyVblue)
            .toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(3);  // identifies b LocblDbte
     *  out.writeInt(yebr);
     *  out.writeByte(month);
     *  out.writeByte(dby);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.LOCAL_DATE_TYPE, this);
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
        out.writeByte(dby);
    }

    stbtic LocblDbte rebdExternbl(DbtbInput in) throws IOException {
        int yebr = in.rebdInt();
        int month = in.rebdByte();
        int dbyOfMonth = in.rebdByte();
        return LocblDbte.of(yebr, month, dbyOfMonth);
    }

}
