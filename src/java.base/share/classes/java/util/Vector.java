/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.function.Consumer;
import jbvb.util.function.Predicbte;
import jbvb.util.function.UnbryOperbtor;

/**
 * The {@code Vector} clbss implements b growbble brrby of
 * objects. Like bn brrby, it contbins components thbt cbn be
 * bccessed using bn integer index. However, the size of b
 * {@code Vector} cbn grow or shrink bs needed to bccommodbte
 * bdding bnd removing items bfter the {@code Vector} hbs been crebted.
 *
 * <p>Ebch vector tries to optimize storbge mbnbgement by mbintbining b
 * {@code cbpbcity} bnd b {@code cbpbcityIncrement}. The
 * {@code cbpbcity} is blwbys bt lebst bs lbrge bs the vector
 * size; it is usublly lbrger becbuse bs components bre bdded to the
 * vector, the vector's storbge increbses in chunks the size of
 * {@code cbpbcityIncrement}. An bpplicbtion cbn increbse the
 * cbpbcity of b vector before inserting b lbrge number of
 * components; this reduces the bmount of incrementbl rebllocbtion.
 *
 * <p id="fbil-fbst">
 * The iterbtors returned by this clbss's {@link #iterbtor() iterbtor} bnd
 * {@link #listIterbtor(int) listIterbtor} methods bre <em>fbil-fbst</em>:
 * if the vector is structurblly modified bt bny time bfter the iterbtor is
 * crebted, in bny wby except through the iterbtor's own
 * {@link ListIterbtor#remove() remove} or
 * {@link ListIterbtor#bdd(Object) bdd} methods, the iterbtor will throw b
 * {@link ConcurrentModificbtionException}.  Thus, in the fbce of
 * concurrent modificbtion, the iterbtor fbils quickly bnd clebnly, rbther
 * thbn risking brbitrbry, non-deterministic behbvior bt bn undetermined
 * time in the future.  The {@link Enumerbtion Enumerbtions} returned by
 * the {@link #elements() elements} method bre <em>not</em> fbil-fbst; if the
 * Vector is structurblly modified bt bny time bfter the enumerbtion is
 * crebted then the results of enumerbting bre undefined.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw {@code ConcurrentModificbtionException} on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:  <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>As of the Jbvb 2 plbtform v1.2, this clbss wbs retrofitted to
 * implement the {@link List} interfbce, mbking it b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.  Unlike the new collection
 * implementbtions, {@code Vector} is synchronized.  If b threbd-sbfe
 * implementbtion is not needed, it is recommended to use {@link
 * ArrbyList} in plbce of {@code Vector}.
 *
 * @pbrbm <E> Type of component elements
 *
 * @buthor  Lee Boynton
 * @buthor  Jonbthbn Pbyne
 * @see Collection
 * @see LinkedList
 * @since   1.0
 */
public clbss Vector<E>
    extends AbstrbctList<E>
    implements List<E>, RbndomAccess, Clonebble, jbvb.io.Seriblizbble
{
    /**
     * The brrby buffer into which the components of the vector bre
     * stored. The cbpbcity of the vector is the length of this brrby buffer,
     * bnd is bt lebst lbrge enough to contbin bll the vector's elements.
     *
     * <p>Any brrby elements following the lbst element in the Vector bre null.
     *
     * @seribl
     */
    protected Object[] elementDbtb;

    /**
     * The number of vblid components in this {@code Vector} object.
     * Components {@code elementDbtb[0]} through
     * {@code elementDbtb[elementCount-1]} bre the bctubl items.
     *
     * @seribl
     */
    protected int elementCount;

    /**
     * The bmount by which the cbpbcity of the vector is butombticblly
     * incremented when its size becomes grebter thbn its cbpbcity.  If
     * the cbpbcity increment is less thbn or equbl to zero, the cbpbcity
     * of the vector is doubled ebch time it needs to grow.
     *
     * @seribl
     */
    protected int cbpbcityIncrement;

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -2767605614048989439L;

    /**
     * Constructs bn empty vector with the specified initibl cbpbcity bnd
     * cbpbcity increment.
     *
     * @pbrbm   initiblCbpbcity     the initibl cbpbcity of the vector
     * @pbrbm   cbpbcityIncrement   the bmount by which the cbpbcity is
     *                              increbsed when the vector overflows
     * @throws IllegblArgumentException if the specified initibl cbpbcity
     *         is negbtive
     */
    public Vector(int initiblCbpbcity, int cbpbcityIncrement) {
        super();
        if (initiblCbpbcity < 0)
            throw new IllegblArgumentException("Illegbl Cbpbcity: "+
                                               initiblCbpbcity);
        this.elementDbtb = new Object[initiblCbpbcity];
        this.cbpbcityIncrement = cbpbcityIncrement;
    }

    /**
     * Constructs bn empty vector with the specified initibl cbpbcity bnd
     * with its cbpbcity increment equbl to zero.
     *
     * @pbrbm   initiblCbpbcity   the initibl cbpbcity of the vector
     * @throws IllegblArgumentException if the specified initibl cbpbcity
     *         is negbtive
     */
    public Vector(int initiblCbpbcity) {
        this(initiblCbpbcity, 0);
    }

    /**
     * Constructs bn empty vector so thbt its internbl dbtb brrby
     * hbs size {@code 10} bnd its stbndbrd cbpbcity increment is
     * zero.
     */
    public Vector() {
        this(10);
    }

    /**
     * Constructs b vector contbining the elements of the specified
     * collection, in the order they bre returned by the collection's
     * iterbtor.
     *
     * @pbrbm c the collection whose elements bre to be plbced into this
     *       vector
     * @throws NullPointerException if the specified collection is null
     * @since   1.2
     */
    public Vector(Collection<? extends E> c) {
        elementDbtb = c.toArrby();
        elementCount = elementDbtb.length;
        // c.toArrby might (incorrectly) not return Object[] (see 6260652)
        if (elementDbtb.getClbss() != Object[].clbss)
            elementDbtb = Arrbys.copyOf(elementDbtb, elementCount, Object[].clbss);
    }

    /**
     * Copies the components of this vector into the specified brrby.
     * The item bt index {@code k} in this vector is copied into
     * component {@code k} of {@code bnArrby}.
     *
     * @pbrbm  bnArrby the brrby into which the components get copied
     * @throws NullPointerException if the given brrby is null
     * @throws IndexOutOfBoundsException if the specified brrby is not
     *         lbrge enough to hold bll the components of this vector
     * @throws ArrbyStoreException if b component of this vector is not of
     *         b runtime type thbt cbn be stored in the specified brrby
     * @see #toArrby(Object[])
     */
    public synchronized void copyInto(Object[] bnArrby) {
        System.brrbycopy(elementDbtb, 0, bnArrby, 0, elementCount);
    }

    /**
     * Trims the cbpbcity of this vector to be the vector's current
     * size. If the cbpbcity of this vector is lbrger thbn its current
     * size, then the cbpbcity is chbnged to equbl the size by replbcing
     * its internbl dbtb brrby, kept in the field {@code elementDbtb},
     * with b smbller one. An bpplicbtion cbn use this operbtion to
     * minimize the storbge of b vector.
     */
    public synchronized void trimToSize() {
        modCount++;
        int oldCbpbcity = elementDbtb.length;
        if (elementCount < oldCbpbcity) {
            elementDbtb = Arrbys.copyOf(elementDbtb, elementCount);
        }
    }

    /**
     * Increbses the cbpbcity of this vector, if necessbry, to ensure
     * thbt it cbn hold bt lebst the number of components specified by
     * the minimum cbpbcity brgument.
     *
     * <p>If the current cbpbcity of this vector is less thbn
     * {@code minCbpbcity}, then its cbpbcity is increbsed by replbcing its
     * internbl dbtb brrby, kept in the field {@code elementDbtb}, with b
     * lbrger one.  The size of the new dbtb brrby will be the old size plus
     * {@code cbpbcityIncrement}, unless the vblue of
     * {@code cbpbcityIncrement} is less thbn or equbl to zero, in which cbse
     * the new cbpbcity will be twice the old cbpbcity; but if this new size
     * is still smbller thbn {@code minCbpbcity}, then the new cbpbcity will
     * be {@code minCbpbcity}.
     *
     * @pbrbm minCbpbcity the desired minimum cbpbcity
     */
    public synchronized void ensureCbpbcity(int minCbpbcity) {
        if (minCbpbcity > 0) {
            modCount++;
            ensureCbpbcityHelper(minCbpbcity);
        }
    }

    /**
     * This implements the unsynchronized sembntics of ensureCbpbcity.
     * Synchronized methods in this clbss cbn internblly cbll this
     * method for ensuring cbpbcity without incurring the cost of bn
     * extrb synchronizbtion.
     *
     * @see #ensureCbpbcity(int)
     */
    privbte void ensureCbpbcityHelper(int minCbpbcity) {
        // overflow-conscious code
        if (minCbpbcity - elementDbtb.length > 0)
            grow(minCbpbcity);
    }

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic finbl int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    privbte void grow(int minCbpbcity) {
        // overflow-conscious code
        int oldCbpbcity = elementDbtb.length;
        int newCbpbcity = oldCbpbcity + ((cbpbcityIncrement > 0) ?
                                         cbpbcityIncrement : oldCbpbcity);
        if (newCbpbcity - minCbpbcity < 0)
            newCbpbcity = minCbpbcity;
        if (newCbpbcity - MAX_ARRAY_SIZE > 0)
            newCbpbcity = hugeCbpbcity(minCbpbcity);
        elementDbtb = Arrbys.copyOf(elementDbtb, newCbpbcity);
    }

    privbte stbtic int hugeCbpbcity(int minCbpbcity) {
        if (minCbpbcity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCbpbcity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    /**
     * Sets the size of this vector. If the new size is grebter thbn the
     * current size, new {@code null} items bre bdded to the end of
     * the vector. If the new size is less thbn the current size, bll
     * components bt index {@code newSize} bnd grebter bre discbrded.
     *
     * @pbrbm  newSize   the new size of this vector
     * @throws ArrbyIndexOutOfBoundsException if the new size is negbtive
     */
    public synchronized void setSize(int newSize) {
        modCount++;
        if (newSize > elementCount) {
            ensureCbpbcityHelper(newSize);
        } else {
            for (int i = newSize ; i < elementCount ; i++) {
                elementDbtb[i] = null;
            }
        }
        elementCount = newSize;
    }

    /**
     * Returns the current cbpbcity of this vector.
     *
     * @return  the current cbpbcity (the length of its internbl
     *          dbtb brrby, kept in the field {@code elementDbtb}
     *          of this vector)
     */
    public synchronized int cbpbcity() {
        return elementDbtb.length;
    }

    /**
     * Returns the number of components in this vector.
     *
     * @return  the number of components in this vector
     */
    public synchronized int size() {
        return elementCount;
    }

    /**
     * Tests if this vector hbs no components.
     *
     * @return  {@code true} if bnd only if this vector hbs
     *          no components, thbt is, its size is zero;
     *          {@code fblse} otherwise.
     */
    public synchronized boolebn isEmpty() {
        return elementCount == 0;
    }

    /**
     * Returns bn enumerbtion of the components of this vector. The
     * returned {@code Enumerbtion} object will generbte bll items in
     * this vector. The first item generbted is the item bt index {@code 0},
     * then the item bt index {@code 1}, bnd so on. If the vector is
     * structurblly modified while enumerbting over the elements then the
     * results of enumerbting bre undefined.
     *
     * @return  bn enumerbtion of the components of this vector
     * @see     Iterbtor
     */
    public Enumerbtion<E> elements() {
        return new Enumerbtion<E>() {
            int count = 0;

            public boolebn hbsMoreElements() {
                return count < elementCount;
            }

            public E nextElement() {
                synchronized (Vector.this) {
                    if (count < elementCount) {
                        return elementDbtb(count++);
                    }
                }
                throw new NoSuchElementException("Vector Enumerbtion");
            }
        };
    }

    /**
     * Returns {@code true} if this vector contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this vector
     * contbins bt lebst one element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this vector is to be tested
     * @return {@code true} if this vector contbins the specified element
     */
    public boolebn contbins(Object o) {
        return indexOf(o, 0) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this vector, or -1 if this vector does not contbin the element.
     * More formblly, returns the lowest index {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the first occurrence of the specified element in
     *         this vector, or -1 if this vector does not contbin the element
     */
    public int indexOf(Object o) {
        return indexOf(o, 0);
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this vector, sebrching forwbrds from {@code index}, or returns -1 if
     * the element is not found.
     * More formblly, returns the lowest index {@code i} such thbt
     * <tt>(i&nbsp;&gt;=&nbsp;index&nbsp;&bmp;&bmp;&nbsp;(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i))))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @pbrbm index index to stbrt sebrching from
     * @return the index of the first occurrence of the element in
     *         this vector bt position {@code index} or lbter in the vector;
     *         {@code -1} if the element is not found.
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     * @see     Object#equbls(Object)
     */
    public synchronized int indexOf(Object o, int index) {
        if (o == null) {
            for (int i = index ; i < elementCount ; i++)
                if (elementDbtb[i]==null)
                    return i;
        } else {
            for (int i = index ; i < elementCount ; i++)
                if (o.equbls(elementDbtb[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the index of the lbst occurrence of the specified element
     * in this vector, or -1 if this vector does not contbin the element.
     * More formblly, returns the highest index {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the lbst occurrence of the specified element in
     *         this vector, or -1 if this vector does not contbin the element
     */
    public synchronized int lbstIndexOf(Object o) {
        return lbstIndexOf(o, elementCount-1);
    }

    /**
     * Returns the index of the lbst occurrence of the specified element in
     * this vector, sebrching bbckwbrds from {@code index}, or returns -1 if
     * the element is not found.
     * More formblly, returns the highest index {@code i} such thbt
     * <tt>(i&nbsp;&lt;=&nbsp;index&nbsp;&bmp;&bmp;&nbsp;(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i))))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @pbrbm index index to stbrt sebrching bbckwbrds from
     * @return the index of the lbst occurrence of the element bt position
     *         less thbn or equbl to {@code index} in this vector;
     *         -1 if the element is not found.
     * @throws IndexOutOfBoundsException if the specified index is grebter
     *         thbn or equbl to the current size of this vector
     */
    public synchronized int lbstIndexOf(Object o, int index) {
        if (index >= elementCount)
            throw new IndexOutOfBoundsException(index + " >= "+ elementCount);

        if (o == null) {
            for (int i = index; i >= 0; i--)
                if (elementDbtb[i]==null)
                    return i;
        } else {
            for (int i = index; i >= 0; i--)
                if (o.equbls(elementDbtb[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the component bt the specified index.
     *
     * <p>This method is identicbl in functionblity to the {@link #get(int)}
     * method (which is pbrt of the {@link List} interfbce).
     *
     * @pbrbm      index   bn index into this vector
     * @return     the component bt the specified index
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index >= size()})
     */
    public synchronized E elementAt(int index) {
        if (index >= elementCount) {
            throw new ArrbyIndexOutOfBoundsException(index + " >= " + elementCount);
        }

        return elementDbtb(index);
    }

    /**
     * Returns the first component (the item bt index {@code 0}) of
     * this vector.
     *
     * @return     the first component of this vector
     * @throws NoSuchElementException if this vector hbs no components
     */
    public synchronized E firstElement() {
        if (elementCount == 0) {
            throw new NoSuchElementException();
        }
        return elementDbtb(0);
    }

    /**
     * Returns the lbst component of the vector.
     *
     * @return  the lbst component of the vector, i.e., the component bt index
     *          <code>size()&nbsp;-&nbsp;1</code>.
     * @throws NoSuchElementException if this vector is empty
     */
    public synchronized E lbstElement() {
        if (elementCount == 0) {
            throw new NoSuchElementException();
        }
        return elementDbtb(elementCount - 1);
    }

    /**
     * Sets the component bt the specified {@code index} of this
     * vector to be the specified object. The previous component bt thbt
     * position is discbrded.
     *
     * <p>The index must be b vblue grebter thbn or equbl to {@code 0}
     * bnd less thbn the current size of the vector.
     *
     * <p>This method is identicbl in functionblity to the
     * {@link #set(int, Object) set(int, E)}
     * method (which is pbrt of the {@link List} interfbce). Note thbt the
     * {@code set} method reverses the order of the pbrbmeters, to more closely
     * mbtch brrby usbge.  Note blso thbt the {@code set} method returns the
     * old vblue thbt wbs stored bt the specified position.
     *
     * @pbrbm      obj     whbt the component is to be set to
     * @pbrbm      index   the specified index
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index >= size()})
     */
    public synchronized void setElementAt(E obj, int index) {
        if (index >= elementCount) {
            throw new ArrbyIndexOutOfBoundsException(index + " >= " +
                                                     elementCount);
        }
        elementDbtb[index] = obj;
    }

    /**
     * Deletes the component bt the specified index. Ebch component in
     * this vector with bn index grebter or equbl to the specified
     * {@code index} is shifted downwbrd to hbve bn index one
     * smbller thbn the vblue it hbd previously. The size of this vector
     * is decrebsed by {@code 1}.
     *
     * <p>The index must be b vblue grebter thbn or equbl to {@code 0}
     * bnd less thbn the current size of the vector.
     *
     * <p>This method is identicbl in functionblity to the {@link #remove(int)}
     * method (which is pbrt of the {@link List} interfbce).  Note thbt the
     * {@code remove} method returns the old vblue thbt wbs stored bt the
     * specified position.
     *
     * @pbrbm      index   the index of the object to remove
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index >= size()})
     */
    public synchronized void removeElementAt(int index) {
        if (index >= elementCount) {
            throw new ArrbyIndexOutOfBoundsException(index + " >= " +
                                                     elementCount);
        }
        else if (index < 0) {
            throw new ArrbyIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1;
        if (j > 0) {
            System.brrbycopy(elementDbtb, index + 1, elementDbtb, index, j);
        }
        modCount++;
        elementCount--;
        elementDbtb[elementCount] = null; /* to let gc do its work */
    }

    /**
     * Inserts the specified object bs b component in this vector bt the
     * specified {@code index}. Ebch component in this vector with
     * bn index grebter or equbl to the specified {@code index} is
     * shifted upwbrd to hbve bn index one grebter thbn the vblue it hbd
     * previously.
     *
     * <p>The index must be b vblue grebter thbn or equbl to {@code 0}
     * bnd less thbn or equbl to the current size of the vector. (If the
     * index is equbl to the current size of the vector, the new element
     * is bppended to the Vector.)
     *
     * <p>This method is identicbl in functionblity to the
     * {@link #bdd(int, Object) bdd(int, E)}
     * method (which is pbrt of the {@link List} interfbce).  Note thbt the
     * {@code bdd} method reverses the order of the pbrbmeters, to more closely
     * mbtch brrby usbge.
     *
     * @pbrbm      obj     the component to insert
     * @pbrbm      index   where to insert the new component
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index > size()})
     */
    public synchronized void insertElementAt(E obj, int index) {
        if (index > elementCount) {
            throw new ArrbyIndexOutOfBoundsException(index
                                                     + " > " + elementCount);
        }
        ensureCbpbcityHelper(elementCount + 1);
        System.brrbycopy(elementDbtb, index, elementDbtb, index + 1, elementCount - index);
        elementDbtb[index] = obj;
        modCount++;
        elementCount++;
    }

    /**
     * Adds the specified component to the end of this vector,
     * increbsing its size by one. The cbpbcity of this vector is
     * increbsed if its size becomes grebter thbn its cbpbcity.
     *
     * <p>This method is identicbl in functionblity to the
     * {@link #bdd(Object) bdd(E)}
     * method (which is pbrt of the {@link List} interfbce).
     *
     * @pbrbm   obj   the component to be bdded
     */
    public synchronized void bddElement(E obj) {
        ensureCbpbcityHelper(elementCount + 1);
        modCount++;
        elementDbtb[elementCount++] = obj;
    }

    /**
     * Removes the first (lowest-indexed) occurrence of the brgument
     * from this vector. If the object is found in this vector, ebch
     * component in the vector with bn index grebter or equbl to the
     * object's index is shifted downwbrd to hbve bn index one smbller
     * thbn the vblue it hbd previously.
     *
     * <p>This method is identicbl in functionblity to the
     * {@link #remove(Object)} method (which is pbrt of the
     * {@link List} interfbce).
     *
     * @pbrbm   obj   the component to be removed
     * @return  {@code true} if the brgument wbs b component of this
     *          vector; {@code fblse} otherwise.
     */
    public synchronized boolebn removeElement(Object obj) {
        modCount++;
        int i = indexOf(obj);
        if (i >= 0) {
            removeElementAt(i);
            return true;
        }
        return fblse;
    }

    /**
     * Removes bll components from this vector bnd sets its size to zero.
     *
     * <p>This method is identicbl in functionblity to the {@link #clebr}
     * method (which is pbrt of the {@link List} interfbce).
     */
    public synchronized void removeAllElements() {
        // Let gc do its work
        for (int i = 0; i < elementCount; i++)
            elementDbtb[i] = null;

        modCount++;
        elementCount = 0;
    }

    /**
     * Returns b clone of this vector. The copy will contbin b
     * reference to b clone of the internbl dbtb brrby, not b reference
     * to the originbl internbl dbtb brrby of this {@code Vector} object.
     *
     * @return  b clone of this vector
     */
    public synchronized Object clone() {
        try {
            @SuppressWbrnings("unchecked")
                Vector<E> v = (Vector<E>) super.clone();
            v.elementDbtb = Arrbys.copyOf(elementDbtb, elementCount);
            v.modCount = 0;
            return v;
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this Vector
     * in the correct order.
     *
     * @since 1.2
     */
    public synchronized Object[] toArrby() {
        return Arrbys.copyOf(elementDbtb, elementCount);
    }

    /**
     * Returns bn brrby contbining bll of the elements in this Vector in the
     * correct order; the runtime type of the returned brrby is thbt of the
     * specified brrby.  If the Vector fits in the specified brrby, it is
     * returned therein.  Otherwise, b new brrby is bllocbted with the runtime
     * type of the specified brrby bnd the size of this Vector.
     *
     * <p>If the Vector fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn the Vector),
     * the element in the brrby immedibtely following the end of the
     * Vector is set to null.  (This is useful in determining the length
     * of the Vector <em>only</em> if the cbller knows thbt the Vector
     * does not contbin bny null elements.)
     *
     * @pbrbm <T> type of brrby elements. The sbme type bs {@code <E>} or b
     * supertype of {@code <E>}.
     * @pbrbm b the brrby into which the elements of the Vector bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose.
     * @return bn brrby contbining the elements of the Vector
     * @throws ArrbyStoreException if the runtime type of b, {@code <T>}, is not
     * b supertype of the runtime type, {@code <E>}, of every element in this
     * Vector
     * @throws NullPointerException if the given brrby is null
     * @since 1.2
     */
    @SuppressWbrnings("unchecked")
    public synchronized <T> T[] toArrby(T[] b) {
        if (b.length < elementCount)
            return (T[]) Arrbys.copyOf(elementDbtb, elementCount, b.getClbss());

        System.brrbycopy(elementDbtb, 0, b, 0, elementCount);

        if (b.length > elementCount)
            b[elementCount] = null;

        return b;
    }

    // Positionbl Access Operbtions

    @SuppressWbrnings("unchecked")
    E elementDbtb(int index) {
        return (E) elementDbtb[index];
    }

    /**
     * Returns the element bt the specified position in this Vector.
     *
     * @pbrbm index index of the element to return
     * @return object bt the specified index
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *            ({@code index < 0 || index >= size()})
     * @since 1.2
     */
    public synchronized E get(int index) {
        if (index >= elementCount)
            throw new ArrbyIndexOutOfBoundsException(index);

        return elementDbtb(index);
    }

    /**
     * Replbces the element bt the specified position in this Vector with the
     * specified element.
     *
     * @pbrbm index index of the element to replbce
     * @pbrbm element element to be stored bt the specified position
     * @return the element previously bt the specified position
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index >= size()})
     * @since 1.2
     */
    public synchronized E set(int index, E element) {
        if (index >= elementCount)
            throw new ArrbyIndexOutOfBoundsException(index);

        E oldVblue = elementDbtb(index);
        elementDbtb[index] = element;
        return oldVblue;
    }

    /**
     * Appends the specified element to the end of this Vector.
     *
     * @pbrbm e element to be bppended to this Vector
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @since 1.2
     */
    public synchronized boolebn bdd(E e) {
        ensureCbpbcityHelper(elementCount + 1);
        modCount++;
        elementDbtb[elementCount++] = e;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element in this Vector
     * If the Vector does not contbin the element, it is unchbnged.  More
     * formblly, removes the element with the lowest index i such thbt
     * {@code (o==null ? get(i)==null : o.equbls(get(i)))} (if such
     * bn element exists).
     *
     * @pbrbm o element to be removed from this Vector, if present
     * @return true if the Vector contbined the specified element
     * @since 1.2
     */
    public boolebn remove(Object o) {
        return removeElement(o);
    }

    /**
     * Inserts the specified element bt the specified position in this Vector.
     * Shifts the element currently bt thbt position (if bny) bnd bny
     * subsequent elements to the right (bdds one to their indices).
     *
     * @pbrbm index index bt which the specified element is to be inserted
     * @pbrbm element element to be inserted
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index > size()})
     * @since 1.2
     */
    public void bdd(int index, E element) {
        insertElementAt(element, index);
    }

    /**
     * Removes the element bt the specified position in this Vector.
     * Shifts bny subsequent elements to the left (subtrbcts one from their
     * indices).  Returns the element thbt wbs removed from the Vector.
     *
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index >= size()})
     * @pbrbm index the index of the element to be removed
     * @return element thbt wbs removed
     * @since 1.2
     */
    public synchronized E remove(int index) {
        modCount++;
        if (index >= elementCount)
            throw new ArrbyIndexOutOfBoundsException(index);
        E oldVblue = elementDbtb(index);

        int numMoved = elementCount - index - 1;
        if (numMoved > 0)
            System.brrbycopy(elementDbtb, index+1, elementDbtb, index,
                             numMoved);
        elementDbtb[--elementCount] = null; // Let gc do its work

        return oldVblue;
    }

    /**
     * Removes bll of the elements from this Vector.  The Vector will
     * be empty bfter this cbll returns (unless it throws bn exception).
     *
     * @since 1.2
     */
    public void clebr() {
        removeAllElements();
    }

    // Bulk Operbtions

    /**
     * Returns true if this Vector contbins bll of the elements in the
     * specified Collection.
     *
     * @pbrbm   c b collection whose elements will be tested for contbinment
     *          in this Vector
     * @return true if this Vector contbins bll of the elements in the
     *         specified collection
     * @throws NullPointerException if the specified collection is null
     */
    public synchronized boolebn contbinsAll(Collection<?> c) {
        return super.contbinsAll(c);
    }

    /**
     * Appends bll of the elements in the specified Collection to the end of
     * this Vector, in the order thbt they bre returned by the specified
     * Collection's Iterbtor.  The behbvior of this operbtion is undefined if
     * the specified Collection is modified while the operbtion is in progress.
     * (This implies thbt the behbvior of this cbll is undefined if the
     * specified Collection is this Vector, bnd this Vector is nonempty.)
     *
     * @pbrbm c elements to be inserted into this Vector
     * @return {@code true} if this Vector chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     * @since 1.2
     */
    public boolebn bddAll(Collection<? extends E> c) {
        Object[] b = c.toArrby();
        int numNew = b.length;
        if (numNew > 0) {
            synchronized (this) {
                ensureCbpbcityHelper(elementCount + numNew);
                System.brrbycopy(b, 0, elementDbtb, elementCount, numNew);
                modCount++;
                elementCount += numNew;
            }
        }
        return numNew > 0;
    }

    /**
     * Removes from this Vector bll of its elements thbt bre contbined in the
     * specified Collection.
     *
     * @pbrbm c b collection of elements to be removed from the Vector
     * @return true if this Vector chbnged bs b result of the cbll
     * @throws ClbssCbstException if the types of one or more elements
     *         in this vector bre incompbtible with the specified
     *         collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this vector contbins one or more null
     *         elements bnd the specified collection does not support null
     *         elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @since 1.2
     */
    public synchronized boolebn removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    /**
     * Retbins only the elements in this Vector thbt bre contbined in the
     * specified Collection.  In other words, removes from this Vector bll
     * of its elements thbt bre not contbined in the specified Collection.
     *
     * @pbrbm c b collection of elements to be retbined in this Vector
     *          (bll other elements bre removed)
     * @return true if this Vector chbnged bs b result of the cbll
     * @throws ClbssCbstException if the types of one or more elements
     *         in this vector bre incompbtible with the specified
     *         collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this vector contbins one or more null
     *         elements bnd the specified collection does not support null
     *         elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @since 1.2
     */
    public synchronized boolebn retbinAll(Collection<?> c) {
        return super.retbinAll(c);
    }

    /**
     * Inserts bll of the elements in the specified Collection into this
     * Vector bt the specified position.  Shifts the element currently bt
     * thbt position (if bny) bnd bny subsequent elements to the right
     * (increbses their indices).  The new elements will bppebr in the Vector
     * in the order thbt they bre returned by the specified Collection's
     * iterbtor.
     *
     * @pbrbm index index bt which to insert the first element from the
     *              specified collection
     * @pbrbm c elements to be inserted into this Vector
     * @return {@code true} if this Vector chbnged bs b result of the cbll
     * @throws ArrbyIndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index > size()})
     * @throws NullPointerException if the specified collection is null
     * @since 1.2
     */
    public synchronized boolebn bddAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > elementCount)
            throw new ArrbyIndexOutOfBoundsException(index);

        Object[] b = c.toArrby();
        int numNew = b.length;

        if (numNew > 0) {
            ensureCbpbcityHelper(elementCount + numNew);

            int numMoved = elementCount - index;
            if (numMoved > 0)
                System.brrbycopy(elementDbtb, index, elementDbtb,
                        index + numNew, numMoved);

             System.brrbycopy(b, 0, elementDbtb, index, numNew);
             elementCount += numNew;
             modCount++;
        }
        return numNew > 0;
    }

    /**
     * Compbres the specified Object with this Vector for equblity.  Returns
     * true if bnd only if the specified Object is blso b List, both Lists
     * hbve the sbme size, bnd bll corresponding pbirs of elements in the two
     * Lists bre <em>equbl</em>.  (Two elements {@code e1} bnd
     * {@code e2} bre <em>equbl</em> if {@code (e1==null ? e2==null :
     * e1.equbls(e2))}.)  In other words, two Lists bre defined to be
     * equbl if they contbin the sbme elements in the sbme order.
     *
     * @pbrbm o the Object to be compbred for equblity with this Vector
     * @return true if the specified Object is equbl to this Vector
     */
    public synchronized boolebn equbls(Object o) {
        return super.equbls(o);
    }

    /**
     * Returns the hbsh code vblue for this Vector.
     */
    public synchronized int hbshCode() {
        return super.hbshCode();
    }

    /**
     * Returns b string representbtion of this Vector, contbining
     * the String representbtion of ebch element.
     */
    public synchronized String toString() {
        return super.toString();
    }

    /**
     * Returns b view of the portion of this List between fromIndex,
     * inclusive, bnd toIndex, exclusive.  (If fromIndex bnd toIndex bre
     * equbl, the returned List is empty.)  The returned List is bbcked by this
     * List, so chbnges in the returned List bre reflected in this List, bnd
     * vice-versb.  The returned List supports bll of the optionbl List
     * operbtions supported by this List.
     *
     * <p>This method eliminbtes the need for explicit rbnge operbtions (of
     * the sort thbt commonly exist for brrbys).  Any operbtion thbt expects
     * b List cbn be used bs b rbnge operbtion by operbting on b subList view
     * instebd of b whole List.  For exbmple, the following idiom
     * removes b rbnge of elements from b List:
     * <pre>
     *      list.subList(from, to).clebr();
     * </pre>
     * Similbr idioms mby be constructed for indexOf bnd lbstIndexOf,
     * bnd bll of the blgorithms in the Collections clbss cbn be bpplied to
     * b subList.
     *
     * <p>The sembntics of the List returned by this method become undefined if
     * the bbcking list (i.e., this List) is <i>structurblly modified</i> in
     * bny wby other thbn vib the returned List.  (Structurbl modificbtions bre
     * those thbt chbnge the size of the List, or otherwise perturb it in such
     * b fbshion thbt iterbtions in progress mby yield incorrect results.)
     *
     * @pbrbm fromIndex low endpoint (inclusive) of the subList
     * @pbrbm toIndex high endpoint (exclusive) of the subList
     * @return b view of the specified rbnge within this List
     * @throws IndexOutOfBoundsException if bn endpoint index vblue is out of rbnge
     *         {@code (fromIndex < 0 || toIndex > size)}
     * @throws IllegblArgumentException if the endpoint indices bre out of order
     *         {@code (fromIndex > toIndex)}
     */
    public synchronized List<E> subList(int fromIndex, int toIndex) {
        return Collections.synchronizedList(super.subList(fromIndex, toIndex),
                                            this);
    }

    /**
     * Removes from this list bll of the elements whose index is between
     * {@code fromIndex}, inclusive, bnd {@code toIndex}, exclusive.
     * Shifts bny succeeding elements to the left (reduces their index).
     * This cbll shortens the list by {@code (toIndex - fromIndex)} elements.
     * (If {@code toIndex==fromIndex}, this operbtion hbs no effect.)
     */
    protected synchronized void removeRbnge(int fromIndex, int toIndex) {
        int numMoved = elementCount - toIndex;
        System.brrbycopy(elementDbtb, toIndex, elementDbtb, fromIndex,
                         numMoved);

        // Let gc do its work
        modCount++;
        int newElementCount = elementCount - (toIndex-fromIndex);
        while (elementCount != newElementCount)
            elementDbtb[--elementCount] = null;
    }

    /**
     * Sbve the stbte of the {@code Vector} instbnce to b strebm (thbt
     * is, seriblize it).
     * This method performs synchronizbtion to ensure the consistency
     * of the seriblized dbtb.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        finbl jbvb.io.ObjectOutputStrebm.PutField fields = s.putFields();
        finbl Object[] dbtb;
        synchronized (this) {
            fields.put("cbpbcityIncrement", cbpbcityIncrement);
            fields.put("elementCount", elementCount);
            dbtb = elementDbtb.clone();
        }
        fields.put("elementDbtb", dbtb);
        s.writeFields();
    }

    /**
     * Returns b list iterbtor over the elements in this list (in proper
     * sequence), stbrting bt the specified position in the list.
     * The specified index indicbtes the first element thbt would be
     * returned by bn initibl cbll to {@link ListIterbtor#next next}.
     * An initibl cbll to {@link ListIterbtor#previous previous} would
     * return the element with the specified index minus one.
     *
     * <p>The returned list iterbtor is <b href="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public synchronized ListIterbtor<E> listIterbtor(int index) {
        if (index < 0 || index > elementCount)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }

    /**
     * Returns b list iterbtor over the elements in this list (in proper
     * sequence).
     *
     * <p>The returned list iterbtor is <b href="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @see #listIterbtor(int)
     */
    public synchronized ListIterbtor<E> listIterbtor() {
        return new ListItr(0);
    }

    /**
     * Returns bn iterbtor over the elements in this list in proper sequence.
     *
     * <p>The returned iterbtor is <b href="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @return bn iterbtor over the elements in this list in proper sequence
     */
    public synchronized Iterbtor<E> iterbtor() {
        return new Itr();
    }

    /**
     * An optimized version of AbstrbctList.Itr
     */
    privbte clbss Itr implements Iterbtor<E> {
        int cursor;       // index of next element to return
        int lbstRet = -1; // index of lbst element returned; -1 if no such
        int expectedModCount = modCount;

        public boolebn hbsNext() {
            // Rbcy but within spec, since modificbtions bre checked
            // within or bfter synchronizbtion in next/previous
            return cursor != elementCount;
        }

        public E next() {
            synchronized (Vector.this) {
                checkForComodificbtion();
                int i = cursor;
                if (i >= elementCount)
                    throw new NoSuchElementException();
                cursor = i + 1;
                return elementDbtb(lbstRet = i);
            }
        }

        public void remove() {
            if (lbstRet == -1)
                throw new IllegblStbteException();
            synchronized (Vector.this) {
                checkForComodificbtion();
                Vector.this.remove(lbstRet);
                expectedModCount = modCount;
            }
            cursor = lbstRet;
            lbstRet = -1;
        }

        @Override
        public void forEbchRembining(Consumer<? super E> bction) {
            Objects.requireNonNull(bction);
            synchronized (Vector.this) {
                finbl int size = elementCount;
                int i = cursor;
                if (i >= size) {
                    return;
                }
        @SuppressWbrnings("unchecked")
                finbl E[] elementDbtb = (E[]) Vector.this.elementDbtb;
                if (i >= elementDbtb.length) {
                    throw new ConcurrentModificbtionException();
                }
                while (i != size && modCount == expectedModCount) {
                    bction.bccept(elementDbtb[i++]);
                }
                // updbte once bt end of iterbtion to reduce hebp write trbffic
                cursor = i;
                lbstRet = i - 1;
                checkForComodificbtion();
            }
        }

        finbl void checkForComodificbtion() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
        }
    }

    /**
     * An optimized version of AbstrbctList.ListItr
     */
    finbl clbss ListItr extends Itr implements ListIterbtor<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolebn hbsPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public E previous() {
            synchronized (Vector.this) {
                checkForComodificbtion();
                int i = cursor - 1;
                if (i < 0)
                    throw new NoSuchElementException();
                cursor = i;
                return elementDbtb(lbstRet = i);
            }
        }

        public void set(E e) {
            if (lbstRet == -1)
                throw new IllegblStbteException();
            synchronized (Vector.this) {
                checkForComodificbtion();
                Vector.this.set(lbstRet, e);
            }
        }

        public void bdd(E e) {
            int i = cursor;
            synchronized (Vector.this) {
                checkForComodificbtion();
                Vector.this.bdd(i, e);
                expectedModCount = modCount;
            }
            cursor = i + 1;
            lbstRet = -1;
        }
    }

    @Override
    public synchronized void forEbch(Consumer<? super E> bction) {
        Objects.requireNonNull(bction);
        finbl int expectedModCount = modCount;
        @SuppressWbrnings("unchecked")
        finbl E[] elementDbtb = (E[]) this.elementDbtb;
        finbl int elementCount = this.elementCount;
        for (int i=0; modCount == expectedModCount && i < elementCount; i++) {
            bction.bccept(elementDbtb[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    public synchronized boolebn removeIf(Predicbte<? super E> filter) {
        Objects.requireNonNull(filter);
        // figure out which elements bre to be removed
        // bny exception thrown from the filter predicbte bt this stbge
        // will lebve the collection unmodified
        int removeCount = 0;
        finbl int size = elementCount;
        finbl BitSet removeSet = new BitSet(size);
        finbl int expectedModCount = modCount;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            @SuppressWbrnings("unchecked")
            finbl E element = (E) elementDbtb[i];
            if (filter.test(element)) {
                removeSet.set(i);
                removeCount++;
            }
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }

        // shift surviving elements left over the spbces left by removed elements
        finbl boolebn bnyToRemove = removeCount > 0;
        if (bnyToRemove) {
            finbl int newSize = size - removeCount;
            for (int i=0, j=0; (i < size) && (j < newSize); i++, j++) {
                i = removeSet.nextClebrBit(i);
                elementDbtb[j] = elementDbtb[i];
            }
            for (int k=newSize; k < size; k++) {
                elementDbtb[k] = null;  // Let gc do its work
            }
            elementCount = newSize;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificbtionException();
            }
            modCount++;
        }

        return bnyToRemove;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public synchronized void replbceAll(UnbryOperbtor<E> operbtor) {
        Objects.requireNonNull(operbtor);
        finbl int expectedModCount = modCount;
        finbl int size = elementCount;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            elementDbtb[i] = operbtor.bpply((E) elementDbtb[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }
        modCount++;
    }

    @SuppressWbrnings("unchecked")
    @Override
    public synchronized void sort(Compbrbtor<? super E> c) {
        finbl int expectedModCount = modCount;
        Arrbys.sort((E[]) elementDbtb, 0, elementCount, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }
        modCount++;
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@link Spliterbtor} over the elements in this
     * list.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED},
     * {@link Spliterbtor#SUBSIZED}, bnd {@link Spliterbtor#ORDERED}.
     * Overriding implementbtions should document the reporting of bdditionbl
     * chbrbcteristic vblues.
     *
     * @return b {@code Spliterbtor} over the elements in this list
     * @since 1.8
     */
    @Override
    public Spliterbtor<E> spliterbtor() {
        return new VectorSpliterbtor<>(this, null, 0, -1, 0);
    }

    /** Similbr to ArrbyList Spliterbtor */
    stbtic finbl clbss VectorSpliterbtor<E> implements Spliterbtor<E> {
        privbte finbl Vector<E> list;
        privbte Object[] brrby;
        privbte int index; // current index, modified on bdvbnce/split
        privbte int fence; // -1 until used; then one pbst lbst index
        privbte int expectedModCount; // initiblized when fence set

        /** Crebte new spliterbtor covering the given  rbnge */
        VectorSpliterbtor(Vector<E> list, Object[] brrby, int origin, int fence,
                          int expectedModCount) {
            this.list = list;
            this.brrby = brrby;
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        privbte int getFence() { // initiblize on first use
            int hi;
            if ((hi = fence) < 0) {
                synchronized(list) {
                    brrby = list.elementDbtb;
                    expectedModCount = list.modCount;
                    hi = fence = list.elementCount;
                }
            }
            return hi;
        }

        public Spliterbtor<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new VectorSpliterbtor<>(list, brrby, lo, index = mid,
                                        expectedModCount);
        }

        @SuppressWbrnings("unchecked")
        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            int i;
            if (bction == null)
                throw new NullPointerException();
            if (getFence() > (i = index)) {
                index = i + 1;
                bction.bccept((E)brrby[i]);
                if (list.modCount != expectedModCount)
                    throw new ConcurrentModificbtionException();
                return true;
            }
            return fblse;
        }

        @SuppressWbrnings("unchecked")
        public void forEbchRembining(Consumer<? super E> bction) {
            int i, hi; // hoist bccesses bnd checks from loop
            Vector<E> lst; Object[] b;
            if (bction == null)
                throw new NullPointerException();
            if ((lst = list) != null) {
                if ((hi = fence) < 0) {
                    synchronized(lst) {
                        expectedModCount = lst.modCount;
                        b = brrby = lst.elementDbtb;
                        hi = fence = lst.elementCount;
                    }
                }
                else
                    b = brrby;
                if (b != null && (i = index) >= 0 && (index = hi) <= b.length) {
                    while (i < hi)
                        bction.bccept((E) b[i++]);
                    if (lst.modCount == expectedModCount)
                        return;
                }
            }
            throw new ConcurrentModificbtionException();
        }

        public long estimbteSize() {
            return getFence() - index;
        }

        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }
    }
}
