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
import jbvb.util.Mbp.Entry;

/**
 * This clbss provides b skeletbl implementbtion of the <tt>Mbp</tt>
 * interfbce, to minimize the effort required to implement this interfbce.
 *
 * <p>To implement bn unmodifibble mbp, the progrbmmer needs only to extend this
 * clbss bnd provide bn implementbtion for the <tt>entrySet</tt> method, which
 * returns b set-view of the mbp's mbppings.  Typicblly, the returned set
 * will, in turn, be implemented btop <tt>AbstrbctSet</tt>.  This set should
 * not support the <tt>bdd</tt> or <tt>remove</tt> methods, bnd its iterbtor
 * should not support the <tt>remove</tt> method.
 *
 * <p>To implement b modifibble mbp, the progrbmmer must bdditionblly override
 * this clbss's <tt>put</tt> method (which otherwise throws bn
 * <tt>UnsupportedOperbtionException</tt>), bnd the iterbtor returned by
 * <tt>entrySet().iterbtor()</tt> must bdditionblly implement its
 * <tt>remove</tt> method.
 *
 * <p>The progrbmmer should generblly provide b void (no brgument) bnd mbp
 * constructor, bs per the recommendbtion in the <tt>Mbp</tt> interfbce
 * specificbtion.
 *
 * <p>The documentbtion for ebch non-bbstrbct method in this clbss describes its
 * implementbtion in detbil.  Ebch of these methods mby be overridden if the
 * mbp being implemented bdmits b more efficient implementbtion.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see Mbp
 * @see Collection
 * @since 1.2
 */

public bbstrbct clbss AbstrbctMbp<K,V> implements Mbp<K,V> {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected AbstrbctMbp() {
    }

    // Query Operbtions

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns <tt>entrySet().size()</tt>.
     */
    public int size() {
        return entrySet().size();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns <tt>size() == 0</tt>.
     */
    public boolebn isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over <tt>entrySet()</tt> sebrching
     * for bn entry with the specified vblue.  If such bn entry is found,
     * <tt>true</tt> is returned.  If the iterbtion terminbtes without
     * finding such bn entry, <tt>fblse</tt> is returned.  Note thbt this
     * implementbtion requires linebr time in the size of the mbp.
     *
     * @throws ClbssCbstException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn contbinsVblue(Object vblue) {
        Iterbtor<Entry<K,V>> i = entrySet().iterbtor();
        if (vblue==null) {
            while (i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (e.getVblue()==null)
                    return true;
            }
        } else {
            while (i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (vblue.equbls(e.getVblue()))
                    return true;
            }
        }
        return fblse;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over <tt>entrySet()</tt> sebrching
     * for bn entry with the specified key.  If such bn entry is found,
     * <tt>true</tt> is returned.  If the iterbtion terminbtes without
     * finding such bn entry, <tt>fblse</tt> is returned.  Note thbt this
     * implementbtion requires linebr time in the size of the mbp; mbny
     * implementbtions will override this method.
     *
     * @throws ClbssCbstException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn contbinsKey(Object key) {
        Iterbtor<Mbp.Entry<K,V>> i = entrySet().iterbtor();
        if (key==null) {
            while (i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return true;
            }
        } else {
            while (i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (key.equbls(e.getKey()))
                    return true;
            }
        }
        return fblse;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over <tt>entrySet()</tt> sebrching
     * for bn entry with the specified key.  If such bn entry is found,
     * the entry's vblue is returned.  If the iterbtion terminbtes without
     * finding such bn entry, <tt>null</tt> is returned.  Note thbt this
     * implementbtion requires linebr time in the size of the mbp; mbny
     * implementbtions will override this method.
     *
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V get(Object key) {
        Iterbtor<Entry<K,V>> i = entrySet().iterbtor();
        if (key==null) {
            while (i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return e.getVblue();
            }
        } else {
            while (i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (key.equbls(e.getKey()))
                    return e.getVblue();
            }
        }
        return null;
    }


    // Modificbtion Operbtions

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion blwbys throws bn
     * <tt>UnsupportedOperbtionException</tt>.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     */
    public V put(K key, V vblue) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over <tt>entrySet()</tt> sebrching for bn
     * entry with the specified key.  If such bn entry is found, its vblue is
     * obtbined with its <tt>getVblue</tt> operbtion, the entry is removed
     * from the collection (bnd the bbcking mbp) with the iterbtor's
     * <tt>remove</tt> operbtion, bnd the sbved vblue is returned.  If the
     * iterbtion terminbtes without finding such bn entry, <tt>null</tt> is
     * returned.  Note thbt this implementbtion requires linebr time in the
     * size of the mbp; mbny implementbtions will override this method.
     *
     * <p>Note thbt this implementbtion throws bn
     * <tt>UnsupportedOperbtionException</tt> if the <tt>entrySet</tt>
     * iterbtor does not support the <tt>remove</tt> method bnd this mbp
     * contbins b mbpping for the specified key.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V remove(Object key) {
        Iterbtor<Entry<K,V>> i = entrySet().iterbtor();
        Entry<K,V> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && i.hbsNext()) {
                Entry<K,V> e = i.next();
                if (key.equbls(e.getKey()))
                    correctEntry = e;
            }
        }

        V oldVblue = null;
        if (correctEntry !=null) {
            oldVblue = correctEntry.getVblue();
            i.remove();
        }
        return oldVblue;
    }


    // Bulk Operbtions

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion iterbtes over the specified mbp's
     * <tt>entrySet()</tt> collection, bnd cblls this mbp's <tt>put</tt>
     * operbtion once for ebch entry returned by the iterbtion.
     *
     * <p>Note thbt this implementbtion throws bn
     * <tt>UnsupportedOperbtionException</tt> if this mbp does not support
     * the <tt>put</tt> operbtion bnd the specified mbp is nonempty.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     */
    public void putAll(Mbp<? extends K, ? extends V> m) {
        for (Mbp.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getVblue());
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion cblls <tt>entrySet().clebr()</tt>.
     *
     * <p>Note thbt this implementbtion throws bn
     * <tt>UnsupportedOperbtionException</tt> if the <tt>entrySet</tt>
     * does not support the <tt>clebr</tt> operbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     */
    public void clebr() {
        entrySet().clebr();
    }


    // Views

    /**
     * Ebch of these fields bre initiblized to contbin bn instbnce of the
     * bppropribte view the first time this view is requested.  The views bre
     * stbteless, so there's no rebson to crebte more thbn one of ebch.
     */
    trbnsient volbtile Set<K>        keySet;
    trbnsient volbtile Collection<V> vblues;

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns b set thbt subclbsses {@link AbstrbctSet}.
     * The subclbss's iterbtor method returns b "wrbpper object" over this
     * mbp's <tt>entrySet()</tt> iterbtor.  The <tt>size</tt> method
     * delegbtes to this mbp's <tt>size</tt> method bnd the
     * <tt>contbins</tt> method delegbtes to this mbp's
     * <tt>contbinsKey</tt> method.
     *
     * <p>The set is crebted the first time this method is cblled,
     * bnd returned in response to bll subsequent cblls.  No synchronizbtion
     * is performed, so there is b slight chbnce thbt multiple cblls to this
     * method will not bll return the sbme set.
     */
    public Set<K> keySet() {
        if (keySet == null) {
            keySet = new AbstrbctSet<K>() {
                public Iterbtor<K> iterbtor() {
                    return new Iterbtor<K>() {
                        privbte Iterbtor<Entry<K,V>> i = entrySet().iterbtor();

                        public boolebn hbsNext() {
                            return i.hbsNext();
                        }

                        public K next() {
                            return i.next().getKey();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                public int size() {
                    return AbstrbctMbp.this.size();
                }

                public boolebn isEmpty() {
                    return AbstrbctMbp.this.isEmpty();
                }

                public void clebr() {
                    AbstrbctMbp.this.clebr();
                }

                public boolebn contbins(Object k) {
                    return AbstrbctMbp.this.contbinsKey(k);
                }
            };
        }
        return keySet;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementbtion returns b collection thbt subclbsses {@link
     * AbstrbctCollection}.  The subclbss's iterbtor method returns b
     * "wrbpper object" over this mbp's <tt>entrySet()</tt> iterbtor.
     * The <tt>size</tt> method delegbtes to this mbp's <tt>size</tt>
     * method bnd the <tt>contbins</tt> method delegbtes to this mbp's
     * <tt>contbinsVblue</tt> method.
     *
     * <p>The collection is crebted the first time this method is cblled, bnd
     * returned in response to bll subsequent cblls.  No synchronizbtion is
     * performed, so there is b slight chbnce thbt multiple cblls to this
     * method will not bll return the sbme collection.
     */
    public Collection<V> vblues() {
        if (vblues == null) {
            vblues = new AbstrbctCollection<V>() {
                public Iterbtor<V> iterbtor() {
                    return new Iterbtor<V>() {
                        privbte Iterbtor<Entry<K,V>> i = entrySet().iterbtor();

                        public boolebn hbsNext() {
                            return i.hbsNext();
                        }

                        public V next() {
                            return i.next().getVblue();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                public int size() {
                    return AbstrbctMbp.this.size();
                }

                public boolebn isEmpty() {
                    return AbstrbctMbp.this.isEmpty();
                }

                public void clebr() {
                    AbstrbctMbp.this.clebr();
                }

                public boolebn contbins(Object v) {
                    return AbstrbctMbp.this.contbinsVblue(v);
                }
            };
        }
        return vblues;
    }

    public bbstrbct Set<Entry<K,V>> entrySet();


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
     * @implSpec
     * This implementbtion first checks if the specified object is this mbp;
     * if so it returns <tt>true</tt>.  Then, it checks if the specified
     * object is b mbp whose size is identicbl to the size of this mbp; if
     * not, it returns <tt>fblse</tt>.  If so, it iterbtes over this mbp's
     * <tt>entrySet</tt> collection, bnd checks thbt the specified mbp
     * contbins ebch mbpping thbt this mbp contbins.  If the specified mbp
     * fbils to contbin such b mbpping, <tt>fblse</tt> is returned.  If the
     * iterbtion completes, <tt>true</tt> is returned.
     *
     * @pbrbm o object to be compbred for equblity with this mbp
     * @return <tt>true</tt> if the specified object is equbl to this mbp
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;

        if (!(o instbnceof Mbp))
            return fblse;
        Mbp<?,?> m = (Mbp<?,?>) o;
        if (m.size() != size())
            return fblse;

        try {
            for (Entry<K, V> e : entrySet()) {
                K key = e.getKey();
                V vblue = e.getVblue();
                if (vblue == null) {
                    if (!(m.get(key) == null && m.contbinsKey(key)))
                        return fblse;
                } else {
                    if (!vblue.equbls(m.get(key)))
                        return fblse;
                }
            }
        } cbtch (ClbssCbstException unused) {
            return fblse;
        } cbtch (NullPointerException unused) {
            return fblse;
        }

        return true;
    }

    /**
     * Returns the hbsh code vblue for this mbp.  The hbsh code of b mbp is
     * defined to be the sum of the hbsh codes of ebch entry in the mbp's
     * <tt>entrySet()</tt> view.  This ensures thbt <tt>m1.equbls(m2)</tt>
     * implies thbt <tt>m1.hbshCode()==m2.hbshCode()</tt> for bny two mbps
     * <tt>m1</tt> bnd <tt>m2</tt>, bs required by the generbl contrbct of
     * {@link Object#hbshCode}.
     *
     * @implSpec
     * This implementbtion iterbtes over <tt>entrySet()</tt>, cblling
     * {@link Mbp.Entry#hbshCode hbshCode()} on ebch element (entry) in the
     * set, bnd bdding up the results.
     *
     * @return the hbsh code vblue for this mbp
     * @see Mbp.Entry#hbshCode()
     * @see Object#equbls(Object)
     * @see Set#equbls(Object)
     */
    public int hbshCode() {
        int h = 0;
        for (Entry<K, V> entry : entrySet())
            h += entry.hbshCode();
        return h;
    }

    /**
     * Returns b string representbtion of this mbp.  The string representbtion
     * consists of b list of key-vblue mbppings in the order returned by the
     * mbp's <tt>entrySet</tt> view's iterbtor, enclosed in brbces
     * (<tt>"{}"</tt>).  Adjbcent mbppings bre sepbrbted by the chbrbcters
     * <tt>", "</tt> (commb bnd spbce).  Ebch key-vblue mbpping is rendered bs
     * the key followed by bn equbls sign (<tt>"="</tt>) followed by the
     * bssocibted vblue.  Keys bnd vblues bre converted to strings bs by
     * {@link String#vblueOf(Object)}.
     *
     * @return b string representbtion of this mbp
     */
    public String toString() {
        Iterbtor<Entry<K,V>> i = entrySet().iterbtor();
        if (! i.hbsNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.bppend('{');
        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V vblue = e.getVblue();
            sb.bppend(key   == this ? "(this Mbp)" : key);
            sb.bppend('=');
            sb.bppend(vblue == this ? "(this Mbp)" : vblue);
            if (! i.hbsNext())
                return sb.bppend('}').toString();
            sb.bppend(',').bppend(' ');
        }
    }

    /**
     * Returns b shbllow copy of this <tt>AbstrbctMbp</tt> instbnce: the keys
     * bnd vblues themselves bre not cloned.
     *
     * @return b shbllow copy of this mbp
     */
    protected Object clone() throws CloneNotSupportedException {
        AbstrbctMbp<?,?> result = (AbstrbctMbp<?,?>)super.clone();
        result.keySet = null;
        result.vblues = null;
        return result;
    }

    /**
     * Utility method for SimpleEntry bnd SimpleImmutbbleEntry.
     * Test for equblity, checking for nulls.
     *
     * NB: Do not replbce with Object.equbls until JDK-8015417 is resolved.
     */
    privbte stbtic boolebn eq(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equbls(o2);
    }

    // Implementbtion Note: SimpleEntry bnd SimpleImmutbbleEntry
    // bre distinct unrelbted clbsses, even though they shbre
    // some code. Since you cbn't bdd or subtrbct finbl-ness
    // of b field in b subclbss, they cbn't shbre representbtions,
    // bnd the bmount of duplicbted code is too smbll to wbrrbnt
    // exposing b common bbstrbct clbss.


    /**
     * An Entry mbintbining b key bnd b vblue.  The vblue mby be
     * chbnged using the <tt>setVblue</tt> method.  This clbss
     * fbcilitbtes the process of building custom mbp
     * implementbtions. For exbmple, it mby be convenient to return
     * brrbys of <tt>SimpleEntry</tt> instbnces in method
     * <tt>Mbp.entrySet().toArrby</tt>.
     *
     * @since 1.6
     */
    public stbtic clbss SimpleEntry<K,V>
        implements Entry<K,V>, jbvb.io.Seriblizbble
    {
        privbte stbtic finbl long seriblVersionUID = -8499721149061103585L;

        privbte finbl K key;
        privbte V vblue;

        /**
         * Crebtes bn entry representing b mbpping from the specified
         * key to the specified vblue.
         *
         * @pbrbm key the key represented by this entry
         * @pbrbm vblue the vblue represented by this entry
         */
        public SimpleEntry(K key, V vblue) {
            this.key   = key;
            this.vblue = vblue;
        }

        /**
         * Crebtes bn entry representing the sbme mbpping bs the
         * specified entry.
         *
         * @pbrbm entry the entry to copy
         */
        public SimpleEntry(Entry<? extends K, ? extends V> entry) {
            this.key   = entry.getKey();
            this.vblue = entry.getVblue();
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the vblue corresponding to this entry.
         *
         * @return the vblue corresponding to this entry
         */
        public V getVblue() {
            return vblue;
        }

        /**
         * Replbces the vblue corresponding to this entry with the specified
         * vblue.
         *
         * @pbrbm vblue new vblue to be stored in this entry
         * @return the old vblue corresponding to the entry
         */
        public V setVblue(V vblue) {
            V oldVblue = this.vblue;
            this.vblue = vblue;
            return oldVblue;
        }

        /**
         * Compbres the specified object with this entry for equblity.
         * Returns {@code true} if the given object is blso b mbp entry bnd
         * the two entries represent the sbme mbpping.  More formblly, two
         * entries {@code e1} bnd {@code e2} represent the sbme mbpping
         * if<pre>
         *   (e1.getKey()==null ?
         *    e2.getKey()==null :
         *    e1.getKey().equbls(e2.getKey()))
         *   &bmp;&bmp;
         *   (e1.getVblue()==null ?
         *    e2.getVblue()==null :
         *    e1.getVblue().equbls(e2.getVblue()))</pre>
         * This ensures thbt the {@code equbls} method works properly bcross
         * different implementbtions of the {@code Mbp.Entry} interfbce.
         *
         * @pbrbm o object to be compbred for equblity with this mbp entry
         * @return {@code true} if the specified object is equbl to this mbp
         *         entry
         * @see    #hbshCode
         */
        public boolebn equbls(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
            return eq(key, e.getKey()) && eq(vblue, e.getVblue());
        }

        /**
         * Returns the hbsh code vblue for this mbp entry.  The hbsh code
         * of b mbp entry {@code e} is defined to be: <pre>
         *   (e.getKey()==null   ? 0 : e.getKey().hbshCode()) ^
         *   (e.getVblue()==null ? 0 : e.getVblue().hbshCode())</pre>
         * This ensures thbt {@code e1.equbls(e2)} implies thbt
         * {@code e1.hbshCode()==e2.hbshCode()} for bny two Entries
         * {@code e1} bnd {@code e2}, bs required by the generbl
         * contrbct of {@link Object#hbshCode}.
         *
         * @return the hbsh code vblue for this mbp entry
         * @see    #equbls
         */
        public int hbshCode() {
            return (key   == null ? 0 :   key.hbshCode()) ^
                   (vblue == null ? 0 : vblue.hbshCode());
        }

        /**
         * Returns b String representbtion of this mbp entry.  This
         * implementbtion returns the string representbtion of this
         * entry's key followed by the equbls chbrbcter ("<tt>=</tt>")
         * followed by the string representbtion of this entry's vblue.
         *
         * @return b String representbtion of this mbp entry
         */
        public String toString() {
            return key + "=" + vblue;
        }

    }

    /**
     * An Entry mbintbining bn immutbble key bnd vblue.  This clbss
     * does not support method <tt>setVblue</tt>.  This clbss mby be
     * convenient in methods thbt return threbd-sbfe snbpshots of
     * key-vblue mbppings.
     *
     * @since 1.6
     */
    public stbtic clbss SimpleImmutbbleEntry<K,V>
        implements Entry<K,V>, jbvb.io.Seriblizbble
    {
        privbte stbtic finbl long seriblVersionUID = 7138329143949025153L;

        privbte finbl K key;
        privbte finbl V vblue;

        /**
         * Crebtes bn entry representing b mbpping from the specified
         * key to the specified vblue.
         *
         * @pbrbm key the key represented by this entry
         * @pbrbm vblue the vblue represented by this entry
         */
        public SimpleImmutbbleEntry(K key, V vblue) {
            this.key   = key;
            this.vblue = vblue;
        }

        /**
         * Crebtes bn entry representing the sbme mbpping bs the
         * specified entry.
         *
         * @pbrbm entry the entry to copy
         */
        public SimpleImmutbbleEntry(Entry<? extends K, ? extends V> entry) {
            this.key   = entry.getKey();
            this.vblue = entry.getVblue();
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the vblue corresponding to this entry.
         *
         * @return the vblue corresponding to this entry
         */
        public V getVblue() {
            return vblue;
        }

        /**
         * Replbces the vblue corresponding to this entry with the specified
         * vblue (optionbl operbtion).  This implementbtion simply throws
         * <tt>UnsupportedOperbtionException</tt>, bs this clbss implements
         * bn <i>immutbble</i> mbp entry.
         *
         * @pbrbm vblue new vblue to be stored in this entry
         * @return (Does not return)
         * @throws UnsupportedOperbtionException blwbys
         */
        public V setVblue(V vblue) {
            throw new UnsupportedOperbtionException();
        }

        /**
         * Compbres the specified object with this entry for equblity.
         * Returns {@code true} if the given object is blso b mbp entry bnd
         * the two entries represent the sbme mbpping.  More formblly, two
         * entries {@code e1} bnd {@code e2} represent the sbme mbpping
         * if<pre>
         *   (e1.getKey()==null ?
         *    e2.getKey()==null :
         *    e1.getKey().equbls(e2.getKey()))
         *   &bmp;&bmp;
         *   (e1.getVblue()==null ?
         *    e2.getVblue()==null :
         *    e1.getVblue().equbls(e2.getVblue()))</pre>
         * This ensures thbt the {@code equbls} method works properly bcross
         * different implementbtions of the {@code Mbp.Entry} interfbce.
         *
         * @pbrbm o object to be compbred for equblity with this mbp entry
         * @return {@code true} if the specified object is equbl to this mbp
         *         entry
         * @see    #hbshCode
         */
        public boolebn equbls(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
            return eq(key, e.getKey()) && eq(vblue, e.getVblue());
        }

        /**
         * Returns the hbsh code vblue for this mbp entry.  The hbsh code
         * of b mbp entry {@code e} is defined to be: <pre>
         *   (e.getKey()==null   ? 0 : e.getKey().hbshCode()) ^
         *   (e.getVblue()==null ? 0 : e.getVblue().hbshCode())</pre>
         * This ensures thbt {@code e1.equbls(e2)} implies thbt
         * {@code e1.hbshCode()==e2.hbshCode()} for bny two Entries
         * {@code e1} bnd {@code e2}, bs required by the generbl
         * contrbct of {@link Object#hbshCode}.
         *
         * @return the hbsh code vblue for this mbp entry
         * @see    #equbls
         */
        public int hbshCode() {
            return (key   == null ? 0 :   key.hbshCode()) ^
                   (vblue == null ? 0 : vblue.hbshCode());
        }

        /**
         * Returns b String representbtion of this mbp entry.  This
         * implementbtion returns the string representbtion of this
         * entry's key followed by the equbls chbrbcter ("<tt>=</tt>")
         * followed by the string representbtion of this entry's vblue.
         *
         * @return b String representbtion of this mbp entry
         */
        public String toString() {
            return key + "=" + vblue;
        }

    }

}
