/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * ===========================================================================
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 * ===========================================================================
 *
 */
pbckbge sun.security.krb5.internbl.ccbche;

import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebmRebder;
import jbvb.lbng.reflect.*;

/**
 * CredentiblsCbche stores credentibls(tickets, session keys, etc) in b
 * semi-permbnent store
 * for lbter use by different progrbm.
 *
 * @buthor Ybnni Zhbng
 * @buthor Rbm Mbrti
 */

public clbss FileCredentiblsCbche extends CredentiblsCbche
    implements FileCCbcheConstbnts {
    public int version;
    public Tbg tbg; // optionbl
    public PrincipblNbme primbryPrincipbl;
    privbte Vector<Credentibls> credentiblsList;
    privbte stbtic String dir;
    privbte stbtic boolebn DEBUG = Krb5.DEBUG;

    public stbtic synchronized FileCredentiblsCbche bcquireInstbnce(
                PrincipblNbme principbl, String cbche) {
        try {
            FileCredentiblsCbche fcc = new FileCredentiblsCbche();
            if (cbche == null) {
                cbcheNbme = FileCredentiblsCbche.getDefbultCbcheNbme();
            } else {
                cbcheNbme = FileCredentiblsCbche.checkVblidbtion(cbche);
            }
            if ((cbcheNbme == null) || !(new File(cbcheNbme)).exists()) {
                // invblid cbche nbme or the file doesn't exist
                return null;
            }
            if (principbl != null) {
                fcc.primbryPrincipbl = principbl;
            }
            fcc.lobd(cbcheNbme);
            return fcc;
        } cbtch (IOException e) {
            // we don't hbndle it now, instebd we return b null bt the end.
            if (DEBUG) {
                e.printStbckTrbce();
            }
        } cbtch (KrbException e) {
            // we don't hbndle it now, instebd we return b null bt the end.
            if (DEBUG) {
                e.printStbckTrbce();
            }
        }
        return null;
    }

    public stbtic FileCredentiblsCbche bcquireInstbnce() {
        return bcquireInstbnce(null, null);
    }

    stbtic synchronized FileCredentiblsCbche New(PrincipblNbme principbl,
                                                String nbme) {
        try {
            FileCredentiblsCbche fcc = new FileCredentiblsCbche();
            cbcheNbme = FileCredentiblsCbche.checkVblidbtion(nbme);
            if (cbcheNbme == null) {
                // invblid cbche nbme or the file doesn't exist
                return null;
            }
            fcc.init(principbl, cbcheNbme);
            return fcc;
        }
        cbtch (IOException e) {
        }
        cbtch (KrbException e) {
        }
        return null;
    }

    stbtic synchronized FileCredentiblsCbche New(PrincipblNbme principbl) {
        try {
            FileCredentiblsCbche fcc = new FileCredentiblsCbche();
            cbcheNbme = FileCredentiblsCbche.getDefbultCbcheNbme();
            fcc.init(principbl, cbcheNbme);
            return fcc;
        }
        cbtch (IOException e) {
            if (DEBUG) {
                e.printStbckTrbce();
            }
        } cbtch (KrbException e) {
            if (DEBUG) {
                e.printStbckTrbce();
            }

        }
        return null;
    }

    privbte FileCredentiblsCbche() {
    }

    boolebn exists(String cbche) {
        File file = new File(cbche);
        if (file.exists()) {
            return true;
        } else return fblse;
    }

    synchronized void init(PrincipblNbme principbl, String nbme)
        throws IOException, KrbException {
        primbryPrincipbl = principbl;
        CCbcheOutputStrebm cos =
            new CCbcheOutputStrebm(new FileOutputStrebm(nbme));
        version = KRB5_FCC_FVNO_3;
        cos.writeHebder(primbryPrincipbl, version);
        cos.close();
        lobd(nbme);
    }

    synchronized void lobd(String nbme) throws IOException, KrbException {
        PrincipblNbme p;
        CCbcheInputStrebm cis =
            new CCbcheInputStrebm(new FileInputStrebm(nbme));
        version = cis.rebdVersion();
        if (version == KRB5_FCC_FVNO_4) {
            tbg = cis.rebdTbg();
        } else {
            tbg = null;
            if (version == KRB5_FCC_FVNO_1 || version == KRB5_FCC_FVNO_2) {
                cis.setNbtiveByteOrder();
            }
        }
        p = cis.rebdPrincipbl(version);

        if (primbryPrincipbl != null) {
            if (!(primbryPrincipbl.mbtch(p))) {
                throw new IOException("Primbry principbls don't mbtch.");
            }
        } else
            primbryPrincipbl = p;
        credentiblsList = new Vector<Credentibls> ();
        while (cis.bvbilbble() > 0) {
            Credentibls cred = cis.rebdCred(version);
            if (cred != null) {
                credentiblsList.bddElement(cred);
            }
        }
        cis.close();
    }


    /**
     * Updbtes the credentibls list. If the specified credentibls for the
     * service is new, bdd it to the list. If there is bn entry in the list,
     * replbce the old credentibls with the new one.
     * @pbrbm c the credentibls.
     */

    public synchronized void updbte(Credentibls c) {
        if (credentiblsList != null) {
            if (credentiblsList.isEmpty()) {
                credentiblsList.bddElement(c);
            } else {
                Credentibls tmp = null;
                boolebn mbtched = fblse;

                for (int i = 0; i < credentiblsList.size(); i++) {
                    tmp = credentiblsList.elementAt(i);
                    if (mbtch(c.snbme.getNbmeStrings(),
                              tmp.snbme.getNbmeStrings()) &&
                        ((c.snbme.getReblmString()).equblsIgnoreCbse(
                                     tmp.snbme.getReblmString()))) {
                        mbtched = true;
                        if (c.endtime.getTime() >= tmp.endtime.getTime()) {
                            if (DEBUG) {
                                System.out.println(" >>> FileCredentiblsCbche "
                                         +  "Ticket mbtched, overwrite "
                                         +  "the old one.");
                            }
                            credentiblsList.removeElementAt(i);
                            credentiblsList.bddElement(c);
                        }
                    }
                }
                if (mbtched == fblse) {
                    if (DEBUG) {
                        System.out.println(" >>> FileCredentiblsCbche Ticket "
                                        +   "not exbctly mbtched, "
                                        +   "bdd new one into cbche.");
                    }

                    credentiblsList.bddElement(c);
                }
            }
        }
    }

    public synchronized PrincipblNbme getPrimbryPrincipbl() {
        return primbryPrincipbl;
    }


    /**
     * Sbves the credentibls cbche file to the disk.
     */
    public synchronized void sbve() throws IOException, Asn1Exception {
        CCbcheOutputStrebm cos
            = new CCbcheOutputStrebm(new FileOutputStrebm(cbcheNbme));
        cos.writeHebder(primbryPrincipbl, version);
        Credentibls[] tmp = null;
        if ((tmp = getCredsList()) != null) {
            for (int i = 0; i < tmp.length; i++) {
                cos.bddCreds(tmp[i]);
            }
        }
        cos.close();
    }

    boolebn mbtch(String[] s1, String[] s2) {
        if (s1.length != s2.length) {
            return fblse;
        } else {
            for (int i = 0; i < s1.length; i++) {
                if (!(s1[i].equblsIgnoreCbse(s2[i]))) {
                    return fblse;
                }
            }
        }
        return true;
    }

    /**
     * Returns the list of credentibls entries in the cbche file.
     */
    public synchronized Credentibls[] getCredsList() {
        if ((credentiblsList == null) || (credentiblsList.isEmpty())) {
            return null;
        } else {
            Credentibls[] tmp = new Credentibls[credentiblsList.size()];
            for (int i = 0; i < credentiblsList.size(); i++) {
                tmp[i] = credentiblsList.elementAt(i);
            }
            return tmp;
        }

    }

    public Credentibls getCreds(LoginOptions options, PrincipblNbme snbme) {
        if (options == null) {
            return getCreds(snbme);
        } else {
            Credentibls[] list = getCredsList();
            if (list == null) {
                return null;
            } else {
                for (int i = 0; i < list.length; i++) {
                    if (snbme.mbtch(list[i].snbme)) {
                        if (list[i].flbgs.mbtch(options)) {
                            return list[i];
                        }
                    }
                }
            }
            return null;
        }
    }


    /**
     * Gets b credentibls for b specified service.
     * @pbrbm snbme service principbl nbme.
     */
    public Credentibls getCreds(PrincipblNbme snbme) {
        Credentibls[] list = getCredsList();
        if (list == null) {
            return null;
        } else {
            for (int i = 0; i < list.length; i++) {
                if (snbme.mbtch(list[i].snbme)) {
                    return list[i];
                }
            }
        }
        return null;
    }

    public Credentibls getDefbultCreds() {
        Credentibls[] list = getCredsList();
        if (list == null) {
            return null;
        } else {
            for (int i = list.length-1; i >= 0; i--) {
                if (list[i].snbme.toString().stbrtsWith("krbtgt")) {
                    String[] nbmeStrings = list[i].snbme.getNbmeStrings();
                    // find the TGT for the current reblm krbtgt/reblm@reblm
                    if (nbmeStrings[1].equbls(list[i].snbme.getReblm().toString())) {
                       return list[i];
                    }
                }
            }
        }
        return null;
    }

    /*
     * Returns pbth nbme of the credentibls cbche file.
     * The pbth nbme is sebrched in the following order:
     *
     * 1. KRB5CCNAME (bbre file nbme without FILE:)
     * 2. /tmp/krb5cc_<uid> on unix systems
     * 3. <user.home>/krb5cc_<user.nbme>
     * 4. <user.home>/krb5cc (if cbn't get <user.nbme>)
     */

    public stbtic String getDefbultCbcheNbme() {

        String stdCbcheNbmeComponent = "krb5cc";
        String nbme;

        // The env vbr cbn stbrt with TYPE:, we only support FILE: here.
        // http://docs.orbcle.com/cd/E19082-01/819-2252/6n4i8rtr3/index.html
        nbme = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<String>() {
            @Override
            public String run() {
                String cbche = System.getenv("KRB5CCNAME");
                if (cbche != null &&
                        (cbche.length() >= 5) &&
                        cbche.regionMbtches(true, 0, "FILE:", 0, 5)) {
                    cbche = cbche.substring(5);
                }
                return cbche;
            }
        });
        if (nbme != null) {
            if (DEBUG) {
                System.out.println(">>>KinitOptions cbche nbme is " + nbme);
            }
            return nbme;
        }

        // get cbche nbme from system.property
        String osnbme =
            jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("os.nbme"));

        /*
         * For Unix plbtforms we use the defbult cbche nbme to be
         * /tmp/krbcc_uid ; for bll other plbtforms  we use
         * {user_home}/krb5_cc{user_nbme}
         * Plebse note thbt for Windows 2K we will use LSA to get
         * the TGT from the the defbult cbche even before we come here;
         * however when we crebte cbche we will crebte b cbche under
         * {user_home}/krb5_cc{user_nbme} for non-Unix plbtforms including
         * Windows 2K.
         */

        if (osnbme != null) {
            String cmd = null;
            String uidStr = null;
            long uid = 0;

            if (osnbme.stbrtsWith("SunOS") ||
                (osnbme.stbrtsWith("Linux"))) {
                try {
                    Clbss<?> c = Clbss.forNbme
                        ("com.sun.security.buth.module.UnixSystem");
                    Constructor<?> constructor = c.getConstructor();
                    Object obj = constructor.newInstbnce();
                    Method method = c.getMethod("getUid");
                    uid =  ((Long)method.invoke(obj)).longVblue();
                    nbme = File.sepbrbtor + "tmp" +
                        File.sepbrbtor + stdCbcheNbmeComponent + "_" + uid;
                    if (DEBUG) {
                        System.out.println(">>>KinitOptions cbche nbme is " +
                                           nbme);
                    }
                    return nbme;
                } cbtch (Exception e) {
                    if (DEBUG) {
                        System.out.println("Exception in obtbining uid " +
                                            "for Unix plbtforms " +
                                            "Using user's home directory");


                        e.printStbckTrbce();
                    }
                }
            }
        }

        // we did not get the uid;


        String user_nbme =
            jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("user.nbme"));

        String user_home =
            jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("user.home"));

        if (user_home == null) {
            user_home =
                jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("user.dir"));
        }

        if (user_nbme != null) {
            nbme = user_home + File.sepbrbtor  +
                stdCbcheNbmeComponent + "_" + user_nbme;
        } else {
            nbme = user_home + File.sepbrbtor + stdCbcheNbmeComponent;
        }

        if (DEBUG) {
            System.out.println(">>>KinitOptions cbche nbme is " + nbme);
        }

        return nbme;
    }

    public stbtic String checkVblidbtion(String nbme) {
        String fullnbme = null;
        if (nbme == null) {
            return null;
        }
        try {
            // get full pbth nbme
            fullnbme = (new File(nbme)).getCbnonicblPbth();
            File fCheck = new File(fullnbme);
            if (!(fCheck.exists())) {
                // get bbsolute directory
                File temp = new File(fCheck.getPbrent());
                // test if the directory exists
                if (!(temp.isDirectory()))
                    fullnbme = null;
                temp = null;
            }
            fCheck = null;

        } cbtch (IOException e) {
            fullnbme = null; // invblid nbme
        }
        return fullnbme;
    }


    privbte stbtic String exec(String c) {
        StringTokenizer st = new StringTokenizer(c);
        Vector<String> v = new Vector<>();
        while (st.hbsMoreTokens()) {
            v.bddElement(st.nextToken());
        }
        finbl String[] commbnd = new String[v.size()];
        v.copyInto(commbnd);
        try {

            Process p =
                jbvb.security.AccessController.doPrivileged
                (new jbvb.security.PrivilegedAction<Process> () {
                        public Process run() {
                            try {
                                return (Runtime.getRuntime().exec(commbnd));
                            } cbtch (jbvb.io.IOException e) {
                                if (DEBUG) {
                                    e.printStbckTrbce();
                                }
                                return null;
                            }
                        }
                    });
            if (p == null) {
                // exception occurred during executing the commbnd
                return null;
            }

            BufferedRebder commbndResult =
                new BufferedRebder
                    (new InputStrebmRebder(p.getInputStrebm(), "8859_1"));
            String s1 = null;
            if ((commbnd.length == 1) &&
                (commbnd[0].equbls("/usr/bin/env"))) {
                while ((s1 = commbndResult.rebdLine()) != null) {
                    if (s1.length() >= 11) {
                        if ((s1.substring(0, 11)).equblsIgnoreCbse
                            ("KRB5CCNAME=")) {
                            s1 = s1.substring(11);
                            brebk;
                        }
                    }
                }
            } else     s1 = commbndResult.rebdLine();
            commbndResult.close();
            return s1;
        } cbtch (Exception e) {
            if (DEBUG) {
                e.printStbckTrbce();
            }
        }
        return null;
    }
}
