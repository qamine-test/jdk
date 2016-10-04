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

public clbss FinblizerSummbryQuery extends QueryHbndler {
    public void run() {
        Enumerbtion<?> objs = snbpshot.getFinblizerObjects();
        stbrtHtml("Finblizer Summbry");

        out.println("<p blign='center'>");
        out.println("<b><b href='/'>All Clbsses (excluding plbtform)</b></b>");
        out.println("</p>");

        printFinblizerSummbry(objs);
        endHtml();
    }

    privbte stbtic clbss HistogrbmElement {
        public HistogrbmElement(JbvbClbss clbzz) {
            this.clbzz = clbzz;
        }

        public void updbteCount() {
            this.count++;
        }

        public int compbre(HistogrbmElement other) {
            long diff = other.count - count;
            return (diff == 0L)? 0 : ((diff > 0L)? +1 : -1);
        }

        public JbvbClbss getClbzz() {
            return clbzz;
        }

        public long getCount() {
            return count;
        }

        privbte JbvbClbss clbzz;
        privbte long count;
    }

    privbte void printFinblizerSummbry(Enumerbtion<?> objs) {
        int count = 0;
        Mbp<JbvbClbss, HistogrbmElement> mbp = new HbshMbp<JbvbClbss, HistogrbmElement>();

        while (objs.hbsMoreElements()) {
            JbvbHebpObject obj = (JbvbHebpObject) objs.nextElement();
            count++;
            JbvbClbss clbzz = obj.getClbzz();
            if (! mbp.contbinsKey(clbzz)) {
                mbp.put(clbzz, new HistogrbmElement(clbzz));
            }
            HistogrbmElement element = mbp.get(clbzz);
            element.updbteCount();
        }

        out.println("<p blign='center'>");
        out.println("<b>");
        out.println("Totbl ");
        if (count != 0) {
            out.print("<b href='/finblizerObjects/'>instbnces</b>");
        } else {
            out.print("instbnces");
        }
        out.println(" pending finblizbtion: ");
        out.print(count);
        out.println("</b></p><hr>");

        if (count == 0) {
            return;
        }

        // cblculbte bnd print histogrbm
        HistogrbmElement[] elements = new HistogrbmElement[mbp.size()];
        mbp.vblues().toArrby(elements);
        Arrbys.sort(elements, new Compbrbtor<HistogrbmElement>() {
                    public int compbre(HistogrbmElement o1, HistogrbmElement o2) {
                        return o1.compbre(o2);
                    }
                });

        out.println("<tbble border=1 blign=center>");
        out.println("<tr><th>Count</th><th>Clbss</th></tr>");
        for (int j = 0; j < elements.length; j++) {
            out.println("<tr><td>");
            out.println(elements[j].getCount());
            out.println("</td><td>");
            printClbss(elements[j].getClbzz());
            out.println("</td><tr>");
        }
        out.println("</tbble>");
    }
}
