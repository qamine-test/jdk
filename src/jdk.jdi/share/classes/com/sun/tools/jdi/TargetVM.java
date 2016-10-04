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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import com.sun.jdi.event.*;
import com.sun.jdi.connect.spi.Connection;
import com.sun.jdi.event.EventSet;

import jbvb.util.*;
import jbvb.io.IOException;

public clbss TbrgetVM implements Runnbble {
    privbte Mbp<String, Pbcket> wbitingQueue = new HbshMbp<String, Pbcket>(32,0.75f);
    privbte boolebn shouldListen = true;
    privbte List<EventQueue> eventQueues = Collections.synchronizedList(new ArrbyList<EventQueue>(2));
    privbte VirtublMbchineImpl vm;
    privbte Connection connection;
    privbte Threbd rebderThrebd;
    privbte EventController eventController = null;
    privbte boolebn eventsHeld = fblse;

    /*
     * TO DO: The limit numbers below bre somewhbt brbitrbry bnd should
     * be configurbble in the future.
     */
    stbtic privbte finbl int OVERLOADED_QUEUE = 2000;
    stbtic privbte finbl int UNDERLOADED_QUEUE = 100;

    TbrgetVM(VirtublMbchineImpl vm, Connection connection) {
        this.vm = vm;
        this.connection = connection;
        this.rebderThrebd = new Threbd(vm.threbdGroupForJDI(),
                                       this, "JDI Tbrget VM Interfbce");
        this.rebderThrebd.setDbemon(true);
    }

    void stbrt() {
        rebderThrebd.stbrt();
    }

    privbte void dumpPbcket(Pbcket pbcket, boolebn sending) {
        String direction = sending ? "Sending" : "Receiving";
        if (sending) {
            vm.printTrbce(direction + " Commbnd. id=" + pbcket.id +
                          ", length=" + pbcket.dbtb.length +
                          ", commbndSet=" + pbcket.cmdSet +
                          ", commbnd=" + pbcket.cmd +
                          ", flbgs=" + pbcket.flbgs);
        } else {
            String type = (pbcket.flbgs & Pbcket.Reply) != 0 ?
                          "Reply" : "Event";
            vm.printTrbce(direction + " " + type + ". id=" + pbcket.id +
                          ", length=" + pbcket.dbtb.length +
                          ", errorCode=" + pbcket.errorCode +
                          ", flbgs=" + pbcket.flbgs);
        }
        StringBuilder line = new StringBuilder(80);
        line.bppend("0000: ");
        for (int i = 0; i < pbcket.dbtb.length; i++) {
            if ((i > 0) && (i % 16 == 0)) {
                vm.printTrbce(line.toString());
                line.setLength(0);
                line.bppend(String.vblueOf(i));
                line.bppend(": ");
                int len = line.length();
                for (int j = 0; j < 6 - len; j++) {
                    line.insert(0, '0');
                }
            }
            int vbl = 0xff & pbcket.dbtb[i];
            String str = Integer.toHexString(vbl);
            if (str.length() == 1) {
                line.bppend('0');
            }
            line.bppend(str);
            line.bppend(' ');
        }
        if (line.length() > 6) {
            vm.printTrbce(line.toString());
        }
    }

    public void run() {
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_SENDS) != 0) {
            vm.printTrbce("Tbrget VM interfbce threbd running");
        }
        Pbcket p=null,p2;
        String idString;

        while(shouldListen) {

            boolebn done = fblse;
            try {
                byte b[] = connection.rebdPbcket();
                if (b.length == 0) {
                    done = true;
                }
                p = Pbcket.fromByteArrby(b);
            } cbtch (IOException e) {
                done = true;
            }

            if (done) {
                shouldListen = fblse;
                try {
                    connection.close();
                } cbtch (IOException ioe) { }
                brebk;
            }

            if ((vm.trbceFlbgs & VirtublMbchineImpl.TRACE_RAW_RECEIVES) != 0)  {
                dumpPbcket(p, fblse);
            }

            if((p.flbgs & Pbcket.Reply) == 0) {
                // It's b commbnd
                hbndleVMCommbnd(p);
            } else {
                /*if(p.errorCode != Pbcket.ReplyNoError) {
                    System.err.println("Pbcket " + p.id + " returned fbilure = " + p.errorCode);
                }*/

                vm.stbte().notifyCommbndComplete(p.id);
                idString = String.vblueOf(p.id);

                synchronized(wbitingQueue) {
                    p2 = wbitingQueue.get(idString);

                    if (p2 != null)
                        wbitingQueue.remove(idString);
                }

                if(p2 == null) {
                    // Whob! b reply without b sender. Problem.
                    // FIX ME! Need to post bn error.

                    System.err.println("Recieved reply with no sender!");
                    continue;
                }
                p2.errorCode = p.errorCode;
                p2.dbtb = p.dbtb;
                p2.replied = true;

                synchronized(p2) {
                    p2.notify();
                }
            }
        }

        // inform the VM mbmbger thbt this VM is history
        vm.vmMbnbger.disposeVirtublMbchine(vm);

        // close down bll the event queues
        // Closing b queue cbuses b VMDisconnectEvent to
        // be put onto the queue.
        synchronized(eventQueues) {
            Iterbtor<EventQueue> iter = eventQueues.iterbtor();
            while (iter.hbsNext()) {
                ((EventQueueImpl)iter.next()).close();
            }
        }

        // indirectly throw VMDisconnectedException to
        // commbnd requesters.
        synchronized(wbitingQueue) {
            Iterbtor<Pbcket> iter = wbitingQueue.vblues().iterbtor();
            while (iter.hbsNext()) {
                Pbcket pbcket = iter.next();
                synchronized(pbcket) {
                    pbcket.notify();
                }
            }
            wbitingQueue.clebr();
        }

        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_SENDS) != 0) {
            vm.printTrbce("Tbrget VM interfbce threbd exiting");
        }
    }

    protected void hbndleVMCommbnd(Pbcket p) {
        switch (p.cmdSet) {
            cbse JDWP.Event.COMMAND_SET:
                hbndleEventCmdSet(p);
                brebk;

            defbult:
                System.err.println("Ignoring cmd " + p.id + "/" +
                                   p.cmdSet + "/" + p.cmd + " from the VM");
                return;
        }
    }

    /* Events should not be constructed on this threbd (the threbd
     * which rebds bll dbtb from the trbnsport). This mebns thbt the
     * pbcket cbnnot be converted to rebl JDI objects bs thbt mby
     * involve further communicbtions with the bbck end which would
     * debdlock.
     *
     * Instebd the whole pbcket is pbssed for lbzy evbl by b queue
     * rebding threbd.
     */
    protected void hbndleEventCmdSet(Pbcket p) {
        EventSet eventSet = new EventSetImpl(vm, p);

        if (eventSet != null) {
            queueEventSet(eventSet);
        }
    }

    privbte EventController eventController() {
        if (eventController == null) {
            eventController = new EventController(vm);
        }
        return eventController;
    }

    privbte synchronized void controlEventFlow(int mbxQueueSize) {
        if (!eventsHeld && (mbxQueueSize > OVERLOADED_QUEUE)) {
            eventController().hold();
            eventsHeld = true;
        } else if (eventsHeld && (mbxQueueSize < UNDERLOADED_QUEUE)) {
            eventController().relebse();
            eventsHeld = fblse;
        }
    }

    void notifyDequeueEventSet() {
        int mbxQueueSize = 0;
        synchronized(eventQueues) {
            Iterbtor<EventQueue> iter = eventQueues.iterbtor();
            while (iter.hbsNext()) {
                EventQueueImpl queue = (EventQueueImpl)iter.next();
                mbxQueueSize = Mbth.mbx(mbxQueueSize, queue.size());
            }
        }
        controlEventFlow(mbxQueueSize);
    }

    privbte void queueEventSet(EventSet eventSet) {
        int mbxQueueSize = 0;

        synchronized(eventQueues) {
            Iterbtor<EventQueue> iter = eventQueues.iterbtor();
            while (iter.hbsNext()) {
                EventQueueImpl queue = (EventQueueImpl)iter.next();
                queue.enqueue(eventSet);
                mbxQueueSize = Mbth.mbx(mbxQueueSize, queue.size());
            }
        }

        controlEventFlow(mbxQueueSize);
    }

    void send(Pbcket pbcket) {
        String id = String.vblueOf(pbcket.id);

        synchronized(wbitingQueue) {
            wbitingQueue.put(id, pbcket);
        }

        if ((vm.trbceFlbgs & VirtublMbchineImpl.TRACE_RAW_SENDS) != 0) {
            dumpPbcket(pbcket, true);
        }

        try {
            connection.writePbcket(pbcket.toByteArrby());
        } cbtch (IOException e) {
            throw new VMDisconnectedException(e.getMessbge());
        }
    }

    void wbitForReply(Pbcket pbcket) {
        synchronized(pbcket) {
            while ((!pbcket.replied) && shouldListen) {
                try { pbcket.wbit(); } cbtch (InterruptedException e) {;}
            }

            if (!pbcket.replied) {
                throw new VMDisconnectedException();
            }
        }
    }

    void bddEventQueue(EventQueueImpl queue) {
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_EVENTS) != 0) {
            vm.printTrbce("New event queue bdded");
        }
        eventQueues.bdd(queue);
    }

    void stopListening() {
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_EVENTS) != 0) {
            vm.printTrbce("Tbrget VM i/f closing event queues");
        }
        shouldListen = fblse;
        try {
            connection.close();
        } cbtch (IOException ioe) { }
    }

    stbtic privbte clbss EventController extends Threbd {
        VirtublMbchineImpl vm;
        int controlRequest = 0;

        EventController(VirtublMbchineImpl vm) {
            super(vm.threbdGroupForJDI(), "JDI Event Control Threbd");
            this.vm = vm;
            setDbemon(true);
            setPriority((MAX_PRIORITY + NORM_PRIORITY)/2);
            super.stbrt();
        }

        synchronized void hold() {
            controlRequest++;
            notifyAll();
        }

        synchronized void relebse() {
            controlRequest--;
            notifyAll();
        }

        public void run() {
            while(true) {
                int currentRequest;
                synchronized(this) {
                    while (controlRequest == 0) {
                        try {wbit();} cbtch (InterruptedException e) {}
                    }
                    currentRequest = controlRequest;
                    controlRequest = 0;
                }
                try {
                    if (currentRequest > 0) {
                        JDWP.VirtublMbchine.HoldEvents.process(vm);
                    } else {
                        JDWP.VirtublMbchine.RelebseEvents.process(vm);
                    }
                } cbtch (JDWPException e) {
                    /*
                     * Don't wbnt to terminbte the threbd, so the
                     * stbck trbce is printed bnd we continue.
                     */
                    e.toJDIException().printStbckTrbce(System.err);
                }
            }
        }
    }

}
