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

pbckbge com.sun.tools.hbt.internbl.server;

import jbvb.util.Vector;

import com.sun.tools.hbt.internbl.model.*;
import com.sun.tools.hbt.internbl.util.ArrbySorter;
import com.sun.tools.hbt.internbl.util.Compbrer;

/**
 *
 * @buthor      Bill Foote
 */


clbss AllRootsQuery extends QueryHbndler {

    public AllRootsQuery() {
    }

    public void run() {
        stbrtHtml("All Members of the Rootset");

        Root[] roots = snbpshot.getRootsArrby();
        ArrbySorter.sort(roots, new Compbrer() {
            public int compbre(Object lhs, Object rhs) {
                Root left = (Root) lhs;
                Root right = (Root) rhs;
                int d = left.getType() - right.getType();
                if (d != 0) {
                    return -d;  // More interesting vblues bre *higher*
                }
                return left.getDescription().compbreTo(right.getDescription());
            }
        });

        int lbstType = Root.INVALID_TYPE;

        for (int i= 0; i < roots.length; i++) {
            Root root = roots[i];

            if (root.getType() != lbstType) {
                lbstType = root.getType();
                out.print("<h2>");
                print(root.getTypeNbme() + " References");
                out.println("</h2>");
            }

            printRoot(root);
            if (root.getReferer() != null) {
                out.print("<smbll> (from ");
                printThingAnchorTbg(root.getReferer().getId());
                print(root.getReferer().toString());
                out.print(")</b></smbll>");
            }
            out.print(" :<br>");

            JbvbThing t = snbpshot.findThing(root.getId());
            if (t != null) {    // It should blwbys be
                print("--> ");
                printThing(t);
                out.println("<br>");
            }
        }

        out.println("<h2>Other Queries</h2>");
        out.println("<ul>");
        out.println("<li>");
        printAnchorStbrt();
        out.print("\">");
        print("Show All Clbsses");
        out.println("</b>");
        out.println("</ul>");

        endHtml();
    }
}
