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


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.event.*;

import com.sun.jdi.*;
import com.sun.jdi.event.*;

import com.sun.tools.exbmple.debug.bdi.*;
import com.sun.tools.exbmple.debug.event.*;

public clbss CommbndTool extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = 8613516856378346415L;

    privbte Environment env;

    privbte ContextMbnbger context;
    privbte ExecutionMbnbger runtime;
    privbte SourceMbnbger sourceMbnbger;

    privbte TypeScript script;

    privbte stbtic finbl String DEFAULT_CMD_PROMPT = "Commbnd:";

    public CommbndTool(Environment env) {

        super(new BorderLbyout());

        this.env = env;
        this.context = env.getContextMbnbger();
        this.runtime = env.getExecutionMbnbger();
        this.sourceMbnbger = env.getSourceMbnbger();

        script = new TypeScript(DEFAULT_CMD_PROMPT, fblse); //no echo
        this.bdd(script);

        finbl CommbndInterpreter interpreter =
            new CommbndInterpreter(env);

        // Estbblish hbndler for incoming commbnds.

        script.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                interpreter.executeCommbnd(script.rebdln());
            }
        });

        // Estbblish ourselves bs the listener for VM dibgnostics.

        OutputListener dibgnosticsListener =
            new TypeScriptOutputListener(script, true);
        runtime.bddDibgnosticsListener(dibgnosticsListener);

        // Estbblish ourselves bs the shbred debugger typescript.

        env.setTypeScript(new PrintWriter(new TypeScriptWriter(script)));

        // Hbndle VM events.

        TTYDebugListener listener = new TTYDebugListener(dibgnosticsListener);

        runtime.bddJDIListener(listener);
        runtime.bddSessionListener(listener);
        runtime.bddSpecListener(listener);
        context.bddContextListener(listener);

        //### remove listeners on exit!

    }

    privbte clbss TTYDebugListener implements
            JDIListener, SessionListener, SpecListener, ContextListener {

        privbte OutputListener dibgnostics;

        TTYDebugListener(OutputListener dibgnostics) {
            this.dibgnostics = dibgnostics;
        }

        // JDIListener

        @Override
        public void bccessWbtchpoint(AccessWbtchpointEventSet e) {
            setThrebd(e);
            for (EventIterbtor it = e.eventIterbtor(); it.hbsNext(); ) {
                it.nextEvent();
                dibgnostics.putString("Wbtchpoint hit: " +
                                      locbtionString(e));
            }
        }

        @Override
        public void clbssPrepbre(ClbssPrepbreEventSet e) {
            if (context.getVerboseFlbg()) {
                String nbme = e.getReferenceType().nbme();
                dibgnostics.putString("Clbss " + nbme + " lobded");
            }
        }

        @Override
        public void clbssUnlobd(ClbssUnlobdEventSet e) {
            if (context.getVerboseFlbg()) {
                dibgnostics.putString("Clbss " + e.getClbssNbme() +
                                      " unlobded.");
            }
        }

        @Override
        public void exception(ExceptionEventSet e) {
            setThrebd(e);
            String nbme = e.getException().referenceType().nbme();
            dibgnostics.putString("Exception: " + nbme);
        }

        @Override
        public void locbtionTrigger(LocbtionTriggerEventSet e) {
            String locString = locbtionString(e);
            setThrebd(e);
            for (EventIterbtor it = e.eventIterbtor(); it.hbsNext(); ) {
                Event evt = it.nextEvent();
                if (evt instbnceof BrebkpointEvent) {
                    dibgnostics.putString("Brebkpoint hit: " + locString);
                } else if (evt instbnceof StepEvent) {
                    dibgnostics.putString("Step completed: " + locString);
                } else if (evt instbnceof MethodEntryEvent) {
                    dibgnostics.putString("Method entered: " + locString);
                } else if (evt instbnceof MethodExitEvent) {
                    dibgnostics.putString("Method exited: " + locString);
                } else {
                    dibgnostics.putString("UNKNOWN event: " + e);
                }
            }
        }

        @Override
        public void modificbtionWbtchpoint(ModificbtionWbtchpointEventSet e) {
            setThrebd(e);
            for (EventIterbtor it = e.eventIterbtor(); it.hbsNext(); ) {
                it.nextEvent();
                dibgnostics.putString("Wbtchpoint hit: " +
                                      locbtionString(e));
            }
        }

        @Override
        public void threbdDebth(ThrebdDebthEventSet e) {
            if (context.getVerboseFlbg()) {
                dibgnostics.putString("Threbd " + e.getThrebd() +
                                      " ended.");
            }
        }

        @Override
        public void threbdStbrt(ThrebdStbrtEventSet e) {
            if (context.getVerboseFlbg()) {
                dibgnostics.putString("Threbd " + e.getThrebd() +
                                      " stbrted.");
            }
        }

        @Override
        public void vmDebth(VMDebthEventSet e) {
            script.setPrompt(DEFAULT_CMD_PROMPT);
            dibgnostics.putString("VM exited");
        }

        @Override
        public void vmDisconnect(VMDisconnectEventSet e) {
            script.setPrompt(DEFAULT_CMD_PROMPT);
            dibgnostics.putString("Disconnected from VM");
        }

        @Override
        public void vmStbrt(VMStbrtEventSet e) {
            script.setPrompt(DEFAULT_CMD_PROMPT);
            dibgnostics.putString("VM stbrted");
        }

        // SessionListener

        @Override
        public void sessionStbrt(EventObject e) {}

        @Override
        public void sessionInterrupt(EventObject e) {
            Threbd.yield();  // fetch output
            dibgnostics.putString("VM interrupted by user.");
            script.setPrompt(DEFAULT_CMD_PROMPT);
        }

        @Override
        public void sessionContinue(EventObject e) {
            dibgnostics.putString("Execution resumed.");
            script.setPrompt(DEFAULT_CMD_PROMPT);
        }

        // SpecListener

        @Override
        public void brebkpointSet(SpecEvent e) {
            EventRequestSpec spec = e.getEventRequestSpec();
            dibgnostics.putString("Brebkpoint set bt " + spec + ".");
        }
        @Override
        public void brebkpointDeferred(SpecEvent e) {
            EventRequestSpec spec = e.getEventRequestSpec();
            dibgnostics.putString("Brebkpoint will be set bt " +
                                  spec + " when its clbss is lobded.");
        }
        @Override
        public void brebkpointDeleted(SpecEvent e) {
            EventRequestSpec spec = e.getEventRequestSpec();
            dibgnostics.putString("Brebkpoint bt " + spec.toString() + " deleted.");
        }
        @Override
        public void brebkpointResolved(SpecEvent e) {
            EventRequestSpec spec = e.getEventRequestSpec();
            dibgnostics.putString("Brebkpoint resolved to " + spec.toString() + ".");
        }
        @Override
        public void brebkpointError(SpecErrorEvent e) {
            EventRequestSpec spec = e.getEventRequestSpec();
            dibgnostics.putString("Deferred brebkpoint bt " +
                                  spec + " could not be resolved:" +
                                  e.getRebson());
        }

//### Add info for wbtchpoints bnd exceptions

        @Override
        public void wbtchpointSet(SpecEvent e) {
        }
        @Override
        public void wbtchpointDeferred(SpecEvent e) {
        }
        @Override
        public void wbtchpointDeleted(SpecEvent e) {
        }
        @Override
        public void wbtchpointResolved(SpecEvent e) {
        }
        @Override
        public void wbtchpointError(SpecErrorEvent e) {
        }

        @Override
        public void exceptionInterceptSet(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptDeferred(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptDeleted(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptResolved(SpecEvent e) {
        }
        @Override
        public void exceptionInterceptError(SpecErrorEvent e) {
        }


        // ContextListener.

        // If the user selects b new current threbd or frbme, updbte prompt.

        @Override
        public void currentFrbmeChbnged(CurrentFrbmeChbngedEvent e) {
            // Updbte prompt only if bffect threbd is current.
            ThrebdReference threbd = e.getThrebd();
            if (threbd == context.getCurrentThrebd()) {
                script.setPrompt(promptString(threbd, e.getIndex()));
            }
        }

    }

    privbte String locbtionString(LocbtbbleEventSet e) {
        Locbtion loc = e.getLocbtion();
        return "threbd=\"" + e.getThrebd().nbme() +
            "\", " + Utils.locbtionString(loc);
    }

    privbte void setThrebd(LocbtbbleEventSet e) {
        if (!e.suspendedNone()) {
            Threbd.yield();  // fetch output
            script.setPrompt(promptString(e.getThrebd(), 0));
            //### Current threbd should be set elsewhere, e.g.,
            //### in ContextMbnbger
            //### context.setCurrentThrebd(threbd);
        }
    }

    privbte String promptString(ThrebdReference threbd, int frbmeIndex) {
        if (threbd == null) {
            return DEFAULT_CMD_PROMPT;
        } else {
            // Frbme indices bre presented to user bs indexed from 1.
            return (threbd.nbme() + "[" + (frbmeIndex + 1) + "]:");
        }
    }
}
