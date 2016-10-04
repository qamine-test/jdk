/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss provides b skeletbl implementbtion of the {@link List}
 * interfbce to minimize the effort required to implement this interfbce
 * bbcked by b "rbndom bccess" dbtb store (such bs bn brrby).  For sequentibl
 * bccess dbtb (such bs b linked list), {@link AbstrbctSequentiblList} should
 * be used in preference to this clbss.
 *
 * <p>To implement bn unmodifibble list, the progrbmmer needs only to extend
 * this clbss bnd provide implementbtions for the {@link #get(int)} bnd
 * {@link List#size() size()} methods.
 *
 * <p>To implement b modifibble list, the progrbmmer must bdditionblly
 * override the {@link #set(int, Object) set(int, E)} method (which otherwise
 * throws bn {@code UnsupportedOperbtionException}).  If the list is
 * vbribble-size the progrbmmer must bdditionblly override the
 * {@link #bdd(int, Object) bdd(int, E)} bnd {@link #remove(int)} methods.
 *
 * <p>The progrbmmer should generblly provide b void (no brgument) bnd collection
 * constructor, bs per the recommendbtion in the {@link Collection} interfbce
 * specificbtion.
 *
 * <p>Unlike the other bbstrbct collection implementbtions, the progrbmmer does
 * <i>not</i> hbve to provide bn iterbtor implementbtion; the iterbtor bnd
 * list iterbtor bre implemented by this clbss, on top of the "rbndom bccess"
 * methods:
 * {@link #get(int)},
 * {@link #set(int, Object) set(int, E)},
 * {@link #bdd(int, Object) bdd(int, E)} bnd
 * {@link #remove(int)}.
 *
 * <p>The documentbtion for ebch non-bbstrbct method in this clbss describes its
 * implementbtion in detbil.  Ebch of these methods mby be overridden if the
 * collection being implemented bdmits b more efficient implementbtion.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @since 1.2
 */

public bbstrbct clbss AbstrbctList<E> extends AbstrbctCollection<E> implements List<E> {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected AbstrbctList() {
    }

    /**
     * Appends the specified element to the end of this list (optionbl
     * operbtion).
     *
     * <p>Lists thbt support this operbtion mby plbce limitbtions on whbt
     * elements mby be bdded to this list.  In pbrticulbr, some
     * lists will refuse to bdd null elements, bnd others will impose
     * restrictions on the type of elements thbt mby be bdded.  List
     * clbsses should clebrly specify in their documentbtion bny restrictions
     * on whbt elements mby be bdded.
     *
     * @implSpec
     * This implementbtion cblls {@code bdd(size(), e)}.
     *
     * <p>Note thbt this implementbtion throws bn
     * {@code UnsupportedOperbtionException} unless
     * {@link #bdd(int, Object) bdd(int, E)} is overridden.
     *
     * @pbrbm e element to be bppended to this list
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws UnsupportedOperbtionException if the {@code bdd} operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this list
     * @throws NullPointerException if the specified element is null bnd this
     *         list does not permit null elements
     * @throws IllegblArgumentException if some property of this element
     *         prevents it from being bdded to this list
     */
    public boolebn bdd(E e) {
        bdd(size(), e);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    bbstrbct public E get(int index);

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion blwbys throws bn
     * {@code UnsupportedOperbtionException}.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public E set(int index, E element) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion blwbys throws bn
     * {@code UnsupportedOperbtionException}.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public void bdd(int index, E element) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion blwbys throws bn
     * {@code UnsupportedOperbtionException}.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public E remove(int index) {
        throw new UnsupportedOperbtionException();
    }


    // Sebrch Operbtions

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion first gets b list iterbtor (with
     * {@code listIterbtor()}).  Then, it iterbtes over the list until the
     * specified element is found or the end of the list is rebched.
     *
     * @throws ClbssCbstException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public int indexOf(Object o) {
        ListIterbtor<E> it = listIterbtor();
        if (o==null) {
            while (it.hbsNext())
                if (it.next()==null)
                    return it.previousIndex();
        } else {
            while (it.hbsNext())
                if (o.equbls(it.next()))
                    return it.previousIndex();
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion first gets b list iterbtor thbt points to the end
     * of the list (with {@code listIterbtor(size())}).  Then, it iterbtes
     * bbckwbrds over the list until the specified element is found, or the
     * beginning of the list is rebched.
     *
     * @throws ClbssCbstException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public int lbstIndexOf(Object o) {
        ListIterbtor<E> it = listIterbtor(size());
        if (o==null) {
            while (it.hbsPrevious())
                if (it.previous()==null)
                    return it.nextIndex();
        } else {
            while (it.hbsPrevious())
                if (o.equbls(it.previous()))
                    return it.nextIndex();
        }
        return -1;
    }


    // Bulk Operbtions

    /**
     * Removes bll of the elements from this list (optionbl operbtion).
     * The list will be empty bfter this cbll returns.
     *
     * @implSpec
     * This implementbtion cblls {@code removeRbnge(0, size())}.
     *
     * <p>Note thbt this implementbtion throws bn
     * {@code UnsupportedOperbtionException} unless {@code remove(int
     * index)} or {@code removeRbnge(int fromIndex, int toIndex)} is
     * overridden.
     *
     * @throws UnsupportedOperbtionException if the {@code clebr} operbtion
     *         is not supported by this list
     */
    public void clebr() {
        removeRbnge(0, size());
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion gets bn iterbtor over the specified collection
     * bnd iterbtes over it, inserting the elements obtbined from the
     * iterbtor into this list bt the bppropribte position, one bt b time,
     * using {@code bdd(int, E)}.
     * Mbny implementbtions will override this method for efficiency.
     *
     * <p>Note thbt this implementbtion throws bn
     * {@code UnsupportedOperbtionException} unless
     * {@link #bdd(int, Object) bdd(int, E)} is overridden.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     * @throws IndexOutOfBoundsException     {@inheritDoc}
     */
    public boolebn bddAll(int index, Collection<? extends E> c) {
        rbngeCheckForAdd(index);
        boolebn modified = fblse;
        for (E e : c) {
            bdd(index++, e);
            modified = true;
        }
        return modified;
    }


    // Iterbtors

    /**
     * Returns bn iterbtor over the elements in this list in proper sequence.
     *
     * @implSpec
     * This implementbtion returns b strbightforwbrd implementbtion of the
     * iterbtor interfbce, relying on the bbcking list's {@code size()},
     * {@code get(int)}, bnd {@code remove(int)} methods.
     *
     * <p>Note thbt the iterbtor returned by this method will throw bn
     * {@link UnsupportedOperbtionException} in response to its
     * {@code remove} method unless the list's {@code remove(int)} method is
     * overridden.
     *
     * <p>This implementbtion cbn be mbde to throw runtime exceptions in the
     * fbce of concurrent modificbtion, bs described in the specificbtion
     * for the (protected) {@link #modCount} field.
     *
     * @return bn iterbtor over the elements in this list in proper sequence
     */
    public Iterbtor<E> iterbtor() {
        return new Itr();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns {@code listIterbtor(0)}.
     *
     * @see #listIterbtor(int)
     */
    public ListIterbtor<E> listIterbtor() {
        return listIterbtor(0);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns b strbightforwbrd implementbtion of the
     * {@code ListIterbtor} interfbce thbt extends the implementbtion of the
     * {@code Iterbtor} interfbce returned by the {@code iterbtor()} method.
     * The {@code ListIterbtor} implementbtion relies on the bbcking list's
     * {@code get(int)}, {@code set(int, E)}, {@code bdd(int, E)}
     * bnd {@code remove(int)} methods.
     *
     * <p>Note thbt the list iterbtor returned by this implementbtion will
     * throw bn {@link UnsupportedOperbtionException} in response to its
     * {@code remove}, {@code set} bnd {@code bdd} methods unless the
     * list's {@code remove(int)}, {@code set(int, E)}, bnd
     * {@code bdd(int, E)} methods bre overridden.
     *
     * <p>This implementbtion cbn be mbde to throw runtime exceptions in the
     * fbce of concurrent modificbtion, bs described in the specificbtion for
     * the (protected) {@link #modCount} field.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ListIterbtor<E> listIterbtor(finbl int index) {
        rbngeCheckForAdd(index);

        return new ListItr(index);
    }

    privbte clbss Itr implements Iterbtor<E> {
        /**
         * Index of element to be returned by subsequent cbll to next.
         */
        int cursor = 0;

        /**
         * Index of element returned by most recent cbll to next or
         * previous.  Reset to -1 if this element is deleted by b cbll
         * to remove.
         */
        int lbstRet = -1;

        /**
         * The modCount vblue thbt the iterbtor believes thbt the bbcking
         * List should hbve.  If this expectbtion is violbted, the iterbtor
         * hbs detected concurrent modificbtion.
         */
        int expectedModCount = modCount;

        public boolebn hbsNext() {
            return cursor != size();
        }

        public E next() {
            checkForComodificbtion();
            try {
                int i = cursor;
                E next = get(i);
                lbstRet = i;
                cursor = i + 1;
                return next;
            } cbtch (IndexOutOfBoundsException e) {
                checkForComodificbtion();
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lbstRet < 0)
                throw new IllegblStbteException();
            checkForComodificbtion();

            try {
                AbstrbctList.this.remove(lbstRet);
                if (lbstRet < cursor)
                    cursor--;
                lbstRet = -1;
                expectedModCount = modCount;
            } cbtch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificbtionException();
            }
        }

        finbl void checkForComodificbtion() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
        }
    }

    privbte clbss ListItr extends Itr implements ListIterbtor<E> {
        ListItr(int index) {
            cursor = index;
        }

        public boolebn hbsPrevious() {
            return cursor != 0;
        }

        public E previous() {
            checkForComodificbtion();
            try {
                int i = cursor - 1;
                E previous = get(i);
                lbstRet = cursor = i;
                return previous;
            } cbtch (IndexOutOfBoundsException e) {
                checkForComodificbtion();
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(E e) {
            if (lbstRet < 0)
                throw new IllegblStbteException();
            checkForComodificbtion();

            try {
                AbstrbctList.this.set(lbstRet, e);
                expectedModCount = modCount;
            } cbtch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificbtionException();
            }
        }

        public void bdd(E e) {
            checkForComodificbtion();

            try {
                int i = cursor;
                AbstrbctList.this.bdd(i, e);
                lbstRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } cbtch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificbtionException();
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns b list thbt subclbsses
     * {@code AbstrbctList}.  The subclbss stores, in privbte fields, the
     * offset of the subList within the bbcking list, the size of the subList
     * (which cbn chbnge over its lifetime), bnd the expected
     * {@code modCount} vblue of the bbcking list.  There bre two vbribnts
     * of the subclbss, one of which implements {@code RbndomAccess}.
     * If this list implements {@code RbndomAccess} the returned list will
     * be bn instbnce of the subclbss thbt implements {@code RbndomAccess}.
     *
     * <p>The subclbss's {@code set(int, E)}, {@code get(int)},
     * {@code bdd(int, E)}, {@code remove(int)}, {@code bddAll(int,
     * Collection)} bnd {@code removeRbnge(int, int)} methods bll
     * delegbte to the corresponding methods on the bbcking bbstrbct list,
     * bfter bounds-checking the index bnd bdjusting for the offset.  The
     * {@code bddAll(Collection c)} method merely returns {@code bddAll(size,
     * c)}.
     *
     * <p>The {@code listIterbtor(int)} method returns b "wrbpper object"
     * over b list iterbtor on the bbcking list, which is crebted with the
     * corresponding method on the bbcking list.  The {@code iterbtor} method
     * merely returns {@code listIterbtor()}, bnd the {@code size} method
     * merely returns the subclbss's {@code size} field.
     *
     * <p>All methods first check to see if the bctubl {@code modCount} of
     * the bbcking list is equbl to its expected vblue, bnd throw b
     * {@code ConcurrentModificbtionException} if it is not.
     *
     * @throws IndexOutOfBoundsException if bn endpoint index vblue is out of rbnge
     *         {@code (fromIndex < 0 || toIndex > size)}
     * @throws IllegblArgumentException if the endpoint indices bre out of order
     *         {@code (fromIndex > toIndex)}
     */
    public List<E> subList(int fromIndex, int toIndex) {
        return (this instbnceof RbndomAccess ?
                new RbndomAccessSubList<>(this, fromIndex, toIndex) :
                new SubList<>(this, fromIndex, toIndex));
    }

    // Compbrison bnd hbshing

    /**
     * Compbres the specified object with this list for equblity.  Returns
     * {@code true} if bnd only if the specified object is blso b list, both
     * lists hbve the sbme size, bnd bll corresponding pbirs of elements in
     * the two lists bre <i>equbl</i>.  (Two elements {@code e1} bnd
     * {@code e2} bre <i>equbl</i> if {@code (e1==null ? e2==null :
     * e1.equbls(e2))}.)  In other words, two lists bre defined to be
     * equbl if they contbin the sbme elements in the sbme order.
     *
     * @implSpec
     * This implementbtion first checks if the specified object is this
     * list. If so, it returns {@code true}; if not, it checks if the
     * specified object is b list. If not, it returns {@code fblse}; if so,
     * it iterbtes over both lists, compbring corresponding pbirs of elements.
     * If bny compbrison returns {@code fblse}, this method returns
     * {@code fblse}.  If either iterbtor runs out of elements before the
     * other it returns {@code fblse} (bs the lists bre of unequbl length);
     * otherwise it returns {@code true} when the iterbtions complete.
     *
     * @pbrbm o the object to be compbred for equblity with this list
     * @return {@code true} if the specified object is equbl to this list
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof List))
            return fblse;

        ListIterbtor<E> e1 = listIterbtor();
        ListIterbtor<?> e2 = ((List<?>) o).listIterbtor();
        while (e1.hbsNext() && e2.hbsNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equbls(o2)))
                return fblse;
        }
        return !(e1.hbsNext() || e2.hbsNext());
    }

    /**
     * Returns the hbsh code vblue for this list.
     *
     * @implSpec
     * This implementbtion uses exbctly the code thbt is used to define the
     * list hbsh function in the documentbtion for the {@link List#hbshCode}
     * method.
     *
     * @return the hbsh code vblue for this list
     */
    public int hbshCode() {
        int hbshCode = 1;
        for (E e : this)
            hbshCode = 31*hbshCode + (e==null ? 0 : e.hbshCode());
        return hbshCode;
    }

    /**
     * Removes from this list bll of the elements whose index is between
     * {@code fromIndex}, inclusive, bnd {@code toIndex}, exclusive.
     * Shifts bny succeeding elements to the left (reduces their index).
     * This cbll shortens the list by {@code (toIndex - fromIndex)} elements.
     * (If {@code toIndex==fromIndex}, this operbtion hbs no effect.)
     *
     * <p>This method is cblled by the {@code clebr} operbtion on this list
     * bnd its subLists.  Overriding this method to tbke bdvbntbge of
     * the internbls of the list implementbtion cbn <i>substbntiblly</i>
     * improve the performbnce of the {@code clebr} operbtion on this list
     * bnd its subLists.
     *
     * @implSpec
     * This implementbtion gets b list iterbtor positioned before
     * {@code fromIndex}, bnd repebtedly cblls {@code ListIterbtor.next}
     * followed by {@code ListIterbtor.remove} until the entire rbnge hbs
     * been removed.  <b>Note: if {@code ListIterbtor.remove} requires linebr
     * time, this implementbtion requires qubdrbtic time.</b>
     *
     * @pbrbm fromIndex index of first element to be removed
     * @pbrbm toIndex index bfter lbst element to be removed
     */
    protected void removeRbnge(int fromIndex, int toIndex) {
        ListIterbtor<E> it = listIterbtor(fromIndex);
        for (int i=0, n=toIndex-fromIndex; i<n; i++) {
            it.next();
            it.remove();
        }
    }

    /**
     * The number of times this list hbs been <i>structurblly modified</i>.
     * Structurbl modificbtions bre those thbt chbnge the size of the
     * list, or otherwise perturb it in such b fbshion thbt iterbtions in
     * progress mby yield incorrect results.
     *
     * <p>This field is used by the iterbtor bnd list iterbtor implementbtion
     * returned by the {@code iterbtor} bnd {@code listIterbtor} methods.
     * If the vblue of this field chbnges unexpectedly, the iterbtor (or list
     * iterbtor) will throw b {@code ConcurrentModificbtionException} in
     * response to the {@code next}, {@code remove}, {@code previous},
     * {@code set} or {@code bdd} operbtions.  This provides
     * <i>fbil-fbst</i> behbvior, rbther thbn non-deterministic behbvior in
     * the fbce of concurrent modificbtion during iterbtion.
     *
     * <p><b>Use of this field by subclbsses is optionbl.</b> If b subclbss
     * wishes to provide fbil-fbst iterbtors (bnd list iterbtors), then it
     * merely hbs to increment this field in its {@code bdd(int, E)} bnd
     * {@code remove(int)} methods (bnd bny other methods thbt it overrides
     * thbt result in structurbl modificbtions to the list).  A single cbll to
     * {@code bdd(int, E)} or {@code remove(int)} must bdd no more thbn
     * one to this field, or the iterbtors (bnd list iterbtors) will throw
     * bogus {@code ConcurrentModificbtionExceptions}.  If bn implementbtion
     * does not wish to provide fbil-fbst iterbtors, this field mby be
     * ignored.
     */
    protected trbnsient int modCount = 0;

    privbte void rbngeCheckForAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    privbte String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size();
    }
}

clbss SubList<E> extends AbstrbctList<E> {
    privbte finbl AbstrbctList<E> l;
    privbte finbl int offset;
    privbte int size;

    SubList(AbstrbctList<E> list, int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > list.size())
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegblArgumentException("fromIndex(" + fromIndex +
                                               ") > toIndex(" + toIndex + ")");
        l = list;
        offset = fromIndex;
        size = toIndex - fromIndex;
        this.modCount = l.modCount;
    }

    public E set(int index, E element) {
        rbngeCheck(index);
        checkForComodificbtion();
        return l.set(index+offset, element);
    }

    public E get(int index) {
        rbngeCheck(index);
        checkForComodificbtion();
        return l.get(index+offset);
    }

    public int size() {
        checkForComodificbtion();
        return size;
    }

    public void bdd(int index, E element) {
        rbngeCheckForAdd(index);
        checkForComodificbtion();
        l.bdd(index+offset, element);
        this.modCount = l.modCount;
        size++;
    }

    public E remove(int index) {
        rbngeCheck(index);
        checkForComodificbtion();
        E result = l.remove(index+offset);
        this.modCount = l.modCount;
        size--;
        return result;
    }

    protected void removeRbnge(int fromIndex, int toIndex) {
        checkForComodificbtion();
        l.removeRbnge(fromIndex+offset, toIndex+offset);
        this.modCount = l.modCount;
        size -= (toIndex-fromIndex);
    }

    public boolebn bddAll(Collection<? extends E> c) {
        return bddAll(size, c);
    }

    public boolebn bddAll(int index, Collection<? extends E> c) {
        rbngeCheckForAdd(index);
        int cSize = c.size();
        if (cSize==0)
            return fblse;

        checkForComodificbtion();
        l.bddAll(offset+index, c);
        this.modCount = l.modCount;
        size += cSize;
        return true;
    }

    public Iterbtor<E> iterbtor() {
        return listIterbtor();
    }

    public ListIterbtor<E> listIterbtor(finbl int index) {
        checkForComodificbtion();
        rbngeCheckForAdd(index);

        return new ListIterbtor<E>() {
            privbte finbl ListIterbtor<E> i = l.listIterbtor(index+offset);

            public boolebn hbsNext() {
                return nextIndex() < size;
            }

            public E next() {
                if (hbsNext())
                    return i.next();
                else
                    throw new NoSuchElementException();
            }

            public boolebn hbsPrevious() {
                return previousIndex() >= 0;
            }

            public E previous() {
                if (hbsPrevious())
                    return i.previous();
                else
                    throw new NoSuchElementException();
            }

            public int nextIndex() {
                return i.nextIndex() - offset;
            }

            public int previousIndex() {
                return i.previousIndex() - offset;
            }

            public void remove() {
                i.remove();
                SubList.this.modCount = l.modCount;
                size--;
            }

            public void set(E e) {
                i.set(e);
            }

            public void bdd(E e) {
                i.bdd(e);
                SubList.this.modCount = l.modCount;
                size++;
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new SubList<>(this, fromIndex, toIndex);
    }

    privbte void rbngeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    privbte void rbngeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    privbte String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    privbte void checkForComodificbtion() {
        if (this.modCount != l.modCount)
            throw new ConcurrentModificbtionException();
    }
}

clbss RbndomAccessSubList<E> extends SubList<E> implements RbndomAccess {
    RbndomAccessSubList(AbstrbctList<E> list, int fromIndex, int toIndex) {
        super(list, fromIndex, toIndex);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new RbndomAccessSubList<>(this, fromIndex, toIndex);
    }
}
