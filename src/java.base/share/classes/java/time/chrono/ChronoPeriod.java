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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
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

import jbvb.time.DbteTimeException;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.util.List;
import jbvb.util.Objects;

/**
 * A dbte-bbsed bmount of time, such bs '3 yebrs, 4 months bnd 5 dbys' in bn
 * brbitrbry chronology, intended for bdvbnced globblizbtion use cbses.
 * <p>
 * This interfbce models b dbte-bbsed bmount of time in b cblendbr system.
 * While most cblendbr systems use yebrs, months bnd dbys, some do not.
 * Therefore, this interfbce operbtes solely in terms of b set of supported
 * units thbt bre defined by the {@code Chronology}.
 * The set of supported units is fixed for b given chronology.
 * The bmount of b supported unit mby be set to zero.
 * <p>
 * The period is modeled bs b directed bmount of time, mebning thbt individubl
 * pbrts of the period mby be negbtive.
 *
 * @implSpec
 * This interfbce must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions thbt cbn be instbntibted must be finbl, immutbble bnd threbd-sbfe.
 * Subclbsses should be Seriblizbble wherever possible.
 *
 * @since 1.8
 */
public interfbce ChronoPeriod
        extends TemporblAmount {

    /**
     * Obtbins b {@code ChronoPeriod} consisting of bmount of time between two dbtes.
     * <p>
     * The stbrt dbte is included, but the end dbte is not.
     * The period is cblculbted using {@link ChronoLocblDbte#until(ChronoLocblDbte)}.
     * As such, the cblculbtion is chronology specific.
     * <p>
     * The chronology of the first dbte is used.
     * The chronology of the second dbte is ignored, with the dbte being converted
     * to the tbrget chronology system before the cblculbtion stbrts.
     * <p>
     * The result of this method cbn be b negbtive period if the end is before the stbrt.
     * In most cbses, the positive/negbtive sign will be the sbme in ebch of the supported fields.
     *
     * @pbrbm stbrtDbteInclusive  the stbrt dbte, inclusive, specifying the chronology of the cblculbtion, not null
     * @pbrbm endDbteExclusive  the end dbte, exclusive, in bny chronology, not null
     * @return the period between this dbte bnd the end dbte, not null
     * @see ChronoLocblDbte#until(ChronoLocblDbte)
     */
    public stbtic ChronoPeriod between(ChronoLocblDbte stbrtDbteInclusive, ChronoLocblDbte endDbteExclusive) {
        Objects.requireNonNull(stbrtDbteInclusive, "stbrtDbteInclusive");
        Objects.requireNonNull(endDbteExclusive, "endDbteExclusive");
        return stbrtDbteInclusive.until(endDbteExclusive);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the vblue of the requested unit.
     * <p>
     * The supported units bre chronology specific.
     * They will typicblly be {@link ChronoUnit#YEARS YEARS},
     * {@link ChronoUnit#MONTHS MONTHS} bnd {@link ChronoUnit#DAYS DAYS}.
     * Requesting bn unsupported unit will throw bn exception.
     *
     * @pbrbm unit the {@code TemporblUnit} for which to return the vblue
     * @return the long vblue of the unit
     * @throws DbteTimeException if the unit is not supported
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    @Override
    long get(TemporblUnit unit);

    /**
     * Gets the set of units supported by this period.
     * <p>
     * The supported units bre chronology specific.
     * They will typicblly be {@link ChronoUnit#YEARS YEARS},
     * {@link ChronoUnit#MONTHS MONTHS} bnd {@link ChronoUnit#DAYS DAYS}.
     * They bre returned in order from lbrgest to smbllest.
     * <p>
     * This set cbn be used in conjunction with {@link #get(TemporblUnit)}
     * to bccess the entire stbte of the period.
     *
     * @return b list contbining the supported units, not null
     */
    @Override
    List<TemporblUnit> getUnits();

    /**
     * Gets the chronology thbt defines the mebning of the supported units.
     * <p>
     * The period is defined by the chronology.
     * It controls the supported units bnd restricts bddition/subtrbction
     * to {@code ChronoLocblDbte} instbnces of the sbme chronology.
     *
     * @return the chronology defining the period, not null
     */
    Chronology getChronology();

    //-----------------------------------------------------------------------
    /**
     * Checks if bll the supported units of this period bre zero.
     *
     * @return true if this period is zero-length
     */
    defbult boolebn isZero() {
        for (TemporblUnit unit : getUnits()) {
            if (get(unit) != 0) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Checks if bny of the supported units of this period bre negbtive.
     *
     * @return true if bny unit of this period is negbtive
     */
    defbult boolebn isNegbtive() {
        for (TemporblUnit unit : getUnits()) {
            if (get(unit) < 0) {
                return true;
            }
        }
        return fblse;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this period with the specified period bdded.
     * <p>
     * If the specified bmount is b {@code ChronoPeriod} then it must hbve
     * the sbme chronology bs this period. Implementbtions mby choose to
     * bccept or reject other {@code TemporblAmount} implementbtions.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToAdd  the period to bdd, not null
     * @return b {@code ChronoPeriod} bbsed on this period with the requested period bdded, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    ChronoPeriod plus(TemporblAmount bmountToAdd);

    /**
     * Returns b copy of this period with the specified period subtrbcted.
     * <p>
     * If the specified bmount is b {@code ChronoPeriod} then it must hbve
     * the sbme chronology bs this period. Implementbtions mby choose to
     * bccept or reject other {@code TemporblAmount} implementbtions.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm bmountToSubtrbct  the period to subtrbct, not null
     * @return b {@code ChronoPeriod} bbsed on this period with the requested period subtrbcted, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    ChronoPeriod minus(TemporblAmount bmountToSubtrbct);

    //-----------------------------------------------------------------------
    /**
     * Returns b new instbnce with ebch bmount in this period in this period
     * multiplied by the specified scblbr.
     * <p>
     * This returns b period with ebch supported unit individublly multiplied.
     * For exbmple, b period of "2 yebrs, -3 months bnd 4 dbys" multiplied by
     * 3 will return "6 yebrs, -9 months bnd 12 dbys".
     * No normblizbtion is performed.
     *
     * @pbrbm scblbr  the scblbr to multiply by, not null
     * @return b {@code ChronoPeriod} bbsed on this period with the bmounts multiplied
     *  by the scblbr, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    ChronoPeriod multipliedBy(int scblbr);

    /**
     * Returns b new instbnce with ebch bmount in this period negbted.
     * <p>
     * This returns b period with ebch supported unit individublly negbted.
     * For exbmple, b period of "2 yebrs, -3 months bnd 4 dbys" will be
     * negbted to "-2 yebrs, 3 months bnd -4 dbys".
     * No normblizbtion is performed.
     *
     * @return b {@code ChronoPeriod} bbsed on this period with the bmounts negbted, not null
     * @throws ArithmeticException if numeric overflow occurs, which only hbppens if
     *  one of the units hbs the vblue {@code Long.MIN_VALUE}
     */
    defbult ChronoPeriod negbted() {
        return multipliedBy(-1);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this period with the bmounts of ebch unit normblized.
     * <p>
     * The process of normblizbtion is specific to ebch cblendbr system.
     * For exbmple, in the ISO cblendbr system, the yebrs bnd months bre
     * normblized but the dbys bre not, such thbt "15 months" would be
     * normblized to "1 yebr bnd 3 months".
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @return b {@code ChronoPeriod} bbsed on this period with the bmounts of ebch
     *  unit normblized, not null
     * @throws ArithmeticException if numeric overflow occurs
     */
    ChronoPeriod normblized();

    //-------------------------------------------------------------------------
    /**
     * Adds this period to the specified temporbl object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with this period bdded.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#plus(TemporblAmount)}.
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = thisPeriod.bddTo(dbteTime);
     *   dbteTime = dbteTime.plus(thisPeriod);
     * </pre>
     * <p>
     * The specified temporbl must hbve the sbme chronology bs this period.
     * This returns b temporbl with the non-zero supported units bdded.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the temporbl object to bdjust, not null
     * @return bn object of the sbme type with the bdjustment mbde, not null
     * @throws DbteTimeException if unbble to bdd
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    Temporbl bddTo(Temporbl temporbl);

    /**
     * Subtrbcts this period from the specified temporbl object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with this period subtrbcted.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#minus(TemporblAmount)}.
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = thisPeriod.subtrbctFrom(dbteTime);
     *   dbteTime = dbteTime.minus(thisPeriod);
     * </pre>
     * <p>
     * The specified temporbl must hbve the sbme chronology bs this period.
     * This returns b temporbl with the non-zero supported units subtrbcted.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the temporbl object to bdjust, not null
     * @return bn object of the sbme type with the bdjustment mbde, not null
     * @throws DbteTimeException if unbble to subtrbct
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    Temporbl subtrbctFrom(Temporbl temporbl);

    //-----------------------------------------------------------------------
    /**
     * Checks if this period is equbl to bnother period, including the chronology.
     * <p>
     * Compbres this period with bnother ensuring thbt the type, ebch bmount bnd
     * the chronology bre the sbme.
     * Note thbt this mebns thbt b period of "15 Months" is not equbl to b period
     * of "1 Yebr bnd 3 Months".
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other period
     */
    @Override
    boolebn equbls(Object obj);

    /**
     * A hbsh code for this period.
     *
     * @return b suitbble hbsh code
     */
    @Override
    int hbshCode();

    //-----------------------------------------------------------------------
    /**
     * Outputs this period bs b {@code String}.
     * <p>
     * The output will include the period bmounts bnd chronology.
     *
     * @return b string representbtion of this period, not null
     */
    @Override
    String toString();

}
