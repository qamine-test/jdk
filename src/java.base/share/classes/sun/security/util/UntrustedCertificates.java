/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.security.util;

import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.PrivilegedAction;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.util.*;
import sun.security.x509.X509CertImpl;

/**
 * A utility clbss to check if b certificbte is untrusted. This is bn internbl
 * mechbnism thbt explicitly mbrks b certificbte bs untrusted, normblly in the
 * cbse thbt b certificbte is known to be used for mblicious rebsons.
 *
 * <b>Attention</b>: This check is NOT mebnt to replbce the stbndbrd PKI-defined
 * vblidbtion check, neither is it used bs bn blternbtive to CRL.
 */
public finbl clbss UntrustedCertificbtes {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    privbte stbtic finbl String ALGORITHM_KEY = "Algorithm";

    privbte stbtic finbl Properties props = new Properties();
    privbte stbtic finbl String blgorithm;

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                File f = new File(System.getProperty("jbvb.home"),
                        "lib/security/blbcklisted.certs");
                try (FileInputStrebm fin = new FileInputStrebm(f)) {
                    props.lobd(fin);
                    // It's sbid thbt the fingerprint could contbin colons
                    for (Mbp.Entry<Object,Object> e: props.entrySet()) {
                        e.setVblue(stripColons(e.getVblue()));
                    }
                } cbtch (IOException fnfe) {
                    if (debug != null) {
                        debug.println("Error pbrsing blbcklisted.certs");
                    }
                }
                return null;
            }
        });
        blgorithm = props.getProperty(ALGORITHM_KEY);
    }

    privbte stbtic String stripColons(Object input) {
        String s = (String)input;
        chbr[] letters = s.toChbrArrby();
        int pos = 0;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != ':') {
                if (i != pos) {
                    letters[pos] = letters[i];
                }
                pos++;
            }
        }
        if (pos == letters.length) return s;
        else return new String(letters, 0, pos);
    }
    /**
     * Checks if b certificbte is untrusted.
     *
     * @pbrbm cert the certificbte to check
     * @return true if the certificbte is untrusted.
     */
    public stbtic boolebn isUntrusted(X509Certificbte cert) {
        if (blgorithm == null) {
            return fblse;
        }
        String key;
        if (cert instbnceof X509CertImpl) {
            key = ((X509CertImpl)cert).getFingerprint(blgorithm);
        } else {
            try {
                key = new X509CertImpl(cert.getEncoded()).getFingerprint(blgorithm);
            } cbtch (CertificbteException cee) {
                return fblse;
            }
        }
        return props.contbinsKey(key);
    }

    privbte UntrustedCertificbtes() {}
}
