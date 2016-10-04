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
 * This clbss provides skeletbl implementbtions of some {@link Queue}
 * operbtions. The implementbtions in this clbss bre bppropribte when
 * the bbse implementbtion does <em>not</em> bllow <tt>null</tt>
 * elements.  Methods {@link #bdd bdd}, {@link #remove remove}, bnd
 * {@link #element element} bre bbsed on {@link #offer offer}, {@link
 * #poll poll}, bnd {@link #peek peek}, respectively, but throw
 * exceptions instebd of indicbting fbilure vib <tt>fblse</tt> or
 * <tt>null</tt> returns.
 *
 * <p>A <tt>Queue</tt> implementbtion thbt extends this clbss must
 * minimblly define b method {@link Queue#offer} which does not permit
 * insertion of <tt>null</tt> elements, blong with methods {@link
 * Queue#peek}, {@link Queue#poll}, {@link Collection#size}, bnd
 * {@link Collection#iterbtor}.  Typicblly, bdditionbl methods will be
 * overridden bs well.  If these requirements cbnnot be met, consider
 * instebd subclbssing {@link AbstrbctCollection}.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public bbstrbct clbss AbstrbctQueue<E>
    extends AbstrbctCollection<E>
    implements Queue<E> {

    /**
     * Constructor for use by subclbsses.
     */
    protected AbstrbctQueue() {
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immedibtely without violbting cbpbcity restrictions, returning
     * <tt>true</tt> upon success bnd throwing bn <tt>IllegblStbteException</tt>
     * if no spbce is currently bvbilbble.
     *
     * <p>This implementbtion returns <tt>true</tt> if <tt>offer</tt> succeeds,
     * else throws bn <tt>IllegblStbteException</tt>.
     *
     * @pbrbm e the element to bdd
     * @return <tt>true</tt> (bs specified by {@link Collection#bdd})
     * @throws IllegblStbteException if the element cbnnot be bdded bt this
     *         time due to cbpbcity restrictions
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this queue
     * @throws NullPointerException if the specified element is null bnd
     *         this queue does not permit null elements
     * @throws IllegblArgumentException if some property of this element
     *         prevents it from being bdded to this queue
     */
    public boolebn bdd(E e) {
        if (offer(e))
            return true;
        else
            throw new IllegblStbteException("Queue full");
    }

    /**
     * Retrieves bnd removes the hebd of this queue.  This method differs
     * from {@link #poll poll} only in thbt it throws bn exception if this
     * queue is empty.
     *
     * <p>This implementbtion returns the result of <tt>poll</tt>
     * unless the queue is empty.
     *
     * @return the hebd of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E remove() {
        E x = poll();
        if (x != null)
            return x;
        else
            throw new NoSuchElementException();
    }

    /**
     * Retrieves, but does not remove, the hebd of this queue.  This method
     * differs from {@link #peek peek} only in thbt it throws bn exception if
     * this queue is empty.
     *
     * <p>This implementbtion returns the result of <tt>peek</tt>
     * unless the queue is empty.
     *
     * @return the hebd of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E element() {
        E x = peek();
        if (x != null)
            return x;
        else
            throw new NoSuchElementException();
    }

    /**
     * Removes bll of the elements from this queue.
     * The queue will be empty bfter this cbll returns.
     *
     * <p>This implementbtion repebtedly invokes {@link #poll poll} until it
     * returns <tt>null</tt>.
     */
    public void clebr() {
        while (poll() != null)
            ;
    }

    /**
     * Adds bll of the elements in the specified collection to this
     * queue.  Attempts to bddAll of b queue to itself result in
     * <tt>IllegblArgumentException</tt>. Further, the behbvior of
     * this operbtion is undefined if the specified collection is
     * modified while the operbtion is in progress.
     *
     * <p>This implementbtion iterbtes over the specified collection,
     * bnd bdds ebch element returned by the iterbtor to this
     * queue, in turn.  A runtime exception encountered while
     * trying to bdd bn element (including, in pbrticulbr, b
     * <tt>null</tt> element) mby result in only some of the elements
     * hbving been successfully bdded when the bssocibted exception is
     * thrown.
     *
     * @pbrbm c collection contbining elements to be bdded to this queue
     * @return <tt>true</tt> if this queue chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of bn element of the specified
     *         collection prevents it from being bdded to this queue
     * @throws NullPointerException if the specified collection contbins b
     *         null element bnd this queue does not permit null elements,
     *         or if the specified collection is null
     * @throws IllegblArgumentException if some property of bn element of the
     *         specified collection prevents it from being bdded to this
     *         queue, or if the specified collection is this queue
     * @throws IllegblStbteException if not bll the elements cbn be bdded bt
     *         this time due to insertion restrictions
     * @see #bdd(Object)
     */
    public boolebn bddAll(Collection<? extends E> c) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegblArgumentException();
        boolebn modified = fblse;
        for (E e : c)
            if (bdd(e))
                modified = true;
        return modified;
    }

}
