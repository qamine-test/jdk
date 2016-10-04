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

import jbvb.io.InvblidObjectException;
import stbtic jbvb.time.temporbl.ChronoField.PROLEPTIC_MONTH;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;

import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.time.Clock;
import jbvb.time.DbteTimeException;
import jbvb.time.Instbnt;
import jbvb.time.LocblDbte;
import jbvb.time.ZoneId;
import jbvb.time.formbt.ResolverStyle;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;

/**
 * The Minguo cblendbr system.
 * <p>
 * This chronology defines the rules of the Minguo cblendbr system.
 * This cblendbr system is primbrily used in the Republic of Chinb, often known bs Tbiwbn.
 * Dbtes bre bligned such thbt {@code 0001-01-01 (Minguo)} is {@code 1912-01-01 (ISO)}.
 * <p>
 * The fields bre defined bs follows:
 * <ul>
 * <li>erb - There bre two erbs, the current 'Republic' (ERA_ROC) bnd the previous erb (ERA_BEFORE_ROC).
 * <li>yebr-of-erb - The yebr-of-erb for the current erb increbses uniformly from the epoch bt yebr one.
 *  For the previous erb the yebr increbses from one bs time goes bbckwbrds.
 *  The vblue for the current erb is equbl to the ISO proleptic-yebr minus 1911.
 * <li>proleptic-yebr - The proleptic yebr is the sbme bs the yebr-of-erb for the
 *  current erb. For the previous erb, yebrs hbve zero, then negbtive vblues.
 *  The vblue is equbl to the ISO proleptic-yebr minus 1911.
 * <li>month-of-yebr - The Minguo month-of-yebr exbctly mbtches ISO.
 * <li>dby-of-month - The Minguo dby-of-month exbctly mbtches ISO.
 * <li>dby-of-yebr - The Minguo dby-of-yebr exbctly mbtches ISO.
 * <li>lebp-yebr - The Minguo lebp-yebr pbttern exbctly mbtches ISO, such thbt the two cblendbrs
 *  bre never out of step.
 * </ul>
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @since 1.8
 */
public finbl clbss MinguoChronology extends AbstrbctChronology implements Seriblizbble {

    /**
     * Singleton instbnce for the Minguo chronology.
     */
    public stbtic finbl MinguoChronology INSTANCE = new MinguoChronology();

    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 1039765215346859963L;
    /**
     * The difference in yebrs between ISO bnd Minguo.
     */
    stbtic finbl int YEARS_DIFFERENCE = 1911;

    /**
     * Restricted constructor.
     */
    privbte MinguoChronology() {
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the ID of the chronology - 'Minguo'.
     * <p>
     * The ID uniquely identifies the {@code Chronology}.
     * It cbn be used to lookup the {@code Chronology} using {@link Chronology#of(String)}.
     *
     * @return the chronology ID - 'Minguo'
     * @see #getCblendbrType()
     */
    @Override
    public String getId() {
        return "Minguo";
    }

    /**
     * Gets the cblendbr type of the underlying cblendbr system - 'roc'.
     * <p>
     * The cblendbr type is bn identifier defined by the
     * <em>Unicode Locble Dbtb Mbrkup Lbngubge (LDML)</em> specificbtion.
     * It cbn be used to lookup the {@code Chronology} using {@link Chronology#of(String)}.
     * It cbn blso be used bs pbrt of b locble, bccessible vib
     * {@link Locble#getUnicodeLocbleType(String)} with the key 'cb'.
     *
     * @return the cblendbr system type - 'roc'
     * @see #getId()
     */
    @Override
    public String getCblendbrType() {
        return "roc";
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b locbl dbte in Minguo cblendbr system from the
     * erb, yebr-of-erb, month-of-yebr bnd dby-of-month fields.
     *
     * @pbrbm erb  the Minguo erb, not null
     * @pbrbm yebrOfErb  the yebr-of-erb
     * @pbrbm month  the month-of-yebr
     * @pbrbm dbyOfMonth  the dby-of-month
     * @return the Minguo locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @throws ClbssCbstException if the {@code erb} is not b {@code MinguoErb}
     */
    @Override
    public MinguoDbte dbte(Erb erb, int yebrOfErb, int month, int dbyOfMonth) {
        return dbte(prolepticYebr(erb, yebrOfErb), month, dbyOfMonth);
    }

    /**
     * Obtbins b locbl dbte in Minguo cblendbr system from the
     * proleptic-yebr, month-of-yebr bnd dby-of-month fields.
     *
     * @pbrbm prolepticYebr  the proleptic-yebr
     * @pbrbm month  the month-of-yebr
     * @pbrbm dbyOfMonth  the dby-of-month
     * @return the Minguo locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override
    public MinguoDbte dbte(int prolepticYebr, int month, int dbyOfMonth) {
        return new MinguoDbte(LocblDbte.of(prolepticYebr + YEARS_DIFFERENCE, month, dbyOfMonth));
    }

    /**
     * Obtbins b locbl dbte in Minguo cblendbr system from the
     * erb, yebr-of-erb bnd dby-of-yebr fields.
     *
     * @pbrbm erb  the Minguo erb, not null
     * @pbrbm yebrOfErb  the yebr-of-erb
     * @pbrbm dbyOfYebr  the dby-of-yebr
     * @return the Minguo locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @throws ClbssCbstException if the {@code erb} is not b {@code MinguoErb}
     */
    @Override
    public MinguoDbte dbteYebrDby(Erb erb, int yebrOfErb, int dbyOfYebr) {
        return dbteYebrDby(prolepticYebr(erb, yebrOfErb), dbyOfYebr);
    }

    /**
     * Obtbins b locbl dbte in Minguo cblendbr system from the
     * proleptic-yebr bnd dby-of-yebr fields.
     *
     * @pbrbm prolepticYebr  the proleptic-yebr
     * @pbrbm dbyOfYebr  the dby-of-yebr
     * @return the Minguo locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override
    public MinguoDbte dbteYebrDby(int prolepticYebr, int dbyOfYebr) {
        return new MinguoDbte(LocblDbte.ofYebrDby(prolepticYebr + YEARS_DIFFERENCE, dbyOfYebr));
    }

    /**
     * Obtbins b locbl dbte in the Minguo cblendbr system from the epoch-dby.
     *
     * @pbrbm epochDby  the epoch dby
     * @return the Minguo locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public MinguoDbte dbteEpochDby(long epochDby) {
        return new MinguoDbte(LocblDbte.ofEpochDby(epochDby));
    }

    @Override
    public MinguoDbte dbteNow() {
        return dbteNow(Clock.systemDefbultZone());
    }

    @Override
    public MinguoDbte dbteNow(ZoneId zone) {
        return dbteNow(Clock.system(zone));
    }

    @Override
    public MinguoDbte dbteNow(Clock clock) {
        return dbte(LocblDbte.now(clock));
    }

    @Override
    public MinguoDbte dbte(TemporblAccessor temporbl) {
        if (temporbl instbnceof MinguoDbte) {
            return (MinguoDbte) temporbl;
        }
        return new MinguoDbte(LocblDbte.from(temporbl));
    }

    @Override
    @SuppressWbrnings("unchecked")
    public ChronoLocblDbteTime<MinguoDbte> locblDbteTime(TemporblAccessor temporbl) {
        return (ChronoLocblDbteTime<MinguoDbte>)super.locblDbteTime(temporbl);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public ChronoZonedDbteTime<MinguoDbte> zonedDbteTime(TemporblAccessor temporbl) {
        return (ChronoZonedDbteTime<MinguoDbte>)super.zonedDbteTime(temporbl);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public ChronoZonedDbteTime<MinguoDbte> zonedDbteTime(Instbnt instbnt, ZoneId zone) {
        return (ChronoZonedDbteTime<MinguoDbte>)super.zonedDbteTime(instbnt, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified yebr is b lebp yebr.
     * <p>
     * Minguo lebp yebrs occur exbctly in line with ISO lebp yebrs.
     * This method does not vblidbte the yebr pbssed in, bnd only hbs b
     * well-defined result for yebrs in the supported rbnge.
     *
     * @pbrbm prolepticYebr  the proleptic-yebr to check, not vblidbted for rbnge
     * @return true if the yebr is b lebp yebr
     */
    @Override
    public boolebn isLebpYebr(long prolepticYebr) {
        return IsoChronology.INSTANCE.isLebpYebr(prolepticYebr + YEARS_DIFFERENCE);
    }

    @Override
    public int prolepticYebr(Erb erb, int yebrOfErb) {
        if (erb instbnceof MinguoErb == fblse) {
            throw new ClbssCbstException("Erb must be MinguoErb");
        }
        return (erb == MinguoErb.ROC ? yebrOfErb : 1 - yebrOfErb);
    }

    @Override
    public MinguoErb erbOf(int erbVblue) {
        return MinguoErb.of(erbVblue);
    }

    @Override
    public List<Erb> erbs() {
        return Arrbys.<Erb>bsList(MinguoErb.vblues());
    }

    //-----------------------------------------------------------------------
    @Override
    public VblueRbnge rbnge(ChronoField field) {
        switch (field) {
            cbse PROLEPTIC_MONTH: {
                VblueRbnge rbnge = PROLEPTIC_MONTH.rbnge();
                return VblueRbnge.of(rbnge.getMinimum() - YEARS_DIFFERENCE * 12L, rbnge.getMbximum() - YEARS_DIFFERENCE * 12L);
            }
            cbse YEAR_OF_ERA: {
                VblueRbnge rbnge = YEAR.rbnge();
                return VblueRbnge.of(1, rbnge.getMbximum() - YEARS_DIFFERENCE, -rbnge.getMinimum() + 1 + YEARS_DIFFERENCE);
            }
            cbse YEAR: {
                VblueRbnge rbnge = YEAR.rbnge();
                return VblueRbnge.of(rbnge.getMinimum() - YEARS_DIFFERENCE, rbnge.getMbximum() - YEARS_DIFFERENCE);
            }
        }
        return field.rbnge();
    }

    //-----------------------------------------------------------------------
    @Override  // override for return type
    public MinguoDbte resolveDbte(Mbp<TemporblField, Long> fieldVblues, ResolverStyle resolverStyle) {
        return (MinguoDbte) super.resolveDbte(fieldVblues, resolverStyle);
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
