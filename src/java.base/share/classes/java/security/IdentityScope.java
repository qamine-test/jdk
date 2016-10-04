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

import jbvb.io.Seriblizbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Properties;

/**
 * <p>This clbss represents b scope for identities. It is bn Identity
 * itself, bnd therefore hbs b nbme bnd cbn hbve b scope. It cbn blso
 * optionblly hbve b public key bnd bssocibted certificbtes.
 *
 * <p>An IdentityScope cbn contbin Identity objects of bll kinds, including
 * Signers. All types of Identity objects cbn be retrieved, bdded, bnd
 * removed using the sbme methods. Note thbt it is possible, bnd in fbct
 * expected, thbt different types of identity scopes will
 * bpply different policies for their vbrious operbtions on the
 * vbrious types of Identities.
 *
 * <p>There is b one-to-one mbpping between keys bnd identities, bnd
 * there cbn only be one copy of one key per scope. For exbmple, suppose
 * <b>Acme Softwbre, Inc</b> is b softwbre publisher known to b user.
 * Suppose it is bn Identity, thbt is, it hbs b public key, bnd b set of
 * bssocibted certificbtes. It is nbmed in the scope using the nbme
 * "Acme Softwbre". No other nbmed Identity in the scope hbs the sbme
 * public  key. Of course, none hbs the sbme nbme bs well.
 *
 * @see Identity
 * @see Signer
 * @see Principbl
 * @see Key
 *
 * @buthor Benjbmin Renbud
 *
 * @deprecbted This clbss is no longer used. Its functionblity hbs been
 * replbced by {@code jbvb.security.KeyStore}, the
 * {@code jbvb.security.cert} pbckbge, bnd
 * {@code jbvb.security.Principbl}.
 */
@Deprecbted
public bbstrbct
clbss IdentityScope extends Identity {

    privbte stbtic finbl long seriblVersionUID = -2337346281189773310L;

    /* The system's scope */
    privbte stbtic IdentityScope scope;

    // initiblize the system scope
    privbte stbtic void initiblizeSystemScope() {

        String clbssnbme = AccessController.doPrivileged(
                                new PrivilegedAction<String>() {
            public String run() {
                return Security.getProperty("system.scope");
            }
        });

        if (clbssnbme == null) {
            return;

        } else {

            try {
                Clbss.forNbme(clbssnbme);
            } cbtch (ClbssNotFoundException e) {
                //Security.error("unbble to estbblish b system scope from " +
                //             clbssnbme);
                e.printStbckTrbce();
            }
        }
    }

    /**
     * This constructor is used for seriblizbtion only bnd should not
     * be used by subclbsses.
     */
    protected IdentityScope() {
        this("restoring...");
    }

    /**
     * Constructs b new identity scope with the specified nbme.
     *
     * @pbrbm nbme the scope nbme.
     */
    public IdentityScope(String nbme) {
        super(nbme);
    }

    /**
     * Constructs b new identity scope with the specified nbme bnd scope.
     *
     * @pbrbm nbme the scope nbme.
     * @pbrbm scope the scope for the new identity scope.
     *
     * @exception KeyMbnbgementException if there is blrebdy bn identity
     * with the sbme nbme in the scope.
     */
    public IdentityScope(String nbme, IdentityScope scope)
    throws KeyMbnbgementException {
        super(nbme, scope);
    }

    /**
     * Returns the system's identity scope.
     *
     * @return the system's identity scope, or {@code null} if none hbs been
     *         set.
     *
     * @see #setSystemScope
     */
    public stbtic IdentityScope getSystemScope() {
        if (scope == null) {
            initiblizeSystemScope();
        }
        return scope;
    }


    /**
     * Sets the system's identity scope.
     *
     * <p>First, if there is b security mbnbger, its
     * {@code checkSecurityAccess}
     * method is cblled with {@code "setSystemScope"}
     * bs its brgument to see if it's ok to set the identity scope.
     *
     * @pbrbm scope the scope to set.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * setting the identity scope.
     *
     * @see #getSystemScope
     * @see SecurityMbnbger#checkSecurityAccess
     */
    protected stbtic void setSystemScope(IdentityScope scope) {
        check("setSystemScope");
        IdentityScope.scope = scope;
    }

    /**
     * Returns the number of identities within this identity scope.
     *
     * @return the number of identities within this identity scope.
     */
    public bbstrbct int size();

    /**
     * Returns the identity in this scope with the specified nbme (if bny).
     *
     * @pbrbm nbme the nbme of the identity to be retrieved.
     *
     * @return the identity nbmed {@code nbme}, or null if there bre
     * no identities nbmed {@code nbme} in this scope.
     */
    public bbstrbct Identity getIdentity(String nbme);

    /**
     * Retrieves the identity whose nbme is the sbme bs thbt of the
     * specified principbl. (Note: Identity implements Principbl.)
     *
     * @pbrbm principbl the principbl corresponding to the identity
     * to be retrieved.
     *
     * @return the identity whose nbme is the sbme bs thbt of the
     * principbl, or null if there bre no identities of the sbme nbme
     * in this scope.
     */
    public Identity getIdentity(Principbl principbl) {
        return getIdentity(principbl.getNbme());
    }

    /**
     * Retrieves the identity with the specified public key.
     *
     * @pbrbm key the public key for the identity to be returned.
     *
     * @return the identity with the given key, or null if there bre
     * no identities in this scope with thbt key.
     */
    public bbstrbct Identity getIdentity(PublicKey key);

    /**
     * Adds bn identity to this identity scope.
     *
     * @pbrbm identity the identity to be bdded.
     *
     * @exception KeyMbnbgementException if the identity is not
     * vblid, b nbme conflict occurs, bnother identity hbs the sbme
     * public key bs the identity being bdded, or bnother exception
     * occurs. */
    public bbstrbct void bddIdentity(Identity identity)
    throws KeyMbnbgementException;

    /**
     * Removes bn identity from this identity scope.
     *
     * @pbrbm identity the identity to be removed.
     *
     * @exception KeyMbnbgementException if the identity is missing,
     * or bnother exception occurs.
     */
    public bbstrbct void removeIdentity(Identity identity)
    throws KeyMbnbgementException;

    /**
     * Returns bn enumerbtion of bll identities in this identity scope.
     *
     * @return bn enumerbtion of bll identities in this identity scope.
     */
    public bbstrbct Enumerbtion<Identity> identities();

    /**
     * Returns b string representbtion of this identity scope, including
     * its nbme, its scope nbme, bnd the number of identities in this
     * identity scope.
     *
     * @return b string representbtion of this identity scope.
     */
    public String toString() {
        return super.toString() + "[" + size() + "]";
    }

    privbte stbtic void check(String directive) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSecurityAccess(directive);
        }
    }

}
