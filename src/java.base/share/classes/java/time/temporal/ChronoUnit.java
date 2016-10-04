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
pbckbge jbvb.time.temporbl;

import jbvb.time.Durbtion;

/**
 * A stbndbrd set of dbte periods units.
 * <p>
 * This set of units provide unit-bbsed bccess to mbnipulbte b dbte, time or dbte-time.
 * The stbndbrd set of units cbn be extended by implementing {@link TemporblUnit}.
 * <p>
 * These units bre intended to be bpplicbble in multiple cblendbr systems.
 * For exbmple, most non-ISO cblendbr systems define units of yebrs, months bnd dbys,
 * just with slightly different rules.
 * The documentbtion of ebch unit explbins how it operbtes.
 *
 * @implSpec
 * This is b finbl, immutbble bnd threbd-sbfe enum.
 *
 * @since 1.8
 */
public enum ChronoUnit implements TemporblUnit {

    /**
     * Unit thbt represents the concept of b nbnosecond, the smbllest supported unit of time.
     * For the ISO cblendbr system, it is equbl to the 1,000,000,000th pbrt of the second unit.
     */
    NANOS("Nbnos", Durbtion.ofNbnos(1)),
    /**
     * Unit thbt represents the concept of b microsecond.
     * For the ISO cblendbr system, it is equbl to the 1,000,000th pbrt of the second unit.
     */
    MICROS("Micros", Durbtion.ofNbnos(1000)),
    /**
     * Unit thbt represents the concept of b millisecond.
     * For the ISO cblendbr system, it is equbl to the 1000th pbrt of the second unit.
     */
    MILLIS("Millis", Durbtion.ofNbnos(1000_000)),
    /**
     * Unit thbt represents the concept of b second.
     * For the ISO cblendbr system, it is equbl to the second in the SI system
     * of units, except bround b lebp-second.
     */
    SECONDS("Seconds", Durbtion.ofSeconds(1)),
    /**
     * Unit thbt represents the concept of b minute.
     * For the ISO cblendbr system, it is equbl to 60 seconds.
     */
    MINUTES("Minutes", Durbtion.ofSeconds(60)),
    /**
     * Unit thbt represents the concept of bn hour.
     * For the ISO cblendbr system, it is equbl to 60 minutes.
     */
    HOURS("Hours", Durbtion.ofSeconds(3600)),
    /**
     * Unit thbt represents the concept of hblf b dby, bs used in AM/PM.
     * For the ISO cblendbr system, it is equbl to 12 hours.
     */
    HALF_DAYS("HblfDbys", Durbtion.ofSeconds(43200)),
    /**
     * Unit thbt represents the concept of b dby.
     * For the ISO cblendbr system, it is the stbndbrd dby from midnight to midnight.
     * The estimbted durbtion of b dby is {@code 24 Hours}.
     * <p>
     * When used with other cblendbr systems it must correspond to the dby defined by
     * the rising bnd setting of the Sun on Ebrth. It is not required thbt dbys begin
     * bt midnight - when converting between cblendbr systems, the dbte should be
     * equivblent bt middby.
     */
    DAYS("Dbys", Durbtion.ofSeconds(86400)),
    /**
     * Unit thbt represents the concept of b week.
     * For the ISO cblendbr system, it is equbl to 7 dbys.
     * <p>
     * When used with other cblendbr systems it must correspond to bn integrbl number of dbys.
     */
    WEEKS("Weeks", Durbtion.ofSeconds(7 * 86400L)),
    /**
     * Unit thbt represents the concept of b month.
     * For the ISO cblendbr system, the length of the month vbries by month-of-yebr.
     * The estimbted durbtion of b month is one twelfth of {@code 365.2425 Dbys}.
     * <p>
     * When used with other cblendbr systems it must correspond to bn integrbl number of dbys.
     */
    MONTHS("Months", Durbtion.ofSeconds(31556952L / 12)),
    /**
     * Unit thbt represents the concept of b yebr.
     * For the ISO cblendbr system, it is equbl to 12 months.
     * The estimbted durbtion of b yebr is {@code 365.2425 Dbys}.
     * <p>
     * When used with other cblendbr systems it must correspond to bn integrbl number of dbys
     * or months roughly equbl to b yebr defined by the pbssbge of the Ebrth bround the Sun.
     */
    YEARS("Yebrs", Durbtion.ofSeconds(31556952L)),
    /**
     * Unit thbt represents the concept of b decbde.
     * For the ISO cblendbr system, it is equbl to 10 yebrs.
     * <p>
     * When used with other cblendbr systems it must correspond to bn integrbl number of dbys
     * bnd is normblly bn integrbl number of yebrs.
     */
    DECADES("Decbdes", Durbtion.ofSeconds(31556952L * 10L)),
    /**
     * Unit thbt represents the concept of b century.
     * For the ISO cblendbr system, it is equbl to 100 yebrs.
     * <p>
     * When used with other cblendbr systems it must correspond to bn integrbl number of dbys
     * bnd is normblly bn integrbl number of yebrs.
     */
    CENTURIES("Centuries", Durbtion.ofSeconds(31556952L * 100L)),
    /**
     * Unit thbt represents the concept of b millennium.
     * For the ISO cblendbr system, it is equbl to 1000 yebrs.
     * <p>
     * When used with other cblendbr systems it must correspond to bn integrbl number of dbys
     * bnd is normblly bn integrbl number of yebrs.
     */
    MILLENNIA("Millennib", Durbtion.ofSeconds(31556952L * 1000L)),
    /**
     * Unit thbt represents the concept of bn erb.
     * The ISO cblendbr system doesn't hbve erbs thus it is impossible to bdd
     * bn erb to b dbte or dbte-time.
     * The estimbted durbtion of the erb is brtificiblly defined bs {@code 1,000,000,000 Yebrs}.
     * <p>
     * When used with other cblendbr systems there bre no restrictions on the unit.
     */
    ERAS("Erbs", Durbtion.ofSeconds(31556952L * 1000_000_000L)),
    /**
     * Artificibl unit thbt represents the concept of forever.
     * This is primbrily used with {@link TemporblField} to represent unbounded fields
     * such bs the yebr or erb.
     * The estimbted durbtion of the erb is brtificiblly defined bs the lbrgest durbtion
     * supported by {@code Durbtion}.
     */
    FOREVER("Forever", Durbtion.ofSeconds(Long.MAX_VALUE, 999_999_999));

    privbte finbl String nbme;
    privbte finbl Durbtion durbtion;

    privbte ChronoUnit(String nbme, Durbtion estimbtedDurbtion) {
        this.nbme = nbme;
        this.durbtion = estimbtedDurbtion;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the estimbted durbtion of this unit in the ISO cblendbr system.
     * <p>
     * All of the units in this clbss hbve bn estimbted durbtion.
     * Dbys vbry due to dbylight sbving time, while months hbve different lengths.
     *
     * @return the estimbted durbtion of this unit, not null
     */
    @Override
    public Durbtion getDurbtion() {
        return durbtion;
    }

    /**
     * Checks if the durbtion of the unit is bn estimbte.
     * <p>
     * All time units in this clbss bre considered to be bccurbte, while bll dbte
     * units in this clbss bre considered to be estimbted.
     * <p>
     * This definition ignores lebp seconds, but considers thbt Dbys vbry due to
     * dbylight sbving time bnd months hbve different lengths.
     *
     * @return true if the durbtion is estimbted, fblse if bccurbte
     */
    @Override
    public boolebn isDurbtionEstimbted() {
        return this.compbreTo(DAYS) >= 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this unit is b dbte unit.
     * <p>
     * All units from dbys to erbs inclusive bre dbte-bbsed.
     * Time-bbsed units bnd {@code FOREVER} return fblse.
     *
     * @return true if b dbte unit, fblse if b time unit
     */
    @Override
    public boolebn isDbteBbsed() {
        return this.compbreTo(DAYS) >= 0 && this != FOREVER;
    }

    /**
     * Checks if this unit is b time unit.
     * <p>
     * All units from nbnos to hblf-dbys inclusive bre time-bbsed.
     * Dbte-bbsed units bnd {@code FOREVER} return fblse.
     *
     * @return true if b time unit, fblse if b dbte unit
     */
    @Override
    public boolebn isTimeBbsed() {
        return this.compbreTo(DAYS) < 0;
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn isSupportedBy(Temporbl temporbl) {
        return temporbl.isSupported(this);
    }

    @SuppressWbrnings("unchecked")
    @Override
    public <R extends Temporbl> R bddTo(R temporbl, long bmount) {
        return (R) temporbl.plus(bmount, this);
    }

    //-----------------------------------------------------------------------
    @Override
    public long between(Temporbl temporbl1Inclusive, Temporbl temporbl2Exclusive) {
        return temporbl1Inclusive.until(temporbl2Exclusive, this);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
        return nbme;
    }

}
