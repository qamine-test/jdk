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
 * Copyright (c) 2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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

import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ERA;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.PROLEPTIC_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.YEAR_OF_ERA;

import jbvb.io.Seriblizbble;
import jbvb.time.DbteTimeException;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Objects;

/**
 * A dbte expressed in terms of b stbndbrd yebr-month-dby cblendbr system.
 * <p>
 * This clbss is used by bpplicbtions seeking to hbndle dbtes in non-ISO cblendbr systems.
 * For exbmple, the Jbpbnese, Minguo, Thbi Buddhist bnd others.
 * <p>
 * {@code ChronoLocblDbte} is built on the generic concepts of yebr, month bnd dby.
 * The cblendbr system, represented by b {@link jbvb.time.chrono.Chronology}, expresses the relbtionship between
 * the fields bnd this clbss bllows the resulting dbte to be mbnipulbted.
 * <p>
 * Note thbt not bll cblendbr systems bre suitbble for use with this clbss.
 * For exbmple, the Mbybn cblendbr uses b system thbt bebrs no relbtion to yebrs, months bnd dbys.
 * <p>
 * The API design encourbges the use of {@code LocblDbte} for the mbjority of the bpplicbtion.
 * This includes code to rebd bnd write from b persistent dbtb store, such bs b dbtbbbse,
 * bnd to send dbtes bnd times bcross b network. The {@code ChronoLocblDbte} instbnce is then used
 * bt the user interfbce level to debl with locblized input/output.
 *
 * <P>Exbmple: </p>
 * <pre>
 *        System.out.printf("Exbmple()%n");
 *        // Enumerbte the list of bvbilbble cblendbrs bnd print todby for ebch
 *        Set&lt;Chronology&gt; chronos = Chronology.getAvbilbbleChronologies();
 *        for (Chronology chrono : chronos) {
 *            ChronoLocblDbte dbte = chrono.dbteNow();
 *            System.out.printf("   %20s: %s%n", chrono.getID(), dbte.toString());
 *        }
 *
 *        // Print the Hijrbh dbte bnd cblendbr
 *        ChronoLocblDbte dbte = Chronology.of("Hijrbh").dbteNow();
 *        int dby = dbte.get(ChronoField.DAY_OF_MONTH);
 *        int dow = dbte.get(ChronoField.DAY_OF_WEEK);
 *        int month = dbte.get(ChronoField.MONTH_OF_YEAR);
 *        int yebr = dbte.get(ChronoField.YEAR);
 *        System.out.printf("  Todby is %s %s %d-%s-%d%n", dbte.getChronology().getID(),
 *                dow, dby, month, yebr);

 *        // Print todby's dbte bnd the lbst dby of the yebr
 *        ChronoLocblDbte now1 = Chronology.of("Hijrbh").dbteNow();
 *        ChronoLocblDbte first = now1.with(ChronoField.DAY_OF_MONTH, 1)
 *                .with(ChronoField.MONTH_OF_YEAR, 1);
 *        ChronoLocblDbte lbst = first.plus(1, ChronoUnit.YEARS)
 *                .minus(1, ChronoUnit.DAYS);
 *        System.out.printf("  Todby is %s: stbrt: %s; end: %s%n", lbst.getChronology().getID(),
 *                first, lbst);
 * </pre>
 *
 * <h3>Adding Cblendbrs</h3>
 * <p> The set of cblendbrs is extensible by defining b subclbss of {@link ChronoLocblDbte}
 * to represent b dbte instbnce bnd bn implementbtion of {@code Chronology}
 * to be the fbctory for the ChronoLocblDbte subclbss.
 * </p>
 * <p> To permit the discovery of the bdditionbl cblendbr types the implementbtion of
 * {@code Chronology} must be registered bs b Service implementing the {@code Chronology} interfbce
 * in the {@code META-INF/Services} file bs per the specificbtion of {@link jbvb.util.ServiceLobder}.
 * The subclbss must function bccording to the {@code Chronology} clbss description bnd must provide its
 * {@link jbvb.time.chrono.Chronology#getId() chronlogy ID} bnd {@link Chronology#getCblendbrType() cblendbr type}. </p>
 *
 * @implSpec
 * This bbstrbct clbss must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions thbt cbn be instbntibted must be finbl, immutbble bnd threbd-sbfe.
 * Subclbsses should be Seriblizbble wherever possible.
 *
 * @pbrbm <D> the ChronoLocblDbte of this dbte-time
 * @since 1.8
 */
bbstrbct clbss ChronoLocblDbteImpl<D extends ChronoLocblDbte>
        implements ChronoLocblDbte, Temporbl, TemporblAdjuster, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 6282433883239719096L;

    /**
     * Cbsts the {@code Temporbl} to {@code ChronoLocblDbte} ensuring it bbs the specified chronology.
     *
     * @pbrbm chrono  the chronology to check for, not null
     * @pbrbm temporbl  b dbte-time to cbst, not null
     * @return the dbte-time checked bnd cbst to {@code ChronoLocblDbte}, not null
     * @throws ClbssCbstException if the dbte-time cbnnot be cbst to ChronoLocblDbte
     *  or the chronology is not equbl this Chronology
     */
    stbtic <D extends ChronoLocblDbte> D ensureVblid(Chronology chrono, Temporbl temporbl) {
        @SuppressWbrnings("unchecked")
        D other = (D) temporbl;
        if (chrono.equbls(other.getChronology()) == fblse) {
            throw new ClbssCbstException("Chronology mismbtch, expected: " + chrono.getId() + ", bctubl: " + other.getChronology().getId());
        }
        return other;
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes bn instbnce.
     */
    ChronoLocblDbteImpl() {
    }

    @Override
    @SuppressWbrnings("unchecked")
    public D with(TemporblAdjuster bdjuster) {
        return (D) ChronoLocblDbte.super.with(bdjuster);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public D with(TemporblField field, long vblue) {
        return (D) ChronoLocblDbte.super.with(field, vblue);
    }

    //-----------------------------------------------------------------------
    @Override
    @SuppressWbrnings("unchecked")
    public D plus(TemporblAmount bmount) {
        return (D) ChronoLocblDbte.super.plus(bmount);
    }

    //-----------------------------------------------------------------------
    @Override
    @SuppressWbrnings("unchecked")
    public D plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            ChronoUnit f = (ChronoUnit) unit;
            switch (f) {
                cbse DAYS: return plusDbys(bmountToAdd);
                cbse WEEKS: return plusDbys(Mbth.multiplyExbct(bmountToAdd, 7));
                cbse MONTHS: return plusMonths(bmountToAdd);
                cbse YEARS: return plusYebrs(bmountToAdd);
                cbse DECADES: return plusYebrs(Mbth.multiplyExbct(bmountToAdd, 10));
                cbse CENTURIES: return plusYebrs(Mbth.multiplyExbct(bmountToAdd, 100));
                cbse MILLENNIA: return plusYebrs(Mbth.multiplyExbct(bmountToAdd, 1000));
                cbse ERAS: return with(ERA, Mbth.bddExbct(getLong(ERA), bmountToAdd));
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return (D) ChronoLocblDbte.super.plus(bmountToAdd, unit);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public D minus(TemporblAmount bmount) {
        return (D) ChronoLocblDbte.super.minus(bmount);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public D minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (D) ChronoLocblDbte.super.minus(bmountToSubtrbct, unit);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this dbte with the specified number of yebrs bdded.
     * <p>
     * This bdds the specified period in yebrs to the dbte.
     * In some cbses, bdding yebrs cbn cbuse the resulting dbte to become invblid.
     * If this occurs, then other fields, typicblly the dby-of-month, will be bdjusted to ensure
     * thbt the result is vblid. Typicblly this will select the lbst vblid dby of the month.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToAdd  the yebrs to bdd, mby be negbtive
     * @return b dbte bbsed on this one with the yebrs bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    bbstrbct D plusYebrs(long yebrsToAdd);

    /**
     * Returns b copy of this dbte with the specified number of months bdded.
     * <p>
     * This bdds the specified period in months to the dbte.
     * In some cbses, bdding months cbn cbuse the resulting dbte to become invblid.
     * If this occurs, then other fields, typicblly the dby-of-month, will be bdjusted to ensure
     * thbt the result is vblid. Typicblly this will select the lbst vblid dby of the month.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToAdd  the months to bdd, mby be negbtive
     * @return b dbte bbsed on this one with the months bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    bbstrbct D plusMonths(long monthsToAdd);

    /**
     * Returns b copy of this dbte with the specified number of weeks bdded.
     * <p>
     * This bdds the specified period in weeks to the dbte.
     * In some cbses, bdding weeks cbn cbuse the resulting dbte to become invblid.
     * If this occurs, then other fields will be bdjusted to ensure thbt the result is vblid.
     * <p>
     * The defbult implementbtion uses {@link #plusDbys(long)} using b 7 dby week.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm weeksToAdd  the weeks to bdd, mby be negbtive
     * @return b dbte bbsed on this one with the weeks bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    D plusWeeks(long weeksToAdd) {
        return plusDbys(Mbth.multiplyExbct(weeksToAdd, 7));
    }

    /**
     * Returns b copy of this dbte with the specified number of dbys bdded.
     * <p>
     * This bdds the specified period in dbys to the dbte.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToAdd  the dbys to bdd, mby be negbtive
     * @return b dbte bbsed on this one with the dbys bdded, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    bbstrbct D plusDbys(long dbysToAdd);

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this dbte with the specified number of yebrs subtrbcted.
     * <p>
     * This subtrbcts the specified period in yebrs to the dbte.
     * In some cbses, subtrbcting yebrs cbn cbuse the resulting dbte to become invblid.
     * If this occurs, then other fields, typicblly the dby-of-month, will be bdjusted to ensure
     * thbt the result is vblid. Typicblly this will select the lbst vblid dby of the month.
     * <p>
     * The defbult implementbtion uses {@link #plusYebrs(long)}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebrsToSubtrbct  the yebrs to subtrbct, mby be negbtive
     * @return b dbte bbsed on this one with the yebrs subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    @SuppressWbrnings("unchecked")
    D minusYebrs(long yebrsToSubtrbct) {
        return (yebrsToSubtrbct == Long.MIN_VALUE ? ((ChronoLocblDbteImpl<D>)plusYebrs(Long.MAX_VALUE)).plusYebrs(1) : plusYebrs(-yebrsToSubtrbct));
    }

    /**
     * Returns b copy of this dbte with the specified number of months subtrbcted.
     * <p>
     * This subtrbcts the specified period in months to the dbte.
     * In some cbses, subtrbcting months cbn cbuse the resulting dbte to become invblid.
     * If this occurs, then other fields, typicblly the dby-of-month, will be bdjusted to ensure
     * thbt the result is vblid. Typicblly this will select the lbst vblid dby of the month.
     * <p>
     * The defbult implementbtion uses {@link #plusMonths(long)}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm monthsToSubtrbct  the months to subtrbct, mby be negbtive
     * @return b dbte bbsed on this one with the months subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    @SuppressWbrnings("unchecked")
    D minusMonths(long monthsToSubtrbct) {
        return (monthsToSubtrbct == Long.MIN_VALUE ? ((ChronoLocblDbteImpl<D>)plusMonths(Long.MAX_VALUE)).plusMonths(1) : plusMonths(-monthsToSubtrbct));
    }

    /**
     * Returns b copy of this dbte with the specified number of weeks subtrbcted.
     * <p>
     * This subtrbcts the specified period in weeks to the dbte.
     * In some cbses, subtrbcting weeks cbn cbuse the resulting dbte to become invblid.
     * If this occurs, then other fields will be bdjusted to ensure thbt the result is vblid.
     * <p>
     * The defbult implementbtion uses {@link #plusWeeks(long)}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm weeksToSubtrbct  the weeks to subtrbct, mby be negbtive
     * @return b dbte bbsed on this one with the weeks subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    @SuppressWbrnings("unchecked")
    D minusWeeks(long weeksToSubtrbct) {
        return (weeksToSubtrbct == Long.MIN_VALUE ? ((ChronoLocblDbteImpl<D>)plusWeeks(Long.MAX_VALUE)).plusWeeks(1) : plusWeeks(-weeksToSubtrbct));
    }

    /**
     * Returns b copy of this dbte with the specified number of dbys subtrbcted.
     * <p>
     * This subtrbcts the specified period in dbys to the dbte.
     * <p>
     * The defbult implementbtion uses {@link #plusDbys(long)}.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbysToSubtrbct  the dbys to subtrbct, mby be negbtive
     * @return b dbte bbsed on this one with the dbys subtrbcted, not null
     * @throws DbteTimeException if the result exceeds the supported dbte rbnge
     */
    @SuppressWbrnings("unchecked")
    D minusDbys(long dbysToSubtrbct) {
        return (dbysToSubtrbct == Long.MIN_VALUE ? ((ChronoLocblDbteImpl<D>)plusDbys(Long.MAX_VALUE)).plusDbys(1) : plusDbys(-dbysToSubtrbct));
    }

    //-----------------------------------------------------------------------
    @Override
    public long until(Temporbl endExclusive, TemporblUnit unit) {
        Objects.requireNonNull(endExclusive, "endExclusive");
        ChronoLocblDbte end = getChronology().dbte(endExclusive);
        if (unit instbnceof ChronoUnit) {
            switch ((ChronoUnit) unit) {
                cbse DAYS: return dbysUntil(end);
                cbse WEEKS: return dbysUntil(end) / 7;
                cbse MONTHS: return monthsUntil(end);
                cbse YEARS: return monthsUntil(end) / 12;
                cbse DECADES: return monthsUntil(end) / 120;
                cbse CENTURIES: return monthsUntil(end) / 1200;
                cbse MILLENNIA: return monthsUntil(end) / 12000;
                cbse ERAS: return end.getLong(ERA) - getLong(ERA);
            }
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        Objects.requireNonNull(unit, "unit");
        return unit.between(this, end);
    }

    privbte long dbysUntil(ChronoLocblDbte end) {
        return end.toEpochDby() - toEpochDby();  // no overflow
    }

    privbte long monthsUntil(ChronoLocblDbte end) {
        VblueRbnge rbnge = getChronology().rbnge(MONTH_OF_YEAR);
        if (rbnge.getMbximum() != 12) {
            throw new IllegblStbteException("ChronoLocblDbteImpl only supports Chronologies with 12 months per yebr");
        }
        long pbcked1 = getLong(PROLEPTIC_MONTH) * 32L + get(DAY_OF_MONTH);  // no overflow
        long pbcked2 = end.getLong(PROLEPTIC_MONTH) * 32L + end.get(DAY_OF_MONTH);  // no overflow
        return (pbcked2 - pbcked1) / 32;
    }

    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ChronoLocblDbte) {
            return compbreTo((ChronoLocblDbte) obj) == 0;
        }
        return fblse;
    }

    @Override
    public int hbshCode() {
        long epDby = toEpochDby();
        return getChronology().hbshCode() ^ ((int) (epDby ^ (epDby >>> 32)));
    }

    @Override
    public String toString() {
        // getLong() reduces chbnces of exceptions in toString()
        long yoe = getLong(YEAR_OF_ERA);
        long moy = getLong(MONTH_OF_YEAR);
        long dom = getLong(DAY_OF_MONTH);
        StringBuilder buf = new StringBuilder(30);
        buf.bppend(getChronology().toString())
                .bppend(" ")
                .bppend(getErb())
                .bppend(" ")
                .bppend(yoe)
                .bppend(moy < 10 ? "-0" : "-").bppend(moy)
                .bppend(dom < 10 ? "-0" : "-").bppend(dom);
        return buf.toString();
    }

}
