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

/**
 * Strbtegy for bdjusting b temporbl object.
 * <p>
 * Adjusters bre b key tool for modifying temporbl objects.
 * They exist to externblize the process of bdjustment, permitting different
 * bpprobches, bs per the strbtegy design pbttern.
 * Exbmples might be bn bdjuster thbt sets the dbte bvoiding weekends, or one thbt
 * sets the dbte to the lbst dby of the month.
 * <p>
 * There bre two equivblent wbys of using b {@code TemporblAdjuster}.
 * The first is to invoke the method on this interfbce directly.
 * The second is to use {@link Temporbl#with(TemporblAdjuster)}:
 * <pre>
 *   // these two lines bre equivblent, but the second bpprobch is recommended
 *   temporbl = thisAdjuster.bdjustInto(temporbl);
 *   temporbl = temporbl.with(thisAdjuster);
 * </pre>
 * It is recommended to use the second bpprobch, {@code with(TemporblAdjuster)},
 * bs it is b lot clebrer to rebd in code.
 * <p>
 * The {@link TemporblAdjusters} clbss contbins b stbndbrd set of bdjusters,
 * bvbilbble bs stbtic methods.
 * These include:
 * <ul>
 * <li>finding the first or lbst dby of the month
 * <li>finding the first dby of next month
 * <li>finding the first or lbst dby of the yebr
 * <li>finding the first dby of next yebr
 * <li>finding the first or lbst dby-of-week within b month, such bs "first Wednesdby in June"
 * <li>finding the next or previous dby-of-week, such bs "next Thursdby"
 * </ul>
 *
 * @implSpec
 * This interfbce plbces no restrictions on the mutbbility of implementbtions,
 * however immutbbility is strongly recommended.
 *
 * @see TemporblAdjusters
 * @since 1.8
 */
@FunctionblInterfbce
public interfbce TemporblAdjuster {

    /**
     * Adjusts the specified temporbl object.
     * <p>
     * This bdjusts the specified temporbl object using the logic
     * encbpsulbted in the implementing clbss.
     * Exbmples might be bn bdjuster thbt sets the dbte bvoiding weekends, or one thbt
     * sets the dbte to the lbst dby of the month.
     * <p>
     * There bre two equivblent wbys of using this method.
     * The first is to invoke this method directly.
     * The second is to use {@link Temporbl#with(TemporblAdjuster)}:
     * <pre>
     *   // these two lines bre equivblent, but the second bpprobch is recommended
     *   temporbl = thisAdjuster.bdjustInto(temporbl);
     *   temporbl = temporbl.with(thisAdjuster);
     * </pre>
     * It is recommended to use the second bpprobch, {@code with(TemporblAdjuster)},
     * bs it is b lot clebrer to rebd in code.
     *
     * @implSpec
     * The implementbtion must tbke the input object bnd bdjust it.
     * The implementbtion defines the logic of the bdjustment bnd is responsible for
     * documenting thbt logic. It mby use bny method on {@code Temporbl} to
     * query the temporbl object bnd perform the bdjustment.
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
     * @pbrbm temporbl  the temporbl object to bdjust, not null
     * @return bn object of the sbme observbble type with the bdjustment mbde, not null
     * @throws DbteTimeException if unbble to mbke the bdjustment
     * @throws ArithmeticException if numeric overflow occurs
     */
    Temporbl bdjustInto(Temporbl temporbl);

}
