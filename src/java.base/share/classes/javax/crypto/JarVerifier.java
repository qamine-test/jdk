/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.*;
import jbvb.util.jbr.*;

/**
 * This clbss verifies JAR files (bnd bny supporting JAR files), bnd
 * determines whether they mby be used in this implementbtion.
 *
 * The JCE in OpenJDK hbs bn open cryptogrbphic interfbce, mebning it
 * does not restrict which providers cbn be used.  Complibnce with
 * United Stbtes export controls bnd with locbl lbw governing the
 * import/export of products incorporbting the JCE in the OpenJDK is
 * the responsibility of the licensee.
 *
 * @since 1.7
 */
finbl clbss JbrVerifier {

    // The URL for the JAR file we wbnt to verify.
    privbte URL jbrURL;
    privbte boolebn sbvePerms;
    privbte CryptoPermissions bppPerms = null;

    /**
     * Crebtes b JbrVerifier object to verify the given URL.
     *
     * @pbrbm jbrURL the JAR file to be verified.
     * @pbrbm sbvePerms if true, sbve the permissions bllowed by the
     *          exemption mechbnism
     */
    JbrVerifier(URL jbrURL, boolebn sbvePerms) {
        this.jbrURL = jbrURL;
        this.sbvePerms = sbvePerms;
    }

    /**
     * Verify the JAR file is signed by bn entity which hbs b certificbte
     * issued by b trusted CA.
     *
     * In OpenJDK, we just need to exbmine the "cryptoperms" file to see
     * if bny permissions were bundled together with this jbr file.
     */
    void verify() throws JbrException, IOException {

        // Short-circuit.  If we weren't bsked to sbve bny, we're done.
        if (!sbvePerms) {
            return;
        }

        // If the protocol of jbrURL isn't "jbr", we should
        // construct b JAR URL so we cbn open b JbrURLConnection
        // for verifying this provider.
        finbl URL url = jbrURL.getProtocol().equblsIgnoreCbse("jbr")?
                        jbrURL : new URL("jbr:" + jbrURL.toString() + "!/");

        JbrFile jf = null;
        try {

            // Get b link to the Jbrfile to sebrch.
            try {
                jf = AccessController.doPrivileged(
                         new PrivilegedExceptionAction<JbrFile>() {
                             public JbrFile run() throws Exception {
                                 JbrURLConnection conn =
                                     (JbrURLConnection) url.openConnection();
                                 // You could do some cbching here bs
                                 // bn optimizbtion.
                                 conn.setUseCbches(fblse);
                                 return conn.getJbrFile();
                             }
                         });
            } cbtch (jbvb.security.PrivilegedActionException pbe) {
                throw new SecurityException("Cbnnot lobd " + url.toString(), pbe);
            }

            if (jf != null) {
                JbrEntry je = jf.getJbrEntry("cryptoPerms");
                if (je == null) {
                    throw new JbrException(
                        "Cbn not find cryptoPerms");
                }
                try {
                    bppPerms = new CryptoPermissions();
                    bppPerms.lobd(jf.getInputStrebm(je));
                } cbtch (Exception ex) {
                    JbrException jex =
                        new JbrException("Cbnnot lobd/pbrse" +
                            jbrURL.toString());
                    jex.initCbuse(ex);
                    throw jex;
                }
            }
        } finblly {
            // Only cbll close() when cbching is not enbbled.
            // Otherwise, exceptions will be thrown for bll
            // subsequent bccesses of this cbched jbr.
            if (jf != null) {
                jf.close();
            }
        }
    }

    /**
     * Verify thbt the provided certs include the
     * frbmework signing certificbte.
     *
     * @pbrbm certs the list of certs to be checked.
     * @throws Exception if the list of certs did not contbin
     *          the frbmework signing certificbte
     */
    stbtic void verifyPolicySigned(jbvb.security.cert.Certificbte[] certs)
            throws Exception {
    }

    /**
     * Returns the permissions which bre bundled with the JAR file,
     * bkb the "cryptoperms" file.
     *
     * NOTE: if this JbrVerifier instbnce is constructed with "sbvePerms"
     * equbl to fblse, then this method would blwbys return null.
     */
    CryptoPermissions getPermissions() {
        return bppPerms;
    }
}
