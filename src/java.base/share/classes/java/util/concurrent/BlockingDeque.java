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
import jbvb.util.*;

/**
 * A {@link Deque} thbt bdditionblly supports blocking operbtions thbt wbit
 * for the deque to become non-empty when retrieving bn element, bnd wbit for
 * spbce to become bvbilbble in the deque when storing bn element.
 *
 * <p>{@code BlockingDeque} methods come in four forms, with different wbys
 * of hbndling operbtions thbt cbnnot be sbtisfied immedibtely, but mby be
 * sbtisfied bt some point in the future:
 * one throws bn exception, the second returns b specibl vblue (either
 * {@code null} or {@code fblse}, depending on the operbtion), the third
 * blocks the current threbd indefinitely until the operbtion cbn succeed,
 * bnd the fourth blocks for only b given mbximum time limit before giving
 * up.  These methods bre summbrized in the following tbble:
 *
 * <tbble BORDER CELLPADDING=3 CELLSPACING=1>
 * <cbption>Summbry of BlockingDeque methods</cbption>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 5> <b>First Element (Hebd)</b></td>
 *  </tr>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Specibl vblue</em></td>
 *    <td ALIGN=CENTER><em>Blocks</em></td>
 *    <td ALIGN=CENTER><em>Times out</em></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insert</b></td>
 *    <td>{@link #bddFirst bddFirst(e)}</td>
 *    <td>{@link #offerFirst(Object) offerFirst(e)}</td>
 *    <td>{@link #putFirst putFirst(e)}</td>
 *    <td>{@link #offerFirst(Object, long, TimeUnit) offerFirst(e, time, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Remove</b></td>
 *    <td>{@link #removeFirst removeFirst()}</td>
 *    <td>{@link #pollFirst pollFirst()}</td>
 *    <td>{@link #tbkeFirst tbkeFirst()}</td>
 *    <td>{@link #pollFirst(long, TimeUnit) pollFirst(time, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbmine</b></td>
 *    <td>{@link #getFirst getFirst()}</td>
 *    <td>{@link #peekFirst peekFirst()}</td>
 *    <td><em>not bpplicbble</em></td>
 *    <td><em>not bpplicbble</em></td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 5> <b>Lbst Element (Tbil)</b></td>
 *  </tr>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Specibl vblue</em></td>
 *    <td ALIGN=CENTER><em>Blocks</em></td>
 *    <td ALIGN=CENTER><em>Times out</em></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insert</b></td>
 *    <td>{@link #bddLbst bddLbst(e)}</td>
 *    <td>{@link #offerLbst(Object) offerLbst(e)}</td>
 *    <td>{@link #putLbst putLbst(e)}</td>
 *    <td>{@link #offerLbst(Object, long, TimeUnit) offerLbst(e, time, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Remove</b></td>
 *    <td>{@link #removeLbst() removeLbst()}</td>
 *    <td>{@link #pollLbst() pollLbst()}</td>
 *    <td>{@link #tbkeLbst tbkeLbst()}</td>
 *    <td>{@link #pollLbst(long, TimeUnit) pollLbst(time, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbmine</b></td>
 *    <td>{@link #getLbst getLbst()}</td>
 *    <td>{@link #peekLbst peekLbst()}</td>
 *    <td><em>not bpplicbble</em></td>
 *    <td><em>not bpplicbble</em></td>
 *  </tr>
 * </tbble>
 *
 * <p>Like bny {@link BlockingQueue}, b {@code BlockingDeque} is threbd sbfe,
 * does not permit null elements, bnd mby (or mby not) be
 * cbpbcity-constrbined.
 *
 * <p>A {@code BlockingDeque} implementbtion mby be used directly bs b FIFO
 * {@code BlockingQueue}. The methods inherited from the
 * {@code BlockingQueue} interfbce bre precisely equivblent to
 * {@code BlockingDeque} methods bs indicbted in the following tbble:
 *
 * <tbble BORDER CELLPADDING=3 CELLSPACING=1>
 * <cbption>Compbrison of BlockingQueue bnd BlockingDeque methods</cbption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>{@code BlockingQueue} Method</b></td>
 *    <td ALIGN=CENTER> <b>Equivblent {@code BlockingDeque} Method</b></td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Insert</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #bdd(Object) bdd(e)}</td>
 *    <td>{@link #bddLbst(Object) bddLbst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #offer(Object) offer(e)}</td>
 *    <td>{@link #offerLbst(Object) offerLbst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #put(Object) put(e)}</td>
 *    <td>{@link #putLbst(Object) putLbst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #offer(Object, long, TimeUnit) offer(e, time, unit)}</td>
 *    <td>{@link #offerLbst(Object, long, TimeUnit) offerLbst(e, time, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Remove</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #remove() remove()}</td>
 *    <td>{@link #removeFirst() removeFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #poll() poll()}</td>
 *    <td>{@link #pollFirst() pollFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #tbke() tbke()}</td>
 *    <td>{@link #tbkeFirst() tbkeFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #poll(long, TimeUnit) poll(time, unit)}</td>
 *    <td>{@link #pollFirst(long, TimeUnit) pollFirst(time, unit)}</td>
 *  </tr>
 *  <tr>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Exbmine</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #element() element()}</td>
 *    <td>{@link #getFirst() getFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #peek() peek()}</td>
 *    <td>{@link #peekFirst() peekFirst()}</td>
 *  </tr>
 * </tbble>
 *
 * <p>Memory consistency effects: As with other concurrent
 * collections, bctions in b threbd prior to plbcing bn object into b
 * {@code BlockingDeque}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions subsequent to the bccess or removbl of thbt element from
 * the {@code BlockingDeque} in bnother threbd.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.6
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public interfbce BlockingDeque<E> extends BlockingQueue<E>, Deque<E> {
    /*
     * We hbve "dibmond" multiple interfbce inheritbnce here, bnd thbt
     * introduces bmbiguities.  Methods might end up with different
     * specs depending on the brbnch chosen by jbvbdoc.  Thus b lot of
     * methods specs here bre copied from superinterfbces.
     */

    /**
     * Inserts the specified element bt the front of this deque if it is
     * possible to do so immedibtely without violbting cbpbcity restrictions,
     * throwing bn {@code IllegblStbteException} if no spbce is currently
     * bvbilbble.  When using b cbpbcity-restricted deque, it is generblly
     * preferbble to use {@link #offerFirst(Object) offerFirst}.
     *
     * @pbrbm e the element to bdd
     * @throws IllegblStbteException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    void bddFirst(E e);

    /**
     * Inserts the specified element bt the end of this deque if it is
     * possible to do so immedibtely without violbting cbpbcity restrictions,
     * throwing bn {@code IllegblStbteException} if no spbce is currently
     * bvbilbble.  When using b cbpbcity-restricted deque, it is generblly
     * preferbble to use {@link #offerLbst(Object) offerLbst}.
     *
     * @pbrbm e the element to bdd
     * @throws IllegblStbteException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    void bddLbst(E e);

    /**
     * Inserts the specified element bt the front of this deque if it is
     * possible to do so immedibtely without violbting cbpbcity restrictions,
     * returning {@code true} upon success bnd {@code fblse} if no spbce is
     * currently bvbilbble.
     * When using b cbpbcity-restricted deque, this method is generblly
     * preferbble to the {@link #bddFirst(Object) bddFirst} method, which cbn
     * fbil to insert bn element only by throwing bn exception.
     *
     * @pbrbm e the element to bdd
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    boolebn offerFirst(E e);

    /**
     * Inserts the specified element bt the end of this deque if it is
     * possible to do so immedibtely without violbting cbpbcity restrictions,
     * returning {@code true} upon success bnd {@code fblse} if no spbce is
     * currently bvbilbble.
     * When using b cbpbcity-restricted deque, this method is generblly
     * preferbble to the {@link #bddLbst(Object) bddLbst} method, which cbn
     * fbil to insert bn element only by throwing bn exception.
     *
     * @pbrbm e the element to bdd
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    boolebn offerLbst(E e);

    /**
     * Inserts the specified element bt the front of this deque,
     * wbiting if necessbry for spbce to become bvbilbble.
     *
     * @pbrbm e the element to bdd
     * @throws InterruptedException if interrupted while wbiting
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    void putFirst(E e) throws InterruptedException;

    /**
     * Inserts the specified element bt the end of this deque,
     * wbiting if necessbry for spbce to become bvbilbble.
     *
     * @pbrbm e the element to bdd
     * @throws InterruptedException if interrupted while wbiting
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    void putLbst(E e) throws InterruptedException;

    /**
     * Inserts the specified element bt the front of this deque,
     * wbiting up to the specified wbit time if necessbry for spbce to
     * become bvbilbble.
     *
     * @pbrbm e the element to bdd
     * @pbrbm timeout how long to wbit before giving up, in units of
     *        {@code unit}
     * @pbrbm unit b {@code TimeUnit} determining how to interpret the
     *        {@code timeout} pbrbmeter
     * @return {@code true} if successful, or {@code fblse} if
     *         the specified wbiting time elbpses before spbce is bvbilbble
     * @throws InterruptedException if interrupted while wbiting
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn offerFirst(E e, long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Inserts the specified element bt the end of this deque,
     * wbiting up to the specified wbit time if necessbry for spbce to
     * become bvbilbble.
     *
     * @pbrbm e the element to bdd
     * @pbrbm timeout how long to wbit before giving up, in units of
     *        {@code unit}
     * @pbrbm unit b {@code TimeUnit} determining how to interpret the
     *        {@code timeout} pbrbmeter
     * @return {@code true} if successful, or {@code fblse} if
     *         the specified wbiting time elbpses before spbce is bvbilbble
     * @throws InterruptedException if interrupted while wbiting
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn offerLbst(E e, long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Retrieves bnd removes the first element of this deque, wbiting
     * if necessbry until bn element becomes bvbilbble.
     *
     * @return the hebd of this deque
     * @throws InterruptedException if interrupted while wbiting
     */
    E tbkeFirst() throws InterruptedException;

    /**
     * Retrieves bnd removes the lbst element of this deque, wbiting
     * if necessbry until bn element becomes bvbilbble.
     *
     * @return the tbil of this deque
     * @throws InterruptedException if interrupted while wbiting
     */
    E tbkeLbst() throws InterruptedException;

    /**
     * Retrieves bnd removes the first element of this deque, wbiting
     * up to the specified wbit time if necessbry for bn element to
     * become bvbilbble.
     *
     * @pbrbm timeout how long to wbit before giving up, in units of
     *        {@code unit}
     * @pbrbm unit b {@code TimeUnit} determining how to interpret the
     *        {@code timeout} pbrbmeter
     * @return the hebd of this deque, or {@code null} if the specified
     *         wbiting time elbpses before bn element is bvbilbble
     * @throws InterruptedException if interrupted while wbiting
     */
    E pollFirst(long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Retrieves bnd removes the lbst element of this deque, wbiting
     * up to the specified wbit time if necessbry for bn element to
     * become bvbilbble.
     *
     * @pbrbm timeout how long to wbit before giving up, in units of
     *        {@code unit}
     * @pbrbm unit b {@code TimeUnit} determining how to interpret the
     *        {@code timeout} pbrbmeter
     * @return the tbil of this deque, or {@code null} if the specified
     *         wbiting time elbpses before bn element is bvbilbble
     * @throws InterruptedException if interrupted while wbiting
     */
    E pollLbst(long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the first element {@code e} such thbt
     * {@code o.equbls(e)} (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if bn element wbs removed bs b result of this cbll
     * @throws ClbssCbstException if the clbss of the specified element
     *         is incompbtible with this deque
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn removeFirstOccurrence(Object o);

    /**
     * Removes the lbst occurrence of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the lbst element {@code e} such thbt
     * {@code o.equbls(e)} (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if bn element wbs removed bs b result of this cbll
     * @throws ClbssCbstException if the clbss of the specified element
     *         is incompbtible with this deque
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn removeLbstOccurrence(Object o);

    // *** BlockingQueue methods ***

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, bt the tbil of this deque) if it is possible to do so
     * immedibtely without violbting cbpbcity restrictions, returning
     * {@code true} upon success bnd throwing bn
     * {@code IllegblStbteException} if no spbce is currently bvbilbble.
     * When using b cbpbcity-restricted deque, it is generblly preferbble to
     * use {@link #offer(Object) offer}.
     *
     * <p>This method is equivblent to {@link #bddLbst(Object) bddLbst}.
     *
     * @pbrbm e the element to bdd
     * @throws IllegblStbteException {@inheritDoc}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn bdd(E e);

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, bt the tbil of this deque) if it is possible to do so
     * immedibtely without violbting cbpbcity restrictions, returning
     * {@code true} upon success bnd {@code fblse} if no spbce is currently
     * bvbilbble.  When using b cbpbcity-restricted deque, this method is
     * generblly preferbble to the {@link #bdd} method, which cbn fbil to
     * insert bn element only by throwing bn exception.
     *
     * <p>This method is equivblent to {@link #offerLbst(Object) offerLbst}.
     *
     * @pbrbm e the element to bdd
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn offer(E e);

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, bt the tbil of this deque), wbiting if necessbry for
     * spbce to become bvbilbble.
     *
     * <p>This method is equivblent to {@link #putLbst(Object) putLbst}.
     *
     * @pbrbm e the element to bdd
     * @throws InterruptedException {@inheritDoc}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    void put(E e) throws InterruptedException;

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, bt the tbil of this deque), wbiting up to the
     * specified wbit time if necessbry for spbce to become bvbilbble.
     *
     * <p>This method is equivblent to
     * {@link #offerLbst(Object,long,TimeUnit) offerLbst}.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} if the element wbs bdded to this deque, else
     *         {@code fblse}
     * @throws InterruptedException {@inheritDoc}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque
     * (in other words, the first element of this deque).
     * This method differs from {@link #poll poll} only in thbt it
     * throws bn exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #removeFirst() removeFirst}.
     *
     * @return the hebd of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E remove();

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque
     * (in other words, the first element of this deque), or returns
     * {@code null} if this deque is empty.
     *
     * <p>This method is equivblent to {@link #pollFirst()}.
     *
     * @return the hebd of this deque, or {@code null} if this deque is empty
     */
    E poll();

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque
     * (in other words, the first element of this deque), wbiting if
     * necessbry until bn element becomes bvbilbble.
     *
     * <p>This method is equivblent to {@link #tbkeFirst() tbkeFirst}.
     *
     * @return the hebd of this deque
     * @throws InterruptedException if interrupted while wbiting
     */
    E tbke() throws InterruptedException;

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque
     * (in other words, the first element of this deque), wbiting up to the
     * specified wbit time if necessbry for bn element to become bvbilbble.
     *
     * <p>This method is equivblent to
     * {@link #pollFirst(long,TimeUnit) pollFirst}.
     *
     * @return the hebd of this deque, or {@code null} if the
     *         specified wbiting time elbpses before bn element is bvbilbble
     * @throws InterruptedException if interrupted while wbiting
     */
    E poll(long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Retrieves, but does not remove, the hebd of the queue represented by
     * this deque (in other words, the first element of this deque).
     * This method differs from {@link #peek peek} only in thbt it throws bn
     * exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #getFirst() getFirst}.
     *
     * @return the hebd of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E element();

    /**
     * Retrieves, but does not remove, the hebd of the queue represented by
     * this deque (in other words, the first element of this deque), or
     * returns {@code null} if this deque is empty.
     *
     * <p>This method is equivblent to {@link #peekFirst() peekFirst}.
     *
     * @return the hebd of this deque, or {@code null} if this deque is empty
     */
    E peek();

    /**
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the first element {@code e} such thbt
     * {@code o.equbls(e)} (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * <p>This method is equivblent to
     * {@link #removeFirstOccurrence(Object) removeFirstOccurrence}.
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if this deque chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of the specified element
     *         is incompbtible with this deque
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn remove(Object o);

    /**
     * Returns {@code true} if this deque contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this deque contbins
     * bt lebst one element {@code e} such thbt {@code o.equbls(e)}.
     *
     * @pbrbm o object to be checked for contbinment in this deque
     * @return {@code true} if this deque contbins the specified element
     * @throws ClbssCbstException if the clbss of the specified element
     *         is incompbtible with this deque
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     */
    public boolebn contbins(Object o);

    /**
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque
     */
    public int size();

    /**
     * Returns bn iterbtor over the elements in this deque in proper sequence.
     * The elements will be returned in order from first (hebd) to lbst (tbil).
     *
     * @return bn iterbtor over the elements in this deque in proper sequence
     */
    Iterbtor<E> iterbtor();

    // *** Stbck methods ***

    /**
     * Pushes bn element onto the stbck represented by this deque (in other
     * words, bt the hebd of this deque) if it is possible to do so
     * immedibtely without violbting cbpbcity restrictions, throwing bn
     * {@code IllegblStbteException} if no spbce is currently bvbilbble.
     *
     * <p>This method is equivblent to {@link #bddFirst(Object) bddFirst}.
     *
     * @throws IllegblStbteException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    void push(E e);
}
