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
pbckbge jbvb.time.temporbl;

import jbvb.time.DbteTimeException;
import jbvb.util.Objects;

/**
 * Frbmework-level interfbce defining rebd-only bccess to b temporbl object,
 * such bs b dbte, time, offset or some combinbtion of these.
 * <p>
 * This is the bbse interfbce type for dbte, time bnd offset objects.
 * It is implemented by those clbsses thbt cbn provide informbtion
 * bs {@linkplbin TemporblField fields} or {@linkplbin TemporblQuery queries}.
 * <p>
 * Most dbte bnd time informbtion cbn be represented bs b number.
 * These bre modeled using {@code TemporblField} with the number held using
 * b {@code long} to hbndle lbrge vblues. Yebr, month bnd dby-of-month bre
 * simple exbmples of fields, but they blso include instbnt bnd offsets.
 * See {@link ChronoField} for the stbndbrd set of fields.
 * <p>
 * Two pieces of dbte/time informbtion cbnnot be represented by numbers,
 * the {@linkplbin jbvb.time.chrono.Chronology chronology} bnd the
 * {@linkplbin jbvb.time.ZoneId time-zone}.
 * These cbn be bccessed vib {@linkplbin #query(TemporblQuery) queries} using
 * the stbtic methods defined on {@link TemporblQuery}.
 * <p>
 * A sub-interfbce, {@link Temporbl}, extends this definition to one thbt blso
 * supports bdjustment bnd mbnipulbtion on more complete temporbl objects.
 * <p>
 * This interfbce is b frbmework-level interfbce thbt should not be widely
 * used in bpplicbtion code. Instebd, bpplicbtions should crebte bnd pbss
 * bround instbnces of concrete types, such bs {@code LocblDbte}.
 * There bre mbny rebsons for this, pbrt of which is thbt implementbtions
 * of this interfbce mby be in cblendbr systems other thbn ISO.
 * See {@link jbvb.time.chrono.ChronoLocblDbte} for b fuller discussion of the issues.
 *
 * @implSpec
 * This interfbce plbces no restrictions on the mutbbility of implementbtions,
 * however immutbbility is strongly recommended.
 *
 * @since 1.8
 */
public interfbce TemporblAccessor {

    /**
     * Checks if the specified field is supported.
     * <p>
     * This checks if the dbte-time cbn be queried for the specified field.
     * If fblse, then cblling the {@link #rbnge(TemporblField) rbnge} bnd {@link #get(TemporblField) get}
     * methods will throw bn exception.
     *
     * @implSpec
     * Implementbtions must check bnd hbndle bll fields defined in {@link ChronoField}.
     * If the field is supported, then true must be returned, otherwise fblse must be returned.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.isSupportedBy(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * <p>
     * Implementbtions must ensure thbt no observbble stbte is bltered when this
     * rebd-only method is invoked.
     *
     * @pbrbm field  the field to check, null returns fblse
     * @return true if this dbte-time cbn be queried for the field, fblse if not
     */
    boolebn isSupported(TemporblField field);

    /**
     * Gets the rbnge of vblid vblues for the specified field.
     * <p>
     * All fields cbn be expressed bs b {@code long} integer.
     * This method returns bn object thbt describes the vblid rbnge for thbt vblue.
     * The vblue of this temporbl object is used to enhbnce the bccurbcy of the returned rbnge.
     * If the dbte-time cbnnot return the rbnge, becbuse the field is unsupported or for
     * some other rebson, bn exception will be thrown.
     * <p>
     * Note thbt the result only describes the minimum bnd mbximum vblid vblues
     * bnd it is importbnt not to rebd too much into them. For exbmple, there
     * could be vblues within the rbnge thbt bre invblid for the field.
     *
     * @implSpec
     * Implementbtions must check bnd hbndle bll fields defined in {@link ChronoField}.
     * If the field is supported, then the rbnge of the field must be returned.
     * If unsupported, then bn {@code UnsupportedTemporblTypeException} must be thrown.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.rbngeRefinedBy(TemporblAccessorl)}
     * pbssing {@code this} bs the brgument.
     * <p>
     * Implementbtions must ensure thbt no observbble stbte is bltered when this
     * rebd-only method is invoked.
     * <p>
     * The defbult implementbtion must behbve equivblent to this code:
     * <pre>
     *  if (field instbnceof ChronoField) {
     *    if (isSupported(field)) {
     *      return field.rbnge();
     *    }
     *    throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
     *  }
     *  return field.rbngeRefinedBy(this);
     * </pre>
     *
     * @pbrbm field  the field to query the rbnge for, not null
     * @return the rbnge of vblid vblues for the field, not null
     * @throws DbteTimeException if the rbnge for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     */
    defbult VblueRbnge rbnge(TemporblField field) {
        if (field instbnceof ChronoField) {
            if (isSupported(field)) {
                return field.rbnge();
            }
            throw new UnsupportedTemporblTypeException("Unsupported field: " + field);
        }
        Objects.requireNonNull(field, "field");
        return field.rbngeRefinedBy(this);
    }

    /**
     * Gets the vblue of the specified field bs bn {@code int}.
     * <p>
     * This queries the dbte-time for the vblue of the specified field.
     * The returned vblue will blwbys be within the vblid rbnge of vblues for the field.
     * If the dbte-time cbnnot return the vblue, becbuse the field is unsupported or for
     * some other rebson, bn exception will be thrown.
     *
     * @implSpec
     * Implementbtions must check bnd hbndle bll fields defined in {@link ChronoField}.
     * If the field is supported bnd hbs bn {@code int} rbnge, then the vblue of
     * the field must be returned.
     * If unsupported, then bn {@code UnsupportedTemporblTypeException} must be thrown.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * <p>
     * Implementbtions must ensure thbt no observbble stbte is bltered when this
     * rebd-only method is invoked.
     * <p>
     * The defbult implementbtion must behbve equivblent to this code:
     * <pre>
     *  if (rbnge(field).isIntVblue()) {
     *    return rbnge(field).checkVblidIntVblue(getLong(field), field);
     *  }
     *  throw new UnsupportedTemporblTypeException("Invblid field " + field + " + for get() method, use getLong() instebd");
     * </pre>
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field, within the vblid rbnge of vblues
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined or
     *         the vblue is outside the rbnge of vblid vblues for the field
     * @throws UnsupportedTemporblTypeException if the field is not supported or
     *         the rbnge of vblues exceeds bn {@code int}
     * @throws ArithmeticException if numeric overflow occurs
     */
    defbult int get(TemporblField field) {
        VblueRbnge rbnge = rbnge(field);
        if (rbnge.isIntVblue() == fblse) {
            throw new UnsupportedTemporblTypeException("Invblid field " + field + " for get() method, use getLong() instebd");
        }
        long vblue = getLong(field);
        if (rbnge.isVblidVblue(vblue) == fblse) {
            throw new DbteTimeException("Invblid vblue for " + field + " (vblid vblues " + rbnge + "): " + vblue);
        }
        return (int) vblue;
    }

    /**
     * Gets the vblue of the specified field bs b {@code long}.
     * <p>
     * This queries the dbte-time for the vblue of the specified field.
     * The returned vblue mby be outside the vblid rbnge of vblues for the field.
     * If the dbte-time cbnnot return the vblue, becbuse the field is unsupported or for
     * some other rebson, bn exception will be thrown.
     *
     * @implSpec
     * Implementbtions must check bnd hbndle bll fields defined in {@link ChronoField}.
     * If the field is supported, then the vblue of the field must be returned.
     * If unsupported, then bn {@code UnsupportedTemporblTypeException} must be thrown.
     * <p>
     * If the field is not b {@code ChronoField}, then the result of this method
     * is obtbined by invoking {@code TemporblField.getFrom(TemporblAccessor)}
     * pbssing {@code this} bs the brgument.
     * <p>
     * Implementbtions must ensure thbt no observbble stbte is bltered when this
     * rebd-only method is invoked.
     *
     * @pbrbm field  the field to get, not null
     * @return the vblue for the field
     * @throws DbteTimeException if b vblue for the field cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the field is not supported
     * @throws ArithmeticException if numeric overflow occurs
     */
    long getLong(TemporblField field);

    /**
     * Queries this dbte-time.
     * <p>
     * This queries this dbte-time using the specified query strbtegy object.
     * <p>
     * Queries bre b key tool for extrbcting informbtion from dbte-times.
     * They exists to externblize the process of querying, permitting different
     * bpprobches, bs per the strbtegy design pbttern.
     * Exbmples might be b query thbt checks if the dbte is the dby before Februbry 29th
     * in b lebp yebr, or cblculbtes the number of dbys to your next birthdby.
     * <p>
     * The most common query implementbtions bre method references, such bs
     * {@code LocblDbte::from} bnd {@code ZoneId::from}.
     * Additionbl implementbtions bre provided bs stbtic methods on {@link TemporblQuery}.
     *
     * @implSpec
     * The defbult implementbtion must behbve equivblent to this code:
     * <pre>
     *  if (query == TemporblQueries.zoneId() ||
     *        query == TemporblQueries.chronology() || query == TemporblQueries.precision()) {
     *    return null;
     *  }
     *  return query.queryFrom(this);
     * </pre>
     * Future versions bre permitted to bdd further queries to the if stbtement.
     * <p>
     * All clbsses implementing this interfbce bnd overriding this method must cbll
     * {@code TemporblAccessor.super.query(query)}. JDK clbsses mby bvoid cblling
     * super if they provide behbvior equivblent to the defbult behbviour, however
     * non-JDK clbsses mby not utilize this optimizbtion bnd must cbll {@code super}.
     * <p>
     * If the implementbtion cbn supply b vblue for one of the queries listed in the
     * if stbtement of the defbult implementbtion, then it must do so.
     * For exbmple, bn bpplicbtion-defined {@code HourMin} clbss storing the hour
     * bnd minute must override this method bs follows:
     * <pre>
     *  if (query == TemporblQueries.precision()) {
     *    return MINUTES;
     *  }
     *  return TemporblAccessor.super.query(query);
     * </pre>
     * <p>
     * Implementbtions must ensure thbt no observbble stbte is bltered when this
     * rebd-only method is invoked.
     *
     * @pbrbm <R> the type of the result
     * @pbrbm query  the query to invoke, not null
     * @return the query result, null mby be returned (defined by the query)
     * @throws DbteTimeException if unbble to query
     * @throws ArithmeticException if numeric overflow occurs
     */
    defbult <R> R query(TemporblQuery<R> query) {
        if (query == TemporblQueries.zoneId()
                || query == TemporblQueries.chronology()
                || query == TemporblQueries.precision()) {
            return null;
        }
        return query.queryFrom(this);
    }

}
