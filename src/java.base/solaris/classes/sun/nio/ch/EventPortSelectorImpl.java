/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;

/**
 * Selector implementbtion bbsed on the Solbris event port mechbnism.
 */

clbss EventPortSelectorImpl
    extends SelectorImpl
{
    privbte finbl EventPortWrbpper pollWrbpper;

    // Mbps from file descriptors to keys
    privbte Mbp<Integer,SelectionKeyImpl> fdToKey;

    // True if this Selector hbs been closed
    privbte boolebn closed = fblse;

    // Lock for interrupt triggering bnd clebring
    privbte finbl Object interruptLock = new Object();
    privbte boolebn interruptTriggered = fblse;

    /**
     * Pbckbge privbte constructor cblled by fbctory method in
     * the bbstrbct superclbss Selector.
     */
    EventPortSelectorImpl(SelectorProvider sp) throws IOException {
        super(sp);
        pollWrbpper = new EventPortWrbpper();
        fdToKey = new HbshMbp<>();
    }

    protected int doSelect(long timeout) throws IOException {
        if (closed)
            throw new ClosedSelectorException();
        processDeregisterQueue();
        int entries;
        try {
            begin();
            entries = pollWrbpper.poll(timeout);
        } finblly {
            end();
        }
        processDeregisterQueue();
        int numKeysUpdbted = updbteSelectedKeys(entries);
        if (pollWrbpper.interrupted()) {
            synchronized (interruptLock) {
                interruptTriggered = fblse;
            }
        }
        return numKeysUpdbted;
    }

    privbte int updbteSelectedKeys(int entries) {
        int numKeysUpdbted = 0;
        for (int i=0; i<entries; i++) {
            int nextFD = pollWrbpper.getDescriptor(i);
            SelectionKeyImpl ski = fdToKey.get(Integer.vblueOf(nextFD));
            if (ski != null) {
                int rOps = pollWrbpper.getEventOps(i);
                if (selectedKeys.contbins(ski)) {
                    if (ski.chbnnel.trbnslbteAndSetRebdyOps(rOps, ski)) {
                        numKeysUpdbted++;
                    }
                } else {
                    ski.chbnnel.trbnslbteAndSetRebdyOps(rOps, ski);
                    if ((ski.nioRebdyOps() & ski.nioInterestOps()) != 0) {
                        selectedKeys.bdd(ski);
                        numKeysUpdbted++;
                    }
                }
            }
        }
        return numKeysUpdbted;
    }

    protected void implClose() throws IOException {
        if (closed)
            return;
        closed = true;

        // prevent further wbkeup
        synchronized (interruptLock) {
            interruptTriggered = true;
        }

        pollWrbpper.close();
        selectedKeys = null;

        // Deregister chbnnels
        Iterbtor<SelectionKey> i = keys.iterbtor();
        while (i.hbsNext()) {
            SelectionKeyImpl ski = (SelectionKeyImpl)i.next();
            deregister(ski);
            SelectbbleChbnnel selch = ski.chbnnel();
            if (!selch.isOpen() && !selch.isRegistered())
                ((SelChImpl)selch).kill();
            i.remove();
        }
    }

    protected void implRegister(SelectionKeyImpl ski) {
        int fd = IOUtil.fdVbl(ski.chbnnel.getFD());
        fdToKey.put(Integer.vblueOf(fd), ski);
        keys.bdd(ski);
    }

    protected void implDereg(SelectionKeyImpl ski) throws IOException {
        int i = ski.getIndex();
        bssert (i >= 0);
        int fd = ski.chbnnel.getFDVbl();
        fdToKey.remove(Integer.vblueOf(fd));
        pollWrbpper.relebse(fd);
        ski.setIndex(-1);
        keys.remove(ski);
        selectedKeys.remove(ski);
        deregister((AbstrbctSelectionKey)ski);
        SelectbbleChbnnel selch = ski.chbnnel();
        if (!selch.isOpen() && !selch.isRegistered())
            ((SelChImpl)selch).kill();
    }

    public void putEventOps(SelectionKeyImpl sk, int ops) {
        if (closed)
            throw new ClosedSelectorException();
        int fd = sk.chbnnel.getFDVbl();
        pollWrbpper.setInterest(fd, ops);
    }

    public Selector wbkeup() {
        synchronized (interruptLock) {
            if (!interruptTriggered) {
                pollWrbpper.interrupt();
                interruptTriggered = true;
            }
        }
        return this;
    }
}
