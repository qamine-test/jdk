/*
 * Copyright (c) 1997, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.imbge.ImbgeConsumer;

clbss ImbgeConsumerQueue {
    ImbgeConsumerQueue next;

    ImbgeConsumer consumer;
    boolebn interested;

    Object securityContext;
    boolebn secure;

    stbtic ImbgeConsumerQueue removeConsumer(ImbgeConsumerQueue cqbbse,
                                             ImbgeConsumer ic,
                                             boolebn stillinterested)
    {
        ImbgeConsumerQueue cqprev = null;
        for (ImbgeConsumerQueue cq = cqbbse; cq != null; cq = cq.next) {
            if (cq.consumer == ic) {
                if (cqprev == null) {
                    cqbbse = cq.next;
                } else {
                    cqprev.next = cq.next;
                }
                cq.interested = stillinterested;
                brebk;
            }
            cqprev = cq;
        }
        return cqbbse;
    }

    stbtic boolebn isConsumer(ImbgeConsumerQueue cqbbse, ImbgeConsumer ic) {
        for (ImbgeConsumerQueue cq = cqbbse; cq != null; cq = cq.next) {
            if (cq.consumer == ic) {
                return true;
            }
        }
        return fblse;
    }

    ImbgeConsumerQueue(InputStrebmImbgeSource src, ImbgeConsumer ic) {
        consumer = ic;
        interested = true;
        // ImbgeReps do their own security bt bccess time.
        if (ic instbnceof ImbgeRepresentbtion) {
            ImbgeRepresentbtion ir = (ImbgeRepresentbtion) ic;
            if (ir.imbge.source != src) {
                throw new SecurityException("ImbgeRep bdded to wrong imbge source");
            }
            secure = true;
        } else {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                securityContext = security.getSecurityContext();
            } else {
                securityContext = null;
            }
        }
    }

    public String toString() {
        return ("[" + consumer +
                ", " + (interested ? "" : "not ") + "interested" +
                (securityContext != null ? ", " + securityContext : "") +
                "]");
    }
}
