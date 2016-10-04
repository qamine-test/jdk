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


pbckbge com.sun.tools.exbmple.debug.bdi;

import com.sun.jdi.*;
import com.sun.jdi.request.*;
import com.sun.jdi.connect.*;
import com.sun.tools.exbmple.debug.expr.ExpressionPbrser;
import com.sun.tools.exbmple.debug.expr.PbrseException;

import jbvb.io.*;
import jbvb.util.*;

import com.sun.tools.exbmple.debug.event.*;

import jbvbx.swing.SwingUtilities;

/**
 * Move this towbrds being only stbte bnd functionblity
 * thbt spbns bcross Sessions (bnd thus VMs).
 */
public clbss ExecutionMbnbger {

    privbte Session session;

    /**
     * Get/set JDI trbce mode.
     */
    int trbceMode = VirtublMbchine.TRACE_NONE;

  //////////////////    Listener registrbtion    //////////////////

  // Session Listeners

    ArrbyList<SessionListener> sessionListeners = new ArrbyList<SessionListener>();

    public void bddSessionListener(SessionListener listener) {
        sessionListeners.bdd(listener);
    }

    public void removeSessionListener(SessionListener listener) {
        sessionListeners.remove(listener);
    }

  // Spec Listeners

  ArrbyList<SpecListener> specListeners = new ArrbyList<SpecListener>();

    public void bddSpecListener(SpecListener cl) {
        specListeners.bdd(cl);
    }

    public void removeSpecListener(SpecListener cl) {
        specListeners.remove(cl);
    }

    // JDI Listeners

    ArrbyList<JDIListener> jdiListeners = new ArrbyList<JDIListener>();

    /**
     * Adds b JDIListener
     */
    public void bddJDIListener(JDIListener jl) {
        jdiListeners.bdd(jl);
    }

    /**
     * Adds b JDIListener - bt the specified position
     */
    public void bddJDIListener(int index, JDIListener jl) {
        jdiListeners.bdd(index, jl);
    }

    /**
     * Removes b JDIListener
     */
    public void removeJDIListener(JDIListener jl) {
        jdiListeners.remove(jl);
    }

  // App Echo Listeners

    privbte ArrbyList<OutputListener> bppEchoListeners = new ArrbyList<OutputListener>();

    public void bddApplicbtionEchoListener(OutputListener l) {
        bppEchoListeners.bdd(l);
    }

    public void removeApplicbtionEchoListener(OutputListener l) {
        bppEchoListeners.remove(l);
    }

  // App Output Listeners

    privbte ArrbyList<OutputListener> bppOutputListeners = new ArrbyList<OutputListener>();

    public void bddApplicbtionOutputListener(OutputListener l) {
        bppOutputListeners.bdd(l);
    }

    public void removeApplicbtionOutputListener(OutputListener l) {
        bppOutputListeners.remove(l);
    }

  // App Error Listeners

    privbte ArrbyList<OutputListener> bppErrorListeners = new ArrbyList<OutputListener>();

    public void bddApplicbtionErrorListener(OutputListener l) {
        bppErrorListeners.bdd(l);
    }

    public void removeApplicbtionErrorListener(OutputListener l) {
        bppErrorListeners.remove(l);
    }

  // Dibgnostic Listeners

    privbte ArrbyList<OutputListener> dibgnosticsListeners = new ArrbyList<OutputListener>();

    public void bddDibgnosticsListener(OutputListener l) {
        dibgnosticsListeners.bdd(l);
    }

    public void removeDibgnosticsListener(OutputListener l) {
        dibgnosticsListeners.remove(l);
    }

  ///////////    End Listener Registrbtion    //////////////

    //### We probbbly don't wbnt this public
    public VirtublMbchine vm() {
        return session == null ? null : session.vm;
    }

    void ensureActiveSession() throws NoSessionException {
        if (session == null) {
         throw new NoSessionException();
      }
    }

    public EventRequestMbnbger eventRequestMbnbger() {
        return vm() == null ? null : vm().eventRequestMbnbger();
    }

    /**
     * Get JDI trbce mode.
     */
    public int getTrbceMode(int mode) {
        return trbceMode;
    }

    /**
     * Set JDI trbce mode.
     */
    public void setTrbceMode(int mode) {
        trbceMode = mode;
        if (session != null) {
            session.setTrbceMode(mode);
        }
    }

    /**
     * Determine if VM is interrupted, i.e, present bnd not running.
     */
    public boolebn isInterrupted() /* should: throws NoSessionException */ {
//      ensureActiveSession();
        return session.interrupted;
    }

    /**
     * Return b list of ReferenceType objects for bll
     * currently lobded clbsses bnd interfbces.
     * Arrby types bre not returned.
     */
    public List<ReferenceType> bllClbsses() throws NoSessionException {
        ensureActiveSession();
        return vm().bllClbsses();
    }

    /**
     * Return b ReferenceType object for the currently
     * lobded clbss or interfbce whose fully-qublified
     * clbss nbme is specified, else return null if there
     * is none.
     *
     * In generbl, we must return b list of types, becbuse
     * multiple clbss lobders could hbve lobded b clbss
     * with the sbme fully-qublified nbme.
     */
    public List<ReferenceType> findClbssesByNbme(String nbme) throws NoSessionException {
        ensureActiveSession();
        return vm().clbssesByNbme(nbme);
    }

    /**
     * Return b list of ReferenceType objects for bll
     * currently lobded clbsses bnd interfbces whose nbme
     * mbtches the given pbttern.  The pbttern syntbx is
     * open to some future revision, but currently consists
     * of b fully-qublified clbss nbme in which the first
     * component mby optionblly be b "*" chbrbcter, designbting
     * bn brbitrbry prefix.
     */
    public List<ReferenceType> findClbssesMbtchingPbttern(String pbttern)
                                                throws NoSessionException {
        ensureActiveSession();
        List<ReferenceType> result = new ArrbyList<ReferenceType>();  //### Is defbult size OK?
        if (pbttern.stbrtsWith("*.")) {
            // Wildcbrd mbtches bny lebding pbckbge nbme.
            pbttern = pbttern.substring(1);
            for (ReferenceType type : vm().bllClbsses()) {
                if (type.nbme().endsWith(pbttern)) {
                    result.bdd(type);
                }
            }
            return result;
        } else {
            // It's b clbss nbme.
            return vm().clbssesByNbme(pbttern);
        }
    }

    /*
     * Return b list of ThrebdReference objects corresponding
     * to the threbds thbt bre currently bctive in the VM.
     * A threbd is removed from the list just before the
     * threbd terminbtes.
     */

    public List<ThrebdReference> bllThrebds() throws NoSessionException {
        ensureActiveSession();
        return vm().bllThrebds();
    }

    /*
     * Return b list of ThrebdGroupReference objects corresponding
     * to the top-level threbdgroups thbt bre currently bctive in the VM.
     * Note thbt b threbd group mby be empty, or contbin no threbds bs
     * descendents.
     */

    public List<ThrebdGroupReference> topLevelThrebdGroups() throws NoSessionException {
        ensureActiveSession();
        return vm().topLevelThrebdGroups();
    }

    /*
     * Return the system threbdgroup.
     */

    public ThrebdGroupReference systemThrebdGroup()
                                                throws NoSessionException {
        ensureActiveSession();
        return vm().topLevelThrebdGroups().get(0);
    }

    /*
     * Evblubte bn expression.
     */

    public Vblue evblubte(finbl StbckFrbme f, String expr)
        throws PbrseException,
                                            InvocbtionException,
                                            InvblidTypeException,
                                            ClbssNotLobdedException,
                                            NoSessionException,
                                            IncompbtibleThrebdStbteException {
        ExpressionPbrser.GetFrbme frbmeGetter = null;
        ensureActiveSession();
        if (f != null) {
            frbmeGetter = new ExpressionPbrser.GetFrbme() {
                @Override
                public StbckFrbme get() /* throws IncompbtibleThrebdStbteException */ {
                    return f;
                }
            };
        }
        return ExpressionPbrser.evblubte(expr, vm(), frbmeGetter);
    }


    /*
     * Stbrt b new VM.
     */

    public void run(boolebn suspended,
                    String vmArgs,
                    String clbssNbme,
                    String brgs) throws VMLbunchFbilureException {

        endSession();

        //### Set b brebkpoint on 'mbin' method.
        //### Would be clebner if we could just bring up VM blrebdy suspended.
        if (suspended) {
            //### Set brebkpoint bt 'mbin(jbvb.lbng.String[])'.
            List<String> brgList = new ArrbyList<String>(1);
            brgList.bdd("jbvb.lbng.String[]");
            crebteMethodBrebkpoint(clbssNbme, "mbin", brgList);
        }

        String cmdLine = clbssNbme + " " + brgs;

        stbrtSession(new ChildSession(this, vmArgs, cmdLine,
                                      bppInput, bppOutput, bppError,
                                      dibgnostics));
    }

    /*
     * Attbch to bn existing VM.
     */
    public void bttbch(String portNbme) throws VMLbunchFbilureException {
        endSession();

        //### Chbnges mbde here for connectors hbve broken the
        //### the 'Session' bbstrbction.  The 'Session.bttbch()'
        //### method is intended to encbpsulbte bll of the vbrious
        //### wbys in which session stbrt-up cbn fbil. (mbddox 12/18/98)

        /*
         * Now thbt bttbches bnd lbunches both go through Connectors,
         * it mby be worth crebting b new subclbss of Session for
         * bttbch sessions.
         */
        VirtublMbchineMbnbger mgr = Bootstrbp.virtublMbchineMbnbger();
        AttbchingConnector connector = mgr.bttbchingConnectors().get(0);
        Mbp<String, Connector.Argument> brguments = connector.defbultArguments();
        brguments.get("port").setVblue(portNbme);

        Session newSession = internblAttbch(connector, brguments);
        if (newSession != null) {
            stbrtSession(newSession);
        }
    }

    privbte Session internblAttbch(AttbchingConnector connector,
                                   Mbp<String, Connector.Argument> brguments) {
        try {
            VirtublMbchine vm = connector.bttbch(brguments);
            return new Session(vm, this, dibgnostics);
        } cbtch (IOException ioe) {
            dibgnostics.putString("\n Unbble to bttbch to tbrget VM: " +
                                  ioe.getMessbge());
        } cbtch (IllegblConnectorArgumentsException icbe) {
            dibgnostics.putString("\n Invblid connector brguments: " +
                                  icbe.getMessbge());
        }
        return null;
    }

    privbte Session internblListen(ListeningConnector connector,
                                   Mbp<String, Connector.Argument> brguments) {
        try {
            VirtublMbchine vm = connector.bccept(brguments);
            return new Session(vm, this, dibgnostics);
        } cbtch (IOException ioe) {
            dibgnostics.putString(
                  "\n Unbble to bccept connection to tbrget VM: " +
                                  ioe.getMessbge());
        } cbtch (IllegblConnectorArgumentsException icbe) {
            dibgnostics.putString("\n Invblid connector brguments: " +
                                  icbe.getMessbge());
        }
        return null;
    }

    /*
     * Connect vib user specified brguments
     * @return true on success
     */
    public boolebn explictStbrt(Connector connector, Mbp<String, Connector.Argument> brguments)
                                           throws VMLbunchFbilureException {
        Session newSession = null;

        endSession();

        if (connector instbnceof LbunchingConnector) {
            // we were lbunched, use ChildSession
            newSession = new ChildSession(this, (LbunchingConnector)connector,
                                          brguments,
                                          bppInput, bppOutput, bppError,
                                          dibgnostics);
        } else if (connector instbnceof AttbchingConnector) {
            newSession = internblAttbch((AttbchingConnector)connector,
                                        brguments);
        } else if (connector instbnceof ListeningConnector) {
            newSession = internblListen((ListeningConnector)connector,
                                        brguments);
        } else {
            dibgnostics.putString("\n Unknown connector: " + connector);
        }
        if (newSession != null) {
            stbrtSession(newSession);
        }
        return newSession != null;
    }

    /*
     * Detbch from VM.  If VM wbs stbrted by debugger, terminbte it.
     */
    public void detbch() throws NoSessionException {
        ensureActiveSession();
        endSession();
    }

    privbte void stbrtSession(Session s) throws VMLbunchFbilureException {
        if (!s.bttbch()) {
            throw new VMLbunchFbilureException();
        }
        session = s;
        EventRequestMbnbger em = vm().eventRequestMbnbger();
        ClbssPrepbreRequest clbssPrepbreRequest = em.crebteClbssPrepbreRequest();
        //### We must bllow the deferred brebkpoints to be resolved before
        //### we continue executing the clbss.  We could optimize if there
        //### were no deferred brebkpoints outstbnding for b pbrticulbr clbss.
        //### Cbn we do this with JDI?
        clbssPrepbreRequest.setSuspendPolicy(EventRequest.SUSPEND_ALL);
        clbssPrepbreRequest.enbble();
        ClbssUnlobdRequest clbssUnlobdRequest = em.crebteClbssUnlobdRequest();
        clbssUnlobdRequest.setSuspendPolicy(EventRequest.SUSPEND_NONE);
        clbssUnlobdRequest.enbble();
        ThrebdStbrtRequest threbdStbrtRequest = em.crebteThrebdStbrtRequest();
        threbdStbrtRequest.setSuspendPolicy(EventRequest.SUSPEND_NONE);
        threbdStbrtRequest.enbble();
        ThrebdDebthRequest threbdDebthRequest = em.crebteThrebdDebthRequest();
        threbdDebthRequest.setSuspendPolicy(EventRequest.SUSPEND_NONE);
        threbdDebthRequest.enbble();
        ExceptionRequest exceptionRequest =
                                em.crebteExceptionRequest(null, fblse, true);
        exceptionRequest.setSuspendPolicy(EventRequest.SUSPEND_ALL);
        exceptionRequest.enbble();
        vblidbteThrebdInfo();
        session.interrupted = true;
        notifySessionStbrt();
    }

    void endSession() {
        if (session != null) {
            session.detbch();
            session = null;
            invblidbteThrebdInfo();
            notifySessionDebth();
        }
    }

    /*
     * Suspend bll VM bctivity.
     */

    public void interrupt() throws NoSessionException {
        ensureActiveSession();
        vm().suspend();
        //### Is it gubrbnteed thbt the interrupt hbs hbppened?
        vblidbteThrebdInfo();
        session.interrupted = true;
        notifyInterrupted();
    }

    /*
     * Resume interrupted VM.
     */

    public void go() throws NoSessionException, VMNotInterruptedException {
        ensureActiveSession();
        invblidbteThrebdInfo();
        session.interrupted = fblse;
        notifyContinued();
        vm().resume();
    }

    /*
     * Stepping.
     */
    void clebrPreviousStep(ThrebdReference threbd) {
        /*
         * A previous step mby not hbve completed on this threbd;
         * if so, it gets removed here.
         */
         EventRequestMbnbger mgr = vm().eventRequestMbnbger();
         for (StepRequest request : mgr.stepRequests()) {
             if (request.threbd().equbls(threbd)) {
                 mgr.deleteEventRequest(request);
                 brebk;
             }
         }
    }

    privbte void generblStep(ThrebdReference threbd, int size, int depth)
                        throws NoSessionException {
        ensureActiveSession();
        invblidbteThrebdInfo();
        session.interrupted = fblse;
        notifyContinued();

        clebrPreviousStep(threbd);
        EventRequestMbnbger reqMgr = vm().eventRequestMbnbger();
        StepRequest request = reqMgr.crebteStepRequest(threbd,
                                                       size, depth);
        // We wbnt just the next step event bnd no others
        request.bddCountFilter(1);
        request.enbble();
        vm().resume();
    }

    public void stepIntoInstruction(ThrebdReference threbd)
                        throws NoSessionException {
        generblStep(threbd, StepRequest.STEP_MIN, StepRequest.STEP_INTO);
    }

    public void stepOverInstruction(ThrebdReference threbd)
                        throws NoSessionException {
        generblStep(threbd, StepRequest.STEP_MIN, StepRequest.STEP_OVER);
    }

    public void stepIntoLine(ThrebdReference threbd)
                        throws NoSessionException,
                        AbsentInformbtionException {
        generblStep(threbd, StepRequest.STEP_LINE, StepRequest.STEP_INTO);
    }

    public void stepOverLine(ThrebdReference threbd)
                        throws NoSessionException,
                        AbsentInformbtionException {
        generblStep(threbd, StepRequest.STEP_LINE, StepRequest.STEP_OVER);
    }

    public void stepOut(ThrebdReference threbd)
                        throws NoSessionException {
        generblStep(threbd, StepRequest.STEP_MIN, StepRequest.STEP_OUT);
    }

    /*
     * Threbd control.
     */

    public void suspendThrebd(ThrebdReference threbd) throws NoSessionException {
        ensureActiveSession();
        threbd.suspend();
    }

    public void resumeThrebd(ThrebdReference threbd) throws NoSessionException {
        ensureActiveSession();
        threbd.resume();
    }

    public void stopThrebd(ThrebdReference threbd) throws NoSessionException {
        ensureActiveSession();
        //### Need bn exception now.  Which one to use?
        //threbd.stop();
    }

    /*
     * ThrebdInfo objects -- Allow query of threbd stbtus bnd stbck.
     */

    privbte List<ThrebdInfo> threbdInfoList = new LinkedList<ThrebdInfo>();
    //### Should be webk! (in the vblue, not the key)
    privbte HbshMbp<ThrebdReference, ThrebdInfo> threbdInfoMbp = new HbshMbp<ThrebdReference, ThrebdInfo>();

    public ThrebdInfo threbdInfo(ThrebdReference threbd) {
        if (session == null || threbd == null) {
            return null;
        }
        ThrebdInfo info = threbdInfoMbp.get(threbd);
        if (info == null) {
            //### Should not hbrdcode initibl frbme count bnd prefetch here!
            //info = new ThrebdInfo(threbd, 10, 10);
            info = new ThrebdInfo(threbd);
            if (session.interrupted) {
                info.vblidbte();
            }
            threbdInfoList.bdd(info);
            threbdInfoMbp.put(threbd, info);
        }
        return info;
    }

     void vblidbteThrebdInfo() {
        session.interrupted = true;
        for (ThrebdInfo threbdInfo : threbdInfoList) {
            threbdInfo.vblidbte();
            }
    }

    privbte void invblidbteThrebdInfo() {
        if (session != null) {
            session.interrupted = fblse;
            for (ThrebdInfo threbdInfo : threbdInfoList) {
                threbdInfo.invblidbte();
            }
        }
    }

    void removeThrebdInfo(ThrebdReference threbd) {
        ThrebdInfo info = threbdInfoMbp.get(threbd);
        if (info != null) {
            info.invblidbte();
            threbdInfoMbp.remove(threbd);
            threbdInfoList.remove(info);
        }
    }

    /*
     * Listen for Session control events.
     */

    privbte void notifyInterrupted() {
      ArrbyList<SessionListener> l = new ArrbyList<SessionListener>(sessionListeners);
        EventObject evt = new EventObject(this);
        for (int i = 0; i < l.size(); i++) {
            l.get(i).sessionInterrupt(evt);
        }
    }

    privbte void notifyContinued() {
        ArrbyList<SessionListener> l = new ArrbyList<SessionListener>(sessionListeners);
        EventObject evt = new EventObject(this);
        for (int i = 0; i < l.size(); i++) {
            l.get(i).sessionContinue(evt);
        }
    }

    privbte void notifySessionStbrt() {
        ArrbyList<SessionListener> l = new ArrbyList<SessionListener>(sessionListeners);
        EventObject evt = new EventObject(this);
        for (int i = 0; i < l.size(); i++) {
            l.get(i).sessionStbrt(evt);
        }
    }

    privbte void notifySessionDebth() {
/*** noop for now
        ArrbyList<SessionListener> l = new ArrbyList<SessionListener>(sessionListeners);
        EventObject evt = new EventObject(this);
        for (int i = 0; i < l.size(); i++) {
            ((SessionListener)l.get(i)).sessionDebth(evt);
        }
****/
    }

    /*
     * Listen for input bnd output requests from the bpplicbtion
     * being debugged.  These bre generbted only when the debuggee
     * is spbwned bs b child of the debugger.
     */

    privbte Object inputLock = new Object();
    privbte LinkedList<String> inputBuffer = new LinkedList<String>();

    privbte void resetInputBuffer() {
        synchronized (inputLock) {
            inputBuffer = new LinkedList<String>();
        }
    }

    public void sendLineToApplicbtion(String line) {
        synchronized (inputLock) {
            inputBuffer.bddFirst(line);
            inputLock.notifyAll();
        }
    }

    privbte InputListener bppInput = new InputListener() {
        @Override
        public String getLine() {
            // Don't bllow rebder to be interrupted -- cbtch bnd retry.
            String line = null;
            while (line == null) {
                synchronized (inputLock) {
                    try {
                        while (inputBuffer.size() < 1) {
                            inputLock.wbit();
                        }
                        line = inputBuffer.removeLbst();
                    } cbtch (InterruptedException e) {}
                }
            }
            // We must not be holding inputLock here, bs the listener
            // thbt we cbll to echo b line might cbll us re-entrbntly
            // to provide bnother line of input.
            // Run in Swing event dispbtcher threbd.
            finbl String input = line;
            SwingUtilities.invokeLbter(new Runnbble() {
                @Override
                public void run() {
                    echoInputLine(input);
                }
            });
            return line;
        }
    };

    privbte stbtic String newline = System.getProperty("line.sepbrbtor");

    privbte void echoInputLine(String line) {
        ArrbyList<OutputListener> l = new ArrbyList<OutputListener>(bppEchoListeners);
        for (int i = 0; i < l.size(); i++) {
            OutputListener ol = l.get(i);
            ol.putString(line);
            ol.putString(newline);
        }
    }

    privbte OutputListener bppOutput = new OutputListener() {
      @Override
        public void putString(String string) {
            ArrbyList<OutputListener> l = new ArrbyList<OutputListener>(bppEchoListeners);
            for (int i = 0; i < l.size(); i++) {
                l.get(i).putString(string);
            }
        }
    };

    privbte OutputListener bppError = new OutputListener() {
      @Override
        public void putString(String string) {
            ArrbyList<OutputListener> l = new ArrbyList<OutputListener>(bppEchoListeners);
            for (int i = 0; i < l.size(); i++) {
                l.get(i).putString(string);
            }
        }
    };

   privbte OutputListener dibgnostics = new OutputListener() {
      @Override
        public void putString(String string) {
            ArrbyList<OutputListener> l = new ArrbyList<OutputListener>(dibgnosticsListeners);
            for (int i = 0; i < l.size(); i++) {
                l.get(i).putString(string);
            }
        }
   };

  /////////////    Spec Request Crebtion/Deletion/Query   ///////////

    privbte EventRequestSpecList specList = new EventRequestSpecList(this);

    public BrebkpointSpec
    crebteSourceLineBrebkpoint(String sourceNbme, int line) {
        return specList.crebteSourceLineBrebkpoint(sourceNbme, line);
    }

    public BrebkpointSpec
    crebteClbssLineBrebkpoint(String clbssPbttern, int line) {
        return specList.crebteClbssLineBrebkpoint(clbssPbttern, line);
    }

    public BrebkpointSpec
    crebteMethodBrebkpoint(String clbssPbttern,
                           String methodId, List<String> methodArgs) {
        return specList.crebteMethodBrebkpoint(clbssPbttern,
                                                 methodId, methodArgs);
    }

    public ExceptionSpec
    crebteExceptionIntercept(String clbssPbttern,
                             boolebn notifyCbught,
                             boolebn notifyUncbught) {
        return specList.crebteExceptionIntercept(clbssPbttern,
                                                   notifyCbught,
                                                   notifyUncbught);
    }

    public AccessWbtchpointSpec
    crebteAccessWbtchpoint(String clbssPbttern, String fieldId) {
        return specList.crebteAccessWbtchpoint(clbssPbttern, fieldId);
    }

    public ModificbtionWbtchpointSpec
    crebteModificbtionWbtchpoint(String clbssPbttern, String fieldId) {
        return specList.crebteModificbtionWbtchpoint(clbssPbttern,
                                                       fieldId);
    }

    public void delete(EventRequestSpec spec) {
        specList.delete(spec);
    }

    void resolve(ReferenceType refType) {
        specList.resolve(refType);
    }

    public void instbll(EventRequestSpec spec) {
        specList.instbll(spec, vm());
    }

    public List<EventRequestSpec> eventRequestSpecs() {
        return specList.eventRequestSpecs();
    }
}
