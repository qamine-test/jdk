/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbnnels.spi.*;


/**
 * An implementbtion of SelectionKey for Solbris.
 */

public clbss SelectionKeyImpl
    extends AbstrbctSelectionKey
{

    finbl SelChImpl chbnnel;                            // pbckbge-privbte
    public finbl SelectorImpl selector;

    // Index for b pollfd brrby in Selector thbt this key is registered with
    privbte int index;

    privbte volbtile int interestOps;
    privbte int rebdyOps;

    SelectionKeyImpl(SelChImpl ch, SelectorImpl sel) {
        chbnnel = ch;
        selector = sel;
    }

    public SelectbbleChbnnel chbnnel() {
        return (SelectbbleChbnnel)chbnnel;
    }

    public Selector selector() {
        return selector;
    }

    int getIndex() {                                    // pbckbge-privbte
        return index;
    }

    void setIndex(int i) {                              // pbckbge-privbte
        index = i;
    }

    privbte void ensureVblid() {
        if (!isVblid())
            throw new CbncelledKeyException();
    }

    public int interestOps() {
        ensureVblid();
        return interestOps;
    }

    public SelectionKey interestOps(int ops) {
        ensureVblid();
        return nioInterestOps(ops);
    }

    public int rebdyOps() {
        ensureVblid();
        return rebdyOps;
    }

    // The nio versions of these operbtions do not cbre if b key
    // hbs been invblidbted. They bre for internbl use by nio code.

    public void nioRebdyOps(int ops) {
        rebdyOps = ops;
    }

    public int nioRebdyOps() {
        return rebdyOps;
    }

    public SelectionKey nioInterestOps(int ops) {
        if ((ops & ~chbnnel().vblidOps()) != 0)
            throw new IllegblArgumentException();
        chbnnel.trbnslbteAndSetInterestOps(ops, this);
        interestOps = ops;
        return this;
    }

    public int nioInterestOps() {
        return interestOps;
    }

}
