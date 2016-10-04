/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.function.UnbryOperbtor;

/**
 * An ordered collection (blso known bs b <i>sequence</i>).  The user of this
 * interfbce hbs precise control over where in the list ebch element is
 * inserted.  The user cbn bccess elements by their integer index (position in
 * the list), bnd sebrch for elements in the list.<p>
 *
 * Unlike sets, lists typicblly bllow duplicbte elements.  More formblly,
 * lists typicblly bllow pbirs of elements <tt>e1</tt> bnd <tt>e2</tt>
 * such thbt <tt>e1.equbls(e2)</tt>, bnd they typicblly bllow multiple
 * null elements if they bllow null elements bt bll.  It is not inconceivbble
 * thbt someone might wish to implement b list thbt prohibits duplicbtes, by
 * throwing runtime exceptions when the user bttempts to insert them, but we
 * expect this usbge to be rbre.<p>
 *
 * The <tt>List</tt> interfbce plbces bdditionbl stipulbtions, beyond those
 * specified in the <tt>Collection</tt> interfbce, on the contrbcts of the
 * <tt>iterbtor</tt>, <tt>bdd</tt>, <tt>remove</tt>, <tt>equbls</tt>, bnd
 * <tt>hbshCode</tt> methods.  Declbrbtions for other inherited methods bre
 * blso included here for convenience.<p>
 *
 * The <tt>List</tt> interfbce provides four methods for positionbl (indexed)
 * bccess to list elements.  Lists (like Jbvb brrbys) bre zero bbsed.  Note
 * thbt these operbtions mby execute in time proportionbl to the index vblue
 * for some implementbtions (the <tt>LinkedList</tt> clbss, for
 * exbmple). Thus, iterbting over the elements in b list is typicblly
 * preferbble to indexing through it if the cbller does not know the
 * implementbtion.<p>
 *
 * The <tt>List</tt> interfbce provides b specibl iterbtor, cblled b
 * <tt>ListIterbtor</tt>, thbt bllows element insertion bnd replbcement, bnd
 * bidirectionbl bccess in bddition to the normbl operbtions thbt the
 * <tt>Iterbtor</tt> interfbce provides.  A method is provided to obtbin b
 * list iterbtor thbt stbrts bt b specified position in the list.<p>
 *
 * The <tt>List</tt> interfbce provides two methods to sebrch for b specified
 * object.  From b performbnce stbndpoint, these methods should be used with
 * cbution.  In mbny implementbtions they will perform costly linebr
 * sebrches.<p>
 *
 * The <tt>List</tt> interfbce provides two methods to efficiently insert bnd
 * remove multiple elements bt bn brbitrbry point in the list.<p>
 *
 * Note: While it is permissible for lists to contbin themselves bs elements,
 * extreme cbution is bdvised: the <tt>equbls</tt> bnd <tt>hbshCode</tt>
 * methods bre no longer well defined on such b list.
 *
 * <p>Some list implementbtions hbve restrictions on the elements thbt
 * they mby contbin.  For exbmple, some implementbtions prohibit null elements,
 * bnd some hbve restrictions on the types of their elements.  Attempting to
 * bdd bn ineligible element throws bn unchecked exception, typicblly
 * <tt>NullPointerException</tt> or <tt>ClbssCbstException</tt>.  Attempting
 * to query the presence of bn ineligible element mby throw bn exception,
 * or it mby simply return fblse; some implementbtions will exhibit the former
 * behbvior bnd some will exhibit the lbtter.  More generblly, bttempting bn
 * operbtion on bn ineligible element whose completion would not result in
 * the insertion of bn ineligible element into the list mby throw bn
 * exception or it mby succeed, bt the option of the implementbtion.
 * Such exceptions bre mbrked bs "optionbl" in the specificbtion for this
 * interfbce.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements in this list
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see Collection
 * @see Set
 * @see ArrbyList
 * @see LinkedList
 * @see Vector
 * @see Arrbys#bsList(Object[])
 * @see Collections#nCopies(int, Object)
 * @see Collections#EMPTY_LIST
 * @see AbstrbctList
 * @see AbstrbctSequentiblList
 * @since 1.2
 */

public interfbce List<E> extends Collection<E> {
    // Query Operbtions

    /**
     * Returns the number of elements in this list.  If this list contbins
     * more thbn <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns <tt>true</tt> if this list contbins no elements.
     *
     * @return <tt>true</tt> if this list contbins no elements
     */
    boolebn isEmpty();

    /**
     * Returns <tt>true</tt> if this list contbins the specified element.
     * More formblly, returns <tt>true</tt> if bnd only if this list contbins
     * bt lebst one element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contbins the specified element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this list
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         list does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn contbins(Object o);

    /**
     * Returns bn iterbtor over the elements in this list in proper sequence.
     *
     * @return bn iterbtor over the elements in this list in proper sequence
     */
    Iterbtor<E> iterbtor();

    /**
     * Returns bn brrby contbining bll of the elements in this list in proper
     * sequence (from first to lbst element).
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this list.  (In other words, this method must
     * bllocbte b new brrby even if this list is bbcked by bn brrby).
     * The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll of the elements in this list in proper
     *         sequence
     * @see Arrbys#bsList(Object[])
     */
    Object[] toArrby();

    /**
     * Returns bn brrby contbining bll of the elements in this list in
     * proper sequence (from first to lbst element); the runtime type of
     * the returned brrby is thbt of the specified brrby.  If the list fits
     * in the specified brrby, it is returned therein.  Otherwise, b new
     * brrby is bllocbted with the runtime type of the specified brrby bnd
     * the size of this list.
     *
     * <p>If the list fits in the specified brrby with room to spbre (i.e.,
     * the brrby hbs more elements thbn the list), the element in the brrby
     * immedibtely following the end of the list is set to <tt>null</tt>.
     * (This is useful in determining the length of the list <i>only</i> if
     * the cbller knows thbt the list does not contbin bny null elements.)
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose <tt>x</tt> is b list known to contbin only strings.
     * The following code cbn be used to dump the list into b newly
     * bllocbted brrby of <tt>String</tt>:
     *
     * <pre>{@code
     *     String[] y = x.toArrby(new String[0]);
     * }</pre>
     *
     * Note thbt <tt>toArrby(new Object[0])</tt> is identicbl in function to
     * <tt>toArrby()</tt>.
     *
     * @pbrbm b the brrby into which the elements of this list bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose.
     * @return bn brrby contbining the elements of this list
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this list
     * @throws NullPointerException if the specified brrby is null
     */
    <T> T[] toArrby(T[] b);


    // Modificbtion Operbtions

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
     * @pbrbm e element to be bppended to this list
     * @return <tt>true</tt> (bs specified by {@link Collection#bdd})
     * @throws UnsupportedOperbtionException if the <tt>bdd</tt> operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this list
     * @throws NullPointerException if the specified element is null bnd this
     *         list does not permit null elements
     * @throws IllegblArgumentException if some property of this element
     *         prevents it from being bdded to this list
     */
    boolebn bdd(E e);

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optionbl operbtion).  If this list does not contbin
     * the element, it is unchbnged.  More formblly, removes the element with
     * the lowest index <tt>i</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>
     * (if such bn element exists).  Returns <tt>true</tt> if this list
     * contbined the specified element (or equivblently, if this list chbnged
     * bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contbined the specified element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this list
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         list does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws UnsupportedOperbtionException if the <tt>remove</tt> operbtion
     *         is not supported by this list
     */
    boolebn remove(Object o);


    // Bulk Modificbtion Operbtions

    /**
     * Returns <tt>true</tt> if this list contbins bll of the elements of the
     * specified collection.
     *
     * @pbrbm  c collection to be checked for contbinment in this list
     * @return <tt>true</tt> if this list contbins bll of the elements of the
     *         specified collection
     * @throws ClbssCbstException if the types of one or more elements
     *         in the specified collection bre incompbtible with this
     *         list
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified collection contbins one
     *         or more null elements bnd this list does not permit null
     *         elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #contbins(Object)
     */
    boolebn contbinsAll(Collection<?> c);

    /**
     * Appends bll of the elements in the specified collection to the end of
     * this list, in the order thbt they bre returned by the specified
     * collection's iterbtor (optionbl operbtion).  The behbvior of this
     * operbtion is undefined if the specified collection is modified while
     * the operbtion is in progress.  (Note thbt this will occur if the
     * specified collection is this list, bnd it's nonempty.)
     *
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>bddAll</tt> operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of bn element of the specified
     *         collection prevents it from being bdded to this list
     * @throws NullPointerException if the specified collection contbins one
     *         or more null elements bnd this list does not permit null
     *         elements, or if the specified collection is null
     * @throws IllegblArgumentException if some property of bn element of the
     *         specified collection prevents it from being bdded to this list
     * @see #bdd(Object)
     */
    boolebn bddAll(Collection<? extends E> c);

    /**
     * Inserts bll of the elements in the specified collection into this
     * list bt the specified position (optionbl operbtion).  Shifts the
     * element currently bt thbt position (if bny) bnd bny subsequent
     * elements to the right (increbses their indices).  The new elements
     * will bppebr in this list in the order thbt they bre returned by the
     * specified collection's iterbtor.  The behbvior of this operbtion is
     * undefined if the specified collection is modified while the
     * operbtion is in progress.  (Note thbt this will occur if the specified
     * collection is this list, bnd it's nonempty.)
     *
     * @pbrbm index index bt which to insert the first element from the
     *              specified collection
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>bddAll</tt> operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of bn element of the specified
     *         collection prevents it from being bdded to this list
     * @throws NullPointerException if the specified collection contbins one
     *         or more null elements bnd this list does not permit null
     *         elements, or if the specified collection is null
     * @throws IllegblArgumentException if some property of bn element of the
     *         specified collection prevents it from being bdded to this list
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     *         (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    boolebn bddAll(int index, Collection<? extends E> c);

    /**
     * Removes from this list bll of its elements thbt bre contbined in the
     * specified collection (optionbl operbtion).
     *
     * @pbrbm c collection contbining elements to be removed from this list
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>removeAll</tt> operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of bn element of this list
     *         is incompbtible with the specified collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this list contbins b null element bnd the
     *         specified collection does not permit null elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    boolebn removeAll(Collection<?> c);

    /**
     * Retbins only the elements in this list thbt bre contbined in the
     * specified collection (optionbl operbtion).  In other words, removes
     * from this list bll of its elements thbt bre not contbined in the
     * specified collection.
     *
     * @pbrbm c collection contbining elements to be retbined in this list
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>retbinAll</tt> operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of bn element of this list
     *         is incompbtible with the specified collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this list contbins b null element bnd the
     *         specified collection does not permit null elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    boolebn retbinAll(Collection<?> c);

    /**
     * Replbces ebch element of this list with the result of bpplying the
     * operbtor to thbt element.  Errors or runtime exceptions thrown by
     * the operbtor bre relbyed to the cbller.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to, for this {@code list}:
     * <pre>{@code
     *     finbl ListIterbtor<E> li = list.listIterbtor();
     *     while (li.hbsNext()) {
     *         li.set(operbtor.bpply(li.next()));
     *     }
     * }</pre>
     *
     * If the list's list-iterbtor does not support the {@code set} operbtion
     * then bn {@code UnsupportedOperbtionException} will be thrown when
     * replbcing the first element.
     *
     * @pbrbm operbtor the operbtor to bpply to ebch element
     * @throws UnsupportedOperbtionException if this list is unmodifibble.
     *         Implementbtions mby throw this exception if bn element
     *         cbnnot be replbced or if, in generbl, modificbtion is not
     *         supported
     * @throws NullPointerException if the specified operbtor is null or
     *         if the operbtor result is b null vblue bnd this list does
     *         not permit null elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @since 1.8
     */
    defbult void replbceAll(UnbryOperbtor<E> operbtor) {
        Objects.requireNonNull(operbtor);
        finbl ListIterbtor<E> li = this.listIterbtor();
        while (li.hbsNext()) {
            li.set(operbtor.bpply(li.next()));
        }
    }

    /**
     * Sorts this list bccording to the order induced by the specified
     * {@link Compbrbtor}.
     *
     * <p>All elements in this list must be <i>mutublly compbrbble</i> using the
     * specified compbrbtor (thbt is, {@code c.compbre(e1, e2)} must not throw
     * b {@code ClbssCbstException} for bny elements {@code e1} bnd {@code e2}
     * in the list).
     *
     * <p>If the specified compbrbtor is {@code null} then bll elements in this
     * list must implement the {@link Compbrbble} interfbce bnd the elements'
     * {@linkplbin Compbrbble nbturbl ordering} should be used.
     *
     * <p>This list must be modifibble, but need not be resizbble.
     *
     * @implSpec
     * The defbult implementbtion obtbins bn brrby contbining bll elements in
     * this list, sorts the brrby, bnd iterbtes over this list resetting ebch
     * element from the corresponding position in the brrby. (This bvoids the
     * n<sup>2</sup> log(n) performbnce thbt would result from bttempting
     * to sort b linked list in plbce.)
     *
     * @implNote
     * This implementbtion is b stbble, bdbptive, iterbtive mergesort thbt
     * requires fbr fewer thbn n lg(n) compbrisons when the input brrby is
     * pbrtiblly sorted, while offering the performbnce of b trbditionbl
     * mergesort when the input brrby is rbndomly ordered.  If the input brrby
     * is nebrly sorted, the implementbtion requires bpproximbtely n
     * compbrisons.  Temporbry storbge requirements vbry from b smbll constbnt
     * for nebrly sorted input brrbys to n/2 object references for rbndomly
     * ordered input brrbys.
     *
     * <p>The implementbtion tbkes equbl bdvbntbge of bscending bnd
     * descending order in its input brrby, bnd cbn tbke bdvbntbge of
     * bscending bnd descending order in different pbrts of the sbme
     * input brrby.  It is well-suited to merging two or more sorted brrbys:
     * simply concbtenbte the brrbys bnd sort the resulting brrby.
     *
     * <p>The implementbtion wbs bdbpted from Tim Peters's list sort for Python
     * (<b href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</b>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting bnd Informbtion Theoretic Complexity", in Proceedings of the
     * Fourth Annubl ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * Jbnubry 1993.
     *
     * @pbrbm c the {@code Compbrbtor} used to compbre list elements.
     *          A {@code null} vblue indicbtes thbt the elements'
     *          {@linkplbin Compbrbble nbturbl ordering} should be used
     * @throws ClbssCbstException if the list contbins elements thbt bre not
     *         <i>mutublly compbrbble</i> using the specified compbrbtor
     * @throws UnsupportedOperbtionException if the list's list-iterbtor does
     *         not support the {@code set} operbtion
     * @throws IllegblArgumentException
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     *         if the compbrbtor is found to violbte the {@link Compbrbtor}
     *         contrbct
     * @since 1.8
     */
    @SuppressWbrnings({"unchecked", "rbwtypes"})
    defbult void sort(Compbrbtor<? super E> c) {
        Object[] b = this.toArrby();
        Arrbys.sort(b, (Compbrbtor) c);
        ListIterbtor<E> i = this.listIterbtor();
        for (Object e : b) {
            i.next();
            i.set((E) e);
        }
    }

    /**
     * Removes bll of the elements from this list (optionbl operbtion).
     * The list will be empty bfter this cbll returns.
     *
     * @throws UnsupportedOperbtionException if the <tt>clebr</tt> operbtion
     *         is not supported by this list
     */
    void clebr();


    // Compbrison bnd hbshing

    /**
     * Compbres the specified object with this list for equblity.  Returns
     * <tt>true</tt> if bnd only if the specified object is blso b list, both
     * lists hbve the sbme size, bnd bll corresponding pbirs of elements in
     * the two lists bre <i>equbl</i>.  (Two elements <tt>e1</tt> bnd
     * <tt>e2</tt> bre <i>equbl</i> if <tt>(e1==null ? e2==null :
     * e1.equbls(e2))</tt>.)  In other words, two lists bre defined to be
     * equbl if they contbin the sbme elements in the sbme order.  This
     * definition ensures thbt the equbls method works properly bcross
     * different implementbtions of the <tt>List</tt> interfbce.
     *
     * @pbrbm o the object to be compbred for equblity with this list
     * @return <tt>true</tt> if the specified object is equbl to this list
     */
    boolebn equbls(Object o);

    /**
     * Returns the hbsh code vblue for this list.  The hbsh code of b list
     * is defined to be the result of the following cblculbtion:
     * <pre>{@code
     *     int hbshCode = 1;
     *     for (E e : list)
     *         hbshCode = 31*hbshCode + (e==null ? 0 : e.hbshCode());
     * }</pre>
     * This ensures thbt <tt>list1.equbls(list2)</tt> implies thbt
     * <tt>list1.hbshCode()==list2.hbshCode()</tt> for bny two lists,
     * <tt>list1</tt> bnd <tt>list2</tt>, bs required by the generbl
     * contrbct of {@link Object#hbshCode}.
     *
     * @return the hbsh code vblue for this list
     * @see Object#equbls(Object)
     * @see #equbls(Object)
     */
    int hbshCode();


    // Positionbl Access Operbtions

    /**
     * Returns the element bt the specified position in this list.
     *
     * @pbrbm index index of the element to return
     * @return the element bt the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E get(int index);

    /**
     * Replbces the element bt the specified position in this list with the
     * specified element (optionbl operbtion).
     *
     * @pbrbm index index of the element to replbce
     * @pbrbm element element to be stored bt the specified position
     * @return the element previously bt the specified position
     * @throws UnsupportedOperbtionException if the <tt>set</tt> operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this list
     * @throws NullPointerException if the specified element is null bnd
     *         this list does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this list
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E set(int index, E element);

    /**
     * Inserts the specified element bt the specified position in this list
     * (optionbl operbtion).  Shifts the element currently bt thbt position
     * (if bny) bnd bny subsequent elements to the right (bdds one to their
     * indices).
     *
     * @pbrbm index index bt which the specified element is to be inserted
     * @pbrbm element element to be inserted
     * @throws UnsupportedOperbtionException if the <tt>bdd</tt> operbtion
     *         is not supported by this list
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this list
     * @throws NullPointerException if the specified element is null bnd
     *         this list does not permit null elements
     * @throws IllegblArgumentException if some property of the specified
     *         element prevents it from being bdded to this list
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     *         (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    void bdd(int index, E element);

    /**
     * Removes the element bt the specified position in this list (optionbl
     * operbtion).  Shifts bny subsequent elements to the left (subtrbcts one
     * from their indices).  Returns the element thbt wbs removed from the
     * list.
     *
     * @pbrbm index the index of the element to be removed
     * @return the element previously bt the specified position
     * @throws UnsupportedOperbtionException if the <tt>remove</tt> operbtion
     *         is not supported by this list
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E remove(int index);


    // Sebrch Operbtions

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the lowest index <tt>i</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contbin the element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this list
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         list does not permit null elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    int indexOf(Object o);

    /**
     * Returns the index of the lbst occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the highest index <tt>i</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the lbst occurrence of the specified element in
     *         this list, or -1 if this list does not contbin the element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this list
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         list does not permit null elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    int lbstIndexOf(Object o);


    // List Iterbtors

    /**
     * Returns b list iterbtor over the elements in this list (in proper
     * sequence).
     *
     * @return b list iterbtor over the elements in this list (in proper
     *         sequence)
     */
    ListIterbtor<E> listIterbtor();

    /**
     * Returns b list iterbtor over the elements in this list (in proper
     * sequence), stbrting bt the specified position in the list.
     * The specified index indicbtes the first element thbt would be
     * returned by bn initibl cbll to {@link ListIterbtor#next next}.
     * An initibl cbll to {@link ListIterbtor#previous previous} would
     * return the element with the specified index minus one.
     *
     * @pbrbm index index of the first element to be returned from the
     *        list iterbtor (by b cbll to {@link ListIterbtor#next next})
     * @return b list iterbtor over the elements in this list (in proper
     *         sequence), stbrting bt the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of rbnge
     *         ({@code index < 0 || index > size()})
     */
    ListIterbtor<E> listIterbtor(int index);

    // View

    /**
     * Returns b view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, bnd <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> bnd <tt>toIndex</tt> bre equbl, the returned list is
     * empty.)  The returned list is bbcked by this list, so non-structurbl
     * chbnges in the returned list bre reflected in this list, bnd vice-versb.
     * The returned list supports bll of the optionbl list operbtions supported
     * by this list.<p>
     *
     * This method eliminbtes the need for explicit rbnge operbtions (of
     * the sort thbt commonly exist for brrbys).  Any operbtion thbt expects
     * b list cbn be used bs b rbnge operbtion by pbssing b subList view
     * instebd of b whole list.  For exbmple, the following idiom
     * removes b rbnge of elements from b list:
     * <pre>{@code
     *      list.subList(from, to).clebr();
     * }</pre>
     * Similbr idioms mby be constructed for <tt>indexOf</tt> bnd
     * <tt>lbstIndexOf</tt>, bnd bll of the blgorithms in the
     * <tt>Collections</tt> clbss cbn be bpplied to b subList.<p>
     *
     * The sembntics of the list returned by this method become undefined if
     * the bbcking list (i.e., this list) is <i>structurblly modified</i> in
     * bny wby other thbn vib the returned list.  (Structurbl modificbtions bre
     * those thbt chbnge the size of this list, or otherwise perturb it in such
     * b fbshion thbt iterbtions in progress mby yield incorrect results.)
     *
     * @pbrbm fromIndex low endpoint (inclusive) of the subList
     * @pbrbm toIndex high endpoint (exclusive) of the subList
     * @return b view of the specified rbnge within this list
     * @throws IndexOutOfBoundsException for bn illegbl endpoint index vblue
     *         (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *         fromIndex &gt; toIndex</tt>)
     */
    List<E> subList(int fromIndex, int toIndex);

    /**
     * Crebtes b {@link Spliterbtor} over the elements in this list.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#ORDERED}.  Implementbtions should document the
     * reporting of bdditionbl chbrbcteristic vblues.
     *
     * @implSpec
     * The defbult implementbtion crebtes b
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em> spliterbtor
     * from the list's {@code Iterbtor}.  The spliterbtor inherits the
     * <em>fbil-fbst</em> properties of the list's iterbtor.
     *
     * @implNote
     * The crebted {@code Spliterbtor} bdditionblly reports
     * {@link Spliterbtor#SUBSIZED}.
     *
     * @return b {@code Spliterbtor} over the elements in this list
     * @since 1.8
     */
    @Override
    defbult Spliterbtor<E> spliterbtor() {
        return Spliterbtors.spliterbtor(this, Spliterbtor.ORDERED);
    }
}
