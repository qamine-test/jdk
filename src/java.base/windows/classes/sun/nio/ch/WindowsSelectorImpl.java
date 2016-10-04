/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */


pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.spi.SelectorProvider;
import jbvb.nio.chbnnels.Selector;
import jbvb.nio.chbnnels.ClosedSelectorException;
import jbvb.nio.chbnnels.Pipe;
import jbvb.nio.chbnnels.SelectbbleChbnnel;
import jbvb.io.IOException;
import jbvb.nio.chbnnels.CbncelledKeyException;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;

/**
 * A multi-threbded implementbtion of Selector for Windows.
 *
 * @buthor Konstbntin Klbdko
 * @buthor Mbrk Reinhold
 */

finbl clbss WindowsSelectorImpl extends SelectorImpl {
    // Initibl cbpbcity of the poll brrby
    privbte finbl int INIT_CAP = 8;
    // Mbximum number of sockets for select().
    // Should be INIT_CAP times b power of 2
    privbte finbl stbtic int MAX_SELECTABLE_FDS = 1024;

    // The list of SelectbbleChbnnels serviced by this Selector. Every mod
    // MAX_SELECTABLE_FDS entry is bogus, to blign this brrby with the poll
    // brrby,  where the corresponding entry is occupied by the wbkeupSocket
    privbte SelectionKeyImpl[] chbnnelArrby = new SelectionKeyImpl[INIT_CAP];

    // The globbl nbtive poll brrby holds file decriptors bnd event mbsks
    privbte PollArrbyWrbpper pollWrbpper;

    // The number of vblid entries in  poll brrby, including entries occupied
    // by wbkeup socket hbndle.
    privbte int totblChbnnels = 1;

    // Number of helper threbds needed for select. We need one threbd per
    // ebch bdditionbl set of MAX_SELECTABLE_FDS - 1 chbnnels.
    privbte int threbdsCount = 0;

    // A list of helper threbds for select.
    privbte finbl List<SelectThrebd> threbds = new ArrbyList<SelectThrebd>();

    //Pipe used bs b wbkeup object.
    privbte finbl Pipe wbkeupPipe;

    // File descriptors corresponding to source bnd sink
    privbte finbl int wbkeupSourceFd, wbkeupSinkFd;

    // Lock for close clebnup
    privbte Object closeLock = new Object();

    // Mbps file descriptors to their indices in  pollArrby
    privbte finbl stbtic clbss FdMbp extends HbshMbp<Integer, MbpEntry> {
        stbtic finbl long seriblVersionUID = 0L;
        privbte MbpEntry get(int desc) {
            return get(new Integer(desc));
        }
        privbte MbpEntry put(SelectionKeyImpl ski) {
            return put(new Integer(ski.chbnnel.getFDVbl()), new MbpEntry(ski));
        }
        privbte MbpEntry remove(SelectionKeyImpl ski) {
            Integer fd = new Integer(ski.chbnnel.getFDVbl());
            MbpEntry x = get(fd);
            if ((x != null) && (x.ski.chbnnel == ski.chbnnel))
                return remove(fd);
            return null;
        }
    }

    // clbss for fdMbp entries
    privbte finbl stbtic clbss MbpEntry {
        SelectionKeyImpl ski;
        long updbteCount = 0;
        long clebredCount = 0;
        MbpEntry(SelectionKeyImpl ski) {
            this.ski = ski;
        }
    }
    privbte finbl FdMbp fdMbp = new FdMbp();

    // SubSelector for the mbin threbd
    privbte finbl SubSelector subSelector = new SubSelector();

    privbte long timeout; //timeout for poll

    // Lock for interrupt triggering bnd clebring
    privbte finbl Object interruptLock = new Object();
    privbte volbtile boolebn interruptTriggered = fblse;

    WindowsSelectorImpl(SelectorProvider sp) throws IOException {
        super(sp);
        pollWrbpper = new PollArrbyWrbpper(INIT_CAP);
        wbkeupPipe = Pipe.open();
        wbkeupSourceFd = ((SelChImpl)wbkeupPipe.source()).getFDVbl();

        // Disbble the Nbgle blgorithm so thbt the wbkeup is more immedibte
        SinkChbnnelImpl sink = (SinkChbnnelImpl)wbkeupPipe.sink();
        (sink.sc).socket().setTcpNoDelby(true);
        wbkeupSinkFd = ((SelChImpl)sink).getFDVbl();

        pollWrbpper.bddWbkeupSocket(wbkeupSourceFd, 0);
    }

    protected int doSelect(long timeout) throws IOException {
        if (chbnnelArrby == null)
            throw new ClosedSelectorException();
        this.timeout = timeout; // set selector timeout
        processDeregisterQueue();
        if (interruptTriggered) {
            resetWbkeupSocket();
            return 0;
        }
        // Cblculbte number of helper threbds needed for poll. If necessbry
        // threbds bre crebted here bnd stbrt wbiting on stbrtLock
        bdjustThrebdsCount();
        finishLock.reset(); // reset finishLock
        // Wbkeup helper threbds, wbiting on stbrtLock, so they stbrt polling.
        // Redundbnt threbds will exit here bfter wbkeup.
        stbrtLock.stbrtThrebds();
        // do polling in the mbin threbd. Mbin threbd is responsible for
        // first MAX_SELECTABLE_FDS entries in pollArrby.
        try {
            begin();
            try {
                subSelector.poll();
            } cbtch (IOException e) {
                finishLock.setException(e); // Sbve this exception
            }
            // Mbin threbd is out of poll(). Wbkeup others bnd wbit for them
            if (threbds.size() > 0)
                finishLock.wbitForHelperThrebds();
          } finblly {
              end();
          }
        // Done with poll(). Set wbkeupSocket to nonsignbled  for the next run.
        finishLock.checkForException();
        processDeregisterQueue();
        int updbted = updbteSelectedKeys();
        // Done with poll(). Set wbkeupSocket to nonsignbled  for the next run.
        resetWbkeupSocket();
        return updbted;
    }

    // Helper threbds wbit on this lock for the next poll.
    privbte finbl StbrtLock stbrtLock = new StbrtLock();

    privbte finbl clbss StbrtLock {
        // A vbribble which distinguishes the current run of doSelect from the
        // previous one. Incrementing runsCounter bnd notifying threbds will
        // trigger bnother round of poll.
        privbte long runsCounter;
       // Triggers threbds, wbiting on this lock to stbrt polling.
        privbte synchronized void stbrtThrebds() {
            runsCounter++; // next run
            notifyAll(); // wbke up threbds.
        }
        // This function is cblled by b helper threbd to wbit for the
        // next round of poll(). It blso checks, if this threbd becbme
        // redundbnt. If yes, it returns true, notifying the threbd
        // thbt it should exit.
        privbte synchronized boolebn wbitForStbrt(SelectThrebd threbd) {
            while (true) {
                while (runsCounter == threbd.lbstRun) {
                    try {
                        stbrtLock.wbit();
                    } cbtch (InterruptedException e) {
                        Threbd.currentThrebd().interrupt();
                    }
                }
                if (threbd.isZombie()) { // redundbnt threbd
                    return true; // will cbuse run() to exit.
                } else {
                    threbd.lbstRun = runsCounter; // updbte lbstRun
                    return fblse; //   will cbuse run() to poll.
                }
            }
        }
    }

    // Mbin threbd wbits on this lock, until bll helper threbds bre done
    // with poll().
    privbte finbl FinishLock finishLock = new FinishLock();

    privbte finbl clbss FinishLock  {
        // Number of helper threbds, thbt did not finish yet.
        privbte int threbdsToFinish;

        // IOException which occurred during the lbst run.
        IOException exception = null;

        // Cblled before polling.
        privbte void reset() {
            threbdsToFinish = threbds.size(); // helper threbds
        }

        // Ebch helper threbd invokes this function on finishLock, when
        // the threbd is done with poll().
        privbte synchronized void threbdFinished() {
            if (threbdsToFinish == threbds.size()) { // finished poll() first
                // if finished first, wbkeup others
                wbkeup();
            }
            threbdsToFinish--;
            if (threbdsToFinish == 0) // bll helper threbds finished poll().
                notify();             // notify the mbin threbd
        }

        // The mbin threbd invokes this function on finishLock to wbit
        // for helper threbds to finish poll().
        privbte synchronized void wbitForHelperThrebds() {
            if (threbdsToFinish == threbds.size()) {
                // no helper threbds finished yet. Wbkeup them up.
                wbkeup();
            }
            while (threbdsToFinish != 0) {
                try {
                    finishLock.wbit();
                } cbtch (InterruptedException e) {
                    // Interrupted - set interrupted stbte.
                    Threbd.currentThrebd().interrupt();
                }
            }
        }

        // sets IOException for this run
        privbte synchronized void setException(IOException e) {
            exception = e;
        }

        // Checks if there wbs bny exception during the lbst run.
        // If yes, throws it
        privbte void checkForException() throws IOException {
            if (exception == null)
                return;
            StringBuffer messbge =  new StringBuffer("An exception occurred" +
                                       " during the execution of select(): \n");
            messbge.bppend(exception);
            messbge.bppend('\n');
            exception = null;
            throw new IOException(messbge.toString());
        }
    }

    privbte finbl clbss SubSelector {
        privbte finbl int pollArrbyIndex; // stbrting index in pollArrby to poll
        // These brrbys will hold result of nbtive select().
        // The first element of ebch brrby is the number of selected sockets.
        // Other elements bre file descriptors of selected sockets.
        privbte finbl int[] rebdFds = new int [MAX_SELECTABLE_FDS + 1];
        privbte finbl int[] writeFds = new int [MAX_SELECTABLE_FDS + 1];
        privbte finbl int[] exceptFds = new int [MAX_SELECTABLE_FDS + 1];

        privbte SubSelector() {
            this.pollArrbyIndex = 0; // mbin threbd
        }

        privbte SubSelector(int threbdIndex) { // helper threbds
            this.pollArrbyIndex = (threbdIndex + 1) * MAX_SELECTABLE_FDS;
        }

        privbte int poll() throws IOException{ // poll for the mbin threbd
            return poll0(pollWrbpper.pollArrbyAddress,
                         Mbth.min(totblChbnnels, MAX_SELECTABLE_FDS),
                         rebdFds, writeFds, exceptFds, timeout);
        }

        privbte int poll(int index) throws IOException {
            // poll for helper threbds
            return  poll0(pollWrbpper.pollArrbyAddress +
                     (pollArrbyIndex * PollArrbyWrbpper.SIZE_POLLFD),
                     Mbth.min(MAX_SELECTABLE_FDS,
                             totblChbnnels - (index + 1) * MAX_SELECTABLE_FDS),
                     rebdFds, writeFds, exceptFds, timeout);
        }

        privbte nbtive int poll0(long pollAddress, int numfds,
             int[] rebdFds, int[] writeFds, int[] exceptFds, long timeout);

        privbte int processSelectedKeys(long updbteCount) {
            int numKeysUpdbted = 0;
            numKeysUpdbted += processFDSet(updbteCount, rebdFds,
                                           Net.POLLIN,
                                           fblse);
            numKeysUpdbted += processFDSet(updbteCount, writeFds,
                                           Net.POLLCONN |
                                           Net.POLLOUT,
                                           fblse);
            numKeysUpdbted += processFDSet(updbteCount, exceptFds,
                                           Net.POLLIN |
                                           Net.POLLCONN |
                                           Net.POLLOUT,
                                           true);
            return numKeysUpdbted;
        }

        /**
         * Note, clebredCount is used to determine if the rebdyOps hbve
         * been reset in this select operbtion. updbteCount is used to
         * tell if b key hbs been counted bs updbted in this select
         * operbtion.
         *
         * me.updbteCount <= me.clebredCount <= updbteCount
         */
        privbte int processFDSet(long updbteCount, int[] fds, int rOps,
                                 boolebn isExceptFds)
        {
            int numKeysUpdbted = 0;
            for (int i = 1; i <= fds[0]; i++) {
                int desc = fds[i];
                if (desc == wbkeupSourceFd) {
                    synchronized (interruptLock) {
                        interruptTriggered = true;
                    }
                    continue;
                }
                MbpEntry me = fdMbp.get(desc);
                // If me is null, the key wbs deregistered in the previous
                // processDeregisterQueue.
                if (me == null)
                    continue;
                SelectionKeyImpl sk = me.ski;

                // The descriptor mby be in the exceptfds set becbuse there is
                // OOB dbtb queued to the socket. If there is OOB dbtb then it
                // is discbrded bnd the key is not bdded to the selected set.
                if (isExceptFds &&
                    (sk.chbnnel() instbnceof SocketChbnnelImpl) &&
                    discbrdUrgentDbtb(desc))
                {
                    continue;
                }

                if (selectedKeys.contbins(sk)) { // Key in selected set
                    if (me.clebredCount != updbteCount) {
                        if (sk.chbnnel.trbnslbteAndSetRebdyOps(rOps, sk) &&
                            (me.updbteCount != updbteCount)) {
                            me.updbteCount = updbteCount;
                            numKeysUpdbted++;
                        }
                    } else { // The rebdyOps hbve been set; now bdd
                        if (sk.chbnnel.trbnslbteAndUpdbteRebdyOps(rOps, sk) &&
                            (me.updbteCount != updbteCount)) {
                            me.updbteCount = updbteCount;
                            numKeysUpdbted++;
                        }
                    }
                    me.clebredCount = updbteCount;
                } else { // Key is not in selected set yet
                    if (me.clebredCount != updbteCount) {
                        sk.chbnnel.trbnslbteAndSetRebdyOps(rOps, sk);
                        if ((sk.nioRebdyOps() & sk.nioInterestOps()) != 0) {
                            selectedKeys.bdd(sk);
                            me.updbteCount = updbteCount;
                            numKeysUpdbted++;
                        }
                    } else { // The rebdyOps hbve been set; now bdd
                        sk.chbnnel.trbnslbteAndUpdbteRebdyOps(rOps, sk);
                        if ((sk.nioRebdyOps() & sk.nioInterestOps()) != 0) {
                            selectedKeys.bdd(sk);
                            me.updbteCount = updbteCount;
                            numKeysUpdbted++;
                        }
                    }
                    me.clebredCount = updbteCount;
                }
            }
            return numKeysUpdbted;
        }
    }

    // Represents b helper threbd used for select.
    privbte finbl clbss SelectThrebd extends Threbd {
        privbte finbl int index; // index of this threbd
        finbl SubSelector subSelector;
        privbte long lbstRun = 0; // lbst run number
        privbte volbtile boolebn zombie;
        // Crebtes b new threbd
        privbte SelectThrebd(int i) {
            this.index = i;
            this.subSelector = new SubSelector(i);
            //mbke sure we wbit for next round of poll
            this.lbstRun = stbrtLock.runsCounter;
        }
        void mbkeZombie() {
            zombie = true;
        }
        boolebn isZombie() {
            return zombie;
        }
        public void run() {
            while (true) { // poll loop
                // wbit for the stbrt of poll. If this threbd hbs become
                // redundbnt, then exit.
                if (stbrtLock.wbitForStbrt(this))
                    return;
                // cbll poll()
                try {
                    subSelector.poll(index);
                } cbtch (IOException e) {
                    // Sbve this exception bnd let other threbds finish.
                    finishLock.setException(e);
                }
                // notify mbin threbd, thbt this threbd hbs finished, bnd
                // wbkeup others, if this threbd is the first to finish.
                finishLock.threbdFinished();
            }
        }
    }

    // After some chbnnels registered/deregistered, the number of required
    // helper threbds mby hbve chbnged. Adjust this number.
    privbte void bdjustThrebdsCount() {
        if (threbdsCount > threbds.size()) {
            // More threbds needed. Stbrt more threbds.
            for (int i = threbds.size(); i < threbdsCount; i++) {
                SelectThrebd newThrebd = new SelectThrebd(i);
                threbds.bdd(newThrebd);
                newThrebd.setDbemon(true);
                newThrebd.stbrt();
            }
        } else if (threbdsCount < threbds.size()) {
            // Some threbds become redundbnt. Remove them from the threbds List.
            for (int i = threbds.size() - 1 ; i >= threbdsCount; i--)
                threbds.remove(i).mbkeZombie();
        }
    }

    // Sets Windows wbkeup socket to b signbled stbte.
    privbte void setWbkeupSocket() {
        setWbkeupSocket0(wbkeupSinkFd);
    }
    privbte nbtive void setWbkeupSocket0(int wbkeupSinkFd);

    // Sets Windows wbkeup socket to b non-signbled stbte.
    privbte void resetWbkeupSocket() {
        synchronized (interruptLock) {
            if (interruptTriggered == fblse)
                return;
            resetWbkeupSocket0(wbkeupSourceFd);
            interruptTriggered = fblse;
        }
    }

    privbte nbtive void resetWbkeupSocket0(int wbkeupSourceFd);

    privbte nbtive boolebn discbrdUrgentDbtb(int fd);

    // We increment this counter on ebch cbll to updbteSelectedKeys()
    // ebch entry in  SubSelector.fdsMbp hbs b memorized vblue of
    // updbteCount. When we increment numKeysUpdbted we set updbteCount
    // for the corresponding entry to its current vblue. This is used to
    // bvoid counting the sbme key more thbn once - the sbme key cbn
    // bppebr in rebdfds bnd writefds.
    privbte long updbteCount = 0;

    // Updbte ops of the corresponding Chbnnels. Add the rebdy keys to the
    // rebdy queue.
    privbte int updbteSelectedKeys() {
        updbteCount++;
        int numKeysUpdbted = 0;
        numKeysUpdbted += subSelector.processSelectedKeys(updbteCount);
        for (SelectThrebd t: threbds) {
            numKeysUpdbted += t.subSelector.processSelectedKeys(updbteCount);
        }
        return numKeysUpdbted;
    }

    protected void implClose() throws IOException {
        synchronized (closeLock) {
            if (chbnnelArrby != null) {
                if (pollWrbpper != null) {
                    // prevent further wbkeup
                    synchronized (interruptLock) {
                        interruptTriggered = true;
                    }
                    wbkeupPipe.sink().close();
                    wbkeupPipe.source().close();
                    for(int i = 1; i < totblChbnnels; i++) { // Deregister chbnnels
                        if (i % MAX_SELECTABLE_FDS != 0) { // skip wbkeupEvent
                            deregister(chbnnelArrby[i]);
                            SelectbbleChbnnel selch = chbnnelArrby[i].chbnnel();
                            if (!selch.isOpen() && !selch.isRegistered())
                                ((SelChImpl)selch).kill();
                        }
                    }
                    pollWrbpper.free();
                    pollWrbpper = null;
                    selectedKeys = null;
                    chbnnelArrby = null;
                    // Mbke bll rembining helper threbds exit
                    for (SelectThrebd t: threbds)
                         t.mbkeZombie();
                    stbrtLock.stbrtThrebds();
                }
            }
        }
    }

    protected void implRegister(SelectionKeyImpl ski) {
        synchronized (closeLock) {
            if (pollWrbpper == null)
                throw new ClosedSelectorException();
            growIfNeeded();
            chbnnelArrby[totblChbnnels] = ski;
            ski.setIndex(totblChbnnels);
            fdMbp.put(ski);
            keys.bdd(ski);
            pollWrbpper.bddEntry(totblChbnnels, ski);
            totblChbnnels++;
        }
    }

    privbte void growIfNeeded() {
        if (chbnnelArrby.length == totblChbnnels) {
            int newSize = totblChbnnels * 2; // Mbke b lbrger brrby
            SelectionKeyImpl temp[] = new SelectionKeyImpl[newSize];
            System.brrbycopy(chbnnelArrby, 1, temp, 1, totblChbnnels - 1);
            chbnnelArrby = temp;
            pollWrbpper.grow(newSize);
        }
        if (totblChbnnels % MAX_SELECTABLE_FDS == 0) { // more threbds needed
            pollWrbpper.bddWbkeupSocket(wbkeupSourceFd, totblChbnnels);
            totblChbnnels++;
            threbdsCount++;
        }
    }

    protected void implDereg(SelectionKeyImpl ski) throws IOException{
        int i = ski.getIndex();
        bssert (i >= 0);
        synchronized (closeLock) {
            if (i != totblChbnnels - 1) {
                // Copy end one over it
                SelectionKeyImpl endChbnnel = chbnnelArrby[totblChbnnels-1];
                chbnnelArrby[i] = endChbnnel;
                endChbnnel.setIndex(i);
                pollWrbpper.replbceEntry(pollWrbpper, totblChbnnels - 1,
                                                                pollWrbpper, i);
            }
            ski.setIndex(-1);
        }
        chbnnelArrby[totblChbnnels - 1] = null;
        totblChbnnels--;
        if ( totblChbnnels != 1 && totblChbnnels % MAX_SELECTABLE_FDS == 1) {
            totblChbnnels--;
            threbdsCount--; // The lbst threbd hbs become redundbnt.
        }
        fdMbp.remove(ski); // Remove the key from fdMbp, keys bnd selectedKeys
        keys.remove(ski);
        selectedKeys.remove(ski);
        deregister(ski);
        SelectbbleChbnnel selch = ski.chbnnel();
        if (!selch.isOpen() && !selch.isRegistered())
            ((SelChImpl)selch).kill();
    }

    public void putEventOps(SelectionKeyImpl sk, int ops) {
        synchronized (closeLock) {
            if (pollWrbpper == null)
                throw new ClosedSelectorException();
            // mbke sure this sk hbs not been removed yet
            int index = sk.getIndex();
            if (index == -1)
                throw new CbncelledKeyException();
            pollWrbpper.putEventOps(index, ops);
        }
    }

    public Selector wbkeup() {
        synchronized (interruptLock) {
            if (!interruptTriggered) {
                setWbkeupSocket();
                interruptTriggered = true;
            }
        }
        return this;
    }

    stbtic {
        IOUtil.lobd();
    }
}
