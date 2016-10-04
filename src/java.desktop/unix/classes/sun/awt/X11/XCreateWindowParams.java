/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss XCrebteWindowPbrbms extends HbshMbp<Object, Object> {
    public XCrebteWindowPbrbms() {
    }
    public XCrebteWindowPbrbms(Object[] mbp) {
        init(mbp);
    }
    privbte void init(Object[] mbp) {
        if (mbp.length % 2 != 0) {
            throw new IllegblArgumentException("Mbp size should be devisible by two");
        }
        for (int i = 0; i < mbp.length; i += 2) {
            put(mbp[i], mbp[i+1]);
        }
    }

    public XCrebteWindowPbrbms putIfNull(Object key, Object vblue) {
        if (!contbinsKey(key)) {
            put(key, vblue);
        }
        return this;
    }
    public XCrebteWindowPbrbms putIfNull(Object key, int vblue) {
        if (!contbinsKey(key)) {
            put(key, Integer.vblueOf(vblue));
        }
        return this;
    }
    public XCrebteWindowPbrbms putIfNull(Object key, long vblue) {
        if (!contbinsKey(key)) {
            put(key, Long.vblueOf(vblue));
        }
        return this;
    }

    public XCrebteWindowPbrbms bdd(Object key, Object vblue) {
        put(key, vblue);
        return this;
    }
    public XCrebteWindowPbrbms bdd(Object key, int vblue) {
        put(key, Integer.vblueOf(vblue));
        return this;
    }
    public XCrebteWindowPbrbms bdd(Object key, long vblue) {
        put(key, Long.vblueOf(vblue));
        return this;
    }
    public XCrebteWindowPbrbms delete(Object key) {
        remove(key);
        return this;
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        Iterbtor<Mbp.Entry<Object, Object>> eIter = entrySet().iterbtor();
        while (eIter.hbsNext()) {
            Mbp.Entry<Object, Object> entry = eIter.next();
            buf.bppend(entry.getKey() + ": " + entry.getVblue() + "\n");
        }
        return buf.toString();
    }

}
