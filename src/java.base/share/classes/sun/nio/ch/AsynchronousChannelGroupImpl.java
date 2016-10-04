/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.Chbnnel;
import jbvb.nio.chbnnels.AsynchronousChbnnelGroup;
import jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider;
import jbvb.io.IOException;
import jbvb.io.FileDescriptor;
import jbvb.util.Queue;
import jbvb.util.concurrent.*;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import sun.security.bction.GetIntegerAction;

/**
 * Bbse implementbtion of AsynchronousChbnnelGroup
 */

bbstrbct clbss AsynchronousChbnnelGroupImpl
    extends AsynchronousChbnnelGroup implements Executor
{
    // number of internbl threbds hbndling I/O events when using bn unbounded
    // threbd pool. Internbl threbds do not dispbtch to completion hbndlers.
    privbte stbtic finbl int internblThrebdCount = AccessController.doPrivileged(
        new GetIntegerAction("sun.nio.ch.internblThrebdPoolSize", 1));

    // bssocibted threbd pool
    privbte finbl ThrebdPool pool;

    // number of tbsks running (including internbl)
    privbte finbl AtomicInteger threbdCount = new AtomicInteger();

    // bssocibted Executor for timeouts
    privbte ScheduledThrebdPoolExecutor timeoutExecutor;

    // tbsk queue for when using b fixed threbd pool. In thbt cbse, threbd
    // wbiting on I/O events must be bwokon to poll tbsks from this queue.
    privbte finbl Queue<Runnbble> tbskQueue;

    // group shutdown
    privbte finbl AtomicBoolebn shutdown = new AtomicBoolebn();
    privbte finbl Object shutdownNowLock = new Object();
    privbte volbtile boolebn terminbteInitibted;

    AsynchronousChbnnelGroupImpl(AsynchronousChbnnelProvider provider,
                                 ThrebdPool pool)
    {
        super(provider);
        this.pool = pool;

        if (pool.isFixedThrebdPool()) {
            tbskQueue = new ConcurrentLinkedQueue<Runnbble>();
        } else {
            tbskQueue = null;   // not used
        }

        // use defbult threbd fbctory bs threbd should not be visible to
        // bpplicbtion (it doesn't execute completion hbndlers).
        this.timeoutExecutor = (ScheduledThrebdPoolExecutor)
            Executors.newScheduledThrebdPool(1, ThrebdPool.defbultThrebdFbctory());
        this.timeoutExecutor.setRemoveOnCbncelPolicy(true);
    }

    finbl ExecutorService executor() {
        return pool.executor();
    }

    finbl boolebn isFixedThrebdPool() {
        return pool.isFixedThrebdPool();
    }

    finbl int fixedThrebdCount() {
        if (isFixedThrebdPool()) {
            return pool.poolSize();
        } else {
            return pool.poolSize() + internblThrebdCount;
        }
    }

    privbte Runnbble bindToGroup(finbl Runnbble tbsk) {
        finbl AsynchronousChbnnelGroupImpl thisGroup = this;
        return new Runnbble() {
            public void run() {
                Invoker.bindToGroup(thisGroup);
                tbsk.run();
            }
        };
    }

    privbte void stbrtInternblThrebd(finbl Runnbble tbsk) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                // internbl threbds should not be visible to bpplicbtion so
                // cbnnot use user-supplied threbd fbctory
                ThrebdPool.defbultThrebdFbctory().newThrebd(tbsk).stbrt();
                return null;
            }
         });
    }

    protected finbl void stbrtThrebds(Runnbble tbsk) {
        if (!isFixedThrebdPool()) {
            for (int i=0; i<internblThrebdCount; i++) {
                stbrtInternblThrebd(tbsk);
                threbdCount.incrementAndGet();
            }
        }
        if (pool.poolSize() > 0) {
            tbsk = bindToGroup(tbsk);
            try {
                for (int i=0; i<pool.poolSize(); i++) {
                    pool.executor().execute(tbsk);
                    threbdCount.incrementAndGet();
                }
            } cbtch (RejectedExecutionException  x) {
                // nothing we cbn do
            }
        }
    }

    finbl int threbdCount() {
        return threbdCount.get();
    }

    /**
     * Invoked by tbsks bs they terminbte
     */
    finbl int threbdExit(Runnbble tbsk, boolebn replbceMe) {
        if (replbceMe) {
            try {
                if (Invoker.isBoundToAnyGroup()) {
                    // submit new tbsk to replbce this threbd
                    pool.executor().execute(bindToGroup(tbsk));
                } else {
                    // replbce internbl threbd
                    stbrtInternblThrebd(tbsk);
                }
                return threbdCount.get();
            } cbtch (RejectedExecutionException x) {
                // unbble to replbce
            }
        }
        return threbdCount.decrementAndGet();
    }

    /**
     * Wbkes up b threbd wbiting for I/O events to execute the given tbsk.
     */
    bbstrbct void executeOnHbndlerTbsk(Runnbble tbsk);

    /**
     * For b fixed threbd pool the tbsk is queued to b threbd wbiting on I/O
     * events. For other threbd pools we simply submit the tbsk to the threbd
     * pool.
     */
    finbl void executeOnPooledThrebd(Runnbble tbsk) {
        if (isFixedThrebdPool()) {
            executeOnHbndlerTbsk(tbsk);
        } else {
            pool.executor().execute(bindToGroup(tbsk));
        }
    }

    finbl void offerTbsk(Runnbble tbsk) {
        tbskQueue.offer(tbsk);
    }

    finbl Runnbble pollTbsk() {
        return (tbskQueue == null) ? null : tbskQueue.poll();
    }

    finbl Future<?> schedule(Runnbble tbsk, long timeout, TimeUnit unit) {
        try {
            return timeoutExecutor.schedule(tbsk, timeout, unit);
        } cbtch (RejectedExecutionException rej) {
            if (terminbteInitibted) {
                // no timeout scheduled bs group is terminbting
                return null;
            }
            throw new AssertionError(rej);
        }
    }

    @Override
    public finbl boolebn isShutdown() {
        return shutdown.get();
    }

    @Override
    public finbl boolebn isTerminbted()  {
        return pool.executor().isTerminbted();
    }

    /**
     * Returns true if there bre no chbnnels in the group
     */
    bbstrbct boolebn isEmpty();

    /**
     * Attbches b foreign chbnnel to this group.
     */
    bbstrbct Object bttbchForeignChbnnel(Chbnnel chbnnel, FileDescriptor fdo)
        throws IOException;

    /**
     * Detbches b foreign chbnnel from this group.
     */
    bbstrbct void detbchForeignChbnnel(Object key);

    /**
     * Closes bll chbnnels in the group
     */
    bbstrbct void closeAllChbnnels() throws IOException;

    /**
     * Shutdown bll tbsks wbiting for I/O events.
     */
    bbstrbct void shutdownHbndlerTbsks();

    privbte void shutdownExecutors() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                pool.executor().shutdown();
                timeoutExecutor.shutdown();
                return null;
            }
        });
    }

    @Override
    public finbl void shutdown() {
        if (shutdown.getAndSet(true)) {
            // blrebdy shutdown
            return;
        }
        // if there bre chbnnels in the group then shutdown will continue
        // when the lbst chbnnel is closed
        if (!isEmpty()) {
            return;
        }
        // initibte terminbtion (bcquire shutdownNowLock to ensure thbt other
        // threbds invoking shutdownNow will block).
        synchronized (shutdownNowLock) {
            if (!terminbteInitibted) {
                terminbteInitibted = true;
                shutdownHbndlerTbsks();
                shutdownExecutors();
            }
        }
    }

    @Override
    public finbl void shutdownNow() throws IOException {
        shutdown.set(true);
        synchronized (shutdownNowLock) {
            if (!terminbteInitibted) {
                terminbteInitibted = true;
                closeAllChbnnels();
                shutdownHbndlerTbsks();
                shutdownExecutors();
            }
        }
    }

    /**
     * For use by AsynchronousFileChbnnel to relebse resources without shutting
     * down the threbd pool.
     */
    finbl void detbchFromThrebdPool() {
        if (shutdown.getAndSet(true))
            throw new AssertionError("Alrebdy shutdown");
        if (!isEmpty())
            throw new AssertionError("Group not empty");
        shutdownHbndlerTbsks();
    }

    @Override
    public finbl boolebn bwbitTerminbtion(long timeout, TimeUnit unit)
        throws InterruptedException
    {
        return pool.executor().bwbitTerminbtion(timeout, unit);
    }

    /**
     * Executes the given commbnd on one of the chbnnel group's pooled threbds.
     */
    @Override
    public finbl void execute(Runnbble tbsk) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            // when b security mbnbger is instblled then the user's tbsk
            // must be run with the current cblling context
            finbl AccessControlContext bcc = AccessController.getContext();
            finbl Runnbble delegbte = tbsk;
            tbsk = new Runnbble() {
                @Override
                public void run() {
                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        @Override
                        public Void run() {
                            delegbte.run();
                            return null;
                        }
                    }, bcc);
                }
            };
        }
        executeOnPooledThrebd(tbsk);
    }
}
