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

import jbvb.time.DbteTimeException;

/**
 * Frbmework-level interfbce defining rebd-write bccess to b temporbl object,
 * such bs b dbte, time, offset or some combinbtion of these.
 * <p>
 * This is the bbse interfbce type for dbte, time bnd offset objects thbt
 * bre complete enough to be mbnipulbted using plus bnd minus.
 * It is implemented by those clbsses thbt cbn provide bnd mbnipulbte informbtion
 * bs {@linkplbin TemporblField fields} or {@linkplbin TemporblQuery queries}.
 * See {@link TemporblAccessor} for the rebd-only version of this interfbce.
 * <p>
 * Most dbte bnd time informbtion cbn be represented bs b number.
 * These bre modeled using {@code TemporblField} with the number held using
 * b {@code long} to hbndle lbrge vblues. Yebr, month bnd dby-of-month bre
 * simple exbmples of fields, but they blso include instbnt bnd offsets.
 * See {@link ChronoField} for the stbndbrd set of fields.
 * <p>
 * Two pieces of dbte/time informbtion cbnnot be represented by numbers,
 * the {@linkplbin jbvb.time.chrono.Chronology chronology} bnd the
 * {@linkplbin jbvb.time.ZoneId time-zone}.
 * These cbn be bccessed vib {@link #query(TemporblQuery) queries} using
 * the stbtic methods defined on {@link TemporblQuery}.
 * <p>
 * This interfbce is b frbmework-level interfbce thbt should not be widely
 * used in bpplicbtion code. Instebd, bpplicbtions should crebte bnd pbss
 * bround instbnces of concrete types, such bs {@code LocblDbte}.
 * There bre mbny rebsons for this, pbrt of which is thbt implementbtions
 * of this interfbce mby be in cblendbr systems other thbn ISO.
 * See {@link jbvb.time.chrono.ChronoLocblDbte} for b fuller discussion of the issues.
 *
 * <h3>When to implement</h3>
 * <p>
 * A clbss should implement this interfbce if it meets three criterib:
 * <ul>
 * <li>it provides bccess to dbte/time/offset informbtion, bs per {@code TemporblAccessor}
 * <li>the set of fields bre contiguous from the lbrgest to the smbllest
 * <li>the set of fields bre complete, such thbt no other field is needed to define the
 *  vblid rbnge of vblues for the fields thbt bre represented
 * </ul>
 * <p>
 * Four exbmples mbke this clebr:
 * <ul>
 * <li>{@code LocblDbte} implements this interfbce bs it represents b set of fields
 *  thbt bre contiguous from dbys to forever bnd require no externbl informbtion to determine
 *  the vblidity of ebch dbte. It is therefore bble to implement plus/minus correctly.
 * <li>{@code LocblTime} implements this interfbce bs it represents b set of fields
 *  thbt bre contiguous from nbnos to within dbys bnd require no externbl informbtion to determine
 *  vblidity. It is bble to implement plus/minus correctly, by wrbpping bround the dby.
 * <li>{@code MonthDby}, the combinbtion of month-of-yebr bnd dby-of-month, does not implement
 *  this interfbce.  While the combinbtion is contiguous, from dbys to months within yebrs,
 *  the combinbtion does not hbve sufficient informbtion to define the vblid rbnge of vblues
 *  for dby-of-month.  As such, it is unbble to implement plus/minus correctly.
 * <li>The combinbtion dby-of-week bnd dby-of-month ("Fridby the 13th") should not implement
 *  this interfbce. It does not represent b contiguous set of fields, bs dbys to weeks overlbps
 *  dbys to months.
 * </ul>
 *
 * @implSpec
 * This interfbce plbces no restrictions on the mutbbility of implementbtions,
 * however immutbbility is strongly recommended.
 * All implementbtions must be {@link Compbrbble}.
 *
 * @since 1.8
 */
public interfbce Temporbl extends TemporblAccessor {

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to, or subtrbcted from, this dbte-time.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     *
     * @implSpec
     * Implementbtions must check bnd hbndle bll units defined in {@link ChronoUnit}.
     * If the unit is supported, then true must be returned, otherwise fblse must be returned.
     * <p>
     * If the field is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.isSupportedBy(Temporbl)}
     * pbssing {@code this} bs the brgument.
     * <p>
     * Implementbtions must ensure thbt no observbble stbte is bltered when this
     * rebd-only method is invoked.
     *
     * @pbrbm unit  the unit to check, null returns fblse
     * @return true if the unit cbn be bdded/subtrbcted, fblse if not
     */
    boolebn isSupported(TemporblUnit unit);

    /**
     * Returns bn bdjusted object of the sbme type bs this object with the bdjustment mbde.
     * <p>
     * This bdjusts this dbte-time bccording to the rules of the specified bdjuster.
     * A simple bdjuster might simply set the one of the fields, such bs the yebr field.
     * A more complex bdjuster might set the dbte to the lbst dby of the month.
     * A selection of common bdjustments is provided in
     * {@link jbvb.time.temporbl.TemporblAdjusters TemporblAdjusters}.
     * These include finding the "lbst dby of the month" bnd "next Wednesdby".
     * The bdjuster is responsible for hbndling specibl cbses, such bs the vbrying
     * lengths of month bnd lebp yebrs.
     * <p>
     * Some exbmple code indicbting how bnd why this method is used:
     * <pre>
     *  dbte = dbte.with(Month.JULY);        // most key clbsses implement TemporblAdjuster
     *  dbte = dbte.with(lbstDbyOfMonth());  // stbtic import from Adjusters
     *  dbte = dbte.with(next(WEDNESDAY));   // stbtic import from Adjusters bnd DbyOfWeek
     * </pre>
     *
     * @implSpec
     * <p>
     * Implementbtions must not blter either this object or the specified temporbl object.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble implementbtions.
     * <p>
     * The defbult implementbtion must behbve equivblent to this code:
     * <pre>
     *  return bdjuster.bdjustInto(this);
     * </pre>
     *
     * @pbrbm bdjuster  the bdjuster to use, not null
     * @return bn object of the sbme type with the specified bdjustment mbde, not null
     * @throws DbteTimeException if unbble to mbke the bdjustment
     * @throws ArithmeticException if numeric overflow occurs
     */
    defbult Temporbl with(TemporblAdjuster bdjuster) {
        return bdjuster.bdjustInto(this);
    }

    /**
     * Returns bn object of the sbme type bs this object with the specified field bltered.
     * <p>
     * This returns b new object bbsed on this one with the vblue for the specified field chbnged.
     * For exbmple, on b {@code LocblDbte}, this could be used to set the yebr, month or dby-of-month.
     * The returned object will hbve the sbme observbble type bs this object.
     * <p>
     * In some cbses, chbnging b field is not fully defined. For exbmple, if the tbrget object is
     * b dbte representing the 31st Jbnubry, then chbnging the month to Februbry would be unclebr.
     * In cbses like this, the field is responsible for resolving the result. Typicblly it will choose
     * the previous vblid dbte, which would be the lbst vblid dby of Februbry in this exbmple.
     *
     * @implSpec
     * Implementbtions must check bnd hbndle bll fields defined in {@link ChronoField}.
     * If the field is supported, then the bdjustment must be performed.
     * If unsupported, then bn {@code UnsupportedTemporblTypeException} must be thrown.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.bdjustInto(Temporbl, long)}
     * pbssing {@code this} bs the first brgument.
     * <p>
     * Implementbtions must not blter this object.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble implementbtions.
     *
     * @pbrbm field  the field to set in the result, not null
     * @pbrbm newVblue  the new vblue of the field in the result
     * @return bn object of the sbme type with the specified field set, not null
     * @throws DbteTimeException if the field cbnnot be set
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    Temporbl with(TemporblField field, long newVblue);

    //-----------------------------------------------------------------------
    /**
     * Returns bn object of the sbme type bs this object with bn bmount bdded.
     * <p>
     * This bdjusts this temporbl, bdding bccording to the rules of the specified bmount.
     * The bmount is typicblly b {@link jbvb.time.Period} but mby be bny other type implementing
     * the {@link TemporblAmount} interfbce, such bs {@link jbvb.time.Durbtion}.
     * <p>
     * Some exbmple code indicbting how bnd why this method is used:
     * <pre>
     *  dbte = dbte.plus(period);                // bdd b Period instbnce
     *  dbte = dbte.plus(durbtion);              // bdd b Durbtion instbnce
     *  dbte = dbte.plus(workingDbys(6));        // exbmple user-written workingDbys method
     * </pre>
     * <p>
     * Note thbt cblling {@code plus} followed by {@code minus} is not gubrbnteed to
     * return the sbme dbte-time.
     *
     * @implSpec
     * <p>
     * Implementbtions must not blter either this object or the specified temporbl object.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble implementbtions.
     * <p>
     * The defbult implementbtion must behbve equivblent to this code:
     * <pre>
     *  return bmount.bddTo(this);
     * </pre>
     *
     * @pbrbm bmount  the bmount to bdd, not null
     * @return bn object of the sbme type with the specified bdjustment mbde, not null
     * @throws DbteTimeException if the bddition cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    defbult Temporbl plus(TemporblAmount bmount) {
        return bmount.bddTo(this);
    }

    /**
     * Returns bn object of the sbme type bs this object with the specified period bdded.
     * <p>
     * This method returns b new object bbsed on this one with the specified period bdded.
     * For exbmple, on b {@code LocblDbte}, this could be used to bdd b number of yebrs, months or dbys.
     * The returned object will hbve the sbme observbble type bs this object.
     * <p>
     * In some cbses, chbnging b field is not fully defined. For exbmple, if the tbrget object is
     * b dbte representing the 31st Jbnubry, then bdding one month would be unclebr.
     * In cbses like this, the field is responsible for resolving the result. Typicblly it will choose
     * the previous vblid dbte, which would be the lbst vblid dby of Februbry in this exbmple.
     *
     * @implSpec
     * Implementbtions must check bnd hbndle bll units defined in {@link ChronoUnit}.
     * If the unit is supported, then the bddition must be performed.
     * If unsupported, then bn {@code UnsupportedTemporblTypeException} must be thrown.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.bddTo(Temporbl, long)}
     * pbssing {@code this} bs the first brgument.
     * <p>
     * Implementbtions must not blter this object.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble implementbtions.
     *
     * @pbrbm bmountToAdd  the bmount of the specified unit to bdd, mby be negbtive
     * @pbrbm unit  the unit of the bmount to bdd, not null
     * @return bn object of the sbme type with the specified period bdded, not null
     * @throws DbteTimeException if the unit cbnnot be bdded
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    Temporbl plus(long bmountToAdd, TemporblUnit unit);

    //-----------------------------------------------------------------------
    /**
     * Returns bn object of the sbme type bs this object with bn bmount subtrbcted.
     * <p>
     * This bdjusts this temporbl, subtrbcting bccording to the rules of the specified bmount.
     * The bmount is typicblly b {@link jbvb.time.Period} but mby be bny other type implementing
     * the {@link TemporblAmount} interfbce, such bs {@link jbvb.time.Durbtion}.
     * <p>
     * Some exbmple code indicbting how bnd why this method is used:
     * <pre>
     *  dbte = dbte.minus(period);               // subtrbct b Period instbnce
     *  dbte = dbte.minus(durbtion);             // subtrbct b Durbtion instbnce
     *  dbte = dbte.minus(workingDbys(6));       // exbmple user-written workingDbys method
     * </pre>
     * <p>
     * Note thbt cblling {@code plus} followed by {@code minus} is not gubrbnteed to
     * return the sbme dbte-time.
     *
     * @implSpec
     * <p>
     * Implementbtions must not blter either this object or the specified temporbl object.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble implementbtions.
     * <p>
     * The defbult implementbtion must behbve equivblent to this code:
     * <pre>
     *  return bmount.subtrbctFrom(this);
     * </pre>
     *
     * @pbrbm bmount  the bmount to subtrbct, not null
     * @return bn object of the sbme type with the specified bdjustment mbde, not null
     * @throws DbteTimeException if the subtrbction cbnnot be mbde
     * @throws ArithmeticException if numeric overflow occurs
     */
    defbult Temporbl minus(TemporblAmount bmount) {
        return bmount.subtrbctFrom(this);
    }

    /**
     * Returns bn object of the sbme type bs this object with the specified period subtrbcted.
     * <p>
     * This method returns b new object bbsed on this one with the specified period subtrbcted.
     * For exbmple, on b {@code LocblDbte}, this could be used to subtrbct b number of yebrs, months or dbys.
     * The returned object will hbve the sbme observbble type bs this object.
     * <p>
     * In some cbses, chbnging b field is not fully defined. For exbmple, if the tbrget object is
     * b dbte representing the 31st Mbrch, then subtrbcting one month would be unclebr.
     * In cbses like this, the field is responsible for resolving the result. Typicblly it will choose
     * the previous vblid dbte, which would be the lbst vblid dby of Februbry in this exbmple.
     *
     * @implSpec
     * Implementbtions must behbve in b mbnor equivblent to the defbult method behbvior.
     * <p>
     * Implementbtions must not blter this object.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble implementbtions.
     * <p>
     * The defbult implementbtion must behbve equivblent to this code:
     * <pre>
     *  return (bmountToSubtrbct == Long.MIN_VALUE ?
     *      plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
     * </pre>
     *
     * @pbrbm bmountToSubtrbct  the bmount of the specified unit to subtrbct, mby be negbtive
     * @pbrbm unit  the unit of the bmount to subtrbct, not null
     * @return bn object of the sbme type with the specified period subtrbcted, not null
     * @throws DbteTimeException if the unit cbnnot be subtrbcted
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    defbult Temporbl minus(long bmountToSubtrbct, TemporblUnit unit) {
        return (bmountToSubtrbct == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit) : plus(-bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Cblculbtes the bmount of time until bnother temporbl in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two temporbl objects
     * in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified temporbl.
     * The end point is converted to be of the sbme type bs the stbrt point if different.
     * The result will be negbtive if the end is before the stbrt.
     * For exbmple, the bmount in hours between two temporbl objects cbn be
     * cblculbted using {@code stbrtTime.until(endTime, HOURS)}.
     * <p>
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two temporbls.
     * For exbmple, the bmount in hours between the times 11:30 bnd 13:29
     * will only be one hour bs it is one minute short of two hours.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link TemporblUnit#between(Temporbl, Temporbl)}:
     * <pre>
     *   // these two lines bre equivblent
     *   temporbl = stbrt.until(end, unit);
     *   temporbl = unit.between(stbrt, end);
     * </pre>
     * The choice should be mbde bbsed on which mbkes the code more rebdbble.
     * <p>
     * For exbmple, this method bllows the number of dbys between two dbtes to
     * be cblculbted:
     * <pre>
     *  long dbysBetween = stbrt.until(end, DAYS);
     *  // or blternbtively
     *  long dbysBetween = DAYS.between(stbrt, end);
     * </pre>
     *
     * @implSpec
     * Implementbtions must begin by checking to ensure thbt the input temporbl
     * object is of the sbme observbble type bs the implementbtion.
     * They must then perform the cblculbtion for bll instbnces of {@link ChronoUnit}.
     * An {@code UnsupportedTemporblTypeException} must be thrown for {@code ChronoUnit}
     * instbnces thbt bre unsupported.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.between(Temporbl, Temporbl)}
     * pbssing {@code this} bs the first brgument bnd the converted input temporbl bs
     * the second brgument.
     * <p>
     * In summbry, implementbtions must behbve in b mbnner equivblent to this pseudo-code:
     * <pre>
     *  // convert the end temporbl to the sbme type bs this clbss
     *  if (unit instbnceof ChronoUnit) {
     *    // if unit is supported, then cblculbte bnd return result
     *    // else throw UnsupportedTemporblTypeException for unsupported units
     *  }
     *  return unit.between(this, convertedEndTemporbl);
     * </pre>
     * <p>
     * Note thbt the unit's {@code between} method must only be invoked if the
     * two temporbl objects hbve exbctly the sbme type evblubted by {@code getClbss()}.
     * <p>
     * Implementbtions must ensure thbt no observbble stbte is bltered when this
     * rebd-only method is invoked.
     *
     * @pbrbm endExclusive  the end temporbl, exclusive, converted to be of the
     *  sbme type bs this object, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this temporbl object bnd the specified one
     *  in terms of the unit; positive if the specified object is lbter thbn this one,
     *  negbtive if it is ebrlier thbn this one
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to the sbme type bs this temporbl
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    long until(Temporbl endExclusive, TemporblUnit unit);

}
