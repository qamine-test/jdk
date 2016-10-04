/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.util;

import jbvb.util.AbstrbctList;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.ConcurrentModificbtionException;
import jbvb.util.List;
import jbvb.util.RbndomAccess;

/**
 * Resizbble-brrby implementbtion of the <tt>List</tt> interfbce.  Implements
 * bll optionbl list operbtions, bnd permits bll elements, including
 * <tt>null</tt>.  In bddition to implementing the <tt>List</tt> interfbce,
 * this clbss provides methods to mbnipulbte the size of the brrby thbt is
 * used internblly to store the list.  (This clbss is roughly equivblent to
 * <tt>Vector</tt>, except thbt it is unsynchronized.)<p>
 *
 * The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>,
 * <tt>iterbtor</tt>, bnd <tt>listIterbtor</tt> operbtions run in constbnt
 * time.  The <tt>bdd</tt> operbtion runs in <i>bmortized constbnt time</i>,
 * thbt is, bdding n elements requires O(n) time.  All of the other operbtions
 * run in linebr time (roughly spebking).  The constbnt fbctor is low compbred
 * to thbt for the <tt>LinkedList</tt> implementbtion.<p>
 *
 * Ebch <tt>IdentityArrbyList</tt> instbnce hbs b <i>cbpbcity</i>.  The cbpbcity is
 * the size of the brrby used to store the elements in the list.  It is blwbys
 * bt lebst bs lbrge bs the list size.  As elements bre bdded to bn IdentityArrbyList,
 * its cbpbcity grows butombticblly.  The detbils of the growth policy bre not
 * specified beyond the fbct thbt bdding bn element hbs constbnt bmortized
 * time cost.<p>
 *
 * An bpplicbtion cbn increbse the cbpbcity of bn <tt>IdentityArrbyList</tt> instbnce
 * before bdding b lbrge number of elements using the <tt>ensureCbpbcity</tt>
 * operbtion.  This mby reduce the bmount of incrementbl rebllocbtion.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess bn <tt>IdentityArrbyList</tt> instbnce concurrently,
 * bnd bt lebst one of the threbds modifies the list structurblly, it
 * <i>must</i> be synchronized externblly.  (A structurbl modificbtion is
 * bny operbtion thbt bdds or deletes one or more elements, or explicitly
 * resizes the bbcking brrby; merely setting the vblue of bn element is not
 * b structurbl modificbtion.)  This is typicblly bccomplished by
 * synchronizing on some object thbt nbturblly encbpsulbtes the list.
 *
 * If no such object exists, the list should be "wrbpped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the list:<pre>
 *   List list = Collections.synchronizedList(new IdentityArrbyList(...));</pre>
 *
 * <p>The iterbtors returned by this clbss's <tt>iterbtor</tt> bnd
 * <tt>listIterbtor</tt> methods bre <i>fbil-fbst</i>: if the list is
 * structurblly modified bt bny time bfter the iterbtor is crebted, in bny wby
 * except through the iterbtor's own <tt>remove</tt> or <tt>bdd</tt> methods,
 * the iterbtor will throw b {@link ConcurrentModificbtionException}.  Thus, in
 * the fbce of concurrent modificbtion, the iterbtor fbils quickly bnd clebnly,
 * rbther thbn risking brbitrbry, non-deterministic behbvior bt bn undetermined
 * time in the future.<p>
 *
 * Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness: <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i><p>
 *
 */

public clbss IdentityArrbyList<E> extends AbstrbctList<E>
        implements List<E>, RbndomAccess
{

    /**
     * The brrby buffer into which the elements of the IdentityArrbyList bre stored.
     * The cbpbcity of the IdentityArrbyList is the length of this brrby buffer.
     */
    privbte trbnsient Object[] elementDbtb;

    /**
     * The size of the IdentityArrbyList (the number of elements it contbins).
     *
     * @seribl
     */
    privbte int size;

    /**
     * Constructs bn empty list with the specified initibl cbpbcity.
     *
     * @pbrbm   initiblCbpbcity   the initibl cbpbcity of the list
     * @exception IllegblArgumentException if the specified initibl cbpbcity
     *            is negbtive
     */
    public IdentityArrbyList(int initiblCbpbcity) {
        super();
        if (initiblCbpbcity < 0)
            throw new IllegblArgumentException("Illegbl Cbpbcity: "+
                    initiblCbpbcity);
        this.elementDbtb = new Object[initiblCbpbcity];
    }

    /**
     * Constructs bn empty list with bn initibl cbpbcity of ten.
     */
    public IdentityArrbyList() {
        this(10);
    }

    /**
     * Constructs b list contbining the elements of the specified
     * collection, in the order they bre returned by the collection's
     * iterbtor.
     *
     * @pbrbm c the collection whose elements bre to be plbced into this list
     * @throws NullPointerException if the specified collection is null
     */
    public IdentityArrbyList(Collection<? extends E> c) {
        elementDbtb = c.toArrby();
        size = elementDbtb.length;
        // c.toArrby might (incorrectly) not return Object[] (see 6260652)
        if (elementDbtb.getClbss() != Object[].clbss)
            elementDbtb = Arrbys.copyOf(elementDbtb, size, Object[].clbss);
    }

    /**
     * Trims the cbpbcity of this <tt>IdentityArrbyList</tt> instbnce to be the
     * list's current size.  An bpplicbtion cbn use this operbtion to minimize
     * the storbge of bn <tt>IdentityArrbyList</tt> instbnce.
     */
    public void trimToSize() {
        modCount++;
        int oldCbpbcity = elementDbtb.length;
        if (size < oldCbpbcity) {
            elementDbtb = Arrbys.copyOf(elementDbtb, size);
        }
    }

    /**
     * Increbses the cbpbcity of this <tt>IdentityArrbyList</tt> instbnce, if
     * necessbry, to ensure thbt it cbn hold bt lebst the number of elements
     * specified by the minimum cbpbcity brgument.
     *
     * @pbrbm   minCbpbcity   the desired minimum cbpbcity
     */
    public void ensureCbpbcity(int minCbpbcity) {
        modCount++;
        int oldCbpbcity = elementDbtb.length;
        if (minCbpbcity > oldCbpbcity) {
            Object oldDbtb[] = elementDbtb;
            int newCbpbcity = (oldCbpbcity * 3)/2 + 1;
            if (newCbpbcity < minCbpbcity)
                newCbpbcity = minCbpbcity;
            // minCbpbcity is usublly close to size, so this is b win:
            elementDbtb = Arrbys.copyOf(elementDbtb, newCbpbcity);
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this list contbins no elements.
     *
     * @return <tt>true</tt> if this list contbins no elements
     */
    public boolebn isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contbins the specified element.
     * More formblly, returns <tt>true</tt> if bnd only if this list contbins
     * bt lebst one element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o == e)</tt>.
     *
     * @pbrbm o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contbins the specified element
     */
    public boolebn contbins(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the lowest index <tt>i</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o == get(i))</tt>,
     * or -1 if there is no such index.
     */
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == elementDbtb[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the lbst occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the highest index <tt>i</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o == get(i))</tt>,
     * or -1 if there is no such index.
     */
    public int lbstIndexOf(Object o) {
        for (int i = size-1; i >= 0; i--) {
            if (o == elementDbtb[i]) {
                return i;
            }
        }
        return -1;
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
     * @return bn brrby contbining bll of the elements in this list in
     *         proper sequence
     */
    public Object[] toArrby() {
        return Arrbys.copyOf(elementDbtb, size);
    }

    /**
     * Returns bn brrby contbining bll of the elements in this list in proper
     * sequence (from first to lbst element); the runtime type of the returned
     * brrby is thbt of the specified brrby.  If the list fits in the
     * specified brrby, it is returned therein.  Otherwise, b new brrby is
     * bllocbted with the runtime type of the specified brrby bnd the size of
     * this list.
     *
     * <p>If the list fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn the list), the element in
     * the brrby immedibtely following the end of the collection is set to
     * <tt>null</tt>.  (This is useful in determining the length of the
     * list <i>only</i> if the cbller knows thbt the list does not contbin
     * bny null elements.)
     *
     * @pbrbm b the brrby into which the elements of the list bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose.
     * @return bn brrby contbining the elements of the list
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this list
     * @throws NullPointerException if the specified brrby is null
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T[] b) {
        if (b.length < size)
            // Mbke b new brrby of b's runtime type, but my contents:
            return (T[]) Arrbys.copyOf(elementDbtb, size, b.getClbss());
        System.brrbycopy(elementDbtb, 0, b, 0, size);
        if (b.length > size)
            b[size] = null;
        return b;
    }

    // Positionbl Access Operbtions

    /**
     * Returns the element bt the specified position in this list.
     *
     * @pbrbm  index index of the element to return
     * @return the element bt the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        rbngeCheck(index);

        @SuppressWbrnings("unchecked")
        E rv = (E) elementDbtb[index];
        return rv;
    }

    /**
     * Replbces the element bt the specified position in this list with
     * the specified element.
     *
     * @pbrbm index index of the element to replbce
     * @pbrbm element element to be stored bt the specified position
     * @return the element previously bt the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        rbngeCheck(index);

        @SuppressWbrnings("unchecked")
        E oldVblue = (E) elementDbtb[index];
        elementDbtb[index] = element;
        return oldVblue;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @pbrbm e element to be bppended to this list
     * @return <tt>true</tt> (bs specified by {@link Collection#bdd})
     */
    public boolebn bdd(E e) {
        ensureCbpbcity(size + 1);  // Increments modCount!!
        elementDbtb[size++] = e;
        return true;
    }

    /**
     * Inserts the specified element bt the specified position in this
     * list. Shifts the element currently bt thbt position (if bny) bnd
     * bny subsequent elements to the right (bdds one to their indices).
     *
     * @pbrbm index index bt which the specified element is to be inserted
     * @pbrbm element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void bdd(int index, E element) {
        rbngeCheckForAdd(index);

        ensureCbpbcity(size+1);  // Increments modCount!!
        System.brrbycopy(elementDbtb, index, elementDbtb, index + 1,
                size - index);
        elementDbtb[index] = element;
        size++;
    }

    /**
     * Removes the element bt the specified position in this list.
     * Shifts bny subsequent elements to the left (subtrbcts one from their
     * indices).
     *
     * @pbrbm index the index of the element to be removed
     * @return the element thbt wbs removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        rbngeCheck(index);

        modCount++;
        @SuppressWbrnings("unchecked")
        E oldVblue = (E) elementDbtb[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.brrbycopy(elementDbtb, index+1, elementDbtb, index,
                    numMoved);
        elementDbtb[--size] = null; // Let gc do its work

        return oldVblue;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contbin the element, it is
     * unchbnged.  More formblly, removes the element with the lowest index
     * <tt>i</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o == get(i))</tt>
     * (if such bn element exists).  Returns <tt>true</tt> if this list
     * contbined the specified element (or equivblently, if this list
     * chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contbined the specified element
     */
    public boolebn remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (o == elementDbtb[index]) {
                fbstRemove(index);
                return true;
            }
        }
        return fblse;
    }

    /*
     * Privbte remove method thbt skips bounds checking bnd does not
     * return the vblue removed.
     */
    privbte void fbstRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.brrbycopy(elementDbtb, index+1, elementDbtb, index,
                    numMoved);
        elementDbtb[--size] = null; // Let gc do its work
    }

    /**
     * Removes bll of the elements from this list.  The list will
     * be empty bfter this cbll returns.
     */
    public void clebr() {
        modCount++;

        // Let gc do its work
        for (int i = 0; i < size; i++)
            elementDbtb[i] = null;

        size = 0;
    }

    /**
     * Appends bll of the elements in the specified collection to the end of
     * this list, in the order thbt they bre returned by the
     * specified collection's Iterbtor.  The behbvior of this operbtion is
     * undefined if the specified collection is modified while the operbtion
     * is in progress.  (This implies thbt the behbvior of this cbll is
     * undefined if the specified collection is this list, bnd this
     * list is nonempty.)
     *
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn bddAll(Collection<? extends E> c) {
        Object[] b = c.toArrby();
        int numNew = b.length;
        ensureCbpbcity(size + numNew);  // Increments modCount
        System.brrbycopy(b, 0, elementDbtb, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Inserts bll of the elements in the specified collection into this
     * list, stbrting bt the specified position.  Shifts the element
     * currently bt thbt position (if bny) bnd bny subsequent elements to
     * the right (increbses their indices).  The new elements will bppebr
     * in the list in the order thbt they bre returned by the
     * specified collection's iterbtor.
     *
     * @pbrbm index index bt which to insert the first element from the
     *              specified collection
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn bddAll(int index, Collection<? extends E> c) {
        rbngeCheckForAdd(index);

        Object[] b = c.toArrby();
        int numNew = b.length;
        ensureCbpbcity(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0) {
            System.brrbycopy(elementDbtb, index, elementDbtb, index + numNew, numMoved);
        }

        System.brrbycopy(b, 0, elementDbtb, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Removes from this list bll of the elements whose index is between
     * <tt>fromIndex</tt>, inclusive, bnd <tt>toIndex</tt>, exclusive.
     * Shifts bny succeeding elements to the left (reduces their index).
     * This cbll shortens the list by <tt>(toIndex - fromIndex)</tt> elements.
     * (If <tt>toIndex==fromIndex</tt>, this operbtion hbs no effect.)
     *
     * @pbrbm fromIndex index of first element to be removed
     * @pbrbm toIndex index bfter lbst element to be removed
     * @throws IndexOutOfBoundsException if fromIndex or toIndex out of
     *              rbnge (fromIndex &lt; 0 || fromIndex &gt;= size() || toIndex
     *              &gt; size() || toIndex &lt; fromIndex)
     */
    protected void removeRbnge(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.brrbycopy(elementDbtb, toIndex, elementDbtb, fromIndex,
                numMoved);

        // Let gc do its work
        int newSize = size - (toIndex-fromIndex);
        while (size != newSize)
            elementDbtb[--size] = null;
    }

    /**
     * Checks if the given index is in rbnge.  If not, throws bn bppropribte
     * runtime exception.  This method does *not* check if the index is
     * negbtive: It is blwbys used immedibtely prior to bn brrby bccess,
     * which throws bn ArrbyIndexOutOfBoundsException if index is negbtive.
     */
    privbte void rbngeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * A version of rbngeCheck used by bdd bnd bddAll.
     */
    privbte void rbngeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Constructs bn IndexOutOfBoundsException detbil messbge.
     * Of the mbny possible refbctorings of the error hbndling code,
     * this "outlining" performs best with both server bnd client VMs.
     */
    privbte String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
