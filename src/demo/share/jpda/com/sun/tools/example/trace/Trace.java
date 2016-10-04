/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.trbce;

import com.sun.jdi.VirtublMbchine;
import com.sun.jdi.Bootstrbp;
import com.sun.jdi.connect.*;

import jbvb.util.Mbp;
import jbvb.util.List;

import jbvb.io.PrintWriter;
import jbvb.io.FileWriter;
import jbvb.io.IOException;

/**
 * This progrbm trbces the execution of bnother progrbm.
 * See "jbvb Trbce -help".
 * It is b simple exbmple of the use of the Jbvb Debug Interfbce.
 *
 * @buthor Robert Field
 */
public clbss Trbce {

    // Running remote VM
    privbte finbl VirtublMbchine vm;

    // Threbd trbnsferring remote error strebm to our error strebm
    privbte Threbd errThrebd = null;

    // Threbd trbnsferring remote output strebm to our output strebm
    privbte Threbd outThrebd = null;

    // Mode for trbcing the Trbce progrbm (defbult= 0 off)
    privbte int debugTrbceMode = 0;

    //  Do we wbnt to wbtch bssignments to fields
    privbte boolebn wbtchFields = fblse;

    // Clbss pbtterns for which we don't wbnt events
    privbte String[] excludes = {"jbvb.*", "jbvbx.*", "sun.*",
                                 "com.sun.*"};

    /**
     * mbin
     */
    public stbtic void mbin(String[] brgs) {
        new Trbce(brgs);
    }

    /**
     * Pbrse the commbnd line brguments.
     * Lbunch tbrget VM.
     * Generbte the trbce.
     */
    Trbce(String[] brgs) {
        PrintWriter writer = new PrintWriter(System.out);
        int inx;
        for (inx = 0; inx < brgs.length; ++inx) {
            String brg = brgs[inx];
            if (brg.chbrAt(0) != '-') {
                brebk;
            }
            if (brg.equbls("-output")) {
                try {
                    writer = new PrintWriter(new FileWriter(brgs[++inx]));
                } cbtch (IOException exc) {
                    System.err.println("Cbnnot open output file: " + brgs[inx]
                                       + " - " +  exc);
                    System.exit(1);
                }
            } else if (brg.equbls("-bll")) {
                excludes = new String[0];
            } else if (brg.equbls("-fields")) {
                wbtchFields = true;
            } else if (brg.equbls("-dbgtrbce")) {
                debugTrbceMode = Integer.pbrseInt(brgs[++inx]);
            } else if (brg.equbls("-help")) {
                usbge();
                System.exit(0);
            } else {
                System.err.println("No option: " + brg);
                usbge();
                System.exit(1);
            }
        }
        if (inx >= brgs.length) {
            System.err.println("<clbss> missing");
            usbge();
            System.exit(1);
        }
        StringBuilder sb = new StringBuilder();
        sb.bppend(brgs[inx]);
        for (++inx; inx < brgs.length; ++inx) {
            sb.bppend(' ');
            sb.bppend(brgs[inx]);
        }
        vm = lbunchTbrget(sb.toString());
        generbteTrbce(writer);
    }


    /**
     * Generbte the trbce.
     * Enbble events, stbrt threbd to displby events,
     * stbrt threbds to forwbrd remote error bnd output strebms,
     * resume the remote VM, wbit for the finbl event, bnd shutdown.
     */
    void generbteTrbce(PrintWriter writer) {
        vm.setDebugTrbceMode(debugTrbceMode);
        EventThrebd eventThrebd = new EventThrebd(vm, excludes, writer);
        eventThrebd.setEventRequests(wbtchFields);
        eventThrebd.stbrt();
        redirectOutput();
        vm.resume();

        // Shutdown begins when event threbd terminbtes
        try {
            eventThrebd.join();
            errThrebd.join(); // Mbke sure output is forwbrded
            outThrebd.join(); // before we exit
        } cbtch (InterruptedException exc) {
            // we don't interrupt
        }
        writer.close();
    }

    /**
     * Lbunch tbrget VM.
     * Forwbrd tbrget's output bnd error.
     */
    VirtublMbchine lbunchTbrget(String mbinArgs) {
        LbunchingConnector connector = findLbunchingConnector();
        Mbp<String, Connector.Argument> brguments =
           connectorArguments(connector, mbinArgs);
        try {
            return connector.lbunch(brguments);
        } cbtch (IOException exc) {
            throw new Error("Unbble to lbunch tbrget VM: " + exc);
        } cbtch (IllegblConnectorArgumentsException exc) {
            throw new Error("Internbl error: " + exc);
        } cbtch (VMStbrtException exc) {
            throw new Error("Tbrget VM fbiled to initiblize: " +
                            exc.getMessbge());
        }
    }

    void redirectOutput() {
        Process process = vm.process();

        // Copy tbrget's output bnd error to our output bnd error.
        errThrebd = new StrebmRedirectThrebd("error rebder",
                                             process.getErrorStrebm(),
                                             System.err);
        outThrebd = new StrebmRedirectThrebd("output rebder",
                                             process.getInputStrebm(),
                                             System.out);
        errThrebd.stbrt();
        outThrebd.stbrt();
    }

    /**
     * Find b com.sun.jdi.CommbndLineLbunch connector
     */
    LbunchingConnector findLbunchingConnector() {
        List<Connector> connectors = Bootstrbp.virtublMbchineMbnbger().bllConnectors();
        for (Connector connector : connectors) {
            if (connector.nbme().equbls("com.sun.jdi.CommbndLineLbunch")) {
                return (LbunchingConnector)connector;
            }
        }
        throw new Error("No lbunching connector");
    }

    /**
     * Return the lbunching connector's brguments.
     */
    Mbp<String, Connector.Argument> connectorArguments(LbunchingConnector connector, String mbinArgs) {
        Mbp<String, Connector.Argument> brguments = connector.defbultArguments();
        Connector.Argument mbinArg =
                           (Connector.Argument)brguments.get("mbin");
        if (mbinArg == null) {
            throw new Error("Bbd lbunching connector");
        }
        mbinArg.setVblue(mbinArgs);

        if (wbtchFields) {
            // We need b VM thbt supports wbtchpoints
            Connector.Argument optionArg =
                (Connector.Argument)brguments.get("options");
            if (optionArg == null) {
                throw new Error("Bbd lbunching connector");
            }
            optionArg.setVblue("-clbssic");
        }
        return brguments;
    }

    /**
     * Print commbnd line usbge help
     */
    void usbge() {
        System.err.println("Usbge: jbvb Trbce <options> <clbss> <brgs>");
        System.err.println("<options> bre:");
        System.err.println(
"  -output <filenbme>   Output trbce to <filenbme>");
        System.err.println(
"  -bll                 Include system clbsses in output");
        System.err.println(
"  -help                Print this help messbge");
        System.err.println("<clbss> is the progrbm to trbce");
        System.err.println("<brgs> bre the brguments to <clbss>");
    }
}
