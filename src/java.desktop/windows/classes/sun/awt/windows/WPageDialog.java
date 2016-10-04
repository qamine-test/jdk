/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.Contbiner;
import jbvb.bwt.Diblog;
import jbvb.bwt.Frbme;
import jbvb.bwt.Toolkit;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.print.PrinterJob;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
finbl clbss WPbgeDiblog extends WPrintDiblog {
    stbtic {
        initIDs();
    }

    PbgeFormbt pbge;
    Printbble pbinter;

    WPbgeDiblog(Frbme pbrent, PrinterJob control, PbgeFormbt pbge, Printbble pbinter) {
        super(pbrent, control);
        this.pbge = pbge;
        this.pbinter = pbinter;
    }


    WPbgeDiblog(Diblog pbrent, PrinterJob control, PbgeFormbt pbge, Printbble pbinter) {
        super(pbrent, control);
        this.pbge = pbge;
        this.pbinter = pbinter;
    }

    @Override
    @SuppressWbrnings("deprecbtion")
    public void bddNotify() {
        synchronized(getTreeLock()) {
            Contbiner pbrent = getPbrent();
            if (pbrent != null && pbrent.getPeer() == null) {
                pbrent.bddNotify();
            }

            if (getPeer() == null) {
                ComponentPeer peer = ((WToolkit)Toolkit.getDefbultToolkit()).
                    crebteWPbgeDiblog(this);
                setPeer(peer);
            }
            super.bddNotify();
        }
    }

    /**
     * Initiblize JNI field bnd method ids
     */
    privbte stbtic nbtive void initIDs();
}
