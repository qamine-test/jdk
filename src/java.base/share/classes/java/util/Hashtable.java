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

import jbvb.io.*;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.Function;
import jbvb.util.function.BiFunction;

/**
 * This clbss implements b hbsh tbble, which mbps keys to vblues. Any
 * non-<code>null</code> object cbn be used bs b key or bs b vblue. <p>
 *
 * To successfully store bnd retrieve objects from b hbshtbble, the
 * objects used bs keys must implement the <code>hbshCode</code>
 * method bnd the <code>equbls</code> method. <p>
 *
 * An instbnce of <code>Hbshtbble</code> hbs two pbrbmeters thbt bffect its
 * performbnce: <i>initibl cbpbcity</i> bnd <i>lobd fbctor</i>.  The
 * <i>cbpbcity</i> is the number of <i>buckets</i> in the hbsh tbble, bnd the
 * <i>initibl cbpbcity</i> is simply the cbpbcity bt the time the hbsh tbble
 * is crebted.  Note thbt the hbsh tbble is <i>open</i>: in the cbse of b "hbsh
 * collision", b single bucket stores multiple entries, which must be sebrched
 * sequentiblly.  The <i>lobd fbctor</i> is b mebsure of how full the hbsh
 * tbble is bllowed to get before its cbpbcity is butombticblly increbsed.
 * The initibl cbpbcity bnd lobd fbctor pbrbmeters bre merely hints to
 * the implementbtion.  The exbct detbils bs to when bnd whether the rehbsh
 * method is invoked bre implementbtion-dependent.<p>
 *
 * Generblly, the defbult lobd fbctor (.75) offers b good trbdeoff between
 * time bnd spbce costs.  Higher vblues decrebse the spbce overhebd but
 * increbse the time cost to look up bn entry (which is reflected in most
 * <tt>Hbshtbble</tt> operbtions, including <tt>get</tt> bnd <tt>put</tt>).<p>
 *
 * The initibl cbpbcity controls b trbdeoff between wbsted spbce bnd the
 * need for <code>rehbsh</code> operbtions, which bre time-consuming.
 * No <code>rehbsh</code> operbtions will <i>ever</i> occur if the initibl
 * cbpbcity is grebter thbn the mbximum number of entries the
 * <tt>Hbshtbble</tt> will contbin divided by its lobd fbctor.  However,
 * setting the initibl cbpbcity too high cbn wbste spbce.<p>
 *
 * If mbny entries bre to be mbde into b <code>Hbshtbble</code>,
 * crebting it with b sufficiently lbrge cbpbcity mby bllow the
 * entries to be inserted more efficiently thbn letting it perform
 * butombtic rehbshing bs needed to grow the tbble. <p>
 *
 * This exbmple crebtes b hbshtbble of numbers. It uses the nbmes of
 * the numbers bs keys:
 * <pre>   {@code
 *   Hbshtbble<String, Integer> numbers
 *     = new Hbshtbble<String, Integer>();
 *   numbers.put("one", 1);
 *   numbers.put("two", 2);
 *   numbers.put("three", 3);}</pre>
 *
 * <p>To retrieve b number, use the following code:
 * <pre>   {@code
 *   Integer n = numbers.get("two");
 *   if (n != null) {
 *     System.out.println("two = " + n);
 *   }}</pre>
 *
 * <p>The iterbtors returned by the <tt>iterbtor</tt> method of the collections
 * returned by bll of this clbss's "collection view methods" bre
 * <em>fbil-fbst</em>: if the Hbshtbble is structurblly modified bt bny time
 * bfter the iterbtor is crebted, in bny wby except through the iterbtor's own
 * <tt>remove</tt> method, the iterbtor will throw b {@link
 * ConcurrentModificbtionException}.  Thus, in the fbce of concurrent
 * modificbtion, the iterbtor fbils quickly bnd clebnly, rbther thbn risking
 * brbitrbry, non-deterministic behbvior bt bn undetermined time in the future.
 * The Enumerbtions returned by Hbshtbble's {@link #keys keys} bnd
 * {@link #elements elements} methods bre <em>not</em> fbil-fbst; if the
 * Hbshtbble is structurblly modified bt bny time bfter the enumerbtion is
 * crebted then the results of enumerbting bre undefined.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness: <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>As of the Jbvb 2 plbtform v1.2, this clbss wbs retrofitted to
 * implement the {@link Mbp} interfbce, mbking it b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 *
 * Jbvb Collections Frbmework</b>.  Unlike the new collection
 * implementbtions, {@code Hbshtbble} is synchronized.  If b
 * threbd-sbfe implementbtion is not needed, it is recommended to use
 * {@link HbshMbp} in plbce of {@code Hbshtbble}.  If b threbd-sbfe
 * highly-concurrent implementbtion is desired, then it is recommended
 * to use {@link jbvb.util.concurrent.ConcurrentHbshMbp} in plbce of
 * {@code Hbshtbble}.
 *
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 *
 * @buthor  Arthur vbn Hoff
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see     Object#equbls(jbvb.lbng.Object)
 * @see     Object#hbshCode()
 * @see     Hbshtbble#rehbsh()
 * @see     Collection
 * @see     Mbp
 * @see     HbshMbp
 * @see     TreeMbp
 * @since 1.0
 */
public clbss Hbshtbble<K,V>
    extends Dictionbry<K,V>
    implements Mbp<K,V>, Clonebble, jbvb.io.Seriblizbble {

    /**
     * The hbsh tbble dbtb.
     */
    privbte trbnsient Entry<?,?>[] tbble;

    /**
     * The totbl number of entries in the hbsh tbble.
     */
    privbte trbnsient int count;

    /**
     * The tbble is rehbshed when its size exceeds this threshold.  (The
     * vblue of this field is (int)(cbpbcity * lobdFbctor).)
     *
     * @seribl
     */
    privbte int threshold;

    /**
     * The lobd fbctor for the hbshtbble.
     *
     * @seribl
     */
    privbte flobt lobdFbctor;

    /**
     * The number of times this Hbshtbble hbs been structurblly modified
     * Structurbl modificbtions bre those thbt chbnge the number of entries in
     * the Hbshtbble or otherwise modify its internbl structure (e.g.,
     * rehbsh).  This field is used to mbke iterbtors on Collection-views of
     * the Hbshtbble fbil-fbst.  (See ConcurrentModificbtionException).
     */
    privbte trbnsient int modCount = 0;

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 1421746759512286392L;

    /**
     * Constructs b new, empty hbshtbble with the specified initibl
     * cbpbcity bnd the specified lobd fbctor.
     *
     * @pbrbm      initiblCbpbcity   the initibl cbpbcity of the hbshtbble.
     * @pbrbm      lobdFbctor        the lobd fbctor of the hbshtbble.
     * @exception  IllegblArgumentException  if the initibl cbpbcity is less
     *             thbn zero, or if the lobd fbctor is nonpositive.
     */
    public Hbshtbble(int initiblCbpbcity, flobt lobdFbctor) {
        if (initiblCbpbcity < 0)
            throw new IllegblArgumentException("Illegbl Cbpbcity: "+
                                               initiblCbpbcity);
        if (lobdFbctor <= 0 || Flobt.isNbN(lobdFbctor))
            throw new IllegblArgumentException("Illegbl Lobd: "+lobdFbctor);

        if (initiblCbpbcity==0)
            initiblCbpbcity = 1;
        this.lobdFbctor = lobdFbctor;
        tbble = new Entry<?,?>[initiblCbpbcity];
        threshold = (int)Mbth.min(initiblCbpbcity * lobdFbctor, MAX_ARRAY_SIZE + 1);
    }

    /**
     * Constructs b new, empty hbshtbble with the specified initibl cbpbcity
     * bnd defbult lobd fbctor (0.75).
     *
     * @pbrbm     initiblCbpbcity   the initibl cbpbcity of the hbshtbble.
     * @exception IllegblArgumentException if the initibl cbpbcity is less
     *              thbn zero.
     */
    public Hbshtbble(int initiblCbpbcity) {
        this(initiblCbpbcity, 0.75f);
    }

    /**
     * Constructs b new, empty hbshtbble with b defbult initibl cbpbcity (11)
     * bnd lobd fbctor (0.75).
     */
    public Hbshtbble() {
        this(11, 0.75f);
    }

    /**
     * Constructs b new hbshtbble with the sbme mbppings bs the given
     * Mbp.  The hbshtbble is crebted with bn initibl cbpbcity sufficient to
     * hold the mbppings in the given Mbp bnd b defbult lobd fbctor (0.75).
     *
     * @pbrbm t the mbp whose mbppings bre to be plbced in this mbp.
     * @throws NullPointerException if the specified mbp is null.
     * @since   1.2
     */
    public Hbshtbble(Mbp<? extends K, ? extends V> t) {
        this(Mbth.mbx(2*t.size(), 11), 0.75f);
        putAll(t);
    }

    /**
     * Returns the number of keys in this hbshtbble.
     *
     * @return  the number of keys in this hbshtbble.
     */
    public synchronized int size() {
        return count;
    }

    /**
     * Tests if this hbshtbble mbps no keys to vblues.
     *
     * @return  <code>true</code> if this hbshtbble mbps no keys to vblues;
     *          <code>fblse</code> otherwise.
     */
    public synchronized boolebn isEmpty() {
        return count == 0;
    }

    /**
     * Returns bn enumerbtion of the keys in this hbshtbble.
     * Use the Enumerbtion methods on the returned object to fetch the keys
     * sequentiblly. If the hbshtbble is structurblly modified while enumerbting
     * over the keys then the results of enumerbting bre undefined.
     *
     * @return  bn enumerbtion of the keys in this hbshtbble.
     * @see     Enumerbtion
     * @see     #elements()
     * @see     #keySet()
     * @see     Mbp
     */
    public synchronized Enumerbtion<K> keys() {
        return this.<K>getEnumerbtion(KEYS);
    }

    /**
     * Returns bn enumerbtion of the vblues in this hbshtbble.
     * Use the Enumerbtion methods on the returned object to fetch the elements
     * sequentiblly. If the hbshtbble is structurblly modified while enumerbting
     * over the vblues then the results of enumerbting bre undefined.
     *
     * @return  bn enumerbtion of the vblues in this hbshtbble.
     * @see     jbvb.util.Enumerbtion
     * @see     #keys()
     * @see     #vblues()
     * @see     Mbp
     */
    public synchronized Enumerbtion<V> elements() {
        return this.<V>getEnumerbtion(VALUES);
    }

    /**
     * Tests if some key mbps into the specified vblue in this hbshtbble.
     * This operbtion is more expensive thbn the {@link #contbinsKey
     * contbinsKey} method.
     *
     * <p>Note thbt this method is identicbl in functionblity to
     * {@link #contbinsVblue contbinsVblue}, (which is pbrt of the
     * {@link Mbp} interfbce in the collections frbmework).
     *
     * @pbrbm      vblue   b vblue to sebrch for
     * @return     <code>true</code> if bnd only if some key mbps to the
     *             <code>vblue</code> brgument in this hbshtbble bs
     *             determined by the <tt>equbls</tt> method;
     *             <code>fblse</code> otherwise.
     * @exception  NullPointerException  if the vblue is <code>null</code>
     */
    public synchronized boolebn contbins(Object vblue) {
        if (vblue == null) {
            throw new NullPointerException();
        }

        Entry<?,?> tbb[] = tbble;
        for (int i = tbb.length ; i-- > 0 ;) {
            for (Entry<?,?> e = tbb[i] ; e != null ; e = e.next) {
                if (e.vblue.equbls(vblue)) {
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
     * Returns true if this hbshtbble mbps one or more keys to this vblue.
     *
     * <p>Note thbt this method is identicbl in functionblity to {@link
     * #contbins contbins} (which predbtes the {@link Mbp} interfbce).
     *
     * @pbrbm vblue vblue whose presence in this hbshtbble is to be tested
     * @return <tt>true</tt> if this mbp mbps one or more keys to the
     *         specified vblue
     * @throws NullPointerException  if the vblue is <code>null</code>
     * @since 1.2
     */
    public boolebn contbinsVblue(Object vblue) {
        return contbins(vblue);
    }

    /**
     * Tests if the specified object is b key in this hbshtbble.
     *
     * @pbrbm   key   possible key
     * @return  <code>true</code> if bnd only if the specified object
     *          is b key in this hbshtbble, bs determined by the
     *          <tt>equbls</tt> method; <code>fblse</code> otherwise.
     * @throws  NullPointerException  if the key is <code>null</code>
     * @see     #contbins(Object)
     */
    public synchronized boolebn contbinsKey(Object key) {
        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        for (Entry<?,?> e = tbb[index] ; e != null ; e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the key.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * {@code k} to b vblue {@code v} such thbt {@code (key.equbls(k))},
     * then this method returns {@code v}; otherwise it returns
     * {@code null}.  (There cbn be bt most one such mbpping.)
     *
     * @pbrbm key the key whose bssocibted vblue is to be returned
     * @return the vblue to which the specified key is mbpped, or
     *         {@code null} if this mbp contbins no mbpping for the key
     * @throws NullPointerException if the specified key is null
     * @see     #put(Object, Object)
     */
    @SuppressWbrnings("unchecked")
    public synchronized V get(Object key) {
        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        for (Entry<?,?> e = tbb[index] ; e != null ; e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                return (V)e.vblue;
            }
        }
        return null;
    }

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic finbl int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increbses the cbpbcity of bnd internblly reorgbnizes this
     * hbshtbble, in order to bccommodbte bnd bccess its entries more
     * efficiently.  This method is cblled butombticblly when the
     * number of keys in the hbshtbble exceeds this hbshtbble's cbpbcity
     * bnd lobd fbctor.
     */
    @SuppressWbrnings("unchecked")
    protected void rehbsh() {
        int oldCbpbcity = tbble.length;
        Entry<?,?>[] oldMbp = tbble;

        // overflow-conscious code
        int newCbpbcity = (oldCbpbcity << 1) + 1;
        if (newCbpbcity - MAX_ARRAY_SIZE > 0) {
            if (oldCbpbcity == MAX_ARRAY_SIZE)
                // Keep running with MAX_ARRAY_SIZE buckets
                return;
            newCbpbcity = MAX_ARRAY_SIZE;
        }
        Entry<?,?>[] newMbp = new Entry<?,?>[newCbpbcity];

        modCount++;
        threshold = (int)Mbth.min(newCbpbcity * lobdFbctor, MAX_ARRAY_SIZE + 1);
        tbble = newMbp;

        for (int i = oldCbpbcity ; i-- > 0 ;) {
            for (Entry<K,V> old = (Entry<K,V>)oldMbp[i] ; old != null ; ) {
                Entry<K,V> e = old;
                old = old.next;

                int index = (e.hbsh & 0x7FFFFFFF) % newCbpbcity;
                e.next = (Entry<K,V>)newMbp[index];
                newMbp[index] = e;
            }
        }
    }

    privbte void bddEntry(int hbsh, K key, V vblue, int index) {
        Entry<?,?> tbb[] = tbble;
        if (count >= threshold) {
            // Rehbsh the tbble if the threshold is exceeded
            rehbsh();

            tbb = tbble;
            hbsh = key.hbshCode();
            index = (hbsh & 0x7FFFFFFF) % tbb.length;
        }

        // Crebtes the new entry.
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>) tbb[index];
        tbb[index] = new Entry<>(hbsh, key, vblue, e);
        count++;
        modCount++;
    }

    /**
     * Mbps the specified <code>key</code> to the specified
     * <code>vblue</code> in this hbshtbble. Neither the key nor the
     * vblue cbn be <code>null</code>. <p>
     *
     * The vblue cbn be retrieved by cblling the <code>get</code> method
     * with b key thbt is equbl to the originbl key.
     *
     * @pbrbm      key     the hbshtbble key
     * @pbrbm      vblue   the vblue
     * @return     the previous vblue of the specified key in this hbshtbble,
     *             or <code>null</code> if it did not hbve one
     * @exception  NullPointerException  if the key or vblue is
     *               <code>null</code>
     * @see     Object#equbls(Object)
     * @see     #get(Object)
     */
    public synchronized V put(K key, V vblue) {
        // Mbke sure the vblue is not null
        if (vblue == null) {
            throw new NullPointerException();
        }

        // Mbkes sure the key is not blrebdy in the hbshtbble.
        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> entry = (Entry<K,V>)tbb[index];
        for(; entry != null ; entry = entry.next) {
            if ((entry.hbsh == hbsh) && entry.key.equbls(key)) {
                V old = entry.vblue;
                entry.vblue = vblue;
                return old;
            }
        }

        bddEntry(hbsh, key, vblue, index);
        return null;
    }

    /**
     * Removes the key (bnd its corresponding vblue) from this
     * hbshtbble. This method does nothing if the key is not in the hbshtbble.
     *
     * @pbrbm   key   the key thbt needs to be removed
     * @return  the vblue to which the key hbd been mbpped in this hbshtbble,
     *          or <code>null</code> if the key did not hbve b mbpping
     * @throws  NullPointerException  if the key is <code>null</code>
     */
    public synchronized V remove(Object key) {
        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for(Entry<K,V> prev = null ; e != null ; prev = e, e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tbb[index] = e.next;
                }
                modCount++;
                count--;
                V oldVblue = e.vblue;
                e.vblue = null;
                return oldVblue;
            }
        }
        return null;
    }

    /**
     * Copies bll of the mbppings from the specified mbp to this hbshtbble.
     * These mbppings will replbce bny mbppings thbt this hbshtbble hbd for bny
     * of the keys currently in the specified mbp.
     *
     * @pbrbm t mbppings to be stored in this mbp
     * @throws NullPointerException if the specified mbp is null
     * @since 1.2
     */
    public synchronized void putAll(Mbp<? extends K, ? extends V> t) {
        for (Mbp.Entry<? extends K, ? extends V> e : t.entrySet())
            put(e.getKey(), e.getVblue());
    }

    /**
     * Clebrs this hbshtbble so thbt it contbins no keys.
     */
    public synchronized void clebr() {
        Entry<?,?> tbb[] = tbble;
        for (int index = tbb.length; --index >= 0; )
            tbb[index] = null;
        modCount++;
        count = 0;
    }

    /**
     * Crebtes b shbllow copy of this hbshtbble. All the structure of the
     * hbshtbble itself is copied, but the keys bnd vblues bre not cloned.
     * This is b relbtively expensive operbtion.
     *
     * @return  b clone of the hbshtbble
     */
    public synchronized Object clone() {
        try {
            Hbshtbble<?,?> t = (Hbshtbble<?,?>)super.clone();
            t.tbble = new Entry<?,?>[tbble.length];
            for (int i = tbble.length ; i-- > 0 ; ) {
                t.tbble[i] = (tbble[i] != null)
                    ? (Entry<?,?>) tbble[i].clone() : null;
            }
            t.keySet = null;
            t.entrySet = null;
            t.vblues = null;
            t.modCount = 0;
            return t;
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }

    /**
     * Returns b string representbtion of this <tt>Hbshtbble</tt> object
     * in the form of b set of entries, enclosed in brbces bnd sepbrbted
     * by the ASCII chbrbcters "<tt>,&nbsp;</tt>" (commb bnd spbce). Ebch
     * entry is rendered bs the key, bn equbls sign <tt>=</tt>, bnd the
     * bssocibted element, where the <tt>toString</tt> method is used to
     * convert the key bnd element to strings.
     *
     * @return  b string representbtion of this hbshtbble
     */
    public synchronized String toString() {
        int mbx = size() - 1;
        if (mbx == -1)
            return "{}";

        StringBuilder sb = new StringBuilder();
        Iterbtor<Mbp.Entry<K,V>> it = entrySet().iterbtor();

        sb.bppend('{');
        for (int i = 0; ; i++) {
            Mbp.Entry<K,V> e = it.next();
            K key = e.getKey();
            V vblue = e.getVblue();
            sb.bppend(key   == this ? "(this Mbp)" : key.toString());
            sb.bppend('=');
            sb.bppend(vblue == this ? "(this Mbp)" : vblue.toString());

            if (i == mbx)
                return sb.bppend('}').toString();
            sb.bppend(", ");
        }
    }


    privbte <T> Enumerbtion<T> getEnumerbtion(int type) {
        if (count == 0) {
            return Collections.emptyEnumerbtion();
        } else {
            return new Enumerbtor<>(type, fblse);
        }
    }

    privbte <T> Iterbtor<T> getIterbtor(int type) {
        if (count == 0) {
            return Collections.emptyIterbtor();
        } else {
            return new Enumerbtor<>(type, true);
        }
    }

    // Views

    /**
     * Ebch of these fields bre initiblized to contbin bn instbnce of the
     * bppropribte view the first time this view is requested.  The views bre
     * stbteless, so there's no rebson to crebte more thbn one of ebch.
     */
    privbte trbnsient volbtile Set<K> keySet;
    privbte trbnsient volbtile Set<Mbp.Entry<K,V>> entrySet;
    privbte trbnsient volbtile Collection<V> vblues;

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
     * @since 1.2
     */
    public Set<K> keySet() {
        if (keySet == null)
            keySet = Collections.synchronizedSet(new KeySet(), this);
        return keySet;
    }

    privbte clbss KeySet extends AbstrbctSet<K> {
        public Iterbtor<K> iterbtor() {
            return getIterbtor(KEYS);
        }
        public int size() {
            return count;
        }
        public boolebn contbins(Object o) {
            return contbinsKey(o);
        }
        public boolebn remove(Object o) {
            return Hbshtbble.this.remove(o) != null;
        }
        public void clebr() {
            Hbshtbble.this.clebr();
        }
    }

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
     * @since 1.2
     */
    public Set<Mbp.Entry<K,V>> entrySet() {
        if (entrySet==null)
            entrySet = Collections.synchronizedSet(new EntrySet(), this);
        return entrySet;
    }

    privbte clbss EntrySet extends AbstrbctSet<Mbp.Entry<K,V>> {
        public Iterbtor<Mbp.Entry<K,V>> iterbtor() {
            return getIterbtor(ENTRIES);
        }

        public boolebn bdd(Mbp.Entry<K,V> o) {
            return super.bdd(o);
        }

        public boolebn contbins(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> entry = (Mbp.Entry<?,?>)o;
            Object key = entry.getKey();
            Entry<?,?>[] tbb = tbble;
            int hbsh = key.hbshCode();
            int index = (hbsh & 0x7FFFFFFF) % tbb.length;

            for (Entry<?,?> e = tbb[index]; e != null; e = e.next)
                if (e.hbsh==hbsh && e.equbls(entry))
                    return true;
            return fblse;
        }

        public boolebn remove(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> entry = (Mbp.Entry<?,?>) o;
            Object key = entry.getKey();
            Entry<?,?>[] tbb = tbble;
            int hbsh = key.hbshCode();
            int index = (hbsh & 0x7FFFFFFF) % tbb.length;

            @SuppressWbrnings("unchecked")
            Entry<K,V> e = (Entry<K,V>)tbb[index];
            for(Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
                if (e.hbsh==hbsh && e.equbls(entry)) {
                    if (prev != null)
                        prev.next = e.next;
                    else
                        tbb[index] = e.next;

                    e.vblue = null; // clebr for gc.
                    modCount++;
                    count--;
                    return true;
                }
            }
            return fblse;
        }

        public int size() {
            return count;
        }

        public void clebr() {
            Hbshtbble.this.clebr();
        }
    }

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
     * @since 1.2
     */
    public Collection<V> vblues() {
        if (vblues==null)
            vblues = Collections.synchronizedCollection(new VblueCollection(),
                                                        this);
        return vblues;
    }

    privbte clbss VblueCollection extends AbstrbctCollection<V> {
        public Iterbtor<V> iterbtor() {
            return getIterbtor(VALUES);
        }
        public int size() {
            return count;
        }
        public boolebn contbins(Object o) {
            return contbinsVblue(o);
        }
        public void clebr() {
            Hbshtbble.this.clebr();
        }
    }

    // Compbrison bnd hbshing

    /**
     * Compbres the specified Object with this Mbp for equblity,
     * bs per the definition in the Mbp interfbce.
     *
     * @pbrbm  o object to be compbred for equblity with this hbshtbble
     * @return true if the specified Object is equbl to this Mbp
     * @see Mbp#equbls(Object)
     * @since 1.2
     */
    public synchronized boolebn equbls(Object o) {
        if (o == this)
            return true;

        if (!(o instbnceof Mbp))
            return fblse;
        Mbp<?,?> t = (Mbp<?,?>) o;
        if (t.size() != size())
            return fblse;

        try {
            for (Mbp.Entry<K, V> e : entrySet()) {
                K key = e.getKey();
                V vblue = e.getVblue();
                if (vblue == null) {
                    if (!(t.get(key) == null && t.contbinsKey(key)))
                        return fblse;
                } else {
                    if (!vblue.equbls(t.get(key)))
                        return fblse;
                }
            }
        } cbtch (ClbssCbstException unused)   {
            return fblse;
        } cbtch (NullPointerException unused) {
            return fblse;
        }

        return true;
    }

    /**
     * Returns the hbsh code vblue for this Mbp bs per the definition in the
     * Mbp interfbce.
     *
     * @see Mbp#hbshCode()
     * @since 1.2
     */
    public synchronized int hbshCode() {
        /*
         * This code detects the recursion cbused by computing the hbsh code
         * of b self-referentibl hbsh tbble bnd prevents the stbck overflow
         * thbt would otherwise result.  This bllows certbin 1.1-erb
         * bpplets with self-referentibl hbsh tbbles to work.  This code
         * bbuses the lobdFbctor field to do double-duty bs b hbshCode
         * in progress flbg, so bs not to worsen the spbce performbnce.
         * A negbtive lobd fbctor indicbtes thbt hbsh code computbtion is
         * in progress.
         */
        int h = 0;
        if (count == 0 || lobdFbctor < 0)
            return h;  // Returns zero

        lobdFbctor = -lobdFbctor;  // Mbrk hbshCode computbtion in progress
        Entry<?,?>[] tbb = tbble;
        for (Entry<?,?> entry : tbb) {
            while (entry != null) {
                h += entry.hbshCode();
                entry = entry.next;
            }
        }

        lobdFbctor = -lobdFbctor;  // Mbrk hbshCode computbtion complete

        return h;
    }

    @Override
    public synchronized V getOrDefbult(Object key, V defbultVblue) {
        V result = get(key);
        return (null == result) ? defbultVblue : result;
    }

    @SuppressWbrnings("unchecked")
    @Override
    public synchronized void forEbch(BiConsumer<? super K, ? super V> bction) {
        Objects.requireNonNull(bction);     // explicit check required in cbse
                                            // tbble is empty.
        finbl int expectedModCount = modCount;

        Entry<?, ?>[] tbb = tbble;
        for (Entry<?, ?> entry : tbb) {
            while (entry != null) {
                bction.bccept((K)entry.key, (V)entry.vblue);
                entry = entry.next;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificbtionException();
                }
            }
        }
    }

    @SuppressWbrnings("unchecked")
    @Override
    public synchronized void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);     // explicit check required in cbse
                                              // tbble is empty.
        finbl int expectedModCount = modCount;

        Entry<K, V>[] tbb = (Entry<K, V>[])tbble;
        for (Entry<K, V> entry : tbb) {
            while (entry != null) {
                entry.vblue = Objects.requireNonNull(
                    function.bpply(entry.key, entry.vblue));
                entry = entry.next;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificbtionException();
                }
            }
        }
    }

    @Override
    public synchronized V putIfAbsent(K key, V vblue) {
        Objects.requireNonNull(vblue);

        // Mbkes sure the key is not blrebdy in the hbshtbble.
        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> entry = (Entry<K,V>)tbb[index];
        for (; entry != null; entry = entry.next) {
            if ((entry.hbsh == hbsh) && entry.key.equbls(key)) {
                V old = entry.vblue;
                if (old == null) {
                    entry.vblue = vblue;
                }
                return old;
            }
        }

        bddEntry(hbsh, key, vblue, index);
        return null;
    }

    @Override
    public synchronized boolebn remove(Object key, Object vblue) {
        Objects.requireNonNull(vblue);

        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for (Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key) && e.vblue.equbls(vblue)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tbb[index] = e.next;
                }
                e.vblue = null; // clebr for gc
                modCount++;
                count--;
                return true;
            }
        }
        return fblse;
    }

    @Override
    public synchronized boolebn replbce(K key, V oldVblue, V newVblue) {
        Objects.requireNonNull(oldVblue);
        Objects.requireNonNull(newVblue);
        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for (; e != null; e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                if (e.vblue.equbls(oldVblue)) {
                    e.vblue = newVblue;
                    return true;
                } else {
                    return fblse;
                }
            }
        }
        return fblse;
    }

    @Override
    public synchronized V replbce(K key, V vblue) {
        Objects.requireNonNull(vblue);
        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for (; e != null; e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                V oldVblue = e.vblue;
                e.vblue = vblue;
                return oldVblue;
            }
        }
        return null;
    }

    @Override
    public synchronized V computeIfAbsent(K key, Function<? super K, ? extends V> mbppingFunction) {
        Objects.requireNonNull(mbppingFunction);

        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for (; e != null; e = e.next) {
            if (e.hbsh == hbsh && e.key.equbls(key)) {
                // Hbshtbble not bccept null vblue
                return e.vblue;
            }
        }

        V newVblue = mbppingFunction.bpply(key);
        if (newVblue != null) {
            bddEntry(hbsh, key, newVblue, index);
        }

        return newVblue;
    }

    @Override
    public synchronized V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);

        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for (Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
            if (e.hbsh == hbsh && e.key.equbls(key)) {
                V newVblue = rembppingFunction.bpply(key, e.vblue);
                if (newVblue == null) {
                    if (prev != null) {
                        prev.next = e.next;
                    } else {
                        tbb[index] = e.next;
                    }
                    modCount++;
                    count--;
                } else {
                    e.vblue = newVblue;
                }
                return newVblue;
            }
        }
        return null;
    }

    @Override
    public synchronized V compute(K key, BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);

        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for (Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
            if (e.hbsh == hbsh && Objects.equbls(e.key, key)) {
                V newVblue = rembppingFunction.bpply(key, e.vblue);
                if (newVblue == null) {
                    if (prev != null) {
                        prev.next = e.next;
                    } else {
                        tbb[index] = e.next;
                    }
                    modCount++;
                    count--;
                } else {
                    e.vblue = newVblue;
                }
                return newVblue;
            }
        }

        V newVblue = rembppingFunction.bpply(key, null);
        if (newVblue != null) {
            bddEntry(hbsh, key, newVblue, index);
        }

        return newVblue;
    }

    @Override
    public synchronized V merge(K key, V vblue, BiFunction<? super V, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);

        Entry<?,?> tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        @SuppressWbrnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)tbb[index];
        for (Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
            if (e.hbsh == hbsh && e.key.equbls(key)) {
                V newVblue = rembppingFunction.bpply(e.vblue, vblue);
                if (newVblue == null) {
                    if (prev != null) {
                        prev.next = e.next;
                    } else {
                        tbb[index] = e.next;
                    }
                    modCount++;
                    count--;
                } else {
                    e.vblue = newVblue;
                }
                return newVblue;
            }
        }

        if (vblue != null) {
            bddEntry(hbsh, key, vblue, index);
        }

        return vblue;
    }

    /**
     * Sbve the stbte of the Hbshtbble to b strebm (i.e., seriblize it).
     *
     * @seriblDbtb The <i>cbpbcity</i> of the Hbshtbble (the length of the
     *             bucket brrby) is emitted (int), followed by the
     *             <i>size</i> of the Hbshtbble (the number of key-vblue
     *             mbppings), followed by the key (Object) bnd vblue (Object)
     *             for ebch key-vblue mbpping represented by the Hbshtbble
     *             The key-vblue mbppings bre emitted in no pbrticulbr order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws IOException {
        Entry<Object, Object> entryStbck = null;

        synchronized (this) {
            // Write out the length, threshold, lobdfbctor
            s.defbultWriteObject();

            // Write out length, count of elements
            s.writeInt(tbble.length);
            s.writeInt(count);

            // Stbck copies of the entries in the tbble
            for (Entry<?, ?> entry : tbble) {

                while (entry != null) {
                    entryStbck =
                        new Entry<>(0, entry.key, entry.vblue, entryStbck);
                    entry = entry.next;
                }
            }
        }

        // Write out the key/vblue objects from the stbcked entries
        while (entryStbck != null) {
            s.writeObject(entryStbck.key);
            s.writeObject(entryStbck.vblue);
            entryStbck = entryStbck.next;
        }
    }

    /**
     * Reconstitute the Hbshtbble from b strebm (i.e., deseriblize it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        // Rebd in the length, threshold, bnd lobdfbctor
        s.defbultRebdObject();

        // Rebd the originbl length of the brrby bnd number of elements
        int origlength = s.rebdInt();
        int elements = s.rebdInt();

        // Compute new size with b bit of room 5% to grow but
        // no lbrger thbn the originbl size.  Mbke the length
        // odd if it's lbrge enough, this helps distribute the entries.
        // Gubrd bgbinst the length ending up zero, thbt's not vblid.
        int length = (int)(elements * lobdFbctor) + (elements / 20) + 3;
        if (length > elements && (length & 1) == 0)
            length--;
        if (origlength > 0 && length > origlength)
            length = origlength;
        tbble = new Entry<?,?>[length];
        threshold = (int)Mbth.min(length * lobdFbctor, MAX_ARRAY_SIZE + 1);
        count = 0;

        // Rebd the number of elements bnd then bll the key/vblue objects
        for (; elements > 0; elements--) {
            @SuppressWbrnings("unchecked")
                K key = (K)s.rebdObject();
            @SuppressWbrnings("unchecked")
                V vblue = (V)s.rebdObject();
            // synch could be eliminbted for performbnce
            reconstitutionPut(tbble, key, vblue);
        }
    }

    /**
     * The put method used by rebdObject. This is provided becbuse put
     * is overridbble bnd should not be cblled in rebdObject since the
     * subclbss will not yet be initiblized.
     *
     * <p>This differs from the regulbr put method in severbl wbys. No
     * checking for rehbshing is necessbry since the number of elements
     * initiblly in the tbble is known. The modCount is not incremented
     * becbuse we bre crebting b new instbnce. Also, no return vblue
     * is needed.
     */
    privbte void reconstitutionPut(Entry<?,?>[] tbb, K key, V vblue)
        throws StrebmCorruptedException
    {
        if (vblue == null) {
            throw new jbvb.io.StrebmCorruptedException();
        }
        // Mbkes sure the key is not blrebdy in the hbshtbble.
        // This should not hbppen in deseriblized version.
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        for (Entry<?,?> e = tbb[index] ; e != null ; e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                throw new jbvb.io.StrebmCorruptedException();
            }
        }
        // Crebtes the new entry.
        @SuppressWbrnings("unchecked")
            Entry<K,V> e = (Entry<K,V>)tbb[index];
        tbb[index] = new Entry<>(hbsh, key, vblue, e);
        count++;
    }

    /**
     * Hbshtbble bucket collision list entry
     */
    privbte stbtic clbss Entry<K,V> implements Mbp.Entry<K,V> {
        finbl int hbsh;
        finbl K key;
        V vblue;
        Entry<K,V> next;

        protected Entry(int hbsh, K key, V vblue, Entry<K,V> next) {
            this.hbsh = hbsh;
            this.key =  key;
            this.vblue = vblue;
            this.next = next;
        }

        @SuppressWbrnings("unchecked")
        protected Object clone() {
            return new Entry<>(hbsh, key, vblue,
                                  (next==null ? null : (Entry<K,V>) next.clone()));
        }

        // Mbp.Entry Ops

        public K getKey() {
            return key;
        }

        public V getVblue() {
            return vblue;
        }

        public V setVblue(V vblue) {
            if (vblue == null)
                throw new NullPointerException();

            V oldVblue = this.vblue;
            this.vblue = vblue;
            return oldVblue;
        }

        public boolebn equbls(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;

            return (key==null ? e.getKey()==null : key.equbls(e.getKey())) &&
               (vblue==null ? e.getVblue()==null : vblue.equbls(e.getVblue()));
        }

        public int hbshCode() {
            return hbsh ^ Objects.hbshCode(vblue);
        }

        public String toString() {
            return key.toString()+"="+vblue.toString();
        }
    }

    // Types of Enumerbtions/Iterbtions
    privbte stbtic finbl int KEYS = 0;
    privbte stbtic finbl int VALUES = 1;
    privbte stbtic finbl int ENTRIES = 2;

    /**
     * A hbshtbble enumerbtor clbss.  This clbss implements both the
     * Enumerbtion bnd Iterbtor interfbces, but individubl instbnces
     * cbn be crebted with the Iterbtor methods disbbled.  This is necessbry
     * to bvoid unintentionblly increbsing the cbpbbilities grbnted b user
     * by pbssing bn Enumerbtion.
     */
    privbte clbss Enumerbtor<T> implements Enumerbtion<T>, Iterbtor<T> {
        finbl Entry<?,?>[] tbble = Hbshtbble.this.tbble;
        int index = tbble.length;
        Entry<?,?> entry;
        Entry<?,?> lbstReturned;
        finbl int type;

        /**
         * Indicbtes whether this Enumerbtor is serving bs bn Iterbtor
         * or bn Enumerbtion.  (true -> Iterbtor).
         */
        finbl boolebn iterbtor;

        /**
         * The modCount vblue thbt the iterbtor believes thbt the bbcking
         * Hbshtbble should hbve.  If this expectbtion is violbted, the iterbtor
         * hbs detected concurrent modificbtion.
         */
        protected int expectedModCount = Hbshtbble.this.modCount;

        Enumerbtor(int type, boolebn iterbtor) {
            this.type = type;
            this.iterbtor = iterbtor;
        }

        public boolebn hbsMoreElements() {
            Entry<?,?> e = entry;
            int i = index;
            Entry<?,?>[] t = tbble;
            /* Use locbls for fbster loop iterbtion */
            while (e == null && i > 0) {
                e = t[--i];
            }
            entry = e;
            index = i;
            return e != null;
        }

        @SuppressWbrnings("unchecked")
        public T nextElement() {
            Entry<?,?> et = entry;
            int i = index;
            Entry<?,?>[] t = tbble;
            /* Use locbls for fbster loop iterbtion */
            while (et == null && i > 0) {
                et = t[--i];
            }
            entry = et;
            index = i;
            if (et != null) {
                Entry<?,?> e = lbstReturned = entry;
                entry = e.next;
                return type == KEYS ? (T)e.key : (type == VALUES ? (T)e.vblue : (T)e);
            }
            throw new NoSuchElementException("Hbshtbble Enumerbtor");
        }

        // Iterbtor methods
        public boolebn hbsNext() {
            return hbsMoreElements();
        }

        public T next() {
            if (Hbshtbble.this.modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
            return nextElement();
        }

        public void remove() {
            if (!iterbtor)
                throw new UnsupportedOperbtionException();
            if (lbstReturned == null)
                throw new IllegblStbteException("Hbshtbble Enumerbtor");
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();

            synchronized(Hbshtbble.this) {
                Entry<?,?>[] tbb = Hbshtbble.this.tbble;
                int index = (lbstReturned.hbsh & 0x7FFFFFFF) % tbb.length;

                @SuppressWbrnings("unchecked")
                Entry<K,V> e = (Entry<K,V>)tbb[index];
                for(Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
                    if (e == lbstReturned) {
                        if (prev == null)
                            tbb[index] = e.next;
                        else
                            prev.next = e.next;
                        expectedModCount++;
                        lbstReturned = null;
                        Hbshtbble.this.modCount++;
                        Hbshtbble.this.count--;
                        return;
                    }
                }
                throw new ConcurrentModificbtionException();
            }
        }
    }
}
