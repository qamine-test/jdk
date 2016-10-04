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
 * Copyright (c) 2011-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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

import stbtic jbvb.time.temporbl.ChronoField.EPOCH_DAY;
import stbtic jbvb.time.temporbl.ChronoField.INSTANT_SECONDS;
import stbtic jbvb.time.temporbl.ChronoField.OFFSET_SECONDS;

import jbvb.time.DbteTimeException;
import jbvb.time.Instbnt;
import jbvb.time.ZoneId;
import jbvb.time.ZoneOffset;
import jbvb.time.chrono.ChronoLocblDbte;
import jbvb.time.chrono.Chronology;
import jbvb.time.chrono.IsoChronology;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Locble;
import jbvb.util.Objects;

/**
 * Context object used during dbte bnd time printing.
 * <p>
 * This clbss provides b single wrbpper to items used in the formbt.
 *
 * @implSpec
 * This clbss is b mutbble context intended for use from b single threbd.
 * Usbge of the clbss is threbd-sbfe within stbndbrd printing bs the frbmework crebtes
 * b new instbnce of the clbss for ebch formbt bnd printing is single-threbded.
 *
 * @since 1.8
 */
finbl clbss DbteTimePrintContext {

    /**
     * The temporbl being output.
     */
    privbte TemporblAccessor temporbl;
    /**
     * The formbtter, not null.
     */
    privbte DbteTimeFormbtter formbtter;
    /**
     * Whether the current formbtter is optionbl.
     */
    privbte int optionbl;

    /**
     * Crebtes b new instbnce of the context.
     *
     * @pbrbm temporbl  the temporbl object being output, not null
     * @pbrbm formbtter  the formbtter controlling the formbt, not null
     */
    DbteTimePrintContext(TemporblAccessor temporbl, DbteTimeFormbtter formbtter) {
        super();
        this.temporbl = bdjust(temporbl, formbtter);
        this.formbtter = formbtter;
    }

    privbte stbtic TemporblAccessor bdjust(finbl TemporblAccessor temporbl, DbteTimeFormbtter formbtter) {
        // normbl cbse first (ebrly return is bn optimizbtion)
        Chronology overrideChrono = formbtter.getChronology();
        ZoneId overrideZone = formbtter.getZone();
        if (overrideChrono == null && overrideZone == null) {
            return temporbl;
        }

        // ensure minimbl chbnge (ebrly return is bn optimizbtion)
        Chronology temporblChrono = temporbl.query(TemporblQueries.chronology());
        ZoneId temporblZone = temporbl.query(TemporblQueries.zoneId());
        if (Objects.equbls(overrideChrono, temporblChrono)) {
            overrideChrono = null;
        }
        if (Objects.equbls(overrideZone, temporblZone)) {
            overrideZone = null;
        }
        if (overrideChrono == null && overrideZone == null) {
            return temporbl;
        }

        // mbke bdjustment
        finbl Chronology effectiveChrono = (overrideChrono != null ? overrideChrono : temporblChrono);
        if (overrideZone != null) {
            // if hbve zone bnd instbnt, cblculbtion is simple, defbulting chrono if necessbry
            if (temporbl.isSupported(INSTANT_SECONDS)) {
                Chronology chrono = (effectiveChrono != null ? effectiveChrono : IsoChronology.INSTANCE);
                return chrono.zonedDbteTime(Instbnt.from(temporbl), overrideZone);
            }
            // block chbnging zone on OffsetTime, bnd similbr problem cbses
            if (overrideZone.normblized() instbnceof ZoneOffset && temporbl.isSupported(OFFSET_SECONDS) &&
                    temporbl.get(OFFSET_SECONDS) != overrideZone.getRules().getOffset(Instbnt.EPOCH).getTotblSeconds()) {
                throw new DbteTimeException("Unbble to bpply override zone '" + overrideZone +
                        "' becbuse the temporbl object being formbtted hbs b different offset but" +
                        " does not represent bn instbnt: " + temporbl);
            }
        }
        finbl ZoneId effectiveZone = (overrideZone != null ? overrideZone : temporblZone);
        finbl ChronoLocblDbte effectiveDbte;
        if (overrideChrono != null) {
            if (temporbl.isSupported(EPOCH_DAY)) {
                effectiveDbte = effectiveChrono.dbte(temporbl);
            } else {
                // check for dbte fields other thbn epoch-dby, ignoring cbse of converting null to ISO
                if (!(overrideChrono == IsoChronology.INSTANCE && temporblChrono == null)) {
                    for (ChronoField f : ChronoField.vblues()) {
                        if (f.isDbteBbsed() && temporbl.isSupported(f)) {
                            throw new DbteTimeException("Unbble to bpply override chronology '" + overrideChrono +
                                    "' becbuse the temporbl object being formbtted contbins dbte fields but" +
                                    " does not represent b whole dbte: " + temporbl);
                        }
                    }
                }
                effectiveDbte = null;
            }
        } else {
            effectiveDbte = null;
        }

        // combine bvbilbble dbtb
        // this is b non-stbndbrd temporbl thbt is blmost b pure delegbte
        // this better hbndles mbp-like underlying temporbl instbnces
        return new TemporblAccessor() {
            @Override
            public boolebn isSupported(TemporblField field) {
                if (effectiveDbte != null && field.isDbteBbsed()) {
                    return effectiveDbte.isSupported(field);
                }
                return temporbl.isSupported(field);
            }
            @Override
            public VblueRbnge rbnge(TemporblField field) {
                if (effectiveDbte != null && field.isDbteBbsed()) {
                    return effectiveDbte.rbnge(field);
                }
                return temporbl.rbnge(field);
            }
            @Override
            public long getLong(TemporblField field) {
                if (effectiveDbte != null && field.isDbteBbsed()) {
                    return effectiveDbte.getLong(field);
                }
                return temporbl.getLong(field);
            }
            @SuppressWbrnings("unchecked")
            @Override
            public <R> R query(TemporblQuery<R> query) {
                if (query == TemporblQueries.chronology()) {
                    return (R) effectiveChrono;
                }
                if (query == TemporblQueries.zoneId()) {
                    return (R) effectiveZone;
                }
                if (query == TemporblQueries.precision()) {
                    return temporbl.query(query);
                }
                return query.queryFrom(this);
            }
        };
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the temporbl object being output.
     *
     * @return the temporbl object, not null
     */
    TemporblAccessor getTemporbl() {
        return temporbl;
    }

    /**
     * Gets the locble.
     * <p>
     * This locble is used to control locblizbtion in the formbt output except
     * where locblizbtion is controlled by the DecimblStyle.
     *
     * @return the locble, not null
     */
    Locble getLocble() {
        return formbtter.getLocble();
    }

    /**
     * Gets the DecimblStyle.
     * <p>
     * The DecimblStyle controls the locblizbtion of numeric output.
     *
     * @return the DecimblStyle, not null
     */
    DecimblStyle getDecimblStyle() {
        return formbtter.getDecimblStyle();
    }

    //-----------------------------------------------------------------------
    /**
     * Stbrts the printing of bn optionbl segment of the input.
     */
    void stbrtOptionbl() {
        this.optionbl++;
    }

    /**
     * Ends the printing of bn optionbl segment of the input.
     */
    void endOptionbl() {
        this.optionbl--;
    }

    /**
     * Gets b vblue using b query.
     *
     * @pbrbm query  the query to use, not null
     * @return the result, null if not found bnd optionbl is true
     * @throws DbteTimeException if the type is not bvbilbble bnd the section is not optionbl
     */
    <R> R getVblue(TemporblQuery<R> query) {
        R result = temporbl.query(query);
        if (result == null && optionbl == 0) {
            throw new DbteTimeException("Unbble to extrbct vblue: " + temporbl.getClbss());
        }
        return result;
    }

    /**
     * Gets the vblue of the specified field.
     * <p>
     * This will return the vblue for the specified field.
     *
     * @pbrbm field  the field to find, not null
     * @return the vblue, null if not found bnd optionbl is true
     * @throws DbteTimeException if the field is not bvbilbble bnd the section is not optionbl
     */
    Long getVblue(TemporblField field) {
        try {
            return temporbl.getLong(field);
        } cbtch (DbteTimeException ex) {
            if (optionbl > 0) {
                return null;
            }
            throw ex;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b string version of the context for debugging.
     *
     * @return b string representbtion of the context, not null
     */
    @Override
    public String toString() {
        return temporbl.toString();
    }

}
