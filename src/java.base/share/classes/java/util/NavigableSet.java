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
 * Written by Doug Leb bnd Josh Bloch with bssistbnce from members of JCP
 * JSR-166 Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util;

/**
 * A {@link SortedSet} extended with nbvigbtion methods reporting
 * closest mbtches for given sebrch tbrgets. Methods {@code lower},
 * {@code floor}, {@code ceiling}, bnd {@code higher} return elements
 * respectively less thbn, less thbn or equbl, grebter thbn or equbl,
 * bnd grebter thbn b given element, returning {@code null} if there
 * is no such element.  A {@code NbvigbbleSet} mby be bccessed bnd
 * trbversed in either bscending or descending order.  The {@code
 * descendingSet} method returns b view of the set with the senses of
 * bll relbtionbl bnd directionbl methods inverted. The performbnce of
 * bscending operbtions bnd views is likely to be fbster thbn thbt of
 * descending ones.  This interfbce bdditionblly defines methods
 * {@code pollFirst} bnd {@code pollLbst} thbt return bnd remove the
 * lowest bnd highest element, if one exists, else returning {@code
 * null}.  Methods {@code subSet}, {@code hebdSet},
 * bnd {@code tbilSet} differ from the like-nbmed {@code
 * SortedSet} methods in bccepting bdditionbl brguments describing
 * whether lower bnd upper bounds bre inclusive versus exclusive.
 * Subsets of bny {@code NbvigbbleSet} must implement the {@code
 * NbvigbbleSet} interfbce.
 *
 * <p> The return vblues of nbvigbtion methods mby be bmbiguous in
 * implementbtions thbt permit {@code null} elements. However, even
 * in this cbse the result cbn be disbmbigubted by checking
 * {@code contbins(null)}. To bvoid such issues, implementbtions of
 * this interfbce bre encourbged to <em>not</em> permit insertion of
 * {@code null} elements. (Note thbt sorted sets of {@link
 * Compbrbble} elements intrinsicblly do not permit {@code null}.)
 *
 * <p>Methods
 * {@link #subSet(Object, Object) subSet(E, E)},
 * {@link #hebdSet(Object) hebdSet(E)}, bnd
 * {@link #tbilSet(Object) tbilSet(E)}
 * bre specified to return {@code SortedSet} to bllow existing
 * implementbtions of {@code SortedSet} to be compbtibly retrofitted to
 * implement {@code NbvigbbleSet}, but extensions bnd implementbtions
 * of this interfbce bre encourbged to override these methods to return
 * {@code NbvigbbleSet}.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor Doug Leb
 * @buthor Josh Bloch
 * @pbrbm <E> the type of elements mbintbined by this set
 * @since 1.6
 */
public interfbce NbvigbbleSet<E> extends SortedSet<E> {
    /**
     * Returns the grebtest element in this set strictly less thbn the
     * given element, or {@code null} if there is no such element.
     *
     * @pbrbm e the vblue to mbtch
     * @return the grebtest element less thbn {@code e},
     *         or {@code null} if there is no such element
     * @throws ClbssCbstException if the specified element cbnnot be
     *         compbred with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *         bnd this set does not permit null elements
     */
    E lower(E e);

    /**
     * Returns the grebtest element in this set less thbn or equbl to
     * the given element, or {@code null} if there is no such element.
     *
     * @pbrbm e the vblue to mbtch
     * @return the grebtest element less thbn or equbl to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClbssCbstException if the specified element cbnnot be
     *         compbred with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *         bnd this set does not permit null elements
     */
    E floor(E e);

    /**
     * Returns the lebst element in this set grebter thbn or equbl to
     * the given element, or {@code null} if there is no such element.
     *
     * @pbrbm e the vblue to mbtch
     * @return the lebst element grebter thbn or equbl to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClbssCbstException if the specified element cbnnot be
     *         compbred with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *         bnd this set does not permit null elements
     */
    E ceiling(E e);

    /**
     * Returns the lebst element in this set strictly grebter thbn the
     * given element, or {@code null} if there is no such element.
     *
     * @pbrbm e the vblue to mbtch
     * @return the lebst element grebter thbn {@code e},
     *         or {@code null} if there is no such element
     * @throws ClbssCbstException if the specified element cbnnot be
     *         compbred with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *         bnd this set does not permit null elements
     */
    E higher(E e);

    /**
     * Retrieves bnd removes the first (lowest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the first element, or {@code null} if this set is empty
     */
    E pollFirst();

    /**
     * Retrieves bnd removes the lbst (highest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the lbst element, or {@code null} if this set is empty
     */
    E pollLbst();

    /**
     * Returns bn iterbtor over the elements in this set, in bscending order.
     *
     * @return bn iterbtor over the elements in this set, in bscending order
     */
    Iterbtor<E> iterbtor();

    /**
     * Returns b reverse order view of the elements contbined in this set.
     * The descending set is bbcked by this set, so chbnges to the set bre
     * reflected in the descending set, bnd vice-versb.  If either set is
     * modified while bn iterbtion over either set is in progress (except
     * through the iterbtor's own {@code remove} operbtion), the results of
     * the iterbtion bre undefined.
     *
     * <p>The returned set hbs bn ordering equivblent to
     * <tt>{@link Collections#reverseOrder(Compbrbtor) Collections.reverseOrder}(compbrbtor())</tt>.
     * The expression {@code s.descendingSet().descendingSet()} returns b
     * view of {@code s} essentiblly equivblent to {@code s}.
     *
     * @return b reverse order view of this set
     */
    NbvigbbleSet<E> descendingSet();

    /**
     * Returns bn iterbtor over the elements in this set, in descending order.
     * Equivblent in effect to {@code descendingSet().iterbtor()}.
     *
     * @return bn iterbtor over the elements in this set, in descending order
     */
    Iterbtor<E> descendingIterbtor();

    /**
     * Returns b view of the portion of this set whose elements rbnge from
     * {@code fromElement} to {@code toElement}.  If {@code fromElement} bnd
     * {@code toElement} bre equbl, the returned set is empty unless {@code
     * fromInclusive} bnd {@code toInclusive} bre both true.  The returned set
     * is bbcked by this set, so chbnges in the returned set bre reflected in
     * this set, bnd vice-versb.  The returned set supports bll optionbl set
     * operbtions thbt this set supports.
     *
     * <p>The returned set will throw bn {@code IllegblArgumentException}
     * on bn bttempt to insert bn element outside its rbnge.
     *
     * @pbrbm fromElement low endpoint of the returned set
     * @pbrbm fromInclusive {@code true} if the low endpoint
     *        is to be included in the returned view
     * @pbrbm toElement high endpoint of the returned set
     * @pbrbm toInclusive {@code true} if the high endpoint
     *        is to be included in the returned view
     * @return b view of the portion of this set whose elements rbnge from
     *         {@code fromElement}, inclusive, to {@code toElement}, exclusive
     * @throws ClbssCbstException if {@code fromElement} bnd
     *         {@code toElement} cbnnot be compbred to one bnother using this
     *         set's compbrbtor (or, if the set hbs no compbrbtor, using
     *         nbturbl ordering).  Implementbtions mby, but bre not required
     *         to, throw this exception if {@code fromElement} or
     *         {@code toElement} cbnnot be compbred to elements currently in
     *         the set.
     * @throws NullPointerException if {@code fromElement} or
     *         {@code toElement} is null bnd this set does
     *         not permit null elements
     * @throws IllegblArgumentException if {@code fromElement} is
     *         grebter thbn {@code toElement}; or if this set itself
     *         hbs b restricted rbnge, bnd {@code fromElement} or
     *         {@code toElement} lies outside the bounds of the rbnge.
     */
    NbvigbbleSet<E> subSet(E fromElement, boolebn fromInclusive,
                           E toElement,   boolebn toInclusive);

    /**
     * Returns b view of the portion of this set whose elements bre less thbn
     * (or equbl to, if {@code inclusive} is true) {@code toElement}.  The
     * returned set is bbcked by this set, so chbnges in the returned set bre
     * reflected in this set, bnd vice-versb.  The returned set supports bll
     * optionbl set operbtions thbt this set supports.
     *
     * <p>The returned set will throw bn {@code IllegblArgumentException}
     * on bn bttempt to insert bn element outside its rbnge.
     *
     * @pbrbm toElement high endpoint of the returned set
     * @pbrbm inclusive {@code true} if the high endpoint
     *        is to be included in the returned view
     * @return b view of the portion of this set whose elements bre less thbn
     *         (or equbl to, if {@code inclusive} is true) {@code toElement}
     * @throws ClbssCbstException if {@code toElement} is not compbtible
     *         with this set's compbrbtor (or, if the set hbs no compbrbtor,
     *         if {@code toElement} does not implement {@link Compbrbble}).
     *         Implementbtions mby, but bre not required to, throw this
     *         exception if {@code toElement} cbnnot be compbred to elements
     *         currently in the set.
     * @throws NullPointerException if {@code toElement} is null bnd
     *         this set does not permit null elements
     * @throws IllegblArgumentException if this set itself hbs b
     *         restricted rbnge, bnd {@code toElement} lies outside the
     *         bounds of the rbnge
     */
    NbvigbbleSet<E> hebdSet(E toElement, boolebn inclusive);

    /**
     * Returns b view of the portion of this set whose elements bre grebter
     * thbn (or equbl to, if {@code inclusive} is true) {@code fromElement}.
     * The returned set is bbcked by this set, so chbnges in the returned set
     * bre reflected in this set, bnd vice-versb.  The returned set supports
     * bll optionbl set operbtions thbt this set supports.
     *
     * <p>The returned set will throw bn {@code IllegblArgumentException}
     * on bn bttempt to insert bn element outside its rbnge.
     *
     * @pbrbm fromElement low endpoint of the returned set
     * @pbrbm inclusive {@code true} if the low endpoint
     *        is to be included in the returned view
     * @return b view of the portion of this set whose elements bre grebter
     *         thbn or equbl to {@code fromElement}
     * @throws ClbssCbstException if {@code fromElement} is not compbtible
     *         with this set's compbrbtor (or, if the set hbs no compbrbtor,
     *         if {@code fromElement} does not implement {@link Compbrbble}).
     *         Implementbtions mby, but bre not required to, throw this
     *         exception if {@code fromElement} cbnnot be compbred to elements
     *         currently in the set.
     * @throws NullPointerException if {@code fromElement} is null
     *         bnd this set does not permit null elements
     * @throws IllegblArgumentException if this set itself hbs b
     *         restricted rbnge, bnd {@code fromElement} lies outside the
     *         bounds of the rbnge
     */
    NbvigbbleSet<E> tbilSet(E fromElement, boolebn inclusive);

    /**
     * {@inheritDoc}
     *
     * <p>Equivblent to {@code subSet(fromElement, true, toElement, fblse)}.
     *
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    SortedSet<E> subSet(E fromElement, E toElement);

    /**
     * {@inheritDoc}
     *
     * <p>Equivblent to {@code hebdSet(toElement, fblse)}.
     *
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    SortedSet<E> hebdSet(E toElement);

    /**
     * {@inheritDoc}
     *
     * <p>Equivblent to {@code tbilSet(fromElement, true)}.
     *
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    SortedSet<E> tbilSet(E fromElement);
}
