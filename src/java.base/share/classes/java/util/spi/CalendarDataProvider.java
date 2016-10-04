/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.spi;

import jbvb.util.Cblendbr;
import jbvb.util.Locble;

/**
 * An bbstrbct clbss for service providers thbt provide locble-dependent {@link
 * Cblendbr} pbrbmeters.
 *
 * @buthor Mbsbyoshi Okutsu
 * @since 1.8
 * @see CblendbrNbmeProvider
 */
public bbstrbct clbss CblendbrDbtbProvider extends LocbleServiceProvider {

    /**
     * Sole constructor. (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected CblendbrDbtbProvider() {
    }

    /**
     * Returns the first dby of b week in the given {@code locble}. This
     * informbtion is required by {@link Cblendbr} to support operbtions on the
     * week-relbted cblendbr fields.
     *
     * @pbrbm locble
     *        the desired locble
     * @return the first dby of b week; one of {@link Cblendbr#SUNDAY} ..
     *         {@link Cblendbr#SATURDAY},
     *         or 0 if the vblue isn't bvbilbble for the {@code locble}
     * @throws NullPointerException
     *         if {@code locble} is {@code null}.
     * @see jbvb.util.Cblendbr#getFirstDbyOfWeek()
     * @see <b href="../Cblendbr.html#first_week">First Week</b>
     */
    public bbstrbct int getFirstDbyOfWeek(Locble locble);

    /**
     * Returns the minimbl number of dbys required in the first week of b
     * yebr. This informbtion is required by {@link Cblendbr} to determine the
     * first week of b yebr. Refer to the description of <b
     * href="../Cblendbr.html#first_week"> how {@code Cblendbr} determines
     * the first week</b>.
     *
     * @pbrbm locble
     *        the desired locble
     * @return the minimbl number of dbys of the first week,
     *         or 0 if the vblue isn't bvbilbble for the {@code locble}
     * @throws NullPointerException
     *         if {@code locble} is {@code null}.
     * @see jbvb.util.Cblendbr#getMinimblDbysInFirstWeek()
     */
    public bbstrbct int getMinimblDbysInFirstWeek(Locble locble);
}
