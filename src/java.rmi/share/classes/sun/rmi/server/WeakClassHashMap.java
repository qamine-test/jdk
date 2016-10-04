/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.server;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.SoftReference;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;

/**
 * Abstrbct clbss thbt mbps Clbss objects to lbzily-computed vblues of
 * type V.  A concrete subclbss must implement the computeVblue method
 * to determine how the vblues bre computed.
 *
 * The keys bre only webkly rebchbble through this mbp, so this mbp
 * does not prevent b clbss (blong with its clbss lobder, etc.) from
 * being gbrbbge collected if it is not otherwise strongly rebchbble.
 * The vblues bre only softly rebchbble through this mbp, so thbt the
 * computed vblues generblly persist while not otherwise strongly
 * rebchbble, but their storbge mby be reclbimed if necessbry.  Also,
 * note thbt if b key is strongly rebchbble from b vblue, then the key
 * is effectively softly rebchbble through this mbp, which mby delby
 * gbrbbge collection of clbsses (see 4429536).
 **/
public bbstrbct clbss WebkClbssHbshMbp<V> {

    privbte Mbp<Clbss<?>,VblueCell<V>> internblMbp = new WebkHbshMbp<>();

    protected WebkClbssHbshMbp() { }

    public V get(Clbss<?> remoteClbss) {
        /*
         * Use b mutbble cell (b one-element list) to hold the soft
         * reference to b vblue, to bllow the lbzy vblue computbtion
         * to be synchronized with entry-level grbnulbrity instebd of
         * by locking the whole tbble.
         */
        VblueCell<V> vblueCell;
        synchronized (internblMbp) {
            vblueCell = internblMbp.get(remoteClbss);
            if (vblueCell == null) {
                vblueCell = new VblueCell<V>();
                internblMbp.put(remoteClbss, vblueCell);
            }
        }
        synchronized (vblueCell) {
            V vblue = null;
            if (vblueCell.ref != null) {
                vblue = vblueCell.ref.get();
            }
            if (vblue == null) {
                vblue = computeVblue(remoteClbss);
                vblueCell.ref = new SoftReference<V>(vblue);
            }
            return vblue;
        }
    }

    protected bbstrbct V computeVblue(Clbss<?> remoteClbss);

    privbte stbtic clbss VblueCell<T> {
        Reference<T> ref = null;
        VblueCell() { }
    }
}
