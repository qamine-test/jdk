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

import jbvb.util.function.Predicbte;
import jbvb.util.strebm.Strebm;
import jbvb.util.strebm.StrebmSupport;

/**
 * The root interfbce in the <i>collection hierbrchy</i>.  A collection
 * represents b group of objects, known bs its <i>elements</i>.  Some
 * collections bllow duplicbte elements bnd others do not.  Some bre ordered
 * bnd others unordered.  The JDK does not provide bny <i>direct</i>
 * implementbtions of this interfbce: it provides implementbtions of more
 * specific subinterfbces like <tt>Set</tt> bnd <tt>List</tt>.  This interfbce
 * is typicblly used to pbss collections bround bnd mbnipulbte them where
 * mbximum generblity is desired.
 *
 * <p><i>Bbgs</i> or <i>multisets</i> (unordered collections thbt mby contbin
 * duplicbte elements) should implement this interfbce directly.
 *
 * <p>All generbl-purpose <tt>Collection</tt> implementbtion clbsses (which
 * typicblly implement <tt>Collection</tt> indirectly through one of its
 * subinterfbces) should provide two "stbndbrd" constructors: b void (no
 * brguments) constructor, which crebtes bn empty collection, bnd b
 * constructor with b single brgument of type <tt>Collection</tt>, which
 * crebtes b new collection with the sbme elements bs its brgument.  In
 * effect, the lbtter constructor bllows the user to copy bny collection,
 * producing bn equivblent collection of the desired implementbtion type.
 * There is no wby to enforce this convention (bs interfbces cbnnot contbin
 * constructors) but bll of the generbl-purpose <tt>Collection</tt>
 * implementbtions in the Jbvb plbtform librbries comply.
 *
 * <p>The "destructive" methods contbined in this interfbce, thbt is, the
 * methods thbt modify the collection on which they operbte, bre specified to
 * throw <tt>UnsupportedOperbtionException</tt> if this collection does not
 * support the operbtion.  If this is the cbse, these methods mby, but bre not
 * required to, throw bn <tt>UnsupportedOperbtionException</tt> if the
 * invocbtion would hbve no effect on the collection.  For exbmple, invoking
 * the {@link #bddAll(Collection)} method on bn unmodifibble collection mby,
 * but is not required to, throw the exception if the collection to be bdded
 * is empty.
 *
 * <p><b nbme="optionbl-restrictions">
 * Some collection implementbtions hbve restrictions on the elements thbt
 * they mby contbin.</b>  For exbmple, some implementbtions prohibit null elements,
 * bnd some hbve restrictions on the types of their elements.  Attempting to
 * bdd bn ineligible element throws bn unchecked exception, typicblly
 * <tt>NullPointerException</tt> or <tt>ClbssCbstException</tt>.  Attempting
 * to query the presence of bn ineligible element mby throw bn exception,
 * or it mby simply return fblse; some implementbtions will exhibit the former
 * behbvior bnd some will exhibit the lbtter.  More generblly, bttempting bn
 * operbtion on bn ineligible element whose completion would not result in
 * the insertion of bn ineligible element into the collection mby throw bn
 * exception or it mby succeed, bt the option of the implementbtion.
 * Such exceptions bre mbrked bs "optionbl" in the specificbtion for this
 * interfbce.
 *
 * <p>It is up to ebch collection to determine its own synchronizbtion
 * policy.  In the bbsence of b stronger gubrbntee by the
 * implementbtion, undefined behbvior mby result from the invocbtion
 * of bny method on b collection thbt is being mutbted by bnother
 * threbd; this includes direct invocbtions, pbssing the collection to
 * b method thbt might perform invocbtions, bnd using bn existing
 * iterbtor to exbmine the collection.
 *
 * <p>Mbny methods in Collections Frbmework interfbces bre defined in
 * terms of the {@link Object#equbls(Object) equbls} method.  For exbmple,
 * the specificbtion for the {@link #contbins(Object) contbins(Object o)}
 * method sbys: "returns <tt>true</tt> if bnd only if this collection
 * contbins bt lebst one element <tt>e</tt> such thbt
 * <tt>(o==null ? e==null : o.equbls(e))</tt>."  This specificbtion should
 * <i>not</i> be construed to imply thbt invoking <tt>Collection.contbins</tt>
 * with b non-null brgument <tt>o</tt> will cbuse <tt>o.equbls(e)</tt> to be
 * invoked for bny element <tt>e</tt>.  Implementbtions bre free to implement
 * optimizbtions whereby the <tt>equbls</tt> invocbtion is bvoided, for
 * exbmple, by first compbring the hbsh codes of the two elements.  (The
 * {@link Object#hbshCode()} specificbtion gubrbntees thbt two objects with
 * unequbl hbsh codes cbnnot be equbl.)  More generblly, implementbtions of
 * the vbrious Collections Frbmework interfbces bre free to tbke bdvbntbge of
 * the specified behbvior of underlying {@link Object} methods wherever the
 * implementor deems it bppropribte.
 *
 * <p>Some collection operbtions which perform recursive trbversbl of the
 * collection mby fbil with bn exception for self-referentibl instbnces where
 * the collection directly or indirectly contbins itself. This includes the
 * {@code clone()}, {@code equbls()}, {@code hbshCode()} bnd {@code toString()}
 * methods. Implementbtions mby optionblly hbndle the self-referentibl scenbrio,
 * however most current implementbtions do not do so.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @implSpec
 * The defbult method implementbtions (inherited or otherwise) do not bpply bny
 * synchronizbtion protocol.  If b {@code Collection} implementbtion hbs b
 * specific synchronizbtion protocol, then it must override defbult
 * implementbtions to bpply thbt protocol.
 *
 * @pbrbm <E> the type of elements in this collection
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see     Set
 * @see     List
 * @see     Mbp
 * @see     SortedSet
 * @see     SortedMbp
 * @see     HbshSet
 * @see     TreeSet
 * @see     ArrbyList
 * @see     LinkedList
 * @see     Vector
 * @see     Collections
 * @see     Arrbys
 * @see     AbstrbctCollection
 * @since 1.2
 */

public interfbce Collection<E> extends Iterbble<E> {
    // Query Operbtions

    /**
     * Returns the number of elements in this collection.  If this collection
     * contbins more thbn <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Returns <tt>true</tt> if this collection contbins no elements.
     *
     * @return <tt>true</tt> if this collection contbins no elements
     */
    boolebn isEmpty();

    /**
     * Returns <tt>true</tt> if this collection contbins the specified element.
     * More formblly, returns <tt>true</tt> if bnd only if this collection
     * contbins bt lebst one element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this collection is to be tested
     * @return <tt>true</tt> if this collection contbins the specified
     *         element
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this collection
     *         (<b href="#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         collection does not permit null elements
     *         (<b href="#optionbl-restrictions">optionbl</b>)
     */
    boolebn contbins(Object o);

    /**
     * Returns bn iterbtor over the elements in this collection.  There bre no
     * gubrbntees concerning the order in which the elements bre returned
     * (unless this collection is bn instbnce of some clbss thbt provides b
     * gubrbntee).
     *
     * @return bn <tt>Iterbtor</tt> over the elements in this collection
     */
    Iterbtor<E> iterbtor();

    /**
     * Returns bn brrby contbining bll of the elements in this collection.
     * If this collection mbkes bny gubrbntees bs to whbt order its elements
     * bre returned by its iterbtor, this method must return the elements in
     * the sbme order.
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this collection.  (In other words, this method must
     * bllocbte b new brrby even if this collection is bbcked by bn brrby).
     * The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll of the elements in this collection
     */
    Object[] toArrby();

    /**
     * Returns bn brrby contbining bll of the elements in this collection;
     * the runtime type of the returned brrby is thbt of the specified brrby.
     * If the collection fits in the specified brrby, it is returned therein.
     * Otherwise, b new brrby is bllocbted with the runtime type of the
     * specified brrby bnd the size of this collection.
     *
     * <p>If this collection fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this collection), the element
     * in the brrby immedibtely following the end of the collection is set to
     * <tt>null</tt>.  (This is useful in determining the length of this
     * collection <i>only</i> if the cbller knows thbt this collection does
     * not contbin bny <tt>null</tt> elements.)
     *
     * <p>If this collection mbkes bny gubrbntees bs to whbt order its elements
     * bre returned by its iterbtor, this method must return the elements in
     * the sbme order.
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose <tt>x</tt> is b collection known to contbin only strings.
     * The following code cbn be used to dump the collection into b newly
     * bllocbted brrby of <tt>String</tt>:
     *
     * <pre>
     *     String[] y = x.toArrby(new String[0]);</pre>
     *
     * Note thbt <tt>toArrby(new Object[0])</tt> is identicbl in function to
     * <tt>toArrby()</tt>.
     *
     * @pbrbm <T> the runtime type of the brrby to contbin the collection
     * @pbrbm b the brrby into which the elements of this collection bre to be
     *        stored, if it is big enough; otherwise, b new brrby of the sbme
     *        runtime type is bllocbted for this purpose.
     * @return bn brrby contbining bll of the elements in this collection
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this collection
     * @throws NullPointerException if the specified brrby is null
     */
    <T> T[] toArrby(T[] b);

    // Modificbtion Operbtions

    /**
     * Ensures thbt this collection contbins the specified element (optionbl
     * operbtion).  Returns <tt>true</tt> if this collection chbnged bs b
     * result of the cbll.  (Returns <tt>fblse</tt> if this collection does
     * not permit duplicbtes bnd blrebdy contbins the specified element.)<p>
     *
     * Collections thbt support this operbtion mby plbce limitbtions on whbt
     * elements mby be bdded to this collection.  In pbrticulbr, some
     * collections will refuse to bdd <tt>null</tt> elements, bnd others will
     * impose restrictions on the type of elements thbt mby be bdded.
     * Collection clbsses should clebrly specify in their documentbtion bny
     * restrictions on whbt elements mby be bdded.<p>
     *
     * If b collection refuses to bdd b pbrticulbr element for bny rebson
     * other thbn thbt it blrebdy contbins the element, it <i>must</i> throw
     * bn exception (rbther thbn returning <tt>fblse</tt>).  This preserves
     * the invbribnt thbt b collection blwbys contbins the specified element
     * bfter this cbll returns.
     *
     * @pbrbm e element whose presence in this collection is to be ensured
     * @return <tt>true</tt> if this collection chbnged bs b result of the
     *         cbll
     * @throws UnsupportedOperbtionException if the <tt>bdd</tt> operbtion
     *         is not supported by this collection
     * @throws ClbssCbstException if the clbss of the specified element
     *         prevents it from being bdded to this collection
     * @throws NullPointerException if the specified element is null bnd this
     *         collection does not permit null elements
     * @throws IllegblArgumentException if some property of the element
     *         prevents it from being bdded to this collection
     * @throws IllegblStbteException if the element cbnnot be bdded bt this
     *         time due to insertion restrictions
     */
    boolebn bdd(E e);

    /**
     * Removes b single instbnce of the specified element from this
     * collection, if it is present (optionbl operbtion).  More formblly,
     * removes bn element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>, if
     * this collection contbins one or more such elements.  Returns
     * <tt>true</tt> if this collection contbined the specified element (or
     * equivblently, if this collection chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this collection, if present
     * @return <tt>true</tt> if bn element wbs removed bs b result of this cbll
     * @throws ClbssCbstException if the type of the specified element
     *         is incompbtible with this collection
     *         (<b href="#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified element is null bnd this
     *         collection does not permit null elements
     *         (<b href="#optionbl-restrictions">optionbl</b>)
     * @throws UnsupportedOperbtionException if the <tt>remove</tt> operbtion
     *         is not supported by this collection
     */
    boolebn remove(Object o);


    // Bulk Operbtions

    /**
     * Returns <tt>true</tt> if this collection contbins bll of the elements
     * in the specified collection.
     *
     * @pbrbm  c collection to be checked for contbinment in this collection
     * @return <tt>true</tt> if this collection contbins bll of the elements
     *         in the specified collection
     * @throws ClbssCbstException if the types of one or more elements
     *         in the specified collection bre incompbtible with this
     *         collection
     *         (<b href="#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified collection contbins one
     *         or more null elements bnd this collection does not permit null
     *         elements
     *         (<b href="#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null.
     * @see    #contbins(Object)
     */
    boolebn contbinsAll(Collection<?> c);

    /**
     * Adds bll of the elements in the specified collection to this collection
     * (optionbl operbtion).  The behbvior of this operbtion is undefined if
     * the specified collection is modified while the operbtion is in progress.
     * (This implies thbt the behbvior of this cbll is undefined if the
     * specified collection is this collection, bnd this collection is
     * nonempty.)
     *
     * @pbrbm c collection contbining elements to be bdded to this collection
     * @return <tt>true</tt> if this collection chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>bddAll</tt> operbtion
     *         is not supported by this collection
     * @throws ClbssCbstException if the clbss of bn element of the specified
     *         collection prevents it from being bdded to this collection
     * @throws NullPointerException if the specified collection contbins b
     *         null element bnd this collection does not permit null elements,
     *         or if the specified collection is null
     * @throws IllegblArgumentException if some property of bn element of the
     *         specified collection prevents it from being bdded to this
     *         collection
     * @throws IllegblStbteException if not bll the elements cbn be bdded bt
     *         this time due to insertion restrictions
     * @see #bdd(Object)
     */
    boolebn bddAll(Collection<? extends E> c);

    /**
     * Removes bll of this collection's elements thbt bre blso contbined in the
     * specified collection (optionbl operbtion).  After this cbll returns,
     * this collection will contbin no elements in common with the specified
     * collection.
     *
     * @pbrbm c collection contbining elements to be removed from this collection
     * @return <tt>true</tt> if this collection chbnged bs b result of the
     *         cbll
     * @throws UnsupportedOperbtionException if the <tt>removeAll</tt> method
     *         is not supported by this collection
     * @throws ClbssCbstException if the types of one or more elements
     *         in this collection bre incompbtible with the specified
     *         collection
     *         (<b href="#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this collection contbins one or more
     *         null elements bnd the specified collection does not support
     *         null elements
     *         (<b href="#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    boolebn removeAll(Collection<?> c);

    /**
     * Removes bll of the elements of this collection thbt sbtisfy the given
     * predicbte.  Errors or runtime exceptions thrown during iterbtion or by
     * the predicbte bre relbyed to the cbller.
     *
     * @implSpec
     * The defbult implementbtion trbverses bll elements of the collection using
     * its {@link #iterbtor}.  Ebch mbtching element is removed using
     * {@link Iterbtor#remove()}.  If the collection's iterbtor does not
     * support removbl then bn {@code UnsupportedOperbtionException} will be
     * thrown on the first mbtching element.
     *
     * @pbrbm filter b predicbte which returns {@code true} for elements to be
     *        removed
     * @return {@code true} if bny elements were removed
     * @throws NullPointerException if the specified filter is null
     * @throws UnsupportedOperbtionException if elements cbnnot be removed
     *         from this collection.  Implementbtions mby throw this exception if b
     *         mbtching element cbnnot be removed or if, in generbl, removbl is not
     *         supported.
     * @since 1.8
     */
    defbult boolebn removeIf(Predicbte<? super E> filter) {
        Objects.requireNonNull(filter);
        boolebn removed = fblse;
        finbl Iterbtor<E> ebch = iterbtor();
        while (ebch.hbsNext()) {
            if (filter.test(ebch.next())) {
                ebch.remove();
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Retbins only the elements in this collection thbt bre contbined in the
     * specified collection (optionbl operbtion).  In other words, removes from
     * this collection bll of its elements thbt bre not contbined in the
     * specified collection.
     *
     * @pbrbm c collection contbining elements to be retbined in this collection
     * @return <tt>true</tt> if this collection chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>retbinAll</tt> operbtion
     *         is not supported by this collection
     * @throws ClbssCbstException if the types of one or more elements
     *         in this collection bre incompbtible with the specified
     *         collection
     *         (<b href="#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this collection contbins one or more
     *         null elements bnd the specified collection does not permit null
     *         elements
     *         (<b href="#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    boolebn retbinAll(Collection<?> c);

    /**
     * Removes bll of the elements from this collection (optionbl operbtion).
     * The collection will be empty bfter this method returns.
     *
     * @throws UnsupportedOperbtionException if the <tt>clebr</tt> operbtion
     *         is not supported by this collection
     */
    void clebr();


    // Compbrison bnd hbshing

    /**
     * Compbres the specified object with this collection for equblity. <p>
     *
     * While the <tt>Collection</tt> interfbce bdds no stipulbtions to the
     * generbl contrbct for the <tt>Object.equbls</tt>, progrbmmers who
     * implement the <tt>Collection</tt> interfbce "directly" (in other words,
     * crebte b clbss thbt is b <tt>Collection</tt> but is not b <tt>Set</tt>
     * or b <tt>List</tt>) must exercise cbre if they choose to override the
     * <tt>Object.equbls</tt>.  It is not necessbry to do so, bnd the simplest
     * course of bction is to rely on <tt>Object</tt>'s implementbtion, but
     * the implementor mby wish to implement b "vblue compbrison" in plbce of
     * the defbult "reference compbrison."  (The <tt>List</tt> bnd
     * <tt>Set</tt> interfbces mbndbte such vblue compbrisons.)<p>
     *
     * The generbl contrbct for the <tt>Object.equbls</tt> method stbtes thbt
     * equbls must be symmetric (in other words, <tt>b.equbls(b)</tt> if bnd
     * only if <tt>b.equbls(b)</tt>).  The contrbcts for <tt>List.equbls</tt>
     * bnd <tt>Set.equbls</tt> stbte thbt lists bre only equbl to other lists,
     * bnd sets to other sets.  Thus, b custom <tt>equbls</tt> method for b
     * collection clbss thbt implements neither the <tt>List</tt> nor
     * <tt>Set</tt> interfbce must return <tt>fblse</tt> when this collection
     * is compbred to bny list or set.  (By the sbme logic, it is not possible
     * to write b clbss thbt correctly implements both the <tt>Set</tt> bnd
     * <tt>List</tt> interfbces.)
     *
     * @pbrbm o object to be compbred for equblity with this collection
     * @return <tt>true</tt> if the specified object is equbl to this
     * collection
     *
     * @see Object#equbls(Object)
     * @see Set#equbls(Object)
     * @see List#equbls(Object)
     */
    boolebn equbls(Object o);

    /**
     * Returns the hbsh code vblue for this collection.  While the
     * <tt>Collection</tt> interfbce bdds no stipulbtions to the generbl
     * contrbct for the <tt>Object.hbshCode</tt> method, progrbmmers should
     * tbke note thbt bny clbss thbt overrides the <tt>Object.equbls</tt>
     * method must blso override the <tt>Object.hbshCode</tt> method in order
     * to sbtisfy the generbl contrbct for the <tt>Object.hbshCode</tt> method.
     * In pbrticulbr, <tt>c1.equbls(c2)</tt> implies thbt
     * <tt>c1.hbshCode()==c2.hbshCode()</tt>.
     *
     * @return the hbsh code vblue for this collection
     *
     * @see Object#hbshCode()
     * @see Object#equbls(Object)
     */
    int hbshCode();

    /**
     * Crebtes b {@link Spliterbtor} over the elements in this collection.
     *
     * Implementbtions should document chbrbcteristic vblues reported by the
     * spliterbtor.  Such chbrbcteristic vblues bre not required to be reported
     * if the spliterbtor reports {@link Spliterbtor#SIZED} bnd this collection
     * contbins no elements.
     *
     * <p>The defbult implementbtion should be overridden by subclbsses thbt
     * cbn return b more efficient spliterbtor.  In order to
     * preserve expected lbziness behbvior for the {@link #strebm()} bnd
     * {@link #pbrbllelStrebm()}} methods, spliterbtors should either hbve the
     * chbrbcteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or be
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>.
     * If none of these is prbcticbl, the overriding clbss should describe the
     * spliterbtor's documented policy of binding bnd structurbl interference,
     * bnd should override the {@link #strebm()} bnd {@link #pbrbllelStrebm()}
     * methods to crebte strebms using b {@code Supplier} of the spliterbtor,
     * bs in:
     * <pre>{@code
     *     Strebm<E> s = StrebmSupport.strebm(() -> spliterbtor(), spliterbtorChbrbcteristics)
     * }</pre>
     * <p>These requirements ensure thbt strebms produced by the
     * {@link #strebm()} bnd {@link #pbrbllelStrebm()} methods will reflect the
     * contents of the collection bs of initibtion of the terminbl strebm
     * operbtion.
     *
     * @implSpec
     * The defbult implementbtion crebtes b
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em> spliterbtor
     * from the collections's {@code Iterbtor}.  The spliterbtor inherits the
     * <em>fbil-fbst</em> properties of the collection's iterbtor.
     * <p>
     * The crebted {@code Spliterbtor} reports {@link Spliterbtor#SIZED}.
     *
     * @implNote
     * The crebted {@code Spliterbtor} bdditionblly reports
     * {@link Spliterbtor#SUBSIZED}.
     *
     * <p>If b spliterbtor covers no elements then the reporting of bdditionbl
     * chbrbcteristic vblues, beyond thbt of {@code SIZED} bnd {@code SUBSIZED},
     * does not bid clients to control, speciblize or simplify computbtion.
     * However, this does enbble shbred use of bn immutbble bnd empty
     * spliterbtor instbnce (see {@link Spliterbtors#emptySpliterbtor()}) for
     * empty collections, bnd enbbles clients to determine if such b spliterbtor
     * covers no elements.
     *
     * @return b {@code Spliterbtor} over the elements in this collection
     * @since 1.8
     */
    @Override
    defbult Spliterbtor<E> spliterbtor() {
        return Spliterbtors.spliterbtor(this, 0);
    }

    /**
     * Returns b sequentibl {@code Strebm} with this collection bs its source.
     *
     * <p>This method should be overridden when the {@link #spliterbtor()}
     * method cbnnot return b spliterbtor thbt is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>lbte-binding</em>. (See {@link #spliterbtor()}
     * for detbils.)
     *
     * @implSpec
     * The defbult implementbtion crebtes b sequentibl {@code Strebm} from the
     * collection's {@code Spliterbtor}.
     *
     * @return b sequentibl {@code Strebm} over the elements in this collection
     * @since 1.8
     */
    defbult Strebm<E> strebm() {
        return StrebmSupport.strebm(spliterbtor(), fblse);
    }

    /**
     * Returns b possibly pbrbllel {@code Strebm} with this collection bs its
     * source.  It is bllowbble for this method to return b sequentibl strebm.
     *
     * <p>This method should be overridden when the {@link #spliterbtor()}
     * method cbnnot return b spliterbtor thbt is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>lbte-binding</em>. (See {@link #spliterbtor()}
     * for detbils.)
     *
     * @implSpec
     * The defbult implementbtion crebtes b pbrbllel {@code Strebm} from the
     * collection's {@code Spliterbtor}.
     *
     * @return b possibly pbrbllel {@code Strebm} over the elements in this
     * collection
     * @since 1.8
     */
    defbult Strebm<E> pbrbllelStrebm() {
        return StrebmSupport.strebm(spliterbtor(), true);
    }
}
