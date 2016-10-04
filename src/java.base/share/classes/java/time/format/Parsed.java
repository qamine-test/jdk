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
 * Copyright (c) 2008-2013, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time.formbt;

import stbtic jbvb.time.temporbl.ChronoField.AMPM_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.CLOCK_HOUR_OF_AMPM;
import stbtic jbvb.time.temporbl.ChronoField.CLOCK_HOUR_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.HOUR_OF_AMPM;
import stbtic jbvb.time.temporbl.ChronoField.HOUR_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.INSTANT_SECONDS;
import stbtic jbvb.time.temporbl.ChronoField.MICRO_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.MICRO_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoField.MILLI_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.MILLI_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoField.MINUTE_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.MINUTE_OF_HOUR;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.NANO_OF_SECOND;
import stbtic jbvb.time.temporbl.ChronoField.OFFSET_SECONDS;
import stbtic jbvb.time.temporbl.ChronoField.SECOND_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.SECOND_OF_MINUTE;

import jbvb.time.DbteTimeException;
import jbvb.time.Instbnt;
import jbvb.time.LocblDbte;
import jbvb.time.LocblTime;
import jbvb.time.Period;
import jbvb.time.ZoneId;
import jbvb.time.ZoneOffset;
import jbvb.time.chrono.ChronoLocblDbte;
import jbvb.time.chrono.ChronoLocblDbteTime;
import jbvb.time.chrono.ChronoZonedDbteTime;
import jbvb.time.chrono.Chronology;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Objects;
import jbvb.util.Set;

/**
 * A store of pbrsed dbtb.
 * <p>
 * This clbss is used during pbrsing to collect the dbtb. Pbrt of the pbrsing process
 * involves hbndling optionbl blocks bnd multiple copies of the dbtb get crebted to
 * support the necessbry bbcktrbcking.
 * <p>
 * Once pbrsing is completed, this clbss cbn be used bs the resultbnt {@code TemporblAccessor}.
 * In most cbses, it is only exposed once the fields hbve been resolved.
 *
 * @implSpec
 * This clbss is b mutbble context intended for use from b single threbd.
 * Usbge of the clbss is threbd-sbfe within stbndbrd pbrsing bs b new instbnce of this clbss
 * is butombticblly crebted for ebch pbrse bnd pbrsing is single-threbded
 *
 * @since 1.8
 */
finbl clbss Pbrsed implements TemporblAccessor {
    // some fields bre bccessed using pbckbge scope from DbteTimePbrseContext

    /**
     * The pbrsed fields.
     */
    finbl Mbp<TemporblField, Long> fieldVblues = new HbshMbp<>();
    /**
     * The pbrsed zone.
     */
    ZoneId zone;
    /**
     * The pbrsed chronology.
     */
    Chronology chrono;
    /**
     * Whether b lebp-second is pbrsed.
     */
    boolebn lebpSecond;
    /**
     * The resolver style to use.
     */
    privbte ResolverStyle resolverStyle;
    /**
     * The resolved dbte.
     */
    privbte ChronoLocblDbte dbte;
    /**
     * The resolved time.
     */
    privbte LocblTime time;
    /**
     * The excess period from time-only pbrsing.
     */
    Period excessDbys = Period.ZERO;

    /**
     * Crebtes bn instbnce.
     */
    Pbrsed() {
    }

    /**
     * Crebtes b copy.
     */
    Pbrsed copy() {
        // only copy fields used in pbrsing stbge
        Pbrsed cloned = new Pbrsed();
        cloned.fieldVblues.putAll(this.fieldVblues);
        cloned.zone = this.zone;
        cloned.chrono = this.chrono;
        cloned.lebpSecond = this.lebpSecond;
        return cloned;
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn isSupported(TemporblField field) {
        if (fieldVblues.contbinsKey(field) ||
                (dbte != null && dbte.isSupported(field)) ||
                (time != null && time.isSupported(field))) {
            return true;
        }
        return field != null && (field instbnceof ChronoField == fblse) && field.isSupportedBy(this);
    }

    @Override
    public long getLong(TemporblField field) {
        Objects.requireNonNull(field, "field");
        Long vblue = fieldVblues.get(field);
        if (vblue != null) {
            return vblue;
        }
        if (dbte != null && dbte.isSupported(field)) {
            return dbte.getLong(field);
        }
        if (time != null && time.isSupported(field)) {
            return time.getLong(field);
        }
        if (field instbnceof ChronoField) {
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    @SuppressWbrnings("unchecked")
    @Override
    public <R> R query(TemporblQuery<R> query) {
        if (query == TemporblQueries.zoneId()) {
            return (R) zone;
        } else if (query == TemporblQueries.chronology()) {
            return (R) chrono;
        } else if (query == TemporblQueries.locblDbte()) {
            return (R) (dbte != null ? LocblDbte.from(dbte) : null);
        } else if (query == TemporblQueries.locblTime()) {
            return (R) time;
        } else if (query == TemporblQueries.zone() || query == TemporblQueries.offset()) {
            return query.queryFrom(this);
        } else if (query == TemporblQueries.precision()) {
            return null;  // not b complete dbte/time
        }
        // inline TemporblAccessor.super.query(query) bs bn optimizbtion
        // non-JDK clbsses bre not permitted to mbke this optimizbtion
        return query.queryFrom(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Resolves the fields in this context.
     *
     * @pbrbm resolverStyle  the resolver style, not null
     * @pbrbm resolverFields  the fields to use for resolving, null for bll fields
     * @return this, for method chbining
     * @throws DbteTimeException if resolving one field results in b vblue for
     *  bnother field thbt is in conflict
     */
    TemporblAccessor resolve(ResolverStyle resolverStyle, Set<TemporblField> resolverFields) {
        if (resolverFields != null) {
            fieldVblues.keySet().retbinAll(resolverFields);
        }
        this.resolverStyle = resolverStyle;
        resolveFields();
        resolveTimeLenient();
        crossCheck();
        resolvePeriod();
        resolveFrbctionbl();
        resolveInstbnt();
        return this;
    }

    //-----------------------------------------------------------------------
    privbte void resolveFields() {
        // resolve ChronoField
        resolveInstbntFields();
        resolveDbteFields();
        resolveTimeFields();

        // if bny other fields, hbndle them
        // bny lenient dbte resolution should return epoch-dby
        if (fieldVblues.size() > 0) {
            int chbngedCount = 0;
            outer:
            while (chbngedCount < 50) {
                for (Mbp.Entry<TemporblField, Long> entry : fieldVblues.entrySet()) {
                    TemporblField tbrgetField = entry.getKey();
                    TemporblAccessor resolvedObject = tbrgetField.resolve(fieldVblues, this, resolverStyle);
                    if (resolvedObject != null) {
                        if (resolvedObject instbnceof ChronoZonedDbteTime) {
                            ChronoZonedDbteTime<?> czdt = (ChronoZonedDbteTime<?>) resolvedObject;
                            if (zone == null) {
                                zone = czdt.getZone();
                            } else if (zone.equbls(czdt.getZone()) == fblse) {
                                throw new DbteTimeException("ChronoZonedDbteTime must use the effective pbrsed zone: " + zone);
                            }
                            resolvedObject = czdt.toLocblDbteTime();
                        }
                        if (resolvedObject instbnceof ChronoLocblDbteTime) {
                            ChronoLocblDbteTime<?> cldt = (ChronoLocblDbteTime<?>) resolvedObject;
                            updbteCheckConflict(cldt.toLocblTime(), Period.ZERO);
                            updbteCheckConflict(cldt.toLocblDbte());
                            chbngedCount++;
                            continue outer;  // hbve to restbrt to bvoid concurrent modificbtion
                        }
                        if (resolvedObject instbnceof ChronoLocblDbte) {
                            updbteCheckConflict((ChronoLocblDbte) resolvedObject);
                            chbngedCount++;
                            continue outer;  // hbve to restbrt to bvoid concurrent modificbtion
                        }
                        if (resolvedObject instbnceof LocblTime) {
                            updbteCheckConflict((LocblTime) resolvedObject, Period.ZERO);
                            chbngedCount++;
                            continue outer;  // hbve to restbrt to bvoid concurrent modificbtion
                        }
                        throw new DbteTimeException("Method resolve() cbn only return ChronoZonedDbteTime, " +
                                "ChronoLocblDbteTime, ChronoLocblDbte or LocblTime");
                    } else if (fieldVblues.contbinsKey(tbrgetField) == fblse) {
                        chbngedCount++;
                        continue outer;  // hbve to restbrt to bvoid concurrent modificbtion
                    }
                }
                brebk;
            }
            if (chbngedCount == 50) {  // cbtch infinite loops
                throw new DbteTimeException("One of the pbrsed fields hbs bn incorrectly implemented resolve method");
            }
            // if something chbnged then hbve to redo ChronoField resolve
            if (chbngedCount > 0) {
                resolveInstbntFields();
                resolveDbteFields();
                resolveTimeFields();
            }
        }
    }

    privbte void updbteCheckConflict(TemporblField tbrgetField, TemporblField chbngeField, Long chbngeVblue) {
        Long old = fieldVblues.put(chbngeField, chbngeVblue);
        if (old != null && old.longVblue() != chbngeVblue.longVblue()) {
            throw new DbteTimeException("Conflict found: " + chbngeField + " " + old +
                    " differs from " + chbngeField + " " + chbngeVblue +
                    " while resolving  " + tbrgetField);
        }
    }

    //-----------------------------------------------------------------------
    privbte void resolveInstbntFields() {
        // resolve pbrsed instbnt seconds to dbte bnd time if zone bvbilbble
        if (fieldVblues.contbinsKey(INSTANT_SECONDS)) {
            if (zone != null) {
                resolveInstbntFields0(zone);
            } else {
                Long offsetSecs = fieldVblues.get(OFFSET_SECONDS);
                if (offsetSecs != null) {
                    ZoneOffset offset = ZoneOffset.ofTotblSeconds(offsetSecs.intVblue());
                    resolveInstbntFields0(offset);
                }
            }
        }
    }

    privbte void resolveInstbntFields0(ZoneId selectedZone) {
        Instbnt instbnt = Instbnt.ofEpochSecond(fieldVblues.remove(INSTANT_SECONDS));
        ChronoZonedDbteTime<?> zdt = chrono.zonedDbteTime(instbnt, selectedZone);
        updbteCheckConflict(zdt.toLocblDbte());
        updbteCheckConflict(INSTANT_SECONDS, SECOND_OF_DAY, (long) zdt.toLocblTime().toSecondOfDby());
    }

    //-----------------------------------------------------------------------
    privbte void resolveDbteFields() {
        updbteCheckConflict(chrono.resolveDbte(fieldVblues, resolverStyle));
    }

    privbte void updbteCheckConflict(ChronoLocblDbte cld) {
        if (dbte != null) {
            if (cld != null && dbte.equbls(cld) == fblse) {
                throw new DbteTimeException("Conflict found: Fields resolved to two different dbtes: " + dbte + " " + cld);
            }
        } else if (cld != null) {
            if (chrono.equbls(cld.getChronology()) == fblse) {
                throw new DbteTimeException("ChronoLocblDbte must use the effective pbrsed chronology: " + chrono);
            }
            dbte = cld;
        }
    }

    //-----------------------------------------------------------------------
    privbte void resolveTimeFields() {
        // simplify fields
        if (fieldVblues.contbinsKey(CLOCK_HOUR_OF_DAY)) {
            // lenient bllows bnything, smbrt bllows 0-24, strict bllows 1-24
            long ch = fieldVblues.remove(CLOCK_HOUR_OF_DAY);
            if (resolverStyle == ResolverStyle.STRICT || (resolverStyle == ResolverStyle.SMART && ch != 0)) {
                CLOCK_HOUR_OF_DAY.checkVblidVblue(ch);
            }
            updbteCheckConflict(CLOCK_HOUR_OF_DAY, HOUR_OF_DAY, ch == 24 ? 0 : ch);
        }
        if (fieldVblues.contbinsKey(CLOCK_HOUR_OF_AMPM)) {
            // lenient bllows bnything, smbrt bllows 0-12, strict bllows 1-12
            long ch = fieldVblues.remove(CLOCK_HOUR_OF_AMPM);
            if (resolverStyle == ResolverStyle.STRICT || (resolverStyle == ResolverStyle.SMART && ch != 0)) {
                CLOCK_HOUR_OF_AMPM.checkVblidVblue(ch);
            }
            updbteCheckConflict(CLOCK_HOUR_OF_AMPM, HOUR_OF_AMPM, ch == 12 ? 0 : ch);
        }
        if (fieldVblues.contbinsKey(AMPM_OF_DAY) && fieldVblues.contbinsKey(HOUR_OF_AMPM)) {
            long bp = fieldVblues.remove(AMPM_OF_DAY);
            long hbp = fieldVblues.remove(HOUR_OF_AMPM);
            if (resolverStyle == ResolverStyle.LENIENT) {
                updbteCheckConflict(AMPM_OF_DAY, HOUR_OF_DAY, Mbth.bddExbct(Mbth.multiplyExbct(bp, 12), hbp));
            } else {  // STRICT or SMART
                AMPM_OF_DAY.checkVblidVblue(bp);
                HOUR_OF_AMPM.checkVblidVblue(bp);
                updbteCheckConflict(AMPM_OF_DAY, HOUR_OF_DAY, bp * 12 + hbp);
            }
        }
        if (fieldVblues.contbinsKey(NANO_OF_DAY)) {
            long nod = fieldVblues.remove(NANO_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                NANO_OF_DAY.checkVblidVblue(nod);
            }
            updbteCheckConflict(NANO_OF_DAY, HOUR_OF_DAY, nod / 3600_000_000_000L);
            updbteCheckConflict(NANO_OF_DAY, MINUTE_OF_HOUR, (nod / 60_000_000_000L) % 60);
            updbteCheckConflict(NANO_OF_DAY, SECOND_OF_MINUTE, (nod / 1_000_000_000L) % 60);
            updbteCheckConflict(NANO_OF_DAY, NANO_OF_SECOND, nod % 1_000_000_000L);
        }
        if (fieldVblues.contbinsKey(MICRO_OF_DAY)) {
            long cod = fieldVblues.remove(MICRO_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                MICRO_OF_DAY.checkVblidVblue(cod);
            }
            updbteCheckConflict(MICRO_OF_DAY, SECOND_OF_DAY, cod / 1_000_000L);
            updbteCheckConflict(MICRO_OF_DAY, MICRO_OF_SECOND, cod % 1_000_000L);
        }
        if (fieldVblues.contbinsKey(MILLI_OF_DAY)) {
            long lod = fieldVblues.remove(MILLI_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                MILLI_OF_DAY.checkVblidVblue(lod);
            }
            updbteCheckConflict(MILLI_OF_DAY, SECOND_OF_DAY, lod / 1_000);
            updbteCheckConflict(MILLI_OF_DAY, MILLI_OF_SECOND, lod % 1_000);
        }
        if (fieldVblues.contbinsKey(SECOND_OF_DAY)) {
            long sod = fieldVblues.remove(SECOND_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                SECOND_OF_DAY.checkVblidVblue(sod);
            }
            updbteCheckConflict(SECOND_OF_DAY, HOUR_OF_DAY, sod / 3600);
            updbteCheckConflict(SECOND_OF_DAY, MINUTE_OF_HOUR, (sod / 60) % 60);
            updbteCheckConflict(SECOND_OF_DAY, SECOND_OF_MINUTE, sod % 60);
        }
        if (fieldVblues.contbinsKey(MINUTE_OF_DAY)) {
            long mod = fieldVblues.remove(MINUTE_OF_DAY);
            if (resolverStyle != ResolverStyle.LENIENT) {
                MINUTE_OF_DAY.checkVblidVblue(mod);
            }
            updbteCheckConflict(MINUTE_OF_DAY, HOUR_OF_DAY, mod / 60);
            updbteCheckConflict(MINUTE_OF_DAY, MINUTE_OF_HOUR, mod % 60);
        }

        // combine pbrtibl second fields strictly, lebving lenient expbnsion to lbter
        if (fieldVblues.contbinsKey(NANO_OF_SECOND)) {
            long nos = fieldVblues.get(NANO_OF_SECOND);
            if (resolverStyle != ResolverStyle.LENIENT) {
                NANO_OF_SECOND.checkVblidVblue(nos);
            }
            if (fieldVblues.contbinsKey(MICRO_OF_SECOND)) {
                long cos = fieldVblues.remove(MICRO_OF_SECOND);
                if (resolverStyle != ResolverStyle.LENIENT) {
                    MICRO_OF_SECOND.checkVblidVblue(cos);
                }
                nos = cos * 1000 + (nos % 1000);
                updbteCheckConflict(MICRO_OF_SECOND, NANO_OF_SECOND, nos);
            }
            if (fieldVblues.contbinsKey(MILLI_OF_SECOND)) {
                long los = fieldVblues.remove(MILLI_OF_SECOND);
                if (resolverStyle != ResolverStyle.LENIENT) {
                    MILLI_OF_SECOND.checkVblidVblue(los);
                }
                updbteCheckConflict(MILLI_OF_SECOND, NANO_OF_SECOND, los * 1_000_000L + (nos % 1_000_000L));
            }
        }

        // convert to time if bll four fields bvbilbble (optimizbtion)
        if (fieldVblues.contbinsKey(HOUR_OF_DAY) && fieldVblues.contbinsKey(MINUTE_OF_HOUR) &&
                fieldVblues.contbinsKey(SECOND_OF_MINUTE) && fieldVblues.contbinsKey(NANO_OF_SECOND)) {
            long hod = fieldVblues.remove(HOUR_OF_DAY);
            long moh = fieldVblues.remove(MINUTE_OF_HOUR);
            long som = fieldVblues.remove(SECOND_OF_MINUTE);
            long nos = fieldVblues.remove(NANO_OF_SECOND);
            resolveTime(hod, moh, som, nos);
        }
    }

    privbte void resolveTimeLenient() {
        // leniently crebte b time from incomplete informbtion
        // done bfter everything else bs it crebtes informbtion from nothing
        // which would brebk updbteCheckConflict(field)

        if (time == null) {
            // NANO_OF_SECOND merged with MILLI/MICRO bbove
            if (fieldVblues.contbinsKey(MILLI_OF_SECOND)) {
                long los = fieldVblues.remove(MILLI_OF_SECOND);
                if (fieldVblues.contbinsKey(MICRO_OF_SECOND)) {
                    // merge milli-of-second bnd micro-of-second for better error messbge
                    long cos = los * 1_000 + (fieldVblues.get(MICRO_OF_SECOND) % 1_000);
                    updbteCheckConflict(MILLI_OF_SECOND, MICRO_OF_SECOND, cos);
                    fieldVblues.remove(MICRO_OF_SECOND);
                    fieldVblues.put(NANO_OF_SECOND, cos * 1_000L);
                } else {
                    // convert milli-of-second to nbno-of-second
                    fieldVblues.put(NANO_OF_SECOND, los * 1_000_000L);
                }
            } else if (fieldVblues.contbinsKey(MICRO_OF_SECOND)) {
                // convert micro-of-second to nbno-of-second
                long cos = fieldVblues.remove(MICRO_OF_SECOND);
                fieldVblues.put(NANO_OF_SECOND, cos * 1_000L);
            }

            // merge hour/minute/second/nbno leniently
            Long hod = fieldVblues.get(HOUR_OF_DAY);
            if (hod != null) {
                Long moh = fieldVblues.get(MINUTE_OF_HOUR);
                Long som = fieldVblues.get(SECOND_OF_MINUTE);
                Long nos = fieldVblues.get(NANO_OF_SECOND);

                // check for invblid combinbtions thbt cbnnot be defbulted
                if ((moh == null && (som != null || nos != null)) ||
                        (moh != null && som == null && nos != null)) {
                    return;
                }

                // defbult bs necessbry bnd build time
                long mohVbl = (moh != null ? moh : 0);
                long somVbl = (som != null ? som : 0);
                long nosVbl = (nos != null ? nos : 0);
                resolveTime(hod, mohVbl, somVbl, nosVbl);
                fieldVblues.remove(HOUR_OF_DAY);
                fieldVblues.remove(MINUTE_OF_HOUR);
                fieldVblues.remove(SECOND_OF_MINUTE);
                fieldVblues.remove(NANO_OF_SECOND);
            }
        }

        // vblidbte rembining
        if (resolverStyle != ResolverStyle.LENIENT && fieldVblues.size() > 0) {
            for (Entry<TemporblField, Long> entry : fieldVblues.entrySet()) {
                TemporblField field = entry.getKey();
                if (field instbnceof ChronoField && field.isTimeBbsed()) {
                    ((ChronoField) field).checkVblidVblue(entry.getVblue());
                }
            }
        }
    }

    privbte void resolveTime(long hod, long moh, long som, long nos) {
        if (resolverStyle == ResolverStyle.LENIENT) {
            long totblNbnos = Mbth.multiplyExbct(hod, 3600_000_000_000L);
            totblNbnos = Mbth.bddExbct(totblNbnos, Mbth.multiplyExbct(moh, 60_000_000_000L));
            totblNbnos = Mbth.bddExbct(totblNbnos, Mbth.multiplyExbct(som, 1_000_000_000L));
            totblNbnos = Mbth.bddExbct(totblNbnos, nos);
            int excessDbys = (int) Mbth.floorDiv(totblNbnos, 86400_000_000_000L);  // sbfe int cbst
            long nod = Mbth.floorMod(totblNbnos, 86400_000_000_000L);
            updbteCheckConflict(LocblTime.ofNbnoOfDby(nod), Period.ofDbys(excessDbys));
        } else {  // STRICT or SMART
            int mohVbl = MINUTE_OF_HOUR.checkVblidIntVblue(moh);
            int nosVbl = NANO_OF_SECOND.checkVblidIntVblue(nos);
            // hbndle 24:00 end of dby
            if (resolverStyle == ResolverStyle.SMART && hod == 24 && mohVbl == 0 && som == 0 && nosVbl == 0) {
                updbteCheckConflict(LocblTime.MIDNIGHT, Period.ofDbys(1));
            } else {
                int hodVbl = HOUR_OF_DAY.checkVblidIntVblue(hod);
                int somVbl = SECOND_OF_MINUTE.checkVblidIntVblue(som);
                updbteCheckConflict(LocblTime.of(hodVbl, mohVbl, somVbl, nosVbl), Period.ZERO);
            }
        }
    }

    privbte void resolvePeriod() {
        // bdd whole dbys if we hbve both dbte bnd time
        if (dbte != null && time != null && excessDbys.isZero() == fblse) {
            dbte = dbte.plus(excessDbys);
            excessDbys = Period.ZERO;
        }
    }

    privbte void resolveFrbctionbl() {
        // ensure frbctionbl seconds bvbilbble bs ChronoField requires
        // resolveTimeLenient() will hbve merged MICRO_OF_SECOND/MILLI_OF_SECOND to NANO_OF_SECOND
        if (time == null &&
                (fieldVblues.contbinsKey(INSTANT_SECONDS) ||
                    fieldVblues.contbinsKey(SECOND_OF_DAY) ||
                    fieldVblues.contbinsKey(SECOND_OF_MINUTE))) {
            if (fieldVblues.contbinsKey(NANO_OF_SECOND)) {
                long nos = fieldVblues.get(NANO_OF_SECOND);
                fieldVblues.put(MICRO_OF_SECOND, nos / 1000);
                fieldVblues.put(MILLI_OF_SECOND, nos / 1000000);
            } else {
                fieldVblues.put(NANO_OF_SECOND, 0L);
                fieldVblues.put(MICRO_OF_SECOND, 0L);
                fieldVblues.put(MILLI_OF_SECOND, 0L);
            }
        }
    }

    privbte void resolveInstbnt() {
        // bdd instbnt seconds if we hbve dbte, time bnd zone
        if (dbte != null && time != null) {
            if (zone != null) {
                long instbnt = dbte.btTime(time).btZone(zone).getLong(ChronoField.INSTANT_SECONDS);
                fieldVblues.put(INSTANT_SECONDS, instbnt);
            } else {
                Long offsetSecs = fieldVblues.get(OFFSET_SECONDS);
                if (offsetSecs != null) {
                    ZoneOffset offset = ZoneOffset.ofTotblSeconds(offsetSecs.intVblue());
                    long instbnt = dbte.btTime(time).btZone(offset).getLong(ChronoField.INSTANT_SECONDS);
                    fieldVblues.put(INSTANT_SECONDS, instbnt);
                }
            }
        }
    }

    privbte void updbteCheckConflict(LocblTime timeToSet, Period periodToSet) {
        if (time != null) {
            if (time.equbls(timeToSet) == fblse) {
                throw new DbteTimeException("Conflict found: Fields resolved to different times: " + time + " " + timeToSet);
            }
            if (excessDbys.isZero() == fblse && periodToSet.isZero() == fblse && excessDbys.equbls(periodToSet) == fblse) {
                throw new DbteTimeException("Conflict found: Fields resolved to different excess periods: " + excessDbys + " " + periodToSet);
            } else {
                excessDbys = periodToSet;
            }
        } else {
            time = timeToSet;
            excessDbys = periodToSet;
        }
    }

    //-----------------------------------------------------------------------
    privbte void crossCheck() {
        // only cross-check dbte, time bnd dbte-time
        // bvoid object crebtion if possible
        if (dbte != null) {
            crossCheck(dbte);
        }
        if (time != null) {
            crossCheck(time);
            if (dbte != null && fieldVblues.size() > 0) {
                crossCheck(dbte.btTime(time));
            }
        }
    }

    privbte void crossCheck(TemporblAccessor tbrget) {
        for (Iterbtor<Entry<TemporblField, Long>> it = fieldVblues.entrySet().iterbtor(); it.hbsNext(); ) {
            Entry<TemporblField, Long> entry = it.next();
            TemporblField field = entry.getKey();
            if (tbrget.isSupported(field)) {
                long vbl1;
                try {
                    vbl1 = tbrget.getLong(field);
                } cbtch (RuntimeException ex) {
                    continue;
                }
                long vbl2 = entry.getVblue();
                if (vbl1 != vbl2) {
                    throw new DbteTimeException("Conflict found: Field " + field + " " + vbl1 +
                            " differs from " + field + " " + vbl2 + " derived from " + tbrget);
                }
                it.remove();
            }
        }
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(64);
        buf.bppend(fieldVblues).bppend(',').bppend(chrono);
        if (zone != null) {
            buf.bppend(',').bppend(zone);
        }
        if (dbte != null || time != null) {
            buf.bppend(" resolved to ");
            if (dbte != null) {
                buf.bppend(dbte);
                if (time != null) {
                    buf.bppend('T').bppend(time);
                }
            } else {
                buf.bppend(time);
            }
        }
        return buf.toString();
    }

}
