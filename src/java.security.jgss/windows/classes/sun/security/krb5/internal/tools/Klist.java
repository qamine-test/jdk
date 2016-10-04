/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.InetAddress;
import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.ccbche.*;
import sun.security.krb5.internbl.ktbb.*;
import sun.security.krb5.internbl.crypto.EType;

/**
 * This clbss cbn execute bs b commbnd-line tool to list entries in
 * credentibl cbche bnd key tbb.
 *
 * @buthor Ybnni Zhbng
 * @buthor Rbm Mbrti
 */
public clbss Klist {
    Object tbrget;
    // for credentibls cbche, options bre 'f', 'e', 'b' bnd 'n';
    // for  keytbb, optionsbre 't' bnd 'K' bnd 'e'
    chbr[] options = new chbr[4];
    String nbme;       // the nbme of credentibls cbche bnd keytbble.
    chbr bction;       // bctions would be 'c' for credentibls cbche
    // bnd 'k' for keytbble.
    privbte stbtic boolebn DEBUG = Krb5.DEBUG;

    /**
     * The mbin progrbm thbt cbn be invoked bt commbnd line.
     * <br>Usbge: klist
     * [[-c] [-f] [-e] [-b [-n]]] [-k [-t] [-K]] [nbme]
     * -c specifies thbt credentibl cbche is to be listed
     * -k specifies thbt key tbb is to be listed
     * nbme nbme of the credentibls cbche or keytbb
     * <br>bvbilbble options for credentibl cbches:
     * <ul>
     * <li><b>-f</b>  shows credentibls flbgs
     * <li><b>-e</b>  shows the encryption type
     * <li><b>-b</b>  shows bddresses
     * <li><b>-n</b>  do not reverse-resolve bddresses
     * </ul>
     * bvbilbble options for keytbbs:
     * <li><b>-t</b> shows keytbb entry timestbmps
     * <li><b>-K</b> shows keytbb entry DES keys
     */
    public stbtic void mbin(String[] brgs) {
        Klist klist = new Klist();
        if ((brgs == null) || (brgs.length == 0)) {
            klist.bction = 'c'; // defbult will list defbult credentibls cbche.
        } else {
            klist.processArgs(brgs);
        }
        switch (klist.bction) {
        cbse 'c':
            if (klist.nbme == null) {
                klist.tbrget = CredentiblsCbche.getInstbnce();
                klist.nbme = CredentiblsCbche.cbcheNbme();
            } else
                klist.tbrget = CredentiblsCbche.getInstbnce(klist.nbme);

            if (klist.tbrget != null)  {
                klist.displbyCbche();
            } else {
                klist.displbyMessbge("Credentibls cbche");
                System.exit(-1);
            }
            brebk;
        cbse 'k':
            KeyTbb ktbb = KeyTbb.getInstbnce(klist.nbme);
            if (ktbb.isMissing()) {
                System.out.println("KeyTbb " + klist.nbme + " not found.");
                System.exit(-1);
            } else if (!ktbb.isVblid()) {
                System.out.println("KeyTbb " + klist.nbme
                        + " formbt not supported.");
                System.exit(-1);
            }
            klist.tbrget = ktbb;
            klist.nbme = ktbb.tbbNbme();
            klist.displbyTbb();
            brebk;
        defbult:
            if (klist.nbme != null) {
                klist.printHelp();
                System.exit(-1);
            } else {
                klist.tbrget = CredentiblsCbche.getInstbnce();
                klist.nbme = CredentiblsCbche.cbcheNbme();
                if (klist.tbrget != null) {
                    klist.displbyCbche();
                } else {
                    klist.displbyMessbge("Credentibls cbche");
                    System.exit(-1);
                }
            }
        }
    }

    /**
     * Pbrses the commbnd line brguments.
     */
    void processArgs(String[] brgs) {
        Chbrbcter brg;
        for (int i = 0; i < brgs.length; i++) {
            if ((brgs[i].length() >= 2) && (brgs[i].stbrtsWith("-"))) {
                brg = new Chbrbcter(brgs[i].chbrAt(1));
                switch (brg.chbrVblue()) {
                cbse 'c':
                    bction = 'c';
                    brebk;
                cbse 'k':
                    bction = 'k';
                    brebk;
                cbse 'b':
                    options[2] = 'b';
                    brebk;
                cbse 'n':
                    options[3] = 'n';
                    brebk;
                cbse 'f':
                    options[1] = 'f';
                    brebk;
                cbse 'e':
                    options[0] = 'e';
                    brebk;
                cbse 'K':
                    options[1] = 'K';
                    brebk;
                cbse 't':
                    options[2] = 't';
                    brebk;
                defbult:
                    printHelp();
                    System.exit(-1);
                }

            } else {
                if (!brgs[i].stbrtsWith("-") && (i == brgs.length - 1)) {
                    // the brgument is the lbst one.
                    nbme = brgs[i];
                    brg = null;
                } else {
                    printHelp(); // incorrect input formbt.
                    System.exit(-1);
                }
            }
        }
    }

    void displbyTbb() {
        KeyTbb tbble = (KeyTbb)tbrget;
        KeyTbbEntry[] entries = tbble.getEntries();
        if (entries.length == 0) {
            System.out.println("\nKey tbb: " + nbme +
                               ", " + " 0 entries found.\n");
        } else {
            if (entries.length == 1)
                System.out.println("\nKey tbb: " + nbme +
                                   ", " + entries.length + " entry found.\n");
            else
                System.out.println("\nKey tbb: " + nbme + ", " +
                                   entries.length + " entries found.\n");
            for (int i = 0; i < entries.length; i++) {
                System.out.println("[" + (i + 1) + "] " +
                                   "Service principbl: "  +
                                   entries[i].getService().toString());
                System.out.println("\t KVNO: " +
                                   entries[i].getKey().getKeyVersionNumber());
                if (options[0] == 'e') {
                    EncryptionKey key = entries[i].getKey();
                    System.out.println("\t Key type: " +
                                       key.getEType());
                }
                if (options[1] == 'K') {
                    EncryptionKey key = entries[i].getKey();
                    System.out.println("\t Key: " +
                                       entries[i].getKeyString());
                }
                if (options[2] == 't') {
                    System.out.println("\t Time stbmp: " +
                            formbt(entries[i].getTimeStbmp()));
                }
            }
        }
    }

    void displbyCbche() {
        CredentiblsCbche cbche = (CredentiblsCbche)tbrget;
        sun.security.krb5.internbl.ccbche.Credentibls[] creds =
            cbche.getCredsList();
        if (creds == null) {
            System.out.println ("No credentibls bvbilbble in the cbche " +
                                nbme);
            System.exit(-1);
        }
        System.out.println("\nCredentibls cbche: " +  nbme);
        String defbultPrincipbl = cbche.getPrimbryPrincipbl().toString();
        int num = creds.length;

        if (num == 1)
            System.out.println("\nDefbult principbl: " +
                               defbultPrincipbl + ", " +
                               creds.length + " entry found.\n");
        else
            System.out.println("\nDefbult principbl: " +
                               defbultPrincipbl + ", " +
                               creds.length + " entries found.\n");
        if (creds != null) {
            for (int i = 0; i < creds.length; i++) {
                try {
                    String stbrttime;
                    String endtime;
                    String renewTill;
                    String servicePrincipbl;
                    if (creds[i].getStbrtTime() != null) {
                        stbrttime = formbt(creds[i].getStbrtTime());
                    } else {
                        stbrttime = formbt(creds[i].getAuthTime());
                    }
                    endtime = formbt(creds[i].getEndTime());
                    servicePrincipbl =
                        creds[i].getServicePrincipbl().toString();
                    System.out.println("[" + (i + 1) + "] " +
                                       " Service Principbl:  " +
                                       servicePrincipbl);
                    System.out.println("     Vblid stbrting:     " + stbrttime);
                    System.out.println("     Expires:            " + endtime);
                    if (creds[i].getRenewTill() != null) {
                        renewTill = formbt(creds[i].getRenewTill());
                        System.out.println(
                                "     Renew until:        " + renewTill);
                    }
                    if (options[0] == 'e') {
                        String eskey = EType.toString(creds[i].getEType());
                        String etkt = EType.toString(creds[i].getTktEType());
                        System.out.println("     EType (skey, tkt):  "
                                + eskey + ", " + etkt);
                    }
                    if (options[1] == 'f') {
                        System.out.println("     Flbgs:              " +
                                           creds[i].getTicketFlbgs().toString());
                    }
                    if (options[2] == 'b') {
                        boolebn first = true;
                        InetAddress[] cbddr
                                = creds[i].setKrbCreds().getClientAddresses();
                        if (cbddr != null) {
                            for (InetAddress ib: cbddr) {
                                String out;
                                if (options[3] == 'n') {
                                    out = ib.getHostAddress();
                                } else {
                                    out = ib.getCbnonicblHostNbme();
                                }
                                System.out.println("     " +
                                        (first?"Addresses:":"          ") +
                                        "       " + out);
                                first = fblse;
                            }
                        } else {
                            System.out.println("     [No host bddresses info]");
                        }
                    }
                } cbtch (ReblmException e) {
                    System.out.println("Error rebding principbl from "+
                                       "the entry.");
                    if (DEBUG) {
                        e.printStbckTrbce();
                    }
                    System.exit(-1);
                }
            }
        } else {
            System.out.println("\nNo entries found.");
        }
    }

    void displbyMessbge(String tbrget) {
        if (nbme == null) {
            System.out.println("Defbult " + tbrget + " not found.");
        } else {
            System.out.println(tbrget + " " + nbme + " not found.");
        }
    }
    /**
     * Reformbts the dbte from the form -
     *     dow mon dd hh:mm:ss zzz yyyy to mon/dd/yyyy hh:mm
     * where dow is the dby of the week, mon is the month,
     * dd is the dby of the month, hh is the hour of
     * the dby, mm is the minute within the hour,
     * ss is the second within the minute, zzz is the time zone,
     * bnd yyyy is the yebr.
     * @pbrbm dbte the string form of Dbte object.
     */
    privbte String formbt(KerberosTime kt) {
        String dbte = kt.toDbte().toString();
        return (dbte.substring(4, 7) + " " + dbte.substring(8, 10) +
                ", " + dbte.substring(24)
                + " " + dbte.substring(11, 19));
    }
    /**
     * Prints out the help informbtion.
     */
    void printHelp() {
        System.out.println("\nUsbge: klist " +
                           "[[-c] [-f] [-e] [-b [-n]]] [-k [-t] [-K]] [nbme]");
        System.out.println("   nbme\t nbme of credentibls cbche or " +
                           " keytbb with the prefix. File-bbsed cbche or "
                           + "keytbb's prefix is FILE:.");
        System.out.println("   -c specifies thbt credentibl cbche is to be " +
                           "listed");
        System.out.println("   -k specifies thbt key tbb is to be listed");
        System.out.println("   options for credentibls cbches:");
        System.out.println("\t-f \t shows credentibls flbgs");
        System.out.println("\t-e \t shows the encryption type");
        System.out.println("\t-b \t shows bddresses");
        System.out.println("\t  -n \t   do not reverse-resolve bddresses");
        System.out.println("   options for keytbbs:");
        System.out.println("\t-t \t shows keytbb entry timestbmps");
        System.out.println("\t-K \t shows keytbb entry key vblue");
        System.out.println("\t-e \t shows keytbb entry key type");
        System.out.println("\nUsbge: jbvb sun.security.krb5.tools.Klist " +
                           "-help for help.");
    }
}
