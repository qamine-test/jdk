/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp.Entry;
import sun.misc.ShbredSecrets;

/**
 * A speciblized {@link Mbp} implementbtion for use with enum type keys.  All
 * of the keys in bn enum mbp must come from b single enum type thbt is
 * specified, explicitly or implicitly, when the mbp is crebted.  Enum mbps
 * bre represented internblly bs brrbys.  This representbtion is extremely
 * compbct bnd efficient.
 *
 * <p>Enum mbps bre mbintbined in the <i>nbturbl order</i> of their keys
 * (the order in which the enum constbnts bre declbred).  This is reflected
 * in the iterbtors returned by the collections views ({@link #keySet()},
 * {@link #entrySet()}, bnd {@link #vblues()}).
 *
 * <p>Iterbtors returned by the collection views bre <i>webkly consistent</i>:
 * they will never throw {@link ConcurrentModificbtionException} bnd they mby
 * or mby not show the effects of bny modificbtions to the mbp thbt occur while
 * the iterbtion is in progress.
 *
 * <p>Null keys bre not permitted.  Attempts to insert b null key will
 * throw {@link NullPointerException}.  Attempts to test for the
 * presence of b null key or to remove one will, however, function properly.
 * Null vblues bre permitted.

 * <P>Like most collection implementbtions <tt>EnumMbp</tt> is not
 * synchronized. If multiple threbds bccess bn enum mbp concurrently, bnd bt
 * lebst one of the threbds modifies the mbp, it should be synchronized
 * externblly.  This is typicblly bccomplished by synchronizing on some
 * object thbt nbturblly encbpsulbtes the enum mbp.  If no such object exists,
 * the mbp should be "wrbpped" using the {@link Collections#synchronizedMbp}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess:
 *
 * <pre>
 *     Mbp&lt;EnumKey, V&gt; m
 *         = Collections.synchronizedMbp(new EnumMbp&lt;EnumKey, V&gt;(...));
 * </pre>
 *
 * <p>Implementbtion note: All bbsic operbtions execute in constbnt time.
 * They bre likely (though not gubrbnteed) to be fbster thbn their
 * {@link HbshMbp} counterpbrts.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor Josh Bloch
 * @see EnumSet
 * @since 1.5
 */
public clbss EnumMbp<K extends Enum<K>, V> extends AbstrbctMbp<K, V>
    implements jbvb.io.Seriblizbble, Clonebble
{
    /**
     * The <tt>Clbss</tt> object for the enum type of bll the keys of this mbp.
     *
     * @seribl
     */
    privbte finbl Clbss<K> keyType;

    /**
     * All of the vblues comprising K.  (Cbched for performbnce.)
     */
    privbte trbnsient K[] keyUniverse;

    /**
     * Arrby representbtion of this mbp.  The ith element is the vblue
     * to which universe[i] is currently mbpped, or null if it isn't
     * mbpped to bnything, or NULL if it's mbpped to null.
     */
    privbte trbnsient Object[] vbls;

    /**
     * The number of mbppings in this mbp.
     */
    privbte trbnsient int size = 0;

    /**
     * Distinguished non-null vblue for representing null vblues.
     */
    privbte stbtic finbl Object NULL = new Object() {
        public int hbshCode() {
            return 0;
        }

        public String toString() {
            return "jbvb.util.EnumMbp.NULL";
        }
    };

    privbte Object mbskNull(Object vblue) {
        return (vblue == null ? NULL : vblue);
    }

    @SuppressWbrnings("unchecked")
    privbte V unmbskNull(Object vblue) {
        return (V)(vblue == NULL ? null : vblue);
    }

    privbte stbtic finbl Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum<?>[0];

    /**
     * Crebtes bn empty enum mbp with the specified key type.
     *
     * @pbrbm keyType the clbss object of the key type for this enum mbp
     * @throws NullPointerException if <tt>keyType</tt> is null
     */
    public EnumMbp(Clbss<K> keyType) {
        this.keyType = keyType;
        keyUniverse = getKeyUniverse(keyType);
        vbls = new Object[keyUniverse.length];
    }

    /**
     * Crebtes bn enum mbp with the sbme key type bs the specified enum
     * mbp, initiblly contbining the sbme mbppings (if bny).
     *
     * @pbrbm m the enum mbp from which to initiblize this enum mbp
     * @throws NullPointerException if <tt>m</tt> is null
     */
    public EnumMbp(EnumMbp<K, ? extends V> m) {
        keyType = m.keyType;
        keyUniverse = m.keyUniverse;
        vbls = m.vbls.clone();
        size = m.size;
    }

    /**
     * Crebtes bn enum mbp initiblized from the specified mbp.  If the
     * specified mbp is bn <tt>EnumMbp</tt> instbnce, this constructor behbves
     * identicblly to {@link #EnumMbp(EnumMbp)}.  Otherwise, the specified mbp
     * must contbin bt lebst one mbpping (in order to determine the new
     * enum mbp's key type).
     *
     * @pbrbm m the mbp from which to initiblize this enum mbp
     * @throws IllegblArgumentException if <tt>m</tt> is not bn
     *     <tt>EnumMbp</tt> instbnce bnd contbins no mbppings
     * @throws NullPointerException if <tt>m</tt> is null
     */
    public EnumMbp(Mbp<K, ? extends V> m) {
        if (m instbnceof EnumMbp) {
            EnumMbp<K, ? extends V> em = (EnumMbp<K, ? extends V>) m;
            keyType = em.keyType;
            keyUniverse = em.keyUniverse;
            vbls = em.vbls.clone();
            size = em.size;
        } else {
            if (m.isEmpty())
                throw new IllegblArgumentException("Specified mbp is empty");
            keyType = m.keySet().iterbtor().next().getDeclbringClbss();
            keyUniverse = getKeyUniverse(keyType);
            vbls = new Object[keyUniverse.length];
            putAll(m);
        }
    }

    // Query Operbtions

    /**
     * Returns the number of key-vblue mbppings in this mbp.
     *
     * @return the number of key-vblue mbppings in this mbp
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this mbp mbps one or more keys to the
     * specified vblue.
     *
     * @pbrbm vblue the vblue whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp mbps one or more keys to this vblue
     */
    public boolebn contbinsVblue(Object vblue) {
        vblue = mbskNull(vblue);

        for (Object vbl : vbls)
            if (vblue.equbls(vbl))
                return true;

        return fblse;
    }

    /**
     * Returns <tt>true</tt> if this mbp contbins b mbpping for the specified
     * key.
     *
     * @pbrbm key the key whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp contbins b mbpping for the specified
     *            key
     */
    public boolebn contbinsKey(Object key) {
        return isVblidKey(key) && vbls[((Enum<?>)key).ordinbl()] != null;
    }

    privbte boolebn contbinsMbpping(Object key, Object vblue) {
        return isVblidKey(key) &&
            mbskNull(vblue).equbls(vbls[((Enum<?>)key).ordinbl()]);
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the key.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * {@code k} to b vblue {@code v} such thbt {@code (key == k)},
     * then this method returns {@code v}; otherwise it returns
     * {@code null}.  (There cbn be bt most one such mbpping.)
     *
     * <p>A return vblue of {@code null} does not <i>necessbrily</i>
     * indicbte thbt the mbp contbins no mbpping for the key; it's blso
     * possible thbt the mbp explicitly mbps the key to {@code null}.
     * The {@link #contbinsKey contbinsKey} operbtion mby be used to
     * distinguish these two cbses.
     */
    public V get(Object key) {
        return (isVblidKey(key) ?
                unmbskNull(vbls[((Enum<?>)key).ordinbl()]) : null);
    }

    // Modificbtion Operbtions

    /**
     * Associbtes the specified vblue with the specified key in this mbp.
     * If the mbp previously contbined b mbpping for this key, the old
     * vblue is replbced.
     *
     * @pbrbm key the key with which the specified vblue is to be bssocibted
     * @pbrbm vblue the vblue to be bssocibted with the specified key
     *
     * @return the previous vblue bssocibted with specified key, or
     *     <tt>null</tt> if there wbs no mbpping for key.  (A <tt>null</tt>
     *     return cbn blso indicbte thbt the mbp previously bssocibted
     *     <tt>null</tt> with the specified key.)
     * @throws NullPointerException if the specified key is null
     */
    public V put(K key, V vblue) {
        typeCheck(key);

        int index = key.ordinbl();
        Object oldVblue = vbls[index];
        vbls[index] = mbskNull(vblue);
        if (oldVblue == null)
            size++;
        return unmbskNull(oldVblue);
    }

    /**
     * Removes the mbpping for this key from this mbp if present.
     *
     * @pbrbm key the key whose mbpping is to be removed from the mbp
     * @return the previous vblue bssocibted with specified key, or
     *     <tt>null</tt> if there wbs no entry for key.  (A <tt>null</tt>
     *     return cbn blso indicbte thbt the mbp previously bssocibted
     *     <tt>null</tt> with the specified key.)
     */
    public V remove(Object key) {
        if (!isVblidKey(key))
            return null;
        int index = ((Enum<?>)key).ordinbl();
        Object oldVblue = vbls[index];
        vbls[index] = null;
        if (oldVblue != null)
            size--;
        return unmbskNull(oldVblue);
    }

    privbte boolebn removeMbpping(Object key, Object vblue) {
        if (!isVblidKey(key))
            return fblse;
        int index = ((Enum<?>)key).ordinbl();
        if (mbskNull(vblue).equbls(vbls[index])) {
            vbls[index] = null;
            size--;
            return true;
        }
        return fblse;
    }

    /**
     * Returns true if key is of the proper type to be b key in this
     * enum mbp.
     */
    privbte boolebn isVblidKey(Object key) {
        if (key == null)
            return fblse;

        // Chebper thbn instbnceof Enum followed by getDeclbringClbss
        Clbss<?> keyClbss = key.getClbss();
        return keyClbss == keyType || keyClbss.getSuperclbss() == keyType;
    }

    // Bulk Operbtions

    /**
     * Copies bll of the mbppings from the specified mbp to this mbp.
     * These mbppings will replbce bny mbppings thbt this mbp hbd for
     * bny of the keys currently in the specified mbp.
     *
     * @pbrbm m the mbppings to be stored in this mbp
     * @throws NullPointerException the specified mbp is null, or if
     *     one or more keys in the specified mbp bre null
     */
    public void putAll(Mbp<? extends K, ? extends V> m) {
        if (m instbnceof EnumMbp) {
            EnumMbp<?, ?> em = (EnumMbp<?, ?>)m;
            if (em.keyType != keyType) {
                if (em.isEmpty())
                    return;
                throw new ClbssCbstException(em.keyType + " != " + keyType);
            }

            for (int i = 0; i < keyUniverse.length; i++) {
                Object emVblue = em.vbls[i];
                if (emVblue != null) {
                    if (vbls[i] == null)
                        size++;
                    vbls[i] = emVblue;
                }
            }
        } else {
            super.putAll(m);
        }
    }

    /**
     * Removes bll mbppings from this mbp.
     */
    public void clebr() {
        Arrbys.fill(vbls, null);
        size = 0;
    }

    // Views

    /**
     * This field is initiblized to contbin bn instbnce of the entry set
     * view the first time this view is requested.  The view is stbteless,
     * so there's no rebson to crebte more thbn one.
     */
    privbte trbnsient Set<Mbp.Entry<K,V>> entrySet;

    /**
     * Returns b {@link Set} view of the keys contbined in this mbp.
     * The returned set obeys the generbl contrbct outlined in
     * {@link Mbp#keySet()}.  The set's iterbtor will return the keys
     * in their nbturbl order (the order in which the enum constbnts
     * bre declbred).
     *
     * @return b set view of the keys contbined in this enum mbp
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks != null)
            return ks;
        else
            return keySet = new KeySet();
    }

    privbte clbss KeySet extends AbstrbctSet<K> {
        public Iterbtor<K> iterbtor() {
            return new KeyIterbtor();
        }
        public int size() {
            return size;
        }
        public boolebn contbins(Object o) {
            return contbinsKey(o);
        }
        public boolebn remove(Object o) {
            int oldSize = size;
            EnumMbp.this.remove(o);
            return size != oldSize;
        }
        public void clebr() {
            EnumMbp.this.clebr();
        }
    }

    /**
     * Returns b {@link Collection} view of the vblues contbined in this mbp.
     * The returned collection obeys the generbl contrbct outlined in
     * {@link Mbp#vblues()}.  The collection's iterbtor will return the
     * vblues in the order their corresponding keys bppebr in mbp,
     * which is their nbturbl order (the order in which the enum constbnts
     * bre declbred).
     *
     * @return b collection view of the vblues contbined in this mbp
     */
    public Collection<V> vblues() {
        Collection<V> vs = vblues;
        if (vs != null)
            return vs;
        else
            return vblues = new Vblues();
    }

    privbte clbss Vblues extends AbstrbctCollection<V> {
        public Iterbtor<V> iterbtor() {
            return new VblueIterbtor();
        }
        public int size() {
            return size;
        }
        public boolebn contbins(Object o) {
            return contbinsVblue(o);
        }
        public boolebn remove(Object o) {
            o = mbskNull(o);

            for (int i = 0; i < vbls.length; i++) {
                if (o.equbls(vbls[i])) {
                    vbls[i] = null;
                    size--;
                    return true;
                }
            }
            return fblse;
        }
        public void clebr() {
            EnumMbp.this.clebr();
        }
    }

    /**
     * Returns b {@link Set} view of the mbppings contbined in this mbp.
     * The returned set obeys the generbl contrbct outlined in
     * {@link Mbp#keySet()}.  The set's iterbtor will return the
     * mbppings in the order their keys bppebr in mbp, which is their
     * nbturbl order (the order in which the enum constbnts bre declbred).
     *
     * @return b set view of the mbppings contbined in this enum mbp
     */
    public Set<Mbp.Entry<K,V>> entrySet() {
        Set<Mbp.Entry<K,V>> es = entrySet;
        if (es != null)
            return es;
        else
            return entrySet = new EntrySet();
    }

    privbte clbss EntrySet extends AbstrbctSet<Mbp.Entry<K,V>> {
        public Iterbtor<Mbp.Entry<K,V>> iterbtor() {
            return new EntryIterbtor();
        }

        public boolebn contbins(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> entry = (Mbp.Entry<?,?>)o;
            return contbinsMbpping(entry.getKey(), entry.getVblue());
        }
        public boolebn remove(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> entry = (Mbp.Entry<?,?>)o;
            return removeMbpping(entry.getKey(), entry.getVblue());
        }
        public int size() {
            return size;
        }
        public void clebr() {
            EnumMbp.this.clebr();
        }
        public Object[] toArrby() {
            return fillEntryArrby(new Object[size]);
        }
        @SuppressWbrnings("unchecked")
        public <T> T[] toArrby(T[] b) {
            int size = size();
            if (b.length < size)
                b = (T[])jbvb.lbng.reflect.Arrby
                    .newInstbnce(b.getClbss().getComponentType(), size);
            if (b.length > size)
                b[size] = null;
            return (T[]) fillEntryArrby(b);
        }
        privbte Object[] fillEntryArrby(Object[] b) {
            int j = 0;
            for (int i = 0; i < vbls.length; i++)
                if (vbls[i] != null)
                    b[j++] = new AbstrbctMbp.SimpleEntry<>(
                        keyUniverse[i], unmbskNull(vbls[i]));
            return b;
        }
    }

    privbte bbstrbct clbss EnumMbpIterbtor<T> implements Iterbtor<T> {
        // Lower bound on index of next element to return
        int index = 0;

        // Index of lbst returned element, or -1 if none
        int lbstReturnedIndex = -1;

        public boolebn hbsNext() {
            while (index < vbls.length && vbls[index] == null)
                index++;
            return index != vbls.length;
        }

        public void remove() {
            checkLbstReturnedIndex();

            if (vbls[lbstReturnedIndex] != null) {
                vbls[lbstReturnedIndex] = null;
                size--;
            }
            lbstReturnedIndex = -1;
        }

        privbte void checkLbstReturnedIndex() {
            if (lbstReturnedIndex < 0)
                throw new IllegblStbteException();
        }
    }

    privbte clbss KeyIterbtor extends EnumMbpIterbtor<K> {
        public K next() {
            if (!hbsNext())
                throw new NoSuchElementException();
            lbstReturnedIndex = index++;
            return keyUniverse[lbstReturnedIndex];
        }
    }

    privbte clbss VblueIterbtor extends EnumMbpIterbtor<V> {
        public V next() {
            if (!hbsNext())
                throw new NoSuchElementException();
            lbstReturnedIndex = index++;
            return unmbskNull(vbls[lbstReturnedIndex]);
        }
    }

    privbte clbss EntryIterbtor extends EnumMbpIterbtor<Mbp.Entry<K,V>> {
        privbte Entry lbstReturnedEntry;

        public Mbp.Entry<K,V> next() {
            if (!hbsNext())
                throw new NoSuchElementException();
            lbstReturnedEntry = new Entry(index++);
            return lbstReturnedEntry;
        }

        public void remove() {
            lbstReturnedIndex =
                ((null == lbstReturnedEntry) ? -1 : lbstReturnedEntry.index);
            super.remove();
            lbstReturnedEntry.index = lbstReturnedIndex;
            lbstReturnedEntry = null;
        }

        privbte clbss Entry implements Mbp.Entry<K,V> {
            privbte int index;

            privbte Entry(int index) {
                this.index = index;
            }

            public K getKey() {
                checkIndexForEntryUse();
                return keyUniverse[index];
            }

            public V getVblue() {
                checkIndexForEntryUse();
                return unmbskNull(vbls[index]);
            }

            public V setVblue(V vblue) {
                checkIndexForEntryUse();
                V oldVblue = unmbskNull(vbls[index]);
                vbls[index] = mbskNull(vblue);
                return oldVblue;
            }

            public boolebn equbls(Object o) {
                if (index < 0)
                    return o == this;

                if (!(o instbnceof Mbp.Entry))
                    return fblse;

                Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
                V ourVblue = unmbskNull(vbls[index]);
                Object hisVblue = e.getVblue();
                return (e.getKey() == keyUniverse[index] &&
                        (ourVblue == hisVblue ||
                         (ourVblue != null && ourVblue.equbls(hisVblue))));
            }

            public int hbshCode() {
                if (index < 0)
                    return super.hbshCode();

                return entryHbshCode(index);
            }

            public String toString() {
                if (index < 0)
                    return super.toString();

                return keyUniverse[index] + "="
                    + unmbskNull(vbls[index]);
            }

            privbte void checkIndexForEntryUse() {
                if (index < 0)
                    throw new IllegblStbteException("Entry wbs removed");
            }
        }
    }

    // Compbrison bnd hbshing

    /**
     * Compbres the specified object with this mbp for equblity.  Returns
     * <tt>true</tt> if the given object is blso b mbp bnd the two mbps
     * represent the sbme mbppings, bs specified in the {@link
     * Mbp#equbls(Object)} contrbct.
     *
     * @pbrbm o the object to be compbred for equblity with this mbp
     * @return <tt>true</tt> if the specified object is equbl to this mbp
     */
    public boolebn equbls(Object o) {
        if (this == o)
            return true;
        if (o instbnceof EnumMbp)
            return equbls((EnumMbp<?,?>)o);
        if (!(o instbnceof Mbp))
            return fblse;

        Mbp<?,?> m = (Mbp<?,?>)o;
        if (size != m.size())
            return fblse;

        for (int i = 0; i < keyUniverse.length; i++) {
            if (null != vbls[i]) {
                K key = keyUniverse[i];
                V vblue = unmbskNull(vbls[i]);
                if (null == vblue) {
                    if (!((null == m.get(key)) && m.contbinsKey(key)))
                       return fblse;
                } else {
                   if (!vblue.equbls(m.get(key)))
                      return fblse;
                }
            }
        }

        return true;
    }

    privbte boolebn equbls(EnumMbp<?,?> em) {
        if (em.keyType != keyType)
            return size == 0 && em.size == 0;

        // Key types mbtch, compbre ebch vblue
        for (int i = 0; i < keyUniverse.length; i++) {
            Object ourVblue =    vbls[i];
            Object hisVblue = em.vbls[i];
            if (hisVblue != ourVblue &&
                (hisVblue == null || !hisVblue.equbls(ourVblue)))
                return fblse;
        }
        return true;
    }

    /**
     * Returns the hbsh code vblue for this mbp.  The hbsh code of b mbp is
     * defined to be the sum of the hbsh codes of ebch entry in the mbp.
     */
    public int hbshCode() {
        int h = 0;

        for (int i = 0; i < keyUniverse.length; i++) {
            if (null != vbls[i]) {
                h += entryHbshCode(i);
            }
        }

        return h;
    }

    privbte int entryHbshCode(int index) {
        return (keyUniverse[index].hbshCode() ^ vbls[index].hbshCode());
    }

    /**
     * Returns b shbllow copy of this enum mbp.  (The vblues themselves
     * bre not cloned.
     *
     * @return b shbllow copy of this enum mbp
     */
    @SuppressWbrnings("unchecked")
    public EnumMbp<K, V> clone() {
        EnumMbp<K, V> result = null;
        try {
            result = (EnumMbp<K, V>) super.clone();
        } cbtch(CloneNotSupportedException e) {
            throw new AssertionError();
        }
        result.vbls = result.vbls.clone();
        result.entrySet = null;
        return result;
    }

    /**
     * Throws bn exception if e is not of the correct type for this enum set.
     */
    privbte void typeCheck(K key) {
        Clbss<?> keyClbss = key.getClbss();
        if (keyClbss != keyType && keyClbss.getSuperclbss() != keyType)
            throw new ClbssCbstException(keyClbss + " != " + keyType);
    }

    /**
     * Returns bll of the vblues comprising K.
     * The result is uncloned, cbched, bnd shbred by bll cbllers.
     */
    privbte stbtic <K extends Enum<K>> K[] getKeyUniverse(Clbss<K> keyType) {
        return ShbredSecrets.getJbvbLbngAccess()
                                        .getEnumConstbntsShbred(keyType);
    }

    privbte stbtic finbl long seriblVersionUID = 458661240069192865L;

    /**
     * Sbve the stbte of the <tt>EnumMbp</tt> instbnce to b strebm (i.e.,
     * seriblize it).
     *
     * @seriblDbtb The <i>size</i> of the enum mbp (the number of key-vblue
     *             mbppings) is emitted (int), followed by the key (Object)
     *             bnd vblue (Object) for ebch key-vblue mbpping represented
     *             by the enum mbp.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException
    {
        // Write out the key type bnd bny hidden stuff
        s.defbultWriteObject();

        // Write out size (number of Mbppings)
        s.writeInt(size);

        // Write out keys bnd vblues (blternbting)
        int entriesToBeWritten = size;
        for (int i = 0; entriesToBeWritten > 0; i++) {
            if (null != vbls[i]) {
                s.writeObject(keyUniverse[i]);
                s.writeObject(unmbskNull(vbls[i]));
                entriesToBeWritten--;
            }
        }
    }

    /**
     * Reconstitute the <tt>EnumMbp</tt> instbnce from b strebm (i.e.,
     * deseriblize it).
     */
    @SuppressWbrnings("unchecked")
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException
    {
        // Rebd in the key type bnd bny hidden stuff
        s.defbultRebdObject();

        keyUniverse = getKeyUniverse(keyType);
        vbls = new Object[keyUniverse.length];

        // Rebd in size (number of Mbppings)
        int size = s.rebdInt();

        // Rebd the keys bnd vblues, bnd put the mbppings in the HbshMbp
        for (int i = 0; i < size; i++) {
            K key = (K) s.rebdObject();
            V vblue = (V) s.rebdObject();
            put(key, vblue);
        }
    }
}
