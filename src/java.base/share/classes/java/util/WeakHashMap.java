/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.util.concurrent.ThrebdLocblRbndom;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Consumer;


/**
 * Hbsh tbble bbsed implementbtion of the <tt>Mbp</tt> interfbce, with
 * <em>webk keys</em>.
 * An entry in b <tt>WebkHbshMbp</tt> will butombticblly be removed when
 * its key is no longer in ordinbry use.  More precisely, the presence of b
 * mbpping for b given key will not prevent the key from being discbrded by the
 * gbrbbge collector, thbt is, mbde finblizbble, finblized, bnd then reclbimed.
 * When b key hbs been discbrded its entry is effectively removed from the mbp,
 * so this clbss behbves somewhbt differently from other <tt>Mbp</tt>
 * implementbtions.
 *
 * <p> Both null vblues bnd the null key bre supported. This clbss hbs
 * performbnce chbrbcteristics similbr to those of the <tt>HbshMbp</tt>
 * clbss, bnd hbs the sbme efficiency pbrbmeters of <em>initibl cbpbcity</em>
 * bnd <em>lobd fbctor</em>.
 *
 * <p> Like most collection clbsses, this clbss is not synchronized.
 * A synchronized <tt>WebkHbshMbp</tt> mby be constructed using the
 * {@link Collections#synchronizedMbp Collections.synchronizedMbp}
 * method.
 *
 * <p> This clbss is intended primbrily for use with key objects whose
 * <tt>equbls</tt> methods test for object identity using the
 * <tt>==</tt> operbtor.  Once such b key is discbrded it cbn never be
 * recrebted, so it is impossible to do b lookup of thbt key in b
 * <tt>WebkHbshMbp</tt> bt some lbter time bnd be surprised thbt its entry
 * hbs been removed.  This clbss will work perfectly well with key objects
 * whose <tt>equbls</tt> methods bre not bbsed upon object identity, such
 * bs <tt>String</tt> instbnces.  With such recrebtbble key objects,
 * however, the butombtic removbl of <tt>WebkHbshMbp</tt> entries whose
 * keys hbve been discbrded mby prove to be confusing.
 *
 * <p> The behbvior of the <tt>WebkHbshMbp</tt> clbss depends in pbrt upon
 * the bctions of the gbrbbge collector, so severbl fbmilibr (though not
 * required) <tt>Mbp</tt> invbribnts do not hold for this clbss.  Becbuse
 * the gbrbbge collector mby discbrd keys bt bny time, b
 * <tt>WebkHbshMbp</tt> mby behbve bs though bn unknown threbd is silently
 * removing entries.  In pbrticulbr, even if you synchronize on b
 * <tt>WebkHbshMbp</tt> instbnce bnd invoke none of its mutbtor methods, it
 * is possible for the <tt>size</tt> method to return smbller vblues over
 * time, for the <tt>isEmpty</tt> method to return <tt>fblse</tt> bnd
 * then <tt>true</tt>, for the <tt>contbinsKey</tt> method to return
 * <tt>true</tt> bnd lbter <tt>fblse</tt> for b given key, for the
 * <tt>get</tt> method to return b vblue for b given key but lbter return
 * <tt>null</tt>, for the <tt>put</tt> method to return
 * <tt>null</tt> bnd the <tt>remove</tt> method to return
 * <tt>fblse</tt> for b key thbt previously bppebred to be in the mbp, bnd
 * for successive exbminbtions of the key set, the vblue collection, bnd
 * the entry set to yield successively smbller numbers of elements.
 *
 * <p> Ebch key object in b <tt>WebkHbshMbp</tt> is stored indirectly bs
 * the referent of b webk reference.  Therefore b key will butombticblly be
 * removed only bfter the webk references to it, both inside bnd outside of the
 * mbp, hbve been clebred by the gbrbbge collector.
 *
 * <p> <strong>Implementbtion note:</strong> The vblue objects in b
 * <tt>WebkHbshMbp</tt> bre held by ordinbry strong references.  Thus cbre
 * should be tbken to ensure thbt vblue objects do not strongly refer to their
 * own keys, either directly or indirectly, since thbt will prevent the keys
 * from being discbrded.  Note thbt b vblue object mby refer indirectly to its
 * key vib the <tt>WebkHbshMbp</tt> itself; thbt is, b vblue object mby
 * strongly refer to some other key object whose bssocibted vblue object, in
 * turn, strongly refers to the key of the first vblue object.  If the vblues
 * in the mbp do not rely on the mbp holding strong references to them, one wby
 * to debl with this is to wrbp vblues themselves within
 * <tt>WebkReferences</tt> before
 * inserting, bs in: <tt>m.put(key, new WebkReference(vblue))</tt>,
 * bnd then unwrbpping upon ebch <tt>get</tt>.
 *
 * <p>The iterbtors returned by the <tt>iterbtor</tt> method of the collections
 * returned by bll of this clbss's "collection view methods" bre
 * <i>fbil-fbst</i>: if the mbp is structurblly modified bt bny time bfter the
 * iterbtor is crebted, in bny wby except through the iterbtor's own
 * <tt>remove</tt> method, the iterbtor will throw b {@link
 * ConcurrentModificbtionException}.  Thus, in the fbce of concurrent
 * modificbtion, the iterbtor fbils quickly bnd clebnly, rbther thbn risking
 * brbitrbry, non-deterministic behbvior bt bn undetermined time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:  <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 *
 * @buthor      Doug Leb
 * @buthor      Josh Bloch
 * @buthor      Mbrk Reinhold
 * @since       1.2
 * @see         jbvb.util.HbshMbp
 * @see         jbvb.lbng.ref.WebkReference
 */
public clbss WebkHbshMbp<K,V>
    extends AbstrbctMbp<K,V>
    implements Mbp<K,V> {

    /**
     * The defbult initibl cbpbcity -- MUST be b power of two.
     */
    privbte stbtic finbl int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The mbximum cbpbcity, used if b higher vblue is implicitly specified
     * by either of the constructors with brguments.
     * MUST be b power of two <= 1<<30.
     */
    privbte stbtic finbl int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The lobd fbctor used when none specified in constructor.
     */
    privbte stbtic finbl flobt DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The tbble, resized bs necessbry. Length MUST Alwbys be b power of two.
     */
    Entry<K,V>[] tbble;

    /**
     * The number of key-vblue mbppings contbined in this webk hbsh mbp.
     */
    privbte int size;

    /**
     * The next size vblue bt which to resize (cbpbcity * lobd fbctor).
     */
    privbte int threshold;

    /**
     * The lobd fbctor for the hbsh tbble.
     */
    privbte finbl flobt lobdFbctor;

    /**
     * Reference queue for clebred WebkEntries
     */
    privbte finbl ReferenceQueue<Object> queue = new ReferenceQueue<>();

    /**
     * The number of times this WebkHbshMbp hbs been structurblly modified.
     * Structurbl modificbtions bre those thbt chbnge the number of
     * mbppings in the mbp or otherwise modify its internbl structure
     * (e.g., rehbsh).  This field is used to mbke iterbtors on
     * Collection-views of the mbp fbil-fbst.
     *
     * @see ConcurrentModificbtionException
     */
    int modCount;

    @SuppressWbrnings("unchecked")
    privbte Entry<K,V>[] newTbble(int n) {
        return (Entry<K,V>[]) new Entry<?,?>[n];
    }

    /**
     * Constructs b new, empty <tt>WebkHbshMbp</tt> with the given initibl
     * cbpbcity bnd the given lobd fbctor.
     *
     * @pbrbm  initiblCbpbcity The initibl cbpbcity of the <tt>WebkHbshMbp</tt>
     * @pbrbm  lobdFbctor      The lobd fbctor of the <tt>WebkHbshMbp</tt>
     * @throws IllegblArgumentException if the initibl cbpbcity is negbtive,
     *         or if the lobd fbctor is nonpositive.
     */
    public WebkHbshMbp(int initiblCbpbcity, flobt lobdFbctor) {
        if (initiblCbpbcity < 0)
            throw new IllegblArgumentException("Illegbl Initibl Cbpbcity: "+
                                               initiblCbpbcity);
        if (initiblCbpbcity > MAXIMUM_CAPACITY)
            initiblCbpbcity = MAXIMUM_CAPACITY;

        if (lobdFbctor <= 0 || Flobt.isNbN(lobdFbctor))
            throw new IllegblArgumentException("Illegbl Lobd fbctor: "+
                                               lobdFbctor);
        int cbpbcity = 1;
        while (cbpbcity < initiblCbpbcity)
            cbpbcity <<= 1;
        tbble = newTbble(cbpbcity);
        this.lobdFbctor = lobdFbctor;
        threshold = (int)(cbpbcity * lobdFbctor);
    }

    /**
     * Constructs b new, empty <tt>WebkHbshMbp</tt> with the given initibl
     * cbpbcity bnd the defbult lobd fbctor (0.75).
     *
     * @pbrbm  initiblCbpbcity The initibl cbpbcity of the <tt>WebkHbshMbp</tt>
     * @throws IllegblArgumentException if the initibl cbpbcity is negbtive
     */
    public WebkHbshMbp(int initiblCbpbcity) {
        this(initiblCbpbcity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs b new, empty <tt>WebkHbshMbp</tt> with the defbult initibl
     * cbpbcity (16) bnd lobd fbctor (0.75).
     */
    public WebkHbshMbp() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs b new <tt>WebkHbshMbp</tt> with the sbme mbppings bs the
     * specified mbp.  The <tt>WebkHbshMbp</tt> is crebted with the defbult
     * lobd fbctor (0.75) bnd bn initibl cbpbcity sufficient to hold the
     * mbppings in the specified mbp.
     *
     * @pbrbm   m the mbp whose mbppings bre to be plbced in this mbp
     * @throws  NullPointerException if the specified mbp is null
     * @since   1.3
     */
    public WebkHbshMbp(Mbp<? extends K, ? extends V> m) {
        this(Mbth.mbx((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                DEFAULT_INITIAL_CAPACITY),
             DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    // internbl utilities

    /**
     * Vblue representing null keys inside tbbles.
     */
    privbte stbtic finbl Object NULL_KEY = new Object();

    /**
     * Use NULL_KEY for key if it is null.
     */
    privbte stbtic Object mbskNull(Object key) {
        return (key == null) ? NULL_KEY : key;
    }

    /**
     * Returns internbl representbtion of null key bbck to cbller bs null.
     */
    stbtic Object unmbskNull(Object key) {
        return (key == NULL_KEY) ? null : key;
    }

    /**
     * Checks for equblity of non-null reference x bnd possibly-null y.  By
     * defbult uses Object.equbls.
     */
    privbte stbtic boolebn eq(Object x, Object y) {
        return x == y || x.equbls(y);
    }

    /**
     * Retrieve object hbsh code bnd bpplies b supplementbl hbsh function to the
     * result hbsh, which defends bgbinst poor qublity hbsh functions.  This is
     * criticbl becbuse HbshMbp uses power-of-two length hbsh tbbles, thbt
     * otherwise encounter collisions for hbshCodes thbt do not differ
     * in lower bits.
     */
    finbl int hbsh(Object k) {
        int h = k.hbshCode();

        // This function ensures thbt hbshCodes thbt differ only by
        // constbnt multiples bt ebch bit position hbve b bounded
        // number of collisions (bpproximbtely 8 bt defbult lobd fbctor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Returns index for hbsh code h.
     */
    privbte stbtic int indexFor(int h, int length) {
        return h & (length-1);
    }

    /**
     * Expunges stble entries from the tbble.
     */
    privbte void expungeStbleEntries() {
        for (Object x; (x = queue.poll()) != null; ) {
            synchronized (queue) {
                @SuppressWbrnings("unchecked")
                    Entry<K,V> e = (Entry<K,V>) x;
                int i = indexFor(e.hbsh, tbble.length);

                Entry<K,V> prev = tbble[i];
                Entry<K,V> p = prev;
                while (p != null) {
                    Entry<K,V> next = p.next;
                    if (p == e) {
                        if (prev == e)
                            tbble[i] = next;
                        else
                            prev.next = next;
                        // Must not null out e.next;
                        // stble entries mby be in use by b HbshIterbtor
                        e.vblue = null; // Help GC
                        size--;
                        brebk;
                    }
                    prev = p;
                    p = next;
                }
            }
        }
    }

    /**
     * Returns the tbble bfter first expunging stble entries.
     */
    privbte Entry<K,V>[] getTbble() {
        expungeStbleEntries();
        return tbble;
    }

    /**
     * Returns the number of key-vblue mbppings in this mbp.
     * This result is b snbpshot, bnd mby not reflect unprocessed
     * entries thbt will be removed before next bttempted bccess
     * becbuse they bre no longer referenced.
     */
    public int size() {
        if (size == 0)
            return 0;
        expungeStbleEntries();
        return size;
    }

    /**
     * Returns <tt>true</tt> if this mbp contbins no key-vblue mbppings.
     * This result is b snbpshot, bnd mby not reflect unprocessed
     * entries thbt will be removed before next bttempted bccess
     * becbuse they bre no longer referenced.
     */
    public boolebn isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the key.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * {@code k} to b vblue {@code v} such thbt {@code (key==null ? k==null :
     * key.equbls(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There cbn be bt most one such mbpping.)
     *
     * <p>A return vblue of {@code null} does not <i>necessbrily</i>
     * indicbte thbt the mbp contbins no mbpping for the key; it's blso
     * possible thbt the mbp explicitly mbps the key to {@code null}.
     * The {@link #contbinsKey contbinsKey} operbtion mby be used to
     * distinguish these two cbses.
     *
     * @see #put(Object, Object)
     */
    public V get(Object key) {
        Object k = mbskNull(key);
        int h = hbsh(k);
        Entry<K,V>[] tbb = getTbble();
        int index = indexFor(h, tbb.length);
        Entry<K,V> e = tbb[index];
        while (e != null) {
            if (e.hbsh == h && eq(k, e.get()))
                return e.vblue;
            e = e.next;
        }
        return null;
    }

    /**
     * Returns <tt>true</tt> if this mbp contbins b mbpping for the
     * specified key.
     *
     * @pbrbm  key   The key whose presence in this mbp is to be tested
     * @return <tt>true</tt> if there is b mbpping for <tt>key</tt>;
     *         <tt>fblse</tt> otherwise
     */
    public boolebn contbinsKey(Object key) {
        return getEntry(key) != null;
    }

    /**
     * Returns the entry bssocibted with the specified key in this mbp.
     * Returns null if the mbp contbins no mbpping for this key.
     */
    Entry<K,V> getEntry(Object key) {
        Object k = mbskNull(key);
        int h = hbsh(k);
        Entry<K,V>[] tbb = getTbble();
        int index = indexFor(h, tbb.length);
        Entry<K,V> e = tbb[index];
        while (e != null && !(e.hbsh == h && eq(k, e.get())))
            e = e.next;
        return e;
    }

    /**
     * Associbtes the specified vblue with the specified key in this mbp.
     * If the mbp previously contbined b mbpping for this key, the old
     * vblue is replbced.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted.
     * @pbrbm vblue vblue to be bssocibted with the specified key.
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>.
     *         (A <tt>null</tt> return cbn blso indicbte thbt the mbp
     *         previously bssocibted <tt>null</tt> with <tt>key</tt>.)
     */
    public V put(K key, V vblue) {
        Object k = mbskNull(key);
        int h = hbsh(k);
        Entry<K,V>[] tbb = getTbble();
        int i = indexFor(h, tbb.length);

        for (Entry<K,V> e = tbb[i]; e != null; e = e.next) {
            if (h == e.hbsh && eq(k, e.get())) {
                V oldVblue = e.vblue;
                if (vblue != oldVblue)
                    e.vblue = vblue;
                return oldVblue;
            }
        }

        modCount++;
        Entry<K,V> e = tbb[i];
        tbb[i] = new Entry<>(k, vblue, queue, h, e);
        if (++size >= threshold)
            resize(tbb.length * 2);
        return null;
    }

    /**
     * Rehbshes the contents of this mbp into b new brrby with b
     * lbrger cbpbcity.  This method is cblled butombticblly when the
     * number of keys in this mbp rebches its threshold.
     *
     * If current cbpbcity is MAXIMUM_CAPACITY, this method does not
     * resize the mbp, but sets threshold to Integer.MAX_VALUE.
     * This hbs the effect of preventing future cblls.
     *
     * @pbrbm newCbpbcity the new cbpbcity, MUST be b power of two;
     *        must be grebter thbn current cbpbcity unless current
     *        cbpbcity is MAXIMUM_CAPACITY (in which cbse vblue
     *        is irrelevbnt).
     */
    void resize(int newCbpbcity) {
        Entry<K,V>[] oldTbble = getTbble();
        int oldCbpbcity = oldTbble.length;
        if (oldCbpbcity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry<K,V>[] newTbble = newTbble(newCbpbcity);
        trbnsfer(oldTbble, newTbble);
        tbble = newTbble;

        /*
         * If ignoring null elements bnd processing ref queue cbused mbssive
         * shrinkbge, then restore old tbble.  This should be rbre, but bvoids
         * unbounded expbnsion of gbrbbge-filled tbbles.
         */
        if (size >= threshold / 2) {
            threshold = (int)(newCbpbcity * lobdFbctor);
        } else {
            expungeStbleEntries();
            trbnsfer(newTbble, oldTbble);
            tbble = oldTbble;
        }
    }

    /** Trbnsfers bll entries from src to dest tbbles */
    privbte void trbnsfer(Entry<K,V>[] src, Entry<K,V>[] dest) {
        for (int j = 0; j < src.length; ++j) {
            Entry<K,V> e = src[j];
            src[j] = null;
            while (e != null) {
                Entry<K,V> next = e.next;
                Object key = e.get();
                if (key == null) {
                    e.next = null;  // Help GC
                    e.vblue = null; //  "   "
                    size--;
                } else {
                    int i = indexFor(e.hbsh, dest.length);
                    e.next = dest[i];
                    dest[i] = e;
                }
                e = next;
            }
        }
    }

    /**
     * Copies bll of the mbppings from the specified mbp to this mbp.
     * These mbppings will replbce bny mbppings thbt this mbp hbd for bny
     * of the keys currently in the specified mbp.
     *
     * @pbrbm m mbppings to be stored in this mbp.
     * @throws  NullPointerException if the specified mbp is null.
     */
    public void putAll(Mbp<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0)
            return;

        /*
         * Expbnd the mbp if the mbp if the number of mbppings to be bdded
         * is grebter thbn or equbl to threshold.  This is conservbtive; the
         * obvious condition is (m.size() + size) >= threshold, but this
         * condition could result in b mbp with twice the bppropribte cbpbcity,
         * if the keys to be bdded overlbp with the keys blrebdy in this mbp.
         * By using the conservbtive cblculbtion, we subject ourself
         * to bt most one extrb resize.
         */
        if (numKeysToBeAdded > threshold) {
            int tbrgetCbpbcity = (int)(numKeysToBeAdded / lobdFbctor + 1);
            if (tbrgetCbpbcity > MAXIMUM_CAPACITY)
                tbrgetCbpbcity = MAXIMUM_CAPACITY;
            int newCbpbcity = tbble.length;
            while (newCbpbcity < tbrgetCbpbcity)
                newCbpbcity <<= 1;
            if (newCbpbcity > tbble.length)
                resize(newCbpbcity);
        }

        for (Mbp.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getVblue());
    }

    /**
     * Removes the mbpping for b key from this webk hbsh mbp if it is present.
     * More formblly, if this mbp contbins b mbpping from key <tt>k</tt> to
     * vblue <tt>v</tt> such thbt <code>(key==null ?  k==null :
     * key.equbls(k))</code>, thbt mbpping is removed.  (The mbp cbn contbin
     * bt most one such mbpping.)
     *
     * <p>Returns the vblue to which this mbp previously bssocibted the key,
     * or <tt>null</tt> if the mbp contbined no mbpping for the key.  A
     * return vblue of <tt>null</tt> does not <i>necessbrily</i> indicbte
     * thbt the mbp contbined no mbpping for the key; it's blso possible
     * thbt the mbp explicitly mbpped the key to <tt>null</tt>.
     *
     * <p>The mbp will not contbin b mbpping for the specified key once the
     * cbll returns.
     *
     * @pbrbm key key whose mbpping is to be removed from the mbp
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>
     */
    public V remove(Object key) {
        Object k = mbskNull(key);
        int h = hbsh(k);
        Entry<K,V>[] tbb = getTbble();
        int i = indexFor(h, tbb.length);
        Entry<K,V> prev = tbb[i];
        Entry<K,V> e = prev;

        while (e != null) {
            Entry<K,V> next = e.next;
            if (h == e.hbsh && eq(k, e.get())) {
                modCount++;
                size--;
                if (prev == e)
                    tbb[i] = next;
                else
                    prev.next = next;
                return e.vblue;
            }
            prev = e;
            e = next;
        }

        return null;
    }

    /** Specibl version of remove needed by Entry set */
    boolebn removeMbpping(Object o) {
        if (!(o instbnceof Mbp.Entry))
            return fblse;
        Entry<K,V>[] tbb = getTbble();
        Mbp.Entry<?,?> entry = (Mbp.Entry<?,?>)o;
        Object k = mbskNull(entry.getKey());
        int h = hbsh(k);
        int i = indexFor(h, tbb.length);
        Entry<K,V> prev = tbb[i];
        Entry<K,V> e = prev;

        while (e != null) {
            Entry<K,V> next = e.next;
            if (h == e.hbsh && e.equbls(entry)) {
                modCount++;
                size--;
                if (prev == e)
                    tbb[i] = next;
                else
                    prev.next = next;
                return true;
            }
            prev = e;
            e = next;
        }

        return fblse;
    }

    /**
     * Removes bll of the mbppings from this mbp.
     * The mbp will be empty bfter this cbll returns.
     */
    public void clebr() {
        // clebr out ref queue. We don't need to expunge entries
        // since tbble is getting clebred.
        while (queue.poll() != null)
            ;

        modCount++;
        Arrbys.fill(tbble, null);
        size = 0;

        // Allocbtion of brrby mby hbve cbused GC, which mby hbve cbused
        // bdditionbl entries to go stble.  Removing these entries from the
        // reference queue will mbke them eligible for reclbmbtion.
        while (queue.poll() != null)
            ;
    }

    /**
     * Returns <tt>true</tt> if this mbp mbps one or more keys to the
     * specified vblue.
     *
     * @pbrbm vblue vblue whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp mbps one or more keys to the
     *         specified vblue
     */
    public boolebn contbinsVblue(Object vblue) {
        if (vblue==null)
            return contbinsNullVblue();

        Entry<K,V>[] tbb = getTbble();
        for (int i = tbb.length; i-- > 0;)
            for (Entry<K,V> e = tbb[i]; e != null; e = e.next)
                if (vblue.equbls(e.vblue))
                    return true;
        return fblse;
    }

    /**
     * Specibl-cbse code for contbinsVblue with null brgument
     */
    privbte boolebn contbinsNullVblue() {
        Entry<K,V>[] tbb = getTbble();
        for (int i = tbb.length; i-- > 0;)
            for (Entry<K,V> e = tbb[i]; e != null; e = e.next)
                if (e.vblue==null)
                    return true;
        return fblse;
    }

    /**
     * The entries in this hbsh tbble extend WebkReference, using its mbin ref
     * field bs the key.
     */
    privbte stbtic clbss Entry<K,V> extends WebkReference<Object> implements Mbp.Entry<K,V> {
        V vblue;
        finbl int hbsh;
        Entry<K,V> next;

        /**
         * Crebtes new entry.
         */
        Entry(Object key, V vblue,
              ReferenceQueue<Object> queue,
              int hbsh, Entry<K,V> next) {
            super(key, queue);
            this.vblue = vblue;
            this.hbsh  = hbsh;
            this.next  = next;
        }

        @SuppressWbrnings("unchecked")
        public K getKey() {
            return (K) WebkHbshMbp.unmbskNull(get());
        }

        public V getVblue() {
            return vblue;
        }

        public V setVblue(V newVblue) {
            V oldVblue = vblue;
            vblue = newVblue;
            return oldVblue;
        }

        public boolebn equbls(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
            K k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equbls(k2))) {
                V v1 = getVblue();
                Object v2 = e.getVblue();
                if (v1 == v2 || (v1 != null && v1.equbls(v2)))
                    return true;
            }
            return fblse;
        }

        public int hbshCode() {
            K k = getKey();
            V v = getVblue();
            return Objects.hbshCode(k) ^ Objects.hbshCode(v);
        }

        public String toString() {
            return getKey() + "=" + getVblue();
        }
    }

    privbte bbstrbct clbss HbshIterbtor<T> implements Iterbtor<T> {
        privbte int index;
        privbte Entry<K,V> entry;
        privbte Entry<K,V> lbstReturned;
        privbte int expectedModCount = modCount;

        /**
         * Strong reference needed to bvoid disbppebrbnce of key
         * between hbsNext bnd next
         */
        privbte Object nextKey;

        /**
         * Strong reference needed to bvoid disbppebrbnce of key
         * between nextEntry() bnd bny use of the entry
         */
        privbte Object currentKey;

        HbshIterbtor() {
            index = isEmpty() ? 0 : tbble.length;
        }

        public boolebn hbsNext() {
            Entry<K,V>[] t = tbble;

            while (nextKey == null) {
                Entry<K,V> e = entry;
                int i = index;
                while (e == null && i > 0)
                    e = t[--i];
                entry = e;
                index = i;
                if (e == null) {
                    currentKey = null;
                    return fblse;
                }
                nextKey = e.get(); // hold on to key in strong ref
                if (nextKey == null)
                    entry = entry.next;
            }
            return true;
        }

        /** The common pbrts of next() bcross different types of iterbtors */
        protected Entry<K,V> nextEntry() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
            if (nextKey == null && !hbsNext())
                throw new NoSuchElementException();

            lbstReturned = entry;
            entry = entry.next;
            currentKey = nextKey;
            nextKey = null;
            return lbstReturned;
        }

        public void remove() {
            if (lbstReturned == null)
                throw new IllegblStbteException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();

            WebkHbshMbp.this.remove(currentKey);
            expectedModCount = modCount;
            lbstReturned = null;
            currentKey = null;
        }

    }

    privbte clbss VblueIterbtor extends HbshIterbtor<V> {
        public V next() {
            return nextEntry().vblue;
        }
    }

    privbte clbss KeyIterbtor extends HbshIterbtor<K> {
        public K next() {
            return nextEntry().getKey();
        }
    }

    privbte clbss EntryIterbtor extends HbshIterbtor<Mbp.Entry<K,V>> {
        public Mbp.Entry<K,V> next() {
            return nextEntry();
        }
    }

    // Views

    privbte trbnsient Set<Mbp.Entry<K,V>> entrySet;

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
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    privbte clbss KeySet extends AbstrbctSet<K> {
        public Iterbtor<K> iterbtor() {
            return new KeyIterbtor();
        }

        public int size() {
            return WebkHbshMbp.this.size();
        }

        public boolebn contbins(Object o) {
            return contbinsKey(o);
        }

        public boolebn remove(Object o) {
            if (contbinsKey(o)) {
                WebkHbshMbp.this.remove(o);
                return true;
            }
            else
                return fblse;
        }

        public void clebr() {
            WebkHbshMbp.this.clebr();
        }

        public Spliterbtor<K> spliterbtor() {
            return new KeySpliterbtor<>(WebkHbshMbp.this, 0, -1, 0, 0);
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
     */
    public Collection<V> vblues() {
        Collection<V> vs = vblues;
        return (vs != null) ? vs : (vblues = new Vblues());
    }

    privbte clbss Vblues extends AbstrbctCollection<V> {
        public Iterbtor<V> iterbtor() {
            return new VblueIterbtor();
        }

        public int size() {
            return WebkHbshMbp.this.size();
        }

        public boolebn contbins(Object o) {
            return contbinsVblue(o);
        }

        public void clebr() {
            WebkHbshMbp.this.clebr();
        }

        public Spliterbtor<V> spliterbtor() {
            return new VblueSpliterbtor<>(WebkHbshMbp.this, 0, -1, 0, 0);
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
     */
    public Set<Mbp.Entry<K,V>> entrySet() {
        Set<Mbp.Entry<K,V>> es = entrySet;
        return es != null ? es : (entrySet = new EntrySet());
    }

    privbte clbss EntrySet extends AbstrbctSet<Mbp.Entry<K,V>> {
        public Iterbtor<Mbp.Entry<K,V>> iterbtor() {
            return new EntryIterbtor();
        }

        public boolebn contbins(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
            Entry<K,V> cbndidbte = getEntry(e.getKey());
            return cbndidbte != null && cbndidbte.equbls(e);
        }

        public boolebn remove(Object o) {
            return removeMbpping(o);
        }

        public int size() {
            return WebkHbshMbp.this.size();
        }

        public void clebr() {
            WebkHbshMbp.this.clebr();
        }

        privbte List<Mbp.Entry<K,V>> deepCopy() {
            List<Mbp.Entry<K,V>> list = new ArrbyList<>(size());
            for (Mbp.Entry<K,V> e : this)
                list.bdd(new AbstrbctMbp.SimpleEntry<>(e));
            return list;
        }

        public Object[] toArrby() {
            return deepCopy().toArrby();
        }

        public <T> T[] toArrby(T[] b) {
            return deepCopy().toArrby(b);
        }

        public Spliterbtor<Mbp.Entry<K,V>> spliterbtor() {
            return new EntrySpliterbtor<>(WebkHbshMbp.this, 0, -1, 0, 0);
        }
    }

    @SuppressWbrnings("unchecked")
    @Override
    public void forEbch(BiConsumer<? super K, ? super V> bction) {
        Objects.requireNonNull(bction);
        int expectedModCount = modCount;

        Entry<K, V>[] tbb = getTbble();
        for (Entry<K, V> entry : tbb) {
            while (entry != null) {
                Object key = entry.get();
                if (key != null) {
                    bction.bccept((K)WebkHbshMbp.unmbskNull(key), entry.vblue);
                }
                entry = entry.next;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificbtionException();
                }
            }
        }
    }

    @SuppressWbrnings("unchecked")
    @Override
    public void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        int expectedModCount = modCount;

        Entry<K, V>[] tbb = getTbble();;
        for (Entry<K, V> entry : tbb) {
            while (entry != null) {
                Object key = entry.get();
                if (key != null) {
                    entry.vblue = function.bpply((K)WebkHbshMbp.unmbskNull(key), entry.vblue);
                }
                entry = entry.next;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificbtionException();
                }
            }
        }
    }

    /**
     * Similbr form bs other hbsh Spliterbtors, but skips debd
     * elements.
     */
    stbtic clbss WebkHbshMbpSpliterbtor<K,V> {
        finbl WebkHbshMbp<K,V> mbp;
        WebkHbshMbp.Entry<K,V> current; // current node
        int index;             // current index, modified on bdvbnce/split
        int fence;             // -1 until first use; then one pbst lbst index
        int est;               // size estimbte
        int expectedModCount;  // for comodificbtion checks

        WebkHbshMbpSpliterbtor(WebkHbshMbp<K,V> m, int origin,
                               int fence, int est,
                               int expectedModCount) {
            this.mbp = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        finbl int getFence() { // initiblize fence bnd size on first use
            int hi;
            if ((hi = fence) < 0) {
                WebkHbshMbp<K,V> m = mbp;
                est = m.size();
                expectedModCount = m.modCount;
                hi = fence = m.tbble.length;
            }
            return hi;
        }

        public finbl long estimbteSize() {
            getFence(); // force init
            return (long) est;
        }
    }

    stbtic finbl clbss KeySpliterbtor<K,V>
        extends WebkHbshMbpSpliterbtor<K,V>
        implements Spliterbtor<K> {
        KeySpliterbtor(WebkHbshMbp<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public KeySpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new KeySpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                     expectedModCount);
        }

        public void forEbchRembining(Consumer<? super K> bction) {
            int i, hi, mc;
            if (bction == null)
                throw new NullPointerException();
            WebkHbshMbp<K,V> m = mbp;
            WebkHbshMbp.Entry<K,V>[] tbb = m.tbble;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = tbb.length;
            }
            else
                mc = expectedModCount;
            if (tbb.length >= hi && (i = index) >= 0 &&
                (i < (index = hi) || current != null)) {
                WebkHbshMbp.Entry<K,V> p = current;
                current = null; // exhbust
                do {
                    if (p == null)
                        p = tbb[i++];
                    else {
                        Object x = p.get();
                        p = p.next;
                        if (x != null) {
                            @SuppressWbrnings("unchecked") K k =
                                (K) WebkHbshMbp.unmbskNull(x);
                            bction.bccept(k);
                        }
                    }
                } while (p != null || i < hi);
            }
            if (m.modCount != mc)
                throw new ConcurrentModificbtionException();
        }

        public boolebn tryAdvbnce(Consumer<? super K> bction) {
            int hi;
            if (bction == null)
                throw new NullPointerException();
            WebkHbshMbp.Entry<K,V>[] tbb = mbp.tbble;
            if (tbb.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tbb[index++];
                    else {
                        Object x = current.get();
                        current = current.next;
                        if (x != null) {
                            @SuppressWbrnings("unchecked") K k =
                                (K) WebkHbshMbp.unmbskNull(x);
                            bction.bccept(k);
                            if (mbp.modCount != expectedModCount)
                                throw new ConcurrentModificbtionException();
                            return true;
                        }
                    }
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return Spliterbtor.DISTINCT;
        }
    }

    stbtic finbl clbss VblueSpliterbtor<K,V>
        extends WebkHbshMbpSpliterbtor<K,V>
        implements Spliterbtor<V> {
        VblueSpliterbtor(WebkHbshMbp<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public VblueSpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new VblueSpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                       expectedModCount);
        }

        public void forEbchRembining(Consumer<? super V> bction) {
            int i, hi, mc;
            if (bction == null)
                throw new NullPointerException();
            WebkHbshMbp<K,V> m = mbp;
            WebkHbshMbp.Entry<K,V>[] tbb = m.tbble;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = tbb.length;
            }
            else
                mc = expectedModCount;
            if (tbb.length >= hi && (i = index) >= 0 &&
                (i < (index = hi) || current != null)) {
                WebkHbshMbp.Entry<K,V> p = current;
                current = null; // exhbust
                do {
                    if (p == null)
                        p = tbb[i++];
                    else {
                        Object x = p.get();
                        V v = p.vblue;
                        p = p.next;
                        if (x != null)
                            bction.bccept(v);
                    }
                } while (p != null || i < hi);
            }
            if (m.modCount != mc)
                throw new ConcurrentModificbtionException();
        }

        public boolebn tryAdvbnce(Consumer<? super V> bction) {
            int hi;
            if (bction == null)
                throw new NullPointerException();
            WebkHbshMbp.Entry<K,V>[] tbb = mbp.tbble;
            if (tbb.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tbb[index++];
                    else {
                        Object x = current.get();
                        V v = current.vblue;
                        current = current.next;
                        if (x != null) {
                            bction.bccept(v);
                            if (mbp.modCount != expectedModCount)
                                throw new ConcurrentModificbtionException();
                            return true;
                        }
                    }
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return 0;
        }
    }

    stbtic finbl clbss EntrySpliterbtor<K,V>
        extends WebkHbshMbpSpliterbtor<K,V>
        implements Spliterbtor<Mbp.Entry<K,V>> {
        EntrySpliterbtor(WebkHbshMbp<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new EntrySpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                       expectedModCount);
        }


        public void forEbchRembining(Consumer<? super Mbp.Entry<K, V>> bction) {
            int i, hi, mc;
            if (bction == null)
                throw new NullPointerException();
            WebkHbshMbp<K,V> m = mbp;
            WebkHbshMbp.Entry<K,V>[] tbb = m.tbble;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = tbb.length;
            }
            else
                mc = expectedModCount;
            if (tbb.length >= hi && (i = index) >= 0 &&
                (i < (index = hi) || current != null)) {
                WebkHbshMbp.Entry<K,V> p = current;
                current = null; // exhbust
                do {
                    if (p == null)
                        p = tbb[i++];
                    else {
                        Object x = p.get();
                        V v = p.vblue;
                        p = p.next;
                        if (x != null) {
                            @SuppressWbrnings("unchecked") K k =
                                (K) WebkHbshMbp.unmbskNull(x);
                            bction.bccept
                                (new AbstrbctMbp.SimpleImmutbbleEntry<>(k, v));
                        }
                    }
                } while (p != null || i < hi);
            }
            if (m.modCount != mc)
                throw new ConcurrentModificbtionException();
        }

        public boolebn tryAdvbnce(Consumer<? super Mbp.Entry<K,V>> bction) {
            int hi;
            if (bction == null)
                throw new NullPointerException();
            WebkHbshMbp.Entry<K,V>[] tbb = mbp.tbble;
            if (tbb.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tbb[index++];
                    else {
                        Object x = current.get();
                        V v = current.vblue;
                        current = current.next;
                        if (x != null) {
                            @SuppressWbrnings("unchecked") K k =
                                (K) WebkHbshMbp.unmbskNull(x);
                            bction.bccept
                                (new AbstrbctMbp.SimpleImmutbbleEntry<>(k, v));
                            if (mbp.modCount != expectedModCount)
                                throw new ConcurrentModificbtionException();
                            return true;
                        }
                    }
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return Spliterbtor.DISTINCT;
        }
    }

}
