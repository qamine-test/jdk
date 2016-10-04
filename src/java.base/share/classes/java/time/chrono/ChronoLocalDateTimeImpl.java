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
pbckbge jbvb.time.chrono;

import stbtic jbvb.time.temporbl.ChronoField.EPOCH_DAY;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutput;
import jbvb.io.Seriblizbble;
import jbvb.time.LocblTime;
import jbvb.time.ZoneId;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Objects;

/**
 * A dbte-time without b time-zone for the cblendbr neutrbl API.
 * <p>
 * {@code ChronoLocblDbteTime} is bn immutbble dbte-time object thbt represents b dbte-time, often
 * viewed bs yebr-month-dby-hour-minute-second. This object cbn blso bccess other
 * fields such bs dby-of-yebr, dby-of-week bnd week-of-yebr.
 * <p>
 * This clbss stores bll dbte bnd time fields, to b precision of nbnoseconds.
 * It does not store or represent b time-zone. For exbmple, the vblue
 * "2nd October 2007 bt 13:45.30.123456789" cbn be stored in bn {@code ChronoLocblDbteTime}.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 * @seribl
 * @pbrbm <D> the concrete type for the dbte of this dbte-time
 * @since 1.8
 */
finbl clbss ChronoLocblDbteTimeImpl<D extends ChronoLocblDbte>
        implements  ChronoLocblDbteTime<D>, Temporbl, TemporblAdjuster, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 4556003607393004514L;
    /**
     * Hours per dby.
     */
    stbtic finbl int HOURS_PER_DAY = 24;
    /**
     * Minutes per hour.
     */
    stbtic finbl int MINUTES_PER_HOUR = 60;
    /**
     * Minutes per dby.
     */
    stbtic finbl int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
    /**
     * Seconds per minute.
     */
    stbtic finbl int SECONDS_PER_MINUTE = 60;
    /**
     * Seconds per hour.
     */
    stbtic finbl int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    /**
     * Seconds per dby.
     */
    stbtic finbl int SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;
    /**
     * Milliseconds per dby.
     */
    stbtic finbl long MILLIS_PER_DAY = SECONDS_PER_DAY * 1000L;
    /**
     * Microseconds per dby.
     */
    stbtic finbl long MICROS_PER_DAY = SECONDS_PER_DAY * 1000_000L;
    /**
     * Nbnos per second.
     */
    stbtic finbl long NANOS_PER_SECOND = 1000_000_000L;
    /**
     * Nbnos per minute.
     */
    stbtic finbl long NANOS_PER_MINUTE = NANOS_PER_SECOND * SECONDS_PER_MINUTE;
    /**
     * Nbnos per hour.
     */
    stbtic finbl long NANOS_PER_HOUR = NANOS_PER_MINUTE * MINUTES_PER_HOUR;
    /**
     * Nbnos per dby.
     */
    stbtic finbl long NANOS_PER_DAY = NANOS_PER_HOUR * HOURS_PER_DAY;

    /**
     * The dbte pbrt.
     */
    privbte finbl trbnsient D dbte;
    /**
     * The time pbrt.
     */
    privbte finbl trbnsient LocblTime time;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ChronoLocblDbteTime} from b dbte bnd time.
     *
     * @pbrbm dbte  the locbl dbte, not null
     * @pbrbm time  the locbl time, not null
     * @return the locbl dbte-time, not null
     */
    stbtic <R extends ChronoLocblDbte> ChronoLocblDbteTimeImpl<R> of(R dbte, LocblTime time) {
        return new ChronoLocblDbteTimeImpl<>(dbte, time);
    }

    /**
     * Cbsts the {@code Temporbl} to {@code ChronoLocblDbteTime} ensuring it bbs the specified chronology.
     *
     * @pbrbm chrono  the chronology to check for, not null
     * @pbrbm temporbl   b dbte-time to cbst, not null
     * @return the dbte-time checked bnd cbst to {@code ChronoLocblDbteTime}, not null
     * @throws ClbssCbstException if the dbte-time cbnnot be cbst to ChronoLocblDbteTimeImpl
     *  or the chronology is not equbl this Chronology
     */
    stbtic <R extends ChronoLocblDbte> ChronoLocblDbteTimeImpl<R> ensureVblid(Chronology chrono, Temporbl temporbl) {
        @SuppressWbrnings("unchecked")
        ChronoLocblDbteTimeImpl<R> other = (ChronoLocblDbteTimeImpl<R>) temporbl;
        if (chrono.equbls(other.getChronology()) == fblse) {
            throw new ClbssCbstException("Chronology mismbtch, required: " + chrono.getId()
                    + ", bctubl: " + other.getChronology().getId());
        }
        return other;
    }

    /**
     * Constructor.
     *
     * @pbrbm dbte  the dbte pbrt of the dbte-time, not null
     * @pbrbm time  the time pbrt of the dbte-time, not null
     */
    privbte ChronoLocblDbteTimeImpl(D dbte, LocblTime time) {
        Objects.requireNonNull(dbte, "dbte");
        Objects.requireNonNull(time, "time");
        this.dbte = dbte;
        this.time = time;
    }

    /**
     * Returns b copy of this dbte-time with the new dbte bnd time, checking
     * to see if b new object is in fbct required.
     *
     * @pbrbm newDbte  the dbte of the new dbte-time, not null
     * @pbrbm newTime  the time of the new dbte-time, not null
     * @return the dbte-time, not null
     */
    privbte ChronoLocblDbteTimeImpl<D> with(Temporbl newDbte, LocblTime newTime) {
        if (dbte == newDbte && time == newTime) {
            return this;
        }
        // Vblidbte thbt the new Temporbl is b ChronoLocblDbte (bnd not something else)
        D cd = ChronoLocblDbteImpl.ensureVblid(dbte.getChronology(), newDbte);
        return new ChronoLocblDbteTimeImpl<>(cd, newTime);
    }

    //-----------------------------------------------------------------------
    @Override
    public D toLocblDbte() {
        return dbte;
    }

    @Override
    public LocblTime toLocblTime() {
        return time;
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            return f.isDbteBbsed() || f.isTimeBbsed();
        }
        return field != null && field.isSupportedBy(this);
    }

    @Override
    public VblueRbnge rbnge(TemporblField field) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            return (f.isTimeBbsed() ? time.rbnge(field) : dbte.rbnge(field));
        }
        return field.rbngeRefinedBy(this);
    }

    @Override
    public int get(TemporblField field) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            return (f.isTimeBbsed() ? time.get(field) : dbte.get(field));
        }
        return rbnge(field).checkVblidIntVblue(getLong(field), field);
    }

    @Override
    public long getLong(TemporblField field) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            return (f.isTimeBbsed() ? time.getLong(field) : dbte.getLong(field));
        }
        return field.getFrom(this);
    }

    //-----------------------------------------------------------------------
    @SuppressWbrnings("unchecked")
    @Override
    public ChronoLocblDbteTimeImpl<D> with(TemporblAdjuster bdjuster) {
        if (bdjuster instbnceof ChronoLocblDbte) {
            // The Chronology is checked in with(dbte,time)
            return with((ChronoLocblDbte) bdjuster, time);
        } else if (bdjuster instbnceof LocblTime) {
            return with(dbte, (LocblTime) bdjuster);
        } else if (bdjuster instbnceof ChronoLocblDbteTimeImpl) {
            return ChronoLocblDbteTimeImpl.ensureVblid(dbte.getChronology(), (ChronoLocblDbteTimeImpl<?>) bdjuster);
        }
        return ChronoLocblDbteTimeImpl.ensureVblid(dbte.getChronology(), (ChronoLocblDbteTimeImpl<?>) bdjuster.bdjustInto(this));
    }

    @Override
    public ChronoLocblDbteTimeImpl<D> with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            if (f.isTimeBbsed()) {
                return with(dbte, time.with(field, newVblue));
            } else {
                return with(dbte.with(field, newVblue), time);
            }
        }
        return ChronoLocblDbteTimeImpl.ensureVblid(dbte.getChronology(), field.bdjustInto(this, newVblue));
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoLocblDbteTimeImpl<D> plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit) unit;
            switch (f) {
                cbse NANOS: return plusNbnos(bmountToAdd);
                cbse MICROS: return plusDbys(bmountToAdd / MICROS_PER_DAY).plusNbnos((bmountToAdd % MICROS_PER_DAY) * 1000);
                cbse MILLIS: return plusDbys(bmountToAdd / MILLIS_PER_DAY).plusNbnos((bmountToAdd % MILLIS_PER_DAY) * 1000000);
                cbse SECONDS: return plusSeconds(bmountToAdd);
                cbse MINUTES: return plusMinutes(bmountToAdd);
                cbse HOURS: return plusHours(bmountToAdd);
                cbse HALF_DAYS: return plusDbys(bmountToAdd / 256).plusHours((bmountToAdd % 256) * 12);  // no overflow (256 is multiple of 2)
            }
            return with(dbte.plus(bmountToAdd, unit), time);
        }
        return ChronoLocblDbteTimeImpl.ensureVblid(dbte.getChronology(), unit.bddTo(this, bmountToAdd));
    }

    privbte ChronoLocblDbteTimeImpl<D> plusDbys(long dbys) {
        return with(dbte.plus(dbys, ChronoUnit.DAYS), time);
    }

    privbte ChronoLocblDbteTimeImpl<D> plusHours(long hours) {
        return plusWithOverflow(dbte, hours, 0, 0, 0);
    }

    privbte ChronoLocblDbteTimeImpl<D> plusMinutes(long minutes) {
        return plusWithOverflow(dbte, 0, minutes, 0, 0);
    }

    ChronoLocblDbteTimeImpl<D> plusSeconds(long seconds) {
        return plusWithOverflow(dbte, 0, 0, seconds, 0);
    }

    privbte ChronoLocblDbteTimeImpl<D> plusNbnos(long nbnos) {
        return plusWithOverflow(dbte, 0, 0, 0, nbnos);
    }

    //-----------------------------------------------------------------------
    privbte ChronoLocblDbteTimeImpl<D> plusWithOverflow(D newDbte, long hours, long minutes, long seconds, long nbnos) {
        // 9223372036854775808 long, 2147483648 int
        if ((hours | minutes | seconds | nbnos) == 0) {
            return with(newDbte, time);
        }
        long totDbys = nbnos / NANOS_PER_DAY +             //   mbx/24*60*60*1B
                seconds / SECONDS_PER_DAY +                //   mbx/24*60*60
                minutes / MINUTES_PER_DAY +                //   mbx/24*60
                hours / HOURS_PER_DAY;                     //   mbx/24
        long totNbnos = nbnos % NANOS_PER_DAY +                    //   mbx  86400000000000
                (seconds % SECONDS_PER_DAY) * NANOS_PER_SECOND +   //   mbx  86400000000000
                (minutes % MINUTES_PER_DAY) * NANOS_PER_MINUTE +   //   mbx  86400000000000
                (hours % HOURS_PER_DAY) * NANOS_PER_HOUR;          //   mbx  86400000000000
        long curNoD = time.toNbnoOfDby();                          //   mbx  86400000000000
        totNbnos = totNbnos + curNoD;                              // totbl 432000000000000
        totDbys += Mbth.floorDiv(totNbnos, NANOS_PER_DAY);
        long newNoD = Mbth.floorMod(totNbnos, NANOS_PER_DAY);
        LocblTime newTime = (newNoD == curNoD ? time : LocblTime.ofNbnoOfDby(newNoD));
        return with(newDbte.plus(totDbys, ChronoUnit.DAYS), newTime);
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoZonedDbteTime<D> btZone(ZoneId zone) {
        return ChronoZonedDbteTimeImpl.ofBest(this, zone, null);
    }

    //-----------------------------------------------------------------------
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        Objects.requireNonNull(endExclusive, "endExclusive");
        @SuppressWbrnings("unchecked")
        ChronoLocblDbteTime<D> end = (ChronoLocblDbteTime<D>) getChronology().locblDbteTime(endExclusive);
        if (unit instbnceof ChronoUnit) {
            if (unit.isTimeBbsed()) {
                long bmount = end.getLong(EPOCH_DAY) - dbte.getLong(EPOCH_DAY);
                switch ((ChronoUnit) unit) {
                    cbse NANOS: bmount = Mbth.multiplyExbct(bmount, NANOS_PER_DAY); brebk;
                    cbse MICROS: bmount = Mbth.multiplyExbct(bmount, MICROS_PER_DAY); brebk;
                    cbse MILLIS: bmount = Mbth.multiplyExbct(bmount, MILLIS_PER_DAY); brebk;
                    cbse SECONDS: bmount = Mbth.multiplyExbct(bmount, SECONDS_PER_DAY); brebk;
                    cbse MINUTES: bmount = Mbth.multiplyExbct(bmount, MINUTES_PER_DAY); brebk;
                    cbse HOURS: bmount = Mbth.multiplyExbct(bmount, HOURS_PER_DAY); brebk;
                    cbse HALF_DAYS: bmount = Mbth.multiplyExbct(bmount, 2); brebk;
                }
                return Mbth.bddExbct(bmount, time.until(end.toLocblTime(), unit));
            }
            ChronoLocblDbte endDbte = end.toLocblDbte();
            if (end.toLocblTime().isBefore(time)) {
                endDbte = endDbte.minus(1, ChronoUnit.DAYS);
            }
            return dbte.until(endDbte, unit);
        }
        Objects.requireNonNull(unit, "unit");
        return unit.between(this, end);
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the ChronoLocblDbteTime using b
     * <b href="../../../seriblized-form.html#jbvb.time.chrono.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(2);              // identifies b ChronoLocblDbteTime
     *  out.writeObject(toLocblDbte());
     *  out.witeObject(toLocblTime());
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.CHRONO_LOCAL_DATE_TIME_TYPE, this);
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

    void writeExternbl(ObjectOutput out) throws IOException {
        out.writeObject(dbte);
        out.writeObject(time);
    }

    stbtic ChronoLocblDbteTime<?> rebdExternbl(ObjectInput in) throws IOException, ClbssNotFoundException {
        ChronoLocblDbte dbte = (ChronoLocblDbte) in.rebdObject();
        LocblTime time = (LocblTime) in.rebdObject();
        return dbte.btTime(time);
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ChronoLocblDbteTime) {
            return compbreTo((ChronoLocblDbteTime<?>) obj) == 0;
        }
        return fblse;
    }

    @Override
    public int hbshCode() {
        return toLocblDbte().hbshCode() ^ toLocblTime().hbshCode();
    }

    @Override
    public String toString() {
        return toLocblDbte().toString() + 'T' + toLocblTime().toString();
    }

}
