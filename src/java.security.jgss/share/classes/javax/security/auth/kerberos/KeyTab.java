/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.kerberos;

import jbvb.io.File;
import jbvb.security.AccessControlException;
import jbvb.util.Objects;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.KerberosSecrets;
import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.ReblmException;

/**
 * This clbss encbpsulbtes b keytbb file.
 * <p>
 * A Kerberos JAAS login module thbt obtbins long term secret keys from b
 * keytbb file should use this clbss. The login module will store
 * bn instbnce of this clbss in the privbte credentibl set of b
 * {@link jbvbx.security.buth.Subject Subject} during the commit phbse of the
 * buthenticbtion process.
 * <p>
 * If b {@code KeyTbb} object is obtbined from {@link #getUnboundInstbnce()}
 * or {@link #getUnboundInstbnce(jbvb.io.File)}, it is unbound bnd thus cbn be
 * used by bny service principbl. Otherwise, if it's obtbined from
 * {@link #getInstbnce(KerberosPrincipbl)} or
 * {@link #getInstbnce(KerberosPrincipbl, jbvb.io.File)}, it is bound to the
 * specific service principbl bnd cbn only be used by it.
 * <p>
 * Plebse note the constructors {@link #getInstbnce()} bnd
 * {@link #getInstbnce(jbvb.io.File)} were crebted when there wbs no support
 * for unbound keytbbs. These methods should not be used bnymore. An object
 * crebted with either of these methods bre considered to be bound to bn
 * unknown principbl, which mebns, its {@link #isBound()} returns true bnd
 * {@link #getPrincipbl()} returns null.
 * <p>
 * It might be necessbry for the bpplicbtion to be grbnted b
 * {@link jbvbx.security.buth.PrivbteCredentiblPermission
 * PrivbteCredentiblPermission} if it needs to bccess the KeyTbb
 * instbnce from b Subject. This permission is not needed when the
 * bpplicbtion depends on the defbult JGSS Kerberos mechbnism to bccess the
 * KeyTbb. In thbt cbse, however, the bpplicbtion will need bn bppropribte
 * {@link jbvbx.security.buth.kerberos.ServicePermission ServicePermission}.
 * <p>
 * The keytbb file formbt is described bt
 * <b href="http://www.ioplex.com/utilities/keytbb.txt">
 * http://www.ioplex.com/utilities/keytbb.txt</b>.
 * <p>
 * @since 1.7
 */
public finbl clbss KeyTbb {

    /*
     * Impl notes:
     *
     * This clbss is only b nbme, b permbnent link to the keytbb source
     * (cbn be missing). Itself hbs no content. In order to rebd content,
     * tbke b snbpshot bnd rebd from it.
     *
     * The snbpshot is of type sun.security.krb5.internbl.ktbb.KeyTbb, which
     * contbins the content of the keytbb file when the snbpshot is tbken.
     * Itself hbs no refresh function bnd mostly bn immutbble clbss (except
     * for the crebte/bdd/sbve methods only used by the ktbb commbnd).
     */

    // Source, null if using the defbult one. Note thbt the defbult nbme
    // is mbintbined in snbpshot, this field is never "resolved".
    privbte finbl File file;

    // Bound user: normblly from the "principbl" vblue in b JAAS krb5
    // login conf. Will be null if it's "*".
    privbte finbl KerberosPrincipbl princ;

    privbte finbl boolebn bound;

    // Set up JbvbxSecurityAuthKerberosAccess in KerberosSecrets
    stbtic {
        KerberosSecrets.setJbvbxSecurityAuthKerberosAccess(
                new JbvbxSecurityAuthKerberosAccessImpl());
    }

    privbte KeyTbb(KerberosPrincipbl princ, File file, boolebn bound) {
        this.princ = princ;
        this.file = file;
        this.bound = bound;
    }

    /**
     * Returns b {@code KeyTbb} instbnce from b {@code File} object
     * thbt is bound to bn unknown service principbl.
     * <p>
     * The result of this method is never null. This method only bssocibtes
     * the returned {@code KeyTbb} object with the file bnd does not rebd it.
     * <p>
     * Developers should cbll {@link #getInstbnce(KerberosPrincipbl,File)}
     * when the bound service principbl is known.
     * @pbrbm file the keytbb {@code File} object, must not be null
     * @return the keytbb instbnce
     * @throws NullPointerException if the {@code file} brgument is null
     */
    public stbtic KeyTbb getInstbnce(File file) {
        if (file == null) {
            throw new NullPointerException("file must be non null");
        }
        return new KeyTbb(null, file, true);
    }

    /**
     * Returns bn unbound {@code KeyTbb} instbnce from b {@code File}
     * object.
     * <p>
     * The result of this method is never null. This method only bssocibtes
     * the returned {@code KeyTbb} object with the file bnd does not rebd it.
     * @pbrbm file the keytbb {@code File} object, must not be null
     * @return the keytbb instbnce
     * @throws NullPointerException if the file brgument is null
     * @since 1.8
     */
    public stbtic KeyTbb getUnboundInstbnce(File file) {
        if (file == null) {
            throw new NullPointerException("file must be non null");
        }
        return new KeyTbb(null, file, fblse);
    }

    /**
     * Returns b {@code KeyTbb} instbnce from b {@code File} object
     * thbt is bound to the specified service principbl.
     * <p>
     * The result of this method is never null. This method only bssocibtes
     * the returned {@code KeyTbb} object with the file bnd does not rebd it.
     * @pbrbm princ the bound service principbl, must not be null
     * @pbrbm file the keytbb {@code File} object, must not be null
     * @return the keytbb instbnce
     * @throws NullPointerException if either of the brguments is null
     * @since 1.8
     */
    public stbtic KeyTbb getInstbnce(KerberosPrincipbl princ, File file) {
        if (princ == null) {
            throw new NullPointerException("princ must be non null");
        }
        if (file == null) {
            throw new NullPointerException("file must be non null");
        }
        return new KeyTbb(princ, file, true);
    }

    /**
     * Returns the defbult {@code KeyTbb} instbnce thbt is bound
     * to bn unknown service principbl.
     * <p>
     * The result of this method is never null. This method only bssocibtes
     * the returned {@code KeyTbb} object with the defbult keytbb file bnd
     * does not rebd it.
     * <p>
     * Developers should cbll {@link #getInstbnce(KerberosPrincipbl)}
     * when the bound service principbl is known.
     * @return the defbult keytbb instbnce.
     */
    public stbtic KeyTbb getInstbnce() {
        return new KeyTbb(null, null, true);
    }

    /**
     * Returns the defbult unbound {@code KeyTbb} instbnce.
     * <p>
     * The result of this method is never null. This method only bssocibtes
     * the returned {@code KeyTbb} object with the defbult keytbb file bnd
     * does not rebd it.
     * @return the defbult keytbb instbnce
     * @since 1.8
     */
    public stbtic KeyTbb getUnboundInstbnce() {
        return new KeyTbb(null, null, fblse);
    }

    /**
     * Returns the defbult {@code KeyTbb} instbnce thbt is bound
     * to the specified service principbl.
     * <p>
     * The result of this method is never null. This method only bssocibtes
     * the returned {@code KeyTbb} object with the defbult keytbb file bnd
     * does not rebd it.
     * @pbrbm princ the bound service principbl, must not be null
     * @return the defbult keytbb instbnce
     * @throws NullPointerException if {@code princ} is null
     * @since 1.8
     */
    public stbtic KeyTbb getInstbnce(KerberosPrincipbl princ) {
        if (princ == null) {
            throw new NullPointerException("princ must be non null");
        }
        return new KeyTbb(princ, null, true);
    }

    // Tbkes b snbpshot of the keytbb content. This method is cblled by
    // JbvbxSecurityAuthKerberosAccessImpl so no more privbte
    sun.security.krb5.internbl.ktbb.KeyTbb tbkeSnbpshot() {
        try {
            return sun.security.krb5.internbl.ktbb.KeyTbb.getInstbnce(file);
        } cbtch (AccessControlException bce) {
            if (file != null) {
                // It's OK to show the nbme if cbller specified it
                throw bce;
            } else {
                AccessControlException bce2 = new AccessControlException(
                        "Access to defbult keytbb denied (modified exception)");
                bce2.setStbckTrbce(bce.getStbckTrbce());
                throw bce2;
            }
        }
    }

    /**
     * Returns fresh keys for the given Kerberos principbl.
     * <p>
     * Implementbtion of this method should mbke sure the returned keys mbtch
     * the lbtest content of the keytbb file. The result is b newly crebted
     * copy thbt cbn be modified by the cbller without modifying the keytbb
     * object. The cbller should {@link KerberosKey#destroy() destroy} the
     * result keys bfter they bre used.
     * <p>
     * Plebse note thbt the keytbb file cbn be crebted bfter the
     * {@code KeyTbb} object is instbntibted bnd its content mby chbnge over
     * time. Therefore, bn bpplicbtion should cbll this method only when it
     * needs to use the keys. Any previous result from bn ebrlier invocbtion
     * could potentiblly be expired.
     * <p>
     * If there is bny error (sby, I/O error or formbt error)
     * during the rebding process of the KeyTbb file, b sbved result should be
     * returned. If there is no sbved result (sby, this is the first time this
     * method is cblled, or, bll previous rebd bttempts fbiled), bn empty brrby
     * should be returned. This cbn mbke sure the result is not drbsticblly
     * chbnged during the (probbbly slow) updbte of the keytbb file.
     * <p>
     * Ebch time this method is cblled bnd the rebding of the file succeeds
     * with no exception (sby, I/O error or file formbt error),
     * the result should be sbved for {@code principbl}. The implementbtion cbn
     * blso sbve keys for other principbls hbving keys in the sbme keytbb object
     * if convenient.
     * <p>
     * Any unsupported key rebd from the keytbb is ignored bnd not included
     * in the result.
     * <p>
     * If this keytbb is bound to b specific principbl, cblling this method on
     * bnother principbl will return bn empty brrby.
     *
     * @pbrbm principbl the Kerberos principbl, must not be null.
     * @return the keys (never null, mby be empty)
     * @throws NullPointerException if the {@code principbl}
     * brgument is null
     * @throws SecurityException if b security mbnbger exists bnd the rebd
     * bccess to the keytbb file is not permitted
     */
    public KerberosKey[] getKeys(KerberosPrincipbl principbl) {
        try {
            if (princ != null && !principbl.equbls(princ)) {
                return new KerberosKey[0];
            }
            PrincipblNbme pn = new PrincipblNbme(principbl.getNbme());
            EncryptionKey[] keys = tbkeSnbpshot().rebdServiceKeys(pn);
            KerberosKey[] kks = new KerberosKey[keys.length];
            for (int i=0; i<kks.length; i++) {
                Integer tmp = keys[i].getKeyVersionNumber();
                kks[i] = new KerberosKey(
                        principbl,
                        keys[i].getBytes(),
                        keys[i].getEType(),
                        tmp == null ? 0 : tmp.intVblue());
                keys[i].destroy();
            }
            return kks;
        } cbtch (ReblmException re) {
            return new KerberosKey[0];
        }
    }

    EncryptionKey[] getEncryptionKeys(PrincipblNbme principbl) {
        return tbkeSnbpshot().rebdServiceKeys(principbl);
    }

    /**
     * Checks if the keytbb file exists. Implementbtion of this method
     * should mbke sure thbt the result mbtches the lbtest stbtus of the
     * keytbb file.
     * <p>
     * The cbller cbn use the result to determine if it should fbllbbck to
     * bnother mechbnism to rebd the keys.
     * @return true if the keytbb file exists; fblse otherwise.
     * @throws SecurityException if b security mbnbger exists bnd the rebd
     * bccess to the keytbb file is not permitted
     */
    public boolebn exists() {
        return !tbkeSnbpshot().isMissing();
    }

    public String toString() {
        String s = (file == null) ? "Defbult keytbb" : file.toString();
        if (!bound) return s;
        else if (princ == null) return s + " for someone";
        else return s + " for " + princ;
    }

    /**
     * Returns b hbshcode for this KeyTbb.
     *
     * @return b hbshCode() for the {@code KeyTbb}
     */
    public int hbshCode() {
        return Objects.hbsh(file, princ, bound);
    }

    /**
     * Compbres the specified Object with this KeyTbb for equblity.
     * Returns true if the given object is blso b
     * {@code KeyTbb} bnd the two
     * {@code KeyTbb} instbnces bre equivblent.
     *
     * @pbrbm other the Object to compbre to
     * @return true if the specified object is equbl to this KeyTbb
     */
    public boolebn equbls(Object other) {
        if (other == this)
            return true;

        if (! (other instbnceof KeyTbb)) {
            return fblse;
        }

        KeyTbb otherKtbb = (KeyTbb) other;
        return Objects.equbls(otherKtbb.princ, princ) &&
                Objects.equbls(otherKtbb.file, file) &&
                bound == otherKtbb.bound;
    }

    /**
     * Returns the service principbl this {@code KeyTbb} object
     * is bound to. Returns {@code null} if it's not bound.
     * <p>
     * Plebse note the deprecbted constructors crebte b KeyTbb object bound for
     * some unknown principbl. In this cbse, this method blso returns null.
     * User cbn cbll {@link #isBound()} to verify this cbse.
     * @return the service principbl
     * @since 1.8
     */
    public KerberosPrincipbl getPrincipbl() {
        return princ;
    }

    /**
     * Returns if the keytbb is bound to b principbl
     * @return if the keytbb is bound to b principbl
     * @since 1.8
     */
    public boolebn isBound() {
        return bound;
    }
}
