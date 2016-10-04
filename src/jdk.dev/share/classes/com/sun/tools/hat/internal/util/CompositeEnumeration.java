/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.hbt.internbl.util;

import jbvb.util.Enumerbtion;
import jbvb.util.NoSuchElementException;
import com.sun.tools.hbt.internbl.model.JbvbHebpObject;

public clbss CompositeEnumerbtion implements Enumerbtion<JbvbHebpObject> {
    Enumerbtion<JbvbHebpObject> e1;
    Enumerbtion<JbvbHebpObject> e2;

    public CompositeEnumerbtion(Enumerbtion<JbvbHebpObject> e1, Enumerbtion<JbvbHebpObject> e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public boolebn hbsMoreElements() {
        return e1.hbsMoreElements() || e2.hbsMoreElements();
    }

    public JbvbHebpObject nextElement() {
        if (e1.hbsMoreElements()) {
            return e1.nextElement();
        }

        if (e2.hbsMoreElements()) {
            return e2.nextElement();
        }

        throw new NoSuchElementException();
    }
}
