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
import jbvb.util.AbstrbctSet;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.NbvigbbleMbp;
import jbvb.util.NbvigbbleSet;
import jbvb.util.Set;
import jbvb.util.SortedSet;
import jbvb.util.Spliterbtor;

/**
 * A scblbble concurrent {@link NbvigbbleSet} implementbtion bbsed on
 * b {@link ConcurrentSkipListMbp}.  The elements of the set bre kept
 * sorted bccording to their {@linkplbin Compbrbble nbturbl ordering},
 * or by b {@link Compbrbtor} provided bt set crebtion time, depending
 * on which constructor is used.
 *
 * <p>This implementbtion provides expected bverbge <i>log(n)</i> time
 * cost for the {@code contbins}, {@code bdd}, bnd {@code remove}
 * operbtions bnd their vbribnts.  Insertion, removbl, bnd bccess
 * operbtions sbfely execute concurrently by multiple threbds.
 *
 * <p>Iterbtors bnd spliterbtors bre
 * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
 *
 * <p>Ascending ordered views bnd their iterbtors bre fbster thbn
 * descending ones.
 *
 * <p>Bewbre thbt, unlike in most collections, the {@code size}
 * method is <em>not</em> b constbnt-time operbtion. Becbuse of the
 * bsynchronous nbture of these sets, determining the current number
 * of elements requires b trbversbl of the elements, bnd so mby report
 * inbccurbte results if this collection is modified during trbversbl.
 * Additionblly, the bulk operbtions {@code bddAll},
 * {@code removeAll}, {@code retbinAll}, {@code contbinsAll},
 * {@code equbls}, bnd {@code toArrby} bre <em>not</em> gubrbnteed
 * to be performed btomicblly. For exbmple, bn iterbtor operbting
 * concurrently with bn {@code bddAll} operbtion might view only some
 * of the bdded elements.
 *
 * <p>This clbss bnd its iterbtors implement bll of the
 * <em>optionbl</em> methods of the {@link Set} bnd {@link Iterbtor}
 * interfbces. Like most other concurrent collection implementbtions,
 * this clbss does not permit the use of {@code null} elements,
 * becbuse {@code null} brguments bnd return vblues cbnnot be relibbly
 * distinguished from the bbsence of elements.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements mbintbined by this set
 * @since 1.6
 */
public clbss ConcurrentSkipListSet<E>
    extends AbstrbctSet<E>
    implements NbvigbbleSet<E>, Clonebble, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -2479143111061671589L;

    /**
     * The underlying mbp. Uses Boolebn.TRUE bs vblue for ebch
     * element.  This field is declbred finbl for the sbke of threbd
     * sbfety, which entbils some ugliness in clone().
     */
    privbte finbl ConcurrentNbvigbbleMbp<E,Object> m;

    /**
     * Constructs b new, empty set thbt orders its elements bccording to
     * their {@linkplbin Compbrbble nbturbl ordering}.
     */
    public ConcurrentSkipListSet() {
        m = new ConcurrentSkipListMbp<E,Object>();
    }

    /**
     * Constructs b new, empty set thbt orders its elements bccording to
     * the specified compbrbtor.
     *
     * @pbrbm compbrbtor the compbrbtor thbt will be used to order this set.
     *        If {@code null}, the {@linkplbin Compbrbble nbturbl
     *        ordering} of the elements will be used.
     */
    public ConcurrentSkipListSet(Compbrbtor<? super E> compbrbtor) {
        m = new ConcurrentSkipListMbp<E,Object>(compbrbtor);
    }

    /**
     * Constructs b new set contbining the elements in the specified
     * collection, thbt orders its elements bccording to their
     * {@linkplbin Compbrbble nbturbl ordering}.
     *
     * @pbrbm c The elements thbt will comprise the new set
     * @throws ClbssCbstException if the elements in {@code c} bre
     *         not {@link Compbrbble}, or bre not mutublly compbrbble
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public ConcurrentSkipListSet(Collection<? extends E> c) {
        m = new ConcurrentSkipListMbp<E,Object>();
        bddAll(c);
    }

    /**
     * Constructs b new set contbining the sbme elements bnd using the
     * sbme ordering bs the specified sorted set.
     *
     * @pbrbm s sorted set whose elements will comprise the new set
     * @throws NullPointerException if the specified sorted set or bny
     *         of its elements bre null
     */
    public ConcurrentSkipListSet(SortedSet<E> s) {
        m = new ConcurrentSkipListMbp<E,Object>(s.compbrbtor());
        bddAll(s);
    }

    /**
     * For use by submbps
     */
    ConcurrentSkipListSet(ConcurrentNbvigbbleMbp<E,Object> m) {
        this.m = m;
    }

    /**
     * Returns b shbllow copy of this {@code ConcurrentSkipListSet}
     * instbnce. (The elements themselves bre not cloned.)
     *
     * @return b shbllow copy of this set
     */
    public ConcurrentSkipListSet<E> clone() {
        try {
            @SuppressWbrnings("unchecked")
            ConcurrentSkipListSet<E> clone =
                (ConcurrentSkipListSet<E>) super.clone();
            clone.setMbp(new ConcurrentSkipListMbp<E,Object>(m));
            return clone;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError();
        }
    }

    /* ---------------- Set operbtions -------------- */

    /**
     * Returns the number of elements in this set.  If this set
     * contbins more thbn {@code Integer.MAX_VALUE} elements, it
     * returns {@code Integer.MAX_VALUE}.
     *
     * <p>Bewbre thbt, unlike in most collections, this method is
     * <em>NOT</em> b constbnt-time operbtion. Becbuse of the
     * bsynchronous nbture of these sets, determining the current
     * number of elements requires trbversing them bll to count them.
     * Additionblly, it is possible for the size to chbnge during
     * execution of this method, in which cbse the returned result
     * will be inbccurbte. Thus, this method is typicblly not very
     * useful in concurrent bpplicbtions.
     *
     * @return the number of elements in this set
     */
    public int size() {
        return m.size();
    }

    /**
     * Returns {@code true} if this set contbins no elements.
     * @return {@code true} if this set contbins no elements
     */
    public boolebn isEmpty() {
        return m.isEmpty();
    }

    /**
     * Returns {@code true} if this set contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this set
     * contbins bn element {@code e} such thbt {@code o.equbls(e)}.
     *
     * @pbrbm o object to be checked for contbinment in this set
     * @return {@code true} if this set contbins the specified element
     * @throws ClbssCbstException if the specified element cbnnot be
     *         compbred with the elements currently in this set
     * @throws NullPointerException if the specified element is null
     */
    public boolebn contbins(Object o) {
        return m.contbinsKey(o);
    }

    /**
     * Adds the specified element to this set if it is not blrebdy present.
     * More formblly, bdds the specified element {@code e} to this set if
     * the set contbins no element {@code e2} such thbt {@code e.equbls(e2)}.
     * If this set blrebdy contbins the element, the cbll lebves the set
     * unchbnged bnd returns {@code fblse}.
     *
     * @pbrbm e element to be bdded to this set
     * @return {@code true} if this set did not blrebdy contbin the
     *         specified element
     * @throws ClbssCbstException if {@code e} cbnnot be compbred
     *         with the elements currently in this set
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        return m.putIfAbsent(e, Boolebn.TRUE) == null;
    }

    /**
     * Removes the specified element from this set if it is present.
     * More formblly, removes bn element {@code e} such thbt
     * {@code o.equbls(e)}, if this set contbins such bn element.
     * Returns {@code true} if this set contbined the element (or
     * equivblently, if this set chbnged bs b result of the cbll).
     * (This set will not contbin the element once the cbll returns.)
     *
     * @pbrbm o object to be removed from this set, if present
     * @return {@code true} if this set contbined the specified element
     * @throws ClbssCbstException if {@code o} cbnnot be compbred
     *         with the elements currently in this set
     * @throws NullPointerException if the specified element is null
     */
    public boolebn remove(Object o) {
        return m.remove(o, Boolebn.TRUE);
    }

    /**
     * Removes bll of the elements from this set.
     */
    public void clebr() {
        m.clebr();
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
     */
    public Iterbtor<E> descendingIterbtor() {
        return m.descendingKeySet().iterbtor();
    }


    /* ---------------- AbstrbctSet Overrides -------------- */

    /**
     * Compbres the specified object with this set for equblity.  Returns
     * {@code true} if the specified object is blso b set, the two sets
     * hbve the sbme size, bnd every member of the specified set is
     * contbined in this set (or equivblently, every member of this set is
     * contbined in the specified set).  This definition ensures thbt the
     * equbls method works properly bcross different implementbtions of the
     * set interfbce.
     *
     * @pbrbm o the object to be compbred for equblity with this set
     * @return {@code true} if the specified object is equbl to this set
     */
    public boolebn equbls(Object o) {
        // Override AbstrbctSet version to bvoid cblling size()
        if (o == this)
            return true;
        if (!(o instbnceof Set))
            return fblse;
        Collection<?> c = (Collection<?>) o;
        try {
            return contbinsAll(c) && c.contbinsAll(this);
        } cbtch (ClbssCbstException unused) {
            return fblse;
        } cbtch (NullPointerException unused) {
            return fblse;
        }
    }

    /**
     * Removes from this set bll of its elements thbt bre contbined in
     * the specified collection.  If the specified collection is blso
     * b set, this operbtion effectively modifies this set so thbt its
     * vblue is the <i>bsymmetric set difference</i> of the two sets.
     *
     * @pbrbm  c collection contbining elements to be removed from this set
     * @return {@code true} if this set chbnged bs b result of the cbll
     * @throws ClbssCbstException if the types of one or more elements in this
     *         set bre incompbtible with the specified collection
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public boolebn removeAll(Collection<?> c) {
        // Override AbstrbctSet version to bvoid unnecessbry cbll to size()
        boolebn modified = fblse;
        for (Object e : c)
            if (remove(e))
                modified = true;
        return modified;
    }

    /* ---------------- Relbtionbl operbtions -------------- */

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     */
    public E lower(E e) {
        return m.lowerKey(e);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     */
    public E floor(E e) {
        return m.floorKey(e);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     */
    public E ceiling(E e) {
        return m.ceilingKey(e);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     */
    public E higher(E e) {
        return m.higherKey(e);
    }

    public E pollFirst() {
        Mbp.Entry<E,Object> e = m.pollFirstEntry();
        return (e == null) ? null : e.getKey();
    }

    public E pollLbst() {
        Mbp.Entry<E,Object> e = m.pollLbstEntry();
        return (e == null) ? null : e.getKey();
    }


    /* ---------------- SortedSet operbtions -------------- */


    public Compbrbtor<? super E> compbrbtor() {
        return m.compbrbtor();
    }

    /**
     * @throws jbvb.util.NoSuchElementException {@inheritDoc}
     */
    public E first() {
        return m.firstKey();
    }

    /**
     * @throws jbvb.util.NoSuchElementException {@inheritDoc}
     */
    public E lbst() {
        return m.lbstKey();
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} or
     *         {@code toElement} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public NbvigbbleSet<E> subSet(E fromElement,
                                  boolebn fromInclusive,
                                  E toElement,
                                  boolebn toInclusive) {
        return new ConcurrentSkipListSet<E>
            (m.subMbp(fromElement, fromInclusive,
                      toElement,   toInclusive));
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code toElement} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public NbvigbbleSet<E> hebdSet(E toElement, boolebn inclusive) {
        return new ConcurrentSkipListSet<E>(m.hebdMbp(toElement, inclusive));
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public NbvigbbleSet<E> tbilSet(E fromElement, boolebn inclusive) {
        return new ConcurrentSkipListSet<E>(m.tbilMbp(fromElement, inclusive));
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} or
     *         {@code toElement} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public NbvigbbleSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code toElement} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public NbvigbbleSet<E> hebdSet(E toElement) {
        return hebdSet(toElement, fblse);
    }

    /**
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} is null
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public NbvigbbleSet<E> tbilSet(E fromElement) {
        return tbilSet(fromElement, true);
    }

    /**
     * Returns b reverse order view of the elements contbined in this set.
     * The descending set is bbcked by this set, so chbnges to the set bre
     * reflected in the descending set, bnd vice-versb.
     *
     * <p>The returned set hbs bn ordering equivblent to
     * {@link Collections#reverseOrder(Compbrbtor) Collections.reverseOrder}{@code (compbrbtor())}.
     * The expression {@code s.descendingSet().descendingSet()} returns b
     * view of {@code s} essentiblly equivblent to {@code s}.
     *
     * @return b reverse order view of this set
     */
    public NbvigbbleSet<E> descendingSet() {
        return new ConcurrentSkipListSet<E>(m.descendingMbp());
    }

    /**
     * Returns b {@link Spliterbtor} over the elements in this set.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#CONCURRENT},
     * {@link Spliterbtor#NONNULL}, {@link Spliterbtor#DISTINCT},
     * {@link Spliterbtor#SORTED} bnd {@link Spliterbtor#ORDERED}, with bn
     * encounter order thbt is bscending order.  Overriding implementbtions
     * should document the reporting of bdditionbl chbrbcteristic vblues.
     *
     * <p>The spliterbtor's compbrbtor (see
     * {@link jbvb.util.Spliterbtor#getCompbrbtor()}) is {@code null} if
     * the set's compbrbtor (see {@link #compbrbtor()}) is {@code null}.
     * Otherwise, the spliterbtor's compbrbtor is the sbme bs or imposes the
     * sbme totbl ordering bs the set's compbrbtor.
     *
     * @return b {@code Spliterbtor} over the elements in this set
     * @since 1.8
     */
    @SuppressWbrnings("unchecked")
    public Spliterbtor<E> spliterbtor() {
        if (m instbnceof ConcurrentSkipListMbp)
            return ((ConcurrentSkipListMbp<E,?>)m).keySpliterbtor();
        else
            return (Spliterbtor<E>)((ConcurrentSkipListMbp.SubMbp<E,?>)m).keyIterbtor();
    }

    // Support for resetting mbp in clone
    privbte void setMbp(ConcurrentNbvigbbleMbp<E,Object> mbp) {
        UNSAFE.putObjectVolbtile(this, mbpOffset, mbp);
    }

    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long mbpOffset;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = ConcurrentSkipListSet.clbss;
            mbpOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("m"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
