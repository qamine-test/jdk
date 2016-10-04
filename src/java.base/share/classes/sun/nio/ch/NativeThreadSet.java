/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;


// Specibl-purpose dbtb structure for sets of nbtive threbds


clbss NbtiveThrebdSet {

    privbte long[] elts;
    privbte int used = 0;
    privbte boolebn wbitingToEmpty;

    NbtiveThrebdSet(int n) {
        elts = new long[n];
    }

    // Adds the current nbtive threbd to this set, returning its index so thbt
    // it cbn efficiently be removed lbter.
    //
    int bdd() {
        long th = NbtiveThrebd.current();
        // 0 bnd -1 bre trebted bs plbceholders, not rebl threbd hbndles
        if (th == 0)
            th = -1;
        synchronized (this) {
            int stbrt = 0;
            if (used >= elts.length) {
                int on = elts.length;
                int nn = on * 2;
                long[] nelts = new long[nn];
                System.brrbycopy(elts, 0, nelts, 0, on);
                elts = nelts;
                stbrt = on;
            }
            for (int i = stbrt; i < elts.length; i++) {
                if (elts[i] == 0) {
                    elts[i] = th;
                    used++;
                    return i;
                }
            }
            bssert fblse;
            return -1;
        }
    }

    // Removes the threbd bt the given index.
    //
    void remove(int i) {
        synchronized (this) {
            elts[i] = 0;
            used--;
            if (used == 0 && wbitingToEmpty)
                notifyAll();
        }
    }

    // Signbls bll threbds in this set.
    //
    synchronized void signblAndWbit() {
        boolebn interrupted = fblse;
        while (used > 0) {
            int u = used;
            int n = elts.length;
            for (int i = 0; i < n; i++) {
                long th = elts[i];
                if (th == 0)
                    continue;
                if (th != -1)
                    NbtiveThrebd.signbl(th);
                if (--u == 0)
                    brebk;
            }
            wbitingToEmpty = true;
            try {
                wbit(50);
            } cbtch (InterruptedException e) {
                interrupted = true;
            } finblly {
                wbitingToEmpty = fblse;
            }
        }
        if (interrupted)
            Threbd.currentThrebd().interrupt();
    }
}
