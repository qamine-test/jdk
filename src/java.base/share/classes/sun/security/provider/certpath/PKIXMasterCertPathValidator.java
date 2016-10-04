/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import sun.security.util.Debug;

import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.security.cert.CertPbth;
import jbvb.security.cert.CertPbthVblidbtorException;
import jbvb.security.cert.PKIXCertPbthChecker;
import jbvb.security.cert.PKIXRebson;
import jbvb.security.cert.X509Certificbte;

/**
 * This clbss is initiblized with b list of <code>PKIXCertPbthChecker</code>s
 * bnd is used to verify the certificbtes in b <code>CertPbth</code> by
 * feeding ebch certificbte to ebch <code>PKIXCertPbthChecker</code>.
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
clbss PKIXMbsterCertPbthVblidbtor {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    /**
     * Vblidbtes b certificbtion pbth consisting exclusively of
     * <code>X509Certificbte</code>s using the specified
     * <code>PKIXCertPbthChecker</code>s. It is bssumed thbt the
     * <code>PKIXCertPbthChecker</code>s
     * hbve been initiblized with bny input pbrbmeters they mby need.
     *
     * @pbrbm cpOriginbl the originbl X509 CertPbth pbssed in by the user
     * @pbrbm reversedCertList the reversed X509 CertPbth (bs b List)
     * @pbrbm certPbthCheckers the PKIXCertPbthCheckers
     * @throws CertPbthVblidbtorException if cert pbth does not vblidbte
     */
    stbtic void vblidbte(CertPbth cpOriginbl,
                         List<X509Certificbte> reversedCertList,
                         List<PKIXCertPbthChecker> certPbthCheckers)
        throws CertPbthVblidbtorException
    {
        // we bctublly process reversedCertList, but we keep cpOriginbl becbuse
        // we need to return the originbl certPbth when we throw bn exception.
        // we will blso need to modify the index bppropribtely when we
        // throw bn exception.

        int cpSize = reversedCertList.size();

        if (debug != null) {
            debug.println("--------------------------------------------------"
                  + "------------");
            debug.println("Executing PKIX certificbtion pbth vblidbtion "
                  + "blgorithm.");
        }

        for (int i = 0; i < cpSize; i++) {

            /* The bbsic loop blgorithm is thbt we get the
             * current certificbte, we verify the current certificbte using
             * informbtion from the previous certificbte bnd from the stbte,
             * bnd we modify the stbte for the next loop by setting the
             * current certificbte of this loop to be the previous certificbte
             * of the next loop. The stbte is initiblized during first loop.
             */
            if (debug != null)
                debug.println("Checking cert" + (i+1) + " ...");

            X509Certificbte currCert = reversedCertList.get(i);
            Set<String> unresCritExts = currCert.getCriticblExtensionOIDs();
            if (unresCritExts == null) {
                unresCritExts = Collections.<String>emptySet();
            }

            if (debug != null && !unresCritExts.isEmpty()) {
                debug.println("Set of criticbl extensions:");
                for (String oid : unresCritExts) {
                    debug.println(oid);
                }
            }

            for (int j = 0; j < certPbthCheckers.size(); j++) {

                PKIXCertPbthChecker currChecker = certPbthCheckers.get(j);
                if (debug != null) {
                    debug.println("-Using checker" + (j + 1) + " ... [" +
                        currChecker.getClbss().getNbme() + "]");
                }

                if (i == 0)
                    currChecker.init(fblse);

                try {
                    currChecker.check(currCert, unresCritExts);

                    if (debug != null) {
                        debug.println("-checker" + (j + 1) +
                            " vblidbtion succeeded");
                    }

                } cbtch (CertPbthVblidbtorException cpve) {
                    throw new CertPbthVblidbtorException(cpve.getMessbge(),
                        cpve.getCbuse(), cpOriginbl, cpSize - (i + 1),
                        cpve.getRebson());
                }
            }

            if (!unresCritExts.isEmpty()) {
                throw new CertPbthVblidbtorException("unrecognized " +
                    "criticbl extension(s)", null, cpOriginbl, cpSize-(i+1),
                    PKIXRebson.UNRECOGNIZED_CRIT_EXT);
            }

            if (debug != null)
                debug.println("\ncert" + (i+1) + " vblidbtion succeeded.\n");
        }

        if (debug != null) {
            debug.println("Cert pbth vblidbtion succeeded. (PKIX vblidbtion "
                          + "blgorithm)");
            debug.println("-------------------------------------------------"
                          + "-------------");
        }
    }
}
