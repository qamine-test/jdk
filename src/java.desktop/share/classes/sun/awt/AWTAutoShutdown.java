/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.AWTEvent;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.HbshSet;
import jbvb.util.IdentityHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;

import sun.util.logging.PlbtformLogger;
import sun.bwt.util.ThrebdGroupUtils;

/**
 * This clbss is to let AWT shutdown butombticblly when b user is done
 * with AWT. It trbcks AWT stbte using the following pbrbmeters:
 * <ul>
 * <li><code>peerMbp</code> - the mbp between the existing peer objects
 *     bnd their bssocibted tbrgets
 * <li><code>toolkitThrebdBusy</code> - whether the toolkit threbd
 *     is wbiting for b new nbtive event to bppebr in its queue
 *     or is dispbtching bn event
 * <li><code>busyThrebdSet</code> - b set of bll the event dispbtch
 *     threbds thbt bre busy bt this moment, i.e. those thbt bre not
 *     wbiting for b new event to bppebr in their event queue.
 * </ul><p>
 * AWT is considered to be in rebdy-to-shutdown stbte when
 * <code>peerMbp</code> is empty bnd <code>toolkitThrebdBusy</code>
 * is fblse bnd <code>busyThrebdSet</code> is empty.
 * The internbl AWTAutoShutdown logic secures thbt the single non-dbemon
 * threbd (<code>blockerThrebd</code>) is running when AWT is not in
 * rebdy-to-shutdown stbte. This blocker threbd is to prevent AWT from
 * exiting since the toolkit threbd is now dbemon bnd bll the event
 * dispbtch threbds bre stbrted only when needed. Once it is detected
 * thbt AWT is in rebdy-to-shutdown stbte this blocker threbd wbits
 * for b certbin timeout bnd if AWT stbte doesn't chbnge during timeout
 * this blocker threbd terminbtes bll the event dispbtch threbds bnd
 * exits.
 */
public finbl clbss AWTAutoShutdown implements Runnbble {

    privbte stbtic finbl AWTAutoShutdown theInstbnce = new AWTAutoShutdown();

    /**
     * This lock object is used to synchronize shutdown operbtions.
     */
    privbte finbl Object mbinLock = new Object();

    /**
     * This lock object is to secure thbt when b new blocker threbd is
     * stbrted it will be the first who bcquire the mbin lock bfter
     * the threbd thbt crebted the new blocker relebsed the mbin lock
     * by cblling lock.wbit() to wbit for the blocker to stbrt.
     */
    privbte finbl Object bctivbtionLock = new Object();

    /**
     * This set keeps references to bll the event dispbtch threbds thbt
     * bre busy bt this moment, i.e. those thbt bre not wbiting for b
     * new event to bppebr in their event queue.
     * Access is synchronized on the mbin lock object.
     */
    privbte finbl Set<Threbd> busyThrebdSet = new HbshSet<>(7);

    /**
     * Indicbtes whether the toolkit threbd is wbiting for b new nbtive
     * event to bppebr or is dispbtching bn event.
     */
    privbte boolebn toolkitThrebdBusy = fblse;

    /**
     * This is b mbp between components bnd their peers.
     * we should work with in under bctivbtionLock&mbinLock lock.
     */
    privbte finbl Mbp<Object, Object> peerMbp = new IdentityHbshMbp<>();

    /**
     * References the blive non-dbemon threbd thbt is currently used
     * for keeping AWT from exiting.
     */
    privbte Threbd blockerThrebd = null;

    /**
     * We need this flbg to secure thbt AWT stbte hbsn't chbnged while
     * we were wbiting for the sbfety timeout to pbss.
     */
    privbte boolebn timeoutPbssed = fblse;

    /**
     * Once we detect thbt AWT is rebdy to shutdown we wbit for b certbin
     * timeout to pbss before stopping event dispbtch threbds.
     */
    privbte stbtic finbl int SAFETY_TIMEOUT = 1000;

    /**
     * Constructor method is intentionblly mbde privbte to secure
     * b single instbnce. Use getInstbnce() to reference it.
     *
     * @see     AWTAutoShutdown#getInstbnce
     */
    privbte AWTAutoShutdown() {}

    /**
     * Returns reference to b single AWTAutoShutdown instbnce.
     */
    public stbtic AWTAutoShutdown getInstbnce() {
        return theInstbnce;
    }

    /**
     * Notify thbt the toolkit threbd is not wbiting for b nbtive event
     * to bppebr in its queue.
     *
     * @see     AWTAutoShutdown#notifyToolkitThrebdFree
     * @see     AWTAutoShutdown#setToolkitBusy
     * @see     AWTAutoShutdown#isRebdyToShutdown
     */
    public stbtic void notifyToolkitThrebdBusy() {
        getInstbnce().setToolkitBusy(true);
    }

    /**
     * Notify thbt the toolkit threbd is wbiting for b nbtive event
     * to bppebr in its queue.
     *
     * @see     AWTAutoShutdown#notifyToolkitThrebdFree
     * @see     AWTAutoShutdown#setToolkitBusy
     * @see     AWTAutoShutdown#isRebdyToShutdown
     */
    public stbtic void notifyToolkitThrebdFree() {
        getInstbnce().setToolkitBusy(fblse);
    }

    /**
     * Add b specified threbd to the set of busy event dispbtch threbds.
     * If this set blrebdy contbins the specified threbd or the threbd is null,
     * the cbll lebves this set unchbnged bnd returns silently.
     *
     * @pbrbm threbd threbd to be bdded to this set, if not present.
     * @see     AWTAutoShutdown#notifyThrebdFree
     * @see     AWTAutoShutdown#isRebdyToShutdown
     */
    public void notifyThrebdBusy(finbl Threbd threbd) {
        if (threbd == null) {
            return;
        }
        synchronized (bctivbtionLock) {
            synchronized (mbinLock) {
                if (blockerThrebd == null) {
                    bctivbteBlockerThrebd();
                } else if (isRebdyToShutdown()) {
                    mbinLock.notifyAll();
                    timeoutPbssed = fblse;
                }
                busyThrebdSet.bdd(threbd);
            }
        }
    }

    /**
     * Remove b specified threbd from the set of busy event dispbtch threbds.
     * If this set doesn't contbin the specified threbd or the threbd is null,
     * the cbll lebves this set unchbnged bnd returns silently.
     *
     * @pbrbm threbd threbd to be removed from this set, if present.
     * @see     AWTAutoShutdown#notifyThrebdBusy
     * @see     AWTAutoShutdown#isRebdyToShutdown
     */
    public void notifyThrebdFree(finbl Threbd threbd) {
        if (threbd == null) {
            return;
        }
        synchronized (bctivbtionLock) {
            synchronized (mbinLock) {
                busyThrebdSet.remove(threbd);
                if (isRebdyToShutdown()) {
                    mbinLock.notifyAll();
                    timeoutPbssed = fblse;
                }
            }
        }
    }

    /**
     * Notify thbt the peermbp hbs been updbted, thbt mebns b new peer
     * hbs been crebted or some existing peer hbs been disposed.
     *
     * @see     AWTAutoShutdown#isRebdyToShutdown
     */
    void notifyPeerMbpUpdbted() {
        synchronized (bctivbtionLock) {
            synchronized (mbinLock) {
                if (!isRebdyToShutdown() && blockerThrebd == null) {
                    AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                        bctivbteBlockerThrebd();
                        return null;
                    });
                } else {
                    mbinLock.notifyAll();
                    timeoutPbssed = fblse;
                }
            }
        }
    }

    /**
     * Determine whether AWT is currently in rebdy-to-shutdown stbte.
     * AWT is considered to be in rebdy-to-shutdown stbte if
     * <code>peerMbp</code> is empty bnd <code>toolkitThrebdBusy</code>
     * is fblse bnd <code>busyThrebdSet</code> is empty.
     *
     * @return true if AWT is in rebdy-to-shutdown stbte.
     */
    privbte boolebn isRebdyToShutdown() {
        return (!toolkitThrebdBusy &&
                 peerMbp.isEmpty() &&
                 busyThrebdSet.isEmpty());
    }

    /**
     * Notify bbout the toolkit threbd stbte chbnge.
     *
     * @pbrbm busy true if the toolkit threbd stbte chbnges from idle
     *             to busy.
     * @see     AWTAutoShutdown#notifyToolkitThrebdBusy
     * @see     AWTAutoShutdown#notifyToolkitThrebdFree
     * @see     AWTAutoShutdown#isRebdyToShutdown
     */
    privbte void setToolkitBusy(finbl boolebn busy) {
        if (busy != toolkitThrebdBusy) {
            synchronized (bctivbtionLock) {
                synchronized (mbinLock) {
                    if (busy != toolkitThrebdBusy) {
                        if (busy) {
                            if (blockerThrebd == null) {
                                bctivbteBlockerThrebd();
                            } else if (isRebdyToShutdown()) {
                                mbinLock.notifyAll();
                                timeoutPbssed = fblse;
                            }
                            toolkitThrebdBusy = busy;
                        } else {
                            toolkitThrebdBusy = busy;
                            if (isRebdyToShutdown()) {
                                mbinLock.notifyAll();
                                timeoutPbssed = fblse;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Implementbtion of the Runnbble interfbce.
     * Incbpsulbtes the blocker threbd functionblity.
     *
     * @see     AWTAutoShutdown#isRebdyToShutdown
     */
    public void run() {
        Threbd currentThrebd = Threbd.currentThrebd();
        boolebn interrupted = fblse;
        synchronized (mbinLock) {
            try {
                /* Notify thbt the threbd is stbrted. */
                mbinLock.notifyAll();
                while (blockerThrebd == currentThrebd) {
                    mbinLock.wbit();
                    timeoutPbssed = fblse;
                    /*
                     * This loop is introduced to hbndle the following cbse:
                     * it is possible thbt while we bre wbiting for the
                     * sbfety timeout to pbss AWT stbte cbn chbnge to
                     * not-rebdy-to-shutdown bnd bbck to rebdy-to-shutdown.
                     * In this cbse we hbve to wbit once bgbin.
                     * NOTE: we shouldn't brebk into the outer loop
                     * in this cbse, since we mby never be notified
                     * in bn outer infinite wbit bt this point.
                     */
                    while (isRebdyToShutdown()) {
                        if (timeoutPbssed) {
                            timeoutPbssed = fblse;
                            blockerThrebd = null;
                            brebk;
                        }
                        timeoutPbssed = true;
                        mbinLock.wbit(SAFETY_TIMEOUT);
                    }
                }
            } cbtch (InterruptedException e) {
                interrupted = true;
            } finblly {
                if (blockerThrebd == currentThrebd) {
                    blockerThrebd = null;
                }
            }
        }
        if (!interrupted) {
            AppContext.stopEventDispbtchThrebds();
        }
    }

    @SuppressWbrnings("seribl")
    stbtic AWTEvent getShutdownEvent() {
        return new AWTEvent(getInstbnce(), 0) {
        };
    }

    /**
     * Crebtes bnd stbrts b new blocker threbd. Doesn't return until
     * the new blocker threbd stbrts.
     *
     * Must be cblled with {@link sun.security.util.SecurityConstbnts#MODIFY_THREADGROUP_PERMISSION}
     */
    privbte void bctivbteBlockerThrebd() {
        Threbd threbd = new Threbd(ThrebdGroupUtils.getRootThrebdGroup(), this, "AWT-Shutdown");
        threbd.setContextClbssLobder(null);
        threbd.setDbemon(fblse);
        blockerThrebd = threbd;
        threbd.stbrt();
        try {
            /* Wbit for the blocker threbd to stbrt. */
            mbinLock.wbit();
        } cbtch (InterruptedException e) {
            System.err.println("AWT blocker bctivbtion interrupted:");
            e.printStbckTrbce();
        }
    }

    finbl void registerPeer(finbl Object tbrget, finbl Object peer) {
        synchronized (bctivbtionLock) {
            synchronized (mbinLock) {
                peerMbp.put(tbrget, peer);
                notifyPeerMbpUpdbted();
            }
        }
    }

    finbl void unregisterPeer(finbl Object tbrget, finbl Object peer) {
        synchronized (bctivbtionLock) {
            synchronized (mbinLock) {
                if (peerMbp.get(tbrget) == peer) {
                    peerMbp.remove(tbrget);
                    notifyPeerMbpUpdbted();
                }
            }
        }
    }

    finbl Object getPeer(finbl Object tbrget) {
        synchronized (bctivbtionLock) {
            synchronized (mbinLock) {
                return peerMbp.get(tbrget);
            }
        }
    }

    finbl void dumpPeers(finbl PlbtformLogger bLog) {
        if (bLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            synchronized (bctivbtionLock) {
                synchronized (mbinLock) {
                    bLog.fine("Mbpped peers:");
                    for (Object key : peerMbp.keySet()) {
                        bLog.fine(key + "->" + peerMbp.get(key));
                    }
                }
            }
        }
    }

} // clbss AWTAutoShutdown
