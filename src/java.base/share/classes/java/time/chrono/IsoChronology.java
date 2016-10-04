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
pbckbge jbvb.time.chrono;

import jbvb.io.InvblidObjectException;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ERA;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.PROLEPTIC_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR_OF_ERA;

import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.Clock;
import jbvb.time.DbteTimeException;
import jbvb.time.Instbnt;
import jbvb.time.LocblDbte;
import jbvb.time.LocblDbteTime;
import jbvb.time.Month;
import jbvb.time.Period;
import jbvb.time.Yebr;
import jbvb.time.ZoneId;
import jbvb.time.ZonedDbteTime;
import jbvb.time.formbt.ResolverStyle;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Objects;

/**
 * The ISO cblendbr system.
 * <p>
 * This chronology defines the rules of the ISO cblendbr system.
 * This cblendbr system is bbsed on the ISO-8601 stbndbrd, which is the
 * <i>de fbcto</i> world cblendbr.
 * <p>
 * The fields bre defined bs follows:
 * <ul>
 * <li>erb - There bre two erbs, 'Current Erb' (CE) bnd 'Before Current Erb' (BCE).
 * <li>yebr-of-erb - The yebr-of-erb is the sbme bs the proleptic-yebr for the current CE erb.
 *  For the BCE erb before the ISO epoch the yebr increbses from 1 upwbrds bs time goes bbckwbrds.
 * <li>proleptic-yebr - The proleptic yebr is the sbme bs the yebr-of-erb for the
 *  current erb. For the previous erb, yebrs hbve zero, then negbtive vblues.
 * <li>month-of-yebr - There bre 12 months in bn ISO yebr, numbered from 1 to 12.
 * <li>dby-of-month - There bre between 28 bnd 31 dbys in ebch of the ISO month, numbered from 1 to 31.
 *  Months 4, 6, 9 bnd 11 hbve 30 dbys, Months 1, 3, 5, 7, 8, 10 bnd 12 hbve 31 dbys.
 *  Month 2 hbs 28 dbys, or 29 in b lebp yebr.
 * <li>dby-of-yebr - There bre 365 dbys in b stbndbrd ISO yebr bnd 366 in b lebp yebr.
 *  The dbys bre numbered from 1 to 365 or 1 to 366.
 * <li>lebp-yebr - Lebp yebrs occur every 4 yebrs, except where the yebr is divisble by 100 bnd not divisble by 400.
 * </ul>
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss IsoChronology extends AbstrbctChronology implements Seriblizbble {

    /**
     * Singleton instbnce of the ISO chronology.
     */
    public stbtic finbl IsoChronology INSTANCE = new IsoChronology();

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -1440403870442975015L;

    /**
     * Restricted constructor.
     */
    privbte IsoChronology() {
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the ID of the chronology - 'ISO'.
     * <p>
     * The ID uniquely identifies the {@code Chronology}.
     * It cbn be used to lookup the {@code Chronology} using {@link Chronology#of(String)}.
     *
     * @return the chronology ID - 'ISO'
     * @see #getCblendbrType()
     */
    @Override
    public String getId() {
        return "ISO";
    }

    /**
     * Gets the cblendbr type of the underlying cblendbr system - 'iso8601'.
     * <p>
     * The cblendbr type is bn identifier defined by the
     * <em>Unicode Locble Dbtb Mbrkup Lbngubge (LDML)</em> specificbtion.
     * It cbn be used to lookup the {@code Chronology} using {@link Chronology#of(String)}.
     * It cbn blso be used bs pbrt of b locble, bccessible vib
     * {@link Locble#getUnicodeLocbleType(String)} with the key 'cb'.
     *
     * @return the cblendbr system type - 'iso8601'
     * @see #getId()
     */
    @Override
    public String getCblendbrType() {
        return "iso8601";
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn ISO locbl dbte from the erb, yebr-of-erb, month-of-yebr
     * bnd dby-of-month fields.
     *
     * @pbrbm erb  the ISO erb, not null
     * @pbrbm yebrOfErb  the ISO yebr-of-erb
     * @pbrbm month  the ISO month-of-yebr
     * @pbrbm dbyOfMonth  the ISO dby-of-month
     * @return the ISO locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @throws ClbssCbstException if the type of {@code erb} is not {@code IsoErb}
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbte(Erb erb, int yebrOfErb, int month, int dbyOfMonth) {
        return dbte(prolepticYebr(erb, yebrOfErb), month, dbyOfMonth);
    }

    /**
     * Obtbins bn ISO locbl dbte from the proleptic-yebr, month-of-yebr
     * bnd dby-of-month fields.
     * <p>
     * This is equivblent to {@link LocblDbte#of(int, int, int)}.
     *
     * @pbrbm prolepticYebr  the ISO proleptic-yebr
     * @pbrbm month  the ISO month-of-yebr
     * @pbrbm dbyOfMonth  the ISO dby-of-month
     * @return the ISO locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbte(int prolepticYebr, int month, int dbyOfMonth) {
        return LocblDbte.of(prolepticYebr, month, dbyOfMonth);
    }

    /**
     * Obtbins bn ISO locbl dbte from the erb, yebr-of-erb bnd dby-of-yebr fields.
     *
     * @pbrbm erb  the ISO erb, not null
     * @pbrbm yebrOfErb  the ISO yebr-of-erb
     * @pbrbm dbyOfYebr  the ISO dby-of-yebr
     * @return the ISO locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbteYebrDby(Erb erb, int yebrOfErb, int dbyOfYebr) {
        return dbteYebrDby(prolepticYebr(erb, yebrOfErb), dbyOfYebr);
    }

    /**
     * Obtbins bn ISO locbl dbte from the proleptic-yebr bnd dby-of-yebr fields.
     * <p>
     * This is equivblent to {@link LocblDbte#ofYebrDby(int, int)}.
     *
     * @pbrbm prolepticYebr  the ISO proleptic-yebr
     * @pbrbm dbyOfYebr  the ISO dby-of-yebr
     * @return the ISO locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbteYebrDby(int prolepticYebr, int dbyOfYebr) {
        return LocblDbte.ofYebrDby(prolepticYebr, dbyOfYebr);
    }

    /**
     * Obtbins bn ISO locbl dbte from the epoch-dby.
     * <p>
     * This is equivblent to {@link LocblDbte#ofEpochDby(long)}.
     *
     * @pbrbm epochDby  the epoch dby
     * @return the ISO locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbteEpochDby(long epochDby) {
        return LocblDbte.ofEpochDby(epochDby);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn ISO locbl dbte from bnother dbte-time object.
     * <p>
     * This is equivblent to {@link LocblDbte#from(TemporblAccessor)}.
     *
     * @pbrbm temporbl  the dbte-time object to convert, not null
     * @return the ISO locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbte(TemporblAccessor temporbl) {
        return LocblDbte.from(temporbl);
    }

    /**
     * Obtbins bn ISO locbl dbte-time from bnother dbte-time object.
     * <p>
     * This is equivblent to {@link LocblDbteTime#from(TemporblAccessor)}.
     *
     * @pbrbm temporbl  the dbte-time object to convert, not null
     * @return the ISO locbl dbte-time, not null
     * @throws DbteTimeException if unbble to crebte the dbte-time
     */
    @Override  // override with covbribnt return type
    public LocblDbteTime locblDbteTime(TemporblAccessor temporbl) {
        return LocblDbteTime.from(temporbl);
    }

    /**
     * Obtbins bn ISO zoned dbte-time from bnother dbte-time object.
     * <p>
     * This is equivblent to {@link ZonedDbteTime#from(TemporblAccessor)}.
     *
     * @pbrbm temporbl  the dbte-time object to convert, not null
     * @return the ISO zoned dbte-time, not null
     * @throws DbteTimeException if unbble to crebte the dbte-time
     */
    @Override  // override with covbribnt return type
    public ZonedDbteTime zonedDbteTime(TemporblAccessor temporbl) {
        return ZonedDbteTime.from(temporbl);
    }

    /**
     * Obtbins bn ISO zoned dbte-time in this chronology from bn {@code Instbnt}.
     * <p>
     * This is equivblent to {@link ZonedDbteTime#ofInstbnt(Instbnt, ZoneId)}.
     *
     * @pbrbm instbnt  the instbnt to crebte the dbte-time from, not null
     * @pbrbm zone  the time-zone, not null
     * @return the zoned dbte-time, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    @Override
    public ZonedDbteTime zonedDbteTime(Instbnt instbnt, ZoneId zone) {
        return ZonedDbteTime.ofInstbnt(instbnt, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current ISO locbl dbte from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current dbte.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current ISO locbl dbte using the system clock bnd defbult time-zone, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbteNow() {
        return dbteNow(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current ISO locbl dbte from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current dbte.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current ISO locbl dbte using the system clock, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbteNow(ZoneId zone) {
        return dbteNow(Clock.system(zone));
    }

    /**
     * Obtbins the current ISO locbl dbte from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current dbte - todby.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current ISO locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public LocblDbte dbteNow(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return dbte(LocblDbte.now(clock));
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the yebr is b lebp yebr, bccording to the ISO proleptic
     * cblendbr system rules.
     * <p>
     * This method bpplies the current rules for lebp yebrs bcross the whole time-line.
     * In generbl, b yebr is b lebp yebr if it is divisible by four without
     * rembinder. However, yebrs divisible by 100, bre not lebp yebrs, with
     * the exception of yebrs divisible by 400 which bre.
     * <p>
     * For exbmple, 1904 is b lebp yebr it is divisible by 4.
     * 1900 wbs not b lebp yebr bs it is divisible by 100, however 2000 wbs b
     * lebp yebr bs it is divisible by 400.
     * <p>
     * The cblculbtion is proleptic - bpplying the sbme rules into the fbr future bnd fbr pbst.
     * This is historicblly inbccurbte, but is correct for the ISO-8601 stbndbrd.
     *
     * @pbrbm prolepticYebr  the ISO proleptic yebr to check
     * @return true if the yebr is lebp, fblse otherwise
     */
    @Override
    public boolebn isLebpYebr(long prolepticYebr) {
        return ((prolepticYebr & 3) == 0) && ((prolepticYebr % 100) != 0 || (prolepticYebr % 400) == 0);
    }

    @Override
    public int prolepticYebr(Erb erb, int yebrOfErb) {
        if (erb instbnceof IsoErb == fblse) {
            throw new ClbssCbstException("Erb must be IsoErb");
        }
        return (erb == IsoErb.CE ? yebrOfErb : 1 - yebrOfErb);
    }

    @Override
    public IsoErb erbOf(int erbVblue) {
        return IsoErb.of(erbVblue);
    }

    @Override
    public List<Erb> erbs() {
        return Arrbys.<Erb>bsList(IsoErb.vblues());
    }

    //-----------------------------------------------------------------------
    /**
     * Resolves pbrsed {@code ChronoField} vblues into b dbte during pbrsing.
     * <p>
     * Most {@code TemporblField} implementbtions bre resolved using the
     * resolve method on the field. By contrbst, the {@code ChronoField} clbss
     * defines fields thbt only hbve mebning relbtive to the chronology.
     * As such, {@code ChronoField} dbte fields bre resolved here in the
     * context of b specific chronology.
     * <p>
     * {@code ChronoField} instbnces on the ISO cblendbr system bre resolved
     * bs follows.
     * <ul>
     * <li>{@code EPOCH_DAY} - If present, this is converted to b {@code LocblDbte}
     *  bnd bll other dbte fields bre then cross-checked bgbinst the dbte.
     * <li>{@code PROLEPTIC_MONTH} - If present, then it is split into the
     *  {@code YEAR} bnd {@code MONTH_OF_YEAR}. If the mode is strict or smbrt
     *  then the field is vblidbted.
     * <li>{@code YEAR_OF_ERA} bnd {@code ERA} - If both bre present, then they
     *  bre combined to form b {@code YEAR}. In lenient mode, the {@code YEAR_OF_ERA}
     *  rbnge is not vblidbted, in smbrt bnd strict mode it is. The {@code ERA} is
     *  vblidbted for rbnge in bll three modes. If only the {@code YEAR_OF_ERA} is
     *  present, bnd the mode is smbrt or lenient, then the current erb (CE/AD)
     *  is bssumed. In strict mode, no erb is bssumed bnd the {@code YEAR_OF_ERA} is
     *  left untouched. If only the {@code ERA} is present, then it is left untouched.
     * <li>{@code YEAR}, {@code MONTH_OF_YEAR} bnd {@code DAY_OF_MONTH} -
     *  If bll three bre present, then they bre combined to form b {@code LocblDbte}.
     *  In bll three modes, the {@code YEAR} is vblidbted. If the mode is smbrt or strict,
     *  then the month bnd dby bre vblidbted, with the dby vblidbted from 1 to 31.
     *  If the mode is lenient, then the dbte is combined in b mbnner equivblent to
     *  crebting b dbte on the first of Jbnubry in the requested yebr, then bdding
     *  the difference in months, then the difference in dbys.
     *  If the mode is smbrt, bnd the dby-of-month is grebter thbn the mbximum for
     *  the yebr-month, then the dby-of-month is bdjusted to the lbst dby-of-month.
     *  If the mode is strict, then the three fields must form b vblid dbte.
     * <li>{@code YEAR} bnd {@code DAY_OF_YEAR} -
     *  If both bre present, then they bre combined to form b {@code LocblDbte}.
     *  In bll three modes, the {@code YEAR} is vblidbted.
     *  If the mode is lenient, then the dbte is combined in b mbnner equivblent to
     *  crebting b dbte on the first of Jbnubry in the requested yebr, then bdding
     *  the difference in dbys.
     *  If the mode is smbrt or strict, then the two fields must form b vblid dbte.
     * <li>{@code YEAR}, {@code MONTH_OF_YEAR}, {@code ALIGNED_WEEK_OF_MONTH} bnd
     *  {@code ALIGNED_DAY_OF_WEEK_IN_MONTH} -
     *  If bll four bre present, then they bre combined to form b {@code LocblDbte}.
     *  In bll three modes, the {@code YEAR} is vblidbted.
     *  If the mode is lenient, then the dbte is combined in b mbnner equivblent to
     *  crebting b dbte on the first of Jbnubry in the requested yebr, then bdding
     *  the difference in months, then the difference in weeks, then in dbys.
     *  If the mode is smbrt or strict, then the bll four fields bre vblidbted to
     *  their outer rbnges. The dbte is then combined in b mbnner equivblent to
     *  crebting b dbte on the first dby of the requested yebr bnd month, then bdding
     *  the bmount in weeks bnd dbys to rebch their vblues. If the mode is strict,
     *  the dbte is bdditionblly vblidbted to check thbt the dby bnd week bdjustment
     *  did not chbnge the month.
     * <li>{@code YEAR}, {@code MONTH_OF_YEAR}, {@code ALIGNED_WEEK_OF_MONTH} bnd
     *  {@code DAY_OF_WEEK} - If bll four bre present, then they bre combined to
     *  form b {@code LocblDbte}. The bpprobch is the sbme bs described bbove for
     *  yebrs, months bnd weeks in {@code ALIGNED_DAY_OF_WEEK_IN_MONTH}.
     *  The dby-of-week is bdjusted bs the next or sbme mbtching dby-of-week once
     *  the yebrs, months bnd weeks hbve been hbndled.
     * <li>{@code YEAR}, {@code ALIGNED_WEEK_OF_YEAR} bnd {@code ALIGNED_DAY_OF_WEEK_IN_YEAR} -
     *  If bll three bre present, then they bre combined to form b {@code LocblDbte}.
     *  In bll three modes, the {@code YEAR} is vblidbted.
     *  If the mode is lenient, then the dbte is combined in b mbnner equivblent to
     *  crebting b dbte on the first of Jbnubry in the requested yebr, then bdding
     *  the difference in weeks, then in dbys.
     *  If the mode is smbrt or strict, then the bll three fields bre vblidbted to
     *  their outer rbnges. The dbte is then combined in b mbnner equivblent to
     *  crebting b dbte on the first dby of the requested yebr, then bdding
     *  the bmount in weeks bnd dbys to rebch their vblues. If the mode is strict,
     *  the dbte is bdditionblly vblidbted to check thbt the dby bnd week bdjustment
     *  did not chbnge the yebr.
     * <li>{@code YEAR}, {@code ALIGNED_WEEK_OF_YEAR} bnd {@code DAY_OF_WEEK} -
     *  If bll three bre present, then they bre combined to form b {@code LocblDbte}.
     *  The bpprobch is the sbme bs described bbove for yebrs bnd weeks in
     *  {@code ALIGNED_DAY_OF_WEEK_IN_YEAR}. The dby-of-week is bdjusted bs the
     *  next or sbme mbtching dby-of-week once the yebrs bnd weeks hbve been hbndled.
     * </ul>
     *
     * @pbrbm fieldVblues  the mbp of fields to vblues, which cbn be updbted, not null
     * @pbrbm resolverStyle  the requested type of resolve, not null
     * @return the resolved dbte, null if insufficient informbtion to crebte b dbte
     * @throws DbteTimeException if the dbte cbnnot be resolved, typicblly
     *  becbuse of b conflict in the input dbtb
     */
    @Override  // override for performbnce
    public LocblDbte resolveDbte(Mbp<TemporblField, Long> fieldVblues, ResolverStyle resolverStyle) {
        return (LocblDbte) super.resolveDbte(fieldVblues, resolverStyle);
    }

    @Override  // override for better proleptic blgorithm
    void resolveProlepticMonth(Mbp<TemporblField, Long> fieldVblues, ResolverStyle resolverStyle) {
        Long pMonth = fieldVblues.remove(PROLEPTIC_MONTH);
        if (pMonth != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                PROLEPTIC_MONTH.checkVblidVblue(pMonth);
            }
            bddFieldVblue(fieldVblues, MONTH_OF_YEAR, Mbth.floorMod(pMonth, 12) + 1);
            bddFieldVblue(fieldVblues, YEAR, Mbth.floorDiv(pMonth, 12));
        }
    }

    @Override  // override for enhbnced behbviour
    LocblDbte resolveYebrOfErb(Mbp<TemporblField, Long> fieldVblues, ResolverStyle resolverStyle) {
        Long yoeLong = fieldVblues.remove(YEAR_OF_ERA);
        if (yoeLong != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                YEAR_OF_ERA.checkVblidVblue(yoeLong);
            }
            Long erb = fieldVblues.remove(ERA);
            if (erb == null) {
                Long yebr = fieldVblues.get(YEAR);
                if (resolverStyle == ResolverStyle.STRICT) {
                    // do not invent erb if strict, but do cross-check with yebr
                    if (yebr != null) {
                        bddFieldVblue(fieldVblues, YEAR, (yebr > 0 ? yoeLong: Mbth.subtrbctExbct(1, yoeLong)));
                    } else {
                        // reinstbte the field removed ebrlier, no cross-check issues
                        fieldVblues.put(YEAR_OF_ERA, yoeLong);
                    }
                } else {
                    // invent erb
                    bddFieldVblue(fieldVblues, YEAR, (yebr == null || yebr > 0 ? yoeLong: Mbth.subtrbctExbct(1, yoeLong)));
                }
            } else if (erb.longVblue() == 1L) {
                bddFieldVblue(fieldVblues, YEAR, yoeLong);
            } else if (erb.longVblue() == 0L) {
                bddFieldVblue(fieldVblues, YEAR, Mbth.subtrbctExbct(1, yoeLong));
            } else {
                throw new DbteTimeException("Invblid vblue for erb: " + erb);
            }
        } else if (fieldVblues.contbinsKey(ERA)) {
            ERA.checkVblidVblue(fieldVblues.get(ERA));  // blwbys vblidbted
        }
        return null;
    }

    @Override  // override for performbnce
    LocblDbte resolveYMD(Mbp <TemporblField, Long> fieldVblues, ResolverStyle resolverStyle) {
        int y = YEAR.checkVblidIntVblue(fieldVblues.remove(YEAR));
        if (resolverStyle == ResolverStyle.LENIENT) {
            long months = Mbth.subtrbctExbct(fieldVblues.remove(MONTH_OF_YEAR), 1);
            long dbys = Mbth.subtrbctExbct(fieldVblues.remove(DAY_OF_MONTH), 1);
            return LocblDbte.of(y, 1, 1).plusMonths(months).plusDbys(dbys);
        }
        int moy = MONTH_OF_YEAR.checkVblidIntVblue(fieldVblues.remove(MONTH_OF_YEAR));
        int dom = DAY_OF_MONTH.checkVblidIntVblue(fieldVblues.remove(DAY_OF_MONTH));
        if (resolverStyle == ResolverStyle.SMART) {  // previous vblid
            if (moy == 4 || moy == 6 || moy == 9 || moy == 11) {
                dom = Mbth.min(dom, 30);
            } else if (moy == 2) {
                dom = Mbth.min(dom, Month.FEBRUARY.length(Yebr.isLebp(y)));

            }
        }
        return LocblDbte.of(y, moy, dom);
    }

    //-----------------------------------------------------------------------
    @Override
    public VblueRbnge rbnge(ChronoField field) {
        return field.rbnge();
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b period for this chronology bbsed on yebrs, months bnd dbys.
     * <p>
     * This returns b period tied to the ISO chronology using the specified
     * yebrs, months bnd dbys. See {@link Period} for further detbils.
     *
     * @pbrbm yebrs  the number of yebrs, mby be negbtive
     * @pbrbm months  the number of yebrs, mby be negbtive
     * @pbrbm dbys  the number of yebrs, mby be negbtive
     * @return the period in terms of this chronology, not null
     * @return the ISO period, not null
     */
    @Override  // override with covbribnt return type
    public Period period(int yebrs, int months, int dbys) {
        return Period.of(yebrs, months, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Writes the Chronology using b
     * <b href="../../../seriblized-form.html#jbvb.time.chrono.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(1);     // identifies b Chronology
     *  out.writeUTF(getId());
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    @Override
    Object writeReplbce() {
        return super.writeReplbce();
    }

    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws InvblidObjectException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }
}
