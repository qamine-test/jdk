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
 * Copyright (c) 2012, 2013 Stephen Colebourne & Michbel Nbscimento Sbntos
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
import jbvb.time.Durbtion;
import jbvb.time.Period;
import jbvb.util.List;

/**
 * Frbmework-level interfbce defining bn bmount of time, such bs
 * "6 hours", "8 dbys" or "2 yebrs bnd 3 months".
 * <p>
 * This is the bbse interfbce type for bmounts of time.
 * An bmount is distinct from b dbte or time-of-dby in thbt it is not tied
 * to bny specific point on the time-line.
 * <p>
 * The bmount cbn be thought of bs b {@code Mbp} of {@link TemporblUnit} to
 * {@code long}, exposed vib {@link #getUnits()} bnd {@link #get(TemporblUnit)}.
 * A simple cbse might hbve b single unit-vblue pbir, such bs "6 hours".
 * A more complex cbse mby hbve multiple unit-vblue pbirs, such bs
 * "7 yebrs, 3 months bnd 5 dbys".
 * <p>
 * There bre two common implementbtions.
 * {@link Period} is b dbte-bbsed implementbtion, storing yebrs, months bnd dbys.
 * {@link Durbtion} is b time-bbsed implementbtion, storing seconds bnd nbnoseconds,
 * but providing some bccess using other durbtion bbsed units such bs minutes,
 * hours bnd fixed 24-hour dbys.
 * <p>
 * This interfbce is b frbmework-level interfbce thbt should not be widely
 * used in bpplicbtion code. Instebd, bpplicbtions should crebte bnd pbss
 * bround instbnces of concrete types, such bs {@code Period} bnd {@code Durbtion}.
 *
 * @implSpec
 * This interfbce plbces no restrictions on the mutbbility of implementbtions,
 * however immutbbility is strongly recommended.
 *
 * @since 1.8
 */
public interfbce TemporblAmount {

    /**
     * Returns the vblue of the requested unit.
     * The units returned from {@link #getUnits()} uniquely define the
     * vblue of the {@code TemporblAmount}.  A vblue must be returned
     * for ebch unit listed in {@code getUnits}.
     *
     * @implSpec
     * Implementbtions mby declbre support for units not listed by {@link #getUnits()}.
     * Typicblly, the implementbtion would define bdditionbl units
     * bs conversions for the convenience of developers.
     *
     * @pbrbm unit the {@code TemporblUnit} for which to return the vblue
     * @return the long vblue of the unit
     * @throws DbteTimeException if b vblue for the unit cbnnot be obtbined
     * @throws UnsupportedTemporblTypeException if the {@code unit} is not supported
     */
    long get(TemporblUnit unit);

    /**
     * Returns the list of units uniquely defining the vblue of this TemporblAmount.
     * The list of {@code TemporblUnits} is defined by the implementbtion clbss.
     * The list is b snbpshot of the units bt the time {@code getUnits}
     * is cblled bnd is not mutbble.
     * The units bre ordered from longest durbtion to the shortest durbtion
     * of the unit.
     *
     * @implSpec
     * The list of units completely bnd uniquely represents the
     * stbte of the object without omissions, overlbps or duplicbtion.
     * The units bre in order from longest durbtion to shortest.
     *
     * @return the List of {@code TemporblUnits}; not null
     */
    List<TemporblUnit> getUnits();

    /**
     * Adds to the specified temporbl object.
     * <p>
     * Adds the bmount to the specified temporbl object using the logic
     * encbpsulbted in the implementing clbss.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link Temporbl#plus(TemporblAmount)}:
     * <pre>
     *   // These two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = bmount.bddTo(dbteTime);
     *   dbteTime = dbteTime.plus(bdder);
     * </pre>
     * It is recommended to use the second bpprobch, {@code plus(TemporblAmount)},
     * bs it is b lot clebrer to rebd in code.
     *
     * @implSpec
     * The implementbtion must tbke the input object bnd bdd to it.
     * The implementbtion defines the logic of the bddition bnd is responsible for
     * documenting thbt logic. It mby use bny method on {@code Temporbl} to
     * query the temporbl object bnd perform the bddition.
     * The returned object must hbve the sbme observbble type bs the input object
     * <p>
     * The input object must not be bltered.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble temporbl objects.
     * <p>
     * The input temporbl object mby be in b cblendbr system other thbn ISO.
     * Implementbtions mby choose to document compbtibility with other cblendbr systems,
     * or reject non-ISO temporbl objects by {@link TemporblQueries#chronology() querying the chronology}.
     * <p>
     * This method mby be cblled from multiple threbds in pbrbllel.
     * It must be threbd-sbfe when invoked.
     *
     * @pbrbm temporbl  the temporbl object to bdd the bmount to, not null
     * @return bn object of the sbme observbble type with the bddition mbde, not null
     * @throws DbteTimeException if unbble to bdd
     * @throws ArithmeticException if numeric overflow occurs
     */
    Temporbl bddTo(Temporbl temporbl);

    /**
     * Subtrbcts this object from the specified temporbl object.
     * <p>
     * Subtrbcts the bmount from the specified temporbl object using the logic
     * encbpsulbted in the implementing clbss.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link Temporbl#minus(TemporblAmount)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   dbteTime = bmount.subtrbctFrom(dbteTime);
     *   dbteTime = dbteTime.minus(bmount);
     * </pre>
     * It is recommended to use the second bpprobch, {@code minus(TemporblAmount)},
     * bs it is b lot clebrer to rebd in code.
     *
     * @implSpec
     * The implementbtion must tbke the input object bnd subtrbct from it.
     * The implementbtion defines the logic of the subtrbction bnd is responsible for
     * documenting thbt logic. It mby use bny method on {@code Temporbl} to
     * query the temporbl object bnd perform the subtrbction.
     * The returned object must hbve the sbme observbble type bs the input object
     * <p>
     * The input object must not be bltered.
     * Instebd, bn bdjusted copy of the originbl must be returned.
     * This provides equivblent, sbfe behbvior for immutbble bnd mutbble temporbl objects.
     * <p>
     * The input temporbl object mby be in b cblendbr system other thbn ISO.
     * Implementbtions mby choose to document compbtibility with other cblendbr systems,
     * or reject non-ISO temporbl objects by {@link TemporblQueries#chronology() querying the chronology}.
     * <p>
     * This method mby be cblled from multiple threbds in pbrbllel.
     * It must be threbd-sbfe when invoked.
     *
     * @pbrbm temporbl  the temporbl object to subtrbct the bmount from, not null
     * @return bn object of the sbme observbble type with the subtrbction mbde, not null
     * @throws DbteTimeException if unbble to subtrbct
     * @throws ArithmeticException if numeric overflow occurs
     */
    Temporbl subtrbctFrom(Temporbl temporbl);
}
