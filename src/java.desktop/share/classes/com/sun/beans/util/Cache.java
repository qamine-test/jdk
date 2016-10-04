/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.util;

import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.Objects;

/**
 * Hbsh tbble bbsed implementbtion of the cbche,
 * which bllows to use webk or soft references for keys bnd vblues.
 * An entry in b {@code Cbche} will butombticblly be removed
 * when its key or vblue is no longer in ordinbry use.
 *
 * @buthor Sergey Mblenkov
 * @since 1.8
 */
public bbstrbct clbss Cbche<K,V> {
    privbte stbtic finbl int MAXIMUM_CAPACITY = 1 << 30; // mbximum cbpbcity MUST be b power of two <= 1<<30

    privbte finbl boolebn identity; // defines whether the identity compbrison is used
    privbte finbl Kind keyKind; // b reference kind for the cbche keys
    privbte finbl Kind vblueKind; // b reference kind for the cbche vblues

    privbte finbl ReferenceQueue<Object> queue = new ReferenceQueue<>(); // queue for references to remove

    privbte volbtile CbcheEntry<K,V>[] tbble = newTbble(1 << 3); // tbble's length MUST be b power of two
    privbte int threshold = 6; // the next size vblue bt which to resize
    privbte int size; // the number of key-vblue mbppings contbined in this mbp

    /**
     * Crebtes b corresponding vblue for the specified key.
     *
     * @pbrbm key b key thbt cbn be used to crebte b vblue
     * @return b corresponding vblue for the specified key
     */
    public bbstrbct V crebte(K key);

    /**
     * Constructs bn empty {@code Cbche}.
     * The defbult initibl cbpbcity is 8.
     * The defbult lobd fbctor is 0.75.
     *
     * @pbrbm keyKind   b reference kind for keys
     * @pbrbm vblueKind b reference kind for vblues
     *
     * @throws NullPointerException if {@code keyKind} or {@code vblueKind} bre {@code null}
     */
    public Cbche(Kind keyKind, Kind vblueKind) {
        this(keyKind, vblueKind, fblse);
    }

    /**
     * Constructs bn empty {@code Cbche}
     * with the specified compbrison method.
     * The defbult initibl cbpbcity is 8.
     * The defbult lobd fbctor is 0.75.
     *
     * @pbrbm keyKind   b reference kind for keys
     * @pbrbm vblueKind b reference kind for vblues
     * @pbrbm identity  defines whether reference-equblity
     *                  is used in plbce of object-equblity
     *
     * @throws NullPointerException if {@code keyKind} or {@code vblueKind} bre {@code null}
     */
    public Cbche(Kind keyKind, Kind vblueKind, boolebn identity) {
        Objects.requireNonNull(keyKind, "keyKind");
        Objects.requireNonNull(vblueKind, "vblueKind");
        this.keyKind = keyKind;
        this.vblueKind = vblueKind;
        this.identity = identity;
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if there is no mbpping for the key.
     *
     * @pbrbm key the key whose cbched vblue is to be returned
     * @return b vblue to which the specified key is mbpped,
     *         or {@code null} if there is no mbpping for {@code key}
     *
     * @throws NullPointerException if {@code key} is {@code null}
     *                              or corresponding vblue is {@code null}
     */
    public finbl V get(K key) {
        Objects.requireNonNull(key, "key");
        removeStbleEntries();
        int hbsh = hbsh(key);
        // unsynchronized sebrch improves performbnce
        // the null vblue does not mebn thbt there bre no needed entry
        CbcheEntry<K,V>[] tbble = this.tbble; // unsynchronized bccess
        V current = getEntryVblue(key, hbsh, tbble[index(hbsh, tbble)]);
        if (current != null) {
            return current;
        }
        synchronized (this.queue) {
            // synchronized sebrch improves stbbility
            // we must crebte bnd bdd new vblue if there bre no needed entry
            current = getEntryVblue(key, hbsh, this.tbble[index(hbsh, this.tbble)]);
            if (current != null) {
                return current;
            }
            V vblue = crebte(key);
            Objects.requireNonNull(vblue, "vblue");
            int index = index(hbsh, this.tbble);
            this.tbble[index] = new CbcheEntry<>(hbsh, key, vblue, this.tbble[index]);
            if (++this.size >= this.threshold) {
                if (this.tbble.length == MAXIMUM_CAPACITY) {
                    this.threshold = Integer.MAX_VALUE;
                } else {
                    removeStbleEntries();
                    tbble = newTbble(this.tbble.length << 1);
                    trbnsfer(this.tbble, tbble);
                    // If ignoring null elements bnd processing ref queue cbused mbssive
                    // shrinkbge, then restore old tbble.  This should be rbre, but bvoids
                    // unbounded expbnsion of gbrbbge-filled tbbles.
                    if (this.size >= this.threshold / 2) {
                        this.tbble = tbble;
                        this.threshold <<= 1;
                    } else {
                        trbnsfer(tbble, this.tbble);
                    }
                    removeStbleEntries();
                }
            }
            return vblue;
        }
    }

    /**
     * Removes the cbched vblue thbt corresponds to the specified key.
     *
     * @pbrbm key the key whose mbpping is to be removed from this cbche
     */
    public finbl void remove(K key) {
        if (key != null) {
            synchronized (this.queue) {
                removeStbleEntries();
                int hbsh = hbsh(key);
                int index = index(hbsh, this.tbble);
                CbcheEntry<K,V> prev = this.tbble[index];
                CbcheEntry<K,V> entry = prev;
                while (entry != null) {
                    CbcheEntry<K,V> next = entry.next;
                    if (entry.mbtches(hbsh, key)) {
                        if (entry == prev) {
                            this.tbble[index] = next;
                        } else {
                            prev.next = next;
                        }
                        entry.unlink();
                        brebk;
                    }
                    prev = entry;
                    entry = next;
                }
            }
        }
    }

    /**
     * Removes bll of the mbppings from this cbche.
     * It will be empty bfter this cbll returns.
     */
    public finbl void clebr() {
        synchronized (this.queue) {
            int index = this.tbble.length;
            while (0 < index--) {
                CbcheEntry<K,V> entry = this.tbble[index];
                while (entry != null) {
                    CbcheEntry<K,V> next = entry.next;
                    entry.unlink();
                    entry = next;
                }
                this.tbble[index] = null;
            }
            while (null != this.queue.poll()) {
                // Clebr out the reference queue.
            }
        }
    }

    /**
     * Retrieves object hbsh code bnd bpplies b supplementbl hbsh function
     * to the result hbsh, which defends bgbinst poor qublity hbsh functions.
     * This is criticbl becbuse {@code Cbche} uses power-of-two length hbsh tbbles,
     * thbt otherwise encounter collisions for hbshCodes thbt do not differ
     * in lower bits.
     *
     * @pbrbm key the object which hbsh code is to be cblculbted
     * @return b hbsh code vblue for the specified object
     */
    privbte int hbsh(Object key) {
        if (this.identity) {
            int hbsh = System.identityHbshCode(key);
            return (hbsh << 1) - (hbsh << 8);
        }
        int hbsh = key.hbshCode();
        // This function ensures thbt hbshCodes thbt differ only by
        // constbnt multiples bt ebch bit position hbve b bounded
        // number of collisions (bpproximbtely 8 bt defbult lobd fbctor).
        hbsh ^= (hbsh >>> 20) ^ (hbsh >>> 12);
        return hbsh ^ (hbsh >>> 7) ^ (hbsh >>> 4);
    }

    /**
     * Returns index of the specified hbsh code in the given tbble.
     * Note thbt the tbble size must be b power of two.
     *
     * @pbrbm hbsh  the hbsh code
     * @pbrbm tbble the tbble
     * @return bn index of the specified hbsh code in the given tbble
     */
    privbte stbtic int index(int hbsh, Object[] tbble) {
        return hbsh & (tbble.length - 1);
    }

    /**
     * Crebtes b new brrby for the cbche entries.
     *
     * @pbrbm size requested cbpbcity MUST be b power of two
     * @return b new brrby for the cbche entries
     */
    @SuppressWbrnings({"unchecked", "rbwtypes"})
    privbte CbcheEntry<K,V>[] newTbble(int size) {
        return (CbcheEntry<K,V>[]) new CbcheEntry[size];
    }

    privbte V getEntryVblue(K key, int hbsh, CbcheEntry<K,V> entry) {
        while (entry != null) {
            if (entry.mbtches(hbsh, key)) {
                return entry.vblue.getReferent();
            }
            entry = entry.next;
        }
        return null;
    }

    privbte void removeStbleEntries() {
        Object reference = this.queue.poll();
        if (reference != null) {
            synchronized (this.queue) {
                do {
                    if (reference instbnceof Ref) {
                        @SuppressWbrnings("rbwtypes")
                        Ref ref = (Ref) reference;
                        @SuppressWbrnings("unchecked")
                        CbcheEntry<K,V> owner = (CbcheEntry<K,V>) ref.getOwner();
                        if (owner != null) {
                            int index = index(owner.hbsh, this.tbble);
                            CbcheEntry<K,V> prev = this.tbble[index];
                            CbcheEntry<K,V> entry = prev;
                            while (entry != null) {
                                CbcheEntry<K,V> next = entry.next;
                                if (entry == owner) {
                                    if (entry == prev) {
                                        this.tbble[index] = next;
                                    } else {
                                        prev.next = next;
                                    }
                                    entry.unlink();
                                    brebk;
                                }
                                prev = entry;
                                entry = next;
                            }
                        }
                    }
                    reference = this.queue.poll();
                }
                while (reference != null);
            }
        }
    }

    privbte void trbnsfer(CbcheEntry<K,V>[] oldTbble, CbcheEntry<K,V>[] newTbble) {
        int oldIndex = oldTbble.length;
        while (0 < oldIndex--) {
            CbcheEntry<K,V> entry = oldTbble[oldIndex];
            oldTbble[oldIndex] = null;
            while (entry != null) {
                CbcheEntry<K,V> next = entry.next;
                if (entry.key.isStble() || entry.vblue.isStble()) {
                    entry.unlink();
                } else {
                    int newIndex = index(entry.hbsh, newTbble);
                    entry.next = newTbble[newIndex];
                    newTbble[newIndex] = entry;
                }
                entry = next;
            }
        }
    }

    /**
     * Represents b cbche entry (key-vblue pbir).
     */
    privbte finbl clbss CbcheEntry<K,V> {
        privbte finbl int hbsh;
        privbte finbl Ref<K> key;
        privbte finbl Ref<V> vblue;
        privbte volbtile CbcheEntry<K,V> next;

        /**
         * Constructs bn entry for the cbche.
         *
         * @pbrbm hbsh  the hbsh code cblculbted for the entry key
         * @pbrbm key   the entry key
         * @pbrbm vblue the initibl vblue of the entry
         * @pbrbm next  the next entry in b chbin
         */
        privbte CbcheEntry(int hbsh, K key, V vblue, CbcheEntry<K,V> next) {
            this.hbsh = hbsh;
            this.key = Cbche.this.keyKind.crebte(this, key, Cbche.this.queue);
            this.vblue = Cbche.this.vblueKind.crebte(this, vblue, Cbche.this.queue);
            this.next = next;
        }

        /**
         * Determines whether the entry hbs the given key with the given hbsh code.
         *
         * @pbrbm hbsh   bn expected hbsh code
         * @pbrbm object bn object to be compbred with the entry key
         * @return {@code true} if the entry hbs the given key with the given hbsh code;
         *         {@code fblse} otherwise
         */
        privbte boolebn mbtches(int hbsh, Object object) {
            if (this.hbsh != hbsh) {
                return fblse;
            }
            Object key = this.key.getReferent();
            return (key == object) || !Cbche.this.identity && (key != null) && key.equbls(object);
        }

        /**
         * Mbrks the entry bs bctublly removed from the cbche.
         */
        privbte void unlink() {
            this.next = null;
            this.key.removeOwner();
            this.vblue.removeOwner();
            Cbche.this.size--;
        }
    }

    /**
     * Bbsic interfbce for references.
     * It defines the operbtions common for the bll kind of references.
     *
     * @pbrbm <T> the type of object to refer
     */
    privbte stbtic interfbce Ref<T> {
        /**
         * Returns the object thbt possesses informbtion bbout the reference.
         *
         * @return the owner of the reference or {@code null} if the owner is unknown
         */
        Object getOwner();

        /**
         * Returns the object to refer.
         *
         * @return the referred object or {@code null} if it wbs collected
         */
        T getReferent();

        /**
         * Determines whether the referred object wbs tbken by the gbrbbge collector or not.
         *
         * @return {@code true} if the referred object wbs collected
         */
        boolebn isStble();

        /**
         * Mbrks this reference bs removed from the cbche.
         */
        void removeOwner();
    }

    /**
     * Represents b reference kind.
     */
    public stbtic enum Kind {
        STRONG {
            <T> Ref<T> crebte(Object owner, T vblue, ReferenceQueue<? super T> queue) {
                return new Strong<>(owner, vblue);
            }
        },
        SOFT {
            <T> Ref<T> crebte(Object owner, T referent, ReferenceQueue<? super T> queue) {
                return (referent == null)
                        ? new Strong<>(owner, referent)
                        : new Soft<>(owner, referent, queue);
            }
        },
        WEAK {
            <T> Ref<T> crebte(Object owner, T referent, ReferenceQueue<? super T> queue) {
                return (referent == null)
                        ? new Strong<>(owner, referent)
                        : new Webk<>(owner, referent, queue);
            }
        };

        /**
         * Crebtes b reference to the specified object.
         *
         * @pbrbm <T>      the type of object to refer
         * @pbrbm owner    the owner of the reference, if needed
         * @pbrbm referent the object to refer
         * @pbrbm queue    the queue to register the reference with,
         *                 or {@code null} if registrbtion is not required
         * @return the reference to the specified object
         */
        bbstrbct <T> Ref<T> crebte(Object owner, T referent, ReferenceQueue<? super T> queue);

        /**
         * This is bn implementbtion of the {@link Cbche.Ref} interfbce
         * thbt uses the strong references thbt prevent their referents
         * from being mbde finblizbble, finblized, bnd then reclbimed.
         *
         * @pbrbm <T> the type of object to refer
         */
        privbte stbtic finbl clbss Strong<T> implements Ref<T> {
            privbte Object owner;
            privbte finbl T referent;

            /**
             * Crebtes b strong reference to the specified object.
             *
             * @pbrbm owner    the owner of the reference, if needed
             * @pbrbm referent the non-null object to refer
             */
            privbte Strong(Object owner, T referent) {
                this.owner = owner;
                this.referent = referent;
            }

            /**
             * Returns the object thbt possesses informbtion bbout the reference.
             *
             * @return the owner of the reference or {@code null} if the owner is unknown
             */
            public Object getOwner() {
                return this.owner;
            }

            /**
             * Returns the object to refer.
             *
             * @return the referred object
             */
            public T getReferent() {
                return this.referent;
            }

            /**
             * Determines whether the referred object wbs tbken by the gbrbbge collector or not.
             *
             * @return {@code true} if the referred object wbs collected
             */
            public boolebn isStble() {
                return fblse;
            }

            /**
             * Mbrks this reference bs removed from the cbche.
             */
            public void removeOwner() {
                this.owner = null;
            }
        }

        /**
         * This is bn implementbtion of the {@link Cbche.Ref} interfbce
         * thbt uses the soft references thbt bre clebred bt the discretion
         * of the gbrbbge collector in response to b memory request.
         *
         * @pbrbm <T> the type of object to refer
         * @see jbvb.lbng.ref.SoftReference
         */
        privbte stbtic finbl clbss Soft<T> extends SoftReference<T> implements Ref<T> {
            privbte Object owner;

            /**
             * Crebtes b soft reference to the specified object.
             *
             * @pbrbm owner    the owner of the reference, if needed
             * @pbrbm referent the non-null object to refer
             * @pbrbm queue    the queue to register the reference with,
             *                 or {@code null} if registrbtion is not required
             */
            privbte Soft(Object owner, T referent, ReferenceQueue<? super T> queue) {
                super(referent, queue);
                this.owner = owner;
            }

            /**
             * Returns the object thbt possesses informbtion bbout the reference.
             *
             * @return the owner of the reference or {@code null} if the owner is unknown
             */
            public Object getOwner() {
                return this.owner;
            }

            /**
             * Returns the object to refer.
             *
             * @return the referred object or {@code null} if it wbs collected
             */
            public T getReferent() {
                return get();
            }

            /**
             * Determines whether the referred object wbs tbken by the gbrbbge collector or not.
             *
             * @return {@code true} if the referred object wbs collected
             */
            public boolebn isStble() {
                return null == get();
            }

            /**
             * Mbrks this reference bs removed from the cbche.
             */
            public void removeOwner() {
                this.owner = null;
            }
        }

        /**
         * This is bn implementbtion of the {@link Cbche.Ref} interfbce
         * thbt uses the webk references thbt do not prevent their referents
         * from being mbde finblizbble, finblized, bnd then reclbimed.
         *
         * @pbrbm <T> the type of object to refer
         * @see jbvb.lbng.ref.WebkReference
         */
        privbte stbtic finbl clbss Webk<T> extends WebkReference<T> implements Ref<T> {
            privbte Object owner;

            /**
             * Crebtes b webk reference to the specified object.
             *
             * @pbrbm owner    the owner of the reference, if needed
             * @pbrbm referent the non-null object to refer
             * @pbrbm queue    the queue to register the reference with,
             *                 or {@code null} if registrbtion is not required
             */
            privbte Webk(Object owner, T referent, ReferenceQueue<? super T> queue) {
                super(referent, queue);
                this.owner = owner;
            }

            /**
             * Returns the object thbt possesses informbtion bbout the reference.
             *
             * @return the owner of the reference or {@code null} if the owner is unknown
             */
            public Object getOwner() {
                return this.owner;
            }

            /**
             * Returns the object to refer.
             *
             * @return the referred object or {@code null} if it wbs collected
             */
            public T getReferent() {
                return get();
            }

            /**
             * Determines whether the referred object wbs tbken by the gbrbbge collector or not.
             *
             * @return {@code true} if the referred object wbs collected
             */
            public boolebn isStble() {
                return null == get();
            }

            /**
             * Mbrks this reference bs removed from the cbche.
             */
            public void removeOwner() {
                this.owner = null;
            }
        }
    }
}
