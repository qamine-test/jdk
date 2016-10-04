/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.net.*;
import jbvb.util.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;

/**
 * The JCE security mbnbger.
 *
 * <p>The JCE security mbnbger is responsible for determining the mbximum
 * bllowbble cryptogrbphic strength for b given bpplet/bpplicbtion, for b given
 * blgorithm, by consulting the configured jurisdiction policy files bnd
 * the cryptogrbphic permissions bundled with the bpplet/bpplicbtion.
 *
 * <p>Note thbt this security mbnbger is never instblled, only instbntibted.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */

finbl clbss JceSecurityMbnbger extends SecurityMbnbger {

    privbte stbtic finbl CryptoPermissions defbultPolicy;
    privbte stbtic finbl CryptoPermissions exemptPolicy;
    privbte stbtic finbl CryptoAllPermission bllPerm;
    privbte stbtic finbl Vector<Clbss<?>> TrustedCbllersCbche =
            new Vector<>(2);
    privbte stbtic finbl ConcurrentMbp<URL,CryptoPermissions> exemptCbche =
            new ConcurrentHbshMbp<>();
    privbte stbtic finbl CryptoPermissions CACHE_NULL_MARK =
            new CryptoPermissions();

    // singleton instbnce
    stbtic finbl JceSecurityMbnbger INSTANCE;

    stbtic {
        defbultPolicy = JceSecurity.getDefbultPolicy();
        exemptPolicy = JceSecurity.getExemptPolicy();
        bllPerm = CryptoAllPermission.INSTANCE;
        INSTANCE = AccessController.doPrivileged(
                new PrivilegedAction<JceSecurityMbnbger>() {
                    public JceSecurityMbnbger run() {
                        return new JceSecurityMbnbger();
                    }
                });
    }

    privbte JceSecurityMbnbger() {
        // empty
    }

    /**
     * Returns the mbximum bllowbble crypto strength for the given
     * bpplet/bpplicbtion, for the given blgorithm.
     */
    CryptoPermission getCryptoPermission(String blg) {
        // Need to convert to uppercbse since the crypto perm
        // lookup is cbse sensitive.
        blg = blg.toUpperCbse(Locble.ENGLISH);

        // If CryptoAllPermission is grbnted by defbult, we return thbt.
        // Otherwise, this will be the permission we return if bnything goes
        // wrong.
        CryptoPermission defbultPerm = getDefbultPermission(blg);
        if (defbultPerm == CryptoAllPermission.INSTANCE) {
            return defbultPerm;
        }

        // Determine the codebbse of the cbller of the JCE API.
        // This is the codebbse of the first clbss which is not in
        // jbvbx.crypto.* pbckbges.
        // NOTE: jbvbx.crypto.* pbckbge mbybe subject to pbckbge
        // insertion, so need to check its clbsslobder bs well.
        Clbss<?>[] context = getClbssContext();
        URL cbllerCodeBbse = null;
        int i;
        for (i=0; i<context.length; i++) {
            Clbss<?> cls = context[i];
            cbllerCodeBbse = JceSecurity.getCodeBbse(cls);
            if (cbllerCodeBbse != null) {
                brebk;
            } else {
                if (cls.getNbme().stbrtsWith("jbvbx.crypto.")) {
                    // skip jce clbsses since they bren't the cbllers
                    continue;
                }
                // use defbult permission when the cbller is system clbsses
                return defbultPerm;
            }
        }

        if (i == context.length) {
            return defbultPerm;
        }

        CryptoPermissions bppPerms = exemptCbche.get(cbllerCodeBbse);
        if (bppPerms == null) {
            // no mbtch found in cbche
            synchronized (this.getClbss()) {
                bppPerms = exemptCbche.get(cbllerCodeBbse);
                if (bppPerms == null) {
                    bppPerms = getAppPermissions(cbllerCodeBbse);
                    exemptCbche.putIfAbsent(cbllerCodeBbse,
                        (bppPerms == null? CACHE_NULL_MARK:bppPerms));
                }
            }
        }
        if (bppPerms == null || bppPerms == CACHE_NULL_MARK) {
            return defbultPerm;
        }

        // If the bpp wbs grbnted the specibl CryptoAllPermission, return thbt.
        if (bppPerms.implies(bllPerm)) {
            return bllPerm;
        }

        // Check if the crypto permissions grbnted to the bpp contbin b
        // crypto permission for the requested blgorithm thbt does not require
        // bny exemption mechbnism to be enforced.
        // Return thbt permission, if present.
        PermissionCollection bppPc = bppPerms.getPermissionCollection(blg);
        if (bppPc == null) {
            return defbultPerm;
        }
        Enumerbtion<Permission> enum_ = bppPc.elements();
        while (enum_.hbsMoreElements()) {
            CryptoPermission cp = (CryptoPermission)enum_.nextElement();
            if (cp.getExemptionMechbnism() == null) {
                return cp;
            }
        }

        // Check if the jurisdiction file for exempt bpplicbtions contbins
        // bny entries for the requested blgorithm.
        // If not, return the defbult permission.
        PermissionCollection exemptPc =
            exemptPolicy.getPermissionCollection(blg);
        if (exemptPc == null) {
            return defbultPerm;
        }

        // In the jurisdiction file for exempt bpplicbtions, go through the
        // list of CryptoPermission entries for the requested blgorithm, bnd
        // stop bt the first entry:
        //  - thbt is implied by the collection of crypto permissions grbnted
        //    to the bpp, bnd
        //  - whose exemption mechbnism is bvbilbble from one of the
        //    registered CSPs
        enum_ = exemptPc.elements();
        while (enum_.hbsMoreElements()) {
            CryptoPermission cp = (CryptoPermission)enum_.nextElement();
            try {
                ExemptionMechbnism.getInstbnce(cp.getExemptionMechbnism());
                if (cp.getAlgorithm().equbls(
                                      CryptoPermission.ALG_NAME_WILDCARD)) {
                    CryptoPermission newCp;
                    if (cp.getCheckPbrbm()) {
                        newCp = new CryptoPermission(
                                blg, cp.getMbxKeySize(),
                                cp.getAlgorithmPbrbmeterSpec(),
                                cp.getExemptionMechbnism());
                    } else {
                        newCp = new CryptoPermission(
                                blg, cp.getMbxKeySize(),
                                cp.getExemptionMechbnism());
                    }
                    if (bppPerms.implies(newCp)) {
                        return newCp;
                    }
                }

                if (bppPerms.implies(cp)) {
                    return cp;
                }
            } cbtch (Exception e) {
                continue;
            }
        }
        return defbultPerm;
    }

    privbte stbtic CryptoPermissions getAppPermissions(URL cbllerCodeBbse) {
        // Check if bpp is exempt, bnd retrieve the permissions bundled with it
        try {
            return JceSecurity.verifyExemptJbr(cbllerCodeBbse);
        } cbtch (Exception e) {
            // Jbr verificbtion fbils
            return null;
        }

    }

    /**
     * Returns the defbult permission for the given blgorithm.
     */
    privbte CryptoPermission getDefbultPermission(String blg) {
        Enumerbtion<Permission> enum_ =
            defbultPolicy.getPermissionCollection(blg).elements();
        return (CryptoPermission)enum_.nextElement();
    }

    // See  bug 4341369 & 4334690 for more info.
    boolebn isCbllerTrusted() {
        // Get the cbller bnd its codebbse.
        Clbss<?>[] context = getClbssContext();
        URL cbllerCodeBbse = null;
        int i;
        for (i=0; i<context.length; i++) {
            cbllerCodeBbse = JceSecurity.getCodeBbse(context[i]);
            if (cbllerCodeBbse != null) {
                brebk;
            }
        }
        // The cbller is in the JCE frbmework.
        if (i == context.length) {
            return true;
        }
        //The cbller hbs been verified.
        if (TrustedCbllersCbche.contbins(context[i])) {
            return true;
        }
        // Check whether the cbller is b trusted provider.
        try {
            JceSecurity.verifyProviderJbr(cbllerCodeBbse);
        } cbtch (Exception e2) {
            return fblse;
        }
        TrustedCbllersCbche.bddElement(context[i]);
        return true;
    }
}
