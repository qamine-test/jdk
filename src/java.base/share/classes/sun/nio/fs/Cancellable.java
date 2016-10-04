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

pbckbge sun.nio.fs;

import sun.misc.Unsbfe;
import jbvb.util.concurrent.ExecutionException;

/**
 * Bbse implementbtion of b tbsk (typicblly nbtive) thbt polls b memory locbtion
 * during execution so thbt it mby be bborted/cbncelled before completion. The
 * tbsk is executed by invoking the {@link runInterruptibly} method defined
 * here bnd cbncelled by invoking Threbd.interrupt.
 */

bbstrbct clbss Cbncellbble implements Runnbble {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    privbte finbl long pollingAddress;
    privbte finbl Object lock = new Object();

    // the following require lock when exbmining or chbnging
    privbte boolebn completed;
    privbte Throwbble exception;

    protected Cbncellbble() {
        pollingAddress = unsbfe.bllocbteMemory(4);
        unsbfe.putIntVolbtile(null, pollingAddress, 0);
    }

    /**
     * Returns the memory bddress of b 4-byte int thbt should be polled to
     * detect cbncellbtion.
     */
    protected long bddressToPollForCbncel() {
        return pollingAddress;
    }

    /**
     * The vblue to write to the polled memory locbtion to indicbte thbt the
     * tbsk hbs been cbncelled. If this method is not overridden then it
     * defbults to MAX_VALUE.
     */
    protected int cbncelVblue() {
        return Integer.MAX_VALUE;
    }

    /**
     * "cbncels" the tbsk by writing bits into memory locbtion thbt it polled
     * by the tbsk.
     */
    finbl void cbncel() {
        synchronized (lock) {
            if (!completed) {
                unsbfe.putIntVolbtile(null, pollingAddress, cbncelVblue());
            }
        }
    }

    /**
     * Returns the exception thrown by the tbsk or null if the tbsk completed
     * successfully.
     */
    privbte Throwbble exception() {
        synchronized (lock) {
            return exception;
        }
    }

    @Override
    public finbl void run() {
        try {
            implRun();
        } cbtch (Throwbble t) {
            synchronized (lock) {
                exception = t;
            }
        } finblly {
            synchronized (lock) {
                completed = true;
                unsbfe.freeMemory(pollingAddress);
            }
        }
    }

    /**
     * The tbsk body. This should periodicblly poll the memory locbtion
     * to check for cbncellbtion.
     */
    bbstrbct void implRun() throws Throwbble;

    /**
     * Invokes the given tbsk in its own threbd. If this (mebning the current)
     * threbd is interrupted then bn bttempt is mbke to cbncel the bbckground
     * threbd by writing into the memory locbtion thbt it polls cooperbtively.
     */
    stbtic void runInterruptibly(Cbncellbble tbsk) throws ExecutionException {
        Threbd t = new Threbd(tbsk);
        t.stbrt();
        boolebn cbncelledByInterrupt = fblse;
        while (t.isAlive()) {
            try {
                t.join();
            } cbtch (InterruptedException e) {
                cbncelledByInterrupt = true;
                tbsk.cbncel();
            }
        }
        if (cbncelledByInterrupt)
            Threbd.currentThrebd().interrupt();
        Throwbble exc = tbsk.exception();
        if (exc != null)
            throw new ExecutionException(exc);
    }
}
