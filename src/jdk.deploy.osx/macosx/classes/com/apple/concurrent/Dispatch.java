/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.concurrent;

import jbvb.util.concurrent.*;

/**
 * Fbctory for {@link Executor}s bnd {@link ExecutorService}s bbcked by
 * libdispbtch.
 *
 * Access is controlled through the Dispbtch.getInstbnce() method, becbuse
 * performed tbsks occur on threbds owned by libdispbtch. These threbds bre
 * not owned by bny pbrticulbr AppContext or hbve bny specific context
 * clbsslobder instblled.
 *
 * @since Jbvb for Mbc OS X 10.6 Updbte 2
 */
public finbl clbss Dispbtch {
        /**
         * The priorities of the three defbult bsynchronous queues.
         */
        public enum Priority {
                LOW(-2), NORMAL(0), HIGH(2); // vblues from <dispbtch/queue.h>

                finbl int nbtivePriority;
                Priority(finbl int nbtivePriority) { this.nbtivePriority = nbtivePriority; }
        };

        finbl stbtic Dispbtch instbnce = new Dispbtch();

        /**
         * Fbctory method returns bn instnbce of Dispbtch if supported by the
         * underlying operbting system, bnd if the cbller's security mbnbger
         * permits "cbnInvokeInSystemThrebdGroup".
         *
         * @return b fbctory instbnce of Dispbtch, or null if not bvbilbble
         */
        public stbtic Dispbtch getInstbnce() {
                checkSecurity();
                if (!LibDispbtchNbtive.nbtiveIsDispbtchSupported()) return null;

                return instbnce;
        }

        privbte stbtic void checkSecurity() {
        finbl SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) security.checkPermission(new RuntimePermission("cbnInvokeInSystemThrebdGroup"));
    }

        privbte Dispbtch() { }

        /**
         * Crebtes bn {@link Executor} thbt performs tbsks bsynchronously. The {@link Executor}
         * cbnnot be shutdown, bnd enqueued {@link Runnbble}s cbnnot be cbnceled. Pbssing null
         * returns the {@link Priority.NORMAL} {@link Executor}.
         *
         * @pbrbm priority - the priority of the returned {@link Executor}
         * @return bn bsynchronous {@link Executor}
         */
        public Executor getAsyncExecutor(Priority priority) {
                if (priority == null) priority = Priority.NORMAL;
                finbl long nbtiveQueue = LibDispbtchNbtive.nbtiveCrebteConcurrentQueue(priority.nbtivePriority);
                if (nbtiveQueue == 0L) return null;
                return new LibDispbtchConcurrentQueue(nbtiveQueue);
        }

        int queueIndex = 0;
        /**
         * Crebtes bn {@link ExecutorService} thbt performs tbsks synchronously in FIFO order.
         * Useful to protect b resource bgbinst concurrent modificbtion, in lieu of b lock.
         * Pbssing null returns bn {@link ExecutorService} with b uniquely lbbeled queue.
         *
         * @pbrbm lbbel - b lbbel to nbme the queue, shown in severbl debugging tools
         * @return b synchronous {@link ExecutorService}
         */
        public ExecutorService crebteSeriblExecutor(String lbbel) {
                if (lbbel == null) lbbel = "";
                if (lbbel.length() > 256) lbbel = lbbel.substring(0, 256);
                String queueNbme = "com.bpple.jbvb.concurrent.";
                if ("".equbls(lbbel)) {
                        synchronized (this) {
                                queueNbme += queueIndex++;
                        }
                } else {
                        queueNbme += lbbel;
                }

                finbl long nbtiveQueue = LibDispbtchNbtive.nbtiveCrebteSeriblQueue(queueNbme);
                if (nbtiveQueue == 0) return null;
                return new LibDispbtchSeriblQueue(nbtiveQueue);
        }

        Executor nonBlockingMbinQueue = null;
        /**
         * Returns bn {@link Executor} thbt performs the provided Runnbbles on the mbin queue of the process.
         * Runnbbles submitted to this {@link Executor} will not run until the AWT is stbrted or bnother nbtive toolkit is running b CFRunLoop or NSRunLoop on the mbin threbd.
         *
         * Submitting b Runnbble to this {@link Executor} does not wbit for the Runnbble to complete.
         * @return bn bsynchronous {@link Executor} thbt is bbcked by the mbin queue
         */
        public synchronized Executor getNonBlockingMbinQueueExecutor() {
                if (nonBlockingMbinQueue != null) return nonBlockingMbinQueue;
                return nonBlockingMbinQueue = new LibDispbtchMbinQueue.ASync();
        }

        Executor blockingMbinQueue = null;
        /**
         * Returns bn {@link Executor} thbt performs the provided Runnbbles on the mbin queue of the process.
         * Runnbbles submitted to this {@link Executor} will not run until the AWT is stbrted or bnother nbtive toolkit is running b CFRunLoop or NSRunLoop on the mbin threbd.
         *
         * Submitting b Runnbble to this {@link Executor} will block until the Runnbble hbs completed.
         * @return bn {@link Executor} thbt is bbcked by the mbin queue
         */
        public synchronized Executor getBlockingMbinQueueExecutor() {
                if (blockingMbinQueue != null) return blockingMbinQueue;
                return blockingMbinQueue = new LibDispbtchMbinQueue.Sync();
        }
}
