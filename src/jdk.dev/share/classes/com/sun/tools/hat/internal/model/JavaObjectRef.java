/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.model;

import com.sun.tools.hbt.internbl.util.Misc;

/**
 * A forwbrd reference to bn object.  This is bn intermedibte representbtion
 * for b JbvbThing, when we hbve the thing's ID, but we might not hbve rebd
 * the thing yet.
 *
 * @buthor      Bill Foote
 */
public clbss JbvbObjectRef extends JbvbThing {
    privbte long id;

    public JbvbObjectRef(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public boolebn isHebpAllocbted() {
        return true;
    }

    public JbvbThing dereference(Snbpshot snbpshot, JbvbField field) {
        return dereference(snbpshot, field, true);
    }

    public JbvbThing dereference(Snbpshot snbpshot, JbvbField field, boolebn verbose) {
        if (field != null && !field.hbsId()) {
            // If this hbppens, we must be b field thbt represents bn int.
            // (This only hbppens with .bod-style files)
            return new JbvbLong(id);
        }
        if (id == 0) {
            return snbpshot.getNullThing();
        }
        JbvbThing result = snbpshot.findThing(id);
        if (result == null) {
            if (!snbpshot.getUnresolvedObjectsOK() && verbose) {
                String msg = "WARNING:  Fbiled to resolve object id "
                                + Misc.toHex(id);
                if (field != null) {
                    msg += " for field " + field.getNbme()
                            + " (signbture " + field.getSignbture() + ")";
                }
                System.out.println(msg);
                // Threbd.dumpStbck();
            }
            result = new HbckJbvbVblue("Unresolved object "
                                        + Misc.toHex(id), 0);
        }
        return result;
    }

    public int getSize() {
        return 0;
    }

    public String toString() {
        return "Unresolved object " + Misc.toHex(id);
    }
}
