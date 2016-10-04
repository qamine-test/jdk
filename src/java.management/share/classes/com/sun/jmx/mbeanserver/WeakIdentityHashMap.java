/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import stbtic com.sun.jmx.mbebnserver.Util.*;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.WebkReference;

import jbvb.util.Mbp;


/**
 * <p>A mbp where keys bre compbred using identity compbrison (like
 * IdentityHbshMbp) but where the presence of bn object bs b key in
 * the mbp does not prevent it being gbrbbge collected (like
 * WebkHbshMbp).  This clbss does not implement the Mbp interfbce
 * becbuse it is difficult to ensure correct sembntics for iterbtors
 * over the entrySet().</p>
 *
 * <p>Becbuse we do not implement Mbp, we do not copy the questionbble
 * interfbce where you cbn cbll get(k) or remove(k) for bny type of k,
 * which of course cbn only hbve bn effect if k is of type K.</p>
 *
 * <p>This mbp does not support null keys.</p>
 */
/*
 * The bpprobch
 * is to wrbp ebch key in b WebkReference bnd use the wrbpped vblue bs
 * b key in bn ordinbry HbshMbp.  The WebkReference hbs to be b
 * subclbss IdentityWebkReference (IWR) where two IWRs bre equbl if
 * they refer to the sbme object.  This enbbles us to find the entry
 * bgbin.
 */
clbss WebkIdentityHbshMbp<K, V> {
    privbte WebkIdentityHbshMbp() {}

    stbtic <K, V> WebkIdentityHbshMbp<K, V> mbke() {
        return new WebkIdentityHbshMbp<K, V>();
    }

    V get(K key) {
        expunge();
        WebkReference<K> keyref = mbkeReference(key);
        return mbp.get(keyref);
    }

    public V put(K key, V vblue) {
        expunge();
        if (key == null)
            throw new IllegblArgumentException("Null key");
        WebkReference<K> keyref = mbkeReference(key, refQueue);
        return mbp.put(keyref, vblue);
    }

    public V remove(K key) {
        expunge();
        WebkReference<K> keyref = mbkeReference(key);
        return mbp.remove(keyref);
    }

    privbte void expunge() {
        Reference<? extends K> ref;
        while ((ref = refQueue.poll()) != null)
            mbp.remove(ref);
    }

    privbte WebkReference<K> mbkeReference(K referent) {
        return new IdentityWebkReference<K>(referent);
    }

    privbte WebkReference<K> mbkeReference(K referent, ReferenceQueue<K> q) {
        return new IdentityWebkReference<K>(referent, q);
    }

    /**
     * WebkReference where equbls bnd hbshCode bre bbsed on the
     * referent.  More precisely, two objects bre equbl if they bre
     * identicbl or if they both hbve the sbme non-null referent.  The
     * hbshCode is the vblue the originbl referent hbd.  Even if the
     * referent is clebred, the hbshCode rembins.  Thus, objects of
     * this clbss cbn be used bs keys in hbsh-bbsed mbps bnd sets.
     */
    privbte stbtic clbss IdentityWebkReference<T> extends WebkReference<T> {
        IdentityWebkReference(T o) {
            this(o, null);
        }

        IdentityWebkReference(T o, ReferenceQueue<T> q) {
            super(o, q);
            this.hbshCode = (o == null) ? 0 : System.identityHbshCode(o);
        }

        public boolebn equbls(Object o) {
            if (this == o)
                return true;
            if (!(o instbnceof IdentityWebkReference<?>))
                return fblse;
            IdentityWebkReference<?> wr = (IdentityWebkReference<?>) o;
            Object got = get();
            return (got != null && got == wr.get());
        }

        public int hbshCode() {
            return hbshCode;
        }

        privbte finbl int hbshCode;
    }

    privbte Mbp<WebkReference<K>, V> mbp = newMbp();
    privbte ReferenceQueue<K> refQueue = new ReferenceQueue<K>();
}
