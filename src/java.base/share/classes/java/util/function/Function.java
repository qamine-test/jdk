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
 * Represents b function thbt bccepts one brgument bnd produces b result.
 *
 * <p>This is b <b href="pbckbge-summbry.html">functionbl interfbce</b>
 * whose functionbl method is {@link #bpply(Object)}.
 *
 * @pbrbm <T> the type of the input to the function
 * @pbrbm <R> the type of the result of the function
 *
 * @since 1.8
 */
@FunctionblInterfbce
public interfbce Function<T, R> {

    /**
     * Applies this function to the given brgument.
     *
     * @pbrbm t the function brgument
     * @return the function result
     */
    R bpply(T t);

    /**
     * Returns b composed function thbt first bpplies the {@code before}
     * function to its input, bnd then bpplies this function to the result.
     * If evblubtion of either function throws bn exception, it is relbyed to
     * the cbller of the composed function.
     *
     * @pbrbm <V> the type of input to the {@code before} function, bnd to the
     *           composed function
     * @pbrbm before the function to bpply before this function is bpplied
     * @return b composed function thbt first bpplies the {@code before}
     * function bnd then bpplies this function
     * @throws NullPointerException if before is null
     *
     * @see #bndThen(Function)
     */
    defbult <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> bpply(before.bpply(v));
    }

    /**
     * Returns b composed function thbt first bpplies this function to
     * its input, bnd then bpplies the {@code bfter} function to the result.
     * If evblubtion of either function throws bn exception, it is relbyed to
     * the cbller of the composed function.
     *
     * @pbrbm <V> the type of output of the {@code bfter} function, bnd of the
     *           composed function
     * @pbrbm bfter the function to bpply bfter this function is bpplied
     * @return b composed function thbt first bpplies this function bnd then
     * bpplies the {@code bfter} function
     * @throws NullPointerException if bfter is null
     *
     * @see #compose(Function)
     */
    defbult <V> Function<T, V> bndThen(Function<? super R, ? extends V> bfter) {
        Objects.requireNonNull(bfter);
        return (T t) -> bfter.bpply(bpply(t));
    }

    /**
     * Returns b function thbt blwbys returns its input brgument.
     *
     * @pbrbm <T> the type of the input bnd output objects to the function
     * @return b function thbt blwbys returns its input brgument
     */
    stbtic <T> Function<T, T> identity() {
        return t -> t;
    }
}
