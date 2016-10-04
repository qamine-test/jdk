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

import stbtic jbvb.time.LocblTime.NANOS_PER_HOUR;
import stbtic jbvb.time.LocblTime.NANOS_PER_MINUTE;
import stbtic jbvb.time.LocblTime.NANOS_PER_SECOND;
import stbtic jbvb.time.LocblTime.SECONDS_PER_DAY;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.OFFSET_SECONDS;
import stbtic jbvb.time.temporbl.ChronoUnit.NANOS;

import jbvb.io.IOException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
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
import jbvb.time.zone.ZoneRules;
import jbvb.util.Objects;

/**
 * A time with bn offset from UTC/Greenwich in the ISO-8601 cblendbr system,
 * such bs {@code 10:15:30+01:00}.
 * <p>
 * {@code OffsetTime} is bn immutbble dbte-time object thbt represents b time, often
 * viewed bs hour-minute-second-offset.
 * This clbss stores bll time fields, to b precision of nbnoseconds,
 * bs well bs b zone offset.
 * For exbmple, the vblue "13:45.30.123456789+02:00" cbn be stored
 * in bn {@code OffsetTime}.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code OffsetTime} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss OffsetTime
        implements Temporbl, TemporblAdjuster, Compbrbble<OffsetTime>, Seriblizbble {

    /**
     * The minimum supported {@code OffsetTime}, '00:00:00+18:00'.
     * This is the time of midnight bt the stbrt of the dby in the mbximum offset
     * (lbrger offsets bre ebrlier on the time-line).
     * This combines {@link LocblTime#MIN} bnd {@link ZoneOffset#MAX}.
     * This could be used by bn bpplicbtion bs b "fbr pbst" dbte.
     */
    public stbtic finbl OffsetTime MIN = LocblTime.MIN.btOffset(ZoneOffset.MAX);
    /**
     * The mbximum supported {@code OffsetTime}, '23:59:59.999999999-18:00'.
     * This is the time just before midnight bt the end of the dby in the minimum offset
     * (lbrger negbtive offsets bre lbter on the time-line).
     * This combines {@link LocblTime#MAX} bnd {@link ZoneOffset#MIN}.
     * This could be used by bn bpplicbtion bs b "fbr future" dbte.
     */
    public stbtic finbl OffsetTime MAX = LocblTime.MAX.btOffset(ZoneOffset.MIN);

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 7264499704384272492L;

    /**
     * The locbl dbte-time.
     */
    privbte finbl LocblTime time;
    /**
     * The offset from UTC/Greenwich.
     */
    privbte finbl ZoneOffset offset;

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current time from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current time.
     * The offset will be cblculbted from the time-zone in the clock.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current time using the system clock bnd defbult time-zone, not null
     */
    public stbtic OffsetTime now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current time from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current time.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * The offset will be cblculbted from the specified time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current time using the system clock, not null
     */
    public stbtic OffsetTime now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current time from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current time.
     * The offset will be cblculbted from the time-zone in the clock.
     * <p>
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current time, not null
     */
    public stbtic OffsetTime now(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        finbl Instbnt now = clock.instbnt();  // cblled once
        return ofInstbnt(now, clock.getZone().getRules().getOffset(now));
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code OffsetTime} from b locbl time bnd bn offset.
     *
     * @pbrbm time  the locbl time, not null
     * @pbrbm offset  the zone offset, not null
     * @return the offset time, not null
     */
    public stbtic OffsetTime of(LocblTime time, ZoneOffset offset) {
        return new OffsetTime(time, offset);
    }

    /**
     * Obtbins bn instbnce of {@code OffsetTime} from bn hour, minute, second bnd nbnosecond.
     * <p>
     * This crebtes bn offset time with the four specified fields.
     * <p>
     * This method exists primbrily for writing test cbses.
     * Non test-code will typicblly use other methods to crebte bn offset time.
     * {@code LocblTime} hbs two bdditionbl convenience vbribnts of the
     * equivblent fbctory method tbking fewer brguments.
     * They bre not provided here to reduce the footprint of the API.
     *
     * @pbrbm hour  the hour-of-dby to represent, from 0 to 23
     * @pbrbm minute  the minute-of-hour to represent, from 0 to 59
     * @pbrbm second  the second-of-minute to represent, from 0 to 59
     * @pbrbm nbnoOfSecond  the nbno-of-second to represent, from 0 to 999,999,999
     * @pbrbm offset  the zone offset, not null
     * @return the offset time, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge
     */
    public stbtic OffsetTime of(int hour, int minute, int second, int nbnoOfSecond, ZoneOffset offset) {
        return new OffsetTime(LocblTime.of(hour, minute, second, nbnoOfSecond), offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code OffsetTime} from bn {@code Instbnt} bnd zone ID.
     * <p>
     * This crebtes bn offset time with the sbme instbnt bs thbt specified.
     * Finding the offset from UTC/Greenwich is simple bs there is only one vblid
     * offset for ebch instbnt.
     * <p>
     * The dbte component of the instbnt is dropped during the conversion.
     * This mebns thbt the conversion cbn never fbil due to the instbnt being
     * out of the vblid rbnge of dbtes.
     *
     * @pbrbm instbnt  the instbnt to crebte the time from, not null
     * @pbrbm zone  the time-zone, which mby be bn offset, not null
     * @return the offset time, not null
     */
    public stbtic OffsetTime ofInstbnt(Instbnt instbnt, ZoneId zone) {
        Objects.requireNonNull(instbnt, "instbnt");
        Objects.requireNonNull(zone, "zone");
        ZoneRules rules = zone.getRules();
        ZoneOffset offset = rules.getOffset(instbnt);
        long locblSecond = instbnt.getEpochSecond() + offset.getTotblSeconds();  // overflow cbught lbter
        int secsOfDby = (int) Mbth.floorMod(locblSecond, SECONDS_PER_DAY);
        LocblTime time = LocblTime.ofNbnoOfDby(secsOfDby * NANOS_PER_SECOND + instbnt.getNbno());
        return new OffsetTime(time, offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code OffsetTime} from b temporbl object.
     * <p>
     * This obtbins bn offset time bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code OffsetTime}.
     * <p>
     * The conversion extrbcts bnd combines the {@code ZoneOffset} bnd the
     * {@code LocblTime} from the temporbl object.
     * Implementbtions bre permitted to perform optimizbtions such bs bccessing
     * those fields thbt bre equivblent to the relevbnt objects.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code OffsetTime::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the offset time, not null
     * @throws DbteTimeException if unbble to convert to bn {@code OffsetTime}
     */
    public stbtic OffsetTime from(TemporblAccessor temporbl) {
        if (temporbl instbnceof OffsetTime) {
            return (OffsetTime) temporbl;
        }
        try {
            LocblTime time = LocblTime.from(temporbl);
            ZoneOffset offset = ZoneOffset.from(temporbl);
            return new OffsetTime(time, offset);
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin OffsetTime from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme(), ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code OffsetTime} from b text string such bs {@code 10:15:30+01:00}.
     * <p>
     * The string must represent b vblid time bnd is pbrsed using
     * {@link jbvb.time.formbt.DbteTimeFormbtter#ISO_OFFSET_TIME}.
     *
     * @pbrbm text  the text to pbrse such bs "10:15:30+01:00", not null
     * @return the pbrsed locbl time, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic OffsetTime pbrse(ChbrSequence text) {
        return pbrse(text, DbteTimeFormbtter.ISO_OFFSET_TIME);
    }

    /**
     * Obtbins bn instbnce of {@code OffsetTime} from b text string using b specific formbtter.
     * <p>
     * The text is pbrsed using the formbtter, returning b time.
     *
     * @pbrbm text  the text to pbrse, not null
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the pbrsed offset time, not null
     * @throws DbteTimePbrseException if the text cbnnot be pbrsed
     */
    public stbtic OffsetTime pbrse(ChbrSequence text, DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.pbrse(text, OffsetTime::from);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @pbrbm time  the locbl time, not null
     * @pbrbm offset  the zone offset, not null
     */
    privbte OffsetTime(LocblTime time, ZoneOffset offset) {
        this.time = Objects.requireNonNull(time, "time");
        this.offset = Objects.requireNonNull(offset, "offset");
    }

    /**
     * Returns b new time bbsed on this one, returning {@code this} where possible.
     *
     * @pbrbm time  the time to crebte with, not null
     * @pbrbm offset  the zone offset to crebte with, not null
     */
    privbte OffsetTime with(LocblTime time, ZoneOffset offset) {
        if (this.time == time && this.offset.equbls(offset)) {
            return this;
        }
        return new OffsetTime(time, offset);
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
     * <li>{@code OFFSET_SECONDS}
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
            return field.isTimeBbsed() || field == OFFSET_SECONDS;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to, or subtrbcted from, this offset-time.
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
    @Override
    public VblueRbnge rbnge(TemporblField field) {
        if (field instbnceof ChronoField) {
            if (field == OFFSET_SECONDS) {
                return field.rbnge();
            }
            return time.rbnge(field);
        }
        return field.rbngeRefinedBy(this);
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
    @Override  // override for Jbvbdoc
    public int get(TemporblField field) {
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
            if (field == OFFSET_SECONDS) {
                return offset.getTotblSeconds();
            }
            return time.getLong(field);
        }
        return field.getFrom(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the zone offset, such bs '+01:00'.
     * <p>
     * This is the offset of the locbl time from UTC/Greenwich.
     *
     * @return the zone offset, not null
     */
    public ZoneOffset getOffset() {
        return offset;
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified offset ensuring
     * thbt the result hbs the sbme locbl time.
     * <p>
     * This method returns bn object with the sbme {@code LocblTime} bnd the specified {@code ZoneOffset}.
     * No cblculbtion is needed or performed.
     * For exbmple, if this time represents {@code 10:30+02:00} bnd the offset specified is
     * {@code +03:00}, then this method will return {@code 10:30+03:00}.
     * <p>
     * To tbke into bccount the difference between the offsets, bnd bdjust the time fields,
     * use {@link #withOffsetSbmeInstbnt}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm offset  the zone offset to chbnge to, not null
     * @return bn {@code OffsetTime} bbsed on this time with the requested offset, not null
     */
    public OffsetTime withOffsetSbmeLocbl(ZoneOffset offset) {
        return offset != null && offset.equbls(this.offset) ? this : new OffsetTime(time, offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified offset ensuring
     * thbt the result is bt the sbme instbnt on bn implied dby.
     * <p>
     * This method returns bn object with the specified {@code ZoneOffset} bnd b {@code LocblTime}
     * bdjusted by the difference between the two offsets.
     * This will result in the old bnd new objects representing the sbme instbnt on bn implied dby.
     * This is useful for finding the locbl time in b different offset.
     * For exbmple, if this time represents {@code 10:30+02:00} bnd the offset specified is
     * {@code +03:00}, then this method will return {@code 11:30+03:00}.
     * <p>
     * To chbnge the offset without bdjusting the locbl time use {@link #withOffsetSbmeLocbl}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm offset  the zone offset to chbnge to, not null
     * @return bn {@code OffsetTime} bbsed on this time with the requested offset, not null
     */
    public OffsetTime withOffsetSbmeInstbnt(ZoneOffset offset) {
        if (offset.equbls(this.offset)) {
            return this;
        }
        int difference = offset.getTotblSeconds() - this.offset.getTotblSeconds();
        LocblTime bdjusted = time.plusSeconds(difference);
        return new OffsetTime(bdjusted, offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the {@code LocblTime} pbrt of this dbte-time.
     * <p>
     * This returns b {@code LocblTime} with the sbme hour, minute, second bnd
     * nbnosecond bs this dbte-time.
     *
     * @return the time pbrt of this dbte-time, not null
     */
    public LocblTime toLocblTime() {
        return time;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the hour-of-dby field.
     *
     * @return the hour-of-dby, from 0 to 23
     */
    public int getHour() {
        return time.getHour();
    }

    /**
     * Gets the minute-of-hour field.
     *
     * @return the minute-of-hour, from 0 to 59
     */
    public int getMinute() {
        return time.getMinute();
    }

    /**
     * Gets the second-of-minute field.
     *
     * @return the second-of-minute, from 0 to 59
     */
    public int getSecond() {
        return time.getSecond();
    }

    /**
     * Gets the nbno-of-second field.
     *
     * @return the nbno-of-second, from 0 to 999,999,999
     */
    public int getNbno() {
        return time.getNbno();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns bn bdjusted copy of this time.
     * <p>
     * This returns bn {@code OffsetTime}, bbsed on this one, with the time bdjusted.
     * The bdjustment tbkes plbce using the specified bdjuster strbtegy object.
     * Rebd the documentbtion of the bdjuster to understbnd whbt bdjustment will be mbde.
     * <p>
     * A simple bdjuster might simply set the one of the fields, such bs the hour field.
     * A more complex bdjuster might set the time to the lbst hour of the dby.
     * <p>
     * The clbsses {@link LocblTime} bnd {@link ZoneOffset} implement {@code TemporblAdjuster},
     * thus this method cbn be used to chbnge the time or offset:
     * <pre>
     *  result = offsetTime.with(time);
     *  result = offsetTime.with(offset);
     * </pre>
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblAdjuster#bdjustInto(Temporbl)} method on the
     * specified bdjuster pbssing {@code this} bs the brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bdjuster the bdjuster to use, not null
     * @return bn {@code OffsetTime} bbsed on {@code this} with the bdjustment mbde, not null
     * @throws DbteTimeException if the bdjustment cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public OffsetTime with(TemporblAdjuster bdjuster) {
        // optimizbtions
        if (bdjuster instbnceof LocblTime) {
            return with((LocblTime) bdjuster, offset);
        } else if (bdjuster instbnceof ZoneOffset) {
            return with(time, (ZoneOffset) bdjuster);
        } else if (bdjuster instbnceof OffsetTime) {
            return (OffsetTime) bdjuster;
        }
        return (OffsetTime) bdjuster.bdjustInto(this);
    }

    /**
     * Returns b copy of this time with the specified field set to b new vblue.
     * <p>
     * This returns bn {@code OffsetTime}, bbsed on this one, with the vblue
     * for the specified field chbnged.
     * This cbn be used to chbnge bny supported field, such bs the hour, minute or second.
     * If it is not possible to set the vblue, becbuse the field is not supported or for
     * some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the bdjustment is implemented here.
     * <p>
     * The {@code OFFSET_SECONDS} field will return b time with the specified offset.
     * The locbl time is unbltered. If the new offset vblue is outside the vblid rbnge
     * then b {@code DbteTimeException} will be thrown.
     * <p>
     * The other {@link #isSupported(TemporblField) supported fields} will behbve bs per
     * the mbtching method on {@link LocblTime#with(TemporblField, long)} LocblTime}.
     * In this cbse, the offset is not pbrt of the cblculbtion bnd will be unchbnged.
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
     * @return bn {@code OffsetTime} bbsed on {@code this} with the specified field set, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public OffsetTime with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            if (field == OFFSET_SECONDS) {
                ChronoField f = (ChronoField) field;
                return with(time, ZoneOffset.ofTotblSeconds(f.checkVblidIntVblue(newVblue)));
            }
            return with(time.with(field, newVblue), offset);
        }
        return field.bdjustInto(this, newVblue);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code OffsetTime} with the hour-of-dby bltered.
     * <p>
     * The offset does not bffect the cblculbtion bnd will be the sbme in the result.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hour  the hour-of-dby to set in the result, from 0 to 23
     * @return bn {@code OffsetTime} bbsed on this time with the requested hour, not null
     * @throws DbteTimeException if the hour vblue is invblid
     */
    public OffsetTime withHour(int hour) {
        return with(time.withHour(hour), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the minute-of-hour bltered.
     * <p>
     * The offset does not bffect the cblculbtion bnd will be the sbme in the result.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minute  the minute-of-hour to set in the result, from 0 to 59
     * @return bn {@code OffsetTime} bbsed on this time with the requested minute, not null
     * @throws DbteTimeException if the minute vblue is invblid
     */
    public OffsetTime withMinute(int minute) {
        return with(time.withMinute(minute), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the second-of-minute bltered.
     * <p>
     * The offset does not bffect the cblculbtion bnd will be the sbme in the result.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm second  the second-of-minute to set in the result, from 0 to 59
     * @return bn {@code OffsetTime} bbsed on this time with the requested second, not null
     * @throws DbteTimeException if the second vblue is invblid
     */
    public OffsetTime withSecond(int second) {
        return with(time.withSecond(second), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the nbno-of-second bltered.
     * <p>
     * The offset does not bffect the cblculbtion bnd will be the sbme in the result.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnoOfSecond  the nbno-of-second to set in the result, from 0 to 999,999,999
     * @return bn {@code OffsetTime} bbsed on this time with the requested nbnosecond, not null
     * @throws DbteTimeException if the nbnos vblue is invblid
     */
    public OffsetTime withNbno(int nbnoOfSecond) {
        return with(time.withNbno(nbnoOfSecond), offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code OffsetTime} with the time truncbted.
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
     * The offset does not bffect the cblculbtion bnd will be the sbme in the result.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm unit  the unit to truncbte to, not null
     * @return bn {@code OffsetTime} bbsed on this time with the time truncbted, not null
     * @throws DbteTimeException if unbble to truncbte
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    public OffsetTime truncbtedTo(TemporblUnit unit) {
        return with(time.truncbtedTo(unit), offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this time with the specified bmount bdded.
     * <p>
     * This returns bn {@code OffsetTime}, bbsed on this one, with the specified bmount bdded.
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
     * @return bn {@code OffsetTime} bbsed on this time with the bddition mbde, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public OffsetTime plus(TemporblAmount bmountToAdd) {
        return (OffsetTime) bmountToAdd.bddTo(this);
    }

    /**
     * Returns b copy of this time with the specified bmount bdded.
     * <p>
     * This returns bn {@code OffsetTime}, bbsed on this one, with the bmount
     * in terms of the unit bdded. If it is not possible to bdd the bmount, becbuse the
     * unit is not supported or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoUnit} then the bddition is implemented by
     * {@link LocblTime#plus(long, TemporblUnit)}.
     * The offset is not pbrt of the cblculbtion bnd will be unchbnged in the result.
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
     * @return bn {@code OffsetTime} bbsed on this time with the specified bmount bdded, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public OffsetTime plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            return with(time.plus(bmountToAdd, unit), offset);
        }
        return unit.bddTo(this, bmountToAdd);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of hours bdded.
     * <p>
     * This bdds the specified number of hours to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hours  the hours to bdd, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the hours bdded, not null
     */
    public OffsetTime plusHours(long hours) {
        return with(time.plusHours(hours), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of minutes bdded.
     * <p>
     * This bdds the specified number of minutes to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minutes  the minutes to bdd, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the minutes bdded, not null
     */
    public OffsetTime plusMinutes(long minutes) {
        return with(time.plusMinutes(minutes), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of seconds bdded.
     * <p>
     * This bdds the specified number of seconds to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm seconds  the seconds to bdd, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the seconds bdded, not null
     */
    public OffsetTime plusSeconds(long seconds) {
        return with(time.plusSeconds(seconds), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of nbnoseconds bdded.
     * <p>
     * This bdds the specified number of nbnoseconds to this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnos  the nbnos to bdd, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the nbnoseconds bdded, not null
     */
    public OffsetTime plusNbnos(long nbnos) {
        return with(time.plusNbnos(nbnos), offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this time with the specified bmount subtrbcted.
     * <p>
     * This returns bn {@code OffsetTime}, bbsed on this one, with the specified bmount subtrbcted.
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
     * @return bn {@code OffsetTime} bbsed on this time with the subtrbction mbde, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public OffsetTime minus(TemporblAmount bmountToSubtrbct) {
        return (OffsetTime) bmountToSubtrbct.subtrbctFrom(this);
    }

    /**
     * Returns b copy of this time with the specified bmount subtrbcted.
     * <p>
     * This returns bn {@code OffsetTime}, bbsed on this one, with the bmount
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
     * @return bn {@code OffsetTime} bbsed on this time with the specified bmount subtrbcted, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public OffsetTime minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of hours subtrbcted.
     * <p>
     * This subtrbcts the specified number of hours from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm hours  the hours to subtrbct, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the hours subtrbcted, not null
     */
    public OffsetTime minusHours(long hours) {
        return with(time.minusHours(hours), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of minutes subtrbcted.
     * <p>
     * This subtrbcts the specified number of minutes from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm minutes  the minutes to subtrbct, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the minutes subtrbcted, not null
     */
    public OffsetTime minusMinutes(long minutes) {
        return with(time.minusMinutes(minutes), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of seconds subtrbcted.
     * <p>
     * This subtrbcts the specified number of seconds from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm seconds  the seconds to subtrbct, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the seconds subtrbcted, not null
     */
    public OffsetTime minusSeconds(long seconds) {
        return with(time.minusSeconds(seconds), offset);
    }

    /**
     * Returns b copy of this {@code OffsetTime} with the specified number of nbnoseconds subtrbcted.
     * <p>
     * This subtrbcts the specified number of nbnoseconds from this time, returning b new time.
     * The cblculbtion wrbps bround midnight.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm nbnos  the nbnos to subtrbct, mby be negbtive
     * @return bn {@code OffsetTime} bbsed on this time with the nbnoseconds subtrbcted, not null
     */
    public OffsetTime minusNbnos(long nbnos) {
        return with(time.minusNbnos(nbnos), offset);
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
        if (query == TemporblQueries.offset() || query == TemporblQueries.zone()) {
            return (R) offset;
        } else if (query == TemporblQueries.zoneId() | query == TemporblQueries.chronology() || query == TemporblQueries.locblDbte()) {
            return null;
        } else if (query == TemporblQueries.locblTime()) {
            return (R) time;
        } else if (query == TemporblQueries.precision()) {
            return (R) NANOS;
        }
        // inline TemporblAccessor.super.query(query) bs bn optimizbtion
        // non-JDK clbsses bre not permitted to mbke this optimizbtion
        return query.queryFrom(this);
    }

    /**
     * Adjusts the specified temporbl object to hbve the sbme offset bnd time
     * bs this object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the offset bnd time chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * twice, pbssing {@link ChronoField#NANO_OF_DAY} bnd
     * {@link ChronoField#OFFSET_SECONDS} bs the fields.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisOffsetTime.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisOffsetTime);
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
        return temporbl
                .with(NANO_OF_DAY, time.toNbnoOfDby())
                .with(OFFSET_SECONDS, offset.getTotblSeconds());
    }

    /**
     * Cblculbtes the bmount of time until bnother time in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two {@code OffsetTime}
     * objects in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified time.
     * The result will be negbtive if the end is before the stbrt.
     * For exbmple, the bmount in hours between two times cbn be cblculbted
     * using {@code stbrtTime.until(endTime, HOURS)}.
     * <p>
     * The {@code Temporbl} pbssed to this method is converted to b
     * {@code OffsetTime} using {@link #from(TemporblAccessor)}.
     * If the offset differs between the two times, then the specified
     * end time is normblized to hbve the sbme offset bs this time.
     * <p>
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two times.
     * For exbmple, the bmount in hours between 11:30Z bnd 13:29Z will only
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
     * @pbrbm endExclusive  the end time, exclusive, which is converted to bn {@code OffsetTime}, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this time bnd the end time
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to bn {@code OffsetTime}
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        OffsetTime end = OffsetTime.from(endExclusive);
        if (unit instbnceof ChronoUnit) {
            long nbnosUntil = end.toEpochNbno() - toEpochNbno();  // no overflow
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
     * Combines this time with b dbte to crebte bn {@code OffsetDbteTime}.
     * <p>
     * This returns bn {@code OffsetDbteTime} formed from this time bnd the specified dbte.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm dbte  the dbte to combine with, not null
     * @return the offset dbte-time formed from this time bnd the specified dbte, not null
     */
    public OffsetDbteTime btDbte(LocblDbte dbte) {
        return OffsetDbteTime.of(dbte, time, offset);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this time to epoch nbnos bbsed on 1970-01-01Z.
     *
     * @return the epoch nbnos vblue
     */
    privbte long toEpochNbno() {
        long nod = time.toNbnoOfDby();
        long offsetNbnos = offset.getTotblSeconds() * NANOS_PER_SECOND;
        return nod - offsetNbnos;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this {@code OffsetTime} to bnother time.
     * <p>
     * The compbrison is bbsed first on the UTC equivblent instbnt, then on the locbl time.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     * <p>
     * For exbmple, the following is the compbrbtor order:
     * <ol>
     * <li>{@code 10:30+01:00}</li>
     * <li>{@code 11:00+01:00}</li>
     * <li>{@code 12:00+02:00}</li>
     * <li>{@code 11:30+01:00}</li>
     * <li>{@code 12:00+01:00}</li>
     * <li>{@code 12:30+01:00}</li>
     * </ol>
     * Vblues #2 bnd #3 represent the sbme instbnt on the time-line.
     * When two vblues represent the sbme instbnt, the locbl time is compbred
     * to distinguish them. This step is needed to mbke the ordering
     * consistent with {@code equbls()}.
     * <p>
     * To compbre the underlying locbl time of two {@code TemporblAccessor} instbnces,
     * use {@link ChronoField#NANO_OF_DAY} bs b compbrbtor.
     *
     * @pbrbm other  the other time to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    public int compbreTo(OffsetTime other) {
        if (offset.equbls(other.offset)) {
            return time.compbreTo(other.time);
        }
        int compbre = Long.compbre(toEpochNbno(), other.toEpochNbno());
        if (compbre == 0) {
            compbre = time.compbreTo(other.time);
        }
        return compbre;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the instbnt of this {@code OffsetTime} is bfter thbt of the
     * specified time bpplying both times to b common dbte.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the instbnt of the time. This is equivblent to converting both
     * times to bn instbnt using the sbme dbte bnd compbring the instbnts.
     *
     * @pbrbm other  the other time to compbre to, not null
     * @return true if this is bfter the instbnt of the specified time
     */
    public boolebn isAfter(OffsetTime other) {
        return toEpochNbno() > other.toEpochNbno();
    }

    /**
     * Checks if the instbnt of this {@code OffsetTime} is before thbt of the
     * specified time bpplying both times to b common dbte.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the instbnt of the time. This is equivblent to converting both
     * times to bn instbnt using the sbme dbte bnd compbring the instbnts.
     *
     * @pbrbm other  the other time to compbre to, not null
     * @return true if this is before the instbnt of the specified time
     */
    public boolebn isBefore(OffsetTime other) {
        return toEpochNbno() < other.toEpochNbno();
    }

    /**
     * Checks if the instbnt of this {@code OffsetTime} is equbl to thbt of the
     * specified time bpplying both times to b common dbte.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} bnd {@link #equbls}
     * in thbt it only compbres the instbnt of the time. This is equivblent to converting both
     * times to bn instbnt using the sbme dbte bnd compbring the instbnts.
     *
     * @pbrbm other  the other time to compbre to, not null
     * @return true if this is equbl to the instbnt of the specified time
     */
    public boolebn isEqubl(OffsetTime other) {
        return toEpochNbno() == other.toEpochNbno();
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this time is equbl to bnother time.
     * <p>
     * The compbrison is bbsed on the locbl-time bnd the offset.
     * To compbre for the sbme instbnt on the time-line, use {@link #isEqubl(OffsetTime)}.
     * <p>
     * Only objects of type {@code OffsetTime} bre compbred, other types return fblse.
     * To compbre the underlying locbl time of two {@code TemporblAccessor} instbnces,
     * use {@link ChronoField#NANO_OF_DAY} bs b compbrbtor.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other time
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof OffsetTime) {
            OffsetTime other = (OffsetTime) obj;
            return time.equbls(other.time) && offset.equbls(other.offset);
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
        return time.hbshCode() ^ offset.hbshCode();
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this time bs b {@code String}, such bs {@code 10:15:30+01:00}.
     * <p>
     * The output will be one of the following ISO-8601 formbts:
     * <ul>
     * <li>{@code HH:mmXXXXX}</li>
     * <li>{@code HH:mm:ssXXXXX}</li>
     * <li>{@code HH:mm:ss.SSSXXXXX}</li>
     * <li>{@code HH:mm:ss.SSSSSSXXXXX}</li>
     * <li>{@code HH:mm:ss.SSSSSSSSSXXXXX}</li>
     * </ul>
     * The formbt used will be the shortest thbt outputs the full vblue of
     * the time where the omitted pbrts bre implied to be zero.
     *
     * @return b string representbtion of this time, not null
     */
    @Override
    public String toString() {
        return time.toString() + offset.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(9);  // identifies bn OffsetTime
     *  // the <b href="../../seriblized-form.html#jbvb.time.LocblTime">time</b> excluding the one byte hebder
     *  // the <b href="../../seriblized-form.html#jbvb.time.ZoneOffset">offset</b> excluding the one byte hebder
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.OFFSET_TIME_TYPE, this);
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
        time.writeExternbl(out);
        offset.writeExternbl(out);
    }

    stbtic OffsetTime rebdExternbl(ObjectInput in) throws IOException, ClbssNotFoundException {
        LocblTime time = LocblTime.rebdExternbl(in);
        ZoneOffset offset = ZoneOffset.rebdExternbl(in);
        return OffsetTime.of(time, offset);
    }

}
