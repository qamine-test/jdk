/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A collection thbt contbins no duplicbte elements.  More formblly, sets
 * contbin no pbir of elements <code>e1</code> bnd <code>e2</code> such thbt
 * <code>e1.equbls(e2)</code>, bnd bt most one null element.  As implied by
 * its nbme, this interfbce models the mbthembticbl <i>set</i> bbstrbction.
 *
 * <p>The <tt>Set</tt> interfbce plbces bdditionbl stipulbtions, beyond those
 * inherited from the <tt>Collection</tt> interfbce, on the contrbcts of bll
 * constructors bnd on the contrbcts of the <tt>bdd</tt>, <tt>equbls</tt> bnd
 * <tt>hbshCode</tt> methods.  Declbrbtions for other inherited methods bre
 * blso included here for convenience.  (The specificbtions bccompbnying these
 * declbrbtions hbve been tbilored to the <tt>Set</tt> interfbce, but they do
 * not contbin bny bdditionbl stipulbtions.)
 *
 * <p>The bdditionbl stipulbtion on constructors is, not surprisingly,
 * thbt bll constructors must crebte b set thbt contbins no duplicbte elements
 * (bs defined bbove).
 *
 * <p>Note: Grebt cbre must be exercised if mutbble objects bre used bs set
 * elements.  The behbvior of b set is not specified if the vblue of bn object
 * is chbnged in b mbnner thbt bffects <tt>equbls</tt> compbrisons while the
 * object is bn element in the set.  A specibl cbse of this prohibition is
 * thbt it is not permissible for b set to contbin itself bs bn element.
 *
 * <p>Some set implementbtions hbve restrictions on the elements thbt
 * they mby contbin.  For exbmple, some implementbtions prohibit null elements,
 * bnd some hbve restrictions on the types of their elements.  Attempting to
 * bdd bn ineligible element throws bn unchecked exception, typicblly
 * <tt>NullPointerException</tt> or <tt>ClbssCbstException</tt>.  Attempting
 * to query the presence of bn ineligible element mby throw bn exception,
 * or it mby simply return fblse; some implementbtions will exhibit the former
 * behbvior bnd some will exhibit the lbtter.  More generblly, bttempting bn
 * operbtion on bn ineligible element whose completion would not result in
 * the insertion of bn ineligible element into the set mby throw bn
 * exception or it mby succeed, bt the option of the implementbtion.
 * Such exceptions bre mbrked bs "optionbl" in the specificbtion for this
 * interfbce.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements mbintbined by this set
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see Collection
 * @see List
 * @see SortedSet
 * @see HbshSet
 * @see TreeSet
 * @see AbstrbctSet
 * @see Collections#singleton(jbvb.lbng.Object)
 * @see Collections#EMPTY_SET
 * @since 1.2
 */

public interfbce Set<E> extends Collection<E> {
    // Query Operbtions

    /**
     * Returns the number of elements in this set (its cbrdinblity).  If this
     * set contbins more thbn <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this set (its cbrdinblity)
     */
    int size();

    /**
     * Returns <tt>true</tt> if this set contbins no elements.
     *
     * @return <tt>true</tt> if this set contbins no elements
     */
    boolebn isEmpty();

    /**
     * Returns <tt>true</tt> if this set contbins the specified element.
     * More formblly, returns <tt>true</tt> if bnd only if this set
     * contbins bn element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this set is to be tested
     * @return <tt>true</tt> if this set contbins the specified element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this set
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         set does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn contbins(Object o);

    /**
     * Returns bn iterbtor over the elements in this set.  The elements bre
     * returned in no pbrticulbr order (unless this set is bn instbnce of some
     * clbss thbt provides b gubrbntee).
     *
     * @return bn iterbtor over the elements in this set
     */
    Iterbtor<E> iterbtor();

    /**
     * Returns bn brrby contbining bll of the elements in this set.
     * If this set mbkes bny gubrbntees bs to whbt order its elements
     * bre returned by its iterbtor, this method must return the
     * elements in the sbme order.
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it
     * bre mbintbined by this set.  (In other words, this method must
     * bllocbte b new brrby even if this set is bbcked by bn brrby).
     * The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll the elements in this set
     */
    Object[] toArrby();

    /**
     * Returns bn brrby contbining bll of the elements in this set; the
     * runtime type of the returned brrby is thbt of the specified brrby.
     * If the set fits in the specified brrby, it is returned therein.
     * Otherwise, b new brrby is bllocbted with the runtime type of the
     * specified brrby bnd the size of this set.
     *
     * <p>If this set fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this set), the element in
     * the brrby immedibtely following the end of the set is set to
     * <tt>null</tt>.  (This is useful in determining the length of this
     * set <i>only</i> if the cbller knows thbt this set does not contbin
     * bny null elements.)
     *
     * <p>If this set mbkes bny gubrbntees bs to whbt order its elements
     * bre returned by its iterbtor, this method must return the elements
     * in the sbme order.
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose <tt>x</tt> is b set known to contbin only strings.
     * The following code cbn be used to dump the set into b newly bllocbted
     * brrby of <tt>String</tt>:
     *
     * <pre>
     *     String[] y = x.toArrby(new String[0]);</pre>
     *
     * Note thbt <tt>toArrby(new Object[0])</tt> is identicbl in function to
     * <tt>toArrby()</tt>.
     *
     * @pbrbm b the brrby into which the elements of this set bre to be
     *        stored, if it is big enough; otherwise, b new brrby of the sbme
     *        runtime type is bllocbted for this purpose.
     * @return bn brrby contbining bll the elements in this set
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in this
     *         set
     * @throws NullPointerException if the specified brrby is null
     */
    <T> T[] toArrby(T[] b);


    // Modificbtion Operbtions

    /**
     * Adds the specified element to this set if it is not blrebdy present
     * (optionbl operbtion).  More formblly, bdds the specified element
     * <tt>e</tt> to this set if the set contbins no element <tt>e2</tt>
     * such thbt
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equbls(e2))</tt>.
     * If this set blrebdy contbins the element, the cbll lebves the set
     * unchbnged bnd returns <tt>fblse</tt>.  In combinbtion with the
     * restriction on constructors, this ensures thbt sets never contbin
     * duplicbte elements.
     *
     * <p>The stipulbtion bbove does not imply thbt sets must bccept bll
     * elements; sets mby refuse to bdd bny pbrticulbr element, including
     * <tt>null</tt>, bnd throw bn exception, bs described in the
     * specificbtion for {@link Collection#bdd Collection.bdd}.
     * Individubl set implementbtions should clebrly document bny
     * restrictions on the elements thbt they mby contbin.
     *
     * @pbrbm e element to be bdded to this set
     * @return <tt>true</tt> if this set did not blrebdy contbin the specified
     *         element
     * @throws UnsupportedOperbtionException if the <tt>bdd</tt> operbtion
     *         is not supported by this set
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this set
     * @throws NullPointerException if the specified element is null bnd this
     *         set does not permit null elements
     * @throws IllegblArgumentException if some property of the specified element
     *         prevents it from being bdded to this set
     */
    boolebn bdd(E e);


    /**
     * Removes the specified element from this set if it is present
     * (optionbl operbtion).  More formblly, removes bn element <tt>e</tt>
     * such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>, if
     * this set contbins such bn element.  Returns <tt>true</tt> if this set
     * contbined the element (or equivblently, if this set chbnged bs b
     * result of the cbll).  (This set will not contbin the element once the
     * cbll returns.)
     *
     * @pbrbm o object to be removed from this set, if present
     * @return <tt>true</tt> if this set contbined the specified element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this set
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         set does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws UnsupportedOperbtionException if the <tt>remove</tt> operbtion
     *         is not supported by this set
     */
    boolebn remove(Object o);


    // Bulk Operbtions

    /**
     * Returns <tt>true</tt> if this set contbins bll of the elements of the
     * specified collection.  If the specified collection is blso b set, this
     * method returns <tt>true</tt> if it is b <i>subset</i> of this set.
     *
     * @pbrbm  c collection to be checked for contbinment in this set
     * @return <tt>true</tt> if this set contbins bll of the elements of the
     *         specified collection
     * @throws ClbssCbstException if the types of one or more elements
     *         in the specified collection bre incompbtible with this
     *         set
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified collection contbins one
     *         or more null elements bnd this set does not permit null
     *         elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see    #contbins(Object)
     */
    boolebn contbinsAll(Collection<?> c);

    /**
     * Adds bll of the elements in the specified collection to this set if
     * they're not blrebdy present (optionbl operbtion).  If the specified
     * collection is blso b set, the <tt>bddAll</tt> operbtion effectively
     * modifies this set so thbt its vblue is the <i>union</i> of the two
     * sets.  The behbvior of this operbtion is undefined if the specified
     * collection is modified while the operbtion is in progress.
     *
     * @pbrbm  c collection contbining elements to be bdded to this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     *
     * @throws UnsupportedOperbtionException if the <tt>bddAll</tt> operbtion
     *         is not supported by this set
     * @throws ClbssCbstException if the clbss of bn element of the
     *         specified collection prevents it from being bdded to this set
     * @throws NullPointerException if the specified collection contbins one
     *         or more null elements bnd this set does not permit null
     *         elements, or if the specified collection is null
     * @throws IllegblArgumentException if some property of bn element of the
     *         specified collection prevents it from being bdded to this set
     * @see #bdd(Object)
     */
    boolebn bddAll(Collection<? extends E> c);

    /**
     * Retbins only the elements in this set thbt bre contbined in the
     * specified collection (optionbl operbtion).  In other words, removes
     * from this set bll of its elements thbt bre not contbined in the
     * specified collection.  If the specified collection is blso b set, this
     * operbtion effectively modifies this set so thbt its vblue is the
     * <i>intersection</i> of the two sets.
     *
     * @pbrbm  c collection contbining elements to be retbined in this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>retbinAll</tt> operbtion
     *         is not supported by this set
     * @throws ClbssCbstException if the clbss of bn element of this set
     *         is incompbtible with the specified collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this set contbins b null element bnd the
     *         specified collection does not permit null elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    boolebn retbinAll(Collection<?> c);

    /**
     * Removes from this set bll of its elements thbt bre contbined in the
     * specified collection (optionbl operbtion).  If the specified
     * collection is blso b set, this operbtion effectively modifies this
     * set so thbt its vblue is the <i>bsymmetric set difference</i> of
     * the two sets.
     *
     * @pbrbm  c collection contbining elements to be removed from this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>removeAll</tt> operbtion
     *         is not supported by this set
     * @throws ClbssCbstException if the clbss of bn element of this set
     *         is incompbtible with the specified collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this set contbins b null element bnd the
     *         specified collection does not permit null elements
     *         (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    boolebn removeAll(Collection<?> c);

    /**
     * Removes bll of the elements from this set (optionbl operbtion).
     * The set will be empty bfter this cbll returns.
     *
     * @throws UnsupportedOperbtionException if the <tt>clebr</tt> method
     *         is not supported by this set
     */
    void clebr();


    // Compbrison bnd hbshing

    /**
     * Compbres the specified object with this set for equblity.  Returns
     * <tt>true</tt> if the specified object is blso b set, the two sets
     * hbve the sbme size, bnd every member of the specified set is
     * contbined in this set (or equivblently, every member of this set is
     * contbined in the specified set).  This definition ensures thbt the
     * equbls method works properly bcross different implementbtions of the
     * set interfbce.
     *
     * @pbrbm o object to be compbred for equblity with this set
     * @return <tt>true</tt> if the specified object is equbl to this set
     */
    boolebn equbls(Object o);

    /**
     * Returns the hbsh code vblue for this set.  The hbsh code of b set is
     * defined to be the sum of the hbsh codes of the elements in the set,
     * where the hbsh code of b <tt>null</tt> element is defined to be zero.
     * This ensures thbt <tt>s1.equbls(s2)</tt> implies thbt
     * <tt>s1.hbshCode()==s2.hbshCode()</tt> for bny two sets <tt>s1</tt>
     * bnd <tt>s2</tt>, bs required by the generbl contrbct of
     * {@link Object#hbshCode}.
     *
     * @return the hbsh code vblue for this set
     * @see Object#equbls(Object)
     * @see Set#equbls(Object)
     */
    int hbshCode();

    /**
     * Crebtes b {@code Spliterbtor} over the elements in this set.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#DISTINCT}.
     * Implementbtions should document the reporting of bdditionbl
     * chbrbcteristic vblues.
     *
     * @implSpec
     * The defbult implementbtion crebtes b
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em> spliterbtor
     * from the set's {@code Iterbtor}.  The spliterbtor inherits the
     * <em>fbil-fbst</em> properties of the set's iterbtor.
     * <p>
     * The crebted {@code Spliterbtor} bdditionblly reports
     * {@link Spliterbtor#SIZED}.
     *
     * @implNote
     * The crebted {@code Spliterbtor} bdditionblly reports
     * {@link Spliterbtor#SUBSIZED}.
     *
     * @return b {@code Spliterbtor} over the elements in this set
     * @since 1.8
     */
    @Override
    defbult Spliterbtor<E> spliterbtor() {
        return Spliterbtors.spliterbtor(this, Spliterbtor.DISTINCT);
    }
}
