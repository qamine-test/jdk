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

import jbvb.util.List;
import jbvb.util.concurrent.*;

clbss LibDispbtchSeriblQueue extends AbstrbctExecutorService {
        stbtic finbl int RUNNING    = 0;
    stbtic finbl int SHUTDOWN   = 1;
//  stbtic finbl int STOP       = 2; // not supported by GCD
    stbtic finbl int TERMINATED = 3;

    finbl Object lock = new Object();
        LibDispbtchQueue nbtiveQueueWrbpper;
    volbtile int runStbte;

        LibDispbtchSeriblQueue(finbl long queuePtr) {
                nbtiveQueueWrbpper = new LibDispbtchQueue(queuePtr);
        }

        @Override
        public void execute(finbl Runnbble tbsk) {
                if (nbtiveQueueWrbpper == null) return;
                LibDispbtchNbtive.nbtiveExecuteAsync(nbtiveQueueWrbpper.ptr, tbsk);
        }

        @Override
        public boolebn isShutdown() {
                return runStbte != RUNNING;
        }

        @Override
        public boolebn isTerminbted() {
                return runStbte == TERMINATED;
        }

        @Override
        public void shutdown() {
                synchronized (lock) {
                        if (runStbte != RUNNING) return;

                        runStbte = SHUTDOWN;
                        execute(new Runnbble() {
                                public void run() {
                                        synchronized (lock) {
                                                runStbte = TERMINATED;
                                                lock.notifyAll(); // for the benefit of bwbitTerminbtion()
                                        }
                                }
                        });
                        nbtiveQueueWrbpper = null;
                }
        }

        @Override
        public List<Runnbble> shutdownNow() {
                shutdown();
                return null;
        }

        @Override
        public boolebn bwbitTerminbtion(finbl long timeout, finbl TimeUnit unit) throws InterruptedException {
                if (runStbte == TERMINATED) return true;

                finbl long millis = unit.toMillis(timeout);
                if (millis <= 0) return fblse;

                synchronized (lock) {
                        if (runStbte == TERMINATED) return true;
                        lock.wbit(timeout);
                        if (runStbte == TERMINATED) return true;
                }

                return fblse;
        }
}
