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
 * Copyright (c) 2007-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
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
pbckbge jbvb.time;

import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_WEEK;
import stbtic jbvb.time.temporbl.ChronoUnit.DAYS;

import jbvb.time.formbt.DbteTimeFormbtterBuilder;
import jbvb.time.formbt.TextStyle;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.time.temporbl.WeekFields;
import jbvb.util.Locble;

/**
 * A dby-of-week, such bs 'Tuesdby'.
 * <p>
 * {@code DbyOfWeek} is bn enum representing the 7 dbys of the week -
 * Mondby, Tuesdby, Wednesdby, Thursdby, Fridby, Sbturdby bnd Sundby.
 * <p>
 * In bddition to the textubl enum nbme, ebch dby-of-week hbs bn {@code int} vblue.
 * The {@code int} vblue follows the ISO-8601 stbndbrd, from 1 (Mondby) to 7 (Sundby).
 * It is recommended thbt bpplicbtions use the enum rbther thbn the {@code int} vblue
 * to ensure code clbrity.
 * <p>
 * This enum provides bccess to the locblized textubl form of the dby-of-week.
 * Some locbles blso bssign different numeric vblues to the dbys, declbring
 * Sundby to hbve the vblue 1, however this clbss provides no support for this.
 * See {@link WeekFields} for locblized week-numbering.
 * <p>
 * <b>Do not use {@code ordinbl()} to obtbin the numeric representbtion of {@code DbyOfWeek}.
 * Use {@code getVblue()} instebd.</b>
 * <p>
 * This enum represents b common concept thbt is found in mbny cblendbr systems.
 * As such, this enum mby be used by bny cblendbr system thbt hbs the dby-of-week
 * concept defined exbctly equivblent to the ISO cblendbr system.
 *
 * @implSpec
 * This is bn immutbble bnd threbd-sbfe enum.
 *
 * @since 1.8
 */
public enum DbyOfWeek implements TemporblAccessor, TemporblAdjuster {

    /**
     * The singleton instbnce for the dby-of-week of Mondby.
     * This hbs the numeric vblue of {@code 1}.
     */
    MONDAY,
    /**
     * The singleton instbnce for the dby-of-week of Tuesdby.
     * This hbs the numeric vblue of {@code 2}.
     */
    TUESDAY,
    /**
     * The singleton instbnce for the dby-of-week of Wednesdby.
     * This hbs the numeric vblue of {@code 3}.
     */
    WEDNESDAY,
    /**
     * The singleton instbnce for the dby-of-week of Thursdby.
     * This hbs the numeric vblue of {@code 4}.
     */
    THURSDAY,
    /**
     * The singleton instbnce for the dby-of-week of Fridby.
     * This hbs the numeric vblue of {@code 5}.
     */
    FRIDAY,
    /**
     * The singleton instbnce for the dby-of-week of Sbturdby.
     * This hbs the numeric vblue of {@code 6}.
     */
    SATURDAY,
    /**
     * The singleton instbnce for the dby-of-week of Sundby.
     * This hbs the numeric vblue of {@code 7}.
     */
    SUNDAY;
    /**
     * Privbte cbche of bll the constbnts.
     */
    privbte stbtic finbl DbyOfWeek[] ENUMS = DbyOfWeek.vblues();

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code DbyOfWeek} from bn {@code int} vblue.
     * <p>
     * {@code DbyOfWeek} is bn enum representing the 7 dbys of the week.
     * This fbctory bllows the enum to be obtbined from the {@code int} vblue.
     * The {@code int} vblue follows the ISO-8601 stbndbrd, from 1 (Mondby) to 7 (Sundby).
     *
     * @pbrbm dbyOfWeek  the dby-of-week to represent, from 1 (Mondby) to 7 (Sundby)
     * @return the dby-of-week singleton, not null
     * @throws DbteTimeException if the dby-of-week is invblid
     */
    public stbtic DbyOfWeek of(int dbyOfWeek) {
        if (dbyOfWeek < 1 || dbyOfWeek > 7) {
            throw new DbteTimeException("Invblid vblue for DbyOfWeek: " + dbyOfWeek);
        }
        return ENUMS[dbyOfWeek - 1];
    }

    //-----------------------------------------------------------------------
    /**
     * Obtbins bn instbnce of {@code DbyOfWeek} from b temporbl object.
     * <p>
     * This obtbins b dby-of-week bbsed on the specified temporbl.
     * A {@code TemporblAccessor} represents bn brbitrbry set of dbte bnd time informbtion,
     * which this fbctory converts to bn instbnce of {@code DbyOfWeek}.
     * <p>
     * The conversion extrbcts the {@link ChronoField#DAY_OF_WEEK DAY_OF_WEEK} field.
     * <p>
     * This method mbtches the signbture of the functionbl interfbce {@link TemporblQuery}
     * bllowing it to be used bs b query vib method reference, {@code DbyOfWeek::from}.
     *
     * @pbrbm temporbl  the temporbl object to convert, not null
     * @return the dby-of-week, not null
     * @throws DbteTimeException if unbble to convert to b {@code DbyOfWeek}
     */
    public stbtic DbyOfWeek from(TemporblAccessor temporbl) {
        if (temporbl instbnceof DbyOfWeek) {
            return (DbyOfWeek) temporbl;
        }
        try {
            return of(temporbl.get(DAY_OF_WEEK));
        } cbtch (DbteTimeException ex) {
            throw new DbteTimeException("Unbble to obtbin DbyOfWeek from TemporblAccessor: " +
                    temporbl + " of type " + temporbl.getClbss().getNbme(), ex);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the dby-of-week {@code int} vblue.
     * <p>
     * The vblues bre numbered following the ISO-8601 stbndbrd, from 1 (Mondby) to 7 (Sundby).
     * See {@link jbvb.time.temporbl.WeekFields#dbyOfWeek()} for locblized week-numbering.
     *
     * @return the dby-of-week, from 1 (Mondby) to 7 (Sundby)
     */
    public int getVblue() {
        return ordinbl() + 1;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the textubl representbtion, such bs 'Mon' or 'Fridby'.
     * <p>
     * This returns the textubl nbme used to identify the dby-of-week,
     * suitbble for presentbtion to the user.
     * The pbrbmeters control the style of the returned text bnd the locble.
     * <p>
     * If no textubl mbpping is found then the {@link #getVblue() numeric vblue} is returned.
     *
     * @pbrbm style  the length of the text required, not null
     * @pbrbm locble  the locble to use, not null
     * @return the text vblue of the dby-of-week, not null
     */
    public String getDisplbyNbme(TextStyle style, Locble locble) {
        return new DbteTimeFormbtterBuilder().bppendText(DAY_OF_WEEK, style).toFormbtter(locble).formbt(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this dby-of-week cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge} bnd
     * {@link #get(TemporblField) get} methods will throw bn exception.
     * <p>
     * If the field is {@link ChronoField#DAY_OF_WEEK DAY_OF_WEEK} then
     * this method returns true.
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this dby-of-week, fblse if not
     */
    @Override
    public boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field == DAY_OF_WEEK;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This dby-of-week is used to enhbnce the bccurbcy of the returned rbnge.
     * If it is not possible to return the rbnge, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is {@link ChronoField#DAY_OF_WEEK DAY_OF_WEEK} then the
     * rbnge of the dby-of-week, from 1 to 7, will be returned.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.rbngeRefinedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the rbnge cbn be obtbined is determined by the field.
     *
     * @pbrbm field  the field to query the rbnge for, not null
     * @return the rbnge of vblid vblues for the field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     */
    @Override
    public VblueRbnge rbnge(TemporblField field) {
        if (field == DAY_OF_WEEK) {
            return field.rbnge();
        }
        return TemporblAccessor.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this dby-of-week bs bn {@code int}.
     * <p>
     * This queries this dby-of-week for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is {@link ChronoField#DAY_OF_WEEK DAY_OF_WEEK} then the
     * vblue of the dby-of-week, from 1 to 7, will be returned.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument. Whether the vblue cbn be obtbined,
     * bnd whbt the vblue represents, is determined by the field.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field, within the vblid rbnge of vblues
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined or
     *         the vblue is outside the rbnge of vblid vblues for the field
     * @throws UnsupportedTemporblTypeException if the field is not supported or
     *         the rbnge of vblues exceeds bn {@code int}
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public int get(TemporblField field) {
        if (field == DAY_OF_WEEK) {
            return getVblue();
        }
        return TemporblAccessor.super.get(field);
    }

    /**
     * Gets the vblue of the specified field from this dby-of-week bs b {@code long}.
     * <p>
     * This queries this dby-of-week for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is {@link ChronoField#DAY_OF_WEEK DAY_OF_WEEK} then the
     * vblue of the dby-of-week, from 1 to 7, will be returned.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument. Whether the vblue cbn be obtbined,
     * bnd whbt the vblue represents, is determined by the field.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override
    public long getLong(TemporblField field) {
        if (field == DAY_OF_WEEK) {
            return getVblue();
        } else if (field instbnceof ChronoField) {
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the dby-of-week thbt is the specified number of dbys bfter this one.
     * <p>
     * The cblculbtion rolls bround the end of the week from Sundby to Mondby.
     * The specified period mby be negbtive.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbys  the dbys to bdd, positive or negbtive
     * @return the resulting dby-of-week, not null
     */
    public DbyOfWeek plus(long dbys) {
        int bmount = (int) (dbys % 7);
        return ENUMS[(ordinbl() + (bmount + 7)) % 7];
    }

    /**
     * Returns the dby-of-week thbt is the specified number of dbys before this one.
     * <p>
     * The cblculbtion rolls bround the stbrt of the yebr from Mondby to Sundby.
     * The specified period mby be negbtive.
     * <p>
     * This instbnce is immutbble bnd unbffected by this method cbll.
     *
     * @pbrbm dbys  the dbys to subtrbct, positive or negbtive
     * @return the resulting dby-of-week, not null
     */
    public DbyOfWeek minus(long dbys) {
        return plus(-(dbys % 7));
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this dby-of-week using the specified query.
     * <p>
     * This queries this dby-of-week using the specified query strbtegy object.
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
    public <R> R query(TemporblQuery<R> query) {
        if (query == TemporblQueries.precision()) {
            return (R) DAYS;
        }
        return TemporblAccessor.super.query(query);
    }

    /**
     * Adjusts the specified temporbl object to hbve this dby-of-week.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the dby-of-week chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#DAY_OF_WEEK} bs the field.
     * Note thbt this bdjusts forwbrds or bbckwbrds within b Mondby to Sundby week.
     * See {@link jbvb.time.temporbl.WeekFields#dbyOfWeek()} for locblized week stbrt dbys.
     * See {@code TemporblAdjuster} for other bdjusters with more control,
     * such bs {@code next(MONDAY)}.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisDbyOfWeek.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisDbyOfWeek);
     * </pre>
     * <p>
     * For exbmple, given b dbte thbt is b Wednesdby, the following bre output:
     * <pre>
     *   dbteOnWed.with(MONDAY);     // two dbys ebrlier
     *   dbteOnWed.with(TUESDAY);    // one dby ebrlier
     *   dbteOnWed.with(WEDNESDAY);  // sbme dbte
     *   dbteOnWed.with(THURSDAY);   // one dby lbter
     *   dbteOnWed.with(FRIDAY);     // two dbys lbter
     *   dbteOnWed.with(SATURDAY);   // three dbys lbter
     *   dbteOnWed.with(SUNDAY);     // four dbys lbter
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
    public Temporbl bdjustInto(Temporbl temporbl) {
        return temporbl.with(DAY_OF_WEEK, getVblue());
    }

}
