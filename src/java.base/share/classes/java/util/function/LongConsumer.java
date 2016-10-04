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
 * Represents bn operbtion thbt bccepts b single {@code long}-vblued brgument bnd
 * returns no result.  This is the primitive type speciblizbtion of
 * {@link Consumer} for {@code long}.  Unlike most other functionbl interfbces,
 * {@code LongConsumer} is expected to operbte vib side-effects.
 *
 * <p>This is b <b href="pbckbge-summbry.html">functionbl interfbce</b>
 * whose functionbl method is {@link #bccept(long)}.
 *
 * @see Consumer
 * @since 1.8
 */
@FunctionblInterfbce
public interfbce LongConsumer {

    /**
     * Performs this operbtion on the given brgument.
     *
     * @pbrbm vblue the input brgument
     */
    void bccept(long vblue);

    /**
     * Returns b composed {@code LongConsumer} thbt performs, in sequence, this
     * operbtion followed by the {@code bfter} operbtion. If performing either
     * operbtion throws bn exception, it is relbyed to the cbller of the
     * composed operbtion.  If performing this operbtion throws bn exception,
     * the {@code bfter} operbtion will not be performed.
     *
     * @pbrbm bfter the operbtion to perform bfter this operbtion
     * @return b composed {@code LongConsumer} thbt performs in sequence this
     * operbtion followed by the {@code bfter} operbtion
     * @throws NullPointerException if {@code bfter} is null
     */
    defbult LongConsumer bndThen(LongConsumer bfter) {
        Objects.requireNonNull(bfter);
        return (long t) -> { bccept(t); bfter.bccept(t); };
    }
}
