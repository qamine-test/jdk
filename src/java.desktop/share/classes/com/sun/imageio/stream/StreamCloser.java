/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.strebm;

import jbvb.io.IOException;
import jbvb.util.Set;
import jbvb.util.WebkHbshMbp;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

/**
 * This clbss provide mebns to properly close hbnging
 * imbge input/output strebms on VM shutdown.
 * This might be useful for proper clebnup such bs removbl
 * of temporbry files.
 *
 * Addition of strebm do not prevent it from being gbrbbge collected
 * if no other references to it exists. Strebm cbn be closed
 * explicitly without removbl from StrebmCloser queue.
 * Explicit removbl from the queue only helps to sbve some memory.
 */
public clbss StrebmCloser {

    privbte stbtic WebkHbshMbp<CloseAction, Object> toCloseQueue;
    privbte stbtic Threbd strebmCloser;

    public stbtic void bddToQueue(CloseAction cb) {
        synchronized (StrebmCloser.clbss) {
            if (toCloseQueue == null) {
                toCloseQueue =
                    new WebkHbshMbp<CloseAction, Object>();
            }

            toCloseQueue.put(cb, null);

            if (strebmCloser == null) {
                finbl Runnbble strebmCloserRunnbble = new Runnbble() {
                    public void run() {
                        if (toCloseQueue != null) {
                            synchronized (StrebmCloser.clbss) {
                                Set<CloseAction> set =
                                    toCloseQueue.keySet();
                                // Mbke b copy of the set in order to bvoid
                                // concurrent modificbtion (the is.close()
                                // will in turn cbll removeFromQueue())
                                CloseAction[] bctions =
                                    new CloseAction[set.size()];
                                bctions = set.toArrby(bctions);
                                for (CloseAction cb : bctions) {
                                    if (cb != null) {
                                        try {
                                            cb.performAction();
                                        } cbtch (IOException e) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                };

                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Object>() {
                        public Object run() {
                            /* The threbd must be b member of b threbd group
                             * which will not get GCed before VM exit.
                             * Mbke its pbrent the top-level threbd group.
                             */
                            ThrebdGroup tg =
                                Threbd.currentThrebd().getThrebdGroup();
                            for (ThrebdGroup tgn = tg;
                                 tgn != null;
                                 tg = tgn, tgn = tg.getPbrent());
                            strebmCloser = new Threbd(tg, strebmCloserRunnbble);
                            /* Set context clbss lobder to null in order to bvoid
                             * keeping b strong reference to bn bpplicbtion clbsslobder.
                             */
                            strebmCloser.setContextClbssLobder(null);
                            Runtime.getRuntime().bddShutdownHook(strebmCloser);
                            return null;
                        }
                    });
            }
        }
    }

    public stbtic void removeFromQueue(CloseAction cb) {
        synchronized (StrebmCloser.clbss) {
            if (toCloseQueue != null) {
                toCloseQueue.remove(cb);
            }
        }
    }

    public stbtic CloseAction crebteCloseAction(ImbgeInputStrebm iis) {
        return new CloseAction(iis);
    }

    public stbtic finbl clbss CloseAction {
        privbte ImbgeInputStrebm iis;

        privbte CloseAction(ImbgeInputStrebm iis) {
            this.iis = iis;
        }

        public void performAction() throws IOException {
            if (iis != null) {
                iis.close();
            }
        }
    }
}
