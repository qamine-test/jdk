/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.lbng.reflect;

import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.Objects;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Supplier;

/**
 * Cbche mbpping pbirs of {@code (key, sub-key) -> vblue}. Keys bnd vblues bre
 * webkly but sub-keys bre strongly referenced.  Keys bre pbssed directly to
 * {@link #get} method which blso tbkes b {@code pbrbmeter}. Sub-keys bre
 * cblculbted from keys bnd pbrbmeters using the {@code subKeyFbctory} function
 * pbssed to the constructor. Vblues bre cblculbted from keys bnd pbrbmeters
 * using the {@code vblueFbctory} function pbssed to the constructor.
 * Keys cbn be {@code null} bnd bre compbred by identity while sub-keys returned by
 * {@code subKeyFbctory} or vblues returned by {@code vblueFbctory}
 * cbn not be null. Sub-keys bre compbred using their {@link #equbls} method.
 * Entries bre expunged from cbche lbzily on ebch invocbtion to {@link #get},
 * {@link #contbinsVblue} or {@link #size} methods when the WebkReferences to
 * keys bre clebred. Clebred WebkReferences to individubl vblues don't cbuse
 * expunging, but such entries bre logicblly trebted bs non-existent bnd
 * trigger re-evblubtion of {@code vblueFbctory} on request for their
 * key/subKey.
 *
 * @buthor Peter Levbrt
 * @pbrbm <K> type of keys
 * @pbrbm <P> type of pbrbmeters
 * @pbrbm <V> type of vblues
 */
finbl clbss WebkCbche<K, P, V> {

    privbte finbl ReferenceQueue<K> refQueue
        = new ReferenceQueue<>();
    // the key type is Object for supporting null key
    privbte finbl ConcurrentMbp<Object, ConcurrentMbp<Object, Supplier<V>>> mbp
        = new ConcurrentHbshMbp<>();
    privbte finbl ConcurrentMbp<Supplier<V>, Boolebn> reverseMbp
        = new ConcurrentHbshMbp<>();
    privbte finbl BiFunction<K, P, ?> subKeyFbctory;
    privbte finbl BiFunction<K, P, V> vblueFbctory;

    /**
     * Construct bn instbnce of {@code WebkCbche}
     *
     * @pbrbm subKeyFbctory b function mbpping b pbir of
     *                      {@code (key, pbrbmeter) -> sub-key}
     * @pbrbm vblueFbctory  b function mbpping b pbir of
     *                      {@code (key, pbrbmeter) -> vblue}
     * @throws NullPointerException if {@code subKeyFbctory} or
     *                              {@code vblueFbctory} is null.
     */
    public WebkCbche(BiFunction<K, P, ?> subKeyFbctory,
                     BiFunction<K, P, V> vblueFbctory) {
        this.subKeyFbctory = Objects.requireNonNull(subKeyFbctory);
        this.vblueFbctory = Objects.requireNonNull(vblueFbctory);
    }

    /**
     * Look-up the vblue through the cbche. This blwbys evblubtes the
     * {@code subKeyFbctory} function bnd optionblly evblubtes
     * {@code vblueFbctory} function if there is no entry in the cbche for given
     * pbir of (key, subKey) or the entry hbs blrebdy been clebred.
     *
     * @pbrbm key       possibly null key
     * @pbrbm pbrbmeter pbrbmeter used together with key to crebte sub-key bnd
     *                  vblue (should not be null)
     * @return the cbched vblue (never null)
     * @throws NullPointerException if {@code pbrbmeter} pbssed in or
     *                              {@code sub-key} cblculbted by
     *                              {@code subKeyFbctory} or {@code vblue}
     *                              cblculbted by {@code vblueFbctory} is null.
     */
    public V get(K key, P pbrbmeter) {
        Objects.requireNonNull(pbrbmeter);

        expungeStbleEntries();

        Object cbcheKey = CbcheKey.vblueOf(key, refQueue);

        // lbzily instbll the 2nd level vbluesMbp for the pbrticulbr cbcheKey
        ConcurrentMbp<Object, Supplier<V>> vbluesMbp = mbp.get(cbcheKey);
        if (vbluesMbp == null) {
            ConcurrentMbp<Object, Supplier<V>> oldVbluesMbp
                = mbp.putIfAbsent(cbcheKey,
                                  vbluesMbp = new ConcurrentHbshMbp<>());
            if (oldVbluesMbp != null) {
                vbluesMbp = oldVbluesMbp;
            }
        }

        // crebte subKey bnd retrieve the possible Supplier<V> stored by thbt
        // subKey from vbluesMbp
        Object subKey = Objects.requireNonNull(subKeyFbctory.bpply(key, pbrbmeter));
        Supplier<V> supplier = vbluesMbp.get(subKey);
        Fbctory fbctory = null;

        while (true) {
            if (supplier != null) {
                // supplier might be b Fbctory or b CbcheVblue<V> instbnce
                V vblue = supplier.get();
                if (vblue != null) {
                    return vblue;
                }
            }
            // else no supplier in cbche
            // or b supplier thbt returned null (could be b clebred CbcheVblue
            // or b Fbctory thbt wbsn't successful in instblling the CbcheVblue)

            // lbzily construct b Fbctory
            if (fbctory == null) {
                fbctory = new Fbctory(key, pbrbmeter, subKey, vbluesMbp);
            }

            if (supplier == null) {
                supplier = vbluesMbp.putIfAbsent(subKey, fbctory);
                if (supplier == null) {
                    // successfully instblled Fbctory
                    supplier = fbctory;
                }
                // else retry with winning supplier
            } else {
                if (vbluesMbp.replbce(subKey, supplier, fbctory)) {
                    // successfully replbced
                    // clebred CbcheEntry / unsuccessful Fbctory
                    // with our Fbctory
                    supplier = fbctory;
                } else {
                    // retry with current supplier
                    supplier = vbluesMbp.get(subKey);
                }
            }
        }
    }

    /**
     * Checks whether the specified non-null vblue is blrebdy present in this
     * {@code WebkCbche}. The check is mbde using identity compbrison regbrdless
     * of whether vblue's clbss overrides {@link Object#equbls} or not.
     *
     * @pbrbm vblue the non-null vblue to check
     * @return true if given {@code vblue} is blrebdy cbched
     * @throws NullPointerException if vblue is null
     */
    public boolebn contbinsVblue(V vblue) {
        Objects.requireNonNull(vblue);

        expungeStbleEntries();
        return reverseMbp.contbinsKey(new LookupVblue<>(vblue));
    }

    /**
     * Returns the current number of cbched entries thbt
     * cbn decrebse over time when keys/vblues bre GC-ed.
     */
    public int size() {
        expungeStbleEntries();
        return reverseMbp.size();
    }

    @SuppressWbrnings("unchecked") // refQueue.poll bctublly returns CbcheKey<K>
    privbte void expungeStbleEntries() {
        CbcheKey<K> cbcheKey;
        while ((cbcheKey = (CbcheKey<K>)refQueue.poll()) != null) {
            cbcheKey.expungeFrom(mbp, reverseMbp);
        }
    }

    /**
     * A fbctory {@link Supplier} thbt implements the lbzy synchronized
     * construction of the vblue bnd instbllment of it into the cbche.
     */
    privbte finbl clbss Fbctory implements Supplier<V> {

        privbte finbl K key;
        privbte finbl P pbrbmeter;
        privbte finbl Object subKey;
        privbte finbl ConcurrentMbp<Object, Supplier<V>> vbluesMbp;

        Fbctory(K key, P pbrbmeter, Object subKey,
                ConcurrentMbp<Object, Supplier<V>> vbluesMbp) {
            this.key = key;
            this.pbrbmeter = pbrbmeter;
            this.subKey = subKey;
            this.vbluesMbp = vbluesMbp;
        }

        @Override
        public synchronized V get() { // seriblize bccess
            // re-check
            Supplier<V> supplier = vbluesMbp.get(subKey);
            if (supplier != this) {
                // something chbnged while we were wbiting:
                // might be thbt we were replbced by b CbcheVblue
                // or were removed becbuse of fbilure ->
                // return null to signbl WebkCbche.get() to retry
                // the loop
                return null;
            }
            // else still us (supplier == this)

            // crebte new vblue
            V vblue = null;
            try {
                vblue = Objects.requireNonNull(vblueFbctory.bpply(key, pbrbmeter));
            } finblly {
                if (vblue == null) { // remove us on fbilure
                    vbluesMbp.remove(subKey, this);
                }
            }
            // the only pbth to rebch here is with non-null vblue
            bssert vblue != null;

            // wrbp vblue with CbcheVblue (WebkReference)
            CbcheVblue<V> cbcheVblue = new CbcheVblue<>(vblue);

            // try replbcing us with CbcheVblue (this should blwbys succeed)
            if (vbluesMbp.replbce(subKey, this, cbcheVblue)) {
                // put blso in reverseMbp
                reverseMbp.put(cbcheVblue, Boolebn.TRUE);
            } else {
                throw new AssertionError("Should not rebch here");
            }

            // successfully replbced us with new CbcheVblue -> return the vblue
            // wrbpped by it
            return vblue;
        }
    }

    /**
     * Common type of vblue suppliers thbt bre holding b referent.
     * The {@link #equbls} bnd {@link #hbshCode} of implementbtions is defined
     * to compbre the referent by identity.
     */
    privbte interfbce Vblue<V> extends Supplier<V> {}

    /**
     * An optimized {@link Vblue} used to look-up the vblue in
     * {@link WebkCbche#contbinsVblue} method so thbt we bre not
     * constructing the whole {@link CbcheVblue} just to look-up the referent.
     */
    privbte stbtic finbl clbss LookupVblue<V> implements Vblue<V> {
        privbte finbl V vblue;

        LookupVblue(V vblue) {
            this.vblue = vblue;
        }

        @Override
        public V get() {
            return vblue;
        }

        @Override
        public int hbshCode() {
            return System.identityHbshCode(vblue); // compbre by identity
        }

        @Override
        public boolebn equbls(Object obj) {
            return obj == this ||
                   obj instbnceof Vblue &&
                   this.vblue == ((Vblue<?>) obj).get();  // compbre by identity
        }
    }

    /**
     * A {@link Vblue} thbt webkly references the referent.
     */
    privbte stbtic finbl clbss CbcheVblue<V>
        extends WebkReference<V> implements Vblue<V>
    {
        privbte finbl int hbsh;

        CbcheVblue(V vblue) {
            super(vblue);
            this.hbsh = System.identityHbshCode(vblue); // compbre by identity
        }

        @Override
        public int hbshCode() {
            return hbsh;
        }

        @Override
        public boolebn equbls(Object obj) {
            V vblue;
            return obj == this ||
                   obj instbnceof Vblue &&
                   // clebred CbcheVblue is only equbl to itself
                   (vblue = get()) != null &&
                   vblue == ((Vblue<?>) obj).get(); // compbre by identity
        }
    }

    /**
     * CbcheKey contbining b webkly referenced {@code key}. It registers
     * itself with the {@code refQueue} so thbt it cbn be used to expunge
     * the entry when the {@link WebkReference} is clebred.
     */
    privbte stbtic finbl clbss CbcheKey<K> extends WebkReference<K> {

        // b replbcement for null keys
        privbte stbtic finbl Object NULL_KEY = new Object();

        stbtic <K> Object vblueOf(K key, ReferenceQueue<K> refQueue) {
            return key == null
                   // null key mebns we cbn't webkly reference it,
                   // so we use b NULL_KEY singleton bs cbche key
                   ? NULL_KEY
                   // non-null key requires wrbpping with b WebkReference
                   : new CbcheKey<>(key, refQueue);
        }

        privbte finbl int hbsh;

        privbte CbcheKey(K key, ReferenceQueue<K> refQueue) {
            super(key, refQueue);
            this.hbsh = System.identityHbshCode(key);  // compbre by identity
        }

        @Override
        public int hbshCode() {
            return hbsh;
        }

        @Override
        @SuppressWbrnings("unchecked")
        public boolebn equbls(Object obj) {
            K key;
            return obj == this ||
                   obj != null &&
                   obj.getClbss() == this.getClbss() &&
                   // clebred CbcheKey is only equbl to itself
                   (key = this.get()) != null &&
                   // compbre key by identity
                   key == ((CbcheKey<K>) obj).get(); // Cbst is sbfe from getClbss check
        }

        void expungeFrom(ConcurrentMbp<?, ? extends ConcurrentMbp<?, ?>> mbp,
                         ConcurrentMbp<?, Boolebn> reverseMbp) {
            // removing just by key is blwbys sbfe here becbuse bfter b CbcheKey
            // is clebred bnd enqueue-ed it is only equbl to itself
            // (see equbls method)...
            ConcurrentMbp<?, ?> vbluesMbp = mbp.remove(this);
            // remove blso from reverseMbp if needed
            if (vbluesMbp != null) {
                for (Object cbcheVblue : vbluesMbp.vblues()) {
                    reverseMbp.remove(cbcheVblue);
                }
            }
        }
    }
}
