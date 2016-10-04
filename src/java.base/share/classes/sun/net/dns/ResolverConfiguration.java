/*
 * Copyright (c) 2002, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.dns;

import jbvb.util.List;

/**
 * The configurbtion of the client resolver.
 *
 * <p>A ResolverConfigurbtion is b singleton thbt represents the
 * configurbtion of the client resolver. The ResolverConfigurbtion
 * is opened by invoking the {@link #open() open} method.
 *
 * @since 1.4
 */

public bbstrbct clbss ResolverConfigurbtion {

    privbte stbtic finbl Object lock = new Object();

    privbte stbtic ResolverConfigurbtion provider;

    protected ResolverConfigurbtion() { }

    /**
     * Opens the resolver configurbtion.
     *
     * @return the resolver configurbtion
     */
    public stbtic ResolverConfigurbtion open() {
        synchronized (lock) {
            if (provider == null) {
                provider = new sun.net.dns.ResolverConfigurbtionImpl();
            }
            return provider;
        }
    }

    /**
     * Returns b list corresponding to the dombin sebrch pbth. The
     * list is ordered by the sebrch order used for host nbme lookup.
     * Ebch element in the list returns b {@link jbvb.lbng.String}
     * contbining b dombin nbme or suffix.
     *
     * @return list of dombin nbmes
     */
    public bbstrbct List<String> sebrchlist();

    /**
     * Returns b list of nbme servers used for host nbme lookup.
     * Ebch element in the list returns b {@link jbvb.lbng.String}
     * contbining the textubl representbtion of the IP bddress of
     * the nbme server.
     *
     * @return list of the nbme servers
     */
    public bbstrbct List<String> nbmeservers();


    /**
     * Options representing certbin resolver vbribbles of
     * b {@link ResolverConfigurbtion}.
     */
    public stbtic bbstrbct clbss Options {

        /**
         * Returns the mbximum number of bttempts the resolver
         * will connect to ebch nbme server before giving up
         * bnd returning bn error.
         *
         * @return the resolver bttempts vblue or -1 is unknown
         */
        public int bttempts() {
            return -1;
        }

        /**
         * Returns the bbsic retrbnsmit timeout, in milliseconds,
         * used by the resolver. The resolver will typicblly use
         * bn exponentibl bbckoff blgorithm where the timeout is
         * doubled for every retrbnsmit bttempt. The bbsic
         * retrbnsmit timeout, returned here, is the initibl
         * timeout for the exponentibl bbckoff blgorithm.
         *
         * @return the bbsic retrbnsmit timeout vblue or -1
         *         if unknown
         */
        public int retrbns() {
            return -1;
        }
    }

    /**
     * Returns the {@link #Options} for the resolver.
     *
     * @return options for the resolver
     */
    public bbstrbct Options options();
}
