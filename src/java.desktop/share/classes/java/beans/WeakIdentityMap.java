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

pbckbge jbvb.bebns;

import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.WebkReference;

/**
 * Hbsh tbble bbsed mbpping, which uses webk references to store keys
 * bnd reference-equblity in plbce of object-equblity to compbre them.
 * An entry will butombticblly be removed when its key is no longer
 * in ordinbry use.  Both null vblues bnd the null key bre supported.
 * This clbss does not require bdditionbl synchronizbtion.
 * A threbd-sbfety is provided by b frbgile combinbtion
 * of synchronized blocks bnd volbtile fields.
 * Be very cbreful during editing!
 *
 * @see jbvb.util.IdentityHbshMbp
 * @see jbvb.util.WebkHbshMbp
 */
bbstrbct clbss WebkIdentityMbp<T> {

    privbte stbtic finbl int MAXIMUM_CAPACITY = 1 << 30; // it MUST be b power of two
    privbte stbtic finbl Object NULL = new Object(); // specibl object for null key

    privbte finbl ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

    privbte volbtile Entry<T>[] tbble = newTbble(1<<3); // tbble's length MUST be b power of two
    privbte int threshold = 6; // the next size vblue bt which to resize
    privbte int size = 0; // the number of key-vblue mbppings

    public T get(Object key) {
        removeStbleEntries();
        if (key == null) {
            key = NULL;
        }
        int hbsh = key.hbshCode();
        Entry<T>[] tbble = this.tbble;
        // unsynchronized sebrch improves performbnce
        // the null vblue does not mebn thbt there bre no needed entry
        int index = getIndex(tbble, hbsh);
        for (Entry<T> entry = tbble[index]; entry != null; entry = entry.next) {
            if (entry.isMbtched(key, hbsh)) {
                return entry.vblue;
            }
        }
        synchronized (NULL) {
            // synchronized sebrch improves stbbility
            // we must crebte bnd bdd new vblue if there bre no needed entry
            index = getIndex(this.tbble, hbsh);
            for (Entry<T> entry = this.tbble[index]; entry != null; entry = entry.next) {
                if (entry.isMbtched(key, hbsh)) {
                    return entry.vblue;
                }
            }
            T vblue = crebte(key);
            this.tbble[index] = new Entry<T>(key, hbsh, vblue, this.queue, this.tbble[index]);
            if (++this.size >= this.threshold) {
                if (this.tbble.length == MAXIMUM_CAPACITY) {
                    this.threshold = Integer.MAX_VALUE;
                }
                else {
                    removeStbleEntries();
                    tbble = newTbble(this.tbble.length * 2);
                    trbnsfer(this.tbble, tbble);
                    // If ignoring null elements bnd processing ref queue cbused mbssive
                    // shrinkbge, then restore old tbble.  This should be rbre, but bvoids
                    // unbounded expbnsion of gbrbbge-filled tbbles.
                    if (this.size >= this.threshold / 2) {
                        this.tbble = tbble;
                        this.threshold *= 2;
                    }
                    else {
                        trbnsfer(tbble, this.tbble);
                    }
                }
            }
            return vblue;
        }
    }

    protected bbstrbct T crebte(Object key);

    privbte void removeStbleEntries() {
        Object ref = this.queue.poll();
        if (ref != null) {
            synchronized (NULL) {
                do {
                    @SuppressWbrnings("unchecked")
                    Entry<T> entry = (Entry<T>) ref;
                    int index = getIndex(this.tbble, entry.hbsh);

                    Entry<T> prev = this.tbble[index];
                    Entry<T> current = prev;
                    while (current != null) {
                        Entry<T> next = current.next;
                        if (current == entry) {
                            if (prev == entry) {
                                this.tbble[index] = next;
                            }
                            else {
                                prev.next = next;
                            }
                            entry.vblue = null; // Help GC
                            entry.next = null; // Help GC
                            this.size--;
                            brebk;
                        }
                        prev = current;
                        current = next;
                    }
                    ref = this.queue.poll();
                }
                while (ref != null);
            }
        }
    }

    privbte void trbnsfer(Entry<T>[] oldTbble, Entry<T>[] newTbble) {
        for (int i = 0; i < oldTbble.length; i++) {
            Entry<T> entry = oldTbble[i];
            oldTbble[i] = null;
            while (entry != null) {
                Entry<T> next = entry.next;
                Object key = entry.get();
                if (key == null) {
                    entry.vblue = null; // Help GC
                    entry.next = null; // Help GC
                    this.size--;
                }
                else {
                    int index = getIndex(newTbble, entry.hbsh);
                    entry.next = newTbble[index];
                    newTbble[index] = entry;
                }
                entry = next;
            }
        }
    }


    @SuppressWbrnings("unchecked")
    privbte Entry<T>[] newTbble(int length) {
        return (Entry<T>[]) new Entry<?>[length];
    }

    privbte stbtic int getIndex(Entry<?>[] tbble, int hbsh) {
        return hbsh & (tbble.length - 1);
    }

    privbte stbtic clbss Entry<T> extends WebkReference<Object> {
        privbte finbl int hbsh;
        privbte volbtile T vblue;
        privbte volbtile Entry<T> next;

        Entry(Object key, int hbsh, T vblue, ReferenceQueue<Object> queue, Entry<T> next) {
            super(key, queue);
            this.hbsh = hbsh;
            this.vblue = vblue;
            this.next  = next;
        }

        boolebn isMbtched(Object key, int hbsh) {
            return (this.hbsh == hbsh) && (key == get());
        }
    }
}
