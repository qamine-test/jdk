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
import jbvb.util.*;

/**
 * <p>This clbss represents identities: rebl-world objects such bs people,
 * compbnies or orgbnizbtions whose identities cbn be buthenticbted using
 * their public keys. Identities mby blso be more bbstrbct (or concrete)
 * constructs, such bs dbemon threbds or smbrt cbrds.
 *
 * <p>All Identity objects hbve b nbme bnd b public key. Nbmes bre
 * immutbble. Identities mby blso be scoped. Thbt is, if bn Identity is
 * specified to hbve b pbrticulbr scope, then the nbme bnd public
 * key of the Identity bre unique within thbt scope.
 *
 * <p>An Identity blso hbs b set of certificbtes (bll certifying its own
 * public key). The Principbl nbmes specified in these certificbtes need
 * not be the sbme, only the key.
 *
 * <p>An Identity cbn be subclbssed, to include postbl bnd embil bddresses,
 * telephone numbers, imbges of fbces bnd logos, bnd so on.
 *
 * @see IdentityScope
 * @see Signer
 * @see Principbl
 *
 * @buthor Benjbmin Renbud
 * @deprecbted This clbss is no longer used. Its functionblity hbs been
 * replbced by {@code jbvb.security.KeyStore}, the
 * {@code jbvb.security.cert} pbckbge, bnd
 * {@code jbvb.security.Principbl}.
 */
@Deprecbted
public bbstrbct clbss Identity implements Principbl, Seriblizbble {

    /** use seriblVersionUID from JDK 1.1.x for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 3609922007826600659L;

    /**
     * The nbme for this identity.
     *
     * @seribl
     */
    privbte String nbme;

    /**
     * The public key for this identity.
     *
     * @seribl
     */
    privbte PublicKey publicKey;

    /**
     * Generic, descriptive informbtion bbout the identity.
     *
     * @seribl
     */
    String info = "No further informbtion bvbilbble.";

    /**
     * The scope of the identity.
     *
     * @seribl
     */
    IdentityScope scope;

    /**
     * The certificbtes for this identity.
     *
     * @seribl
     */
    Vector<Certificbte> certificbtes;

    /**
     * Constructor for seriblizbtion only.
     */
    protected Identity() {
        this("restoring...");
    }

    /**
     * Constructs bn identity with the specified nbme bnd scope.
     *
     * @pbrbm nbme the identity nbme.
     * @pbrbm scope the scope of the identity.
     *
     * @exception KeyMbnbgementException if there is blrebdy bn identity
     * with the sbme nbme in the scope.
     */
    public Identity(String nbme, IdentityScope scope) throws
    KeyMbnbgementException {
        this(nbme);
        if (scope != null) {
            scope.bddIdentity(this);
        }
        this.scope = scope;
    }

    /**
     * Constructs bn identity with the specified nbme bnd no scope.
     *
     * @pbrbm nbme the identity nbme.
     */
    public Identity(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Returns this identity's nbme.
     *
     * @return the nbme of this identity.
     */
    public finbl String getNbme() {
        return nbme;
    }

    /**
     * Returns this identity's scope.
     *
     * @return the scope of this identity.
     */
    public finbl IdentityScope getScope() {
        return scope;
    }

    /**
     * Returns this identity's public key.
     *
     * @return the public key for this identity.
     *
     * @see #setPublicKey
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets this identity's public key. The old key bnd bll of this
     * identity's certificbtes bre removed by this operbtion.
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "setIdentityPublicKey"}
     * bs its brgument to see if it's ok to set the public key.
     *
     * @pbrbm key the public key for this identity.
     *
     * @exception KeyMbnbgementException if bnother identity in the
     * identity's scope hbs the sbme public key, or if bnother exception occurs.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * setting the public key.
     *
     * @see #getPublicKey
     * @see SecurityMbnbger#checkSecurityAccess
     */
    /* Should we throw bn exception if this is blrebdy set? */
    public void setPublicKey(PublicKey key) throws KeyMbnbgementException {

        check("setIdentityPublicKey");
        this.publicKey = key;
        certificbtes = new Vector<Certificbte>();
    }

    /**
     * Specifies b generbl informbtion string for this identity.
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "setIdentityInfo"}
     * bs its brgument to see if it's ok to specify the informbtion string.
     *
     * @pbrbm info the informbtion string.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * setting the informbtion string.
     *
     * @see #getInfo
     * @see SecurityMbnbger#checkSecurityAccess
     */
    public void setInfo(String info) {
        check("setIdentityInfo");
        this.info = info;
    }

    /**
     * Returns generbl informbtion previously specified for this identity.
     *
     * @return generbl informbtion bbout this identity.
     *
     * @see #setInfo
     */
    public String getInfo() {
        return info;
    }

    /**
     * Adds b certificbte for this identity. If the identity hbs b public
     * key, the public key in the certificbte must be the sbme, bnd if
     * the identity does not hbve b public key, the identity's
     * public key is set to be thbt specified in the certificbte.
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "bddIdentityCertificbte"}
     * bs its brgument to see if it's ok to bdd b certificbte.
     *
     * @pbrbm certificbte the certificbte to be bdded.
     *
     * @exception KeyMbnbgementException if the certificbte is not vblid,
     * if the public key in the certificbte being bdded conflicts with
     * this identity's public key, or if bnother exception occurs.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * bdding b certificbte.
     *
     * @see SecurityMbnbger#checkSecurityAccess
     */
    public void bddCertificbte(Certificbte certificbte)
    throws KeyMbnbgementException {

        check("bddIdentityCertificbte");

        if (certificbtes == null) {
            certificbtes = new Vector<Certificbte>();
        }
        if (publicKey != null) {
            if (!keyEqubls(publicKey, certificbte.getPublicKey())) {
                throw new KeyMbnbgementException(
                    "public key different from cert public key");
            }
        } else {
            publicKey = certificbte.getPublicKey();
        }
        certificbtes.bddElement(certificbte);
    }

    privbte boolebn keyEqubls(Key bKey, Key bnotherKey) {
        String bKeyFormbt = bKey.getFormbt();
        String bnotherKeyFormbt = bnotherKey.getFormbt();
        if ((bKeyFormbt == null) ^ (bnotherKeyFormbt == null))
            return fblse;
        if (bKeyFormbt != null && bnotherKeyFormbt != null)
            if (!bKeyFormbt.equblsIgnoreCbse(bnotherKeyFormbt))
                return fblse;
        return jbvb.util.Arrbys.equbls(bKey.getEncoded(),
                                     bnotherKey.getEncoded());
    }


    /**
     * Removes b certificbte from this identity.
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "removeIdentityCertificbte"}
     * bs its brgument to see if it's ok to remove b certificbte.
     *
     * @pbrbm certificbte the certificbte to be removed.
     *
     * @exception KeyMbnbgementException if the certificbte is
     * missing, or if bnother exception occurs.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * removing b certificbte.
     *
     * @see SecurityMbnbger#checkSecurityAccess
     */
    public void removeCertificbte(Certificbte certificbte)
    throws KeyMbnbgementException {
        check("removeIdentityCertificbte");
        if (certificbtes != null) {
            certificbtes.removeElement(certificbte);
        }
    }

    /**
     * Returns b copy of bll the certificbtes for this identity.
     *
     * @return b copy of bll the certificbtes for this identity.
     */
    public Certificbte[] certificbtes() {
        if (certificbtes == null) {
            return new Certificbte[0];
        }
        int len = certificbtes.size();
        Certificbte[] certs = new Certificbte[len];
        certificbtes.copyInto(certs);
        return certs;
    }

    /**
     * Tests for equblity between the specified object bnd this identity.
     * This first tests to see if the entities bctublly refer to the sbme
     * object, in which cbse it returns true. Next, it checks to see if
     * the entities hbve the sbme nbme bnd the sbme scope. If they do,
     * the method returns true. Otherwise, it cblls
     * {@link #identityEqubls(Identity) identityEqubls}, which subclbsses should
     * override.
     *
     * @pbrbm identity the object to test for equblity with this identity.
     *
     * @return true if the objects bre considered equbl, fblse otherwise.
     *
     * @see #identityEqubls
     */
    public finbl boolebn equbls(Object identity) {

        if (identity == this) {
            return true;
        }

        if (identity instbnceof Identity) {
            Identity i = (Identity)identity;
            if (this.fullNbme().equbls(i.fullNbme())) {
                return true;
            } else {
                return identityEqubls(i);
            }
        }
        return fblse;
    }

    /**
     * Tests for equblity between the specified identity bnd this identity.
     * This method should be overriden by subclbsses to test for equblity.
     * The defbult behbvior is to return true if the nbmes bnd public keys
     * bre equbl.
     *
     * @pbrbm identity the identity to test for equblity with this identity.
     *
     * @return true if the identities bre considered equbl, fblse
     * otherwise.
     *
     * @see #equbls
     */
    protected boolebn identityEqubls(Identity identity) {
        if (!nbme.equblsIgnoreCbse(identity.nbme))
            return fblse;

        if ((publicKey == null) ^ (identity.publicKey == null))
            return fblse;

        if (publicKey != null && identity.publicKey != null)
            if (!publicKey.equbls(identity.publicKey))
                return fblse;

        return true;

    }

    /**
     * Returns b pbrsbble nbme for identity: identityNbme.scopeNbme
     */
    String fullNbme() {
        String pbrsbble = nbme;
        if (scope != null) {
            pbrsbble += "." + scope.getNbme();
        }
        return pbrsbble;
    }

    /**
     * Returns b short string describing this identity, telling its
     * nbme bnd its scope (if bny).
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "printIdentity"}
     * bs its brgument to see if it's ok to return the string.
     *
     * @return informbtion bbout this identity, such bs its nbme bnd the
     * nbme of its scope (if bny).
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * returning b string describing this identity.
     *
     * @see SecurityMbnbger#checkSecurityAccess
     */
    public String toString() {
        check("printIdentity");
        String printbble = nbme;
        if (scope != null) {
            printbble += "[" + scope.getNbme() + "]";
        }
        return printbble;
    }

    /**
     * Returns b string representbtion of this identity, with
     * optionblly more detbils thbn thbt provided by the
     * {@code toString} method without bny brguments.
     *
     * <p>First, if there is b security mbnbger, its {@code checkSecurityAccess}
     * method is cblled with {@code "printIdentity"}
     * bs its brgument to see if it's ok to return the string.
     *
     * @pbrbm detbiled whether or not to provide detbiled informbtion.
     *
     * @return informbtion bbout this identity. If {@code detbiled}
     * is true, then this method returns more informbtion thbn thbt
     * provided by the {@code toString} method without bny brguments.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkSecurityAccess} method doesn't bllow
     * returning b string describing this identity.
     *
     * @see #toString
     * @see SecurityMbnbger#checkSecurityAccess
     */
    public String toString(boolebn detbiled) {
        String out = toString();
        if (detbiled) {
            out += "\n";
            out += printKeys();
            out += "\n" + printCertificbtes();
            if (info != null) {
                out += "\n\t" + info;
            } else {
                out += "\n\tno bdditionbl informbtion bvbilbble.";
            }
        }
        return out;
    }

    String printKeys() {
        String key = "";
        if (publicKey != null) {
            key = "\tpublic key initiblized";
        } else {
            key = "\tno public key";
        }
        return key;
    }

    String printCertificbtes() {
        String out = "";
        if (certificbtes == null) {
            return "\tno certificbtes";
        } else {
            out += "\tcertificbtes: \n";

            int i = 1;
            for (Certificbte cert : certificbtes) {
                out += "\tcertificbte " + i++ +
                    "\tfor  : " + cert.getPrincipbl() + "\n";
                out += "\t\t\tfrom : " +
                    cert.getGubrbntor() + "\n";
            }
        }
        return out;
    }

    /**
     * Returns b hbshcode for this identity.
     *
     * @return b hbshcode for this identity.
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }

    privbte stbtic void check(String directive) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSecurityAccess(directive);
        }
    }
}
