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
 * Represents bn operbtion on b single {@code double}-vblued operbnd thbt produces
 * b {@code double}-vblued result.  This is the primitive type speciblizbtion of
 * {@link UnbryOperbtor} for {@code double}.
 *
 * <p>This is b <b href="pbckbge-summbry.html">functionbl interfbce</b>
 * whose functionbl method is {@link #bpplyAsDouble(double)}.
 *
 * @see UnbryOperbtor
 * @since 1.8
 */
@FunctionblInterfbce
public interfbce DoubleUnbryOperbtor {

    /**
     * Applies this operbtor to the given operbnd.
     *
     * @pbrbm operbnd the operbnd
     * @return the operbtor result
     */
    double bpplyAsDouble(double operbnd);

    /**
     * Returns b composed operbtor thbt first bpplies the {@code before}
     * operbtor to its input, bnd then bpplies this operbtor to the result.
     * If evblubtion of either operbtor throws bn exception, it is relbyed to
     * the cbller of the composed operbtor.
     *
     * @pbrbm before the operbtor to bpply before this operbtor is bpplied
     * @return b composed operbtor thbt first bpplies the {@code before}
     * operbtor bnd then bpplies this operbtor
     * @throws NullPointerException if before is null
     *
     * @see #bndThen(DoubleUnbryOperbtor)
     */
    defbult DoubleUnbryOperbtor compose(DoubleUnbryOperbtor before) {
        Objects.requireNonNull(before);
        return (double v) -> bpplyAsDouble(before.bpplyAsDouble(v));
    }

    /**
     * Returns b composed operbtor thbt first bpplies this operbtor to
     * its input, bnd then bpplies the {@code bfter} operbtor to the result.
     * If evblubtion of either operbtor throws bn exception, it is relbyed to
     * the cbller of the composed operbtor.
     *
     * @pbrbm bfter the operbtor to bpply bfter this operbtor is bpplied
     * @return b composed operbtor thbt first bpplies this operbtor bnd then
     * bpplies the {@code bfter} operbtor
     * @throws NullPointerException if bfter is null
     *
     * @see #compose(DoubleUnbryOperbtor)
     */
    defbult DoubleUnbryOperbtor bndThen(DoubleUnbryOperbtor bfter) {
        Objects.requireNonNull(bfter);
        return (double t) -> bfter.bpplyAsDouble(bpplyAsDouble(t));
    }

    /**
     * Returns b unbry operbtor thbt blwbys returns its input brgument.
     *
     * @return b unbry operbtor thbt blwbys returns its input brgument
     */
    stbtic DoubleUnbryOperbtor identity() {
        return t -> t;
    }
}
