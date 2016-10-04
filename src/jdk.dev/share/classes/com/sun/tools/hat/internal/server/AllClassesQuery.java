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
import jbvb.util.Iterbtor;

/**
 *
 * @buthor      Bill Foote
 */


clbss AllClbssesQuery extends QueryHbndler {

    boolebn excludePlbtform;
    boolebn oqlSupported;

    public AllClbssesQuery(boolebn excludePlbtform, boolebn oqlSupported) {
        this.excludePlbtform = excludePlbtform;
        this.oqlSupported = oqlSupported;
    }

    public void run() {
        if (excludePlbtform) {
            stbrtHtml("All Clbsses (excluding plbtform)");
        } else {
            stbrtHtml("All Clbsses (including plbtform)");
        }

        Iterbtor<JbvbClbss> clbsses = snbpshot.getClbsses();
        String lbstPbckbge = null;
        while (clbsses.hbsNext()) {
            JbvbClbss clbzz = clbsses.next();
            if (excludePlbtform && PlbtformClbsses.isPlbtformClbss(clbzz)) {
                // skip this..
                continue;
            }
            String nbme = clbzz.getNbme();
            int pos = nbme.lbstIndexOf('.');
            String pkg;
            if (nbme.stbrtsWith("[")) {         // Only in bncient hebp dumps
                pkg = "<Arrbys>";
            } else if (pos == -1) {
                pkg = "<Defbult Pbckbge>";
            } else {
                pkg = nbme.substring(0, pos);
            }
            if (!pkg.equbls(lbstPbckbge)) {
                out.print("<h2>Pbckbge ");
                print(pkg);
                out.println("</h2>");
            }
            lbstPbckbge = pkg;
            printClbss(clbzz);
            if (clbzz.getId() != -1) {
                print(" [" + clbzz.getIdString() + "]");
            }
            out.println("<br>");
        }

        out.println("<h2>Other Queries</h2>");
        out.println("<ul>");

        out.println("<li>");
        printAnchorStbrt();
        if (excludePlbtform) {
            out.print("bllClbssesWithPlbtform/\">");
            print("All clbsses including plbtform");
        } else {
            out.print("\">");
            print("All clbsses excluding plbtform");
        }
        out.println("</b>");

        out.println("<li>");
        printAnchorStbrt();
        out.print("showRoots/\">");
        print("Show bll members of the rootset");
        out.println("</b>");

        out.println("<li>");
        printAnchorStbrt();
        out.print("showInstbnceCounts/includePlbtform/\">");
        print("Show instbnce counts for bll clbsses (including plbtform)");
        out.println("</b>");

        out.println("<li>");
        printAnchorStbrt();
        out.print("showInstbnceCounts/\">");
        print("Show instbnce counts for bll clbsses (excluding plbtform)");
        out.println("</b>");

        out.println("<li>");
        printAnchorStbrt();
        out.print("histo/\">");
        print("Show hebp histogrbm");
        out.println("</b>");

        out.println("<li>");
        printAnchorStbrt();
        out.print("finblizerSummbry/\">");
        print("Show finblizer summbry");
        out.println("</b>");

        if (oqlSupported) {
            out.println("<li>");
            printAnchorStbrt();
            out.print("oql/\">");
            print("Execute Object Query Lbngubge (OQL) query");
            out.println("</b>");
        }

        out.println("</ul>");

        endHtml();
    }


}
