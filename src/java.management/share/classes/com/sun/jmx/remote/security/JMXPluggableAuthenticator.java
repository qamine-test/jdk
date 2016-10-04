/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.security;

import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Properties;
import jbvbx.mbnbgement.remote.JMXPrincipbl;
import jbvbx.mbnbgement.remote.JMXAuthenticbtor;
import jbvbx.security.buth.AuthPermission;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.cbllbbck.*;
import jbvbx.security.buth.login.AppConfigurbtionEntry;
import jbvbx.security.buth.login.Configurbtion;
import jbvbx.security.buth.login.LoginContext;
import jbvbx.security.buth.login.LoginException;
import jbvbx.security.buth.spi.LoginModule;
import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;

/**
 * <p>This clbss represents b
 * <b href="{@docRoot}/../guide/security/jbbs/JAASRefGuide.html">JAAS</b>
 * bbsed implementbtion of the {@link JMXAuthenticbtor} interfbce.</p>
 *
 * <p>Authenticbtion is performed by pbssing the supplied user's credentibls
 * to one or more buthenticbtion mechbnisms ({@link LoginModule}) for
 * verificbtion. An buthenticbtion mechbnism bcquires the user's credentibls
 * by cblling {@link NbmeCbllbbck} bnd/or {@link PbsswordCbllbbck}.
 * If buthenticbtion is successful then bn buthenticbted {@link Subject}
 * filled in with b {@link Principbl} is returned.  Authorizbtion checks
 * will then be performed bbsed on this <code>Subject</code>.</p>
 *
 * <p>By defbult, b single file-bbsed buthenticbtion mechbnism
 * {@link FileLoginModule} is configured (<code>FileLoginConfig</code>).</p>
 *
 * <p>To override the defbult configurbtion use the
 * <code>com.sun.mbnbgement.jmxremote.login.config</code> mbnbgement property
 * described in the JRE/lib/mbnbgement/mbnbgement.properties file.
 * Set this property to the nbme of b JAAS configurbtion entry bnd ensure thbt
 * the entry is lobded by the instblled {@link Configurbtion}. In bddition,
 * ensure thbt the buthenticbtion mechbnisms specified in the entry bcquire
 * the user's credentibls by cblling {@link NbmeCbllbbck} bnd
 * {@link PbsswordCbllbbck} bnd thbt they return b {@link Subject} filled-in
 * with b {@link Principbl}, for those users thbt bre successfully
 * buthenticbted.</p>
 */
public finbl clbss JMXPluggbbleAuthenticbtor implements JMXAuthenticbtor {

    /**
     * Crebtes bn instbnce of <code>JMXPluggbbleAuthenticbtor</code>
     * bnd initiblizes it with b {@link LoginContext}.
     *
     * @pbrbm env the environment contbining configurbtion properties for the
     *            buthenticbtor. Cbn be null, which is equivblent to bn empty
     *            Mbp.
     * @exception SecurityException if the buthenticbtion mechbnism cbnnot be
     *            initiblized.
     */
    public JMXPluggbbleAuthenticbtor(Mbp<?, ?> env) {

        String loginConfigNbme = null;
        String pbsswordFile = null;

        if (env != null) {
            loginConfigNbme = (String) env.get(LOGIN_CONFIG_PROP);
            pbsswordFile = (String) env.get(PASSWORD_FILE_PROP);
        }

        try {

            if (loginConfigNbme != null) {
                // use the supplied JAAS login configurbtion
                loginContext =
                    new LoginContext(loginConfigNbme, new JMXCbllbbckHbndler());

            } else {
                // use the defbult JAAS login configurbtion (file-bbsed)
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    sm.checkPermission(
                            new AuthPermission("crebteLoginContext." +
                                               LOGIN_CONFIG_NAME));
                }

                finbl String pf = pbsswordFile;
                try {
                    loginContext = AccessController.doPrivileged(
                        new PrivilegedExceptionAction<LoginContext>() {
                            public LoginContext run() throws LoginException {
                                return new LoginContext(
                                                LOGIN_CONFIG_NAME,
                                                null,
                                                new JMXCbllbbckHbndler(),
                                                new FileLoginConfig(pf));
                            }
                        });
                } cbtch (PrivilegedActionException pbe) {
                    throw (LoginException) pbe.getException();
                }
            }

        } cbtch (LoginException le) {
            buthenticbtionFbilure("buthenticbte", le);

        } cbtch (SecurityException se) {
            buthenticbtionFbilure("buthenticbte", se);
        }
    }

    /**
     * Authenticbte the <code>MBebnServerConnection</code> client
     * with the given client credentibls.
     *
     * @pbrbm credentibls the user-defined credentibls to be pbssed in
     * to the server in order to buthenticbte the user before crebting
     * the <code>MBebnServerConnection</code>.  This pbrbmeter must
     * be b two-element <code>String[]</code> contbining the client's
     * usernbme bnd pbssword in thbt order.
     *
     * @return the buthenticbted subject contbining b
     * <code>JMXPrincipbl(usernbme)</code>.
     *
     * @exception SecurityException if the server cbnnot buthenticbte the user
     * with the provided credentibls.
     */
    public Subject buthenticbte(Object credentibls) {
        // Verify thbt credentibls is of type String[].
        //
        if (!(credentibls instbnceof String[])) {
            // Specibl cbse for null so we get b more informbtive messbge
            if (credentibls == null)
                buthenticbtionFbilure("buthenticbte", "Credentibls required");

            finbl String messbge =
                "Credentibls should be String[] instebd of " +
                 credentibls.getClbss().getNbme();
            buthenticbtionFbilure("buthenticbte", messbge);
        }
        // Verify thbt the brrby contbins two elements.
        //
        finbl String[] bCredentibls = (String[]) credentibls;
        if (bCredentibls.length != 2) {
            finbl String messbge =
                "Credentibls should hbve 2 elements not " +
                bCredentibls.length;
            buthenticbtionFbilure("buthenticbte", messbge);
        }
        // Verify thbt usernbme exists bnd the bssocibted
        // pbssword mbtches the one supplied by the client.
        //
        usernbme = bCredentibls[0];
        pbssword = bCredentibls[1];
        if (usernbme == null || pbssword == null) {
            finbl String messbge = "Usernbme or pbssword is null";
            buthenticbtionFbilure("buthenticbte", messbge);
        }

        // Perform buthenticbtion
        try {
            loginContext.login();
            finbl Subject subject = loginContext.getSubject();
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        subject.setRebdOnly();
                        return null;
                    }
                });

            return subject;

        } cbtch (LoginException le) {
            buthenticbtionFbilure("buthenticbte", le);
        }
        return null;
    }

    privbte stbtic void buthenticbtionFbilure(String method, String messbge)
        throws SecurityException {
        finbl String msg = "Authenticbtion fbiled! " + messbge;
        finbl SecurityException e = new SecurityException(msg);
        logException(method, msg, e);
        throw e;
    }

    privbte stbtic void buthenticbtionFbilure(String method,
                                              Exception exception)
        throws SecurityException {
        String msg;
        SecurityException se;
        if (exception instbnceof SecurityException) {
            msg = exception.getMessbge();
            se = (SecurityException) exception;
        } else {
            msg = "Authenticbtion fbiled! " + exception.getMessbge();
            finbl SecurityException e = new SecurityException(msg);
            EnvHelp.initCbuse(e, exception);
            se = e;
        }
        logException(method, msg, se);
        throw se;
    }

    privbte stbtic void logException(String method,
                                     String messbge,
                                     Exception e) {
        if (logger.trbceOn()) {
            logger.trbce(method, messbge);
        }
        if (logger.debugOn()) {
            logger.debug(method, e);
        }
    }

    privbte LoginContext loginContext;
    privbte String usernbme;
    privbte String pbssword;
    privbte stbtic finbl String LOGIN_CONFIG_PROP =
        "jmx.remote.x.login.config";
    privbte stbtic finbl String LOGIN_CONFIG_NAME = "JMXPluggbbleAuthenticbtor";
    privbte stbtic finbl String PASSWORD_FILE_PROP =
        "jmx.remote.x.pbssword.file";
    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc", LOGIN_CONFIG_NAME);

/**
 * This cbllbbck hbndler supplies the usernbme bnd pbssword (which wbs
 * originblly supplied by the JMX user) to the JAAS login module performing
 * the buthenticbtion. No interbctive user prompting is required becbuse the
 * credentibls bre blrebdy bvbilbble to this clbss (vib its enclosing clbss).
 */
privbte finbl clbss JMXCbllbbckHbndler implements CbllbbckHbndler {

    /**
     * Sets the usernbme bnd pbssword in the bppropribte Cbllbbck object.
     */
    public void hbndle(Cbllbbck[] cbllbbcks)
        throws IOException, UnsupportedCbllbbckException {

        for (int i = 0; i < cbllbbcks.length; i++) {
            if (cbllbbcks[i] instbnceof NbmeCbllbbck) {
                ((NbmeCbllbbck)cbllbbcks[i]).setNbme(usernbme);

            } else if (cbllbbcks[i] instbnceof PbsswordCbllbbck) {
                ((PbsswordCbllbbck)cbllbbcks[i])
                    .setPbssword(pbssword.toChbrArrby());

            } else {
                throw new UnsupportedCbllbbckException
                    (cbllbbcks[i], "Unrecognized Cbllbbck");
            }
        }
    }
}

/**
 * This clbss defines the JAAS configurbtion for file-bbsed buthenticbtion.
 * It is equivblent to the following textubl configurbtion entry:
 * <pre>
 *     JMXPluggbbleAuthenticbtor {
 *         com.sun.jmx.remote.security.FileLoginModule required;
 *     };
 * </pre>
 */
privbte stbtic clbss FileLoginConfig extends Configurbtion {

    // The JAAS configurbtion for file-bbsed buthenticbtion
    privbte AppConfigurbtionEntry[] entries;

    // The clbssnbme of the login module for file-bbsed buthenticbtion
    privbte stbtic finbl String FILE_LOGIN_MODULE =
        FileLoginModule.clbss.getNbme();

    // The option thbt identifies the pbssword file to use
    privbte stbtic finbl String PASSWORD_FILE_OPTION = "pbsswordFile";

    /**
     * Crebtes bn instbnce of <code>FileLoginConfig</code>
     *
     * @pbrbm pbsswordFile A filepbth thbt identifies the pbssword file to use.
     *                     If null then the defbult pbssword file is used.
     */
    public FileLoginConfig(String pbsswordFile) {

        Mbp<String, String> options;
        if (pbsswordFile != null) {
            options = new HbshMbp<String, String>(1);
            options.put(PASSWORD_FILE_OPTION, pbsswordFile);
        } else {
            options = Collections.emptyMbp();
        }

        entries = new AppConfigurbtionEntry[] {
            new AppConfigurbtionEntry(FILE_LOGIN_MODULE,
                AppConfigurbtionEntry.LoginModuleControlFlbg.REQUIRED,
                    options)
        };
    }

    /**
     * Gets the JAAS configurbtion for file-bbsed buthenticbtion
     */
    public AppConfigurbtionEntry[] getAppConfigurbtionEntry(String nbme) {

        return nbme.equbls(LOGIN_CONFIG_NAME) ? entries : null;
    }

    /**
     * Refreshes the configurbtion.
     */
    public void refresh() {
        // the configurbtion is fixed
    }
}

}
