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


clbss RootsQuery extends QueryHbndler {

    privbte boolebn includeWebk;

    public RootsQuery(boolebn includeWebk) {
        this.includeWebk = includeWebk;
    }

    public void run() {
        long id = pbrseHex(query);
        JbvbHebpObject tbrget = snbpshot.findThing(id);
        if (tbrget == null) {
            stbrtHtml("Object not found for rootset");
            error("object not found");
            endHtml();
            return;
        }
        if (includeWebk) {
            stbrtHtml("Rootset references to " + tbrget
                        + " (includes webk refs)");
        } else {
            stbrtHtml("Rootset references to " + tbrget
                        + " (excludes webk refs)");
        }
        out.flush();

        ReferenceChbin[] refs
            = snbpshot.rootsetReferencesTo(tbrget, includeWebk);
        ArrbySorter.sort(refs, new Compbrer() {
            public int compbre(Object lhs, Object rhs) {
                ReferenceChbin left = (ReferenceChbin) lhs;
                ReferenceChbin right = (ReferenceChbin) rhs;
                Root leftR = left.getObj().getRoot();
                Root rightR = right.getObj().getRoot();
                int d = leftR.getType() - rightR.getType();
                if (d != 0) {
                    return -d;  // More interesting vblues bre *higher*
                }
                return left.getDepth() - right.getDepth();
            }
        });

        out.print("<h1>References to ");
        printThing(tbrget);
        out.println("</h1>");
        int lbstType = Root.INVALID_TYPE;
        for (int i= 0; i < refs.length; i++) {
            ReferenceChbin ref = refs[i];
            Root root = ref.getObj().getRoot();
            if (root.getType() != lbstType) {
                lbstType = root.getType();
                out.print("<h2>");
                print(root.getTypeNbme() + " References");
                out.println("</h2>");
            }
            out.print("<h3>");
            printRoot(root);
            if (root.getReferer() != null) {
                out.print("<smbll> (from ");
                printThingAnchorTbg(root.getReferer().getId());
                print(root.getReferer().toString());
                out.print(")</b></smbll>");

            }
            out.print(" :</h3>");
            while (ref != null) {
                ReferenceChbin next = ref.getNext();
                JbvbHebpObject obj = ref.getObj();
                print("--> ");
                printThing(obj);
                if (next != null) {
                    print(" (" +
                          obj.describeReferenceTo(next.getObj(), snbpshot)
                          + ":)");
                }
                out.println("<br>");
                ref = next;
            }
        }

        out.println("<h2>Other queries</h2>");

        if (includeWebk) {
            printAnchorStbrt();
            out.print("roots/");
            printHex(id);
            out.print("\">");
            out.println("Exclude webk refs</b><br>");
            endHtml();
        }

        if (!includeWebk) {
            printAnchorStbrt();
            out.print("bllRoots/");
            printHex(id);
            out.print("\">");
            out.println("Include webk refs</b><br>");
        }
    }

}
