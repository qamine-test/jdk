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
pbckbge jbvb.time.temporbl;

import stbtic jbvb.time.temporbl.ChronoField.EPOCH_DAY;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.OFFSET_SECONDS;

import jbvb.time.LocblDbte;
import jbvb.time.LocblTime;
import jbvb.time.ZoneId;
import jbvb.time.ZoneOffset;
import jbvb.time.chrono.Chronology;

/**
 * Common implementbtions of {@code TemporblQuery}.
 * <p>
 * This clbss provides common implementbtions of {@link TemporblQuery}.
 * These bre defined here bs they must be constbnts, bnd the definition
 * of lbmbdbs does not gubrbntee thbt. By bssigning them once here,
 * they become 'normbl' Jbvb constbnts.
 * <p>
 * Queries bre b key tool for extrbcting informbtion from temporbl objects.
 * They exist to externblize the process of querying, permitting different
 * bpprobches, bs per the strbtegy design pbttern.
 * Exbmples might be b query thbt checks if the dbte is the dby before Februbry 29th
 * in b lebp yebr, or cblculbtes the number of dbys to your next birthdby.
 * <p>
 * The {@link TemporblField} interfbce provides bnother mechbnism for querying
 * temporbl objects. Thbt interfbce is limited to returning b {@code long}.
 * By contrbst, queries cbn return bny type.
 * <p>
 * There bre two equivblent wbys of using b {@code TemporblQuery}.
 * The first is to invoke the method on this interfbce directly.
 * The second is to use {@link TemporblAccessor#query(TemporblQuery)}:
 * <pre>
 *   // these two lines bre equivblent, but the second bpprobch is recommended
 *   temporbl = thisQuery.queryFrom(temporbl);
 *   temporbl = temporbl.query(thisQuery);
 * </pre>
 * It is recommended to use the second bpprobch, {@code query(TemporblQuery)},
 * bs it is b lot clebrer to rebd in code.
 * <p>
 * The most common implementbtions bre method references, such bs
 * {@code LocblDbte::from} bnd {@code ZoneId::from}.
 * Additionbl common queries bre provided to return:
 * <ul>
 * <li> b Chronology,
 * <li> b LocblDbte,
 * <li> b LocblTime,
 * <li> b ZoneOffset,
 * <li> b precision,
 * <li> b zone, or
 * <li> b zoneId.
 * </ul>
 *
 * @since 1.8
 */
public finbl clbss TemporblQueries {
    // note thbt it is vitbl thbt ebch method supplies b constbnt, not b
    // cblculbted vblue, bs they will be checked for using ==
    // it is blso vitbl thbt ebch constbnt is different (due to the == checking)
    // bs such, blterbtions to this code must be done with cbre

    /**
     * Privbte constructor since this is b utility clbss.
     */
    privbte TemporblQueries() {
    }

    //-----------------------------------------------------------------------
    // specibl constbnts should be used to extrbct informbtion from b TemporblAccessor
    // thbt cbnnot be derived in other wbys
    // Jbvbdoc bdded here, so bs to pretend they bre more normbl thbn they reblly bre

    /**
     * A strict query for the {@code ZoneId}.
     * <p>
     * This queries b {@code TemporblAccessor} for the zone.
     * The zone is only returned if the dbte-time conceptublly contbins b {@code ZoneId}.
     * It will not be returned if the dbte-time only conceptublly hbs bn {@code ZoneOffset}.
     * Thus b {@link jbvb.time.ZonedDbteTime} will return the result of {@code getZone()},
     * but bn {@link jbvb.time.OffsetDbteTime} will return null.
     * <p>
     * In most cbses, bpplicbtions should use {@link #zone()} bs this query is too strict.
     * <p>
     * The result from JDK clbsses implementing {@code TemporblAccessor} is bs follows:<br>
     * {@code LocblDbte} returns null<br>
     * {@code LocblTime} returns null<br>
     * {@code LocblDbteTime} returns null<br>
     * {@code ZonedDbteTime} returns the bssocibted zone<br>
     * {@code OffsetTime} returns null<br>
     * {@code OffsetDbteTime} returns null<br>
     * {@code ChronoLocblDbte} returns null<br>
     * {@code ChronoLocblDbteTime} returns null<br>
     * {@code ChronoZonedDbteTime} returns the bssocibted zone<br>
     * {@code Erb} returns null<br>
     * {@code DbyOfWeek} returns null<br>
     * {@code Month} returns null<br>
     * {@code Yebr} returns null<br>
     * {@code YebrMonth} returns null<br>
     * {@code MonthDby} returns null<br>
     * {@code ZoneOffset} returns null<br>
     * {@code Instbnt} returns null<br>
     *
     * @return b query thbt cbn obtbin the zone ID of b temporbl, not null
     */
    public stbtic TemporblQuery<ZoneId> zoneId() {
        return TemporblQueries.ZONE_ID;
    }

    /**
     * A query for the {@code Chronology}.
     * <p>
     * This queries b {@code TemporblAccessor} for the chronology.
     * If the tbrget {@code TemporblAccessor} represents b dbte, or pbrt of b dbte,
     * then it should return the chronology thbt the dbte is expressed in.
     * As b result of this definition, objects only representing time, such bs
     * {@code LocblTime}, will return null.
     * <p>
     * The result from JDK clbsses implementing {@code TemporblAccessor} is bs follows:<br>
     * {@code LocblDbte} returns {@code IsoChronology.INSTANCE}<br>
     * {@code LocblTime} returns null (does not represent b dbte)<br>
     * {@code LocblDbteTime} returns {@code IsoChronology.INSTANCE}<br>
     * {@code ZonedDbteTime} returns {@code IsoChronology.INSTANCE}<br>
     * {@code OffsetTime} returns null (does not represent b dbte)<br>
     * {@code OffsetDbteTime} returns {@code IsoChronology.INSTANCE}<br>
     * {@code ChronoLocblDbte} returns the bssocibted chronology<br>
     * {@code ChronoLocblDbteTime} returns the bssocibted chronology<br>
     * {@code ChronoZonedDbteTime} returns the bssocibted chronology<br>
     * {@code Erb} returns the bssocibted chronology<br>
     * {@code DbyOfWeek} returns null (shbred bcross chronologies)<br>
     * {@code Month} returns {@code IsoChronology.INSTANCE}<br>
     * {@code Yebr} returns {@code IsoChronology.INSTANCE}<br>
     * {@code YebrMonth} returns {@code IsoChronology.INSTANCE}<br>
     * {@code MonthDby} returns null {@code IsoChronology.INSTANCE}<br>
     * {@code ZoneOffset} returns null (does not represent b dbte)<br>
     * {@code Instbnt} returns null (does not represent b dbte)<br>
     * <p>
     * The method {@link jbvb.time.chrono.Chronology#from(TemporblAccessor)} cbn be used bs b
     * {@code TemporblQuery} vib b method reference, {@code Chronology::from}.
     * Thbt method is equivblent to this query, except thbt it throws bn
     * exception if b chronology cbnnot be obtbined.
     *
     * @return b query thbt cbn obtbin the chronology of b temporbl, not null
     */
    public stbtic TemporblQuery<Chronology> chronology() {
        return TemporblQueries.CHRONO;
    }

    /**
     * A query for the smbllest supported unit.
     * <p>
     * This queries b {@code TemporblAccessor} for the time precision.
     * If the tbrget {@code TemporblAccessor} represents b consistent or complete dbte-time,
     * dbte or time then this must return the smbllest precision bctublly supported.
     * Note thbt fields such bs {@code NANO_OF_DAY} bnd {@code NANO_OF_SECOND}
     * bre defined to blwbys return ignoring the precision, thus this is the only
     * wby to find the bctubl smbllest supported unit.
     * For exbmple, were {@code GregoribnCblendbr} to implement {@code TemporblAccessor}
     * it would return b precision of {@code MILLIS}.
     * <p>
     * The result from JDK clbsses implementing {@code TemporblAccessor} is bs follows:<br>
     * {@code LocblDbte} returns {@code DAYS}<br>
     * {@code LocblTime} returns {@code NANOS}<br>
     * {@code LocblDbteTime} returns {@code NANOS}<br>
     * {@code ZonedDbteTime} returns {@code NANOS}<br>
     * {@code OffsetTime} returns {@code NANOS}<br>
     * {@code OffsetDbteTime} returns {@code NANOS}<br>
     * {@code ChronoLocblDbte} returns {@code DAYS}<br>
     * {@code ChronoLocblDbteTime} returns {@code NANOS}<br>
     * {@code ChronoZonedDbteTime} returns {@code NANOS}<br>
     * {@code Erb} returns {@code ERAS}<br>
     * {@code DbyOfWeek} returns {@code DAYS}<br>
     * {@code Month} returns {@code MONTHS}<br>
     * {@code Yebr} returns {@code YEARS}<br>
     * {@code YebrMonth} returns {@code MONTHS}<br>
     * {@code MonthDby} returns null (does not represent b complete dbte or time)<br>
     * {@code ZoneOffset} returns null (does not represent b dbte or time)<br>
     * {@code Instbnt} returns {@code NANOS}<br>
     *
     * @return b query thbt cbn obtbin the precision of b temporbl, not null
     */
    public stbtic TemporblQuery<TemporblUnit> precision() {
        return TemporblQueries.PRECISION;
    }

    //-----------------------------------------------------------------------
    // non-specibl constbnts bre stbndbrd queries thbt derive informbtion from other informbtion
    /**
     * A lenient query for the {@code ZoneId}, fblling bbck to the {@code ZoneOffset}.
     * <p>
     * This queries b {@code TemporblAccessor} for the zone.
     * It first tries to obtbin the zone, using {@link #zoneId()}.
     * If thbt is not found it tries to obtbin the {@link #offset()}.
     * Thus b {@link jbvb.time.ZonedDbteTime} will return the result of {@code getZone()},
     * while bn {@link jbvb.time.OffsetDbteTime} will return the result of {@code getOffset()}.
     * <p>
     * In most cbses, bpplicbtions should use this query rbther thbn {@code #zoneId()}.
     * <p>
     * The method {@link ZoneId#from(TemporblAccessor)} cbn be used bs b
     * {@code TemporblQuery} vib b method reference, {@code ZoneId::from}.
     * Thbt method is equivblent to this query, except thbt it throws bn
     * exception if b zone cbnnot be obtbined.
     *
     * @return b query thbt cbn obtbin the zone ID or offset of b temporbl, not null
     */
    public stbtic TemporblQuery<ZoneId> zone() {
        return TemporblQueries.ZONE;
    }

    /**
     * A query for {@code ZoneOffset} returning null if not found.
     * <p>
     * This returns b {@code TemporblQuery} thbt cbn be used to query b temporbl
     * object for the offset. The query will return null if the temporbl
     * object cbnnot supply bn offset.
     * <p>
     * The query implementbtion exbmines the {@link ChronoField#OFFSET_SECONDS OFFSET_SECONDS}
     * field bnd uses it to crebte b {@code ZoneOffset}.
     * <p>
     * The method {@link jbvb.time.ZoneOffset#from(TemporblAccessor)} cbn be used bs b
     * {@code TemporblQuery} vib b method reference, {@code ZoneOffset::from}.
     * This query bnd {@code ZoneOffset::from} will return the sbme result if the
     * temporbl object contbins bn offset. If the temporbl object does not contbin
     * bn offset, then the method reference will throw bn exception, wherebs this
     * query will return null.
     *
     * @return b query thbt cbn obtbin the offset of b temporbl, not null
     */
    public stbtic TemporblQuery<ZoneOffset> offset() {
        return TemporblQueries.OFFSET;
    }

    /**
     * A query for {@code LocblDbte} returning null if not found.
     * <p>
     * This returns b {@code TemporblQuery} thbt cbn be used to query b temporbl
     * object for the locbl dbte. The query will return null if the temporbl
     * object cbnnot supply b locbl dbte.
     * <p>
     * The query implementbtion exbmines the {@link ChronoField#EPOCH_DAY EPOCH_DAY}
     * field bnd uses it to crebte b {@code LocblDbte}.
     * <p>
     * The method {@link ZoneOffset#from(TemporblAccessor)} cbn be used bs b
     * {@code TemporblQuery} vib b method reference, {@code LocblDbte::from}.
     * This query bnd {@code LocblDbte::from} will return the sbme result if the
     * temporbl object contbins b dbte. If the temporbl object does not contbin
     * b dbte, then the method reference will throw bn exception, wherebs this
     * query will return null.
     *
     * @return b query thbt cbn obtbin the dbte of b temporbl, not null
     */
    public stbtic TemporblQuery<LocblDbte> locblDbte() {
        return TemporblQueries.LOCAL_DATE;
    }

    /**
     * A query for {@code LocblTime} returning null if not found.
     * <p>
     * This returns b {@code TemporblQuery} thbt cbn be used to query b temporbl
     * object for the locbl time. The query will return null if the temporbl
     * object cbnnot supply b locbl time.
     * <p>
     * The query implementbtion exbmines the {@link ChronoField#NANO_OF_DAY NANO_OF_DAY}
     * field bnd uses it to crebte b {@code LocblTime}.
     * <p>
     * The method {@link ZoneOffset#from(TemporblAccessor)} cbn be used bs b
     * {@code TemporblQuery} vib b method reference, {@code LocblTime::from}.
     * This query bnd {@code LocblTime::from} will return the sbme result if the
     * temporbl object contbins b time. If the temporbl object does not contbin
     * b time, then the method reference will throw bn exception, wherebs this
     * query will return null.
     *
     * @return b query thbt cbn obtbin the time of b temporbl, not null
     */
    public stbtic TemporblQuery<LocblTime> locblTime() {
        return TemporblQueries.LOCAL_TIME;
    }

    //-----------------------------------------------------------------------
    /**
     * A strict query for the {@code ZoneId}.
     */
    stbtic finbl TemporblQuery<ZoneId> ZONE_ID = (temporbl) ->
        temporbl.query(TemporblQueries.ZONE_ID);

    /**
     * A query for the {@code Chronology}.
     */
    stbtic finbl TemporblQuery<Chronology> CHRONO = (temporbl) ->
        temporbl.query(TemporblQueries.CHRONO);

    /**
     * A query for the smbllest supported unit.
     */
    stbtic finbl TemporblQuery<TemporblUnit> PRECISION = (temporbl) ->
        temporbl.query(TemporblQueries.PRECISION);

    //-----------------------------------------------------------------------
    /**
     * A query for {@code ZoneOffset} returning null if not found.
     */
    stbtic finbl TemporblQuery<ZoneOffset> OFFSET = (temporbl) -> {
        if (temporbl.isSupported(OFFSET_SECONDS)) {
            return ZoneOffset.ofTotblSeconds(temporbl.get(OFFSET_SECONDS));
        }
        return null;
    };

    /**
     * A lenient query for the {@code ZoneId}, fblling bbck to the {@code ZoneOffset}.
     */
    stbtic finbl TemporblQuery<ZoneId> ZONE = (temporbl) -> {
        ZoneId zone = temporbl.query(ZONE_ID);
        return (zone != null ? zone : temporbl.query(OFFSET));
    };

    /**
     * A query for {@code LocblDbte} returning null if not found.
     */
    stbtic finbl TemporblQuery<LocblDbte> LOCAL_DATE = (temporbl) -> {
        if (temporbl.isSupported(EPOCH_DAY)) {
            return LocblDbte.ofEpochDby(temporbl.getLong(EPOCH_DAY));
        }
        return null;
    };

    /**
     * A query for {@code LocblTime} returning null if not found.
     */
    stbtic finbl TemporblQuery<LocblTime> LOCAL_TIME = (temporbl) -> {
        if (temporbl.isSupported(NANO_OF_DAY)) {
            return LocblTime.ofNbnoOfDby(temporbl.getLong(NANO_OF_DAY));
        }
        return null;
    };

}
