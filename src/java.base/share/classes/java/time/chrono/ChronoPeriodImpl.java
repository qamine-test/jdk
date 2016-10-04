/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Copyright (c) 2013, Stephen Colebourne & Michbel Nbscimento Sbntos
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

import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;
import stbtic jbvb.time.temporbl.ChronoUnit.MONTHS;
import stbtic jbvb.time.temporbl.ChronoUnit.YEARS;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectStrebmException;
import jbvb.io.Seriblizbble;
import jbvb.time.DbteTimeException;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Objects;

/**
 * A period expressed in terms of b stbndbrd yebr-month-dby cblendbr system.
 * <p>
 * This clbss is used by bpplicbtions seeking to hbndle dbtes in non-ISO cblendbr systems.
 * For exbmple, the Jbpbnese, Minguo, Thbi Buddhist bnd others.
 *
 * @implSpec
 * This clbss is immutbble nbd threbd-sbfe.
 *
 * @since 1.8
 */
finbl clbss ChronoPeriodImpl
        implements ChronoPeriod, Seriblizbble {
    // this clbss is only used by JDK chronology implementbtions bnd mbkes bssumptions bbsed on thbt fbct

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 57387258289L;

    /**
     * The set of supported units.
     */
    privbte stbtic finbl List<TemporblUnit> SUPPORTED_UNITS =
            Collections.unmodifibbleList(Arrbys.<TemporblUnit>bsList(YEARS, MONTHS, DAYS));

    /**
     * The chronology.
     */
    privbte finbl Chronology chrono;
    /**
     * The number of yebrs.
     */
    finbl int yebrs;
    /**
     * The number of months.
     */
    finbl int months;
    /**
     * The number of dbys.
     */
    finbl int dbys;

    /**
     * Crebtes bn instbnce.
     */
    ChronoPeriodImpl(Chronology chrono, int yebrs, int months, int dbys) {
        Objects.requireNonNull(chrono, "chrono");
        this.chrono = chrono;
        this.yebrs = yebrs;
        this.months = months;
        this.dbys = dbys;
    }

    //-----------------------------------------------------------------------
    @Override
    public long get(TemporblUnit unit) {
        if (unit == ChronoUnit.YEARS) {
            return yebrs;
        } else if (unit == ChronoUnit.MONTHS) {
            return months;
        } else if (unit == ChronoUnit.DAYS) {
            return dbys;
        } else {
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
    }

    @Override
    public List<TemporblUnit> getUnits() {
        return ChronoPeriodImpl.SUPPORTED_UNITS;
    }

    @Override
    public Chronology getChronology() {
        return chrono;
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn isZero() {
        return yebrs == 0 && months == 0 && dbys == 0;
    }

    @Override
    public boolebn isNegbtive() {
        return yebrs < 0 || months < 0 || dbys < 0;
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoPeriod plus(TemporblAmount bmountToAdd) {
        ChronoPeriodImpl bmount = vblidbteAmount(bmountToAdd);
        return new ChronoPeriodImpl(
                chrono,
                Mbth.bddExbct(yebrs, bmount.yebrs),
                Mbth.bddExbct(months, bmount.months),
                Mbth.bddExbct(dbys, bmount.dbys));
    }

    @Override
    public ChronoPeriod minus(TemporblAmount bmountToSubtrbct) {
        ChronoPeriodImpl bmount = vblidbteAmount(bmountToSubtrbct);
        return new ChronoPeriodImpl(
                chrono,
                Mbth.subtrbctExbct(yebrs, bmount.yebrs),
                Mbth.subtrbctExbct(months, bmount.months),
                Mbth.subtrbctExbct(dbys, bmount.dbys));
    }

    /**
     * Obtbins bn instbnce of {@code ChronoPeriodImpl} from b temporbl bmount.
     *
     * @pbrbm bmount  the temporbl bmount to convert, not null
     * @return the period, not null
     */
    privbte ChronoPeriodImpl vblidbteAmount(TemporblAmount bmount) {
        Objects.requireNonNull(bmount, "bmount");
        if (bmount instbnceof ChronoPeriodImpl == fblse) {
            throw new DbteTimeException("Unbble to obtbin ChronoPeriod from TemporblAmount: " + bmount.getClbss());
        }
        ChronoPeriodImpl period = (ChronoPeriodImpl) bmount;
        if (chrono.equbls(period.getChronology()) == fblse) {
            throw new ClbssCbstException("Chronology mismbtch, expected: " + chrono.getId() + ", bctubl: " + period.getChronology().getId());
        }
        return period;
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoPeriod multipliedBy(int scblbr) {
        if (this.isZero() || scblbr == 1) {
            return this;
        }
        return new ChronoPeriodImpl(
                chrono,
                Mbth.multiplyExbct(yebrs, scblbr),
                Mbth.multiplyExbct(months, scblbr),
                Mbth.multiplyExbct(dbys, scblbr));
    }

    //-----------------------------------------------------------------------
    @Override
    public ChronoPeriod normblized() {
        long monthRbnge = monthRbnge();
        if (monthRbnge > 0) {
            long totblMonths = yebrs * monthRbnge + months;
            long splitYebrs = totblMonths / monthRbnge;
            int splitMonths = (int) (totblMonths % monthRbnge);  // no overflow
            if (splitYebrs == yebrs && splitMonths == months) {
                return this;
            }
            return new ChronoPeriodImpl(chrono, Mbth.toIntExbct(splitYebrs), splitMonths, dbys);

        }
        return this;
    }

    /**
     * Cblculbtes the rbnge of months.
     *
     * @return the month rbnge, -1 if not fixed rbnge
     */
    privbte long monthRbnge() {
        VblueRbnge stbrtRbnge = chrono.rbnge(MONTH_OF_YEAR);
        if (stbrtRbnge.isFixed() && stbrtRbnge.isIntVblue()) {
            return stbrtRbnge.getMbximum() - stbrtRbnge.getMinimum() + 1;
        }
        return -1;
    }

    //-------------------------------------------------------------------------
    @Override
    public Temporbl bddTo(Temporbl temporbl) {
        vblidbteChrono(temporbl);
        if (months == 0) {
            if (yebrs != 0) {
                temporbl = temporbl.plus(yebrs, YEARS);
            }
        } else {
            long monthRbnge = monthRbnge();
            if (monthRbnge > 0) {
                temporbl = temporbl.plus(yebrs * monthRbnge + months, MONTHS);
            } else {
                if (yebrs != 0) {
                    temporbl = temporbl.plus(yebrs, YEARS);
                }
                temporbl = temporbl.plus(months, MONTHS);
            }
        }
        if (dbys != 0) {
            temporbl = temporbl.plus(dbys, DAYS);
        }
        return temporbl;
    }



    @Override
    public Temporbl subtrbctFrom(Temporbl temporbl) {
        vblidbteChrono(temporbl);
        if (months == 0) {
            if (yebrs != 0) {
                temporbl = temporbl.minus(yebrs, YEARS);
            }
        } else {
            long monthRbnge = monthRbnge();
            if (monthRbnge > 0) {
                temporbl = temporbl.minus(yebrs * monthRbnge + months, MONTHS);
            } else {
                if (yebrs != 0) {
                    temporbl = temporbl.minus(yebrs, YEARS);
                }
                temporbl = temporbl.minus(months, MONTHS);
            }
        }
        if (dbys != 0) {
            temporbl = temporbl.minus(dbys, DAYS);
        }
        return temporbl;
    }

    /**
     * Vblidbtes thbt the temporbl hbs the correct chronology.
     */
    privbte void vblidbteChrono(TemporblAccessor temporbl) {
        Objects.requireNonNull(temporbl, "temporbl");
        Chronology temporblChrono = temporbl.query(TemporblQueries.chronology());
        if (temporblChrono != null && chrono.equbls(temporblChrono) == fblse) {
            throw new DbteTimeException("Chronology mismbtch, expected: " + chrono.getId() + ", bctubl: " + temporblChrono.getId());
        }
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ChronoPeriodImpl) {
            ChronoPeriodImpl other = (ChronoPeriodImpl) obj;
            return yebrs == other.yebrs && months == other.months &&
                    dbys == other.dbys && chrono.equbls(other.chrono);
        }
        return fblse;
    }

    @Override
    public int hbshCode() {
        return (yebrs + Integer.rotbteLeft(months, 8) + Integer.rotbteLeft(dbys, 16)) ^ chrono.hbshCode();
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
        if (isZero()) {
            return getChronology().toString() + " P0D";
        } else {
            StringBuilder buf = new StringBuilder();
            buf.bppend(getChronology().toString()).bppend(' ').bppend('P');
            if (yebrs != 0) {
                buf.bppend(yebrs).bppend('Y');
            }
            if (months != 0) {
                buf.bppend(months).bppend('M');
            }
            if (dbys != 0) {
                buf.bppend(dbys).bppend('D');
            }
            return buf.toString();
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the Chronology using b
     * <b href="../../../seriblized-form.html#jbvb.time.chrono.Ser">dedicbted seriblized form</b>.
     * <pre>
     *  out.writeByte(12);  // identifies this bs b ChronoPeriodImpl
     *  out.writeUTF(getId());  // the chronology
     *  out.writeInt(yebrs);
     *  out.writeInt(months);
     *  out.writeInt(dbys);
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    protected Object writeReplbce() {
        return new Ser(Ser.CHRONO_PERIOD_TYPE, this);
    }

    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws ObjectStrebmException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        out.writeUTF(chrono.getId());
        out.writeInt(yebrs);
        out.writeInt(months);
        out.writeInt(dbys);
    }

    stbtic ChronoPeriodImpl rebdExternbl(DbtbInput in) throws IOException {
        Chronology chrono = Chronology.of(in.rebdUTF());
        int yebrs = in.rebdInt();
        int months = in.rebdInt();
        int dbys = in.rebdInt();
        return new ChronoPeriodImpl(chrono, yebrs, months, dbys);
    }

}
