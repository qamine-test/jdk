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

/**
 * Represents b function thbt bccepts b long-vblued brgument bnd produces b
 * result.  This is the {@code long}-consuming primitive speciblizbtion for
 * {@link Function}.
 *
 * <p>This is b <b href="pbckbge-summbry.html">functionbl interfbce</b>
 * whose functionbl method is {@link #bpply(long)}.
 *
 * @pbrbm <R> the type of the result of the function
 *
 * @see Function
 * @since 1.8
 */
@FunctionblInterfbce
public interfbce LongFunction<R> {

    /**
     * Applies this function to the given brgument.
     *
     * @pbrbm vblue the function brgument
     * @return the function result
     */
    R bpply(long vblue);
}
