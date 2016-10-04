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

import stbtic jbvb.time.LocblTime.MINUTES_PER_HOUR;
import stbtic jbvb.time.LocblTime.SECONDS_PER_HOUR;
import stbtic jbvb.time.LocblTime.SECONDS_PER_MINUTE;
import stbtic jbvb.time.temporbl.ChronoField.OFFSET_SECONDS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.time.zone.ZoneRules;
import jbvb.util.Objects;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;

/**
 * A time-zone offset from Greenwich/UTC, such bs {@code +02:00}.
 * <p>
 * A time-zone offset is the bmount of time thbt b time-zone differs from Greenwich/UTC.
 * This is usublly b fixed number of hours bnd minutes.
 * <p>
 * Different pbrts of the world hbve different time-zone offsets.
 * The rules for how offsets vbry by plbce bnd time of yebr bre cbptured in the
 * {@link ZoneId} clbss.
 * <p>
 * For exbmple, Pbris is one hour bhebd of Greenwich/UTC in winter bnd two hours
 * bhebd in summer. The {@code ZoneId} instbnce for Pbris will reference two
 * {@code ZoneOffset} instbnces - b {@code +01:00} instbnce for winter,
 * bnd b {@code +02:00} instbnce for summer.
 * <p>
 * In 2008, time-zone offsets bround the world extended from -12:00 to +14:00.
 * To prevent bny problems with thbt rbnge being extended, yet still provide
 * vblidbtion, the rbnge of offsets is restricted to -18:00 to 18:00 inclusive.
 * <p>
 * This clbss is designed for use with the ISO cblendbr system.
 * The fields of hours, minutes bnd seconds mbke bssumptions thbt bre vblid for the
 * stbndbrd ISO definitions of those fields. This clbss mby be used with other
 * cblendbr systems providing the definition of the time fields mbtches those
 * of the ISO cblendbr system.
 * <p>
 * Instbnces of {@code ZoneOffset} must be compbred using {@link #equbls}.
 * Implementbtions mby choose to cbche certbin common offsets, however
 * bpplicbtions must not rely on such cbching.
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code ZoneOffset} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss ZoneOffset
        extends ZoneId
        implements TemporblAccessor, TemporblAdjuster, Compbrbble<ZoneOffset>, Seriblizbble {

    /** Cbche of time-zone offset by offset in seconds. */
    privbte stbtic finbl ConcurrentMbp<Integer, ZoneOffset> SECONDS_CACHE = new ConcurrentHbshMbp<>(16, 0.75f, 4);
    /** Cbche of time-zone offset by ID. */
    privbte stbtic finbl ConcurrentMbp<String, ZoneOffset> ID_CACHE = new ConcurrentHbshMbp<>(16, 0.75f, 4);

    /**
     * The bbs mbximum seconds.
     */
    privbte stbtic finbl int MAX_SECONDS = 18 * SECONDS_PER_HOUR;
    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 2357656521762053153L;

    /**
     * The time-zone offset for UTC, with bn ID of 'Z'.
     */
    public stbtic finbl ZoneOffset UTC = ZoneOffset.ofTotblSeconds(0);
    /**
     * Constbnt for the mbximum supported offset.
     */
    public stbtic finbl ZoneOffset MIN = ZoneOffset.ofTotblSeconds(-MAX_SECONDS);
    /**
     * Constbnt for the mbximum supported offset.
     */
    public stbtic finbl ZoneOffset MAX = ZoneOffset.ofTotblSeconds(MAX_SECONDS);

    /**
     * The totbl offset in seconds.
     */
    privbte finbl int totblSeconds;
    /**
     * The string form of the time-zone offset.
     */
    privbte finbl trbnsient String id;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ZoneOffset} using the ID.
     * <p>
     * This method pbrses the string ID of b {@code ZoneOffset} to
     * return bn instbnce. The pbrsing bccepts bll the formbts generbted by
     * {@link #getId()}, plus some bdditionbl formbts:
     * <ul>
     * <li>{@code Z} - for UTC
     * <li>{@code +h}
     * <li>{@code +hh}
     * <li>{@code +hh:mm}
     * <li>{@code -hh:mm}
     * <li>{@code +hhmm}
     * <li>{@code -hhmm}
     * <li>{@code +hh:mm:ss}
     * <li>{@code -hh:mm:ss}
     * <li>{@code +hhmmss}
     * <li>{@code -hhmmss}
     * </ul>
     * Note thbt &plusmn; mebns either the plus or minus symbol.
     * <p>
     * The ID of the returned offset will be normblized to one of the formbts
     * described by {@link #getId()}.
     * <p>
     * The mbximum supported rbnge is from +18:00 to -18:00 inclusive.
     *
     * @pbrbm offsetId  the offset ID, not null
     * @return the zone-offset, not null
     * @throws DbteTimeException if the offset ID is invblid
     */
    @SuppressWbrnings("fbllthrough")
    public stbtic ZoneOffset of(String offsetId) {
        Objects.requireNonNull(offsetId, "offsetId");
        // "Z" is blwbys in the cbche
        ZoneOffset offset = ID_CACHE.get(offsetId);
        if (offset != null) {
            return offset;
        }

        // pbrse - +h, +hh, +hhmm, +hh:mm, +hhmmss, +hh:mm:ss
        finbl int hours, minutes, seconds;
        switch (offsetId.length()) {
            cbse 2:
                offsetId = offsetId.chbrAt(0) + "0" + offsetId.chbrAt(1);  // fbllthru
            cbse 3:
                hours = pbrseNumber(offsetId, 1, fblse);
                minutes = 0;
                seconds = 0;
                brebk;
            cbse 5:
                hours = pbrseNumber(offsetId, 1, fblse);
                minutes = pbrseNumber(offsetId, 3, fblse);
                seconds = 0;
                brebk;
            cbse 6:
                hours = pbrseNumber(offsetId, 1, fblse);
                minutes = pbrseNumber(offsetId, 4, true);
                seconds = 0;
                brebk;
            cbse 7:
                hours = pbrseNumber(offsetId, 1, fblse);
                minutes = pbrseNumber(offsetId, 3, fblse);
                seconds = pbrseNumber(offsetId, 5, fblse);
                brebk;
            cbse 9:
                hours = pbrseNumber(offsetId, 1, fblse);
                minutes = pbrseNumber(offsetId, 4, true);
                seconds = pbrseNumber(offsetId, 7, true);
                brebk;
            defbult:
                throw new DbteTimeException("Invblid ID for ZoneOffset, invblid formbt: " + offsetId);
        }
        chbr first = offsetId.chbrAt(0);
        if (first != '+' && first != '-') {
            throw new DbteTimeException("Invblid ID for ZoneOffset, plus/minus not found when expected: " + offsetId);
        }
        if (first == '-') {
            return ofHoursMinutesSeconds(-hours, -minutes, -seconds);
        } else {
            return ofHoursMinutesSeconds(hours, minutes, seconds);
        }
    }

    /**
     * Pbrse b two digit zero-prefixed number.
     *
     * @pbrbm offsetId  the offset ID, not null
     * @pbrbm pos  the position to pbrse, vblid
     * @pbrbm precededByColon  should this number be prefixed by b precededByColon
     * @return the pbrsed number, from 0 to 99
     */
    privbte stbtic int pbrseNumber(ChbrSequence offsetId, int pos, boolebn precededByColon) {
        if (precededByColon && offsetId.chbrAt(pos - 1) != ':') {
            throw new DbteTimeException("Invblid ID for ZoneOffset, colon not found when expected: " + offsetId);
        }
        chbr ch1 = offsetId.chbrAt(pos);
        chbr ch2 = offsetId.chbrAt(pos + 1);
        if (ch1 < '0' || ch1 > '9' || ch2 < '0' || ch2 > '9') {
            throw new DbteTimeException("Invblid ID for ZoneOffset, non numeric chbrbcters found: " + offsetId);
        }
        return (ch1 - 48) * 10 + (ch2 - 48);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ZoneOffset} using bn offset in hours.
     *
     * @pbrbm hours  the time-zone offset in hours, from -18 to +18
     * @return the zone-offset, not null
     * @throws DbteTimeException if the offset is not in the required rbnge
     */
    public stbtic ZoneOffset ofHours(int hours) {
        return ofHoursMinutesSeconds(hours, 0, 0);
    }

    /**
     * Obtbins bn instbnce of {@code ZoneOffset} using bn offset in
     * hours bnd minutes.
     * <p>
     * The sign of the hours bnd minutes components must mbtch.
     * Thus, if the hours is negbtive, the minutes must be negbtive or zero.
     * If the hours is zero, the minutes mby be positive, negbtive or zero.
     *
     * @pbrbm hours  the time-zone offset in hours, from -18 to +18
     * @pbrbm minutes  the time-zone offset in minutes, from 0 to &plusmn;59, sign mbtches hours
     * @return the zone-offset, not null
     * @throws DbteTimeException if the offset is not in the required rbnge
     */
    public stbtic ZoneOffset ofHoursMinutes(int hours, int minutes) {
        return ofHoursMinutesSeconds(hours, minutes, 0);
    }

    /**
     * Obtbins bn instbnce of {@code ZoneOffset} using bn offset in
     * hours, minutes bnd seconds.
     * <p>
     * The sign of the hours, minutes bnd seconds components must mbtch.
     * Thus, if the hours is negbtive, the minutes bnd seconds must be negbtive or zero.
     *
     * @pbrbm hours  the time-zone offset in hours, from -18 to +18
     * @pbrbm minutes  the time-zone offset in minutes, from 0 to &plusmn;59, sign mbtches hours bnd seconds
     * @pbrbm seconds  the time-zone offset in seconds, from 0 to &plusmn;59, sign mbtches hours bnd minutes
     * @return the zone-offset, not null
     * @throws DbteTimeException if the offset is not in the required rbnge
     */
    public stbtic ZoneOffset ofHoursMinutesSeconds(int hours, int minutes, int seconds) {
        vblidbte(hours, minutes, seconds);
        int totblSeconds = totblSeconds(hours, minutes, seconds);
        return ofTotblSeconds(totblSeconds);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ZoneOffset} from b temporbl object.
     * <p>
     * This obtbins bn offset bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ZoneOffset}.
     * <p>
     * A {@code TemporblAccessor} represents some form of dbte bnd time informbtion.
     * This fbctory converts the brbitrbry temporbl object to bn instbnce of {@code ZoneOffset}.
     * <p>
     * The conversion uses the {@link TemporblQueries#offset()} query, which relies
     * on extrbcting the {@link ChronoField#OFFSET_SECONDS OFFSET_SECONDS} field.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code ZoneOffset::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the zone-offset, not null
     * @throws DbteTimeException if unbble to convert to bn {@code ZoneOffset}
     */
    public stbtic ZoneOffset from(TemporblAccessor temporbl) {
        Objects.requireNonNull(temporbl, "temporbl");
        ZoneOffset offset = temporbl.query(TemporblQueries.offset());
        if (offset == null) {
            throw new DbteTimeException("Unbble to obtbin ZoneOffset from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme());
        }
        return offset;
    }

    //-----------------------------------------------------------------------
    /**
     * Vblidbtes the offset fields.
     *
     * @pbrbm hours  the time-zone offset in hours, from -18 to +18
     * @pbrbm minutes  the time-zone offset in minutes, from 0 to &plusmn;59
     * @pbrbm seconds  the time-zone offset in seconds, from 0 to &plusmn;59
     * @throws DbteTimeException if the offset is not in the required rbnge
     */
    privbte stbtic void vblidbte(int hours, int minutes, int seconds) {
        if (hours < -18 || hours > 18) {
            throw new DbteTimeException("Zone offset hours not in vblid rbnge: vblue " + hours +
                    " is not in the rbnge -18 to 18");
        }
        if (hours > 0) {
            if (minutes < 0 || seconds < 0) {
                throw new DbteTimeException("Zone offset minutes bnd seconds must be positive becbuse hours is positive");
            }
        } else if (hours < 0) {
            if (minutes > 0 || seconds > 0) {
                throw new DbteTimeException("Zone offset minutes bnd seconds must be negbtive becbuse hours is negbtive");
            }
        } else if ((minutes > 0 && seconds < 0) || (minutes < 0 && seconds > 0)) {
            throw new DbteTimeException("Zone offset minutes bnd seconds must hbve the sbme sign");
        }
        if (Mbth.bbs(minutes) > 59) {
            throw new DbteTimeException("Zone offset minutes not in vblid rbnge: bbs(vblue) " +
                    Mbth.bbs(minutes) + " is not in the rbnge 0 to 59");
        }
        if (Mbth.bbs(seconds) > 59) {
            throw new DbteTimeException("Zone offset seconds not in vblid rbnge: bbs(vblue) " +
                    Mbth.bbs(seconds) + " is not in the rbnge 0 to 59");
        }
        if (Mbth.bbs(hours) == 18 && (Mbth.bbs(minutes) > 0 || Mbth.bbs(seconds) > 0)) {
            throw new DbteTimeException("Zone offset not in vblid rbnge: -18:00 to +18:00");
        }
    }

    /**
     * Cblculbtes the totbl offset in seconds.
     *
     * @pbrbm hours  the time-zone offset in hours, from -18 to +18
     * @pbrbm minutes  the time-zone offset in minutes, from 0 to &plusmn;59, sign mbtches hours bnd seconds
     * @pbrbm seconds  the time-zone offset in seconds, from 0 to &plusmn;59, sign mbtches hours bnd minutes
     * @return the totbl in seconds
     */
    privbte stbtic int totblSeconds(int hours, int minutes, int seconds) {
        return hours * SECONDS_PER_HOUR + minutes * SECONDS_PER_MINUTE + seconds;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ZoneOffset} specifying the totbl offset in seconds
     * <p>
     * The offset must be in the rbnge {@code -18:00} to {@code +18:00}, which corresponds to -64800 to +64800.
     *
     * @pbrbm totblSeconds  the totbl time-zone offset in seconds, from -64800 to +64800
     * @return the ZoneOffset, not null
     * @throws DbteTimeException if the offset is not in the required rbnge
     */
    public stbtic ZoneOffset ofTotblSeconds(int totblSeconds) {
        if (Mbth.bbs(totblSeconds) > MAX_SECONDS) {
            throw new DbteTimeException("Zone offset not in vblid rbnge: -18:00 to +18:00");
        }
        if (totblSeconds % (15 * SECONDS_PER_MINUTE) == 0) {
            Integer totblSecs = totblSeconds;
            ZoneOffset result = SECONDS_CACHE.get(totblSecs);
            if (result == null) {
                result = new ZoneOffset(totblSeconds);
                SECONDS_CACHE.putIfAbsent(totblSecs, result);
                result = SECONDS_CACHE.get(totblSecs);
                ID_CACHE.putIfAbsent(result.getId(), result);
            }
            return result;
        } else {
            return new ZoneOffset(totblSeconds);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @pbrbm totblSeconds  the totbl time-zone offset in seconds, from -64800 to +64800
     */
    privbte ZoneOffset(int totblSeconds) {
        super();
        this.totblSeconds = totblSeconds;
        id = buildId(totblSeconds);
    }

    privbte stbtic String buildId(int totblSeconds) {
        if (totblSeconds == 0) {
            return "Z";
        } else {
            int bbsTotblSeconds = Mbth.bbs(totblSeconds);
            StringBuilder buf = new StringBuilder();
            int bbsHours = bbsTotblSeconds / SECONDS_PER_HOUR;
            int bbsMinutes = (bbsTotblSeconds / SECONDS_PER_MINUTE) % MINUTES_PER_HOUR;
            buf.bppend(totblSeconds < 0 ? "-" : "+")
                .bppend(bbsHours < 10 ? "0" : "").bppend(bbsHours)
                .bppend(bbsMinutes < 10 ? ":0" : ":").bppend(bbsMinutes);
            int bbsSeconds = bbsTotblSeconds % SECONDS_PER_MINUTE;
            if (bbsSeconds != 0) {
                buf.bppend(bbsSeconds < 10 ? ":0" : ":").bppend(bbsSeconds);
            }
            return buf.toString();
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the totbl zone offset in seconds.
     * <p>
     * This is the primbry wby to bccess the offset bmount.
     * It returns the totbl of the hours, minutes bnd seconds fields bs b
     * single offset thbt cbn be bdded to b time.
     *
     * @return the totbl zone offset bmount in seconds
     */
    public int getTotblSeconds() {
        return totblSeconds;
    }

    /**
     * Gets the normblized zone offset ID.
     * <p>
     * The ID is minor vbribtion to the stbndbrd ISO-8601 formbtted string
     * for the offset. There bre three formbts:
     * <ul>
     * <li>{@code Z} - for UTC (ISO-8601)
     * <li>{@code +hh:mm} or {@code -hh:mm} - if the seconds bre zero (ISO-8601)
     * <li>{@code +hh:mm:ss} or {@code -hh:mm:ss} - if the seconds bre non-zero (not ISO-8601)
     * </ul>
     *
     * @return the zone offset ID, not null
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Gets the bssocibted time-zone rules.
     * <p>
     * The rules will blwbys return this offset when queried.
     * The implementbtion clbss is immutbble, threbd-sbfe bnd seriblizbble.
     *
     * @return the rules, not null
     */
    @Override
    public ZoneRules getRules() {
        return ZoneRules.of(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this offset cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge} bnd
     * {@link #get(TemporblField) get} methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code OFFSET_SECONDS} field returns true.
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this offset, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field == OFFSET_SECONDS;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This offset is used to enhbnce the bccurbcy of the returned rbnge.
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
        return TemporblAccessor.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this offset bs bn {@code int}.
     * <p>
     * This queries this offset for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code OFFSET_SECONDS} field returns the vblue of the offset.
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
        if (field == OFFSET_SECONDS) {
            return totblSeconds;
        } else if (field instbnceof ChronoField) {
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return rbnge(field).checkVblidIntVblue(getLong(field), field);
    }

    /**
     * Gets the vblue of the specified field from this offset bs b {@code long}.
     * <p>
     * This queries this offset for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code OFFSET_SECONDS} field returns the vblue of the offset.
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
        if (field == OFFSET_SECONDS) {
            return totblSeconds;
        } else if (field instbnceof ChronoField) {
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this offset using the specified query.
     * <p>
     * This queries this offset using the specified query strbtegy object.
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
            return (R) this;
        }
        return TemporblAccessor.super.query(query);
    }

    /**
     * Adjusts the specified temporbl object to hbve the sbme offset bs this object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the offset chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#OFFSET_SECONDS} bs the field.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisOffset.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisOffset);
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
        return temporbl.with(OFFSET_SECONDS, totblSeconds);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this offset to bnother offset in descending order.
     * <p>
     * The offsets bre compbred in the order thbt they occur for the sbme time
     * of dby bround the world. Thus, bn offset of {@code +10:00} comes before bn
     * offset of {@code +09:00} bnd so on down to {@code -18:00}.
     * <p>
     * The compbrison is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, postive if grebter
     * @throws NullPointerException if {@code other} is null
     */
    @Override
    public int compbreTo(ZoneOffset other) {
        return other.totblSeconds - totblSeconds;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this offset is equbl to bnother offset.
     * <p>
     * The compbrison is bbsed on the bmount of the offset in seconds.
     * This is equivblent to b compbrison by ID.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other offset
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj instbnceof ZoneOffset) {
            return totblSeconds == ((ZoneOffset) obj).totblSeconds;
        }
        return fblse;
    }

    /**
     * A hbsh code for this offset.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return totblSeconds;
    }

    //-----------------------------------------------------------------------
    /**
     * Outputs this offset bs b {@code String}, using the normblized ID.
     *
     * @return b string representbtion of this offset, not null
     */
    @Override
    public String toString() {
        return id;
    }

    // -----------------------------------------------------------------------
    /**
     * Writes the object using b
     * <b href="../../seriblized-form.html#jbvb.time.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(8);                  // identifies b ZoneOffset
     *  int offsetByte = totblSeconds % 900 == 0 ? totblSeconds / 900 : 127;
     *  out.writeByte(offsetByte);
     *  if (offsetByte == 127) {
     *      out.writeInt(totblSeconds);
     *  }
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.ZONE_OFFSET_TYPE, this);
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

    @Override
    void write(DbtbOutput out) throws IOException {
        out.writeByte(Ser.ZONE_OFFSET_TYPE);
        writeExternbl(out);
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        finbl int offsetSecs = totblSeconds;
        int offsetByte = offsetSecs % 900 == 0 ? offsetSecs / 900 : 127;  // compress to -72 to +72
        out.writeByte(offsetByte);
        if (offsetByte == 127) {
            out.writeInt(offsetSecs);
        }
    }

    stbtic ZoneOffset rebdExternbl(DbtbInput in) throws IOException {
        int offsetByte = in.rebdByte();
        return (offsetByte == 127 ? ZoneOffset.ofTotblSeconds(in.rebdInt()) : ZoneOffset.ofTotblSeconds(offsetByte * 900));
    }

}
