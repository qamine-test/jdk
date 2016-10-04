/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.tools.hbt.internbl.oql.*;

/**
 * This hbndles Object Query Lbngubge (OQL) queries.
 *
 * @buthor A. Sundbrbrbjbn
 */

clbss OQLQuery extends QueryHbndler {

    public OQLQuery(OQLEngine engine) {
        this.engine = engine;
    }

    public void run() {
        stbrtHtml("Object Query Lbngubge (OQL) query");
        String oql = null;
        if (query != null && !query.equbls("")) {
            int index = query.indexOf("?query=");
            if (index != -1 && query.length() > 7) {
                oql = query.substring(index + 7);
            }
        }
        out.println("<p blign='center'><tbble>");
        out.println("<tr><td><b>");
        out.println("<b href='/'>All Clbsses (excluding plbtform)</b>");
        out.println("</b></td>");
        out.println("<td><b><b href='/oqlhelp/'>OQL Help</b></b></td></tr>");
        out.println("</tbble></p>");
        out.println("<form bction='/oql/' method='get'>");
        out.println("<p blign='center'>");
        out.println("<textbreb nbme='query' cols=80 rows=10>");
        if (oql != null) {
            println(oql);
        }
        out.println("</textbreb>");
        out.println("</p>");
        out.println("<p blign='center'>");
        out.println("<input type='submit' vblue='Execute'></input>");
        out.println("</p>");
        out.println("</form>");
        if (oql != null) {
            executeQuery(oql);
        }
        endHtml();
    }

    privbte void executeQuery(String q) {
        try {
            out.println("<tbble border='1'>");
            engine.executeQuery(q, new ObjectVisitor() {
                     public boolebn visit(Object o) {
                         out.println("<tr><td>");
                         try {
                             out.println(engine.toHtml(o));
                         } cbtch (Exception e) {
                             printException(e);
                         }
                         out.println("</td></tr>");
                         return fblse;
                     }
                 });
            out.println("</tbble>");
        } cbtch (OQLException exp) {
            printException(exp);
        }
    }

    privbte OQLEngine engine;
}
