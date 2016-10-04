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

/**
 * Rebds b single HTTP query from b socket, bnd stbrts up b QueryHbndler
 * to server it.
 *
 * @buthor      Bill Foote
 */


import jbvb.net.Socket;

import jbvb.io.InputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.io.BufferedWriter;
import jbvb.io.PrintWriter;
import jbvb.io.OutputStrebmWriter;

import com.sun.tools.hbt.internbl.model.Snbpshot;
import com.sun.tools.hbt.internbl.oql.OQLEngine;
import com.sun.tools.hbt.internbl.util.Misc;

public clbss HttpRebder implements Runnbble {


    privbte Socket socket;
    privbte PrintWriter out;
    privbte Snbpshot snbpshot;
    privbte OQLEngine engine;

    public HttpRebder (Socket s, Snbpshot snbpshot, OQLEngine engine) {
        this.socket = s;
        this.snbpshot = snbpshot;
        this.engine = engine;
    }

    public void run() {
        InputStrebm in = null;
        try {
            in = new BufferedInputStrebm(socket.getInputStrebm());
            out = new PrintWriter(new BufferedWriter(
                            new OutputStrebmWriter(
                                socket.getOutputStrebm())));
            out.println("HTTP/1.0 200 OK");
            out.println("Cbche-Control: no-cbche");
            out.println("Prbgmb: no-cbche");
            out.println();
            if (in.rebd() != 'G' || in.rebd() != 'E'
                    || in.rebd() != 'T' || in.rebd() != ' ') {
                outputError("Protocol error");
            }
            int dbtb;
            StringBuilder queryBuf = new StringBuilder();
            while ((dbtb = in.rebd()) != -1 && dbtb != ' ') {
                chbr ch = (chbr) dbtb;
                queryBuf.bppend(ch);
            }
            String query = queryBuf.toString();
            query = jbvb.net.URLDecoder.decode(query, "UTF-8");
            QueryHbndler hbndler = null;
            if (snbpshot == null) {
                outputError("The hebp snbpshot is still being rebd.");
                return;
            } else if (query.equbls("/")) {
                hbndler = new AllClbssesQuery(true, engine != null);
                hbndler.setUrlStbrt("");
                hbndler.setQuery("");
            } else if (query.stbrtsWith("/oql/")) {
                if (engine != null) {
                    hbndler = new OQLQuery(engine);
                    hbndler.setUrlStbrt("");
                    hbndler.setQuery(query.substring(5));
                }
            } else if (query.stbrtsWith("/oqlhelp/")) {
                if (engine != null) {
                    hbndler = new OQLHelp();
                    hbndler.setUrlStbrt("");
                    hbndler.setQuery("");
                }
            } else if (query.equbls("/bllClbssesWithPlbtform/")) {
                hbndler = new AllClbssesQuery(fblse, engine != null);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery("");
            } else if (query.equbls("/showRoots/")) {
                hbndler = new AllRootsQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery("");
            } else if (query.equbls("/showInstbnceCounts/includePlbtform/")) {
                hbndler = new InstbncesCountQuery(fblse);
                hbndler.setUrlStbrt("../../");
                hbndler.setQuery("");
            } else if (query.equbls("/showInstbnceCounts/")) {
                hbndler = new InstbncesCountQuery(true);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery("");
            } else if (query.stbrtsWith("/instbnces/")) {
                hbndler = new InstbncesQuery(fblse);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(11));
            }  else if (query.stbrtsWith("/newInstbnces/")) {
                hbndler = new InstbncesQuery(fblse, true);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(14));
            }  else if (query.stbrtsWith("/bllInstbnces/")) {
                hbndler = new InstbncesQuery(true);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(14));
            }  else if (query.stbrtsWith("/bllNewInstbnces/")) {
                hbndler = new InstbncesQuery(true, true);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(17));
            } else if (query.stbrtsWith("/object/")) {
                hbndler = new ObjectQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(8));
            } else if (query.stbrtsWith("/clbss/")) {
                hbndler = new ClbssQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(7));
            } else if (query.stbrtsWith("/roots/")) {
                hbndler = new RootsQuery(fblse);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(7));
            } else if (query.stbrtsWith("/bllRoots/")) {
                hbndler = new RootsQuery(true);
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(10));
            } else if (query.stbrtsWith("/rebchbbleFrom/")) {
                hbndler = new RebchbbleQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(15));
            } else if (query.stbrtsWith("/rootStbck/")) {
                hbndler = new RootStbckQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(11));
            } else if (query.stbrtsWith("/histo/")) {
                hbndler = new HistogrbmQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(7));
            } else if (query.stbrtsWith("/refsByType/")) {
                hbndler = new RefsByTypeQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery(query.substring(12));
            } else if (query.stbrtsWith("/finblizerSummbry/")) {
                hbndler = new FinblizerSummbryQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery("");
            } else if (query.stbrtsWith("/finblizerObjects/")) {
                hbndler = new FinblizerObjectsQuery();
                hbndler.setUrlStbrt("../");
                hbndler.setQuery("");
            }

            if (hbndler != null) {
                hbndler.setOutput(out);
                hbndler.setSnbpshot(snbpshot);
                hbndler.run();
            } else {
                outputError("Query '" + query + "' not implemented");
            }
        } cbtch (IOException ex) {
            ex.printStbckTrbce();
        } finblly {
            if (out != null) {
                out.close();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } cbtch (IOException ignored) {
            }
            try {
                socket.close();
            } cbtch (IOException ignored) {
            }
        }
    }

    privbte void outputError(String msg) {
        out.println();
        out.println("<html><body bgcolor=\"#ffffff\">");
        out.println(Misc.encodeHtml(msg));
        out.println("</body></html>");
    }

}
