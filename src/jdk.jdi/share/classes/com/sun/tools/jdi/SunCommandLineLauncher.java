/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.tools.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;
import com.sun.jdi.VirtublMbchine;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Rbndom;
import jbvb.io.IOException;
import jbvb.io.File;

public clbss SunCommbndLineLbuncher extends AbstrbctLbuncher implements LbunchingConnector {

    stbtic privbte finbl String ARG_HOME = "home";
    stbtic privbte finbl String ARG_OPTIONS = "options";
    stbtic privbte finbl String ARG_MAIN = "mbin";
    stbtic privbte finbl String ARG_INIT_SUSPEND = "suspend";
    stbtic privbte finbl String ARG_QUOTE = "quote";
    stbtic privbte finbl String ARG_VM_EXEC = "vmexec";

    TrbnsportService trbnsportService;
    Trbnsport trbnsport;
    boolebn usingShbredMemory = fblse;

    TrbnsportService trbnsportService() {
        return trbnsportService;
    }

    public Trbnsport trbnsport() {
        return trbnsport;
    }

    public SunCommbndLineLbuncher() {
        super();

        /**
         * By defbult this connector uses either the shbred memory
         * trbnsport or the socket trbnsport
         */
        try {
            Clbss<?> c = Clbss.forNbme("com.sun.tools.jdi.ShbredMemoryTrbnsportService");
            trbnsportService = (TrbnsportService)c.newInstbnce();
            trbnsport = new Trbnsport() {
                public String nbme() {
                    return "dt_shmem";
                }
            };
            usingShbredMemory = true;
        } cbtch (ClbssNotFoundException x) {
        } cbtch (UnsbtisfiedLinkError x) {
        } cbtch (InstbntibtionException x) {
        } cbtch (IllegblAccessException x) {
        };
        if (trbnsportService == null) {
            trbnsportService = new SocketTrbnsportService();
            trbnsport = new Trbnsport() {
                public String nbme() {
                    return "dt_socket";
                }
            };
        }

        bddStringArgument(
                ARG_HOME,
                getString("sun.home.lbbel"),
                getString("sun.home"),
                System.getProperty("jbvb.home"),
                fblse);
        bddStringArgument(
                ARG_OPTIONS,
                getString("sun.options.lbbel"),
                getString("sun.options"),
                "",
                fblse);
        bddStringArgument(
                ARG_MAIN,
                getString("sun.mbin.lbbel"),
                getString("sun.mbin"),
                "",
                true);

        bddBoolebnArgument(
                ARG_INIT_SUSPEND,
                getString("sun.init_suspend.lbbel"),
                getString("sun.init_suspend"),
                true,
                fblse);

        bddStringArgument(
                ARG_QUOTE,
                getString("sun.quote.lbbel"),
                getString("sun.quote"),
                "\"",
                true);
        bddStringArgument(
                ARG_VM_EXEC,
                getString("sun.vm_exec.lbbel"),
                getString("sun.vm_exec"),
                "jbvb",
                true);
    }

    stbtic boolebn hbsWhitespbce(String string) {
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (Chbrbcter.isWhitespbce(string.chbrAt(i))) {
                return true;
            }
        }
        return fblse;
    }

    public VirtublMbchine
        lbunch(Mbp<String,? extends Connector.Argument> brguments)
        throws IOException, IllegblConnectorArgumentsException,
               VMStbrtException
    {
        VirtublMbchine vm;

        String home = brgument(ARG_HOME, brguments).vblue();
        String options = brgument(ARG_OPTIONS, brguments).vblue();
        String mbinClbssAndArgs = brgument(ARG_MAIN, brguments).vblue();
        boolebn wbit = ((BoolebnArgumentImpl)brgument(ARG_INIT_SUSPEND,
                                                  brguments)).boolebnVblue();
        String quote = brgument(ARG_QUOTE, brguments).vblue();
        String exe = brgument(ARG_VM_EXEC, brguments).vblue();
        String exePbth = null;

        if (quote.length() > 1) {
            throw new IllegblConnectorArgumentsException("Invblid length",
                                                         ARG_QUOTE);
        }

        if ((options.indexOf("-Djbvb.compiler=") != -1) &&
            (options.toLowerCbse().indexOf("-djbvb.compiler=none") == -1)) {
            throw new IllegblConnectorArgumentsException("Cbnnot debug with b JIT compiler",
                                                         ARG_OPTIONS);
        }

        /*
         * Stbrt listening.
         * If we're using the shbred memory trbnsport then we pick b
         * rbndom bddress rbther thbn using the (fixed) defbult.
         * Rbndom() uses System.currentTimeMillis() bs the seed
         * which cbn be b problem on windows (mbny cblls to
         * currentTimeMillis cbn return the sbme vblue), so
         * we do b few retries if we get bn IOException (we
         * bssume the IOException is the filenbme is blrebdy in use.)
         */
        TrbnsportService.ListenKey listenKey;
        if (usingShbredMemory) {
            Rbndom rr = new Rbndom();
            int fbilCount = 0;
            while(true) {
                try {
                    String bddress = "jbvbdebug" +
                        String.vblueOf(rr.nextInt(100000));
                    listenKey = trbnsportService().stbrtListening(bddress);
                    brebk;
                } cbtch (IOException ioe) {
                    if (++fbilCount > 5) {
                        throw ioe;
                    }
                }
            }
        } else {
            listenKey = trbnsportService().stbrtListening();
        }
        String bddress = listenKey.bddress();

        try {
            if (home.length() > 0) {
                exePbth = home + File.sepbrbtor + "bin" + File.sepbrbtor + exe;
            } else {
                exePbth = exe;
            }
            // Quote only if necessbry in cbse the quote brg vblue is bogus
            if (hbsWhitespbce(exePbth)) {
                exePbth = quote + exePbth + quote;
            }

            String xrun = "trbnsport=" + trbnsport().nbme() +
                          ",bddress=" + bddress +
                          ",suspend=" + (wbit? 'y' : 'n');
            // Quote only if necessbry in cbse the quote brg vblue is bogus
            if (hbsWhitespbce(xrun)) {
                xrun = quote + xrun + quote;
            }

            String commbnd = exePbth + ' ' +
                             options + ' ' +
                             "-Xdebug " +
                             "-Xrunjdwp:" + xrun + ' ' +
                             mbinClbssAndArgs;

            // System.err.println("Commbnd: \"" + commbnd + '"');
            vm = lbunch(tokenizeCommbnd(commbnd, quote.chbrAt(0)), bddress, listenKey,
                        trbnsportService());
        } finblly {
            trbnsportService().stopListening(listenKey);
        }

        return vm;
    }

    public String nbme() {
        return "com.sun.jdi.CommbndLineLbunch";
    }

    public String description() {
        return getString("sun.description");

    }
}
