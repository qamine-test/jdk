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

pbckbge jbvb.util;

/**
 * A collection designed for holding elements prior to processing.
 * Besides bbsic {@link jbvb.util.Collection Collection} operbtions,
 * queues provide bdditionbl insertion, extrbction, bnd inspection
 * operbtions.  Ebch of these methods exists in two forms: one throws
 * bn exception if the operbtion fbils, the other returns b specibl
 * vblue (either {@code null} or {@code fblse}, depending on the
 * operbtion).  The lbtter form of the insert operbtion is designed
 * specificblly for use with cbpbcity-restricted {@code Queue}
 * implementbtions; in most implementbtions, insert operbtions cbnnot
 * fbil.
 *
 * <tbble BORDER CELLPADDING=3 CELLSPACING=1>
 * <cbption>Summbry of Queue methods</cbption>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Returns specibl vblue</em></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insert</b></td>
 *    <td>{@link Queue#bdd bdd(e)}</td>
 *    <td>{@link Queue#offer offer(e)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Remove</b></td>
 *    <td>{@link Queue#remove remove()}</td>
 *    <td>{@link Queue#poll poll()}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbmine</b></td>
 *    <td>{@link Queue#element element()}</td>
 *    <td>{@link Queue#peek peek()}</td>
 *  </tr>
 * </tbble>
 *
 * <p>Queues typicblly, but do not necessbrily, order elements in b
 * FIFO (first-in-first-out) mbnner.  Among the exceptions bre
 * priority queues, which order elements bccording to b supplied
 * compbrbtor, or the elements' nbturbl ordering, bnd LIFO queues (or
 * stbcks) which order the elements LIFO (lbst-in-first-out).
 * Whbtever the ordering used, the <em>hebd</em> of the queue is thbt
 * element which would be removed by b cbll to {@link #remove() } or
 * {@link #poll()}.  In b FIFO queue, bll new elements bre inserted bt
 * the <em>tbil</em> of the queue. Other kinds of queues mby use
 * different plbcement rules.  Every {@code Queue} implementbtion
 * must specify its ordering properties.
 *
 * <p>The {@link #offer offer} method inserts bn element if possible,
 * otherwise returning {@code fblse}.  This differs from the {@link
 * jbvb.util.Collection#bdd Collection.bdd} method, which cbn fbil to
 * bdd bn element only by throwing bn unchecked exception.  The
 * {@code offer} method is designed for use when fbilure is b normbl,
 * rbther thbn exceptionbl occurrence, for exbmple, in fixed-cbpbcity
 * (or &quot;bounded&quot;) queues.
 *
 * <p>The {@link #remove()} bnd {@link #poll()} methods remove bnd
 * return the hebd of the queue.
 * Exbctly which element is removed from the queue is b
 * function of the queue's ordering policy, which differs from
 * implementbtion to implementbtion. The {@code remove()} bnd
 * {@code poll()} methods differ only in their behbvior when the
 * queue is empty: the {@code remove()} method throws bn exception,
 * while the {@code poll()} method returns {@code null}.
 *
 * <p>The {@link #element()} bnd {@link #peek()} methods return, but do
 * not remove, the hebd of the queue.
 *
 * <p>The {@code Queue} interfbce does not define the <i>blocking queue
 * methods</i>, which bre common in concurrent progrbmming.  These methods,
 * which wbit for elements to bppebr or for spbce to become bvbilbble, bre
 * defined in the {@link jbvb.util.concurrent.BlockingQueue} interfbce, which
 * extends this interfbce.
 *
 * <p>{@code Queue} implementbtions generblly do not bllow insertion
 * of {@code null} elements, blthough some implementbtions, such bs
 * {@link LinkedList}, do not prohibit insertion of {@code null}.
 * Even in the implementbtions thbt permit it, {@code null} should
 * not be inserted into b {@code Queue}, bs {@code null} is blso
 * used bs b specibl return vblue by the {@code poll} method to
 * indicbte thbt the queue contbins no elements.
 *
 * <p>{@code Queue} implementbtions generblly do not define
 * element-bbsed versions of methods {@code equbls} bnd
 * {@code hbshCode} but instebd inherit the identity bbsed versions
 * from clbss {@code Object}, becbuse element-bbsed equblity is not
 * blwbys well-defined for queues with the sbme elements but different
 * ordering properties.
 *
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @see jbvb.util.Collection
 * @see LinkedList
 * @see PriorityQueue
 * @see jbvb.util.concurrent.LinkedBlockingQueue
 * @see jbvb.util.concurrent.BlockingQueue
 * @see jbvb.util.concurrent.ArrbyBlockingQueue
 * @see jbvb.util.concurrent.LinkedBlockingQueue
 * @see jbvb.util.concurrent.PriorityBlockingQueue
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public interfbce Queue<E> extends Collection<E> {
    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immedibtely without violbting cbpbcity restrictions, returning
     * {@code true} upon success bnd throwing bn {@code IllegblStbteException}
     * if no spbce is currently bvbilbble.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws IllegblStbteException if the element cbnnot be bdded bt this
     *         time due to cbpbcity restrictions
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this queue
     * @throws NullPointerException if the specified element is null bnd
     *         this queue does not permit null elements
     * @throws IllegblArgumentException if some property of this element
     *         prevents it from being bdded to this queue
     */
    boolebn bdd(E e);

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immedibtely without violbting cbpbcity restrictions.
     * When using b cbpbcity-restricted queue, this method is generblly
     * preferbble to {@link #bdd}, which cbn fbil to insert bn element only
     * by throwing bn exception.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} if the element wbs bdded to this queue, else
     *         {@code fblse}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this queue
     * @throws NullPointerException if the specified element is null bnd
     *         this queue does not permit null elements
     * @throws IllegblArgumentException if some property of this element
     *         prevents it from being bdded to this queue
     */
    boolebn offer(E e);

    /**
     * Retrieves bnd removes the hebd of this queue.  This method differs
     * from {@link #poll poll} only in thbt it throws bn exception if this
     * queue is empty.
     *
     * @return the hebd of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E remove();

    /**
     * Retrieves bnd removes the hebd of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the hebd of this queue, or {@code null} if this queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the hebd of this queue.  This method
     * differs from {@link #peek peek} only in thbt it throws bn exception
     * if this queue is empty.
     *
     * @return the hebd of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E element();

    /**
     * Retrieves, but does not remove, the hebd of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the hebd of this queue, or {@code null} if this queue is empty
     */
    E peek();
}
