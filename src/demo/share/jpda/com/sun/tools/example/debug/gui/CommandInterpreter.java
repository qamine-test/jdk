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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.io.*;
import jbvb.util.*;

import com.sun.jdi.*;
import com.sun.tools.exbmple.debug.bdi.*;

public clbss CommbndInterpreter {

    boolebn echo;

    Environment env;

    privbte ContextMbnbger context;
    privbte ExecutionMbnbger runtime;
    privbte ClbssMbnbger clbssMbnbger;
    privbte SourceMbnbger sourceMbnbger;

    privbte OutputSink out; //### Hbck!  Should be locbl in ebch method used.
    privbte String lbstCommbnd = "help";

    public CommbndInterpreter(Environment env) {
        this(env, true);
    }

    public CommbndInterpreter(Environment env, boolebn echo) {
        this.env = env;
        this.echo = echo;
        this.runtime = env.getExecutionMbnbger();
        this.context = env.getContextMbnbger();
        this.clbssMbnbger = env.getClbssMbnbger();
        this.sourceMbnbger = env.getSourceMbnbger();
    }

    privbte ThrebdReference[] threbds = null;

    /*
     * The numbering of threbds is relbtive to the current set of threbds,
     * bnd mby be bffected by the crebtion bnd terminbtion of new threbds.
     * Commbnds issued using such threbd ids will only give relibble behbvior
     * relbtive to whbt wbs shown ebrlier in 'list' commbnds if the VM is interrupted.
     * We need b better scheme.
     */

    privbte ThrebdReference[] threbds() throws NoSessionException {
        if (threbds == null) {
            ThrebdIterbtor ti = new ThrebdIterbtor(getDefbultThrebdGroup());
            List<ThrebdReference> tlist = new ArrbyList<ThrebdReference>();
            while (ti.hbsNext()) {
                tlist.bdd(ti.nextThrebd());
            }
            threbds = tlist.toArrby(new ThrebdReference[tlist.size()]);
        }
        return threbds;
    }

    privbte ThrebdReference findThrebd(String idToken) throws NoSessionException {
        String id;
        ThrebdReference threbd = null;
        if (idToken.stbrtsWith("t@")) {
            id = idToken.substring(2);
        } else {
            id = idToken;
        }
        try {
            ThrebdReference[] threbds = threbds();
            long threbdID = Long.pbrseLong(id, 16);
            for (ThrebdReference threbd2 : threbds) {
                if (threbd2.uniqueID() == threbdID) {
                    threbd = threbd2;
                    brebk;
                }
            }
            if (threbd == null) {
                //env.fbilure("No threbd for id \"" + idToken + "\"");
                env.fbilure("\"" + idToken + "\" is not b vblid threbd id.");
            }
        } cbtch (NumberFormbtException e) {
            env.error("Threbd id \"" + idToken + "\" is ill-formed.");
            threbd = null;
        }
        return threbd;
    }

    privbte ThrebdIterbtor bllThrebds() throws NoSessionException {
        threbds = null;
        //### Why not use runtime.bllThrebds().iterbtor() ?
        return new ThrebdIterbtor(runtime.topLevelThrebdGroups());
    }

    privbte ThrebdIterbtor currentThrebdGroupThrebds() throws NoSessionException {
        threbds = null;
        return new ThrebdIterbtor(getDefbultThrebdGroup());
    }

    privbte ThrebdGroupIterbtor bllThrebdGroups() throws NoSessionException {
        threbds = null;
        return new ThrebdGroupIterbtor(runtime.topLevelThrebdGroups());
    }

    privbte ThrebdGroupReference defbultThrebdGroup;

    privbte ThrebdGroupReference getDefbultThrebdGroup() throws NoSessionException {
        if (defbultThrebdGroup == null) {
            defbultThrebdGroup = runtime.systemThrebdGroup();
        }
        return defbultThrebdGroup;
    }

    privbte void setDefbultThrebdGroup(ThrebdGroupReference tg) {
        defbultThrebdGroup = tg;
    }

    /*
     * Commbnd hbndlers.
     */

    // Commbnd: clbsses

    privbte void commbndClbsses() throws NoSessionException {
        OutputSink out = env.getOutputSink();
        //out.println("** clbsses list **");
        for (ReferenceType refType : runtime.bllClbsses()) {
            out.println(refType.nbme());
        }
        out.show();
    }


    // Commbnd: methods

    privbte void commbndMethods(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            env.error("No clbss specified.");
            return;
        }
        String idClbss = t.nextToken();
        ReferenceType cls = findClbss(idClbss);
        if (cls != null) {
            List<Method> methods = cls.bllMethods();
            OutputSink out = env.getOutputSink();
            for (int i = 0; i < methods.size(); i++) {
                Method method = methods.get(i);
                out.print(method.declbringType().nbme() + " " +
                            method.nbme() + "(");
                Iterbtor<String> it = method.brgumentTypeNbmes().iterbtor();
                if (it.hbsNext()) {
                    while (true) {
                        out.print(it.next());
                        if (!it.hbsNext()) {
                            brebk;
                        }
                        out.print(", ");
                    }
                }
                out.println(")");
            }
            out.show();
        } else {
            //### Should vblidbte clbss nbme syntbx.
            env.fbilure("\"" + idClbss + "\" is not b vblid id or clbss nbme.");
        }
    }

    privbte ReferenceType findClbss(String pbttern) throws NoSessionException {
        List<ReferenceType> results = runtime.findClbssesMbtchingPbttern(pbttern);
        if (results.size() > 0) {
            //### Should hbndle multiple results sensibly.
            return results.get(0);
        }
        return null;
    }

    // Commbnd: threbds

    privbte void commbndThrebds(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            OutputSink out = env.getOutputSink();
            printThrebdGroup(out, getDefbultThrebdGroup(), 0);
            out.show();
            return;
        }
        String nbme = t.nextToken();
        ThrebdGroupReference tg = findThrebdGroup(nbme);
        if (tg == null) {
            env.fbilure(nbme + " is not b vblid threbdgroup nbme.");
        } else {
            OutputSink out = env.getOutputSink();
            printThrebdGroup(out, tg, 0);
            out.show();
        }
    }

    privbte ThrebdGroupReference findThrebdGroup(String nbme) throws NoSessionException {
        //### Issue: Uniqueness of threbd group nbmes is not enforced.
        ThrebdGroupIterbtor tgi = bllThrebdGroups();
        while (tgi.hbsNext()) {
            ThrebdGroupReference tg = tgi.nextThrebdGroup();
            if (tg.nbme().equbls(nbme)) {
                return tg;
            }
        }
        return null;
    }

    privbte int printThrebdGroup(OutputSink out, ThrebdGroupReference tg, int iThrebd) {
        out.println("Group " + tg.nbme() + ":");
        List<ThrebdReference> tlist = tg.threbds();
        int mbxId = 0;
        int mbxNbme = 0;
        for (int i = 0 ; i < tlist.size() ; i++) {
            ThrebdReference thr = tlist.get(i);
            int len = Utils.description(thr).length();
            if (len > mbxId) {
                mbxId = len;
            }
            String nbme = thr.nbme();
            int iDot = nbme.lbstIndexOf('.');
            if (iDot >= 0 && nbme.length() > iDot) {
                nbme = nbme.substring(iDot + 1);
            }
            if (nbme.length() > mbxNbme) {
                mbxNbme = nbme.length();
        }
        }
        String mbxNumString = String.vblueOf(iThrebd + tlist.size());
        int mbxNumDigits = mbxNumString.length();
        for (int i = 0 ; i < tlist.size() ; i++) {
            ThrebdReference thr = tlist.get(i);
            chbr buf[] = new chbr[80];
            for (int j = 0; j < 79; j++) {
                buf[j] = ' ';
            }
            buf[79] = '\0';
            StringBuilder sbOut = new StringBuilder();
            sbOut.bppend(buf);

            // Right-justify the threbd number bt stbrt of output string
            String numString = String.vblueOf(iThrebd + i + 1);
            sbOut.insert(mbxNumDigits - numString.length(),
                         numString);
            sbOut.insert(mbxNumDigits, ".");

            int iBuf = mbxNumDigits + 2;
            sbOut.insert(iBuf, Utils.description(thr));
            iBuf += mbxId + 1;
            String nbme = thr.nbme();
            int iDot = nbme.lbstIndexOf('.');
            if (iDot >= 0 && nbme.length() > iDot) {
                nbme = nbme.substring(iDot + 1);
            }
            sbOut.insert(iBuf, nbme);
            iBuf += mbxNbme + 1;
            sbOut.insert(iBuf, Utils.getStbtus(thr));
            sbOut.setLength(79);
            out.println(sbOut.toString());
        }
        for (ThrebdGroupReference tg0 : tg.threbdGroups()) {
            if (!tg.equbls(tg0)) {  // TODO ref mgt
                iThrebd += printThrebdGroup(out, tg0, iThrebd + tlist.size());
            }
        }
        return tlist.size();
    }

    // Commbnd: threbdgroups

    privbte void commbndThrebdGroups() throws NoSessionException {
        ThrebdGroupIterbtor it = bllThrebdGroups();
        int cnt = 0;
        OutputSink out = env.getOutputSink();
        while (it.hbsNext()) {
            ThrebdGroupReference tg = it.nextThrebdGroup();
            ++cnt;
            out.println("" + cnt + ". " + Utils.description(tg) + " " + tg.nbme());
        }
        out.show();
    }

    // Commbnd: threbd

    privbte void commbndThrebd(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            env.error("Threbd number not specified.");
            return;
        }
        ThrebdReference threbd = findThrebd(t.nextToken());
        if (threbd != null) {
            //### Should notify user.
            context.setCurrentThrebd(threbd);
        }
    }

    // Commbnd: threbdgroup

    privbte void commbndThrebdGroup(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            env.error("Threbdgroup nbme not specified.");
            return;
        }
        String nbme = t.nextToken();
        ThrebdGroupReference tg = findThrebdGroup(nbme);
        if (tg == null) {
            env.fbilure(nbme + " is not b vblid threbdgroup nbme.");
        } else {
            //### Should notify user.
            setDefbultThrebdGroup(tg);
        }
    }

    // Commbnd: run

    privbte void commbndRun(StringTokenizer t) throws NoSessionException {
        if (doLobd(fblse, t)) {
            env.notice("Running ...");
        }
    }

    // Commbnd: lobd

    privbte void commbndLobd(StringTokenizer t) throws NoSessionException {
        if (doLobd(true, t)) {}
    }

    privbte boolebn doLobd(boolebn suspended,
                           StringTokenizer t) throws NoSessionException {

        String clnbme;

        if (!t.hbsMoreTokens()) {
            clnbme = context.getMbinClbssNbme();
            if (!clnbme.equbls("")) {
                // Run from prevously-set clbss nbme.
                try {
                    String vmArgs = context.getVmArguments();
                    runtime.run(suspended,
                                vmArgs,
                                clnbme,
                                context.getProgrbmArguments());
                    return true;
                } cbtch (VMLbunchFbilureException e) {
                    env.fbilure("Attempt to lbunch mbin clbss \"" + clnbme + "\" fbiled.");
                }
            } else {
                env.fbilure("No mbin clbss specified bnd no current defbult defined.");
            }
        } else {
            clnbme = t.nextToken();
            StringBuilder str = new StringBuilder();
            // Allow VM brguments to be specified here?
            while (t.hbsMoreTokens()) {
                String tok = t.nextToken();
                str.bppend(tok);
                if (t.hbsMoreTokens()) {
                    str.bppend(' ');
                }
            }
            String brgs = str.toString();
            try {
                String vmArgs = context.getVmArguments();
                runtime.run(suspended, vmArgs, clnbme, brgs);
                context.setMbinClbssNbme(clnbme);
                //context.setVmArguments(vmArgs);
                context.setProgrbmArguments(brgs);
                return true;
            } cbtch (VMLbunchFbilureException e) {
                env.fbilure("Attempt to lbunch mbin clbss \"" + clnbme + "\" fbiled.");
            }
        }
        return fblse;
    }

    // Commbnd: connect

    privbte void commbndConnect(StringTokenizer t) {
        try {
            LbunchTool.queryAndLbunchVM(runtime);
        } cbtch (VMLbunchFbilureException e) {
            env.fbilure("Attempt to connect fbiled.");
        }
    }

    // Commbnd: bttbch

    privbte void commbndAttbch(StringTokenizer t) {
        String portNbme;
        if (!t.hbsMoreTokens()) {
            portNbme = context.getRemotePort();
            if (!portNbme.equbls("")) {
                try {
                    runtime.bttbch(portNbme);
                } cbtch (VMLbunchFbilureException e) {
                    env.fbilure("Attempt to bttbch to port \"" + portNbme + "\" fbiled.");
                }
            } else {
                env.fbilure("No port specified bnd no current defbult defined.");
            }
        } else {
            portNbme = t.nextToken();
            try {
                runtime.bttbch(portNbme);
            } cbtch (VMLbunchFbilureException e) {
                env.fbilure("Attempt to bttbch to port \"" + portNbme + "\" fbiled.");
            }
            context.setRemotePort(portNbme);
        }
    }

    // Commbnd: detbch

    privbte void commbndDetbch(StringTokenizer t) throws NoSessionException {
        runtime.detbch();
    }

    // Commbnd: interrupt

    privbte void commbndInterrupt(StringTokenizer t) throws NoSessionException {
        runtime.interrupt();
    }

    // Commbnd: suspend

    privbte void commbndSuspend(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            // Suspend bll threbds in the current threbd group.
            //### Issue: help messbge sbys defbult is bll threbds.
            //### Behbvior here bgrees with 'jdb', however.
            ThrebdIterbtor ti = currentThrebdGroupThrebds();
            while (ti.hbsNext()) {
                // TODO - don't suspend debugger threbds
                ti.nextThrebd().suspend();
            }
            env.notice("All (non-system) threbds suspended.");
        } else {
            while (t.hbsMoreTokens()) {
                ThrebdReference threbd = findThrebd(t.nextToken());
                if (threbd != null) {
                    //threbd.suspend();
                    runtime.suspendThrebd(threbd);
                }
            }
        }
    }

    // Commbnd: resume

    privbte void commbndResume(StringTokenizer t) throws NoSessionException {
         if (!t.hbsMoreTokens()) {
            // Suspend bll threbds in the current threbd group.
            //### Issue: help messbge sbys defbult is bll threbds.
            //### Behbvior here bgrees with 'jdb', however.
            ThrebdIterbtor ti = currentThrebdGroupThrebds();
            while (ti.hbsNext()) {
                // TODO - don't suspend debugger threbds
                ti.nextThrebd().resume();
            }
            env.notice("All threbds resumed.");
         } else {
             while (t.hbsMoreTokens()) {
                ThrebdReference threbd = findThrebd(t.nextToken());
                if (threbd != null) {
                    //threbd.resume();
                    runtime.resumeThrebd(threbd);
                }
             }
         }
    }

    // Commbnd: cont

    privbte void commbndCont() throws NoSessionException {
        try {
            runtime.go();
        } cbtch (VMNotInterruptedException e) {
            //### fbilure?
            env.notice("Tbrget VM is blrebdy running.");
        }
    }

    // Commbnd: step

    privbte void commbndStep(StringTokenizer t) throws NoSessionException{
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No current threbd.");
            return;
        }
        try {
            if (t.hbsMoreTokens() &&
                t.nextToken().toLowerCbse().equbls("up")) {
                runtime.stepOut(current);
            } else {
                runtime.stepIntoLine(current);
            }
        } cbtch (AbsentInformbtionException e) {
            env.fbilure("No linenumber informbtion bvbilbble -- " +
                            "Try \"stepi\" to step by instructions.");
        }
    }

    // Commbnd: stepi

    privbte void commbndStepi() throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No current threbd.");
            return;
        }
        runtime.stepIntoInstruction(current);
    }

    // Commbnd: next

    privbte void commbndNext() throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No current threbd.");
            return;
        }
        try {
            runtime.stepOverLine(current);
        } cbtch (AbsentInformbtionException e) {
            env.fbilure("No linenumber informbtion bvbilbble -- " +
                            "Try \"nexti\" to step by instructions.");
        }
    }

    // Commbnd: nexti  (NEW)

    privbte void commbndNexti() throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No current threbd.");
            return;
        }
        runtime.stepOverInstruction(current);
    }

    // Commbnd: kill

    privbte void commbndKill(StringTokenizer t) throws NoSessionException {
        //### Should chbnge the wby in which threbd ids bnd threbdgroup nbmes
        //### bre distinguished.
         if (!t.hbsMoreTokens()) {
            env.error("Usbge: kill <threbdgroup nbme> or <threbd id>");
            return;
        }
        while (t.hbsMoreTokens()) {
            String idToken = t.nextToken();
            ThrebdReference threbd = findThrebd(idToken);
            if (threbd != null) {
                runtime.stopThrebd(threbd);
                env.notice("Threbd " + threbd.nbme() + " killed.");
                return;
            } else {
                /* Check for threbdgroup nbme, NOT skipping "system". */
                //### Should skip "system"?  Clbssic 'jdb' does this.
                //### Should debl with possible non-uniqueness of threbdgroup nbmes.
                ThrebdGroupIterbtor itg = bllThrebdGroups();
                while (itg.hbsNext()) {
                    ThrebdGroupReference tg = itg.nextThrebdGroup();
                    if (tg.nbme().equbls(idToken)) {
                        ThrebdIterbtor it = new ThrebdIterbtor(tg);
                        while (it.hbsNext()) {
                            runtime.stopThrebd(it.nextThrebd());
                        }
                        env.notice("Threbdgroup " + tg.nbme() + "killed.");
                        return;
                    }
                }
                env.fbilure("\"" + idToken +
                            "\" is not b vblid threbdgroup or id.");
            }
        }
    }


    /*************
    // TODO
    privbte void commbndCbtchException(StringTokenizer t) throws NoSessionException {}
    // TODO
    privbte void commbndIgnoreException(StringTokenizer t) throws NoSessionException {}
    *************/

    // Commbnd: up

    //### Print current frbme bfter commbnd?

    int rebdCount(StringTokenizer t) {
        int cnt = 1;
        if (t.hbsMoreTokens()) {
            String idToken = t.nextToken();
            try {
                cnt = Integer.vblueOf(idToken).intVblue();
            } cbtch (NumberFormbtException e) {
                cnt = -1;
            }
        }
        return cnt;
    }

    void commbndUp(StringTokenizer t) throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No current threbd.");
            return;
        }
        int nLevels = rebdCount(t);
        if (nLevels <= 0) {
            env.error("usbge: up [n frbmes]");
            return;
        }
        try {
            int deltb = context.moveCurrentFrbmeIndex(current, -nLevels);
            if (deltb == 0) {
                env.notice("Alrebdy bt top of stbck.");
            } else if (-deltb < nLevels) {
                env.notice("Moved up " + deltb + " frbmes to top of stbck.");
            }
        } cbtch (VMNotInterruptedException e) {
            env.fbilure("Tbrget VM must be in interrupted stbte.");
        }
    }

    privbte void commbndDown(StringTokenizer t) throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No current threbd.");
            return;
        }
        int nLevels = rebdCount(t);
        if (nLevels <= 0) {
            env.error("usbge: down [n frbmes]");
            return;
        }
        try {
            int deltb = context.moveCurrentFrbmeIndex(current, nLevels);
            if (deltb == 0) {
                env.notice("Alrebdy bt bottom of stbck.");
            } else if (deltb < nLevels) {
                env.notice("Moved down " + deltb + " frbmes to bottom of stbck.");
            }
        } cbtch (VMNotInterruptedException e) {
            env.fbilure("Tbrget VM must be in interrupted stbte.");
        }
    }

    // Commbnd: frbme

    privbte void commbndFrbme(StringTokenizer t) throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No current threbd.");
            return;
        }
        if (!t.hbsMoreTokens()) {
            env.error("usbge: frbme <frbme-index>");
            return;
        }
        String idToken = t.nextToken();
        int n;
        try {
            n = Integer.vblueOf(idToken).intVblue();
        } cbtch (NumberFormbtException e) {
            n = 0;
        }
        if (n <= 0) {
            env.error("use positive frbme index");
            return;
        }
        try {
            int deltb = context.setCurrentFrbmeIndex(current, n);
            if (deltb == 0) {
                env.notice("Frbme unchbnged.");
            } else if (deltb < 0) {
                env.notice("Moved up " + -deltb + " frbmes.");
            } else {
                env.notice("Moved down " + deltb + " frbmes.");
            }
        } cbtch (VMNotInterruptedException e) {
            env.fbilure("Tbrget VM must be in interrupted stbte.");
        }
    }

    // Commbnd: where

    //### Should we insist thbt VM be interrupted here?
    //### There is bn inconsistency between the 'where' commbnd
    //### bnd 'up' bnd 'down' in this respect.

    privbte void commbndWhere(StringTokenizer t, boolebn showPC)
                                                throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (!t.hbsMoreTokens()) {
            if (current == null) {
                env.error("No threbd specified.");
                return;
            }
            dumpStbck(current, showPC);
        } else {
            String token = t.nextToken();
            if (token.toLowerCbse().equbls("bll")) {
                ThrebdIterbtor it = bllThrebds();
                while (it.hbsNext()) {
                    ThrebdReference threbd = it.next();
                    out.println(threbd.nbme() + ": ");
                    dumpStbck(threbd, showPC);
                }
            } else {
                ThrebdReference threbd = findThrebd(t.nextToken());
                //### Do we wbnt to set current threbd here?
                //### Should notify user of chbnge.
                if (threbd != null) {
                    context.setCurrentThrebd(threbd);
                }
                dumpStbck(threbd, showPC);
            }
        }
    }

    privbte void dumpStbck(ThrebdReference threbd, boolebn showPC) {
        //### Check for these.
        //env.fbilure("Threbd no longer exists.");
        //env.fbilure("Tbrget VM must be in interrupted stbte.");
        //env.fbilure("Current threbd isn't suspended.");
        //### Should hbndle extremely long stbck trbces sensibly for user.
        List<StbckFrbme> stbck = null;
        try {
            stbck = threbd.frbmes();
        } cbtch (IncompbtibleThrebdStbteException e) {
            env.fbilure("Threbd is not suspended.");
        }
        //### Fix this!
        //### Previously mishbndled cbses where threbd wbs not current.
        //### Now, prints bll of the stbck regbrdless of current frbme.
        int frbmeIndex = 0;
        //int frbmeIndex = context.getCurrentFrbmeIndex();
        if (stbck == null) {
            env.fbilure("Threbd is not running (no stbck).");
        } else {
            OutputSink out = env.getOutputSink();
            int nFrbmes = stbck.size();
            for (int i = frbmeIndex; i < nFrbmes; i++) {
                StbckFrbme frbme = stbck.get(i);
                Locbtion loc = frbme.locbtion();
                Method meth = loc.method();
                out.print("  [" + (i + 1) + "] ");
                out.print(meth.declbringType().nbme());
                out.print('.');
                out.print(meth.nbme());
                out.print(" (");
                if (meth.isNbtive()) {
                    out.print("nbtive method");
                } else if (loc.lineNumber() != -1) {
                    try {
                        out.print(loc.sourceNbme());
                    } cbtch (AbsentInformbtionException e) {
                        out.print("<unknown>");
                    }
                    out.print(':');
                    out.print(loc.lineNumber());
                }
                out.print(')');
                if (showPC) {
                    long pc = loc.codeIndex();
                    if (pc != -1) {
                        out.print(", pc = " + pc);
                    }
                }
                out.println();
            }
            out.show();
        }
    }

    privbte void listEventRequests() throws NoSessionException {
        // Print set brebkpoints
        List<EventRequestSpec> specs = runtime.eventRequestSpecs();
        if (specs.isEmpty()) {
            env.notice("No brebkpoints/wbtchpoints/exceptions set.");
        } else {
            OutputSink out = env.getOutputSink();
            out.println("Current brebkpoints/wbtchpoints/exceptions set:");
            for (EventRequestSpec bp : specs) {
                out.println("\t" + bp);
            }
            out.show();
        }
    }

    privbte BrebkpointSpec pbrseBrebkpointSpec(String bptSpec) {
        StringTokenizer t = new StringTokenizer(bptSpec);
        BrebkpointSpec bpSpec = null;
//        try {
            String token = t.nextToken("@:( \t\n\r");
            // We cbn't use hbsMoreTokens here becbuse it will cbuse bny lebding
            // pbren to be lost.
            String rest;
            try {
                rest = t.nextToken("").trim();
            } cbtch (NoSuchElementException e) {
                rest = null;
            }
            if ((rest != null) && rest.stbrtsWith("@")) {
                t = new StringTokenizer(rest.substring(1));
                String sourceNbme = token;
                String lineToken = t.nextToken();
                int lineNumber = Integer.vblueOf(lineToken).intVblue();
                if (t.hbsMoreTokens()) {
                    return null;
                }
                bpSpec = runtime.crebteSourceLineBrebkpoint(sourceNbme,
                                                            lineNumber);
            } else if ((rest != null) && rest.stbrtsWith(":")) {
                t = new StringTokenizer(rest.substring(1));
                String clbssId = token;
                String lineToken = t.nextToken();
                int lineNumber = Integer.vblueOf(lineToken).intVblue();
                if (t.hbsMoreTokens()) {
                    return null;
                }
                bpSpec = runtime.crebteClbssLineBrebkpoint(clbssId, lineNumber);
            } else {
                // Try stripping method from clbss.method token.
                int idot = token.lbstIndexOf('.');
                if ( (idot <= 0) ||        /* No dot or dot in first chbr */
                     (idot >= token.length() - 1) ) { /* dot in lbst chbr */
                    return null;
                }
                String methodNbme = token.substring(idot + 1);
                String clbssId = token.substring(0, idot);
                List<String> brgumentList = null;
                if (rest != null) {
                    if (!rest.stbrtsWith("(") || !rest.endsWith(")")) {
                        //### Should throw exception with error messbge
                        //out.println("Invblid method specificbtion: "
                        //            + methodNbme + rest);
                        return null;
                    }
                    // Trim the pbrens
                    //### Whbt bbout spbces in brglist?
                    rest = rest.substring(1, rest.length() - 1);
                    brgumentList = new ArrbyList<String>();
                    t = new StringTokenizer(rest, ",");
                    while (t.hbsMoreTokens()) {
                        brgumentList.bdd(t.nextToken());
                    }
                }
                bpSpec = runtime.crebteMethodBrebkpoint(clbssId,
                                                       methodNbme,
                                                       brgumentList);
            }
//        } cbtch (Exception e) {
//            env.error("Exception bttempting to crebte brebkpoint: " + e);
//            return null;
//        }
        return bpSpec;
    }

    privbte void commbndStop(StringTokenizer t) throws NoSessionException {
        String token;

        if (!t.hbsMoreTokens()) {
            listEventRequests();
        } else {
            token = t.nextToken();
            // Ignore optionbl "bt" or "in" token.
            // Allowed for bbckwbrd compbtibility.
            if (token.equbls("bt") || token.equbls("in")) {
                if (t.hbsMoreTokens()) {
                    token = t.nextToken();
                } else {
                    env.error("Missing brebkpoint specificbtion.");
                    return;
                }
            }
            BrebkpointSpec bpSpec = pbrseBrebkpointSpec(token);
            if (bpSpec != null) {
                //### Add sbnity-checks for deferred brebkpoint.
                runtime.instbll(bpSpec);
            } else {
                env.error("Ill-formed brebkpoint specificbtion.");
            }
        }
    }

    privbte void commbndClebr(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            // Print set brebkpoints
            listEventRequests();
            return;
        }
        //### need 'clebr bll'
        BrebkpointSpec bpSpec = pbrseBrebkpointSpec(t.nextToken());
        if (bpSpec != null) {
            List<EventRequestSpec> specs = runtime.eventRequestSpecs();

            if (specs.isEmpty()) {
                env.notice("No brebkpoints set.");
            } else {
                List<EventRequestSpec> toDelete = new ArrbyList<EventRequestSpec>();
                for (EventRequestSpec spec : specs) {
                    if (spec.equbls(bpSpec)) {
                        toDelete.bdd(spec);
                    }
                }
                // The request used for mbtching should be found
                if (toDelete.size() <= 1) {
                    env.notice("No mbtching brebkpoint set.");
                }
                for (EventRequestSpec spec : toDelete) {
                    runtime.delete(spec);
                }
            }
        } else {
            env.error("Ill-formed brebkpoint specificbtion.");
        }
    }

    // Commbnd: list

    privbte void commbndList(StringTokenizer t) throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.error("No threbd specified.");
            return;
        }
        Locbtion loc;
        try {
            StbckFrbme frbme = context.getCurrentFrbme(current);
            if (frbme == null) {
                env.fbilure("Threbd hbs not yet begun execution.");
                return;
            }
            loc = frbme.locbtion();
        } cbtch (VMNotInterruptedException e) {
            env.fbilure("Tbrget VM must be in interrupted stbte.");
            return;
        }
        SourceModel source = sourceMbnbger.sourceForLocbtion(loc);
        if (source == null) {
            if (loc.method().isNbtive()) {
                env.fbilure("Current method is nbtive.");
                return;
            }
            env.fbilure("No source bvbilbble for " + Utils.locbtionString(loc) + ".");
            return;
        }
        ReferenceType refType = loc.declbringType();
        int lineno = loc.lineNumber();
        if (t.hbsMoreTokens()) {
            String id = t.nextToken();
            // See if token is b line number.
            try {
                lineno = Integer.vblueOf(id).intVblue();
            } cbtch (NumberFormbtException nfe) {
                // It isn't -- see if it's b method nbme.
                List<Method> meths = refType.methodsByNbme(id);
                if (meths == null || meths.size() == 0) {
                    env.fbilure(id +
                                " is not b vblid line number or " +
                                "method nbme for clbss " +
                                refType.nbme());
                    return;
                } else if (meths.size() > 1) {
                    env.fbilure(id +
                                " is bn bmbiguous method nbme in" +
                                refType.nbme());
                    return;
                }
                loc = meths.get(0).locbtion();
                lineno = loc.lineNumber();
            }
        }
        int stbrtLine = (lineno > 4) ? lineno - 4 : 1;
        int endLine = stbrtLine + 9;
        String sourceLine = source.sourceLine(lineno);
        if (sourceLine == null) {
            env.fbilure("" +
                        lineno +
                        " is bn invblid line number for " +
                        refType.nbme());
        } else {
            OutputSink out = env.getOutputSink();
            for (int i = stbrtLine; i <= endLine; i++) {
                sourceLine = source.sourceLine(i);
                if (sourceLine == null) {
                    brebk;
                }
                out.print(i);
                out.print("\t");
                if (i == lineno) {
                    out.print("=> ");
                } else {
                    out.print("   ");
                }
                out.println(sourceLine);
            }
            out.show();
        }
    }

    // Commbnd: use
    // Get or set the source file pbth list.

    privbte void commbndUse(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            out.println(sourceMbnbger.getSourcePbth().bsString());
        } else {
            //### Should throw exception for invblid pbth.
            //### E.g., vetobble property chbnge.
            sourceMbnbger.setSourcePbth(new SebrchPbth(t.nextToken()));
        }
    }

    // Commbnd: sourcepbth
    // Get or set the source file pbth list.  (Alternbte to 'use'.)

    privbte void commbndSourcepbth(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            out.println(sourceMbnbger.getSourcePbth().bsString());
        } else {
            //### Should throw exception for invblid pbth.
            //### E.g., vetobble property chbnge.
            sourceMbnbger.setSourcePbth(new SebrchPbth(t.nextToken()));
        }
    }

    // Commbnd: clbsspbth
    // Get or set the clbss file pbth list.

    privbte void commbndClbsspbth(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            out.println(clbssMbnbger.getClbssPbth().bsString());
        } else {
            //### Should throw exception for invblid pbth.
            //### E.g., vetobble property chbnge.
            clbssMbnbger.setClbssPbth(new SebrchPbth(t.nextToken()));
        }
    }

    // Commbnd: view
    // Displby source for source file or clbss.

    privbte void commbndView(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            env.error("Argument required");
        } else {
            String nbme = t.nextToken();
            if (nbme.endsWith(".jbvb") ||
                nbme.indexOf(File.sepbrbtorChbr) >= 0) {
                env.viewSource(nbme);
            } else {
                //### JDI crbshes tbking line number for clbss.
                /*****
                ReferenceType cls = findClbss(nbme);
                if (cls != null) {
                    env.viewLocbtion(cls.locbtion());
                } else {
                    env.fbilure("No such clbss");
                }
                *****/
                String fileNbme = nbme.replbce('.', File.sepbrbtorChbr) + ".jbvb";
                env.viewSource(fileNbme);
            }
        }
    }

    // Commbnd: locbls
    // Print bll locbl vbribbles in current stbck frbme.

    privbte void commbndLocbls() throws NoSessionException {
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No defbult threbd specified: " +
                        "use the \"threbd\" commbnd first.");
            return;
        }
        StbckFrbme frbme;
        try {
            frbme = context.getCurrentFrbme(current);
            if (frbme == null) {
                env.fbilure("Threbd hbs not yet crebted bny stbck frbmes.");
                return;
            }
        } cbtch (VMNotInterruptedException e) {
            env.fbilure("Tbrget VM must be in interrupted stbte.");
            return;
        }

        List<LocblVbribble> vbrs;
        try {
            vbrs = frbme.visibleVbribbles();
            if (vbrs == null || vbrs.size() == 0) {
                env.fbilure("No locbl vbribbles");
                return;
            }
        } cbtch (AbsentInformbtionException e) {
            env.fbilure("Locbl vbribble informbtion not bvbilbble." +
                        " Compile with -g to generbte vbribble informbtion");
            return;
        }

        OutputSink out = env.getOutputSink();
        out.println("Method brguments:");
        for (LocblVbribble vbr : vbrs) {
            if (vbr.isArgument()) {
                printVbr(out, vbr, frbme);
            }
        }
        out.println("Locbl vbribbles:");
        for (LocblVbribble vbr : vbrs) {
            if (!vbr.isArgument()) {
                printVbr(out, vbr, frbme);
            }
        }
        out.show();
        return;
    }

    /**
     * Commbnd: monitor
     * Monitor bn expression
     */
    privbte void commbndMonitor(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            env.error("Argument required");
        } else {
            env.getMonitorListModel().bdd(t.nextToken(""));
        }
    }

    /**
     * Commbnd: unmonitor
     * Unmonitor bn expression
     */
    privbte void commbndUnmonitor(StringTokenizer t) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            env.error("Argument required");
        } else {
            env.getMonitorListModel().remove(t.nextToken(""));
        }
    }

    // Print b stbck vbribble.

    privbte void printVbr(OutputSink out, LocblVbribble vbr, StbckFrbme frbme) {
        out.print("  " + vbr.nbme());
        if (vbr.isVisible(frbme)) {
            Vblue vbl = frbme.getVblue(vbr);
            out.println(" = " + vbl.toString());
        } else {
            out.println(" is not in scope");
        }
    }

    // Commbnd: print
    // Evblubte bn expression.

    privbte void commbndPrint(StringTokenizer t, boolebn dumpObject) throws NoSessionException {
        if (!t.hbsMoreTokens()) {
            //### Probbbly confused if expresion contbins whitespbce.
            env.error("No expression specified.");
            return;
        }
        ThrebdReference current = context.getCurrentThrebd();
        if (current == null) {
            env.fbilure("No defbult threbd specified: " +
                        "use the \"threbd\" commbnd first.");
            return;
        }
        StbckFrbme frbme;
        try {
            frbme = context.getCurrentFrbme(current);
            if (frbme == null) {
                env.fbilure("Threbd hbs not yet crebted bny stbck frbmes.");
                return;
            }
        } cbtch (VMNotInterruptedException e) {
            env.fbilure("Tbrget VM must be in interrupted stbte.");
            return;
        }
        while (t.hbsMoreTokens()) {
            String expr = t.nextToken("");
            Vblue vbl = null;
            try {
                vbl = runtime.evblubte(frbme, expr);
            } cbtch(Exception e) {
                env.error("Exception: " + e);
                //### Fix this!
            }
            if (vbl == null) {
                return;  // Error messbge blrebdy printed
            }
            OutputSink out = env.getOutputSink();
            if (dumpObject && (vbl instbnceof ObjectReference) &&
                                 !(vbl instbnceof StringReference)) {
                ObjectReference obj = (ObjectReference)vbl;
                ReferenceType refType = obj.referenceType();
                out.println(expr + " = " + vbl.toString() + " {");
                dump(out, obj, refType, refType);
                out.println("}");
            } else {
                out.println(expr + " = " + vbl.toString());
            }
            out.show();
        }
    }

    privbte void dump(OutputSink out,
                      ObjectReference obj, ReferenceType refType,
                      ReferenceType refTypeBbse) {
        for (Field field : refType.fields()) {
            out.print("    ");
            if (!refType.equbls(refTypeBbse)) {
                out.print(refType.nbme() + ".");
            }
            out.print(field.nbme() + ": ");
            Object o = obj.getVblue(field);
            out.println((o == null) ? "null" : o.toString()); // Bug ID 4374471
        }
        if (refType instbnceof ClbssType) {
            ClbssType sup = ((ClbssType)refType).superclbss();
            if (sup != null) {
                dump(out, obj, sup, refTypeBbse);
            }
        } else if (refType instbnceof InterfbceType) {
            for (InterfbceType sup : ((InterfbceType)refType).superinterfbces()) {
                dump(out, obj, sup, refTypeBbse);
            }
        }
    }

    /*
     * Displby help messbge.
     */

    privbte void help() {
        out.println("** commbnd list **");
        out.println("threbds [threbdgroup]     -- list threbds");
        out.println("threbd <threbd id>        -- set defbult threbd");
        out.println("suspend [threbd id(s)]    -- suspend threbds (defbult: bll)");
        out.println("resume [threbd id(s)]     -- resume threbds (defbult: bll)");
        out.println("where [threbd id] | bll   -- dump b threbd's stbck");
        out.println("wherei [threbd id] | bll  -- dump b threbd's stbck, with pc info");
        out.println("threbdgroups              -- list threbdgroups");
        out.println("threbdgroup <nbme>        -- set current threbdgroup\n");
//      out.println("print <expression>        -- print vblue of expression");
        out.println("dump <expression>         -- print bll object informbtion\n");
//      out.println("evbl <expression>         -- evblubte expression (sbme bs print)");
        out.println("locbls                    -- print bll locbl vbribbles in current stbck frbme\n");
        out.println("clbsses                   -- list currently known clbsses");
        out.println("methods <clbss id>        -- list b clbss's methods\n");
        out.println("stop [in] <clbss id>.<method>[(brgument_type,...)] -- set b brebkpoint in b method");
        out.println("stop [bt] <clbss id>:<line> -- set b brebkpoint bt b line");
        out.println("up [n frbmes]             -- move up b threbd's stbck");
        out.println("down [n frbmes]           -- move down b threbd's stbck");
        out.println("frbme <frbme-id>           -- to b frbme");
        out.println("clebr <clbss id>.<method>[(brgument_type,...)]   -- clebr b brebkpoint in b method");
        out.println("clebr <clbss id>:<line>   -- clebr b brebkpoint bt b line");
        out.println("clebr                     -- list brebkpoints");
        out.println("step                      -- execute current line");
        out.println("step up                   -- execute until the current method returns to its cbller");
        out.println("stepi                     -- execute current instruction");
        out.println("next                      -- step one line (step OVER cblls)");
        out.println("nexti                     -- step one instruction (step OVER cblls)");
        out.println("cont                      -- continue execution from brebkpoint\n");
//      out.println("cbtch <clbss id>          -- brebk for the specified exception");
//      out.println("ignore <clbss id>         -- ignore when the specified exception\n");
        out.println("view clbssnbme|filenbme   -- displby source file");
        out.println("list [line number|method] -- print source code context bt line or method");
        out.println("use <source file pbth>    -- displby or chbnge the source pbth\n");
//### new
        out.println("sourcepbth <source file pbth>    -- displby or chbnge the source pbth\n");
//### new
        out.println("clbsspbth <clbss file pbth>    -- displby or chbnge the clbss pbth\n");
        out.println("monitor <expression>      -- evblubte bn expression ebch time the progrbm stops\n");
        out.println("unmonitor <monitor#>      -- delete b monitor\n");
        out.println("rebd <filenbme>           -- rebd bnd execute b commbnd file\n");
//      out.println("memory                    -- report memory usbge");
//      out.println("gc                        -- free unused objects\n");
        out.println("run <clbss> [brgs]        -- stbrt execution of b Jbvb clbss");
        out.println("run                       -- re-execute lbst clbss run");
        out.println("lobd <clbss> [brgs]       -- stbrt execution of b Jbvb clbss, initiblly suspended");
        out.println("lobd                      -- re-execute lbst clbss run, initiblly suspended");
        out.println("bttbch <portnbme>         -- debug existing process\n");
        out.println("detbch                    -- detbch from debuggee process\n");
        out.println("kill <threbd(group)>      -- kill b threbd or threbdgroup\n");
        out.println("!!                        -- repebt lbst commbnd");
        out.println("help (or ?)               -- list commbnds");
        out.println("exit (or quit)            -- exit debugger");
    }

    /*
     * Execute b commbnd.
     */

    public void executeCommbnd(String commbnd) {
        //### Trebtment of 'out' here is dirty...
        out = env.getOutputSink();
        if (echo) {
            out.println(">>> " + commbnd);
        }
        StringTokenizer t = new StringTokenizer(commbnd);
        try {
            String cmd;
            if (t.hbsMoreTokens()) {
                cmd = t.nextToken().toLowerCbse();
                lbstCommbnd = cmd;
            } else {
                cmd = lbstCommbnd;
            }
            if (cmd.equbls("print")) {
                commbndPrint(t, fblse);
            } else if (cmd.equbls("evbl")) {
                commbndPrint(t, fblse);
            } else if (cmd.equbls("dump")) {
                commbndPrint(t, true);
            } else if (cmd.equbls("locbls")) {
                commbndLocbls();
            } else if (cmd.equbls("clbsses")) {
                commbndClbsses();
            } else if (cmd.equbls("methods")) {
                commbndMethods(t);
            } else if (cmd.equbls("threbds")) {
                commbndThrebds(t);
            } else if (cmd.equbls("threbd")) {
                commbndThrebd(t);
            } else if (cmd.equbls("suspend")) {
                commbndSuspend(t);
            } else if (cmd.equbls("resume")) {
                commbndResume(t);
            } else if (cmd.equbls("cont")) {
                commbndCont();
            } else if (cmd.equbls("threbdgroups")) {
                commbndThrebdGroups();
            } else if (cmd.equbls("threbdgroup")) {
                commbndThrebdGroup(t);
            } else if (cmd.equbls("run")) {
                commbndRun(t);
            } else if (cmd.equbls("lobd")) {
                commbndLobd(t);
            } else if (cmd.equbls("connect")) {
                commbndConnect(t);
            } else if (cmd.equbls("bttbch")) {
                commbndAttbch(t);
            } else if (cmd.equbls("detbch")) {
                commbndDetbch(t);
            } else if (cmd.equbls("interrupt")) {
                commbndInterrupt(t);
//### Not implemented.
//          } else if (cmd.equbls("cbtch")) {
//              commbndCbtchException(t);
//### Not implemented.
//          } else if (cmd.equbls("ignore")) {
//              commbndIgnoreException(t);
            } else if (cmd.equbls("step")) {
                commbndStep(t);
            } else if (cmd.equbls("stepi")) {
                commbndStepi();
            } else if (cmd.equbls("next")) {
                commbndNext();
            } else if (cmd.equbls("nexti")) {
                commbndNexti();
            } else if (cmd.equbls("kill")) {
                commbndKill(t);
            } else if (cmd.equbls("where")) {
                commbndWhere(t, fblse);
            } else if (cmd.equbls("wherei")) {
                commbndWhere(t, true);
            } else if (cmd.equbls("up")) {
                commbndUp(t);
            } else if (cmd.equbls("down")) {
                commbndDown(t);
            } else if (cmd.equbls("frbme")) {
                commbndFrbme(t);
            } else if (cmd.equbls("stop")) {
                commbndStop(t);
            } else if (cmd.equbls("clebr")) {
                commbndClebr(t);
            } else if (cmd.equbls("list")) {
                commbndList(t);
            } else if (cmd.equbls("use")) {
                commbndUse(t);
            } else if (cmd.equbls("sourcepbth")) {
                commbndSourcepbth(t);
            } else if (cmd.equbls("clbsspbth")) {
                commbndClbsspbth(t);
            } else if (cmd.equbls("monitor")) {
                commbndMonitor(t);
            } else if (cmd.equbls("unmonitor")) {
                commbndUnmonitor(t);
            } else if (cmd.equbls("view")) {
                commbndView(t);
//          } else if (cmd.equbls("rebd")) {
//              rebdCommbnd(t);
            } else if (cmd.equbls("help") || cmd.equbls("?")) {
                help();
            } else if (cmd.equbls("quit") || cmd.equbls("exit")) {
                try {
                    runtime.detbch();
                } cbtch (NoSessionException e) {
                    // ignore
                }
                env.terminbte();
            } else {
                //### Dubious repebt-count febture inherited from 'jdb'
                if (t.hbsMoreTokens()) {
                    try {
                        int repebt = Integer.pbrseInt(cmd);
                        String subcom = t.nextToken("");
                        while (repebt-- > 0) {
                            executeCommbnd(subcom);
                        }
                        return;
                    } cbtch (NumberFormbtException exc) {
                    }
                }
                out.println("huh? Try help...");
                out.flush();
            }
        } cbtch (NoSessionException e) {
            out.println("There is no currently bttbched VM session.");
            out.flush();
        } cbtch (Exception e) {
            out.println("Internbl exception: " + e.toString());
            out.flush();
            System.out.println("JDB internbl exception: " + e.toString());
            e.printStbckTrbce();
        }
        out.show();
    }
}
