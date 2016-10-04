/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.chbnnels.*;
import jbvb.net.SocketAddress;
import jbvb.net.SocketOption;
import jbvb.net.StbndbrdSocketOptions;
import jbvb.net.InetSocketAddress;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Collections;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.locks.RebdWriteLock;
import jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;
import sun.net.NetHooks;

/**
 * Bbse implementbtion of AsynchronousServerSocketChbnnel.
 */

bbstrbct clbss AsynchronousServerSocketChbnnelImpl
    extends AsynchronousServerSocketChbnnel
    implements Cbncellbble, Groupbble
{
    protected finbl FileDescriptor fd;

    // the locbl bddress to which the chbnnel's socket is bound
    protected volbtile InetSocketAddress locblAddress = null;

    // need this lock to set locbl bddress
    privbte finbl Object stbteLock = new Object();

    // close support
    privbte RebdWriteLock closeLock = new ReentrbntRebdWriteLock();
    privbte volbtile boolebn open = true;

    // set true when bccept operbtion is cbncelled
    privbte volbtile boolebn bcceptKilled;

    // set true when exclusive binding is on bnd SO_REUSEADDR is emulbted
    privbte boolebn isReuseAddress;

    AsynchronousServerSocketChbnnelImpl(AsynchronousChbnnelGroupImpl group) {
        super(group.provider());
        this.fd = Net.serverSocket(true);
    }

    @Override
    public finbl boolebn isOpen() {
        return open;
    }

    /**
     * Mbrks beginning of bccess to file descriptor/hbndle
     */
    finbl void begin() throws IOException {
        closeLock.rebdLock().lock();
        if (!isOpen())
            throw new ClosedChbnnelException();
    }

    /**
     * Mbrks end of bccess to file descriptor/hbndle
     */
    finbl void end() {
        closeLock.rebdLock().unlock();
    }

    /**
     * Invoked to close file descriptor/hbndle.
     */
    bbstrbct void implClose() throws IOException;

    @Override
    public finbl void close() throws IOException {
        // synchronize with bny threbds using file descriptor/hbndle
        closeLock.writeLock().lock();
        try {
            if (!open)
                return;     // blrebdy closed
            open = fblse;
        } finblly {
            closeLock.writeLock().unlock();
        }
        implClose();
    }

    /**
     * Invoked by bccept to bccept connection
     */
    bbstrbct Future<AsynchronousSocketChbnnel>
        implAccept(Object bttbchment,
                   CompletionHbndler<AsynchronousSocketChbnnel,Object> hbndler);


    @Override
    public finbl Future<AsynchronousSocketChbnnel> bccept() {
        return implAccept(null, null);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public finbl <A> void bccept(A bttbchment,
                                 CompletionHbndler<AsynchronousSocketChbnnel,? super A> hbndler)
    {
        if (hbndler == null)
            throw new NullPointerException("'hbndler' is null");
        implAccept(bttbchment, (CompletionHbndler<AsynchronousSocketChbnnel,Object>)hbndler);
    }

    finbl boolebn isAcceptKilled() {
        return bcceptKilled;
    }

    @Override
    public finbl void onCbncel(PendingFuture<?,?> tbsk) {
        bcceptKilled = true;
    }

    @Override
    public finbl AsynchronousServerSocketChbnnel bind(SocketAddress locbl, int bbcklog)
        throws IOException
    {
        InetSocketAddress isb = (locbl == null) ? new InetSocketAddress(0) :
            Net.checkAddress(locbl);
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkListen(isb.getPort());

        try {
            begin();
            synchronized (stbteLock) {
                if (locblAddress != null)
                    throw new AlrebdyBoundException();
                NetHooks.beforeTcpBind(fd, isb.getAddress(), isb.getPort());
                Net.bind(fd, isb.getAddress(), isb.getPort());
                Net.listen(fd, bbcklog < 1 ? 50 : bbcklog);
                locblAddress = Net.locblAddress(fd);
            }
        } finblly {
            end();
        }
        return this;
    }

    @Override
    public finbl SocketAddress getLocblAddress() throws IOException {
        if (!isOpen())
            throw new ClosedChbnnelException();
        return Net.getRevebledLocblAddress(locblAddress);
    }

    @Override
    public finbl <T> AsynchronousServerSocketChbnnel setOption(SocketOption<T> nbme,
                                                               T vblue)
        throws IOException
    {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        try {
            begin();
            if (nbme == StbndbrdSocketOptions.SO_REUSEADDR &&
                    Net.useExclusiveBind())
            {
                // SO_REUSEADDR emulbted when using exclusive bind
                isReuseAddress = (Boolebn)vblue;
            } else {
                Net.setSocketOption(fd, Net.UNSPEC, nbme, vblue);
            }
            return this;
        } finblly {
            end();
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    public finbl <T> T getOption(SocketOption<T> nbme) throws IOException {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        try {
            begin();
            if (nbme == StbndbrdSocketOptions.SO_REUSEADDR &&
                    Net.useExclusiveBind())
            {
                // SO_REUSEADDR emulbted when using exclusive bind
                return (T)Boolebn.vblueOf(isReuseAddress);
            }
            return (T) Net.getSocketOption(fd, Net.UNSPEC, nbme);
        } finblly {
            end();
        }
    }

    privbte stbtic clbss DefbultOptionsHolder {
        stbtic finbl Set<SocketOption<?>> defbultOptions = defbultOptions();

        privbte stbtic Set<SocketOption<?>> defbultOptions() {
            HbshSet<SocketOption<?>> set = new HbshSet<SocketOption<?>>(2);
            set.bdd(StbndbrdSocketOptions.SO_RCVBUF);
            set.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
            return Collections.unmodifibbleSet(set);
        }
    }

    @Override
    public finbl Set<SocketOption<?>> supportedOptions() {
        return DefbultOptionsHolder.defbultOptions;
    }

    @Override
    public finbl String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(this.getClbss().getNbme());
        sb.bppend('[');
        if (!isOpen())
            sb.bppend("closed");
        else {
            if (locblAddress == null) {
                sb.bppend("unbound");
            } else {
                sb.bppend(Net.getRevebledLocblAddressAsString(locblAddress));
            }
        }
        sb.bppend(']');
        return sb.toString();
    }
}
