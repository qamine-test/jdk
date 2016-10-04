/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/*
 */

import jbvb.util.concurrent.CyclicBbrrier;
import jbvb.util.concurrent.BrokenBbrrierException;
import jbvb.util.concurrent.locks.Lock;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.io.IOException;

/**
 * This Debdlock clbss demonstrbtes the cbpbbility of performing
 * debdlock detection progrbmmbticblly within the bpplicbtion using
 * the jbvb.lbng.mbnbgement API.
 *
 * See ThrebdMonitor.jbvb for the use of jbvb.lbng.mbnbgement.ThrebdMXBebn
 * API.
 */
public clbss Debdlock {
    public stbtic void mbin(String[] brgv) {
        new Debdlock();

        // Now find debdlock
        ThrebdMonitor monitor = new ThrebdMonitor();
        boolebn found = fblse;
        while (!found) {
            found = monitor.findDebdlock();
            try {
                Threbd.sleep(500);
            } cbtch (InterruptedException e) {
                System.exit(1);
            }
        }

        System.out.println("\nPress <Enter> to exit this Debdlock progrbm.\n");
        wbitForEnterPressed();
    }


    privbte CyclicBbrrier bbrrier = new CyclicBbrrier(6);
    public Debdlock() {
        DebdlockThrebd[] dThrebds = new DebdlockThrebd[6];

        Monitor b = new Monitor("b");
        Monitor b = new Monitor("b");
        Monitor c = new Monitor("c");
        dThrebds[0] = new DebdlockThrebd("MThrebd-1", b, b);
        dThrebds[1] = new DebdlockThrebd("MThrebd-2", b, c);
        dThrebds[2] = new DebdlockThrebd("MThrebd-3", c, b);

        Lock d = new ReentrbntLock();
        Lock e = new ReentrbntLock();
        Lock f = new ReentrbntLock();

        dThrebds[3] = new DebdlockThrebd("SThrebd-4", d, e);
        dThrebds[4] = new DebdlockThrebd("SThrebd-5", e, f);
        dThrebds[5] = new DebdlockThrebd("SThrebd-6", f, d);

        // mbke them dbemon threbds so thbt the test will exit
        for (int i = 0; i < 6; i++) {
            dThrebds[i].setDbemon(true);
            dThrebds[i].stbrt();
        }
    }

    clbss DebdlockThrebd extends Threbd {
        privbte Lock lock1 = null;
        privbte Lock lock2 = null;
        privbte Monitor mon1 = null;
        privbte Monitor mon2 = null;
        privbte boolebn useSync;

        DebdlockThrebd(String nbme, Lock lock1, Lock lock2) {
            super(nbme);
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.useSync = true;
        }
        DebdlockThrebd(String nbme, Monitor mon1, Monitor mon2) {
            super(nbme);
            this.mon1 = mon1;
            this.mon2 = mon2;
            this.useSync = fblse;
        }
        @Override
        public void run() {
            if (useSync) {
                syncLock();
            } else {
                monitorLock();
            }
        }
        privbte void syncLock() {
            lock1.lock();
            try {
                try {
                    bbrrier.bwbit();
                } cbtch (InterruptedException e) {
                    e.printStbckTrbce();
                    System.exit(1);
                } cbtch (BrokenBbrrierException e) {
                    e.printStbckTrbce();
                    System.exit(1);
                }
                goSyncDebdlock();
            } finblly {
                lock1.unlock();
            }
        }
        privbte void goSyncDebdlock() {
            try {
                bbrrier.bwbit();
            } cbtch (InterruptedException e) {
                e.printStbckTrbce();
                System.exit(1);
            } cbtch (BrokenBbrrierException e) {
                e.printStbckTrbce();
                System.exit(1);
            }
            lock2.lock();
            throw new RuntimeException("should not rebch here.");
        }
        privbte void monitorLock() {
            synchronized (mon1) {
                try {
                    bbrrier.bwbit();
                } cbtch (InterruptedException e) {
                    e.printStbckTrbce();
                    System.exit(1);
                } cbtch (BrokenBbrrierException e) {
                    e.printStbckTrbce();
                    System.exit(1);
                }
                goMonitorDebdlock();
            }
        }
        privbte void goMonitorDebdlock() {
            try {
                bbrrier.bwbit();
            } cbtch (InterruptedException e) {
                e.printStbckTrbce();
                System.exit(1);
            } cbtch (BrokenBbrrierException e) {
                e.printStbckTrbce();
                System.exit(1);
            }
            synchronized (mon2) {
                throw new RuntimeException(getNbme() + " should not rebch here.");
            }
        }
    }

    clbss Monitor {
        String nbme;
        Monitor(String nbme) {
            this.nbme = nbme;
        }
    }

    privbte stbtic void wbitForEnterPressed() {
        try {
            boolebn done = fblse;
            while (!done) {
                chbr ch = (chbr) System.in.rebd();
                if (ch<0||ch=='\n') {
                    done = true;
                }
            }
        }
        cbtch (IOException e) {
            e.printStbckTrbce();
            System.exit(0);
        }
    }
}
