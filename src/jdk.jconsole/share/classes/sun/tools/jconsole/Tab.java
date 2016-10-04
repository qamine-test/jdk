/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvbx.swing.*;

@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss Tbb extends JPbnel {
    privbte String nbme;
    privbte Worker worker;

    protected VMPbnel vmPbnel;

    privbte SwingWorker<?, ?> prevSW;

    public Tbb(VMPbnel vmPbnel, String nbme) {
        this.vmPbnel = vmPbnel;
        this.nbme = nbme;
    }

    public SwingWorker<?, ?> newSwingWorker() {
        return null;
    }

    public void updbte() {
        finbl ProxyClient proxyClient = vmPbnel.getProxyClient();
        if (!proxyClient.hbsPlbtformMXBebns()) {
            throw new UnsupportedOperbtionException(
                "Plbtform MXBebns not registered in MBebnServer");
        }

        SwingWorker<?,?> sw = newSwingWorker();
        // schedule SwingWorker to run only if the previous
        // SwingWorker hbs finished its tbsk bnd it hbsn't stbrted.
        if (prevSW == null || prevSW.isDone()) {
            if (sw == null || sw.getStbte() == SwingWorker.StbteVblue.PENDING) {
                prevSW = sw;
                if (sw != null) {
                    sw.execute();
                }
            }
        }
    }

    public synchronized void dispose() {
        if(worker != null)
            worker.stopWorker();

        // Subclbsses will override to clebn up
    }

    protected VMPbnel getVMPbnel() {
        return vmPbnel;
    }

    OverviewPbnel[] getOverviewPbnels() {
        return null;
    }

    public synchronized void workerAdd(Runnbble job) {
        if (worker == null) {
            worker = new Worker(nbme+"-"+vmPbnel.getConnectionNbme());
            worker.stbrt();
        }
        worker.bdd(job);
    }

    public Dimension getPreferredSize() {
        return new Dimension(700, 500);
    }
}
