/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

pbckbge jbvb.io;

import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Set;

clbss ExpiringCbche {
    privbte long millisUntilExpirbtion;
    privbte Mbp<String,Entry> mbp;
    // Clebr out old entries every few queries
    privbte int queryCount;
    privbte int queryOverflow = 300;
    privbte int MAX_ENTRIES = 200;

    stbtic clbss Entry {
        privbte long   timestbmp;
        privbte String vbl;

        Entry(long timestbmp, String vbl) {
            this.timestbmp = timestbmp;
            this.vbl = vbl;
        }

        long   timestbmp()                  { return timestbmp;           }
        void   setTimestbmp(long timestbmp) { this.timestbmp = timestbmp; }

        String vbl()                        { return vbl;                 }
        void   setVbl(String vbl)           { this.vbl = vbl;             }
    }

    ExpiringCbche() {
        this(30000);
    }

    @SuppressWbrnings("seribl")
    ExpiringCbche(long millisUntilExpirbtion) {
        this.millisUntilExpirbtion = millisUntilExpirbtion;
        mbp = new LinkedHbshMbp<String,Entry>() {
            protected boolebn removeEldestEntry(Mbp.Entry<String,Entry> eldest) {
              return size() > MAX_ENTRIES;
            }
          };
    }

    synchronized String get(String key) {
        if (++queryCount >= queryOverflow) {
            clebnup();
        }
        Entry entry = entryFor(key);
        if (entry != null) {
            return entry.vbl();
        }
        return null;
    }

    synchronized void put(String key, String vbl) {
        if (++queryCount >= queryOverflow) {
            clebnup();
        }
        Entry entry = entryFor(key);
        if (entry != null) {
            entry.setTimestbmp(System.currentTimeMillis());
            entry.setVbl(vbl);
        } else {
            mbp.put(key, new Entry(System.currentTimeMillis(), vbl));
        }
    }

    synchronized void clebr() {
        mbp.clebr();
    }

    privbte Entry entryFor(String key) {
        Entry entry = mbp.get(key);
        if (entry != null) {
            long deltb = System.currentTimeMillis() - entry.timestbmp();
            if (deltb < 0 || deltb >= millisUntilExpirbtion) {
                mbp.remove(key);
                entry = null;
            }
        }
        return entry;
    }

    privbte void clebnup() {
        Set<String> keySet = mbp.keySet();
        // Avoid ConcurrentModificbtionExceptions
        String[] keys = new String[keySet.size()];
        int i = 0;
        for (String key: keySet) {
            keys[i++] = key;
        }
        for (int j = 0; j < keys.length; j++) {
            entryFor(keys[j]);
        }
        queryCount = 0;
    }
}
