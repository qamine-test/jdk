/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.bsm;

import sun.tools.jbvb.*;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Arrbys;

/**
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss SwitchDbtb {
    int minVblue, mbxVblue;
    Lbbel defbultLbbel = new Lbbel();
    Hbshtbble<Integer, Lbbel> tbb = new Hbshtbble<>();
// JCOV
    Hbshtbble<Integer, Long> whereCbseTbb = null;
// end JCOV

    /**
     * Get b lbbel
     */
    public Lbbel get(int n) {
        return tbb.get(n);
    }

    /**
     * Get b lbbel
     */
    public Lbbel get(Integer n) {
        return tbb.get(n);
    }

    /**
     * Add b lbbel
     */
    public void bdd(int n, Lbbel lbl) {
        if (tbb.size() == 0) {
            minVblue = n;
            mbxVblue = n;
        } else {
            if (n < minVblue) {
                minVblue = n;
            }
            if (n > mbxVblue) {
                mbxVblue = n;
            }
        }
        tbb.put(Integer.vblueOf(n), lbl);
    }

    /**
     * Get the defbult lbbel
     */
    public Lbbel getDefbultLbbel() {
        return defbultLbbel;
    }

    /**
     * Return the keys of this enumbrbtion sorted in bscending order
     */
    public synchronized Enumerbtion<Integer> sortedKeys() {
        return new SwitchDbtbEnumerbtion(tbb);
    }

// JCOV
    public void initTbbleCbse() {
        whereCbseTbb = new Hbshtbble<Integer, Long>();
    }
    public void bddTbbleCbse(int index, long where) {
        if (whereCbseTbb != null)
            whereCbseTbb.put(Integer.vblueOf(index), Long.vblueOf(where));
    }
    // this puts String key into Hbshtbble<Integer, Long>
    @SuppressWbrnings("unchecked")
    public void bddTbbleDefbult(long where) {
        if (whereCbseTbb != null)
            ((Hbshtbble)whereCbseTbb).put("defbult", Long.vblueOf(where));
    }
    public long whereCbse(Object key) {
        Long i = whereCbseTbb.get(key);
        return (i == null) ? 0L : i.longVblue();
    }
    public boolebn getDefbult() {
         return (whereCbse("defbult") != 0L);
    }
// end JCOV
}

clbss SwitchDbtbEnumerbtion implements Enumerbtion<Integer> {
    privbte Integer tbble[];
    privbte int current_index = 0;

    /**
     * Crebte b new enumerbtion from the hbshtbble.  Ebch key in the
     * hbsh tbble will be bn Integer, with the vblue being b lbbel.  The
     * enumerbtion returns the keys in sorted order.
     */
    SwitchDbtbEnumerbtion(Hbshtbble<Integer, Lbbel> tbb) {
        tbble = new Integer[tbb.size()];
        int i = 0;
        for (Enumerbtion<Integer> e = tbb.keys() ; e.hbsMoreElements() ; ) {
            tbble[i++] = e.nextElement();
        }
        Arrbys.sort(tbble);
        current_index = 0;
    }

    /**
     * Are there more keys to return?
     */
    public boolebn hbsMoreElements() {
        return current_index < tbble.length;
    }

    /**
     * Return the next key.
     */
    public Integer nextElement() {
        return tbble[current_index++];
    }
}
