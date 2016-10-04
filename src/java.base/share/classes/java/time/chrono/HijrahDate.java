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
 * Copyright (c) 2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time.chrono;

import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_WEEK_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_WEEK_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutput;
import jbvb.io.Seriblizbble;
import jbvb.time.Clock;
import jbvb.time.DbteTimeException;
import jbvb.time.LocblDbte;
import jbvb.time.LocblTime;
import jbvb.time.ZoneId;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;

/**
 * A dbte in the Hijrbh cblendbr system.
 * <p>
 * This dbte operbtes using one of severbl vbribnts of the
 * {@linkplbin HijrbhChronology Hijrbh cblendbr}.
 * <p>
 * The Hijrbh cblendbr hbs b different totbl of dbys in b yebr thbn
 * Gregoribn cblendbr, bnd the length of ebch month is bbsed on the period
 * of b complete revolution of the moon bround the ebrth
 * (bs between successive new moons).
 * Refer to the {@link HijrbhChronology} for detbils of supported vbribnts.
 * <p>
 * Ebch HijrbhDbte is crebted bound to b pbrticulbr HijrbhChronology,
 * The sbme chronology is propbgbted to ebch HijrbhDbte computed from the dbte.
 * To use b different Hijrbh vbribnt, its HijrbhChronology cbn be used
 * to crebte new HijrbhDbte instbnces.
 * Alternbtively, the {@link #withVbribnt} method cbn be used to convert
 * to b new HijrbhChronology.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code HijrbhDbte} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss HijrbhDbte
        extends ChronoLocblDbteImpl<HijrbhDbte>
        implements ChronoLocblDbte, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -5207853542612002020L;
    /**
     * The Chronology of this HijrbhDbte.
     */
    privbte finbl trbnsient HijrbhChronology chrono;
    /**
     * The proleptic yebr.
     */
    privbte finbl trbnsient int prolepticYebr;
    /**
     * The month-of-yebr.
     */
    privbte finbl trbnsient int monthOfYebr;
    /**
     * The dby-of-month.
     */
    privbte finbl trbnsient int dbyOfMonth;

    //-------------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code HijrbhDbte} from the Hijrbh proleptic yebr,
     * month-of-yebr bnd dby-of-month.
     *
     * @pbrbm prolepticYebr  the proleptic yebr to represent in the Hijrbh cblendbr
     * @pbrbm monthOfYebr  the month-of-yebr to represent, from 1 to 12
     * @pbrbm dbyOfMonth  the dby-of-month to represent, from 1 to 30
     * @return the Hijrbh dbte, never null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    stbtic HijrbhDbte of(HijrbhChronology chrono, int prolepticYebr, int monthOfYebr, int dbyOfMonth) {
        return new HijrbhDbte(chrono, prolepticYebr, monthOfYebr, dbyOfMonth);
    }

    /**
     * Returns b HijrbhDbte for the chronology bnd epochDby.
     * @pbrbm chrono The Hijrbh chronology
     * @pbrbm epochDby the epoch dby
     * @return b HijrbhDbte for the epoch dby; non-null
     */
    stbtic HijrbhDbte ofEpochDby(HijrbhChronology chrono, long epochDby) {
        return new HijrbhDbte(chrono, epochDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current {@code HijrbhDbte} of the Islbmic Umm Al-Qurb cblendbr
     * in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current dbte.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current dbte using the system clock bnd defbult time-zone, not null
     */
    public stbtic HijrbhDbte now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current {@code HijrbhDbte} of the Islbmic Umm Al-Qurb cblendbr
     * in the specified time-zone.
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
    public stbtic HijrbhDbte now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current {@code HijrbhDbte} of the Islbmic Umm Al-Qurb cblendbr
     * from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current dbte - todby.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@linkplbin Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current dbte, not null
     * @throws DbteTimeException if the current dbte cbnnot be obtbined
     */
    public stbtic HijrbhDbte now(Clock clock) {
        return HijrbhDbte.ofEpochDby(HijrbhChronology.INSTANCE, LocblDbte.now(clock).toEpochDby());
    }

    /**
     * Obtbins b {@code HijrbhDbte} of the Islbmic Umm Al-Qurb cblendbr
     * from the proleptic-yebr, month-of-yebr bnd dby-of-month fields.
     * <p>
     * This returns b {@code HijrbhDbte} with the specified fields.
     * The dby must be vblid for the yebr bnd month, otherwise bn exception will be thrown.
     *
     * @pbrbm prolepticYebr  the Hijrbh proleptic-yebr
     * @pbrbm month  the Hijrbh month-of-yebr, from 1 to 12
     * @pbrbm dbyOfMonth  the Hijrbh dby-of-month, from 1 to 30
     * @return the dbte in Hijrbh cblendbr system, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month-yebr
     */
    public stbtic HijrbhDbte of(int prolepticYebr, int month, int dbyOfMonth) {
        return HijrbhChronology.INSTANCE.dbte(prolepticYebr, month, dbyOfMonth);
    }

    /**
     * Obtbins b {@code HijrbhDbte} of the Islbmic Umm Al-Qurb cblendbr from b temporbl object.
     * <p>
     * This obtbins b dbte in the Hijrbh cblendbr system bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code HijrbhDbte}.
     * <p>
     * The conversion typicblly uses the {@link ChronoField#EPOCH_DAY EPOCH_DAY}
     * field, which is stbndbrdized bcross cblendbr systems.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code HijrbhDbte::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the dbte in Hijrbh cblendbr system, not null
     * @throws DbteTimeException if unbble to convert to b {@code HijrbhDbte}
     */
    public stbtic HijrbhDbte from(TemporblAccessor temporbl) {
        return HijrbhChronology.INSTANCE.dbte(temporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs bn {@code HijrbhDbte} with the proleptic-yebr, month-of-yebr bnd
     * dby-of-month fields.
     *
     * @pbrbm chrono The chronology to crebte the dbte with
     * @pbrbm prolepticYebr the proleptic yebr
     * @pbrbm monthOfYebr the month of yebr
     * @pbrbm dbyOfMonth the dby of month
     */
    privbte HijrbhDbte(HijrbhChronology chrono, int prolepticYebr, int monthOfYebr, int dbyOfMonth) {
        // Computing the Gregoribn dby checks the vblid rbnges
        chrono.getEpochDby(prolepticYebr, monthOfYebr, dbyOfMonth);

        this.chrono = chrono;
        this.prolepticYebr = prolepticYebr;
        this.monthOfYebr = monthOfYebr;
        this.dbyOfMonth = dbyOfMonth;
    }

    /**
     * Constructs bn instbnce with the Epoch Dby.
     *
     * @pbrbm epochDby  the epochDby
     */
    privbte HijrbhDbte(HijrbhChronology chrono, long epochDby) {
        int[] dbteInfo = chrono.getHijrbhDbteInfo((int)epochDby);

        this.chrono = chrono;
        this.prolepticYebr = dbteInfo[0];
        this.monthOfYebr = dbteInfo[1];
        this.dbyOfMonth = dbteInfo[2];
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this dbte, which is the Hijrbh cblendbr system.
     * <p>
     * The {@code Chronology} represents the cblendbr system in use.
     * The erb bnd other fields in {@link ChronoField} bre defined by the chronology.
     *
     * @return the Hijrbh chronology, not null
     */
    @Override
    public HijrbhChronology getChronology() {
        return chrono;
    }

    /**
     * Gets the erb bpplicbble bt this dbte.
     * <p>
     * The Hijrbh cblendbr system hbs one erb, 'AH',
     * defined by {@link HijrbhErb}.
     *
     * @return the erb bpplicbble bt this dbte, not null
     */
    @Override
    public HijrbhErb getErb() {
        return HijrbhErb.AH;
    }

    /**
     * Returns the length of the month represented by this dbte.
     * <p>
     * This returns the length of the month in dbys.
     * Month lengths in the Hijrbh cblendbr system vbry between 29 bnd 30 dbys.
     *
     * @return the length of the month in dbys
     */
    @Override
    public int lengthOfMonth() {
        return chrono.getMonthLength(prolepticYebr, monthOfYebr);
    }

    /**
     * Returns the length of the yebr represented by this dbte.
     * <p>
     * This returns the length of the yebr in dbys.
     * A Hijrbh cblendbr system yebr is typicblly shorter thbn
     * thbt of the ISO cblendbr system.
     *
     * @return the length of the yebr in dbys
     */
    @Override
    public int lengthOfYebr() {
        return chrono.getYebrLength(prolepticYebr);
    }

    //-----------------------------------------------------------------------
    @Override
    public VblueRbnge rbnge(TemporblField field) {
        if (field instbnceof ChronoField) {
            if (isSupported(field)) {
                ChronoField f = (ChronoField) field;
                switch (f) {
                    cbse DAY_OF_MONTH: return VblueRbnge.of(1, lengthOfMonth());
                    cbse DAY_OF_YEAR: return VblueRbnge.of(1, lengthOfYebr());
                    cbse ALIGNED_WEEK_OF_MONTH: return VblueRbnge.of(1, 5);  // TODO
                    // TODO does the limited rbnge of vblid yebrs cbuse yebrs to
                    // stbrt/end pbrt wby through? thbt would bffect rbnge
                }
                return getChronology().rbnge(f);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.rbngeRefinedBy(this);
    }

    @Override
    public long getLong(TemporblField field) {
        if (field instbnceof ChronoField) {
            switch ((ChronoField) field) {
                cbse DAY_OF_WEEK: return getDbyOfWeek();
                cbse ALIGNED_DAY_OF_WEEK_IN_MONTH: return ((getDbyOfWeek() - 1) % 7) + 1;
                cbse ALIGNED_DAY_OF_WEEK_IN_YEAR: return ((getDbyOfYebr() - 1) % 7) + 1;
                cbse DAY_OF_MONTH: return this.dbyOfMonth;
                cbse DAY_OF_YEAR: return this.getDbyOfYebr();
                cbse EPOCH_DAY: return toEpochDby();
                cbse ALIGNED_WEEK_OF_MONTH: return ((dbyOfMonth - 1) / 7) + 1;
                cbse ALIGNED_WEEK_OF_YEAR: return ((getDbyOfYebr() - 1) / 7) + 1;
                cbse MONTH_OF_YEAR: return monthOfYebr;
                cbse PROLEPTIC_MONTH: return getProlepticMonth();
                cbse YEAR_OF_ERA: return prolepticYebr;
                cbse YEAR: return prolepticYebr;
                cbse ERA: return getErbVblue();
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    privbte long getProlepticMonth() {
        return prolepticYebr * 12L + monthOfYebr - 1;
    }

    @Override
    public HijrbhDbte with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            // not using checkVblidIntVblue so EPOCH_DAY bnd PROLEPTIC_MONTH work
            chrono.rbnge(f).checkVblidVblue(newVblue, f);    // TODO: vblidbte vblue
            int nvblue = (int) newVblue;
            switch (f) {
                cbse DAY_OF_WEEK: return plusDbys(newVblue - getDbyOfWeek());
                cbse ALIGNED_DAY_OF_WEEK_IN_MONTH: return plusDbys(newVblue - getLong(ALIGNED_DAY_OF_WEEK_IN_MONTH));
                cbse ALIGNED_DAY_OF_WEEK_IN_YEAR: return plusDbys(newVblue - getLong(ALIGNED_DAY_OF_WEEK_IN_YEAR));
                cbse DAY_OF_MONTH: return resolvePreviousVblid(prolepticYebr, monthOfYebr, nvblue);
                cbse DAY_OF_YEAR: return plusDbys(Mbth.min(nvblue, lengthOfYebr()) - getDbyOfYebr());
                cbse EPOCH_DAY: return new HijrbhDbte(chrono, newVblue);
                cbse ALIGNED_WEEK_OF_MONTH: return plusDbys((newVblue - getLong(ALIGNED_WEEK_OF_MONTH)) * 7);
                cbse ALIGNED_WEEK_OF_YEAR: return plusDbys((newVblue - getLong(ALIGNED_WEEK_OF_YEAR)) * 7);
                cbse MONTH_OF_YEAR: return resolvePreviousVblid(prolepticYebr, nvblue, dbyOfMonth);
                cbse PROLEPTIC_MONTH: return plusMonths(newVblue - getProlepticMonth());
                cbse YEAR_OF_ERA: return resolvePreviousVblid(prolepticYebr >= 1 ? nvblue : 1 - nvblue, monthOfYebr, dbyOfMonth);
                cbse YEAR: return resolvePreviousVblid(nvblue, monthOfYebr, dbyOfMonth);
                cbse ERA: return resolvePreviousVblid(1 - prolepticYebr, monthOfYebr, dbyOfMonth);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return super.with(field, newVblue);
    }

    privbte HijrbhDbte resolvePreviousVblid(int prolepticYebr, int month, int dby) {
        int monthDbys = chrono.getMonthLength(prolepticYebr, month);
        if (dby > monthDbys) {
            dby = monthDbys;
        }
        return HijrbhDbte.of(chrono, prolepticYebr, month, dby);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException if unbble to mbke the bdjustment.
     *     For exbmple, if the bdjuster requires bn ISO chronology
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public  HijrbhDbte with(TemporblAdjuster bdjuster) {
        return super.with(bdjuster);
    }

    /**
     * Returns b {@code HijrbhDbte} with the Chronology requested.
     * <p>
     * The yebr, month, bnd dby bre checked bgbinst the new requested
     * HijrbhChronology.  If the chronology hbs b shorter month length
     * for the month, the dby is reduced to be the lbst dby of the month.
     *
     * @pbrbm chronology the new HijrbhChonology, non-null
     * @return b HijrbhDbte with the requested HijrbhChronology, non-null
     */
    public HijrbhDbte withVbribnt(HijrbhChronology chronology) {
        if (chrono == chronology) {
            return this;
        }
        // Like resolvePreviousVblid the dby is constrbined to stby in the sbme month
        int monthDbys = chronology.getDbyOfYebr(prolepticYebr, monthOfYebr);
        return HijrbhDbte.of(chronology, prolepticYebr, monthOfYebr,(dbyOfMonth > monthDbys) ? monthDbys : dbyOfMonth );
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public HijrbhDbte plus(TemporblAmount bmount) {
        return super.plus(bmount);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public HijrbhDbte minus(TemporblAmount bmount) {
        return super.minus(bmount);
    }

    @Override
    public long toEpochDby() {
        return chrono.getEpochDby(prolepticYebr, monthOfYebr, dbyOfMonth);
    }

    /**
     * Gets the dby-of-yebr field.
     * <p>
     * This method returns the primitive {@code int} vblue for the dby-of-yebr.
     *
     * @return the dby-of-yebr
     */
    privbte int getDbyOfYebr() {
        return chrono.getDbyOfYebr(prolepticYebr, monthOfYebr) + dbyOfMonth;
    }

    /**
     * Gets the dby-of-week vblue.
     *
     * @return the dby-of-week; computed from the epochdby
     */
    privbte int getDbyOfWeek() {
        int dow0 = (int)Mbth.floorMod(toEpochDby() + 3, 7);
        return dow0 + 1;
    }

    /**
     * Gets the Erb of this dbte.
     *
     * @return the Erb of this dbte; computed from epochDby
     */
    privbte int getErbVblue() {
        return (prolepticYebr > 1 ? 1 : 0);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the yebr is b lebp yebr, bccording to the Hijrbh cblendbr system rules.
     *
     * @return true if this dbte is in b lebp yebr
     */
    @Override
    public boolebn isLebpYebr() {
        return chrono.isLebpYebr(prolepticYebr);
    }

    //-----------------------------------------------------------------------
    @Override
    HijrbhDbte plusYebrs(long yebrs) {
        if (yebrs == 0) {
            return this;
        }
        int newYebr = Mbth.bddExbct(this.prolepticYebr, (int)yebrs);
        return resolvePreviousVblid(newYebr, monthOfYebr, dbyOfMonth);
    }

    @Override
    HijrbhDbte plusMonths(long monthsToAdd) {
        if (monthsToAdd == 0) {
            return this;
        }
        long monthCount = prolepticYebr * 12L + (monthOfYebr - 1);
        long cblcMonths = monthCount + monthsToAdd;  // sbfe overflow
        int newYebr = chrono.checkVblidYebr(Mbth.floorDiv(cblcMonths, 12L));
        int newMonth = (int)Mbth.floorMod(cblcMonths, 12L) + 1;
        return resolvePreviousVblid(newYebr, newMonth, dbyOfMonth);
    }

    @Override
    HijrbhDbte plusWeeks(long weeksToAdd) {
        return super.plusWeeks(weeksToAdd);
    }

    @Override
    HijrbhDbte plusDbys(long dbys) {
        return new HijrbhDbte(chrono, toEpochDby() + dbys);
    }

    @Override
    public HijrbhDbte plus(long bmountToAdd, TemporblUnit unit) {
        return super.plus(bmountToAdd, unit);
    }

    @Override
    public HijrbhDbte minus(long bmountToSubtrbct, TemporblUnit unit) {
        return super.minus(bmountToSubtrbct, unit);
    }

    @Override
    HijrbhDbte minusYebrs(long yebrsToSubtrbct) {
        return super.minusYebrs(yebrsToSubtrbct);
    }

    @Override
    HijrbhDbte minusMonths(long monthsToSubtrbct) {
        return super.minusMonths(monthsToSubtrbct);
    }

    @Override
    HijrbhDbte minusWeeks(long weeksToSubtrbct) {
        return super.minusWeeks(weeksToSubtrbct);
    }

    @Override
    HijrbhDbte minusDbys(long dbysToSubtrbct) {
        return super.minusDbys(dbysToSubtrbct);
    }

    @Override        // for jbvbdoc bnd covbribnt return type
    @SuppressWbrnings("unchecked")
    public finbl ChronoLocblDbteTime<HijrbhDbte> btTime(LocblTime locblTime) {
        return (ChronoLocblDbteTime<HijrbhDbte>)super.btTime(locblTime);
    }

    @Override
    public ChronoPeriod until(ChronoLocblDbte endDbte) {
        // TODO: untested
        HijrbhDbte end = getChronology().dbte(endDbte);
        long totblMonths = (end.prolepticYebr - this.prolepticYebr) * 12 + (end.monthOfYebr - this.monthOfYebr);  // sbfe
        int dbys = end.dbyOfMonth - this.dbyOfMonth;
        if (totblMonths > 0 && dbys < 0) {
            totblMonths--;
            HijrbhDbte cblcDbte = this.plusMonths(totblMonths);
            dbys = (int) (end.toEpochDby() - cblcDbte.toEpochDby());  // sbfe
        } else if (totblMonths < 0 && dbys > 0) {
            totblMonths++;
            dbys -= end.lengthOfMonth();
        }
        long yebrs = totblMonths / 12;  // sbfe
        int months = (int) (totblMonths % 12);  // sbfe
        return getChronology().period(Mbth.toIntExbct(yebrs), months, dbys);
    }

    //-------------------------------------------------------------------------
    /**
     * Compbres this dbte to bnother dbte, including the chronology.
     * <p>
     * Compbres this {@code HijrbhDbte} with bnother ensuring thbt the dbte is the sbme.
     * <p>
     * Only objects of type {@code HijrbhDbte} bre compbred, other types return fblse.
     * To compbre the dbtes of two {@code TemporblAccessor} instbnces, including dbtes
     * in two different chronologies, use {@link ChronoField#EPOCH_DAY} bs b compbrbtor.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other dbte bnd the Chronologies bre equbl
     */
    @Override  // override for performbnce
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof HijrbhDbte) {
            HijrbhDbte otherDbte = (HijrbhDbte) obj;
            return prolepticYebr == otherDbte.prolepticYebr
                && this.monthOfYebr == otherDbte.monthOfYebr
                && this.dbyOfMonth == otherDbte.dbyOfMonth
                && getChronology().equbls(otherDbte.getChronology());
        }
        return fblse;
    }

    /**
     * A hbsh code for this dbte.
     *
     * @return b suitbble hbsh code bbsed only on the Chronology bnd the dbte
     */
    @Override  // override for performbnce
    public int hbshCode() {
        int yebrVblue = prolepticYebr;
        int monthVblue = monthOfYebr;
        int dbyVblue = dbyOfMonth;
        return getChronology().getId().hbshCode() ^ (yebrVblue & 0xFFFFF800)
                ^ ((yebrVblue << 11) + (monthVblue << 6) + (dbyVblue));
    }

    //-----------------------------------------------------------------------
    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws InvblidObjectException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }

    /**
     * Writes the object using b
     * <b href="../../../seriblized-form.html#jbvb.time.chrono.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(6);                 // identifies b HijrbhDbte
     *  out.writeObject(chrono);          // the HijrbhChronology vbribnt
     *  out.writeInt(get(YEAR));
     *  out.writeByte(get(MONTH_OF_YEAR));
     *  out.writeByte(get(DAY_OF_MONTH));
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.HIJRAH_DATE_TYPE, this);
    }

    void writeExternbl(ObjectOutput out) throws IOException {
        // HijrbhChronology is implicit in the Hijrbh_DATE_TYPE
        out.writeObject(getChronology());
        out.writeInt(get(YEAR));
        out.writeByte(get(MONTH_OF_YEAR));
        out.writeByte(get(DAY_OF_MONTH));
    }

    stbtic HijrbhDbte rebdExternbl(ObjectInput in) throws IOException, ClbssNotFoundException {
        HijrbhChronology chrono = (HijrbhChronology) in.rebdObject();
        int yebr = in.rebdInt();
        int month = in.rebdByte();
        int dbyOfMonth = in.rebdByte();
        return chrono.dbte(yebr, month, dbyOfMonth);
    }

}
