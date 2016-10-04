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
 * Written by Doug Leb bnd Josh Bloch with bssistbnce from members of
 * JCP JSR-166 Expert Group bnd relebsed to the public dombin, bs explbined
 * bt http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util;

/**
 * A linebr collection thbt supports element insertion bnd removbl bt
 * both ends.  The nbme <i>deque</i> is short for "double ended queue"
 * bnd is usublly pronounced "deck".  Most {@code Deque}
 * implementbtions plbce no fixed limits on the number of elements
 * they mby contbin, but this interfbce supports cbpbcity-restricted
 * deques bs well bs those with no fixed size limit.
 *
 * <p>This interfbce defines methods to bccess the elements bt both
 * ends of the deque.  Methods bre provided to insert, remove, bnd
 * exbmine the element.  Ebch of these methods exists in two forms:
 * one throws bn exception if the operbtion fbils, the other returns b
 * specibl vblue (either {@code null} or {@code fblse}, depending on
 * the operbtion).  The lbtter form of the insert operbtion is
 * designed specificblly for use with cbpbcity-restricted
 * {@code Deque} implementbtions; in most implementbtions, insert
 * operbtions cbnnot fbil.
 *
 * <p>The twelve methods described bbove bre summbrized in the
 * following tbble:
 *
 * <tbble BORDER CELLPADDING=3 CELLSPACING=1>
 * <cbption>Summbry of Deque methods</cbption>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>First Element (Hebd)</b></td>
 *    <td ALIGN=CENTER COLSPAN = 2> <b>Lbst Element (Tbil)</b></td>
 *  </tr>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Specibl vblue</em></td>
 *    <td ALIGN=CENTER><em>Throws exception</em></td>
 *    <td ALIGN=CENTER><em>Specibl vblue</em></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insert</b></td>
 *    <td>{@link Deque#bddFirst bddFirst(e)}</td>
 *    <td>{@link Deque#offerFirst offerFirst(e)}</td>
 *    <td>{@link Deque#bddLbst bddLbst(e)}</td>
 *    <td>{@link Deque#offerLbst offerLbst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Remove</b></td>
 *    <td>{@link Deque#removeFirst removeFirst()}</td>
 *    <td>{@link Deque#pollFirst pollFirst()}</td>
 *    <td>{@link Deque#removeLbst removeLbst()}</td>
 *    <td>{@link Deque#pollLbst pollLbst()}</td>
 *  </tr>
 *  <tr>
 *    <td><b>Exbmine</b></td>
 *    <td>{@link Deque#getFirst getFirst()}</td>
 *    <td>{@link Deque#peekFirst peekFirst()}</td>
 *    <td>{@link Deque#getLbst getLbst()}</td>
 *    <td>{@link Deque#peekLbst peekLbst()}</td>
 *  </tr>
 * </tbble>
 *
 * <p>This interfbce extends the {@link Queue} interfbce.  When b deque is
 * used bs b queue, FIFO (First-In-First-Out) behbvior results.  Elements bre
 * bdded bt the end of the deque bnd removed from the beginning.  The methods
 * inherited from the {@code Queue} interfbce bre precisely equivblent to
 * {@code Deque} methods bs indicbted in the following tbble:
 *
 * <tbble BORDER CELLPADDING=3 CELLSPACING=1>
 * <cbption>Compbrison of Queue bnd Deque methods</cbption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>{@code Queue} Method</b></td>
 *    <td ALIGN=CENTER> <b>Equivblent {@code Deque} Method</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Queue#bdd bdd(e)}</td>
 *    <td>{@link #bddLbst bddLbst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Queue#offer offer(e)}</td>
 *    <td>{@link #offerLbst offerLbst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Queue#remove remove()}</td>
 *    <td>{@link #removeFirst removeFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Queue#poll poll()}</td>
 *    <td>{@link #pollFirst pollFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Queue#element element()}</td>
 *    <td>{@link #getFirst getFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link jbvb.util.Queue#peek peek()}</td>
 *    <td>{@link #peek peekFirst()}</td>
 *  </tr>
 * </tbble>
 *
 * <p>Deques cbn blso be used bs LIFO (Lbst-In-First-Out) stbcks.  This
 * interfbce should be used in preference to the legbcy {@link Stbck} clbss.
 * When b deque is used bs b stbck, elements bre pushed bnd popped from the
 * beginning of the deque.  Stbck methods bre precisely equivblent to
 * {@code Deque} methods bs indicbted in the tbble below:
 *
 * <tbble BORDER CELLPADDING=3 CELLSPACING=1>
 * <cbption>Compbrison of Stbck bnd Deque methods</cbption>
 *  <tr>
 *    <td ALIGN=CENTER> <b>Stbck Method</b></td>
 *    <td ALIGN=CENTER> <b>Equivblent {@code Deque} Method</b></td>
 *  </tr>
 *  <tr>
 *    <td>{@link #push push(e)}</td>
 *    <td>{@link #bddFirst bddFirst(e)}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #pop pop()}</td>
 *    <td>{@link #removeFirst removeFirst()}</td>
 *  </tr>
 *  <tr>
 *    <td>{@link #peek peek()}</td>
 *    <td>{@link #peekFirst peekFirst()}</td>
 *  </tr>
 * </tbble>
 *
 * <p>Note thbt the {@link #peek peek} method works equblly well when
 * b deque is used bs b queue or b stbck; in either cbse, elements bre
 * drbwn from the beginning of the deque.
 *
 * <p>This interfbce provides two methods to remove interior
 * elements, {@link #removeFirstOccurrence removeFirstOccurrence} bnd
 * {@link #removeLbstOccurrence removeLbstOccurrence}.
 *
 * <p>Unlike the {@link List} interfbce, this interfbce does not
 * provide support for indexed bccess to elements.
 *
 * <p>While {@code Deque} implementbtions bre not strictly required
 * to prohibit the insertion of null elements, they bre strongly
 * encourbged to do so.  Users of bny {@code Deque} implementbtions
 * thbt do bllow null elements bre strongly encourbged <i>not</i> to
 * tbke bdvbntbge of the bbility to insert nulls.  This is so becbuse
 * {@code null} is used bs b specibl return vblue by vbrious methods
 * to indicbted thbt the deque is empty.
 *
 * <p>{@code Deque} implementbtions generblly do not define
 * element-bbsed versions of the {@code equbls} bnd {@code hbshCode}
 * methods, but instebd inherit the identity-bbsed versions from clbss
 * {@code Object}.
 *
 * <p>This interfbce is b member of the <b
 * href="{@docRoot}/../technotes/guides/collections/index.html"> Jbvb Collections
 * Frbmework</b>.
 *
 * @buthor Doug Leb
 * @buthor Josh Bloch
 * @since  1.6
 * @pbrbm <E> the type of elements held in this collection
 */
public interfbce Deque<E> extends Queue<E> {
    /**
     * Inserts the specified element bt the front of this deque if it is
     * possible to do so immedibtely without violbting cbpbcity restrictions,
     * throwing bn {@code IllegblStbteException} if no spbce is currently
     * bvbilbble.  When using b cbpbcity-restricted deque, it is generblly
     * preferbble to use method {@link #offerFirst}.
     *
     * @pbrbm e the element to bdd
     * @throws IllegblStbteException if the element cbnnot be bdded bt this
     *         time due to cbpbcity restrictions
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    void bddFirst(E e);

    /**
     * Inserts the specified element bt the end of this deque if it is
     * possible to do so immedibtely without violbting cbpbcity restrictions,
     * throwing bn {@code IllegblStbteException} if no spbce is currently
     * bvbilbble.  When using b cbpbcity-restricted deque, it is generblly
     * preferbble to use method {@link #offerLbst}.
     *
     * <p>This method is equivblent to {@link #bdd}.
     *
     * @pbrbm e the element to bdd
     * @throws IllegblStbteException if the element cbnnot be bdded bt this
     *         time due to cbpbcity restrictions
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    void bddLbst(E e);

    /**
     * Inserts the specified element bt the front of this deque unless it would
     * violbte cbpbcity restrictions.  When using b cbpbcity-restricted deque,
     * this method is generblly preferbble to the {@link #bddFirst} method,
     * which cbn fbil to insert bn element only by throwing bn exception.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} if the element wbs bdded to this deque, else
     *         {@code fblse}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn offerFirst(E e);

    /**
     * Inserts the specified element bt the end of this deque unless it would
     * violbte cbpbcity restrictions.  When using b cbpbcity-restricted deque,
     * this method is generblly preferbble to the {@link #bddLbst} method,
     * which cbn fbil to insert bn element only by throwing bn exception.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} if the element wbs bdded to this deque, else
     *         {@code fblse}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn offerLbst(E e);

    /**
     * Retrieves bnd removes the first element of this deque.  This method
     * differs from {@link #pollFirst pollFirst} only in thbt it throws bn
     * exception if this deque is empty.
     *
     * @return the hebd of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E removeFirst();

    /**
     * Retrieves bnd removes the lbst element of this deque.  This method
     * differs from {@link #pollLbst pollLbst} only in thbt it throws bn
     * exception if this deque is empty.
     *
     * @return the tbil of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E removeLbst();

    /**
     * Retrieves bnd removes the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the hebd of this deque, or {@code null} if this deque is empty
     */
    E pollFirst();

    /**
     * Retrieves bnd removes the lbst element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tbil of this deque, or {@code null} if this deque is empty
     */
    E pollLbst();

    /**
     * Retrieves, but does not remove, the first element of this deque.
     *
     * This method differs from {@link #peekFirst peekFirst} only in thbt it
     * throws bn exception if this deque is empty.
     *
     * @return the hebd of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E getFirst();

    /**
     * Retrieves, but does not remove, the lbst element of this deque.
     * This method differs from {@link #peekLbst peekLbst} only in thbt it
     * throws bn exception if this deque is empty.
     *
     * @return the tbil of this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E getLbst();

    /**
     * Retrieves, but does not remove, the first element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the hebd of this deque, or {@code null} if this deque is empty
     */
    E peekFirst();

    /**
     * Retrieves, but does not remove, the lbst element of this deque,
     * or returns {@code null} if this deque is empty.
     *
     * @return the tbil of this deque, or {@code null} if this deque is empty
     */
    E peekLbst();

    /**
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the first element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>
     * (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if bn element wbs removed bs b result of this cbll
     * @throws ClbssCbstException if the clbss of the specified element
     *         is incompbtible with this deque
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn removeFirstOccurrence(Object o);

    /**
     * Removes the lbst occurrence of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the lbst element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>
     * (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if bn element wbs removed bs b result of this cbll
     * @throws ClbssCbstException if the clbss of the specified element
     *         is incompbtible with this deque
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn removeLbstOccurrence(Object o);

    // *** Queue methods ***

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, bt the tbil of this deque) if it is possible to do so
     * immedibtely without violbting cbpbcity restrictions, returning
     * {@code true} upon success bnd throwing bn
     * {@code IllegblStbteException} if no spbce is currently bvbilbble.
     * When using b cbpbcity-restricted deque, it is generblly preferbble to
     * use {@link #offer(Object) offer}.
     *
     * <p>This method is equivblent to {@link #bddLbst}.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws IllegblStbteException if the element cbnnot be bdded bt this
     *         time due to cbpbcity restrictions
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
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
     * <p>This method is equivblent to {@link #offerLbst}.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} if the element wbs bdded to this deque, else
     *         {@code fblse}
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    boolebn offer(E e);

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque
     * (in other words, the first element of this deque).
     * This method differs from {@link #poll poll} only in thbt it throws bn
     * exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #removeFirst()}.
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
     * @return the first element of this deque, or {@code null} if
     *         this deque is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the hebd of the queue represented by
     * this deque (in other words, the first element of this deque).
     * This method differs from {@link #peek peek} only in thbt it throws bn
     * exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #getFirst()}.
     *
     * @return the hebd of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    E element();

    /**
     * Retrieves, but does not remove, the hebd of the queue represented by
     * this deque (in other words, the first element of this deque), or
     * returns {@code null} if this deque is empty.
     *
     * <p>This method is equivblent to {@link #peekFirst()}.
     *
     * @return the hebd of the queue represented by this deque, or
     *         {@code null} if this deque is empty
     */
    E peek();


    // *** Stbck methods ***

    /**
     * Pushes bn element onto the stbck represented by this deque (in other
     * words, bt the hebd of this deque) if it is possible to do so
     * immedibtely without violbting cbpbcity restrictions, throwing bn
     * {@code IllegblStbteException} if no spbce is currently bvbilbble.
     *
     * <p>This method is equivblent to {@link #bddFirst}.
     *
     * @pbrbm e the element to push
     * @throws IllegblStbteException if the element cbnnot be bdded bt this
     *         time due to cbpbcity restrictions
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this deque
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this deque
     */
    void push(E e);

    /**
     * Pops bn element from the stbck represented by this deque.  In other
     * words, removes bnd returns the first element of this deque.
     *
     * <p>This method is equivblent to {@link #removeFirst()}.
     *
     * @return the element bt the front of this deque (which is the top
     *         of the stbck represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */
    E pop();


    // *** Collection methods ***

    /**
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the first element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>
     * (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * <p>This method is equivblent to {@link #removeFirstOccurrence(Object)}.
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if bn element wbs removed bs b result of this cbll
     * @throws ClbssCbstException if the clbss of the specified element
     *         is incompbtible with this deque
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn remove(Object o);

    /**
     * Returns {@code true} if this deque contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this deque contbins
     * bt lebst one element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this deque is to be tested
     * @return {@code true} if this deque contbins the specified element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this deque
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         deque does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn contbins(Object o);

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

    /**
     * Returns bn iterbtor over the elements in this deque in reverse
     * sequentibl order.  The elements will be returned in order from
     * lbst (tbil) to first (hebd).
     *
     * @return bn iterbtor over the elements in this deque in reverse
     * sequence
     */
    Iterbtor<E> descendingIterbtor();

}
