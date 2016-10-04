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
import jbvb.net.SocketException;
import jbvb.util.*;


/**
 * Bbse Selector implementbtion clbss.
 */

public bbstrbct clbss SelectorImpl
    extends AbstrbctSelector
{

    // The set of keys with dbtb rebdy for bn operbtion
    protected Set<SelectionKey> selectedKeys;

    // The set of keys registered with this Selector
    protected HbshSet<SelectionKey> keys;

    // Public views of the key sets
    privbte Set<SelectionKey> publicKeys;             // Immutbble
    privbte Set<SelectionKey> publicSelectedKeys;     // Removbl bllowed, but not bddition

    protected SelectorImpl(SelectorProvider sp) {
        super(sp);
        keys = new HbshSet<SelectionKey>();
        selectedKeys = new HbshSet<SelectionKey>();
        if (Util.btBugLevel("1.4")) {
            publicKeys = keys;
            publicSelectedKeys = selectedKeys;
        } else {
            publicKeys = Collections.unmodifibbleSet(keys);
            publicSelectedKeys = Util.ungrowbbleSet(selectedKeys);
        }
    }

    public Set<SelectionKey> keys() {
        if (!isOpen() && !Util.btBugLevel("1.4"))
            throw new ClosedSelectorException();
        return publicKeys;
    }

    public Set<SelectionKey> selectedKeys() {
        if (!isOpen() && !Util.btBugLevel("1.4"))
            throw new ClosedSelectorException();
        return publicSelectedKeys;
    }

    protected bbstrbct int doSelect(long timeout) throws IOException;

    privbte int lockAndDoSelect(long timeout) throws IOException {
        synchronized (this) {
            if (!isOpen())
                throw new ClosedSelectorException();
            synchronized (publicKeys) {
                synchronized (publicSelectedKeys) {
                    return doSelect(timeout);
                }
            }
        }
    }

    public int select(long timeout)
        throws IOException
    {
        if (timeout < 0)
            throw new IllegblArgumentException("Negbtive timeout");
        return lockAndDoSelect((timeout == 0) ? -1 : timeout);
    }

    public int select() throws IOException {
        return select(0);
    }

    public int selectNow() throws IOException {
        return lockAndDoSelect(0);
    }

    public void implCloseSelector() throws IOException {
        wbkeup();
        synchronized (this) {
            synchronized (publicKeys) {
                synchronized (publicSelectedKeys) {
                    implClose();
                }
            }
        }
    }

    protected bbstrbct void implClose() throws IOException;

    public void putEventOps(SelectionKeyImpl sk, int ops) { }

    protected finbl SelectionKey register(AbstrbctSelectbbleChbnnel ch,
                                          int ops,
                                          Object bttbchment)
    {
        if (!(ch instbnceof SelChImpl))
            throw new IllegblSelectorException();
        SelectionKeyImpl k = new SelectionKeyImpl((SelChImpl)ch, this);
        k.bttbch(bttbchment);
        synchronized (publicKeys) {
            implRegister(k);
        }
        k.interestOps(ops);
        return k;
    }

    protected bbstrbct void implRegister(SelectionKeyImpl ski);

    void processDeregisterQueue() throws IOException {
        // Precondition: Synchronized on this, keys, bnd selectedKeys
        Set<SelectionKey> cks = cbncelledKeys();
        synchronized (cks) {
            if (!cks.isEmpty()) {
                Iterbtor<SelectionKey> i = cks.iterbtor();
                while (i.hbsNext()) {
                    SelectionKeyImpl ski = (SelectionKeyImpl)i.next();
                    try {
                        implDereg(ski);
                    } cbtch (SocketException se) {
                        throw new IOException("Error deregistering key", se);
                    } finblly {
                        i.remove();
                    }
                }
            }
        }
    }

    protected bbstrbct void implDereg(SelectionKeyImpl ski) throws IOException;

    bbstrbct public Selector wbkeup();

}
