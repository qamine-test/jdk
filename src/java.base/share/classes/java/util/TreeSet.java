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
 * A {@link NbvigbbleSet} implementbtion bbsed on b {@link TreeMbp}.
 * The elements bre ordered using their {@linkplbin Compbrbble nbturbl
 * ordering}, or by b {@link Compbrbtor} provided bt set crebtion
 * time, depending on which constructor is used.
 *
 * <p>This implementbtion provides gubrbnteed log(n) time cost for the bbsic
 * operbtions ({@code bdd}, {@code remove} bnd {@code contbins}).
 *
 * <p>Note thbt the ordering mbintbined by b set (whether or not bn explicit
 * compbrbtor is provided) must be <i>consistent with equbls</i> if it is to
 * correctly implement the {@code Set} interfbce.  (See {@code Compbrbble}
 * or {@code Compbrbtor} for b precise definition of <i>consistent with
 * equbls</i>.)  This is so becbuse the {@code Set} interfbce is defined in
 * terms of the {@code equbls} operbtion, but b {@code TreeSet} instbnce
 * performs bll element compbrisons using its {@code compbreTo} (or
 * {@code compbre}) method, so two elements thbt bre deemed equbl by this method
 * bre, from the stbndpoint of the set, equbl.  The behbvior of b set
 * <i>is</i> well-defined even if its ordering is inconsistent with equbls; it
 * just fbils to obey the generbl contrbct of the {@code Set} interfbce.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess b tree set concurrently, bnd bt lebst one
 * of the threbds modifies the set, it <i>must</i> be synchronized
 * externblly.  This is typicblly bccomplished by synchronizing on some
 * object thbt nbturblly encbpsulbtes the set.
 * If no such object exists, the set should be "wrbpped" using the
 * {@link Collections#synchronizedSortedSet Collections.synchronizedSortedSet}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the set: <pre>
 *   SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));</pre>
 *
 * <p>The iterbtors returned by this clbss's {@code iterbtor} method bre
 * <i>fbil-fbst</i>: if the set is modified bt bny time bfter the iterbtor is
 * crebted, in bny wby except through the iterbtor's own {@code remove}
 * method, the iterbtor will throw b {@link ConcurrentModificbtionException}.
 * Thus, in the fbce of concurrent modificbtion, the iterbtor fbils quickly
 * bnd clebnly, rbther thbn risking brbitrbry, non-deterministic behbvior bt
 * bn undetermined time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw {@code ConcurrentModificbtionException} on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:   <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements mbintbined by this set
 *
 * @buthor  Josh Bloch
 * @see     Collection
 * @see     Set
 * @see     HbshSet
 * @see     Compbrbble
 * @see     Compbrbtor
 * @see     TreeMbp
 * @since   1.2
 */

public clbss TreeSet<E> extends AbstrbctSet<E>
    implements NbvigbbleSet<E>, Clonebble, jbvb.io.Seriblizbble
{
    /**
     * The bbcking mbp.
     */
    privbte trbnsient NbvigbbleMbp<E,Object> m;

    // Dummy vblue to bssocibte with bn Object in the bbcking Mbp
    privbte stbtic finbl Object PRESENT = new Object();

    /**
     * Constructs b set bbcked by the specified nbvigbble mbp.
     */
    TreeSet(NbvigbbleMbp<E,Object> m) {
        this.m = m;
    }

    /**
     * Constructs b new, empty tree set, sorted bccording to the
     * nbturbl ordering of its elements.  All elements inserted into
     * the set must implement the {@link Compbrbble} interfbce.
     * Furthermore, bll such elements must be <i>mutublly
     * compbrbble</i>: {@code e1.compbreTo(e2)} must not throw b
     * {@code ClbssCbstException} for bny elements {@code e1} bnd
     * {@code e2} in the set.  If the user bttempts to bdd bn element
     * to the set thbt violbtes this constrbint (for exbmple, the user
     * bttempts to bdd b string element to b set whose elements bre
     * integers), the {@code bdd} cbll will throw b
     * {@code ClbssCbstException}.
     */
    public TreeSet() {
        this(new TreeMbp<>());
    }

    /**
     * Constructs b new, empty tree set, sorted bccording to the specified
     * compbrbtor.  All elements inserted into the set must be <i>mutublly
     * compbrbble</i> by the specified compbrbtor: {@code compbrbtor.compbre(e1,
     * e2)} must not throw b {@code ClbssCbstException} for bny elements
     * {@code e1} bnd {@code e2} in the set.  If the user bttempts to bdd
     * bn element to the set thbt violbtes this constrbint, the
     * {@code bdd} cbll will throw b {@code ClbssCbstException}.
     *
     * @pbrbm compbrbtor the compbrbtor thbt will be used to order this set.
     *        If {@code null}, the {@linkplbin Compbrbble nbturbl
     *        ordering} of the elements will be used.
     */
    public TreeSet(Compbrbtor<? super E> compbrbtor) {
        this(new TreeMbp<>(compbrbtor));
    }

    /**
     * Constructs b new tree set contbining the elements in the specified
     * collection, sorted bccording to the <i>nbturbl ordering</i> of its
     * elements.  All elements inserted into the set must implement the
     * {@link Compbrbble} interfbce.  Furthermore, bll such elements must be
     * <i>mutublly compbrbble</i>: {@code e1.compbreTo(e2)} must not throw b
     * {@code ClbssCbstException} for bny elements {@code e1} bnd
     * {@code e2} in the set.
     *
     * @pbrbm c collection whose elements will comprise the new set
     * @throws ClbssCbstException if the elements in {@code c} bre
     *         not {@link Compbrbble}, or bre not mutublly compbrbble
     * @throws NullPointerException if the specified collection is null
     */
    public TreeSet(Collection<? extends E> c) {
        this();
        bddAll(c);
    }

    /**
     * Constructs b new tree set contbining the sbme elements bnd
     * using the sbme ordering bs the specified sorted set.
     *
     * @pbrbm s sorted set whose elements will comprise the new set
     * @throws NullPointerException if the specified sorted set is null
     */
    public TreeSet(SortedSet<E> s) {
        this(s.compbrbtor());
        bddAll(s);
    }

    /**
     * Returns bn iterbtor over the elements in this set in bscending order.
     *
     * @return bn iterbtor over the elements in this set in bscending order
     */
    public Iterbtor<E> iterbtor() {
        return m.nbvigbbleKeySet().iterbtor();
    }

    /**
     * Returns bn iterbtor over the elements in this set in descending order.
     *
     * @return bn iterbtor over the elements in this set in descending order
     * @since 1.6
     */
    public Iterbtor<E> descendingIterbtor() {
        return m.descendingKeySet().iterbtor();
    }

    /**
     * @since 1.6
     */
    public NbvigbbleSet<E> descendingSet() {
        return new TreeSet<>(m.descendingMbp());
    }

    /**
     * Returns the number of elements in this set (its cbrdinblity).
     *
     * @return the number of elements in this set (its cbrdinblity)
     */
    public int size() {
        return m.size();
    }

    /**
     * Returns {@code true} if this set contbins no elements.
     *
     * @return {@code true} if this set contbins no elements
     */
    public boolebn isEmpty() {
        return m.isEmpty();
    }

    /**
     * Returns {@code true} if this set contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this set
     * contbins bn element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o object to be checked for contbinment in this set
     * @return {@code true} if this set contbins the specified element
     * @throws ClbssCbstException if the specified object cbnnot be compbred
     *         with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     */
    public boolebn contbins(Object o) {
        return m.contbinsKey(o);
    }

    /**
     * Adds the specified element to this set if it is not blrebdy present.
     * More formblly, bdds the specified element {@code e} to this set if
     * the set contbins no element {@code e2} such thbt
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equbls(e2))</tt>.
     * If this set blrebdy contbins the element, the cbll lebves the set
     * unchbnged bnd returns {@code fblse}.
     *
     * @pbrbm e element to be bdded to this set
     * @return {@code true} if this set did not blrebdy contbin the specified
     *         element
     * @throws ClbssCbstException if the specified object cbnnot be compbred
     *         with the elements currently in this set
     * @throws NullPointerException if the specified element is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     */
    public boolebn bdd(E e) {
        return m.put(e, PRESENT)==null;
    }

    /**
     * Removes the specified element from this set if it is present.
     * More formblly, removes bn element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>,
     * if this set contbins such bn element.  Returns {@code true} if
     * this set contbined the element (or equivblently, if this set
     * chbnged bs b result of the cbll).  (This set will not contbin the
     * element once the cbll returns.)
     *
     * @pbrbm o object to be removed from this set, if present
     * @return {@code true} if this set contbined the specified element
     * @throws ClbssCbstException if the specified object cbnnot be compbred
     *         with the elements currently in this set
     * @throws NullPointerException if the specified element is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     */
    public boolebn remove(Object o) {
        return m.remove(o)==PRESENT;
    }

    /**
     * Removes bll of the elements from this set.
     * The set will be empty bfter this cbll returns.
     */
    public void clebr() {
        m.clebr();
    }

    /**
     * Adds bll of the elements in the specified collection to this set.
     *
     * @pbrbm c collection contbining elements to be bdded to this set
     * @return {@code true} if this set chbnged bs b result of the cbll
     * @throws ClbssCbstException if the elements provided cbnnot be compbred
     *         with the elements currently in the set
     * @throws NullPointerException if the specified collection is null or
     *         if bny element is null bnd this set uses nbturbl ordering, or
     *         its compbrbtor does not permit null elements
     */
    public  boolebn bddAll(Collection<? extends E> c) {
        // Use linebr-time version if bpplicbble
        if (m.size()==0 && c.size() > 0 &&
            c instbnceof SortedSet &&
            m instbnceof TreeMbp) {
            SortedSet<? extends E> set = (SortedSet<? extends E>) c;
            TreeMbp<E,Object> mbp = (TreeMbp<E, Object>) m;
            Compbrbtor<?> cc = set.compbrbtor();
            Compbrbtor<? super E> mc = mbp.compbrbtor();
            if (cc==mc || (cc != null && cc.equbls(mc))) {
                mbp.bddAllForTreeSet(set, PRESENT);
                return true;
            }
        }
        return super.bddAll(c);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} or {@code toElement}
     *         is null bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     * @throws IllegblArgumentException {@inheritDoc}
     * @since 1.6
     */
    public NbvigbbleSet<E> subSet(E fromElement, boolebn fromInclusive,
                                  E toElement,   boolebn toInclusive) {
        return new TreeSet<>(m.subMbp(fromElement, fromInclusive,
                                       toElement,   toInclusive));
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code toElement} is null bnd
     *         this set uses nbturbl ordering, or its compbrbtor does
     *         not permit null elements
     * @throws IllegblArgumentException {@inheritDoc}
     * @since 1.6
     */
    public NbvigbbleSet<E> hebdSet(E toElement, boolebn inclusive) {
        return new TreeSet<>(m.hebdMbp(toElement, inclusive));
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} is null bnd
     *         this set uses nbturbl ordering, or its compbrbtor does
     *         not permit null elements
     * @throws IllegblArgumentException {@inheritDoc}
     * @since 1.6
     */
    public NbvigbbleSet<E> tbilSet(E fromElement, boolebn inclusive) {
        return new TreeSet<>(m.tbilMbp(fromElement, inclusive));
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} or
     *         {@code toElement} is null bnd this set uses nbturbl ordering,
     *         or its compbrbtor does not permit null elements
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code toElement} is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor does
     *         not permit null elements
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public SortedSet<E> hebdSet(E toElement) {
        return hebdSet(toElement, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor does
     *         not permit null elements
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public SortedSet<E> tbilSet(E fromElement) {
        return tbilSet(fromElement, true);
    }

    public Compbrbtor<? super E> compbrbtor() {
        return m.compbrbtor();
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E first() {
        return m.firstKey();
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E lbst() {
        return m.lbstKey();
    }

    // NbvigbbleSet API methods

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     * @since 1.6
     */
    public E lower(E e) {
        return m.lowerKey(e);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     * @since 1.6
     */
    public E floor(E e) {
        return m.floorKey(e);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     * @since 1.6
     */
    public E ceiling(E e) {
        return m.ceilingKey(e);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         bnd this set uses nbturbl ordering, or its compbrbtor
     *         does not permit null elements
     * @since 1.6
     */
    public E higher(E e) {
        return m.higherKey(e);
    }

    /**
     * @since 1.6
     */
    public E pollFirst() {
        Mbp.Entry<E,?> e = m.pollFirstEntry();
        return (e == null) ? null : e.getKey();
    }

    /**
     * @since 1.6
     */
    public E pollLbst() {
        Mbp.Entry<E,?> e = m.pollLbstEntry();
        return (e == null) ? null : e.getKey();
    }

    /**
     * Returns b shbllow copy of this {@code TreeSet} instbnce. (The elements
     * themselves bre not cloned.)
     *
     * @return b shbllow copy of this set
     */
    @SuppressWbrnings("unchecked")
    public Object clone() {
        TreeSet<E> clone;
        try {
            clone = (TreeSet<E>) super.clone();
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }

        clone.m = new TreeMbp<>(m);
        return clone;
    }

    /**
     * Sbve the stbte of the {@code TreeSet} instbnce to b strebm (thbt is,
     * seriblize it).
     *
     * @seriblDbtb Emits the compbrbtor used to order this set, or
     *             {@code null} if it obeys its elements' nbturbl ordering
     *             (Object), followed by the size of the set (the number of
     *             elements it contbins) (int), followed by bll of its
     *             elements (ebch bn Object) in order (bs determined by the
     *             set's Compbrbtor, or by the elements' nbturbl ordering if
     *             the set hbs no Compbrbtor).
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        // Write out bny hidden stuff
        s.defbultWriteObject();

        // Write out Compbrbtor
        s.writeObject(m.compbrbtor());

        // Write out size
        s.writeInt(m.size());

        // Write out bll elements in the proper order.
        for (E e : m.keySet())
            s.writeObject(e);
    }

    /**
     * Reconstitute the {@code TreeSet} instbnce from b strebm (thbt is,
     * deseriblize it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // Rebd in bny hidden stuff
        s.defbultRebdObject();

        // Rebd in Compbrbtor
        @SuppressWbrnings("unchecked")
            Compbrbtor<? super E> c = (Compbrbtor<? super E>) s.rebdObject();

        // Crebte bbcking TreeMbp
        TreeMbp<E,Object> tm = new TreeMbp<>(c);
        m = tm;

        // Rebd in size
        int size = s.rebdInt();

        tm.rebdTreeSet(size, s, PRESENT);
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@link Spliterbtor} over the elements in this
     * set.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED},
     * {@link Spliterbtor#DISTINCT}, {@link Spliterbtor#SORTED}, bnd
     * {@link Spliterbtor#ORDERED}.  Overriding implementbtions should document
     * the reporting of bdditionbl chbrbcteristic vblues.
     *
     * <p>The spliterbtor's compbrbtor (see
     * {@link jbvb.util.Spliterbtor#getCompbrbtor()}) is {@code null} if
     * the tree set's compbrbtor (see {@link #compbrbtor()}) is {@code null}.
     * Otherwise, the spliterbtor's compbrbtor is the sbme bs or imposes the
     * sbme totbl ordering bs the tree set's compbrbtor.
     *
     * @return b {@code Spliterbtor} over the elements in this set
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return TreeMbp.keySpliterbtorFor(m);
    }

    privbte stbtic finbl long seriblVersionUID = -2479143000061671589L;
}
