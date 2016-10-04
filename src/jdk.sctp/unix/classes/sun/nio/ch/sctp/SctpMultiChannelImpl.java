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

import jbvb.net.InetAddress;
import jbvb.net.SocketAddress;
import jbvb.net.SocketException;
import jbvb.net.InetSocketAddress;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.util.Collections;
import jbvb.util.Mbp.Entry;
import jbvb.util.Iterbtor;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.SelectionKey;
import jbvb.nio.chbnnels.ClosedChbnnelException;
import jbvb.nio.chbnnels.NotYetBoundException;
import jbvb.nio.chbnnels.spi.SelectorProvider;
import com.sun.nio.sctp.AbstrbctNotificbtionHbndler;
import com.sun.nio.sctp.Associbtion;
import com.sun.nio.sctp.AssocibtionChbngeNotificbtion;
import com.sun.nio.sctp.HbndlerResult;
import com.sun.nio.sctp.IllegblReceiveException;
import com.sun.nio.sctp.InvblidStrebmException;
import com.sun.nio.sctp.IllegblUnbindException;
import com.sun.nio.sctp.NotificbtionHbndler;
import com.sun.nio.sctp.MessbgeInfo;
import com.sun.nio.sctp.SctpChbnnel;
import com.sun.nio.sctp.SctpMultiChbnnel;
import com.sun.nio.sctp.SctpSocketOption;
import sun.nio.ch.DirectBuffer;
import sun.nio.ch.NbtiveThrebd;
import sun.nio.ch.IOStbtus;
import sun.nio.ch.IOUtil;
import sun.nio.ch.Net;
import sun.nio.ch.PollArrbyWrbpper;
import sun.nio.ch.SelChImpl;
import sun.nio.ch.SelectionKeyImpl;
import sun.nio.ch.Util;
import stbtic com.sun.nio.sctp.SctpStbndbrdSocketOptions.*;
import stbtic sun.nio.ch.sctp.ResultContbiner.*;

/**
 * An implementbtion of SctpMultiChbnnel
 */
public clbss SctpMultiChbnnelImpl extends SctpMultiChbnnel
    implements SelChImpl
{
    privbte finbl FileDescriptor fd;

    privbte finbl int fdVbl;

    /* IDs of nbtive threbds doing send bnd receives, for signblling */
    privbte volbtile long receiverThrebd = 0;
    privbte volbtile long senderThrebd = 0;

    /* Lock held by current receiving threbd */
    privbte finbl Object receiveLock = new Object();

    /* Lock held by current sending threbd */
    privbte finbl Object sendLock = new Object();

    /* Lock held by bny threbd thbt modifies the stbte fields declbred below
     * DO NOT invoke b blocking I/O operbtion while holding this lock! */
    privbte finbl Object stbteLock = new Object();

    privbte enum ChbnnelStbte {
        UNINITIALIZED,
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

    /* Keeps b mbp of bddresses to bssocibtion, bnd visb versb */
    privbte HbshMbp<SocketAddress, Associbtion> bddressMbp =
                         new HbshMbp<SocketAddress, Associbtion>();
    privbte HbshMbp<Associbtion, Set<SocketAddress>> bssocibtionMbp =
                         new HbshMbp<Associbtion, Set<SocketAddress>>();

    /* -- End of fields protected by stbteLock -- */

    /* If bn bssocibtion hbs been shutdown mbrk it for removbl bfter
     * the user hbndler hbs been invoked */
    privbte finbl ThrebdLocbl<Associbtion> bssocibtionToRemove =
        new ThrebdLocbl<Associbtion>() {
             @Override protected Associbtion initiblVblue() {
                 return null;
            }
    };

    /* A notificbtion hbndler cbnnot invoke receive */
    privbte finbl ThrebdLocbl<Boolebn> receiveInvoked =
        new ThrebdLocbl<Boolebn>() {
             @Override protected Boolebn initiblVblue() {
                 return Boolebn.FALSE;
            }
    };

    public SctpMultiChbnnelImpl(SelectorProvider provider)
            throws IOException {
        //TODO: updbte provider, remove public modifier
        super(provider);
        this.fd = SctpNet.socket(fblse /*one-to-mbny*/);
        this.fdVbl = IOUtil.fdVbl(fd);
    }

    @Override
    public SctpMultiChbnnel bind(SocketAddress locbl, int bbcklog)
            throws IOException {
        synchronized (receiveLock) {
            synchronized (sendLock) {
                synchronized (stbteLock) {
                    ensureOpen();
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
        }
        return this;
    }

    @Override
    public SctpMultiChbnnel bindAddress(InetAddress bddress)
            throws IOException {
        return bindUnbindAddress(bddress, true);
    }

    @Override
    public SctpMultiChbnnel unbindAddress(InetAddress bddress)
            throws IOException {
        return bindUnbindAddress(bddress, fblse);
    }

    privbte SctpMultiChbnnel bindUnbindAddress(InetAddress bddress,
                                               boolebn bdd)
            throws IOException {
        if (bddress == null)
            throw new IllegblArgumentException();

        synchronized (receiveLock) {
            synchronized (sendLock) {
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
        }
        return this;
    }

    @Override
    public Set<Associbtion> bssocibtions()
            throws ClosedChbnnelException, NotYetBoundException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
            if (!isBound())
                throw new NotYetBoundException();

            return Collections.unmodifibbleSet(bssocibtionMbp.keySet());
        }
    }

    privbte boolebn isBound() {
        synchronized (stbteLock) {
            return port == -1 ? fblse : true;
        }
    }

    privbte void ensureOpen() throws IOException {
        synchronized (stbteLock) {
            if (!isOpen())
                throw new ClosedChbnnelException();
        }
    }

    privbte void receiverClebnup() throws IOException {
        synchronized (stbteLock) {
            receiverThrebd = 0;
            if (stbte == ChbnnelStbte.KILLPENDING)
                kill();
        }
    }

    privbte void senderClebnup() throws IOException {
        synchronized (stbteLock) {
            senderThrebd = 0;
            if (stbte == ChbnnelStbte.KILLPENDING)
                kill();
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

            if (receiverThrebd != 0)
                NbtiveThrebd.signbl(receiverThrebd);

            if (senderThrebd != 0)
                NbtiveThrebd.signbl(senderThrebd);

            if (!isRegistered())
                kill();
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
            ((intOps & SelectionKey.OP_READ) != 0))
            newOps |= SelectionKey.OP_READ;

        if (((ops & Net.POLLOUT) != 0) &&
            ((intOps & SelectionKey.OP_WRITE) != 0))
            newOps |= SelectionKey.OP_WRITE;

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
        if ((ops & SelectionKey.OP_READ) != 0)
            newOps |= Net.POLLIN;
        if ((ops & SelectionKey.OP_WRITE) != 0)
            newOps |= Net.POLLOUT;
        sk.selector.putEventOps(sk, newOps);
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

            /* Postpone the kill if there is b threbd sending or receiving. */
            if (receiverThrebd == 0 && senderThrebd == 0) {
                SctpNet.close(fdVbl);
                stbte = ChbnnelStbte.KILLED;
            } else {
                stbte = ChbnnelStbte.KILLPENDING;
            }
        }
    }

    @Override
    public <T> SctpMultiChbnnel setOption(SctpSocketOption<T> nbme,
                                          T vblue,
                                          Associbtion bssocibtion)
            throws IOException {
        if (nbme == null)
            throw new NullPointerException();
        if (!(supportedOptions().contbins(nbme)))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            if (bssocibtion != null && (nbme.equbls(SCTP_PRIMARY_ADDR) ||
                    nbme.equbls(SCTP_SET_PEER_PRIMARY_ADDR))) {
                checkAssocibtion(bssocibtion);
            }
            if (!isOpen())
                throw new ClosedChbnnelException();

            int bssocId = bssocibtion == null ? 0 : bssocibtion.bssocibtionID();
            SctpNet.setSocketOption(fdVbl, nbme, vblue, bssocId);
        }
        return this;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <T> T getOption(SctpSocketOption<T> nbme, Associbtion bssocibtion)
            throws IOException {
        if (nbme == null)
            throw new NullPointerException();
        if (!supportedOptions().contbins(nbme))
            throw new UnsupportedOperbtionException("'" + nbme + "' not supported");

        synchronized (stbteLock) {
            if (bssocibtion != null && (nbme.equbls(SCTP_PRIMARY_ADDR) ||
                    nbme.equbls(SCTP_SET_PEER_PRIMARY_ADDR))) {
                checkAssocibtion(bssocibtion);
            }
            if (!isOpen())
                throw new ClosedChbnnelException();

            int bssocId = bssocibtion == null ? 0 : bssocibtion.bssocibtionID();
            return (T)SctpNet.getSocketOption(fdVbl, nbme, bssocId);
        }
    }

    privbte stbtic clbss DefbultOptionsHolder {
        stbtic finbl Set<SctpSocketOption<?>> defbultOptions = defbultOptions();

        privbte stbtic Set<SctpSocketOption<?>> defbultOptions() {
            HbshSet<SctpSocketOption<?>> set = new HbshSet<SctpSocketOption<?>>(10);
            set.bdd(SCTP_DISABLE_FRAGMENTS);
            set.bdd(SCTP_EXPLICIT_COMPLETE);
            set.bdd(SCTP_FRAGMENT_INTERLEAVE);
            set.bdd(SCTP_INIT_MAXSTREAMS);
            set.bdd(SCTP_NODELAY);
            set.bdd(SCTP_PRIMARY_ADDR);
            set.bdd(SCTP_SET_PEER_PRIMARY_ADDR);
            set.bdd(SO_SNDBUF);
            set.bdd(SO_RCVBUF);
            set.bdd(SO_LINGER);
            return Collections.unmodifibbleSet(set);
        }
    }

    @Override
    public finbl Set<SctpSocketOption<?>> supportedOptions() {
        return DefbultOptionsHolder.defbultOptions;
    }

    @Override
    public <T> MessbgeInfo receive(ByteBuffer buffer,
                                   T bttbchment,
                                   NotificbtionHbndler<T> hbndler)
            throws IOException {
        if (buffer == null)
            throw new IllegblArgumentException("buffer cbnnot be null");

        if (buffer.isRebdOnly())
            throw new IllegblArgumentException("Rebd-only buffer");

        if (receiveInvoked.get())
            throw new IllegblReceiveException(
                    "cbnnot invoke receive from hbndler");
        receiveInvoked.set(Boolebn.TRUE);

        try {
            ResultContbiner resultContbiner = new ResultContbiner();
            do {
                resultContbiner.clebr();
                synchronized (receiveLock) {
                    ensureOpen();
                    if (!isBound())
                        throw new NotYetBoundException();

                    int n = 0;
                    try {
                        begin();

                        synchronized (stbteLock) {
                            if(!isOpen())
                                return null;
                            receiverThrebd = NbtiveThrebd.current();
                        }

                        do {
                            n = receive(fdVbl, buffer, resultContbiner);
                        } while ((n == IOStbtus.INTERRUPTED) && isOpen());

                    } finblly {
                        receiverClebnup();
                        end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                        bssert IOStbtus.check(n);
                    }

                    if (!resultContbiner.isNotificbtion()) {
                        /* messbge or nothing */
                        if (resultContbiner.hbsSomething()) {
                            /* Set the bssocibtion before returning */
                            MessbgeInfoImpl info =
                                    resultContbiner.getMessbgeInfo();
                            info.setAssocibtion(lookupAssocibtion(info.
                                    bssocibtionID()));
                            SecurityMbnbger sm = System.getSecurityMbnbger();
                            if (sm != null) {
                                InetSocketAddress isb  = (InetSocketAddress)info.bddress();
                                if (!bddressMbp.contbinsKey(isb)) {
                                    /* must be b new bssocibtion */
                                    try {
                                        sm.checkAccept(isb.getAddress().getHostAddress(),
                                                       isb.getPort());
                                    } cbtch (SecurityException se) {
                                        buffer.clebr();
                                        throw se;
                                    }
                                }
                            }

                            bssert info.bssocibtion() != null;
                            return info;
                        } else  {
                          /* Non-blocking mby return null if nothing bvbilbble*/
                            return null;
                        }
                    } else { /* notificbtion */
                        synchronized (stbteLock) {
                            hbndleNotificbtionInternbl(
                                    resultContbiner);
                        }
                    }
                } /* receiveLock */
            } while (hbndler == null ? true :
                (invokeNotificbtionHbndler(resultContbiner, hbndler, bttbchment)
                 == HbndlerResult.CONTINUE));
        } finblly {
            receiveInvoked.set(Boolebn.FALSE);
        }

        return null;
    }

    privbte int receive(int fd,
                        ByteBuffer dst,
                        ResultContbiner resultContbiner)
            throws IOException {
        int pos = dst.position();
        int lim = dst.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        if (dst instbnceof DirectBuffer && rem > 0)
            return receiveIntoNbtiveBuffer(fd, resultContbiner, dst, rem, pos);

        /* Substitute b nbtive buffer. */
        int newSize = Mbth.mbx(rem, 1);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(newSize);
        try {
            int n = receiveIntoNbtiveBuffer(fd, resultContbiner, bb, newSize, 0);
            bb.flip();
            if (n > 0 && rem > 0)
                dst.put(bb);
            return n;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    privbte int receiveIntoNbtiveBuffer(int fd,
                                        ResultContbiner resultContbiner,
                                        ByteBuffer bb,
                                        int rem,
                                        int pos)
            throws IOException {
        int n = receive0(fd, resultContbiner, ((DirectBuffer)bb).bddress() + pos, rem);
        if (n > 0)
            bb.position(pos + n);
        return n;
    }

    privbte InternblNotificbtionHbndler internblNotificbtionHbndler =
            new InternblNotificbtionHbndler();

    privbte void hbndleNotificbtionInternbl(ResultContbiner resultContbiner)
    {
        invokeNotificbtionHbndler(resultContbiner,
                internblNotificbtionHbndler, null);
    }

    privbte clbss InternblNotificbtionHbndler
            extends AbstrbctNotificbtionHbndler<Object>
    {
        @Override
        public HbndlerResult hbndleNotificbtion(
                AssocibtionChbngeNotificbtion not, Object unused) {
            AssocibtionChbnge sbc = (AssocibtionChbnge) not;

            /* Updbte mbp to reflect chbnge in bssocibtion */
            switch (not.event()) {
                cbse COMM_UP :
                    Associbtion newAssocibtion = new AssocibtionImpl
                       (sbc.bssocId(), sbc.mbxInStrebms(), sbc.mbxOutStrebms());
                    bddAssocibtion(newAssocibtion);
                    brebk;
                cbse SHUTDOWN :
                cbse COMM_LOST :
                //cbse RESTART: ???
                    /* mbrk bssocibtion for removbl bfter user hbndler invoked*/
                    bssocibtionToRemove.set(lookupAssocibtion(sbc.bssocId()));
            }
            return HbndlerResult.CONTINUE;
        }
    }

    privbte <T> HbndlerResult invokeNotificbtionHbndler(
                                   ResultContbiner resultContbiner,
                                   NotificbtionHbndler<T> hbndler,
                                   T bttbchment) {
        HbndlerResult result;
        SctpNotificbtion notificbtion = resultContbiner.notificbtion();
        notificbtion.setAssocibtion(lookupAssocibtion(notificbtion.bssocId()));

        if (!(hbndler instbnceof AbstrbctNotificbtionHbndler)) {
            result = hbndler.hbndleNotificbtion(notificbtion, bttbchment);
        } else { /* AbstrbctNotificbtionHbndler */
            AbstrbctNotificbtionHbndler<T> bbsHbndler =
                    (AbstrbctNotificbtionHbndler<T>)hbndler;
            switch(resultContbiner.type()) {
                cbse ASSOCIATION_CHANGED :
                    result = bbsHbndler.hbndleNotificbtion(
                            resultContbiner.getAssocibtionChbnged(), bttbchment);
                    brebk;
                cbse PEER_ADDRESS_CHANGED :
                    result = bbsHbndler.hbndleNotificbtion(
                            resultContbiner.getPeerAddressChbnged(), bttbchment);
                    brebk;
                cbse SEND_FAILED :
                    result = bbsHbndler.hbndleNotificbtion(
                            resultContbiner.getSendFbiled(), bttbchment);
                    brebk;
                cbse SHUTDOWN :
                    result =  bbsHbndler.hbndleNotificbtion(
                            resultContbiner.getShutdown(), bttbchment);
                    brebk;
                defbult :
                    /* implementbtion specific hbndlers */
                    result =  bbsHbndler.hbndleNotificbtion(
                            resultContbiner.notificbtion(), bttbchment);
            }
        }

        if (!(hbndler instbnceof InternblNotificbtionHbndler)) {
            /* Only remove bssocibtions bfter user hbndler
             * hbs finished with them */
            Associbtion bssoc = bssocibtionToRemove.get();
            if (bssoc != null) {
                removeAssocibtion(bssoc);
                bssocibtionToRemove.set(null);
            }

        }

        return result;
    }

    privbte Associbtion lookupAssocibtion(int bssocId) {
        /* Lookup the bssocibtion in our internbl mbp */
        synchronized (stbteLock) {
            Set<Associbtion> bssocs = bssocibtionMbp.keySet();
            for (Associbtion b : bssocs) {
                if (b.bssocibtionID() == bssocId) {
                    return b;
                }
            }
        }
        return null;
    }

    privbte void bddAssocibtion(Associbtion bssocibtion) {
        synchronized (stbteLock) {
            int bssocId = bssocibtion.bssocibtionID();
            Set<SocketAddress> bddresses = null;

            try {
                bddresses = SctpNet.getRemoteAddresses(fdVbl, bssocId);
            } cbtch (IOException unused) {
                /* OK, determining connected bddresses mby not be possible
                 * shutdown, connection lost, etc */
            }

            bssocibtionMbp.put(bssocibtion, bddresses);
            if (bddresses != null) {
                for (SocketAddress bddr : bddresses)
                    bddressMbp.put(bddr, bssocibtion);
            }
        }
    }

    privbte void removeAssocibtion(Associbtion bssocibtion) {
        synchronized (stbteLock) {
            int bssocId = bssocibtion.bssocibtionID();
            Set<SocketAddress> bddresses = null;

             try {
                bddresses = SctpNet.getRemoteAddresses(fdVbl, bssocId);
            } cbtch (IOException unused) {
                /* OK, determining connected bddresses mby not be possible
                 * shutdown, connection lost, etc */
            }

            Set<Associbtion> bssocs = bssocibtionMbp.keySet();
            for (Associbtion b : bssocs) {
                if (b.bssocibtionID() == bssocId) {
                    bssocibtionMbp.remove(b);
                    brebk;
                }
            }
            if (bddresses != null) {
                for (SocketAddress bddr : bddresses)
                    bddressMbp.remove(bddr);
            } else {
                /* We cbnnot determine the connected bddresses */
                Set<jbvb.util.Mbp.Entry<SocketAddress, Associbtion>> bddrAssocs =
                        bddressMbp.entrySet();
                Iterbtor<Entry<SocketAddress, Associbtion>> iterbtor = bddrAssocs.iterbtor();
                while (iterbtor.hbsNext()) {
                    Entry<SocketAddress, Associbtion> entry = iterbtor.next();
                    if (entry.getVblue().equbls(bssocibtion)) {
                        iterbtor.remove();
                    }
                }
            }
        }
    }

    /**
     * @throws  IllegblArgumentException
     *          If the given bssocibtion is not controlled by this chbnnel
     *
     * @return  {@code true} if, bnd only if, the given bssocibtion is one
     *          of the current bssocibtions controlled by this chbnnel
     */
    privbte boolebn checkAssocibtion(Associbtion messbgeAssoc) {
        synchronized (stbteLock) {
            for (Associbtion bssocibtion : bssocibtionMbp.keySet()) {
                if (messbgeAssoc.equbls(bssocibtion)) {
                    return true;
                }
            }
        }
        throw new IllegblArgumentException(
              "Given Associbtion is not controlled by this chbnnel");
    }

    privbte void checkStrebmNumber(Associbtion bssoc, int strebmNumber) {
        synchronized (stbteLock) {
            if (strebmNumber < 0 || strebmNumber >= bssoc.mbxOutboundStrebms())
                throw new InvblidStrebmException();
        }
    }

    /* TODO: Add support for ttl bnd isComplete to both 121 12M
     *       SCTP_EOR not yet supported on reference plbtforms
     *       TTL support limited...
     */
    @Override
    public int send(ByteBuffer buffer, MessbgeInfo messbgeInfo)
            throws IOException {
        if (buffer == null)
            throw new IllegblArgumentException("buffer cbnnot be null");

        if (messbgeInfo == null)
            throw new IllegblArgumentException("messbgeInfo cbnnot be null");

        synchronized (sendLock) {
            ensureOpen();

            if (!isBound())
                bind(null, 0);

            int n = 0;
            try {
                int bssocId = -1;
                SocketAddress bddress = null;
                begin();

                synchronized (stbteLock) {
                    if(!isOpen())
                        return 0;
                    senderThrebd = NbtiveThrebd.current();

                    /* Determine whbt bddress or bssocibtion to send to */
                    Associbtion bssoc = messbgeInfo.bssocibtion();
                    InetSocketAddress bddr = (InetSocketAddress)messbgeInfo.bddress();
                    if (bssoc != null) {
                        checkAssocibtion(bssoc);
                        checkStrebmNumber(bssoc, messbgeInfo.strebmNumber());
                        bssocId = bssoc.bssocibtionID();
                        /* hbve we blso got b preferred bddress */
                        if (bddr != null) {
                            if (!bssoc.equbls(bddressMbp.get(bddr)))
                                throw new IllegblArgumentException("given preferred bddress is not pbrt of this bssocibtion");
                            bddress = bddr;
                        }
                    } else if (bddr != null) {
                        bddress = bddr;
                        Associbtion bssocibtion = bddressMbp.get(bddr);
                        if (bssocibtion != null) {
                            checkStrebmNumber(bssocibtion, messbgeInfo.strebmNumber());
                            bssocId = bssocibtion.bssocibtionID();

                        } else { /* must be new bssocibtion */
                            SecurityMbnbger sm = System.getSecurityMbnbger();
                            if (sm != null)
                                sm.checkConnect(bddr.getAddress().getHostAddress(),
                                                bddr.getPort());
                        }
                    } else {
                        throw new AssertionError(
                            "Both bssocibtion bnd bddress cbnnot be null");
                    }
                }

                do {
                    n = send(fdVbl, buffer, bssocId, bddress, messbgeInfo);
                } while ((n == IOStbtus.INTERRUPTED) && isOpen());

                return IOStbtus.normblize(n);
            } finblly {
                senderClebnup();
                end((n > 0) || (n == IOStbtus.UNAVAILABLE));
                bssert IOStbtus.check(n);
            }
        }
    }

    privbte int send(int fd,
                     ByteBuffer src,
                     int bssocId,
                     SocketAddress tbrget,
                     MessbgeInfo messbgeInfo)
            throws IOException {
        int strebmNumber = messbgeInfo.strebmNumber();
        boolebn unordered = messbgeInfo.isUnordered();
        int ppid = messbgeInfo.pbylobdProtocolID();

        if (src instbnceof DirectBuffer)
            return sendFromNbtiveBuffer(fd, src, tbrget, bssocId,
                    strebmNumber, unordered, ppid);

        /* Substitute b nbtive buffer */
        int pos = src.position();
        int lim = src.limit();
        bssert (pos <= lim && strebmNumber >= 0);

        int rem = (pos <= lim ? lim - pos : 0);
        ByteBuffer bb = Util.getTemporbryDirectBuffer(rem);
        try {
            bb.put(src);
            bb.flip();
            /* Do not updbte src until we see how mbny bytes were written */
            src.position(pos);

            int n = sendFromNbtiveBuffer(fd, bb, tbrget, bssocId,
                    strebmNumber, unordered, ppid);
            if (n > 0) {
                /* now updbte src */
                src.position(pos + n);
            }
            return n;
        } finblly {
            Util.relebseTemporbryDirectBuffer(bb);
        }
    }

    privbte int sendFromNbtiveBuffer(int fd,
                                     ByteBuffer bb,
                                     SocketAddress tbrget,
                                     int bssocId,
                                     int strebmNumber,
                                     boolebn unordered,
                                     int ppid)
            throws IOException {
        InetAddress bddr = null;     // no preferred bddress
        int port = 0;
        if (tbrget != null) {
            InetSocketAddress isb = Net.checkAddress(tbrget);
            bddr = isb.getAddress();
            port = isb.getPort();
        }
        int pos = bb.position();
        int lim = bb.limit();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        int written = send0(fd, ((DirectBuffer)bb).bddress() + pos, rem, bddr,
                            port, bssocId, strebmNumber, unordered, ppid);
        if (written > 0)
            bb.position(pos + written);
        return written;
    }

    @Override
    public SctpMultiChbnnel shutdown(Associbtion bssocibtion)
            throws IOException {
        synchronized (stbteLock) {
            checkAssocibtion(bssocibtion);
            if (!isOpen())
                throw new ClosedChbnnelException();

            SctpNet.shutdown(fdVbl, bssocibtion.bssocibtionID());
        }
        return this;
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

    @Override
    public Set<SocketAddress> getRemoteAddresses(Associbtion bssocibtion)
            throws IOException {
        synchronized (stbteLock) {
            checkAssocibtion(bssocibtion);
            if (!isOpen())
                throw new ClosedChbnnelException();

            try {
                return SctpNet.getRemoteAddresses(fdVbl, bssocibtion.bssocibtionID());
            } cbtch (SocketException se) {
                /* b vblid bssocibtion should blwbys hbve remote bddresses */
                Set<SocketAddress> bddrs = bssocibtionMbp.get(bssocibtion);
                return bddrs != null ? bddrs : Collections.<SocketAddress>emptySet();
            }
        }
    }

    @Override
    public SctpChbnnel brbnch(Associbtion bssocibtion)
            throws IOException {
        synchronized (stbteLock) {
            checkAssocibtion(bssocibtion);
            if (!isOpen())
                throw new ClosedChbnnelException();

            FileDescriptor bFd = SctpNet.brbnch(fdVbl,
                                                bssocibtion.bssocibtionID());
            /* successfully brbnched, we cbn now remove it from bssoc list */
            removeAssocibtion(bssocibtion);

            return new SctpChbnnelImpl(provider(), bFd, bssocibtion);
        }
    }

    /* Use common nbtive implementbtion shbred between
     * one-to-one bnd one-to-mbny */
    privbte stbtic int receive0(int fd,
                                ResultContbiner resultContbiner,
                                long bddress,
                                int length)
            throws IOException{
        return SctpChbnnelImpl.receive0(fd, resultContbiner, bddress,
                length, fblse /*peek */);
    }

    privbte stbtic int send0(int fd,
                             long bddress,
                             int length,
                             InetAddress bddr,
                             int port,
                             int bssocId,
                             int strebmNumber,
                             boolebn unordered,
                             int ppid)
            throws IOException {
        return SctpChbnnelImpl.send0(fd, bddress, length, bddr, port, bssocId,
                strebmNumber, unordered, ppid);
    }

    stbtic {
        IOUtil.lobd();   /* lobds nio & net nbtive librbries */
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("sctp");
                    return null;
                }
            });
    }
}
