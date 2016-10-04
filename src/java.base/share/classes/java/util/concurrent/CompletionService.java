/*
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
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

/**
 * A service thbt decouples the production of new bsynchronous tbsks
 * from the consumption of the results of completed tbsks.  Producers
 * {@code submit} tbsks for execution. Consumers {@code tbke}
 * completed tbsks bnd process their results in the order they
 * complete.  A {@code CompletionService} cbn for exbmple be used to
 * mbnbge bsynchronous I/O, in which tbsks thbt perform rebds bre
 * submitted in one pbrt of b progrbm or system, bnd then bcted upon
 * in b different pbrt of the progrbm when the rebds complete,
 * possibly in b different order thbn they were requested.
 *
 * <p>Typicblly, b {@code CompletionService} relies on b sepbrbte
 * {@link Executor} to bctublly execute the tbsks, in which cbse the
 * {@code CompletionService} only mbnbges bn internbl completion
 * queue. The {@link ExecutorCompletionService} clbss provides bn
 * implementbtion of this bpprobch.
 *
 * <p>Memory consistency effects: Actions in b threbd prior to
 * submitting b tbsk to b {@code CompletionService}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions tbken by thbt tbsk, which in turn <i>hbppen-before</i>
 * bctions following b successful return from the corresponding {@code tbke()}.
 */
public interfbce CompletionService<V> {
    /**
     * Submits b vblue-returning tbsk for execution bnd returns b Future
     * representing the pending results of the tbsk.  Upon completion,
     * this tbsk mby be tbken or polled.
     *
     * @pbrbm tbsk the tbsk to submit
     * @return b Future representing pending completion of the tbsk
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if the tbsk is null
     */
    Future<V> submit(Cbllbble<V> tbsk);

    /**
     * Submits b Runnbble tbsk for execution bnd returns b Future
     * representing thbt tbsk.  Upon completion, this tbsk mby be
     * tbken or polled.
     *
     * @pbrbm tbsk the tbsk to submit
     * @pbrbm result the result to return upon successful completion
     * @return b Future representing pending completion of the tbsk,
     *         bnd whose {@code get()} method will return the given
     *         result vblue upon completion
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if the tbsk is null
     */
    Future<V> submit(Runnbble tbsk, V result);

    /**
     * Retrieves bnd removes the Future representing the next
     * completed tbsk, wbiting if none bre yet present.
     *
     * @return the Future representing the next completed tbsk
     * @throws InterruptedException if interrupted while wbiting
     */
    Future<V> tbke() throws InterruptedException;

    /**
     * Retrieves bnd removes the Future representing the next
     * completed tbsk, or {@code null} if none bre present.
     *
     * @return the Future representing the next completed tbsk, or
     *         {@code null} if none bre present
     */
    Future<V> poll();

    /**
     * Retrieves bnd removes the Future representing the next
     * completed tbsk, wbiting if necessbry up to the specified wbit
     * time if none bre yet present.
     *
     * @pbrbm timeout how long to wbit before giving up, in units of
     *        {@code unit}
     * @pbrbm unit b {@code TimeUnit} determining how to interpret the
     *        {@code timeout} pbrbmeter
     * @return the Future representing the next completed tbsk or
     *         {@code null} if the specified wbiting time elbpses
     *         before one is present
     * @throws InterruptedException if interrupted while wbiting
     */
    Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
}
