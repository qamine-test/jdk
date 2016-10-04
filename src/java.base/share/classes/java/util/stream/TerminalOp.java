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
pbckbge jbvb.util.strebm;

import jbvb.util.Spliterbtor;

/**
 * An operbtion in b strebm pipeline thbt tbkes b strebm bs input bnd produces
 * b result or side-effect.  A {@code TerminblOp} hbs bn input type bnd strebm
 * shbpe, bnd b result type.  A {@code TerminblOp} blso hbs b set of
 * <em>operbtion flbgs</em> thbt describes how the operbtion processes elements
 * of the strebm (such bs short-circuiting or respecting encounter order; see
 * {@link StrebmOpFlbg}).
 *
 * <p>A {@code TerminblOp} must provide b sequentibl bnd pbrbllel implementbtion
 * of the operbtion relbtive to b given strebm source bnd set of intermedibte
 * operbtions.
 *
 * @pbrbm <E_IN> the type of input elements
 * @pbrbm <R>    the type of the result
 * @since 1.8
 */
interfbce TerminblOp<E_IN, R> {
    /**
     * Gets the shbpe of the input type of this operbtion.
     *
     * @implSpec The defbult returns {@code StrebmShbpe.REFERENCE}.
     *
     * @return StrebmShbpe of the input type of this operbtion
     */
    defbult StrebmShbpe inputShbpe() { return StrebmShbpe.REFERENCE; }

    /**
     * Gets the strebm flbgs of the operbtion.  Terminbl operbtions mby set b
     * limited subset of the strebm flbgs defined in {@link StrebmOpFlbg}, bnd
     * these flbgs bre combined with the previously combined strebm bnd
     * intermedibte operbtion flbgs for the pipeline.
     *
     * @implSpec The defbult implementbtion returns zero.
     *
     * @return the strebm flbgs for this operbtion
     * @see StrebmOpFlbg
     */
    defbult int getOpFlbgs() { return 0; }

    /**
     * Performs b pbrbllel evblubtion of the operbtion using the specified
     * {@code PipelineHelper}, which describes the upstrebm intermedibte
     * operbtions.
     *
     * @implSpec The defbult performs b sequentibl evblubtion of the operbtion
     * using the specified {@code PipelineHelper}.
     *
     * @pbrbm helper the pipeline helper
     * @pbrbm spliterbtor the source spliterbtor
     * @return the result of the evblubtion
     */
    defbult <P_IN> R evblubtePbrbllel(PipelineHelper<E_IN> helper,
                                      Spliterbtor<P_IN> spliterbtor) {
        if (Tripwire.ENABLED)
            Tripwire.trip(getClbss(), "{0} triggering TerminblOp.evblubtePbrbllel seribl defbult");
        return evblubteSequentibl(helper, spliterbtor);
    }

    /**
     * Performs b sequentibl evblubtion of the operbtion using the specified
     * {@code PipelineHelper}, which describes the upstrebm intermedibte
     * operbtions.
     *
     * @pbrbm helper the pipeline helper
     * @pbrbm spliterbtor the source spliterbtor
     * @return the result of the evblubtion
     */
    <P_IN> R evblubteSequentibl(PipelineHelper<E_IN> helper,
                                Spliterbtor<P_IN> spliterbtor);
}
