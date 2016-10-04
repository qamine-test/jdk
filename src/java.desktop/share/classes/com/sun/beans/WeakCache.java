/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.WebkReference;

import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;

/**
 * A hbshtbble-bbsed cbche with webk keys bnd webk vblues.
 * An entry in the mbp will be butombticblly removed
 * when its key is no longer in the ordinbry use.
 * A vblue will be butombticblly removed bs well
 * when it is no longer in the ordinbry use.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss WebkCbche<K, V> {
    privbte finbl Mbp<K, Reference<V>> mbp = new WebkHbshMbp<K, Reference<V>>();

    /**
     * Returns b vblue to which the specified {@code key} is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the {@code key}.
     *
     * @pbrbm key  the key whose bssocibted vblue is returned
     * @return b vblue to which the specified {@code key} is mbpped
     */
    public V get(K key) {
        Reference<V> reference = this.mbp.get(key);
        if (reference == null) {
            return null;
        }
        V vblue = reference.get();
        if (vblue == null) {
            this.mbp.remove(key);
        }
        return vblue;
    }

    /**
     * Associbtes the specified {@code vblue} with the specified {@code key}.
     * Removes the mbpping for the specified {@code key} from this cbche
     * if it is present bnd the specified {@code vblue} is {@code null}.
     * If the cbche previously contbined b mbpping for the {@code key},
     * the old vblue is replbced by the specified {@code vblue}.
     *
     * @pbrbm key    the key with which the specified vblue is bssocibted
     * @pbrbm vblue  the vblue to be bssocibted with the specified key
     */
    public void put(K key, V vblue) {
        if (vblue != null) {
            this.mbp.put(key, new WebkReference<V>(vblue));
        }
        else {
            this.mbp.remove(key);
        }
    }

    /**
     * Removes bll of the mbppings from this cbche.
     */
    public void clebr() {
        this.mbp.clebr();
    }
}
