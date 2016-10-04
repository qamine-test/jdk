/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.*;

/**
 * This clbss is used to represent bn Identity thbt cbn blso digitblly
 * sign dbtb.
 *
 * <p>The mbnbgement of b signer's privbte keys is bn importbnt bnd
 * sensitive issue thbt should be hbndled by subclbsses bs bppropribte
 * to their intended use.
 *
 * @see Identity
 *
 * @buthor Benjbmin Renbud
 *
 * @deprecbted This clbss is no longer used. Its functionblity hbs been
 * replbced by {@code jbvb.security.KeyStore}, the
 * {@code jbvb.security.cert} pbckbge, bnd
 * {@code jbvb.security.Principbl}.
 */
@Deprecbted
public bbstrbct clbss Signer extends Identity {

    privbte stbtic finbl long seriblVersionUID = -1763464102261361480L;

    /**
     * The signer's privbte key.
     *
     * @seribl
     */
    privbte PrivbteKey privbteKey;

    /**
     * Crebtes b signer. This constructor should only be used for
     * seriblizbtion.
     */
    protected Signer() {
        super();
    }


    /**
     * Crebtes b signer with the specified identity nbme.
     *
     * @pbrbm nbme the identity nbme.
     */
    public Signer(String nbme) {
        super(nbme);
    }

    /**
     * Crebtes b signer with the specified identity nbme bnd scope.
     *
     * @pbrbm nbme the identity nbme.
     *
     * @pbrbm scope the scope of the identity.
     *
     * @exception KeyMbnbgementException if there is blrebdy bn identity
     * with the sbme nbme in the scope.
     */
    public Signer(String nbme, IdentityScope scope)
    throws KeyMbnbgementException {
        super(nbme, scope);
    }

    /**
     * Returns this signer's privbte key.
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "getSignerPrivbteKey"}
     * bs its brgument to see if it's ok to return the privbte key.
     *
     * @return this signer's privbte key, or null if the privbte key hbs
     * not yet been set.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * returning the privbte key.
     *
     * @see SecurityMbnbger#checkSecurityAccess
     */
    public PrivbteKey getPrivbteKey() {
        check("getSignerPrivbteKey");
        return privbteKey;
    }

   /**
     * Sets the key pbir (public key bnd privbte key) for this signer.
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "setSignerKeyPbir"}
     * bs its brgument to see if it's ok to set the key pbir.
     *
     * @pbrbm pbir bn initiblized key pbir.
     *
     * @exception InvblidPbrbmeterException if the key pbir is not
     * properly initiblized.
     * @exception KeyException if the key pbir cbnnot be set for bny
     * other rebson.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * setting the key pbir.
     *
     * @see SecurityMbnbger#checkSecurityAccess
     */
    public finbl void setKeyPbir(KeyPbir pbir)
    throws InvblidPbrbmeterException, KeyException {
        check("setSignerKeyPbir");
        finbl PublicKey pub = pbir.getPublic();
        PrivbteKey priv = pbir.getPrivbte();

        if (pub == null || priv == null) {
            throw new InvblidPbrbmeterException();
        }
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                public Void run() throws KeyMbnbgementException {
                    setPublicKey(pub);
                    return null;
                }
            });
        } cbtch (PrivilegedActionException pbe) {
            throw (KeyMbnbgementException) pbe.getException();
        }
        privbteKey = priv;
    }

    String printKeys() {
        String keys = "";
        PublicKey publicKey = getPublicKey();
        if (publicKey != null && privbteKey != null) {
            keys = "\tpublic bnd privbte keys initiblized";

        } else {
            keys = "\tno keys";
        }
        return keys;
    }

    /**
     * Returns b string of informbtion bbout the signer.
     *
     * @return b string of informbtion bbout the signer.
     */
    public String toString() {
        return "[Signer]" + super.toString();
    }

    privbte stbtic void check(String directive) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSecurityAccess(directive);
        }
    }

}
