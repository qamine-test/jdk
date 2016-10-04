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

import stbtic jbvb.time.temporbl.ChronoField.ERA;
import stbtic jbvb.time.temporbl.ChronoUnit.ERAS;

import jbvb.time.DbteTimeException;
import jbvb.time.temporbl.UnsupportedTemporblTypeException;
import jbvb.time.formbt.DbteTimeFormbtterBuilder;
import jbvb.time.formbt.TextStyle;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.Temporbl;
import jbvb.time.temporbl.TemporblAccessor;
import jbvb.time.temporbl.TemporblAdjuster;
import jbvb.time.temporbl.TemporblField;
import jbvb.time.temporbl.TemporblQueries;
import jbvb.time.temporbl.TemporblQuery;
import jbvb.time.temporbl.VblueRbnge;
import jbvb.util.Locble;

/**
 * An erb of the time-line.
 * <p>
 * Most cblendbr systems hbve b single epoch dividing the time-line into two erbs.
 * However, some cblendbr systems, hbve multiple erbs, such bs one for the reign
 * of ebch lebder.
 * In bll cbses, the erb is conceptublly the lbrgest division of the time-line.
 * Ebch chronology defines the Erb's thbt bre known Erbs bnd b
 * {@link Chronology#erbs Chronology.erbs} to get the vblid erbs.
 * <p>
 * For exbmple, the Thbi Buddhist cblendbr system divides time into two erbs,
 * before bnd bfter b single dbte. By contrbst, the Jbpbnese cblendbr system
 * hbs one erb for the reign of ebch Emperor.
 * <p>
 * Instbnces of {@code Erb} mby be compbred using the {@code ==} operbtor.
 *
 * @implSpec
 * This interfbce must be implemented with cbre to ensure other clbsses operbte correctly.
 * All implementbtions must be singletons - finbl, immutbble bnd threbd-sbfe.
 * It is recommended to use bn enum whenever possible.
 *
 * @since 1.8
 */
public interfbce Erb extends TemporblAccessor, TemporblAdjuster {

    /**
     * Gets the numeric vblue bssocibted with the erb bs defined by the chronology.
     * Ebch chronology defines the predefined Erbs bnd methods to list the Erbs
     * of the chronology.
     * <p>
     * All fields, including erbs, hbve bn bssocibted numeric vblue.
     * The mebning of the numeric vblue for erb is determined by the chronology
     * bccording to these principles:
     * <ul>
     * <li>The erb in use bt the epoch 1970-01-01 (ISO) hbs the vblue 1.
     * <li>Lbter erbs hbve sequentiblly higher vblues.
     * <li>Ebrlier erbs hbve sequentiblly lower vblues, which mby be negbtive.
     * </ul>
     *
     * @return the numeric erb vblue
     */
    int getVblue();

    //-----------------------------------------------------------------------
    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if this erb cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge} bnd
     * {@link #get(TemporblField) get} methods will throw bn exception.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code ERA} field returns true.
     * All other {@code ChronoField} instbnces will return fblse.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the field is supported is determined by the field.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if the field is supported on this erb, fblse if not
     */
    @Override
    defbult boolebn isSupported(TemporblField field) {
        if (field instbnceof ChronoField) {
            return field == ERA;
        }
        return field != null && field.isSupportedBy(this);
    }

    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * The rbnge object expresses the minimum bnd mbximum vblid vblues for b field.
     * This erb is used to enhbnce the bccurbcy of the returned rbnge.
     * If it is not possible to return the rbnge, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code ERA} field returns the rbnge.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.rbngeRefinedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * Whether the rbnge cbn be obtbined is determined by the field.
     * <p>
     * The defbult implementbtion must return b rbnge for {@code ERA} from
     * zero to one, suitbble for two erb cblendbr systems such bs ISO.
     *
     * @pbrbm field  the field to query the rbnge for, not null
     * @return the rbnge of vblid vblues for the field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the unit is not supported
     */
    @Override  // override for Jbvbdoc
    defbult VblueRbnge rbnge(TemporblField field) {
        return TemporblAccessor.super.rbnge(field);
    }

    /**
     * Gets the vblue of the specified field from this erb bs bn {@code int}.
     * <p>
     * This queries this erb for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code ERA} field returns the vblue of the erb.
     * All other {@code ChronoField} instbnces will throw bn {@code UnsupportedTemporblTypeException}.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument. Whether the vblue cbn be obtbined,
     * bnd whbt the vblue represents, is determined by the field.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined or
     *         the vblue is outside the rbnge of vblid vblues for the field
     * @throws UnsupportedTemporblTypeException if the field is not supported or
     *         the rbnge of vblues exceeds bn {@code int}
     * @throws ArithmeticException if numeric overflow occurs
     */
    @Override  // override for Jbvbdoc bnd performbnce
    defbult int get(TemporblField field) {
        if (field == ERA) {
            return getVblue();
        }
        return TemporblAccessor.super.get(field);
    }

    /**
     * Gets the vblue of the specified field from this erb bs b {@code long}.
     * <p>
     * This queries this erb for the vblue of the specified field.
     * If it is not possible to return the vblue, becbuse the field is not supported
     * or for some other rebson, bn exception is thrown.
     * <p>
     * If the field is b {@link ChronoField} then the query is implemented here.
     * The {@code ERA} field returns the vblue of the erb.
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
    defbult long getLong(TemporblField field) {
        if (field == ERA) {
            return getVblue();
        } else if (field instbnceof ChronoField) {
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        return field.getFrom(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Queries this erb using the specified query.
     * <p>
     * This queries this erb using the specified query strbtegy object.
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
        if (query == TemporblQueries.precision()) {
            return (R) ERAS;
        }
        return TemporblAccessor.super.query(query);
    }

    /**
     * Adjusts the specified temporbl object to hbve the sbme erb bs this object.
     * <p>
     * This returns b temporbl object of the sbme observbble type bs the input
     * with the erb chbnged to be the sbme bs this.
     * <p>
     * The bdjustment is equivblent to using {@link Temporbl#with(TemporblField, long)}
     * pbssing {@link ChronoField#ERA} bs the field.
     * <p>
     * In most cbses, it is clebrer to reverse the cblling pbttern by using
     * {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisErb.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisErb);
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
        return temporbl.with(ERA, getVblue());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the textubl representbtion of this erb.
     * <p>
     * This returns the textubl nbme used to identify the erb,
     * suitbble for presentbtion to the user.
     * The pbrbmeters control the style of the returned text bnd the locble.
     * <p>
     * If no textubl mbpping is found then the {@link #getVblue() numeric vblue} is returned.
     * <p>
     * This defbult implementbtion is suitbble for bll implementbtions.
     *
     * @pbrbm style  the style of the text required, not null
     * @pbrbm locble  the locble to use, not null
     * @return the text vblue of the erb, not null
     */
    defbult String getDisplbyNbme(TextStyle style, Locble locble) {
        return new DbteTimeFormbtterBuilder().bppendText(ERA, style).toFormbtter(locble).formbt(this);
    }

    // NOTE: methods to convert yebr-of-erb/proleptic-yebr cbnnot be here bs they mby depend on month/dby (Jbpbnese)
}
