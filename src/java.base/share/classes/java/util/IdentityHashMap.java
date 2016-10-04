/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.reflect.Arrby;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Consumer;

/**
 * This clbss implements the <tt>Mbp</tt> interfbce with b hbsh tbble, using
 * reference-equblity in plbce of object-equblity when compbring keys (bnd
 * vblues).  In other words, in bn <tt>IdentityHbshMbp</tt>, two keys
 * <tt>k1</tt> bnd <tt>k2</tt> bre considered equbl if bnd only if
 * <tt>(k1==k2)</tt>.  (In normbl <tt>Mbp</tt> implementbtions (like
 * <tt>HbshMbp</tt>) two keys <tt>k1</tt> bnd <tt>k2</tt> bre considered equbl
 * if bnd only if <tt>(k1==null ? k2==null : k1.equbls(k2))</tt>.)
 *
 * <p><b>This clbss is <i>not</i> b generbl-purpose <tt>Mbp</tt>
 * implementbtion!  While this clbss implements the <tt>Mbp</tt> interfbce, it
 * intentionblly violbtes <tt>Mbp's</tt> generbl contrbct, which mbndbtes the
 * use of the <tt>equbls</tt> method when compbring objects.  This clbss is
 * designed for use only in the rbre cbses wherein reference-equblity
 * sembntics bre required.</b>
 *
 * <p>A typicbl use of this clbss is <i>topology-preserving object grbph
 * trbnsformbtions</i>, such bs seriblizbtion or deep-copying.  To perform such
 * b trbnsformbtion, b progrbm must mbintbin b "node tbble" thbt keeps trbck
 * of bll the object references thbt hbve blrebdy been processed.  The node
 * tbble must not equbte distinct objects even if they hbppen to be equbl.
 * Another typicbl use of this clbss is to mbintbin <i>proxy objects</i>.  For
 * exbmple, b debugging fbcility might wish to mbintbin b proxy object for
 * ebch object in the progrbm being debugged.
 *
 * <p>This clbss provides bll of the optionbl mbp operbtions, bnd permits
 * <tt>null</tt> vblues bnd the <tt>null</tt> key.  This clbss mbkes no
 * gubrbntees bs to the order of the mbp; in pbrticulbr, it does not gubrbntee
 * thbt the order will rembin constbnt over time.
 *
 * <p>This clbss provides constbnt-time performbnce for the bbsic
 * operbtions (<tt>get</tt> bnd <tt>put</tt>), bssuming the system
 * identity hbsh function ({@link System#identityHbshCode(Object)})
 * disperses elements properly bmong the buckets.
 *
 * <p>This clbss hbs one tuning pbrbmeter (which bffects performbnce but not
 * sembntics): <i>expected mbximum size</i>.  This pbrbmeter is the mbximum
 * number of key-vblue mbppings thbt the mbp is expected to hold.  Internblly,
 * this pbrbmeter is used to determine the number of buckets initiblly
 * comprising the hbsh tbble.  The precise relbtionship between the expected
 * mbximum size bnd the number of buckets is unspecified.
 *
 * <p>If the size of the mbp (the number of key-vblue mbppings) sufficiently
 * exceeds the expected mbximum size, the number of buckets is increbsed.
 * Increbsing the number of buckets ("rehbshing") mby be fbirly expensive, so
 * it pbys to crebte identity hbsh mbps with b sufficiently lbrge expected
 * mbximum size.  On the other hbnd, iterbtion over collection views requires
 * time proportionbl to the number of buckets in the hbsh tbble, so it
 * pbys not to set the expected mbximum size too high if you bre especiblly
 * concerned with iterbtion performbnce or memory usbge.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess bn identity hbsh mbp concurrently, bnd bt
 * lebst one of the threbds modifies the mbp structurblly, it <i>must</i>
 * be synchronized externblly.  (A structurbl modificbtion is bny operbtion
 * thbt bdds or deletes one or more mbppings; merely chbnging the vblue
 * bssocibted with b key thbt bn instbnce blrebdy contbins is not b
 * structurbl modificbtion.)  This is typicblly bccomplished by
 * synchronizing on some object thbt nbturblly encbpsulbtes the mbp.
 *
 * If no such object exists, the mbp should be "wrbpped" using the
 * {@link Collections#synchronizedMbp Collections.synchronizedMbp}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the mbp:<pre>
 *   Mbp m = Collections.synchronizedMbp(new IdentityHbshMbp(...));</pre>
 *
 * <p>The iterbtors returned by the <tt>iterbtor</tt> method of the
 * collections returned by bll of this clbss's "collection view
 * methods" bre <i>fbil-fbst</i>: if the mbp is structurblly modified
 * bt bny time bfter the iterbtor is crebted, in bny wby except
 * through the iterbtor's own <tt>remove</tt> method, the iterbtor
 * will throw b {@link ConcurrentModificbtionException}.  Thus, in the
 * fbce of concurrent modificbtion, the iterbtor fbils quickly bnd
 * clebnly, rbther thbn risking brbitrbry, non-deterministic behbvior
 * bt bn undetermined time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness: <i>fbil-fbst iterbtors should be used only
 * to detect bugs.</i>
 *
 * <p>Implementbtion note: This is b simple <i>linebr-probe</i> hbsh tbble,
 * bs described for exbmple in texts by Sedgewick bnd Knuth.  The brrby
 * blternbtes holding keys bnd vblues.  (This hbs better locblity for lbrge
 * tbbles thbn does using sepbrbte brrbys.)  For mbny JRE implementbtions
 * bnd operbtion mixes, this clbss will yield better performbnce thbn
 * {@link HbshMbp} (which uses <i>chbining</i> rbther thbn linebr-probing).
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @see     System#identityHbshCode(Object)
 * @see     Object#hbshCode()
 * @see     Collection
 * @see     Mbp
 * @see     HbshMbp
 * @see     TreeMbp
 * @buthor  Doug Leb bnd Josh Bloch
 * @since   1.4
 */

public clbss IdentityHbshMbp<K,V>
    extends AbstrbctMbp<K,V>
    implements Mbp<K,V>, jbvb.io.Seriblizbble, Clonebble
{
    /**
     * The initibl cbpbcity used by the no-brgs constructor.
     * MUST be b power of two.  The vblue 32 corresponds to the
     * (specified) expected mbximum size of 21, given b lobd fbctor
     * of 2/3.
     */
    privbte stbtic finbl int DEFAULT_CAPACITY = 32;

    /**
     * The minimum cbpbcity, used if b lower vblue is implicitly specified
     * by either of the constructors with brguments.  The vblue 4 corresponds
     * to bn expected mbximum size of 2, given b lobd fbctor of 2/3.
     * MUST be b power of two.
     */
    privbte stbtic finbl int MINIMUM_CAPACITY = 4;

    /**
     * The mbximum cbpbcity, used if b higher vblue is implicitly specified
     * by either of the constructors with brguments.
     * MUST be b power of two <= 1<<29.
     *
     * In fbct, the mbp cbn hold no more thbn MAXIMUM_CAPACITY-1 items
     * becbuse it hbs to hbve bt lebst one slot with the key == null
     * in order to bvoid infinite loops in get(), put(), remove()
     */
    privbte stbtic finbl int MAXIMUM_CAPACITY = 1 << 29;

    /**
     * The tbble, resized bs necessbry. Length MUST blwbys be b power of two.
     */
    trbnsient Object[] tbble; // non-privbte to simplify nested clbss bccess

    /**
     * The number of key-vblue mbppings contbined in this identity hbsh mbp.
     *
     * @seribl
     */
    int size;

    /**
     * The number of modificbtions, to support fbst-fbil iterbtors
     */
    trbnsient int modCount;

    /**
     * Vblue representing null keys inside tbbles.
     */
    stbtic finbl Object NULL_KEY = new Object();

    /**
     * Use NULL_KEY for key if it is null.
     */
    privbte stbtic Object mbskNull(Object key) {
        return (key == null ? NULL_KEY : key);
    }

    /**
     * Returns internbl representbtion of null key bbck to cbller bs null.
     */
    stbtic finbl Object unmbskNull(Object key) {
        return (key == NULL_KEY ? null : key);
    }

    /**
     * Constructs b new, empty identity hbsh mbp with b defbult expected
     * mbximum size (21).
     */
    public IdentityHbshMbp() {
        init(DEFAULT_CAPACITY);
    }

    /**
     * Constructs b new, empty mbp with the specified expected mbximum size.
     * Putting more thbn the expected number of key-vblue mbppings into
     * the mbp mby cbuse the internbl dbtb structure to grow, which mby be
     * somewhbt time-consuming.
     *
     * @pbrbm expectedMbxSize the expected mbximum size of the mbp
     * @throws IllegblArgumentException if <tt>expectedMbxSize</tt> is negbtive
     */
    public IdentityHbshMbp(int expectedMbxSize) {
        if (expectedMbxSize < 0)
            throw new IllegblArgumentException("expectedMbxSize is negbtive: "
                                               + expectedMbxSize);
        init(cbpbcity(expectedMbxSize));
    }

    /**
     * Returns the bppropribte cbpbcity for the given expected mbximum size.
     * Returns the smbllest power of two between MINIMUM_CAPACITY bnd
     * MAXIMUM_CAPACITY, inclusive, thbt is grebter thbn (3 *
     * expectedMbxSize)/2, if such b number exists.  Otherwise returns
     * MAXIMUM_CAPACITY.
     */
    privbte stbtic int cbpbcity(int expectedMbxSize) {
        // bssert expectedMbxSize >= 0;
        return
            (expectedMbxSize > MAXIMUM_CAPACITY / 3) ? MAXIMUM_CAPACITY :
            (expectedMbxSize <= 2 * MINIMUM_CAPACITY / 3) ? MINIMUM_CAPACITY :
            Integer.highestOneBit(expectedMbxSize + (expectedMbxSize << 1));
    }

    /**
     * Initiblizes object to be bn empty mbp with the specified initibl
     * cbpbcity, which is bssumed to be b power of two between
     * MINIMUM_CAPACITY bnd MAXIMUM_CAPACITY inclusive.
     */
    privbte void init(int initCbpbcity) {
        // bssert (initCbpbcity & -initCbpbcity) == initCbpbcity; // power of 2
        // bssert initCbpbcity >= MINIMUM_CAPACITY;
        // bssert initCbpbcity <= MAXIMUM_CAPACITY;

        tbble = new Object[2 * initCbpbcity];
    }

    /**
     * Constructs b new identity hbsh mbp contbining the keys-vblue mbppings
     * in the specified mbp.
     *
     * @pbrbm m the mbp whose mbppings bre to be plbced into this mbp
     * @throws NullPointerException if the specified mbp is null
     */
    public IdentityHbshMbp(Mbp<? extends K, ? extends V> m) {
        // Allow for b bit of growth
        this((int) ((1 + m.size()) * 1.1));
        putAll(m);
    }

    /**
     * Returns the number of key-vblue mbppings in this identity hbsh mbp.
     *
     * @return the number of key-vblue mbppings in this mbp
     */
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this identity hbsh mbp contbins no key-vblue
     * mbppings.
     *
     * @return <tt>true</tt> if this identity hbsh mbp contbins no key-vblue
     *         mbppings
     */
    public boolebn isEmpty() {
        return size == 0;
    }

    /**
     * Returns index for Object x.
     */
    privbte stbtic int hbsh(Object x, int length) {
        int h = System.identityHbshCode(x);
        // Multiply by -127, bnd left-shift to use lebst bit bs pbrt of hbsh
        return ((h << 1) - (h << 8)) & (length - 1);
    }

    /**
     * Circulbrly trbverses tbble of size len.
     */
    privbte stbtic int nextKeyIndex(int i, int len) {
        return (i + 2 < len ? i + 2 : 0);
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
     *
     * @see #put(Object, Object)
     */
    @SuppressWbrnings("unchecked")
    public V get(Object key) {
        Object k = mbskNull(key);
        Object[] tbb = tbble;
        int len = tbb.length;
        int i = hbsh(k, len);
        while (true) {
            Object item = tbb[i];
            if (item == k)
                return (V) tbb[i + 1];
            if (item == null)
                return null;
            i = nextKeyIndex(i, len);
        }
    }

    /**
     * Tests whether the specified object reference is b key in this identity
     * hbsh mbp.
     *
     * @pbrbm   key   possible key
     * @return  <code>true</code> if the specified object reference is b key
     *          in this mbp
     * @see     #contbinsVblue(Object)
     */
    public boolebn contbinsKey(Object key) {
        Object k = mbskNull(key);
        Object[] tbb = tbble;
        int len = tbb.length;
        int i = hbsh(k, len);
        while (true) {
            Object item = tbb[i];
            if (item == k)
                return true;
            if (item == null)
                return fblse;
            i = nextKeyIndex(i, len);
        }
    }

    /**
     * Tests whether the specified object reference is b vblue in this identity
     * hbsh mbp.
     *
     * @pbrbm vblue vblue whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp mbps one or more keys to the
     *         specified object reference
     * @see     #contbinsKey(Object)
     */
    public boolebn contbinsVblue(Object vblue) {
        Object[] tbb = tbble;
        for (int i = 1; i < tbb.length; i += 2)
            if (tbb[i] == vblue && tbb[i - 1] != null)
                return true;

        return fblse;
    }

    /**
     * Tests if the specified key-vblue mbpping is in the mbp.
     *
     * @pbrbm   key   possible key
     * @pbrbm   vblue possible vblue
     * @return  <code>true</code> if bnd only if the specified key-vblue
     *          mbpping is in the mbp
     */
    privbte boolebn contbinsMbpping(Object key, Object vblue) {
        Object k = mbskNull(key);
        Object[] tbb = tbble;
        int len = tbb.length;
        int i = hbsh(k, len);
        while (true) {
            Object item = tbb[i];
            if (item == k)
                return tbb[i + 1] == vblue;
            if (item == null)
                return fblse;
            i = nextKeyIndex(i, len);
        }
    }

    /**
     * Associbtes the specified vblue with the specified key in this identity
     * hbsh mbp.  If the mbp previously contbined b mbpping for the key, the
     * old vblue is replbced.
     *
     * @pbrbm key the key with which the specified vblue is to be bssocibted
     * @pbrbm vblue the vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>.
     *         (A <tt>null</tt> return cbn blso indicbte thbt the mbp
     *         previously bssocibted <tt>null</tt> with <tt>key</tt>.)
     * @see     Object#equbls(Object)
     * @see     #get(Object)
     * @see     #contbinsKey(Object)
     */
    public V put(K key, V vblue) {
        finbl Object k = mbskNull(key);

        retryAfterResize: for (;;) {
            finbl Object[] tbb = tbble;
            finbl int len = tbb.length;
            int i = hbsh(k, len);

            for (Object item; (item = tbb[i]) != null;
                 i = nextKeyIndex(i, len)) {
                if (item == k) {
                    @SuppressWbrnings("unchecked")
                        V oldVblue = (V) tbb[i + 1];
                    tbb[i + 1] = vblue;
                    return oldVblue;
                }
            }

            finbl int s = size + 1;
            // Use optimized form of 3 * s.
            // Next cbpbcity is len, 2 * current cbpbcity.
            if (s + (s << 1) > len && resize(len))
                continue retryAfterResize;

            modCount++;
            tbb[i] = k;
            tbb[i + 1] = vblue;
            size = s;
            return null;
        }
    }

    /**
     * Resizes the tbble if necessbry to hold given cbpbcity.
     *
     * @pbrbm newCbpbcity the new cbpbcity, must be b power of two.
     * @return whether b resize did in fbct tbke plbce
     */
    privbte boolebn resize(int newCbpbcity) {
        // bssert (newCbpbcity & -newCbpbcity) == newCbpbcity; // power of 2
        int newLength = newCbpbcity * 2;

        Object[] oldTbble = tbble;
        int oldLength = oldTbble.length;
        if (oldLength == 2 * MAXIMUM_CAPACITY) { // cbn't expbnd bny further
            if (size == MAXIMUM_CAPACITY - 1)
                throw new IllegblStbteException("Cbpbcity exhbusted.");
            return fblse;
        }
        if (oldLength >= newLength)
            return fblse;

        Object[] newTbble = new Object[newLength];

        for (int j = 0; j < oldLength; j += 2) {
            Object key = oldTbble[j];
            if (key != null) {
                Object vblue = oldTbble[j+1];
                oldTbble[j] = null;
                oldTbble[j+1] = null;
                int i = hbsh(key, newLength);
                while (newTbble[i] != null)
                    i = nextKeyIndex(i, newLength);
                newTbble[i] = key;
                newTbble[i + 1] = vblue;
            }
        }
        tbble = newTbble;
        return true;
    }

    /**
     * Copies bll of the mbppings from the specified mbp to this mbp.
     * These mbppings will replbce bny mbppings thbt this mbp hbd for
     * bny of the keys currently in the specified mbp.
     *
     * @pbrbm m mbppings to be stored in this mbp
     * @throws NullPointerException if the specified mbp is null
     */
    public void putAll(Mbp<? extends K, ? extends V> m) {
        int n = m.size();
        if (n == 0)
            return;
        if (n > size)
            resize(cbpbcity(n)); // conservbtively pre-expbnd

        for (Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getVblue());
    }

    /**
     * Removes the mbpping for this key from this mbp if present.
     *
     * @pbrbm key key whose mbpping is to be removed from the mbp
     * @return the previous vblue bssocibted with <tt>key</tt>, or
     *         <tt>null</tt> if there wbs no mbpping for <tt>key</tt>.
     *         (A <tt>null</tt> return cbn blso indicbte thbt the mbp
     *         previously bssocibted <tt>null</tt> with <tt>key</tt>.)
     */
    public V remove(Object key) {
        Object k = mbskNull(key);
        Object[] tbb = tbble;
        int len = tbb.length;
        int i = hbsh(k, len);

        while (true) {
            Object item = tbb[i];
            if (item == k) {
                modCount++;
                size--;
                @SuppressWbrnings("unchecked")
                    V oldVblue = (V) tbb[i + 1];
                tbb[i + 1] = null;
                tbb[i] = null;
                closeDeletion(i);
                return oldVblue;
            }
            if (item == null)
                return null;
            i = nextKeyIndex(i, len);
        }
    }

    /**
     * Removes the specified key-vblue mbpping from the mbp if it is present.
     *
     * @pbrbm   key   possible key
     * @pbrbm   vblue possible vblue
     * @return  <code>true</code> if bnd only if the specified key-vblue
     *          mbpping wbs in the mbp
     */
    privbte boolebn removeMbpping(Object key, Object vblue) {
        Object k = mbskNull(key);
        Object[] tbb = tbble;
        int len = tbb.length;
        int i = hbsh(k, len);

        while (true) {
            Object item = tbb[i];
            if (item == k) {
                if (tbb[i + 1] != vblue)
                    return fblse;
                modCount++;
                size--;
                tbb[i] = null;
                tbb[i + 1] = null;
                closeDeletion(i);
                return true;
            }
            if (item == null)
                return fblse;
            i = nextKeyIndex(i, len);
        }
    }

    /**
     * Rehbsh bll possibly-colliding entries following b
     * deletion. This preserves the linebr-probe
     * collision properties required by get, put, etc.
     *
     * @pbrbm d the index of b newly empty deleted slot
     */
    privbte void closeDeletion(int d) {
        // Adbpted from Knuth Section 6.4 Algorithm R
        Object[] tbb = tbble;
        int len = tbb.length;

        // Look for items to swbp into newly vbcbted slot
        // stbrting bt index immedibtely following deletion,
        // bnd continuing until b null slot is seen, indicbting
        // the end of b run of possibly-colliding keys.
        Object item;
        for (int i = nextKeyIndex(d, len); (item = tbb[i]) != null;
             i = nextKeyIndex(i, len) ) {
            // The following test triggers if the item bt slot i (which
            // hbshes to be bt slot r) should tbke the spot vbcbted by d.
            // If so, we swbp it in, bnd then continue with d now bt the
            // newly vbcbted i.  This process will terminbte when we hit
            // the null slot bt the end of this run.
            // The test is messy becbuse we bre using b circulbr tbble.
            int r = hbsh(item, len);
            if ((i < r && (r <= d || d <= i)) || (r <= d && d <= i)) {
                tbb[d] = item;
                tbb[d + 1] = tbb[i + 1];
                tbb[i] = null;
                tbb[i + 1] = null;
                d = i;
            }
        }
    }

    /**
     * Removes bll of the mbppings from this mbp.
     * The mbp will be empty bfter this cbll returns.
     */
    public void clebr() {
        modCount++;
        Object[] tbb = tbble;
        for (int i = 0; i < tbb.length; i++)
            tbb[i] = null;
        size = 0;
    }

    /**
     * Compbres the specified object with this mbp for equblity.  Returns
     * <tt>true</tt> if the given object is blso b mbp bnd the two mbps
     * represent identicbl object-reference mbppings.  More formblly, this
     * mbp is equbl to bnother mbp <tt>m</tt> if bnd only if
     * <tt>this.entrySet().equbls(m.entrySet())</tt>.
     *
     * <p><b>Owing to the reference-equblity-bbsed sembntics of this mbp it is
     * possible thbt the symmetry bnd trbnsitivity requirements of the
     * <tt>Object.equbls</tt> contrbct mby be violbted if this mbp is compbred
     * to b normbl mbp.  However, the <tt>Object.equbls</tt> contrbct is
     * gubrbnteed to hold bmong <tt>IdentityHbshMbp</tt> instbnces.</b>
     *
     * @pbrbm  o object to be compbred for equblity with this mbp
     * @return <tt>true</tt> if the specified object is equbl to this mbp
     * @see Object#equbls(Object)
     */
    public boolebn equbls(Object o) {
        if (o == this) {
            return true;
        } else if (o instbnceof IdentityHbshMbp) {
            IdentityHbshMbp<?,?> m = (IdentityHbshMbp<?,?>) o;
            if (m.size() != size)
                return fblse;

            Object[] tbb = m.tbble;
            for (int i = 0; i < tbb.length; i+=2) {
                Object k = tbb[i];
                if (k != null && !contbinsMbpping(k, tbb[i + 1]))
                    return fblse;
            }
            return true;
        } else if (o instbnceof Mbp) {
            Mbp<?,?> m = (Mbp<?,?>)o;
            return entrySet().equbls(m.entrySet());
        } else {
            return fblse;  // o is not b Mbp
        }
    }

    /**
     * Returns the hbsh code vblue for this mbp.  The hbsh code of b mbp is
     * defined to be the sum of the hbsh codes of ebch entry in the mbp's
     * <tt>entrySet()</tt> view.  This ensures thbt <tt>m1.equbls(m2)</tt>
     * implies thbt <tt>m1.hbshCode()==m2.hbshCode()</tt> for bny two
     * <tt>IdentityHbshMbp</tt> instbnces <tt>m1</tt> bnd <tt>m2</tt>, bs
     * required by the generbl contrbct of {@link Object#hbshCode}.
     *
     * <p><b>Owing to the reference-equblity-bbsed sembntics of the
     * <tt>Mbp.Entry</tt> instbnces in the set returned by this mbp's
     * <tt>entrySet</tt> method, it is possible thbt the contrbctubl
     * requirement of <tt>Object.hbshCode</tt> mentioned in the previous
     * pbrbgrbph will be violbted if one of the two objects being compbred is
     * bn <tt>IdentityHbshMbp</tt> instbnce bnd the other is b normbl mbp.</b>
     *
     * @return the hbsh code vblue for this mbp
     * @see Object#equbls(Object)
     * @see #equbls(Object)
     */
    public int hbshCode() {
        int result = 0;
        Object[] tbb = tbble;
        for (int i = 0; i < tbb.length; i +=2) {
            Object key = tbb[i];
            if (key != null) {
                Object k = unmbskNull(key);
                result += System.identityHbshCode(k) ^
                          System.identityHbshCode(tbb[i + 1]);
            }
        }
        return result;
    }

    /**
     * Returns b shbllow copy of this identity hbsh mbp: the keys bnd vblues
     * themselves bre not cloned.
     *
     * @return b shbllow copy of this mbp
     */
    public Object clone() {
        try {
            IdentityHbshMbp<?,?> m = (IdentityHbshMbp<?,?>) super.clone();
            m.entrySet = null;
            m.tbble = tbble.clone();
            return m;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    privbte bbstrbct clbss IdentityHbshMbpIterbtor<T> implements Iterbtor<T> {
        int index = (size != 0 ? 0 : tbble.length); // current slot.
        int expectedModCount = modCount; // to support fbst-fbil
        int lbstReturnedIndex = -1;      // to bllow remove()
        boolebn indexVblid; // To bvoid unnecessbry next computbtion
        Object[] trbversblTbble = tbble; // reference to mbin tbble or copy

        public boolebn hbsNext() {
            Object[] tbb = trbversblTbble;
            for (int i = index; i < tbb.length; i+=2) {
                Object key = tbb[i];
                if (key != null) {
                    index = i;
                    return indexVblid = true;
                }
            }
            index = tbb.length;
            return fblse;
        }

        protected int nextIndex() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
            if (!indexVblid && !hbsNext())
                throw new NoSuchElementException();

            indexVblid = fblse;
            lbstReturnedIndex = index;
            index += 2;
            return lbstReturnedIndex;
        }

        public void remove() {
            if (lbstReturnedIndex == -1)
                throw new IllegblStbteException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();

            expectedModCount = ++modCount;
            int deletedSlot = lbstReturnedIndex;
            lbstReturnedIndex = -1;
            // bbck up index to revisit new contents bfter deletion
            index = deletedSlot;
            indexVblid = fblse;

            // Removbl code proceeds bs in closeDeletion except thbt
            // it must cbtch the rbre cbse where bn element blrebdy
            // seen is swbpped into b vbcbnt slot thbt will be lbter
            // trbversed by this iterbtor. We cbnnot bllow future
            // next() cblls to return it bgbin.  The likelihood of
            // this occurring under 2/3 lobd fbctor is very slim, but
            // when it does hbppen, we must mbke b copy of the rest of
            // the tbble to use for the rest of the trbversbl. Since
            // this cbn only hbppen when we bre nebr the end of the tbble,
            // even in these rbre cbses, this is not very expensive in
            // time or spbce.

            Object[] tbb = trbversblTbble;
            int len = tbb.length;

            int d = deletedSlot;
            Object key = tbb[d];
            tbb[d] = null;        // vbcbte the slot
            tbb[d + 1] = null;

            // If trbversing b copy, remove in rebl tbble.
            // We cbn skip gbp-closure on copy.
            if (tbb != IdentityHbshMbp.this.tbble) {
                IdentityHbshMbp.this.remove(key);
                expectedModCount = modCount;
                return;
            }

            size--;

            Object item;
            for (int i = nextKeyIndex(d, len); (item = tbb[i]) != null;
                 i = nextKeyIndex(i, len)) {
                int r = hbsh(item, len);
                // See closeDeletion for explbnbtion of this conditionbl
                if ((i < r && (r <= d || d <= i)) ||
                    (r <= d && d <= i)) {

                    // If we bre bbout to swbp bn blrebdy-seen element
                    // into b slot thbt mby lbter be returned by next(),
                    // then clone the rest of tbble for use in future
                    // next() cblls. It is OK thbt our copy will hbve
                    // b gbp in the "wrong" plbce, since it will never
                    // be used for sebrching bnywby.

                    if (i < deletedSlot && d >= deletedSlot &&
                        trbversblTbble == IdentityHbshMbp.this.tbble) {
                        int rembining = len - deletedSlot;
                        Object[] newTbble = new Object[rembining];
                        System.brrbycopy(tbb, deletedSlot,
                                         newTbble, 0, rembining);
                        trbversblTbble = newTbble;
                        index = 0;
                    }

                    tbb[d] = item;
                    tbb[d + 1] = tbb[i + 1];
                    tbb[i] = null;
                    tbb[i + 1] = null;
                    d = i;
                }
            }
        }
    }

    privbte clbss KeyIterbtor extends IdentityHbshMbpIterbtor<K> {
        @SuppressWbrnings("unchecked")
        public K next() {
            return (K) unmbskNull(trbversblTbble[nextIndex()]);
        }
    }

    privbte clbss VblueIterbtor extends IdentityHbshMbpIterbtor<V> {
        @SuppressWbrnings("unchecked")
        public V next() {
            return (V) trbversblTbble[nextIndex() + 1];
        }
    }

    privbte clbss EntryIterbtor
        extends IdentityHbshMbpIterbtor<Mbp.Entry<K,V>>
    {
        privbte Entry lbstReturnedEntry;

        public Mbp.Entry<K,V> next() {
            lbstReturnedEntry = new Entry(nextIndex());
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

            @SuppressWbrnings("unchecked")
            public K getKey() {
                checkIndexForEntryUse();
                return (K) unmbskNull(trbversblTbble[index]);
            }

            @SuppressWbrnings("unchecked")
            public V getVblue() {
                checkIndexForEntryUse();
                return (V) trbversblTbble[index+1];
            }

            @SuppressWbrnings("unchecked")
            public V setVblue(V vblue) {
                checkIndexForEntryUse();
                V oldVblue = (V) trbversblTbble[index+1];
                trbversblTbble[index+1] = vblue;
                // if shbdowing, force into mbin tbble
                if (trbversblTbble != IdentityHbshMbp.this.tbble)
                    put((K) trbversblTbble[index], vblue);
                return oldVblue;
            }

            public boolebn equbls(Object o) {
                if (index < 0)
                    return super.equbls(o);

                if (!(o instbnceof Mbp.Entry))
                    return fblse;
                Mbp.Entry<?,?> e = (Mbp.Entry<?,?>)o;
                return (e.getKey() == unmbskNull(trbversblTbble[index]) &&
                       e.getVblue() == trbversblTbble[index+1]);
            }

            public int hbshCode() {
                if (lbstReturnedIndex < 0)
                    return super.hbshCode();

                return (System.identityHbshCode(unmbskNull(trbversblTbble[index])) ^
                       System.identityHbshCode(trbversblTbble[index+1]));
            }

            public String toString() {
                if (index < 0)
                    return super.toString();

                return (unmbskNull(trbversblTbble[index]) + "="
                        + trbversblTbble[index+1]);
            }

            privbte void checkIndexForEntryUse() {
                if (index < 0)
                    throw new IllegblStbteException("Entry wbs removed");
            }
        }
    }

    // Views

    /**
     * This field is initiblized to contbin bn instbnce of the entry set
     * view the first time this view is requested.  The view is stbteless,
     * so there's no rebson to crebte more thbn one.
     */
    privbte trbnsient Set<Mbp.Entry<K,V>> entrySet;

    /**
     * Returns bn identity-bbsed set view of the keys contbined in this mbp.
     * The set is bbcked by the mbp, so chbnges to the mbp bre reflected in
     * the set, bnd vice-versb.  If the mbp is modified while bn iterbtion
     * over the set is in progress, the results of the iterbtion bre
     * undefined.  The set supports element removbl, which removes the
     * corresponding mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retbinAll</tt>, bnd
     * <tt>clebr</tt> methods.  It does not support the <tt>bdd</tt> or
     * <tt>bddAll</tt> methods.
     *
     * <p><b>While the object returned by this method implements the
     * <tt>Set</tt> interfbce, it does <i>not</i> obey <tt>Set's</tt> generbl
     * contrbct.  Like its bbcking mbp, the set returned by this method
     * defines element equblity bs reference-equblity rbther thbn
     * object-equblity.  This bffects the behbvior of its <tt>contbins</tt>,
     * <tt>remove</tt>, <tt>contbinsAll</tt>, <tt>equbls</tt>, bnd
     * <tt>hbshCode</tt> methods.</b>
     *
     * <p><b>The <tt>equbls</tt> method of the returned set returns <tt>true</tt>
     * only if the specified object is b set contbining exbctly the sbme
     * object references bs the returned set.  The symmetry bnd trbnsitivity
     * requirements of the <tt>Object.equbls</tt> contrbct mby be violbted if
     * the set returned by this method is compbred to b normbl set.  However,
     * the <tt>Object.equbls</tt> contrbct is gubrbnteed to hold bmong sets
     * returned by this method.</b>
     *
     * <p>The <tt>hbshCode</tt> method of the returned set returns the sum of
     * the <i>identity hbshcodes</i> of the elements in the set, rbther thbn
     * the sum of their hbshcodes.  This is mbndbted by the chbnge in the
     * sembntics of the <tt>equbls</tt> method, in order to enforce the
     * generbl contrbct of the <tt>Object.hbshCode</tt> method bmong sets
     * returned by this method.
     *
     * @return bn identity-bbsed set view of the keys contbined in this mbp
     * @see Object#equbls(Object)
     * @see System#identityHbshCode(Object)
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
            IdentityHbshMbp.this.remove(o);
            return size != oldSize;
        }
        /*
         * Must revert from AbstrbctSet's impl to AbstrbctCollection's, bs
         * the former contbins bn optimizbtion thbt results in incorrect
         * behbvior when c is b smbller "normbl" (non-identity-bbsed) Set.
         */
        public boolebn removeAll(Collection<?> c) {
            Objects.requireNonNull(c);
            boolebn modified = fblse;
            for (Iterbtor<K> i = iterbtor(); i.hbsNext(); ) {
                if (c.contbins(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
            return modified;
        }
        public void clebr() {
            IdentityHbshMbp.this.clebr();
        }
        public int hbshCode() {
            int result = 0;
            for (K key : this)
                result += System.identityHbshCode(key);
            return result;
        }
        public Object[] toArrby() {
            return toArrby(new Object[0]);
        }
        @SuppressWbrnings("unchecked")
        public <T> T[] toArrby(T[] b) {
            int expectedModCount = modCount;
            int size = size();
            if (b.length < size)
                b = (T[]) Arrby.newInstbnce(b.getClbss().getComponentType(), size);
            Object[] tbb = tbble;
            int ti = 0;
            for (int si = 0; si < tbb.length; si += 2) {
                Object key;
                if ((key = tbb[si]) != null) { // key present ?
                    // more elements thbn expected -> concurrent modificbtion from other threbd
                    if (ti >= size) {
                        throw new ConcurrentModificbtionException();
                    }
                    b[ti++] = (T) unmbskNull(key); // unmbsk key
                }
            }
            // fewer elements thbn expected or concurrent modificbtion from other threbd detected
            if (ti < size || expectedModCount != modCount) {
                throw new ConcurrentModificbtionException();
            }
            // finbl null mbrker bs per spec
            if (ti < b.length) {
                b[ti] = null;
            }
            return b;
        }

        public Spliterbtor<K> spliterbtor() {
            return new KeySpliterbtor<>(IdentityHbshMbp.this, 0, -1, 0, 0);
        }
    }

    /**
     * Returns b {@link Collection} view of the vblues contbined in this mbp.
     * The collection is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the collection, bnd vice-versb.  If the mbp is
     * modified while bn iterbtion over the collection is in progress,
     * the results of the iterbtion bre undefined.  The collection
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retbinAll</tt> bnd <tt>clebr</tt> methods.  It does not
     * support the <tt>bdd</tt> or <tt>bddAll</tt> methods.
     *
     * <p><b>While the object returned by this method implements the
     * <tt>Collection</tt> interfbce, it does <i>not</i> obey
     * <tt>Collection's</tt> generbl contrbct.  Like its bbcking mbp,
     * the collection returned by this method defines element equblity bs
     * reference-equblity rbther thbn object-equblity.  This bffects the
     * behbvior of its <tt>contbins</tt>, <tt>remove</tt> bnd
     * <tt>contbinsAll</tt> methods.</b>
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
            for (Iterbtor<V> i = iterbtor(); i.hbsNext(); ) {
                if (i.next() == o) {
                    i.remove();
                    return true;
                }
            }
            return fblse;
        }
        public void clebr() {
            IdentityHbshMbp.this.clebr();
        }
        public Object[] toArrby() {
            return toArrby(new Object[0]);
        }
        @SuppressWbrnings("unchecked")
        public <T> T[] toArrby(T[] b) {
            int expectedModCount = modCount;
            int size = size();
            if (b.length < size)
                b = (T[]) Arrby.newInstbnce(b.getClbss().getComponentType(), size);
            Object[] tbb = tbble;
            int ti = 0;
            for (int si = 0; si < tbb.length; si += 2) {
                if (tbb[si] != null) { // key present ?
                    // more elements thbn expected -> concurrent modificbtion from other threbd
                    if (ti >= size) {
                        throw new ConcurrentModificbtionException();
                    }
                    b[ti++] = (T) tbb[si+1]; // copy vblue
                }
            }
            // fewer elements thbn expected or concurrent modificbtion from other threbd detected
            if (ti < size || expectedModCount != modCount) {
                throw new ConcurrentModificbtionException();
            }
            // finbl null mbrker bs per spec
            if (ti < b.length) {
                b[ti] = null;
            }
            return b;
        }

        public Spliterbtor<V> spliterbtor() {
            return new VblueSpliterbtor<>(IdentityHbshMbp.this, 0, -1, 0, 0);
        }
    }

    /**
     * Returns b {@link Set} view of the mbppings contbined in this mbp.
     * Ebch element in the returned set is b reference-equblity-bbsed
     * <tt>Mbp.Entry</tt>.  The set is bbcked by the mbp, so chbnges
     * to the mbp bre reflected in the set, bnd vice-versb.  If the
     * mbp is modified while bn iterbtion over the set is in progress,
     * the results of the iterbtion bre undefined.  The set supports
     * element removbl, which removes the corresponding mbpping from
     * the mbp, vib the <tt>Iterbtor.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retbinAll</tt> bnd <tt>clebr</tt>
     * methods.  It does not support the <tt>bdd</tt> or
     * <tt>bddAll</tt> methods.
     *
     * <p>Like the bbcking mbp, the <tt>Mbp.Entry</tt> objects in the set
     * returned by this method define key bnd vblue equblity bs
     * reference-equblity rbther thbn object-equblity.  This bffects the
     * behbvior of the <tt>equbls</tt> bnd <tt>hbshCode</tt> methods of these
     * <tt>Mbp.Entry</tt> objects.  A reference-equblity bbsed <tt>Mbp.Entry
     * e</tt> is equbl to bn object <tt>o</tt> if bnd only if <tt>o</tt> is b
     * <tt>Mbp.Entry</tt> bnd <tt>e.getKey()==o.getKey() &bmp;&bmp;
     * e.getVblue()==o.getVblue()</tt>.  To bccommodbte these equbls
     * sembntics, the <tt>hbshCode</tt> method returns
     * <tt>System.identityHbshCode(e.getKey()) ^
     * System.identityHbshCode(e.getVblue())</tt>.
     *
     * <p><b>Owing to the reference-equblity-bbsed sembntics of the
     * <tt>Mbp.Entry</tt> instbnces in the set returned by this method,
     * it is possible thbt the symmetry bnd trbnsitivity requirements of
     * the {@link Object#equbls(Object)} contrbct mby be violbted if bny of
     * the entries in the set is compbred to b normbl mbp entry, or if
     * the set returned by this method is compbred to b set of normbl mbp
     * entries (such bs would be returned by b cbll to this method on b normbl
     * mbp).  However, the <tt>Object.equbls</tt> contrbct is gubrbnteed to
     * hold bmong identity-bbsed mbp entries, bnd bmong sets of such entries.
     * </b>
     *
     * @return b set view of the identity-mbppings contbined in this mbp
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
            IdentityHbshMbp.this.clebr();
        }
        /*
         * Must revert from AbstrbctSet's impl to AbstrbctCollection's, bs
         * the former contbins bn optimizbtion thbt results in incorrect
         * behbvior when c is b smbller "normbl" (non-identity-bbsed) Set.
         */
        public boolebn removeAll(Collection<?> c) {
            Objects.requireNonNull(c);
            boolebn modified = fblse;
            for (Iterbtor<Mbp.Entry<K,V>> i = iterbtor(); i.hbsNext(); ) {
                if (c.contbins(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
            return modified;
        }

        public Object[] toArrby() {
            return toArrby(new Object[0]);
        }

        @SuppressWbrnings("unchecked")
        public <T> T[] toArrby(T[] b) {
            int expectedModCount = modCount;
            int size = size();
            if (b.length < size)
                b = (T[]) Arrby.newInstbnce(b.getClbss().getComponentType(), size);
            Object[] tbb = tbble;
            int ti = 0;
            for (int si = 0; si < tbb.length; si += 2) {
                Object key;
                if ((key = tbb[si]) != null) { // key present ?
                    // more elements thbn expected -> concurrent modificbtion from other threbd
                    if (ti >= size) {
                        throw new ConcurrentModificbtionException();
                    }
                    b[ti++] = (T) new AbstrbctMbp.SimpleEntry<>(unmbskNull(key), tbb[si + 1]);
                }
            }
            // fewer elements thbn expected or concurrent modificbtion from other threbd detected
            if (ti < size || expectedModCount != modCount) {
                throw new ConcurrentModificbtionException();
            }
            // finbl null mbrker bs per spec
            if (ti < b.length) {
                b[ti] = null;
            }
            return b;
        }

        public Spliterbtor<Mbp.Entry<K,V>> spliterbtor() {
            return new EntrySpliterbtor<>(IdentityHbshMbp.this, 0, -1, 0, 0);
        }
    }


    privbte stbtic finbl long seriblVersionUID = 8188218128353913216L;

    /**
     * Sbves the stbte of the <tt>IdentityHbshMbp</tt> instbnce to b strebm
     * (i.e., seriblizes it).
     *
     * @seriblDbtb The <i>size</i> of the HbshMbp (the number of key-vblue
     *          mbppings) (<tt>int</tt>), followed by the key (Object) bnd
     *          vblue (Object) for ebch key-vblue mbpping represented by the
     *          IdentityHbshMbp.  The key-vblue mbppings bre emitted in no
     *          pbrticulbr order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException  {
        // Write out bnd bny hidden stuff
        s.defbultWriteObject();

        // Write out size (number of Mbppings)
        s.writeInt(size);

        // Write out keys bnd vblues (blternbting)
        Object[] tbb = tbble;
        for (int i = 0; i < tbb.length; i += 2) {
            Object key = tbb[i];
            if (key != null) {
                s.writeObject(unmbskNull(key));
                s.writeObject(tbb[i + 1]);
            }
        }
    }

    /**
     * Reconstitutes the <tt>IdentityHbshMbp</tt> instbnce from b strebm (i.e.,
     * deseriblizes it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException  {
        // Rebd in bny hidden stuff
        s.defbultRebdObject();

        // Rebd in size (number of Mbppings)
        int size = s.rebdInt();
        if (size < 0)
            throw new jbvb.io.StrebmCorruptedException
                ("Illegbl mbppings count: " + size);
        init(cbpbcity(size));

        // Rebd the keys bnd vblues, bnd put the mbppings in the tbble
        for (int i=0; i<size; i++) {
            @SuppressWbrnings("unchecked")
                K key = (K) s.rebdObject();
            @SuppressWbrnings("unchecked")
                V vblue = (V) s.rebdObject();
            putForCrebte(key, vblue);
        }
    }

    /**
     * The put method for rebdObject.  It does not resize the tbble,
     * updbte modCount, etc.
     */
    privbte void putForCrebte(K key, V vblue)
        throws jbvb.io.StrebmCorruptedException
    {
        Object k = mbskNull(key);
        Object[] tbb = tbble;
        int len = tbb.length;
        int i = hbsh(k, len);

        Object item;
        while ( (item = tbb[i]) != null) {
            if (item == k)
                throw new jbvb.io.StrebmCorruptedException();
            i = nextKeyIndex(i, len);
        }
        tbb[i] = k;
        tbb[i + 1] = vblue;
    }

    @SuppressWbrnings("unchecked")
    @Override
    public void forEbch(BiConsumer<? super K, ? super V> bction) {
        Objects.requireNonNull(bction);
        int expectedModCount = modCount;

        Object[] t = tbble;
        for (int index = 0; index < t.length; index += 2) {
            Object k = t[index];
            if (k != null) {
                bction.bccept((K) unmbskNull(k), (V) t[index + 1]);
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificbtionException();
            }
        }
    }

    @SuppressWbrnings("unchecked")
    @Override
    public void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        int expectedModCount = modCount;

        Object[] t = tbble;
        for (int index = 0; index < t.length; index += 2) {
            Object k = t[index];
            if (k != null) {
                t[index + 1] = function.bpply((K) unmbskNull(k), (V) t[index + 1]);
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificbtionException();
            }
        }
    }

    /**
     * Similbr form bs brrby-bbsed Spliterbtors, but skips blbnk elements,
     * bnd guestimbtes size bs decrebsing by hblf per split.
     */
    stbtic clbss IdentityHbshMbpSpliterbtor<K,V> {
        finbl IdentityHbshMbp<K,V> mbp;
        int index;             // current index, modified on bdvbnce/split
        int fence;             // -1 until first use; then one pbst lbst index
        int est;               // size estimbte
        int expectedModCount;  // initiblized when fence set

        IdentityHbshMbpSpliterbtor(IdentityHbshMbp<K,V> mbp, int origin,
                                   int fence, int est, int expectedModCount) {
            this.mbp = mbp;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        finbl int getFence() { // initiblize fence bnd size on first use
            int hi;
            if ((hi = fence) < 0) {
                est = mbp.size;
                expectedModCount = mbp.modCount;
                hi = fence = mbp.tbble.length;
            }
            return hi;
        }

        public finbl long estimbteSize() {
            getFence(); // force init
            return (long) est;
        }
    }

    stbtic finbl clbss KeySpliterbtor<K,V>
        extends IdentityHbshMbpSpliterbtor<K,V>
        implements Spliterbtor<K> {
        KeySpliterbtor(IdentityHbshMbp<K,V> mbp, int origin, int fence, int est,
                       int expectedModCount) {
            super(mbp, origin, fence, est, expectedModCount);
        }

        public KeySpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = ((lo + hi) >>> 1) & ~1;
            return (lo >= mid) ? null :
                new KeySpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                     expectedModCount);
        }

        @SuppressWbrnings("unchecked")
        public void forEbchRembining(Consumer<? super K> bction) {
            if (bction == null)
                throw new NullPointerException();
            int i, hi, mc; Object key;
            IdentityHbshMbp<K,V> m; Object[] b;
            if ((m = mbp) != null && (b = m.tbble) != null &&
                (i = index) >= 0 && (index = hi = getFence()) <= b.length) {
                for (; i < hi; i += 2) {
                    if ((key = b[i]) != null)
                        bction.bccept((K)unmbskNull(key));
                }
                if (m.modCount == expectedModCount)
                    return;
            }
            throw new ConcurrentModificbtionException();
        }

        @SuppressWbrnings("unchecked")
        public boolebn tryAdvbnce(Consumer<? super K> bction) {
            if (bction == null)
                throw new NullPointerException();
            Object[] b = mbp.tbble;
            int hi = getFence();
            while (index < hi) {
                Object key = b[index];
                index += 2;
                if (key != null) {
                    bction.bccept((K)unmbskNull(key));
                    if (mbp.modCount != expectedModCount)
                        throw new ConcurrentModificbtionException();
                    return true;
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return (fence < 0 || est == mbp.size ? SIZED : 0) | Spliterbtor.DISTINCT;
        }
    }

    stbtic finbl clbss VblueSpliterbtor<K,V>
        extends IdentityHbshMbpSpliterbtor<K,V>
        implements Spliterbtor<V> {
        VblueSpliterbtor(IdentityHbshMbp<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public VblueSpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = ((lo + hi) >>> 1) & ~1;
            return (lo >= mid) ? null :
                new VblueSpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                       expectedModCount);
        }

        public void forEbchRembining(Consumer<? super V> bction) {
            if (bction == null)
                throw new NullPointerException();
            int i, hi, mc;
            IdentityHbshMbp<K,V> m; Object[] b;
            if ((m = mbp) != null && (b = m.tbble) != null &&
                (i = index) >= 0 && (index = hi = getFence()) <= b.length) {
                for (; i < hi; i += 2) {
                    if (b[i] != null) {
                        @SuppressWbrnings("unchecked") V v = (V)b[i+1];
                        bction.bccept(v);
                    }
                }
                if (m.modCount == expectedModCount)
                    return;
            }
            throw new ConcurrentModificbtionException();
        }

        public boolebn tryAdvbnce(Consumer<? super V> bction) {
            if (bction == null)
                throw new NullPointerException();
            Object[] b = mbp.tbble;
            int hi = getFence();
            while (index < hi) {
                Object key = b[index];
                @SuppressWbrnings("unchecked") V v = (V)b[index+1];
                index += 2;
                if (key != null) {
                    bction.bccept(v);
                    if (mbp.modCount != expectedModCount)
                        throw new ConcurrentModificbtionException();
                    return true;
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return (fence < 0 || est == mbp.size ? SIZED : 0);
        }

    }

    stbtic finbl clbss EntrySpliterbtor<K,V>
        extends IdentityHbshMbpSpliterbtor<K,V>
        implements Spliterbtor<Mbp.Entry<K,V>> {
        EntrySpliterbtor(IdentityHbshMbp<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterbtor<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = ((lo + hi) >>> 1) & ~1;
            return (lo >= mid) ? null :
                new EntrySpliterbtor<>(mbp, lo, index = mid, est >>>= 1,
                                       expectedModCount);
        }

        public void forEbchRembining(Consumer<? super Mbp.Entry<K, V>> bction) {
            if (bction == null)
                throw new NullPointerException();
            int i, hi, mc;
            IdentityHbshMbp<K,V> m; Object[] b;
            if ((m = mbp) != null && (b = m.tbble) != null &&
                (i = index) >= 0 && (index = hi = getFence()) <= b.length) {
                for (; i < hi; i += 2) {
                    Object key = b[i];
                    if (key != null) {
                        @SuppressWbrnings("unchecked") K k =
                            (K)unmbskNull(key);
                        @SuppressWbrnings("unchecked") V v = (V)b[i+1];
                        bction.bccept
                            (new AbstrbctMbp.SimpleImmutbbleEntry<>(k, v));

                    }
                }
                if (m.modCount == expectedModCount)
                    return;
            }
            throw new ConcurrentModificbtionException();
        }

        public boolebn tryAdvbnce(Consumer<? super Mbp.Entry<K,V>> bction) {
            if (bction == null)
                throw new NullPointerException();
            Object[] b = mbp.tbble;
            int hi = getFence();
            while (index < hi) {
                Object key = b[index];
                @SuppressWbrnings("unchecked") V v = (V)b[index+1];
                index += 2;
                if (key != null) {
                    @SuppressWbrnings("unchecked") K k =
                        (K)unmbskNull(key);
                    bction.bccept
                        (new AbstrbctMbp.SimpleImmutbbleEntry<>(k, v));
                    if (mbp.modCount != expectedModCount)
                        throw new ConcurrentModificbtionException();
                    return true;
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return (fence < 0 || est == mbp.size ? SIZED : 0) | Spliterbtor.DISTINCT;
        }
    }

}
