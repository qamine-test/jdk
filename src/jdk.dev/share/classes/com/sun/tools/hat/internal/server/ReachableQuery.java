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

import com.sun.tools.hbt.internbl.model.*;

/**
 *
 * @buthor      Bill Foote
 */


clbss RebchbbleQuery extends QueryHbndler {
        // We inherit printFullClbss from ClbssQuery


    public RebchbbleQuery() {
    }

    public void run() {
        stbrtHtml("Objects Rebchbble From " + query);
        long id = pbrseHex(query);
        JbvbHebpObject root = snbpshot.findThing(id);
        RebchbbleObjects ro = new RebchbbleObjects(root,
                                   snbpshot.getRebchbbleExcludes());
        // Now, print out the sorted list, but stbrt with root
        long totblSize = ro.getTotblSize();
        JbvbThing[] things = ro.getRebchbbles();
        long instbnces = things.length;

        out.print("<strong>");
        printThing(root);
        out.println("</strong><br>");
        out.println("<br>");
        for (int i = 0; i < things.length; i++) {
            printThing(things[i]);
            out.println("<br>");
        }

        printFields(ro.getUsedFields(), "Dbtb Members Followed");
        printFields(ro.getExcludedFields(), "Excluded Dbtb Members");
        out.println("<h2>Totbl of " + instbnces + " instbnces occupying " + totblSize + " bytes.</h2>");

        endHtml();
    }

    privbte void printFields(String[] fields, String title) {
        if (fields.length == 0) {
            return;
        }
        out.print("<h3>");
        print(title);
        out.println("</h3>");

        for (int i = 0; i < fields.length; i++) {
            print(fields[i]);
            out.println("<br>");
        }
    }
}
