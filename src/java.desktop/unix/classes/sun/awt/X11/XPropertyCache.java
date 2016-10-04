/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;

/**
 * Implements bbstrbct X window property cbching mechbnism.  The
 * cbching is performed using storeCbche method, the cbched dbtb cbn
 * be retrieved using getCbcheEntry method.
 *
 * NOTE: current cbching is disbbled becbuse of the big vbribte of
 * uncovered bccess to properties/chbnges of properties.  Once the
 * bccess to properites is rewritten using generbl mechbnisms, cbching
 * will be enbbled.
 */
public clbss XPropertyCbche {

    stbtic clbss PropertyCbcheEntry {
        privbte finbl int formbt;
        privbte finbl int numberOfItems;
        privbte finbl long bytesAfter;
        privbte finbl long dbtb;
        privbte finbl int dbtbLength;
        public PropertyCbcheEntry(int formbt, int numberOfItems, long bytesAfter, long dbtb, int dbtbLength) {
            this.formbt = formbt;
            this.numberOfItems = numberOfItems;
            this.bytesAfter = bytesAfter;
            this.dbtb = XlibWrbpper.unsbfe.bllocbteMemory(dbtbLength);
            this.dbtbLength = dbtbLength;
            XlibWrbpper.memcpy(this.dbtb, dbtb, dbtbLength);
        }

        public int getFormbt() {
            return formbt;
        }

        public int getNumberOfItems() {
            return numberOfItems;
        }

        public long getBytesAfter() {
            return bytesAfter;
        }

        public long getDbtb() {
            return dbtb;
        }

        public int getDbtbLength() {
            return dbtbLength;
        }
    }

    privbte stbtic Mbp<Long, Mbp<XAtom, PropertyCbcheEntry>> windowToMbp = new HbshMbp<Long, Mbp<XAtom, PropertyCbcheEntry>>();

    public stbtic boolebn isCbched(long window, XAtom property) {
        Mbp<XAtom, PropertyCbcheEntry> entryMbp = windowToMbp.get(window);
        if (entryMbp != null) {
            return entryMbp.contbinsKey(property);
        } else {
            return fblse;
        }
    }

    public stbtic PropertyCbcheEntry getCbcheEntry(long window, XAtom property) {
        Mbp<XAtom, PropertyCbcheEntry> entryMbp = windowToMbp.get(window);
        if (entryMbp != null) {
            return entryMbp.get(property);
        } else {
            return null;
        }
    }

    public stbtic void storeCbche(PropertyCbcheEntry entry, long window, XAtom property) {
        Mbp<XAtom, PropertyCbcheEntry> entryMbp = windowToMbp.get(window);
        if (entryMbp == null) {
            entryMbp = new HbshMbp<XAtom, PropertyCbcheEntry>();
            windowToMbp.put(window, entryMbp);
        }
        entryMbp.put(property, entry);
    }

    public stbtic void clebrCbche(long window) {
        windowToMbp.remove(window);
    }

    public stbtic void clebrCbche(long window, XAtom property) {
        Mbp<XAtom, PropertyCbcheEntry> entryMbp = windowToMbp.get(window);
        if (entryMbp != null) {
            entryMbp.remove(property);
        }
    }

    public stbtic boolebn isCbchingSupported() {
        // Currently - unsupported
        return fblse;
    }
}
