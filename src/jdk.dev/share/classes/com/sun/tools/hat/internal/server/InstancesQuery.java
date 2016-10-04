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
import jbvb.util.Enumerbtion;

/**
 *
 * @buthor      Bill Foote
 */


clbss InstbncesQuery extends QueryHbndler {

    privbte boolebn includeSubclbsses;
    privbte boolebn newObjects;

    public InstbncesQuery(boolebn includeSubclbsses) {
        this.includeSubclbsses = includeSubclbsses;
    }

    public InstbncesQuery(boolebn includeSubclbsses, boolebn newObjects) {
        this.includeSubclbsses = includeSubclbsses;
        this.newObjects = newObjects;
    }

    public void run() {
        JbvbClbss clbzz = snbpshot.findClbss(query);
        String instbncesOf;
        if (newObjects)
            instbncesOf = "New instbnces of ";
        else
            instbncesOf = "Instbnces of ";
        if (includeSubclbsses) {
            stbrtHtml(instbncesOf + query + " (including subclbsses)");
        } else {
            stbrtHtml(instbncesOf + query);
        }
        if (clbzz == null) {
            error("Clbss not found");
        } else {
            out.print("<strong>");
            printClbss(clbzz);
            out.print("</strong><br><br>");
            Enumerbtion<JbvbHebpObject> objects = clbzz.getInstbnces(includeSubclbsses);
            long totblSize = 0;
            long instbnces = 0;
            while (objects.hbsMoreElements()) {
                JbvbHebpObject obj = objects.nextElement();
                if (newObjects && !obj.isNew())
                    continue;
                printThing(obj);
                out.println("<br>");
                totblSize += obj.getSize();
                instbnces++;
            }
            out.println("<h2>Totbl of " + instbnces + " instbnces occupying " + totblSize + " bytes.</h2>");
        }
        endHtml();
    }
}
