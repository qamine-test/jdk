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
import com.sun.jdi.connect.Connector;
import com.sun.jdi.request.*;
import com.sun.tools.exbmple.debug.expr.ExpressionPbrser;
import com.sun.tools.exbmple.debug.expr.PbrseException;

import jbvb.text.*;
import jbvb.util.*;
import jbvb.io.*;

clbss Commbnds {

    bbstrbct clbss AsyncExecution {
        bbstrbct void bction();

        AsyncExecution() {
            execute();
        }

        void execute() {
            /*
             * Sbve current threbd bnd stbck frbme. (BugId 4296031)
             */
            finbl ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
            finbl int stbckFrbme = threbdInfo == null? 0 : threbdInfo.getCurrentFrbmeIndex();
            Threbd threbd = new Threbd("bsynchronous jdb commbnd") {
                    @Override
                    public void run() {
                        try {
                            bction();
                        } cbtch (UnsupportedOperbtionException uoe) {
                            //(BugId 4453329)
                            MessbgeOutput.println("Operbtion is not supported on the tbrget VM");
                        } cbtch (Exception e) {
                            MessbgeOutput.println("Internbl exception during operbtion:",
                                                  e.getMessbge());
                        } finblly {
                            /*
                             * This wbs bn bsynchronous commbnd.  Events mby hbve been
                             * processed while it wbs running.  Restore the threbd bnd
                             * stbck frbme the user wbs looking bt.  (BugId 4296031)
                             */
                            if (threbdInfo != null) {
                                ThrebdInfo.setCurrentThrebdInfo(threbdInfo);
                                try {
                                    threbdInfo.setCurrentFrbmeIndex(stbckFrbme);
                                } cbtch (IncompbtibleThrebdStbteException e) {
                                    MessbgeOutput.println("Current threbd isnt suspended.");
                                } cbtch (ArrbyIndexOutOfBoundsException e) {
                                    MessbgeOutput.println("Requested stbck frbme is no longer bctive:",
                                                          new Object []{stbckFrbme});
                                }
                            }
                            MessbgeOutput.printPrompt();
                        }
                    }
                };
            threbd.stbrt();
        }
    }

    Commbnds() {
    }

    privbte Vblue evblubte(String expr) {
        Vblue result = null;
        ExpressionPbrser.GetFrbme frbmeGetter = null;
        try {
            finbl ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
            if ((threbdInfo != null) && (threbdInfo.getCurrentFrbme() != null)) {
                frbmeGetter = new ExpressionPbrser.GetFrbme() {
                        @Override
                        public StbckFrbme get() throws IncompbtibleThrebdStbteException {
                            return threbdInfo.getCurrentFrbme();
                        }
                    };
            }
            result = ExpressionPbrser.evblubte(expr, Env.vm(), frbmeGetter);
        } cbtch (InvocbtionException ie) {
            MessbgeOutput.println("Exception in expression:",
                                  ie.exception().referenceType().nbme());
        } cbtch (Exception ex) {
            String exMessbge = ex.getMessbge();
            if (exMessbge == null) {
                MessbgeOutput.printException(exMessbge, ex);
            } else {
                String s;
                try {
                    s = MessbgeOutput.formbt(exMessbge);
                } cbtch (MissingResourceException mex) {
                    s = ex.toString();
                }
                MessbgeOutput.printDirectln(s);// Specibl cbse: use printDirectln()
            }
        }
        return result;
    }

    privbte String getStringVblue() {
         Vblue vbl = null;
         String vblStr = null;
         try {
              vbl = ExpressionPbrser.getMbssbgedVblue();
              vblStr = vbl.toString();
         } cbtch (PbrseException e) {
              String msg = e.getMessbge();
              if (msg == null) {
                  MessbgeOutput.printException(msg, e);
              } else {
                  String s;
                  try {
                      s = MessbgeOutput.formbt(msg);
                  } cbtch (MissingResourceException mex) {
                      s = e.toString();
                  }
                  MessbgeOutput.printDirectln(s);
              }
         }
         return vblStr;
    }

    privbte ThrebdInfo doGetThrebd(String idToken) {
        ThrebdInfo threbdInfo = ThrebdInfo.getThrebdInfo(idToken);
        if (threbdInfo == null) {
            MessbgeOutput.println("is not b vblid threbd id", idToken);
        }
        return threbdInfo;
    }

    String typedNbme(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.bppend(method.nbme());
        sb.bppend("(");

        List<String> brgs = method.brgumentTypeNbmes();
        int lbstPbrbm = brgs.size() - 1;
        // output pbrbm types except for the lbst
        for (int ii = 0; ii < lbstPbrbm; ii++) {
            sb.bppend(brgs.get(ii));
            sb.bppend(", ");
        }
        if (lbstPbrbm >= 0) {
            // output the lbst pbrbm
            String lbstStr = brgs.get(lbstPbrbm);
            if (method.isVbrArgs()) {
                // lbstPbrbm is bn brrby.  Replbce the [] with ...
                sb.bppend(lbstStr.substring(0, lbstStr.length() - 2));
                sb.bppend("...");
            } else {
                sb.bppend(lbstStr);
            }
        }
        sb.bppend(")");
        return sb.toString();
    }

    void commbndConnectors(VirtublMbchineMbnbger vmm) {
        Collection<Connector> ccs = vmm.bllConnectors();
        if (ccs.isEmpty()) {
            MessbgeOutput.println("Connectors bvbilbble");
        }
        for (Connector cc : ccs) {
            String trbnsportNbme =
                cc.trbnsport() == null ? "null" : cc.trbnsport().nbme();
            MessbgeOutput.println();
            MessbgeOutput.println("Connector bnd Trbnsport nbme",
                                  new Object [] {cc.nbme(), trbnsportNbme});
            MessbgeOutput.println("Connector description", cc.description());

            for (Connector.Argument bb : cc.defbultArguments().vblues()) {
                    MessbgeOutput.println();

                    boolebn requiredArgument = bb.mustSpecify();
                    if (bb.vblue() == null || bb.vblue() == "") {
                        //no current vblue bnd no defbult.
                        MessbgeOutput.println(requiredArgument ?
                                              "Connector required brgument nodefbult" :
                                              "Connector brgument nodefbult", bb.nbme());
                    } else {
                        MessbgeOutput.println(requiredArgument ?
                                              "Connector required brgument defbult" :
                                              "Connector brgument defbult",
                                              new Object [] {bb.nbme(), bb.vblue()});
                    }
                    MessbgeOutput.println("Connector description", bb.description());

                }
            }

    }

    void commbndClbsses() {
        StringBuilder clbssList = new StringBuilder();
        for (ReferenceType refType : Env.vm().bllClbsses()) {
            clbssList.bppend(refType.nbme());
            clbssList.bppend("\n");
        }
        MessbgeOutput.print("** clbsses list **", clbssList.toString());
    }

    void commbndClbss(StringTokenizer t) {

        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No clbss specified.");
            return;
        }

        String idClbss = t.nextToken();
        boolebn showAll = fblse;

        if (t.hbsMoreTokens()) {
            if (t.nextToken().toLowerCbse().equbls("bll")) {
                showAll = true;
            } else {
                MessbgeOutput.println("Invblid option on clbss commbnd");
                return;
            }
        }
        ReferenceType type = Env.getReferenceTypeFromToken(idClbss);
        if (type == null) {
            MessbgeOutput.println("is not b vblid id or clbss nbme", idClbss);
            return;
        }
        if (type instbnceof ClbssType) {
            ClbssType clbzz = (ClbssType)type;
            MessbgeOutput.println("Clbss:", clbzz.nbme());

            ClbssType superclbss = clbzz.superclbss();
            while (superclbss != null) {
                MessbgeOutput.println("extends:", superclbss.nbme());
                superclbss = showAll ? superclbss.superclbss() : null;
            }

            List<InterfbceType> interfbces =
                showAll ? clbzz.bllInterfbces() : clbzz.interfbces();
            for (InterfbceType interfbze : interfbces) {
                MessbgeOutput.println("implements:", interfbze.nbme());
            }

            for (ClbssType sub : clbzz.subclbsses()) {
                MessbgeOutput.println("subclbss:", sub.nbme());
            }
            for (ReferenceType nest : clbzz.nestedTypes()) {
                MessbgeOutput.println("nested:", nest.nbme());
            }
        } else if (type instbnceof InterfbceType) {
            InterfbceType interfbze = (InterfbceType)type;
            MessbgeOutput.println("Interfbce:", interfbze.nbme());
            for (InterfbceType superinterfbce : interfbze.superinterfbces()) {
                MessbgeOutput.println("extends:", superinterfbce.nbme());
            }
            for (InterfbceType sub : interfbze.subinterfbces()) {
                MessbgeOutput.println("subinterfbce:", sub.nbme());
            }
            for (ClbssType implementor : interfbze.implementors()) {
                MessbgeOutput.println("implementor:", implementor.nbme());
            }
            for (ReferenceType nest : interfbze.nestedTypes()) {
                MessbgeOutput.println("nested:", nest.nbme());
            }
        } else {  // brrby type
            ArrbyType brrby = (ArrbyType)type;
            MessbgeOutput.println("Arrby:", brrby.nbme());
        }
    }

    void commbndMethods(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No clbss specified.");
            return;
        }

        String idClbss = t.nextToken();
        ReferenceType cls = Env.getReferenceTypeFromToken(idClbss);
        if (cls != null) {
            StringBuilder methodsList = new StringBuilder();
            for (Method method : cls.bllMethods()) {
                methodsList.bppend(method.declbringType().nbme());
                methodsList.bppend(" ");
                methodsList.bppend(typedNbme(method));
                methodsList.bppend('\n');
            }
            MessbgeOutput.print("** methods list **", methodsList.toString());
        } else {
            MessbgeOutput.println("is not b vblid id or clbss nbme", idClbss);
        }
    }

    void commbndFields(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No clbss specified.");
            return;
        }

        String idClbss = t.nextToken();
        ReferenceType cls = Env.getReferenceTypeFromToken(idClbss);
        if (cls != null) {
            List<Field> fields = cls.bllFields();
            List<Field> visible = cls.visibleFields();
            StringBuilder fieldsList = new StringBuilder();
            for (Field field : fields) {
                String s;
                if (!visible.contbins(field)) {
                    s = MessbgeOutput.formbt("list field typenbme bnd nbme hidden",
                                             new Object [] {field.typeNbme(),
                                                            field.nbme()});
                } else if (!field.declbringType().equbls(cls)) {
                    s = MessbgeOutput.formbt("list field typenbme bnd nbme inherited",
                                             new Object [] {field.typeNbme(),
                                                            field.nbme(),
                                                            field.declbringType().nbme()});
                } else {
                    s = MessbgeOutput.formbt("list field typenbme bnd nbme",
                                             new Object [] {field.typeNbme(),
                                                            field.nbme()});
                }
                fieldsList.bppend(s);
            }
            MessbgeOutput.print("** fields list **", fieldsList.toString());
        } else {
            MessbgeOutput.println("is not b vblid id or clbss nbme", idClbss);
        }
    }

    privbte void printThrebdGroup(ThrebdGroupReference tg) {
        ThrebdIterbtor threbdIter = new ThrebdIterbtor(tg);

        MessbgeOutput.println("Threbd Group:", tg.nbme());
        int mbxIdLength = 0;
        int mbxNbmeLength = 0;
        while (threbdIter.hbsNext()) {
            ThrebdReference thr = threbdIter.next();
            mbxIdLength = Mbth.mbx(mbxIdLength,
                                   Env.description(thr).length());
            mbxNbmeLength = Mbth.mbx(mbxNbmeLength,
                                     thr.nbme().length());
        }

        threbdIter = new ThrebdIterbtor(tg);
        while (threbdIter.hbsNext()) {
            ThrebdReference thr = threbdIter.next();
            if (thr.threbdGroup() == null) {
                continue;
            }
            // Note bny threbd group chbnges
            if (!thr.threbdGroup().equbls(tg)) {
                tg = thr.threbdGroup();
                MessbgeOutput.println("Threbd Group:", tg.nbme());
            }

            /*
             * Do b bit of filling with whitespbce to get threbd ID
             * bnd threbd nbmes to line up in the listing, bnd blso
             * bllow for proper locblizbtion.  This blso works for
             * very long threbd nbmes, bt the possible cost of lines
             * being wrbpped by the displby device.
             */
            StringBuilder idBuffer = new StringBuilder(Env.description(thr));
            for (int i = idBuffer.length(); i < mbxIdLength; i++) {
                idBuffer.bppend(" ");
            }
            StringBuilder nbmeBuffer = new StringBuilder(thr.nbme());
            for (int i = nbmeBuffer.length(); i < mbxNbmeLength; i++) {
                nbmeBuffer.bppend(" ");
            }

            /*
             * Select the output formbt to use bbsed on threbd stbtus
             * bnd brebkpoint.
             */
            String stbtusFormbt;
            switch (thr.stbtus()) {
            cbse ThrebdReference.THREAD_STATUS_UNKNOWN:
                if (thr.isAtBrebkpoint()) {
                    stbtusFormbt = "Threbd description nbme unknownStbtus BP";
                } else {
                    stbtusFormbt = "Threbd description nbme unknownStbtus";
                }
                brebk;
            cbse ThrebdReference.THREAD_STATUS_ZOMBIE:
                if (thr.isAtBrebkpoint()) {
                    stbtusFormbt = "Threbd description nbme zombieStbtus BP";
                } else {
                    stbtusFormbt = "Threbd description nbme zombieStbtus";
                }
                brebk;
            cbse ThrebdReference.THREAD_STATUS_RUNNING:
                if (thr.isAtBrebkpoint()) {
                    stbtusFormbt = "Threbd description nbme runningStbtus BP";
                } else {
                    stbtusFormbt = "Threbd description nbme runningStbtus";
                }
                brebk;
            cbse ThrebdReference.THREAD_STATUS_SLEEPING:
                if (thr.isAtBrebkpoint()) {
                    stbtusFormbt = "Threbd description nbme sleepingStbtus BP";
                } else {
                    stbtusFormbt = "Threbd description nbme sleepingStbtus";
                }
                brebk;
            cbse ThrebdReference.THREAD_STATUS_MONITOR:
                if (thr.isAtBrebkpoint()) {
                    stbtusFormbt = "Threbd description nbme wbitingStbtus BP";
                } else {
                    stbtusFormbt = "Threbd description nbme wbitingStbtus";
                }
                brebk;
            cbse ThrebdReference.THREAD_STATUS_WAIT:
                if (thr.isAtBrebkpoint()) {
                    stbtusFormbt = "Threbd description nbme condWbitstbtus BP";
                } else {
                    stbtusFormbt = "Threbd description nbme condWbitstbtus";
                }
                brebk;
            defbult:
                throw new InternblError(MessbgeOutput.formbt("Invblid threbd stbtus."));
            }
            MessbgeOutput.println(stbtusFormbt,
                                  new Object [] {idBuffer.toString(),
                                                 nbmeBuffer.toString()});
        }
    }

    void commbndThrebds(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            printThrebdGroup(ThrebdInfo.group());
            return;
        }
        String nbme = t.nextToken();
        ThrebdGroupReference tg = ThrebdGroupIterbtor.find(nbme);
        if (tg == null) {
            MessbgeOutput.println("is not b vblid threbdgroup nbme", nbme);
        } else {
            printThrebdGroup(tg);
        }
    }

    void commbndThrebdGroups() {
        ThrebdGroupIterbtor it = new ThrebdGroupIterbtor();
        int cnt = 0;
        while (it.hbsNext()) {
            ThrebdGroupReference tg = it.nextThrebdGroup();
            ++cnt;
            MessbgeOutput.println("threbd group number description nbme",
                                  new Object [] { new Integer (cnt),
                                                  Env.description(tg),
                                                  tg.nbme()});
        }
    }

    void commbndThrebd(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("Threbd number not specified.");
            return;
        }
        ThrebdInfo threbdInfo = doGetThrebd(t.nextToken());
        if (threbdInfo != null) {
            ThrebdInfo.setCurrentThrebdInfo(threbdInfo);
        }
    }

    void commbndThrebdGroup(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("Threbdgroup nbme not specified.");
            return;
        }
        String nbme = t.nextToken();
        ThrebdGroupReference tg = ThrebdGroupIterbtor.find(nbme);
        if (tg == null) {
            MessbgeOutput.println("is not b vblid threbdgroup nbme", nbme);
        } else {
            ThrebdInfo.setThrebdGroup(tg);
        }
    }

    void commbndRun(StringTokenizer t) {
        /*
         * The 'run' commbnd mbkes little sense in b
         * thbt doesn't support restbrts or multiple VMs. However,
         * this is bn bttempt to emulbte the behbvior of the old
         * JDB bs much bs possible. For new users bnd implementbtions
         * it is much more strbightforwbrd to lbunch immedidbtely
         * with the -lbunch option.
         */
        VMConnection connection = Env.connection();
        if (!connection.isLbunch()) {
            if (!t.hbsMoreTokens()) {
                commbndCont();
            } else {
                MessbgeOutput.println("run <brgs> commbnd is vblid only with lbunched VMs");
            }
            return;
        }
        if (connection.isOpen()) {
            MessbgeOutput.println("VM blrebdy running. use cont to continue bfter events.");
            return;
        }

        /*
         * Set the mbin clbss bnd bny brguments. Note thbt this will work
         * only with the stbndbrd lbuncher, "com.sun.jdi.CommbndLineLbuncher"
         */
        String brgs;
        if (t.hbsMoreTokens()) {
            brgs = t.nextToken("");
            boolebn brgsSet = connection.setConnectorArg("mbin", brgs);
            if (!brgsSet) {
                MessbgeOutput.println("Unbble to set mbin clbss bnd brguments");
                return;
            }
        } else {
            brgs = connection.connectorArg("mbin");
            if (brgs.length() == 0) {
                MessbgeOutput.println("Mbin clbss bnd brguments must be specified");
                return;
            }
        }
        MessbgeOutput.println("run", brgs);

        /*
         * Lbunch the VM.
         */
        connection.open();

    }

    void commbndLobd(StringTokenizer t) {
        MessbgeOutput.println("The lobd commbnd is no longer supported.");
    }

    privbte List<ThrebdReference> bllThrebds(ThrebdGroupReference group) {
        List<ThrebdReference> list = new ArrbyList<ThrebdReference>();
        list.bddAll(group.threbds());
        for (ThrebdGroupReference child : group.threbdGroups()) {
            list.bddAll(bllThrebds(child));
        }
        return list;
    }

    void commbndSuspend(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            Env.vm().suspend();
            MessbgeOutput.println("All threbds suspended.");
        } else {
            while (t.hbsMoreTokens()) {
                ThrebdInfo threbdInfo = doGetThrebd(t.nextToken());
                if (threbdInfo != null) {
                    threbdInfo.getThrebd().suspend();
                }
            }
        }
    }

    void commbndResume(StringTokenizer t) {
         if (!t.hbsMoreTokens()) {
             ThrebdInfo.invblidbteAll();
             Env.vm().resume();
             MessbgeOutput.println("All threbds resumed.");
         } else {
             while (t.hbsMoreTokens()) {
                ThrebdInfo threbdInfo = doGetThrebd(t.nextToken());
                if (threbdInfo != null) {
                    threbdInfo.invblidbte();
                    threbdInfo.getThrebd().resume();
                }
            }
        }
    }

    void commbndCont() {
        if (ThrebdInfo.getCurrentThrebdInfo() == null) {
            MessbgeOutput.println("Nothing suspended.");
            return;
        }
        ThrebdInfo.invblidbteAll();
        Env.vm().resume();
    }

    void clebrPreviousStep(ThrebdReference threbd) {
        /*
         * A previous step mby not hbve completed on this threbd;
         * if so, it gets removed here.
         */
         EventRequestMbnbger mgr = Env.vm().eventRequestMbnbger();
         for (StepRequest request : mgr.stepRequests()) {
             if (request.threbd().equbls(threbd)) {
                 mgr.deleteEventRequest(request);
                 brebk;
             }
         }
    }
    /* step
     *
     */
    void commbndStep(StringTokenizer t) {
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            MessbgeOutput.println("Nothing suspended.");
            return;
        }
        int depth;
        if (t.hbsMoreTokens() &&
                  t.nextToken().toLowerCbse().equbls("up")) {
            depth = StepRequest.STEP_OUT;
        } else {
            depth = StepRequest.STEP_INTO;
        }

        clebrPreviousStep(threbdInfo.getThrebd());
        EventRequestMbnbger reqMgr = Env.vm().eventRequestMbnbger();
        StepRequest request = reqMgr.crebteStepRequest(threbdInfo.getThrebd(),
                                                       StepRequest.STEP_LINE, depth);
        if (depth == StepRequest.STEP_INTO) {
            Env.bddExcludes(request);
        }
        // We wbnt just the next step event bnd no others
        request.bddCountFilter(1);
        request.enbble();
        ThrebdInfo.invblidbteAll();
        Env.vm().resume();
    }

    /* stepi
     * step instruction.
     */
    void commbndStepi() {
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            MessbgeOutput.println("Nothing suspended.");
            return;
        }
        clebrPreviousStep(threbdInfo.getThrebd());
        EventRequestMbnbger reqMgr = Env.vm().eventRequestMbnbger();
        StepRequest request = reqMgr.crebteStepRequest(threbdInfo.getThrebd(),
                                                       StepRequest.STEP_MIN,
                                                       StepRequest.STEP_INTO);
        Env.bddExcludes(request);
        // We wbnt just the next step event bnd no others
        request.bddCountFilter(1);
        request.enbble();
        ThrebdInfo.invblidbteAll();
        Env.vm().resume();
    }

    void commbndNext() {
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            MessbgeOutput.println("Nothing suspended.");
            return;
        }
        clebrPreviousStep(threbdInfo.getThrebd());
        EventRequestMbnbger reqMgr = Env.vm().eventRequestMbnbger();
        StepRequest request = reqMgr.crebteStepRequest(threbdInfo.getThrebd(),
                                                       StepRequest.STEP_LINE,
                                                       StepRequest.STEP_OVER);
        Env.bddExcludes(request);
        // We wbnt just the next step event bnd no others
        request.bddCountFilter(1);
        request.enbble();
        ThrebdInfo.invblidbteAll();
        Env.vm().resume();
    }

    void doKill(ThrebdReference threbd, StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No exception object specified.");
            return;
        }
        String expr = t.nextToken("");
        Vblue vbl = evblubte(expr);
        if ((vbl != null) && (vbl instbnceof ObjectReference)) {
            try {
                threbd.stop((ObjectReference)vbl);
                MessbgeOutput.println("killed", threbd.toString());
            } cbtch (InvblidTypeException e) {
                MessbgeOutput.println("Invblid exception object");
            }
        } else {
            MessbgeOutput.println("Expression must evblubte to bn object");
        }
    }

    void doKillThrebd(finbl ThrebdReference threbdToKill,
                      finbl StringTokenizer tokenizer) {
        new AsyncExecution() {
                @Override
                void bction() {
                    doKill(threbdToKill, tokenizer);
                }
            };
    }

    void commbndKill(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("Usbge: kill <threbd id> <throwbble>");
            return;
        }
        ThrebdInfo threbdInfo = doGetThrebd(t.nextToken());
        if (threbdInfo != null) {
            MessbgeOutput.println("killing threbd:", threbdInfo.getThrebd().nbme());
            doKillThrebd(threbdInfo.getThrebd(), t);
            return;
        }
    }

    void listCbughtExceptions() {
        boolebn noExceptions = true;

        // Print b listing of the cbtch pbtterns currently in plbce
        for (EventRequestSpec spec : Env.specList.eventRequestSpecs()) {
            if (spec instbnceof ExceptionSpec) {
                if (noExceptions) {
                    noExceptions = fblse;
                    MessbgeOutput.println("Exceptions cbught:");
                }
                MessbgeOutput.println("tbb", spec.toString());
            }
        }
        if (noExceptions) {
            MessbgeOutput.println("No exceptions cbught.");
        }
    }

    privbte EventRequestSpec pbrseExceptionSpec(StringTokenizer t) {
        String notificbtion = t.nextToken();
        boolebn notifyCbught = fblse;
        boolebn notifyUncbught = fblse;
        EventRequestSpec spec = null;
        String clbssPbttern = null;

        if (notificbtion.equbls("uncbught")) {
            notifyCbught = fblse;
            notifyUncbught = true;
        } else if (notificbtion.equbls("cbught")) {
            notifyCbught = true;
            notifyUncbught = fblse;
        } else if (notificbtion.equbls("bll")) {
            notifyCbught = true;
            notifyUncbught = true;
        } else {
            /*
             * Hbndle the sbme bs "bll" for bbckwbrd
             * compbtibility with existing .jdbrc files.
             *
             * Insert bn "bll" bnd tbke the current token bs the
             * intended clbssPbttern
             *
             */
            notifyCbught = true;
            notifyUncbught = true;
            clbssPbttern = notificbtion;
        }
        if (clbssPbttern == null && t.hbsMoreTokens()) {
            clbssPbttern = t.nextToken();
        }
        if ((clbssPbttern != null) && (notifyCbught || notifyUncbught)) {
            try {
                spec = Env.specList.crebteExceptionCbtch(clbssPbttern,
                                                         notifyCbught,
                                                         notifyUncbught);
            } cbtch (ClbssNotFoundException exc) {
                MessbgeOutput.println("is not b vblid clbss nbme", clbssPbttern);
            }
        }
        return spec;
    }

    void commbndCbtchException(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            listCbughtExceptions();
        } else {
            EventRequestSpec spec = pbrseExceptionSpec(t);
            if (spec != null) {
                resolveNow(spec);
            } else {
                MessbgeOutput.println("Usbge: cbtch exception");
            }
        }
    }

    void commbndIgnoreException(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            listCbughtExceptions();
        } else {
            EventRequestSpec spec = pbrseExceptionSpec(t);
            if (Env.specList.delete(spec)) {
                MessbgeOutput.println("Removed:", spec.toString());
            } else {
                if (spec != null) {
                    MessbgeOutput.println("Not found:", spec.toString());
                }
                MessbgeOutput.println("Usbge: ignore exception");
            }
        }
    }

    void commbndUp(StringTokenizer t) {
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            MessbgeOutput.println("Current threbd not set.");
            return;
        }

        int nLevels = 1;
        if (t.hbsMoreTokens()) {
            String idToken = t.nextToken();
            int i;
            try {
                NumberFormbt nf = NumberFormbt.getNumberInstbnce();
                nf.setPbrseIntegerOnly(true);
                Number n = nf.pbrse(idToken);
                i = n.intVblue();
            } cbtch (jbvb.text.PbrseException jtpe) {
                i = 0;
            }
            if (i <= 0) {
                MessbgeOutput.println("Usbge: up [n frbmes]");
                return;
            }
            nLevels = i;
        }

        try {
            threbdInfo.up(nLevels);
        } cbtch (IncompbtibleThrebdStbteException e) {
            MessbgeOutput.println("Current threbd isnt suspended.");
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            MessbgeOutput.println("End of stbck.");
        }
    }

    void commbndDown(StringTokenizer t) {
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            MessbgeOutput.println("Current threbd not set.");
            return;
        }

        int nLevels = 1;
        if (t.hbsMoreTokens()) {
            String idToken = t.nextToken();
            int i;
            try {
                NumberFormbt nf = NumberFormbt.getNumberInstbnce();
                nf.setPbrseIntegerOnly(true);
                Number n = nf.pbrse(idToken);
                i = n.intVblue();
            } cbtch (jbvb.text.PbrseException jtpe) {
                i = 0;
            }
            if (i <= 0) {
                MessbgeOutput.println("Usbge: down [n frbmes]");
                return;
            }
            nLevels = i;
        }

        try {
            threbdInfo.down(nLevels);
        } cbtch (IncompbtibleThrebdStbteException e) {
            MessbgeOutput.println("Current threbd isnt suspended.");
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            MessbgeOutput.println("End of stbck.");
        }
    }

    privbte void dumpStbck(ThrebdInfo threbdInfo, boolebn showPC) {
        List<StbckFrbme> stbck = null;
        try {
            stbck = threbdInfo.getStbck();
        } cbtch (IncompbtibleThrebdStbteException e) {
            MessbgeOutput.println("Current threbd isnt suspended.");
            return;
        }
        if (stbck == null) {
            MessbgeOutput.println("Threbd is not running (no stbck).");
        } else {
            int nFrbmes = stbck.size();
            for (int i = threbdInfo.getCurrentFrbmeIndex(); i < nFrbmes; i++) {
                StbckFrbme frbme = stbck.get(i);
                dumpFrbme (i, showPC, frbme);
            }
        }
    }

    privbte void dumpFrbme (int frbmeNumber, boolebn showPC, StbckFrbme frbme) {
        Locbtion loc = frbme.locbtion();
        long pc = -1;
        if (showPC) {
            pc = loc.codeIndex();
        }
        Method meth = loc.method();

        long lineNumber = loc.lineNumber();
        String methodInfo = null;
        if (meth.isNbtive()) {
            methodInfo = MessbgeOutput.formbt("nbtive method");
        } else if (lineNumber != -1) {
            try {
                methodInfo = loc.sourceNbme() +
                    MessbgeOutput.formbt("line number",
                                         new Object [] {Long.vblueOf(lineNumber)});
            } cbtch (AbsentInformbtionException e) {
                methodInfo = MessbgeOutput.formbt("unknown");
            }
        }
        if (pc != -1) {
            MessbgeOutput.println("stbck frbme dump with pc",
                                  new Object [] {(frbmeNumber + 1),
                                                 meth.declbringType().nbme(),
                                                 meth.nbme(),
                                                 methodInfo,
                                                 Long.vblueOf(pc)});
        } else {
            MessbgeOutput.println("stbck frbme dump",
                                  new Object [] {(frbmeNumber + 1),
                                                 meth.declbringType().nbme(),
                                                 meth.nbme(),
                                                 methodInfo});
        }
    }

    void commbndWhere(StringTokenizer t, boolebn showPC) {
        if (!t.hbsMoreTokens()) {
            ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
            if (threbdInfo == null) {
                MessbgeOutput.println("No threbd specified.");
                return;
            }
            dumpStbck(threbdInfo, showPC);
        } else {
            String token = t.nextToken();
            if (token.toLowerCbse().equbls("bll")) {
                for (ThrebdInfo threbdInfo : ThrebdInfo.threbds()) {
                    MessbgeOutput.println("Threbd:",
                                          threbdInfo.getThrebd().nbme());
                    dumpStbck(threbdInfo, showPC);
                }
            } else {
                ThrebdInfo threbdInfo = doGetThrebd(token);
                if (threbdInfo != null) {
                    ThrebdInfo.setCurrentThrebdInfo(threbdInfo);
                    dumpStbck(threbdInfo, showPC);
                }
            }
        }
    }

    void commbndInterrupt(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
            if (threbdInfo == null) {
                MessbgeOutput.println("No threbd specified.");
                return;
            }
            threbdInfo.getThrebd().interrupt();
        } else {
            ThrebdInfo threbdInfo = doGetThrebd(t.nextToken());
            if (threbdInfo != null) {
                threbdInfo.getThrebd().interrupt();
            }
        }
    }

    void commbndMemory() {
        MessbgeOutput.println("The memory commbnd is no longer supported.");
    }

    void commbndGC() {
        MessbgeOutput.println("The gc commbnd is no longer necessbry.");
    }

    /*
     * The next two methods bre used by this clbss bnd by EventHbndler
     * to print consistent locbtions bnd error messbges.
     */
    stbtic String locbtionString(Locbtion loc) {
        return MessbgeOutput.formbt("locbtionString",
                                    new Object [] {loc.declbringType().nbme(),
                                                   loc.method().nbme(),
                                                   new Integer (loc.lineNumber()),
                                                   Long.vblueOf(loc.codeIndex())});
    }

    void listBrebkpoints() {
        boolebn noBrebkpoints = true;

        // Print set brebkpoints
        for (EventRequestSpec spec : Env.specList.eventRequestSpecs()) {
            if (spec instbnceof BrebkpointSpec) {
                if (noBrebkpoints) {
                    noBrebkpoints = fblse;
                    MessbgeOutput.println("Brebkpoints set:");
                }
                MessbgeOutput.println("tbb", spec.toString());
            }
        }
        if (noBrebkpoints) {
            MessbgeOutput.println("No brebkpoints set.");
        }
    }


    privbte void printBrebkpointCommbndUsbge(String btForm, String inForm) {
        MessbgeOutput.println("printbrebkpointcommbndusbge",
                              new Object [] {btForm, inForm});
    }

    protected BrebkpointSpec pbrseBrebkpointSpec(StringTokenizer t,
                                             String btForm, String inForm) {
        BrebkpointSpec brebkpoint = null;
        try {
            String token = t.nextToken(":( \t\n\r");

            // We cbn't use hbsMoreTokens here becbuse it will cbuse bny lebding
            // pbren to be lost.
            String rest;
            try {
                rest = t.nextToken("").trim();
            } cbtch (NoSuchElementException e) {
                rest = null;
            }

            if ((rest != null) && rest.stbrtsWith(":")) {
                t = new StringTokenizer(rest.substring(1));
                String clbssId = token;
                String lineToken = t.nextToken();

                NumberFormbt nf = NumberFormbt.getNumberInstbnce();
                nf.setPbrseIntegerOnly(true);
                Number n = nf.pbrse(lineToken);
                int lineNumber = n.intVblue();

                if (t.hbsMoreTokens()) {
                    printBrebkpointCommbndUsbge(btForm, inForm);
                    return null;
                }
                try {
                    brebkpoint = Env.specList.crebteBrebkpoint(clbssId,
                                                               lineNumber);
                } cbtch (ClbssNotFoundException exc) {
                    MessbgeOutput.println("is not b vblid clbss nbme", clbssId);
                }
            } else {
                // Try stripping method from clbss.method token.
                int idot = token.lbstIndexOf('.');
                if ( (idot <= 0) ||                     /* No dot or dot in first chbr */
                     (idot >= token.length() - 1) ) { /* dot in lbst chbr */
                    printBrebkpointCommbndUsbge(btForm, inForm);
                    return null;
                }
                String methodNbme = token.substring(idot + 1);
                String clbssId = token.substring(0, idot);
                List<String> brgumentList = null;
                if (rest != null) {
                    if (!rest.stbrtsWith("(") || !rest.endsWith(")")) {
                        MessbgeOutput.println("Invblid method specificbtion:",
                                              methodNbme + rest);
                        printBrebkpointCommbndUsbge(btForm, inForm);
                        return null;
                    }
                    // Trim the pbrens
                    rest = rest.substring(1, rest.length() - 1);

                    brgumentList = new ArrbyList<String>();
                    t = new StringTokenizer(rest, ",");
                    while (t.hbsMoreTokens()) {
                        brgumentList.bdd(t.nextToken());
                    }
                }
                try {
                    brebkpoint = Env.specList.crebteBrebkpoint(clbssId,
                                                               methodNbme,
                                                               brgumentList);
                } cbtch (MblformedMemberNbmeException exc) {
                    MessbgeOutput.println("is not b vblid method nbme", methodNbme);
                } cbtch (ClbssNotFoundException exc) {
                    MessbgeOutput.println("is not b vblid clbss nbme", clbssId);
                }
            }
        } cbtch (Exception e) {
            printBrebkpointCommbndUsbge(btForm, inForm);
            return null;
        }
        return brebkpoint;
    }

    privbte void resolveNow(EventRequestSpec spec) {
        boolebn success = Env.specList.bddEbgerlyResolve(spec);
        if (success && !spec.isResolved()) {
            MessbgeOutput.println("Deferring.", spec.toString());
        }
    }

    void commbndStop(StringTokenizer t) {
        String btIn;
        byte suspendPolicy = EventRequest.SUSPEND_ALL;

        if (t.hbsMoreTokens()) {
            btIn = t.nextToken();
            if (btIn.equbls("go") && t.hbsMoreTokens()) {
                suspendPolicy = EventRequest.SUSPEND_NONE;
                btIn = t.nextToken();
            } else if (btIn.equbls("threbd") && t.hbsMoreTokens()) {
                suspendPolicy = EventRequest.SUSPEND_EVENT_THREAD;
                btIn = t.nextToken();
            }
        } else {
            listBrebkpoints();
            return;
        }

        BrebkpointSpec spec = pbrseBrebkpointSpec(t, "stop bt", "stop in");
        if (spec != null) {
            // Enforcement of "bt" vs. "in". The distinction is reblly
            // unnecessbry bnd we should consider not checking for this
            // (bnd mbking "bt" bnd "in" optionbl).
            if (btIn.equbls("bt") && spec.isMethodBrebkpoint()) {
                MessbgeOutput.println("Use stop bt to set b brebkpoint bt b line number");
                printBrebkpointCommbndUsbge("stop bt", "stop in");
                return;
            }
            spec.suspendPolicy = suspendPolicy;
            resolveNow(spec);
        }
    }

    void commbndClebr(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            listBrebkpoints();
            return;
        }

        BrebkpointSpec spec = pbrseBrebkpointSpec(t, "clebr", "clebr");
        if (spec != null) {
            if (Env.specList.delete(spec)) {
                MessbgeOutput.println("Removed:", spec.toString());
            } else {
                MessbgeOutput.println("Not found:", spec.toString());
            }
        }
    }

    privbte List<WbtchpointSpec> pbrseWbtchpointSpec(StringTokenizer t) {
        List<WbtchpointSpec> list = new ArrbyList<WbtchpointSpec>();
        boolebn bccess = fblse;
        boolebn modificbtion = fblse;
        int suspendPolicy = EventRequest.SUSPEND_ALL;

        String fieldNbme = t.nextToken();
        if (fieldNbme.equbls("go")) {
            suspendPolicy = EventRequest.SUSPEND_NONE;
            fieldNbme = t.nextToken();
        } else if (fieldNbme.equbls("threbd")) {
            suspendPolicy = EventRequest.SUSPEND_EVENT_THREAD;
            fieldNbme = t.nextToken();
        }
        if (fieldNbme.equbls("bccess")) {
            bccess = true;
            fieldNbme = t.nextToken();
        } else if (fieldNbme.equbls("bll")) {
            bccess = true;
            modificbtion = true;
            fieldNbme = t.nextToken();
        } else {
            modificbtion = true;
        }
        int dot = fieldNbme.lbstIndexOf('.');
        if (dot < 0) {
            MessbgeOutput.println("Clbss contbining field must be specified.");
            return list;
        }
        String clbssNbme = fieldNbme.substring(0, dot);
        fieldNbme = fieldNbme.substring(dot+1);

        try {
            WbtchpointSpec spec;
            if (bccess) {
                spec = Env.specList.crebteAccessWbtchpoint(clbssNbme,
                                                           fieldNbme);
                spec.suspendPolicy = suspendPolicy;
                list.bdd(spec);
            }
            if (modificbtion) {
                spec = Env.specList.crebteModificbtionWbtchpoint(clbssNbme,
                                                                 fieldNbme);
                spec.suspendPolicy = suspendPolicy;
                list.bdd(spec);
            }
        } cbtch (MblformedMemberNbmeException exc) {
            MessbgeOutput.println("is not b vblid field nbme", fieldNbme);
        } cbtch (ClbssNotFoundException exc) {
            MessbgeOutput.println("is not b vblid clbss nbme", clbssNbme);
        }
        return list;
    }

    void commbndWbtch(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("Field to wbtch not specified");
            return;
        }

        for (WbtchpointSpec spec : pbrseWbtchpointSpec(t)) {
            resolveNow(spec);
        }
    }

    void commbndUnwbtch(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("Field to unwbtch not specified");
            return;
        }

        for (WbtchpointSpec spec : pbrseWbtchpointSpec(t)) {
            if (Env.specList.delete(spec)) {
                MessbgeOutput.println("Removed:", spec.toString());
            } else {
                MessbgeOutput.println("Not found:", spec.toString());
            }
        }
    }

    void turnOnExitTrbce(ThrebdInfo threbdInfo, int suspendPolicy) {
        EventRequestMbnbger erm = Env.vm().eventRequestMbnbger();
        MethodExitRequest exit = erm.crebteMethodExitRequest();
        if (threbdInfo != null) {
            exit.bddThrebdFilter(threbdInfo.getThrebd());
        }
        Env.bddExcludes(exit);
        exit.setSuspendPolicy(suspendPolicy);
        exit.enbble();

    }

    stbtic String methodTrbceCommbnd = null;

    void commbndTrbce(StringTokenizer t) {
        String modif;
        int suspendPolicy = EventRequest.SUSPEND_ALL;
        ThrebdInfo threbdInfo = null;
        String goStr = " ";

        /*
         * trbce [go] methods [threbd]
         * trbce [go] method exit | exits [threbd]
         */
        if (t.hbsMoreTokens()) {
            modif = t.nextToken();
            if (modif.equbls("go")) {
                suspendPolicy = EventRequest.SUSPEND_NONE;
                goStr = " go ";
                if (t.hbsMoreTokens()) {
                    modif = t.nextToken();
                }
            } else if (modif.equbls("threbd")) {
                // this is undocumented bs it doesn't work right.
                suspendPolicy = EventRequest.SUSPEND_EVENT_THREAD;
                if (t.hbsMoreTokens()) {
                    modif = t.nextToken();
                }
            }

            if  (modif.equbls("method")) {
                String trbceCmd = null;

                if (t.hbsMoreTokens()) {
                    String modif1 = t.nextToken();
                    if (modif1.equbls("exits") || modif1.equbls("exit")) {
                        if (t.hbsMoreTokens()) {
                            threbdInfo = doGetThrebd(t.nextToken());
                        }
                        if (modif1.equbls("exit")) {
                            StbckFrbme frbme;
                            try {
                                frbme = ThrebdInfo.getCurrentThrebdInfo().getCurrentFrbme();
                            } cbtch (IncompbtibleThrebdStbteException ee) {
                                MessbgeOutput.println("Current threbd isnt suspended.");
                                return;
                            }
                            Env.setAtExitMethod(frbme.locbtion().method());
                            trbceCmd = MessbgeOutput.formbt("trbce" +
                                                    goStr + "method exit " +
                                                    "in effect for",
                                                    Env.btExitMethod().toString());
                        } else {
                            trbceCmd = MessbgeOutput.formbt("trbce" +
                                                   goStr + "method exits " +
                                                   "in effect");
                        }
                        commbndUntrbce(new StringTokenizer("methods"));
                        turnOnExitTrbce(threbdInfo, suspendPolicy);
                        methodTrbceCommbnd = trbceCmd;
                        return;
                    }
                } else {
                   MessbgeOutput.println("Cbn only trbce");
                   return;
                }
            }
            if (modif.equbls("methods")) {
                // Turn on method entry trbce
                MethodEntryRequest entry;
                EventRequestMbnbger erm = Env.vm().eventRequestMbnbger();
                if (t.hbsMoreTokens()) {
                    threbdInfo = doGetThrebd(t.nextToken());
                }
                if (threbdInfo != null) {
                    /*
                     * To keep things simple we wbnt ebch 'trbce' to cbncel
                     * previous trbces.  However in this cbse, we don't do thbt
                     * to preserve bbckwbrd compbtibility with pre JDK 6.0.
                     * IE, you cbn currently do
                     *   trbce   methods 0x21
                     *   trbce   methods 0x22
                     * bnd you will get xxx trbced just on those two threbds
                     * But this febture is kind of broken becbuse if you then do
                     *   untrbce  0x21
                     * it turns off both trbces instebd of just the one.
                     * Another bogosity is thbt if you do
                     *   trbce methods
                     *   trbce methods
                     * bnd you will get two trbces.
                     */

                    entry = erm.crebteMethodEntryRequest();
                    entry.bddThrebdFilter(threbdInfo.getThrebd());
                } else {
                    commbndUntrbce(new StringTokenizer("methods"));
                    entry = erm.crebteMethodEntryRequest();
                }
                Env.bddExcludes(entry);
                entry.setSuspendPolicy(suspendPolicy);
                entry.enbble();
                turnOnExitTrbce(threbdInfo, suspendPolicy);
                methodTrbceCommbnd = MessbgeOutput.formbt("trbce" + goStr +
                                                          "methods in effect");

                return;
            }

            MessbgeOutput.println("Cbn only trbce");
            return;
        }

        // trbce bll by itself.
        if (methodTrbceCommbnd != null) {
            MessbgeOutput.printDirectln(methodTrbceCommbnd);
        }

        // More trbce lines cbn be bdded here.
    }

    void commbndUntrbce(StringTokenizer t) {
        // untrbce
        // untrbce methods

        String modif = null;
        EventRequestMbnbger erm = Env.vm().eventRequestMbnbger();
        if (t.hbsMoreTokens()) {
            modif = t.nextToken();
        }
        if (modif == null || modif.equbls("methods")) {
            erm.deleteEventRequests(erm.methodEntryRequests());
            erm.deleteEventRequests(erm.methodExitRequests());
            Env.setAtExitMethod(null);
            methodTrbceCommbnd = null;
        }
    }

    void commbndList(StringTokenizer t) {
        StbckFrbme frbme = null;
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            MessbgeOutput.println("No threbd specified.");
            return;
        }
        try {
            frbme = threbdInfo.getCurrentFrbme();
        } cbtch (IncompbtibleThrebdStbteException e) {
            MessbgeOutput.println("Current threbd isnt suspended.");
            return;
        }

        if (frbme == null) {
            MessbgeOutput.println("No frbmes on the current cbll stbck");
            return;
        }

        Locbtion loc = frbme.locbtion();
        if (loc.method().isNbtive()) {
            MessbgeOutput.println("Current method is nbtive");
            return;
        }

        String sourceFileNbme = null;
        try {
            sourceFileNbme = loc.sourceNbme();

            ReferenceType refType = loc.declbringType();
            int lineno = loc.lineNumber();

            if (t.hbsMoreTokens()) {
                String id = t.nextToken();

                // See if token is b line number.
                try {
                    NumberFormbt nf = NumberFormbt.getNumberInstbnce();
                    nf.setPbrseIntegerOnly(true);
                    Number n = nf.pbrse(id);
                    lineno = n.intVblue();
                } cbtch (jbvb.text.PbrseException jtpe) {
                    // It isn't -- see if it's b method nbme.
                        List<Method> meths = refType.methodsByNbme(id);
                        if (meths == null || meths.size() == 0) {
                            MessbgeOutput.println("is not b vblid line number or method nbme for",
                                                  new Object [] {id, refType.nbme()});
                            return;
                        } else if (meths.size() > 1) {
                            MessbgeOutput.println("is bn bmbiguous method nbme in",
                                                  new Object [] {id, refType.nbme()});
                            return;
                        }
                        loc = meths.get(0).locbtion();
                        lineno = loc.lineNumber();
                }
            }
            int stbrtLine = Mbth.mbx(lineno - 4, 1);
            int endLine = stbrtLine + 9;
            if (lineno < 0) {
                MessbgeOutput.println("Line number informbtion not bvbilbble for");
            } else if (Env.sourceLine(loc, lineno) == null) {
                MessbgeOutput.println("is bn invblid line number for",
                                      new Object [] {new Integer (lineno),
                                                     refType.nbme()});
            } else {
                for (int i = stbrtLine; i <= endLine; i++) {
                    String sourceLine = Env.sourceLine(loc, i);
                    if (sourceLine == null) {
                        brebk;
                    }
                    if (i == lineno) {
                        MessbgeOutput.println("source line number current line bnd line",
                                              new Object [] {new Integer (i),
                                                             sourceLine});
                    } else {
                        MessbgeOutput.println("source line number bnd line",
                                              new Object [] {new Integer (i),
                                                             sourceLine});
                    }
                }
            }
        } cbtch (AbsentInformbtionException e) {
            MessbgeOutput.println("No source informbtion bvbilbble for:", loc.toString());
        } cbtch(FileNotFoundException exc) {
            MessbgeOutput.println("Source file not found:", sourceFileNbme);
        } cbtch(IOException exc) {
            MessbgeOutput.println("I/O exception occurred:", exc.toString());
        }
    }

    void commbndLines(StringTokenizer t) { // Undocumented commbnd: useful for testing
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("Specify clbss bnd method");
        } else {
            String idClbss = t.nextToken();
            String idMethod = t.hbsMoreTokens() ? t.nextToken() : null;
            try {
                ReferenceType refType = Env.getReferenceTypeFromToken(idClbss);
                if (refType != null) {
                    List<Locbtion> lines = null;
                    if (idMethod == null) {
                        lines = refType.bllLineLocbtions();
                    } else {
                        for (Method method : refType.bllMethods()) {
                            if (method.nbme().equbls(idMethod)) {
                                lines = method.bllLineLocbtions();
                            }
                        }
                        if (lines == null) {
                            MessbgeOutput.println("is not b vblid method nbme", idMethod);
                        }
                    }
                    for (Locbtion line : lines) {
                        MessbgeOutput.printDirectln(line.toString());// Specibl cbse: use printDirectln()
                    }
                } else {
                    MessbgeOutput.println("is not b vblid id or clbss nbme", idClbss);
                }
            } cbtch (AbsentInformbtionException e) {
                MessbgeOutput.println("Line number informbtion not bvbilbble for", idClbss);
            }
        }
    }

    void commbndClbsspbth(StringTokenizer t) {
        if (Env.vm() instbnceof PbthSebrchingVirtublMbchine) {
            PbthSebrchingVirtublMbchine vm = (PbthSebrchingVirtublMbchine)Env.vm();
            MessbgeOutput.println("bbse directory:", vm.bbseDirectory());
            MessbgeOutput.println("clbsspbth:", vm.clbssPbth().toString());
            MessbgeOutput.println("bootclbsspbth:", vm.bootClbssPbth().toString());
        } else {
            MessbgeOutput.println("The VM does not use pbths");
        }
    }

    /* Get or set the source file pbth list. */
    void commbndUse(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.printDirectln(Env.getSourcePbth());// Specibl cbse: use printDirectln()
        } else {
            /*
             * Tbke the rembinder of the commbnd line, minus
             * lebding or trbiling whitespbce.  Embedded
             * whitespbce is fine.
             */
            Env.setSourcePbth(t.nextToken("").trim());
        }
    }

    /* Print b stbck vbribble */
    privbte void printVbr(LocblVbribble vbr, Vblue vblue) {
        MessbgeOutput.println("expr is vblue",
                              new Object [] {vbr.nbme(),
                                             vblue == null ? "null" : vblue.toString()});
    }

    /* Print bll locbl vbribbles in current stbck frbme. */
    void commbndLocbls() {
        StbckFrbme frbme;
        ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
        if (threbdInfo == null) {
            MessbgeOutput.println("No defbult threbd specified:");
            return;
        }
        try {
            frbme = threbdInfo.getCurrentFrbme();
            if (frbme == null) {
                throw new AbsentInformbtionException();
            }
            List<LocblVbribble> vbrs = frbme.visibleVbribbles();

            if (vbrs.size() == 0) {
                MessbgeOutput.println("No locbl vbribbles");
                return;
            }
            Mbp<LocblVbribble, Vblue> vblues = frbme.getVblues(vbrs);

            MessbgeOutput.println("Method brguments:");
            for (LocblVbribble vbr : vbrs) {
                if (vbr.isArgument()) {
                    Vblue vbl = vblues.get(vbr);
                    printVbr(vbr, vbl);
                }
            }
            MessbgeOutput.println("Locbl vbribbles:");
            for (LocblVbribble vbr : vbrs) {
                if (!vbr.isArgument()) {
                    Vblue vbl = vblues.get(vbr);
                    printVbr(vbr, vbl);
                }
            }
        } cbtch (AbsentInformbtionException bie) {
            MessbgeOutput.println("Locbl vbribble informbtion not bvbilbble.");
        } cbtch (IncompbtibleThrebdStbteException exc) {
            MessbgeOutput.println("Current threbd isnt suspended.");
        }
    }

    privbte void dump(ObjectReference obj, ReferenceType refType,
                      ReferenceType refTypeBbse) {
        for (Field field : refType.fields()) {
            StringBuilder sb = new StringBuilder();
            sb.bppend("    ");
            if (!refType.equbls(refTypeBbse)) {
                sb.bppend(refType.nbme());
                sb.bppend(".");
            }
            sb.bppend(field.nbme());
            sb.bppend(MessbgeOutput.formbt("colon spbce"));
            sb.bppend(obj.getVblue(field));
            MessbgeOutput.printDirectln(sb.toString()); // Specibl cbse: use printDirectln()
        }
        if (refType instbnceof ClbssType) {
            ClbssType sup = ((ClbssType)refType).superclbss();
            if (sup != null) {
                dump(obj, sup, refTypeBbse);
            }
        } else if (refType instbnceof InterfbceType) {
            for (InterfbceType sup : ((InterfbceType)refType).superinterfbces()) {
                dump(obj, sup, refTypeBbse);
            }
        } else {
            /* else refType is bn instbnceof ArrbyType */
            if (obj instbnceof ArrbyReference) {
                for (Iterbtor<Vblue> it = ((ArrbyReference)obj).getVblues().iterbtor();
                     it.hbsNext(); ) {
                    MessbgeOutput.printDirect(it.next().toString());// Specibl cbse: use printDirect()
                    if (it.hbsNext()) {
                        MessbgeOutput.printDirect(", ");// Specibl cbse: use printDirect()
                    }
                }
                MessbgeOutput.println();
            }
        }
    }

    /* Print b specified reference.
     */
    void doPrint(StringTokenizer t, boolebn dumpObject) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No objects specified.");
            return;
        }

        while (t.hbsMoreTokens()) {
            String expr = t.nextToken("");
            Vblue vbl = evblubte(expr);
            if (vbl == null) {
                MessbgeOutput.println("expr is null", expr.toString());
            } else if (dumpObject && (vbl instbnceof ObjectReference) &&
                       !(vbl instbnceof StringReference)) {
                ObjectReference obj = (ObjectReference)vbl;
                ReferenceType refType = obj.referenceType();
                MessbgeOutput.println("expr is vblue",
                                      new Object [] {expr.toString(),
                                                     MessbgeOutput.formbt("grouping begin chbrbcter")});
                dump(obj, refType, refType);
                MessbgeOutput.println("grouping end chbrbcter");
            } else {
                  String strVbl = getStringVblue();
                  if (strVbl != null) {
                     MessbgeOutput.println("expr is vblue", new Object [] {expr.toString(),
                                                                      strVbl});
                   }
            }
        }
    }

    void commbndPrint(finbl StringTokenizer t, finbl boolebn dumpObject) {
        new AsyncExecution() {
                @Override
                void bction() {
                    doPrint(t, dumpObject);
                }
            };
    }

    void commbndSet(finbl StringTokenizer t) {
        String bll = t.nextToken("");

        /*
         * Bbre bones error checking.
         */
        if (bll.indexOf('=') == -1) {
            MessbgeOutput.println("Invblid bssignment syntbx");
            MessbgeOutput.printPrompt();
            return;
        }

        /*
         * The set commbnd is reblly just syntbctic sugbr. Pbss it on to the
         * print commbnd.
         */
        commbndPrint(new StringTokenizer(bll), fblse);
    }

    void doLock(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No object specified.");
            return;
        }

        String expr = t.nextToken("");
        Vblue vbl = evblubte(expr);

        try {
            if ((vbl != null) && (vbl instbnceof ObjectReference)) {
                ObjectReference object = (ObjectReference)vbl;
                String strVbl = getStringVblue();
                if (strVbl != null) {
                    MessbgeOutput.println("Monitor informbtion for expr",
                                      new Object [] {expr.trim(),
                                                     strVbl});
                }
                ThrebdReference owner = object.owningThrebd();
                if (owner == null) {
                    MessbgeOutput.println("Not owned");
                } else {
                    MessbgeOutput.println("Owned by:",
                                          new Object [] {owner.nbme(),
                                                         new Integer (object.entryCount())});
                }
                List<ThrebdReference> wbiters = object.wbitingThrebds();
                if (wbiters.size() == 0) {
                    MessbgeOutput.println("No wbiters");
                } else {
                    for (ThrebdReference wbiter : wbiters) {
                        MessbgeOutput.println("Wbiting threbd:", wbiter.nbme());
                    }
                }
            } else {
                MessbgeOutput.println("Expression must evblubte to bn object");
            }
        } cbtch (IncompbtibleThrebdStbteException e) {
            MessbgeOutput.println("Threbds must be suspended");
        }
    }

    void commbndLock(finbl StringTokenizer t) {
        new AsyncExecution() {
                @Override
                void bction() {
                    doLock(t);
                }
            };
    }

    privbte void printThrebdLockInfo(ThrebdInfo threbdInfo) {
        ThrebdReference threbd = threbdInfo.getThrebd();
        try {
            MessbgeOutput.println("Monitor informbtion for threbd", threbd.nbme());
            List<ObjectReference> owned = threbd.ownedMonitors();
            if (owned.size() == 0) {
                MessbgeOutput.println("No monitors owned");
            } else {
                for (ObjectReference monitor : owned) {
                    MessbgeOutput.println("Owned monitor:", monitor.toString());
                }
            }
            ObjectReference wbiting = threbd.currentContendedMonitor();
            if (wbiting == null) {
                MessbgeOutput.println("Not wbiting for b monitor");
            } else {
                MessbgeOutput.println("Wbiting for monitor:", wbiting.toString());
            }
        } cbtch (IncompbtibleThrebdStbteException e) {
            MessbgeOutput.println("Threbds must be suspended");
        }
    }

    void commbndThrebdlocks(finbl StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            ThrebdInfo threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
            if (threbdInfo == null) {
                MessbgeOutput.println("Current threbd not set.");
            } else {
                printThrebdLockInfo(threbdInfo);
            }
            return;
        }
        String token = t.nextToken();
        if (token.toLowerCbse().equbls("bll")) {
            for (ThrebdInfo threbdInfo : ThrebdInfo.threbds()) {
                printThrebdLockInfo(threbdInfo);
            }
        } else {
            ThrebdInfo threbdInfo = doGetThrebd(token);
            if (threbdInfo != null) {
                ThrebdInfo.setCurrentThrebdInfo(threbdInfo);
                printThrebdLockInfo(threbdInfo);
            }
        }
    }

    void doDisbbleGC(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No object specified.");
            return;
        }

        String expr = t.nextToken("");
        Vblue vbl = evblubte(expr);
        if ((vbl != null) && (vbl instbnceof ObjectReference)) {
            ObjectReference object = (ObjectReference)vbl;
            object.disbbleCollection();
            String strVbl = getStringVblue();
            if (strVbl != null) {
                 MessbgeOutput.println("GC Disbbled for", strVbl);
            }
        } else {
            MessbgeOutput.println("Expression must evblubte to bn object");
        }
    }

    void commbndDisbbleGC(finbl StringTokenizer t) {
        new AsyncExecution() {
                @Override
                void bction() {
                    doDisbbleGC(t);
                }
            };
    }

    void doEnbbleGC(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No object specified.");
            return;
        }

        String expr = t.nextToken("");
        Vblue vbl = evblubte(expr);
        if ((vbl != null) && (vbl instbnceof ObjectReference)) {
            ObjectReference object = (ObjectReference)vbl;
            object.enbbleCollection();
            String strVbl = getStringVblue();
            if (strVbl != null) {
                 MessbgeOutput.println("GC Enbbled for", strVbl);
            }
        } else {
            MessbgeOutput.println("Expression must evblubte to bn object");
        }
    }

    void commbndEnbbleGC(finbl StringTokenizer t) {
        new AsyncExecution() {
                @Override
                void bction() {
                    doEnbbleGC(t);
                }
            };
    }

    void doSbve(StringTokenizer t) {// Undocumented commbnd: useful for testing.
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No sbve index specified.");
            return;
        }

        String key = t.nextToken();

        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No expression specified.");
            return;
        }
        String expr = t.nextToken("");
        Vblue vbl = evblubte(expr);
        if (vbl != null) {
            Env.setSbvedVblue(key, vbl);
            String strVbl = getStringVblue();
            if (strVbl != null) {
                 MessbgeOutput.println("sbved", strVbl);
            }
        } else {
            MessbgeOutput.println("Expression cbnnot be void");
        }
    }

    void commbndSbve(finbl StringTokenizer t) { // Undocumented commbnd: useful for testing.
        if (!t.hbsMoreTokens()) {
            Set<String> keys = Env.getSbveKeys();
            if (keys.isEmpty()) {
                MessbgeOutput.println("No sbved vblues");
                return;
            }
            for (String key : keys) {
                Vblue vblue = Env.getSbvedVblue(key);
                if ((vblue instbnceof ObjectReference) &&
                    ((ObjectReference)vblue).isCollected()) {
                    MessbgeOutput.println("expr is vblue <collected>",
                                          new Object [] {key, vblue.toString()});
                } else {
                    if (vblue == null){
                        MessbgeOutput.println("expr is null", key);
                    } else {
                        MessbgeOutput.println("expr is vblue",
                                              new Object [] {key, vblue.toString()});
                    }
                }
            }
        } else {
            new AsyncExecution() {
                    @Override
                    void bction() {
                        doSbve(t);
                    }
                };
        }

    }

   void commbndBytecodes(finbl StringTokenizer t) { // Undocumented commbnd: useful for testing.
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No clbss specified.");
            return;
        }
        String clbssNbme = t.nextToken();

        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No method specified.");
            return;
        }
        // Overlobding is not hbndled here.
        String methodNbme = t.nextToken();

        List<ReferenceType> clbsses = Env.vm().clbssesByNbme(clbssNbme);
        // TO DO: hbndle multiple clbsses found
        if (clbsses.size() == 0) {
            if (clbssNbme.indexOf('.') < 0) {
                MessbgeOutput.println("not found (try the full nbme)", clbssNbme);
            } else {
                MessbgeOutput.println("not found", clbssNbme);
            }
            return;
        }

        ReferenceType rt = clbsses.get(0);
        if (!(rt instbnceof ClbssType)) {
            MessbgeOutput.println("not b clbss", clbssNbme);
            return;
        }

        byte[] bytecodes = null;
        for (Method method : rt.methodsByNbme(methodNbme)) {
            if (!method.isAbstrbct()) {
                bytecodes = method.bytecodes();
                brebk;
            }
        }

        StringBuilder line = new StringBuilder(80);
        line.bppend("0000: ");
        for (int i = 0; i < bytecodes.length; i++) {
            if ((i > 0) && (i % 16 == 0)) {
                MessbgeOutput.printDirectln(line.toString());// Specibl cbse: use printDirectln()
                line.setLength(0);
                line.bppend(String.vblueOf(i));
                line.bppend(": ");
                int len = line.length();
                for (int j = 0; j < 6 - len; j++) {
                    line.insert(0, '0');
                }
            }
            int vbl = 0xff & bytecodes[i];
            String str = Integer.toHexString(vbl);
            if (str.length() == 1) {
                line.bppend('0');
            }
            line.bppend(str);
            line.bppend(' ');
        }
        if (line.length() > 6) {
            MessbgeOutput.printDirectln(line.toString());// Specibl cbse: use printDirectln()
        }
    }

    void commbndExclude(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.printDirectln(Env.excludesString());// Specibl cbse: use printDirectln()
        } else {
            String rest = t.nextToken("");
            if (rest.equbls("none")) {
                rest = "";
            }
            Env.setExcludes(rest);
        }
    }

    void commbndRedefine(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("Specify clbsses to redefine");
        } else {
            String clbssNbme = t.nextToken();
            List<ReferenceType> clbsses = Env.vm().clbssesByNbme(clbssNbme);
            if (clbsses.size() == 0) {
                MessbgeOutput.println("No clbss nbmed", clbssNbme);
                return;
            }
            if (clbsses.size() > 1) {
                MessbgeOutput.println("More thbn one clbss nbmed", clbssNbme);
                return;
            }
            Env.setSourcePbth(Env.getSourcePbth());
            ReferenceType refType = clbsses.get(0);
            if (!t.hbsMoreTokens()) {
                MessbgeOutput.println("Specify file nbme for clbss", clbssNbme);
                return;
            }
            String fileNbme = t.nextToken();
            File phyl = new File(fileNbme);
            byte[] bytes = new byte[(int)phyl.length()];
            try {
                InputStrebm in = new FileInputStrebm(phyl);
                in.rebd(bytes);
                in.close();
            } cbtch (Exception exc) {
                MessbgeOutput.println("Error rebding file",
                             new Object [] {fileNbme, exc.toString()});
                return;
            }
            Mbp<ReferenceType, byte[]> mbp
                = new HbshMbp<ReferenceType, byte[]>();
            mbp.put(refType, bytes);
            try {
                Env.vm().redefineClbsses(mbp);
            } cbtch (Throwbble exc) {
                MessbgeOutput.println("Error redefining clbss to file",
                             new Object [] {clbssNbme,
                                            fileNbme,
                                            exc});
            }
        }
    }

    void commbndPopFrbmes(StringTokenizer t, boolebn reenter) {
        ThrebdInfo threbdInfo;

        if (t.hbsMoreTokens()) {
            String token = t.nextToken();
            threbdInfo = doGetThrebd(token);
            if (threbdInfo == null) {
                return;
            }
        } else {
            threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
            if (threbdInfo == null) {
                MessbgeOutput.println("No threbd specified.");
                return;
            }
        }

        try {
            StbckFrbme frbme = threbdInfo.getCurrentFrbme();
            threbdInfo.getThrebd().popFrbmes(frbme);
            threbdInfo = ThrebdInfo.getCurrentThrebdInfo();
            ThrebdInfo.setCurrentThrebdInfo(threbdInfo);
            if (reenter) {
                commbndStepi();
            }
        } cbtch (Throwbble exc) {
            MessbgeOutput.println("Error popping frbme", exc.toString());
        }
    }

    void commbndExtension(StringTokenizer t) {
        if (!t.hbsMoreTokens()) {
            MessbgeOutput.println("No clbss specified.");
            return;
        }

        String idClbss = t.nextToken();
        ReferenceType cls = Env.getReferenceTypeFromToken(idClbss);
        String extension = null;
        if (cls != null) {
            try {
                extension = cls.sourceDebugExtension();
                MessbgeOutput.println("sourcedebugextension", extension);
            } cbtch (AbsentInformbtionException e) {
                MessbgeOutput.println("No sourcedebugextension specified");
            }
        } else {
            MessbgeOutput.println("is not b vblid id or clbss nbme", idClbss);
        }
    }

    void commbndVersion(String debuggerNbme,
                        VirtublMbchineMbnbger vmm) {
        MessbgeOutput.println("minus version",
                              new Object [] { debuggerNbme,
                                              vmm.mbjorInterfbceVersion(),
                                              vmm.minorInterfbceVersion(),
                                                  System.getProperty("jbvb.version")});
        if (Env.connection() != null) {
            try {
                MessbgeOutput.printDirectln(Env.vm().description());// Specibl cbse: use printDirectln()
            } cbtch (VMNotConnectedException e) {
                MessbgeOutput.println("No VM connected");
            }
        }
    }
}
