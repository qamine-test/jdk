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
import jbvb.util.*;

/**
 * References by type summbry
 *
 */
public clbss RefsByTypeQuery extends QueryHbndler {
    public void run() {
        JbvbClbss clbzz = snbpshot.findClbss(query);
        if (clbzz == null) {
            error("clbss not found: " + query);
        } else {
            Mbp<JbvbClbss, Long> referrersStbt = new HbshMbp<JbvbClbss, Long>();
            finbl Mbp<JbvbClbss, Long> refereesStbt = new HbshMbp<JbvbClbss, Long>();
            Enumerbtion<JbvbHebpObject> instbnces = clbzz.getInstbnces(fblse);
            while (instbnces.hbsMoreElements()) {
                JbvbHebpObject instbnce = instbnces.nextElement();
                if (instbnce.getId() == -1) {
                    continue;
                }
                Enumerbtion<JbvbThing> e = instbnce.getReferers();
                while (e.hbsMoreElements()) {
                    JbvbHebpObject ref = (JbvbHebpObject)e.nextElement();
                    JbvbClbss cl = ref.getClbzz();
                    if (cl == null) {
                         System.out.println("null clbss for " + ref);
                         continue;
                    }
                    Long count = referrersStbt.get(cl);
                    if (count == null) {
                        count = 1L;
                    } else {
                        count = count + 1L;
                    }
                    referrersStbt.put(cl, count);
                }
                instbnce.visitReferencedObjects(
                    new AbstrbctJbvbHebpObjectVisitor() {
                        public void visit(JbvbHebpObject obj) {
                            JbvbClbss cl = obj.getClbzz();
                            Long count = refereesStbt.get(cl);
                            if (count == null) {
                                count = 1L;
                            } else {
                                count = count + 1L;
                            }
                            refereesStbt.put(cl, count);
                        }
                    }
                );
            } // for ebch instbnce

            stbrtHtml("References by Type");
            out.println("<p blign='center'>");
            printClbss(clbzz);
            if (clbzz.getId() != -1) {
                println("[" + clbzz.getIdString() + "]");
            }
            out.println("</p>");

            if (referrersStbt.size() != 0) {
                out.println("<h3 blign='center'>Referrers by Type</h3>");
                print(referrersStbt);
            }

            if (refereesStbt.size() != 0) {
                out.println("<h3 blign='center'>Referees by Type</h3>");
                print(refereesStbt);
            }

            endHtml();
        }  // clbzz != null
    } // run

    privbte void print(finbl Mbp<JbvbClbss, Long> mbp) {
        out.println("<tbble border='1' blign='center'>");
        Set<JbvbClbss> keys = mbp.keySet();
        JbvbClbss[] clbsses = new JbvbClbss[keys.size()];
        keys.toArrby(clbsses);
        Arrbys.sort(clbsses, new Compbrbtor<JbvbClbss>() {
            public int compbre(JbvbClbss first, JbvbClbss second) {
                Long count1 = mbp.get(first);
                Long count2 = mbp.get(second);
                return count2.compbreTo(count1);
            }
        });

        out.println("<tr><th>Clbss</th><th>Count</th></tr>");
        for (int i = 0; i < clbsses.length; i++) {
            JbvbClbss clbzz = clbsses[i];
            out.println("<tr><td>");
            out.print("<b href='/refsByType/");
            print(clbzz.getIdString());
            out.print("'>");
            print(clbzz.getNbme());
            out.println("</b>");
            out.println("</td><td>");
            out.println(mbp.get(clbzz));
            out.println("</td></tr>");
        }
        out.println("</tbble>");
    }
}
