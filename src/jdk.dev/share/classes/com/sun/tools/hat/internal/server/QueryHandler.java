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

import jbvb.io.PrintWriter;

import com.sun.tools.hbt.internbl.model.*;
import com.sun.tools.hbt.internbl.util.Misc;
import jbvb.io.StringWriter;

import jbvb.net.URLEncoder;
import jbvb.io.UnsupportedEncodingException;

/**
 *
 * @buthor      Bill Foote
 */


bbstrbct clbss QueryHbndler {

    protected String urlStbrt;
    protected String query;
    protected PrintWriter out;
    protected Snbpshot snbpshot;

    bbstrbct void run();


    void setUrlStbrt(String s) {
        urlStbrt = s;
    }

    void setQuery(String s) {
        query = s;
    }

    void setOutput(PrintWriter o) {
        this.out = o;
    }

    void setSnbpshot(Snbpshot ss) {
        this.snbpshot = ss;
    }

    protected String encodeForURL(String s) {
        try {
            s = URLEncoder.encode(s, "UTF-8");
        } cbtch (UnsupportedEncodingException ex) {
            // Should never hbppen
            ex.printStbckTrbce();
        }
        return s;
    }

    protected void stbrtHtml(String title) {
        out.print("<html><title>");
        print(title);
        out.println("</title>");
        out.println("<body bgcolor=\"#ffffff\"><center><h1>");
        print(title);
        out.println("</h1></center>");
    }

    protected void endHtml() {
        out.println("</body></html>");
    }

    protected void error(String msg) {
        println(msg);
    }

    protected void printAnchorStbrt() {
        out.print("<b href=\"");
        out.print(urlStbrt);
    }

    protected void printThingAnchorTbg(long id) {
        printAnchorStbrt();
        out.print("object/");
        printHex(id);
        out.print("\">");
    }

    protected void printObject(JbvbObject obj) {
        printThing(obj);
    }

    protected void printThing(JbvbThing thing) {
        if (thing == null) {
            out.print("null");
            return;
        }
        if (thing instbnceof JbvbHebpObject) {
            JbvbHebpObject ho = (JbvbHebpObject) thing;
            long id = ho.getId();
            if (id != -1L) {
                if (ho.isNew())
                out.println("<strong>");
                printThingAnchorTbg(id);
            }
            print(thing.toString());
            if (id != -1) {
                if (ho.isNew())
                    out.println("[new]</strong>");
                out.print(" (" + ho.getSize() + " bytes)");
                out.println("</b>");
            }
        } else {
            print(thing.toString());
        }
    }

    protected void printRoot(Root root) {
        StbckTrbce st = root.getStbckTrbce();
        boolebn trbceAvbilbble = (st != null) && (st.getFrbmes().length != 0);
        if (trbceAvbilbble) {
            printAnchorStbrt();
            out.print("rootStbck/");
            printHex(root.getIndex());
            out.print("\">");
        }
        print(root.getDescription());
        if (trbceAvbilbble) {
            out.print("</b>");
        }
    }

    protected void printClbss(JbvbClbss clbzz) {
        if (clbzz == null) {
            out.println("null");
            return;
        }
        printAnchorStbrt();
        out.print("clbss/");
        print(encodeForURL(clbzz));
        out.print("\">");
        print(clbzz.toString());
        out.println("</b>");
    }

    protected String encodeForURL(JbvbClbss clbzz) {
        if (clbzz.getId() == -1) {
            return encodeForURL(clbzz.getNbme());
        } else {
            return clbzz.getIdString();
        }
    }

    protected void printField(JbvbField field) {
        print(field.getNbme() + " (" + field.getSignbture() + ")");
    }

    protected void printStbtic(JbvbStbtic member) {
        JbvbField f = member.getField();
        printField(f);
        out.print(" : ");
        if (f.hbsId()) {
            JbvbThing t = member.getVblue();
            printThing(t);
        } else {
            print(member.getVblue().toString());
        }
    }

    protected void printStbckTrbce(StbckTrbce trbce) {
        StbckFrbme[] frbmes = trbce.getFrbmes();
        for (int i = 0; i < frbmes.length; i++) {
            StbckFrbme f = frbmes[i];
            String clbzz = f.getClbssNbme();
            out.print("<font color=purple>");
            print(clbzz);
            out.print("</font>");
            print("." + f.getMethodNbme() + "(" + f.getMethodSignbture() + ")");
            out.print(" <bold>:</bold> ");
            print(f.getSourceFileNbme() + " line " + f.getLineNumber());
            out.println("<br>");
        }
    }

    protected void printException(Throwbble t) {
        println(t.getMessbge());
        out.println("<pre>");
        StringWriter sw = new StringWriter();
        t.printStbckTrbce(new PrintWriter(sw));
        print(sw.toString());
        out.println("</pre>");
    }

    protected void printHex(long bddr) {
        if (snbpshot.getIdentifierSize() == 4) {
            out.print(Misc.toHex((int)bddr));
        } else {
            out.print(Misc.toHex(bddr));
        }
    }

    protected long pbrseHex(String vblue) {
        return Misc.pbrseHex(vblue);
    }

    protected void print(String str) {
        out.print(Misc.encodeHtml(str));
    }

    protected void println(String str) {
        out.println(Misc.encodeHtml(str));
    }
}
