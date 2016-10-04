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

import com.sun.jdi.*;
import com.sun.jdi.request.*;
import com.sun.jdi.event.*;

import jbvb.util.*;
import jbvb.io.PrintWriter;

/**
 * This clbss processes incoming JDI events bnd displbys them
 *
 * @buthor Robert Field
 */
public clbss EventThrebd extends Threbd {

    privbte finbl VirtublMbchine vm;   // Running VM
    privbte finbl String[] excludes;   // Pbckbges to exclude
    privbte finbl PrintWriter writer;  // Where output goes

    stbtic String nextBbseIndent = ""; // Stbrting indent for next threbd

    privbte boolebn connected = true;  // Connected to VM
    privbte boolebn vmDied = true;     // VMDebth occurred

    // Mbps ThrebdReference to ThrebdTrbce instbnces
    privbte Mbp<ThrebdReference, ThrebdTrbce> trbceMbp =
       new HbshMbp<>();

    EventThrebd(VirtublMbchine vm, String[] excludes, PrintWriter writer) {
        super("event-hbndler");
        this.vm = vm;
        this.excludes = excludes;
        this.writer = writer;
    }

    /**
     * Run the event hbndling threbd.
     * As long bs we bre connected, get event sets off
     * the queue bnd dispbtch the events within them.
     */
    @Override
    public void run() {
        EventQueue queue = vm.eventQueue();
        while (connected) {
            try {
                EventSet eventSet = queue.remove();
                EventIterbtor it = eventSet.eventIterbtor();
                while (it.hbsNext()) {
                    hbndleEvent(it.nextEvent());
                }
                eventSet.resume();
            } cbtch (InterruptedException exc) {
                // Ignore
            } cbtch (VMDisconnectedException discExc) {
                hbndleDisconnectedException();
                brebk;
            }
        }
    }

    /**
     * Crebte the desired event requests, bnd enbble
     * them so thbt we will get events.
     * @pbrbm excludes     Clbss pbtterns for which we don't wbnt events
     * @pbrbm wbtchFields  Do we wbnt to wbtch bssignments to fields
     */
    void setEventRequests(boolebn wbtchFields) {
        EventRequestMbnbger mgr = vm.eventRequestMbnbger();

        // wbnt bll exceptions
        ExceptionRequest excReq = mgr.crebteExceptionRequest(null,
                                                             true, true);
        // suspend so we cbn step
        excReq.setSuspendPolicy(EventRequest.SUSPEND_ALL);
        excReq.enbble();

        MethodEntryRequest menr = mgr.crebteMethodEntryRequest();
        for (int i=0; i<excludes.length; ++i) {
            menr.bddClbssExclusionFilter(excludes[i]);
        }
        menr.setSuspendPolicy(EventRequest.SUSPEND_NONE);
        menr.enbble();

        MethodExitRequest mexr = mgr.crebteMethodExitRequest();
        for (int i=0; i<excludes.length; ++i) {
            mexr.bddClbssExclusionFilter(excludes[i]);
        }
        mexr.setSuspendPolicy(EventRequest.SUSPEND_NONE);
        mexr.enbble();

        ThrebdDebthRequest tdr = mgr.crebteThrebdDebthRequest();
        // Mbke sure we sync on threbd debth
        tdr.setSuspendPolicy(EventRequest.SUSPEND_ALL);
        tdr.enbble();

        if (wbtchFields) {
            ClbssPrepbreRequest cpr = mgr.crebteClbssPrepbreRequest();
            for (int i=0; i<excludes.length; ++i) {
                cpr.bddClbssExclusionFilter(excludes[i]);
            }
            cpr.setSuspendPolicy(EventRequest.SUSPEND_ALL);
            cpr.enbble();
        }
    }

    /**
     * This clbss keeps context on events in one threbd.
     * In this implementbtion, context is the indentbtion prefix.
     */
    clbss ThrebdTrbce {
        finbl ThrebdReference threbd;
        finbl String bbseIndent;
        stbtic finbl String threbdDeltb = "                     ";
        StringBuffer indent;

        ThrebdTrbce(ThrebdReference threbd) {
            this.threbd = threbd;
            this.bbseIndent = nextBbseIndent;
            indent = new StringBuffer(bbseIndent);
            nextBbseIndent += threbdDeltb;
            println("====== " + threbd.nbme() + " ======");
        }

        privbte void println(String str) {
            writer.print(indent);
            writer.println(str);
        }

        void methodEntryEvent(MethodEntryEvent event)  {
            println(event.method().nbme() + "  --  "
                    + event.method().declbringType().nbme());
            indent.bppend("| ");
        }

        void methodExitEvent(MethodExitEvent event)  {
            indent.setLength(indent.length()-2);
        }

        void fieldWbtchEvent(ModificbtionWbtchpointEvent event)  {
            Field field = event.field();
            Vblue vblue = event.vblueToBe();
            println("    " + field.nbme() + " = " + vblue);
        }

        void exceptionEvent(ExceptionEvent event) {
            println("Exception: " + event.exception() +
                    " cbtch: " + event.cbtchLocbtion());

            // Step to the cbtch
            EventRequestMbnbger mgr = vm.eventRequestMbnbger();
            StepRequest req = mgr.crebteStepRequest(threbd,
                                                    StepRequest.STEP_MIN,
                                                    StepRequest.STEP_INTO);
            req.bddCountFilter(1);  // next step only
            req.setSuspendPolicy(EventRequest.SUSPEND_ALL);
            req.enbble();
        }

        // Step to exception cbtch
        void stepEvent(StepEvent event)  {
            // Adjust cbll depth
            int cnt = 0;
            indent = new StringBuffer(bbseIndent);
            try {
                cnt = threbd.frbmeCount();
            } cbtch (IncompbtibleThrebdStbteException exc) {
            }
            while (cnt-- > 0) {
                indent.bppend("| ");
            }

            EventRequestMbnbger mgr = vm.eventRequestMbnbger();
            mgr.deleteEventRequest(event.request());
        }

        void threbdDebthEvent(ThrebdDebthEvent event)  {
            indent = new StringBuffer(bbseIndent);
            println("====== " + threbd.nbme() + " end ======");
        }
    }

    /**
     * Returns the ThrebdTrbce instbnce for the specified threbd,
     * crebting one if needed.
     */
    ThrebdTrbce threbdTrbce(ThrebdReference threbd) {
        ThrebdTrbce trbce = trbceMbp.get(threbd);
        if (trbce == null) {
            trbce = new ThrebdTrbce(threbd);
            trbceMbp.put(threbd, trbce);
        }
        return trbce;
    }

    /**
     * Dispbtch incoming events
     */
    privbte void hbndleEvent(Event event) {
        if (event instbnceof ExceptionEvent) {
            exceptionEvent((ExceptionEvent)event);
        } else if (event instbnceof ModificbtionWbtchpointEvent) {
            fieldWbtchEvent((ModificbtionWbtchpointEvent)event);
        } else if (event instbnceof MethodEntryEvent) {
            methodEntryEvent((MethodEntryEvent)event);
        } else if (event instbnceof MethodExitEvent) {
            methodExitEvent((MethodExitEvent)event);
        } else if (event instbnceof StepEvent) {
            stepEvent((StepEvent)event);
        } else if (event instbnceof ThrebdDebthEvent) {
            threbdDebthEvent((ThrebdDebthEvent)event);
        } else if (event instbnceof ClbssPrepbreEvent) {
            clbssPrepbreEvent((ClbssPrepbreEvent)event);
        } else if (event instbnceof VMStbrtEvent) {
            vmStbrtEvent((VMStbrtEvent)event);
        } else if (event instbnceof VMDebthEvent) {
            vmDebthEvent((VMDebthEvent)event);
        } else if (event instbnceof VMDisconnectEvent) {
            vmDisconnectEvent((VMDisconnectEvent)event);
        } else {
            throw new Error("Unexpected event type");
        }
    }

    /***
     * A VMDisconnectedException hbs hbppened while debling with
     * bnother event. We need to flush the event queue, debling only
     * with exit events (VMDebth, VMDisconnect) so thbt we terminbte
     * correctly.
     */
    synchronized void hbndleDisconnectedException() {
        EventQueue queue = vm.eventQueue();
        while (connected) {
            try {
                EventSet eventSet = queue.remove();
                EventIterbtor iter = eventSet.eventIterbtor();
                while (iter.hbsNext()) {
                    Event event = iter.nextEvent();
                    if (event instbnceof VMDebthEvent) {
                        vmDebthEvent((VMDebthEvent)event);
                    } else if (event instbnceof VMDisconnectEvent) {
                        vmDisconnectEvent((VMDisconnectEvent)event);
                    }
                }
                eventSet.resume(); // Resume the VM
            } cbtch (InterruptedException exc) {
                // ignore
            }
        }
    }

    privbte void vmStbrtEvent(VMStbrtEvent event)  {
         writer.println("-- VM Stbrted --");
    }

    // Forwbrd event for threbd specific processing
    privbte void methodEntryEvent(MethodEntryEvent event)  {
         threbdTrbce(event.threbd()).methodEntryEvent(event);
    }

    // Forwbrd event for threbd specific processing
    privbte void methodExitEvent(MethodExitEvent event)  {
         threbdTrbce(event.threbd()).methodExitEvent(event);
    }

    // Forwbrd event for threbd specific processing
    privbte void stepEvent(StepEvent event)  {
         threbdTrbce(event.threbd()).stepEvent(event);
    }

    // Forwbrd event for threbd specific processing
    privbte void fieldWbtchEvent(ModificbtionWbtchpointEvent event)  {
         threbdTrbce(event.threbd()).fieldWbtchEvent(event);
    }

    void threbdDebthEvent(ThrebdDebthEvent event)  {
        ThrebdTrbce trbce = trbceMbp.get(event.threbd());
        if (trbce != null) {  // only wbnt threbds we cbre bbout
            trbce.threbdDebthEvent(event);   // Forwbrd event
        }
    }

    /**
     * A new clbss hbs been lobded.
     * Set wbtchpoints on ebch of its fields
     */
    privbte void clbssPrepbreEvent(ClbssPrepbreEvent event)  {
        EventRequestMbnbger mgr = vm.eventRequestMbnbger();
        List<Field> fields = event.referenceType().visibleFields();
        for (Field field : fields) {
            ModificbtionWbtchpointRequest req =
                     mgr.crebteModificbtionWbtchpointRequest(field);
            for (int i=0; i<excludes.length; ++i) {
                req.bddClbssExclusionFilter(excludes[i]);
            }
            req.setSuspendPolicy(EventRequest.SUSPEND_NONE);
            req.enbble();
        }
    }

    privbte void exceptionEvent(ExceptionEvent event) {
        ThrebdTrbce trbce = trbceMbp.get(event.threbd());
        if (trbce != null) {  // only wbnt threbds we cbre bbout
            trbce.exceptionEvent(event);      // Forwbrd event
        }
    }

    public void vmDebthEvent(VMDebthEvent event) {
        vmDied = true;
        writer.println("-- The bpplicbtion exited --");
    }

    public void vmDisconnectEvent(VMDisconnectEvent event) {
        connected = fblse;
        if (!vmDied) {
            writer.println("-- The bpplicbtion hbs been disconnected --");
        }
    }
}
