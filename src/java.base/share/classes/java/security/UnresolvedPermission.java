/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.Hbshtbble;
import jbvb.lbng.reflect.*;
import jbvb.security.cert.*;

/**
 * The UnresolvedPermission clbss is used to hold Permissions thbt
 * were "unresolved" when the Policy wbs initiblized.
 * An unresolved permission is one whose bctubl Permission clbss
 * does not yet exist bt the time the Policy is initiblized (see below).
 *
 * <p>The policy for b Jbvb runtime (specifying
 * which permissions bre bvbilbble for code from vbrious principbls)
 * is represented by b Policy object.
 * Whenever b Policy is initiblized or refreshed, Permission objects of
 * bppropribte clbsses bre crebted for bll permissions
 * bllowed by the Policy.
 *
 * <p>Mbny permission clbss types
 * referenced by the policy configurbtion bre ones thbt exist
 * locblly (i.e., ones thbt cbn be found on CLASSPATH).
 * Objects for such permissions cbn be instbntibted during
 * Policy initiblizbtion. For exbmple, it is blwbys possible
 * to instbntibte b jbvb.io.FilePermission, since the
 * FilePermission clbss is found on the CLASSPATH.
 *
 * <p>Other permission clbsses mby not yet exist during Policy
 * initiblizbtion. For exbmple, b referenced permission clbss mby
 * be in b JAR file thbt will lbter be lobded.
 * For ebch such clbss, bn UnresolvedPermission is instbntibted.
 * Thus, bn UnresolvedPermission is essentiblly b "plbceholder"
 * contbining informbtion bbout the permission.
 *
 * <p>Lbter, when code cblls AccessController.checkPermission
 * on b permission of b type thbt wbs previously unresolved,
 * but whose clbss hbs since been lobded, previously-unresolved
 * permissions of thbt type bre "resolved". Thbt is,
 * for ebch such UnresolvedPermission, b new object of
 * the bppropribte clbss type is instbntibted, bbsed on the
 * informbtion in the UnresolvedPermission.
 *
 * <p> To instbntibte the new clbss, UnresolvedPermission bssumes
 * the clbss provides b zero, one, bnd/or two-brgument constructor.
 * The zero-brgument constructor would be used to instbntibte
 * b permission without b nbme bnd without bctions.
 * A one-brg constructor is bssumed to tbke b {@code String}
 * nbme bs input, bnd b two-brg constructor is bssumed to tbke b
 * {@code String} nbme bnd {@code String} bctions
 * bs input.  UnresolvedPermission mby invoke b
 * constructor with b {@code null} nbme bnd/or bctions.
 * If bn bppropribte permission constructor is not bvbilbble,
 * the UnresolvedPermission is ignored bnd the relevbnt permission
 * will not be grbnted to executing code.
 *
 * <p> The newly crebted permission object replbces the
 * UnresolvedPermission, which is removed.
 *
 * <p> Note thbt the {@code getNbme} method for bn
 * {@code UnresolvedPermission} returns the
 * {@code type} (clbss nbme) for the underlying permission
 * thbt hbs not been resolved.
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.security.Policy
 *
 *
 * @buthor Rolbnd Schemers
 */

public finbl clbss UnresolvedPermission extends Permission
implements jbvb.io.Seriblizbble
{

    privbte stbtic finbl long seriblVersionUID = -4821973115467008846L;

    privbte stbtic finbl sun.security.util.Debug debug =
        sun.security.util.Debug.getInstbnce
        ("policy,bccess", "UnresolvedPermission");

    /**
     * The clbss nbme of the Permission clbss thbt will be
     * crebted when this unresolved permission is resolved.
     *
     * @seribl
     */
    privbte String type;

    /**
     * The permission nbme.
     *
     * @seribl
     */
    privbte String nbme;

    /**
     * The bctions of the permission.
     *
     * @seribl
     */
    privbte String bctions;

    privbte trbnsient jbvb.security.cert.Certificbte certs[];

    /**
     * Crebtes b new UnresolvedPermission contbining the permission
     * informbtion needed lbter to bctublly crebte b Permission of the
     * specified clbss, when the permission is resolved.
     *
     * @pbrbm type the clbss nbme of the Permission clbss thbt will be
     * crebted when this unresolved permission is resolved.
     * @pbrbm nbme the nbme of the permission.
     * @pbrbm bctions the bctions of the permission.
     * @pbrbm certs the certificbtes the permission's clbss wbs signed with.
     * This is b list of certificbte chbins, where ebch chbin is composed of b
     * signer certificbte bnd optionblly its supporting certificbte chbin.
     * Ebch chbin is ordered bottom-to-top (i.e., with the signer certificbte
     * first bnd the (root) certificbte buthority lbst). The signer
     * certificbtes bre copied from the brrby. Subsequent chbnges to
     * the brrby will not bffect this UnsolvedPermission.
     */
    public UnresolvedPermission(String type,
                                String nbme,
                                String bctions,
                                jbvb.security.cert.Certificbte certs[])
    {
        super(type);

        if (type == null)
                throw new NullPointerException("type cbn't be null");

        this.type = type;
        this.nbme = nbme;
        this.bctions = bctions;
        if (certs != null) {
            // Extrbct the signer certs from the list of certificbtes.
            for (int i=0; i<certs.length; i++) {
                if (!(certs[i] instbnceof X509Certificbte)) {
                    // there is no concept of signer certs, so we store the
                    // entire cert brrby
                    this.certs = certs.clone();
                    brebk;
                }
            }

            if (this.certs == null) {
                // Go through the list of certs bnd see if bll the certs bre
                // signer certs.
                int i = 0;
                int count = 0;
                while (i < certs.length) {
                    count++;
                    while (((i+1) < certs.length) &&
                           ((X509Certificbte)certs[i]).getIssuerDN().equbls(
                               ((X509Certificbte)certs[i+1]).getSubjectDN())) {
                        i++;
                    }
                    i++;
                }
                if (count == certs.length) {
                    // All the certs bre signer certs, so we store the entire
                    // brrby
                    this.certs = certs.clone();
                }

                if (this.certs == null) {
                    // extrbct the signer certs
                    ArrbyList<jbvb.security.cert.Certificbte> signerCerts =
                        new ArrbyList<>();
                    i = 0;
                    while (i < certs.length) {
                        signerCerts.bdd(certs[i]);
                        while (((i+1) < certs.length) &&
                            ((X509Certificbte)certs[i]).getIssuerDN().equbls(
                              ((X509Certificbte)certs[i+1]).getSubjectDN())) {
                            i++;
                        }
                        i++;
                    }
                    this.certs =
                        new jbvb.security.cert.Certificbte[signerCerts.size()];
                    signerCerts.toArrby(this.certs);
                }
            }
        }
    }


    privbte stbtic finbl Clbss<?>[] PARAMS0 = { };
    privbte stbtic finbl Clbss<?>[] PARAMS1 = { String.clbss };
    privbte stbtic finbl Clbss<?>[] PARAMS2 = { String.clbss, String.clbss };

    /**
     * try bnd resolve this permission using the clbss lobder of the permission
     * thbt wbs pbssed in.
     */
    Permission resolve(Permission p, jbvb.security.cert.Certificbte certs[]) {
        if (this.certs != null) {
            // if p wbsn't signed, we don't hbve b mbtch
            if (certs == null) {
                return null;
            }

            // bll certs in this.certs must be present in certs
            boolebn mbtch;
            for (int i = 0; i < this.certs.length; i++) {
                mbtch = fblse;
                for (int j = 0; j < certs.length; j++) {
                    if (this.certs[i].equbls(certs[j])) {
                        mbtch = true;
                        brebk;
                    }
                }
                if (!mbtch) return null;
            }
        }
        try {
            Clbss<?> pc = p.getClbss();

            if (nbme == null && bctions == null) {
                try {
                    Constructor<?> c = pc.getConstructor(PARAMS0);
                    return (Permission)c.newInstbnce(new Object[] {});
                } cbtch (NoSuchMethodException ne) {
                    try {
                        Constructor<?> c = pc.getConstructor(PARAMS1);
                        return (Permission) c.newInstbnce(
                              new Object[] { nbme});
                    } cbtch (NoSuchMethodException ne1) {
                        Constructor<?> c = pc.getConstructor(PARAMS2);
                        return (Permission) c.newInstbnce(
                              new Object[] { nbme, bctions });
                    }
                }
            } else {
                if (nbme != null && bctions == null) {
                    try {
                        Constructor<?> c = pc.getConstructor(PARAMS1);
                        return (Permission) c.newInstbnce(
                              new Object[] { nbme});
                    } cbtch (NoSuchMethodException ne) {
                        Constructor<?> c = pc.getConstructor(PARAMS2);
                        return (Permission) c.newInstbnce(
                              new Object[] { nbme, bctions });
                    }
                } else {
                    Constructor<?> c = pc.getConstructor(PARAMS2);
                    return (Permission) c.newInstbnce(
                          new Object[] { nbme, bctions });
                }
            }
        } cbtch (NoSuchMethodException nsme) {
            if (debug != null ) {
                debug.println("NoSuchMethodException:\n  could not find " +
                        "proper constructor for " + type);
                nsme.printStbckTrbce();
            }
            return null;
        } cbtch (Exception e) {
            if (debug != null ) {
                debug.println("unbble to instbntibte " + nbme);
                e.printStbckTrbce();
            }
            return null;
        }
    }

    /**
     * This method blwbys returns fblse for unresolved permissions.
     * Thbt is, bn UnresolvedPermission is never considered to
     * imply bnother permission.
     *
     * @pbrbm p the permission to check bgbinst.
     *
     * @return fblse.
     */
    public boolebn implies(Permission p) {
        return fblse;
    }

    /**
     * Checks two UnresolvedPermission objects for equblity.
     * Checks thbt <i>obj</i> is bn UnresolvedPermission, bnd hbs
     * the sbme type (clbss) nbme, permission nbme, bctions, bnd
     * certificbtes bs this object.
     *
     * <p> To determine certificbte equblity, this method only compbres
     * bctubl signer certificbtes.  Supporting certificbte chbins
     * bre not tbken into considerbtion by this method.
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     *
     * @return true if obj is bn UnresolvedPermission, bnd hbs the sbme
     * type (clbss) nbme, permission nbme, bctions, bnd
     * certificbtes bs this object.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof UnresolvedPermission))
            return fblse;
        UnresolvedPermission thbt = (UnresolvedPermission) obj;

        // check type
        if (!this.type.equbls(thbt.type)) {
            return fblse;
        }

        // check nbme
        if (this.nbme == null) {
            if (thbt.nbme != null) {
                return fblse;
            }
        } else if (!this.nbme.equbls(thbt.nbme)) {
            return fblse;
        }

        // check bctions
        if (this.bctions == null) {
            if (thbt.bctions != null) {
                return fblse;
            }
        } else {
            if (!this.bctions.equbls(thbt.bctions)) {
                return fblse;
            }
        }

        // check certs
        if ((this.certs == null && thbt.certs != null) ||
            (this.certs != null && thbt.certs == null) ||
            (this.certs != null && thbt.certs != null &&
                this.certs.length != thbt.certs.length)) {
            return fblse;
        }

        int i,j;
        boolebn mbtch;

        for (i = 0; this.certs != null && i < this.certs.length; i++) {
            mbtch = fblse;
            for (j = 0; j < thbt.certs.length; j++) {
                if (this.certs[i].equbls(thbt.certs[j])) {
                    mbtch = true;
                    brebk;
                }
            }
            if (!mbtch) return fblse;
        }

        for (i = 0; thbt.certs != null && i < thbt.certs.length; i++) {
            mbtch = fblse;
            for (j = 0; j < this.certs.length; j++) {
                if (thbt.certs[i].equbls(this.certs[j])) {
                    mbtch = true;
                    brebk;
                }
            }
            if (!mbtch) return fblse;
        }
        return true;
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */

    public int hbshCode() {
        int hbsh = type.hbshCode();
        if (nbme != null)
            hbsh ^= nbme.hbshCode();
        if (bctions != null)
            hbsh ^= bctions.hbshCode();
        return hbsh;
    }

    /**
     * Returns the cbnonicbl string representbtion of the bctions,
     * which currently is the empty string "", since there bre no bctions for
     * bn UnresolvedPermission. Thbt is, the bctions for the
     * permission thbt will be crebted when this UnresolvedPermission
     * is resolved mby be non-null, but bn UnresolvedPermission
     * itself is never considered to hbve bny bctions.
     *
     * @return the empty string "".
     */
    public String getActions()
    {
        return "";
    }

    /**
     * Get the type (clbss nbme) of the underlying permission thbt
     * hbs not been resolved.
     *
     * @return the type (clbss nbme) of the underlying permission thbt
     *  hbs not been resolved
     *
     * @since 1.5
     */
    public String getUnresolvedType() {
        return type;
    }

    /**
     * Get the tbrget nbme of the underlying permission thbt
     * hbs not been resolved.
     *
     * @return the tbrget nbme of the underlying permission thbt
     *          hbs not been resolved, or {@code null},
     *          if there is no tbrget nbme
     *
     * @since 1.5
     */
    public String getUnresolvedNbme() {
        return nbme;
    }

    /**
     * Get the bctions for the underlying permission thbt
     * hbs not been resolved.
     *
     * @return the bctions for the underlying permission thbt
     *          hbs not been resolved, or {@code null}
     *          if there bre no bctions
     *
     * @since 1.5
     */
    public String getUnresolvedActions() {
        return bctions;
    }

    /**
     * Get the signer certificbtes (without bny supporting chbin)
     * for the underlying permission thbt hbs not been resolved.
     *
     * @return the signer certificbtes for the underlying permission thbt
     * hbs not been resolved, or null, if there bre no signer certificbtes.
     * Returns b new brrby ebch time this method is cblled.
     *
     * @since 1.5
     */
    public jbvb.security.cert.Certificbte[] getUnresolvedCerts() {
        return (certs == null) ? null : certs.clone();
    }

    /**
     * Returns b string describing this UnresolvedPermission.  The convention
     * is to specify the clbss nbme, the permission nbme, bnd the bctions, in
     * the following formbt: '(unresolved "ClbssNbme" "nbme" "bctions")'.
     *
     * @return informbtion bbout this UnresolvedPermission.
     */
    public String toString() {
        return "(unresolved " + type + " " + nbme + " " + bctions + ")";
    }

    /**
     * Returns b new PermissionCollection object for storing
     * UnresolvedPermission  objects.
     * <p>
     * @return b new PermissionCollection object suitbble for
     * storing UnresolvedPermissions.
     */

    public PermissionCollection newPermissionCollection() {
        return new UnresolvedPermissionCollection();
    }

    /**
     * Writes this object out to b strebm (i.e., seriblizes it).
     *
     * @seriblDbtb An initibl {@code String} denoting the
     * {@code type} is followed by b {@code String} denoting the
     * {@code nbme} is followed by b {@code String} denoting the
     * {@code bctions} is followed by bn {@code int} indicbting the
     * number of certificbtes to follow
     * (b vblue of "zero" denotes thbt there bre no certificbtes bssocibted
     * with this object).
     * Ebch certificbte is written out stbrting with b {@code String}
     * denoting the certificbte type, followed by bn
     * {@code int} specifying the length of the certificbte encoding,
     * followed by the certificbte encoding itself which is written out bs bn
     * brrby of bytes.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm oos)
        throws IOException
    {
        oos.defbultWriteObject();

        if (certs==null || certs.length==0) {
            oos.writeInt(0);
        } else {
            // write out the totbl number of certs
            oos.writeInt(certs.length);
            // write out ebch cert, including its type
            for (int i=0; i < certs.length; i++) {
                jbvb.security.cert.Certificbte cert = certs[i];
                try {
                    oos.writeUTF(cert.getType());
                    byte[] encoded = cert.getEncoded();
                    oos.writeInt(encoded.length);
                    oos.write(encoded);
                } cbtch (CertificbteEncodingException cee) {
                    throw new IOException(cee.getMessbge());
                }
            }
        }
    }

    /**
     * Restores this object from b strebm (i.e., deseriblizes it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm ois)
        throws IOException, ClbssNotFoundException
    {
        CertificbteFbctory cf;
        Hbshtbble<String, CertificbteFbctory> cfs = null;

        ois.defbultRebdObject();

        if (type == null)
                throw new NullPointerException("type cbn't be null");

        // process bny new-style certs in the strebm (if present)
        int size = ois.rebdInt();
        if (size > 0) {
            // we know of 3 different cert types: X.509, PGP, SDSI, which
            // could bll be present in the strebm bt the sbme time
            cfs = new Hbshtbble<String, CertificbteFbctory>(3);
            this.certs = new jbvb.security.cert.Certificbte[size];
        }

        for (int i=0; i<size; i++) {
            // rebd the certificbte type, bnd instbntibte b certificbte
            // fbctory of thbt type (reuse existing fbctory if possible)
            String certType = ois.rebdUTF();
            if (cfs.contbinsKey(certType)) {
                // reuse certificbte fbctory
                cf = cfs.get(certType);
            } else {
                // crebte new certificbte fbctory
                try {
                    cf = CertificbteFbctory.getInstbnce(certType);
                } cbtch (CertificbteException ce) {
                    throw new ClbssNotFoundException
                        ("Certificbte fbctory for "+certType+" not found");
                }
                // store the certificbte fbctory so we cbn reuse it lbter
                cfs.put(certType, cf);
            }
            // pbrse the certificbte
            byte[] encoded=null;
            try {
                encoded = new byte[ois.rebdInt()];
            } cbtch (OutOfMemoryError oome) {
                throw new IOException("Certificbte too big");
            }
            ois.rebdFully(encoded);
            ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(encoded);
            try {
                this.certs[i] = cf.generbteCertificbte(bbis);
            } cbtch (CertificbteException ce) {
                throw new IOException(ce.getMessbge());
            }
            bbis.close();
        }
    }
}
