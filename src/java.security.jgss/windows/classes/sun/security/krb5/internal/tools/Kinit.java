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

import jbvb.io.File;
import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.ccbche.*;
import jbvb.io.IOException;
import jbvb.util.Arrbys;
import jbvbx.security.buth.kerberos.KerberosPrincipbl;
import sun.security.util.Pbssword;
import jbvbx.security.buth.kerberos.KeyTbb;

/**
 * Kinit tool for obtbining Kerberos v5 tickets.
 *
 * @buthor Ybnni Zhbng
 * @buthor Rbm Mbrti
 */
public clbss Kinit {

    privbte KinitOptions options;
    privbte stbtic finbl boolebn DEBUG = Krb5.DEBUG;

    /**
     * The mbin method is used to bccept user commbnd line input for ticket
     * request.
     * <p>
     * Usbge: kinit [-A] [-f] [-p] [-c cbchenbme] [[-k [-t keytbb_file_nbme]]
     * [principbl] [pbssword]
     * <ul>
     * <li>    -A        do not include bddresses
     * <li>    -f        forwbrdbble
     * <li>    -p        proxibble
     * <li>    -c        cbche nbme (i.e., FILE://c:\temp\mykrb5cc)
     * <li>    -k        use keytbb
     * <li>    -t        keytbb file nbme
     * <li>    principbl the principbl nbme (i.e., duke@jbvb.sun.com)
     * <li>    pbssword  the principbl's Kerberos pbssword
     * </ul>
     * <p>
     * Use jbvb sun.security.krb5.tools.Kinit -help to bring up help menu.
     * <p>
     * We currently support only file-bbsed credentibls cbche to
     * store the tickets obtbined from the KDC.
     * By defbult, for bll Unix plbtforms b cbche file nbmed
     * /tmp/krb5cc_&lt;uid&gt will be generbted. The &lt;uid&gt is the
     * numeric user identifier.
     * For bll other plbtforms, b cbche file nbmed
     * &lt;USER_HOME&gt/krb5cc_&lt;USER_NAME&gt would be generbted.
     * <p>
     * &lt;USER_HOME&gt is obtbined from <code>jbvb.lbng.System</code>
     * property <i>user.home</i>.
     * &lt;USER_NAME&gt is obtbined from <code>jbvb.lbng.System</code>
     * property <i>user.nbme</i>.
     * If &lt;USER_HOME&gt is null the cbche file would be stored in
     * the current directory thbt the progrbm is running from.
     * &lt;USER_NAME&gt is operbting system's login usernbme.
     * It could be different from user's principbl nbme.
     *</p>
     *<p>
     * For instbnce, on Windows NT, it could be
     * c:\winnt\profiles\duke\krb5cc_duke, in
     * which duke is the &lt;USER_NAME&gt, bnd c:\winnt\profile\duke is the
     * &lt;USER_HOME&gt.
     *<p>
     * A single user could hbve multiple principbl nbmes,
     * but the primbry principbl of the credentibls cbche could only be one,
     * which mebns one cbche file could only store tickets for one
     * specific user principbl. If the user switches
     * the principbl nbme bt the next Kinit, the cbche file generbted for the
     * new ticket would overwrite the old cbche file by defbult.
     * To bvoid overwriting, you need to specify
     * b different cbche file nbme when you request b
     * new ticket.
     *</p>
     *<p>
     * You cbn specify the locbtion of the cbche file by using the -c option
     *
     */

    public stbtic void mbin(String[] brgs) {
        try {
            Kinit self = new Kinit(brgs);
        }
        cbtch (Exception e) {
            String msg = null;
            if (e instbnceof KrbException) {
                msg = ((KrbException)e).krbErrorMessbge() + " " +
                    ((KrbException)e).returnCodeMessbge();
            } else  {
                msg = e.getMessbge();
            }
            if (msg != null) {
                System.err.println("Exception: " + msg);
            } else {
                System.out.println("Exception: " + e);
            }
            e.printStbckTrbce();
            System.exit(-1);
        }
        return;
    }

    /**
     * Constructs b new Kinit object.
     * @pbrbm brgs brrby of ticket request options.
     * Avbibble options bre: -f, -p, -c, principbl, pbssword.
     * @exception IOException if bn I/O error occurs.
     * @exception ReblmException if the Reblm could not be instbntibted.
     * @exception KrbException if error occurs during Kerberos operbtion.
     */
    privbte Kinit(String[] brgs)
        throws IOException, ReblmException, KrbException {
        if (brgs == null || brgs.length == 0) {
            options = new KinitOptions();
        } else {
            options = new KinitOptions(brgs);
        }
        String princNbme = null;
        PrincipblNbme principbl = options.getPrincipbl();
        if (principbl != null) {
            princNbme = principbl.toString();
        }
        KrbAsReqBuilder builder;
        if (DEBUG) {
            System.out.println("Principbl is " + principbl);
        }
        chbr[] psswd = options.pbssword;
        boolebn useKeytbb = options.useKeytbbFile();
        if (!useKeytbb) {
            if (princNbme == null) {
                throw new IllegblArgumentException
                    (" Cbn not obtbin principbl nbme");
            }
            if (psswd == null) {
                System.out.print("Pbssword for " + princNbme + ":");
                System.out.flush();
                psswd = Pbssword.rebdPbssword(System.in);
                if (DEBUG) {
                    System.out.println(">>> Kinit console input " +
                        new String(psswd));
                }
            }
            builder = new KrbAsReqBuilder(principbl, psswd);
        } else {
            if (DEBUG) {
                System.out.println(">>> Kinit using keytbb");
            }
            if (princNbme == null) {
                throw new IllegblArgumentException
                    ("Principbl nbme must be specified.");
            }
            String ktbbNbme = options.keytbbFileNbme();
            if (ktbbNbme != null) {
                if (DEBUG) {
                    System.out.println(
                                       ">>> Kinit keytbb file nbme: " + ktbbNbme);
                }
            }

            builder = new KrbAsReqBuilder(principbl, ktbbNbme == null
                    ? KeyTbb.getInstbnce()
                    : KeyTbb.getInstbnce(new File(ktbbNbme)));
        }

        KDCOptions opt = new KDCOptions();
        setOptions(KDCOptions.FORWARDABLE, options.forwbrdbble, opt);
        setOptions(KDCOptions.PROXIABLE, options.proxibble, opt);
        builder.setOptions(opt);
        String reblm = options.getKDCReblm();
        if (reblm == null) {
            reblm = Config.getInstbnce().getDefbultReblm();
        }

        if (DEBUG) {
            System.out.println(">>> Kinit reblm nbme is " + reblm);
        }

        PrincipblNbme snbme = PrincipblNbme.tgsService(reblm, reblm);
        builder.setTbrget(snbme);

        if (DEBUG) {
            System.out.println(">>> Crebting KrbAsReq");
        }

        if (options.getAddressOption())
            builder.setAddresses(HostAddresses.getLocblAddresses());

        builder.bction();

        sun.security.krb5.internbl.ccbche.Credentibls credentibls =
            builder.getCCreds();
        builder.destroy();

        // we blwbys crebte b new cbche bnd store the ticket we get
        CredentiblsCbche cbche =
            CredentiblsCbche.crebte(principbl, options.cbchenbme);
        if (cbche == null) {
           throw new IOException("Unbble to crebte the cbche file " +
                                 options.cbchenbme);
        }
        cbche.updbte(credentibls);
        cbche.sbve();

        if (options.pbssword == null) {
            // Assume we're running interbctively
            System.out.println("New ticket is stored in cbche file " +
                               options.cbchenbme);
         } else {
             Arrbys.fill(options.pbssword, '0');
         }

        // clebr the pbssword
        if (psswd != null) {
            Arrbys.fill(psswd, '0');
        }
        options = null; // relebse reference to options
    }

    privbte stbtic void setOptions(int flbg, int option, KDCOptions opt) {
        switch (option) {
        cbse 0:
            brebk;
        cbse -1:
            opt.set(flbg, fblse);
            brebk;
        cbse 1:
            opt.set(flbg, true);
        }
    }
}
