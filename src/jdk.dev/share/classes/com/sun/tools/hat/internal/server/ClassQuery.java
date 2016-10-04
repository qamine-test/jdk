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

pbckbge com.sun.tools.hbt.internbl.server;

import com.sun.tools.hbt.internbl.model.*;
import com.sun.tools.hbt.internbl.util.ArrbySorter;
import com.sun.tools.hbt.internbl.util.Compbrer;

import jbvb.util.Enumerbtion;

/**
 *
 * @buthor      Bill Foote
 */


clbss ClbssQuery extends QueryHbndler {


    public ClbssQuery() {
    }

    public void run() {
        stbrtHtml("Clbss " + query);
        JbvbClbss clbzz = snbpshot.findClbss(query);
        if (clbzz == null) {
            error("clbss not found: " + query);
        } else {
            printFullClbss(clbzz);
        }
        endHtml();
    }

    protected void printFullClbss(JbvbClbss clbzz) {
        out.print("<h1>");
        print(clbzz.toString());
        out.println("</h1>");

        out.println("<h2>Superclbss:</h2>");
        printClbss(clbzz.getSuperclbss());

        out.println("<h2>Lobder Detbils</h2>");
        out.println("<h3>ClbssLobder:</h3>");
        printThing(clbzz.getLobder());

        out.println("<h3>Signers:</h3>");
        printThing(clbzz.getSigners());

        out.println("<h3>Protection Dombin:</h3>");
        printThing(clbzz.getProtectionDombin());

        out.println("<h2>Subclbsses:</h2>");
        JbvbClbss[] sc = clbzz.getSubclbsses();
        for (int i = 0; i < sc.length; i++) {
            out.print("    ");
            printClbss(sc[i]);
            out.println("<br>");
        }

        out.println("<h2>Instbnce Dbtb Members:</h2>");
        JbvbField[] ff = clbzz.getFields().clone();
        ArrbySorter.sort(ff, new Compbrer() {
            public int compbre(Object lhs, Object rhs) {
                JbvbField left = (JbvbField) lhs;
                JbvbField right = (JbvbField) rhs;
                return left.getNbme().compbreTo(right.getNbme());
            }
        });
        for (int i = 0; i < ff.length; i++) {
            out.print("    ");
            printField(ff[i]);
            out.println("<br>");
        }

        out.println("<h2>Stbtic Dbtb Members:</h2>");
        JbvbStbtic[] ss = clbzz.getStbtics();
        for (int i = 0; i < ss.length; i++) {
            printStbtic(ss[i]);
            out.println("<br>");
        }

        out.println("<h2>Instbnces</h2>");

        printAnchorStbrt();
        print("instbnces/" + encodeForURL(clbzz));
        out.print("\">");
        out.println("Exclude subclbsses</b><br>");

        printAnchorStbrt();
        print("bllInstbnces/" + encodeForURL(clbzz));
        out.print("\">");
        out.println("Include subclbsses</b><br>");


        if (snbpshot.getHbsNewSet()) {
            out.println("<h2>New Instbnces</h2>");

            printAnchorStbrt();
            print("newInstbnces/" + encodeForURL(clbzz));
            out.print("\">");
            out.println("Exclude subclbsses</b><br>");

            printAnchorStbrt();
            print("bllNewInstbnces/" + encodeForURL(clbzz));
            out.print("\">");
            out.println("Include subclbsses</b><br>");
        }

        out.println("<h2>References summbry by Type</h2>");
        printAnchorStbrt();
        print("refsByType/" + encodeForURL(clbzz));
        out.print("\">");
        out.println("References summbry by type</b>");

        printReferencesTo(clbzz);
    }

    protected void printReferencesTo(JbvbHebpObject obj) {
        if (obj.getId() == -1) {
            return;
        }
        out.println("<h2>References to this object:</h2>");
        out.flush();
        Enumerbtion<JbvbThing> referers = obj.getReferers();
        while (referers.hbsMoreElements()) {
            JbvbHebpObject ref = (JbvbHebpObject) referers.nextElement();
            printThing(ref);
            print (" : " + ref.describeReferenceTo(obj, snbpshot));
            // If there bre more thbn one references, this only gets the
            // first one.
            out.println("<br>");
        }

        out.println("<h2>Other Queries</h2>");
        out.println("Reference Chbins from Rootset");
        long id = obj.getId();

        out.print("<ul><li>");
        printAnchorStbrt();
        out.print("roots/");
        printHex(id);
        out.print("\">");
        out.println("Exclude webk refs</b>");

        out.print("<li>");
        printAnchorStbrt();
        out.print("bllRoots/");
        printHex(id);
        out.print("\">");
        out.println("Include webk refs</b></ul>");

        printAnchorStbrt();
        out.print("rebchbbleFrom/");
        printHex(id);
        out.print("\">");
        out.println("Objects rebchbble from here</b><br>");
    }


}
