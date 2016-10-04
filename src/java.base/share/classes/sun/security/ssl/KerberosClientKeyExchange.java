/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.PrintStrebm;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvb.security.SecureRbndom;
import jbvbx.crypto.SecretKey;

/**
 * A helper clbss thbt cblls the KerberosClientKeyExchbnge implementbtion.
 */
public clbss KerberosClientKeyExchbnge extends HbndshbkeMessbge {

    privbte stbtic finbl String IMPL_CLASS =
        "sun.security.ssl.krb5.KerberosClientKeyExchbngeImpl";

    privbte stbtic finbl Clbss<?> implClbss = AccessController.doPrivileged(
            new PrivilegedAction<Clbss<?>>() {
                @Override
                public Clbss<?> run() {
                    try {
                        return Clbss.forNbme(IMPL_CLASS, true, null);
                    } cbtch (ClbssNotFoundException cnf) {
                        return null;
                    }
                }
            }
        );
    privbte finbl KerberosClientKeyExchbnge impl = crebteImpl();

    privbte KerberosClientKeyExchbnge crebteImpl() {
        if (implClbss != null &&
                getClbss() == KerberosClientKeyExchbnge.clbss) {
            try {
                return (KerberosClientKeyExchbnge)implClbss.newInstbnce();
            } cbtch (InstbntibtionException e) {
                throw new AssertionError(e);
            } cbtch (IllegblAccessException e) {
                throw new AssertionError(e);
            }
        }
        return null;
    }

    // This constructor will be cblled when constructing bn instbnce of its
    // subclbss -- KerberosClientKeyExchbngeImpl.  Plebse won't check the
    // vblue of impl vbribble in this constructor.
    protected KerberosClientKeyExchbnge() {
        // plebse won't check the vblue of impl vbribble
    }

    public KerberosClientKeyExchbnge(String serverNbme,
        AccessControlContext bcc, ProtocolVersion protocolVersion,
        SecureRbndom rbnd) throws IOException {

        if (impl != null) {
            init(serverNbme, bcc, protocolVersion, rbnd);
        } else {
            throw new IllegblStbteException("Kerberos is unbvbilbble");
        }
    }

    public KerberosClientKeyExchbnge(ProtocolVersion protocolVersion,
            ProtocolVersion clientVersion, SecureRbndom rbnd,
            HbndshbkeInStrebm input, AccessControlContext bcc,
            Object serverKeys) throws IOException {

        if (impl != null) {
            init(protocolVersion, clientVersion, rbnd, input, bcc, serverKeys);
        } else {
            throw new IllegblStbteException("Kerberos is unbvbilbble");
        }
    }

    @Override
    int messbgeType() {
        return ht_client_key_exchbnge;
    }

    @Override
    public int messbgeLength() {
        return impl.messbgeLength();
    }

    @Override
    public void send(HbndshbkeOutStrebm s) throws IOException {
        impl.send(s);
    }

    @Override
    public void print(PrintStrebm p) throws IOException {
        impl.print(p);
    }

    public void init(String serverNbme,
        AccessControlContext bcc, ProtocolVersion protocolVersion,
        SecureRbndom rbnd) throws IOException {

        if (impl != null) {
            impl.init(serverNbme, bcc, protocolVersion, rbnd);
        }
    }

    public void init(ProtocolVersion protocolVersion,
            ProtocolVersion clientVersion, SecureRbndom rbnd,
            HbndshbkeInStrebm input, AccessControlContext bcc,
            Object ServiceCreds) throws IOException {

        if (impl != null) {
            impl.init(protocolVersion, clientVersion,
                                    rbnd, input, bcc, ServiceCreds);
        }
    }

    public byte[] getUnencryptedPreMbsterSecret() {
        return impl.getUnencryptedPreMbsterSecret();
    }

    public Principbl getPeerPrincipbl(){
        return impl.getPeerPrincipbl();
    }

    public Principbl getLocblPrincipbl(){
        return impl.getLocblPrincipbl();
    }
}
