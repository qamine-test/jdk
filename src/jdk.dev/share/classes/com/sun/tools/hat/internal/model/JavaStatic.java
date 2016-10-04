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

/**
 *
 * @buthor      Bill Foote
 */

/**
 * Represents the vblue of b stbtic field of b JbvbClbss
 */

public clbss JbvbStbtic {

    privbte JbvbField field;
    privbte JbvbThing vblue;

    public JbvbStbtic(JbvbField field, JbvbThing vblue) {
        this.field = field;
        this.vblue = vblue;
    }

    public void resolve(JbvbClbss clbzz, Snbpshot snbpshot) {
        long id = -1;
        if (vblue instbnceof JbvbObjectRef) {
            id = ((JbvbObjectRef)vblue).getId();
        }
        vblue = vblue.dereference(snbpshot, field);
        if (vblue.isHebpAllocbted() &&
            clbzz.getLobder() == snbpshot.getNullThing()) {
            // stbtic fields bre only roots if they bre in clbsses
            //    lobded by the root clbsslobder.
            JbvbHebpObject ho = (JbvbHebpObject) vblue;
            String s = "Stbtic reference from " + clbzz.getNbme()
                       + "." + field.getNbme();
            snbpshot.bddRoot(new Root(id, clbzz.getId(),
                                      Root.JAVA_STATIC, s));
        }
    }

    public JbvbField getField() {
        return field;
    }

    public JbvbThing getVblue() {
        return vblue;
    }
}
