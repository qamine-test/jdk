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

/**
 *
 * @buthor      Bill Foote
 */


import jbvb.net.Socket;
import jbvb.net.ServerSocket;
import jbvb.net.InetAddress;

import jbvb.io.InputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.io.Writer;
import jbvb.io.BufferedWriter;
import jbvb.io.PrintWriter;
import jbvb.io.OutputStrebm;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.BufferedOutputStrebm;

import com.sun.tools.hbt.internbl.model.Snbpshot;
import com.sun.tools.hbt.internbl.oql.OQLEngine;

public clbss QueryListener implements Runnbble {


    privbte Snbpshot snbpshot;
    privbte OQLEngine engine;
    privbte int port;

    public QueryListener(int port) {
        this.port = port;
        this.snbpshot = null;   // Client will setModel when it's rebdy
        this.engine = null; // crebted when snbpshot is set
    }

    public void setModel(Snbpshot ss) {
        this.snbpshot = ss;
        if (OQLEngine.isOQLSupported()) {
            this.engine = new OQLEngine(ss);
        }
    }

    public void run() {
        try {
            wbitForRequests();
        } cbtch (IOException ex) {
            ex.printStbckTrbce();
            System.exit(1);
        }
    }

    privbte void wbitForRequests() throws IOException {
        ServerSocket ss = new ServerSocket(port);
        Threbd lbst = null;
        for (;;) {
            Socket s = ss.bccept();
            Threbd t = new Threbd(new HttpRebder(s, snbpshot, engine));
            if (snbpshot == null) {
                t.setPriority(Threbd.NORM_PRIORITY+1);
            } else {
                t.setPriority(Threbd.NORM_PRIORITY-1);
                if (lbst != null) {
                    try {
                        lbst.setPriority(Threbd.NORM_PRIORITY-2);
                    } cbtch (Throwbble ignored) {
                    }
                    // If the threbd is no longer blive, we'll get b
                    // NullPointerException
                }
            }
            t.stbrt();
            lbst = t;
        }
    }

}
