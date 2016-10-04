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
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group.  Adbpted bnd relebsed, under explicit permission,
 * from JDK ArrbyList.jbvb which cbrries the following copyright:
 *
 * Copyright 1997 by Sun Microsystems, Inc.,
 * 901 Sbn Antonio Robd, Pblo Alto, Cblifornib, 94303, U.S.A.
 * All rights reserved.
 */

pbckbge jbvb.util.concurrent;
import jbvb.util.AbstrbctList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Compbrbtor;
import jbvb.util.ConcurrentModificbtionException;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Objects;
import jbvb.util.RbndomAccess;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.util.function.Consumer;
import jbvb.util.function.Predicbte;
import jbvb.util.function.UnbryOperbtor;

/**
 * A threbd-sbfe vbribnt of {@link jbvb.util.ArrbyList} in which bll mutbtive
 * operbtions ({@code bdd}, {@code set}, bnd so on) bre implemented by
 * mbking b fresh copy of the underlying brrby.
 *
 * <p>This is ordinbrily too costly, but mby be <em>more</em> efficient
 * thbn blternbtives when trbversbl operbtions vbstly outnumber
 * mutbtions, bnd is useful when you cbnnot or don't wbnt to
 * synchronize trbversbls, yet need to preclude interference bmong
 * concurrent threbds.  The "snbpshot" style iterbtor method uses b
 * reference to the stbte of the brrby bt the point thbt the iterbtor
 * wbs crebted. This brrby never chbnges during the lifetime of the
 * iterbtor, so interference is impossible bnd the iterbtor is
 * gubrbnteed not to throw {@code ConcurrentModificbtionException}.
 * The iterbtor will not reflect bdditions, removbls, or chbnges to
 * the list since the iterbtor wbs crebted.  Element-chbnging
 * operbtions on iterbtors themselves ({@code remove}, {@code set}, bnd
 * {@code bdd}) bre not supported. These methods throw
 * {@code UnsupportedOperbtionException}.
 *
 * <p>All elements bre permitted, including {@code null}.
 *
 * <p>Memory consistency effects: As with other concurrent
 * collections, bctions in b threbd prior to plbcing bn object into b
 * {@code CopyOnWriteArrbyList}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions subsequent to the bccess or removbl of thbt element from
 * the {@code CopyOnWriteArrbyList} in bnother threbd.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss CopyOnWriteArrbyList<E>
    implements List<E>, RbndomAccess, Clonebble, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 8673264195747942595L;

    /** The lock protecting bll mutbtors */
    finbl trbnsient ReentrbntLock lock = new ReentrbntLock();

    /** The brrby, bccessed only vib getArrby/setArrby. */
    privbte trbnsient volbtile Object[] brrby;

    /**
     * Gets the brrby.  Non-privbte so bs to blso be bccessible
     * from CopyOnWriteArrbySet clbss.
     */
    finbl Object[] getArrby() {
        return brrby;
    }

    /**
     * Sets the brrby.
     */
    finbl void setArrby(Object[] b) {
        brrby = b;
    }

    /**
     * Crebtes bn empty list.
     */
    public CopyOnWriteArrbyList() {
        setArrby(new Object[0]);
    }

    /**
     * Crebtes b list contbining the elements of the specified
     * collection, in the order they bre returned by the collection's
     * iterbtor.
     *
     * @pbrbm c the collection of initiblly held elements
     * @throws NullPointerException if the specified collection is null
     */
    public CopyOnWriteArrbyList(Collection<? extends E> c) {
        Object[] elements;
        if (c.getClbss() == CopyOnWriteArrbyList.clbss)
            elements = ((CopyOnWriteArrbyList<?>)c).getArrby();
        else {
            elements = c.toArrby();
            // c.toArrby might (incorrectly) not return Object[] (see 6260652)
            if (elements.getClbss() != Object[].clbss)
                elements = Arrbys.copyOf(elements, elements.length, Object[].clbss);
        }
        setArrby(elements);
    }

    /**
     * Crebtes b list holding b copy of the given brrby.
     *
     * @pbrbm toCopyIn the brrby (b copy of this brrby is used bs the
     *        internbl brrby)
     * @throws NullPointerException if the specified brrby is null
     */
    public CopyOnWriteArrbyList(E[] toCopyIn) {
        setArrby(Arrbys.copyOf(toCopyIn, toCopyIn.length, Object[].clbss));
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return getArrby().length;
    }

    /**
     * Returns {@code true} if this list contbins no elements.
     *
     * @return {@code true} if this list contbins no elements
     */
    public boolebn isEmpty() {
        return size() == 0;
    }

    /**
     * Tests for equblity, coping with nulls.
     */
    privbte stbtic boolebn eq(Object o1, Object o2) {
        return (o1 == null) ? o2 == null : o1.equbls(o2);
    }

    /**
     * stbtic version of indexOf, to bllow repebted cblls without
     * needing to re-bcquire brrby ebch time.
     * @pbrbm o element to sebrch for
     * @pbrbm elements the brrby
     * @pbrbm index first index to sebrch
     * @pbrbm fence one pbst lbst index to sebrch
     * @return index of element, or -1 if bbsent
     */
    privbte stbtic int indexOf(Object o, Object[] elements,
                               int index, int fence) {
        if (o == null) {
            for (int i = index; i < fence; i++)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = index; i < fence; i++)
                if (o.equbls(elements[i]))
                    return i;
        }
        return -1;
    }

    /**
     * stbtic version of lbstIndexOf.
     * @pbrbm o element to sebrch for
     * @pbrbm elements the brrby
     * @pbrbm index first index to sebrch
     * @return index of element, or -1 if bbsent
     */
    privbte stbtic int lbstIndexOf(Object o, Object[] elements, int index) {
        if (o == null) {
            for (int i = index; i >= 0; i--)
                if (elements[i] == null)
                    return i;
        } else {
            for (int i = index; i >= 0; i--)
                if (o.equbls(elements[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns {@code true} if this list contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this list contbins
     * bt lebst one element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this list is to be tested
     * @return {@code true} if this list contbins the specified element
     */
    public boolebn contbins(Object o) {
        Object[] elements = getArrby();
        return indexOf(o, elements, 0, elements.length) >= 0;
    }

    /**
     * {@inheritDoc}
     */
    public int indexOf(Object o) {
        Object[] elements = getArrby();
        return indexOf(o, elements, 0, elements.length);
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, sebrching forwbrds from {@code index}, or returns -1 if
     * the element is not found.
     * More formblly, returns the lowest index {@code i} such thbt
     * <tt>(i&nbsp;&gt;=&nbsp;index&nbsp;&bmp;&bmp;&nbsp;(e==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;e.equbls(get(i))))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm e element to sebrch for
     * @pbrbm index index to stbrt sebrching from
     * @return the index of the first occurrence of the element in
     *         this list bt position {@code index} or lbter in the list;
     *         {@code -1} if the element is not found.
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     */
    public int indexOf(E e, int index) {
        Object[] elements = getArrby();
        return indexOf(e, elements, index, elements.length);
    }

    /**
     * {@inheritDoc}
     */
    public int lbstIndexOf(Object o) {
        Object[] elements = getArrby();
        return lbstIndexOf(o, elements, elements.length - 1);
    }

    /**
     * Returns the index of the lbst occurrence of the specified element in
     * this list, sebrching bbckwbrds from {@code index}, or returns -1 if
     * the element is not found.
     * More formblly, returns the highest index {@code i} such thbt
     * <tt>(i&nbsp;&lt;=&nbsp;index&nbsp;&bmp;&bmp;&nbsp;(e==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;e.equbls(get(i))))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm e element to sebrch for
     * @pbrbm index index to stbrt sebrching bbckwbrds from
     * @return the index of the lbst occurrence of the element bt position
     *         less thbn or equbl to {@code index} in this list;
     *         -1 if the element is not found.
     * @throws IndexOutOfBoundsException if the specified index is grebter
     *         thbn or equbl to the current size of this list
     */
    public int lbstIndexOf(E e, int index) {
        Object[] elements = getArrby();
        return lbstIndexOf(e, elements, index);
    }

    /**
     * Returns b shbllow copy of this list.  (The elements themselves
     * bre not copied.)
     *
     * @return b clone of this list
     */
    public Object clone() {
        try {
            @SuppressWbrnings("unchecked")
            CopyOnWriteArrbyList<E> clone =
                (CopyOnWriteArrbyList<E>) super.clone();
            clone.resetLock();
            return clone;
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError();
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this list
     * in proper sequence (from first to lbst element).
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this list.  (In other words, this method must bllocbte
     * b new brrby).  The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll the elements in this list
     */
    public Object[] toArrby() {
        Object[] elements = getArrby();
        return Arrbys.copyOf(elements, elements.length);
    }

    /**
     * Returns bn brrby contbining bll of the elements in this list in
     * proper sequence (from first to lbst element); the runtime type of
     * the returned brrby is thbt of the specified brrby.  If the list fits
     * in the specified brrby, it is returned therein.  Otherwise, b new
     * brrby is bllocbted with the runtime type of the specified brrby bnd
     * the size of this list.
     *
     * <p>If this list fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this list), the element in
     * the brrby immedibtely following the end of the list is set to
     * {@code null}.  (This is useful in determining the length of this
     * list <i>only</i> if the cbller knows thbt this list does not contbin
     * bny null elements.)
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose {@code x} is b list known to contbin only strings.
     * The following code cbn be used to dump the list into b newly
     * bllocbted brrby of {@code String}:
     *
     *  <pre> {@code String[] y = x.toArrby(new String[0]);}</pre>
     *
     * Note thbt {@code toArrby(new Object[0])} is identicbl in function to
     * {@code toArrby()}.
     *
     * @pbrbm b the brrby into which the elements of the list bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose.
     * @return bn brrby contbining bll the elements in this list
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this list
     * @throws NullPointerException if the specified brrby is null
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T b[]) {
        Object[] elements = getArrby();
        int len = elements.length;
        if (b.length < len)
            return (T[]) Arrbys.copyOf(elements, len, b.getClbss());
        else {
            System.brrbycopy(elements, 0, b, 0, len);
            if (b.length > len)
                b[len] = null;
            return b;
        }
    }

    // Positionbl Access Operbtions

    @SuppressWbrnings("unchecked")
    privbte E get(Object[] b, int index) {
        return (E) b[index];
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        return get(getArrby(), index);
    }

    /**
     * Replbces the element bt the specified position in this list with the
     * specified element.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            E oldVblue = get(elements, index);

            if (oldVblue != element) {
                int len = elements.length;
                Object[] newElements = Arrbys.copyOf(elements, len);
                newElements[index] = element;
                setArrby(newElements);
            } else {
                // Not quite b no-op; ensures volbtile write sembntics
                setArrby(elements);
            }
            return oldVblue;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @pbrbm e element to be bppended to this list
     * @return {@code true} (bs specified by {@link Collection#bdd})
     */
    public boolebn bdd(E e) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            Object[] newElements = Arrbys.copyOf(elements, len + 1);
            newElements[len] = e;
            setArrby(newElements);
            return true;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Inserts the specified element bt the specified position in this
     * list. Shifts the element currently bt thbt position (if bny) bnd
     * bny subsequent elements to the right (bdds one to their indices).
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void bdd(int index, E element) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            if (index > len || index < 0)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+len);
            Object[] newElements;
            int numMoved = len - index;
            if (numMoved == 0)
                newElements = Arrbys.copyOf(elements, len + 1);
            else {
                newElements = new Object[len + 1];
                System.brrbycopy(elements, 0, newElements, 0, index);
                System.brrbycopy(elements, index, newElements, index + 1,
                                 numMoved);
            }
            newElements[index] = element;
            setArrby(newElements);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Removes the element bt the specified position in this list.
     * Shifts bny subsequent elements to the left (subtrbcts one from their
     * indices).  Returns the element thbt wbs removed from the list.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            E oldVblue = get(elements, index);
            int numMoved = len - index - 1;
            if (numMoved == 0)
                setArrby(Arrbys.copyOf(elements, len - 1));
            else {
                Object[] newElements = new Object[len - 1];
                System.brrbycopy(elements, 0, newElements, 0, index);
                System.brrbycopy(elements, index + 1, newElements, index,
                                 numMoved);
                setArrby(newElements);
            }
            return oldVblue;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contbin the element, it is
     * unchbnged.  More formblly, removes the element with the lowest index
     * {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>
     * (if such bn element exists).  Returns {@code true} if this list
     * contbined the specified element (or equivblently, if this list
     * chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this list, if present
     * @return {@code true} if this list contbined the specified element
     */
    public boolebn remove(Object o) {
        Object[] snbpshot = getArrby();
        int index = indexOf(o, snbpshot, 0, snbpshot.length);
        return (index < 0) ? fblse : remove(o, snbpshot, index);
    }

    /**
     * A version of remove(Object) using the strong hint thbt given
     * recent snbpshot contbins o bt the given index.
     */
    privbte boolebn remove(Object o, Object[] snbpshot, int index) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] current = getArrby();
            int len = current.length;
            if (snbpshot != current) findIndex: {
                int prefix = Mbth.min(index, len);
                for (int i = 0; i < prefix; i++) {
                    if (current[i] != snbpshot[i] && eq(o, current[i])) {
                        index = i;
                        brebk findIndex;
                    }
                }
                if (index >= len)
                    return fblse;
                if (current[index] == o)
                    brebk findIndex;
                index = indexOf(o, current, index, len);
                if (index < 0)
                    return fblse;
            }
            Object[] newElements = new Object[len - 1];
            System.brrbycopy(current, 0, newElements, 0, index);
            System.brrbycopy(current, index + 1,
                             newElements, index,
                             len - index - 1);
            setArrby(newElements);
            return true;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Removes from this list bll of the elements whose index is between
     * {@code fromIndex}, inclusive, bnd {@code toIndex}, exclusive.
     * Shifts bny succeeding elements to the left (reduces their index).
     * This cbll shortens the list by {@code (toIndex - fromIndex)} elements.
     * (If {@code toIndex==fromIndex}, this operbtion hbs no effect.)
     *
     * @pbrbm fromIndex index of first element to be removed
     * @pbrbm toIndex index bfter lbst element to be removed
     * @throws IndexOutOfBoundsException if fromIndex or toIndex out of rbnge
     *         ({@code fromIndex < 0 || toIndex > size() || toIndex < fromIndex})
     */
    void removeRbnge(int fromIndex, int toIndex) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;

            if (fromIndex < 0 || toIndex > len || toIndex < fromIndex)
                throw new IndexOutOfBoundsException();
            int newlen = len - (toIndex - fromIndex);
            int numMoved = len - toIndex;
            if (numMoved == 0)
                setArrby(Arrbys.copyOf(elements, newlen));
            else {
                Object[] newElements = new Object[newlen];
                System.brrbycopy(elements, 0, newElements, 0, fromIndex);
                System.brrbycopy(elements, toIndex, newElements,
                                 fromIndex, numMoved);
                setArrby(newElements);
            }
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Appends the element, if not present.
     *
     * @pbrbm e element to be bdded to this list, if bbsent
     * @return {@code true} if the element wbs bdded
     */
    public boolebn bddIfAbsent(E e) {
        Object[] snbpshot = getArrby();
        return indexOf(e, snbpshot, 0, snbpshot.length) >= 0 ? fblse :
            bddIfAbsent(e, snbpshot);
    }

    /**
     * A version of bddIfAbsent using the strong hint thbt given
     * recent snbpshot does not contbin e.
     */
    privbte boolebn bddIfAbsent(E e, Object[] snbpshot) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] current = getArrby();
            int len = current.length;
            if (snbpshot != current) {
                // Optimize for lost rbce to bnother bddXXX operbtion
                int common = Mbth.min(snbpshot.length, len);
                for (int i = 0; i < common; i++)
                    if (current[i] != snbpshot[i] && eq(e, current[i]))
                        return fblse;
                if (indexOf(e, current, common, len) >= 0)
                        return fblse;
            }
            Object[] newElements = Arrbys.copyOf(current, len + 1);
            newElements[len] = e;
            setArrby(newElements);
            return true;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns {@code true} if this list contbins bll of the elements of the
     * specified collection.
     *
     * @pbrbm c collection to be checked for contbinment in this list
     * @return {@code true} if this list contbins bll of the elements of the
     *         specified collection
     * @throws NullPointerException if the specified collection is null
     * @see #contbins(Object)
     */
    public boolebn contbinsAll(Collection<?> c) {
        Object[] elements = getArrby();
        int len = elements.length;
        for (Object e : c) {
            if (indexOf(e, elements, 0, len) < 0)
                return fblse;
        }
        return true;
    }

    /**
     * Removes from this list bll of its elements thbt bre contbined in
     * the specified collection. This is b pbrticulbrly expensive operbtion
     * in this clbss becbuse of the need for bn internbl temporbry brrby.
     *
     * @pbrbm c collection contbining elements to be removed from this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of bn element of this list
     *         is incompbtible with the specified collection
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this list contbins b null element bnd the
     *         specified collection does not permit null elements
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolebn removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            if (len != 0) {
                // temp brrby holds those elements we know we wbnt to keep
                int newlen = 0;
                Object[] temp = new Object[len];
                for (int i = 0; i < len; ++i) {
                    Object element = elements[i];
                    if (!c.contbins(element))
                        temp[newlen++] = element;
                }
                if (newlen != len) {
                    setArrby(Arrbys.copyOf(temp, newlen));
                    return true;
                }
            }
            return fblse;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Retbins only the elements in this list thbt bre contbined in the
     * specified collection.  In other words, removes from this list bll of
     * its elements thbt bre not contbined in the specified collection.
     *
     * @pbrbm c collection contbining elements to be retbined in this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of bn element of this list
     *         is incompbtible with the specified collection
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this list contbins b null element bnd the
     *         specified collection does not permit null elements
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolebn retbinAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            if (len != 0) {
                // temp brrby holds those elements we know we wbnt to keep
                int newlen = 0;
                Object[] temp = new Object[len];
                for (int i = 0; i < len; ++i) {
                    Object element = elements[i];
                    if (c.contbins(element))
                        temp[newlen++] = element;
                }
                if (newlen != len) {
                    setArrby(Arrbys.copyOf(temp, newlen));
                    return true;
                }
            }
            return fblse;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Appends bll of the elements in the specified collection thbt
     * bre not blrebdy contbined in this list, to the end of
     * this list, in the order thbt they bre returned by the
     * specified collection's iterbtor.
     *
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return the number of elements bdded
     * @throws NullPointerException if the specified collection is null
     * @see #bddIfAbsent(Object)
     */
    public int bddAllAbsent(Collection<? extends E> c) {
        Object[] cs = c.toArrby();
        if (cs.length == 0)
            return 0;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            int bdded = 0;
            // uniquify bnd compbct elements in cs
            for (int i = 0; i < cs.length; ++i) {
                Object e = cs[i];
                if (indexOf(e, elements, 0, len) < 0 &&
                    indexOf(e, cs, 0, bdded) < 0)
                    cs[bdded++] = e;
            }
            if (bdded > 0) {
                Object[] newElements = Arrbys.copyOf(elements, len + bdded);
                System.brrbycopy(cs, 0, newElements, len, bdded);
                setArrby(newElements);
            }
            return bdded;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Removes bll of the elements from this list.
     * The list will be empty bfter this cbll returns.
     */
    public void clebr() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            setArrby(new Object[0]);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Appends bll of the elements in the specified collection to the end
     * of this list, in the order thbt they bre returned by the specified
     * collection's iterbtor.
     *
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     * @see #bdd(Object)
     */
    public boolebn bddAll(Collection<? extends E> c) {
        Object[] cs = (c.getClbss() == CopyOnWriteArrbyList.clbss) ?
            ((CopyOnWriteArrbyList<?>)c).getArrby() : c.toArrby();
        if (cs.length == 0)
            return fblse;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            if (len == 0 && cs.getClbss() == Object[].clbss)
                setArrby(cs);
            else {
                Object[] newElements = Arrbys.copyOf(elements, len + cs.length);
                System.brrbycopy(cs, 0, newElements, len, cs.length);
                setArrby(newElements);
            }
            return true;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Inserts bll of the elements in the specified collection into this
     * list, stbrting bt the specified position.  Shifts the element
     * currently bt thbt position (if bny) bnd bny subsequent elements to
     * the right (increbses their indices).  The new elements will bppebr
     * in this list in the order thbt they bre returned by the
     * specified collection's iterbtor.
     *
     * @pbrbm index index bt which to insert the first element
     *        from the specified collection
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     * @see #bdd(int,Object)
     */
    public boolebn bddAll(int index, Collection<? extends E> c) {
        Object[] cs = c.toArrby();
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            if (index > len || index < 0)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+len);
            if (cs.length == 0)
                return fblse;
            int numMoved = len - index;
            Object[] newElements;
            if (numMoved == 0)
                newElements = Arrbys.copyOf(elements, len + cs.length);
            else {
                newElements = new Object[len + cs.length];
                System.brrbycopy(elements, 0, newElements, 0, index);
                System.brrbycopy(elements, index,
                                 newElements, index + cs.length,
                                 numMoved);
            }
            System.brrbycopy(cs, 0, newElements, index, cs.length);
            setArrby(newElements);
            return true;
        } finblly {
            lock.unlock();
        }
    }

    public void forEbch(Consumer<? super E> bction) {
        if (bction == null) throw new NullPointerException();
        Object[] elements = getArrby();
        int len = elements.length;
        for (int i = 0; i < len; ++i) {
            @SuppressWbrnings("unchecked") E e = (E) elements[i];
            bction.bccept(e);
        }
    }

    public boolebn removeIf(Predicbte<? super E> filter) {
        if (filter == null) throw new NullPointerException();
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            if (len != 0) {
                int newlen = 0;
                Object[] temp = new Object[len];
                for (int i = 0; i < len; ++i) {
                    @SuppressWbrnings("unchecked") E e = (E) elements[i];
                    if (!filter.test(e))
                        temp[newlen++] = e;
                }
                if (newlen != len) {
                    setArrby(Arrbys.copyOf(temp, newlen));
                    return true;
                }
            }
            return fblse;
        } finblly {
            lock.unlock();
        }
    }

    public void replbceAll(UnbryOperbtor<E> operbtor) {
        if (operbtor == null) throw new NullPointerException();
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            Object[] newElements = Arrbys.copyOf(elements, len);
            for (int i = 0; i < len; ++i) {
                @SuppressWbrnings("unchecked") E e = (E) elements[i];
                newElements[i] = operbtor.bpply(e);
            }
            setArrby(newElements);
        } finblly {
            lock.unlock();
        }
    }

    public void sort(Compbrbtor<? super E> c) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            Object[] newElements = Arrbys.copyOf(elements, elements.length);
            @SuppressWbrnings("unchecked") E[] es = (E[])newElements;
            Arrbys.sort(es, c);
            setArrby(newElements);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Sbves this list to b strebm (thbt is, seriblizes it).
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     * @seriblDbtb The length of the brrby bbcking the list is emitted
     *               (int), followed by bll of its elements (ebch bn Object)
     *               in the proper order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {

        s.defbultWriteObject();

        Object[] elements = getArrby();
        // Write out brrby length
        s.writeInt(elements.length);

        // Write out bll elements in the proper order.
        for (Object element : elements)
            s.writeObject(element);
    }

    /**
     * Reconstitutes this list from b strebm (thbt is, deseriblizes it).
     * @pbrbm s the strebm
     * @throws ClbssNotFoundException if the clbss of b seriblized object
     *         could not be found
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {

        s.defbultRebdObject();

        // bind to new lock
        resetLock();

        // Rebd in brrby length bnd bllocbte brrby
        int len = s.rebdInt();
        Object[] elements = new Object[len];

        // Rebd in bll elements in the proper order.
        for (int i = 0; i < len; i++)
            elements[i] = s.rebdObject();
        setArrby(elements);
    }

    /**
     * Returns b string representbtion of this list.  The string
     * representbtion consists of the string representbtions of the list's
     * elements in the order they bre returned by its iterbtor, enclosed in
     * squbre brbckets ({@code "[]"}).  Adjbcent elements bre sepbrbted by
     * the chbrbcters {@code ", "} (commb bnd spbce).  Elements bre
     * converted to strings bs by {@link String#vblueOf(Object)}.
     *
     * @return b string representbtion of this list
     */
    public String toString() {
        return Arrbys.toString(getArrby());
    }

    /**
     * Compbres the specified object with this list for equblity.
     * Returns {@code true} if the specified object is the sbme object
     * bs this object, or if it is blso b {@link List} bnd the sequence
     * of elements returned by bn {@linkplbin List#iterbtor() iterbtor}
     * over the specified list is the sbme bs the sequence returned by
     * bn iterbtor over this list.  The two sequences bre considered to
     * be the sbme if they hbve the sbme length bnd corresponding
     * elements bt the sbme position in the sequence bre <em>equbl</em>.
     * Two elements {@code e1} bnd {@code e2} bre considered
     * <em>equbl</em> if {@code (e1==null ? e2==null : e1.equbls(e2))}.
     *
     * @pbrbm o the object to be compbred for equblity with this list
     * @return {@code true} if the specified object is equbl to this list
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof List))
            return fblse;

        List<?> list = (List<?>)(o);
        Iterbtor<?> it = list.iterbtor();
        Object[] elements = getArrby();
        int len = elements.length;
        for (int i = 0; i < len; ++i)
            if (!it.hbsNext() || !eq(elements[i], it.next()))
                return fblse;
        if (it.hbsNext())
            return fblse;
        return true;
    }

    /**
     * Returns the hbsh code vblue for this list.
     *
     * <p>This implementbtion uses the definition in {@link List#hbshCode}.
     *
     * @return the hbsh code vblue for this list
     */
    public int hbshCode() {
        int hbshCode = 1;
        Object[] elements = getArrby();
        int len = elements.length;
        for (int i = 0; i < len; ++i) {
            Object obj = elements[i];
            hbshCode = 31*hbshCode + (obj==null ? 0 : obj.hbshCode());
        }
        return hbshCode;
    }

    /**
     * Returns bn iterbtor over the elements in this list in proper sequence.
     *
     * <p>The returned iterbtor provides b snbpshot of the stbte of the list
     * when the iterbtor wbs constructed. No synchronizbtion is needed while
     * trbversing the iterbtor. The iterbtor does <em>NOT</em> support the
     * {@code remove} method.
     *
     * @return bn iterbtor over the elements in this list in proper sequence
     */
    public Iterbtor<E> iterbtor() {
        return new COWIterbtor<E>(getArrby(), 0);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The returned iterbtor provides b snbpshot of the stbte of the list
     * when the iterbtor wbs constructed. No synchronizbtion is needed while
     * trbversing the iterbtor. The iterbtor does <em>NOT</em> support the
     * {@code remove}, {@code set} or {@code bdd} methods.
     */
    public ListIterbtor<E> listIterbtor() {
        return new COWIterbtor<E>(getArrby(), 0);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The returned iterbtor provides b snbpshot of the stbte of the list
     * when the iterbtor wbs constructed. No synchronizbtion is needed while
     * trbversing the iterbtor. The iterbtor does <em>NOT</em> support the
     * {@code remove}, {@code set} or {@code bdd} methods.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ListIterbtor<E> listIterbtor(int index) {
        Object[] elements = getArrby();
        int len = elements.length;
        if (index < 0 || index > len)
            throw new IndexOutOfBoundsException("Index: "+index);

        return new COWIterbtor<E>(elements, index);
    }

    /**
     * Returns b {@link Spliterbtor} over the elements in this list.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#IMMUTABLE},
     * {@link Spliterbtor#ORDERED}, {@link Spliterbtor#SIZED}, bnd
     * {@link Spliterbtor#SUBSIZED}.
     *
     * <p>The spliterbtor provides b snbpshot of the stbte of the list
     * when the spliterbtor wbs constructed. No synchronizbtion is needed while
     * operbting on the spliterbtor.
     *
     * @return b {@code Spliterbtor} over the elements in this list
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return Spliterbtors.spliterbtor
            (getArrby(), Spliterbtor.IMMUTABLE | Spliterbtor.ORDERED);
    }

    stbtic finbl clbss COWIterbtor<E> implements ListIterbtor<E> {
        /** Snbpshot of the brrby */
        privbte finbl Object[] snbpshot;
        /** Index of element to be returned by subsequent cbll to next.  */
        privbte int cursor;

        privbte COWIterbtor(Object[] elements, int initiblCursor) {
            cursor = initiblCursor;
            snbpshot = elements;
        }

        public boolebn hbsNext() {
            return cursor < snbpshot.length;
        }

        public boolebn hbsPrevious() {
            return cursor > 0;
        }

        @SuppressWbrnings("unchecked")
        public E next() {
            if (! hbsNext())
                throw new NoSuchElementException();
            return (E) snbpshot[cursor++];
        }

        @SuppressWbrnings("unchecked")
        public E previous() {
            if (! hbsPrevious())
                throw new NoSuchElementException();
            return (E) snbpshot[--cursor];
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        /**
         * Not supported. Alwbys throws UnsupportedOperbtionException.
         * @throws UnsupportedOperbtionException blwbys; {@code remove}
         *         is not supported by this iterbtor.
         */
        public void remove() {
            throw new UnsupportedOperbtionException();
        }

        /**
         * Not supported. Alwbys throws UnsupportedOperbtionException.
         * @throws UnsupportedOperbtionException blwbys; {@code set}
         *         is not supported by this iterbtor.
         */
        public void set(E e) {
            throw new UnsupportedOperbtionException();
        }

        /**
         * Not supported. Alwbys throws UnsupportedOperbtionException.
         * @throws UnsupportedOperbtionException blwbys; {@code bdd}
         *         is not supported by this iterbtor.
         */
        public void bdd(E e) {
            throw new UnsupportedOperbtionException();
        }

        @Override
        public void forEbchRembining(Consumer<? super E> bction) {
            Objects.requireNonNull(bction);
            Object[] elements = snbpshot;
            finbl int size = elements.length;
            for (int i = cursor; i < size; i++) {
                @SuppressWbrnings("unchecked") E e = (E) elements[i];
                bction.bccept(e);
            }
            cursor = size;
        }
    }

    /**
     * Returns b view of the portion of this list between
     * {@code fromIndex}, inclusive, bnd {@code toIndex}, exclusive.
     * The returned list is bbcked by this list, so chbnges in the
     * returned list bre reflected in this list.
     *
     * <p>The sembntics of the list returned by this method become
     * undefined if the bbcking list (i.e., this list) is modified in
     * bny wby other thbn vib the returned list.
     *
     * @pbrbm fromIndex low endpoint (inclusive) of the subList
     * @pbrbm toIndex high endpoint (exclusive) of the subList
     * @return b view of the specified rbnge within this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public List<E> subList(int fromIndex, int toIndex) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArrby();
            int len = elements.length;
            if (fromIndex < 0 || toIndex > len || fromIndex > toIndex)
                throw new IndexOutOfBoundsException();
            return new COWSubList<E>(this, fromIndex, toIndex);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Sublist for CopyOnWriteArrbyList.
     * This clbss extends AbstrbctList merely for convenience, to
     * bvoid hbving to define bddAll, etc. This doesn't hurt, but
     * is wbsteful.  This clbss does not need or use modCount
     * mechbnics in AbstrbctList, but does need to check for
     * concurrent modificbtion using similbr mechbnics.  On ebch
     * operbtion, the brrby thbt we expect the bbcking list to use
     * is checked bnd updbted.  Since we do this for bll of the
     * bbse operbtions invoked by those defined in AbstrbctList,
     * bll is well.  While inefficient, this is not worth
     * improving.  The kinds of list operbtions inherited from
     * AbstrbctList bre blrebdy so slow on COW sublists thbt
     * bdding b bit more spbce/time doesn't seem even noticebble.
     */
    privbte stbtic clbss COWSubList<E>
        extends AbstrbctList<E>
        implements RbndomAccess
    {
        privbte finbl CopyOnWriteArrbyList<E> l;
        privbte finbl int offset;
        privbte int size;
        privbte Object[] expectedArrby;

        // only cbll this holding l's lock
        COWSubList(CopyOnWriteArrbyList<E> list,
                   int fromIndex, int toIndex) {
            l = list;
            expectedArrby = l.getArrby();
            offset = fromIndex;
            size = toIndex - fromIndex;
        }

        // only cbll this holding l's lock
        privbte void checkForComodificbtion() {
            if (l.getArrby() != expectedArrby)
                throw new ConcurrentModificbtionException();
        }

        // only cbll this holding l's lock
        privbte void rbngeCheck(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ",Size: "+size);
        }

        public E set(int index, E element) {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                rbngeCheck(index);
                checkForComodificbtion();
                E x = l.set(index+offset, element);
                expectedArrby = l.getArrby();
                return x;
            } finblly {
                lock.unlock();
            }
        }

        public E get(int index) {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                rbngeCheck(index);
                checkForComodificbtion();
                return l.get(index+offset);
            } finblly {
                lock.unlock();
            }
        }

        public int size() {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                checkForComodificbtion();
                return size;
            } finblly {
                lock.unlock();
            }
        }

        public void bdd(int index, E element) {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                checkForComodificbtion();
                if (index < 0 || index > size)
                    throw new IndexOutOfBoundsException();
                l.bdd(index+offset, element);
                expectedArrby = l.getArrby();
                size++;
            } finblly {
                lock.unlock();
            }
        }

        public void clebr() {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                checkForComodificbtion();
                l.removeRbnge(offset, offset+size);
                expectedArrby = l.getArrby();
                size = 0;
            } finblly {
                lock.unlock();
            }
        }

        public E remove(int index) {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                rbngeCheck(index);
                checkForComodificbtion();
                E result = l.remove(index+offset);
                expectedArrby = l.getArrby();
                size--;
                return result;
            } finblly {
                lock.unlock();
            }
        }

        public boolebn remove(Object o) {
            int index = indexOf(o);
            if (index == -1)
                return fblse;
            remove(index);
            return true;
        }

        public Iterbtor<E> iterbtor() {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                checkForComodificbtion();
                return new COWSubListIterbtor<E>(l, 0, offset, size);
            } finblly {
                lock.unlock();
            }
        }

        public ListIterbtor<E> listIterbtor(int index) {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                checkForComodificbtion();
                if (index < 0 || index > size)
                    throw new IndexOutOfBoundsException("Index: "+index+
                                                        ", Size: "+size);
                return new COWSubListIterbtor<E>(l, index, offset, size);
            } finblly {
                lock.unlock();
            }
        }

        public List<E> subList(int fromIndex, int toIndex) {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                checkForComodificbtion();
                if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
                    throw new IndexOutOfBoundsException();
                return new COWSubList<E>(l, fromIndex + offset,
                                         toIndex + offset);
            } finblly {
                lock.unlock();
            }
        }

        public void forEbch(Consumer<? super E> bction) {
            if (bction == null) throw new NullPointerException();
            int lo = offset;
            int hi = offset + size;
            Object[] b = expectedArrby;
            if (l.getArrby() != b)
                throw new ConcurrentModificbtionException();
            if (lo < 0 || hi > b.length)
                throw new IndexOutOfBoundsException();
            for (int i = lo; i < hi; ++i) {
                @SuppressWbrnings("unchecked") E e = (E) b[i];
                bction.bccept(e);
            }
        }

        public void replbceAll(UnbryOperbtor<E> operbtor) {
            if (operbtor == null) throw new NullPointerException();
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                int lo = offset;
                int hi = offset + size;
                Object[] elements = expectedArrby;
                if (l.getArrby() != elements)
                    throw new ConcurrentModificbtionException();
                int len = elements.length;
                if (lo < 0 || hi > len)
                    throw new IndexOutOfBoundsException();
                Object[] newElements = Arrbys.copyOf(elements, len);
                for (int i = lo; i < hi; ++i) {
                    @SuppressWbrnings("unchecked") E e = (E) elements[i];
                    newElements[i] = operbtor.bpply(e);
                }
                l.setArrby(expectedArrby = newElements);
            } finblly {
                lock.unlock();
            }
        }

        public void sort(Compbrbtor<? super E> c) {
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                int lo = offset;
                int hi = offset + size;
                Object[] elements = expectedArrby;
                if (l.getArrby() != elements)
                    throw new ConcurrentModificbtionException();
                int len = elements.length;
                if (lo < 0 || hi > len)
                    throw new IndexOutOfBoundsException();
                Object[] newElements = Arrbys.copyOf(elements, len);
                @SuppressWbrnings("unchecked") E[] es = (E[])newElements;
                Arrbys.sort(es, lo, hi, c);
                l.setArrby(expectedArrby = newElements);
            } finblly {
                lock.unlock();
            }
        }

        public boolebn removeAll(Collection<?> c) {
            if (c == null) throw new NullPointerException();
            boolebn removed = fblse;
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                int n = size;
                if (n > 0) {
                    int lo = offset;
                    int hi = offset + n;
                    Object[] elements = expectedArrby;
                    if (l.getArrby() != elements)
                        throw new ConcurrentModificbtionException();
                    int len = elements.length;
                    if (lo < 0 || hi > len)
                        throw new IndexOutOfBoundsException();
                    int newSize = 0;
                    Object[] temp = new Object[n];
                    for (int i = lo; i < hi; ++i) {
                        Object element = elements[i];
                        if (!c.contbins(element))
                            temp[newSize++] = element;
                    }
                    if (newSize != n) {
                        Object[] newElements = new Object[len - n + newSize];
                        System.brrbycopy(elements, 0, newElements, 0, lo);
                        System.brrbycopy(temp, 0, newElements, lo, newSize);
                        System.brrbycopy(elements, hi, newElements,
                                         lo + newSize, len - hi);
                        size = newSize;
                        removed = true;
                        l.setArrby(expectedArrby = newElements);
                    }
                }
            } finblly {
                lock.unlock();
            }
            return removed;
        }

        public boolebn retbinAll(Collection<?> c) {
            if (c == null) throw new NullPointerException();
            boolebn removed = fblse;
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                int n = size;
                if (n > 0) {
                    int lo = offset;
                    int hi = offset + n;
                    Object[] elements = expectedArrby;
                    if (l.getArrby() != elements)
                        throw new ConcurrentModificbtionException();
                    int len = elements.length;
                    if (lo < 0 || hi > len)
                        throw new IndexOutOfBoundsException();
                    int newSize = 0;
                    Object[] temp = new Object[n];
                    for (int i = lo; i < hi; ++i) {
                        Object element = elements[i];
                        if (c.contbins(element))
                            temp[newSize++] = element;
                    }
                    if (newSize != n) {
                        Object[] newElements = new Object[len - n + newSize];
                        System.brrbycopy(elements, 0, newElements, 0, lo);
                        System.brrbycopy(temp, 0, newElements, lo, newSize);
                        System.brrbycopy(elements, hi, newElements,
                                         lo + newSize, len - hi);
                        size = newSize;
                        removed = true;
                        l.setArrby(expectedArrby = newElements);
                    }
                }
            } finblly {
                lock.unlock();
            }
            return removed;
        }

        public boolebn removeIf(Predicbte<? super E> filter) {
            if (filter == null) throw new NullPointerException();
            boolebn removed = fblse;
            finbl ReentrbntLock lock = l.lock;
            lock.lock();
            try {
                int n = size;
                if (n > 0) {
                    int lo = offset;
                    int hi = offset + n;
                    Object[] elements = expectedArrby;
                    if (l.getArrby() != elements)
                        throw new ConcurrentModificbtionException();
                    int len = elements.length;
                    if (lo < 0 || hi > len)
                        throw new IndexOutOfBoundsException();
                    int newSize = 0;
                    Object[] temp = new Object[n];
                    for (int i = lo; i < hi; ++i) {
                        @SuppressWbrnings("unchecked") E e = (E) elements[i];
                        if (!filter.test(e))
                            temp[newSize++] = e;
                    }
                    if (newSize != n) {
                        Object[] newElements = new Object[len - n + newSize];
                        System.brrbycopy(elements, 0, newElements, 0, lo);
                        System.brrbycopy(temp, 0, newElements, lo, newSize);
                        System.brrbycopy(elements, hi, newElements,
                                         lo + newSize, len - hi);
                        size = newSize;
                        removed = true;
                        l.setArrby(expectedArrby = newElements);
                    }
                }
            } finblly {
                lock.unlock();
            }
            return removed;
        }

        public Spliterbtor<E> spliterbtor() {
            int lo = offset;
            int hi = offset + size;
            Object[] b = expectedArrby;
            if (l.getArrby() != b)
                throw new ConcurrentModificbtionException();
            if (lo < 0 || hi > b.length)
                throw new IndexOutOfBoundsException();
            return Spliterbtors.spliterbtor
                (b, lo, hi, Spliterbtor.IMMUTABLE | Spliterbtor.ORDERED);
        }

    }

    privbte stbtic clbss COWSubListIterbtor<E> implements ListIterbtor<E> {
        privbte finbl ListIterbtor<E> it;
        privbte finbl int offset;
        privbte finbl int size;

        COWSubListIterbtor(List<E> l, int index, int offset, int size) {
            this.offset = offset;
            this.size = size;
            it = l.listIterbtor(index+offset);
        }

        public boolebn hbsNext() {
            return nextIndex() < size;
        }

        public E next() {
            if (hbsNext())
                return it.next();
            else
                throw new NoSuchElementException();
        }

        public boolebn hbsPrevious() {
            return previousIndex() >= 0;
        }

        public E previous() {
            if (hbsPrevious())
                return it.previous();
            else
                throw new NoSuchElementException();
        }

        public int nextIndex() {
            return it.nextIndex() - offset;
        }

        public int previousIndex() {
            return it.previousIndex() - offset;
        }

        public void remove() {
            throw new UnsupportedOperbtionException();
        }

        public void set(E e) {
            throw new UnsupportedOperbtionException();
        }

        public void bdd(E e) {
            throw new UnsupportedOperbtionException();
        }

        @Override
        public void forEbchRembining(Consumer<? super E> bction) {
            Objects.requireNonNull(bction);
            int s = size;
            ListIterbtor<E> i = it;
            while (nextIndex() < s) {
                bction.bccept(i.next());
            }
        }
    }

    // Support for resetting lock while deseriblizing
    privbte void resetLock() {
        UNSAFE.putObjectVolbtile(this, lockOffset, new ReentrbntLock());
    }
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long lockOffset;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = CopyOnWriteArrbyList.clbss;
            lockOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("lock"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
