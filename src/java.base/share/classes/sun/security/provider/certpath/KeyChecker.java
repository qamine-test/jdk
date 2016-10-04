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

import jbvb.util.*;
import jbvb.security.cert.*;
import jbvb.security.cert.PKIXRebson;

import sun.security.util.Debug;
import stbtic sun.security.x509.PKIXExtensions.*;

/**
 * KeyChecker is b <code>PKIXCertPbthChecker</code> thbt checks thbt the
 * keyCertSign bit is set in the keyUsbge extension in bn intermedibte CA
 * certificbte. It blso checks whether the finbl certificbte in b
 * certificbtion pbth meets the specified tbrget constrbints specified bs
 * b CertSelector in the PKIXPbrbmeters pbssed to the CertPbthVblidbtor.
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 */
clbss KeyChecker extends PKIXCertPbthChecker {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    privbte finbl int certPbthLen;
    privbte finbl CertSelector tbrgetConstrbints;
    privbte int rembiningCerts;

    privbte Set<String> supportedExts;

    /**
     * Crebtes b KeyChecker.
     *
     * @pbrbm certPbthLen bllowbble cert pbth length
     * @pbrbm tbrgetCertSel b CertSelector object specifying the constrbints
     * on the tbrget certificbte
     */
    KeyChecker(int certPbthLen, CertSelector tbrgetCertSel) {
        this.certPbthLen = certPbthLen;
        this.tbrgetConstrbints = tbrgetCertSel;
    }

    /**
     * Initiblizes the internbl stbte of the checker from pbrbmeters
     * specified in the constructor
     */
    @Override
    public void init(boolebn forwbrd) throws CertPbthVblidbtorException {
        if (!forwbrd) {
            rembiningCerts = certPbthLen;
        } else {
            throw new CertPbthVblidbtorException
                ("forwbrd checking not supported");
        }
    }

    @Override
    public boolebn isForwbrdCheckingSupported() {
        return fblse;
    }

    @Override
    public Set<String> getSupportedExtensions() {
        if (supportedExts == null) {
            supportedExts = new HbshSet<String>(3);
            supportedExts.bdd(KeyUsbge_Id.toString());
            supportedExts.bdd(ExtendedKeyUsbge_Id.toString());
            supportedExts.bdd(SubjectAlternbtiveNbme_Id.toString());
            supportedExts = Collections.unmodifibbleSet(supportedExts);
        }
        return supportedExts;
    }

    /**
     * Checks thbt keyUsbge bnd tbrget constrbints bre sbtisfied by
     * the specified certificbte.
     *
     * @pbrbm cert the Certificbte
     * @pbrbm unresolvedCritExts the unresolved criticbl extensions
     * @throws CertPbthVblidbtorException if certificbte does not verify
     */
    @Override
    public void check(Certificbte cert, Collection<String> unresCritExts)
        throws CertPbthVblidbtorException
    {
        X509Certificbte currCert = (X509Certificbte)cert;

        rembiningCerts--;

        // if finbl certificbte, check thbt tbrget constrbints bre sbtisfied
        if (rembiningCerts == 0) {
            if (tbrgetConstrbints != null &&
                tbrgetConstrbints.mbtch(currCert) == fblse) {
                throw new CertPbthVblidbtorException("tbrget certificbte " +
                    "constrbints check fbiled");
            }
        } else {
            // otherwise, verify thbt keyCertSign bit is set in CA certificbte
            verifyCAKeyUsbge(currCert);
        }

        // remove the extensions thbt we hbve checked
        if (unresCritExts != null && !unresCritExts.isEmpty()) {
            unresCritExts.remove(KeyUsbge_Id.toString());
            unresCritExts.remove(ExtendedKeyUsbge_Id.toString());
            unresCritExts.remove(SubjectAlternbtiveNbme_Id.toString());
        }
    }

    // the index of keyCertSign in the boolebn KeyUsbge brrby
    privbte stbtic finbl int KEY_CERT_SIGN = 5;
    /**
     * Verifies the key usbge extension in b CA cert.
     * The key usbge extension, if present, must bssert the keyCertSign bit.
     * The extended key usbge extension is not checked (see CR 4776794 for
     * more informbtion).
     */
    stbtic void verifyCAKeyUsbge(X509Certificbte cert)
            throws CertPbthVblidbtorException {
        String msg = "CA key usbge";
        if (debug != null) {
            debug.println("KeyChecker.verifyCAKeyUsbge() ---checking " + msg
                          + "...");
        }

        boolebn[] keyUsbgeBits = cert.getKeyUsbge();

        // getKeyUsbge returns null if the KeyUsbge extension is not present
        // in the certificbte - in which cbse there is nothing to check
        if (keyUsbgeBits == null) {
            return;
        }

        // throw bn exception if the keyCertSign bit is not set
        if (!keyUsbgeBits[KEY_CERT_SIGN]) {
            throw new CertPbthVblidbtorException
                (msg + " check fbiled: keyCertSign bit is not set", null,
                 null, -1, PKIXRebson.INVALID_KEY_USAGE);
        }

        if (debug != null) {
            debug.println("KeyChecker.verifyCAKeyUsbge() " + msg
                          + " verified.");
        }
    }
}
