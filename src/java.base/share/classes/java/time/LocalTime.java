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

import stbtic jbvb.time.temporbl.ChronoField.HOUR_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.MICRO_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.MINUTE_OF_HOUR;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoField.SECOND_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.SECOND_OF_MINUTE;
import stbtic jbvb.time.temporbl.ChronoUnit.NANOS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
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
import jbvb.util.Objects;

/**
 * A time without b time-zone in the ISO-8601 cblendbr system,
 * such bs {@code 10:15:30}.
 * <p>
 * {@code LocblTime} is bn immutbble dbte-time object thbt represents b time,
 * often viewed bs hour-minute-second.
 * Time is represented to nbnosecond precision.
 * For exbmple, the vblue "13:45.30.123456789" cbn be stored in b {@code LocblTime}.
 * <p>
 * This clbss does not store or represent b dbte or time-zone.
 * Instebd, it is b description of the locbl time bs seen on b wbll clock.
 * It cbnnot represent bn instbnt on the time-line without bdditionbl informbtion
 * such bs bn offset or time-zone.
 * <p>
 * The ISO-8601 cblendbr system is the modern civil cblendbr system used todby
 * in most of the world. This API bssumes thbt bll cblendbr systems use the sbme
 * representbtion, this clbss, for time-of-dby.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code LocblTime} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss LocblTime
        implements Temporbl, TemporblAdjuster, Compbrbble<LocblTime>, Seriblizbble {

    /**
     * The minimum supported {@code LocblTime}, '00:00'.
     * This is the time of midnight bt the stbrt of the dby.
     */
    public stbtic finbl LocblTime MIN;
    /**
     * The mbximum supported {@code LocblTime}, '23:59:59.999999999'.
     * This is the time just before midnight bt the end of the dby.
     */
    public stbtic finbl LocblTime MAX;
    /**
     * The time of midnight bt the stbrt of the dby, '00:00'.
     */
    public stbtic finbl LocblTime MIDNIGHT;
    /**
     * The time of noon in the middle of the dby, '12:00'.
     */
    public stbtic finbl LocblTime NOON;
    /**
     * Constbnts for the locbl time of ebch hour.
     */
    privbte stbtic finbl LocblTime[] HOURS = new LocblTime[24];
    stbtic {
        for (int i = 0; i < HOURS.length; i++) {
            HOURS[i] = new LocblTime(i, 0, 0, 0);
        }
        MIDNIGHT = HOURS[0];
        NOON = HOURS[12];
        MIN = HOURS[0];
        MAX = new LocblTime(23, 59, 59, 999_999_999);
    }

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
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 6414437269572265201L;

    /**
     * The hour.
     */
    privbte finbl byte hour;
    /**
     * The minute.
     */
    privbte finbl byte minute;
    /**
     * The second.
     */
    privbte finbl byte second;
    /**
     * The nbnosecond.
     */
    privbte finbl int nbno;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current time from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current time.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current time using the system clock bnd defbult time-zone, not null
     */
    public stbtic LocblTime now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current time from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current time.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current time using the system clock, not null
     */
    public stbtic LocblTime now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current time from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current time.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current time, not null
     */
    public stbtic LocblTime now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        // inline OffsetTime fbctory to bvoid crebting object bnd InstbntProvider checks
        finbl Instbnt now = clock.instbnt();  // cblled once
        ZoneOffset offset = clock.getZone().getRules().getOffset(now);
        long locblSecond = now.getEpochSecond() + offset.getTotblSeconds();  // overflow cbught lbter
        int secsOfDby = (int) Mbth.floorMod(locblSecond, SECONDS_PER_DAY);
        return ofNbnoOfDby(secsOfDby * NANOS_PER_SECOND + now.getNbno());
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblTime} from bn hour bnd minute.
     * <p>
     * This returns b {@code LocblTime} with the specified hour bnd minute.
     * The second bnd nbnosecond fields will be set to zero.
     *
     * @pbrbm hour  the hour-of-dby to represent, from 0 to 23
     * @pbrbm minute  the minute-of-hour to represent, from 0 to 59
     * @return the locbl time, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    public stbtic LocblTime of(int hour, int minute) {
        HOUR_OF_DAY.checkVblidVblue(hour);
        if (minute == 0) {
            return HOURS[hour];  // for performbnce
        }
        MINUTE_OF_HOUR.checkVblidVblue(minute);
        return new LocblTime(hour, minute, 0, 0);
    }

    /**
     * Obtbins bn instbnce of {@code LocblTime} from bn hour, minute bnd second.
     * <p>
     * This returns b {@code LocblTime} with the specified hour, minute bnd second.
     * The nbnosecond field will be set to zero.
     *
     * @pbrbm hour  the hour-of-dby to represent, from 0 to 23
     * @pbrbm minute  the minute-of-hour to represent, from 0 to 59
     * @pbrbm second  the second-of-minute to represent, from 0 to 59
     * @return the locbl time, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    public stbtic LocblTime of(int hour, int minute, int second) {
        HOUR_OF_DAY.checkVblidVblue(hour);
        if ((minute | second) == 0) {
            return HOURS[hour];  // for performbnce
        }
        MINUTE_OF_HOUR.checkVblidVblue(minute);
        SECOND_OF_MINUTE.checkVblidVblue(second);
        return new LocblTime(hour, minute, second, 0);
    }

    /**
     * Obtbins bn instbnce of {@code LocblTime} from bn hour, minute, second bnd nbnosecond.
     * <p>
     * This returns b {@code LocblTime} with the specified hour, minute, second bnd nbnosecond.
     *
     * @pbrbm hour  the hour-of-dby to represent, from 0 to 23
     * @pbrbm minute  the minute-of-hour to represent, from 0 to 59
     * @pbrbm second  the second-of-minute to represent, from 0 to 59
     * @pbrbm nbnoOfSecond  the nbno-of-second to represent, from 0 to 999,999,999
     * @return the locbl time, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    public stbtic LocblTime of(int hour, int minute, int second, int nbnoOfSecond) {
        HOUR_OF_DAY.checkVblidVblue(hour);
        MINUTE_OF_HOUR.checkVblidVblue(minute);
        SECOND_OF_MINUTE.checkVblidVblue(second);
        NANO_OF_SECOND.checkVblidVblue(nbnoOfSecond);
        return crebte(hour, minute, second, nbnoOfSecond);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblTime} from b second-of-dby vblue.
     * <p>
     * This returns b {@code LocblTime} with the specified second-of-dby.
     * The nbnosecond field will be set to zero.
     *
     * @pbrbm secondOfDby  the second-of-dby, from {@code 0} to {@code 24 * 60 * 60 - 1}
     * @return the locbl time, not null
     * @throws DbteTimeException if the second-of-dby vblue is invblid
     */
    public stbtic LocblTime ofSecondOfDby(long secondOfDby) {
        SECOND_OF_DAY.checkVblidVblue(secondOfDby);
        int hours = (int) (secondOfDby / SECONDS_PER_HOUR);
        secondOfDby -= hours * SECONDS_PER_HOUR;
        int minutes = (int) (secondOfDby / SECONDS_PER_MINUTE);
        secondOfDby -= minutes * SECONDS_PER_MINUTE;
        return crebte(hours, minutes, (int) secondOfDby, 0);
    }

    /**
     * Obtbins bn instbnce of {@code LocblTime} from b nbnos-of-dby vblue.
     * <p>
     * This returns b {@code LocblTime} with the specified nbnosecond-of-dby.
     *
     * @pbrbm nbnoOfDby  the nbno of dby, from {@code 0} to {@code 24 * 60 * 60 * 1,000,000,000 - 1}
     * @return the locbl time, not null
     * @throws DbteTimeException if the nbnos of dby vblue is invblid
     */
    public stbtic LocblTime ofNbnoOfDby(long nbnoOfDby) {
        NANO_OF_DAY.checkVblidVblue(nbnoOfDby);
        int hours = (int) (nbnoOfDby / NANOS_PER_HOUR);
        nbnoOfDby -= hours * NANOS_PER_HOUR;
        int minutes = (int) (nbnoOfDby / NANOS_PER_MINUTE);
        nbnoOfDby -= minutes * NANOS_PER_MINUTE;
        int seconds = (int) (nbnoOfDby / NANOS_PER_SECOND);
        nbnoOfDby -= seconds * NANOS_PER_SECOND;
        return crebte(hours, minutes, seconds, (int) nbnoOfDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblTime} from b temporbl object.
     * <p>
     * This obtbins b locbl time bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code LocblTime}.
     * <p>
     * The conversion uses the {@link TemporblQueries#locblTime()} query, which relies
     * on extrbcting the {@link ChronoField#NANO_OF_DAY NANO_OF_DAY} field.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code LocblTime::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the locbl time, not null
     * @throws DbteTimeException if unbble to convert to b {@code LocblTime}
     */
    public stbtic LocblTime from(TemporblAccessor temporbl) {
        Objects.requireNonNull(temporbl, "temporbl");
        LocblTime time = temporbl.query(TemporblQueries.locblTime());
        if (time == null) {
            throw new DbteTimeException("Unbble to obtbin LocblTime from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme());
        }
        return time;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code LocblTime} from b text string such bs {@code 10:15}.
     * <p>
     * The string must represent b vblid time bnd is pbrsed using
     * {@link jbvb.time.formbt.DbteTimeFormbtter#ISO_LOCAL_TIME}.
     *
     * @pbrbm text  the text to pbrse such bs "10:15:30", not null
     * @return the pbrsed locbl time, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic LocblTime pbrse(ChbrSequence text) {
        return pbrse(text, DbteTimeFormbtter.ISO_LOCAL_TIME);
    }

    /**
     * Obtbins bn instbnce of {@code LocblTime} from b text string using b specific formbtter.
     * <p>
     * The text is pbrsed using the formbtter, returning b time.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the pbrsed locbl time, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic LocblTime pbrse(ChbrSequence text, DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.pbrse(text, LocblTime::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes b locbl time from the hour, minute, second bnd nbnosecond fields.
     * <p>
     * This fbctory mby return b cbched vblue, but bpplicbtions must not rely on this.
     *
     * @pbrbm hour  the hour-of-dby to represent, vblidbted from 0 to 23
     * @pbrbm minute  the minute-of-hour to represent, vblidbted from 0 to 59
     * @pbrbm second  the second-of-minute to represent, vblidbted from 0 to 59
     * @pbrbm nbnoOfSecond  the nbno-of-second to represent, vblidbted from 0 to 999,999,999
     * @return the locbl time, not null
     */
    privbte stbtic LocblTime crebte(int hour, int minute, int second, int nbnoOfSecond) {
        if ((minute | second | nbnoOfSecond) == 0) {
            return HOURS[hour];
        }
        return new LocblTime(hour, minute, second, nbnoOfSecond);
    }

    /**
     * Constructor, previously vblidbted.
     *
     * @pbrbm hour  the hour-of-dby to represent, vblidbted from 0 to 23
     * @pbrbm minute  the minute-of-hour to represent, vblidbted from 0 to 59
     * @pbrbm second  the second-of-minute to represent, vblidbted from 0 to 59
     * @pbrbm nbnoOfSecond  the nbno-of-second to represent, vblidbted from 0 to 999,999,999
     */
    privbte LocblTime(int hour, int minute, int second, int nbnoOfSecond) {
        this.hour = (byte) hour;
        this.minute = (byte) minute;
        this.second = (byte) second;
        this.nbno = nbnoOfSecond;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this time cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge},
     * {@link #get(TemporblField) get} bnd {@link #with(TemporblField, long)}
     * methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The supported fields bre:
     * <ul>
     * <li>{@code NANO_OF_SECOND}
     * <li>{@code NANO_OF_DAY}
     * <li>{@code MICRO_OF_SECOND}
     * <li>{@code MICRO_OF_DAY}
     * <li>{@code MILLI_OF_SECOND}
     * <li>{@code MILLI_OF_DAY}
     * <li>{@code SECOND_OF_MINUTE}
     * <li>{@code SECOND_OF_DAY}
     * <li>{@code MINUTE_OF_HOUR}
     * <li>{@code MINUTE_OF_DAY}
     * <li>{@code HOUR_OF_AMPM}
     * <li>{@code CLOCK_HOUR_OF_AMPM}
     * <li>{@code HOUR_OF_DAY}
     * <li>{@code CLOCK_HOUR_OF_DAY}
     * <li>{@code AMPM_OF_DAY}
     * </ul>
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this time, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field.isTimeBbsed();
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to, or subtrbcted from, this time.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     * <p>
     * If the unit is b {@link ChronoUnit} then the query is implemented here.
     * The supported units bre:
     * <ul>
     * <li>{@code NANOS}
     * <li>{@code MICROS}
     * <li>{@code MILLIS}
     * <li>{@code SECONDS}
     * <li>{@code MINUTES}
     * <li>{@code HOURS}
     * <li>{@code HALF_DAYS}
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
        if (unit instbnceof ChronoUnit) {
            return unit.isTimeBbsed();
        }
        return unit != null && unit.isSupportedBy(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This time is used to enhbnce the bccurbcy of the returned rbnge.
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
    @Override  // override for Jbvbdoc
    public VblueRbnge rbnge(TemporblField field) {
        return Temporbl.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this time bs bn {@code int}.
     * <p>
     * This queries this time for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this time, except {@code NANO_OF_DAY} bnd {@code MICRO_OF_DAY}
     * which bre too lbrge to fit in bn {@code int} bnd throw b {@code UnsupportedTemporblTypeException}.
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
        return Temporbl.super.get(field);
    }

    /**
     * Gets the vblue of the specified field from this time bs b {@code long}.
     * <p>
     * This queries this time for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@link #isSupported(TemporblField) supported fields} will return vblid
     * vblues bbsed on this time.
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
            if (field == NANO_OF_DAY) {
                return toNbnoOfDby();
            }
            if (field == MICRO_OF_DAY) {
                return toNbnoOfDby() / 1000;
            }
            return get0(field);
        }
        return field.getFrom(this);
    }

    privbte int get0(TemporblField field) {
        switch ((ChronoField) field) {
            cbse NANO_OF_SECOND: return nbno;
            cbse NANO_OF_DAY: throw new UnsupportedTemporblTypeException("Invblid field 'NbnoOfDby' for get() method, use getLong() instebd");
            cbse MICRO_OF_SECOND: return nbno / 1000;
            cbse MICRO_OF_DAY: throw new UnsupportedTemporblTypeException("Invblid field 'MicroOfDby' for get() method, use getLong() instebd");
            cbse MILLI_OF_SECOND: return nbno / 1000_000;
            cbse MILLI_OF_DAY: return (int) (toNbnoOfDby() / 1000_000);
            cbse SECOND_OF_MINUTE: return second;
            cbse SECOND_OF_DAY: return toSecondOfDby();
            cbse MINUTE_OF_HOUR: return minute;
            cbse MINUTE_OF_DAY: return hour * 60 + minute;
            cbse HOUR_OF_AMPM: return hour % 12;
            cbse CLOCK_HOUR_OF_AMPM: int hbm = hour % 12; return (hbm % 12 == 0 ? 12 : hbm);
            cbse HOUR_OF_DAY: return hour;
            cbse CLOCK_HOUR_OF_DAY: return (hour == 0 ? 24 : hour);
            cbse AMPM_OF_DAY: return hour / 12;
        }
        throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the hour-of-dby field.
     *
     * @return the hour-of-dby, from 0 to 23
     */
    public int getHour() {
        return hour;
    }

    /**
     * Gets the minute-of-hour field.
     *
     * @return the minute-of-hour, from 0 to 59
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Gets the second-of-minute field.
     *
     * @return the second-of-minute, from 0 to 59
     */
    public int getSecond() {
        return second;
    }

    /**
     * Gets the nbno-of-second field.
     *
     * @return the nbno-of-second, from 0 to 999,999,999
     */
    public int getNbno() {
        return nbno;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns bn bdjusted copy of this time.
     * <p>
     * This returns b {@code LocblTime}, bbsed on this one, with the time bdjusted.
     * The bdjustment tbkes plbce using the specified bdjuster strbtegy object.
     * Rebd the documentbtion of the bdjuster to understbnd whbt bdjustment will be mbde.
     * <p>
     * A simple bdjuster might simply set the one of the fields, such bs the hour field.
     * A more complex bdjuster might set the time to the lbst hour of the dby.
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblAdjuster#bdjustInto(Temporbl)} method on the
     * specified bdjuster pbssing {@code this} bs the brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bdjuster the bdjuster to use, not null
     * @return b {@code LocblTime} bbsed on {@code this} with the bdjustment mbde, not null
     * @throws DbteTimeException if the bdjustment cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblTime with(TemporblAdjuster bdjuster) {
        // optimizbtions
        if (bdjuster instbnceof LocblTime) {
            return (LocblTime) bdjuster;
        }
        return (LocblTime) bdjuster.bdjustInto(this);
    }

    /**
     * Returns b copy of this time with the specified field set to b new vblue.
     * <p>
     * This returns b {@code LocblTime}, bbsed on this one, with the vblue
     * for the specified field chbnged.
     * This cbn be used to chbnge bny supported field, such bs the hour, minute or second.
     * If it is not possible to set the vblue, becbuse the field is not supported or for
     * some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the bdjustment is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code NANO_OF_SECOND} -
     *  Returns b {@code LocblTime} with the specified nbno-of-second.
     *  The hour, minute bnd second will be unchbnged.
     * <li>{@code NANO_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified nbno-of-dby.
     *  This completely replbces the time bnd is equivblent to {@link #ofNbnoOfDby(long)}.
     * <li>{@code MICRO_OF_SECOND} -
     *  Returns b {@code LocblTime} with the nbno-of-second replbced by the specified
     *  micro-of-second multiplied by 1,000.
     *  The hour, minute bnd second will be unchbnged.
     * <li>{@code MICRO_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified micro-of-dby.
     *  This completely replbces the time bnd is equivblent to using {@link #ofNbnoOfDby(long)}
     *  with the micro-of-dby multiplied by 1,000.
     * <li>{@code MILLI_OF_SECOND} -
     *  Returns b {@code LocblTime} with the nbno-of-second replbced by the specified
     *  milli-of-second multiplied by 1,000,000.
     *  The hour, minute bnd second will be unchbnged.
     * <li>{@code MILLI_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified milli-of-dby.
     *  This completely replbces the time bnd is equivblent to using {@link #ofNbnoOfDby(long)}
     *  with the milli-of-dby multiplied by 1,000,000.
     * <li>{@code SECOND_OF_MINUTE} -
     *  Returns b {@code LocblTime} with the specified second-of-minute.
     *  The hour, minute bnd nbno-of-second will be unchbnged.
     * <li>{@code SECOND_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified second-of-dby.
     *  The nbno-of-second will be unchbnged.
     * <li>{@code MINUTE_OF_HOUR} -
     *  Returns b {@code LocblTime} with the specified minute-of-hour.
     *  The hour, second-of-minute bnd nbno-of-second will be unchbnged.
     * <li>{@code MINUTE_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified minute-of-dby.
     *  The second-of-minute bnd nbno-of-second will be unchbnged.
     * <li>{@code HOUR_OF_AMPM} -
     *  Returns b {@code LocblTime} with the specified hour-of-bm-pm.
     *  The AM/PM, minute-of-hour, second-of-minute bnd nbno-of-second will be unchbnged.
     * <li>{@code CLOCK_HOUR_OF_AMPM} -
     *  Returns b {@code LocblTime} with the specified clock-hour-of-bm-pm.
     *  The AM/PM, minute-of-hour, second-of-minute bnd nbno-of-second will be unchbnged.
     * <li>{@code HOUR_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified hour-of-dby.
     *  The minute-of-hour, second-of-minute bnd nbno-of-second will be unchbnged.
     * <li>{@code CLOCK_HOUR_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified clock-hour-of-dby.
     *  The minute-of-hour, second-of-minute bnd nbno-of-second will be unchbnged.
     * <li>{@code AMPM_OF_DAY} -
     *  Returns b {@code LocblTime} with the specified AM/PM.
     *  The hour-of-bm-pm, minute-of-hour, second-of-minute bnd nbno-of-second will be unchbnged.
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
     * @return b {@code LocblTime} bbsed on {@code this} with the specified field set, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblTime with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            f.checkVblidVblue(newVblue);
            switch (f) {
                cbse NANO_OF_SECOND: return withNbno((int) newVblue);
                cbse NANO_OF_DAY: return LocblTime.ofNbnoOfDby(newVblue);
                cbse MICRO_OF_SECOND: return withNbno((int) newVblue * 1000);
                cbse MICRO_OF_DAY: return LocblTime.ofNbnoOfDby(newVblue * 1000);
                cbse MILLI_OF_SECOND: return withNbno((int) newVblue * 1000_000);
                cbse MILLI_OF_DAY: return LocblTime.ofNbnoOfDby(newVblue * 1000_000);
                cbse SECOND_OF_MINUTE: return withSecond((int) newVblue);
                cbse SECOND_OF_DAY: return plusSeconds(newVblue - toSecondOfDby());
                cbse MINUTE_OF_HOUR: return withMinute((int) newVblue);
                cbse MINUTE_OF_DAY: return plusMinutes(newVblue - (hour * 60 + minute));
                cbse HOUR_OF_AMPM: return plusHours(newVblue - (hour % 12));
                cbse CLOCK_HOUR_OF_AMPM: return plusHours((newVblue == 12 ? 0 : newVblue) - (hour % 12));
                cbse HOUR_OF_DAY: return withHour((int) newVblue);
                cbse CLOCK_HOUR_OF_DAY: return withHour((int) (newVblue == 24 ? 0 : newVblue));
                cbse AMPM_OF_DAY: return plusHours((newVblue - (hour / 12)) * 12);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.bdjustInto(this, newVblue);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code LocblTime} with the hour-of-dby bltered.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hour  the hour-of-dby to set in the result, from 0 to 23
     * @return b {@code LocblTime} bbsed on this time with the requested hour, not null
     * @throws DbteTimeException if the hour vblue is invblid
     */
    public LocblTime withHour(int hour) {
        if (this.hour == hour) {
            return this;
        }
        HOUR_OF_DAY.checkVblidVblue(hour);
        return crebte(hour, minute, second, nbno);
    }

    /**
     * Returns b copy of this {@code LocblTime} with the minute-of-hour bltered.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minute  the minute-of-hour to set in the result, from 0 to 59
     * @return b {@code LocblTime} bbsed on this time with the requested minute, not null
     * @throws DbteTimeException if the minute vblue is invblid
     */
    public LocblTime withMinute(int minute) {
        if (this.minute == minute) {
            return this;
        }
        MINUTE_OF_HOUR.checkVblidVblue(minute);
        return crebte(hour, minute, second, nbno);
    }

    /**
     * Returns b copy of this {@code LocblTime} with the second-of-minute bltered.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm second  the second-of-minute to set in the result, from 0 to 59
     * @return b {@code LocblTime} bbsed on this time with the requested second, not null
     * @throws DbteTimeException if the second vblue is invblid
     */
    public LocblTime withSecond(int second) {
        if (this.second == second) {
            return this;
        }
        SECOND_OF_MINUTE.checkVblidVblue(second);
        return crebte(hour, minute, second, nbno);
    }

    /**
     * Returns b copy of this {@code LocblTime} with the nbno-of-second bltered.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnoOfSecond  the nbno-of-second to set in the result, from 0 to 999,999,999
     * @return b {@code LocblTime} bbsed on this time with the requested nbnosecond, not null
     * @throws DbteTimeException if the nbnos vblue is invblid
     */
    public LocblTime withNbno(int nbnoOfSecond) {
        if (this.nbno == nbnoOfSecond) {
            return this;
        }
        NANO_OF_SECOND.checkVblidVblue(nbnoOfSecond);
        return crebte(hour, minute, second, nbnoOfSecond);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code LocblTime} with the time truncbted.
     * <p>
     * Truncbtion returns b copy of the originbl time with fields
     * smbller thbn the specified unit set to zero.
     * For exbmple, truncbting with the {@link ChronoUnit#MINUTES minutes} unit
     * will set the second-of-minute bnd nbno-of-second field to zero.
     * <p>
     * The unit must hbve b {@linkplbin TemporblUnit#getDurbtion() durbtion}
     * thbt divides into the length of b stbndbrd dby without rembinder.
     * This includes bll supplied time units on {@link ChronoUnit} bnd
     * {@link ChronoUnit#DAYS DAYS}. Other units throw bn exception.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm unit  the unit to truncbte to, not null
     * @return b {@code LocblTime} bbsed on this time with the time truncbted, not null
     * @throws DbteTimeException if unbble to truncbte
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    public LocblTime truncbtedTo(TemporblUnit unit) {
        if (unit == ChronoUnit.NANOS) {
            return this;
        }
        Durbtion unitDur = unit.getDurbtion();
        if (unitDur.getSeconds() > SECONDS_PER_DAY) {
            throw new UnsupportedTemporblTypeException("Unit is too lbrge to be used for truncbtion");
        }
        long dur = unitDur.toNbnos();
        if ((NANOS_PER_DAY % dur) != 0) {
            throw new UnsupportedTemporblTypeException("Unit must divide into b stbndbrd dby without rembinder");
        }
        long nod = toNbnoOfDby();
        return ofNbnoOfDby((nod / dur) * dur);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this time with the specified bmount bdded.
     * <p>
     * This returns b {@code LocblTime}, bbsed on this one, with the specified bmount bdded.
     * The bmount is typicblly {@link Durbtion} but mby be bny other type implementing
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
     * @return b {@code LocblTime} bbsed on this time with the bddition mbde, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblTime plus(TemporblAmount bmountToAdd) {
        return (LocblTime) bmountToAdd.bddTo(this);
    }

    /**
     * Returns b copy of this time with the specified bmount bdded.
     * <p>
     * This returns b {@code LocblTime}, bbsed on this one, with the bmount
     * in terms of the unit bdded. If it is not possible to bdd the bmount, becbuse the
     * unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoUnit} then the bddition is implemented here.
     * The supported fields behbve bs follows:
     * <ul>
     * <li>{@code NANOS} -
     *  Returns b {@code LocblTime} with the specified number of nbnoseconds bdded.
     *  This is equivblent to {@link #plusNbnos(long)}.
     * <li>{@code MICROS} -
     *  Returns b {@code LocblTime} with the specified number of microseconds bdded.
     *  This is equivblent to {@link #plusNbnos(long)} with the bmount
     *  multiplied by 1,000.
     * <li>{@code MILLIS} -
     *  Returns b {@code LocblTime} with the specified number of milliseconds bdded.
     *  This is equivblent to {@link #plusNbnos(long)} with the bmount
     *  multiplied by 1,000,000.
     * <li>{@code SECONDS} -
     *  Returns b {@code LocblTime} with the specified number of seconds bdded.
     *  This is equivblent to {@link #plusSeconds(long)}.
     * <li>{@code MINUTES} -
     *  Returns b {@code LocblTime} with the specified number of minutes bdded.
     *  This is equivblent to {@link #plusMinutes(long)}.
     * <li>{@code HOURS} -
     *  Returns b {@code LocblTime} with the specified number of hours bdded.
     *  This is equivblent to {@link #plusHours(long)}.
     * <li>{@code HALF_DAYS} -
     *  Returns b {@code LocblTime} with the specified number of hblf-dbys bdded.
     *  This is equivblent to {@link #plusHours(long)} with the bmount
     *  multiplied by 12.
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
     * @return b {@code LocblTime} bbsed on this time with the specified bmount bdded, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblTime plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            switch ((ChronoUnit) unit) {
                cbse NANOS: return plusNbnos(bmountToAdd);
                cbse MICROS: return plusNbnos((bmountToAdd % MICROS_PER_DAY) * 1000);
                cbse MILLIS: return plusNbnos((bmountToAdd % MILLIS_PER_DAY) * 1000_000);
                cbse SECONDS: return plusSeconds(bmountToAdd);
                cbse MINUTES: return plusMinutes(bmountToAdd);
                cbse HOURS: return plusHours(bmountToAdd);
                cbse HALF_DAYS: return plusHours((bmountToAdd % 2) * 12);
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.bddTo(this, bmountToAdd);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code LocblTime} with the specified number of hours bdded.
     * <p>
     * This bdds the specified number of hours to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hoursToAdd  the hours to bdd, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the hours bdded, not null
     */
    public LocblTime plusHours(long hoursToAdd) {
        if (hoursToAdd == 0) {
            return this;
        }
        int newHour = ((int) (hoursToAdd % HOURS_PER_DAY) + hour + HOURS_PER_DAY) % HOURS_PER_DAY;
        return crebte(newHour, minute, second, nbno);
    }

    /**
     * Returns b copy of this {@code LocblTime} with the specified number of minutes bdded.
     * <p>
     * This bdds the specified number of minutes to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minutesToAdd  the minutes to bdd, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the minutes bdded, not null
     */
    public LocblTime plusMinutes(long minutesToAdd) {
        if (minutesToAdd == 0) {
            return this;
        }
        int mofd = hour * MINUTES_PER_HOUR + minute;
        int newMofd = ((int) (minutesToAdd % MINUTES_PER_DAY) + mofd + MINUTES_PER_DAY) % MINUTES_PER_DAY;
        if (mofd == newMofd) {
            return this;
        }
        int newHour = newMofd / MINUTES_PER_HOUR;
        int newMinute = newMofd % MINUTES_PER_HOUR;
        return crebte(newHour, newMinute, second, nbno);
    }

    /**
     * Returns b copy of this {@code LocblTime} with the specified number of seconds bdded.
     * <p>
     * This bdds the specified number of seconds to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondstoAdd  the seconds to bdd, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the seconds bdded, not null
     */
    public LocblTime plusSeconds(long secondstoAdd) {
        if (secondstoAdd == 0) {
            return this;
        }
        int sofd = hour * SECONDS_PER_HOUR +
                    minute * SECONDS_PER_MINUTE + second;
        int newSofd = ((int) (secondstoAdd % SECONDS_PER_DAY) + sofd + SECONDS_PER_DAY) % SECONDS_PER_DAY;
        if (sofd == newSofd) {
            return this;
        }
        int newHour = newSofd / SECONDS_PER_HOUR;
        int newMinute = (newSofd / SECONDS_PER_MINUTE) % MINUTES_PER_HOUR;
        int newSecond = newSofd % SECONDS_PER_MINUTE;
        return crebte(newHour, newMinute, newSecond, nbno);
    }

    /**
     * Returns b copy of this {@code LocblTime} with the specified number of nbnoseconds bdded.
     * <p>
     * This bdds the specified number of nbnoseconds to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnosToAdd  the nbnos to bdd, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the nbnoseconds bdded, not null
     */
    public LocblTime plusNbnos(long nbnosToAdd) {
        if (nbnosToAdd == 0) {
            return this;
        }
        long nofd = toNbnoOfDby();
        long newNofd = ((nbnosToAdd % NANOS_PER_DAY) + nofd + NANOS_PER_DAY) % NANOS_PER_DAY;
        if (nofd == newNofd) {
            return this;
        }
        int newHour = (int) (newNofd / NANOS_PER_HOUR);
        int newMinute = (int) ((newNofd / NANOS_PER_MINUTE) % MINUTES_PER_HOUR);
        int newSecond = (int) ((newNofd / NANOS_PER_SECOND) % SECONDS_PER_MINUTE);
        int newNbno = (int) (newNofd % NANOS_PER_SECOND);
        return crebte(newHour, newMinute, newSecond, newNbno);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this time with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code LocblTime}, bbsed on this one, with the specified bmount subtrbcted.
     * The bmount is typicblly {@link Durbtion} but mby be bny other type implementing
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
     * @return b {@code LocblTime} bbsed on this time with the subtrbction mbde, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblTime minus(TemporblAmount bmountToSubtrbct) {
        return (LocblTime) bmountToSubtrbct.subtrbctFrom(this);
    }

    /**
     * Returns b copy of this time with the specified bmount subtrbcted.
     * <p>
     * This returns b {@code LocblTime}, bbsed on this one, with the bmount
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
     * @return b {@code LocblTime} bbsed on this time with the specified bmount subtrbcted, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public LocblTime minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code LocblTime} with the specified number of hours subtrbcted.
     * <p>
     * This subtrbcts the specified number of hours from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hoursToSubtrbct  the hours to subtrbct, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the hours subtrbcted, not null
     */
    public LocblTime minusHours(long hoursToSubtrbct) {
        return plusHours(-(hoursToSubtrbct % HOURS_PER_DAY));
    }

    /**
     * Returns b copy of this {@code LocblTime} with the specified number of minutes subtrbcted.
     * <p>
     * This subtrbcts the specified number of minutes from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minutesToSubtrbct  the minutes to subtrbct, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the minutes subtrbcted, not null
     */
    public LocblTime minusMinutes(long minutesToSubtrbct) {
        return plusMinutes(-(minutesToSubtrbct % MINUTES_PER_DAY));
    }

    /**
     * Returns b copy of this {@code LocblTime} with the specified number of seconds subtrbcted.
     * <p>
     * This subtrbcts the specified number of seconds from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm secondsToSubtrbct  the seconds to subtrbct, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the seconds subtrbcted, not null
     */
    public LocblTime minusSeconds(long secondsToSubtrbct) {
        return plusSeconds(-(secondsToSubtrbct % SECONDS_PER_DAY));
    }

    /**
     * Returns b copy of this {@code LocblTime} with the specified number of nbnoseconds subtrbcted.
     * <p>
     * This subtrbcts the specified number of nbnoseconds from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnosToSubtrbct  the nbnos to subtrbct, mby be negbtive
     * @return b {@code LocblTime} bbsed on this time with the nbnoseconds subtrbcted, not null
     */
    public LocblTime minusNbnos(long nbnosToSubtrbct) {
        return plusNbnos(-(nbnosToSubtrbct % NANOS_PER_DAY));
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this time using the specified query.
     * <p>
     * This queries this time using the specified query strbtegy object.
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
        if (query == TemporblQueries.chronology() || query == TemporblQueries.zoneId() ||
                query == TemporblQueries.zone() || query == TemporblQueries.offset()) {
            return null;
        } else if (query == TemporblQueries.locblTime()) {
            return (R) this;
        } else if (query == TemporblQueries.locblDbte()) {
            return null;
        } else if (query == TemporblQueries.precision()) {
            return (R) NANOS;
        }
        // inline TemporblAccessor.super.query(query) bs bn optimizbtion
        // non-JDK clbsses bre not permitted to mbke this optimizbtion
        return query.queryFrom(this);
    }

    /**
     * Adjusts the specified temporbl object to hbve the sbme time bs this object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the time chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#NANO_OF_DAY} bs the field.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisLocblTime.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisLocblTime);
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
        return temporbl.with(NANO_OF_DAY, toNbnoOfDby());
    }

    /**
     * Cblculbtes the bmount of time until bnother time in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two {@code LocblTime}
     * objects in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified time.
     * The result will be negbtive if the end is before the stbrt.
     * The {@code Temporbl} pbssed to this method is converted to b
     * {@code LocblTime} using {@link #from(TemporblAccessor)}.
     * For exbmple, the bmount in hours between two times cbn be cblculbted
     * using {@code stbrtTime.until(endTime, HOURS)}.
     * <p>
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two times.
     * For exbmple, the bmount in hours between 11:30 bnd 13:29 will only
     * be one hour bs it is one minute short of two hours.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method.
     * The second is to use {@link TemporblUnit#between(Temporbl, Temporbl)}:
     * <pre>
     *   // these two lines bre equivblent
     *   bmount = stbrt.until(end, MINUTES);
     *   bmount = MINUTES.between(stbrt, end);
     * </pre>
     * The choice should be mbde bbsed on which mbkes the code more rebdbble.
     * <p>
     * The cblculbtion is implemented in this method for {@link ChronoUnit}.
     * The units {@code NANOS}, {@code MICROS}, {@code MILLIS}, {@code SECONDS},
     * {@code MINUTES}, {@code HOURS} bnd {@code HALF_DAYS} bre supported.
     * Other {@code ChronoUnit} vblues will throw bn exception.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.between(Temporbl, Temporbl)}
     * pbssing {@code this} bs the first brgument bnd the converted input temporbl
     * bs the second brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm endExclusive  the end time, exclusive, which is converted to b {@code LocblTime}, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this time bnd the end time
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to b {@code LocblTime}
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        LocblTime end = LocblTime.from(endExclusive);
        if (unit instbnceof ChronoUnit) {
            long nbnosUntil = end.toNbnoOfDby() - toNbnoOfDby();  // no overflow
            switch ((ChronoUnit) unit) {
                cbse NANOS: return nbnosUntil;
                cbse MICROS: return nbnosUntil / 1000;
                cbse MILLIS: return nbnosUntil / 1000_000;
                cbse SECONDS: return nbnosUntil / NANOS_PER_SECOND;
                cbse MINUTES: return nbnosUntil / NANOS_PER_MINUTE;
                cbse HOURS: return nbnosUntil / NANOS_PER_HOUR;
                cbse HALF_DAYS: return nbnosUntil / (12 * NANOS_PER_HOUR);
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return unit.between(this, end);
    }

    /**
     * Formbts this time using the specified formbtter.
     * <p>
     * This time will be pbssed to the formbtter to produce b string.
     *
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the formbtted time string, not null
     * @throws DbteTimeException if bn error occurs during printing
     */
    public String formbt(DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this time with b dbte to crebte b {@code LocblDbteTime}.
     * <p>
     * This returns b {@code LocblDbteTime} formed from this time bt the specified dbte.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm dbte  the dbte to combine with, not null
     * @return the locbl dbte-time formed from this time bnd the specified dbte, not null
     */
    public LocblDbteTime btDbte(LocblDbte dbte) {
        return LocblDbteTime.of(dbte, this);
    }

    /**
     * Combines this time with bn offset to crebte bn {@code OffsetTime}.
     * <p>
     * This returns bn {@code OffsetTime} formed from this time bt the specified offset.
     * All possible combinbtions of time bnd offset bre vblid.
     *
     * @pbrbm offset  the offset to combine with, not null
     * @return the offset time formed from this time bnd the specified offset, not null
     */
    public OffsetTime btOffset(ZoneOffset offset) {
        return OffsetTime.of(this, offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Extrbcts the time bs seconds of dby,
     * from {@code 0} to {@code 24 * 60 * 60 - 1}.
     *
     * @return the second-of-dby equivblent to this time
     */
    public int toSecondOfDby() {
        int totbl = hour * SECONDS_PER_HOUR;
        totbl += minute * SECONDS_PER_MINUTE;
        totbl += second;
        return totbl;
    }

    /**
     * Extrbcts the time bs nbnos of dby,
     * from {@code 0} to {@code 24 * 60 * 60 * 1,000,000,000 - 1}.
     *
     * @return the nbno of dby equivblent to this time
     */
    public long toNbnoOfDby() {
        long totbl = hour * NANOS_PER_HOUR;
        totbl += minute * NANOS_PER_MINUTE;
        totbl += second * NANOS_PER_SECOND;
        totbl += nbno;
        return totbl;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this time to bnother time.
     * <p>
     * The compbrison is bbsed on the time-line position of the locbl times within b dby.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm other  the other time to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    public int compbreTo(LocblTime other) {
        int cmp = Integer.compbre(hour, other.hour);
        if (cmp == 0) {
            cmp = Integer.compbre(minute, other.minute);
            if (cmp == 0) {
                cmp = Integer.compbre(second, other.second);
                if (cmp == 0) {
                    cmp = Integer.compbre(nbno, other.nbno);
                }
            }
        }
        return cmp;
    }

    /**
     * Checks if this time is bfter the specified time.
     * <p>
     * The compbrison is bbsed on the time-line position of the time within b dby.
     *
     * @pbrbm other  the other time to compbre to, not null
     * @return true if this is bfter the specified time
     */
    public boolebn isAfter(LocblTime other) {
        return compbreTo(other) > 0;
    }

    /**
     * Checks if this time is before the specified time.
     * <p>
     * The compbrison is bbsed on the time-line position of the time within b dby.
     *
     * @pbrbm other  the other time to compbre to, not null
     * @return true if this point is before the specified time
     */
    public boolebn isBefore(LocblTime other) {
        return compbreTo(other) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this time is equbl to bnother time.
     * <p>
     * The compbrison is bbsed on the time-line position of the time within b dby.
     * <p>
     * Only objects of type {@code LocblTime} bre compbred, other types return fblse.
     * To compbre the dbte of two {@code TemporblAccessor} instbnces, use
     * {@link ChronoField#NANO_OF_DAY} bs b compbrbtor.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other time
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof LocblTime) {
            LocblTime other = (LocblTime) obj;
            return hour == other.hour && minute == other.minute &&
                    second == other.second && nbno == other.nbno;
        }
        return fblse;
    }

    /**
     * A hbsh code for this time.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        long nod = toNbnoOfDby();
        return (int) (nod ^ (nod >>> 32));
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this time bs b {@code String}, such bs {@code 10:15}.
     * <p>
     * The output will be one of the following ISO-8601 formbts:
     * <ul>
     * <li>{@code HH:mm}</li>
     * <li>{@code HH:mm:ss}</li>
     * <li>{@code HH:mm:ss.SSS}</li>
     * <li>{@code HH:mm:ss.SSSSSS}</li>
     * <li>{@code HH:mm:ss.SSSSSSSSS}</li>
     * </ul>
     * The formbt used will be the shortest thbt outputs the full vblue of
     * the time where the omitted pbrts bre implied to be zero.
     *
     * @return b string representbtion of this time, not null
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(18);
        int hourVblue = hour;
        int minuteVblue = minute;
        int secondVblue = second;
        int nbnoVblue = nbno;
        buf.bppend(hourVblue < 10 ? "0" : "").bppend(hourVblue)
            .bppend(minuteVblue < 10 ? ":0" : ":").bppend(minuteVblue);
        if (secondVblue > 0 || nbnoVblue > 0) {
            buf.bppend(secondVblue < 10 ? ":0" : ":").bppend(secondVblue);
            if (nbnoVblue > 0) {
                buf.bppend('.');
                if (nbnoVblue % 1000_000 == 0) {
                    buf.bppend(Integer.toString((nbnoVblue / 1000_000) + 1000).substring(1));
                } else if (nbnoVblue % 1000 == 0) {
                    buf.bppend(Integer.toString((nbnoVblue / 1000) + 1000_000).substring(1));
                } else {
                    buf.bppend(Integer.toString((nbnoVblue) + 1000_000_000).substring(1));
                }
            }
        }
        return buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * A twos-complement vblue indicbtes the rembining vblues bre not in the strebm
     * bnd should be set to zero.
     * <pre>
     *  out.writeByte(4);  // identifies b LocblTime
     *  if (nbno == 0) {
     *    if (second == 0) {
     *      if (minute == 0) {
     *        out.writeByte(~hour);
     *      } else {
     *        out.writeByte(hour);
     *        out.writeByte(~minute);
     *      }
     *    } else {
     *      out.writeByte(hour);
     *      out.writeByte(minute);
     *      out.writeByte(~second);
     *    }
     *  } else {
     *    out.writeByte(hour);
     *    out.writeByte(minute);
     *    out.writeByte(second);
     *    out.writeInt(nbno);
     *  }
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.LOCAL_TIME_TYPE, this);
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
        if (nbno == 0) {
            if (second == 0) {
                if (minute == 0) {
                    out.writeByte(~hour);
                } else {
                    out.writeByte(hour);
                    out.writeByte(~minute);
                }
            } else {
                out.writeByte(hour);
                out.writeByte(minute);
                out.writeByte(~second);
            }
        } else {
            out.writeByte(hour);
            out.writeByte(minute);
            out.writeByte(second);
            out.writeInt(nbno);
        }
    }

    stbtic LocblTime rebdExternbl(DbtbInput in) throws IOException {
        int hour = in.rebdByte();
        int minute = 0;
        int second = 0;
        int nbno = 0;
        if (hour < 0) {
            hour = ~hour;
        } else {
            minute = in.rebdByte();
            if (minute < 0) {
                minute = ~minute;
            } else {
                second = in.rebdByte();
                if (second < 0) {
                    second = ~second;
                } else {
                    nbno = in.rebdInt();
                }
            }
        }
        return LocblTime.of(hour, minute, second, nbno);
    }

}
