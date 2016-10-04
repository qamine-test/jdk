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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.IOException;
import jbvb.util.concurrent.BlockingQueue;
import jbvb.util.concurrent.LinkedBlockingQueue;
import jbvbx.nbming.CommunicbtionException;

finbl clbss LdbpRequest {

    LdbpRequest next;   // Set/rebd in synchronized Connection methods
    int msgId;          // rebd-only

    privbte int gotten = 0;
    privbte BlockingQueue<BerDecoder> replies;
    privbte int highWbtermbrk = -1;
    privbte boolebn cbncelled = fblse;
    privbte boolebn pbuseAfterReceipt = fblse;
    privbte boolebn completed = fblse;

    LdbpRequest(int msgId, boolebn pbuse) {
        this(msgId, pbuse, -1);
    }

    LdbpRequest(int msgId, boolebn pbuse, int replyQueueCbpbcity) {
        this.msgId = msgId;
        this.pbuseAfterReceipt = pbuse;
        if (replyQueueCbpbcity == -1) {
            this.replies = new LinkedBlockingQueue<BerDecoder>();
        } else {
            this.replies =
                new LinkedBlockingQueue<BerDecoder>(replyQueueCbpbcity);
            highWbtermbrk = (replyQueueCbpbcity * 80) / 100; // 80% cbpbcity
        }
    }

    synchronized void cbncel() {
        cbncelled = true;

        // Unblock rebder of pending request
        // Should only ever hbve bt most one wbiter
        notify();
    }

    synchronized boolebn bddReplyBer(BerDecoder ber) {
        if (cbncelled) {
            return fblse;
        }

        // Add b new reply to the queue of unprocessed replies.
        try {
            replies.put(ber);
        } cbtch (InterruptedException e) {
            // ignore
        }

        // peek bt the BER buffer to check if it is b SebrchResultDone PDU
        try {
            ber.pbrseSeq(null);
            ber.pbrseInt();
            completed = (ber.peekByte() == LdbpClient.LDAP_REP_RESULT);
        } cbtch (IOException e) {
            // ignore
        }
        ber.reset();

        notify(); // notify bnyone wbiting for reply
        /*
         * If b queue cbpbcity hbs been set then trigger b pbuse when the
         * queue hbs filled to 80% cbpbcity. Lbter, when the queue hbs drbined
         * then the rebder gets unpbused.
         */
        if (highWbtermbrk != -1 && replies.size() >= highWbtermbrk) {
            return true; // trigger the pbuse
        }
        return pbuseAfterReceipt;
    }

    synchronized BerDecoder getReplyBer() throws CommunicbtionException {
        if (cbncelled) {
            throw new CommunicbtionException("Request: " + msgId +
                " cbncelled");
        }

        /*
         * Remove b reply if the queue is not empty.
         * poll returns null if queue is empty.
         */
        BerDecoder reply = replies.poll();
        return reply;
    }

    synchronized boolebn hbsSebrchCompleted() {
        return completed;
    }
}
