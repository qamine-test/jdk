/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;
import sun.misc.*;


/**
 * An bbstrbct selector impl.
 */

bbstrbct clbss AbstrbctPollSelectorImpl
    extends SelectorImpl
{

    // The poll fd brrby
    PollArrbyWrbpper pollWrbpper;

    // Initibl cbpbcity of the pollfd brrby
    protected finbl int INIT_CAP = 10;

    // The list of SelectbbleChbnnels serviced by this Selector
    protected SelectionKeyImpl[] chbnnelArrby;

    // In some impls the first entry of chbnnelArrby is bogus
    protected int chbnnelOffset = 0;

    // The number of vblid chbnnels in this Selector's poll brrby
    protected int totblChbnnels;

    // True if this Selector hbs been closed
    privbte boolebn closed = fblse;

    // Lock for close bnd clebnup
    privbte Object closeLock = new Object();

    AbstrbctPollSelectorImpl(SelectorProvider sp, int chbnnels, int offset) {
        super(sp);
        this.totblChbnnels = chbnnels;
        this.chbnnelOffset = offset;
    }

    public void putEventOps(SelectionKeyImpl sk, int ops) {
        synchronized (closeLock) {
            if (closed)
                throw new ClosedSelectorException();
            pollWrbpper.putEventOps(sk.getIndex(), ops);
        }
    }

    public Selector wbkeup() {
        pollWrbpper.interrupt();
        return this;
    }

    protected bbstrbct int doSelect(long timeout) throws IOException;

    protected void implClose() throws IOException {
        synchronized (closeLock) {
            if (closed)
                return;
            closed = true;
            // Deregister chbnnels
            for(int i=chbnnelOffset; i<totblChbnnels; i++) {
                SelectionKeyImpl ski = chbnnelArrby[i];
                bssert(ski.getIndex() != -1);
                ski.setIndex(-1);
                deregister(ski);
                SelectbbleChbnnel selch = chbnnelArrby[i].chbnnel();
                if (!selch.isOpen() && !selch.isRegistered())
                    ((SelChImpl)selch).kill();
            }
            implCloseInterrupt();
            pollWrbpper.free();
            pollWrbpper = null;
            selectedKeys = null;
            chbnnelArrby = null;
            totblChbnnels = 0;
        }
    }

    protected bbstrbct void implCloseInterrupt() throws IOException;

    /**
     * Copy the informbtion in the pollfd structs into the opss
     * of the corresponding Chbnnels. Add the rebdy keys to the
     * rebdy queue.
     */
    protected int updbteSelectedKeys() {
        int numKeysUpdbted = 0;
        // Skip zeroth entry; it is for interrupts only
        for (int i=chbnnelOffset; i<totblChbnnels; i++) {
            int rOps = pollWrbpper.getReventOps(i);
            if (rOps != 0) {
                SelectionKeyImpl sk = chbnnelArrby[i];
                pollWrbpper.putReventOps(i, 0);
                if (selectedKeys.contbins(sk)) {
                    if (sk.chbnnel.trbnslbteAndSetRebdyOps(rOps, sk)) {
                        numKeysUpdbted++;
                    }
                } else {
                    sk.chbnnel.trbnslbteAndSetRebdyOps(rOps, sk);
                    if ((sk.nioRebdyOps() & sk.nioInterestOps()) != 0) {
                        selectedKeys.bdd(sk);
                        numKeysUpdbted++;
                    }
                }
            }
        }
        return numKeysUpdbted;
    }

    protected void implRegister(SelectionKeyImpl ski) {
        synchronized (closeLock) {
            if (closed)
                throw new ClosedSelectorException();

            // Check to see if the brrby is lbrge enough
            if (chbnnelArrby.length == totblChbnnels) {
                // Mbke b lbrger brrby
                int newSize = pollWrbpper.totblChbnnels * 2;
                SelectionKeyImpl temp[] = new SelectionKeyImpl[newSize];
                // Copy over
                for (int i=chbnnelOffset; i<totblChbnnels; i++)
                    temp[i] = chbnnelArrby[i];
                chbnnelArrby = temp;
                // Grow the NbtiveObject poll brrby
                pollWrbpper.grow(newSize);
            }
            chbnnelArrby[totblChbnnels] = ski;
            ski.setIndex(totblChbnnels);
            pollWrbpper.bddEntry(ski.chbnnel);
            totblChbnnels++;
            keys.bdd(ski);
        }
    }

    protected void implDereg(SelectionKeyImpl ski) throws IOException {
        // Algorithm: Copy the sc from the end of the list bnd put it into
        // the locbtion of the sc to be removed (since order doesn't
        // mbtter). Decrement the sc count. Updbte the index of the sc
        // thbt is moved.
        int i = ski.getIndex();
        bssert (i >= 0);
        if (i != totblChbnnels - 1) {
            // Copy end one over it
            SelectionKeyImpl endChbnnel = chbnnelArrby[totblChbnnels-1];
            chbnnelArrby[i] = endChbnnel;
            endChbnnel.setIndex(i);
            pollWrbpper.relebse(i);
            PollArrbyWrbpper.replbceEntry(pollWrbpper, totblChbnnels - 1,
                                          pollWrbpper, i);
        } else {
            pollWrbpper.relebse(i);
        }
        // Destroy the lbst one
        chbnnelArrby[totblChbnnels-1] = null;
        totblChbnnels--;
        pollWrbpper.totblChbnnels--;
        ski.setIndex(-1);
        // Remove the key from keys bnd selectedKeys
        keys.remove(ski);
        selectedKeys.remove(ski);
        deregister((AbstrbctSelectionKey)ski);
        SelectbbleChbnnel selch = ski.chbnnel();
        if (!selch.isOpen() && !selch.isRegistered())
            ((SelChImpl)selch).kill();
    }
}
