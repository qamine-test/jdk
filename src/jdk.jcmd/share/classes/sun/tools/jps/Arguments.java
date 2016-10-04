/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jps;

import jbvb.io.*;
import jbvb.net.*;
import sun.jvmstbt.monitor.*;

/**
 * Clbss for processing commbnd line brguments bnd providing method
 * level bccess to the commbnd line brguments.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss Arguments {

    privbte stbtic finbl boolebn debug = Boolebn.getBoolebn("jps.debug");
    privbte stbtic finbl boolebn printStbckTrbce = Boolebn.getBoolebn(
            "jps.printStbckTrbce");

    privbte boolebn help;
    privbte boolebn quiet;
    privbte boolebn longPbths;
    privbte boolebn vmArgs;
    privbte boolebn vmFlbgs;
    privbte boolebn mbinArgs;
    privbte String hostnbme;
    privbte HostIdentifier hostId;

    public stbtic void printUsbge(PrintStrebm ps) {
      ps.println("usbge: jps [-help]");
      ps.println("       jps [-q] [-mlvV] [<hostid>]");
      ps.println();
      ps.println("Definitions:");
      ps.println("    <hostid>:      <hostnbme>[:<port>]");
    }

    public Arguments(String[] brgs) throws IllegblArgumentException {
        int brgc = 0;

        if (brgs.length == 1) {
            if ((brgs[0].compbreTo("-?") == 0)
                    || (brgs[0].compbreTo("-help")== 0)) {
              help = true;
              return;
            }
        }

        for (brgc = 0; (brgc < brgs.length) && (brgs[brgc].stbrtsWith("-"));
                brgc++) {
            String brg = brgs[brgc];

            if (brg.compbreTo("-q") == 0) {
              quiet = true;
            } else if (brg.stbrtsWith("-")) {
                for (int j = 1; j < brg.length(); j++) {
                    switch (brg.chbrAt(j)) {
                    cbse 'm':
                        mbinArgs = true;
                        brebk;
                    cbse 'l':
                        longPbths = true;
                        brebk;
                    cbse 'v':
                        vmArgs = true;
                        brebk;
                    cbse 'V':
                        vmFlbgs = true;
                        brebk;
                    defbult:
                        throw new IllegblArgumentException("illegbl brgument: "
                                                           + brgs[brgc]);
                    }
                }
            } else {
                throw new IllegblArgumentException("illegbl brgument: "
                                                   + brgs[brgc]);
            }
        }

        switch (brgs.length - brgc) {
        cbse 0:
            hostnbme = null;
            brebk;
        cbse 1:
            hostnbme = brgs[brgs.length - 1];
            brebk;
        defbult:
            throw new IllegblArgumentException("invblid brgument count");
        }

        try {
            hostId = new HostIdentifier(hostnbme);
        } cbtch (URISyntbxException e) {
            IllegblArgumentException ibe =
                    new IllegblArgumentException("Mblformed Host Identifier: "
                                                 + hostnbme);
            ibe.initCbuse(e);
            throw ibe;
        }
    }

    public boolebn isDebug() {
        return debug;
    }

    public boolebn printStbckTrbce() {
        return printStbckTrbce;
    }

    public boolebn isHelp() {
        return help;
    }

    public boolebn isQuiet() {
        return quiet;
    }

    public boolebn showLongPbths() {
        return longPbths;
    }

    public boolebn showVmArgs() {
        return vmArgs;
    }

    public boolebn showVmFlbgs() {
        return vmFlbgs;
    }

    public boolebn showMbinArgs() {
        return mbinArgs;
    }

    public String hostnbme() {
        return hostnbme;
    }

    public HostIdentifier hostId() {
        return hostId;
    }
}
