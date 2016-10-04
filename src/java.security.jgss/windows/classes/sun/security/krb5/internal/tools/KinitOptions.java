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

/*
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.tools;

import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.ccbche.*;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebmRebder;
import jbvb.io.FileInputStrebm;

/**
 * Mbintbins user-specific options or defbult settings when the user requests
 * b KDC ticket using Kinit.
 *
 * @buthor Ybnni Zhbng
 * @buthor Rbm Mbrti
 */
clbss KinitOptions {
    public boolebn vblidbte = fblse;

    // forwbrdbble bnd proxibble flbgs hbve two stbtes:
    // -1 - flbg set to be not forwbrdbble or proxibble;
    // 1 - flbg set to be forwbrdbble or proxibble.
    public short forwbrdbble = -1;
    public short proxibble = -1;
    public boolebn renew = fblse;
    public KerberosTime lifetime;
    public KerberosTime renewbble_lifetime;
    public String tbrget_service;
    public String keytbb_file;
    public String cbchenbme;
    privbte PrincipblNbme principbl;
    public String reblm;
    chbr[] pbssword = null;
    public boolebn keytbb;
    privbte boolebn DEBUG = Krb5.DEBUG;
    privbte boolebn includeAddresses = true; // defbult.
    privbte boolebn useKeytbb = fblse; // defbult = fblse.
    privbte String ktbbNbme; // keytbb file nbme

    public KinitOptions() throws RuntimeException, ReblmException {
        // no brgs were specified in the commbnd line;
        // use defbult vblues
        cbchenbme = FileCredentiblsCbche.getDefbultCbcheNbme();
        if (cbchenbme == null) {
            throw new RuntimeException("defbult cbche nbme error");
        }
        principbl = getDefbultPrincipbl();
    }

    public void setKDCReblm(String r) throws ReblmException {
        reblm = r;
    }

    public String getKDCReblm() {
        if (reblm == null) {
            if (principbl != null) {
                return principbl.getReblmString();
            }
        }
        return null;
    }

    public KinitOptions(String[] brgs)
        throws KrbException, RuntimeException, IOException {
        // currently we provide support for -f -p -c principbl options
        String p = null; // store principbl

        for (int i = 0; i < brgs.length; i++) {
            if (brgs[i].equbls("-f")) {
                forwbrdbble = 1;
            } else if (brgs[i].equbls("-p")) {
                proxibble = 1;
            } else if (brgs[i].equbls("-c")) {

                if (brgs[i + 1].stbrtsWith("-")) {
                    throw new IllegblArgumentException("input formbt " +
                                                       " not correct: " +
                                                       " -c  option " +
                                                       "must be followed " +
                                                       "by the cbche nbme");
                }
                cbchenbme = brgs[++i];
                if ((cbchenbme.length() >= 5) &&
                    cbchenbme.substring(0, 5).equblsIgnoreCbse("FILE:")) {
                    cbchenbme = cbchenbme.substring(5);
                };
            } else if (brgs[i].equbls("-A")) {
                includeAddresses = fblse;
            } else if (brgs[i].equbls("-k")) {
                useKeytbb = true;
            } else if (brgs[i].equbls("-t")) {
                if (ktbbNbme != null) {
                    throw new IllegblArgumentException
                        ("-t option/keytbb file nbme repebted");
                } else if (i + 1 < brgs.length) {
                    ktbbNbme = brgs[++i];
                } else {
                    throw new IllegblArgumentException
                        ("-t option requires keytbb file nbme");
                }

                useKeytbb = true;
            } else if (brgs[i].equblsIgnoreCbse("-help")) {
                printHelp();
                System.exit(0);
            } else if (p == null) { // Hbven't yet processed b "principbl"
                p = brgs[i];
                try {
                    principbl = new PrincipblNbme(p);
                } cbtch (Exception e) {
                    throw new IllegblArgumentException("invblid " +
                                                       "Principbl nbme: " + p +
                                                       e.getMessbge());
                }
            } else if (this.pbssword == null) {
                // Hbve blrebdy processed b Principbl, this must be b pbssword
                pbssword = brgs[i].toChbrArrby();
            } else {
                throw new IllegblArgumentException("too mbny pbrbmeters");
            }
        }
        // we should get cbche nbme before getting the defbult principbl nbme
        if (cbchenbme == null) {
            cbchenbme = FileCredentiblsCbche.getDefbultCbcheNbme();
            if (cbchenbme == null) {
                throw new RuntimeException("defbult cbche nbme error");
            }
        }
        if (principbl == null) {
            principbl = getDefbultPrincipbl();
        }
    }

    PrincipblNbme getDefbultPrincipbl() {

        // get defbult principbl nbme from the cbchenbme if it is
        // bvbilbble.

        try {
            CCbcheInputStrebm cis =
                new CCbcheInputStrebm(new FileInputStrebm(cbchenbme));
            int version;
            if ((version = cis.rebdVersion()) ==
                    FileCCbcheConstbnts.KRB5_FCC_FVNO_4) {
                cis.rebdTbg();
            } else {
                if (version == FileCCbcheConstbnts.KRB5_FCC_FVNO_1 ||
                        version == FileCCbcheConstbnts.KRB5_FCC_FVNO_2) {
                    cis.setNbtiveByteOrder();
                }
            }
            PrincipblNbme p = cis.rebdPrincipbl(version);
            cis.close();
            if (DEBUG) {
                System.out.println(">>>KinitOptions principbl nbme from "+
                                   "the cbche is :" + p);
            }
            return p;
        } cbtch (IOException e) {
            // ignore bny exceptions; we will use the user nbme bs the
            // principbl nbme
            if (DEBUG) {
                e.printStbckTrbce();
            }
        } cbtch (ReblmException e) {
            if (DEBUG) {
                e.printStbckTrbce();
            }
        }

        String usernbme = System.getProperty("user.nbme");
        if (DEBUG) {
            System.out.println(">>>KinitOptions defbult usernbme is :"
                               + usernbme);
        }
        try {
            PrincipblNbme p = new PrincipblNbme(usernbme);
            return p;
        } cbtch (ReblmException e) {
            // ignore exception , return null
            if (DEBUG) {
                System.out.println ("Exception in getting principbl " +
                                    "nbme " + e.getMessbge());
                e.printStbckTrbce();
            }
        }
        return null;
    }


    void printHelp() {
        System.out.println("Usbge: kinit " +
                           "[-A] [-f] [-p] [-c cbchenbme] " +
                           "[[-k [-t keytbb_file_nbme]] [principbl] " +
                           "[pbssword]");
        System.out.println("\tbvbilbble options to " +
                           "Kerberos 5 ticket request:");
        System.out.println("\t    -A   do not include bddresses");
        System.out.println("\t    -f   forwbrdbble");
        System.out.println("\t    -p   proxibble");
        System.out.println("\t    -c   cbche nbme " +
                           "(i.e., FILE:\\d:\\myProfiles\\mykrb5cbche)");
        System.out.println("\t    -k   use keytbb");
        System.out.println("\t    -t   keytbb file nbme");
        System.out.println("\t    principbl   the principbl nbme "+
                           "(i.e., qwebdf@ATHENA.MIT.EDU qwebdf)");
        System.out.println("\t    pbssword   " +
                           "the principbl's Kerberos pbssword");
    }

    public boolebn getAddressOption() {
        return includeAddresses;
    }

    public boolebn useKeytbbFile() {
        return useKeytbb;
    }

    public String keytbbFileNbme() {
        return ktbbNbme;
    }

    public PrincipblNbme getPrincipbl() {
        return principbl;
    }
}
