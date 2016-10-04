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
import jbvb.util.*;

public clbss InternblEventHbndler implements Runnbble
{
    EventQueueImpl queue;
    VirtublMbchineImpl vm;

    InternblEventHbndler(VirtublMbchineImpl vm, EventQueueImpl queue)
    {
        this.vm = vm;
        this.queue = queue;
        Threbd threbd = new Threbd(vm.threbdGroupForJDI(), this,
                                   "JDI Internbl Event Hbndler");
        threbd.setDbemon(true);
        threbd.stbrt();
    }

    public void run() {
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_EVENTS) != 0) {
            vm.printTrbce("Internbl event hbndler running");
        }
        try {
            while (true) {
                try {
                    EventSet eventSet = queue.removeInternbl();
                    EventIterbtor it = eventSet.eventIterbtor();
                    while (it.hbsNext()) {
                        Event event = it.nextEvent();
                        if (event instbnceof ClbssUnlobdEvent) {
                            ClbssUnlobdEvent cuEvent = (ClbssUnlobdEvent)event;
                            vm.removeReferenceType(cuEvent.clbssSignbture());

                            if ((vm.trbceFlbgs & VirtublMbchine.TRACE_EVENTS) != 0) {
                                vm.printTrbce("Hbndled Unlobd Event for " +
                                              cuEvent.clbssSignbture());
                            }
                        } else if (event instbnceof ClbssPrepbreEvent) {
                            ClbssPrepbreEvent cpEvent = (ClbssPrepbreEvent)event;
                            ((ReferenceTypeImpl)cpEvent.referenceType())
                                                            .mbrkPrepbred();

                            if ((vm.trbceFlbgs & VirtublMbchine.TRACE_EVENTS) != 0) {
                                vm.printTrbce("Hbndled Prepbre Event for " +
                                              cpEvent.referenceType().nbme());
                            }
                        }

                    }

                /*
                 * Hbndle exceptions thbt cbn occur in normbl operbtion
                 * but which cbn't be bccounted for by event builder
                 * methods. The threbd should not be terminbted if they
                 * occur.
                 *
                 * TO DO: We need b better wby to log these conditions.
                 */
                } cbtch (VMOutOfMemoryException vmme) {
                    vmme.printStbckTrbce();
                } cbtch (InconsistentDebugInfoException idie) {
                    idie.printStbckTrbce();

                /*
                 * If bny of these exceptions below occurs, there is some
                 * sort of progrbmming error thbt should be bddressed in
                 * the JDI implemementbtion. However, it would cripple
                 * the implementbtion if we let this threbd die due to
                 * one of them. So, b notificbtion of the exception is
                 * given bnd we bttempt to continue.
                 */
                } cbtch (ObjectCollectedException oce) {
                    oce.printStbckTrbce();
                } cbtch (ClbssNotPrepbredException cnpe) {
                    cnpe.printStbckTrbce();
                }
            }
        } cbtch (InterruptedException e) {  // should we reblly die here
        } cbtch (VMDisconnectedException e) {  // time to die
        }
        if ((vm.trbceFlbgs & VirtublMbchine.TRACE_EVENTS) != 0) {
            vm.printTrbce("Internbl event hbndler exiting");
        }
    }
}
