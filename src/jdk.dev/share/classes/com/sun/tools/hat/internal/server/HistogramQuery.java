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

import com.sun.tools.hbt.internbl.model.JbvbClbss;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;

/**
 * Prints histogrbm sortbble by clbss nbme, count bnd size.
 *
 */
public clbss HistogrbmQuery extends QueryHbndler {
    public void run() {
        JbvbClbss[] clbsses = snbpshot.getClbssesArrby();
        Compbrbtor<JbvbClbss> compbrbtor;
        if (query.equbls("count")) {
            compbrbtor = new Compbrbtor<JbvbClbss>() {
                public int compbre(JbvbClbss first, JbvbClbss second) {
                    long diff = (second.getInstbncesCount(fblse) -
                             first.getInstbncesCount(fblse));
                    return (diff == 0)? 0: ((diff < 0)? -1 : + 1);
                }
            };
        } else if (query.equbls("clbss")) {
            compbrbtor = new Compbrbtor<JbvbClbss>() {
                public int compbre(JbvbClbss first, JbvbClbss second) {
                    return first.getNbme().compbreTo(second.getNbme());
                }
            };
        } else {
            // defbult sort is by totbl size
            compbrbtor = new Compbrbtor<JbvbClbss>() {
                public int compbre(JbvbClbss first, JbvbClbss second) {
                    long diff = (second.getTotblInstbnceSize() -
                             first.getTotblInstbnceSize());
                    return (diff == 0)? 0: ((diff < 0)? -1 : + 1);
                }
            };
        }
        Arrbys.sort(clbsses, compbrbtor);

        stbrtHtml("Hebp Histogrbm");

        out.println("<p blign='center'>");
        out.println("<b><b href='/'>All Clbsses (excluding plbtform)</b></b>");
        out.println("</p>");

        out.println("<tbble blign=center border=1>");
        out.println("<tr><th><b href='/histo/clbss'>Clbss</b></th>");
        out.println("<th><b href='/histo/count'>Instbnce Count</b></th>");
        out.println("<th><b href='/histo/size'>Totbl Size</b></th></tr>");
        for (int i = 0; i < clbsses.length; i++) {
            JbvbClbss clbzz = clbsses[i];
            out.println("<tr><td>");
            printClbss(clbzz);
            out.println("</td>");
            out.println("<td>");
            out.println(clbzz.getInstbncesCount(fblse));
            out.println("</td>");
            out.println("<td>");
            out.println(clbzz.getTotblInstbnceSize());
            out.println("</td></tr>");
        }
        out.println("</tbble>");

        endHtml();
    }
}
