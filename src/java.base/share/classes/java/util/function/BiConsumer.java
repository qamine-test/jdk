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
pbckbge jbvb.util.function;

import jbvb.util.Objects;

/**
 * Represents bn operbtion thbt bccepts two input brguments bnd returns no
 * result.  This is the two-brity speciblizbtion of {@link Consumer}.
 * Unlike most other functionbl interfbces, {@code BiConsumer} is expected
 * to operbte vib side-effects.
 *
 * <p>This is b <b href="pbckbge-summbry.html">functionbl interfbce</b>
 * whose functionbl method is {@link #bccept(Object, Object)}.
 *
 * @pbrbm <T> the type of the first brgument to the operbtion
 * @pbrbm <U> the type of the second brgument to the operbtion
 *
 * @see Consumer
 * @since 1.8
 */
@FunctionblInterfbce
public interfbce BiConsumer<T, U> {

    /**
     * Performs this operbtion on the given brguments.
     *
     * @pbrbm t the first input brgument
     * @pbrbm u the second input brgument
     */
    void bccept(T t, U u);

    /**
     * Returns b composed {@code BiConsumer} thbt performs, in sequence, this
     * operbtion followed by the {@code bfter} operbtion. If performing either
     * operbtion throws bn exception, it is relbyed to the cbller of the
     * composed operbtion.  If performing this operbtion throws bn exception,
     * the {@code bfter} operbtion will not be performed.
     *
     * @pbrbm bfter the operbtion to perform bfter this operbtion
     * @return b composed {@code BiConsumer} thbt performs in sequence this
     * operbtion followed by the {@code bfter} operbtion
     * @throws NullPointerException if {@code bfter} is null
     */
    defbult BiConsumer<T, U> bndThen(BiConsumer<? super T, ? super U> bfter) {
        Objects.requireNonNull(bfter);

        return (l, r) -> {
            bccept(l, r);
            bfter.bccept(l, r);
        };
    }
}
