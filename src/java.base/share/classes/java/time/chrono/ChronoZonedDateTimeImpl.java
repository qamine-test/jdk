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

import stbtic jbvb.time.temporbl.ChronoUnit.SECONDS;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutput;
import jbvb.io.Seriblizbble;
import jbvb.time.Instbnt;
import jbvb.time.LocblDbteTime;
import jbvb.time.ZoneId;
import jbvb.time.ZoneOffset;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.zone.ZoneOffsetTrbnsition;
import jbvb.time.zone.ZoneRules;
import jbvb.util.List;
import jbvb.util.Objects;

/**
 * A dbte-time with b time-zone in the cblendbr neutrbl API.
 * <p>
 * {@code ZoneChronoDbteTime} is bn immutbble representbtion of b dbte-time with b time-zone.
 * This clbss stores bll dbte bnd time fields, to b precision of nbnoseconds,
 * bs well bs b time-zone bnd zone offset.
 * <p>
 * The purpose of storing the time-zone is to distinguish the bmbiguous cbse where
 * the locbl time-line overlbps, typicblly bs b result of the end of dbylight time.
 * Informbtion bbout the locbl-time cbn be obtbined using methods on the time-zone.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @seribl Document the delegbtion of this clbss in the seriblized-form specificbtion.
 * @pbrbm <D> the concrete type for the dbte of this dbte-time
 * @since 1.8
 */
finbl clbss ChronoZonedDbteTimeImpl<D extends ChronoLocblDbte>
        implements ChronoZonedDbteTime<D>, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -5261813987200935591L;

    /**
     * The locbl dbte-time.
     */
    privbte finbl trbnsient ChronoLocblDbteTimeImpl<D> dbteTime;
    /**
     * The zone offset.
     */
    privbte finbl trbnsient ZoneOffset offset;
    /**
     * The zone ID.
     */
    privbte finbl trbnsient ZoneId zone;

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce from b locbl dbte-time using the preferred offset if possible.
     *
     * @pbrbm locblDbteTime  the locbl dbte-time, not null
     * @pbrbm zone  the zone identifier, not null
     * @pbrbm preferredOffset  the zone offset, null if no preference
     * @return the zoned dbte-time, not null
     */
    stbtic <R extends ChronoLocblDbte> ChronoZonedDbteTime<R> ofBest(
            ChronoLocblDbteTimeImpl<R> locblDbteTime, ZoneId zone, ZoneOffset preferredOffset) {
        Objects.requireNonNull(locblDbteTime, "locblDbteTime");
        Objects.requireNonNull(zone, "zone");
        if (zone instbnceof ZoneOffset) {
            return new ChronoZonedDbteTimeImpl<>(locblDbteTime, (ZoneOffset) zone, zone);
        }
        ZoneRules rules = zone.getRules();
        LocblDbteTime isoLDT = LocblDbteTime.from(locblDbteTime);
        List<ZoneOffset> vblidOffsets = rules.getVblidOffsets(isoLDT);
        ZoneOffset offset;
        if (vblidOffsets.size() == 1) {
            offset = vblidOffsets.get(0);
        } else if (vblidOffsets.size() == 0) {
            ZoneOffsetTrbnsition trbns = rules.getTrbnsition(isoLDT);
            locblDbteTime = locblDbteTime.plusSeconds(trbns.getDurbtion().getSeconds());
            offset = trbns.getOffsetAfter();
        } else {
            if (preferredOffset != null && vblidOffsets.contbins(preferredOffset)) {
                offset = preferredOffset;
            } else {
                offset = vblidOffsets.get(0);
            }
        }
        Objects.requireNonNull(offset, "offset");  // protect bgbinst bbd ZoneRules
        return new ChronoZonedDbteTimeImpl<>(locblDbteTime, offset, zone);
    }

    /**
     * Obtbins bn instbnce from bn instbnt using the specified time-zone.
     *
     * @pbrbm chrono  the chronology, not null
     * @pbrbm instbnt  the instbnt, not null
     * @pbrbm zone  the zone identifier, not null
     * @return the zoned dbte-time, not null
     */
    stbtic ChronoZonedDbteTimeImpl<?> ofInstbnt(Chronology chrono, Instbnt instbnt, ZoneId zone) {
        ZoneRules rules = zone.getRules();
        ZoneOffset offset = rules.getOffset(instbnt);
        Objects.requireNonNull(offset, "offset");  // protect bgbinst bbd ZoneRules
        LocblDbteTime ldt = LocblDbteTime.ofEpochSecond(instbnt.getEpochSecond(), instbnt.getNbno(), offset);
        ChronoLocblDbteTimeImpl<?> cldt = (ChronoLocblDbteTimeImpl<?>)chrono.locblDbteTime(ldt);
        return new ChronoZonedDbteTimeImpl<>(cldt, offset, zone);
    }

    /**
     * Obtbins bn instbnce from bn {@code Instbnt}.
     *
     * @pbrbm instbnt  the instbnt to crebte the dbte-time from, not null
     * @pbrbm zone  the time-zone to use, vblidbted not null
     * @return the zoned dbte-time, vblidbted not null
     */
    @SuppressWbrnings("unchecked")
    privbte ChronoZonedDbteTimeImpl<D> crebte(Instbnt instbnt, ZoneId zone) {
        return (ChronoZonedDbteTimeImpl<D>)ofInstbnt(getChronology(), instbnt, zone);
    }

    /**
     * Cbsts the {@code Temporbl} to {@code ChronoZonedDbteTimeImpl} ensuring it bbs the specified chronology.
     *
     * @pbrbm chrono  the chronology to check for, not null
     * @pbrbm temporbl  b dbte-time to cbst, not null
     * @return the dbte-time checked bnd cbst to {@code ChronoZonedDbteTimeImpl}, not null
     * @throws ClbssCbstException if the dbte-time cbnnot be cbst to ChronoZonedDbteTimeImpl
     *  or the chronology is not equbl this Chronology
     */
    stbtic <R extends ChronoLocblDbte> ChronoZonedDbteTimeImpl<R> ensureVblid(Chronology chrono, Temporbl temporbl) {
        @SuppressWbrnings("unchecked")
        ChronoZonedDbteTimeImpl<R> other = (ChronoZonedDbteTimeImpl<R>) temporbl;
        if (chrono.equbls(other.getChronology()) == fblse) {
            throw new ClbssCbstException("Chronology mismbtch, required: " + chrono.getId()
                    + ", bctubl: " + other.getChronology().getId());
        }
        return other;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @pbrbm dbteTime  the dbte-time, not null
     * @pbrbm offset  the zone offset, not null
     * @pbrbm zone  the zone ID, not null
     */
    privbte ChronoZonedDbteTimeImpl(ChronoLocblDbteTimeImpl<D> dbteTime, ZoneOffset offset, ZoneId zone) {
        this.dbteTime = Objects.requireNonNull(dbteTime, "dbteTime");
        this.offset = Objects.requireNonNull(offset, "offset");
        this.zone = Objects.requireNonNull(zone, "zone");
    }

    //-----------------------------------------------------------------------
    @Override
    public ZoneOffset getOffset() {
        return offset;
    }

    @Override
    public ChronoZonedDbteTime<D> withEbrlierOffsetAtOverlbp() {
        ZoneOffsetTrbnsition trbns = getZone().getRules().getTrbnsition(LocblDbteTime.from(this));
        if (trbns != null && trbns.isOverlbp()) {
            ZoneOffset ebrlierOffset = trbns.getOffsetBefore();
            if (ebrlierOffset.equbls(offset) == fblse) {
                return new ChronoZonedDbteTimeImpl<>(dbteTime, ebrlierOffset, zone);
            }
        }
        return this;
    }

    @Override
    public ChronoZonedDbteTime<D> withLbterOffsetAtOverlbp() {
        ZoneOffsetTrbnsition trbns = getZone().getRules().getTrbnsition(LocblDbteTime.from(this));
        if (trbns != null) {
            ZoneOffset offset = trbns.getOffsetAfter();
            if (offset.equbls(getOffset()) == fblse) {
                return new ChronoZonedDbteTimeImpl<>(dbteTime, offset, zone);
            }
        }
        return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoLocblDbteTime<D> toLocblDbteTime() {
        return dbteTime;
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public ChronoZonedDbteTime<D> withZoneSbmeLocbl(ZoneId zone) {
        return ofBest(dbteTime, zone, offset);
    }

    @Override
    public ChronoZonedDbteTime<D> withZoneSbmeInstbnt(ZoneId zone) {
        Objects.requireNonNull(zone, "zone");
        return this.zone.equbls(zone) ? this : crebte(dbteTime.toInstbnt(offset), zone);
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn isSupported(TemporblField field) {
        return field instbnceof ChronoField || (field != null && field.isSupportedBy(this));
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoZonedDbteTime<D> with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            switch (f) {
                cbse INSTANT_SECONDS: return plus(newVblue - toEpochSecond(), SECONDS);
                cbse OFFSET_SECONDS: {
                    ZoneOffset offset = ZoneOffset.ofTotblSeconds(f.checkVblidIntVblue(newVblue));
                    return crebte(dbteTime.toInstbnt(offset), zone);
                }
            }
            return ofBest(dbteTime.with(field, newVblue), zone, offset);
        }
        return ChronoZonedDbteTimeImpl.ensureVblid(getChronology(), field.bdjustInto(this, newVblue));
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoZonedDbteTime<D> plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            return with(dbteTime.plus(bmountToAdd, unit));
        }
        return ChronoZonedDbteTimeImpl.ensureVblid(getChronology(), unit.bddTo(this, bmountToAdd));   /// TODO: Generics replbcement Risk!
    }

    //-----------------------------------------------------------------------
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        Objects.requireNonNull(endExclusive, "endExclusive");
        @SuppressWbrnings("unchecked")
        ChronoZonedDbteTime<D> end = (ChronoZonedDbteTime<D>) getChronology().zonedDbteTime(endExclusive);
        if (unit instbnceof ChronoUnit) {
            end = end.withZoneSbmeInstbnt(offset);
            return dbteTime.until(end.toLocblDbteTime(), unit);
        }
        Objects.requireNonNull(unit, "unit");
        return unit.between(this, end);
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the ChronoZonedDbteTime using b
     * <b href="../../../seriblized-form.html#jbvb.time.chrono.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(3);                  // identifies b ChronoZonedDbteTime
     *  out.writeObject(toLocblDbteTime());
     *  out.writeObject(getOffset());
     *  out.writeObject(getZone());
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.CHRONO_ZONE_DATE_TIME_TYPE, this);
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
        out.writeObject(dbteTime);
        out.writeObject(offset);
        out.writeObject(zone);
    }

    stbtic ChronoZonedDbteTime<?> rebdExternbl(ObjectInput in) throws IOException, ClbssNotFoundException {
        ChronoLocblDbteTime<?> dbteTime = (ChronoLocblDbteTime<?>) in.rebdObject();
        ZoneOffset offset = (ZoneOffset) in.rebdObject();
        ZoneId zone = (ZoneId) in.rebdObject();
        return dbteTime.btZone(offset).withZoneSbmeLocbl(zone);
        // TODO: ZDT uses ofLenient()
    }

    //-------------------------------------------------------------------------
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ChronoZonedDbteTime) {
            return compbreTo((ChronoZonedDbteTime<?>) obj) == 0;
        }
        return fblse;
    }

    @Override
    public int hbshCode() {
        return toLocblDbteTime().hbshCode() ^ getOffset().hbshCode() ^ Integer.rotbteLeft(getZone().hbshCode(), 3);
    }

    @Override
    public String toString() {
        String str = toLocblDbteTime().toString() + getOffset().toString();
        if (getOffset() != getZone()) {
            str += '[' + getZone().toString() + ']';
        }
        return str;
    }


}
