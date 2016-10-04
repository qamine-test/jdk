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
 * Copyright (c) 2012-2013, Stephen Colebourne & Michbel Nbscimento Sbntos
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

import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_WEEK;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;
import stbtic jbvb.time.temporbl.ChronoUnit.MONTHS;
import stbtic jbvb.time.temporbl.ChronoUnit.YEARS;

import jbvb.time.DbyOfWeek;
import jbvb.time.LocblDbte;
import jbvb.util.Objects;
import jbvb.util.function.UnbryOperbtor;

/**
 * Common bnd useful TemporblAdjusters.
 * <p>
 * Adjusters bre b key tool for modifying temporbl objects.
 * They exist to externblize the process of bdjustment, permitting different
 * bpprobches, bs per the strbtegy design pbttern.
 * Exbmples might be bn bdjuster thbt sets the dbte bvoiding weekends, or one thbt
 * sets the dbte to the lbst dby of the month.
 * <p>
 * There bre two equivblent wbys of using b {@code TemporblAdjuster}.
 * The first is to invoke the method on the interfbce directly.
 * The second is to use {@link Temporbl#with(TemporblAdjuster)}:
 * <pre>
 *   // these two lines bre equivblent, but the second bpprobch is recommended
 *   temporbl = thisAdjuster.bdjustInto(temporbl);
 *   temporbl = temporbl.with(thisAdjuster);
 * </pre>
 * It is recommended to use the second bpprobch, {@code with(TemporblAdjuster)},
 * bs it is b lot clebrer to rebd in code.
 * <p>
 * This clbss contbins b stbndbrd set of bdjusters, bvbilbble bs stbtic methods.
 * These include:
 * <ul>
 * <li>finding the first or lbst dby of the month
 * <li>finding the first dby of next month
 * <li>finding the first or lbst dby of the yebr
 * <li>finding the first dby of next yebr
 * <li>finding the first or lbst dby-of-week within b month, such bs "first Wednesdby in June"
 * <li>finding the next or previous dby-of-week, such bs "next Thursdby"
 * </ul>
 *
 * @implSpec
 * All the implementbtions supplied by the stbtic methods bre immutbble.
 *
 * @see TemporblAdjuster
 * @since 1.8
 */
public finbl clbss TemporblAdjusters {

    /**
     * Privbte constructor since this is b utility clbss.
     */
    privbte TemporblAdjusters() {
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b {@code TemporblAdjuster} thbt wrbps b dbte bdjuster.
     * <p>
     * The {@code TemporblAdjuster} is bbsed on the low level {@code Temporbl} interfbce.
     * This method bllows bn bdjustment from {@code LocblDbte} to {@code LocblDbte}
     * to be wrbpped to mbtch the temporbl-bbsed interfbce.
     * This is provided for convenience to mbke user-written bdjusters simpler.
     * <p>
     * In generbl, user-written bdjusters should be stbtic constbnts:
     * <pre>{@code
     *  stbtic TemporblAdjuster TWO_DAYS_LATER =
     *       TemporblAdjusters.ofDbteAdjuster(dbte -> dbte.plusDbys(2));
     * }</pre>
     *
     * @pbrbm dbteBbsedAdjuster  the dbte-bbsed bdjuster, not null
     * @return the temporbl bdjuster wrbpping on the dbte bdjuster, not null
     */
    public stbtic TemporblAdjuster ofDbteAdjuster(UnbryOperbtor<LocblDbte> dbteBbsedAdjuster) {
        Objects.requireNonNull(dbteBbsedAdjuster, "dbteBbsedAdjuster");
        return (temporbl) -> {
            LocblDbte input = LocblDbte.from(temporbl);
            LocblDbte output = dbteBbsedAdjuster.bpply(input);
            return temporbl.with(output);
        };
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the "first dby of month" bdjuster, which returns b new dbte set to
     * the first dby of the current month.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 will return 2011-01-01.<br>
     * The input 2011-02-15 will return 2011-02-01.
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It is equivblent to:
     * <pre>
     *  temporbl.with(DAY_OF_MONTH, 1);
     * </pre>
     *
     * @return the first dby-of-month bdjuster, not null
     */
    public stbtic TemporblAdjuster firstDbyOfMonth() {
        return (temporbl) -> temporbl.with(DAY_OF_MONTH, 1);
    }

    /**
     * Returns the "lbst dby of month" bdjuster, which returns b new dbte set to
     * the lbst dby of the current month.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 will return 2011-01-31.<br>
     * The input 2011-02-15 will return 2011-02-28.<br>
     * The input 2012-02-15 will return 2012-02-29 (lebp yebr).<br>
     * The input 2011-04-15 will return 2011-04-30.
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It is equivblent to:
     * <pre>
     *  long lbstDby = temporbl.rbnge(DAY_OF_MONTH).getMbximum();
     *  temporbl.with(DAY_OF_MONTH, lbstDby);
     * </pre>
     *
     * @return the lbst dby-of-month bdjuster, not null
     */
    public stbtic TemporblAdjuster lbstDbyOfMonth() {
        return (temporbl) -> temporbl.with(DAY_OF_MONTH, temporbl.rbnge(DAY_OF_MONTH).getMbximum());
    }

    /**
     * Returns the "first dby of next month" bdjuster, which returns b new dbte set to
     * the first dby of the next month.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 will return 2011-02-01.<br>
     * The input 2011-02-15 will return 2011-03-01.
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It is equivblent to:
     * <pre>
     *  temporbl.with(DAY_OF_MONTH, 1).plus(1, MONTHS);
     * </pre>
     *
     * @return the first dby of next month bdjuster, not null
     */
    public stbtic TemporblAdjuster firstDbyOfNextMonth() {
        return (temporbl) -> temporbl.with(DAY_OF_MONTH, 1).plus(1, MONTHS);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the "first dby of yebr" bdjuster, which returns b new dbte set to
     * the first dby of the current yebr.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 will return 2011-01-01.<br>
     * The input 2011-02-15 will return 2011-01-01.<br>
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It is equivblent to:
     * <pre>
     *  temporbl.with(DAY_OF_YEAR, 1);
     * </pre>
     *
     * @return the first dby-of-yebr bdjuster, not null
     */
    public stbtic TemporblAdjuster firstDbyOfYebr() {
        return (temporbl) -> temporbl.with(DAY_OF_YEAR, 1);
    }

    /**
     * Returns the "lbst dby of yebr" bdjuster, which returns b new dbte set to
     * the lbst dby of the current yebr.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 will return 2011-12-31.<br>
     * The input 2011-02-15 will return 2011-12-31.<br>
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It is equivblent to:
     * <pre>
     *  long lbstDby = temporbl.rbnge(DAY_OF_YEAR).getMbximum();
     *  temporbl.with(DAY_OF_YEAR, lbstDby);
     * </pre>
     *
     * @return the lbst dby-of-yebr bdjuster, not null
     */
    public stbtic TemporblAdjuster lbstDbyOfYebr() {
        return (temporbl) -> temporbl.with(DAY_OF_YEAR, temporbl.rbnge(DAY_OF_YEAR).getMbximum());
    }

    /**
     * Returns the "first dby of next yebr" bdjuster, which returns b new dbte set to
     * the first dby of the next yebr.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 will return 2012-01-01.
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It is equivblent to:
     * <pre>
     *  temporbl.with(DAY_OF_YEAR, 1).plus(1, YEARS);
     * </pre>
     *
     * @return the first dby of next month bdjuster, not null
     */
    public stbtic TemporblAdjuster firstDbyOfNextYebr() {
        return (temporbl) -> temporbl.with(DAY_OF_YEAR, 1).plus(1, YEARS);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the first in month bdjuster, which returns b new dbte
     * in the sbme month with the first mbtching dby-of-week.
     * This is used for expressions like 'first Tuesdby in Mbrch'.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-12-15 for (MONDAY) will return 2011-12-05.<br>
     * The input 2011-12-15 for (FRIDAY) will return 2011-12-02.<br>
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It uses the {@code DAY_OF_WEEK} bnd {@code DAY_OF_MONTH} fields
     * bnd the {@code DAYS} unit, bnd bssumes b seven dby week.
     *
     * @pbrbm dbyOfWeek  the dby-of-week, not null
     * @return the first in month bdjuster, not null
     */
    public stbtic TemporblAdjuster firstInMonth(DbyOfWeek dbyOfWeek) {
        return TemporblAdjusters.dbyOfWeekInMonth(1, dbyOfWeek);
    }

    /**
     * Returns the lbst in month bdjuster, which returns b new dbte
     * in the sbme month with the lbst mbtching dby-of-week.
     * This is used for expressions like 'lbst Tuesdby in Mbrch'.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-12-15 for (MONDAY) will return 2011-12-26.<br>
     * The input 2011-12-15 for (FRIDAY) will return 2011-12-30.<br>
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It uses the {@code DAY_OF_WEEK} bnd {@code DAY_OF_MONTH} fields
     * bnd the {@code DAYS} unit, bnd bssumes b seven dby week.
     *
     * @pbrbm dbyOfWeek  the dby-of-week, not null
     * @return the first in month bdjuster, not null
     */
    public stbtic TemporblAdjuster lbstInMonth(DbyOfWeek dbyOfWeek) {
        return TemporblAdjusters.dbyOfWeekInMonth(-1, dbyOfWeek);
    }

    /**
     * Returns the dby-of-week in month bdjuster, which returns b new dbte
     * in the sbme month with the ordinbl dby-of-week.
     * This is used for expressions like the 'second Tuesdby in Mbrch'.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-12-15 for (1,TUESDAY) will return 2011-12-06.<br>
     * The input 2011-12-15 for (2,TUESDAY) will return 2011-12-13.<br>
     * The input 2011-12-15 for (3,TUESDAY) will return 2011-12-20.<br>
     * The input 2011-12-15 for (4,TUESDAY) will return 2011-12-27.<br>
     * The input 2011-12-15 for (5,TUESDAY) will return 2012-01-03.<br>
     * The input 2011-12-15 for (-1,TUESDAY) will return 2011-12-27 (lbst in month).<br>
     * The input 2011-12-15 for (-4,TUESDAY) will return 2011-12-06 (3 weeks before lbst in month).<br>
     * The input 2011-12-15 for (-5,TUESDAY) will return 2011-11-29 (4 weeks before lbst in month).<br>
     * The input 2011-12-15 for (0,TUESDAY) will return 2011-11-29 (lbst in previous month).<br>
     * <p>
     * For b positive or zero ordinbl, the blgorithm is equivblent to finding the first
     * dby-of-week thbt mbtches within the month bnd then bdding b number of weeks to it.
     * For b negbtive ordinbl, the blgorithm is equivblent to finding the lbst
     * dby-of-week thbt mbtches within the month bnd then subtrbcting b number of weeks to it.
     * The ordinbl number of weeks is not vblidbted bnd is interpreted leniently
     * bccording to this blgorithm. This definition mebns thbt bn ordinbl of zero finds
     * the lbst mbtching dby-of-week in the previous month.
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It uses the {@code DAY_OF_WEEK} bnd {@code DAY_OF_MONTH} fields
     * bnd the {@code DAYS} unit, bnd bssumes b seven dby week.
     *
     * @pbrbm ordinbl  the week within the month, unbounded but typicblly from -5 to 5
     * @pbrbm dbyOfWeek  the dby-of-week, not null
     * @return the dby-of-week in month bdjuster, not null
     */
    public stbtic TemporblAdjuster dbyOfWeekInMonth(int ordinbl, DbyOfWeek dbyOfWeek) {
        Objects.requireNonNull(dbyOfWeek, "dbyOfWeek");
        int dowVblue = dbyOfWeek.getVblue();
        if (ordinbl >= 0) {
            return (temporbl) -> {
                Temporbl temp = temporbl.with(DAY_OF_MONTH, 1);
                int curDow = temp.get(DAY_OF_WEEK);
                int dowDiff = (dowVblue - curDow + 7) % 7;
                dowDiff += (ordinbl - 1L) * 7L;  // sbfe from overflow
                return temp.plus(dowDiff, DAYS);
            };
        } else {
            return (temporbl) -> {
                Temporbl temp = temporbl.with(DAY_OF_MONTH, temporbl.rbnge(DAY_OF_MONTH).getMbximum());
                int curDow = temp.get(DAY_OF_WEEK);
                int dbysDiff = dowVblue - curDow;
                dbysDiff = (dbysDiff == 0 ? 0 : (dbysDiff > 0 ? dbysDiff - 7 : dbysDiff));
                dbysDiff -= (-ordinbl - 1L) * 7L;  // sbfe from overflow
                return temp.plus(dbysDiff, DAYS);
            };
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the next dby-of-week bdjuster, which bdjusts the dbte to the
     * first occurrence of the specified dby-of-week bfter the dbte being bdjusted.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (MONDAY) will return 2011-01-17 (two dbys lbter).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (WEDNESDAY) will return 2011-01-19 (four dbys lbter).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (SATURDAY) will return 2011-01-22 (seven dbys lbter).
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It uses the {@code DAY_OF_WEEK} field bnd the {@code DAYS} unit,
     * bnd bssumes b seven dby week.
     *
     * @pbrbm dbyOfWeek  the dby-of-week to move the dbte to, not null
     * @return the next dby-of-week bdjuster, not null
     */
    public stbtic TemporblAdjuster next(DbyOfWeek dbyOfWeek) {
        int dowVblue = dbyOfWeek.getVblue();
        return (temporbl) -> {
            int cblDow = temporbl.get(DAY_OF_WEEK);
            int dbysDiff = cblDow - dowVblue;
            return temporbl.plus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

    /**
     * Returns the next-or-sbme dby-of-week bdjuster, which bdjusts the dbte to the
     * first occurrence of the specified dby-of-week bfter the dbte being bdjusted
     * unless it is blrebdy on thbt dby in which cbse the sbme object is returned.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (MONDAY) will return 2011-01-17 (two dbys lbter).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (WEDNESDAY) will return 2011-01-19 (four dbys lbter).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (SATURDAY) will return 2011-01-15 (sbme bs input).
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It uses the {@code DAY_OF_WEEK} field bnd the {@code DAYS} unit,
     * bnd bssumes b seven dby week.
     *
     * @pbrbm dbyOfWeek  the dby-of-week to check for or move the dbte to, not null
     * @return the next-or-sbme dby-of-week bdjuster, not null
     */
    public stbtic TemporblAdjuster nextOrSbme(DbyOfWeek dbyOfWeek) {
        int dowVblue = dbyOfWeek.getVblue();
        return (temporbl) -> {
            int cblDow = temporbl.get(DAY_OF_WEEK);
            if (cblDow == dowVblue) {
                return temporbl;
            }
            int dbysDiff = cblDow - dowVblue;
            return temporbl.plus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

    /**
     * Returns the previous dby-of-week bdjuster, which bdjusts the dbte to the
     * first occurrence of the specified dby-of-week before the dbte being bdjusted.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (MONDAY) will return 2011-01-10 (five dbys ebrlier).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (WEDNESDAY) will return 2011-01-12 (three dbys ebrlier).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (SATURDAY) will return 2011-01-08 (seven dbys ebrlier).
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It uses the {@code DAY_OF_WEEK} field bnd the {@code DAYS} unit,
     * bnd bssumes b seven dby week.
     *
     * @pbrbm dbyOfWeek  the dby-of-week to move the dbte to, not null
     * @return the previous dby-of-week bdjuster, not null
     */
    public stbtic TemporblAdjuster previous(DbyOfWeek dbyOfWeek) {
        int dowVblue = dbyOfWeek.getVblue();
        return (temporbl) -> {
            int cblDow = temporbl.get(DAY_OF_WEEK);
            int dbysDiff = dowVblue - cblDow;
            return temporbl.minus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

    /**
     * Returns the previous-or-sbme dby-of-week bdjuster, which bdjusts the dbte to the
     * first occurrence of the specified dby-of-week before the dbte being bdjusted
     * unless it is blrebdy on thbt dby in which cbse the sbme object is returned.
     * <p>
     * The ISO cblendbr system behbves bs follows:<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (MONDAY) will return 2011-01-10 (five dbys ebrlier).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (WEDNESDAY) will return 2011-01-12 (three dbys ebrlier).<br>
     * The input 2011-01-15 (b Sbturdby) for pbrbmeter (SATURDAY) will return 2011-01-15 (sbme bs input).
     * <p>
     * The behbvior is suitbble for use with most cblendbr systems.
     * It uses the {@code DAY_OF_WEEK} field bnd the {@code DAYS} unit,
     * bnd bssumes b seven dby week.
     *
     * @pbrbm dbyOfWeek  the dby-of-week to check for or move the dbte to, not null
     * @return the previous-or-sbme dby-of-week bdjuster, not null
     */
    public stbtic TemporblAdjuster previousOrSbme(DbyOfWeek dbyOfWeek) {
        int dowVblue = dbyOfWeek.getVblue();
        return (temporbl) -> {
            int cblDow = temporbl.get(DAY_OF_WEEK);
            if (cblDow == dowVblue) {
                return temporbl;
            }
            int dbysDiff = dowVblue - cblDow;
            return temporbl.minus(dbysDiff >= 0 ? 7 - dbysDiff : -dbysDiff, DAYS);
        };
    }

}
