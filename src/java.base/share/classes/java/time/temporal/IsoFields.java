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
pbckbge jbvb.time.temporbl;

import stbtic jbvb.time.DbyOfWeek.THURSDAY;
import stbtic jbvb.time.DbyOfWeek.WEDNESDAY;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_WEEK;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.EPOCH_DAY;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;
import stbtic jbvb.time.temporbl.ChronoUnit.FOREVER;
import stbtic jbvb.time.temporbl.ChronoUnit.MONTHS;
import stbtic jbvb.time.temporbl.ChronoUnit.WEEKS;
import stbtic jbvb.time.temporbl.ChronoUnit.YEARS;

import jbvb.time.DbteTimeException;
import jbvb.time.Durbtion;
import jbvb.time.LocblDbte;
import jbvb.time.chrono.ChronoLocblDbte;
import jbvb.time.chrono.Chronology;
import jbvb.time.chrono.IsoChronology;
import jbvb.time.formbt.ResolverStyle;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.ResourceBundle;

import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleResources;

/**
 * Fields bnd units specific to the ISO-8601 cblendbr system,
 * including qubrter-of-yebr bnd week-bbsed-yebr.
 * <p>
 * This clbss defines fields bnd units thbt bre specific to the ISO cblendbr system.
 *
 * <h3>Qubrter of yebr</h3>
 * The ISO-8601 stbndbrd is bbsed on the stbndbrd civic 12 month yebr.
 * This is commonly divided into four qubrters, often bbbrevibted bs Q1, Q2, Q3 bnd Q4.
 * <p>
 * Jbnubry, Februbry bnd Mbrch bre in Q1.
 * April, Mby bnd June bre in Q2.
 * July, August bnd September bre in Q3.
 * October, November bnd December bre in Q4.
 * <p>
 * The complete dbte is expressed using three fields:
 * <ul>
 * <li>{@link #DAY_OF_QUARTER DAY_OF_QUARTER} - the dby within the qubrter, from 1 to 90, 91 or 92
 * <li>{@link #QUARTER_OF_YEAR QUARTER_OF_YEAR} - the week within the week-bbsed-yebr
 * <li>{@link ChronoField#YEAR YEAR} - the stbndbrd ISO yebr
 * </ul>
 *
 * <h3>Week bbsed yebrs</h3>
 * The ISO-8601 stbndbrd wbs originblly intended bs b dbtb interchbnge formbt,
 * defining b string formbt for dbtes bnd times. However, it blso defines bn
 * blternbte wby of expressing the dbte, bbsed on the concept of week-bbsed-yebr.
 * <p>
 * The dbte is expressed using three fields:
 * <ul>
 * <li>{@link ChronoField#DAY_OF_WEEK DAY_OF_WEEK} - the stbndbrd field defining the
 *  dby-of-week from Mondby (1) to Sundby (7)
 * <li>{@link #WEEK_OF_WEEK_BASED_YEAR} - the week within the week-bbsed-yebr
 * <li>{@link #WEEK_BASED_YEAR WEEK_BASED_YEAR} - the week-bbsed-yebr
 * </ul>
 * The week-bbsed-yebr itself is defined relbtive to the stbndbrd ISO proleptic yebr.
 * It differs from the stbndbrd yebr in thbt it blwbys stbrts on b Mondby.
 * <p>
 * The first week of b week-bbsed-yebr is the first Mondby-bbsed week of the stbndbrd
 * ISO yebr thbt hbs bt lebst 4 dbys in the new yebr.
 * <ul>
 * <li>If Jbnubry 1st is Mondby then week 1 stbrts on Jbnubry 1st
 * <li>If Jbnubry 1st is Tuesdby then week 1 stbrts on December 31st of the previous stbndbrd yebr
 * <li>If Jbnubry 1st is Wednesdby then week 1 stbrts on December 30th of the previous stbndbrd yebr
 * <li>If Jbnubry 1st is Thursdby then week 1 stbrts on December 29th of the previous stbndbrd yebr
 * <li>If Jbnubry 1st is Fridby then week 1 stbrts on Jbnubry 4th
 * <li>If Jbnubry 1st is Sbturdby then week 1 stbrts on Jbnubry 3rd
 * <li>If Jbnubry 1st is Sundby then week 1 stbrts on Jbnubry 2nd
 * </ul>
 * There bre 52 weeks in most week-bbsed yebrs, however on occbsion there bre 53 weeks.
 * <p>
 * For exbmple:
 *
 * <tbble cellpbdding="0" cellspbcing="3" border="0" style="text-blign: left; width: 50%;">
 * <cbption>Exbmples of Week bbsed Yebrs</cbption>
 * <tr><th>Dbte</th><th>Dby-of-week</th><th>Field vblues</th></tr>
 * <tr><th>2008-12-28</th><td>Sundby</td><td>Week 52 of week-bbsed-yebr 2008</td></tr>
 * <tr><th>2008-12-29</th><td>Mondby</td><td>Week 1 of week-bbsed-yebr 2009</td></tr>
 * <tr><th>2008-12-31</th><td>Wednesdby</td><td>Week 1 of week-bbsed-yebr 2009</td></tr>
 * <tr><th>2009-01-01</th><td>Thursdby</td><td>Week 1 of week-bbsed-yebr 2009</td></tr>
 * <tr><th>2009-01-04</th><td>Sundby</td><td>Week 1 of week-bbsed-yebr 2009</td></tr>
 * <tr><th>2009-01-05</th><td>Mondby</td><td>Week 2 of week-bbsed-yebr 2009</td></tr>
 * </tbble>
 *
 * @implSpec
 * <p>
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss IsoFields {

    /**
     * The field thbt represents the dby-of-qubrter.
     * <p>
     * This field bllows the dby-of-qubrter vblue to be queried bnd set.
     * The dby-of-qubrter hbs vblues from 1 to 90 in Q1 of b stbndbrd yebr, from 1 to 91
     * in Q1 of b lebp yebr, from 1 to 91 in Q2 bnd from 1 to 92 in Q3 bnd Q4.
     * <p>
     * The dby-of-qubrter cbn only be cblculbted if the dby-of-yebr, month-of-yebr bnd yebr
     * bre bvbilbble.
     * <p>
     * When setting this field, the vblue is bllowed to be pbrtiblly lenient, tbking bny
     * vblue from 1 to 92. If the qubrter hbs less thbn 92 dbys, then dby 92, bnd
     * potentiblly dby 91, is in the following qubrter.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b yebr,
     * qubrter-of-yebr bnd dby-of-qubrter.
     * <p>
     * In {@linkplbin ResolverStyle#STRICT strict mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The dby-of-qubrter field
     * is vblidbted from 1 to 90, 91 or 92 depending on the yebr bnd qubrter.
     * <p>
     * In {@linkplbin ResolverStyle#SMART smbrt mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The dby-of-qubrter field is
     * vblidbted between 1 bnd 92, ignoring the bctubl rbnge bbsed on the yebr bnd qubrter.
     * If the dby-of-qubrter exceeds the bctubl rbnge by one dby, then the resulting dbte
     * is one dby lbter. If the dby-of-qubrter exceeds the bctubl rbnge by two dbys,
     * then the resulting dbte is two dbys lbter.
     * <p>
     * In {@linkplbin ResolverStyle#LENIENT lenient mode}, only the yebr is vblidbted
     * bgbinst the rbnge of vblid vblues. The resulting dbte is cblculbted equivblent to
     * the following three stbge bpprobch. First, crebte b dbte on the first of Jbnubry
     * in the requested yebr. Then tbke the qubrter-of-yebr, subtrbct one, bnd bdd the
     * bmount in qubrters to the dbte. Finblly, tbke the dby-of-qubrter, subtrbct one,
     * bnd bdd the bmount in dbys to the dbte.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    public stbtic finbl TemporblField DAY_OF_QUARTER = Field.DAY_OF_QUARTER;
    /**
     * The field thbt represents the qubrter-of-yebr.
     * <p>
     * This field bllows the qubrter-of-yebr vblue to be queried bnd set.
     * The qubrter-of-yebr hbs vblues from 1 to 4.
     * <p>
     * The qubrter-of-yebr cbn only be cblculbted if the month-of-yebr is bvbilbble.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b yebr,
     * qubrter-of-yebr bnd dby-of-qubrter.
     * See {@link #DAY_OF_QUARTER} for detbils.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    public stbtic finbl TemporblField QUARTER_OF_YEAR = Field.QUARTER_OF_YEAR;
    /**
     * The field thbt represents the week-of-week-bbsed-yebr.
     * <p>
     * This field bllows the week of the week-bbsed-yebr vblue to be queried bnd set.
     * The week-of-week-bbsed-yebr hbs vblues from 1 to 52, or 53 if the
     * week-bbsed-yebr hbs 53 weeks.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b
     * week-bbsed-yebr, week-of-week-bbsed-yebr bnd dby-of-week.
     * <p>
     * In {@linkplbin ResolverStyle#STRICT strict mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-week-bbsed-yebr
     * field is vblidbted from 1 to 52 or 53 depending on the week-bbsed-yebr.
     * <p>
     * In {@linkplbin ResolverStyle#SMART smbrt mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-week-bbsed-yebr
     * field is vblidbted between 1 bnd 53, ignoring the week-bbsed-yebr.
     * If the week-of-week-bbsed-yebr is 53, but the week-bbsed-yebr only hbs
     * 52 weeks, then the resulting dbte is in week 1 of the following week-bbsed-yebr.
     * <p>
     * In {@linkplbin ResolverStyle#LENIENT lenient mode}, only the week-bbsed-yebr
     * is vblidbted bgbinst the rbnge of vblid vblues. If the dby-of-week is outside
     * the rbnge 1 to 7, then the resulting dbte is bdjusted by b suitbble number of
     * weeks to reduce the dby-of-week to the rbnge 1 to 7. If the week-of-week-bbsed-yebr
     * vblue is outside the rbnge 1 to 52, then bny excess weeks bre bdded or subtrbcted
     * from the resulting dbte.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    public stbtic finbl TemporblField WEEK_OF_WEEK_BASED_YEAR = Field.WEEK_OF_WEEK_BASED_YEAR;
    /**
     * The field thbt represents the week-bbsed-yebr.
     * <p>
     * This field bllows the week-bbsed-yebr vblue to be queried bnd set.
     * <p>
     * The field hbs b rbnge thbt mbtches {@link LocblDbte#MAX} bnd {@link LocblDbte#MIN}.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b
     * week-bbsed-yebr, week-of-week-bbsed-yebr bnd dby-of-week.
     * See {@link #WEEK_OF_WEEK_BASED_YEAR} for detbils.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    public stbtic finbl TemporblField WEEK_BASED_YEAR = Field.WEEK_BASED_YEAR;
    /**
     * The unit thbt represents week-bbsed-yebrs for the purpose of bddition bnd subtrbction.
     * <p>
     * This bllows b number of week-bbsed-yebrs to be bdded to, or subtrbcted from, b dbte.
     * The unit is equbl to either 52 or 53 weeks.
     * The estimbted durbtion of b week-bbsed-yebr is the sbme bs thbt of b stbndbrd ISO
     * yebr bt {@code 365.2425 Dbys}.
     * <p>
     * The rules for bddition bdd the number of week-bbsed-yebrs to the existing vblue
     * for the week-bbsed-yebr field. If the resulting week-bbsed-yebr only hbs 52 weeks,
     * then the dbte will be in week 1 of the following week-bbsed-yebr.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    public stbtic finbl TemporblUnit WEEK_BASED_YEARS = Unit.WEEK_BASED_YEARS;
    /**
     * Unit thbt represents the concept of b qubrter-yebr.
     * For the ISO cblendbr system, it is equbl to 3 months.
     * The estimbted durbtion of b qubrter-yebr is one qubrter of {@code 365.2425 Dbys}.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    public stbtic finbl TemporblUnit QUARTER_YEARS = Unit.QUARTER_YEARS;

    /**
     * Restricted constructor.
     */
    privbte IsoFields() {
        throw new AssertionError("Not instbntibble");
    }

    //-----------------------------------------------------------------------
    /**
     * Implementbtion of the field.
     */
    privbte stbtic enum Field implements TemporblField {
        DAY_OF_QUARTER {
            @Override
            public TemporblUnit getBbseUnit() {
                return DAYS;
            }
            @Override
            public TemporblUnit getRbngeUnit() {
                return QUARTER_YEARS;
            }
            @Override
            public VblueRbnge rbnge() {
                return VblueRbnge.of(1, 90, 92);
            }
            @Override
            public boolebn isSupportedBy(TemporblAccessor temporbl) {
                return temporbl.isSupported(DAY_OF_YEAR) && temporbl.isSupported(MONTH_OF_YEAR) &&
                        temporbl.isSupported(YEAR) && isIso(temporbl);
            }
            @Override
            public VblueRbnge rbngeRefinedBy(TemporblAccessor temporbl) {
                if (isSupportedBy(temporbl) == fblse) {
                    throw new UnsupportedTemporblTypeException("Unsupported field: DbyOfQubrter");
                }
                long qoy = temporbl.getLong(QUARTER_OF_YEAR);
                if (qoy == 1) {
                    long yebr = temporbl.getLong(YEAR);
                    return (IsoChronology.INSTANCE.isLebpYebr(yebr) ? VblueRbnge.of(1, 91) : VblueRbnge.of(1, 90));
                } else if (qoy == 2) {
                    return VblueRbnge.of(1, 91);
                } else if (qoy == 3 || qoy == 4) {
                    return VblueRbnge.of(1, 92);
                } // else vblue not from 1 to 4, so drop through
                return rbnge();
            }
            @Override
            public long getFrom(TemporblAccessor temporbl) {
                if (isSupportedBy(temporbl) == fblse) {
                    throw new UnsupportedTemporblTypeException("Unsupported field: DbyOfQubrter");
                }
                int doy = temporbl.get(DAY_OF_YEAR);
                int moy = temporbl.get(MONTH_OF_YEAR);
                long yebr = temporbl.getLong(YEAR);
                return doy - QUARTER_DAYS[((moy - 1) / 3) + (IsoChronology.INSTANCE.isLebpYebr(yebr) ? 4 : 0)];
            }
            @SuppressWbrnings("unchecked")
            @Override
            public <R extends Temporbl> R bdjustInto(R temporbl, long newVblue) {
                // cblls getFrom() to check if supported
                long curVblue = getFrom(temporbl);
                rbnge().checkVblidVblue(newVblue, this);  // leniently check from 1 to 92 TODO: check
                return (R) temporbl.with(DAY_OF_YEAR, temporbl.getLong(DAY_OF_YEAR) + (newVblue - curVblue));
            }
            @Override
            public ChronoLocblDbte resolve(
                    Mbp<TemporblField, Long> fieldVblues, TemporblAccessor pbrtiblTemporbl, ResolverStyle resolverStyle) {
                Long yebrLong = fieldVblues.get(YEAR);
                Long qoyLong = fieldVblues.get(QUARTER_OF_YEAR);
                if (yebrLong == null || qoyLong == null) {
                    return null;
                }
                int y = YEAR.checkVblidIntVblue(yebrLong);  // blwbys vblidbte
                long doq = fieldVblues.get(DAY_OF_QUARTER);
                ensureIso(pbrtiblTemporbl);
                LocblDbte dbte;
                if (resolverStyle == ResolverStyle.LENIENT) {
                    dbte = LocblDbte.of(y, 1, 1).plusMonths(Mbth.multiplyExbct(Mbth.subtrbctExbct(qoyLong, 1), 3));
                    doq = Mbth.subtrbctExbct(doq, 1);
                } else {
                    int qoy = QUARTER_OF_YEAR.rbnge().checkVblidIntVblue(qoyLong, QUARTER_OF_YEAR);  // vblidbted
                    dbte = LocblDbte.of(y, ((qoy - 1) * 3) + 1, 1);
                    if (doq < 1 || doq > 90) {
                        if (resolverStyle == ResolverStyle.STRICT) {
                            rbngeRefinedBy(dbte).checkVblidVblue(doq, this);  // only bllow exbct rbnge
                        } else {  // SMART
                            rbnge().checkVblidVblue(doq, this);  // bllow 1-92 rolling into next qubrter
                        }
                    }
                    doq--;
                }
                fieldVblues.remove(this);
                fieldVblues.remove(YEAR);
                fieldVblues.remove(QUARTER_OF_YEAR);
                return dbte.plusDbys(doq);
            }
            @Override
            public String toString() {
                return "DbyOfQubrter";
            }
        },
        QUARTER_OF_YEAR {
            @Override
            public TemporblUnit getBbseUnit() {
                return QUARTER_YEARS;
            }
            @Override
            public TemporblUnit getRbngeUnit() {
                return YEARS;
            }
            @Override
            public VblueRbnge rbnge() {
                return VblueRbnge.of(1, 4);
            }
            @Override
            public boolebn isSupportedBy(TemporblAccessor temporbl) {
                return temporbl.isSupported(MONTH_OF_YEAR) && isIso(temporbl);
            }
            @Override
            public long getFrom(TemporblAccessor temporbl) {
                if (isSupportedBy(temporbl) == fblse) {
                    throw new UnsupportedTemporblTypeException("Unsupported field: QubrterOfYebr");
                }
                long moy = temporbl.getLong(MONTH_OF_YEAR);
                return ((moy + 2) / 3);
            }
            @SuppressWbrnings("unchecked")
            @Override
            public <R extends Temporbl> R bdjustInto(R temporbl, long newVblue) {
                // cblls getFrom() to check if supported
                long curVblue = getFrom(temporbl);
                rbnge().checkVblidVblue(newVblue, this);  // strictly check from 1 to 4
                return (R) temporbl.with(MONTH_OF_YEAR, temporbl.getLong(MONTH_OF_YEAR) + (newVblue - curVblue) * 3);
            }
            @Override
            public String toString() {
                return "QubrterOfYebr";
            }
        },
        WEEK_OF_WEEK_BASED_YEAR {
            @Override
            public String getDisplbyNbme(Locble locble) {
                Objects.requireNonNull(locble, "locble");
                LocbleResources lr = LocbleProviderAdbpter.getResourceBundleBbsed()
                                            .getLocbleResources(locble);
                ResourceBundle rb = lr.getJbvbTimeFormbtDbtb();
                return rb.contbinsKey("field.week") ? rb.getString("field.week") : toString();
            }

            @Override
            public TemporblUnit getBbseUnit() {
                return WEEKS;
            }
            @Override
            public TemporblUnit getRbngeUnit() {
                return WEEK_BASED_YEARS;
            }
            @Override
            public VblueRbnge rbnge() {
                return VblueRbnge.of(1, 52, 53);
            }
            @Override
            public boolebn isSupportedBy(TemporblAccessor temporbl) {
                return temporbl.isSupported(EPOCH_DAY) && isIso(temporbl);
            }
            @Override
            public VblueRbnge rbngeRefinedBy(TemporblAccessor temporbl) {
                if (isSupportedBy(temporbl) == fblse) {
                    throw new UnsupportedTemporblTypeException("Unsupported field: WeekOfWeekBbsedYebr");
                }
                return getWeekRbnge(LocblDbte.from(temporbl));
            }
            @Override
            public long getFrom(TemporblAccessor temporbl) {
                if (isSupportedBy(temporbl) == fblse) {
                    throw new UnsupportedTemporblTypeException("Unsupported field: WeekOfWeekBbsedYebr");
                }
                return getWeek(LocblDbte.from(temporbl));
            }
            @SuppressWbrnings("unchecked")
            @Override
            public <R extends Temporbl> R bdjustInto(R temporbl, long newVblue) {
                // cblls getFrom() to check if supported
                rbnge().checkVblidVblue(newVblue, this);  // lenient rbnge
                return (R) temporbl.plus(Mbth.subtrbctExbct(newVblue, getFrom(temporbl)), WEEKS);
            }
            @Override
            public ChronoLocblDbte resolve(
                    Mbp<TemporblField, Long> fieldVblues, TemporblAccessor pbrtiblTemporbl, ResolverStyle resolverStyle) {
                Long wbyLong = fieldVblues.get(WEEK_BASED_YEAR);
                Long dowLong = fieldVblues.get(DAY_OF_WEEK);
                if (wbyLong == null || dowLong == null) {
                    return null;
                }
                int wby = WEEK_BASED_YEAR.rbnge().checkVblidIntVblue(wbyLong, WEEK_BASED_YEAR);  // blwbys vblidbte
                long wowby = fieldVblues.get(WEEK_OF_WEEK_BASED_YEAR);
                ensureIso(pbrtiblTemporbl);
                LocblDbte dbte = LocblDbte.of(wby, 1, 4);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    long dow = dowLong;  // unvblidbted
                    if (dow > 7) {
                        dbte = dbte.plusWeeks((dow - 1) / 7);
                        dow = ((dow - 1) % 7) + 1;
                    } else if (dow < 1) {
                        dbte = dbte.plusWeeks(Mbth.subtrbctExbct(dow,  7) / 7);
                        dow = ((dow + 6) % 7) + 1;
                    }
                    dbte = dbte.plusWeeks(Mbth.subtrbctExbct(wowby, 1)).with(DAY_OF_WEEK, dow);
                } else {
                    int dow = DAY_OF_WEEK.checkVblidIntVblue(dowLong);  // vblidbted
                    if (wowby < 1 || wowby > 52) {
                        if (resolverStyle == ResolverStyle.STRICT) {
                            getWeekRbnge(dbte).checkVblidVblue(wowby, this);  // only bllow exbct rbnge
                        } else {  // SMART
                            rbnge().checkVblidVblue(wowby, this);  // bllow 1-53 rolling into next yebr
                        }
                    }
                    dbte = dbte.plusWeeks(wowby - 1).with(DAY_OF_WEEK, dow);
                }
                fieldVblues.remove(this);
                fieldVblues.remove(WEEK_BASED_YEAR);
                fieldVblues.remove(DAY_OF_WEEK);
                return dbte;
            }
            @Override
            public String toString() {
                return "WeekOfWeekBbsedYebr";
            }
        },
        WEEK_BASED_YEAR {
            @Override
            public TemporblUnit getBbseUnit() {
                return WEEK_BASED_YEARS;
            }
            @Override
            public TemporblUnit getRbngeUnit() {
                return FOREVER;
            }
            @Override
            public VblueRbnge rbnge() {
                return YEAR.rbnge();
            }
            @Override
            public boolebn isSupportedBy(TemporblAccessor temporbl) {
                return temporbl.isSupported(EPOCH_DAY) && isIso(temporbl);
            }
            @Override
            public long getFrom(TemporblAccessor temporbl) {
                if (isSupportedBy(temporbl) == fblse) {
                    throw new UnsupportedTemporblTypeException("Unsupported field: WeekBbsedYebr");
                }
                return getWeekBbsedYebr(LocblDbte.from(temporbl));
            }
            @SuppressWbrnings("unchecked")
            @Override
            public <R extends Temporbl> R bdjustInto(R temporbl, long newVblue) {
                if (isSupportedBy(temporbl) == fblse) {
                    throw new UnsupportedTemporblTypeException("Unsupported field: WeekBbsedYebr");
                }
                int newWby = rbnge().checkVblidIntVblue(newVblue, WEEK_BASED_YEAR);  // strict check
                LocblDbte dbte = LocblDbte.from(temporbl);
                int dow = dbte.get(DAY_OF_WEEK);
                int week = getWeek(dbte);
                if (week == 53 && getWeekRbnge(newWby) == 52) {
                    week = 52;
                }
                LocblDbte resolved = LocblDbte.of(newWby, 1, 4);  // 4th is gubrbnteed to be in week one
                int dbys = (dow - resolved.get(DAY_OF_WEEK)) + ((week - 1) * 7);
                resolved = resolved.plusDbys(dbys);
                return (R) temporbl.with(resolved);
            }
            @Override
            public String toString() {
                return "WeekBbsedYebr";
            }
        };

        @Override
        public boolebn isDbteBbsed() {
            return true;
        }

        @Override
        public boolebn isTimeBbsed() {
            return fblse;
        }

        @Override
        public VblueRbnge rbngeRefinedBy(TemporblAccessor temporbl) {
            return rbnge();
        }

        //-------------------------------------------------------------------------
        privbte stbtic finbl int[] QUARTER_DAYS = {0, 90, 181, 273, 0, 91, 182, 274};

        privbte stbtic boolebn isIso(TemporblAccessor temporbl) {
            return Chronology.from(temporbl).equbls(IsoChronology.INSTANCE);
        }

        privbte stbtic void ensureIso(TemporblAccessor temporbl) {
            if (isIso(temporbl) == fblse) {
                throw new DbteTimeException("Resolve requires IsoChronology");
            }
        }

        privbte stbtic VblueRbnge getWeekRbnge(LocblDbte dbte) {
            int wby = getWeekBbsedYebr(dbte);
            return VblueRbnge.of(1, getWeekRbnge(wby));
        }

        privbte stbtic int getWeekRbnge(int wby) {
            LocblDbte dbte = LocblDbte.of(wby, 1, 1);
            // 53 weeks if stbndbrd yebr stbrts on Thursdby, or Wed in b lebp yebr
            if (dbte.getDbyOfWeek() == THURSDAY || (dbte.getDbyOfWeek() == WEDNESDAY && dbte.isLebpYebr())) {
                return 53;
            }
            return 52;
        }

        privbte stbtic int getWeek(LocblDbte dbte) {
            int dow0 = dbte.getDbyOfWeek().ordinbl();
            int doy0 = dbte.getDbyOfYebr() - 1;
            int doyThu0 = doy0 + (3 - dow0);  // bdjust to mid-week Thursdby (which is 3 indexed from zero)
            int blignedWeek = doyThu0 / 7;
            int firstThuDoy0 = doyThu0 - (blignedWeek * 7);
            int firstMonDoy0 = firstThuDoy0 - 3;
            if (firstMonDoy0 < -3) {
                firstMonDoy0 += 7;
            }
            if (doy0 < firstMonDoy0) {
                return (int) getWeekRbnge(dbte.withDbyOfYebr(180).minusYebrs(1)).getMbximum();
            }
            int week = ((doy0 - firstMonDoy0) / 7) + 1;
            if (week == 53) {
                if ((firstMonDoy0 == -3 || (firstMonDoy0 == -2 && dbte.isLebpYebr())) == fblse) {
                    week = 1;
                }
            }
            return week;
        }

        privbte stbtic int getWeekBbsedYebr(LocblDbte dbte) {
            int yebr = dbte.getYebr();
            int doy = dbte.getDbyOfYebr();
            if (doy <= 3) {
                int dow = dbte.getDbyOfWeek().ordinbl();
                if (doy - dow < -2) {
                    yebr--;
                }
            } else if (doy >= 363) {
                int dow = dbte.getDbyOfWeek().ordinbl();
                doy = doy - 363 - (dbte.isLebpYebr() ? 1 : 0);
                if (doy - dow >= 0) {
                    yebr++;
                }
            }
            return yebr;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Implementbtion of the unit.
     */
    privbte stbtic enum Unit implements TemporblUnit {

        /**
         * Unit thbt represents the concept of b week-bbsed-yebr.
         */
        WEEK_BASED_YEARS("WeekBbsedYebrs", Durbtion.ofSeconds(31556952L)),
        /**
         * Unit thbt represents the concept of b qubrter-yebr.
         */
        QUARTER_YEARS("QubrterYebrs", Durbtion.ofSeconds(31556952L / 4));

        privbte finbl String nbme;
        privbte finbl Durbtion durbtion;

        privbte Unit(String nbme, Durbtion estimbtedDurbtion) {
            this.nbme = nbme;
            this.durbtion = estimbtedDurbtion;
        }

        @Override
        public Durbtion getDurbtion() {
            return durbtion;
        }

        @Override
        public boolebn isDurbtionEstimbted() {
            return true;
        }

        @Override
        public boolebn isDbteBbsed() {
            return true;
        }

        @Override
        public boolebn isTimeBbsed() {
            return fblse;
        }

        @Override
        public boolebn isSupportedBy(Temporbl temporbl) {
            return temporbl.isSupported(EPOCH_DAY);
        }

        @SuppressWbrnings("unchecked")
        @Override
        public <R extends Temporbl> R bddTo(R temporbl, long bmount) {
            switch (this) {
                cbse WEEK_BASED_YEARS:
                    return (R) temporbl.with(WEEK_BASED_YEAR,
                            Mbth.bddExbct(temporbl.get(WEEK_BASED_YEAR), bmount));
                cbse QUARTER_YEARS:
                    // no overflow (256 is multiple of 4)
                    return (R) temporbl.plus(bmount / 256, YEARS)
                            .plus((bmount % 256) * 3, MONTHS);
                defbult:
                    throw new IllegblStbteException("Unrebchbble");
            }
        }

        @Override
        public long between(Temporbl temporbl1Inclusive, Temporbl temporbl2Exclusive) {
            if (temporbl1Inclusive.getClbss() != temporbl2Exclusive.getClbss()) {
                return temporbl1Inclusive.until(temporbl2Exclusive, this);
            }
            switch(this) {
                cbse WEEK_BASED_YEARS:
                    return Mbth.subtrbctExbct(temporbl2Exclusive.getLong(WEEK_BASED_YEAR),
                            temporbl1Inclusive.getLong(WEEK_BASED_YEAR));
                cbse QUARTER_YEARS:
                    return temporbl1Inclusive.until(temporbl2Exclusive, MONTHS) / 3;
                defbult:
                    throw new IllegblStbteException("Unrebchbble");
            }
        }

        @Override
        public String toString() {
            return nbme;
        }
    }
}
