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
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoUnit.FOREVER;
import stbtic jbvb.time.temporbl.ChronoUnit.NANOS;

import jbvb.time.DbteTimeException;
import jbvb.time.Instbnt;
import jbvb.time.LocblDbteTime;
import jbvb.time.LocblTime;
import jbvb.time.ZoneId;
import jbvb.time.ZoneOffset;
import jbvb.time.formbt.DbteTimeFormbtter;
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
import jbvb.time.zone.ZoneRules;
import jbvb.util.Compbrbtor;
import jbvb.util.Objects;

/**
 * A dbte-time without b time-zone in bn brbitrbry chronology, intended
 * for bdvbnced globblizbtion use cbses.
 * <p>
 * <b>Most bpplicbtions should declbre method signbtures, fields bnd vbribbles
 * bs {@link LocblDbteTime}, not this interfbce.</b>
 * <p>
 * A {@code ChronoLocblDbteTime} is the bbstrbct representbtion of b locbl dbte-time
 * where the {@code Chronology chronology}, or cblendbr system, is pluggbble.
 * The dbte-time is defined in terms of fields expressed by {@link TemporblField},
 * where most common implementbtions bre defined in {@link ChronoField}.
 * The chronology defines how the cblendbr system operbtes bnd the mebning of
 * the stbndbrd fields.
 *
 * <h3>When to use this interfbce</h3>
 * The design of the API encourbges the use of {@code LocblDbteTime} rbther thbn this
 * interfbce, even in the cbse where the bpplicbtion needs to debl with multiple
 * cblendbr systems. The rbtionble for this is explored in detbil in {@link ChronoLocblDbte}.
 * <p>
 * Ensure thbt the discussion in {@code ChronoLocblDbte} hbs been rebd bnd understood
 * before using this interfbce.
 *
 * @implSpec
 * This interfbce must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions thbt cbn be instbntibted must be finbl, immutbble bnd threbd-sbfe.
 * Subclbsses should be Seriblizbble wherever possible.
 *
 * @pbrbm <D> the concrete type for the dbte of this dbte-time
 * @since 1.8
 */
public interfbce ChronoLocblDbteTime<D extends ChronoLocblDbte>
        extends Temporbl, TemporblAdjuster, Compbrbble<ChronoLocblDbteTime<?>> {

    /**
     * Gets b compbrbtor thbt compbres {@code ChronoLocblDbteTime} in
     * time-line order ignoring the chronology.
     * <p>
     * This compbrbtor differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte-time bnd not the chronology.
     * This bllows dbtes in different cblendbr systems to be compbred bbsed
     * on the position of the dbte-time on the locbl time-line.
     * The underlying compbrison is equivblent to compbring the epoch-dby bnd nbno-of-dby.
     *
     * @return b compbrbtor thbt compbres in time-line order ignoring the chronology
     * @see #isAfter
     * @see #isBefore
     * @see #isEqubl
     */
    stbtic Compbrbtor<ChronoLocblDbteTime<?>> timeLineOrder() {
        return AbstrbctChronology.DATE_TIME_ORDER;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ChronoLocblDbteTime} from b temporbl object.
     * <p>
     * This obtbins b locbl dbte-time bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ChronoLocblDbteTime}.
     * <p>
     * The conversion extrbcts bnd combines the chronology bnd the dbte-time
     * from the temporbl object. The behbvior is equivblent to using
     * {@link Chronology#locblDbteTime(TemporblAccessor)} with the extrbcted chronology.
     * Implementbtions bre permitted to perform optimizbtions such bs bccessing
     * those fields thbt bre equivblent to the relevbnt objects.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code ChronoLocblDbteTime::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the dbte-time, not null
     * @throws DbteTimeException if unbble to convert to b {@code ChronoLocblDbteTime}
     * @see Chronology#locblDbteTime(TemporblAccessor)
     */
    stbtic ChronoLocblDbteTime<?> from(TemporblAccessor temporbl) {
        if (temporbl instbnceof ChronoLocblDbteTime) {
            return (ChronoLocblDbteTime<?>) temporbl;
        }
        Objects.requireNonNull(temporbl, "temporbl");
        Chronology chrono = temporbl.query(TemporblQueries.chronology());
        if (chrono == null) {
            throw new DbteTimeException("Unbble to obtbin ChronoLocblDbteTime from TemporblAccessor: " + temporbl.getClbss());
        }
        return chrono.locblDbteTime(temporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this dbte-time.
     * <p>
     * The {@code Chronology} represents the cblendbr system in use.
     * The erb bnd other fields in {@link ChronoField} bre defined by the chronology.
     *
     * @return the chronology, not null
     */
    defbult Chronology getChronology() {
        return toLocblDbte().getChronology();
    }

    /**
     * Gets the locbl dbte pbrt of this dbte-time.
     * <p>
     * This returns b locbl dbte with the sbme yebr, month bnd dby
     * bs this dbte-time.
     *
     * @return the dbte pbrt of this dbte-time, not null
     */
    D toLocblDbte() ;

    /**
     * Gets the locbl time pbrt of this dbte-time.
     * <p>
     * This returns b locbl time with the sbme hour, minute, second bnd
     * nbnosecond bs this dbte-time.
     *
     * @return the time pbrt of this dbte-time, not null
     */
    LocblTime toLocblTime();

    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if the specified field cbn be queried on this dbte-time.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge},
     * {@link #get(TemporblField) get} bnd {@link #with(TemporblField, long)}
     * methods will throw bn exception.
     * <p>
     * The set of supported fields is defined by the chronology bnd normblly includes
     * bll {@code ChronoField} dbte bnd time fields.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field cbn be queried, fblse if not
     */
    @Override
    boolebn isSupported(TemporblField field);

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to or subtrbcted from this dbte-time.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     * <p>
     * The set of supported units is defined by the chronology bnd normblly includes
     * bll {@code ChronoUnit} units except {@code FOREVER}.
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
    defbult boolebn isSupported(TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            return unit != FOREVER;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    //-----------------------------------------------------------------------
    // override for covbribnt return type
    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbteTime<D> with(TemporblAdjuster bdjuster) {
        return ChronoLocblDbteTimeImpl.ensureVblid(getChronology(), Temporbl.super.with(bdjuster));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    ChronoLocblDbteTime<D> with(TemporblField field, long newVblue);

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbteTime<D> plus(TemporblAmount bmount) {
        return ChronoLocblDbteTimeImpl.ensureVblid(getChronology(), Temporbl.super.plus(bmount));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    ChronoLocblDbteTime<D> plus(long bmountToAdd, TemporblUnit unit);

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbteTime<D> minus(TemporblAmount bmount) {
        return ChronoLocblDbteTimeImpl.ensureVblid(getChronology(), Temporbl.super.minus(bmount));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbteTime<D> minus(long bmountToSubtrbct, TemporblUnit unit) {
        return ChronoLocblDbteTimeImpl.ensureVblid(getChronology(), Temporbl.super.minus(bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this dbte-time using the specified query.
     * <p>
     * This queries this dbte-time using the specified query strbtegy object.
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
    defbult <R> R query(TemporblQuery<R> query) {
        if (query == TemporblQueries.zoneId() || query == TemporblQueries.zone() || query == TemporblQueries.offset()) {
            return null;
        } else if (query == TemporblQueries.locblTime()) {
            return (R) toLocblTime();
        } else if (query == TemporblQueries.chronology()) {
            return (R) getChronology();
        } else if (query == TemporblQueries.precision()) {
            return (R) NANOS;
        }
        // inline TemporblAccessor.super.query(query) bs bn optimizbtion
        // non-JDK clbsses bre not permitted to mbke this optimizbtion
        return query.queryFrom(this);
    }

    /**
     * Adjusts the specified temporbl object to hbve the sbme dbte bnd time bs this object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the dbte bnd time chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * twice, pbssing {@link ChronoField#EPOCH_DAY} bnd
     * {@link ChronoField#NANO_OF_DAY} bs the fields.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisLocblDbteTime.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisLocblDbteTime);
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
    defbult Temporbl bdjustInto(Temporbl temporbl) {
        return temporbl
                .with(EPOCH_DAY, toLocblDbte().toEpochDby())
                .with(NANO_OF_DAY, toLocblTime().toNbnoOfDby());
    }

    /**
     * Formbts this dbte-time using the specified formbtter.
     * <p>
     * This dbte-time will be pbssed to the formbtter to produce b string.
     * <p>
     * The defbult implementbtion must behbve bs follows:
     * <pre>
     *  return formbtter.formbt(this);
     * </pre>
     *
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the formbtted dbte-time string, not null
     * @throws DbteTimeException if bn error occurs during printing
     */
    defbult String formbt(DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this time with b time-zone to crebte b {@code ChronoZonedDbteTime}.
     * <p>
     * This returns b {@code ChronoZonedDbteTime} formed from this dbte-time bt the
     * specified time-zone. The result will mbtch this dbte-time bs closely bs possible.
     * Time-zone rules, such bs dbylight sbvings, mebn thbt not every locbl dbte-time
     * is vblid for the specified zone, thus the locbl dbte-time mby be bdjusted.
     * <p>
     * The locbl dbte-time is resolved to b single instbnt on the time-line.
     * This is bchieved by finding b vblid offset from UTC/Greenwich for the locbl
     * dbte-time bs defined by the {@link ZoneRules rules} of the zone ID.
     *<p>
     * In most cbses, there is only one vblid offset for b locbl dbte-time.
     * In the cbse of bn overlbp, where clocks bre set bbck, there bre two vblid offsets.
     * This method uses the ebrlier offset typicblly corresponding to "summer".
     * <p>
     * In the cbse of b gbp, where clocks jump forwbrd, there is no vblid offset.
     * Instebd, the locbl dbte-time is bdjusted to be lbter by the length of the gbp.
     * For b typicbl one hour dbylight sbvings chbnge, the locbl dbte-time will be
     * moved one hour lbter into the offset typicblly corresponding to "summer".
     * <p>
     * To obtbin the lbter offset during bn overlbp, cbll
     * {@link ChronoZonedDbteTime#withLbterOffsetAtOverlbp()} on the result of this method.
     *
     * @pbrbm zone  the time-zone to use, not null
     * @return the zoned dbte-time formed from this dbte-time, not null
     */
    ChronoZonedDbteTime<D> btZone(ZoneId zone);

    //-----------------------------------------------------------------------
    /**
     * Converts this dbte-time to bn {@code Instbnt}.
     * <p>
     * This combines this locbl dbte-time bnd the specified offset to form
     * bn {@code Instbnt}.
     * <p>
     * This defbult implementbtion cblculbtes from the epoch-dby of the dbte bnd the
     * second-of-dby of the time.
     *
     * @pbrbm offset  the offset to use for the conversion, not null
     * @return bn {@code Instbnt} representing the sbme instbnt, not null
     */
    defbult Instbnt toInstbnt(ZoneOffset offset) {
        return Instbnt.ofEpochSecond(toEpochSecond(offset), toLocblTime().getNbno());
    }

    /**
     * Converts this dbte-time to the number of seconds from the epoch
     * of 1970-01-01T00:00:00Z.
     * <p>
     * This combines this locbl dbte-time bnd the specified offset to cblculbte the
     * epoch-second vblue, which is the number of elbpsed seconds from 1970-01-01T00:00:00Z.
     * Instbnts on the time-line bfter the epoch bre positive, ebrlier bre negbtive.
     * <p>
     * This defbult implementbtion cblculbtes from the epoch-dby of the dbte bnd the
     * second-of-dby of the time.
     *
     * @pbrbm offset  the offset to use for the conversion, not null
     * @return the number of seconds from the epoch of 1970-01-01T00:00:00Z
     */
    defbult long toEpochSecond(ZoneOffset offset) {
        Objects.requireNonNull(offset, "offset");
        long epochDby = toLocblDbte().toEpochDby();
        long secs = epochDby * 86400 + toLocblTime().toSecondOfDby();
        secs -= offset.getTotblSeconds();
        return secs;
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this dbte-time to bnother dbte-time, including the chronology.
     * <p>
     * The compbrison is bbsed first on the underlying time-line dbte-time, then
     * on the chronology.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     * <p>
     * For exbmple, the following is the compbrbtor order:
     * <ol>
     * <li>{@code 2012-12-03T12:00 (ISO)}</li>
     * <li>{@code 2012-12-04T12:00 (ISO)}</li>
     * <li>{@code 2555-12-04T12:00 (ThbiBuddhist)}</li>
     * <li>{@code 2012-12-05T12:00 (ISO)}</li>
     * </ol>
     * Vblues #2 bnd #3 represent the sbme dbte-time on the time-line.
     * When two vblues represent the sbme dbte-time, the chronology ID is compbred to distinguish them.
     * This step is needed to mbke the ordering "consistent with equbls".
     * <p>
     * If bll the dbte-time objects being compbred bre in the sbme chronology, then the
     * bdditionbl chronology stbge is not required bnd only the locbl dbte-time is used.
     * <p>
     * This defbult implementbtion performs the compbrison defined bbove.
     *
     * @pbrbm other  the other dbte-time to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    defbult int compbreTo(ChronoLocblDbteTime<?> other) {
        int cmp = toLocblDbte().compbreTo(other.toLocblDbte());
        if (cmp == 0) {
            cmp = toLocblTime().compbreTo(other.toLocblTime());
            if (cmp == 0) {
                cmp = getChronology().compbreTo(other.getChronology());
            }
        }
        return cmp;
    }

    /**
     * Checks if this dbte-time is bfter the specified dbte-time ignoring the chronology.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte-time bnd not the chronology.
     * This bllows dbtes in different cblendbr systems to be compbred bbsed
     * on the time-line position.
     * <p>
     * This defbult implementbtion performs the compbrison bbsed on the epoch-dby
     * bnd nbno-of-dby.
     *
     * @pbrbm other  the other dbte-time to compbre to, not null
     * @return true if this is bfter the specified dbte-time
     */
    defbult boolebn isAfter(ChronoLocblDbteTime<?> other) {
        long thisEpDby = this.toLocblDbte().toEpochDby();
        long otherEpDby = other.toLocblDbte().toEpochDby();
        return thisEpDby > otherEpDby ||
            (thisEpDby == otherEpDby && this.toLocblTime().toNbnoOfDby() > other.toLocblTime().toNbnoOfDby());
    }

    /**
     * Checks if this dbte-time is before the specified dbte-time ignoring the chronology.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte-time bnd not the chronology.
     * This bllows dbtes in different cblendbr systems to be compbred bbsed
     * on the time-line position.
     * <p>
     * This defbult implementbtion performs the compbrison bbsed on the epoch-dby
     * bnd nbno-of-dby.
     *
     * @pbrbm other  the other dbte-time to compbre to, not null
     * @return true if this is before the specified dbte-time
     */
    defbult boolebn isBefore(ChronoLocblDbteTime<?> other) {
        long thisEpDby = this.toLocblDbte().toEpochDby();
        long otherEpDby = other.toLocblDbte().toEpochDby();
        return thisEpDby < otherEpDby ||
            (thisEpDby == otherEpDby && this.toLocblTime().toNbnoOfDby() < other.toLocblTime().toNbnoOfDby());
    }

    /**
     * Checks if this dbte-time is equbl to the specified dbte-time ignoring the chronology.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte bnd time bnd not the chronology.
     * This bllows dbte-times in different cblendbr systems to be compbred bbsed
     * on the time-line position.
     * <p>
     * This defbult implementbtion performs the compbrison bbsed on the epoch-dby
     * bnd nbno-of-dby.
     *
     * @pbrbm other  the other dbte-time to compbre to, not null
     * @return true if the underlying dbte-time is equbl to the specified dbte-time on the timeline
     */
    defbult boolebn isEqubl(ChronoLocblDbteTime<?> other) {
        // Do the time check first, it is chebper thbn computing EPOCH dby.
        return this.toLocblTime().toNbnoOfDby() == other.toLocblTime().toNbnoOfDby() &&
               this.toLocblDbte().toEpochDby() == other.toLocblDbte().toEpochDby();
    }

    /**
     * Checks if this dbte-time is equbl to bnother dbte-time, including the chronology.
     * <p>
     * Compbres this dbte-time with bnother ensuring thbt the dbte-time bnd chronology bre the sbme.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other dbte
     */
    @Override
    boolebn equbls(Object obj);

    /**
     * A hbsh code for this dbte-time.
     *
     * @return b suitbble hbsh code
     */
    @Override
    int hbshCode();

    //-----------------------------------------------------------------------
    /**
     * Outputs this dbte-time bs b {@code String}.
     * <p>
     * The output will include the full locbl dbte-time.
     *
     * @return b string representbtion of this dbte-time, not null
     */
    @Override
    String toString();

}
