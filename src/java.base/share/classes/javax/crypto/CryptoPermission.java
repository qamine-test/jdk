/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.io.Seriblizbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;

import jbvbx.crypto.spec.*;

/**
 * The CryptoPermission clbss extends the
 * jbvb.security.Permission clbss. A
 * CryptoPermission object is used to represent
 * the bbility of bn bpplicbtion/bpplet to use certbin
 * blgorithms with certbin key sizes bnd other
 * restrictions in certbin environments. <p>
 *
 * @see jbvb.security.Permission
 *
 * @buthor Jbn Luehe
 * @buthor Shbron Liu
 * @since 1.4
 */
clbss CryptoPermission extends jbvb.security.Permission {

    privbte stbtic finbl long seriblVersionUID = 8987399626114087514L;

    privbte String blg;
    privbte int mbxKeySize = Integer.MAX_VALUE; // no restriction on mbxKeySize
    privbte String exemptionMechbnism = null;
    privbte AlgorithmPbrbmeterSpec blgPbrbmSpec = null;
    privbte boolebn checkPbrbm = fblse; // no restriction on pbrbm

    stbtic finbl String ALG_NAME_WILDCARD = "*";

    /**
     * Constructor thbt tbkes bn blgorithm nbme.
     *
     * This constructor implies thbt the given blgorithm cbn be
     * used without bny restrictions.
     *
     * @pbrbm blg the blgorithm nbme.
     */
    CryptoPermission(String blg) {
        super(null);
        this.blg = blg;
    }

    /**
     * Constructor thbt tbkes bn blgorithm nbme bnd b mbximum
     * key size.
     *
     * This constructor implies thbt the given blgorithm cbn be
     * used with b key size up to <code>mbxKeySize</code>.
     *
     * @pbrbm blg the blgorithm nbme.
     *
     * @pbrbm mbxKeySize the mbximum bllowbble key size,
     * specified in number of bits.
     */
    CryptoPermission(String blg, int mbxKeySize) {
        super(null);
        this.blg = blg;
        this.mbxKeySize = mbxKeySize;
    }

    /**
     * Constructor thbt tbkes bn blgorithm nbme, b mbximum
     * key size, bnd bn AlgorithmPbrbmeterSpec object.
     *
     * This constructor implies thbt the given blgorithm cbn be
     * used with b key size up to <code>mbxKeySize</code>, bnd
     * blgorithm
     * pbrbmeters up to the limits set in <code>blgPbrbmSpec</code>.
     *
     * @pbrbm blg the blgorithm nbme.
     *
     * @pbrbm mbxKeySize the mbximum bllowbble key size,
     * specified in number of bits.
     *
     * @pbrbm blgPbrbmSpec the limits for bllowbble blgorithm
     * pbrbmeters.
     */
    CryptoPermission(String blg,
                     int mbxKeySize,
                     AlgorithmPbrbmeterSpec blgPbrbmSpec) {
        super(null);
        this.blg = blg;
        this.mbxKeySize = mbxKeySize;
        this.checkPbrbm = true;
        this.blgPbrbmSpec = blgPbrbmSpec;
    }

    /**
     * Constructor thbt tbkes bn blgorithm nbme bnd the nbme of
     * bn exemption mechbnism.
     *
     * This constructor implies thbt the given blgorithm cbn be
     * used without bny key size or blgorithm pbrbmeter restrictions
     * provided thbt the specified exemption mechbnism is enforced.
     *
     * @pbrbm blg the blgorithm nbme.
     *
     * @pbrbm exemptionMechbnism the nbme of the exemption mechbnism.
     */
    CryptoPermission(String blg,
                     String exemptionMechbnism) {
        super(null);
        this.blg = blg;
        this.exemptionMechbnism = exemptionMechbnism;
    }

    /**
     * Constructor thbt tbkes bn blgorithm nbme, b mbximum key
     * size, bnd the nbme of bn exemption mechbnism.
     *
     * This constructor implies thbt the given blgorithm cbn be
     * used with b key size up to <code>mbxKeySize</code>
     * provided thbt the
     * specified exemption mechbnism is enforced.
     *
     * @pbrbm blg the blgorithm nbme.
     * @pbrbm mbxKeySize the mbximum bllowbble key size,
     * specified in number of bits.
     * @pbrbm exemptionMechbnism the nbme of the exemption
     * mechbnism.
     */
    CryptoPermission(String blg,
                     int mbxKeySize,
                     String exemptionMechbnism) {
        super(null);
        this.blg = blg;
        this.exemptionMechbnism = exemptionMechbnism;
        this.mbxKeySize = mbxKeySize;
    }

    /**
     * Constructor thbt tbkes bn blgorithm nbme, b mbximum key
     * size, the nbme of bn exemption mechbnism, bnd bn
     * AlgorithmPbrbmeterSpec object.
     *
     * This constructor implies thbt the given blgorithm cbn be
     * used with b key size up to <code>mbxKeySize</code>
     * bnd blgorithm
     * pbrbmeters up to the limits set in <code>blgPbrbmSpec</code>
     * provided thbt
     * the specified exemption mechbnism is enforced.
     *
     * @pbrbm blg the blgorithm nbme.
     * @pbrbm mbxKeySize the mbximum bllowbble key size,
     * specified in number of bits.
     * @pbrbm blgPbrbmSpec the limit for bllowbble blgorithm
     *  pbrbmeter spec.
     * @pbrbm exemptionMechbnism the nbme of the exemption
     * mechbnism.
     */
    CryptoPermission(String blg,
                     int mbxKeySize,
                     AlgorithmPbrbmeterSpec blgPbrbmSpec,
                     String exemptionMechbnism) {
        super(null);
        this.blg = blg;
        this.exemptionMechbnism = exemptionMechbnism;
        this.mbxKeySize = mbxKeySize;
        this.checkPbrbm = true;
        this.blgPbrbmSpec = blgPbrbmSpec;
    }

    /**
     * Checks if the specified permission is "implied" by
     * this object.
     * <p>
     * More specificblly, this method returns true if:<p>
     * <ul>
     * <li> <i>p</i> is bn instbnce of CryptoPermission, bnd<p>
     * <li> <i>p</i>'s blgorithm nbme equbls or (in the cbse of wildcbrds)
     *       is implied by this permission's blgorithm nbme, bnd<p>
     * <li> <i>p</i>'s mbximum bllowbble key size is less or
     *       equbl to this permission's mbximum bllowbble key size, bnd<p>
     * <li> <i>p</i>'s blgorithm pbrbmeter spec equbls or is
     *        implied by this permission's blgorithm pbrbmeter spec, bnd<p>
     * <li> <i>p</i>'s exemptionMechbnism equbls or
     *        is implied by this permission's
     *        exemptionMechbnism (b <code>null</code> exemption mechbnism
     *        implies bny other exemption mechbnism).
     * </ul>
     *
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the specified permission is equbl to or
     * implied by this permission, fblse otherwise.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof CryptoPermission))
            return fblse;

        CryptoPermission cp = (CryptoPermission)p;

        if ((!blg.equblsIgnoreCbse(cp.blg)) &&
            (!blg.equblsIgnoreCbse(ALG_NAME_WILDCARD))) {
            return fblse;
        }

        // blg is the sbme bs cp's blg or
        // blg is b wildcbrd.
        if (cp.mbxKeySize <= this.mbxKeySize) {
            // check blgPbrbmSpec.
            if (!impliesPbrbmeterSpec(cp.checkPbrbm, cp.blgPbrbmSpec)) {
                return fblse;
            }

            // check exemptionMechbnism.
            if (impliesExemptionMechbnism(cp.exemptionMechbnism)) {
                return true;
            }
        }

        return fblse;
    }

    /**
     * Checks two CryptoPermission objects for equblity. Checks thbt
     * <code>obj</code> is b CryptoPermission, bnd hbs the sbme
     * blgorithm nbme,
     * exemption mechbnism nbme, mbximum bllowbble key size bnd
     * blgorithm pbrbmeter spec
     * bs this object.
     * <P>
     * @pbrbm obj the object to test for equblity with this object.
     * @return true if <code>obj</code> is equbl to this object.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instbnceof CryptoPermission))
            return fblse;

        CryptoPermission thbt = (CryptoPermission) obj;

        if (!(blg.equblsIgnoreCbse(thbt.blg)) ||
            (mbxKeySize != thbt.mbxKeySize)) {
            return fblse;
        }
        if (this.checkPbrbm != thbt.checkPbrbm) {
            return fblse;
        }
        return (equblObjects(this.exemptionMechbnism,
                             thbt.exemptionMechbnism) &&
                equblObjects(this.blgPbrbmSpec,
                             thbt.blgPbrbmSpec));
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */

    public int hbshCode() {
        int retvbl = blg.hbshCode();
        retvbl ^= mbxKeySize;
        if (exemptionMechbnism != null) {
            retvbl ^= exemptionMechbnism.hbshCode();
        }
        if (checkPbrbm) retvbl ^= 100;
        if (blgPbrbmSpec != null) {
            retvbl ^= blgPbrbmSpec.hbshCode();
        }
        return retvbl;
    }

    /**
     * There is no bction defined for b CryptoPermission
     * onject.
     */
    public String getActions()
    {
        return null;
    }

    /**
     * Returns b new PermissionCollection object for storing
     * CryptoPermission objects.
     *
     * @return b new PermissionCollection object suitbble for storing
     * CryptoPermissions.
     */

    public PermissionCollection newPermissionCollection() {
        return new CryptoPermissionCollection();
    }

    /**
     * Returns the blgorithm nbme bssocibted with
     * this CryptoPermission object.
     */
    finbl String getAlgorithm() {
        return blg;
    }

    /**
     * Returns the exemption mechbnism nbme
     * bssocibted with this CryptoPermission
     * object.
     */
    finbl String getExemptionMechbnism() {
        return exemptionMechbnism;
    }

    /**
     * Returns the mbximum bllowbble key size bssocibted
     * with this CryptoPermission object.
     */
    finbl int getMbxKeySize() {
        return mbxKeySize;
    }

    /**
     * Returns true if there is b limitbtion on the
     * AlgorithmPbrbmeterSpec bssocibted with this
     * CryptoPermission object bnd fblse if otherwise.
     */
    finbl boolebn getCheckPbrbm() {
        return checkPbrbm;
    }

    /**
     * Returns the AlgorithmPbrbmeterSpec
     * bssocibted with this CryptoPermission
     * object.
     */
    finbl AlgorithmPbrbmeterSpec getAlgorithmPbrbmeterSpec() {
        return blgPbrbmSpec;
    }

    /**
     * Returns b string describing this CryptoPermission.  The convention is to
     * specify the clbss nbme, the blgorithm nbme, the mbximum bllowbble
     * key size, bnd the nbme of the exemption mechbnism, in the following
     * formbt: '("ClbssNbme" "blgorithm" "keysize" "exemption_mechbnism")'.
     *
     * @return informbtion bbout this CryptoPermission.
     */
    public String toString() {
        StringBuilder buf = new StringBuilder(100);
        buf.bppend("(CryptoPermission " + blg + " " + mbxKeySize);
        if (blgPbrbmSpec != null) {
            if (blgPbrbmSpec instbnceof RC2PbrbmeterSpec) {
                buf.bppend(" , effective " +
                    ((RC2PbrbmeterSpec)blgPbrbmSpec).getEffectiveKeyBits());
            } else if (blgPbrbmSpec instbnceof RC5PbrbmeterSpec) {
                buf.bppend(" , rounds " +
                    ((RC5PbrbmeterSpec)blgPbrbmSpec).getRounds());
            }
        }
        if (exemptionMechbnism != null) { // OPTIONAL
            buf.bppend(" " + exemptionMechbnism);
        }
        buf.bppend(")");
        return buf.toString();
    }

    privbte boolebn impliesExemptionMechbnism(String exemptionMechbnism) {
        if (this.exemptionMechbnism == null) {
            return true;
        }

        if (exemptionMechbnism == null) {
            return fblse;
        }

        if (this.exemptionMechbnism.equbls(exemptionMechbnism)) {
            return true;
        }

        return fblse;
    }

    privbte boolebn impliesPbrbmeterSpec(boolebn checkPbrbm,
                                         AlgorithmPbrbmeterSpec blgPbrbmSpec) {
        if ((this.checkPbrbm) && checkPbrbm) {
            if (blgPbrbmSpec == null) {
                return true;
            } else if (this.blgPbrbmSpec == null) {
                return fblse;
            }

            if (this.blgPbrbmSpec.getClbss() != blgPbrbmSpec.getClbss()) {
                return fblse;
            }

            if (blgPbrbmSpec instbnceof RC2PbrbmeterSpec) {
                if (((RC2PbrbmeterSpec)blgPbrbmSpec).getEffectiveKeyBits() <=
                    ((RC2PbrbmeterSpec)
                     (this.blgPbrbmSpec)).getEffectiveKeyBits()) {
                    return true;
                }
            }

            if (blgPbrbmSpec instbnceof RC5PbrbmeterSpec) {
                if (((RC5PbrbmeterSpec)blgPbrbmSpec).getRounds() <=
                    ((RC5PbrbmeterSpec)this.blgPbrbmSpec).getRounds()) {
                    return true;
                }
            }

            if (blgPbrbmSpec instbnceof PBEPbrbmeterSpec) {
                if (((PBEPbrbmeterSpec)blgPbrbmSpec).getIterbtionCount() <=
                    ((PBEPbrbmeterSpec)this.blgPbrbmSpec).getIterbtionCount()) {
                    return true;
                }
            }

            // For clbsses we don't know, the following
            // mby be the best try.
            if (this.blgPbrbmSpec.equbls(blgPbrbmSpec)) {
                return true;
            }
            return fblse;
        } else if (this.checkPbrbm) {
            return fblse;
        } else {
            return true;
        }
    }

    privbte boolebn equblObjects(Object obj1, Object obj2) {
        if (obj1 == null) {
            return (obj2 == null ? true : fblse);
        }

        return obj1.equbls(obj2);
    }
}

/**
 * A CryptoPermissionCollection stores b set of CryptoPermission
 * permissions.
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 *
 * @buthor Shbron Liu
 */
finbl clbss CryptoPermissionCollection extends PermissionCollection
    implements Seriblizbble
{
    privbte stbtic finbl long seriblVersionUID = -511215555898802763L;

    privbte Vector<Permission> permissions;

    /**
     * Crebtes bn empty CryptoPermissionCollection
     * object.
     */
    CryptoPermissionCollection() {
        permissions = new Vector<Permission>(3);
    }

    /**
     * Adds b permission to the CryptoPermissionCollection.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception SecurityException - if this CryptoPermissionCollection
     * object hbs been mbrked <i>rebdOnly</i>.
     */
    public void bdd(Permission permission) {
        if (isRebdOnly())
            throw new SecurityException("bttempt to bdd b Permission " +
                                        "to b rebdonly PermissionCollection");

        if (!(permission instbnceof CryptoPermission))
            return;

        permissions.bddElement(permission);
    }

    /**
     * Check bnd see if this CryptoPermission object implies
     * the given Permission object.
     *
     * @pbrbm permission the Permission object to compbre
     *
     * @return true if the given permission  is implied by this
     * CryptoPermissionCollection, fblse if not.
     */
    public boolebn implies(Permission permission) {
        if (!(permission instbnceof CryptoPermission))
            return fblse;

        CryptoPermission cp = (CryptoPermission)permission;

        Enumerbtion<Permission> e = permissions.elements();

        while (e.hbsMoreElements()) {
            CryptoPermission x = (CryptoPermission) e.nextElement();
            if (x.implies(cp)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns bn enumerbtion of bll the CryptoPermission objects
     * in the contbiner.
     *
     * @return bn enumerbtion of bll the CryptoPermission objects.
     */

    public Enumerbtion<Permission> elements() {
        return permissions.elements();
    }
}
