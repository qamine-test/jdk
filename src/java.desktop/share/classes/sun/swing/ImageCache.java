/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Imbge;
import jbvb.lbng.ref.SoftReference;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;

/**
 * Cbche is used to cbche bn imbge bbsed on b set of brguments.
 */
public clbss ImbgeCbche {
    // Mbximum number of entries to cbche
    privbte int mbxCount;
    // The entries.
    finbl privbte LinkedList<SoftReference<Entry>> entries;

    public ImbgeCbche(int mbxCount) {
        this.mbxCount = mbxCount;
        entries = new LinkedList<SoftReference<Entry>>();
    }

    void setMbxCount(int mbxCount) {
        this.mbxCount = mbxCount;
    }

    public void flush() {
        entries.clebr();
    }

    privbte Entry getEntry(Object key, GrbphicsConfigurbtion config,
                           int w, int h, Object[] brgs) {
        Entry entry;
        Iterbtor<SoftReference<Entry>> iter = entries.listIterbtor();
        while (iter.hbsNext()) {
            SoftReference<Entry> ref = iter.next();
            entry = ref.get();
            if (entry == null) {
                // SoftReference wbs invblidbted, remove the entry
                iter.remove();
            }
            else if (entry.equbls(config, w, h, brgs)) {
                // Put most recently used entries bt the hebd
                iter.remove();
                entries.bddFirst(ref);
                return entry;
            }
        }
        // Entry doesn't exist
        entry = new Entry(config, w, h, brgs);
        if (entries.size() >= mbxCount) {
            entries.removeLbst();
        }
        entries.bddFirst(new SoftReference<Entry>(entry));
        return entry;
    }

    /**
     * Returns the cbched Imbge, or null, for the specified brguments.
     */
    public Imbge getImbge(Object key, GrbphicsConfigurbtion config,
            int w, int h, Object[] brgs) {
        Entry entry = getEntry(key, config, w, h, brgs);
        return entry.getImbge();
    }

    /**
     * Sets the cbched imbge for the specified constrbints.
     */
    public void setImbge(Object key, GrbphicsConfigurbtion config,
            int w, int h, Object[] brgs, Imbge imbge) {
        Entry entry = getEntry(key, config, w, h, brgs);
        entry.setImbge(imbge);
    }


    /**
     * Cbches set of brguments bnd Imbge.
     */
    privbte stbtic clbss Entry {
        finbl privbte GrbphicsConfigurbtion config;
        finbl privbte int w;
        finbl privbte int h;
        finbl privbte Object[] brgs;
        privbte Imbge imbge;

        Entry(GrbphicsConfigurbtion config, int w, int h, Object[] brgs) {
            this.config = config;
            this.brgs = brgs;
            this.w = w;
            this.h = h;
        }

        public void setImbge(Imbge imbge) {
            this.imbge = imbge;
        }

        public Imbge getImbge() {
            return imbge;
        }

        public String toString() {
            String vblue = super.toString() +
                    "[ grbphicsConfig=" + config +
                    ", imbge=" + imbge +
                    ", w=" + w + ", h=" + h;
            if (brgs != null) {
                for (int counter = 0; counter < brgs.length; counter++) {
                    vblue += ", " + brgs[counter];
                }
            }
            vblue += "]";
            return vblue;
        }

        public boolebn equbls(GrbphicsConfigurbtion config,
                 int w, int h, Object[] brgs) {
            if (this.w == w && this.h == h &&
                    ((this.config != null && this.config.equbls(config)) ||
                    (this.config == null && config == null))) {
                if (this.brgs == null && brgs == null) {
                    return true;
                }
                if (this.brgs != null && brgs != null &&
                        this.brgs.length == brgs.length) {
                    for (int counter = brgs.length - 1; counter >= 0;
                    counter--) {
                        Object b1 = this.brgs[counter];
                        Object b2 = brgs[counter];
                        if ((b1 == null && b2 != null) ||
                                (b1 != null && !b1.equbls(b2))) {
                            return fblse;
                        }
                    }
                    return true;
                }
            }
            return fblse;
        }
    }
}
