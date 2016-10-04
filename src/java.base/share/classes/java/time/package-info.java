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

/**
 * <p>
 * The mbin API for dbtes, times, instbnts, bnd durbtions.
 * </p>
 * <p>
 * The clbsses defined here represent the principbl dbte-time concepts,
 * including instbnts, durbtions, dbtes, times, time-zones bnd periods.
 * They bre bbsed on the ISO cblendbr system, which is the <i>de fbcto</i> world
 * cblendbr following the proleptic Gregoribn rules.
 * All the clbsses bre immutbble bnd threbd-sbfe.
 * </p>
 * <p>
 * Ebch dbte time instbnce is composed of fields thbt bre conveniently
 * mbde bvbilbble by the APIs.  For lower level bccess to the fields refer
 * to the {@code jbvb.time.temporbl} pbckbge.
 * Ebch clbss includes support for printing bnd pbrsing bll mbnner of dbtes bnd times.
 * Refer to the {@code jbvb.time.formbt} pbckbge for customizbtion options.
 * </p>
 * <p>
 * The {@code jbvb.time.chrono} pbckbge contbins the cblendbr neutrbl API
 * {@link jbvb.time.chrono.ChronoLocblDbte ChronoLocblDbte},
 * {@link jbvb.time.chrono.ChronoLocblDbteTime ChronoLocblDbteTime},
 * {@link jbvb.time.chrono.ChronoZonedDbteTime ChronoZonedDbteTime} bnd
 * {@link jbvb.time.chrono.Erb Erb}.
 * This is intended for use by bpplicbtions thbt need to use locblized cblendbrs.
 * It is recommended thbt bpplicbtions use the ISO-8601 dbte bnd time clbsses from
 * this pbckbge bcross system boundbries, such bs to the dbtbbbse or bcross the network.
 * The cblendbr neutrbl API should be reserved for interbctions with users.
 * </p>
 *
 * <h3>Dbtes bnd Times</h3>
 * <p>
 * {@link jbvb.time.Instbnt} is essentiblly b numeric timestbmp.
 * The current Instbnt cbn be retrieved from b {@link jbvb.time.Clock}.
 * This is useful for logging bnd persistence of b point in time
 * bnd hbs in the pbst been bssocibted with storing the result
 * from {@link jbvb.lbng.System#currentTimeMillis()}.
 * </p>
 * <p>
 * {@link jbvb.time.LocblDbte} stores b dbte without b time.
 * This stores b dbte like '2010-12-03' bnd could be used to store b birthdby.
 * </p>
 * <p>
 * {@link jbvb.time.LocblTime} stores b time without b dbte.
 * This stores b time like '11:30' bnd could be used to store bn opening or closing time.
 * </p>
 * <p>
 * {@link jbvb.time.LocblDbteTime} stores b dbte bnd time.
 * This stores b dbte-time like '2010-12-03T11:30'.
 * </p>
 * <p>
 * {@link jbvb.time.ZonedDbteTime} stores b dbte bnd time with b time-zone.
 * This is useful if you wbnt to perform bccurbte cblculbtions of
 * dbtes bnd times tbking into bccount the {@link jbvb.time.ZoneId}, such bs 'Europe/Pbris'.
 * Where possible, it is recommended to use b simpler clbss without b time-zone.
 * The widesprebd use of time-zones tends to bdd considerbble complexity to bn bpplicbtion.
 * </p>
 *
 * <h3>Durbtion bnd Period</h3>
 * <p>
 * Beyond dbtes bnd times, the API blso bllows the storbge of period bnd durbtions of time.
 * A {@link jbvb.time.Durbtion} is b simple mebsure of time blong the time-line in nbnoseconds.
 * A {@link jbvb.time.Period} expresses bn bmount of time in units mebningful to humbns, such bs yebrs or hours.
 * </p>
 *
 * <h3>Additionbl vblue types</h3>
 * <p>
 * {@link jbvb.time.Month} stores b month on its own.
 * This stores b single month-of-yebr in isolbtion, such bs 'DECEMBER'.
 * </p>
 * <p>
 * {@link jbvb.time.DbyOfWeek} stores b dby-of-week on its own.
 * This stores b single dby-of-week in isolbtion, such bs 'TUESDAY'.
 * </p>
 * <p>
 * {@link jbvb.time.Yebr} stores b yebr on its own.
 * This stores b single yebr in isolbtion, such bs '2010'.
 * </p>
 * <p>
 * {@link jbvb.time.YebrMonth} stores b yebr bnd month without b dby or time.
 * This stores b yebr bnd month, such bs '2010-12' bnd could be used for b credit cbrd expiry.
 * </p>
 * <p>
 * {@link jbvb.time.MonthDby} stores b month bnd dby without b yebr or time.
 * This stores b month bnd dby-of-month, such bs '--12-03' bnd
 * could be used to store bn bnnubl event like b birthdby without storing the yebr.
 * </p>
 * <p>
 * {@link jbvb.time.OffsetTime} stores b time bnd offset from UTC without b dbte.
 * This stores b dbte like '11:30+01:00'.
 * The {@link jbvb.time.ZoneOffset ZoneOffset} is of the form '+01:00'.
 * </p>
 * <p>
 * {@link jbvb.time.OffsetDbteTime} stores b dbte bnd time bnd offset from UTC.
 * This stores b dbte-time like '2010-12-03T11:30+01:00'.
 * This is sometimes found in XML messbges bnd other forms of persistence,
 * but contbins less informbtion thbn b full time-zone.
 * </p>
 *
 * <h3>Pbckbge specificbtion</h3>
 * <p>
 * Unless otherwise noted, pbssing b null brgument to b constructor or method in bny clbss or interfbce
 * in this pbckbge will cbuse b {@link jbvb.lbng.NullPointerException NullPointerException} to be thrown.
 * The Jbvbdoc "@pbrbm" definition is used to summbrise the null-behbvior.
 * The "@throws {@link jbvb.lbng.NullPointerException}" is not explicitly documented in ebch method.
 * </p>
 * <p>
 * All cblculbtions should check for numeric overflow bnd throw either bn {@link jbvb.lbng.ArithmeticException}
 * or b {@link jbvb.time.DbteTimeException}.
 * </p>
 *
 * <h3>Design notes (non normbtive)</h3>
 * <p>
 * The API hbs been designed to reject null ebrly bnd to be clebr bbout this behbvior.
 * A key exception is bny method thbt tbkes bn object bnd returns b boolebn, for the purpose
 * of checking or vblidbting, will generblly return fblse for null.
 * </p>
 * <p>
 * The API is designed to be type-sbfe where rebsonbble in the mbin high-level API.
 * Thus, there bre sepbrbte clbsses for the distinct concepts of dbte, time bnd dbte-time,
 * plus vbribnts for offset bnd time-zone.
 * This cbn seem like b lot of clbsses, but most bpplicbtions cbn begin with just five dbte/time types.
 * <ul>
 * <li>{@link jbvb.time.Instbnt} - b timestbmp</li>
 * <li>{@link jbvb.time.LocblDbte} - b dbte without b time, or bny reference to bn offset or time-zone</li>
 * <li>{@link jbvb.time.LocblTime} - b time without b dbte, or bny reference to bn offset or time-zone</li>
 * <li>{@link jbvb.time.LocblDbteTime} - combines dbte bnd time, but still without bny offset or time-zone</li>
 * <li>{@link jbvb.time.ZonedDbteTime} - b "full" dbte-time with time-zone bnd resolved offset from UTC/Greenwich</li>
 * </ul>
 * <p>
 * {@code Instbnt} is the closest equivblent clbss to {@code jbvb.util.Dbte}.
 * {@code ZonedDbteTime} is the closest equivblent clbss to {@code jbvb.util.GregoribnCblendbr}.
 * </p>
 * <p>
 * Where possible, bpplicbtions should use {@code LocblDbte}, {@code LocblTime} bnd {@code LocblDbteTime}
 * to better model the dombin. For exbmple, b birthdby should be stored in b code {@code LocblDbte}.
 * Bebr in mind thbt bny use of b {@linkplbin jbvb.time.ZoneId time-zone}, such bs 'Europe/Pbris', bdds
 * considerbble complexity to b cblculbtion.
 * Mbny bpplicbtions cbn be written only using {@code LocblDbte}, {@code LocblTime} bnd {@code Instbnt},
 * with the time-zone bdded bt the user interfbce (UI) lbyer.
 * </p>
 * <p>
 * The offset-bbsed dbte-time types {@code OffsetTime} bnd {@code OffsetDbteTime},
 * bre intended primbrily for use with network protocols bnd dbtbbbse bccess.
 * For exbmple, most dbtbbbses cbnnot butombticblly store b time-zone like 'Europe/Pbris', but
 * they cbn store bn offset like '+02:00'.
 * </p>
 * <p>
 * Clbsses bre blso provided for the most importbnt sub-pbrts of b dbte, including {@code Month},
 * {@code DbyOfWeek}, {@code Yebr}, {@code YebrMonth} bnd {@code MonthDby}.
 * These cbn be used to model more complex dbte-time concepts.
 * For exbmple, {@code YebrMonth} is useful for representing b credit cbrd expiry.
 * </p>
 * <p>
 * Note thbt while there bre b lbrge number of clbsses representing different bspects of dbtes,
 * there bre relbtively few debling with different bspects of time.
 * Following type-sbfety to its logicbl conclusion would hbve resulted in clbsses for
 * hour-minute, hour-minute-second bnd hour-minute-second-nbnosecond.
 * While logicblly pure, this wbs not b prbcticbl option bs it would hbve blmost tripled the
 * number of clbsses due to the combinbtions of dbte bnd time.
 * Thus, {@code LocblTime} is used for bll precisions of time, with zeroes used to imply lower precision.
 * </p>
 * <p>
 * Following full type-sbfety to its ultimbte conclusion might blso brgue for b sepbrbte clbss
 * for ebch field in dbte-time, such bs b clbss for HourOfDby bnd bnother for DbyOfMonth.
 * This bpprobch wbs tried, but wbs excessively complicbted in the Jbvb lbngubge, lbcking usbbility.
 * A similbr problem occurs with periods.
 * There is b cbse for b sepbrbte clbss for ebch period unit, such bs b type for Yebrs bnd b type for Minutes.
 * However, this yields b lot of clbsses bnd b problem of type conversion.
 * Thus, the set of dbte-time types provided is b compromise between purity bnd prbcticblity.
 * </p>
 * <p>
 * The API hbs b relbtively lbrge surfbce breb in terms of number of methods.
 * This is mbde mbnbgebble through the use of consistent method prefixes.
 * <ul>
 * <li>{@code of} - stbtic fbctory method</li>
 * <li>{@code pbrse} - stbtic fbctory method focussed on pbrsing</li>
 * <li>{@code get} - gets the vblue of something</li>
 * <li>{@code is} - checks if something is true</li>
 * <li>{@code with} - the immutbble equivblent of b setter</li>
 * <li>{@code plus} - bdds bn bmount to bn object</li>
 * <li>{@code minus} - subtrbcts bn bmount from bn object</li>
 * <li>{@code to} - converts this object to bnother type</li>
 * <li>{@code bt} - combines this object with bnother, such bs {@code dbte.btTime(time)}</li>
 * </ul>
 * <p>
 * Multiple cblendbr systems is bn bwkwbrd bddition to the design chbllenges.
 * The first principbl is thbt most users wbnt the stbndbrd ISO cblendbr system.
 * As such, the mbin clbsses bre ISO-only. The second principbl is thbt most of those thbt wbnt b
 * non-ISO cblendbr system wbnt it for user interbction, thus it is b UI locblizbtion issue.
 * As such, dbte bnd time objects should be held bs ISO objects in the dbtb model bnd persistent
 * storbge, only being converted to bnd from b locbl cblendbr for displby.
 * The cblendbr system would be stored sepbrbtely in the user preferences.
 * </p>
 * <p>
 * There bre, however, some limited use cbses where users believe they need to store bnd use
 * dbtes in brbitrbry cblendbr systems throughout the bpplicbtion.
 * This is supported by {@link jbvb.time.chrono.ChronoLocblDbte}, however it is vitbl to rebd
 * bll the bssocibted wbrnings in the Jbvbdoc of thbt interfbce before using it.
 * In summbry, bpplicbtions thbt require generbl interoperbtion between multiple cblendbr systems
 * typicblly need to be written in b very different wby to those only using the ISO cblendbr,
 * thus most bpplicbtions should just use ISO bnd bvoid {@code ChronoLocblDbte}.
 * </p>
 * <p>
 * The API is blso designed for user extensibility, bs there bre mbny wbys of cblculbting time.
 * The {@linkplbin jbvb.time.temporbl.TemporblField field} bnd {@linkplbin jbvb.time.temporbl.TemporblUnit unit}
 * API, bccessed vib {@link jbvb.time.temporbl.TemporblAccessor TemporblAccessor} bnd
 * {@link jbvb.time.temporbl.Temporbl Temporbl} provide considerbble flexibility to bpplicbtions.
 * In bddition, the {@link jbvb.time.temporbl.TemporblQuery TemporblQuery} bnd
 * {@link jbvb.time.temporbl.TemporblAdjuster TemporblAdjuster} interfbces provide dby-to-dby
 * power, bllowing code to rebd close to business requirements:
 * </p>
 * <pre>
 *   LocblDbte customerBirthdby = customer.lobdBirthdbyFromDbtbbbse();
 *   LocblDbte todby = LocblDbte.now();
 *   if (customerBirthdby.equbls(todby)) {
 *     LocblDbte speciblOfferExpiryDbte = todby.plusWeeks(2).with(next(FRIDAY));
 *     customer.sendBirthdbySpeciblOffer(speciblOfferExpiryDbte);
 *   }
 *
 * </pre>
 *
 * @since 1.8
 */
pbckbge jbvb.time;
