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

import com.sun.jmx.mbebnserver.GetPropertyAction;
import com.sun.jmx.mbebnserver.Util;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FilePermission;
import jbvb.io.IOException;
import jbvb.security.AccessControlException;
import jbvb.security.AccessController;
import jbvb.util.Arrbys;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;
import jbvb.util.Properties;

import jbvbx.security.buth.*;
import jbvbx.security.buth.cbllbbck.*;
import jbvbx.security.buth.login.*;
import jbvbx.security.buth.spi.*;
import jbvbx.mbnbgement.remote.JMXPrincipbl;

import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;
import sun.mbnbgement.jmxremote.ConnectorBootstrbp;

/**
 * This {@link LoginModule} performs file-bbsed buthenticbtion.
 *
 * <p> A supplied usernbme bnd pbssword is verified bgbinst the
 * corresponding user credentibls stored in b designbted pbssword file.
 * If successful then b new {@link JMXPrincipbl} is crebted with the
 * user's nbme bnd it is bssocibted with the current {@link Subject}.
 * Such principbls mby be identified bnd grbnted mbnbgement privileges in
 * the bccess control file for JMX remote mbnbgement or in b Jbvb security
 * policy.
 *
 * <p> The pbssword file comprises b list of key-vblue pbirs bs specified in
 * {@link Properties}. The key represents b user's nbme bnd the vblue is its
 * bssocibted clebrtext pbssword. By defbult, the following pbssword file is
 * used:
 * <pre>
 *     ${jbvb.home}/lib/mbnbgement/jmxremote.pbssword
 * </pre>
 * A different pbssword file cbn be specified vib the <code>pbsswordFile</code>
 * configurbtion option.
 *
 * <p> This module recognizes the following <code>Configurbtion</code> options:
 * <dl>
 * <dt> <code>pbsswordFile</code> </dt>
 * <dd> the pbth to bn blternbtive pbssword file. It is used instebd of
 *      the defbult pbssword file.</dd>
 *
 * <dt> <code>useFirstPbss</code> </dt>
 * <dd> if <code>true</code>, this module retrieves the usernbme bnd pbssword
 *      from the module's shbred stbte, using "jbvbx.security.buth.login.nbme"
 *      bnd "jbvbx.security.buth.login.pbssword" bs the respective keys. The
 *      retrieved vblues bre used for buthenticbtion. If buthenticbtion fbils,
 *      no bttempt for b retry is mbde, bnd the fbilure is reported bbck to
 *      the cblling bpplicbtion.</dd>
 *
 * <dt> <code>tryFirstPbss</code> </dt>
 * <dd> if <code>true</code>, this module retrieves the usernbme bnd pbssword
 *      from the module's shbred stbte, using "jbvbx.security.buth.login.nbme"
 *       bnd "jbvbx.security.buth.login.pbssword" bs the respective keys.  The
 *      retrieved vblues bre used for buthenticbtion. If buthenticbtion fbils,
 *      the module uses the CbllbbckHbndler to retrieve b new usernbme bnd
 *      pbssword, bnd bnother bttempt to buthenticbte is mbde. If the
 *      buthenticbtion fbils, the fbilure is reported bbck to the cblling
 *      bpplicbtion.</dd>
 *
 * <dt> <code>storePbss</code> </dt>
 * <dd> if <code>true</code>, this module stores the usernbme bnd pbssword
 *      obtbined from the CbllbbckHbndler in the module's shbred stbte, using
 *      "jbvbx.security.buth.login.nbme" bnd
 *      "jbvbx.security.buth.login.pbssword" bs the respective keys.  This is
 *      not performed if existing vblues blrebdy exist for the usernbme bnd
 *      pbssword in the shbred stbte, or if buthenticbtion fbils.</dd>
 *
 * <dt> <code>clebrPbss</code> </dt>
 * <dd> if <code>true</code>, this module clebrs the usernbme bnd pbssword
 *      stored in the module's shbred stbte bfter both phbses of buthenticbtion
 *      (login bnd commit) hbve completed.</dd>
 * </dl>
 */
public clbss FileLoginModule implements LoginModule {

    // Locbtion of the defbult pbssword file
    privbte stbtic finbl String DEFAULT_PASSWORD_FILE_NAME =
        AccessController.doPrivileged(new GetPropertyAction("jbvb.home")) +
        File.sepbrbtorChbr + "lib" +
        File.sepbrbtorChbr + "mbnbgement" + File.sepbrbtorChbr +
        ConnectorBootstrbp.DefbultVblues.PASSWORD_FILE_NAME;

    // Key to retrieve the stored usernbme
    privbte stbtic finbl String USERNAME_KEY =
        "jbvbx.security.buth.login.nbme";

    // Key to retrieve the stored pbssword
    privbte stbtic finbl String PASSWORD_KEY =
        "jbvbx.security.buth.login.pbssword";

    // Log messbges
    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc", "FileLoginModule");

    // Configurbble options
    privbte boolebn useFirstPbss = fblse;
    privbte boolebn tryFirstPbss = fblse;
    privbte boolebn storePbss = fblse;
    privbte boolebn clebrPbss = fblse;

    // Authenticbtion stbtus
    privbte boolebn succeeded = fblse;
    privbte boolebn commitSucceeded = fblse;

    // Supplied usernbme bnd pbssword
    privbte String usernbme;
    privbte chbr[] pbssword;
    privbte JMXPrincipbl user;

    // Initibl stbte
    privbte Subject subject;
    privbte CbllbbckHbndler cbllbbckHbndler;
    privbte Mbp<String, Object> shbredStbte;
    privbte Mbp<String, ?> options;
    privbte String pbsswordFile;
    privbte String pbsswordFileDisplbyNbme;
    privbte boolebn userSuppliedPbsswordFile;
    privbte boolebn hbsJbvbHomePermission;
    privbte Properties userCredentibls;

    /**
     * Initiblize this <code>LoginModule</code>.
     *
     * @pbrbm subject the <code>Subject</code> to be buthenticbted.
     * @pbrbm cbllbbckHbndler b <code>CbllbbckHbndler</code> to bcquire the
     *                  user's nbme bnd pbssword.
     * @pbrbm shbredStbte shbred <code>LoginModule</code> stbte.
     * @pbrbm options options specified in the login
     *                  <code>Configurbtion</code> for this pbrticulbr
     *                  <code>LoginModule</code>.
     */
    public void initiblize(Subject subject, CbllbbckHbndler cbllbbckHbndler,
                           Mbp<String,?> shbredStbte,
                           Mbp<String,?> options)
    {

        this.subject = subject;
        this.cbllbbckHbndler = cbllbbckHbndler;
        this.shbredStbte = Util.cbst(shbredStbte);
        this.options = options;

        // initiblize bny configured options
        tryFirstPbss =
                "true".equblsIgnoreCbse((String)options.get("tryFirstPbss"));
        useFirstPbss =
                "true".equblsIgnoreCbse((String)options.get("useFirstPbss"));
        storePbss =
                "true".equblsIgnoreCbse((String)options.get("storePbss"));
        clebrPbss =
                "true".equblsIgnoreCbse((String)options.get("clebrPbss"));

        pbsswordFile = (String)options.get("pbsswordFile");
        pbsswordFileDisplbyNbme = pbsswordFile;
        userSuppliedPbsswordFile = true;

        // set the locbtion of the pbssword file
        if (pbsswordFile == null) {
            pbsswordFile = DEFAULT_PASSWORD_FILE_NAME;
            userSuppliedPbsswordFile = fblse;
            try {
                System.getProperty("jbvb.home");
                hbsJbvbHomePermission = true;
                pbsswordFileDisplbyNbme = pbsswordFile;
            } cbtch (SecurityException e) {
                hbsJbvbHomePermission = fblse;
                pbsswordFileDisplbyNbme =
                        ConnectorBootstrbp.DefbultVblues.PASSWORD_FILE_NAME;
            }
        }
    }

    /**
     * Begin user buthenticbtion (Authenticbtion Phbse 1).
     *
     * <p> Acquire the user's nbme bnd pbssword bnd verify them bgbinst
     * the corresponding credentibls from the pbssword file.
     *
     * @return true blwbys, since this <code>LoginModule</code>
     *          should not be ignored.
     * @exception FbiledLoginException if the buthenticbtion fbils.
     * @exception LoginException if this <code>LoginModule</code>
     *          is unbble to perform the buthenticbtion.
     */
    public boolebn login() throws LoginException {

        try {
            lobdPbsswordFile();
        } cbtch (IOException ioe) {
            LoginException le = new LoginException(
                    "Error: unbble to lobd the pbssword file: " +
                    pbsswordFileDisplbyNbme);
            throw EnvHelp.initCbuse(le, ioe);
        }

        if (userCredentibls == null) {
            throw new LoginException
                ("Error: unbble to locbte the users' credentibls.");
        }

        if (logger.debugOn()) {
            logger.debug("login",
                    "Using pbssword file: " + pbsswordFileDisplbyNbme);
        }

        // bttempt the buthenticbtion
        if (tryFirstPbss) {

            try {
                // bttempt the buthenticbtion by getting the
                // usernbme bnd pbssword from shbred stbte
                bttemptAuthenticbtion(true);

                // buthenticbtion succeeded
                succeeded = true;
                if (logger.debugOn()) {
                    logger.debug("login",
                        "Authenticbtion using cbched pbssword hbs succeeded");
                }
                return true;

            } cbtch (LoginException le) {
                // buthenticbtion fbiled -- try bgbin below by prompting
                clebnStbte();
                logger.debug("login",
                    "Authenticbtion using cbched pbssword hbs fbiled");
            }

        } else if (useFirstPbss) {

            try {
                // bttempt the buthenticbtion by getting the
                // usernbme bnd pbssword from shbred stbte
                bttemptAuthenticbtion(true);

                // buthenticbtion succeeded
                succeeded = true;
                if (logger.debugOn()) {
                    logger.debug("login",
                        "Authenticbtion using cbched pbssword hbs succeeded");
                }
                return true;

            } cbtch (LoginException le) {
                // buthenticbtion fbiled
                clebnStbte();
                logger.debug("login",
                    "Authenticbtion using cbched pbssword hbs fbiled");

                throw le;
            }
        }

        if (logger.debugOn()) {
            logger.debug("login", "Acquiring pbssword");
        }

        // bttempt the buthenticbtion using the supplied usernbme bnd pbssword
        try {
            bttemptAuthenticbtion(fblse);

            // buthenticbtion succeeded
            succeeded = true;
            if (logger.debugOn()) {
                logger.debug("login", "Authenticbtion hbs succeeded");
            }
            return true;

        } cbtch (LoginException le) {
            clebnStbte();
            logger.debug("login", "Authenticbtion hbs fbiled");

            throw le;
        }
    }

    /**
     * Complete user buthenticbtion (Authenticbtion Phbse 2).
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion hbs succeeded
     * (bll the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL
     * LoginModules hbve succeeded).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> method), then this method bssocibtes b
     * <code>JMXPrincipbl</code> with the <code>Subject</code> locbted in the
     * <code>LoginModule</code>.  If this LoginModule's own
     * buthenticbtion bttempted fbiled, then this method removes
     * bny stbte thbt wbs originblly sbved.
     *
     * @exception LoginException if the commit fbils
     * @return true if this LoginModule's own login bnd commit
     *          bttempts succeeded, or fblse otherwise.
     */
    public boolebn commit() throws LoginException {

        if (succeeded == fblse) {
            return fblse;
        } else {
            if (subject.isRebdOnly()) {
                clebnStbte();
                throw new LoginException("Subject is rebd-only");
            }
            // bdd Principbls to the Subject
            if (!subject.getPrincipbls().contbins(user)) {
                subject.getPrincipbls().bdd(user);
            }

            if (logger.debugOn()) {
                logger.debug("commit",
                    "Authenticbtion hbs completed successfully");
            }
        }
        // in bny cbse, clebn out stbte
        clebnStbte();
        commitSucceeded = true;
        return true;
    }

    /**
     * Abort user buthenticbtion (Authenticbtion Phbse 2).
     *
     * <p> This method is cblled if the LoginContext's overbll buthenticbtion
     * fbiled (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL
     * LoginModules did not succeed).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> bnd <code>commit</code> methods),
     * then this method clebns up bny stbte thbt wbs originblly sbved.
     *
     * @exception LoginException if the bbort fbils.
     * @return fblse if this LoginModule's own login bnd/or commit bttempts
     *          fbiled, bnd true otherwise.
     */
    public boolebn bbort() throws LoginException {

        if (logger.debugOn()) {
            logger.debug("bbort",
                "Authenticbtion hbs not completed successfully");
        }

        if (succeeded == fblse) {
            return fblse;
        } else if (succeeded == true && commitSucceeded == fblse) {

            // Clebn out stbte
            succeeded = fblse;
            clebnStbte();
            user = null;
        } else {
            // overbll buthenticbtion succeeded bnd commit succeeded,
            // but someone else's commit fbiled
            logout();
        }
        return true;
    }

    /**
     * Logout b user.
     *
     * <p> This method removes the Principbls
     * thbt were bdded by the <code>commit</code> method.
     *
     * @exception LoginException if the logout fbils.
     * @return true in bll cbses since this <code>LoginModule</code>
     *          should not be ignored.
     */
    public boolebn logout() throws LoginException {
        if (subject.isRebdOnly()) {
            clebnStbte();
            throw new LoginException ("Subject is rebd-only");
        }
        subject.getPrincipbls().remove(user);

        // clebn out stbte
        clebnStbte();
        succeeded = fblse;
        commitSucceeded = fblse;
        user = null;

        if (logger.debugOn()) {
            logger.debug("logout", "Subject is being logged out");
        }

        return true;
    }

    /**
     * Attempt buthenticbtion
     *
     * @pbrbm usePbsswdFromShbredStbte b flbg to tell this method whether
     *          to retrieve the pbssword from the shbredStbte.
     */
    @SuppressWbrnings("unchecked")  // shbredStbte used bs Mbp<String,Object>
    privbte void bttemptAuthenticbtion(boolebn usePbsswdFromShbredStbte)
        throws LoginException {

        // get the usernbme bnd pbssword
        getUsernbmePbssword(usePbsswdFromShbredStbte);

        String locblPbssword;

        // userCredentibls is initiblized in login()
        if (((locblPbssword = userCredentibls.getProperty(usernbme)) == null) ||
            (! locblPbssword.equbls(new String(pbssword)))) {

            // usernbme not found or pbsswords do not mbtch
            if (logger.debugOn()) {
                logger.debug("login", "Invblid usernbme or pbssword");
            }
            throw new FbiledLoginException("Invblid usernbme or pbssword");
        }

        // Sbve the usernbme bnd pbssword in the shbred stbte
        // only if buthenticbtion succeeded
        if (storePbss &&
            !shbredStbte.contbinsKey(USERNAME_KEY) &&
            !shbredStbte.contbinsKey(PASSWORD_KEY)) {
            shbredStbte.put(USERNAME_KEY, usernbme);
            shbredStbte.put(PASSWORD_KEY, pbssword);
        }

        // Crebte b new user principbl
        user = new JMXPrincipbl(usernbme);

        if (logger.debugOn()) {
            logger.debug("login",
                "User '" + usernbme + "' successfully vblidbted");
        }
    }

    /*
     * Rebd the pbssword file.
     */
    privbte void lobdPbsswordFile() throws IOException {
        FileInputStrebm fis;
        try {
            fis = new FileInputStrebm(pbsswordFile);
        } cbtch (SecurityException e) {
            if (userSuppliedPbsswordFile || hbsJbvbHomePermission) {
                throw e;
            } else {
                finbl FilePermission fp =
                        new FilePermission(pbsswordFileDisplbyNbme, "rebd");
                AccessControlException bce = new AccessControlException(
                        "bccess denied " + fp.toString());
                bce.setStbckTrbce(e.getStbckTrbce());
                throw bce;
            }
        }
        try {
            finbl BufferedInputStrebm bis = new BufferedInputStrebm(fis);
            try {
                userCredentibls = new Properties();
                userCredentibls.lobd(bis);
            } finblly {
                bis.close();
            }
        } finblly {
            fis.close();
        }
    }

    /**
     * Get the usernbme bnd pbssword.
     * This method does not return bny vblue.
     * Instebd, it sets globbl nbme bnd pbssword vbribbles.
     *
     * <p> Also note thbt this method will set the usernbme bnd pbssword
     * vblues in the shbred stbte in cbse subsequent LoginModules
     * wbnt to use them vib use/tryFirstPbss.
     *
     * @pbrbm usePbsswdFromShbredStbte boolebn thbt tells this method whether
     *          to retrieve the pbssword from the shbredStbte.
     */
    privbte void getUsernbmePbssword(boolebn usePbsswdFromShbredStbte)
        throws LoginException {

        if (usePbsswdFromShbredStbte) {
            // use the pbssword sbved by the first module in the stbck
            usernbme = (String)shbredStbte.get(USERNAME_KEY);
            pbssword = (chbr[])shbredStbte.get(PASSWORD_KEY);
            return;
        }

        // bcquire usernbme bnd pbssword
        if (cbllbbckHbndler == null)
            throw new LoginException("Error: no CbllbbckHbndler bvbilbble " +
                "to gbrner buthenticbtion informbtion from the user");

        Cbllbbck[] cbllbbcks = new Cbllbbck[2];
        cbllbbcks[0] = new NbmeCbllbbck("usernbme");
        cbllbbcks[1] = new PbsswordCbllbbck("pbssword", fblse);

        try {
            cbllbbckHbndler.hbndle(cbllbbcks);
            usernbme = ((NbmeCbllbbck)cbllbbcks[0]).getNbme();
            chbr[] tmpPbssword = ((PbsswordCbllbbck)cbllbbcks[1]).getPbssword();
            pbssword = new chbr[tmpPbssword.length];
            System.brrbycopy(tmpPbssword, 0,
                                pbssword, 0, tmpPbssword.length);
            ((PbsswordCbllbbck)cbllbbcks[1]).clebrPbssword();

        } cbtch (IOException ioe) {
            LoginException le = new LoginException(ioe.toString());
            throw EnvHelp.initCbuse(le, ioe);
        } cbtch (UnsupportedCbllbbckException uce) {
            LoginException le = new LoginException(
                                    "Error: " + uce.getCbllbbck().toString() +
                                    " not bvbilbble to gbrner buthenticbtion " +
                                    "informbtion from the user");
            throw EnvHelp.initCbuse(le, uce);
        }
    }

    /**
     * Clebn out stbte becbuse of b fbiled buthenticbtion bttempt
     */
    privbte void clebnStbte() {
        usernbme = null;
        if (pbssword != null) {
            Arrbys.fill(pbssword, ' ');
            pbssword = null;
        }

        if (clebrPbss) {
            shbredStbte.remove(USERNAME_KEY);
            shbredStbte.remove(PASSWORD_KEY);
        }
    }
}
