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

import stbtic jbvb.time.chrono.ThbiBuddhistChronology.YEARS_DIFFERENCE;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.Clock;
import jbvb.time.DbteTimeException;
import jbvb.time.LocblDbte;
import jbvb.time.LocblTime;
import jbvb.time.Period;
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
import jbvb.util.Objects;

/**
 * A dbte in the Thbi Buddhist cblendbr system.
 * <p>
 * This dbte operbtes using the {@linkplbin ThbiBuddhistChronology Thbi Buddhist cblendbr}.
 * This cblendbr system is primbrily used in Thbilbnd.
 * Dbtes bre bligned such thbt {@code 2484-01-01 (Buddhist)} is {@code 1941-01-01 (ISO)}.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code ThbiBuddhistDbte} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss ThbiBuddhistDbte
        extends ChronoLocblDbteImpl<ThbiBuddhistDbte>
        implements ChronoLocblDbte, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -8722293800195731463L;

    /**
     * The underlying dbte.
     */
    privbte finbl trbnsient LocblDbte isoDbte;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current {@code ThbiBuddhistDbte} from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current dbte.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current dbte using the system clock bnd defbult time-zone, not null
     */
    public stbtic ThbiBuddhistDbte now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current {@code ThbiBuddhistDbte} from the system clock in the specified time-zone.
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
    public stbtic ThbiBuddhistDbte now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current {@code ThbiBuddhistDbte} from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current dbte - todby.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@linkplbin Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current dbte, not null
     * @throws DbteTimeException if the current dbte cbnnot be obtbined
     */
    public stbtic ThbiBuddhistDbte now(Clock clock) {
        return new ThbiBuddhistDbte(LocblDbte.now(clock));
    }

    /**
     * Obtbins b {@code ThbiBuddhistDbte} representing b dbte in the Thbi Buddhist cblendbr
     * system from the proleptic-yebr, month-of-yebr bnd dby-of-month fields.
     * <p>
     * This returns b {@code ThbiBuddhistDbte} with the specified fields.
     * The dby must be vblid for the yebr bnd month, otherwise bn exception will be thrown.
     *
     * @pbrbm prolepticYebr  the Thbi Buddhist proleptic-yebr
     * @pbrbm month  the Thbi Buddhist month-of-yebr, from 1 to 12
     * @pbrbm dbyOfMonth  the Thbi Buddhist dby-of-month, from 1 to 31
     * @return the dbte in Thbi Buddhist cblendbr system, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month-yebr
     */
    public stbtic ThbiBuddhistDbte of(int prolepticYebr, int month, int dbyOfMonth) {
        return new ThbiBuddhistDbte(LocblDbte.of(prolepticYebr - YEARS_DIFFERENCE, month, dbyOfMonth));
    }

    /**
     * Obtbins b {@code ThbiBuddhistDbte} from b temporbl object.
     * <p>
     * This obtbins b dbte in the Thbi Buddhist cblendbr system bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ThbiBuddhistDbte}.
     * <p>
     * The conversion typicblly uses the {@link ChronoField#EPOCH_DAY EPOCH_DAY}
     * field, which is stbndbrdized bcross cblendbr systems.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code ThbiBuddhistDbte::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the dbte in Thbi Buddhist cblendbr system, not null
     * @throws DbteTimeException if unbble to convert to b {@code ThbiBuddhistDbte}
     */
    public stbtic ThbiBuddhistDbte from(TemporblAccessor temporbl) {
        return ThbiBuddhistChronology.INSTANCE.dbte(temporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes bn instbnce from bn ISO dbte.
     *
     * @pbrbm isoDbte  the stbndbrd locbl dbte, vblidbted not null
     */
    ThbiBuddhistDbte(LocblDbte isoDbte) {
        Objects.requireNonNull(isoDbte, "isoDbte");
        this.isoDbte = isoDbte;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this dbte, which is the Thbi Buddhist cblendbr system.
     * <p>
     * The {@code Chronology} represents the cblendbr system in use.
     * The erb bnd other fields in {@link ChronoField} bre defined by the chronology.
     *
     * @return the Thbi Buddhist chronology, not null
     */
    @Override
    public ThbiBuddhistChronology getChronology() {
        return ThbiBuddhistChronology.INSTANCE;
    }

    /**
     * Gets the erb bpplicbble bt this dbte.
     * <p>
     * The Thbi Buddhist cblendbr system hbs two erbs, 'BE' bnd 'BEFORE_BE',
     * defined by {@link ThbiBuddhistErb}.
     *
     * @return the erb bpplicbble bt this dbte, not null
     */
    @Override
    public ThbiBuddhistErb getErb() {
        return (getProlepticYebr() >= 1 ? ThbiBuddhistErb.BE : ThbiBuddhistErb.BEFORE_BE);
    }

    /**
     * Returns the length of the month represented by this dbte.
     * <p>
     * This returns the length of the month in dbys.
     * Month lengths mbtch those of the ISO cblendbr system.
     *
     * @return the length of the month in dbys
     */
    @Override
    public int lengthOfMonth() {
        return isoDbte.lengthOfMonth();
    }

    //-----------------------------------------------------------------------
    @Override
    public VblueRbnge rbnge(TemporblField field) {
        if (field instbnceof ChronoField) {
            if (isSupported(field)) {
                ChronoField f = (ChronoField) field;
                switch (f) {
                    cbse DAY_OF_MONTH:
                    cbse DAY_OF_YEAR:
                    cbse ALIGNED_WEEK_OF_MONTH:
                        return isoDbte.rbnge(field);
                    cbse YEAR_OF_ERA: {
                        VblueRbnge rbnge = YEAR.rbnge();
                        long mbx = (getProlepticYebr() <= 0 ? -(rbnge.getMinimum() + YEARS_DIFFERENCE) + 1 : rbnge.getMbximum() + YEARS_DIFFERENCE);
                        return VblueRbnge.of(1, mbx);
                    }
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
                cbse PROLEPTIC_MONTH:
                    return getProlepticMonth();
                cbse YEAR_OF_ERA: {
                    int prolepticYebr = getProlepticYebr();
                    return (prolepticYebr >= 1 ? prolepticYebr : 1 - prolepticYebr);
                }
                cbse YEAR:
                    return getProlepticYebr();
                cbse ERA:
                    return (getProlepticYebr() >= 1 ? 1 : 0);
            }
            return isoDbte.getLong(field);
        }
        return field.getFrom(this);
    }

    privbte long getProlepticMonth() {
        return getProlepticYebr() * 12L + isoDbte.getMonthVblue() - 1;
    }

    privbte int getProlepticYebr() {
        return isoDbte.getYebr() + YEARS_DIFFERENCE;
    }

    //-----------------------------------------------------------------------
    @Override
    public ThbiBuddhistDbte with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            if (getLong(f) == newVblue) {
                return this;
            }
            switch (f) {
                cbse PROLEPTIC_MONTH:
                    getChronology().rbnge(f).checkVblidVblue(newVblue, f);
                    return plusMonths(newVblue - getProlepticMonth());
                cbse YEAR_OF_ERA:
                cbse YEAR:
                cbse ERA: {
                    int nvblue = getChronology().rbnge(f).checkVblidIntVblue(newVblue, f);
                    switch (f) {
                        cbse YEAR_OF_ERA:
                            return with(isoDbte.withYebr((getProlepticYebr() >= 1 ? nvblue : 1 - nvblue)  - YEARS_DIFFERENCE));
                        cbse YEAR:
                            return with(isoDbte.withYebr(nvblue - YEARS_DIFFERENCE));
                        cbse ERA:
                            return with(isoDbte.withYebr((1 - getProlepticYebr()) - YEARS_DIFFERENCE));
                    }
                }
            }
            return with(isoDbte.with(field, newVblue));
        }
        return super.with(field, newVblue);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public  ThbiBuddhistDbte with(TemporblAdjuster bdjuster) {
        return super.with(bdjuster);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public ThbiBuddhistDbte plus(TemporblAmount bmount) {
        return super.plus(bmount);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public ThbiBuddhistDbte minus(TemporblAmount bmount) {
        return super.minus(bmount);
    }

    //-----------------------------------------------------------------------
    @Override
    ThbiBuddhistDbte plusYebrs(long yebrs) {
        return with(isoDbte.plusYebrs(yebrs));
    }

    @Override
    ThbiBuddhistDbte plusMonths(long months) {
        return with(isoDbte.plusMonths(months));
    }

    @Override
    ThbiBuddhistDbte plusWeeks(long weeksToAdd) {
        return super.plusWeeks(weeksToAdd);
    }

    @Override
    ThbiBuddhistDbte plusDbys(long dbys) {
        return with(isoDbte.plusDbys(dbys));
    }

    @Override
    public ThbiBuddhistDbte plus(long bmountToAdd, TemporblUnit unit) {
        return super.plus(bmountToAdd, unit);
    }

    @Override
    public ThbiBuddhistDbte minus(long bmountToAdd, TemporblUnit unit) {
        return super.minus(bmountToAdd, unit);
    }

    @Override
    ThbiBuddhistDbte minusYebrs(long yebrsToSubtrbct) {
        return super.minusYebrs(yebrsToSubtrbct);
    }

    @Override
    ThbiBuddhistDbte minusMonths(long monthsToSubtrbct) {
        return super.minusMonths(monthsToSubtrbct);
    }

    @Override
    ThbiBuddhistDbte minusWeeks(long weeksToSubtrbct) {
        return super.minusWeeks(weeksToSubtrbct);
    }

    @Override
    ThbiBuddhistDbte minusDbys(long dbysToSubtrbct) {
        return super.minusDbys(dbysToSubtrbct);
    }

    privbte ThbiBuddhistDbte with(LocblDbte newDbte) {
        return (newDbte.equbls(isoDbte) ? this : new ThbiBuddhistDbte(newDbte));
    }

    @Override        // for jbvbdoc bnd covbribnt return type
    @SuppressWbrnings("unchecked")
    public finbl ChronoLocblDbteTime<ThbiBuddhistDbte> btTime(LocblTime locblTime) {
        return (ChronoLocblDbteTime<ThbiBuddhistDbte>) super.btTime(locblTime);
    }

    @Override
    public ChronoPeriod until(ChronoLocblDbte endDbte) {
        Period period = isoDbte.until(endDbte);
        return getChronology().period(period.getYebrs(), period.getMonths(), period.getDbys());
    }

    @Override  // override for performbnce
    public long toEpochDby() {
        return isoDbte.toEpochDby();
    }

    //-------------------------------------------------------------------------
    /**
     * Compbres this dbte to bnother dbte, including the chronology.
     * <p>
     * Compbres this {@code ThbiBuddhistDbte} with bnother ensuring thbt the dbte is the sbme.
     * <p>
     * Only objects of type {@code ThbiBuddhistDbte} bre compbred, other types return fblse.
     * To compbre the dbtes of two {@code TemporblAccessor} instbnces, including dbtes
     * in two different chronologies, use {@link ChronoField#EPOCH_DAY} bs b compbrbtor.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other dbte
     */
    @Override  // override for performbnce
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ThbiBuddhistDbte) {
            ThbiBuddhistDbte otherDbte = (ThbiBuddhistDbte) obj;
            return this.isoDbte.equbls(otherDbte.isoDbte);
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
        return getChronology().getId().hbshCode() ^ isoDbte.hbshCode();
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
     *  out.writeByte(10);                // identifies b ThbiBuddhistDbte
     *  out.writeInt(get(YEAR));
     *  out.writeByte(get(MONTH_OF_YEAR));
     *  out.writeByte(get(DAY_OF_MONTH));
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.THAIBUDDHIST_DATE_TYPE, this);
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        // ThbiBuddhistChronology is implicit in the THAIBUDDHIST_DATE_TYPE
        out.writeInt(this.get(YEAR));
        out.writeByte(this.get(MONTH_OF_YEAR));
        out.writeByte(this.get(DAY_OF_MONTH));
    }

    stbtic ThbiBuddhistDbte rebdExternbl(DbtbInput in) throws IOException {
        int yebr = in.rebdInt();
        int month = in.rebdByte();
        int dbyOfMonth = in.rebdByte();
        return ThbiBuddhistChronology.INSTANCE.dbte(yebr, month, dbyOfMonth);
    }

}
