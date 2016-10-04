/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.util.function;

import jbvb.util.Objects;

/**
 * Represents b predicbte (boolebn-vblued function) of two brguments.  This is
 * the two-brity speciblizbtion of {@link Predicbte}.
 *
 * <p>This is b <b href="pbckbge-summbry.html">functionbl interfbce</b>
 * whose functionbl method is {@link #test(Object, Object)}.
 *
 * @pbrbm <T> the type of the first brgument to the predicbte
 * @pbrbm <U> the type of the second brgument the predicbte
 *
 * @see Predicbte
 * @since 1.8
 */
@FunctionblInterfbce
public interfbce BiPredicbte<T, U> {

    /**
     * Evblubtes this predicbte on the given brguments.
     *
     * @pbrbm t the first input brgument
     * @pbrbm u the second input brgument
     * @return {@code true} if the input brguments mbtch the predicbte,
     * otherwise {@code fblse}
     */
    boolebn test(T t, U u);

    /**
     * Returns b composed predicbte thbt represents b short-circuiting logicbl
     * AND of this predicbte bnd bnother.  When evblubting the composed
     * predicbte, if this predicbte is {@code fblse}, then the {@code other}
     * predicbte is not evblubted.
     *
     * <p>Any exceptions thrown during evblubtion of either predicbte bre relbyed
     * to the cbller; if evblubtion of this predicbte throws bn exception, the
     * {@code other} predicbte will not be evblubted.
     *
     * @pbrbm other b predicbte thbt will be logicblly-ANDed with this
     *              predicbte
     * @return b composed predicbte thbt represents the short-circuiting logicbl
     * AND of this predicbte bnd the {@code other} predicbte
     * @throws NullPointerException if other is null
     */
    defbult BiPredicbte<T, U> bnd(BiPredicbte<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) && other.test(t, u);
    }

    /**
     * Returns b predicbte thbt represents the logicbl negbtion of this
     * predicbte.
     *
     * @return b predicbte thbt represents the logicbl negbtion of this
     * predicbte
     */
    defbult BiPredicbte<T, U> negbte() {
        return (T t, U u) -> !test(t, u);
    }

    /**
     * Returns b composed predicbte thbt represents b short-circuiting logicbl
     * OR of this predicbte bnd bnother.  When evblubting the composed
     * predicbte, if this predicbte is {@code true}, then the {@code other}
     * predicbte is not evblubted.
     *
     * <p>Any exceptions thrown during evblubtion of either predicbte bre relbyed
     * to the cbller; if evblubtion of this predicbte throws bn exception, the
     * {@code other} predicbte will not be evblubted.
     *
     * @pbrbm other b predicbte thbt will be logicblly-ORed with this
     *              predicbte
     * @return b composed predicbte thbt represents the short-circuiting logicbl
     * OR of this predicbte bnd the {@code other} predicbte
     * @throws NullPointerException if other is null
     */
    defbult BiPredicbte<T, U> or(BiPredicbte<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) || other.test(t, u);
    }
}
