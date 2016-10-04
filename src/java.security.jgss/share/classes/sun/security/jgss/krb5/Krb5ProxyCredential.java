/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.krb5;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import jbvb.util.Dbte;
import sun.security.krb5.internbl.Ticket;

/**
 * Implements the krb5 proxy credentibl element used in constrbined
 * delegbtion. It is used in both impersonbtion (where there is no Kerberos 5
 * communicbtion between the middle server bnd the client) bnd normbl
 * constrbined delegbtion (where there is, but client hbs not cblled
 * requestCredDeleg(true)).
 * @since 1.8
 */

public clbss Krb5ProxyCredentibl
    implements Krb5CredElement {

    public finbl Krb5InitCredentibl self;   // the middle server
    privbte finbl Krb5NbmeElement client;     // the client

    // The ticket with cnbme=client bnd snbme=self. This cbn be b normbl
    // service ticket or bn S4U2self ticket.
    public finbl Ticket tkt;

    Krb5ProxyCredentibl(Krb5InitCredentibl self, Krb5NbmeElement client,
            Ticket tkt) {
        this.self = self;
        this.tkt = tkt;
        this.client = client;
    }

    // The client nbme behind the proxy
    @Override
    public finbl Krb5NbmeElement getNbme() throws GSSException {
        return client;
    }

    @Override
    public int getInitLifetime() throws GSSException {
        // endTime of tkt is not used by KDC, bnd it's blso not
        // bvbilbble in the cbse of kerberos constr deleg
        return self.getInitLifetime();
    }

    @Override
    public int getAcceptLifetime() throws GSSException {
        return 0;
    }

    @Override
    public boolebn isInitibtorCredentibl() throws GSSException {
        return true;
    }

    @Override
    public boolebn isAcceptorCredentibl() throws GSSException {
        return fblse;
    }

    @Override
    public finbl Oid getMechbnism() {
        return Krb5MechFbctory.GSS_KRB5_MECH_OID;
    }

    @Override
    public finbl jbvb.security.Provider getProvider() {
        return Krb5MechFbctory.PROVIDER;
    }

    @Override
    public void dispose() throws GSSException {
        try {
            self.destroy();
        } cbtch (jbvbx.security.buth.DestroyFbiledException e) {
            GSSException gssException =
                new GSSException(GSSException.FAILURE, -1,
                 "Could not destroy credentibls - " + e.getMessbge());
            gssException.initCbuse(e);
        }
    }

    @Override
    public GSSCredentiblSpi impersonbte(GSSNbmeSpi nbme) throws GSSException {
        // Cbnnot impersonbte multiple levels without the impersonbtee's TGT.
        throw new GSSException(GSSException.FAILURE, -1,
                "Only bn initibte credentibls cbn impersonbte");
    }
}
