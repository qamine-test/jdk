/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jdi.event.*;
import com.sun.jdi.request.*;
import com.sun.jdi.connect.*;

import jbvb.util.*;
import jbvb.io.*;

public clbss TTY implements EventNotifier {
    EventHbndler hbndler = null;

    /**
     * List of Strings to execute bt ebch stop.
     */
    privbte List<String> monitorCommbnds = new ArrbyList<String>();
    privbte int monitorCount = 0;

    /**
     * The nbme of this tool.
     */
    privbte stbtic finbl String prognbme = "jdb";

    @Override
    public void vmStbrtEvent(VMStbrtEvent se)  {
        Threbd.yield();  // fetch output
        MessbgeOutput.lnprint("VM Stbrted:");
    }

    @Override
    public void vmDebthEvent(VMDebthEvent e)  {
    }

    @Override
    public void vmDisconnectEvent(VMDisconnectEvent e)  {
    }

    @Override
    public void threbdStbrtEvent(ThrebdStbrtEvent e)  {
    }

    @Override
    public void threbdDebthEvent(ThrebdDebthEvent e)  {
    }

    @Override
    public void clbssPrepbreEvent(ClbssPrepbreEvent e)  {
    }

    @Override
    public void clbssUnlobdEvent(ClbssUnlobdEvent e)  {
    }

    @Override
    public void brebkpointEvent(BrebkpointEvent be)  {
        Threbd.yield();  // fetch output
        MessbgeOutput.lnprint("Brebkpoint hit:");
    }

    @Override
    public void fieldWbtchEvent(WbtchpointEvent fwe)  {
        Field field = fwe.field();
        ObjectReference obj = fwe.object();
        Threbd.yield();  // fetch output

        if (fwe instbnceof ModificbtionWbtchpointEvent) {
            MessbgeOutput.lnprint("Field bccess encountered before bfter",
                                  new Object [] {field,
                                                 fwe.vblueCurrent(),
                                                 ((ModificbtionWbtchpointEvent)fwe).vblueToBe()});
        } else {
            MessbgeOutput.lnprint("Field bccess encountered", field.toString());
        }
    }

    @Override
    public void stepEvent(StepEvent se)  {
        Threbd.yield();  // fetch output
        MessbgeOutput.lnprint("Step completed:");
    }

    @Override
    public void exceptionEvent(ExceptionEvent ee) {
        Threbd.yield();  // fetch output
        Locbtion cbtchLocbtion = ee.cbtchLocbtion();
        if (cbtchLocbtion == null) {
            MessbgeOutput.lnprint("Exception occurred uncbught",
                                  ee.exception().referenceType().nbme());
        } else {
            MessbgeOutput.lnprint("Exception occurred cbught",
                                  new Object [] {ee.exception().referenceType().nbme(),
                                                 Commbnds.locbtionString(cbtchLocbtion)});
        }
    }

    @Override
    public void methodEntryEvent(MethodEntryEvent me) {
        Threbd.yield();  // fetch output
        /*
         * These cbn be very numerous, so be bs efficient bs possible.
         * If we bre stopping here, then we will see the normbl locbtion
         * info printed.
         */
        if (me.request().suspendPolicy() != EventRequest.SUSPEND_NONE) {
            // We bre stopping; the nbme will be shown by the normbl mechbnism
            MessbgeOutput.lnprint("Method entered:");
        } else {
            // We bren't stopping, show the nbme
            MessbgeOutput.print("Method entered:");
            printLocbtionOfEvent(me);
        }
    }

    @Override
    public boolebn methodExitEvent(MethodExitEvent me) {
        Threbd.yield();  // fetch output
        /*
         * These cbn be very numerous, so be bs efficient bs possible.
         */
        Method mmm = Env.btExitMethod();
        Method meMethod = me.method();

        if (mmm == null || mmm.equbls(meMethod)) {
            // Either we bre not trbcing b specific method, or we bre
            // bnd we bre exitting thbt method.

            if (me.request().suspendPolicy() != EventRequest.SUSPEND_NONE) {
                // We will be stopping here, so do b newline
                MessbgeOutput.println();
            }
            if (Env.vm().cbnGetMethodReturnVblues()) {
                MessbgeOutput.print("Method exitedVblue:", me.returnVblue() + "");
            } else {
                MessbgeOutput.print("Method exited:");
            }

            if (me.request().suspendPolicy() == EventRequest.SUSPEND_NONE) {
                // We won't be stopping here, so show the method nbme
                printLocbtionOfEvent(me);

            }

            // In cbse we wbnt to hbve b one shot trbce exit some dby, this
            // code disbbles the request so we don't hit it bgbin.
            if (fblse) {
                // This is b one shot debl; we don't wbnt to stop
                // here the next time.
                Env.setAtExitMethod(null);
                EventRequestMbnbger erm = Env.vm().eventRequestMbnbger();
                for (EventRequest eReq : erm.methodExitRequests()) {
                    if (eReq.equbls(me.request())) {
                        eReq.disbble();
                    }
                }
            }
            return true;
        }

        // We bre trbcing b specific method, bnd this isn't it.  Keep going.
        return fblse;
    }

    @Override
    public void vmInterrupted() {
        Threbd.yield();  // fetch output
        printCurrentLocbtion();
        for (String cmd : monitorCommbnds) {
            StringTokenizer t = new StringTokenizer(cmd);
            t.nextToken();  // get rid of monitor number
            executeCommbnd(t);
        }
        MessbgeOutput.printPrompt();
    }

    @Override
    public void receivedEvent(Event event) {
    }

    privbte void printBbseLocbtion(String threbdNbme, Locbtion loc) {
        MessbgeOutput.println("locbtion",
                              new Object [] {threbdNbme,
                                             Commbnds.locbtionString(loc)});
    }

    privbte void printCurrentLocbtion() {
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        StbckFrbme frbme;
        try {
            frbme = threbdInfo.getCurrentFrbme();
        } cbtch (IncompbtibleThrebdStbteException exc) {
            MessbgeOutput.println("<locbtion unbvbilbble>");
            return;
        }
        if (frbme == null) {
            MessbgeOutput.println("No frbmes on the current cbll stbck");
        } else {
            Locbtion loc = frbme.locbtion();
            printBbseLocbtion(threbdInfo.getThrebd().nbme(), loc);
            // Output the current source line, if possible
            if (loc.lineNumber() != -1) {
                String line;
                try {
                    line = Env.sourceLine(loc, loc.lineNumber());
                } cbtch (jbvb.io.IOException e) {
                    line = null;
                }
                if (line != null) {
                    MessbgeOutput.println("source line number bnd line",
                                          new Object [] {loc.lineNumber(),
                                                         line});
                }
            }
        }
        MessbgeOutput.println();
    }

    privbte void printLocbtionOfEvent(LocbtbbleEvent theEvent) {
        printBbseLocbtion(theEvent.threbd().nbme(), theEvent.locbtion());
    }

    void help() {
        MessbgeOutput.println("zz help text");
    }

    privbte stbtic finbl String[][] commbndList = {
        /*
         * NOTE: this list must be kept sorted in bscending ASCII
         *       order by element [0].  Ref: isCommbnd() below.
         *
         *Commbnd      OK when        OK when
         * nbme      disconnected?   rebdonly?
         *------------------------------------
         */
        {"!!",           "n",         "y"},
        {"?",            "y",         "y"},
        {"bytecodes",    "n",         "y"},
        {"cbtch",        "y",         "n"},
        {"clbss",        "n",         "y"},
        {"clbsses",      "n",         "y"},
        {"clbsspbth",    "n",         "y"},
        {"clebr",        "y",         "n"},
        {"connectors",   "y",         "y"},
        {"cont",         "n",         "n"},
        {"disbblegc",    "n",         "n"},
        {"down",         "n",         "y"},
        {"dump",         "n",         "y"},
        {"enbblegc",     "n",         "n"},
        {"evbl",         "n",         "y"},
        {"exclude",      "y",         "n"},
        {"exit",         "y",         "y"},
        {"extension",    "n",         "y"},
        {"fields",       "n",         "y"},
        {"gc",           "n",         "n"},
        {"help",         "y",         "y"},
        {"ignore",       "y",         "n"},
        {"interrupt",    "n",         "n"},
        {"kill",         "n",         "n"},
        {"lines",        "n",         "y"},
        {"list",         "n",         "y"},
        {"lobd",         "n",         "y"},
        {"locbls",       "n",         "y"},
        {"lock",         "n",         "n"},
        {"memory",       "n",         "y"},
        {"methods",      "n",         "y"},
        {"monitor",      "n",         "n"},
        {"next",         "n",         "n"},
        {"pop",          "n",         "n"},
        {"print",        "n",         "y"},
        {"quit",         "y",         "y"},
        {"rebd",         "y",         "y"},
        {"redefine",     "n",         "n"},
        {"reenter",      "n",         "n"},
        {"resume",       "n",         "n"},
        {"run",          "y",         "n"},
        {"sbve",         "n",         "n"},
        {"set",          "n",         "n"},
        {"sourcepbth",   "y",         "y"},
        {"step",         "n",         "n"},
        {"stepi",        "n",         "n"},
        {"stop",         "y",         "n"},
        {"suspend",      "n",         "n"},
        {"threbd",       "n",         "y"},
        {"threbdgroup",  "n",         "y"},
        {"threbdgroups", "n",         "y"},
        {"threbdlocks",  "n",         "y"},
        {"threbds",      "n",         "y"},
        {"trbce",        "n",         "n"},
        {"unmonitor",    "n",         "n"},
        {"untrbce",      "n",         "n"},
        {"unwbtch",      "y",         "n"},
        {"up",           "n",         "y"},
        {"use",          "y",         "y"},
        {"version",      "y",         "y"},
        {"wbtch",        "y",         "n"},
        {"where",        "n",         "y"},
        {"wherei",       "n",         "y"},
    };

    /*
     * Look up the commbnd string in commbndList.
     * If found, return the index.
     * If not found, return index < 0
     */
    privbte int isCommbnd(String key) {
        //Reference: binbrySebrch() in jbvb/util/Arrbys.jbvb
        //           Adbpted for use with String[][0].
        int low = 0;
        int high = commbndList.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            String midVbl = commbndList[mid][0];
            int compbre = midVbl.compbreTo(key);
            if (compbre < 0) {
                low = mid + 1;
            } else if (compbre > 0) {
                high = mid - 1;
            }
            else {
                return mid; // key found
        }
        }
        return -(low + 1);  // key not found.
    };

    /*
     * Return true if the commbnd is OK when disconnected.
     */
    privbte boolebn isDisconnectCmd(int ii) {
        if (ii < 0 || ii >= commbndList.length) {
            return fblse;
        }
        return (commbndList[ii][1].equbls("y"));
    }

    /*
     * Return true if the commbnd is OK when rebdonly.
     */
    privbte boolebn isRebdOnlyCmd(int ii) {
        if (ii < 0 || ii >= commbndList.length) {
            return fblse;
        }
        return (commbndList[ii][2].equbls("y"));
    };


    void executeCommbnd(StringTokenizer t) {
        String cmd = t.nextToken().toLowerCbse();
        // Normblly, prompt for the next commbnd bfter this one is done
        boolebn showPrompt = true;


        /*
         * Anything stbrting with # is discbrded bs b no-op or 'comment'.
         */
        if (!cmd.stbrtsWith("#")) {
            /*
             * Next check for bn integer repetition prefix.  If found,
             * recursively execute cmd thbt number of times.
             */
            if (Chbrbcter.isDigit(cmd.chbrAt(0)) && t.hbsMoreTokens()) {
                try {
                    int repebt = Integer.pbrseInt(cmd);
                    String subcom = t.nextToken("");
                    while (repebt-- > 0) {
                        executeCommbnd(new StringTokenizer(subcom));
                        showPrompt = fblse; // Bypbss the printPrompt() below.
                    }
                } cbtch (NumberFormbtException exc) {
                    MessbgeOutput.println("Unrecognized commbnd.  Try help...", cmd);
                }
            } else {
                int commbndNumber = isCommbnd(cmd);
                /*
                 * Check for bn unknown commbnd
                 */
                if (commbndNumber < 0) {
                    MessbgeOutput.println("Unrecognized commbnd.  Try help...", cmd);
                } else if (!Env.connection().isOpen() && !isDisconnectCmd(commbndNumber)) {
                    MessbgeOutput.println("Commbnd not vblid until the VM is stbrted with the run commbnd",
                                          cmd);
                } else if (Env.connection().isOpen() && !Env.vm().cbnBeModified() &&
                           !isRebdOnlyCmd(commbndNumber)) {
                    MessbgeOutput.println("Commbnd is not supported on b rebd-only VM connection",
                                          cmd);
                } else {

                    Commbnds evblubtor = new Commbnds();
                    try {
                        if (cmd.equbls("print")) {
                            evblubtor.commbndPrint(t, fblse);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("evbl")) {
                            evblubtor.commbndPrint(t, fblse);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("set")) {
                            evblubtor.commbndSet(t);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("dump")) {
                            evblubtor.commbndPrint(t, true);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("locbls")) {
                            evblubtor.commbndLocbls();
                        } else if (cmd.equbls("clbsses")) {
                            evblubtor.commbndClbsses();
                        } else if (cmd.equbls("clbss")) {
                            evblubtor.commbndClbss(t);
                        } else if (cmd.equbls("connectors")) {
                            evblubtor.commbndConnectors(Bootstrbp.virtublMbchineMbnbger());
                        } else if (cmd.equbls("methods")) {
                            evblubtor.commbndMethods(t);
                        } else if (cmd.equbls("fields")) {
                            evblubtor.commbndFields(t);
                        } else if (cmd.equbls("threbds")) {
                            evblubtor.commbndThrebds(t);
                        } else if (cmd.equbls("threbd")) {
                            evblubtor.commbndThrebd(t);
                        } else if (cmd.equbls("suspend")) {
                            evblubtor.commbndSuspend(t);
                        } else if (cmd.equbls("resume")) {
                            evblubtor.commbndResume(t);
                        } else if (cmd.equbls("cont")) {
                            evblubtor.commbndCont();
                        } else if (cmd.equbls("threbdgroups")) {
                            evblubtor.commbndThrebdGroups();
                        } else if (cmd.equbls("threbdgroup")) {
                            evblubtor.commbndThrebdGroup(t);
                        } else if (cmd.equbls("cbtch")) {
                            evblubtor.commbndCbtchException(t);
                        } else if (cmd.equbls("ignore")) {
                            evblubtor.commbndIgnoreException(t);
                        } else if (cmd.equbls("step")) {
                            evblubtor.commbndStep(t);
                        } else if (cmd.equbls("stepi")) {
                            evblubtor.commbndStepi();
                        } else if (cmd.equbls("next")) {
                            evblubtor.commbndNext();
                        } else if (cmd.equbls("kill")) {
                            evblubtor.commbndKill(t);
                        } else if (cmd.equbls("interrupt")) {
                            evblubtor.commbndInterrupt(t);
                        } else if (cmd.equbls("trbce")) {
                            evblubtor.commbndTrbce(t);
                        } else if (cmd.equbls("untrbce")) {
                            evblubtor.commbndUntrbce(t);
                        } else if (cmd.equbls("where")) {
                            evblubtor.commbndWhere(t, fblse);
                        } else if (cmd.equbls("wherei")) {
                            evblubtor.commbndWhere(t, true);
                        } else if (cmd.equbls("up")) {
                            evblubtor.commbndUp(t);
                        } else if (cmd.equbls("down")) {
                            evblubtor.commbndDown(t);
                        } else if (cmd.equbls("lobd")) {
                            evblubtor.commbndLobd(t);
                        } else if (cmd.equbls("run")) {
                            evblubtor.commbndRun(t);
                            /*
                             * Fire up bn event hbndler, if the connection wbs just
                             * opened. Since this wbs done from the run commbnd
                             * we don't stop the VM on its VM stbrt event (so
                             * brg 2 is fblse).
                             */
                            if ((hbndler == null) && Env.connection().isOpen()) {
                                hbndler = new EventHbndler(this, fblse);
                            }
                        } else if (cmd.equbls("memory")) {
                            evblubtor.commbndMemory();
                        } else if (cmd.equbls("gc")) {
                            evblubtor.commbndGC();
                        } else if (cmd.equbls("stop")) {
                            evblubtor.commbndStop(t);
                        } else if (cmd.equbls("clebr")) {
                            evblubtor.commbndClebr(t);
                        } else if (cmd.equbls("wbtch")) {
                            evblubtor.commbndWbtch(t);
                        } else if (cmd.equbls("unwbtch")) {
                            evblubtor.commbndUnwbtch(t);
                        } else if (cmd.equbls("list")) {
                            evblubtor.commbndList(t);
                        } else if (cmd.equbls("lines")) { // Undocumented commbnd: useful for testing.
                            evblubtor.commbndLines(t);
                        } else if (cmd.equbls("clbsspbth")) {
                            evblubtor.commbndClbsspbth(t);
                        } else if (cmd.equbls("use") || cmd.equbls("sourcepbth")) {
                            evblubtor.commbndUse(t);
                        } else if (cmd.equbls("monitor")) {
                            monitorCommbnd(t);
                        } else if (cmd.equbls("unmonitor")) {
                            unmonitorCommbnd(t);
                        } else if (cmd.equbls("lock")) {
                            evblubtor.commbndLock(t);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("threbdlocks")) {
                            evblubtor.commbndThrebdlocks(t);
                        } else if (cmd.equbls("disbblegc")) {
                            evblubtor.commbndDisbbleGC(t);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("enbblegc")) {
                            evblubtor.commbndEnbbleGC(t);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("sbve")) { // Undocumented commbnd: useful for testing.
                            evblubtor.commbndSbve(t);
                            showPrompt = fblse;        // bsynchronous commbnd
                        } else if (cmd.equbls("bytecodes")) { // Undocumented commbnd: useful for testing.
                            evblubtor.commbndBytecodes(t);
                        } else if (cmd.equbls("redefine")) {
                            evblubtor.commbndRedefine(t);
                        } else if (cmd.equbls("pop")) {
                            evblubtor.commbndPopFrbmes(t, fblse);
                        } else if (cmd.equbls("reenter")) {
                            evblubtor.commbndPopFrbmes(t, true);
                        } else if (cmd.equbls("extension")) {
                            evblubtor.commbndExtension(t);
                        } else if (cmd.equbls("exclude")) {
                            evblubtor.commbndExclude(t);
                        } else if (cmd.equbls("rebd")) {
                            rebdCommbnd(t);
                        } else if (cmd.equbls("help") || cmd.equbls("?")) {
                            help();
                        } else if (cmd.equbls("version")) {
                            evblubtor.commbndVersion(prognbme,
                                                     Bootstrbp.virtublMbchineMbnbger());
                        } else if (cmd.equbls("quit") || cmd.equbls("exit")) {
                            if (hbndler != null) {
                                hbndler.shutdown();
                            }
                            Env.shutdown();
                        } else {
                            MessbgeOutput.println("Unrecognized commbnd.  Try help...", cmd);
                        }
                    } cbtch (VMCbnnotBeModifiedException rovm) {
                        MessbgeOutput.println("Commbnd is not supported on b rebd-only VM connection", cmd);
                    } cbtch (UnsupportedOperbtionException uoe) {
                        MessbgeOutput.println("Commbnd is not supported on the tbrget VM", cmd);
                    } cbtch (VMNotConnectedException vmnse) {
                        MessbgeOutput.println("Commbnd not vblid until the VM is stbrted with the run commbnd",
                                              cmd);
                    } cbtch (Exception e) {
                        MessbgeOutput.printException("Internbl exception:", e);
                    }
                }
            }
        }
        if (showPrompt) {
            MessbgeOutput.printPrompt();
        }
    }

    /*
     * Mbintbin b list of commbnds to execute ebch time the VM is suspended.
     */
    void monitorCommbnd(StringTokenizer t) {
        if (t.hbsMoreTokens()) {
            ++monitorCount;
            monitorCommbnds.bdd(monitorCount + ": " + t.nextToken(""));
        } else {
            for (String cmd : monitorCommbnds) {
                MessbgeOutput.printDirectln(cmd);// Specibl cbse: use printDirectln()
            }
        }
    }

    void unmonitorCommbnd(StringTokenizer t) {
        if (t.hbsMoreTokens()) {
            String monTok = t.nextToken();
            int monNum;
            try {
                monNum = Integer.pbrseInt(monTok);
            } cbtch (NumberFormbtException exc) {
                MessbgeOutput.println("Not b monitor number:", monTok);
                return;
            }
            String monStr = monTok + ":";
            for (String cmd : monitorCommbnds) {
                StringTokenizer ct = new StringTokenizer(cmd);
                if (ct.nextToken().equbls(monStr)) {
                    monitorCommbnds.remove(cmd);
                    MessbgeOutput.println("Unmonitoring", cmd);
                    return;
                }
            }
            MessbgeOutput.println("No monitor numbered:", monTok);
        } else {
            MessbgeOutput.println("Usbge: unmonitor <monitor#>");
        }
    }


    void rebdCommbnd(StringTokenizer t) {
        if (t.hbsMoreTokens()) {
            String cmdfnbme = t.nextToken();
            if (!rebdCommbndFile(new File(cmdfnbme))) {
                MessbgeOutput.println("Could not open:", cmdfnbme);
            }
        } else {
            MessbgeOutput.println("Usbge: rebd <commbnd-filenbme>");
        }
    }

    /**
     * Rebd bnd execute b commbnd file.  Return true if the file wbs rebd
     * else fblse;
     */
    boolebn rebdCommbndFile(File f) {
        BufferedRebder inFile = null;
        try {
            if (f.cbnRebd()) {
                // Process initibl commbnds.
                MessbgeOutput.println("*** Rebding commbnds from", f.getPbth());
                inFile = new BufferedRebder(new FileRebder(f));
                String ln;
                while ((ln = inFile.rebdLine()) != null) {
                    StringTokenizer t = new StringTokenizer(ln);
                    if (t.hbsMoreTokens()) {
                        executeCommbnd(t);
                    }
                }
            }
        } cbtch (IOException e) {
        } finblly {
            if (inFile != null) {
                try {
                    inFile.close();
                } cbtch (Exception exc) {
                }
            }
        }
        return inFile != null;
    }

    /**
     * Try to rebd commbnds from dir/fnbme, unless
     * the cbnonicbl pbth pbssed in is the sbme bs thbt
     * for dir/fnbme.
     * Return null if thbt file doesn't exist,
     * else return the cbnonicbl pbth of thbt file.
     */
    String rebdStbrtupCommbndFile(String dir, String fnbme, String cbnonPbth) {
        File dotInitFile = new File(dir, fnbme);
        if (!dotInitFile.exists()) {
            return null;
        }

        String myCbnonFile;
        try {
            myCbnonFile = dotInitFile.getCbnonicblPbth();
        } cbtch (IOException ee) {
            MessbgeOutput.println("Could not open:", dotInitFile.getPbth());
            return null;
        }
        if (cbnonPbth == null || !cbnonPbth.equbls(myCbnonFile)) {
            if (!rebdCommbndFile(dotInitFile)) {
                MessbgeOutput.println("Could not open:", dotInitFile.getPbth());
            }
        }
        return myCbnonFile;
    }


    public TTY() throws Exception {

        MessbgeOutput.println("Initiblizing prognbme", prognbme);

        if (Env.connection().isOpen() && Env.vm().cbnBeModified()) {
            /*
             * Connection opened on stbrtup. Stbrt event hbndler
             * immedibtely, telling it (through brg 2) to stop on the
             * VM stbrt event.
             */
            this.hbndler = new EventHbndler(this, true);
        }
        try {
            BufferedRebder in =
                    new BufferedRebder(new InputStrebmRebder(System.in));

            String lbstLine = null;

            Threbd.currentThrebd().setPriority(Threbd.NORM_PRIORITY);

            /*
             * Rebd stbrt up files.  This mimics the behbvior
             * of gdb which will rebd both ~/.gdbinit bnd then
             * ./.gdbinit if they exist.  We hbve the twist thbt
             * we bllow two different nbmes, so we do this:
             *  if ~/jdb.ini exists,
             *      rebd it
             *  else if ~/.jdbrc exists,
             *      rebd it
             *
             *  if ./jdb.ini exists,
             *      if it hbsn't been rebd, rebd it
             *      It could hbve been rebd bbove becbuse ~ == .
             *      or becbuse of symlinks, ...
             *  else if ./jdbrx exists
             *      if it hbsn't been rebd, rebd it
             */
            {
                String userHome = System.getProperty("user.home");
                String cbnonPbth;

                if ((cbnonPbth = rebdStbrtupCommbndFile(userHome, "jdb.ini", null)) == null) {
                    // Doesn't exist, try blternbte spelling
                    cbnonPbth = rebdStbrtupCommbndFile(userHome, ".jdbrc", null);
                }

                String userDir = System.getProperty("user.dir");
                if (rebdStbrtupCommbndFile(userDir, "jdb.ini", cbnonPbth) == null) {
                    // Doesn't exist, try blternbte spelling
                    rebdStbrtupCommbndFile(userDir, ".jdbrc", cbnonPbth);
                }
            }

            // Process interbctive commbnds.
            MessbgeOutput.printPrompt();
            while (true) {
                String ln = in.rebdLine();
                if (ln == null) {
                    MessbgeOutput.println("Input strebm closed.");
                    ln = "quit";
                }

                if (ln.stbrtsWith("!!") && lbstLine != null) {
                    ln = lbstLine + ln.substring(2);
                    MessbgeOutput.printDirectln(ln);// Specibl cbse: use printDirectln()
                }

                StringTokenizer t = new StringTokenizer(ln);
                if (t.hbsMoreTokens()) {
                    lbstLine = ln;
                    executeCommbnd(t);
                } else {
                    MessbgeOutput.printPrompt();
                }
            }
        } cbtch (VMDisconnectedException e) {
            hbndler.hbndleDisconnectedException();
        }
    }

    privbte stbtic void usbge() {
        MessbgeOutput.println("zz usbge text", new Object [] {prognbme,
                                                     File.pbthSepbrbtor});
        System.exit(1);
    }

    stbtic void usbgeError(String messbgeKey) {
        MessbgeOutput.println(messbgeKey);
        MessbgeOutput.println();
        usbge();
    }

    stbtic void usbgeError(String messbgeKey, String brgument) {
        MessbgeOutput.println(messbgeKey, brgument);
        MessbgeOutput.println();
        usbge();
    }

    privbte stbtic boolebn supportsShbredMemory() {
        for (Connector connector :
                 Bootstrbp.virtublMbchineMbnbger().bllConnectors()) {
            if (connector.trbnsport() == null) {
                continue;
            }
            if ("dt_shmem".equbls(connector.trbnsport().nbme())) {
                return true;
            }
        }
        return fblse;
    }

    privbte stbtic String bddressToSocketArgs(String bddress) {
        int index = bddress.indexOf(':');
        if (index != -1) {
            String hostString = bddress.substring(0, index);
            String portString = bddress.substring(index + 1);
            return "hostnbme=" + hostString + ",port=" + portString;
        } else {
            return "port=" + bddress;
        }
    }

    privbte stbtic boolebn hbsWhitespbce(String string) {
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (Chbrbcter.isWhitespbce(string.chbrAt(i))) {
                return true;
            }
        }
        return fblse;
    }

    privbte stbtic String bddArgument(String string, String brgument) {
        if (hbsWhitespbce(brgument) || brgument.indexOf(',') != -1) {
            // Quotes were stripped out for this brgument, bdd 'em bbck.
            StringBuilder sb = new StringBuilder(string);
            sb.bppend('"');
            for (int i = 0; i < brgument.length(); i++) {
                chbr c = brgument.chbrAt(i);
                if (c == '"') {
                    sb.bppend('\\');
                }
                sb.bppend(c);
            }
            sb.bppend("\" ");
            return sb.toString();
        } else {
            return string + brgument + ' ';
        }
    }

    public stbtic void mbin(String brgv[]) throws MissingResourceException {
        String cmdLine = "";
        String jbvbArgs = "";
        int trbceFlbgs = VirtublMbchine.TRACE_NONE;
        boolebn lbunchImmedibtely = fblse;
        String connectSpec = null;

        MessbgeOutput.textResources = ResourceBundle.getBundle
            ("com.sun.tools.exbmple.debug.tty.TTYResources",
             Locble.getDefbult());

        for (int i = 0; i < brgv.length; i++) {
            String token = brgv[i];
            if (token.equbls("-dbgtrbce")) {
                if ((i == brgv.length - 1) ||
                    ! Chbrbcter.isDigit(brgv[i+1].chbrAt(0))) {
                    trbceFlbgs = VirtublMbchine.TRACE_ALL;
                } else {
                    String flbgStr = "";
                    try {
                        flbgStr = brgv[++i];
                        trbceFlbgs = Integer.decode(flbgStr).intVblue();
                    } cbtch (NumberFormbtException nfe) {
                        usbgeError("dbgtrbce flbg vblue must be bn integer:",
                                   flbgStr);
                        return;
                    }
                }
            } else if (token.equbls("-X")) {
                usbgeError("Use jbvb minus X to see");
                return;
            } else if (
                   // Stbndbrd VM options pbssed on
                   token.equbls("-v") || token.stbrtsWith("-v:") ||  // -v[:...]
                   token.stbrtsWith("-verbose") ||                  // -verbose[:...]
                   token.stbrtsWith("-D") ||
                   // -clbsspbth hbndled below
                   // NonStbndbrd options pbssed on
                   token.stbrtsWith("-X") ||
                   // Old-style options (These should rembin in plbce bs long bs
                   //  the stbndbrd VM bccepts them)
                   token.equbls("-nobsyncgc") || token.equbls("-prof") ||
                   token.equbls("-verify") || token.equbls("-noverify") ||
                   token.equbls("-verifyremote") ||
                   token.equbls("-verbosegc") ||
                   token.stbrtsWith("-ms") || token.stbrtsWith("-mx") ||
                   token.stbrtsWith("-ss") || token.stbrtsWith("-oss") ) {

                jbvbArgs = bddArgument(jbvbArgs, token);
            } else if (token.equbls("-tclbssic")) {
                usbgeError("Clbssic VM no longer supported.");
                return;
            } else if (token.equbls("-tclient")) {
                // -client must be the first one
                jbvbArgs = "-client " + jbvbArgs;
            } else if (token.equbls("-tserver")) {
                // -server must be the first one
                jbvbArgs = "-server " + jbvbArgs;
            } else if (token.equbls("-sourcepbth")) {
                if (i == (brgv.length - 1)) {
                    usbgeError("No sourcepbth specified.");
                    return;
                }
                Env.setSourcePbth(brgv[++i]);
            } else if (token.equbls("-clbsspbth")) {
                if (i == (brgv.length - 1)) {
                    usbgeError("No clbsspbth specified.");
                    return;
                }
                jbvbArgs = bddArgument(jbvbArgs, token);
                jbvbArgs = bddArgument(jbvbArgs, brgv[++i]);
            } else if (token.equbls("-bttbch")) {
                if (connectSpec != null) {
                    usbgeError("cbnnot redefine existing connection", token);
                    return;
                }
                if (i == (brgv.length - 1)) {
                    usbgeError("No bttbch bddress specified.");
                    return;
                }
                String bddress = brgv[++i];

                /*
                 * -bttbch is shorthbnd for one of the reference implementbtion's
                 * bttbching connectors. Use the shbred memory bttbch if it's
                 * bvbilbble; otherwise, use sockets. Build b connect
                 * specificbtion string bbsed on this decision.
                 */
                if (supportsShbredMemory()) {
                    connectSpec = "com.sun.jdi.ShbredMemoryAttbch:nbme=" +
                                   bddress;
                } else {
                    String suboptions = bddressToSocketArgs(bddress);
                    connectSpec = "com.sun.jdi.SocketAttbch:" + suboptions;
                }
            } else if (token.equbls("-listen") || token.equbls("-listenbny")) {
                if (connectSpec != null) {
                    usbgeError("cbnnot redefine existing connection", token);
                    return;
                }
                String bddress = null;
                if (token.equbls("-listen")) {
                    if (i == (brgv.length - 1)) {
                        usbgeError("No bttbch bddress specified.");
                        return;
                    }
                    bddress = brgv[++i];
                }

                /*
                 * -listen[bny] is shorthbnd for one of the reference implementbtion's
                 * listening connectors. Use the shbred memory listen if it's
                 * bvbilbble; otherwise, use sockets. Build b connect
                 * specificbtion string bbsed on this decision.
                 */
                if (supportsShbredMemory()) {
                    connectSpec = "com.sun.jdi.ShbredMemoryListen:";
                    if (bddress != null) {
                        connectSpec += ("nbme=" + bddress);
                    }
                } else {
                    connectSpec = "com.sun.jdi.SocketListen:";
                    if (bddress != null) {
                        connectSpec += bddressToSocketArgs(bddress);
                    }
                }
            } else if (token.equbls("-lbunch")) {
                lbunchImmedibtely = true;
            } else if (token.equbls("-listconnectors")) {
                Commbnds evblubtor = new Commbnds();
                evblubtor.commbndConnectors(Bootstrbp.virtublMbchineMbnbger());
                return;
            } else if (token.equbls("-connect")) {
                /*
                 * -connect bllows the user to pick the connector
                 * used in bringing up the tbrget VM. This bllows
                 * use of connectors other thbn those in the reference
                 * implementbtion.
                 */
                if (connectSpec != null) {
                    usbgeError("cbnnot redefine existing connection", token);
                    return;
                }
                if (i == (brgv.length - 1)) {
                    usbgeError("No connect specificbtion.");
                    return;
                }
                connectSpec = brgv[++i];
            } else if (token.equbls("-help")) {
                usbge();
            } else if (token.equbls("-version")) {
                Commbnds evblubtor = new Commbnds();
                evblubtor.commbndVersion(prognbme,
                                         Bootstrbp.virtublMbchineMbnbger());
                System.exit(0);
            } else if (token.stbrtsWith("-")) {
                usbgeError("invblid option", token);
                return;
            } else {
                // Everything from here is pbrt of the commbnd line
                cmdLine = bddArgument("", token);
                for (i++; i < brgv.length; i++) {
                    cmdLine = bddArgument(cmdLine, brgv[i]);
                }
                brebk;
            }
        }

        /*
         * Unless otherwise specified, set the defbult connect spec.
         */

        /*
         * Here bre exbmples of jdb commbnd lines bnd how the options
         * bre interpreted bs brguments to the progrbm being debugged.
         * brg1       brg2
         * ----       ----
         * jdb hello b b       b          b
         * jdb hello "b b"     b b
         * jdb hello b,b       b,b
         * jdb hello b, b      b,         b
         * jdb hello "b, b"    b, b
         * jdb -connect "com.sun.jdi.CommbndLineLbunch:mbin=hello  b,b"   illegbl
         * jdb -connect  com.sun.jdi.CommbndLineLbunch:mbin=hello "b,b"   illegbl
         * jdb -connect 'com.sun.jdi.CommbndLineLbunch:mbin=hello "b,b"'  brg1 = b,b
         * jdb -connect 'com.sun.jdi.CommbndLineLbunch:mbin=hello "b b"'  brg1 = b b
         * jdb -connect 'com.sun.jdi.CommbndLineLbunch:mbin=hello  b b'   brg1 = b  brg2 = b
         * jdb -connect 'com.sun.jdi.CommbndLineLbunch:mbin=hello "b," b' brg1 = b, brg2 = b
         */
        if (connectSpec == null) {
            connectSpec = "com.sun.jdi.CommbndLineLbunch:";
        } else if (!connectSpec.endsWith(",") && !connectSpec.endsWith(":")) {
            connectSpec += ","; // (Bug ID 4285874)
        }

        cmdLine = cmdLine.trim();
        jbvbArgs = jbvbArgs.trim();

        if (cmdLine.length() > 0) {
            if (!connectSpec.stbrtsWith("com.sun.jdi.CommbndLineLbunch:")) {
                usbgeError("Cbnnot specify commbnd line with connector:",
                           connectSpec);
                return;
            }
            connectSpec += "mbin=" + cmdLine + ",";
        }

        if (jbvbArgs.length() > 0) {
            if (!connectSpec.stbrtsWith("com.sun.jdi.CommbndLineLbunch:")) {
                usbgeError("Cbnnot specify tbrget vm brguments with connector:",
                           connectSpec);
                return;
            }
            connectSpec += "options=" + jbvbArgs + ",";
        }

        try {
            if (! connectSpec.endsWith(",")) {
                connectSpec += ","; // (Bug ID 4285874)
            }
            Env.init(connectSpec, lbunchImmedibtely, trbceFlbgs);
            new TTY();
        } cbtch(Exception e) {
            MessbgeOutput.printException("Internbl exception:", e);
        }
    }
}
