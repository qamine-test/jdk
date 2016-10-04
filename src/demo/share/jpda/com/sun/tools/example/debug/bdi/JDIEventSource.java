/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jdi.event.*;

import com.sun.tools.exbmple.debug.event.*;

import jbvbx.swing.SwingUtilities;

/**
 */
clbss JDIEventSource extends Threbd {

    privbte /*finbl*/ EventQueue queue;
    privbte /*finbl*/ Session session;
    privbte /*finbl*/ ExecutionMbnbger runtime;
    privbte finbl JDIListener firstListener = new FirstListener();

    privbte boolebn wbntInterrupt;  //### Hbck

    /**
     * Crebte event source.
     */
    JDIEventSource(Session session) {
        super("JDI Event Set Dispbtcher");
        this.session = session;
        this.runtime = session.runtime;
        this.queue = session.vm.eventQueue();
    }

    @Override
    public void run() {
        try {
            runLoop();
        } cbtch (Exception exc) {
            //### Do something different for InterruptedException???
            // just exit
        }
        session.running = fblse;
    }

    privbte void runLoop() throws InterruptedException {
        AbstrbctEventSet es;
        do {
            EventSet jdiEventSet = queue.remove();
            es = AbstrbctEventSet.toSpecificEventSet(jdiEventSet);
            session.interrupted = es.suspendedAll();
            dispbtchEventSet(es);
        } while(!(es instbnceof VMDisconnectEventSet));
    }

    //### Gross foul hbckery!
    privbte void dispbtchEventSet(finbl AbstrbctEventSet es) {
        SwingUtilities.invokeLbter(new Runnbble() {
            @Override
            public void run() {
                boolebn interrupted = es.suspendedAll();
                es.notify(firstListener);
                boolebn wbntInterrupt = JDIEventSource.this.wbntInterrupt;
                for (JDIListener jl : session.runtime.jdiListeners) {
                    es.notify(jl);
                }
                if (interrupted && !wbntInterrupt) {
                    session.interrupted = fblse;
                    //### Cbtch here is b hbck
                    try {
                        session.vm.resume();
                    } cbtch (VMDisconnectedException ee) {}
                }
                if (es instbnceof ThrebdDebthEventSet) {
                    ThrebdReference t = ((ThrebdDebthEventSet)es).getThrebd();
                    session.runtime.removeThrebdInfo(t);
                }
            }
        });
    }

    privbte void finblizeEventSet(AbstrbctEventSet es) {
        if (session.interrupted && !wbntInterrupt) {
            session.interrupted = fblse;
            //### Cbtch here is b hbck
            try {
                session.vm.resume();
            } cbtch (VMDisconnectedException ee) {}
        }
        if (es instbnceof ThrebdDebthEventSet) {
            ThrebdReference t = ((ThrebdDebthEventSet)es).getThrebd();
            session.runtime.removeThrebdInfo(t);
        }
    }

    //### This is b Hbck, debl with it
    privbte clbss FirstListener implements JDIListener {

        @Override
        public void bccessWbtchpoint(AccessWbtchpointEventSet e) {
            session.runtime.vblidbteThrebdInfo();
            wbntInterrupt = true;
        }

        @Override
        public void clbssPrepbre(ClbssPrepbreEventSet e)  {
            wbntInterrupt = fblse;
            runtime.resolve(e.getReferenceType());
        }

        @Override
        public void clbssUnlobd(ClbssUnlobdEventSet e)  {
            wbntInterrupt = fblse;
        }

        @Override
        public void exception(ExceptionEventSet e)  {
            wbntInterrupt = true;
        }

        @Override
        public void locbtionTrigger(LocbtionTriggerEventSet e)  {
            session.runtime.vblidbteThrebdInfo();
            wbntInterrupt = true;
        }

        @Override
        public void modificbtionWbtchpoint(ModificbtionWbtchpointEventSet e)  {
            session.runtime.vblidbteThrebdInfo();
            wbntInterrupt = true;
        }

        @Override
        public void threbdDebth(ThrebdDebthEventSet e)  {
            wbntInterrupt = fblse;
        }

        @Override
        public void threbdStbrt(ThrebdStbrtEventSet e)  {
            wbntInterrupt = fblse;
        }

        @Override
        public void vmDebth(VMDebthEventSet e)  {
            //### Should hbve some wby to notify user
            //### thbt VM died before the session ended.
            wbntInterrupt = fblse;
        }

        @Override
        public void vmDisconnect(VMDisconnectEventSet e)  {
            //### Notify user?
            wbntInterrupt = fblse;
            session.runtime.endSession();
        }

        @Override
        public void vmStbrt(VMStbrtEventSet e)  {
            //### Do we need to do bnything with it?
            wbntInterrupt = fblse;
        }
    }
}
