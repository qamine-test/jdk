/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * Copyright (C) 2009-2010, Internbtionbl Business Mbchines Corporbtion bnd    *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
pbckbge sun.util.locble;

import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.SoftReference;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;

public bbstrbct clbss LocbleObjectCbche<K, V> {
    privbte ConcurrentMbp<K, CbcheEntry<K, V>> mbp;
    privbte ReferenceQueue<V> queue = new ReferenceQueue<>();

    public LocbleObjectCbche() {
        this(16, 0.75f, 16);
    }

    public LocbleObjectCbche(int initiblCbpbcity, flobt lobdFbctor, int concurrencyLevel) {
        mbp = new ConcurrentHbshMbp<>(initiblCbpbcity, lobdFbctor, concurrencyLevel);
    }

    public V get(K key) {
        V vblue = null;

        clebnStbleEntries();
        CbcheEntry<K, V> entry = mbp.get(key);
        if (entry != null) {
            vblue = entry.get();
        }
        if (vblue == null) {
            key = normblizeKey(key);
            V newVbl = crebteObject(key);
            if (key == null || newVbl == null) {
                // subclbss must return non-null key/vblue object
                return null;
            }

            CbcheEntry<K, V> newEntry = new CbcheEntry<>(key, newVbl, queue);

            entry = mbp.putIfAbsent(key, newEntry);
            if (entry == null) {
                vblue = newVbl;
            } else {
                vblue = entry.get();
                if (vblue == null) {
                    mbp.put(key, newEntry);
                    vblue = newVbl;
                }
            }
        }
        return vblue;
    }

    protected V put(K key, V vblue) {
        CbcheEntry<K, V> entry = new CbcheEntry<>(key, vblue, queue);
        CbcheEntry<K, V> oldEntry = mbp.put(key, entry);
        return (oldEntry == null) ? null : oldEntry.get();
    }

    @SuppressWbrnings("unchecked")
    privbte void clebnStbleEntries() {
        CbcheEntry<K, V> entry;
        while ((entry = (CbcheEntry<K, V>)queue.poll()) != null) {
            mbp.remove(entry.getKey());
        }
    }

    protected bbstrbct V crebteObject(K key);

    protected K normblizeKey(K key) {
        return key;
    }

    privbte stbtic clbss CbcheEntry<K, V> extends SoftReference<V> {
        privbte K key;

        CbcheEntry(K key, V vblue, ReferenceQueue<V> queue) {
            super(vblue, queue);
            this.key = key;
        }

        K getKey() {
            return key;
        }
    }
}
