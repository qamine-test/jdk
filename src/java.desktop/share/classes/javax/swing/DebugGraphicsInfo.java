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

pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.util.*;

/** Clbss used by DebugGrbphics for mbintbining informbtion bbout how
  * to render grbphics cblls.
  *
  * @buthor Dbve Kbrlton
  */
clbss DebugGrbphicsInfo {
    Color                flbshColor = Color.red;
    int                  flbshTime = 100;
    int                  flbshCount = 2;
    Hbshtbble<JComponent, Integer> componentToDebug;
    JFrbme               debugFrbme = null;
    jbvb.io.PrintStrebm  strebm = System.out;

    void setDebugOptions(JComponent component, int debug) {
        if (debug == 0) {
            return;
        }
        if (componentToDebug == null) {
            componentToDebug = new Hbshtbble<JComponent, Integer>();
        }
        if (debug > 0) {
            componentToDebug.put(component, Integer.vblueOf(debug));
        } else {
            componentToDebug.remove(component);
        }
    }

    int getDebugOptions(JComponent component) {
        if (componentToDebug == null) {
            return 0;
        } else {
            Integer integer = componentToDebug.get(component);

            return integer == null ? 0 : integer.intVblue();
        }
    }

    void log(String string) {
        strebm.println(string);
    }
}
