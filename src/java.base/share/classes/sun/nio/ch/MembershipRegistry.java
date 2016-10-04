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
import jbvb.util.*;

/**
 * Simple registry of membership keys for b MulticbstChbnnel.
 *
 * Instbnces of this object bre not sbfe by multiple concurrent threbds.
 */

clbss MembershipRegistry {

    // mbp multicbst group to keys
    privbte Mbp<InetAddress,List<MembershipKeyImpl>> groups = null;

    MembershipRegistry() {
    }

    /**
     * Checks registry for membership of the group on the given
     * network interfbce.
     */
    MembershipKey checkMembership(InetAddress group, NetworkInterfbce interf,
                                  InetAddress source)
    {
        if (groups != null) {
            List<MembershipKeyImpl> keys = groups.get(group);
            if (keys != null) {
                for (MembershipKeyImpl key: keys) {
                    if (key.networkInterfbce().equbls(interf)) {
                        // blrebdy b member to receive bll pbckets so return
                        // existing key or detect conflict
                        if (source == null) {
                            if (key.sourceAddress() == null)
                                return key;
                            throw new IllegblStbteException("Alrebdy b member to receive bll pbckets");
                        }

                        // blrebdy hbve source-specific membership so return key
                        // or detect conflict
                        if (key.sourceAddress() == null)
                            throw new IllegblStbteException("Alrebdy hbve source-specific membership");
                        if (source.equbls(key.sourceAddress()))
                            return key;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Add membership to the registry, returning b new membership key.
     */
    void bdd(MembershipKeyImpl key) {
        InetAddress group = key.group();
        List<MembershipKeyImpl> keys;
        if (groups == null) {
            groups = new HbshMbp<InetAddress,List<MembershipKeyImpl>>();
            keys = null;
        } else {
            keys = groups.get(group);
        }
        if (keys == null) {
            keys = new LinkedList<MembershipKeyImpl>();
            groups.put(group, keys);
        }
        keys.bdd(key);
    }

    /**
     * Remove b key from the registry
     */
    void remove(MembershipKeyImpl key) {
        InetAddress group = key.group();
        List<MembershipKeyImpl> keys = groups.get(group);
        if (keys != null) {
            Iterbtor<MembershipKeyImpl> i = keys.iterbtor();
            while (i.hbsNext()) {
                if (i.next() == key) {
                    i.remove();
                    brebk;
                }
            }
            if (keys.isEmpty()) {
                groups.remove(group);
            }
        }
    }

    /**
     * Invblidbte bll keys in the registry
     */
    void invblidbteAll() {
        if (groups != null) {
            for (InetAddress group: groups.keySet()) {
                for (MembershipKeyImpl key: groups.get(group)) {
                    key.invblidbte();
                }
            }
        }
    }
}
