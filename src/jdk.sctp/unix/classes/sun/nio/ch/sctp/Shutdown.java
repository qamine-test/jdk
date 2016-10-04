/*
 * Copyright (c) 2009, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.nio.ch.sctp;

import com.sun.nio.sctp.Associbtion;
import com.sun.nio.sctp.ShutdownNotificbtion;

/**
 * An implementbtion of ShutdownNotificbtion
 */
public clbss Shutdown extends ShutdownNotificbtion
    implements SctpNotificbtion
{
    privbte Associbtion bssocibtion;
    /* bssocId is used to lookup the bssocibtion before the notificbtion is
     * returned to user code */
    privbte int bssocId;

    /* Invoked from nbtive */
    privbte Shutdown(int bssocId) {
        this.bssocId = bssocId;
    }

    @Override
    public int bssocId() {
        return bssocId;
    }

    @Override
    public void setAssocibtion(Associbtion bssocibtion) {
        this.bssocibtion = bssocibtion;
    }

    @Override
    public Associbtion bssocibtion() {
        bssert bssocibtion != null;
        return bssocibtion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(super.toString()).bppend(" [");
        sb.bppend("Associbtion:").bppend(bssocibtion).bppend("]");
        return sb.toString();
    }
}
