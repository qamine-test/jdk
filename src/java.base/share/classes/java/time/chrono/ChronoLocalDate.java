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

import stbtic jbvb.time.temporbl.ChronoField.EPOCH_DAY;
import stbtic jbvb.time.temporbl.ChronoField.ERA;
import stbtic jbvb.time.temporbl.ChronoField.YEAR;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;

import jbvb.time.DbteTimeException;
import jbvb.time.LocblDbte;
import jbvb.time.LocblTime;
import jbvb.time.formbt.DbteTimeFormbtter;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.ChronoUnit;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblAmount;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.TemporblUnit;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.util.Compbrbtor;
import jbvb.util.Objects;

/**
 * A dbte without time-of-dby or time-zone in bn brbitrbry chronology, intended
 * for bdvbnced globblizbtion use cbses.
 * <p>
 * <b>Most bpplicbtions should declbre method signbtures, fields bnd vbribbles
 * bs {@link LocblDbte}, not this interfbce.</b>
 * <p>
 * A {@code ChronoLocblDbte} is the bbstrbct representbtion of b dbte where the
 * {@code Chronology chronology}, or cblendbr system, is pluggbble.
 * The dbte is defined in terms of fields expressed by {@link TemporblField},
 * where most common implementbtions bre defined in {@link ChronoField}.
 * The chronology defines how the cblendbr system operbtes bnd the mebning of
 * the stbndbrd fields.
 *
 * <h3>When to use this interfbce</h3>
 * The design of the API encourbges the use of {@code LocblDbte} rbther thbn this
 * interfbce, even in the cbse where the bpplicbtion needs to debl with multiple
 * cblendbr systems.
 * <p>
 * This concept cbn seem surprising bt first, bs the nbturbl wby to globblize bn
 * bpplicbtion might initiblly bppebr to be to bbstrbct the cblendbr system.
 * However, bs explored below, bbstrbcting the cblendbr system is usublly the wrong
 * bpprobch, resulting in logic errors bnd hbrd to find bugs.
 * As such, it should be considered bn bpplicbtion-wide brchitecturbl decision to choose
 * to use this interfbce bs opposed to {@code LocblDbte}.
 *
 * <h3>Architecturbl issues to consider</h3>
 * These bre some of the points thbt must be considered before using this interfbce
 * throughout bn bpplicbtion.
 * <p>
 * 1) Applicbtions using this interfbce, bs opposed to using just {@code LocblDbte},
 * fbce b significbntly higher probbbility of bugs. This is becbuse the cblendbr system
 * in use is not known bt development time. A key cbuse of bugs is where the developer
 * bpplies bssumptions from their dby-to-dby knowledge of the ISO cblendbr system
 * to code thbt is intended to debl with bny brbitrbry cblendbr system.
 * The section below outlines how those bssumptions cbn cbuse problems
 * The primbry mechbnism for reducing this increbsed risk of bugs is b strong code review process.
 * This should blso be considered b extrb cost in mbintenbnce for the lifetime of the code.
 * <p>
 * 2) This interfbce does not enforce immutbbility of implementbtions.
 * While the implementbtion notes indicbte thbt bll implementbtions must be immutbble
 * there is nothing in the code or type system to enforce this. Any method declbred
 * to bccept b {@code ChronoLocblDbte} could therefore be pbssed b poorly or
 * mbliciously written mutbble implementbtion.
 * <p>
 * 3) Applicbtions using this interfbce  must consider the impbct of erbs.
 * {@code LocblDbte} shields users from the concept of erbs, by ensuring thbt {@code getYebr()}
 * returns the proleptic yebr. Thbt decision ensures thbt developers cbn think of
 * {@code LocblDbte} instbnces bs consisting of three fields - yebr, month-of-yebr bnd dby-of-month.
 * By contrbst, users of this interfbce must think of dbtes bs consisting of four fields -
 * erb, yebr-of-erb, month-of-yebr bnd dby-of-month. The extrb erb field is frequently
 * forgotten, yet it is of vitbl importbnce to dbtes in bn brbitrbry cblendbr system.
 * For exbmple, in the Jbpbnese cblendbr system, the erb represents the reign of bn Emperor.
 * Whenever one reign ends bnd bnother stbrts, the yebr-of-erb is reset to one.
 * <p>
 * 4) The only bgreed internbtionbl stbndbrd for pbssing b dbte between two systems
 * is the ISO-8601 stbndbrd which requires the ISO cblendbr system. Using this interfbce
 * throughout the bpplicbtion will inevitbbly lebd to the requirement to pbss the dbte
 * bcross b network or component boundbry, requiring bn bpplicbtion specific protocol or formbt.
 * <p>
 * 5) Long term persistence, such bs b dbtbbbse, will blmost blwbys only bccept dbtes in the
 * ISO-8601 cblendbr system (or the relbted Julibn-Gregoribn). Pbssing bround dbtes in other
 * cblendbr systems increbses the complicbtions of interbcting with persistence.
 * <p>
 * 6) Most of the time, pbssing b {@code ChronoLocblDbte} throughout bn bpplicbtion
 * is unnecessbry, bs discussed in the lbst section below.
 *
 * <h3>Fblse bssumptions cbusing bugs in multi-cblendbr system code</h3>
 * As indicbted bbove, there bre mbny issues to consider when try to use bnd mbnipulbte b
 * dbte in bn brbitrbry cblendbr system. These bre some of the key issues.
 * <p>
 * Code thbt queries the dby-of-month bnd bssumes thbt the vblue will never be more thbn
 * 31 is invblid. Some cblendbr systems hbve more thbn 31 dbys in some months.
 * <p>
 * Code thbt bdds 12 months to b dbte bnd bssumes thbt b yebr hbs been bdded is invblid.
 * Some cblendbr systems hbve b different number of months, such bs 13 in the Coptic or Ethiopic.
 * <p>
 * Code thbt bdds one month to b dbte bnd bssumes thbt the month-of-yebr vblue will increbse
 * by one or wrbp to the next yebr is invblid. Some cblendbr systems hbve b vbribble number
 * of months in b yebr, such bs the Hebrew.
 * <p>
 * Code thbt bdds one month, then bdds b second one month bnd bssumes thbt the dby-of-month
 * will rembin close to its originbl vblue is invblid. Some cblendbr systems hbve b lbrge difference
 * between the length of the longest month bnd the length of the shortest month.
 * For exbmple, the Coptic or Ethiopic hbve 12 months of 30 dbys bnd 1 month of 5 dbys.
 * <p>
 * Code thbt bdds seven dbys bnd bssumes thbt b week hbs been bdded is invblid.
 * Some cblendbr systems hbve weeks of other thbn seven dbys, such bs the French Revolutionbry.
 * <p>
 * Code thbt bssumes thbt becbuse the yebr of {@code dbte1} is grebter thbn the yebr of {@code dbte2}
 * then {@code dbte1} is bfter {@code dbte2} is invblid. This is invblid for bll cblendbr systems
 * when referring to the yebr-of-erb, bnd especiblly untrue of the Jbpbnese cblendbr system
 * where the yebr-of-erb restbrts with the reign of every new Emperor.
 * <p>
 * Code thbt trebts month-of-yebr one bnd dby-of-month one bs the stbrt of the yebr is invblid.
 * Not bll cblendbr systems stbrt the yebr when the month vblue is one.
 * <p>
 * In generbl, mbnipulbting b dbte, bnd even querying b dbte, is wide open to bugs when the
 * cblendbr system is unknown bt development time. This is why it is essentibl thbt code using
 * this interfbce is subjected to bdditionbl code reviews. It is blso why bn brchitecturbl
 * decision to bvoid this interfbce type is usublly the correct one.
 *
 * <h3>Using LocblDbte instebd</h3>
 * The primbry blternbtive to using this interfbce throughout your bpplicbtion is bs follows.
 * <ul>
 * <li>Declbre bll method signbtures referring to dbtes in terms of {@code LocblDbte}.
 * <li>Either store the chronology (cblendbr system) in the user profile or lookup
 *  the chronology from the user locble
 * <li>Convert the ISO {@code LocblDbte} to bnd from the user's preferred cblendbr system during
 *  printing bnd pbrsing
 * </ul>
 * This bpprobch trebts the problem of globblized cblendbr systems bs b locblizbtion issue
 * bnd confines it to the UI lbyer. This bpprobch is in keeping with other locblizbtion
 * issues in the jbvb plbtform.
 * <p>
 * As discussed bbove, performing cblculbtions on b dbte where the rules of the cblendbr system
 * bre pluggbble requires skill bnd is not recommended.
 * Fortunbtely, the need to perform cblculbtions on b dbte in bn brbitrbry cblendbr system
 * is extremely rbre. For exbmple, it is highly unlikely thbt the business rules of b librbry
 * book rentbl scheme will bllow rentbls to be for one month, where mebning of the month
 * is dependent on the user's preferred cblendbr system.
 * <p>
 * A key use cbse for cblculbtions on b dbte in bn brbitrbry cblendbr system is producing
 * b month-by-month cblendbr for displby bnd user interbction. Agbin, this is b UI issue,
 * bnd use of this interfbce solely within b few methods of the UI lbyer mby be justified.
 * <p>
 * In bny other pbrt of the system, where b dbte must be mbnipulbted in b cblendbr system
 * other thbn ISO, the use cbse will generblly specify the cblendbr system to use.
 * For exbmple, bn bpplicbtion mby need to cblculbte the next Islbmic or Hebrew holidby
 * which mby require mbnipulbting the dbte.
 * This kind of use cbse cbn be hbndled bs follows:
 * <ul>
 * <li>stbrt from the ISO {@code LocblDbte} being pbssed to the method
 * <li>convert the dbte to the blternbte cblendbr system, which for this use cbse is known
 *  rbther thbn brbitrbry
 * <li>perform the cblculbtion
 * <li>convert bbck to {@code LocblDbte}
 * </ul>
 * Developers writing low-level frbmeworks or librbries should blso bvoid this interfbce.
 * Instebd, one of the two generbl purpose bccess interfbces should be used.
 * Use {@link TemporblAccessor} if rebd-only bccess is required, or use {@link Temporbl}
 * if rebd-write bccess is required.
 *
 * @implSpec
 * This interfbce must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions thbt cbn be instbntibted must be finbl, immutbble bnd threbd-sbfe.
 * Subclbsses should be Seriblizbble wherever possible.
 * <p>
 * Additionbl cblendbr systems mby be bdded to the system.
 * See {@link Chronology} for more detbils.
 *
 * @since 1.8
 */
public interfbce ChronoLocblDbte
        extends Temporbl, TemporblAdjuster, Compbrbble<ChronoLocblDbte> {

    /**
     * Gets b compbrbtor thbt compbres {@code ChronoLocblDbte} in
     * time-line order ignoring the chronology.
     * <p>
     * This compbrbtor differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte bnd not the chronology.
     * This bllows dbtes in different cblendbr systems to be compbred bbsed
     * on the position of the dbte on the locbl time-line.
     * The underlying compbrison is equivblent to compbring the epoch-dby.
     *
     * @return b compbrbtor thbt compbres in time-line order ignoring the chronology
     * @see #isAfter
     * @see #isBefore
     * @see #isEqubl
     */
    stbtic Compbrbtor<ChronoLocblDbte> timeLineOrder() {
        return AbstrbctChronology.DATE_ORDER;
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code ChronoLocblDbte} from b temporbl object.
     * <p>
     * This obtbins b locbl dbte bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code ChronoLocblDbte}.
     * <p>
     * The conversion extrbcts bnd combines the chronology bnd the dbte
     * from the temporbl object. The behbvior is equivblent to using
     * {@link Chronology#dbte(TemporblAccessor)} with the extrbcted chronology.
     * Implementbtions bre permitted to perform optimizbtions such bs bccessing
     * those fields thbt bre equivblent to the relevbnt objects.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code ChronoLocblDbte::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the dbte, not null
     * @throws DbteTimeException if unbble to convert to b {@code ChronoLocblDbte}
     * @see Chronology#dbte(TemporblAccessor)
     */
    stbtic ChronoLocblDbte from(TemporblAccessor temporbl) {
        if (temporbl instbnceof ChronoLocblDbte) {
            return (ChronoLocblDbte) temporbl;
        }
        Objects.requireNonNull(temporbl, "temporbl");
        Chronology chrono = temporbl.query(TemporblQueries.chronology());
        if (chrono == null) {
            throw new DbteTimeException("Unbble to obtbin ChronoLocblDbte from TemporblAccessor: " + temporbl.getClbss());
        }
        return chrono.dbte(temporbl);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this dbte.
     * <p>
     * The {@code Chronology} represents the cblendbr system in use.
     * The erb bnd other fields in {@link ChronoField} bre defined by the chronology.
     *
     * @return the chronology, not null
     */
    Chronology getChronology();

    /**
     * Gets the erb, bs defined by the chronology.
     * <p>
     * The erb is, conceptublly, the lbrgest division of the time-line.
     * Most cblendbr systems hbve b single epoch dividing the time-line into two erbs.
     * However, some hbve multiple erbs, such bs one for the reign of ebch lebder.
     * The exbct mebning is determined by the {@code Chronology}.
     * <p>
     * All correctly implemented {@code Erb} clbsses bre singletons, thus it
     * is vblid code to write {@code dbte.getErb() == SomeChrono.ERA_NAME)}.
     * <p>
     * This defbult implementbtion uses {@link Chronology#erbOf(int)}.
     *
     * @return the chronology specific erb constbnt bpplicbble bt this dbte, not null
     */
    defbult Erb getErb() {
        return getChronology().erbOf(get(ERA));
    }

    /**
     * Checks if the yebr is b lebp yebr, bs defined by the cblendbr system.
     * <p>
     * A lebp-yebr is b yebr of b longer length thbn normbl.
     * The exbct mebning is determined by the chronology with the constrbint thbt
     * b lebp-yebr must imply b yebr-length longer thbn b non lebp-yebr.
     * <p>
     * This defbult implementbtion uses {@link Chronology#isLebpYebr(long)}.
     *
     * @return true if this dbte is in b lebp yebr, fblse otherwise
     */
    defbult boolebn isLebpYebr() {
        return getChronology().isLebpYebr(getLong(YEAR));
    }

    /**
     * Returns the length of the month represented by this dbte, bs defined by the cblendbr system.
     * <p>
     * This returns the length of the month in dbys.
     *
     * @return the length of the month in dbys
     */
    int lengthOfMonth();

    /**
     * Returns the length of the yebr represented by this dbte, bs defined by the cblendbr system.
     * <p>
     * This returns the length of the yebr in dbys.
     * <p>
     * The defbult implementbtion uses {@link #isLebpYebr()} bnd returns 365 or 366.
     *
     * @return the length of the yebr in dbys
     */
    defbult int lengthOfYebr() {
        return (isLebpYebr() ? 366 : 365);
    }

    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if the specified field cbn be queried on this dbte.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge},
     * {@link #get(TemporblField) get} bnd {@link #with(TemporblField, long)}
     * methods will throw bn exception.
     * <p>
     * The set of supported fields is defined by the chronology bnd normblly includes
     * bll {@code ChronoField} dbte fields.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field cbn be queried, fblse if not
     */
    @Override
    defbult boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field.isDbteBbsed();
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Checks if the specified unit is supported.
     * <p>
     * This checks if the specified unit cbn be bdded to or subtrbcted from this dbte.
     * If fblse, then cblling the {@link #plus(long, TemporblUnit)} bnd
     * {@link #minus(long, TemporblUnit) minus} methods will throw bn exception.
     * <p>
     * The set of supported units is defined by the chronology bnd normblly includes
     * bll {@code ChronoUnit} dbte units except {@code FOREVER}.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.isSupportedBy(Temporbl)}
     * pbssing {@code this} bs the brgument.
     * Whether the unit is supported is determined by the unit.
     *
     * @pbrbm unit  the unit to check, null returns fblse
     * @return true if the unit cbn be bdded/subtrbcted, fblse if not
     */
    @Override
    defbult boolebn isSupported(TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            return unit.isDbteBbsed();
        }
        return unit != null && unit.isSupportedBy(this);
    }

    //-----------------------------------------------------------------------
    // override for covbribnt return type
    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbte with(TemporblAdjuster bdjuster) {
        return ChronoLocblDbteImpl.ensureVblid(getChronology(), Temporbl.super.with(bdjuster));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws UnsupportedTemporblTypeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbte with(TemporblField field, long newVblue) {
        if (field instbnceof ChronoField) {
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return ChronoLocblDbteImpl.ensureVblid(getChronology(), field.bdjustInto(this, newVblue));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbte plus(TemporblAmount bmount) {
        return ChronoLocblDbteImpl.ensureVblid(getChronology(), Temporbl.super.plus(bmount));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbte plus(long bmountToAdd, TemporblUnit unit) {
        if (unit instbnceof ChronoUnit) {
            throw new UnsupportedTemporblTypeException("Unsupported unit: " + unit);
        }
        return ChronoLocblDbteImpl.ensureVblid(getChronology(), unit.bddTo(this, bmountToAdd));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbte minus(TemporblAmount bmount) {
        return ChronoLocblDbteImpl.ensureVblid(getChronology(), Temporbl.super.minus(bmount));
    }

    /**
     * {@inheritDoc}
     * @throws DbteTimeException {@inheritDoc}
     * @throws UnsupportedTemporblTypeException {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Override
    defbult ChronoLocblDbte minus(long bmountToSubtrbct, TemporblUnit unit) {
        return ChronoLocblDbteImpl.ensureVblid(getChronology(), Temporbl.super.minus(bmountToSubtrbct, unit));
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this dbte using the specified query.
     * <p>
     * This queries this dbte using the specified query strbtegy object.
     * The {@code TemporblQuery} object defines the logic to be used to
     * obtbin the result. Rebd the documentbtion of the query to understbnd
     * whbt the result of this method will be.
     * <p>
     * The result of this method is obtbined by invoking the
     * {@link TemporblQuery#queryFrom(TemporblAccessor)} method on the
     * specified query pbssing {@code this} bs the brgument.
     *
     * @pbrbm <R> the type of the result
     * @pbrbm query  the query to invoke, not null
     * @return the query result, null mby be returned (defined by the query)
     * @throws DbteTimeException if unbble to query (defined by the query)
     * @throws ArithmeticException if numeric overflow occurs (defined by the query)
     */
    @SuppressWbrnings("unchecked")
    @Override
    defbult <R> R query(TemporblQuery<R> query) {
        if (query == TemporblQueries.zoneId() || query == TemporblQueries.zone() || query == TemporblQueries.offset()) {
            return null;
        } else if (query == TemporblQueries.locblTime()) {
            return null;
        } else if (query == TemporblQueries.chronology()) {
            return (R) getChronology();
        } else if (query == TemporblQueries.precision()) {
            return (R) DAYS;
        }
        // inline TemporblAccessor.super.query(query) bs bn optimizbtion
        // non-JDK clbsses bre not permitted to mbke this optimizbtion
        return query.queryFrom(this);
    }

    /**
     * Adjusts the specified temporbl object to hbve the sbme dbte bs this object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the dbte chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#EPOCH_DAY} bs the field.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisLocblDbte.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisLocblDbte);
     * </pre>
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm temporbl  the tbrget object to be bdjusted, not null
     * @return the bdjusted object, not null
     * @throws DbteTimeException if unbble to mbke the bdjustment
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    defbult Temporbl bdjustInto(Temporbl temporbl) {
        return temporbl.with(EPOCH_DAY, toEpochDby());
    }

    /**
     * Cblculbtes the bmount of time until bnother dbte in terms of the specified unit.
     * <p>
     * This cblculbtes the bmount of time between two {@code ChronoLocblDbte}
     * objects in terms of b single {@code TemporblUnit}.
     * The stbrt bnd end points bre {@code this} bnd the specified dbte.
     * The result will be negbtive if the end is before the stbrt.
     * The {@code Temporbl} pbssed to this method is converted to b
     * {@code ChronoLocblDbte} using {@link Chronology#dbte(TemporblAccessor)}.
     * The cblculbtion returns b whole number, representing the number of
     * complete units between the two dbtes.
     * For exbmple, the bmount in dbys between two dbtes cbn be cblculbted
     * using {@code stbrtDbte.until(endDbte, DAYS)}.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method.
     * The second is to use {@link TemporblUnit#between(Temporbl, Temporbl)}:
     * <pre>
     *   // these two lines bre equivblent
     *   bmount = stbrt.until(end, MONTHS);
     *   bmount = MONTHS.between(stbrt, end);
     * </pre>
     * The choice should be mbde bbsed on which mbkes the code more rebdbble.
     * <p>
     * The cblculbtion is implemented in this method for {@link ChronoUnit}.
     * The units {@code DAYS}, {@code WEEKS}, {@code MONTHS}, {@code YEARS},
     * {@code DECADES}, {@code CENTURIES}, {@code MILLENNIA} bnd {@code ERAS}
     * should be supported by bll implementbtions.
     * Other {@code ChronoUnit} vblues will throw bn exception.
     * <p>
     * If the unit is not b {@code ChronoUnit}, then the result of this method
     * is obtbined by invoking {@code TemporblUnit.between(Temporbl, Temporbl)}
     * pbssing {@code this} bs the first brgument bnd the converted input temporbl bs
     * the second brgument.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm endExclusive  the end dbte, exclusive, which is converted to b
     *  {@code ChronoLocblDbte} in the sbme chronology, not null
     * @pbrbm unit  the unit to mebsure the bmount in, not null
     * @return the bmount of time between this dbte bnd the end dbte
     * @throws DbteTimeException if the bmount cbnnot be cblculbted, or the end
     *  temporbl cbnnot be converted to b {@code ChronoLocblDbte}
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override  // override for Jbvbdoc
    long until(Temporbl endExclusive, TemporblUnit unit);

    /**
     * Cblculbtes the period between this dbte bnd bnother dbte bs b {@code ChronoPeriod}.
     * <p>
     * This cblculbtes the period between two dbtes. All supplied chronologies
     * cblculbte the period using yebrs, months bnd dbys, however the
     * {@code ChronoPeriod} API bllows the period to be represented using other units.
     * <p>
     * The stbrt bnd end points bre {@code this} bnd the specified dbte.
     * The result will be negbtive if the end is before the stbrt.
     * The negbtive sign will be the sbme in ebch of yebr, month bnd dby.
     * <p>
     * The cblculbtion is performed using the chronology of this dbte.
     * If necessbry, the input dbte will be converted to mbtch.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm endDbteExclusive  the end dbte, exclusive, which mby be in bny chronology, not null
     * @return the period between this dbte bnd the end dbte, not null
     * @throws DbteTimeException if the period cbnnot be cblculbted
     * @throws ArithmeticException if numeric overflow occurs
     */
    ChronoPeriod until(ChronoLocblDbte endDbteExclusive);

    /**
     * Formbts this dbte using the specified formbtter.
     * <p>
     * This dbte will be pbssed to the formbtter to produce b string.
     * <p>
     * The defbult implementbtion must behbve bs follows:
     * <pre>
     *  return formbtter.formbt(this);
     * </pre>
     *
     * @pbrbm formbtter  the formbtter to use, not null
     * @return the formbtted dbte string, not null
     * @throws DbteTimeException if bn error occurs during printing
     */
    defbult String formbt(DbteTimeFormbtter formbtter) {
        Objects.requireNonNull(formbtter, "formbtter");
        return formbtter.formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Combines this dbte with b time to crebte b {@code ChronoLocblDbteTime}.
     * <p>
     * This returns b {@code ChronoLocblDbteTime} formed from this dbte bt the specified time.
     * All possible combinbtions of dbte bnd time bre vblid.
     *
     * @pbrbm locblTime  the locbl time to use, not null
     * @return the locbl dbte-time formed from this dbte bnd the specified time, not null
     */
    @SuppressWbrnings("unchecked")
    defbult ChronoLocblDbteTime<?> btTime(LocblTime locblTime) {
        return ChronoLocblDbteTimeImpl.of(this, locblTime);
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this dbte to the Epoch Dby.
     * <p>
     * The {@link ChronoField#EPOCH_DAY Epoch Dby count} is b simple
     * incrementing count of dbys where dby 0 is 1970-01-01 (ISO).
     * This definition is the sbme for bll chronologies, enbbling conversion.
     * <p>
     * This defbult implementbtion queries the {@code EPOCH_DAY} field.
     *
     * @return the Epoch Dby equivblent to this dbte
     */
    defbult long toEpochDby() {
        return getLong(EPOCH_DAY);
    }

    //-----------------------------------------------------------------------
    /**
     * Compbres this dbte to bnother dbte, including the chronology.
     * <p>
     * The compbrison is bbsed first on the underlying time-line dbte, then
     * on the chronology.
     * It is "consistent with equbls", bs defined by {@link Compbrbble}.
     * <p>
     * For exbmple, the following is the compbrbtor order:
     * <ol>
     * <li>{@code 2012-12-03 (ISO)}</li>
     * <li>{@code 2012-12-04 (ISO)}</li>
     * <li>{@code 2555-12-04 (ThbiBuddhist)}</li>
     * <li>{@code 2012-12-05 (ISO)}</li>
     * </ol>
     * Vblues #2 bnd #3 represent the sbme dbte on the time-line.
     * When two vblues represent the sbme dbte, the chronology ID is compbred to distinguish them.
     * This step is needed to mbke the ordering "consistent with equbls".
     * <p>
     * If bll the dbte objects being compbred bre in the sbme chronology, then the
     * bdditionbl chronology stbge is not required bnd only the locbl dbte is used.
     * To compbre the dbtes of two {@code TemporblAccessor} instbnces, including dbtes
     * in two different chronologies, use {@link ChronoField#EPOCH_DAY} bs b compbrbtor.
     * <p>
     * This defbult implementbtion performs the compbrison defined bbove.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return the compbrbtor vblue, negbtive if less, positive if grebter
     */
    @Override
    defbult int compbreTo(ChronoLocblDbte other) {
        int cmp = Long.compbre(toEpochDby(), other.toEpochDby());
        if (cmp == 0) {
            cmp = getChronology().compbreTo(other.getChronology());
        }
        return cmp;
    }

    /**
     * Checks if this dbte is bfter the specified dbte ignoring the chronology.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte bnd not the chronology.
     * This bllows dbtes in different cblendbr systems to be compbred bbsed
     * on the time-line position.
     * This is equivblent to using {@code dbte1.toEpochDby() &gt; dbte2.toEpochDby()}.
     * <p>
     * This defbult implementbtion performs the compbrison bbsed on the epoch-dby.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return true if this is bfter the specified dbte
     */
    defbult boolebn isAfter(ChronoLocblDbte other) {
        return this.toEpochDby() > other.toEpochDby();
    }

    /**
     * Checks if this dbte is before the specified dbte ignoring the chronology.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte bnd not the chronology.
     * This bllows dbtes in different cblendbr systems to be compbred bbsed
     * on the time-line position.
     * This is equivblent to using {@code dbte1.toEpochDby() &lt; dbte2.toEpochDby()}.
     * <p>
     * This defbult implementbtion performs the compbrison bbsed on the epoch-dby.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return true if this is before the specified dbte
     */
    defbult boolebn isBefore(ChronoLocblDbte other) {
        return this.toEpochDby() < other.toEpochDby();
    }

    /**
     * Checks if this dbte is equbl to the specified dbte ignoring the chronology.
     * <p>
     * This method differs from the compbrison in {@link #compbreTo} in thbt it
     * only compbres the underlying dbte bnd not the chronology.
     * This bllows dbtes in different cblendbr systems to be compbred bbsed
     * on the time-line position.
     * This is equivblent to using {@code dbte1.toEpochDby() == dbte2.toEpochDby()}.
     * <p>
     * This defbult implementbtion performs the compbrison bbsed on the epoch-dby.
     *
     * @pbrbm other  the other dbte to compbre to, not null
     * @return true if the underlying dbte is equbl to the specified dbte
     */
    defbult boolebn isEqubl(ChronoLocblDbte other) {
        return this.toEpochDby() == other.toEpochDby();
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this dbte is equbl to bnother dbte, including the chronology.
     * <p>
     * Compbres this dbte with bnother ensuring thbt the dbte bnd chronology bre the sbme.
     * <p>
     * To compbre the dbtes of two {@code TemporblAccessor} instbnces, including dbtes
     * in two different chronologies, use {@link ChronoField#EPOCH_DAY} bs b compbrbtor.
     *
     * @pbrbm obj  the object to check, null returns fblse
     * @return true if this is equbl to the other dbte
     */
    @Override
    boolebn equbls(Object obj);

    /**
     * A hbsh code for this dbte.
     *
     * @return b suitbble hbsh code
     */
    @Override
    int hbshCode();

    //-----------------------------------------------------------------------
    /**
     * Outputs this dbte bs b {@code String}.
     * <p>
     * The output will include the full locbl dbte.
     *
     * @return the formbtted dbte, not null
     */
    @Override
    String toString();

}
