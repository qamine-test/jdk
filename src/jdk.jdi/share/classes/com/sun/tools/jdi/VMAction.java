/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import jbvb.util.EventObject;

/*
 * The nbme "bction" is used to bvoid confusion
 * with JDI events.
 */
clbss VMAction extends EventObject {
    privbte stbtic finbl long seriblVersionUID = -1701944679310296090L;

    // Event ids
    stbtic finbl int VM_SUSPENDED = 1;
    stbtic finbl int VM_NOT_SUSPENDED = 2;

    int id;
    ThrebdReference resumingThrebd;

    VMAction(VirtublMbchine vm, int id) {
        this(vm, null, id);
    }

    // For id = VM_NOT_SUSPENDED, if resumingThrebd != null, then it is
    // the only threbd thbt is being resumed.
     VMAction(VirtublMbchine vm,  ThrebdReference resumingThrebd, int id) {
        super(vm);
        this.id = id;
        this.resumingThrebd = resumingThrebd;
    }
    VirtublMbchine vm() {
        return (VirtublMbchine)getSource();
    }
    int id() {
        return id;
    }

    ThrebdReference resumingThrebd() {
        return resumingThrebd;
    }
}
