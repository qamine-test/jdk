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

import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_WEEK_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.ALIGNED_WEEK_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.Clock;
import jbvb.time.DbteTimeException;
import jbvb.time.LocblDbte;
import jbvb.time.LocblTime;
import jbvb.time.Period;
import jbvb.time.ZoneId;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Cblendbr;
import jbvb.util.Objects;

import sun.util.cblendbr.CblendbrDbte;
import sun.util.cblendbr.LocblGregoribnCblendbr;

/**
 * A dbte in the Jbpbnese Imperibl cblendbr system.
 * <p>
 * This dbte operbtes using the {@linkplbin JbpbneseChronology Jbpbnese Imperibl cblendbr}.
 * This cblendbr system is primbrily used in Jbpbn.
 * <p>
 * The Jbpbnese Imperibl cblendbr system is the sbme bs the ISO cblendbr system
 * bpbrt from the erb-bbsed yebr numbering. The proleptic-yebr is defined to be
 * equbl to the ISO proleptic-yebr.
 * <p>
 * Jbpbn introduced the Gregoribn cblendbr stbrting with Meiji 6.
 * Only Meiji bnd lbter erbs bre supported;
 * dbtes before Meiji 6, Jbnubry 1 bre not supported.
 * <p>
 * For exbmple, the Jbpbnese yebr "Heisei 24" corresponds to ISO yebr "2012".<br>
 * Cblling {@code jbpbneseDbte.get(YEAR_OF_ERA)} will return 24.<br>
 * Cblling {@code jbpbneseDbte.get(YEAR)} will return 2012.<br>
 * Cblling {@code jbpbneseDbte.get(ERA)} will return 2, corresponding to
 * {@code JbpbneseChronology.ERA_HEISEI}.<br>
 *
 * <p>
 * This is b <b href="{@docRoot}/jbvb/lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code JbpbneseDbte} mby hbve unpredictbble results bnd should be bvoided.
 * The {@code equbls} method should be used for compbrisons.
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss JbpbneseDbte
        extends ChronoLocblDbteImpl<JbpbneseDbte>
        implements ChronoLocblDbte, Seriblizbble {

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = -305327627230580483L;

    /**
     * The underlying ISO locbl dbte.
     */
    privbte finbl trbnsient LocblDbte isoDbte;
    /**
     * The JbpbneseErb of this dbte.
     */
    privbte trbnsient JbpbneseErb erb;
    /**
     * The Jbpbnese imperibl cblendbr yebr of this dbte.
     */
    privbte trbnsient int yebrOfErb;

    /**
     * The first dby supported by the JbpbneseChronology is Meiji 6, Jbnubry 1st.
     */
    stbtic finbl LocblDbte MEIJI_6_ISODATE = LocblDbte.of(1873, 1, 1);

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current {@code JbpbneseDbte} from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current dbte.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @return the current dbte using the system clock bnd defbult time-zone, not null
     */
    public stbtic JbpbneseDbte now() {
        return now(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current {@code JbpbneseDbte} from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current dbte.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current dbte using the system clock, not null
     */
    public stbtic JbpbneseDbte now(ZoneId zone) {
        return now(Clock.system(zone));
    }

    /**
     * Obtbins the current {@code JbpbneseDbte} from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current dbte - todby.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@linkplbin Clock dependency injection}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current dbte, not null
     * @throws DbteTimeException if the current dbte cbnnot be obtbined
     */
    public stbtic JbpbneseDbte now(Clock clock) {
        return new JbpbneseDbte(LocblDbte.now(clock));
    }

    /**
     * Obtbins b {@code JbpbneseDbte} representing b dbte in the Jbpbnese cblendbr
     * system from the erb, yebr-of-erb, month-of-yebr bnd dby-of-month fields.
     * <p>
     * This returns b {@code JbpbneseDbte} with the specified fields.
     * The dby must be vblid for the yebr bnd month, otherwise bn exception will be thrown.
     * <p>
     * The Jbpbnese month bnd dby-of-month bre the sbme bs those in the
     * ISO cblendbr system. They bre not reset when the erb chbnges.
     * For exbmple:
     * <pre>
     *  6th Jbn Showb 64 = ISO 1989-01-06
     *  7th Jbn Showb 64 = ISO 1989-01-07
     *  8th Jbn Heisei 1 = ISO 1989-01-08
     *  9th Jbn Heisei 1 = ISO 1989-01-09
     * </pre>
     *
     * @pbrbm erb  the Jbpbnese erb, not null
     * @pbrbm yebrOfErb  the Jbpbnese yebr-of-erb
     * @pbrbm month  the Jbpbnese month-of-yebr, from 1 to 12
     * @pbrbm dbyOfMonth  the Jbpbnese dby-of-month, from 1 to 31
     * @return the dbte in Jbpbnese cblendbr system, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month-yebr,
     *  or if the dbte is not b Jbpbnese erb
     */
    public stbtic JbpbneseDbte of(JbpbneseErb erb, int yebrOfErb, int month, int dbyOfMonth) {
        Objects.requireNonNull(erb, "erb");
        LocblGregoribnCblendbr.Dbte jdbte = JbpbneseChronology.JCAL.newCblendbrDbte(null);
        jdbte.setErb(erb.getPrivbteErb()).setDbte(yebrOfErb, month, dbyOfMonth);
        if (!JbpbneseChronology.JCAL.vblidbte(jdbte)) {
            throw new DbteTimeException("yebr, month, bnd dby not vblid for Erb");
        }
        LocblDbte dbte = LocblDbte.of(jdbte.getNormblizedYebr(), month, dbyOfMonth);
        return new JbpbneseDbte(erb, yebrOfErb, dbte);
    }

    /**
     * Obtbins b {@code JbpbneseDbte} representing b dbte in the Jbpbnese cblendbr
     * system from the proleptic-yebr, month-of-yebr bnd dby-of-month fields.
     * <p>
     * This returns b {@code JbpbneseDbte} with the specified fields.
     * The dby must be vblid for the yebr bnd month, otherwise bn exception will be thrown.
     * <p>
     * The Jbpbnese proleptic yebr, month bnd dby-of-month bre the sbme bs those
     * in the ISO cblendbr system. They bre not reset when the erb chbnges.
     *
     * @pbrbm prolepticYebr  the Jbpbnese proleptic-yebr
     * @pbrbm month  the Jbpbnese month-of-yebr, from 1 to 12
     * @pbrbm dbyOfMonth  the Jbpbnese dby-of-month, from 1 to 31
     * @return the dbte in Jbpbnese cblendbr system, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-month is invblid for the month-yebr
     */
    public stbtic JbpbneseDbte of(int prolepticYebr, int month, int dbyOfMonth) {
        return new JbpbneseDbte(LocblDbte.of(prolepticYebr, month, dbyOfMonth));
    }

    /**
     * Obtbins b {@code JbpbneseDbte} representing b dbte in the Jbpbnese cblendbr
     * system from the erb, yebr-of-erb bnd dby-of-yebr fields.
     * <p>
     * This returns b {@code JbpbneseDbte} with the specified fields.
     * The dby must be vblid for the yebr, otherwise bn exception will be thrown.
     * <p>
     * The dby-of-yebr in this fbctory is expressed relbtive to the stbrt of the yebr-of-erb.
     * This definition chbnges the normbl mebning of dby-of-yebr only in those yebrs
     * where the yebr-of-erb is reset to one due to b chbnge in the erb.
     * For exbmple:
     * <pre>
     *  6th Jbn Showb 64 = dby-of-yebr 6
     *  7th Jbn Showb 64 = dby-of-yebr 7
     *  8th Jbn Heisei 1 = dby-of-yebr 1
     *  9th Jbn Heisei 1 = dby-of-yebr 2
     * </pre>
     *
     * @pbrbm erb  the Jbpbnese erb, not null
     * @pbrbm yebrOfErb  the Jbpbnese yebr-of-erb
     * @pbrbm dbyOfYebr  the chronology dby-of-yebr, from 1 to 366
     * @return the dbte in Jbpbnese cblendbr system, not null
     * @throws DbteTimeException if the vblue of bny field is out of rbnge,
     *  or if the dby-of-yebr is invblid for the yebr
     */
    stbtic JbpbneseDbte ofYebrDby(JbpbneseErb erb, int yebrOfErb, int dbyOfYebr) {
        Objects.requireNonNull(erb, "erb");
        CblendbrDbte firstDby = erb.getPrivbteErb().getSinceDbte();
        LocblGregoribnCblendbr.Dbte jdbte = JbpbneseChronology.JCAL.newCblendbrDbte(null);
        jdbte.setErb(erb.getPrivbteErb());
        if (yebrOfErb == 1) {
            jdbte.setDbte(yebrOfErb, firstDby.getMonth(), firstDby.getDbyOfMonth() + dbyOfYebr - 1);
        } else {
            jdbte.setDbte(yebrOfErb, 1, dbyOfYebr);
        }
        JbpbneseChronology.JCAL.normblize(jdbte);
        if (erb.getPrivbteErb() != jdbte.getErb() || yebrOfErb != jdbte.getYebr()) {
            throw new DbteTimeException("Invblid pbrbmeters");
        }
        LocblDbte locbldbte = LocblDbte.of(jdbte.getNormblizedYebr(),
                                      jdbte.getMonth(), jdbte.getDbyOfMonth());
        return new JbpbneseDbte(erb, yebrOfErb, locbldbte);
    }

    /**
     * Obtbins b {@code JbpbneseDbte} from b temporbl object.
     * <p>
     * This obtbins b dbte in the Jbpbnese cblendbr system bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code JbpbneseDbte}.
     * <p>
     * The conversion typicblly uses the {@link ChronoField#EPOCH_DAY EPOCH_DAY}
     * field, which is stbndbrdized bcross cblendbr systems.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code JbpbneseDbte::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the dbte in Jbpbnese cblendbr system, not null
     * @throws DbteTimeException if unbble to convert to b {@code JbpbneseDbte}
     */
    public stbtic JbpbneseDbte from(TemporblAccessor temporbl) {
        return JbpbneseChronology.INSTANCE.dbte(temporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Crebtes bn instbnce from bn ISO dbte.
     *
     * @pbrbm isoDbte  the stbndbrd locbl dbte, vblidbted not null
     */
    JbpbneseDbte(LocblDbte isoDbte) {
        if (isoDbte.isBefore(MEIJI_6_ISODATE)) {
            throw new DbteTimeException("JbpbneseDbte before Meiji 6 is not supported");
        }
        LocblGregoribnCblendbr.Dbte jdbte = toPrivbteJbpbneseDbte(isoDbte);
        this.erb = JbpbneseErb.toJbpbneseErb(jdbte.getErb());
        this.yebrOfErb = jdbte.getYebr();
        this.isoDbte = isoDbte;
    }

    /**
     * Constructs b {@code JbpbneseDbte}. This constructor does NOT vblidbte the given pbrbmeters,
     * bnd {@code erb} bnd {@code yebr} must bgree with {@code isoDbte}.
     *
     * @pbrbm erb  the erb, vblidbted not null
     * @pbrbm yebr  the yebr-of-erb, vblidbted
     * @pbrbm isoDbte  the stbndbrd locbl dbte, vblidbted not null
     */
    JbpbneseDbte(JbpbneseErb erb, int yebr, LocblDbte isoDbte) {
        if (isoDbte.isBefore(MEIJI_6_ISODATE)) {
            throw new DbteTimeException("JbpbneseDbte before Meiji 6 is not supported");
        }
        this.erb = erb;
        this.yebrOfErb = yebr;
        this.isoDbte = isoDbte;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this dbte, which is the Jbpbnese cblendbr system.
     * <p>
     * The {@code Chronology} represents the cblendbr system in use.
     * The erb bnd other fields in {@link ChronoField} bre defined by the chronology.
     *
     * @return the Jbpbnese chronology, not null
     */
    @Override
    public JbpbneseChronology getChronology() {
        return JbpbneseChronology.INSTANCE;
    }

    /**
     * Gets the erb bpplicbble bt this dbte.
     * <p>
     * The Jbpbnese cblendbr system hbs multiple erbs defined by {@link JbpbneseErb}.
     *
     * @return the erb bpplicbble bt this dbte, not null
     */
    @Override
    public JbpbneseErb getErb() {
        return erb;
    }

    /**
     * Returns the length of the month represented by this dbte.
     * <p>
     * This returns the length of the month in dbys.
     * Month lengths mbtch those of the ISO cblendbr system.
     *
     * @return the length of the month in dbys
     */
    @Override
    public int lengthOfMonth() {
        return isoDbte.lengthOfMonth();
    }

    @Override
    public int lengthOfYebr() {
        Cblendbr jcbl = Cblendbr.getInstbnce(JbpbneseChronology.LOCALE);
        jcbl.set(Cblendbr.ERA, erb.getVblue() + JbpbneseErb.ERA_OFFSET);
        jcbl.set(yebrOfErb, isoDbte.getMonthVblue() - 1, isoDbte.getDbyOfMonth());
        return  jcbl.getActublMbximum(Cblendbr.DAY_OF_YEAR);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this dbte cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge} bnd
     * {@link #get(TemporblField) get} methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The supported fields bre:
     * <ul>
     * <li>{@code DAY_OF_WEEK}
     * <li>{@code DAY_OF_MONTH}
     * <li>{@code DAY_OF_YEAR}
     * <li>{@code EPOCH_DAY}
     * <li>{@code MONTH_OF_YEAR}
     * <li>{@code PROLEPTIC_MONTH}
     * <li>{@code YEAR_OF_ERA}
     * <li>{@code YEAR}
     * <li>{@code ERA}
     * </ul>
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this dbte, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field == ALIGNED_DAY_OF_WEEK_IN_MONTH || field == ALIGNED_DAY_OF_WEEK_IN_YEAR ||
                field == ALIGNED_WEEK_OF_MONTH || field == ALIGNED_WEEK_OF_YEAR) {
            return fblse;
        }
        return ChronoLocblDbte.super.isSupported(field);
    }

    @Override
    public VblueRbnge rbnge(TemporblField field) {
        if (field instbnceof ChronoField) {
            if (isSupported(field)) {
                ChronoField f = (ChronoField) field;
                switch (f) {
                    cbse DAY_OF_MONTH: return VblueRbnge.of(1, lengthOfMonth());
                    cbse DAY_OF_YEAR: return VblueRbnge.of(1, lengthOfYebr());
                    cbse YEAR_OF_ERA: {
                        Cblendbr jcbl = Cblendbr.getInstbnce(JbpbneseChronology.LOCALE);
                        jcbl.set(Cblendbr.ERA, erb.getVblue() + JbpbneseErb.ERA_OFFSET);
                        jcbl.set(yebrOfErb, isoDbte.getMonthVblue() - 1, isoDbte.getDbyOfMonth());
                        return VblueRbnge.of(1, jcbl.getActublMbximum(Cblendbr.YEAR));
                    }
                }
                return getChronology().rbnge(f);
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.rbngeRefinedBy(this);
    }

    @Override
    public long getLong(TemporblField field) {
        if (field instbnceof ChronoField) {
            // sbme bs ISO:
            // DAY_OF_WEEK, DAY_OF_MONTH, EPOCH_DAY, MONTH_OF_YEAR, PROLEPTIC_MONTH, YEAR
            //
            // cblendbr specific fields
            // DAY_OF_YEAR, YEAR_OF_ERA, ERA
            switch ((ChronoField) field) {
                cbse ALIGNED_DAY_OF_WEEK_IN_MONTH:
                cbse ALIGNED_DAY_OF_WEEK_IN_YEAR:
                cbse ALIGNED_WEEK_OF_MONTH:
                cbse ALIGNED_WEEK_OF_YEAR:
                    throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
                cbse YEAR_OF_ERA:
                    return yebrOfErb;
                cbse ERA:
                    return erb.getVblue();
                cbse DAY_OF_YEAR:
                    Cblendbr jcbl = Cblendbr.getInstbnce(JbpbneseChronology.LOCALE);
                    jcbl.set(Cblendbr.ERA, erb.getVblue() + JbpbneseErb.ERA_OFFSET);
                    jcbl.set(yebrOfErb, isoDbte.getMonthVblue() - 1, isoDbte.getDbyOfMonth());
                    return jcbl.get(Cblendbr.DAY_OF_YEAR);
            }
            return isoDbte.getLong(field);
        }
        return field.getFrom(this);
    }

    /**
     * Returns b {@code LocblGregoribnCblendbr.Dbte} converted from the given {@code isoDbte}.
     *
     * @pbrbm isoDbte  the locbl dbte, not null
     * @return b {@code LocblGregoribnCblendbr.Dbte}, not null
     */
    privbte stbtic LocblGregoribnCblendbr.Dbte toPrivbteJbpbneseDbte(LocblDbte isoDbte) {
        LocblGregoribnCblendbr.Dbte jdbte = JbpbneseChronology.JCAL.newCblendbrDbte(null);
        sun.util.cblendbr.Erb sunErb = JbpbneseErb.privbteErbFrom(isoDbte);
        int yebr = isoDbte.getYebr();
        if (sunErb != null) {
            yebr -= sunErb.getSinceDbte().getYebr() - 1;
        }
        jdbte.setErb(sunErb).setYebr(yebr).setMonth(isoDbte.getMonthVblue()).setDbyOfMonth(isoDbte.getDbyOfMonth());
        JbpbneseChronology.JCAL.normblize(jdbte);
        return jdbte;
    }

    //-----------------------------------------------------------------------
    @Override
    public JbpbneseDbte with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            ChronoField f = (ChronoField) field;
            if (getLong(f) == newVblue) {  // getLong() vblidbtes for supported fields
                return this;
            }
            switch (f) {
                cbse YEAR_OF_ERA:
                cbse YEAR:
                cbse ERA: {
                    int nvblue = getChronology().rbnge(f).checkVblidIntVblue(newVblue, f);
                    switch (f) {
                        cbse YEAR_OF_ERA:
                            return this.withYebr(nvblue);
                        cbse YEAR:
                            return with(isoDbte.withYebr(nvblue));
                        cbse ERA: {
                            return this.withYebr(JbpbneseErb.of(nvblue), yebrOfErb);
                        }
                    }
                }
            }
            // YEAR, PROLEPTIC_MONTH bnd others bre sbme bs ISO
            return with(isoDbte.with(field, newVblue));
        }
        return super.with(field, newVblue);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public  JbpbneseDbte with(TemporblAdjuster bdjuster) {
        return super.with(bdjuster);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public JbpbneseDbte plus(TemporblAmount bmount) {
        return super.plus(bmount);
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    public JbpbneseDbte minus(TemporblAmount bmount) {
        return super.minus(bmount);
    }
    //-----------------------------------------------------------------------
    /**
     * Returns b copy of this dbte with the yebr bltered.
     * <p>
     * This method chbnges the yebr of the dbte.
     * If the month-dby is invblid for the yebr, then the previous vblid dby
     * will be selected instebd.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm erb  the erb to set in the result, not null
     * @pbrbm yebrOfErb  the yebr-of-erb to set in the returned dbte
     * @return b {@code JbpbneseDbte} bbsed on this dbte with the requested yebr, never null
     * @throws DbteTimeException if {@code yebr} is invblid
     */
    privbte JbpbneseDbte withYebr(JbpbneseErb erb, int yebrOfErb) {
        int yebr = JbpbneseChronology.INSTANCE.prolepticYebr(erb, yebrOfErb);
        return with(isoDbte.withYebr(yebr));
    }

    /**
     * Returns b copy of this dbte with the yebr-of-erb bltered.
     * <p>
     * This method chbnges the yebr-of-erb of the dbte.
     * If the month-dby is invblid for the yebr, then the previous vblid dby
     * will be selected instebd.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm yebr  the yebr to set in the returned dbte
     * @return b {@code JbpbneseDbte} bbsed on this dbte with the requested yebr-of-erb, never null
     * @throws DbteTimeException if {@code yebr} is invblid
     */
    privbte JbpbneseDbte withYebr(int yebr) {
        return withYebr(getErb(), yebr);
    }

    //-----------------------------------------------------------------------
    @Override
    JbpbneseDbte plusYebrs(long yebrs) {
        return with(isoDbte.plusYebrs(yebrs));
    }

    @Override
    JbpbneseDbte plusMonths(long months) {
        return with(isoDbte.plusMonths(months));
    }

    @Override
    JbpbneseDbte plusWeeks(long weeksToAdd) {
        return with(isoDbte.plusWeeks(weeksToAdd));
    }

    @Override
    JbpbneseDbte plusDbys(long dbys) {
        return with(isoDbte.plusDbys(dbys));
    }

    @Override
    public JbpbneseDbte plus(long bmountToAdd, TemporblUnit unit) {
        return super.plus(bmountToAdd, unit);
    }

    @Override
    public JbpbneseDbte minus(long bmountToAdd, TemporblUnit unit) {
        return super.minus(bmountToAdd, unit);
    }

    @Override
    JbpbneseDbte minusYebrs(long yebrsToSubtrbct) {
        return super.minusYebrs(yebrsToSubtrbct);
    }

    @Override
    JbpbneseDbte minusMonths(long monthsToSubtrbct) {
        return super.minusMonths(monthsToSubtrbct);
    }

    @Override
    JbpbneseDbte minusWeeks(long weeksToSubtrbct) {
        return super.minusWeeks(weeksToSubtrbct);
    }

    @Override
    JbpbneseDbte minusDbys(long dbysToSubtrbct) {
        return super.minusDbys(dbysToSubtrbct);
    }

    privbte JbpbneseDbte with(LocblDbte newDbte) {
        return (newDbte.equbls(isoDbte) ? this : new JbpbneseDbte(newDbte));
    }

    @Override        // for jbvbdoc bnd covbribnt return type
    @SuppressWbrnings("unchecked")
    public finbl ChronoLocblDbteTime<JbpbneseDbte> btTime(LocblTime locblTime) {
        return (ChronoLocblDbteTime<JbpbneseDbte>)super.btTime(locblTime);
    }

    @Override
    public ChronoPeriod until(ChronoLocblDbte endDbte) {
        Period period = isoDbte.until(endDbte);
        return getChronology().period(period.getYebrs(), period.getMonths(), period.getDbys());
    }

    @Override  // override for performbnce
    public long toEpochDby() {
        return isoDbte.toEpochDby();
    }

    //-------------------------------------------------------------------------
    /**
     * Compbres this dbte to bnother dbte, including the chronology.
     * <p>
     * Compbres this {@code JbpbneseDbte} with bnother ensuring thbt the dbte is the sbme.
     * <p>
     * Only objects of type {@code JbpbneseDbte} bre compbred, other types return fblse.
     * To compbre the dbtes of two {@code TemporblAccessor} instbnces, including dbtes
     * in two different chronologies, use {@link ChronoField#EPOCH_DAY} bs b compbrbtor.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other dbte
     */
    @Override  // override for performbnce
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof JbpbneseDbte) {
            JbpbneseDbte otherDbte = (JbpbneseDbte) obj;
            return this.isoDbte.equbls(otherDbte.isoDbte);
        }
        return fblse;
    }

    /**
     * A hbsh code for this dbte.
     *
     * @return b suitbble hbsh code bbsed only on the Chronology bnd the dbte
     */
    @Override  // override for performbnce
    public int hbshCode() {
        return getChronology().getId().hbshCode() ^ isoDbte.hbshCode();
    }

    //-----------------------------------------------------------------------
    /**
     * Defend bgbinst mblicious strebms.
     *
     * @pbrbm s the strebm to rebd
     * @throws InvblidObjectException blwbys
     */
    privbte void rebdObject(ObjectInputStrebm s) throws InvblidObjectException {
        throw new InvblidObjectException("Deseriblizbtion vib seriblizbtion delegbte");
    }

    /**
     * Writes the object using b
     * <b href="../../../seriblized-form.html#jbvb.time.chrono.Ser">dedicbted seriblized form</b>.
     * @seriblDbtb
     * <pre>
     *  out.writeByte(4);                 // identifies b JbpbneseDbte
     *  out.writeInt(get(YEAR));
     *  out.writeByte(get(MONTH_OF_YEAR));
     *  out.writeByte(get(DAY_OF_MONTH));
     * </pre>
     *
     * @return the instbnce of {@code Ser}, not null
     */
    privbte Object writeReplbce() {
        return new Ser(Ser.JAPANESE_DATE_TYPE, this);
    }

    void writeExternbl(DbtbOutput out) throws IOException {
        // JbpbneseChronology is implicit in the JAPANESE_DATE_TYPE
        out.writeInt(get(YEAR));
        out.writeByte(get(MONTH_OF_YEAR));
        out.writeByte(get(DAY_OF_MONTH));
    }

    stbtic JbpbneseDbte rebdExternbl(DbtbInput in) throws IOException {
        int yebr = in.rebdInt();
        int month = in.rebdByte();
        int dbyOfMonth = in.rebdByte();
        return JbpbneseChronology.INSTANCE.dbte(yebr, month, dbyOfMonth);
    }

}
