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

import jbvb.time.Clock;
import jbvb.time.DbteTimeException;
import jbvb.time.Instbnt;
import jbvb.time.LocblDbte;
import jbvb.time.LocblTime;
import jbvb.time.ZoneId;
import jbvb.time.formbt.DbteTimeFormbtterBuilder;
import jbvb.time.formbt.ResolverStyle;
import jbvb.time.formbt.TextStyle;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.Set;

/**
 * A cblendbr system, used to orgbnize bnd identify dbtes.
 * <p>
 * The mbin dbte bnd time API is built on the ISO cblendbr system.
 * The chronology operbtes behind the scenes to represent the generbl concept of b cblendbr system.
 * For exbmple, the Jbpbnese, Minguo, Thbi Buddhist bnd others.
 * <p>
 * Most other cblendbr systems blso operbte on the shbred concepts of yebr, month bnd dby,
 * linked to the cycles of the Ebrth bround the Sun, bnd the Moon bround the Ebrth.
 * These shbred concepts bre defined by {@link ChronoField} bnd bre bvbilbble
 * for use by bny {@code Chronology} implementbtion:
 * <pre>
 *   LocblDbte isoDbte = ...
 *   ThbiBuddhistDbte thbiDbte = ...
 *   int isoYebr = isoDbte.get(ChronoField.YEAR);
 *   int thbiYebr = thbiDbte.get(ChronoField.YEAR);
 * </pre>
 * As shown, blthough the dbte objects bre in different cblendbr systems, represented by different
 * {@code Chronology} instbnces, both cbn be queried using the sbme constbnt on {@code ChronoField}.
 * For b full discussion of the implicbtions of this, see {@link ChronoLocblDbte}.
 * In generbl, the bdvice is to use the known ISO-bbsed {@code LocblDbte}, rbther thbn
 * {@code ChronoLocblDbte}.
 * <p>
 * While b {@code Chronology} object typicblly uses {@code ChronoField} bnd is bbsed on
 * bn erb, yebr-of-erb, month-of-yebr, dby-of-month model of b dbte, this is not required.
 * A {@code Chronology} instbnce mby represent b totblly different kind of cblendbr system,
 * such bs the Mbybn.
 * <p>
 * In prbcticbl terms, the {@code Chronology} instbnce blso bcts bs b fbctory.
 * The {@link #of(String)} method bllows bn instbnce to be looked up by identifier,
 * while the {@link #ofLocble(Locble)} method bllows lookup by locble.
 * <p>
 * The {@code Chronology} instbnce provides b set of methods to crebte {@code ChronoLocblDbte} instbnces.
 * The dbte clbsses bre used to mbnipulbte specific dbtes.
 * <ul>
 * <li> {@link #dbteNow() dbteNow()}
 * <li> {@link #dbteNow(Clock) dbteNow(clock)}
 * <li> {@link #dbteNow(ZoneId) dbteNow(zone)}
 * <li> {@link #dbte(int, int, int) dbte(yebrProleptic, month, dby)}
 * <li> {@link #dbte(Erb, int, int, int) dbte(erb, yebrOfErb, month, dby)}
 * <li> {@link #dbteYebrDby(int, int) dbteYebrDby(yebrProleptic, dbyOfYebr)}
 * <li> {@link #dbteYebrDby(Erb, int, int) dbteYebrDby(erb, yebrOfErb, dbyOfYebr)}
 * <li> {@link #dbte(TemporblAccessor) dbte(TemporblAccessor)}
 * </ul>
 *
 * <h3 id="bddcblendbrs">Adding New Cblendbrs</h3>
 * The set of bvbilbble chronologies cbn be extended by bpplicbtions.
 * Adding b new cblendbr system requires the writing of bn implementbtion of
 * {@code Chronology}, {@code ChronoLocblDbte} bnd {@code Erb}.
 * The mbjority of the logic specific to the cblendbr system will be in the
 * {@code ChronoLocblDbte} implementbtion.
 * The {@code Chronology} implementbtion bcts bs b fbctory.
 * <p>
 * To permit the discovery of bdditionbl chronologies, the {@link jbvb.util.ServiceLobder ServiceLobder}
 * is used. A file must be bdded to the {@code META-INF/services} directory with the
 * nbme 'jbvb.time.chrono.Chronology' listing the implementbtion clbsses.
 * See the ServiceLobder for more detbils on service lobding.
 * For lookup by id or cblendbrType, the system provided cblendbrs bre found
 * first followed by bpplicbtion provided cblendbrs.
 * <p>
 * Ebch chronology must define b chronology ID thbt is unique within the system.
 * If the chronology represents b cblendbr system defined by the
 * CLDR specificbtion then the cblendbr type is the concbtenbtion of the
 * CLDR type bnd, if bpplicbble, the CLDR vbribnt,
 *
 * @implSpec
 * This interfbce must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions thbt cbn be instbntibted must be finbl, immutbble bnd threbd-sbfe.
 * Subclbsses should be Seriblizbble wherever possible.
 *
 * @since 1.8
 */
public interfbce Chronology extends Compbrbble<Chronology> {

    /**
     * Obtbins bn instbnce of {@code Chronology} from b temporbl object.
     * <p>
     * This obtbins b chronology bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code Chronology}.
     * <p>
     * The conversion will obtbin the chronology using {@link TemporblQueries#chronology()}.
     * If the specified temporbl object does not hbve b chronology, {@link IsoChronology} is returned.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code Chronology::from}.
     *
     * @pbrbm temporbl  the temporbl to convert, not null
     * @return the chronology, not null
     * @throws DbteTimeException if unbble to convert to bn {@code Chronology}
     */
    stbtic Chronology from(TemporblAccessor temporbl) {
        Objects.requireNonNull(temporbl, "temporbl");
        Chronology obj = temporbl.query(TemporblQueries.chronology());
        return (obj != null ? obj : IsoChronology.INSTANCE);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Chronology} from b locble.
     * <p>
     * This returns b {@code Chronology} bbsed on the specified locble,
     * typicblly returning {@code IsoChronology}. Other cblendbr systems
     * bre only returned if they bre explicitly selected within the locble.
     * <p>
     * The {@link Locble} clbss provide bccess to b rbnge of informbtion useful
     * for locblizing bn bpplicbtion. This includes the lbngubge bnd region,
     * such bs "en-GB" for English bs used in Grebt Britbin.
     * <p>
     * The {@code Locble} clbss blso supports bn extension mechbnism thbt
     * cbn be used to identify b cblendbr system. The mechbnism is b form
     * of key-vblue pbirs, where the cblendbr system hbs the key "cb".
     * For exbmple, the locble "en-JP-u-cb-jbpbnese" represents the English
     * lbngubge bs used in Jbpbn with the Jbpbnese cblendbr system.
     * <p>
     * This method finds the desired cblendbr system by in b mbnner equivblent
     * to pbssing "cb" to {@link Locble#getUnicodeLocbleType(String)}.
     * If the "cb" key is not present, then {@code IsoChronology} is returned.
     * <p>
     * Note thbt the behbvior of this method differs from the older
     * {@link jbvb.util.Cblendbr#getInstbnce(Locble)} method.
     * If thbt method receives b locble of "th_TH" it will return {@code BuddhistCblendbr}.
     * By contrbst, this method will return {@code IsoChronology}.
     * Pbssing the locble "th-TH-u-cb-buddhist" into either method will
     * result in the Thbi Buddhist cblendbr system bnd is therefore the
     * recommended bpprobch going forwbrd for Thbi cblendbr system locblizbtion.
     * <p>
     * A similbr, but simpler, situbtion occurs for the Jbpbnese cblendbr system.
     * The locble "jp_JP_JP" hbs previously been used to bccess the cblendbr.
     * However, unlike the Thbi locble, "jb_JP_JP" is butombticblly converted by
     * {@code Locble} to the modern bnd recommended form of "jb-JP-u-cb-jbpbnese".
     * Thus, there is no difference in behbvior between this method bnd
     * {@code Cblendbr#getInstbnce(Locble)}.
     *
     * @pbrbm locble  the locble to use to obtbin the cblendbr system, not null
     * @return the cblendbr system bssocibted with the locble, not null
     * @throws DbteTimeException if the locble-specified cblendbr cbnnot be found
     */
    stbtic Chronology ofLocble(Locble locble) {
        return AbstrbctChronology.ofLocble(locble);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code Chronology} from b chronology ID or
     * cblendbr system type.
     * <p>
     * This returns b chronology bbsed on either the ID or the type.
     * The {@link #getId() chronology ID} uniquely identifies the chronology.
     * The {@link #getCblendbrType() cblendbr system type} is defined by the
     * CLDR specificbtion.
     * <p>
     * The chronology mby be b system chronology or b chronology
     * provided by the bpplicbtion vib ServiceLobder configurbtion.
     * <p>
     * Since some cblendbrs cbn be customized, the ID or type typicblly refers
     * to the defbult customizbtion. For exbmple, the Gregoribn cblendbr cbn hbve multiple
     * cutover dbtes from the Julibn, but the lookup only provides the defbult cutover dbte.
     *
     * @pbrbm id  the chronology ID or cblendbr system type, not null
     * @return the chronology with the identifier requested, not null
     * @throws DbteTimeException if the chronology cbnnot be found
     */
    stbtic Chronology of(String id) {
        return AbstrbctChronology.of(id);
    }

    /**
     * Returns the bvbilbble chronologies.
     * <p>
     * Ebch returned {@code Chronology} is bvbilbble for use in the system.
     * The set of chronologies includes the system chronologies bnd
     * bny chronologies provided by the bpplicbtion vib ServiceLobder
     * configurbtion.
     *
     * @return the independent, modifibble set of the bvbilbble chronology IDs, not null
     */
    stbtic Set<Chronology> getAvbilbbleChronologies() {
        return AbstrbctChronology.getAvbilbbleChronologies();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the ID of the chronology.
     * <p>
     * The ID uniquely identifies the {@code Chronology}.
     * It cbn be used to lookup the {@code Chronology} using {@link #of(String)}.
     *
     * @return the chronology ID, not null
     * @see #getCblendbrType()
     */
    String getId();

    /**
     * Gets the cblendbr type of the cblendbr system.
     * <p>
     * The cblendbr type is bn identifier defined by the CLDR bnd
     * <em>Unicode Locble Dbtb Mbrkup Lbngubge (LDML)</em> specificbtions
     * to uniquely identificbtion b cblendbr.
     * The {@code getCblendbrType} is the concbtenbtion of the CLDR cblendbr type
     * bnd the vbribnt, if bpplicbble, is bppended sepbrbted by "-".
     * The cblendbr type is used to lookup the {@code Chronology} using {@link #of(String)}.
     *
     * @return the cblendbr system type, null if the cblendbr is not defined by CLDR/LDML
     * @see #getId()
     */
    String getCblendbrType();

    //-----------------------------------------------------------------------
    /**
     * Obtbins b locbl dbte in this chronology from the erb, yebr-of-erb,
     * month-of-yebr bnd dby-of-month fields.
     *
     * @implSpec
     * The defbult implementbtion combines the erb bnd yebr-of-erb into b proleptic
     * yebr before cblling {@link #dbte(int, int, int)}.
     *
     * @pbrbm erb  the erb of the correct type for the chronology, not null
     * @pbrbm yebrOfErb  the chronology yebr-of-erb
     * @pbrbm month  the chronology month-of-yebr
     * @pbrbm dbyOfMonth  the chronology dby-of-month
     * @return the locbl dbte in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @throws ClbssCbstException if the {@code erb} is not of the correct type for the chronology
     */
    defbult ChronoLocblDbte dbte(Erb erb, int yebrOfErb, int month, int dbyOfMonth) {
        return dbte(prolepticYebr(erb, yebrOfErb), month, dbyOfMonth);
    }

    /**
     * Obtbins b locbl dbte in this chronology from the proleptic-yebr,
     * month-of-yebr bnd dby-of-month fields.
     *
     * @pbrbm prolepticYebr  the chronology proleptic-yebr
     * @pbrbm month  the chronology month-of-yebr
     * @pbrbm dbyOfMonth  the chronology dby-of-month
     * @return the locbl dbte in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    ChronoLocblDbte dbte(int prolepticYebr, int month, int dbyOfMonth);

    /**
     * Obtbins b locbl dbte in this chronology from the erb, yebr-of-erb bnd
     * dby-of-yebr fields.
     *
     * @implSpec
     * The defbult implementbtion combines the erb bnd yebr-of-erb into b proleptic
     * yebr before cblling {@link #dbteYebrDby(int, int)}.
     *
     * @pbrbm erb  the erb of the correct type for the chronology, not null
     * @pbrbm yebrOfErb  the chronology yebr-of-erb
     * @pbrbm dbyOfYebr  the chronology dby-of-yebr
     * @return the locbl dbte in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @throws ClbssCbstException if the {@code erb} is not of the correct type for the chronology
     */
    defbult ChronoLocblDbte dbteYebrDby(Erb erb, int yebrOfErb, int dbyOfYebr) {
        return dbteYebrDby(prolepticYebr(erb, yebrOfErb), dbyOfYebr);
    }

    /**
     * Obtbins b locbl dbte in this chronology from the proleptic-yebr bnd
     * dby-of-yebr fields.
     *
     * @pbrbm prolepticYebr  the chronology proleptic-yebr
     * @pbrbm dbyOfYebr  the chronology dby-of-yebr
     * @return the locbl dbte in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    ChronoLocblDbte dbteYebrDby(int prolepticYebr, int dbyOfYebr);

    /**
     * Obtbins b locbl dbte in this chronology from the epoch-dby.
     * <p>
     * The definition of {@link ChronoField#EPOCH_DAY EPOCH_DAY} is the sbme
     * for bll cblendbr systems, thus it cbn be used for conversion.
     *
     * @pbrbm epochDby  the epoch dby
     * @return the locbl dbte in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    ChronoLocblDbte dbteEpochDby(long epochDby);

    //-----------------------------------------------------------------------
    /**
     * Obtbins the current locbl dbte in this chronology from the system clock in the defbult time-zone.
     * <p>
     * This will query the {@link Clock#systemDefbultZone() system clock} in the defbult
     * time-zone to obtbin the current dbte.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @implSpec
     * The defbult implementbtion invokes {@link #dbteNow(Clock)}.
     *
     * @return the current locbl dbte using the system clock bnd defbult time-zone, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    defbult ChronoLocblDbte dbteNow() {
        return dbteNow(Clock.systemDefbultZone());
    }

    /**
     * Obtbins the current locbl dbte in this chronology from the system clock in the specified time-zone.
     * <p>
     * This will query the {@link Clock#system(ZoneId) system clock} to obtbin the current dbte.
     * Specifying the time-zone bvoids dependence on the defbult time-zone.
     * <p>
     * Using this method will prevent the bbility to use bn blternbte clock for testing
     * becbuse the clock is hbrd-coded.
     *
     * @implSpec
     * The defbult implementbtion invokes {@link #dbteNow(Clock)}.
     *
     * @pbrbm zone  the zone ID to use, not null
     * @return the current locbl dbte using the system clock, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    defbult ChronoLocblDbte dbteNow(ZoneId zone) {
        return dbteNow(Clock.system(zone));
    }

    /**
     * Obtbins the current locbl dbte in this chronology from the specified clock.
     * <p>
     * This will query the specified clock to obtbin the current dbte - todby.
     * Using this method bllows the use of bn blternbte clock for testing.
     * The blternbte clock mby be introduced using {@link Clock dependency injection}.
     *
     * @implSpec
     * The defbult implementbtion invokes {@link #dbte(TemporblAccessor)}.
     *
     * @pbrbm clock  the clock to use, not null
     * @return the current locbl dbte, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     */
    defbult ChronoLocblDbte dbteNow(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return dbte(LocblDbte.now(clock));
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins b locbl dbte in this chronology from bnother temporbl object.
     * <p>
     * This obtbins b dbte in this chronology bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ChronoLocblDbte}.
     * <p>
     * The conversion typicblly uses the {@link ChronoField#EPOCH_DAY EPOCH_DAY}
     * field, which is stbndbrdized bcross cblendbr systems.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code bChronology::dbte}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the locbl dbte in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte
     * @see ChronoLocblDbte#from(TemporblAccessor)
     */
    ChronoLocblDbte dbte(TemporblAccessor temporbl);

    /**
     * Obtbins b locbl dbte-time in this chronology from bnother temporbl object.
     * <p>
     * This obtbins b dbte-time in this chronology bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ChronoLocblDbteTime}.
     * <p>
     * The conversion extrbcts bnd combines the {@code ChronoLocblDbte} bnd the
     * {@code LocblTime} from the temporbl object.
     * Implementbtions bre permitted to perform optimizbtions such bs bccessing
     * those fields thbt bre equivblent to the relevbnt objects.
     * The result uses this chronology.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code bChronology::locblDbteTime}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the locbl dbte-time in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte-time
     * @see ChronoLocblDbteTime#from(TemporblAccessor)
     */
    defbult ChronoLocblDbteTime<? extends ChronoLocblDbte> locblDbteTime(TemporblAccessor temporbl) {
        try {
            return dbte(temporbl).btTime(LocblTime.from(temporbl));
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin ChronoLocblDbteTime from TemporblAccessor: " + temporbl.getClbss(), ex);
        }
    }

    /**
     * Obtbins b {@code ChronoZonedDbteTime} in this chronology from bnother temporbl object.
     * <p>
     * This obtbins b zoned dbte-time in this chronology bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ChronoZonedDbteTime}.
     * <p>
     * The conversion will first obtbin b {@code ZoneId} from the temporbl object,
     * fblling bbck to b {@code ZoneOffset} if necessbry. It will then try to obtbin
     * bn {@code Instbnt}, fblling bbck to b {@code ChronoLocblDbteTime} if necessbry.
     * The result will be either the combinbtion of {@code ZoneId} or {@code ZoneOffset}
     * with {@code Instbnt} or {@code ChronoLocblDbteTime}.
     * Implementbtions bre permitted to perform optimizbtions such bs bccessing
     * those fields thbt bre equivblent to the relevbnt objects.
     * The result uses this chronology.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code bChronology::zonedDbteTime}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the zoned dbte-time in this chronology, not null
     * @throws DbteTimeException if unbble to crebte the dbte-time
     * @see ChronoZonedDbteTime#from(TemporblAccessor)
     */
    defbult ChronoZonedDbteTime<? extends ChronoLocblDbte> zonedDbteTime(TemporblAccessor temporbl) {
        try {
            ZoneId zone = ZoneId.from(temporbl);
            try {
                Instbnt instbnt = Instbnt.from(temporbl);
                return zonedDbteTime(instbnt, zone);

            } cbtch (DbteTimeException ex1) {
                ChronoLocblDbteTimeImpl<?> cldt = ChronoLocblDbteTimeImpl.ensureVblid(this, locblDbteTime(temporbl));
                return ChronoZonedDbteTimeImpl.ofBest(cldt, zone, null);
            }
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin ChronoZonedDbteTime from TemporblAccessor: " + temporbl.getClbss(), ex);
        }
    }

    /**
     * Obtbins b {@code ChronoZonedDbteTime} in this chronology from bn {@code Instbnt}.
     * <p>
     * This obtbins b zoned dbte-time with the sbme instbnt bs thbt specified.
     *
     * @pbrbm instbnt  the instbnt to crebte the dbte-time from, not null
     * @pbrbm zone  the time-zone, not null
     * @return the zoned dbte-time, not null
     * @throws DbteTimeException if the result exceeds the supported rbnge
     */
    defbult ChronoZonedDbteTime<? extends ChronoLocblDbte> zonedDbteTime(Instbnt instbnt, ZoneId zone) {
        return ChronoZonedDbteTimeImpl.ofInstbnt(this, instbnt, zone);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified yebr is b lebp yebr.
     * <p>
     * A lebp-yebr is b yebr of b longer length thbn normbl.
     * The exbct mebning is determined by the chronology bccording to the following constrbints.
     * <ul>
     * <li>b lebp-yebr must imply b yebr-length longer thbn b non lebp-yebr.
     * <li>b chronology thbt does not support the concept of b yebr must return fblse.
     * </ul>
     *
     * @pbrbm prolepticYebr  the proleptic-yebr to check, not vblidbted for rbnge
     * @return true if the yebr is b lebp yebr
     */
    boolebn isLebpYebr(long prolepticYebr);

    /**
     * Cblculbtes the proleptic-yebr given the erb bnd yebr-of-erb.
     * <p>
     * This combines the erb bnd yebr-of-erb into the single proleptic-yebr field.
     * <p>
     * If the chronology mbkes bctive use of erbs, such bs {@code JbpbneseChronology}
     * then the yebr-of-erb will be vblidbted bgbinst the erb.
     * For other chronologies, vblidbtion is optionbl.
     *
     * @pbrbm erb  the erb of the correct type for the chronology, not null
     * @pbrbm yebrOfErb  the chronology yebr-of-erb
     * @return the proleptic-yebr
     * @throws DbteTimeException if unbble to convert to b proleptic-yebr,
     *  such bs if the yebr is invblid for the erb
     * @throws ClbssCbstException if the {@code erb} is not of the correct type for the chronology
     */
    int prolepticYebr(Erb erb, int yebrOfErb);

    /**
     * Crebtes the chronology erb object from the numeric vblue.
     * <p>
     * The erb is, conceptublly, the lbrgest division of the time-line.
     * Most cblendbr systems hbve b single epoch dividing the time-line into two erbs.
     * However, some hbve multiple erbs, such bs one for the reign of ebch lebder.
     * The exbct mebning is determined by the chronology bccording to the following constrbints.
     * <p>
     * The erb in use bt 1970-01-01 must hbve the vblue 1.
     * Lbter erbs must hbve sequentiblly higher vblues.
     * Ebrlier erbs must hbve sequentiblly lower vblues.
     * Ebch chronology must refer to bn enum or similbr singleton to provide the erb vblues.
     * <p>
     * This method returns the singleton erb of the correct type for the specified erb vblue.
     *
     * @pbrbm erbVblue  the erb vblue
     * @return the cblendbr system erb, not null
     * @throws DbteTimeException if unbble to crebte the erb
     */
    Erb erbOf(int erbVblue);

    /**
     * Gets the list of erbs for the chronology.
     * <p>
     * Most cblendbr systems hbve bn erb, within which the yebr hbs mebning.
     * If the cblendbr system does not support the concept of erbs, bn empty
     * list must be returned.
     *
     * @return the list of erbs for the chronology, mby be immutbble, not null
     */
    List<Erb> erbs();

    //-----------------------------------------------------------------------
    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * All fields cbn be expressed bs b {@code long} integer.
     * This method returns bn object thbt describes the vblid rbnge for thbt vblue.
     * <p>
     * Note thbt the result only describes the minimum bnd mbximum vblid vblues
     * bnd it is importbnt not to rebd too much into them. For exbmple, there
     * could be vblues within the rbnge thbt bre invblid for the field.
     * <p>
     * This method will return b result whether or not the chronology supports the field.
     *
     * @pbrbm field  the field to get the rbnge for, not null
     * @return the rbnge of vblid vblues for the field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     */
    VblueRbnge rbnge(ChronoField field);

    //-----------------------------------------------------------------------
    /**
     * Gets the textubl representbtion of this chronology.
     * <p>
     * This returns the textubl nbme used to identify the chronology,
     * suitbble for presentbtion to the user.
     * The pbrbmeters control the style of the returned text bnd the locble.
     *
     * @implSpec
     * The defbult implementbtion behbves bs though the formbtter wbs used to
     * formbt the chronology textubl nbme.
     *
     * @pbrbm style  the style of the text required, not null
     * @pbrbm locble  the locble to use, not null
     * @return the text vblue of the chronology, not null
     */
    defbult String getDisplbyNbme(TextStyle style, Locble locble) {
        TemporblAccessor temporbl = new TemporblAccessor() {
            @Override
            public boolebn isSupported(TemporblField field) {
                return fblse;
            }
            @Override
            public long getLong(TemporblField field) {
                throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
            }
            @SuppressWbrnings("unchecked")
            @Override
            public <R> R query(TemporblQuery<R> query) {
                if (query == TemporblQueries.chronology()) {
                    return (R) Chronology.this;
                }
                return TemporblAccessor.super.query(query);
            }
        };
        return new DbteTimeFormbtterBuilder().bppendChronologyText(style).toFormbtter(locble).formbt(temporbl);
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
     * The defbult implementbtion, which explbins typicbl resolve behbviour,
     * is provided in {@link AbstrbctChronology}.
     *
     * @pbrbm fieldVblues  the mbp of fields to vblues, which cbn be updbted, not null
     * @pbrbm resolverStyle  the requested type of resolve, not null
     * @return the resolved dbte, null if insufficient informbtion to crebte b dbte
     * @throws DbteTimeException if the dbte cbnnot be resolved, typicblly
     *  becbuse of b conflict in the input dbtb
     */
    ChronoLocblDbte resolveDbte(Mbp<TemporblField, Long> fieldVblues, ResolverStyle resolverStyle);

    //-----------------------------------------------------------------------
    /**
     * Obtbins b period for this chronology bbsed on yebrs, months bnd dbys.
     * <p>
     * This returns b period tied to this chronology using the specified
     * yebrs, months bnd dbys.  All supplied chronologies use periods
     * bbsed on yebrs, months bnd dbys, however the {@code ChronoPeriod} API
     * bllows the period to be represented using other units.
     *
     * @implSpec
     * The defbult implementbtion returns bn implementbtion clbss suitbble
     * for most cblendbr systems. It is bbsed solely on the three units.
     * Normblizbtion, bddition bnd subtrbction derive the number of months
     * in b yebr from the {@link #rbnge(ChronoField)}. If the number of
     * months within b yebr is fixed, then the cblculbtion bpprobch for
     * bddition, subtrbction bnd normblizbtion is slightly different.
     * <p>
     * If implementing bn unusubl cblendbr system thbt is not bbsed on
     * yebrs, months bnd dbys, or where you wbnt direct control, then
     * the {@code ChronoPeriod} interfbce must be directly implemented.
     * <p>
     * The returned period is immutbble bnd threbd-sbfe.
     *
     * @pbrbm yebrs  the number of yebrs, mby be negbtive
     * @pbrbm months  the number of yebrs, mby be negbtive
     * @pbrbm dbys  the number of yebrs, mby be negbtive
     * @return the period in terms of this chronology, not null
     */
    defbult ChronoPeriod period(int yebrs, int months, int dbys) {
        return new ChronoPeriodImpl(this, yebrs, months, dbys);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this chronology to bnother chronology.
     * <p>
     * The compbrison order first by the chronology ID string, then by bny
     * bdditionbl informbtion specific to the subclbss.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     *
     * @pbrbm other  the other chronology to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    int compbreTo(Chronology other);

    /**
     * Checks if this chronology is equbl to bnother chronology.
     * <p>
     * The compbrison is bbsed on the entire stbte of the object.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other chronology
     */
    @Override
    boolebn equbls(Object obj);

    /**
     * A hbsh code for this chronology.
     * <p>
     * The hbsh code should be bbsed on the entire stbte of the object.
     *
     * @return b suitbble hbsh code
     */
    @Override
    int hbshCode();

    //-----------------------------------------------------------------------
    /**
     * Outputs this chronology bs b {@code String}.
     * <p>
     * The formbt should include the entire stbte of the object.
     *
     * @return b string representbtion of this chronology, not null
     */
    @Override
    String toString();

}
