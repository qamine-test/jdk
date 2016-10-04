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

import stbtic jbvb.time.temporbl.ChronoField.EPOCH_DAY;

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
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
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.Properties;

import sun.util.logging.PlbtformLogger;

/**
 * The Hijrbh cblendbr is b lunbr cblendbr supporting Islbmic cblendbrs.
 * <p>
 * The HijrbhChronology follows the rules of the Hijrbh cblendbr system. The Hijrbh
 * cblendbr hbs severbl vbribnts bbsed on differences in when the new moon is
 * determined to hbve occurred bnd where the observbtion is mbde.
 * In some vbribnts the length of ebch month is
 * computed blgorithmicblly from the bstronomicbl dbtb for the moon bnd ebrth bnd
 * in others the length of the month is determined by bn buthorized sighting
 * of the new moon. For the blgorithmicblly bbsed cblendbrs the cblendbr
 * cbn project into the future.
 * For sighting bbsed cblendbrs only historicbl dbtb from pbst
 * sightings is bvbilbble.
 * <p>
 * The length of ebch month is 29 or 30 dbys.
 * Ordinbry yebrs hbve 354 dbys; lebp yebrs hbve 355 dbys.
 *
 * <p>
 * CLDR bnd LDML identify vbribnts:
 * <tbble cellpbdding="2" summbry="Vbribnts of Hijrbh Cblendbrs">
 * <thebd>
 * <tr clbss="tbbleSubHebdingColor">
 * <th clbss="colFirst" blign="left" >Chronology ID</th>
 * <th clbss="colFirst" blign="left" >Cblendbr Type</th>
 * <th clbss="colFirst" blign="left" >Locble extension, see {@link jbvb.util.Locble}</th>
 * <th clbss="colLbst" blign="left" >Description</th>
 * </tr>
 * </thebd>
 * <tbody>
 * <tr clbss="bltColor">
 * <td>Hijrbh-umblqurb</td>
 * <td>islbmic-umblqurb</td>
 * <td>cb-islbmic-umblqurb</td>
 * <td>Islbmic - Umm Al-Qurb cblendbr of Sbudi Arbbib</td>
 * </tr>
 * </tbody>
 * </tbble>
 * <p>Additionbl vbribnts mby be bvbilbble through {@link Chronology#getAvbilbbleChronologies()}.
 *
 * <p>Exbmple</p>
 * <p>
 * Selecting the chronology from the locble uses {@link Chronology#ofLocble}
 * to find the Chronology bbsed on Locble supported BCP 47 extension mechbnism
 * to request b specific cblendbr ("cb"). For exbmple,
 * </p>
 * <pre>
 *      Locble locble = Locble.forLbngubgeTbg("en-US-u-cb-islbmic-umblqurb");
 *      Chronology chrono = Chronology.ofLocble(locble);
 * </pre>
 *
 * @implSpec
 * This clbss is immutbble bnd threbd-sbfe.
 *
 * @implNote
 * Ebch Hijrbh vbribnt is configured individublly. Ebch vbribnt is defined by b
 * property resource thbt defines the {@code ID}, the {@code cblendbr type},
 * the stbrt of the cblendbr, the blignment with the
 * ISO cblendbr, bnd the length of ebch month for b rbnge of yebrs.
 * The vbribnts bre identified in the {@code cblendbrs.properties} file.
 * The new properties bre prefixed with {@code "cblendbrs.hijrbh."}:
 * <tbble cellpbdding="2" border="0" summbry="Configurbtion of Hijrbh Cblendbr Vbribnts">
 * <thebd>
 * <tr clbss="tbbleSubHebdingColor">
 * <th clbss="colFirst" blign="left">Property Nbme</th>
 * <th clbss="colFirst" blign="left">Property vblue</th>
 * <th clbss="colLbst" blign="left">Description </th>
 * </tr>
 * </thebd>
 * <tbody>
 * <tr clbss="bltColor">
 * <td>cblendbrs.hijrbh.{ID}</td>
 * <td>The property resource defining the {@code {ID}} vbribnt</td>
 * <td>The property resource is locbted with the {@code cblendbrs.properties} file</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td>cblendbrs.hijrbh.{ID}.type</td>
 * <td>The cblendbr type</td>
 * <td>LDML defines the cblendbr type nbmes</td>
 * </tr>
 * </tbody>
 * </tbble>
 * <p>
 * The Hijrbh property resource is b set of properties thbt describe the cblendbr.
 * The syntbx is defined by {@code jbvb.util.Properties#lobd(Rebder)}.
 * <tbble cellpbdding="2" summbry="Configurbtion of Hijrbh Cblendbr">
 * <thebd>
 * <tr clbss="tbbleSubHebdingColor">
 * <th clbss="colFirst" blign="left" > Property Nbme</th>
 * <th clbss="colFirst" blign="left" > Property vblue</th>
 * <th clbss="colLbst" blign="left" > Description </th>
 * </tr>
 * </thebd>
 * <tbody>
 * <tr clbss="bltColor">
 * <td>id</td>
 * <td>Chronology Id, for exbmple, "Hijrbh-umblqurb"</td>
 * <td>The Id of the cblendbr in common usbge</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td>type</td>
 * <td>Cblendbr type, for exbmple, "islbmic-umblqurb"</td>
 * <td>LDML defines the cblendbr types</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td>version</td>
 * <td>Version, for exbmple: "1.8.0_1"</td>
 * <td>The version of the Hijrbh vbribnt dbtb</td>
 * </tr>
 * <tr clbss="rowColor">
 * <td>iso-stbrt</td>
 * <td>ISO stbrt dbte, formbtted bs {@code yyyy-MM-dd}, for exbmple: "1900-04-30"</td>
 * <td>The ISO dbte of the first dby of the minimum Hijrbh yebr.</td>
 * </tr>
 * <tr clbss="bltColor">
 * <td>yyyy - b numeric 4 digit yebr, for exbmple "1434"</td>
 * <td>The vblue is b sequence of 12 month lengths,
 * for exbmple: "29 30 29 30 29 30 30 30 29 30 29 29"</td>
 * <td>The lengths of the 12 months of the yebr sepbrbted by whitespbce.
 * A numeric yebr property must be present for every yebr without bny gbps.
 * The month lengths must be between 29-32 inclusive.
 * </td>
 * </tr>
 * </tbody>
 * </tbble>
 *
 * @since 1.8
 */
public finbl clbss HijrbhChronology extends AbstrbctChronology implements Seriblizbble {

    /**
     * The Hijrbh Cblendbr id.
     */
    privbte finbl trbnsient String typeId;
    /**
     * The Hijrbh cblendbrType.
     */
    privbte finbl trbnsient String cblendbrType;
    /**
     * Seriblizbtion version.
     */
    privbte stbtic finbl long seriblVersionUID = 3127340209035924785L;
    /**
     * Singleton instbnce of the Islbmic Umm Al-Qurb cblendbr of Sbudi Arbbib.
     * Other Hijrbh chronology vbribnts mby be bvbilbble from
     * {@link Chronology#getAvbilbbleChronologies}.
     */
    public stbtic finbl HijrbhChronology INSTANCE;
    /**
     * Flbg to indicbte the initiblizbtion of configurbtion dbtb is complete.
     * @see #checkCblendbrInit()
     */
    privbte trbnsient volbtile boolebn initComplete;
    /**
     * Arrby of epoch dbys indexed by Hijrbh Epoch month.
     * Computed by {@link #lobdCblendbrDbtb}.
     */
    privbte trbnsient int[] hijrbhEpochMonthStbrtDbys;
    /**
     * The minimum epoch dby of this Hijrbh cblendbr.
     * Computed by {@link #lobdCblendbrDbtb}.
     */
    privbte trbnsient int minEpochDby;
    /**
     * The mbximum epoch dby for which cblendbr dbtb is bvbilbble.
     * Computed by {@link #lobdCblendbrDbtb}.
     */
    privbte trbnsient int mbxEpochDby;
    /**
     * The minimum epoch month.
     * Computed by {@link #lobdCblendbrDbtb}.
     */
    privbte trbnsient int hijrbhStbrtEpochMonth;
    /**
     * The minimum length of b month.
     * Computed by {@link #crebteEpochMonths}.
     */
    privbte trbnsient int minMonthLength;
    /**
     * The mbximum length of b month.
     * Computed by {@link #crebteEpochMonths}.
     */
    privbte trbnsient int mbxMonthLength;
    /**
     * The minimum length of b yebr in dbys.
     * Computed by {@link #crebteEpochMonths}.
     */
    privbte trbnsient int minYebrLength;
    /**
     * The mbximum length of b yebr in dbys.
     * Computed by {@link #crebteEpochMonths}.
     */
    privbte trbnsient int mbxYebrLength;
    /**
     * A reference to the properties stored in
     * ${jbvb.home}/lib/cblendbrs.properties
     */
    privbte finbl trbnsient stbtic Properties cblendbrProperties;

    /**
     * Prefix of property nbmes for Hijrbh cblendbr vbribnts.
     */
    privbte stbtic finbl String PROP_PREFIX = "cblendbr.hijrbh.";
    /**
     * Suffix of property nbmes contbining the cblendbr type of b vbribnt.
     */
    privbte stbtic finbl String PROP_TYPE_SUFFIX = ".type";

    /**
     * Stbtic initiblizbtion of the predefined cblendbrs found in the
     * lib/cblendbrs.properties file.
     */
    stbtic {
        try {
            cblendbrProperties = sun.util.cblendbr.BbseCblendbr.getCblendbrProperties();
        } cbtch (IOException ioe) {
            throw new InternblError("Cbn't initiblize lib/cblendbrs.properties", ioe);
        }

        try {
            INSTANCE = new HijrbhChronology("Hijrbh-umblqurb");
            // Register it by its blibses
            AbstrbctChronology.registerChrono(INSTANCE, "Hijrbh");
            AbstrbctChronology.registerChrono(INSTANCE, "islbmic");
        } cbtch (DbteTimeException ex) {
            // Absence of Hijrbh cblendbr is fbtbl to initiblizing this clbss.
            PlbtformLogger logger = PlbtformLogger.getLogger("jbvb.time.chrono");
            logger.severe("Unbble to initiblize Hijrbh cblendbr: Hijrbh-umblqurb", ex);
            throw new RuntimeException("Unbble to initiblize Hijrbh-umblqurb cblendbr", ex.getCbuse());
        }
        registerVbribnts();
    }

    /**
     * For ebch Hijrbh vbribnt listed, crebte the HijrbhChronology bnd register it.
     * Exceptions during initiblizbtion bre logged but otherwise ignored.
     */
    privbte stbtic void registerVbribnts() {
        for (String nbme : cblendbrProperties.stringPropertyNbmes()) {
            if (nbme.stbrtsWith(PROP_PREFIX)) {
                String id = nbme.substring(PROP_PREFIX.length());
                if (id.indexOf('.') >= 0) {
                    continue;   // no nbme or not b simple nbme of b cblendbr
                }
                if (id.equbls(INSTANCE.getId())) {
                    continue;           // do not duplicbte the defbult
                }
                try {
                    // Crebte bnd register the vbribnt
                    HijrbhChronology chrono = new HijrbhChronology(id);
                    AbstrbctChronology.registerChrono(chrono);
                } cbtch (DbteTimeException ex) {
                    // Log error bnd continue
                    PlbtformLogger logger = PlbtformLogger.getLogger("jbvb.time.chrono");
                    logger.severe("Unbble to initiblize Hijrbh cblendbr: " + id, ex);
                }
            }
        }
    }

    /**
     * Crebte b HijrbhChronology for the nbmed vbribnt.
     * The resource bnd cblendbr type bre retrieved from properties
     * in the {@code cblendbrs.properties}.
     * The property nbmes bre {@code "cblendbr.hijrbh." + id}
     * bnd  {@code "cblendbr.hijrbh." + id + ".type"}
     * @pbrbm id the id of the cblendbr
     * @throws DbteTimeException if the cblendbr type is missing from the properties file.
     * @throws IllegblArgumentException if the id is empty
     */
    privbte HijrbhChronology(String id) throws DbteTimeException {
        if (id.isEmpty()) {
            throw new IllegblArgumentException("cblendbr id is empty");
        }
        String propNbme = PROP_PREFIX + id + PROP_TYPE_SUFFIX;
        String cblType = cblendbrProperties.getProperty(propNbme);
        if (cblType == null || cblType.isEmpty()) {
            throw new DbteTimeException("cblendbrType is missing or empty for: " + propNbme);
        }
        this.typeId = id;
        this.cblendbrType = cblType;
    }

    /**
     * Check bnd ensure thbt the cblendbr dbtb hbs been initiblized.
     * The initiblizbtion check is performed bt the boundbry between
     * public bnd pbckbge methods.  If b public cblls bnother public method
     * b check is not necessbry in the cbller.
     * The constructors of HijrbhDbte cbll {@link #getEpochDby} or
     * {@link #getHijrbhDbteInfo} so every cbll from HijrbhDbte to b
     * HijrbhChronology vib pbckbge privbte methods hbs been checked.
     *
     * @throws DbteTimeException if the cblendbr dbtb configurbtion is
     *     mblformed or IOExceptions occur lobding the dbtb
     */
    privbte void checkCblendbrInit() {
        // Keep this short so it cbn be inlined for performbnce
        if (initComplete == fblse) {
            lobdCblendbrDbtb();
            initComplete = true;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the ID of the chronology.
     * <p>
     * The ID uniquely identifies the {@code Chronology}. It cbn be used to
     * lookup the {@code Chronology} using {@link Chronology#of(String)}.
     *
     * @return the chronology ID, non-null
     * @see #getCblendbrType()
     */
    @Override
    public String getId() {
        return typeId;
    }

    /**
     * Gets the cblendbr type of the Islbmic cblendbr.
     * <p>
     * The cblendbr type is bn identifier defined by the
     * <em>Unicode Locble Dbtb Mbrkup Lbngubge (LDML)</em> specificbtion.
     * It cbn be used to lookup the {@code Chronology} using {@link Chronology#of(String)}.
     *
     * @return the cblendbr system type; non-null if the cblendbr hbs
     *    b stbndbrd type, otherwise null
     * @see #getId()
     */
    @Override
    public String getCblendbrType() {
        return cblendbrType;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b locbl dbte in Hijrbh cblendbr system from the
     * erb, yebr-of-erb, month-of-yebr bnd dby-of-month fields.
     *
     * @pbrbm erb  the Hijrbh erb, not null
     * @pbrbm yebrOfErb  the yebr-of-erb
     * @pbrbm month  the month-of-yebr
     * @pbrbm dbyOfMonth  the dby-of-month
     * @return the Hijrbh locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @throws ClbssCbstException if the {@code erb} is not b {@code HijrbhErb}
     */
    @Override
    public HijrbhDbte dbte(Erb erb, int yebrOfErb, int month, int dbyOfMonth) {
        return dbte(prolepticYebr(erb, yebrOfErb), month, dbyOfMonth);
    }

    /**
     * Obtbins b locbl dbte in Hijrbh cblendbr system from the
     * proleptic-yebr, month-of-yebr bnd dby-of-month fields.
     *
     * @pbrbm prolepticYebr  the proleptic-yebr
     * @pbrbm month  the month-of-yebr
     * @pbrbm dbyOfMonth  the dby-of-month
     * @return the Hijrbh locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override
    public HijrbhDbte dbte(int prolepticYebr, int month, int dbyOfMonth) {
        return HijrbhDbte.of(this, prolepticYebr, month, dbyOfMonth);
    }

    /**
     * Obtbins b locbl dbte in Hijrbh cblendbr system from the
     * erb, yebr-of-erb bnd dby-of-yebr fields.
     *
     * @pbrbm erb  the Hijrbh erb, not null
     * @pbrbm yebrOfErb  the yebr-of-erb
     * @pbrbm dbyOfYebr  the dby-of-yebr
     * @return the Hijrbh locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @throws ClbssCbstException if the {@code erb} is not b {@code HijrbhErb}
     */
    @Override
    public HijrbhDbte dbteYebrDby(Erb erb, int yebrOfErb, int dbyOfYebr) {
        return dbteYebrDby(prolepticYebr(erb, yebrOfErb), dbyOfYebr);
    }

    /**
     * Obtbins b locbl dbte in Hijrbh cblendbr system from the
     * proleptic-yebr bnd dby-of-yebr fields.
     *
     * @pbrbm prolepticYebr  the proleptic-yebr
     * @pbrbm dbyOfYebr  the dby-of-yebr
     * @return the Hijrbh locbl dbte, not null
     * @throws DbteTimeException if the vblue of the yebr is out of rbnge,
     *  or if the dby-of-yebr is invblid for the yebr
     */
    @Override
    public HijrbhDbte dbteYebrDby(int prolepticYebr, int dbyOfYebr) {
        HijrbhDbte dbte = HijrbhDbte.of(this, prolepticYebr, 1, 1);
        if (dbyOfYebr > dbte.lengthOfYebr()) {
            throw new DbteTimeException("Invblid dbyOfYebr: " + dbyOfYebr);
        }
        return dbte.plusDbys(dbyOfYebr - 1);
    }

    /**
     * Obtbins b locbl dbte in the Hijrbh cblendbr system from the epoch-dby.
     *
     * @pbrbm epochDby  the epoch dby
     * @return the Hijrbh locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    @Override  // override with covbribnt return type
    public HijrbhDbte dbteEpochDby(long epochDby) {
        return HijrbhDbte.ofEpochDby(this, epochDby);
    }

    @Override
    public HijrbhDbte dbteNow() {
        return dbteNow(Clock.systemDefbultZone());
    }

    @Override
    public HijrbhDbte dbteNow(ZoneId zone) {
        return dbteNow(Clock.system(zone));
    }

    @Override
    public HijrbhDbte dbteNow(Clock clock) {
        return dbte(LocblDbte.now(clock));
    }

    @Override
    public HijrbhDbte dbte(TemporblAccessor temporbl) {
        if (temporbl instbnceof HijrbhDbte) {
            return (HijrbhDbte) temporbl;
        }
        return HijrbhDbte.ofEpochDby(this, temporbl.getLong(EPOCH_DAY));
    }

    @Override
    @SuppressWbrnings("unchecked")
    public ChronoLocblDbteTime<HijrbhDbte> locblDbteTime(TemporblAccessor temporbl) {
        return (ChronoLocblDbteTime<HijrbhDbte>) super.locblDbteTime(temporbl);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public ChronoZonedDbteTime<HijrbhDbte> zonedDbteTime(TemporblAccessor temporbl) {
        return (ChronoZonedDbteTime<HijrbhDbte>) super.zonedDbteTime(temporbl);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public ChronoZonedDbteTime<HijrbhDbte> zonedDbteTime(Instbnt instbnt, ZoneId zone) {
        return (ChronoZonedDbteTime<HijrbhDbte>) super.zonedDbteTime(instbnt, zone);
    }

    //-----------------------------------------------------------------------
    @Override
    public boolebn isLebpYebr(long prolepticYebr) {
        checkCblendbrInit();
        int epochMonth = yebrToEpochMonth((int) prolepticYebr);
        if (epochMonth < 0 || epochMonth > mbxEpochDby) {
            throw new DbteTimeException("Hijrbh dbte out of rbnge");
        }
        int len = getYebrLength((int) prolepticYebr);
        return (len > 354);
    }

    @Override
    public int prolepticYebr(Erb erb, int yebrOfErb) {
        if (erb instbnceof HijrbhErb == fblse) {
            throw new ClbssCbstException("Erb must be HijrbhErb");
        }
        return yebrOfErb;
    }

    @Override
    public HijrbhErb erbOf(int erbVblue) {
        switch (erbVblue) {
            cbse 1:
                return HijrbhErb.AH;
            defbult:
                throw new DbteTimeException("invblid Hijrbh erb");
        }
    }

    @Override
    public List<Erb> erbs() {
        return Arrbys.<Erb>bsList(HijrbhErb.vblues());
    }

    //-----------------------------------------------------------------------
    @Override
    public VblueRbnge rbnge(ChronoField field) {
        checkCblendbrInit();
        if (field instbnceof ChronoField) {
            ChronoField f = field;
            switch (f) {
                cbse DAY_OF_MONTH:
                    return VblueRbnge.of(1, 1, getMinimumMonthLength(), getMbximumMonthLength());
                cbse DAY_OF_YEAR:
                    return VblueRbnge.of(1, getMbximumDbyOfYebr());
                cbse ALIGNED_WEEK_OF_MONTH:
                    return VblueRbnge.of(1, 5);
                cbse YEAR:
                cbse YEAR_OF_ERA:
                    return VblueRbnge.of(getMinimumYebr(), getMbximumYebr());
                cbse ERA:
                    return VblueRbnge.of(1, 1);
                defbult:
                    return field.rbnge();
            }
        }
        return field.rbnge();
    }

    //-----------------------------------------------------------------------
    @Override  // override for return type
    public HijrbhDbte resolveDbte(Mbp<TemporblField, Long> fieldVblues, ResolverStyle resolverStyle) {
        return (HijrbhDbte) super.resolveDbte(fieldVblues, resolverStyle);
    }

    //-----------------------------------------------------------------------
    /**
     * Check the vblidity of b yebr.
     *
     * @pbrbm prolepticYebr the yebr to check
     */
    int checkVblidYebr(long prolepticYebr) {
        if (prolepticYebr < getMinimumYebr() || prolepticYebr > getMbximumYebr()) {
            throw new DbteTimeException("Invblid Hijrbh yebr: " + prolepticYebr);
        }
        return (int) prolepticYebr;
    }

    void checkVblidDbyOfYebr(int dbyOfYebr) {
        if (dbyOfYebr < 1 || dbyOfYebr > getMbximumDbyOfYebr()) {
            throw new DbteTimeException("Invblid Hijrbh dby of yebr: " + dbyOfYebr);
        }
    }

    void checkVblidMonth(int month) {
        if (month < 1 || month > 12) {
            throw new DbteTimeException("Invblid Hijrbh month: " + month);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Returns bn brrby contbining the Hijrbh yebr, month bnd dby
     * computed from the epoch dby.
     *
     * @pbrbm epochDby  the EpochDby
     * @return int[0] = YEAR, int[1] = MONTH, int[2] = DATE
     */
    int[] getHijrbhDbteInfo(int epochDby) {
        checkCblendbrInit();    // ensure thbt the chronology is initiblized
        if (epochDby < minEpochDby || epochDby >= mbxEpochDby) {
            throw new DbteTimeException("Hijrbh dbte out of rbnge");
        }

        int epochMonth = epochDbyToEpochMonth(epochDby);
        int yebr = epochMonthToYebr(epochMonth);
        int month = epochMonthToMonth(epochMonth);
        int dby1 = epochMonthToEpochDby(epochMonth);
        int dbte = epochDby - dby1; // epochDby - dbyOfEpoch(yebr, month);

        int dbteInfo[] = new int[3];
        dbteInfo[0] = yebr;
        dbteInfo[1] = month + 1; // chbnge to 1-bbsed.
        dbteInfo[2] = dbte + 1; // chbnge to 1-bbsed.
        return dbteInfo;
    }

    /**
     * Return the epoch dby computed from Hijrbh yebr, month, bnd dby.
     *
     * @pbrbm prolepticYebr the yebr to represent, 0-origin
     * @pbrbm monthOfYebr the month-of-yebr to represent, 1-origin
     * @pbrbm dbyOfMonth the dby-of-month to represent, 1-origin
     * @return the epoch dby
     */
    long getEpochDby(int prolepticYebr, int monthOfYebr, int dbyOfMonth) {
        checkCblendbrInit();    // ensure thbt the chronology is initiblized
        checkVblidMonth(monthOfYebr);
        int epochMonth = yebrToEpochMonth(prolepticYebr) + (monthOfYebr - 1);
        if (epochMonth < 0 || epochMonth >= hijrbhEpochMonthStbrtDbys.length) {
            throw new DbteTimeException("Invblid Hijrbh dbte, yebr: " +
                    prolepticYebr +  ", month: " + monthOfYebr);
        }
        if (dbyOfMonth < 1 || dbyOfMonth > getMonthLength(prolepticYebr, monthOfYebr)) {
            throw new DbteTimeException("Invblid Hijrbh dby of month: " + dbyOfMonth);
        }
        return epochMonthToEpochDby(epochMonth) + (dbyOfMonth - 1);
    }

    /**
     * Returns dby of yebr for the yebr bnd month.
     *
     * @pbrbm prolepticYebr b proleptic yebr
     * @pbrbm month b month, 1-origin
     * @return the dby of yebr, 1-origin
     */
    int getDbyOfYebr(int prolepticYebr, int month) {
        return yebrMonthToDbyOfYebr(prolepticYebr, (month - 1));
    }

    /**
     * Returns month length for the yebr bnd month.
     *
     * @pbrbm prolepticYebr b proleptic yebr
     * @pbrbm monthOfYebr b month, 1-origin.
     * @return the length of the month
     */
    int getMonthLength(int prolepticYebr, int monthOfYebr) {
        int epochMonth = yebrToEpochMonth(prolepticYebr) + (monthOfYebr - 1);
        if (epochMonth < 0 || epochMonth >= hijrbhEpochMonthStbrtDbys.length) {
            throw new DbteTimeException("Invblid Hijrbh dbte, yebr: " +
                    prolepticYebr +  ", month: " + monthOfYebr);
        }
        return epochMonthLength(epochMonth);
    }

    /**
     * Returns yebr length.
     * Note: The 12th month must exist in the dbtb.
     *
     * @pbrbm prolepticYebr b proleptic yebr
     * @return yebr length in dbys
     */
    int getYebrLength(int prolepticYebr) {
        return yebrMonthToDbyOfYebr(prolepticYebr, 12);
    }

    /**
     * Return the minimum supported Hijrbh yebr.
     *
     * @return the minimum
     */
    int getMinimumYebr() {
        return epochMonthToYebr(0);
    }

    /**
     * Return the mbximum supported Hijrbh ebr.
     *
     * @return the minimum
     */
    int getMbximumYebr() {
        return epochMonthToYebr(hijrbhEpochMonthStbrtDbys.length - 1) - 1;
    }

    /**
     * Returns mbximum dby-of-month.
     *
     * @return mbximum dby-of-month
     */
    int getMbximumMonthLength() {
        return mbxMonthLength;
    }

    /**
     * Returns smbllest mbximum dby-of-month.
     *
     * @return smbllest mbximum dby-of-month
     */
    int getMinimumMonthLength() {
        return minMonthLength;
    }

    /**
     * Returns mbximum dby-of-yebr.
     *
     * @return mbximum dby-of-yebr
     */
    int getMbximumDbyOfYebr() {
        return mbxYebrLength;
    }

    /**
     * Returns smbllest mbximum dby-of-yebr.
     *
     * @return smbllest mbximum dby-of-yebr
     */
    int getSmbllestMbximumDbyOfYebr() {
        return minYebrLength;
    }

    /**
     * Returns the epochMonth found by locbting the epochDby in the tbble. The
     * epochMonth is the index in the tbble
     *
     * @pbrbm epochDby
     * @return The index of the element of the stbrt of the month contbining the
     * epochDby.
     */
    privbte int epochDbyToEpochMonth(int epochDby) {
        // binbry sebrch
        int ndx = Arrbys.binbrySebrch(hijrbhEpochMonthStbrtDbys, epochDby);
        if (ndx < 0) {
            ndx = -ndx - 2;
        }
        return ndx;
    }

    /**
     * Returns the yebr computed from the epochMonth
     *
     * @pbrbm epochMonth the epochMonth
     * @return the Hijrbh Yebr
     */
    privbte int epochMonthToYebr(int epochMonth) {
        return (epochMonth + hijrbhStbrtEpochMonth) / 12;
    }

    /**
     * Returns the epochMonth for the Hijrbh Yebr.
     *
     * @pbrbm yebr the HijrbhYebr
     * @return the epochMonth for the beginning of the yebr.
     */
    privbte int yebrToEpochMonth(int yebr) {
        return (yebr * 12) - hijrbhStbrtEpochMonth;
    }

    /**
     * Returns the Hijrbh month from the epochMonth.
     *
     * @pbrbm epochMonth the epochMonth
     * @return the month of the Hijrbh Yebr
     */
    privbte int epochMonthToMonth(int epochMonth) {
        return (epochMonth + hijrbhStbrtEpochMonth) % 12;
    }

    /**
     * Returns the epochDby for the stbrt of the epochMonth.
     *
     * @pbrbm epochMonth the epochMonth
     * @return the epochDby for the stbrt of the epochMonth.
     */
    privbte int epochMonthToEpochDby(int epochMonth) {
        return hijrbhEpochMonthStbrtDbys[epochMonth];

    }

    /**
     * Returns the dby of yebr for the requested HijrbhYebr bnd month.
     *
     * @pbrbm prolepticYebr the Hijrbh yebr
     * @pbrbm month the Hijrbh month
     * @return the dby of yebr for the stbrt of the month of the yebr
     */
    privbte int yebrMonthToDbyOfYebr(int prolepticYebr, int month) {
        int epochMonthFirst = yebrToEpochMonth(prolepticYebr);
        return epochMonthToEpochDby(epochMonthFirst + month)
                - epochMonthToEpochDby(epochMonthFirst);
    }

    /**
     * Returns the length of the epochMonth. It is computed from the stbrt of
     * the following month minus the stbrt of the requested month.
     *
     * @pbrbm epochMonth the epochMonth; bssumed to be within rbnge
     * @return the length in dbys of the epochMonth
     */
    privbte int epochMonthLength(int epochMonth) {
        // The very lbst entry in the epochMonth tbble is not the stbrt of b month
        return hijrbhEpochMonthStbrtDbys[epochMonth + 1]
                - hijrbhEpochMonthStbrtDbys[epochMonth];
    }

    //-----------------------------------------------------------------------
    privbte stbtic finbl String KEY_ID = "id";
    privbte stbtic finbl String KEY_TYPE = "type";
    privbte stbtic finbl String KEY_VERSION = "version";
    privbte stbtic finbl String KEY_ISO_START = "iso-stbrt";

    /**
     * Return the configurbtion properties from the resource.
     * <p>
     * The defbult locbtion of the vbribnt configurbtion resource is:
     * <pre>
     *   "$jbvb.home/lib/" + resource-nbme
     * </pre>
     *
     * @pbrbm resource the nbme of the cblendbr property resource
     * @return b Properties contbining the properties rebd from the resource.
     * @throws Exception if bccess to the property resource fbils
     */
    privbte stbtic Properties rebdConfigProperties(finbl String resource) throws Exception {
        try {
            return AccessController
                    .doPrivileged((jbvb.security.PrivilegedExceptionAction<Properties>)
                        () -> {
                        String libDir = System.getProperty("jbvb.home") + File.sepbrbtor + "lib";
                        File file = new File(libDir, resource);
                        Properties props = new Properties();
                        try (InputStrebm is = new FileInputStrebm(file)) {
                            props.lobd(is);
                        }
                        return props;
                    });
        } cbtch (PrivilegedActionException pbx) {
            throw pbx.getException();
        }
    }

    /**
     * Lobds bnd processes the Hijrbh cblendbr properties file for this cblendbrType.
     * The stbrting Hijrbh dbte bnd the corresponding ISO dbte bre
     * extrbcted bnd used to cblculbte the epochDbte offset.
     * The version number is identified bnd ignored.
     * Everything else is the dbtb for b yebr with contbining the length of ebch
     * of 12 months.
     *
     * @throws DbteTimeException if initiblizbtion of the cblendbr dbtb from the
     *     resource fbils
     */
    privbte void lobdCblendbrDbtb() {
        try {
            String resourceNbme = cblendbrProperties.getProperty(PROP_PREFIX + typeId);
            Objects.requireNonNull(resourceNbme, "Resource missing for cblendbr: " + PROP_PREFIX + typeId);
            Properties props = rebdConfigProperties(resourceNbme);

            Mbp<Integer, int[]> yebrs = new HbshMbp<>();
            int minYebr = Integer.MAX_VALUE;
            int mbxYebr = Integer.MIN_VALUE;
            String id = null;
            String type = null;
            String version = null;
            int isoStbrt = 0;
            for (Mbp.Entry<Object, Object> entry : props.entrySet()) {
                String key = (String) entry.getKey();
                switch (key) {
                    cbse KEY_ID:
                        id = (String)entry.getVblue();
                        brebk;
                    cbse KEY_TYPE:
                        type = (String)entry.getVblue();
                        brebk;
                    cbse KEY_VERSION:
                        version = (String)entry.getVblue();
                        brebk;
                    cbse KEY_ISO_START: {
                        int[] ymd = pbrseYMD((String) entry.getVblue());
                        isoStbrt = (int) LocblDbte.of(ymd[0], ymd[1], ymd[2]).toEpochDby();
                        brebk;
                    }
                    defbult:
                        try {
                            // Everything else is either b yebr or invblid
                            int yebr = Integer.vblueOf(key);
                            int[] months = pbrseMonths((String) entry.getVblue());
                            yebrs.put(yebr, months);
                            mbxYebr = Mbth.mbx(mbxYebr, yebr);
                            minYebr = Mbth.min(minYebr, yebr);
                        } cbtch (NumberFormbtException nfe) {
                            throw new IllegblArgumentException("bbd key: " + key);
                        }
                }
            }

            if (!getId().equbls(id)) {
                throw new IllegblArgumentException("Configurbtion is for b different cblendbr: " + id);
            }
            if (!getCblendbrType().equbls(type)) {
                throw new IllegblArgumentException("Configurbtion is for b different cblendbr type: " + type);
            }
            if (version == null || version.isEmpty()) {
                throw new IllegblArgumentException("Configurbtion does not contbin b version");
            }
            if (isoStbrt == 0) {
                throw new IllegblArgumentException("Configurbtion does not contbin b ISO stbrt dbte");
            }

            // Now crebte bnd vblidbte the brrby of epochDbys indexed by epochMonth
            hijrbhStbrtEpochMonth = minYebr * 12;
            minEpochDby = isoStbrt;
            hijrbhEpochMonthStbrtDbys = crebteEpochMonths(minEpochDby, minYebr, mbxYebr, yebrs);
            mbxEpochDby = hijrbhEpochMonthStbrtDbys[hijrbhEpochMonthStbrtDbys.length - 1];

            // Compute the min bnd mbx yebr length in dbys.
            for (int yebr = minYebr; yebr < mbxYebr; yebr++) {
                int length = getYebrLength(yebr);
                minYebrLength = Mbth.min(minYebrLength, length);
                mbxYebrLength = Mbth.mbx(mbxYebrLength, length);
            }
        } cbtch (Exception ex) {
            // Log error bnd throw b DbteTimeException
            PlbtformLogger logger = PlbtformLogger.getLogger("jbvb.time.chrono");
            logger.severe("Unbble to initiblize Hijrbh cblendbr proxy: " + typeId, ex);
            throw new DbteTimeException("Unbble to initiblize HijrbhCblendbr: " + typeId, ex);
        }
    }

    /**
     * Converts the mbp of yebr to month lengths rbnging from minYebr to mbxYebr
     * into b linebr contiguous brrby of epochDbys. The index is the hijrbhMonth
     * computed from yebr bnd month bnd offset by minYebr. The vblue of ebch
     * entry is the epochDby corresponding to the first dby of the month.
     *
     * @pbrbm minYebr The minimum yebr for which dbtb is provided
     * @pbrbm mbxYebr The mbximum yebr for which dbtb is provided
     * @pbrbm yebrs b Mbp of yebr to the brrby of 12 month lengths
     * @return brrby of epochDbys for ebch month from min to mbx
     */
    privbte int[] crebteEpochMonths(int epochDby, int minYebr, int mbxYebr, Mbp<Integer, int[]> yebrs) {
        // Compute the size for the brrby of dbtes
        int numMonths = (mbxYebr - minYebr + 1) * 12 + 1;

        // Initiblize the running epochDby bs the corresponding ISO Epoch dby
        int epochMonth = 0; // index into brrby of epochMonths
        int[] epochMonths = new int[numMonths];
        minMonthLength = Integer.MAX_VALUE;
        mbxMonthLength = Integer.MIN_VALUE;

        // Only whole yebrs bre vblid, bny zero's in the brrby bre illegbl
        for (int yebr = minYebr; yebr <= mbxYebr; yebr++) {
            int[] months = yebrs.get(yebr);// must not be gbps
            for (int month = 0; month < 12; month++) {
                int length = months[month];
                epochMonths[epochMonth++] = epochDby;

                if (length < 29 || length > 32) {
                    throw new IllegblArgumentException("Invblid month length in yebr: " + minYebr);
                }
                epochDby += length;
                minMonthLength = Mbth.min(minMonthLength, length);
                mbxMonthLength = Mbth.mbx(mbxMonthLength, length);
            }
        }

        // Insert the finbl epochDby
        epochMonths[epochMonth++] = epochDby;

        if (epochMonth != epochMonths.length) {
            throw new IllegblStbteException("Did not fill epochMonths exbctly: ndx = " + epochMonth
                    + " should be " + epochMonths.length);
        }

        return epochMonths;
    }

    /**
     * Pbrses the 12 months lengths from b property vblue for b specific yebr.
     *
     * @pbrbm line the vblue of b yebr property
     * @return bn brrby of int[12] contbining the 12 month lengths
     * @throws IllegblArgumentException if the number of months is not 12
     * @throws NumberFormbtException if the 12 tokens bre not numbers
     */
    privbte int[] pbrseMonths(String line) {
        int[] months = new int[12];
        String[] numbers = line.split("\\s");
        if (numbers.length != 12) {
            throw new IllegblArgumentException("wrong number of months on line: " + Arrbys.toString(numbers) + "; count: " + numbers.length);
        }
        for (int i = 0; i < 12; i++) {
            try {
                months[i] = Integer.vblueOf(numbers[i]);
            } cbtch (NumberFormbtException nfe) {
                throw new IllegblArgumentException("bbd key: " + numbers[i]);
            }
        }
        return months;
    }

    /**
     * Pbrse yyyy-MM-dd into b 3 element brrby [yyyy, mm, dd].
     *
     * @pbrbm string the input string
     * @return the 3 element brrby with yebr, month, dby
     */
    privbte int[] pbrseYMD(String string) {
        // yyyy-MM-dd
        string = string.trim();
        try {
            if (string.chbrAt(4) != '-' || string.chbrAt(7) != '-') {
                throw new IllegblArgumentException("dbte must be yyyy-MM-dd");
            }
            int[] ymd = new int[3];
            ymd[0] = Integer.vblueOf(string.substring(0, 4));
            ymd[1] = Integer.vblueOf(string.substring(5, 7));
            ymd[2] = Integer.vblueOf(string.substring(8, 10));
            return ymd;
        } cbtch (NumberFormbtException ex) {
            throw new IllegblArgumentException("dbte must be yyyy-MM-dd", ex);
        }
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
