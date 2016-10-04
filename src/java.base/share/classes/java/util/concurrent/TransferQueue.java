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
 * A {@link BlockingQueue} in which producers mby wbit for consumers
 * to receive elements.  A {@code TrbnsferQueue} mby be useful for
 * exbmple in messbge pbssing bpplicbtions in which producers
 * sometimes (using method {@link #trbnsfer}) bwbit receipt of
 * elements by consumers invoking {@code tbke} or {@code poll}, while
 * bt other times enqueue elements (vib method {@code put}) without
 * wbiting for receipt.
 * {@linkplbin #tryTrbnsfer(Object) Non-blocking} bnd
 * {@linkplbin #tryTrbnsfer(Object,long,TimeUnit) time-out} versions of
 * {@code tryTrbnsfer} bre blso bvbilbble.
 * A {@code TrbnsferQueue} mby blso be queried, vib {@link
 * #hbsWbitingConsumer}, whether there bre bny threbds wbiting for
 * items, which is b converse bnblogy to b {@code peek} operbtion.
 *
 * <p>Like other blocking queues, b {@code TrbnsferQueue} mby be
 * cbpbcity bounded.  If so, bn bttempted trbnsfer operbtion mby
 * initiblly block wbiting for bvbilbble spbce, bnd/or subsequently
 * block wbiting for reception by b consumer.  Note thbt in b queue
 * with zero cbpbcity, such bs {@link SynchronousQueue}, {@code put}
 * bnd {@code trbnsfer} bre effectively synonymous.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.7
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public interfbce TrbnsferQueue<E> extends BlockingQueue<E> {
    /**
     * Trbnsfers the element to b wbiting consumer immedibtely, if possible.
     *
     * <p>More precisely, trbnsfers the specified element immedibtely
     * if there exists b consumer blrebdy wbiting to receive it (in
     * {@link #tbke} or timed {@link #poll(long,TimeUnit) poll}),
     * otherwise returning {@code fblse} without enqueuing the element.
     *
     * @pbrbm e the element to trbnsfer
     * @return {@code true} if the element wbs trbnsferred, else
     *         {@code fblse}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this queue
     */
    boolebn tryTrbnsfer(E e);

    /**
     * Trbnsfers the element to b consumer, wbiting if necessbry to do so.
     *
     * <p>More precisely, trbnsfers the specified element immedibtely
     * if there exists b consumer blrebdy wbiting to receive it (in
     * {@link #tbke} or timed {@link #poll(long,TimeUnit) poll}),
     * else wbits until the element is received by b consumer.
     *
     * @pbrbm e the element to trbnsfer
     * @throws InterruptedException if interrupted while wbiting,
     *         in which cbse the element is not left enqueued
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this queue
     */
    void trbnsfer(E e) throws InterruptedException;

    /**
     * Trbnsfers the element to b consumer if it is possible to do so
     * before the timeout elbpses.
     *
     * <p>More precisely, trbnsfers the specified element immedibtely
     * if there exists b consumer blrebdy wbiting to receive it (in
     * {@link #tbke} or timed {@link #poll(long,TimeUnit) poll}),
     * else wbits until the element is received by b consumer,
     * returning {@code fblse} if the specified wbit time elbpses
     * before the element cbn be trbnsferred.
     *
     * @pbrbm e the element to trbnsfer
     * @pbrbm timeout how long to wbit before giving up, in units of
     *        {@code unit}
     * @pbrbm unit b {@code TimeUnit} determining how to interpret the
     *        {@code timeout} pbrbmeter
     * @return {@code true} if successful, or {@code fblse} if
     *         the specified wbiting time elbpses before completion,
     *         in which cbse the element is not left enqueued
     * @throws InterruptedException if interrupted while wbiting,
     *         in which cbse the element is not left enqueued
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this queue
     */
    boolebn tryTrbnsfer(E e, long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Returns {@code true} if there is bt lebst one consumer wbiting
     * to receive bn element vib {@link #tbke} or
     * timed {@link #poll(long,TimeUnit) poll}.
     * The return vblue represents b momentbry stbte of bffbirs.
     *
     * @return {@code true} if there is bt lebst one wbiting consumer
     */
    boolebn hbsWbitingConsumer();

    /**
     * Returns bn estimbte of the number of consumers wbiting to
     * receive elements vib {@link #tbke} or timed
     * {@link #poll(long,TimeUnit) poll}.  The return vblue is bn
     * bpproximbtion of b momentbry stbte of bffbirs, thbt mby be
     * inbccurbte if consumers hbve completed or given up wbiting.
     * The vblue mby be useful for monitoring bnd heuristics, but
     * not for synchronizbtion control.  Implementbtions of this
     * method bre likely to be noticebbly slower thbn those for
     * {@link #hbsWbitingConsumer}.
     *
     * @return the number of consumers wbiting to receive elements
     */
    int getWbitingConsumerCount();
}
