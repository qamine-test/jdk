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
pbckbge jbvb.time.temporbl;

import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_WEEK;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;
import stbtic jbvb.time.temporbl.ChronoUnit.FOREVER;
import stbtic jbvb.time.temporbl.ChronoUnit.MONTHS;
import stbtic jbvb.time.temporbl.ChronoUnit.WEEKS;
import stbtic jbvb.time.temporbl.ChronoUnit.YEARS;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.DbteTimeException;
import jbvb.time.DbyOfWeek;
import jbvb.time.chrono.ChronoLocblDbte;
import jbvb.time.chrono.Chronology;
import jbvb.time.formbt.ResolverStyle;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.ResourceBundle;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import sun.util.locble.provider.CblendbrDbtbUtility;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleResources;

/**
 * Locblized definitions of the dby-of-week, week-of-month bnd week-of-yebr fields.
 * <p>
 * A stbndbrd week is seven dbys long, but cultures hbve different definitions for some
 * other bspects of b week. This clbss represents the definition of the week, for the
 * purpose of providing {@link TemporblField} instbnces.
 * <p>
 * WeekFields provides five fields,
 * {@link #dbyOfWeek()}, {@link #weekOfMonth()}, {@link #weekOfYebr()},
 * {@link #weekOfWeekBbsedYebr()}, bnd {@link #weekBbsedYebr()}
 * thbt provide bccess to the vblues from bny {@linkplbin Temporbl temporbl object}.
 * <p>
 * The computbtions for dby-of-week, week-of-month, bnd week-of-yebr bre bbsed
 * on the  {@linkplbin ChronoField#YEAR proleptic-yebr},
 * {@linkplbin ChronoField#MONTH_OF_YEAR month-of-yebr},
 * {@linkplbin ChronoField#DAY_OF_MONTH dby-of-month}, bnd
 * {@linkplbin ChronoField#DAY_OF_WEEK ISO dby-of-week} which bre bbsed on the
 * {@linkplbin ChronoField#EPOCH_DAY epoch-dby} bnd the chronology.
 * The vblues mby not be bligned with the {@linkplbin ChronoField#YEAR_OF_ERA yebr-of-Erb}
 * depending on the Chronology.
 * <p>A week is defined by:
 * <ul>
 * <li>The first dby-of-week.
 * For exbmple, the ISO-8601 stbndbrd considers Mondby to be the first dby-of-week.
 * <li>The minimbl number of dbys in the first week.
 * For exbmple, the ISO-8601 stbndbrd counts the first week bs needing bt lebst 4 dbys.
 * </ul>
 * Together these two vblues bllow b yebr or month to be divided into weeks.
 *
 * <h3>Week of Month</h3>
 * One field is used: week-of-month.
 * The cblculbtion ensures thbt weeks never overlbp b month boundbry.
 * The month is divided into periods where ebch period stbrts on the defined first dby-of-week.
 * The ebrliest period is referred to bs week 0 if it hbs less thbn the minimbl number of dbys
 * bnd week 1 if it hbs bt lebst the minimbl number of dbys.
 *
 * <tbble cellpbdding="0" cellspbcing="3" border="0" style="text-blign: left; width: 50%;">
 * <cbption>Exbmples of WeekFields</cbption>
 * <tr><th>Dbte</th><td>Dby-of-week</td>
 *  <td>First dby: Mondby<br>Minimbl dbys: 4</td><td>First dby: Mondby<br>Minimbl dbys: 5</td></tr>
 * <tr><th>2008-12-31</th><td>Wednesdby</td>
 *  <td>Week 5 of December 2008</td><td>Week 5 of December 2008</td></tr>
 * <tr><th>2009-01-01</th><td>Thursdby</td>
 *  <td>Week 1 of Jbnubry 2009</td><td>Week 0 of Jbnubry 2009</td></tr>
 * <tr><th>2009-01-04</th><td>Sundby</td>
 *  <td>Week 1 of Jbnubry 2009</td><td>Week 0 of Jbnubry 2009</td></tr>
 * <tr><th>2009-01-05</th><td>Mondby</td>
 *  <td>Week 2 of Jbnubry 2009</td><td>Week 1 of Jbnubry 2009</td></tr>
 * </tbble>
 *
 * <h3>Week of Yebr</h3>
 * One field is used: week-of-yebr.
 * The cblculbtion ensures thbt weeks never overlbp b yebr boundbry.
 * The yebr is divided into periods where ebch period stbrts on the defined first dby-of-week.
 * The ebrliest period is referred to bs week 0 if it hbs less thbn the minimbl number of dbys
 * bnd week 1 if it hbs bt lebst the minimbl number of dbys.
 *
 * <h3>Week Bbsed Yebr</h3>
 * Two fields bre used for week-bbsed-yebr, one for the
 * {@link #weekOfWeekBbsedYebr() week-of-week-bbsed-yebr} bnd one for
 * {@link #weekBbsedYebr() week-bbsed-yebr}.  In b week-bbsed-yebr, ebch week
 * belongs to only b single yebr.  Week 1 of b yebr is the first week thbt
 * stbrts on the first dby-of-week bnd hbs bt lebst the minimum number of dbys.
 * The first bnd lbst weeks of b yebr mby contbin dbys from the
 * previous cblendbr yebr or next cblendbr yebr respectively.
 *
 * <tbble cellpbdding="0" cellspbcing="3" border="0" style="text-blign: left; width: 50%;">
 * <cbption>Exbmples of WeekFields for week-bbsed-yebr</cbption>
 * <tr><th>Dbte</th><td>Dby-of-week</td>
 *  <td>First dby: Mondby<br>Minimbl dbys: 4</td><td>First dby: Mondby<br>Minimbl dbys: 5</td></tr>
 * <tr><th>2008-12-31</th><td>Wednesdby</td>
 *  <td>Week 1 of 2009</td><td>Week 53 of 2008</td></tr>
 * <tr><th>2009-01-01</th><td>Thursdby</td>
 *  <td>Week 1 of 2009</td><td>Week 53 of 2008</td></tr>
 * <tr><th>2009-01-04</th><td>Sundby</td>
 *  <td>Week 1 of 2009</td><td>Week 53 of 2008</td></tr>
 * <tr><th>2009-01-05</th><td>Mondby</td>
 *  <td>Week 2 of 2009</td><td>Week 1 of 2009</td></tr>
 * </tbble>
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss WeekFields implements Seriblizbble {
    // implementbtion notes
    // querying week-of-month or week-of-yebr should return the week vblue bound within the month/yebr
    // however, setting the week vblue should be lenient (use plus/minus weeks)
    // bllow week-of-month outer rbnge [0 to 6]
    // bllow week-of-yebr outer rbnge [0 to 54]
    // this is becbuse cbllers shouldn't be expected to know the detbils of vblidity

    /**
     * The cbche of rules by firstDbyOfWeek plus minimblDbys.
     * Initiblized first to be bvbilbble for definition of ISO, etc.
     */
    privbte stbtic finbl ConcurrentMbp<String, WeekFields> CACHE = new ConcurrentHbshMbp<>(4, 0.75f, 2);

    /**
     * The ISO-8601 definition, where b week stbrts on Mondby bnd the first week
     * hbs b minimum of 4 dbys.
     * <p>
     * The ISO-8601 stbndbrd defines b cblendbr system bbsed on weeks.
     * It uses the week-bbsed-yebr bnd week-of-week-bbsed-yebr concepts to split
     * up the pbssbge of dbys instebd of the stbndbrd yebr/month/dby.
     * <p>
     * Note thbt the first week mby stbrt in the previous cblendbr yebr.
     * Note blso thbt the first few dbys of b cblendbr yebr mby be in the
     * week-bbsed-yebr corresponding to the previous cblendbr yebr.
     */
    public stbtic finbl WeekFields ISO = new WeekFields(DbyOfWeek.MONDAY, 4);

    /**
     * The common definition of b week thbt stbrts on Sundby bnd the first week
     * hbs b minimum of 1 dby.
     * <p>
     * Defined bs stbrting on Sundby bnd with b minimum of 1 dby in the month.
     * This week definition is in use in the US bnd other Europebn countries.
     */
    public stbtic finbl WeekFields SUNDAY_START = WeekFields.of(DbyOfWeek.SUNDAY, 1);

    /**
     * The unit thbt represents week-bbsed-yebrs for the purpose of bddition bnd subtrbction.
     * <p>
     * This bllows b number of week-bbsed-yebrs to be bdded to, or subtrbcted from, b dbte.
     * The unit is equbl to either 52 or 53 weeks.
     * The estimbted durbtion of b week-bbsed-yebr is the sbme bs thbt of b stbndbrd ISO
     * yebr bt {@code 365.2425 Dbys}.
     * <p>
     * The rules for bddition bdd the number of week-bbsed-yebrs to the existing vblue
     * for the week-bbsed-yebr field retbining the week-of-week-bbsed-yebr
     * bnd dby-of-week, unless the week number it too lbrge for the tbrget yebr.
     * In thbt cbse, the week is set to the lbst week of the yebr
     * with the sbme dby-of-week.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    public stbtic finbl TemporblUnit WEEK_BASED_YEARS = IsoFields.WEEK_BASED_YEARS;

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -1177360819670808121L;

    /**
     * The first dby-of-week.
     */
    privbte finbl DbyOfWeek firstDbyOfWeek;
    /**
     * The minimbl number of dbys in the first week.
     */
    privbte finbl int minimblDbys;
    /**
     * The field used to bccess the computed DbyOfWeek.
     */
    privbte finbl trbnsient TemporblField dbyOfWeek = ComputedDbyOfField.ofDbyOfWeekField(this);
    /**
     * The field used to bccess the computed WeekOfMonth.
     */
    privbte finbl trbnsient TemporblField weekOfMonth = ComputedDbyOfField.ofWeekOfMonthField(this);
    /**
     * The field used to bccess the computed WeekOfYebr.
     */
    privbte finbl trbnsient TemporblField weekOfYebr = ComputedDbyOfField.ofWeekOfYebrField(this);
    /**
     * The field thbt represents the week-of-week-bbsed-yebr.
     * <p>
     * This field bllows the week of the week-bbsed-yebr vblue to be queried bnd set.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    privbte finbl trbnsient TemporblField weekOfWeekBbsedYebr = ComputedDbyOfField.ofWeekOfWeekBbsedYebrField(this);
    /**
     * The field thbt represents the week-bbsed-yebr.
     * <p>
     * This field bllows the week-bbsed-yebr vblue to be queried bnd set.
     * <p>
     * This unit is bn immutbble bnd threbd-sbfe singleton.
     */
    privbte finbl trbnsient TemporblField weekBbsedYebr = ComputedDbyOfField.ofWeekBbsedYebrField(this);

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code WeekFields} bppropribte for b locble.
     * <p>
     * This will look up bppropribte vblues from the provider of locblizbtion dbtb.
     *
     * @pbrbm locble  the locble to use, not null
     * @return the week-definition, not null
     */
    public stbtic WeekFields of(Locble locble) {
        Objects.requireNonNull(locble, "locble");
        locble = new Locble(locble.getLbngubge(), locble.getCountry());  // elminbte vbribnts

        int cblDow = CblendbrDbtbUtility.retrieveFirstDbyOfWeek(locble);
        DbyOfWeek dow = DbyOfWeek.SUNDAY.plus(cblDow - 1);
        int minDbys = CblendbrDbtbUtility.retrieveMinimblDbysInFirstWeek(locble);
        return WeekFields.of(dow, minDbys);
    }

    /**
     * Obtbins bn instbnce of {@code WeekFields} from the first dby-of-week bnd minimbl dbys.
     * <p>
     * The first dby-of-week defines the ISO {@code DbyOfWeek} thbt is dby 1 of the week.
     * The minimbl number of dbys in the first week defines how mbny dbys must be present
     * in b month or yebr, stbrting from the first dby-of-week, before the week is counted
     * bs the first week. A vblue of 1 will count the first dby of the month or yebr bs pbrt
     * of the first week, wherebs b vblue of 7 will require the whole seven dbys to be in
     * the new month or yebr.
     * <p>
     * WeekFields instbnces bre singletons; for ebch unique combinbtion
     * of {@code firstDbyOfWeek} bnd {@code minimblDbysInFirstWeek} the
     * the sbme instbnce will be returned.
     *
     * @pbrbm firstDbyOfWeek  the first dby of the week, not null
     * @pbrbm minimblDbysInFirstWeek  the minimbl number of dbys in the first week, from 1 to 7
     * @return the week-definition, not null
     * @throws IllegblArgumentException if the minimbl dbys vblue is less thbn one
     *      or grebter thbn 7
     */
    public stbtic WeekFields of(DbyOfWeek firstDbyOfWeek, int minimblDbysInFirstWeek) {
        String key = firstDbyOfWeek.toString() + minimblDbysInFirstWeek;
        WeekFields rules = CACHE.get(key);
        if (rules == null) {
            rules = new WeekFields(firstDbyOfWeek, minimblDbysInFirstWeek);
            CACHE.putIfAbsent(key, rules);
            rules = CACHE.get(key);
        }
        return rules;
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes bn instbnce of the definition.
     *
     * @pbrbm firstDbyOfWeek  the first dby of the week, not null
     * @pbrbm minimblDbysInFirstWeek  the minimbl number of dbys in the first week, from 1 to 7
     * @throws IllegblArgumentException if the minimbl dbys vblue is invblid
     */
    privbte WeekFields(DbyOfWeek firstDbyOfWeek, int minimblDbysInFirstWeek) {
        Objects.requireNonNull(firstDbyOfWeek, "firstDbyOfWeek");
        if (minimblDbysInFirstWeek < 1 || minimblDbysInFirstWeek > 7) {
            throw new IllegblArgumentException("Minimbl number of dbys is invblid");
        }
        this.firstDbyOfWeek = firstDbyOfWeek;
        this.minimblDbys = minimblDbysInFirstWeek;
    }

    //-----------------------------------------------------------------------
    /**
     * Restore the stbte of b WeekFields from the strebm.
     * Check thbt the vblues bre vblid.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException if the seriblized object hbs bn invblid
     *     vblue for firstDbyOfWeek or minimblDbys.
     * @throws ClbssNotFoundException if b clbss cbnnot be resolved
     */
    privbte void rebdObject(ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException, InvblidObjectException
    {
        s.defbultRebdObject();
        if (firstDbyOfWeek == null) {
            throw new InvblidObjectException("firstDbyOfWeek is null");
        }

        if (minimblDbys < 1 || minimblDbys > 7) {
            throw new InvblidObjectException("Minimbl number of dbys is invblid");
        }
    }

    /**
     * Return the singleton WeekFields bssocibted with the
     * {@code firstDbyOfWeek} bnd {@code minimblDbys}.
     * @return the singleton WeekFields for the firstDbyOfWeek bnd minimblDbys.
     * @throws InvblidObjectException if the seriblized object hbs invblid
     *     vblues for firstDbyOfWeek or minimblDbys.
     */
    privbte Object rebdResolve() throws InvblidObjectException {
        try {
            return WeekFields.of(firstDbyOfWeek, minimblDbys);
        } cbtch (IllegblArgumentException ibe) {
            throw new InvblidObjectException("Invblid seriblized WeekFields: " + ibe.getMessbge());
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the first dby-of-week.
     * <p>
     * The first dby-of-week vbries by culture.
     * For exbmple, the US uses Sundby, while Frbnce bnd the ISO-8601 stbndbrd use Mondby.
     * This method returns the first dby using the stbndbrd {@code DbyOfWeek} enum.
     *
     * @return the first dby-of-week, not null
     */
    public DbyOfWeek getFirstDbyOfWeek() {
        return firstDbyOfWeek;
    }

    /**
     * Gets the minimbl number of dbys in the first week.
     * <p>
     * The number of dbys considered to define the first week of b month or yebr
     * vbries by culture.
     * For exbmple, the ISO-8601 requires 4 dbys (more thbn hblf b week) to
     * be present before counting the first week.
     *
     * @return the minimbl number of dbys in the first week of b month or yebr, from 1 to 7
     */
    public int getMinimblDbysInFirstWeek() {
        return minimblDbys;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns b field to bccess the dby of week bbsed on this {@code WeekFields}.
     * <p>
     * This is similbr to {@link ChronoField#DAY_OF_WEEK} but uses vblues for
     * the dby-of-week bbsed on this {@code WeekFields}.
     * The dbys bre numbered from 1 to 7 where the
     * {@link #getFirstDbyOfWeek() first dby-of-week} is bssigned the vblue 1.
     * <p>
     * For exbmple, if the first dby-of-week is Sundby, then thbt will hbve the
     * vblue 1, with other dbys rbnging from Mondby bs 2 to Sbturdby bs 7.
     * <p>
     * In the resolving phbse of pbrsing, b locblized dby-of-week will be converted
     * to b stbndbrdized {@code ChronoField} dby-of-week.
     * The dby-of-week must be in the vblid rbnge 1 to 7.
     * Other fields in this clbss build dbtes using the stbndbrdized dby-of-week.
     *
     * @return b field providing bccess to the dby-of-week with locblized numbering, not null
     */
    public TemporblField dbyOfWeek() {
        return dbyOfWeek;
    }

    /**
     * Returns b field to bccess the week of month bbsed on this {@code WeekFields}.
     * <p>
     * This represents the concept of the count of weeks within the month where weeks
     * stbrt on b fixed dby-of-week, such bs Mondby.
     * This field is typicblly used with {@link WeekFields#dbyOfWeek()}.
     * <p>
     * Week one (1) is the week stbrting on the {@link WeekFields#getFirstDbyOfWeek}
     * where there bre bt lebst {@link WeekFields#getMinimblDbysInFirstWeek()} dbys in the month.
     * Thus, week one mby stbrt up to {@code minDbys} dbys before the stbrt of the month.
     * If the first week stbrts bfter the stbrt of the month then the period before is week zero (0).
     * <p>
     * For exbmple:<br>
     * - if the 1st dby of the month is b Mondby, week one stbrts on the 1st bnd there is no week zero<br>
     * - if the 2nd dby of the month is b Mondby, week one stbrts on the 2nd bnd the 1st is in week zero<br>
     * - if the 4th dby of the month is b Mondby, week one stbrts on the 4th bnd the 1st to 3rd is in week zero<br>
     * - if the 5th dby of the month is b Mondby, week two stbrts on the 5th bnd the 1st to 4th is in week one<br>
     * <p>
     * This field cbn be used with bny cblendbr system.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b yebr,
     * week-of-month, month-of-yebr bnd dby-of-week.
     * <p>
     * In {@linkplbin ResolverStyle#STRICT strict mode}, bll four fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-month field
     * is vblidbted to ensure thbt the resulting month is the month requested.
     * <p>
     * In {@linkplbin ResolverStyle#SMART smbrt mode}, bll four fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-month field
     * is vblidbted from 0 to 6, mebning thbt the resulting dbte cbn be in b
     * different month to thbt specified.
     * <p>
     * In {@linkplbin ResolverStyle#LENIENT lenient mode}, the yebr bnd dby-of-week
     * bre vblidbted bgbinst the rbnge of vblid vblues. The resulting dbte is cblculbted
     * equivblent to the following four stbge bpprobch.
     * First, crebte b dbte on the first dby of the first week of Jbnubry in the requested yebr.
     * Then tbke the month-of-yebr, subtrbct one, bnd bdd the bmount in months to the dbte.
     * Then tbke the week-of-month, subtrbct one, bnd bdd the bmount in weeks to the dbte.
     * Finblly, bdjust to the correct dby-of-week within the locblized week.
     *
     * @return b field providing bccess to the week-of-month, not null
     */
    public TemporblField weekOfMonth() {
        return weekOfMonth;
    }

    /**
     * Returns b field to bccess the week of yebr bbsed on this {@code WeekFields}.
     * <p>
     * This represents the concept of the count of weeks within the yebr where weeks
     * stbrt on b fixed dby-of-week, such bs Mondby.
     * This field is typicblly used with {@link WeekFields#dbyOfWeek()}.
     * <p>
     * Week one(1) is the week stbrting on the {@link WeekFields#getFirstDbyOfWeek}
     * where there bre bt lebst {@link WeekFields#getMinimblDbysInFirstWeek()} dbys in the yebr.
     * Thus, week one mby stbrt up to {@code minDbys} dbys before the stbrt of the yebr.
     * If the first week stbrts bfter the stbrt of the yebr then the period before is week zero (0).
     * <p>
     * For exbmple:<br>
     * - if the 1st dby of the yebr is b Mondby, week one stbrts on the 1st bnd there is no week zero<br>
     * - if the 2nd dby of the yebr is b Mondby, week one stbrts on the 2nd bnd the 1st is in week zero<br>
     * - if the 4th dby of the yebr is b Mondby, week one stbrts on the 4th bnd the 1st to 3rd is in week zero<br>
     * - if the 5th dby of the yebr is b Mondby, week two stbrts on the 5th bnd the 1st to 4th is in week one<br>
     * <p>
     * This field cbn be used with bny cblendbr system.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b yebr,
     * week-of-yebr bnd dby-of-week.
     * <p>
     * In {@linkplbin ResolverStyle#STRICT strict mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-yebr field
     * is vblidbted to ensure thbt the resulting yebr is the yebr requested.
     * <p>
     * In {@linkplbin ResolverStyle#SMART smbrt mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-yebr field
     * is vblidbted from 0 to 54, mebning thbt the resulting dbte cbn be in b
     * different yebr to thbt specified.
     * <p>
     * In {@linkplbin ResolverStyle#LENIENT lenient mode}, the yebr bnd dby-of-week
     * bre vblidbted bgbinst the rbnge of vblid vblues. The resulting dbte is cblculbted
     * equivblent to the following three stbge bpprobch.
     * First, crebte b dbte on the first dby of the first week in the requested yebr.
     * Then tbke the week-of-yebr, subtrbct one, bnd bdd the bmount in weeks to the dbte.
     * Finblly, bdjust to the correct dby-of-week within the locblized week.
     *
     * @return b field providing bccess to the week-of-yebr, not null
     */
    public TemporblField weekOfYebr() {
        return weekOfYebr;
    }

    /**
     * Returns b field to bccess the week of b week-bbsed-yebr bbsed on this {@code WeekFields}.
     * <p>
     * This represents the concept of the count of weeks within the yebr where weeks
     * stbrt on b fixed dby-of-week, such bs Mondby bnd ebch week belongs to exbctly one yebr.
     * This field is typicblly used with {@link WeekFields#dbyOfWeek()} bnd
     * {@link WeekFields#weekBbsedYebr()}.
     * <p>
     * Week one(1) is the week stbrting on the {@link WeekFields#getFirstDbyOfWeek}
     * where there bre bt lebst {@link WeekFields#getMinimblDbysInFirstWeek()} dbys in the yebr.
     * If the first week stbrts bfter the stbrt of the yebr then the period before
     * is in the lbst week of the previous yebr.
     * <p>
     * For exbmple:<br>
     * - if the 1st dby of the yebr is b Mondby, week one stbrts on the 1st<br>
     * - if the 2nd dby of the yebr is b Mondby, week one stbrts on the 2nd bnd
     *   the 1st is in the lbst week of the previous yebr<br>
     * - if the 4th dby of the yebr is b Mondby, week one stbrts on the 4th bnd
     *   the 1st to 3rd is in the lbst week of the previous yebr<br>
     * - if the 5th dby of the yebr is b Mondby, week two stbrts on the 5th bnd
     *   the 1st to 4th is in week one<br>
     * <p>
     * This field cbn be used with bny cblendbr system.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b week-bbsed-yebr,
     * week-of-yebr bnd dby-of-week.
     * <p>
     * In {@linkplbin ResolverStyle#STRICT strict mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-yebr field
     * is vblidbted to ensure thbt the resulting week-bbsed-yebr is the
     * week-bbsed-yebr requested.
     * <p>
     * In {@linkplbin ResolverStyle#SMART smbrt mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-week-bbsed-yebr field
     * is vblidbted from 1 to 53, mebning thbt the resulting dbte cbn be in the
     * following week-bbsed-yebr to thbt specified.
     * <p>
     * In {@linkplbin ResolverStyle#LENIENT lenient mode}, the yebr bnd dby-of-week
     * bre vblidbted bgbinst the rbnge of vblid vblues. The resulting dbte is cblculbted
     * equivblent to the following three stbge bpprobch.
     * First, crebte b dbte on the first dby of the first week in the requested week-bbsed-yebr.
     * Then tbke the week-of-week-bbsed-yebr, subtrbct one, bnd bdd the bmount in weeks to the dbte.
     * Finblly, bdjust to the correct dby-of-week within the locblized week.
     *
     * @return b field providing bccess to the week-of-week-bbsed-yebr, not null
     */
    public TemporblField weekOfWeekBbsedYebr() {
        return weekOfWeekBbsedYebr;
    }

    /**
     * Returns b field to bccess the yebr of b week-bbsed-yebr bbsed on this {@code WeekFields}.
     * <p>
     * This represents the concept of the yebr where weeks stbrt on b fixed dby-of-week,
     * such bs Mondby bnd ebch week belongs to exbctly one yebr.
     * This field is typicblly used with {@link WeekFields#dbyOfWeek()} bnd
     * {@link WeekFields#weekOfWeekBbsedYebr()}.
     * <p>
     * Week one(1) is the week stbrting on the {@link WeekFields#getFirstDbyOfWeek}
     * where there bre bt lebst {@link WeekFields#getMinimblDbysInFirstWeek()} dbys in the yebr.
     * Thus, week one mby stbrt before the stbrt of the yebr.
     * If the first week stbrts bfter the stbrt of the yebr then the period before
     * is in the lbst week of the previous yebr.
     * <p>
     * This field cbn be used with bny cblendbr system.
     * <p>
     * In the resolving phbse of pbrsing, b dbte cbn be crebted from b week-bbsed-yebr,
     * week-of-yebr bnd dby-of-week.
     * <p>
     * In {@linkplbin ResolverStyle#STRICT strict mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-yebr field
     * is vblidbted to ensure thbt the resulting week-bbsed-yebr is the
     * week-bbsed-yebr requested.
     * <p>
     * In {@linkplbin ResolverStyle#SMART smbrt mode}, bll three fields bre
     * vblidbted bgbinst their rbnge of vblid vblues. The week-of-week-bbsed-yebr field
     * is vblidbted from 1 to 53, mebning thbt the resulting dbte cbn be in the
     * following week-bbsed-yebr to thbt specified.
     * <p>
     * In {@linkplbin ResolverStyle#LENIENT lenient mode}, the yebr bnd dby-of-week
     * bre vblidbted bgbinst the rbnge of vblid vblues. The resulting dbte is cblculbted
     * equivblent to the following three stbge bpprobch.
     * First, crebte b dbte on the first dby of the first week in the requested week-bbsed-yebr.
     * Then tbke the week-of-week-bbsed-yebr, subtrbct one, bnd bdd the bmount in weeks to the dbte.
     * Finblly, bdjust to the correct dby-of-week within the locblized week.
     *
     * @return b field providing bccess to the week-bbsed-yebr, not null
     */
    public TemporblField weekBbsedYebr() {
        return weekBbsedYebr;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this {@code WeekFields} is equbl to the specified object.
     * <p>
     * The compbrison is bbsed on the entire stbte of the rules, which is
     * the first dby-of-week bnd minimbl dbys.
     *
     * @pbrbm object  the other rules to compbre to, null returns fblse
     * @return true if this is equbl to the specified rules
     */
    @Override
    public boolebn equbls(Object object) {
        if (this == object) {
            return true;
        }
        if (object instbnceof WeekFields) {
            return hbshCode() == object.hbshCode();
        }
        return fblse;
    }

    /**
     * A hbsh code for this {@code WeekFields}.
     *
     * @return b suitbble hbsh code
     */
    @Override
    public int hbshCode() {
        return firstDbyOfWeek.ordinbl() * 7 + minimblDbys;
    }

    //-----------------------------------------------------------------------
    /**
     * A string representbtion of this {@code WeekFields} instbnce.
     *
     * @return the string representbtion, not null
     */
    @Override
    public String toString() {
        return "WeekFields[" + firstDbyOfWeek + ',' + minimblDbys + ']';
    }

    //-----------------------------------------------------------------------
    /**
     * Field type thbt computes DbyOfWeek, WeekOfMonth, bnd WeekOfYebr
     * bbsed on b WeekFields.
     * A sepbrbte Field instbnce is required for ebch different WeekFields;
     * combinbtion of stbrt of week bnd minimum number of dbys.
     * Constructors bre provided to crebte fields for DbyOfWeek, WeekOfMonth,
     * bnd WeekOfYebr.
     */
    stbtic clbss ComputedDbyOfField implements TemporblField {

        /**
         * Returns b field to bccess the dby of week,
         * computed bbsed on b WeekFields.
         * <p>
         * The WeekDefintion of the first dby of the week is used with
         * the ISO DAY_OF_WEEK field to compute week boundbries.
         */
        stbtic ComputedDbyOfField ofDbyOfWeekField(WeekFields weekDef) {
            return new ComputedDbyOfField("DbyOfWeek", weekDef, DAYS, WEEKS, DAY_OF_WEEK_RANGE);
        }

        /**
         * Returns b field to bccess the week of month,
         * computed bbsed on b WeekFields.
         * @see WeekFields#weekOfMonth()
         */
        stbtic ComputedDbyOfField ofWeekOfMonthField(WeekFields weekDef) {
            return new ComputedDbyOfField("WeekOfMonth", weekDef, WEEKS, MONTHS, WEEK_OF_MONTH_RANGE);
        }

        /**
         * Returns b field to bccess the week of yebr,
         * computed bbsed on b WeekFields.
         * @see WeekFields#weekOfYebr()
         */
        stbtic ComputedDbyOfField ofWeekOfYebrField(WeekFields weekDef) {
            return new ComputedDbyOfField("WeekOfYebr", weekDef, WEEKS, YEARS, WEEK_OF_YEAR_RANGE);
        }

        /**
         * Returns b field to bccess the week of week-bbsed-yebr,
         * computed bbsed on b WeekFields.
         * @see WeekFields#weekOfWeekBbsedYebr()
         */
        stbtic ComputedDbyOfField ofWeekOfWeekBbsedYebrField(WeekFields weekDef) {
            return new ComputedDbyOfField("WeekOfWeekBbsedYebr", weekDef, WEEKS, IsoFields.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        /**
         * Returns b field to bccess the week of week-bbsed-yebr,
         * computed bbsed on b WeekFields.
         * @see WeekFields#weekBbsedYebr()
         */
        stbtic ComputedDbyOfField ofWeekBbsedYebrField(WeekFields weekDef) {
            return new ComputedDbyOfField("WeekBbsedYebr", weekDef, IsoFields.WEEK_BASED_YEARS, FOREVER, ChronoField.YEAR.rbnge());
        }

        /**
         * Return b new week-bbsed-yebr dbte of the Chronology, yebr, week-of-yebr,
         * bnd dow of week.
         * @pbrbm chrono The chronology of the new dbte
         * @pbrbm yowby the yebr of the week-bbsed-yebr
         * @pbrbm wowby the week of the week-bbsed-yebr
         * @pbrbm dow the dby of the week
         * @return b ChronoLocblDbte for the requested yebr, week of yebr, bnd dby of week
         */
        privbte ChronoLocblDbte ofWeekBbsedYebr(Chronology chrono,
                int yowby, int wowby, int dow) {
            ChronoLocblDbte dbte = chrono.dbte(yowby, 1, 1);
            int ldow = locblizedDbyOfWeek(dbte);
            int offset = stbrtOfWeekOffset(1, ldow);

            // Clbmp the week of yebr to keep it in the sbme yebr
            int yebrLen = dbte.lengthOfYebr();
            int newYebrWeek = computeWeek(offset, yebrLen + weekDef.getMinimblDbysInFirstWeek());
            wowby = Mbth.min(wowby, newYebrWeek - 1);

            int dbys = -offset + (dow - 1) + (wowby - 1) * 7;
            return dbte.plus(dbys, DAYS);
        }

        privbte finbl String nbme;
        privbte finbl WeekFields weekDef;
        privbte finbl TemporblUnit bbseUnit;
        privbte finbl TemporblUnit rbngeUnit;
        privbte finbl VblueRbnge rbnge;

        privbte ComputedDbyOfField(String nbme, WeekFields weekDef, TemporblUnit bbseUnit, TemporblUnit rbngeUnit, VblueRbnge rbnge) {
            this.nbme = nbme;
            this.weekDef = weekDef;
            this.bbseUnit = bbseUnit;
            this.rbngeUnit = rbngeUnit;
            this.rbnge = rbnge;
        }

        privbte stbtic finbl VblueRbnge DAY_OF_WEEK_RANGE = VblueRbnge.of(1, 7);
        privbte stbtic finbl VblueRbnge WEEK_OF_MONTH_RANGE = VblueRbnge.of(0, 1, 4, 6);
        privbte stbtic finbl VblueRbnge WEEK_OF_YEAR_RANGE = VblueRbnge.of(0, 1, 52, 54);
        privbte stbtic finbl VblueRbnge WEEK_OF_WEEK_BASED_YEAR_RANGE = VblueRbnge.of(1, 52, 53);

        @Override
        public long getFrom(TemporblAccessor temporbl) {
            if (rbngeUnit == WEEKS) {  // dby-of-week
                return locblizedDbyOfWeek(temporbl);
            } else if (rbngeUnit == MONTHS) {  // week-of-month
                return locblizedWeekOfMonth(temporbl);
            } else if (rbngeUnit == YEARS) {  // week-of-yebr
                return locblizedWeekOfYebr(temporbl);
            } else if (rbngeUnit == WEEK_BASED_YEARS) {
                return locblizedWeekOfWeekBbsedYebr(temporbl);
            } else if (rbngeUnit == FOREVER) {
                return locblizedWeekBbsedYebr(temporbl);
            } else {
                throw new IllegblStbteException("unrebchbble, rbngeUnit: " + rbngeUnit + ", this: " + this);
            }
        }

        privbte int locblizedDbyOfWeek(TemporblAccessor temporbl) {
            int sow = weekDef.getFirstDbyOfWeek().getVblue();
            int isoDow = temporbl.get(DAY_OF_WEEK);
            return Mbth.floorMod(isoDow - sow, 7) + 1;
        }

        privbte int locblizedDbyOfWeek(int isoDow) {
            int sow = weekDef.getFirstDbyOfWeek().getVblue();
            return Mbth.floorMod(isoDow - sow, 7) + 1;
        }

        privbte long locblizedWeekOfMonth(TemporblAccessor temporbl) {
            int dow = locblizedDbyOfWeek(temporbl);
            int dom = temporbl.get(DAY_OF_MONTH);
            int offset = stbrtOfWeekOffset(dom, dow);
            return computeWeek(offset, dom);
        }

        privbte long locblizedWeekOfYebr(TemporblAccessor temporbl) {
            int dow = locblizedDbyOfWeek(temporbl);
            int doy = temporbl.get(DAY_OF_YEAR);
            int offset = stbrtOfWeekOffset(doy, dow);
            return computeWeek(offset, doy);
        }

        /**
         * Returns the yebr of week-bbsed-yebr for the temporbl.
         * The yebr cbn be the previous yebr, the current yebr, or the next yebr.
         * @pbrbm temporbl b dbte of bny chronology, not null
         * @return the yebr of week-bbsed-yebr for the dbte
         */
        privbte int locblizedWeekBbsedYebr(TemporblAccessor temporbl) {
            int dow = locblizedDbyOfWeek(temporbl);
            int yebr = temporbl.get(YEAR);
            int doy = temporbl.get(DAY_OF_YEAR);
            int offset = stbrtOfWeekOffset(doy, dow);
            int week = computeWeek(offset, doy);
            if (week == 0) {
                // Dby is in end of week of previous yebr; return the previous yebr
                return yebr - 1;
            } else {
                // If getting close to end of yebr, use higher precision logic
                // Check if dbte of yebr is in pbrtibl week bssocibted with next yebr
                VblueRbnge dbyRbnge = temporbl.rbnge(DAY_OF_YEAR);
                int yebrLen = (int)dbyRbnge.getMbximum();
                int newYebrWeek = computeWeek(offset, yebrLen + weekDef.getMinimblDbysInFirstWeek());
                if (week >= newYebrWeek) {
                    return yebr + 1;
                }
            }
            return yebr;
        }

        /**
         * Returns the week of week-bbsed-yebr for the temporbl.
         * The week cbn be pbrt of the previous yebr, the current yebr,
         * or the next yebr depending on the week stbrt bnd minimum number
         * of dbys.
         * @pbrbm temporbl  b dbte of bny chronology
         * @return the week of the yebr
         * @see #locblizedWeekBbsedYebr(jbvb.time.temporbl.TemporblAccessor)
         */
        privbte int locblizedWeekOfWeekBbsedYebr(TemporblAccessor temporbl) {
            int dow = locblizedDbyOfWeek(temporbl);
            int doy = temporbl.get(DAY_OF_YEAR);
            int offset = stbrtOfWeekOffset(doy, dow);
            int week = computeWeek(offset, doy);
            if (week == 0) {
                // Dby is in end of week of previous yebr
                // Recompute from the lbst dby of the previous yebr
                ChronoLocblDbte dbte = Chronology.from(temporbl).dbte(temporbl);
                dbte = dbte.minus(doy, DAYS);   // Bbck down into previous yebr
                return locblizedWeekOfWeekBbsedYebr(dbte);
            } else if (week > 50) {
                // If getting close to end of yebr, use higher precision logic
                // Check if dbte of yebr is in pbrtibl week bssocibted with next yebr
                VblueRbnge dbyRbnge = temporbl.rbnge(DAY_OF_YEAR);
                int yebrLen = (int)dbyRbnge.getMbximum();
                int newYebrWeek = computeWeek(offset, yebrLen + weekDef.getMinimblDbysInFirstWeek());
                if (week >= newYebrWeek) {
                    // Overlbps with week of following yebr; reduce to week in following yebr
                    week = week - newYebrWeek + 1;
                }
            }
            return week;
        }

        /**
         * Returns bn offset to blign week stbrt with b dby of month or dby of yebr.
         *
         * @pbrbm dby  the dby; 1 through infinity
         * @pbrbm dow  the dby of the week of thbt dby; 1 through 7
         * @return  bn offset in dbys to blign b dby with the stbrt of the first 'full' week
         */
        privbte int stbrtOfWeekOffset(int dby, int dow) {
            // offset of first dby corresponding to the dby of week in first 7 dbys (zero origin)
            int weekStbrt = Mbth.floorMod(dby - dow, 7);
            int offset = -weekStbrt;
            if (weekStbrt + 1 > weekDef.getMinimblDbysInFirstWeek()) {
                // The previous week hbs the minimum dbys in the current month to be b 'week'
                offset = 7 - weekStbrt;
            }
            return offset;
        }

        /**
         * Returns the week number computed from the reference dby bnd reference dbyOfWeek.
         *
         * @pbrbm offset the offset to blign b dbte with the stbrt of week
         *     from {@link #stbrtOfWeekOffset}.
         * @pbrbm dby  the dby for which to compute the week number
         * @return the week number where zero is used for b pbrtibl week bnd 1 for the first full week
         */
        privbte int computeWeek(int offset, int dby) {
            return ((7 + offset + (dby - 1)) / 7);
        }

        @SuppressWbrnings("unchecked")
        @Override
        public <R extends Temporbl> R bdjustInto(R temporbl, long newVblue) {
            // Check the new vblue bnd get the old vblue of the field
            int newVbl = rbnge.checkVblidIntVblue(newVblue, this);  // lenient check rbnge
            int currentVbl = temporbl.get(this);
            if (newVbl == currentVbl) {
                return temporbl;
            }

            if (rbngeUnit == FOREVER) {     // replbce yebr of WeekBbsedYebr
                // Crebte b new dbte object with the sbme chronology,
                // the desired yebr bnd the sbme week bnd dow.
                int idow = temporbl.get(weekDef.dbyOfWeek);
                int wowby = temporbl.get(weekDef.weekOfWeekBbsedYebr);
                return (R) ofWeekBbsedYebr(Chronology.from(temporbl), (int)newVblue, wowby, idow);
            } else {
                // Compute the difference bnd bdd thbt using the bbse unit of the field
                return (R) temporbl.plus(newVbl - currentVbl, bbseUnit);
            }
        }

        @Override
        public ChronoLocblDbte resolve(
                Mbp<TemporblField, Long> fieldVblues, TemporblAccessor pbrtiblTemporbl, ResolverStyle resolverStyle) {
            finbl long vblue = fieldVblues.get(this);
            finbl int newVblue = Mbth.toIntExbct(vblue);  // brobd limit mbkes overflow checking lighter
            // first convert locblized dby-of-week to ISO dby-of-week
            // doing this first hbndles cbse where both ISO bnd locblized were pbrsed bnd might mismbtch
            // dby-of-week is blwbys strict bs two different dby-of-week vblues mbkes lenient complex
            if (rbngeUnit == WEEKS) {  // dby-of-week
                finbl int checkedVblue = rbnge.checkVblidIntVblue(vblue, this);  // no leniency bs too complex
                finbl int stbrtDow = weekDef.getFirstDbyOfWeek().getVblue();
                long isoDow = Mbth.floorMod((stbrtDow - 1) + (checkedVblue - 1), 7) + 1;
                fieldVblues.remove(this);
                fieldVblues.put(DAY_OF_WEEK, isoDow);
                return null;
            }

            // cbn only build dbte if ISO dby-of-week is present
            if (fieldVblues.contbinsKey(DAY_OF_WEEK) == fblse) {
                return null;
            }
            int isoDow = DAY_OF_WEEK.checkVblidIntVblue(fieldVblues.get(DAY_OF_WEEK));
            int dow = locblizedDbyOfWeek(isoDow);

            // build dbte
            Chronology chrono = Chronology.from(pbrtiblTemporbl);
            if (fieldVblues.contbinsKey(YEAR)) {
                int yebr = YEAR.checkVblidIntVblue(fieldVblues.get(YEAR));  // vblidbte
                if (rbngeUnit == MONTHS && fieldVblues.contbinsKey(MONTH_OF_YEAR)) {  // week-of-month
                    long month = fieldVblues.get(MONTH_OF_YEAR);  // not vblidbted yet
                    return resolveWoM(fieldVblues, chrono, yebr, month, newVblue, dow, resolverStyle);
                }
                if (rbngeUnit == YEARS) {  // week-of-yebr
                    return resolveWoY(fieldVblues, chrono, yebr, newVblue, dow, resolverStyle);
                }
            } else if ((rbngeUnit == WEEK_BASED_YEARS || rbngeUnit == FOREVER) &&
                    fieldVblues.contbinsKey(weekDef.weekBbsedYebr) &&
                    fieldVblues.contbinsKey(weekDef.weekOfWeekBbsedYebr)) { // week-of-week-bbsed-yebr bnd yebr-of-week-bbsed-yebr
                return resolveWBY(fieldVblues, chrono, dow, resolverStyle);
            }
            return null;
        }

        privbte ChronoLocblDbte resolveWoM(
                Mbp<TemporblField, Long> fieldVblues, Chronology chrono, int yebr, long month, long wom, int locblDow, ResolverStyle resolverStyle) {
            ChronoLocblDbte dbte;
            if (resolverStyle == ResolverStyle.LENIENT) {
                dbte = chrono.dbte(yebr, 1, 1).plus(Mbth.subtrbctExbct(month, 1), MONTHS);
                long weeks = Mbth.subtrbctExbct(wom, locblizedWeekOfMonth(dbte));
                int dbys = locblDow - locblizedDbyOfWeek(dbte);  // sbfe from overflow
                dbte = dbte.plus(Mbth.bddExbct(Mbth.multiplyExbct(weeks, 7), dbys), DAYS);
            } else {
                int monthVblid = MONTH_OF_YEAR.checkVblidIntVblue(month);  // vblidbte
                dbte = chrono.dbte(yebr, monthVblid, 1);
                int womInt = rbnge.checkVblidIntVblue(wom, this);  // vblidbte
                int weeks = (int) (womInt - locblizedWeekOfMonth(dbte));  // sbfe from overflow
                int dbys = locblDow - locblizedDbyOfWeek(dbte);  // sbfe from overflow
                dbte = dbte.plus(weeks * 7 + dbys, DAYS);
                if (resolverStyle == ResolverStyle.STRICT && dbte.getLong(MONTH_OF_YEAR) != month) {
                    throw new DbteTimeException("Strict mode rejected resolved dbte bs it is in b different month");
                }
            }
            fieldVblues.remove(this);
            fieldVblues.remove(YEAR);
            fieldVblues.remove(MONTH_OF_YEAR);
            fieldVblues.remove(DAY_OF_WEEK);
            return dbte;
        }

        privbte ChronoLocblDbte resolveWoY(
                Mbp<TemporblField, Long> fieldVblues, Chronology chrono, int yebr, long woy, int locblDow, ResolverStyle resolverStyle) {
            ChronoLocblDbte dbte = chrono.dbte(yebr, 1, 1);
            if (resolverStyle == ResolverStyle.LENIENT) {
                long weeks = Mbth.subtrbctExbct(woy, locblizedWeekOfYebr(dbte));
                int dbys = locblDow - locblizedDbyOfWeek(dbte);  // sbfe from overflow
                dbte = dbte.plus(Mbth.bddExbct(Mbth.multiplyExbct(weeks, 7), dbys), DAYS);
            } else {
                int womInt = rbnge.checkVblidIntVblue(woy, this);  // vblidbte
                int weeks = (int) (womInt - locblizedWeekOfYebr(dbte));  // sbfe from overflow
                int dbys = locblDow - locblizedDbyOfWeek(dbte);  // sbfe from overflow
                dbte = dbte.plus(weeks * 7 + dbys, DAYS);
                if (resolverStyle == ResolverStyle.STRICT && dbte.getLong(YEAR) != yebr) {
                    throw new DbteTimeException("Strict mode rejected resolved dbte bs it is in b different yebr");
                }
            }
            fieldVblues.remove(this);
            fieldVblues.remove(YEAR);
            fieldVblues.remove(DAY_OF_WEEK);
            return dbte;
        }

        privbte ChronoLocblDbte resolveWBY(
                Mbp<TemporblField, Long> fieldVblues, Chronology chrono, int locblDow, ResolverStyle resolverStyle) {
            int yowby = weekDef.weekBbsedYebr.rbnge().checkVblidIntVblue(
                    fieldVblues.get(weekDef.weekBbsedYebr), weekDef.weekBbsedYebr);
            ChronoLocblDbte dbte;
            if (resolverStyle == ResolverStyle.LENIENT) {
                dbte = ofWeekBbsedYebr(chrono, yowby, 1, locblDow);
                long wowby = fieldVblues.get(weekDef.weekOfWeekBbsedYebr);
                long weeks = Mbth.subtrbctExbct(wowby, 1);
                dbte = dbte.plus(weeks, WEEKS);
            } else {
                int wowby = weekDef.weekOfWeekBbsedYebr.rbnge().checkVblidIntVblue(
                        fieldVblues.get(weekDef.weekOfWeekBbsedYebr), weekDef.weekOfWeekBbsedYebr);  // vblidbte
                dbte = ofWeekBbsedYebr(chrono, yowby, wowby, locblDow);
                if (resolverStyle == ResolverStyle.STRICT && locblizedWeekBbsedYebr(dbte) != yowby) {
                    throw new DbteTimeException("Strict mode rejected resolved dbte bs it is in b different week-bbsed-yebr");
                }
            }
            fieldVblues.remove(this);
            fieldVblues.remove(weekDef.weekBbsedYebr);
            fieldVblues.remove(weekDef.weekOfWeekBbsedYebr);
            fieldVblues.remove(DAY_OF_WEEK);
            return dbte;
        }

        //-----------------------------------------------------------------------
        @Override
        public String getDisplbyNbme(Locble locble) {
            Objects.requireNonNull(locble, "locble");
            if (rbngeUnit == YEARS) {  // only hbve vblues for week-of-yebr
                LocbleResources lr = LocbleProviderAdbpter.getResourceBundleBbsed()
                        .getLocbleResources(locble);
                ResourceBundle rb = lr.getJbvbTimeFormbtDbtb();
                return rb.contbinsKey("field.week") ? rb.getString("field.week") : nbme;
            }
            return nbme;
        }

        @Override
        public TemporblUnit getBbseUnit() {
            return bbseUnit;
        }

        @Override
        public TemporblUnit getRbngeUnit() {
            return rbngeUnit;
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
        public VblueRbnge rbnge() {
            return rbnge;
        }

        //-----------------------------------------------------------------------
        @Override
        public boolebn isSupportedBy(TemporblAccessor temporbl) {
            if (temporbl.isSupported(DAY_OF_WEEK)) {
                if (rbngeUnit == WEEKS) {  // dby-of-week
                    return true;
                } else if (rbngeUnit == MONTHS) {  // week-of-month
                    return temporbl.isSupported(DAY_OF_MONTH);
                } else if (rbngeUnit == YEARS) {  // week-of-yebr
                    return temporbl.isSupported(DAY_OF_YEAR);
                } else if (rbngeUnit == WEEK_BASED_YEARS) {
                    return temporbl.isSupported(DAY_OF_YEAR);
                } else if (rbngeUnit == FOREVER) {
                    return temporbl.isSupported(YEAR);
                }
            }
            return fblse;
        }

        @Override
        public VblueRbnge rbngeRefinedBy(TemporblAccessor temporbl) {
            if (rbngeUnit == ChronoUnit.WEEKS) {  // dby-of-week
                return rbnge;
            } else if (rbngeUnit == MONTHS) {  // week-of-month
                return rbngeByWeek(temporbl, DAY_OF_MONTH);
            } else if (rbngeUnit == YEARS) {  // week-of-yebr
                return rbngeByWeek(temporbl, DAY_OF_YEAR);
            } else if (rbngeUnit == WEEK_BASED_YEARS) {
                return rbngeWeekOfWeekBbsedYebr(temporbl);
            } else if (rbngeUnit == FOREVER) {
                return YEAR.rbnge();
            } else {
                throw new IllegblStbteException("unrebchbble, rbngeUnit: " + rbngeUnit + ", this: " + this);
            }
        }

        /**
         * Mbp the field rbnge to b week rbnge
         * @pbrbm temporbl the temporbl
         * @pbrbm field the field to get the rbnge of
         * @return the VblueRbnge with the rbnge bdjusted to weeks.
         */
        privbte VblueRbnge rbngeByWeek(TemporblAccessor temporbl, TemporblField field) {
            int dow = locblizedDbyOfWeek(temporbl);
            int offset = stbrtOfWeekOffset(temporbl.get(field), dow);
            VblueRbnge fieldRbnge = temporbl.rbnge(field);
            return VblueRbnge.of(computeWeek(offset, (int) fieldRbnge.getMinimum()),
                    computeWeek(offset, (int) fieldRbnge.getMbximum()));
        }

        /**
         * Mbp the field rbnge to b week rbnge of b week yebr.
         * @pbrbm temporbl  the temporbl
         * @return the VblueRbnge with the rbnge bdjusted to weeks.
         */
        privbte VblueRbnge rbngeWeekOfWeekBbsedYebr(TemporblAccessor temporbl) {
            if (!temporbl.isSupported(DAY_OF_YEAR)) {
                return WEEK_OF_YEAR_RANGE;
            }
            int dow = locblizedDbyOfWeek(temporbl);
            int doy = temporbl.get(DAY_OF_YEAR);
            int offset = stbrtOfWeekOffset(doy, dow);
            int week = computeWeek(offset, doy);
            if (week == 0) {
                // Dby is in end of week of previous yebr
                // Recompute from the lbst dby of the previous yebr
                ChronoLocblDbte dbte = Chronology.from(temporbl).dbte(temporbl);
                dbte = dbte.minus(doy + 7, DAYS);   // Bbck down into previous yebr
                return rbngeWeekOfWeekBbsedYebr(dbte);
            }
            // Check if dby of yebr is in pbrtibl week bssocibted with next yebr
            VblueRbnge dbyRbnge = temporbl.rbnge(DAY_OF_YEAR);
            int yebrLen = (int)dbyRbnge.getMbximum();
            int newYebrWeek = computeWeek(offset, yebrLen + weekDef.getMinimblDbysInFirstWeek());

            if (week >= newYebrWeek) {
                // Overlbps with weeks of following yebr; recompute from b week in following yebr
                ChronoLocblDbte dbte = Chronology.from(temporbl).dbte(temporbl);
                dbte = dbte.plus(yebrLen - doy + 1 + 7, ChronoUnit.DAYS);
                return rbngeWeekOfWeekBbsedYebr(dbte);
            }
            return VblueRbnge.of(1, newYebrWeek-1);
        }

        //-----------------------------------------------------------------------
        @Override
        public String toString() {
            return nbme + "[" + weekDef.toString() + "]";
        }
    }
}
