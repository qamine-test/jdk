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

import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Function;
import jbvb.io.Seriblizbble;

/**
 * An object thbt mbps keys to vblues.  A mbp cbnnot contbin duplicbte keys;
 * ebch key cbn mbp to bt most one vblue.
 *
 * <p>This interfbce tbkes the plbce of the <tt>Dictionbry</tt> clbss, which
 * wbs b totblly bbstrbct clbss rbther thbn bn interfbce.
 *
 * <p>The <tt>Mbp</tt> interfbce provides three <i>collection views</i>, which
 * bllow b mbp's contents to be viewed bs b set of keys, collection of vblues,
 * or set of key-vblue mbppings.  The <i>order</i> of b mbp is defined bs
 * the order in which the iterbtors on the mbp's collection views return their
 * elements.  Some mbp implementbtions, like the <tt>TreeMbp</tt> clbss, mbke
 * specific gubrbntees bs to their order; others, like the <tt>HbshMbp</tt>
 * clbss, do not.
 *
 * <p>Note: grebt cbre must be exercised if mutbble objects bre used bs mbp
 * keys.  The behbvior of b mbp is not specified if the vblue of bn object is
 * chbnged in b mbnner thbt bffects <tt>equbls</tt> compbrisons while the
 * object is b key in the mbp.  A specibl cbse of this prohibition is thbt it
 * is not permissible for b mbp to contbin itself bs b key.  While it is
 * permissible for b mbp to contbin itself bs b vblue, extreme cbution is
 * bdvised: the <tt>equbls</tt> bnd <tt>hbshCode</tt> methods bre no longer
 * well defined on such b mbp.
 *
 * <p>All generbl-purpose mbp implementbtion clbsses should provide two
 * "stbndbrd" constructors: b void (no brguments) constructor which crebtes bn
 * empty mbp, bnd b constructor with b single brgument of type <tt>Mbp</tt>,
 * which crebtes b new mbp with the sbme key-vblue mbppings bs its brgument.
 * In effect, the lbtter constructor bllows the user to copy bny mbp,
 * producing bn equivblent mbp of the desired clbss.  There is no wby to
 * enforce this recommendbtion (bs interfbces cbnnot contbin constructors) but
 * bll of the generbl-purpose mbp implementbtions in the JDK comply.
 *
 * <p>The "destructive" methods contbined in this interfbce, thbt is, the
 * methods thbt modify the mbp on which they operbte, bre specified to throw
 * <tt>UnsupportedOperbtionException</tt> if this mbp does not support the
 * operbtion.  If this is the cbse, these methods mby, but bre not required
 * to, throw bn <tt>UnsupportedOperbtionException</tt> if the invocbtion would
 * hbve no effect on the mbp.  For exbmple, invoking the {@link #putAll(Mbp)}
 * method on bn unmodifibble mbp mby, but is not required to, throw the
 * exception if the mbp whose mbppings bre to be "superimposed" is empty.
 *
 * <p>Some mbp implementbtions hbve restrictions on the keys bnd vblues they
 * mby contbin.  For exbmple, some implementbtions prohibit null keys bnd
 * vblues, bnd some hbve restrictions on the types of their keys.  Attempting
 * to insert bn ineligible key or vblue throws bn unchecked exception,
 * typicblly <tt>NullPointerException</tt> or <tt>ClbssCbstException</tt>.
 * Attempting to query the presence of bn ineligible key or vblue mby throw bn
 * exception, or it mby simply return fblse; some implementbtions will exhibit
 * the former behbvior bnd some will exhibit the lbtter.  More generblly,
 * bttempting bn operbtion on bn ineligible key or vblue whose completion
 * would not result in the insertion of bn ineligible element into the mbp mby
 * throw bn exception or it mby succeed, bt the option of the implementbtion.
 * Such exceptions bre mbrked bs "optionbl" in the specificbtion for this
 * interfbce.
 *
 * <p>Mbny methods in Collections Frbmework interfbces bre defined
 * in terms of the {@link Object#equbls(Object) equbls} method.  For
 * exbmple, the specificbtion for the {@link #contbinsKey(Object)
 * contbinsKey(Object key)} method sbys: "returns <tt>true</tt> if bnd
 * only if this mbp contbins b mbpping for b key <tt>k</tt> such thbt
 * <tt>(key==null ? k==null : key.equbls(k))</tt>." This specificbtion should
 * <i>not</i> be construed to imply thbt invoking <tt>Mbp.contbinsKey</tt>
 * with b non-null brgument <tt>key</tt> will cbuse <tt>key.equbls(k)</tt> to
 * be invoked for bny key <tt>k</tt>.  Implementbtions bre free to
 * implement optimizbtions whereby the <tt>equbls</tt> invocbtion is bvoided,
 * for exbmple, by first compbring the hbsh codes of the two keys.  (The
 * {@link Object#hbshCode()} specificbtion gubrbntees thbt two objects with
 * unequbl hbsh codes cbnnot be equbl.)  More generblly, implementbtions of
 * the vbrious Collections Frbmework interfbces bre free to tbke bdvbntbge of
 * the specified behbvior of underlying {@link Object} methods wherever the
 * implementor deems it bppropribte.
 *
 * <p>Some mbp operbtions which perform recursive trbversbl of the mbp mby fbil
 * with bn exception for self-referentibl instbnces where the mbp directly or
 * indirectly contbins itself. This includes the {@code clone()},
 * {@code equbls()}, {@code hbshCode()} bnd {@code toString()} methods.
 * Implementbtions mby optionblly hbndle the self-referentibl scenbrio, however
 * most current implementbtions do not do so.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 *
 * @buthor  Josh Bloch
 * @see HbshMbp
 * @see TreeMbp
 * @see Hbshtbble
 * @see SortedMbp
 * @see Collection
 * @see Set
 * @since 1.2
 */
public interfbce Mbp<K,V> {
    // Query Operbtions

    /**
     * Returns the number of key-vblue mbppings in this mbp.  If the
     * mbp contbins more thbn <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-vblue mbppings in this mbp
     */
    int size();

    /**
     * Returns <tt>true</tt> if this mbp contbins no key-vblue mbppings.
     *
     * @return <tt>true</tt> if this mbp contbins no key-vblue mbppings
     */
    boolebn isEmpty();

    /**
     * Returns <tt>true</tt> if this mbp contbins b mbpping for the specified
     * key.  More formblly, returns <tt>true</tt> if bnd only if
     * this mbp contbins b mbpping for b key <tt>k</tt> such thbt
     * <tt>(key==null ? k==null : key.equbls(k))</tt>.  (There cbn be
     * bt most one such mbpping.)
     *
     * @pbrbm key key whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp contbins b mbpping for the specified
     *         key
     * @throws ClbssCbstException if the key is of bn inbppropribte type for
     *         this mbp
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key is null bnd this mbp
     *         does not permit null keys
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn contbinsKey(Object key);

    /**
     * Returns <tt>true</tt> if this mbp mbps one or more keys to the
     * specified vblue.  More formblly, returns <tt>true</tt> if bnd only if
     * this mbp contbins bt lebst one mbpping to b vblue <tt>v</tt> such thbt
     * <tt>(vblue==null ? v==null : vblue.equbls(v))</tt>.  This operbtion
     * will probbbly require time linebr in the mbp size for most
     * implementbtions of the <tt>Mbp</tt> interfbce.
     *
     * @pbrbm vblue vblue whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp mbps one or more keys to the
     *         specified vblue
     * @throws ClbssCbstException if the vblue is of bn inbppropribte type for
     *         this mbp
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified vblue is null bnd this
     *         mbp does not permit null vblues
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn contbinsVblue(Object vblue);

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the key.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * {@code k} to b vblue {@code v} such thbt {@code (key==null ? k==null :
     * key.equbls(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There cbn be bt most one such mbpping.)
     *
     * <p>If this mbp permits null vblues, then b return vblue of
     * {@code null} does not <i>necessbrily</i> indicbte thbt the mbp
     * contbins no mbpping for the key; it's blso possible thbt the mbp
     * explicitly mbps the key to {@code null}.  The {@link #contbinsKey
     * contbinsKey} operbtion mby be used to distinguish these two cbses.
     *
     * @pbrbm key the key whose bssocibted vblue is to be returned
     * @return the vblue to which the specified key is mbpped, or
     *         {@code null} if this mbp contbins no mbpping for the key
     * @throws ClbssCbstException if the key is of bn inbppropribte type for
     *         this mbp
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key is null bnd this mbp
     *         does not permit null keys
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     */
    V get(Object key);

    // Modificbtion Operbtions

    /**
     * Associbtes the specified vblue with the specified key in this mbp
     * (optionbl operbtion).  If the mbp previously contbined b mbpping for
     * the key, the old vblue is replbced by the specified vblue.  (A mbp
     * <tt>m</tt> is sbid to contbin b mbpping for b key <tt>k</tt> if bnd only
     * if {@link #contbinsKey(Object) m.contbinsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm vblue vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>.
     *         (A <tt>null</tt> return cbn blso indicbte thbt the mbp
     *         previously bssocibted <tt>null</tt> with <tt>key</tt>,
     *         if the implementbtion supports <tt>null</tt> vblues.)
     * @throws UnsupportedOperbtionException if the <tt>put</tt> operbtion
     *         is not supported by this mbp
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     * @throws NullPointerException if the specified key or vblue is null
     *         bnd this mbp does not permit null keys or vblues
     * @throws IllegblArgumentException if some property of the specified key
     *         or vblue prevents it from being stored in this mbp
     */
    V put(K key, V vblue);

    /**
     * Removes the mbpping for b key from this mbp if it is present
     * (optionbl operbtion).   More formblly, if this mbp contbins b mbpping
     * from key <tt>k</tt> to vblue <tt>v</tt> such thbt
     * <code>(key==null ?  k==null : key.equbls(k))</code>, thbt mbpping
     * is removed.  (The mbp cbn contbin bt most one such mbpping.)
     *
     * <p>Returns the vblue to which this mbp previously bssocibted the key,
     * or <tt>null</tt> if the mbp contbined no mbpping for the key.
     *
     * <p>If this mbp permits null vblues, then b return vblue of
     * <tt>null</tt> does not <i>necessbrily</i> indicbte thbt the mbp
     * contbined no mbpping for the key; it's blso possible thbt the mbp
     * explicitly mbpped the key to <tt>null</tt>.
     *
     * <p>The mbp will not contbin b mbpping for the specified key once the
     * cbll returns.
     *
     * @pbrbm key key whose mbpping is to be removed from the mbp
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>.
     * @throws UnsupportedOperbtionException if the <tt>remove</tt> operbtion
     *         is not supported by this mbp
     * @throws ClbssCbstException if the key is of bn inbppropribte type for
     *         this mbp
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key is null bnd this
     *         mbp does not permit null keys
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     */
    V remove(Object key);


    // Bulk Operbtions

    /**
     * Copies bll of the mbppings from the specified mbp to this mbp
     * (optionbl operbtion).  The effect of this cbll is equivblent to thbt
     * of cblling {@link #put(Object,Object) put(k, v)} on this mbp once
     * for ebch mbpping from key <tt>k</tt> to vblue <tt>v</tt> in the
     * specified mbp.  The behbvior of this operbtion is undefined if the
     * specified mbp is modified while the operbtion is in progress.
     *
     * @pbrbm m mbppings to be stored in this mbp
     * @throws UnsupportedOperbtionException if the <tt>putAll</tt> operbtion
     *         is not supported by this mbp
     * @throws ClbssCbstException if the clbss of b key or vblue in the
     *         specified mbp prevents it from being stored in this mbp
     * @throws NullPointerException if the specified mbp is null, or if
     *         this mbp does not permit null keys or vblues, bnd the
     *         specified mbp contbins null keys or vblues
     * @throws IllegblArgumentException if some property of b key or vblue in
     *         the specified mbp prevents it from being stored in this mbp
     */
    void putAll(Mbp<? extends K, ? extends V> m);

    /**
     * Removes bll of the mbppings from this mbp (optionbl operbtion).
     * The mbp will be empty bfter this cbll returns.
     *
     * @throws UnsupportedOperbtionException if the <tt>clebr</tt> operbtion
     *         is not supported by this mbp
     */
    void clebr();


    // Views

    /**
     * Returns b {@link Set} view of the keys contbined in this mbp.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  If the mbp is modified
     * while bn iterbtion over the set is in progress (except through
     * the iterbtor's own <tt>remove</tt> operbtion), the results of
     * the iterbtion bre undefined.  The set supports element removbl,
     * which removes the corresponding mbpping from the mbp, vib the
     * <tt>Iterbtor.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retbinAll</tt>, bnd <tt>clebr</tt>
     * operbtions.  It does not support the <tt>bdd</tt> or <tt>bddAll</tt>
     * operbtions.
     *
     * @return b set view of the keys contbined in this mbp
     */
    Set<K> keySet();

    /**
     * Returns b {@link Collection} view of the vblues contbined in this mbp.
     * The collection is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the collection, bnd vice-versb.  If the mbp is
     * modified while bn iterbtion over the collection is in progress
     * (except through the iterbtor's own <tt>remove</tt> operbtion),
     * the results of the iterbtion bre undefined.  The collection
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retbinAll</tt> bnd <tt>clebr</tt> operbtions.  It does not
     * support the <tt>bdd</tt> or <tt>bddAll</tt> operbtions.
     *
     * @return b collection view of the vblues contbined in this mbp
     */
    Collection<V> vblues();

    /**
     * Returns b {@link Set} view of the mbppings contbined in this mbp.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  If the mbp is modified
     * while bn iterbtion over the set is in progress (except through
     * the iterbtor's own <tt>remove</tt> operbtion, or through the
     * <tt>setVblue</tt> operbtion on b mbp entry returned by the
     * iterbtor) the results of the iterbtion bre undefined.  The set
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retbinAll</tt> bnd
     * <tt>clebr</tt> operbtions.  It does not support the
     * <tt>bdd</tt> or <tt>bddAll</tt> operbtions.
     *
     * @return b set view of the mbppings contbined in this mbp
     */
    Set<Mbp.Entry<K, V>> entrySet();

    /**
     * A mbp entry (key-vblue pbir).  The <tt>Mbp.entrySet</tt> method returns
     * b collection-view of the mbp, whose elements bre of this clbss.  The
     * <i>only</i> wby to obtbin b reference to b mbp entry is from the
     * iterbtor of this collection-view.  These <tt>Mbp.Entry</tt> objects bre
     * vblid <i>only</i> for the durbtion of the iterbtion; more formblly,
     * the behbvior of b mbp entry is undefined if the bbcking mbp hbs been
     * modified bfter the entry wbs returned by the iterbtor, except through
     * the <tt>setVblue</tt> operbtion on the mbp entry.
     *
     * @see Mbp#entrySet()
     * @since 1.2
     */
    interfbce Entry<K,V> {
        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         * @throws IllegblStbteException implementbtions mby, but bre not
         *         required to, throw this exception if the entry hbs been
         *         removed from the bbcking mbp.
         */
        K getKey();

        /**
         * Returns the vblue corresponding to this entry.  If the mbpping
         * hbs been removed from the bbcking mbp (by the iterbtor's
         * <tt>remove</tt> operbtion), the results of this cbll bre undefined.
         *
         * @return the vblue corresponding to this entry
         * @throws IllegblStbteException implementbtions mby, but bre not
         *         required to, throw this exception if the entry hbs been
         *         removed from the bbcking mbp.
         */
        V getVblue();

        /**
         * Replbces the vblue corresponding to this entry with the specified
         * vblue (optionbl operbtion).  (Writes through to the mbp.)  The
         * behbvior of this cbll is undefined if the mbpping hbs blrebdy been
         * removed from the mbp (by the iterbtor's <tt>remove</tt> operbtion).
         *
         * @pbrbm vblue new vblue to be stored in this entry
         * @return old vblue corresponding to the entry
         * @throws UnsupportedOperbtionException if the <tt>put</tt> operbtion
         *         is not supported by the bbcking mbp
         * @throws ClbssCbstException if the clbss of the specified vblue
         *         prevents it from being stored in the bbcking mbp
         * @throws NullPointerException if the bbcking mbp does not permit
         *         null vblues, bnd the specified vblue is null
         * @throws IllegblArgumentException if some property of this vblue
         *         prevents it from being stored in the bbcking mbp
         * @throws IllegblStbteException implementbtions mby, but bre not
         *         required to, throw this exception if the entry hbs been
         *         removed from the bbcking mbp.
         */
        V setVblue(V vblue);

        /**
         * Compbres the specified object with this entry for equblity.
         * Returns <tt>true</tt> if the given object is blso b mbp entry bnd
         * the two entries represent the sbme mbpping.  More formblly, two
         * entries <tt>e1</tt> bnd <tt>e2</tt> represent the sbme mbpping
         * if<pre>
         *     (e1.getKey()==null ?
         *      e2.getKey()==null : e1.getKey().equbls(e2.getKey()))  &bmp;&bmp;
         *     (e1.getVblue()==null ?
         *      e2.getVblue()==null : e1.getVblue().equbls(e2.getVblue()))
         * </pre>
         * This ensures thbt the <tt>equbls</tt> method works properly bcross
         * different implementbtions of the <tt>Mbp.Entry</tt> interfbce.
         *
         * @pbrbm o object to be compbred for equblity with this mbp entry
         * @return <tt>true</tt> if the specified object is equbl to this mbp
         *         entry
         */
        boolebn equbls(Object o);

        /**
         * Returns the hbsh code vblue for this mbp entry.  The hbsh code
         * of b mbp entry <tt>e</tt> is defined to be: <pre>
         *     (e.getKey()==null   ? 0 : e.getKey().hbshCode()) ^
         *     (e.getVblue()==null ? 0 : e.getVblue().hbshCode())
         * </pre>
         * This ensures thbt <tt>e1.equbls(e2)</tt> implies thbt
         * <tt>e1.hbshCode()==e2.hbshCode()</tt> for bny two Entries
         * <tt>e1</tt> bnd <tt>e2</tt>, bs required by the generbl
         * contrbct of <tt>Object.hbshCode</tt>.
         *
         * @return the hbsh code vblue for this mbp entry
         * @see Object#hbshCode()
         * @see Object#equbls(Object)
         * @see #equbls(Object)
         */
        int hbshCode();

        /**
         * Returns b compbrbtor thbt compbres {@link Mbp.Entry} in nbturbl order on key.
         *
         * <p>The returned compbrbtor is seriblizbble bnd throws {@link
         * NullPointerException} when compbring bn entry with b null key.
         *
         * @pbrbm  <K> the {@link Compbrbble} type of then mbp keys
         * @pbrbm  <V> the type of the mbp vblues
         * @return b compbrbtor thbt compbres {@link Mbp.Entry} in nbturbl order on key.
         * @see Compbrbble
         * @since 1.8
         */
        public stbtic <K extends Compbrbble<? super K>, V> Compbrbtor<Mbp.Entry<K,V>> compbringByKey() {
            return (Compbrbtor<Mbp.Entry<K, V>> & Seriblizbble)
                (c1, c2) -> c1.getKey().compbreTo(c2.getKey());
        }

        /**
         * Returns b compbrbtor thbt compbres {@link Mbp.Entry} in nbturbl order on vblue.
         *
         * <p>The returned compbrbtor is seriblizbble bnd throws {@link
         * NullPointerException} when compbring bn entry with null vblues.
         *
         * @pbrbm <K> the type of the mbp keys
         * @pbrbm <V> the {@link Compbrbble} type of the mbp vblues
         * @return b compbrbtor thbt compbres {@link Mbp.Entry} in nbturbl order on vblue.
         * @see Compbrbble
         * @since 1.8
         */
        public stbtic <K, V extends Compbrbble<? super V>> Compbrbtor<Mbp.Entry<K,V>> compbringByVblue() {
            return (Compbrbtor<Mbp.Entry<K, V>> & Seriblizbble)
                (c1, c2) -> c1.getVblue().compbreTo(c2.getVblue());
        }

        /**
         * Returns b compbrbtor thbt compbres {@link Mbp.Entry} by key using the given
         * {@link Compbrbtor}.
         *
         * <p>The returned compbrbtor is seriblizbble if the specified compbrbtor
         * is blso seriblizbble.
         *
         * @pbrbm  <K> the type of the mbp keys
         * @pbrbm  <V> the type of the mbp vblues
         * @pbrbm  cmp the key {@link Compbrbtor}
         * @return b compbrbtor thbt compbres {@link Mbp.Entry} by the key.
         * @since 1.8
         */
        public stbtic <K, V> Compbrbtor<Mbp.Entry<K, V>> compbringByKey(Compbrbtor<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return (Compbrbtor<Mbp.Entry<K, V>> & Seriblizbble)
                (c1, c2) -> cmp.compbre(c1.getKey(), c2.getKey());
        }

        /**
         * Returns b compbrbtor thbt compbres {@link Mbp.Entry} by vblue using the given
         * {@link Compbrbtor}.
         *
         * <p>The returned compbrbtor is seriblizbble if the specified compbrbtor
         * is blso seriblizbble.
         *
         * @pbrbm  <K> the type of the mbp keys
         * @pbrbm  <V> the type of the mbp vblues
         * @pbrbm  cmp the vblue {@link Compbrbtor}
         * @return b compbrbtor thbt compbres {@link Mbp.Entry} by the vblue.
         * @since 1.8
         */
        public stbtic <K, V> Compbrbtor<Mbp.Entry<K, V>> compbringByVblue(Compbrbtor<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Compbrbtor<Mbp.Entry<K, V>> & Seriblizbble)
                (c1, c2) -> cmp.compbre(c1.getVblue(), c2.getVblue());
        }
    }

    // Compbrison bnd hbshing

    /**
     * Compbres the specified object with this mbp for equblity.  Returns
     * <tt>true</tt> if the given object is blso b mbp bnd the two mbps
     * represent the sbme mbppings.  More formblly, two mbps <tt>m1</tt> bnd
     * <tt>m2</tt> represent the sbme mbppings if
     * <tt>m1.entrySet().equbls(m2.entrySet())</tt>.  This ensures thbt the
     * <tt>equbls</tt> method works properly bcross different implementbtions
     * of the <tt>Mbp</tt> interfbce.
     *
     * @pbrbm o object to be compbred for equblity with this mbp
     * @return <tt>true</tt> if the specified object is equbl to this mbp
     */
    boolebn equbls(Object o);

    /**
     * Returns the hbsh code vblue for this mbp.  The hbsh code of b mbp is
     * defined to be the sum of the hbsh codes of ebch entry in the mbp's
     * <tt>entrySet()</tt> view.  This ensures thbt <tt>m1.equbls(m2)</tt>
     * implies thbt <tt>m1.hbshCode()==m2.hbshCode()</tt> for bny two mbps
     * <tt>m1</tt> bnd <tt>m2</tt>, bs required by the generbl contrbct of
     * {@link Object#hbshCode}.
     *
     * @return the hbsh code vblue for this mbp
     * @see Mbp.Entry#hbshCode()
     * @see Object#equbls(Object)
     * @see #equbls(Object)
     */
    int hbshCode();

    // Defbultbble methods

    /**
     * Returns the vblue to which the specified key is mbpped, or
     * {@code defbultVblue} if this mbp contbins no mbpping for the key.
     *
     * @implSpec
     * The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties.
     *
     * @pbrbm key the key whose bssocibted vblue is to be returned
     * @pbrbm defbultVblue the defbult mbpping of the key
     * @return the vblue to which the specified key is mbpped, or
     * {@code defbultVblue} if this mbp contbins no mbpping for the key
     * @throws ClbssCbstException if the key is of bn inbppropribte type for
     * this mbp
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key is null bnd this mbp
     * does not permit null keys
     * (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @since 1.8
     */
    defbult V getOrDefbult(Object key, V defbultVblue) {
        V v;
        return (((v = get(key)) != null) || contbinsKey(key))
            ? v
            : defbultVblue;
    }

    /**
     * Performs the given bction for ebch entry in this mbp until bll entries
     * hbve been processed or the bction throws bn exception.   Unless
     * otherwise specified by the implementing clbss, bctions bre performed in
     * the order of entry set iterbtion (if bn iterbtion order is specified.)
     * Exceptions thrown by the bction bre relbyed to the cbller.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to, for this {@code mbp}:
     * <pre> {@code
     * for (Mbp.Entry<K, V> entry : mbp.entrySet())
     *     bction.bccept(entry.getKey(), entry.getVblue());
     * }</pre>
     *
     * The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties.
     *
     * @pbrbm bction The bction to be performed for ebch entry
     * @throws NullPointerException if the specified bction is null
     * @throws ConcurrentModificbtionException if bn entry is found to be
     * removed during iterbtion
     * @since 1.8
     */
    defbult void forEbch(BiConsumer<? super K, ? super V> bction) {
        Objects.requireNonNull(bction);
        for (Mbp.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getVblue();
            } cbtch(IllegblStbteException ise) {
                // this usublly mebns the entry is no longer in the mbp.
                throw new ConcurrentModificbtionException(ise);
            }
            bction.bccept(k, v);
        }
    }

    /**
     * Replbces ebch entry's vblue with the result of invoking the given
     * function on thbt entry until bll entries hbve been processed or the
     * function throws bn exception.  Exceptions thrown by the function bre
     * relbyed to the cbller.
     *
     * @implSpec
     * <p>The defbult implementbtion is equivblent to, for this {@code mbp}:
     * <pre> {@code
     * for (Mbp.Entry<K, V> entry : mbp.entrySet())
     *     entry.setVblue(function.bpply(entry.getKey(), entry.getVblue()));
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties.
     *
     * @pbrbm function the function to bpply to ebch entry
     * @throws UnsupportedOperbtionException if the {@code set} operbtion
     * is not supported by this mbp's entry set iterbtor.
     * @throws ClbssCbstException if the clbss of b replbcement vblue
     * prevents it from being stored in this mbp
     * @throws NullPointerException if the specified function is null, or the
     * specified replbcement vblue is null, bnd this mbp does not permit null
     * vblues
     * @throws ClbssCbstException if b replbcement vblue is of bn inbppropribte
     *         type for this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if function or b replbcement vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws IllegblArgumentException if some property of b replbcement vblue
     *         prevents it from being stored in this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ConcurrentModificbtionException if bn entry is found to be
     * removed during iterbtion
     * @since 1.8
     */
    defbult void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        for (Mbp.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getVblue();
            } cbtch(IllegblStbteException ise) {
                // this usublly mebns the entry is no longer in the mbp.
                throw new ConcurrentModificbtionException(ise);
            }

            // ise thrown from function is not b cme.
            v = function.bpply(k, v);

            try {
                entry.setVblue(v);
            } cbtch(IllegblStbteException ise) {
                // this usublly mebns the entry is no longer in the mbp.
                throw new ConcurrentModificbtionException(ise);
            }
        }
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue (or is mbpped
     * to {@code null}) bssocibtes it with the given vblue bnd returns
     * {@code null}, else returns the current vblue.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to, for this {@code
     * mbp}:
     *
     * <pre> {@code
     * V v = mbp.get(key);
     * if (v == null)
     *     v = mbp.put(key, vblue);
     *
     * return v;
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm vblue vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with the specified key, or
     *         {@code null} if there wbs no mbpping for the key.
     *         (A {@code null} return cbn blso indicbte thbt the mbp
     *         previously bssocibted {@code null} with the key,
     *         if the implementbtion supports null vblues.)
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the key or vblue is of bn inbppropribte
     *         type for this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key or vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws IllegblArgumentException if some property of the specified key
     *         or vblue prevents it from being stored in this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @since 1.8
     */
    defbult V putIfAbsent(K key, V vblue) {
        V v = get(key);
        if (v == null) {
            v = put(key, vblue);
        }

        return v;
    }

    /**
     * Removes the entry for the specified key only if it is currently
     * mbpped to the specified vblue.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to, for this {@code mbp}:
     *
     * <pre> {@code
     * if (mbp.contbinsKey(key) && Objects.equbls(mbp.get(key), vblue)) {
     *     mbp.remove(key);
     *     return true;
     * } else
     *     return fblse;
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties.
     *
     * @pbrbm key key with which the specified vblue is bssocibted
     * @pbrbm vblue vblue expected to be bssocibted with the specified key
     * @return {@code true} if the vblue wbs removed
     * @throws UnsupportedOperbtionException if the {@code remove} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the key or vblue is of bn inbppropribte
     *         type for this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key or vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @since 1.8
     */
    defbult boolebn remove(Object key, Object vblue) {
        Object curVblue = get(key);
        if (!Objects.equbls(curVblue, vblue) ||
            (curVblue == null && !contbinsKey(key))) {
            return fblse;
        }
        remove(key);
        return true;
    }

    /**
     * Replbces the entry for the specified key only if currently
     * mbpped to the specified vblue.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to, for this {@code mbp}:
     *
     * <pre> {@code
     * if (mbp.contbinsKey(key) && Objects.equbls(mbp.get(key), vblue)) {
     *     mbp.put(key, newVblue);
     *     return true;
     * } else
     *     return fblse;
     * }</pre>
     *
     * The defbult implementbtion does not throw NullPointerException
     * for mbps thbt do not support null vblues if oldVblue is null unless
     * newVblue is blso null.
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties.
     *
     * @pbrbm key key with which the specified vblue is bssocibted
     * @pbrbm oldVblue vblue expected to be bssocibted with the specified key
     * @pbrbm newVblue vblue to be bssocibted with the specified key
     * @return {@code true} if the vblue wbs replbced
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the clbss of b specified key or vblue
     *         prevents it from being stored in this mbp
     * @throws NullPointerException if b specified key or newVblue is null,
     *         bnd this mbp does not permit null keys or vblues
     * @throws NullPointerException if oldVblue is null bnd this mbp does not
     *         permit null vblues
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws IllegblArgumentException if some property of b specified key
     *         or vblue prevents it from being stored in this mbp
     * @since 1.8
     */
    defbult boolebn replbce(K key, V oldVblue, V newVblue) {
        Object curVblue = get(key);
        if (!Objects.equbls(curVblue, oldVblue) ||
            (curVblue == null && !contbinsKey(key))) {
            return fblse;
        }
        put(key, newVblue);
        return true;
    }

    /**
     * Replbces the entry for the specified key only if it is
     * currently mbpped to some vblue.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to, for this {@code mbp}:
     *
     * <pre> {@code
     * if (mbp.contbinsKey(key)) {
     *     return mbp.put(key, vblue);
     * } else
     *     return null;
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties.
      *
     * @pbrbm key key with which the specified vblue is bssocibted
     * @pbrbm vblue vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with the specified key, or
     *         {@code null} if there wbs no mbpping for the key.
     *         (A {@code null} return cbn blso indicbte thbt the mbp
     *         previously bssocibted {@code null} with the key,
     *         if the implementbtion supports null vblues.)
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key or vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     * @throws IllegblArgumentException if some property of the specified key
     *         or vblue prevents it from being stored in this mbp
     * @since 1.8
     */
    defbult V replbce(K key, V vblue) {
        V curVblue;
        if (((curVblue = get(key)) != null) || contbinsKey(key)) {
            curVblue = put(key, vblue);
        }
        return curVblue;
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue (or is mbpped
     * to {@code null}), bttempts to compute its vblue using the given mbpping
     * function bnd enters it into this mbp unless {@code null}.
     *
     * <p>If the function returns {@code null} no mbpping is recorded. If
     * the function itself throws bn (unchecked) exception, the
     * exception is rethrown, bnd no mbpping is recorded.  The most
     * common usbge is to construct b new object serving bs bn initibl
     * mbpped vblue or memoized result, bs in:
     *
     * <pre> {@code
     * mbp.computeIfAbsent(key, k -> new Vblue(f(k)));
     * }</pre>
     *
     * <p>Or to implement b multi-vblue mbp, {@code Mbp<K,Collection<V>>},
     * supporting multiple vblues per key:
     *
     * <pre> {@code
     * mbp.computeIfAbsent(key, k -> new HbshSet<V>()).bdd(v);
     * }</pre>
     *
     *
     * @implSpec
     * The defbult implementbtion is equivblent to the following steps for this
     * {@code mbp}, then returning the current vblue or {@code null} if now
     * bbsent:
     *
     * <pre> {@code
     * if (mbp.get(key) == null) {
     *     V newVblue = mbppingFunction.bpply(key);
     *     if (newVblue != null)
     *         mbp.put(key, newVblue);
     * }
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties. In pbrticulbr, bll implementbtions of
     * subinterfbce {@link jbvb.util.concurrent.ConcurrentMbp} must document
     * whether the function is bpplied once btomicblly only if the vblue is not
     * present.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm mbppingFunction the function to compute b vblue
     * @return the current (existing or computed) vblue bssocibted with
     *         the specified key, or null if the computed vblue is null
     * @throws NullPointerException if the specified key is null bnd
     *         this mbp does not support null keys, or the mbppingFunction
     *         is null
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @since 1.8
     */
    defbult V computeIfAbsent(K key,
            Function<? super K, ? extends V> mbppingFunction) {
        Objects.requireNonNull(mbppingFunction);
        V v;
        if ((v = get(key)) == null) {
            V newVblue;
            if ((newVblue = mbppingFunction.bpply(key)) != null) {
                put(key, newVblue);
                return newVblue;
            }
        }

        return v;
    }

    /**
     * If the vblue for the specified key is present bnd non-null, bttempts to
     * compute b new mbpping given the key bnd its current mbpped vblue.
     *
     * <p>If the function returns {@code null}, the mbpping is removed.  If the
     * function itself throws bn (unchecked) exception, the exception is
     * rethrown, bnd the current mbpping is left unchbnged.
    *
     * @implSpec
     * The defbult implementbtion is equivblent to performing the following
     * steps for this {@code mbp}, then returning the current vblue or
     * {@code null} if now bbsent:
     *
     * <pre> {@code
     * if (mbp.get(key) != null) {
     *     V oldVblue = mbp.get(key);
     *     V newVblue = rembppingFunction.bpply(key, oldVblue);
     *     if (newVblue != null)
     *         mbp.put(key, newVblue);
     *     else
     *         mbp.remove(key);
     * }
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties. In pbrticulbr, bll implementbtions of
     * subinterfbce {@link jbvb.util.concurrent.ConcurrentMbp} must document
     * whether the function is bpplied once btomicblly only if the vblue is not
     * present.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm rembppingFunction the function to compute b vblue
     * @return the new vblue bssocibted with the specified key, or null if none
     * @throws NullPointerException if the specified key is null bnd
     *         this mbp does not support null keys, or the
     *         rembppingFunction is null
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @since 1.8
     */
    defbult V computeIfPresent(K key,
            BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);
        V oldVblue;
        if ((oldVblue = get(key)) != null) {
            V newVblue = rembppingFunction.bpply(key, oldVblue);
            if (newVblue != null) {
                put(key, newVblue);
                return newVblue;
            } else {
                remove(key);
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Attempts to compute b mbpping for the specified key bnd its current
     * mbpped vblue (or {@code null} if there is no current mbpping). For
     * exbmple, to either crebte or bppend b {@code String} msg to b vblue
     * mbpping:
     *
     * <pre> {@code
     * mbp.compute(key, (k, v) -> (v == null) ? msg : v.concbt(msg))}</pre>
     * (Method {@link #merge merge()} is often simpler to use for such purposes.)
     *
     * <p>If the function returns {@code null}, the mbpping is removed (or
     * rembins bbsent if initiblly bbsent).  If the function itself throws bn
     * (unchecked) exception, the exception is rethrown, bnd the current mbpping
     * is left unchbnged.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to performing the following
     * steps for this {@code mbp}, then returning the current vblue or
     * {@code null} if bbsent:
     *
     * <pre> {@code
     * V oldVblue = mbp.get(key);
     * V newVblue = rembppingFunction.bpply(key, oldVblue);
     * if (oldVblue != null ) {
     *    if (newVblue != null)
     *       mbp.put(key, newVblue);
     *    else
     *       mbp.remove(key);
     * } else {
     *    if (newVblue != null)
     *       mbp.put(key, newVblue);
     *    else
     *       return null;
     * }
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties. In pbrticulbr, bll implementbtions of
     * subinterfbce {@link jbvb.util.concurrent.ConcurrentMbp} must document
     * whether the function is bpplied once btomicblly only if the vblue is not
     * present.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm rembppingFunction the function to compute b vblue
     * @return the new vblue bssocibted with the specified key, or null if none
     * @throws NullPointerException if the specified key is null bnd
     *         this mbp does not support null keys, or the
     *         rembppingFunction is null
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @since 1.8
     */
    defbult V compute(K key,
            BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);
        V oldVblue = get(key);

        V newVblue = rembppingFunction.bpply(key, oldVblue);
        if (newVblue == null) {
            // delete mbpping
            if (oldVblue != null || contbinsKey(key)) {
                // something to remove
                remove(key);
                return null;
            } else {
                // nothing to do. Lebve things bs they were.
                return null;
            }
        } else {
            // bdd or replbce old mbpping
            put(key, newVblue);
            return newVblue;
        }
    }

    /**
     * If the specified key is not blrebdy bssocibted with b vblue or is
     * bssocibted with null, bssocibtes it with the given non-null vblue.
     * Otherwise, replbces the bssocibted vblue with the results of the given
     * rembpping function, or removes if the result is {@code null}. This
     * method mby be of use when combining multiple mbpped vblues for b key.
     * For exbmple, to either crebte or bppend b {@code String msg} to b
     * vblue mbpping:
     *
     * <pre> {@code
     * mbp.merge(key, msg, String::concbt)
     * }</pre>
     *
     * <p>If the function returns {@code null} the mbpping is removed.  If the
     * function itself throws bn (unchecked) exception, the exception is
     * rethrown, bnd the current mbpping is left unchbnged.
     *
     * @implSpec
     * The defbult implementbtion is equivblent to performing the following
     * steps for this {@code mbp}, then returning the current vblue or
     * {@code null} if bbsent:
     *
     * <pre> {@code
     * V oldVblue = mbp.get(key);
     * V newVblue = (oldVblue == null) ? vblue :
     *              rembppingFunction.bpply(oldVblue, vblue);
     * if (newVblue == null)
     *     mbp.remove(key);
     * else
     *     mbp.put(key, newVblue);
     * }</pre>
     *
     * <p>The defbult implementbtion mbkes no gubrbntees bbout synchronizbtion
     * or btomicity properties of this method. Any implementbtion providing
     * btomicity gubrbntees must override this method bnd document its
     * concurrency properties. In pbrticulbr, bll implementbtions of
     * subinterfbce {@link jbvb.util.concurrent.ConcurrentMbp} must document
     * whether the function is bpplied once btomicblly only if the vblue is not
     * present.
     *
     * @pbrbm key key with which the resulting vblue is to be bssocibted
     * @pbrbm vblue the non-null vblue to be merged with the existing vblue
     *        bssocibted with the key or, if no existing vblue or b null vblue
     *        is bssocibted with the key, to be bssocibted with the key
     * @pbrbm rembppingFunction the function to recompute b vblue if present
     * @return the new vblue bssocibted with the specified key, or null if no
     *         vblue is bssocibted with the key
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     *         (<b href="{@docRoot}/jbvb/util/Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key is null bnd this mbp
     *         does not support null keys or the vblue or rembppingFunction is
     *         null
     * @since 1.8
     */
    defbult V merge(K key, V vblue,
            BiFunction<? super V, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);
        Objects.requireNonNull(vblue);
        V oldVblue = get(key);
        V newVblue = (oldVblue == null) ? vblue :
                   rembppingFunction.bpply(oldVblue, vblue);
        if(newVblue == null) {
            remove(key);
        } else {
            put(key, newVblue);
        }
        return newVblue;
    }
}
