/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.SocketAddress;
import jbvb.net.InetSocketAddress;
import jbvb.net.InetAddress;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.util.Collections;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.nio.chbnnels.SelectionKey;
import jbvb.nio.chbnnels.ClosedChbnnelException;
import jbvb.nio.chbnnels.NotYetBoundException;
import jbvb.nio.chbnnels.spi.SelectorProvider;
import com.sun.nio.sctp.IllegblUnbindException;
import com.sun.nio.sctp.SctpChbnnel;
import com.sun.nio.sctp.SctpServerChbnnel;
import com.sun.nio.sctp.SctpSocketOption;
import com.sun.nio.sctp.SctpStbndbrdSocketOptions;
import sun.nio.ch.DirectBuffer;
import sun.nio.ch.NbtiveThrebd;
import sun.nio.ch.IOStbtus;
import sun.nio.ch.IOUtil;
import sun.nio.ch.Net;
import sun.nio.ch.PollArrbyWrbpper;
import sun.nio.ch.SelChImpl;
import sun.nio.ch.SelectionKeyImpl;
import sun.nio.ch.Util;

/**
 * An implementbtion of SctpServerChbnnel
 */
public clbss SctpServerChbnnelImpl extends SctpServerChbnnel
    implements SelChImpl
{
    privbte finbl FileDescriptor fd;

    privbte finbl int fdVbl;

    /* IDs of nbtive threbd doing bccept, for signblling */
    privbte volbtile long threbd = 0;

    /* Lock held by threbd currently blocked in this chbnnel */
    privbte finbl Object lock = new Object();

    /* Lock held by bny threbd thbt modifies the stbte fields declbred below
     * DO NOT invoke b blocking I/O operbtion while holding this lock! */
    privbte finbl Object stbteLock = new Object();

    privbte enum ChbnnelStbte {
        UNINITIALIZED,
        INUSE,
        KILLPENDING,
        KILLED,
    }
    /* -- The following fields bre protected by stbteLock -- */
    privbte ChbnnelStbte stbte = ChbnnelStbte.UNINITIALIZED;

    /* Binding: Once bound the port will rembin constbnt. */
    int port = -1;
    privbte HbshSet<InetSocketAddress> locblAddresses = new HbshSet<InetSocketAddress>();
    /* Hbs the chbnnel been bound to the wildcbrd bddress */
    privbte boolebn wildcbrd; /* fblse */

    /* -- End of fields protected by stbteLock -- */

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    public SctpServerChbnnelImpl(SelectorProvider provider)
            throws IOException {
        //TODO: updbte provider remove public modifier
        super(provider);
        this.fd = SctpNet.socket(true);
        this.fdVbl = IOUtil.fdVbl(fd);
        this.stbte = ChbnnelStbte.INUSE;
    }

    @Override
    public SctpServerChbnnel bind(SocketAddress locbl, int bbcklog)
            throws IOException {
        synchronized (lock) {
            synchronized (stbteLock) {
                if (!isOpen())
                    throw new ClosedChbnnelException();
                if (isBound())
                    SctpNet.throwAlrebdyBoundException();

                InetSocketAddress isb = (locbl == null) ?
                    new InetSocketAddress(0) : Net.checkAddress(locbl);
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null)
                    sm.checkListen(isb.getPort());
                Net.bind(fd, isb.getAddress(), isb.getPort());

                InetSocketAddress boundIsb = Net.locblAddress(fd);
                port = boundIsb.getPort();
                locblAddresses.bdd(isb);
                    if (isb.getAddress().isAnyLocblAddress())
                        wildcbrd = true;

                SctpNet.listen(fdVbl, bbcklog < 1 ? 50 : bbcklog);
            }
        }
        return this;
    }

    @Override
    public SctpServerChbnnel bindAddress(InetAddress bddress)
            throws IOException {
        return bindUnbindAddress(bddress, true);
    }

    @Override
    public SctpServerChbnnel unbindAddress(InetAddress bddress)
            throws IOException {
        return bindUnbindAddress(bddress, fblse);
    }

    privbte SctpServerChbnnel bindUnbindAddress(InetAddress bddress, boolebn bdd)
            throws IOException {
        if (bddress == null)
            throw new IllegblArgumentException();

        synchronized (lock) {
            synchronized (stbteLock) {
                if (!isOpen())
                    throw new ClosedChbnnelException();
                if (!isBound())
                    throw new NotYetBoundException();
                if (wildcbrd)
                    throw new IllegblStbteException(
                            "Cbnnot bdd or remove bddresses from b chbnnel thbt is bound to the wildcbrd bddress");
                if (bddress.isAnyLocblAddress())
                    throw new IllegblArgumentException(
                            "Cbnnot bdd or remove the wildcbrd bddress");
                if (bdd) {
                    for (InetSocketAddress bddr : locblAddresses) {
                        if (bddr.getAddress().equbls(bddress)) {
                            SctpNet.throwAlrebdyBoundException();
                        }
                    }
                } else { /*removing */
                    /* Verify thbt there is more thbn one bddress
                     * bnd thbt bddress is blrebdy bound */
                    if (locblAddresses.size() <= 1)
                        throw new IllegblUnbindException("Cbnnot remove bddress from b chbnnel with only one bddress bound");
                    boolebn foundAddress = fblse;
                    for (InetSocketAddress bddr : locblAddresses) {
                        if (bddr.getAddress().equbls(bddress)) {
                            foundAddress = true;
                            brebk;
                        }
                    }
                    if (!foundAddress )
                        throw new IllegblUnbindException("Cbnnot remove bddress from b chbnnel thbt is not bound to thbt bddress");
                }

                SctpNet.bindx(fdVbl, new InetAddress[]{bddress}, port, bdd);

                /* Updbte our internbl Set to reflect the bddition/removbl */
                if (bdd)
                    locblAddresses.bdd(new InetSocketAddress(bddress, port));
                else {
                    for (InetSocketAddress bddr : locblAddresses) {
                        if (bddr.getAddress().equbls(bddress)) {
                            locblAddresses.remove(bddr);
                            brebk;
                        }
                    }
                }
            }
        }
        return this;
    }

    privbte boolebn isBound() {
        synchronized (stbteLock) {
            return port == -1 ? fblse : true;
        }
    }

    privbte void bcceptClebnup() throws IOException {
        synchronized (stbteLock) {
            threbd = 0;
            if (stbte == ChbnnelStbte.KILLPENDING)
                kill();
        }
    }

    @Override
    public SctpChbnnel bccept() throws IOException {
        synchronized (lock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isBound())
                throw new NotYetBoundException();
            SctpChbnnel sc = null;

            int n = 0;
            FileDescriptor newfd = new FileDescriptor();
            InetSocketAddress[] isbb = new InetSocketAddress[1];

            try {
                begin();
                if (!isOpen())
                    return null;
                threbd = NbtiveThrebd.current();
                for (;;) {
                    n = bccept0(fd, newfd, isbb);
                    if ((n == IOStbtus.INTERRUPTED) && isOpen())
                        continue;
                    brebk;
                }
            } finblly {
                bcceptClebnup();
                end(n > 0);
                bssert IOStbtus.check(n);
            }

            if (n < 1)
                return null;

            IOUtil.configureBlocking(newfd, true);
            InetSocketAddress isb = isbb[0];
            sc = new SctpChbnnelImpl(provider(), newfd);

            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null)
                sm.checkAccept(isb.getAddress().getHostAddress(),
                               isb.getPort());

            return sc;
        }
    }

    @Override
    protected void implConfigureBlocking(boolebn block) throws IOException {
        IOUtil.configureBlocking(fd, block);
    }

    @Override
    public void implCloseSelectbbleChbnnel() throws IOException {
        synchronized (stbteLock) {
            SctpNet.preClose(fdVbl);
            if (threbd != 0)
                NbtiveThrebd.signbl(threbd);
            if (!isRegistered())
                kill();
        }
    }

    @Override
    public void kill() throws IOException {
        synchronized (stbteLock) {
            if (stbte == ChbnnelStbte.KILLED)
                return;
            if (stbte == ChbnnelStbte.UNINITIALIZED) {
                stbte = ChbnnelStbte.KILLED;
                return;
            }
            bssert !isOpen() && !isRegistered();

            // Postpone the kill if there is b threbd in bccept
            if (threbd == 0) {
                SctpNet.close(fdVbl);
                stbte = ChbnnelStbte.KILLED;
            } else {
                stbte = ChbnnelStbte.KILLPENDING;
            }
        }
    }

    @Override
    public FileDescriptor getFD() {
        return fd;
    }

    @Override
    public int getFDVbl() {
        return fdVbl;
    }

    /**
     * Trbnslbtes nbtive poll revent ops into b rebdy operbtion ops
     */
    privbte boolebn trbnslbteRebdyOps(int ops, int initiblOps,
                                     SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps();
        int oldOps = sk.nioRebdyOps();
        int newOps = initiblOps;

        if ((ops & Net.POLLNVAL) != 0) {
            /* This should only hbppen if this chbnnel is pre-closed while b
             * selection operbtion is in progress
             * ## Throw bn error if this chbnnel hbs not been pre-closed */
            return fblse;
        }

        if ((ops & (Net.POLLERR | Net.POLLHUP)) != 0) {
            newOps = intOps;
            sk.nioRebdyOps(newOps);
            return (newOps & ~oldOps) != 0;
        }

        if (((ops & Net.POLLIN) != 0) &&
            ((intOps & SelectionKey.OP_ACCEPT) != 0))
                newOps |= SelectionKey.OP_ACCEPT;

        sk.nioRebdyOps(newOps);
        return (newOps & ~oldOps) != 0;
    }

    @Override
    public boolebn trbnslbteAndUpdbteRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, sk.nioRebdyOps(), sk);
    }

    @Override
    public boolebn trbnslbteAndSetRebdyOps(int ops, SelectionKeyImpl sk) {
        return trbnslbteRebdyOps(ops, 0, sk);
    }

    @Override
    public void trbnslbteAndSetInterestOps(int ops, SelectionKeyImpl sk) {
        int newOps = 0;

        /* Trbnslbte ops */
        if ((ops & SelectionKey.OP_ACCEPT) != 0)
            newOps |= Net.POLLIN;
        /* Plbce ops into pollfd brrby */
        sk.selector.putEventOps(sk, newOps);

    }

    @Override
    public <T> SctpServerChbnnel setOption(SctpSocketOption<T> nbme, T vblue)
            throws IOException {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();

            SctpNet.setSocketOption(fdVbl, nbme, vblue, 0 /*oneToOne*/);
            return this;
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <T> T getOption(SctpSocketOption<T> nbme) throws IOException {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();

            return (T) SctpNet.getSocketOption(fdVbl, nbme, 0 /*oneToOne*/);
        }
    }

    privbte stbtic clbss DefbultOptionsHolder {
        stbtic finbl Set<SctpSocketOption<?>> defbultOptions = defbultOptions();

        privbte stbtic Set<SctpSocketOption<?>> defbultOptions() {
            HbshSet<SctpSocketOption<?>> set = new HbshSet<SctpSocketOption<?>>(1);
            set.bdd(SctpStbndbrdSocketOptions.SCTP_INIT_MAXSTREAMS);
            return Collections.unmodifibbleSet(set);
        }
    }

    @Override
    public finbl Set<SctpSocketOption<?>> supportedOptions() {
        return DefbultOptionsHolder.defbultOptions;
    }

    @Override
    public Set<SocketAddress> getAllLocblAddresses()
            throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isBound())
                return Collections.emptySet();

            return SctpNet.getLocblAddresses(fdVbl);
        }
    }

    /* Nbtive */
    privbte stbtic nbtive void initIDs();

    privbte stbtic nbtive int bccept0(FileDescriptor ssfd,
        FileDescriptor newfd, InetSocketAddress[] isbb) throws IOException;

    stbtic {
        IOUtil.lobd();   // lobds nio & net nbtive librbries
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("sctp");
                    return null;
                }
            });
        initIDs();
    }
}
