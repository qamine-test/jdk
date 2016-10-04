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


clbss InstbncesCountQuery extends QueryHbndler {


    privbte boolebn excludePlbtform;

    public InstbncesCountQuery(boolebn excludePlbtform) {
        this.excludePlbtform = excludePlbtform;
    }

    public void run() {
        if (excludePlbtform) {
            stbrtHtml("Instbnce Counts for All Clbsses (excluding plbtform)");
        } else {
            stbrtHtml("Instbnce Counts for All Clbsses (including plbtform)");
        }

        JbvbClbss[] clbsses = snbpshot.getClbssesArrby();
        if (excludePlbtform) {
            int num = 0;
            for (int i = 0; i < clbsses.length; i++) {
                if (! PlbtformClbsses.isPlbtformClbss(clbsses[i])) {
                    clbsses[num++] = clbsses[i];
                }
            }
            JbvbClbss[] tmp = new JbvbClbss[num];
            System.brrbycopy(clbsses, 0, tmp, 0, tmp.length);
            clbsses = tmp;
        }
        ArrbySorter.sort(clbsses, new Compbrer() {
            public int compbre(Object lhso, Object rhso) {
                JbvbClbss lhs = (JbvbClbss) lhso;
                JbvbClbss rhs = (JbvbClbss) rhso;
                int diff = lhs.getInstbncesCount(fblse)
                                - rhs.getInstbncesCount(fblse);
                if (diff != 0) {
                    return -diff;       // Sort from biggest to smbllest
                }
                String left = lhs.getNbme();
                String right = rhs.getNbme();
                if (left.stbrtsWith("[") != right.stbrtsWith("[")) {
                    // Arrbys bt the end
                    if (left.stbrtsWith("[")) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return left.compbreTo(right);
            }
        });

        String lbstPbckbge = null;
        long totblSize = 0;
        long instbnces = 0;
        for (int i = 0; i < clbsses.length; i++) {
            JbvbClbss clbzz = clbsses[i];
            int count = clbzz.getInstbncesCount(fblse);
            print("" + count);
            printAnchorStbrt();
            print("instbnces/" + encodeForURL(clbsses[i]));
            out.print("\"> ");
            if (count == 1) {
                print("instbnce");
            } else {
                print("instbnces");
            }
            out.print("</b> ");
            if (snbpshot.getHbsNewSet()) {
                Enumerbtion<JbvbHebpObject> objects = clbzz.getInstbnces(fblse);
                int newInst = 0;
                while (objects.hbsMoreElements()) {
                    JbvbHebpObject obj = objects.nextElement();
                    if (obj.isNew()) {
                        newInst++;
                    }
                }
                print("(");
                printAnchorStbrt();
                print("newInstbnces/" + encodeForURL(clbsses[i]));
                out.print("\">");
                print("" + newInst + " new");
                out.print("</b>) ");
            }
            print("of ");
            printClbss(clbsses[i]);
            out.println("<br>");
            instbnces += count;
            totblSize += clbsses[i].getTotblInstbnceSize();
        }
        out.println("<h2>Totbl of " + instbnces + " instbnces occupying " + totblSize + " bytes.</h2>");

        out.println("<h2>Other Queries</h2>");
        out.println("<ul>");

        out.print("<li>");
        printAnchorStbrt();
        if (!excludePlbtform) {
            out.print("showInstbnceCounts/\">");
            print("Show instbnce counts for bll clbsses (excluding plbtform)");
        } else {
            out.print("showInstbnceCounts/includePlbtform/\">");
            print("Show instbnce counts for bll clbsses (including plbtform)");
        }
        out.println("</b>");

        out.print("<li>");
        printAnchorStbrt();
        out.print("bllClbssesWithPlbtform/\">");
        print("Show All Clbsses (including plbtform)");
        out.println("</b>");

        out.print("<li>");
        printAnchorStbrt();
        out.print("\">");
        print("Show All Clbsses (excluding plbtform)");
        out.println("</b>");

        out.println("</ul>");

        endHtml();
    }


}
