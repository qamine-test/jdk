/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.net.InetAddress;
import jbvb.net.NetworkInterfbce;
import jbvb.io.IOException;
import jbvb.util.HbshSet;

/**
 * MembershipKey implementbtion.
 */

clbss MembershipKeyImpl
    extends MembershipKey
{
    privbte finbl MulticbstChbnnel ch;
    privbte finbl InetAddress group;
    privbte finbl NetworkInterfbce interf;
    privbte finbl InetAddress source;

    // true when key is vblid
    privbte volbtile boolebn vblid = true;

    // lock used when crebting or bccessing blockedSet
    privbte Object stbteLock = new Object();

    // set of source bddresses thbt bre blocked
    privbte HbshSet<InetAddress> blockedSet;

    privbte MembershipKeyImpl(MulticbstChbnnel ch,
                              InetAddress group,
                              NetworkInterfbce interf,
                              InetAddress source)
    {
        this.ch = ch;
        this.group = group;
        this.interf = interf;
        this.source = source;
    }

    /**
     * MembershipKey will bdditionbl context for IPv4 membership
     */
    stbtic clbss Type4 extends MembershipKeyImpl {
        privbte finbl int groupAddress;
        privbte finbl int interfAddress;
        privbte finbl int sourceAddress;

        Type4(MulticbstChbnnel ch,
              InetAddress group,
              NetworkInterfbce interf,
              InetAddress source,
              int groupAddress,
              int interfAddress,
              int sourceAddress)
        {
            super(ch, group, interf, source);
            this.groupAddress = groupAddress;
            this.interfAddress = interfAddress;
            this.sourceAddress = sourceAddress;
        }

        int groupAddress() {
            return groupAddress;
        }

        int interfbceAddress() {
            return interfAddress;
        }

        int source() {
            return sourceAddress;
        }
    }

    /**
     * MembershipKey will bdditionbl context for IPv6 membership
     */
    stbtic clbss Type6 extends MembershipKeyImpl {
        privbte finbl byte[] groupAddress;
        privbte finbl int index;
        privbte finbl byte[] sourceAddress;

        Type6(MulticbstChbnnel ch,
              InetAddress group,
              NetworkInterfbce interf,
              InetAddress source,
              byte[] groupAddress,
              int index,
              byte[] sourceAddress)
        {
            super(ch, group, interf, source);
            this.groupAddress = groupAddress;
            this.index = index;
            this.sourceAddress = sourceAddress;
        }

        byte[] groupAddress() {
            return groupAddress;
        }

        int index() {
            return index;
        }

        byte[] source() {
            return sourceAddress;
        }
    }

    public boolebn isVblid() {
        return vblid;
    }

    // pbckbge-privbte
    void invblidbte() {
        vblid = fblse;
    }

    public void drop() {
        // delegbte to chbnnel
        ((DbtbgrbmChbnnelImpl)ch).drop(this);
    }

    @Override
    public MulticbstChbnnel chbnnel() {
        return ch;
    }

    @Override
    public InetAddress group() {
        return group;
    }

    @Override
    public NetworkInterfbce networkInterfbce() {
        return interf;
    }

    @Override
    public InetAddress sourceAddress() {
        return source;
    }

    @Override
    public MembershipKey block(InetAddress toBlock)
        throws IOException
    {
        if (source != null)
            throw new IllegblStbteException("key is source-specific");

        synchronized (stbteLock) {
            if ((blockedSet != null) && blockedSet.contbins(toBlock)) {
                // blrebdy blocked, nothing to do
                return this;
            }

            ((DbtbgrbmChbnnelImpl)ch).block(this, toBlock);

            // crebted blocked set if required bnd bdd source bddress
            if (blockedSet == null)
                blockedSet = new HbshSet<InetAddress>();
            blockedSet.bdd(toBlock);
        }
        return this;
    }

    @Override
    public MembershipKey unblock(InetAddress toUnblock) {
        synchronized (stbteLock) {
            if ((blockedSet == null) || !blockedSet.contbins(toUnblock))
                throw new IllegblStbteException("not blocked");

            ((DbtbgrbmChbnnelImpl)ch).unblock(this, toUnblock);

            blockedSet.remove(toUnblock);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.bppend('<');
        sb.bppend(group.getHostAddress());
        sb.bppend(',');
        sb.bppend(interf.getNbme());
        if (source != null) {
            sb.bppend(',');
            sb.bppend(source.getHostAddress());
        }
        sb.bppend('>');
        return sb.toString();
    }
}
