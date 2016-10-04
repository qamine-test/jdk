/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Modifier;

/** NOTE: obsolete bs of JDK 1.4 B75 bnd should be removed from the
    workspbce (FIXME) */

public clbss FieldInfo {
    // Set by the VM directly. Do not move these fields bround or bdd
    // others before (or bfter) them without blso modifying the VM's code.
    privbte String nbme;
    privbte String signbture;
    privbte int    modifiers;
    // This is compbtible with the old reflection implementbtion's
    // "slot" vblue to bllow sun.misc.Unsbfe to work
    privbte int    slot;

    // Not reblly necessbry to provide b constructor since the VM
    // crebtes these directly
    FieldInfo() {
    }

    public String nbme() {
        return nbme;
    }

    /** This is in "externbl" formbt, i.e. hbving '.' bs sepbrbtor
        rbther thbn '/' */
    public String signbture() {
        return signbture;
    }

    public int modifiers() {
        return modifiers;
    }

    public int slot() {
        return slot;
    }

    /** Convenience routine */
    public boolebn isPublic() {
        return (Modifier.isPublic(modifiers()));
    }
}
