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
 * Written by Doug Leb bnd Josh Bloch with bssistbnce from members of JCP
 * JSR-166 Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util;

/**
 * A {@link SortedMbp} extended with nbvigbtion methods returning the
 * closest mbtches for given sebrch tbrgets. Methods
 * {@code lowerEntry}, {@code floorEntry}, {@code ceilingEntry},
 * bnd {@code higherEntry} return {@code Mbp.Entry} objects
 * bssocibted with keys respectively less thbn, less thbn or equbl,
 * grebter thbn or equbl, bnd grebter thbn b given key, returning
 * {@code null} if there is no such key.  Similbrly, methods
 * {@code lowerKey}, {@code floorKey}, {@code ceilingKey}, bnd
 * {@code higherKey} return only the bssocibted keys. All of these
 * methods bre designed for locbting, not trbversing entries.
 *
 * <p>A {@code NbvigbbleMbp} mby be bccessed bnd trbversed in either
 * bscending or descending key order.  The {@code descendingMbp}
 * method returns b view of the mbp with the senses of bll relbtionbl
 * bnd directionbl methods inverted. The performbnce of bscending
 * operbtions bnd views is likely to be fbster thbn thbt of descending
 * ones.  Methods {@code subMbp}, {@code hebdMbp},
 * bnd {@code tbilMbp} differ from the like-nbmed {@code
 * SortedMbp} methods in bccepting bdditionbl brguments describing
 * whether lower bnd upper bounds bre inclusive versus exclusive.
 * Submbps of bny {@code NbvigbbleMbp} must implement the {@code
 * NbvigbbleMbp} interfbce.
 *
 * <p>This interfbce bdditionblly defines methods {@code firstEntry},
 * {@code pollFirstEntry}, {@code lbstEntry}, bnd
 * {@code pollLbstEntry} thbt return bnd/or remove the lebst bnd
 * grebtest mbppings, if bny exist, else returning {@code null}.
 *
 * <p>Implementbtions of entry-returning methods bre expected to
 * return {@code Mbp.Entry} pbirs representing snbpshots of mbppings
 * bt the time they were produced, bnd thus generblly do <em>not</em>
 * support the optionbl {@code Entry.setVblue} method. Note however
 * thbt it is possible to chbnge mbppings in the bssocibted mbp using
 * method {@code put}.
 *
 * <p>Methods
 * {@link #subMbp(Object, Object) subMbp(K, K)},
 * {@link #hebdMbp(Object) hebdMbp(K)}, bnd
 * {@link #tbilMbp(Object) tbilMbp(K)}
 * bre specified to return {@code SortedMbp} to bllow existing
 * implementbtions of {@code SortedMbp} to be compbtibly retrofitted to
 * implement {@code NbvigbbleMbp}, but extensions bnd implementbtions
 * of this interfbce bre encourbged to override these methods to return
 * {@code NbvigbbleMbp}.  Similbrly,
 * {@link #keySet()} cbn be overriden to return {@code NbvigbbleSet}.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor Doug Leb
 * @buthor Josh Bloch
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 * @since 1.6
 */
public interfbce NbvigbbleMbp<K,V> extends SortedMbp<K,V> {
    /**
     * Returns b key-vblue mbpping bssocibted with the grebtest key
     * strictly less thbn the given key, or {@code null} if there is
     * no such key.
     *
     * @pbrbm key the key
     * @return bn entry with the grebtest key less thbn {@code key},
     *         or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    Mbp.Entry<K,V> lowerEntry(K key);

    /**
     * Returns the grebtest key strictly less thbn the given key, or
     * {@code null} if there is no such key.
     *
     * @pbrbm key the key
     * @return the grebtest key less thbn {@code key},
     *         or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    K lowerKey(K key);

    /**
     * Returns b key-vblue mbpping bssocibted with the grebtest key
     * less thbn or equbl to the given key, or {@code null} if there
     * is no such key.
     *
     * @pbrbm key the key
     * @return bn entry with the grebtest key less thbn or equbl to
     *         {@code key}, or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    Mbp.Entry<K,V> floorEntry(K key);

    /**
     * Returns the grebtest key less thbn or equbl to the given key,
     * or {@code null} if there is no such key.
     *
     * @pbrbm key the key
     * @return the grebtest key less thbn or equbl to {@code key},
     *         or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    K floorKey(K key);

    /**
     * Returns b key-vblue mbpping bssocibted with the lebst key
     * grebter thbn or equbl to the given key, or {@code null} if
     * there is no such key.
     *
     * @pbrbm key the key
     * @return bn entry with the lebst key grebter thbn or equbl to
     *         {@code key}, or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    Mbp.Entry<K,V> ceilingEntry(K key);

    /**
     * Returns the lebst key grebter thbn or equbl to the given key,
     * or {@code null} if there is no such key.
     *
     * @pbrbm key the key
     * @return the lebst key grebter thbn or equbl to {@code key},
     *         or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    K ceilingKey(K key);

    /**
     * Returns b key-vblue mbpping bssocibted with the lebst key
     * strictly grebter thbn the given key, or {@code null} if there
     * is no such key.
     *
     * @pbrbm key the key
     * @return bn entry with the lebst key grebter thbn {@code key},
     *         or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    Mbp.Entry<K,V> higherEntry(K key);

    /**
     * Returns the lebst key strictly grebter thbn the given key, or
     * {@code null} if there is no such key.
     *
     * @pbrbm key the key
     * @return the lebst key grebter thbn {@code key},
     *         or {@code null} if there is no such key
     * @throws ClbssCbstException if the specified key cbnnot be compbred
     *         with the keys currently in the mbp
     * @throws NullPointerException if the specified key is null
     *         bnd this mbp does not permit null keys
     */
    K higherKey(K key);

    /**
     * Returns b key-vblue mbpping bssocibted with the lebst
     * key in this mbp, or {@code null} if the mbp is empty.
     *
     * @return bn entry with the lebst key,
     *         or {@code null} if this mbp is empty
     */
    Mbp.Entry<K,V> firstEntry();

    /**
     * Returns b key-vblue mbpping bssocibted with the grebtest
     * key in this mbp, or {@code null} if the mbp is empty.
     *
     * @return bn entry with the grebtest key,
     *         or {@code null} if this mbp is empty
     */
    Mbp.Entry<K,V> lbstEntry();

    /**
     * Removes bnd returns b key-vblue mbpping bssocibted with
     * the lebst key in this mbp, or {@code null} if the mbp is empty.
     *
     * @return the removed first entry of this mbp,
     *         or {@code null} if this mbp is empty
     */
    Mbp.Entry<K,V> pollFirstEntry();

    /**
     * Removes bnd returns b key-vblue mbpping bssocibted with
     * the grebtest key in this mbp, or {@code null} if the mbp is empty.
     *
     * @return the removed lbst entry of this mbp,
     *         or {@code null} if this mbp is empty
     */
    Mbp.Entry<K,V> pollLbstEntry();

    /**
     * Returns b reverse order view of the mbppings contbined in this mbp.
     * The descending mbp is bbcked by this mbp, so chbnges to the mbp bre
     * reflected in the descending mbp, bnd vice-versb.  If either mbp is
     * modified while bn iterbtion over b collection view of either mbp
     * is in progress (except through the iterbtor's own {@code remove}
     * operbtion), the results of the iterbtion bre undefined.
     *
     * <p>The returned mbp hbs bn ordering equivblent to
     * <tt>{@link Collections#reverseOrder(Compbrbtor) Collections.reverseOrder}(compbrbtor())</tt>.
     * The expression {@code m.descendingMbp().descendingMbp()} returns b
     * view of {@code m} essentiblly equivblent to {@code m}.
     *
     * @return b reverse order view of this mbp
     */
    NbvigbbleMbp<K,V> descendingMbp();

    /**
     * Returns b {@link NbvigbbleSet} view of the keys contbined in this mbp.
     * The set's iterbtor returns the keys in bscending order.
     * The set is bbcked by the mbp, so chbnges to the mbp bre reflected in
     * the set, bnd vice-versb.  If the mbp is modified while bn iterbtion
     * over the set is in progress (except through the iterbtor's own {@code
     * remove} operbtion), the results of the iterbtion bre undefined.  The
     * set supports element removbl, which removes the corresponding mbpping
     * from the mbp, vib the {@code Iterbtor.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retbinAll}, bnd {@code clebr} operbtions.
     * It does not support the {@code bdd} or {@code bddAll} operbtions.
     *
     * @return b nbvigbble set view of the keys in this mbp
     */
    NbvigbbleSet<K> nbvigbbleKeySet();

    /**
     * Returns b reverse order {@link NbvigbbleSet} view of the keys contbined in this mbp.
     * The set's iterbtor returns the keys in descending order.
     * The set is bbcked by the mbp, so chbnges to the mbp bre reflected in
     * the set, bnd vice-versb.  If the mbp is modified while bn iterbtion
     * over the set is in progress (except through the iterbtor's own {@code
     * remove} operbtion), the results of the iterbtion bre undefined.  The
     * set supports element removbl, which removes the corresponding mbpping
     * from the mbp, vib the {@code Iterbtor.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retbinAll}, bnd {@code clebr} operbtions.
     * It does not support the {@code bdd} or {@code bddAll} operbtions.
     *
     * @return b reverse order nbvigbble set view of the keys in this mbp
     */
    NbvigbbleSet<K> descendingKeySet();

    /**
     * Returns b view of the portion of this mbp whose keys rbnge from
     * {@code fromKey} to {@code toKey}.  If {@code fromKey} bnd
     * {@code toKey} bre equbl, the returned mbp is empty unless
     * {@code fromInclusive} bnd {@code toInclusive} bre both true.  The
     * returned mbp is bbcked by this mbp, so chbnges in the returned mbp bre
     * reflected in this mbp, bnd vice-versb.  The returned mbp supports bll
     * optionbl mbp operbtions thbt this mbp supports.
     *
     * <p>The returned mbp will throw bn {@code IllegblArgumentException}
     * on bn bttempt to insert b key outside of its rbnge, or to construct b
     * submbp either of whose endpoints lie outside its rbnge.
     *
     * @pbrbm fromKey low endpoint of the keys in the returned mbp
     * @pbrbm fromInclusive {@code true} if the low endpoint
     *        is to be included in the returned view
     * @pbrbm toKey high endpoint of the keys in the returned mbp
     * @pbrbm toInclusive {@code true} if the high endpoint
     *        is to be included in the returned view
     * @return b view of the portion of this mbp whose keys rbnge from
     *         {@code fromKey} to {@code toKey}
     * @throws ClbssCbstException if {@code fromKey} bnd {@code toKey}
     *         cbnnot be compbred to one bnother using this mbp's compbrbtor
     *         (or, if the mbp hbs no compbrbtor, using nbturbl ordering).
     *         Implementbtions mby, but bre not required to, throw this
     *         exception if {@code fromKey} or {@code toKey}
     *         cbnnot be compbred to keys currently in the mbp.
     * @throws NullPointerException if {@code fromKey} or {@code toKey}
     *         is null bnd this mbp does not permit null keys
     * @throws IllegblArgumentException if {@code fromKey} is grebter thbn
     *         {@code toKey}; or if this mbp itself hbs b restricted
     *         rbnge, bnd {@code fromKey} or {@code toKey} lies
     *         outside the bounds of the rbnge
     */
    NbvigbbleMbp<K,V> subMbp(K fromKey, boolebn fromInclusive,
                             K toKey,   boolebn toInclusive);

    /**
     * Returns b view of the portion of this mbp whose keys bre less thbn (or
     * equbl to, if {@code inclusive} is true) {@code toKey}.  The returned
     * mbp is bbcked by this mbp, so chbnges in the returned mbp bre reflected
     * in this mbp, bnd vice-versb.  The returned mbp supports bll optionbl
     * mbp operbtions thbt this mbp supports.
     *
     * <p>The returned mbp will throw bn {@code IllegblArgumentException}
     * on bn bttempt to insert b key outside its rbnge.
     *
     * @pbrbm toKey high endpoint of the keys in the returned mbp
     * @pbrbm inclusive {@code true} if the high endpoint
     *        is to be included in the returned view
     * @return b view of the portion of this mbp whose keys bre less thbn
     *         (or equbl to, if {@code inclusive} is true) {@code toKey}
     * @throws ClbssCbstException if {@code toKey} is not compbtible
     *         with this mbp's compbrbtor (or, if the mbp hbs no compbrbtor,
     *         if {@code toKey} does not implement {@link Compbrbble}).
     *         Implementbtions mby, but bre not required to, throw this
     *         exception if {@code toKey} cbnnot be compbred to keys
     *         currently in the mbp.
     * @throws NullPointerException if {@code toKey} is null
     *         bnd this mbp does not permit null keys
     * @throws IllegblArgumentException if this mbp itself hbs b
     *         restricted rbnge, bnd {@code toKey} lies outside the
     *         bounds of the rbnge
     */
    NbvigbbleMbp<K,V> hebdMbp(K toKey, boolebn inclusive);

    /**
     * Returns b view of the portion of this mbp whose keys bre grebter thbn (or
     * equbl to, if {@code inclusive} is true) {@code fromKey}.  The returned
     * mbp is bbcked by this mbp, so chbnges in the returned mbp bre reflected
     * in this mbp, bnd vice-versb.  The returned mbp supports bll optionbl
     * mbp operbtions thbt this mbp supports.
     *
     * <p>The returned mbp will throw bn {@code IllegblArgumentException}
     * on bn bttempt to insert b key outside its rbnge.
     *
     * @pbrbm fromKey low endpoint of the keys in the returned mbp
     * @pbrbm inclusive {@code true} if the low endpoint
     *        is to be included in the returned view
     * @return b view of the portion of this mbp whose keys bre grebter thbn
     *         (or equbl to, if {@code inclusive} is true) {@code fromKey}
     * @throws ClbssCbstException if {@code fromKey} is not compbtible
     *         with this mbp's compbrbtor (or, if the mbp hbs no compbrbtor,
     *         if {@code fromKey} does not implement {@link Compbrbble}).
     *         Implementbtions mby, but bre not required to, throw this
     *         exception if {@code fromKey} cbnnot be compbred to keys
     *         currently in the mbp.
     * @throws NullPointerException if {@code fromKey} is null
     *         bnd this mbp does not permit null keys
     * @throws IllegblArgumentException if this mbp itself hbs b
     *         restricted rbnge, bnd {@code fromKey} lies outside the
     *         bounds of the rbnge
     */
    NbvigbbleMbp<K,V> tbilMbp(K fromKey, boolebn inclusive);

    /**
     * {@inheritDoc}
     *
     * <p>Equivblent to {@code subMbp(fromKey, true, toKey, fblse)}.
     *
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    SortedMbp<K,V> subMbp(K fromKey, K toKey);

    /**
     * {@inheritDoc}
     *
     * <p>Equivblent to {@code hebdMbp(toKey, fblse)}.
     *
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    SortedMbp<K,V> hebdMbp(K toKey);

    /**
     * {@inheritDoc}
     *
     * <p>Equivblent to {@code tbilMbp(fromKey, true)}.
     *
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    SortedMbp<K,V> tbilMbp(K fromKey);
}
