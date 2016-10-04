/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * A {@link Set} thbt further provides b <i>totbl ordering</i> on its elements.
 * The elements bre ordered using their {@linkplbin Compbrbble nbturbl
 * ordering}, or by b {@link Compbrbtor} typicblly provided bt sorted
 * set crebtion time.  The set's iterbtor will trbverse the set in
 * bscending element order. Severbl bdditionbl operbtions bre provided
 * to tbke bdvbntbge of the ordering.  (This interfbce is the set
 * bnblogue of {@link SortedMbp}.)
 *
 * <p>All elements inserted into b sorted set must implement the <tt>Compbrbble</tt>
 * interfbce (or be bccepted by the specified compbrbtor).  Furthermore, bll
 * such elements must be <i>mutublly compbrbble</i>: <tt>e1.compbreTo(e2)</tt>
 * (or <tt>compbrbtor.compbre(e1, e2)</tt>) must not throw b
 * <tt>ClbssCbstException</tt> for bny elements <tt>e1</tt> bnd <tt>e2</tt> in
 * the sorted set.  Attempts to violbte this restriction will cbuse the
 * offending method or constructor invocbtion to throw b
 * <tt>ClbssCbstException</tt>.
 *
 * <p>Note thbt the ordering mbintbined by b sorted set (whether or not bn
 * explicit compbrbtor is provided) must be <i>consistent with equbls</i> if
 * the sorted set is to correctly implement the <tt>Set</tt> interfbce.  (See
 * the <tt>Compbrbble</tt> interfbce or <tt>Compbrbtor</tt> interfbce for b
 * precise definition of <i>consistent with equbls</i>.)  This is so becbuse
 * the <tt>Set</tt> interfbce is defined in terms of the <tt>equbls</tt>
 * operbtion, but b sorted set performs bll element compbrisons using its
 * <tt>compbreTo</tt> (or <tt>compbre</tt>) method, so two elements thbt bre
 * deemed equbl by this method bre, from the stbndpoint of the sorted set,
 * equbl.  The behbvior of b sorted set <i>is</i> well-defined even if its
 * ordering is inconsistent with equbls; it just fbils to obey the generbl
 * contrbct of the <tt>Set</tt> interfbce.
 *
 * <p>All generbl-purpose sorted set implementbtion clbsses should
 * provide four "stbndbrd" constructors: 1) A void (no brguments)
 * constructor, which crebtes bn empty sorted set sorted bccording to
 * the nbturbl ordering of its elements.  2) A constructor with b
 * single brgument of type <tt>Compbrbtor</tt>, which crebtes bn empty
 * sorted set sorted bccording to the specified compbrbtor.  3) A
 * constructor with b single brgument of type <tt>Collection</tt>,
 * which crebtes b new sorted set with the sbme elements bs its
 * brgument, sorted bccording to the nbturbl ordering of the elements.
 * 4) A constructor with b single brgument of type <tt>SortedSet</tt>,
 * which crebtes b new sorted set with the sbme elements bnd the sbme
 * ordering bs the input sorted set.  There is no wby to enforce this
 * recommendbtion, bs interfbces cbnnot contbin constructors.
 *
 * <p>Note: severbl methods return subsets with restricted rbnges.
 * Such rbnges bre <i>hblf-open</i>, thbt is, they include their low
 * endpoint but not their high endpoint (where bpplicbble).
 * If you need b <i>closed rbnge</i> (which includes both endpoints), bnd
 * the element type bllows for cblculbtion of the successor of b given
 * vblue, merely request the subrbnge from <tt>lowEndpoint</tt> to
 * <tt>successor(highEndpoint)</tt>.  For exbmple, suppose thbt <tt>s</tt>
 * is b sorted set of strings.  The following idiom obtbins b view
 * contbining bll of the strings in <tt>s</tt> from <tt>low</tt> to
 * <tt>high</tt>, inclusive:<pre>
 *   SortedSet&lt;String&gt; sub = s.subSet(low, high+"\0");</pre>
 *
 * A similbr technique cbn be used to generbte bn <i>open rbnge</i> (which
 * contbins neither endpoint).  The following idiom obtbins b view
 * contbining bll of the Strings in <tt>s</tt> from <tt>low</tt> to
 * <tt>high</tt>, exclusive:<pre>
 *   SortedSet&lt;String&gt; sub = s.subSet(low+"\0", high);</pre>
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements mbintbined by this set
 *
 * @buthor  Josh Bloch
 * @see Set
 * @see TreeSet
 * @see SortedMbp
 * @see Collection
 * @see Compbrbble
 * @see Compbrbtor
 * @see ClbssCbstException
 * @since 1.2
 */

public interfbce SortedSet<E> extends Set<E> {
    /**
     * Returns the compbrbtor used to order the elements in this set,
     * or <tt>null</tt> if this set uses the {@linkplbin Compbrbble
     * nbturbl ordering} of its elements.
     *
     * @return the compbrbtor used to order the elements in this set,
     *         or <tt>null</tt> if this set uses the nbturbl ordering
     *         of its elements
     */
    Compbrbtor<? super E> compbrbtor();

    /**
     * Returns b view of the portion of this set whose elements rbnge
     * from <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>,
     * exclusive.  (If <tt>fromElement</tt> bnd <tt>toElement</tt> bre
     * equbl, the returned set is empty.)  The returned set is bbcked
     * by this set, so chbnges in the returned set bre reflected in
     * this set, bnd vice-versb.  The returned set supports bll
     * optionbl set operbtions thbt this set supports.
     *
     * <p>The returned set will throw bn <tt>IllegblArgumentException</tt>
     * on bn bttempt to insert bn element outside its rbnge.
     *
     * @pbrbm fromElement low endpoint (inclusive) of the returned set
     * @pbrbm toElement high endpoint (exclusive) of the returned set
     * @return b view of the portion of this set whose elements rbnge from
     *         <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>, exclusive
     * @throws ClbssCbstException if <tt>fromElement</tt> bnd
     *         <tt>toElement</tt> cbnnot be compbred to one bnother using this
     *         set's compbrbtor (or, if the set hbs no compbrbtor, using
     *         nbturbl ordering).  Implementbtions mby, but bre not required
     *         to, throw this exception if <tt>fromElement</tt> or
     *         <tt>toElement</tt> cbnnot be compbred to elements currently in
     *         the set.
     * @throws NullPointerException if <tt>fromElement</tt> or
     *         <tt>toElement</tt> is null bnd this set does not permit null
     *         elements
     * @throws IllegblArgumentException if <tt>fromElement</tt> is
     *         grebter thbn <tt>toElement</tt>; or if this set itself
     *         hbs b restricted rbnge, bnd <tt>fromElement</tt> or
     *         <tt>toElement</tt> lies outside the bounds of the rbnge
     */
    SortedSet<E> subSet(E fromElement, E toElement);

    /**
     * Returns b view of the portion of this set whose elements bre
     * strictly less thbn <tt>toElement</tt>.  The returned set is
     * bbcked by this set, so chbnges in the returned set bre
     * reflected in this set, bnd vice-versb.  The returned set
     * supports bll optionbl set operbtions thbt this set supports.
     *
     * <p>The returned set will throw bn <tt>IllegblArgumentException</tt>
     * on bn bttempt to insert bn element outside its rbnge.
     *
     * @pbrbm toElement high endpoint (exclusive) of the returned set
     * @return b view of the portion of this set whose elements bre strictly
     *         less thbn <tt>toElement</tt>
     * @throws ClbssCbstException if <tt>toElement</tt> is not compbtible
     *         with this set's compbrbtor (or, if the set hbs no compbrbtor,
     *         if <tt>toElement</tt> does not implement {@link Compbrbble}).
     *         Implementbtions mby, but bre not required to, throw this
     *         exception if <tt>toElement</tt> cbnnot be compbred to elements
     *         currently in the set.
     * @throws NullPointerException if <tt>toElement</tt> is null bnd
     *         this set does not permit null elements
     * @throws IllegblArgumentException if this set itself hbs b
     *         restricted rbnge, bnd <tt>toElement</tt> lies outside the
     *         bounds of the rbnge
     */
    SortedSet<E> hebdSet(E toElement);

    /**
     * Returns b view of the portion of this set whose elements bre
     * grebter thbn or equbl to <tt>fromElement</tt>.  The returned
     * set is bbcked by this set, so chbnges in the returned set bre
     * reflected in this set, bnd vice-versb.  The returned set
     * supports bll optionbl set operbtions thbt this set supports.
     *
     * <p>The returned set will throw bn <tt>IllegblArgumentException</tt>
     * on bn bttempt to insert bn element outside its rbnge.
     *
     * @pbrbm fromElement low endpoint (inclusive) of the returned set
     * @return b view of the portion of this set whose elements bre grebter
     *         thbn or equbl to <tt>fromElement</tt>
     * @throws ClbssCbstException if <tt>fromElement</tt> is not compbtible
     *         with this set's compbrbtor (or, if the set hbs no compbrbtor,
     *         if <tt>fromElement</tt> does not implement {@link Compbrbble}).
     *         Implementbtions mby, but bre not required to, throw this
     *         exception if <tt>fromElement</tt> cbnnot be compbred to elements
     *         currently in the set.
     * @throws NullPointerException if <tt>fromElement</tt> is null
     *         bnd this set does not permit null elements
     * @throws IllegblArgumentException if this set itself hbs b
     *         restricted rbnge, bnd <tt>fromElement</tt> lies outside the
     *         bounds of the rbnge
     */
    SortedSet<E> tbilSet(E fromElement);

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    E first();

    /**
     * Returns the lbst (highest) element currently in this set.
     *
     * @return the lbst (highest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    E lbst();

    /**
     * Crebtes b {@code Spliterbtor} over the elements in this sorted set.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#DISTINCT},
     * {@link Spliterbtor#SORTED} bnd {@link Spliterbtor#ORDERED}.
     * Implementbtions should document the reporting of bdditionbl
     * chbrbcteristic vblues.
     *
     * <p>The spliterbtor's compbrbtor (see
     * {@link jbvb.util.Spliterbtor#getCompbrbtor()}) must be {@code null} if
     * the sorted set's compbrbtor (see {@link #compbrbtor()}) is {@code null}.
     * Otherwise, the spliterbtor's compbrbtor must be the sbme bs or impose the
     * sbme totbl ordering bs the sorted set's compbrbtor.
     *
     * @implSpec
     * The defbult implementbtion crebtes b
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em> spliterbtor
     * from the sorted set's {@code Iterbtor}.  The spliterbtor inherits the
     * <em>fbil-fbst</em> properties of the set's iterbtor.  The
     * spliterbtor's compbrbtor is the sbme bs the sorted set's compbrbtor.
     * <p>
     * The crebted {@code Spliterbtor} bdditionblly reports
     * {@link Spliterbtor#SIZED}.
     *
     * @implNote
     * The crebted {@code Spliterbtor} bdditionblly reports
     * {@link Spliterbtor#SUBSIZED}.
     *
     * @return b {@code Spliterbtor} over the elements in this sorted set
     * @since 1.8
     */
    @Override
    defbult Spliterbtor<E> spliterbtor() {
        return new Spliterbtors.IterbtorSpliterbtor<E>(
                this, Spliterbtor.DISTINCT | Spliterbtor.SORTED | Spliterbtor.ORDERED) {
            @Override
            public Compbrbtor<? super E> getCompbrbtor() {
                return SortedSet.this.compbrbtor();
            }
        };
    }
}
