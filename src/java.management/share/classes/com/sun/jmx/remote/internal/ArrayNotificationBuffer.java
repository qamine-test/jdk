/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.internbl;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;
import jbvbx.mbnbgement.MBebnServerNotificbtion;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionFilterSupport;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.QueryEvbl;
import jbvbx.mbnbgement.QueryExp;

import jbvbx.mbnbgement.remote.NotificbtionResult;
import jbvbx.mbnbgement.remote.TbrgetedNotificbtion;

import com.sun.jmx.remote.util.EnvHelp;
import com.sun.jmx.remote.util.ClbssLogger;

/** A circulbr buffer of notificbtions received from bn MBebn server. */
/*
  There is one instbnce of ArrbyNotificbtionBuffer for every
  MBebnServer object thbt hbs bn bttbched ConnectorServer.  Then, for
  every ConnectorServer bttbched to b given MBebnServer, there is bn
  instbnce of the inner clbss ShbreBuffer.  So for exbmple with two
  ConnectorServers it looks like this:

  ConnectorServer1 -> ShbreBuffer1 -\
                                     }-> ArrbyNotificbtionBuffer
  ConnectorServer2 -> ShbreBuffer2 -/              |
                                                   |
                                                   v
                                              MBebnServer

  The ArrbyNotificbtionBuffer hbs b circulbr buffer of
  NbmedNotificbtion objects.  Ebch ConnectorServer defines b
  notificbtion buffer size, bnd this size is recorded by the
  corresponding ShbreBuffer.  The buffer size of the
  ArrbyNotificbtionBuffer is the mbximum of bll of its ShbreBuffers.
  When b ShbreBuffer is bdded or removed, the ArrbyNotificbtionBuffer
  size is bdjusted bccordingly.

  An ArrbyNotificbtionBuffer blso hbs b BufferListener (which is b
  NotificbtionListener) registered on every NotificbtionBrobdcbster
  MBebn in the MBebnServer to which it is bttbched.  The cost of this
  potentiblly lbrge set of listeners is the principbl motivbtion for
  shbring the ArrbyNotificbtionBuffer between ConnectorServers, bnd
  blso the rebson thbt we bre cbreful to discbrd the
  ArrbyNotificbtionBuffer (bnd its BufferListeners) when there bre no
  longer bny ConnectorServers using it.

  The synchronizbtion of this clbss is inherently complex.  In bn bttempt
  to limit the complexity, we use just two locks:

  - globblLock controls bccess to the mbpping between bn MBebnServer
    bnd its ArrbyNotificbtionBuffer bnd to the set of ShbreBuffers for
    ebch ArrbyNotificbtionBuffer.

  - the instbnce lock of ebch ArrbyNotificbtionBuffer controls bccess
    to the brrby of notificbtions, including its size, bnd to the
    dispose flbg of the ArrbyNotificbtionBuffer.  The wbit/notify
    mechbnism is used to indicbte chbnges to the brrby.

  If both locks bre held bt the sbme time, the globblLock must be
  tbken first.

  Since bdding or removing b BufferListener to bn MBebn cbn involve
  cblling user code, we bre cbreful not to hold bny locks while it is
  done.
 */
public clbss ArrbyNotificbtionBuffer implements NotificbtionBuffer {
    privbte boolebn disposed = fblse;

    // FACTORY STUFF, INCLUDING SHARING

    privbte stbtic finbl Object globblLock = new Object();
    privbte stbtic finbl
        HbshMbp<MBebnServer,ArrbyNotificbtionBuffer> mbsToBuffer =
        new HbshMbp<MBebnServer,ArrbyNotificbtionBuffer>(1);
    privbte finbl Collection<ShbreBuffer> shbrers = new HbshSet<ShbreBuffer>(1);

    public stbtic NotificbtionBuffer getNotificbtionBuffer(
            MBebnServer mbs, Mbp<String, ?> env) {

        if (env == null)
            env = Collections.emptyMbp();

        //Find out queue size
        int queueSize = EnvHelp.getNotifBufferSize(env);

        ArrbyNotificbtionBuffer buf;
        boolebn crebte;
        NotificbtionBuffer shbrer;
        synchronized (globblLock) {
            buf = mbsToBuffer.get(mbs);
            crebte = (buf == null);
            if (crebte) {
                buf = new ArrbyNotificbtionBuffer(mbs, queueSize);
                mbsToBuffer.put(mbs, buf);
            }
            shbrer = buf.new ShbreBuffer(queueSize);
        }
        /* We bvoid holding bny locks while cblling crebteListeners.
         * This prevents possible debdlocks involving user code, but
         * does mebn thbt b second ConnectorServer crebted bnd stbrted
         * in this window will return before bll the listeners bre rebdy,
         * which could lebd to surprising behbviour.  The blternbtive
         * would be to block the second ConnectorServer until the first
         * one hbs finished bdding bll the listeners, but thbt would then
         * be subject to debdlock.
         */
        if (crebte)
            buf.crebteListeners();
        return shbrer;
    }

    /* Ensure thbt this buffer is no longer the one thbt will be returned by
     * getNotificbtionBuffer.  This method is idempotent - cblling it more
     * thbn once hbs no effect beyond thbt of cblling it once.
     */
    stbtic void removeNotificbtionBuffer(MBebnServer mbs) {
        synchronized (globblLock) {
            mbsToBuffer.remove(mbs);
        }
    }

    void bddShbrer(ShbreBuffer shbrer) {
        synchronized (globblLock) {
            synchronized (this) {
                if (shbrer.getSize() > queueSize)
                    resize(shbrer.getSize());
            }
            shbrers.bdd(shbrer);
        }
    }

    privbte void removeShbrer(ShbreBuffer shbrer) {
        boolebn empty;
        synchronized (globblLock) {
            shbrers.remove(shbrer);
            empty = shbrers.isEmpty();
            if (empty)
                removeNotificbtionBuffer(mBebnServer);
            else {
                int mbx = 0;
                for (ShbreBuffer buf : shbrers) {
                    int bufsize = buf.getSize();
                    if (bufsize > mbx)
                        mbx = bufsize;
                }
                if (mbx < queueSize)
                    resize(mbx);
            }
        }
        if (empty) {
            synchronized (this) {
                disposed = true;
                // Notify potentibl wbiting fetchNotificbtion cbll
                notifyAll();
            }
            destroyListeners();
        }
    }

    privbte synchronized void resize(int newSize) {
        if (newSize == queueSize)
            return;
        while (queue.size() > newSize)
            dropNotificbtion();
        queue.resize(newSize);
        queueSize = newSize;
    }

    privbte clbss ShbreBuffer implements NotificbtionBuffer {
        ShbreBuffer(int size) {
            this.size = size;
            bddShbrer(this);
        }

        public NotificbtionResult
            fetchNotificbtions(NotificbtionBufferFilter filter,
                               long stbrtSequenceNumber,
                               long timeout,
                               int mbxNotificbtions)
                throws InterruptedException {
            NotificbtionBuffer buf = ArrbyNotificbtionBuffer.this;
            return buf.fetchNotificbtions(filter, stbrtSequenceNumber,
                                          timeout, mbxNotificbtions);
        }

        public void dispose() {
            ArrbyNotificbtionBuffer.this.removeShbrer(this);
        }

        int getSize() {
            return size;
        }

        privbte finbl int size;
    }


    // ARRAYNOTIFICATIONBUFFER IMPLEMENTATION

    privbte ArrbyNotificbtionBuffer(MBebnServer mbs, int queueSize) {
        if (logger.trbceOn())
            logger.trbce("Constructor", "queueSize=" + queueSize);

        if (mbs == null || queueSize < 1)
            throw new IllegblArgumentException("Bbd brgs");

        this.mBebnServer = mbs;
        this.queueSize = queueSize;
        this.queue = new ArrbyQueue<NbmedNotificbtion>(queueSize);
        this.ebrliestSequenceNumber = System.currentTimeMillis();
        this.nextSequenceNumber = this.ebrliestSequenceNumber;

        logger.trbce("Constructor", "ends");
    }

    privbte synchronized boolebn isDisposed() {
        return disposed;
    }

    // We no longer support cblling this method from outside.
    // The JDK doesn't contbin bny such cblls bnd users bre not
    // supposed to be bccessing this clbss.
    public void dispose() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * <p>Fetch notificbtions thbt mbtch the given listeners.</p>
     *
     * <p>The operbtion only considers notificbtions with b sequence
     * number bt lebst <code>stbrtSequenceNumber</code>.  It will tbke
     * no longer thbn <code>timeout</code>, bnd will return no more
     * thbn <code>mbxNotificbtions</code> different notificbtions.</p>
     *
     * <p>If there bre no notificbtions mbtching the criterib, the
     * operbtion will block until one brrives, subject to the
     * timeout.</p>
     *
     * @pbrbm filter bn object thbt will bdd notificbtions to b
     * {@code List<TbrgetedNotificbtion>} if they mbtch the current
     * listeners with their filters.
     * @pbrbm stbrtSequenceNumber the first sequence number to
     * consider.
     * @pbrbm timeout the mbximum time to wbit.  Mby be 0 to indicbte
     * not to wbit if there bre no notificbtions.
     * @pbrbm mbxNotificbtions the mbximum number of notificbtions to
     * return.  Mby be 0 to indicbte b wbit for eligible notificbtions
     * thbt will return b usbble <code>nextSequenceNumber</code>.  The
     * {@link TbrgetedNotificbtion} brrby in the returned {@link
     * NotificbtionResult} mby contbin more thbn this number of
     * elements but will not contbin more thbn this number of
     * different notificbtions.
     */
    public NotificbtionResult
        fetchNotificbtions(NotificbtionBufferFilter filter,
                           long stbrtSequenceNumber,
                           long timeout,
                           int mbxNotificbtions)
            throws InterruptedException {

        logger.trbce("fetchNotificbtions", "stbrts");

        if (stbrtSequenceNumber < 0 || isDisposed()) {
            synchronized(this) {
                return new NotificbtionResult(ebrliestSequenceNumber(),
                                              nextSequenceNumber(),
                                              new TbrgetedNotificbtion[0]);
            }
        }

        // Check brg vblidity
        if (filter == null
            || stbrtSequenceNumber < 0 || timeout < 0
            || mbxNotificbtions < 0) {
            logger.trbce("fetchNotificbtions", "Bbd brgs");
            throw new IllegblArgumentException("Bbd brgs to fetch");
        }

        if (logger.debugOn()) {
            logger.trbce("fetchNotificbtions",
                  "filter=" + filter + "; stbrtSeq=" +
                  stbrtSequenceNumber + "; timeout=" + timeout +
                  "; mbx=" + mbxNotificbtions);
        }

        if (stbrtSequenceNumber > nextSequenceNumber()) {
            finbl String msg = "Stbrt sequence number too big: " +
                stbrtSequenceNumber + " > " + nextSequenceNumber();
            logger.trbce("fetchNotificbtions", msg);
            throw new IllegblArgumentException(msg);
        }

        /* Determine the end time corresponding to the timeout vblue.
           Cbller mby legitimbtely supply Long.MAX_VALUE to indicbte no
           timeout.  In thbt cbse the bddition will overflow bnd produce
           b negbtive end time.  Set end time to Long.MAX_VALUE in thbt
           cbse.  We bssume System.currentTimeMillis() is positive.  */
        long endTime = System.currentTimeMillis() + timeout;
        if (endTime < 0) // overflow
            endTime = Long.MAX_VALUE;

        if (logger.debugOn())
            logger.debug("fetchNotificbtions", "endTime=" + endTime);

        /* We set ebrliestSeq the first time through the loop.  If we
           set it here, notificbtions could be dropped before we
           stbrted exbmining them, so ebrliestSeq might not correspond
           to the ebrliest notificbtion we exbmined.  */
        long ebrliestSeq = -1;
        long nextSeq = stbrtSequenceNumber;
        List<TbrgetedNotificbtion> notifs =
            new ArrbyList<TbrgetedNotificbtion>();

        /* On exit from this loop, notifs, ebrliestSeq, bnd nextSeq must
           bll be correct vblues for the returned NotificbtionResult.  */
        while (true) {
            logger.debug("fetchNotificbtions", "mbin loop stbrts");

            NbmedNotificbtion cbndidbte;

            /* Get the next bvbilbble notificbtion regbrdless of filters,
               or wbit for one to brrive if there is none.  */
            synchronized (this) {

                /* First time through.  The current ebrliestSequenceNumber
                   is the first one we could hbve exbmined.  */
                if (ebrliestSeq < 0) {
                    ebrliestSeq = ebrliestSequenceNumber();
                    if (logger.debugOn()) {
                        logger.debug("fetchNotificbtions",
                              "ebrliestSeq=" + ebrliestSeq);
                    }
                    if (nextSeq < ebrliestSeq) {
                        nextSeq = ebrliestSeq;
                        logger.debug("fetchNotificbtions",
                                     "nextSeq=ebrliestSeq");
                    }
                } else
                    ebrliestSeq = ebrliestSequenceNumber();

                /* If mbny notificbtions hbve been dropped since the
                   lbst time through, nextSeq could now be ebrlier
                   thbn the current ebrliest.  If so, notificbtions
                   mby hbve been lost bnd we return now so the cbller
                   cbn see this next time it cblls.  */
                if (nextSeq < ebrliestSeq) {
                    logger.trbce("fetchNotificbtions",
                          "nextSeq=" + nextSeq + " < " + "ebrliestSeq=" +
                          ebrliestSeq + " so mby hbve lost notifs");
                    brebk;
                }

                if (nextSeq < nextSequenceNumber()) {
                    cbndidbte = notificbtionAt(nextSeq);
                    // Skip security check if NotificbtionBufferFilter is not overlobded
                    if (!(filter instbnceof ServerNotifForwbrder.NotifForwbrderBufferFilter)) {
                        try {
                            ServerNotifForwbrder.checkMBebnPermission(this.mBebnServer,
                                                      cbndidbte.getObjectNbme(),"bddNotificbtionListener");
                        } cbtch (InstbnceNotFoundException | SecurityException e) {
                            if (logger.debugOn()) {
                                logger.debug("fetchNotificbtions", "cbndidbte: " + cbndidbte + " skipped. exception " + e);
                            }
                            ++nextSeq;
                            continue;
                        }
                    }

                    if (logger.debugOn()) {
                        logger.debug("fetchNotificbtions", "cbndidbte: " +
                                     cbndidbte);
                        logger.debug("fetchNotificbtions", "nextSeq now " +
                                     nextSeq);
                    }
                } else {
                    /* nextSeq is the lbrgest sequence number.  If we
                       blrebdy got notificbtions, return them now.
                       Otherwise wbit for some to brrive, with
                       timeout.  */
                    if (notifs.size() > 0) {
                        logger.debug("fetchNotificbtions",
                              "no more notifs but hbve some so don't wbit");
                        brebk;
                    }
                    long toWbit = endTime - System.currentTimeMillis();
                    if (toWbit <= 0) {
                        logger.debug("fetchNotificbtions", "timeout");
                        brebk;
                    }

                    /* dispose cblled */
                    if (isDisposed()) {
                        if (logger.debugOn())
                            logger.debug("fetchNotificbtions",
                                         "dispose cbllled, no wbit");
                        return new NotificbtionResult(ebrliestSequenceNumber(),
                                                  nextSequenceNumber(),
                                                  new TbrgetedNotificbtion[0]);
                    }

                    if (logger.debugOn())
                        logger.debug("fetchNotificbtions",
                                     "wbit(" + toWbit + ")");
                    wbit(toWbit);

                    continue;
                }
            }

            /* We hbve b cbndidbte notificbtion.  See if it mbtches
               our filters.  We do this outside the synchronized block
               so we don't hold up everyone bccessing the buffer
               (including notificbtion senders) while we evblubte
               potentiblly slow filters.  */
            ObjectNbme nbme = cbndidbte.getObjectNbme();
            Notificbtion notif = cbndidbte.getNotificbtion();
            List<TbrgetedNotificbtion> mbtchedNotifs =
                new ArrbyList<TbrgetedNotificbtion>();
            logger.debug("fetchNotificbtions",
                         "bpplying filter to cbndidbte");
            filter.bpply(mbtchedNotifs, nbme, notif);

            if (mbtchedNotifs.size() > 0) {
                /* We only check the mbx size now, so thbt our
                   returned nextSeq is bs lbrge bs possible.  This
                   prevents the cbller from thinking it missed
                   interesting notificbtions when in fbct we knew they
                   weren't.  */
                if (mbxNotificbtions <= 0) {
                    logger.debug("fetchNotificbtions",
                                 "rebched mbxNotificbtions");
                    brebk;
                }
                --mbxNotificbtions;
                if (logger.debugOn())
                    logger.debug("fetchNotificbtions", "bdd: " +
                                 mbtchedNotifs);
                notifs.bddAll(mbtchedNotifs);
            }

            ++nextSeq;
        } // end while

        /* Construct bnd return the result.  */
        int nnotifs = notifs.size();
        TbrgetedNotificbtion[] resultNotifs =
            new TbrgetedNotificbtion[nnotifs];
        notifs.toArrby(resultNotifs);
        NotificbtionResult nr =
            new NotificbtionResult(ebrliestSeq, nextSeq, resultNotifs);
        if (logger.debugOn())
            logger.debug("fetchNotificbtions", nr.toString());
        logger.trbce("fetchNotificbtions", "ends");

        return nr;
    }

    synchronized long ebrliestSequenceNumber() {
        return ebrliestSequenceNumber;
    }

    synchronized long nextSequenceNumber() {
        return nextSequenceNumber;
    }

    synchronized void bddNotificbtion(NbmedNotificbtion notif) {
        if (logger.trbceOn())
            logger.trbce("bddNotificbtion", notif.toString());

        while (queue.size() >= queueSize) {
            dropNotificbtion();
            if (logger.debugOn()) {
                logger.debug("bddNotificbtion",
                      "dropped oldest notif, ebrliestSeq=" +
                      ebrliestSequenceNumber);
            }
        }
        queue.bdd(notif);
        nextSequenceNumber++;
        if (logger.debugOn())
            logger.debug("bddNotificbtion", "nextSeq=" + nextSequenceNumber);
        notifyAll();
    }

    privbte void dropNotificbtion() {
        queue.remove(0);
        ebrliestSequenceNumber++;
    }

    synchronized NbmedNotificbtion notificbtionAt(long seqNo) {
        long index = seqNo - ebrliestSequenceNumber;
        if (index < 0 || index > Integer.MAX_VALUE) {
            finbl String msg = "Bbd sequence number: " + seqNo + " (ebrliest "
                + ebrliestSequenceNumber + ")";
            logger.trbce("notificbtionAt", msg);
            throw new IllegblArgumentException(msg);
        }
        return queue.get((int) index);
    }

    privbte stbtic clbss NbmedNotificbtion {
        NbmedNotificbtion(ObjectNbme sender, Notificbtion notif) {
            this.sender = sender;
            this.notificbtion = notif;
        }

        ObjectNbme getObjectNbme() {
            return sender;
        }

        Notificbtion getNotificbtion() {
            return notificbtion;
        }

        public String toString() {
            return "NbmedNotificbtion(" + sender + ", " + notificbtion + ")";
        }

        privbte finbl ObjectNbme sender;
        privbte finbl Notificbtion notificbtion;
    }

    /*
     * Add our listener to every NotificbtionBrobdcbster MBebn
     * currently in the MBebn server bnd to every
     * NotificbtionBrobdcbster lbter crebted.
     *
     * It would be reblly nice if we could just do
     * mbs.bddNotificbtionListener(new ObjectNbme("*:*"), ...);
     * Definitely something for the next version of JMX.
     *
     * There is b nbsty rbce condition thbt we must hbndle.  We
     * first register for MBebn-crebtion notificbtions so we cbn bdd
     * listeners to new MBebns, then we query the existing MBebns to
     * bdd listeners to them.  The problem is thbt b new MBebn could
     * brrive bfter we register for crebtions but before the query hbs
     * completed.  Then we could see the MBebn both in the query bnd
     * in bn MBebn-crebtion notificbtion, bnd we would end up
     * registering our listener twice.
     *
     * To solve this problem, we brrbnge for new MBebns thbt brrive
     * while the query is being done to be bdded to the Set crebtedDuringQuery
     * bnd we do not bdd b listener immedibtely.  When the query is done,
     * we btomicblly turn off the bddition of new nbmes to crebtedDuringQuery
     * bnd bdd bll the nbmes thbt were there to the result of the query.
     * Since we bre debling with Sets, the result is the sbme whether or not
     * the newly-crebted MBebn wbs included in the query result.
     *
     * It is importbnt not to hold bny locks during the operbtion of bdding
     * listeners to MBebns.  An MBebn's bddNotificbtionListener cbn be
     * brbitrbry user code, bnd this could debdlock with bny locks we hold
     * (see bug 6239400).  The corollbry is thbt we must not do bny operbtions
     * in this method or the methods it cblls thbt require locks.
     */
    privbte void crebteListeners() {
        logger.debug("crebteListeners", "stbrts");

        synchronized (this) {
            crebtedDuringQuery = new HbshSet<ObjectNbme>();
        }

        try {
            bddNotificbtionListener(MBebnServerDelegbte.DELEGATE_NAME,
                                    crebtionListener, crebtionFilter, null);
            logger.debug("crebteListeners", "bdded crebtionListener");
        } cbtch (Exception e) {
            finbl String msg = "Cbn't bdd listener to MBebn server delegbte: ";
            RuntimeException re = new IllegblArgumentException(msg + e);
            EnvHelp.initCbuse(re, e);
            logger.fine("crebteListeners", msg + e);
            logger.debug("crebteListeners", e);
            throw re;
        }

        /* Spec doesn't sby whether Set returned by QueryNbmes cbn be modified
           so we clone it. */
        Set<ObjectNbme> nbmes = queryNbmes(null, brobdcbsterQuery);
        nbmes = new HbshSet<ObjectNbme>(nbmes);

        synchronized (this) {
            nbmes.bddAll(crebtedDuringQuery);
            crebtedDuringQuery = null;
        }

        for (ObjectNbme nbme : nbmes)
            bddBufferListener(nbme);
        logger.debug("crebteListeners", "ends");
    }

    privbte void bddBufferListener(ObjectNbme nbme) {
        checkNoLocks();
        if (logger.debugOn())
            logger.debug("bddBufferListener", nbme.toString());
        try {
            bddNotificbtionListener(nbme, bufferListener, null, nbme);
        } cbtch (Exception e) {
            logger.trbce("bddBufferListener", e);
            /* This cbn hbppen if the MBebn wbs unregistered just
               bfter the query.  Or user NotificbtionBrobdcbster might
               throw unexpected exception.  */
        }
    }

    privbte void removeBufferListener(ObjectNbme nbme) {
        checkNoLocks();
        if (logger.debugOn())
            logger.debug("removeBufferListener", nbme.toString());
        try {
            removeNotificbtionListener(nbme, bufferListener);
        } cbtch (Exception e) {
            logger.trbce("removeBufferListener", e);
        }
    }

    privbte void bddNotificbtionListener(finbl ObjectNbme nbme,
                                         finbl NotificbtionListener listener,
                                         finbl NotificbtionFilter filter,
                                         finbl Object hbndbbck)
            throws Exception {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws InstbnceNotFoundException {
                    mBebnServer.bddNotificbtionListener(nbme,
                                                        listener,
                                                        filter,
                                                        hbndbbck);
                    return null;
                }
            });
        } cbtch (Exception e) {
            throw extrbctException(e);
        }
    }

    privbte void removeNotificbtionListener(finbl ObjectNbme nbme,
                                            finbl NotificbtionListener listener)
            throws Exception {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws Exception {
                    mBebnServer.removeNotificbtionListener(nbme, listener);
                    return null;
                }
            });
        } cbtch (Exception e) {
            throw extrbctException(e);
        }
    }

    privbte Set<ObjectNbme> queryNbmes(finbl ObjectNbme nbme,
                                       finbl QueryExp query) {
        PrivilegedAction<Set<ObjectNbme>> bct =
            new PrivilegedAction<Set<ObjectNbme>>() {
                public Set<ObjectNbme> run() {
                    return mBebnServer.queryNbmes(nbme, query);
                }
            };
        try {
            return AccessController.doPrivileged(bct);
        } cbtch (RuntimeException e) {
            logger.fine("queryNbmes", "Fbiled to query nbmes: " + e);
            logger.debug("queryNbmes", e);
            throw e;
        }
    }

    privbte stbtic boolebn isInstbnceOf(finbl MBebnServer mbs,
                                        finbl ObjectNbme nbme,
                                        finbl String clbssNbme) {
        PrivilegedExceptionAction<Boolebn> bct =
            new PrivilegedExceptionAction<Boolebn>() {
                public Boolebn run() throws InstbnceNotFoundException {
                    return mbs.isInstbnceOf(nbme, clbssNbme);
                }
            };
        try {
            return AccessController.doPrivileged(bct);
        } cbtch (Exception e) {
            logger.fine("isInstbnceOf", "fbiled: " + e);
            logger.debug("isInstbnceOf", e);
            return fblse;
        }
    }

    /* This method must not be synchronized.  See the comment on the
     * crebteListeners method.
     *
     * The notificbtion could brrive bfter our buffer hbs been destroyed
     * or even during its destruction.  So we blwbys bdd our listener
     * (without synchronizbtion), then we check if the buffer hbs been
     * destroyed bnd if so remove the listener we just bdded.
     */
    privbte void crebtedNotificbtion(MBebnServerNotificbtion n) {
        finbl String shouldEqubl =
            MBebnServerNotificbtion.REGISTRATION_NOTIFICATION;
        if (!n.getType().equbls(shouldEqubl)) {
            logger.wbrning("crebteNotificbtion", "bbd type: " + n.getType());
            return;
        }

        ObjectNbme nbme = n.getMBebnNbme();
        if (logger.debugOn())
            logger.debug("crebtedNotificbtion", "for: " + nbme);

        synchronized (this) {
            if (crebtedDuringQuery != null) {
                crebtedDuringQuery.bdd(nbme);
                return;
            }
        }

        if (isInstbnceOf(mBebnServer, nbme, brobdcbsterClbss)) {
            bddBufferListener(nbme);
            if (isDisposed())
                removeBufferListener(nbme);
        }
    }

    privbte clbss BufferListener implements NotificbtionListener {
        public void hbndleNotificbtion(Notificbtion notif, Object hbndbbck) {
            if (logger.debugOn()) {
                logger.debug("BufferListener.hbndleNotificbtion",
                      "notif=" + notif + "; hbndbbck=" + hbndbbck);
            }
            ObjectNbme nbme = (ObjectNbme) hbndbbck;
            bddNotificbtion(new NbmedNotificbtion(nbme, notif));
        }
    }

    privbte finbl NotificbtionListener bufferListener = new BufferListener();

    privbte stbtic clbss BrobdcbsterQuery
            extends QueryEvbl implements QueryExp {
        privbte stbtic finbl long seriblVersionUID = 7378487660587592048L;

        public boolebn bpply(finbl ObjectNbme nbme) {
            finbl MBebnServer mbs = QueryEvbl.getMBebnServer();
            return isInstbnceOf(mbs, nbme, brobdcbsterClbss);
        }
    }
    privbte stbtic finbl QueryExp brobdcbsterQuery = new BrobdcbsterQuery();

    privbte stbtic finbl NotificbtionFilter crebtionFilter;
    stbtic {
        NotificbtionFilterSupport nfs = new NotificbtionFilterSupport();
        nfs.enbbleType(MBebnServerNotificbtion.REGISTRATION_NOTIFICATION);
        crebtionFilter = nfs;
    }

    privbte finbl NotificbtionListener crebtionListener =
        new NotificbtionListener() {
            public void hbndleNotificbtion(Notificbtion notif,
                                           Object hbndbbck) {
                logger.debug("crebtionListener", "hbndleNotificbtion cblled");
                crebtedNotificbtion((MBebnServerNotificbtion) notif);
            }
        };

    privbte void destroyListeners() {
        checkNoLocks();
        logger.debug("destroyListeners", "stbrts");
        try {
            removeNotificbtionListener(MBebnServerDelegbte.DELEGATE_NAME,
                                       crebtionListener);
        } cbtch (Exception e) {
            logger.wbrning("remove listener from MBebnServer delegbte", e);
        }
        Set<ObjectNbme> nbmes = queryNbmes(null, brobdcbsterQuery);
        for (finbl ObjectNbme nbme : nbmes) {
            if (logger.debugOn())
                logger.debug("destroyListeners",
                             "remove listener from " + nbme);
            removeBufferListener(nbme);
        }
        logger.debug("destroyListeners", "ends");
    }

    privbte void checkNoLocks() {
        if (Threbd.holdsLock(this) || Threbd.holdsLock(globblLock))
            logger.wbrning("checkNoLocks", "lock protocol violbtion");
    }

    /**
     * Iterbte until we extrbct the rebl exception
     * from b stbck of PrivilegedActionExceptions.
     */
    privbte stbtic Exception extrbctException(Exception e) {
        while (e instbnceof PrivilegedActionException) {
            e = ((PrivilegedActionException)e).getException();
        }
        return e;
    }

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc",
                        "ArrbyNotificbtionBuffer");

    privbte finbl MBebnServer mBebnServer;
    privbte finbl ArrbyQueue<NbmedNotificbtion> queue;
    privbte int queueSize;
    privbte long ebrliestSequenceNumber;
    privbte long nextSequenceNumber;
    privbte Set<ObjectNbme> crebtedDuringQuery;

    stbtic finbl String brobdcbsterClbss =
        NotificbtionBrobdcbster.clbss.getNbme();
}
