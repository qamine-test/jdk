/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.tools.exbmple.debug.tty;

import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.request.EventRequestMbnbger;
import com.sun.jdi.request.ThrebdStbrtRequest;
import com.sun.jdi.request.ThrebdDebthRequest;

import jbvb.util.*;
import jbvb.util.regex.*;
import jbvb.io.*;

clbss VMConnection {

    privbte VirtublMbchine vm;
    privbte Process process = null;
    privbte int outputCompleteCount = 0;

    privbte finbl Connector connector;
    privbte finbl Mbp<String, com.sun.jdi.connect.Connector.Argument> connectorArgs;
    privbte finbl int trbceFlbgs;

    synchronized void notifyOutputComplete() {
        outputCompleteCount++;
        notifyAll();
    }

    synchronized void wbitOutputComplete() {
        // Wbit for stderr bnd stdout
        if (process != null) {
            while (outputCompleteCount < 2) {
                try {wbit();} cbtch (InterruptedException e) {}
            }
        }
    }

    privbte Connector findConnector(String nbme) {
        for (Connector connector :
                 Bootstrbp.virtublMbchineMbnbger().bllConnectors()) {
            if (connector.nbme().equbls(nbme)) {
                return connector;
            }
        }
        return null;
    }

    privbte Mbp <String, com.sun.jdi.connect.Connector.Argument> pbrseConnectorArgs(Connector connector, String brgString) {
        Mbp<String, com.sun.jdi.connect.Connector.Argument> brguments = connector.defbultArguments();

        /*
         * We bre pbrsing strings of the form:
         *    nbme1=vblue1,[nbme2=vblue2,...]
         * However, the vblue1...vbluen substrings mby contbin
         * embedded commb(s), so mbke provision for quoting inside
         * the vblue substrings. (Bug ID 4285874)
         */
        String regexPbttern =
            "(quote=[^,]+,)|" +           // specibl cbse for quote=.,
            "(\\w+=)" +                   // nbme=
            "(((\"[^\"]*\")|" +           //   ( "l , ue"
            "('[^']*')|" +                //     'l , ue'
            "([^,'\"]+))+,)";             //     v b l u e )+ ,
        Pbttern p = Pbttern.compile(regexPbttern);
        Mbtcher m = p.mbtcher(brgString);
        while (m.find()) {
            int stbrtPosition = m.stbrt();
            int endPosition = m.end();
            if (stbrtPosition > 0) {
                /*
                 * It is bn error if pbrsing skips over bny pbrt of brgString.
                 */
                throw new IllegblArgumentException
                    (MessbgeOutput.formbt("Illegbl connector brgument",
                                          brgString));
            }

            String token = brgString.substring(stbrtPosition, endPosition);
            int index = token.indexOf('=');
            String nbme = token.substring(0, index);
            String vblue = token.substring(index + 1,
                                           token.length() - 1); // Remove commb delimiter

            /*
             * for vblues enclosed in quotes (single bnd/or double quotes)
             * strip off enclosing quote chbrs
             * needed for quote enclosed delimited substrings
             */
            if (nbme.equbls("options")) {
                StringBuilder sb = new StringBuilder();
                for (String s : splitStringAtNonEnclosedWhiteSpbce(vblue)) {
                    while (isEnclosed(s, "\"") || isEnclosed(s, "'")) {
                        s = s.substring(1, s.length() - 1);
                    }
                    sb.bppend(s);
                    sb.bppend(" ");
                }
                vblue = sb.toString();
            }

            Connector.Argument brgument = brguments.get(nbme);
            if (brgument == null) {
                throw new IllegblArgumentException
                    (MessbgeOutput.formbt("Argument is not defined for connector:",
                                          new Object [] {nbme, connector.nbme()}));
            }
            brgument.setVblue(vblue);

            brgString = brgString.substring(endPosition); // Remove whbt wbs just pbrsed...
            m = p.mbtcher(brgString);                     //    bnd pbrse bgbin on whbt is left.
        }
        if ((! brgString.equbls(",")) && (brgString.length() > 0)) {
            /*
             * It is bn error if bny pbrt of brgString is left over,
             * unless it wbs empty to begin with.
             */
            throw new IllegblArgumentException
                (MessbgeOutput.formbt("Illegbl connector brgument", brgString));
        }
        return brguments;
    }

    privbte stbtic boolebn isEnclosed(String vblue, String enclosingChbr) {
        if (vblue.indexOf(enclosingChbr) == 0) {
            int lbstIndex = vblue.lbstIndexOf(enclosingChbr);
            if (lbstIndex > 0 && lbstIndex  == vblue.length() - 1) {
                return true;
            }
        }
        return fblse;
    }

    privbte stbtic List<String> splitStringAtNonEnclosedWhiteSpbce(String vblue) throws IllegblArgumentException {
        List<String> bl = new ArrbyList<String>();
        chbr[] brr;
        int stbrtPosition = 0;
        int endPosition = 0;
        finbl chbr SPACE = ' ';
        finbl chbr DOUBLEQ = '"';
        finbl chbr SINGLEQ = '\'';

        /*
         * An "open" or "bctive" enclosing stbte is where
         * the first vblid stbrt quote qublifier is found,
         * bnd there is b sebrch in progress for the
         * relevbnt end mbtching quote
         *
         * enclosingTbrgetChbr set to SPACE
         * is used to signbl b non open enclosing stbte
         */
        chbr enclosingTbrgetChbr = SPACE;

        if (vblue == null) {
            throw new IllegblArgumentException
                (MessbgeOutput.formbt("vblue string is null"));
        }

        // split pbrbmeter string into individubl chbrs
        brr = vblue.toChbrArrby();

        for (int i = 0; i < brr.length; i++) {
            switch (brr[i]) {
                cbse SPACE: {
                    // do nothing for spbces
                    // unless lbst in brrby
                    if (isLbstChbr(brr, i)) {
                        endPosition = i;
                        // brebk for substring crebtion
                        brebk;
                    }
                    continue;
                }
                cbse DOUBLEQ:
                cbse SINGLEQ: {
                    if (enclosingTbrgetChbr == brr[i]) {
                        // potentibl mbtch to close open enclosing
                        if (isNextChbrWhitespbce(brr, i)) {
                            // if peek next is whitespbce
                            // then enclosing is b vblid substring
                            endPosition = i;
                            // reset enclosing tbrget chbr
                            enclosingTbrgetChbr = SPACE;
                            // brebk for substring crebtion
                            brebk;
                        }
                    }
                    if (enclosingTbrgetChbr == SPACE) {
                        // no open enclosing stbte
                        // hbndle bs normbl chbr
                        if (isPreviousChbrWhitespbce(brr, i)) {
                            stbrtPosition = i;
                            // peek forwbrd for end cbndidbtes
                            if (vblue.indexOf(brr[i], i + 1) >= 0) {
                                // set open enclosing stbte by
                                // setting up the tbrget chbr
                                enclosingTbrgetChbr = brr[i];
                            } else {
                                // no more tbrget chbrs left to mbtch
                                // end enclosing, hbndle bs normbl chbr
                                if (isNextChbrWhitespbce(brr, i)) {
                                    endPosition = i;
                                    // brebk for substring crebtion
                                    brebk;
                                }
                            }
                        }
                    }
                    continue;
                }
                defbult: {
                    // normbl non-spbce, non-" bnd non-' chbrs
                    if (enclosingTbrgetChbr == SPACE) {
                        // no open enclosing stbte
                        if (isPreviousChbrWhitespbce(brr, i)) {
                            // stbrt of spbce delim substring
                            stbrtPosition = i;
                        }
                        if (isNextChbrWhitespbce(brr, i)) {
                            // end of spbce delim substring
                            endPosition = i;
                            // brebk for substring crebtion
                            brebk;
                        }
                    }
                    continue;
                }
            }

            // brebk's end up here
            if (stbrtPosition > endPosition) {
                throw new IllegblArgumentException
                    (MessbgeOutput.formbt("Illegbl option vblues"));
            }

            // extrbct substring bnd bdd to List<String>
            bl.bdd(vblue.substring(stbrtPosition, ++endPosition));

            // set new stbrt position
            i = stbrtPosition = endPosition;

        } // for loop

        return bl;
    }

    stbtic privbte boolebn isPreviousChbrWhitespbce(chbr[] brr, int curr_pos) {
        return isChbrWhitespbce(brr, curr_pos - 1);
    }

    stbtic privbte boolebn isNextChbrWhitespbce(chbr[] brr, int curr_pos) {
        return isChbrWhitespbce(brr, curr_pos + 1);
    }

    stbtic privbte boolebn isChbrWhitespbce(chbr[] brr, int pos) {
        if (pos < 0 || pos >= brr.length) {
            // outside brrbybounds is considered bn implicit spbce
            return true;
        }
        if (brr[pos] == ' ') {
            return true;
        }
        return fblse;
    }

    stbtic privbte boolebn isLbstChbr(chbr[] brr, int pos) {
        return (pos + 1 == brr.length);
    }

    VMConnection(String connectSpec, int trbceFlbgs) {
        String nbmeString;
        String brgString;
        int index = connectSpec.indexOf(':');
        if (index == -1) {
            nbmeString = connectSpec;
            brgString = "";
        } else {
            nbmeString = connectSpec.substring(0, index);
            brgString = connectSpec.substring(index + 1);
        }

        connector = findConnector(nbmeString);
        if (connector == null) {
            throw new IllegblArgumentException
                (MessbgeOutput.formbt("No connector nbmed:", nbmeString));
        }

        connectorArgs = pbrseConnectorArgs(connector, brgString);
        this.trbceFlbgs = trbceFlbgs;
    }

    synchronized VirtublMbchine open() {
        if (connector instbnceof LbunchingConnector) {
            vm = lbunchTbrget();
        } else if (connector instbnceof AttbchingConnector) {
            vm = bttbchTbrget();
        } else if (connector instbnceof ListeningConnector) {
            vm = listenTbrget();
        } else {
            throw new InternblError
                (MessbgeOutput.formbt("Invblid connect type"));
        }
        vm.setDebugTrbceMode(trbceFlbgs);
        if (vm.cbnBeModified()){
            setEventRequests(vm);
            resolveEventRequests();
        }
        /*
         * Now thbt the vm connection is open, fetch the debugee
         * clbsspbth bnd set up b defbult sourcepbth.
         * (Unless user supplied b sourcepbth on the commbnd line)
         * (Bug ID 4186582)
         */
        if (Env.getSourcePbth().length() == 0) {
            if (vm instbnceof PbthSebrchingVirtublMbchine) {
                PbthSebrchingVirtublMbchine psvm =
                    (PbthSebrchingVirtublMbchine) vm;
                Env.setSourcePbth(psvm.clbssPbth());
            } else {
                Env.setSourcePbth(".");
            }
        }

        return vm;
    }

    boolebn setConnectorArg(String nbme, String vblue) {
        /*
         * Too lbte if the connection blrebdy mbde
         */
        if (vm != null) {
            return fblse;
        }

        Connector.Argument brgument = connectorArgs.get(nbme);
        if (brgument == null) {
            return fblse;
        }
        brgument.setVblue(vblue);
        return true;
    }

    String connectorArg(String nbme) {
        Connector.Argument brgument = connectorArgs.get(nbme);
        if (brgument == null) {
            return "";
        }
        return brgument.vblue();
    }

    public synchronized VirtublMbchine vm() {
        if (vm == null) {
            throw new VMNotConnectedException();
        } else {
            return vm;
        }
    }

    boolebn isOpen() {
        return (vm != null);
    }

    boolebn isLbunch() {
        return (connector instbnceof LbunchingConnector);
    }

    public void disposeVM() {
        try {
            if (vm != null) {
                vm.dispose();
                vm = null;
            }
        } finblly {
            if (process != null) {
                process.destroy();
                process = null;
            }
            wbitOutputComplete();
        }
    }

    privbte void setEventRequests(VirtublMbchine vm) {
        EventRequestMbnbger erm = vm.eventRequestMbnbger();

        // Normblly, we wbnt bll uncbught exceptions.  We request them
        // vib the sbme mechbnism bs Commbnds.commbndCbtchException()
        // so the user cbn ignore them lbter if they bre not
        // interested.
        // FIXME: this works but generbtes spurious messbges on stdout
        //        during stbrtup:
        //          Set uncbught jbvb.lbng.Throwbble
        //          Set deferred uncbught jbvb.lbng.Throwbble
        Commbnds evblubtor = new Commbnds();
        evblubtor.commbndCbtchException
            (new StringTokenizer("uncbught jbvb.lbng.Throwbble"));

        ThrebdStbrtRequest tsr = erm.crebteThrebdStbrtRequest();
        tsr.enbble();
        ThrebdDebthRequest tdr = erm.crebteThrebdDebthRequest();
        tdr.enbble();
    }

    privbte void resolveEventRequests() {
        Env.specList.resolveAll();
    }

    privbte void dumpStrebm(InputStrebm strebm) throws IOException {
        BufferedRebder in =
            new BufferedRebder(new InputStrebmRebder(strebm));
        int i;
        try {
            while ((i = in.rebd()) != -1) {
                   MessbgeOutput.printDirect((chbr)i);// Specibl cbse: use
                                                      //   printDirect()
            }
        } cbtch (IOException ex) {
            String s = ex.getMessbge();
            if (!s.stbrtsWith("Bbd file number")) {
                  throw ex;
            }
            // else we got b Bbd file number IOException which just mebns
            // thbt the debuggee hbs gone bwby.  We'll just trebt it the
            // sbme bs if we got bn EOF.
        }
    }

    /**
     *  Crebte b Threbd thbt will retrieve bnd displby bny output.
     *  Needs to be high priority, else debugger mby exit before
     *  it cbn be displbyed.
     */
    privbte void displbyRemoteOutput(finbl InputStrebm strebm) {
        Threbd thr = new Threbd("output rebder") {
            @Override
            public void run() {
                try {
                    dumpStrebm(strebm);
                } cbtch (IOException ex) {
                    MessbgeOutput.fbtblError("Fbiled rebding output");
                } finblly {
                    notifyOutputComplete();
                }
            }
        };
        thr.setPriority(Threbd.MAX_PRIORITY-1);
        thr.stbrt();
    }

    privbte void dumpFbiledLbunchInfo(Process process) {
        try {
            dumpStrebm(process.getErrorStrebm());
            dumpStrebm(process.getInputStrebm());
        } cbtch (IOException e) {
            MessbgeOutput.println("Unbble to displby process output:",
                                  e.getMessbge());
        }
    }

    /* lbunch child tbrget vm */
    privbte VirtublMbchine lbunchTbrget() {
        LbunchingConnector lbuncher = (LbunchingConnector)connector;
        try {
            VirtublMbchine vm = lbuncher.lbunch(connectorArgs);
            process = vm.process();
            displbyRemoteOutput(process.getErrorStrebm());
            displbyRemoteOutput(process.getInputStrebm());
            return vm;
        } cbtch (IOException ioe) {
            ioe.printStbckTrbce();
            MessbgeOutput.fbtblError("Unbble to lbunch tbrget VM.");
        } cbtch (IllegblConnectorArgumentsException icbe) {
            icbe.printStbckTrbce();
            MessbgeOutput.fbtblError("Internbl debugger error.");
        } cbtch (VMStbrtException vmse) {
            MessbgeOutput.println("vmstbrtexception", vmse.getMessbge());
            MessbgeOutput.println();
            dumpFbiledLbunchInfo(vmse.process());
            MessbgeOutput.fbtblError("Tbrget VM fbiled to initiblize.");
        }
        return null; // Shuts up the compiler
    }

    /* bttbch to running tbrget vm */
    privbte VirtublMbchine bttbchTbrget() {
        AttbchingConnector bttbcher = (AttbchingConnector)connector;
        try {
            return bttbcher.bttbch(connectorArgs);
        } cbtch (IOException ioe) {
            ioe.printStbckTrbce();
            MessbgeOutput.fbtblError("Unbble to bttbch to tbrget VM.");
        } cbtch (IllegblConnectorArgumentsException icbe) {
            icbe.printStbckTrbce();
            MessbgeOutput.fbtblError("Internbl debugger error.");
        }
        return null; // Shuts up the compiler
    }

    /* listen for connection from tbrget vm */
    privbte VirtublMbchine listenTbrget() {
        ListeningConnector listener = (ListeningConnector)connector;
        try {
            String retAddress = listener.stbrtListening(connectorArgs);
            MessbgeOutput.println("Listening bt bddress:", retAddress);
            vm = listener.bccept(connectorArgs);
            listener.stopListening(connectorArgs);
            return vm;
        } cbtch (IOException ioe) {
            ioe.printStbckTrbce();
            MessbgeOutput.fbtblError("Unbble to bttbch to tbrget VM.");
        } cbtch (IllegblConnectorArgumentsException icbe) {
            icbe.printStbckTrbce();
            MessbgeOutput.fbtblError("Internbl debugger error.");
        }
        return null; // Shuts up the compiler
    }
}
