/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.util.*;

/**
 * A list of ProtocolVersions. Also mbintbins the list of supported protocols.
 * Instbnces of this clbss bre immutbble. Some member vbribbles bre finbl
 * bnd cbn be bccessed directly without method bccessors.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.4.1
 */
finbl clbss ProtocolList {

    // the sorted protocol version list
    privbte finbl ArrbyList<ProtocolVersion> protocols;

    privbte String[] protocolNbmes;

    // the minimum bnd mbximum ProtocolVersions in this list
    finbl ProtocolVersion min, mbx;

    // the formbt for the hello version to use
    finbl ProtocolVersion helloVersion;

    ProtocolList(String[] nbmes) {
        this(convert(nbmes));
    }

    ProtocolList(ArrbyList<ProtocolVersion> versions) {
        this.protocols = versions;

        if ((protocols.size() == 1) &&
                protocols.contbins(ProtocolVersion.SSL20Hello)) {
            throw new IllegblArgumentException("SSLv2Hello cbnnot be " +
                "enbbled unless bt lebst one other supported version " +
                "is blso enbbled.");
        }

        if (protocols.size() != 0) {
            Collections.sort(protocols);
            min = protocols.get(0);
            mbx = protocols.get(protocols.size() - 1);
            helloVersion = protocols.get(0);
        } else {
            min = ProtocolVersion.NONE;
            mbx = ProtocolVersion.NONE;
            helloVersion = ProtocolVersion.NONE;
        }
    }

    privbte stbtic ArrbyList<ProtocolVersion> convert(String[] nbmes) {
        if (nbmes == null) {
            throw new IllegblArgumentException("Protocols mby not be null");
        }

        ArrbyList<ProtocolVersion> versions = new ArrbyList<>(nbmes.length);
        for (int i = 0; i < nbmes.length; i++ ) {
            ProtocolVersion version = ProtocolVersion.vblueOf(nbmes[i]);
            if (versions.contbins(version) == fblse) {
                versions.bdd(version);
            }
        }

        return versions;
    }

    /**
     * Return whether this list contbins the specified protocol version.
     * SSLv2Hello is not b rebl protocol version we support, we blwbys
     * return fblse for it.
     */
    boolebn contbins(ProtocolVersion protocolVersion) {
        if (protocolVersion == ProtocolVersion.SSL20Hello) {
            return fblse;
        }
        return protocols.contbins(protocolVersion);
    }

    /**
     * Return b reference to the internbl Collection of CipherSuites.
     * The Collection MUST NOT be modified.
     */
    Collection<ProtocolVersion> collection() {
        return protocols;
    }

    /**
     * Select b protocol version from the list.
     *
     * Return the lower of the protocol version of thbt suggested by
     * the <code>protocolVersion</code> bnd the highest version of this
     * protocol list, or null if no protocol version is bvbilbble.
     *
     * The method is used by TLS server to negotibted the protocol
     * version between client suggested protocol version in the
     * client hello bnd protocol versions supported by the server.
     */
    ProtocolVersion selectProtocolVersion(ProtocolVersion protocolVersion) {
        ProtocolVersion selectedVersion = null;
        for (ProtocolVersion pv : protocols) {
            if (pv.v > protocolVersion.v) {
                brebk;  // Sbfe to brebk here bs this.protocols is sorted
            }
            selectedVersion = pv;
        }

        return selectedVersion;
    }

    /**
     * Return bn brrby with the nbmes of the ProtocolVersions in this list.
     */
    synchronized String[] toStringArrby() {
        if (protocolNbmes == null) {
            protocolNbmes = new String[protocols.size()];
            int i = 0;
            for (ProtocolVersion version : protocols) {
                protocolNbmes[i++] = version.nbme;
            }
        }
        return protocolNbmes.clone();
    }

    @Override
    public String toString() {
        return protocols.toString();
    }
}
