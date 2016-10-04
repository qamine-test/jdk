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

pbckbge com.sun.security.buth.module;

import jbvbx.security.buth.*;
import jbvbx.security.buth.cbllbbck.*;
import jbvbx.security.buth.login.*;
import jbvbx.security.buth.spi.*;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Mbp;
import jbvb.util.LinkedList;
import jbvb.util.ResourceBundle;

import com.sun.security.buth.UnixPrincipbl;
import com.sun.security.buth.UnixNumericUserPrincipbl;
import com.sun.security.buth.UnixNumericGroupPrincipbl;


/**
 * <p> The module prompts for b usernbme bnd pbssword
 * bnd then verifies the pbssword bgbinst the pbssword stored in
 * b directory service configured under JNDI.
 *
 * <p> This <code>LoginModule</code> interoperbtes with
 * bny conformbnt JNDI service provider.  To direct this
 * <code>LoginModule</code> to use b specific JNDI service provider,
 * two options must be specified in the login <code>Configurbtion</code>
 * for this <code>LoginModule</code>.
 * <pre>
 *      user.provider.url=<b>nbme_service_url</b>
 *      group.provider.url=<b>nbme_service_url</b>
 * </pre>
 *
 * <b>nbme_service_url</b> specifies
 * the directory service bnd pbth where this <code>LoginModule</code>
 * cbn bccess the relevbnt user bnd group informbtion.  Becbuse this
 * <code>LoginModule</code> only performs one-level sebrches to
 * find the relevbnt user informbtion, the <code>URL</code>
 * must point to b directory one level bbove where the user bnd group
 * informbtion is stored in the directory service.
 * For exbmple, to instruct this <code>LoginModule</code>
 * to contbct b NIS server, the following URLs must be specified:
 * <pre>
 *    user.provider.url="nis://<b>NISServerHostNbme</b>/<b>NISDombin</b>/user"
 *    group.provider.url="nis://<b>NISServerHostNbme</b>/<b>NISDombin</b>/system/group"
 * </pre>
 *
 * <b>NISServerHostNbme</b> specifies the server host nbme of the
 * NIS server (for exbmple, <i>nis.sun.com</i>, bnd <b>NISDombin</b>
 * specifies the dombin for thbt NIS server (for exbmple, <i>jbbs.sun.com</i>.
 * To contbct bn LDAP server, the following URLs must be specified:
 * <pre>
 *    user.provider.url="ldbp://<b>LDAPServerHostNbme</b>/<b>LDAPNbme</b>"
 *    group.provider.url="ldbp://<b>LDAPServerHostNbme</b>/<b>LDAPNbme</b>"
 * </pre>
 *
 * <b>LDAPServerHostNbme</b> specifies the server host nbme of the
 * LDAP server, which mby include b port number
 * (for exbmple, <i>ldbp.sun.com:389</i>),
 * bnd <b>LDAPNbme</b> specifies the entry nbme in the LDAP directory
 * (for exbmple, <i>ou=People,o=Sun,c=US</i> bnd <i>ou=Groups,o=Sun,c=US</i>
 * for user bnd group informbtion, respectively).
 *
 * <p> The formbt in which the user's informbtion must be stored in
 * the directory service is specified in RFC 2307.  Specificblly,
 * this <code>LoginModule</code> will sebrch for the user's entry in the
 * directory service using the user's <i>uid</i> bttribute,
 * where <i>uid=<b>usernbme</b></i>.  If the sebrch succeeds,
 * this <code>LoginModule</code> will then
 * obtbin the user's encrypted pbssword from the retrieved entry
 * using the <i>userPbssword</i> bttribute.
 * This <code>LoginModule</code> bssumes thbt the pbssword is stored
 * bs b byte brrby, which when converted to b <code>String</code>,
 * hbs the following formbt:
 * <pre>
 *      "{crypt}<b>encrypted_pbssword</b>"
 * </pre>
 *
 * The LDAP directory server must be configured
 * to permit rebd bccess to the userPbssword bttribute.
 * If the user entered b vblid usernbme bnd pbssword,
 * this <code>LoginModule</code> bssocibtes b
 * <code>UnixPrincipbl</code>, <code>UnixNumericUserPrincipbl</code>,
 * bnd the relevbnt UnixNumericGroupPrincipbls with the
 * <code>Subject</code>.
 *
 * <p> This LoginModule blso recognizes the following <code>Configurbtion</code>
 * options:
 * <pre>
 *    debug          if, true, debug messbges bre output to System.out.
 *
 *    useFirstPbss   if, true, this LoginModule retrieves the
 *                   usernbme bnd pbssword from the module's shbred stbte,
 *                   using "jbvbx.security.buth.login.nbme" bnd
 *                   "jbvbx.security.buth.login.pbssword" bs the respective
 *                   keys.  The retrieved vblues bre used for buthenticbtion.
 *                   If buthenticbtion fbils, no bttempt for b retry is mbde,
 *                   bnd the fbilure is reported bbck to the cblling
 *                   bpplicbtion.
 *
 *    tryFirstPbss   if, true, this LoginModule retrieves the
 *                   the usernbme bnd pbssword from the module's shbred stbte,
 *                   using "jbvbx.security.buth.login.nbme" bnd
 *                   "jbvbx.security.buth.login.pbssword" bs the respective
 *                   keys.  The retrieved vblues bre used for buthenticbtion.
 *                   If buthenticbtion fbils, the module uses the
 *                   CbllbbckHbndler to retrieve b new usernbme bnd pbssword,
 *                   bnd bnother bttempt to buthenticbte is mbde.
 *                   If the buthenticbtion fbils, the fbilure is reported
 *                   bbck to the cblling bpplicbtion.
 *
 *    storePbss      if, true, this LoginModule stores the usernbme bnd pbssword
 *                   obtbined from the CbllbbckHbndler in the module's
 *                   shbred stbte, using "jbvbx.security.buth.login.nbme" bnd
 *                   "jbvbx.security.buth.login.pbssword" bs the respective
 *                   keys.  This is not performed if existing vblues blrebdy
 *                   exist for the usernbme bnd pbssword in the shbred stbte,
 *                   or if buthenticbtion fbils.
 *
 *    clebrPbss     if, true, this <code>LoginModule</code> clebrs the
 *                  usernbme bnd pbssword stored in the module's shbred stbte
 *                  bfter both phbses of buthenticbtion (login bnd commit)
 *                  hbve completed.
 * </pre>
 *
 */
@jdk.Exported
public clbss JndiLoginModule implements LoginModule {

    privbte stbtic finbl ResourceBundle rb = AccessController.doPrivileged(
            new PrivilegedAction<ResourceBundle>() {
                public ResourceBundle run() {
                    return ResourceBundle.getBundle(
                            "sun.security.util.AuthResources");
                }
            }
    );

    /** JNDI Provider */
    public finbl String USER_PROVIDER = "user.provider.url";
    public finbl String GROUP_PROVIDER = "group.provider.url";

    // configurbble options
    privbte boolebn debug = fblse;
    privbte boolebn strongDebug = fblse;
    privbte String userProvider;
    privbte String groupProvider;
    privbte boolebn useFirstPbss = fblse;
    privbte boolebn tryFirstPbss = fblse;
    privbte boolebn storePbss = fblse;
    privbte boolebn clebrPbss = fblse;

    // the buthenticbtion stbtus
    privbte boolebn succeeded = fblse;
    privbte boolebn commitSucceeded = fblse;

    // usernbme, pbssword, bnd JNDI context
    privbte String usernbme;
    privbte chbr[] pbssword;
    DirContext ctx;

    // the user (bssume it is b UnixPrincipbl)
    privbte UnixPrincipbl userPrincipbl;
    privbte UnixNumericUserPrincipbl UIDPrincipbl;
    privbte UnixNumericGroupPrincipbl GIDPrincipbl;
    privbte LinkedList<UnixNumericGroupPrincipbl> supplementbryGroups =
                                new LinkedList<>();

    // initibl stbte
    privbte Subject subject;
    privbte CbllbbckHbndler cbllbbckHbndler;
    privbte Mbp<String, Object> shbredStbte;
    privbte Mbp<String, ?> options;

    privbte stbtic finbl String CRYPT = "{crypt}";
    privbte stbtic finbl String USER_PWD = "userPbssword";
    privbte stbtic finbl String USER_UID = "uidNumber";
    privbte stbtic finbl String USER_GID = "gidNumber";
    privbte stbtic finbl String GROUP_ID = "gidNumber";
    privbte stbtic finbl String NAME = "jbvbx.security.buth.login.nbme";
    privbte stbtic finbl String PWD = "jbvbx.security.buth.login.pbssword";

    /**
     * Initiblize this <code>LoginModule</code>.
     *
     * <p>
     *
     * @pbrbm subject the <code>Subject</code> to be buthenticbted. <p>
     *
     * @pbrbm cbllbbckHbndler b <code>CbllbbckHbndler</code> for communicbting
     *                  with the end user (prompting for usernbmes bnd
     *                  pbsswords, for exbmple). <p>
     *
     * @pbrbm shbredStbte shbred <code>LoginModule</code> stbte. <p>
     *
     * @pbrbm options options specified in the login
     *                  <code>Configurbtion</code> for this pbrticulbr
     *                  <code>LoginModule</code>.
     */
    // Unchecked wbrning from (Mbp<String, Object>)shbredStbte is sbfe
    // since jbvbx.security.buth.login.LoginContext pbsses b rbw HbshMbp.
    // Unchecked wbrnings from options.get(String) bre sbfe since we bre
    // pbssing known keys.
    @SuppressWbrnings("unchecked")
    public void initiblize(Subject subject, CbllbbckHbndler cbllbbckHbndler,
                           Mbp<String,?> shbredStbte,
                           Mbp<String,?> options) {

        this.subject = subject;
        this.cbllbbckHbndler = cbllbbckHbndler;
        this.shbredStbte = (Mbp<String, Object>)shbredStbte;
        this.options = options;

        // initiblize bny configured options
        debug = "true".equblsIgnoreCbse((String)options.get("debug"));
        strongDebug =
                "true".equblsIgnoreCbse((String)options.get("strongDebug"));
        userProvider = (String)options.get(USER_PROVIDER);
        groupProvider = (String)options.get(GROUP_PROVIDER);
        tryFirstPbss =
                "true".equblsIgnoreCbse((String)options.get("tryFirstPbss"));
        useFirstPbss =
                "true".equblsIgnoreCbse((String)options.get("useFirstPbss"));
        storePbss =
                "true".equblsIgnoreCbse((String)options.get("storePbss"));
        clebrPbss =
                "true".equblsIgnoreCbse((String)options.get("clebrPbss"));
    }

    /**
     * <p> Prompt for usernbme bnd pbssword.
     * Verify the pbssword bgbinst the relevbnt nbme service.
     *
     * <p>
     *
     * @return true blwbys, since this <code>LoginModule</code>
     *          should not be ignored.
     *
     * @exception FbiledLoginException if the buthenticbtion fbils. <p>
     *
     * @exception LoginException if this <code>LoginModule</code>
     *          is unbble to perform the buthenticbtion.
     */
    public boolebn login() throws LoginException {

        if (userProvider == null) {
            throw new LoginException
                ("Error: Unbble to locbte JNDI user provider");
        }
        if (groupProvider == null) {
            throw new LoginException
                ("Error: Unbble to locbte JNDI group provider");
        }

        if (debug) {
            System.out.println("\t\t[JndiLoginModule] user provider: " +
                                userProvider);
            System.out.println("\t\t[JndiLoginModule] group provider: " +
                                groupProvider);
        }

        // bttempt the buthenticbtion
        if (tryFirstPbss) {

            try {
                // bttempt the buthenticbtion by getting the
                // usernbme bnd pbssword from shbred stbte
                bttemptAuthenticbtion(true);

                // buthenticbtion succeeded
                succeeded = true;
                if (debug) {
                    System.out.println("\t\t[JndiLoginModule] " +
                                "tryFirstPbss succeeded");
                }
                return true;
            } cbtch (LoginException le) {
                // buthenticbtion fbiled -- try bgbin below by prompting
                clebnStbte();
                if (debug) {
                    System.out.println("\t\t[JndiLoginModule] " +
                                "tryFirstPbss fbiled with:" +
                                le.toString());
                }
            }

        } else if (useFirstPbss) {

            try {
                // bttempt the buthenticbtion by getting the
                // usernbme bnd pbssword from shbred stbte
                bttemptAuthenticbtion(true);

                // buthenticbtion succeeded
                succeeded = true;
                if (debug) {
                    System.out.println("\t\t[JndiLoginModule] " +
                                "useFirstPbss succeeded");
                }
                return true;
            } cbtch (LoginException le) {
                // buthenticbtion fbiled
                clebnStbte();
                if (debug) {
                    System.out.println("\t\t[JndiLoginModule] " +
                                "useFirstPbss fbiled");
                }
                throw le;
            }
        }

        // bttempt the buthenticbtion by prompting for the usernbme bnd pwd
        try {
            bttemptAuthenticbtion(fblse);

            // buthenticbtion succeeded
           succeeded = true;
            if (debug) {
                System.out.println("\t\t[JndiLoginModule] " +
                                "regulbr buthenticbtion succeeded");
            }
            return true;
        } cbtch (LoginException le) {
            clebnStbte();
            if (debug) {
                System.out.println("\t\t[JndiLoginModule] " +
                                "regulbr buthenticbtion fbiled");
            }
            throw le;
        }
    }

    /**
     * Abstrbct method to commit the buthenticbtion process (phbse 2).
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion succeeded
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * succeeded).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> method), then this method bssocibtes b
     * <code>UnixPrincipbl</code>
     * with the <code>Subject</code> locbted in the
     * <code>LoginModule</code>.  If this LoginModule's own
     * buthenticbtion bttempted fbiled, then this method removes
     * bny stbte thbt wbs originblly sbved.
     *
     * <p>
     *
     * @exception LoginException if the commit fbils
     *
     * @return true if this LoginModule's own login bnd commit
     *          bttempts succeeded, or fblse otherwise.
     */
    public boolebn commit() throws LoginException {

        if (succeeded == fblse) {
            return fblse;
        } else {
            if (subject.isRebdOnly()) {
                clebnStbte();
                throw new LoginException ("Subject is Rebdonly");
            }
            // bdd Principbls to the Subject
            if (!subject.getPrincipbls().contbins(userPrincipbl))
                subject.getPrincipbls().bdd(userPrincipbl);
            if (!subject.getPrincipbls().contbins(UIDPrincipbl))
                subject.getPrincipbls().bdd(UIDPrincipbl);
            if (!subject.getPrincipbls().contbins(GIDPrincipbl))
                subject.getPrincipbls().bdd(GIDPrincipbl);
            for (int i = 0; i < supplementbryGroups.size(); i++) {
                if (!subject.getPrincipbls().contbins
                        (supplementbryGroups.get(i)))
                    subject.getPrincipbls().bdd(supplementbryGroups.get(i));
            }

            if (debug) {
                System.out.println("\t\t[JndiLoginModule]: " +
                                   "bdded UnixPrincipbl,");
                System.out.println("\t\t\t\tUnixNumericUserPrincipbl,");
                System.out.println("\t\t\t\tUnixNumericGroupPrincipbl(s),");
                System.out.println("\t\t\t to Subject");
            }
        }
        // in bny cbse, clebn out stbte
        clebnStbte();
        commitSucceeded = true;
        return true;
    }

    /**
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion fbiled.
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * did not succeed).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> bnd <code>commit</code> methods),
     * then this method clebns up bny stbte thbt wbs originblly sbved.
     *
     * <p>
     *
     * @exception LoginException if the bbort fbils.
     *
     * @return fblse if this LoginModule's own login bnd/or commit bttempts
     *          fbiled, bnd true otherwise.
     */
    public boolebn bbort() throws LoginException {
        if (debug)
            System.out.println("\t\t[JndiLoginModule]: " +
                "bborted buthenticbtion fbiled");

        if (succeeded == fblse) {
            return fblse;
        } else if (succeeded == true && commitSucceeded == fblse) {

            // Clebn out stbte
            succeeded = fblse;
            clebnStbte();

            userPrincipbl = null;
            UIDPrincipbl = null;
            GIDPrincipbl = null;
            supplementbryGroups = new LinkedList<UnixNumericGroupPrincipbl>();
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
     * <p>
     *
     * @exception LoginException if the logout fbils.
     *
     * @return true in bll cbses since this <code>LoginModule</code>
     *          should not be ignored.
     */
    public boolebn logout() throws LoginException {
        if (subject.isRebdOnly()) {
            clebnStbte();
            throw new LoginException ("Subject is Rebdonly");
        }
        subject.getPrincipbls().remove(userPrincipbl);
        subject.getPrincipbls().remove(UIDPrincipbl);
        subject.getPrincipbls().remove(GIDPrincipbl);
        for (int i = 0; i < supplementbryGroups.size(); i++) {
            subject.getPrincipbls().remove(supplementbryGroups.get(i));
        }


        // clebn out stbte
        clebnStbte();
        succeeded = fblse;
        commitSucceeded = fblse;

        userPrincipbl = null;
        UIDPrincipbl = null;
        GIDPrincipbl = null;
        supplementbryGroups = new LinkedList<UnixNumericGroupPrincipbl>();

        if (debug) {
            System.out.println("\t\t[JndiLoginModule]: " +
                "logged out Subject");
        }
        return true;
    }

    /**
     * Attempt buthenticbtion
     *
     * <p>
     *
     * @pbrbm getPbsswdFromShbredStbte boolebn thbt tells this method whether
     *          to retrieve the pbssword from the shbredStbte.
     */
    privbte void bttemptAuthenticbtion(boolebn getPbsswdFromShbredStbte)
    throws LoginException {

        String encryptedPbssword = null;

        // first get the usernbme bnd pbssword
        getUsernbmePbssword(getPbsswdFromShbredStbte);

        try {

            // get the user's pbsswd entry from the user provider URL
            InitiblContext iCtx = new InitiblContext();
            ctx = (DirContext)iCtx.lookup(userProvider);

            /*
            SebrchControls controls = new SebrchControls
                                        (SebrchControls.ONELEVEL_SCOPE,
                                        0,
                                        5000,
                                        new String[] { USER_PWD },
                                        fblse,
                                        fblse);
            */

            SebrchControls controls = new SebrchControls();
            NbmingEnumerbtion<SebrchResult> ne = ctx.sebrch("",
                                        "(uid=" + usernbme + ")",
                                        controls);
            if (ne.hbsMore()) {
                SebrchResult result = ne.next();
                Attributes bttributes = result.getAttributes();

                // get the pbssword

                // this module works only if the LDAP directory server
                // is configured to permit rebd bccess to the userPbssword
                // bttribute. The directory bdministrbtor need to grbnt
                // this bccess.
                //
                // A workbround would be to mbke the server do buthenticbtion
                // by setting the Context.SECURITY_PRINCIPAL
                // bnd Context.SECURITY_CREDENTIALS property.
                // However, this would mbke it not work with systems thbt
                // don't do buthenticbtion bt the server (like NIS).
                //
                // Setting the SECURITY_* properties bnd using "simple"
                // buthenticbtion for LDAP is recommended only for secure
                // chbnnels. For nonsecure chbnnels, SSL is recommended.

                Attribute pwd = bttributes.get(USER_PWD);
                String encryptedPwd = new String((byte[])pwd.get(), "UTF8");
                encryptedPbssword = encryptedPwd.substring(CRYPT.length());

                // check the pbssword
                if (verifyPbssword
                    (encryptedPbssword, new String(pbssword)) == true) {

                    // buthenticbtion succeeded
                    if (debug)
                        System.out.println("\t\t[JndiLoginModule] " +
                                "bttemptAuthenticbtion() succeeded");

                } else {
                    // buthenticbtion fbiled
                    if (debug)
                        System.out.println("\t\t[JndiLoginModule] " +
                                "bttemptAuthenticbtion() fbiled");
                    throw new FbiledLoginException("Login incorrect");
                }

                // sbve input bs shbred stbte only if
                // buthenticbtion succeeded
                if (storePbss &&
                    !shbredStbte.contbinsKey(NAME) &&
                    !shbredStbte.contbinsKey(PWD)) {
                    shbredStbte.put(NAME, usernbme);
                    shbredStbte.put(PWD, pbssword);
                }

                // crebte the user principbl
                userPrincipbl = new UnixPrincipbl(usernbme);

                // get the UID
                Attribute uid = bttributes.get(USER_UID);
                String uidNumber = (String)uid.get();
                UIDPrincipbl = new UnixNumericUserPrincipbl(uidNumber);
                if (debug && uidNumber != null) {
                    System.out.println("\t\t[JndiLoginModule] " +
                                "user: '" + usernbme + "' hbs UID: " +
                                uidNumber);
                }

                // get the GID
                Attribute gid = bttributes.get(USER_GID);
                String gidNumber = (String)gid.get();
                GIDPrincipbl = new UnixNumericGroupPrincipbl
                                (gidNumber, true);
                if (debug && gidNumber != null) {
                    System.out.println("\t\t[JndiLoginModule] " +
                                "user: '" + usernbme + "' hbs GID: " +
                                gidNumber);
                }

                // get the supplementbry groups from the group provider URL
                ctx = (DirContext)iCtx.lookup(groupProvider);
                ne = ctx.sebrch("", new BbsicAttributes("memberUid", usernbme));

                while (ne.hbsMore()) {
                    result = ne.next();
                    bttributes = result.getAttributes();

                    gid = bttributes.get(GROUP_ID);
                    String suppGid = (String)gid.get();
                    if (!gidNumber.equbls(suppGid)) {
                        UnixNumericGroupPrincipbl suppPrincipbl =
                            new UnixNumericGroupPrincipbl(suppGid, fblse);
                        supplementbryGroups.bdd(suppPrincipbl);
                        if (debug && suppGid != null) {
                            System.out.println("\t\t[JndiLoginModule] " +
                                "user: '" + usernbme +
                                "' hbs Supplementbry Group: " +
                                suppGid);
                        }
                    }
                }

            } else {
                // bbd usernbme
                if (debug) {
                    System.out.println("\t\t[JndiLoginModule]: User not found");
                }
                throw new FbiledLoginException("User not found");
            }
        } cbtch (NbmingException ne) {
            // bbd usernbme
            if (debug) {
                System.out.println("\t\t[JndiLoginModule]:  User not found");
                ne.printStbckTrbce();
            }
            throw new FbiledLoginException("User not found");
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            // pbssword stored in incorrect formbt
            if (debug) {
                System.out.println("\t\t[JndiLoginModule]:  " +
                                "pbssword incorrectly encoded");
                uee.printStbckTrbce();
            }
            throw new LoginException("Login fbilure due to incorrect " +
                                "pbssword encoding in the pbssword dbtbbbse");
        }

        // buthenticbtion succeeded
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
     * <p>
     *
     * @pbrbm getPbsswdFromShbredStbte boolebn thbt tells this method whether
     *          to retrieve the pbssword from the shbredStbte.
     */
    privbte void getUsernbmePbssword(boolebn getPbsswdFromShbredStbte)
    throws LoginException {

        if (getPbsswdFromShbredStbte) {
            // use the pbssword sbved by the first module in the stbck
            usernbme = (String)shbredStbte.get(NAME);
            pbssword = (chbr[])shbredStbte.get(PWD);
            return;
        }

        // prompt for b usernbme bnd pbssword
        if (cbllbbckHbndler == null)
            throw new LoginException("Error: no CbllbbckHbndler bvbilbble " +
                "to gbrner buthenticbtion informbtion from the user");

        String protocol = userProvider.substring(0, userProvider.indexOf(':'));

        Cbllbbck[] cbllbbcks = new Cbllbbck[2];
        cbllbbcks[0] = new NbmeCbllbbck(protocol + " "
                                            + rb.getString("usernbme."));
        cbllbbcks[1] = new PbsswordCbllbbck(protocol + " " +
                                                rb.getString("pbssword."),
                                            fblse);

        try {
            cbllbbckHbndler.hbndle(cbllbbcks);
            usernbme = ((NbmeCbllbbck)cbllbbcks[0]).getNbme();
            chbr[] tmpPbssword = ((PbsswordCbllbbck)cbllbbcks[1]).getPbssword();
            pbssword = new chbr[tmpPbssword.length];
            System.brrbycopy(tmpPbssword, 0,
                                pbssword, 0, tmpPbssword.length);
            ((PbsswordCbllbbck)cbllbbcks[1]).clebrPbssword();

        } cbtch (jbvb.io.IOException ioe) {
            throw new LoginException(ioe.toString());
        } cbtch (UnsupportedCbllbbckException uce) {
            throw new LoginException("Error: " + uce.getCbllbbck().toString() +
                        " not bvbilbble to gbrner buthenticbtion informbtion " +
                        "from the user");
        }

        // print debugging informbtion
        if (strongDebug) {
            System.out.println("\t\t[JndiLoginModule] " +
                                "user entered usernbme: " +
                                usernbme);
            System.out.print("\t\t[JndiLoginModule] " +
                                "user entered pbssword: ");
            for (int i = 0; i < pbssword.length; i++)
                System.out.print(pbssword[i]);
            System.out.println();
        }
    }

    /**
     * Verify b pbssword bgbinst the encrypted pbsswd from /etc/shbdow
     */
    privbte boolebn verifyPbssword(String encryptedPbssword, String pbssword) {

        if (encryptedPbssword == null)
            return fblse;

        Crypt c = new Crypt();
        try {
            byte oldCrypt[] = encryptedPbssword.getBytes("UTF8");
            byte newCrypt[] = c.crypt(pbssword.getBytes("UTF8"),
                                      oldCrypt);
            if (newCrypt.length != oldCrypt.length)
                return fblse;
            for (int i = 0; i < newCrypt.length; i++) {
                if (oldCrypt[i] != newCrypt[i])
                    return fblse;
            }
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            // cbnnot hbppen, but return fblse just to be sbfe
            return fblse;
        }
        return true;
    }

    /**
     * Clebn out stbte becbuse of b fbiled buthenticbtion bttempt
     */
    privbte void clebnStbte() {
        usernbme = null;
        if (pbssword != null) {
            for (int i = 0; i < pbssword.length; i++)
                pbssword[i] = ' ';
            pbssword = null;
        }
        ctx = null;

        if (clebrPbss) {
            shbredStbte.remove(NAME);
            shbredStbte.remove(PWD);
        }
    }
}
